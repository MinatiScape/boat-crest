package com.bumptech.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.util.Util;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
@RequiresApi(19)
/* loaded from: classes2.dex */
public class SizeConfigStrategy implements e {
    public static final Bitmap.Config[] d;
    public static final Bitmap.Config[] e;
    public static final Bitmap.Config[] f;
    public static final Bitmap.Config[] g;
    public static final Bitmap.Config[] h;

    /* renamed from: a  reason: collision with root package name */
    public final c f2355a = new c();
    public final d<b, Bitmap> b = new d<>();
    public final Map<Bitmap.Config, NavigableMap<Integer, Integer>> c = new HashMap();

    /* loaded from: classes2.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2356a;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            f2356a = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2356a[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2356a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2356a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        public final c f2357a;
        public int b;
        public Bitmap.Config c;

        public b(c cVar) {
            this.f2357a = cVar;
        }

        @Override // com.bumptech.glide.load.engine.bitmap_recycle.f
        public void a() {
            this.f2357a.c(this);
        }

        public void b(int i, Bitmap.Config config) {
            this.b = i;
            this.c = config;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return this.b == bVar.b && Util.bothNullOrEqual(this.c, bVar.c);
            }
            return false;
        }

        public int hashCode() {
            int i = this.b * 31;
            Bitmap.Config config = this.c;
            return i + (config != null ? config.hashCode() : 0);
        }

        public String toString() {
            return SizeConfigStrategy.c(this.b, this.c);
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class c extends com.bumptech.glide.load.engine.bitmap_recycle.c<b> {
        @Override // com.bumptech.glide.load.engine.bitmap_recycle.c
        /* renamed from: d */
        public b a() {
            return new b(this);
        }

        public b e(int i, Bitmap.Config config) {
            b b = b();
            b.b(i, config);
            return b;
        }
    }

    static {
        Bitmap.Config[] configArr = {Bitmap.Config.ARGB_8888, null};
        if (Build.VERSION.SDK_INT >= 26) {
            configArr = (Bitmap.Config[]) Arrays.copyOf(configArr, 3);
            configArr[configArr.length - 1] = Bitmap.Config.RGBA_F16;
        }
        d = configArr;
        e = configArr;
        f = new Bitmap.Config[]{Bitmap.Config.RGB_565};
        g = new Bitmap.Config[]{Bitmap.Config.ARGB_4444};
        h = new Bitmap.Config[]{Bitmap.Config.ALPHA_8};
    }

    public static String c(int i, Bitmap.Config config) {
        return "[" + i + "](" + config + ")";
    }

    public static Bitmap.Config[] d(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16.equals(config)) {
            return e;
        }
        int i = a.f2356a[config.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return i != 4 ? new Bitmap.Config[]{config} : h;
                }
                return g;
            }
            return f;
        }
        return d;
    }

    public final void a(Integer num, Bitmap bitmap) {
        NavigableMap<Integer, Integer> e2 = e(bitmap.getConfig());
        Integer num2 = (Integer) e2.get(num);
        if (num2 != null) {
            if (num2.intValue() == 1) {
                e2.remove(num);
                return;
            } else {
                e2.put(num, Integer.valueOf(num2.intValue() - 1));
                return;
            }
        }
        throw new NullPointerException("Tried to decrement empty size, size: " + num + ", removed: " + logBitmap(bitmap) + ", this: " + this);
    }

    public final b b(int i, Bitmap.Config config) {
        Bitmap.Config[] d2;
        b e2 = this.f2355a.e(i, config);
        for (Bitmap.Config config2 : d(config)) {
            Integer ceilingKey = e(config2).ceilingKey(Integer.valueOf(i));
            if (ceilingKey != null && ceilingKey.intValue() <= i * 8) {
                if (ceilingKey.intValue() == i) {
                    if (config2 == null) {
                        if (config == null) {
                            return e2;
                        }
                    } else if (config2.equals(config)) {
                        return e2;
                    }
                }
                this.f2355a.c(e2);
                return this.f2355a.e(ceilingKey.intValue(), config2);
            }
        }
        return e2;
    }

    public final NavigableMap<Integer, Integer> e(Bitmap.Config config) {
        NavigableMap<Integer, Integer> navigableMap = this.c.get(config);
        if (navigableMap == null) {
            TreeMap treeMap = new TreeMap();
            this.c.put(config, treeMap);
            return treeMap;
        }
        return navigableMap;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    @Nullable
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        b b2 = b(Util.getBitmapByteSize(i, i2, config), config);
        Bitmap a2 = this.b.a(b2);
        if (a2 != null) {
            a(Integer.valueOf(b2.b), a2);
            a2.reconfigure(i, i2, config);
        }
        return a2;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public int getSize(Bitmap bitmap) {
        return Util.getBitmapByteSize(bitmap);
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public String logBitmap(Bitmap bitmap) {
        return c(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public void put(Bitmap bitmap) {
        b e2 = this.f2355a.e(Util.getBitmapByteSize(bitmap), bitmap.getConfig());
        this.b.d(e2, bitmap);
        NavigableMap<Integer, Integer> e3 = e(bitmap.getConfig());
        Integer num = (Integer) e3.get(Integer.valueOf(e2.b));
        e3.put(Integer.valueOf(e2.b), Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    @Nullable
    public Bitmap removeLast() {
        Bitmap f2 = this.b.f();
        if (f2 != null) {
            a(Integer.valueOf(Util.getBitmapByteSize(f2)), f2);
        }
        return f2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy{groupedMap=");
        sb.append(this.b);
        sb.append(", sortedSizes=(");
        for (Map.Entry<Bitmap.Config, NavigableMap<Integer, Integer>> entry : this.c.entrySet()) {
            sb.append(entry.getKey());
            sb.append('[');
            sb.append(entry.getValue());
            sb.append("], ");
        }
        if (!this.c.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")}");
        return sb.toString();
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.e
    public String logBitmap(int i, int i2, Bitmap.Config config) {
        return c(Util.getBitmapByteSize(i, i2, config), config);
    }
}
