package com.abupdate.iot_libs.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RecoverySystem;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.abupdate.iot_download_libs.DLManager;
import com.abupdate.iot_libs.MqttAgentPolicy;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.a.b;
import com.abupdate.iot_libs.engine.d;
import com.abupdate.iot_libs.engine.f;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.info.VersionInfo;
import com.abupdate.iot_libs.inter.IDownSimpleListener;
import com.abupdate.iot_libs.inter.ILogoutCallback;
import com.abupdate.iot_libs.inter.OtaListener;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.iot_libs.utils.e;
import com.abupdate.iot_libs.utils.g;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.mqtt_libs.connect.MqttManager;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.trace.Trace;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
/* loaded from: classes.dex */
public class OtaService extends IntentService {
    public static final String ACTION_CHECK_VERSION = "action_check_version";
    public static final String ACTION_CONNECT = "action_connect";
    public static final String ACTION_DISCONNECT = "action_disconnect";
    public static final String ACTION_DOWNLOAD = "action_download";
    public static final String ACTION_REGISTER = "action_register";
    public static final String ACTION_REPORT = "action_report";
    public static final String ACTION_STATIC_CHECK_VERSION = "action_static_check_version";
    public static final String ACTION_UPDATE = "action_update";
    public static final String TAG = "OtaService";
    public static Context i = null;
    public static long j = 0;
    public static String k = "";
    public Handler h;

    /* loaded from: classes.dex */
    public class a extends IDownSimpleListener {
        public a() {
        }

        @Override // com.abupdate.iot_libs.inter.IDownSimpleListener
        public void onCancel() {
            com.abupdate.iot_libs.engine.c.a().a(3, 0L, 0L, 0);
        }

        @Override // com.abupdate.iot_libs.inter.IDownSimpleListener
        public void onCompleted(File file) {
            ReportManager.getInstance(OtaService.i).reportDownParamInfo(0, OtaService.j, OtaService.k);
            com.abupdate.iot_libs.engine.c.a().a(4, 0L, 0L, 0);
        }

        @Override // com.abupdate.iot_libs.inter.IDownSimpleListener
        public void onDownloadProgress(long j, long j2, int i) {
            com.abupdate.iot_libs.engine.c.a().a(2, j, j2, 0);
        }

        @Override // com.abupdate.iot_libs.inter.IDownSimpleListener
        public void onFailed(int i) {
            Trace.d(OtaService.TAG, "onFailed() " + i);
            com.abupdate.iot_libs.engine.c.a().a(5, 0L, 0L, i);
            ReportManager.getInstance(OtaService.i).reportDownParamInfo(i, OtaService.j, OtaService.k);
        }

        @Override // com.abupdate.iot_libs.inter.IDownSimpleListener, com.abupdate.iot_download_libs.IOnDownListener
        public void on_start() {
            com.abupdate.iot_libs.engine.c.a().a(1, 0L, 0L, 0);
            OtaService.this.o();
        }
    }

    /* loaded from: classes.dex */
    public class b implements ILogoutCallback {
        public b(OtaService otaService) {
        }

        @Override // com.abupdate.iot_libs.inter.ILogoutCallback
        public void onLogoutFail(int i) {
            com.abupdate.iot_libs.a.b.a().a(OtaListener.getInstance().setAction(OtaListener.Action.DISCONNECT));
        }

        @Override // com.abupdate.iot_libs.inter.ILogoutCallback
        public void onLogoutSuccess() {
            com.abupdate.iot_libs.a.b.a().a(OtaListener.getInstance().setAction(OtaListener.Action.DISCONNECT));
        }

        @Override // com.abupdate.iot_libs.inter.ILogoutCallback
        public void onLogoutTimeout() {
            com.abupdate.iot_libs.a.b.a().a(OtaListener.getInstance().setAction(OtaListener.Action.DISCONNECT));
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c(OtaService otaService) {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String str = VersionInfo.getInstance().deltaUrl;
                if (TextUtils.isEmpty(str)) {
                    String unused = OtaService.k = "";
                } else {
                    String unused2 = OtaService.k = InetAddress.getByName(g.a(str)).getHostAddress();
                }
                Trace.d(OtaService.TAG, "download_task() download IP:" + OtaService.k);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    public OtaService() {
        super(TAG);
    }

    public static void initContext(Context context) {
        i = context;
    }

    public static void selfDisconnect() {
        if (MqttManager.getInstance().isKeepConnect()) {
            MqttManager.getInstance().stopKeepConnect();
            startByAction(ACTION_DISCONNECT);
        } else if (!MqttManager.getInstance().isConneect()) {
            Trace.d("MqttAgentPolicy", "disConnect() is disconnected");
            OtaListener.getInstance().disconnect(new MqttException(new Throwable("is disconnected")));
        } else if (com.abupdate.iot_libs.a.b.a().b() == b.a.Disconnecting) {
            Trace.d("MqttAgentPolicy", "disConnect() is disconnecting");
            OtaListener.getInstance().disconnect(new MqttException(new Throwable("is disconnecting")));
        } else {
            startByAction(ACTION_DISCONNECT);
        }
    }

    public static void setDownloadCancel() {
        DLManager.getInstance().cancel_all();
    }

    public static void startByAction(String str) {
        startByAction(str, null);
    }

    public final void d(File file) {
        if (!com.abupdate.iot_libs.engine.c.d().rebootConditionPrepare()) {
            Trace.d(TAG, "startUpdate() update conditions does not meet");
            com.abupdate.iot_libs.engine.c.d().onError(7006);
            return;
        }
        if (file.getAbsoluteFile() != null && OtaAgentPolicy.getVersionInfo().versionName != null && OtaAgentPolicy.getVersionInfo().deltaID != null) {
            SPFTool.putString(SPFTool.KEY_UPDATE_FILE_PATH, file.getAbsolutePath());
            SPFTool.putString(SPFTool.KEY_VERSION_NAME, OtaAgentPolicy.getVersionInfo().versionName);
            SPFTool.putString(SPFTool.KEY_DELTAID, OtaAgentPolicy.getVersionInfo().deltaID);
            SPFTool.putLong(SPFTool.KEY_LAST_RECOVERY_TIME, System.currentTimeMillis());
            Trace.d(TAG, "rebootUpgrade() version_name = " + SPFTool.getString(SPFTool.KEY_VERSION_NAME, "null") + ",deltaId:" + SPFTool.getString(SPFTool.KEY_DELTAID, "null"));
        }
        try {
            RecoverySystem.installPackage(i, file);
        } catch (IOException e) {
            Trace.e(TAG, "onUpdateFail() .", e);
            com.abupdate.iot_libs.engine.c.a().b(7004);
            ReportManager.getInstance(i).reportUpdateParamInfo(7004);
        }
    }

    public final void g() {
        try {
            d.a().b();
        } catch (FotaException e) {
            e.printStackTrace();
        }
    }

    public final void h() {
        if (DeviceInfo.getInstance().isValid()) {
            ReportManager.getInstance(i).report();
        }
    }

    public final void i() {
        Trace.d(TAG, "upgrade() start.");
        String str = OtaAgentPolicy.config.updatePath;
        Trace.d(TAG, "rebootUpgrade() path:" + str);
        if (!com.abupdate.iot_libs.utils.c.a(str, VersionInfo.getInstance().md5sum)) {
            Trace.e(TAG, "onUpdateFail() . update validate file fail");
            com.abupdate.iot_libs.engine.c.a().b(7005);
            ReportManager.getInstance(i).reportUpdateParamInfo(7005);
            return;
        }
        d(new File(str));
    }

    public final void j() {
        j = j.a();
        if (new File(OtaAgentPolicy.config.updatePath).exists() && com.abupdate.iot_libs.utils.c.a(OtaAgentPolicy.config.updatePath, VersionInfo.getInstance().md5sum)) {
            ReportManager.getInstance(OtaAgentPolicy.sCx).reportDownParamInfo(0, j, "");
            com.abupdate.iot_libs.engine.c.a().a(4, 0L, 0L, 0);
            return;
        }
        DLManager.getInstance().add(d.a().e());
        DLManager.getInstance().execAsync(new a());
    }

    public final void k() {
        int c2 = d.a().c();
        if (e.a(c2)) {
            com.abupdate.iot_libs.engine.c.a().c();
        } else {
            com.abupdate.iot_libs.engine.c.a().a(c2);
        }
    }

    public final void l() {
        if (MqttAgentPolicy.isConnected()) {
            if (com.abupdate.iot_libs.a.b.a().b() == b.a.Login) {
                com.abupdate.iot_libs.a.b.a().a(b.a.Disconnecting);
                com.abupdate.iot_libs.a.b.a().a(true, (ILogoutCallback) new b(this));
                return;
            }
            com.abupdate.iot_libs.a.b.a().a(b.a.Disconnecting);
            com.abupdate.iot_libs.a.b.a().a(OtaListener.getInstance().setAction(OtaListener.Action.DISCONNECT));
        } else if (MqttManager.getInstance().isKeepConnect()) {
            com.abupdate.iot_libs.a.b.a().a(OtaListener.getInstance().setAction(OtaListener.Action.DISCONNECT));
        }
    }

    public final void m() {
        if ((!TextUtils.isEmpty(RegisterInfo.getInstance().deviceSecret) && !TextUtils.isEmpty(RegisterInfo.getInstance().deviceId)) || (1000 == d.a().d() && RegisterInfo.getInstance().isValid())) {
            com.abupdate.iot_libs.a.b.a().a(b.a.Connecting);
            com.abupdate.iot_libs.a.b.a().c();
            return;
        }
        Trace.e(TAG, "connectMqtt() failed");
    }

    public final boolean n() {
        if (i == null) {
            Trace.e(TAG, "register_task() context is null,please call method initContext() in application!");
            return false;
        }
        int d = d.a().d();
        if (e.a(d)) {
            com.abupdate.iot_libs.engine.c.a().b();
            return true;
        }
        com.abupdate.iot_libs.engine.c.a().a(d);
        return false;
    }

    public final void o() {
        f.a().a(new c(this));
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.h = new Handler();
        if (com.abupdate.iot_libs.engine.c.a().f1892a == null) {
            com.abupdate.iot_libs.engine.c.a().a(this.h);
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.IntentService
    public void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("key_action");
        stringExtra.hashCode();
        char c2 = 65535;
        switch (stringExtra.hashCode()) {
            case -2124670863:
                if (stringExtra.equals(ACTION_DOWNLOAD)) {
                    c2 = 0;
                    break;
                }
                break;
            case -964360223:
                if (stringExtra.equals(ACTION_CONNECT)) {
                    c2 = 1;
                    break;
                }
                break;
            case -563586472:
                if (stringExtra.equals(ACTION_CHECK_VERSION)) {
                    c2 = 2;
                    break;
                }
                break;
            case -507665671:
                if (stringExtra.equals(ACTION_STATIC_CHECK_VERSION)) {
                    c2 = 3;
                    break;
                }
                break;
            case 52264588:
                if (stringExtra.equals(ACTION_REGISTER)) {
                    c2 = 4;
                    break;
                }
                break;
            case 1087001157:
                if (stringExtra.equals(ACTION_DISCONNECT)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1497533277:
                if (stringExtra.equals(ACTION_REPORT)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1593208562:
                if (stringExtra.equals(ACTION_UPDATE)) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                j();
                return;
            case 1:
                m();
                return;
            case 2:
                k();
                return;
            case 3:
                g();
                return;
            case 4:
                n();
                return;
            case 5:
                l();
                return;
            case 6:
                h();
                return;
            case 7:
                String stringExtra2 = intent.getStringExtra("key_extra_info");
                if (stringExtra2 != null) {
                    rebootLocalUpgrade(stringExtra2);
                    return;
                } else {
                    i();
                    return;
                }
            default:
                return;
        }
    }

    public void rebootLocalUpgrade(String str) {
        Trace.d(TAG, "rebootLocalUpgrade() path:" + str);
        if (TextUtils.isEmpty(str)) {
            Trace.e(TAG, "rebootLocalUpgrade() path is null");
            com.abupdate.iot_libs.engine.c.a().b(7002);
            ReportManager.getInstance(i).reportUpdateParamInfo(7002);
            return;
        }
        d(new File(str));
    }

    public static void startByAction(String str, @Nullable Object obj) {
        synchronized (OtaService.class) {
            if (TextUtils.isEmpty(str)) {
                Trace.e(TAG, "startByAction() action is null");
            } else if (i == null) {
                Trace.e(TAG, "startByAction() context is null,should call initContext();");
            } else {
                Intent intent = new Intent();
                intent.putExtra("key_action", str);
                intent.setClass(i, OtaService.class);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        intent.putExtra("key_extra_info", (Boolean) obj);
                    }
                    if (obj instanceof Integer) {
                        intent.putExtra("key_extra_info", (Integer) obj);
                    }
                    if (obj instanceof String) {
                        intent.putExtra("key_extra_info", (String) obj);
                    }
                }
                i.startService(intent);
            }
        }
    }
}
