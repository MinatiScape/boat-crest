package com.ido.ble.firmware.log;

import java.text.SimpleDateFormat;
/* loaded from: classes11.dex */
class g {

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleDateFormat f12282a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String a(String str) {
        return a(str, c.b, c.c);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            boolean r6 = r0.exists()
            if (r6 != 0) goto Le
            r0.mkdirs()
        Le:
            java.io.File r6 = new java.io.File
            r6.<init>(r0, r7)
            r7 = 0
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r2 = 1
            r1.<init>(r6, r2)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L67 java.io.IOException -> L69
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r1.<init>()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r2.<init>()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r3 = "["
            r2.append(r3)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.text.SimpleDateFormat r3 = com.ido.ble.firmware.log.g.f12282a     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.util.Date r4 = new java.util.Date     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r4.<init>()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r3 = r3.format(r4)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r2.append(r3)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r3 = "]"
            r2.append(r3)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r2.append(r5)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r5 = r2.toString()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r1.append(r5)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r5 = r1.toString()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r0.write(r5)     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r0.newLine()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r0.flush()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            java.lang.String r5 = r6.getAbsolutePath()     // Catch: java.io.IOException -> L65 java.lang.Throwable -> L79
            r0.close()     // Catch: java.io.IOException -> L60
            goto L64
        L60:
            r6 = move-exception
            r6.printStackTrace()
        L64:
            return r5
        L65:
            r5 = move-exception
            goto L6b
        L67:
            r5 = move-exception
            goto L7b
        L69:
            r5 = move-exception
            r0 = r7
        L6b:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L79
            if (r0 == 0) goto L78
            r0.close()     // Catch: java.io.IOException -> L74
            goto L78
        L74:
            r5 = move-exception
            r5.printStackTrace()
        L78:
            return r7
        L79:
            r5 = move-exception
            r7 = r0
        L7b:
            if (r7 == 0) goto L85
            r7.close()     // Catch: java.io.IOException -> L81
            goto L85
        L81:
            r6 = move-exception
            r6.printStackTrace()
        L85:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.firmware.log.g.a(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}
