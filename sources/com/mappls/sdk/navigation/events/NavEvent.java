package com.mappls.sdk.navigation.events;

import android.content.Context;
import com.mappls.sdk.navigation.data.LocationPoint;
import com.mappls.sdk.navigation.data.a;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
/* loaded from: classes11.dex */
public class NavEvent implements LocationPoint {

    /* renamed from: a  reason: collision with root package name */
    public String f12897a;
    public double b;
    public double c;
    public boolean d;
    public boolean e;
    public Long f;
    public double g;
    public ReportDetails h;

    public NavEvent() {
    }

    public NavEvent(String str, double d, double d2, boolean z, boolean z2) {
        this.f12897a = str;
        this.b = d;
        this.c = d2;
        this.d = z;
        this.e = z2;
    }

    public NavEvent(String str, double d, double d2, boolean z, boolean z2, Long l) {
        this.f12897a = str;
        this.b = d;
        this.c = d2;
        this.d = z;
        this.e = z2;
        this.f = l;
    }

    public NavEvent(String str, double d, double d2, boolean z, boolean z2, Long l, ReportDetails reportDetails) {
        this.f12897a = str;
        this.b = d;
        this.c = d2;
        this.d = z;
        this.e = z2;
        this.f = l;
        this.h = reportDetails;
    }

    public double getDistanceLeft() {
        return this.g;
    }

    public Long getIndex() {
        return this.f;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public double getLatitude() {
        return this.b;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public double getLongitude() {
        return this.c;
    }

    public String getName() {
        return this.f12897a;
    }

    @Override // com.mappls.sdk.navigation.data.LocationPoint
    public a getPointDescription(Context context) {
        return new a("events", this.f12897a);
    }

    public ReportDetails getReportDetails() {
        return this.h;
    }

    public boolean isShouldSpeak() {
        return this.d;
    }

    public boolean isVisible() {
        return this.e;
    }

    public void setDistanceLeft(double d) {
        this.g = d;
    }

    public void setIndex(Long l) {
        this.f = l;
    }

    public void setLatitude(double d) {
        this.b = d;
    }

    public void setLongitude(double d) {
        this.c = d;
    }

    public void setName(String str) {
        this.f12897a = str;
    }

    public void setReportDetails(ReportDetails reportDetails) {
        this.h = reportDetails;
    }

    public void setShouldSpeak(boolean z) {
        this.d = z;
    }

    public void setVisibility(boolean z) {
        this.e = z;
    }
}
