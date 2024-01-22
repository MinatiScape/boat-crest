package com.coveiot.android.sportsnotification;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SConsentRequest;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsDisclaimerActivity extends BaseActivity {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FRAGMENTHOMEDASHBOARD = "fragment_dashboard_home";
    @NotNull
    public static final String IS_FROM = "is_from";
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public Button btnAccept;
    public Button btnReject;
    public WebView mWebView;
    public TextView toolbarBackArrow;
    public TextView toolbarTitle;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final void u(SportsDisclaimerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t(true);
    }

    public static final void v(SportsDisclaimerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t(false);
    }

    public static final void x(SportsDisclaimerActivity this$0, View view) {
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

    @NotNull
    public final Button getBtnAccept() {
        Button button = this.btnAccept;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btnAccept");
        return null;
    }

    @NotNull
    public final Button getBtnReject() {
        Button button = this.btnReject;
        if (button != null) {
            return button;
        }
        Intrinsics.throwUninitializedPropertyAccessException("btnReject");
        return null;
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

    @NotNull
    public final TextView getToolbarBackArrow() {
        TextView textView = this.toolbarBackArrow;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toolbarBackArrow");
        return null;
    }

    @NotNull
    public final TextView getToolbarTitle() {
        TextView textView = this.toolbarTitle;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("toolbarTitle");
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"JavascriptInterface"})
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_disclaimer_notify);
        View findViewById = findViewById(R.id.buttonAccept);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.buttonAccept)");
        setBtnAccept((Button) findViewById);
        View findViewById2 = findViewById(R.id.buttonReject);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.buttonReject)");
        setBtnReject((Button) findViewById2);
        View findViewById3 = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.toolbar_title)");
        setToolbarTitle((TextView) findViewById3);
        View findViewById4 = findViewById(R.id.toolbar_back_arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(R.id.toolbar_back_arrow)");
        setToolbarBackArrow((TextView) findViewById4);
        View findViewById5 = findViewById(R.id.web_view);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.webkit.WebView");
        setMWebView((WebView) findViewById5);
        Intent intent = getIntent();
        if ((intent != null ? intent.getStringExtra(IS_FROM) : null) != null) {
            if (kotlin.text.m.equals$default(intent.getStringExtra(IS_FROM), FRAGMENTHOMEDASHBOARD, false, 2, null)) {
                ((ConstraintLayout) _$_findCachedViewById(R.id.clButtons)).setVisibility(0);
            }
        } else {
            ((ConstraintLayout) _$_findCachedViewById(R.id.clButtons)).setVisibility(8);
        }
        w();
        String sportsDisclaimerDocUrl = SessionManager.getInstance(this).getSportsDisclaimerDocUrl() != null ? SessionManager.getInstance(this).getSportsDisclaimerDocUrl() : "https://static.coveiot.com/borrelly/sports-disclaimer.html";
        if (!AppUtils.isEmpty(sportsDisclaimerDocUrl)) {
            WebSettings settings = getMWebView().getSettings();
            Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
            settings.setJavaScriptEnabled(true);
            settings.setMixedContentMode(0);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            getMWebView().setWebViewClient(new WebViewClient() { // from class: com.coveiot.android.sportsnotification.SportsDisclaimerActivity$onCreate$1
                @Override // android.webkit.WebViewClient
                public void onPageFinished(@NotNull WebView view, @NotNull String url) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    SportsDisclaimerActivity.this.dismissProgress();
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
                    SportsDisclaimerActivity.this.showProgress();
                    view.loadUrl(url);
                    return true;
                }
            });
            WebView mWebView = getMWebView();
            Intrinsics.checkNotNull(sportsDisclaimerDocUrl);
            mWebView.loadUrl(sportsDisclaimerDocUrl);
        }
        getMWebView().setOnKeyListener(new View.OnKeyListener() { // from class: com.coveiot.android.sportsnotification.SportsDisclaimerActivity$onCreate$2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(@NotNull View v, int i, @NotNull KeyEvent event) {
                Intrinsics.checkNotNullParameter(v, "v");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 0) {
                    WebView webView = (WebView) v;
                    if (i == 4 && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                    return false;
                }
                return false;
            }
        });
        getBtnAccept().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsDisclaimerActivity.u(SportsDisclaimerActivity.this, view);
            }
        });
        getBtnReject().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsDisclaimerActivity.v(SportsDisclaimerActivity.this, view);
            }
        });
    }

    public final void setBtnAccept(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btnAccept = button;
    }

    public final void setBtnReject(@NotNull Button button) {
        Intrinsics.checkNotNullParameter(button, "<set-?>");
        this.btnReject = button;
    }

    public final void setMWebView(@NotNull WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final void setToolbarBackArrow(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.toolbarBackArrow = textView;
    }

    public final void setToolbarTitle(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.toolbarTitle = textView;
    }

    public final void t(final boolean z) {
        if (AppUtils.isNetConnected(this)) {
            SConsentRequest sConsentRequest = new SConsentRequest();
            sConsentRequest.setSportsConsent(Boolean.valueOf(z));
            CoveUserAppSettings.saveUserConsent(sConsentRequest, new CoveApiListener<ActivityRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.SportsDisclaimerActivity$callUserConsentApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SportsDisclaimerActivity sportsDisclaimerActivity = this;
                    Toast.makeText(sportsDisclaimerActivity, sportsDisclaimerActivity.getString(R.string.failure_message), 0).show();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable ActivityRes activityRes) {
                    if (z) {
                        SportsDisclaimerActivity sportsDisclaimerActivity = this;
                        Toast.makeText(sportsDisclaimerActivity, sportsDisclaimerActivity.getString(R.string.setting_saved_successfully), 0).show();
                        SportsPreference.Companion.saveDisclaimerAccepted(this, true);
                        this.startActivity(new Intent(this, SportsNotificationActivity.class));
                        this.finish();
                        return;
                    }
                    SportsPreference.Companion.saveDisclaimerAccepted(this, false);
                    this.finish();
                }
            });
            return;
        }
        Toast.makeText(this, getResources().getString(R.string.no_internet_connection), 0).show();
    }

    public final void w() {
        getToolbarTitle().setText(getString(R.string.disclaimer));
        getToolbarBackArrow().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportsDisclaimerActivity.x(SportsDisclaimerActivity.this, view);
            }
        });
    }
}
