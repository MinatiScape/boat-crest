package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public class c implements ViewOverlayImpl {

    /* renamed from: a  reason: collision with root package name */
    public a f10328a;

    @SuppressLint({"ViewConstructor", "PrivateApi"})
    /* loaded from: classes10.dex */
    public static class a extends ViewGroup {
        public ViewGroup h;
        public View i;
        public ArrayList<Drawable> j;
        public c k;
        public boolean l;

        static {
            try {
                Class cls = Integer.TYPE;
                ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, c cVar) {
            super(context);
            this.j = null;
            this.h = viewGroup;
            this.i = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.k = cVar;
        }

        public void a(Drawable drawable) {
            b();
            if (this.j == null) {
                this.j = new ArrayList<>();
            }
            if (this.j.contains(drawable)) {
                return;
            }
            this.j.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public final void b() {
            if (this.l) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void c() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.j;
                if (arrayList == null || arrayList.size() == 0) {
                    this.l = true;
                    this.h.removeView(this);
                }
            }
        }

        public final void d(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.h.getLocationOnScreen(iArr2);
            this.i.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            this.h.getLocationOnScreen(iArr);
            this.i.getLocationOnScreen(iArr2);
            canvas.translate(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
            canvas.clipRect(new Rect(0, 0, this.i.getWidth(), this.i.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.j;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i = 0; i < size; i++) {
                this.j.get(i).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void e(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.j;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                c();
            }
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.h != null) {
                rect.offset(iArr[0], iArr[1]);
                if (this.h != null) {
                    iArr[0] = 0;
                    iArr[1] = 0;
                    int[] iArr2 = new int[2];
                    d(iArr2);
                    rect.offset(iArr2[0], iArr2[1]);
                    return super.invalidateChildInParent(iArr, rect);
                }
                invalidate(rect);
                return null;
            }
            return null;
        }

        @Override // android.view.View, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(@NonNull Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(@NonNull Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.j) != null && arrayList.contains(drawable));
        }
    }

    public c(Context context, ViewGroup viewGroup, View view) {
        this.f10328a = new a(context, viewGroup, view, this);
    }

    public static c a(View view) {
        ViewGroup contentView = ViewUtils.getContentView(view);
        if (contentView != null) {
            int childCount = contentView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = contentView.getChildAt(i);
                if (childAt instanceof a) {
                    return ((a) childAt).k;
                }
            }
            return new b(contentView.getContext(), contentView, view);
        }
        return null;
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void add(@NonNull Drawable drawable) {
        this.f10328a.a(drawable);
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void remove(@NonNull Drawable drawable) {
        this.f10328a.e(drawable);
    }
}
