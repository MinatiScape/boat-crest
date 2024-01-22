package com.mappls.sdk.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.Annotation;
import com.mappls.sdk.maps.annotations.BaseMarkerOptions;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.PolygonOptions;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.annotations.PolylineOptions;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.log.Logger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class b {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final MapView f12682a;
    public final i b;
    public final LongSparseArray<Annotation> d;
    public final LongSparseArray<LatLng> f;
    public MapplsMap g;
    @Nullable
    public MapplsMap.OnMarkerClickListener h;
    @Nullable
    public MapplsMap.OnPolygonClickListener i;
    @Nullable
    public MapplsMap.OnPolylineClickListener j;
    public com.mappls.sdk.maps.c k;
    public b0 l;
    public q m;
    public w n;
    public y o;
    public final j c = new j();
    public final List<Marker> e = new ArrayList();

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final RectF f12683a;
        public final List<Marker> b;

        public a(RectF rectF, List<Marker> list) {
            this.f12683a = rectF;
            this.b = list;
        }

        public float c() {
            return this.f12683a.centerX();
        }

        public float d() {
            return this.f12683a.centerY();
        }
    }

    /* renamed from: com.mappls.sdk.maps.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0625b {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Projection f12684a;
        public final int b;
        public final LongSparseArray<LatLng> c;
        public Bitmap d;
        public int e;
        public int f;
        public PointF g;
        @NonNull
        public RectF h;
        @NonNull
        public RectF i;
        public long j;

        public C0625b(@NonNull MapplsMap mapplsMap, LongSparseArray<LatLng> longSparseArray) {
            new Rect();
            this.h = new RectF();
            this.i = new RectF();
            this.j = -1L;
            this.f12684a = mapplsMap.getProjection();
            this.b = (int) (Mappls.getApplicationContext().getResources().getDisplayMetrics().density * 32.0f);
            this.c = longSparseArray;
        }

        public long a(@NonNull a aVar) {
            e(aVar);
            return this.j;
        }

        public final void b(a aVar, @NonNull Marker marker, RectF rectF) {
            if (rectF.contains(aVar.c(), aVar.d())) {
                rectF.intersect(aVar.f12683a);
                if (c(rectF)) {
                    this.i = new RectF(rectF);
                    this.j = marker.getId();
                }
            }
        }

        public final boolean c(RectF rectF) {
            return rectF.width() * rectF.height() > this.i.width() * this.i.height();
        }

        public final void d(@NonNull a aVar, Marker marker) {
            if (marker.getMapplsPin() != null && marker.getPosition() == null) {
                this.g = this.f12684a.toScreenLocation(this.c.get(marker.getId(), null));
            } else {
                this.g = this.f12684a.toScreenLocation(marker.getPosition());
            }
            Bitmap bitmap = marker.getIcon().getBitmap();
            this.d = bitmap;
            int height = bitmap.getHeight();
            this.f = height;
            int i = this.b;
            if (height < i) {
                this.f = i;
            }
            int width = this.d.getWidth();
            this.e = width;
            int i2 = this.b;
            if (width < i2) {
                this.e = i2;
            }
            this.h.set(0.0f, 0.0f, this.e, this.f);
            RectF rectF = this.h;
            PointF pointF = this.g;
            rectF.offsetTo(pointF.x - (this.e / 2), pointF.y - (this.f / 2));
            b(aVar, marker, this.h);
        }

        public final void e(a aVar) {
            for (Marker marker : aVar.b) {
                d(aVar, marker);
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final RectF f12685a;

        public c(RectF rectF) {
            this.f12685a = rectF;
        }
    }

    /* loaded from: classes11.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public b0 f12686a;

        public d(b0 b0Var) {
            this.f12686a = b0Var;
        }

        @Nullable
        public Annotation a(@NonNull c cVar) {
            List<Annotation> b = this.f12686a.b(cVar.f12685a);
            if (b.size() > 0) {
                return b.get(0);
            }
            return null;
        }
    }

    public b(@NonNull MapView mapView, LongSparseArray<Annotation> longSparseArray, i iVar, com.mappls.sdk.maps.c cVar, q qVar, w wVar, y yVar, b0 b0Var, LongSparseArray<LatLng> longSparseArray2) {
        this.f12682a = mapView;
        this.d = longSparseArray;
        this.b = iVar;
        this.k = cVar;
        this.m = qVar;
        this.n = wVar;
        this.o = yVar;
        this.f = longSparseArray2;
        this.l = b0Var;
    }

    public void A() {
        this.m.c();
    }

    public void B(long j) {
        this.k.e(j);
    }

    public void C(@NonNull Annotation annotation) {
        if (annotation instanceof Marker) {
            Marker marker = (Marker) annotation;
            marker.hideInfoWindow();
            if (this.e.contains(marker)) {
                this.e.remove(marker);
            }
            this.b.g(marker.getIcon());
        }
        this.k.d(annotation);
    }

    public void D() {
        int size = this.d.size();
        long[] jArr = new long[size];
        this.e.clear();
        for (int i = 0; i < size; i++) {
            jArr[i] = this.d.keyAt(i);
            Annotation annotation = this.d.get(jArr[i]);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                this.b.g(marker.getIcon());
            }
        }
        this.k.f();
    }

    public void E(@NonNull List<? extends Annotation> list) {
        for (Annotation annotation : list) {
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                if (this.e.contains(marker)) {
                    this.e.remove(marker);
                }
                this.b.g(marker.getIcon());
            }
        }
        this.k.b(list);
    }

    public void F(@NonNull Marker marker) {
        MapView mapView;
        if (this.e.contains(marker)) {
            return;
        }
        if (!this.c.f()) {
            j();
        }
        if ((this.c.g(marker) || this.c.b() != null) && (mapView = this.f12682a) != null) {
            this.c.a(marker.showInfoWindow(this.g, mapView));
        }
        this.e.add(marker);
    }

    public void G(@Nullable MapplsMap.OnMarkerClickListener onMarkerClickListener) {
        this.h = onMarkerClickListener;
    }

    public void H(@Nullable MapplsMap.OnPolygonClickListener onPolygonClickListener) {
        this.i = onPolygonClickListener;
    }

    public void I(@Nullable MapplsMap.OnPolylineClickListener onPolylineClickListener) {
        this.j = onPolylineClickListener;
    }

    public final void J(@NonNull Marker marker) {
        if (!this.e.contains(marker)) {
            F(marker);
        } else {
            i(marker);
        }
    }

    public void K() {
        this.c.m();
    }

    public void L(@NonNull Marker marker, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        if (!v(marker)) {
            x(marker);
        } else {
            this.m.e(marker, mapplsMap, onMarkerAddedListener);
        }
    }

    public void M(@NonNull Polygon polygon) {
        if (!v(polygon)) {
            x(polygon);
        } else {
            this.n.c(polygon);
        }
    }

    public void N(@NonNull Polyline polyline) {
        if (!v(polyline)) {
            x(polyline);
        } else {
            this.o.c(polyline);
        }
    }

    public Marker a(@NonNull BaseMarkerOptions baseMarkerOptions, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        return this.m.f(baseMarkerOptions, mapplsMap, onMarkerAddedListener);
    }

    public List<Marker> b(@NonNull List<? extends BaseMarkerOptions> list, @NonNull MapplsMap mapplsMap, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        return this.m.d(list, mapplsMap, onMarkerAddedListener);
    }

    public Polygon c(@NonNull PolygonOptions polygonOptions, @NonNull MapplsMap mapplsMap) {
        return this.n.d(polygonOptions, mapplsMap);
    }

    public List<Polygon> d(@NonNull List<PolygonOptions> list, @NonNull MapplsMap mapplsMap) {
        return this.n.b(list, mapplsMap);
    }

    public Polyline e(@NonNull PolylineOptions polylineOptions, @NonNull MapplsMap mapplsMap) {
        return this.o.d(polylineOptions, mapplsMap);
    }

    public List<Polyline> f(@NonNull List<PolylineOptions> list, @NonNull MapplsMap mapplsMap) {
        return this.o.b(list, mapplsMap);
    }

    public void g(@NonNull MapplsMap mapplsMap) {
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            Annotation annotation = this.d.get(i);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.setTopOffsetPixels(this.b.f(marker.getIcon()));
            }
        }
        if (this.f12682a != null) {
            for (Marker marker2 : this.e) {
                if (marker2.isInfoWindowShown()) {
                    marker2.hideInfoWindow();
                    marker2.showInfoWindow(mapplsMap, this.f12682a);
                }
            }
        }
    }

    @NonNull
    public b h(MapplsMap mapplsMap) {
        this.g = mapplsMap;
        return this;
    }

    public void i(@NonNull Marker marker) {
        if (this.e.contains(marker)) {
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
            this.e.remove(marker);
        }
    }

    public void j() {
        if (this.e.isEmpty()) {
            return;
        }
        for (Marker marker : this.e) {
            if (marker != null && marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
        }
        this.e.clear();
    }

    public Annotation k(long j) {
        return this.k.c(j);
    }

    public List<Annotation> l() {
        return this.k.a();
    }

    @NonNull
    public j m() {
        return this.c;
    }

    public final a n(PointF pointF) {
        float f = pointF.x;
        float d2 = (int) (this.b.d() * 1.5d);
        float f2 = pointF.y;
        float e = (int) (this.b.e() * 1.5d);
        RectF rectF = new RectF(f - d2, f2 - e, f + d2, f2 + e);
        return new a(rectF, p(rectF));
    }

    public List<Marker> o() {
        return this.m.a();
    }

    @NonNull
    public List<Marker> p(@NonNull RectF rectF) {
        return this.m.b(rectF);
    }

    public List<Polygon> q() {
        return this.n.a();
    }

    public List<Polyline> r() {
        return this.o.a();
    }

    @NonNull
    public List<Marker> s() {
        return this.e;
    }

    public final c t(PointF pointF) {
        float dimension = Mappls.getApplicationContext().getResources().getDimension(R.dimen.mappls_maps_eight_dp);
        float f = pointF.x;
        float f2 = pointF.y;
        return new c(new RectF(f - dimension, f2 - dimension, f + dimension, f2 + dimension));
    }

    public final boolean u(Annotation annotation) {
        MapplsMap.OnPolylineClickListener onPolylineClickListener;
        MapplsMap.OnPolygonClickListener onPolygonClickListener;
        if ((annotation instanceof Polygon) && (onPolygonClickListener = this.i) != null) {
            onPolygonClickListener.onPolygonClick((Polygon) annotation);
            return true;
        } else if (!(annotation instanceof Polyline) || (onPolylineClickListener = this.j) == null) {
            return false;
        } else {
            onPolylineClickListener.onPolylineClick((Polyline) annotation);
            return true;
        }
    }

    public final boolean v(@Nullable Annotation annotation) {
        return (annotation == null || annotation.getId() == -1 || this.d.indexOfKey(annotation.getId()) <= -1) ? false : true;
    }

    public final boolean w(long j) {
        Marker marker = (Marker) k(j);
        if (y(marker)) {
            return true;
        }
        J(marker);
        return true;
    }

    public final void x(@NonNull Annotation annotation) {
        Logger.w("Mbgl-AnnotationManager", String.format("Attempting to update non-added %s with value %s", annotation.getClass().getCanonicalName(), annotation));
    }

    public final boolean y(@NonNull Marker marker) {
        MapplsMap.OnMarkerClickListener onMarkerClickListener = this.h;
        return onMarkerClickListener != null && onMarkerClickListener.onMarkerClick(marker);
    }

    public boolean z(@NonNull PointF pointF) {
        long a2 = new C0625b(this.g, this.f).a(n(pointF));
        if (a2 == -1 || !w(a2)) {
            Annotation a3 = new d(this.l).a(t(pointF));
            return a3 != null && u(a3);
        }
        return true;
    }
}
