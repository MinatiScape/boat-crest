package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@RequiresApi(21)
@SuppressLint({"SoonBlockedPrivateApi"})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class f {

    /* renamed from: a  reason: collision with root package name */
    public static final Field f1052a;
    public static final Method b;
    public static final Method c;
    public static final Constructor<Typeface> d;
    @GuardedBy("sWeightCacheLock")
    public static final LongSparseArray<SparseArray<Typeface>> e;
    public static final Object f;

    static {
        Method method;
        Method method2;
        Constructor<Typeface> constructor;
        Field field = null;
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            Class cls = Long.TYPE;
            Class cls2 = Integer.TYPE;
            method = Typeface.class.getDeclaredMethod("nativeCreateFromTypeface", cls, cls2);
            method.setAccessible(true);
            method2 = Typeface.class.getDeclaredMethod("nativeCreateWeightAlias", cls, cls2);
            method2.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(cls);
            constructor.setAccessible(true);
            field = declaredField;
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e("WeightTypeface", e2.getClass().getName(), e2);
            method = null;
            method2 = null;
            constructor = null;
        }
        f1052a = field;
        b = method;
        c = method2;
        d = constructor;
        e = new LongSparseArray<>(3);
        f = new Object();
    }

    @Nullable
    public static Typeface a(long j) {
        try {
            return d.newInstance(Long.valueOf(j));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Nullable
    public static Typeface b(@NonNull Typeface typeface, int i, boolean z) {
        Typeface a2;
        if (d()) {
            int i2 = (i << 1) | z;
            synchronized (f) {
                long c2 = c(typeface);
                LongSparseArray<SparseArray<Typeface>> longSparseArray = e;
                SparseArray<Typeface> sparseArray = longSparseArray.get(c2);
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>(4);
                    longSparseArray.put(c2, sparseArray);
                } else {
                    Typeface typeface2 = sparseArray.get(i2);
                    if (typeface2 != null) {
                        return typeface2;
                    }
                }
                if (z == typeface.isItalic()) {
                    a2 = a(f(c2, i));
                } else {
                    a2 = a(e(c2, i, z));
                }
                sparseArray.put(i2, a2);
                return a2;
            }
        }
        return null;
    }

    public static long c(@NonNull Typeface typeface) {
        try {
            return f1052a.getLong(typeface);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean d() {
        return f1052a != null;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static long e(long j, int i, boolean z) {
        try {
            return ((Long) c.invoke(null, Long.valueOf(((Long) b.invoke(null, Long.valueOf(j), Integer.valueOf(z ? 2 : 0))).longValue()), Integer.valueOf(i))).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static long f(long j, int i) {
        try {
            return ((Long) c.invoke(null, Long.valueOf(j), Integer.valueOf(i))).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
