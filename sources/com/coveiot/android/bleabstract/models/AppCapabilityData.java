package com.coveiot.android.bleabstract.models;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class AppCapabilityData implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3398a = true;
    public boolean b = true;
    public boolean c = true;
    public boolean d = true;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    public boolean j = true;
    public boolean k = true;
    public boolean l = true;
    public boolean m = true;
    public boolean n = true;

    public boolean isAADSupported() {
        return this.f3398a;
    }

    public boolean isBatterySaverConfigSupported() {
        return this.b;
    }

    public boolean isDistanceAndCalorieDataFromBandSupported() {
        return this.h;
    }

    public boolean isExtendedAppNotificationSupported() {
        return this.g;
    }

    public boolean isFindWatchSupported() {
        return this.i;
    }

    public boolean isNavigationSupported() {
        return this.k;
    }

    public boolean isPersonalizedSupported() {
        return this.j;
    }

    public boolean isQRCodeSupported() {
        return this.m;
    }

    public boolean isRespiratoryRateSupported() {
        return this.e;
    }

    public boolean isSOSSupported() {
        return this.n;
    }

    public boolean isScheduledLiftWristToViewSupported() {
        return this.f;
    }

    public boolean isSensAISupported() {
        return this.d;
    }

    public boolean isSilentModeSupported() {
        return this.c;
    }

    public boolean isSmartAlertSupported() {
        return this.l;
    }

    public void setAADSupported(boolean z) {
        this.f3398a = z;
    }

    public void setBatterySaverConfigSupported(boolean z) {
        this.b = z;
    }

    public void setDistanceAndCalorieDataFromBandSupported(boolean z) {
        this.h = z;
    }

    public void setExtendedAppNotificationSupported(boolean z) {
        this.g = z;
    }

    public void setFindWatchSupported(boolean z) {
        this.i = z;
    }

    public void setNavigationSupported(boolean z) {
        this.k = z;
    }

    public void setPersonalizedSupported(boolean z) {
        this.j = z;
    }

    public void setQRCodeSupported(boolean z) {
        this.m = z;
    }

    public void setRespiratoryRateSupported(boolean z) {
        this.e = z;
    }

    public void setSOSSupported(boolean z) {
        this.n = z;
    }

    public void setScheduledLiftWristToViewSupported(boolean z) {
        this.f = z;
    }

    public void setSensAISupported(boolean z) {
        this.d = z;
    }

    public void setSilentModeSupported(boolean z) {
        this.c = z;
    }

    public void setSmartAlertSupported(boolean z) {
        this.l = z;
    }
}
