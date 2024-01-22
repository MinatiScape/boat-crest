package com.clevertap.android.sdk.inapp;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.toolbox.JsonRequest;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.utils.UriHelper;
import java.net.URLDecoder;
/* loaded from: classes2.dex */
public abstract class CTInAppBasePartialHtmlFragment extends CTInAppBasePartialFragment implements View.OnTouchListener, View.OnLongClickListener {
    public final GestureDetector p = new GestureDetector(new b());
    public e q;

    /* loaded from: classes2.dex */
    public class b extends GestureDetector.SimpleOnGestureListener {

        /* loaded from: classes2.dex */
        public class a implements Animation.AnimationListener {
            public a() {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CTInAppBasePartialHtmlFragment.this.didDismiss(null);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        }

        public b() {
        }

        public final boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, boolean z) {
            TranslateAnimation translateAnimation;
            AnimationSet animationSet = new AnimationSet(true);
            if (z) {
                translateAnimation = new TranslateAnimation(0.0f, CTInAppBasePartialHtmlFragment.this.h(50), 0.0f, 0.0f);
            } else {
                translateAnimation = new TranslateAnimation(0.0f, -CTInAppBasePartialHtmlFragment.this.h(50), 0.0f, 0.0f);
            }
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
            animationSet.setDuration(300L);
            animationSet.setFillAfter(true);
            animationSet.setFillEnabled(true);
            animationSet.setAnimationListener(new a());
            CTInAppBasePartialHtmlFragment.this.q.startAnimation(animationSet);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (motionEvent.getX() - motionEvent2.getX() > 120.0f && Math.abs(f) > 200.0f) {
                return a(motionEvent, motionEvent2, false);
            }
            if (motionEvent2.getX() - motionEvent.getX() <= 120.0f || Math.abs(f) <= 200.0f) {
                return false;
            }
            return a(motionEvent, motionEvent2, true);
        }
    }

    /* loaded from: classes2.dex */
    public class c extends WebViewClient {
        public c() {
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
                CTInAppBasePartialHtmlFragment.this.c(allKeyValuePairs, null);
                Logger.d("Executing call to action for in-app: " + str);
                CTInAppBasePartialHtmlFragment.this.e(str, allKeyValuePairs);
            } catch (Throwable th) {
                Logger.v("Error parsing the in-app notification action!", th);
            }
            return true;
        }
    }

    public final View l(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        try {
            View n = n(layoutInflater, viewGroup);
            ViewGroup m = m(n);
            this.q = new e(this.j, this.l.getWidth(), this.l.getHeight(), this.l.r(), this.l.j());
            this.q.setWebViewClient(new c());
            this.q.setOnTouchListener(this);
            this.q.setOnLongClickListener(this);
            if (m != null) {
                m.addView(this.q);
            }
            return n;
        } catch (Throwable th) {
            this.i.getLogger().verbose(this.i.getAccountId(), "Fragment view not created", th);
            return null;
        }
    }

    public abstract ViewGroup m(View view);

    public abstract View n(LayoutInflater layoutInflater, ViewGroup viewGroup);

    public final void o() {
        this.q.a();
        Point point = this.q.h;
        int i = point.y;
        int i2 = point.x;
        float f = getResources().getDisplayMetrics().density;
        String replaceFirst = this.l.k().replaceFirst("<head>", "<head>" + ("<style>body{width:" + ((int) (i2 / f)) + "px; height: " + ((int) (i / f)) + "px; margin: 0; padding:0;}</style>"));
        Logger.v("Density appears to be " + f);
        this.q.setInitialScale((int) (f * 100.0f));
        this.q.loadDataWithBaseURL(null, replaceFirst, "text/html", JsonRequest.PROTOCOL_CHARSET, null);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        o();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        return l(layoutInflater, viewGroup);
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        return true;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.p.onTouchEvent(motionEvent) || motionEvent.getAction() == 2;
    }

    @Override // com.clevertap.android.sdk.inapp.CTInAppBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        o();
    }
}
