package com.google.firebase.ml.vision.label;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzkv;
import com.google.android.gms.internal.firebase_ml.zzms;
import com.google.android.gms.internal.firebase_ml.zzrq;
import com.google.android.gms.vision.label.ImageLabel;
import com.google.firebase.ml.vision.automl.internal.zzl;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionImageLabel {

    /* renamed from: a  reason: collision with root package name */
    public final String f11456a;
    public final String b;
    public final float c;

    public FirebaseVisionImageLabel(@NonNull ImageLabel imageLabel) {
        this(imageLabel.getLabel(), imageLabel.getConfidence(), imageLabel.getMid());
    }

    @Nullable
    public static FirebaseVisionImageLabel zza(@Nullable zzkv zzkvVar) {
        if (zzkvVar == null) {
            return null;
        }
        return new FirebaseVisionImageLabel(zzkvVar.getDescription(), zzrq.zza(zzkvVar.zzir()), zzkvVar.getMid());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionImageLabel) {
            FirebaseVisionImageLabel firebaseVisionImageLabel = (FirebaseVisionImageLabel) obj;
            return Objects.equal(this.f11456a, firebaseVisionImageLabel.getEntityId()) && Objects.equal(this.b, firebaseVisionImageLabel.getText()) && Float.compare(this.c, firebaseVisionImageLabel.getConfidence()) == 0;
        }
        return false;
    }

    public float getConfidence() {
        return this.c;
    }

    @Nullable
    public String getEntityId() {
        return this.f11456a;
    }

    @NonNull
    public String getText() {
        return this.b;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11456a, this.b, Float.valueOf(this.c));
    }

    @VisibleForTesting
    public FirebaseVisionImageLabel(@Nullable String str, float f, @Nullable String str2) {
        this.b = zzms.zzbb(str);
        this.f11456a = str2;
        if (Float.compare(f, 0.0f) < 0) {
            f = 0.0f;
        } else if (Float.compare(f, 1.0f) > 0) {
            f = 1.0f;
        }
        this.c = f;
    }

    public static FirebaseVisionImageLabel zza(@NonNull zzl zzlVar) {
        Preconditions.checkNotNull(zzlVar, "Returned image label parcel can not be null");
        return new FirebaseVisionImageLabel(zzlVar.text, zzlVar.zzbpv, zzlVar.zzbpu);
    }
}
