package com.google.firebase.ml.vision.face;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzmb;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzwz;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class FirebaseVisionFaceDetectorOptions {
    public static final int ACCURATE = 2;
    public static final int ALL_CLASSIFICATIONS = 2;
    public static final int ALL_CONTOURS = 2;
    public static final int ALL_LANDMARKS = 2;
    public static final int FAST = 1;
    public static final int NO_CLASSIFICATIONS = 1;
    public static final int NO_CONTOURS = 1;
    public static final int NO_LANDMARKS = 1;
    @LandmarkMode

    /* renamed from: a  reason: collision with root package name */
    public final int f11451a;
    @ContourMode
    public final int b;
    @ClassificationMode
    public final int c;
    @PerformanceMode
    public final int d;
    public final boolean e;
    public final float f;

    /* loaded from: classes10.dex */
    public static class Builder {
        @LandmarkMode

        /* renamed from: a  reason: collision with root package name */
        public int f11452a = 1;
        @ContourMode
        public int b = 1;
        @ClassificationMode
        public int c = 1;
        @PerformanceMode
        public int d = 1;
        public boolean e = false;
        public float f = 0.1f;

        @NonNull
        public FirebaseVisionFaceDetectorOptions build() {
            return new FirebaseVisionFaceDetectorOptions(this.f11452a, this.b, this.c, this.d, this.e, this.f);
        }

        @NonNull
        public Builder enableTracking() {
            this.e = true;
            return this;
        }

        @NonNull
        public Builder setClassificationMode(@ClassificationMode int i) {
            this.c = i;
            return this;
        }

        @NonNull
        public Builder setContourMode(@ContourMode int i) {
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setLandmarkMode(@LandmarkMode int i) {
            this.f11452a = i;
            return this;
        }

        @NonNull
        public Builder setMinFaceSize(float f) {
            this.f = f;
            return this;
        }

        @NonNull
        public Builder setPerformanceMode(@PerformanceMode int i) {
            this.d = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ClassificationMode {
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface ContourMode {
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface LandmarkMode {
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface PerformanceMode {
    }

    public FirebaseVisionFaceDetectorOptions(@LandmarkMode int i, @ContourMode int i2, @ClassificationMode int i3, @PerformanceMode int i4, boolean z, float f) {
        this.f11451a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = z;
        this.f = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionFaceDetectorOptions) {
            FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions = (FirebaseVisionFaceDetectorOptions) obj;
            return Float.floatToIntBits(this.f) == Float.floatToIntBits(firebaseVisionFaceDetectorOptions.f) && this.f11451a == firebaseVisionFaceDetectorOptions.f11451a && this.b == firebaseVisionFaceDetectorOptions.b && this.d == firebaseVisionFaceDetectorOptions.d && this.e == firebaseVisionFaceDetectorOptions.e && this.c == firebaseVisionFaceDetectorOptions.c;
        }
        return false;
    }

    @ClassificationMode
    public int getClassificationMode() {
        return this.c;
    }

    @ContourMode
    public int getContourMode() {
        return this.b;
    }

    @LandmarkMode
    public int getLandmarkMode() {
        return this.f11451a;
    }

    public float getMinFaceSize() {
        return this.f;
    }

    @PerformanceMode
    public int getPerformanceMode() {
        return this.d;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Float.floatToIntBits(this.f)), Integer.valueOf(this.f11451a), Integer.valueOf(this.b), Integer.valueOf(this.d), Boolean.valueOf(this.e), Integer.valueOf(this.c));
    }

    public boolean isTrackingEnabled() {
        return this.e;
    }

    public String toString() {
        return zzmb.zzaz("FaceDetectorOptions").zzb("landmarkMode", this.f11451a).zzb("contourMode", this.b).zzb("classificationMode", this.c).zzb("performanceMode", this.d).zza("trackingEnabled", this.e).zza("minFaceSize", this.f).toString();
    }

    public final zzns.zzac zzqs() {
        zzns.zzac.zzd zzdVar;
        zzns.zzac.zzb zzbVar;
        zzns.zzac.zze zzeVar;
        zzns.zzac.zzc zzcVar;
        zzns.zzac.zza zzlx = zzns.zzac.zzlx();
        int i = this.f11451a;
        if (i == 1) {
            zzdVar = zzns.zzac.zzd.NO_LANDMARKS;
        } else if (i != 2) {
            zzdVar = zzns.zzac.zzd.UNKNOWN_LANDMARKS;
        } else {
            zzdVar = zzns.zzac.zzd.ALL_LANDMARKS;
        }
        zzns.zzac.zza zza = zzlx.zza(zzdVar);
        int i2 = this.c;
        if (i2 == 1) {
            zzbVar = zzns.zzac.zzb.NO_CLASSIFICATIONS;
        } else if (i2 != 2) {
            zzbVar = zzns.zzac.zzb.UNKNOWN_CLASSIFICATIONS;
        } else {
            zzbVar = zzns.zzac.zzb.ALL_CLASSIFICATIONS;
        }
        zzns.zzac.zza zza2 = zza.zza(zzbVar);
        int i3 = this.d;
        if (i3 == 1) {
            zzeVar = zzns.zzac.zze.FAST;
        } else if (i3 != 2) {
            zzeVar = zzns.zzac.zze.UNKNOWN_PERFORMANCE;
        } else {
            zzeVar = zzns.zzac.zze.ACCURATE;
        }
        zzns.zzac.zza zza3 = zza2.zza(zzeVar);
        int i4 = this.b;
        if (i4 == 1) {
            zzcVar = zzns.zzac.zzc.NO_CONTOURS;
        } else if (i4 != 2) {
            zzcVar = zzns.zzac.zzc.UNKNOWN_CONTOURS;
        } else {
            zzcVar = zzns.zzac.zzc.ALL_CONTOURS;
        }
        return (zzns.zzac) ((zzwz) zza3.zza(zzcVar).zzaa(isTrackingEnabled()).zzk(this.f).zzvb());
    }
}
