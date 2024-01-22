package com.abupdate.iot_libs.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import com.abupdate.iot_libs.MqttAgentPolicy;
import com.abupdate.iot_libs.constant.BroadcastConsts;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.engine.LogManager;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.UpgradeParamInfo;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.service.OtaService;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.trace.Trace;
import java.io.File;
/* loaded from: classes.dex */
public class UpgradeReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public Context f1907a;

    public final void a() {
        SPFTool.putBoolean(SPFTool.KEY_SHOULD_REPORT, true);
        String string = SPFTool.getString(SPFTool.KEY_VERSION_NAME, "");
        String string2 = SPFTool.getString(SPFTool.KEY_DELTAID, "");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return;
        }
        SPFTool.putString(SPFTool.KEY_VERSION_NAME, "");
        SPFTool.putString(SPFTool.KEY_DELTAID, "");
        Trace.i("UpgradeReceiver", "mobile version:" + DeviceInfo.getInstance().version + " update version " + string);
        boolean equals = string.equals(DeviceInfo.getInstance().version);
        Intent intent = new Intent(BroadcastConsts.ACTION_FOTA_UPDATE_RESULT);
        intent.putExtra(BroadcastConsts.KEY_FOTA_UPDATE_RESULT, equals);
        intent.setPackage(BroadcastConsts.PACKAGE_FOTA_UPDATE);
        this.f1907a.sendBroadcast(intent);
        if (equals) {
            Trace.d("UpgradeReceiver", "boot_process() update success!");
            String string3 = SPFTool.getString(SPFTool.KEY_UPDATE_FILE_PATH, "");
            if (!TextUtils.isEmpty(string3)) {
                new File(string3).delete();
            }
        } else {
            Trace.d("UpgradeReceiver", "boot_process() update failed!");
        }
        ReportManager.getInstance(this.f1907a).saveReportData(new UpgradeParamInfo(TextUtils.isEmpty(DeviceInfo.getInstance().mid) ? SPFTool.getString(DeviceInfo.KEY_MID_BACK, "abupdate-MID-ERROR-COLLECT") : DeviceInfo.getInstance().mid, string2, String.valueOf(equals ? 1 : 99)));
        if (!equals) {
            LogManager.getInstance().saveRecoveryLog(string2);
        }
        if (c(this.f1907a)) {
            Trace.d("UpgradeReceiver", "boot_process() boot complete upgrade report");
            e();
        }
    }

    public final void b(String str) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str)) {
            d();
        } else if ("android.intent.action.BOOT_COMPLETED".equals(str)) {
            a();
        }
    }

    public final boolean c(Context context) {
        NetworkInfo[] allNetworkInfo;
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null) {
                return false;
            }
            for (int i = 0; i < allNetworkInfo.length && allNetworkInfo[i] != null; i++) {
                if (allNetworkInfo[i].isConnected() && allNetworkInfo[i].isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            Trace.d("UpgradeReceiver", "==Util:isNetWorkAvailable Exception");
            return false;
        }
    }

    public final void d() {
        if (Build.VERSION.SDK_INT >= 21 || !c(this.f1907a)) {
            return;
        }
        Trace.d("UpgradeReceiver", "network_process() should_report = " + SPFTool.getBoolean(SPFTool.KEY_SHOULD_REPORT, false));
        if (SPFTool.getBoolean(SPFTool.KEY_SHOULD_REPORT, false)) {
            e();
        }
        if (System.currentTimeMillis() - SPFTool.getLong(OtaConstants.SPF_STATIC_CHECK_VERSION_CYCLE, -1L) >= OtaConstants.STATIC_CHECK_VERSION_CYCLE) {
            OtaService.startByAction(OtaService.ACTION_STATIC_CHECK_VERSION);
            SPFTool.putLong(OtaConstants.SPF_STATIC_CHECK_VERSION_CYCLE, System.currentTimeMillis());
        }
        if (!SPFTool.getBoolean(MqttAgentPolicy.CONFIG_MQTT_CONNECT, false) || MqttAgentPolicy.isConnected()) {
            return;
        }
        MqttAgentPolicy.connect();
    }

    public final void e() {
        if (ReportManager.getInstance(this.f1907a).queryReport() == 0) {
            SPFTool.putBoolean(SPFTool.KEY_SHOULD_REPORT, false);
            Trace.d("UpgradeReceiver", "report() do not have data to be reported!");
            return;
        }
        OtaService.startByAction(OtaService.ACTION_REPORT);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.f1907a = context;
        String action = intent.getAction();
        Trace.d("UpgradeReceiver", "action: " + action);
        b(action);
    }
}
