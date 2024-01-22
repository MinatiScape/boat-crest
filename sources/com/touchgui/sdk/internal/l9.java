package com.touchgui.sdk.internal;

import android.location.Location;
import android.text.TextUtils;
import com.touchgui.sdk.TGCommand;
import com.touchgui.sdk.TGCommandBuilder;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGAppMenu;
import com.touchgui.sdk.bean.TGAppMenuStyle;
import com.touchgui.sdk.bean.TGBrightnessConfig;
import com.touchgui.sdk.bean.TGFunctions;
import com.touchgui.sdk.bean.TGHeartRateMonitoringModeConfig;
import com.touchgui.sdk.bean.TGHeartRateRangeConfig;
import com.touchgui.sdk.bean.TGMessage;
import com.touchgui.sdk.bean.TGMusicInfo;
import com.touchgui.sdk.bean.TGNightModeConfig;
import com.touchgui.sdk.bean.TGNotDisturbConfig;
import com.touchgui.sdk.bean.TGPhysiologicalCycle;
import com.touchgui.sdk.bean.TGProfile;
import com.touchgui.sdk.bean.TGQuickCard;
import com.touchgui.sdk.bean.TGQuickReply;
import com.touchgui.sdk.bean.TGRaiseWristConfig;
import com.touchgui.sdk.bean.TGRemindDrinking;
import com.touchgui.sdk.bean.TGSedentaryConfig;
import com.touchgui.sdk.bean.TGSosConfig;
import com.touchgui.sdk.bean.TGSpo2Config;
import com.touchgui.sdk.bean.TGStock;
import com.touchgui.sdk.bean.TGStressConfig;
import com.touchgui.sdk.bean.TGTarget;
import com.touchgui.sdk.bean.TGUnitConfig;
import com.touchgui.sdk.bean.TGWashConfig;
import com.touchgui.sdk.bean.TGWeather;
import com.touchgui.sdk.bean.TGWorkoutHeartRateConfig;
import com.touchgui.sdk.bean.TGWorkoutMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes12.dex */
public final class l9 implements TGCommandBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final d2 f13789a;

    public l9(d2 d2Var) {
        this.f13789a = d2Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand auth(String str) {
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.d() == 1) {
            a0 a0Var = d2Var.f13753a;
            n6 n6Var = new n6();
            n6Var.b(18).put((byte) 12).put((byte) 1).put(a.a(s.a(str)));
            return new v8(a0Var, n6Var);
        }
        a0 a0Var2 = d2Var.f13753a;
        f fVar = new f();
        fVar.b(18).put((byte) 12).put((byte) 1).put(a.a(s.a(str)));
        return new v8(a0Var2, fVar);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand bind() {
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.d() == 1) {
            return new v8(d2Var.f13753a, new m6());
        }
        return new v8(d2Var.f13753a, new e());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand findDevice(boolean z, int i) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 18);
        y8Var.b(18).put((byte) (z ? 170 : 85));
        y8Var.b(18).put((byte) i);
        return new g0(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getAppMenuStyle() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new r0(d2Var, a0Var, new q8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getAppMenus() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new t0(d2Var, a0Var, new r8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getBatteryInfo() {
        return new v8(this.f13789a.f13753a, new b8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getBrightnessConfig() {
        d2 d2Var = this.f13789a;
        return new o0(d2Var, d2Var.f13753a, new n7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getBtMac() {
        return new v8(this.f13789a.f13753a, new w7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getBtName() {
        d2 d2Var = this.f13789a;
        return new h1(d2Var, d2Var.f13753a, new u7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getCameraOnOff() {
        d2 d2Var = this.f13789a;
        return new m0(d2Var, d2Var.f13753a, new k7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getContacts() {
        d2 d2Var = this.f13789a;
        return new q1(d2Var, d2Var.f13753a, new f2(d2Var.f13753a.a(34146306)));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getDeviceInfo() {
        return new v8(this.f13789a.f13753a, new b7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getDeviceMac() {
        return new v8(this.f13789a.f13753a, new v7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getDeviceSn() {
        return new v8(this.f13789a.f13753a, new x7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getEventReminders() {
        d2 d2Var = this.f13789a;
        return new p1(d2Var, d2Var.f13753a, new k3(d2Var.f13753a.a(34146306)));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getFindPhoneConfig() {
        d2 d2Var = this.f13789a;
        return new f0(d2Var, d2Var.f13753a, new g7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getGpsInfo() {
        return new v8(this.f13789a.f13753a, new t6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getGpsStatus() {
        return new v8(this.f13789a.f13753a, new u6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getHeartRateMonitoringMode() {
        d2 d2Var = this.f13789a;
        return new e0(d2Var, d2Var.f13753a, new f7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getHeartRateRange() {
        d2 d2Var = this.f13789a;
        return new d0(d2Var, d2Var.f13753a, new e7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getModelName() {
        return new v8(this.f13789a.f13753a, new s6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getMtuInfo() {
        return new v8(this.f13789a.f13753a, new w6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getMusicOnOff() {
        d2 d2Var = this.f13789a;
        return new j0(d2Var, d2Var.f13753a, new p7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getNightMode() {
        d2 d2Var = this.f13789a;
        return new n0(d2Var, d2Var.f13753a, new m7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getNotDisturbMode() {
        d2 d2Var = this.f13789a;
        return new i0(d2Var, d2Var.f13753a, new i7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getPhysiologicalCycle() {
        return new v8(this.f13789a.f13753a, new q7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getProfile() {
        d2 d2Var = this.f13789a;
        return new a2(d2Var, d2Var.f13753a, new a7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getQuickCards() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new p0(d2Var, a0Var, new p8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getRaiseWrist() {
        d2 d2Var = this.f13789a;
        return new h0(d2Var, d2Var.f13753a, new h7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getRealTimeData() {
        return new v8(this.f13789a.f13753a, new r6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getRemindDrinking() {
        d2 d2Var = this.f13789a;
        return new c2(d2Var, d2Var.f13753a, new j7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getSedentary() {
        d2 d2Var = this.f13789a;
        return new c0(d2Var, d2Var.f13753a, new d7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getSosConfig() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new e1(d2Var, a0Var, new j8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getSpo2Config() {
        d2 d2Var = this.f13789a;
        return new y0(d2Var, d2Var.f13753a, new r7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getStocks() {
        d2 d2Var = this.f13789a;
        return new w1(d2Var, d2Var.f13753a, new j9());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getStressConfig() {
        d2 d2Var = this.f13789a;
        return new a1(d2Var, d2Var.f13753a, new s7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getTarget() {
        d2 d2Var = this.f13789a;
        return new z1(d2Var, d2Var.f13753a, new y6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getTargetOnOff() {
        return new v8(this.f13789a.f13753a, new z6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getUnit() {
        d2 d2Var = this.f13789a;
        return new b2(d2Var, d2Var.f13753a, new c7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getVersionInfo() {
        return new v8(this.f13789a.f13753a, new a8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWashConfig() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new c1(d2Var, a0Var, new i8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWeatherOnOff() {
        d2 d2Var = this.f13789a;
        return new k0(d2Var, d2Var.f13753a, new o7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWorkoutHeartRateConfig() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new g1(d2Var, a0Var, new k8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWorkoutMode() {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        return new v0(d2Var, a0Var, new t8());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWorkoutSupportList() {
        d2 d2Var = this.f13789a;
        return new y1(d2Var, d2Var.f13753a, new t7());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand getWorldClocks() {
        d2 d2Var = this.f13789a;
        return new u1(d2Var, d2Var.f13753a, new rb());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand messageReminder(String str, String str2, String str3, int i) {
        d2 d2Var = this.f13789a;
        if (i == 1 && d2Var.f13753a.a(33687816)) {
            d2Var.f13753a.E = str2;
        } else {
            d2Var.f13753a.E = null;
        }
        v8 v8Var = new v8(d2Var.f13753a, l6.a(str, str2, str3, i));
        v8Var.b = 1;
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand querySportStatus() {
        d2 d2Var = this.f13789a;
        return new t1(d2Var, d2Var.f13753a, new v6());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand reboot() {
        return new v8(this.f13789a.f13753a, new y8((byte) -16, (byte) 1, 1));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand remindCall(String str, String str2) {
        d2 d2Var = this.f13789a;
        boolean a2 = d2Var.f13753a.a(34146306);
        if (d2Var.f13753a.a(33687816)) {
            d2Var.f13753a.E = str2;
        }
        if (d2Var.f13753a.a(34147088)) {
            a0 a0Var = d2Var.f13753a;
            ByteBuffer a3 = l6.a(str, str2, a2);
            y8 y8Var = new y8((byte) 5, (byte) 1);
            y8Var.b(a3.array().length).put(a3.array());
            g5 g5Var = new g5(a0Var, y8Var);
            g5Var.b = 1;
            return g5Var;
        }
        a0 a0Var2 = d2Var.f13753a;
        ByteBuffer a4 = l6.a(str, str2, a2);
        w8 w8Var = new w8((byte) 5, (byte) 1);
        w8Var.e = a4.array();
        v8 v8Var = new v8(a0Var2, w8Var);
        v8Var.b = 1;
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand reset() {
        return new v8(this.f13789a.f13753a, new y8((byte) -16, (byte) 4));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setAlarms(List list) {
        return new g5(this.f13789a.f13753a, new y5(list));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setAppMenuStyle(TGAppMenuStyle tGAppMenuStyle) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8(2824);
        y8Var.b(1).put((byte) tGAppMenuStyle.getStyle());
        return new s0(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setAppMenus(List list) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 11, (byte) 4);
        ByteBuffer b = y8Var.b(list.size() + 1);
        b.put((byte) (list.size() & 255));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TGAppMenu tGAppMenu = (TGAppMenu) it.next();
            b.put((byte) ((tGAppMenu.isVisible() ? 128 : 0) | (tGAppMenu.getId() & 127)));
        }
        return new u0(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setBrightnessConfig(TGBrightnessConfig tGBrightnessConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 50);
        y8Var.b(2).put((byte) tGBrightnessConfig.getLevel()).put((byte) tGBrightnessConfig.getMode());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setCameraOnOff(boolean z) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 47);
        y8Var.b(18).put(z ? (byte) 1 : (byte) 0);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setCity(String str) {
        d2 d2Var = this.f13789a;
        d2Var.getClass();
        byte[] bArr = new byte[0];
        if (!TextUtils.isEmpty(str)) {
            bArr = str.getBytes();
        }
        if (d2Var.f13753a.i()) {
            a0 a0Var = d2Var.f13753a;
            y8 y8Var = new y8((byte) 10, (byte) 5);
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 1);
            allocate.put((byte) bArr.length);
            allocate.put(bArr);
            y8Var.b(bArr.length + 1).put((byte) bArr.length).put(bArr);
            return new g5(a0Var, y8Var);
        } else if (d2Var.f13753a.a(34017026)) {
            a0 a0Var2 = d2Var.f13753a;
            w8 w8Var = new w8((byte) 10, (byte) 3);
            ByteBuffer allocate2 = ByteBuffer.allocate(bArr.length + 1);
            allocate2.put((byte) bArr.length);
            allocate2.put(bArr);
            w8Var.e = allocate2.array();
            return new v8(a0Var2, w8Var);
        } else {
            a0 a0Var3 = d2Var.f13753a;
            int min = Math.min(bArr.length, 17);
            y8 y8Var2 = new y8((byte) 10, (byte) 2);
            ByteBuffer b = y8Var2.b(18);
            b.put((byte) min);
            b.put(bArr, 0, min);
            return new v8(a0Var3, y8Var2);
        }
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setContacts(List list) {
        d2 d2Var = this.f13789a;
        return new r1(d2Var, d2Var.f13753a, new g2(list, d2Var.f13753a.a(34146306)));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setEventReminders(List list) {
        d2 d2Var = this.f13789a;
        return new o1(d2Var, d2Var.f13753a, new l3(list, d2Var.f13753a.a(34146306)));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setFindPhoneOnOff(boolean z, int i) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 38);
        y8Var.b(18).put((byte) (z ? 170 : 85));
        y8Var.b(18).put((byte) i);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setHeartRateMonitoringMode(TGHeartRateMonitoringModeConfig tGHeartRateMonitoringModeConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 37);
        y8Var.b(7).put((byte) tGHeartRateMonitoringModeConfig.getMode()).put(tGHeartRateMonitoringModeConfig.isHasRange() ? (byte) 1 : (byte) 0).put((byte) tGHeartRateMonitoringModeConfig.getStartHour()).put((byte) tGHeartRateMonitoringModeConfig.getStartMinute()).put((byte) tGHeartRateMonitoringModeConfig.getStopHour()).put((byte) tGHeartRateMonitoringModeConfig.getStopMinute()).put((byte) tGHeartRateMonitoringModeConfig.getInterval());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setHeartRateRange(TGHeartRateRangeConfig tGHeartRateRangeConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 36);
        y8Var.b(12).put(new byte[]{0, 0, 0}).put((byte) tGHeartRateRangeConfig.getMaxHr()).put((byte) tGHeartRateRangeConfig.getRange1()).put((byte) tGHeartRateRangeConfig.getRange2()).put((byte) tGHeartRateRangeConfig.getRange3()).put((byte) tGHeartRateRangeConfig.getRange4()).put((byte) tGHeartRateRangeConfig.getRange5()).put((byte) tGHeartRateRangeConfig.getMinHr()).put(tGHeartRateRangeConfig.isEnableMax() ? (byte) 1 : (byte) 0).put(tGHeartRateRangeConfig.isEnableMin() ? (byte) 1 : (byte) 0);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setMusicOnOff(boolean z) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 42);
        y8Var.b(18).put((byte) (z ? 170 : 85));
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setNightMode(TGNightModeConfig tGNightModeConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 49);
        y8Var.b(5).put((byte) (tGNightModeConfig.isOn() ? 170 : 85)).put((byte) tGNightModeConfig.getStartHour()).put((byte) tGNightModeConfig.getStartMinute()).put((byte) tGNightModeConfig.getStopHour()).put((byte) tGNightModeConfig.getStopMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setNotDisturbMode(TGNotDisturbConfig tGNotDisturbConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 41);
        y8Var.b(5).put((byte) (tGNotDisturbConfig.isOn() ? 170 : 85)).put((byte) tGNotDisturbConfig.getStartHour()).put((byte) tGNotDisturbConfig.getStartMinute()).put((byte) tGNotDisturbConfig.getStopHour()).put((byte) tGNotDisturbConfig.getStopMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setPhysiologicalCycle(TGPhysiologicalCycle tGPhysiologicalCycle) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 19);
        ByteBuffer b = y8Var.b(18);
        b.put(tGPhysiologicalCycle.isEnable() ? (byte) 1 : (byte) 0);
        Date lastDate = tGPhysiologicalCycle.getLastDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastDate);
        b.putShort((short) calendar.get(1));
        Date lastDate2 = tGPhysiologicalCycle.getLastDate();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(lastDate2);
        b.put((byte) (calendar2.get(2) + 1));
        Date lastDate3 = tGPhysiologicalCycle.getLastDate();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(lastDate3);
        b.put((byte) calendar3.get(5));
        b.put((byte) tGPhysiologicalCycle.getMenstrualDuration());
        b.put((byte) tGPhysiologicalCycle.getMenstrualCycleDays());
        b.put((byte) tGPhysiologicalCycle.getRemindMenstrual());
        b.put((byte) tGPhysiologicalCycle.getRemindOvulation());
        b.put((byte) tGPhysiologicalCycle.getRemindHour());
        b.put((byte) tGPhysiologicalCycle.getRemindMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setProfile(TGProfile tGProfile) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 16);
        ByteBuffer put = y8Var.b(8).put((byte) tGProfile.getHeight()).putShort((short) tGProfile.getWeight()).put((byte) tGProfile.getGender());
        Date birthday = tGProfile.getBirthday();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        ByteBuffer putShort = put.putShort((short) calendar.get(1));
        Date birthday2 = tGProfile.getBirthday();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(birthday2);
        ByteBuffer put2 = putShort.put((byte) (calendar2.get(2) + 1));
        Date birthday3 = tGProfile.getBirthday();
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(birthday3);
        put2.put((byte) calendar3.get(5));
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setQuickCards(List list) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 11, (byte) 2);
        ByteBuffer b = y8Var.b(list.size() + 1);
        b.put((byte) (list.size() & 255));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TGQuickCard tGQuickCard = (TGQuickCard) it.next();
            b.put((byte) ((tGQuickCard.isVisible() ? 128 : 0) | (tGQuickCard.getId() & 127)));
        }
        return new q0(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setRaiseWrist(TGRaiseWristConfig tGRaiseWristConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 40);
        y8Var.b(7).put((byte) (tGRaiseWristConfig.isOn() ? 170 : 85)).put((byte) 0).put(tGRaiseWristConfig.isHasRange() ? (byte) 1 : (byte) 0).put((byte) tGRaiseWristConfig.getStartHour()).put((byte) tGRaiseWristConfig.getStartMinute()).put((byte) tGRaiseWristConfig.getStopHour()).put((byte) tGRaiseWristConfig.getStopMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setRemindDrinking(TGRemindDrinking tGRemindDrinking) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 96);
        y8Var.b(13).put((byte) ((tGRemindDrinking.getRepeat() & 1) == 1 ? 170 : 85)).put((byte) tGRemindDrinking.getStartHour()).put((byte) tGRemindDrinking.getStartMinute()).put((byte) tGRemindDrinking.getStopHour()).put((byte) tGRemindDrinking.getStopMinute()).put((byte) (tGRemindDrinking.getRepeat() & 254)).putShort((short) tGRemindDrinking.getInterval()).put(tGRemindDrinking.isNoontimeOnOff() ? (byte) 1 : (byte) 0).put((byte) tGRemindDrinking.getNoontimeStartHour()).put((byte) tGRemindDrinking.getNoontimeStartMinute()).put((byte) tGRemindDrinking.getNoontimeStopHour()).put((byte) tGRemindDrinking.getNoontimeStopMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setSedentary(TGSedentaryConfig tGSedentaryConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 32);
        y8Var.b(18).put((byte) tGSedentaryConfig.getStartHour()).put((byte) tGSedentaryConfig.getStartMinute()).put((byte) tGSedentaryConfig.getStopHour()).put((byte) tGSedentaryConfig.getStopMinute()).putShort((short) tGSedentaryConfig.getInterval()).put((byte) (tGSedentaryConfig.getRepeat() & 255)).put(tGSedentaryConfig.isNoontimeOnOff() ? (byte) 1 : (byte) 0).put((byte) tGSedentaryConfig.getNoontimeStartHour()).put((byte) tGSedentaryConfig.getNoontimeStartMinute()).put((byte) tGSedentaryConfig.getNoontimeStopHour()).put((byte) tGSedentaryConfig.getNoontimeStopMinute());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setSosConfig(TGSosConfig tGSosConfig) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 9);
        int nameLen = tGSosConfig.getNameLen();
        int phoneNumLen = tGSosConfig.getPhoneNumLen();
        y8Var.b(nameLen + 2 + phoneNumLen).put((byte) nameLen).put((byte) phoneNumLen).put(tGSosConfig.getNameBytes()).put(tGSosConfig.getPhoneNumBytes());
        return new f1(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setSpo2Config(TGSpo2Config tGSpo2Config) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 4);
        y8Var.b(7).put((byte) tGSpo2Config.getMode()).put(tGSpo2Config.isHasRange() ? (byte) 1 : (byte) 0).put((byte) tGSpo2Config.getStartHour()).put((byte) tGSpo2Config.getStartMinute()).put((byte) tGSpo2Config.getStopHour()).put((byte) tGSpo2Config.getStopMinute()).put((byte) tGSpo2Config.getInterval());
        return new z0(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setStocks(List list) {
        d2 d2Var = this.f13789a;
        return new v1(d2Var, d2Var.f13753a, new k9(list));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setStressConfig(TGStressConfig tGStressConfig) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 5);
        y8Var.b(7).put((byte) tGStressConfig.getMode()).put(tGStressConfig.isHasRange() ? (byte) 1 : (byte) 0).put((byte) tGStressConfig.getStartHour()).put((byte) tGStressConfig.getStartMinute()).put((byte) tGStressConfig.getStopHour()).put((byte) tGStressConfig.getStopMinute()).put((byte) tGStressConfig.getInterval());
        return new b1(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setTarget(int i, int i2) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 3);
        y8Var.b(5).put((byte) i).putInt(i2);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setTargetOnOff(boolean z) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) -23);
        y8Var.b(18).put(z ? (byte) 1 : (byte) 0);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setUnit(TGUnitConfig tGUnitConfig) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 17);
        y8Var.b(18).put((byte) tGUnitConfig.getDistance()).put((byte) tGUnitConfig.getWeight()).put((byte) tGUnitConfig.getTemp()).put((byte) tGUnitConfig.getStrideWalk()).put((byte) tGUnitConfig.getLanguage()).put((byte) tGUnitConfig.getTimeMode()).put((byte) tGUnitConfig.getStrideRun());
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWashConfig(TGWashConfig tGWashConfig) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 6);
        y8Var.b(7).put((byte) (tGWashConfig.isEnable() ? 170 : 85)).put((byte) tGWashConfig.getStartHour()).put((byte) tGWashConfig.getStartMinute()).put((byte) tGWashConfig.getStopHour()).put((byte) tGWashConfig.getStopMinute()).putShort((short) (tGWashConfig.getInterval() & 65535));
        return new d1(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWeather(TGWeather tGWeather) {
        TGCommand v8Var;
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.i()) {
            char c = d2Var.f13753a.a(TGFunctions.FUNC_WEATHER_SUPPORT_AQI) ? (char) 2 : (char) 1;
            a0 a0Var = d2Var.f13753a;
            int i = c >= 2 ? 16 : 11;
            y8 y8Var = new y8((byte) 10, (byte) 4);
            List<TGWeather.FutureWeather> futureWeatherList = tGWeather.getFutureWeatherList();
            ByteBuffer b = y8Var.b(((futureWeatherList != null ? futureWeatherList.size() : 0) * 3) + i);
            b.put((byte) tGWeather.getType());
            b.put((byte) tGWeather.getCurrentTemp());
            b.put((byte) tGWeather.getMaxTemp());
            b.put((byte) tGWeather.getMinTemp());
            b.put((byte) tGWeather.getHumidity());
            b.put((byte) tGWeather.getUv());
            b.put((byte) tGWeather.getPm());
            b.put((byte) tGWeather.getSunriseHour());
            b.put((byte) tGWeather.getSunriseMinute());
            b.put((byte) tGWeather.getSunsetHour());
            b.put((byte) tGWeather.getSunsetMinute());
            if (c >= 2) {
                b.put((byte) tGWeather.getAqi());
                b.putInt(0);
            }
            if (futureWeatherList != null) {
                for (TGWeather.FutureWeather futureWeather : futureWeatherList) {
                    b.put((byte) futureWeather.getType());
                    b.put((byte) futureWeather.getMaxTemp());
                    b.put((byte) futureWeather.getMinTemp());
                }
            }
            v8Var = new g5(a0Var, y8Var);
        } else {
            a0 a0Var2 = d2Var.f13753a;
            y8 y8Var2 = new y8((byte) 10, (byte) 1);
            ByteBuffer b2 = y8Var2.b(18);
            b2.put((byte) tGWeather.getType());
            b2.put((byte) tGWeather.getCurrentTemp());
            b2.put((byte) tGWeather.getMaxTemp());
            b2.put((byte) tGWeather.getMinTemp());
            b2.put((byte) tGWeather.getHumidity());
            b2.put((byte) tGWeather.getUv());
            b2.put((byte) tGWeather.getPm());
            List<TGWeather.FutureWeather> futureWeatherList2 = tGWeather.getFutureWeatherList();
            if (futureWeatherList2 != null) {
                for (int i2 = 0; i2 < Math.min(3, futureWeatherList2.size()); i2++) {
                    TGWeather.FutureWeather futureWeather2 = futureWeatherList2.get(i2);
                    b2.put((byte) futureWeather2.getType());
                    b2.put((byte) futureWeather2.getMaxTemp());
                    b2.put((byte) futureWeather2.getMinTemp());
                }
            }
            v8Var = new v8(a0Var2, y8Var2);
        }
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWeatherOnOff(boolean z) {
        a0 a0Var = this.f13789a.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 45);
        y8Var.b(18).put(z ? (byte) 1 : (byte) 0);
        return new v8(a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWorkoutHeartRateConfig(TGWorkoutHeartRateConfig tGWorkoutHeartRateConfig) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 8);
        y8Var.b(2).put((byte) (tGWorkoutHeartRateConfig.isEnableAlert() ? 170 : 85)).put((byte) tGWorkoutHeartRateConfig.getMaxHr());
        return new i1(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWorkoutMode(TGWorkoutMode tGWorkoutMode) {
        List<Integer> workouts;
        int indexOf;
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.d() == 534 && (workouts = tGWorkoutMode.getWorkouts()) != null && (indexOf = workouts.indexOf(150)) != -1) {
            workouts.remove(indexOf);
            workouts.add(indexOf, 47);
        }
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        s8 s8Var = new s8();
        List<Integer> workouts2 = tGWorkoutMode.getWorkouts();
        ByteBuffer b = s8Var.b(workouts2.size() + 1);
        b.put((byte) (workouts2.size() & 255));
        for (Integer num : workouts2) {
            b.put((byte) (num.intValue() & 255));
        }
        return new x0(d2Var, a0Var, s8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setWorldClocks(List list) {
        d2 d2Var = this.f13789a;
        return new s1(d2Var, d2Var.f13753a, new sb(list));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand shutdown() {
        return new v8(this.f13789a.f13753a, new y8((byte) -16, (byte) 3, 1));
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand stopFindPhone() {
        v8 v8Var = new v8(this.f13789a.f13753a, new y8((byte) 6, (byte) 7));
        v8Var.b = 1;
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncAlarms() {
        return new g5(this.f13789a.f13753a, new r5());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncAppGpsData(Location location, int i) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        h8 h8Var = new h8((byte) 5, (byte) 5, 2);
        if (h8Var.d == null) {
            ByteBuffer allocate = ByteBuffer.allocate(18);
            h8Var.d = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        ByteBuffer byteBuffer = h8Var.d;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) (i & 255));
        byteBuffer.putDouble(location.getLongitude());
        byteBuffer.putDouble(location.getLatitude());
        byteBuffer.put((byte) 0);
        return new k1(d2Var, a0Var, h8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncAppGpsStatus(int i, int i2) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        h8 h8Var = new h8((byte) 7, (byte) 3, 2);
        if (h8Var.d == null) {
            ByteBuffer allocate = ByteBuffer.allocate(2);
            h8Var.d = allocate;
            allocate.order(ByteOrder.LITTLE_ENDIAN);
        }
        h8Var.d.put((byte) i).put((byte) i2);
        return new j1(d2Var, a0Var, h8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncCallStatus(int i) {
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.a(33687816)) {
            d2Var.f13753a.E = null;
        }
        v8 v8Var = new v8(d2Var.f13753a, new k6((byte) i));
        v8Var.b = 1;
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncMessage(TGMessage tGMessage) {
        d2 d2Var = this.f13789a;
        d2Var.getClass();
        return d2Var.a(tGMessage.getName(), tGMessage.getPhoneNumber(), tGMessage.getContent(), tGMessage.getType());
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncMusic(TGMusicInfo tGMusicInfo) {
        d2 d2Var = this.f13789a;
        d2Var.getClass();
        String musicName = tGMusicInfo.getMusicName();
        g5 g5Var = new g5(d2Var.f13753a, new z5(tGMusicInfo.isPlaying(), musicName, tGMusicInfo.getMaxVol(), tGMusicInfo.getCurVol()));
        g5Var.b = 1;
        return g5Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncQuickReply(List list) {
        d2 d2Var = this.f13789a;
        d2Var.getClass();
        if (list != null && list.size() > 5) {
            TGLogger.w("Up to 5 messages, size=" + list.size());
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        if (list == null || list.size() <= 0) {
            int i2 = 0;
            while (i2 < 5) {
                TGQuickReply tGQuickReply = new TGQuickReply();
                i2++;
                tGQuickReply.setMsgIndex(i2);
                tGQuickReply.setContent("");
                arrayList.add(tGQuickReply);
            }
        } else {
            while (i < list.size()) {
                TGQuickReply tGQuickReply2 = (TGQuickReply) list.get(i);
                i++;
                tGQuickReply2.setMsgIndex(i);
                arrayList.add(tGQuickReply2);
            }
            i = arrayList.size();
        }
        return new n1(d2Var, arrayList, i);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncTime() {
        d2 d2Var = this.f13789a;
        boolean i = d2Var.f13753a.i();
        boolean z = i && d2Var.f13753a.c < 787;
        a0 a0Var = d2Var.f13753a;
        HashMap hashMap = u8.f13829a;
        Calendar calendar = Calendar.getInstance();
        l8 l8Var = new l8();
        ByteBuffer b = l8Var.b(14);
        b.putShort((short) calendar.get(1));
        b.put((byte) (calendar.get(2) + 1));
        b.put((byte) calendar.get(5));
        b.put((byte) calendar.get(11));
        b.put((byte) calendar.get(12));
        b.put((byte) calendar.get(13));
        int i2 = calendar.get(7) - 2;
        if (i2 < 0) {
            i2 = 6;
        }
        b.put((byte) i2);
        b.putInt(0);
        if (i) {
            int rawOffset = calendar.getTimeZone().getRawOffset() / 60000;
            if (z && rawOffset <= 0) {
                rawOffset += 1440;
            }
            b.putShort((short) rawOffset);
        } else {
            b.putShort((short) 0);
        }
        v8 v8Var = new v8(a0Var, l8Var);
        v8Var.b = 1;
        return v8Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand unbind(boolean z) {
        TGCommand w0Var;
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.d() == 1) {
            a0 a0Var = d2Var.f13753a;
            y8 y8Var = new y8((byte) 11, (byte) 2);
            y8Var.b(18).put((byte) (z ? 170 : 85));
            w0Var = new l0(d2Var, a0Var, y8Var);
        } else {
            a0 a0Var2 = d2Var.f13753a;
            y8 y8Var2 = new y8((byte) 4, (byte) 2);
            y8Var2.b(18).put((byte) (z ? 170 : 85));
            w0Var = new w0(d2Var, a0Var2, y8Var2);
        }
        return w0Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand updateStock(TGStock tGStock) {
        d2 d2Var = this.f13789a;
        a0 a0Var = d2Var.f13753a;
        y8 y8Var = new y8((byte) 3, (byte) 7);
        int codeLen = tGStock.getCodeLen();
        int nameLen = tGStock.getNameLen();
        int i = codeLen + nameLen + 14;
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.putInt((int) (tGStock.getCurrent() * 1000.0f));
        allocate.putInt((int) (tGStock.getFluctuates() * 1000.0f));
        allocate.putInt((int) (tGStock.getPercent() * 10000.0f));
        allocate.put((byte) (codeLen & 255));
        if (codeLen > 0) {
            allocate.put(tGStock.getCodeBytes());
        }
        allocate.put((byte) (nameLen & 255));
        if (nameLen > 0) {
            allocate.put(tGStock.getNameBytes());
        }
        y8Var.b(i).put(allocate.array());
        return new x1(d2Var, a0Var, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncMessage(String str, String str2, String str3, int i) {
        return this.f13789a.a(str, str2, str3, i);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setTarget(int i, int i2, int i3, int i4, int i5) {
        d2 d2Var = this.f13789a;
        d2Var.getClass();
        HashMap hashMap = u8.f13829a;
        y8 y8Var = new y8((byte) 3, (byte) 3);
        y8Var.b(15).put((byte) 4).putInt(i).put((byte) i2).put((byte) i3).putInt(i4).putInt(i5);
        return new v8(d2Var.f13753a, y8Var);
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand syncMusic(String str, boolean z, int i, int i2) {
        g5 g5Var = new g5(this.f13789a.f13753a, new z5(z, str, i, i2));
        g5Var.b = 1;
        return g5Var;
    }

    @Override // com.touchgui.sdk.TGCommandBuilder
    public final TGCommand setTarget(TGTarget tGTarget) {
        d2 d2Var = this.f13789a;
        if (d2Var.f13753a.i()) {
            HashMap hashMap = u8.f13829a;
            y8 y8Var = new y8((byte) 3, (byte) 3);
            y8Var.b(23).put((byte) 4).putInt(tGTarget.getStep()).put((byte) tGTarget.getSleepHour()).put((byte) tGTarget.getSleepMinute()).putInt(tGTarget.getCalorie()).putInt(tGTarget.getDistance()).putInt(tGTarget.getWorkoutTime()).putInt(tGTarget.getStandingTime());
            return new g5(d2Var.f13753a, y8Var);
        }
        int step = tGTarget.getStep();
        int sleepHour = tGTarget.getSleepHour();
        int sleepMinute = tGTarget.getSleepMinute();
        int calorie = tGTarget.getCalorie();
        int distance = tGTarget.getDistance();
        HashMap hashMap2 = u8.f13829a;
        y8 y8Var2 = new y8((byte) 3, (byte) 3);
        y8Var2.b(15).put((byte) 4).putInt(step).put((byte) sleepHour).put((byte) sleepMinute).putInt(calorie).putInt(distance);
        return new v8(d2Var.f13753a, y8Var2);
    }
}
