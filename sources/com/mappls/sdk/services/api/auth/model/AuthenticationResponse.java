package com.mappls.sdk.services.api.auth.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jose4j.jwk.RsaJsonWebKey;
@Keep
/* loaded from: classes11.dex */
public class AuthenticationResponse {
    @SerializedName(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT)
    @Expose
    private String tokenString;

    public String getTokenString() {
        return this.tokenString;
    }

    public void setTokenString(String str) {
        this.tokenString = str;
    }
}
