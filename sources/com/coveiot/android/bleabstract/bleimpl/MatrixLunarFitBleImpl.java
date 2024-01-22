package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.CloveMatrixBleState;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.WristbandManager;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.squareup.otto.Subscribe;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixLunarFitBleImpl extends MatrixWaveForceBleImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public DeviceSupportedFeatures u;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixLunarFitBleImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.MatrixLunarFitBleImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixLunarFitBleImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3199a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixLunarFitBleImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixLunarFitBleImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixLunarFitBleImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3199a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixLunarFitBleImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initServiceConnection();
        checkAndStartService();
        setMWristbandManager(WristbandApplication.getWristbandManager());
        PreferenceManagerMatrix.getInstance(context).saveBindOrLogin(false);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixWaveForceBleImpl, com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        WristbandConfig wristbandConfig;
        WristbandVersion wristbandVersion;
        WristbandConfig wristbandConfig2;
        WristbandVersion wristbandVersion2;
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        boolean z = false;
        deviceSupportedFeatures.setLiveStepsSupported(false);
        deviceSupportedFeatures.setLiveHeartRateSupported(false);
        deviceSupportedFeatures.setLiveBPSupported(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setMultipleAlarmsSupportedAtATime(true);
        deviceSupportedFeatures.setOnceAlarmSupported(true);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setSampleDataSupportedInSportMode(true);
        deviceSupportedFeatures.setSyncBandSettingsSupported(false);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setDeviceSettingsSupportedInOneCommand(false);
        deviceSupportedFeatures.setAutoTemperatureSettingsSupported(false);
        deviceSupportedFeatures.setScheduleReminderSupported(false);
        deviceSupportedFeatures.setREMSupportedInSleep(false);
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(7);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(true);
        deviceSupportedFeatures.setScheduledDndSupported(false);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setFemaleWellnessSupported(true);
        deviceSupportedFeatures.setCameraLaunchFromAppIsSupported(false);
        deviceSupportedFeatures.setDrinkingReminderSupported(true);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        deviceSupportedFeatures.setGoalSettingSupportedInSingleCommand(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(true);
        deviceSupportedFeatures.setBTCallingSupported(true);
        deviceSupportedFeatures.setDndSupported(true);
        deviceSupportedFeatures.setGenericActivityDataSampleSupported(true);
        deviceSupportedFeatures.setContactSyncSupported(true);
        deviceSupportedFeatures.setMaxContactsInOneRequest(10);
        deviceSupportedFeatures.setCalorieGoalSupported(true);
        deviceSupportedFeatures.setDistanceGoalSupported(true);
        deviceSupportedFeatures.setSportBinFilePushFromAppSupported(true);
        WristbandManager mWristbandManager = getMWristbandManager();
        deviceSupportedFeatures.setBusinessCardSupported((mWristbandManager == null || (wristbandConfig2 = mWristbandManager.getWristbandConfig()) == null || (wristbandVersion2 = wristbandConfig2.getWristbandVersion()) == null) ? false : wristbandVersion2.isExtBusinessCard());
        WristbandManager mWristbandManager2 = getMWristbandManager();
        if (mWristbandManager2 != null && (wristbandConfig = mWristbandManager2.getWristbandConfig()) != null && (wristbandVersion = wristbandConfig.getWristbandVersion()) != null) {
            z = wristbandVersion.isExtCollectionCode();
        }
        deviceSupportedFeatures.setWalletCardSupported(z);
        this.u = deviceSupportedFeatures;
        Intrinsics.checkNotNull(deviceSupportedFeatures);
        return deviceSupportedFeatures;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixWaveForceBleImpl, com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixWaveForceBleImpl
    @Subscribe
    public void onConnectionStateChanged(@Nullable CloveMatrixBleState cloveMatrixBleState) {
        super.onConnectionStateChangedHandler(cloveMatrixBleState);
    }
}
