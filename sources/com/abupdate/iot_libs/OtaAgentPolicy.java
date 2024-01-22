package com.abupdate.iot_libs;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.abupdate.http_libs.engine.HttpManager;
import com.abupdate.iot_download_libs.DLManager;
import com.abupdate.iot_download_libs.DownConfig;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.constant.SDKConfig;
import com.abupdate.iot_libs.engine.a;
import com.abupdate.iot_libs.engine.b;
import com.abupdate.iot_libs.engine.d;
import com.abupdate.iot_libs.info.CustomDeviceInfo;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.ProductInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.ICheckVersionCallback;
import com.abupdate.iot_libs.inter.IDownSimpleListener;
import com.abupdate.iot_libs.inter.IDownloadListener;
import com.abupdate.iot_libs.inter.IRebootUpgradeCallBack;
import com.abupdate.iot_libs.inter.IRegisterListener;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.service.JobSchedulerService;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.iot_libs.utils.c;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.trace.Trace;
import java.io.File;
/* loaded from: classes.dex */
public class OtaAgentPolicy {
    public static Builder config;
    public static Context sCx;

    /* loaded from: classes.dex */
    public static class Builder {
        public CustomDeviceInfo customDeviceInfo;
        public String mid;
        public String tracePath;
        public String updatePath;
        public boolean showTrace = true;
        public boolean reportLog = true;

        public void commit() throws FotaException {
            CustomDeviceInfo customDeviceInfo;
            boolean z = false;
            if (!(!TextUtils.isEmpty(this.updatePath) && c.b(new File(this.updatePath).getParentFile().getAbsolutePath()))) {
                this.updatePath = OtaAgentPolicy.sCx.getFilesDir() + File.separator + "update.zip";
            }
            if (!TextUtils.isEmpty(this.tracePath) && c.b(new File(this.tracePath).getParentFile().getAbsolutePath())) {
                z = true;
            }
            if (!z) {
                this.tracePath = j.b(OtaAgentPolicy.sCx);
            }
            Trace.setLevel(this.showTrace ? 3 : 7);
            Trace.setLog_path(this.tracePath);
            Trace.setShowPosition(true);
            DownConfig.RETRY_TIMES_MAX = 3;
            if (TextUtils.isEmpty(this.mid) && ((customDeviceInfo = this.customDeviceInfo) == null || TextUtils.isEmpty(customDeviceInfo.mid))) {
                throw new RuntimeException("mid can not be null");
            }
            CustomDeviceInfo customDeviceInfo2 = this.customDeviceInfo;
            if (customDeviceInfo2 != null && !TextUtils.isEmpty(customDeviceInfo2.mid)) {
                DeviceInfo.getInstance().initInfo(this.customDeviceInfo.mid);
            } else {
                DeviceInfo.getInstance().initInfo(this.mid);
            }
            OtaAgentPolicy.b(OtaAgentPolicy.sCx);
        }

        public Builder isReportLog(boolean z) {
            this.reportLog = z;
            return this;
        }

        public Builder setCustomDeviceInfo(CustomDeviceInfo customDeviceInfo) {
            this.customDeviceInfo = customDeviceInfo;
            return this;
        }

        public Builder setMid(String str) {
            this.mid = str;
            return this;
        }

        public Builder setTracePath(String str) {
            Trace.d("OtaAgentPolicy", "setTracePath() path:" + str);
            if (c.b(new File(str).getParentFile().getAbsolutePath())) {
                this.tracePath = str;
            } else {
                Trace.d("OtaAgentPolicy", "setTracePath() path is invalid ! set path fail");
            }
            return this;
        }

        public Builder setUpdatePath(String str) {
            Trace.d("OtaAgentPolicy", "setUpdatePath() :" + str);
            if (c.b(new File(str).getParentFile().getAbsolutePath())) {
                this.updatePath = str;
            } else {
                Trace.e("OtaAgentPolicy", "setUpdatePath() path is invalid ! set path fail");
            }
            return this;
        }

        public Builder showTrace(boolean z) {
            this.showTrace = z;
            return this;
        }
    }

    public static void b(Context context) throws FotaException {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "init Fota", OtaConstants.SINGLE_LINE);
        a.a().a(context);
        SDKConfig.gen();
        d(context);
        if (config.customDeviceInfo == null) {
            DeviceInfo.getInstance().init();
            ProductInfo.getInstance().init();
        } else {
            DeviceInfo deviceInfo = DeviceInfo.getInstance();
            CustomDeviceInfo customDeviceInfo = config.customDeviceInfo;
            deviceInfo.initOtherInfo(customDeviceInfo.version, customDeviceInfo.oem, customDeviceInfo.models, customDeviceInfo.platform, customDeviceInfo.deviceType);
            if (!TextUtils.isEmpty(config.customDeviceInfo.productId) && !TextUtils.isEmpty(config.customDeviceInfo.product_secret)) {
                ProductInfo.getInstance().productSecret = config.customDeviceInfo.product_secret;
                ProductInfo.getInstance().productId = config.customDeviceInfo.productId;
            } else {
                ProductInfo.getInstance().init();
            }
        }
        RegisterInfo.getInstance().init();
        MqttAgentPolicy.initMqtt();
        com.abupdate.iot_libs.utils.a.a();
        HttpManager.build(context).setRedirectTimes(0).setRetryTimes(3).create();
        if (Build.VERSION.SDK_INT >= 21) {
            c();
        }
    }

    @RequiresApi(api = 21)
    public static void c() {
        sCx.startService(new Intent(sCx, JobSchedulerService.class));
        ((JobScheduler) sCx.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(888, new ComponentName(sCx, JobSchedulerService.class)).setPeriodic(86400000L).setRequiredNetworkType(1).setPersisted(true).build());
    }

    public static void checkVersionAsync(ICheckVersionCallback iCheckVersionCallback) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "check version", OtaConstants.SINGLE_LINE);
        com.abupdate.iot_libs.engine.c.a(iCheckVersionCallback);
        OtaService.startByAction(OtaService.ACTION_CHECK_VERSION);
    }

    public static Pair<Integer, VersionInfo> checkVersionExecute() {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "check version", OtaConstants.SINGLE_LINE);
        return new Pair<>(Integer.valueOf(d.a().c()), VersionInfo.getInstance());
    }

    public static boolean d(Context context) {
        if (b.a(context)) {
            return true;
        }
        throw new RuntimeException("AndroidManifest element and permissions is lack");
    }

    public static void downloadAsync(IDownloadListener iDownloadListener) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "downloadEnqueue", OtaConstants.SINGLE_LINE);
        com.abupdate.iot_libs.engine.c.a(iDownloadListener);
        OtaService.startByAction(OtaService.ACTION_DOWNLOAD);
    }

    public static void downloadCancel() {
        Trace.d("OtaAgentPolicy", "downloadCancel().");
        OtaService.setDownloadCancel();
    }

    public static boolean downloadExecute(IDownSimpleListener iDownSimpleListener) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "downloadExecute", OtaConstants.SINGLE_LINE);
        return d.a().a(iDownSimpleListener);
    }

    public static Builder getConfig() {
        return config;
    }

    public static VersionInfo getVersionInfo() {
        return VersionInfo.getInstance();
    }

    public static Builder init(Context context) {
        config = new Builder();
        sCx = context;
        SPFTool.initContext(context);
        OtaService.initContext(sCx);
        DLManager.getInstance().setContext(sCx);
        return config;
    }

    public static void rebootLocalUpgrade(String str, IRebootUpgradeCallBack iRebootUpgradeCallBack) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "reboot local update", OtaConstants.SINGLE_LINE);
        OtaService.startByAction(OtaService.ACTION_UPDATE, str);
        com.abupdate.iot_libs.engine.c.a(iRebootUpgradeCallBack);
    }

    public static void rebootUpgrade(IRebootUpgradeCallBack iRebootUpgradeCallBack) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "reboot update", OtaConstants.SINGLE_LINE);
        OtaService.startByAction(OtaService.ACTION_UPDATE);
        com.abupdate.iot_libs.engine.c.a(iRebootUpgradeCallBack);
    }

    public static void registerAsync(IRegisterListener iRegisterListener) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "register", OtaConstants.SINGLE_LINE);
        com.abupdate.iot_libs.engine.c.a(iRegisterListener);
        OtaService.startByAction(OtaService.ACTION_REGISTER);
    }

    public static boolean downloadExecute(IDownSimpleListener iDownSimpleListener, boolean z) {
        Trace.d("OtaAgentPolicy", "%s%s%s", OtaConstants.SINGLE_LINE, "downloadExecute", OtaConstants.SINGLE_LINE);
        return d.a().a(iDownSimpleListener, z);
    }
}
