package com.coveiot.android.dynamictab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.dynamictab.adapters.DynamicTabAdapter;
import com.coveiot.android.dynamictab.adapters.FlyAnimatonAdapter;
import com.coveiot.android.dynamictab.cricketmodels.AnimationFlyModel;
import com.coveiot.android.dynamictab.cricketmodels.AnimationInitModel;
import com.coveiot.android.dynamictab.cricketmodels.AnimationVisibilityModel;
import com.coveiot.android.dynamictab.cricketmodels.OnAnimationBarClick;
import com.coveiot.android.dynamictab.cricketmodels.OnAnimationItemClick;
import com.coveiot.android.dynamictab.sports.CoveJsInterface;
import com.coveiot.android.dynamictab.sports.model.FlyAnimationModel;
import com.coveiot.android.dynamictab.sports.model.WebViewDataCMMModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveHealthStatusApi;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetHealthStatusRes;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.navigation.NavigationConstants;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes4.dex */
public class DynamicTabWebActivity extends BaseActivity implements OnAnimationBarClick, OnAnimationItemClick, AppActionInterface {
    public static final String Q = "DynamicTabWebActivity";
    public static boolean isActive;
    public static WebView mWebView;
    public List<FlyAnimationModel> D;
    public List<FlyAnimationModel> E;
    public List<AnimationInitModel.DataBean.DefinitionBean.ElementsBean> F;
    public AnimationInitModel H;
    public CoveJsInterface.LocationResultListener K;
    public JSONObject N;
    public String O;
    public String P;
    public TextView invalidUrl;
    public RecyclerView q;
    public RecyclerView.LayoutManager r;
    public FlyAnimatonAdapter t;
    public RelativeLayout u;
    public boolean p = false;
    public Gson s = new Gson();
    public DisplayMetrics v = new DisplayMetrics();
    public int w = 0;
    public int x = 0;
    public float y = 0.0f;
    public float z = 0.0f;
    public float A = 0.0f;
    public float B = 0.0f;
    public float C = 0.0f;
    public boolean G = true;
    public List<Integer> I = new ArrayList();
    public Handler J = new Handler();
    public String L = "";
    public boolean M = true;

    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: com.coveiot.android.dynamictab.DynamicTabWebActivity$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class C0266a implements ValueCallback<String> {
            public C0266a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                DynamicTabWebActivity.this.q("onReceiveValue", str);
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 19)
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + DynamicTabWebActivity.this.N.toString() + ")", new C0266a());
        }
    }

    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                DynamicTabWebActivity.this.q("onReceiveValue", str);
            }
        }

        public b() {
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 19)
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + DynamicTabWebActivity.this.N.toString() + ")", new a());
        }
    }

    /* loaded from: classes4.dex */
    public class c implements Runnable {
        public final /* synthetic */ AnimationInitModel h;

        public c(AnimationInitModel animationInitModel) {
            this.h = animationInitModel;
        }

        @Override // java.lang.Runnable
        public void run() {
            DynamicTabWebActivity.this.z(this.h);
        }
    }

    /* loaded from: classes4.dex */
    public class d implements Runnable {
        public final /* synthetic */ WebViewDataCMMModel h;

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                DynamicTabWebActivity.this.q("onReceiveValue", str);
            }
        }

        public d(WebViewDataCMMModel webViewDataCMMModel) {
            this.h = webViewDataCMMModel;
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 19)
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + this.h.getWebview() + ")", new a());
        }
    }

    /* loaded from: classes4.dex */
    public class e implements CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel> {
        public e() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(SGetHealthStatusRes sGetHealthStatusRes) {
            if (sGetHealthStatusRes != null) {
                Intent intent = new Intent();
                DynamicTabAdapter.Companion companion = DynamicTabAdapter.Companion;
                intent.putExtra(companion.getEXTRA_API_RESPONSE(), new Gson().toJson(sGetHealthStatusRes));
                intent.putExtra(companion.getEXTRA_IS_USER_FLAGGED(), sGetHealthStatusRes.getData().getTemperature().isIsFlagged());
                DynamicTabWebActivity.this.setResult(-1, intent);
            }
        }
    }

    /* loaded from: classes4.dex */
    public class f implements PermissionUtils.PermissionAskListener {
        public f() {
        }

        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
        public void onPermissionAsk() {
            ActivityCompat.requestPermissions(DynamicTabWebActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, 102);
        }

        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
        public void onPermissionDisabled() {
            DynamicTabWebActivity.this.C();
        }

        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
        public void onPermissionGranted() {
            DynamicTabWebActivity.this.y();
        }

        @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
        public void onPermissionPreviouslyDenied() {
            DynamicTabWebActivity.this.C();
        }
    }

    /* loaded from: classes4.dex */
    public class g implements View.OnClickListener {
        public final /* synthetic */ BottomSheetDialogOneButtonOneTitle h;

        public g(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
            this.h = bottomSheetDialogOneButtonOneTitle;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
            AppUtils.openAppSettings(DynamicTabWebActivity.this, 102);
        }
    }

    /* loaded from: classes4.dex */
    public class h extends WebViewClient {
        public h(DynamicTabWebActivity dynamicTabWebActivity) {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
        }
    }

    /* loaded from: classes4.dex */
    public class i extends WebViewClient {
        public i() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            DynamicTabWebActivity.this.dismissProgress();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            DynamicTabWebActivity.this.dismissProgress();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            DynamicTabWebActivity.this.showProgress();
            return true;
        }
    }

    /* loaded from: classes4.dex */
    public class j implements Runnable {
        public j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DynamicTabWebActivity.this.p = false;
        }
    }

    /* loaded from: classes4.dex */
    public class k implements Runnable {
        public final /* synthetic */ JSONObject h;

        public k(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AnimationInitModel animationInitModel = (AnimationInitModel) DynamicTabWebActivity.this.s.fromJson(this.h.toString(), (Class<Object>) AnimationInitModel.class);
                DynamicTabWebActivity.this.H = animationInitModel;
                if (animationInitModel != null) {
                    if (animationInitModel.getData().getDefinition().getDefaultVisibility().equalsIgnoreCase(Property.VISIBLE)) {
                        DynamicTabWebActivity.this.q.setVisibility(0);
                        DynamicTabWebActivity.mWebView.scrollBy(5, 5);
                        DynamicTabWebActivity.this.G = true;
                    } else {
                        DynamicTabWebActivity.this.q.setVisibility(8);
                        DynamicTabWebActivity.this.G = false;
                    }
                    DynamicTabWebActivity.this.D = new ArrayList();
                    DynamicTabWebActivity.this.E = new ArrayList();
                    if (animationInitModel.getData() != null && animationInitModel.getData().getDefinition() != null && animationInitModel.getData().getDefinition().getElements().size() > 0) {
                        for (int i = 0; i < animationInitModel.getData().getDefinition().getElements().size(); i++) {
                            FlyAnimationModel flyAnimationModel = new FlyAnimationModel();
                            flyAnimationModel.setImageUrl(animationInitModel.getData().getDefinition().getElements().get(i).getImageUrl());
                            flyAnimationModel.setFlyRandom(animationInitModel.getData().getDefinition().getElements().get(i).isFlyRandom());
                            flyAnimationModel.setStickerId(animationInitModel.getData().getDefinition().getElements().get(i).getStickerId());
                            flyAnimationModel.setVisibility(animationInitModel.getData().getDefinition().getElements().get(i).getVisibility());
                            if (animationInitModel.getData().getDefinition().getElements().get(i).isFlyRandom()) {
                                DynamicTabWebActivity.this.F.add(animationInitModel.getData().getDefinition().getElements().get(i));
                            }
                            DynamicTabWebActivity.this.D.add(flyAnimationModel);
                        }
                    }
                    if (DynamicTabWebActivity.this.D.size() > 0) {
                        DynamicTabWebActivity dynamicTabWebActivity = DynamicTabWebActivity.this;
                        dynamicTabWebActivity.t = new FlyAnimatonAdapter(dynamicTabWebActivity, dynamicTabWebActivity);
                        DynamicTabWebActivity dynamicTabWebActivity2 = DynamicTabWebActivity.this;
                        dynamicTabWebActivity2.q.setAdapter(dynamicTabWebActivity2.t);
                        for (int i2 = 0; i2 < DynamicTabWebActivity.this.D.size(); i2++) {
                            if (DynamicTabWebActivity.this.D.get(i2).getVisibility().equalsIgnoreCase(Property.VISIBLE)) {
                                DynamicTabWebActivity dynamicTabWebActivity3 = DynamicTabWebActivity.this;
                                dynamicTabWebActivity3.E.add(dynamicTabWebActivity3.D.get(i2));
                            }
                        }
                        DynamicTabWebActivity dynamicTabWebActivity4 = DynamicTabWebActivity.this;
                        dynamicTabWebActivity4.t.setData(dynamicTabWebActivity4.E);
                        DynamicTabWebActivity.this.t.notifyItemChanged(0);
                        DynamicTabWebActivity.this.z(animationInitModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class l implements Runnable {
        public final /* synthetic */ AnimationFlyModel h;

        public l(AnimationFlyModel animationFlyModel) {
            this.h = animationFlyModel;
        }

        @Override // java.lang.Runnable
        public void run() {
            DynamicTabWebActivity dynamicTabWebActivity;
            try {
                AnimationFlyModel animationFlyModel = this.h;
                if (animationFlyModel == null || animationFlyModel.getData().getStickers().size() <= 0) {
                    return;
                }
                for (int i = 0; i < this.h.getData().getStickers().size(); i++) {
                    ImageView imageView = new ImageView(DynamicTabWebActivity.this);
                    Glide.with((FragmentActivity) DynamicTabWebActivity.this).m30load(this.h.getData().getStickers().get(i).getImageUrl()).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).into(imageView);
                    imageView.setClickable(false);
                    DynamicTabWebActivity dynamicTabWebActivity2 = DynamicTabWebActivity.this;
                    dynamicTabWebActivity2.u = (RelativeLayout) dynamicTabWebActivity2.findViewById(R.id.root_layout);
                    DynamicTabWebActivity.this.u.setClipChildren(false);
                    DynamicTabWebActivity.this.u.bringToFront();
                    DynamicTabWebActivity.this.u.addView(imageView);
                    Resources resources = DynamicTabWebActivity.this.getResources();
                    int i2 = R.dimen.imageview_size;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) resources.getDimension(i2), (int) DynamicTabWebActivity.this.getResources().getDimension(i2)));
                    DynamicTabWebActivity.this.w(imageView, (int) (dynamicTabWebActivity.w * 0.1f), (int) (dynamicTabWebActivity.x * 0.9f), this.h.getData().getStickers().get(i).getImageUrl(), true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class m implements Runnable {
        public final /* synthetic */ JSONObject h;

        public m(JSONObject jSONObject) {
            this.h = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AnimationVisibilityModel animationVisibilityModel = (AnimationVisibilityModel) DynamicTabWebActivity.this.s.fromJson(this.h.toString(), (Class<Object>) AnimationVisibilityModel.class);
                if (animationVisibilityModel != null) {
                    if (animationVisibilityModel.getData().getValue().equalsIgnoreCase(Property.VISIBLE)) {
                        DynamicTabWebActivity.mWebView.scrollBy(5, 5);
                        DynamicTabWebActivity.this.q.setVisibility(0);
                        DynamicTabWebActivity.mWebView.scrollBy(5, 5);
                        DynamicTabWebActivity dynamicTabWebActivity = DynamicTabWebActivity.this;
                        dynamicTabWebActivity.G = true;
                        dynamicTabWebActivity.z(dynamicTabWebActivity.H);
                    } else {
                        DynamicTabWebActivity.this.q.setVisibility(8);
                        DynamicTabWebActivity.this.G = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class n implements Animator.AnimatorListener {
        public final /* synthetic */ View h;

        public n(View view) {
            this.h = view;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            DynamicTabWebActivity.this.u.removeView(this.h);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes4.dex */
    public class o implements Animator.AnimatorListener {
        public final /* synthetic */ View h;

        public o(View view) {
            this.h = view;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            DynamicTabWebActivity.this.u.removeView(this.h);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes4.dex */
    public class p implements Runnable {

        /* loaded from: classes4.dex */
        public class a implements ValueCallback<String> {
            public a() {
            }

            @Override // android.webkit.ValueCallback
            /* renamed from: a */
            public void onReceiveValue(String str) {
                DynamicTabWebActivity.this.q("onReceiveValue", str);
            }
        }

        public p() {
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 19)
        public void run() {
            WebView webView = DynamicTabWebActivity.mWebView;
            webView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + DynamicTabWebActivity.this.N.toString() + ")", new a());
        }
    }

    public static int A(int i2, int i3) {
        if (i2 <= i3) {
            return ((int) Math.floor(Math.random() * ((i3 - i2) + 1))) + i2;
        }
        throw new IllegalArgumentException("max must be greater than min");
    }

    public final void B() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new f());
    }

    public final void C() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, getString(R.string.need_location_access));
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(getString(R.string.ok), new g(bottomSheetDialogOneButtonOneTitle));
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.B = motionEvent.getX();
        this.C = motionEvent.getY();
        Log.d("dispatchTouchEvent", "touchX: " + this.B + " touchY: " + this.C);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.coveiot.android.dynamictab.AppActionInterface
    public String getLanguage() {
        return PreferenceManager.getInstance().getLanguage();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        Bundle extras = intent != null ? intent.getExtras() : null;
        if (i2 != 5) {
            if (i2 == 102) {
                if (AppUtils.checkIfLocationPermissionExists(this)) {
                    y();
                } else {
                    this.K.permissionDenied();
                }
            }
        } else if (i3 == -1 && extras != null) {
            Log.d(Q, extras.toString());
            extras.getString("pgMeTrnRefNo");
            extras.getString("orderNo");
            extras.getString("txnAmount");
            extras.getString("tranAuthdate");
            String string = extras.getString(NotificationCompat.CATEGORY_STATUS);
            extras.getString("statusDesc");
            extras.getString("responsecode");
            extras.getString("approvalCode");
            extras.getString("payerVA");
            extras.getString("npciTxnId");
            extras.getString("refId");
            extras.getString("payerAccountNo");
            extras.getString("payerIfsc");
            extras.getString("payerAccName");
            extras.getString("add1");
            extras.getString("add2");
            extras.getString("add3");
            extras.getString("add4");
            extras.getString("add5");
            extras.getString("add6");
            extras.getString("add7");
            extras.getString("add8");
            extras.getString("add9");
            try {
                JSONObject jSONObject = this.N;
                jSONObject.put("resId", "" + new Date().getTime());
                this.N.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("refId", this.P);
                jSONObject2.put("txnStatus", string);
                this.N.put("data", jSONObject2);
                mWebView.post(new p());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                JSONObject jSONObject3 = this.N;
                jSONObject3.put("resId", "" + new Date().getTime());
                this.N.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put("refId", this.P);
                jSONObject4.put("txnStatus", WeatherCriteria.UNIT_CELSIUS);
                this.N.put("data", jSONObject4);
                mWebView.post(new a());
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override // com.coveiot.android.dynamictab.cricketmodels.OnAnimationBarClick
    public void onAnimationFly(JSONObject jSONObject) {
        runOnUiThread(new l((AnimationFlyModel) this.s.fromJson(jSONObject.toString(), (Class<Object>) AnimationFlyModel.class)));
    }

    @Override // com.coveiot.android.dynamictab.cricketmodels.OnAnimationBarClick
    public void onAnimationInit(JSONObject jSONObject) {
        this.F = new ArrayList();
        runOnUiThread(new k(jSONObject));
    }

    @Override // com.coveiot.android.dynamictab.cricketmodels.OnAnimationBarClick
    public void onAnimationVisibility(JSONObject jSONObject) {
        runOnUiThread(new m(jSONObject));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.p) {
            super.onBackPressed();
            return;
        }
        this.p = true;
        AppUtils.showToast(this, getString(R.string.press_again));
        new Handler().postDelayed(new j(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(api = 19)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        isActive = true;
        setContentView(R.layout.activity_leaderboard_web);
        mWebView = (WebView) findViewById(R.id.webview);
        this.q = (RecyclerView) findViewById(R.id.flyRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.r = linearLayoutManager;
        this.q.setLayoutManager(linearLayoutManager);
        this.q.setHasFixedSize(true);
        this.q.setLayoutManager(this.r);
        this.invalidUrl = (TextView) findViewById(R.id.invalidUrl);
        this.u = (RelativeLayout) findViewById(R.id.root_layout);
        getWindowManager().getDefaultDisplay().getMetrics(this.v);
        DisplayMetrics displayMetrics = this.v;
        int i2 = displayMetrics.widthPixels;
        this.w = i2;
        int i3 = displayMetrics.heightPixels;
        this.x = i3;
        this.y = i2 * 0.75f;
        this.A = i3 * 0.5f;
        this.z = i2 * 0.15f;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.L = extras.getString("url");
            this.M = extras.getBoolean("coveJsInterface");
            String str = Q;
            Log.d(str, this.L + " :" + this.M);
            String str2 = this.L;
            if (str2 != null && !str2.isEmpty()) {
                this.invalidUrl.setVisibility(8);
                mWebView.setVisibility(0);
            } else {
                this.invalidUrl.setVisibility(0);
                mWebView.setVisibility(8);
            }
            if (getIntent().hasExtra("HEALTH_DATA")) {
                this.O = extras.getString("HEALTH_DATA");
            }
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 19 && (getApplicationInfo().flags & 2) != 0) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        mWebView.setWebViewClient(new h(this));
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        if (i4 >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath("/data/data/" + mWebView.getContext().getPackageName() + "/databases/");
        mWebView.setWebViewClient(new i());
        if (this.M) {
            CoveJsInterface coveJsInterface = new CoveJsInterface(this, this);
            coveJsInterface.setAlertWebModelString(this.O);
            coveJsInterface.setAppInteractionListner(this);
            mWebView.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        }
        String str3 = this.L;
        if (str3 == null || str3.isEmpty()) {
            return;
        }
        mWebView.loadUrl(this.L);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
        isActive = false;
    }

    @Override // com.coveiot.android.dynamictab.AppActionInterface
    public void onHealthQuestionaryComplete() {
        CoveHealthStatusApi.getHealthStatus(new e());
    }

    @Override // com.coveiot.android.dynamictab.cricketmodels.OnAnimationItemClick
    public void onItemClick(View view, String str) {
        try {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            String str2 = Q;
            StringBuilder sb = new StringBuilder();
            sb.append("x: ");
            int i2 = iArr[0];
            Resources resources = getResources();
            int i3 = R.dimen.imageview_size;
            sb.append(i2 + ((int) ((resources.getDimension(i3) / 2.0f) + 5.0f)));
            sb.append(" y: ");
            sb.append(iArr[1] + ((int) (getResources().getDimension(i3) / 2.0f)));
            Log.d(str2, sb.toString());
            ImageView imageView = new ImageView(this);
            Glide.with((FragmentActivity) this).m30load(str).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).into(imageView);
            imageView.setClickable(false);
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_layout);
            this.u = relativeLayout;
            relativeLayout.setClipChildren(false);
            this.u.bringToFront();
            this.u.addView(imageView);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) getResources().getDimension(i3), (int) getResources().getDimension(i3)));
            x(imageView, iArr[0], iArr[1], str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Subscribe
    public void onNotificationOpened(WebViewDataCMMModel webViewDataCMMModel) {
        mWebView.post(new d(webViewDataCMMModel));
    }

    @Override // com.coveiot.android.dynamictab.AppActionInterface
    public void onRequestLocation(CoveJsInterface.LocationResultListener locationResultListener) {
        this.K = locationResultListener;
        if (AppUtils.checkIfLocationPermissionExists(this)) {
            y();
        } else {
            B();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (iArr.length > 0) {
            y();
        } else {
            this.K.permissionDenied();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Handler handler = this.J;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final void q(String str, String str2) {
    }

    public void replyWebView(String str) {
        try {
            JSONObject jSONObject = this.N;
            jSONObject.put("resId", "" + new Date().getTime());
            this.N.put(NotificationCompat.CATEGORY_STATUS, CoveApiConstants.RESPONSE_STATUS_VALUE_OK);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("refId", this.P);
            jSONObject2.put("txnStatus", str);
            this.N.put("data", jSONObject2);
            mWebView.post(new b());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void requestMoneyUpi(JSONObject jSONObject, JSONObject jSONObject2) {
        this.N = jSONObject;
        try {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("data");
            this.P = jSONObject3.getString("refId");
            jSONObject3.getString("amount");
            jSONObject3.getString("remark");
            String string = jSONObject3.getJSONArray("methods").getString(0);
            if (!string.equals("UPI") && string.equals("UPI_COLLECT")) {
                jSONObject3.getJSONObject("upi").getString("vpa");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void w(View view, int i2, int i3, String str, boolean z) {
        ObjectAnimator ofFloat;
        try {
            Glide.with((FragmentActivity) this).m30load(str).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).into((ImageView) view);
            view.setClickable(false);
            view.getLocationOnScreen(new int[2]);
            if (z) {
                ofFloat = ObjectAnimator.ofFloat(view, "x", i2, this.z);
            } else {
                float f2 = this.y;
                ofFloat = ObjectAnimator.ofFloat(view, "x", i2, f2, 20.0f + f2, f2, f2);
            }
            int i4 = this.x;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, EllipticCurveJsonWebKey.Y_MEMBER_NAME, i3, this.A, i4 * 0.1f, i4 * 0.1f, i4 * 0.1f);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.75f, 0.5f, 0.25f, 0.0f);
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.75f, 0.5f, 0.25f, 0.0f);
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f, 0.0f, 0.0f, 0.0f);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4).with(ofFloat5);
            if (z) {
                animatorSet.setDuration(A(A(NavigationConstants.UI_HANDLER_MAP_CONTROLS, 4300), A(5200, 5500)));
            } else {
                animatorSet.setDuration(A(3000, NavigationConstants.UI_HANDLER_MAP_CONTROLS));
            }
            animatorSet.start();
            animatorSet.addListener(new o(view));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void x(View view, int i2, int i3, String str) {
        try {
            String str2 = Q;
            Log.d(str2, "animation");
            Glide.with((FragmentActivity) this).m30load(str).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).into((ImageView) view);
            view.setClickable(false);
            float f2 = this.y;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "x", i2, f2, 20.0f + f2, f2, f2);
            int i4 = this.x;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, EllipticCurveJsonWebKey.Y_MEMBER_NAME, i3, this.A, i4 * 0.1f, i4 * 0.1f, i4 * 0.1f);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            Log.d(str2, "X: " + iArr[0] + " Y: " + iArr[1]);
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.75f, 0.5f, 0.25f, 0.0f);
            ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.75f, 0.5f, 0.25f, 0.0f);
            ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.5f, 0.0f, 0.0f, 0.0f);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat2.setInterpolator(new AccelerateDecelerateInterpolator());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(ofFloat).with(ofFloat2).with(ofFloat3).with(ofFloat4).with(ofFloat5);
            animatorSet.setDuration(3500L);
            animatorSet.start();
            animatorSet.addListener(new n(view));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void y() {
        LocationManager locationManager = (LocationManager) getSystemService(FirebaseAnalytics.Param.LOCATION);
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
            if (lastKnownLocation == null) {
                lastKnownLocation = locationManager.getLastKnownLocation("network");
            }
            if (lastKnownLocation != null) {
                this.K.onLocationReceived(lastKnownLocation);
            }
        }
    }

    public final void z(AnimationInitModel animationInitModel) {
        try {
            if (this.G && animationInitModel != null && animationInitModel.getData().getDefinition().isEnableFlyRandom()) {
                this.I.clear();
                int A = A(animationInitModel.getData().getDefinition().getFlyRate().getVolMin(), animationInitModel.getData().getDefinition().getFlyRate().getVolMax());
                int A2 = A(animationInitModel.getData().getDefinition().getFlyRate().getTimeMin(), animationInitModel.getData().getDefinition().getFlyRate().getTimeMax());
                for (int i2 = 0; i2 < A; i2++) {
                    this.I.add(Integer.valueOf(A(0, this.F.size())));
                }
                this.J.postDelayed(new c(animationInitModel), A2);
                for (int i3 = 0; i3 < this.I.size(); i3++) {
                    ImageView imageView = new ImageView(this);
                    Glide.with((FragmentActivity) this).m30load(animationInitModel.getData().getDefinition().getElements().get(this.I.get(i3).intValue()).getImageUrl()).apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)).into(imageView);
                    imageView.setClickable(false);
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_layout);
                    this.u = relativeLayout;
                    relativeLayout.setClipChildren(false);
                    this.u.bringToFront();
                    this.u.addView(imageView);
                    Resources resources = getResources();
                    int i4 = R.dimen.imageview_size;
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams((int) resources.getDimension(i4), (int) getResources().getDimension(i4)));
                    w(imageView, (int) (this.w * ((-(i3 * 0.15f)) + 0.9f)), (int) (this.x * 0.85f), animationInitModel.getData().getDefinition().getElements().get(this.I.get(i3).intValue()).getImageUrl(), false);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
