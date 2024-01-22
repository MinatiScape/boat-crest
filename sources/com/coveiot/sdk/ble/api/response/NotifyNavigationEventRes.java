package com.coveiot.sdk.ble.api.response;

import androidx.annotation.NonNull;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class NotifyNavigationEventRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f7556a;
    public int b;
    public int c;

    public NotifyNavigationEventRes(int i, int i2, int i3) {
        this.f7556a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getEvent() {
        return this.f7556a;
    }

    public int getMode() {
        return this.c;
    }

    public int getPlaceId() {
        return this.b;
    }

    public void setEvent(int i) {
        this.f7556a = i;
    }

    public void setMode(int i) {
        this.c = i;
    }

    public void setPlaceId(int i) {
        this.b = i;
    }

    @NonNull
    public String toString() {
        return "NotifyNavigationEventRes{event=" + this.f7556a + " placeId=" + this.b + " mode=" + this.c + '}';
    }

    public NotifyNavigationEventRes(int i) {
        this.f7556a = i;
    }
}
