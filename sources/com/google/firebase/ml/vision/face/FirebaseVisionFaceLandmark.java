package com.google.firebase.ml.vision.face;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzmb;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes10.dex */
public class FirebaseVisionFaceLandmark {
    public static final int LEFT_CHEEK = 1;
    public static final int LEFT_EAR = 3;
    public static final int LEFT_EYE = 4;
    public static final int MOUTH_BOTTOM = 0;
    public static final int MOUTH_LEFT = 5;
    public static final int MOUTH_RIGHT = 11;
    public static final int NOSE_BASE = 6;
    public static final int RIGHT_CHEEK = 7;
    public static final int RIGHT_EAR = 9;
    public static final int RIGHT_EYE = 10;

    /* renamed from: a  reason: collision with root package name */
    public final int f11453a;
    public final FirebaseVisionPoint b;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface LandmarkType {
    }

    public FirebaseVisionFaceLandmark(@LandmarkType int i, @NonNull FirebaseVisionPoint firebaseVisionPoint) {
        this.f11453a = i;
        this.b = firebaseVisionPoint;
    }

    @LandmarkType
    public int getLandmarkType() {
        return this.f11453a;
    }

    @NonNull
    public FirebaseVisionPoint getPosition() {
        return this.b;
    }

    public String toString() {
        return zzmb.zzaz("FirebaseVisionFaceLandmark").zzb("type", this.f11453a).zzh(DeviceKey.position, this.b).toString();
    }
}
