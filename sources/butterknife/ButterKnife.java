package butterknife;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class ButterKnife {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1859a = false;
    @VisibleForTesting
    public static final Map<Class<?>, Constructor<? extends Unbinder>> b = new LinkedHashMap();

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    @Nullable
    @CheckResult
    @UiThread
    public static Constructor<? extends Unbinder> a(Class<?> cls) {
        Constructor<? extends Unbinder> a2;
        Map<Class<?>, Constructor<? extends Unbinder>> map = b;
        Constructor<? extends Unbinder> constructor = map.get(cls);
        if (constructor == null && !map.containsKey(cls)) {
            String name = cls.getName();
            if (!name.startsWith("android.") && !name.startsWith("java.") && !name.startsWith("androidx.")) {
                try {
                    ClassLoader classLoader = cls.getClassLoader();
                    a2 = classLoader.loadClass(name + "_ViewBinding").getConstructor(cls, View.class);
                    if (f1859a) {
                        Log.d("ButterKnife", "HIT: Loaded binding class and constructor.");
                    }
                } catch (ClassNotFoundException unused) {
                    if (f1859a) {
                        Log.d("ButterKnife", "Not found. Trying superclass " + cls.getSuperclass().getName());
                    }
                    a2 = a(cls.getSuperclass());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException("Unable to find binding constructor for " + name, e);
                }
                b.put(cls, a2);
                return a2;
            } else if (f1859a) {
                Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
                return null;
            } else {
                return null;
            }
        }
        if (f1859a) {
            Log.d("ButterKnife", "HIT: Cached in binding map.");
        }
        return constructor;
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull Activity activity) {
        return bind(activity, activity.getWindow().getDecorView());
    }

    public static void setDebug(boolean z) {
        f1859a = z;
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull View view) {
        return bind(view, view);
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull Dialog dialog) {
        return bind(dialog, dialog.getWindow().getDecorView());
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull Object obj, @NonNull Activity activity) {
        return bind(obj, activity.getWindow().getDecorView());
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull Object obj, @NonNull Dialog dialog) {
        return bind(obj, dialog.getWindow().getDecorView());
    }

    @NonNull
    @UiThread
    public static Unbinder bind(@NonNull Object obj, @NonNull View view) {
        Class<?> cls = obj.getClass();
        if (f1859a) {
            Log.d("ButterKnife", "Looking up binding for " + cls.getName());
        }
        Constructor<? extends Unbinder> a2 = a(cls);
        if (a2 == null) {
            return Unbinder.EMPTY;
        }
        try {
            return a2.newInstance(obj, view);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + a2, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Unable to invoke " + a2, e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (!(cause instanceof RuntimeException)) {
                if (cause instanceof Error) {
                    throw ((Error) cause);
                }
                throw new RuntimeException("Unable to create binding instance.", cause);
            }
            throw ((RuntimeException) cause);
        }
    }
}
