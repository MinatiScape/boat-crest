package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.AllPhoneContacts;
import com.ido.ble.protocol.model.WeatherInfo;
import com.ido.ble.protocol.model.WeatherInfoV3;
import com.ido.ble.protocol.model.WeatherSunTime;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c {
    public static void a(AllPhoneContacts allPhoneContacts) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(allPhoneContacts)), (int) com.veryfit.multi.nativeprotocol.b.k4);
    }

    public static void a(WeatherInfo weatherInfo) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherInfo)), 153);
    }

    public static void a(WeatherInfoV3 weatherInfoV3) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherInfoV3)), (int) com.veryfit.multi.nativeprotocol.b.R3);
    }

    public static void a(WeatherSunTime weatherSunTime) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(weatherSunTime)), (int) com.veryfit.multi.nativeprotocol.b.t4);
    }
}
