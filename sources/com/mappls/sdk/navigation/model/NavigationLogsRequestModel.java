package com.mappls.sdk.navigation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes11.dex */
public class NavigationLogsRequestModel {
    @SerializedName("routeId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f12920a;
    @SerializedName("deviceId")
    @Expose
    private String b;
    @SerializedName("routeIndex")
    @Expose
    private int c;
    @SerializedName("fromNode")
    @Expose
    private long d;
    @SerializedName("toNode")
    @Expose
    private long e;
    @SerializedName("duration_act")
    @Expose
    private long f;
    @SerializedName("duration")
    @Expose
    private double g;
    @SerializedName("timestamp")
    @Expose
    private long h;
    @SerializedName("DoDV")
    @Expose
    private int i;
    @SerializedName("latitude")
    @Expose
    private double j;
    @SerializedName("longitude")
    @Expose
    private double k;
    @SerializedName("successfullySent")
    @Expose
    private Boolean l;
    @SerializedName("node_latitude")
    @Expose
    private double m;
    @SerializedName("node_longitude")
    @Expose
    private double n;
    @SerializedName("snapped_latitude")
    @Expose
    private double o;
    @SerializedName("snapped_longitude")
    @Expose
    private double p;

    public NavigationLogsRequestModel(String str, String str2, int i) {
        this.f12920a = str;
        this.b = str2;
        this.c = i;
    }

    public String getDeviceId() {
        return this.b;
    }

    public int getDoDV() {
        return this.i;
    }

    public double getDuration() {
        return this.g;
    }

    public long getDurationAct() {
        return this.f;
    }

    public long getFromNode() {
        return this.d;
    }

    public double getLatitude() {
        return this.j;
    }

    public double getLongitude() {
        return this.k;
    }

    public double getNodeLatitude() {
        return this.m;
    }

    public double getNodeLongitude() {
        return this.n;
    }

    public String getRouteId() {
        return this.f12920a;
    }

    public int getRouteIndex() {
        return this.c;
    }

    public double getSnappedLatitude() {
        return this.o;
    }

    public double getSnappedLongitude() {
        return this.p;
    }

    public Boolean getSuccessfullySent() {
        return this.l;
    }

    public long getTimestamp() {
        return this.h;
    }

    public long getToNode() {
        return this.e;
    }

    public void setDeviceId(String str) {
        this.b = str;
    }

    public void setDoDV(int i) {
        this.i = i;
    }

    public void setDuration(double d) {
        this.g = d;
    }

    public void setDurationAct(long j) {
        this.f = j;
    }

    public void setFromNode(long j) {
        this.d = j;
    }

    public void setLatitude(double d) {
        this.j = d;
    }

    public void setLongitude(double d) {
        this.k = d;
    }

    public void setNodeLatitude(double d) {
        this.m = d;
    }

    public void setNodeLongitude(double d) {
        this.n = d;
    }

    public void setRouteId(String str) {
        this.f12920a = str;
    }

    public void setRouteIndex(int i) {
        this.c = i;
    }

    public void setSnappedLatitude(double d) {
        this.o = d;
    }

    public void setSnappedLongitude(double d) {
        this.p = d;
    }

    public void setSuccessfullySent(Boolean bool) {
        this.l = bool;
    }

    public void setTimestamp(long j) {
        this.h = j;
    }

    public void setToNode(long j) {
        this.e = j;
    }
}
