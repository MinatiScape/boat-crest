package com.clevertap.android.sdk.inapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.toolbox.JsonRequest;
import com.clevertap.android.sdk.CTWebInterface;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.customviews.CloseImageView;
import com.clevertap.android.sdk.utils.UriHelper;
import java.net.URLDecoder;
/* loaded from: classes2.dex */
public abstract class CTInAppBaseFullHtmlFragment extends CTInAppBaseFullFragment {
    public e webView;

    /* loaded from: classes2.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CTInAppBaseFullHtmlFragment.this.didDismiss(null);
        }
    }

    /* loaded from: classes2.dex */
    public class b extends WebViewClient {
        public b() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String string;
            try {
                Bundle allKeyValuePairs = UriHelper.getAllKeyValuePairs(str, false);
                if (allKeyValuePairs.containsKey(Constants.KEY_C2A) && (string = allKeyValuePairs.getString(Constants.KEY_C2A)) != null) {
                    String[] split = string.split("__dl__");
                    if (split.length == 2) {
                        allKeyValuePairs.putString(Constants.KEY_C2A, URLDecoder.decode(split[0], "UTF-8"));
                        str = split[1];
                    }
                }
                CTInAppBaseFullHtmlFragment.this.c(allKeyValuePairs, null);
                Logger.d("Executing call to action for in-app: " + str);
                CTInAppBaseFullHtmlFragment.this.e(str, allKeyValuePairs);
            } catch (Throwable th) {
                Logger.v("Error parsing the in-app notification action!", th);
            }
            return true;
        }
    }

    public RelativeLayout.LayoutParams getLayoutParamsForCloseButton() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(2, this.webView.getId());
        layoutParams.addRule(1, this.webView.getId());
        int i = -(h(40) / 2);
        layoutParams.setMargins(i, 0, 0, i);
        return layoutParams;
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        y();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        return u(layoutInflater, viewGroup);
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        y();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public final View u(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        try {
            View inflate = layoutInflater.inflate(R.layout.inapp_html_full, viewGroup, false);
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.inapp_html_full_relative_layout);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            v(layoutParams);
            this.webView = new e(this.j, this.l.getWidth(), this.l.getHeight(), this.l.r(), this.l.j());
            this.webView.setWebViewClient(new b());
            if (this.l.v()) {
                this.webView.getSettings().setJavaScriptEnabled(true);
                this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
                this.webView.getSettings().setAllowContentAccess(false);
                this.webView.getSettings().setAllowFileAccess(false);
                if (Build.VERSION.SDK_INT >= 16) {
                    this.webView.getSettings().setAllowFileAccessFromFileURLs(false);
                }
                this.webView.addJavascriptInterface(new CTWebInterface(CleverTapAPI.instanceWithConfig(getActivity(), this.i), this), Constants.CLEVERTAP_LOG_TAG);
            }
            if (x()) {
                relativeLayout.setBackground(new ColorDrawable(-1157627904));
            } else {
                relativeLayout.setBackground(new ColorDrawable(0));
            }
            relativeLayout.addView(this.webView, layoutParams);
            if (w()) {
                this.h = new CloseImageView(this.j);
                RelativeLayout.LayoutParams layoutParamsForCloseButton = getLayoutParamsForCloseButton();
                this.h.setOnClickListener(new a());
                relativeLayout.addView(this.h, layoutParamsForCloseButton);
            }
            return inflate;
        } catch (Throwable th) {
            this.i.getLogger().verbose(this.i.getAccountId(), "Fragment view not created", th);
            return null;
        }
    }

    public final void v(RelativeLayout.LayoutParams layoutParams) {
        char p = this.l.p();
        if (p == 'b') {
            layoutParams.addRule(12);
        } else if (p == 'c') {
            layoutParams.addRule(13);
        } else if (p == 'l') {
            layoutParams.addRule(9);
        } else if (p == 'r') {
            layoutParams.addRule(11);
        } else if (p == 't') {
            layoutParams.addRule(10);
        }
        layoutParams.setMargins(0, 0, 0, 0);
    }

    public final boolean w() {
        return this.l.x();
    }

    public final boolean x() {
        return this.l.t();
    }

    public final void y() {
        this.webView.a();
        if (this.l.g().isEmpty()) {
            Point point = this.webView.h;
            int i = point.y;
            int i2 = point.x;
            float f = getResources().getDisplayMetrics().density;
            String replaceFirst = this.l.k().replaceFirst("<head>", "<head>" + ("<style>body{width:" + ((int) (i2 / f)) + "px; height: " + ((int) (i / f)) + "px; margin: 0; padding:0;}</style>"));
            Logger.v("Density appears to be " + f);
            this.webView.setInitialScale((int) (f * 100.0f));
            this.webView.loadDataWithBaseURL(null, replaceFirst, "text/html", JsonRequest.PROTOCOL_CHARSET, null);
            return;
        }
        String g = this.l.g();
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.loadUrl(g);
    }
}
