package com.htsmart.wristband2.dfu;

import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public class m {
    public static List<File> a(File file, File file2) throws IOException {
        return a(file, file2, (String) null);
    }

    public static List<File> a(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            if (str != null && !str.isEmpty()) {
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    String replace = nextElement.getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
                    if (replace.contains("../")) {
                        Log.e("ZipUtils", "entryName: " + replace + " is dangerous!");
                    } else if (replace.contains(str) && !c(file2, arrayList, zipFile, nextElement, replace)) {
                        zipFile.close();
                        return arrayList;
                    }
                }
                zipFile.close();
                return arrayList;
            }
            while (entries.hasMoreElements()) {
                ZipEntry nextElement2 = entries.nextElement();
                String replace2 = nextElement2.getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
                if (replace2.contains("../")) {
                    Log.e("ZipUtils", "entryName: " + replace2 + " is dangerous!");
                } else if (!c(file2, arrayList, zipFile, nextElement2, replace2)) {
                    zipFile.close();
                    return arrayList;
                }
            }
            zipFile.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static List<File> a(String str, String str2) throws IOException {
        return a(str, str2, (String) null);
    }

    public static List<File> a(String str, String str2, String str3) throws IOException {
        return a(d(str), d(str2), str3);
    }

    public static boolean a(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean a(String str) {
        return a(d(str));
    }

    public static boolean a(Collection<File> collection, File file) throws IOException {
        return a(collection, file, (String) null);
    }

    public static boolean a(Collection<File> collection, File file, String str) throws IOException {
        if (collection == null || file == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        try {
            for (File file2 : collection) {
                if (!b(file2, "", zipOutputStream, str)) {
                    zipOutputStream.close();
                    return false;
                }
            }
            zipOutputStream.close();
            return true;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static boolean a(Collection<String> collection, String str) throws IOException {
        return a(collection, str, (String) null);
    }

    public static boolean a(Collection<String> collection, String str, String str2) throws IOException {
        if (collection == null || str == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
        try {
            for (String str3 : collection) {
                if (!b(d(str3), "", zipOutputStream, str2)) {
                    zipOutputStream.close();
                    return false;
                }
            }
            zipOutputStream.close();
            return true;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static boolean b(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (a(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean b(File file, File file2) throws IOException {
        return b(file, file2, (String) null);
    }

    public static boolean b(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return false;
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
        try {
            boolean b = b(file, "", zipOutputStream, str);
            zipOutputStream.close();
            return b;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static boolean b(File file, String str, ZipOutputStream zipOutputStream, String str2) throws IOException {
        if (file == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.isEmpty() ? "" : File.separator);
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
                return true;
            }
            ZipEntry zipEntry = new ZipEntry(sb2 + '/');
            zipEntry.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.closeEntry();
            return true;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        try {
            ZipEntry zipEntry2 = new ZipEntry(sb2);
            zipEntry2.setComment(str2);
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 8192);
                if (read == -1) {
                    zipOutputStream.closeEntry();
                    bufferedInputStream.close();
                    return true;
                }
                zipOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public static boolean b(String str) {
        return b(d(str));
    }

    public static boolean b(String str, String str2) throws IOException {
        return b(d(str), d(str2), (String) null);
    }

    public static boolean b(String str, String str2, String str3) throws IOException {
        return b(d(str), d(str2), str3);
    }

    public static List<String> c(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(entries.nextElement().getComment());
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> c(String str) throws IOException {
        return c(d(str));
    }

    public static boolean c(File file, List<File> list, ZipFile zipFile, ZipEntry zipEntry, String str) throws IOException {
        File file2 = new File(file, str);
        list.add(file2);
        if (zipEntry.isDirectory()) {
            return a(file2);
        }
        if (!b(file2)) {
            return false;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    return true;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
        } finally {
        }
    }

    public static File d(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new File(str);
    }

    public static List<String> d(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String replace = entries.nextElement().getName().replace("\\", MqttTopic.TOPIC_LEVEL_SEPARATOR);
            if (replace.contains("../")) {
                Log.e("ZipUtils", "entryName: " + replace + " is dangerous!");
            }
            arrayList.add(replace);
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> e(String str) throws IOException {
        return d(d(str));
    }
}
