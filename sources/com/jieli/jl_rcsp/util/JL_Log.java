package com.jieli.jl_rcsp.util;

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
    private static boolean f12515a = true;
    private static boolean b = false;
    @SuppressLint({"StaticFieldLeak"})
    private static Context c;
    private static String d;
    @SuppressLint({"StaticFieldLeak"})
    private static SaveLogFileThread f;
    private static ILogOutput i;
    @SuppressLint({"ConstantLocale"})
    private static final SimpleDateFormat e = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.getDefault());
    private static String g = "";
    public static long FILE_SIZE_LIMIT = 314572800;
    private static boolean h = false;

    /* loaded from: classes11.dex */
    public interface ILogOutput {
        void output(String str);
    }

    /* loaded from: classes11.dex */
    public static class SaveLogFileThread extends Thread {

        /* renamed from: a  reason: collision with root package name */
        private final LinkedBlockingQueue<byte[]> f12516a;
        private final Context b;
        private volatile boolean c;
        private volatile boolean d;
        private long e;
        private FileOutputStream f;

        public SaveLogFileThread(Context context) {
            super("SaveLogFileThread");
            this.f12516a = new LinkedBlockingQueue<>();
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
                java.util.concurrent.LinkedBlockingQueue<byte[]> r0 = r1.f12516a     // Catch: java.lang.InterruptedException -> L9
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
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.jl_rcsp.util.JL_Log.SaveLogFileThread.addLog(byte[]):void");
        }

        public synchronized void closeSaveFile() {
            this.d = false;
            a();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            a(this.b);
            synchronized (this.f12516a) {
                while (this.d) {
                    if (this.f12516a.isEmpty()) {
                        this.c = true;
                        try {
                            this.f12516a.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        this.c = false;
                        byte[] poll = this.f12516a.poll();
                        if (poll != null && (fileOutputStream = this.f) != null) {
                            try {
                                fileOutputStream.write(poll);
                                this.e += poll.length;
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            if (this.e >= JL_Log.FILE_SIZE_LIMIT) {
                                try {
                                    this.f.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                                a(this.b);
                            }
                        }
                    }
                }
            }
            this.d = false;
            this.c = false;
            this.f12516a.clear();
            FileOutputStream fileOutputStream2 = this.f;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            SaveLogFileThread unused = JL_Log.f = null;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.e = 0L;
            this.d = this.b != null;
            super.start();
        }

        private void a() {
            if (this.c) {
                synchronized (this.f12516a) {
                    this.f12516a.notify();
                }
            }
        }

        private void a(Context context) {
            if (context == null) {
                return;
            }
            if (TextUtils.isEmpty(JL_Log.d)) {
                String unused = JL_Log.d = JL_Log.getSaveLogPath(context);
            }
            try {
                this.f = new FileOutputStream(JL_Log.d + MqttTopic.TOPIC_LEVEL_SEPARATOR + JL_Log.b() + JL_Log.c() + ".txt", true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addLogOutput(String str) {
        if (!b || str == null) {
            return;
        }
        if (f == null) {
            a(c);
            SystemClock.sleep(20L);
        }
        f.addLog(str.getBytes());
    }

    public static /* synthetic */ String b() {
        return f();
    }

    public static /* synthetic */ String c() {
        return e();
    }

    public static void configureLog(Context context, boolean z, boolean z2) {
        f12515a = z;
        b = z2;
        if (z2) {
            c = context;
            CrashHandler.getInstance().init(c);
            a(context);
            return;
        }
        c = null;
        d();
    }

    public static void d(String str, String str2) {
        String b2 = b(str);
        if (f12515a) {
            if (h) {
                TestLog.d(b2, str2);
            } else {
                Log.d(b2, str2);
            }
        }
        b("d", b2, str2);
    }

    public static void e(String str, String str2) {
        String b2 = b(str);
        if (f12515a) {
            if (h) {
                TestLog.e(b2, str2);
            } else {
                Log.e(b2, str2);
            }
        }
        b(RsaJsonWebKey.EXPONENT_MEMBER_NAME, b2, str2);
    }

    private static String f() {
        if (TextUtils.isEmpty(g)) {
            return "app_log_";
        }
        return g + "_log_";
    }

    public static boolean getSaveLogFile() {
        return b;
    }

    public static String getSaveLogPath(Context context) {
        return a(context, "logcat");
    }

    public static void i(String str, String str2) {
        String b2 = b(str);
        if (f12515a) {
            if (h) {
                TestLog.i(b2, str2);
            } else {
                Log.i(b2, str2);
            }
        }
        b("i", b2, str2);
    }

    public static boolean isLog() {
        return f12515a;
    }

    public static void setLogOutput(ILogOutput iLogOutput) {
        i = iLogOutput;
    }

    public static void setTagPrefix(String str) {
        g = str;
    }

    public static void setUseTest(boolean z) {
        h = z;
    }

    public static void v(String str, String str2) {
        String b2 = b(str);
        if (f12515a) {
            if (h) {
                TestLog.v(b2, str2);
            } else {
                Log.v(b2, str2);
            }
        }
    }

    public static void w(String str, String str2) {
        String b2 = b(str);
        if (f12515a) {
            if (h) {
                TestLog.w(b2, str2);
            } else {
                Log.w(b2, str2);
            }
        }
        b(Constants.INAPP_WINDOW, b2, str2);
    }

    private static String b(String str) {
        return g + ":" + str;
    }

    private static void b(String str, String str2, String str3) {
        String a2 = a(str, str2, str3);
        ILogOutput iLogOutput = i;
        if (iLogOutput != null) {
            iLogOutput.output(a2);
        }
        addLogOutput(a2);
    }

    private static void a(Context context) {
        SaveLogFileThread saveLogFileThread = f;
        if (saveLogFileThread == null || !saveLogFileThread.d) {
            SaveLogFileThread saveLogFileThread2 = new SaveLogFileThread(context);
            f = saveLogFileThread2;
            saveLogFileThread2.start();
        }
    }

    private static String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(e());
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

    private static void d() {
        SaveLogFileThread saveLogFileThread = f;
        if (saveLogFileThread != null) {
            saveLogFileThread.closeSaveFile();
            f = null;
        }
    }

    private static String e() {
        return e.format(Calendar.getInstance().getTime());
    }

    private static String a(Context context, String... strArr) {
        File externalFilesDir;
        if (context == null || strArr == null || strArr.length == 0 || (externalFilesDir = context.getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return null;
        }
        StringBuilder sb = new StringBuilder(externalFilesDir.getPath());
        int i2 = 0;
        if (sb.toString().endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR)));
        }
        int length = strArr.length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str = strArr[i2];
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(str);
            File file = new File(sb.toString());
            if ((!file.exists() || file.isFile()) && !file.mkdir()) {
                w("jieli", "create dir failed. filePath = " + ((Object) sb));
                break;
            }
            i2++;
        }
        return sb.toString();
    }
}
