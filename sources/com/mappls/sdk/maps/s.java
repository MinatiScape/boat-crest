package com.mappls.sdk.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.geometry.ProjectedMeters;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
import com.mappls.sdk.maps.style.light.Light;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.maps.style.sources.Source;
import java.util.List;
/* loaded from: classes11.dex */
public interface s {
    void A(@NonNull Marker marker);

    boolean A0();

    @NonNull
    PointF B(@NonNull LatLng latLng);

    void B0(double d, double d2, long j);

    void C(boolean z);

    double C0();

    void D(double[] dArr);

    double D0();

    long E(Polygon polygon);

    @NonNull
    long[] E0(RectF rectF);

    void F(String str);

    void F0(Image[] imageArr);

    void G();

    void G0(double d, @NonNull PointF pointF, long j);

    @NonNull
    RectF H(RectF rectF);

    void H0(@IntRange(from = 0) int i);

    boolean I(@NonNull String str);

    double J();

    void K(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr);

    @NonNull
    long[] L(@NonNull List<Polygon> list);

    void M(@NonNull Layer layer, @NonNull String str);

    @NonNull
    String N();

    void O(long[] jArr);

    void P(@Nullable LatLngBounds latLngBounds);

    @NonNull
    long[] Q(@NonNull List<Marker> list);

    void R(@NonNull Polygon polygon);

    void S(double d, long j);

    void T(double d);

    void U(boolean z);

    void V(double d, double d2, double d3, long j);

    double W(double d);

    Light X();

    Bitmap Y(String str);

    ProjectedMeters Z(@NonNull LatLng latLng);

    boolean a(@NonNull Layer layer);

    @NonNull
    long[] a0(RectF rectF);

    void b(@NonNull Polyline polyline);

    void b0(int i, int i2);

    @NonNull
    TransitionOptions c();

    void c0(@NonNull Layer layer);

    @NonNull
    List<Feature> d(@NonNull PointF pointF, @Nullable String[] strArr, @Nullable Expression expression);

    void d0(@NonNull Layer layer, @NonNull String str);

    void destroy();

    void e();

    long e0(Polyline polyline);

    @NonNull
    List<Source> f();

    LatLng f0(@NonNull ProjectedMeters projectedMeters);

    @NonNull
    long[] g(@NonNull List<Polyline> list);

    void g0(String str, int i, int i2, float f, byte[] bArr);

    @NonNull
    CameraPosition getCameraPosition();

    @NonNull
    List<Layer> getLayers();

    double getMaxZoom();

    double getMinZoom();

    float getPixelRatio();

    void h(@NonNull Layer layer, @IntRange(from = 0) int i);

    void h0(@NonNull double[] dArr, @NonNull double[] dArr2);

    void i(long j);

    CameraPosition i0(@NonNull LatLngBounds latLngBounds, int[] iArr, double d, double d2);

    boolean isDestroyed();

    double j();

    String j0();

    boolean k(@IntRange(from = 0) int i);

    @NonNull
    List<Feature> k0(@NonNull RectF rectF, @Nullable String[] strArr, @Nullable Expression expression);

    void l(String str);

    void l0(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr, long j, boolean z);

    void m(MapplsStyle mapplsStyle);

    void m0(boolean z);

    boolean n(@NonNull Source source);

    void n0(@NonNull double[] dArr, @NonNull double[] dArr2);

    Source o(@NonNull String str);

    List<MapplsStyle> o0();

    void onLowMemory();

    LatLng p(@NonNull PointF pointF);

    @NonNull
    String p0();

    void q(@NonNull Source source);

    Layer q0(String str);

    void r(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr, long j);

    void r0(String str);

    void s(double d);

    boolean s0(@NonNull String str);

    void setDebug(boolean z);

    CameraPosition t(@NonNull Geometry geometry, int[] iArr, double d, double d2);

    boolean t0();

    void u(String str);

    void u0(@NonNull double[] dArr);

    double v(String str);

    double v0();

    @IntRange(from = 0)
    int w();

    long w0(Marker marker);

    void x();

    void x0(@NonNull MapplsMap.OnFpsChangedListener onFpsChangedListener);

    void y(double d);

    void y0(@NonNull TransitionOptions transitionOptions);

    void z(@NonNull MapplsMap.SnapshotReadyCallback snapshotReadyCallback);

    void z0(double d);
}
