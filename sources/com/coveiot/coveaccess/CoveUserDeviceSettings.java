package com.coveiot.coveaccess;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.ActivitySession;
import com.coveiot.coveaccess.model.server.AutoRecognize;
import com.coveiot.coveaccess.model.server.AutoRecognizeActivity;
import com.coveiot.coveaccess.model.server.AutoRecognizeSchedule;
import com.coveiot.coveaccess.model.server.AutoRecognizeSlot;
import com.coveiot.coveaccess.model.server.AutoStress;
import com.coveiot.coveaccess.model.server.AutoStressAlert;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.EnabledTypeSports;
import com.coveiot.coveaccess.model.server.GetAppConfigRes;
import com.coveiot.coveaccess.model.server.SAllUserDeviceSettingRes;
import com.coveiot.coveaccess.model.server.SGetSmartAlertAppsData;
import com.coveiot.coveaccess.model.server.SUserDeviceSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.Save4hButtonActionItemsReq;
import com.coveiot.coveaccess.userdevicesetting.Save4hButtonActionsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveActivitySessionReq;
import com.coveiot.coveaccess.userdevicesetting.SaveActivitySessionRes;
import com.coveiot.coveaccess.userdevicesetting.SaveAmbientSoundSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAmbientSoundSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoActivityRecognizeReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoRecognizeSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoSpo2SettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoSpo2SettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoStressSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoStressSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomMessageSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomMessageSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveMapNavigationSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveMapNavigationSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveRespiratoryRateSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveRespiratoryRateSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveSedentaryAlertDeviceSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveSedentaryAlertDeviceSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveSmartAlertSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.SaveSmartAlertSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.SaveVitalCardSequenceRes;
import com.coveiot.coveaccess.userdevicesetting.model.AmbientSoundSettings;
import com.coveiot.coveaccess.userdevicesetting.model.AutoSpo2Settings;
import com.coveiot.coveaccess.userdevicesetting.model.Button4hActionItems;
import com.coveiot.coveaccess.userdevicesetting.model.ContactsSettings;
import com.coveiot.coveaccess.userdevicesetting.model.MapNavigationSettings;
import com.coveiot.coveaccess.userdevicesetting.model.RespiratoryRateSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SOSSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SaveVitalCardsSequenceReq;
import com.coveiot.coveaccess.userdevicesetting.model.SedentaryAlertUserDeviceSettingsBean;
import com.coveiot.coveaccess.userdevicesetting.model.SmartAlertSettings;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveUserDeviceSettings {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6355a;

        public a(CoveApiListener coveApiListener) {
            this.f6355a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6355a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6355a.onSuccess(new SaveAutoSpo2SettingsRes(response.code()));
            } else {
                this.f6355a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6356a;

        public b(CoveApiListener coveApiListener) {
            this.f6356a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6356a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6356a.onSuccess(new SaveVitalCardSequenceRes(response.code()));
            } else {
                this.f6356a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<GetAppConfigRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6357a;

        public c(CoveApiListener coveApiListener) {
            this.f6357a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<GetAppConfigRes> call, Throwable th) {
            this.f6357a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<GetAppConfigRes> call, Response<GetAppConfigRes> response) {
            if (response.isSuccessful()) {
                this.f6357a.onSuccess(response.body());
            } else {
                this.f6357a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6358a;

        public d(CoveApiListener coveApiListener) {
            this.f6358a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6358a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6358a.onSuccess(new Save4hButtonActionsRes(response.code()));
            } else {
                this.f6358a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6359a;

        public e(CoveApiListener coveApiListener) {
            this.f6359a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6359a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6359a.onSuccess(new SaveMapNavigationSettingsRes(response.code()));
            } else {
                this.f6359a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6360a;

        public f(CoveApiListener coveApiListener) {
            this.f6360a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6360a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6360a.onSuccess(new SaveSmartAlertSettingsRes(response.code()));
            } else {
                this.f6360a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<SGetSmartAlertAppsData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6361a;

        public g(CoveApiListener coveApiListener) {
            this.f6361a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetSmartAlertAppsData> call, Throwable th) {
            this.f6361a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetSmartAlertAppsData> call, Response<SGetSmartAlertAppsData> response) {
            if (response.isSuccessful()) {
                this.f6361a.onSuccess(response.body());
            } else {
                this.f6361a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6362a;

        public h(CoveApiListener coveApiListener) {
            this.f6362a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6362a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6362a.onSuccess(new CommonResponseClass());
            } else {
                this.f6362a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6363a;

        public i(CoveApiListener coveApiListener) {
            this.f6363a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6363a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6363a.onSuccess(new CommonResponseClass());
            } else {
                this.f6363a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6364a;

        public j(CoveApiListener coveApiListener) {
            this.f6364a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6364a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6364a.onSuccess(new SaveCustomRemindersSettingsRes(response.code()));
            } else {
                this.f6364a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6365a;

        public k(CoveApiListener coveApiListener) {
            this.f6365a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6365a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6365a.onSuccess(new SaveCustomMessageSettingsRes(response.code()));
            } else {
                this.f6365a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6366a;

        public l(CoveApiListener coveApiListener) {
            this.f6366a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6366a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6366a.onSuccess(new SaveAutoStressSettingsRes(response.code()));
            } else {
                this.f6366a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6367a;

        public m(CoveApiListener coveApiListener) {
            this.f6367a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6367a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6367a.onSuccess(new SaveAutoRecognizeSettingsRes(response.code()));
            } else {
                this.f6367a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6368a;

        public n(CoveApiListener coveApiListener) {
            this.f6368a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6368a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6368a.onSuccess(new SaveActivitySessionRes(response.code()));
            } else {
                this.f6368a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<SAllUserDeviceSettingRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6369a;

        public o(CoveApiListener coveApiListener) {
            this.f6369a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SAllUserDeviceSettingRes> call, Throwable th) {
            this.f6369a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SAllUserDeviceSettingRes> call, Response<SAllUserDeviceSettingRes> response) {
            if (response.isSuccessful() && response.body() != null && response.body().getDataBean() != null) {
                SAllUserDeviceSettingRes body = response.body();
                GetAllUserDeviceSettingRes getAllUserDeviceSettingRes = new GetAllUserDeviceSettingRes();
                GetAllUserDeviceSettingRes.DataBean dataBean = new GetAllUserDeviceSettingRes.DataBean();
                SAllUserDeviceSettingRes.DataBean.CustomReminders customReminders = body.getDataBean().getCustomReminders();
                if (customReminders != null && !AppUtils.isEmpty(customReminders.getCustomReminderListItems())) {
                    GetAllUserDeviceSettingRes.DataBean.CustomReminders customReminders2 = new GetAllUserDeviceSettingRes.DataBean.CustomReminders();
                    ArrayList arrayList = new ArrayList();
                    for (SAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem customReminderListItem : customReminders.getCustomReminderListItems()) {
                        GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem customReminderListItem2 = new GetAllUserDeviceSettingRes.DataBean.CustomReminders.CustomReminderListItem();
                        customReminderListItem2.setReminderId(customReminderListItem.getReminderId());
                        customReminderListItem2.setActive(customReminderListItem.isActive());
                        customReminderListItem2.setType(customReminderListItem.getType());
                        customReminderListItem2.setLabel(customReminderListItem.getLabel());
                        customReminderListItem2.setStartDate(customReminderListItem.getStartDate());
                        customReminderListItem2.setEndDate(customReminderListItem.getEndDate());
                        customReminderListItem2.setRemindAt(customReminderListItem.getRemindAt());
                        customReminderListItem2.setRemindBefore(customReminderListItem.getRemindBefore());
                        customReminderListItem2.setStartTime(customReminderListItem.getStartTime());
                        customReminderListItem2.setEndTime(customReminderListItem.getEndTime());
                        customReminderListItem2.setInterval(customReminderListItem.getInterval());
                        customReminderListItem2.setWeekDays(customReminderListItem.getWeekDays());
                        arrayList.add(customReminderListItem2);
                    }
                    customReminders2.setTzOffset(customReminders.getTzOffset());
                    customReminders2.setCustomReminderListItems(arrayList);
                    dataBean.setCustomReminders(customReminders2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                SAllUserDeviceSettingRes.DataBean.CustomMessages customMessages = body.getDataBean().getCustomMessages();
                if (customMessages != null) {
                    GetAllUserDeviceSettingRes.DataBean.CustomMessages customMessages2 = new GetAllUserDeviceSettingRes.DataBean.CustomMessages();
                    GetAllUserDeviceSettingRes.DataBean.CustomMessages.NudgeMessages nudgeMessages = new GetAllUserDeviceSettingRes.DataBean.CustomMessages.NudgeMessages();
                    GetAllUserDeviceSettingRes.DataBean.CustomMessages.NudgeMessageVibration nudgeMessageVibration = new GetAllUserDeviceSettingRes.DataBean.CustomMessages.NudgeMessageVibration();
                    nudgeMessages.setActive(customMessages.getNudgeMessages().isActive());
                    nudgeMessageVibration.setActive(customMessages.getNudgeMessageVibration().isActive());
                    customMessages2.setNudgeMessages(nudgeMessages);
                    customMessages2.setNudgeMessageVibration(nudgeMessageVibration);
                    dataBean.setCustomMessages(customMessages2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                AutoRecognize autoRecognize = body.getDataBean().getAutoRecognize();
                if (autoRecognize != null) {
                    AutoRecognize autoRecognize2 = new AutoRecognize();
                    if (autoRecognize.getActivities() != null && !autoRecognize.getActivities().isEmpty()) {
                        ArrayList arrayList2 = new ArrayList();
                        for (int i = 0; i < autoRecognize.getActivities().size(); i++) {
                            AutoRecognizeActivity autoRecognizeActivity = new AutoRecognizeActivity();
                            autoRecognizeActivity.setType(autoRecognize.getActivities().get(i).getType());
                            autoRecognizeActivity.setCode(autoRecognize.getActivities().get(i).getCode());
                            arrayList2.add(autoRecognizeActivity);
                        }
                        autoRecognize2.setActivities(arrayList2);
                    }
                    autoRecognize2.setWeekDays(autoRecognize.getWeekDays());
                    if (autoRecognize.getSchedule() != null) {
                        AutoRecognizeSchedule autoRecognizeSchedule = new AutoRecognizeSchedule();
                        if (autoRecognize.getSchedule().getSlots() != null && !autoRecognize.getSchedule().getSlots().isEmpty()) {
                            ArrayList arrayList3 = new ArrayList();
                            for (int i2 = 0; i2 < autoRecognize.getSchedule().getSlots().size(); i2++) {
                                AutoRecognizeSlot autoRecognizeSlot = new AutoRecognizeSlot();
                                autoRecognizeSlot.setStartTime(autoRecognize.getSchedule().getSlots().get(i2).getStartTime());
                                autoRecognizeSlot.setEndTime(autoRecognize.getSchedule().getSlots().get(i2).getEndTime());
                                arrayList3.add(autoRecognizeSlot);
                            }
                            autoRecognizeSchedule.setSlots(arrayList3);
                            autoRecognizeSchedule.setActive(true);
                        } else {
                            autoRecognizeSchedule.setActive(false);
                        }
                        autoRecognize2.setSchedule(autoRecognizeSchedule);
                    }
                    dataBean.setAutoRecognize(autoRecognize2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                AutoStress autoStress = body.getDataBean().getAutoStress();
                if (autoStress != null) {
                    AutoStress autoStress2 = new AutoStress();
                    if (autoStress.getAlert() != null) {
                        AutoStressAlert autoStressAlert = new AutoStressAlert();
                        autoStressAlert.setActive(autoStress.getAlert().getActive());
                        autoStressAlert.setEndTime(autoStress.getAlert().getEndTime());
                        autoStressAlert.setInterval(autoStress.getAlert().getInterval());
                        autoStressAlert.setRepeat(autoStress.getAlert().getRepeat());
                        autoStressAlert.setStartTime(autoStress.getAlert().getStartTime());
                        autoStressAlert.setWeekDays(autoStress.getAlert().getWeekDays());
                        autoStress2.setAlert(autoStressAlert);
                    }
                    autoStress2.setActive(autoStress.getActive());
                    autoStress2.setInterval(autoStress.getInterval());
                    dataBean.setAutoStress(autoStress2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                ActivitySession activitySession = body.getDataBean().getActivitySession();
                if (activitySession != null) {
                    ActivitySession activitySession2 = new ActivitySession();
                    ArrayList arrayList4 = new ArrayList();
                    for (EnabledTypeSports enabledTypeSports : activitySession.getEnabledTypes()) {
                        EnabledTypeSports enabledTypeSports2 = new EnabledTypeSports();
                        enabledTypeSports2.setSessionType(enabledTypeSports.getSessionType());
                        if (enabledTypeSports.getActivitySite() != null) {
                            enabledTypeSports2.setActivitySite(enabledTypeSports.getActivitySite());
                        }
                        arrayList4.add(enabledTypeSports2);
                    }
                    dataBean.setActivitySession(activitySession2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean = body.getDataBean().getSedentaryAlertUserDeviceSettingsBean();
                if (sedentaryAlertUserDeviceSettingsBean != null) {
                    SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean2 = new SedentaryAlertUserDeviceSettingsBean();
                    SedentaryAlertUserDeviceSettingsBean.Siesta siesta = new SedentaryAlertUserDeviceSettingsBean.Siesta();
                    sedentaryAlertUserDeviceSettingsBean2.setActive(sedentaryAlertUserDeviceSettingsBean.isActive());
                    sedentaryAlertUserDeviceSettingsBean2.setStartTime(sedentaryAlertUserDeviceSettingsBean.getStartTime());
                    sedentaryAlertUserDeviceSettingsBean2.setEndTime(sedentaryAlertUserDeviceSettingsBean.getEndTime());
                    sedentaryAlertUserDeviceSettingsBean2.setInterval(sedentaryAlertUserDeviceSettingsBean.getInterval());
                    sedentaryAlertUserDeviceSettingsBean2.setRepeat(sedentaryAlertUserDeviceSettingsBean.isRepeat());
                    sedentaryAlertUserDeviceSettingsBean2.setWeekDays(sedentaryAlertUserDeviceSettingsBean.getWeekDays());
                    sedentaryAlertUserDeviceSettingsBean2.setStartTime(sedentaryAlertUserDeviceSettingsBean.getStartTime());
                    if (sedentaryAlertUserDeviceSettingsBean.getSiesta() != null) {
                        siesta.setActive(sedentaryAlertUserDeviceSettingsBean.getSiesta().isActive());
                        sedentaryAlertUserDeviceSettingsBean2.setSiesta(siesta);
                    }
                    dataBean.setSedentaryAlertUserDeviceSettingsBean(sedentaryAlertUserDeviceSettingsBean2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                AmbientSoundSettings ambientSoundSettings = body.getDataBean().getAmbientSoundSettings();
                if (ambientSoundSettings != null) {
                    AmbientSoundSettings ambientSoundSettings2 = new AmbientSoundSettings();
                    ambientSoundSettings2.setActive(ambientSoundSettings.isActive());
                    dataBean.setAmbientSoundSettings(ambientSoundSettings2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                RespiratoryRateSettings respiratoryRateSettings = body.getDataBean().getRespiratoryRateSettings();
                if (respiratoryRateSettings != null) {
                    RespiratoryRateSettings respiratoryRateSettings2 = new RespiratoryRateSettings();
                    respiratoryRateSettings2.setActive(respiratoryRateSettings.isActive());
                    dataBean.setRespiratoryRateSettings(respiratoryRateSettings2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                AutoSpo2Settings autoSPO2Settings = body.getDataBean().getAutoSPO2Settings();
                if (autoSPO2Settings != null) {
                    AutoSpo2Settings autoSpo2Settings = new AutoSpo2Settings();
                    autoSpo2Settings.setActive(autoSPO2Settings.isActive());
                    dataBean.setAutoSPO2Settings(autoSpo2Settings);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                dataBean.setAppDashboard(body.getDataBean().getAppDashboard());
                getAllUserDeviceSettingRes.setDataBean(dataBean);
                getAllUserDeviceSettingRes.setStatus(body.getStatus());
                getAllUserDeviceSettingRes.setMessage(body.getMessage());
                if (body.getDataBean().getButtonC11n() != null) {
                    Button4hActionItems button4hActionItems = new Button4hActionItems();
                    button4hActionItems.setButtonC11n(body.getDataBean().getButtonC11n());
                    dataBean.setButtonC11n(button4hActionItems);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                SmartAlertSettings smartAlertSettings = body.getDataBean().getSmartAlertSettings();
                if (smartAlertSettings != null) {
                    SmartAlertSettings smartAlertSettings2 = new SmartAlertSettings();
                    smartAlertSettings2.setApps(smartAlertSettings.getApps());
                    dataBean.setSmartAlertSettings(smartAlertSettings2);
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                if (body.getDataBean().getMapNavigationSettings() != null) {
                    dataBean.setMapNavigationSettings(body.getDataBean().getMapNavigationSettings());
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                if (body.getDataBean().getSosSettings() != null) {
                    dataBean.setSosSettings(body.getDataBean().getSosSettings());
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                if (body.getDataBean().getContactsSettings() != null) {
                    dataBean.setContactsSettings(body.getDataBean().getContactsSettings());
                    getAllUserDeviceSettingRes.setDataBean(dataBean);
                }
                this.f6369a.onSuccess(getAllUserDeviceSettingRes);
                return;
            }
            this.f6369a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6370a;

        public p(CoveApiListener coveApiListener) {
            this.f6370a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6370a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6370a.onSuccess(new SaveSedentaryAlertDeviceSettingsRes(response.code()));
            } else {
                this.f6370a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6371a;

        public q(CoveApiListener coveApiListener) {
            this.f6371a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6371a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6371a.onSuccess(new SaveAmbientSoundSettingsRes(response.code()));
            } else {
                this.f6371a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6372a;

        public r(CoveApiListener coveApiListener) {
            this.f6372a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6372a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6372a.onSuccess(new SaveRespiratoryRateSettingsRes(response.code()));
            } else {
                this.f6372a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void a(CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAllUserDevicesSettings(map).enqueue(new o(coveApiListener));
    }

    public static void b(CoveApiListener<GetAppConfigRes, CoveApiErrorModel> coveApiListener, Map<String, String> map, String str, String str2) {
        CoveApi.getService().getAppConfig(map, str, str2).enqueue(new c(coveApiListener));
    }

    public static void c(@NonNull Save4hButtonActionItemsReq save4hButtonActionItemsReq, @NonNull CoveApiListener<Save4hButtonActionsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        Button4hActionItems.ButtonC11n buttonC11n = new Button4hActionItems.ButtonC11n();
        Button4hActionItems.Button4h button4h = new Button4hActionItems.Button4h();
        button4h.setActionVersion(save4hButtonActionItemsReq.getButton4hActionItems().getButtonC11n().getButton4h().actionVersion);
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        for (Button4hActionItems.ActionItem actionItem : save4hButtonActionItemsReq.getButton4hActionItems().getButtonC11n().getButton4h().actions) {
            Button4hActionItems.ActionItem actionItem2 = new Button4hActionItems.ActionItem();
            actionItem2.setActionId(actionItem.getActionId());
            actionItem2.setEvent(actionItem.getEvent());
            arrayList.add(actionItem2);
        }
        button4h.setActions(arrayList);
        buttonC11n.setButton4h(button4h);
        sUserDeviceSettingsReq.setButtonC11n(buttonC11n);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new d(coveApiListener));
    }

    public static void d(@NonNull SaveActivitySessionReq saveActivitySessionReq, @NonNull CoveApiListener<SaveActivitySessionRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        ActivitySession activitySession = new ActivitySession();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < saveActivitySessionReq.getActivitySession().getEnabledTypes().size(); i2++) {
            EnabledTypeSports enabledTypeSports = new EnabledTypeSports();
            EnabledTypeSports enabledTypeSports2 = saveActivitySessionReq.getActivitySession().getEnabledTypes().get(i2);
            enabledTypeSports.setActivitySite(enabledTypeSports2.getActivitySite());
            enabledTypeSports.setSessionType(enabledTypeSports2.getSessionType());
            arrayList.add(enabledTypeSports);
        }
        activitySession.setEnabledTypes(arrayList);
        sUserDeviceSettingsReq.setActivitySession(activitySession);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new n(coveApiListener));
    }

    public static void e(@NonNull SaveAmbientSoundSettingsReq saveAmbientSoundSettingsReq, @NonNull CoveApiListener<SaveAmbientSoundSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        AmbientSoundSettings ambientSoundSettings = new AmbientSoundSettings();
        if (saveAmbientSoundSettingsReq != null && saveAmbientSoundSettingsReq.getAmbientSoundSettings() != null) {
            ambientSoundSettings.setActive(saveAmbientSoundSettingsReq.getAmbientSoundSettings().isActive());
            sUserDeviceSettingsReq.setAmbientSoundSettings(ambientSoundSettings);
        }
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new q(coveApiListener));
    }

    public static void f(@NonNull SaveAutoActivityRecognizeReq saveAutoActivityRecognizeReq, @NonNull CoveApiListener<SaveAutoRecognizeSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        AutoRecognize autoRecognize = new AutoRecognize();
        if (saveAutoActivityRecognizeReq.getAutoRecognize() != null) {
            if (saveAutoActivityRecognizeReq.getAutoRecognize().getActivities() != null && !saveAutoActivityRecognizeReq.getAutoRecognize().getActivities().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < saveAutoActivityRecognizeReq.getAutoRecognize().getActivities().size(); i2++) {
                    AutoRecognizeActivity autoRecognizeActivity = new AutoRecognizeActivity();
                    autoRecognizeActivity.setType(saveAutoActivityRecognizeReq.getAutoRecognize().getActivities().get(i2).getType());
                    autoRecognizeActivity.setCode(saveAutoActivityRecognizeReq.getAutoRecognize().getActivities().get(i2).getCode());
                    arrayList.add(autoRecognizeActivity);
                }
                autoRecognize.setActivities(arrayList);
            }
            autoRecognize.setWeekDays(saveAutoActivityRecognizeReq.getAutoRecognize().getWeekDays());
            if (saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule() != null) {
                AutoRecognizeSchedule autoRecognizeSchedule = new AutoRecognizeSchedule();
                autoRecognizeSchedule.setActive(saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().isActive());
                if (saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().getSlots() != null && !saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().getSlots().isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().getSlots().size(); i3++) {
                        AutoRecognizeSlot autoRecognizeSlot = new AutoRecognizeSlot();
                        autoRecognizeSlot.setStartTime(saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().getSlots().get(i3).getStartTime());
                        autoRecognizeSlot.setEndTime(saveAutoActivityRecognizeReq.getAutoRecognize().getSchedule().getSlots().get(i3).getEndTime());
                        arrayList2.add(autoRecognizeSlot);
                    }
                    autoRecognizeSchedule.setSlots(arrayList2);
                }
                autoRecognize.setSchedule(autoRecognizeSchedule);
            }
        }
        sUserDeviceSettingsReq.setAutoRecognize(autoRecognize);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new m(coveApiListener));
    }

    public static void g(@NonNull SaveAutoSpo2SettingsReq saveAutoSpo2SettingsReq, @NonNull CoveApiListener<SaveAutoSpo2SettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        AutoSpo2Settings autoSpo2Settings = new AutoSpo2Settings();
        if (saveAutoSpo2SettingsReq != null && saveAutoSpo2SettingsReq.getAutoSPO2Settings() != null) {
            autoSpo2Settings.setActive(saveAutoSpo2SettingsReq.getAutoSPO2Settings().isActive());
            sUserDeviceSettingsReq.setAutoSPO2Settings(autoSpo2Settings);
        }
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new a(coveApiListener));
    }

    public static void get4hButtonSupportedItems(CoveApiListener<GetAppConfigRes, CoveApiErrorModel> coveApiListener, String str, String str2) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str, str2);
    }

    public static void getAllUserDeviceSettings(CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getSmartAlertApps(String str, @NonNull CoveApiListener<SGetSmartAlertAppsData, CoveApiErrorModel> coveApiListener) {
        q(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(@NonNull SaveAutoStressSettingsReq saveAutoStressSettingsReq, @NonNull CoveApiListener<SaveAutoStressSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        AutoStress autoStress = new AutoStress();
        autoStress.setActive(saveAutoStressSettingsReq.getActive());
        autoStress.setInterval(saveAutoStressSettingsReq.getInterval());
        if (saveAutoStressSettingsReq.getAlert() != null) {
            AutoStressAlert autoStressAlert = new AutoStressAlert();
            autoStressAlert.setActive(saveAutoStressSettingsReq.getAlert().getActive());
            autoStressAlert.setEndTime(saveAutoStressSettingsReq.getAlert().getEndTime());
            autoStressAlert.setInterval(saveAutoStressSettingsReq.getAlert().getInterval());
            autoStressAlert.setStartTime(saveAutoStressSettingsReq.getAlert().getStartTime());
            autoStressAlert.setWeekDays(saveAutoStressSettingsReq.getAlert().getWeekDays());
            autoStressAlert.setRepeat(saveAutoStressSettingsReq.getAlert().getRepeat());
            autoStress.setAlert(autoStressAlert);
        }
        sUserDeviceSettingsReq.setAutoStress(autoStress);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new l(coveApiListener));
    }

    public static void i(@NonNull SaveCustomMessageSettingsReq saveCustomMessageSettingsReq, @NonNull CoveApiListener<SaveCustomMessageSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        SUserDeviceSettingsReq.CustomMessages customMessages = new SUserDeviceSettingsReq.CustomMessages();
        SUserDeviceSettingsReq.CustomMessages.NudgeMessages nudgeMessages = new SUserDeviceSettingsReq.CustomMessages.NudgeMessages();
        SUserDeviceSettingsReq.CustomMessages.NudgeMessageVibration nudgeMessageVibration = new SUserDeviceSettingsReq.CustomMessages.NudgeMessageVibration();
        nudgeMessages.setActive(saveCustomMessageSettingsReq.getNudgeMessages().getNudges().isActive());
        nudgeMessageVibration.setActive(saveCustomMessageSettingsReq.getNudgeMessages().getVibration().isActive());
        customMessages.setNudgeMessages(nudgeMessages);
        customMessages.setNudgeMessageVibration(nudgeMessageVibration);
        sUserDeviceSettingsReq.setCustomMessages(customMessages);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new k(coveApiListener));
    }

    public static void j(@NonNull SaveCustomRemindersSettingsReq saveCustomRemindersSettingsReq, @NonNull CoveApiListener<SaveCustomRemindersSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        SUserDeviceSettingsReq.CustomReminders customReminders = new SUserDeviceSettingsReq.CustomReminders();
        ArrayList arrayList = new ArrayList();
        customReminders.setTzOffset(saveCustomRemindersSettingsReq.getCustomReminders().getTzOffset());
        for (SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem customRemindersListItem : saveCustomRemindersSettingsReq.getCustomReminders().getCustomReminderList()) {
            SUserDeviceSettingsReq.CustomReminders.CustomReminderListItem customReminderListItem = new SUserDeviceSettingsReq.CustomReminders.CustomReminderListItem();
            customReminderListItem.setReminderId(customRemindersListItem.getReminderId());
            customReminderListItem.setActive(customRemindersListItem.isActive());
            customReminderListItem.setType(customRemindersListItem.getType());
            customReminderListItem.setLabel(customRemindersListItem.getLabel());
            customReminderListItem.setStartDate(customRemindersListItem.getStartDate());
            customReminderListItem.setEndDate(customRemindersListItem.getEndDate());
            customReminderListItem.setRemindAt(customRemindersListItem.getRemindAt());
            if (customRemindersListItem.getRemindBefore() != null) {
                customReminderListItem.setRemindBefore(customRemindersListItem.getRemindBefore());
            }
            customReminderListItem.setStartTime(customRemindersListItem.getStartTime());
            customReminderListItem.setEndTime(customRemindersListItem.getEndTime());
            customReminderListItem.setInterval(customRemindersListItem.getInterval());
            customReminderListItem.setWeekDays(customRemindersListItem.getWeekDays());
            if (customRemindersListItem.getFrequency() != null) {
                customReminderListItem.setFrequency(customRemindersListItem.getFrequency());
            }
            arrayList.add(customReminderListItem);
        }
        customReminders.setCustomReminderListItems(arrayList);
        sUserDeviceSettingsReq.setCustomReminders(customReminders);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new j(coveApiListener));
    }

    public static void k(@NonNull SaveMapNavigationSettingsReq saveMapNavigationSettingsReq, @NonNull CoveApiListener<SaveMapNavigationSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        MapNavigationSettings mapNavigationSettings = new MapNavigationSettings();
        MapNavigationSettings.AodSettings aodSettings = new MapNavigationSettings.AodSettings();
        aodSettings.setActive(saveMapNavigationSettingsReq.getMapNavigationSettings().getAodSettings().isActive());
        MapNavigationSettings.AudioSettings audioSettings = new MapNavigationSettings.AudioSettings();
        audioSettings.setActive(saveMapNavigationSettingsReq.getMapNavigationSettings().getAudioSettings().isActive());
        MapNavigationSettings.VibrationSettings vibrationSettings = new MapNavigationSettings.VibrationSettings();
        vibrationSettings.setActive(saveMapNavigationSettingsReq.getMapNavigationSettings().getVibrationSettings().isActive());
        mapNavigationSettings.setAodSettings(aodSettings);
        mapNavigationSettings.setAudioSettings(audioSettings);
        mapNavigationSettings.setVibrationSettings(vibrationSettings);
        sUserDeviceSettingsReq.setMapNavigationSettings(mapNavigationSettings);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new e(coveApiListener));
    }

    public static void l(@NonNull SaveRespiratoryRateSettingsReq saveRespiratoryRateSettingsReq, @NonNull CoveApiListener<SaveRespiratoryRateSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        RespiratoryRateSettings respiratoryRateSettings = new RespiratoryRateSettings();
        if (saveRespiratoryRateSettingsReq != null && saveRespiratoryRateSettingsReq.getRespiratoryRateSettings() != null) {
            respiratoryRateSettings.setActive(saveRespiratoryRateSettingsReq.getRespiratoryRateSettings().isActive());
            sUserDeviceSettingsReq.setRespiratoryRateSettings(respiratoryRateSettings);
        }
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new r(coveApiListener));
    }

    public static void m(@NonNull SaveSedentaryAlertDeviceSettingsReq saveSedentaryAlertDeviceSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertDeviceSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean = new SedentaryAlertUserDeviceSettingsBean();
        if (saveSedentaryAlertDeviceSettingsReq != null && saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean() != null) {
            sedentaryAlertUserDeviceSettingsBean.setActive(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().isActive());
            sedentaryAlertUserDeviceSettingsBean.setStartTime(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getStartTime());
            sedentaryAlertUserDeviceSettingsBean.setEndTime(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getEndTime());
            sedentaryAlertUserDeviceSettingsBean.setInterval(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getInterval());
            sedentaryAlertUserDeviceSettingsBean.setRepeat(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().isRepeat());
            sedentaryAlertUserDeviceSettingsBean.setWeekDays(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getWeekDays());
            if (saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getSiesta() != null) {
                SedentaryAlertUserDeviceSettingsBean.Siesta siesta = new SedentaryAlertUserDeviceSettingsBean.Siesta();
                siesta.setActive(saveSedentaryAlertDeviceSettingsReq.getSedentaryAlertBean().getSiesta().isActive());
                sedentaryAlertUserDeviceSettingsBean.setSiesta(siesta);
            }
            sUserDeviceSettingsReq.setSedentaryAlertUserDeviceSettingsBean(sedentaryAlertUserDeviceSettingsBean);
        }
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new p(coveApiListener));
    }

    public static void n(@NonNull SaveSmartAlertSettingsReq saveSmartAlertSettingsReq, @NonNull CoveApiListener<SaveSmartAlertSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        sUserDeviceSettingsReq.setSmartAlertSettings(saveSmartAlertSettingsReq.getSmartAlertSettings());
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new f(coveApiListener));
    }

    public static void o(@NonNull SOSSettings sOSSettings, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        sUserDeviceSettingsReq.setSosSettings(sOSSettings);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new h(coveApiListener));
    }

    public static void p(SaveVitalCardsSequenceReq saveVitalCardsSequenceReq, @NonNull CoveApiListener<SaveVitalCardSequenceRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        sUserDeviceSettingsReq.setAppDashboard(saveVitalCardsSequenceReq.getAppDashboard());
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new b(coveApiListener));
    }

    public static void q(String str, @NonNull CoveApiListener<SGetSmartAlertAppsData, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getSmartAlertApps(map, str).enqueue(new g(coveApiListener));
    }

    public static void r(@NonNull List<ContactsSettings> list, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        sUserDeviceSettingsReq.setContactsSettings(list);
        CoveApi.getService().saveUserDeviceSettings(map, sUserDeviceSettingsReq).enqueue(new i(coveApiListener));
    }

    public static void save4hButtonActions(@NonNull Save4hButtonActionItemsReq save4hButtonActionItemsReq, @NonNull CoveApiListener<Save4hButtonActionsRes, CoveApiErrorModel> coveApiListener) {
        c(save4hButtonActionItemsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveActivitySessionSettings(@NonNull SaveActivitySessionReq saveActivitySessionReq, @NonNull CoveApiListener<SaveActivitySessionRes, CoveApiErrorModel> coveApiListener) {
        d(saveActivitySessionReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAmbientSoundSettings(@NonNull SaveAmbientSoundSettingsReq saveAmbientSoundSettingsReq, @NonNull CoveApiListener<SaveAmbientSoundSettingsRes, CoveApiErrorModel> coveApiListener) {
        e(saveAmbientSoundSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAutoRecognizeSettings(@NonNull SaveAutoActivityRecognizeReq saveAutoActivityRecognizeReq, @NonNull CoveApiListener<SaveAutoRecognizeSettingsRes, CoveApiErrorModel> coveApiListener) {
        f(saveAutoActivityRecognizeReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAutoSpo2Settings(@NonNull SaveAutoSpo2SettingsReq saveAutoSpo2SettingsReq, @NonNull CoveApiListener<SaveAutoSpo2SettingsRes, CoveApiErrorModel> coveApiListener) {
        g(saveAutoSpo2SettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAutoStressSettings(@NonNull SaveAutoStressSettingsReq saveAutoStressSettingsReq, @NonNull CoveApiListener<SaveAutoStressSettingsRes, CoveApiErrorModel> coveApiListener) {
        h(saveAutoStressSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveContactsSettings(@NonNull List<ContactsSettings> list, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        r(list, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveCustomMessagesSettings(@NonNull SaveCustomMessageSettingsReq saveCustomMessageSettingsReq, @NonNull CoveApiListener<SaveCustomMessageSettingsRes, CoveApiErrorModel> coveApiListener) {
        i(saveCustomMessageSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveCustomReminderSettings(@NonNull SaveCustomRemindersSettingsReq saveCustomRemindersSettingsReq, @NonNull CoveApiListener<SaveCustomRemindersSettingsRes, CoveApiErrorModel> coveApiListener) {
        j(saveCustomRemindersSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveMapNavigationSetting(HashMap<String, String> hashMap, @NonNull SaveMapNavigationSettingsReq saveMapNavigationSettingsReq, @NonNull CoveApiListener<SaveMapNavigationSettingsRes, CoveApiErrorModel> coveApiListener) {
        k(saveMapNavigationSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveMapNavigationSettings(@NonNull SaveMapNavigationSettingsReq saveMapNavigationSettingsReq, @NonNull CoveApiListener<SaveMapNavigationSettingsRes, CoveApiErrorModel> coveApiListener) {
        k(saveMapNavigationSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveRespiratoryRateSettings(@NonNull SaveRespiratoryRateSettingsReq saveRespiratoryRateSettingsReq, @NonNull CoveApiListener<SaveRespiratoryRateSettingsRes, CoveApiErrorModel> coveApiListener) {
        l(saveRespiratoryRateSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSOSSettings(@NonNull SOSSettings sOSSettings, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        o(sOSSettings, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSedentarySettings(@NonNull SaveSedentaryAlertDeviceSettingsReq saveSedentaryAlertDeviceSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertDeviceSettingsRes, CoveApiErrorModel> coveApiListener) {
        m(saveSedentaryAlertDeviceSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSmartAlertSetting(HashMap<String, String> hashMap, @NonNull SaveSmartAlertSettingsReq saveSmartAlertSettingsReq, @NonNull CoveApiListener<SaveSmartAlertSettingsRes, CoveApiErrorModel> coveApiListener) {
        n(saveSmartAlertSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSmartAlertSettings(@NonNull SaveSmartAlertSettingsReq saveSmartAlertSettingsReq, @NonNull CoveApiListener<SaveSmartAlertSettingsRes, CoveApiErrorModel> coveApiListener) {
        n(saveSmartAlertSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveVitalCardsSequence(SaveVitalCardsSequenceReq saveVitalCardsSequenceReq, @NonNull CoveApiListener<SaveVitalCardSequenceRes, CoveApiErrorModel> coveApiListener) {
        p(saveVitalCardsSequenceReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void get4hButtonSupportedItems(CoveApiListener<GetAppConfigRes, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap, String str, String str2) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str, str2);
    }

    public static void saveVitalCardsSequence(HashMap<String, String> hashMap, SaveVitalCardsSequenceReq saveVitalCardsSequenceReq, @NonNull CoveApiListener<SaveVitalCardSequenceRes, CoveApiErrorModel> coveApiListener) {
        p(saveVitalCardsSequenceReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getAllUserDeviceSettings(HashMap<String, String> hashMap, CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getSmartAlertApps(String str, HashMap<String, String> hashMap, @NonNull CoveApiListener<SGetSmartAlertAppsData, CoveApiErrorModel> coveApiListener) {
        q(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void save4hButtonActions(HashMap<String, String> hashMap, @NonNull Save4hButtonActionItemsReq save4hButtonActionItemsReq, @NonNull CoveApiListener<Save4hButtonActionsRes, CoveApiErrorModel> coveApiListener) {
        c(save4hButtonActionItemsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveActivitySessionSettings(HashMap<String, String> hashMap, @NonNull SaveActivitySessionReq saveActivitySessionReq, @NonNull CoveApiListener<SaveActivitySessionRes, CoveApiErrorModel> coveApiListener) {
        d(saveActivitySessionReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAmbientSoundSettings(HashMap<String, String> hashMap, @NonNull SaveAmbientSoundSettingsReq saveAmbientSoundSettingsReq, @NonNull CoveApiListener<SaveAmbientSoundSettingsRes, CoveApiErrorModel> coveApiListener) {
        e(saveAmbientSoundSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAutoRecognizeSettings(HashMap<String, String> hashMap, @NonNull SaveAutoActivityRecognizeReq saveAutoActivityRecognizeReq, @NonNull CoveApiListener<SaveAutoRecognizeSettingsRes, CoveApiErrorModel> coveApiListener) {
        f(saveAutoActivityRecognizeReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAutoSpo2Settings(HashMap<String, String> hashMap, @NonNull SaveAutoSpo2SettingsReq saveAutoSpo2SettingsReq, @NonNull CoveApiListener<SaveAutoSpo2SettingsRes, CoveApiErrorModel> coveApiListener) {
        g(saveAutoSpo2SettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAutoStressSettings(HashMap<String, String> hashMap, @NonNull SaveAutoStressSettingsReq saveAutoStressSettingsReq, @NonNull CoveApiListener<SaveAutoStressSettingsRes, CoveApiErrorModel> coveApiListener) {
        h(saveAutoStressSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveContactsSettings(HashMap<String, String> hashMap, @NonNull List<ContactsSettings> list, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        r(list, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveCustomMessagesSettings(HashMap<String, String> hashMap, @NonNull SaveCustomMessageSettingsReq saveCustomMessageSettingsReq, @NonNull CoveApiListener<SaveCustomMessageSettingsRes, CoveApiErrorModel> coveApiListener) {
        i(saveCustomMessageSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveCustomReminderSettings(HashMap<String, String> hashMap, @NonNull SaveCustomRemindersSettingsReq saveCustomRemindersSettingsReq, @NonNull CoveApiListener<SaveCustomRemindersSettingsRes, CoveApiErrorModel> coveApiListener) {
        j(saveCustomRemindersSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveRespiratoryRateSettings(HashMap<String, String> hashMap, @NonNull SaveRespiratoryRateSettingsReq saveRespiratoryRateSettingsReq, @NonNull CoveApiListener<SaveRespiratoryRateSettingsRes, CoveApiErrorModel> coveApiListener) {
        l(saveRespiratoryRateSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSOSSettings(HashMap<String, String> hashMap, @NonNull SOSSettings sOSSettings, @NonNull CoveApiListener<CommonResponseClass, CoveApiErrorModel> coveApiListener) {
        o(sOSSettings, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSedentarySettings(HashMap<String, String> hashMap, @NonNull SaveSedentaryAlertDeviceSettingsReq saveSedentaryAlertDeviceSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertDeviceSettingsRes, CoveApiErrorModel> coveApiListener) {
        m(saveSedentaryAlertDeviceSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
