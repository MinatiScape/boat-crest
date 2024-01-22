package com.google.firebase.crashlytics.internal.model;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes10.dex */
public abstract class StaticSessionData {

    @AutoValue
    /* loaded from: classes10.dex */
    public static abstract class AppData {
        public static AppData create(String str, String str2, String str3, String str4, int i, @Nullable String str5) {
            return new w(str, str2, str3, str4, i, str5);
        }

        public abstract String appIdentifier();

        public abstract int deliveryMechanism();

        public abstract String installUuid();

        @Nullable
        public abstract String unityVersion();

        public abstract String versionCode();

        public abstract String versionName();
    }

    @AutoValue
    /* loaded from: classes10.dex */
    public static abstract class DeviceData {
        public static DeviceData create(int i, String str, int i2, long j, long j2, boolean z, int i3, String str2, String str3) {
            return new x(i, str, i2, j, j2, z, i3, str2, str3);
        }

        public abstract int arch();

        public abstract int availableProcessors();

        public abstract long diskSpace();

        public abstract boolean isEmulator();

        public abstract String manufacturer();

        public abstract String model();

        public abstract String modelClass();

        public abstract int state();

        public abstract long totalRam();
    }

    @AutoValue
    /* loaded from: classes10.dex */
    public static abstract class OsData {
        public static OsData create(String str, String str2, boolean z) {
            return new y(str, str2, z);
        }

        public abstract boolean isRooted();

        public abstract String osCodeName();

        public abstract String osRelease();
    }

    public static StaticSessionData create(AppData appData, OsData osData, DeviceData deviceData) {
        return new v(appData, osData, deviceData);
    }

    public abstract AppData appData();

    public abstract DeviceData deviceData();

    public abstract OsData osData();
}
