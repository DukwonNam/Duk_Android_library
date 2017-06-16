package com.duk.lab.android.bluetooth;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.duk.lab.android.R;

/**
 * Created by Duk on 2016-12-19.
 */

public class BluetoothMainFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "BluetoothMainFragment";
    private static final String BT_SCAN_START = "Scan BT";
    private static final String BT_SCAN_STOP = "Stop BT";

    private static final int REQUEST_BT = 99;

    private RecyclerView mBTFoundListView;
    private BTDeviceListAdapter mBTDeviceListAdapter;
    private Button mToggleScanBt;
    private BluetoothAdapter mBTAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.bluetooth_main, container, false);

        mBTFoundListView = (RecyclerView)view.findViewById(R.id.btFoundList);
        mBTFoundListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mBTFoundListView.setHasFixedSize(true);
        mBTDeviceListAdapter = new BTDeviceListAdapter(getActivity(), mBTDeviceItemListener);
        mBTFoundListView.setAdapter(mBTDeviceListAdapter);

        mToggleScanBt = (Button)view.findViewById(R.id.toggleScanBt);
        mToggleScanBt.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopScan();
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.toggleScanBt:
                if (BT_SCAN_START.equalsIgnoreCase(mToggleScanBt.getText().toString())) {
                    startScan();
                } else {
                    stopScan();
                }
                break;
        }
    }

    private void startScan() {
        mToggleScanBt.setText(BT_SCAN_STOP);

        if (Build.VERSION.SDK_INT <= 17) {
            mBTAdapter = BluetoothAdapter.getDefaultAdapter();
        } else {
            BluetoothManager btMgr = (BluetoothManager)getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
            mBTAdapter = btMgr.getAdapter();
        }
        Log.d(TAG, "startScan mBTAdapter=" + mBTAdapter);

        if (mBTAdapter == null) {
            return;
        }

        if (!mBTAdapter.isEnabled()) {
            Log.d(TAG, "startScan mBTAdapter.isEnabled() is false");
            Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBT, REQUEST_BT);
            return;
        }

        if (mBTAdapter.isDiscovering()) {
            Log.d(TAG, "startScan mBTAdapter.isDiscovering");
            mBTAdapter.cancelDiscovery();
        }

        mBTDeviceListAdapter.clear();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        getActivity().registerReceiver(mBTFoundReceiver, filter);

        boolean result = mBTAdapter.startDiscovery();
        Log.d(TAG, "startScan mBTAdapter.startDiscovery result=" + result);
    }

    private void stopScan() {
        mToggleScanBt.setText(BT_SCAN_START);

        Log.d(TAG, "stopScan mBTAdapter=" + mBTAdapter);
        if (mBTAdapter == null) {
            return;
        }

        getActivity().unregisterReceiver(mBTFoundReceiver);
        mBTAdapter.cancelDiscovery();
        mBTAdapter = null;
    }

    private BroadcastReceiver mBTFoundReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "mBTFoundReceiver onReceive intent=" + intent);

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d(TAG, "device=" + device);

                mBTDeviceListAdapter.add(device);
                mBTDeviceListAdapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                stopScan();
            }
        }
    };

    BTDeviceListAdapter.BTDeviceListItemListener mBTDeviceItemListener = new BTDeviceListAdapter.BTDeviceListItemListener() {
        @Override
        public void onClickItem(BluetoothDevice device) {
            stopScan();
            if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                return;
            }

            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
            getActivity().registerReceiver(mBTConectReceiver, filter);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                device.createBond();
            }
        }
    };

    private BroadcastReceiver mBTConectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "mBTConectReceiver onReceive intent=" + intent);

            if (BluetoothDevice.ACTION_PAIRING_REQUEST.equals(action)) {;
            }
        }
    };
}
