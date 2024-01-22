package com.coveiot.android.healthbuddies.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.android.healthbuddies.constants.Constants;
import com.coveiot.android.healthbuddies.utils.CoveJsInterface;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityInAppWebViewer extends BaseActivity implements CoveJsInterface.OnJavascriptListener {
    public TextView backButtonIV;
    public WebView mWebView;
    @Nullable
    public CoveJsInterface q;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityInAppWebViewer";

    public static final void t(final ActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "appOutScript: " + str);
        WebView mWebView = this$0.getMWebView();
        Intrinsics.checkNotNull(str);
        mWebView.evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.healthbuddies.activities.b
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewer.u(ActivityInAppWebViewer.this, (String) obj);
            }
        });
    }

    public static final void u(ActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void v(ActivityInAppWebViewer this$0, View view) {
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

    @Override // com.coveiot.android.healthbuddies.utils.CoveJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.healthbuddies.activities.c
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewer.t(ActivityInAppWebViewer.this, str);
            }
        });
    }

    @NotNull
    public final TextView getBackButtonIV() {
        TextView textView = this.backButtonIV;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backButtonIV");
        return null;
    }

    @Nullable
    public final CoveJsInterface getCoveJsInterface() {
        return this.q;
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_in_app_web_viewer);
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.webkit.WebView");
        setMWebView((WebView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.toolbar_back_arrow)");
        setBackButtonIV((TextView) findViewById2);
        String string = getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading)");
        showProgressWithTitle(string);
        this.q = new CoveJsInterface(this, this);
        WebView mWebView = getMWebView();
        CoveJsInterface coveJsInterface = this.q;
        Intrinsics.checkNotNull(coveJsInterface);
        mWebView.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.healthbuddies.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInAppWebViewer.v(ActivityInAppWebViewer.this, view);
            }
        });
        String stringExtra = getIntent().getStringExtra(Constants.INTENT_EXTRA_URL.getValue());
        String stringExtra2 = getIntent().getStringExtra(Constants.INTENT_EXTRA_TITLE.getValue());
        Intrinsics.checkNotNull(stringExtra2);
        if (stringExtra2.length() > 0) {
            ((TextView) _$_findCachedViewById(R.id.toolbar).findViewById(R.id.toolbar_title)).setText(stringExtra2);
        }
        if (AppUtils.isEmpty(stringExtra)) {
            return;
        }
        String deviceAgent = PreferenceManager.getInstance().getDeviceAgent();
        if (!AppUtils.isEmpty(deviceAgent)) {
            stringExtra = stringExtra + "?deviceAgent=" + deviceAgent;
        }
        LogHelper.d(this.p, "WEBVIEW URL: " + stringExtra);
        WebSettings settings = getMWebView().getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
        settings.setMixedContentMode(0);
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.healthbuddies.activities.ActivityInAppWebViewer$onCreate$2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                ActivityInAppWebViewer.this.dismissProgress();
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

    public final void setBackButtonIV(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backButtonIV = textView;
    }

    public final void setCoveJsInterface(@Nullable CoveJsInterface coveJsInterface) {
        this.q = coveJsInterface;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }
}
