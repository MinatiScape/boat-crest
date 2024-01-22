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
import androidx.annotation.CallSuper;
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
@Deprecated
/* loaded from: classes2.dex */
public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    public static boolean m;
    public static int n = R.id.glide_custom_view_target_tag;
    public final b i;
    @Nullable
    public View.OnAttachStateChangeListener j;
    public boolean k;
    public boolean l;
    public final T view;

    /* loaded from: classes2.dex */
    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            ViewTarget.this.e();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTarget.this.d();
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class b {
        @Nullable
        @VisibleForTesting
        public static Integer e;

        /* renamed from: a  reason: collision with root package name */
        public final View f2531a;
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
                if (Log.isLoggable("ViewTarget", 2)) {
                    Log.v("ViewTarget", "OnGlobalLayoutListener called attachStateListener=" + this);
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
            this.f2531a = view;
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
            ViewTreeObserver viewTreeObserver = this.f2531a.getViewTreeObserver();
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
                ViewTreeObserver viewTreeObserver = this.f2531a.getViewTreeObserver();
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
            if (this.c && this.f2531a.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.f2531a.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable("ViewTarget", 4)) {
                Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return c(this.f2531a.getContext());
        }

        public final int f() {
            int paddingTop = this.f2531a.getPaddingTop() + this.f2531a.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.f2531a.getLayoutParams();
            return e(this.f2531a.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        public final int g() {
            int paddingLeft = this.f2531a.getPaddingLeft() + this.f2531a.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.f2531a.getLayoutParams();
            return e(this.f2531a.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
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

    public ViewTarget(@NonNull T t) {
        this.view = (T) Preconditions.checkNotNull(t);
        this.i = new b(t);
    }

    @Deprecated
    public static void setTagId(int i) {
        if (!m) {
            n = i;
            return;
        }
        throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
    }

    @Nullable
    public final Object a() {
        return this.view.getTag(n);
    }

    public final void b() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.j;
        if (onAttachStateChangeListener == null || this.l) {
            return;
        }
        this.view.addOnAttachStateChangeListener(onAttachStateChangeListener);
        this.l = true;
    }

    public final void c() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.j;
        if (onAttachStateChangeListener == null || !this.l) {
            return;
        }
        this.view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        this.l = false;
    }

    @NonNull
    public final ViewTarget<T, Z> clearOnDetach() {
        if (this.j != null) {
            return this;
        }
        this.j = new a();
        b();
        return this;
    }

    public void d() {
        Request request = getRequest();
        if (request != null) {
            this.k = true;
            request.clear();
            this.k = false;
        }
    }

    public void e() {
        Request request = getRequest();
        if (request == null || !request.isCleared()) {
            return;
        }
        request.begin();
    }

    public final void f(@Nullable Object obj) {
        m = true;
        this.view.setTag(n, obj);
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    @Nullable
    public Request getRequest() {
        Object a2 = a();
        if (a2 != null) {
            if (a2 instanceof Request) {
                return (Request) a2;
            }
            throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
        }
        return null;
    }

    @Override // com.bumptech.glide.request.target.Target
    @CallSuper
    public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.i.d(sizeReadyCallback);
    }

    @NonNull
    public T getView() {
        return this.view;
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    @CallSuper
    public void onLoadCleared(@Nullable Drawable drawable) {
        super.onLoadCleared(drawable);
        this.i.b();
        if (this.k) {
            return;
        }
        c();
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    @CallSuper
    public void onLoadStarted(@Nullable Drawable drawable) {
        super.onLoadStarted(drawable);
        b();
    }

    @Override // com.bumptech.glide.request.target.Target
    @CallSuper
    public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
        this.i.k(sizeReadyCallback);
    }

    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
    public void setRequest(@Nullable Request request) {
        f(request);
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    @NonNull
    public final ViewTarget<T, Z> waitForLayout() {
        this.i.c = true;
        return this;
    }

    @Deprecated
    public ViewTarget(@NonNull T t, boolean z) {
        this(t);
        if (z) {
            waitForLayout();
        }
    }
}
