package com.coveiot.coveaccess;

import android.os.Build;
import androidx.annotation.NonNull;
import com.coveiot.coveaccess.drowsiness.DrowsyEstimationReq;
import com.coveiot.coveaccess.drowsiness.SDrowsyEstimationPostRes;
import com.coveiot.coveaccess.drowsyfatiguestress.DrowsyFatigueStressDataReq;
import com.coveiot.coveaccess.drowsyfatiguestress.SDrowsyFatigueStressDataPostRes;
import com.coveiot.coveaccess.feedback.FeedbackCategoriesRes;
import com.coveiot.coveaccess.feedback.PostFeedBackReq;
import com.coveiot.coveaccess.feedback.PostFeedBackRes;
import com.coveiot.coveaccess.fitness.model.ActivityRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CallQuickRepliesBean;
import com.coveiot.coveaccess.model.server.DNDBean;
import com.coveiot.coveaccess.model.server.DeviceConfigurationRes;
import com.coveiot.coveaccess.model.server.DualTimeBean;
import com.coveiot.coveaccess.model.server.LiftWristToViewBean;
import com.coveiot.coveaccess.model.server.MensturationBean;
import com.coveiot.coveaccess.model.server.RRateResponse;
import com.coveiot.coveaccess.model.server.RRateTrainingRequest;
import com.coveiot.coveaccess.model.server.SAllUserAppSettingsRes;
import com.coveiot.coveaccess.model.server.SConsentRequest;
import com.coveiot.coveaccess.model.server.SDrowbsinessFeedbackReq;
import com.coveiot.coveaccess.model.server.SFeedBackCategories;
import com.coveiot.coveaccess.model.server.SGetConsentData;
import com.coveiot.coveaccess.model.server.SGetUserDetails;
import com.coveiot.coveaccess.model.server.SPostFeedbackToServer;
import com.coveiot.coveaccess.model.server.SSoftwareUpdateReq;
import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
import com.coveiot.coveaccess.model.server.Snooze;
import com.coveiot.coveaccess.model.server.WeatherForecastBean;
import com.coveiot.coveaccess.spo2.SPO2EstimationReq;
import com.coveiot.coveaccess.spo2.SSPO2EstimationPostRes;
import com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingRes;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAutoHRSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveAutoTemperatureSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAutoTemperatureSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsReq;
import com.coveiot.coveaccess.userappsetting.SaveDeviceSpecificParamsRes;
import com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDrinkReminderSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveDualTimeZoneSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDualTimeZoneSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveLapSettingRes;
import com.coveiot.coveaccess.userappsetting.SaveLapSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveNotificationSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveQuickReplySettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveQuickReplySettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveSDCTSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveSDCTSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveScheduleEventRes;
import com.coveiot.coveaccess.userappsetting.SaveScheduleEventsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveUserSleepSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveUserSleepSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveWeatherSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveWeatherSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessReq;
import com.coveiot.coveaccess.userappsetting.SaveWomenWellnessRes;
import com.coveiot.coveaccess.userappsetting.SoftwarUpdateReq;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveUserAppSettings {

    /* loaded from: classes8.dex */
    public static class a implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6325a;

        public a(CoveApiListener coveApiListener) {
            this.f6325a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6325a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6325a.onSuccess(new SaveSedentaryAlertSettingsRes(response.code()));
            } else {
                this.f6325a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class a0 implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6326a;

        public a0(CoveApiListener coveApiListener) {
            this.f6326a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6326a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6326a.onSuccess(new SaveDualTimeZoneSettingsRes(response.code()));
            } else {
                this.f6326a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6327a;

        public b(CoveApiListener coveApiListener) {
            this.f6327a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6327a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6327a.onSuccess(new SaveLiftWristToViewSettingsRes(response.code()));
            } else {
                this.f6327a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b0 implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6328a;

        public b0(CoveApiListener coveApiListener) {
            this.f6328a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6328a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6328a.onSuccess(new SaveScheduleEventRes(response.code()));
            } else {
                this.f6328a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6329a;

        public c(CoveApiListener coveApiListener) {
            this.f6329a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6329a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6329a.onSuccess(new SaveDrinkReminderSettingsRes(response.code()));
            } else {
                this.f6329a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class c0 implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6330a;

        public c0(CoveApiListener coveApiListener) {
            this.f6330a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6330a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6330a.onSuccess(new SaveDNDSettingsRes(response.code()));
            } else {
                this.f6330a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6331a;

        public d(CoveApiListener coveApiListener) {
            this.f6331a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6331a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6331a.onSuccess(new SaveWeatherSettingsRes(response.code()));
            } else {
                this.f6331a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class d0 implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6332a;

        public d0(CoveApiListener coveApiListener) {
            this.f6332a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6332a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6332a.onSuccess(new SaveWomenWellnessRes(response.code()));
            } else {
                this.f6332a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6333a;

        public e(CoveApiListener coveApiListener) {
            this.f6333a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6333a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6333a.onSuccess(new SaveNotificationSettingsRes(response.code()));
            } else {
                this.f6333a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<SAllUserAppSettingsRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6334a;

        public f(CoveApiListener coveApiListener) {
            this.f6334a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SAllUserAppSettingsRes> call, Throwable th) {
            this.f6334a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SAllUserAppSettingsRes> call, Response<SAllUserAppSettingsRes> response) {
            if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                SAllUserAppSettingsRes body = response.body();
                GetAllUserAppSettingsRes getAllUserAppSettingsRes = new GetAllUserAppSettingsRes();
                SAllUserAppSettingsRes.DataBean.AlarmBean alarm = body.getData().getAlarm();
                if (alarm != null) {
                    GetAllUserAppSettingsRes.AlarmBean alarmBean = new GetAllUserAppSettingsRes.AlarmBean();
                    Snooze snooze = new Snooze();
                    ArrayList arrayList = new ArrayList();
                    for (SAllUserAppSettingsRes.DataBean.AlarmBean.ListBean listBean : alarm.getList()) {
                        GetAllUserAppSettingsRes.AlarmBean.ListBean listBean2 = new GetAllUserAppSettingsRes.AlarmBean.ListBean();
                        listBean2.setActive(listBean.isActive());
                        listBean2.setLabel(listBean.getLabel());
                        listBean2.setAlarmId(listBean.getAlarmId());
                        listBean2.setTime(listBean.getTime());
                        listBean2.setRepeat(listBean.isRepeat());
                        listBean2.setWeek(listBean.getWeek());
                        listBean2.setType(listBean.getType());
                        arrayList.add(listBean2);
                    }
                    if (alarm.getSnooze() != null && alarm.getSnooze().getInterval() != null && alarm.getSnooze().getMaxAllowed() != null) {
                        snooze.setInterval(alarm.getSnooze().getInterval());
                        snooze.setMaxAllowed(alarm.getSnooze().getMaxAllowed());
                        alarmBean.setSnooze(snooze);
                    }
                    alarmBean.setList(arrayList);
                    alarmBean.setActive(alarm.getActive());
                    getAllUserAppSettingsRes.setAlarm(alarmBean);
                }
                MensturationBean mensturationBean = body.getData().getMensturationBean();
                if (mensturationBean != null) {
                    MensturationBean mensturationBean2 = new MensturationBean();
                    ArrayList arrayList2 = new ArrayList();
                    if (mensturationBean.getReminders() != null && mensturationBean.getReminders().size() != 0) {
                        for (MensturationBean.Reminder reminder : mensturationBean.getReminders()) {
                            MensturationBean.Reminder reminder2 = new MensturationBean.Reminder();
                            reminder2.setType(reminder.getType());
                            reminder2.setRemindBefore(reminder.getRemindBefore());
                            reminder2.setRemindAt(reminder.getRemindAt());
                            reminder2.setActive(reminder.getActive());
                            arrayList2.add(reminder2);
                        }
                        mensturationBean2.setReminders(arrayList2);
                    }
                    mensturationBean2.setActive(mensturationBean.getActive());
                    mensturationBean2.setCycleStartDate(mensturationBean.getCycleStartDate());
                    mensturationBean2.setPmsLength(mensturationBean.getPmsLength());
                    mensturationBean2.setPeriodLength(mensturationBean.getPeriodLength());
                    mensturationBean2.setCycleLength(mensturationBean.getCycleLength());
                    getAllUserAppSettingsRes.setMensturationBean(mensturationBean2);
                }
                DNDBean dnd = body.getData().getDnd();
                if (dnd != null) {
                    DNDBean dNDBean = new DNDBean();
                    ArrayList arrayList3 = new ArrayList();
                    if (dnd.getSchedules() != null && dnd.getSchedules().size() != 0) {
                        for (DNDBean.Schedule schedule : dnd.getSchedules()) {
                            DNDBean.Schedule schedule2 = new DNDBean.Schedule();
                            schedule2.setEndTime(schedule.getEndTime());
                            schedule2.setStartTime(schedule.getStartTime());
                            arrayList3.add(schedule2);
                        }
                        dNDBean.setSchedules(arrayList3);
                    }
                    dNDBean.setActive(dnd.getActive());
                    getAllUserAppSettingsRes.setDndBean(dNDBean);
                }
                CallQuickRepliesBean callQuickReplies = body.getData().getCallQuickReplies();
                if (callQuickReplies != null) {
                    CallQuickRepliesBean callQuickRepliesBean = new CallQuickRepliesBean();
                    if (callQuickReplies.getMessages() != null && callQuickReplies.getMessages().size() != 0) {
                        callQuickRepliesBean.setMessages(callQuickReplies.getMessages());
                    }
                    callQuickRepliesBean.setActive(callQuickReplies.getActive());
                    getAllUserAppSettingsRes.setCallQuickReplies(callQuickRepliesBean);
                }
                SAllUserAppSettingsRes.DataBean.SedentaryAlertBean sedentaryAlert = body.getData().getSedentaryAlert();
                if (sedentaryAlert != null) {
                    GetAllUserAppSettingsRes.SedentaryAlertBean sedentaryAlertBean = new GetAllUserAppSettingsRes.SedentaryAlertBean();
                    sedentaryAlertBean.setActive(sedentaryAlert.isActive());
                    sedentaryAlertBean.setStartTime(sedentaryAlert.getStartTime());
                    sedentaryAlertBean.setEndTime(sedentaryAlert.getEndTime());
                    sedentaryAlertBean.setInterval(sedentaryAlert.getInterval());
                    getAllUserAppSettingsRes.setSedentaryAlert(sedentaryAlertBean);
                }
                SAllUserAppSettingsRes.DataBean.DrinkReminderAlertBean drinkReminder = body.getData().getDrinkReminder();
                if (drinkReminder != null) {
                    GetAllUserAppSettingsRes.DrinkReminderAlertBean drinkReminderAlertBean = new GetAllUserAppSettingsRes.DrinkReminderAlertBean();
                    drinkReminderAlertBean.setActive(drinkReminder.isActive());
                    drinkReminderAlertBean.setStartTime(drinkReminder.getStartTime());
                    drinkReminderAlertBean.setEndTime(drinkReminder.getEndTime());
                    drinkReminderAlertBean.setInterval(drinkReminder.getInterval());
                    getAllUserAppSettingsRes.setDrinkReminderAlert(drinkReminderAlertBean);
                }
                SAllUserAppSettingsRes.DataBean.NotificationBean notification = body.getData().getNotification();
                if (notification != null) {
                    GetAllUserAppSettingsRes.NotificationBean notificationBean = new GetAllUserAppSettingsRes.NotificationBean();
                    if (notification.getCall() != null) {
                        GetAllUserAppSettingsRes.NotificationBean.CallBean callBean = new GetAllUserAppSettingsRes.NotificationBean.CallBean();
                        callBean.setActive(notification.getCall().isActive());
                        if (!CoveUtil.isEmpty(notification.getCall().getFavContacts())) {
                            ArrayList arrayList4 = new ArrayList();
                            for (SAllUserAppSettingsRes.DataBean.NotificationBean.CallBean.FavContactsBean favContactsBean : notification.getCall().getFavContacts()) {
                                GetAllUserAppSettingsRes.NotificationBean.CallBean.FavContactsBean favContactsBean2 = new GetAllUserAppSettingsRes.NotificationBean.CallBean.FavContactsBean();
                                favContactsBean2.setHourIndex(favContactsBean.getHourIndex());
                                favContactsBean2.setMobileNumber(favContactsBean.getMobileNumber());
                                favContactsBean2.setName(favContactsBean.getName());
                                arrayList4.add(favContactsBean2);
                            }
                            callBean.setFavContacts(arrayList4);
                        }
                        notificationBean.setCall(callBean);
                    }
                    if (notification.getOtherApps() != null) {
                        GetAllUserAppSettingsRes.NotificationBean.AppBean appBean = new GetAllUserAppSettingsRes.NotificationBean.AppBean();
                        appBean.setEnableAll(notification.getOtherApps().isEnableAll());
                        if (!CoveUtil.isEmpty(notification.getOtherApps().getApps())) {
                            ArrayList arrayList5 = new ArrayList();
                            for (SAllUserAppSettingsRes.DataBean.NotificationBean.AppBean.Apps apps : notification.getOtherApps().getApps()) {
                                GetAllUserAppSettingsRes.NotificationBean.AppBean.Apps apps2 = new GetAllUserAppSettingsRes.NotificationBean.AppBean.Apps();
                                apps2.setActive(apps.getActive());
                                apps2.setAppId(apps.getAppId());
                                arrayList5.add(apps2);
                            }
                            appBean.setApps(arrayList5);
                        }
                        if (!CoveUtil.isEmpty(notification.getOtherApps().getAndroidApps())) {
                            ArrayList arrayList6 = new ArrayList();
                            for (SAllUserAppSettingsRes.DataBean.NotificationBean.AppBean.AndroidApps androidApps : notification.getOtherApps().getAndroidApps()) {
                                GetAllUserAppSettingsRes.NotificationBean.AppBean.AndroidApps androidApps2 = new GetAllUserAppSettingsRes.NotificationBean.AppBean.AndroidApps();
                                androidApps2.setActive(androidApps.getActive());
                                androidApps2.setPackageX(androidApps.getPackageX());
                                arrayList6.add(androidApps2);
                            }
                            appBean.setAndroidApps(arrayList6);
                        }
                        notificationBean.setOtherApps(appBean);
                    }
                    if (notification.getSms() != null) {
                        GetAllUserAppSettingsRes.NotificationBean.SmsBean smsBean = new GetAllUserAppSettingsRes.NotificationBean.SmsBean();
                        smsBean.setActive(notification.getSms().isActive());
                        notificationBean.setSms(smsBean);
                    }
                    if (notification.getDnd() != null) {
                        GetAllUserAppSettingsRes.NotificationBean.DndBean dndBean = new GetAllUserAppSettingsRes.NotificationBean.DndBean();
                        dndBean.setActive(notification.getDnd().isActive());
                        notificationBean.setDnd(dndBean);
                    }
                    getAllUserAppSettingsRes.setNotification(notificationBean);
                }
                SAllUserAppSettingsRes.DataBean.SleepBean sleep = body.getData().getSleep();
                if (sleep != null) {
                    GetAllUserAppSettingsRes.SleepBean sleepBean = new GetAllUserAppSettingsRes.SleepBean();
                    sleepBean.setActive(sleep.isActive());
                    SAllUserAppSettingsRes.DataBean.SleepBean.SleepTimeBean sleepTime = sleep.getSleepTime();
                    if (sleepTime != null) {
                        GetAllUserAppSettingsRes.SleepBean.SleepTimeBean sleepTimeBean = new GetAllUserAppSettingsRes.SleepBean.SleepTimeBean();
                        sleepTimeBean.setActive(sleepTime.isActive());
                        sleepTimeBean.setStartTime(sleepTime.getStartTime());
                        sleepTimeBean.setEndTime(sleepTime.getEndTime());
                        sleepTimeBean.setNotifyActive(sleepTime.isNotifyActive());
                        sleepTimeBean.setNotifyTime(sleepTime.getNotifyTime());
                        sleepBean.setSleepTime(sleepTimeBean);
                    }
                    if (sleep.getNapTime() != null) {
                        GetAllUserAppSettingsRes.SleepBean.NapTimeBean napTimeBean = new GetAllUserAppSettingsRes.SleepBean.NapTimeBean();
                        napTimeBean.setActive(sleepTime.isActive());
                        napTimeBean.setStartTime(sleepTime.getStartTime());
                        napTimeBean.setEndTime(sleepTime.getEndTime());
                        napTimeBean.setNotifyActive(sleepTime.isNotifyActive());
                        napTimeBean.setNotifyTime(sleepTime.getNotifyTime());
                        sleepBean.setNapTime(napTimeBean);
                    }
                    getAllUserAppSettingsRes.setSleep(sleepBean);
                }
                SAllUserAppSettingsRes.DataBean.DeviceSpecificParamsBean deviceSpecificParams = body.getData().getDeviceSpecificParams();
                if (deviceSpecificParams != null) {
                    GetAllUserAppSettingsRes.DeviceSpecificParamsBean deviceSpecificParamsBean = new GetAllUserAppSettingsRes.DeviceSpecificParamsBean();
                    deviceSpecificParamsBean.setActiveDisplays(deviceSpecificParams.getActiveDisplays());
                    deviceSpecificParamsBean.setScreenOrientation(deviceSpecificParams.getScreenOrientation());
                    deviceSpecificParamsBean.setWearingOn(deviceSpecificParams.getWearingOn());
                    deviceSpecificParamsBean.setClockFormat(deviceSpecificParams.getClockFormat());
                    deviceSpecificParamsBean.setLiftWristToView(deviceSpecificParams.getLiftWristToView());
                    deviceSpecificParamsBean.setDistanceUnit(deviceSpecificParams.getDistanceUnit());
                    deviceSpecificParamsBean.setTemperatureUnit(deviceSpecificParams.getTemperatureUnit());
                    deviceSpecificParamsBean.setCurrentWatchFace(deviceSpecificParams.getCurrentWatchFace());
                    deviceSpecificParamsBean.setBpCalibration(deviceSpecificParams.getBpCalibration());
                    getAllUserAppSettingsRes.setDeviceSpecificParams(deviceSpecificParamsBean);
                }
                SAllUserAppSettingsRes.DataBean.AutoHr autoHr = body.getData().getAutoHr();
                if (autoHr != null) {
                    GetAllUserAppSettingsRes.AutoHr autoHr2 = new GetAllUserAppSettingsRes.AutoHr();
                    autoHr2.setActive(autoHr.getActive());
                    autoHr2.setInterval(autoHr.getInterval());
                    getAllUserAppSettingsRes.setAutoHr(autoHr2);
                }
                SAllUserAppSettingsRes.DataBean.AutoTemperature autoTemperature = body.getData().getAutoTemperature();
                if (autoTemperature != null) {
                    GetAllUserAppSettingsRes.AutoTemperature autoTemperature2 = new GetAllUserAppSettingsRes.AutoTemperature();
                    autoTemperature2.setActive(autoTemperature.getActive());
                    autoTemperature2.setInterval(autoTemperature.getInterval());
                    getAllUserAppSettingsRes.setAutoTemperature(autoTemperature2);
                }
                SAllUserAppSettingsRes.DataBean.SocialDistancingBean socialDistancing = body.getData().getSocialDistancing();
                if (socialDistancing != null) {
                    GetAllUserAppSettingsRes.SocialDistancingBean socialDistancingBean = new GetAllUserAppSettingsRes.SocialDistancingBean();
                    socialDistancingBean.setActive(socialDistancing.getActive());
                    getAllUserAppSettingsRes.setSocialDistancing(socialDistancingBean);
                }
                SAllUserAppSettingsRes.DataBean.ContactTracingBean contactTracing = body.getData().getContactTracing();
                if (contactTracing != null) {
                    GetAllUserAppSettingsRes.ContactTracingBean contactTracingBean = new GetAllUserAppSettingsRes.ContactTracingBean();
                    contactTracingBean.setActive(contactTracing.getActive());
                    getAllUserAppSettingsRes.setContactTracing(contactTracingBean);
                }
                if (body.getData().getCalender() != null) {
                    getAllUserAppSettingsRes.setCalendarBean(body.getData().getCalender());
                }
                LiftWristToViewBean liftWristToView = body.getData().getLiftWristToView();
                if (liftWristToView != null) {
                    LiftWristToViewBean liftWristToViewBean = new LiftWristToViewBean();
                    liftWristToViewBean.setActive(liftWristToView.getActive());
                    liftWristToViewBean.setStartTime(liftWristToView.getStartTime());
                    liftWristToViewBean.setEndTime(liftWristToView.getEndTime());
                    getAllUserAppSettingsRes.setLiftWristToView(liftWristToViewBean);
                }
                WeatherForecastBean weatherForecast = body.getData().getWeatherForecast();
                if (weatherForecast != null) {
                    getAllUserAppSettingsRes.setWeatherForecast(weatherForecast);
                }
                DualTimeBean dualTimeBean = body.getData().getDualTimeBean();
                if (dualTimeBean != null) {
                    getAllUserAppSettingsRes.setDualTimeBean(dualTimeBean);
                }
                this.f6334a.onSuccess(getAllUserAppSettingsRes);
                return;
            }
            this.f6334a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6335a;

        public g(CoveApiListener coveApiListener) {
            this.f6335a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6335a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6335a.onSuccess(new SaveUserSleepSettingsRes(response.code()));
            } else {
                this.f6335a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<SFeedBackCategories> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6336a;

        public h(CoveApiListener coveApiListener) {
            this.f6336a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SFeedBackCategories> call, Throwable th) {
            this.f6336a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SFeedBackCategories> call, Response<SFeedBackCategories> response) {
            if (response.isSuccessful() && response.body() != null) {
                SFeedBackCategories body = response.body();
                if (body.getData() != null && !CoveUtil.isEmpty(body.getData().getFields())) {
                    ArrayList arrayList = new ArrayList();
                    for (SFeedBackCategories.DataBean.FieldsBean fieldsBean : body.getData().getFields()) {
                        FeedbackCategoriesRes.FieldsBean fieldsBean2 = new FeedbackCategoriesRes.FieldsBean();
                        fieldsBean2.setName(fieldsBean.getName());
                        fieldsBean2.setType(fieldsBean.getType());
                        fieldsBean2.setRequired(fieldsBean.isRequired());
                        ArrayList arrayList2 = new ArrayList();
                        for (SFeedBackCategories.DataBean.FieldsBean.OptionsBean optionsBean : fieldsBean.getOptions()) {
                            FeedbackCategoriesRes.FieldsBean.OptionsBean optionsBean2 = new FeedbackCategoriesRes.FieldsBean.OptionsBean();
                            optionsBean2.setText(optionsBean.getText());
                            optionsBean2.setValue(optionsBean.getValue());
                            arrayList2.add(optionsBean2);
                        }
                        fieldsBean2.setOptions(arrayList2);
                        arrayList.add(fieldsBean2);
                    }
                    FeedbackCategoriesRes feedbackCategoriesRes = new FeedbackCategoriesRes();
                    feedbackCategoriesRes.setFields(arrayList);
                    this.f6336a.onSuccess(feedbackCategoriesRes);
                    return;
                }
                this.f6336a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
                return;
            }
            this.f6336a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6337a;

        public i(CoveApiListener coveApiListener) {
            this.f6337a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6337a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6337a.onSuccess(new SaveDeviceSpecificParamsRes(response.code()));
            } else {
                this.f6337a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6338a;

        public j(CoveApiListener coveApiListener) {
            this.f6338a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6338a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6338a.onSuccess(new PostFeedBackRes(response.code()));
            } else {
                this.f6338a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<SoftwareUpdateRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6339a;

        public k(CoveApiListener coveApiListener) {
            this.f6339a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SoftwareUpdateRes> call, Throwable th) {
            this.f6339a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SoftwareUpdateRes> call, Response<SoftwareUpdateRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6339a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6339a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class l implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6340a;

        public l(CoveApiListener coveApiListener) {
            this.f6340a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6340a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6340a.onSuccess(new SaveAutoHRSettingsRes(response.code()));
            } else {
                this.f6340a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class m implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6341a;

        public m(CoveApiListener coveApiListener) {
            this.f6341a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6341a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6341a.onSuccess(new SaveAutoTemperatureSettingsRes(response.code()));
            } else {
                this.f6341a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class n implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6342a;

        public n(CoveApiListener coveApiListener) {
            this.f6342a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6342a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6342a.onSuccess(new SaveSDCTSettingsRes(response.code()));
            } else {
                this.f6342a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class o implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6343a;

        public o(CoveApiListener coveApiListener) {
            this.f6343a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6343a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6343a.onSuccess(new SaveAlarmSettingRes(response.code()));
            } else {
                this.f6343a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class p implements Callback<ActivityRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6344a;

        public p(CoveApiListener coveApiListener) {
            this.f6344a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ActivityRes> call, Throwable th) {
            this.f6344a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ActivityRes> call, Response<ActivityRes> response) {
            if (response.isSuccessful()) {
                this.f6344a.onSuccess(response.body());
            } else {
                this.f6344a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class q implements Callback<SGetConsentData> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6345a;

        public q(CoveApiListener coveApiListener) {
            this.f6345a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetConsentData> call, Throwable th) {
            this.f6345a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetConsentData> call, Response<SGetConsentData> response) {
            if (response.isSuccessful()) {
                this.f6345a.onSuccess(response.body());
            } else {
                this.f6345a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class r implements Callback<DeviceConfigurationRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6346a;

        public r(CoveApiListener coveApiListener) {
            this.f6346a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<DeviceConfigurationRes> call, Throwable th) {
            this.f6346a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<DeviceConfigurationRes> call, Response<DeviceConfigurationRes> response) {
            if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                this.f6346a.onSuccess(response.body());
            } else {
                this.f6346a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class s implements Callback<SSPO2EstimationPostRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6347a;

        public s(CoveApiListener coveApiListener) {
            this.f6347a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SSPO2EstimationPostRes> call, Throwable th) {
            this.f6347a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SSPO2EstimationPostRes> call, Response<SSPO2EstimationPostRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6347a.onSuccess(response.body());
            } else {
                this.f6347a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class t implements Callback<SDrowsyEstimationPostRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6348a;

        public t(CoveApiListener coveApiListener) {
            this.f6348a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDrowsyEstimationPostRes> call, Throwable th) {
            this.f6348a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDrowsyEstimationPostRes> call, Response<SDrowsyEstimationPostRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6348a.onSuccess(response.body());
            } else {
                this.f6348a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class u implements Callback<JsonObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6349a;

        public u(CoveApiListener coveApiListener) {
            this.f6349a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JsonObject> call, Throwable th) {
            this.f6349a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            this.f6349a.onSuccess(response.body());
        }
    }

    /* loaded from: classes8.dex */
    public static class v implements Callback<RRateResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6350a;

        public v(CoveApiListener coveApiListener) {
            this.f6350a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<RRateResponse> call, Throwable th) {
            this.f6350a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<RRateResponse> call, Response<RRateResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6350a.onSuccess(response.body());
            } else {
                this.f6350a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class w implements Callback<SDrowsyFatigueStressDataPostRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6351a;

        public w(CoveApiListener coveApiListener) {
            this.f6351a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SDrowsyFatigueStressDataPostRes> call, Throwable th) {
            this.f6351a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SDrowsyFatigueStressDataPostRes> call, Response<SDrowsyFatigueStressDataPostRes> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6351a.onSuccess(response.body());
            } else {
                this.f6351a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class x implements Callback<SGetUserDetails> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6352a;

        public x(CoveApiListener coveApiListener) {
            this.f6352a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGetUserDetails> call, Throwable th) {
            this.f6352a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGetUserDetails> call, Response<SGetUserDetails> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6352a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6352a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class y implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6353a;

        public y(CoveApiListener coveApiListener) {
            this.f6353a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6353a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6353a.onSuccess(new SaveLapSettingRes(response.code()));
            } else {
                this.f6353a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class z implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6354a;

        public z(CoveApiListener coveApiListener) {
            this.f6354a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6354a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6354a.onSuccess(new SaveQuickReplySettingsRes(response.code()));
            } else {
                this.f6354a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
            }
        }
    }

    public static void A(@NonNull SoftwarUpdateReq softwarUpdateReq, @NonNull CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SSoftwareUpdateReq sSoftwareUpdateReq = new SSoftwareUpdateReq();
        ArrayList arrayList = new ArrayList();
        SSoftwareUpdateReq.DeviceBean deviceBean = new SSoftwareUpdateReq.DeviceBean();
        SSoftwareUpdateReq.DeviceBean.LocationBean locationBean = new SSoftwareUpdateReq.DeviceBean.LocationBean();
        SSoftwareUpdateReq.PlatformBean platformBean = new SSoftwareUpdateReq.PlatformBean();
        platformBean.setOsName("ANDROID");
        platformBean.setApiLevel(Build.VERSION.SDK_INT + "");
        platformBean.setOsVersion(Build.VERSION.RELEASE + "");
        List<SoftwarUpdateReq.CloveDeviceBean> coveDevices = softwarUpdateReq.getCoveDevices();
        if (coveDevices == null) {
            coveDevices = softwarUpdateReq.getCloveDeviceBeans();
        }
        for (int i2 = 0; i2 < coveDevices.size(); i2++) {
            SSoftwareUpdateReq.CloveDeviceBean cloveDeviceBean = new SSoftwareUpdateReq.CloveDeviceBean();
            cloveDeviceBean.setBtMacAddress(coveDevices.get(i2).getBtMacAddress());
            cloveDeviceBean.setCustomerId(coveDevices.get(i2).getCustomerId());
            cloveDeviceBean.setFirmwareVersion(coveDevices.get(i2).getFirmwareVersion());
            cloveDeviceBean.setHardwareVersion(coveDevices.get(i2).getHardwareVersion());
            cloveDeviceBean.setModelNumber(coveDevices.get(i2).getModelNumber());
            cloveDeviceBean.setSerialNumber(coveDevices.get(i2).getSerialNumber());
            arrayList.add(cloveDeviceBean);
        }
        locationBean.setLatitude(softwarUpdateReq.getDevice().getLocation().getLatitude());
        locationBean.setLongitude(softwarUpdateReq.getDevice().getLocation().getLongitude());
        deviceBean.setManufacturer(Build.MANUFACTURER);
        deviceBean.setModel(Build.MODEL);
        deviceBean.setLocation(locationBean);
        sSoftwareUpdateReq.setCloveDeviceBeans(arrayList);
        sSoftwareUpdateReq.setCoveDevices(arrayList);
        sSoftwareUpdateReq.setDevice(deviceBean);
        sSoftwareUpdateReq.setPlatform(platformBean);
        sSoftwareUpdateReq.setAppId(softwarUpdateReq.getAppId());
        sSoftwareUpdateReq.setAppVersion(softwarUpdateReq.getAppVersion());
        CoveApi.getService().softwareUpdate(map, sSoftwareUpdateReq).enqueue(new k(coveApiListener));
    }

    public static void B(String str, CoveApiListener<DeviceConfigurationRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getDeviceConfiguration(map, str).enqueue(new r(coveApiListener));
    }

    public static void C(@NonNull CoveApiListener<SGetConsentData, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getConsentDataFromServer(map).enqueue(new q(coveApiListener));
    }

    public static void D(CoveApiListener<FeedbackCategoriesRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getFeedbackCategories(map).enqueue(new h(coveApiListener));
    }

    public static void a(CoveApiListener<SGetUserDetails, CoveApiErrorModel> coveApiListener, HashMap<String, String> hashMap) {
        CoveApi.getService().getUserDetails(hashMap).enqueue(new x(coveApiListener));
    }

    public static void b(CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        Call<SAllUserAppSettingsRes> allUserAppSettings = CoveApi.getService().getAllUserAppSettings(map);
        if (CoveApi.getInstance().isEgApp()) {
            allUserAppSettings = CoveApi.getService().getAllEgUserAppSettings(map);
        }
        allUserAppSettings.enqueue(new f(coveApiListener));
    }

    public static void c(@NonNull DrowsyEstimationReq drowsyEstimationReq, CoveApiListener<SDrowsyEstimationPostRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().sendDrowsyEstimation(map, "http://cove-us-dev-drowsiness-aiml-srv.us-east-1.elasticbeanstalk.com//drowsy/estimation/", drowsyEstimationReq).enqueue(new t(coveApiListener));
    }

    public static void d(@NonNull DrowsyFatigueStressDataReq drowsyFatigueStressDataReq, CoveApiListener<SDrowsyFatigueStressDataPostRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().sendDrowsyFatigueStressData(map, "http://cove-us-dev-drowsiness-aiml-srv.us-east-1.elasticbeanstalk.com/drowsy/train/", drowsyFatigueStressDataReq).enqueue(new w(coveApiListener));
    }

    public static void e(@NonNull PostFeedBackReq postFeedBackReq, @NonNull CoveApiListener<PostFeedBackRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SPostFeedbackToServer sPostFeedbackToServer = new SPostFeedbackToServer();
        sPostFeedbackToServer.setEmailId(postFeedBackReq.getEmailId());
        sPostFeedbackToServer.setMessage(postFeedBackReq.getMessage());
        sPostFeedbackToServer.setSubject(postFeedBackReq.getSubject());
        sPostFeedbackToServer.setName(postFeedBackReq.getName());
        sPostFeedbackToServer.setContactPreference(postFeedBackReq.getContactPreference());
        CoveApi.getService().postFeedbackForm(map, sPostFeedbackToServer).enqueue(new j(coveApiListener));
    }

    public static void f(@NonNull RRateTrainingRequest rRateTrainingRequest, CoveApiListener<RRateResponse, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().sendRRateEstimation(map, "http://cove-us-dev-drowsiness-aiml-srv.us-east-1.elasticbeanstalk.com//rrate/estimation/", rRateTrainingRequest).enqueue(new v(coveApiListener));
    }

    public static void g(@NonNull SConsentRequest sConsentRequest, @NonNull CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().postConsentDataToServer(map, sConsentRequest).enqueue(new p(coveApiListener));
    }

    public static void getAllUserAppSettings(CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getDeviceConfiguration(String str, CoveApiListener<DeviceConfigurationRes, CoveApiErrorModel> coveApiListener) {
        B(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getFeedBackCategories(CoveApiListener<FeedbackCategoriesRes, CoveApiErrorModel> coveApiListener) {
        D(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getUserConsent(@NonNull CoveApiListener<SGetConsentData, CoveApiErrorModel> coveApiListener) {
        C(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getUserDetails(CoveApiListener<SGetUserDetails, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(@NonNull SDrowbsinessFeedbackReq sDrowbsinessFeedbackReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().sendDrowsyEstimationFeedback(map, "https://aiml.cove.kahaapi.com/v1/feedback/", sDrowbsinessFeedbackReq).enqueue(new u(coveApiListener));
    }

    public static void i(@NonNull SPO2EstimationReq sPO2EstimationReq, CoveApiListener<SSPO2EstimationPostRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApiService service = CoveApi.getService();
        service.sendSPO2Estimation(map, CoveApi.getInstance().getGateWayUrl() + "v1/spo2/estimation", sPO2EstimationReq).enqueue(new s(coveApiListener));
    }

    public static void j(@NonNull SaveAlarmSettingReq saveAlarmSettingReq, @NonNull CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.AlarmBean alarmBean = new SUserAppSettingsReq.AlarmBean();
        Snooze snooze = new Snooze();
        ArrayList arrayList = new ArrayList();
        for (SaveAlarmSettingReq.AlarmListBean alarmListBean : saveAlarmSettingReq.getAlarmListBeans()) {
            SUserAppSettingsReq.AlarmBean.ListBean listBean = new SUserAppSettingsReq.AlarmBean.ListBean();
            listBean.setActive(alarmListBean.isActive());
            listBean.setAlarmId(alarmListBean.getAlarmId());
            listBean.setLabel(alarmListBean.getLabel());
            listBean.setTime(alarmListBean.getTime());
            listBean.setRepeat(alarmListBean.isRepeat());
            listBean.setWeek(alarmListBean.getWeek());
            if (!CoveUtil.isEmpty(alarmListBean.getType())) {
                listBean.setType(alarmListBean.getType());
            }
            arrayList.add(listBean);
        }
        if (saveAlarmSettingReq.getSnooze() != null && saveAlarmSettingReq.getSnooze().getInterval() != null && saveAlarmSettingReq.getSnooze().getMaxAllowed() != null) {
            snooze.setInterval(saveAlarmSettingReq.getSnooze().getInterval());
            snooze.setMaxAllowed(saveAlarmSettingReq.getSnooze().getMaxAllowed());
            alarmBean.setSnooze(snooze);
        }
        alarmBean.setActive(saveAlarmSettingReq.getActive());
        alarmBean.setList(arrayList);
        sUserAppSettingsReq.setAlarm(alarmBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new o(coveApiListener));
    }

    public static void k(@NonNull SaveAutoHRSettingReq saveAutoHRSettingReq, @NonNull CoveApiListener<SaveAutoHRSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.AutoHr autoHr = new SUserAppSettingsReq.AutoHr();
        autoHr.setActive(saveAutoHRSettingReq.getActive());
        autoHr.setInterval(saveAutoHRSettingReq.getInterval());
        sUserAppSettingsReq.setAutoHr(autoHr);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new l(coveApiListener));
    }

    public static void l(@NonNull SaveAutoTemperatureSettingReq saveAutoTemperatureSettingReq, @NonNull CoveApiListener<SaveAutoTemperatureSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.AutoTemperature autoTemperature = new SUserAppSettingsReq.AutoTemperature();
        autoTemperature.setActive(saveAutoTemperatureSettingReq.getActive());
        autoTemperature.setInterval(saveAutoTemperatureSettingReq.getInterval());
        sUserAppSettingsReq.setAutoTemperature(autoTemperature);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new m(coveApiListener));
    }

    public static void m(@NonNull SaveDNDSettingsReq saveDNDSettingsReq, @NonNull CoveApiListener<SaveDNDSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        DNDBean dNDBean = new DNDBean();
        ArrayList arrayList = new ArrayList();
        for (SaveDNDSettingsReq.ScheduleDNDListBean scheduleDNDListBean : saveDNDSettingsReq.getDndListBean()) {
            DNDBean.Schedule schedule = new DNDBean.Schedule();
            schedule.setEndTime(scheduleDNDListBean.getEndTime());
            schedule.setStartTime(scheduleDNDListBean.getStartTime());
            arrayList.add(schedule);
        }
        if (arrayList.size() != 0) {
            dNDBean.setSchedules(arrayList);
        } else {
            dNDBean.setSchedules(null);
        }
        dNDBean.setActive(Boolean.valueOf(saveDNDSettingsReq.isActive()));
        sUserAppSettingsReq.setDnd(dNDBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new c0(coveApiListener));
    }

    public static void n(@NonNull SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq, @NonNull CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.DeviceSpecificParamsBean deviceSpecificParamsBean = new SUserAppSettingsReq.DeviceSpecificParamsBean();
        if (saveDeviceSpecificParamsReq.getActiveDisplays() != null) {
            deviceSpecificParamsBean.setActiveDisplays(saveDeviceSpecificParamsReq.getActiveDisplays());
        }
        if (saveDeviceSpecificParamsReq.getScreenOrientation() != null) {
            deviceSpecificParamsBean.setScreenOrientation(saveDeviceSpecificParamsReq.getScreenOrientation());
        }
        if (saveDeviceSpecificParamsReq.getWearingOn() != null) {
            deviceSpecificParamsBean.setWearingOn(saveDeviceSpecificParamsReq.getWearingOn());
        }
        if (saveDeviceSpecificParamsReq.getClockFormat() != null) {
            deviceSpecificParamsBean.setClockFormat(saveDeviceSpecificParamsReq.getClockFormat());
        }
        if (saveDeviceSpecificParamsReq.getDistanceUnit() != null) {
            deviceSpecificParamsBean.setDistanceUnit(saveDeviceSpecificParamsReq.getDistanceUnit());
        }
        if (saveDeviceSpecificParamsReq.getTemperatureUnit() != null) {
            deviceSpecificParamsBean.setTemperatureUnit(saveDeviceSpecificParamsReq.getTemperatureUnit());
        }
        if (saveDeviceSpecificParamsReq.getBpCalibration() != null) {
            deviceSpecificParamsBean.setBpCalibration(saveDeviceSpecificParamsReq.getBpCalibration());
        }
        if (saveDeviceSpecificParamsReq.getCurrentWatchFace() != null) {
            deviceSpecificParamsBean.setCurrentWatchFace(saveDeviceSpecificParamsReq.getCurrentWatchFace());
        }
        deviceSpecificParamsBean.setLiftWristToView(Boolean.valueOf(saveDeviceSpecificParamsReq.isLiftWristToView()));
        sUserAppSettingsReq.setDeviceSpecificParams(deviceSpecificParamsBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new i(coveApiListener));
    }

    public static void o(@NonNull SaveDrinkReminderSettingsReq saveDrinkReminderSettingsReq, @NonNull CoveApiListener<SaveDrinkReminderSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.DrinkReminderAlertBean drinkReminderAlertBean = new SUserAppSettingsReq.DrinkReminderAlertBean();
        drinkReminderAlertBean.setActive(saveDrinkReminderSettingsReq.isActive());
        drinkReminderAlertBean.setStartTime(saveDrinkReminderSettingsReq.getStartTime());
        drinkReminderAlertBean.setEndTime(saveDrinkReminderSettingsReq.getEndTime());
        drinkReminderAlertBean.setInterval(saveDrinkReminderSettingsReq.getInterval());
        sUserAppSettingsReq.setDrinkReminderAlert(drinkReminderAlertBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new c(coveApiListener));
    }

    public static void p(@NonNull SaveDualTimeZoneSettingsReq saveDualTimeZoneSettingsReq, @NonNull CoveApiListener<SaveDualTimeZoneSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        DualTimeBean dualTimeBean = new DualTimeBean();
        ArrayList arrayList = new ArrayList();
        for (SaveDualTimeZoneSettingsReq.TimeZone timeZone : saveDualTimeZoneSettingsReq.getTimeZones()) {
            DualTimeBean.TimeZone timeZone2 = new DualTimeBean.TimeZone();
            timeZone2.setCity(timeZone.getCity());
            timeZone2.setOffset(timeZone.getOffset());
            timeZone2.setPreference(timeZone.getPreference());
            arrayList.add(timeZone2);
        }
        dualTimeBean.setTimeZones(arrayList);
        sUserAppSettingsReq.setDualTimeBean(dualTimeBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new a0(coveApiListener));
    }

    public static void postFeedbackToServer(@NonNull PostFeedBackReq postFeedBackReq, @NonNull CoveApiListener<PostFeedBackRes, CoveApiErrorModel> coveApiListener) {
        e(postFeedBackReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void postSoftwareUpdate(@NonNull SoftwarUpdateReq softwarUpdateReq, @NonNull CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel> coveApiListener) {
        A(softwarUpdateReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void q(@NonNull SaveLapSettingsReq saveLapSettingsReq, @NonNull CoveApiListener<SaveLapSettingRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.LapsBean lapsBean = new SUserAppSettingsReq.LapsBean();
        lapsBean.setCycleDistance(saveLapSettingsReq.getCycleDistance());
        lapsBean.setRunDistance(saveLapSettingsReq.getRunDistance());
        lapsBean.setWalkDistance(saveLapSettingsReq.getWalkDistance());
        sUserAppSettingsReq.setLap(lapsBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new y(coveApiListener));
    }

    public static void r(@NonNull SaveLiftWristToViewSettingsReq saveLiftWristToViewSettingsReq, @NonNull CoveApiListener<SaveLiftWristToViewSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        LiftWristToViewBean liftWristToViewBean = new LiftWristToViewBean();
        liftWristToViewBean.setActive(Boolean.valueOf(saveLiftWristToViewSettingsReq.isActive()));
        liftWristToViewBean.setStartTime(saveLiftWristToViewSettingsReq.getStartTime());
        liftWristToViewBean.setEndTime(saveLiftWristToViewSettingsReq.getEndTime());
        sUserAppSettingsReq.setLiftWristToViewBean(liftWristToViewBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new b(coveApiListener));
    }

    public static void s(@NonNull SaveNotificationSettingsReq saveNotificationSettingsReq, @NonNull CoveApiListener<SaveNotificationSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.NotificationBean notificationBean = new SUserAppSettingsReq.NotificationBean();
        if (saveNotificationSettingsReq.getSms() != null) {
            SUserAppSettingsReq.NotificationBean.SmsBean smsBean = new SUserAppSettingsReq.NotificationBean.SmsBean();
            smsBean.setActive(saveNotificationSettingsReq.getSms().isActive());
            notificationBean.setSms(smsBean);
        }
        if (saveNotificationSettingsReq.getCall() != null) {
            SUserAppSettingsReq.NotificationBean.CallBean callBean = new SUserAppSettingsReq.NotificationBean.CallBean();
            callBean.setActive(saveNotificationSettingsReq.getCall().isActive());
            if (!CoveUtil.isEmpty(saveNotificationSettingsReq.getCall().getFavContacts())) {
                ArrayList arrayList = new ArrayList();
                for (SaveNotificationSettingsReq.CallBean.FavContactsBean favContactsBean : saveNotificationSettingsReq.getCall().getFavContacts()) {
                    SUserAppSettingsReq.NotificationBean.CallBean.FavContactsBean favContactsBean2 = new SUserAppSettingsReq.NotificationBean.CallBean.FavContactsBean();
                    favContactsBean2.setName(favContactsBean.getName());
                    favContactsBean2.setHourIndex(favContactsBean.getHourIndex());
                    favContactsBean2.setMobileNumber(favContactsBean.getMobileNumber());
                    arrayList.add(favContactsBean2);
                }
                callBean.setFavContacts(arrayList);
            }
            notificationBean.setCall(callBean);
        }
        if (saveNotificationSettingsReq.getOtherApps() != null) {
            SUserAppSettingsReq.NotificationBean.AppBean appBean = new SUserAppSettingsReq.NotificationBean.AppBean();
            appBean.setEnableAll(saveNotificationSettingsReq.getOtherApps().isEnableAll());
            if (!CoveUtil.isEmpty(saveNotificationSettingsReq.getOtherApps().getApps())) {
                ArrayList arrayList2 = new ArrayList();
                for (SaveNotificationSettingsReq.AppBean.Apps apps : saveNotificationSettingsReq.getOtherApps().getApps()) {
                    SUserAppSettingsReq.NotificationBean.AppBean.Apps apps2 = new SUserAppSettingsReq.NotificationBean.AppBean.Apps();
                    apps2.setActive(apps.getActive());
                    apps2.setAppId(apps.getAppId());
                    arrayList2.add(apps2);
                }
                appBean.setApps(arrayList2);
            }
            if (!CoveUtil.isEmpty(saveNotificationSettingsReq.getOtherApps().getAndroidApps())) {
                ArrayList arrayList3 = new ArrayList();
                for (SaveNotificationSettingsReq.AppBean.AndroidApps androidApps : saveNotificationSettingsReq.getOtherApps().getAndroidApps()) {
                    SUserAppSettingsReq.NotificationBean.AppBean.AndroidApps androidApps2 = new SUserAppSettingsReq.NotificationBean.AppBean.AndroidApps();
                    androidApps2.setActive(androidApps.getActive());
                    androidApps2.setPackageX(androidApps.getPackageX());
                    arrayList3.add(androidApps2);
                }
                appBean.setAndroidApps(arrayList3);
            }
            notificationBean.setOtherApps(appBean);
        }
        if (saveNotificationSettingsReq.getDnd() != null) {
            SUserAppSettingsReq.NotificationBean.DndBean dndBean = new SUserAppSettingsReq.NotificationBean.DndBean();
            dndBean.setActive(saveNotificationSettingsReq.getDnd().isActive());
            notificationBean.setDnd(dndBean);
        }
        sUserAppSettingsReq.setNotification(notificationBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new e(coveApiListener));
    }

    public static void saveAlarmSettings(@NonNull SaveAlarmSettingReq saveAlarmSettingReq, @NonNull CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel> coveApiListener) {
        j(saveAlarmSettingReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAutoHeartRateInterval(@NonNull SaveAutoHRSettingReq saveAutoHRSettingReq, @NonNull CoveApiListener<SaveAutoHRSettingsRes, CoveApiErrorModel> coveApiListener) {
        k(saveAutoHRSettingReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveAutoTemperatureInterval(@NonNull SaveAutoTemperatureSettingReq saveAutoTemperatureSettingReq, @NonNull CoveApiListener<SaveAutoTemperatureSettingsRes, CoveApiErrorModel> coveApiListener) {
        l(saveAutoTemperatureSettingReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveDNDSettings(@NonNull SaveDNDSettingsReq saveDNDSettingsReq, @NonNull CoveApiListener<SaveDNDSettingsRes, CoveApiErrorModel> coveApiListener) {
        m(saveDNDSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveDeviceSpecificParameters(@NonNull SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq, @NonNull CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel> coveApiListener) {
        n(saveDeviceSpecificParamsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveDrinkReminderAlertSettings(@NonNull SaveDrinkReminderSettingsReq saveDrinkReminderSettingsReq, @NonNull CoveApiListener<SaveDrinkReminderSettingsRes, CoveApiErrorModel> coveApiListener) {
        o(saveDrinkReminderSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveDrinkReminderSettings(HashMap<String, String> hashMap, @NonNull SaveDrinkReminderSettingsReq saveDrinkReminderSettingsReq, @NonNull CoveApiListener<SaveDrinkReminderSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        o(saveDrinkReminderSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveDualTimeZoneSettings(@NonNull SaveDualTimeZoneSettingsReq saveDualTimeZoneSettingsReq, @NonNull CoveApiListener<SaveDualTimeZoneSettingsRes, CoveApiErrorModel> coveApiListener) {
        p(saveDualTimeZoneSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveLapSettings(@NonNull SaveLapSettingsReq saveLapSettingsReq, @NonNull CoveApiListener<SaveLapSettingRes, CoveApiErrorModel> coveApiListener) {
        q(saveLapSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveLiftWristToViewSettings(@NonNull SaveLiftWristToViewSettingsReq saveLiftWristToViewSettingsReq, @NonNull CoveApiListener<SaveLiftWristToViewSettingsRes, CoveApiErrorModel> coveApiListener) {
        r(saveLiftWristToViewSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveMenstruationSettings(@NonNull SaveWomenWellnessReq saveWomenWellnessReq, @NonNull CoveApiListener<SaveWomenWellnessRes, CoveApiErrorModel> coveApiListener) {
        z(saveWomenWellnessReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveNotificationSettings(@NonNull SaveNotificationSettingsReq saveNotificationSettingsReq, @NonNull CoveApiListener<SaveNotificationSettingsRes, CoveApiErrorModel> coveApiListener) {
        s(saveNotificationSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveQuickReplySettings(@NonNull SaveQuickReplySettingsReq saveQuickReplySettingsReq, @NonNull CoveApiListener<SaveQuickReplySettingsRes, CoveApiErrorModel> coveApiListener) {
        t(saveQuickReplySettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSDCTSetting(@NonNull SaveSDCTSettingReq saveSDCTSettingReq, @NonNull CoveApiListener<SaveSDCTSettingsRes, CoveApiErrorModel> coveApiListener) {
        u(saveSDCTSettingReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveScheduleEventsSettings(@NonNull SaveScheduleEventsReq saveScheduleEventsReq, @NonNull CoveApiListener<SaveScheduleEventRes, CoveApiErrorModel> coveApiListener) {
        v(saveScheduleEventsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSedentaryAlertSettings(@NonNull SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertSettingsRes, CoveApiErrorModel> coveApiListener) {
        w(saveSedentaryAlertSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveSleepSettings(@NonNull SaveUserSleepSettingsReq saveUserSleepSettingsReq, CoveApiListener<SaveUserSleepSettingsRes, CoveApiErrorModel> coveApiListener) {
        x(saveUserSleepSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveUserConsent(@NonNull SConsentRequest sConsentRequest, @NonNull CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener) {
        g(sConsentRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveWeatherSettings(@NonNull SaveWeatherSettingsReq saveWeatherSettingsReq, @NonNull CoveApiListener<SaveWeatherSettingsRes, CoveApiErrorModel> coveApiListener) {
        y(saveWeatherSettingsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendDrowsyEstimation(HashMap<String, String> hashMap, @NonNull DrowsyEstimationReq drowsyEstimationReq, CoveApiListener<SDrowsyEstimationPostRes, CoveApiErrorModel> coveApiListener) {
        c(drowsyEstimationReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDrowsyEstimationFeedback(@NonNull SDrowbsinessFeedbackReq sDrowbsinessFeedbackReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener) {
        h(sDrowbsinessFeedbackReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendDrowsyFatigueStressData(HashMap<String, String> hashMap, @NonNull DrowsyFatigueStressDataReq drowsyFatigueStressDataReq, CoveApiListener<SDrowsyFatigueStressDataPostRes, CoveApiErrorModel> coveApiListener) {
        d(drowsyFatigueStressDataReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendRRateEstimation(HashMap<String, String> hashMap, @NonNull RRateTrainingRequest rRateTrainingRequest, CoveApiListener<RRateResponse, CoveApiErrorModel> coveApiListener) {
        f(rRateTrainingRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendSPO2Estimation(HashMap<String, String> hashMap, @NonNull SPO2EstimationReq sPO2EstimationReq, CoveApiListener<SSPO2EstimationPostRes, CoveApiErrorModel> coveApiListener) {
        i(sPO2EstimationReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void t(@NonNull SaveQuickReplySettingsReq saveQuickReplySettingsReq, @NonNull CoveApiListener<SaveQuickReplySettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        CallQuickRepliesBean callQuickRepliesBean = new CallQuickRepliesBean();
        callQuickRepliesBean.setActive(Boolean.valueOf(saveQuickReplySettingsReq.isActive()));
        callQuickRepliesBean.setMessages(saveQuickReplySettingsReq.getQuickReplyMessages());
        sUserAppSettingsReq.setCallQuickReplies(callQuickRepliesBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new z(coveApiListener));
    }

    public static void u(@NonNull SaveSDCTSettingReq saveSDCTSettingReq, @NonNull CoveApiListener<SaveSDCTSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.SocialDistancingBean socialDistancingBean = new SUserAppSettingsReq.SocialDistancingBean();
        socialDistancingBean.setActive(saveSDCTSettingReq.getEnableSocialDistancing());
        SUserAppSettingsReq.ContactTracingBean contactTracingBean = new SUserAppSettingsReq.ContactTracingBean();
        contactTracingBean.setActive(saveSDCTSettingReq.getEnableContactTracing());
        sUserAppSettingsReq.setContactTracing(contactTracingBean);
        sUserAppSettingsReq.setSocialDistancing(socialDistancingBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new n(coveApiListener));
    }

    public static void v(@NonNull SaveScheduleEventsReq saveScheduleEventsReq, @NonNull CoveApiListener<SaveScheduleEventRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        sUserAppSettingsReq.setCalender(saveScheduleEventsReq.getCalendar());
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new b0(coveApiListener));
    }

    public static void w(@NonNull SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.SedentaryAlertBean sedentaryAlertBean = new SUserAppSettingsReq.SedentaryAlertBean();
        sedentaryAlertBean.setActive(saveSedentaryAlertSettingsReq.isActive());
        sedentaryAlertBean.setStartTime(saveSedentaryAlertSettingsReq.getStartTime());
        sedentaryAlertBean.setEndTime(saveSedentaryAlertSettingsReq.getEndTime());
        sedentaryAlertBean.setInterval(saveSedentaryAlertSettingsReq.getInterval());
        if (saveSedentaryAlertSettingsReq.getSiesta() != null) {
            SUserAppSettingsReq.SedentaryAlertBean.SiestaBean siestaBean = new SUserAppSettingsReq.SedentaryAlertBean.SiestaBean();
            siestaBean.setActive(saveSedentaryAlertSettingsReq.getSiesta().isActive());
            sedentaryAlertBean.setSiesta(siestaBean);
        }
        sUserAppSettingsReq.setSedentaryAlert(sedentaryAlertBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new a(coveApiListener));
    }

    public static void x(@NonNull SaveUserSleepSettingsReq saveUserSleepSettingsReq, CoveApiListener<SaveUserSleepSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        SUserAppSettingsReq.SleepBean sleepBean = new SUserAppSettingsReq.SleepBean();
        sleepBean.setActive(saveUserSleepSettingsReq.isActive());
        if (saveUserSleepSettingsReq.getSleepTime() != null) {
            SUserAppSettingsReq.SleepBean.SleepTimeBean sleepTimeBean = new SUserAppSettingsReq.SleepBean.SleepTimeBean();
            sleepTimeBean.setActive(saveUserSleepSettingsReq.getSleepTime().isActive());
            sleepTimeBean.setStartTime(saveUserSleepSettingsReq.getSleepTime().getStartTime());
            sleepTimeBean.setEndTime(saveUserSleepSettingsReq.getSleepTime().getEndTime());
            sleepTimeBean.setNotifyActive(saveUserSleepSettingsReq.getSleepTime().isNotifyActive());
            sleepTimeBean.setNotifyTime(saveUserSleepSettingsReq.getSleepTime().getNotifyTime());
            sleepBean.setSleepTime(sleepTimeBean);
        }
        if (saveUserSleepSettingsReq.getNapTime() != null) {
            SUserAppSettingsReq.SleepBean.NapTimeBean napTimeBean = new SUserAppSettingsReq.SleepBean.NapTimeBean();
            napTimeBean.setActive(saveUserSleepSettingsReq.getNapTime().isActive());
            napTimeBean.setStartTime(saveUserSleepSettingsReq.getNapTime().getStartTime());
            napTimeBean.setEndTime(saveUserSleepSettingsReq.getNapTime().getEndTime());
            napTimeBean.setNotifyActive(saveUserSleepSettingsReq.getNapTime().isNotifyActive());
            napTimeBean.setNotifyTime(saveUserSleepSettingsReq.getNapTime().getNotifyTime());
            sleepBean.setNapTime(napTimeBean);
        }
        sUserAppSettingsReq.setSleep(sleepBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new g(coveApiListener));
    }

    public static void y(@NonNull SaveWeatherSettingsReq saveWeatherSettingsReq, @NonNull CoveApiListener<SaveWeatherSettingsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        sUserAppSettingsReq.setWeatherForecast(saveWeatherSettingsReq.getWeatherForecastBean());
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new d(coveApiListener));
    }

    public static void z(@NonNull SaveWomenWellnessReq saveWomenWellnessReq, @NonNull CoveApiListener<SaveWomenWellnessRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SUserAppSettingsReq sUserAppSettingsReq = new SUserAppSettingsReq();
        MensturationBean mensturationBean = new MensturationBean();
        ArrayList arrayList = new ArrayList();
        if (saveWomenWellnessReq.getReminderListBeans() != null) {
            for (SaveWomenWellnessReq.ReminderListBean reminderListBean : saveWomenWellnessReq.getReminderListBeans()) {
                MensturationBean.Reminder reminder = new MensturationBean.Reminder();
                reminder.setActive(reminderListBean.getActive());
                reminder.setRemindAt(reminderListBean.getRemindAt());
                reminder.setRemindBefore(reminderListBean.getRemindBefore());
                reminder.setType(reminderListBean.getType());
                arrayList.add(reminder);
            }
        }
        if (arrayList.size() > 0) {
            mensturationBean.setReminders(arrayList);
        } else {
            mensturationBean.setReminders(null);
        }
        mensturationBean.setActive(saveWomenWellnessReq.getActive());
        mensturationBean.setCycleLength(saveWomenWellnessReq.getCycleLength());
        mensturationBean.setPeriodLength(saveWomenWellnessReq.getPeriodLength());
        mensturationBean.setPmsLength(saveWomenWellnessReq.getPmsLength());
        mensturationBean.setCycleStartDate(saveWomenWellnessReq.getCycleStartDate());
        sUserAppSettingsReq.setMenstruation(mensturationBean);
        CoveApi.getService().saveUserAppSettings(map, sUserAppSettingsReq).enqueue(new d0(coveApiListener));
    }

    public static void saveWeatherSettings(HashMap<String, String> hashMap, @NonNull SaveWeatherSettingsReq saveWeatherSettingsReq, @NonNull CoveApiListener<SaveWeatherSettingsRes, CoveApiErrorModel> coveApiListener) {
        y(saveWeatherSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDrowsyEstimation(@NonNull DrowsyEstimationReq drowsyEstimationReq, CoveApiListener<SDrowsyEstimationPostRes, CoveApiErrorModel> coveApiListener) {
        c(drowsyEstimationReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendDrowsyEstimationFeedback(HashMap<String, String> hashMap, @NonNull SDrowbsinessFeedbackReq sDrowbsinessFeedbackReq, CoveApiListener<JsonObject, CoveApiErrorModel> coveApiListener) {
        h(sDrowbsinessFeedbackReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void sendDrowsyFatigueStressData(@NonNull DrowsyFatigueStressDataReq drowsyFatigueStressDataReq, CoveApiListener<SDrowsyFatigueStressDataPostRes, CoveApiErrorModel> coveApiListener) {
        d(drowsyFatigueStressDataReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendRRateEstimation(@NonNull RRateTrainingRequest rRateTrainingRequest, CoveApiListener<RRateResponse, CoveApiErrorModel> coveApiListener) {
        f(rRateTrainingRequest, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void sendSPO2Estimation(@NonNull SPO2EstimationReq sPO2EstimationReq, CoveApiListener<SSPO2EstimationPostRes, CoveApiErrorModel> coveApiListener) {
        i(sPO2EstimationReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAllUserAppSettings(HashMap<String, String> hashMap, CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel> coveApiListener) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getDeviceConfiguration(HashMap<String, String> hashMap, String str, CoveApiListener<DeviceConfigurationRes, CoveApiErrorModel> coveApiListener) {
        B(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getFeedBackCategories(HashMap<String, String> hashMap, CoveApiListener<FeedbackCategoriesRes, CoveApiErrorModel> coveApiListener) {
        D(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getUserConsent(HashMap<String, String> hashMap, @NonNull CoveApiListener<SGetConsentData, CoveApiErrorModel> coveApiListener) {
        C(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getUserDetails(HashMap<String, String> hashMap, CoveApiListener<SGetUserDetails, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void postFeedbackToServer(HashMap<String, String> hashMap, @NonNull PostFeedBackReq postFeedBackReq, @NonNull CoveApiListener<PostFeedBackRes, CoveApiErrorModel> coveApiListener) {
        e(postFeedBackReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void postSoftwareUpdate(HashMap<String, String> hashMap, @NonNull SoftwarUpdateReq softwarUpdateReq, @NonNull CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        A(softwarUpdateReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAlarmSettings(HashMap<String, String> hashMap, @NonNull SaveAlarmSettingReq saveAlarmSettingReq, @NonNull CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel> coveApiListener) {
        j(saveAlarmSettingReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAutoHeartRateInterval(HashMap<String, String> hashMap, @NonNull SaveAutoHRSettingReq saveAutoHRSettingReq, @NonNull CoveApiListener<SaveAutoHRSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        k(saveAutoHRSettingReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveAutoTemperatureInterval(HashMap<String, String> hashMap, @NonNull SaveAutoTemperatureSettingReq saveAutoTemperatureSettingReq, @NonNull CoveApiListener<SaveAutoTemperatureSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        l(saveAutoTemperatureSettingReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveDNDSettings(HashMap<String, String> hashMap, @NonNull SaveDNDSettingsReq saveDNDSettingsReq, @NonNull CoveApiListener<SaveDNDSettingsRes, CoveApiErrorModel> coveApiListener) {
        m(saveDNDSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveDeviceSpecificParameters(HashMap<String, String> hashMap, @NonNull SaveDeviceSpecificParamsReq saveDeviceSpecificParamsReq, @NonNull CoveApiListener<SaveDeviceSpecificParamsRes, CoveApiErrorModel> coveApiListener) {
        n(saveDeviceSpecificParamsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveDualTimeZoneSettings(HashMap<String, String> hashMap, @NonNull SaveDualTimeZoneSettingsReq saveDualTimeZoneSettingsReq, @NonNull CoveApiListener<SaveDualTimeZoneSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        p(saveDualTimeZoneSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveLiftWristToViewSettings(HashMap<String, String> hashMap, @NonNull SaveLiftWristToViewSettingsReq saveLiftWristToViewSettingsReq, @NonNull CoveApiListener<SaveLiftWristToViewSettingsRes, CoveApiErrorModel> coveApiListener) {
        r(saveLiftWristToViewSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveMenstruationSettings(HashMap<String, String> hashMap, @NonNull SaveWomenWellnessReq saveWomenWellnessReq, @NonNull CoveApiListener<SaveWomenWellnessRes, CoveApiErrorModel> coveApiListener) {
        z(saveWomenWellnessReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveNotificationSettings(HashMap<String, String> hashMap, @NonNull SaveNotificationSettingsReq saveNotificationSettingsReq, @NonNull CoveApiListener<SaveNotificationSettingsRes, CoveApiErrorModel> coveApiListener) {
        s(saveNotificationSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSDCTSetting(HashMap<String, String> hashMap, @NonNull SaveSDCTSettingReq saveSDCTSettingReq, @NonNull CoveApiListener<SaveSDCTSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        u(saveSDCTSettingReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveScheduleEventsSettings(HashMap<String, String> hashMap, @NonNull SaveScheduleEventsReq saveScheduleEventsReq, @NonNull CoveApiListener<SaveScheduleEventRes, CoveApiErrorModel> coveApiListener) {
        v(saveScheduleEventsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSedentaryAlertSettings(HashMap<String, String> hashMap, @NonNull SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq, @NonNull CoveApiListener<SaveSedentaryAlertSettingsRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        w(saveSedentaryAlertSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveSleepSettings(HashMap<String, String> hashMap, @NonNull SaveUserSleepSettingsReq saveUserSleepSettingsReq, CoveApiListener<SaveUserSleepSettingsRes, CoveApiErrorModel> coveApiListener) {
        x(saveUserSleepSettingsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveUserConsent(HashMap<String, String> hashMap, @NonNull SConsentRequest sConsentRequest, @NonNull CoveApiListener<ActivityRes, CoveApiErrorModel> coveApiListener) {
        g(sConsentRequest, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
