package com.crrepa.g0;

import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static com.crrepa.a0.a f7719a;

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7720a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[CRPWatchFaceLayoutInfo.CompressionType.values().length];
            b = iArr;
            try {
                iArr[CRPWatchFaceLayoutInfo.CompressionType.LZO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[CRPWatchFaceLayoutInfo.CompressionType.RGB_LINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[b.values().length];
            f7720a = iArr2;
            try {
                iArr2[b.ORIGINAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7720a[b.LZO.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7720a[b.AVATAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7720a[b.RGB_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum b {
        ORIGINAL,
        LZO,
        RGB_LINE,
        AVATAR
    }

    public static com.crrepa.a0.a a(b bVar) {
        com.crrepa.a0.a eVar;
        int i = a.f7720a[bVar.ordinal()];
        if (i == 1) {
            eVar = new e();
        } else if (i == 2) {
            eVar = new c();
        } else if (i == 3) {
            eVar = new com.crrepa.b0.a();
        } else if (i != 4) {
            throw new IllegalStateException("BaseWatchFaceBackgroudTransInitiator Unexpected value: " + bVar);
        } else {
            eVar = new com.crrepa.g0.b();
        }
        f7719a = eVar;
        return f7719a;
    }

    public static b a(CRPWatchFaceLayoutInfo.CompressionType compressionType) {
        if (compressionType == null) {
            return b.ORIGINAL;
        }
        int i = a.b[compressionType.ordinal()];
        return i != 1 ? i != 2 ? b.ORIGINAL : b.RGB_LINE : b.LZO;
    }

    public static void a() {
        f7719a = null;
    }

    public static com.crrepa.a0.a b() {
        return f7719a;
    }
}
