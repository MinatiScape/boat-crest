package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import com.mappls.android.lms.MapplsLMSDbAdapter;
/* loaded from: classes8.dex */
public class SEgLogoutRequest {
    @SerializedName(MapplsLMSDbAdapter.KEY_TOKEN)
    private String token;
    @SerializedName("token_type_hint")
    private String tokenTypeHint = "refresh_token";

    public String getToken() {
        return this.token;
    }

    public String getTokenTypeHint() {
        return this.tokenTypeHint;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
