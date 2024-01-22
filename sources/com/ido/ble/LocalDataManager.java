package com.ido.ble;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
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
import com.ido.ble.protocol.model.PressureParam;
import com.ido.ble.protocol.model.QuickSportMode;
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
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class LocalDataManager {
    public static void clearAllSettingPara() {
        com.ido.ble.f.a.f.a.g0().c();
    }

    public static void deleteAllHealthDbData() {
        com.ido.ble.f.a.b.d().a();
    }

    public static ActivitySwitch getActivitySwitch() {
        return com.ido.ble.f.a.f.a.g0().d();
    }

    public static List<Alarm> getAlarm() {
        return com.ido.ble.f.a.f.a.g0().e();
    }

    public static List<HealthGps> getAllHealthGps() {
        return com.ido.ble.gps.database.a.a();
    }

    public static AntiLostMode getAntiLostMode() {
        return com.ido.ble.f.a.f.a.g0().g();
    }

    public static BasicInfo getBasicInfo() {
        return com.ido.ble.f.a.f.a.g0().h();
    }

    public static String getBindMacAddress() {
        return com.ido.ble.bluetooth.a.d();
    }

    public static List<String> getBindMacAddressList() {
        return com.ido.ble.f.a.f.b.e().b();
    }

    public static BloodPressureAdjustPara getBloodPressureAdjustPara() {
        return com.ido.ble.f.a.f.a.g0().i();
    }

    public static BreatheTrain getBreatheTrain() {
        return com.ido.ble.f.a.f.a.g0().l();
    }

    public static CalorieAndDistanceGoal getCalorieAndDistanceGoal() {
        return com.ido.ble.f.a.f.a.g0().n();
    }

    public static BLEDevice getCurrentDeviceInfo() {
        return com.ido.ble.f.a.f.a.g0().o();
    }

    public static DialPlate getDialPlate() {
        return com.ido.ble.f.a.f.a.g0().p();
    }

    public static DisplayMode getDisplayMode() {
        return com.ido.ble.f.a.f.a.g0().q();
    }

    public static DrinkWaterReminder getDrinkWaterReminder() {
        return com.ido.ble.f.a.f.a.g0().r();
    }

    public static boolean getFindPhoneSwitch() {
        return com.ido.ble.f.a.f.a.g0().s();
    }

    public static Goal getGoal() {
        return com.ido.ble.f.a.f.a.g0().u();
    }

    public static HandWearMode getHandWearMode() {
        return com.ido.ble.f.a.f.a.g0().w();
    }

    public static List<HealthActivity> getHealthActivityByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().a(whereCondition, whereConditionArr);
    }

    public static List<HealthActivity> getHealthActivityByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().p(i, i2, i3);
    }

    public static List<HealthActivity> getHealthActivityByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().a(i, i2);
    }

    public static List<HealthActivity> getHealthActivityByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().b(i, i2);
    }

    public static List<HealthActivity> getHealthActivityByYear(int i) {
        return com.ido.ble.f.a.b.d().a(i);
    }

    public static List<HealthBloodPressed> getHealthBloodPressedByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().b(whereCondition, whereConditionArr);
    }

    public static HealthBloodPressed getHealthBloodPressedByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().q(i, i2, i3);
    }

    public static List<HealthBloodPressed> getHealthBloodPressedByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().c(i, i2);
    }

    public static List<HealthBloodPressed> getHealthBloodPressedByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().d(i, i2);
    }

    public static List<HealthBloodPressed> getHealthBloodPressedByYear(int i) {
        return com.ido.ble.f.a.b.d().c(i);
    }

    public static List<HealthBloodPressedItem> getHealthBloodPressedItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().c(whereCondition, whereConditionArr);
    }

    public static List<HealthBloodPressedItem> getHealthBloodPressedItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().r(i, i2, i3);
    }

    public static List<HealthBloodPressedItem> getHealthBloodPressedItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().e(i, i2);
    }

    public static List<HealthBloodPressedItem> getHealthBloodPressedItemByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().f(i, i2);
    }

    public static List<HealthBloodPressedItem> getHealthBloodPressedItemByYear(int i) {
        return com.ido.ble.f.a.b.d().e(i);
    }

    public static List<HealthGps> getHealthGpsByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.gps.database.a.a(whereCondition, whereConditionArr);
    }

    public static HealthGps getHealthGpsByDate(int i, int i2, int i3) {
        return com.ido.ble.gps.database.a.b(i, i2, i3);
    }

    public static List<HealthGps> getHealthGpsByMonth(int i, int i2) {
        return com.ido.ble.gps.database.a.a(i, i2);
    }

    public static List<HealthGps> getHealthGpsByWeek(int i) {
        return com.ido.ble.gps.database.a.a(i);
    }

    public static List<HealthGps> getHealthGpsByWeek(int i, int i2) {
        return com.ido.ble.gps.database.a.b(i, i2);
    }

    public static List<HealthGps> getHealthGpsByYear(int i) {
        return com.ido.ble.gps.database.a.b(i);
    }

    public static List<HealthGpsItem> getHealthGpsItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.gps.database.a.b(whereCondition, whereConditionArr);
    }

    public static List<HealthGpsItem> getHealthGpsItemByDate(long j) {
        return com.ido.ble.gps.database.a.a(j);
    }

    public static List<HealthHeartRate> getHealthHeartRateByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().d(whereCondition, whereConditionArr);
    }

    public static HealthHeartRate getHealthHeartRateByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().s(i, i2, i3);
    }

    public static List<HealthHeartRate> getHealthHeartRateByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().g(i, i2);
    }

    public static List<HealthHeartRate> getHealthHeartRateByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().h(i, i2);
    }

    public static List<HealthHeartRate> getHealthHeartRateByYear(int i) {
        return com.ido.ble.f.a.b.d().g(i);
    }

    public static List<HealthHeartRateItem> getHealthHeartRateItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().e(whereCondition, whereConditionArr);
    }

    public static List<HealthHeartRateItem> getHealthHeartRateItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().t(i, i2, i3);
    }

    public static List<HealthHeartRateItem> getHealthHeartRateItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().i(i, i2);
    }

    public static List<HealthHeartRateItem> getHealthHeartRateItemByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().j(i, i2);
    }

    public static List<HealthHeartRateItem> getHealthHeartRateItemByYear(int i) {
        return com.ido.ble.f.a.b.d().i(i);
    }

    public static List<HealthHeartRateSecond> getHealthHeartRateSecondByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().f(whereCondition, whereConditionArr);
    }

    public static HealthHeartRateSecond getHealthHeartRateSecondByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().u(i, i2, i3);
    }

    public static List<HealthHeartRateSecond> getHealthHeartRateSecondByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().k(i, i2);
    }

    public static List<HealthHeartRateSecond> getHealthHeartRateSecondByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().l(i, i2);
    }

    public static List<HealthHeartRateSecond> getHealthHeartRateSecondByYear(int i) {
        return com.ido.ble.f.a.b.d().k(i);
    }

    public static List<HealthPressure> getHealthPressureByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().g(whereCondition, whereConditionArr);
    }

    public static HealthPressure getHealthPressureByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().v(i, i2, i3);
    }

    public static List<HealthPressure> getHealthPressureByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().m(i, i2);
    }

    public static List<HealthPressure> getHealthPressureByWeek(int i) {
        return com.ido.ble.f.a.b.d().n(i, 0);
    }

    public static List<HealthPressure> getHealthPressureByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().n(i, i2);
    }

    public static List<HealthPressure> getHealthPressureByYear(int i) {
        return com.ido.ble.f.a.b.d().m(i);
    }

    public static List<HealthPressureItem> getHealthPressureItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().h(whereCondition, whereConditionArr);
    }

    public static List<HealthPressureItem> getHealthPressureItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().w(i, i2, i3);
    }

    public static List<HealthPressureItem> getHealthPressureItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().o(i, i2);
    }

    public static List<HealthPressureItem> getHealthPressureItemByWeek(int i) {
        return com.ido.ble.f.a.b.d().p(i, 0);
    }

    public static List<HealthPressureItem> getHealthPressureItemByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().p(i, i2);
    }

    public static List<HealthPressureItem> getHealthPressureItemByYear(int i) {
        return com.ido.ble.f.a.b.d().o(i);
    }

    public static List<HealthSleep> getHealthSleepByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().i(whereCondition, whereConditionArr);
    }

    public static HealthSleep getHealthSleepByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().x(i, i2, i3);
    }

    public static List<HealthSleep> getHealthSleepByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().q(i, i2);
    }

    public static List<HealthSleep> getHealthSleepByYear(int i) {
        return com.ido.ble.f.a.b.d().q(i);
    }

    public static List<HealthSleepItem> getHealthSleepItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().j(whereCondition, whereConditionArr);
    }

    public static List<HealthSleepItem> getHealthSleepItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().y(i, i2, i3);
    }

    public static List<HealthSleepItem> getHealthSleepItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().s(i, i2);
    }

    public static List<HealthSleepItem> getHealthSleepItemByYear(int i) {
        return com.ido.ble.f.a.b.d().s(i);
    }

    public static List<HealthSpO2> getHealthSpO2ByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().k(whereCondition, whereConditionArr);
    }

    public static HealthSpO2 getHealthSpO2ByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().z(i, i2, i3);
    }

    public static List<HealthSpO2> getHealthSpO2ByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().u(i, i2);
    }

    public static List<HealthSpO2> getHealthSpO2ByWeek(int i) {
        return com.ido.ble.f.a.b.d().v(i, 0);
    }

    public static List<HealthSpO2> getHealthSpO2ByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().v(i, i2);
    }

    public static List<HealthSpO2> getHealthSpO2ByYear(int i) {
        return com.ido.ble.f.a.b.d().u(i);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().l(whereCondition, whereConditionArr);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().A(i, i2, i3);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().w(i, i2);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByWeek(int i) {
        return com.ido.ble.f.a.b.d().x(i, 0);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().x(i, i2);
    }

    public static List<HealthSpO2Item> getHealthSpO2ItemByYear(int i) {
        return com.ido.ble.f.a.b.d().w(i);
    }

    public static List<HealthSport> getHealthSportByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().m(whereCondition, whereConditionArr);
    }

    public static HealthSport getHealthSportByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().B(i, i2, i3);
    }

    public static List<HealthSport> getHealthSportByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().y(i, i2);
    }

    public static List<HealthSport> getHealthSportByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().z(i, i2);
    }

    public static List<HealthSport> getHealthSportByYear(int i) {
        return com.ido.ble.f.a.b.d().y(i);
    }

    public static List<HealthSportItem> getHealthSportItemByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().n(whereCondition, whereConditionArr);
    }

    public static List<HealthSportItem> getHealthSportItemByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().C(i, i2, i3);
    }

    public static List<HealthSportItem> getHealthSportItemByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().A(i, i2);
    }

    public static List<HealthSportItem> getHealthSportItemByYear(int i) {
        return com.ido.ble.f.a.b.d().A(i);
    }

    public static List<HealthSwimming> getHealthSwimmingByCustom(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        return com.ido.ble.f.a.b.d().o(whereCondition, whereConditionArr);
    }

    public static List<HealthSwimming> getHealthSwimmingByDay(int i, int i2, int i3) {
        return com.ido.ble.f.a.b.d().D(i, i2, i3);
    }

    public static List<HealthSwimming> getHealthSwimmingByMonth(int i, int i2) {
        return com.ido.ble.f.a.b.d().C(i, i2);
    }

    public static List<HealthSwimming> getHealthSwimmingByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().D(i, i2);
    }

    public static List<HealthSwimming> getHealthSwimmingByYear(int i) {
        return com.ido.ble.f.a.b.d().B(i);
    }

    public static HeartRateInterval getHeartRateInterval() {
        return com.ido.ble.f.a.f.a.g0().x();
    }

    public static HeartRateMeasureMode getHeartRateMode() {
        return com.ido.ble.f.a.f.a.g0().y();
    }

    public static HeartRateMeasureModeV3 getHeartRateModeV3() {
        return com.ido.ble.f.a.f.a.g0().z();
    }

    public static BLEDevice getLastConnectedDeviceInfo() {
        return com.ido.ble.f.a.f.b.e().c();
    }

    public static List<HealthSleep> getListHealthSleepByWeek(int i, int i2) {
        return com.ido.ble.f.a.b.d().r(i, i2);
    }

    public static LongSit getLongSit() {
        return com.ido.ble.f.a.f.a.g0().E();
    }

    public static Menstrual getMenstrual() {
        return com.ido.ble.f.a.f.a.g0().F();
    }

    public static MenstrualRemind getMenstrualRemind() {
        return com.ido.ble.f.a.f.a.g0().G();
    }

    public static MenuList getMenuList() {
        return com.ido.ble.f.a.f.a.g0().H();
    }

    public static boolean getMusicSwitch() {
        return com.ido.ble.f.a.f.a.g0().I();
    }

    public static NotDisturbPara getNotDisturbPara() {
        return com.ido.ble.f.a.f.a.g0().L();
    }

    public static boolean getOneKeySOSSwitch() {
        return com.ido.ble.f.a.f.a.g0().N();
    }

    public static PressureParam getPressureParam() {
        return com.ido.ble.f.a.f.a.g0().O();
    }

    public static QuickSportMode getQuickSportMode() {
        return com.ido.ble.f.a.f.a.g0().P();
    }

    public static String getSDKVeriosn() {
        return "2.66.69";
    }

    public static boolean getScreenBrightnessAutoAdjustNightSwitch() {
        return com.ido.ble.f.a.f.a.g0().R();
    }

    public static ScreenBrightness getScreenBrigthnessConfig() {
        return com.ido.ble.f.a.f.a.g0().S();
    }

    public static ShortCut getShortCut() {
        return com.ido.ble.f.a.f.a.g0().T();
    }

    public static SleepMonitoringPara getSleepMonitoringPara() {
        return com.ido.ble.f.a.f.a.g0().U();
    }

    public static SPO2Param getSpO2Param() {
        return com.ido.ble.f.a.f.a.g0().V();
    }

    public static SportModeSort getSportModeSort() {
        return com.ido.ble.f.a.f.a.g0().W();
    }

    public static SportModeSortV3 getSportModeSortV3() {
        return com.ido.ble.f.a.f.a.g0().X();
    }

    public static SupportFunctionInfo getSupportFunctionInfo() {
        return com.ido.ble.f.a.f.a.g0().Z();
    }

    public static SupportFunctionInfo getSupportFunctionInfo(String str) {
        return com.ido.ble.f.a.f.a.f(str).Z();
    }

    public static Units getUnits() {
        return com.ido.ble.f.a.f.a.g0().a0();
    }

    public static UpHandGesture getUpHandGesture() {
        return com.ido.ble.f.a.f.a.g0().b0();
    }

    public static UserInfo getUserInfo() {
        return com.ido.ble.f.a.f.a.g0().c0();
    }

    public static WalkReminder getWalkReminder() {
        return com.ido.ble.f.a.f.a.g0().d0();
    }

    public static boolean getWeatherSwitch() {
        return com.ido.ble.f.a.f.a.g0().e0();
    }

    public static boolean isBind() {
        return com.ido.ble.bluetooth.a.g();
    }
}
