package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.annotations.Annotation;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.PolygonOptions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class v implements w {

    /* renamed from: a  reason: collision with root package name */
    public final s f12858a;
    public final LongSparseArray<Annotation> b;

    public v(s sVar, LongSparseArray<Annotation> longSparseArray) {
        this.f12858a = sVar;
        this.b = longSparseArray;
    }

    @Override // com.mappls.sdk.maps.w
    @NonNull
    public List<Polygon> a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.b;
            Annotation annotation = longSparseArray.get(longSparseArray.keyAt(i));
            if (annotation instanceof Polygon) {
                arrayList.add((Polygon) annotation);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.w
    @NonNull
    public List<Polygon> b(@NonNull List<PolygonOptions> list, @NonNull MapplsMap mapplsMap) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (this.f12858a != null && size > 0) {
            for (PolygonOptions polygonOptions : list) {
                Polygon polygon = polygonOptions.getPolygon();
                if (!polygon.getPoints().isEmpty()) {
                    arrayList.add(polygon);
                }
            }
            long[] L = this.f12858a.L(arrayList);
            for (int i = 0; i < L.length; i++) {
                Polygon polygon2 = (Polygon) arrayList.get(i);
                polygon2.setMapplsMap(mapplsMap);
                polygon2.setId(L[i]);
                this.b.put(L[i], polygon2);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.w
    public void c(@NonNull Polygon polygon) {
        this.f12858a.R(polygon);
        LongSparseArray<Annotation> longSparseArray = this.b;
        longSparseArray.setValueAt(longSparseArray.indexOfKey(polygon.getId()), polygon);
    }

    @Override // com.mappls.sdk.maps.w
    public Polygon d(@NonNull PolygonOptions polygonOptions, @NonNull MapplsMap mapplsMap) {
        Polygon polygon = polygonOptions.getPolygon();
        if (!polygon.getPoints().isEmpty()) {
            s sVar = this.f12858a;
            long E = sVar != null ? sVar.E(polygon) : 0L;
            polygon.setId(E);
            polygon.setMapplsMap(mapplsMap);
            this.b.put(E, polygon);
        }
        return polygon;
    }
}
