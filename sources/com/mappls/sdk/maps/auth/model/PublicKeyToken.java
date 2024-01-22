package com.mappls.sdk.maps.auth.model;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes11.dex */
public class PublicKeyToken {
    @SerializedName("expiresOn")
    @Expose
    private long expiresOn;
    @SerializedName("publicKey")
    @Expose
    private String publicKey;

    public long getExpiresOn() {
        return this.expiresOn;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setExpiresOn(long j) {
        this.expiresOn = j;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }
}
