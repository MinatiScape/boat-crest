package com.clevertap.android.sdk.inapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.LruCache;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.utils.ImageCache;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInAppNotification implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotification> CREATOR = new a();
    public boolean A;
    public boolean B;
    public boolean C;
    public JSONObject D;
    public String E;
    public int F;
    public ArrayList<CTInAppNotificationMedia> G;
    public String H;
    public String I;
    public char J;
    public boolean K;
    public long L;
    public String M;
    public String N;
    public int O;
    public int P;
    public String Q;
    public boolean R;
    public int S;
    public int T;
    public boolean U;
    public boolean V;
    public c h;
    public String i;
    public JSONObject j;
    public String k;
    public int l;
    public ArrayList<CTInAppNotificationButton> m;
    public String n;
    public JSONObject o;
    public String p;
    public boolean q;
    public String r;
    public boolean s;
    public int t;
    public int u;
    public boolean v;
    public String w;
    public String x;
    public CTInAppType y;
    public boolean z;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInAppNotification> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInAppNotification createFromParcel(Parcel parcel) {
            return new CTInAppNotification(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInAppNotification[] newArray(int i) {
            return new CTInAppNotification[i];
        }
    }

    /* loaded from: classes2.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2620a;

        static {
            int[] iArr = new int[CTInAppType.values().length];
            f2620a = iArr;
            try {
                iArr[CTInAppType.CTInAppTypeFooter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeHeader.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeCover.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeHalfInterstitial.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeCoverImageOnly.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeHalfInterstitialImageOnly.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2620a[CTInAppType.CTInAppTypeInterstitialImageOnly.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface c {
        void notificationReady(CTInAppNotification cTInAppNotification);
    }

    /* loaded from: classes2.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public static final int f2621a;
        public static final int b;
        public static LruCache<String, byte[]> c;

        /* loaded from: classes2.dex */
        public class a extends LruCache<String, byte[]> {
            public a(int i) {
                super(i);
            }

            @Override // android.util.LruCache
            /* renamed from: a */
            public int sizeOf(String str, byte[] bArr) {
                int f = d.f(bArr);
                Logger.v("CTInAppNotification.GifCache: have gif of size: " + f + "KB for key: " + str);
                return f;
            }
        }

        static {
            int maxMemory = ((int) Runtime.getRuntime().maxMemory()) / 1024;
            f2621a = maxMemory;
            b = Math.max(maxMemory / 32, 5120);
        }

        public static boolean b(String str, byte[] bArr) {
            if (c == null) {
                return false;
            }
            if (e(str) == null) {
                synchronized (d.class) {
                    int f = f(bArr);
                    int d = d();
                    Logger.v("CTInAppNotification.GifCache: gif size: " + f + "KB. Available mem: " + d + "KB.");
                    if (f > d()) {
                        Logger.v("CTInAppNotification.GifCache: insufficient memory to add gif: " + str);
                        return false;
                    }
                    c.put(str, bArr);
                    Logger.v("CTInAppNotification.GifCache: added gif for key: " + str);
                    return true;
                }
            }
            return true;
        }

        public static void c() {
            synchronized (d.class) {
                if (h()) {
                    Logger.v("CTInAppNotification.GifCache: cache is empty, removing it");
                    c = null;
                }
            }
        }

        public static int d() {
            int size;
            synchronized (d.class) {
                LruCache<String, byte[]> lruCache = c;
                size = lruCache == null ? 0 : b - lruCache.size();
            }
            return size;
        }

        public static byte[] e(String str) {
            byte[] bArr;
            synchronized (d.class) {
                LruCache<String, byte[]> lruCache = c;
                bArr = lruCache == null ? null : lruCache.get(str);
            }
            return bArr;
        }

        public static int f(byte[] bArr) {
            return bArr.length / 1024;
        }

        public static void g() {
            synchronized (d.class) {
                if (c == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("CTInAppNotification.GifCache: init with max device memory: ");
                    sb.append(f2621a);
                    sb.append("KB and allocated cache size: ");
                    int i = b;
                    sb.append(i);
                    sb.append("KB");
                    Logger.v(sb.toString());
                    c = new a(i);
                }
            }
        }

        public static boolean h() {
            boolean z;
            synchronized (d.class) {
                z = c.size() <= 0;
            }
            return z;
        }

        public static void i(String str) {
            synchronized (d.class) {
                LruCache<String, byte[]> lruCache = c;
                if (lruCache == null) {
                    return;
                }
                lruCache.remove(str);
                Logger.v("CTInAppNotification.GifCache: removed gif for key: " + str);
                c();
            }
        }
    }

    public /* synthetic */ CTInAppNotification(Parcel parcel, a aVar) {
        this(parcel);
    }

    public static Bundle d(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                Object obj = jSONObject.get(next);
                if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                } else if (obj instanceof Character) {
                    bundle.putChar(next, ((Character) obj).charValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(next, ((Float) obj).floatValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof JSONObject) {
                    bundle.putBundle(next, d((JSONObject) obj));
                }
            } catch (JSONException unused) {
                Logger.v("Key had unknown object. Discarding");
            }
        }
        return bundle;
    }

    public void A() {
        Iterator<CTInAppNotificationMedia> it = this.G.iterator();
        while (it.hasNext()) {
            CTInAppNotificationMedia next = it.next();
            if (next.f()) {
                d.g();
                if (i(next) != null) {
                    this.h.notificationReady(this);
                    return;
                } else if (next.c() != null) {
                    Logger.v("CTInAppNotification: downloading GIF :" + next.c());
                    byte[] byteArrayFromImageURL = Utils.getByteArrayFromImageURL(next.c());
                    if (byteArrayFromImageURL != null) {
                        Logger.v("GIF Downloaded from url: " + next.c());
                        if (!d.b(next.a(), byteArrayFromImageURL)) {
                            this.r = "Error processing GIF";
                        }
                    }
                }
            } else if (next.g()) {
                ImageCache.init();
                if (l(next) != null) {
                    this.h.notificationReady(this);
                    return;
                } else if (next.c() != null) {
                    Logger.v("CTInAppNotification: downloading Image :" + next.c());
                    Bitmap bitmapFromURL = Utils.getBitmapFromURL(next.c());
                    if (bitmapFromURL != null) {
                        Logger.v("Image Downloaded from url: " + next.c());
                        if (!ImageCache.addBitmap(next.a(), bitmapFromURL)) {
                            this.r = "Error processing image";
                        }
                    } else {
                        Logger.d("Image Bitmap is null");
                        this.r = "Error processing image as bitmap was NULL";
                    }
                }
            } else if (next.h() || next.e()) {
                if (!this.R) {
                    this.r = "InApp Video/Audio is not supported";
                }
            }
        }
        this.h.notificationReady(this);
    }

    public final void B() {
        Iterator<CTInAppNotificationMedia> it = this.G.iterator();
        while (it.hasNext()) {
            CTInAppNotificationMedia next = it.next();
            if (next.c() != null && next.a() != null) {
                if (!next.b().equals("image/gif")) {
                    ImageCache.removeBitmap(next.a(), false);
                    Logger.v("Deleted image - " + next.a());
                } else {
                    d.i(next.a());
                    Logger.v("Deleted GIF - " + next.a());
                }
            }
        }
    }

    public final boolean C(Bundle bundle) {
        try {
            Bundle bundle2 = bundle.getBundle(Constants.INAPP_WINDOW);
            Bundle bundle3 = bundle.getBundle("d");
            if (bundle2 == null || bundle3 == null || !(w(bundle2, Constants.INAPP_X_DP, Integer.class) || w(bundle2, Constants.INAPP_X_PERCENT, Integer.class))) {
                return false;
            }
            if ((w(bundle2, Constants.INAPP_Y_DP, Integer.class) || w(bundle2, Constants.INAPP_Y_PERCENT, Integer.class)) && w(bundle2, Constants.INAPP_NOTIF_DARKEN_SCREEN, Boolean.class) && w(bundle2, Constants.INAPP_NOTIF_SHOW_CLOSE, Boolean.class) && w(bundle3, Constants.INAPP_HTML_TAG, String.class) && w(bundle2, Constants.INAPP_POSITION, String.class)) {
                char charAt = bundle2.getString(Constants.INAPP_POSITION).charAt(0);
                return charAt == 'b' || charAt == 'c' || charAt == 'l' || charAt == 'r' || charAt == 't';
            }
            return false;
        } catch (Throwable th) {
            Logger.v("Failed to parse in-app notification!", th);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0172 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0194 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01b7 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f9 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0235 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ec A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00f1 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0102 A[Catch: JSONException -> 0x0262, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010e A[Catch: JSONException -> 0x0262, TRY_ENTER, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0132 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x013a A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:3:0x0024, B:6:0x002c, B:8:0x0033, B:10:0x003b, B:12:0x0042, B:14:0x0053, B:18:0x005c, B:20:0x0064, B:24:0x006d, B:26:0x0075, B:30:0x007e, B:32:0x0087, B:34:0x008d, B:36:0x0095, B:37:0x0099, B:39:0x00a9, B:43:0x00b2, B:45:0x00ba, B:47:0x00c1, B:49:0x00c9, B:54:0x00d3, B:56:0x00db, B:60:0x00e4, B:62:0x00ec, B:64:0x00f9, B:66:0x0102, B:71:0x010e, B:73:0x0114, B:75:0x011b, B:77:0x0123, B:79:0x012a, B:80:0x012c, B:82:0x0132, B:85:0x013a, B:87:0x0140, B:88:0x0144, B:90:0x014e, B:92:0x0155, B:93:0x0157, B:95:0x015f, B:99:0x0168, B:101:0x0172, B:104:0x017c, B:106:0x0187, B:107:0x018c, B:109:0x0194, B:112:0x019e, B:114:0x01aa, B:115:0x01af, B:117:0x01b7, B:121:0x01c2, B:123:0x01c8, B:125:0x01d7, B:127:0x01dd, B:128:0x01e7, B:129:0x01ea, B:130:0x01f4, B:132:0x01f9, B:134:0x0201, B:135:0x0207, B:137:0x020d, B:139:0x0219, B:141:0x021f, B:143:0x0225, B:145:0x022b, B:146:0x0230, B:147:0x0235, B:148:0x023b, B:150:0x0241, B:152:0x024d, B:154:0x0253, B:156:0x0259, B:63:0x00f1), top: B:161:0x0024 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(org.json.JSONObject r20) {
        /*
            Method dump skipped, instructions count: 654
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inapp.CTInAppNotification.a(org.json.JSONObject):void");
    }

    public void b() {
        B();
    }

    public String c() {
        return this.k;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        return this.l;
    }

    public JSONObject f() {
        return this.o;
    }

    public boolean fallBackToNotificationSettings() {
        return this.V;
    }

    public String g() {
        return this.p;
    }

    public JSONObject getActionExtras() {
        return this.j;
    }

    public ArrayList<CTInAppNotificationButton> getButtons() {
        return this.m;
    }

    public String getCampaignId() {
        return this.n;
    }

    public int getHeight() {
        return this.t;
    }

    public String getId() {
        return this.x;
    }

    public CTInAppType getInAppType() {
        return this.y;
    }

    public JSONObject getJsonDescription() {
        return this.D;
    }

    public int getMaxPerSession() {
        return this.F;
    }

    public String getMessage() {
        return this.H;
    }

    public long getTimeToLive() {
        return this.L;
    }

    public String getTitle() {
        return this.M;
    }

    public int getTotalDailyCount() {
        return this.O;
    }

    public int getTotalLifetimeCount() {
        return this.P;
    }

    public String getType() {
        return this.Q;
    }

    public int getWidth() {
        return this.S;
    }

    public String h() {
        return this.r;
    }

    public byte[] i(CTInAppNotificationMedia cTInAppNotificationMedia) {
        return d.e(cTInAppNotificationMedia.a());
    }

    public boolean isExcludeFromCaps() {
        return this.s;
    }

    public boolean isLandscape() {
        return this.z;
    }

    public boolean isLocalInApp() {
        return this.U;
    }

    public boolean isPortrait() {
        return this.A;
    }

    public int j() {
        return this.u;
    }

    public String k() {
        return this.w;
    }

    public Bitmap l(CTInAppNotificationMedia cTInAppNotificationMedia) {
        return ImageCache.getBitmap(cTInAppNotificationMedia.a());
    }

    public CTInAppNotificationMedia m(int i) {
        Iterator<CTInAppNotificationMedia> it = this.G.iterator();
        while (it.hasNext()) {
            CTInAppNotificationMedia next = it.next();
            if (i == next.getOrientation()) {
                return next;
            }
        }
        return null;
    }

    public ArrayList<CTInAppNotificationMedia> n() {
        return this.G;
    }

    public String o() {
        return this.I;
    }

    public char p() {
        return this.J;
    }

    public String q() {
        return this.N;
    }

    public int r() {
        return this.T;
    }

    public CTInAppNotification s(JSONObject jSONObject, boolean z) {
        String string;
        this.R = z;
        this.D = jSONObject;
        try {
            string = jSONObject.has("type") ? jSONObject.getString("type") : null;
            this.Q = string;
        } catch (JSONException e) {
            this.r = "Invalid JSON : " + e.getLocalizedMessage();
        }
        if (string != null && !string.equals(Constants.KEY_CUSTOM_HTML)) {
            a(jSONObject);
            return this;
        }
        z(jSONObject);
        return this;
    }

    public boolean t() {
        return this.q;
    }

    public boolean u() {
        return this.v;
    }

    public boolean v() {
        return this.C;
    }

    public final boolean w(Bundle bundle, String str, Class<?> cls) {
        return bundle.containsKey(str) && bundle.get(str).getClass().equals(cls);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.x);
        parcel.writeString(this.n);
        parcel.writeValue(this.y);
        parcel.writeString(this.w);
        parcel.writeByte(this.s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.K ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.F);
        parcel.writeInt(this.P);
        parcel.writeInt(this.O);
        parcel.writeValue(Character.valueOf(this.J));
        parcel.writeInt(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.S);
        parcel.writeInt(this.T);
        if (this.D == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.D.toString());
        }
        parcel.writeString(this.r);
        if (this.o == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.o.toString());
        }
        if (this.j == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeString(this.j.toString());
        }
        parcel.writeString(this.Q);
        parcel.writeString(this.M);
        parcel.writeString(this.N);
        parcel.writeString(this.k);
        parcel.writeString(this.H);
        parcel.writeString(this.I);
        parcel.writeTypedList(this.m);
        parcel.writeTypedList(this.G);
        parcel.writeByte(this.v ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.l);
        parcel.writeByte(this.B ? (byte) 1 : (byte) 0);
        parcel.writeString(this.p);
        parcel.writeByte(this.C ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.A ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.z ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.U ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.V ? (byte) 1 : (byte) 0);
        parcel.writeString(this.E);
        parcel.writeString(this.i);
        parcel.writeLong(this.L);
    }

    public boolean x() {
        return this.K;
    }

    public boolean y() {
        return this.B;
    }

    public final void z(JSONObject jSONObject) {
        long currentTimeMillis;
        if (!C(d(jSONObject))) {
            this.r = "Invalid JSON";
            return;
        }
        try {
            this.x = jSONObject.has(Constants.INAPP_ID_IN_PAYLOAD) ? jSONObject.getString(Constants.INAPP_ID_IN_PAYLOAD) : "";
            this.n = jSONObject.has(Constants.NOTIFICATION_ID_TAG) ? jSONObject.getString(Constants.NOTIFICATION_ID_TAG) : "";
            boolean z = true;
            this.s = jSONObject.has(Constants.KEY_EFC) && jSONObject.getInt(Constants.KEY_EFC) == 1;
            this.P = jSONObject.has(Constants.KEY_TLC) ? jSONObject.getInt(Constants.KEY_TLC) : -1;
            this.O = jSONObject.has(Constants.KEY_TDC) ? jSONObject.getInt(Constants.KEY_TDC) : -1;
            if (!jSONObject.has(Constants.INAPP_JS_ENABLED) || !jSONObject.getBoolean(Constants.INAPP_JS_ENABLED)) {
                z = false;
            }
            this.C = z;
            if (jSONObject.has("wzrk_ttl")) {
                currentTimeMillis = jSONObject.getLong("wzrk_ttl");
            } else {
                currentTimeMillis = (System.currentTimeMillis() + 172800000) / 1000;
            }
            this.L = currentTimeMillis;
            JSONObject jSONObject2 = jSONObject.has("d") ? jSONObject.getJSONObject("d") : null;
            if (jSONObject2 != null) {
                this.w = jSONObject2.getString(Constants.INAPP_HTML_TAG);
                this.p = jSONObject2.has("url") ? jSONObject2.getString("url") : "";
                JSONObject jSONObject3 = jSONObject2.has(Constants.KEY_KV) ? jSONObject2.getJSONObject(Constants.KEY_KV) : null;
                this.o = jSONObject3;
                if (jSONObject3 == null) {
                    this.o = new JSONObject();
                }
                JSONObject jSONObject4 = jSONObject.getJSONObject(Constants.INAPP_WINDOW);
                if (jSONObject4 != null) {
                    this.q = jSONObject4.getBoolean(Constants.INAPP_NOTIF_DARKEN_SCREEN);
                    this.K = jSONObject4.getBoolean(Constants.INAPP_NOTIF_SHOW_CLOSE);
                    this.J = jSONObject4.getString(Constants.INAPP_POSITION).charAt(0);
                    this.S = jSONObject4.has(Constants.INAPP_X_DP) ? jSONObject4.getInt(Constants.INAPP_X_DP) : 0;
                    this.T = jSONObject4.has(Constants.INAPP_X_PERCENT) ? jSONObject4.getInt(Constants.INAPP_X_PERCENT) : 0;
                    this.t = jSONObject4.has(Constants.INAPP_Y_DP) ? jSONObject4.getInt(Constants.INAPP_Y_DP) : 0;
                    this.u = jSONObject4.has(Constants.INAPP_Y_PERCENT) ? jSONObject4.getInt(Constants.INAPP_Y_PERCENT) : 0;
                    this.F = jSONObject4.has(Constants.INAPP_MAX_DISPLAY_COUNT) ? jSONObject4.getInt(Constants.INAPP_MAX_DISPLAY_COUNT) : -1;
                }
                if (this.w != null) {
                    char c2 = this.J;
                    if (c2 == 't' && this.T == 100 && this.u <= 30) {
                        this.y = CTInAppType.CTInAppTypeHeaderHTML;
                    } else if (c2 == 'b' && this.T == 100 && this.u <= 30) {
                        this.y = CTInAppType.CTInAppTypeFooterHTML;
                    } else if (c2 == 'c' && this.T == 90 && this.u == 85) {
                        this.y = CTInAppType.CTInAppTypeInterstitialHTML;
                    } else if (c2 == 'c' && this.T == 100 && this.u == 100) {
                        this.y = CTInAppType.CTInAppTypeCoverHTML;
                    } else if (c2 == 'c' && this.T == 90 && this.u == 50) {
                        this.y = CTInAppType.CTInAppTypeHalfInterstitialHTML;
                    }
                }
            }
        } catch (JSONException unused) {
            this.r = "Invalid JSON";
        }
    }

    public CTInAppNotification() {
        this.m = new ArrayList<>();
        this.G = new ArrayList<>();
        this.U = false;
        this.V = false;
    }

    public CTInAppNotification(Parcel parcel) {
        this.m = new ArrayList<>();
        this.G = new ArrayList<>();
        this.U = false;
        this.V = false;
        try {
            this.x = parcel.readString();
            this.n = parcel.readString();
            this.y = (CTInAppType) parcel.readValue(CTInAppType.class.getClassLoader());
            this.w = parcel.readString();
            this.s = parcel.readByte() != 0;
            this.K = parcel.readByte() != 0;
            this.q = parcel.readByte() != 0;
            this.F = parcel.readInt();
            this.P = parcel.readInt();
            this.O = parcel.readInt();
            this.J = ((Character) parcel.readValue(Character.TYPE.getClassLoader())).charValue();
            this.t = parcel.readInt();
            this.u = parcel.readInt();
            this.S = parcel.readInt();
            this.T = parcel.readInt();
            JSONObject jSONObject = null;
            this.D = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            this.r = parcel.readString();
            this.o = parcel.readByte() == 0 ? null : new JSONObject(parcel.readString());
            if (parcel.readByte() != 0) {
                jSONObject = new JSONObject(parcel.readString());
            }
            this.j = jSONObject;
            this.Q = parcel.readString();
            this.M = parcel.readString();
            this.N = parcel.readString();
            this.k = parcel.readString();
            this.H = parcel.readString();
            this.I = parcel.readString();
            try {
                this.m = parcel.createTypedArrayList(CTInAppNotificationButton.CREATOR);
            } catch (Throwable unused) {
            }
            try {
                this.G = parcel.createTypedArrayList(CTInAppNotificationMedia.CREATOR);
            } catch (Throwable unused2) {
            }
            this.v = parcel.readByte() != 0;
            this.l = parcel.readInt();
            this.B = parcel.readByte() != 0;
            this.p = parcel.readString();
            this.C = parcel.readByte() != 0;
            this.A = parcel.readByte() != 0;
            this.z = parcel.readByte() != 0;
            this.U = parcel.readByte() != 0;
            this.V = parcel.readByte() != 0;
            this.E = parcel.readString();
            this.i = parcel.readString();
            this.L = parcel.readLong();
        } catch (JSONException unused3) {
        }
    }
}
