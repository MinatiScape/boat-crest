package com.google.firebase.ml.vision.cloud.landmark;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_ml.zzkv;
import com.google.android.gms.internal.firebase_ml.zzlc;
import com.google.android.gms.internal.firebase_ml.zzms;
import com.google.android.gms.internal.firebase_ml.zzrq;
import com.google.firebase.ml.vision.common.FirebaseVisionLatLng;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionCloudLandmark {

    /* renamed from: a  reason: collision with root package name */
    public final String f11438a;
    public final Rect b;
    public final float c;
    public final String d;
    public final List<FirebaseVisionLatLng> e;

    public FirebaseVisionCloudLandmark(@Nullable String str, float f, @Nullable Rect rect, @Nullable String str2, @NonNull List<FirebaseVisionLatLng> list) {
        this.b = rect;
        this.f11438a = zzms.zzbb(str);
        this.d = zzms.zzbb(str2);
        this.e = list;
        if (Float.compare(f, 0.0f) < 0) {
            f = 0.0f;
        } else if (Float.compare(f, 1.0f) > 0) {
            f = 1.0f;
        }
        this.c = f;
    }

    @Nullable
    public static FirebaseVisionCloudLandmark a(@Nullable zzkv zzkvVar, float f) {
        ArrayList arrayList;
        if (zzkvVar == null) {
            return null;
        }
        float zza = zzrq.zza(zzkvVar.zzir());
        Rect zza2 = zzrq.zza(zzkvVar.zziq(), f);
        String description = zzkvVar.getDescription();
        String mid = zzkvVar.getMid();
        List<zzlc> locations = zzkvVar.getLocations();
        if (locations == null) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (zzlc zzlcVar : locations) {
                if (zzlcVar.zziu() != null && zzlcVar.zziu().zzis() != null && zzlcVar.zziu().zzit() != null) {
                    arrayList2.add(new FirebaseVisionLatLng(zzlcVar.zziu().zzis().doubleValue(), zzlcVar.zziu().zzit().doubleValue()));
                }
            }
            arrayList = arrayList2;
        }
        return new FirebaseVisionCloudLandmark(description, zza, zza2, mid, arrayList);
    }

    @Nullable
    public Rect getBoundingBox() {
        return this.b;
    }

    public float getConfidence() {
        return this.c;
    }

    @NonNull
    public String getEntityId() {
        return this.d;
    }

    @NonNull
    public String getLandmark() {
        return this.f11438a;
    }

    @NonNull
    public List<FirebaseVisionLatLng> getLocations() {
        return this.e;
    }
}
