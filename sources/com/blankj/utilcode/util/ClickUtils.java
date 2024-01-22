package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.blankj.utilcode.util.ShadowUtils;
import com.clevertap.android.sdk.Constants;
import java.util.Objects;
/* loaded from: classes.dex */
public class ClickUtils {

    /* renamed from: a  reason: collision with root package name */
    public static long f2245a;
    public static int b;

    /* loaded from: classes.dex */
    public interface Back2HomeFriendlyListener {
        public static final Back2HomeFriendlyListener DEFAULT = new a();

        /* loaded from: classes.dex */
        public static class a implements Back2HomeFriendlyListener {
            @Override // com.blankj.utilcode.util.ClickUtils.Back2HomeFriendlyListener
            public void dismiss() {
                com.blankj.utilcode.util.b.b1();
            }

            @Override // com.blankj.utilcode.util.ClickUtils.Back2HomeFriendlyListener
            public void show(CharSequence charSequence, long j) {
                com.blankj.utilcode.util.b.c1(charSequence);
            }
        }

        void dismiss();

        void show(CharSequence charSequence, long j);
    }

    /* loaded from: classes.dex */
    public static abstract class OnDebouncingClickListener implements View.OnClickListener {
        public static boolean j = true;
        public static final Runnable k = new a();
        public long h;
        public boolean i;

        /* loaded from: classes.dex */
        public static class a implements Runnable {
            @Override // java.lang.Runnable
            public void run() {
                boolean unused = OnDebouncingClickListener.j = true;
            }
        }

        public OnDebouncingClickListener() {
            this(true, 1000L);
        }

        public static boolean b(@NonNull View view, long j2) {
            Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            return com.blankj.utilcode.util.b.D0(view, j2);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.i) {
                if (j) {
                    j = false;
                    view.postDelayed(k, this.h);
                    onDebouncingClick(view);
                }
            } else if (b(view, this.h)) {
                onDebouncingClick(view);
            }
        }

        public abstract void onDebouncingClick(View view);

        public OnDebouncingClickListener(boolean z) {
            this(z, 1000L);
        }

        public OnDebouncingClickListener(long j2) {
            this(true, j2);
        }

        public OnDebouncingClickListener(boolean z, long j2) {
            this.i = z;
            this.h = j2;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnMultiClickListener implements View.OnClickListener {
        public final int h;
        public final long i;
        public long j;
        public int k;

        public OnMultiClickListener(int i) {
            this(i, 666L);
        }

        public abstract void onBeforeTriggerClick(View view, int i);

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.h <= 1) {
                onTriggerClick(view);
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.j < this.i) {
                int i = this.k + 1;
                this.k = i;
                int i2 = this.h;
                if (i == i2) {
                    onTriggerClick(view);
                } else if (i < i2) {
                    onBeforeTriggerClick(view, i);
                } else {
                    this.k = 1;
                    onBeforeTriggerClick(view, 1);
                }
            } else {
                this.k = 1;
                onBeforeTriggerClick(view, 1);
            }
            this.j = currentTimeMillis;
        }

        public abstract void onTriggerClick(View view);

        public OnMultiClickListener(int i, long j) {
            this.h = i;
            this.i = j;
        }
    }

    /* loaded from: classes.dex */
    public static class a extends OnDebouncingClickListener {
        public final /* synthetic */ View.OnClickListener l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(boolean z, long j, View.OnClickListener onClickListener) {
            super(z, j);
            this.l = onClickListener;
        }

        @Override // com.blankj.utilcode.util.ClickUtils.OnDebouncingClickListener
        public void onDebouncingClick(View view) {
            this.l.onClick(view);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final /* synthetic */ View h;
        public final /* synthetic */ int i;
        public final /* synthetic */ int j;
        public final /* synthetic */ int k;
        public final /* synthetic */ int l;
        public final /* synthetic */ View m;

        public b(View view, int i, int i2, int i3, int i4, View view2) {
            this.h = view;
            this.i = i;
            this.j = i2;
            this.k = i3;
            this.l = i4;
            this.m = view2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Rect rect = new Rect();
            this.h.getHitRect(rect);
            rect.top -= this.i;
            rect.bottom += this.j;
            rect.left -= this.k;
            rect.right += this.l;
            this.m.setTouchDelegate(new TouchDelegate(rect, this.h));
        }
    }

    /* loaded from: classes.dex */
    public static class c extends ShadowUtils.a {
        public BitmapDrawable i;
        public Paint j;

        public c(Drawable drawable) {
            super(drawable);
            this.i = null;
            this.j = null;
            if (drawable instanceof ColorDrawable) {
                Paint paint = new Paint(5);
                this.j = paint;
                paint.setColor(((ColorDrawable) drawable).getColor());
            }
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.i == null) {
                Bitmap createBitmap = Bitmap.createBitmap(getBounds().width(), getBounds().height(), Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(createBitmap);
                if (this.j != null) {
                    canvas2.drawRect(getBounds(), this.j);
                } else {
                    super.draw(canvas2);
                }
                BitmapDrawable bitmapDrawable = new BitmapDrawable(Resources.getSystem(), createBitmap);
                this.i = bitmapDrawable;
                bitmapDrawable.setBounds(getBounds());
            }
            this.i.draw(canvas);
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            Paint paint;
            super.setAlpha(i);
            if (Build.VERSION.SDK_INT >= 21 || (paint = this.j) == null) {
                return;
            }
            paint.setColor(((ColorDrawable) getWrappedDrawable()).getColor());
        }

        @Override // com.blankj.utilcode.util.ShadowUtils.a, android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            Paint paint;
            super.setColorFilter(colorFilter);
            if (Build.VERSION.SDK_INT >= 21 || (paint = this.j) == null) {
                return;
            }
            paint.setColorFilter(colorFilter);
        }
    }

    /* loaded from: classes.dex */
    public static class d implements View.OnTouchListener {

        /* loaded from: classes.dex */
        public static class a {

            /* renamed from: a  reason: collision with root package name */
            public static final d f2246a = new d(null);
        }

        public /* synthetic */ d(a aVar) {
            this();
        }

        public static d a() {
            return a.f2246a;
        }

        public final void b(View view, boolean z) {
            Object tag = view.getTag(z ? -2 : -3);
            if (tag instanceof Float) {
                view.setAlpha(((Float) tag).floatValue());
            }
        }

        public final void c(View view, boolean z) {
            Object tag = view.getTag(-1);
            if (tag instanceof Float) {
                float floatValue = z ? 1.0f + ((Float) tag).floatValue() : 1.0f;
                view.animate().scaleX(floatValue).scaleY(floatValue).setDuration(200L).start();
            }
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                c(view, true);
                b(view, true);
            } else if (action == 1 || action == 3) {
                c(view, false);
                b(view, false);
            }
            return false;
        }

        public d() {
        }
    }

    public ClickUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(View[] viewArr, boolean z, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        if (viewArr == null || viewArr.length == 0 || onClickListener == null) {
            return;
        }
        for (View view : viewArr) {
            if (view != null) {
                view.setOnClickListener(new a(z, j, onClickListener));
            }
        }
    }

    public static void applyGlobalDebouncing(View view, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, onClickListener);
    }

    public static void applyPressedBgAlpha(View view) {
        applyPressedBgAlpha(view, 0.9f);
    }

    public static void applyPressedBgDark(View view) {
        applyPressedBgDark(view, 0.9f);
    }

    public static void applyPressedViewAlpha(View... viewArr) {
        applyPressedViewAlpha(viewArr, (float[]) null);
    }

    public static void applyPressedViewScale(View... viewArr) {
        applyPressedViewScale(viewArr, (float[]) null);
    }

    public static void applySingleDebouncing(View view, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, onClickListener);
    }

    public static void b(View view, int i, float f) {
        if (view == null) {
            return;
        }
        Drawable background = view.getBackground();
        int i2 = -i;
        Object tag = view.getTag(i2);
        if (tag instanceof Drawable) {
            ViewCompat.setBackground(view, (Drawable) tag);
            return;
        }
        Drawable e = e(background, i, f);
        ViewCompat.setBackground(view, e);
        view.setTag(i2, e);
    }

    public static void back2HomeFriendly(CharSequence charSequence) {
        back2HomeFriendly(charSequence, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS, Back2HomeFriendlyListener.DEFAULT);
    }

    public static Drawable c(Drawable drawable, float f) {
        c cVar = new c(drawable);
        cVar.setAlpha((int) (f * 255.0f));
        return cVar;
    }

    public static Drawable d(Drawable drawable, float f) {
        c cVar = new c(drawable);
        cVar.setColorFilter(f(f));
        return cVar;
    }

    public static Drawable e(Drawable drawable, int i, float f) {
        if (drawable == null) {
            drawable = new ColorDrawable(0);
        }
        if (drawable.getConstantState() == null) {
            return drawable;
        }
        Drawable mutate = drawable.getConstantState().newDrawable().mutate();
        if (i == 4) {
            mutate = c(mutate, f);
        } else if (i == 5) {
            mutate = d(mutate, f);
        }
        Drawable c2 = c(drawable.getConstantState().newDrawable().mutate(), 0.5f);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, mutate);
        stateListDrawable.addState(new int[]{-16842910}, c2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    public static void expandClickArea(@NonNull View view, int i) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        expandClickArea(view, i, i, i, i);
    }

    public static ColorMatrixColorFilter f(float f) {
        return new ColorMatrixColorFilter(new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2.0f, 0.0f}));
    }

    public static void applyGlobalDebouncing(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void applyPressedBgAlpha(View view, float f) {
        b(view, 4, f);
    }

    public static void applyPressedBgDark(View view, float f) {
        b(view, 5, f);
    }

    public static void applyPressedViewAlpha(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr != null && i < fArr.length) {
                applyPressedViewAlpha(viewArr[i], fArr[i]);
            } else {
                applyPressedViewAlpha(viewArr[i], 0.8f);
            }
        }
    }

    public static void applyPressedViewScale(View[] viewArr, float[] fArr) {
        if (viewArr == null || viewArr.length == 0) {
            return;
        }
        for (int i = 0; i < viewArr.length; i++) {
            if (fArr != null && i < fArr.length) {
                applyPressedViewScale(viewArr[i], fArr[i]);
            } else {
                applyPressedViewScale(viewArr[i], -0.06f);
            }
        }
    }

    public static void applySingleDebouncing(View view, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        applySingleDebouncing(new View[]{view}, j, onClickListener);
    }

    public static void back2HomeFriendly(@NonNull CharSequence charSequence, long j, @NonNull Back2HomeFriendlyListener back2HomeFriendlyListener) {
        Objects.requireNonNull(charSequence, "Argument 'tip' of type CharSequence (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(back2HomeFriendlyListener, "Argument 'listener' of type Back2HomeFriendlyListener (#2 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - f2245a) < j) {
            int i = b + 1;
            b = i;
            if (i == 2) {
                com.blankj.utilcode.util.b.Y0();
                back2HomeFriendlyListener.dismiss();
                f2245a = 0L;
                return;
            }
            return;
        }
        b = 1;
        back2HomeFriendlyListener.show(charSequence, j);
        f2245a = elapsedRealtime;
    }

    public static void applyGlobalDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applyGlobalDebouncing(viewArr, 1000L, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, View.OnClickListener onClickListener) {
        applySingleDebouncing(viewArr, 1000L, onClickListener);
    }

    public static void expandClickArea(@NonNull View view, int i, int i2, int i3, int i4) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 5, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View view2 = (View) view.getParent();
        if (view2 == null) {
            Log.e("ClickUtils", "expandClickArea must have parent view.");
        } else {
            view2.post(new b(view, i, i4, i2, i3, view2));
        }
    }

    public static void applyGlobalDebouncing(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        a(viewArr, true, j, onClickListener);
    }

    public static void applySingleDebouncing(View[] viewArr, @IntRange(from = 0) long j, View.OnClickListener onClickListener) {
        a(viewArr, false, j, onClickListener);
    }

    public static void applyPressedViewAlpha(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-2, Float.valueOf(f));
        view.setTag(-3, Float.valueOf(view.getAlpha()));
        view.setClickable(true);
        view.setOnTouchListener(d.a());
    }

    public static void applyPressedViewScale(View view, float f) {
        if (view == null) {
            return;
        }
        view.setTag(-1, Float.valueOf(f));
        view.setClickable(true);
        view.setOnTouchListener(d.a());
    }
}
