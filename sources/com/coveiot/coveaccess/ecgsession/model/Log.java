package com.coveiot.coveaccess.ecgsession.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Log {
    @SerializedName("seqNumber")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6484a;
    @SerializedName("codedValues")
    @Expose
    private List<Integer> b = null;

    public List<Integer> getCodedValues() {
        return this.b;
    }

    public String getSeqNumber() {
        return this.f6484a;
    }

    public void setCodedValues(List<Integer> list) {
        this.b = list;
    }

    public void setSeqNumber(String str) {
        this.f6484a = str;
    }
}
