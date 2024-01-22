package com.bumptech.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.R;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    @IdRes
    public static final int l = R.id.glide_custom_view_target_tag;
    public final b h;
    @Nullable
    public View.OnAttachStateChangeListener i;
    public boolean j;
    public boolean k;
    public final T view;

    /* loaded from: classes2.dex */
    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            CustomViewTarget.this.e();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            CustomViewTarget.this.d();
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class b {
        @Nullable
        @VisibleForTesting
        public static Integer e;

        /* renamed from: a  reason: collision with root package name */
        public final View f2528a;
        public final List<SizeReadyCallback> b = new ArrayList();
        public boolean c;
        @Nullable
        public a d;

        /* loaded from: classes2.dex */
        public static final class a implements ViewTreeObserver.OnPreDrawListener {
            public final WeakReference<b> h;

            public a(@NonNull b bVar) {
                this.h = new WeakReference<>(bVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable("CustomViewTarget", 2)) {
                    Log.v("CustomViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                b bVar = this.h.get();
                if (bVar != null) {
                    bVar.a();
                    return true;
                }
                return true;
            }
        }

        public b(@NonNull View view) {
            this.f2528a = view;
        }

        public static int c(@NonNull Context context) {
            if (e == null) {
                Display defaultDisplay = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                e = Integer.valueOf(Math.max(point.x, point.y));
            }
            return e.intValue();
        }

        public void a() {
            if (this.b.isEmpty()) {
                return;
            }
            int g = g();
            int f = f();
            if (i(g, f)) {
                j(g, f);
                b();
            }
        }

        public void b() {
            ViewTreeObserver viewTreeObserver = this.f2528a.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.d);
            }
            this.d = null;
            this.b.clear();
        }

        public void d(@NonNull SizeReadyCallback sizeReadyCallback) {
            int g = g();
            int f = f();
            if (i(g, f)) {
                sizeReadyCallback.onSizeReady(g, f);
                return;
            }
            if (!this.b.contains(sizeReadyCallback)) {
                this.b.add(sizeReadyCallback);
            }
            if (this.d == null) {
                ViewTreeObserver viewTreeObserver = this.f2528a.getViewTreeObserver();
                a aVar = new a(this);
                this.d = aVar;
                viewTreeObserver.addOnPreDrawListener(aVar);
            }
        }

        public final int e(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.c && this.f2528a.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.f2528a.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("CustomViewTarget", 4)) {
                Log.i("CustomViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return c(this.f2528a.getContext());
        }

        public final int f() {
            int paddingTop = this.f2528a.getPaddingTop() + this.f2528a.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f2528a.getLayoutParams();
            return e(this.f2528a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        public final int g() {
            int paddingLeft = this.f2528a.getPaddingLeft() + this.f2528a.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f2528a.getLayoutParams();
            return e(this.f2528a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        public final boolean h(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        public final boolean i(int i, int i2) {
            return h(i) && h(i2);
        }

        public final void j(int i, int i2) {
            Iterator it = new ArrayList(this.b).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(i, i2);
            }
        }

        public void k(@NonNull SizeReadyCallback sizeReadyCallback) {
            this.b.remove(sizeReadyCallback);
        }
    }

    public CustomViewTarget(@NonNull T t) {
        this.view = (T) Preconditions.checkNotNull(t);
        this.h = new b(t);
    }

    @Nullable
    public final Object a() {
        return this.view.getTag(l);
    }

    public final void b() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.i;
        if (onAttachStateChangeListener == null || this.k) {
            return;
        }
        this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.k = true;
    }

    public final void c() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.i;
        if (onAttachStateChangeListener == null || !this.k) {
            return;
        }
        this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.k = false;
    }

    @NonNull
    public final CustomViewTarget<T, Z> clearOnDetach() {
        if (this.i != null) {
            return this;
        }
        this.i = new a();
        b();
        return this;
    }

    public final void d() {
        Request request = getRequest();
        if (request != null) {
            this.j = true;
            request.clear();
            this.j = false;
        }
    }

    public final void e() {
        Request request = getRequest();
        if (request == null || !request.isCleared()) {
            return;
        }
        request.begin();
    }

    public final void f(@Nullable Object obj) {
        this.view.setTag(l, obj);
    }

    @Override // com.bumptech.glide.request.target.Target
    @Nullable
    public final Request getRequest() {
        Object a2 = a();
        if (a2 != null) {
            if (a2 instanceof Request) {
                return (Request) a2;
            }
            throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
        }
        return null;
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.h.d(sizeReadyCallback);
    }

    @NonNull
    public final T getView() {
        return this.view;
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void onLoadCleared(@Nullable Drawable drawable) {
        this.h.b();
        onResourceCleared(drawable);
        if (this.j) {
            return;
        }
        c();
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void onLoadStarted(@Nullable Drawable drawable) {
        b();
        onResourceLoading(drawable);
    }

    public abstract void onResourceCleared(@Nullable Drawable drawable);

    public void onResourceLoading(@Nullable Drawable drawable) {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.bumptech.glide.manager.LifecycleListener
    public void onStop() {
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.h.k(sizeReadyCallback);
    }

    @Override // com.bumptech.glide.request.target.Target
    public final void setRequest(@Nullable Request request) {
        f(request);
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    @Deprecated
    public final CustomViewTarget<T, Z> useTagId(@IdRes int i) {
        return this;
    }

    @NonNull
    public final CustomViewTarget<T, Z> waitForLayout() {
        this.h.c = true;
        return this;
    }
}
