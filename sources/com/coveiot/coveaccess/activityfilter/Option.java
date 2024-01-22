package com.coveiot.coveaccess.activityfilter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class Option {
    @SerializedName("seqNumber")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Integer f6380a;
    @SerializedName("optionName")
    @Expose
    private String b;
    @SerializedName("categoryId")
    @Expose
    private Integer c;
    @SerializedName("sessionType")
    @Expose
    private String d;
    @SerializedName("iconUrl")
    @Expose
    private String e;

    public Integer getCategoryId() {
        return this.c;
    }

    public String getIconUrl() {
        return this.e;
    }

    public String getOptionName() {
        return this.b;
    }

    public Integer getSeqNumber() {
        return this.f6380a;
    }

    public String getSessionType() {
        return this.d;
    }

    public void setCategoryId(Integer num) {
        this.c = num;
    }

    public void setIconUrl(String str) {
        this.e = str;
    }

    public void setOptionName(String str) {
        this.b = str;
    }

    public void setSeqNumber(Integer num) {
        this.f6380a = num;
    }

    public void setSessionType(String str) {
        this.d = str;
    }
}
