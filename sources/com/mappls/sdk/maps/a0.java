package com.mappls.sdk.maps;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.annotations.Annotation;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a0 implements b0 {

    /* renamed from: a  reason: collision with root package name */
    public final s f12665a;
    public final LongSparseArray<Annotation> b;

    public a0(s sVar, LongSparseArray<Annotation> longSparseArray) {
        this.f12665a = sVar;
        this.b = longSparseArray;
    }

    @NonNull
    public final List<Annotation> a(long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            Annotation annotation = this.b.get(j);
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.b0
    @NonNull
    public List<Annotation> b(@NonNull RectF rectF) {
        return a(this.f12665a.a0(this.f12665a.H(rectF)));
    }
}
