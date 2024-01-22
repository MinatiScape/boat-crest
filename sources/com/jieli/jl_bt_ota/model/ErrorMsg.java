package com.jieli.jl_bt_ota.model;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class ErrorMsg {
    private final int code;
    private final String codeDesc;
    private final int subCode;
    private final String subMessage;

    public ErrorMsg(int i, String str) {
        this(i, str, -1, null);
    }

    public static ErrorMsg parseJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new ErrorMsg(jSONObject.has("code") ? jSONObject.getInt("code") : -1, jSONObject.has("desc") ? jSONObject.getString("desc") : null, jSONObject.has("sub_code") ? jSONObject.getInt("sub_code") : -1, jSONObject.has(Constants.KEY_MESSAGE) ? jSONObject.getString(Constants.KEY_MESSAGE) : null);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String toJsonString() {
        String str = "{\"code\":" + this.code;
        if (this.codeDesc != null) {
            str = str + ", \"desc\":\"" + this.codeDesc + "\"";
        }
        if (this.subCode != -1) {
            str = str + ", \"sub_code\":" + this.subCode;
        }
        if (this.subMessage != null) {
            str = str + ", \"message\":\"" + this.subMessage + "\"";
        }
        return str + '}';
    }

    public Integer getCode() {
        return Integer.valueOf(this.code);
    }

    public String getCodeDesc() {
        return this.codeDesc;
    }

    public int getSubCode() {
        return this.subCode;
    }

    public String getSubMessage() {
        return this.subMessage;
    }

    public String toString() {
        return toJsonString();
    }

    public ErrorMsg(int i, String str, int i2, String str2) {
        this.code = i;
        this.codeDesc = str;
        this.subCode = i2;
        this.subMessage = str2;
    }
}
