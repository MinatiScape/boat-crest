package com.abupdate.iot_libs.engine;

import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.ErrorFileParamInfo;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.trace.Trace;
import com.jieli.watchtesttool.tool.upgrade.OTAManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
/* loaded from: classes.dex */
public class LogManager {
    public static final String FILE_KEY = "uploadFile";
    public static final int TASK_REPORT_RECOVERY_LOG = 1;
    public static final int TASK_REPORT_TRACE_LOG = 2;
    public static LogManager b;

    /* renamed from: a  reason: collision with root package name */
    public String f1889a = OtaAgentPolicy.sCx.getFilesDir() + "/Log";

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;

        public a(LogManager logManager, String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            File zipRecoveryLog = LogManager.getInstance().zipRecoveryLog();
            if (zipRecoveryLog == null || !zipRecoveryLog.exists() || zipRecoveryLog.length() <= 0) {
                return;
            }
            ReportManager.getInstance(OtaAgentPolicy.sCx).saveErrorFileData(new ErrorFileParamInfo(DeviceInfo.getInstance().mid, this.h, String.valueOf(1), zipRecoveryLog.getAbsolutePath()));
            Trace.d("LogManager", "saveRecoveryLog() finish");
        }
    }

    public static LogManager getInstance() {
        if (b == null) {
            synchronized (LogManager.class) {
                if (b == null) {
                    b = new LogManager();
                }
            }
        }
        return b;
    }

    public final void a() {
        com.abupdate.iot_libs.report.b db = ReportManager.getInstance(OtaAgentPolicy.sCx).getDB();
        List<ErrorFileParamInfo> d = db.d();
        if (d.size() > 5) {
            for (ErrorFileParamInfo errorFileParamInfo : d) {
                new File(errorFileParamInfo.uploadFile).delete();
                db.a(errorFileParamInfo);
            }
        }
    }

    public String formatTime(long j) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Long.valueOf(j));
    }

    public String getLogName(String str, Long l) {
        return str + "@" + l + ".txt";
    }

    public void report() {
        OtaService.startByAction(OtaService.ACTION_REPORT);
    }

    public void saveRecoveryLog(String str) {
        if (OtaAgentPolicy.getConfig().reportLog) {
            a();
            f.a().a(new a(this, str));
        }
    }

    public void saveTraceLog() {
        File zipTraceLog;
        if (OtaAgentPolicy.getConfig().reportLog && (zipTraceLog = zipTraceLog(OtaAgentPolicy.getConfig().tracePath)) != null && zipTraceLog.exists() && zipTraceLog.length() != 0) {
            saveTraceLog(zipTraceLog);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File zipRecoveryLog() {
        /*
            r10 = this;
            java.lang.String r0 = "key_last_recovery_time"
            r1 = -1
            long r3 = com.abupdate.iot_libs.utils.SPFTool.getLong(r0, r1)
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r1 = 0
            if (r0 != 0) goto Le
            return r1
        Le:
            java.io.File r0 = new java.io.File
            java.lang.String r2 = r10.f1889a
            r0.<init>(r2)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L24
            java.lang.String r0 = r10.f1889a
            boolean r0 = com.abupdate.iot_libs.utils.c.b(r0)
            if (r0 != 0) goto L24
            return r1
        L24:
            android.content.Context r0 = com.abupdate.iot_libs.OtaAgentPolicy.sCx
            java.lang.String r2 = "dropbox"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.os.DropBoxManager r0 = (android.os.DropBoxManager) r0
            java.lang.String r2 = "SYSTEM_RECOVERY_LOG"
            android.os.DropBoxManager$Entry r3 = r0.getNextEntry(r2, r3)
            if (r3 == 0) goto Lb4
            boolean r0 = r0.isTagEnabled(r2)
            if (r0 == 0) goto Lb4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r10.f1889a
            r0.append(r2)
            java.lang.String r2 = java.io.File.separator
            r0.append(r2)
            long r4 = r3.getTimeMillis()
            java.lang.String r2 = r10.formatTime(r4)
            r0.append(r2)
            java.lang.String r2 = ".zip"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r2 = 0
            r4 = 1
            java.io.InputStream r5 = r3.getInputStream()     // Catch: java.lang.Throwable -> L99 java.io.IOException -> L9b
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r7 = r3.getTag()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            long r8 = r3.getTimeMillis()     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.Long r3 = java.lang.Long.valueOf(r8)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            java.lang.String r3 = r10.getLogName(r7, r3)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            boolean r3 = com.abupdate.iot_libs.utils.c.a(r5, r6, r3)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            if (r3 != 0) goto L8a
            if (r5 == 0) goto L89
            java.io.Closeable[] r0 = new java.io.Closeable[r4]
            r0[r2] = r5
            com.abupdate.iot_libs.utils.c.a(r0)
        L89:
            return r1
        L8a:
            if (r5 == 0) goto Lb6
            java.io.Closeable[] r3 = new java.io.Closeable[r4]
            r3[r2] = r5
            com.abupdate.iot_libs.utils.c.a(r3)
            goto Lb6
        L94:
            r0 = move-exception
            r1 = r5
            goto Laa
        L97:
            r3 = move-exception
            goto L9d
        L99:
            r0 = move-exception
            goto Laa
        L9b:
            r3 = move-exception
            r5 = r1
        L9d:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L94
            if (r5 == 0) goto Lb6
            java.io.Closeable[] r3 = new java.io.Closeable[r4]
            r3[r2] = r5
            com.abupdate.iot_libs.utils.c.a(r3)
            goto Lb6
        Laa:
            if (r1 == 0) goto Lb3
            java.io.Closeable[] r3 = new java.io.Closeable[r4]
            r3[r2] = r1
            com.abupdate.iot_libs.utils.c.a(r3)
        Lb3:
            throw r0
        Lb4:
            java.lang.String r0 = ""
        Lb6:
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            boolean r2 = r2.exists()
            if (r2 == 0) goto Ld5
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            long r2 = r2.length()
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto Ld5
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
        Ld5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_libs.engine.LogManager.zipRecoveryLog():java.io.File");
    }

    public File zipTraceLog(String str) {
        if (new File(str).exists() && new File(str).length() > 0) {
            String str2 = this.f1889a + File.separator + formatTime(System.currentTimeMillis()) + OTAManager.OTA_ZIP_SUFFIX;
            if (!new File(this.f1889a).exists() && !com.abupdate.iot_libs.utils.c.b(this.f1889a)) {
                Trace.e("LogManager", "fota log dir create failed");
                return null;
            }
            try {
                if (com.abupdate.iot_libs.utils.c.a(new File(str), new File(str2))) {
                    return new File(str2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        Trace.e("LogManager", "Trace file not exist!");
        return null;
    }

    public void saveTraceLog(File file) {
        a();
        ReportManager.getInstance(OtaAgentPolicy.sCx).saveErrorFileData(new ErrorFileParamInfo(DeviceInfo.getInstance().mid, String.valueOf(2), file.getAbsolutePath()));
        Trace.d("LogManager", "saveTraceLog() finish");
    }
}
