package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.FontResourcesParserCompat;
import java.lang.reflect.Field;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static final Field f1051a;
    @GuardedBy("sWeightCacheLock")
    public static final LongSparseArray<SparseArray<Typeface>> b;
    public static final Object c;

    static {
        Field field;
        try {
            field = Typeface.class.getDeclaredField("native_instance");
            field.setAccessible(true);
        } catch (Exception e) {
            Log.e("WeightTypeface", e.getClass().getName(), e);
            field = null;
        }
        f1051a = field;
        b = new LongSparseArray<>(3);
        c = new Object();
    }

    @Nullable
    public static Typeface a(@NonNull d dVar, @NonNull Context context, @NonNull Typeface typeface, int i, boolean z) {
        if (d()) {
            int i2 = (i << 1) | (z ? 1 : 0);
            synchronized (c) {
                long c2 = c(typeface);
                LongSparseArray<SparseArray<Typeface>> longSparseArray = b;
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
                Typeface b2 = b(dVar, context, typeface, i, z);
                if (b2 == null) {
                    b2 = e(typeface, i, z);
                }
                sparseArray.put(i2, b2);
                return b2;
            }
        }
        return null;
    }

    @Nullable
    public static Typeface b(@NonNull d dVar, @NonNull Context context, @NonNull Typeface typeface, int i, boolean z) {
        FontResourcesParserCompat.FontFamilyFilesResourceEntry h = dVar.h(typeface);
        if (h == null) {
            return null;
        }
        return dVar.b(context, h, context.getResources(), i, z);
    }

    public static long c(@NonNull Typeface typeface) {
        try {
            return ((Number) f1051a.get(typeface)).longValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean d() {
        return f1051a != null;
    }

    public static Typeface e(Typeface typeface, int i, boolean z) {
        int i2 = 1;
        boolean z2 = i >= 600;
        if (!z2 && !z) {
            i2 = 0;
        } else if (!z2) {
            i2 = 2;
        } else if (z) {
            i2 = 3;
        }
        return Typeface.create(typeface, i2);
    }
}
