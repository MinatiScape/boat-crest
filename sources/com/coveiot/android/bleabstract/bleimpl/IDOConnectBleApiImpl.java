package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.idoSdk.api.IDOBaseRes;
import com.coveiot.android.idoSdk.error.IDOError;
import com.ido.ble.BLEManager;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class IDOConnectBleApiImpl extends IDOBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public DeviceSupportedFeatures I;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<IDOConnectBleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.IDOConnectBleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, IDOConnectBleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f2998a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, IDOConnectBleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public IDOConnectBleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new IDOConnectBleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f2998a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IDOConnectBleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (BLEManager.isConnected()) {
            super.getData(request, listener);
            return;
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        if (this.I == null) {
            DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
            this.I = deviceSupportedFeatures;
            deviceSupportedFeatures.setSleepSupported(true);
            DeviceSupportedFeatures deviceSupportedFeatures2 = this.I;
            if (deviceSupportedFeatures2 != null) {
                deviceSupportedFeatures2.setStepsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures3 = this.I;
            if (deviceSupportedFeatures3 != null) {
                deviceSupportedFeatures3.setHeartRateSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures4 = this.I;
            if (deviceSupportedFeatures4 != null) {
                deviceSupportedFeatures4.setSedentaryReminderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures5 = this.I;
            if (deviceSupportedFeatures5 != null) {
                deviceSupportedFeatures5.setPersonalInfoSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures6 = this.I;
            if (deviceSupportedFeatures6 != null) {
                deviceSupportedFeatures6.setStepGoalSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures7 = this.I;
            if (deviceSupportedFeatures7 != null) {
                deviceSupportedFeatures7.setCallNotificationSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures8 = this.I;
            if (deviceSupportedFeatures8 != null) {
                deviceSupportedFeatures8.setSmsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures9 = this.I;
            if (deviceSupportedFeatures9 != null) {
                deviceSupportedFeatures9.setMessageReadSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures10 = this.I;
            if (deviceSupportedFeatures10 != null) {
                deviceSupportedFeatures10.setSocialNotificationSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures11 = this.I;
            if (deviceSupportedFeatures11 != null) {
                deviceSupportedFeatures11.setHandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures12 = this.I;
            if (deviceSupportedFeatures12 != null) {
                deviceSupportedFeatures12.setPersonalInfoSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures13 = this.I;
            if (deviceSupportedFeatures13 != null) {
                deviceSupportedFeatures13.setLiveHeartRateSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures14 = this.I;
            if (deviceSupportedFeatures14 != null) {
                deviceSupportedFeatures14.setLiveStepsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures15 = this.I;
            if (deviceSupportedFeatures15 != null) {
                deviceSupportedFeatures15.setLiveBPSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures16 = this.I;
            if (deviceSupportedFeatures16 != null) {
                deviceSupportedFeatures16.setSportModeSupportedFromApp(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures17 = this.I;
            if (deviceSupportedFeatures17 != null) {
                deviceSupportedFeatures17.setBatteryLevelRequestSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures18 = this.I;
            if (deviceSupportedFeatures18 != null) {
                deviceSupportedFeatures18.setBandSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures19 = this.I;
            if (deviceSupportedFeatures19 != null) {
                deviceSupportedFeatures19.setDistanceUnitSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures20 = this.I;
            if (deviceSupportedFeatures20 != null) {
                deviceSupportedFeatures20.setLiftWristToViewSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures21 = this.I;
            if (deviceSupportedFeatures21 != null) {
                deviceSupportedFeatures21.setVibrationAlarmSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures22 = this.I;
            if (deviceSupportedFeatures22 != null) {
                deviceSupportedFeatures22.setREMSupportedInSleep(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures23 = this.I;
            if (deviceSupportedFeatures23 != null) {
                deviceSupportedFeatures23.setPhoneFinderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures24 = this.I;
            if (deviceSupportedFeatures24 != null) {
                deviceSupportedFeatures24.setCameraFeatureSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures25 = this.I;
            if (deviceSupportedFeatures25 != null) {
                deviceSupportedFeatures25.setManualSpo2SupportedOnBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures26 = this.I;
            if (deviceSupportedFeatures26 != null) {
                deviceSupportedFeatures26.setTimeFormatSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures27 = this.I;
            if (deviceSupportedFeatures27 != null) {
                deviceSupportedFeatures27.setAutoHrSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures28 = this.I;
            if (deviceSupportedFeatures28 != null) {
                deviceSupportedFeatures28.setMultipleAlarmsSupportedAtATime(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures29 = this.I;
            if (deviceSupportedFeatures29 != null) {
                deviceSupportedFeatures29.setFemaleWellnessSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures30 = this.I;
            if (deviceSupportedFeatures30 != null) {
                deviceSupportedFeatures30.setScheduledDndSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures31 = this.I;
            if (deviceSupportedFeatures31 != null) {
                deviceSupportedFeatures31.setWeatherSupportedInBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures32 = this.I;
            if (deviceSupportedFeatures32 != null) {
                deviceSupportedFeatures32.setMaxDaysOfStepsDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures33 = this.I;
            if (deviceSupportedFeatures33 != null) {
                deviceSupportedFeatures33.setMaxDaysOfSleepDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures34 = this.I;
            if (deviceSupportedFeatures34 != null) {
                deviceSupportedFeatures34.setMaxDaysOfHeartRateDataOnBand(7);
            }
            DeviceSupportedFeatures deviceSupportedFeatures35 = this.I;
            if (deviceSupportedFeatures35 != null) {
                deviceSupportedFeatures35.setRepeatDaysSupportedInSedentary(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures36 = this.I;
            if (deviceSupportedFeatures36 != null) {
                deviceSupportedFeatures36.setSnoozeSettingsSupportedInAlarm(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures37 = this.I;
            if (deviceSupportedFeatures37 != null) {
                deviceSupportedFeatures37.setActivityShowHideCommandSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures38 = this.I;
            if (deviceSupportedFeatures38 != null) {
                deviceSupportedFeatures38.setShortcutMenuShowHideCommandSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures39 = this.I;
            if (deviceSupportedFeatures39 != null) {
                deviceSupportedFeatures39.setStressHistorySupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures40 = this.I;
            if (deviceSupportedFeatures40 != null) {
                deviceSupportedFeatures40.setSportsModeHistorySupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures41 = this.I;
            if (deviceSupportedFeatures41 != null) {
                deviceSupportedFeatures41.setSampleDataSupportedInSportMode(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures42 = this.I;
            if (deviceSupportedFeatures42 != null) {
                deviceSupportedFeatures42.setSyncBandSettingsSupported(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures43 = this.I;
            if (deviceSupportedFeatures43 != null) {
                deviceSupportedFeatures43.setSportModeSupportedFromApp(false);
            }
            DeviceSupportedFeatures deviceSupportedFeatures44 = this.I;
            if (deviceSupportedFeatures44 != null) {
                deviceSupportedFeatures44.setMaxAlarmSupportedOnBand(10);
            }
            DeviceSupportedFeatures deviceSupportedFeatures45 = this.I;
            if (deviceSupportedFeatures45 != null) {
                deviceSupportedFeatures45.setMusicMetaDataChangeFromAppSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures46 = this.I;
            if (deviceSupportedFeatures46 != null) {
                deviceSupportedFeatures46.setSleepScoreSupportsFromBand(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures47 = this.I;
            if (deviceSupportedFeatures47 != null) {
                deviceSupportedFeatures47.setActiveTimeSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures48 = this.I;
            if (deviceSupportedFeatures48 != null) {
                deviceSupportedFeatures48.setMaxContactsInOneRequest(20);
            }
            DeviceSupportedFeatures deviceSupportedFeatures49 = this.I;
            if (deviceSupportedFeatures49 != null) {
                deviceSupportedFeatures49.setContactSyncSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures50 = this.I;
            if (deviceSupportedFeatures50 != null) {
                deviceSupportedFeatures50.setBTCallingSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures51 = this.I;
            if (deviceSupportedFeatures51 != null) {
                deviceSupportedFeatures51.setWorldClockFeatureSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures52 = this.I;
            if (deviceSupportedFeatures52 != null) {
                deviceSupportedFeatures52.setAmbientSoundLevelSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures53 = this.I;
            if (deviceSupportedFeatures53 != null) {
                deviceSupportedFeatures53.setCalorieGoalSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures54 = this.I;
            if (deviceSupportedFeatures54 != null) {
                deviceSupportedFeatures54.setExerciseMinutesGoalSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures55 = this.I;
            if (deviceSupportedFeatures55 != null) {
                deviceSupportedFeatures55.setWalkingHourGoalSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures56 = this.I;
            if (deviceSupportedFeatures56 != null) {
                deviceSupportedFeatures56.setBreathQualityInSleepSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures57 = this.I;
            if (deviceSupportedFeatures57 != null) {
                deviceSupportedFeatures57.setDrinkingReminderSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures58 = this.I;
            if (deviceSupportedFeatures58 != null) {
                deviceSupportedFeatures58.setActivityAutoRecognitionSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures59 = this.I;
            if (deviceSupportedFeatures59 != null) {
                deviceSupportedFeatures59.setBandVolumeControlSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures60 = this.I;
            if (deviceSupportedFeatures60 != null) {
                deviceSupportedFeatures60.setAutoStressSettingsSupported(true);
            }
            DeviceSupportedFeatures deviceSupportedFeatures61 = this.I;
            if (deviceSupportedFeatures61 != null) {
                deviceSupportedFeatures61.setGenericActivityDataSampleSupported(true);
            }
        }
        DeviceSupportedFeatures deviceSupportedFeatures62 = this.I;
        Intrinsics.checkNotNull(deviceSupportedFeatures62);
        return deviceSupportedFeatures62;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl, com.coveiot.android.idoSdk.IDOResponseListener
    public void onFailure(@NotNull IDOError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onFailure(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl, com.coveiot.android.idoSdk.IDOResponseListener
    public void onResponse(@NotNull IDOBaseRes response) {
        Intrinsics.checkNotNullParameter(response, "response");
        super.onResponse(response);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.IDOBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (BLEManager.isConnected()) {
            super.setUserSettings(request, listener);
            return;
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }
}
