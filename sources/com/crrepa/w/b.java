package com.crrepa.w;

import com.crrepa.i0.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes9.dex */
public class b {
    public static File a(File file) {
        if (file.exists()) {
            String name = file.getName();
            File file2 = new File(file.getParentFile().getPath(), name.substring(0, name.lastIndexOf(46)));
            if (!file2.exists()) {
                file2.mkdirs();
            }
            try {
                ZipFile zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    String name2 = nextElement.getName();
                    InputStream inputStream = zipFile.getInputStream(nextElement);
                    File file3 = new File(file2, name2);
                    if (!file3.getParentFile().exists()) {
                        file3.getParentFile().mkdirs();
                    }
                    if (!file3.isDirectory()) {
                        if (!file3.getCanonicalPath().startsWith(file2.getCanonicalPath())) {
                            c.b("unzip SecurityException");
                            return null;
                        }
                        b(inputStream, file3);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file2;
        }
        return null;
    }

    public static void b(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                inputStream.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(bArr, 0, read);
        }
    }
}
