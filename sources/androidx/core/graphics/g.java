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
@RequiresApi(26)
@SuppressLint({"SoonBlockedPrivateApi"})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public static final Field f1053a;
    public static final Method b;
    public static final Constructor<Typeface> c;
    @GuardedBy("sWeightCacheLock")
    public static final LongSparseArray<SparseArray<Typeface>> d;
    public static final Object e;

    static {
        Method method;
        Constructor<Typeface> constructor;
        Field field = null;
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            Class cls = Long.TYPE;
            method = Typeface.class.getDeclaredMethod("nativeCreateFromTypefaceWithExactStyle", cls, Integer.TYPE, Boolean.TYPE);
            method.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(cls);
            constructor.setAccessible(true);
            field = declaredField;
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e("WeightTypeface", e2.getClass().getName(), e2);
            method = null;
            constructor = null;
        }
        f1053a = field;
        b = method;
        c = constructor;
        d = new LongSparseArray<>(3);
        e = new Object();
    }

    @Nullable
    public static Typeface a(long j) {
        try {
            return c.newInstance(Long.valueOf(j));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Nullable
    public static Typeface b(@NonNull Typeface typeface, int i, boolean z) {
        if (d()) {
            int i2 = (i << 1) | (z ? 1 : 0);
            synchronized (e) {
                long c2 = c(typeface);
                LongSparseArray<SparseArray<Typeface>> longSparseArray = d;
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
                Typeface a2 = a(e(c2, i, z));
                sparseArray.put(i2, a2);
                return a2;
            }
        }
        return null;
    }

    public static long c(@NonNull Typeface typeface) {
        try {
            return f1053a.getLong(typeface);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static boolean d() {
        return f1053a != null;
    }

    @SuppressLint({"BanUncheckedReflection"})
    public static long e(long j, int i, boolean z) {
        try {
            return ((Long) b.invoke(null, Long.valueOf(j), Integer.valueOf(i), Boolean.valueOf(z))).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
