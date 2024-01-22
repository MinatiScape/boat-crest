package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.abupdate.iot_libs.constant.Error;
import com.blankj.utilcode.R;
import com.blankj.utilcode.util.Utils;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libble.v2.impl.BleGattX;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Objects;
/* loaded from: classes.dex */
public final class ToastUtils {
    public static final ToastUtils l = make();
    public static WeakReference<e> m;

    /* renamed from: a  reason: collision with root package name */
    public String f2294a;
    public int b = -1;
    public int c = -1;
    public int d = -1;
    public int e = -16777217;
    public int f = -1;
    public int g = -16777217;
    public int h = -1;
    public boolean i = false;
    public Drawable[] j = new Drawable[4];
    public boolean k = false;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MODE {
        public static final String DARK = "dark";
        public static final String LIGHT = "light";
    }

    /* loaded from: classes.dex */
    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {
        public static final int h = com.blankj.utilcode.util.b.w(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(com.blankj.utilcode.util.b.K() - h, Integer.MIN_VALUE), i2);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }
    }

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            if (ToastUtils.m != null) {
                e eVar = (e) ToastUtils.m.get();
                if (eVar != null) {
                    eVar.cancel();
                }
                WeakReference unused = ToastUtils.m = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final /* synthetic */ View i;
        public final /* synthetic */ CharSequence j;
        public final /* synthetic */ int k;

        public b(View view, CharSequence charSequence, int i) {
            this.i = view;
            this.j = charSequence;
            this.k = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ToastUtils.cancel();
            e n = ToastUtils.n(ToastUtils.this);
            WeakReference unused = ToastUtils.m = new WeakReference(n);
            View view = this.i;
            if (view != null) {
                n.a(view);
            } else {
                n.c(this.j);
            }
            n.b(this.k);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class c implements e {

        /* renamed from: a  reason: collision with root package name */
        public Toast f2295a = new Toast(Utils.getApp());
        public ToastUtils b;
        public View c;

        public c(ToastUtils toastUtils) {
            this.b = toastUtils;
            if (toastUtils.b == -1 && this.b.c == -1 && this.b.d == -1) {
                return;
            }
            this.f2295a.setGravity(this.b.b, this.b.c, this.b.d);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void a(View view) {
            this.c = view;
            this.f2295a.setView(view);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void c(CharSequence charSequence) {
            View r = this.b.r(charSequence);
            if (r != null) {
                a(r);
                e();
                return;
            }
            View view = this.f2295a.getView();
            this.c = view;
            if (view == null || view.findViewById(16908299) == null) {
                a(com.blankj.utilcode.util.b.G0(R.layout.utils_toast_view));
            }
            TextView textView = (TextView) this.c.findViewById(16908299);
            textView.setText(charSequence);
            if (this.b.g != -16777217) {
                textView.setTextColor(this.b.g);
            }
            if (this.b.h != -1) {
                textView.setTextSize(this.b.h);
            }
            f(textView);
            e();
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        @CallSuper
        public void cancel() {
            Toast toast = this.f2295a;
            if (toast != null) {
                toast.cancel();
            }
            this.f2295a = null;
            this.c = null;
        }

        public View d(int i) {
            Bitmap f1 = com.blankj.utilcode.util.b.f1(this.c);
            ImageView imageView = new ImageView(Utils.getApp());
            imageView.setTag("TAG_TOAST" + i);
            imageView.setImageBitmap(f1);
            return imageView;
        }

        public final void e() {
            if (com.blankj.utilcode.util.b.x0()) {
                a(d(-1));
            }
        }

        public final void f(TextView textView) {
            if (this.b.f != -1) {
                this.c.setBackgroundResource(this.b.f);
                textView.setBackgroundColor(0);
            } else if (this.b.e != -16777217) {
                Drawable background = this.c.getBackground();
                Drawable background2 = textView.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.b.e, PorterDuff.Mode.SRC_IN));
                    textView.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.b.e, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.b.e, PorterDuff.Mode.SRC_IN));
                } else {
                    this.c.setBackgroundColor(this.b.e);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends c {
        public static int f;
        public Utils.ActivityLifecycleCallbacks d;
        public e e;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.this.cancel();
            }
        }

        /* loaded from: classes.dex */
        public class b extends Utils.ActivityLifecycleCallbacks {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f2296a;

            public b(int i) {
                this.f2296a = i;
            }

            @Override // com.blankj.utilcode.util.Utils.ActivityLifecycleCallbacks
            public void onActivityCreated(@NonNull Activity activity) {
                Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                if (d.this.i()) {
                    d.this.l(activity, this.f2296a, false);
                }
            }
        }

        public d(ToastUtils toastUtils) {
            super(toastUtils);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void b(int i) {
            if (this.f2295a == null) {
                return;
            }
            if (!com.blankj.utilcode.util.b.q0()) {
                this.e = k(i);
                return;
            }
            boolean z = false;
            for (Activity activity : com.blankj.utilcode.util.b.J()) {
                if (com.blankj.utilcode.util.b.o0(activity)) {
                    if (!z) {
                        this.e = m(activity, i);
                        z = true;
                    } else {
                        l(activity, f, true);
                    }
                }
            }
            if (z) {
                j();
                com.blankj.utilcode.util.b.V0(new a(), i == 0 ? Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS : 3500L);
                f++;
                return;
            }
            this.e = k(i);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public void cancel() {
            Window window;
            if (i()) {
                n();
                for (Activity activity : com.blankj.utilcode.util.b.J()) {
                    if (com.blankj.utilcode.util.b.o0(activity) && (window = activity.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sb = new StringBuilder();
                        sb.append("TAG_TOAST");
                        sb.append(f - 1);
                        View findViewWithTag = viewGroup.findViewWithTag(sb.toString());
                        if (findViewWithTag != null) {
                            try {
                                viewGroup.removeView(findViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            e eVar = this.e;
            if (eVar != null) {
                eVar.cancel();
                this.e = null;
            }
            super.cancel();
        }

        public final boolean i() {
            return this.d != null;
        }

        public final void j() {
            b bVar = new b(f);
            this.d = bVar;
            com.blankj.utilcode.util.b.b(bVar);
        }

        public final e k(int i) {
            f fVar = new f(this.b);
            fVar.f2295a = this.f2295a;
            fVar.b(i);
            return fVar;
        }

        public final void l(Activity activity, int i, boolean z) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.f2295a.getGravity();
                layoutParams.bottomMargin = this.f2295a.getYOffset() + com.blankj.utilcode.util.b.a0();
                layoutParams.topMargin = this.f2295a.getYOffset() + com.blankj.utilcode.util.b.e0();
                layoutParams.leftMargin = this.f2295a.getXOffset();
                View d = d(i);
                if (z) {
                    d.setAlpha(0.0f);
                    d.animate().alpha(1.0f).setDuration(200L).start();
                }
                viewGroup.addView(d, layoutParams);
            }
        }

        public final e m(Activity activity, int i) {
            g gVar = new g(this.b, activity.getWindowManager(), 99);
            gVar.c = d(-1);
            gVar.f2295a = this.f2295a;
            gVar.b(i);
            return gVar;
        }

        public final void n() {
            com.blankj.utilcode.util.b.S0(this.d);
            this.d = null;
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        void a(View view);

        void b(int i);

        void c(CharSequence charSequence);

        void cancel();
    }

    /* loaded from: classes.dex */
    public static final class f extends c {

        /* loaded from: classes.dex */
        public static class a extends Handler {

            /* renamed from: a  reason: collision with root package name */
            public Handler f2297a;

            public a(Handler handler) {
                this.f2297a = handler;
            }

            @Override // android.os.Handler
            public void dispatchMessage(@NonNull Message message) {
                Objects.requireNonNull(message, "Argument 'msg' of type Message (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                try {
                    this.f2297a.dispatchMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // android.os.Handler
            public void handleMessage(@NonNull Message message) {
                Objects.requireNonNull(message, "Argument 'msg' of type Message (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
                this.f2297a.handleMessage(message);
            }
        }

        public f(ToastUtils toastUtils) {
            super(toastUtils);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(this.f2295a);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new a((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void b(int i) {
            Toast toast = this.f2295a;
            if (toast == null) {
                return;
            }
            toast.setDuration(i);
            this.f2295a.show();
        }
    }

    public static void cancel() {
        com.blankj.utilcode.util.b.U0(new a());
    }

    @NonNull
    public static ToastUtils getDefaultMaker() {
        ToastUtils toastUtils = l;
        Objects.requireNonNull(toastUtils, "Detected an attempt to return null from a method com.blankj.utilcode.util.ToastUtils.getDefaultMaker() marked by @androidx.annotation.NonNull");
        return toastUtils;
    }

    public static CharSequence m(CharSequence charSequence) {
        return charSequence == null ? "toast null" : charSequence.length() == 0 ? "toast nothing" : charSequence;
    }

    @NonNull
    public static ToastUtils make() {
        return new ToastUtils();
    }

    public static e n(ToastUtils toastUtils) {
        if (!toastUtils.k && NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled()) {
            if (Build.VERSION.SDK_INT < 23) {
                return new f(toastUtils);
            }
            if (!com.blankj.utilcode.util.b.v0()) {
                return new f(toastUtils);
            }
        }
        int i = Build.VERSION.SDK_INT;
        if (i < 25) {
            return new g(toastUtils, BleGattX.EVT_SERVICE_DISCOVERED);
        }
        if (com.blankj.utilcode.util.b.v0()) {
            if (i >= 26) {
                return new g(toastUtils, 2038);
            }
            return new g(toastUtils, Error.RESPONSE_ERROR);
        }
        return new d(toastUtils);
    }

    public static void o(@NonNull View view, int i, ToastUtils toastUtils) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        p(view, null, i, toastUtils);
    }

    public static void p(@Nullable View view, @Nullable CharSequence charSequence, int i, @NonNull ToastUtils toastUtils) {
        Objects.requireNonNull(toastUtils, "Argument 'utils' of type ToastUtils (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        com.blankj.utilcode.util.b.U0(new b(view, charSequence, i));
    }

    public static void q(@Nullable CharSequence charSequence, int i, ToastUtils toastUtils) {
        p(null, m(charSequence), i, toastUtils);
    }

    public static void showLong(@Nullable CharSequence charSequence) {
        q(charSequence, 1, l);
    }

    public static void showShort(@Nullable CharSequence charSequence) {
        q(charSequence, 0, l);
    }

    public final int l() {
        return this.i ? 1 : 0;
    }

    public final View r(CharSequence charSequence) {
        if (!"dark".equals(this.f2294a) && !"light".equals(this.f2294a)) {
            Drawable[] drawableArr = this.j;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View G0 = com.blankj.utilcode.util.b.G0(R.layout.utils_toast_view);
        TextView textView = (TextView) G0.findViewById(16908299);
        if ("dark".equals(this.f2294a)) {
            ((GradientDrawable) G0.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(charSequence);
        if (this.j[0] != null) {
            View findViewById = G0.findViewById(R.id.utvLeftIconView);
            ViewCompat.setBackground(findViewById, this.j[0]);
            findViewById.setVisibility(0);
        }
        if (this.j[1] != null) {
            View findViewById2 = G0.findViewById(R.id.utvTopIconView);
            ViewCompat.setBackground(findViewById2, this.j[1]);
            findViewById2.setVisibility(0);
        }
        if (this.j[2] != null) {
            View findViewById3 = G0.findViewById(R.id.utvRightIconView);
            ViewCompat.setBackground(findViewById3, this.j[2]);
            findViewById3.setVisibility(0);
        }
        if (this.j[3] != null) {
            View findViewById4 = G0.findViewById(R.id.utvBottomIconView);
            ViewCompat.setBackground(findViewById4, this.j[3]);
            findViewById4.setVisibility(0);
        }
        return G0;
    }

    @NonNull
    public final ToastUtils setBgColor(@ColorInt int i) {
        this.e = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBgResource(@DrawableRes int i) {
        this.f = i;
        return this;
    }

    @NonNull
    public final ToastUtils setBottomIcon(int i) {
        ToastUtils bottomIcon = setBottomIcon(ContextCompat.getDrawable(Utils.getApp(), i));
        Objects.requireNonNull(bottomIcon, "Detected an attempt to return null from a method com.blankj.utilcode.util.ToastUtils.setBottomIcon() marked by @androidx.annotation.NonNull");
        return bottomIcon;
    }

    @NonNull
    public final ToastUtils setDurationIsLong(boolean z) {
        this.i = z;
        return this;
    }

    @NonNull
    public final ToastUtils setGravity(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        return this;
    }

    @NonNull
    public final ToastUtils setLeftIcon(@DrawableRes int i) {
        ToastUtils leftIcon = setLeftIcon(ContextCompat.getDrawable(Utils.getApp(), i));
        Objects.requireNonNull(leftIcon, "Detected an attempt to return null from a method com.blankj.utilcode.util.ToastUtils.setLeftIcon() marked by @androidx.annotation.NonNull");
        return leftIcon;
    }

    @NonNull
    public final ToastUtils setMode(String str) {
        this.f2294a = str;
        return this;
    }

    @NonNull
    public final ToastUtils setNotUseSystemToast() {
        this.k = true;
        return this;
    }

    @NonNull
    public final ToastUtils setRightIcon(@DrawableRes int i) {
        ToastUtils rightIcon = setRightIcon(ContextCompat.getDrawable(Utils.getApp(), i));
        Objects.requireNonNull(rightIcon, "Detected an attempt to return null from a method com.blankj.utilcode.util.ToastUtils.setRightIcon() marked by @androidx.annotation.NonNull");
        return rightIcon;
    }

    @NonNull
    public final ToastUtils setTextColor(@ColorInt int i) {
        this.g = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTextSize(int i) {
        this.h = i;
        return this;
    }

    @NonNull
    public final ToastUtils setTopIcon(@DrawableRes int i) {
        ToastUtils topIcon = setTopIcon(ContextCompat.getDrawable(Utils.getApp(), i));
        Objects.requireNonNull(topIcon, "Detected an attempt to return null from a method com.blankj.utilcode.util.ToastUtils.setTopIcon() marked by @androidx.annotation.NonNull");
        return topIcon;
    }

    public final void show(@Nullable CharSequence charSequence) {
        q(charSequence, l(), this);
    }

    public static void showLong(@StringRes int i) {
        q(com.blankj.utilcode.util.b.f0(i), 1, l);
    }

    public static void showShort(@StringRes int i) {
        q(com.blankj.utilcode.util.b.f0(i), 0, l);
    }

    @NonNull
    public final ToastUtils setBottomIcon(@Nullable Drawable drawable) {
        this.j[3] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setLeftIcon(@Nullable Drawable drawable) {
        this.j[0] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setRightIcon(@Nullable Drawable drawable) {
        this.j[2] = drawable;
        return this;
    }

    @NonNull
    public final ToastUtils setTopIcon(@Nullable Drawable drawable) {
        this.j[1] = drawable;
        return this;
    }

    public final void show(@StringRes int i) {
        q(com.blankj.utilcode.util.b.f0(i), l(), this);
    }

    public static void showLong(@StringRes int i, Object... objArr) {
        q(com.blankj.utilcode.util.b.g0(i, objArr), 1, l);
    }

    public static void showShort(@StringRes int i, Object... objArr) {
        q(com.blankj.utilcode.util.b.g0(i, objArr), 0, l);
    }

    public final void show(@StringRes int i, Object... objArr) {
        q(com.blankj.utilcode.util.b.g0(i, objArr), l(), this);
    }

    /* loaded from: classes.dex */
    public static final class g extends c {
        public WindowManager d;
        public WindowManager.LayoutParams e;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                g.this.cancel();
            }
        }

        public g(ToastUtils toastUtils, int i) {
            super(toastUtils);
            this.e = new WindowManager.LayoutParams();
            this.d = (WindowManager) Utils.getApp().getSystemService("window");
            this.e.type = i;
        }

        @Override // com.blankj.utilcode.util.ToastUtils.e
        public void b(int i) {
            if (this.f2295a == null) {
                return;
            }
            WindowManager.LayoutParams layoutParams = this.e;
            layoutParams.height = -2;
            layoutParams.width = -2;
            layoutParams.format = -3;
            layoutParams.windowAnimations = 16973828;
            layoutParams.setTitle("ToastWithoutNotification");
            WindowManager.LayoutParams layoutParams2 = this.e;
            layoutParams2.flags = 152;
            layoutParams2.packageName = Utils.getApp().getPackageName();
            this.e.gravity = this.f2295a.getGravity();
            WindowManager.LayoutParams layoutParams3 = this.e;
            int i2 = layoutParams3.gravity;
            if ((i2 & 7) == 7) {
                layoutParams3.horizontalWeight = 1.0f;
            }
            if ((i2 & 112) == 112) {
                layoutParams3.verticalWeight = 1.0f;
            }
            layoutParams3.x = this.f2295a.getXOffset();
            this.e.y = this.f2295a.getYOffset();
            this.e.horizontalMargin = this.f2295a.getHorizontalMargin();
            this.e.verticalMargin = this.f2295a.getVerticalMargin();
            try {
                WindowManager windowManager = this.d;
                if (windowManager != null) {
                    windowManager.addView(this.c, this.e);
                }
            } catch (Exception unused) {
            }
            com.blankj.utilcode.util.b.V0(new a(), i == 0 ? Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS : 3500L);
        }

        @Override // com.blankj.utilcode.util.ToastUtils.c, com.blankj.utilcode.util.ToastUtils.e
        public void cancel() {
            try {
                WindowManager windowManager = this.d;
                if (windowManager != null) {
                    windowManager.removeViewImmediate(this.c);
                    this.d = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }

        public g(ToastUtils toastUtils, WindowManager windowManager, int i) {
            super(toastUtils);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.e = layoutParams;
            this.d = windowManager;
            layoutParams.type = i;
        }
    }

    public static void showLong(@Nullable String str, Object... objArr) {
        q(com.blankj.utilcode.util.b.F(str, objArr), 1, l);
    }

    public static void showShort(@Nullable String str, Object... objArr) {
        q(com.blankj.utilcode.util.b.F(str, objArr), 0, l);
    }

    public final void show(@Nullable String str, Object... objArr) {
        q(com.blankj.utilcode.util.b.F(str, objArr), l(), this);
    }

    public final void show(@NonNull View view) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        o(view, l(), this);
    }
}
