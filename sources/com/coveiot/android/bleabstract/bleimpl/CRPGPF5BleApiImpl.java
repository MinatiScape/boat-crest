package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.models.CloveCRPBleState;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.crpsdk.events.CRPResponseEvent;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CRPGPF5BleApiImpl extends CRPBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<CRPGPF5BleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.CRPGPF5BleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, CRPGPF5BleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2928a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, CRPGPF5BleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public CRPGPF5BleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CRPGPF5BleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2928a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CRPGPF5BleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        super.registerEvenBus();
        checkAndStartService();
    }

    public final void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(getTAG(), "checkAndStartService-> service is not running ++ ");
            startBleService();
        } else if (getBleService() != null || isBindToServiceInProgress()) {
        } else {
            bindToBleService();
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CRPBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        setDeviceSupportedFeat(new DeviceSupportedFeatures());
        DeviceSupportedFeatures deviceSupportedFeat = getDeviceSupportedFeat();
        if (deviceSupportedFeat != null) {
            deviceSupportedFeat.setStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat2 = getDeviceSupportedFeat();
        if (deviceSupportedFeat2 != null) {
            deviceSupportedFeat2.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat3 = getDeviceSupportedFeat();
        if (deviceSupportedFeat3 != null) {
            deviceSupportedFeat3.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat4 = getDeviceSupportedFeat();
        if (deviceSupportedFeat4 != null) {
            deviceSupportedFeat4.setTemparatureHistorySupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat5 = getDeviceSupportedFeat();
        if (deviceSupportedFeat5 != null) {
            deviceSupportedFeat5.setManualBpSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat6 = getDeviceSupportedFeat();
        if (deviceSupportedFeat6 != null) {
            deviceSupportedFeat6.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat7 = getDeviceSupportedFeat();
        if (deviceSupportedFeat7 != null) {
            deviceSupportedFeat7.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat8 = getDeviceSupportedFeat();
        if (deviceSupportedFeat8 != null) {
            deviceSupportedFeat8.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat9 = getDeviceSupportedFeat();
        if (deviceSupportedFeat9 != null) {
            deviceSupportedFeat9.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat10 = getDeviceSupportedFeat();
        if (deviceSupportedFeat10 != null) {
            deviceSupportedFeat10.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat11 = getDeviceSupportedFeat();
        if (deviceSupportedFeat11 != null) {
            deviceSupportedFeat11.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat12 = getDeviceSupportedFeat();
        if (deviceSupportedFeat12 != null) {
            deviceSupportedFeat12.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat13 = getDeviceSupportedFeat();
        if (deviceSupportedFeat13 != null) {
            deviceSupportedFeat13.setPhoneFinderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat14 = getDeviceSupportedFeat();
        if (deviceSupportedFeat14 != null) {
            deviceSupportedFeat14.setLiveStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat15 = getDeviceSupportedFeat();
        if (deviceSupportedFeat15 != null) {
            deviceSupportedFeat15.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat16 = getDeviceSupportedFeat();
        if (deviceSupportedFeat16 != null) {
            deviceSupportedFeat16.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat17 = getDeviceSupportedFeat();
        if (deviceSupportedFeat17 != null) {
            deviceSupportedFeat17.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat18 = getDeviceSupportedFeat();
        if (deviceSupportedFeat18 != null) {
            deviceSupportedFeat18.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat19 = getDeviceSupportedFeat();
        if (deviceSupportedFeat19 != null) {
            deviceSupportedFeat19.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat20 = getDeviceSupportedFeat();
        if (deviceSupportedFeat20 != null) {
            deviceSupportedFeat20.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat21 = getDeviceSupportedFeat();
        if (deviceSupportedFeat21 != null) {
            deviceSupportedFeat21.setTemperatureUnitSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat22 = getDeviceSupportedFeat();
        if (deviceSupportedFeat22 != null) {
            deviceSupportedFeat22.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat23 = getDeviceSupportedFeat();
        if (deviceSupportedFeat23 != null) {
            deviceSupportedFeat23.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat24 = getDeviceSupportedFeat();
        if (deviceSupportedFeat24 != null) {
            deviceSupportedFeat24.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat25 = getDeviceSupportedFeat();
        if (deviceSupportedFeat25 != null) {
            deviceSupportedFeat25.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat26 = getDeviceSupportedFeat();
        if (deviceSupportedFeat26 != null) {
            deviceSupportedFeat26.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat27 = getDeviceSupportedFeat();
        if (deviceSupportedFeat27 != null) {
            deviceSupportedFeat27.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat28 = getDeviceSupportedFeat();
        if (deviceSupportedFeat28 != null) {
            deviceSupportedFeat28.setSyncBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat29 = getDeviceSupportedFeat();
        if (deviceSupportedFeat29 != null) {
            deviceSupportedFeat29.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat30 = getDeviceSupportedFeat();
        if (deviceSupportedFeat30 != null) {
            deviceSupportedFeat30.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat31 = getDeviceSupportedFeat();
        if (deviceSupportedFeat31 != null) {
            deviceSupportedFeat31.setAppSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat32 = getDeviceSupportedFeat();
        if (deviceSupportedFeat32 != null) {
            deviceSupportedFeat32.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat33 = getDeviceSupportedFeat();
        if (deviceSupportedFeat33 != null) {
            deviceSupportedFeat33.setMaxCharSupportedInNotification(60);
        }
        DeviceSupportedFeatures deviceSupportedFeat34 = getDeviceSupportedFeat();
        if (deviceSupportedFeat34 != null) {
            deviceSupportedFeat34.setAutoTemperatureSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat35 = getDeviceSupportedFeat();
        if (deviceSupportedFeat35 != null) {
            deviceSupportedFeat35.setScheduleReminderSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat36 = getDeviceSupportedFeat();
        if (deviceSupportedFeat36 != null) {
            deviceSupportedFeat36.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat37 = getDeviceSupportedFeat();
        if (deviceSupportedFeat37 != null) {
            deviceSupportedFeat37.setMaxDaysOfStepsDataOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeat38 = getDeviceSupportedFeat();
        if (deviceSupportedFeat38 != null) {
            deviceSupportedFeat38.setMaxDaysOfHeartRateDataOnBand(2);
        }
        DeviceSupportedFeatures deviceSupportedFeat39 = getDeviceSupportedFeat();
        if (deviceSupportedFeat39 != null) {
            deviceSupportedFeat39.setMaxDaysOfSleepDataOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeat40 = getDeviceSupportedFeat();
        if (deviceSupportedFeat40 != null) {
            deviceSupportedFeat40.setScheduledDndSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat41 = getDeviceSupportedFeat();
        if (deviceSupportedFeat41 != null) {
            deviceSupportedFeat41.setManualSpo2SupportedOnBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat42 = getDeviceSupportedFeat();
        if (deviceSupportedFeat42 != null) {
            deviceSupportedFeat42.setMaxDaysOfSpo2DataOnBand(2);
        }
        DeviceSupportedFeatures deviceSupportedFeat43 = getDeviceSupportedFeat();
        if (deviceSupportedFeat43 != null) {
            deviceSupportedFeat43.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat44 = getDeviceSupportedFeat();
        if (deviceSupportedFeat44 != null) {
            deviceSupportedFeat44.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat45 = getDeviceSupportedFeat();
        if (deviceSupportedFeat45 != null) {
            deviceSupportedFeat45.setMusicVolumeChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat46 = getDeviceSupportedFeat();
        if (deviceSupportedFeat46 != null) {
            deviceSupportedFeat46.setMaxAlarmSupportedOnBand(3);
        }
        DeviceSupportedFeatures deviceSupportedFeat47 = getDeviceSupportedFeat();
        if (deviceSupportedFeat47 != null) {
            deviceSupportedFeat47.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat48 = getDeviceSupportedFeat();
        if (deviceSupportedFeat48 != null) {
            deviceSupportedFeat48.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat49 = getDeviceSupportedFeat();
        if (deviceSupportedFeat49 != null) {
            deviceSupportedFeat49.setScheduledLiftWristViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat50 = getDeviceSupportedFeat();
        Intrinsics.checkNotNull(deviceSupportedFeat50);
        return deviceSupportedFeat50;
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveCRPBleState cloveCRPBleState) {
        super.onConnectionStateChangedHandler(cloveCRPBleState);
    }

    @Subscribe
    public final void onResponseEventReceived(@NotNull CRPResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.processResponseEventReceived(responseEvent);
    }
}
