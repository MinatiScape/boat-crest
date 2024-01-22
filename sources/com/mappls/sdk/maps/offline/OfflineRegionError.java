package com.mappls.sdk.maps.offline;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public class OfflineRegionError {
    public static final String REASON_CONNECTION = "REASON_CONNECTION";
    public static final String REASON_NOT_FOUND = "REASON_NOT_FOUND";
    public static final String REASON_OTHER = "REASON_OTHER";
    public static final String REASON_SERVER = "REASON_SERVER";
    public static final String REASON_SUCCESS = "REASON_SUCCESS";
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final String f12813a;
    @NonNull
    public final String b;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ErrorReason {
    }

    @Keep
    private OfflineRegionError(@NonNull String str, @NonNull String str2) {
        this.f12813a = str;
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OfflineRegionError offlineRegionError = (OfflineRegionError) obj;
        if (this.f12813a.equals(offlineRegionError.f12813a)) {
            return this.b.equals(offlineRegionError.b);
        }
        return false;
    }

    @NonNull
    public String getMessage() {
        return this.b;
    }

    @NonNull
    public String getReason() {
        return this.f12813a;
    }

    public int hashCode() {
        return (this.f12813a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "OfflineRegionError{reason='" + this.f12813a + "', message='" + this.b + "'}";
    }
}
