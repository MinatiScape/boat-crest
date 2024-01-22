package com.ido.ble.f.a;

import com.google.gson.Gson;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.Alarm;
import com.ido.ble.protocol.model.AntiLostMode;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BloodPressureAdjustPara;
import com.ido.ble.protocol.model.BreatheTrain;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DialPlate;
import com.ido.ble.protocol.model.DisplayMode;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.ble.protocol.model.Goal;
import com.ido.ble.protocol.model.HandWearMode;
import com.ido.ble.protocol.model.HeartRateInterval;
import com.ido.ble.protocol.model.HeartRateMeasureMode;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.LongSit;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.ShortCut;
import com.ido.ble.protocol.model.SportModeSort;
import com.ido.ble.protocol.model.SportModeSortV3;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
import com.ido.ble.protocol.model.Units;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.UserInfo;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class c {
    private static c M;
    private ScreenBrightness A;
    private Menstrual B;
    private MenstrualRemind C;
    private CalorieAndDistanceGoal D;
    private WalkReminder E;
    private BreatheTrain F;
    private ActivitySwitch G;
    private SportModeSort H;
    private SportModeSortV3 I;
    private PressureParam J;
    private MenuList K;
    private DrinkWaterReminder L;
    private SPO2Param b;
    private d c;
    private b d;
    private C0597c e;
    private Goal g;
    private UserInfo h;
    private Units i;
    private DialPlate j;
    private ShortCut k;
    private BloodPressureAdjustPara l;
    private LongSit m;
    private AntiLostMode n;
    private HandWearMode o;
    private NoticeReminderSwitchStatus p;
    private HeartRateInterval q;
    public HeartRateMeasureMode r;
    private boolean s;
    private UpHandGesture t;
    private NotDisturbPara u;
    private boolean v;
    private DisplayMode w;
    private boolean x;
    private boolean y;
    private QuickSportMode z;

    /* renamed from: a  reason: collision with root package name */
    private boolean f12226a = false;
    private List<Alarm> f = new ArrayList();

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f12227a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SettingCallBack.SettingType.values().length];
            b = iArr;
            try {
                iArr[SettingCallBack.SettingType.ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SettingCallBack.SettingType.GOAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SettingCallBack.SettingType.USER_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SettingCallBack.SettingType.UNIT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[SettingCallBack.SettingType.DIAL_PLATE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[SettingCallBack.SettingType.SHORTCUT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[SettingCallBack.SettingType.BLOOD_ADJUST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[SettingCallBack.SettingType.LONG_SIT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[SettingCallBack.SettingType.ANTI_LOST_MODE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[SettingCallBack.SettingType.HAND_MODE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[SettingCallBack.SettingType.HEART_RATE_INTERVAL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[SettingCallBack.SettingType.FIND_PHONE_SWITCH.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[SettingCallBack.SettingType.UP_HAND_GESTURE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[SettingCallBack.SettingType.NOT_DISTURB.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[SettingCallBack.SettingType.MUSIC_SWITCH.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[SettingCallBack.SettingType.DISPLAY_MODE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                b[SettingCallBack.SettingType.ONE_KEY_SOS.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                b[SettingCallBack.SettingType.WEATHER_SWITCH.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                b[SettingCallBack.SettingType.QUICK_SPORT_MODE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                b[SettingCallBack.SettingType.SCREEN_BRIGHTNESS.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                b[SettingCallBack.SettingType.BREATHE_TRAIN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                b[SettingCallBack.SettingType.WALK_REMINDER.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                b[SettingCallBack.SettingType.ACTIVITY_SWITCH.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                b[SettingCallBack.SettingType.DRINK_WATER_REMINDER.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                b[SettingCallBack.SettingType.MENU_LIST_SET.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                b[SettingCallBack.SettingType.SPORT_SORT_V3.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            int[] iArr2 = new int[OtherProtocolCallBack.SettingType.values().length];
            f12227a = iArr2;
            try {
                iArr2[OtherProtocolCallBack.SettingType.MENSTRUAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                f12227a[OtherProtocolCallBack.SettingType.MENSTRUAL_REMIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                f12227a[OtherProtocolCallBack.SettingType.CALORIE_DISTANCE_GOAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                f12227a[OtherProtocolCallBack.SettingType.SPORT_MODE_SORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                f12227a[OtherProtocolCallBack.SettingType.SPO2.ordinal()] = 5;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                f12227a[OtherProtocolCallBack.SettingType.PRESSURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused34) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b extends com.ido.ble.callback.a {
        public b() {
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetBasicInfo(BasicInfo basicInfo) {
            c.this.a(basicInfo);
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo) {
            if (c.this.f12226a) {
                c.this.a(supportFunctionInfo);
                c.this.f12226a = false;
            }
        }

        @Override // com.ido.ble.callback.a, com.ido.ble.callback.GetDeviceInfoCallBack.ICallBack
        public void onGetTime(SystemTime systemTime) {
        }
    }

    /* renamed from: com.ido.ble.f.a.c$c  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0597c implements OtherProtocolCallBack.ICallBack {
        private C0597c() {
        }

        public /* synthetic */ C0597c(c cVar, a aVar) {
            this();
        }

        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onFailed(OtherProtocolCallBack.SettingType settingType) {
            String str;
            int i = a.f12227a[settingType.ordinal()];
            if (i == 1) {
                str = "[SET_PARA] setMenstrual failed!";
            } else if (i == 2) {
                str = "[SET_PARA] setMenstrualRemind failed!";
            } else if (i == 3) {
                str = "[SET_PARA] setCalorieAndDistanceGoal failed!";
            } else if (i == 4) {
                str = "[SET_PARA] setSportModeSort failed!";
            } else if (i != 6) {
                return;
            } else {
                str = "[SET_PARA] setPressurePara failed!";
            }
            LogTool.b(com.ido.ble.logs.a.f12307a, str);
        }

        @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
        public void onSuccess(OtherProtocolCallBack.SettingType settingType) {
            switch (a.f12227a[settingType.ordinal()]) {
                case 1:
                    c.this.v();
                    return;
                case 2:
                    c.this.w();
                    return;
                case 3:
                    c.this.n();
                    return;
                case 4:
                    c.this.G();
                    return;
                case 5:
                    c.this.F();
                    return;
                case 6:
                    c.this.C();
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes11.dex */
    public class d implements SettingCallBack.ICallBack {
        public d() {
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType settingType) {
            String str;
            switch (a.b[settingType.ordinal()]) {
                case 1:
                    str = "[SET_PARA] setAlarm failed!";
                    break;
                case 2:
                    str = "[SET_PARA] setGoal failed!";
                    break;
                case 3:
                    str = "[SET_PARA] setUserInfo failed!";
                    break;
                case 4:
                    str = "[SET_PARA] setUnits failed!";
                    break;
                case 5:
                    str = "[SET_PARA] setDialPlate failed!";
                    break;
                case 6:
                    str = "[SET_PARA] setShortCut failed!";
                    break;
                case 7:
                    str = "[SET_PARA] setBloodPressureAdjustPara failed!";
                    break;
                case 8:
                    str = "[SET_PARA] setLongSit failed!";
                    break;
                case 9:
                    str = "[SET_PARA] setAntiLostMode failed!";
                    break;
                case 10:
                    str = "[SET_PARA] setHandMode failed!";
                    break;
                case 11:
                    str = "[SET_PARA] setHeartRateInterval failed!";
                    break;
                case 12:
                case 22:
                case 23:
                case 24:
                case 25:
                default:
                    return;
                case 13:
                    str = "[SET_PARA] setHeartRateMeasureMode failed!";
                    break;
                case 14:
                    str = "[SET_PARA] setFindPhoneSwitch failed!";
                    break;
                case 15:
                    str = "[SET_PARA] setUpHandGesture failed!";
                    break;
                case 16:
                    str = "[SET_PARA] setNotDisturbPara failed!";
                    break;
                case 17:
                    str = "[SET_PARA] setMusicSwitch failed!";
                    break;
                case 18:
                    str = "[SET_PARA] setDisplayMode failed!";
                    break;
                case 19:
                    str = "[SET_PARA] setOneKeySOSSwitch failed!";
                    break;
                case 20:
                    str = "[SET_PARA] setWeatherSwitch failed!";
                    break;
                case 21:
                    str = "[SET_PARA] setQuickSportMode failed!";
                    break;
                case 26:
                    str = "[SET_PARA] setDrinkWaterReminder failed!";
                    break;
                case 27:
                    str = "[SET_PARA] setMenuList failed!";
                    break;
            }
            LogTool.b(com.ido.ble.logs.a.f12307a, str);
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
            switch (a.b[settingType.ordinal()]) {
                case 1:
                    c.this.j();
                    return;
                case 2:
                    c.this.r();
                    return;
                case 3:
                    c.this.K();
                    return;
                case 4:
                    c.this.I();
                    return;
                case 5:
                    c.this.o();
                    return;
                case 6:
                    c.this.E();
                    return;
                case 7:
                    c.this.l();
                    return;
                case 8:
                    c.this.u();
                    return;
                case 9:
                    c.this.k();
                    return;
                case 10:
                    c.this.s();
                    return;
                case 11:
                    c.this.t();
                    return;
                case 12:
                    c.this.a(obj);
                    return;
                case 13:
                    c.this.d();
                    return;
                case 14:
                    c.this.c();
                    return;
                case 15:
                    c.this.J();
                    return;
                case 16:
                    c.this.z();
                    return;
                case 17:
                    c.this.y();
                    return;
                case 18:
                    c.this.p();
                    return;
                case 19:
                    c.this.B();
                    return;
                case 20:
                    c.this.L();
                    return;
                case 21:
                    c.this.D();
                    return;
                case 22:
                    c.this.e();
                    return;
                case 23:
                    c.this.m();
                    return;
                case 24:
                    c.this.f();
                    return;
                case 25:
                    c.this.i();
                    return;
                case 26:
                    c.this.q();
                    return;
                case 27:
                    c.this.x();
                    return;
                case 28:
                    c.this.H();
                    return;
                default:
                    return;
            }
        }
    }

    public c() {
        if (this.c != null) {
            com.ido.ble.callback.b.N().b(this.c);
        }
        if (this.e != null) {
            com.ido.ble.callback.b.N().b(this.e);
        }
        this.c = new d();
        com.ido.ble.callback.b.N().a(this.c);
        this.e = new C0597c(this, null);
        com.ido.ble.callback.b.N().a(this.e);
    }

    private void A() {
        if (this.p == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.p);
        LogTool.d(com.ido.ble.logs.a.h, "[saveNoticeReminderSwitchStatus]," + this.p.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        com.ido.ble.f.a.f.a.g0().c(this.x);
        LogTool.d(com.ido.ble.logs.a.h, "[saveOneKeySOSSwitch]," + this.x);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        if (this.J != null) {
            com.ido.ble.f.a.f.a.g0().a(this.J);
            LogTool.d(com.ido.ble.logs.a.h, "[savePressureParam]," + this.J.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        if (this.z == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.z);
        LogTool.d(com.ido.ble.logs.a.h, "[saveQuickSportMode]," + this.z.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        if (this.k == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.k);
        LogTool.d(com.ido.ble.logs.a.h, "[saveShortCut]," + this.k.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.b != null) {
            com.ido.ble.f.a.f.a.g0().a(this.b);
            LogTool.d(com.ido.ble.logs.a.h, "[saveSpO2Param]," + this.b.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        if (this.H != null) {
            com.ido.ble.f.a.f.a.g0().a(this.H);
            LogTool.d(com.ido.ble.logs.a.h, "[saveSportModeSort]," + this.H.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        if (this.I != null) {
            com.ido.ble.f.a.f.a.g0().a(this.I);
            LogTool.d(com.ido.ble.logs.a.h, "[saveSportModeSortV3]," + this.I.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        if (this.i == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.i);
        LogTool.d(com.ido.ble.logs.a.h, "[saveUnits]," + this.i.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        if (this.t == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.t);
        LogTool.d(com.ido.ble.logs.a.h, "[saveUpHandGesture]," + this.t.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K() {
        if (this.h == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.h);
        LogTool.d(com.ido.ble.logs.a.h, "[saveUserInfo]," + this.h.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L() {
        com.ido.ble.f.a.f.a.g0().d(this.y);
        LogTool.d(com.ido.ble.logs.a.h, "[saveWeatherSwitch]," + this.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BasicInfo basicInfo) {
        if (basicInfo != null) {
            com.ido.ble.f.a.f.a.g0().a(basicInfo);
            LogTool.d(com.ido.ble.logs.a.h, "[saveBasicInfo]," + basicInfo.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(SupportFunctionInfo supportFunctionInfo) {
        if (supportFunctionInfo == null) {
            LogTool.b(com.ido.ble.logs.a.h, "[saveSupportFunction], supportFunctionInfo = null");
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(supportFunctionInfo);
        LogTool.d(com.ido.ble.logs.a.h, "[saveSupportFunction]," + supportFunctionInfo.toString());
    }

    public static c h() {
        if (M == null) {
            M = new c();
        }
        return M;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.G != null) {
            com.ido.ble.f.a.f.a.g0().a(this.G);
            LogTool.d(com.ido.ble.logs.a.h, "[saveActivitySwitch]," + this.G.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.f.size() == 0) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.f);
        LogTool.d(com.ido.ble.logs.a.h, "[saveAlarm]," + new Gson().toJson(this.f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.n == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.n);
        LogTool.d(com.ido.ble.logs.a.h, "[saveAntiLostMode]," + this.n.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (this.l == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.l);
        LogTool.d(com.ido.ble.logs.a.h, "[saveBloodPressureAdjustPara]," + this.l.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.F != null) {
            com.ido.ble.f.a.f.a.g0().a(this.F);
            LogTool.d(com.ido.ble.logs.a.h, "[saveBreatheTrain]," + this.F.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.D != null) {
            com.ido.ble.f.a.f.a.g0().a(this.D);
            LogTool.d(com.ido.ble.logs.a.h, "[saveCalorieAndDistanceGoal]," + this.D.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.j == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.j);
        LogTool.d(com.ido.ble.logs.a.h, "[saveDialPlate]," + this.j.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.w == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.w);
        LogTool.d(com.ido.ble.logs.a.h, "[saveDisplayMode]," + this.w.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.L != null) {
            com.ido.ble.f.a.f.a.g0().a(this.L);
            LogTool.d(com.ido.ble.logs.a.h, "[saveDrinkWaterReminder]," + this.L.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        if (this.g == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.g);
        LogTool.d(com.ido.ble.logs.a.h, "[saveGoal]," + this.g.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (this.o == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.o);
        LogTool.d(com.ido.ble.logs.a.h, "[saveHandMode]," + this.o.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.q == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.q);
        LogTool.d(com.ido.ble.logs.a.h, "[saveHeartRateInterval]," + this.q.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (this.m == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.m);
        LogTool.d(com.ido.ble.logs.a.h, "[saveLongSit]," + this.m.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.B != null) {
            com.ido.ble.f.a.f.a.g0().a(this.B);
            LogTool.d(com.ido.ble.logs.a.h, "[saveMenstrualPara]," + this.B.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.C != null) {
            com.ido.ble.f.a.f.a.g0().a(this.C);
            LogTool.d(com.ido.ble.logs.a.h, "[saveMenstrualRemind]," + this.C.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (this.K != null) {
            com.ido.ble.f.a.f.a.g0().a(this.K);
            LogTool.d(com.ido.ble.logs.a.h, "[saveMenuList]," + this.K.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        com.ido.ble.f.a.f.a.g0().b(this.v);
        LogTool.d(com.ido.ble.logs.a.h, "[saveMusicSwitch]," + this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        if (this.u == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.u);
        LogTool.d(com.ido.ble.logs.a.h, "[saveNotDisturbPara]," + this.u.toString());
    }

    public void a() {
        if (this.c != null) {
            com.ido.ble.callback.b.N().b(this.c);
        }
        if (this.d != null) {
            com.ido.ble.callback.b.N().b(this.d);
        }
        M = null;
    }

    public void a(ActivitySwitch activitySwitch) {
        this.G = activitySwitch;
    }

    public void a(AntiLostMode antiLostMode) {
        this.n = antiLostMode;
    }

    public void a(BloodPressureAdjustPara bloodPressureAdjustPara) {
        this.l = bloodPressureAdjustPara;
    }

    public void a(BreatheTrain breatheTrain) {
        this.F = breatheTrain;
    }

    public void a(CalorieAndDistanceGoal calorieAndDistanceGoal) {
        this.D = calorieAndDistanceGoal;
    }

    public void a(DialPlate dialPlate) {
        this.j = dialPlate;
    }

    public void a(DisplayMode displayMode) {
        this.w = displayMode;
    }

    public void a(DrinkWaterReminder drinkWaterReminder) {
        this.L = drinkWaterReminder;
    }

    public void a(Goal goal) {
        this.g = goal;
    }

    public void a(HandWearMode handWearMode) {
        this.o = handWearMode;
    }

    public void a(HeartRateInterval heartRateInterval) {
        this.q = heartRateInterval;
    }

    public void a(HeartRateMeasureMode heartRateMeasureMode) {
        this.r = heartRateMeasureMode;
    }

    public void a(LongSit longSit) {
        this.m = longSit;
    }

    public void a(Menstrual menstrual) {
        this.B = menstrual;
    }

    public void a(MenstrualRemind menstrualRemind) {
        this.C = menstrualRemind;
    }

    public void a(MenuList menuList) {
        this.K = menuList;
    }

    public void a(NotDisturbPara notDisturbPara) {
        this.u = notDisturbPara;
    }

    public void a(NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        this.p = noticeReminderSwitchStatus;
    }

    public void a(PressureParam pressureParam) {
        this.J = pressureParam;
    }

    public void a(QuickSportMode quickSportMode) {
        this.z = quickSportMode;
    }

    public void a(SPO2Param sPO2Param) {
        this.b = sPO2Param;
    }

    public void a(ScreenBrightness screenBrightness) {
        this.A = screenBrightness;
    }

    public void a(ShortCut shortCut) {
        this.k = shortCut;
    }

    public void a(SportModeSort sportModeSort) {
        this.H = sportModeSort;
    }

    public void a(SportModeSortV3 sportModeSortV3) {
        this.I = sportModeSortV3;
    }

    public void a(Units units) {
        this.i = units;
    }

    public void a(UpHandGesture upHandGesture) {
        this.t = upHandGesture;
    }

    public void a(UserInfo userInfo) {
        this.h = userInfo;
    }

    public void a(WalkReminder walkReminder) {
        this.E = walkReminder;
    }

    public void a(Object obj) {
        if (obj == null) {
            return;
        }
        HeartRateMeasureModeV3 heartRateMeasureModeV3 = obj instanceof HeartRateMeasureModeV3 ? (HeartRateMeasureModeV3) obj : null;
        if (heartRateMeasureModeV3 == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(heartRateMeasureModeV3);
        LogTool.d(com.ido.ble.logs.a.h, "[saveHeartRateMeasureModeV3]," + obj.toString());
    }

    public void a(List<Alarm> list) {
        this.f.clear();
        this.f.addAll(list);
    }

    public void a(boolean z) {
        this.s = z;
    }

    public void b() {
        if (this.d != null) {
            com.ido.ble.callback.b.N().b(this.d);
        }
        this.d = new b();
        com.ido.ble.callback.b.N().a(this.d);
    }

    public void b(boolean z) {
        this.v = z;
    }

    public void c() {
        com.ido.ble.f.a.f.a.g0().a(this.s);
        LogTool.d(com.ido.ble.logs.a.h, "[saveFindPhoneSwitch]," + this.s);
    }

    public void c(boolean z) {
        this.x = z;
    }

    public void d() {
        if (this.r == null) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.r);
        LogTool.d(com.ido.ble.logs.a.h, "[saveHeartRateMeasureMode]," + this.r.toString());
    }

    public void d(boolean z) {
        this.y = z;
    }

    public void e() {
        ScreenBrightness screenBrightness = this.A;
        if (screenBrightness == null || screenBrightness.opera == 0) {
            return;
        }
        com.ido.ble.f.a.f.a.g0().a(this.A);
        LogTool.d(com.ido.ble.logs.a.h, "[saveScreenBrightness]," + this.A.toString());
    }

    public void f() {
        if (this.E != null) {
            com.ido.ble.f.a.f.a.g0().a(this.E);
            LogTool.d(com.ido.ble.logs.a.h, "[saveWalkReminder]," + this.E.toString());
        }
    }

    public void g() {
        this.f12226a = true;
    }
}
