package com.coveiot.android.spo2sdk.utils;

import android.content.Context;
import com.coveiot.android.spo2sdk.events.ConnectionTypeSpo2;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.GsonBuilder;
/* loaded from: classes7.dex */
public class Spo2Utils {
    public static ConnectionTypeSpo2 getConnectionType(Context context) {
        ConnectionTypeSpo2 connectionTypeSpo2 = ConnectionTypeSpo2.RECONNECT_ON_DISCONNECT;
        String str = (String) Spo2PreferenceManager.getPreference(context, Spo2PrefConstants.BLE_CONNECTION_TYPE.getName(), connectionTypeSpo2.name());
        if (AppUtils.isEmpty(str)) {
            return connectionTypeSpo2;
        }
        LogHelper.e("Bleutils ", "preference value is +++ " + str, Spo2PrefConstants.SPO2_MODULE_NAME.getName());
        return (ConnectionTypeSpo2) new GsonBuilder().registerTypeAdapterFactory(new LowercaseEnumTypeAdapterFactory()).create().fromJson(str.toLowerCase(), (Class<Object>) ConnectionTypeSpo2.class);
    }
}
