package com.mappls.sdk.services.api.auth.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class AtlasAuthToken {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
    @SerializedName("client_id")
    @Expose
    public String clientId;
    @SerializedName("expires_in")
    @Expose
    public long expiresIn;
    @SerializedName("project_code")
    @Expose
    public String projectCode;
    @SerializedName("refresh_token")
    @Expose
    public String refreshToken;
    @SerializedName("scope")
    @Expose
    public String scope;
    @SerializedName("token_type")
    @Expose
    public String tokenType;
    @SerializedName("user_name")
    @Expose
    public String userId;

    public String getAccessToken() {
        return this.accessToken;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getScope() {
        return this.scope;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getUserId() {
        return this.userId;
    }
}
