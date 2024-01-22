package com.coveiot.covepreferences.data;

import java.util.Objects;
/* loaded from: classes8.dex */
public class AppNotificationData {

    /* renamed from: a  reason: collision with root package name */
    public String f7008a;
    public String b;
    public boolean c;
    public boolean d;

    public AppNotificationData() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((AppNotificationData) obj).b);
    }

    public String getAppName() {
        return this.f7008a;
    }

    public boolean getChecked() {
        return this.c;
    }

    public String getPackageName() {
        return this.b;
    }

    public int hashCode() {
        return Objects.hash(this.b);
    }

    public boolean isSmartAlertSupported() {
        return this.d;
    }

    public void setAppName(String str) {
        this.f7008a = str;
    }

    public void setChecked(boolean z) {
        this.c = z;
    }

    public void setPackageName(String str) {
        this.b = str;
    }

    public void setSmartAlertSupported(boolean z) {
        this.d = z;
    }

    public AppNotificationData(String str, String str2, boolean z, boolean z2) {
        this.f7008a = str;
        this.b = str2;
        this.c = z;
        this.d = z2;
    }
}
