package com.mappls.sdk.services.api.publickey.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@Keep
/* loaded from: classes7.dex */
public class PublicKeyResponse {
    @SerializedName("expiresAfter")
    private Long expiresAfter;
    @SerializedName("expiresOn")
    private Long expiresOn;
    @SerializedName(OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME)
    private String secretKey;

    public Long getExpiresAfter() {
        return this.expiresAfter;
    }

    public Long getExpiresOn() {
        return this.expiresOn;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setExpiresAfter(Long l) {
        this.expiresAfter = l;
    }

    public void setExpiresOn(Long l) {
        this.expiresOn = l;
    }

    public void setSecretKey(String str) {
        this.secretKey = str;
    }
}
