package com.coveiot.android.leonardo.boatcoin.activities;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityBoatCoinsWebViewerBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.firebaseservices.enums.FcmMessageTypes;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.websupport.CoveJsInterface;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class ActivityBoatCoinsWebViewer extends BaseActivity implements CoveJsInterface.OnJavascriptListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static WebView sWebView;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityInAppWebViewer";
    public boolean q = true;
    public boolean r;
    @Nullable
    public String s;
    @Nullable
    public String t;
    public ActivityBoatCoinsWebViewerBinding u;
    @Nullable
    public CoveJsInterface v;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final WebView getSWebView() {
            WebView webView = ActivityBoatCoinsWebViewer.sWebView;
            if (webView != null) {
                return webView;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sWebView");
            return null;
        }

        public final void setSWebView(@NotNull WebView webView) {
            Intrinsics.checkNotNullParameter(webView, "<set-?>");
            ActivityBoatCoinsWebViewer.sWebView = webView;
        }
    }

    public static final void u(final ActivityBoatCoinsWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        StringBuilder sb = new StringBuilder();
        sb.append("appOutScript: ");
        Intrinsics.checkNotNull(str);
        sb.append(str);
        LogHelper.d(str2, sb.toString());
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding = this$0.u;
        if (activityBoatCoinsWebViewerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding = null;
        }
        activityBoatCoinsWebViewerBinding.webViewBoatCoins.evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.leonardo.boatcoin.activities.p
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityBoatCoinsWebViewer.v(ActivityBoatCoinsWebViewer.this, (String) obj);
            }
        });
    }

    public static final void v(ActivityBoatCoinsWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void w(ActivityBoatCoinsWebViewer this$0, String url, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (AppUtils.isNetConnected(this$0)) {
            this$0.finish();
            AppNavigator.Companion.navigateToBoatCoinsWebViewer(this$0, url, this$0.screenName());
            ((Dialog) dialog.element).dismiss();
            return;
        }
        Toast.makeText(this$0, "Please check your internet connection", 0).show();
    }

    public static final void x(ActivityBoatCoinsWebViewer this$0, Ref.ObjectRef dialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        AppNavigator.Companion.navigateToActivityDashboard(this$0);
        this$0.finish();
        ((Dialog) dialog.element).dismiss();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @Override // com.coveiot.android.leonardo.websupport.CoveJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.boatcoin.activities.q
            @Override // java.lang.Runnable
            public final void run() {
                ActivityBoatCoinsWebViewer.u(ActivityBoatCoinsWebViewer.this, str);
            }
        });
    }

    @Nullable
    public final String getAxValue() {
        return this.t;
    }

    @Nullable
    public final CoveJsInterface getCoveJsInterface() {
        return this.v;
    }

    @NotNull
    public final String getDataObject(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(CleverTapConstants.CustomEventProperties.BOAT_COINS_PAGE_SOURCE.getValue(), value);
        jSONObject.put("ep", jSONObject2);
        String jSONObject3 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject3, "data.toString()");
        return jSONObject3;
    }

    public final boolean getLoadingFinished() {
        return this.q;
    }

    public final boolean getRedirect() {
        return this.r;
    }

    @Nullable
    public final String getScreenType() {
        return this.s;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, android.app.Dialog] */
    public final void noInternetDialog(@NotNull final String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? dialog = new Dialog(this, 16973829);
        objectRef.element = dialog;
        ((Dialog) dialog).requestWindowFeature(1);
        ((Dialog) objectRef.element).setContentView(R.layout.no_internet_message);
        Button button = (Button) ((Dialog) objectRef.element).findViewById(R.id.btn_retry);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.n
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityBoatCoinsWebViewer.w(ActivityBoatCoinsWebViewer.this, url, objectRef, view);
                }
            });
        }
        ((TextView) ((Dialog) objectRef.element).findViewById(R.id.btn_home)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.boatcoin.activities.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBoatCoinsWebViewer.x(ActivityBoatCoinsWebViewer.this, objectRef, view);
            }
        });
        ((Dialog) objectRef.element).show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        String url = SessionManager.getInstance(this).getCoinsHomeUrl();
        if (AppUtils.isNetConnected(this)) {
            ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding = this.u;
            ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding2 = null;
            if (activityBoatCoinsWebViewerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBoatCoinsWebViewerBinding = null;
            }
            if (activityBoatCoinsWebViewerBinding.webViewBoatCoins.canGoBack()) {
                ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding3 = this.u;
                if (activityBoatCoinsWebViewerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityBoatCoinsWebViewerBinding3 = null;
                }
                if (!kotlin.text.m.equals$default(activityBoatCoinsWebViewerBinding3.webViewBoatCoins.getUrl(), url, false, 2, null)) {
                    ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding4 = this.u;
                    if (activityBoatCoinsWebViewerBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityBoatCoinsWebViewerBinding2 = activityBoatCoinsWebViewerBinding4;
                    }
                    activityBoatCoinsWebViewerBinding2.webViewBoatCoins.goBack();
                    return;
                }
            }
            super.onBackPressed();
            return;
        }
        Intrinsics.checkNotNullExpressionValue(url, "url");
        noInternetDialog(url);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    public void onCreate(@Nullable Bundle bundle) {
        String queryParameter;
        super.onCreate(bundle);
        ActivityBoatCoinsWebViewerBinding inflate = ActivityBoatCoinsWebViewerBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.u = inflate;
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Companion companion = Companion;
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding2 = this.u;
        if (activityBoatCoinsWebViewerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding2 = null;
        }
        WebView webView = activityBoatCoinsWebViewerBinding2.webViewBoatCoins;
        Intrinsics.checkNotNullExpressionValue(webView, "binding.webViewBoatCoins");
        companion.setSWebView(webView);
        boolean booleanExtra = getIntent().getBooleanExtra(ThemeConstants.INTENT_EXTRA_FROM_NOTIFICATION.getValue(), false);
        String stringExtra = getIntent().getStringExtra(ThemeConstants.NOTIFY_IDENTIFIER.getValue());
        String stringExtra2 = getIntent().getStringExtra(ThemeConstants.FCM_NOTIFICATION_TYPE.getValue());
        if (getIntent() != null) {
            Intent intent = getIntent();
            AppConstants appConstants = AppConstants.SCREEN_NAME;
            if (intent.hasExtra(appConstants.getValue())) {
                String stringExtra3 = getIntent().getStringExtra(appConstants.getValue());
                Intrinsics.checkNotNull(stringExtra3);
                this.s = stringExtra3;
            }
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CV_PAGE_VISIT.getValue());
        if (stringExtra2 != null) {
            if (stringExtra2.equals(FcmMessageTypes.COINS_EARNED_MESSAGE.name())) {
                analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.BOAT_COINS_DASHBOARD.getValue());
            } else if (stringExtra2.equals(FcmMessageTypes.COINS_FEATURE_MESSAGE.name())) {
                analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.PARTNER_DETAILS.getValue());
            }
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("cv_feature_name", "boatcoins");
        if (booleanExtra) {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SYSTEM_NOTIFICATION.getValue());
            if (stringExtra != null) {
                hashMap.put("cv_notif_identifier", stringExtra);
                analyticsLog.setMapData(hashMap);
            }
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        } else {
            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.BOAT_COINS_DASHBOARD.getValue());
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        }
        String value = AppConstants.EMPTY_STRING.getValue();
        Intent intent2 = getIntent();
        AppConstants appConstants2 = AppConstants.INTENT_EXTRA_URL;
        if (intent2.hasExtra(appConstants2.getValue())) {
            value = getIntent().getStringExtra(appConstants2.getValue());
            Intrinsics.checkNotNull(value);
        }
        if (getIntent().getData() != null) {
            Uri data = getIntent().getData();
            if ((data == null || (queryParameter = data.getQueryParameter(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE)) == null || !queryParameter.equals("1")) ? false : true) {
                String queryParameter2 = data != null ? data.getQueryParameter("__u") : null;
                String replace$default = queryParameter2 != null ? kotlin.text.m.replace$default(queryParameter2, "-", "+", false, 4, (Object) null) : null;
                value = String.valueOf(DeviceUtils.Companion.decryptRSA(this, (replace$default != null ? kotlin.text.m.replace$default(replace$default, "_", MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 4, (Object) null) : null) + '='));
            }
            this.t = data != null ? data.getQueryParameter("_ax") : null;
        }
        String str = this.s;
        if (!(str == null || str.length() == 0) && kotlin.text.m.equals(this.s, BoatCoinsScreenCaller.HP_BANNER.name(), true)) {
            this.t = ThemesUtils.INSTANCE.encode(getDataObject(CleverTapConstants.CustomEventValues.HP_BANNER.getValue()));
        } else {
            String str2 = this.s;
            if (!(str2 == null || str2.length() == 0) && kotlin.text.m.equals(this.s, BoatCoinsScreenCaller.HP_TOP_ICON.name(), true)) {
                this.t = ThemesUtils.INSTANCE.encode(getDataObject(CleverTapConstants.CustomEventValues.HP_ICON_TOP.getValue()));
            } else {
                String str3 = this.s;
                if (!(str3 == null || str3.length() == 0) && kotlin.text.m.equals(this.s, BoatCoinsScreenCaller.PROFILE.name(), true)) {
                    this.t = ThemesUtils.INSTANCE.encode(getDataObject(CleverTapConstants.CustomEventValues.PROFILE.getValue()));
                }
            }
        }
        String str4 = this.t;
        if (str4 == null || str4.length() == 0) {
            this.t = ThemesUtils.INSTANCE.encode(getDataObject(CleverTapConstants.CustomEventValues.HP_GRID.getValue()));
        }
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getString("actionId") != null) {
                Bundle extras2 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras2);
                boolean z = extras2.getBoolean("autoCancel", true);
                Bundle extras3 = getIntent().getExtras();
                Intrinsics.checkNotNull(extras3);
                int i = extras3.getInt(Constants.PT_NOTIF_ID, -1);
                if (z && i > -1) {
                    Object systemService = getApplicationContext().getSystemService("notification");
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
                    ((NotificationManager) systemService).cancel(i);
                }
            }
        }
        this.v = new CoveJsInterface(this, this);
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding3 = this.u;
        if (activityBoatCoinsWebViewerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding3 = null;
        }
        WebView webView2 = activityBoatCoinsWebViewerBinding3.webViewBoatCoins;
        CoveJsInterface coveJsInterface = this.v;
        Intrinsics.checkNotNull(coveJsInterface);
        webView2.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding4 = this.u;
        if (activityBoatCoinsWebViewerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding4 = null;
        }
        activityBoatCoinsWebViewerBinding4.webViewBoatCoins.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        if (AppUtils.isEmpty(value)) {
            return;
        }
        if (!AppUtils.isEmpty(PreferenceManager.getInstance().getDeviceAgent())) {
            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
            Intrinsics.checkNotNull(value);
            value = companion2.appendUriForAnalytics(value, this.t);
        }
        LogHelper.d(this.p, "WEBVIEW URL: " + value);
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding5 = this.u;
        if (activityBoatCoinsWebViewerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding5 = null;
        }
        WebSettings settings = activityBoatCoinsWebViewerBinding5.webViewBoatCoins.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "binding.webViewBoatCoins.settings");
        settings.setJavaScriptEnabled(true);
        String string = getString(R.string.loading2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading2)");
        showProgressWithTitle(string);
        settings.setMixedContentMode(0);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        settings.setDomStorageEnabled(true);
        StringBuilder sb = new StringBuilder();
        sb.append("/data/data/");
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding6 = this.u;
        if (activityBoatCoinsWebViewerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding6 = null;
        }
        sb.append(activityBoatCoinsWebViewerBinding6.webViewBoatCoins.getContext().getPackageName());
        sb.append("/databases/");
        settings.setDatabasePath(sb.toString());
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding7 = this.u;
        if (activityBoatCoinsWebViewerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBoatCoinsWebViewerBinding7 = null;
        }
        activityBoatCoinsWebViewerBinding7.webViewBoatCoins.setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinsWebViewer$onCreate$1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!ActivityBoatCoinsWebViewer.this.getRedirect()) {
                    ActivityBoatCoinsWebViewer.this.setLoadingFinished(true);
                }
                if (!ActivityBoatCoinsWebViewer.this.getLoadingFinished() || ActivityBoatCoinsWebViewer.this.getRedirect()) {
                    ActivityBoatCoinsWebViewer.this.setRedirect(false);
                }
                ActivityBoatCoinsWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(@Nullable WebView webView3, @Nullable String str5, @Nullable Bitmap bitmap) {
                ActivityBoatCoinsWebViewer.this.setLoadingFinished(false);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i2, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                ActivityBoatCoinsWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!ActivityBoatCoinsWebViewer.this.getLoadingFinished()) {
                    ActivityBoatCoinsWebViewer.this.setRedirect(true);
                }
                ActivityBoatCoinsWebViewer.this.setLoadingFinished(false);
                view.loadUrl(url);
                return true;
            }
        });
        ActivityBoatCoinsWebViewerBinding activityBoatCoinsWebViewerBinding8 = this.u;
        if (activityBoatCoinsWebViewerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBoatCoinsWebViewerBinding = activityBoatCoinsWebViewerBinding8;
        }
        WebView webView3 = activityBoatCoinsWebViewerBinding.webViewBoatCoins;
        Intrinsics.checkNotNull(value);
        webView3.loadUrl(value);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.q) {
            return;
        }
        String string = getString(R.string.loading2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading2)");
        showProgressWithTitle(string);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.websupport.CoveJsInterface.OnJavascriptListener
    public void openLinkInBrowser(@Nullable String str) {
        if (str != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        }
    }

    @NotNull
    public final BoatCoinsScreenCaller screenName() {
        String str = this.s;
        if (str != null) {
            BoatCoinsScreenCaller boatCoinsScreenCaller = BoatCoinsScreenCaller.HP_BANNER;
            if (kotlin.text.m.equals$default(str, boatCoinsScreenCaller.name(), false, 2, null)) {
                return boatCoinsScreenCaller;
            }
        }
        String str2 = this.s;
        if (str2 != null) {
            BoatCoinsScreenCaller boatCoinsScreenCaller2 = BoatCoinsScreenCaller.HP_TOP_ICON;
            if (kotlin.text.m.equals$default(str2, boatCoinsScreenCaller2.name(), false, 2, null)) {
                return boatCoinsScreenCaller2;
            }
        }
        return BoatCoinsScreenCaller.NULL;
    }

    public final void setAxValue(@Nullable String str) {
        this.t = str;
    }

    public final void setCoveJsInterface(@Nullable CoveJsInterface coveJsInterface) {
        this.v = coveJsInterface;
    }

    public final void setLoadingFinished(boolean z) {
        this.q = z;
    }

    public final void setRedirect(boolean z) {
        this.r = z;
    }

    public final void setScreenType(@Nullable String str) {
        this.s = str;
    }
}
