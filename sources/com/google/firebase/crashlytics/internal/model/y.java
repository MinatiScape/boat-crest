package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class y extends StaticSessionData.OsData {

    /* renamed from: a  reason: collision with root package name */
    public final String f11243a;
    public final String b;
    public final boolean c;

    public y(String str, String str2, boolean z) {
        Objects.requireNonNull(str, "Null osRelease");
        this.f11243a = str;
        Objects.requireNonNull(str2, "Null osCodeName");
        this.b = str2;
        this.c = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StaticSessionData.OsData) {
            StaticSessionData.OsData osData = (StaticSessionData.OsData) obj;
            return this.f11243a.equals(osData.osRelease()) && this.b.equals(osData.osCodeName()) && this.c == osData.isRooted();
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f11243a.hashCode() ^ 1000003) * 1000003) ^ this.b.hashCode()) * 1000003) ^ (this.c ? 1231 : 1237);
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public boolean isRooted() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public String osCodeName() {
        return this.b;
    }

    @Override // com.google.firebase.crashlytics.internal.model.StaticSessionData.OsData
    public String osRelease() {
        return this.f11243a;
    }

    public String toString() {
        return "OsData{osRelease=" + this.f11243a + ", osCodeName=" + this.b + ", isRooted=" + this.c + "}";
    }
}
