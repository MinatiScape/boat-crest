package com.coveiot.coveaccess.onekactivity;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class PhysicalActivityItem {
    @SerializedName("categoryId")

    /* renamed from: a  reason: collision with root package name */
    private Integer f6674a;
    @SerializedName("activityId")
    private Integer b;
    @SerializedName("activityCode")
    private String c;
    @SerializedName("cpaCode")
    private String d;
    @SerializedName("inbuilt")
    private Boolean e;
    @SerializedName("activityName")
    private String f;
    @SerializedName("descInMetric")
    private String g;
    @SerializedName("descInImperial")
    private String h;
    @SerializedName("defaultMets")
    private Double i;
    @SerializedName("calorieFunc")
    private String j;
    @SerializedName("metrics")
    private List<String> k;
    @SerializedName("seqNumber")
    private Integer l;
    @SerializedName("fwActId")
    private int m;
    @SerializedName("fwActIdFmt")
    private String n;
    @SerializedName("iconUrl")
    private String o;
    @SerializedName("deviceIcons")
    private List<DeviceIcon> p = null;
    @SerializedName("titleInMetric")
    private String q;
    @SerializedName("titleInImperial")
    private String r;
    @SerializedName("dvcTitleInMetric")
    private List<String> s;
    @SerializedName("dvcTitleInImperial")
    private List<String> t;

    public String getActivityCode() {
        return this.c;
    }

    public Integer getActivityId() {
        return this.b;
    }

    public String getActivityName() {
        return this.f;
    }

    public String getCalorieFunc() {
        return this.j;
    }

    public Integer getCategoryId() {
        return this.f6674a;
    }

    public String getCpaCode() {
        return this.d;
    }

    public Double getDefaultMets() {
        return this.i;
    }

    public String getDescInImperial() {
        return this.h;
    }

    public String getDescInMetric() {
        return this.g;
    }

    public List<DeviceIcon> getDeviceIcons() {
        return this.p;
    }

    public List<String> getDvcTitleInImperial() {
        return this.t;
    }

    public List<String> getDvcTitleInMetric() {
        return this.s;
    }

    public int getFwActId() {
        return this.m;
    }

    public String getFwActIdFmt() {
        return this.n;
    }

    public String getIconUrl() {
        return this.o;
    }

    public Boolean getInbuilt() {
        return this.e;
    }

    public List<String> getMetrics() {
        return this.k;
    }

    public Integer getSeqNumber() {
        return this.l;
    }

    public String getTitleInImperial() {
        return this.r;
    }

    public String getTitleInMetric() {
        return this.q;
    }

    public void setActivityCode(String str) {
        this.c = str;
    }

    public void setActivityId(Integer num) {
        this.b = num;
    }

    public void setActivityName(String str) {
        this.f = str;
    }

    public void setCalorieFunc(String str) {
        this.j = str;
    }

    public void setCategoryId(Integer num) {
        this.f6674a = num;
    }

    public void setCpaCode(String str) {
        this.d = str;
    }

    public void setDefaultMets(Double d) {
        this.i = d;
    }

    public void setDescInImperial(String str) {
        this.h = str;
    }

    public void setDescInMetric(String str) {
        this.g = str;
    }

    public void setDeviceIcons(List<DeviceIcon> list) {
        this.p = list;
    }

    public void setDvcTitleInImperial(List<String> list) {
        this.t = list;
    }

    public void setDvcTitleInMetric(List<String> list) {
        this.s = list;
    }

    public void setFwActId(int i) {
        this.m = i;
    }

    public void setFwActIdFmt(String str) {
        this.n = str;
    }

    public void setIconUrl(String str) {
        this.o = str;
    }

    public void setInbuilt(Boolean bool) {
        this.e = bool;
    }

    public void setMetrics(List<String> list) {
        this.k = list;
    }

    public void setSeqNumber(Integer num) {
        this.l = num;
    }

    public void setTitleInImperial(String str) {
        this.r = str;
    }

    public void setTitleInMetric(String str) {
        this.q = str;
    }
}
