package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.services.TouchELXService;
import com.coveiot.android.bleabstract.utils.touchUtils.TouchDeviceManager;
import com.coveiot.android.touchsdk.api.TouchELXBaseRes;
import com.coveiot.android.touchsdk.error.TouchELXError;
import com.coveiot.sdk.ble.CloveBleState;
import com.squareup.otto.Subscribe;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class TouchWaveNeoBleApiImpl extends TouchELXBaseBleImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public DeviceSupportedFeatures t;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<TouchWaveNeoBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.TouchWaveNeoBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, TouchWaveNeoBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3293a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, TouchWaveNeoBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public TouchWaveNeoBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new TouchWaveNeoBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3293a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchWaveNeoBleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                TouchDeviceManager mTouchDeviceManager = getMTouchDeviceManager();
                Boolean valueOf = mTouchDeviceManager != null ? Boolean.valueOf(mTouchDeviceManager.isConnected()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    super.getData(request, listener);
                    return;
                }
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        this.t = deviceSupportedFeatures;
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        DeviceSupportedFeatures deviceSupportedFeatures2 = this.t;
        if (deviceSupportedFeatures2 != null) {
            deviceSupportedFeatures2.setMaxDaysOfHeartRateDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures3 = this.t;
        if (deviceSupportedFeatures3 != null) {
            deviceSupportedFeatures3.setMaxDaysOfSleepDataOnBand(7);
        }
        DeviceSupportedFeatures deviceSupportedFeatures4 = this.t;
        if (deviceSupportedFeatures4 != null) {
            deviceSupportedFeatures4.setStepsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures5 = this.t;
        if (deviceSupportedFeatures5 != null) {
            deviceSupportedFeatures5.setSleepSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures6 = this.t;
        if (deviceSupportedFeatures6 != null) {
            deviceSupportedFeatures6.setHeartRateSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures7 = this.t;
        if (deviceSupportedFeatures7 != null) {
            deviceSupportedFeatures7.setStressHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures8 = this.t;
        if (deviceSupportedFeatures8 != null) {
            deviceSupportedFeatures8.setPeriodicSpO2Supported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures9 = this.t;
        if (deviceSupportedFeatures9 != null) {
            deviceSupportedFeatures9.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures10 = this.t;
        if (deviceSupportedFeatures10 != null) {
            deviceSupportedFeatures10.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures11 = this.t;
        if (deviceSupportedFeatures11 != null) {
            deviceSupportedFeatures11.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures12 = this.t;
        if (deviceSupportedFeatures12 != null) {
            deviceSupportedFeatures12.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures13 = this.t;
        if (deviceSupportedFeatures13 != null) {
            deviceSupportedFeatures13.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures14 = this.t;
        if (deviceSupportedFeatures14 != null) {
            deviceSupportedFeatures14.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures15 = this.t;
        if (deviceSupportedFeatures15 != null) {
            deviceSupportedFeatures15.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures16 = this.t;
        if (deviceSupportedFeatures16 != null) {
            deviceSupportedFeatures16.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures17 = this.t;
        if (deviceSupportedFeatures17 != null) {
            deviceSupportedFeatures17.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures18 = this.t;
        if (deviceSupportedFeatures18 != null) {
            deviceSupportedFeatures18.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures19 = this.t;
        if (deviceSupportedFeatures19 != null) {
            deviceSupportedFeatures19.setMultipleAlarmsSupportedAtATime(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures20 = this.t;
        if (deviceSupportedFeatures20 != null) {
            deviceSupportedFeatures20.setREMSupportedInSleep(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures21 = this.t;
        if (deviceSupportedFeatures21 != null) {
            deviceSupportedFeatures21.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures22 = this.t;
        if (deviceSupportedFeatures22 != null) {
            deviceSupportedFeatures22.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures23 = this.t;
        if (deviceSupportedFeatures23 != null) {
            deviceSupportedFeatures23.setCalorieGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures24 = this.t;
        if (deviceSupportedFeatures24 != null) {
            deviceSupportedFeatures24.setExerciseMinutesGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures25 = this.t;
        if (deviceSupportedFeatures25 != null) {
            deviceSupportedFeatures25.setWalkingHourGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures26 = this.t;
        if (deviceSupportedFeatures26 != null) {
            deviceSupportedFeatures26.setDistanceGoalSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures27 = this.t;
        if (deviceSupportedFeatures27 != null) {
            deviceSupportedFeatures27.setSyncBandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures28 = this.t;
        if (deviceSupportedFeatures28 != null) {
            deviceSupportedFeatures28.setMaxAlarmSupportedOnBand(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures29 = this.t;
        if (deviceSupportedFeatures29 != null) {
            deviceSupportedFeatures29.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures30 = this.t;
        if (deviceSupportedFeatures30 != null) {
            deviceSupportedFeatures30.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures31 = this.t;
        if (deviceSupportedFeatures31 != null) {
            deviceSupportedFeatures31.setSleepScoreSupportsFromBand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures32 = this.t;
        if (deviceSupportedFeatures32 != null) {
            deviceSupportedFeatures32.setActiveTimeSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures33 = this.t;
        if (deviceSupportedFeatures33 != null) {
            deviceSupportedFeatures33.setDrinkingReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures34 = this.t;
        if (deviceSupportedFeatures34 != null) {
            deviceSupportedFeatures34.setBatteryLevelRequestSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures35 = this.t;
        if (deviceSupportedFeatures35 != null) {
            deviceSupportedFeatures35.setBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures36 = this.t;
        if (deviceSupportedFeatures36 != null) {
            deviceSupportedFeatures36.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures37 = this.t;
        if (deviceSupportedFeatures37 != null) {
            deviceSupportedFeatures37.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures38 = this.t;
        if (deviceSupportedFeatures38 != null) {
            deviceSupportedFeatures38.setScheduledDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures39 = this.t;
        if (deviceSupportedFeatures39 != null) {
            deviceSupportedFeatures39.setDndSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures40 = this.t;
        if (deviceSupportedFeatures40 != null) {
            deviceSupportedFeatures40.setRepeatDaysSupportedInSedentary(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures41 = this.t;
        if (deviceSupportedFeatures41 != null) {
            deviceSupportedFeatures41.setBandVolumeControlSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures42 = this.t;
        if (deviceSupportedFeatures42 != null) {
            deviceSupportedFeatures42.setCameraFeatureSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures43 = this.t;
        if (deviceSupportedFeatures43 != null) {
            deviceSupportedFeatures43.setMaxContactsInOneRequest(10);
        }
        DeviceSupportedFeatures deviceSupportedFeatures44 = this.t;
        if (deviceSupportedFeatures44 != null) {
            deviceSupportedFeatures44.setContactSyncSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures45 = this.t;
        if (deviceSupportedFeatures45 != null) {
            deviceSupportedFeatures45.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures46 = this.t;
        if (deviceSupportedFeatures46 != null) {
            deviceSupportedFeatures46.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures47 = this.t;
        if (deviceSupportedFeatures47 != null) {
            deviceSupportedFeatures47.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures48 = this.t;
        if (deviceSupportedFeatures48 != null) {
            deviceSupportedFeatures48.setWeatherEnableCommandSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures49 = this.t;
        if (deviceSupportedFeatures49 != null) {
            deviceSupportedFeatures49.setWeatherSupportedInBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures50 = this.t;
        if (deviceSupportedFeatures50 != null) {
            deviceSupportedFeatures50.setActivityAutoRecognitionSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeatures51 = this.t;
        if (deviceSupportedFeatures51 != null) {
            deviceSupportedFeatures51.setCameraEnableSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures52 = this.t;
        if (deviceSupportedFeatures52 != null) {
            TouchDeviceManager mTouchDeviceManager = getMTouchDeviceManager();
            Boolean valueOf = mTouchDeviceManager != null ? Boolean.valueOf(mTouchDeviceManager.isAutoStressSupported()) : null;
            Intrinsics.checkNotNull(valueOf);
            deviceSupportedFeatures52.setAutoStressSettingsSupported(valueOf.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures53 = this.t;
        if (deviceSupportedFeatures53 != null) {
            TouchDeviceManager mTouchDeviceManager2 = getMTouchDeviceManager();
            Boolean valueOf2 = mTouchDeviceManager2 != null ? Boolean.valueOf(mTouchDeviceManager2.isAutoSPO2Supported()) : null;
            Intrinsics.checkNotNull(valueOf2);
            deviceSupportedFeatures53.setAutoSPO2SettingsSupported(valueOf2.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures54 = this.t;
        if (deviceSupportedFeatures54 != null) {
            TouchDeviceManager mTouchDeviceManager3 = getMTouchDeviceManager();
            Boolean valueOf3 = mTouchDeviceManager3 != null ? Boolean.valueOf(mTouchDeviceManager3.isQuickReplySupported()) : null;
            Intrinsics.checkNotNull(valueOf3);
            deviceSupportedFeatures54.setQuickReplySupported(valueOf3.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures55 = this.t;
        if (deviceSupportedFeatures55 != null) {
            TouchDeviceManager mTouchDeviceManager4 = getMTouchDeviceManager();
            Boolean valueOf4 = mTouchDeviceManager4 != null ? Boolean.valueOf(mTouchDeviceManager4.isFindDeviceSupported()) : null;
            Intrinsics.checkNotNull(valueOf4);
            deviceSupportedFeatures55.setFindMyBandSupported(valueOf4.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures56 = this.t;
        if (deviceSupportedFeatures56 != null) {
            TouchDeviceManager mTouchDeviceManager5 = getMTouchDeviceManager();
            Boolean valueOf5 = mTouchDeviceManager5 != null ? Boolean.valueOf(mTouchDeviceManager5.isSportModeSupported()) : null;
            Intrinsics.checkNotNull(valueOf5);
            deviceSupportedFeatures56.setActivityShowHideCommandSupported(valueOf5.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures57 = this.t;
        if (deviceSupportedFeatures57 != null) {
            TouchDeviceManager mTouchDeviceManager6 = getMTouchDeviceManager();
            Boolean valueOf6 = mTouchDeviceManager6 != null ? Boolean.valueOf(mTouchDeviceManager6.isQuickCardsSupported()) : null;
            Intrinsics.checkNotNull(valueOf6);
            deviceSupportedFeatures57.setShortcutMenuShowHideCommandSupported(valueOf6.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures58 = this.t;
        if (deviceSupportedFeatures58 != null) {
            TouchDeviceManager mTouchDeviceManager7 = getMTouchDeviceManager();
            Boolean valueOf7 = mTouchDeviceManager7 != null ? Boolean.valueOf(mTouchDeviceManager7.isFemaleWellnessSupported()) : null;
            Intrinsics.checkNotNull(valueOf7);
            deviceSupportedFeatures58.setFemaleWellnessSupported(valueOf7.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures59 = this.t;
        if (deviceSupportedFeatures59 != null) {
            TouchDeviceManager mTouchDeviceManager8 = getMTouchDeviceManager();
            Boolean valueOf8 = mTouchDeviceManager8 != null ? Boolean.valueOf(mTouchDeviceManager8.isBTCallingSupported()) : null;
            Intrinsics.checkNotNull(valueOf8);
            deviceSupportedFeatures59.setBTCallingSupported(valueOf8.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures60 = this.t;
        if (deviceSupportedFeatures60 != null) {
            TouchDeviceManager mTouchDeviceManager9 = getMTouchDeviceManager();
            Boolean valueOf9 = mTouchDeviceManager9 != null ? Boolean.valueOf(mTouchDeviceManager9.isEventReminderSupported()) : null;
            Intrinsics.checkNotNull(valueOf9);
            deviceSupportedFeatures60.setGenericEventReminderSupported(valueOf9.booleanValue());
        }
        DeviceSupportedFeatures deviceSupportedFeatures61 = this.t;
        if (deviceSupportedFeatures61 != null) {
            deviceSupportedFeatures61.setMusicDataSupportInSingleCommand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.t;
        if (deviceSupportedFeatures62 != null) {
            deviceSupportedFeatures62.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures63 = this.t;
        if (deviceSupportedFeatures63 != null) {
            deviceSupportedFeatures63.setGenericActivityDataSampleSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures64 = this.t;
        if (deviceSupportedFeatures64 != null) {
            deviceSupportedFeatures64.setManualSpo2SupportedOnBand(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures65 = this.t;
        if (deviceSupportedFeatures65 != null) {
            deviceSupportedFeatures65.setScheduledLiftWristViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures66 = this.t;
        if (deviceSupportedFeatures66 != null) {
            deviceSupportedFeatures66.setGetLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeatures67 = this.t;
        Intrinsics.checkNotNull(deviceSupportedFeatures67);
        return deviceSupportedFeatures67;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveBleState cloveBleState) {
        super.onConnectionStateChangedHandler(cloveBleState);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.touchsdk.TouchELXResponseListener
    public void onFailure(@NotNull TouchELXError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onFailure(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.touchsdk.TouchELXResponseListener
    public void onResponse(@NotNull TouchELXBaseRes response) {
        Intrinsics.checkNotNullParameter(response, "response");
        super.onResponse(response);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.TouchELXBaseBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            TouchELXService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
                TouchDeviceManager mTouchDeviceManager = getMTouchDeviceManager();
                Boolean valueOf = mTouchDeviceManager != null ? Boolean.valueOf(mTouchDeviceManager.isConnected()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    super.setUserSettings(request, listener);
                    return;
                }
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }
}
