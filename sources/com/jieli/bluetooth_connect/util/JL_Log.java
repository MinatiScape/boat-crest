package com.jieli.bluetooth_connect.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes11.dex */
public class JL_Log {
    private static String SAVE_LOG_PATH = null;
    private static final String TAG_PREFIX = "bluetooth:";
    private static boolean isLog = true;
    private static boolean isSaveLogFile = false;
    private static ILogOutput logOutput;
    @SuppressLint({"StaticFieldLeak"})
    private static Context mContext;
    private static SaveLogFileThread mSaveLogFileThread;
    @SuppressLint({"ConstantLocale"})
    private static final SimpleDateFormat yyyyMMdd_HHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmss.SSS", Locale.getDefault());
    public static int LOG_FILE_SIZE_LIMIT = 62914560;

    /* loaded from: classes11.dex */
    public interface ILogOutput {
        void output(String str);
    }

    /* loaded from: classes11.dex */
    public static class SaveLogFileThread extends Thread {
        private long fileSize;
        private volatile boolean isSaving;
        private volatile boolean isWaiting;
        private final Context mContext;
        private FileOutputStream mLogFileOutputStream;
        private final LinkedBlockingQueue<byte[]> mQueue;

        public SaveLogFileThread(Context context) {
            super("SaveLogFileThread");
            this.mQueue = new LinkedBlockingQueue<>();
            this.mContext = context;
        }

        private void createFile(Context context) {
            if (context == null) {
                return;
            }
            if (TextUtils.isEmpty(JL_Log.SAVE_LOG_PATH)) {
                String unused = JL_Log.SAVE_LOG_PATH = JL_Log.getSaveLogPath(context);
            }
            try {
                this.mLogFileOutputStream = new FileOutputStream(JL_Log.SAVE_LOG_PATH + "/bluetooth_log_app_" + JL_Log.access$300() + ".txt", true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        private void wakeupSaveThread() {
            if (this.isWaiting) {
                synchronized (this.mQueue) {
                    this.mQueue.notify();
                }
            }
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
                java.util.concurrent.LinkedBlockingQueue<byte[]> r0 = r1.mQueue     // Catch: java.lang.InterruptedException -> L9
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
                r1.wakeupSaveThread()
            L13:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.util.JL_Log.SaveLogFileThread.addLog(byte[]):void");
        }

        public synchronized void closeSaveFile() {
            this.isSaving = false;
            wakeupSaveThread();
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
            r7.mLogFileOutputStream.close();
         */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r7 = this;
                android.content.Context r0 = r7.mContext
                r7.createFile(r0)
                java.util.concurrent.LinkedBlockingQueue<byte[]> r0 = r7.mQueue
                monitor-enter(r0)
            L8:
                boolean r1 = r7.isSaving     // Catch: java.lang.Throwable -> L77
                r2 = 0
                if (r1 == 0) goto L5a
                java.util.concurrent.LinkedBlockingQueue<byte[]> r1 = r7.mQueue     // Catch: java.lang.Throwable -> L77
                boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L77
                if (r1 == 0) goto L23
                r1 = 1
                r7.isWaiting = r1     // Catch: java.lang.Throwable -> L77
                java.util.concurrent.LinkedBlockingQueue<byte[]> r1 = r7.mQueue     // Catch: java.lang.InterruptedException -> L1e java.lang.Throwable -> L77
                r1.wait()     // Catch: java.lang.InterruptedException -> L1e java.lang.Throwable -> L77
                goto L8
            L1e:
                r1 = move-exception
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L77
                goto L8
            L23:
                r7.isWaiting = r2     // Catch: java.lang.Throwable -> L77
                java.util.concurrent.LinkedBlockingQueue<byte[]> r1 = r7.mQueue     // Catch: java.lang.Throwable -> L77
                java.lang.Object r1 = r1.poll()     // Catch: java.lang.Throwable -> L77
                byte[] r1 = (byte[]) r1     // Catch: java.lang.Throwable -> L77
                if (r1 == 0) goto L8
                java.io.FileOutputStream r3 = r7.mLogFileOutputStream     // Catch: java.lang.Throwable -> L77
                if (r3 == 0) goto L8
                r3.write(r1)     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L77
                long r3 = r7.fileSize     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L77
                int r1 = r1.length     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L77
                long r5 = (long) r1     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L77
                long r3 = r3 + r5
                r7.fileSize = r3     // Catch: java.io.IOException -> L3e java.lang.Throwable -> L77
                goto L42
            L3e:
                r1 = move-exception
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L77
            L42:
                long r3 = r7.fileSize     // Catch: java.lang.Throwable -> L77
                int r1 = com.jieli.bluetooth_connect.util.JL_Log.LOG_FILE_SIZE_LIMIT     // Catch: java.lang.Throwable -> L77
                long r5 = (long) r1
                int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r1 < 0) goto L8
                java.io.FileOutputStream r1 = r7.mLogFileOutputStream     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L77
                r1.close()     // Catch: java.io.IOException -> L51 java.lang.Throwable -> L77
                goto L55
            L51:
                r1 = move-exception
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L77
            L55:
                android.content.Context r1 = r7.mContext     // Catch: java.lang.Throwable -> L77
                r7.createFile(r1)     // Catch: java.lang.Throwable -> L77
            L5a:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
                r7.isSaving = r2
                r7.isWaiting = r2
                java.util.concurrent.LinkedBlockingQueue<byte[]> r0 = r7.mQueue
                r0.clear()
                java.io.FileOutputStream r0 = r7.mLogFileOutputStream
                r1 = 0
                if (r0 == 0) goto L73
                r0.close()     // Catch: java.io.IOException -> L6d
                goto L71
            L6d:
                r0 = move-exception
                r0.printStackTrace()
            L71:
                r7.mLogFileOutputStream = r1
            L73:
                com.jieli.bluetooth_connect.util.JL_Log.access$102(r1)
                return
            L77:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jieli.bluetooth_connect.util.JL_Log.SaveLogFileThread.run():void");
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.fileSize = 0L;
            this.isSaving = this.mContext != null;
            super.start();
        }
    }

    public static /* synthetic */ String access$300() {
        return currentTimeString();
    }

    public static void addLogOutput(String str) {
        if (isSaveLogFile) {
            if (mSaveLogFileThread == null) {
                openLogFileStream(mContext);
                SystemClock.sleep(20L);
            }
            mSaveLogFileThread.addLog(str.getBytes());
        }
    }

    private static void closeLogFile() {
        SaveLogFileThread saveLogFileThread = mSaveLogFileThread;
        if (saveLogFileThread != null) {
            saveLogFileThread.closeSaveFile();
            mSaveLogFileThread = null;
        }
    }

    public static String createFilePath(Context context, String... strArr) {
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

    private static String currentTimeString() {
        return yyyyMMdd_HHmmssSSS.format(Calendar.getInstance().getTime());
    }

    public static void d(String str, String str2) {
        String formatTag = formatTag(str);
        if (isLog) {
            Log.d(formatTag, str2);
        }
        saveLogInFile("d", formatTag, str2);
    }

    public static void e(String str, String str2) {
        String formatTag = formatTag(str);
        if (isLog) {
            Log.e(formatTag, str2);
        }
        saveLogInFile(RsaJsonWebKey.EXPONENT_MEMBER_NAME, formatTag, str2);
    }

    private static String formatLog(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeString());
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

    private static String formatTag(String str) {
        return TAG_PREFIX + str;
    }

    public static boolean getSaveLogFile() {
        return isSaveLogFile;
    }

    public static String getSaveLogPath(Context context) {
        return createFilePath(context, context.getPackageName(), "logcat");
    }

    public static void i(String str, String str2) {
        String formatTag = formatTag(str);
        if (isLog) {
            Log.i(formatTag, str2);
        }
        saveLogInFile("i", formatTag, str2);
    }

    public static boolean isLog() {
        return isLog;
    }

    private static void openLogFileStream(Context context) {
        SaveLogFileThread saveLogFileThread = mSaveLogFileThread;
        if (saveLogFileThread == null || !saveLogFileThread.isSaving) {
            SaveLogFileThread saveLogFileThread2 = new SaveLogFileThread(context);
            mSaveLogFileThread = saveLogFileThread2;
            saveLogFileThread2.start();
        }
    }

    private static void saveLogInFile(String str, String str2, String str3) {
        String formatLog = formatLog(str, str2, str3);
        ILogOutput iLogOutput = logOutput;
        if (iLogOutput != null) {
            iLogOutput.output(formatLog);
        } else {
            addLogOutput(formatLog);
        }
    }

    public static void setIsSaveLogFile(boolean z, Context context) {
        isSaveLogFile = z;
        if (z) {
            mContext = context;
            openLogFileStream(context);
            return;
        }
        mContext = null;
        closeLogFile();
    }

    public static void setLog(boolean z) {
        isLog = z;
    }

    public static void setLogOutput(ILogOutput iLogOutput) {
        logOutput = iLogOutput;
    }

    public static void v(String str, String str2) {
        String formatTag = formatTag(str);
        if (isLog) {
            Log.v(formatTag, str2);
        }
        saveLogInFile(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, formatTag, str2);
    }

    public static void w(String str, String str2) {
        String formatTag = formatTag(str);
        if (isLog) {
            Log.w(formatTag, str2);
        }
        saveLogInFile(Constants.INAPP_WINDOW, formatTag, str2);
    }
}
