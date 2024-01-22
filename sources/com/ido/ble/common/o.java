package com.ido.ble.common;

import android.text.TextUtils;
import android.util.Log;
import com.ido.ble.logs.LogTool;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public class o {
    private static final int b = 4096;

    /* renamed from: a  reason: collision with root package name */
    private a f12156a;

    /* loaded from: classes11.dex */
    public interface a {
        void onFinish();
    }

    private static void a(String str, File file, ZipOutputStream zipOutputStream) {
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                listFiles[i].getAbsolutePath().indexOf(file.getAbsolutePath());
                a(str, listFiles[i], zipOutputStream);
            }
            return;
        }
        byte[] bArr = new byte[1024];
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.indexOf(str) != -1) {
            absolutePath = absolutePath.substring(str.length() + File.separator.length());
        }
        zipOutputStream.putNextEntry(new ZipEntry(absolutePath));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        while (true) {
            int read = bufferedInputStream.read(bArr, 0, 1024);
            if (read == -1) {
                bufferedInputStream.close();
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, read);
        }
    }

    public static void a(String str, String str2, String str3) {
        File file;
        String str4;
        ZipOutputStream zipOutputStream;
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            throw new Exception("para is empty");
        }
        ZipOutputStream zipOutputStream2 = null;
        try {
            try {
                file = new File(str);
                if (file.isDirectory() && str2.indexOf(str) != -1) {
                    throw new Exception("zipPath must not be the child directory of srcPath.");
                }
                File file2 = new File(str2);
                if (!file2.exists() || !file2.isDirectory()) {
                    file2.mkdirs();
                }
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                str4 = File.separator;
                sb.append(str4);
                sb.append(str3);
                String sb2 = sb.toString();
                File file3 = new File(sb2);
                if (file3.exists()) {
                    new SecurityManager().checkDelete(sb2);
                    file3.delete();
                }
                zipOutputStream = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(file3), new CRC32()));
            } catch (Throwable th) {
                th = th;
            }
            try {
                if (file.isFile() && (lastIndexOf = str.lastIndexOf(str4)) != -1) {
                    str = str.substring(0, lastIndexOf);
                }
                a(str, file, zipOutputStream);
                zipOutputStream.flush();
                try {
                    zipOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
            } catch (Throwable th2) {
                th = th2;
                zipOutputStream2 = zipOutputStream;
                if (zipOutputStream2 != null) {
                    try {
                        zipOutputStream2.close();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            throw e4;
        }
    }

    private static void a(String str, String str2, ZipOutputStream zipOutputStream) {
        Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");
        if (zipOutputStream == null) {
            return;
        }
        File file = new File(str + str2);
        if (!file.isFile()) {
            String[] list = file.list();
            if (list.length <= 0) {
                zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
                zipOutputStream.closeEntry();
            }
            for (int i = 0; i < list.length; i++) {
                a(str, str2 + File.separator + list[i], zipOutputStream);
            }
            return;
        }
        ZipEntry zipEntry = new ZipEntry(str2);
        FileInputStream fileInputStream = new FileInputStream(file);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                zipOutputStream.closeEntry();
                return;
            }
            zipOutputStream.write(bArr, 0, read);
        }
    }

    public static boolean a(String str) {
        try {
            Enumeration<? extends ZipEntry> entries = new ZipFile(str).entries();
            while (entries.hasMoreElements()) {
                if (entries.nextElement().getName().contains("../")) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str, String str2) {
        try {
            ZipFile zipFile = new ZipFile(str);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry nextElement = entries.nextElement();
                String replace = nextElement.getName().replace("../", "");
                if (nextElement.isDirectory()) {
                    new File(str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + replace).mkdirs();
                } else {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                    File file = new File(str2 + MqttTopic.TOPIC_LEVEL_SEPARATOR + replace);
                    String canonicalPath = new File(str2).getCanonicalPath();
                    String canonicalPath2 = file.getCanonicalPath();
                    if (!canonicalPath2.startsWith(canonicalPath)) {
                        LogTool.d("ZipUtil", "unzip error!  unzipPath:" + str2);
                        LogTool.d("ZipUtil", "unzip error!  cononicalpahtPrent:" + canonicalPath);
                        LogTool.d("ZipUtil", "unzip error!  canoicalPath:" + canonicalPath2);
                        return false;
                    }
                    File parentFile = file.getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 4096);
                        if (read == -1) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    l.a((Closeable) bufferedInputStream);
                    l.a(fileOutputStream);
                    l.a(bufferedOutputStream);
                }
            }
            l.a(zipFile);
            return true;
        } catch (Exception e) {
            Log.e("ZipUtil", "unzip error! zip file:" + str + " unzip to path:" + str2);
            e.printStackTrace();
            return false;
        }
    }

    public void a(a aVar) {
        this.f12156a = aVar;
    }

    public void a(String str, String str2) {
        Log.v("XZip", "ZipFolder(String, String)");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
        File file = new File(str);
        a(file.getParent() + File.separator, file.getName(), zipOutputStream);
        zipOutputStream.finish();
        zipOutputStream.close();
        this.f12156a.onFinish();
    }
}
