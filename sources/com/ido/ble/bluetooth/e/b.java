package com.ido.ble.bluetooth.e;

import com.ido.ble.bluetooth.f.f;
import com.ido.ble.common.c;
import com.ido.ble.logs.LogTool;
import java.io.UnsupportedEncodingException;
/* loaded from: classes11.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12115a = "ScannerServiceParser";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final int g = 6;
    private static final int h = 7;
    private static final int i = 8;
    private static final int j = 9;
    private static final byte k = 1;
    private static final byte l = 2;

    private static int a(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2] & 255) << 0);
    }

    public static String a(byte[] bArr) {
        int i2;
        try {
            int length = bArr.length;
            while (i2 < length) {
                byte b2 = bArr[i2];
                if (b2 == 0) {
                    break;
                }
                int i3 = i2 + 1;
                byte b3 = bArr[i3];
                i2 = (b3 == 9 || b3 == 8) ? 0 : i3 + (b2 - 1) + 1;
                return a(bArr, i3 + 1, b2 - 1);
            }
        } catch (Exception e2) {
            LogTool.b(f12115a, e2.getMessage());
        }
        return null;
    }

    public static String a(byte[] bArr, int i2, int i3) {
        try {
            return new String(bArr, i2, i3, "UTF-8");
        } catch (UnsupportedEncodingException | IndexOutOfBoundsException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0073 A[Catch: Exception -> 0x0088, TRY_LEAVE, TryCatch #0 {Exception -> 0x0088, blocks: (B:5:0x0004, B:8:0x000b, B:14:0x0015, B:58:0x0073, B:27:0x002d, B:28:0x002f, B:31:0x0036, B:36:0x0042, B:37:0x0045, B:38:0x0047, B:41:0x004e, B:46:0x0058, B:47:0x005b, B:48:0x005d, B:51:0x0064, B:56:0x006e), top: B:71:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x007d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(byte[] r12, java.lang.String[] r13) {
        /*
            r0 = 0
            if (r12 != 0) goto L4
            return r0
        L4:
            int r1 = r12.length     // Catch: java.lang.Exception -> L88
            r2 = r0
            r3 = r2
            r4 = r3
        L8:
            r5 = 1
            if (r2 >= r1) goto L82
            r6 = r12[r2]     // Catch: java.lang.Exception -> L88
            if (r6 != 0) goto L15
            if (r3 == 0) goto L14
            if (r4 == 0) goto L14
            r0 = r5
        L14:
            return r0
        L15:
            int r2 = r2 + 1
            r7 = r12[r2]     // Catch: java.lang.Exception -> L88
            r8 = 3
            r9 = 2
            if (r7 == r9) goto L5b
            if (r7 != r8) goto L20
            goto L5b
        L20:
            r9 = 4
            if (r7 == r9) goto L45
            r10 = 5
            if (r7 != r10) goto L27
            goto L45
        L27:
            r9 = 6
            if (r7 == r9) goto L2d
            r9 = 7
            if (r7 != r9) goto L71
        L2d:
            int r9 = r2 + 1
        L2f:
            int r10 = r2 + r6
            int r10 = r10 - r5
            if (r9 >= r10) goto L71
            if (r4 != 0) goto L41
            r4 = 16
            boolean r4 = a(r13, r12, r9, r4)     // Catch: java.lang.Exception -> L88
            if (r4 == 0) goto L3f
            goto L41
        L3f:
            r4 = r0
            goto L42
        L41:
            r4 = r5
        L42:
            int r9 = r9 + 16
            goto L2f
        L45:
            int r10 = r2 + 1
        L47:
            int r11 = r2 + r6
            int r11 = r11 - r5
            if (r10 >= r11) goto L71
            if (r4 != 0) goto L57
            boolean r4 = c(r13, r12, r10, r9)     // Catch: java.lang.Exception -> L88
            if (r4 == 0) goto L55
            goto L57
        L55:
            r4 = r0
            goto L58
        L57:
            r4 = r5
        L58:
            int r10 = r10 + 4
            goto L47
        L5b:
            int r10 = r2 + 1
        L5d:
            int r11 = r2 + r6
            int r11 = r11 - r5
            if (r10 >= r11) goto L71
            if (r4 != 0) goto L6d
            boolean r4 = b(r13, r12, r10, r9)     // Catch: java.lang.Exception -> L88
            if (r4 == 0) goto L6b
            goto L6d
        L6b:
            r4 = r0
            goto L6e
        L6d:
            r4 = r5
        L6e:
            int r10 = r10 + 2
            goto L5d
        L71:
            if (r7 != r5) goto L7d
            int r3 = r2 + 1
            r3 = r12[r3]     // Catch: java.lang.Exception -> L88
            r3 = r3 & r8
            if (r3 <= 0) goto L7c
            r3 = r5
            goto L7d
        L7c:
            r3 = r0
        L7d:
            int r6 = r6 + (-1)
            int r2 = r2 + r6
            int r2 = r2 + r5
            goto L8
        L82:
            if (r3 == 0) goto L87
            if (r4 == 0) goto L87
            r0 = r5
        L87:
            return r0
        L88:
            r12 = move-exception
            java.lang.String r12 = r12.getMessage()
            java.lang.String r13 = "ScannerServiceParser"
            com.ido.ble.logs.LogTool.b(r13, r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.bluetooth.e.b.a(byte[], java.lang.String[]):boolean");
    }

    private static boolean a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.equalsIgnoreCase(str2.substring(4, 8))) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, (i2 + i3) - 4))));
    }

    private static boolean b(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, i2))));
    }

    public static byte[] b(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            byte[] bArr3 = new byte[62];
            int length = bArr.length;
            int i2 = 0;
            while (i2 < length) {
                int i3 = bArr[i2];
                if (i3 == 0) {
                    return null;
                }
                int i4 = i2 + 1;
                if (bArr[i4] == -1) {
                    int i5 = i3 - 1;
                    c.a(bArr, bArr3, i4 + 1, i5);
                    bArr2 = new byte[i5];
                    c.a(bArr3, bArr2, 0, i5);
                    return bArr2;
                }
                i2 = i4 + (i3 - 1) + 1;
            }
            return null;
        } catch (Exception e2) {
            LogTool.b(f12115a, e2.getMessage());
            return bArr2;
        }
    }

    public static boolean c(byte[] bArr) {
        return a(bArr, new String[]{f.d.toString(), f.e.toString()});
    }

    private static boolean c(String[] strArr, byte[] bArr, int i2, int i3) {
        return a(strArr, String.format("%04x", Integer.valueOf(a(bArr, (i2 + i3) - 4))));
    }

    public static boolean d(byte[] bArr) {
        return a(bArr, new String[]{f.m.toString(), f.n.toString()});
    }
}
