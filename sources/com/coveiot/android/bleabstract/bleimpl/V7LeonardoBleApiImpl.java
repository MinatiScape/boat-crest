package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.LiveRawPPGControlRequest;
import com.coveiot.android.bleabstract.request.LiveTemperatureControlRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.ProgressType;
import com.coveiot.sdk.ble.api.request.ChangeTemperatureUnitReq;
import com.coveiot.sdk.ble.api.request.DNDModeReq;
import com.coveiot.sdk.ble.api.request.GetDNDModeSettingsReq;
import com.coveiot.sdk.ble.api.request.LiftWristViewReq;
import com.coveiot.sdk.ble.api.request.SleepDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureDataDataReq;
import com.coveiot.sdk.ble.api.request.TemperatureMeasurementReq;
import com.coveiot.sdk.ble.api.request.TimeIntervalForAutomaticTemperatureReq;
import com.coveiot.sdk.ble.api.request.V7PPGControlReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.ChangeTemperatureUnitRes;
import com.coveiot.sdk.ble.api.response.GetDNDModeSettingsRes;
import com.coveiot.sdk.ble.api.response.LiftWristViewRes;
import com.coveiot.sdk.ble.api.response.LiveTemperatureRes;
import com.coveiot.sdk.ble.api.response.SetDNDModeRes;
import com.coveiot.sdk.ble.api.response.TemperatureDataRes;
import com.coveiot.sdk.ble.api.response.TimeIntervalForAutomaticTemperatureRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.events.ResponseType;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class V7LeonardoBleApiImpl extends LeonardoBleApiImpl {
    public static final String q = "V7LeonardoBleApiImpl";
    public static V7LeonardoBleApiImpl r;
    public MutableLiveData<LiveTemperatureData> o;
    public int p;

    /* renamed from: com.coveiot.android.bleabstract.bleimpl.V7LeonardoBleApiImpl$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f3297a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ResponseType.values().length];
            b = iArr;
            try {
                iArr[ResponseType.GET_LIVE_TEMPERATURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[CommandType.values().length];
            f3297a = iArr2;
            try {
                iArr2[CommandType.TIME_INTERVAL_FOR_AUTOMATIC_TEMPERATURE.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3297a[CommandType.SET_TEMPERATURE_UNIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3297a[CommandType.TEMPERATURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3297a[CommandType.SET_LIFT_WRIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3297a[CommandType.SET_DND_MODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3297a[CommandType.GET_DND_MODE_SETTINGS.ordinal()] = 6;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static V7LeonardoBleApiImpl getInstance(Context context) {
        if (r == null) {
            LeonardoBleApiImpl.context = context.getApplicationContext();
            r = new V7LeonardoBleApiImpl();
        }
        String str = q;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "check for service ++ ", moduleNames.getModuleName());
        LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
        if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
            LogHelper.d(str, "service is not running ++ ", moduleNames.getModuleName());
            try {
                LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
                if (Build.VERSION.SDK_INT >= 26) {
                    LeonardoBleApiImpl.context.startForegroundService(LeonardoBleApiImpl.intent);
                } else {
                    LeonardoBleApiImpl.context.startService(LeonardoBleApiImpl.intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
                BleApiUtils.checkExceptionAndShowNotification(e, LeonardoBleApiImpl.context);
            }
        } else if (LeonardoBleApiImpl.bleService == null) {
            LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
        }
        return r;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(BleBaseRequest bleBaseRequest, DataResultListener dataResultListener) {
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService == null || leonardoBleCmdService.getConnectionState() != CloveBleState.BleState.CONNECTED) {
            if (bleBaseRequest != null) {
                onDataError(dataResultListener, new BleBaseError("Band not connected", bleBaseRequest.getBleCommand()));
                return;
            }
            return;
        }
        bleBaseRequest.setRequId(UUID.randomUUID().toString());
        boolean z = bleBaseRequest instanceof TemperatureDataRequest;
        int i = TimeConstants.DAY;
        long j = 6;
        if (z) {
            this.p = 0;
            TemperatureDataRequest temperatureDataRequest = (TemperatureDataRequest) bleBaseRequest;
            long findDateDifference = BleApiUtils.findDateDifference(temperatureDataRequest.getStartDate(), temperatureDataRequest.getEndDate());
            Date startDate = temperatureDataRequest.getStartDate();
            if (findDateDifference > 6) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.setTimeInMillis(calendar.getTimeInMillis() + ((findDateDifference - 6) * 86400000));
                startDate = calendar.getTime();
            } else {
                j = findDateDifference;
            }
            BleCommand bleCommand = BleCommand.GET_TEMPERATURE_DATA;
            if (getSameCommandCount(bleCommand) > 0) {
                removeSimilarCommand(bleCommand);
            }
            int i2 = Calendar.getInstance().get(11);
            if (Calendar.getInstance().get(12) > 0) {
                i2++;
            }
            int i3 = (int) ((567 * j) + (i2 * 24));
            int i4 = 0;
            while (i4 <= j) {
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(startDate);
                long j2 = j;
                calendar2.setTimeInMillis(calendar2.getTimeInMillis() + (i4 * TimeConstants.DAY));
                Date time = calendar2.getTime();
                int startHour = temperatureDataRequest.getStartHour();
                int endHour = temperatureDataRequest.getEndHour();
                if (startHour == -1) {
                    startHour = 0;
                }
                if (endHour == -1) {
                    endHour = 23;
                }
                TemperatureDataDataReq build = new TemperatureDataDataReq.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setId(new ExpectedDataSize(i3)).setEndHour(endHour).build();
                TemperatureDataRequest build2 = new TemperatureDataRequest.Builder().setStartDate(time).setEndDate(time).setStartHour(startHour).setEndHour(endHour).build();
                build2.setBleCommand(BleCommand.GET_TEMPERATURE_DATA);
                build2.setResponseListener(dataResultListener);
                build2.setRequId(UUID.randomUUID().toString());
                addToQueue(build2);
                build.setReqId(build2.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
                i4++;
                j = j2;
            }
        } else if (bleBaseRequest instanceof SleepDataRequest) {
            SleepDataRequest sleepDataRequest = (SleepDataRequest) bleBaseRequest;
            long findDateDifference2 = BleApiUtils.findDateDifference(sleepDataRequest.getStartDate(), sleepDataRequest.getEndDate());
            int i5 = 0;
            this.sleepDataSize = 0;
            Date startDate2 = sleepDataRequest.getStartDate();
            if (findDateDifference2 > 6) {
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(startDate2);
                calendar3.setTimeInMillis(calendar3.getTimeInMillis() + ((findDateDifference2 - 6) * 86400000));
                startDate2 = calendar3.getTime();
            } else {
                j = findDateDifference2;
            }
            BleCommand bleCommand2 = BleCommand.GET_SLEEP_DATA;
            if (getSameCommandCount(bleCommand2) > 0) {
                removeSimilarCommand(bleCommand2);
            }
            int i6 = (int) ((1 + j) * 360);
            while (i5 <= j) {
                Calendar calendar4 = Calendar.getInstance();
                calendar4.setTime(startDate2);
                int i7 = i6;
                calendar4.setTimeInMillis(calendar4.getTimeInMillis() + (i5 * i));
                Date time2 = calendar4.getTime();
                int startHour2 = sleepDataRequest.getStartHour();
                int endHour2 = sleepDataRequest.getEndHour();
                if (startHour2 == -1) {
                    startHour2 = 12;
                }
                if (endHour2 == -1) {
                    endHour2 = 11;
                }
                SleepDataReq build3 = new SleepDataReq.Builder().setStartDate(time2).setEndDate(time2).setDeviceType("V7").setStartHour(startHour2 < 12 ? 12 : startHour2).setEndHour(endHour2 > 11 ? 11 : endHour2).setId(new ExpectedDataSize(i7)).build();
                SleepDataRequest build4 = new SleepDataRequest.Builder().setStartDate(time2).setEndDate(time2).setStartHour(startHour2).setEndHour(endHour2).build();
                build4.setResponseListener(dataResultListener);
                build4.setBleCommand(BleCommand.GET_SLEEP_DATA);
                build4.setRequId(UUID.randomUUID().toString());
                addToQueue(build4);
                build3.setReqId(build4.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build3, this);
                i5++;
                i6 = i7;
                i = TimeConstants.DAY;
            }
        } else if (bleBaseRequest instanceof GetDNDModeSettingsRequest) {
            GetDNDModeSettingsReq build5 = new GetDNDModeSettingsReq.Builder().build();
            build5.setResponseListener(this);
            bleBaseRequest.setResponseListener(dataResultListener);
            bleBaseRequest.setBleCommand(BleCommand.GET_DND_MODE_SETTINGS);
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            addToQueue(bleBaseRequest);
            build5.setReqId(bleBaseRequest.getRequId());
            LeonardoBleApiImpl.bleService.sendRequest(build5, this);
        } else {
            super.getData(bleBaseRequest, dataResultListener);
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public DeviceSupportedFeatures getDeviceSupportedFeatures() {
        DeviceSupportedFeatures deviceSupportedFeatures = new DeviceSupportedFeatures();
        deviceSupportedFeatures.setMaxDaysOfStepsDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfSleepDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfBpDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRunDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfHeartRateDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRrDataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfCyclingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfSwimmingDataOnBand(0);
        deviceSupportedFeatures.setMaxDaysOfEcgRrDataOnBand(0);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setTemparatureHistorySupported(true);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(false);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(true);
        deviceSupportedFeatures.setGpsSupported(false);
        deviceSupportedFeatures.setCalendarSyncSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setSosSupported(false);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setBandDisplaySupported(true);
        deviceSupportedFeatures.setHandSettingsSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(false);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setBPCalibrationSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(true);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(12);
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(LeonardoBleApiImpl.context, getMacAddress(), deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(CloveBleState cloveBleState) {
        super.onConnectionStateChanged(cloveBleState);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(Error error) {
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(ProgressDataBean progressDataBean) {
        if (progressDataBean.getBaseRequest().getCommandName() == CommandNames.GET_5MIN_TEMPERATURE_DATA) {
            LeonardoBleApiImpl.QueueObject fromQueueWith = getFromQueueWith(progressDataBean.getBaseRequest());
            DataResultListener dataResultListener = (DataResultListener) fromQueueWith.f3181a.getResponseListener();
            if (progressDataBean.getProgressType() == ProgressType.DATA_BYTES) {
                this.p += progressDataBean.getValue();
            }
            dataResultListener.onProgressUpdate(new ProgressData(com.coveiot.android.bleabstract.models.ProgressType.DETERMINATE, (int) ((this.p / ((ExpectedDataSize) progressDataBean.getBaseRequest().getId()).getSize()) * 100.0f), fromQueueWith.f3181a));
            return;
        }
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(BaseResponse baseResponse) {
        String str = q;
        ModuleNames moduleNames = ModuleNames.BLEABSTRACT;
        LogHelper.d(str, "On Response " + baseResponse.getActivityType(), moduleNames.getModuleName());
        switch (AnonymousClass1.f3297a[baseResponse.getActivityType().ordinal()]) {
            case 1:
                BleCommand bleCommand = BleCommand.SET_TEMPERATURE_TIME_INTERVAL;
                LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(bleCommand, baseResponse);
                if (fromQueue != null) {
                    SettingsResultListener settingsResultListener = (SettingsResultListener) fromQueue.f3181a.getResponseListener();
                    if (((TimeIntervalForAutomaticTemperatureRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener, new BleBaseResponse(fromQueue.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener, new BleBaseError("Temperature time interval setting failed", bleCommand));
                        return;
                    }
                }
                return;
            case 2:
                LogHelper.d(str, "SET_TEMPERATURE_UNIT command response received from parser!!!", moduleNames.getModuleName());
                BleCommand bleCommand2 = BleCommand.SET_TEMPERATURE_UNIT;
                LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(bleCommand2, baseResponse);
                if (fromQueue2 != null) {
                    SettingsResultListener settingsResultListener2 = (SettingsResultListener) fromQueue2.f3181a.getResponseListener();
                    BleBaseResponse bleBaseResponse = new BleBaseResponse(fromQueue2.f3181a);
                    StringBuilder sb = new StringBuilder();
                    sb.append("((ChangeTemperatureUnitRes) response).isSuccess(): ");
                    ChangeTemperatureUnitRes changeTemperatureUnitRes = (ChangeTemperatureUnitRes) baseResponse;
                    sb.append(changeTemperatureUnitRes.isSuccess());
                    LogHelper.d(str, sb.toString(), moduleNames.getModuleName());
                    if (changeTemperatureUnitRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener2, bleBaseResponse);
                        return;
                    } else {
                        onSettingsError(settingsResultListener2, new BleBaseError("ChangeTemperatureUnitRes setting failed", bleCommand2));
                        return;
                    }
                }
                return;
            case 3:
                onProgressUpdate(new ProgressDataBean(baseResponse.getBaseRequest(), 100, ProgressType.PACKETS));
                BleCommand bleCommand3 = BleCommand.GET_TEMPERATURE_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate = getFromQueuebasedOnDate(bleCommand3, baseResponse.getBaseRequest());
                if (fromQueuebasedOnDate != null) {
                    LogHelper.d(str, "Temperature Request is there in queue", moduleNames.getModuleName());
                    try {
                        int sameCommandCount = getSameCommandCount(bleCommand3);
                        LogHelper.d(str, "Temperature queue size is ++ " + sameCommandCount, moduleNames.getModuleName());
                        TemperatureResponse temperatureResponse = LeonardoFormatter.getTemperatureResponse(((TemperatureDataRes) baseResponse).getTemperatureData());
                        if (sameCommandCount == 0) {
                            temperatureResponse.setComplete(true);
                        } else {
                            temperatureResponse.setComplete(false);
                        }
                        LogHelper.d(str, "Temperature isComplete ++ " + temperatureResponse.isComplete(), moduleNames.getModuleName());
                        BleBaseResponse bleBaseResponse2 = new BleBaseResponse(fromQueuebasedOnDate.f3181a);
                        bleBaseResponse2.setResponseData(temperatureResponse);
                        onDataResponse(bleBaseResponse2, (DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener());
                        LogHelper.d(str, "Temperature Request onDataResponse", moduleNames.getModuleName());
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        LogHelper.e(q, e.getMessage(), e, ModuleNames.BLEABSTRACT.getModuleName());
                        onDataError((DataResultListener) fromQueuebasedOnDate.f3181a.getResponseListener(), new BleBaseError(e.getMessage(), fromQueuebasedOnDate.f3181a.getBleCommand()));
                        return;
                    }
                }
                LogHelper.d(str, "Temperature Request is no in queue", moduleNames.getModuleName());
                return;
            case 4:
                BleCommand bleCommand4 = BleCommand.SET_LIFT_WRIST;
                LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand4, baseResponse);
                if (fromQueue3 != null) {
                    SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue3.f3181a.getResponseListener();
                    if (((LiftWristViewRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener3, new BleBaseResponse(fromQueue3.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener3, new BleBaseError("LiftWrist setting failed", bleCommand4));
                        return;
                    }
                }
                return;
            case 5:
                BleCommand bleCommand5 = BleCommand.SET_DND_MODE;
                LeonardoBleApiImpl.QueueObject fromQueue4 = getFromQueue(bleCommand5, baseResponse);
                if (fromQueue4 != null) {
                    SettingsResultListener settingsResultListener4 = (SettingsResultListener) fromQueue4.f3181a.getResponseListener();
                    if (((SetDNDModeRes) baseResponse).isSuccess()) {
                        onSettingsResponse(settingsResultListener4, new BleBaseResponse(fromQueue4.f3181a));
                        return;
                    } else {
                        onSettingsError(settingsResultListener4, new BleBaseError("DND setting failed", bleCommand5));
                        return;
                    }
                }
                return;
            case 6:
                LeonardoBleApiImpl.QueueObject fromQueue5 = getFromQueue(BleCommand.GET_DND_MODE_SETTINGS, baseResponse);
                if (fromQueue5 == null || !(baseResponse instanceof GetDNDModeSettingsRes)) {
                    return;
                }
                BleBaseResponse bleBaseResponse3 = new BleBaseResponse(fromQueue5.f3181a);
                bleBaseResponse3.setResponseData(((GetDNDModeSettingsRes) baseResponse).getDNDModeSettingsData());
                onDataResponse(bleBaseResponse3, (DataResultListener) fromQueue5.f3181a.getResponseListener());
                return;
            default:
                super.onResponse(baseResponse);
                return;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(ResponseEvent responseEvent) {
        if (AnonymousClass1.b[responseEvent.getType().ordinal()] != 1) {
            super.onResponseEventReceivved(responseEvent);
            return;
        }
        LiveTemperatureRes liveTemperatureRes = (LiveTemperatureRes) responseEvent.getData();
        if (this.o == null || liveTemperatureRes == null || liveTemperatureRes.getLiveTemperatureData() == null) {
            return;
        }
        this.o.setValue(Mapper.a(liveTemperatureRes.getLiveTemperatureData()));
        this.o.postValue(Mapper.a(liveTemperatureRes.getLiveTemperatureData()));
        String str = q;
        LogHelper.d(str, liveTemperatureRes.getLiveTemperatureData().getTemperature() + "", ModuleNames.BLEABSTRACT.getModuleName());
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
    public LiveData<LiveTemperatureData> registerForLiveTemperatureData() {
        if (this.o == null) {
            this.o = new MutableLiveData<>();
        }
        return this.o;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(BleBaseRequest bleBaseRequest, SettingsResultListener settingsResultListener) {
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService == null || leonardoBleCmdService.getConnectionState() != CloveBleState.BleState.CONNECTED) {
            if (bleBaseRequest != null && bleBaseRequest.getBleCommand() != null) {
                onSettingsError(settingsResultListener, new BleBaseError("Band is not connected", bleBaseRequest.getBleCommand()));
            } else {
                onSettingsError(settingsResultListener, new BleBaseError("Band is not connected"));
            }
        } else if (bleBaseRequest != null) {
            bleBaseRequest.setRequId(UUID.randomUUID().toString());
            boolean z = true;
            if (bleBaseRequest instanceof TemperatureTimeIntervalRequest) {
                bleBaseRequest.setResponseListener(settingsResultListener);
                bleBaseRequest.setBleCommand(BleCommand.SET_TEMPERATURE_TIME_INTERVAL);
                addToQueue(bleBaseRequest);
                TemperatureTimeIntervalRequest temperatureTimeIntervalRequest = (TemperatureTimeIntervalRequest) bleBaseRequest;
                if (temperatureTimeIntervalRequest.getOpen() != 1 && temperatureTimeIntervalRequest.getOpen() != 2) {
                    z = false;
                }
                TimeIntervalForAutomaticTemperatureReq timeIntervalForAutomaticTemperatureReq = new TimeIntervalForAutomaticTemperatureReq(temperatureTimeIntervalRequest.getTimeInterval(), z);
                BlePreferenceManager.savePreference(LeonardoBleApiImpl.context, CommonPreference.TEMPERATURE_INTERVAL_VALUE, Integer.valueOf(timeIntervalForAutomaticTemperatureReq.getTimeInterval()));
                timeIntervalForAutomaticTemperatureReq.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(timeIntervalForAutomaticTemperatureReq, this);
            } else if (bleBaseRequest instanceof SetTemperatureUnitRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_TEMPERATURE_UNIT);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                ChangeTemperatureUnitReq build = new ChangeTemperatureUnitReq.Builder().setFahrenheit(!((SetTemperatureUnitRequest) bleBaseRequest).isTemperatureInCelsius()).build();
                build.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
            } else if (bleBaseRequest instanceof LiveTemperatureControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_TEMPERATURE_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                TemperatureMeasurementReq build2 = new TemperatureMeasurementReq.Builder(((LiveTemperatureControlRequest) bleBaseRequest).isEnabled()).build();
                build2.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build2, this);
            } else if (bleBaseRequest instanceof LiveRawPPGControlRequest) {
                bleBaseRequest.setBleCommand(BleCommand.RAW_PPG_CONTROL);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                V7PPGControlReq build3 = new V7PPGControlReq.Builder().setEnabled(((LiveRawPPGControlRequest) bleBaseRequest).isEnabled()).build();
                build3.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build3, this);
            } else if (bleBaseRequest instanceof SetLiftWristRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_LIFT_WRIST);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                LiftWristViewReq build4 = new LiftWristViewReq.Builder(((SetLiftWristRequest) bleBaseRequest).isLiftWristEnabled()).build();
                build4.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build4, this);
            } else if (bleBaseRequest instanceof SetDNDModeRequest) {
                bleBaseRequest.setBleCommand(BleCommand.SET_DND_MODE);
                bleBaseRequest.setResponseListener(settingsResultListener);
                addToQueue(bleBaseRequest);
                SetDNDModeRequest setDNDModeRequest = (SetDNDModeRequest) bleBaseRequest;
                DNDModeReq build5 = new DNDModeReq.Builder(setDNDModeRequest.isDNDEnabled(), setDNDModeRequest.getStartHour(), setDNDModeRequest.getStartMin(), setDNDModeRequest.getEndHour(), setDNDModeRequest.getEndMin()).setNotificationEnabled(setDNDModeRequest.isNotificationEnabled()).setLiftWristEnabled(setDNDModeRequest.isLiftWristEnabled()).build();
                build5.setReqId(bleBaseRequest.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build5, this);
            } else {
                super.setUserSettings(bleBaseRequest, settingsResultListener);
            }
        }
    }
}
