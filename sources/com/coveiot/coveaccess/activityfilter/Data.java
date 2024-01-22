package com.coveiot.coveaccess.activityfilter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class Data {
    @SerializedName("filterBy")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6379a;
    @SerializedName("options")
    @Expose
    private List<Option> b = null;

    public String getFilterBy() {
        return this.f6379a;
    }

    public List<Option> getOptions() {
        return this.b;
    }

    public void setFilterBy(String str) {
        this.f6379a = str;
    }

    public void setOptions(List<Option> list) {
        this.b = list;
    }
}
