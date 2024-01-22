package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import android.provider.Settings;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.handler.INotificationPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SNotificationInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class NotificationHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final INotificationPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull INotificationPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = NotificationHandler.class.getSimpleName();
    }

    public final boolean a() {
        String string = Settings.Secure.getString(this.c.getContentResolver(), Constants.ENABLE_NOTIFICATION_LISTENERS.getValue());
        if (string != null) {
            String packageName = this.c.getPackageName();
            Intrinsics.checkNotNullExpressionValue(packageName, "context.packageName");
            return StringsKt__StringsKt.contains$default((CharSequence) string, (CharSequence) packageName, false, 2, (Object) null);
        }
        return false;
    }

    public final void b(SNotificationInfo sNotificationInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SaveNotificationSettingsReq saveNotificationSettingsReq = new SaveNotificationSettingsReq();
            SaveNotificationSettingsReq.SmsBean smsBean = new SaveNotificationSettingsReq.SmsBean();
            SaveNotificationSettingsReq.CallBean callBean = new SaveNotificationSettingsReq.CallBean();
            SaveNotificationSettingsReq.AppBean appBean = new SaveNotificationSettingsReq.AppBean();
            ArrayList arrayList = new ArrayList();
            SNotificationInfo.OtherApps otherApps = sNotificationInfo.getOtherApps();
            if (otherApps != null && otherApps.getApps() != null) {
                SNotificationInfo.OtherApps otherApps2 = sNotificationInfo.getOtherApps();
                Intrinsics.checkNotNull(otherApps2);
                List<SNotificationInfo.AppBean> apps = otherApps2.getApps();
                Intrinsics.checkNotNull(apps);
                int size = apps.size();
                for (int i = 0; i < size; i++) {
                    SaveNotificationSettingsReq.AppBean.Apps apps2 = new SaveNotificationSettingsReq.AppBean.Apps();
                    SNotificationInfo.OtherApps otherApps3 = sNotificationInfo.getOtherApps();
                    Intrinsics.checkNotNull(otherApps3);
                    List<SNotificationInfo.AppBean> apps3 = otherApps3.getApps();
                    Intrinsics.checkNotNull(apps3);
                    apps2.setActive(apps3.get(i).getActive());
                    SNotificationInfo.OtherApps otherApps4 = sNotificationInfo.getOtherApps();
                    Intrinsics.checkNotNull(otherApps4);
                    List<SNotificationInfo.AppBean> apps4 = otherApps4.getApps();
                    Intrinsics.checkNotNull(apps4);
                    apps2.setAppId(apps4.get(i).getAppId());
                    arrayList.add(apps2);
                }
            }
            SNotificationInfo.SmsBean sms = sNotificationInfo.getSms();
            smsBean.setActive(sms != null ? Intrinsics.areEqual(sms.getActive(), Boolean.TRUE) : false);
            SNotificationInfo.CallBean call = sNotificationInfo.getCall();
            callBean.setActive(call != null ? Intrinsics.areEqual(call.getActive(), Boolean.TRUE) : false);
            SNotificationInfo.OtherApps otherApps5 = sNotificationInfo.getOtherApps();
            appBean.setEnableAll(otherApps5 != null ? Intrinsics.areEqual(otherApps5.getEnableAll(), Boolean.TRUE) : false);
            appBean.setApps(arrayList);
            saveNotificationSettingsReq.setCall(callBean);
            saveNotificationSettingsReq.setSms(smsBean);
            saveNotificationSettingsReq.setOtherApps(appBean);
            CoveUserAppSettings.saveNotificationSettings(saveNotificationSettingsReq, new CoveApiListener<SaveNotificationSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.NotificationHandler$saveToServer$2
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(NotificationHandler.this.getTAG(), "Notification save to the server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveNotificationSettingsRes saveNotificationSettingsRes) {
                    LogHelper.i(NotificationHandler.this.getTAG(), "Notification saved to the server successfully.");
                }
            });
        }
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @NotNull
    public final INotificationPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setNotification() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                if (a()) {
                    final SNotificationInfo sCommandInfo = getSCommandInfo(getCommand());
                    if (sCommandInfo != null) {
                        if (!this.d.isAlreadySet(this.c, sCommandInfo)) {
                            if (BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isNotificationConfigurationSupported()) {
                                new SetCallSmsSocialNotificationRequest.Builder().setAppAlerts(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true).build();
                                SetCallSmsSocialNotificationRequest callSmsSocialNotificationRequest = new SetCallSmsSocialNotificationRequest.Builder().setCallEnabled(true).setSMSAppEnabled(true).setFacebookEnabled(true).setWhatsAppEnabled(true).setTwitterEnabled(true).setInstagramEnabled(true).setSkypeEnabled(true).setTelegramEnabled(true).setLinkedInEnabled(true).setEmailAppEnabled(true).build();
                                BleApi bleApi = BleApiManager.getInstance(this.c).getBleApi();
                                Intrinsics.checkNotNullExpressionValue(callSmsSocialNotificationRequest, "callSmsSocialNotificationRequest");
                                bleApi.setUserSettings(callSmsSocialNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.NotificationHandler$setNotification$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        NotificationHandler.this.sendCommandFailed(error.getErrorMsg());
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        NotificationHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                                        NotificationHandler.this.getCommand().setData(null);
                                        NotificationHandler.this.getCommandResponseListener().onResponse(NotificationHandler.this.getCommand());
                                        INotificationPreferenceHandler preferenceHandler = NotificationHandler.this.getPreferenceHandler();
                                        Context context = NotificationHandler.this.getContext();
                                        final SNotificationInfo sNotificationInfo = sCommandInfo;
                                        final NotificationHandler notificationHandler = NotificationHandler.this;
                                        preferenceHandler.update(context, sNotificationInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.NotificationHandler$setNotification$1$onSettingsResponse$1
                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onFailure(@Nullable String str) {
                                                LogHelper.e(NotificationHandler.this.getTAG(), "Saving to preference failed.");
                                            }

                                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                            public void onSuccess() {
                                                NotificationHandler.this.b(sNotificationInfo);
                                            }
                                        });
                                    }
                                });
                                return;
                            }
                            getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                            getCommand().setData(null);
                            getCommandResponseListener().onResponse(getCommand());
                            this.d.update(this.c, sCommandInfo, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.NotificationHandler$setNotification$2
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str) {
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                    NotificationHandler.this.b(sCommandInfo);
                                }
                            });
                            return;
                        }
                        sendSettingAlreadyApplied();
                        return;
                    }
                    sendCommandParsingFailed();
                    return;
                }
                sendNotificationAccessNotEnabled();
                return;
            }
            sendSyncIsInProgress();
            return;
        }
        sendDeviceNotConnected();
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    @Nullable
    public SNotificationInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SNotificationInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SNotificationInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
