package com.coveiot.coveaccess.ecgsession.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class EcgGraph {
    @SerializedName("mediaClass")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6482a;
    @SerializedName("mediaId")
    @Expose
    private String b;

    public String getMediaClass() {
        return this.f6482a;
    }

    public String getMediaId() {
        return this.b;
    }

    public void setMediaClass(String str) {
        this.f6482a = str;
    }

    public void setMediaId(String str) {
        this.b = str;
    }
}
