package com.crrepa.i0;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes9.dex */
public class j {
    public static File a(File file, String str) throws IOException {
        if (file != null && file.exists() && !TextUtils.isEmpty(str)) {
            if (file.isFile()) {
                return b(str, file);
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return null;
            }
            for (File file2 : listFiles) {
                if (!file2.isDirectory()) {
                    return b(str, file2);
                }
                a(file2, str);
            }
        }
        return null;
    }

    public static String a(byte[] bArr, File file) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bArr == null) {
            return null;
        }
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            return file.getAbsolutePath();
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return file.getAbsolutePath();
    }

    public static void a(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                a(file2);
            }
            c(file2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0044, code lost:
        if (r1 == null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(java.lang.String r3) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r3 = r0.exists()
            if (r3 != 0) goto L14
            return r1
        L14:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L37 java.io.FileNotFoundException -> L3f
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L37 java.io.FileNotFoundException -> L3f
            int r0 = r3.available()     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2f
            byte[] r1 = new byte[r0]     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2f
            r3.read(r1)     // Catch: java.lang.Throwable -> L28 java.io.IOException -> L2a java.io.FileNotFoundException -> L2f
            r3.close()     // Catch: java.io.IOException -> L26
            goto L52
        L26:
            r3 = move-exception
            goto L4d
        L28:
            r0 = move-exception
            goto L54
        L2a:
            r0 = move-exception
            r2 = r1
            r1 = r3
            r3 = r2
            goto L39
        L2f:
            r0 = move-exception
            r2 = r1
            r1 = r3
            r3 = r2
            goto L41
        L34:
            r3 = move-exception
            r0 = r3
            goto L53
        L37:
            r0 = move-exception
            r3 = r1
        L39:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L51
            goto L46
        L3f:
            r0 = move-exception
            r3 = r1
        L41:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L51
        L46:
            r1.close()     // Catch: java.io.IOException -> L4a
            goto L51
        L4a:
            r0 = move-exception
            r1 = r3
            r3 = r0
        L4d:
            r3.printStackTrace()
            goto L52
        L51:
            r1 = r3
        L52:
            return r1
        L53:
            r3 = r1
        L54:
            if (r3 == 0) goto L5e
            r3.close()     // Catch: java.io.IOException -> L5a
            goto L5e
        L5a:
            r3 = move-exception
            r3.printStackTrace()
        L5e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.i0.j.a(java.lang.String):byte[]");
    }

    @Nullable
    public static File b(String str, File file) {
        if (k.a(str, file)) {
            return file;
        }
        return null;
    }

    public static void c(File file) {
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
