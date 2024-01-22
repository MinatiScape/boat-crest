package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes12.dex */
public abstract class tb {
    public static void a(String str, String str2) {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        byte[] bArr = new byte[1024];
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                return;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                new File(str2 + File.separator + name.substring(0, name.length() - 1)).mkdirs();
            } else {
                String str3 = str2 + File.separator + name.substring(name.lastIndexOf(47) + 1);
                TGLogger.d(str3);
                File file2 = new File(str3);
                if (!file2.exists()) {
                    TGLogger.d("Create the file:" + str3);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    int read = zipInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                }
                fileOutputStream.close();
            }
        }
    }
}
