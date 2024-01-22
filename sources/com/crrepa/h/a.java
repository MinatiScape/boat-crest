package com.crrepa.h;

import android.bluetooth.BluetoothGatt;
import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.annotation.IntRange;
import com.clevertap.android.sdk.Constants;
import com.crrepa.ble.conn.CRPBleConnection;
import com.crrepa.ble.conn.bean.CRPAlarmClockInfo;
import com.crrepa.ble.conn.bean.CRPContactInfo;
import com.crrepa.ble.conn.bean.CRPCustomizeWatchFaceInfo;
import com.crrepa.ble.conn.bean.CRPDrinkWaterPeriodInfo;
import com.crrepa.ble.conn.bean.CRPFunctionInfo;
import com.crrepa.ble.conn.bean.CRPFutureWeatherInfo;
import com.crrepa.ble.conn.bean.CRPHandWashingPeriodInfo;
import com.crrepa.ble.conn.bean.CRPMessageInfo;
import com.crrepa.ble.conn.bean.CRPPeriodTimeInfo;
import com.crrepa.ble.conn.bean.CRPPhysiologcalPeriodInfo;
import com.crrepa.ble.conn.bean.CRPPillReminderInfo;
import com.crrepa.ble.conn.bean.CRPSedentaryReminderPeriodInfo;
import com.crrepa.ble.conn.bean.CRPSleepInfo;
import com.crrepa.ble.conn.bean.CRPTodayWeatherInfo;
import com.crrepa.ble.conn.bean.CRPUserInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceBackgroundInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceLayoutInfo;
import com.crrepa.ble.conn.callback.CRPBtAddressCallback;
import com.crrepa.ble.conn.callback.CRPContactConfigCallback;
import com.crrepa.ble.conn.callback.CRPContactCountCallback;
import com.crrepa.ble.conn.callback.CRPDeviceAlarmClockCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBondStateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBreathingLightCallback;
import com.crrepa.ble.conn.callback.CRPDeviceBrightnessCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuAddressCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuTypeCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayTimeCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDisplayWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDominantHandCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDrinkWaterPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceFunctionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceGoalStepCallback;
import com.crrepa.ble.conn.callback.CRPDeviceHandWashingPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceLanguageCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMaxHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceMetricSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceOtherMessageCallback;
import com.crrepa.ble.conn.callback.CRPDevicePeriodTimeCallback;
import com.crrepa.ble.conn.callback.CRPDevicePhysiologcalPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceQuickViewCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSedentaryReminderPeriodCallback;
import com.crrepa.ble.conn.callback.CRPDeviceSupportWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimeSystemCallback;
import com.crrepa.ble.conn.callback.CRPDeviceTimingMeasureHeartRateCallback;
import com.crrepa.ble.conn.callback.CRPDeviceVersionCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceLayoutCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceListCallback;
import com.crrepa.ble.conn.callback.CRPDeviceWatchFaceStoreCallback;
import com.crrepa.ble.conn.callback.CRPMtuChangeCallback;
import com.crrepa.ble.conn.callback.CRPPillReminderCallback;
import com.crrepa.ble.conn.callback.CRPTapToWakeCallback;
import com.crrepa.ble.conn.callback.CRPTimingTempStateCallback;
import com.crrepa.ble.conn.listener.CRPA2DPConnectStateListener;
import com.crrepa.ble.conn.listener.CRPBatterySavingChangeListener;
import com.crrepa.ble.conn.listener.CRPBleConnectionStateListener;
import com.crrepa.ble.conn.listener.CRPBleECGChangeListener;
import com.crrepa.ble.conn.listener.CRPBleFirmwareUpgradeListener;
import com.crrepa.ble.conn.listener.CRPBloodOxygenChangeListener;
import com.crrepa.ble.conn.listener.CRPBloodPressureChangeListener;
import com.crrepa.ble.conn.listener.CRPCallNumberListener;
import com.crrepa.ble.conn.listener.CRPCameraOperationListener;
import com.crrepa.ble.conn.listener.CRPContactListener;
import com.crrepa.ble.conn.listener.CRPDeviceBatteryListener;
import com.crrepa.ble.conn.listener.CRPDeviceRssiListener;
import com.crrepa.ble.conn.listener.CRPFileTransListener;
import com.crrepa.ble.conn.listener.CRPFindPhoneListener;
import com.crrepa.ble.conn.listener.CRPHeartRateChangeListener;
import com.crrepa.ble.conn.listener.CRPHrvChangeListener;
import com.crrepa.ble.conn.listener.CRPMovementStateListener;
import com.crrepa.ble.conn.listener.CRPPhoneOperationListener;
import com.crrepa.ble.conn.listener.CRPSleepActionChangeListener;
import com.crrepa.ble.conn.listener.CRPSleepChangeListener;
import com.crrepa.ble.conn.listener.CRPSosChangeListener;
import com.crrepa.ble.conn.listener.CRPStepChangeListener;
import com.crrepa.ble.conn.listener.CRPStepsCategoryChangeListener;
import com.crrepa.ble.conn.listener.CRPTempChangeListener;
import com.crrepa.ble.conn.listener.CRPTrainingChangeListener;
import com.crrepa.ble.conn.listener.CRPWeatherChangeListener;
import com.crrepa.ble.conn.type.CRPBloodOxygenTimeType;
import com.crrepa.ble.conn.type.CRPEcgMeasureType;
import com.crrepa.ble.conn.type.CRPHistoryDynamicRateType;
import com.crrepa.ble.conn.type.CRPProtocolVersion;
import com.crrepa.ble.conn.type.CRPTempTimeType;
import com.crrepa.f.a0;
import com.crrepa.f.a1;
import com.crrepa.f.b0;
import com.crrepa.f.b1;
import com.crrepa.f.c0;
import com.crrepa.f.c1;
import com.crrepa.f.d0;
import com.crrepa.f.d1;
import com.crrepa.f.e0;
import com.crrepa.f.e1;
import com.crrepa.f.f0;
import com.crrepa.f.g;
import com.crrepa.f.g0;
import com.crrepa.f.h;
import com.crrepa.f.h0;
import com.crrepa.f.i;
import com.crrepa.f.i0;
import com.crrepa.f.j;
import com.crrepa.f.j0;
import com.crrepa.f.k;
import com.crrepa.f.k0;
import com.crrepa.f.l;
import com.crrepa.f.l0;
import com.crrepa.f.m0;
import com.crrepa.f.n;
import com.crrepa.f.n0;
import com.crrepa.f.o;
import com.crrepa.f.o0;
import com.crrepa.f.p0;
import com.crrepa.f.q;
import com.crrepa.f.q0;
import com.crrepa.f.r;
import com.crrepa.f.r0;
import com.crrepa.f.s;
import com.crrepa.f.s0;
import com.crrepa.f.t;
import com.crrepa.f.t0;
import com.crrepa.f.u;
import com.crrepa.f.u0;
import com.crrepa.f.v;
import com.crrepa.f.v0;
import com.crrepa.f.w;
import com.crrepa.f.w0;
import com.crrepa.f.x;
import com.crrepa.f.x0;
import com.crrepa.f.y;
import com.crrepa.f.y0;
import com.crrepa.f.z;
import com.crrepa.f.z0;
import com.crrepa.g0.d;
import com.crrepa.j.p;
import com.crrepa.m.e;
import com.crrepa.m.f;
import java.io.File;
import java.util.List;
/* loaded from: classes9.dex */
public class a implements CRPBleConnection {

    /* renamed from: a  reason: collision with root package name */
    public d f7722a;
    public f b = f.d();
    public e c;
    public com.crrepa.m.a d;

    /* renamed from: com.crrepa.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0340a implements Runnable {
        public RunnableC0340a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c.d();
        }
    }

    public void a(d dVar) {
        this.f7722a = dVar;
        this.c = dVar.b();
        this.d = this.f7722a.a();
        com.crrepa.p.c.b().a(this.c);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void abortFirmwareUpgrade() {
        com.crrepa.c0.a.b().a();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void abortWatchFace() {
        com.crrepa.f0.a.a().abort();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void abortWatchFaceBackground() {
        com.crrepa.a0.a b = com.crrepa.g0.d.b();
        if (b != null) {
            b.a();
        }
    }

    public final void b(byte b) {
        e(d1.a(81, new byte[]{b}));
    }

    public final void c(int i) {
        e(s.b(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void checkSupportQuickContact(CRPContactConfigCallback cRPContactConfigCallback) {
        this.d.a(cRPContactConfigCallback);
        e(i0.c());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void clearContact() {
        e(i0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void clearPillReminder() {
        e(h0.a(255));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void close() {
        com.crrepa.m.b.b(com.crrepa.l.a.b().a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void closeMusicControl() {
        e(p0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public boolean connect() {
        BluetoothGatt a2 = com.crrepa.l.a.b().a();
        if (a2 == null) {
            return false;
        }
        return a2.connect();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void createBond(byte[] bArr, CRPDeviceBondStateCallback cRPDeviceBondStateCallback) {
        this.d.a(cRPDeviceBondStateCallback);
        e(com.crrepa.f.f.a(bArr));
    }

    public final void d(boolean z) {
        e(o0.a());
        this.d.a(z);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void deleteContact(int i) {
        e(i0.b((byte) i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void deleteContactAvatar(int i) {
        e(i0.a((byte) i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void deletePillReminder(int i) {
        e(h0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableContinueBloodOxygen() {
        j(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableContinueBloodPressure() {
        k(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableContinueTemp() {
        l(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableDrinkWaterReminder() {
        e(r.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableHandWashingReminder() {
        e(x.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableHrvMeasure() {
        f(0);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableTimingMeasureBloodOxygen() {
        h(0);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableTimingMeasureHeartRate() {
        e(z.a(-1));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void disableTimingMeasureTemp() {
        n(false);
    }

    public final void e(byte[] bArr) {
        this.b.a(bArr);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableContinueBloodOxygen() {
        j(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableContinueBloodPressure() {
        k(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableContinueTemp() {
        l(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableDrinkWaterReminder(CRPDrinkWaterPeriodInfo cRPDrinkWaterPeriodInfo) {
        e(r.a(cRPDrinkWaterPeriodInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableHandWashingReminder(CRPHandWashingPeriodInfo cRPHandWashingPeriodInfo) {
        e(x.a(cRPHandWashingPeriodInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableHrvMeasure(int i) {
        f(i);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableHsDfu() {
        e(d1.a(99, new byte[]{1}));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableTimingMeasureBloodOxygen(int i) {
        h(i);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableTimingMeasureHeartRate(int i) {
        if (i <= 0) {
            return;
        }
        e(z.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enableTimingMeasureTemp() {
        n(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void enterCameraView() {
        e(k.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void exitCameraView() {
        e(k.b());
    }

    public final void f(int i) {
        e(a0.b(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void findDevice() {
        e(d1.a(97, null));
    }

    public final void g(boolean z) {
        e(u0.b(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public CRPProtocolVersion getProtocolVersion() {
        return com.crrepa.l.a.b().e();
    }

    public final void h(int i) {
        e(com.crrepa.f.d.a(i));
    }

    public final void i(boolean z) {
        e(com.crrepa.f.d.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public boolean isNewECGMeasurementVersion() {
        return com.crrepa.e.a.a();
    }

    public final void j(boolean z) {
        e(com.crrepa.f.d.b(z));
    }

    public final void k(boolean z) {
        e(com.crrepa.f.e.a(z));
    }

    public final void l(boolean z) {
        e(u0.a(z));
    }

    public final void m(boolean z) {
        e(z.b(z));
    }

    public final void n(boolean z) {
        e(u0.c(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public CRPSleepInfo parseSleep(CRPSleepInfo cRPSleepInfo) {
        return p.a(cRPSleepInfo);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryA2DPState() {
        e(com.crrepa.f.a.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryAllAlarmClock(CRPDeviceAlarmClockCallback cRPDeviceAlarmClockCallback) {
        this.d.a(cRPDeviceAlarmClockCallback);
        e(com.crrepa.f.b.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryBatterySaving() {
        e(com.crrepa.f.c.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryBreathingLight(CRPDeviceBreathingLightCallback cRPDeviceBreathingLightCallback) {
        this.d.a(cRPDeviceBreathingLightCallback);
        e(h.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryBrightness(CRPDeviceBrightnessCallback cRPDeviceBrightnessCallback) {
        this.d.a(cRPDeviceBrightnessCallback);
        e(i.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryBtAddress(CRPBtAddressCallback cRPBtAddressCallback) {
        this.d.a(cRPBtAddressCallback);
        e(j.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryContactCount(CRPContactCountCallback cRPContactCountCallback) {
        this.d.a(cRPContactCountCallback);
        e(i0.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryContinueBloodOxygenState() {
        e(com.crrepa.f.d.c());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryContinueBloodPressureState() {
        e(com.crrepa.f.e.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryContinueTempState() {
        e(u0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDeviceBattery() {
        this.c.c();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDeviceDfuStatus(CRPDeviceDfuStatusCallback cRPDeviceDfuStatusCallback) {
        com.crrepa.d.b.a().a(cRPDeviceDfuStatusCallback);
        e(n.a());
        com.crrepa.g.a.a(new RunnableC0340a(), Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDeviceLanguage(CRPDeviceLanguageCallback cRPDeviceLanguageCallback) {
        this.d.a(cRPDeviceLanguageCallback);
        e(b0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDeviceSupportFunction(CRPDeviceFunctionCallback cRPDeviceFunctionCallback) {
        this.d.a(cRPDeviceFunctionCallback);
        e(u.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDeviceVersion(CRPDeviceVersionCallback cRPDeviceVersionCallback) {
        this.d.a(cRPDeviceVersionCallback);
        e(l.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDfuType(CRPDeviceDfuTypeCallback cRPDeviceDfuTypeCallback) {
        com.crrepa.d.b.a().a(cRPDeviceDfuTypeCallback);
        e(n.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDisplayDeviceFunction(CRPDeviceFunctionCallback cRPDeviceFunctionCallback) {
        this.d.a(cRPDeviceFunctionCallback);
        e(u.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDisplayTime(CRPDeviceDisplayTimeCallback cRPDeviceDisplayTimeCallback) {
        this.d.a(cRPDeviceDisplayTimeCallback);
        e(o.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDisplayWatchFace(CRPDeviceDisplayWatchFaceCallback cRPDeviceDisplayWatchFaceCallback) {
        this.d.a(cRPDeviceDisplayWatchFaceCallback);
        e(a1.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDoNotDistrubTime(CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback) {
        this.d.a(cRPDevicePeriodTimeCallback);
        e(com.crrepa.f.p.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDominantHand(CRPDeviceDominantHandCallback cRPDeviceDominantHandCallback) {
        this.d.a(cRPDeviceDominantHandCallback);
        e(q.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryDrinkWaterReminderPeriod(CRPDeviceDrinkWaterPeriodCallback cRPDeviceDrinkWaterPeriodCallback) {
        this.d.a(cRPDeviceDrinkWaterPeriodCallback);
        e(r.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryFrimwareVersion(CRPDeviceFirmwareVersionCallback cRPDeviceFirmwareVersionCallback) {
        com.crrepa.d.c.a().a(cRPDeviceFirmwareVersionCallback);
        this.c.e();
        e(n.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryGoalStep(CRPDeviceGoalStepCallback cRPDeviceGoalStepCallback) {
        this.d.a(cRPDeviceGoalStepCallback);
        e(w.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHandWashingReminderPeriod(CRPDeviceHandWashingPeriodCallback cRPDeviceHandWashingPeriodCallback) {
        this.d.a(cRPDeviceHandWashingPeriodCallback);
        e(x.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHistoryBloodOxygen() {
        e(com.crrepa.f.d.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHistoryBloodPressure() {
        e(com.crrepa.f.e.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHistoryHeartRate() {
        e(z.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHistoryTraining() {
        e(x0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHrv(int i, int i2) {
        e(a0.a(i, i2));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHrvMeasureCount(int i) {
        e(a0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHrvMeasureInterval() {
        e(a0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryHsDfuAddress(CRPDeviceDfuAddressCallback cRPDeviceDfuAddressCallback) {
        this.d.a(cRPDeviceDfuAddressCallback);
        e(d1.a(99, new byte[]{0}));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryLast24HourBloodOxygen() {
        e(com.crrepa.f.d.c(0));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryLast24HourBloodPressure() {
        e(com.crrepa.f.e.a(0));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryLast24HourTemp() {
        e(u0.a(0));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryLastDynamicRate(CRPHistoryDynamicRateType cRPHistoryDynamicRateType) {
        e(z.b(cRPHistoryDynamicRateType.getValue()));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryLastMeasureECGData() {
        c(2);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryMaxHeartRate(CRPDeviceMaxHeartRateCallback cRPDeviceMaxHeartRateCallback) {
        this.d.a(cRPDeviceMaxHeartRateCallback);
        e(y.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryMetricSystem(CRPDeviceMetricSystemCallback cRPDeviceMetricSystemCallback) {
        this.d.a(cRPDeviceMetricSystemCallback);
        e(d0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryMovementHeartRate() {
        e(z.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryOtherMessageState(CRPDeviceOtherMessageCallback cRPDeviceOtherMessageCallback) {
        this.d.a(cRPDeviceOtherMessageCallback);
        e(f0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryPastHeartRate() {
        e(z.d((byte) 4));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryPhysiologcalPeriod(CRPDevicePhysiologcalPeriodCallback cRPDevicePhysiologcalPeriodCallback) {
        this.d.a(cRPDevicePhysiologcalPeriodCallback);
        e(g0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryPillReminder(CRPPillReminderCallback cRPPillReminderCallback) {
        this.d.a(cRPPillReminderCallback);
        e(h0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryQuickView(CRPDeviceQuickViewCallback cRPDeviceQuickViewCallback) {
        this.d.a(cRPDeviceQuickViewCallback);
        e(j0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryQuickViewTime(CRPDevicePeriodTimeCallback cRPDevicePeriodTimeCallback) {
        this.d.a(cRPDevicePeriodTimeCallback);
        e(k0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void querySedentaryReminder(CRPDeviceSedentaryReminderCallback cRPDeviceSedentaryReminderCallback) {
        this.d.a(cRPDeviceSedentaryReminderCallback);
        e(m0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void querySedentaryReminderPeriod(CRPDeviceSedentaryReminderPeriodCallback cRPDeviceSedentaryReminderPeriodCallback) {
        this.d.a(cRPDeviceSedentaryReminderPeriodCallback);
        e(l0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void querySleepAction(int i) {
        e(n0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryStepsCategory(int i) {
        e(s0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void querySupportWatchFace(CRPDeviceSupportWatchFaceCallback cRPDeviceSupportWatchFaceCallback) {
        this.d.a(cRPDeviceSupportWatchFaceCallback);
        e(e1.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTapToWakeState(CRPTapToWakeCallback cRPTapToWakeCallback) {
        this.d.a(cRPTapToWakeCallback);
        e(t0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTempUnit() {
        e(v0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimeSystem(CRPDeviceTimeSystemCallback cRPDeviceTimeSystemCallback) {
        this.d.a(cRPDeviceTimeSystemCallback);
        e(w0.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimingBloodOxygen(CRPBloodOxygenTimeType cRPBloodOxygenTimeType) {
        e(com.crrepa.f.d.b(cRPBloodOxygenTimeType.getValue()));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimingBloodOxygenMeasureState() {
        e(com.crrepa.f.d.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimingMeasureHeartRate(CRPDeviceTimingMeasureHeartRateCallback cRPDeviceTimingMeasureHeartRateCallback) {
        this.d.a(cRPDeviceTimingMeasureHeartRateCallback);
        e(z.c());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimingMeasureTemp(CRPTempTimeType cRPTempTimeType) {
        e(u0.a(cRPTempTimeType));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTimingMeasureTempState(CRPTimingTempStateCallback cRPTimingTempStateCallback) {
        this.d.a(cRPTimingTempStateCallback);
        e(u0.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTodayHeartRate(int i) {
        e(i == 1 ? z.d((byte) 0) : i == 2 ? z.c((byte) 0) : null);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryTraining(int i) {
        e(x0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryWatchFaceLayout(CRPDeviceWatchFaceLayoutCallback cRPDeviceWatchFaceLayoutCallback) {
        this.d.a(cRPDeviceWatchFaceLayoutCallback);
        e(b1.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryWatchFaceList(CRPDeviceWatchFaceListCallback cRPDeviceWatchFaceListCallback) {
        this.d.a(cRPDeviceWatchFaceListCallback);
        e(a1.b());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryWatchFaceOfID(int i, CRPDeviceWatchFaceCallback cRPDeviceWatchFaceCallback) {
        new com.crrepa.f0.b(cRPDeviceWatchFaceCallback).a(i);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void queryWatchFaceStore(List<Integer> list, String str, int i, int i2, CRPDeviceWatchFaceStoreCallback cRPDeviceWatchFaceStoreCallback) {
        new com.crrepa.f0.c(cRPDeviceWatchFaceStoreCallback).a(list, str, i, i2);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void readDeviceRssi() {
        BluetoothGatt a2 = com.crrepa.l.a.b().a();
        if (a2 != null) {
            a2.readRemoteRssi();
        }
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void reset() {
        e(d1.a(81, new byte[]{2}));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void restart() {
        b((byte) 1);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendA2DPState(CRPA2DPConnectStateListener.A2DPConnectState a2DPConnectState) {
        e(com.crrepa.f.a.a(a2DPConnectState));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendAlarmClock(CRPAlarmClockInfo cRPAlarmClockInfo) {
        if (cRPAlarmClockInfo == null) {
            return;
        }
        e(com.crrepa.f.b.a(cRPAlarmClockInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendBatterySaving(boolean z) {
        e(com.crrepa.f.c.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendBoundVibration() {
        e(g.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendBreathingLight(boolean z) {
        e(h.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendBrightness(int i) {
        e(i.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendCall0ffHook() {
        e(com.crrepa.i.a.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendCallContactName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e(com.crrepa.i.a.a(str));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendContact(CRPContactInfo cRPContactInfo) {
        e(i0.a(cRPContactInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendContactAvatar(int i, Bitmap bitmap, int i2, CRPFileTransListener cRPFileTransListener) {
        com.crrepa.b0.a aVar = (com.crrepa.b0.a) com.crrepa.g0.d.a(d.b.AVATAR);
        aVar.a((byte) i, bitmap);
        aVar.f(i2);
        aVar.a(cRPFileTransListener);
        aVar.j();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendCurrentVolume(int i) {
        e(z0.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDeviceLanguage(byte b) {
        e(b0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDeviceVersion(byte b) {
        e(l.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDislpayDeviceFunction(CRPFunctionInfo cRPFunctionInfo) {
        if (cRPFunctionInfo == null) {
            return;
        }
        e(u.a(cRPFunctionInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDisplayTime(int i) {
        e(o.a((byte) i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDisplayWatchFace(byte b) {
        e(a1.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDoNotDistrubTime(CRPPeriodTimeInfo cRPPeriodTimeInfo) {
        if (cRPPeriodTimeInfo == null) {
            return;
        }
        e(com.crrepa.f.p.a(cRPPeriodTimeInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendDominantHand(byte b) {
        e(q.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendECGHeartRate(int i) {
        e(s.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendFutureWeather(CRPFutureWeatherInfo cRPFutureWeatherInfo) {
        if (cRPFutureWeatherInfo == null) {
            return;
        }
        e(c1.a(cRPFutureWeatherInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendGoalSteps(int i) {
        e(w.a(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendGsensorCalibration() {
        e(v.a());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendLocalCity(String str) {
        e(c0.a(str));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendLyrics(String str) {
        e(p0.a((byte) 1, str));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendMaxVolume(int i) {
        e(z0.b(i));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendMessage(CRPMessageInfo cRPMessageInfo) {
        this.b.b(com.crrepa.i.a.a(cRPMessageInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendMetricSystem(byte b) {
        e(d0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendOtherMessageState(boolean z) {
        e(f0.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendPhysiologcalPeriod(CRPPhysiologcalPeriodInfo cRPPhysiologcalPeriodInfo) {
        e(g0.a(cRPPhysiologcalPeriodInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendPillReminder(CRPPillReminderInfo cRPPillReminderInfo) {
        e(h0.a(cRPPillReminderInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendQuickView(boolean z) {
        e(j0.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendQuickViewTime(CRPPeriodTimeInfo cRPPeriodTimeInfo) {
        if (cRPPeriodTimeInfo == null) {
            return;
        }
        e(k0.a(cRPPeriodTimeInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendSedentaryReminder(boolean z) {
        e(m0.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendSedentaryReminderPeriod(CRPSedentaryReminderPeriodInfo cRPSedentaryReminderPeriodInfo) {
        e(l0.a(cRPSedentaryReminderPeriodInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendSongTitle(String str) {
        e(p0.a((byte) 0, str));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendStepLength(byte b) {
        e(r0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendTapToWakeState(boolean z) {
        e(t0.a(z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendTempUnit(byte b) {
        e(v0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendTimeSystem(byte b) {
        e(w0.a((int) b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendTodayWeather(CRPTodayWeatherInfo cRPTodayWeatherInfo) {
        if (cRPTodayWeatherInfo == null) {
            return;
        }
        e(c1.a(cRPTodayWeatherInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendUserInfo(CRPUserInfo cRPUserInfo) {
        e(y0.a(cRPUserInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendWatchFace(CRPCustomizeWatchFaceInfo cRPCustomizeWatchFaceInfo, CRPFileTransListener cRPFileTransListener, int i) {
        com.crrepa.f0.a a2 = com.crrepa.f0.a.a();
        a2.a(cRPFileTransListener);
        a2.a(i);
        a2.a(cRPCustomizeWatchFaceInfo.getFile());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendWatchFaceBackground(CRPWatchFaceBackgroundInfo cRPWatchFaceBackgroundInfo, CRPFileTransListener cRPFileTransListener) {
        com.crrepa.a0.a a2 = com.crrepa.g0.d.a(com.crrepa.g0.d.a(cRPWatchFaceBackgroundInfo.getType()));
        a2.a(cRPWatchFaceBackgroundInfo.getBitmap(), cRPWatchFaceBackgroundInfo.getThumBitmap());
        a2.b(false);
        a2.f(cRPWatchFaceBackgroundInfo.getTimeout());
        a2.a(cRPFileTransListener);
        a2.j();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void sendWatchFaceLayout(CRPWatchFaceLayoutInfo cRPWatchFaceLayoutInfo) {
        if (cRPWatchFaceLayoutInfo == null) {
            return;
        }
        e(b1.a(cRPWatchFaceLayoutInfo));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setA2DPConnectStateListener(CRPA2DPConnectStateListener cRPA2DPConnectStateListener) {
        this.d.a(cRPA2DPConnectStateListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setBatterySavingListener(CRPBatterySavingChangeListener cRPBatterySavingChangeListener) {
        this.d.a(cRPBatterySavingChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setBloodOxygenChangeListener(CRPBloodOxygenChangeListener cRPBloodOxygenChangeListener) {
        this.d.a(cRPBloodOxygenChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setBloodPressureChangeListener(CRPBloodPressureChangeListener cRPBloodPressureChangeListener) {
        this.d.a(cRPBloodPressureChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setCallNumberListener(CRPCallNumberListener cRPCallNumberListener) {
        this.d.a(cRPCallNumberListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setCameraOperationListener(CRPCameraOperationListener cRPCameraOperationListener) {
        this.d.a(cRPCameraOperationListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setConnectionStateListener(CRPBleConnectionStateListener cRPBleConnectionStateListener) {
        this.f7722a.a(cRPBleConnectionStateListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setContactListener(CRPContactListener cRPContactListener) {
        this.d.a(cRPContactListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setDeviceBatteryListener(CRPDeviceBatteryListener cRPDeviceBatteryListener) {
        com.crrepa.n.a.a().a(cRPDeviceBatteryListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setDeviceRssiListener(CRPDeviceRssiListener cRPDeviceRssiListener) {
        this.f7722a.a(cRPDeviceRssiListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setECGChangeListener(CRPBleECGChangeListener cRPBleECGChangeListener, CRPEcgMeasureType cRPEcgMeasureType) {
        this.d.a(cRPBleECGChangeListener, cRPEcgMeasureType);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setFindPhoneListener(CRPFindPhoneListener cRPFindPhoneListener) {
        this.d.a(cRPFindPhoneListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setHeartRateChangeListener(CRPHeartRateChangeListener cRPHeartRateChangeListener) {
        this.d.a(cRPHeartRateChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setHrvChangeListener(CRPHrvChangeListener cRPHrvChangeListener) {
        this.d.a(cRPHrvChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setMaxHeartRate(byte b, boolean z) {
        e(y.a(b, z));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setMovementState(byte b) {
        e(z.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setMovementStateListener(CRPMovementStateListener cRPMovementStateListener) {
        this.d.a(cRPMovementStateListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setMtu(CRPMtuChangeCallback cRPMtuChangeCallback, @IntRange(from = 23, to = 517) int i) {
        this.f7722a.a(cRPMtuChangeCallback);
        com.crrepa.p.c.b().a(new com.crrepa.p.a(5, com.crrepa.i0.e.a(i)));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setMusicPlayerState(byte b) {
        e(e0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setPhoneOperationListener(CRPPhoneOperationListener cRPPhoneOperationListener) {
        this.d.a(cRPPhoneOperationListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setSleepActionChangeListener(CRPSleepActionChangeListener cRPSleepActionChangeListener) {
        this.d.a(cRPSleepActionChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setSleepChangeListener(CRPSleepChangeListener cRPSleepChangeListener) {
        this.d.a(cRPSleepChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setSosChangeListener(CRPSosChangeListener cRPSosChangeListener) {
        this.d.a(cRPSosChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setStepChangeListener(CRPStepChangeListener cRPStepChangeListener) {
        this.d.a(cRPStepChangeListener);
        this.c.a(cRPStepChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setStepsCategoryListener(CRPStepsCategoryChangeListener cRPStepsCategoryChangeListener) {
        this.d.a(cRPStepsCategoryChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setTempChangeListener(CRPTempChangeListener cRPTempChangeListener) {
        this.d.a(cRPTempChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setTrainingListener(CRPTrainingChangeListener cRPTrainingChangeListener) {
        this.d.a(cRPTrainingChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void setWeatherChangeListener(CRPWeatherChangeListener cRPWeatherChangeListener) {
        this.d.a(cRPWeatherChangeListener);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void shutDown() {
        b((byte) 0);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startECGMeasure() {
        c(1);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startFindPhone() {
        e(t.a(true));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startFirmwareUpgrade(boolean z, File file, String str, CRPBleFirmwareUpgradeListener cRPBleFirmwareUpgradeListener) {
        com.crrepa.c0.a.b().a(z, cRPBleFirmwareUpgradeListener, file, str);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureBloodOxygen() {
        i(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureBloodPressure() {
        e(com.crrepa.f.e.c());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureDynamicRate() {
        e(z.a(true));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureHrv() {
        e(a0.a(true));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureOnceHeartRate() {
        m(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMeasureTemp() {
        g(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void startMovement(byte b) {
        e(z.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopECGMeasure() {
        c(0);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopFindPhone() {
        e(t.a(false));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureBloodOxygen() {
        i(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureBloodPressure() {
        e(com.crrepa.f.e.d());
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureDynamicRtae() {
        e(z.a(false));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureHrv() {
        e(a0.a(false));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureOnceHeartRate() {
        m(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void stopMeasureTemp() {
        g(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void subscribeDeviceBattery() {
        com.crrepa.p.c.b().a(new com.crrepa.p.a(4, new byte[]{32}));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncPastSleep(byte b) {
        e(o0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncPastStep(byte b) {
        e(q0.a(b));
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncRemSleep() {
        d(true);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncSleep() {
        d(false);
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncStep() {
        this.c.g();
    }

    @Override // com.crrepa.ble.conn.CRPBleConnection
    public void syncTime() {
        e(w0.b());
    }
}
