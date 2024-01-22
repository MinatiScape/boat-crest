package com.abupdate.iot_libs.a;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.abupdate.http_libs.HttpIotUtils;
import com.abupdate.http_libs.response.Response;
import com.abupdate.iot_libs.constant.SDKConfig;
import com.abupdate.iot_libs.engine.LogManager;
import com.abupdate.iot_libs.info.DeviceInfo;
import com.abupdate.iot_libs.info.DownParamInfo;
import com.abupdate.iot_libs.info.ErrorFileParamInfo;
import com.abupdate.iot_libs.info.ProductInfo;
import com.abupdate.iot_libs.info.RegisterInfo;
import com.abupdate.iot_libs.info.UpgradeParamInfo;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.utils.d;
import com.abupdate.iot_libs.utils.e;
import com.abupdate.iot_libs.utils.f;
import com.abupdate.iot_libs.utils.g;
import com.abupdate.iot_libs.utils.j;
import com.abupdate.trace.Trace;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static a f1885a;

    public static a a() {
        if (f1885a == null) {
            synchronized (a.class) {
                if (f1885a == null) {
                    f1885a = new a();
                }
            }
        }
        return f1885a;
    }

    public static void c(JSONObject jSONObject, String str) {
        String a2;
        long a3 = j.a();
        DeviceInfo deviceInfo = DeviceInfo.getInstance();
        if (!deviceInfo.isValid()) {
            Trace.e("HttpTools", "device info is invalid!");
            return;
        }
        if (str.contains("register")) {
            if (!ProductInfo.getInstance().isProductValid()) {
                Trace.e("HttpTools", "Product info is invalid!");
                return;
            }
            a2 = com.abupdate.iot_libs.security.a.a(deviceInfo.mid + ProductInfo.getInstance().productId + a3, ProductInfo.getInstance().productSecret);
        } else if (str.contains("obtainProduct")) {
            a2 = com.abupdate.iot_libs.security.b.a();
        } else if (ProductInfo.getInstance().isProductValid() && RegisterInfo.getInstance().isValid()) {
            a2 = com.abupdate.iot_libs.security.a.a(RegisterInfo.getInstance().deviceId + ProductInfo.getInstance().productId + a3, RegisterInfo.getInstance().deviceSecret);
        } else {
            Trace.e("HttpTools", "Product info or Register info is invalid!");
            return;
        }
        try {
            jSONObject.put("timestamp", a3);
            if (str.contains("notify/response")) {
                return;
            }
            jSONObject.put("sign", a2);
        } catch (JSONException e) {
            e.printStackTrace();
            Trace.e("HttpTools", "genSignAndTime() " + e.toString());
        }
    }

    public String b() {
        String str = SDKConfig.HTTP_BASE_URL + "/product/obtainProduct";
        JSONObject jSONObject = new JSONObject();
        DeviceInfo deviceInfo = DeviceInfo.getInstance();
        try {
            jSONObject.put("oem", deviceInfo.oem);
            jSONObject.put("models", deviceInfo.models);
            jSONObject.put("platform", deviceInfo.platform);
            jSONObject.put("deviceType", deviceInfo.deviceType);
            Response a2 = a(str, jSONObject);
            if (a2.isResultOk()) {
                JSONObject jSONObject2 = new JSONObject(a2.getContent());
                if (jSONObject2.has(NotificationCompat.CATEGORY_STATUS) && e.a(jSONObject2.getInt(NotificationCompat.CATEGORY_STATUS)) && jSONObject2.has("data")) {
                    String string = jSONObject2.getString("data");
                    String string2 = jSONObject.getString("sign");
                    String substring = string2.substring(8, 24);
                    if (!TextUtils.isEmpty(string2)) {
                        String a3 = com.abupdate.iot_libs.security.b.a(substring, string);
                        Trace.d("HttpTools", "doPostObtainProduct() :" + a3);
                        return a3;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String a(DeviceInfo deviceInfo, Context context) {
        String a2 = j.a(context);
        String a3 = g.a(context);
        String str = SDKConfig.HTTP_BASE_URL + "/register/" + ProductInfo.getInstance().productId;
        JSONObject jSONObject = new JSONObject();
        try {
            String a4 = com.abupdate.iot_libs.utils.b.a(context);
            jSONObject.put("mid", deviceInfo.mid);
            jSONObject.put("oem", deviceInfo.oem);
            jSONObject.put("models", deviceInfo.models);
            jSONObject.put("platform", deviceInfo.platform);
            jSONObject.put("deviceType", deviceInfo.deviceType);
            jSONObject.put("sdkversion", "1.3.5_smawatch");
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("appversion", a2);
            }
            jSONObject.put("version", deviceInfo.version);
            if (!TextUtils.isEmpty(a4)) {
                jSONObject.put("mac", a4);
            }
            if (!TextUtils.isEmpty(a3)) {
                jSONObject.put("networkType", a3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Trace.d("HttpTools", "doPostRegister() :" + e.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            Trace.d("HttpTools", "doPostRegister() :" + e2.toString());
        }
        Response a5 = a(str, jSONObject);
        return a5.isResultOk() ? a5.getContent() : "";
    }

    public String b(DeviceInfo deviceInfo, Context context) {
        String a2 = g.a(context);
        String format = String.format("%s/product/%s/%s/ota/checkVersion", SDKConfig.HTTP_BASE_URL, ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
        com.abupdate.iot_libs.engine.e.a().a(context);
        String c = com.abupdate.iot_libs.engine.e.a().c();
        String b = com.abupdate.iot_libs.engine.e.a().b();
        String d = com.abupdate.iot_libs.engine.e.a().d();
        String e = com.abupdate.iot_libs.engine.e.a().e();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", DeviceInfo.getInstance().mid);
            jSONObject.put("version", DeviceInfo.getInstance().version);
            jSONObject.put("networkType", a2);
            if (!TextUtils.isEmpty(c) && c.length() <= 100) {
                jSONObject.put("cid", c);
            }
            if (!TextUtils.isEmpty(b) && b.length() <= 100) {
                jSONObject.put("lac", b);
            }
            if (!TextUtils.isEmpty(d) && d.length() <= 100) {
                jSONObject.put("mcc", d);
            }
            if (!TextUtils.isEmpty(e) && e.length() <= 100) {
                jSONObject.put("mnc", e);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Response a3 = a(format, jSONObject);
        return a3.isResultOk() ? a3.getContent() : "";
    }

    public String a(DownParamInfo downParamInfo) {
        String format = String.format(SDKConfig.HTTP_BASE_URL + "/product/%s/%s/ota/reportDownResult", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", downParamInfo.mid);
            jSONObject.put("deltaID", downParamInfo.deltaID);
            jSONObject.put("downloadStatus", downParamInfo.downloadStatus);
            jSONObject.put("downStart", downParamInfo.downStart);
            jSONObject.put("downEnd", downParamInfo.downEnd);
            jSONObject.put("downSize", downParamInfo.downSize);
            if (!TextUtils.isEmpty(downParamInfo.downIp)) {
                jSONObject.put("downIp", downParamInfo.downIp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response a2 = a(format, jSONObject);
        return a2.isResultOk() ? a2.getContent() : "";
    }

    public String a(UpgradeParamInfo upgradeParamInfo) {
        String format = String.format(SDKConfig.HTTP_BASE_URL + "/product/%s/%s/ota/reportUpgradeResult", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", upgradeParamInfo.mid);
            jSONObject.put("deltaID", upgradeParamInfo.deltaID);
            jSONObject.put("updateStatus", upgradeParamInfo.updateStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response a2 = a(format, jSONObject);
        return a2.isResultOk() ? a2.getContent() : "";
    }

    public static void b(Map<String, String> map) {
        long a2 = j.a();
        String a3 = com.abupdate.iot_libs.security.a.a(RegisterInfo.getInstance().deviceId + ProductInfo.getInstance().productId + a2, RegisterInfo.getInstance().deviceSecret);
        map.put("timestamp", String.valueOf(a2));
        map.put("sign", a3);
    }

    public String a(ErrorFileParamInfo errorFileParamInfo) throws FotaException {
        File file = new File(errorFileParamInfo.uploadFile);
        if (file.exists()) {
            HashMap hashMap = new HashMap();
            String format = String.format(SDKConfig.HTTP_BASE_URL + "/product/%s/%s/ota/reportErrorLog", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId);
            Trace.d("HttpTools", "doPostErrorLog() log path:" + file.getAbsolutePath());
            hashMap.put("mid", errorFileParamInfo.mid);
            if (!TextUtils.isEmpty(errorFileParamInfo.deltaID)) {
                hashMap.put("deltaID", errorFileParamInfo.deltaID);
            }
            hashMap.put("errorType", errorFileParamInfo.errorType);
            Trace.d("HttpTools", "doPostErrorLog() params:" + j.a(hashMap));
            b(hashMap);
            Response exec = HttpIotUtils.postFile(format).addFile(LogManager.FILE_KEY, file).map(hashMap).exec();
            return exec.isResultOk() ? exec.getContent() : "";
        }
        throw new FotaException(204);
    }

    public String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msgId", str);
            jSONObject.put("mid", DeviceInfo.getInstance().mid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Response a2 = a(String.format(SDKConfig.HTTP_BASE_URL + "/product/%s/%s/notify/response", ProductInfo.getInstance().productId, RegisterInfo.getInstance().deviceId), jSONObject);
        return a2.isResultOk() ? a2.getContent() : "";
    }

    public static Response a(String str, JSONObject jSONObject) {
        c(jSONObject, str);
        Trace.d("HttpTools", "doPostJson() request: url:" + str + " ,json:" + jSONObject.toString());
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext a2 = d.a(new String(SDKConfig.KEY), "adcom.bks");
            if (a2 != null) {
                sSLSocketFactory = a2.getSocketFactory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Response exec = HttpIotUtils.postJson(str).json(jSONObject).setHostnameVerifier(new f()).setSslSocketFactory(sSLSocketFactory).build().exec();
        Object[] objArr = new Object[1];
        objArr[0] = exec.isResultOk() ? exec.getContent() : "null";
        Trace.d("HttpTools", "doPostJson() response: result:%s", objArr);
        if (!exec.isResultOk() && exec.getException() != null) {
            Trace.e("HttpTools", "doPostJson() exception:", exec.getException());
        }
        return exec;
    }
}
