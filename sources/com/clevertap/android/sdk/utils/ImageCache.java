package com.clevertap.android.sdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.LruCache;
import com.clevertap.android.sdk.Logger;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes2.dex */
public class ImageCache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2688a;
    public static final int b;
    public static LruCache<String, Bitmap> c;
    public static File d;
    public static MessageDigest e;

    /* loaded from: classes2.dex */
    public class a extends LruCache<String, Bitmap> {
        public a(int i) {
            super(i);
        }

        @Override // android.util.LruCache
        /* renamed from: a */
        public int sizeOf(String str, Bitmap bitmap) {
            int g = ImageCache.g(bitmap);
            Logger.v("CleverTap.ImageCache: have image of size: " + g + "KB for key: " + str);
            return g;
        }
    }

    static {
        int maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024;
        f2688a = maxMemory;
        b = Math.max(maxMemory / 32, 20480);
    }

    public static boolean addBitmap(String str, Bitmap bitmap) {
        if (c == null) {
            return false;
        }
        if (e(str) == null) {
            synchronized (ImageCache.class) {
                int g = g(bitmap);
                int d2 = d();
                Logger.v("CleverTap.ImageCache: image size: " + g + "KB. Available mem: " + d2 + "KB.");
                if (g > d()) {
                    Logger.v("CleverTap.ImageCache: insufficient memory to add image: " + str);
                    return false;
                }
                c.put(str, bitmap);
                Logger.v("CleverTap.ImageCache: added image for key: " + str);
                return true;
            }
        }
        return true;
    }

    public static void b() {
        synchronized (ImageCache.class) {
            if (i()) {
                Logger.v("CTInAppNotification.ImageCache: cache is empty, removing it");
                c = null;
            }
        }
    }

    public static Bitmap c(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        if (((options.outHeight * options.outWidth) * 4.0f) / 1024.0f > d()) {
            Logger.v("CleverTap.ImageCache: image too large to decode");
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (decodeFile == null) {
            file.delete();
        }
        return decodeFile;
    }

    public static int d() {
        int size;
        synchronized (ImageCache.class) {
            LruCache<String, Bitmap> lruCache = c;
            size = lruCache == null ? 0 : b - lruCache.size();
        }
        return size;
    }

    public static Bitmap e(String str) {
        LruCache<String, Bitmap> lruCache;
        if (str == null || (lruCache = c) == null) {
            return null;
        }
        return lruCache.get(str);
    }

    public static File f(String str) {
        MessageDigest messageDigest = e;
        if (messageDigest == null) {
            return null;
        }
        byte[] digest = messageDigest.digest(str.getBytes());
        return new File(d, "CT_IMAGE_" + Base64.encodeToString(digest, 10));
    }

    public static int g(Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }

    public static Bitmap getBitmap(String str) {
        synchronized (ImageCache.class) {
            Bitmap bitmap = null;
            if (str != null) {
                LruCache<String, Bitmap> lruCache = c;
                if (lruCache != null) {
                    bitmap = lruCache.get(str);
                }
                return bitmap;
            }
            return null;
        }
    }

    public static Bitmap getOrFetchBitmap(String str) {
        Bitmap bitmap = getBitmap(str);
        if (bitmap == null) {
            File h = h(str);
            if (h != null) {
                Bitmap c2 = c(h);
                addBitmap(str, c2);
                return c2;
            }
            return null;
        }
        return bitmap;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File h(java.lang.String r5) {
        /*
            java.lang.String r0 = "CleverTap.ImageCache: error writing image file"
            java.lang.String r1 = "CleverTap.ImageCache: error closing image output file"
            java.io.File r2 = f(r5)
            if (r2 == 0) goto L10
            boolean r3 = r2.exists()
            if (r3 != 0) goto L64
        L10:
            byte[] r5 = com.clevertap.android.sdk.Utils.getByteArrayFromImageURL(r5)
            if (r5 == 0) goto L64
            if (r2 == 0) goto L64
            int r3 = r5.length
            r4 = 10000000(0x989680, float:1.4012985E-38)
            if (r3 >= r4) goto L64
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L49
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L49
            r4.write(r5)     // Catch: java.lang.Throwable -> L30 java.io.IOException -> L33 java.io.FileNotFoundException -> L35
            r4.close()     // Catch: java.io.IOException -> L2b
            goto L64
        L2b:
            r5 = move-exception
            com.clevertap.android.sdk.Logger.v(r1, r5)
            goto L64
        L30:
            r5 = move-exception
            r3 = r4
            goto L59
        L33:
            r5 = move-exception
            goto L3b
        L35:
            r5 = move-exception
            goto L4b
        L37:
            r5 = move-exception
            goto L59
        L39:
            r5 = move-exception
            r4 = r3
        L3b:
            com.clevertap.android.sdk.Logger.v(r0, r5)     // Catch: java.lang.Throwable -> L30
            if (r4 == 0) goto L48
            r4.close()     // Catch: java.io.IOException -> L44
            goto L48
        L44:
            r5 = move-exception
            com.clevertap.android.sdk.Logger.v(r1, r5)
        L48:
            return r3
        L49:
            r5 = move-exception
            r4 = r3
        L4b:
            com.clevertap.android.sdk.Logger.v(r0, r5)     // Catch: java.lang.Throwable -> L30
            if (r4 == 0) goto L58
            r4.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r5 = move-exception
            com.clevertap.android.sdk.Logger.v(r1, r5)
        L58:
            return r3
        L59:
            if (r3 == 0) goto L63
            r3.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r0 = move-exception
            com.clevertap.android.sdk.Logger.v(r1, r0)
        L63:
            throw r5
        L64:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.utils.ImageCache.h(java.lang.String):java.io.File");
    }

    public static boolean i() {
        boolean z;
        synchronized (ImageCache.class) {
            z = c.size() <= 0;
        }
        return z;
    }

    public static void init() {
        synchronized (ImageCache.class) {
            if (c == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("CleverTap.ImageCache: init with max device memory: ");
                sb.append(f2688a);
                sb.append("KB and allocated cache size: ");
                int i = b;
                sb.append(i);
                sb.append("KB");
                Logger.v(sb.toString());
                c = new a(i);
            }
        }
    }

    public static void initWithPersistence(Context context) {
        synchronized (ImageCache.class) {
            if (d == null) {
                d = context.getDir("CleverTap.Images.", 0);
            }
            if (e == null) {
                try {
                    e = MessageDigest.getInstance("SHA256");
                } catch (NoSuchAlgorithmException unused) {
                    Logger.d("CleverTap.ImageCache: image file system caching unavailable as SHA1 hash function not available on platform");
                }
            }
        }
        init();
    }

    public static void j(String str) {
        File f = f(str);
        if (f == null || !f.exists()) {
            return;
        }
        f.delete();
    }

    public static void removeBitmap(String str, boolean z) {
        synchronized (ImageCache.class) {
            if (z) {
                j(str);
            }
            LruCache<String, Bitmap> lruCache = c;
            if (lruCache == null) {
                return;
            }
            lruCache.remove(str);
            Logger.v("CleverTap.ImageCache: removed image for key: " + str);
            b();
        }
    }
}
