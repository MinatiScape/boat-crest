package com.blankj.utilcode.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import java.lang.reflect.Field;
import java.util.Objects;
/* loaded from: classes.dex */
public final class KeyboardUtils {

    /* renamed from: a  reason: collision with root package name */
    public static long f2259a;
    public static int b;

    /* loaded from: classes.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    /* loaded from: classes.dex */
    public static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ Window h;
        public final /* synthetic */ int[] i;
        public final /* synthetic */ OnSoftInputChangedListener j;

        public a(Window window, int[] iArr, OnSoftInputChangedListener onSoftInputChangedListener) {
            this.h = window;
            this.i = iArr;
            this.j = onSoftInputChangedListener;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int d = KeyboardUtils.d(this.h);
            if (this.i[0] != d) {
                this.j.onSoftInputChanged(d);
                this.i[0] = d;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ Window h;
        public final /* synthetic */ int[] i;
        public final /* synthetic */ View j;
        public final /* synthetic */ int k;

        public b(Window window, int[] iArr, View view, int i) {
            this.h = window;
            this.i = iArr;
            this.j = view;
            this.k = i;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int c = KeyboardUtils.c(this.h);
            if (this.i[0] != c) {
                View view = this.j;
                view.setPadding(view.getPaddingLeft(), this.j.getPaddingTop(), this.j.getPaddingRight(), this.k + KeyboardUtils.d(this.h));
                this.i[0] = c;
            }
        }
    }

    public KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int c(Window window) {
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= com.blankj.utilcode.util.b.e0() + com.blankj.utilcode.util.b.a0()) {
            return 0;
        }
        return abs;
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    public static int d(@NonNull Window window) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View decorView = window.getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs <= com.blankj.utilcode.util.b.a0() + com.blankj.utilcode.util.b.e0()) {
            b = abs;
            return 0;
        }
        return abs - b;
    }

    public static void fixAndroidBug5497(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        fixAndroidBug5497(activity.getWindow());
    }

    public static void fixSoftInputLeaks(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        fixSoftInputLeaks(activity.getWindow());
    }

    public static void hideSoftInput(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        hideSoftInput(activity.getWindow());
    }

    public static void hideSoftInputByToggle(Activity activity) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - f2259a) > 500 && isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
        f2259a = elapsedRealtime;
    }

    public static boolean isSoftInputVisible(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return d(activity.getWindow()) > 0;
    }

    public static void registerSoftInputChangedListener(@NonNull Activity activity, @NonNull OnSoftInputChangedListener onSoftInputChangedListener) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(onSoftInputChangedListener, "Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
    }

    public static void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public static void unregisterSoftInputChangedListener(@NonNull Window window) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return;
        }
        Object tag = findViewById.getTag(-8);
        if (!(tag instanceof ViewTreeObserver.OnGlobalLayoutListener) || Build.VERSION.SDK_INT < 16) {
            return;
        }
        findViewById.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) tag);
    }

    public static void fixAndroidBug5497(@NonNull Window window) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        window.setSoftInputMode(window.getAttributes().softInputMode & (-17));
        FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
        View childAt = frameLayout.getChildAt(0);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new b(window, new int[]{c(window)}, childAt, childAt.getPaddingBottom()));
    }

    public static void fixSoftInputLeaks(@NonNull Window window) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        String[] strArr = {"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"};
        for (int i = 0; i < 4; i++) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(strArr[i]);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void hideSoftInput(@NonNull Window window) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        View currentFocus = window.getCurrentFocus();
        if (currentFocus == null) {
            View decorView = window.getDecorView();
            View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
            if (findViewWithTag == null) {
                findViewWithTag = new EditText(window.getContext());
                findViewWithTag.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(findViewWithTag, 0, 0);
            }
            currentFocus = findViewWithTag;
            currentFocus.requestFocus();
        }
        hideSoftInput(currentFocus);
    }

    public static void showSoftInput(@NonNull Activity activity) {
        Objects.requireNonNull(activity, "Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if (isSoftInputVisible(activity)) {
            return;
        }
        toggleSoftInput();
    }

    public static void registerSoftInputChangedListener(@NonNull Window window, @NonNull OnSoftInputChangedListener onSoftInputChangedListener) {
        Objects.requireNonNull(window, "Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(onSoftInputChangedListener, "Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        if ((window.getAttributes().flags & 512) != 0) {
            window.clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
        a aVar = new a(window, new int[]{d(window)}, onSoftInputChangedListener);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(aVar);
        frameLayout.setTag(-8, aVar);
    }

    public static void showSoftInput(@NonNull View view) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        showSoftInput(view, 0);
    }

    public static void showSoftInput(@NonNull View view, int i) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) { // from class: com.blankj.utilcode.util.KeyboardUtils.1
            @Override // android.os.ResultReceiver
            public void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 1 || i2 == 3) {
                    KeyboardUtils.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void hideSoftInput(@NonNull View view) {
        Objects.requireNonNull(view, "Argument 'view' of type View (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
