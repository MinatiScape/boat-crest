package com.google.firebase.ml.vision.objects;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class FirebaseVisionObjectDetectorOptions {
    public static final int SINGLE_IMAGE_MODE = 2;
    public static final int STREAM_MODE = 1;

    /* renamed from: a  reason: collision with root package name */
    public final int f11463a;
    public final boolean b;
    public final boolean c;

    /* loaded from: classes10.dex */
    public static class Builder {
        @DetectorMode

        /* renamed from: a  reason: collision with root package name */
        public int f11464a = 1;
        public boolean b = false;
        public boolean c = false;

        @NonNull
        public FirebaseVisionObjectDetectorOptions build() {
            return new FirebaseVisionObjectDetectorOptions(this.f11464a, this.b, this.c);
        }

        @NonNull
        public Builder enableClassification() {
            this.c = true;
            return this;
        }

        @NonNull
        public Builder enableMultipleObjects() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setDetectorMode(@DetectorMode int i) {
            this.f11464a = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface DetectorMode {
    }

    public FirebaseVisionObjectDetectorOptions(@DetectorMode int i, boolean z, boolean z2) {
        this.f11463a = i;
        this.b = z;
        this.c = z2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionObjectDetectorOptions) {
            FirebaseVisionObjectDetectorOptions firebaseVisionObjectDetectorOptions = (FirebaseVisionObjectDetectorOptions) obj;
            return firebaseVisionObjectDetectorOptions.f11463a == this.f11463a && firebaseVisionObjectDetectorOptions.c == this.c && firebaseVisionObjectDetectorOptions.b == this.b;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f11463a), Boolean.valueOf(this.c), Boolean.valueOf(this.b));
    }

    @DetectorMode
    public final int zzqw() {
        return this.f11463a;
    }

    public final boolean zzqx() {
        return this.c;
    }

    public final boolean zzqy() {
        return this.b;
    }
}
