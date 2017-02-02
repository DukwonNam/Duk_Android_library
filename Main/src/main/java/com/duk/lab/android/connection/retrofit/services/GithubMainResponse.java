package com.duk.lab.android.connection.retrofit.services;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * Created by dukwonnam on 2017. 2. 2..
 */

public class GithubMainResponse {

    @SerializedName("current_user_url")
    private String current_user_url;

    @SerializedName("current_user_authorizations_html_url")
    private String current_user_authorizations_html_url;

    @SerializedName("authorizations_url")
    private String authorizations_url;

    @SerializedName("code_search_url")
    private String code_search_url;

    @SerializedName("commit_search_url")
    private String commit_search_url;

    @SerializedName("emails_url")
    private String emails_url;

    @SerializedName("emojis_url")
    private String emojis_url;

    @SerializedName("events_url")
    private String events_url;

    @SerializedName("feeds_url")
    private String feeds_url;

    @SerializedName("followers_url")
    private String followers_url;

    @SerializedName("following_url")
    private String following_url;

    @SerializedName("gists_url")
    private String gists_url;

    @SerializedName("hub_url")
    private String hub_url;

    @SerializedName("issue_search_url")
    private String issue_search_url;

    @SerializedName("issues_url")
    private String issues_url;

    @SerializedName("keys_url")
    private String keys_url;

    @SerializedName("notifications_url")
    private String notifications_url;

    @SerializedName("organization_repositories_url")
    private String organization_repositories_url;

    @SerializedName("organization_url")
    private String organization_url;

    @SerializedName("public_gists_url")
    private String public_gists_url;

    @SerializedName("rate_limit_url")
    private String rate_limit_url;

    @SerializedName("repository_url")
    private String repository_url;

    @SerializedName("repository_search_url")
    private String repository_search_url;

    @SerializedName("current_user_repositories_url")
    private String current_user_repositories_url;

    @SerializedName("starred_url")
    private String starred_url;

    @SerializedName("starred_gists_url")
    private String starred_gists_url;

    @SerializedName("team_url")
    private String team_url;

    @SerializedName("user_url")
    private String user_url;

    @SerializedName("user_organizations_url")
    private String user_organizations_url;

    @SerializedName("user_repositories_url")
    private String user_repositories_url;

    @SerializedName("user_search_url")
    private String user_search_url;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        Field[] fieldArray = this.getClass().getDeclaredFields();

        try {
            for (int i = 0; i < fieldArray.length; i++) {
                final Field field = fieldArray[i];
                field.setAccessible(true);
                sb.append("[").append(i).append("] ").append(field.getName()).append(" = ").append(field.get(this)).append("\n");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
