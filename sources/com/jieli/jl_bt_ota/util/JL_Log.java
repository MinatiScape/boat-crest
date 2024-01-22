package com.jieli.jl_bt_ota.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes11.dex */
public class JL_Log {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12404a = true;
    private static boolean b = false;
    private static String c = null;
    @SuppressLint({"StaticFieldLeak"})
    private static SaveLogFileThread e = null;
    @SuppressLint({"StaticFieldLeak"})
    private static Context f = null;
    private static ILogOutput g = null;
    private static final String h = "ota:";
    @SuppressLint({"ConstantLocale"})
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.getDefault());
    public static boolean isTest = false;
    public static int LOG_FILE_SIZE_MAX = 314572800;

    /* loaded from: classes11.dex */
    public interface ILogOutput {
        void output(String str);
    }

    /* loaded from: classes11.dex */
    public static class SaveLogFileThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private final LinkedBlockingQueue<byte[]> f12405a;
        private final Context b;
        private volatile boolean c;
        private volatile boolean d;
        private long e;
        private FileOutputStream f;

        public SaveLogFileThread(Context context) {
            super("SaveLogFileThread");
            this.f12405a = new LinkedBlockingQueue<>();
            this.b = context;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0010  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void addLog(byte[] r2) {
            /*
                r1 = this;
                if (r2 == 0) goto Ld
                java.util.concurrent.LinkedBlockingQueue<byte[]> r0 = r1.f12405a     // Catch: java.lang.InterruptedException -> L9
                r0.put(r2)     // Catch: java.lang.InterruptedException -> L9
                r2 = 1
                goto Le
            L9:
                r2 = move-exception
                r2.printStackTrace()
            Ld:
                r2 = 0
            Le:
                if (r2 == 0) goto L13
                r1.a()
            L13:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_bt_ota.util.JL_Log.SaveLogFileThread.addLog(byte[]):void");
        }

        public synchronized void closeSaveFile() {
            this.d = false;
            a();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            this.d = a(this.b);
            synchronized (this.f12405a) {
                while (this.d) {
                    if (this.f12405a.isEmpty()) {
                        this.c = true;
                        try {
                            this.f12405a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.c = false;
                        byte[] poll = this.f12405a.poll();
                        if (poll != null && (fileOutputStream = this.f) != null) {
                            try {
                                fileOutputStream.write(poll);
                                this.e += poll.length;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            if (this.e >= JL_Log.LOG_FILE_SIZE_MAX) {
                                try {
                                    this.f.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                this.d = a(this.b);
                            }
                        }
                    }
                }
            }
            this.d = false;
            this.c = false;
            this.f12405a.clear();
            FileOutputStream fileOutputStream2 = this.f;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            SaveLogFileThread unused = JL_Log.e = null;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.e = 0L;
            this.d = true;
            super.start();
        }

        private void a() {
            if (this.c) {
                synchronized (this.f12405a) {
                    this.f12405a.notify();
                }
            }
        }

        private boolean a(Context context) {
            if (context == null) {
                return false;
            }
            if (TextUtils.isEmpty(JL_Log.c)) {
                String unused = JL_Log.c = JL_Log.b(context, "logcat");
            }
            try {
                this.f = new FileOutputStream(JL_Log.c + "/ota_log_app_" + JL_Log.b() + ".txt", true);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void addLogOutput(String str) {
        if (b) {
            if (e == null) {
                a(f);
                SystemClock.sleep(20L);
            }
            SaveLogFileThread saveLogFileThread = e;
            if (saveLogFileThread != null) {
                saveLogFileThread.addLog(str.getBytes());
            }
        }
    }

    public static /* synthetic */ String b() {
        return d();
    }

    private static void c(String str) {
        System.out.println(str);
    }

    public static void d(String str, String str2) {
        String b2 = b(str);
        if (isTest) {
            c(String.format(Locale.getDefault(), "%s : %s", b2, str2));
            return;
        }
        if (f12404a) {
            Log.d(b2, str2);
        }
        b("d", b2, str2);
    }

    public static void e(String str, String str2) {
        String b2 = b(str);
        if (isTest) {
            c(String.format(Locale.getDefault(), "%s : %s", b2, str2));
            return;
        }
        if (f12404a) {
            Log.e(b2, str2);
        }
        b(RsaJsonWebKey.EXPONENT_MEMBER_NAME, b2, str2);
    }

    public static boolean getSaveLogFile() {
        return b;
    }

    public static void i(String str, String str2) {
        String b2 = b(str);
        if (isTest) {
            c(String.format(Locale.getDefault(), "%s : %s", b2, str2));
            return;
        }
        if (f12404a) {
            Log.i(b2, str2);
        }
        b("i", b2, str2);
    }

    public static boolean isIsLog() {
        return f12404a;
    }

    public static void setIsSaveLogFile(Context context, boolean z) {
        b = z;
        if (z) {
            a(context);
        } else {
            c();
        }
    }

    public static void setIsTest(boolean z) {
        isTest = z;
    }

    public static void setLog(boolean z) {
        f12404a = z;
    }

    public static void setLogOutput(ILogOutput iLogOutput) {
        g = iLogOutput;
    }

    public static void w(String str, String str2) {
        String b2 = b(str);
        if (isTest) {
            c(String.format(Locale.getDefault(), "%s : %s", b2, str2));
            return;
        }
        if (f12404a) {
            Log.w(b2, str2);
        }
        b(Constants.INAPP_WINDOW, b2, str2);
    }

    private static String b(String str) {
        return h + str;
    }

    private static void c() {
        SaveLogFileThread saveLogFileThread = e;
        if (saveLogFileThread != null) {
            saveLogFileThread.closeSaveFile();
            e = null;
        }
        f = null;
    }

    private static void b(String str, String str2, String str3) {
        String a2 = a(str, str2, str3);
        ILogOutput iLogOutput = g;
        if (iLogOutput != null) {
            iLogOutput.output(a2);
        } else {
            addLogOutput(a2);
        }
    }

    private static void a(Context context) {
        SaveLogFileThread saveLogFileThread = e;
        if (saveLogFileThread == null || !saveLogFileThread.d) {
            if (f == null) {
                if (context == null) {
                    context = CommonUtil.getMainContext();
                }
                f = context;
            }
            SaveLogFileThread saveLogFileThread2 = new SaveLogFileThread(f);
            e = saveLogFileThread2;
            saveLogFileThread2.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, String... strArr) {
        File externalFilesDir;
        if (context == null || strArr == null || strArr.length == 0 || (externalFilesDir = context.getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(externalFilesDir.getPath());
        int i = 0;
        if (sb.toString().endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR)));
        }
        int length = strArr.length;
        while (true) {
            if (i >= length) {
                break;
            }
            String str = strArr[i];
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(str);
            File file = new File(sb.toString());
            if ((!file.exists() || file.isFile()) && !file.mkdir()) {
                w("jieli", "create dir failed. filePath = " + ((Object) sb));
                break;
            }
            i++;
        }
        return sb.toString();
    }

    private static String d() {
        return d.format(Calendar.getInstance().getTime());
    }

    private static String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(d());
        sb.append("   ");
        sb.append(str);
        sb.append("   ");
        if (str2 == null) {
            str2 = "null";
        }
        sb.append(str2);
        sb.append(" :  ");
        if (str3 == null) {
            str3 = "null";
        }
        sb.append(str3);
        sb.append("\n");
        return sb.toString();
    }
}
