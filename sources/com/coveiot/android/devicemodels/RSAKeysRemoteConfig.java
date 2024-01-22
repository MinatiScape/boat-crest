package com.coveiot.android.devicemodels;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes4.dex */
public class RSAKeysRemoteConfig implements Serializable {
    @SerializedName("rsa_encryptdecrypt_keys")
    private RSAEncryptDecryptKeys rsa_encryptdecrypt_keys;

    /* loaded from: classes4.dex */
    public static class RSAEncryptDecryptKeys {
        @SerializedName("private_key")

        /* renamed from: a  reason: collision with root package name */
        private String f4304a;
        @SerializedName("public_key")
        private String b;

        public String getPrivate_key() {
            return this.f4304a;
        }

        public String getPublic_key() {
            return this.b;
        }

        public void setPrivate_key(String str) {
            this.f4304a = str;
        }

        public void setPublic_key(String str) {
            this.b = str;
        }
    }

    public RSAEncryptDecryptKeys getRsa_encryptdecrypt_keys() {
        return this.rsa_encryptdecrypt_keys;
    }

    public void setRsa_encryptdecrypt_keys(RSAEncryptDecryptKeys rSAEncryptDecryptKeys) {
        this.rsa_encryptdecrypt_keys = this.rsa_encryptdecrypt_keys;
    }
}
