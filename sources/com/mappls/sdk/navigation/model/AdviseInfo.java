package com.mappls.sdk.navigation.model;

import android.location.Location;
import androidx.annotation.Nullable;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.h;
/* loaded from: classes11.dex */
public class AdviseInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f12918a;
    public String b;
    public int c;
    public int d;
    public String e;
    public long f;
    public int j;
    public int k;
    public long l;
    public int m;
    public Object n;
    public Object o;
    public NavLocation p;
    public double q;
    public String r;
    public Double s;
    public boolean g = false;
    public boolean h = false;
    public boolean i = true;
    public boolean t = false;
    public boolean u = false;
    public boolean v = false;

    public Double getCurrentRoadSpeed() {
        return this.s;
    }

    public double getDistanceFromRoute() {
        return this.q;
    }

    public int getDistanceToNextAdvise() {
        return this.c;
    }

    public String getEta() {
        return this.e;
    }

    public long getEtaInSecond() {
        return this.l;
    }

    public Object getInfo() {
        return this.n;
    }

    public int getLeftDistance() {
        return this.d;
    }

    public int getLeftTime() {
        return this.j;
    }

    public int getLeftTimeStep() {
        return this.k;
    }

    @Nullable
    public Location getLocation() {
        Location location = new Location("gps");
        NavLocation navLocation = this.p;
        if (navLocation != null) {
            location.setLatitude(navLocation.getLatitude());
            location.setLongitude(this.p.getLongitude());
            location.setBearing(this.p.getBearing());
            location.setAccuracy(this.p.getAccuracy());
            location.setSpeed(this.p.getSpeed());
        }
        return location;
    }

    public long getManeuverID() {
        return this.f;
    }

    @Nullable
    public NavLocation getNavLocation() {
        return this.p;
    }

    public Object getNextInstructionInfo() {
        return this.o;
    }

    public String getNextInstructionText() {
        return this.r;
    }

    public int getPosition() {
        return this.m;
    }

    public String getShortText() {
        return this.b;
    }

    public String getText() {
        return this.f12918a;
    }

    public boolean isIntermediate() {
        return this.v;
    }

    public boolean isOnRoute() {
        return this.i;
    }

    public boolean isOnTollRoad() {
        return this.u;
    }

    public boolean isOverSpeed() {
        return this.t;
    }

    public boolean isRouteBeingRecalculated() {
        return this.g;
    }

    public boolean isuTurnSuggestion() {
        return this.h;
    }

    public void setCurrentRoadSpeed(Double d) {
        this.s = d;
    }

    public void setDistanceFromRoute(double d) {
        this.q = d;
    }

    public void setDistanceToNextAdvise(int i) {
        this.c = i;
    }

    public void setEta(String str) {
        this.e = str;
    }

    public void setEtaInSecond(long j) {
        this.l = j;
    }

    public void setInfo(Object obj) {
        this.n = obj;
    }

    public void setIntermediate(boolean z) {
        this.v = z;
    }

    public void setLeftDistance(int i) {
        this.d = i;
    }

    public void setLeftTime(int i) {
        this.j = i;
    }

    public void setLeftTimeStep(int i) {
        this.k = i;
    }

    public void setLocation(NavLocation navLocation) {
        this.p = navLocation;
    }

    public void setManeuverID(long j) {
        this.f = j;
    }

    public void setNextInstructionInfo(Object obj) {
        this.o = obj;
    }

    public void setNextInstructionText(String str) {
        this.r = str;
    }

    public void setOnRoute(boolean z) {
        this.i = z;
    }

    public void setOnTollRoad(boolean z) {
        this.u = z;
    }

    public void setOverSpeed(boolean z) {
        this.t = z;
    }

    public void setPosition(int i) {
        this.m = i;
    }

    public void setRouteBeingRecalculated(boolean z) {
        this.g = z;
    }

    public AdviseInfo setShortText(String str) {
        this.b = str;
        return this;
    }

    public void setText(String str) {
        this.f12918a = str;
    }

    public void setuTurnSuggestion(boolean z) {
        this.h = z;
    }

    public String toString() {
        StringBuilder a2 = h.a("AdviseInfo{text='");
        a2.append(this.f12918a);
        a2.append('\'');
        a2.append(", shortText='");
        a2.append(this.b);
        a2.append('\'');
        a2.append(", distanceToNextAdvise=");
        a2.append(this.c);
        a2.append(", leftDistance=");
        a2.append(this.d);
        a2.append(", eta='");
        a2.append(this.e);
        a2.append('\'');
        a2.append(", maneuverID=");
        a2.append(this.f);
        a2.append(", routeBeingRecalculated=");
        a2.append(this.g);
        a2.append(", uTurnSuggestion=");
        a2.append(this.h);
        a2.append(", isOnRoute=");
        a2.append(this.i);
        a2.append(", leftTime=");
        a2.append(this.j);
        a2.append(", leftTimeStep=");
        a2.append(this.k);
        a2.append(", etaInSecond=");
        a2.append(this.l);
        a2.append(", position=");
        a2.append(this.m);
        a2.append(", info=");
        a2.append(this.n);
        a2.append(", nextInstructionInfo=");
        a2.append(this.o);
        a2.append(", location=");
        a2.append(this.p);
        a2.append(", distanceFromRoute=");
        a2.append(this.q);
        a2.append(", nextInstructionText='");
        a2.append(this.r);
        a2.append('\'');
        a2.append(", currentRoadSpeed=");
        a2.append(this.s);
        a2.append(", isOverSpeed=");
        a2.append(this.t);
        a2.append(", isOnTollRoad=");
        a2.append(this.u);
        a2.append(", intermediate=");
        a2.append(this.v);
        a2.append('}');
        return a2.toString();
    }
}
