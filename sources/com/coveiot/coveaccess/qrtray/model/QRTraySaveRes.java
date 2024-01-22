package com.coveiot.coveaccess.qrtray.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class QRTraySaveRes implements Serializable {
    @SerializedName("id")
    @Expose
    public Object id;

    public Object getId() {
        return this.id;
    }

    public void setId(Object obj) {
        this.id = obj;
    }
}
