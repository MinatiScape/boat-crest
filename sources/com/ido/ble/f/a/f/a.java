package com.ido.ble.f.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
import com.ido.ble.common.k;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.BodyPowerSwitch;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.HeartRateSmartMode;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NightTemperatureMonitoringPara;
import com.ido.ble.protocol.model.NoisePara;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.RespiratoryRate;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class a extends d {
    private static final String A = "weatherSwitch";
    private static final String B = "quickSportMode";
    private static final String C = "dialPlateMode";
    private static final String D = "shortCut";
    private static final String E = "SleepMonitoring";
    private static final String F = "NightTemperatureMonitoring";
    private static final String G = "NoisePara";
    private static final String H = "FitnessGuidance";
    private static final String I = "lastSyncHealthDataTime";
    private static final String J = "lastSyncHealthDataDeviceUniqueId";
    private static final String K = "offsetSportIndex";
    private static final String L = "offsetHeartRateIndex";
    private static final String M = "offsetBloodPressuredIndex";
    private static final String N = "setConfigGpsPara";
    @Deprecated
    private static final String O = "screenBrightness";
    private static final String P = "screenBrightness_config";
    private static final String Q = "isNeedCollectRebootLog";
    private static final String R = "menstrual";
    private static final String S = "menstrual_remind";
    private static final String T = "calorie_distance_goal";
    private static final String U = "sport_mode_sort";
    private static final String V = "sport_mode_sort_v3";
    private static final String W = "breatheTrain";
    private static final String X = "walkReminder";
    private static final String Y = "spo2Param";
    private static final String Z = "activitySwitch";
    private static final String a0 = "drinkWaterReminder";
    private static final String b0 = "menuList";
    private static final String c0 = "pressurePara";
    private static final String d = "device_";
    private static final String d0 = "device_info";
    private static final String e = "user_info";
    private static final String e0 = "bt_mac_address";
    private static final String f = "supportFunctionTable";
    private static final String f0 = "key_rate_switch";
    private static final String g = "alarms";
    private static final String g0 = "key_bodypower_switch";
    private static final String h = "goal";
    private static final String h0 = "key_noticeremind_switch";
    private static final String i = "handWearMode";
    private static a i0 = null;
    private static final String j = "bloodPressureAdjustPara";
    private static final String j0 = "default";
    private static final String k = "units";
    private static final String l = "basicInfo";
    private static final String m = "AntiLostMode";
    private static final String n = "AntiLostPara";
    private static final String o = "LongSit";
    private static final String p = "findPhoneSwitch";
    private static final String q = "heartRateMode";
    private static final String r = "heartRateModeV3";
    private static final String s = "heartRateSmartMode";
    private static final String t = "heartRateInterval";
    private static final String u = "noticeReminderSwitchStatus";
    private static final String v = "upHandGesture";
    private static final String w = "notDisturb";
    private static final String x = "musicSwitch";
    private static final String y = "displayMode";
    private static final String z = "oneKeySos";
    private String c;

    /* renamed from: com.ido.ble.f.a.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0598a extends TypeToken<List<Alarm>> {
        public C0598a() {
        }
    }

    private void b(BLEDevice bLEDevice) {
        BasicInfo h2;
        if (bLEDevice == null || bLEDevice.mDeviceId > 0 || (h2 = h()) == null) {
            return;
        }
        bLEDevice.mDeviceId = h2.deivceId;
    }

    public static a f(String str) {
        a aVar = new a();
        aVar.a(e.a(), str);
        LogTool.d("MultiDeviceSPDataUtils", "[createInstanceWithMacAddress] sp_name = " + aVar.c);
        return aVar;
    }

    public static a g0() {
        if (i0 == null) {
            BLEDevice c = b.e().c();
            i0 = new a();
            if (c == null || TextUtils.isEmpty(c.mDeviceAddress)) {
                i0.a(e.a(), "default");
            } else {
                i0.a(e.a(), c.mDeviceAddress);
            }
            LogTool.d("DeviceSharedPreferences", "sp_name = " + i0.c);
        }
        return i0;
    }

    @Deprecated
    private int h0() {
        return a(O, -1);
    }

    public int A() {
        return a(L, 0);
    }

    public HeartRateSmartMode B() {
        String a2 = a(s, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (HeartRateSmartMode) new Gson().fromJson(a2, (Class<Object>) HeartRateSmartMode.class);
    }

    public String C() {
        return a(J, "");
    }

    public long D() {
        return a(I, 0L);
    }

    public LongSit E() {
        String a2 = a(o, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (LongSit) new Gson().fromJson(a2, (Class<Object>) LongSit.class);
    }

    public Menstrual F() {
        return (Menstrual) a(R, Menstrual.class);
    }

    public MenstrualRemind G() {
        return (MenstrualRemind) a(S, MenstrualRemind.class);
    }

    public MenuList H() {
        return (MenuList) a(b0, MenuList.class);
    }

    public boolean I() {
        return a(x, false);
    }

    public NightTemperatureMonitoringPara J() {
        String a2 = a(F, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (NightTemperatureMonitoringPara) new Gson().fromJson(a2, (Class<Object>) NightTemperatureMonitoringPara.class);
    }

    public NoisePara K() {
        String a2 = a(G, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (NoisePara) new Gson().fromJson(a2, (Class<Object>) NoisePara.class);
    }

    public NotDisturbPara L() {
        String a2 = a(w, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (NotDisturbPara) new Gson().fromJson(a2, (Class<Object>) NotDisturbPara.class);
    }

    public NoticeReminderSwitchStatus M() {
        return (NoticeReminderSwitchStatus) a(h0, NoticeReminderSwitchStatus.class);
    }

    public boolean N() {
        return a(z, false);
    }

    public PressureParam O() {
        return (PressureParam) a(c0, PressureParam.class);
    }

    public QuickSportMode P() {
        String a2 = a(B, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (QuickSportMode) new Gson().fromJson(a2, (Class<Object>) QuickSportMode.class);
    }

    public RespiratoryRate Q() {
        return (RespiratoryRate) a(f0, RespiratoryRate.class);
    }

    public boolean R() {
        ScreenBrightness S2 = S();
        return S2 != null && S2.mode == 1;
    }

    public ScreenBrightness S() {
        int h02;
        ScreenBrightness screenBrightness = (ScreenBrightness) a(P, ScreenBrightness.class);
        if (screenBrightness != null || (h02 = h0()) <= 0) {
            return screenBrightness;
        }
        ScreenBrightness screenBrightness2 = new ScreenBrightness();
        screenBrightness2.level = h02;
        return screenBrightness2;
    }

    public ShortCut T() {
        String a2 = a(D, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (ShortCut) new Gson().fromJson(a2, (Class<Object>) ShortCut.class);
    }

    public SleepMonitoringPara U() {
        String a2 = a(E, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (SleepMonitoringPara) new Gson().fromJson(a2, (Class<Object>) SleepMonitoringPara.class);
    }

    public SPO2Param V() {
        return (SPO2Param) a(Y, SPO2Param.class);
    }

    public SportModeSort W() {
        return (SportModeSort) a(U, SportModeSort.class);
    }

    public SportModeSortV3 X() {
        return (SportModeSortV3) a(V, SportModeSortV3.class);
    }

    public int Y() {
        return a(K, 0);
    }

    public SupportFunctionInfo Z() {
        String a2 = a(f, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (SupportFunctionInfo) new Gson().fromJson(a2, (Class<Object>) SupportFunctionInfo.class);
    }

    public void a(int i2) {
        b(M, i2);
    }

    public void a(long j2) {
        b(I, j2);
    }

    @Override // com.ido.ble.common.d
    public void a(Context context, String str) {
        String str2 = d + str;
        this.c = str2;
        super.a(context, str2);
    }

    public void a(BLEDevice bLEDevice) {
        if (bLEDevice == null) {
            return;
        }
        b(bLEDevice);
        b(d0, k.a(bLEDevice));
    }

    public void a(ConfigGPS configGPS) {
        if (configGPS == null) {
            return;
        }
        b(N, new Gson().toJson(configGPS));
    }

    public void a(ActivitySwitch activitySwitch) {
        if (activitySwitch == null) {
            return;
        }
        b(Z, k.a(activitySwitch));
    }

    public void a(AntiLostMode antiLostMode) {
        if (antiLostMode == null) {
            return;
        }
        b(m, new Gson().toJson(antiLostMode));
    }

    public void a(BasicInfo basicInfo) {
        if (basicInfo == null) {
            return;
        }
        b(l, new Gson().toJson(basicInfo));
    }

    public void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        if (bloodPressureAdjustPara == null) {
            return;
        }
        b(j, new Gson().toJson(bloodPressureAdjustPara));
    }

    public void a(BodyPowerSwitch bodyPowerSwitch) {
        if (bodyPowerSwitch == null) {
            return;
        }
        b(g0, k.a(bodyPowerSwitch));
    }

    public void a(BreatheTrain breatheTrain) {
        if (breatheTrain == null) {
            return;
        }
        b(W, k.a(breatheTrain));
    }

    public void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        if (calorieAndDistanceGoal == null) {
            return;
        }
        b(T, k.a(calorieAndDistanceGoal));
    }

    public void a(DialPlate dialPlate) {
        if (dialPlate == null) {
            return;
        }
        b(C, new Gson().toJson(dialPlate));
    }

    public void a(DisplayMode displayMode) {
        if (displayMode == null) {
            return;
        }
        b(y, new Gson().toJson(displayMode));
    }

    public void a(DrinkWaterReminder drinkWaterReminder) {
        if (drinkWaterReminder == null) {
            return;
        }
        b(a0, k.a(drinkWaterReminder));
    }

    public void a(FitnessGuidance fitnessGuidance) {
        if (fitnessGuidance == null) {
            return;
        }
        b(H, new Gson().toJson(fitnessGuidance));
    }

    public void a(Goal goal) {
        if (goal == null) {
            return;
        }
        b("goal", new Gson().toJson(goal));
    }

    public void a(HandWearMode handWearMode) {
        if (handWearMode == null) {
            return;
        }
        b(i, new Gson().toJson(handWearMode));
    }

    public void a(HeartRateInterval heartRateInterval) {
        if (heartRateInterval == null) {
            return;
        }
        b(t, new Gson().toJson(heartRateInterval));
    }

    public void a(HeartRateMeasureMode heartRateMeasureMode) {
        if (heartRateMeasureMode == null) {
            return;
        }
        b(q, new Gson().toJson(heartRateMeasureMode));
    }

    public void a(HeartRateMeasureModeV3 heartRateMeasureModeV3) {
        if (heartRateMeasureModeV3 == null) {
            return;
        }
        b(r, new Gson().toJson(heartRateMeasureModeV3));
    }

    public void a(HeartRateSmartMode heartRateSmartMode) {
        if (heartRateSmartMode == null) {
            return;
        }
        b(s, new Gson().toJson(heartRateSmartMode));
    }

    public void a(LongSit longSit) {
        if (longSit == null) {
            return;
        }
        b(o, new Gson().toJson(longSit));
    }

    public void a(Menstrual menstrual) {
        if (menstrual == null) {
            return;
        }
        b(R, new Gson().toJson(menstrual));
    }

    public void a(MenstrualRemind menstrualRemind) {
        if (menstrualRemind == null) {
            return;
        }
        b(S, k.a(menstrualRemind));
    }

    public void a(MenuList menuList) {
        if (menuList == null) {
            return;
        }
        b(b0, k.a(menuList));
    }

    public void a(NightTemperatureMonitoringPara nightTemperatureMonitoringPara) {
        if (nightTemperatureMonitoringPara == null) {
            return;
        }
        b(F, new Gson().toJson(nightTemperatureMonitoringPara));
    }

    public void a(NoisePara noisePara) {
        if (noisePara == null) {
            return;
        }
        b(G, new Gson().toJson(noisePara));
    }

    public void a(NotDisturbPara notDisturbPara) {
        if (notDisturbPara == null) {
            return;
        }
        b(w, new Gson().toJson(notDisturbPara));
    }

    public void a(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        if (noticeReminderSwitchStatus == null) {
            return;
        }
        b(u, new Gson().toJson(noticeReminderSwitchStatus));
    }

    public void a(PressureParam pressureParam) {
        if (pressureParam == null) {
            return;
        }
        b(c0, k.a(pressureParam));
    }

    public void a(QuickSportMode quickSportMode) {
        if (quickSportMode == null) {
            return;
        }
        b(B, new Gson().toJson(quickSportMode));
    }

    public void a(RespiratoryRate respiratoryRate) {
        if (respiratoryRate == null) {
            return;
        }
        b(f0, k.a(respiratoryRate));
    }

    public void a(SPO2Param sPO2Param) {
        if (sPO2Param == null) {
            return;
        }
        b(Y, k.a(sPO2Param));
    }

    public void a(ScreenBrightness screenBrightness) {
        b(P, k.a(screenBrightness));
    }

    public void a(ShortCut shortCut) {
        if (shortCut == null) {
            return;
        }
        b(D, new Gson().toJson(shortCut));
    }

    public void a(SleepMonitoringPara sleepMonitoringPara) {
        if (sleepMonitoringPara == null) {
            return;
        }
        b(E, new Gson().toJson(sleepMonitoringPara));
    }

    public void a(SportModeSort sportModeSort) {
        if (sportModeSort == null) {
            return;
        }
        b(U, k.a(sportModeSort));
    }

    public void a(SportModeSortV3 sportModeSortV3) {
        if (sportModeSortV3 == null) {
            return;
        }
        b(V, k.a(sportModeSortV3));
    }

    public void a(SupportFunctionInfo supportFunctionInfo) {
        if (supportFunctionInfo == null) {
            return;
        }
        b(f, new Gson().toJson(supportFunctionInfo));
    }

    public void a(Units units) {
        if (units == null) {
            return;
        }
        b(k, new Gson().toJson(units));
    }

    public void a(UpHandGesture upHandGesture) {
        if (upHandGesture == null) {
            return;
        }
        b(v, new Gson().toJson(upHandGesture));
    }

    public void a(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        b(e, new Gson().toJson(userInfo));
    }

    public void a(WalkReminder walkReminder) {
        if (walkReminder == null) {
            return;
        }
        b(X, k.a(walkReminder));
    }

    public void a(List<Alarm> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        b(g, new Gson().toJson(list));
    }

    public void a(boolean z2) {
        b(p, z2);
    }

    public Units a0() {
        String a2 = a(k, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (Units) new Gson().fromJson(a2, (Class<Object>) Units.class);
    }

    public void b() {
        b(e0, "");
    }

    public void b(int i2) {
        b(L, i2);
    }

    public void b(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        if (noticeReminderSwitchStatus == null) {
            return;
        }
        b(h0, k.a(noticeReminderSwitchStatus));
    }

    public void b(boolean z2) {
        b(x, z2);
    }

    public UpHandGesture b0() {
        String a2 = a(v, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (UpHandGesture) new Gson().fromJson(a2, (Class<Object>) UpHandGesture.class);
    }

    public void c() {
        b(g);
        b("goal");
        b(i);
        b(j);
        b(k);
        b(l);
        b(m);
        b(n);
        b(o);
        b(p);
        b(q);
        b(t);
        b(v);
        b(w);
        b(x);
        b(y);
        b(z);
        b(A);
        b(B);
        b(C);
        b(D);
        b(E);
        b(I);
        b(J);
        b(K);
        b(L);
        b(M);
        b(N);
        b(O);
        b(Q);
        b(R);
        b(S);
        b(T);
        b(U);
        b(X);
        b(W);
        b(Y);
        b(Z);
        b(f0);
        b(h0);
    }

    public void c(int i2) {
        ScreenBrightness S2 = S();
        if (S2 == null) {
            S2 = new ScreenBrightness();
        }
        S2.level = i2;
        S2.opera = 1;
        a(S2);
    }

    public void c(String str) {
        b(e0, str);
    }

    public void c(boolean z2) {
        b(z, z2);
    }

    public UserInfo c0() {
        String a2 = a(e, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (UserInfo) new Gson().fromJson(a2, (Class<Object>) UserInfo.class);
    }

    public ActivitySwitch d() {
        return (ActivitySwitch) a(Z, ActivitySwitch.class);
    }

    public void d(int i2) {
        b(K, i2);
    }

    public void d(String str) {
        b(J, str);
    }

    public void d(boolean z2) {
        b(A, z2);
    }

    public WalkReminder d0() {
        return (WalkReminder) a(X, WalkReminder.class);
    }

    public List<Alarm> e() {
        String a2 = a(g, "");
        return !TextUtils.isEmpty(a2) ? (List) new Gson().fromJson(a2, new C0598a().getType()) : new ArrayList();
    }

    public void e(String str) {
        i0.a(e.a(), str);
        LogTool.d("MultiDeviceSPDataUtils", "[switchToDevice] sp_name = " + i0.c);
    }

    public void e(boolean z2) {
        b(Q, z2);
    }

    public boolean e0() {
        return a(A, false);
    }

    public Map<Integer, Alarm> f() {
        List<Alarm> e2 = e();
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < e2.size(); i2++) {
            hashMap.put(Integer.valueOf(e2.get(i2).getAlarmId()), e2.get(i2));
        }
        return hashMap;
    }

    public boolean f0() {
        return a(Q, false);
    }

    public AntiLostMode g() {
        return (AntiLostMode) a(m, AntiLostMode.class);
    }

    public BasicInfo h() {
        return (BasicInfo) a(l, BasicInfo.class);
    }

    public BloodPressureAdjustPara i() {
        return (BloodPressureAdjustPara) a(j, BloodPressureAdjustPara.class);
    }

    public int j() {
        return a(M, 0);
    }

    public BodyPowerSwitch k() {
        return (BodyPowerSwitch) a(g0, BodyPowerSwitch.class);
    }

    public BreatheTrain l() {
        return (BreatheTrain) a(W, BreatheTrain.class);
    }

    public String m() {
        return a(e0, "");
    }

    public CalorieAndDistanceGoal n() {
        return (CalorieAndDistanceGoal) a(T, CalorieAndDistanceGoal.class);
    }

    public BLEDevice o() {
        BLEDevice bLEDevice = (BLEDevice) a(d0, BLEDevice.class);
        b(bLEDevice);
        return bLEDevice;
    }

    public DialPlate p() {
        String a2 = a(C, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (DialPlate) new Gson().fromJson(a2, (Class<Object>) DialPlate.class);
    }

    public DisplayMode q() {
        String a2 = a(y, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (DisplayMode) new Gson().fromJson(a2, (Class<Object>) DisplayMode.class);
    }

    public DrinkWaterReminder r() {
        return (DrinkWaterReminder) a(a0, DrinkWaterReminder.class);
    }

    public boolean s() {
        return a(p, true);
    }

    public FitnessGuidance t() {
        String a2 = a(H, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (FitnessGuidance) new Gson().fromJson(a2, (Class<Object>) FitnessGuidance.class);
    }

    public Goal u() {
        String a2 = a("goal", "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (Goal) new Gson().fromJson(a2, (Class<Object>) Goal.class);
    }

    public ConfigGPS v() {
        ConfigGPS configGPS = (ConfigGPS) a(N, ConfigGPS.class);
        if (configGPS != null) {
            ConfigGPS configGPS2 = new ConfigGPS();
            configGPS2.gnsValue = configGPS.gnsValue;
            configGPS2.cycleMS = configGPS.cycleMS;
            configGPS2.operationMode = configGPS.operationMode;
            configGPS2.startMode = configGPS.startMode;
            return configGPS2;
        }
        return null;
    }

    public HandWearMode w() {
        return (HandWearMode) a(i, HandWearMode.class);
    }

    public HeartRateInterval x() {
        String a2 = a(t, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (HeartRateInterval) new Gson().fromJson(a2, (Class<Object>) HeartRateInterval.class);
    }

    public HeartRateMeasureMode y() {
        String a2 = a(q, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (HeartRateMeasureMode) new Gson().fromJson(a2, (Class<Object>) HeartRateMeasureMode.class);
    }

    public HeartRateMeasureModeV3 z() {
        String a2 = a(r, "");
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return (HeartRateMeasureModeV3) new Gson().fromJson(a2, (Class<Object>) HeartRateMeasureModeV3.class);
    }
}
