package com.duk.lab.android.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duk.lab.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dukwonnam on 2017. 6. 16..
 */

public class BTDeviceListAdapter extends RecyclerView.Adapter {

    interface BTDeviceListItemListener {
        void onClickItem(BluetoothDevice device);
    }

    class BTItem {
        public static final int VIEWTYPE_DEVICE = 0;
        public static final int VIEWTYPE_LINE = 1;

        final BluetoothDevice device;
        final int viewType;

        BTItem(BluetoothDevice device, int viewType) {
            this.device = device;
            this.viewType = viewType;
        }
    }

    private final List<BTItem> mBondedDeviceList = new ArrayList<>();
    private final List<BTItem> mNewDeviceList = new ArrayList<>();
    private final Context mContext;
    private final BTDeviceListItemListener mListener;

    BTDeviceListAdapter(Context context, BTDeviceListItemListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void add(BluetoothDevice device) {
        switch (device.getBondState()) {
            case BluetoothDevice.BOND_BONDED:
                mBondedDeviceList.add(new BTItem(device, BTItem.VIEWTYPE_DEVICE));
                break;
            case BluetoothDevice.BOND_BONDING:
            case BluetoothDevice.BOND_NONE:
            default:
                mNewDeviceList.add(new BTItem(device, BTItem.VIEWTYPE_DEVICE));
                break;
        }
    }

    public void clear() {
        mNewDeviceList.clear();
        mBondedDeviceList.clear();
        mBondedDeviceList.add(new BTItem(null, BTItem.VIEWTYPE_LINE));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bt_device_item, parent, false);
        return new BTDeviceItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final BluetoothDevice device;
        if (mNewDeviceList.size() > position) {
            device = mNewDeviceList.get(position).device;
        } else {
            device = mBondedDeviceList.get(position - mNewDeviceList.size()).device;
        }

        BTDeviceItemHolder btDeviceItemHolder = (BTDeviceItemHolder) holder;
        if (device == null) {
            btDeviceItemHolder.bondState.setVisibility(View.GONE);
            btDeviceItemHolder.deviceName.setVisibility(View.GONE);
            btDeviceItemHolder.deviceAddress.setText("=========================================================================");
        } else {
            btDeviceItemHolder.bondState.setVisibility(View.VISIBLE);
            btDeviceItemHolder.deviceName.setVisibility(View.VISIBLE);
            switch (device.getBondState()) {
                case BluetoothDevice.BOND_BONDED:
                    btDeviceItemHolder.bondState.setText("BONDED");
                    break;
                case BluetoothDevice.BOND_BONDING:
                    btDeviceItemHolder.bondState.setText("BONDING");
                    break;
                case BluetoothDevice.BOND_NONE:
                default:
                    btDeviceItemHolder.bondState.setText("NONE");
                    break;
            }
            btDeviceItemHolder.deviceName.setText(device.getName());
            btDeviceItemHolder.deviceAddress.setText(device.getAddress());
        }

    }

    @Override
    public int getItemCount() {
        return mNewDeviceList.size() + mBondedDeviceList.size();
    }

    class BTDeviceItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bondState;
        TextView deviceName;
        TextView deviceAddress;

        public BTDeviceItemHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            bondState = (TextView)itemView.findViewById(R.id.bondState);
            deviceName = (TextView)itemView.findViewById(R.id.deviceName);
            deviceAddress = (TextView)itemView.findViewById(R.id.deviceAddress);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            final BluetoothDevice device;
            if (mNewDeviceList.size() > position) {
                device = mNewDeviceList.get(position).device;
            } else {
                device = mBondedDeviceList.get(position - mNewDeviceList.size()).device;
            }

            if (mListener != null) {
                mListener.onClickItem(device);
            }
        }
    }
}
