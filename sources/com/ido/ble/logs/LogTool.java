package com.ido.ble.logs;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.ido.ble.b;
import com.ido.ble.common.e;
import com.ido.ble.common.f;
import com.ido.ble.custom.CustomConfig;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes11.dex */
public class LogTool {
    private static final String i = "yyyyMMdd";
    private static final String j = "yyyy-MM-dd HH:mm:ss.SSSZ";
    private static final String l = "[IDO_BLE_SDK] LogTool";
    private static final int m = 7;
    private static final String n = ".log";
    private Thread c;
    private Lock e;
    private Condition f;
    private LogListener g;
    private Runnable h;
    private static final String k = System.getProperty("line.separator");
    private static LogTool o = new LogTool();
    private static boolean p = false;

    /* renamed from: a  reason: collision with root package name */
    private ConcurrentLinkedQueue<String> f12306a = new ConcurrentLinkedQueue<>();
    private volatile boolean b = false;
    private String d = "";

    /* loaded from: classes11.dex */
    public interface LogListener {
        void onLog(String str);
    }

    private LogTool() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.e = reentrantLock;
        this.f = reentrantLock.newCondition();
        this.h = new Runnable() { // from class: com.ido.ble.logs.LogTool.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                if (!LogTool.this.b()) {
                    Log.e(LogTool.l, "createLogFileDir failed");
                    return;
                }
                LogTool.this.c();
                while (true) {
                    if (LogTool.this.b) {
                        break;
                    }
                    LogTool.this.e.lock();
                    try {
                        try {
                            if (LogTool.this.f12306a.isEmpty()) {
                                LogTool.this.f.await();
                            }
                        } catch (InterruptedException e) {
                            Log.e(LogTool.l, e.getMessage(), e);
                            Thread.currentThread().interrupt();
                            str = "";
                        }
                        if (LogTool.this.b) {
                            break;
                        }
                        str = (String) LogTool.this.f12306a.poll();
                        LogTool.this.e.unlock();
                        LogTool.this.b(str);
                    } finally {
                        LogTool.this.e.unlock();
                    }
                }
                Log.i(LogTool.l, "exit loop ok!");
            }
        };
    }

    private synchronized Date a(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            try {
                time = new SimpleDateFormat(i, Locale.getDefault()).parse(str);
            } catch (ParseException e) {
                Log.e(l, e.getMessage(), e);
            }
        }
        return time;
    }

    private static void a() {
        if (b.b() == null) {
            Log.i(l, "checkPermission..Config.getApplication()==null.");
        } else {
            p = ContextCompat.checkSelfPermission(e.a(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(e.a(), "android.permission.READ_EXTERNAL_STORAGE") == 0;
        }
    }

    public static void a(LogListener logListener) {
        o.g = logListener;
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, str2);
    }

    private synchronized void a(String str, String str2, String str3) {
        if (!p) {
            a();
            return;
        }
        Thread thread = this.c;
        if (thread == null || !thread.isAlive() || this.b) {
            j();
        }
        if (TextUtils.isEmpty(g())) {
            Log.e(l, "getLogPathSdcardDir is null");
            return;
        }
        if (!TextUtils.isEmpty(str2) && str2.length() < 20) {
            for (int length = str2.length(); length < 20; length++) {
                str2 = str2 + HexStringBuilder.DEFAULT_SEPARATOR;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("    [");
        sb.append(h());
        sb.append("]    ");
        sb.append("    [");
        sb.append(str2);
        sb.append("]    ");
        sb.append(str3);
        String str4 = k;
        sb.append(str4);
        String sb2 = sb.toString();
        LogListener logListener = this.g;
        if (logListener != null) {
            logListener.onLog(str3 + str4);
        }
        this.e.lock();
        this.f12306a.add(sb2);
        this.f.signal();
        this.e.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(java.lang.String r7) {
        /*
            r6 = this;
            com.ido.ble.custom.CustomConfig r0 = com.ido.ble.custom.CustomConfig.getConfig()
            boolean r0 = r0.isEnableLog()
            if (r0 != 0) goto Lb
            return
        Lb:
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r6.g()
            r1.append(r2)
            java.lang.String r2 = java.io.File.separator
            r1.append(r2)
            java.lang.String r2 = r6.f()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r1 = r2.exists()
            r3 = 1
            java.lang.String r4 = "[IDO_BLE_SDK] LogTool"
            if (r1 != 0) goto L43
            boolean r1 = r2.createNewFile()     // Catch: java.io.IOException -> L3b
            goto L44
        L3b:
            r1 = move-exception
            java.lang.String r1 = r1.toString()
            android.util.Log.e(r4, r1)
        L43:
            r1 = r3
        L44:
            if (r1 != 0) goto L4c
            java.lang.String r7 = "create log file failed!"
            android.util.Log.e(r4, r7)
            return
        L4c:
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L67
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L67
            r5.<init>(r2, r3)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L67
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L65 java.lang.Exception -> L67
            r1.write(r7)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            r1.close()     // Catch: java.io.IOException -> L5d
            goto L7c
        L5d:
            r7 = move-exception
            goto L75
        L5f:
            r7 = move-exception
            r0 = r1
            goto L7d
        L62:
            r7 = move-exception
            r0 = r1
            goto L68
        L65:
            r7 = move-exception
            goto L7d
        L67:
            r7 = move-exception
        L68:
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L65
            android.util.Log.e(r4, r7)     // Catch: java.lang.Throwable -> L65
            if (r0 == 0) goto L7c
            r0.close()     // Catch: java.io.IOException -> L5d
            goto L7c
        L75:
            java.lang.String r7 = r7.toString()
            android.util.Log.e(r4, r7)
        L7c:
            return
        L7d:
            if (r0 == 0) goto L8b
            r0.close()     // Catch: java.io.IOException -> L83
            goto L8b
        L83:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r4, r0)
        L8b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.ble.logs.LogTool.b(java.lang.String):void");
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
        o.a(ExifInterface.LONGITUDE_EAST, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        File file = new File(g());
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        File[] listFiles;
        File file = new File(this.d);
        if (!file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!file2.isDirectory()) {
                Date e = e();
                if (file2.getName().endsWith(n) && a(file2.getName().replace(n, "")).before(e)) {
                    file2.delete();
                }
            }
        }
    }

    public static void c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
    }

    public static void d() {
        Log.i(l, "destroy...");
        if (p) {
            o.k();
        }
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
        o.a("P", str, str2);
    }

    private Date e() {
        int logSaveDays = CustomConfig.getConfig().getLogSaveDays() <= 0 ? 7 : CustomConfig.getConfig().getLogSaveDays();
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) - logSaveDays);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.v(str, str2);
    }

    private synchronized String f() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(i, Locale.getDefault());
            str = simpleDateFormat.format(time) + n;
        }
        return str;
    }

    public static void f(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, str2);
    }

    private String g() {
        String str;
        if (this.d.equals("") && Environment.getExternalStorageState().equals("mounted")) {
            if (TextUtils.isEmpty(CustomConfig.getConfig().getLogSavePath())) {
                str = f.a() + "Log";
            } else {
                str = CustomConfig.getConfig().getLogSavePath();
            }
            this.d = str;
        }
        return this.d;
    }

    private synchronized String h() {
        String format;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            format = new SimpleDateFormat(j, Locale.getDefault()).format(time);
        }
        return format;
    }

    public static void i() {
        Log.i(l, "init...");
        a();
        if (p) {
            o.j();
        }
    }

    private void j() {
        this.b = false;
        if (this.c == null) {
            this.c = new Thread(this.h);
        }
        if (this.c.isAlive()) {
            return;
        }
        try {
            this.c.start();
        } catch (IllegalThreadStateException unused) {
        }
    }

    private void k() {
        this.b = true;
        Thread thread = this.c;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.e.lock();
        this.f.signal();
        this.e.unlock();
        this.f12306a.clear();
        this.c = null;
    }
}
