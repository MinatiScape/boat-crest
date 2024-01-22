package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SessionGPSDataRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataRequest;
import com.coveiot.android.bleabstract.request.SetMusicPlaybackStateChangedRequest;
import com.coveiot.android.bleabstract.request.SetMusicVolumeRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.bleabstract.services.SmaBaseBleService;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaManualBPDataReq;
import com.coveiot.android.smasdk.api.SmaSetMusicMetaDataReq;
import com.coveiot.android.smasdk.api.SmaSetPlaybackStateReq;
import com.coveiot.android.smasdk.api.SmaStressDataReq;
import com.coveiot.android.smasdk.api.SmaSyncContactsReq;
import com.coveiot.android.smasdk.api.SmaWorkout2DataReq;
import com.coveiot.android.smasdk.model.Contact;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleCache;
import com.szabh.smable3.entity.BleMusicControl;
import com.szabh.smable3.entity.MusicAttr;
import com.szabh.smable3.entity.MusicEntity;
import com.szabh.smable3.entity.PlaybackState;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmaV2BleApiImpl extends SmaBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final String y;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SmaV2BleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaV2BleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SmaV2BleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3249a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SmaV2BleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SmaV2BleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmaV2BleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3249a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BleKey.values().length];
            try {
                iArr[BleKey.LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BleKey.WORKOUT2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.PRESSURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.USER_PROFILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.STEP_GOAL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.HR_MONITORING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.TEMPERATURE_DETECTING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.HOUR_SYSTEM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.ALARM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.SEDENTARINESS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.NOTIFICATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BleKey.GESTURE_WAKE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BleKey.MUSIC_CONTROL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaV2BleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        String simpleName = SmaV2BleApiImpl.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SmaV2BleApiImpl::class.java.simpleName");
        this.y = simpleName;
        super.registerEvenBus();
        checkAndStartService();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
    }

    public static final void g(Ref.ObjectRef bleBaseRequest, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        T t = bleBaseRequest.element;
        Intrinsics.checkNotNull(t);
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) t);
        bleBaseResponse.setResponseData(null);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void j(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void k(DataResultListener dataResultListener, BleBaseResponse sportResponse) {
        Intrinsics.checkNotNullParameter(sportResponse, "$sportResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(sportResponse);
    }

    public static final void l(DataResultListener dataResultListener, BleBaseResponse tempResponse) {
        Intrinsics.checkNotNullParameter(tempResponse, "$tempResponse");
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(tempResponse);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (getQueue()) {
            SmaBaseReq smaBleReq = getSmaBleReq(baseRequest);
            if (smaBleReq != null) {
                if (smaBleReq.isPriority()) {
                    getQueue().addFirst(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String str = this.y;
                    StringBuilder sb = new StringBuilder();
                    sb.append("addToQueue-> added ");
                    BleCommand bleCommand = baseRequest.getBleCommand();
                    sb.append(bleCommand != null ? bleCommand.name() : null);
                    LogHelper.d(str, sb.toString());
                } else {
                    getQueue().add(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String str2 = this.y;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("addToQueue-> added ");
                    BleCommand bleCommand2 = baseRequest.getBleCommand();
                    sb2.append(bleCommand2 != null ? bleCommand2.name() : null);
                    LogHelper.d(str2, sb2.toString());
                }
            } else if (!(baseRequest instanceof ActivityModeWithSamplesRequest)) {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(this.y, "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (request instanceof ActivityModeWithSamplesRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService = getBleService();
                Intrinsics.checkNotNull(bleService);
                if (bleService.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string));
        } else if (request instanceof SessionGPSDataRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService2 = getBleService();
                Intrinsics.checkNotNull(bleService2);
                if (bleService2.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string2 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string2));
        } else if (request instanceof ActivityModeSummaryRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService3 = getBleService();
                Intrinsics.checkNotNull(bleService3);
                if (bleService3.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string3 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string3));
        } else if (request instanceof StressDataRequest) {
            if (getBleService() != null) {
                SmaBaseBleService bleService4 = getBleService();
                Intrinsics.checkNotNull(bleService4);
                if (bleService4.getConnectionState() == CloveSmaBleState.BleState.CONNECTED) {
                    request.setRequId(UUID.randomUUID().toString());
                    request.setResponseListener(listener);
                    addToQueue(request);
                    sendCommandRequest();
                    return;
                }
            }
            String string4 = getContext().getString(R.string.band_not_connected);
            Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.band_not_connected)");
            listener.onDataError(new BleBaseError(string4));
        } else {
            super.getData(request, listener);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        setDeviceSupportedFeat(new DeviceSupportedFeatures());
        DeviceSupportedFeatures deviceSupportedFeat = getDeviceSupportedFeat();
        if (deviceSupportedFeat != null) {
            deviceSupportedFeat.setStepsSupported(BleCache.INSTANCE.getMDataKeys().contains(1282));
        }
        DeviceSupportedFeatures deviceSupportedFeat2 = getDeviceSupportedFeat();
        if (deviceSupportedFeat2 != null) {
            deviceSupportedFeat2.setSleepSupported(BleCache.INSTANCE.getMDataKeys().contains(1285));
        }
        DeviceSupportedFeatures deviceSupportedFeat3 = getDeviceSupportedFeat();
        if (deviceSupportedFeat3 != null) {
            deviceSupportedFeat3.setHeartRateSupported(BleCache.INSTANCE.getMDataKeys().contains(1283));
        }
        DeviceSupportedFeatures deviceSupportedFeat4 = getDeviceSupportedFeat();
        if (deviceSupportedFeat4 != null) {
            deviceSupportedFeat4.setTemparatureHistorySupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat5 = getDeviceSupportedFeat();
        if (deviceSupportedFeat5 != null) {
            deviceSupportedFeat5.setManualBpSupported(BleCache.INSTANCE.getMDataKeys().contains(1284));
        }
        DeviceSupportedFeatures deviceSupportedFeat6 = getDeviceSupportedFeat();
        if (deviceSupportedFeat6 != null) {
            deviceSupportedFeat6.setManualSpo2SupportedOnBand(BleCache.INSTANCE.getMDataKeys().contains(1289));
        }
        DeviceSupportedFeatures deviceSupportedFeat7 = getDeviceSupportedFeat();
        if (deviceSupportedFeat7 != null) {
            deviceSupportedFeat7.setStressHistorySupported(BleCache.INSTANCE.getMDataKeys().contains(1293));
        }
        DeviceSupportedFeatures deviceSupportedFeat8 = getDeviceSupportedFeat();
        if (deviceSupportedFeat8 != null) {
            deviceSupportedFeat8.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat9 = getDeviceSupportedFeat();
        if (deviceSupportedFeat9 != null) {
            deviceSupportedFeat9.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat10 = getDeviceSupportedFeat();
        if (deviceSupportedFeat10 != null) {
            deviceSupportedFeat10.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat11 = getDeviceSupportedFeat();
        if (deviceSupportedFeat11 != null) {
            deviceSupportedFeat11.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat12 = getDeviceSupportedFeat();
        if (deviceSupportedFeat12 != null) {
            deviceSupportedFeat12.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat13 = getDeviceSupportedFeat();
        if (deviceSupportedFeat13 != null) {
            deviceSupportedFeat13.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat14 = getDeviceSupportedFeat();
        if (deviceSupportedFeat14 != null) {
            deviceSupportedFeat14.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat15 = getDeviceSupportedFeat();
        if (deviceSupportedFeat15 != null) {
            deviceSupportedFeat15.setPhoneFinderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat16 = getDeviceSupportedFeat();
        if (deviceSupportedFeat16 != null) {
            deviceSupportedFeat16.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat17 = getDeviceSupportedFeat();
        if (deviceSupportedFeat17 != null) {
            deviceSupportedFeat17.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat18 = getDeviceSupportedFeat();
        if (deviceSupportedFeat18 != null) {
            deviceSupportedFeat18.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat19 = getDeviceSupportedFeat();
        if (deviceSupportedFeat19 != null) {
            deviceSupportedFeat19.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat20 = getDeviceSupportedFeat();
        if (deviceSupportedFeat20 != null) {
            deviceSupportedFeat20.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat21 = getDeviceSupportedFeat();
        if (deviceSupportedFeat21 != null) {
            deviceSupportedFeat21.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat22 = getDeviceSupportedFeat();
        if (deviceSupportedFeat22 != null) {
            deviceSupportedFeat22.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat23 = getDeviceSupportedFeat();
        if (deviceSupportedFeat23 != null) {
            deviceSupportedFeat23.setTemperatureUnitSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat24 = getDeviceSupportedFeat();
        if (deviceSupportedFeat24 != null) {
            deviceSupportedFeat24.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat25 = getDeviceSupportedFeat();
        if (deviceSupportedFeat25 != null) {
            deviceSupportedFeat25.setAutoHrSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1283));
        }
        DeviceSupportedFeatures deviceSupportedFeat26 = getDeviceSupportedFeat();
        if (deviceSupportedFeat26 != null) {
            deviceSupportedFeat26.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat27 = getDeviceSupportedFeat();
        if (deviceSupportedFeat27 != null) {
            deviceSupportedFeat27.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat28 = getDeviceSupportedFeat();
        if (deviceSupportedFeat28 != null) {
            deviceSupportedFeat28.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat29 = getDeviceSupportedFeat();
        if (deviceSupportedFeat29 != null) {
            deviceSupportedFeat29.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat30 = getDeviceSupportedFeat();
        if (deviceSupportedFeat30 != null) {
            deviceSupportedFeat30.setSyncBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat31 = getDeviceSupportedFeat();
        if (deviceSupportedFeat31 != null) {
            deviceSupportedFeat31.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat32 = getDeviceSupportedFeat();
        if (deviceSupportedFeat32 != null) {
            deviceSupportedFeat32.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat33 = getDeviceSupportedFeat();
        if (deviceSupportedFeat33 != null) {
            deviceSupportedFeat33.setAppSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat34 = getDeviceSupportedFeat();
        if (deviceSupportedFeat34 != null) {
            deviceSupportedFeat34.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat35 = getDeviceSupportedFeat();
        if (deviceSupportedFeat35 != null) {
            deviceSupportedFeat35.setMaxCharSupportedInNotification(60);
        }
        DeviceSupportedFeatures deviceSupportedFeat36 = getDeviceSupportedFeat();
        if (deviceSupportedFeat36 != null) {
            deviceSupportedFeat36.setAutoTemperatureSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat37 = getDeviceSupportedFeat();
        if (deviceSupportedFeat37 != null) {
            deviceSupportedFeat37.setScheduleReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat38 = getDeviceSupportedFeat();
        if (deviceSupportedFeat38 != null) {
            deviceSupportedFeat38.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat39 = getDeviceSupportedFeat();
        if (deviceSupportedFeat39 != null) {
            deviceSupportedFeat39.setMusicPlaybackStateChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat40 = getDeviceSupportedFeat();
        if (deviceSupportedFeat40 != null) {
            deviceSupportedFeat40.setMusicMetaDataChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat41 = getDeviceSupportedFeat();
        if (deviceSupportedFeat41 != null) {
            deviceSupportedFeat41.setMusicVolumeChangeFromAppSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat42 = getDeviceSupportedFeat();
        if (deviceSupportedFeat42 != null) {
            deviceSupportedFeat42.setFemaleWellnessSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat43 = getDeviceSupportedFeat();
        Intrinsics.checkNotNull(deviceSupportedFeat43);
        return deviceSupportedFeat43;
    }

    @NotNull
    public final String getMTAG() {
        return this.y;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @Nullable
    public SmaBaseReq getSmaBleReq(@NotNull BleBaseRequest request) {
        String format;
        SmaBaseReq smaBaseReq;
        Intrinsics.checkNotNullParameter(request, "request");
        String str = this.y;
        LogHelper.d(str, "getSmaBleReq " + request);
        BleMusicControl bleMusicControl = null;
        if (request instanceof ActivityModeWithSamplesRequest) {
            if (isHeartRateDataSyncRequired()) {
                SessionHeartRateRequest sessionHeartRateRequest = new SessionHeartRateRequest();
                BaseListener responseListener = request.getResponseListener();
                Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                getData(sessionHeartRateRequest, (DataResultListener) responseListener);
            }
            SessionGPSDataRequest sessionGPSDataRequest = new SessionGPSDataRequest();
            BaseListener responseListener2 = request.getResponseListener();
            Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
            getData(sessionGPSDataRequest, (DataResultListener) responseListener2);
            ActivityModeSummaryRequest activityModeSummaryRequest = new ActivityModeSummaryRequest();
            BaseListener responseListener3 = request.getResponseListener();
            Intrinsics.checkNotNull(responseListener3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
            getData(activityModeSummaryRequest, (DataResultListener) responseListener3);
            return null;
        } else if (request instanceof SessionGPSDataRequest) {
            SmaManualBPDataReq smaManualBPDataReq = new SmaManualBPDataReq();
            smaManualBPDataReq.setReqId(request.getRequId());
            smaManualBPDataReq.setKey(BleKey.LOCATION);
            smaManualBPDataReq.setKeyFlag(BleKeyFlag.READ);
            return smaManualBPDataReq;
        } else if (request instanceof ActivityModeSummaryRequest) {
            SmaWorkout2DataReq smaWorkout2DataReq = new SmaWorkout2DataReq();
            smaWorkout2DataReq.setReqId(request.getRequId());
            smaWorkout2DataReq.setKey(BleKey.WORKOUT2);
            smaWorkout2DataReq.setKeyFlag(BleKeyFlag.READ);
            return smaWorkout2DataReq;
        } else {
            if (request instanceof SyncContactsRequest) {
                SyncContactsRequest syncContactsRequest = (SyncContactsRequest) request;
                if (AppUtils.isEmpty(syncContactsRequest.getContactDataArrayList())) {
                    SmaBaseReq smaSyncContactsReq = new SmaSyncContactsReq(null);
                    smaSyncContactsReq.setKey(BleKey.CONTACT);
                    smaSyncContactsReq.setKeyFlag(BleKeyFlag.DELETE);
                    smaSyncContactsReq.setReqId(request.getRequId());
                    smaBaseReq = smaSyncContactsReq;
                } else {
                    ArrayList<ContactData> contactDataArrayList = syncContactsRequest.getContactDataArrayList();
                    Intrinsics.checkNotNullExpressionValue(contactDataArrayList, "syncContactRequest.contactDataArrayList");
                    ArrayList arrayList = new ArrayList();
                    Iterator<ContactData> it = contactDataArrayList.iterator();
                    while (it.hasNext()) {
                        ContactData next = it.next();
                        arrayList.add(new Contact(next.getName(), next.getMobileNumber()));
                    }
                    SmaSyncContactsReq smaSyncContactsReq2 = new SmaSyncContactsReq(arrayList);
                    smaSyncContactsReq2.setKey(BleKey.CONTACT);
                    smaSyncContactsReq2.setKeyFlag(BleKeyFlag.UPDATE);
                    smaSyncContactsReq2.setReqId(request.getRequId());
                    return smaSyncContactsReq2;
                }
            } else if (request instanceof StressDataRequest) {
                SmaStressDataReq smaStressDataReq = new SmaStressDataReq();
                smaStressDataReq.setKey(BleKey.PRESSURE);
                smaStressDataReq.setKeyFlag(BleKeyFlag.READ);
                smaStressDataReq.setReqId(request.getRequId());
                return smaStressDataReq;
            } else if (request instanceof SetMusicMetaDataRequest) {
                SmaSetMusicMetaDataReq smaSetMusicMetaDataReq = new SmaSetMusicMetaDataReq();
                ArrayList arrayList2 = new ArrayList();
                SetMusicMetaDataRequest setMusicMetaDataRequest = (SetMusicMetaDataRequest) request;
                String str2 = setMusicMetaDataRequest.title;
                if (str2 != null) {
                    MusicEntity musicEntity = MusicEntity.TRACK;
                    MusicAttr musicAttr = MusicAttr.TRACK_TITLE;
                    Intrinsics.checkNotNullExpressionValue(str2, "request.title");
                    arrayList2.add(new BleMusicControl(musicEntity, musicAttr, str2));
                }
                String str3 = setMusicMetaDataRequest.album;
                if (str3 != null) {
                    MusicEntity musicEntity2 = MusicEntity.TRACK;
                    MusicAttr musicAttr2 = MusicAttr.TRACK_ALBUM;
                    Intrinsics.checkNotNullExpressionValue(str3, "request.album");
                    arrayList2.add(new BleMusicControl(musicEntity2, musicAttr2, str3));
                }
                String str4 = setMusicMetaDataRequest.artist;
                if (str4 != null) {
                    MusicEntity musicEntity3 = MusicEntity.TRACK;
                    MusicAttr musicAttr3 = MusicAttr.TRACK_ARTIST;
                    Intrinsics.checkNotNullExpressionValue(str4, "request.artist");
                    arrayList2.add(new BleMusicControl(musicEntity3, musicAttr3, str4));
                }
                smaSetMusicMetaDataReq.setMusicControls(arrayList2);
                smaSetMusicMetaDataReq.setKey(BleKey.MUSIC_CONTROL);
                smaSetMusicMetaDataReq.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetMusicMetaDataReq.setReqId(request.getRequId());
                return smaSetMusicMetaDataReq;
            } else if (request instanceof SetMusicPlaybackStateChangedRequest) {
                SmaSetPlaybackStateReq smaSetPlaybackStateReq = new SmaSetPlaybackStateReq();
                SetMusicPlaybackStateChangedRequest setMusicPlaybackStateChangedRequest = (SetMusicPlaybackStateChangedRequest) request;
                if (setMusicPlaybackStateChangedRequest.getMusicControlState() != null && setMusicPlaybackStateChangedRequest.getMusicControlState() == MusicControlState.PLAY) {
                    bleMusicControl = new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, String.valueOf((int) PlaybackState.PLAYING.getMState()));
                }
                if (setMusicPlaybackStateChangedRequest.getMusicControlState() != null && setMusicPlaybackStateChangedRequest.getMusicControlState() == MusicControlState.PAUSE) {
                    bleMusicControl = new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_PLAYBACK_INFO, String.valueOf((int) PlaybackState.PAUSED.getMState()));
                }
                smaSetPlaybackStateReq.setMusicControl(bleMusicControl);
                smaSetPlaybackStateReq.setKey(BleKey.MUSIC_CONTROL);
                smaSetPlaybackStateReq.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetPlaybackStateReq.setReqId(request.getRequId());
                smaBaseReq = smaSetPlaybackStateReq;
            } else if (request instanceof SetMusicVolumeRequest) {
                SmaSetMusicMetaDataReq smaSetMusicMetaDataReq2 = new SmaSetMusicMetaDataReq();
                ArrayList arrayList3 = new ArrayList();
                SetMusicVolumeRequest setMusicVolumeRequest = (SetMusicVolumeRequest) request;
                float volume = setMusicVolumeRequest.getVolume() / setMusicVolumeRequest.getMaxVolume();
                if ((Float.isInfinite(volume) || Float.isNaN(volume)) ? false : true) {
                    format = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH)).format(new BigDecimal(String.valueOf(volume)));
                    Intrinsics.checkNotNullExpressionValue(format, "{\n            DecimalFor…toBigDecimal())\n        }");
                } else {
                    format = "0.00";
                }
                arrayList3.add(new BleMusicControl(MusicEntity.PLAYER, MusicAttr.PLAYER_VOLUME, format));
                smaSetMusicMetaDataReq2.setMusicControls(arrayList3);
                smaSetMusicMetaDataReq2.setKey(BleKey.MUSIC_CONTROL);
                smaSetMusicMetaDataReq2.setKeyFlag(BleKeyFlag.UPDATE);
                smaSetMusicMetaDataReq2.setReqId(request.getRequId());
                return smaSetMusicMetaDataReq2;
            } else {
                return super.getSmaBleReq(request);
            }
            return smaBaseReq;
        }
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveSmaBleState cloveSmaBleState) {
        super.onConnectionStateChangedHandler(cloveSmaBleState);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0165 A[Catch: Exception -> 0x022c, TryCatch #0 {Exception -> 0x022c, blocks: (B:3:0x0005, B:5:0x0041, B:7:0x004b, B:9:0x0061, B:11:0x006f, B:17:0x0095, B:18:0x009a, B:20:0x00a0, B:21:0x00a3, B:23:0x00a9, B:25:0x00b1, B:26:0x00c0, B:28:0x00c6, B:30:0x00ce, B:32:0x00f4, B:33:0x0129, B:34:0x013a, B:36:0x0149, B:40:0x0159, B:46:0x0165, B:48:0x0184, B:50:0x018e, B:54:0x019e, B:55:0x01bf, B:56:0x01c3, B:57:0x01c7, B:59:0x01d3, B:60:0x01e3, B:61:0x01e6, B:62:0x01ea, B:64:0x01f0, B:65:0x01f3, B:66:0x0228), top: B:71:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0184 A[Catch: Exception -> 0x022c, TryCatch #0 {Exception -> 0x022c, blocks: (B:3:0x0005, B:5:0x0041, B:7:0x004b, B:9:0x0061, B:11:0x006f, B:17:0x0095, B:18:0x009a, B:20:0x00a0, B:21:0x00a3, B:23:0x00a9, B:25:0x00b1, B:26:0x00c0, B:28:0x00c6, B:30:0x00ce, B:32:0x00f4, B:33:0x0129, B:34:0x013a, B:36:0x0149, B:40:0x0159, B:46:0x0165, B:48:0x0184, B:50:0x018e, B:54:0x019e, B:55:0x01bf, B:56:0x01c3, B:57:0x01c7, B:59:0x01d3, B:60:0x01e3, B:61:0x01e6, B:62:0x01ea, B:64:0x01f0, B:65:0x01f3, B:66:0x0228), top: B:71:0x0005 }] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.smasdk.SmaResponseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.smasdk.api.SmaBaseRes r12) {
        /*
            Method dump skipped, instructions count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaV2BleApiImpl.onResponse(com.coveiot.android.smasdk.api.SmaBaseRes):void");
    }

    @Subscribe
    public final void onResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.processResponseEventReceived(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void processNextCommand() {
        if (getQueue() == null || getQueue().size() <= 0) {
            return;
        }
        SmaBaseReq smaBleReq = getSmaBleReq(getQueue().get(0).getBaseRequest());
        if (smaBleReq != null) {
            if (getKhCurrentCommand() != null) {
                BleBaseRequest khCurrentCommand = getKhCurrentCommand();
                Intrinsics.checkNotNull(khCurrentCommand);
                if (!khCurrentCommand.isComplete()) {
                    return;
                }
            }
            sendRequestToBleService(smaBleReq);
            return;
        }
        sendCommandNotFoundError(getQueue().get(0).getBaseRequest());
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void sendCommandRequest() {
        if (getQueue() == null || getQueue().size() <= 0 || getKhCurrentCommand() != null) {
            return;
        }
        processNextCommand();
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        super.setUserSettings(request, listener);
    }
}
