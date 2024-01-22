package com.coveiot.android.remotecommandframework.alexa.ble;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreter;
import com.coveiot.android.remotecommandframework.alexa.commandinterpreter.CommandInterpreterFactory;
import com.coveiot.android.remotecommandframework.alexa.handler.IDNDPreferenceHandler;
import com.coveiot.android.remotecommandframework.alexa.model.SCommandInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SDNDInfo;
import com.coveiot.android.remotecommandframework.alexa.model.SScheduleInfo;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.response.listener.CommandResponseListener;
import com.coveiot.android.remotecommandframework.alexa.utils.ResponseType;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsRes;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.sdk.ble.api.model.DNDSettingsData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.e;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class DNDHandler extends CommandHandler {
    @NotNull
    public final Context c;
    @NotNull
    public final IDNDPreferenceHandler d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DNDHandler(@NotNull Context context, @NotNull Command command, @NotNull CommandResponseListener commandResponseListener, @NotNull IDNDPreferenceHandler preferenceHandler) {
        super(command, commandResponseListener);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(command, "command");
        Intrinsics.checkNotNullParameter(commandResponseListener, "commandResponseListener");
        Intrinsics.checkNotNullParameter(preferenceHandler, "preferenceHandler");
        this.c = context;
        this.d = preferenceHandler;
        this.e = DNDHandler.class.getSimpleName();
    }

    public final SDNDInfo a(DNDData dNDData) {
        SDNDInfo sDNDInfo = new SDNDInfo();
        sDNDInfo.setActive(dNDData.isDNDEnabled());
        ArrayList arrayList = new ArrayList();
        SScheduleInfo sScheduleInfo = new SScheduleInfo();
        Pair<String, String> c = c(dNDData.getStartHour(), dNDData.getStartMin(), dNDData.getEndHour(), dNDData.getEndMin());
        sScheduleInfo.setStartTime(c.getFirst());
        sScheduleInfo.setEndTime(c.getSecond());
        arrayList.add(sScheduleInfo);
        sDNDInfo.setSchedules(arrayList);
        return sDNDInfo;
    }

    public final SDNDInfo b(DNDSettingsData dNDSettingsData) {
        SDNDInfo sDNDInfo = new SDNDInfo();
        sDNDInfo.setActive(dNDSettingsData.isDNDEnabled());
        ArrayList arrayList = new ArrayList();
        SScheduleInfo sScheduleInfo = new SScheduleInfo();
        Pair<String, String> c = c(dNDSettingsData.getStartHour(), dNDSettingsData.getStartMin(), dNDSettingsData.getEndHour(), dNDSettingsData.getEndMin());
        sScheduleInfo.setStartTime(c.getFirst());
        sScheduleInfo.setEndTime(c.getSecond());
        arrayList.add(sScheduleInfo);
        sDNDInfo.setSchedules(arrayList);
        return sDNDInfo;
    }

    public final Pair<String, String> c(int i, int i2, int i3, int i4) {
        String valueOf;
        String valueOf2;
        String valueOf3;
        String valueOf4;
        if (i < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(i);
            valueOf = sb.toString();
        } else {
            valueOf = String.valueOf(i);
        }
        if (i2 < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i2);
            valueOf2 = sb2.toString();
        } else {
            valueOf2 = String.valueOf(i2);
        }
        if (i3 < 10) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('0');
            sb3.append(i3);
            valueOf3 = sb3.toString();
        } else {
            valueOf3 = String.valueOf(i3);
        }
        if (i4 < 10) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append('0');
            sb4.append(i4);
            valueOf4 = sb4.toString();
        } else {
            valueOf4 = String.valueOf(i4);
        }
        return new Pair<>(valueOf + ':' + valueOf2 + ":00", valueOf3 + ':' + valueOf4 + ":00");
    }

    public final boolean d() {
        return !RepositoryUtils.isMoyangDevice(this.c);
    }

    public final void e(SDNDInfo sDNDInfo) {
        if (AppUtils.isNetConnected(this.c)) {
            SaveDNDSettingsReq saveDNDSettingsReq = new SaveDNDSettingsReq();
            saveDNDSettingsReq.setActive(sDNDInfo.getActive());
            SaveDNDSettingsReq.ScheduleDNDListBean scheduleDNDListBean = new SaveDNDSettingsReq.ScheduleDNDListBean();
            List<SScheduleInfo> schedules = sDNDInfo.getSchedules();
            if (!(schedules == null || schedules.isEmpty())) {
                List<SScheduleInfo> schedules2 = sDNDInfo.getSchedules();
                Intrinsics.checkNotNull(schedules2);
                scheduleDNDListBean.setStartTime(schedules2.get(0).getStartTime());
                List<SScheduleInfo> schedules3 = sDNDInfo.getSchedules();
                Intrinsics.checkNotNull(schedules3);
                scheduleDNDListBean.setEndTime(schedules3.get(0).getEndTime());
            } else if (!RepositoryUtils.isKaHaDevice(this.c) && !RepositoryUtils.isKaHaDeviceREM(this.c)) {
                scheduleDNDListBean.setStartTime("00:00:00");
                scheduleDNDListBean.setEndTime("23:59:00");
            } else {
                scheduleDNDListBean.setStartTime("00:00:00");
                scheduleDNDListBean.setEndTime("00:00:00");
            }
            saveDNDSettingsReq.setDndListBean(e.listOf(scheduleDNDListBean));
            CoveUserAppSettings.saveDNDSettings(saveDNDSettingsReq, new CoveApiListener<SaveDNDSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$saveToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.d(DNDHandler.this.getTAG(), "DND saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDNDSettingsRes saveDNDSettingsRes) {
                    LogHelper.d(DNDHandler.this.getTAG(), "DND saved to server successfully.");
                }
            });
        }
    }

    public final void f(final SDNDInfo sDNDInfo) {
        CommandInterpreter<SCommandInfo, BleBaseRequest> commandInterpreter = CommandInterpreterFactory.INSTANCE.getCommandInterpreter(this.c, sDNDInfo);
        SetDNDModeRequest setDNDModeRequest = (SetDNDModeRequest) (commandInterpreter != null ? commandInterpreter.getBleRequestObject() : null);
        if (setDNDModeRequest != null && this.d.isValidInput(this.c, sDNDInfo)) {
            if (BleApiManager.getInstance(this.c).getBleApi() != null && (BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isDndSupported() || BleApiManager.getInstance(this.c).getBleApi().getDeviceSupportedFeatures().isScheduledDndSupported())) {
                if (!this.d.isAlreadySet(this.c, sDNDInfo)) {
                    BleApiManager.getInstance(this.c).getBleApi().setUserSettings(setDNDModeRequest, new SettingsResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$sendToBandAndUpdatePreference$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            DNDHandler.this.sendCommandFailed(error.getErrorMsg());
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            DNDHandler.this.getCommand().setStatus(ResponseType.SUCCESS.getStatus());
                            DNDHandler.this.getCommand().setData(null);
                            DNDHandler.this.getCommandResponseListener().onResponse(DNDHandler.this.getCommand());
                            IDNDPreferenceHandler preferenceHandler = DNDHandler.this.getPreferenceHandler();
                            Context context = DNDHandler.this.getContext();
                            final SDNDInfo sDNDInfo2 = sDNDInfo;
                            final DNDHandler dNDHandler = DNDHandler.this;
                            preferenceHandler.update(context, sDNDInfo2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$sendToBandAndUpdatePreference$1$onSettingsResponse$1
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str) {
                                    LogHelper.e(DNDHandler.this.getTAG(), "Saving to preference failed.");
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                    DNDHandler.this.e(sDNDInfo2);
                                }
                            });
                        }
                    });
                    return;
                } else {
                    sendSettingAlreadyApplied();
                    return;
                }
            }
            sendCommandNotSupported();
            return;
        }
        sendInvalidCommandData();
    }

    @NotNull
    public final Context getContext() {
        return this.c;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void getDndFromBand(@NotNull final SuccessListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (d()) {
            BleApiManager.getInstance(this.c).getBleApi().getData(new GetDNDModeSettingsRequest(), new DataResultListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$getDndFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    SuccessListener.this.onFailure(error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    SDNDInfo b;
                    SDNDInfo a2;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() != null && (response.getResponseData() instanceof DNDData)) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.DNDData");
                        IDNDPreferenceHandler preferenceHandler = this.getPreferenceHandler();
                        Context context = this.getContext();
                        a2 = this.a((DNDData) responseData);
                        final SuccessListener successListener = SuccessListener.this;
                        preferenceHandler.updatePreferenceWithLatestBandData(context, a2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$getDndFromBand$1$onDataResponse$1
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str) {
                                SuccessListener.this.onSuccess();
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                                SuccessListener.this.onSuccess();
                            }
                        });
                    } else if (response.getResponseData() == null || !(response.getResponseData() instanceof DNDSettingsData)) {
                    } else {
                        Object responseData2 = response.getResponseData();
                        Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.model.DNDSettingsData");
                        IDNDPreferenceHandler preferenceHandler2 = this.getPreferenceHandler();
                        Context context2 = this.getContext();
                        b = this.b((DNDSettingsData) responseData2);
                        final SuccessListener successListener2 = SuccessListener.this;
                        preferenceHandler2.updatePreferenceWithLatestBandData(context2, b, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$getDndFromBand$1$onDataResponse$2
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str) {
                                SuccessListener.this.onSuccess();
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                                SuccessListener.this.onSuccess();
                            }
                        });
                    }
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
    public final IDNDPreferenceHandler getPreferenceHandler() {
        return this.d;
    }

    public final String getTAG() {
        return this.e;
    }

    @Override // com.coveiot.android.remotecommandframework.alexa.ble.CommandHandler
    public void setDND() {
        Util.Companion companion = Util.Companion;
        if (companion.isDeviceConnected(this.c)) {
            if (!companion.isSyncInProgress()) {
                final SDNDInfo sCommandInfo = getSCommandInfo(getCommand());
                if (sCommandInfo != null) {
                    getDndFromBand(new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.ble.DNDHandler$setDND$1
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str) {
                            DNDHandler.this.f(sCommandInfo);
                        }

                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onSuccess() {
                            DNDHandler.this.f(sCommandInfo);
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
    public SDNDInfo getSCommandInfo(@NotNull Command command) {
        Intrinsics.checkNotNullParameter(command, "command");
        try {
            Gson gson = new Gson();
            return (SDNDInfo) gson.fromJson((JsonElement) gson.toJsonTree(command.getData()).getAsJsonObject(), (Class<Object>) SDNDInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
