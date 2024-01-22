package com.coveiot.coveaccess.qrtray.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public class QRTrayCodeIDsReq implements Serializable {
    @SerializedName("qrCodeIds")
    @Expose
    public List<String> qrCodeIds;

    public QRTrayCodeIDsReq(List<String> list) {
        this.qrCodeIds = list;
    }

    public List<String> getQrCodeIds() {
        return this.qrCodeIds;
    }

    public void setQrCodeIds(List<String> list) {
        this.qrCodeIds = list;
    }
}
