package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.AppSendAllPhoneContactsCallBack;
import com.ido.ble.callback.AppSendDataCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.WeatherInfoV3;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class b {
    public static void a(int i, int i2, int i3) {
        if (i != 153) {
            return;
        }
        a(i2, AppSendDataCallBack.DataType.WEATHER);
    }

    private static void a(int i, AppSendDataCallBack.DataType dataType) {
        AppSendDataCallBack.a(i == 0, dataType);
    }

    public static void a(int i, byte[] bArr, int i2) {
        String d = com.ido.ble.common.c.d(bArr);
        if (i == 5045) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[APP_SEND_DATA] [handle Device Return set v3 weather JsonString] " + d);
            a(d, AppSendDataCallBack.DataType.WEATHER_V3);
        } else if (i == 5518) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[APP_SEND_DATA] [handle Device Return set all phone contacts] " + d);
            a(d);
        } else if (i != 6504) {
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[APP_SEND_DATA] [handle Device Return set sun time JsonString] " + d);
            b(d, AppSendDataCallBack.DataType.WEATHER_SUN_TIME);
        }
    }

    private static void a(String str) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return set AllPhoneContacts JsonString] " + str);
        AllPhoneContacts.Response response = (AllPhoneContacts.Response) new Gson().fromJson(str, (Class<Object>) AllPhoneContacts.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return AllPhoneContacts JsonString] response == null");
        } else {
            AppSendAllPhoneContactsCallBack.onCallBack(response.path);
        }
    }

    private static void a(String str, AppSendDataCallBack.DataType dataType) {
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return V3Weather JsonString] " + str);
        WeatherInfoV3.Response response = (WeatherInfoV3.Response) new Gson().fromJson(str, (Class<Object>) WeatherInfoV3.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return V3Weather JsonString] response == null");
        } else {
            AppSendDataCallBack.a(response.err_code == 0, dataType);
        }
    }

    public static boolean a(int i) {
        return i == 153 || i == 5045 || i == 5518 || i == 6504;
    }

    private static void b(String str, AppSendDataCallBack.DataType dataType) {
        int i;
        try {
            i = new JSONObject(str).optInt("is_success");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.p + e.getMessage());
            i = 0;
        }
        AppSendDataCallBack.a(i == 1, dataType);
    }
}
