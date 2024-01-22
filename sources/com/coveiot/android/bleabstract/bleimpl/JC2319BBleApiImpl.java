package com.coveiot.android.bleabstract.bleimpl;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl;
import com.coveiot.android.bleabstract.error.Error;
import com.coveiot.android.bleabstract.formatter.LeonardoFormatter;
import com.coveiot.android.bleabstract.listeners.BaseListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BatterySaverConfigAbstract;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.SilentModeConfigAbstract;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.CustomMessageRequest;
import com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest;
import com.coveiot.android.bleabstract.request.GetBatterySaverConfigRequest;
import com.coveiot.android.bleabstract.request.GetCalorieDataRequest;
import com.coveiot.android.bleabstract.request.GetDistanceDataRequest;
import com.coveiot.android.bleabstract.request.GetLiftWristSettingsRequest;
import com.coveiot.android.bleabstract.request.GetSensAIActivityConfigRequest;
import com.coveiot.android.bleabstract.request.GetSensAISummaryDetailsRequest;
import com.coveiot.android.bleabstract.request.GetSensAISummaryRequest;
import com.coveiot.android.bleabstract.request.GetSilentModeConfigRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetCustomRemindersRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataRequest;
import com.coveiot.android.bleabstract.request.SetSensAIActivityConfigRequest;
import com.coveiot.android.bleabstract.request.SportsNotificationRequest;
import com.coveiot.android.bleabstract.request.TodaysFitnessDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.GetCalorieDataResponse;
import com.coveiot.android.bleabstract.response.GetDistanceDataResponse;
import com.coveiot.android.bleabstract.response.GetLiftWristResponse;
import com.coveiot.android.bleabstract.response.GetSensAIActivityConfigResponse;
import com.coveiot.android.bleabstract.response.GetSensAISummaryDataResponse;
import com.coveiot.android.bleabstract.response.GetSensAISummaryDetailsResponse;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsData;
import com.coveiot.android.bleabstract.response.TodaysStepsResponse;
import com.coveiot.android.bleabstract.services.LeonardoBleCmdService;
import com.coveiot.sdk.ble.CloveBleState;
import com.coveiot.sdk.ble.api.model.BatterySaverConfig;
import com.coveiot.sdk.ble.api.model.BleGetSensAIActivityConfig;
import com.coveiot.sdk.ble.api.model.BleGetSensAISummaryDetailData;
import com.coveiot.sdk.ble.api.model.DailyCalorieData;
import com.coveiot.sdk.ble.api.model.DailyDistanceData;
import com.coveiot.sdk.ble.api.model.LiftWristViewData;
import com.coveiot.sdk.ble.api.model.ProgressDataBean;
import com.coveiot.sdk.ble.api.model.SilentModeConfig;
import com.coveiot.sdk.ble.api.model.TodaysWalkData;
import com.coveiot.sdk.ble.api.request.CustomMessageReq;
import com.coveiot.sdk.ble.api.request.DeleteImageReq;
import com.coveiot.sdk.ble.api.request.GetBatterySaverConfigReq;
import com.coveiot.sdk.ble.api.request.GetCalorieDataReq;
import com.coveiot.sdk.ble.api.request.GetDistanceDataReq;
import com.coveiot.sdk.ble.api.request.GetLiftWristViewReq;
import com.coveiot.sdk.ble.api.request.GetSensAIActivityConfigReq;
import com.coveiot.sdk.ble.api.request.GetSensAISummaryDataReq;
import com.coveiot.sdk.ble.api.request.GetSensAISummaryDetailsDataReq;
import com.coveiot.sdk.ble.api.request.GetSilentModeConfigReq;
import com.coveiot.sdk.ble.api.request.LiftWristViewReq;
import com.coveiot.sdk.ble.api.request.MessageAlertSwitchesReq;
import com.coveiot.sdk.ble.api.request.SendImageReq;
import com.coveiot.sdk.ble.api.request.SensAISetActivityConfigReq;
import com.coveiot.sdk.ble.api.request.SetCustomReminderReq;
import com.coveiot.sdk.ble.api.request.SetMediaInfoReq;
import com.coveiot.sdk.ble.api.request.SportsNotificationReq;
import com.coveiot.sdk.ble.api.request.TodaysFitnessDataReq;
import com.coveiot.sdk.ble.api.response.BaseResponse;
import com.coveiot.sdk.ble.api.response.CalorieDataRes;
import com.coveiot.sdk.ble.api.response.DeleteImageRes;
import com.coveiot.sdk.ble.api.response.DistanceDataRes;
import com.coveiot.sdk.ble.api.response.GetBatterySaverConfigRes;
import com.coveiot.sdk.ble.api.response.GetLiftWristViewRes;
import com.coveiot.sdk.ble.api.response.GetSensAIActivityConfigRes;
import com.coveiot.sdk.ble.api.response.GetSensAISummaryDataRes;
import com.coveiot.sdk.ble.api.response.GetSensAISummaryDetailsDataRes;
import com.coveiot.sdk.ble.api.response.GetSilentModeConfigRes;
import com.coveiot.sdk.ble.api.response.LiftWristViewRes;
import com.coveiot.sdk.ble.api.response.SetCustomReminderRes;
import com.coveiot.sdk.ble.api.response.SetSensAIActivityConfigRes;
import com.coveiot.sdk.ble.api.response.TodaysFitnessDataRes;
import com.coveiot.sdk.ble.events.ResponseEvent;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public class JC2319BBleApiImpl extends CA6BTABleApiImpl {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String G = JC2319BBleApiImpl.class.getSimpleName();
    @Nullable
    public static JC2319BBleApiImpl H;
    public final String F = JC2319BBleApiImpl.class.getSimpleName();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final JC2319BBleApiImpl getInstance(@NotNull Context ctxt) {
            Intrinsics.checkNotNullParameter(ctxt, "ctxt");
            if (JC2319BBleApiImpl.H == null) {
                LeonardoBleApiImpl.context = ctxt.getApplicationContext();
                JC2319BBleApiImpl.H = new JC2319BBleApiImpl();
            }
            LeonardoBleApiImpl.intent = new Intent(LeonardoBleApiImpl.context, LeonardoBleCmdService.class);
            if (!LeonardoBleApiImpl.checkIfServiceIsRunning()) {
                LogHelper.d(JC2319BBleApiImpl.G, "service is not running ++ ", ModuleNames.BLEABSTRACT.getModuleName());
                try {
                    LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
                    if (Build.VERSION.SDK_INT >= 26) {
                        LeonardoBleApiImpl.context.startForegroundService(LeonardoBleApiImpl.intent);
                    } else {
                        LeonardoBleApiImpl.context.startService(LeonardoBleApiImpl.intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Context context = LeonardoBleApiImpl.context;
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    BleApiUtils.checkExceptionAndShowNotification(e, context);
                }
            } else if (LeonardoBleApiImpl.bleService == null) {
                LeonardoBleApiImpl.context.bindService(LeonardoBleApiImpl.intent, LeonardoBleApiImpl.serviceConnection, 1);
            }
            return JC2319BBleApiImpl.H;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CommandType.values().length];
            try {
                iArr[CommandType.DELETE_IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CommandType.GET_TODAY_FITNESS_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CommandType.SET_CUSTOM_REMINDERS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CommandType.GET_BATTERY_SAVER_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CommandType.GET_SILENT_MODE_CONFIG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CommandType.GET_LIFT_WRIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CommandType.SET_LIFT_WRIST.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CommandType.SET_SENS_AI_ACTIVITY_CONFIG.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[CommandType.GET_SENS_AI_ACTIVITY_CONFIG.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[CommandType.GET_SENS_AI_SUMMARY_DATA.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[CommandType.GET_DISTANCE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[CommandType.GET_CALORIE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[CommandType.GET_SENS_AI_SUMMARY_DETAILS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void a(SendImageReq sendImageReq1, JC2319BBleApiImpl this$0) {
        Intrinsics.checkNotNullParameter(sendImageReq1, "$sendImageReq1");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LeonardoBleApiImpl.bleService.sendRequest(sendImageReq1, this$0);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void getData(@NotNull BleBaseRequest request, @NotNull DataResultListener listener) {
        long j;
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            if (request instanceof SendImageRequest) {
                SendImageRequest sendImageRequest = (SendImageRequest) request;
                SendImageReq sendImageReq = new SendImageReq(null, sendImageRequest.getImageId(), sendImageRequest.getImageFile(), sendImageRequest.getCompression(), sendImageRequest.getTransparentChannel(), sendImageRequest.getxCoordinate(), sendImageRequest.getyCoordinate(), sendImageRequest.getHeight(), sendImageRequest.getWidth(), DevicePlatformEnum.Realtek);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SEND_IMAGE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sendImageReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sendImageReq, this);
                return;
            } else if (request instanceof TodaysFitnessDataRequest) {
                TodaysFitnessDataReq build = new TodaysFitnessDataReq.Builder().build();
                build.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_TODAY_FITNESS_VALUE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
                return;
            } else if (request instanceof SetCustomRemindersRequest) {
                SetCustomReminderReq customReminderReq = LeonardoFormatter.getCustomReminderReq((SetCustomRemindersRequest) request);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_CUSTOM_REMINDERS);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                customReminderReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(customReminderReq, this);
                return;
            } else if (request instanceof GetBatterySaverConfigRequest) {
                GetBatterySaverConfigReq getBatterySaverConfigReq = new GetBatterySaverConfigReq(null);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_BATTERY_SAVER_INFO);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                getBatterySaverConfigReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getBatterySaverConfigReq, this);
                return;
            } else if (request instanceof GetSilentModeConfigRequest) {
                GetSilentModeConfigReq getSilentModeConfigReq = new GetSilentModeConfigReq(null);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_SILENT_MODE_CONFIG);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                getSilentModeConfigReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getSilentModeConfigReq, this);
                return;
            } else if (request instanceof GetLiftWristSettingsRequest) {
                GetLiftWristViewReq getLiftWristViewReq = new GetLiftWristViewReq(null);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_LIFT_WRIST);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                getLiftWristViewReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getLiftWristViewReq, this);
                return;
            } else if (request instanceof CustomWatchFaceBackgroundChangeRequest) {
                request.setBleCommand(BleCommand.CHANGE_WATCH_FACE_BG);
                request.setResponseListener(listener);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                DeleteImageReq deleteImageReq = new DeleteImageReq(null, ((CustomWatchFaceBackgroundChangeRequest) request).getImageId());
                deleteImageReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(deleteImageReq, this);
                return;
            } else if (request instanceof SportsNotificationRequest) {
                SportsNotificationReq sportsNotificationReq = new SportsNotificationReq(null, LeonardoFormatter.getBleDynamicFieldData(((SportsNotificationRequest) request).getDynamicSportsFieldList()));
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SPORTS_NOTIFICATION);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                sportsNotificationReq.setDevicePlatformEnum(DevicePlatformEnum.Realtek);
                sportsNotificationReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sportsNotificationReq, this);
                return;
            } else if (request instanceof CustomMessageRequest) {
                CustomMessageRequest customMessageRequest = (CustomMessageRequest) request;
                CustomMessageReq customMessageReq = new CustomMessageReq(null, LeonardoFormatter.getBleDynamicFieldData(customMessageRequest.getDynamicSportsFieldList()), customMessageRequest.getMessageType(), (short) (customMessageRequest.getVibrationDuration() / 100), (short) (customMessageRequest.getDisplayTime() / 100), customMessageRequest.getEnterDirection(), customMessageRequest.getExitDirection());
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.CUSTOM_MESSAGE);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                customMessageReq.setReqId(request.getRequId());
                customMessageReq.setDevicePlatformEnum(DevicePlatformEnum.Realtek);
                LeonardoBleApiImpl.bleService.sendRequest(customMessageReq, this);
                return;
            } else if (request instanceof GetSensAIActivityConfigRequest) {
                GetSensAIActivityConfigReq build2 = new GetSensAIActivityConfigReq.Builder().build();
                build2.setResponseListener(this);
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.GET_SENS_AI_ACTIVITY_CONFIG);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                build2.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build2, this);
                return;
            } else if (request instanceof GetSensAISummaryDetailsRequest) {
                GetSensAISummaryDetailsRequest getSensAISummaryDetailsRequest = (GetSensAISummaryDetailsRequest) request;
                String sessionID = getSensAISummaryDetailsRequest.getSessionID();
                Long valueOf = sessionID != null ? Long.valueOf(Long.parseLong(sessionID)) : null;
                Intrinsics.checkNotNull(valueOf);
                GetSensAISummaryDetailsDataReq getSensAISummaryDetailsDataReq = new GetSensAISummaryDetailsDataReq(null, valueOf.longValue());
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                GetSensAISummaryDetailsRequest getSensAISummaryDetailsRequest2 = new GetSensAISummaryDetailsRequest();
                getSensAISummaryDetailsRequest2.setSessionID(getSensAISummaryDetailsRequest.getSessionID());
                getSensAISummaryDetailsRequest2.setBleCommand(BleCommand.GET_SENS_AI_SUMMARY_DETAILS);
                getSensAISummaryDetailsRequest2.setResponseListener(listener);
                getSensAISummaryDetailsRequest2.setRequId(uuid);
                addToQueue(getSensAISummaryDetailsRequest2);
                getSensAISummaryDetailsDataReq.setReqId(getSensAISummaryDetailsRequest2.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(getSensAISummaryDetailsDataReq, this);
                return;
            } else if (request instanceof GetSensAISummaryRequest) {
                GetSensAISummaryRequest getSensAISummaryRequest = (GetSensAISummaryRequest) request;
                long findDateDifference = BleApiUtils.findDateDifference(getSensAISummaryRequest.getStartDate(), getSensAISummaryRequest.getEndDate());
                Date startDate = getSensAISummaryRequest.getStartDate();
                if (findDateDifference > 6) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    calendar.setTimeInMillis(calendar.getTimeInMillis() + (((long) TimeConstants.DAY) * (findDateDifference - 6)));
                    startDate = calendar.getTime();
                    j = 6;
                } else {
                    j = findDateDifference;
                }
                BleCommand bleCommand = BleCommand.GET_SENS_AI_SUMMARY_DATA;
                if (getSameCommandCount(bleCommand) > 0) {
                    removeSimilarCommand(bleCommand);
                }
                for (int i = 0; i <= j; i++) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(startDate);
                    calendar2.setTimeInMillis(calendar2.getTimeInMillis() + (i * TimeConstants.DAY));
                    Date time = calendar2.getTime();
                    GetSensAISummaryDataReq getSensAISummaryDataReq = new GetSensAISummaryDataReq(null, time);
                    String uuid2 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(uuid2, "randomUUID().toString()");
                    GetSensAISummaryRequest getSensAISummaryRequest2 = new GetSensAISummaryRequest();
                    getSensAISummaryRequest2.setStartDate(time);
                    getSensAISummaryRequest2.setEndDate(time);
                    getSensAISummaryRequest2.setBleCommand(BleCommand.GET_SENS_AI_SUMMARY_DATA);
                    getSensAISummaryRequest2.setResponseListener(listener);
                    getSensAISummaryRequest2.setRequId(uuid2);
                    addToQueue(getSensAISummaryRequest2);
                    getSensAISummaryDataReq.setReqId(getSensAISummaryRequest2.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(getSensAISummaryDataReq, this);
                }
                return;
            } else if (request instanceof GetDistanceDataRequest) {
                GetDistanceDataRequest getDistanceDataRequest = (GetDistanceDataRequest) request;
                long findDateDifference2 = BleApiUtils.findDateDifference(getDistanceDataRequest.getStartDate(), getDistanceDataRequest.getEndDate());
                Date startDate2 = getDistanceDataRequest.getStartDate();
                if (findDateDifference2 > 6) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(startDate2);
                    calendar3.setTimeInMillis(calendar3.getTimeInMillis() + (((long) TimeConstants.DAY) * (findDateDifference2 - 6)));
                    startDate2 = calendar3.getTime();
                    findDateDifference2 = 6;
                }
                BleCommand bleCommand2 = BleCommand.GET_DISTANCE;
                if (getSameCommandCount(bleCommand2) > 0) {
                    removeSimilarCommand(bleCommand2);
                }
                for (int i2 = 0; i2 <= findDateDifference2; i2++) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTime(startDate2);
                    calendar4.setTimeInMillis(calendar4.getTimeInMillis() + (i2 * TimeConstants.DAY));
                    Date time2 = calendar4.getTime();
                    GetDistanceDataReq getDistanceDataReq = new GetDistanceDataReq(null, time2);
                    String uuid3 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(uuid3, "randomUUID().toString()");
                    GetDistanceDataRequest getDistanceDataRequest2 = new GetDistanceDataRequest();
                    getDistanceDataRequest2.setStartDate(time2);
                    getDistanceDataRequest2.setEndDate(time2);
                    getDistanceDataRequest2.setBleCommand(BleCommand.GET_DISTANCE);
                    getDistanceDataRequest2.setResponseListener(listener);
                    getDistanceDataRequest2.setRequId(uuid3);
                    addToQueue(getDistanceDataRequest2);
                    getDistanceDataReq.setReqId(getDistanceDataRequest2.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(getDistanceDataReq, this);
                }
                return;
            } else if (request instanceof GetCalorieDataRequest) {
                GetCalorieDataRequest getCalorieDataRequest = (GetCalorieDataRequest) request;
                long findDateDifference3 = BleApiUtils.findDateDifference(getCalorieDataRequest.getStartDate(), getCalorieDataRequest.getEndDate());
                Date startDate3 = getCalorieDataRequest.getStartDate();
                if (findDateDifference3 > 6) {
                    Calendar calendar5 = Calendar.getInstance();
                    calendar5.setTime(startDate3);
                    calendar5.setTimeInMillis(calendar5.getTimeInMillis() + (((long) TimeConstants.DAY) * (findDateDifference3 - 6)));
                    startDate3 = calendar5.getTime();
                    findDateDifference3 = 6;
                }
                BleCommand bleCommand3 = BleCommand.GET_CALORIE;
                if (getSameCommandCount(bleCommand3) > 0) {
                    removeSimilarCommand(bleCommand3);
                }
                for (int i3 = 0; i3 <= findDateDifference3; i3++) {
                    Calendar calendar6 = Calendar.getInstance();
                    calendar6.setTime(startDate3);
                    calendar6.setTimeInMillis(calendar6.getTimeInMillis() + (i3 * TimeConstants.DAY));
                    Date time3 = calendar6.getTime();
                    GetCalorieDataReq getCalorieDataReq = new GetCalorieDataReq(null, time3);
                    String uuid4 = UUID.randomUUID().toString();
                    Intrinsics.checkNotNullExpressionValue(uuid4, "randomUUID().toString()");
                    GetCalorieDataRequest getCalorieDataRequest2 = new GetCalorieDataRequest();
                    getCalorieDataRequest2.setStartDate(time3);
                    getCalorieDataRequest2.setEndDate(time3);
                    getCalorieDataRequest2.setBleCommand(BleCommand.GET_CALORIE);
                    getCalorieDataRequest2.setResponseListener(listener);
                    getCalorieDataRequest2.setRequId(uuid4);
                    addToQueue(getCalorieDataRequest2);
                    getCalorieDataReq.setReqId(getCalorieDataRequest2.getRequId());
                    LeonardoBleApiImpl.bleService.sendRequest(getCalorieDataReq, this);
                }
                return;
            } else {
                super.getData(request, listener);
                return;
            }
        }
        listener.onDataError(new BleBaseError("Watch not connected", request.getBleCommand()));
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @NotNull
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
        deviceSupportedFeatures.setScheduledDndSupported(true);
        deviceSupportedFeatures.setOneClickToConnectSupported(true);
        deviceSupportedFeatures.setMaxDaysOfSpo2DataOnBand(7);
        deviceSupportedFeatures.setMaxDaysOfRawPPGDataOnBand(1);
        deviceSupportedFeatures.setStepsSupported(true);
        deviceSupportedFeatures.setSleepSupported(true);
        deviceSupportedFeatures.setREMSupportedInSleep(true);
        deviceSupportedFeatures.setRunSupported(false);
        deviceSupportedFeatures.setHeartRateSupported(true);
        deviceSupportedFeatures.setBpSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setTemparatureHistorySupported(false);
        deviceSupportedFeatures.setRrSupported(false);
        deviceSupportedFeatures.setEcgSupported(false);
        deviceSupportedFeatures.setSwimmingSupported(false);
        deviceSupportedFeatures.setCyclingSupported(false);
        deviceSupportedFeatures.setShouldKeepDeviceConnectedAlways(true);
        deviceSupportedFeatures.setSportModeSupportedFromApp(false);
        deviceSupportedFeatures.setGpsSupported(false);
        deviceSupportedFeatures.setCalendarSyncSupported(true);
        deviceSupportedFeatures.setCallNotificationSupported(true);
        deviceSupportedFeatures.setSmsSupported(true);
        deviceSupportedFeatures.setMessageReadSupported(true);
        deviceSupportedFeatures.setSocialNotificationSupported(true);
        deviceSupportedFeatures.setSosSupported(false);
        deviceSupportedFeatures.setSedentaryReminderSupported(true);
        deviceSupportedFeatures.setSedentaryAlertHistorySupported(true);
        deviceSupportedFeatures.setVibrationAlarmSupported(true);
        deviceSupportedFeatures.setBandDisplaySupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setPhoneFinderSupported(true);
        deviceSupportedFeatures.setPersonalInfoSupported(true);
        deviceSupportedFeatures.setStepGoalSupported(true);
        deviceSupportedFeatures.setPhoneTypeCommandSupported(true);
        deviceSupportedFeatures.setTimeFormartCommandSupported(true);
        deviceSupportedFeatures.setTitleSupportedInNotification(true);
        deviceSupportedFeatures.setTimeFormatSettingsSupported(true);
        deviceSupportedFeatures.setProbeFeatureSupported(true);
        deviceSupportedFeatures.setManualSpo2SupportedOnBand(false);
        deviceSupportedFeatures.setBPCalibrationSupported(BleApiUtils.getBleEnableBpV7());
        deviceSupportedFeatures.setAutoHrSettingsSupported(true);
        deviceSupportedFeatures.setAutoTemperatureSettingsSupported(false);
        deviceSupportedFeatures.setDistanceUnitSettingsSupported(true);
        deviceSupportedFeatures.setMusicMetaDataChangeFromAppSupported(true);
        deviceSupportedFeatures.setTemperatureUnitSettingsSupported(false);
        deviceSupportedFeatures.setAppSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setBandSocialDistanceFeatureSupported(false);
        deviceSupportedFeatures.setSportsModeHistorySupported(true);
        deviceSupportedFeatures.setLiftWristToViewSettingsSupported(true);
        deviceSupportedFeatures.setMaxCharSupportedInNotification(200);
        deviceSupportedFeatures.setRawPPGHistaoryDataSupported(true);
        deviceSupportedFeatures.setPeriodicSpO2Supported(true);
        deviceSupportedFeatures.setMusicPlaybackStateChangeFromAppSupported(true);
        deviceSupportedFeatures.setCameraFeatureSupported(true);
        deviceSupportedFeatures.setSleepTargetSupported(true);
        deviceSupportedFeatures.set1kActivitySupported(false);
        deviceSupportedFeatures.setFemaleWellnessSupported(true);
        deviceSupportedFeatures.setQuickReplySupported(true);
        deviceSupportedFeatures.setWeatherSupportedInBand(true);
        deviceSupportedFeatures.setCustomMessageSupported(true);
        deviceSupportedFeatures.setKaHaRealtekChip(true);
        deviceSupportedFeatures.setWeatherEnableCommandSupported(true);
        deviceSupportedFeatures.setKahaBTCallSupported(true);
        deviceSupportedFeatures.setNotificationConfigurationSupported(true);
        deviceSupportedFeatures.setMaxContactsInOneRequest(20);
        deviceSupportedFeatures.setContactSyncSupported(true);
        deviceSupportedFeatures.setNudgeSupported(true);
        deviceSupportedFeatures.setBTCallingSupported(true);
        deviceSupportedFeatures.setFindMyBandSupported(true);
        deviceSupportedFeatures.setCustomRemindersSupported(true);
        deviceSupportedFeatures.setBandVolumeControlSupported(true);
        deviceSupportedFeatures.setDiagnosticTestSupported(true);
        deviceSupportedFeatures.setPairingFlowTypeCommandSupported(true);
        Context context = LeonardoBleApiImpl.context;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        String macAddress = getMacAddress();
        Intrinsics.checkNotNullExpressionValue(macAddress, "getMacAddress()");
        return BleApiUtils.updateDeviceSupportedFeatureBasedOnFWAndAppCapability(context, macAddress, deviceSupportedFeatures);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public boolean isScanResultReturnedImmediately() {
        return true;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onConnectionStateChanged(@NotNull CloveBleState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        super.onConnectionStateChanged(state);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onError(@NotNull Error error) {
        Intrinsics.checkNotNullParameter(error, "error");
        super.onError(error);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
        Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
        super.onProgressUpdate(progressDataBean);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.sdk.ble.api.ResponseListener
    public void onResponse(@NotNull BaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        CommandType activityType = response.getActivityType();
        switch (activityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()]) {
            case 1:
                BleCommand bleCommand = BleCommand.DELETE_IMAGE;
                LeonardoBleApiImpl.QueueObject fromQueue = getFromQueue(bleCommand, response);
                if (fromQueue != null) {
                    BaseListener responseListener = fromQueue.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.SettingsResultListener");
                    SettingsResultListener settingsResultListener = (SettingsResultListener) responseListener;
                    LogHelper.d(this.F, "DeleteImageRes is not empty");
                    if (((DeleteImageRes) response).isSuccess()) {
                        BleBaseRequest bleBaseRequest = fromQueue.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest, "deleteImageReq.baseRequest");
                        onSettingsResponse(settingsResultListener, new BleBaseResponse(bleBaseRequest));
                        return;
                    }
                    onSettingsError(settingsResultListener, new BleBaseError("DeleteImageRes setting failed", bleCommand));
                    return;
                }
                LeonardoBleApiImpl.QueueObject fromQueue2 = getFromQueue(BleCommand.CHANGE_WATCH_FACE_BG, response);
                if (fromQueue2 != null) {
                    if (fromQueue2.f3181a instanceof CustomWatchFaceBackgroundChangeRequest) {
                        LogHelper.d(this.F, "CustomWatchFaceBackgroundChangeRequest is non empty");
                        BleBaseRequest bleBaseRequest2 = fromQueue2.f3181a;
                        Intrinsics.checkNotNull(bleBaseRequest2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.request.CustomWatchFaceBackgroundChangeRequest");
                        CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = (CustomWatchFaceBackgroundChangeRequest) bleBaseRequest2;
                        final SendImageReq sendImageReq = new SendImageReq(null, customWatchFaceBackgroundChangeRequest.getImageId(), customWatchFaceBackgroundChangeRequest.getImageFile(), 0, 0, 0, 0, customWatchFaceBackgroundChangeRequest.getHeight(), customWatchFaceBackgroundChangeRequest.getWidth(), DevicePlatformEnum.Realtek);
                        addToQueue(customWatchFaceBackgroundChangeRequest);
                        customWatchFaceBackgroundChangeRequest.setResponseListener(customWatchFaceBackgroundChangeRequest.getResponseListener());
                        sendImageReq.setReqId(customWatchFaceBackgroundChangeRequest.getRequId());
                        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.l3
                            @Override // java.lang.Runnable
                            public final void run() {
                                JC2319BBleApiImpl.a(SendImageReq.this, this);
                            }
                        }, 500L);
                        return;
                    }
                    LogHelper.d(this.F, "CustomWatchFaceBackgroundChangeRequest is ins");
                    return;
                }
                LogHelper.d(this.F, "CustomWatchFaceBackgroundChangeRequest is empty");
                return;
            case 2:
                BleCommand bleCommand2 = BleCommand.GET_TODAY_FITNESS_VALUE;
                LeonardoBleApiImpl.QueueObject fromQueue3 = getFromQueue(bleCommand2, response);
                Intrinsics.checkNotNull(fromQueue3);
                BleBaseRequest bleBaseRequest3 = fromQueue3.f3181a;
                Intrinsics.checkNotNullExpressionValue(bleBaseRequest3, "todayFitnessData!!.baseRequest");
                BleBaseResponse bleBaseResponse = new BleBaseResponse(bleBaseRequest3);
                DataResultListener dataResultListener = (DataResultListener) fromQueue3.f3181a.getResponseListener();
                try {
                    if (((TodaysFitnessDataRes) response).isSuccess()) {
                        TodaysStepsResponse todaysStepsResponse = new TodaysStepsResponse();
                        TodaysWalkData stepsData = ((TodaysFitnessDataRes) response).getStepsData();
                        TodaysStepsData todaysStepsData = new TodaysStepsData();
                        todaysStepsData.setTotalCalories(stepsData.getTotalCalories());
                        todaysStepsData.setTotalSteps(stepsData.getTotalSteps());
                        todaysStepsData.setTotalDistance(stepsData.getTotalDistance());
                        todaysStepsResponse.setTodaysStepsData(todaysStepsData);
                        if (this.liveStepsDataMutableLiveData != null) {
                            final LiveStepsData liveStepsData = new LiveStepsData();
                            liveStepsData.setLiveSteps(stepsData.getTotalSteps());
                            liveStepsData.setMeters(stepsData.getTotalDistance());
                            liveStepsData.setCalories(stepsData.getTotalCalories());
                            this.uiHandler.post(new Runnable() { // from class: com.coveiot.android.bleabstract.bleimpl.k3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    JC2319BBleApiImpl.a(JC2319BBleApiImpl.this, liveStepsData);
                                }
                            });
                        }
                        bleBaseResponse.setResponseData(todaysStepsResponse);
                        onDataResponse(bleBaseResponse, dataResultListener);
                        return;
                    }
                    onDataError(dataResultListener, new BleBaseError("Get Today's Steps Data failed", bleCommand2));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    String message = e.getMessage();
                    Intrinsics.checkNotNull(message);
                    onDataError(dataResultListener, new BleBaseError(message, BleCommand.GET_TODAYS_TOTAL_WALK_DATA));
                    return;
                }
            case 3:
                BleCommand bleCommand3 = BleCommand.SET_CUSTOM_REMINDERS;
                LeonardoBleApiImpl.QueueObject fromQueue4 = getFromQueue(bleCommand3, response);
                if (fromQueue4 != null) {
                    DataResultListener dataResultListener2 = (DataResultListener) fromQueue4.f3181a.getResponseListener();
                    if (((SetCustomReminderRes) response).isSuccess()) {
                        BleBaseRequest bleBaseRequest4 = fromQueue4.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest4, "setCustomReminder.baseRequest");
                        onDataResponse(new BleBaseResponse(bleBaseRequest4), dataResultListener2);
                        return;
                    }
                    onDataError(dataResultListener2, new BleBaseError("SetCustomReminderRes setting failed", bleCommand3));
                    return;
                }
                return;
            case 4:
                BleCommand bleCommand4 = BleCommand.GET_BATTERY_SAVER_INFO;
                LeonardoBleApiImpl.QueueObject fromQueue5 = getFromQueue(bleCommand4, response);
                if (fromQueue5 != null) {
                    BaseListener responseListener2 = fromQueue5.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener3 = (DataResultListener) responseListener2;
                    if (response instanceof GetBatterySaverConfigRes) {
                        BatterySaverConfig batterySaverConfig = ((GetBatterySaverConfigRes) response).getBatterySaverConfig();
                        BleBaseRequest bleBaseRequest5 = fromQueue5.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest5, "getBatterySaverConfigRequest.baseRequest");
                        BleBaseResponse bleBaseResponse2 = new BleBaseResponse(bleBaseRequest5);
                        bleBaseResponse2.setResponseData(new BatterySaverConfigAbstract(batterySaverConfig.isEnabled(), batterySaverConfig.getMode(), batterySaverConfig.getAutoStart(), batterySaverConfig.getIsActive()));
                        bleBaseResponse2.setCompleted(true);
                        onDataResponse(bleBaseResponse2, dataResultListener3);
                        return;
                    }
                    onDataError(dataResultListener3, new BleBaseError("GetBatterySaverConfigRes response failed", bleCommand4));
                    return;
                }
                return;
            case 5:
                BleCommand bleCommand5 = BleCommand.GET_SILENT_MODE_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue6 = getFromQueue(bleCommand5, response);
                if (fromQueue6 != null) {
                    BaseListener responseListener3 = fromQueue6.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener4 = (DataResultListener) responseListener3;
                    if (response instanceof GetSilentModeConfigRes) {
                        SilentModeConfig silentModeConfig = ((GetSilentModeConfigRes) response).getSilentModeConfig();
                        BleBaseRequest bleBaseRequest6 = fromQueue6.f3181a;
                        Intrinsics.checkNotNullExpressionValue(bleBaseRequest6, "getSilentModeConfigRequest.baseRequest");
                        BleBaseResponse bleBaseResponse3 = new BleBaseResponse(bleBaseRequest6);
                        bleBaseResponse3.setResponseData(new SilentModeConfigAbstract(silentModeConfig.isSilentModeEnabled(), silentModeConfig.isVibrationEnabled()));
                        bleBaseResponse3.setCompleted(true);
                        onDataResponse(bleBaseResponse3, dataResultListener4);
                        return;
                    }
                    onDataError(dataResultListener4, new BleBaseError("GetSilentModeConfigRes response failed", bleCommand5));
                    return;
                }
                return;
            case 6:
                BleCommand bleCommand6 = BleCommand.GET_LIFT_WRIST;
                LeonardoBleApiImpl.QueueObject fromQueue7 = getFromQueue(bleCommand6, response);
                if (fromQueue7 != null) {
                    BaseListener responseListener4 = fromQueue7.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener4, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener5 = (DataResultListener) responseListener4;
                    if (response instanceof GetLiftWristViewRes) {
                        LiftWristViewData liftWristViewData = ((GetLiftWristViewRes) response).getLiftWristViewData();
                        if (liftWristViewData != null) {
                            GetLiftWristResponse getLiftWristResponse = new GetLiftWristResponse();
                            getLiftWristResponse.setLiftWristEnabled(liftWristViewData.isEnabled());
                            getLiftWristResponse.setStartHour(liftWristViewData.getStartHour());
                            getLiftWristResponse.setStartMinute(liftWristViewData.getStartMin());
                            getLiftWristResponse.setEndHour(liftWristViewData.getEndHour());
                            getLiftWristResponse.setEndMinute(liftWristViewData.getEndMin());
                            BleBaseRequest bleBaseRequest7 = fromQueue7.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest7, "getLiftWristViewRequest.baseRequest");
                            BleBaseResponse bleBaseResponse4 = new BleBaseResponse(bleBaseRequest7);
                            bleBaseResponse4.setResponseData(getLiftWristResponse);
                            bleBaseResponse4.setCompleted(true);
                            onDataResponse(bleBaseResponse4, dataResultListener5);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener5, new BleBaseError("GetLiftWristViewReq failed", bleCommand6));
                    return;
                }
                return;
            case 7:
                LeonardoBleApiImpl.QueueObject fromQueue8 = getFromQueue(BleCommand.SET_LIFT_WRIST, response);
                if (fromQueue8 != null) {
                    BaseListener responseListener5 = fromQueue8.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener5, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.SettingsResultListener");
                    SettingsResultListener settingsResultListener2 = (SettingsResultListener) responseListener5;
                    if (response instanceof LiftWristViewRes) {
                        if (((LiftWristViewRes) response).isSuccess()) {
                            BleBaseRequest bleBaseRequest8 = fromQueue8.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest8, "setLiftWristViewRequest.baseRequest");
                            onSettingsResponse(settingsResultListener2, new BleBaseResponse(bleBaseRequest8));
                            return;
                        }
                        onSettingsError(settingsResultListener2, new BleBaseError("Set LiftWrist View Failed"));
                        return;
                    }
                    return;
                }
                return;
            case 8:
                String str = G;
                LogHelper.d(str, "On Response " + response);
                BleCommand bleCommand7 = BleCommand.SET_SENS_AI_ACTIVITY_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue9 = getFromQueue(bleCommand7, response);
                if (fromQueue9 != null) {
                    SettingsResultListener settingsResultListener3 = (SettingsResultListener) fromQueue9.f3181a.getResponseListener();
                    BleBaseRequest bleBaseRequest9 = fromQueue9.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest9, "setSensAIActivityConfig.baseRequest");
                    BleBaseResponse bleBaseResponse5 = new BleBaseResponse(bleBaseRequest9);
                    StringBuilder sb = new StringBuilder();
                    sb.append("((SET_SENS_AI_ACTIVITY_CONFIG) response).isSuccess(): ");
                    SetSensAIActivityConfigRes setSensAIActivityConfigRes = (SetSensAIActivityConfigRes) response;
                    sb.append(setSensAIActivityConfigRes.isSuccess());
                    LogHelper.d(str, sb.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                    if (setSensAIActivityConfigRes.isSuccess()) {
                        onSettingsResponse(settingsResultListener3, bleBaseResponse5);
                        return;
                    } else {
                        onSettingsError(settingsResultListener3, new BleBaseError("SET_SENS_AI_ACTIVITY_CONFIG setting failed", bleCommand7));
                        return;
                    }
                }
                return;
            case 9:
                BleCommand bleCommand8 = BleCommand.GET_SENS_AI_ACTIVITY_CONFIG;
                LeonardoBleApiImpl.QueueObject fromQueue10 = getFromQueue(bleCommand8, response);
                if (fromQueue10 != null) {
                    BaseListener responseListener6 = fromQueue10.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener6, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener6 = (DataResultListener) responseListener6;
                    if (response instanceof GetSensAIActivityConfigRes) {
                        BleGetSensAIActivityConfig bleGetSensAIActivityConfig = ((GetSensAIActivityConfigRes) response).getBleGetSensAIActivityConfig();
                        if (bleGetSensAIActivityConfig != null) {
                            GetSensAIActivityConfigResponse getSensAIActivityConfigResponse = new GetSensAIActivityConfigResponse();
                            getSensAIActivityConfigResponse.setActivityNumber(bleGetSensAIActivityConfig.getActivityNumber());
                            getSensAIActivityConfigResponse.setType(bleGetSensAIActivityConfig.getType());
                            BleBaseRequest bleBaseRequest10 = fromQueue10.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest10, "getSensAIActivityConfigRequest.baseRequest");
                            BleBaseResponse bleBaseResponse6 = new BleBaseResponse(bleBaseRequest10);
                            bleBaseResponse6.setResponseData(getSensAIActivityConfigResponse);
                            bleBaseResponse6.setCompleted(true);
                            onDataResponse(bleBaseResponse6, dataResultListener6);
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener6, new BleBaseError("GetSensAIActivityConfigReq failed", bleCommand8));
                    return;
                }
                return;
            case 10:
                BleCommand bleCommand9 = BleCommand.GET_SENS_AI_SUMMARY_DATA;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate = getFromQueuebasedOnDate(bleCommand9, response.getBaseRequest());
                if (fromQueuebasedOnDate != null) {
                    int sameCommandCount = getSameCommandCount(bleCommand9);
                    GetSensAISummaryDataResponse getSensAISummaryDataResponse = new GetSensAISummaryDataResponse(LeonardoFormatter.getSensAISummaryResponse((ArrayList) ((GetSensAISummaryDataRes) response).getBleActivitySummaryDataList()));
                    getSensAISummaryDataResponse.setComplete(sameCommandCount == 0);
                    BleBaseRequest bleBaseRequest11 = fromQueuebasedOnDate.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest11, "sensAISummaryDataRequest.baseRequest");
                    BleBaseResponse bleBaseResponse7 = new BleBaseResponse(bleBaseRequest11);
                    bleBaseResponse7.setResponseData(getSensAISummaryDataResponse);
                    BaseListener responseListener7 = fromQueuebasedOnDate.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener7, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    onDataResponse(bleBaseResponse7, (DataResultListener) responseListener7);
                    LogHelper.d(G, "Sens AI summary Request onDataResponse", ModuleNames.BLEABSTRACT.getModuleName());
                    return;
                }
                return;
            case 11:
                BleCommand bleCommand10 = BleCommand.GET_DISTANCE;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate2 = getFromQueuebasedOnDate(bleCommand10, response.getBaseRequest());
                if (fromQueuebasedOnDate2 != null) {
                    int sameCommandCount2 = getSameCommandCount(bleCommand10);
                    DailyDistanceData distanceData = ((DistanceDataRes) response).getDistanceData();
                    Intrinsics.checkNotNull(distanceData, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.model.DailyDistanceData");
                    GetDistanceDataResponse getDistanceDataResponse = new GetDistanceDataResponse(LeonardoFormatter.getDistanceResponse(distanceData));
                    getDistanceDataResponse.setComplete(sameCommandCount2 == 0);
                    BleBaseRequest bleBaseRequest12 = fromQueuebasedOnDate2.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest12, "distanceDataRequest.baseRequest");
                    BleBaseResponse bleBaseResponse8 = new BleBaseResponse(bleBaseRequest12);
                    bleBaseResponse8.setResponseData(getDistanceDataResponse);
                    BaseListener responseListener8 = fromQueuebasedOnDate2.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener8, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    onDataResponse(bleBaseResponse8, (DataResultListener) responseListener8);
                    LogHelper.d(G, "Distance Request onDataResponse", ModuleNames.BLEABSTRACT.getModuleName());
                    return;
                }
                return;
            case 12:
                BleCommand bleCommand11 = BleCommand.GET_CALORIE;
                LeonardoBleApiImpl.QueueObject fromQueuebasedOnDate3 = getFromQueuebasedOnDate(bleCommand11, response.getBaseRequest());
                if (fromQueuebasedOnDate3 != null) {
                    int sameCommandCount3 = getSameCommandCount(bleCommand11);
                    DailyCalorieData calorieData = ((CalorieDataRes) response).getCalorieData();
                    Intrinsics.checkNotNull(calorieData, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.model.DailyCalorieData");
                    GetCalorieDataResponse getCalorieDataResponse = new GetCalorieDataResponse(LeonardoFormatter.getCalorieResponse(calorieData));
                    getCalorieDataResponse.setComplete(sameCommandCount3 == 0);
                    BleBaseRequest bleBaseRequest13 = fromQueuebasedOnDate3.f3181a;
                    Intrinsics.checkNotNullExpressionValue(bleBaseRequest13, "calorieDataRequest.baseRequest");
                    BleBaseResponse bleBaseResponse9 = new BleBaseResponse(bleBaseRequest13);
                    bleBaseResponse9.setResponseData(getCalorieDataResponse);
                    BaseListener responseListener9 = fromQueuebasedOnDate3.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener9, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    onDataResponse(bleBaseResponse9, (DataResultListener) responseListener9);
                    LogHelper.d(G, "Calorie Request onDataResponse", ModuleNames.BLEABSTRACT.getModuleName());
                    return;
                }
                return;
            case 13:
                BleCommand bleCommand12 = BleCommand.GET_SENS_AI_SUMMARY_DETAILS;
                LeonardoBleApiImpl.QueueObject fromQueue11 = getFromQueue(bleCommand12, response);
                if (fromQueue11 != null) {
                    BaseListener responseListener10 = fromQueue11.f3181a.getResponseListener();
                    Intrinsics.checkNotNull(responseListener10, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                    DataResultListener dataResultListener7 = (DataResultListener) responseListener10;
                    if (response instanceof GetSensAISummaryDetailsDataRes) {
                        BleGetSensAISummaryDetailData sensAISummaryDetailData = ((GetSensAISummaryDetailsDataRes) response).getSensAISummaryDetailData();
                        if (sensAISummaryDetailData != null) {
                            GetSensAISummaryDetailsResponse getSensAISummaryDetailsResponse = new GetSensAISummaryDetailsResponse();
                            getSensAISummaryDetailsResponse.setSessionId(sensAISummaryDetailData.getSessionId());
                            getSensAISummaryDetailsResponse.setActivityType(sensAISummaryDetailData.getActivityType());
                            getSensAISummaryDetailsResponse.setDetailsDataNum(sensAISummaryDetailData.getDetailsDataNum());
                            getSensAISummaryDetailsResponse.setTimeStamp(sensAISummaryDetailData.getTimeStamp());
                            getSensAISummaryDetailsResponse.setHandSpeedList(sensAISummaryDetailData.getHandSpeedList());
                            getSensAISummaryDetailsResponse.setHrList(sensAISummaryDetailData.getHrList());
                            getSensAISummaryDetailsResponse.setStepsList(sensAISummaryDetailData.getStepsList());
                            getSensAISummaryDetailsResponse.setDistanceList(sensAISummaryDetailData.getDistanceList());
                            getSensAISummaryDetailsResponse.setCaloriesList(sensAISummaryDetailData.getCaloriesList());
                            getSensAISummaryDetailsResponse.setHitOrMissList(sensAISummaryDetailData.getHitOrMissList());
                            getSensAISummaryDetailsResponse.setArmSpeedList(sensAISummaryDetailData.getArmSpeedList());
                            getSensAISummaryDetailsResponse.setComplete(true);
                            BleBaseRequest bleBaseRequest14 = fromQueue11.f3181a;
                            Intrinsics.checkNotNullExpressionValue(bleBaseRequest14, "sensAISummaryDetailRequest.baseRequest");
                            BleBaseResponse bleBaseResponse10 = new BleBaseResponse(bleBaseRequest14);
                            bleBaseResponse10.setResponseData(getSensAISummaryDetailsResponse);
                            BaseListener responseListener11 = fromQueue11.f3181a.getResponseListener();
                            Intrinsics.checkNotNull(responseListener11, "null cannot be cast to non-null type com.coveiot.android.bleabstract.listeners.DataResultListener");
                            onDataResponse(bleBaseResponse10, (DataResultListener) responseListener11);
                            LogHelper.d(G, "Sens AI summary details Request onDataResponse", ModuleNames.BLEABSTRACT.getModuleName());
                            return;
                        }
                        return;
                    }
                    onDataError(dataResultListener7, new BleBaseError("GetSensAISummaryDetailReq failed", bleCommand12));
                    return;
                }
                return;
            default:
                super.onResponse(response);
                return;
        }
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl
    @Subscribe
    public void onResponseEventReceivved(@NotNull ResponseEvent responseEvent) {
        Intrinsics.checkNotNullParameter(responseEvent, "responseEvent");
        super.onResponseEventReceivved(responseEvent);
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    @Nullable
    public MutableLiveData<BloodPressureMeasurement> registerLiveBpData() {
        return null;
    }

    @Override // com.coveiot.android.bleabstract.bleimpl.CA6BTABleApiImpl, com.coveiot.android.bleabstract.bleimpl.CY1BleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3BTCallingLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CA3LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ1WavePrimeLeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.CZ0LeonardoBleApiImpl, com.coveiot.android.bleabstract.bleimpl.LeonardoBleApiImpl, com.coveiot.android.bleabstract.api.BleApi
    public void setUserSettings(@NotNull BleBaseRequest request, @NotNull SettingsResultListener listener) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(listener, "listener");
        LeonardoBleCmdService leonardoBleCmdService = LeonardoBleApiImpl.bleService;
        if (leonardoBleCmdService != null && leonardoBleCmdService.getConnectionState() == CloveBleState.BleState.CONNECTED) {
            request.setRequId(UUID.randomUUID().toString());
            if (request instanceof SetSensAIActivityConfigRequest) {
                request.setResponseListener(listener);
                request.setBleCommand(BleCommand.SET_SENS_AI_ACTIVITY_CONFIG);
                request.setRequId(UUID.randomUUID().toString());
                addToQueue(request);
                SensAISetActivityConfigReq sensAISetActivityConfigReq = new SensAISetActivityConfigReq(null, (short) 1, ((SetSensAIActivityConfigRequest) request).isActivityConfigControl());
                sensAISetActivityConfigReq.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(sensAISetActivityConfigReq, this);
            } else if (request instanceof SetLiftWristRequest) {
                request.setBleCommand(BleCommand.SET_LIFT_WRIST);
                request.setRequId(UUID.randomUUID().toString());
                request.setResponseListener(listener);
                addToQueue(request);
                SetLiftWristRequest setLiftWristRequest = (SetLiftWristRequest) request;
                LiftWristViewReq build = new LiftWristViewReq.Builder(setLiftWristRequest.isLiftWristEnabled()).setScheduledDndSupported(true).setStartHour(setLiftWristRequest.getStartHour()).setStartMinute(setLiftWristRequest.getStartMinute()).setEndHour(setLiftWristRequest.getEndHour()).setEndMinute(setLiftWristRequest.getEndMinute()).build();
                build.setReqId(request.getRequId());
                LeonardoBleApiImpl.bleService.sendRequest(build, this);
            } else if (request instanceof SetMusicMetaDataRequest) {
                SetMusicMetaDataRequest setMusicMetaDataRequest = (SetMusicMetaDataRequest) request;
                SetMediaInfoReq setMediaInfoReq = new SetMediaInfoReq(null, setMusicMetaDataRequest.title, setMusicMetaDataRequest.album);
                setMediaInfoReq.setArtist(setMusicMetaDataRequest.artist);
                LeonardoBleApiImpl.bleService.sendRequest(setMediaInfoReq, this);
            } else if (request instanceof SetCallSmsSocialNotificationRequest) {
                request.setResponseListener(listener);
                request.setRequId(UUID.randomUUID().toString());
                request.setBleCommand(BleCommand.SET_MESSAGE_ALERT_SWITCH);
                addToQueue(request);
                SetCallSmsSocialNotificationRequest setCallSmsSocialNotificationRequest = (SetCallSmsSocialNotificationRequest) request;
                MessageAlertSwitchesReq build2 = new MessageAlertSwitchesReq.Builder().setAppAlerts(setCallSmsSocialNotificationRequest.isCallEnabled(), setCallSmsSocialNotificationRequest.isCalendarEnabled(), setCallSmsSocialNotificationRequest.isSmsEnabled(), setCallSmsSocialNotificationRequest.isEmailEnabled(), setCallSmsSocialNotificationRequest.isWhatsAppEnabled(), setCallSmsSocialNotificationRequest.isWeChatEnabled(), setCallSmsSocialNotificationRequest.isFacebookEnabled(), setCallSmsSocialNotificationRequest.isInstagramEnabled(), setCallSmsSocialNotificationRequest.isTwitterEnabled(), setCallSmsSocialNotificationRequest.isMessengerEnabled(), setCallSmsSocialNotificationRequest.isQQEnabled(), setCallSmsSocialNotificationRequest.isQzoneEnabled(), setCallSmsSocialNotificationRequest.isSnapchatEnabled(), setCallSmsSocialNotificationRequest.isSkypeEnabled(), setCallSmsSocialNotificationRequest.isLineEnabled(), setCallSmsSocialNotificationRequest.isTelegramEnabled(), setCallSmsSocialNotificationRequest.isLinkedInEnabled()).setYoutubeEnabled(setCallSmsSocialNotificationRequest.isYoutubeEnabled()).setCommonAppEnabled(setCallSmsSocialNotificationRequest.isOtherAppEnabled()).setKaKaoTalkEnabled(setCallSmsSocialNotificationRequest.isKaKaoTalkEnabled()).setGmailEnabled(setCallSmsSocialNotificationRequest.isGmailEnabled()).setNewsEnabled(setCallSmsSocialNotificationRequest.isNewsEnabled()).doesSupportExtendedAppNotification(true).setCustomEventEnabled(setCallSmsSocialNotificationRequest.isCustomEventEnabled()).build();
                build2.setReqId(request.getRequId());
                build2.setDevicePlatformEnum(DevicePlatformEnum.Realtek);
                LeonardoBleApiImpl.bleService.sendRequest(build2, this);
            } else {
                super.setUserSettings(request, listener);
            }
        } else if (request.getBleCommand() != null) {
            onSettingsError(listener, new BleBaseError("Band is not connected", request.getBleCommand()));
        } else {
            onSettingsError(listener, new BleBaseError("Band is not connected"));
        }
    }

    public static final void a(JC2319BBleApiImpl this$0, LiveStepsData liveStepsData1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(liveStepsData1, "$liveStepsData1");
        this$0.liveStepsDataMutableLiveData.setValue(liveStepsData1);
        this$0.liveStepsDataMutableLiveData.postValue(liveStepsData1);
    }
}
