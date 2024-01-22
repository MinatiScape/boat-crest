package com.coveiot.android.dashboard2;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.Alarm;
import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.DNDData;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.MeasurementUnitType;
import com.coveiot.android.bleabstract.models.PPGTypes;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.SedentaryReminderData;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.request.BpCalibrationDataRequest;
import com.coveiot.android.bleabstract.request.GetAlarmDataRequest;
import com.coveiot.android.bleabstract.request.GetDNDModeSettingsRequest;
import com.coveiot.android.bleabstract.request.GetDeviceSettingsInfoRequest;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.request.GetSedentaryDataRequest;
import com.coveiot.android.bleabstract.request.HeartRateTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.ProbeDataRequest;
import com.coveiot.android.bleabstract.request.SetAutoActivityDetectionSettingsRequest;
import com.coveiot.android.bleabstract.request.SetAutomaticPPGConfigRequest;
import com.coveiot.android.bleabstract.request.SetCallSmsSocialNotificationRequest;
import com.coveiot.android.bleabstract.request.SetDNDModeRequest;
import com.coveiot.android.bleabstract.request.SetDeviceParametersRequest;
import com.coveiot.android.bleabstract.request.SetDistanceUnitRequest;
import com.coveiot.android.bleabstract.request.SetFitnessInfoRequest;
import com.coveiot.android.bleabstract.request.SetHourFormatRequest;
import com.coveiot.android.bleabstract.request.SetLiftWristRequest;
import com.coveiot.android.bleabstract.request.SetMusicMetaDataPlaybackVolumeRequest;
import com.coveiot.android.bleabstract.request.SetMusicVolumePercentageChangedRequest;
import com.coveiot.android.bleabstract.request.SetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.request.SetNavigationFavouriteLocationRequest;
import com.coveiot.android.bleabstract.request.SetQuickReplyRequest;
import com.coveiot.android.bleabstract.request.SetReminderRequest;
import com.coveiot.android.bleabstract.request.SetSOSConfigRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.request.SetTemperatureUnitRequest;
import com.coveiot.android.bleabstract.request.SetVibrationAlarmRequest;
import com.coveiot.android.bleabstract.request.SetWeatherConfigInfoRequest;
import com.coveiot.android.bleabstract.request.SportNotificationControlRequest;
import com.coveiot.android.bleabstract.request.StepsTargetRequest;
import com.coveiot.android.bleabstract.request.StressTimeIntervalRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.request.TemperatureTimeIntervalRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.coveiot.android.customreminders.listeners.ResultListener;
import com.coveiot.android.customreminders.preference.PreferenceManagerCustomReminders;
import com.coveiot.android.customreminders.utils.ReminderHelper;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.dashboard2.util.Dashboard2Utils;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.weather.weather.WeatherAppPreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SUserAppSettingsReq;
import com.coveiot.coveaccess.model.server.Snooze;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingReq;
import com.coveiot.coveaccess.userappsetting.SaveAlarmSettingRes;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveDNDSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveLiftWristToViewSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveSedentaryAlertSettingsRes;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.model.MapNavigationSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SmartAlertSettings;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.covepreferences.data.BpCalibrationData;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.QuickReplyListModel;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
import com.coveiot.sdk.ble.api.model.BleQuickReplyModel;
import com.coveiot.sdk.ble.api.model.DNDSettingsData;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.e;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SettingsSyncHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4198a;
    @Nullable
    public SettingsSyncListner b;
    public int c;
    @NotNull
    public final String d;
    @Nullable
    public DeviceSupportedFeatures e;
    public int f;

    /* loaded from: classes4.dex */
    public static final class Companion extends SingletonHolder<SettingsSyncHelper, Context> {

        /* loaded from: classes4.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, SettingsSyncHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, SettingsSyncHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final SettingsSyncHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new SettingsSyncHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes4.dex */
    public interface SettingsSyncListner {
        void onProgressUpdate(int i);

        void onSettingSyncError();

        void onSettingsSyncCompleted();
    }

    public SettingsSyncHelper(Context context) {
        this.f4198a = context;
        this.d = "SettingsSyncHelper";
        if (BleApiManager.getInstance(context) != null) {
            BleApiManager bleApiManager = BleApiManager.getInstance(context);
            Intrinsics.checkNotNull(bleApiManager);
            if (bleApiManager.getBleApi() != null) {
                this.e = BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures();
            }
        }
    }

    public /* synthetic */ SettingsSyncHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void i(SettingsSyncHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(Dashboard2Constants.MUSIC_PLAYBACK_STATE_CHANGE_BROADCAST_INTENT);
        intent.putExtra(Dashboard2Constants.MUSIC_APP_PLAYBACK_STATE_CHANGED, 0);
        LocalBroadcastManager.getInstance(this$0.f4198a.getApplicationContext()).sendBroadcast(intent);
    }

    public final void A() {
        SetReminderRequest sedentaryReminderData = DataHelper.INSTANCE.getSedentaryReminderData(this.f4198a);
        if (sedentaryReminderData != null) {
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(sedentaryReminderData, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSedentaryReminderData$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d("SettingsSyncHelper", " Sedentary settings success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0100, code lost:
        if (r0.isScheduledDndSupported() != false) goto L91;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void B(com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner r7) {
        /*
            Method dump skipped, instructions count: 537
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.SettingsSyncHelper.B(com.coveiot.android.dashboard2.SettingsSyncHelper$SettingsSyncListner):void");
    }

    public final void C() {
        EntityProfile latestProfileData;
        ProfileRepository profileRepository = ProfileRepository.getInstance();
        if (profileRepository == null || (latestProfileData = profileRepository.getLatestProfileData(this.f4198a)) == null) {
            return;
        }
        StepsTargetRequest setWalkTargetReq = new StepsTargetRequest.Builder().setTarget(latestProfileData.stepsTarget).build();
        this.f++;
        BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setWalkTargetReq, "setWalkTargetReq");
        bleApi.setUserSettings(setWalkTargetReq, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncStepsGoal$1$1$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("SettingsSyncHelper", " Step goal settings success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    @NotNull
    public final List<AlarmInfo> ConvertFromVibrateToAlarminfo() {
        List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(this.f4198a).getVibrationAlarmData();
        Intrinsics.checkNotNull(vibrationAlarmData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.VibrationAlarmData?>");
        List<VibrationAlarmData> asMutableList = TypeIntrinsics.asMutableList(vibrationAlarmData);
        ArrayList arrayList = new ArrayList();
        for (VibrationAlarmData vibrationAlarmData2 : asMutableList) {
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setEventName(vibrationAlarmData2.getEvent_name());
            alarmInfo.setAlarmType(vibrationAlarmData2.getAlarmType());
            String alarmId = vibrationAlarmData2.getAlarmId();
            Intrinsics.checkNotNullExpressionValue(alarmId, "mutlistarr.alarmId");
            alarmInfo.setAlarmId(Integer.parseInt(alarmId));
            alarmInfo.setHour(vibrationAlarmData2.getEvent_time_hour());
            alarmInfo.setMinute(vibrationAlarmData2.getEvent_time_minutes());
            alarmInfo.setDaysSelected(new AlarmInfo.Days(vibrationAlarmData2.isSunday(), vibrationAlarmData2.isMonday(), vibrationAlarmData2.isTuesday(), vibrationAlarmData2.isWednesday(), vibrationAlarmData2.isThrusday(), vibrationAlarmData2.isFriday(), vibrationAlarmData2.isSaturday()));
            alarmInfo.setAlarmOn(vibrationAlarmData2.getSwitch_on_off());
            arrayList.add(alarmInfo);
        }
        if (arrayList.size() >= 3) {
            if (arrayList.size() == 4 && (((AlarmInfo) arrayList.get(3)).getAlarmId() <= 0 || ((AlarmInfo) arrayList.get(3)).getAlarmId() >= 4)) {
                return callDefaultAlarm();
            }
            if (arrayList.size() != 5 || (((AlarmInfo) arrayList.get(4)).getAlarmId() > 0 && ((AlarmInfo) arrayList.get(4)).getAlarmId() < 5 && ((AlarmInfo) arrayList.get(3)).getAlarmId() > 0 && ((AlarmInfo) arrayList.get(3)).getAlarmId() < 4)) {
                return (((AlarmInfo) arrayList.get(0)).getAlarmId() > 0 || ((AlarmInfo) arrayList.get(0)).getAlarmId() < 0 || ((AlarmInfo) arrayList.get(1)).getAlarmId() <= 0 || ((AlarmInfo) arrayList.get(2)).getAlarmId() <= 0) ? callDefaultAlarm() : arrayList;
            }
            return callDefaultAlarm();
        }
        arrayList.size();
        return arrayList;
    }

    public final void D() {
        AutoStressSettingsData autoStressSettingsData = UserDataManager.getInstance(this.f4198a).getAutoStressSettingsData();
        if (autoStressSettingsData != null) {
            StressConfiguration autoStressConfig = SessionManager.getInstance(this.f4198a).getAutoStressConfig();
            Dashboard2Utils.Companion companion = Dashboard2Utils.Companion;
            String baselineTime = autoStressConfig.getStress().getBaselineTime();
            Intrinsics.checkNotNullExpressionValue(baselineTime, "stressConfiguration.stress.baselineTime");
            long convertIntervalToMilliSeconds = companion.convertIntervalToMilliSeconds(baselineTime);
            String readinessTime = autoStressConfig.getStress().getReadinessTime();
            Intrinsics.checkNotNullExpressionValue(readinessTime, "stressConfiguration.stress.readinessTime");
            long convertIntervalToMilliSeconds2 = companion.convertIntervalToMilliSeconds(readinessTime);
            StressTimeIntervalRequest.Builder enabled = new StressTimeIntervalRequest.Builder(autoStressSettingsData.getMeasuringInterval()).setEnabled(autoStressSettingsData.getAutoStress());
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            StressTimeIntervalRequest.Builder highStressThreshold = enabled.setBaseLineTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds)).setReadinessTime((int) timeUnit.toMinutes(convertIntervalToMilliSeconds2)).setHighStressThreshold(autoStressConfig.getStress().getAlert().getThreshold().intValue());
            Integer maxAllowed = autoStressConfig.getStress().getAlert().getMaxAllowed();
            Intrinsics.checkNotNullExpressionValue(maxAllowed, "stressConfiguration.stress.alert.maxAllowed");
            StressTimeIntervalRequest build = highStressThreshold.setLimit(maxAllowed.intValue()).build();
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncStressAndHRVSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    LogHelper.d("SettingsSyncHelper", " stressTimeIntervalRequest settings failed");
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d("SettingsSyncHelper", " stressTimeIntervalRequest settings success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
            return;
        }
        LogHelper.d(this.d, " Stress HRV Settings diabled");
    }

    public final void E() {
        AutoStressSettingsData autoStressSettingsData = UserDataManager.getInstance(this.f4198a).getAutoStressSettingsData();
        if (autoStressSettingsData != null) {
            StressTimeIntervalRequest build = new StressTimeIntervalRequest.Builder(autoStressSettingsData.getMeasuringInterval()).setStartHour(autoStressSettingsData.getBeggining_time_hour()).setEndHour(autoStressSettingsData.getEnd_time_hour()).setStartMin(autoStressSettingsData.getBeggining_time_minutes()).setEndMin(autoStressSettingsData.getEnd_time_minutes()).setEnabled(autoStressSettingsData.getAutoStress()).setHighThresholdRemindEnabled(autoStressSettingsData.isHighStressReminder()).setSundayEnabled(autoStressSettingsData.isSunday()).setMondayEnabled(autoStressSettingsData.isMonday()).setTuesdayEnabled(autoStressSettingsData.isTuesday()).setWednesdayEnabled(autoStressSettingsData.isWednesday()).setThursdayEnabled(autoStressSettingsData.isThrusday()).setFridayEnabled(autoStressSettingsData.isFriday()).setSaturdayEnabled(autoStressSettingsData.isSaturday()).build();
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncStressSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d("SettingsSyncHelper", " stressTimeIntervalRequest settings success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        }
    }

    public final void F() {
        Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4198a).isTemperatureUnitInFahrenheit();
        this.f++;
        SetTemperatureUnitRequest build = new SetTemperatureUnitRequest.Builder(!isTemperatureUnitInFahrenheit.booleanValue()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(!temperatureUnitInFahrenheit).build()");
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncTemperatureUnit$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, error.toString());
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, response.toString());
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void G() {
        MeasurementUnitType measurementUnitType;
        try {
            WeatherAppPreferenceManager.Companion companion = WeatherAppPreferenceManager.Companion;
            WeatherAppPreferenceManager companion2 = companion.getInstance(this.f4198a);
            Boolean isMetricUnitEnabled = companion2 != null ? companion2.isMetricUnitEnabled() : null;
            Intrinsics.checkNotNull(isMetricUnitEnabled);
            if (isMetricUnitEnabled.booleanValue()) {
                measurementUnitType = MeasurementUnitType.METRIC;
            } else {
                measurementUnitType = MeasurementUnitType.IMPERIAL;
            }
            this.f++;
            WeatherAppPreferenceManager companion3 = companion.getInstance(this.f4198a);
            Boolean isWeatherEnabled = companion3 != null ? companion3.isWeatherEnabled() : null;
            Intrinsics.checkNotNull(isWeatherEnabled);
            n(measurementUnitType, isWeatherEnabled.booleanValue(), new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncWeatherSettings$1
                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onError(@Nullable String str) {
                    String str2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    str2 = SettingsSyncHelper.this.d;
                    LogHelper.d(str2, "Weather control command failed");
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.theme.SuccessResultListener
                public void onSuccess() {
                    String str;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "Weather control command succeeded");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void H(SettingsSyncListner settingsSyncListner) {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this.f4198a) && !companion.isCADevice(this.f4198a) && !companion.isCYDevice(this.f4198a)) {
            if (this.f == 0) {
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingsSyncCompleted();
                return;
            }
            return;
        }
        if (SportsPreference.Companion.isNotificationEnabled(this.f4198a)) {
            Object preference = BlePreferenceManager.getPreference(this.f4198a, CommonPreference.SHOULD_UPDATE_SETTINGS_BAND, Boolean.FALSE);
            Intrinsics.checkNotNullExpressionValue(preference, "getPreference(\n         …, false\n                )");
            if (((Boolean) preference).booleanValue()) {
                this.f++;
                BleApiManager.getInstance(this.f4198a).getBleApi().getData(new GetImageIdListRequest(), new SettingsSyncHelper$uploadCricketImage$1(this, settingsSyncListner));
                return;
            }
        }
        if (this.f == 0) {
            Intrinsics.checkNotNull(settingsSyncListner);
            settingsSyncListner.onSettingsSyncCompleted();
        }
    }

    public final void I(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (AppUtils.isNetConnected(this.f4198a)) {
            SUserAppSettingsReq.SedentaryAlertBean.SiestaBean siestaBean = new SUserAppSettingsReq.SedentaryAlertBean.SiestaBean();
            siestaBean.setActive(false);
            SaveSedentaryAlertSettingsReq saveSedentaryAlertSettingsReq = new SaveSedentaryAlertSettingsReq();
            if (i < 10) {
                saveSedentaryAlertSettingsReq.setEndTime('0' + i + ':' + i2 + ":00");
            } else {
                saveSedentaryAlertSettingsReq.setEndTime(i + ':' + i2 + ":00");
            }
            if (i3 < 10) {
                saveSedentaryAlertSettingsReq.setStartTime('0' + i3 + ':' + i4 + ":00");
            } else {
                saveSedentaryAlertSettingsReq.setStartTime(i3 + ':' + i4 + ":00");
            }
            saveSedentaryAlertSettingsReq.setInterval(getConvertedInterval(i5));
            saveSedentaryAlertSettingsReq.setActive(z);
            saveSedentaryAlertSettingsReq.setSiesta(siestaBean);
            CoveUserAppSettings.saveSedentaryAlertSettings(saveSedentaryAlertSettingsReq, new CoveApiListener<SaveSedentaryAlertSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$uploadSedentaryReminderToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveSedentaryAlertSettingsRes saveSedentaryAlertSettingsRes) {
                }
            });
        }
    }

    public final void J(GetFavouritePlacesRes getFavouritePlacesRes, final Function2<? super String, ? super Boolean, Unit> function2) {
        ArrayList arrayList = new ArrayList();
        List<FavouritePlace> favouritePlaceList = getFavouritePlacesRes.getFavouritePlaceList();
        Intrinsics.checkNotNullExpressionValue(favouritePlaceList, "getFavouritePlacesRes.favouritePlaceList");
        int i = 0;
        for (Object obj : favouritePlaceList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            FavouritePlace favouritePlace = (FavouritePlace) obj;
            Integer sortIndex = favouritePlace.getSortIndex();
            Intrinsics.checkNotNull(sortIndex);
            int intValue = sortIndex.intValue();
            Integer sortIndex2 = favouritePlace.getSortIndex();
            Intrinsics.checkNotNull(sortIndex2);
            arrayList.add(new FavouriteLocationData(intValue, sortIndex2.intValue(), favouritePlace.getLabel(), favouritePlace.getName()));
            i = i2;
        }
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new SetNavigationFavouriteLocationRequest(arrayList), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$writeFavouritePlacesToBand$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                function2.invoke(error.getErrorMsg(), Boolean.FALSE);
                str = this.d;
                LogHelper.d(str, "syncFavouritePlaces saveFavouritePlaceOnBand onSettingsError " + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                function2.invoke("", Boolean.TRUE);
                str = this.d;
                LogHelper.d(str, "syncFavouritePlaces saveFavouritePlaceOnBand onSettingsResponse");
            }
        });
    }

    public final void K(MapNavigationSettings mapNavigationSettings, final Function1<? super Boolean, Unit> function1) {
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new SetNavigationConfigurationRequest(mapNavigationSettings.getAudioSettings().isActive(), mapNavigationSettings.getVibrationSettings().isActive(), mapNavigationSettings.getAodSettings().isActive()), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$writeNavigationSettingsToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                function1.invoke(Boolean.FALSE);
                str = this.d;
                LogHelper.d(str, "syncNavigationSettings writeNavigationSettingsToBand onSettingsError");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                function1.invoke(Boolean.TRUE);
                str = this.d;
                LogHelper.d(str, "syncNavigationSettings writeNavigationSettingsToBand onSettingsResponse");
            }
        });
    }

    public final void b(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str2);
            Intrinsics.checkNotNullExpressionValue(open, "assetManager.open(fileName)");
            FileOutputStream fileOutputStream = new FileOutputStream(str + str2);
            byte[] bArr = new byte[1024];
            for (int read = open.read(bArr); read != -1; read = open.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public final String c(Alarm alarm) {
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

    @NotNull
    public final ArrayList<AlarmInfo> callDefaultAlarm() {
        ArrayList<AlarmInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AlarmInfo alarmInfo = new AlarmInfo();
            alarmInfo.setAlarmOn(false);
            alarmInfo.setDaysSelected(new AlarmInfo.Days(false, false, false, false, false, false, false));
            if (i == 0) {
                alarmInfo.setEventName(this.f4198a.getResources().getString(R.string.eventname1));
                alarmInfo.setAlarmId(0);
                alarmInfo.setHour(9);
                alarmInfo.setMinute(30);
            } else if (i != 1) {
                alarmInfo.setEventName(this.f4198a.getResources().getString(R.string.eventname3));
                alarmInfo.setAlarmId(2);
                alarmInfo.setHour(18);
                alarmInfo.setMinute(30);
            } else {
                alarmInfo.setEventName(this.f4198a.getResources().getString(R.string.eventname2));
                alarmInfo.setAlarmId(1);
                alarmInfo.setHour(13);
                alarmInfo.setMinute(0);
            }
            arrayList.add(alarmInfo);
        }
        return arrayList;
    }

    public final String d(SedentaryReminderData sedentaryReminderData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (sedentaryReminderData.isSundayEnabled()) {
            str = "S";
        } else {
            str = "" + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isMondayEnabled()) {
            str2 = str + 'M';
        } else {
            str2 = str + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isTuesdayEnabled()) {
            str3 = str2 + 'T';
        } else {
            str3 = str2 + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isWednesdayEnabled()) {
            str4 = str3 + 'W';
        } else {
            str4 = str3 + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isThursdayEnabled()) {
            str5 = str4 + 'T';
        } else {
            str5 = str4 + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isFridayEnabled()) {
            str6 = str5 + 'F';
        } else {
            str6 = str5 + Soundex.SILENT_MARKER;
        }
        if (sedentaryReminderData.isSaturdayEnabled()) {
            return str6 + 'S';
        }
        return str6 + Soundex.SILENT_MARKER;
    }

    public final boolean e(AlarmInfo alarmInfo) {
        boolean[] isDaySelected = alarmInfo.getDaysSelected().getIsDaySelected();
        Intrinsics.checkNotNullExpressionValue(isDaySelected, "alarmInfo.daysSelected.isDaySelected");
        for (boolean z : isDaySelected) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final void f(final SettingsSyncListner settingsSyncListner) {
        if (BleApiManager.getInstance(this.f4198a).getDeviceType() == DeviceType.smaM6 && UserDataManager.getInstance(this.f4198a).getSedentaryReminderData() != null) {
            BleApiManager.getInstance(this.f4198a).getBleApi().getData(new GetSedentaryDataRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$readSedentaryInfoFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingsSyncCompleted();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    String d;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() != null && (response.getResponseData() instanceof SedentaryReminderData)) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.SedentaryReminderData");
                        SedentaryReminderData sedentaryReminderData = (SedentaryReminderData) responseData;
                        com.coveiot.covepreferences.data.SedentaryReminderData sedentaryReminderData2 = UserDataManager.getInstance(this.getContext()).getSedentaryReminderData();
                        sedentaryReminderData2.setRemind_in(sedentaryReminderData.getReminderInterval());
                        sedentaryReminderData2.setAlarm_on_off(sedentaryReminderData.isEnabled());
                        sedentaryReminderData2.setBeggining_time_hour(sedentaryReminderData.getStartHour1());
                        sedentaryReminderData2.setBeggining_time_minutes(sedentaryReminderData.getStartMin1());
                        sedentaryReminderData2.setEnd_time_hour(sedentaryReminderData.getEndHour1());
                        sedentaryReminderData2.setEnd_time_minutes(sedentaryReminderData.getEndMin1());
                        sedentaryReminderData2.setEnd_time_minutes(sedentaryReminderData.getEndMin1());
                        d = this.d(sedentaryReminderData);
                        sedentaryReminderData2.setWeeks(d);
                        sedentaryReminderData2.setMonday(sedentaryReminderData.isMondayEnabled());
                        sedentaryReminderData2.setTuesday(sedentaryReminderData.isTuesdayEnabled());
                        sedentaryReminderData2.setWednesday(sedentaryReminderData.isWednesdayEnabled());
                        sedentaryReminderData2.setThrusday(sedentaryReminderData.isThursdayEnabled());
                        sedentaryReminderData2.setFriday(sedentaryReminderData.isFridayEnabled());
                        sedentaryReminderData2.setSaturday(sedentaryReminderData.isSaturdayEnabled());
                        sedentaryReminderData2.setSunday(sedentaryReminderData.isSundayEnabled());
                        UserDataManager.getInstance(this.getContext()).saveSedentaryReminderSettings(sedentaryReminderData2);
                        this.I(sedentaryReminderData2.getEnd_time_hour(), sedentaryReminderData2.getEnd_time_minutes(), sedentaryReminderData2.getBeggining_time_hour(), sedentaryReminderData2.getBeggining_time_minutes(), sedentaryReminderData2.getRemind_in(), sedentaryReminderData2.getAlarm_on_off());
                    }
                    this.readDndInfoFromBand(SettingsSyncHelper.SettingsSyncListner.this);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        readDndInfoFromBand(settingsSyncListner);
    }

    public final void g(final List<AlarmInfo> list) {
        if (AppUtils.isEmpty(list)) {
            return;
        }
        AlarmInfo alarmInfo = list.get(this.c);
        SetVibrationAlarmRequest setAlarmReq = new SetVibrationAlarmRequest.Builder().setAlarmId(alarmInfo.getAlarmId()).setHour(alarmInfo.getHour()).setMinute(alarmInfo.getMinute()).setEnabled(alarmInfo.isAlarmOn()).setEventName(alarmInfo.getEventName()).setRepeatEnabled(e(alarmInfo)).setSundayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[0]).setMondayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[1]).setTuesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[2]).setWednesdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[3]).setThursdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[4]).setFridayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[5]).setSaturdayEnabled(alarmInfo.getDaysSelected().getIsDaySelected()[6]).build();
        BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setAlarmReq, "setAlarmReq");
        bleApi.setUserSettings(setAlarmReq, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendAlarmDataToBle$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                int i3;
                int i4;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.c;
                settingsSyncHelper.c = i + 1;
                i2 = SettingsSyncHelper.this.c;
                if (i2 < list.size()) {
                    SettingsSyncHelper.this.g(list);
                    return;
                }
                LogHelper.d("SettingsSyncHelper", " Alarm settings success");
                SettingsSyncHelper settingsSyncHelper2 = SettingsSyncHelper.this;
                i3 = settingsSyncHelper2.f;
                settingsSyncHelper2.f = i3 - 1;
                i4 = SettingsSyncHelper.this.f;
                if (i4 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f4198a;
    }

    @NotNull
    public final String getConvertedInterval(int i) {
        return i != 15 ? i != 30 ? i != 45 ? i != 60 ? i != 90 ? i != 120 ? i != 150 ? "01:00:00" : "02:30:00" : "02:00:00" : "01:30:00" : "01:00:00" : "00:45:00" : "00:30:00" : "00:15:00";
    }

    @NotNull
    public final VibrationAlarmData getVibrationAlarmData(@NotNull Alarm alarm) {
        List<VibrationAlarmData> vibrationAlarmData;
        Intrinsics.checkNotNullParameter(alarm, "alarm");
        VibrationAlarmData vibrationAlarmData2 = VibrationAlarmData.getInstance();
        Intrinsics.checkNotNullExpressionValue(vibrationAlarmData2, "getInstance()");
        if (alarm.getEventName() != null) {
            String eventName = alarm.getEventName();
            Intrinsics.checkNotNull(eventName);
            if (!(eventName.length() == 0)) {
                vibrationAlarmData2.setEvent_name(alarm.getEventName());
                vibrationAlarmData2.setEvent_time_hour(alarm.getHour());
                vibrationAlarmData2.setEvent_time_minutes(alarm.getMinute());
                vibrationAlarmData2.setSwitch_on_off(alarm.isEnabled());
                vibrationAlarmData2.setWeeks(c(alarm));
                vibrationAlarmData2.setMonday(alarm.isMondayEnabled());
                vibrationAlarmData2.setTuesday(alarm.isTuesdayEnabled());
                vibrationAlarmData2.setWednesday(alarm.isWednesdayEnabled());
                vibrationAlarmData2.setThrusday(alarm.isThursdayEnabled());
                vibrationAlarmData2.setFriday(alarm.isFridayEnabled());
                vibrationAlarmData2.setSaturday(alarm.isSaturdayEnabled());
                vibrationAlarmData2.setSunday(alarm.isSundayEnabled());
                vibrationAlarmData2.setAlarmId(String.valueOf(alarm.getAlarmId()));
                vibrationAlarmData2.setAlarmType(alarm.getAlarmType());
                vibrationAlarmData = UserDataManager.getInstance(this.f4198a).getVibrationAlarmData();
                if (vibrationAlarmData != null && vibrationAlarmData.size() > 0) {
                    vibrationAlarmData2.setSnoozeDuration(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeDuration());
                    vibrationAlarmData2.setSnoozeRepeatCount(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeRepeatCount());
                }
                return vibrationAlarmData2;
            }
        }
        StringBuilder sb = new StringBuilder();
        String string = this.f4198a.getResources().getString(R.string.eventname2);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.eventname2)");
        sb.append((String) StringsKt__StringsKt.split$default((CharSequence) string, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
        sb.append(' ');
        sb.append(alarm.getAlarmId());
        vibrationAlarmData2.setEvent_name(sb.toString());
        vibrationAlarmData2.setEvent_time_hour(alarm.getHour());
        vibrationAlarmData2.setEvent_time_minutes(alarm.getMinute());
        vibrationAlarmData2.setSwitch_on_off(alarm.isEnabled());
        vibrationAlarmData2.setWeeks(c(alarm));
        vibrationAlarmData2.setMonday(alarm.isMondayEnabled());
        vibrationAlarmData2.setTuesday(alarm.isTuesdayEnabled());
        vibrationAlarmData2.setWednesday(alarm.isWednesdayEnabled());
        vibrationAlarmData2.setThrusday(alarm.isThursdayEnabled());
        vibrationAlarmData2.setFriday(alarm.isFridayEnabled());
        vibrationAlarmData2.setSaturday(alarm.isSaturdayEnabled());
        vibrationAlarmData2.setSunday(alarm.isSundayEnabled());
        vibrationAlarmData2.setAlarmId(String.valueOf(alarm.getAlarmId()));
        vibrationAlarmData2.setAlarmType(alarm.getAlarmType());
        vibrationAlarmData = UserDataManager.getInstance(this.f4198a).getVibrationAlarmData();
        if (vibrationAlarmData != null) {
            vibrationAlarmData2.setSnoozeDuration(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeDuration());
            vibrationAlarmData2.setSnoozeRepeatCount(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeRepeatCount());
        }
        return vibrationAlarmData2;
    }

    public final void h() {
        AutoActivityDetectionData autoActivityDetectionData = UserDataManager.getInstance(this.f4198a).getAutoActivityDetectionData();
        if (autoActivityDetectionData != null) {
            SetAutoActivityDetectionSettingsRequest setAutoActivityDetectionSettingsRequest = new SetAutoActivityDetectionSettingsRequest();
            ArrayList arrayList = new ArrayList();
            Boolean smartModeEnabled = autoActivityDetectionData.getSmartModeEnabled();
            Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "activityDetectionData.smartModeEnabled");
            if (smartModeEnabled.booleanValue()) {
                List<AutoActivityDetectionData.Period> periods = autoActivityDetectionData.getPeriods();
                if (!(periods == null || periods.isEmpty())) {
                    for (AutoActivityDetectionData.Period period : autoActivityDetectionData.getPeriods()) {
                        Integer startTime = period.getStartTime();
                        Intrinsics.checkNotNullExpressionValue(startTime, "p.startTime");
                        int intValue = startTime.intValue();
                        Integer endTime = period.getEndTime();
                        Intrinsics.checkNotNullExpressionValue(endTime, "p.endTime");
                        arrayList.add(new AutoActivityDetectionModel.Period(intValue, endTime.intValue()));
                    }
                }
            }
            this.f++;
            byte[] activities = autoActivityDetectionData.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities, "activityDetectionData.activities");
            Boolean sundayEnabled = autoActivityDetectionData.getSundayEnabled();
            Intrinsics.checkNotNullExpressionValue(sundayEnabled, "activityDetectionData.sundayEnabled");
            boolean booleanValue = sundayEnabled.booleanValue();
            Boolean mondayEnabled = autoActivityDetectionData.getMondayEnabled();
            Intrinsics.checkNotNullExpressionValue(mondayEnabled, "activityDetectionData.mondayEnabled");
            boolean booleanValue2 = mondayEnabled.booleanValue();
            Boolean tuesdayEnabled = autoActivityDetectionData.getTuesdayEnabled();
            Intrinsics.checkNotNullExpressionValue(tuesdayEnabled, "activityDetectionData.tuesdayEnabled");
            boolean booleanValue3 = tuesdayEnabled.booleanValue();
            Boolean wednesdayEnabled = autoActivityDetectionData.getWednesdayEnabled();
            Intrinsics.checkNotNullExpressionValue(wednesdayEnabled, "activityDetectionData.wednesdayEnabled");
            boolean booleanValue4 = wednesdayEnabled.booleanValue();
            Boolean thursdayEnabled = autoActivityDetectionData.getThursdayEnabled();
            Intrinsics.checkNotNullExpressionValue(thursdayEnabled, "activityDetectionData.thursdayEnabled");
            boolean booleanValue5 = thursdayEnabled.booleanValue();
            Boolean fridayEnabled = autoActivityDetectionData.getFridayEnabled();
            Intrinsics.checkNotNullExpressionValue(fridayEnabled, "activityDetectionData.fridayEnabled");
            boolean booleanValue6 = fridayEnabled.booleanValue();
            Boolean saturdayEnabled = autoActivityDetectionData.getSaturdayEnabled();
            Intrinsics.checkNotNullExpressionValue(saturdayEnabled, "activityDetectionData.saturdayEnabled");
            boolean booleanValue7 = saturdayEnabled.booleanValue();
            Integer countDownValue = autoActivityDetectionData.getCountDownValue();
            Intrinsics.checkNotNullExpressionValue(countDownValue, "activityDetectionData.countDownValue");
            int intValue2 = countDownValue.intValue();
            Boolean vibrationEnabled = autoActivityDetectionData.getVibrationEnabled();
            Intrinsics.checkNotNullExpressionValue(vibrationEnabled, "activityDetectionData.vibrationEnabled");
            setAutoActivityDetectionSettingsRequest.setAutoActivityDetectionModel(new AutoActivityDetectionModel(activities, booleanValue, booleanValue2, booleanValue3, booleanValue4, booleanValue5, booleanValue6, booleanValue7, intValue2, vibrationEnabled.booleanValue(), arrayList));
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(setAutoActivityDetectionSettingsRequest, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendAutoRecognitionToWatchToKaHaDevice$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    String str2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "sendAutoRecognitionToWatchToKaHaDevice failed");
                    str2 = SettingsSyncHelper.this.d;
                    LogHelper.d(str2, error.toString());
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "sendAutoRecognitionToWatchToKaHaDevice success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        }
    }

    public final void j() {
        NotificationSettings notificationsData = UserDataManager.getInstance(this.f4198a).getNotificationsData();
        SetCallSmsSocialNotificationRequest callSmsSocialNotificationRequest = new SetCallSmsSocialNotificationRequest.Builder().setAppAlerts(notificationsData != null ? notificationsData.isCall_notifications() : false, true, true, true, true, true, true, true, true, true, true, true, true, true, true).setTelegramEnabled(true).setLineEnabled(true).setOtherAppEnabled(true).setWhatsAppBusinessEnabled(true).setCustomEventEnabled(true).setGmailEnabled(true).setNewsEnabled(true).setKaKaoTalkEnabled(true).setOutLookEnabled(true).setYahooEmailEnabled(true).setYoutubeEnabled(true).setLinkedInEnabled(true).build();
        this.f++;
        BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(callSmsSocialNotificationRequest, "callSmsSocialNotificationRequest");
        bleApi.setUserSettings(callSmsSocialNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendNotificationConfigurationToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "sendNotificationConfigurationToBand failed");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "sendNotificationConfigurationToBand success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void k() {
        ProbeDataRequest build = new ProbeDataRequest.Builder().setInterval(UserDataManager.getInstance(this.f4198a).getProbeInterval()).build();
        this.f++;
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendProbeCommand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("SettingsSyncHelper", " Probe settings success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void l() {
        int parseInt;
        int i;
        int duration;
        boolean isRespiratoryRateFeatureEnabled = UserDataManager.getInstance(this.f4198a).isRespiratoryRateFeatureEnabled(this.f4198a);
        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(this.f4198a).getRespiratoryRateRemoteConfig();
        PPGTypes pPGTypes = PPGTypes.PPG;
        int i2 = 0;
        if (isRespiratoryRateFeatureEnabled) {
            String startTime = respiratoryRateRemoteConfig.getStartTime();
            Intrinsics.checkNotNullExpressionValue(startTime, "respiratoryRateConfPref.startTime");
            List split$default = StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt2 = (Integer.parseInt((String) split$default.get(0)) * 60) + Integer.parseInt((String) split$default.get(1));
            String endTime = respiratoryRateRemoteConfig.getEndTime();
            Intrinsics.checkNotNullExpressionValue(endTime, "respiratoryRateConfPref.endTime");
            List split$default2 = StringsKt__StringsKt.split$default((CharSequence) endTime, new String[]{":"}, false, 0, 6, (Object) null);
            int parseInt3 = (Integer.parseInt((String) split$default2.get(0)) * 60) + Integer.parseInt((String) split$default2.get(1));
            String interval = respiratoryRateRemoteConfig.getInterval();
            Intrinsics.checkNotNullExpressionValue(interval, "respiratoryRateConfPref.interval");
            List split$default3 = StringsKt__StringsKt.split$default((CharSequence) interval, new String[]{":"}, false, 0, 6, (Object) null);
            parseInt = (Integer.parseInt((String) split$default3.get(0)) * 60) + Integer.parseInt((String) split$default3.get(1));
            i2 = parseInt2;
            i = parseInt3;
            duration = respiratoryRateRemoteConfig.getDuration();
        } else {
            parseInt = 60;
            duration = 60;
            i = 0;
        }
        this.f++;
        SetAutomaticPPGConfigRequest setAutomaticPPGConfigRequest = new SetAutomaticPPGConfigRequest.Builder(pPGTypes, i2, i, parseInt, duration).build();
        BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
        Intrinsics.checkNotNullExpressionValue(setAutomaticPPGConfigRequest, "setAutomaticPPGConfigRequest");
        bleApi.setUserSettings(setAutomaticPPGConfigRequest, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendRespiratoryRateSettingsToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                String str2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "Respiratory rate settings failed");
                str2 = SettingsSyncHelper.this.d;
                LogHelper.d(str2, error.toString());
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                int i3;
                int i4;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "Respiratory rate settings success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i3 = settingsSyncHelper.f;
                settingsSyncHelper.f = i3 - 1;
                i4 = SettingsSyncHelper.this.f;
                if (i4 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void m() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCZDevice(this.f4198a) && !companion.isCADevice(this.f4198a) && !companion.isCYDevice(this.f4198a) && !companion.isPS1Device(this.f4198a) && !companion.isBESDevice(this.f4198a)) {
            int i = this.f - 1;
            this.f = i;
            if (i == 0) {
                SettingsSyncListner settingsSyncListner = this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingsSyncCompleted();
                return;
            }
            return;
        }
        SportsPreferenceModel sportsNotification = SportsPreference.Companion.getSportsNotification(this.f4198a);
        if (sportsNotification != null && sportsNotification.isEnable() != null) {
            Boolean isEnable = sportsNotification.isEnable();
            Intrinsics.checkNotNull(isEnable);
            SportNotificationControlRequest sportsNotificationRequest = new SportNotificationControlRequest.Builder(isEnable.booleanValue()).build();
            BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(sportsNotificationRequest, "sportsNotificationRequest");
            bleApi.setUserSettings(sportsNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendSportNotificationControlRequest$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    int i2;
                    int i3;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "On Failure sendSportNotificationControlRequest");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i2 = settingsSyncHelper.f;
                    settingsSyncHelper.f = i2 - 1;
                    i3 = SettingsSyncHelper.this.f;
                    if (i3 == 0) {
                        settingsSyncListner2 = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner2);
                        settingsSyncListner2.onSettingsSyncCompleted();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    int i2;
                    int i3;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "On Success sendSportNotificationControlRequest");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i2 = settingsSyncHelper.f;
                    settingsSyncHelper.f = i2 - 1;
                    i3 = SettingsSyncHelper.this.f;
                    if (i3 == 0) {
                        settingsSyncListner2 = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner2);
                        settingsSyncListner2.onSettingsSyncCompleted();
                    }
                }
            });
            return;
        }
        int i2 = this.f - 1;
        this.f = i2;
        if (i2 == 0) {
            SettingsSyncListner settingsSyncListner2 = this.b;
            Intrinsics.checkNotNull(settingsSyncListner2);
            settingsSyncListner2.onSettingsSyncCompleted();
        }
    }

    public final void n(MeasurementUnitType measurementUnitType, boolean z, final SuccessResultListener successResultListener) {
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new SetWeatherConfigInfoRequest(measurementUnitType, z), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendWeatherConfigInfoToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SuccessResultListener successResultListener2 = SuccessResultListener.this;
                if (successResultListener2 != null) {
                    successResultListener2.onError(error.getErrorMsg());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SuccessResultListener successResultListener2 = SuccessResultListener.this;
                if (successResultListener2 != null) {
                    successResultListener2.onSuccess();
                }
            }
        });
    }

    public final void o() {
        this.c = 0;
        List<AlarmInfo> vibrationAlarmData = DataHelper.INSTANCE.getVibrationAlarmData(this.f4198a);
        if (AppUtils.isEmpty(vibrationAlarmData)) {
            return;
        }
        this.f++;
        g(vibrationAlarmData);
    }

    public final void p() {
        int autoHrInterval = UserDataManager.getInstance(this.f4198a).getAutoHrInterval();
        if (!UserDataManager.getInstance(this.f4198a).isAutoHrEnabled()) {
            autoHrInterval = 0;
        }
        this.f++;
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new HeartRateTimeIntervalRequest.Builder(autoHrInterval).build(), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncAutoHrSettings$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("SettingsSyncHelper", " Auto Hr settings success");
                if (BleApiManager.getInstance(SettingsSyncHelper.this.getContext()).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    SettingsSyncHelper.this.q();
                } else {
                    SettingsSyncHelper.this.m();
                }
            }
        });
    }

    public final void q() {
        UserDataManager.getInstance(this.f4198a).getAutoHrInterval();
        UserDataManager.getInstance(this.f4198a).isAutoHrEnabled();
        int autoTemperatureInterval = UserDataManager.getInstance(this.f4198a).getAutoTemperatureInterval();
        if (!UserDataManager.getInstance(this.f4198a).getAutoTemperatureEnabled()) {
            autoTemperatureInterval = 0;
        }
        if (BleApiManager.getInstance(this.f4198a).getDeviceType() != DeviceType.v7) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCZDevice(this.f4198a) && !companion.isCADevice(this.f4198a) && !companion.isCYDevice(this.f4198a) && !companion.isPS1Device(this.f4198a) && !companion.isBESDevice(this.f4198a)) {
                if (BleApiManager.getInstance(this.f4198a).getDeviceType() == DeviceType.smaF2) {
                    BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(autoTemperatureInterval).setOpen(1).build(), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncAutoTemperatureSettings$2
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                            Intrinsics.checkNotNullParameter(error, "error");
                            settingsSyncListner = SettingsSyncHelper.this.b;
                            Intrinsics.checkNotNull(settingsSyncListner);
                            settingsSyncListner.onSettingSyncError();
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            int i;
                            int i2;
                            SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                            Intrinsics.checkNotNullParameter(response, "response");
                            SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                            i = settingsSyncHelper.f;
                            settingsSyncHelper.f = i - 1;
                            i2 = SettingsSyncHelper.this.f;
                            if (i2 == 0) {
                                settingsSyncListner = SettingsSyncHelper.this.b;
                                Intrinsics.checkNotNull(settingsSyncListner);
                                settingsSyncListner.onSettingsSyncCompleted();
                            }
                        }
                    });
                    return;
                }
                int i = this.f - 1;
                this.f = i;
                if (i == 0) {
                    SettingsSyncListner settingsSyncListner = this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                    return;
                }
                return;
            }
        }
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new TemperatureTimeIntervalRequest.Builder(autoTemperatureInterval).setOpen(1).build(), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncAutoTemperatureSettings$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2;
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner2 = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SettingsSyncHelper.this.m();
            }
        });
    }

    public final void r(final SettingsSyncListner settingsSyncListner) {
        ArrayList<CoveContact> syncedContacts = UserDataManager.getInstance(this.f4198a).getSyncedContacts();
        if (syncedContacts != null) {
            ArrayList<ContactData> arrayList = new ArrayList<>();
            Iterator<CoveContact> it = syncedContacts.iterator();
            while (it.hasNext()) {
                CoveContact next = it.next();
                String name = next.getName();
                Intrinsics.checkNotNullExpressionValue(name, "contact.name");
                String phoneNumber = next.getPhoneNumber();
                Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
                arrayList.add(new ContactData(name, phoneNumber));
            }
            SyncContactsRequest syncContactsRequest = new SyncContactsRequest.Builder().Builder(arrayList).build();
            this.f++;
            BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(syncContactsRequest, "syncContactsRequest");
            bleApi.getData(syncContactsRequest, new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncBTContacts$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    SettingsSyncHelper.SettingsSyncListner.this.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    Intrinsics.checkNotNullParameter(response, "response");
                    SettingsSyncHelper settingsSyncHelper = this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = this.f;
                    if (i2 == 0) {
                        SettingsSyncHelper.SettingsSyncListner.this.onSettingsSyncCompleted();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }
    }

    public final void readAlarmInfoFromBand(@NotNull final SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(settingsSyncListner, "settingsSyncListner");
        BleApiManager.getInstance(this.f4198a).getBleApi().getData(new GetAlarmDataRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$readAlarmInfoFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingsSyncCompleted();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String c;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof List) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.bleabstract.models.Alarm>");
                    List<Alarm> list = (List) responseData;
                    ArrayList arrayList = new ArrayList();
                    List<VibrationAlarmData> vibrationAlarmData = UserDataManager.getInstance(this.getContext()).getVibrationAlarmData();
                    if (!list.isEmpty()) {
                        for (Alarm alarm : list) {
                            int size = vibrationAlarmData.size();
                            int i = 0;
                            while (true) {
                                if (i >= size) {
                                    break;
                                } else if (vibrationAlarmData.get(i).getAlarmId().equals(String.valueOf(alarm.getAlarmId()))) {
                                    VibrationAlarmData vibrationAlarm = vibrationAlarmData.get(i);
                                    if (alarm.getEventName() != null) {
                                        String eventName = alarm.getEventName();
                                        Intrinsics.checkNotNull(eventName);
                                        if (eventName.length() > 0) {
                                            vibrationAlarm.setEvent_name(alarm.getEventName());
                                        }
                                    }
                                    vibrationAlarm.setEvent_time_hour(alarm.getHour());
                                    vibrationAlarm.setEvent_time_minutes(alarm.getMinute());
                                    vibrationAlarm.setSwitch_on_off(alarm.isEnabled());
                                    c = this.c(alarm);
                                    vibrationAlarm.setWeeks(c);
                                    vibrationAlarm.setMonday(alarm.isMondayEnabled());
                                    vibrationAlarm.setTuesday(alarm.isTuesdayEnabled());
                                    vibrationAlarm.setWednesday(alarm.isWednesdayEnabled());
                                    vibrationAlarm.setThrusday(alarm.isThursdayEnabled());
                                    vibrationAlarm.setFriday(alarm.isFridayEnabled());
                                    vibrationAlarm.setSaturday(alarm.isSaturdayEnabled());
                                    vibrationAlarm.setSunday(alarm.isSundayEnabled());
                                    vibrationAlarm.setSnoozeDuration(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeDuration());
                                    vibrationAlarm.setSnoozeRepeatCount(vibrationAlarmData.get(vibrationAlarmData.size() - 1).getSnoozeRepeatCount());
                                    Intrinsics.checkNotNullExpressionValue(vibrationAlarm, "vibrationAlarm");
                                    arrayList.add(vibrationAlarm);
                                    r5 = true;
                                } else {
                                    i++;
                                }
                            }
                            if (!r5) {
                                arrayList.add(SettingsSyncHelper.Companion.getInstance(this.getContext()).getVibrationAlarmData(alarm));
                            }
                        }
                    }
                    UserDataManager.getInstance(this.getContext()).saveVibrationAlarmSettings(arrayList);
                    SettingsSyncHelper settingsSyncHelper = this;
                    settingsSyncHelper.saveAlarmsToServer(settingsSyncHelper.getContext(), this.ConvertFromVibrateToAlarminfo());
                }
                if (!DeviceUtils.Companion.isMoyangDevice(this.getContext())) {
                    this.f(SettingsSyncHelper.SettingsSyncListner.this);
                    return;
                }
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingsSyncCompleted();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void readDndInfoFromBand(@NotNull final SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(settingsSyncListner, "settingsSyncListner");
        BleApiManager.getInstance(this.f4198a).getBleApi().getData(new GetDNDModeSettingsRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$readDndInfoFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingsSyncCompleted();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() != null && (response.getResponseData() instanceof DNDData)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.models.DNDData");
                    DNDData dNDData = (DNDData) responseData;
                    DoNotDisturbData doNotDisturbData = UserDataManager.getInstance(this.getContext()).getDoNotDisturbData();
                    if (doNotDisturbData == null) {
                        doNotDisturbData = DoNotDisturbData.getInstance();
                    }
                    if (dNDData.getStartHour() == 0 && dNDData.getStartMin() == 0 && ((dNDData.getEndHour() == 23 && dNDData.getEndMin() == 59) || (dNDData.getEndHour() == 0 && dNDData.getEndMin() == 0))) {
                        Intrinsics.checkNotNull(doNotDisturbData);
                        doNotDisturbData.setDnd_on_off(dNDData.isDNDEnabled());
                        doNotDisturbData.setSchedule_on_off(false);
                    } else {
                        Intrinsics.checkNotNull(doNotDisturbData);
                        doNotDisturbData.setDnd_on_off(false);
                        doNotDisturbData.setSchedule_on_off(dNDData.isDNDEnabled());
                    }
                    doNotDisturbData.setBeggining_time_hour(dNDData.getStartHour());
                    doNotDisturbData.setBeggining_time_minutes(dNDData.getStartMin());
                    doNotDisturbData.setEnd_time_hour(dNDData.getEndHour());
                    doNotDisturbData.setEnd_time_minutes(dNDData.getEndMin());
                    UserDataManager.getInstance(this.getContext()).saveDoNotDisturbSettings(doNotDisturbData);
                    UserDataManager.getInstance(this.getContext()).saveScheuleDnd(dNDData.isDNDEnabled());
                    this.uploadDndToServer(doNotDisturbData.getBeggining_time_hour(), doNotDisturbData.getBeggining_time_minutes(), doNotDisturbData.getEnd_time_hour(), doNotDisturbData.getEnd_time_minutes(), doNotDisturbData.isSchedule_on_off(), doNotDisturbData.isDnd_on_off());
                } else if (response.getResponseData() != null && (response.getResponseData() instanceof DNDSettingsData)) {
                    Object responseData2 = response.getResponseData();
                    Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.model.DNDSettingsData");
                    DNDSettingsData dNDSettingsData = (DNDSettingsData) responseData2;
                    DoNotDisturbData doNotDisturbData2 = UserDataManager.getInstance(this.getContext()).getDoNotDisturbData();
                    if (doNotDisturbData2 == null) {
                        doNotDisturbData2 = DoNotDisturbData.getInstance();
                    }
                    if (dNDSettingsData.getStartHour() == 0 && dNDSettingsData.getStartMin() == 0 && dNDSettingsData.getEndHour() == 23 && dNDSettingsData.getEndMin() == 59) {
                        Intrinsics.checkNotNull(doNotDisturbData2);
                        doNotDisturbData2.setDnd_on_off(dNDSettingsData.isDNDEnabled());
                        doNotDisturbData2.setSchedule_on_off(false);
                    } else {
                        DeviceUtils.Companion companion = DeviceUtils.Companion;
                        if ((companion.isCZDevice(this.getContext()) || companion.isCADevice(this.getContext()) || companion.isCYDevice(this.getContext()) || companion.isPS1Device(this.getContext()) || companion.isBESDevice(this.getContext())) && dNDSettingsData.getStartHour() == 0 && dNDSettingsData.getStartMin() == 0 && ((dNDSettingsData.getEndHour() == 23 && dNDSettingsData.getEndMin() == 59) || (dNDSettingsData.getEndHour() == 0 && dNDSettingsData.getEndMin() == 0))) {
                            Intrinsics.checkNotNull(doNotDisturbData2);
                            doNotDisturbData2.setDnd_on_off(dNDSettingsData.isDNDEnabled());
                            doNotDisturbData2.setSchedule_on_off(false);
                        } else {
                            Intrinsics.checkNotNull(doNotDisturbData2);
                            doNotDisturbData2.setDnd_on_off(false);
                            doNotDisturbData2.setSchedule_on_off(dNDSettingsData.isDNDEnabled());
                        }
                    }
                    if (doNotDisturbData2.isSchedule_on_off()) {
                        doNotDisturbData2.setBeggining_time_hour(dNDSettingsData.getStartHour());
                        doNotDisturbData2.setBeggining_time_minutes(dNDSettingsData.getStartMin());
                        doNotDisturbData2.setEnd_time_hour(dNDSettingsData.getEndHour());
                        doNotDisturbData2.setEnd_time_minutes(dNDSettingsData.getEndMin());
                    }
                    UserDataManager.getInstance(this.getContext()).saveDoNotDisturbSettings(doNotDisturbData2);
                    UserDataManager.getInstance(this.getContext()).saveScheuleDnd(dNDSettingsData.isDNDEnabled());
                    this.uploadDndToServer(doNotDisturbData2.getBeggining_time_hour(), doNotDisturbData2.getBeggining_time_minutes(), doNotDisturbData2.getEnd_time_hour(), doNotDisturbData2.getEnd_time_minutes(), doNotDisturbData2.isSchedule_on_off(), doNotDisturbData2.isDnd_on_off());
                }
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingsSyncCompleted();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void s(final SettingsSyncListner settingsSyncListner) {
        ReminderHelper.Companion.getInstance(this.f4198a).sendRemindersToWatch((ArrayList) PreferenceManagerCustomReminders.Companion.getReminders(this.f4198a), new ResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncCustomRemindersToWatch$1
            @Override // com.coveiot.android.customreminders.listeners.ResultListener
            public void onError(@Nullable String str) {
                String str2;
                str2 = SettingsSyncHelper.this.d;
                LogHelper.d(str2, "syncCustomRemindersToWatch failed");
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingSyncError();
            }

            @Override // com.coveiot.android.customreminders.listeners.ResultListener
            public void onSuccess() {
                String str;
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "syncCustomRemindersToWatch success");
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                Intrinsics.checkNotNull(settingsSyncListner2);
                settingsSyncListner2.onSettingsSyncCompleted();
            }
        });
    }

    public final void saveAlarmsToServer(@NotNull Context context, @NotNull List<AlarmInfo> alarmInfos) {
        boolean z;
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(alarmInfos, "alarmInfos");
        if (AppUtils.isNetConnected(context)) {
            new SaveAlarmSettingReq.AlarmListBean();
            ArrayList arrayList = new ArrayList();
            Snooze snooze = new Snooze();
            if (alarmInfos.size() <= 0 || alarmInfos.get(0).getAlarmId() > 0) {
                z = false;
                i = 1;
            } else if (alarmInfos.get(0).getAlarmId() == 0) {
                i = 1;
                z = true;
            } else {
                i = Math.abs(alarmInfos.get(0).getAlarmId() - 1);
                z = true;
            }
            for (AlarmInfo alarmInfo : alarmInfos) {
                snooze.setInterval(String.valueOf(alarmInfo.getSnoozeDuration()));
                snooze.setMaxAllowed(Integer.valueOf(alarmInfo.getSnoozeRepeatCount()));
                SaveAlarmSettingReq.AlarmListBean alarmListBean = new SaveAlarmSettingReq.AlarmListBean();
                if (z) {
                    alarmListBean.setAlarmId(String.valueOf(alarmInfo.getAlarmId() + i));
                } else {
                    alarmListBean.setAlarmId(String.valueOf(alarmInfo.getAlarmId()));
                }
                alarmListBean.setActive(alarmInfo.isAlarmOn());
                StringBuilder sb = new StringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getHour())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                sb.append(format);
                sb.append(':');
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(alarmInfo.getMinute())}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb.append(format2);
                sb.append(":00");
                alarmListBean.setTime(sb.toString());
                alarmListBean.setLabel(alarmInfo.getEventName());
                AlarmInfo.Days daysSelected = alarmInfo.getDaysSelected();
                String str = "";
                if (daysSelected != null && daysSelected.getIsDaySelected() != null) {
                    int length = daysSelected.getIsDaySelected().length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 == 0) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.S) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 1) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.M) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 2) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.T) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 3) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.W) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 4) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.T) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 5) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.F) : str + Soundex.SILENT_MARKER;
                        } else if (i2 == 6) {
                            str = daysSelected.getIsDaySelected()[i2] ? str + context.getResources().getString(R.string.S) : str + Soundex.SILENT_MARKER;
                        }
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = context.getResources().getString(R.string.week_empty);
                    Intrinsics.checkNotNullExpressionValue(str, "context.resources.getString(R.string.week_empty)");
                    alarmListBean.setRepeat(false);
                }
                if (m.equals(str, context.getResources().getString(R.string.week_empty), true)) {
                    alarmListBean.setRepeat(false);
                } else {
                    alarmListBean.setRepeat(true);
                }
                alarmListBean.setWeek(str);
                arrayList.add(alarmListBean);
            }
            SaveAlarmSettingReq saveAlarmSettingReq = new SaveAlarmSettingReq();
            saveAlarmSettingReq.setSnooze(snooze);
            saveAlarmSettingReq.setAlarmListBeans(arrayList);
            CoveUserAppSettings.saveAlarmSettings(saveAlarmSettingReq, new CoveApiListener<SaveAlarmSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$saveAlarmsToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveAlarmSettingRes saveAlarmSettingRes) {
                }
            });
        }
    }

    public final void sendHourFormart() {
        Boolean is12Hour = UserDataManager.getInstance(this.f4198a).isTimeIn12HourFormat();
        if (BleApiManager.getInstance(this.f4198a).getBleApi().getDeviceSupportedFeatures().isDeviceSettingsSupportedInOneCommand()) {
            Boolean isRightHandSelected = UserDataManager.getInstance(this.f4198a).isRightHandSelected();
            Boolean distanceUnitPref = UserDataManager.getInstance(this.f4198a).isDistanceUnitInMile();
            Boolean isLiftWristEnabled = UserDataManager.getInstance(this.f4198a).isLiftWristToViewEnable();
            Boolean isTemperatureUnitInFahrenheit = UserDataManager.getInstance(this.f4198a).isTemperatureUnitInFahrenheit();
            SetDeviceParametersRequest.Builder handState = new SetDeviceParametersRequest.Builder().setHandState(!isRightHandSelected.booleanValue());
            Intrinsics.checkNotNullExpressionValue(is12Hour, "is12Hour");
            SetDeviceParametersRequest.Builder hourFormat = handState.setHourFormat(is12Hour.booleanValue());
            Intrinsics.checkNotNullExpressionValue(distanceUnitPref, "distanceUnitPref");
            SetDeviceParametersRequest.Builder distanceUnit = hourFormat.setDistanceUnit(distanceUnitPref.booleanValue());
            Intrinsics.checkNotNullExpressionValue(isLiftWristEnabled, "isLiftWristEnabled");
            SetDeviceParametersRequest build = distanceUnit.setLiftWristEnable(isLiftWristEnabled.booleanValue()).setTemperatureUnitInCelsius(!isTemperatureUnitInFahrenheit.booleanValue()).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setHandState(!…UnitInFahrenheit).build()");
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendHourFormart$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    String str2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "sendHourFormart failed");
                    str2 = SettingsSyncHelper.this.d;
                    LogHelper.d(str2, error.toString());
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    String str2;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, response.toString());
                    str2 = SettingsSyncHelper.this.d;
                    LogHelper.d(str2, "sendHourFormart success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
            return;
        }
        Intrinsics.checkNotNullExpressionValue(is12Hour, "is12Hour");
        SetHourFormatRequest build2 = new SetHourFormatRequest.Builder(is12Hour.booleanValue()).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder(is12Hour).build()");
        this.f++;
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build2, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendHourFormart$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                String str2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "sendHourFormart failed");
                str2 = SettingsSyncHelper.this.d;
                LogHelper.d(str2, error.toString());
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                String str2;
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, response.toString());
                str2 = SettingsSyncHelper.this.d;
                LogHelper.d(str2, "sendHourFormart success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void sendMusicNotPlayingMsg() {
        if (BleApiManager.getInstance(this.f4198a).getBleApi() == null || BleApiManager.getInstance(this.f4198a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        Intent intent = new Intent(Dashboard2Constants.MUSIC_METADATA_BROADCAST_INTENT);
        Context context = this.f4198a;
        int i = R.string.music_not_playing;
        intent.putExtra("title", context.getString(i));
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isCYDevice(this.f4198a) || !companion.isPS1Device(this.f4198a) || companion.isBESDevice(this.f4198a)) {
            intent.putExtra(Dashboard2Constants.MUSIC_METADATA_KEY_ARTIST, this.f4198a.getString(i));
            intent.putExtra(Dashboard2Constants.MUSIC_METADATA_KEY_ALBUM, this.f4198a.getString(i));
        }
        LocalBroadcastManager.getInstance(this.f4198a.getApplicationContext()).sendBroadcast(intent);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.dashboard2.a
            @Override // java.lang.Runnable
            public final void run() {
                SettingsSyncHelper.i(SettingsSyncHelper.this);
            }
        }, 500L);
        sendNotPlayingMusicMetaDataVolumeToWatch();
    }

    public final void sendNotPlayingMusicMetaDataVolumeToWatch() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
        if ((bleApi != null ? bleApi.getConnectionStatus() : null) == ConnectionStatus.CONNECTED) {
            BleApi bleApi2 = BleApiManager.getInstance(this.f4198a).getBleApi();
            boolean z = true;
            if (bleApi2 == null || (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures.isMusicDataSupportInSingleCommand()) {
                z = false;
            }
            if (z) {
                Object systemService = this.f4198a.getSystemService("audio");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
                AudioManager audioManager = (AudioManager) systemService;
                SetMusicMetaDataPlaybackVolumeRequest build = new SetMusicMetaDataPlaybackVolumeRequest.Builder().setVolume(audioManager.getStreamVolume(3)).setMaxVolume(audioManager.getStreamMaxVolume(3)).setTitle(this.f4198a.getString(R.string.music_not_playing)).setMusicControlState(MusicControlState.PAUSE).build();
                Context context = this.f4198a;
                Intrinsics.checkNotNull(context);
                BleApiManager.getInstance(context).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendNotPlayingMusicMetaDataVolumeToWatch$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        String str;
                        Intrinsics.checkNotNullParameter(response, "response");
                        str = SettingsSyncHelper.this.d;
                        LogHelper.i(str, "sendNotPlayingMusicMetaDataVolumeToWatch success");
                    }
                });
            }
        }
    }

    public final void sendSmartAlertSettingsToWatch(@NotNull SmartAlertSettings smartAlertSettings, final boolean z) {
        Intrinsics.checkNotNullParameter(smartAlertSettings, "smartAlertSettings");
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        List<SmartAlertSettings.App> apps = smartAlertSettings.getApps();
        boolean z2 = false;
        if (!(apps == null || apps.isEmpty())) {
            for (SmartAlertSettings.App app : smartAlertSettings.getApps()) {
                String packageName = app.getPackageName();
                if (!(packageName == null || packageName.length() == 0)) {
                    AppNotificationData appNotificationData = new AppNotificationData();
                    appNotificationData.setAppName(app.getName());
                    Boolean active = app.getActive();
                    Intrinsics.checkNotNullExpressionValue(active, "app.active");
                    appNotificationData.setChecked(active.booleanValue());
                    appNotificationData.setPackageName(app.getPackageName());
                    appNotificationData.setSmartAlertSupported(true);
                    arrayList2.add(appNotificationData);
                    Boolean active2 = app.getActive();
                    Intrinsics.checkNotNullExpressionValue(active2, "app.active");
                    if (active2.booleanValue()) {
                        SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
                        Context context = this.f4198a;
                        String packageName2 = app.getPackageName();
                        Intrinsics.checkNotNullExpressionValue(packageName2, "app.packageName");
                        SmartAlertAppServerConfData smartAlertServerAppConfigByPackageName = companion.getSmartAlertServerAppConfigByPackageName(context, packageName2);
                        if (smartAlertServerAppConfigByPackageName == null) {
                            break;
                        } else if (smartAlertServerAppConfigByPackageName.getDeviceData() != null) {
                            String name = smartAlertServerAppConfigByPackageName.getName();
                            if (!(name == null || name.length() == 0)) {
                                SmartAlertAppServerConfData.DeviceData deviceData = smartAlertServerAppConfigByPackageName.getDeviceData();
                                Intrinsics.checkNotNull(deviceData);
                                if (deviceData.getFontSize() != null) {
                                    SmartAlertAppServerConfData.DeviceData deviceData2 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                    Intrinsics.checkNotNull(deviceData2);
                                    String fontColor = deviceData2.getFontColor();
                                    if (!(fontColor == null || fontColor.length() == 0)) {
                                        SmartAlertAppServerConfData.DeviceData deviceData3 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        Intrinsics.checkNotNull(deviceData3);
                                        Integer appId = deviceData3.getAppId();
                                        Intrinsics.checkNotNull(appId);
                                        int intValue = appId.intValue();
                                        String name2 = smartAlertServerAppConfigByPackageName.getName();
                                        Intrinsics.checkNotNull(name2);
                                        SmartAlertAppServerConfData.DeviceData deviceData4 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        Intrinsics.checkNotNull(deviceData4);
                                        Integer fontSize = deviceData4.getFontSize();
                                        Intrinsics.checkNotNull(fontSize);
                                        int intValue2 = fontSize.intValue();
                                        SmartAlertAppServerConfData.DeviceData deviceData5 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        Intrinsics.checkNotNull(deviceData5);
                                        String fontColor2 = deviceData5.getFontColor();
                                        Intrinsics.checkNotNull(fontColor2);
                                        arrayList.add(new SmartAlertAppData(intValue, name2, intValue2, companion.convertHexTo565(fontColor2)));
                                    }
                                }
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        z2 = true;
        if (z2) {
            if (z) {
                this.f++;
            }
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new SetSmartAlertConfigRequest(!arrayList.isEmpty(), arrayList), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$sendSmartAlertSettingsToWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "Smart alert settings failed " + error.getErrorMsg());
                    if (z) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingSyncError();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "Smart alert settings success");
                    SmartAlertPreferenceManager.Companion.getInstance(SettingsSyncHelper.this.getContext()).saveSmartAlertAppNotificationsSettings(arrayList2);
                    if (z) {
                        SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                        i = settingsSyncHelper.f;
                        settingsSyncHelper.f = i - 1;
                        i2 = SettingsSyncHelper.this.f;
                        if (i2 == 0) {
                            settingsSyncListner = SettingsSyncHelper.this.b;
                            Intrinsics.checkNotNull(settingsSyncListner);
                            settingsSyncListner.onSettingsSyncCompleted();
                        }
                    }
                }
            });
        }
    }

    public final void setCurrentVolumeToWatch() {
        if (BleApiManager.getInstance(this.f4198a).getBleApi() != null && BleApiManager.getInstance(this.f4198a).getBleApi().getDeviceSupportedFeatures().isBandVolumeControlSupported() && BleApiManager.getInstance(this.f4198a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Object systemService = this.f4198a.getSystemService("audio");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            AudioManager audioManager = (AudioManager) systemService;
            Objects.requireNonNull(audioManager);
            final int streamVolume = (int) ((audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3)) * 100.0f);
            SetMusicVolumePercentageChangedRequest setPlaybackState2 = new SetMusicVolumePercentageChangedRequest.Builder(streamVolume).build();
            BleApi bleApi = BleApiManager.getInstance(this.f4198a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setPlaybackState2, "setPlaybackState2");
            bleApi.setUserSettings(setPlaybackState2, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$setCurrentVolumeToWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, String.valueOf(error.getErrorMsg()));
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "SetMusicVolumePercentageChangedRequest Sent successfully. Volume change to " + streamVolume);
                }
            });
        }
    }

    public final void syncBandSettings(@NotNull final SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(settingsSyncListner, "settingsSyncListner");
        if (BleApiManager.getInstance(this.f4198a).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            return;
        }
        if (!BleApiManager.getInstance(this.f4198a).getDeviceType().equals(DeviceType.jstyle1963YH) && !BleApiManager.getInstance(this.f4198a).getDeviceType().equals(DeviceType.jstyle1860)) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isSmaDevice(this.f4198a) && !companion.isMoyangDevice(this.f4198a) && !companion.isCZDevice(this.f4198a) && !companion.isMatrixDevice(this.f4198a) && !companion.isCADevice(this.f4198a) && !companion.isIDODevice(this.f4198a) && !companion.isCYDevice(this.f4198a) && !companion.isTouchELXDevice(this.f4198a) && !companion.isEastApexDevice(this.f4198a) && !companion.isPS1Device(this.f4198a) && !companion.isBESDevice(this.f4198a)) {
                settingsSyncListner.onSettingsSyncCompleted();
                return;
            } else if (companion.isMoyangDevice(this.f4198a)) {
                if (UserDataManager.getInstance(this.f4198a).getVertexForceLiftWristToViewPushed()) {
                    readAlarmInfoFromBand(settingsSyncListner);
                    return;
                }
                Boolean isLiftWristToViewOn = UserDataManager.getInstance(this.f4198a).isLiftWristToViewEnable();
                Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
                SetLiftWristRequest.Builder builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
                builder.setStartHour(UserDataManager.getInstance(this.f4198a).getLiftWristToViewStartHour());
                builder.setStartMinute(UserDataManager.getInstance(this.f4198a).getLiftWristToViewStartMin());
                builder.setEndHour(UserDataManager.getInstance(this.f4198a).getLiftWristToViewEndHour());
                builder.setEndMinute(UserDataManager.getInstance(this.f4198a).getLiftWristToViewEndMin());
                SetLiftWristRequest build = builder.build();
                Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
                BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncBandSettings$2
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        SettingsSyncHelper.this.readAlarmInfoFromBand(settingsSyncListner);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        UserDataManager.getInstance(SettingsSyncHelper.this.getContext()).saveVertexForceLiftWristToViewPushed(Boolean.TRUE);
                        SettingsSyncHelper.this.readAlarmInfoFromBand(settingsSyncListner);
                    }
                });
                return;
            } else {
                readAlarmInfoFromBand(settingsSyncListner);
                return;
            }
        }
        BleApiManager.getInstance(this.f4198a).getBleApi().getData(new GetDeviceSettingsInfoRequest(), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncBandSettings$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SettingsSyncHelper.SettingsSyncListner.this.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof DeviceSettingsInfoResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceSettingsInfoResponse");
                    DeviceSettingsInfoResponse deviceSettingsInfoResponse = (DeviceSettingsInfoResponse) responseData;
                    UserDataManager.getInstance(this.getContext()).saveDistanceUnitPref(Boolean.valueOf(deviceSettingsInfoResponse.isDistanceUnitInMile()));
                    UserDataManager.getInstance(this.getContext()).saveTemperatureUnitPref(Boolean.valueOf(deviceSettingsInfoResponse.isTemperatureUnitInFahrenheit()));
                    UserDataManager.getInstance(this.getContext()).saveLiftWristPref(Boolean.valueOf(deviceSettingsInfoResponse.isLiftWristON()));
                    UserDataManager.getInstance(this.getContext()).saveHourFormatPref(Boolean.valueOf(deviceSettingsInfoResponse.isTimeIn12HRFormat()));
                }
                SettingsSyncHelper.SettingsSyncListner.this.onSettingsSyncCompleted();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncBpCalibration() {
        BpCalibrationData bpCalibrationData = UserDataManager.getInstance(this.f4198a).getBpCalibrationData();
        if (bpCalibrationData != null) {
            BpCalibrationDataRequest build = new BpCalibrationDataRequest.Builder().setDbpCalculatingSign(bpCalibrationData.getCalculatingSignDbp()).setDbp(bpCalibrationData.getDbp()).setSbpCalculatingSign(bpCalibrationData.getCalculatingSignSbp()).setSbp(bpCalibrationData.getSbp()).build();
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncBpCalibration$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    LogHelper.d("SettingsSyncHelper", " BpCalibration success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        }
    }

    public final void syncQuickReplySettings(@NotNull final SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(settingsSyncListner, "settingsSyncListner");
        QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(this.f4198a).getQuickReplyListFromPref();
        if (quickReplyListFromPref != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.clear();
            arrayList.add(new BleQuickReplyModel(quickReplyListFromPref.getQuickReplyMessage().get(0)));
            arrayList.add(new BleQuickReplyModel(quickReplyListFromPref.getQuickReplyMessage().get(1)));
            arrayList.add(new BleQuickReplyModel(quickReplyListFromPref.getQuickReplyMessage().get(2)));
            arrayList.add(new BleQuickReplyModel(quickReplyListFromPref.getQuickReplyMessage().get(3)));
            arrayList.add(new BleQuickReplyModel(quickReplyListFromPref.getQuickReplyMessage().get(4)));
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(new SetQuickReplyRequest.Builder().setQuickReply(quickReplyListFromPref.isQuickReplyEnabled(), arrayList).build(), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncQuickReplySettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = this.d;
                    LogHelper.d(str, " syncQuickReplySettings Error" + error.getErrorMsg());
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = SettingsSyncHelper.SettingsSyncListner.this;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingsSyncCompleted();
                    str = this.d;
                    LogHelper.d(str, " syncQuickReplySettings Success");
                }
            });
            return;
        }
        settingsSyncListner.onSettingsSyncCompleted();
    }

    public final void syncSettings(@NotNull final SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(settingsSyncListner, "settingsSyncListner");
        if (this.e != null) {
            this.f = 0;
            this.b = settingsSyncListner;
            this.e = BleApiManager.getInstance(this.f4198a).getBleApi().getDeviceSupportedFeatures();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isCZDevice(this.f4198a) || companion.isCADevice(this.f4198a) || companion.isCYDevice(this.f4198a) || companion.isPS1Device(this.f4198a) || companion.isBESDevice(this.f4198a)) {
                UserDataManager.getInstance(this.f4198a).resetHeathData(this.f4198a);
            }
            if (BleApiManager.getInstance(this.f4198a).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported()) {
                syncQuickReplySettings(new SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSettings$1
                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onProgressUpdate(int i) {
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingSyncError() {
                        DeviceSupportedFeatures deviceSupportedFeatures;
                        deviceSupportedFeatures = SettingsSyncHelper.this.e;
                        Intrinsics.checkNotNull(deviceSupportedFeatures);
                        if (!deviceSupportedFeatures.isCustomRemindersSupported()) {
                            SettingsSyncHelper.this.B(settingsSyncListner);
                            return;
                        }
                        final SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                        final SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                        settingsSyncHelper.s(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSettings$1$onSettingSyncError$1
                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onProgressUpdate(int i) {
                            }

                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onSettingSyncError() {
                                SettingsSyncHelper.this.B(settingsSyncListner2);
                            }

                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onSettingsSyncCompleted() {
                                SettingsSyncHelper.this.B(settingsSyncListner2);
                            }
                        });
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingsSyncCompleted() {
                        DeviceSupportedFeatures deviceSupportedFeatures;
                        deviceSupportedFeatures = SettingsSyncHelper.this.e;
                        Intrinsics.checkNotNull(deviceSupportedFeatures);
                        if (!deviceSupportedFeatures.isCustomRemindersSupported()) {
                            SettingsSyncHelper.this.B(settingsSyncListner);
                            return;
                        }
                        final SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                        final SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                        settingsSyncHelper.s(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSettings$1$onSettingsSyncCompleted$1
                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onProgressUpdate(int i) {
                            }

                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onSettingSyncError() {
                                SettingsSyncHelper.this.B(settingsSyncListner2);
                            }

                            @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                            public void onSettingsSyncCompleted() {
                                SettingsSyncHelper.this.B(settingsSyncListner2);
                            }
                        });
                    }
                });
                return;
            }
            DeviceSupportedFeatures deviceSupportedFeatures = this.e;
            Intrinsics.checkNotNull(deviceSupportedFeatures);
            if (deviceSupportedFeatures.isCustomRemindersSupported()) {
                s(new SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSettings$2
                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onProgressUpdate(int i) {
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingSyncError() {
                        SettingsSyncHelper.this.B(settingsSyncListner);
                    }

                    @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                    public void onSettingsSyncCompleted() {
                        SettingsSyncHelper.this.B(settingsSyncListner);
                    }
                });
                return;
            } else {
                B(settingsSyncListner);
                return;
            }
        }
        B(settingsSyncListner);
    }

    public final void t() {
        if (DeviceUtils.Companion.isSmaDevice(this.f4198a)) {
            this.f++;
            ProfileData userDetails = SessionManager.getInstance(this.f4198a).getUserDetails();
            Intrinsics.checkNotNullExpressionValue(userDetails, "getInstance(context).userDetails");
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            int parseInt = Integer.parseInt(height);
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            SetFitnessInfoRequest.Builder builder = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
            builder.setStride(ProfileRepository.getInstance().getLatestProfileData(this.f4198a).walkStrideLength);
            builder.setRunStride(ProfileRepository.getInstance().getLatestProfileData(this.f4198a).runStrideLength);
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f4198a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context)\n   …  .isDistanceUnitInMile()");
            builder.setUnitType(isDistanceUnitInMile.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC);
            String dob = userDetails.getDob();
            boolean z = false;
            if (!(dob == null || dob.length() == 0)) {
                builder.setAge(AppUtils.getAge(userDetails.getDob()));
            }
            String gender = userDetails.getGender();
            if (gender == null || gender.length() == 0) {
                z = true;
            }
            if (!z) {
                builder.setMale(m.equals(userDetails.getGender(), "Male", true));
            }
            SetFitnessInfoRequest builder2 = builder.builder();
            Intrinsics.checkNotNullExpressionValue(builder2, "fitnessInfoRequestBuilder.builder()");
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(builder2, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncDistanceUnit$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, error.toString());
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, response.toString());
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
            return;
        }
        this.f++;
        Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this.f4198a).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile()");
        SetDistanceUnitRequest build = new SetDistanceUnitRequest.Builder(isDistanceUnitInMile2.booleanValue()).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …e()\n            ).build()");
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncDistanceUnit$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, error.toString());
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "on Success SetDistanceUnitRequest");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void u() {
        SetDNDModeRequest build;
        DoNotDisturbData doNotDisturbData = UserDataManager.getInstance(this.f4198a).getDoNotDisturbData();
        if (doNotDisturbData != null) {
            if (doNotDisturbData.isDnd_on_off()) {
                build = new SetDNDModeRequest.Builder(true, 0, 0, 0, 0).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder(true, 0, 0, 0, 0).build()");
            } else if (doNotDisturbData.isSchedule_on_off()) {
                build = new SetDNDModeRequest.Builder(true, doNotDisturbData.getBeggining_time_hour(), doNotDisturbData.getBeggining_time_minutes(), doNotDisturbData.getEnd_time_hour(), doNotDisturbData.getEnd_time_minutes()).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder(\n               …                ).build()");
            } else {
                build = new SetDNDModeRequest.Builder(false, 0, 0, 23, 59).build();
                Intrinsics.checkNotNullExpressionValue(build, "Builder(false, 0, 0, 23, 59).build()");
            }
            this.f++;
            BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncDndToBand$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "syncDndToBand failed");
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingSyncError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    int i;
                    int i2;
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                    Intrinsics.checkNotNullParameter(response, "response");
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "syncDndToBand success");
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i - 1;
                    i2 = SettingsSyncHelper.this.f;
                    if (i2 == 0) {
                        settingsSyncListner = SettingsSyncHelper.this.b;
                        Intrinsics.checkNotNull(settingsSyncListner);
                        settingsSyncListner.onSettingsSyncCompleted();
                    }
                }
            });
        }
    }

    public final void uploadDndToServer(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        String valueOf;
        String valueOf2;
        String valueOf3;
        String valueOf4;
        if (AppUtils.isNetConnected(this.f4198a)) {
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
            SaveDNDSettingsReq saveDNDSettingsReq = new SaveDNDSettingsReq();
            SaveDNDSettingsReq.ScheduleDNDListBean scheduleDNDListBean = new SaveDNDSettingsReq.ScheduleDNDListBean();
            if (z2) {
                z = z2;
            }
            saveDNDSettingsReq.setActive(z);
            if (z2) {
                scheduleDNDListBean.setStartTime("00:00:00");
                scheduleDNDListBean.setEndTime("00:00:00");
            } else {
                scheduleDNDListBean.setStartTime(valueOf + ':' + valueOf2 + ":00");
                scheduleDNDListBean.setEndTime(valueOf3 + ':' + valueOf4 + ":00");
            }
            saveDNDSettingsReq.setDndListBean(e.listOf(scheduleDNDListBean));
            CoveUserAppSettings.saveDNDSettings(saveDNDSettingsReq, new CoveApiListener<SaveDNDSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$uploadDndToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    String str;
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "DND saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveDNDSettingsRes saveDNDSettingsRes) {
                    String str;
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "DND saved to server successfully.");
                }
            });
        }
    }

    public final void uploadLiftWristToServer(int i, int i2, int i3, int i4, boolean z) {
        if (AppUtils.isNetConnected(this.f4198a)) {
            SaveLiftWristToViewSettingsReq saveLiftWristToViewSettingsReq = new SaveLiftWristToViewSettingsReq();
            saveLiftWristToViewSettingsReq.setActive(z);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%02d:%02d:00", Arrays.copyOf(new Object[]{Integer.valueOf(i3), Integer.valueOf(i4)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            saveLiftWristToViewSettingsReq.setEndTime(format);
            String format2 = String.format("%02d:%02d:00", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            saveLiftWristToViewSettingsReq.setStartTime(format2);
            CoveUserAppSettings.saveLiftWristToViewSettings(saveLiftWristToViewSettingsReq, new CoveApiListener<SaveLiftWristToViewSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$uploadLiftWristToServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    String str;
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "Lift wrist to view saved to server failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SaveLiftWristToViewSettingsRes saveLiftWristToViewSettingsRes) {
                    String str;
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "Lift wrist to view saved to server successfully.");
                }
            });
        }
    }

    public final void v(final SettingsSyncListner settingsSyncListner) {
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncFavouritePlaces$1

            /* loaded from: classes4.dex */
            public static final class a extends Lambda implements Function2<String, Boolean, Unit> {
                public final /* synthetic */ SettingsSyncHelper.SettingsSyncListner $settingsSyncListner;
                public final /* synthetic */ SettingsSyncHelper this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(SettingsSyncHelper settingsSyncHelper, SettingsSyncHelper.SettingsSyncListner settingsSyncListner) {
                    super(2);
                    this.this$0 = settingsSyncHelper;
                    this.$settingsSyncListner = settingsSyncListner;
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                    invoke(str, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull String _msg, boolean z) {
                    int i;
                    int i2;
                    Intrinsics.checkNotNullParameter(_msg, "_msg");
                    if (z) {
                        SettingsSyncHelper settingsSyncHelper = this.this$0;
                        i = settingsSyncHelper.f;
                        settingsSyncHelper.f = i - 1;
                        i2 = this.this$0.f;
                        if (i2 == 0) {
                            SettingsSyncHelper.SettingsSyncListner settingsSyncListner = this.$settingsSyncListner;
                            Intrinsics.checkNotNull(settingsSyncListner);
                            settingsSyncListner.onSettingsSyncCompleted();
                            return;
                        }
                        return;
                    }
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = this.$settingsSyncListner;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingSyncError();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "syncFavouritePlaces onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String str;
                int i;
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "syncFavouritePlaces onSuccess");
                List<FavouritePlace> favouritePlaceList = getFavouritePlacesRes != null ? getFavouritePlacesRes.getFavouritePlaceList() : null;
                if (favouritePlaceList == null || favouritePlaceList.isEmpty()) {
                    return;
                }
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i + 1;
                SettingsSyncHelper settingsSyncHelper2 = SettingsSyncHelper.this;
                Intrinsics.checkNotNull(getFavouritePlacesRes);
                settingsSyncHelper2.J(getFavouritePlacesRes, new a(SettingsSyncHelper.this, settingsSyncListner));
            }
        });
    }

    public final void w() {
        SetFitnessInfoRequest builder;
        ProfileData userDetails = SessionManager.getInstance(this.f4198a).getUserDetails();
        double d = ProfileRepository.getInstance().getLatestProfileData(this.f4198a) != null ? ProfileRepository.getInstance().getLatestProfileData(this.f4198a).walkStrideLength : -1.0d;
        if (d == -1.0d) {
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            d = Double.parseDouble(height) * 0.413d;
        }
        if (TextUtils.isEmpty(userDetails.getGender())) {
            String height2 = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height2, "profileData.height");
            int parseInt = Integer.parseInt(height2);
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            SetFitnessInfoRequest.Builder builder2 = new SetFitnessInfoRequest.Builder(parseInt, Double.parseDouble(weight));
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(this.f4198a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
            builder = builder2.setUnitType(isDistanceUnitInMile.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC).setStride(d).builder();
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(\n               …lkStrideLength).builder()");
        } else {
            String height3 = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height3, "profileData.height");
            int parseInt2 = Integer.parseInt(height3);
            String weight2 = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight2, "profileData.weight");
            SetFitnessInfoRequest.Builder stride = new SetFitnessInfoRequest.Builder(parseInt2, Double.parseDouble(weight2)).setStride(d);
            Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(this.f4198a).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(context).isDistanceUnitInMile");
            builder = stride.setUnitType(isDistanceUnitInMile2.booleanValue() ? MeasurementUnitType.IMPERIAL : MeasurementUnitType.METRIC).setMale(m.equals(userDetails.getGender(), "Male", true)).builder();
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(\n               …               .builder()");
        }
        this.f++;
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(builder, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncHeightWeightInformation$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d("SettingsSyncHelper", " Weight weight settings success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void x() {
        SetLiftWristRequest.Builder builder;
        this.f++;
        Boolean isLiftWristToViewOn = UserDataManager.getInstance(this.f4198a).isLiftWristToViewEnable();
        if (DeviceUtils.Companion.isMoyangDevice(this.f4198a)) {
            Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
            builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
            builder.setStartHour(UserDataManager.getInstance(this.f4198a).getLiftWristToViewStartHour());
            builder.setStartMinute(UserDataManager.getInstance(this.f4198a).getLiftWristToViewStartMin());
            builder.setEndHour(UserDataManager.getInstance(this.f4198a).getLiftWristToViewEndHour());
            builder.setEndMinute(UserDataManager.getInstance(this.f4198a).getLiftWristToViewEndMin());
        } else {
            Intrinsics.checkNotNullExpressionValue(isLiftWristToViewOn, "isLiftWristToViewOn");
            builder = new SetLiftWristRequest.Builder(isLiftWristToViewOn.booleanValue());
        }
        SetLiftWristRequest build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncLiftWristToView$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "syncLiftWristToView failed");
                settingsSyncListner = SettingsSyncHelper.this.b;
                Intrinsics.checkNotNull(settingsSyncListner);
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                String str2;
                int i;
                int i2;
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, response.toString());
                str2 = SettingsSyncHelper.this.d;
                LogHelper.d(str2, "syncLiftWristToView success");
                SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = SettingsSyncHelper.this.f;
                if (i2 == 0) {
                    settingsSyncListner = SettingsSyncHelper.this.b;
                    Intrinsics.checkNotNull(settingsSyncListner);
                    settingsSyncListner.onSettingsSyncCompleted();
                }
            }
        });
    }

    public final void y(final SettingsSyncListner settingsSyncListner) {
        CoveUserDeviceSettings.getAllUserDeviceSettings(new CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncNavigationSettings$1

            /* loaded from: classes4.dex */
            public static final class a extends Lambda implements Function1<Boolean, Unit> {
                public final /* synthetic */ SettingsSyncHelper.SettingsSyncListner $settingsSyncListner;
                public final /* synthetic */ SettingsSyncHelper this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(SettingsSyncHelper settingsSyncHelper, SettingsSyncHelper.SettingsSyncListner settingsSyncListner) {
                    super(1);
                    this.this$0 = settingsSyncHelper;
                    this.$settingsSyncListner = settingsSyncListner;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    int i;
                    int i2;
                    if (z) {
                        SettingsSyncHelper settingsSyncHelper = this.this$0;
                        i = settingsSyncHelper.f;
                        settingsSyncHelper.f = i - 1;
                        i2 = this.this$0.f;
                        if (i2 == 0) {
                            SettingsSyncHelper.SettingsSyncListner settingsSyncListner = this.$settingsSyncListner;
                            Intrinsics.checkNotNull(settingsSyncListner);
                            settingsSyncListner.onSettingsSyncCompleted();
                            return;
                        }
                        return;
                    }
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = this.$settingsSyncListner;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingSyncError();
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = SettingsSyncHelper.this.d;
                LogHelper.d(str, "syncNavigationSettings onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAllUserDeviceSettingRes getAllUserDeviceSettingRes) {
                String str;
                int i;
                if (getAllUserDeviceSettingRes != null) {
                    str = SettingsSyncHelper.this.d;
                    LogHelper.d(str, "syncNavigationSettings onSuccess " + new Gson().toJson(getAllUserDeviceSettingRes));
                    if (getAllUserDeviceSettingRes.getDataBean() == null || getAllUserDeviceSettingRes.getDataBean().getMapNavigationSettings() == null) {
                        return;
                    }
                    SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                    i = settingsSyncHelper.f;
                    settingsSyncHelper.f = i + 1;
                    SettingsSyncHelper settingsSyncHelper2 = SettingsSyncHelper.this;
                    MapNavigationSettings mapNavigationSettings = getAllUserDeviceSettingRes.getDataBean().getMapNavigationSettings();
                    Intrinsics.checkNotNullExpressionValue(mapNavigationSettings, "p0.dataBean.mapNavigationSettings");
                    settingsSyncHelper2.K(mapNavigationSettings, new a(SettingsSyncHelper.this, settingsSyncListner));
                }
            }
        });
    }

    public final void z(final SettingsSyncListner settingsSyncListner) {
        SOSData sOSConfig = SessionManager.getInstance(this.f4198a).getSOSConfig();
        if ((sOSConfig != null ? sOSConfig.isSOSEnabled() : null) != null) {
            if ((sOSConfig != null ? sOSConfig.getContactName() : null) != null) {
                if ((sOSConfig != null ? sOSConfig.getContactNumber() : null) != null) {
                    this.f++;
                    SetSOSConfigRequest.Builder builder = new SetSOSConfigRequest.Builder();
                    Boolean isSOSEnabled = sOSConfig.isSOSEnabled();
                    Intrinsics.checkNotNull(isSOSEnabled);
                    boolean booleanValue = isSOSEnabled.booleanValue();
                    String contactName = sOSConfig.getContactName();
                    Intrinsics.checkNotNull(contactName);
                    String contactNumber = sOSConfig.getContactNumber();
                    Intrinsics.checkNotNull(contactNumber);
                    BleApiManager.getInstance(this.f4198a).getBleApi().setUserSettings(builder.setSOSConfigRequests(booleanValue, 5, contactName, contactNumber).build(), new SettingsResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$syncSOS$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            settingsSyncListner.onSettingSyncError();
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            int i;
                            int i2;
                            Intrinsics.checkNotNullParameter(response, "response");
                            SettingsSyncHelper settingsSyncHelper = SettingsSyncHelper.this;
                            i = settingsSyncHelper.f;
                            settingsSyncHelper.f = i - 1;
                            i2 = SettingsSyncHelper.this.f;
                            if (i2 == 0) {
                                settingsSyncListner.onSettingsSyncCompleted();
                            }
                        }
                    });
                }
            }
        }
    }
}
