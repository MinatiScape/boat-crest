package com.abupdate.iot_libs.info;

import android.text.TextUtils;
import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.constant.Error;
import com.abupdate.iot_libs.inter.IRegisterListener;
import com.abupdate.iot_libs.utils.SPFTool;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class RegisterInfo {

    /* renamed from: a  reason: collision with root package name */
    public static RegisterInfo f1898a;
    public String deviceId;
    public String deviceSecret;

    /* loaded from: classes.dex */
    public class a implements IRegisterListener {
        public a(RegisterInfo registerInfo) {
        }

        @Override // com.abupdate.iot_libs.inter.IRegisterListener
        public void onFailed(int i) {
            Trace.w("RegisterInfo", "init() register fail, " + Error.getErrorMessage(i));
        }

        @Override // com.abupdate.iot_libs.inter.IRegisterListener
        public void onSuccess() {
            Trace.d("RegisterInfo", "init() register success ");
        }
    }

    public static RegisterInfo getInstance() {
        if (f1898a == null) {
            synchronized (RegisterInfo.class) {
                if (f1898a == null) {
                    f1898a = new RegisterInfo();
                }
            }
        }
        return f1898a;
    }

    public void init() {
        String string = SPFTool.getString(SPFTool.KEY_DEVICE_SECRET, "");
        String string2 = SPFTool.getString(SPFTool.KEY_DEVICE_ID, "");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            Trace.d("RegisterInfo", "init() first register");
            OtaAgentPolicy.registerAsync(new a(this));
        }
        if (!TextUtils.isEmpty(string)) {
            this.deviceSecret = string;
        }
        if (TextUtils.isEmpty(string2)) {
            return;
        }
        this.deviceId = string2;
    }

    public boolean isValid() {
        boolean z;
        if (TextUtils.isEmpty(this.deviceSecret)) {
            Trace.d("RegisterInfo", "isValid() deviceSecret = null");
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(this.deviceId)) {
            Trace.d("RegisterInfo", "isValid() deviceId = null");
            return false;
        }
        return z;
    }

    public void reset() {
        Trace.d("RegisterInfo", "register info reset");
        this.deviceSecret = "";
        this.deviceId = "";
        SPFTool.putString(SPFTool.KEY_DEVICE_SECRET, "");
        SPFTool.putString(SPFTool.KEY_DEVICE_ID, "");
    }
}
