package com.elvishew.xlog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
/* loaded from: classes9.dex */
public class LogUtils {
    public static String addBorder(String[] strArr) {
        XLog.a();
        return XLog.b.borderFormatter.format(strArr);
    }

    public static void compress(String str, String str2) throws IOException {
        String[] list;
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File file2 = new File(str2);
            if (!file2.exists()) {
                File parentFile = file2.getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    throw new IOException("Zip folder " + parentFile.getAbsolutePath() + " not created");
                } else if (!file2.createNewFile()) {
                    throw new IOException("Zip file " + str2 + " not created");
                }
            }
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
            try {
                byte[] bArr = new byte[8192];
                for (String str3 : file.list()) {
                    if (!str3.equals(".") && !str3.equals("..")) {
                        File file3 = new File(file, str3);
                        if (file3.isFile()) {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file3), 8192);
                            zipOutputStream.putNextEntry(new ZipEntry(str3));
                            while (true) {
                                int read = bufferedInputStream.read(bArr, 0, 8192);
                                if (read != -1) {
                                    zipOutputStream.write(bArr, 0, read);
                                } else {
                                    try {
                                        break;
                                    } catch (IOException unused) {
                                    }
                                }
                            }
                            bufferedInputStream.close();
                        }
                    }
                }
                try {
                    return;
                } catch (IOException unused2) {
                    return;
                }
            } finally {
                try {
                    zipOutputStream.close();
                } catch (IOException unused3) {
                }
            }
        }
        throw new IOException("Folder " + str + " does't exist or isn't a directory");
    }

    public static String formatJson(String str) {
        XLog.a();
        return XLog.b.jsonFormatter.format(str);
    }

    public static String formatStackTrace(StackTraceElement[] stackTraceElementArr) {
        XLog.a();
        return XLog.b.stackTraceFormatter.format(stackTraceElementArr);
    }

    public static String formatThread(Thread thread) {
        XLog.a();
        return XLog.b.threadFormatter.format(thread);
    }

    public static String formatThrowable(Throwable th) {
        XLog.a();
        return XLog.b.throwableFormatter.format(th);
    }

    public static String formatXml(String str) {
        XLog.a();
        return XLog.b.xmlFormatter.format(str);
    }
}
