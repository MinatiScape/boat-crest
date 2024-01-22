package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class v extends StaticSessionData {

    /* renamed from: a  reason: collision with root package name */
    public final StaticSessionData.AppData f11240a;
    public final StaticSessionData.OsData b;
    public final StaticSessionData.DeviceData c;

    public v(StaticSessionData.AppData appData, StaticSessionData.OsData osData, StaticSessionData.DeviceData deviceData) {
        Objects.requireNonNull(appData, "Null appData");
        this.f11240a = appData;
        Objects.requireNonNull(osData, "Null osData");
        this.b = osData;
        Objects.requireNonNull(deviceData, "Null deviceData");
        this.c = deviceData;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.AppData appData() {
        return this.f11240a;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.DeviceData deviceData() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StaticSessionData) {
            StaticSessionData staticSessionData = (StaticSessionData) obj;
            return this.f11240a.equals(staticSessionData.appData()) && this.b.equals(staticSessionData.osData()) && this.c.equals(staticSessionData.deviceData());
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f11240a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode();
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData
    public StaticSessionData.OsData osData() {
        return this.b;
    }

    public String toString() {
        return "StaticSessionData{appData=" + this.f11240a + ", osData=" + this.b + ", deviceData=" + this.c + "}";
    }
}
