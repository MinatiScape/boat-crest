package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.annotations.Annotation;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    public final s f12664a;
    public final LongSparseArray<Annotation> b;

    public a(s sVar, LongSparseArray<Annotation> longSparseArray) {
        this.f12664a = sVar;
        this.b = longSparseArray;
    }

    @Override // com.mappls.sdk.maps.c
    @NonNull
    public List<Annotation> a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.b;
            arrayList.add(longSparseArray.get(longSparseArray.keyAt(i)));
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.c
    public void b(@NonNull List<? extends Annotation> list) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = list.get(i).getId();
        }
        g(jArr);
        for (int i2 = 0; i2 < size; i2++) {
            this.b.remove(jArr[i2]);
        }
    }

    @Override // com.mappls.sdk.maps.c
    public Annotation c(long j) {
        return this.b.get(j);
    }

    @Override // com.mappls.sdk.maps.c
    public void d(@NonNull Annotation annotation) {
        e(annotation.getId());
    }

    @Override // com.mappls.sdk.maps.c
    public void e(long j) {
        s sVar = this.f12664a;
        if (sVar != null) {
            sVar.i(j);
        }
        this.b.remove(j);
    }

    @Override // com.mappls.sdk.maps.c
    public void f() {
        int size = this.b.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = this.b.keyAt(i);
        }
        g(jArr);
        this.b.clear();
    }

    public final void g(long[] jArr) {
        s sVar = this.f12664a;
        if (sVar != null) {
            sVar.O(jArr);
        }
    }
}
