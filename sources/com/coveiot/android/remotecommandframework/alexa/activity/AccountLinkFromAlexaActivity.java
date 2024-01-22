package com.coveiot.android.remotecommandframework.alexa.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.coveiot.android.remotecommandframework.R;
import com.coveiot.android.remotecommandframework.RcfPreferenceManager;
import com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener;
import com.coveiot.android.remotecommandframework.alexa.jsinterface.CoveJsInterface;
import com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AccountLinkFromAlexaActivity extends BaseActivity implements AlexaJsInterfaceListener {
    public CoveJsInterface coveJsInterface;
    public WebView mWebView;
    public boolean r;
    public boolean s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "AccountLinkFromAlexaActivity";
    public boolean q = true;
    @NotNull
    public final Handler t = new Handler(Looper.getMainLooper());

    public static final void B(AccountLinkFromAlexaActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing()) {
            return;
        }
        this$0.finish();
    }

    public static final void v(AccountLinkFromAlexaActivity this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.isFinishing() || !z) {
            return;
        }
        this$0.A();
    }

    public static final void w(final AccountLinkFromAlexaActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getMWebView() != null) {
            if ((str == null || str.length() == 0) || this$0.isFinishing()) {
                return;
            }
            WebView mWebView = this$0.getMWebView();
            mWebView.evaluateJavascript("javascript:CoveJsInterface.appOut(" + str + HexStringBuilder.COMMENT_END_CHAR, new ValueCallback() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.b
                @Override // android.webkit.ValueCallback
                public final void onReceiveValue(Object obj) {
                    AccountLinkFromAlexaActivity.x(AccountLinkFromAlexaActivity.this, (String) obj);
                }
            });
        }
    }

    public static final void x(AccountLinkFromAlexaActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.p, str);
    }

    public static final void z(AccountLinkFromAlexaActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void A() {
        Intent intent = new Intent(this, AlexaRemoteCommandFrameworkService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
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

    @NotNull
    public final CoveJsInterface getCoveJsInterface() {
        CoveJsInterface coveJsInterface = this.coveJsInterface;
        if (coveJsInterface != null) {
            return coveJsInterface;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coveJsInterface");
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

    @NotNull
    public final String getTAG() {
        return this.p;
    }

    public final boolean isLinkingStartedFromAlexaAppAndNotLoggedIn() {
        return this.s;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener
    public void onAccountLinkingCompleted(final boolean z) {
        this.t.post(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.e
            @Override // java.lang.Runnable
            public final void run() {
                AccountLinkFromAlexaActivity.v(AccountLinkFromAlexaActivity.this, z);
            }
        });
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.jsinterface.AlexaJsInterfaceListener
    public void onAppOut(@Nullable final String str) {
        this.t.post(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.d
            @Override // java.lang.Runnable
            public final void run() {
                AccountLinkFromAlexaActivity.w(AccountLinkFromAlexaActivity.this, str);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (!SessionManager.getInstance(this).isDevicePaired()) {
            RcfPreferenceManager singletonHolder = RcfPreferenceManager.Companion.getInstance(this);
            Intent intent = getIntent();
            singletonHolder.saveAlexaAccountLinkingUri(intent != null ? intent.getData() : null);
            startActivity(new Intent(this, LinkToAlexaNotLoggedInActivity.class));
            finish();
        }
        setContentView(R.layout.activity_link_from_alexa);
        setCoveJsInterface(new CoveJsInterface(this, this));
        y();
        View findViewById = findViewById(R.id.web_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.web_view)");
        setMWebView((WebView) findViewById);
        getMWebView().setBackgroundColor(getColor(R.color.background_color_dark));
        Intent intent2 = getIntent();
        Uri data = intent2 != null ? intent2.getData() : null;
        if (getIntent() != null && getIntent().hasExtra("wasNotLoggedIn")) {
            Intent intent3 = getIntent();
            this.s = intent3 != null && intent3.getBooleanExtra("wasNotLoggedIn", false);
        }
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
        settings.setMixedContentMode(0);
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.AccountLinkFromAlexaActivity$onCreate$1
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                AccountLinkFromAlexaActivity.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(@Nullable WebView webView, @Nullable String str, @Nullable Bitmap bitmap) {
                AccountLinkFromAlexaActivity.this.showProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i2, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                AccountLinkFromAlexaActivity.this.dismissProgress();
            }
        });
        getMWebView().addJavascriptInterface(getCoveJsInterface(), CoveJsInterface.TAG);
        WebView mWebView = getMWebView();
        Intrinsics.checkNotNull(data);
        mWebView.loadUrl(data.toString());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (getMWebView() != null) {
            getMWebView().destroy();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        RcfPreferenceManager.Companion.getInstance(this).saveAlexaAccountLinkingUri(null);
    }

    public final void setCoveJsInterface(@NotNull CoveJsInterface coveJsInterface) {
        Intrinsics.checkNotNullParameter(coveJsInterface, "<set-?>");
        this.coveJsInterface = coveJsInterface;
    }

    public final void setLinkingStartedFromAlexaAppAndNotLoggedIn(boolean z) {
        this.s = z;
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
        this.t.post(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.c
            @Override // java.lang.Runnable
            public final void run() {
                AccountLinkFromAlexaActivity.B(AccountLinkFromAlexaActivity.this);
            }
        });
    }

    public final void y() {
        ((TextView) findViewById(R.id.toolbar_title)).setText("Account Linking from Alexa App");
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.remotecommandframework.alexa.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccountLinkFromAlexaActivity.z(AccountLinkFromAlexaActivity.this, view);
            }
        });
    }
}
