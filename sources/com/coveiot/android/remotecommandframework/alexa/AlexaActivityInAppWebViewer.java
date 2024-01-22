package com.coveiot.android.remotecommandframework.alexa;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener;
import com.coveiot.android.remotecommandframework.alexa.jsinterface.CoveJsInterface;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AlexaActivityInAppWebViewer extends BaseActivity implements AlexaJsInterfaceListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String v = WorkoutConstants.INTENT_EXTRA_TITLE;
    @NotNull
    public static final String w = "INTENT_EXTRA_URL";
    @NotNull
    public static final String x = "IS_HEADER_REQUIRED";
    public TextView backButtonIV;
    public WebView mWebView;
    public boolean r;
    @Nullable
    public CoveJsInterface s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "ActivityInAppWebViewer";
    public boolean q = true;
    @NotNull
    public final Handler t = new Handler(Looper.getMainLooper());
    public boolean u = true;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getINTENT_EXTRA_TITLE() {
            return AlexaActivityInAppWebViewer.v;
        }

        @NotNull
        public final String getINTENT_EXTRA_URL() {
            return AlexaActivityInAppWebViewer.w;
        }

        @NotNull
        public final String getIS_HEADER_REQURIED() {
            return AlexaActivityInAppWebViewer.x;
        }
    }

    public static final void u(final AlexaActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMWebView() != null) {
            if ((str == null || str.length() == 0) || this$0.isFinishing()) {
                return;
            }
            WebView mWebView = this$0.getMWebView();
            mWebView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + str + HexStringBuilder.COMMENT_END_CHAR, new ValueCallback() { // from class: com.coveiot.android.remotecommandframework.alexa.b
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    AlexaActivityInAppWebViewer.v(AlexaActivityInAppWebViewer.this, (String) obj);
                }
            });
        }
    }

    public static final void v(AlexaActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.p, str);
    }

    public static final void w(AlexaActivityInAppWebViewer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void x(AlexaActivityInAppWebViewer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
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

    @Nullable
    public final CoveJsInterface getAlexaJsInterface() {
        return this.s;
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

    @NotNull
    public final Handler getHandler() {
        return this.t;
    }

    public final boolean getLoadingFinished() {
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

    public final boolean getRedirect() {
        return this.r;
    }

    public final boolean isHeaderRequired() {
        return this.u;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener
    public void onAccountLinkingCompleted(boolean z) {
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener
    public void onAppOut(@Nullable final String str) {
        this.t.post(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.d
            @Override // java.lang.Runnable
            public final void run() {
                AlexaActivityInAppWebViewer.u(AlexaActivityInAppWebViewer.this, str);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_in_app_web_viewer);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<WebView>(R.id.web_view)");
        setMWebView((WebView) findViewById);
        View findViewById2 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.toolbar_back_arrow)");
        setBackButtonIV((TextView) findViewById2);
        this.s = new CoveJsInterface(this, this);
        WebView mWebView = getMWebView();
        CoveJsInterface coveJsInterface = this.s;
        Intrinsics.checkNotNull(coveJsInterface);
        mWebView.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaActivityInAppWebViewer.w(AlexaActivityInAppWebViewer.this, view);
            }
        });
        Intent intent = getIntent();
        String str = x;
        if (intent.hasExtra(str)) {
            this.u = getIntent().getBooleanExtra(str, true);
        }
        if (!this.u) {
            constraintLayout.setVisibility(8);
        } else {
            constraintLayout.setVisibility(0);
        }
        String stringExtra = getIntent().getStringExtra(w);
        String stringExtra2 = getIntent().getStringExtra(v);
        Intrinsics.checkNotNull(stringExtra2);
        if (stringExtra2.length() > 0) {
            ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(stringExtra2);
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
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(getMWebView(), true);
        }
        if (i >= 21) {
            settings.setMixedContentMode(0);
        }
        String string = getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading)");
        showProgressWithTitle(string);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.remotecommandframework.alexa.AlexaActivityInAppWebViewer$onCreate$2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!AlexaActivityInAppWebViewer.this.getRedirect()) {
                    AlexaActivityInAppWebViewer.this.setLoadingFinished(true);
                }
                if (!AlexaActivityInAppWebViewer.this.getLoadingFinished() || AlexaActivityInAppWebViewer.this.getRedirect()) {
                    AlexaActivityInAppWebViewer.this.setRedirect(false);
                }
                AlexaActivityInAppWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(@Nullable WebView webView, @Nullable String str2, @Nullable Bitmap bitmap) {
                AlexaActivityInAppWebViewer.this.setLoadingFinished(false);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i2, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                AlexaActivityInAppWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!AlexaActivityInAppWebViewer.this.getLoadingFinished()) {
                    AlexaActivityInAppWebViewer.this.setRedirect(true);
                }
                AlexaActivityInAppWebViewer.this.setLoadingFinished(false);
                view.loadUrl(url);
                return true;
            }
        });
        WebView mWebView2 = getMWebView();
        Intrinsics.checkNotNull(stringExtra);
        mWebView2.loadUrl(stringExtra);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.q) {
            return;
        }
        String string = getString(R.string.loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading)");
        showProgressWithTitle(string);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        dismissProgress();
    }

    public final void setAlexaJsInterface(@Nullable CoveJsInterface coveJsInterface) {
        this.s = coveJsInterface;
    }

    public final void setBackButtonIV(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backButtonIV = textView;
    }

    public final void setHeaderRequired(boolean z) {
        this.u = z;
    }

    public final void setLoadingFinished(boolean z) {
        this.q = z;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final void setRedirect(boolean z) {
        this.r = z;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener
    public void winClose() {
        this.t.post(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.c
            @Override // java.lang.Runnable
            public final void run() {
                AlexaActivityInAppWebViewer.x(AlexaActivityInAppWebViewer.this);
            }
        });
    }
}
