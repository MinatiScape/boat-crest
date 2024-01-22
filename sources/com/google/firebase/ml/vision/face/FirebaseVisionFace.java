package com.google.firebase.ml.vision.face;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_ml.zzmb;
import com.google.android.gms.internal.firebase_ml.zzmd;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionFace {
    public static final int INVALID_ID = -1;
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;

    /* renamed from: a  reason: collision with root package name */
    public final Rect f11449a;
    public int b;
    public float c;
    public float d;
    public float e;
    public final float f;
    public final float g;
    public final SparseArray<FirebaseVisionFaceLandmark> h = new SparseArray<>();
    public final SparseArray<FirebaseVisionFaceContour> i = new SparseArray<>();

    public FirebaseVisionFace(@NonNull Face face) {
        int i;
        PointF position = face.getPosition();
        float f = position.x;
        this.f11449a = new Rect((int) f, (int) position.y, (int) (f + face.getWidth()), (int) (position.y + face.getHeight()));
        this.b = face.getId();
        for (Landmark landmark : face.getLandmarks()) {
            if (a(landmark.getType()) && landmark.getPosition() != null) {
                this.h.put(landmark.getType(), new FirebaseVisionFaceLandmark(landmark.getType(), new FirebaseVisionPoint(Float.valueOf(landmark.getPosition().x), Float.valueOf(landmark.getPosition().y), null)));
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Contour> it = face.getContours().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                this.i.put(1, new FirebaseVisionFaceContour(1, arrayList));
                this.f = face.getEulerY();
                this.g = face.getEulerZ();
                this.e = face.getIsSmilingProbability();
                this.d = face.getIsLeftEyeOpenProbability();
                this.c = face.getIsRightEyeOpenProbability();
                return;
            }
            Contour next = it.next();
            switch (next.getType()) {
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 3;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 5;
                    break;
                case 5:
                    i = 6;
                    break;
                case 6:
                    i = 7;
                    break;
                case 7:
                    i = 8;
                    break;
                case 8:
                    i = 9;
                    break;
                case 9:
                    i = 10;
                    break;
                case 10:
                    i = 11;
                    break;
                case 11:
                    i = 12;
                    break;
                case 12:
                    i = 13;
                    break;
                case 13:
                    i = 14;
                    break;
                default:
                    i = -1;
                    break;
            }
            if ((i > 14 || i <= 0) ? false : z) {
                PointF[] positions = next.getPositions();
                ArrayList arrayList2 = new ArrayList();
                if (positions != null) {
                    for (PointF pointF : positions) {
                        arrayList2.add(new FirebaseVisionPoint(Float.valueOf(pointF.x), Float.valueOf(pointF.y), null));
                    }
                    this.i.put(i, new FirebaseVisionFaceContour(i, arrayList2));
                    arrayList.addAll(arrayList2);
                }
            }
        }
    }

    public static boolean a(@FirebaseVisionFaceLandmark.LandmarkType int i) {
        return i == 0 || i == 1 || i == 7 || i == 3 || i == 9 || i == 4 || i == 10 || i == 5 || i == 11 || i == 6;
    }

    @NonNull
    public Rect getBoundingBox() {
        return this.f11449a;
    }

    @NonNull
    public FirebaseVisionFaceContour getContour(@FirebaseVisionFaceContour.ContourType int i) {
        FirebaseVisionFaceContour firebaseVisionFaceContour = this.i.get(i);
        return firebaseVisionFaceContour != null ? firebaseVisionFaceContour : new FirebaseVisionFaceContour(i, new ArrayList());
    }

    public float getHeadEulerAngleY() {
        return this.f;
    }

    public float getHeadEulerAngleZ() {
        return this.g;
    }

    @Nullable
    public FirebaseVisionFaceLandmark getLandmark(@FirebaseVisionFaceLandmark.LandmarkType int i) {
        return this.h.get(i);
    }

    public float getLeftEyeOpenProbability() {
        return this.d;
    }

    public float getRightEyeOpenProbability() {
        return this.c;
    }

    public float getSmilingProbability() {
        return this.e;
    }

    public int getTrackingId() {
        return this.b;
    }

    public String toString() {
        zzmd zza = zzmb.zzaz("FirebaseVisionFace").zzh("boundingBox", this.f11449a).zzb("trackingId", this.b).zza("rightEyeOpenProbability", this.c).zza("leftEyeOpenProbability", this.d).zza("smileProbability", this.e).zza("eulerY", this.f).zza("eulerZ", this.g);
        zzmd zzaz = zzmb.zzaz("Landmarks");
        for (int i = 0; i <= 11; i++) {
            if (a(i)) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("landmark_");
                sb.append(i);
                zzaz.zzh(sb.toString(), getLandmark(i));
            }
        }
        zza.zzh("landmarks", zzaz.toString());
        zzmd zzaz2 = zzmb.zzaz("Contours");
        for (int i2 = 1; i2 <= 14; i2++) {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Contour_");
            sb2.append(i2);
            zzaz2.zzh(sb2.toString(), getContour(i2));
        }
        zza.zzh("contours", zzaz2.toString());
        return zza.toString();
    }

    public final void zza(SparseArray<FirebaseVisionFaceContour> sparseArray) {
        this.i.clear();
        for (int i = 0; i < sparseArray.size(); i++) {
            this.i.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
        }
    }

    public final void zzbi(int i) {
        this.b = -1;
    }

    public final SparseArray<FirebaseVisionFaceContour> zzqr() {
        return this.i;
    }
}
