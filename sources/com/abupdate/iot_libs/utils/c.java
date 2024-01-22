package com.abupdate.iot_libs.utils;

import android.text.TextUtils;
import com.abupdate.trace.Trace;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/* loaded from: classes.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:76:0x00c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r9) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "MD5"
            java.security.MessageDigest r2 = java.security.MessageDigest.getInstance(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            r9 = 262144(0x40000, float:3.67342E-40)
            byte[] r9 = new byte[r9]     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            if (r2 != 0) goto L23
            r3.close()     // Catch: java.io.IOException -> L1e
            goto L22
        L1e:
            r9 = move-exception
            r9.printStackTrace()
        L22:
            return r1
        L23:
            int r4 = r3.read(r9)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r5 = -1
            r6 = 0
            if (r4 == r5) goto L2f
            r2.update(r9, r6, r4)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            goto L23
        L2f:
            byte[] r9 = r2.digest()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            if (r9 != 0) goto L3e
            r3.close()     // Catch: java.io.IOException -> L39
            goto L3d
        L39:
            r9 = move-exception
            r9.printStackTrace()
        L3d:
            return r1
        L3e:
            r2 = r6
        L3f:
            int r4 = r9.length     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            java.lang.String r5 = "0"
            if (r2 >= r4) goto L67
            r4 = r9[r2]     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r4 = r4 & 255(0xff, float:3.57E-43)
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            if (r4 == 0) goto L5e
            int r7 = r4.length()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r8 = 1
            if (r7 != r8) goto L58
            r0.append(r5)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
        L58:
            r0.append(r4)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            int r2 = r2 + 1
            goto L3f
        L5e:
            r3.close()     // Catch: java.io.IOException -> L62
            goto L66
        L62:
            r9 = move-exception
            r9.printStackTrace()
        L66:
            return r1
        L67:
            java.lang.String r9 = r0.toString()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            int r0 = r9.length()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            int r0 = 32 - r0
        L71:
            if (r6 >= r0) goto L85
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r2.<init>()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r2.append(r5)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            r2.append(r9)     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Exception -> L8e java.lang.Throwable -> Lbc
            int r6 = r6 + 1
            goto L71
        L85:
            r3.close()     // Catch: java.io.IOException -> L89
            goto L8d
        L89:
            r0 = move-exception
            r0.printStackTrace()
        L8d:
            return r9
        L8e:
            r9 = move-exception
            goto L94
        L90:
            r9 = move-exception
            goto Lbe
        L92:
            r9 = move-exception
            r3 = r1
        L94:
            java.lang.String r0 = "FileUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbc
            r2.<init>()     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r4 = "getFileMD5, Exception "
            r2.append(r4)     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r4 = r9.toString()     // Catch: java.lang.Throwable -> Lbc
            r2.append(r4)     // Catch: java.lang.Throwable -> Lbc
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lbc
            com.abupdate.trace.Trace.d(r0, r2)     // Catch: java.lang.Throwable -> Lbc
            r9.printStackTrace()     // Catch: java.lang.Throwable -> Lbc
            if (r3 == 0) goto Lbb
            r3.close()     // Catch: java.io.IOException -> Lb7
            goto Lbb
        Lb7:
            r9 = move-exception
            r9.printStackTrace()
        Lbb:
            return r1
        Lbc:
            r9 = move-exception
            r1 = r3
        Lbe:
            if (r1 == 0) goto Lc8
            r1.close()     // Catch: java.io.IOException -> Lc4
            goto Lc8
        Lc4:
            r0 = move-exception
            r0.printStackTrace()
        Lc8:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_libs.utils.c.a(java.io.File):java.lang.String");
    }

    public static boolean b(String str) {
        if (c(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            if (!file.isDirectory()) {
                return false;
            }
        } else if (!file.mkdirs()) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean b(String str, String str2) {
        return new File(str).renameTo(new File(str2));
    }

    public static boolean b(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(c(str) ? "" : File.separator);
        sb.append(file.getName());
        String sb2 = sb.toString();
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (!b(file2, sb2, zipOutputStream, str2)) {
                        return false;
                    }
                }
            } else {
                ZipEntry zipEntry = new ZipEntry(sb2 + '/');
                zipEntry.setComment(str2);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.closeEntry();
            }
        } else {
            BufferedInputStream bufferedInputStream = null;
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    ZipEntry zipEntry2 = new ZipEntry(sb2);
                    zipEntry2.setComment(str2);
                    zipOutputStream.putNextEntry(zipEntry2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.closeEntry();
                    a(bufferedInputStream2);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    a(bufferedInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return true;
    }

    public static String a(String str) {
        File file = new File(str);
        return file.exists() ? a(file) : "";
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String a2 = a(str);
        Trace.i("FileUtil", "validateFile() " + str2.equals(a2) + "md5_file = " + a2 + " md5_net = " + str2);
        return str2.equals(a2);
    }

    public static void a(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean a(File file, File file2) throws IOException {
        return a(file, file2, (String) null);
    }

    public static boolean a(InputStream inputStream, File file, String str) throws IOException {
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
                zipOutputStream.putNextEntry(new ZipEntry(str));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                zipOutputStream.closeEntry();
                a(bufferedInputStream2);
                return file.exists();
            } catch (Throwable th) {
                th = th;
                bufferedInputStream = bufferedInputStream2;
                a(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean a(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(file2));
            try {
                boolean b = b(file, "", zipOutputStream2, str);
                a(zipOutputStream2);
                return b;
            } catch (Throwable th) {
                th = th;
                zipOutputStream = zipOutputStream2;
                if (zipOutputStream != null) {
                    a(zipOutputStream);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
