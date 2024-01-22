package com.ido.ble.i.a;

import com.google.gson.Gson;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.SportModeSort;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class k {
    public static void a() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, (int) com.veryfit.multi.nativeprotocol.b.z1, 0, 0);
    }

    public static void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(calorieAndDistanceGoal)), 161);
    }

    public static void a(Menstrual menstrual) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menstrual)), 159);
    }

    public static void a(MenstrualRemind menstrualRemind) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(menstrualRemind)), 160);
    }

    public static void a(PressureParam pressureParam) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(pressureParam)), 163);
    }

    public static void a(SPO2Param sPO2Param) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sPO2Param)), 162);
    }

    public static void a(SportModeSort sportModeSort) {
        u.b(com.ido.ble.common.c.b(new Gson().toJson(sportModeSort)), 164);
    }

    public static void b() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 1, 0, 0);
    }

    public static void c() {
        u.a((int) com.veryfit.multi.nativeprotocol.b.t5, 2, 0, 0);
    }
}
