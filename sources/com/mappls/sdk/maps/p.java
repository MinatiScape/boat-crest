package com.mappls.sdk.maps;

import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.Annotation;
import com.mappls.sdk.maps.annotations.BaseMarkerOptions;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes11.dex */
public class p implements q {

    /* renamed from: a  reason: collision with root package name */
    public final s f12815a;
    public final LongSparseArray<Annotation> b;
    public final i c;
    public final LongSparseArray<LatLng> d;

    /* loaded from: classes11.dex */
    public class a implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap.OnMarkerAddedListener f12816a;
        public final /* synthetic */ Marker b;
        public final /* synthetic */ MapplsMap c;

        public a(MapplsMap.OnMarkerAddedListener onMarkerAddedListener, Marker marker, MapplsMap mapplsMap) {
            this.f12816a = onMarkerAddedListener;
            this.b = marker;
            this.c = mapplsMap;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                this.b.setPosition(new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue()));
                p.this.m(this.b, this.c);
                if (this.f12816a != null) {
                    if (p.this.f12815a != null) {
                        this.f12816a.onSuccess();
                        return;
                    } else {
                        this.f12816a.onFailure();
                        return;
                    }
                }
                return;
            }
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.f12816a;
            if (onMarkerAddedListener != null) {
                onMarkerAddedListener.onFailure();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.f12816a;
            if (onMarkerAddedListener != null) {
                onMarkerAddedListener.onFailure();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ HashMap f12817a;
        public final /* synthetic */ List b;
        public final /* synthetic */ MapplsMap c;
        public final /* synthetic */ MapplsMap.OnMarkerAddedListener d;

        public b(HashMap hashMap, List list, MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
            this.f12817a = hashMap;
            this.b = list;
            this.c = mapplsMap;
            this.d = onMarkerAddedListener;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() <= 0) {
                p.this.r(this.b);
                p.this.n(this.b, this.c);
                MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.d;
                if (onMarkerAddedListener != null) {
                    onMarkerAddedListener.onFailure();
                    return;
                }
                return;
            }
            for (CoordinateResult coordinateResult : list) {
                if (this.f12817a.containsKey(coordinateResult.getMapplsPin().toUpperCase())) {
                    ((Marker) this.f12817a.get(coordinateResult.getMapplsPin().toUpperCase())).setPosition(new LatLng(coordinateResult.getLatitude().doubleValue(), coordinateResult.getLongitude().doubleValue()));
                }
            }
            p.this.r(this.b);
            p.this.n(this.b, this.c);
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener2 = this.d;
            if (onMarkerAddedListener2 != null) {
                onMarkerAddedListener2.onSuccess();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            p.this.r(this.b);
            p.this.n(this.b, this.c);
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.d;
            if (onMarkerAddedListener != null) {
                onMarkerAddedListener.onFailure();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Marker f12818a;
        public final /* synthetic */ MapplsMap.OnMarkerAddedListener b;

        public c(Marker marker, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
            this.f12818a = marker;
            this.b = onMarkerAddedListener;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                this.f12818a.updatePosition(new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue()));
                p.this.f12815a.A(this.f12818a);
                p.this.b.setValueAt(p.this.b.indexOfKey(this.f12818a.getId()), this.f12818a);
                p.this.d.setValueAt(p.this.d.indexOfKey(this.f12818a.getId()), this.f12818a.getPosition());
                this.f12818a.setPosition(null);
                MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.b;
                if (onMarkerAddedListener != null) {
                    onMarkerAddedListener.onSuccess();
                    return;
                }
                return;
            }
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener2 = this.b;
            if (onMarkerAddedListener2 != null) {
                onMarkerAddedListener2.onFailure();
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
            MapplsMap.OnMarkerAddedListener onMarkerAddedListener = this.b;
            if (onMarkerAddedListener != null) {
                onMarkerAddedListener.onFailure();
            }
        }
    }

    public p(s sVar, LongSparseArray<Annotation> longSparseArray, i iVar, LongSparseArray<LatLng> longSparseArray2) {
        this.f12815a = sVar;
        this.b = longSparseArray;
        this.c = iVar;
        this.d = longSparseArray2;
    }

    @Override // com.mappls.sdk.maps.q
    @NonNull
    public List<Marker> a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.b;
            Annotation annotation = longSparseArray.get(longSparseArray.keyAt(i));
            if (annotation instanceof Marker) {
                arrayList.add((Marker) annotation);
            }
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.q
    @NonNull
    public List<Marker> b(@NonNull RectF rectF) {
        long[] E0 = this.f12815a.E0(this.f12815a.H(rectF));
        ArrayList arrayList = new ArrayList(E0.length);
        for (long j : E0) {
            arrayList.add(Long.valueOf(j));
        }
        ArrayList arrayList2 = new ArrayList(E0.length);
        List<Annotation> p = p();
        int size = p.size();
        for (int i = 0; i < size; i++) {
            Annotation annotation = p.get(i);
            if ((annotation instanceof Marker) && arrayList.contains(Long.valueOf(annotation.getId()))) {
                arrayList2.add((Marker) annotation);
            }
        }
        return new ArrayList(arrayList2);
    }

    @Override // com.mappls.sdk.maps.q
    public void c() {
        this.c.k();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            Annotation annotation = this.b.get(i);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                this.f12815a.i(annotation.getId());
                marker.setId(this.f12815a.w0(marker));
            }
        }
    }

    @Override // com.mappls.sdk.maps.q
    @NonNull
    public List<Marker> d(@NonNull List<? extends BaseMarkerOptions> list, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        if (this.f12815a != null && size > 0) {
            for (int i = 0; i < size; i++) {
                Marker q = q(list.get(i));
                if (q.getMapplsPin() != null && q.getPosition() == null) {
                    arrayList2.add(q.getMapplsPin());
                    hashMap.put(q.getMapplsPin().toUpperCase(), q);
                }
                arrayList.add(q);
            }
            if (arrayList2.size() > 0) {
                o.d().c(arrayList2, new b(hashMap, arrayList, mapplsMap, onMarkerAddedListener));
            } else {
                n(arrayList, mapplsMap);
                if (onMarkerAddedListener != null) {
                    onMarkerAddedListener.onSuccess();
                }
            }
        } else if (onMarkerAddedListener != null) {
            onMarkerAddedListener.onFailure();
        }
        return arrayList;
    }

    @Override // com.mappls.sdk.maps.q
    public void e(@NonNull Marker marker, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        o(marker, mapplsMap);
        if (marker.getMapplsPin() != null) {
            o.d().b(marker.getMapplsPin(), new c(marker, onMarkerAddedListener));
            return;
        }
        this.f12815a.A(marker);
        LongSparseArray<Annotation> longSparseArray = this.b;
        longSparseArray.setValueAt(longSparseArray.indexOfKey(marker.getId()), marker);
        LongSparseArray<LatLng> longSparseArray2 = this.d;
        longSparseArray2.setValueAt(longSparseArray2.indexOfKey(marker.getId()), marker.getPosition());
        if (onMarkerAddedListener != null) {
            onMarkerAddedListener.onSuccess();
        }
    }

    @Override // com.mappls.sdk.maps.q
    public Marker f(@NonNull BaseMarkerOptions baseMarkerOptions, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        Marker q = q(baseMarkerOptions);
        if (q.getMapplsPin() != null && q.getPosition() == null) {
            o.d().b(q.getMapplsPin(), new a(onMarkerAddedListener, q, mapplsMap));
        } else {
            m(q, mapplsMap);
            if (onMarkerAddedListener != null) {
                if (this.f12815a != null) {
                    onMarkerAddedListener.onSuccess();
                } else {
                    onMarkerAddedListener.onFailure();
                }
            }
        }
        return q;
    }

    public final void m(Marker marker, MapplsMap mapplsMap) {
        s sVar = this.f12815a;
        long w0 = sVar != null ? sVar.w0(marker) : 0L;
        marker.setMapplsMap(mapplsMap);
        marker.setId(w0);
        this.b.put(w0, marker);
        this.d.put(w0, marker.getPosition());
        if (marker.getMapplsPin() != null) {
            marker.setPosition(null);
        }
    }

    public final void n(List<Marker> list, MapplsMap mapplsMap) {
        if (list.size() > 0) {
            long[] Q = this.f12815a.Q(list);
            for (int i = 0; i < Q.length; i++) {
                Marker marker = list.get(i);
                marker.setMapplsMap(mapplsMap);
                marker.setId(Q[i]);
                this.d.put(Q[i], marker.getPosition());
                if (marker.getMapplsPin() != null) {
                    marker.setPosition(null);
                }
                this.b.put(Q[i], marker);
            }
        }
    }

    public final void o(Marker marker, @NonNull MapplsMap mapplsMap) {
        this.c.c(marker, mapplsMap);
    }

    @NonNull
    public final List<Annotation> p() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.b;
            arrayList.add(longSparseArray.get(longSparseArray.keyAt(i)));
        }
        return arrayList;
    }

    public final Marker q(BaseMarkerOptions baseMarkerOptions) {
        Marker marker = baseMarkerOptions.getMarker();
        marker.setTopOffsetPixels(this.c.f(this.c.j(marker)));
        return marker;
    }

    public final void r(List<Marker> list) {
        ArrayList arrayList = new ArrayList();
        for (Marker marker : list) {
            if (marker.getPosition() != null) {
                arrayList.add(marker);
            }
        }
        list.clear();
        list.addAll(arrayList);
    }
}
