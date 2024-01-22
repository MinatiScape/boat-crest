package com.mappls.sdk.maps.camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraMapplsPinPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
public class CameraMapplsPinUpdateFactory {

    /* loaded from: classes11.dex */
    public static final class b implements CameraMapplsPinUpdate {

        /* renamed from: a  reason: collision with root package name */
        public final double f12692a;
        public final String b;
        public final double c;
        public final double d;
        public final double[] e;

        public b(double d, String str, double d2, double d3, double[] dArr) {
            this.f12692a = d;
            this.b = str;
            this.c = d2;
            this.d = d3;
            this.e = dArr;
        }

        public double a() {
            return this.f12692a;
        }

        public double[] b() {
            return this.e;
        }

        public String c() {
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
            if (Double.compare(bVar.f12692a, this.f12692a) == 0 && Double.compare(bVar.c, this.c) == 0 && Double.compare(bVar.d, this.d) == 0) {
                String str = this.b;
                if (str == null ? bVar.b == null : str.equals(bVar.b)) {
                    return Arrays.equals(this.e, bVar.e);
                }
                return false;
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinUpdate
        public CameraMapplsPinPosition getCameraMapplsPinPosition(@NonNull MapplsMap mapplsMap) {
            return new CameraMapplsPinPosition.Builder(this).build();
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.f12692a);
            int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
            String str = this.b;
            int hashCode = str != null ? str.hashCode() : 0;
            long doubleToLongBits2 = Double.doubleToLongBits(this.c);
            long doubleToLongBits3 = Double.doubleToLongBits(this.d);
            return ((((((i + hashCode) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3))) * 31) + Arrays.hashCode(this.e);
        }

        public String toString() {
            return "CameraMapplsPinPositionUpdate{bearing=" + this.f12692a + ", target=" + this.b + ", tilt=" + this.c + ", zoom=" + this.d + ", padding=" + Arrays.toString(this.e) + '}';
        }
    }

    public static CameraMapplsPinUpdate newCameraPosition(@NonNull CameraMapplsPinPosition cameraMapplsPinPosition) {
        return new b(cameraMapplsPinPosition.bearing, cameraMapplsPinPosition.target, cameraMapplsPinPosition.tilt, cameraMapplsPinPosition.zoom, cameraMapplsPinPosition.padding);
    }

    public static CameraMapplsPinUpdate newMapplsPin(@NonNull String str) {
        return new b(-1.0d, str, -1.0d, -1.0d, null);
    }

    public static CameraMapplsPinBoundUpdate newMapplsPinBounds(@NonNull List<String> list, int i) {
        return newMapplsPinBounds(list, i, i, i, i);
    }

    public static CameraMapplsPinUpdate newMapplsPinPadding(@NonNull String str, double d, double d2, double d3, double d4) {
        return new b(-1.0d, str, -1.0d, -1.0d, new double[]{d, d2, d3, d4});
    }

    public static CameraMapplsPinUpdate newMapplsPinZoom(@NonNull String str, double d) {
        return new b(-1.0d, str, -1.0d, d, null);
    }

    public static CameraMapplsPinBoundUpdate newMapplsPinBounds(@NonNull List<String> list, double d, double d2, int i) {
        return newMapplsPinBounds(list, d, d2, i, i, i, i);
    }

    public static CameraMapplsPinBoundUpdate newMapplsPinBounds(@NonNull List<String> list, int i, int i2, int i3, int i4) {
        return new a(list, null, null, i, i2, i3, i4);
    }

    public static CameraMapplsPinBoundUpdate newMapplsPinBounds(@NonNull List<String> list, double d, double d2, int i, int i2, int i3, int i4) {
        return new a(list, Double.valueOf(d), Double.valueOf(d2), i, i2, i3, i4);
    }

    /* loaded from: classes11.dex */
    public static final class a implements CameraMapplsPinBoundUpdate {

        /* renamed from: a  reason: collision with root package name */
        public final List<String> f12690a;
        public final int[] b;
        public final Double c;
        public final Double d;

        /* renamed from: com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0626a implements CoordinateCallback {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ CameraMapplsPinCallback f12691a;

            public C0626a(CameraMapplsPinCallback cameraMapplsPinCallback) {
                this.f12691a = cameraMapplsPinCallback;
            }

            @Override // com.mappls.sdk.maps.CoordinateCallback
            public void coordinateResultSuccess(List<CoordinateResult> list) {
                if (list.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (CoordinateResult coordinateResult : list) {
                        arrayList.add(new LatLng(coordinateResult.getLatitude().doubleValue(), coordinateResult.getLongitude().doubleValue()));
                    }
                    LatLngBounds build = new LatLngBounds.Builder().includes(arrayList).build();
                    if (a.this.c != null || a.this.d != null) {
                        this.f12691a.getCameraUpdate(CameraUpdateFactory.newLatLngBounds(build, a.this.c.doubleValue(), a.this.d.doubleValue(), a.this.b[0], a.this.b[1], a.this.b[2], a.this.b[3]));
                        return;
                    }
                    this.f12691a.getCameraUpdate(CameraUpdateFactory.newLatLngBounds(build, a.this.b[0], a.this.b[1], a.this.b[2], a.this.b[3]));
                    return;
                }
                this.f12691a.onError();
            }

            @Override // com.mappls.sdk.maps.CoordinateCallback
            public void onFailure() {
                this.f12691a.onError();
            }
        }

        public a(List<String> list, Double d, Double d2, int[] iArr) {
            this.f12690a = list;
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
            if (this.f12690a.equals(aVar.f12690a)) {
                return Arrays.equals(this.b, aVar.b);
            }
            return false;
        }

        @Override // com.mappls.sdk.maps.camera.CameraMapplsPinBoundUpdate
        public void getCameraMapplsPinPosition(@NonNull MapplsMap mapplsMap, CameraMapplsPinCallback cameraMapplsPinCallback) {
            try {
                Object newInstance = BaseMapplsHelper.class.newInstance();
                Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", List.class, CoordinateCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(newInstance, this.f12690a, new C0626a(cameraMapplsPinCallback));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        }

        public int hashCode() {
            return (this.f12690a.hashCode() * 31) + Arrays.hashCode(this.b);
        }

        public String toString() {
            return "CameraMapplsPinBoundsUpdate{bounds=" + this.f12690a + ", padding=" + Arrays.toString(this.b) + '}';
        }

        public a(List<String> list, Double d, Double d2, int i, int i2, int i3, int i4) {
            this(list, d, d2, new int[]{i, i2, i3, i4});
        }
    }
}
