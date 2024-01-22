package com.abupdate.iot_libs.report;

import android.content.Context;
import android.text.TextUtils;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.DownParamInfo;
import com.abupdate.iot_libs.info.ErrorFileParamInfo;
import com.abupdate.iot_libs.info.PushMessageInfo;
import com.abupdate.iot_libs.info.UpgradeParamInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.IReportResultCallback;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.e;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.trace.Trace;
import java.io.File;
import java.util.List;
/* loaded from: classes.dex */
public class ReportManager {
    public static String c = "ReportManager";
    public static ReportManager d;

    /* renamed from: a  reason: collision with root package name */
    public final Context f1908a;
    public final com.abupdate.iot_libs.report.b b;

    /* loaded from: classes.dex */
    public class a implements IReportResultCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ DownParamInfo f1909a;

        public a(DownParamInfo downParamInfo) {
            this.f1909a = downParamInfo;
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportFail() {
            Trace.d(ReportManager.c, "onReportFail() report down.");
            ReportManager.this.b.b(this.f1909a);
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportNetFail() {
            Trace.d(ReportManager.c, "onReportNetFail() report down.");
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportSuccess() {
            Trace.d(ReportManager.c, "onReportSuccess() report down.");
            ReportManager.this.b.b(this.f1909a);
        }
    }

    /* loaded from: classes.dex */
    public class b implements IReportResultCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ UpgradeParamInfo f1910a;

        public b(UpgradeParamInfo upgradeParamInfo) {
            this.f1910a = upgradeParamInfo;
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportFail() {
            Trace.d(ReportManager.c, "onReportFail() upgrade.");
            ReportManager.this.b.b(this.f1910a);
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportNetFail() {
            Trace.d(ReportManager.c, "onReportNetFail() upgrade.");
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportSuccess() {
            Trace.d(ReportManager.c, "onReportSuccess() upgrade");
            ReportManager.this.b.b(this.f1910a);
        }
    }

    /* loaded from: classes.dex */
    public class c implements IReportResultCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PushMessageInfo f1911a;

        public c(PushMessageInfo pushMessageInfo) {
            this.f1911a = pushMessageInfo;
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportFail() {
            Trace.d(ReportManager.c, "onReportFail() push");
            ReportManager.this.b.a(this.f1911a);
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportNetFail() {
            Trace.d(ReportManager.c, "onReportNetFail() push");
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportSuccess() {
            Trace.d(ReportManager.c, "onReportSuccess() push");
            ReportManager.this.b.a(this.f1911a);
        }
    }

    /* loaded from: classes.dex */
    public class d implements IReportResultCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ErrorFileParamInfo f1912a;

        public d(ErrorFileParamInfo errorFileParamInfo) {
            this.f1912a = errorFileParamInfo;
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportFail() {
            Trace.d(ReportManager.c, "onReportFail() error log");
            ReportManager.this.b.a(this.f1912a);
            new File(this.f1912a.uploadFile).delete();
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportNetFail() {
            Trace.d(ReportManager.c, "onReportNetFail() error log");
            if (TextUtils.isEmpty(this.f1912a.deltaID)) {
                ReportManager.this.b.a(this.f1912a);
                new File(this.f1912a.uploadFile).delete();
            }
        }

        @Override // com.abupdate.iot_libs.inter.IReportResultCallback
        public void onReportSuccess() {
            Trace.d(ReportManager.c, "onReportSuccess() error log");
            ReportManager.this.b.a(this.f1912a);
            new File(this.f1912a.uploadFile).delete();
        }
    }

    public ReportManager(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f1908a = applicationContext;
        this.b = new com.abupdate.iot_libs.report.b(applicationContext);
    }

    public static ReportManager getInstance(Context context) {
        if (d == null) {
            synchronized (ReportManager.class) {
                if (d == null) {
                    d = new ReportManager(context);
                }
            }
        }
        return d;
    }

    public final void c(ErrorFileParamInfo errorFileParamInfo, IReportResultCallback iReportResultCallback) {
        try {
            String a2 = com.abupdate.iot_libs.a.a.a().a(errorFileParamInfo);
            String str = c;
            Trace.d(str, "reportErrorLog(): " + a2);
            if (TextUtils.isEmpty(a2)) {
                iReportResultCallback.onReportNetFail();
            } else if (e.a(a2)) {
                iReportResultCallback.onReportSuccess();
            } else {
                iReportResultCallback.onReportFail();
            }
        } catch (FotaException e) {
            iReportResultCallback.onReportFail();
            e.printStackTrace();
        }
    }

    public com.abupdate.iot_libs.report.b getDB() {
        return this.b;
    }

    public int queryReport() {
        return this.b.b().size() + this.b.a().size() + this.b.c().size() + this.b.d().size();
    }

    public void report() {
        List<DownParamInfo> a2 = this.b.a();
        int size = a2.size();
        if (size > 0) {
            String str = c;
            Trace.d(str, "check the local report download: " + size);
        }
        for (DownParamInfo downParamInfo : a2) {
            reportDown(downParamInfo, new a(downParamInfo));
        }
        List<UpgradeParamInfo> b2 = this.b.b();
        int size2 = b2.size();
        if (size2 > 0) {
            String str2 = c;
            Trace.d(str2, "check the local report upgrade: " + size2);
        }
        for (UpgradeParamInfo upgradeParamInfo : b2) {
            reportUpgrade(upgradeParamInfo, new b(upgradeParamInfo));
        }
        List<PushMessageInfo> c2 = this.b.c();
        int size3 = c2.size();
        if (size3 > 0) {
            String str3 = c;
            Trace.d(str3, "check push message data:" + size3);
        }
        for (PushMessageInfo pushMessageInfo : c2) {
            reportPushData(pushMessageInfo, new c(pushMessageInfo));
        }
        List<ErrorFileParamInfo> d2 = this.b.d();
        int size4 = d2.size();
        if (size4 > 0) {
            String str4 = c;
            Trace.d(str4, "check error log report data:" + size4);
            for (ErrorFileParamInfo errorFileParamInfo : d2) {
                c(errorFileParamInfo, new d(errorFileParamInfo));
            }
        }
    }

    public void reportDown(DownParamInfo downParamInfo, IReportResultCallback iReportResultCallback) {
        try {
            String a2 = com.abupdate.iot_libs.a.a.a().a(downParamInfo);
            if (TextUtils.isEmpty(a2)) {
                iReportResultCallback.onReportNetFail();
            } else if (e.a(a2)) {
                iReportResultCallback.onReportSuccess();
            } else {
                iReportResultCallback.onReportFail();
            }
        } catch (Exception e) {
            iReportResultCallback.onReportFail();
            e.printStackTrace();
        }
    }

    public void reportDownParamInfo(int i, long j, String str) {
        int i2;
        int i3 = (i == -4 || i == -2) ? 7 : i != -1 ? i != 0 ? 99 : 1 : 8;
        try {
            i2 = (int) new File(OtaAgentPolicy.config.updatePath).length();
        } catch (Exception unused) {
            i2 = 0;
        }
        saveReportData(new DownParamInfo(OtaAgentPolicy.getVersionInfo().deltaID, String.valueOf(i3), j, j.a(), i2, str));
        OtaService.startByAction(OtaService.ACTION_REPORT);
    }

    public void reportPushData(PushMessageInfo pushMessageInfo, IReportResultCallback iReportResultCallback) {
        try {
            String a2 = com.abupdate.iot_libs.a.a.a().a(pushMessageInfo.msgId);
            if (TextUtils.isEmpty(a2)) {
                iReportResultCallback.onReportNetFail();
            } else if (e.a(a2)) {
                iReportResultCallback.onReportSuccess();
            } else {
                iReportResultCallback.onReportFail();
            }
        } catch (Exception e) {
            iReportResultCallback.onReportFail();
            e.printStackTrace();
        }
    }

    public void reportUpdateParamInfo(int i) {
        int i2;
        if (VersionInfo.getInstance().deltaID == null) {
            return;
        }
        switch (i) {
            case 7002:
                i2 = 4;
                break;
            case 7003:
                i2 = 2;
                break;
            case 7004:
                i2 = 3;
                break;
            case 7005:
                i2 = 5;
                break;
            default:
                i2 = 99;
                break;
        }
        saveReportData(new UpgradeParamInfo(DeviceInfo.getInstance().mid, VersionInfo.getInstance().deltaID, String.valueOf(i2)));
        OtaService.startByAction(OtaService.ACTION_REPORT);
    }

    public void reportUpgrade(UpgradeParamInfo upgradeParamInfo, IReportResultCallback iReportResultCallback) {
        try {
            String a2 = com.abupdate.iot_libs.a.a.a().a(upgradeParamInfo);
            if (TextUtils.isEmpty(a2)) {
                iReportResultCallback.onReportNetFail();
            } else if (e.a(a2)) {
                iReportResultCallback.onReportSuccess();
            } else {
                iReportResultCallback.onReportFail();
            }
        } catch (Exception e) {
            iReportResultCallback.onReportFail();
            e.printStackTrace();
        }
    }

    public void saveErrorFileData(ErrorFileParamInfo errorFileParamInfo) {
        this.b.b(errorFileParamInfo);
    }

    public void savePushResponseData(PushMessageInfo pushMessageInfo) {
        this.b.b(pushMessageInfo);
    }

    public void saveReportData(Object obj) {
        if (obj instanceof UpgradeParamInfo) {
            this.b.a((UpgradeParamInfo) obj);
        } else if (obj instanceof DownParamInfo) {
            this.b.a((DownParamInfo) obj);
        }
    }
}
