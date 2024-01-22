package com.abupdate.iot_libs.engine;

import android.text.TextUtils;
import com.abupdate.iot_download_libs.CallBackManager;
import com.abupdate.iot_download_libs.DLManager;
import com.abupdate.iot_download_libs.DownConfig;
import com.abupdate.iot_download_libs.DownEntity;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.ProductInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.IDownSimpleListener;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.trace.Trace;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static d f1893a;

    /* loaded from: classes.dex */
    public class a implements Callable<String> {
        public a(d dVar) {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public String call() throws Exception {
            return com.abupdate.iot_libs.a.a.a().b(DeviceInfo.getInstance(), OtaAgentPolicy.sCx);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Callable<String> {
        public b(d dVar) {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public String call() throws Exception {
            return com.abupdate.iot_libs.a.a.a().a(DeviceInfo.getInstance(), OtaAgentPolicy.sCx);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Callable<String> {
        public c(d dVar) {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public String call() throws Exception {
            return com.abupdate.iot_libs.a.a.a().b();
        }
    }

    public static d a() {
        if (f1893a == null) {
            synchronized (d.class) {
                if (f1893a == null) {
                    f1893a = new d();
                }
            }
        }
        return f1893a;
    }

    public String b() throws FotaException {
        if (DeviceInfo.getInstance().isValid()) {
            if (!RegisterInfo.getInstance().isValid() || !ProductInfo.getInstance().isProductValid()) {
                Trace.d("OTAExecuteManager", "check_version_task() start register!");
                int d = d();
                if (1000 != d || !RegisterInfo.getInstance().isValid()) {
                    throw new FotaException(d);
                }
            }
            try {
                return (String) f.a().a(new a(this)).get();
            } catch (InterruptedException | ExecutionException e) {
                Trace.e("OTAExecuteManager", e);
                return "";
            }
        }
        Trace.e("OTAExecuteManager", "check_version_task() device info is invalid!");
        throw new FotaException(3001);
    }

    public int c() {
        try {
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                Trace.e("OTAExecuteManager", "check_version_task() json is null!");
                return 3003;
            }
            int c2 = com.abupdate.iot_libs.utils.e.c(b2);
            if (com.abupdate.iot_libs.utils.e.a(c2)) {
                com.abupdate.iot_libs.utils.a.a(b2);
            }
            if (c2 == 2001 || c2 == 2103) {
                RegisterInfo.getInstance().reset();
            }
            return c2;
        } catch (FotaException e) {
            e.printStackTrace();
            return e.getReasonCode();
        }
    }

    public int d() {
        String str;
        if (!DeviceInfo.getInstance().isValid()) {
            Trace.e("OTAExecuteManager", "register_task() failed. device info is null");
            return 3001;
        } else if (ProductInfo.getInstance().isProductValid() || (com.abupdate.iot_libs.utils.e.a(a().f()) && ProductInfo.getInstance().isProductValid())) {
            try {
                str = (String) f.a().a(new b(this)).get();
            } catch (InterruptedException | ExecutionException e) {
                Trace.e("OTAExecuteManager", e);
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                return 3003;
            }
            int a2 = com.abupdate.iot_libs.utils.e.a(str, OtaAgentPolicy.sCx);
            if (com.abupdate.iot_libs.utils.e.a(a2)) {
                com.abupdate.iot_libs.utils.a.a(str, OtaAgentPolicy.sCx);
                return 1000;
            }
            return a2;
        } else {
            return 3001;
        }
    }

    public DownEntity e() {
        OtaService.startByAction(OtaService.ACTION_REPORT);
        String str = OtaAgentPolicy.config.updatePath + ".temp";
        if (DownConfig.sSegmentDownload && VersionInfo.getInstance().segmentSha != null && VersionInfo.getInstance().segmentSha.size() > 0) {
            return new DownEntity(VersionInfo.getInstance().deltaUrl, str, VersionInfo.getInstance().fileSize, VersionInfo.getInstance().md5sum).setSegmentDownInfo(VersionInfo.getInstance().segmentSha);
        }
        return new DownEntity(VersionInfo.getInstance().deltaUrl, str, VersionInfo.getInstance().fileSize, VersionInfo.getInstance().md5sum);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027 A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int f() {
        /*
            r4 = this;
            com.abupdate.iot_libs.engine.f r0 = com.abupdate.iot_libs.engine.f.a()
            com.abupdate.iot_libs.engine.d$c r1 = new com.abupdate.iot_libs.engine.d$c
            r1.<init>(r4)
            java.util.concurrent.FutureTask r0 = r0.a(r1)
            java.lang.Object r0 = r0.get()     // Catch: java.util.concurrent.ExecutionException -> L14 java.lang.InterruptedException -> L19
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.util.concurrent.ExecutionException -> L14 java.lang.InterruptedException -> L19
            goto L1f
        L14:
            r0 = move-exception
            r0.printStackTrace()
            goto L1d
        L19:
            r0 = move-exception
            r0.printStackTrace()
        L1d:
            java.lang.String r0 = ""
        L1f:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 1009(0x3f1, float:1.414E-42)
            if (r1 == 0) goto L28
            return r2
        L28:
            java.lang.String r1 = "_"
            int r1 = r0.indexOf(r1)
            if (r1 <= 0) goto L45
            com.abupdate.iot_libs.info.ProductInfo r2 = com.abupdate.iot_libs.info.ProductInfo.getInstance()
            r3 = 0
            java.lang.String r3 = r0.substring(r3, r1)
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
            r2.setAndStoreProductInfo(r3, r0)
            r0 = 1000(0x3e8, float:1.401E-42)
            return r0
        L45:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.iot_libs.engine.d.f():int");
    }

    public boolean a(IDownSimpleListener iDownSimpleListener, boolean z) {
        DownEntity e = e();
        CallBackManager.getInstance().setListener(iDownSimpleListener);
        DLManager.getInstance().setCallbackOnUIThread(z);
        if (new File(OtaAgentPolicy.config.updatePath).exists() && com.abupdate.iot_libs.utils.c.a(OtaAgentPolicy.config.updatePath, VersionInfo.getInstance().md5sum)) {
            ReportManager.getInstance(OtaAgentPolicy.sCx).reportDownParamInfo(0, j.a(), "");
            CallBackManager.getInstance().on_success(e);
            return true;
        }
        DLManager.getInstance().add(e);
        long a2 = j.a();
        if (DLManager.getInstance().execute(iDownSimpleListener)) {
            ReportManager.getInstance(OtaAgentPolicy.sCx).reportDownParamInfo(e.download_status, a2, "");
            return e.download_status == 0;
        }
        return false;
    }

    public boolean a(IDownSimpleListener iDownSimpleListener) {
        return a(iDownSimpleListener, false);
    }
}
