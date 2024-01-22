package com.coveiot.android.bleabstract.models;

import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeviceSupportedFeatures {
    public boolean A;
    @Nullable
    public String[] A0;
    public boolean A1;
    public boolean B;
    public boolean B0;
    public boolean B1;
    public boolean C;
    public boolean C0;
    public boolean C1;
    public boolean D;
    public boolean D0;
    public boolean D1;
    public boolean E;
    public boolean E0;
    public boolean E1;
    public boolean F;
    public boolean F0;
    public boolean F1;
    public boolean G;
    public boolean G0;
    public boolean G1;
    public boolean H0;
    public boolean H1;
    public boolean I;
    public boolean I0;
    public boolean I1;
    public boolean J1;
    public boolean K;
    public boolean K0;
    public boolean K1;
    public boolean L;
    public boolean L0;
    public boolean L1;
    public boolean M;
    public boolean M0;
    public boolean M1;
    public boolean N;
    public boolean N0;
    public boolean N1;
    public boolean O;
    public boolean O0;
    public boolean O1;
    public boolean P;
    public boolean P0;
    public boolean P1;
    public boolean Q;
    public boolean Q0;
    public boolean Q1;
    public boolean R;
    public boolean R0;
    public boolean R1;
    public boolean S;
    public boolean S0;
    public boolean S1;
    public boolean T;
    public boolean T0;
    public boolean T1;
    public boolean U;
    public boolean U0;
    public boolean U1;
    public boolean V;
    public boolean V0;
    public boolean V1;
    public boolean W;
    public boolean W0;
    public boolean W1;
    public boolean X;
    public boolean X0;
    public boolean X1;
    public boolean Y;
    public boolean Y0;
    public boolean Y1;
    public boolean Z0;
    public boolean Z1;

    /* renamed from: a  reason: collision with root package name */
    public int f3425a;
    public boolean a1;
    public boolean a2;
    public boolean b1;
    public boolean b2;
    public boolean c0;
    public boolean c1;
    public boolean c2;
    public boolean d0;
    public boolean d1;
    public boolean d2;
    public boolean e0;
    public boolean e2;
    public boolean f0;
    public boolean f1;
    public boolean f2;
    public boolean g0;
    public boolean g1;
    public boolean g2;
    public int h;
    public boolean h0;
    public boolean h1;
    public boolean h2;
    public int i;
    public boolean i0;
    public boolean i1;
    public boolean i2;
    public int j;
    public boolean j0;
    public boolean j1;
    public boolean j2;
    public int k;
    public boolean k0;
    public int l;
    public boolean l0;
    public boolean l1;
    public int m;
    public boolean m1;
    public boolean n1;
    public int o;
    public boolean o0;
    public boolean o1;
    public int p;
    public boolean p0;
    public boolean p1;
    public int q;
    public boolean q0;
    public boolean q1;
    public boolean r1;
    public boolean s;
    public boolean s1;
    public boolean t;
    public boolean t0;
    public boolean t1;
    public boolean u;
    public boolean u0;
    public boolean u1;
    public boolean v0;
    public boolean v1;
    public boolean w1;
    public boolean x;
    public boolean x1;
    public boolean y;
    public boolean y0;
    public boolean y1;
    public boolean z;
    public boolean z0;
    public boolean z1;
    public int b = 7;
    public int c = 7;
    public int d = 7;
    public int e = 7;
    public int f = 5;
    public int g = 7;
    public int n = 7;
    public int r = 7;
    public boolean v = true;
    public boolean w = true;
    public boolean H = true;
    public boolean J = true;
    public boolean Z = true;
    public boolean a0 = true;
    public boolean b0 = true;
    public boolean m0 = true;
    public boolean n0 = true;
    public boolean r0 = true;
    public boolean s0 = true;
    public int w0 = 12;
    public boolean x0 = true;
    public boolean J0 = true;
    public int e1 = 10;
    public int k1 = 5;
    @NotNull
    public List<HButtonType> k2 = CollectionsKt__CollectionsKt.mutableListOf(HButtonType.H3);
    @NotNull
    public List<SensorType> l2 = CollectionsKt__CollectionsKt.mutableListOf(SensorType.ACCELEROMETER, SensorType.PPG);
    @NotNull
    public DevicePlatformEnum m2 = DevicePlatformEnum.Nordic;

    @Nullable
    public final String[] getAlarmTypes() {
        return this.A0;
    }

    public final boolean getAutoHrSettingsSupported() {
        return this.i0;
    }

    public final boolean getAutoTemperatureSettingsSupported() {
        return this.B0;
    }

    @NotNull
    public final List<HButtonType> getHButtonsSupported() {
        return this.k2;
    }

    public final int getMaxAlarmSupportedOnBand() {
        return this.k1;
    }

    public final int getMaxCharSupportedInNotification() {
        return this.w0;
    }

    public final int getMaxContactsInOneRequest() {
        return this.e1;
    }

    public final int getMaxDaysOfBpDataOnBand() {
        return this.o;
    }

    public final int getMaxDaysOfCalorieDataOnBand() {
        return this.c;
    }

    public final int getMaxDaysOfCyclingDataOnBand() {
        return this.i;
    }

    public final int getMaxDaysOfDistanceDataOnBand() {
        return this.b;
    }

    public final int getMaxDaysOfEcgRrDataOnBand() {
        return this.p;
    }

    public final int getMaxDaysOfHeartRateDataOnBand() {
        return this.l;
    }

    public final int getMaxDaysOfRawAccelerometerDataOnBand() {
        return this.g;
    }

    public final int getMaxDaysOfRawPPGDataOnBand() {
        return this.e;
    }

    public final int getMaxDaysOfRrDataOnBand() {
        return this.m;
    }

    public final int getMaxDaysOfRunDataOnBand() {
        return this.h;
    }

    public final int getMaxDaysOfSedentaryDataOnBand() {
        return this.f;
    }

    public final int getMaxDaysOfSensAISummaryDataOnBand() {
        return this.r;
    }

    public final int getMaxDaysOfSleepDataOnBand() {
        return this.k;
    }

    public final int getMaxDaysOfSpo2DataOnBand() {
        return this.d;
    }

    public final int getMaxDaysOfSportsHistoryDataOnBand() {
        return this.q;
    }

    public final int getMaxDaysOfStepsDataOnBand() {
        return this.f3425a;
    }

    public final int getMaxDaysOfSwimmingDataOnBand() {
        return this.j;
    }

    public final int getMaxDaysOfTemperatureDataOnBand() {
        return this.n;
    }

    @NotNull
    public final DevicePlatformEnum getPlatform() {
        return this.m2;
    }

    @NotNull
    public final List<SensorType> getSensorsSupported() {
        return this.l2;
    }

    public final boolean getShouldKeepDeviceConnectedAlways() {
        return this.Y;
    }

    public final boolean is1kActivitySupported() {
        return this.W0;
    }

    public final boolean is4hButtonFunctionSupported() {
        return this.g2;
    }

    public final boolean isActiveTimeSupported() {
        return this.n1;
    }

    public final boolean isActivityAutoRecognitionSupported() {
        return this.r1;
    }

    public final boolean isActivityShowHideCommandSupported() {
        return this.i1;
    }

    public final boolean isAgpsFileUploadSupported() {
        return this.Q0;
    }

    public final boolean isAmbientSoundLevelSupported() {
        return this.u1;
    }

    public final boolean isAppSocialDistanceFeatureSupported() {
        return this.p0;
    }

    public final boolean isAutoSPO2SettingsSupported() {
        return this.H1;
    }

    public final boolean isAutoStressSettingsSupported() {
        return this.J1;
    }

    public final boolean isBPCalibrationSupported() {
        return this.k0;
    }

    public final boolean isBTCallingSupported() {
        return this.s1;
    }

    public final boolean isBandDisplaySupported() {
        return this.L;
    }

    public final boolean isBandSettingsSupported() {
        return this.n0;
    }

    public final boolean isBandSocialDistanceFeatureSupported() {
        return this.q0;
    }

    public final boolean isBandVolumeControlSupported() {
        return this.B1;
    }

    public final boolean isBatteryLevelRequestSupported() {
        return this.x0;
    }

    public final boolean isBatterySaverConfigSupported() {
        return this.C1;
    }

    public final boolean isBpSupported() {
        return this.A;
    }

    public final boolean isBreathQualityInSleepSupported() {
        return this.y1;
    }

    public final boolean isBusinessCardSupported() {
        return this.W1;
    }

    public final boolean isCalendarSyncSupported() {
        return this.O;
    }

    public final boolean isCallNotificationSupported() {
        return this.M;
    }

    public final boolean isCalorieDistanceGoalGetSupported() {
        return this.G1;
    }

    public final boolean isCalorieGoalSupported() {
        return this.v1;
    }

    public final boolean isCameraEnableSettingsSupported() {
        return this.K1;
    }

    public final boolean isCameraFeatureSupported() {
        return this.S;
    }

    public final boolean isCameraLaunchFromAppIsSupported() {
        return this.V0;
    }

    public final boolean isChangeCalorieGoalFromWatchSupported() {
        return this.a2;
    }

    public final boolean isChangeDistanceGoalFromWatchSupported() {
        return this.Z1;
    }

    public final boolean isChangeStepGoalFromWatchSupported() {
        return this.Y1;
    }

    public final boolean isContactSyncSupported() {
        return this.f1;
    }

    public final boolean isCustomMessageSupported() {
        return this.X0;
    }

    public final boolean isCustomRemindersSupported() {
        return this.z1;
    }

    public final boolean isCyclingSupported() {
        return this.D;
    }

    public final boolean isDeleteActivityDataSupported() {
        return this.R0;
    }

    public final boolean isDeleteGpsActivityDataSupported() {
        return this.U0;
    }

    public final boolean isDeleteGpsDataSupported() {
        return this.S0;
    }

    public final boolean isDeviceSettingsSupportedInOneCommand() {
        return this.t0;
    }

    public final boolean isDiagnosticTestSupported() {
        return this.T1;
    }

    public final boolean isDistanceAndCalorieDataFromBandSupported() {
        return this.R1;
    }

    public final boolean isDistanceGoalSupported() {
        return this.F1;
    }

    public final boolean isDistanceUnitSettingsSupported() {
        return this.e0;
    }

    public final boolean isDndSupported() {
        return this.J0;
    }

    public final boolean isDrinkingReminderSupported() {
        return this.p1;
    }

    public final boolean isDynamicHRMergeSupported() {
        return this.F0;
    }

    public final boolean isEcgSupported() {
        return this.B;
    }

    public final boolean isExerciseMinutesGoalSupported() {
        return this.w1;
    }

    public final boolean isExtendedNotificationsSupported() {
        return this.O1;
    }

    public final boolean isFemaleWellnessSupported() {
        return this.a1;
    }

    public final boolean isFindMyBandSupported() {
        return this.q1;
    }

    public final boolean isFitnessValueCommandSupported() {
        return this.S1;
    }

    public final boolean isGPSDataSupportedInSportMode() {
        return this.y0;
    }

    public final boolean isGenericActivityDataSampleSupported() {
        return this.Q1;
    }

    public final boolean isGenericEventReminderSupported() {
        return this.L1;
    }

    public final boolean isGetLiftWristToViewSettingsSupported() {
        return this.b2;
    }

    public final boolean isGoalSettingSupportedInSingleCommand() {
        return this.P1;
    }

    public final boolean isGpsSupported() {
        return this.G;
    }

    public final boolean isHRVHistorySupported() {
        return this.l1;
    }

    public final boolean isHandPreferenceSettingsSupported() {
        return this.f0;
    }

    public final boolean isHandSettingsSupported() {
        return this.K;
    }

    public final boolean isHeartRateSupported() {
        return this.s;
    }

    public final boolean isKaHaRealtekChip() {
        return this.c1;
    }

    public final boolean isKaHaSifliChip() {
        return this.d2;
    }

    public final boolean isKahaBTCallSupported() {
        return this.b1;
    }

    public final boolean isLiftWristToViewSettingsSupported() {
        return this.g0;
    }

    public final boolean isLiveBPSupported() {
        return this.b0;
    }

    public final boolean isLiveHeartRateSupported() {
        return this.a0;
    }

    public final boolean isLiveStepsSupported() {
        return this.Z;
    }

    public final boolean isManualBpSupported() {
        return this.u0;
    }

    public final boolean isManualHRVMeasurementSupported() {
        return this.D0;
    }

    public final boolean isManualSpo2SupportedOnBand() {
        return this.v0;
    }

    public final boolean isManualStressMeasurementSupported() {
        return this.E0;
    }

    public final boolean isMessageReadSupported() {
        return this.P;
    }

    public final boolean isMultipleAlarmsSupportedAtATime() {
        return this.l0;
    }

    public final boolean isMusicDataSupportInSingleCommand() {
        return this.N1;
    }

    public final boolean isMusicMetaDataChangeFromAppSupported() {
        return this.N0;
    }

    public final boolean isMusicPlaybackStateChangeFromAppSupported() {
        return this.O0;
    }

    public final boolean isMusicVolumeChangeFromAppSupported() {
        return this.P0;
    }

    public final boolean isNewCommandSupportedForFindMyWatch() {
        return this.j2;
    }

    public final boolean isNotificationConfigurationSupported() {
        return this.L0;
    }

    public final boolean isNotificationSupport() {
        return this.w;
    }

    public final boolean isNudgeSupported() {
        return this.o1;
    }

    public final boolean isOnceAlarmSupported() {
        return this.m0;
    }

    public final boolean isOneClickToConnectSupported() {
        return this.t;
    }

    public final boolean isPairingFlowTypeCommandSupported() {
        return this.V1;
    }

    public final boolean isPeriodicSpO2Supported() {
        return this.K0;
    }

    public final boolean isPersonalInfoSupported() {
        return this.U;
    }

    public final boolean isPersonalizedMessageSupported() {
        return this.i2;
    }

    public final boolean isPhoneFinderSupported() {
        return this.R;
    }

    public final boolean isPhoneTypeCommandSupported() {
        return this.W;
    }

    public final boolean isProbeFeatureSupported() {
        return this.j0;
    }

    public final boolean isQRCodeSupported() {
        return this.h2;
    }

    public final boolean isQuickReplySupported() {
        return this.Y0;
    }

    public final boolean isREMSupportedInSleep() {
        return this.G0;
    }

    public final boolean isRawPPGHistaoryDataSupported() {
        return this.H0;
    }

    public final boolean isReadWorldClockFromWatchSupported() {
        return this.c2;
    }

    public final boolean isRepeatDaysSupportedInSedentary() {
        return this.g1;
    }

    public final boolean isRespiratoryRateByPPGSupported() {
        return this.A1;
    }

    public final boolean isRrSupported() {
        return this.C;
    }

    public final boolean isRunSupported() {
        return this.F;
    }

    public final boolean isSampleDataSupportedInSportMode() {
        return this.s0;
    }

    public final boolean isScheduleReminderSupported() {
        return this.C0;
    }

    public final boolean isScheduledDndSupported() {
        return this.I0;
    }

    public final boolean isScheduledLiftWristViewSettingsSupported() {
        return this.I1;
    }

    public final boolean isSedentaryAlertHistorySupported() {
        return this.I;
    }

    public final boolean isSedentaryReminderSupported() {
        return this.H;
    }

    public final boolean isSensAISupported() {
        return this.M1;
    }

    public final boolean isSetVolumePercentageSupported() {
        return this.E1;
    }

    public final boolean isShortcutMenuShowHideCommandSupported() {
        return this.j1;
    }

    public final boolean isSilentModeConfigSupported() {
        return this.D1;
    }

    public final boolean isSleepScoreSupportsFromBand() {
        return this.m1;
    }

    public final boolean isSleepSupported() {
        return this.u;
    }

    public final boolean isSleepTargetSupported() {
        return this.T0;
    }

    public final boolean isSmartAlertsSupported() {
        return this.e2;
    }

    public final boolean isSmsSupported() {
        return this.N;
    }

    public final boolean isSnoozeSettingsSupportedInAlarm() {
        return this.h1;
    }

    public final boolean isSocialNotificationSupported() {
        return this.Q;
    }

    public final boolean isSosSupported() {
        return this.T;
    }

    public final boolean isSportBinFilePushFromAppSupported() {
        return this.U1;
    }

    public final boolean isSportModeSupportedFromApp() {
        return this.r0;
    }

    public final boolean isSportsModeHistorySupported() {
        return this.o0;
    }

    public final boolean isStepGoalSupported() {
        return this.V;
    }

    public final boolean isStepsSupported() {
        return this.z;
    }

    public final boolean isStressHistorySupported() {
        return this.x;
    }

    public final boolean isStressNudgeSupported() {
        return this.y;
    }

    public final boolean isSwimmingSupported() {
        return this.E;
    }

    public final boolean isSyncBandSettingsSupported() {
        return this.z0;
    }

    public final boolean isTemparatureHistorySupported() {
        return this.c0;
    }

    public final boolean isTemperatureUnitSettingsSupported() {
        return this.h0;
    }

    public final boolean isTimeFormartCommandSupported() {
        return this.X;
    }

    public final boolean isTimeFormatSettingsSupported() {
        return this.d0;
    }

    public final boolean isTitleSupportedInNotification() {
        return this.M0;
    }

    public final boolean isTurnByTurnNavigationSupported() {
        return this.f2;
    }

    public final boolean isVibrationAlarmSupported() {
        return this.J;
    }

    public final boolean isWalkingHourGoalSupported() {
        return this.x1;
    }

    public final boolean isWalletCardSupported() {
        return this.X1;
    }

    public final boolean isWatchFaceSupport() {
        return this.v;
    }

    public final boolean isWeatherEnableCommandSupported() {
        return this.d1;
    }

    public final boolean isWeatherSupportedInBand() {
        return this.Z0;
    }

    public final boolean isWorldClockFeatureSupported() {
        return this.t1;
    }

    public final void set1kActivitySupported(boolean z) {
        this.W0 = z;
    }

    public final void set4hButtonFunctionSupported(boolean z) {
        this.g2 = z;
    }

    public final void setActiveTimeSupported(boolean z) {
        this.n1 = z;
    }

    public final void setActivityAutoRecognitionSupported(boolean z) {
        this.r1 = z;
    }

    public final void setActivityShowHideCommandSupported(boolean z) {
        this.i1 = z;
    }

    public final void setAgpsFileUploadSupported(boolean z) {
        this.Q0 = z;
    }

    public final void setAlarmTypes(@Nullable String[] strArr) {
        this.A0 = strArr;
    }

    public final void setAmbientSoundLevelSupported(boolean z) {
        this.u1 = z;
    }

    public final void setAppSocialDistanceFeatureSupported(boolean z) {
        this.p0 = z;
    }

    public final void setAutoHrSettingsSupported(boolean z) {
        this.i0 = z;
    }

    public final void setAutoSPO2SettingsSupported(boolean z) {
        this.H1 = z;
    }

    public final void setAutoStressSettingsSupported(boolean z) {
        this.J1 = z;
    }

    public final void setAutoTemperatureSettingsSupported(boolean z) {
        this.B0 = z;
    }

    public final void setBPCalibrationSupported(boolean z) {
        this.k0 = z;
    }

    public final void setBTCallingSupported(boolean z) {
        this.s1 = z;
    }

    public final void setBandDisplaySupported(boolean z) {
        this.L = z;
    }

    public final void setBandSettingsSupported(boolean z) {
        this.n0 = z;
    }

    public final void setBandSocialDistanceFeatureSupported(boolean z) {
        this.q0 = z;
    }

    public final void setBandVolumeControlSupported(boolean z) {
        this.B1 = z;
    }

    public final void setBatteryLevelRequestSupported(boolean z) {
        this.x0 = z;
    }

    public final void setBatterySaverConfigSupported(boolean z) {
        this.C1 = z;
    }

    public final void setBpSupported(boolean z) {
        this.A = z;
    }

    public final void setBreathQualityInSleepSupported(boolean z) {
        this.y1 = z;
    }

    public final void setBusinessCardSupported(boolean z) {
        this.W1 = z;
    }

    public final void setCalendarSyncSupported(boolean z) {
        this.O = z;
    }

    public final void setCallNotificationSupported(boolean z) {
        this.M = z;
    }

    public final void setCalorieDistanceGoalGetSupported(boolean z) {
        this.G1 = z;
    }

    public final void setCalorieGoalSupported(boolean z) {
        this.v1 = z;
    }

    public final void setCameraEnableSettingsSupported(boolean z) {
        this.K1 = z;
    }

    public final void setCameraFeatureSupported(boolean z) {
        this.S = z;
    }

    public final void setCameraLaunchFromAppIsSupported(boolean z) {
        this.V0 = z;
    }

    public final void setChangeCalorieGoalFromWatchSupported(boolean z) {
        this.a2 = z;
    }

    public final void setChangeDistanceGoalFromWatchSupported(boolean z) {
        this.Z1 = z;
    }

    public final void setChangeStepGoalFromWatchSupported(boolean z) {
        this.Y1 = z;
    }

    public final void setContactSyncSupported(boolean z) {
        this.f1 = z;
    }

    public final void setCustomMessageSupported(boolean z) {
        this.X0 = z;
    }

    public final void setCustomRemindersSupported(boolean z) {
        this.z1 = z;
    }

    public final void setCyclingSupported(boolean z) {
        this.D = z;
    }

    public final void setDeleteActivityDataSupported(boolean z) {
        this.R0 = z;
    }

    public final void setDeleteGpsActivityDataSupported(boolean z) {
        this.U0 = z;
    }

    public final void setDeleteGpsDataSupported(boolean z) {
        this.S0 = z;
    }

    public final void setDeviceSettingsSupportedInOneCommand(boolean z) {
        this.t0 = z;
    }

    public final void setDiagnosticTestSupported(boolean z) {
        this.T1 = z;
    }

    public final void setDistanceAndCalorieDataFromBandSupported(boolean z) {
        this.R1 = z;
    }

    public final void setDistanceGoalSupported(boolean z) {
        this.F1 = z;
    }

    public final void setDistanceUnitSettingsSupported(boolean z) {
        this.e0 = z;
    }

    public final void setDndSupported(boolean z) {
        this.J0 = z;
    }

    public final void setDrinkingReminderSupported(boolean z) {
        this.p1 = z;
    }

    public final void setDynamicHRMergeSupported(boolean z) {
        this.F0 = z;
    }

    public final void setEcgSupported(boolean z) {
        this.B = z;
    }

    public final void setExerciseMinutesGoalSupported(boolean z) {
        this.w1 = z;
    }

    public final void setExtendedNotificationsSupported(boolean z) {
        this.O1 = z;
    }

    public final void setFemaleWellnessSupported(boolean z) {
        this.a1 = z;
    }

    public final void setFindMyBandSupported(boolean z) {
        this.q1 = z;
    }

    public final void setFitnessValueCommandSupported(boolean z) {
        this.S1 = z;
    }

    public final void setGPSDataSupportedInSportMode(boolean z) {
        this.y0 = z;
    }

    public final void setGenericActivityDataSampleSupported(boolean z) {
        this.Q1 = z;
    }

    public final void setGenericEventReminderSupported(boolean z) {
        this.L1 = z;
    }

    public final void setGetLiftWristToViewSettingsSupported(boolean z) {
        this.b2 = z;
    }

    public final void setGoalSettingSupportedInSingleCommand(boolean z) {
        this.P1 = z;
    }

    public final void setGpsSupported(boolean z) {
        this.G = z;
    }

    public final void setHButtonsSupported(@NotNull List<HButtonType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.k2 = list;
    }

    public final void setHRVHistorySupported(boolean z) {
        this.l1 = z;
    }

    public final void setHandPreferenceSettingsSupported(boolean z) {
        this.f0 = z;
    }

    public final void setHandSettingsSupported(boolean z) {
        this.K = z;
    }

    public final void setHeartRateSupported(boolean z) {
        this.s = z;
    }

    public final void setKaHaRealtekChip(boolean z) {
        this.c1 = z;
    }

    public final void setKaHaSifliChip(boolean z) {
        this.d2 = z;
    }

    public final void setKahaBTCallSupported(boolean z) {
        this.b1 = z;
    }

    public final void setLiftWristToViewSettingsSupported(boolean z) {
        this.g0 = z;
    }

    public final void setLiveBPSupported(boolean z) {
        this.b0 = z;
    }

    public final void setLiveHeartRateSupported(boolean z) {
        this.a0 = z;
    }

    public final void setLiveStepsSupported(boolean z) {
        this.Z = z;
    }

    public final void setManualBpSupported(boolean z) {
        this.u0 = z;
    }

    public final void setManualHRVMeasurementSupported(boolean z) {
        this.D0 = z;
    }

    public final void setManualSpo2SupportedOnBand(boolean z) {
        this.v0 = z;
    }

    public final void setManualStressMeasurementSupported(boolean z) {
        this.E0 = z;
    }

    public final void setMaxAlarmSupportedOnBand(int i) {
        this.k1 = i;
    }

    public final void setMaxCharSupportedInNotification(int i) {
        this.w0 = i;
    }

    public final void setMaxContactsInOneRequest(int i) {
        this.e1 = i;
    }

    public final void setMaxDaysOfBpDataOnBand(int i) {
        this.o = i;
    }

    public final void setMaxDaysOfCalorieDataOnBand(int i) {
        this.c = i;
    }

    public final void setMaxDaysOfCyclingDataOnBand(int i) {
        this.i = i;
    }

    public final void setMaxDaysOfDistanceDataOnBand(int i) {
        this.b = i;
    }

    public final void setMaxDaysOfEcgRrDataOnBand(int i) {
        this.p = i;
    }

    public final void setMaxDaysOfHeartRateDataOnBand(int i) {
        this.l = i;
    }

    public final void setMaxDaysOfRawAccelerometerDataOnBand(int i) {
        this.g = i;
    }

    public final void setMaxDaysOfRawPPGDataOnBand(int i) {
        this.e = i;
    }

    public final void setMaxDaysOfRrDataOnBand(int i) {
        this.m = i;
    }

    public final void setMaxDaysOfRunDataOnBand(int i) {
        this.h = i;
    }

    public final void setMaxDaysOfSedentaryDataOnBand(int i) {
        this.f = i;
    }

    public final void setMaxDaysOfSensAISummaryDataOnBand(int i) {
        this.r = i;
    }

    public final void setMaxDaysOfSleepDataOnBand(int i) {
        this.k = i;
    }

    public final void setMaxDaysOfSpo2DataOnBand(int i) {
        this.d = i;
    }

    public final void setMaxDaysOfSportsHistoryDataOnBand(int i) {
        this.q = i;
    }

    public final void setMaxDaysOfStepsDataOnBand(int i) {
        this.f3425a = i;
    }

    public final void setMaxDaysOfSwimmingDataOnBand(int i) {
        this.j = i;
    }

    public final void setMaxDaysOfTemperatureDataOnBand(int i) {
        this.n = i;
    }

    public final void setMessageReadSupported(boolean z) {
        this.P = z;
    }

    public final void setMultipleAlarmsSupportedAtATime(boolean z) {
        this.l0 = z;
    }

    public final void setMusicDataSupportInSingleCommand(boolean z) {
        this.N1 = z;
    }

    public final void setMusicMetaDataChangeFromAppSupported(boolean z) {
        this.N0 = z;
    }

    public final void setMusicPlaybackStateChangeFromAppSupported(boolean z) {
        this.O0 = z;
    }

    public final void setMusicVolumeChangeFromAppSupported(boolean z) {
        this.P0 = z;
    }

    public final void setNewCommandSupportedForFindMyWatch(boolean z) {
        this.j2 = z;
    }

    public final void setNotificationConfigurationSupported(boolean z) {
        this.L0 = z;
    }

    public final void setNotificationSupport(boolean z) {
        this.w = z;
    }

    public final void setNudgeSupported(boolean z) {
        this.o1 = z;
    }

    public final void setOnceAlarmSupported(boolean z) {
        this.m0 = z;
    }

    public final void setOneClickToConnectSupported(boolean z) {
        this.t = z;
    }

    public final void setPairingFlowTypeCommandSupported(boolean z) {
        this.V1 = z;
    }

    public final void setPeriodicSpO2Supported(boolean z) {
        this.K0 = z;
    }

    public final void setPersonalInfoSupported(boolean z) {
        this.U = z;
    }

    public final void setPersonalizedMessageSupported(boolean z) {
        this.i2 = z;
    }

    public final void setPhoneFinderSupported(boolean z) {
        this.R = z;
    }

    public final void setPhoneTypeCommandSupported(boolean z) {
        this.W = z;
    }

    public final void setPlatform(@NotNull DevicePlatformEnum devicePlatformEnum) {
        Intrinsics.checkNotNullParameter(devicePlatformEnum, "<set-?>");
        this.m2 = devicePlatformEnum;
    }

    public final void setProbeFeatureSupported(boolean z) {
        this.j0 = z;
    }

    public final void setQRCodeSupported(boolean z) {
        this.h2 = z;
    }

    public final void setQuickReplySupported(boolean z) {
        this.Y0 = z;
    }

    public final void setREMSupportedInSleep(boolean z) {
        this.G0 = z;
    }

    public final void setRawPPGHistaoryDataSupported(boolean z) {
        this.H0 = z;
    }

    public final void setReadWorldClockFromWatchSupported(boolean z) {
        this.c2 = z;
    }

    public final void setRepeatDaysSupportedInSedentary(boolean z) {
        this.g1 = z;
    }

    public final void setRespiratoryRateByPPGSupported(boolean z) {
        this.A1 = z;
    }

    public final void setRrSupported(boolean z) {
        this.C = z;
    }

    public final void setRunSupported(boolean z) {
        this.F = z;
    }

    public final void setSampleDataSupportedInSportMode(boolean z) {
        this.s0 = z;
    }

    public final void setScheduleReminderSupported(boolean z) {
        this.C0 = z;
    }

    public final void setScheduledDndSupported(boolean z) {
        this.I0 = z;
    }

    public final void setScheduledLiftWristViewSettingsSupported(boolean z) {
        this.I1 = z;
    }

    public final void setSedentaryAlertHistorySupported(boolean z) {
        this.I = z;
    }

    public final void setSedentaryReminderSupported(boolean z) {
        this.H = z;
    }

    public final void setSensAISupported(boolean z) {
        this.M1 = z;
    }

    public final void setSensorsSupported(@NotNull List<SensorType> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.l2 = list;
    }

    public final void setSetVolumePercentageSupported(boolean z) {
        this.E1 = z;
    }

    public final void setShortcutMenuShowHideCommandSupported(boolean z) {
        this.j1 = z;
    }

    public final void setShouldKeepDeviceConnectedAlways(boolean z) {
        this.Y = z;
    }

    public final void setSilentModeConfigSupported(boolean z) {
        this.D1 = z;
    }

    public final void setSleepScoreSupportsFromBand(boolean z) {
        this.m1 = z;
    }

    public final void setSleepSupported(boolean z) {
        this.u = z;
    }

    public final void setSleepTargetSupported(boolean z) {
        this.T0 = z;
    }

    public final void setSmartAlertsSupported(boolean z) {
        this.e2 = z;
    }

    public final void setSmsSupported(boolean z) {
        this.N = z;
    }

    public final void setSnoozeSettingsSupportedInAlarm(boolean z) {
        this.h1 = z;
    }

    public final void setSocialNotificationSupported(boolean z) {
        this.Q = z;
    }

    public final void setSosSupported(boolean z) {
        this.T = z;
    }

    public final void setSportBinFilePushFromAppSupported(boolean z) {
        this.U1 = z;
    }

    public final void setSportModeSupportedFromApp(boolean z) {
        this.r0 = z;
    }

    public final void setSportsModeHistorySupported(boolean z) {
        this.o0 = z;
    }

    public final void setStepGoalSupported(boolean z) {
        this.V = z;
    }

    public final void setStepsSupported(boolean z) {
        this.z = z;
    }

    public final void setStressHistorySupported(boolean z) {
        this.x = z;
    }

    public final void setStressNudgeSupported(boolean z) {
        this.y = z;
    }

    public final void setSwimmingSupported(boolean z) {
        this.E = z;
    }

    public final void setSyncBandSettingsSupported(boolean z) {
        this.z0 = z;
    }

    public final void setTemparatureHistorySupported(boolean z) {
        this.c0 = z;
    }

    public final void setTemperatureUnitSettingsSupported(boolean z) {
        this.h0 = z;
    }

    public final void setTimeFormartCommandSupported(boolean z) {
        this.X = z;
    }

    public final void setTimeFormatSettingsSupported(boolean z) {
        this.d0 = z;
    }

    public final void setTitleSupportedInNotification(boolean z) {
        this.M0 = z;
    }

    public final void setTurnByTurnNavigationSupported(boolean z) {
        this.f2 = z;
    }

    public final void setVibrationAlarmSupported(boolean z) {
        this.J = z;
    }

    public final void setWalkingHourGoalSupported(boolean z) {
        this.x1 = z;
    }

    public final void setWalletCardSupported(boolean z) {
        this.X1 = z;
    }

    public final void setWatchFaceSupport(boolean z) {
        this.v = z;
    }

    public final void setWeatherEnableCommandSupported(boolean z) {
        this.d1 = z;
    }

    public final void setWeatherSupportedInBand(boolean z) {
        this.Z0 = z;
    }

    public final void setWorldClockFeatureSupported(boolean z) {
        this.t1 = z;
    }
}
