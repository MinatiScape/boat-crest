package com.mappls.sdk.maps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.internal.LinkedTreeMap;
import com.mappls.android.lms.MapplsLMSManager;
import com.mappls.sdk.gestures.AndroidGesturesManager;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.NativeMapView;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.attribution.AttributionView;
import com.mappls.sdk.maps.auth.MapplsVectorKey;
import com.mappls.sdk.maps.auth.model.PublicKeyToken;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.covid.RasterPlugin;
import com.mappls.sdk.maps.covid.SafetyStripView;
import com.mappls.sdk.maps.exceptions.MapplsConfigurationException;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.location.LocationComponent;
import com.mappls.sdk.maps.module.http.MapplsMapManager;
import com.mappls.sdk.maps.net.ConnectivityReceiver;
import com.mappls.sdk.maps.promo.MapplsPromo;
import com.mappls.sdk.maps.promo.model.HyperlinkContent;
import com.mappls.sdk.maps.promo.model.Promo;
import com.mappls.sdk.maps.renderer.MapRenderer;
import com.mappls.sdk.maps.renderer.glsurfaceview.GLSurfaceViewMapRenderer;
import com.mappls.sdk.maps.renderer.glsurfaceview.MapplsGLSurfaceView;
import com.mappls.sdk.maps.renderer.textureview.TextureViewMapRenderer;
import com.mappls.sdk.maps.session.AuthenticationError;
import com.mappls.sdk.maps.session.IStopSession;
import com.mappls.sdk.maps.session.InitializationListener;
import com.mappls.sdk.maps.storage.FileSource;
import com.mappls.sdk.maps.style.IStyleListener;
import com.mappls.sdk.maps.utils.BitmapUtils;
import com.mappls.sdk.maps.widgets.CompassView;
import com.mappls.sdk.maps.widgets.LogoView;
import com.mappls.sdk.maps.widgets.indoor.FloorControllerView;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.services.account.MapplsAccountManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;
/* loaded from: classes11.dex */
public class MapView extends FrameLayout implements NativeMapView.ViewCallback {
    @Nullable
    public com.mappls.sdk.maps.m A;
    @Nullable
    public Bundle B;
    public boolean C;
    public View D;
    public Button E;
    public TextView F;
    public TextView G;
    public View H;
    public LogoView I;
    public FloorControllerView J;
    public SafetyStripView K;
    public ImageView L;
    public AuthenticationError M;
    public final com.mappls.sdk.maps.k h;
    public final q i;
    public final p j;
    public final n k;
    public final o l;
    public final com.mappls.sdk.maps.g m;
    public boolean n;
    public MapplsMapOptions o;
    public boolean p;
    public boolean q;
    @Nullable
    public s r;
    @Nullable
    public MapplsMap s;
    public View t;
    public m u;
    public MapRenderer v;
    public boolean w;
    @Nullable
    public CompassView x;
    public PointF y;
    @Nullable
    public com.mappls.sdk.maps.l z;

    /* loaded from: classes11.dex */
    public interface OnCameraDidChangeListener {
        void onCameraDidChange(boolean z);
    }

    /* loaded from: classes11.dex */
    public interface OnCameraIsChangingListener {
        void onCameraIsChanging();
    }

    /* loaded from: classes11.dex */
    public interface OnCameraWillChangeListener {
        void onCameraWillChange(boolean z);
    }

    /* loaded from: classes11.dex */
    public interface OnCanRemoveUnusedStyleImageListener {
        boolean onCanRemoveUnusedStyleImage(@NonNull String str);
    }

    /* loaded from: classes11.dex */
    public interface OnDidBecomeIdleListener {
        void onDidBecomeIdle();
    }

    /* loaded from: classes11.dex */
    public interface OnDidFailLoadingMapListener {
        void onDidFailLoadingMap(String str);
    }

    /* loaded from: classes11.dex */
    public interface OnDidFinishLoadingMapListener {
        void onDidFinishLoadingMap();
    }

    /* loaded from: classes11.dex */
    public interface OnDidFinishLoadingStyleListener {
        void onDidFinishLoadingStyle();
    }

    /* loaded from: classes11.dex */
    public interface OnDidFinishRenderingFrameListener {
        void onDidFinishRenderingFrame(boolean z);
    }

    /* loaded from: classes11.dex */
    public interface OnDidFinishRenderingMapListener {
        void onDidFinishRenderingMap(boolean z);
    }

    /* loaded from: classes11.dex */
    public interface OnSourceChangedListener {
        void onSourceChangedListener(String str);
    }

    /* loaded from: classes11.dex */
    public interface OnStyleImageMissingListener {
        void onStyleImageMissing(@NonNull String str);
    }

    /* loaded from: classes11.dex */
    public interface OnWillStartLoadingMapListener {
        void onWillStartLoadingMap();
    }

    /* loaded from: classes11.dex */
    public interface OnWillStartRenderingFrameListener {
        void onWillStartRenderingFrame();
    }

    /* loaded from: classes11.dex */
    public interface OnWillStartRenderingMapListener {
        void onWillStartRenderingMap();
    }

    /* loaded from: classes11.dex */
    public class a implements Callback<List<Promo>> {

        /* renamed from: com.mappls.sdk.maps.MapView$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public class View$OnClickListenerC0620a implements View.OnClickListener {
            public final /* synthetic */ String h;

            public View$OnClickListenerC0620a(String str) {
                this.h = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MapView.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.h)));
            }
        }

        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(@NonNull Call<List<Promo>> call, @NonNull Throwable th) {
            if (MapView.this.L != null) {
                MapView.this.L.setVisibility(8);
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(@NonNull Call<List<Promo>> call, @NonNull Response<List<Promo>> response) {
            if (response.code() == 200) {
                if (response.body().size() <= 0) {
                    if (MapView.this.L != null) {
                        MapView.this.L.setVisibility(8);
                        return;
                    }
                    return;
                }
                for (Promo promo : response.body()) {
                    if (promo.getContentType().equalsIgnoreCase("Hyperlink")) {
                        LinkedTreeMap linkedTreeMap = (LinkedTreeMap) promo.getContent();
                        String obj = linkedTreeMap.containsKey("title") ? linkedTreeMap.get("title").toString() : null;
                        if (obj != null && obj.equalsIgnoreCase("COVID-19")) {
                            String obj2 = linkedTreeMap.get("iconSource").toString();
                            String obj3 = linkedTreeMap.get("triggerUrl").toString();
                            String obj4 = linkedTreeMap.get(SavingTrackHelper.POINT_COL_DESCRIPTION).toString();
                            HyperlinkContent hyperlinkContent = new HyperlinkContent();
                            hyperlinkContent.setTitle(obj);
                            hyperlinkContent.setIconSource(obj2);
                            hyperlinkContent.setTriggerUrl(obj3);
                            hyperlinkContent.setDescription(obj4);
                            if (MapView.this.L != null) {
                                MapView.this.L.setVisibility(0);
                                MapView.this.L.setOnClickListener(new View$OnClickListenerC0620a(obj3));
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements IStyleListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f12632a;
        public final /* synthetic */ MapplsMapOptions b;

        public b(Context context, MapplsMapOptions mapplsMapOptions) {
            this.f12632a = context;
            this.b = mapplsMapOptions;
        }

        @Override // com.mappls.sdk.maps.style.IStyleListener
        public void onFailure(int i, String str) {
            MapView.this.z(i, str);
        }

        @Override // com.mappls.sdk.maps.style.IStyleListener
        public void onSuccess() {
            if (!ConnectivityReceiver.instance(this.f12632a).isOffline()) {
                MapView.this.w(this.f12632a, this.b);
            } else {
                MapView.this.initialize(this.f12632a, this.b);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements Callback<PublicKeyToken> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f12633a;
        public final /* synthetic */ MapplsMapOptions b;

        public c(Context context, MapplsMapOptions mapplsMapOptions) {
            this.f12633a = context;
            this.b = mapplsMapOptions;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<PublicKeyToken> call, Throwable th) {
            MapView.this.z(5, "Something went wrong - 102");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<PublicKeyToken> call, Response<PublicKeyToken> response) {
            if (response.code() == 200 && response.body() != null) {
                MapplsMapManager.getInstance().setRawPublicKey(response.body().getPublicKey());
                MapplsMapManager.getInstance().setKeyExpirationTime(response.body().getExpiresOn());
                MapView.this.B(this.f12633a, this.b);
            } else if (response.code() == 7 || response.code() == 8 || response.code() == 9 || response.code() == 101 || response.code() == 102 || response.code() == 103) {
                MapView.this.z(response.code(), response.message());
            } else if (response.headers().get(Constants.KEY_MESSAGE) == null) {
                MapView.this.z(response.code(), "Something went wrong - 102");
            } else {
                MapView mapView = MapView.this;
                int code = response.code();
                mapView.z(code, response.headers().get(Constants.KEY_MESSAGE) + " - 102");
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements View.OnClickListener {
        public final /* synthetic */ View h;
        public final /* synthetic */ Context i;
        public final /* synthetic */ MapplsMapOptions j;

        public d(View view, Context context, MapplsMapOptions mapplsMapOptions) {
            this.h = view;
            this.i = context;
            this.j = mapplsMapOptions;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MapplsLMSManager.isInitialised()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_view", "retry");
                    if (MapView.this.M != null) {
                        jSONObject.put("error_code", MapView.this.M.errorCode);
                        jSONObject.put("error_message", MapView.this.M.errorMessage);
                    }
                    MapplsLMSManager.getInstance().add("click", BuildConfig.MAPPLS_SDK_NAME, BuildConfig.MAPPLS_SDK_VERSION, jSONObject);
                } catch (JSONException unused) {
                }
            }
            MapView.this.n(this.h, this.i, this.j);
        }
    }

    /* loaded from: classes11.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MapView.this.s == null || MapView.this.s.getUiSettings() == null || !MapView.this.s.getUiSettings().isEnableLogoClick()) {
                return;
            }
            if (MapplsLMSManager.isInitialised()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_view", "logo");
                    MapplsLMSManager.getInstance().add("click", BuildConfig.MAPPLS_SDK_NAME, BuildConfig.MAPPLS_SDK_VERSION, jSONObject);
                } catch (JSONException unused) {
                }
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://about.mappls.com/"));
                MapView.this.getContext().startActivity(intent);
            } catch (ActivityNotFoundException unused2) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public class f implements FocalPointChangeListener {
        public f() {
        }

        @Override // com.mappls.sdk.maps.FocalPointChangeListener
        public void onFocalPointChanged(PointF pointF) {
            MapView.this.y = pointF;
        }
    }

    /* loaded from: classes11.dex */
    public class g implements MapplsMap.OnCompassAnimationListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ com.mappls.sdk.maps.g f12635a;

        public g(com.mappls.sdk.maps.g gVar) {
            this.f12635a = gVar;
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnCompassAnimationListener
        public void onCompassAnimation() {
            this.f12635a.onCameraMove();
        }

        @Override // com.mappls.sdk.maps.MapplsMap.OnCompassAnimationListener
        public void onCompassAnimationFinished() {
            if (MapView.this.x != null) {
                MapView.this.x.isAnimating(false);
            }
            this.f12635a.onCameraIdle();
        }
    }

    /* loaded from: classes11.dex */
    public class h implements View.OnClickListener {
        public final /* synthetic */ com.mappls.sdk.maps.g h;

        public h(com.mappls.sdk.maps.g gVar) {
            this.h = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MapView.this.s == null || MapView.this.x == null) {
                return;
            }
            double d = MapView.this.s.getCameraPosition().bearing;
            if (MapView.this.y != null) {
                MapView.this.s.setFocalBearing(0.0d, MapView.this.y.x, MapView.this.y.y, 150L);
            } else {
                MapView.this.s.setFocalBearing(0.0d, MapView.this.s.getWidth() / 2.0f, MapView.this.s.getHeight() / 2.0f, 150L);
            }
            this.h.onCameraMoveStarted(3);
            MapView.this.x.isAnimating(true);
            MapView.this.x.postDelayed(MapView.this.x, 650L);
            if (MapplsLMSManager.isInitialised()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_view", "compass");
                    jSONObject.put("bearing", d);
                    MapplsLMSManager.getInstance().add("click", BuildConfig.MAPPLS_SDK_NAME, BuildConfig.MAPPLS_SDK_VERSION, jSONObject);
                } catch (JSONException unused) {
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class i extends TextureViewMapRenderer {
        public i(Context context, TextureView textureView, String str, boolean z) {
            super(context, textureView, str, z);
        }

        @Override // com.mappls.sdk.maps.renderer.textureview.TextureViewMapRenderer, com.mappls.sdk.maps.renderer.MapRenderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            MapView mapView = MapView.this;
            if (mapView.q) {
                mapView.A();
            }
            super.onSurfaceCreated(gl10, eGLConfig);
            MapView.this.p = true;
        }
    }

    /* loaded from: classes11.dex */
    public class j extends GLSurfaceViewMapRenderer {
        public j(Context context, MapplsGLSurfaceView mapplsGLSurfaceView, String str) {
            super(context, mapplsGLSurfaceView, str);
        }

        @Override // com.mappls.sdk.maps.renderer.glsurfaceview.GLSurfaceViewMapRenderer, com.mappls.sdk.maps.renderer.MapRenderer
        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            MapView mapView = MapView.this;
            if (mapView.q) {
                mapView.A();
            }
            super.onSurfaceCreated(gl10, eGLConfig);
            MapView.this.p = true;
        }
    }

    /* loaded from: classes11.dex */
    public class k implements Runnable {
        public k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MapView.this.w || MapView.this.s != null) {
                return;
            }
            MapView.this.u();
            MapView.this.s.w();
        }
    }

    /* loaded from: classes11.dex */
    public class l implements InitializationListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f12636a;
        public final /* synthetic */ MapplsMapOptions b;

        /* loaded from: classes11.dex */
        public class a implements IStopSession {
            public a() {
            }

            @Override // com.mappls.sdk.maps.session.IStopSession
            public void onFailure() {
                MapView.this.z(5, "Something went wrong - 104");
            }

            @Override // com.mappls.sdk.maps.session.IStopSession
            public void onSuccess() {
                l lVar = l.this;
                MapView.this.B(lVar.f12636a, lVar.b);
            }
        }

        public l(Context context, MapplsMapOptions mapplsMapOptions) {
            this.f12636a = context;
            this.b = mapplsMapOptions;
        }

        @Override // com.mappls.sdk.maps.session.InitializationListener
        public void onFailure(AuthenticationError authenticationError, Exception exc) {
            MapView mapView = MapView.this;
            mapView.n = false;
            int i = authenticationError.errorCode;
            if (i == 409) {
                Mappls.getSessionHelper().deleteNavigationSession(new a());
            } else if (i > 399 && i < 500) {
                mapView.z(i, authenticationError.errorMessage + " - 103");
            } else if (i == 101 || i == 102 || i == 103 || i == 7 || i == 8 || i == 9) {
                mapView.z(i, authenticationError.errorMessage);
            } else {
                mapView.z(5, "Something went wrong - 103");
            }
        }

        @Override // com.mappls.sdk.maps.session.InitializationListener
        public void onSuccess() {
            MapView mapView = MapView.this;
            mapView.n = true;
            mapView.initialize(this.f12636a, this.b);
        }
    }

    /* loaded from: classes11.dex */
    public static class m implements View.OnClickListener {
        @NonNull
        public final AttributionDialogManager h;
        public UiSettings i;

        public /* synthetic */ m(Context context, MapplsMap mapplsMap, a aVar) {
            this(context, mapplsMap);
        }

        public final AttributionDialogManager a() {
            if (this.i.getAttributionDialogManager() != null) {
                return this.i.getAttributionDialogManager();
            }
            return this.h;
        }

        public void b() {
            a().onStop();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a().onClick(view);
            if (MapplsLMSManager.isInitialised()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("event_view", "attribution");
                    MapplsLMSManager.getInstance().add("click", BuildConfig.MAPPLS_SDK_NAME, BuildConfig.MAPPLS_SDK_VERSION, jSONObject);
                } catch (JSONException unused) {
                }
            }
        }

        public m(@NonNull Context context, @NonNull MapplsMap mapplsMap) {
            this.h = new AttributionDialogManager(context, mapplsMap);
            this.i = mapplsMap.getUiSettings();
        }
    }

    /* loaded from: classes11.dex */
    public class o implements MapplsMap.g {
        public o() {
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void a(MapplsMap.OnRotateListener onRotateListener) {
            MapView.this.z.f0(onRotateListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void b(MapplsMap.OnShoveListener onShoveListener) {
            MapView.this.z.h0(onShoveListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void c(MapplsMap.OnScaleListener onScaleListener) {
            MapView.this.z.g0(onScaleListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void d(MapplsMap.OnMapClickListener onMapClickListener) {
            MapView.this.z.c0(onMapClickListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void e(MapplsMap.OnMapLongClickListener onMapLongClickListener) {
            MapView.this.z.d0(onMapLongClickListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void f(MapplsMap.OnMapLongClickListener onMapLongClickListener) {
            MapView.this.z.u(onMapLongClickListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void g(AndroidGesturesManager androidGesturesManager, boolean z, boolean z2) {
            MapView.this.z.k0(MapView.this.getContext(), androidGesturesManager, z, z2);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public AndroidGesturesManager h() {
            return MapView.this.z.G();
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void i(MapplsMap.OnFlingListener onFlingListener) {
            MapView.this.z.b0(onFlingListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void j(MapplsMap.OnRotateListener onRotateListener) {
            MapView.this.z.w(onRotateListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void k(MapplsMap.OnMapClickListener onMapClickListener) {
            MapView.this.z.t(onMapClickListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void l(MapplsMap.OnFlingListener onFlingListener) {
            MapView.this.z.s(onFlingListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void m(MapplsMap.OnMoveListener onMoveListener) {
            MapView.this.z.v(onMoveListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void n(MapplsMap.OnScaleListener onScaleListener) {
            MapView.this.z.x(onScaleListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void o(MapplsMap.OnShoveListener onShoveListener) {
            MapView.this.z.y(onShoveListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void p(MapplsMap.OnMoveListener onMoveListener) {
            MapView.this.z.e0(onMoveListener);
        }

        @Override // com.mappls.sdk.maps.MapplsMap.g
        public void q() {
            MapView.this.z.A();
        }

        public /* synthetic */ o(MapView mapView, a aVar) {
            this();
        }
    }

    /* loaded from: classes11.dex */
    public class p implements OnDidFinishRenderingFrameListener {
        public int h;

        public p() {
            MapView.this.addOnDidFinishRenderingFrameListener(this);
        }

        public final void b() {
            MapView.this.removeOnDidFinishRenderingFrameListener(this);
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFinishRenderingFrameListener
        public void onDidFinishRenderingFrame(boolean z) {
            if (MapView.this.s == null || MapView.this.s.getStyle() == null || !MapView.this.s.getStyle().isFullyLoaded()) {
                return;
            }
            int i = this.h + 1;
            this.h = i;
            if (i == 3) {
                MapView.this.setForeground(null);
                MapView.this.removeOnDidFinishRenderingFrameListener(this);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class q implements OnDidFinishLoadingStyleListener, OnDidFinishRenderingFrameListener, OnDidFinishLoadingMapListener, OnCameraIsChangingListener, OnCameraDidChangeListener, OnDidFailLoadingMapListener {
        public final List<OnMapReadyCallback> h = new ArrayList();

        public q() {
            MapView.this.addOnDidFinishLoadingStyleListener(this);
            MapView.this.addOnDidFinishRenderingFrameListener(this);
            MapView.this.addOnDidFinishLoadingMapListener(this);
            MapView.this.addOnCameraIsChangingListener(this);
            MapView.this.addOnCameraDidChangeListener(this);
            MapView.this.addOnDidFailLoadingMapListener(this);
        }

        public void b(OnMapReadyCallback onMapReadyCallback) {
            this.h.add(onMapReadyCallback);
        }

        public void c() {
            MapView.this.s.t();
            f();
            MapView.this.s.s();
        }

        public void d() {
            this.h.clear();
            MapView.this.removeOnDidFinishLoadingStyleListener(this);
            MapView.this.removeOnDidFinishRenderingFrameListener(this);
            MapView.this.removeOnDidFinishLoadingMapListener(this);
            MapView.this.removeOnCameraIsChangingListener(this);
            MapView.this.removeOnCameraDidChangeListener(this);
            MapView.this.removeOnDidFailLoadingMapListener(this);
        }

        public final void e(int i, String str) {
            Log.e("onMapError", i + "----" + str);
            if (this.h.size() > 0) {
                for (OnMapReadyCallback onMapReadyCallback : this.h) {
                    onMapReadyCallback.onMapError(i, str);
                }
            }
        }

        public final void f() {
            if (this.h.size() > 0) {
                Iterator<OnMapReadyCallback> it = this.h.iterator();
                while (it.hasNext()) {
                    OnMapReadyCallback next = it.next();
                    if (next != null) {
                        next.onMapReady(MapView.this.s);
                    }
                    it.remove();
                }
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnCameraDidChangeListener
        public void onCameraDidChange(boolean z) {
            if (MapView.this.s != null) {
                MapView.this.s.z();
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnCameraIsChangingListener
        public void onCameraIsChanging() {
            if (MapView.this.s != null) {
                MapView.this.s.z();
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFailLoadingMapListener
        public void onDidFailLoadingMap(String str) {
            if (MapView.this.s != null) {
                MapView.this.s.q();
                if (ConnectivityReceiver.instance(MapView.this.getContext()).isOffline()) {
                    MapView.this.s.setStyle(new Style.Builder().fromJson("{\"version\": 8,\"sources\": {},\"layers\": []}"));
                }
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingMapListener
        public void onDidFinishLoadingMap() {
            if (MapView.this.s != null) {
                MapView.this.s.z();
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFinishLoadingStyleListener
        public void onDidFinishLoadingStyle() {
            if (MapView.this.s != null) {
                MapView.this.s.r();
            }
        }

        @Override // com.mappls.sdk.maps.MapView.OnDidFinishRenderingFrameListener
        public void onDidFinishRenderingFrame(boolean z) {
            if (MapView.this.s != null) {
                if (MapplsLMSManager.isInitialised() && MapView.this.s.getCameraPosition() != null) {
                    LatLng latLng = MapView.this.s.getCameraPosition().target;
                    MapplsLMSManager.getInstance().setMapCenter(latLng.getLatitude(), latLng.getLongitude());
                }
                MapView.this.s.y();
            }
        }
    }

    @UiThread
    public MapView(@NonNull Context context) {
        super(context);
        this.h = new com.mappls.sdk.maps.k();
        this.i = new q();
        this.j = new p();
        this.k = new n(this, null);
        this.l = new o(this, null);
        this.m = new com.mappls.sdk.maps.g();
        this.n = false;
        this.p = false;
        this.q = false;
        if (!ConnectivityReceiver.instance(context).isOffline()) {
            if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
                r(context, MapplsMapOptions.createFromAttributes(context, null));
                return;
            } else {
                v(context, MapplsMapOptions.createFromAttributes(context, null));
                return;
            }
        }
        v(context, MapplsMapOptions.createFromAttributes(context, null));
    }

    public static void setMapStrictModeEnabled(boolean z) {
        MapStrictMode.setStrictModeEnabled(z);
    }

    public final void A() {
        post(new k());
    }

    public void B(Context context, MapplsMapOptions mapplsMapOptions) {
        if (!this.n) {
            Mappls.getSessionHelper().startGlobalSession(new l(context, mapplsMapOptions));
        } else {
            initialize(context, mapplsMapOptions);
        }
    }

    public void addOnCameraDidChangeListener(@NonNull OnCameraDidChangeListener onCameraDidChangeListener) {
        this.h.b(onCameraDidChangeListener);
    }

    public void addOnCameraIsChangingListener(@NonNull OnCameraIsChangingListener onCameraIsChangingListener) {
        this.h.c(onCameraIsChangingListener);
    }

    public void addOnCameraWillChangeListener(@NonNull OnCameraWillChangeListener onCameraWillChangeListener) {
        this.h.d(onCameraWillChangeListener);
    }

    public void addOnCanRemoveUnusedStyleImageListener(@NonNull OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.h.e(onCanRemoveUnusedStyleImageListener);
    }

    public void addOnDidBecomeIdleListener(@NonNull OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.h.f(onDidBecomeIdleListener);
    }

    public void addOnDidFailLoadingMapListener(@NonNull OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.h.g(onDidFailLoadingMapListener);
    }

    public void addOnDidFinishLoadingMapListener(@NonNull OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.h.h(onDidFinishLoadingMapListener);
    }

    public void addOnDidFinishLoadingStyleListener(@NonNull OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.h.i(onDidFinishLoadingStyleListener);
    }

    public void addOnDidFinishRenderingFrameListener(@NonNull OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.h.j(onDidFinishRenderingFrameListener);
    }

    public void addOnDidFinishRenderingMapListener(@NonNull OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.h.k(onDidFinishRenderingMapListener);
    }

    public void addOnSourceChangedListener(@NonNull OnSourceChangedListener onSourceChangedListener) {
        this.h.l(onSourceChangedListener);
    }

    public void addOnStyleImageMissingListener(@NonNull OnStyleImageMissingListener onStyleImageMissingListener) {
        this.h.m(onStyleImageMissingListener);
    }

    public void addOnWillStartLoadingMapListener(@NonNull OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.h.n(onWillStartLoadingMapListener);
    }

    public void addOnWillStartRenderingFrameListener(@NonNull OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.h.o(onWillStartRenderingFrameListener);
    }

    public void addOnWillStartRenderingMapListener(@NonNull OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.h.p(onWillStartRenderingMapListener);
    }

    public CompassView getCompassView() {
        return this.x;
    }

    @UiThread
    public void getMapAsync(@NonNull OnMapReadyCallback onMapReadyCallback) {
        MapplsMap mapplsMap = this.s;
        if (mapplsMap == null && this.M == null) {
            this.i.b(onMapReadyCallback);
        } else if (mapplsMap != null) {
            onMapReadyCallback.onMapReady(mapplsMap);
        } else {
            AuthenticationError authenticationError = this.M;
            onMapReadyCallback.onMapError(authenticationError.errorCode, authenticationError.errorMessage);
        }
    }

    @Nullable
    public MapplsMap getMapplsMap() {
        return this.s;
    }

    public float getPixelRatio() {
        float pixelRatio = this.o.getPixelRatio();
        return pixelRatio == 0.0f ? getResources().getDisplayMetrics().density : pixelRatio;
    }

    @NonNull
    @UiThread
    public View getRenderView() {
        return this.t;
    }

    @Override // com.mappls.sdk.maps.NativeMapView.ViewCallback
    @Nullable
    public Bitmap getViewContent() {
        return BitmapUtils.createBitmapFromView(this);
    }

    public AttributionView initialiseAttributionView() {
        AttributionView attributionView = new AttributionView(getContext());
        addView(attributionView);
        attributionView.setTag("attrView");
        attributionView.getLayoutParams().width = -2;
        attributionView.getLayoutParams().height = -2;
        attributionView.setAdjustViewBounds(true);
        attributionView.setClickable(true);
        attributionView.setFocusable(true);
        attributionView.setContentDescription(getResources().getString(R.string.mappls_maps_attributionsIconContentDescription));
        attributionView.setImageDrawable(BitmapUtils.getDrawableFromRes(getContext(), R.drawable.mappls_maps_info_bg_selector));
        m mVar = new m(getContext(), this.s, null);
        this.u = mVar;
        attributionView.setOnClickListener(mVar);
        return attributionView;
    }

    public CompassView initialiseCompassView() {
        CompassView compassView = new CompassView(getContext());
        this.x = compassView;
        addView(compassView);
        this.x.setTag("compassView");
        this.x.getLayoutParams().width = -2;
        this.x.getLayoutParams().height = -2;
        this.x.setContentDescription(getResources().getString(R.string.mappls_maps_compassContentDescription));
        this.x.injectCompassAnimationListener(o(this.m));
        this.x.setOnClickListener(p(this.m));
        return this.x;
    }

    public ImageView initialiseEventView() {
        ImageView imageView = new ImageView(getContext());
        this.L = imageView;
        addView(imageView);
        this.L.getLayoutParams().width = -2;
        this.L.getLayoutParams().height = -2;
        this.L.setTag("eventView");
        this.L.setImageDrawable(BitmapUtils.getDrawableFromRes(getContext(), R.drawable.mappls_maps_fab_corona));
        this.L.setVisibility(8);
        return this.L;
    }

    public FloorControllerView initialiseLayerControlView() {
        FloorControllerView floorControllerView = new FloorControllerView(getContext());
        this.J = floorControllerView;
        addView(floorControllerView);
        this.J.setTag("attrView");
        this.J.getLayoutParams().width = -2;
        this.J.getLayoutParams().height = -2;
        this.J.setBackgroundResource(R.drawable.mappls_maps_floor_layer_bg);
        this.J.setClickable(true);
        this.J.setFocusable(true);
        return this.J;
    }

    public LogoView initialiseLogoView() {
        LogoView logoView = new LogoView(getContext());
        this.I = logoView;
        addView(logoView);
        this.I.injectLogoView(this, this.s);
        this.I.setTag("logoView");
        this.I.getLayoutParams().width = -2;
        this.I.getLayoutParams().height = -2;
        this.I.setVisibility(8);
        t();
        return this.I;
    }

    public SafetyStripView initialiseSafetyStripView() {
        SafetyStripView safetyStripView = new SafetyStripView(getContext());
        this.K = safetyStripView;
        addView(safetyStripView);
        this.K.setTag("safetyStripView");
        this.K.setVisibility(8);
        MapplsMap mapplsMap = this.s;
        if (mapplsMap != null) {
            mapplsMap.k(this.K);
        }
        this.K.getLayoutParams().width = -1;
        this.K.getLayoutParams().height = -2;
        return this.K;
    }

    public View initialiseSplashView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.mappls_maps_splash_layout, (ViewGroup) null);
        inflate.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.D = inflate.findViewById(R.id.splash_view);
        Button button = (Button) inflate.findViewById(R.id.map_retry_button);
        this.E = button;
        ViewCompat.setBackgroundTintList(button, ContextCompat.getColorStateList(getContext(), R.color.mappls_maps_light_gray));
        this.F = (TextView) inflate.findViewById(R.id.loading_map_text_view);
        this.G = (TextView) inflate.findViewById(R.id.error_text_view);
        this.H = inflate.findViewById(R.id.map_loading_issue_layout);
        this.D.setVisibility(MapplsMapConfiguration.getInstance().isDeveloperShowingSplash() ? 8 : 0);
        addView(inflate);
        return inflate;
    }

    @CallSuper
    @UiThread
    public void initialize(@NonNull Context context, @NonNull MapplsMapOptions mapplsMapOptions) {
        this.M = null;
        if (isInEditMode()) {
            return;
        }
        this.q = true;
        setForeground(new ColorDrawable(mapplsMapOptions.getForegroundLoadColor()));
        View view = this.D;
        if (view != null) {
            view.setVisibility(8);
        }
        if (Mappls.hasInstance()) {
            if (this.p) {
                A();
            }
            if (MapplsMapConfiguration.getInstance().isEnablePromotion()) {
                MapplsPromo.builder().build().enqueueCall(new a());
                return;
            }
            return;
        }
        throw new MapplsConfigurationException();
    }

    @UiThread
    public boolean isDestroyed() {
        return this.w;
    }

    public void loadMap() {
        Button button = this.E;
        if (button != null) {
            button.performClick();
        }
    }

    public final void n(@NonNull View view, @NonNull Context context, @NonNull MapplsMapOptions mapplsMapOptions) {
        this.M = null;
        try {
            if (TextUtils.isEmpty(MapplsAccountManager.getInstance().getMapSDKKey())) {
                z(1, "Map SDK Key is missing.\n\nPlease set it in MapplsAccountManager");
            } else if (TextUtils.isEmpty(MapplsAccountManager.getInstance().getRestAPIKey())) {
                z(2, "Rest API Key is missing.\n\nPlease set it in MapplsAccountManager");
            } else if (TextUtils.isEmpty(MapplsAccountManager.getInstance().getAtlasClientId())) {
                z(3, "Atlas client ID is missing.\n\nPlease set it in MapplsAccountManager");
            } else if (TextUtils.isEmpty(MapplsAccountManager.getInstance().getAtlasClientSecret())) {
                z(4, "Atlas client Secret is missing.\n\nPlease set it in MapplsAccountManager");
            } else {
                this.H.setVisibility(4);
                this.F.setVisibility(0);
                setForeground(null);
                v(context, mapplsMapOptions);
            }
        } catch (Exception e2) {
            Timber.e(e2);
            z(5, "Something went wrong.Please try again.");
        }
    }

    public final MapplsMap.OnCompassAnimationListener o(@NonNull com.mappls.sdk.maps.g gVar) {
        return new g(gVar);
    }

    @UiThread
    public void onCreate(@Nullable Bundle bundle) {
        if (bundle == null || !bundle.getBoolean(MapplsConstants.STATE_HAS_SAVED_STATE)) {
            return;
        }
        this.B = bundle;
    }

    @UiThread
    public void onDestroy() {
        this.w = true;
        if (MapplsLMSManager.isInitialised()) {
            MapplsLMSManager.getInstance().setMapCenter(0.0d, 0.0d);
        }
        this.h.q();
        this.i.d();
        this.j.b();
        CompassView compassView = this.x;
        if (compassView != null) {
            compassView.resetAnimation();
        }
        MapplsMap mapplsMap = this.s;
        if (mapplsMap != null) {
            mapplsMap.p();
        }
        s sVar = this.r;
        if (sVar != null) {
            sVar.destroy();
            this.r = null;
        }
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.onDestroy();
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(@NonNull MotionEvent motionEvent) {
        if (x()) {
            return this.z.Z(motionEvent) || super.onGenericMotionEvent(motionEvent);
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        if (y()) {
            return this.A.d(i2, keyEvent) || super.onKeyDown(i2, keyEvent);
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        if (y()) {
            return this.A.e(i2, keyEvent) || super.onKeyLongPress(i2, keyEvent);
        }
        return super.onKeyLongPress(i2, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        if (y()) {
            return this.A.f(i2, keyEvent) || super.onKeyUp(i2, keyEvent);
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @UiThread
    public void onLowMemory() {
        s sVar = this.r;
        if (sVar == null || this.s == null || this.w) {
            return;
        }
        sVar.onLowMemory();
    }

    @UiThread
    public void onPause() {
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.onPause();
        }
    }

    @UiThread
    public void onResume() {
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.onResume();
        }
    }

    @UiThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        if (this.s != null) {
            bundle.putBoolean(MapplsConstants.STATE_HAS_SAVED_STATE, true);
            this.s.v(bundle);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        s sVar;
        if (isInEditMode() || (sVar = this.r) == null) {
            return;
        }
        sVar.b0(i2, i3);
    }

    @UiThread
    public void onStart() {
        if (!this.C) {
            ConnectivityReceiver.instance(getContext()).activate();
            FileSource.getInstance(getContext()).activate();
            this.C = true;
        }
        MapplsMap mapplsMap = this.s;
        if (mapplsMap != null) {
            mapplsMap.w();
        }
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.onStart();
        }
    }

    @UiThread
    public void onStop() {
        m mVar = this.u;
        if (mVar != null) {
            mVar.b();
        }
        if (this.s != null) {
            this.z.A();
            this.s.x();
        }
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.onStop();
        }
        if (this.C) {
            ConnectivityReceiver.instance(getContext()).deactivate();
            FileSource.getInstance(getContext()).deactivate();
            this.C = false;
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (x()) {
            return this.z.a0(motionEvent) || super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTrackballEvent(@NonNull MotionEvent motionEvent) {
        if (y()) {
            return this.A.g(motionEvent) || super.onTrackballEvent(motionEvent);
        }
        return super.onTrackballEvent(motionEvent);
    }

    public final View.OnClickListener p(@NonNull com.mappls.sdk.maps.g gVar) {
        return new h(gVar);
    }

    public final FocalPointChangeListener q() {
        return new f();
    }

    public void queueEvent(@NonNull Runnable runnable) {
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.queueEvent(runnable);
            return;
        }
        throw new IllegalStateException("Calling MapView#queueEvent before mapRenderer is created.");
    }

    public final void r(@NonNull Context context, @NonNull MapplsMapOptions mapplsMapOptions) {
        if (isInEditMode()) {
            return;
        }
        setForeground(new ColorDrawable(mapplsMapOptions.getForegroundLoadColor()));
        this.o = mapplsMapOptions;
        setContentDescription(context.getString(R.string.mappls_maps_mapActionDescription));
        setWillNotDraw(false);
        s(mapplsMapOptions);
        View initialiseSplashView = initialiseSplashView();
        n(initialiseSplashView, context, mapplsMapOptions);
        this.E.setOnClickListener(new d(initialiseSplashView, context, mapplsMapOptions));
    }

    public void removeOnCameraDidChangeListener(@NonNull OnCameraDidChangeListener onCameraDidChangeListener) {
        this.h.r(onCameraDidChangeListener);
    }

    public void removeOnCameraIsChangingListener(@NonNull OnCameraIsChangingListener onCameraIsChangingListener) {
        this.h.s(onCameraIsChangingListener);
    }

    public void removeOnCameraWillChangeListener(@NonNull OnCameraWillChangeListener onCameraWillChangeListener) {
        this.h.t(onCameraWillChangeListener);
    }

    public void removeOnCanRemoveUnusedStyleImageListener(@NonNull OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.h.u(onCanRemoveUnusedStyleImageListener);
    }

    public void removeOnDidBecomeIdleListener(@NonNull OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.h.v(onDidBecomeIdleListener);
    }

    public void removeOnDidFailLoadingMapListener(@NonNull OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.h.w(onDidFailLoadingMapListener);
    }

    public void removeOnDidFinishLoadingMapListener(@NonNull OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.h.x(onDidFinishLoadingMapListener);
    }

    public void removeOnDidFinishLoadingStyleListener(@NonNull OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.h.y(onDidFinishLoadingStyleListener);
    }

    public void removeOnDidFinishRenderingFrameListener(@NonNull OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.h.z(onDidFinishRenderingFrameListener);
    }

    public void removeOnDidFinishRenderingMapListener(OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.h.A(onDidFinishRenderingMapListener);
    }

    public void removeOnSourceChangedListener(@NonNull OnSourceChangedListener onSourceChangedListener) {
        this.h.B(onSourceChangedListener);
    }

    public void removeOnStyleImageMissingListener(@NonNull OnStyleImageMissingListener onStyleImageMissingListener) {
        this.h.C(onStyleImageMissingListener);
    }

    public void removeOnWillStartLoadingMapListener(@NonNull OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.h.D(onWillStartLoadingMapListener);
    }

    public void removeOnWillStartRenderingFrameListener(@NonNull OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.h.E(onWillStartRenderingFrameListener);
    }

    public void removeOnWillStartRenderingMapListener(@NonNull OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.h.F(onWillStartRenderingMapListener);
    }

    public final void s(MapplsMapOptions mapplsMapOptions) {
        String localIdeographFontFamily = mapplsMapOptions.getLocalIdeographFontFamily();
        if (mapplsMapOptions.getTextureMode()) {
            TextureView textureView = new TextureView(getContext());
            this.v = new i(getContext(), textureView, localIdeographFontFamily, mapplsMapOptions.getTranslucentTextureSurface());
            addView(textureView, 0);
            this.t = textureView;
        } else {
            MapplsGLSurfaceView mapplsGLSurfaceView = new MapplsGLSurfaceView(getContext());
            mapplsGLSurfaceView.setZOrderMediaOverlay(this.o.getRenderSurfaceOnTop());
            this.v = new j(getContext(), mapplsGLSurfaceView, localIdeographFontFamily);
            addView(mapplsGLSurfaceView, 0);
            this.t = mapplsGLSurfaceView;
        }
        this.r = new NativeMapView(getContext(), getPixelRatio(), this.o.getCrossSourceCollisions(), this, this.h, this.v);
    }

    public void setIsSatellite(boolean z) {
        this.I.setImageResource(z ? R.drawable.mappls_maps_logo_icon : R.drawable.mappls_maps_bhuwan_logo_icon);
    }

    public void setMapplsMap(MapplsMap mapplsMap) {
        this.s = mapplsMap;
    }

    public void setMaximumFps(int i2) {
        MapRenderer mapRenderer = this.v;
        if (mapRenderer != null) {
            mapRenderer.setMaximumFps(i2);
            return;
        }
        throw new IllegalStateException("Calling MapView#setMaximumFps before mapRenderer is created.");
    }

    public final void t() {
        this.I.setOnClickListener(new e());
    }

    public final void u() {
        Context context = getContext();
        this.k.a(q());
        Projection projection = new Projection(this.r, this);
        UiSettings uiSettings = new UiSettings(projection, this.k, getPixelRatio(), this);
        LongSparseArray longSparseArray = new LongSparseArray();
        LongSparseArray longSparseArray2 = new LongSparseArray();
        com.mappls.sdk.maps.i iVar = new com.mappls.sdk.maps.i(this.r);
        com.mappls.sdk.maps.b bVar = new com.mappls.sdk.maps.b(this, longSparseArray, iVar, new com.mappls.sdk.maps.a(this.r, longSparseArray), new com.mappls.sdk.maps.p(this.r, longSparseArray, iVar, longSparseArray2), new v(this.r, longSparseArray), new x(this.r, longSparseArray), new a0(this.r, longSparseArray), longSparseArray2);
        Transform transform = new Transform(this, this.r, this.m);
        ArrayList arrayList = new ArrayList();
        MapplsMap mapplsMap = new MapplsMap(this.r, transform, uiSettings, projection, this.l, this.m, arrayList);
        this.s = mapplsMap;
        mapplsMap.g(bVar);
        com.mappls.sdk.maps.l lVar = new com.mappls.sdk.maps.l(context, transform, projection, uiSettings, bVar, this.m);
        this.z = lVar;
        this.A = new com.mappls.sdk.maps.m(transform, uiSettings, lVar);
        MapplsMap mapplsMap2 = this.s;
        mapplsMap2.i(new LocationComponent(mapplsMap2, transform, arrayList));
        MapplsMap mapplsMap3 = this.s;
        mapplsMap3.l(new c0(mapplsMap3));
        MapplsMap mapplsMap4 = this.s;
        mapplsMap4.h(new RasterPlugin(this, mapplsMap4));
        MapplsMap mapplsMap5 = this.s;
        mapplsMap5.m(new e0(this, mapplsMap5));
        this.s.j(new u(this.s));
        setClickable(true);
        setLongClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestDisallowInterceptTouchEvent(true);
        this.r.C(Mappls.isConnected().booleanValue());
        Bundle bundle = this.B;
        if (bundle == null) {
            this.s.f(context, this.o);
        } else {
            this.s.u(bundle);
        }
        this.s.k(this.K);
        this.i.c();
    }

    public void v(Context context, MapplsMapOptions mapplsMapOptions) {
        this.o = mapplsMapOptions;
        if (MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            setWillNotDraw(false);
            s(mapplsMapOptions);
        }
        setForeground(new ColorDrawable(mapplsMapOptions.getForegroundLoadColor()));
        Mappls.getStyleHelper().initialiseStyles(new b(context, mapplsMapOptions));
    }

    public final void w(Context context, MapplsMapOptions mapplsMapOptions) {
        if (MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            B(context, mapplsMapOptions);
        } else if (MapplsMapManager.getInstance().getRawPublicKey() == null) {
            MapplsVectorKey.builder().build().enqueueCall(new c(context, mapplsMapOptions));
        } else {
            B(context, mapplsMapOptions);
        }
    }

    public final boolean x() {
        return this.z != null;
    }

    public final boolean y() {
        return this.A != null;
    }

    public final void z(int i2, String str) {
        this.M = new AuthenticationError(i2, str);
        setForeground(null);
        this.i.e(i2, str);
        if (MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            return;
        }
        this.H.setVisibility(0);
        this.G.setText(str);
        this.F.setVisibility(8);
        boolean z = true;
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            z = false;
        }
        this.E.setVisibility(z ? 0 : 8);
    }

    /* loaded from: classes11.dex */
    public class n implements FocalPointChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final List<FocalPointChangeListener> f12638a;

        public n() {
            this.f12638a = new ArrayList();
        }

        public void a(FocalPointChangeListener focalPointChangeListener) {
            this.f12638a.add(focalPointChangeListener);
        }

        @Override // com.mappls.sdk.maps.FocalPointChangeListener
        public void onFocalPointChanged(PointF pointF) {
            MapView.this.z.j0(pointF);
            for (FocalPointChangeListener focalPointChangeListener : this.f12638a) {
                focalPointChangeListener.onFocalPointChanged(pointF);
            }
        }

        public /* synthetic */ n(MapView mapView, a aVar) {
            this();
        }
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new com.mappls.sdk.maps.k();
        this.i = new q();
        this.j = new p();
        this.k = new n(this, null);
        this.l = new o(this, null);
        this.m = new com.mappls.sdk.maps.g();
        this.n = false;
        this.p = false;
        this.q = false;
        if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            r(context, MapplsMapOptions.createFromAttributes(context, attributeSet));
        } else {
            v(context, MapplsMapOptions.createFromAttributes(context, attributeSet));
        }
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.h = new com.mappls.sdk.maps.k();
        this.i = new q();
        this.j = new p();
        this.k = new n(this, null);
        this.l = new o(this, null);
        this.m = new com.mappls.sdk.maps.g();
        this.n = false;
        this.p = false;
        this.q = false;
        if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            r(context, MapplsMapOptions.createFromAttributes(context, attributeSet));
        } else {
            v(context, MapplsMapOptions.createFromAttributes(context, attributeSet));
        }
    }

    @UiThread
    public MapView(@NonNull Context context, @Nullable MapplsMapOptions mapplsMapOptions) {
        super(context);
        this.h = new com.mappls.sdk.maps.k();
        this.i = new q();
        this.j = new p();
        this.k = new n(this, null);
        this.l = new o(this, null);
        this.m = new com.mappls.sdk.maps.g();
        this.n = false;
        this.p = false;
        this.q = false;
        if (!MapplsMapConfiguration.getInstance().isUsingRasterStyle()) {
            r(context, mapplsMapOptions == null ? MapplsMapOptions.createFromAttributes(context, null) : mapplsMapOptions);
        } else {
            v(context, mapplsMapOptions == null ? MapplsMapOptions.createFromAttributes(context, null) : mapplsMapOptions);
        }
    }
}
