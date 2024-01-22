package com.abupdate.iot_download_libs;

import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.abupdate.trace.Trace;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static String f1882a = "DownUtils";

    /* loaded from: classes.dex */
    public static class a implements Callable {
        public final /* synthetic */ String h;

        public a(String str) {
            this.h = str;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            int i;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.h).openConnection();
                httpURLConnection.setConnectTimeout(DownConfig.CONNECT_TIMEOUT);
                httpURLConnection.setReadTimeout(DownConfig.READ_TIMEOUT);
                i = httpURLConnection.getContentLength();
            } catch (IOException e) {
                e.printStackTrace();
                Trace.e(d.f1882a, e);
                i = -1;
            }
            return Integer.valueOf(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0046 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long a(java.io.File r5, java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5, r6)
            boolean r5 = r0.exists()
            r1 = 0
            if (r5 != 0) goto Le
            return r1
        Le:
            r5 = 0
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L31
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L31
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L31
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L31
            java.lang.String r5 = r6.readLine()     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L43
            long r0 = java.lang.Long.parseLong(r5)     // Catch: java.lang.Exception -> L2a java.lang.Throwable -> L43
            r6.close()     // Catch: java.io.IOException -> L25
            goto L29
        L25:
            r5 = move-exception
            r5.printStackTrace()
        L29:
            return r0
        L2a:
            r5 = move-exception
            goto L35
        L2c:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L44
        L31:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L35:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L43
            if (r6 == 0) goto L42
            r6.close()     // Catch: java.io.IOException -> L3e
            goto L42
        L3e:
            r5 = move-exception
            r5.printStackTrace()
        L42:
            return r1
        L43:
            r5 = move-exception
        L44:
            if (r6 == 0) goto L4e
            r6.close()     // Catch: java.io.IOException -> L4a
            goto L4e
        L4a:
            r6 = move-exception
            r6.printStackTrace()
        L4e:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_download_libs.d.a(java.io.File, java.lang.String):long");
    }

    public static long a(String str) {
        ExecutionException e;
        Integer num;
        InterruptedException e2;
        if (TextUtils.isEmpty(str)) {
            return -1L;
        }
        FutureTask futureTask = new FutureTask(new a(str));
        Executors.newSingleThreadExecutor().execute(futureTask);
        try {
            num = (Integer) futureTask.get();
            try {
                Log.d(f1882a, "fetch_file_size: " + num);
            } catch (InterruptedException e3) {
                e2 = e3;
                e2.printStackTrace();
                return num.intValue();
            } catch (ExecutionException e4) {
                e = e4;
                e.printStackTrace();
                return num.intValue();
            }
        } catch (InterruptedException e5) {
            e2 = e5;
            num = 0;
        } catch (ExecutionException e6) {
            e = e6;
            num = 0;
        }
        return num.intValue();
    }

    public static File a(DownEntity downEntity) {
        return new File(DLManager.getInstance().mCx.getFilesDir(), "temp_folder");
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r10) {
        /*
            java.lang.String r0 = ""
            if (r10 == 0) goto Lb8
            boolean r1 = r10.exists()
            if (r1 != 0) goto Lc
            goto Lb8
        Lc:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L99 java.lang.Exception -> L9b
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L99 java.lang.Exception -> L9b
            r4.<init>(r10)     // Catch: java.lang.Throwable -> L99 java.lang.Exception -> L9b
            r10 = 262144(0x40000, float:3.67342E-40)
            byte[] r10 = new byte[r10]     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            if (r3 != 0) goto L2c
            r4.close()     // Catch: java.io.IOException -> L27
            goto L2b
        L27:
            r10 = move-exception
            r10.printStackTrace()
        L2b:
            return r0
        L2c:
            int r5 = r4.read(r10)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r6 = -1
            r7 = 0
            if (r5 == r6) goto L38
            r3.update(r10, r7, r5)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            goto L2c
        L38:
            byte[] r10 = r3.digest()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            if (r10 != 0) goto L47
            r4.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r10 = move-exception
            r10.printStackTrace()
        L46:
            return r0
        L47:
            r3 = r7
        L48:
            int r5 = r10.length     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            java.lang.String r6 = "0"
            if (r3 >= r5) goto L70
            r5 = r10[r3]     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r5 = r5 & 255(0xff, float:3.57E-43)
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            if (r5 != 0) goto L60
            r4.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r10 = move-exception
            r10.printStackTrace()
        L5f:
            return r0
        L60:
            int r8 = r5.length()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r9 = 1
            if (r8 != r9) goto L6a
            r1.append(r6)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
        L6a:
            r1.append(r5)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            int r3 = r3 + 1
            goto L48
        L70:
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            int r0 = r10.length()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            int r0 = 32 - r0
        L7a:
            if (r7 >= r0) goto L8e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r1.<init>()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r1.append(r6)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            r1.append(r10)     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            java.lang.String r10 = r1.toString()     // Catch: java.lang.Exception -> L97 java.lang.Throwable -> Lab
            int r7 = r7 + 1
            goto L7a
        L8e:
            r4.close()     // Catch: java.io.IOException -> L92
            goto L96
        L92:
            r0 = move-exception
            r0.printStackTrace()
        L96:
            return r10
        L97:
            r10 = move-exception
            goto L9d
        L99:
            r10 = move-exception
            goto Lad
        L9b:
            r10 = move-exception
            r4 = r2
        L9d:
            r10.printStackTrace()     // Catch: java.lang.Throwable -> Lab
            if (r4 == 0) goto Laa
            r4.close()     // Catch: java.io.IOException -> La6
            goto Laa
        La6:
            r10 = move-exception
            r10.printStackTrace()
        Laa:
            return r2
        Lab:
            r10 = move-exception
            r2 = r4
        Lad:
            if (r2 == 0) goto Lb7
            r2.close()     // Catch: java.io.IOException -> Lb3
            goto Lb7
        Lb3:
            r0 = move-exception
            r0.printStackTrace()
        Lb7:
            throw r10
        Lb8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_download_libs.d.a(java.io.File):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0037 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r3, java.lang.String r4, long r5) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3, r4)
            r3 = 0
            java.io.BufferedWriter r4 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L22
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L22
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L22
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L22
            java.lang.String r3 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L34
            r4.write(r3)     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L34
            r4.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L1b:
            r3 = move-exception
            goto L26
        L1d:
            r4 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
            goto L35
        L22:
            r4 = move-exception
            r2 = r4
            r4 = r3
            r3 = r2
        L26:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r4 == 0) goto L33
            r4.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r3 = move-exception
            r3.printStackTrace()
        L33:
            return
        L34:
            r3 = move-exception
        L35:
            if (r4 == 0) goto L3f
            r4.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r4 = move-exception
            r4.printStackTrace()
        L3f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_download_libs.d.a(java.io.File, java.lang.String, long):void");
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

    public static long b(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            int i = Build.VERSION.SDK_INT;
            return (i >= 18 ? statFs.getAvailableBlocksLong() : statFs.getAvailableBlocks()) * (i >= 18 ? statFs.getBlockSizeLong() : statFs.getBlockSize());
        } catch (Exception e) {
            String str2 = f1882a;
            Trace.e(str2, "get_storage_free_size() e = " + e);
            return -1L;
        }
    }

    public static boolean c(String str) {
        if (e(str)) {
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

    public static boolean d(String str) {
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    d(str + MqttTopic.TOPIC_LEVEL_SEPARATOR + listFiles[i].getName());
                } else {
                    listFiles[i].delete();
                }
            }
            return file.delete();
        }
        return false;
    }

    public static boolean e(String str) {
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
}
