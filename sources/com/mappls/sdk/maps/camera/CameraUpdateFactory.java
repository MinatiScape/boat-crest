package com.mappls.sdk.maps.camera;

import android.graphics.Point;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import java.util.Arrays;
/* loaded from: classes11.dex */
public final class CameraUpdateFactory {

    /* loaded from: classes11.dex */
    public static final class b implements CameraUpdate {

        /* renamed from: a  reason: collision with root package name */
        public final double f12695a;
        public final LatLng b;
        public final double c;
        public final double d;
        public final double[] e;

        public b(double d, LatLng latLng, double d2, double d3, double[] dArr) {
            this.f12695a = d;
            this.b = latLng;
            this.c = d2;
            this.d = d3;
            this.e = dArr;
        }

        public double a() {
            return this.f12695a;
        }

        public double[] b() {
            return this.e;
        }

        public LatLng c() {
            return this.b;
        }

        public double d() {
            return this.c;
        }

        public double e() {
            return this.d;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (Double.compare(bVar.f12695a, this.f12695a) == 0 && Double.compare(bVar.c, this.c) == 0 && Double.compare(bVar.d, this.d) == 0) {
                LatLng latLng = this.b;
                if (latLng == null ? bVar.b == null : latLng.equals(bVar.b)) {
                    return Arrays.equals(this.e, bVar.e);
                }
                return false;
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.camera.CameraUpdate
        public CameraPosition getCameraPosition(@NonNull MapplsMap mapplsMap) {
            if (this.b == null) {
                return new CameraPosition.Builder(this).target(mapplsMap.getCameraPosition().target).build();
            }
            return new CameraPosition.Builder(this).build();
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.f12695a);
            int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
            LatLng latLng = this.b;
            int hashCode = latLng != null ? latLng.hashCode() : 0;
            long doubleToLongBits2 = Double.doubleToLongBits(this.c);
            long doubleToLongBits3 = Double.doubleToLongBits(this.d);
            return ((((((i + hashCode) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3))) * 31) + Arrays.hashCode(this.e);
        }

        public String toString() {
            return "CameraPositionUpdate{bearing=" + this.f12695a + ", target=" + this.b + ", tilt=" + this.c + ", zoom=" + this.d + ", padding=" + Arrays.toString(this.e) + '}';
        }
    }

    public static CameraUpdate bearingTo(double d) {
        return new b(d, null, -1.0d, -1.0d, null);
    }

    public static CameraUpdate newCameraPosition(@NonNull CameraPosition cameraPosition) {
        return new b(cameraPosition.bearing, cameraPosition.target, cameraPosition.tilt, cameraPosition.zoom, cameraPosition.padding);
    }

    public static CameraUpdate newLatLng(@NonNull LatLng latLng) {
        return new b(-1.0d, latLng, -1.0d, -1.0d, null);
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i) {
        return newLatLngBounds(latLngBounds, i, i, i, i);
    }

    public static CameraUpdate newLatLngPadding(@NonNull LatLng latLng, double d, double d2, double d3, double d4) {
        return new b(-1.0d, latLng, -1.0d, -1.0d, new double[]{d, d2, d3, d4});
    }

    public static CameraUpdate newLatLngZoom(@NonNull LatLng latLng, double d) {
        return new b(-1.0d, latLng, -1.0d, d, null);
    }

    public static CameraUpdate paddingTo(double[] dArr) {
        return new b(-1.0d, null, -1.0d, -1.0d, dArr);
    }

    public static CameraUpdate tiltTo(double d) {
        return new b(-1.0d, null, d, -1.0d, null);
    }

    public static CameraUpdate zoomBy(double d, Point point) {
        return new c(d, point.x, point.y);
    }

    public static CameraUpdate zoomIn() {
        return new c(0);
    }

    public static CameraUpdate zoomOut() {
        return new c(1);
    }

    public static CameraUpdate zoomTo(double d) {
        return new c(3, d);
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, double d, double d2, int i) {
        return newLatLngBounds(latLngBounds, d, d2, i, i, i, i);
    }

    public static CameraUpdate paddingTo(double d, double d2, double d3, double d4) {
        return paddingTo(new double[]{d, d2, d3, d4});
    }

    public static CameraUpdate zoomBy(double d) {
        return new c(2, d);
    }

    /* loaded from: classes11.dex */
    public static final class c implements CameraUpdate {

        /* renamed from: a  reason: collision with root package name */
        public final int f12696a;
        public final double b;
        public float c;
        public float d;

        public c(int i) {
            this.f12696a = i;
            this.b = 0.0d;
        }

        public int a() {
            return this.f12696a;
        }

        public float b() {
            return this.c;
        }

        public float c() {
            return this.d;
        }

        public double d() {
            return this.b;
        }

        public double e(double d) {
            double d2;
            int a2 = a();
            if (a2 != 0) {
                if (a2 == 1) {
                    double d3 = d - 1.0d;
                    if (d3 < 0.0d) {
                        return 0.0d;
                    }
                    return d3;
                }
                if (a2 == 2) {
                    d2 = d();
                } else if (a2 == 3) {
                    return d();
                } else {
                    if (a2 != 4) {
                        return d;
                    }
                    d2 = d();
                }
                return d + d2;
            }
            return d + 1.0d;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || c.class != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            return this.f12696a == cVar.f12696a && Double.compare(cVar.b, this.b) == 0 && Float.compare(cVar.c, this.c) == 0 && Float.compare(cVar.d, this.d) == 0;
        }

        @Override // com.mappls.sdk.maps.camera.CameraUpdate
        public CameraPosition getCameraPosition(@NonNull MapplsMap mapplsMap) {
            CameraPosition cameraPosition = mapplsMap.getCameraPosition();
            if (a() != 4) {
                return new CameraPosition.Builder(cameraPosition).zoom(e(cameraPosition.zoom)).build();
            }
            return new CameraPosition.Builder(cameraPosition).zoom(e(cameraPosition.zoom)).target(mapplsMap.getProjection().fromScreenLocation(new PointF(b(), c()))).build();
        }

        public int hashCode() {
            int i = this.f12696a;
            long doubleToLongBits = Double.doubleToLongBits(this.b);
            int i2 = ((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
            float f = this.c;
            int floatToIntBits = (i2 + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
            float f2 = this.d;
            return floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
        }

        public String toString() {
            return "ZoomUpdate{type=" + this.f12696a + ", zoom=" + this.b + ", x=" + this.c + ", y=" + this.d + '}';
        }

        public c(int i, double d) {
            this.f12696a = i;
            this.b = d;
        }

        public c(double d, float f, float f2) {
            this.f12696a = 4;
            this.b = d;
            this.c = f;
            this.d = f2;
        }
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        return new a(latLngBounds, null, null, i, i2, i3, i4);
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, double d, double d2, int i, int i2, int i3, int i4) {
        return new a(latLngBounds, Double.valueOf(d), Double.valueOf(d2), i, i2, i3, i4);
    }

    /* loaded from: classes11.dex */
    public static final class a implements CameraUpdate {

        /* renamed from: a  reason: collision with root package name */
        public final LatLngBounds f12694a;
        public final int[] b;
        public final Double c;
        public final Double d;

        public a(LatLngBounds latLngBounds, Double d, Double d2, int[] iArr) {
            this.f12694a = latLngBounds;
            this.b = iArr;
            this.c = d;
            this.d = d2;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.f12694a.equals(aVar.f12694a)) {
                return Arrays.equals(this.b, aVar.b);
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.camera.CameraUpdate
        public CameraPosition getCameraPosition(@NonNull MapplsMap mapplsMap) {
            Double d = this.c;
            if (d == null && this.d == null) {
                return mapplsMap.getCameraForLatLngBounds(this.f12694a, this.b);
            }
            return mapplsMap.getCameraForLatLngBounds(this.f12694a, this.b, d.doubleValue(), this.d.doubleValue());
        }

        public int hashCode() {
            return (this.f12694a.hashCode() * 31) + Arrays.hashCode(this.b);
        }

        public String toString() {
            return "CameraBoundsUpdate{bounds=" + this.f12694a + ", padding=" + Arrays.toString(this.b) + '}';
        }

        public a(LatLngBounds latLngBounds, Double d, Double d2, int i, int i2, int i3, int i4) {
            this(latLngBounds, d, d2, new int[]{i, i2, i3, i4});
        }
    }
}
