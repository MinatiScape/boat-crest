package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.R;
import com.coveiot.android.bleabstract.SingletonHolder;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.CloveMatrixBleState;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerMatrix;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.SendWeatherRequest;
import com.coveiot.android.bleabstract.request.SetGoalTargetRequest;
import com.coveiot.android.bleabstract.request.SetSportsPushRequest;
import com.coveiot.android.bleabstract.request.SetWearingHandRequest;
import com.coveiot.android.bleabstract.request.SetWeatherUnitRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.services.MatrixBleService;
import com.coveiot.android.bleabstract.utils.matrixUtils.MatrixUtils;
import com.coveiot.android.matrixsdk.api.MatrixBaseReq;
import com.coveiot.android.matrixsdk.api.MatrixBaseRes;
import com.coveiot.android.matrixsdk.api.MatrixContactReq;
import com.coveiot.android.matrixsdk.api.MatrixGetDNDConfig;
import com.coveiot.android.matrixsdk.api.MatrixSetSportGoalReq;
import com.coveiot.android.matrixsdk.api.MatrixSportsPushReq;
import com.coveiot.android.matrixsdk.api.MatrixTempUnitReq;
import com.coveiot.android.matrixsdk.api.MatrixWeatherReq;
import com.coveiot.android.matrixsdk.api.MatrixWristWearReq;
import com.coveiot.utils.utility.LogHelper;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.weather.WeatherForecast;
import com.htsmart.wristband2.bean.weather.WeatherToday;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class MatrixWaveForceBleImpl extends MatrixBleImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public final String s;
    @Nullable
    public DeviceSupportedFeatures t;

    /* loaded from: classes2.dex */
    public static final class Companion extends SingletonHolder<MatrixWaveForceBleImpl, Context> {

        /* renamed from: com.coveiot.android.bleabstract.bleimpl.MatrixWaveForceBleImpl$Companion$1  reason: invalid class name */
        /* loaded from: classes2.dex */
        public /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, MatrixWaveForceBleImpl> {

            /* renamed from: a  reason: collision with root package name */
            public static final AnonymousClass1 f3203a = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, MatrixWaveForceBleImpl.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public MatrixWaveForceBleImpl invoke(Context context) {
                Context p0 = context;
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new MatrixWaveForceBleImpl(p0);
            }
        }

        public Companion() {
            super(AnonymousClass1.f3203a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MatrixWaveForceBleImpl(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.s = MatrixWaveForceBleImpl.class.getSimpleName();
        initServiceConnection();
        checkANDStartService();
        setMWristbandManager(WristbandApplication.getWristbandManager());
        PreferenceManagerMatrix.getInstance(context).saveBindOrLogin(false);
    }

    public static final void a(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(new BleBaseResponse(bleBaseRequest));
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public final void checkANDStartService() {
        if (!checkIfServiceIsRunning()) {
            LogHelper.d(this.s, "checkAndStartService-> service is not running ++ ");
            startBleService();
            return;
        }
        bindBleService();
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            MatrixBleService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                super.getData(request, listener);
                return;
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onDataError(new BleBaseError(string));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
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
        this.t = deviceSupportedFeatures;
        Intrinsics.checkNotNull(deviceSupportedFeatures);
        return deviceSupportedFeatures;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl
    @Nullable
    public MatrixBaseReq getMatrixBleReq(@NotNull BleBaseRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (request instanceof SendWeatherRequest) {
            WeatherToday weatherToday = new WeatherToday();
            SendWeatherRequest sendWeatherRequest = (SendWeatherRequest) request;
            if (sendWeatherRequest.getListSendWeatherModel() != null) {
                MatrixUtils matrixUtils = MatrixUtils.INSTANCE;
                String weatherText = sendWeatherRequest.getListSendWeatherModel().get(0).getWeatherText();
                Intrinsics.checkNotNullExpressionValue(weatherText, "request.listSendWeatherModel[0].weatherText");
                weatherToday.setWeatherCode(matrixUtils.getMatrixWeatherType(weatherText));
                weatherToday.setHighTemperature(sendWeatherRequest.getListSendWeatherModel().get(0).getTempMax());
                weatherToday.setLowTemperature(sendWeatherRequest.getListSendWeatherModel().get(0).getTempMin());
                weatherToday.setCurrentTemperature(sendWeatherRequest.getListSendWeatherModel().get(0).getTemp());
            }
            ArrayList arrayList = new ArrayList();
            if (sendWeatherRequest.getListSendWeatherModel() != null && sendWeatherRequest.getListSendWeatherModel().size() > 5) {
                int size = sendWeatherRequest.getListSendWeatherModel().size();
                for (int i = 1; i < size; i++) {
                    WeatherForecast weatherForecast = new WeatherForecast();
                    weatherForecast.setHighTemperature(sendWeatherRequest.getListSendWeatherModel().get(i).getTempMax());
                    weatherForecast.setLowTemperature(sendWeatherRequest.getListSendWeatherModel().get(i).getTempMin());
                    MatrixUtils matrixUtils2 = MatrixUtils.INSTANCE;
                    String weatherText2 = sendWeatherRequest.getListSendWeatherModel().get(i).getWeatherText();
                    Intrinsics.checkNotNullExpressionValue(weatherText2, "request.listSendWeatherModel[i].weatherText");
                    weatherForecast.setWeatherCode(matrixUtils2.getMatrixWeatherType(weatherText2));
                    arrayList.add(weatherForecast);
                }
            }
            String placeName = sendWeatherRequest.getPlaceName();
            MatrixWeatherReq matrixWeatherReq = placeName != null ? new MatrixWeatherReq(placeName, weatherToday, arrayList) : null;
            if (matrixWeatherReq == null) {
                return matrixWeatherReq;
            }
            matrixWeatherReq.setReqId(request.getRequId());
            return matrixWeatherReq;
        } else if (request instanceof SyncContactsRequest) {
            LogHelper.d(this.s, "SyncContactsRequest");
            ArrayList arrayList2 = new ArrayList();
            Iterator<ContactData> it = ((SyncContactsRequest) request).getContactDataArrayList().iterator();
            while (it.hasNext()) {
                ContactData next = it.next();
                WristbandContacts wristbandContacts = new WristbandContacts();
                wristbandContacts.setName(next.getName());
                wristbandContacts.setNumber(next.getMobileNumber());
                arrayList2.add(wristbandContacts);
            }
            MatrixContactReq matrixContactReq = new MatrixContactReq(arrayList2);
            matrixContactReq.setReqId(request.getRequId());
            return matrixContactReq;
        } else if (request instanceof StepsTargetRequest) {
            MatrixSetSportGoalReq matrixSetSportGoalReq = new MatrixSetSportGoalReq(((StepsTargetRequest) request).getTarget(), 0, 0);
            matrixSetSportGoalReq.setReqId(request.getRequId());
            return matrixSetSportGoalReq;
        } else if (request instanceof SetGoalTargetRequest) {
            SetGoalTargetRequest setGoalTargetRequest = (SetGoalTargetRequest) request;
            MatrixSetSportGoalReq matrixSetSportGoalReq2 = new MatrixSetSportGoalReq(setGoalTargetRequest.getStepTarget(), setGoalTargetRequest.getDistanceTarget(), setGoalTargetRequest.getCalorieTarget());
            matrixSetSportGoalReq2.setReqId(request.getRequId());
            return matrixSetSportGoalReq2;
        } else if (request instanceof SetWearingHandRequest) {
            String str = this.s;
            StringBuilder sb = new StringBuilder();
            sb.append("SetWearingHandRequest : ");
            SetWearingHandRequest setWearingHandRequest = (SetWearingHandRequest) request;
            sb.append(setWearingHandRequest.isRightHand());
            LogHelper.d(str, sb.toString());
            MatrixWristWearReq matrixWristWearReq = new MatrixWristWearReq(setWearingHandRequest.isRightHand());
            matrixWristWearReq.setReqId(request.getRequId());
            return matrixWristWearReq;
        } else if (request instanceof SetWeatherUnitRequest) {
            MatrixTempUnitReq matrixTempUnitReq = new MatrixTempUnitReq(((SetWeatherUnitRequest) request).getMeasurementUnitType() == MeasurementUnitType.IMPERIAL);
            matrixTempUnitReq.setReqId(request.getRequId());
            return matrixTempUnitReq;
        } else if (request instanceof SetSportsPushRequest) {
            MatrixSportsPushReq matrixSportsPushReq = new MatrixSportsPushReq(((SetSportsPushRequest) request).getFilePath());
            matrixSportsPushReq.setReqId(request.getRequId());
            return matrixSportsPushReq;
        } else {
            return super.getMatrixBleReq(request);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Subscribe
    public void onConnectionStateChanged(@Nullable CloveMatrixBleState cloveMatrixBleState) {
        super.onConnectionStateChangedHandler(cloveMatrixBleState);
    }

    /* JADX WARN: Type inference failed for: r3v10, types: [com.coveiot.android.bleabstract.models.DNDData, T] */
    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.matrixsdk.MatrixResponseListener
    public void onResponse(@NotNull MatrixBaseRes baseRes) {
        Intrinsics.checkNotNullParameter(baseRes, "baseRes");
        try {
            final BleBaseRequest fromQueue = getFromQueue(baseRes.getBaseReq());
            if (fromQueue != null) {
                if (fromQueue.getResponseListener() instanceof DataResultListener) {
                    final DataResultListener dataResultListener = (DataResultListener) fromQueue.getResponseListener();
                    MatrixBaseReq baseReq = baseRes.getBaseReq();
                    if (baseReq instanceof MatrixContactReq) {
                        LogHelper.d(this.s, "onResponseMatrixContactReq: ");
                        getSyncTimeOutHandler().removeCallbacksAndMessages(null);
                        getMDataResponseHandler().post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixWaveForceBleImpl.a(BleBaseRequest.this, dataResultListener);
                            }
                        });
                        setCompleteAndProcessNext(fromQueue);
                    } else if (baseReq instanceof MatrixGetDNDConfig) {
                        Handler syncTimeOutHandler = getSyncTimeOutHandler();
                        if (syncTimeOutHandler != null) {
                            syncTimeOutHandler.removeCallbacksAndMessages(null);
                        }
                        if (baseRes.getObj() != null && (baseRes.getObj() instanceof NotDisturbConfig)) {
                            Object obj = baseRes.getObj();
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.htsmart.wristband2.bean.config.NotDisturbConfig");
                            NotDisturbConfig notDisturbConfig = (NotDisturbConfig) obj;
                            final DataResultListener dataResultListener2 = (DataResultListener) fromQueue.getResponseListener();
                            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                            String str = this.s;
                            LogHelper.d(str, "DNDData notDisturbConfig isEnableAllDay " + notDisturbConfig.isEnableAllDay() + " isEnablePeriodTime " + notDisturbConfig.isEnablePeriodTime() + " start " + notDisturbConfig.getStart() + " end " + notDisturbConfig.getEnd());
                            objectRef.element = new DNDData(notDisturbConfig.isEnableAllDay(), 0, 0, 23, 59);
                            getMDataResponseHandler().post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l6
                                @Override // java.lang.Runnable
                                public final void run() {
                                    MatrixWaveForceBleImpl.a(BleBaseRequest.this, objectRef, dataResultListener2);
                                }
                            });
                        }
                        setCompleteAndProcessNext(fromQueue);
                    } else {
                        super.onResponse(baseRes);
                    }
                } else if (fromQueue.getResponseListener() instanceof SettingsResultListener) {
                    MatrixBaseReq baseReq2 = baseRes.getBaseReq();
                    boolean z = true;
                    if (!(baseReq2 instanceof MatrixSetSportGoalReq ? true : baseReq2 instanceof MatrixWeatherReq ? true : baseReq2 instanceof MatrixWristWearReq)) {
                        z = baseReq2 instanceof MatrixTempUnitReq;
                    }
                    if (z) {
                        getSyncTimeOutHandler().removeCallbacksAndMessages(null);
                        String str2 = this.s;
                        LogHelper.d(str2, "onResponse  ==" + baseRes.getBaseReq());
                        getMDataResponseHandler().post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.j6
                            @Override // java.lang.Runnable
                            public final void run() {
                                MatrixWaveForceBleImpl.a(BleBaseRequest.this, this);
                            }
                        });
                        return;
                    }
                    super.onResponse(baseRes);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.MatrixBleImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (getBleService() != null) {
            MatrixBleService bleService = getBleService();
            Intrinsics.checkNotNull(bleService);
            if (bleService.getConnectionState() == CloveMatrixBleState.BleState.CONNECTED) {
                super.setUserSettings(request, listener);
                return;
            }
        }
        String string = getContext().getString(R.string.band_not_connected);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.band_not_connected)");
        listener.onSettingsError(new BleBaseError(string));
    }

    public static final void a(BleBaseRequest bleBaseRequest, Ref.ObjectRef dndData, DataResultListener dataResultListener) {
        Intrinsics.checkNotNullParameter(dndData, "$dndData");
        BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest);
        bleBaseResponse.setResponseData(dndData.element);
        Intrinsics.checkNotNull(dataResultListener);
        dataResultListener.onDataResponse(bleBaseResponse);
    }

    public static final void a(BleBaseRequest bleBaseRequest, MatrixWaveForceBleImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsResultListener settingsResultListener = (SettingsResultListener) bleBaseRequest.getResponseListener();
        Intrinsics.checkNotNull(settingsResultListener);
        settingsResultListener.onSettingsResponse(new BleBaseResponse(bleBaseRequest));
        this$0.setCompleteAndProcessNext(bleBaseRequest);
    }
}
