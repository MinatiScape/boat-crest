package com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
/* loaded from: classes7.dex */
public class Event {
    @SerializedName("pid")

    /* renamed from: a  reason: collision with root package name */
    private String f5986a;
    @SerializedName(SavingTrackHelper.POINT_COL_NAME)
    private String b;
    @SerializedName("type")
    private String c;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    private String d;
    @SerializedName("injurytime")
    private String e;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String f;
    @SerializedName("team")
    private String g;
    @SerializedName("card")
    private String h;
    @SerializedName("player_in")
    private String i;
    @SerializedName("player_in_name")
    private String j;
    @SerializedName("player_out")
    private String k;
    @SerializedName("player_out_name")
    private String l;
    @SerializedName("assists")
    private JsonElement m;
    @SerializedName("goaltypeid")
    private String n;
    @SerializedName("goaltype")
    private String o;

    public JsonElement getAssists() {
        return this.m;
    }

    public String getCard() {
        return this.h;
    }

    public String getGoaltype() {
        return this.o;
    }

    public String getGoaltypeid() {
        return this.n;
    }

    public String getInjurytime() {
        return this.e;
    }

    public String getName() {
        return this.f;
    }

    public String getPid() {
        return this.f5986a;
    }

    public String getPlayerIn() {
        return this.i;
    }

    public String getPlayerInName() {
        return this.j;
    }

    public String getPlayerOut() {
        return this.k;
    }

    public String getPlayerOutName() {
        return this.l;
    }

    public String getPname() {
        return this.b;
    }

    public String getTeam() {
        return this.g;
    }

    public String getTime() {
        return this.d;
    }

    public String getType() {
        return this.c;
    }

    public void setAssists(JsonElement jsonElement) {
        this.m = jsonElement;
    }

    public void setCard(String str) {
        this.h = str;
    }

    public void setGoaltype(String str) {
        this.o = str;
    }

    public void setGoaltypeid(String str) {
        this.n = str;
    }

    public void setInjurytime(String str) {
        this.e = str;
    }

    public void setName(String str) {
        this.f = str;
    }

    public void setPid(String str) {
        this.f5986a = str;
    }

    public void setPlayerIn(String str) {
        this.i = str;
    }

    public void setPlayerInName(String str) {
        this.j = str;
    }

    public void setPlayerOut(String str) {
        this.k = str;
    }

    public void setPlayerOutName(String str) {
        this.l = str;
    }

    public void setPname(String str) {
        this.b = str;
    }

    public void setTeam(String str) {
        this.g = str;
    }

    public void setTime(String str) {
        this.d = str;
    }

    public void setType(String str) {
        this.c = str;
    }
}
