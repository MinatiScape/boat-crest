package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.android.gms.internal.firebase_ml.zzwz;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionOnDeviceImageLabelerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final float f11459a;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public float f11460a = 0.5f;

        @NonNull
        public FirebaseVisionOnDeviceImageLabelerOptions build() {
            return new FirebaseVisionOnDeviceImageLabelerOptions(this.f11460a);
        }

        @NonNull
        public Builder setConfidenceThreshold(float f) {
            Preconditions.checkArgument(Float.compare(f, 0.0f) >= 0 && Float.compare(f, 1.0f) <= 0, "Confidence Threshold should be in range [0.0f, 1.0f].");
            this.f11460a = f;
            return this;
        }
    }

    public FirebaseVisionOnDeviceImageLabelerOptions(float f) {
        this.f11459a = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof FirebaseVisionOnDeviceImageLabelerOptions) && this.f11459a == ((FirebaseVisionOnDeviceImageLabelerOptions) obj).f11459a;
    }

    public float getConfidenceThreshold() {
        return this.f11459a;
    }

    public int hashCode() {
        return Objects.hashCode(Float.valueOf(this.f11459a));
    }

    public final zzns.zzap zzqv() {
        return (zzns.zzap) ((zzwz) zzns.zzap.zzmz().zzp(this.f11459a).zzvb());
    }
}
