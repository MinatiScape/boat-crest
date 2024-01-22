package androidx.core.provider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Consumer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final LruCache<String, Typeface> f1087a = new LruCache<>(16);
    public static final ExecutorService b = f.a("fonts-androidx", 10, 10000);
    public static final Object c = new Object();
    @GuardedBy("LOCK")
    public static final SimpleArrayMap<String, ArrayList<Consumer<C0131e>>> d = new SimpleArrayMap<>();

    /* loaded from: classes.dex */
    public class a implements Callable<C0131e> {
        public final /* synthetic */ String h;
        public final /* synthetic */ Context i;
        public final /* synthetic */ FontRequest j;
        public final /* synthetic */ int k;

        public a(String str, Context context, FontRequest fontRequest, int i) {
            this.h = str;
            this.i = context;
            this.j = fontRequest;
            this.k = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public C0131e call() {
            return e.c(this.h, this.i, this.j, this.k);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Consumer<C0131e> {
        public final /* synthetic */ androidx.core.provider.a h;

        public b(androidx.core.provider.a aVar) {
            this.h = aVar;
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: a */
        public void accept(C0131e c0131e) {
            if (c0131e == null) {
                c0131e = new C0131e(-3);
            }
            this.h.b(c0131e);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Callable<C0131e> {
        public final /* synthetic */ String h;
        public final /* synthetic */ Context i;
        public final /* synthetic */ FontRequest j;
        public final /* synthetic */ int k;

        public c(String str, Context context, FontRequest fontRequest, int i) {
            this.h = str;
            this.i = context;
            this.j = fontRequest;
            this.k = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public C0131e call() {
            try {
                return e.c(this.h, this.i, this.j, this.k);
            } catch (Throwable unused) {
                return new C0131e(-3);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements Consumer<C0131e> {
        public final /* synthetic */ String h;

        public d(String str) {
            this.h = str;
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: a */
        public void accept(C0131e c0131e) {
            synchronized (e.c) {
                SimpleArrayMap<String, ArrayList<Consumer<C0131e>>> simpleArrayMap = e.d;
                ArrayList<Consumer<C0131e>> arrayList = simpleArrayMap.get(this.h);
                if (arrayList == null) {
                    return;
                }
                simpleArrayMap.remove(this.h);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).accept(c0131e);
                }
            }
        }
    }

    public static String a(@NonNull FontRequest fontRequest, int i) {
        return fontRequest.b() + "-" + i;
    }

    @SuppressLint({"WrongConstant"})
    public static int b(@NonNull FontsContractCompat.FontFamilyResult fontFamilyResult) {
        int i = 1;
        if (fontFamilyResult.getStatusCode() != 0) {
            return fontFamilyResult.getStatusCode() != 1 ? -3 : -2;
        }
        FontsContractCompat.FontInfo[] fonts = fontFamilyResult.getFonts();
        if (fonts != null && fonts.length != 0) {
            i = 0;
            for (FontsContractCompat.FontInfo fontInfo : fonts) {
                int resultCode = fontInfo.getResultCode();
                if (resultCode != 0) {
                    if (resultCode < 0) {
                        return -3;
                    }
                    return resultCode;
                }
            }
        }
        return i;
    }

    @NonNull
    public static C0131e c(@NonNull String str, @NonNull Context context, @NonNull FontRequest fontRequest, int i) {
        LruCache<String, Typeface> lruCache = f1087a;
        Typeface typeface = lruCache.get(str);
        if (typeface != null) {
            return new C0131e(typeface);
        }
        try {
            FontsContractCompat.FontFamilyResult e = androidx.core.provider.d.e(context, fontRequest, null);
            int b2 = b(e);
            if (b2 != 0) {
                return new C0131e(b2);
            }
            Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, e.getFonts(), i);
            if (createFromFontInfo != null) {
                lruCache.put(str, createFromFontInfo);
                return new C0131e(createFromFontInfo);
            }
            return new C0131e(-3);
        } catch (PackageManager.NameNotFoundException unused) {
            return new C0131e(-1);
        }
    }

    public static Typeface d(@NonNull Context context, @NonNull FontRequest fontRequest, int i, @Nullable Executor executor, @NonNull androidx.core.provider.a aVar) {
        String a2 = a(fontRequest, i);
        Typeface typeface = f1087a.get(a2);
        if (typeface != null) {
            aVar.b(new C0131e(typeface));
            return typeface;
        }
        b bVar = new b(aVar);
        synchronized (c) {
            SimpleArrayMap<String, ArrayList<Consumer<C0131e>>> simpleArrayMap = d;
            ArrayList<Consumer<C0131e>> arrayList = simpleArrayMap.get(a2);
            if (arrayList != null) {
                arrayList.add(bVar);
                return null;
            }
            ArrayList<Consumer<C0131e>> arrayList2 = new ArrayList<>();
            arrayList2.add(bVar);
            simpleArrayMap.put(a2, arrayList2);
            c cVar = new c(a2, context, fontRequest, i);
            if (executor == null) {
                executor = b;
            }
            f.c(executor, cVar, new d(a2));
            return null;
        }
    }

    public static Typeface e(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull androidx.core.provider.a aVar, int i, int i2) {
        String a2 = a(fontRequest, i);
        Typeface typeface = f1087a.get(a2);
        if (typeface != null) {
            aVar.b(new C0131e(typeface));
            return typeface;
        } else if (i2 == -1) {
            C0131e c2 = c(a2, context, fontRequest, i);
            aVar.b(c2);
            return c2.f1088a;
        } else {
            try {
                C0131e c0131e = (C0131e) f.d(b, new a(a2, context, fontRequest, i), i2);
                aVar.b(c0131e);
                return c0131e.f1088a;
            } catch (InterruptedException unused) {
                aVar.b(new C0131e(-3));
                return null;
            }
        }
    }

    public static void f() {
        f1087a.evictAll();
    }

    /* renamed from: androidx.core.provider.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class C0131e {

        /* renamed from: a  reason: collision with root package name */
        public final Typeface f1088a;
        public final int b;

        public C0131e(int i) {
            this.f1088a = null;
            this.b = i;
        }

        @SuppressLint({"WrongConstant"})
        public boolean a() {
            return this.b == 0;
        }

        @SuppressLint({"WrongConstant"})
        public C0131e(@NonNull Typeface typeface) {
            this.f1088a = typeface;
            this.b = 0;
        }
    }
}
