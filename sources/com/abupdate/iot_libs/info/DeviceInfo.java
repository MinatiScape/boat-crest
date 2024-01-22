package com.abupdate.iot_libs.info;

import android.text.TextUtils;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.constant.OtaConstants;
import com.abupdate.iot_libs.security.FotaException;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.trace.Trace;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DeviceInfo {
    public static final String KEY_MID_BACK = "key_mid_back";
    public static DeviceInfo mInstance;
    public String deviceType;
    public String mid;
    public String models;
    public String oem;
    public String platform;
    public String version;

    public static DeviceInfo getInstance() {
        if (mInstance == null) {
            synchronized (DeviceInfo.class) {
                if (mInstance == null) {
                    mInstance = new DeviceInfo();
                }
            }
        }
        return mInstance;
    }

    public final boolean a() {
        String str = getInstance().oem + getInstance().models + getInstance().platform + getInstance().deviceType;
        if (TextUtils.equals(SPFTool.getString("key_device_info", ""), str)) {
            return true;
        }
        SPFTool.putString("key_device_info", str);
        return false;
    }

    public void init() throws FotaException {
        Trace.d("DeviceInfo", "%s%s%s", OtaConstants.SINGLE_LINE, "init info", OtaConstants.SINGLE_LINE);
        try {
            try {
                Class<?> loadClass = OtaAgentPolicy.sCx.getClassLoader().loadClass("android.os.SystemProperties");
                Method method = loadClass.getMethod("get", String.class);
                this.version = (String) method.invoke(loadClass, "ro.fota.version");
                this.oem = (String) method.invoke(loadClass, "ro.fota.oem");
                this.models = (String) method.invoke(loadClass, "ro.fota.device");
                this.platform = (String) method.invoke(loadClass, "ro.fota.platform");
                this.deviceType = (String) method.invoke(loadClass, "ro.fota.type");
                Trace.d("DeviceInfo", String.format("version:%s,oem:%s,models:%s,platform:%s,deviceType:%s", this.version, this.oem, this.models, this.platform, this.deviceType));
            } catch (Exception e) {
                throw new FotaException(202, e);
            }
        } finally {
            if (!a()) {
                ProductInfo.getInstance().reset();
                RegisterInfo.getInstance().reset();
            }
        }
    }

    public void initInfo(String str) {
        Trace.d("DeviceInfo", "set mid:" + str);
        this.mid = str;
        SPFTool.putString(KEY_MID_BACK, str);
    }

    public void initOtherInfo(String str, String str2, String str3, String str4, String str5) throws FotaException {
        Trace.d("DeviceInfo", "%s%s%s", OtaConstants.SINGLE_LINE, "init other info", OtaConstants.SINGLE_LINE);
        this.oem = str2;
        this.models = str3;
        this.platform = str4;
        this.deviceType = str5;
        this.version = str;
        if (!a()) {
            ProductInfo.getInstance().reset();
            RegisterInfo.getInstance().reset();
        }
        Trace.d("DeviceInfo", String.format("version:%s,oem:%s,models:%s,platform:%s,deviceType:%s", str, str2, str3, str4, str5));
    }

    public boolean isValid() {
        boolean z;
        if (TextUtils.isEmpty(this.mid)) {
            Trace.d("DeviceInfo", "isValid() mid = null");
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(this.oem)) {
            Trace.d("DeviceInfo", "isValid() oem = null");
            z = false;
        }
        if (TextUtils.isEmpty(this.models)) {
            Trace.d("DeviceInfo", "isValid() models = null");
            z = false;
        }
        if (TextUtils.isEmpty(this.platform)) {
            Trace.d("DeviceInfo", "isValid() platform = null");
            z = false;
        }
        if (TextUtils.isEmpty(this.deviceType)) {
            Trace.d("DeviceInfo", "isValid() deviceType = null");
            return false;
        }
        return z;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mid", this.mid);
            jSONObject.put("version", this.version);
            jSONObject.put("oem", this.oem);
            jSONObject.put("models", this.models);
            jSONObject.put("platform", this.platform);
            jSONObject.put("deviceType", this.deviceType);
            jSONObject.put("productId", ProductInfo.getInstance().productId);
            jSONObject.put("productSecret", ProductInfo.getInstance().productSecret);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
