package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import android.text.format.DateFormat;
import com.ido.ble.BLESpecialAPI;
import com.ido.ble.LocalDataManager;
import com.ido.ble.gps.model.ConfigGPS;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BindAuth;
import com.ido.ble.protocol.model.BodyPowerSwitch;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.ble.protocol.model.Goal;
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
import com.szabh.smable3.entity.Languages;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes11.dex */
final class s {
    private static void A() {
        NoisePara K = com.ido.ble.f.a.f.a.g0().K();
        if (K == null) {
            K = new NoisePara();
        }
        com.ido.ble.i.a.a.a(K);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setnoisePara:" + K);
    }

    private static void B() {
        NotDisturbPara L = com.ido.ble.f.a.f.a.g0().L();
        if (L == null) {
            L = new NotDisturbPara();
            L.onOFf = 85;
        }
        com.ido.ble.i.a.a.a(L);
    }

    private static void C() {
        NoticeReminderSwitchStatus M = com.ido.ble.f.a.f.a.g0().M();
        if (M == null) {
            M = new NoticeReminderSwitchStatus();
            M.notify_switch = 136;
            M.call_switch = 170;
        }
        com.ido.ble.i.a.a.a(M);
    }

    private static void D() {
        PressureParam O = com.ido.ble.f.a.f.a.g0().O();
        if (O == null) {
            O = new PressureParam();
            O.onOff = 85;
        }
        com.ido.ble.i.a.a.a(O);
    }

    private static void E() {
        QuickSportMode P = com.ido.ble.f.a.f.a.g0().P();
        if (P == null) {
            P = b();
        }
        com.ido.ble.i.a.a.a(P);
    }

    private static void F() {
        RespiratoryRate Q = com.ido.ble.f.a.f.a.g0().Q();
        if (Q == null) {
            Q = new RespiratoryRate();
            Q.on_off = 85;
        }
        com.ido.ble.i.a.a.a(Q);
    }

    private static void G() {
        ScreenBrightness S = com.ido.ble.f.a.f.a.g0().S();
        if (S == null) {
            S = new ScreenBrightness();
            S.level = 100;
        }
        S.opera = 0;
        S.mode = 2;
        com.ido.ble.i.a.a.a(S);
    }

    private static void H() {
        ShortCut T = com.ido.ble.f.a.f.a.g0().T();
        if (T == null) {
            T = new ShortCut();
        }
        com.ido.ble.i.a.a.a(T);
    }

    private static void I() {
        SleepMonitoringPara U = com.ido.ble.f.a.f.a.g0().U();
        if (U == null) {
            U = new SleepMonitoringPara();
        }
        com.ido.ble.i.a.a.a(U);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSleepMonitoringPara:" + U);
    }

    private static void J() {
        SPO2Param V = com.ido.ble.f.a.f.a.g0().V();
        if (V == null) {
            V = new SPO2Param();
            V.onOff = 85;
        }
        com.ido.ble.i.a.a.a(V);
    }

    private static void K() {
        SportModeSort W = com.ido.ble.f.a.f.a.g0().W();
        if (W == null) {
            W = new SportModeSort();
            W.items = new SportModeSort.SportModeSortItem[1];
            SportModeSort.SportModeSortItem sportModeSortItem = new SportModeSort.SportModeSortItem();
            sportModeSortItem.index = 1;
            sportModeSortItem.type = 1;
            W.items[0] = sportModeSortItem;
        }
        com.ido.ble.i.a.a.a(W);
    }

    private static void L() {
        SportModeSortV3 X = com.ido.ble.f.a.f.a.g0().X();
        if (X == null) {
            X = new SportModeSortV3();
            SportModeSortV3.SportModeSortItemV3 sportModeSortItemV3 = new SportModeSortV3.SportModeSortItemV3();
            ArrayList arrayList = new ArrayList();
            X.item = arrayList;
            X.num = 1;
            sportModeSortItemV3.type = 1;
            sportModeSortItemV3.index = 1;
            arrayList.add(sportModeSortItemV3);
        }
        com.ido.ble.i.a.a.a(X);
    }

    private static void M() {
        Units a0 = com.ido.ble.f.a.f.a.g0().a0();
        if (a0 == null) {
            a0 = c();
        }
        com.ido.ble.i.a.a.a(a0);
    }

    private static void N() {
        UpHandGesture b0 = com.ido.ble.f.a.f.a.g0().b0();
        if (b0 == null) {
            b0 = new UpHandGesture();
            b0.onOff = 170;
            b0.hasTimeRange = 0;
        }
        com.ido.ble.i.a.a.a(b0);
    }

    private static void O() {
        WalkReminder d0 = com.ido.ble.f.a.f.a.g0().d0();
        if (d0 == null) {
            d0 = new WalkReminder();
            d0.setOnOff(0);
        }
        com.ido.ble.i.a.a.a(d0);
    }

    private static int a() {
        String language = Locale.getDefault().getLanguage();
        if (language.equalsIgnoreCase("zh")) {
            return 1;
        }
        if (language.equalsIgnoreCase(Languages.DEFAULT_LANGUAGE)) {
            return 2;
        }
        if (language.equalsIgnoreCase("fr")) {
            return 3;
        }
        if (language.equalsIgnoreCase("de")) {
            return 4;
        }
        if (language.equalsIgnoreCase("it")) {
            return 5;
        }
        if (language.equalsIgnoreCase("es")) {
            return 6;
        }
        return language.equalsIgnoreCase("ja") ? 7 : 2;
    }

    public static void a(int i) {
        String str;
        if (i == 107) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUserInfo");
            UserInfo c0 = com.ido.ble.f.a.f.a.g0().c0();
            if (c0 != null) {
                com.ido.ble.i.a.a.a(c0);
                return;
            }
            str = "[SoBaseRequest] setUserInfo failed, userInfo is null, you should call BLEManager.setUserInfo or BLEManager.setUserInfoPending to set it";
        } else if (i == 108) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUnits");
            M();
            return;
        } else if (i == 124) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDialPlate");
            m();
            return;
        } else if (i == 125) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setShortCut");
            H();
            return;
        } else if (i == 150) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setWeatherSwitch");
            com.ido.ble.i.a.a.f(com.ido.ble.f.a.f.a.g0().e0());
            return;
        } else if (i == 151) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setQuickSportMode");
            E();
            return;
        } else if (i == 154) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setScreenBrightness");
            G();
            return;
        } else if (i == 155) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDefaultGpsPara");
            l();
            return;
        } else if (i == 191) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setRespiratoryRate");
            F();
            return;
        } else if (i == 192) {
            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setBodyPowerSwitch");
            i();
            return;
        } else {
            switch (i) {
                case 100:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setAlarm");
                    com.ido.ble.i.a.a.g(com.ido.ble.f.a.f.a.g0().e());
                    return;
                case 101:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setLongSit");
                    v();
                    return;
                case 102:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setAntiLostMode");
                    g();
                    return;
                case 103:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFindPhoneSwitch");
                    com.ido.ble.i.a.a.a(com.ido.ble.f.a.f.a.g0().s());
                    return;
                case 104:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setTime");
                    com.ido.ble.i.a.a.p0();
                    return;
                case 105:
                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setGoal");
                    q();
                    return;
                default:
                    switch (i) {
                        case 111:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNoticeReminder");
                            C();
                            return;
                        case 112:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateInterval");
                            r();
                            return;
                        case 113:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateMeasureMode");
                            s();
                            return;
                        case 114:
                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setUpHandGesture");
                            N();
                            return;
                        default:
                            switch (i) {
                                case 116:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNotDisturbPara");
                                    B();
                                    return;
                                case 117:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMusicSwitch");
                                    com.ido.ble.i.a.a.c(com.ido.ble.f.a.f.a.g0().I());
                                    return;
                                case 118:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDisplayMode");
                                    n();
                                    return;
                                case 119:
                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setOneKeySOSSwitch");
                                    com.ido.ble.i.a.a.d(com.ido.ble.f.a.f.a.g0().N());
                                    return;
                                default:
                                    switch (i) {
                                        case 159:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenstrual");
                                            w();
                                            return;
                                        case 160:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenstrualRemind");
                                            x();
                                            return;
                                        case 161:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setCalorieAndDistanceGoal");
                                            k();
                                            return;
                                        case 162:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSpO2Param");
                                            J();
                                            return;
                                        case 163:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setPressureParam");
                                            D();
                                            return;
                                        case 164:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSportModeSort");
                                            K();
                                            return;
                                        case 165:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setWalkReminder");
                                            O();
                                            return;
                                        case 166:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setBreatheTrain");
                                            j();
                                            return;
                                        case 167:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setActivitySwitch");
                                            f();
                                            return;
                                        case 168:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setDrinkWaterReminder");
                                            o();
                                            return;
                                        case 171:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setMenuList");
                                            y();
                                            return;
                                        case 202:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setBindAuth");
                                            h();
                                            return;
                                        case 300:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getMacAddress");
                                            com.ido.ble.i.a.a.I();
                                            return;
                                        case 301:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getBasicInfo");
                                            com.ido.ble.i.a.a.q();
                                            return;
                                        case 302:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getFunctionTables");
                                            com.ido.ble.i.a.a.T();
                                            return;
                                        case 311:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getExFunctionTables");
                                            com.ido.ble.i.a.a.C();
                                            return;
                                        case 317:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getMTU");
                                            BLESpecialAPI.getMTU();
                                            return;
                                        case 5010:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateMeasureModeV3");
                                            t();
                                            return;
                                        case 5013:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSportModeSortV3");
                                            L();
                                            return;
                                        case 5018:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getAlarmV3");
                                            com.ido.ble.i.a.a.o();
                                            return;
                                        case com.veryfit.multi.nativeprotocol.b.I3 /* 5031 */:
                                            LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] getV3Tables");
                                            d();
                                            return;
                                        default:
                                            switch (i) {
                                                case 182:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setHeartRateSmart");
                                                    u();
                                                    return;
                                                case 183:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNoisePara");
                                                    A();
                                                    return;
                                                case 184:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setSleepMonitoringPara");
                                                    I();
                                                    return;
                                                case 185:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNightTemperatureMonitoringPara");
                                                    z();
                                                    return;
                                                case 186:
                                                    LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFitnessGuidance");
                                                    p();
                                                    return;
                                                default:
                                                    str = "[SoBaseRequest] should handle type = " + i;
                                                    break;
                                            }
                                    }
                            }
                    }
            }
        }
        LogTool.b(com.ido.ble.logs.a.i, str);
    }

    private static QuickSportMode b() {
        int i;
        QuickSportMode quickSportMode = new QuickSportMode();
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        boolean z = Z != null && ((i = Z.sport_show_num) == 3 || i == 4);
        quickSportMode.sport_type0_walk = true;
        quickSportMode.sport_type0_run = true;
        quickSportMode.sport_type0_by_bike = true;
        if (!z) {
            quickSportMode.sport_type2_basketball = true;
            quickSportMode.sport_type0_on_foot = true;
            quickSportMode.sport_type0_badminton = true;
            quickSportMode.sport_type1_fitness = true;
            quickSportMode.sport_type1_treadmill = true;
        } else if (Z.sport_show_num == 4) {
            quickSportMode.sport_type1_fitness = true;
        }
        return quickSportMode;
    }

    private static Units c() {
        Units units = new Units();
        units.stride = 75;
        int a2 = a();
        units.language = a2;
        if (a2 == 1) {
            units.temp = 1;
            units.dist = 1;
        } else {
            units.temp = 2;
            units.dist = 2;
        }
        units.timeMode = e() ? 1 : 2;
        return units;
    }

    private static void d() {
        u.d(com.veryfit.multi.nativeprotocol.b.I3);
    }

    private static boolean e() {
        return DateFormat.is24HourFormat(com.ido.ble.common.e.a());
    }

    private static void f() {
        ActivitySwitch d = com.ido.ble.f.a.f.a.g0().d();
        if (d == null) {
            d = new ActivitySwitch();
        }
        com.ido.ble.i.a.a.a(d);
    }

    private static void g() {
        AntiLostMode g = com.ido.ble.f.a.f.a.g0().g();
        if (g == null) {
            g = new AntiLostMode();
            g.mode = 0;
        }
        com.ido.ble.i.a.a.a(g);
    }

    private static void h() {
        if (!com.ido.ble.bluetooth.a.g()) {
            LogTool.b(com.ido.ble.logs.a.i, "[SoBaseRequest] not int bind state, ignore set bind auth.");
            return;
        }
        String c = com.ido.ble.bluetooth.a.c();
        if (TextUtils.isEmpty(c)) {
            LogTool.b(com.ido.ble.logs.a.i, "[SoBaseRequest] bind auth is null, ignore set bind auth.");
        } else {
            com.ido.ble.i.a.a.a(((BindAuth) com.ido.ble.common.k.c(c, BindAuth.class)).auth_code);
        }
    }

    private static void i() {
        BodyPowerSwitch k = com.ido.ble.f.a.f.a.g0().k();
        if (k == null) {
            k = new BodyPowerSwitch();
            k.on_off = 85;
        }
        com.ido.ble.i.a.a.a(k);
    }

    private static void j() {
        BreatheTrain l = com.ido.ble.f.a.f.a.g0().l();
        if (l == null) {
            l = new BreatheTrain();
            l.frequency = 60;
        }
        com.ido.ble.i.a.a.a(l);
    }

    private static void k() {
        CalorieAndDistanceGoal n = com.ido.ble.f.a.f.a.g0().n();
        if (n == null) {
            n = new CalorieAndDistanceGoal();
        }
        com.ido.ble.i.a.a.a(n);
    }

    private static void l() {
        ConfigGPS v = com.ido.ble.f.a.f.a.g0().v();
        if (v == null) {
            v = new ConfigGPS();
            v.startMode = 2;
            v.operationMode = 1;
            v.cycleMS = 1000;
            v.gnsValue = 1;
        }
        if (LocalDataManager.getSupportFunctionInfo().ex_table_main8_ublox_model) {
            v.gnsValue = 3;
        }
        com.ido.ble.h.a.a(v);
    }

    private static void m() {
        DialPlate p = com.ido.ble.f.a.f.a.g0().p();
        if (p == null) {
            p = new DialPlate();
            p.dial_id = 1;
        }
        com.ido.ble.i.a.a.a(p);
    }

    private static void n() {
        DisplayMode q = com.ido.ble.f.a.f.a.g0().q();
        if (q == null) {
            q = new DisplayMode();
            q.mode = 0;
        }
        com.ido.ble.i.a.a.a(q);
    }

    private static void o() {
        DrinkWaterReminder r = com.ido.ble.f.a.f.a.g0().r();
        if (r == null) {
            r = new DrinkWaterReminder();
        }
        com.ido.ble.i.a.a.a(r);
    }

    private static void p() {
        FitnessGuidance t = com.ido.ble.f.a.f.a.g0().t();
        if (t == null) {
            t = new FitnessGuidance();
        }
        com.ido.ble.i.a.a.a(t);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setFitnessGuidance:" + t);
    }

    private static void q() {
        Goal u = com.ido.ble.f.a.f.a.g0().u();
        if (u == null) {
            u = new Goal();
            u.sport_step = 10000;
        }
        com.ido.ble.i.a.a.a(u);
    }

    private static void r() {
        int i;
        HeartRateInterval x = com.ido.ble.f.a.f.a.g0().x();
        SupportFunctionInfo Z = com.ido.ble.f.a.f.a.g0().Z();
        int i2 = 200;
        if (x != null) {
            int userMaxHR = x.getUserMaxHR();
            if (userMaxHR == 0) {
                double d = 200;
                x.setLimintThreshold((int) (0.85d * d));
                x.setAerobicThreshold((int) (d * 0.7d));
                x.setBurnFatThreshold((int) (d * 0.5d));
            } else {
                i2 = userMaxHR;
            }
            if (Z != null && (Z.level5_hr_interval || Z.FiveHRInterval)) {
                double d2 = i2;
                x.setLimintThreshold((int) (0.9d * d2));
                x.setAnaerobicThreshold((int) (0.8d * d2));
                x.setAerobicThreshold((int) (0.7d * d2));
                x.setBurnFatThreshold((int) (0.6d * d2));
                i = (int) (d2 * 0.5d);
                x.setWarmUpThreshold(i);
            }
        } else {
            x = new HeartRateInterval();
            x.setUserMaxHR(200);
            x.setLimintThreshold(168);
            x.setAerobicThreshold(126);
            x.setBurnFatThreshold(105);
            if (Z != null && (Z.level5_hr_interval || Z.FiveHRInterval)) {
                x.setLimintThreshold(180);
                x.setAnaerobicThreshold(160);
                x.setAerobicThreshold(140);
                x.setBurnFatThreshold(120);
                i = 100;
                x.setWarmUpThreshold(i);
            }
        }
        com.ido.ble.i.a.a.a(x);
    }

    private static void s() {
        HeartRateMeasureMode y = com.ido.ble.f.a.f.a.g0().y();
        if (y == null) {
            y = new HeartRateMeasureMode();
            y.mode = 136;
        }
        com.ido.ble.i.a.a.a(y);
    }

    private static void t() {
        HeartRateMeasureModeV3 z = com.ido.ble.f.a.f.a.g0().z();
        if (z == null) {
            z = new HeartRateMeasureModeV3();
            z.mode = 136;
        }
        com.ido.ble.i.a.a.a(z);
    }

    private static void u() {
        HeartRateSmartMode B = com.ido.ble.f.a.f.a.g0().B();
        if (B == null) {
            B = new HeartRateSmartMode();
            B.mode = 170;
        }
        com.ido.ble.i.a.a.a(B);
    }

    private static void v() {
        LongSit E = com.ido.ble.f.a.f.a.g0().E();
        if (E == null) {
            E = new LongSit();
            E.setStartHour(9);
            E.setEndHour(20);
            E.setInterval(15);
            E.setOnOff(false);
        } else if (E.getInterval() == 0) {
            E.setInterval(15);
        }
        com.ido.ble.i.a.a.a(E);
    }

    private static void w() {
        Menstrual F = com.ido.ble.f.a.f.a.g0().F();
        if (F == null) {
            F = new Menstrual();
            F.on_off = 85;
        }
        com.ido.ble.i.a.a.a(F);
    }

    private static void x() {
        MenstrualRemind G = com.ido.ble.f.a.f.a.g0().G();
        if (G == null) {
            G = new MenstrualRemind();
        }
        com.ido.ble.i.a.a.a(G);
    }

    private static void y() {
        MenuList H = com.ido.ble.f.a.f.a.g0().H();
        if (H == null) {
            H = new MenuList();
            H.items.add(15);
            H.items.add(9);
        }
        com.ido.ble.i.a.a.a(H);
    }

    private static void z() {
        NightTemperatureMonitoringPara J = com.ido.ble.f.a.f.a.g0().J();
        if (J == null) {
            J = new NightTemperatureMonitoringPara();
        }
        com.ido.ble.i.a.a.a(J);
        LogTool.d(com.ido.ble.logs.a.i, "[SoBaseRequest] setNightTemperatureMonitoringPara:" + J);
    }
}
