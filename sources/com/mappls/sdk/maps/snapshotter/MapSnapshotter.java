package com.mappls.sdk.maps.snapshotter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.content.res.ResourcesCompat;
import com.mappls.sdk.maps.Image;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.attribution.AttributionLayout;
import com.mappls.sdk.maps.attribution.AttributionMeasure;
import com.mappls.sdk.maps.attribution.AttributionParser;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.log.Logger;
import com.mappls.sdk.maps.storage.FileSource;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.sources.Source;
import com.mappls.sdk.maps.utils.FontUtils;
import com.mappls.sdk.maps.utils.ThreadUtils;
@UiThread
/* loaded from: classes11.dex */
public class MapSnapshotter {

    /* renamed from: a  reason: collision with root package name */
    public final Context f12837a;
    public Options c;
    @Nullable
    public SnapshotReadyCallback d;
    @Nullable
    public ErrorHandler e;
    @Nullable
    public Observer f;
    @Keep
    private long nativePtr = 0;
    public boolean b = false;

    /* loaded from: classes11.dex */
    public interface ErrorHandler {
        void onError(String str);
    }

    /* loaded from: classes11.dex */
    public interface Observer {
        void onDidFinishLoadingStyle();

        void onStyleImageMissing(String str);
    }

    /* loaded from: classes11.dex */
    public static class Options {
        public int b;
        public int c;
        public LatLngBounds d;
        public CameraPosition e;
        public String h;
        public Style.Builder i;

        /* renamed from: a  reason: collision with root package name */
        public float f12838a = 1.0f;
        public boolean f = true;
        public String g = MapplsConstants.DEFAULT_FONT;

        public Options(int i, int i2) {
            if (i != 0 && i2 != 0) {
                this.b = i;
                this.c = i2;
                return;
            }
            throw new IllegalArgumentException("Unable to create a snapshot with width or height set to 0");
        }

        @Nullable
        public String getApiBaseUri() {
            return this.h;
        }

        @Nullable
        @Deprecated
        public String getApiBaseUrl() {
            return this.h;
        }

        public Style.Builder getBuilder() {
            return this.i;
        }

        @Nullable
        public CameraPosition getCameraPosition() {
            return this.e;
        }

        public int getHeight() {
            return this.c;
        }

        public String getLocalIdeographFontFamily() {
            return this.g;
        }

        public String getMapplsStyle() {
            Style.Builder builder = this.i;
            if (builder == null) {
                return null;
            }
            return builder.getMapplsStyle();
        }

        public float getPixelRatio() {
            return this.f12838a;
        }

        @Nullable
        public LatLngBounds getRegion() {
            return this.d;
        }

        @Nullable
        public String getStyleJson() {
            Style.Builder builder = this.i;
            if (builder == null) {
                return null;
            }
            return builder.getJson();
        }

        public int getWidth() {
            return this.b;
        }

        @NonNull
        public Options withApiBaseUri(String str) {
            this.h = str;
            return this;
        }

        @NonNull
        @Deprecated
        public Options withApiBaseUrl(String str) {
            this.h = str;
            return this;
        }

        @NonNull
        public Options withCameraPosition(CameraPosition cameraPosition) {
            this.e = cameraPosition;
            return this;
        }

        @NonNull
        public Options withLocalIdeographFontFamily(String str) {
            this.g = FontUtils.extractValidFont(str);
            return this;
        }

        @NonNull
        public Options withLogo(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        public Options withMapplsStyle(String str) {
            withStyleBuilder(new Style.Builder().fromMapplsStyle(str));
            return this;
        }

        @NonNull
        public Options withPixelRatio(float f) {
            this.f12838a = f;
            return this;
        }

        @NonNull
        public Options withRegion(LatLngBounds latLngBounds) {
            this.d = latLngBounds;
            return this;
        }

        @NonNull
        public Options withStyleBuilder(Style.Builder builder) {
            this.i = builder;
            return this;
        }

        @NonNull
        public Options withStyleJson(String str) {
            withStyleBuilder(new Style.Builder().fromJson(str));
            return this;
        }

        @NonNull
        public Options withLocalIdeographFontFamily(String... strArr) {
            this.g = FontUtils.extractValidFont(strArr);
            return this;
        }
    }

    /* loaded from: classes11.dex */
    public interface SnapshotReadyCallback {
        void onSnapshotReady(MapSnapshot mapSnapshot);
    }

    /* loaded from: classes11.dex */
    public class a implements Runnable {
        public final /* synthetic */ MapSnapshot h;

        public a(MapSnapshot mapSnapshot) {
            this.h = mapSnapshot;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MapSnapshotter.this.d != null) {
                MapSnapshotter.this.addOverlay(this.h);
                MapSnapshotter.this.d.onSnapshotReady(this.h);
                MapSnapshotter.this.reset();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Runnable {
        public final /* synthetic */ String h;

        public b(String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MapSnapshotter.this.e != null) {
                MapSnapshotter.this.e.onError(this.h);
                MapSnapshotter.this.reset();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f12839a;
        public Bitmap b;
        public float c;

        public c(Bitmap bitmap, Bitmap bitmap2, float f) {
            this.f12839a = bitmap;
            this.b = bitmap2;
            this.c = f;
        }

        public Bitmap a() {
            return this.f12839a;
        }

        public float b() {
            return this.c;
        }

        public Bitmap c() {
            return this.b;
        }
    }

    public MapSnapshotter(@NonNull Context context, @NonNull Options options) {
        g();
        this.f12837a = context.getApplicationContext();
        this.c = options;
        FileSource fileSource = FileSource.getInstance(context);
        String apiBaseUrl = options.getApiBaseUrl();
        if (!TextUtils.isEmpty(apiBaseUrl)) {
            fileSource.setApiBaseUrl(apiBaseUrl);
        }
        nativeInitialize(this, fileSource, options.f12838a, options.b, options.c, options.getMapplsStyle(), options.getStyleJson(), options.d, options.e, options.f, options.g);
    }

    @Keep
    private native void nativeAddImages(Image[] imageArr);

    @Keep
    private native void nativeAddLayerAbove(long j, String str);

    @Keep
    private native void nativeAddLayerAt(long j, int i);

    @Keep
    private native void nativeAddLayerBelow(long j, String str);

    @Keep
    private native void nativeAddSource(Source source, long j);

    @NonNull
    @Keep
    private native Layer nativeGetLayer(String str);

    @NonNull
    @Keep
    private native Source nativeGetSource(String str);

    public void addImage(@NonNull String str, @NonNull Bitmap bitmap, boolean z) {
        nativeAddImages(new Image[]{Style.toImage(new Style.Builder.ImageWrapper(str, bitmap, z))});
    }

    public void addOverlay(@NonNull MapSnapshot mapSnapshot) {
        Bitmap bitmap = mapSnapshot.getBitmap();
        o(mapSnapshot, bitmap, new Canvas(bitmap), ((int) this.f12837a.getResources().getDisplayMetrics().density) * 4);
    }

    public final void c(@NonNull Layer layer, @NonNull String str) {
        nativeAddLayerAbove(layer.getNativePtr(), str);
    }

    public void cancel() {
        g();
        reset();
        nativeCancel();
    }

    public final void d(Layer layer, int i) {
        nativeAddLayerAt(layer.getNativePtr(), i);
    }

    public final void e(Layer layer, String str) {
        nativeAddLayerBelow(layer.getNativePtr(), str);
    }

    public final float f(Bitmap bitmap, Bitmap bitmap2) {
        DisplayMetrics displayMetrics;
        float min = Math.min((bitmap2.getWidth() / (displayMetrics.widthPixels / bitmap.getWidth())) / bitmap2.getWidth(), (bitmap2.getHeight() / (this.f12837a.getResources().getDisplayMetrics().heightPixels / bitmap.getHeight())) / bitmap2.getHeight()) * 2.0f;
        if (min > 1.0f) {
            return 1.0f;
        }
        if (min < 0.6f) {
            return 0.6f;
        }
        return min;
    }

    @Keep
    public native void finalize() throws Throwable;

    public final void g() {
        ThreadUtils.checkThread("Mbgl-MapSnapshotter");
    }

    @Nullable
    public Layer getLayer(@NonNull String str) {
        g();
        if (this.b) {
            return nativeGetLayer(str);
        }
        return null;
    }

    @Nullable
    public Source getSource(@NonNull String str) {
        g();
        if (this.b) {
            return nativeGetSource(str);
        }
        return null;
    }

    @NonNull
    public final String h(MapSnapshot mapSnapshot, boolean z) {
        return new AttributionParser.Options(this.f12837a).withAttributionData(mapSnapshot.getAttributions()).withCopyrightSign(false).withImproveMap(false).build().createAttributionString(z);
    }

    public final c i(@NonNull Bitmap bitmap) {
        Resources resources = this.f12837a.getResources();
        int i = R.drawable.mappls_maps_logo_icon;
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, null);
        float f = f(bitmap, decodeResource);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(this.f12837a.getResources(), i, null);
        return new c(Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true), Bitmap.createBitmap(decodeResource2, 0, 0, decodeResource2.getWidth(), decodeResource2.getHeight(), matrix, true), f);
    }

    @NonNull
    public final TextView j(@NonNull MapSnapshot mapSnapshot, boolean z, float f) {
        int color = ResourcesCompat.getColor(this.f12837a.getResources(), R.color.mappls_maps_gray_dark, this.f12837a.getTheme());
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        TextView textView = new TextView(this.f12837a);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setSingleLine(true);
        textView.setTextSize(f * 10.0f);
        textView.setTextColor(color);
        textView.setBackgroundResource(R.drawable.mappls_maps_rounded_corner);
        textView.setText(Html.fromHtml(h(mapSnapshot, z)));
        textView.measure(makeMeasureSpec, makeMeasureSpec2);
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        return textView;
    }

    public final void k(Canvas canvas, AttributionMeasure attributionMeasure, PointF pointF) {
        canvas.save();
        canvas.translate(pointF.x, pointF.y);
        attributionMeasure.getTextView().draw(canvas);
        canvas.restore();
    }

    public final void l(@NonNull MapSnapshot mapSnapshot, @NonNull Canvas canvas, @NonNull AttributionMeasure attributionMeasure, AttributionLayout attributionLayout) {
        PointF anchorPoint = attributionLayout.getAnchorPoint();
        if (anchorPoint != null) {
            k(canvas, attributionMeasure, anchorPoint);
            return;
        }
        Bitmap bitmap = mapSnapshot.getBitmap();
        Logger.e("Mbgl-MapSnapshotter", String.format("Could not generate attribution for snapshot size: %s x %s. You are required to provide your own attribution for the used sources: %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), mapSnapshot.getAttributions()));
    }

    public final void m(@NonNull Bitmap bitmap, @NonNull Canvas canvas, int i, AttributionLayout attributionLayout) {
        Bitmap logo = attributionLayout.getLogo();
        if (logo != null) {
            canvas.drawBitmap(logo, i, (bitmap.getHeight() - logo.getHeight()) - i, (Paint) null);
        }
    }

    public final void n(MapSnapshot mapSnapshot, @NonNull Canvas canvas, int i, @NonNull AttributionLayout attributionLayout) {
        if (mapSnapshot.a()) {
            m(mapSnapshot.getBitmap(), canvas, i, attributionLayout);
        }
    }

    @Keep
    public native void nativeCancel();

    @Keep
    public native void nativeInitialize(MapSnapshotter mapSnapshotter, FileSource fileSource, float f, int i, int i2, String str, String str2, LatLngBounds latLngBounds, CameraPosition cameraPosition, boolean z, String str3);

    @Keep
    public native void nativeStart();

    public final void o(@NonNull MapSnapshot mapSnapshot, @NonNull Bitmap bitmap, @NonNull Canvas canvas, int i) {
        AttributionMeasure p = p(mapSnapshot, bitmap, i);
        AttributionLayout measure = p.measure();
        n(mapSnapshot, canvas, i, measure);
        l(mapSnapshot, canvas, p, measure);
    }

    @Keep
    public void onDidFailLoadingStyle(String str) {
        onSnapshotFailed(str);
    }

    @Keep
    public void onDidFinishLoadingStyle() {
        if (!this.b) {
            this.b = true;
            Style.Builder builder = this.c.getBuilder();
            if (builder != null) {
                for (Source source : builder.getSources()) {
                    nativeAddSource(source, source.getNativePtr());
                }
                for (Style.Builder.LayerWrapper layerWrapper : builder.getLayers()) {
                    if (layerWrapper instanceof Style.Builder.LayerAtWrapper) {
                        d(layerWrapper.getLayer(), ((Style.Builder.LayerAtWrapper) layerWrapper).getIndex());
                    } else if (layerWrapper instanceof Style.Builder.LayerAboveWrapper) {
                        c(layerWrapper.getLayer(), ((Style.Builder.LayerAboveWrapper) layerWrapper).getAboveLayer());
                    } else if (layerWrapper instanceof Style.Builder.LayerBelowWrapper) {
                        e(layerWrapper.getLayer(), ((Style.Builder.LayerBelowWrapper) layerWrapper).getBelowLayer());
                    } else {
                        e(layerWrapper.getLayer(), MapplsConstants.LAYER_ID_ANNOTATIONS);
                    }
                }
                for (Style.Builder.ImageWrapper imageWrapper : builder.getImages()) {
                    addImage(imageWrapper.getId(), imageWrapper.getBitmap(), imageWrapper.isSdf());
                }
            }
        }
        Observer observer = this.f;
        if (observer != null) {
            observer.onDidFinishLoadingStyle();
        }
    }

    @Keep
    public void onSnapshotFailed(String str) {
        new Handler().post(new b(str));
    }

    @Keep
    public void onSnapshotReady(@NonNull MapSnapshot mapSnapshot) {
        new Handler().post(new a(mapSnapshot));
    }

    @Keep
    public void onStyleImageMissing(String str) {
        Observer observer = this.f;
        if (observer != null) {
            observer.onStyleImageMissing(str);
        }
    }

    @NonNull
    public final AttributionMeasure p(@NonNull MapSnapshot mapSnapshot, @NonNull Bitmap bitmap, int i) {
        c i2 = i(bitmap);
        TextView j = j(mapSnapshot, false, i2.b());
        return new AttributionMeasure.Builder().setSnapshot(bitmap).setLogo(i2.a()).setLogoSmall(i2.c()).setTextView(j).setTextViewShort(j(mapSnapshot, true, i2.b())).setMarginPadding(i).build();
    }

    public void reset() {
        this.d = null;
        this.e = null;
    }

    @Keep
    public native void setCameraPosition(CameraPosition cameraPosition);

    public void setObserver(@Nullable Observer observer) {
        g();
        this.f = observer;
    }

    @Keep
    public native void setRegion(LatLngBounds latLngBounds);

    @Keep
    public native void setSize(int i, int i2);

    @Keep
    public native void setStyleJson(String str);

    @Keep
    public native void setStyleUrl(String str);

    public void start(@NonNull SnapshotReadyCallback snapshotReadyCallback) {
        start(snapshotReadyCallback, null);
    }

    public void start(@NonNull SnapshotReadyCallback snapshotReadyCallback, ErrorHandler errorHandler) {
        if (this.d == null) {
            g();
            this.d = snapshotReadyCallback;
            this.e = errorHandler;
            nativeStart();
            return;
        }
        throw new IllegalStateException("Snapshotter was already started");
    }
}
