package com.google.firebase.ml.vision.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzmb;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes10.dex */
public final class FirebaseVisionPoint {

    /* renamed from: a  reason: collision with root package name */
    public final Float f11443a;
    public final Float b;

    public FirebaseVisionPoint(@NonNull Float f, @NonNull Float f2, @Nullable Float f3) {
        this.f11443a = f;
        this.b = f2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionPoint) {
            FirebaseVisionPoint firebaseVisionPoint = (FirebaseVisionPoint) obj;
            return Objects.equal(this.f11443a, firebaseVisionPoint.f11443a) && Objects.equal(this.b, firebaseVisionPoint.b) && Objects.equal(null, null);
        }
        return false;
    }

    @NonNull
    public final Float getX() {
        return this.f11443a;
    }

    @NonNull
    public final Float getY() {
        return this.b;
    }

    @Nullable
    public final Float getZ() {
        return null;
    }

    public final int hashCode() {
        return Objects.hashCode(this.f11443a, this.b, null);
    }

    public final String toString() {
        return zzmb.zzaz("FirebaseVisionPoint").zzh("x", this.f11443a).zzh(EllipticCurveJsonWebKey.Y_MEMBER_NAME, this.b).zzh("z", null).toString();
    }
}
