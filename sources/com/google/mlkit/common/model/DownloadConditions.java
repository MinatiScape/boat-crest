package com.google.mlkit.common.model;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes10.dex */
public class DownloadConditions {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11575a;
    public final boolean b;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11576a = false;
        public boolean b = false;

        @NonNull
        public DownloadConditions build() {
            return new DownloadConditions(this.f11576a, this.b, null);
        }

        @NonNull
        @RequiresApi(24)
        @TargetApi(24)
        public Builder requireCharging() {
            this.f11576a = true;
            return this;
        }

        @NonNull
        public Builder requireWifi() {
            this.b = true;
            return this;
        }
    }

    public /* synthetic */ DownloadConditions(boolean z, boolean z2, zzb zzbVar) {
        this.f11575a = z;
        this.b = z2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DownloadConditions) {
            DownloadConditions downloadConditions = (DownloadConditions) obj;
            return this.f11575a == downloadConditions.f11575a && this.b == downloadConditions.b;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.f11575a), Boolean.valueOf(this.b));
    }

    public boolean isChargingRequired() {
        return this.f11575a;
    }

    public boolean isWifiRequired() {
        return this.b;
    }
}
