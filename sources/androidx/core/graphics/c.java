package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class c extends d {
    public static final Class<?> b;
    public static final Constructor<?> c;
    public static final Method d;
    public static final Method e;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            Class<?> cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        c = constructor;
        b = cls;
        d = method2;
        e = method;
    }

    public static Typeface createFromFamiliesWithDefault(Object obj) {
        try {
            Object newInstance = Array.newInstance(b, 1);
            Array.set(newInstance, 0, obj);
            return (Typeface) e.invoke(null, newInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public static boolean j(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static boolean k() {
        Method method = d;
        if (method == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return method != null;
    }

    public static Object l() {
        try {
            return c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // androidx.core.graphics.d
    @NonNull
    public Typeface c(@NonNull Context context, @NonNull Typeface typeface, int i, boolean z) {
        Typeface typeface2;
        try {
            typeface2 = f.b(typeface, i, z);
        } catch (RuntimeException unused) {
            typeface2 = null;
        }
        return typeface2 == null ? super.c(context, typeface, i, z) : typeface2;
    }

    @Override // androidx.core.graphics.d
    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat.FontFileResourceEntry[] entries;
        Object l = l();
        if (l == null) {
            return null;
        }
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            ByteBuffer copyToDirectBuffer = TypefaceCompatUtil.copyToDirectBuffer(context, resources, fontFileResourceEntry.getResourceId());
            if (copyToDirectBuffer == null || !j(l, copyToDirectBuffer, fontFileResourceEntry.getTtcIndex(), fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic())) {
                return null;
            }
        }
        return createFromFamiliesWithDefault(l);
    }

    @Override // androidx.core.graphics.d
    @Nullable
    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        Object l = l();
        if (l == null) {
            return null;
        }
        SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
        for (FontsContractCompat.FontInfo fontInfo : fontInfoArr) {
            Uri uri = fontInfo.getUri();
            ByteBuffer byteBuffer = (ByteBuffer) simpleArrayMap.get(uri);
            if (byteBuffer == null) {
                byteBuffer = TypefaceCompatUtil.mmap(context, cancellationSignal, uri);
                simpleArrayMap.put(uri, byteBuffer);
            }
            if (byteBuffer == null || !j(l, byteBuffer, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic())) {
                return null;
            }
        }
        Typeface createFromFamiliesWithDefault = createFromFamiliesWithDefault(l);
        if (createFromFamiliesWithDefault == null) {
            return null;
        }
        return Typeface.create(createFromFamiliesWithDefault, i);
    }
}