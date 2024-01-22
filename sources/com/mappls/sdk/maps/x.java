package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.annotations.Annotation;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.annotations.PolylineOptions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class x implements y {

    /* renamed from: a  reason: collision with root package name */
    public final s f12864a;
    public final LongSparseArray<Annotation> b;

    public x(s sVar, LongSparseArray<Annotation> longSparseArray) {
        this.f12864a = sVar;
        this.b = longSparseArray;
    }

    @Override // com.mappls.sdk.maps.y
    @NonNull
    public List<Polyline> a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.b;
            Annotation annotation = longSparseArray.get(longSparseArray.keyAt(i));
            if (annotation instanceof Polyline) {
                arrayList.add((Polyline) annotation);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.y
    @NonNull
    public List<Polyline> b(@NonNull List<PolylineOptions> list, @NonNull MapplsMap mapplsMap) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (this.f12864a != null && size > 0) {
            for (PolylineOptions polylineOptions : list) {
                Polyline polyline = polylineOptions.getPolyline();
                if (!polyline.getPoints().isEmpty()) {
                    arrayList.add(polyline);
                }
            }
            long[] g = this.f12864a.g(arrayList);
            for (int i = 0; i < g.length; i++) {
                Polyline polyline2 = (Polyline) arrayList.get(i);
                polyline2.setMapplsMap(mapplsMap);
                polyline2.setId(g[i]);
                this.b.put(g[i], polyline2);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.y
    public void c(@NonNull Polyline polyline) {
        this.f12864a.b(polyline);
        LongSparseArray<Annotation> longSparseArray = this.b;
        longSparseArray.setValueAt(longSparseArray.indexOfKey(polyline.getId()), polyline);
    }

    @Override // com.mappls.sdk.maps.y
    public Polyline d(@NonNull PolylineOptions polylineOptions, @NonNull MapplsMap mapplsMap) {
        Polyline polyline = polylineOptions.getPolyline();
        if (!polyline.getPoints().isEmpty()) {
            s sVar = this.f12864a;
            long e0 = sVar != null ? sVar.e0(polyline) : 0L;
            polyline.setMapplsMap(mapplsMap);
            polyline.setId(e0);
            this.b.put(e0, polyline);
        }
        return polyline;
    }
}
