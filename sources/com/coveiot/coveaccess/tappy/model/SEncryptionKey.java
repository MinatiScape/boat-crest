package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes8.dex */
public class SEncryptionKey implements Serializable {
    @SerializedName(RsaJsonWebKey.EXPONENT_MEMBER_NAME)
    @Expose
    private String e;
    @SerializedName(JsonWebKey.KEY_TYPE_PARAMETER)
    @Expose
    private String kty;
    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName(JsonWebKey.USE_PARAMETER)
    @Expose
    private String use;

    public String getE() {
        return this.e;
    }

    public String getKty() {
        return this.kty;
    }

    public String getN() {
        return this.n;
    }

    public String getUse() {
        return this.use;
    }

    public void setE(String str) {
        this.e = str;
    }

    public void setKty(String str) {
        this.kty = str;
    }

    public void setN(String str) {
        this.n = str;
    }

    public void setUse(String str) {
        this.use = str;
    }
}
