package com.coveiot.android.sportsnotificationsdk.soccer.models.common;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
/* loaded from: classes7.dex */
public class Competition {
    @SerializedName("cid")

    /* renamed from: a  reason: collision with root package name */
    private String f5979a;
    @SerializedName("cname")
    private String b;
    @SerializedName("startdate")
    private String c;
    @SerializedName("enddate")
    private String d;
    @SerializedName("startdatetimestamp")
    private String e;
    @SerializedName("endtdatetimestamp")
    private String f;
    @SerializedName("year")
    private String g;
    @SerializedName(SavingTrackHelper.POINT_COL_CATEGORY)
    private String h;
    @SerializedName("tournament_id")
    private String i;
    @SerializedName("category_id")
    private String j;
    @SerializedName("ioc")
    private String k;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String l;
    @SerializedName("status_str")
    private String m;
    @SerializedName("logo")
    private String n;

    public String getCategory() {
        return this.h;
    }

    public String getCategoryId() {
        return this.j;
    }

    public String getCid() {
        return this.f5979a;
    }

    public String getCname() {
        return this.b;
    }

    public String getEnddate() {
        return this.d;
    }

    public String getEndtdatetimestamp() {
        return this.f;
    }

    public String getIoc() {
        return this.k;
    }

    public String getLogo() {
        return this.n;
    }

    public String getStartdate() {
        return this.c;
    }

    public String getStartdatetimestamp() {
        return this.e;
    }

    public String getStatus() {
        return this.l;
    }

    public String getStatusStr() {
        return this.m;
    }

    public String getTournamentId() {
        return this.i;
    }

    public String getYear() {
        return this.g;
    }

    public void setCategory(String str) {
        this.h = str;
    }

    public void setCategoryId(String str) {
        this.j = str;
    }

    public void setCid(String str) {
        this.f5979a = str;
    }

    public void setCname(String str) {
        this.b = str;
    }

    public void setEnddate(String str) {
        this.d = str;
    }

    public void setEndtdatetimestamp(String str) {
        this.f = str;
    }

    public void setIoc(String str) {
        this.k = str;
    }

    public void setLogo(String str) {
        this.n = str;
    }

    public void setStartdate(String str) {
        this.c = str;
    }

    public void setStartdatetimestamp(String str) {
        this.e = str;
    }

    public void setStatus(String str) {
        this.l = str;
    }

    public void setStatusStr(String str) {
        this.m = str;
    }

    public void setTournamentId(String str) {
        this.i = str;
    }

    public void setYear(String str) {
        this.g = str;
    }
}
