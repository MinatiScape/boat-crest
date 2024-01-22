package com.mappls.sdk.services.api.publickey;

import androidx.annotation.Keep;
import com.google.gson.annotations.SerializedName;
@Keep
/* loaded from: classes7.dex */
class PublicKeyRequest {
    @SerializedName("d")
    private String deviceId;
    @SerializedName("hsa")
    private String hashingAlgo;

    public PublicKeyRequest(String str, String str2) {
        this.deviceId = str;
        this.hashingAlgo = str2;
    }
}
