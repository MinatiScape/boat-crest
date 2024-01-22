package com.mappls.sdk.maps;

import android.graphics.PointF;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.geometry.ProjectedMeters;
import com.mappls.sdk.maps.geometry.VisibleRegion;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes11.dex */
public class Projection {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final s f12654a;
    @Nullable
    public final MapView b;

    public Projection(@NonNull s sVar, @NonNull MapView mapView) {
        this.f12654a = sVar;
        this.b = mapView;
    }

    public static double a(@NonNull LatLng latLng, @NonNull LatLng latLng2) {
        double b = b(latLng.getLongitude());
        double b2 = b(latLng2.getLongitude());
        double b3 = b(latLng.getLatitude());
        double b4 = b(latLng2.getLatitude());
        double d = b2 - b;
        return g(Math.atan2(Math.sin(d) * Math.cos(b4), (Math.cos(b3) * Math.sin(b4)) - ((Math.sin(b3) * Math.cos(b4)) * Math.cos(d))));
    }

    public static double b(double d) {
        return ((d % 360.0d) * 3.141592653589793d) / 180.0d;
    }

    public static double e(double d, double d2) {
        double abs = Math.abs(d - d2);
        return d > d2 ? abs : 360.0d - abs;
    }

    public static double g(double d) {
        return ((d % 6.283185307179586d) * 180.0d) / 3.141592653589793d;
    }

    public int[] c() {
        double[] dArr = this.f12654a.getCameraPosition().padding;
        return new int[]{(int) dArr[0], (int) dArr[1], (int) dArr[2], (int) dArr[3]};
    }

    public double calculateZoom(float f) {
        return this.f12654a.C0() + (Math.log(f) / Math.log(2.0d));
    }

    public float d() {
        MapView mapView = this.b;
        if (mapView != null) {
            return mapView.getHeight();
        }
        return 0.0f;
    }

    public float f() {
        MapView mapView = this.b;
        if (mapView != null) {
            return mapView.getWidth();
        }
        return 0.0f;
    }

    @NonNull
    public LatLng fromScreenLocation(@NonNull PointF pointF) {
        return this.f12654a.p(pointF);
    }

    public void fromScreenLocations(@NonNull double[] dArr, @NonNull double[] dArr2) {
        this.f12654a.n0(dArr, dArr2);
    }

    @NonNull
    public LatLng getLatLngForProjectedMeters(@NonNull ProjectedMeters projectedMeters) {
        return this.f12654a.f0(projectedMeters);
    }

    public double getMetersPerPixelAtLatitude(@FloatRange(from = -90.0d, to = 90.0d) double d) {
        return this.f12654a.W(d);
    }

    @NonNull
    public ProjectedMeters getProjectedMetersForLatLng(@NonNull LatLng latLng) {
        return this.f12654a.Z(latLng);
    }

    public void getVisibleCoordinateBounds(@NonNull double[] dArr) {
        this.f12654a.u0(dArr);
    }

    @NonNull
    public VisibleRegion getVisibleRegion() {
        return getVisibleRegion(true);
    }

    public void h(int[] iArr) {
        double[] dArr = new double[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            dArr[i] = iArr[i];
        }
        this.f12654a.D(dArr);
    }

    @Deprecated
    public void invalidateContentPadding() {
    }

    @NonNull
    public PointF toScreenLocation(@NonNull LatLng latLng) {
        return this.f12654a.B(latLng);
    }

    public void toScreenLocations(@NonNull double[] dArr, @NonNull double[] dArr2) {
        this.f12654a.h0(dArr, dArr2);
    }

    @NonNull
    public VisibleRegion getVisibleRegion(boolean z) {
        MapView mapView;
        float f;
        MapView mapView2;
        float f2;
        float f3;
        Iterator it;
        MapView mapView3;
        if (z) {
            f2 = this.b != null ? mapView3.getWidth() : 0.0f;
            MapView mapView4 = this.b;
            if (mapView4 != null) {
                f3 = mapView4.getHeight();
                f = 0.0f;
            } else {
                f3 = 0.0f;
                f = 0.0f;
            }
        } else {
            int[] c = c();
            float f4 = c[0];
            float width = this.b != null ? mapView.getWidth() - c[2] : 0.0f;
            f = c[1];
            r1 = this.b != null ? mapView2.getHeight() - c[3] : 0.0f;
            f2 = width;
            f3 = r1;
            r1 = f4;
        }
        LatLng fromScreenLocation = fromScreenLocation(new PointF(((f2 - r1) / 2.0f) + r1, ((f3 - f) / 2.0f) + f));
        LatLng fromScreenLocation2 = fromScreenLocation(new PointF(r1, f));
        LatLng fromScreenLocation3 = fromScreenLocation(new PointF(f2, f));
        LatLng fromScreenLocation4 = fromScreenLocation(new PointF(f2, f3));
        LatLng fromScreenLocation5 = fromScreenLocation(new PointF(r1, f3));
        ArrayList arrayList = new ArrayList();
        arrayList.add(fromScreenLocation3);
        arrayList.add(fromScreenLocation4);
        arrayList.add(fromScreenLocation5);
        arrayList.add(fromScreenLocation2);
        Iterator it2 = arrayList.iterator();
        double d = 0.0d;
        double d2 = -90.0d;
        double d3 = 90.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        while (it2.hasNext()) {
            LatLng latLng = (LatLng) it2.next();
            if (a(fromScreenLocation, latLng) >= d) {
                it = it2;
                double e = e(latLng.getLongitude(), fromScreenLocation.getLongitude());
                if (e > d5) {
                    d6 = latLng.getLongitude();
                    d5 = e;
                }
            } else {
                it = it2;
                double e2 = e(fromScreenLocation.getLongitude(), latLng.getLongitude());
                if (e2 > d4) {
                    d7 = latLng.getLongitude();
                    d4 = e2;
                }
            }
            if (d2 < latLng.getLatitude()) {
                d2 = latLng.getLatitude();
            }
            if (d3 > latLng.getLatitude()) {
                d3 = latLng.getLatitude();
            }
            it2 = it;
            d = 0.0d;
        }
        if (d6 < d7) {
            return new VisibleRegion(fromScreenLocation2, fromScreenLocation3, fromScreenLocation5, fromScreenLocation4, LatLngBounds.from(d2, d6 + 360.0d, d3, d7));
        }
        return new VisibleRegion(fromScreenLocation2, fromScreenLocation3, fromScreenLocation5, fromScreenLocation4, LatLngBounds.from(d2, d6, d3, d7));
    }
}
