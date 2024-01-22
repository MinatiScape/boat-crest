package com.mappls.sdk.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.Polygon;
import com.mappls.sdk.maps.annotations.Polyline;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.exceptions.CalledFromWorkerThreadException;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.geometry.ProjectedMeters;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.renderer.MapRenderer;
import com.mappls.sdk.maps.storage.FileSource;
import com.mappls.sdk.maps.style.expressions.Expression;
import com.mappls.sdk.maps.style.layers.CannotAddLayerException;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.TransitionOptions;
import com.mappls.sdk.maps.style.light.Light;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.maps.style.sources.CannotAddSourceException;
import com.mappls.sdk.maps.style.sources.Source;
import com.mappls.sdk.maps.utils.BitmapUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class NativeMapView implements s {

    /* renamed from: a  reason: collision with root package name */
    public final FileSource f12652a;
    public final MapRenderer b;
    @NonNull
    public final Thread c;
    @Nullable
    public ViewCallback d;
    @Nullable
    public b e;
    public final float f;
    public double[] h;
    public MapplsMap.SnapshotReadyCallback i;
    public String j;
    public boolean g = false;
    @Keep
    private long nativePtr = 0;

    /* loaded from: classes11.dex */
    public interface ViewCallback {
        @Nullable
        Bitmap getViewContent();
    }

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ MapplsMap.OnFpsChangedListener h;
        public final /* synthetic */ Handler i;

        /* renamed from: com.mappls.sdk.maps.NativeMapView$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class C0622a implements MapplsMap.OnFpsChangedListener {

            /* renamed from: com.mappls.sdk.maps.NativeMapView$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes11.dex */
            public class RunnableC0623a implements Runnable {
                public final /* synthetic */ double h;

                public RunnableC0623a(double d) {
                    this.h = d;
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.h.onFpsChanged(this.h);
                }
            }

            public C0622a() {
            }

            @Override // com.mappls.sdk.maps.MapplsMap.OnFpsChangedListener
            public void onFpsChanged(double d) {
                a.this.i.post(new RunnableC0623a(d));
            }
        }

        public a(MapplsMap.OnFpsChangedListener onFpsChangedListener, Handler handler) {
            this.h = onFpsChangedListener;
            this.i = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h != null) {
                NativeMapView.this.b.setOnFpsChangedListener(new C0622a());
            } else {
                NativeMapView.this.b.setOnFpsChangedListener(null);
            }
        }
    }

    /* loaded from: classes11.dex */
    public interface b extends c {
        void a(String str);

        void onCameraDidChange(boolean z);

        void onCameraIsChanging();

        void onCameraWillChange(boolean z);

        boolean onCanRemoveUnusedStyleImage(String str);

        void onDidBecomeIdle();

        void onDidFailLoadingMap(String str);

        void onDidFinishLoadingMap();

        void onDidFinishRenderingFrame(boolean z);

        void onDidFinishRenderingMap(boolean z);

        void onStyleImageMissing(String str);

        void onWillStartRenderingFrame();

        void onWillStartRenderingMap();
    }

    /* loaded from: classes11.dex */
    public interface c {
        void onDidFinishLoadingStyle();

        void onWillStartLoadingMap();
    }

    static {
        LibraryLoader.load();
    }

    public NativeMapView(Context context, float f, boolean z, ViewCallback viewCallback, b bVar, MapRenderer mapRenderer) {
        this.b = mapRenderer;
        this.d = viewCallback;
        FileSource fileSource = FileSource.getInstance(context);
        this.f12652a = fileSource;
        this.f = f;
        this.c = Thread.currentThread();
        this.e = bVar;
        nativeInitialize(this, fileSource, mapRenderer, f, z);
    }

    @Keep
    private native void nativeAddAnnotationIcon(String str, int i, int i2, float f, byte[] bArr);

    @Keep
    private native void nativeAddImage(String str, Bitmap bitmap, float f, boolean z);

    @Keep
    private native void nativeAddImages(Image[] imageArr);

    @Keep
    private native void nativeAddLayer(long j, String str) throws CannotAddLayerException;

    @Keep
    private native void nativeAddLayerAbove(long j, String str) throws CannotAddLayerException;

    @Keep
    private native void nativeAddLayerAt(long j, int i) throws CannotAddLayerException;

    @NonNull
    @Keep
    private native long[] nativeAddMarkers(Marker[] markerArr);

    @NonNull
    @Keep
    private native long[] nativeAddPolygons(Polygon[] polygonArr);

    @NonNull
    @Keep
    private native long[] nativeAddPolylines(Polyline[] polylineArr);

    @Keep
    private native void nativeAddSource(Source source, long j) throws CannotAddSourceException;

    @Keep
    private native void nativeCancelTransitions();

    @Keep
    private native void nativeDestroy();

    @Keep
    private native void nativeEaseTo(double d, double d2, double d3, long j, double d4, double d5, double[] dArr, boolean z);

    @Keep
    private native void nativeFlyTo(double d, double d2, double d3, long j, double d4, double d5, double[] dArr);

    @Keep
    private native double nativeGetBearing();

    @NonNull
    @Keep
    private native CameraPosition nativeGetCameraForGeometry(Geometry geometry, double d, double d2, double d3, double d4, double d5, double d6);

    @NonNull
    @Keep
    private native CameraPosition nativeGetCameraForLatLngBounds(LatLngBounds latLngBounds, double d, double d2, double d3, double d4, double d5, double d6);

    @NonNull
    @Keep
    private native CameraPosition nativeGetCameraPosition();

    @Keep
    private native boolean nativeGetDebug();

    @NonNull
    @Keep
    private native Bitmap nativeGetImage(String str);

    @NonNull
    @Keep
    private native LatLng nativeGetLatLng();

    @NonNull
    @Keep
    private native Layer nativeGetLayer(String str);

    @NonNull
    @Keep
    private native Layer[] nativeGetLayers();

    @NonNull
    @Keep
    private native Light nativeGetLight();

    @Keep
    private native double nativeGetMaxPitch();

    @Keep
    private native double nativeGetMaxZoom();

    @Keep
    private native double nativeGetMetersPerPixelAtLatitude(double d, double d2);

    @Keep
    private native double nativeGetMinPitch();

    @Keep
    private native double nativeGetMinZoom();

    @Keep
    private native double nativeGetPitch();

    @Keep
    private native boolean nativeGetPrefetchTiles();

    @Keep
    private native int nativeGetPrefetchZoomDelta();

    @NonNull
    @Keep
    private native Source nativeGetSource(String str);

    @NonNull
    @Keep
    private native Source[] nativeGetSources();

    @NonNull
    @Keep
    private native String nativeGetStyleJson();

    @NonNull
    @Keep
    private native String nativeGetStyleUrl();

    @Keep
    private native double nativeGetTopOffsetPixelsForAnnotationSymbol(String str);

    @Keep
    private native long nativeGetTransitionDelay();

    @Keep
    private native long nativeGetTransitionDuration();

    @NonNull
    @Keep
    private native TransitionOptions nativeGetTransitionOptions();

    @Keep
    private native void nativeGetVisibleCoordinateBounds(double[] dArr);

    @Keep
    private native double nativeGetZoom();

    @Keep
    private native void nativeInitialize(NativeMapView nativeMapView, FileSource fileSource, MapRenderer mapRenderer, float f, boolean z);

    @Keep
    private native boolean nativeIsFullyLoaded();

    @Keep
    private native void nativeJumpTo(double d, double d2, double d3, double d4, double d5, double[] dArr);

    @NonNull
    @Keep
    private native LatLng nativeLatLngForPixel(float f, float f2);

    @NonNull
    @Keep
    private native LatLng nativeLatLngForProjectedMeters(double d, double d2);

    @Keep
    private native void nativeLatLngsForPixels(double[] dArr, double[] dArr2, float f);

    @Keep
    private native void nativeMoveBy(double d, double d2, long j);

    @Keep
    private native void nativeOnLowMemory();

    @NonNull
    @Keep
    private native PointF nativePixelForLatLng(double d, double d2);

    @Keep
    private native void nativePixelsForLatLngs(double[] dArr, double[] dArr2, float f);

    @NonNull
    @Keep
    private native ProjectedMeters nativeProjectedMetersForLatLng(double d, double d2);

    @NonNull
    @Keep
    private native long[] nativeQueryPointAnnotations(RectF rectF);

    @NonNull
    @Keep
    private native Feature[] nativeQueryRenderedFeaturesForBox(float f, float f2, float f3, float f4, String[] strArr, Object[] objArr);

    @NonNull
    @Keep
    private native Feature[] nativeQueryRenderedFeaturesForPoint(float f, float f2, String[] strArr, Object[] objArr);

    @NonNull
    @Keep
    private native long[] nativeQueryShapeAnnotations(RectF rectF);

    @Keep
    private native void nativeRemoveAnnotationIcon(String str);

    @Keep
    private native void nativeRemoveAnnotations(long[] jArr);

    @Keep
    private native void nativeRemoveImage(String str);

    @Keep
    private native boolean nativeRemoveLayer(long j);

    @Keep
    private native boolean nativeRemoveLayerAt(int i);

    @Keep
    private native boolean nativeRemoveSource(Source source, long j);

    @Keep
    private native void nativeResetNorth();

    @Keep
    private native void nativeResetPosition();

    @Keep
    private native void nativeResetZoom();

    @Keep
    private native void nativeResizeView(int i, int i2);

    @Keep
    private native void nativeRotateBy(double d, double d2, double d3, double d4, long j);

    @Keep
    private native void nativeSetBearing(double d, long j);

    @Keep
    private native void nativeSetBearingXY(double d, double d2, double d3, long j);

    @Keep
    private native void nativeSetDebug(boolean z);

    @Keep
    private native void nativeSetGestureInProgress(boolean z);

    @Keep
    private native void nativeSetLatLng(double d, double d2, double[] dArr, long j);

    @Keep
    private native void nativeSetLatLngBounds(LatLngBounds latLngBounds);

    @Keep
    private native void nativeSetMaxPitch(double d);

    @Keep
    private native void nativeSetMaxZoom(double d);

    @Keep
    private native void nativeSetMinPitch(double d);

    @Keep
    private native void nativeSetMinZoom(double d);

    @Keep
    private native void nativeSetPitch(double d, long j);

    @Keep
    private native void nativeSetPrefetchTiles(boolean z);

    @Keep
    private native void nativeSetPrefetchZoomDelta(int i);

    @Keep
    private native void nativeSetReachability(boolean z);

    @Keep
    private native void nativeSetStyleJson(String str);

    @Keep
    private native void nativeSetStyleUrl(String str);

    @Keep
    private native void nativeSetTransitionDelay(long j);

    @Keep
    private native void nativeSetTransitionDuration(long j);

    @Keep
    private native void nativeSetTransitionOptions(TransitionOptions transitionOptions);

    @Keep
    private native void nativeSetVisibleCoordinateBounds(LatLng[] latLngArr, RectF rectF, double d, long j);

    @Keep
    private native void nativeSetZoom(double d, double d2, double d3, long j);

    @Keep
    private native void nativeTakeSnapshot();

    @Keep
    private native void nativeTriggerRepaint();

    @Keep
    private native void nativeUpdateMarker(long j, double d, double d2, String str);

    @Keep
    private native void nativeUpdatePolygon(long j, Polygon polygon);

    @Keep
    private native void nativeUpdatePolyline(long j, Polyline polyline);

    @Keep
    private void onCameraDidChange(boolean z) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onCameraDidChange(z);
        }
    }

    @Keep
    private void onCameraIsChanging() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onCameraIsChanging();
        }
    }

    @Keep
    private void onCameraWillChange(boolean z) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onCameraWillChange(z);
        }
    }

    @Keep
    private boolean onCanRemoveUnusedStyleImage(String str) {
        b bVar = this.e;
        if (bVar != null) {
            return bVar.onCanRemoveUnusedStyleImage(str);
        }
        return true;
    }

    @Keep
    private void onDidBecomeIdle() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidBecomeIdle();
        }
    }

    @Keep
    private void onDidFailLoadingMap(String str) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidFailLoadingMap(str);
        }
    }

    @Keep
    private void onDidFinishLoadingMap() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidFinishLoadingMap();
        }
    }

    @Keep
    private void onDidFinishLoadingStyle() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidFinishLoadingStyle();
        }
    }

    @Keep
    private void onDidFinishRenderingFrame(boolean z) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidFinishRenderingFrame(z);
        }
    }

    @Keep
    private void onDidFinishRenderingMap(boolean z) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onDidFinishRenderingMap(z);
        }
    }

    @Keep
    private void onSourceChanged(String str) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(str);
        }
    }

    @Keep
    private void onStyleImageMissing(String str) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onStyleImageMissing(str);
        }
    }

    @Keep
    private void onWillStartLoadingMap() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onWillStartLoadingMap();
        }
    }

    @Keep
    private void onWillStartRenderingFrame() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onWillStartRenderingFrame();
        }
    }

    @Keep
    private void onWillStartRenderingMap() {
        b bVar = this.e;
        if (bVar != null) {
            bVar.onWillStartRenderingMap();
        }
    }

    @Override // com.mappls.sdk.maps.s
    public void A(@NonNull Marker marker) {
        if (J0("updateMarker")) {
            return;
        }
        LatLng position = marker.getPosition();
        nativeUpdateMarker(marker.getId(), position.getLatitude(), position.getLongitude(), marker.getIcon().getId());
    }

    @Override // com.mappls.sdk.maps.s
    public boolean A0() {
        if (J0("getPrefetchTiles")) {
            return false;
        }
        return nativeGetPrefetchTiles();
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public PointF B(@NonNull LatLng latLng) {
        if (J0("pixelForLatLng")) {
            return new PointF();
        }
        PointF nativePixelForLatLng = nativePixelForLatLng(latLng.getLatitude(), latLng.getLongitude());
        float f = nativePixelForLatLng.x;
        float f2 = this.f;
        nativePixelForLatLng.set(f * f2, nativePixelForLatLng.y * f2);
        return nativePixelForLatLng;
    }

    @Override // com.mappls.sdk.maps.s
    public void B0(double d, double d2, long j) {
        if (J0("moveBy")) {
            return;
        }
        float f = this.f;
        nativeMoveBy(d / f, d2 / f, j);
    }

    @Override // com.mappls.sdk.maps.s
    public void C(boolean z) {
        if (J0("setReachability")) {
            return;
        }
        nativeSetReachability(z);
    }

    @Override // com.mappls.sdk.maps.s
    public double C0() {
        if (J0("getZoom")) {
            return 0.0d;
        }
        return nativeGetZoom();
    }

    @Override // com.mappls.sdk.maps.s
    public void D(double[] dArr) {
        if (J0("setContentPadding")) {
            return;
        }
        this.h = dArr;
    }

    @Override // com.mappls.sdk.maps.s
    public double D0() {
        if (J0("getBearing")) {
            return 0.0d;
        }
        return nativeGetBearing();
    }

    @Override // com.mappls.sdk.maps.s
    public long E(Polygon polygon) {
        if (J0("addPolygon")) {
            return 0L;
        }
        return nativeAddPolygons(new Polygon[]{polygon})[0];
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public long[] E0(RectF rectF) {
        return J0("queryPointAnnotations") ? new long[0] : nativeQueryPointAnnotations(rectF);
    }

    @Override // com.mappls.sdk.maps.s
    public void F(String str) {
        if (J0("removeImage")) {
            return;
        }
        nativeRemoveImage(str);
    }

    @Override // com.mappls.sdk.maps.s
    public void F0(@NonNull Image[] imageArr) {
        if (J0("addImages")) {
            return;
        }
        nativeAddImages(imageArr);
    }

    @Override // com.mappls.sdk.maps.s
    public void G() {
        if (J0("resetNorth")) {
            return;
        }
        nativeResetNorth();
    }

    @Override // com.mappls.sdk.maps.s
    public void G0(double d, @NonNull PointF pointF, long j) {
        if (J0("setZoom")) {
            return;
        }
        float f = pointF.x;
        float f2 = this.f;
        nativeSetZoom(d, f / f2, pointF.y / f2, j);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public RectF H(RectF rectF) {
        float f = rectF.left;
        float f2 = this.f;
        return new RectF(f / f2, rectF.top / f2, rectF.right / f2, rectF.bottom / f2);
    }

    @Override // com.mappls.sdk.maps.s
    public void H0(@IntRange(from = 0) int i) {
        if (J0("nativeSetPrefetchZoomDelta")) {
            return;
        }
        nativeSetPrefetchZoomDelta(i);
    }

    @Override // com.mappls.sdk.maps.s
    public boolean I(@NonNull String str) {
        Source o;
        if (J0("removeSource") || (o = o(str)) == null) {
            return false;
        }
        return n(o);
    }

    @Override // com.mappls.sdk.maps.s
    public double J() {
        if (J0("getPitch")) {
            return 0.0d;
        }
        return nativeGetPitch();
    }

    public final boolean J0(String str) {
        if (this.c == Thread.currentThread()) {
            if (this.g && !TextUtils.isEmpty(str)) {
                String format = String.format("You're calling `%s` after the `MapView` was destroyed, were you invoking it after `onDestroy()`?", str);
                Logger.e("Mbgl-NativeMapView", format);
                MapStrictMode.strictModeViolation(format);
            }
            return this.g;
        }
        throw new CalledFromWorkerThreadException(String.format("Map interactions should happen on the UI thread. Method invoked from wrong thread is %s.", str));
    }

    @Override // com.mappls.sdk.maps.s
    public void K(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr) {
        if (J0("jumpTo")) {
            return;
        }
        nativeJumpTo(d3, latLng.getLatitude(), latLng.getLongitude(), d2, d, K0(dArr));
    }

    public final double[] K0(double[] dArr) {
        if (dArr == null) {
            dArr = this.h;
        }
        this.h = null;
        if (dArr == null) {
            return null;
        }
        double d = dArr[1];
        float f = this.f;
        return new double[]{d / f, dArr[0] / f, dArr[3] / f, dArr[2] / f};
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public long[] L(@NonNull List<Polygon> list) {
        return J0("addPolygons") ? new long[0] : nativeAddPolygons((Polygon[]) list.toArray(new Polygon[list.size()]));
    }

    @Override // com.mappls.sdk.maps.s
    public void M(@NonNull Layer layer, @NonNull String str) {
        if (J0("addLayerAbove")) {
            return;
        }
        nativeAddLayerAbove(layer.getNativePtr(), str);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public String N() {
        return J0("getStyleJson") ? "" : nativeGetStyleJson();
    }

    @Override // com.mappls.sdk.maps.s
    public void O(long[] jArr) {
        if (J0("removeAnnotations")) {
            return;
        }
        nativeRemoveAnnotations(jArr);
    }

    @Override // com.mappls.sdk.maps.s
    public void P(LatLngBounds latLngBounds) {
        if (J0("setLatLngBounds")) {
            return;
        }
        nativeSetLatLngBounds(latLngBounds);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public long[] Q(@NonNull List<Marker> list) {
        return J0("addMarkers") ? new long[0] : nativeAddMarkers((Marker[]) list.toArray(new Marker[list.size()]));
    }

    @Override // com.mappls.sdk.maps.s
    public void R(@NonNull Polygon polygon) {
        if (J0("updatePolygon")) {
            return;
        }
        nativeUpdatePolygon(polygon.getId(), polygon);
    }

    @Override // com.mappls.sdk.maps.s
    public void S(double d, long j) {
        if (J0("setPitch")) {
            return;
        }
        nativeSetPitch(d, j);
    }

    @Override // com.mappls.sdk.maps.s
    public void T(double d) {
        if (J0("setMinPitch")) {
            return;
        }
        nativeSetMinPitch(d);
    }

    @Override // com.mappls.sdk.maps.s
    public void U(boolean z) {
        if (J0("setGestureInProgress")) {
            return;
        }
        nativeSetGestureInProgress(z);
    }

    @Override // com.mappls.sdk.maps.s
    public void V(double d, double d2, double d3, long j) {
        if (J0("setBearing")) {
            return;
        }
        float f = this.f;
        nativeSetBearingXY(d, d2 / f, d3 / f, j);
    }

    @Override // com.mappls.sdk.maps.s
    public double W(double d) {
        if (J0("getMetersPerPixelAtLatitude")) {
            return 0.0d;
        }
        return nativeGetMetersPerPixelAtLatitude(d, C0());
    }

    @Override // com.mappls.sdk.maps.s
    public Light X() {
        if (J0("getLight")) {
            return null;
        }
        return nativeGetLight();
    }

    @Override // com.mappls.sdk.maps.s
    public Bitmap Y(String str) {
        if (J0("getImage")) {
            return null;
        }
        return nativeGetImage(str);
    }

    @Override // com.mappls.sdk.maps.s
    public ProjectedMeters Z(@NonNull LatLng latLng) {
        if (J0("projectedMetersForLatLng")) {
            return null;
        }
        return nativeProjectedMetersForLatLng(latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // com.mappls.sdk.maps.s
    public boolean a(@NonNull Layer layer) {
        if (J0("removeLayer")) {
            return false;
        }
        return nativeRemoveLayer(layer.getNativePtr());
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public long[] a0(RectF rectF) {
        return J0("queryShapeAnnotations") ? new long[0] : nativeQueryShapeAnnotations(rectF);
    }

    @Override // com.mappls.sdk.maps.s
    public void b(@NonNull Polyline polyline) {
        if (J0("updatePolyline")) {
            return;
        }
        nativeUpdatePolyline(polyline.getId(), polyline);
    }

    @Override // com.mappls.sdk.maps.s
    public void b0(int i, int i2) {
        if (J0("resizeView")) {
            return;
        }
        int ceil = (int) Math.ceil(i / this.f);
        int ceil2 = (int) Math.ceil(i2 / this.f);
        if (ceil < 0) {
            Logger.e("Mbgl-NativeMapView", String.format("Device returned a negative width size, setting value to 0 instead of %s", Integer.valueOf(ceil)));
            ceil = 0;
        }
        if (ceil2 < 0) {
            Logger.e("Mbgl-NativeMapView", String.format("Device returned a negative height size, setting value to 0 instead of %s", Integer.valueOf(ceil2)));
            ceil2 = 0;
        }
        if (ceil > 65535) {
            Logger.e("Mbgl-NativeMapView", String.format("Device returned an out of range width size, capping value at 65535 instead of %s", Integer.valueOf(ceil)));
            ceil = 65535;
        }
        if (ceil2 > 65535) {
            Logger.e("Mbgl-NativeMapView", String.format("Device returned an out of range height size, capping value at 65535 instead of %s", Integer.valueOf(ceil2)));
            ceil2 = 65535;
        }
        nativeResizeView(ceil, ceil2);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public TransitionOptions c() {
        return nativeGetTransitionOptions();
    }

    @Override // com.mappls.sdk.maps.s
    public void c0(@NonNull Layer layer) {
        if (J0("addLayer")) {
            return;
        }
        nativeAddLayer(layer.getNativePtr(), null);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public List<Feature> d(@NonNull PointF pointF, @Nullable String[] strArr, @Nullable Expression expression) {
        if (J0("queryRenderedFeatures")) {
            return new ArrayList();
        }
        float f = pointF.x;
        float f2 = this.f;
        Feature[] nativeQueryRenderedFeaturesForPoint = nativeQueryRenderedFeaturesForPoint(f / f2, pointF.y / f2, strArr, expression != null ? expression.toArray() : null);
        return nativeQueryRenderedFeaturesForPoint != null ? Arrays.asList(nativeQueryRenderedFeaturesForPoint) : new ArrayList();
    }

    @Override // com.mappls.sdk.maps.s
    public void d0(@NonNull Layer layer, @NonNull String str) {
        if (J0("addLayerBelow")) {
            return;
        }
        nativeAddLayer(layer.getNativePtr(), str);
    }

    @Override // com.mappls.sdk.maps.s
    public void destroy() {
        this.g = true;
        this.d = null;
        nativeDestroy();
    }

    @Override // com.mappls.sdk.maps.s
    public void e() {
        if (J0("cancelTransitions")) {
            return;
        }
        nativeCancelTransitions();
    }

    @Override // com.mappls.sdk.maps.s
    public long e0(Polyline polyline) {
        if (J0("addPolyline")) {
            return 0L;
        }
        return nativeAddPolylines(new Polyline[]{polyline})[0];
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public List<Source> f() {
        if (J0("getSources")) {
            return new ArrayList();
        }
        return Arrays.asList(nativeGetSources());
    }

    @Override // com.mappls.sdk.maps.s
    public LatLng f0(@NonNull ProjectedMeters projectedMeters) {
        if (J0("latLngForProjectedMeters")) {
            return new LatLng();
        }
        return nativeLatLngForProjectedMeters(projectedMeters.getNorthing(), projectedMeters.getEasting());
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public long[] g(@NonNull List<Polyline> list) {
        return J0("addPolylines") ? new long[0] : nativeAddPolylines((Polyline[]) list.toArray(new Polyline[list.size()]));
    }

    @Override // com.mappls.sdk.maps.s
    public void g0(String str, int i, int i2, float f, byte[] bArr) {
        if (J0("addAnnotationIcon")) {
            return;
        }
        nativeAddAnnotationIcon(str, i, i2, f, bArr);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public CameraPosition getCameraPosition() {
        if (J0("getCameraValues")) {
            return new CameraPosition.Builder().build();
        }
        if (this.h != null) {
            return new CameraPosition.Builder(nativeGetCameraPosition()).padding(this.h).build();
        }
        return nativeGetCameraPosition();
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public List<Layer> getLayers() {
        if (J0("getLayers")) {
            return new ArrayList();
        }
        return Arrays.asList(nativeGetLayers());
    }

    @Override // com.mappls.sdk.maps.s
    public double getMaxZoom() {
        if (J0("getMaxZoom")) {
            return 0.0d;
        }
        return nativeGetMaxZoom();
    }

    @Override // com.mappls.sdk.maps.s
    public double getMinZoom() {
        if (J0("getMinZoom")) {
            return 0.0d;
        }
        return nativeGetMinZoom();
    }

    @Override // com.mappls.sdk.maps.s
    public float getPixelRatio() {
        return this.f;
    }

    @Override // com.mappls.sdk.maps.s
    public void h(@NonNull Layer layer, @IntRange(from = 0) int i) {
        if (J0("addLayerAt")) {
            return;
        }
        nativeAddLayerAt(layer.getNativePtr(), i);
    }

    @Override // com.mappls.sdk.maps.s
    public void h0(@NonNull double[] dArr, @NonNull double[] dArr2) {
        if (J0("pixelsForLatLngs")) {
            return;
        }
        nativePixelsForLatLngs(dArr, dArr2, this.f);
    }

    @Override // com.mappls.sdk.maps.s
    public void i(long j) {
        if (J0("removeAnnotation")) {
            return;
        }
        O(new long[]{j});
    }

    @Override // com.mappls.sdk.maps.s
    public CameraPosition i0(LatLngBounds latLngBounds, int[] iArr, double d, double d2) {
        if (J0("getCameraForLatLngBounds")) {
            return null;
        }
        float f = this.f;
        return nativeGetCameraForLatLngBounds(latLngBounds, iArr[1] / f, iArr[0] / f, iArr[3] / f, iArr[2] / f, d, d2);
    }

    @Override // com.mappls.sdk.maps.s
    public boolean isDestroyed() {
        return this.g;
    }

    @Override // com.mappls.sdk.maps.s
    public double j() {
        if (J0("getMinPitch")) {
            return 0.0d;
        }
        return nativeGetMinPitch();
    }

    @Override // com.mappls.sdk.maps.s
    public String j0() {
        return this.j;
    }

    @Override // com.mappls.sdk.maps.s
    public boolean k(@IntRange(from = 0) int i) {
        if (J0("removeLayerAt")) {
            return false;
        }
        return nativeRemoveLayerAt(i);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public List<Feature> k0(@NonNull RectF rectF, @Nullable String[] strArr, @Nullable Expression expression) {
        if (J0("queryRenderedFeatures")) {
            return new ArrayList();
        }
        float f = rectF.left;
        float f2 = this.f;
        Feature[] nativeQueryRenderedFeaturesForBox = nativeQueryRenderedFeaturesForBox(f / f2, rectF.top / f2, rectF.right / f2, rectF.bottom / f2, strArr, expression != null ? expression.toArray() : null);
        return nativeQueryRenderedFeaturesForBox != null ? Arrays.asList(nativeQueryRenderedFeaturesForBox) : new ArrayList();
    }

    @Override // com.mappls.sdk.maps.s
    public void l(String str) {
        if (J0("setStyleJson")) {
            return;
        }
        nativeSetStyleJson(str);
    }

    @Override // com.mappls.sdk.maps.s
    public void l0(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr, long j, boolean z) {
        if (J0("easeTo")) {
            return;
        }
        nativeEaseTo(d2, latLng.getLatitude(), latLng.getLongitude(), j, d3, d, K0(dArr), z);
    }

    @Override // com.mappls.sdk.maps.s
    public void m(MapplsStyle mapplsStyle) {
        if (J0("setMapplsStyle")) {
            return;
        }
        this.j = mapplsStyle.getName();
        if (MapplsMapConfiguration.getInstance().isShowLastSelectedStyle()) {
            Mappls.getStyleHelper().setSelectedStyle(this.j);
        }
        nativeSetStyleUrl(Mappls.getStyleHelper().getStyleUrl(mapplsStyle));
    }

    @Override // com.mappls.sdk.maps.s
    public void m0(boolean z) {
        if (J0("setPrefetchTiles")) {
            return;
        }
        nativeSetPrefetchTiles(z);
    }

    @Override // com.mappls.sdk.maps.s
    public boolean n(@NonNull Source source) {
        if (J0("removeSource")) {
            return false;
        }
        return nativeRemoveSource(source, source.getNativePtr());
    }

    @Override // com.mappls.sdk.maps.s
    public void n0(@NonNull double[] dArr, @NonNull double[] dArr2) {
        if (J0("latLngsForPixels")) {
            return;
        }
        nativeLatLngsForPixels(dArr, dArr2, this.f);
    }

    @Override // com.mappls.sdk.maps.s
    public Source o(@NonNull String str) {
        if (J0("getSource")) {
            return null;
        }
        return nativeGetSource(str);
    }

    @Override // com.mappls.sdk.maps.s
    public List<MapplsStyle> o0() {
        return Mappls.getStyleHelper().getStyleList();
    }

    @Override // com.mappls.sdk.maps.s
    public void onLowMemory() {
        if (J0("onLowMemory")) {
            return;
        }
        nativeOnLowMemory();
    }

    @Keep
    public void onSnapshotReady(@Nullable Bitmap bitmap) {
        if (J0("OnSnapshotReady")) {
            return;
        }
        try {
            MapplsMap.SnapshotReadyCallback snapshotReadyCallback = this.i;
            if (snapshotReadyCallback == null || bitmap == null) {
                return;
            }
            ViewCallback viewCallback = this.d;
            if (viewCallback == null) {
                snapshotReadyCallback.onSnapshotReady(bitmap);
                return;
            }
            Bitmap viewContent = viewCallback.getViewContent();
            if (viewContent != null) {
                this.i.onSnapshotReady(BitmapUtils.mergeBitmap(bitmap, viewContent));
            }
        } catch (Throwable th) {
            Logger.e("Mbgl-NativeMapView", "Exception in onSnapshotReady", th);
            throw th;
        }
    }

    @Override // com.mappls.sdk.maps.s
    public LatLng p(@NonNull PointF pointF) {
        if (J0("latLngForPixel")) {
            return new LatLng();
        }
        float f = pointF.x;
        float f2 = this.f;
        return nativeLatLngForPixel(f / f2, pointF.y / f2);
    }

    @Override // com.mappls.sdk.maps.s
    @NonNull
    public String p0() {
        return J0("getStyleUri") ? "" : nativeGetStyleUrl();
    }

    @Override // com.mappls.sdk.maps.s
    public void q(@NonNull Source source) {
        if (J0("addSource")) {
            return;
        }
        nativeAddSource(source, source.getNativePtr());
    }

    @Override // com.mappls.sdk.maps.s
    public Layer q0(String str) {
        if (J0("getLayer")) {
            return null;
        }
        return nativeGetLayer(str);
    }

    @Override // com.mappls.sdk.maps.s
    public void r(@NonNull LatLng latLng, double d, double d2, double d3, double[] dArr, long j) {
        if (J0("flyTo")) {
            return;
        }
        nativeFlyTo(d2, latLng.getLatitude(), latLng.getLongitude(), j, d3, d, K0(dArr));
    }

    @Override // com.mappls.sdk.maps.s
    public void r0(String str) {
        if (J0("removeAnnotationIcon")) {
            return;
        }
        nativeRemoveAnnotationIcon(str);
    }

    @Override // com.mappls.sdk.maps.s
    public void s(double d) {
        if (J0("setMaxZoom")) {
            return;
        }
        nativeSetMaxZoom(d);
    }

    @Override // com.mappls.sdk.maps.s
    public boolean s0(@NonNull String str) {
        Layer q0;
        if (J0("removeLayer") || (q0 = q0(str)) == null) {
            return false;
        }
        return a(q0);
    }

    @Override // com.mappls.sdk.maps.s
    public void setDebug(boolean z) {
        if (J0("setDebug")) {
            return;
        }
        nativeSetDebug(z);
    }

    @Override // com.mappls.sdk.maps.s
    public CameraPosition t(Geometry geometry, int[] iArr, double d, double d2) {
        if (J0("getCameraForGeometry")) {
            return null;
        }
        float f = this.f;
        return nativeGetCameraForGeometry(geometry, iArr[1] / f, iArr[0] / f, iArr[3] / f, iArr[2] / f, d, d2);
    }

    @Override // com.mappls.sdk.maps.s
    public boolean t0() {
        if (J0("getDebug")) {
            return false;
        }
        return nativeGetDebug();
    }

    @Override // com.mappls.sdk.maps.s
    public void u(String str) {
        if (J0("setApiBaseUrl")) {
            return;
        }
        this.f12652a.setApiBaseUrl(str);
    }

    @Override // com.mappls.sdk.maps.s
    public void u0(@NonNull double[] dArr) {
        if (J0("getVisibleCoordinateBounds")) {
            return;
        }
        nativeGetVisibleCoordinateBounds(dArr);
    }

    @Override // com.mappls.sdk.maps.s
    public double v(String str) {
        if (J0("getTopOffsetPixelsForAnnotationSymbol")) {
            return 0.0d;
        }
        return nativeGetTopOffsetPixelsForAnnotationSymbol(str);
    }

    @Override // com.mappls.sdk.maps.s
    public double v0() {
        if (J0("getMaxPitch")) {
            return 0.0d;
        }
        return nativeGetMaxPitch();
    }

    @Override // com.mappls.sdk.maps.s
    @IntRange(from = 0)
    public int w() {
        if (J0("nativeGetPrefetchZoomDelta")) {
            return 0;
        }
        return nativeGetPrefetchZoomDelta();
    }

    @Override // com.mappls.sdk.maps.s
    public long w0(Marker marker) {
        if (J0("addMarker")) {
            return 0L;
        }
        return nativeAddMarkers(new Marker[]{marker})[0];
    }

    @Override // com.mappls.sdk.maps.s
    public void x() {
        nativeTriggerRepaint();
    }

    @Override // com.mappls.sdk.maps.s
    public void x0(@Nullable MapplsMap.OnFpsChangedListener onFpsChangedListener) {
        this.b.queueEvent(new a(onFpsChangedListener, new Handler()));
    }

    @Override // com.mappls.sdk.maps.s
    public void y(double d) {
        if (J0("setMaxPitch")) {
            return;
        }
        nativeSetMaxPitch(d);
    }

    @Override // com.mappls.sdk.maps.s
    public void y0(@NonNull TransitionOptions transitionOptions) {
        nativeSetTransitionOptions(transitionOptions);
    }

    @Override // com.mappls.sdk.maps.s
    public void z(@NonNull MapplsMap.SnapshotReadyCallback snapshotReadyCallback) {
        if (J0("addSnapshotCallback")) {
            return;
        }
        this.i = snapshotReadyCallback;
        nativeTakeSnapshot();
    }

    @Override // com.mappls.sdk.maps.s
    public void z0(double d) {
        if (J0("setMinZoom")) {
            return;
        }
        nativeSetMinZoom(d);
    }
}
