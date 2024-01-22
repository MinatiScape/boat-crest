package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import com.blankj.utilcode.constant.CacheConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CacheDiskUtils implements CacheConstants {
    public static final Map<String, CacheDiskUtils> f = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public final String f2238a;
    public final File b;
    public final long c;
    public final int d;
    public c e;

    /* loaded from: classes.dex */
    public static final class b {
        public static byte[] d(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        public static String e(int i) {
            return String.format(Locale.getDefault(), "_$%010d$_", Long.valueOf((System.currentTimeMillis() / 1000) + i));
        }

        public static byte[] f(byte[] bArr) {
            return h(bArr) ? d(bArr, 14, bArr.length) : bArr;
        }

        public static long g(byte[] bArr) {
            if (h(bArr)) {
                try {
                    return Long.parseLong(new String(d(bArr, 2, 12))) * 1000;
                } catch (NumberFormatException unused) {
                }
            }
            return -1L;
        }

        public static boolean h(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == 95 && bArr[1] == 36 && bArr[12] == 36 && bArr[13] == 95;
        }

        public static boolean i(byte[] bArr) {
            long g = g(bArr);
            return g != -1 && System.currentTimeMillis() > g;
        }

        public static byte[] j(int i, byte[] bArr) {
            byte[] bytes = e(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicLong f2239a;
        public final AtomicInteger b;
        public final long c;
        public final int d;
        public final Map<File, Long> e;
        public final File f;
        public final Thread g;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ File h;

            /* renamed from: com.blankj.utilcode.util.CacheDiskUtils$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes.dex */
            public class C0205a implements FilenameFilter {
                public C0205a(a aVar) {
                }

                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return str.startsWith("cdu_");
                }
            }

            public a(File file) {
                this.h = file;
            }

            @Override // java.lang.Runnable
            public void run() {
                File[] listFiles = this.h.listFiles(new C0205a(this));
                if (listFiles != null) {
                    int i = 0;
                    int i2 = 0;
                    for (File file : listFiles) {
                        i = (int) (i + file.length());
                        i2++;
                        c.this.e.put(file, Long.valueOf(file.lastModified()));
                    }
                    c.this.f2239a.getAndAdd(i);
                    c.this.b.getAndAdd(i2);
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements FilenameFilter {
            public b(c cVar) {
            }

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.startsWith("cdu_");
            }
        }

        public final boolean l() {
            File[] listFiles = this.f.listFiles(new b(this));
            boolean z = true;
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.delete()) {
                        this.f2239a.addAndGet(-file.length());
                        this.b.addAndGet(-1);
                        this.e.remove(file);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    this.e.clear();
                    this.f2239a.set(0L);
                    this.b.set(0);
                }
            }
            return z;
        }

        public final int m() {
            v();
            return this.b.get();
        }

        public final String n(String str) {
            return "cdu_" + str.substring(0, 3) + str.substring(3).hashCode();
        }

        public final long o() {
            v();
            return this.f2239a.get();
        }

        public final File p(String str) {
            v();
            File file = new File(this.f, n(str));
            if (file.exists()) {
                this.b.addAndGet(-1);
                this.f2239a.addAndGet(-file.length());
            }
            return file;
        }

        public final File q(String str) {
            File file = new File(this.f, n(str));
            if (file.exists()) {
                return file;
            }
            return null;
        }

        public final void r(File file) {
            this.b.addAndGet(1);
            this.f2239a.addAndGet(file.length());
            while (true) {
                if (this.b.get() <= this.d && this.f2239a.get() <= this.c) {
                    return;
                }
                this.f2239a.addAndGet(-t());
                this.b.addAndGet(-1);
            }
        }

        public final boolean s(String str) {
            File q = q(str);
            if (q == null) {
                return true;
            }
            if (q.delete()) {
                this.f2239a.addAndGet(-q.length());
                this.b.addAndGet(-1);
                this.e.remove(q);
                return true;
            }
            return false;
        }

        public final long t() {
            if (this.e.isEmpty()) {
                return 0L;
            }
            Long l = Long.MAX_VALUE;
            File file = null;
            Set<Map.Entry<File, Long>> entrySet = this.e.entrySet();
            synchronized (this.e) {
                for (Map.Entry<File, Long> entry : entrySet) {
                    Long value = entry.getValue();
                    if (value.longValue() < l.longValue()) {
                        file = entry.getKey();
                        l = value;
                    }
                }
            }
            if (file == null) {
                return 0L;
            }
            long length = file.length();
            if (file.delete()) {
                this.e.remove(file);
                return length;
            }
            return 0L;
        }

        public final void u(File file) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.e.put(file, valueOf);
        }

        public final void v() {
            try {
                this.g.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public c(File file, long j, int i) {
            this.e = Collections.synchronizedMap(new HashMap());
            this.f = file;
            this.c = j;
            this.d = i;
            this.f2239a = new AtomicLong();
            this.b = new AtomicInteger();
            Thread thread = new Thread(new a(file));
            this.g = thread;
            thread.start();
        }
    }

    public CacheDiskUtils(String str, File file, long j, int i) {
        this.f2238a = str;
        this.b = file;
        this.c = j;
        this.d = i;
    }

    public static CacheDiskUtils getInstance() {
        return getInstance("", Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public final c a() {
        if (this.b.exists()) {
            if (this.e == null) {
                this.e = new c(this.b, this.c, this.d);
            }
        } else if (this.b.mkdirs()) {
            this.e = new c(this.b, this.c, this.d);
        } else {
            Log.e("CacheDiskUtils", "can't make dirs in " + this.b.getAbsolutePath());
        }
        return this.e;
    }

    public final byte[] b(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return c(str, null);
    }

    public final byte[] c(@NonNull String str, byte[] bArr) {
        File q;
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        c a2 = a();
        if (a2 == null || (q = a2.q(str)) == null) {
            return bArr;
        }
        byte[] O0 = com.blankj.utilcode.util.b.O0(q);
        if (b.i(O0)) {
            a2.s(str);
            return bArr;
        }
        a2.u(q);
        return b.f(O0);
    }

    public boolean clear() {
        c a2 = a();
        if (a2 == null) {
            return true;
        }
        return a2.l();
    }

    public final void d(String str, byte[] bArr, int i) {
        c a2;
        if (bArr == null || (a2 = a()) == null) {
            return;
        }
        if (i >= 0) {
            bArr = b.j(i, bArr);
        }
        File p = a2.p(str);
        com.blankj.utilcode.util.b.g1(p, bArr);
        a2.u(p);
        a2.r(p);
    }

    public Bitmap getBitmap(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBitmap(str, null);
    }

    public byte[] getBytes(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getBytes(str, null);
    }

    public int getCacheCount() {
        c a2 = a();
        if (a2 == null) {
            return 0;
        }
        return a2.m();
    }

    public long getCacheSize() {
        c a2 = a();
        if (a2 == null) {
            return 0L;
        }
        return a2.o();
    }

    public Drawable getDrawable(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getDrawable(str, null);
    }

    public JSONArray getJSONArray(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getJSONArray(str, null);
    }

    public JSONObject getJSONObject(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getJSONObject(str, null);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(creator, "Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return (T) getParcelable(str, creator, null);
    }

    public Object getSerializable(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getSerializable(str, null);
    }

    public String getString(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getString(str, null);
    }

    public void put(@NonNull String str, byte[] bArr) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, bArr, -1);
    }

    public boolean remove(@NonNull String str) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        c a2 = a();
        if (a2 == null) {
            return true;
        }
        if (a2.s("by_" + str)) {
            if (a2.s("st_" + str)) {
                if (a2.s("jo_" + str)) {
                    if (a2.s("ja_" + str)) {
                        if (a2.s("bi_" + str)) {
                            if (a2.s("dr_" + str)) {
                                if (a2.s("pa_" + str)) {
                                    if (a2.s("se_" + str)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public String toString() {
        return this.f2238a + "@" + Integer.toHexString(hashCode());
    }

    public static CacheDiskUtils getInstance(String str) {
        return getInstance(str, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(long j, int i) {
        return getInstance("", j, i);
    }

    public Bitmap getBitmap(@NonNull String str, Bitmap bitmap) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("bi_" + str);
        return b2 == null ? bitmap : com.blankj.utilcode.util.b.j(b2);
    }

    public byte[] getBytes(@NonNull String str, byte[] bArr) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return c("by_" + str, bArr);
    }

    public Drawable getDrawable(@NonNull String str, Drawable drawable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("dr_" + str);
        return b2 == null ? drawable : com.blankj.utilcode.util.b.k(b2);
    }

    public JSONArray getJSONArray(@NonNull String str, JSONArray jSONArray) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("ja_" + str);
        return b2 == null ? jSONArray : com.blankj.utilcode.util.b.m(b2);
    }

    public JSONObject getJSONObject(@NonNull String str, JSONObject jSONObject) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("jo_" + str);
        return b2 == null ? jSONObject : com.blankj.utilcode.util.b.n(b2);
    }

    public Object getSerializable(@NonNull String str, Object obj) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("se_" + str);
        return b2 == null ? obj : com.blankj.utilcode.util.b.o(b2);
    }

    public String getString(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("st_" + str);
        return b2 == null ? str2 : com.blankj.utilcode.util.b.q(b2);
    }

    public void put(@NonNull String str, byte[] bArr, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("by_" + str, bArr, i);
    }

    public static CacheDiskUtils getInstance(String str, long j, int i) {
        if (com.blankj.utilcode.util.b.C0(str)) {
            str = "cacheUtils";
        }
        return getInstance(new File(Utils.getApp().getCacheDir(), str), j, i);
    }

    public <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Objects.requireNonNull(creator, "Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        byte[] b2 = b("pa_" + str);
        return b2 == null ? t : (T) com.blankj.utilcode.util.b.p(b2, creator);
    }

    public void put(@NonNull String str, String str2) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, str2, -1);
    }

    public static CacheDiskUtils getInstance(@NonNull File file) {
        Objects.requireNonNull(file, "Argument 'cacheDir' of type File (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        return getInstance(file, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public void put(@NonNull String str, String str2, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("st_" + str, com.blankj.utilcode.util.b.Z0(str2), i);
    }

    public static CacheDiskUtils getInstance(@NonNull File file, long j, int i) {
        Objects.requireNonNull(file, "Argument 'cacheDir' of type File (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        String str = file.getAbsoluteFile() + "_" + j + "_" + i;
        Map<String, CacheDiskUtils> map = f;
        CacheDiskUtils cacheDiskUtils = map.get(str);
        if (cacheDiskUtils == null) {
            synchronized (CacheDiskUtils.class) {
                cacheDiskUtils = map.get(str);
                if (cacheDiskUtils == null) {
                    CacheDiskUtils cacheDiskUtils2 = new CacheDiskUtils(str, file, j, i);
                    map.put(str, cacheDiskUtils2);
                    cacheDiskUtils = cacheDiskUtils2;
                }
            }
        }
        return cacheDiskUtils;
    }

    public void put(@NonNull String str, JSONObject jSONObject) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, jSONObject, -1);
    }

    public void put(@NonNull String str, JSONObject jSONObject, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("jo_" + str, com.blankj.utilcode.util.b.F0(jSONObject), i);
    }

    public void put(@NonNull String str, JSONArray jSONArray) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, jSONArray, -1);
    }

    public void put(@NonNull String str, JSONArray jSONArray, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("ja_" + str, com.blankj.utilcode.util.b.E0(jSONArray), i);
    }

    public void put(@NonNull String str, Bitmap bitmap) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, bitmap, -1);
    }

    public void put(@NonNull String str, Bitmap bitmap, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("bi_" + str, com.blankj.utilcode.util.b.f(bitmap), i);
    }

    public void put(@NonNull String str, Drawable drawable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, drawable, -1);
    }

    public void put(@NonNull String str, Drawable drawable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("dr_" + str, com.blankj.utilcode.util.b.y(drawable), i);
    }

    public void put(@NonNull String str, Parcelable parcelable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, parcelable, -1);
    }

    public void put(@NonNull String str, Parcelable parcelable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("pa_" + str, com.blankj.utilcode.util.b.J0(parcelable), i);
    }

    public void put(@NonNull String str, Serializable serializable) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        put(str, serializable, -1);
    }

    public void put(@NonNull String str, Serializable serializable, int i) {
        Objects.requireNonNull(str, "Argument 'key' of type String (#0 out of 3, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        d("se_" + str, com.blankj.utilcode.util.b.W0(serializable), i);
    }
}
