package com.apex.bluetooth.data_parse;

import a.a;
import a.d;
import com.apex.bluetooth.callback.AttentionCallback;
import com.apex.bluetooth.callback.BatterInfoCallback;
import com.apex.bluetooth.callback.BloodPressureCallback;
import com.apex.bluetooth.callback.CalorieSwitchCallback;
import com.apex.bluetooth.callback.CombinationCallback;
import com.apex.bluetooth.callback.DeviceBugCallback;
import com.apex.bluetooth.callback.DistanceUnitCallback;
import com.apex.bluetooth.callback.DonDisturbCallback;
import com.apex.bluetooth.callback.EABleCallback;
import com.apex.bluetooth.callback.EditAttentionCallback;
import com.apex.bluetooth.callback.FeaturesCallback;
import com.apex.bluetooth.callback.GeneralCallback;
import com.apex.bluetooth.callback.GoalCallback;
import com.apex.bluetooth.callback.HabitCallback;
import com.apex.bluetooth.callback.HabitResultCallback;
import com.apex.bluetooth.callback.HeartCheckCallback;
import com.apex.bluetooth.callback.HeartLimitCallback;
import com.apex.bluetooth.callback.HrOxygenPressureCheckCallback;
import com.apex.bluetooth.callback.InfoPushCallback;
import com.apex.bluetooth.callback.LanguageCallback;
import com.apex.bluetooth.callback.MenuCallback;
import com.apex.bluetooth.callback.MonitorReminderCallback;
import com.apex.bluetooth.callback.PeriodCallback;
import com.apex.bluetooth.callback.PeriodReminderCallback;
import com.apex.bluetooth.callback.PersonInfoCallback;
import com.apex.bluetooth.callback.RaiseHandBrightScreenCallback;
import com.apex.bluetooth.callback.ReadBookListCallback;
import com.apex.bluetooth.callback.RemindCallback;
import com.apex.bluetooth.callback.RestScreenCallback;
import com.apex.bluetooth.callback.ScreenBrightnessCallback;
import com.apex.bluetooth.callback.SedentaryCheckCallback;
import com.apex.bluetooth.callback.SleepBloodMonitorCallback;
import com.apex.bluetooth.callback.SleepCheckCallback;
import com.apex.bluetooth.callback.StressMonitorCallback;
import com.apex.bluetooth.callback.TimeCallback;
import com.apex.bluetooth.callback.TimeZoneCallback;
import com.apex.bluetooth.callback.TodayTotalDataCallback;
import com.apex.bluetooth.callback.UnitCallback;
import com.apex.bluetooth.callback.VibrateCallback;
import com.apex.bluetooth.callback.WatchFaceCallback;
import com.apex.bluetooth.callback.WatchInfoCallback;
import com.apex.bluetooth.callback.WeatherCallback;
import com.apex.bluetooth.callback.WeightUnitCallback;
import com.apex.bluetooth.enumeration.BatInfoStatus;
import com.apex.bluetooth.enumeration.CommonAction;
import com.apex.bluetooth.enumeration.HabitIcon;
import com.apex.bluetooth.enumeration.HabitState;
import com.apex.bluetooth.enumeration.PersonHand;
import com.apex.bluetooth.enumeration.TimeZone;
import com.apex.bluetooth.enumeration.UnitFormat;
import com.apex.bluetooth.enumeration.VibrationIntensity;
import com.apex.bluetooth.model.EABleAncsSw;
import com.apex.bluetooth.model.EABleAutoCheckSleep;
import com.apex.bluetooth.model.EABleAutoMonitor;
import com.apex.bluetooth.model.EABleBatInfo;
import com.apex.bluetooth.model.EABleBloodPressure;
import com.apex.bluetooth.model.EABleCombination;
import com.apex.bluetooth.model.EABleContact;
import com.apex.bluetooth.model.EABleDailyGoal;
import com.apex.bluetooth.model.EABleDevUnit;
import com.apex.bluetooth.model.EABleDeviceLanguage;
import com.apex.bluetooth.model.EABleDistanceFormat;
import com.apex.bluetooth.model.EABleFeatures;
import com.apex.bluetooth.model.EABleGesturesBrightScreen;
import com.apex.bluetooth.model.EABleHabit;
import com.apex.bluetooth.model.EABleHabitRespond;
import com.apex.bluetooth.model.EABleHomeTimeZone;
import com.apex.bluetooth.model.EABleHr;
import com.apex.bluetooth.model.EABleInfoPush;
import com.apex.bluetooth.model.EABleMenuPage;
import com.apex.bluetooth.model.EABleMonitorReminder;
import com.apex.bluetooth.model.EABleNotDisturb;
import com.apex.bluetooth.model.EABlePeriod;
import com.apex.bluetooth.model.EABlePeriodReminder;
import com.apex.bluetooth.model.EABlePersonInfo;
import com.apex.bluetooth.model.EABleReadDebug;
import com.apex.bluetooth.model.EABleRemindRespond;
import com.apex.bluetooth.model.EABleReminder;
import com.apex.bluetooth.model.EABleSedentariness;
import com.apex.bluetooth.model.EABleSleepBloodSwitch;
import com.apex.bluetooth.model.EABleSyncTime;
import com.apex.bluetooth.model.EABleWatchFace;
import com.apex.bluetooth.model.EABleWatchInfo;
import com.apex.bluetooth.model.EABleWeather;
import com.apex.bluetooth.model.EABleWeightFormat;
import com.apex.bluetooth.model.TodayTotalData;
import com.apex.bluetooth.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final String f2212a = a.class.getSimpleName();

    public void a(byte[] bArr, EABleCallback eABleCallback) {
        List<a.k.c> c;
        List<a.f0.c> c2;
        VibrationIntensity vibrationIntensity;
        if (bArr == null) {
            return;
        }
        int i = ((bArr[4] & 255) << 8) | (bArr[3] & 255);
        int length = bArr.length - 6;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 5, bArr2, 0, length);
        switch (i) {
            case 1:
                a.i a2 = a.i.a(bArr2);
                if (a2.c() == a.i.c.success) {
                    if (eABleCallback != null) {
                        ((GeneralCallback) eABleCallback).result(true);
                        return;
                    }
                    return;
                } else if (a2.c() == a.i.c.watch_in_motion) {
                    if (eABleCallback != null) {
                        ((GeneralCallback) eABleCallback).result(false);
                        return;
                    }
                    return;
                } else if (a2.c() == a.i.c.watch_end_motion) {
                    if (eABleCallback != null) {
                        ((GeneralCallback) eABleCallback).result(true);
                        return;
                    }
                    return;
                } else if (eABleCallback != null) {
                    ((GeneralCallback) eABleCallback).result(false);
                    return;
                } else {
                    return;
                }
            case 2:
            case 6:
            case 12:
            case 19:
            case 29:
            case 41:
            case 42:
            case 46:
            case 47:
            case 48:
            case 49:
            case 52:
            case 54:
            default:
                return;
            case 3:
                a.q0 a3 = a.q0.a(bArr2);
                EABleWatchInfo eABleWatchInfo = new EABleWatchInfo();
                a3.e();
                eABleWatchInfo.setBindingInfo(a3.f() <= 0 ? EABleWatchInfo.BindingInfo.bound : EABleWatchInfo.BindingInfo.unbound);
                String g = a3.g();
                String u = a3.u();
                String h = a3.h();
                String t = a3.t();
                eABleWatchInfo.setFirmwareVersion(g);
                eABleWatchInfo.setUserId(u);
                eABleWatchInfo.setWatchId(h);
                eABleWatchInfo.setWatchType(t);
                eABleWatchInfo.setAgps_update_timestamp(a3.b() & (-1));
                eABleWatchInfo.setBle_mac_addr(a3.c());
                eABleWatchInfo.setIs_wait_for_binding(a3.i());
                eABleWatchInfo.setProj_settings(a3.s());
                eABleWatchInfo.setLcd_full_h(a3.j());
                eABleWatchInfo.setLcd_full_w(a3.l());
                eABleWatchInfo.setLcd_full_type(a3.k());
                eABleWatchInfo.setLcd_preview_h(a3.n());
                eABleWatchInfo.setLcd_preview_w(a3.p());
                eABleWatchInfo.setLcd_preview_radius(a3.o());
                eABleWatchInfo.setNot_support_sn(a3.r());
                eABleWatchInfo.setMax_watch_size(a3.q());
                eABleWatchInfo.setLcd_pixel_type(a3.m());
                if (eABleCallback != null) {
                    ((WatchInfoCallback) eABleCallback).watchInfo(eABleWatchInfo);
                    return;
                }
                return;
            case 4:
                a.o0 a4 = a.o0.a(bArr2);
                EABlePersonInfo eABlePersonInfo = new EABlePersonInfo();
                eABlePersonInfo.setAge(a4.b());
                a4.d();
                eABlePersonInfo.setE_hand_info(a4.e() <= 0 ? PersonHand.left : PersonHand.right);
                a4.f();
                eABlePersonInfo.setE_sex_info(a4.g() <= 0 ? EABlePersonInfo.PersonSex.male : EABlePersonInfo.PersonSex.female);
                eABlePersonInfo.setHeight(a4.i());
                eABlePersonInfo.setWeight(a4.j());
                if (a4.h() == a.o0.e.skin_white) {
                    eABlePersonInfo.setE_skin_color(EABlePersonInfo.SkinColor.skin_white);
                } else if (a4.h() == a.o0.e.skin_white_yellow) {
                    eABlePersonInfo.setE_skin_color(EABlePersonInfo.SkinColor.skin_white_yellow);
                } else if (a4.h() == a.o0.e.skin_yellow) {
                    eABlePersonInfo.setE_skin_color(EABlePersonInfo.SkinColor.skin_yellow);
                } else if (a4.h() == a.o0.e.skin_yellow_black) {
                    eABlePersonInfo.setE_skin_color(EABlePersonInfo.SkinColor.skin_yellow_black);
                } else if (a4.h() == a.o0.e.skin_balck) {
                    eABlePersonInfo.setE_skin_color(EABlePersonInfo.SkinColor.skin_balck);
                }
                if (eABleCallback != null) {
                    ((PersonInfoCallback) eABleCallback).personInfo(eABlePersonInfo);
                    return;
                }
                return;
            case 5:
                a.l0 a5 = a.l0.a(bArr2);
                EABleSyncTime eABleSyncTime = new EABleSyncTime();
                a5.f();
                eABleSyncTime.setE_sync_mode(a5.g() <= 0 ? EABleSyncTime.SyncMode.normal : EABleSyncTime.SyncMode.watch);
                eABleSyncTime.setYear(a5.o());
                eABleSyncTime.setMonth(a5.k());
                eABleSyncTime.setDay(a5.b());
                eABleSyncTime.setHour(a5.i());
                eABleSyncTime.setMinute(a5.j());
                eABleSyncTime.setSecond(a5.l());
                a5.d();
                eABleSyncTime.setE_hour_system(a5.e() <= 0 ? EABleSyncTime.HourSystem.hour_12 : EABleSyncTime.HourSystem.hour_24);
                a5.h();
                if (a5.h() == a.l0.e.west) {
                    eABleSyncTime.setE_time_zone(TimeZone.west);
                } else if (a5.h() == a.l0.e.east) {
                    eABleSyncTime.setE_time_zone(TimeZone.east);
                } else if (a5.h() == a.l0.e.zero) {
                    eABleSyncTime.setE_time_zone(TimeZone.zero);
                } else {
                    eABleSyncTime.setE_time_zone(TimeZone.unknown);
                }
                eABleSyncTime.setTime_zone_hour(a5.m());
                eABleSyncTime.setTime_zone_minute(a5.n());
                if (eABleCallback != null) {
                    ((TimeCallback) eABleCallback).syncTime(eABleSyncTime);
                    return;
                }
                return;
            case 7:
                int b = a.p.a(bArr2).b();
                if (eABleCallback != null) {
                    ((ScreenBrightnessCallback) eABleCallback).screenBrightness(b);
                    return;
                }
                return;
            case 8:
                int b2 = a.q.a(bArr2).b();
                if (eABleCallback != null) {
                    ((RestScreenCallback) eABleCallback).restScreen(b2);
                    return;
                }
                return;
            case 9:
                a.f a6 = a.f.a(bArr2);
                EABleBatInfo eABleBatInfo = new EABleBatInfo();
                a6.c();
                eABleBatInfo.setE_status(a6.d() <= 0 ? BatInfoStatus.normal : BatInfoStatus.in_charging);
                eABleBatInfo.setLevel(a6.e());
                if (eABleCallback != null) {
                    ((BatterInfoCallback) eABleCallback).batterInfo(eABleBatInfo);
                    return;
                }
                return;
            case 10:
                a.a0 a7 = a.a0.a(bArr2);
                EABleDeviceLanguage eABleDeviceLanguage = new EABleDeviceLanguage();
                a7.d();
                if (a7.d() == a.a0.d.arabic) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.arabic);
                } else if (a7.d() == a.a0.d.default_type) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.default_type);
                } else if (a7.d() == a.a0.d.english) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.english);
                } else if (a7.d() == a.a0.d.chinese_simplifid) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.chinese_simplifid);
                } else if (a7.d() == a.a0.d.chinese_traditional) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.chinese_traditional);
                } else if (a7.d() == a.a0.d.korean) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.korean);
                } else if (a7.d() == a.a0.d.thai) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.thai);
                } else if (a7.d() == a.a0.d.japanese) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.japanese);
                } else if (a7.d() == a.a0.d.spanish) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.spanish);
                } else if (a7.d() == a.a0.d.francais) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.francais);
                } else if (a7.d() == a.a0.d.deutsch) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.deutsch);
                } else if (a7.d() == a.a0.d.italiano) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.italiano);
                } else if (a7.d() == a.a0.d.polski) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.polski);
                } else if (a7.d() == a.a0.d.portuguese) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.portuguese);
                } else if (a7.d() == a.a0.d.russian) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.russian);
                } else if (a7.d() == a.a0.d.dutch) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.dutch);
                } else if (a7.d() == a.a0.d.greek) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.greek);
                } else if (a7.d() == a.a0.d.hebrew) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.hebrew);
                } else if (a7.d() == a.a0.d.swedish) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.swedish);
                } else if (a7.d() == a.a0.d.osmanli) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.osmanli);
                } else if (a7.d() == a.a0.d.czech) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.czech);
                } else if (a7.d() == a.a0.d.indonesia) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.indonesia);
                } else if (a7.d() == a.a0.d.melayu) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.melayu);
                } else if (a7.d() == a.a0.d.farsi) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.farsi);
                } else if (a7.d() == a.a0.d.vietnamese) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.vietnamese);
                } else if (a7.d() == a.a0.d.belarusian) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.belarusian);
                } else if (a7.d() == a.a0.d.hungarian) {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.hungarian);
                } else {
                    eABleDeviceLanguage.setE_type(EABleDeviceLanguage.LanguageType.unknown);
                }
                List<a.a0.c> e = a7.e();
                if (e != null && !e.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    eABleDeviceLanguage.setSupportList(arrayList);
                    while (r8 < e.size()) {
                        int b3 = e.get(r8).b();
                        if (b3 == 0) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.default_type);
                        } else if (b3 == 1) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.english);
                        } else if (b3 == 2) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.chinese_simplifid);
                        } else if (b3 == 3) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.chinese_traditional);
                        } else if (b3 == 4) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.korean);
                        } else if (b3 == 5) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.thai);
                        } else if (b3 == 6) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.japanese);
                        } else if (b3 == 7) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.spanish);
                        } else if (b3 == 8) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.francais);
                        } else if (b3 == 9) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.deutsch);
                        } else if (b3 == 10) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.italiano);
                        } else if (b3 == 11) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.polski);
                        } else if (b3 == 12) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.portuguese);
                        } else if (b3 == 13) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.russian);
                        } else if (b3 == 14) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.dutch);
                        } else if (b3 == 15) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.arabic);
                        } else if (b3 == 16) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.greek);
                        } else if (b3 == 17) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.hebrew);
                        } else if (b3 == 18) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.swedish);
                        } else if (b3 == 19) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.osmanli);
                        } else if (b3 == 20) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.czech);
                        } else if (b3 == 21) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.indonesia);
                        } else if (b3 == 22) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.melayu);
                        } else if (b3 == 23) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.farsi);
                        } else if (b3 == 24) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.vietnamese);
                        } else if (b3 == 25) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.belarusian);
                        } else if (b3 == 26) {
                            arrayList.add(EABleDeviceLanguage.LanguageType.hungarian);
                        } else {
                            arrayList.add(EABleDeviceLanguage.LanguageType.unknown);
                        }
                        r8++;
                    }
                }
                if (eABleCallback != null) {
                    ((LanguageCallback) eABleCallback).languageInfo(eABleDeviceLanguage);
                    return;
                }
                return;
            case 11:
                a.m0 a8 = a.m0.a(bArr2);
                EABleDevUnit eABleDevUnit = new EABleDevUnit();
                a8.c();
                eABleDevUnit.setE_format(a8.d() <= 0 ? UnitFormat.metric : UnitFormat.british);
                if (eABleCallback != null) {
                    ((UnitCallback) eABleCallback).unitInfo(eABleDevUnit);
                    return;
                }
                return;
            case 13:
                a.d0 a9 = a.d0.a(bArr2);
                EABleNotDisturb eABleNotDisturb = new EABleNotDisturb();
                eABleNotDisturb.setBegin_hour(a9.b());
                eABleNotDisturb.setBegin_minute(a9.c());
                eABleNotDisturb.setEnd_hour(a9.d());
                eABleNotDisturb.setEnd_minute(a9.e());
                eABleNotDisturb.setSw(a9.f() <= 0 ? 0 : 1);
                eABleNotDisturb.setWatch_sw(a9.g() > 0 ? 1 : 0);
                if (eABleCallback != null) {
                    ((DonDisturbCallback) eABleCallback).donDisturbInfo(eABleNotDisturb);
                    return;
                }
                return;
            case 14:
                a.y a10 = a.y.a(bArr2);
                EABleHomeTimeZone eABleHomeTimeZone = new EABleHomeTimeZone();
                List<a.y.c> c3 = a10.c();
                if (c3 != null && !c3.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (a.y.c cVar : c3) {
                        if (cVar != null) {
                            EABleHomeTimeZone.EABleHomeZone eABleHomeZone = new EABleHomeTimeZone.EABleHomeZone();
                            cVar.c();
                            if (cVar.c() == a.y.c.EnumC0065c.east) {
                                eABleHomeZone.setE_time_zone(TimeZone.east);
                            } else if (cVar.c() == a.y.c.EnumC0065c.west) {
                                eABleHomeZone.setE_time_zone(TimeZone.west);
                            } else if (cVar.c() == a.y.c.EnumC0065c.zero) {
                                eABleHomeZone.setE_time_zone(TimeZone.zero);
                            }
                            eABleHomeZone.setPlace(cVar.d());
                            eABleHomeZone.setTime_zone_hour(cVar.e());
                            eABleHomeZone.setTime_zone_minute(cVar.f());
                            arrayList2.add(eABleHomeZone);
                        }
                    }
                    eABleHomeTimeZone.setS_home(arrayList2);
                }
                if (eABleCallback != null) {
                    ((TimeZoneCallback) eABleCallback).timeZoneInfo(eABleHomeTimeZone);
                    return;
                }
                return;
            case 15:
                a.r a11 = a.r.a(bArr2);
                EABleDailyGoal eABleDailyGoal = new EABleDailyGoal();
                a.r.c b4 = a11.b();
                a.r.c c4 = a11.c();
                a.r.c d = a11.d();
                a.r.c e2 = a11.e();
                a.r.c f = a11.f();
                if (b4 != null) {
                    eABleDailyGoal.setS_calorie(a(b4));
                }
                if (c4 != null) {
                    eABleDailyGoal.setS_distance(a(c4));
                }
                if (d != null) {
                    eABleDailyGoal.setS_duration(a(d));
                }
                if (e2 != null) {
                    eABleDailyGoal.setS_sleep(a(e2));
                }
                if (f != null) {
                    eABleDailyGoal.setS_step(a(f));
                }
                if (eABleCallback != null) {
                    ((GoalCallback) eABleCallback).goalInfo(eABleDailyGoal);
                    return;
                }
                return;
            case 16:
                a.n a12 = a.n.a(bArr2);
                EABleAutoCheckSleep eABleAutoCheckSleep = new EABleAutoCheckSleep();
                eABleAutoCheckSleep.setBegin_hour(a12.b());
                eABleAutoCheckSleep.setBegin_minute(a12.c());
                eABleAutoCheckSleep.setEnd_hour(a12.d());
                eABleAutoCheckSleep.setEnd_minute(a12.e());
                eABleAutoCheckSleep.setWeek_cycle_bit(a12.f() & 255);
                if (eABleCallback != null) {
                    ((SleepCheckCallback) eABleCallback).sleepInfo(eABleAutoCheckSleep);
                    return;
                }
                return;
            case 17:
                int b5 = a.m.a(bArr2).b();
                if (eABleCallback != null) {
                    ((HeartCheckCallback) eABleCallback).heartInfo(b5);
                    return;
                }
                return;
            case 18:
                a.l a13 = a.l.a(bArr2);
                EABleSedentariness eABleSedentariness = new EABleSedentariness();
                eABleSedentariness.setBegin_hour(a13.b());
                eABleSedentariness.setBegin_minute(a13.c());
                eABleSedentariness.setEnd_hour(a13.d());
                eABleSedentariness.setEnd_minute(a13.e());
                eABleSedentariness.setInterval(a13.f());
                eABleSedentariness.setStep_threshold(a13.l());
                eABleSedentariness.setWeek_cycle_bit(a13.n() & 255);
                eABleSedentariness.setSw(a13.m());
                eABleSedentariness.setNoon_sw(a13.k());
                eABleSedentariness.setNoon_begin_hour(a13.g());
                eABleSedentariness.setNoon_begin_minute(a13.h());
                eABleSedentariness.setNoon_end_hour(a13.i());
                eABleSedentariness.setNoon_end_minute(a13.j());
                if (eABleCallback != null) {
                    ((SedentaryCheckCallback) eABleCallback).sedentaryInfo(eABleSedentariness);
                    return;
                }
                return;
            case 20:
                a.s0 a14 = a.s0.a(bArr2);
                EABleWeather eABleWeather = new EABleWeather();
                eABleWeather.setPlace(a14.f());
                eABleWeather.setCurrent_temperature(a14.c());
                if (a14.e() == a.s0.e.centigrade) {
                    eABleWeather.setTemperatureUnit(EABleWeather.TemperatureUnit.centigrade);
                } else {
                    eABleWeather.setTemperatureUnit(EABleWeather.TemperatureUnit.fahrenheit);
                }
                List<a.s0.d> g2 = a14.g();
                if (g2 != null && !g2.isEmpty()) {
                    ArrayList arrayList3 = new ArrayList();
                    for (a.s0.d dVar : g2) {
                        if (dVar != null) {
                            EABleWeather.EABleWeatherItem eABleWeatherItem = new EABleWeather.EABleWeatherItem();
                            eABleWeatherItem.setMin_temperature(dVar.n());
                            eABleWeatherItem.setMax_temperature(dVar.l());
                            eABleWeatherItem.setAir_humidity(dVar.c());
                            eABleWeatherItem.setCloudiness(dVar.d());
                            eABleWeatherItem.setMax_wind_power(dVar.m());
                            eABleWeatherItem.setMin_wind_power(dVar.o());
                            eABleWeatherItem.setSunrise_timestamp(dVar.p());
                            eABleWeatherItem.setSunset_timestamp(dVar.q());
                            eABleWeatherItem.setAir_grade(dVar.b());
                            dVar.f();
                            eABleWeatherItem.setE_day_type(a(dVar.g() & 255));
                            dVar.i();
                            eABleWeatherItem.setE_night_type(a(dVar.j() & 255));
                            a.s0.c e3 = dVar.e();
                            if (e3 == a.s0.c.bad) {
                                eABleWeatherItem.setE_air(EABleWeather.AirQuality.bad);
                            } else if (e3 == a.s0.c.good) {
                                eABleWeatherItem.setE_air(EABleWeather.AirQuality.good);
                            } else {
                                eABleWeatherItem.setE_air(EABleWeather.AirQuality.excellent);
                            }
                            a.s0.f h2 = dVar.h();
                            if (h2 == a.s0.f.full_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.full_moon);
                            } else if (h2 == a.s0.f.new_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.new_moon);
                            } else if (h2 == a.s0.f.quarter_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.quarter_moon);
                            } else if (h2 == a.s0.f.last_quarter_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.last_quarter_moon);
                            } else if (h2 == a.s0.f.waning_crescent_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.waning_crescent_moon);
                            } else if (h2 == a.s0.f.waning_gibbous_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.waning_gibbous_moon);
                            } else if (h2 == a.s0.f.waxing_crescent_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.waxing_crescent_moon);
                            } else if (h2 == a.s0.f.waxing_gibbous_moon) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.waxing_gibbous_moon);
                            } else if (h2 == a.s0.f.half_moon_1) {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.half_moon_1);
                            } else {
                                eABleWeatherItem.setE_moon(EABleWeather.Moon.half_moon_2);
                            }
                            a.s0.g k = dVar.k();
                            if (k == a.s0.g.medium) {
                                eABleWeatherItem.setE_rays(EABleWeather.RaysLevel.medium);
                            } else if (k == a.s0.g.strong) {
                                eABleWeatherItem.setE_rays(EABleWeather.RaysLevel.strong);
                            } else if (k == a.s0.g.super_strong) {
                                eABleWeatherItem.setE_rays(EABleWeather.RaysLevel.super_strong);
                            } else if (k == a.s0.g.very_strong) {
                                eABleWeatherItem.setE_rays(EABleWeather.RaysLevel.very_strong);
                            } else {
                                eABleWeatherItem.setE_rays(EABleWeather.RaysLevel.weak);
                            }
                            arrayList3.add(eABleWeatherItem);
                        }
                    }
                    eABleWeather.setS_day(arrayList3);
                }
                if (eABleCallback != null) {
                    ((WeatherCallback) eABleCallback).weatherInfo(eABleWeather);
                    return;
                }
                return;
            case 21:
                a.j a15 = a.j.a(bArr2);
                a.j.c c5 = a15.c();
                a.j.c d2 = a15.d();
                a.j.c f2 = a15.f();
                a.j.c g3 = a15.g();
                a.j.c b6 = a15.b();
                a.j.c e4 = a15.e();
                EABleAncsSw eABleAncsSw = new EABleAncsSw();
                if (c5 != null) {
                    eABleAncsSw.setS_incomingcall(a(c5));
                }
                if (d2 != null) {
                    eABleAncsSw.setS_missedcall(a(d2));
                }
                if (f2 != null) {
                    eABleAncsSw.setS_sms(a(f2));
                }
                if (g3 != null) {
                    eABleAncsSw.setS_social(a(g3));
                }
                if (b6 != null) {
                    eABleAncsSw.setS_email(a(b6));
                }
                if (e4 != null) {
                    eABleAncsSw.setS_schedule(a(e4));
                }
                if (eABleCallback != null) {
                    ((RemindCallback) eABleCallback).remindInfo(eABleAncsSw);
                    return;
                }
                return;
            case 22:
                a.h0 a16 = a.h0.a(bArr2);
                int f3 = a16.f();
                List<a.h0.c> g4 = a16.g();
                EABleReminder eABleReminder = new EABleReminder();
                eABleReminder.setId(f3);
                a16.d();
                eABleReminder.setE_ops(e(a16.e() & 255));
                if (g4 != null && !g4.isEmpty()) {
                    ArrayList arrayList4 = new ArrayList();
                    for (a.h0.c cVar2 : g4) {
                        if (cVar2 != null) {
                            EABleReminder.EABleReminderItem eABleReminderItem = new EABleReminder.EABleReminderItem();
                            eABleReminderItem.setContent(cVar2.b());
                            eABleReminderItem.setDay(cVar2.c());
                            eABleReminderItem.setHour(cVar2.h());
                            eABleReminderItem.setId(cVar2.i());
                            eABleReminderItem.setMinute(cVar2.j());
                            eABleReminderItem.setMonth(cVar2.k());
                            eABleReminderItem.setSec_sw(cVar2.l());
                            eABleReminderItem.setSw(cVar2.n() <= 0 ? 0 : 1);
                            eABleReminderItem.setSleep_duration(cVar2.m());
                            eABleReminderItem.setWeek_cycle_bit(cVar2.o() & 255);
                            cVar2.d();
                            eABleReminderItem.setE_action(d(cVar2.e() & 255));
                            cVar2.f();
                            eABleReminderItem.setE_type(f(cVar2.g() & 255));
                            eABleReminderItem.setYear(cVar2.p());
                            arrayList4.add(eABleReminderItem);
                        }
                    }
                    eABleReminder.setS_index(arrayList4);
                }
                if (eABleCallback != null) {
                    ((AttentionCallback) eABleCallback).attentionInfo(eABleReminder);
                    return;
                }
                return;
            case 23:
                a.i0 a17 = a.i0.a(bArr2);
                EABleRemindRespond eABleRemindRespond = new EABleRemindRespond();
                a17.c();
                if (a17.c() == a.i0.c.success) {
                    eABleRemindRespond.setRemindRespondResult(EABleRemindRespond.RemindRespondResult.success);
                } else if (a17.c() == a.i0.c.fail) {
                    eABleRemindRespond.setRemindRespondResult(EABleRemindRespond.RemindRespondResult.fail);
                } else if (a17.c() == a.i0.c.mem_full) {
                    eABleRemindRespond.setRemindRespondResult(EABleRemindRespond.RemindRespondResult.mem_full);
                } else if (a17.c() == a.i0.c.time_conflict) {
                    eABleRemindRespond.setRemindRespondResult(EABleRemindRespond.RemindRespondResult.time_conflict);
                }
                eABleRemindRespond.setId(a17.d());
                if (eABleCallback != null) {
                    ((EditAttentionCallback) eABleCallback).editResult(eABleRemindRespond);
                    return;
                }
                return;
            case 24:
                a.t a18 = a.t.a(bArr2);
                EABleDistanceFormat eABleDistanceFormat = new EABleDistanceFormat();
                a18.c();
                eABleDistanceFormat.setE_format(a18.d() <= 0 ? EABleDistanceFormat.DistanceUnit.kilometre : EABleDistanceFormat.DistanceUnit.mile);
                if (eABleCallback != null) {
                    ((DistanceUnitCallback) eABleCallback).distanceUnitInfo(eABleDistanceFormat);
                    return;
                }
                return;
            case 25:
                a.t0 a19 = a.t0.a(bArr2);
                EABleWeightFormat eABleWeightFormat = new EABleWeightFormat();
                a19.c();
                eABleWeightFormat.setE_format(a19.d() <= 0 ? EABleWeightFormat.WeightUnit.kilogram : EABleWeightFormat.WeightUnit.pound);
                if (eABleCallback != null) {
                    ((WeightUnitCallback) eABleCallback).weightUnitInfo(eABleWeightFormat);
                    return;
                }
                return;
            case 26:
                a.z a20 = a.z.a(bArr2);
                EABleHr eABleHr = new EABleHr();
                eABleHr.setMax_hr(a20.b());
                eABleHr.setMin_hr(a20.c());
                eABleHr.setSw(a20.d() > 0 ? 1 : 0);
                if (eABleCallback != null) {
                    ((HeartLimitCallback) eABleCallback).heartLimitInfo(eABleHr);
                    return;
                }
                return;
            case 27:
                a.o a21 = a.o.a(bArr2);
                a21.b();
                if (eABleCallback != null) {
                    ((CalorieSwitchCallback) eABleCallback).switchInfo(a21.b() > 0 ? 1 : 0);
                    return;
                }
                return;
            case 28:
                a.u a22 = a.u.a(bArr2);
                EABleGesturesBrightScreen eABleGesturesBrightScreen = new EABleGesturesBrightScreen();
                eABleGesturesBrightScreen.setBegin_hour(a22.b());
                eABleGesturesBrightScreen.setBegin_minute(a22.c());
                eABleGesturesBrightScreen.setEnd_hour(a22.f());
                eABleGesturesBrightScreen.setEnd_minute(a22.g());
                if (a22.e() == 0) {
                    eABleGesturesBrightScreen.setBrightScreenSwitch(EABleGesturesBrightScreen.BrightScreenSwitch.close);
                } else if (a22.e() == 1) {
                    eABleGesturesBrightScreen.setBrightScreenSwitch(EABleGesturesBrightScreen.BrightScreenSwitch.all_day);
                } else {
                    eABleGesturesBrightScreen.setBrightScreenSwitch(EABleGesturesBrightScreen.BrightScreenSwitch.select_time);
                }
                if (eABleCallback != null) {
                    ((RaiseHandBrightScreenCallback) eABleCallback).switchInfo(eABleGesturesBrightScreen);
                    return;
                }
                return;
            case 30:
                a.j0 a23 = a.j0.a(bArr2);
                EABleCombination eABleCombination = new EABleCombination();
                eABleCombination.setAuto_check_hr_sw(a23.b());
                eABleCombination.setAuto_pressure_sw(a23.c());
                eABleCombination.setAuto_sedentariness_sw(a23.d());
                eABleCombination.setBat_level(a23.e());
                eABleCombination.setGestures_sw(a23.k());
                eABleCombination.setNot_disturb_sw(a23.l());
                eABleCombination.setSet_vibrate_intensity(a23.m());
                a23.h();
                eABleCombination.setE_hand_info(a23.h() == a.j0.d.left ? PersonHand.left : PersonHand.right);
                a23.g();
                eABleCombination.setE_status(a23.g() == a.j0.c.normal ? BatInfoStatus.normal : BatInfoStatus.in_charging);
                a23.i();
                eABleCombination.setE_unit_format(a23.i() == a.j0.e.metric ? UnitFormat.metric : UnitFormat.british);
                a23.j();
                if (a23.j() == a.u0.light) {
                    eABleCombination.setE_vibrate_intensity(VibrationIntensity.light);
                } else if (a23.j() == a.u0.medium) {
                    eABleCombination.setE_vibrate_intensity(VibrationIntensity.medium);
                } else if (a23.j() == a.u0.strong) {
                    eABleCombination.setE_vibrate_intensity(VibrationIntensity.strong);
                } else {
                    eABleCombination.setE_vibrate_intensity(VibrationIntensity.not_vibrate);
                }
                eABleCombination.setWf_id(a23.q());
                eABleCombination.setUser_wf_id(a23.p());
                eABleCombination.setSleep_blood_oxygen_sw(a23.n());
                eABleCombination.setStress_sw(a23.o());
                if (eABleCallback != null) {
                    ((CombinationCallback) eABleCallback).combinationInfo(eABleCombination);
                    return;
                }
                return;
            case 31:
                a.x a24 = a.x.a(bArr2);
                EABleMenuPage eABleMenuPage = new EABleMenuPage();
                LogUtils.i(this.f2212a, "解析菜单");
                List<a.x.c> e5 = a24.e();
                if (e5 != null && !e5.isEmpty()) {
                    ArrayList arrayList5 = new ArrayList();
                    for (int i2 = 0; i2 < e5.size(); i2++) {
                        a.x.d b7 = e5.get(i2).b();
                        if (b7 == a.x.d.page_null) {
                            arrayList5.add(EABleMenuPage.MenuType.page_null);
                        } else if (b7 == a.x.d.page_heart_rate) {
                            arrayList5.add(EABleMenuPage.MenuType.page_heart_rate);
                        } else if (b7 == a.x.d.page_pressure) {
                            arrayList5.add(EABleMenuPage.MenuType.page_pressure);
                        } else if (b7 == a.x.d.page_weather) {
                            arrayList5.add(EABleMenuPage.MenuType.page_weather);
                        } else if (b7 == a.x.d.page_music) {
                            arrayList5.add(EABleMenuPage.MenuType.page_music);
                        } else if (b7 == a.x.d.page_breath) {
                            arrayList5.add(EABleMenuPage.MenuType.page_breath);
                        } else if (b7 == a.x.d.page_sleep) {
                            arrayList5.add(EABleMenuPage.MenuType.page_sleep);
                        } else if (b7 == a.x.d.page_menstrual_cycle) {
                            arrayList5.add(EABleMenuPage.MenuType.page_menstrual_cycle);
                        } else if (b7 == a.x.d.page_camera) {
                            arrayList5.add(EABleMenuPage.MenuType.page_camera);
                        } else {
                            arrayList5.add(EABleMenuPage.MenuType.page_workout);
                        }
                    }
                    eABleMenuPage.setTypeList(arrayList5);
                }
                List<a.x.c> f4 = a24.f();
                if (f4 != null && !f4.isEmpty()) {
                    ArrayList arrayList6 = new ArrayList();
                    while (r8 < f4.size()) {
                        a.x.d b8 = f4.get(r8).b();
                        if (b8 == a.x.d.page_null) {
                            arrayList6.add(EABleMenuPage.MenuType.page_null);
                        } else if (b8 == a.x.d.page_heart_rate) {
                            arrayList6.add(EABleMenuPage.MenuType.page_heart_rate);
                        } else if (b8 == a.x.d.page_pressure) {
                            arrayList6.add(EABleMenuPage.MenuType.page_pressure);
                        } else if (b8 == a.x.d.page_weather) {
                            arrayList6.add(EABleMenuPage.MenuType.page_weather);
                        } else if (b8 == a.x.d.page_music) {
                            arrayList6.add(EABleMenuPage.MenuType.page_music);
                        } else if (b8 == a.x.d.page_breath) {
                            arrayList6.add(EABleMenuPage.MenuType.page_breath);
                        } else if (b8 == a.x.d.page_sleep) {
                            arrayList6.add(EABleMenuPage.MenuType.page_sleep);
                        } else if (b8 == a.x.d.page_menstrual_cycle) {
                            arrayList6.add(EABleMenuPage.MenuType.page_menstrual_cycle);
                        } else if (b8 == a.x.d.page_camera) {
                            arrayList6.add(EABleMenuPage.MenuType.page_camera);
                        } else {
                            arrayList6.add(EABleMenuPage.MenuType.page_workout);
                        }
                        r8++;
                    }
                    eABleMenuPage.setAllSupportList(arrayList6);
                }
                if (eABleCallback != null) {
                    ((MenuCallback) eABleCallback).menuInfo(eABleMenuPage);
                    return;
                }
                return;
            case 32:
                a.b0 a25 = a.b0.a(bArr2);
                EABlePeriod eABlePeriod = new EABlePeriod();
                List<a.b0.c> d3 = a25.d();
                if (d3 != null && !d3.isEmpty()) {
                    ArrayList arrayList7 = new ArrayList();
                    while (r8 < d3.size()) {
                        a.b0.c cVar3 = d3.get(r8);
                        if (cVar3 != null) {
                            EABlePeriod.EABlePeriodData eABlePeriodData = new EABlePeriod.EABlePeriodData();
                            eABlePeriodData.setTime_stamp(cVar3.d());
                            eABlePeriodData.setDays(cVar3.b());
                            cVar3.c();
                            if (cVar3.c() == a.b0.d.safety_period_1) {
                                eABlePeriodData.setPeriodType(EABlePeriod.PeriodType.safety_period_1);
                            } else if (cVar3.c() == a.b0.d.safety_period_2) {
                                eABlePeriodData.setPeriodType(EABlePeriod.PeriodType.safety_period_2);
                            } else if (cVar3.c() == a.b0.d.menstrual) {
                                eABlePeriodData.setPeriodType(EABlePeriod.PeriodType.menstrual);
                            } else {
                                eABlePeriodData.setPeriodType(EABlePeriod.PeriodType.ovulation);
                            }
                            arrayList7.add(eABlePeriodData);
                        }
                        r8++;
                    }
                    eABlePeriod.setDataList(arrayList7);
                }
                if (eABleCallback != null) {
                    ((PeriodCallback) eABleCallback).periodInfo(eABlePeriod);
                    return;
                }
                return;
            case 33:
                a.r0 a26 = a.r0.a(bArr2);
                EABleWatchFace eABleWatchFace = new EABleWatchFace();
                eABleWatchFace.setId(a26.b());
                eABleWatchFace.setUser_wf_id(a26.c());
                eABleWatchFace.setUser_wf_id_0(a26.d());
                eABleWatchFace.setUser_wf_id_1(a26.e());
                eABleWatchFace.setUser_wf_id_2(a26.f());
                eABleWatchFace.setUser_wf_id_3(a26.g());
                if (eABleCallback != null) {
                    ((WatchFaceCallback) eABleCallback).watchFaceInfo(eABleWatchFace);
                    return;
                }
                return;
            case 34:
                a.k a27 = a.k.a(bArr2);
                EABleInfoPush eABleInfoPush = new EABleInfoPush();
                if (a27 != null && (c = a27.c()) != null && !c.isEmpty()) {
                    ArrayList arrayList8 = new ArrayList();
                    while (r8 < c.size()) {
                        EABleInfoPush.EABlePushSwitch eABlePushSwitch = new EABleInfoPush.EABlePushSwitch();
                        eABlePushSwitch.setSw(c.get(r8).b());
                        arrayList8.add(eABlePushSwitch);
                        r8++;
                    }
                    eABleInfoPush.setS_app_sw(arrayList8);
                }
                if (eABleCallback != null) {
                    ((InfoPushCallback) eABleCallback).pushInfo(eABleInfoPush);
                    return;
                }
                return;
            case 35:
                a.h a28 = a.h.a(bArr2);
                EABleReadDebug eABleReadDebug = new EABleReadDebug();
                if (a28 != null) {
                    eABleReadDebug.setLog(a28.d());
                    eABleReadDebug.setMem_addr(a28.e());
                    eABleReadDebug.setMem_data_len(a28.f());
                    if (a28.c() == a.h.c.error_log) {
                        eABleReadDebug.setType(EABleReadDebug.DebugType.error_log);
                    } else if (a28.c() == a.h.c.mem_log) {
                        eABleReadDebug.setType(EABleReadDebug.DebugType.mem_log);
                    } else if (a28.c() == a.h.c.hr_log_on) {
                        eABleReadDebug.setType(EABleReadDebug.DebugType.hr_log_on);
                    } else if (a28.c() == a.h.c.hr_log_off) {
                        eABleReadDebug.setType(EABleReadDebug.DebugType.hr_log_off);
                    } else if (a28.c() == a.h.c.error_debug) {
                        eABleReadDebug.setType(EABleReadDebug.DebugType.error_debug);
                    }
                }
                if (eABleCallback != null) {
                    ((DeviceBugCallback) eABleCallback).debugInfo(eABleReadDebug);
                    return;
                }
                return;
            case 36:
                a.g a29 = a.g.a(bArr2);
                EABleBloodPressure eABleBloodPressure = new EABleBloodPressure();
                if (a29 != null) {
                    eABleBloodPressure.setHigh_blood_val(a29.b());
                    eABleBloodPressure.setLow_blood_val(a29.c());
                }
                if (eABleCallback != null) {
                    ((BloodPressureCallback) eABleCallback).bloodPressureInfo(eABleBloodPressure);
                    return;
                }
                return;
            case 37:
                a.e a30 = a.e.a(bArr2);
                EABleAutoMonitor eABleAutoMonitor = new EABleAutoMonitor();
                if (a30 != null) {
                    eABleAutoMonitor.setBlood_oxy_check_sw(a30.b());
                    eABleAutoMonitor.setBlood_pressure_check_sw(a30.c());
                    eABleAutoMonitor.setHeart_check_sw(a30.e());
                    eABleAutoMonitor.setCheck_time(a30.d());
                }
                if (eABleCallback != null) {
                    ((HrOxygenPressureCheckCallback) eABleCallback).checkInfo(eABleAutoMonitor);
                    return;
                }
                return;
            case 38:
                a.v a31 = a.v.a(bArr2);
                EABleHabit eABleHabit = new EABleHabit();
                eABleHabit.setId(a31.g());
                a31.e();
                if (a31.f() == 0) {
                    eABleHabit.setE_ops(EABleHabit.HabitualOperation.add);
                } else if (a31.f() == 1) {
                    eABleHabit.setE_ops(EABleHabit.HabitualOperation.edit);
                } else if (a31.f() == 2) {
                    eABleHabit.setE_ops(EABleHabit.HabitualOperation.del);
                } else if (a31.f() == 3) {
                    eABleHabit.setE_ops(EABleHabit.HabitualOperation.del_all);
                }
                List<a.v.f> h3 = a31.h();
                if (h3 != null && !h3.isEmpty()) {
                    ArrayList arrayList9 = new ArrayList();
                    eABleHabit.setItemList(arrayList9);
                    while (r8 < h3.size()) {
                        EABleHabit.HabitItem habitItem = new EABleHabit.HabitItem();
                        habitItem.setContent(h3.get(r8).e());
                        habitItem.setBegin_hour(h3.get(r8).c());
                        habitItem.setBegin_minute(h3.get(r8).d());
                        habitItem.setBlueColor(h3.get(r8).b());
                        habitItem.setDuration(h3.get(r8).f());
                        habitItem.setEnd_hour(h3.get(r8).j());
                        habitItem.setEnd_minute(h3.get(r8).k());
                        habitItem.setGreenColor(h3.get(r8).l());
                        habitItem.setRedColor(h3.get(r8).n());
                        habitItem.setId(h3.get(r8).m());
                        habitItem.setE_icon_id(b(h3.get(r8).i()));
                        habitItem.setE_action(d(h3.get(r8).g()));
                        habitItem.setHabitState(c(h3.get(r8).h()));
                        habitItem.setCycle(h3.get(r8).o());
                        arrayList9.add(habitItem);
                        r8++;
                    }
                }
                if (eABleCallback != null) {
                    ((HabitCallback) eABleCallback).habitInfo(eABleHabit);
                    return;
                }
                return;
            case 39:
                a.w a32 = a.w.a(bArr2);
                EABleHabitRespond eABleHabitRespond = new EABleHabitRespond();
                eABleHabitRespond.setId(a32.d());
                if (a32.c() == 0) {
                    eABleHabitRespond.setResult(EABleHabitRespond.Result.success);
                } else if (a32.c() == 1) {
                    eABleHabitRespond.setResult(EABleHabitRespond.Result.fail);
                } else if (a32.c() == 2) {
                    eABleHabitRespond.setResult(EABleHabitRespond.Result.mem_full);
                } else {
                    eABleHabitRespond.setResult(EABleHabitRespond.Result.time_conflict);
                }
                if (eABleCallback != null) {
                    ((HabitResultCallback) eABleCallback).editResult(eABleHabitRespond);
                    return;
                }
                return;
            case 40:
                a.k0 a33 = a.k0.a(bArr2);
                TodayTotalData todayTotalData = new TodayTotalData();
                todayTotalData.setCalorie(a33.b());
                todayTotalData.setDistance(a33.c());
                todayTotalData.setDuration(a33.d());
                todayTotalData.setSteps(a33.e());
                if (eABleCallback != null) {
                    ((TodayTotalDataCallback) eABleCallback).todayData(todayTotalData);
                    return;
                }
                return;
            case 43:
                a.f0 a34 = a.f0.a(bArr2);
                ArrayList arrayList10 = new ArrayList();
                if (a34 != null && (c2 = a34.c()) != null && !c2.isEmpty()) {
                    while (r8 < c2.size()) {
                        EABleContact eABleContact = new EABleContact();
                        eABleContact.setContactNum(c2.get(r8).b());
                        arrayList10.add(eABleContact);
                        r8++;
                    }
                }
                if (eABleCallback != null) {
                    ((ReadBookListCallback) eABleCallback).bookList(arrayList10);
                    return;
                }
                return;
            case 44:
                a.g0 a35 = a.g0.a(bArr2);
                EABleFeatures eABleFeatures = new EABleFeatures();
                eABleFeatures.setAlarm_settings(a35.b());
                eABleFeatures.setApp_push_settings(a35.d());
                eABleFeatures.setBase_calories_settings(a35.e());
                eABleFeatures.setBlood_pressure_monitoring(a35.f());
                eABleFeatures.setDisturb_setting(a35.h());
                eABleFeatures.setGestures_wake_up_settings(a35.j());
                eABleFeatures.setGoal_achievement_reminder(a35.k());
                eABleFeatures.setGps_setting(a35.l());
                eABleFeatures.setHabit_tracker_color_settings(a35.m());
                eABleFeatures.setHabit_tracker_settings(a35.n());
                eABleFeatures.setHr_monitoring(a35.o());
                eABleFeatures.setHr_warning(a35.p());
                eABleFeatures.setWeather_settings(a35.T());
                eABleFeatures.setWearing_mode_settings(a35.R());
                eABleFeatures.setIncoming_call_reminder(a35.q());
                eABleFeatures.setMail_reminder(a35.r());
                eABleFeatures.setMenstrual_setting(a35.t());
                eABleFeatures.setMenu_settings(a35.u());
                eABleFeatures.setMissed_call_reminder(a35.v());
                eABleFeatures.setPhone_contact(a35.x());
                eABleFeatures.setPressure_monitoring(a35.y());
                eABleFeatures.setRhr_monitoring(a35.A());
                eABleFeatures.setRingtone_support(a35.B());
                eABleFeatures.setSchedule_setting(a35.C());
                eABleFeatures.setSedentary_monitoring(a35.D());
                eABleFeatures.setSleep_monitoring(a35.G());
                eABleFeatures.setSmart_sports_setting(a35.H());
                eABleFeatures.setSms_reminder(a35.I());
                eABleFeatures.setTemperature_monitoring(a35.N());
                eABleFeatures.setUnit_settings(a35.O());
                eABleFeatures.setVibration_mode_settings(a35.Q());
                eABleFeatures.setMonitor_reminder(a35.w());
                eABleFeatures.setBt_one_key_connect(a35.g());
                eABleFeatures.setWeather_air(a35.S());
                eABleFeatures.setFind_watch(a35.i());
                eABleFeatures.setSupport_app_detection(a35.K());
                eABleFeatures.setSupport_app_sport(a35.L());
                eABleFeatures.setSupport_only_get_big_data(a35.M());
                eABleFeatures.setSleep_blood_oxygen_monitor_setting(a35.F());
                eABleFeatures.setStress_monitor_setting(a35.J());
                eABleFeatures.setSend_real_time_data_setting(a35.E());
                eABleFeatures.setReminder_all_in_and_replace_type_setting(a35.z());
                eABleFeatures.setVibrate_intensity_setting(a35.P());
                eABleFeatures.setApp_launch_screen_sport(a35.c());
                eABleFeatures.setMenstrual_reminder(a35.s());
                if (eABleCallback != null) {
                    ((FeaturesCallback) eABleCallback).featuresList(eABleFeatures);
                    return;
                }
                return;
            case 45:
                a.c0 a36 = a.c0.a(bArr2);
                EABleMonitorReminder eABleMonitorReminder = new EABleMonitorReminder();
                eABleMonitorReminder.setBegin_hour(a36.b());
                eABleMonitorReminder.setBegin_minute(a36.c());
                eABleMonitorReminder.setReminderSwitch(a36.k());
                eABleMonitorReminder.setCup(a36.d());
                eABleMonitorReminder.setInterval(a36.i());
                eABleMonitorReminder.setStep_threshold(a36.j());
                eABleMonitorReminder.setEnd_hour(a36.g());
                eABleMonitorReminder.setEnd_minute(a36.h());
                eABleMonitorReminder.setWeek_cycle_bit(a36.l() & (-1));
                if (a36.f() == 0) {
                    eABleMonitorReminder.setEaBleMonitorType(EABleMonitorReminder.EABleMonitorType.drink);
                } else if (a36.f() == 1) {
                    eABleMonitorReminder.setEaBleMonitorType(EABleMonitorReminder.EABleMonitorType.washHands);
                } else if (a36.f() == 2) {
                    eABleMonitorReminder.setEaBleMonitorType(EABleMonitorReminder.EABleMonitorType.takeMedicine);
                }
                if (eABleCallback != null) {
                    ((MonitorReminderCallback) eABleCallback).monitorReminder(eABleMonitorReminder);
                    return;
                }
                return;
            case 50:
                d.h a37 = d.h.a(bArr2);
                EABleSleepBloodSwitch eABleSleepBloodSwitch = new EABleSleepBloodSwitch();
                eABleSleepBloodSwitch.setInterval(a37.b());
                eABleSleepBloodSwitch.setSw(a37.c());
                if (eABleCallback != null) {
                    ((SleepBloodMonitorCallback) eABleCallback).sleepBloodMonitor(eABleSleepBloodSwitch);
                    return;
                }
                return;
            case 51:
                d.i a38 = d.i.a(bArr2);
                if (eABleCallback != null) {
                    ((StressMonitorCallback) eABleCallback).stressSwitch(a38.b());
                    return;
                }
                return;
            case 53:
                int b9 = a.p0.a(bArr2).b();
                if (b9 == 0) {
                    vibrationIntensity = VibrationIntensity.light;
                } else if (b9 == 1) {
                    vibrationIntensity = VibrationIntensity.medium;
                } else if (b9 == 2) {
                    vibrationIntensity = VibrationIntensity.strong;
                } else {
                    vibrationIntensity = VibrationIntensity.not_vibrate;
                }
                if (eABleCallback != null) {
                    ((VibrateCallback) eABleCallback).vibrateMode(vibrationIntensity);
                    return;
                }
                return;
            case 55:
                d.e a39 = d.e.a(bArr2);
                EABlePeriodReminder eABlePeriodReminder = new EABlePeriodReminder();
                eABlePeriodReminder.setPeriodStart(a39.d());
                eABlePeriodReminder.setPeriodEnd(a39.e());
                eABlePeriodReminder.setPregnancyEnd(a39.c());
                eABlePeriodReminder.setPregnancyStart(a39.b());
                if (eABleCallback != null) {
                    ((PeriodReminderCallback) eABleCallback).periodReminderInfo(eABlePeriodReminder);
                    return;
                }
                return;
        }
    }

    public final HabitIcon b(int i) {
        if (i == 0) {
            return HabitIcon.study_01;
        }
        if (i == 1) {
            return HabitIcon.sleep_02;
        }
        if (i == 2) {
            return HabitIcon.study_03;
        }
        if (i == 3) {
            return HabitIcon.chores_04;
        }
        if (i == 4) {
            return HabitIcon.havefun_05;
        }
        if (i == 5) {
            return HabitIcon.drink_06;
        }
        if (i == 6) {
            return HabitIcon.sun_07;
        }
        if (i == 7) {
            return HabitIcon.teeth_08;
        }
        if (i == 8) {
            return HabitIcon.calendar_09;
        }
        if (i == 9) {
            return HabitIcon.piano_10;
        }
        if (i == 10) {
            return HabitIcon.fruit_11;
        }
        if (i == 11) {
            return HabitIcon.medicine_12;
        }
        if (i == 12) {
            return HabitIcon.draw_13;
        }
        if (i == 13) {
            return HabitIcon.target_14;
        }
        if (i == 14) {
            return HabitIcon.dog_15;
        }
        if (i == 15) {
            return HabitIcon.exercise_16;
        }
        if (i == 16) {
            return HabitIcon.bed_17;
        }
        if (i == 17) {
            return HabitIcon.tidyup_18;
        }
        if (i == 18) {
            return HabitIcon.eat_food;
        }
        return HabitIcon.pack_your_bag;
    }

    public final HabitState c(int i) {
        if (i == 0) {
            return HabitState.initial;
        }
        if (i == 1) {
            return HabitState.in_progress;
        }
        if (i == 2) {
            return HabitState.completed;
        }
        return HabitState.cancel;
    }

    public final CommonAction d(int i) {
        if (i == 0) {
            return CommonAction.no_action;
        }
        if (i == 1) {
            return CommonAction.one_long_vibration;
        }
        if (i == 2) {
            return CommonAction.one_short_vibration;
        }
        if (i == 3) {
            return CommonAction.two_long_vibration;
        }
        if (i == 4) {
            return CommonAction.two_short_vibration;
        }
        if (i == 5) {
            return CommonAction.long_vibration;
        }
        if (i == 6) {
            return CommonAction.long_short_vibration;
        }
        if (i == 7) {
            return CommonAction.one_ring;
        }
        if (i == 8) {
            return CommonAction.two_ring;
        }
        if (i == 9) {
            return CommonAction.ring;
        }
        if (i == 10) {
            return CommonAction.one_vibration_ring;
        }
        if (i == 11) {
            return CommonAction.vibration_ring;
        }
        return null;
    }

    public final EABleReminder.ReminderOps e(int i) {
        if (i == 0) {
            return EABleReminder.ReminderOps.add;
        }
        if (i == 1) {
            return EABleReminder.ReminderOps.edit;
        }
        if (i == 2) {
            return EABleReminder.ReminderOps.del;
        }
        if (i == 3) {
            return EABleReminder.ReminderOps.del_remind;
        }
        if (i == 4) {
            return EABleReminder.ReminderOps.del_alarm;
        }
        if (i == 5) {
            return EABleReminder.ReminderOps.del_remind_alarm;
        }
        if (i == 6) {
            return EABleReminder.ReminderOps.replace_type;
        }
        if (i == 7) {
            return EABleReminder.ReminderOps.all_in;
        }
        return EABleReminder.ReminderOps.unknown;
    }

    public final EABleReminder.ReminderType f(int i) {
        if (i == 0) {
            return EABleReminder.ReminderType.alarm;
        }
        if (i == 1) {
            return EABleReminder.ReminderType.sleep;
        }
        if (i == 2) {
            return EABleReminder.ReminderType.sport;
        }
        if (i == 3) {
            return EABleReminder.ReminderType.drink;
        }
        if (i == 4) {
            return EABleReminder.ReminderType.medicine;
        }
        if (i == 5) {
            return EABleReminder.ReminderType.meeting;
        }
        return EABleReminder.ReminderType.user;
    }

    public final EABleAncsSw.EABleAncsSwItem a(a.j.c cVar) {
        EABleAncsSw.EABleAncsSwItem eABleAncsSwItem = new EABleAncsSw.EABleAncsSwItem();
        eABleAncsSwItem.setSw(cVar.f82a <= 0 ? 0 : 1);
        eABleAncsSwItem.setE_action(d(cVar.b));
        return eABleAncsSwItem;
    }

    public final EABleWeather.WeatherType a(int i) {
        if (i == 0) {
            return EABleWeather.WeatherType.clear;
        }
        if (i == 1) {
            return EABleWeather.WeatherType.cloudy;
        }
        if (i == 2) {
            return EABleWeather.WeatherType.gloomy;
        }
        if (i == 3) {
            return EABleWeather.WeatherType.drizzle;
        }
        if (i == 4) {
            return EABleWeather.WeatherType.moderate_rain;
        }
        if (i == 5) {
            return EABleWeather.WeatherType.thunderstorm;
        }
        if (i == 6) {
            return EABleWeather.WeatherType.heavy_rain;
        }
        if (i == 7) {
            return EABleWeather.WeatherType.sleet;
        }
        if (i == 8) {
            return EABleWeather.WeatherType.light_snow;
        }
        if (i == 9) {
            return EABleWeather.WeatherType.moderate_snow;
        }
        if (i == 10) {
            return EABleWeather.WeatherType.heavy_snow;
        }
        if (i == 11) {
            return EABleWeather.WeatherType.typhoon;
        }
        if (i == 12) {
            return EABleWeather.WeatherType.dust;
        }
        if (i == 13) {
            return EABleWeather.WeatherType.sandstorm;
        }
        if (i == 14) {
            return EABleWeather.WeatherType.fog;
        }
        if (i == 15) {
            return EABleWeather.WeatherType.haze;
        }
        return null;
    }

    public final EABleDailyGoal.EABleDaily a(a.r.c cVar) {
        EABleDailyGoal.EABleDaily eABleDaily = new EABleDailyGoal.EABleDaily();
        eABleDaily.setGoal(cVar.b);
        eABleDaily.setSw(cVar.f129a <= 0 ? 0 : 1);
        return eABleDaily;
    }
}
