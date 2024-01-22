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
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.ActivityModeSummaryRequest;
import com.coveiot.android.bleabstract.request.ActivityModeWithSamplesRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SessionGPSDataRequest;
import com.coveiot.android.bleabstract.request.SessionHeartRateRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.services.SmaBaseBleService;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaManualBPDataReq;
import com.coveiot.android.smasdk.api.SmaWorkoutDataReq;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import com.szabh.smable3.BleKey;
import com.szabh.smable3.BleKeyFlag;
import com.szabh.smable3.component.BleCache;
import java.util.UUID;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SmaS10BleApiImpl extends SmaBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SmaS10BleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaS10BleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SmaS10BleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3244a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SmaS10BleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SmaS10BleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmaS10BleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3244a);
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
                iArr[BleKey.WORKOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BleKey.USER_PROFILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BleKey.STEP_GOAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BleKey.HR_MONITORING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BleKey.TEMPERATURE_DETECTING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BleKey.HOUR_SYSTEM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BleKey.ALARM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BleKey.SEDENTARINESS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BleKey.NOTIFICATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BleKey.GESTURE_WAKE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaS10BleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        super.registerEvenBus();
        checkAndStartService();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        SettingsResultListener settingsResultListener = (SettingsResultListener) ((BleBaseRequest) bleBaseRequest.element).getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse((BleBaseRequest) bleBaseRequest.element));
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

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    public void addToQueue(@NotNull BleBaseRequest baseRequest) {
        Intrinsics.checkNotNullParameter(baseRequest, "baseRequest");
        synchronized (getQueue()) {
            SmaBaseReq smaBleReq = getSmaBleReq(baseRequest);
            if (smaBleReq != null) {
                if (smaBleReq.isPriority()) {
                    getQueue().addFirst(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String tag = getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("addToQueue-> added ");
                    BleCommand bleCommand = baseRequest.getBleCommand();
                    sb.append(bleCommand != null ? bleCommand.name() : null);
                    LogHelper.d(tag, sb.toString());
                } else {
                    getQueue().add(new SmaBaseBleApiImpl.QueueObject(baseRequest));
                    String tag2 = getTAG();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("addToQueue-> added ");
                    BleCommand bleCommand2 = baseRequest.getBleCommand();
                    sb2.append(bleCommand2 != null ? bleCommand2.name() : null);
                    LogHelper.d(tag2, sb2.toString());
                }
            } else {
                sendCommandNotFoundError(baseRequest);
            }
        }
    }

    public final void checkAndStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(getTAG(), "checkAndStartService-> service is not running ++ ");
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
            deviceSupportedFeat13.setPhoneFinderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat14 = getDeviceSupportedFeat();
        if (deviceSupportedFeat14 != null) {
            deviceSupportedFeat14.setLiveStepsSupported(false);
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
            deviceSupportedFeat21.setTemperatureUnitSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
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
            deviceSupportedFeat34.setAutoTemperatureSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat35 = getDeviceSupportedFeat();
        if (deviceSupportedFeat35 != null) {
            deviceSupportedFeat35.setScheduleReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat36 = getDeviceSupportedFeat();
        if (deviceSupportedFeat36 != null) {
            deviceSupportedFeat36.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat37 = getDeviceSupportedFeat();
        if (deviceSupportedFeat37 != null) {
            deviceSupportedFeat37.setFemaleWellnessSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat38 = getDeviceSupportedFeat();
        Intrinsics.checkNotNull(deviceSupportedFeat38);
        return deviceSupportedFeat38;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @Nullable
    public SmaBaseReq getSmaBleReq(@NotNull BleBaseRequest request) {
        SmaBaseReq smaWorkoutDataReq;
        Intrinsics.checkNotNullParameter(request, "request");
        String tag = getTAG();
        LogHelper.d(tag, "getSmaBleReq " + request);
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
        }
        if (request instanceof SessionGPSDataRequest) {
            smaWorkoutDataReq = new SmaManualBPDataReq();
            smaWorkoutDataReq.setReqId(request.getRequId());
            smaWorkoutDataReq.setKey(BleKey.LOCATION);
            smaWorkoutDataReq.setKeyFlag(BleKeyFlag.READ);
        } else if (request instanceof ActivityModeSummaryRequest) {
            smaWorkoutDataReq = new SmaWorkoutDataReq();
            smaWorkoutDataReq.setReqId(request.getRequId());
            smaWorkoutDataReq.setKey(BleKey.WORKOUT);
            smaWorkoutDataReq.setKeyFlag(BleKeyFlag.READ);
        } else {
            return super.getSmaBleReq(request);
        }
        return smaWorkoutDataReq;
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveSmaBleState cloveSmaBleState) {
        super.onConnectionStateChangedHandler(cloveSmaBleState);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c6 A[Catch: Exception -> 0x018f, TryCatch #0 {Exception -> 0x018f, blocks: (B:3:0x0005, B:5:0x0043, B:7:0x004d, B:9:0x0063, B:11:0x0071, B:15:0x0094, B:16:0x0099, B:18:0x00aa, B:22:0x00ba, B:28:0x00c6, B:30:0x00e5, B:32:0x00ef, B:36:0x00ff, B:37:0x0120, B:38:0x0124, B:39:0x0128, B:41:0x0134, B:42:0x0144, B:43:0x0147, B:44:0x014b, B:46:0x0151, B:47:0x0154, B:48:0x018b), top: B:53:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e5 A[Catch: Exception -> 0x018f, TryCatch #0 {Exception -> 0x018f, blocks: (B:3:0x0005, B:5:0x0043, B:7:0x004d, B:9:0x0063, B:11:0x0071, B:15:0x0094, B:16:0x0099, B:18:0x00aa, B:22:0x00ba, B:28:0x00c6, B:30:0x00e5, B:32:0x00ef, B:36:0x00ff, B:37:0x0120, B:38:0x0124, B:39:0x0128, B:41:0x0134, B:42:0x0144, B:43:0x0147, B:44:0x014b, B:46:0x0151, B:47:0x0154, B:48:0x018b), top: B:53:0x0005 }] */
    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.smasdk.SmaResponseListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.smasdk.api.SmaBaseRes r10) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.bleabstract.bleimpl.SmaS10BleApiImpl.onResponse(com.coveiot.android.smasdk.api.SmaBaseRes):void");
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
