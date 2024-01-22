package com.coveiot.android.leonardo.threshold;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.threshold.AlertJsInterface;
import com.coveiot.android.leonardo.threshold.model.AlertWebDataModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveHealthStatusApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGetHealthStatusRes;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public final class ActivityAlertWebViewer extends BaseActivity implements AlertJsInterface.OnJavascriptListener {
    public ImageView backButtonIV;
    public WebView mWebView;
    @Nullable
    public AlertJsInterface q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityAlertWebViewer";

    public static final void t(final ActivityAlertWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        StringBuilder sb = new StringBuilder();
        sb.append("appOutScript: ");
        Intrinsics.checkNotNull(str);
        sb.append(str);
        Log.d(str2, sb.toString());
        this$0.getMWebView().evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.leonardo.threshold.b
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityAlertWebViewer.u(ActivityAlertWebViewer.this, (String) obj);
            }
        });
    }

    public static final void u(ActivityAlertWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        Log.d(str2, "onReceiveValue: " + str);
    }

    public static final void w(ActivityAlertWebViewer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.threshold.c
            @Override // java.lang.Runnable
            public final void run() {
                ActivityAlertWebViewer.t(ActivityAlertWebViewer.this, str);
            }
        });
    }

    @NotNull
    public final ImageView getBackButtonIV() {
        ImageView imageView = this.backButtonIV;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backButtonIV");
        return null;
    }

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    @NotNull
    public String getLanguage() {
        String language = PreferenceManager.getInstance().getLanguage();
        Intrinsics.checkNotNullExpressionValue(language, "getInstance().language");
        return language;
    }

    @NotNull
    public final WebView getMWebView() {
        WebView webView = this.mWebView;
        if (webView != null) {
            return webView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        return null;
    }

    @Nullable
    public final AlertJsInterface getWorkoutJsInterface() {
        return this.q;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getMWebView().canGoBack()) {
            getMWebView().goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alert_web_viewer);
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.webkit.WebView");
        setMWebView((WebView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.toolbar_back_arrow)");
        setBackButtonIV((ImageView) findViewById2);
        Bundle extras = getIntent().getExtras();
        Serializable serializable = extras != null ? extras.getSerializable("AlertWebDataBean") : null;
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.leonardo.threshold.model.AlertWebDataModel");
        String string = getString(R.string.loading2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading2)");
        showProgressWithTitle(string);
        this.q = new AlertJsInterface(this, this, (AlertWebDataModel) serializable);
        WebView mWebView = getMWebView();
        AlertJsInterface alertJsInterface = this.q;
        Intrinsics.checkNotNull(alertJsInterface);
        mWebView.addJavascriptInterface(alertJsInterface, "CoveJsInterface");
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.threshold.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAlertWebViewer.w(ActivityAlertWebViewer.this, view);
            }
        });
        String stringExtra = getIntent().getStringExtra(AppConstants.INTENT_EXTRA_URL.getValue());
        String stringExtra2 = getIntent().getStringExtra(AppConstants.INTENT_EXTRA_TITLE.getValue());
        Intrinsics.checkNotNull(stringExtra2);
        if (stringExtra2.length() > 0) {
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(stringExtra2);
        }
        if (AppUtils.isEmpty(stringExtra)) {
            return;
        }
        WebSettings settings = getMWebView().getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
        settings.setMixedContentMode(0);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath("/data/data/" + getMWebView().getContext().getPackageName() + "/databases/");
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.leonardo.threshold.ActivityAlertWebViewer$onCreate$2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                ActivityAlertWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                view.loadUrl(url);
                return true;
            }
        });
        WebView mWebView2 = getMWebView();
        Intrinsics.checkNotNull(stringExtra);
        mWebView2.loadUrl(stringExtra);
    }

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    public void onHealthQuestionaryComplete() {
        v();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    public void openLinkInBrowser(@Nullable String str) {
        if (str != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            startActivity(intent);
        }
    }

    public final void setBackButtonIV(@NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.backButtonIV = imageView;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final void setWorkoutJsInterface(@Nullable AlertJsInterface alertJsInterface) {
        this.q = alertJsInterface;
    }

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    public void updatePlan() {
        v();
    }

    @Override // com.coveiot.android.leonardo.threshold.AlertJsInterface.OnJavascriptListener
    public void updateProfile(@NotNull JSONObject profileJsonObj) {
        Intrinsics.checkNotNullParameter(profileJsonObj, "profileJsonObj");
    }

    public final void v() {
        CoveHealthStatusApi.getHealthStatus(new CoveApiListener<SGetHealthStatusRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.threshold.ActivityAlertWebViewer$getHealthStatusFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SGetHealthStatusRes sGetHealthStatusRes) {
                if (sGetHealthStatusRes != null) {
                    try {
                        Intent intent = ActivityAlertWebViewer.this.getIntent();
                        intent.putExtra(ActivityDashboardNew.Companion.getEXTRA_IS_USER_FLAGGED(), sGetHealthStatusRes.getData().getTemperature().isIsFlagged());
                        ActivityAlertWebViewer.this.setResult(-1, intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
