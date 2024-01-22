package com.coveiot.android.leonardo.websupport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.websupport.CoveJsInterface;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityInAppWebViewer extends BaseActivity implements CoveJsInterface.OnJavascriptListener {
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

    public static final void u(final ActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        StringBuilder sb = new StringBuilder();
        sb.append("appOutScript: ");
        Intrinsics.checkNotNull(str);
        sb.append(str);
        LogHelper.d(str2, sb.toString());
        this$0.getMWebView().evaluateJavascript(str, new ValueCallback() { // from class: com.coveiot.android.leonardo.websupport.b
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                ActivityInAppWebViewer.v(ActivityInAppWebViewer.this, (String) obj);
            }
        });
    }

    public static final void v(ActivityInAppWebViewer this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String str2 = this$0.p;
        LogHelper.d(str2, "onReceiveValue: " + str);
    }

    public static final void w(ActivityInAppWebViewer this$0, View view) {
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

    @Override // com.coveiot.android.leonardo.websupport.CoveJsInterface.OnJavascriptListener
    public void evaluateJavascript(@Nullable final String str) {
        runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.websupport.c
            @Override // java.lang.Runnable
            public final void run() {
                ActivityInAppWebViewer.u(ActivityInAppWebViewer.this, str);
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
        return this.s;
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
        this.s = new CoveJsInterface(this, this);
        WebView mWebView = getMWebView();
        CoveJsInterface coveJsInterface = this.s;
        Intrinsics.checkNotNull(coveJsInterface);
        mWebView.addJavascriptInterface(coveJsInterface, "CoveJsInterface");
        getMWebView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getBackButtonIV().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.websupport.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityInAppWebViewer.w(ActivityInAppWebViewer.this, view);
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
        String deviceAgent = PreferenceManager.getInstance().getDeviceAgent();
        if (!AppUtils.isEmpty(deviceAgent)) {
            Intrinsics.checkNotNull(stringExtra);
            Intrinsics.checkNotNullExpressionValue(deviceAgent, "deviceAgent");
            stringExtra = t(stringExtra, deviceAgent);
        }
        String str = this.p;
        LogHelper.d(str, "WEBVIEW URL: " + stringExtra);
        WebSettings settings = getMWebView().getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
        String string = getString(R.string.loading2);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading2)");
        showProgressWithTitle(string);
        settings.setMixedContentMode(0);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.leonardo.websupport.ActivityInAppWebViewer$onCreate$2
            @Override // android.webkit.WebViewClient
            public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!ActivityInAppWebViewer.this.getRedirect()) {
                    ActivityInAppWebViewer.this.setLoadingFinished(true);
                }
                if (!ActivityInAppWebViewer.this.getLoadingFinished() || ActivityInAppWebViewer.this.getRedirect()) {
                    ActivityInAppWebViewer.this.setRedirect(false);
                }
                ActivityInAppWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(@Nullable WebView webView, @Nullable String str2, @Nullable Bitmap bitmap) {
                ActivityInAppWebViewer.this.setLoadingFinished(false);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(@NotNull WebView view, int i, @NotNull String description, @NotNull String failingUrl) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(description, "description");
                Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
                ActivityInAppWebViewer.this.dismissProgress();
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (!ActivityInAppWebViewer.this.getLoadingFinished()) {
                    ActivityInAppWebViewer.this.setRedirect(true);
                }
                ActivityInAppWebViewer.this.setLoadingFinished(false);
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

    public final void setBackButtonIV(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.backButtonIV = textView;
    }

    public final void setCoveJsInterface(@Nullable CoveJsInterface coveJsInterface) {
        this.s = coveJsInterface;
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

    public final String t(String str, String str2) {
        String str3;
        try {
            URI uri = new URI(str);
            String str4 = "deviceAgent=" + str2;
            String query = uri.getQuery();
            if (query != null) {
                String str5 = query + Typography.amp + str4;
                if (str5 != null) {
                    str3 = str5;
                    String uri2 = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), str3, uri.getFragment()).toString();
                    Intrinsics.checkNotNullExpressionValue(uri2, "{\n            val oldUri…dUri.toString()\n        }");
                    return uri2;
                }
            }
            str3 = str4;
            String uri22 = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), str3, uri.getFragment()).toString();
            Intrinsics.checkNotNullExpressionValue(uri22, "{\n            val oldUri…dUri.toString()\n        }");
            return uri22;
        } catch (URISyntaxException unused) {
            return str + "?deviceAgent=" + str2;
        }
    }
}
