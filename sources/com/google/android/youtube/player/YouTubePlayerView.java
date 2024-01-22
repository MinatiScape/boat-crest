package com.google.android.youtube.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.core.view.ViewCompat;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.internal.aa;
import com.google.android.youtube.player.internal.ab;
import com.google.android.youtube.player.internal.n;
import com.google.android.youtube.player.internal.s;
import com.google.android.youtube.player.internal.t;
import com.google.android.youtube.player.internal.w;
import com.google.android.youtube.player.internal.y;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes10.dex */
public final class YouTubePlayerView extends ViewGroup implements YouTubePlayer.Provider {
    public final c h;
    public final Set<View> i;
    public final d j;
    public com.google.android.youtube.player.internal.b k;
    public s l;
    public View m;
    public n n;
    public YouTubePlayer.Provider o;
    public Bundle p;
    public YouTubePlayer.OnInitializedListener q;
    public boolean r;
    public boolean s;

    /* loaded from: classes10.dex */
    public class a implements t.a {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Activity f10485a;

        public a(Activity activity) {
            this.f10485a = activity;
        }

        @Override // com.google.android.youtube.player.internal.t.a
        public final void a() {
            if (YouTubePlayerView.this.k != null) {
                YouTubePlayerView.f(YouTubePlayerView.this, this.f10485a);
            }
            YouTubePlayerView.i(YouTubePlayerView.this);
        }

        @Override // com.google.android.youtube.player.internal.t.a
        public final void b() {
            if (!YouTubePlayerView.this.s && YouTubePlayerView.this.l != null) {
                YouTubePlayerView.this.l.f();
            }
            YouTubePlayerView.this.n.a();
            YouTubePlayerView youTubePlayerView = YouTubePlayerView.this;
            if (youTubePlayerView.indexOfChild(youTubePlayerView.n) < 0) {
                YouTubePlayerView youTubePlayerView2 = YouTubePlayerView.this;
                youTubePlayerView2.addView(youTubePlayerView2.n);
                YouTubePlayerView youTubePlayerView3 = YouTubePlayerView.this;
                youTubePlayerView3.removeView(youTubePlayerView3.m);
            }
            YouTubePlayerView.t(YouTubePlayerView.this);
            YouTubePlayerView.u(YouTubePlayerView.this);
            YouTubePlayerView.i(YouTubePlayerView.this);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements t.b {
        public b() {
        }

        @Override // com.google.android.youtube.player.internal.t.b
        public final void a(YouTubeInitializationResult youTubeInitializationResult) {
            YouTubePlayerView.this.e(youTubeInitializationResult);
            YouTubePlayerView.i(YouTubePlayerView.this);
        }
    }

    /* loaded from: classes10.dex */
    public final class c implements ViewTreeObserver.OnGlobalFocusChangeListener {
        public c() {
        }

        public /* synthetic */ c(YouTubePlayerView youTubePlayerView, byte b) {
            this();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public final void onGlobalFocusChanged(View view, View view2) {
            if (YouTubePlayerView.this.l == null || !YouTubePlayerView.this.i.contains(view2) || YouTubePlayerView.this.i.contains(view)) {
                return;
            }
            YouTubePlayerView.this.l.g();
        }
    }

    /* loaded from: classes10.dex */
    public interface d {
        void a(YouTubePlayerView youTubePlayerView);

        void b(YouTubePlayerView youTubePlayerView, String str, YouTubePlayer.OnInitializedListener onInitializedListener);
    }

    public YouTubePlayerView(Context context) {
        this(context, null);
    }

    public YouTubePlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public YouTubePlayerView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, ((YouTubeBaseActivity) context).b());
        if (!(context instanceof YouTubeBaseActivity)) {
            throw new IllegalStateException("A YouTubePlayerView can only be created with an Activity  which extends YouTubeBaseActivity as its context.");
        }
    }

    public YouTubePlayerView(Context context, AttributeSet attributeSet, int i, d dVar) {
        super((Context) ab.a(context, "context cannot be null"), attributeSet, i);
        this.j = (d) ab.a(dVar, "listener cannot be null");
        if (getBackground() == null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        setClipToPadding(false);
        n nVar = new n(context);
        this.n = nVar;
        requestTransparentRegion(nVar);
        addView(this.n);
        this.i = new HashSet();
        this.h = new c(this, (byte) 0);
    }

    public static /* synthetic */ void f(YouTubePlayerView youTubePlayerView, Activity activity) {
        try {
            s sVar = new s(youTubePlayerView.k, aa.a().a(activity, youTubePlayerView.k, youTubePlayerView.r));
            youTubePlayerView.l = sVar;
            View a2 = sVar.a();
            youTubePlayerView.m = a2;
            youTubePlayerView.addView(a2);
            youTubePlayerView.removeView(youTubePlayerView.n);
            youTubePlayerView.j.a(youTubePlayerView);
            if (youTubePlayerView.q != null) {
                boolean z = false;
                Bundle bundle = youTubePlayerView.p;
                if (bundle != null) {
                    z = youTubePlayerView.l.a(bundle);
                    youTubePlayerView.p = null;
                }
                youTubePlayerView.q.onInitializationSuccess(youTubePlayerView.o, youTubePlayerView.l, z);
                youTubePlayerView.q = null;
            }
        } catch (w.a e) {
            y.a("Error creating YouTubePlayerView", e);
            youTubePlayerView.e(YouTubeInitializationResult.INTERNAL_ERROR);
        }
    }

    public static /* synthetic */ com.google.android.youtube.player.internal.b i(YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.k = null;
        return null;
    }

    public static /* synthetic */ View t(YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.m = null;
        return null;
    }

    public static /* synthetic */ s u(YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.l = null;
        return null;
    }

    @Override // android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int i) {
        ArrayList<View> arrayList2 = new ArrayList<>();
        super.addFocusables(arrayList2, i);
        arrayList.addAll(arrayList2);
        this.i.clear();
        this.i.addAll(arrayList2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        ArrayList<View> arrayList2 = new ArrayList<>();
        super.addFocusables(arrayList2, i, i2);
        arrayList.addAll(arrayList2);
        this.i.clear();
        this.i.addAll(arrayList2);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        d(view);
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        d(view);
        super.addView(view, i);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, int i2) {
        d(view);
        super.addView(view, i, i2);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        d(view);
        super.addView(view, i, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        d(view);
        super.addView(view, layoutParams);
    }

    public final void b() {
        s sVar = this.l;
        if (sVar != null) {
            sVar.b();
        }
    }

    public final void c(Activity activity, YouTubePlayer.Provider provider, String str, YouTubePlayer.OnInitializedListener onInitializedListener, Bundle bundle) {
        if (this.l == null && this.q == null) {
            ab.a(activity, "activity cannot be null");
            this.o = (YouTubePlayer.Provider) ab.a(provider, "provider cannot be null");
            this.q = (YouTubePlayer.OnInitializedListener) ab.a(onInitializedListener, "listener cannot be null");
            this.p = bundle;
            this.n.b();
            com.google.android.youtube.player.internal.b a2 = aa.a().a(getContext(), str, new a(activity), new b());
            this.k = a2;
            a2.e();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void clearChildFocus(View view) {
        if (hasFocusable()) {
            requestFocus();
        } else {
            super.clearChildFocus(view);
        }
    }

    public final void d(View view) {
        if (!(view == this.n || (this.l != null && view == this.m))) {
            throw new UnsupportedOperationException("No views can be added on top of the player");
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.l != null) {
            if (keyEvent.getAction() == 0) {
                return this.l.a(keyEvent.getKeyCode(), keyEvent) || super.dispatchKeyEvent(keyEvent);
            } else if (keyEvent.getAction() == 1) {
                return this.l.b(keyEvent.getKeyCode(), keyEvent) || super.dispatchKeyEvent(keyEvent);
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void e(YouTubeInitializationResult youTubeInitializationResult) {
        this.l = null;
        this.n.c();
        YouTubePlayer.OnInitializedListener onInitializedListener = this.q;
        if (onInitializedListener != null) {
            onInitializedListener.onInitializationFailure(this.o, youTubeInitializationResult);
            this.q = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void focusableViewAvailable(View view) {
        super.focusableViewAvailable(view);
        this.i.add(view);
    }

    public final void h(boolean z) {
        if (!z || Build.VERSION.SDK_INT >= 14) {
            this.r = z;
            return;
        }
        y.a("Could not enable TextureView because API level is lower than 14", new Object[0]);
        this.r = false;
    }

    @Override // com.google.android.youtube.player.YouTubePlayer.Provider
    public final void initialize(String str, YouTubePlayer.OnInitializedListener onInitializedListener) {
        ab.a(str, (Object) "Developer key cannot be null or empty");
        this.j.b(this, str, onInitializedListener);
    }

    public final void j() {
        s sVar = this.l;
        if (sVar != null) {
            sVar.c();
        }
    }

    public final void k(boolean z) {
        s sVar = this.l;
        if (sVar != null) {
            sVar.b(z);
            m(z);
        }
    }

    public final void l() {
        s sVar = this.l;
        if (sVar != null) {
            sVar.d();
        }
    }

    public final void m(boolean z) {
        this.s = true;
        s sVar = this.l;
        if (sVar != null) {
            sVar.a(z);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.h);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        s sVar = this.l;
        if (sVar != null) {
            sVar.a(configuration);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.h);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            getChildAt(0).layout(0, 0, i3 - i, i4 - i2);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (getChildCount() <= 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        View childAt = getChildAt(0);
        childAt.measure(i, i2);
        setMeasuredDimension(childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public final void p() {
        s sVar = this.l;
        if (sVar != null) {
            sVar.e();
        }
    }

    public final Bundle q() {
        s sVar = this.l;
        return sVar == null ? this.p : sVar.h();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        this.i.add(view2);
    }

    @Override // android.view.ViewGroup
    public final void setClipToPadding(boolean z) {
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
    }
}
