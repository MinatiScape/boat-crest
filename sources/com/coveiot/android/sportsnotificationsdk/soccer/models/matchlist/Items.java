package com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist;

import androidx.core.app.NotificationCompat;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Competition;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Result;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Teams;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Items {
    @SerializedName("commentaryavailable")
    private String A;
    @SerializedName("mid")

    /* renamed from: a  reason: collision with root package name */
    private String f5991a;
    @SerializedName("round")
    private String b;
    @SerializedName("result")
    private Result c;
    @SerializedName("teams")
    private Teams d;
    @SerializedName("periods")
    private JsonElement e;
    @SerializedName("datestart")
    private String f;
    @SerializedName("dateend")
    private String g;
    @SerializedName("timestampstart")
    private String h;
    @SerializedName("timestampend")
    private String i;
    @SerializedName("injurytime")
    private String j;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    private String k;
    @SerializedName("status_str")
    private String l;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String m;
    @SerializedName("gamestate_str")
    private String n;
    @SerializedName("gamestate")
    private String o;
    @SerializedName("pre_squad")
    private String p;
    @SerializedName("verified")
    private String q;
    @SerializedName("periodlength")
    private String r;
    @SerializedName("numberofperiods")
    private String s;
    @SerializedName("attendance")
    private String t;
    @SerializedName("overtimelength")
    private String u;
    @SerializedName("competition")
    private Competition v;
    @SerializedName("venue")
    private JsonElement w;
    @SerializedName("lineupavailable")
    private String x;
    @SerializedName("projectionavailable")
    private String y;
    @SerializedName("eventavailable")
    private String z;

    public String getAttendance() {
        return this.t;
    }

    public String getCommentaryavailable() {
        return this.A;
    }

    public Competition getCompetition() {
        return this.v;
    }

    public String getDateend() {
        return this.g;
    }

    public String getDatestart() {
        return this.f;
    }

    public String getEventavailable() {
        return this.z;
    }

    public String getGamestate() {
        return this.o;
    }

    public String getGamestateStr() {
        return this.n;
    }

    public String getInjurytime() {
        return this.j;
    }

    public String getLineupavailable() {
        return this.x;
    }

    public String getMid() {
        return this.f5991a;
    }

    public String getNumberofperiods() {
        return this.s;
    }

    public String getOvertimelength() {
        return this.u;
    }

    public String getPeriodlength() {
        return this.r;
    }

    public JsonElement getPeriods() {
        return this.e;
    }

    public String getPreSquad() {
        return this.p;
    }

    public String getProjectionavailable() {
        return this.y;
    }

    public Result getResult() {
        return this.c;
    }

    public String getRound() {
        return this.b;
    }

    public String getStatus() {
        return this.m;
    }

    public String getStatusStr() {
        return this.l;
    }

    public Teams getTeams() {
        return this.d;
    }

    public String getTime() {
        return this.k;
    }

    public String getTimestampend() {
        return this.i;
    }

    public String getTimestampstart() {
        return this.h;
    }

    public JsonElement getVenue() {
        return this.w;
    }

    public String getVerified() {
        return this.q;
    }

    public void setAttendance(String str) {
        this.t = str;
    }

    public void setCommentaryavailable(String str) {
        this.A = str;
    }

    public void setCompetition(Competition competition) {
        this.v = competition;
    }

    public void setDateend(String str) {
        this.g = str;
    }

    public void setDatestart(String str) {
        this.f = str;
    }

    public void setEventavailable(String str) {
        this.z = str;
    }

    public void setGamestate(String str) {
        this.o = str;
    }

    public void setGamestateStr(String str) {
        this.n = str;
    }

    public void setInjurytime(String str) {
        this.j = str;
    }

    public void setLineupavailable(String str) {
        this.x = str;
    }

    public void setMid(String str) {
        this.f5991a = str;
    }

    public void setNumberofperiods(String str) {
        this.s = str;
    }

    public void setOvertimelength(String str) {
        this.u = str;
    }

    public void setPeriodlength(String str) {
        this.r = str;
    }

    public void setPeriods(JsonElement jsonElement) {
        this.e = jsonElement;
    }

    public void setPreSquad(String str) {
        this.p = str;
    }

    public void setProjectionavailable(String str) {
        this.y = str;
    }

    public void setResult(Result result) {
        this.c = result;
    }

    public void setRound(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.m = str;
    }

    public void setStatusStr(String str) {
        this.l = str;
    }

    public void setTeams(Teams teams) {
        this.d = teams;
    }

    public void setTime(String str) {
        this.k = str;
    }

    public void setTimestampend(String str) {
        this.i = str;
    }

    public void setTimestampstart(String str) {
        this.h = str;
    }

    public void setVenue(JsonElement jsonElement) {
        this.w = jsonElement;
    }

    public void setVerified(String str) {
        this.q = str;
    }
}
