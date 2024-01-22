package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.vision.Frame;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
/* loaded from: classes7.dex */
public final class zzsf implements zzpy {
    public final Frame zzbrv;
    public final FirebaseVisionImage zzbsk;

    public zzsf(@NonNull FirebaseVisionImage firebaseVisionImage, @NonNull Frame frame) {
        this.zzbsk = firebaseVisionImage;
        this.zzbrv = frame;
    }
}
