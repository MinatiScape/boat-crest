package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class w extends StaticSessionData.AppData {

    /* renamed from: a  reason: collision with root package name */
    public final String f11241a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public final String f;

    public w(String str, String str2, String str3, String str4, int i, @Nullable String str5) {
        Objects.requireNonNull(str, "Null appIdentifier");
        this.f11241a = str;
        Objects.requireNonNull(str2, "Null versionCode");
        this.b = str2;
        Objects.requireNonNull(str3, "Null versionName");
        this.c = str3;
        Objects.requireNonNull(str4, "Null installUuid");
        this.d = str4;
        this.e = i;
        this.f = str5;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String appIdentifier() {
        return this.f11241a;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public int deliveryMechanism() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StaticSessionData.AppData) {
            StaticSessionData.AppData appData = (StaticSessionData.AppData) obj;
            if (this.f11241a.equals(appData.appIdentifier()) && this.b.equals(appData.versionCode()) && this.c.equals(appData.versionName()) && this.d.equals(appData.installUuid()) && this.e == appData.deliveryMechanism()) {
                String str = this.f;
                if (str == null) {
                    if (appData.unityVersion() == null) {
                        return true;
                    }
                } else if (str.equals(appData.unityVersion())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((this.f11241a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ this.c.hashCode()) * 1000003) ^ this.d.hashCode()) * 1000003) ^ this.e) * 1000003;
        String str = this.f;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String installUuid() {
        return this.d;
    }

    public String toString() {
        return "AppData{appIdentifier=" + this.f11241a + ", versionCode=" + this.b + ", versionName=" + this.c + ", installUuid=" + this.d + ", deliveryMechanism=" + this.e + ", unityVersion=" + this.f + "}";
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    @Nullable
    public String unityVersion() {
        return this.f;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String versionCode() {
        return this.b;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.AppData
    public String versionName() {
        return this.c;
    }
}
