package com.abupdate.iot_libs;

import android.content.Context;
import com.abupdate.iot_libs.info.CustomDeviceInfo;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.ProductInfo;
import com.abupdate.iot_libs.report.ReportManager;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.iot_libs.utils.a;
import com.abupdate.trace.Trace;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IndirectOtaAgentPolicy {
    public static String getDeviceInfo() {
        Trace.d("IndirectOtaAgentPolicy", "getDeviceInfo() ");
        return DeviceInfo.getInstance().toString();
    }

    public static String getVersionInfo() {
        Trace.d("IndirectOtaAgentPolicy", "getVersionInfo() ");
        return SPFTool.getString(SPFTool.KEY_VERSION_INFO, "");
    }

    public static void reportUpdate(Context context, boolean z) {
        Trace.d("IndirectOtaAgentPolicy", "reportUpdate() result:" + z);
        ReportManager.getInstance(context).reportUpdateParamInfo(z ? 1 : 99);
    }

    public static boolean resetDeviceInfo(String str) {
        Trace.d("IndirectOtaAgentPolicy", "resetDeviceInfo() device info:" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            DeviceInfo.getInstance().mid = jSONObject.getString("mid");
            DeviceInfo.getInstance().version = jSONObject.getString("version");
            DeviceInfo.getInstance().oem = jSONObject.getString("oem");
            DeviceInfo.getInstance().models = jSONObject.getString("models");
            DeviceInfo.getInstance().platform = jSONObject.getString("platform");
            DeviceInfo.getInstance().deviceType = jSONObject.getString("deviceType");
            ProductInfo.getInstance().productId = jSONObject.getString("productId");
            ProductInfo.getInstance().productSecret = jSONObject.getString("productSecret");
        } catch (Exception e) {
            Trace.e("IndirectOtaAgentPolicy", e);
        }
        return DeviceInfo.getInstance().isValid();
    }

    public static CustomDeviceInfo setDeviceInfo(String str) {
        Trace.d("IndirectOtaAgentPolicy", "setDeviceInfo() device info:" + str);
        CustomDeviceInfo customDeviceInfo = new CustomDeviceInfo();
        try {
            JSONObject jSONObject = new JSONObject(str);
            customDeviceInfo.mid = jSONObject.getString("mid");
            customDeviceInfo.version = jSONObject.getString("version");
            customDeviceInfo.oem = jSONObject.getString("oem");
            customDeviceInfo.models = jSONObject.getString("models");
            customDeviceInfo.platform = jSONObject.getString("platform");
            customDeviceInfo.deviceType = jSONObject.getString("deviceType");
            customDeviceInfo.productId = jSONObject.getString("productId");
            customDeviceInfo.product_secret = jSONObject.getString("productSecret");
        } catch (Exception e) {
            Trace.e("IndirectOtaAgentPolicy", e);
        }
        return customDeviceInfo;
    }

    public static void setVersionInfo(String str) {
        Trace.d("IndirectOtaAgentPolicy", "setVersionInfo() version info:" + str);
        a.a(str);
    }
}
