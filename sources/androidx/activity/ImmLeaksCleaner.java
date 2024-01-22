package androidx.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Field;
@RequiresApi(19)
/* loaded from: classes.dex */
final class ImmLeaksCleaner implements LifecycleEventObserver {
    public static int i;
    public static Field j;
    public static Field k;
    public static Field l;
    public Activity h;

    public ImmLeaksCleaner(Activity activity) {
        this.h = activity;
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    @MainThread
    public static void a() {
        try {
            i = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            k = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            l = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            j = declaredField3;
            declaredField3.setAccessible(true);
            i = 1;
        } catch (NoSuchFieldException unused) {
        }
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (i == 0) {
            a();
        }
        if (i == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.h.getSystemService("input_method");
            try {
                Object obj = j.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) k.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                l.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused2) {
                    } catch (IllegalAccessException unused3) {
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }
}
