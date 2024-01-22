package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.IAlarmPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.AlarmType;
import com.coveiot.android.remotecommandframework.alexa.model.SAlarmInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class AlarmHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IAlarmPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlarmHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IAlarmPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = AlarmHandler.class.getSimpleName();
    }

    public final List<SAlarmInfo> a(List<Alarm> list) {
        ArrayList arrayList = new ArrayList();
        for (Alarm alarm : list) {
            SAlarmInfo sAlarmInfo = new SAlarmInfo();
            sAlarmInfo.setActive(alarm.isEnabled());
            sAlarmInfo.setRepeat(f(alarm));
            sAlarmInfo.setAlarmId(alarm.getAlarmId());
            sAlarmInfo.setType(b(Integer.valueOf(alarm.getAlarmType())).getType());
            sAlarmInfo.setWeekDays(d(alarm));
            sAlarmInfo.setLabel(alarm.getEventName());
            StringBuilder sb = new StringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarm.getHour())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(':');
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarm.getMinute())}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            sb.append(format2);
            sb.append(":00");
            sAlarmInfo.setTime(sb.toString());
            arrayList.add(sAlarmInfo);
        }
        return arrayList;
    }

    public final AlarmType b(Integer num) {
        if (num == null) {
            return AlarmType.VIBRATION_ALARM;
        }
        int intValue = num.intValue();
        if (intValue != 1) {
            if (intValue != 2) {
                if (intValue != 3) {
                    if (intValue != 4) {
                        return AlarmType.VIBRATION_ALARM;
                    }
                    return AlarmType.FOOD;
                }
                return AlarmType.DRINK;
            }
            return AlarmType.MEDICINE;
        }
        return AlarmType.VIBRATION_ALARM;
    }

    public final int c() {
        return (BleApiManager.getInstance(this.c).getDeviceType() == DeviceType.kh17 || BleApiManager.getInstance(this.c).getDeviceType() == DeviceType.crpGPF5) ? 3 : 5;
    }

    public final String d(Alarm alarm) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (alarm.isSundayEnabled()) {
            str = "S";
        } else {
            str = "" + Soundex.SILENT_MARKER;
        }
        if (alarm.isMondayEnabled()) {
            str2 = str + 'M';
        } else {
            str2 = str + Soundex.SILENT_MARKER;
        }
        if (alarm.isTuesdayEnabled()) {
            str3 = str2 + 'T';
        } else {
            str3 = str2 + Soundex.SILENT_MARKER;
        }
        if (alarm.isWednesdayEnabled()) {
            str4 = str3 + 'W';
        } else {
            str4 = str3 + Soundex.SILENT_MARKER;
        }
        if (alarm.isThursdayEnabled()) {
            str5 = str4 + 'T';
        } else {
            str5 = str4 + Soundex.SILENT_MARKER;
        }
        if (alarm.isFridayEnabled()) {
            str6 = str5 + 'F';
        } else {
            str6 = str5 + Soundex.SILENT_MARKER;
        }
        if (alarm.isSaturdayEnabled()) {
            return str6 + 'S';
        }
        return str6 + Soundex.SILENT_MARKER;
    }

    public final boolean e() {
        return true;
    }

    public final boolean f(Alarm alarm) {
        return alarm.isSundayEnabled() || alarm.isMondayEnabled() || alarm.isTuesdayEnabled() || alarm.isWednesdayEnabled() || alarm.isThursdayEnabled() || alarm.isFridayEnabled() || alarm.isSaturdayEnabled();
    }

    public final void g(SAlarmInfo sAlarmInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            ArrayList arrayList = new ArrayList();
            SaveAlarmSettingReq.AlarmListBean alarmListBean = new SaveAlarmSettingReq.AlarmListBean();
            alarmListBean.setAlarmId(String.valueOf(sAlarmInfo.getAlarmId() + 1));
            alarmListBean.setActive(sAlarmInfo.getActive());
            alarmListBean.setTime(sAlarmInfo.getTime());
            alarmListBean.setLabel(sAlarmInfo.getLabel());
            alarmListBean.setRepeat(sAlarmInfo.getRepeat());
            alarmListBean.setWeek(sAlarmInfo.getWeekDays());
            if (sAlarmInfo.getType() != null && Intrinsics.areEqual(sAlarmInfo.getType(), AlarmType.VIBRATION_ALARM.getType())) {
                sAlarmInfo.setType(null);
            }
            alarmListBean.setType(sAlarmInfo.getType());
            arrayList.add(alarmListBean);
            SaveAlarmSettingReq saveAlarmSettingReq = new SaveAlarmSettingReq();
            saveAlarmSettingReq.setAlarmListBeans(arrayList);
            CoveUserAppSettings.saveAlarmSettings(saveAlarmSettingReq, new CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(AlarmHandler.this.getTAG(), "Alarm save to the server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAlarmSettingRes saveAlarmSettingRes) {
                    LogHelper.d(AlarmHandler.this.getTAG(), "Alarm saved to the server successfully.");
                }
            });
        }
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void getAlarmFromBand(@NotNull final SuccessListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (e()) {
            BleApiManager.getInstance(this.c).getBleApi().getData(new GetAlarmDataRequest(), new DataResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$getAlarmFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    SuccessListener.this.onFailure(error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    List<SAlarmInfo> a2;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof List) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.bleabstract.models.Alarm>");
                        AlarmHandler alarmHandler = this;
                        final SuccessListener successListener = SuccessListener.this;
                        IAlarmPreferenceHandler preferenceHandler = alarmHandler.getPreferenceHandler();
                        Context context = alarmHandler.getContext();
                        a2 = alarmHandler.a(CollectionsKt___CollectionsKt.toMutableList((Collection) ((List) responseData)));
                        preferenceHandler.updatePreferenceWithLatestBandData(context, a2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$getAlarmFromBand$1$onDataResponse$1$1
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str) {
                                SuccessListener.this.onSuccess();
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                                SuccessListener.this.onSuccess();
                            }
                        });
                        return;
                    }
                    SuccessListener.this.onSuccess();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        listener.onSuccess();
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final IAlarmPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    public final void h(final SAlarmInfo sAlarmInfo) {
        if (!this.d.isAtMaximum(this.c, sAlarmInfo, c())) {
            if (!this.d.isAlreadySet(this.c, sAlarmInfo)) {
                sAlarmInfo.setAlarmId(this.d.getAlarmId(this.c, sAlarmInfo, c()));
                CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sAlarmInfo);
                SetVibrationAlarmRequest setVibrationAlarmRequest = (SetVibrationAlarmRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
                if (setVibrationAlarmRequest != null && this.d.isValidInput(this.c, sAlarmInfo)) {
                    BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setVibrationAlarmRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$sendToBandAndUpdatePreference$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            AlarmHandler.this.sendCommandFailed(error.getErrorMsg());
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            int c;
                            Intrinsics.checkNotNullParameter(response, "response");
                            AlarmHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                            AlarmHandler.this.getCommand().setData(null);
                            AlarmHandler.this.getCommandResponseListener().onResponse(AlarmHandler.this.getCommand());
                            IAlarmPreferenceHandler preferenceHandler = AlarmHandler.this.getPreferenceHandler();
                            Context context = AlarmHandler.this.getContext();
                            SAlarmInfo sAlarmInfo2 = sAlarmInfo;
                            c = AlarmHandler.this.c();
                            final AlarmHandler alarmHandler = AlarmHandler.this;
                            final SAlarmInfo sAlarmInfo3 = sAlarmInfo;
                            preferenceHandler.update(context, sAlarmInfo2, c, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$sendToBandAndUpdatePreference$1$onSettingsResponse$1
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str) {
                                    LogHelper.e(AlarmHandler.this.getTAG(), "Saving to preference failed.");
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                    AlarmHandler.this.g(sAlarmInfo3);
                                }
                            });
                        }
                    });
                    return;
                } else {
                    sendInvalidCommandData();
                    return;
                }
            }
            sendSettingAlreadyApplied();
            return;
        }
        sendMaxLimitExceeded();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setAlarm() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SAlarmInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    getAlarmFromBand(new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.AlarmHandler$setAlarm$1
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str) {
                            AlarmHandler.this.h(sCommandInfo);
                        }

                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onSuccess() {
                            AlarmHandler.this.h(sCommandInfo);
                        }
                    });
                    return;
                } else {
                    sendCommandParsingFailed();
                    return;
                }
            }
            sendSyncIsInProgress();
            return;
        }
        sendDeviceNotConnected();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SAlarmInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SAlarmInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SAlarmInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
