package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class d {
    @SuppressLint({"BanConcurrentHashMap"})

    /* renamed from: a  reason: collision with root package name */
    public ConcurrentHashMap<Long, FontResourcesParserCompat.FontFamilyFilesResourceEntry> f1046a = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public class a implements InterfaceC0127d<FontsContractCompat.FontInfo> {
        public a(d dVar) {
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: c */
        public int b(FontsContractCompat.FontInfo fontInfo) {
            return fontInfo.getWeight();
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: d */
        public boolean a(FontsContractCompat.FontInfo fontInfo) {
            return fontInfo.isItalic();
        }
    }

    /* loaded from: classes.dex */
    public class b implements InterfaceC0127d<FontResourcesParserCompat.FontFileResourceEntry> {
        public b(d dVar) {
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: c */
        public int b(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.getWeight();
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: d */
        public boolean a(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.isItalic();
        }
    }

    /* loaded from: classes.dex */
    public class c implements InterfaceC0127d<FontResourcesParserCompat.FontFileResourceEntry> {
        public c(d dVar) {
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: c */
        public int b(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.getWeight();
        }

        @Override // androidx.core.graphics.d.InterfaceC0127d
        /* renamed from: d */
        public boolean a(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.isItalic();
        }
    }

    /* renamed from: androidx.core.graphics.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0127d<T> {
        boolean a(T t);

        int b(T t);
    }

    public static <T> T f(T[] tArr, int i, InterfaceC0127d<T> interfaceC0127d) {
        return (T) g(tArr, (i & 1) == 0 ? 400 : TypedValues.TransitionType.TYPE_DURATION, (i & 2) != 0, interfaceC0127d);
    }

    public static <T> T g(T[] tArr, int i, boolean z, InterfaceC0127d<T> interfaceC0127d) {
        T t = null;
        int i2 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = (Math.abs(interfaceC0127d.b(t2) - i) * 2) + (interfaceC0127d.a(t2) == z ? 0 : 1);
            if (t == null || i2 > abs) {
                t = t2;
                i2 = abs;
            }
        }
        return t;
    }

    public static long i(@Nullable Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e);
            return 0L;
        } catch (NoSuchFieldException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        }
    }

    public final void a(Typeface typeface, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry) {
        long i = i(typeface);
        if (i != 0) {
            this.f1046a.put(Long.valueOf(i), fontFamilyFilesResourceEntry);
        }
    }

    @Nullable
    public Typeface b(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i, boolean z) {
        FontResourcesParserCompat.FontFileResourceEntry e = e(fontFamilyFilesResourceEntry, i, z);
        if (e == null) {
            return null;
        }
        Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, e.getResourceId(), e.getFileName(), 0, 0);
        a(createFromResourcesFontFile, fontFamilyFilesResourceEntry);
        return createFromResourcesFontFile;
    }

    @NonNull
    public Typeface c(@NonNull Context context, @NonNull Typeface typeface, int i, boolean z) {
        Typeface typeface2;
        try {
            typeface2 = e.a(this, context, typeface, i, z);
        } catch (RuntimeException unused) {
            typeface2 = null;
        }
        return typeface2 == null ? typeface : typeface2;
    }

    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat.FontFileResourceEntry d = d(fontFamilyFilesResourceEntry, i);
        if (d == null) {
            return null;
        }
        Typeface createFromResourcesFontFile = TypefaceCompat.createFromResourcesFontFile(context, resources, d.getResourceId(), d.getFileName(), 0, i);
        a(createFromResourcesFontFile, fontFamilyFilesResourceEntry);
        return createFromResourcesFontFile;
    }

    @Nullable
    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        if (fontInfoArr.length < 1) {
            return null;
        }
        try {
            inputStream = context.getContentResolver().openInputStream(findBestInfo(fontInfoArr, i).getUri());
        } catch (IOException unused) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Typeface createFromInputStream = createFromInputStream(context, inputStream);
            TypefaceCompatUtil.closeQuietly(inputStream);
            return createFromInputStream;
        } catch (IOException unused2) {
            TypefaceCompatUtil.closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            TypefaceCompatUtil.closeQuietly(inputStream2);
            throw th;
        }
    }

    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, resources, i)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    public final FontResourcesParserCompat.FontFileResourceEntry d(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i) {
        return (FontResourcesParserCompat.FontFileResourceEntry) f(fontFamilyFilesResourceEntry.getEntries(), i, new b(this));
    }

    public final FontResourcesParserCompat.FontFileResourceEntry e(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i, boolean z) {
        return (FontResourcesParserCompat.FontFileResourceEntry) g(fontFamilyFilesResourceEntry.getEntries(), i, z, new c(this));
    }

    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        return (FontsContractCompat.FontInfo) f(fontInfoArr, i, new a(this));
    }

    @Nullable
    public FontResourcesParserCompat.FontFamilyFilesResourceEntry h(Typeface typeface) {
        long i = i(typeface);
        if (i == 0) {
            return null;
        }
        return this.f1046a.get(Long.valueOf(i));
    }
}
