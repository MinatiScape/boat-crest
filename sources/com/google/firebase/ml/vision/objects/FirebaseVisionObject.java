package com.google.firebase.ml.vision.objects;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.ml.vision.objects.internal.zzj;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes10.dex */
public class FirebaseVisionObject {
    public static final int CATEGORY_FASHION_GOOD = 2;
    public static final int CATEGORY_FOOD = 3;
    public static final int CATEGORY_HOME_GOOD = 1;
    public static final int CATEGORY_PLACE = 4;
    public static final int CATEGORY_PLANT = 5;
    public static final int CATEGORY_UNKNOWN = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Rect f11462a;
    public final Integer b;
    public final Float c;
    @Category
    public final int d;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface Category {
    }

    public FirebaseVisionObject(@NonNull zzj zzjVar) {
        Preconditions.checkNotNull(zzjVar, "Returned Object Detector Parcel can not be null");
        int[] iArr = zzjVar.zzbur;
        Preconditions.checkArgument(iArr.length == 4);
        this.f11462a = new Rect(iArr[0], iArr[1], iArr[2], iArr[3]);
        this.b = zzjVar.zzbud;
        this.c = zzjVar.confidence;
        this.d = zzjVar.category;
    }

    @NonNull
    public Rect getBoundingBox() {
        return this.f11462a;
    }

    @Category
    public int getClassificationCategory() {
        return this.d;
    }

    @Nullable
    public Float getClassificationConfidence() {
        return this.c;
    }

    @Nullable
    public Integer getTrackingId() {
        return this.b;
    }
}
