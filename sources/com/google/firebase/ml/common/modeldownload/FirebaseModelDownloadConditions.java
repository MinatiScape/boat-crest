package com.google.firebase.ml.common.modeldownload;

import android.annotation.TargetApi;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.gms.common.internal.Objects;
/* loaded from: classes10.dex */
public class FirebaseModelDownloadConditions {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11403a;
    public final boolean b;
    public final boolean c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f11404a = false;
        public boolean b = false;
        public boolean c = false;

        @NonNull
        public FirebaseModelDownloadConditions build() {
            return new FirebaseModelDownloadConditions(this.f11404a, this.b, this.c);
        }

        @NonNull
        @RequiresApi(24)
        @TargetApi(24)
        public Builder requireCharging() {
            this.f11404a = true;
            return this;
        }

        @NonNull
        @RequiresApi(24)
        @TargetApi(24)
        public Builder requireDeviceIdle() {
            this.c = true;
            return this;
        }

        @NonNull
        public Builder requireWifi() {
            this.b = true;
            return this;
        }
    }

    public FirebaseModelDownloadConditions(boolean z, boolean z2, boolean z3) {
        this.f11403a = z;
        this.b = z2;
        this.c = z3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseModelDownloadConditions) {
            FirebaseModelDownloadConditions firebaseModelDownloadConditions = (FirebaseModelDownloadConditions) obj;
            return this.f11403a == firebaseModelDownloadConditions.f11403a && this.c == firebaseModelDownloadConditions.c && this.b == firebaseModelDownloadConditions.b;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.f11403a), Boolean.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public boolean isChargingRequired() {
        return this.f11403a;
    }

    public boolean isDeviceIdleRequired() {
        return this.c;
    }

    public boolean isWifiRequired() {
        return this.b;
    }
}
