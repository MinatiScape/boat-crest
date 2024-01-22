package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.CloveSmaBleState;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetWatchFacePositionRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.WatchFacePositionResponse;
import com.coveiot.android.bleabstract.services.SmaBaseBleService;
import com.coveiot.android.smasdk.api.SmaBaseReq;
import com.coveiot.android.smasdk.api.SmaBaseRes;
import com.coveiot.android.smasdk.api.SmaGetWatchFacePositionReq;
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
public final class SmaF2BleApiImpl extends SmaBaseBleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<SmaF2BleApiImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.SmaF2BleApiImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, SmaF2BleApiImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3239a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, SmaF2BleApiImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public SmaF2BleApiImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SmaF2BleApiImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3239a);
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
                iArr[BleKey.WATCH_FACE_SWITCH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmaF2BleApiImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        super.registerEvenBus();
        checkAndStartService();
    }

    public static final void a(Ref.ObjectRef bleBaseRequest, WatchFacePositionResponse watchFacePositionResponse, DataResultListener dataResultListener, SmaF2BleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(bleBaseRequest, "$bleBaseRequest");
        Intrinsics.checkNotNullParameter(watchFacePositionResponse, "$watchFacePositionResponse");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BleBaseResponse bleBaseResponse = new BleBaseResponse((BleBaseRequest) bleBaseRequest.element);
        bleBaseResponse.setResponseData(watchFacePositionResponse);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
        this$0.setCompleteAndProcessNext();
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
        if (request instanceof GetWatchFacePositionRequest) {
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
            return;
        }
        super.getData(request, listener);
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
            deviceSupportedFeat7.setPersonalInfoSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat8 = getDeviceSupportedFeat();
        if (deviceSupportedFeat8 != null) {
            deviceSupportedFeat8.setStepGoalSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat9 = getDeviceSupportedFeat();
        if (deviceSupportedFeat9 != null) {
            deviceSupportedFeat9.setCallNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat10 = getDeviceSupportedFeat();
        if (deviceSupportedFeat10 != null) {
            deviceSupportedFeat10.setSmsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat11 = getDeviceSupportedFeat();
        if (deviceSupportedFeat11 != null) {
            deviceSupportedFeat11.setMessageReadSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat12 = getDeviceSupportedFeat();
        if (deviceSupportedFeat12 != null) {
            deviceSupportedFeat12.setSocialNotificationSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat13 = getDeviceSupportedFeat();
        if (deviceSupportedFeat13 != null) {
            deviceSupportedFeat13.setHandSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat14 = getDeviceSupportedFeat();
        if (deviceSupportedFeat14 != null) {
            deviceSupportedFeat14.setPhoneFinderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat15 = getDeviceSupportedFeat();
        if (deviceSupportedFeat15 != null) {
            deviceSupportedFeat15.setLiveStepsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat16 = getDeviceSupportedFeat();
        if (deviceSupportedFeat16 != null) {
            deviceSupportedFeat16.setLiveHeartRateSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat17 = getDeviceSupportedFeat();
        if (deviceSupportedFeat17 != null) {
            deviceSupportedFeat17.setLiveBPSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat18 = getDeviceSupportedFeat();
        if (deviceSupportedFeat18 != null) {
            deviceSupportedFeat18.setHandPreferenceSettingsSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat19 = getDeviceSupportedFeat();
        if (deviceSupportedFeat19 != null) {
            deviceSupportedFeat19.setTimeFormatSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat20 = getDeviceSupportedFeat();
        if (deviceSupportedFeat20 != null) {
            deviceSupportedFeat20.setDistanceUnitSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat21 = getDeviceSupportedFeat();
        if (deviceSupportedFeat21 != null) {
            deviceSupportedFeat21.setLiftWristToViewSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat22 = getDeviceSupportedFeat();
        if (deviceSupportedFeat22 != null) {
            deviceSupportedFeat22.setTemperatureUnitSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat23 = getDeviceSupportedFeat();
        if (deviceSupportedFeat23 != null) {
            deviceSupportedFeat23.setProbeFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat24 = getDeviceSupportedFeat();
        if (deviceSupportedFeat24 != null) {
            deviceSupportedFeat24.setAutoHrSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat25 = getDeviceSupportedFeat();
        if (deviceSupportedFeat25 != null) {
            deviceSupportedFeat25.setMultipleAlarmsSupportedAtATime(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat26 = getDeviceSupportedFeat();
        if (deviceSupportedFeat26 != null) {
            deviceSupportedFeat26.setOnceAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat27 = getDeviceSupportedFeat();
        if (deviceSupportedFeat27 != null) {
            deviceSupportedFeat27.setSportsModeHistorySupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat28 = getDeviceSupportedFeat();
        if (deviceSupportedFeat28 != null) {
            deviceSupportedFeat28.setSampleDataSupportedInSportMode(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat29 = getDeviceSupportedFeat();
        if (deviceSupportedFeat29 != null) {
            deviceSupportedFeat29.setSyncBandSettingsSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat30 = getDeviceSupportedFeat();
        if (deviceSupportedFeat30 != null) {
            deviceSupportedFeat30.setSportModeSupportedFromApp(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat31 = getDeviceSupportedFeat();
        if (deviceSupportedFeat31 != null) {
            deviceSupportedFeat31.setDeviceSettingsSupportedInOneCommand(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat32 = getDeviceSupportedFeat();
        if (deviceSupportedFeat32 != null) {
            deviceSupportedFeat32.setAppSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat33 = getDeviceSupportedFeat();
        if (deviceSupportedFeat33 != null) {
            deviceSupportedFeat33.setBandSocialDistanceFeatureSupported(false);
        }
        DeviceSupportedFeatures deviceSupportedFeat34 = getDeviceSupportedFeat();
        if (deviceSupportedFeat34 != null) {
            deviceSupportedFeat34.setMaxCharSupportedInNotification(60);
        }
        DeviceSupportedFeatures deviceSupportedFeat35 = getDeviceSupportedFeat();
        if (deviceSupportedFeat35 != null) {
            deviceSupportedFeat35.setAutoTemperatureSettingsSupported(BleCache.INSTANCE.getMDataKeys().contains(1288));
        }
        DeviceSupportedFeatures deviceSupportedFeat36 = getDeviceSupportedFeat();
        if (deviceSupportedFeat36 != null) {
            deviceSupportedFeat36.setScheduleReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat37 = getDeviceSupportedFeat();
        if (deviceSupportedFeat37 != null) {
            deviceSupportedFeat37.setTitleSupportedInNotification(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat38 = getDeviceSupportedFeat();
        if (deviceSupportedFeat38 != null) {
            deviceSupportedFeat38.setFemaleWellnessSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat39 = getDeviceSupportedFeat();
        if (deviceSupportedFeat39 != null) {
            deviceSupportedFeat39.setSedentaryReminderSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat40 = getDeviceSupportedFeat();
        if (deviceSupportedFeat40 != null) {
            deviceSupportedFeat40.setVibrationAlarmSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat41 = getDeviceSupportedFeat();
        if (deviceSupportedFeat41 != null) {
            deviceSupportedFeat41.setNudgeSupported(true);
        }
        DeviceSupportedFeatures deviceSupportedFeat42 = getDeviceSupportedFeat();
        Intrinsics.checkNotNull(deviceSupportedFeat42);
        return deviceSupportedFeat42;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl
    @Nullable
    public SmaBaseReq getSmaBleReq(@NotNull BleBaseRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        String tag = getTAG();
        LogHelper.d(tag, "getSmaBleReq " + request);
        if (request instanceof GetWatchFacePositionRequest) {
            SmaGetWatchFacePositionReq smaGetWatchFacePositionReq = new SmaGetWatchFacePositionReq();
            smaGetWatchFacePositionReq.setReqId(request.getRequId());
            smaGetWatchFacePositionReq.setKey(BleKey.WATCH_FACE_SWITCH);
            smaGetWatchFacePositionReq.setKeyFlag(BleKeyFlag.READ);
            return smaGetWatchFacePositionReq;
        }
        return super.getSmaBleReq(request);
    }

    @Subscribe
    public final void onConnectionStateChanged(@Nullable CloveSmaBleState cloveSmaBleState) {
        super.onConnectionStateChangedHandler(cloveSmaBleState);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.coveiot.android.bleabstract.request.BleBaseRequest] */
    @Override // com.coveiot.android.bleabstract.bleimpl.SmaBaseBleApiImpl, com.coveiot.android.smasdk.SmaResponseListener
    public void onResponse(@NotNull SmaBaseRes baseRes) {
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            String tag = getTAG();
            LogHelper.d(tag, "onResponse(baseRes: SmaBaseRes-> " + baseRes.getBaseReq().getClass().getSimpleName() + ", " + baseRes.getBaseReq().getKey().name());
            if (baseRes.getBaseReq() == null || baseRes.getBaseReq().getReqId() == null) {
                return;
            }
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = getFromQueue(baseRes.getBaseReq());
            setSmaBaseRes(baseRes);
            T t = objectRef.element;
            if (t != 0) {
                Intrinsics.checkNotNull(t);
                if (((BleBaseRequest) t).getResponseListener() instanceof DataResultListener) {
                    T t2 = objectRef.element;
                    Intrinsics.checkNotNull(t2);
                    final DataResultListener dataResultListener = (DataResultListener) ((BleBaseRequest) t2).getResponseListener();
                    if (WhenMappings.$EnumSwitchMapping$0[baseRes.getBaseReq().getKey().ordinal()] == 1) {
                        Handler syncTimeOutHandler = getSyncTimeOutHandler();
                        if (syncTimeOutHandler != null) {
                            syncTimeOutHandler.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getBaseReq() instanceof SmaGetWatchFacePositionReq) {
                            Object obj = baseRes.getObj();
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
                            int intValue = ((Integer) obj).intValue();
                            final WatchFacePositionResponse watchFacePositionResponse = new WatchFacePositionResponse();
                            watchFacePositionResponse.setWatchFacePosition(Integer.valueOf(intValue));
                            watchFacePositionResponse.setComplete(true);
                            getMDataResponseHandler().post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.b8
                                @Override // java.lang.Runnable
                                public final void run() {
                                    SmaF2BleApiImpl.a(Ref.ObjectRef.this, watchFacePositionResponse, dataResultListener, this);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    super.onResponse(baseRes);
                } else if (((BleBaseRequest) objectRef.element).getResponseListener() instanceof SettingsResultListener) {
                    super.onResponse(baseRes);
                } else {
                    super.onResponse(baseRes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public final void onResponseEventReceived(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.processResponseEventReceived(responseEvent);
    }
}
