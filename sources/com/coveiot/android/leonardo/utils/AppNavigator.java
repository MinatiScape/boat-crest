package com.coveiot.android.leonardo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutHistory;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutWebViewer;
import com.coveiot.android.activitymodes.autodetection.activities.ActivityAutoRecognitionActivitiesWithOneK;
import com.coveiot.android.activitymodes.autodetection.activities.ActivityAutoRecognitionActivitiesWithOneKDisclaimer;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.workoutVideos.activities.WorkoutVideosActivity;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessCalendarView;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessFTU;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSettings;
import com.coveiot.android.femalewellness.activities.ActivityFemaleWellnessSymptoms;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinContacts;
import com.coveiot.android.leonardo.boatcoin.activities.ActivityBoatCoinsWebViewer;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityAmbientSoundHistory;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityHeartRateHistory;
import com.coveiot.android.leonardo.dashboard.health.activities.ActivityShareScreen;
import com.coveiot.android.leonardo.dashboard.health.activities.AmbientSoundLevelInfoActivity;
import com.coveiot.android.leonardo.dashboard.health.activities.InfoActivity;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.spo2.ActivitySp02FromRawPPG;
import com.coveiot.android.leonardo.dashboard.health.spo2.activity.ActivitySPO2Measurement;
import com.coveiot.android.leonardo.dashboard.permissioncheck.ActivityPermissionCheck;
import com.coveiot.android.leonardo.more.activities.ActivityAddQRCode;
import com.coveiot.android.leonardo.more.activities.ActivityAdditionalActivities;
import com.coveiot.android.leonardo.more.activities.ActivityAlarmSettings;
import com.coveiot.android.leonardo.more.activities.ActivityAmbientSoundSettings;
import com.coveiot.android.leonardo.more.activities.ActivityAutoHrSettings;
import com.coveiot.android.leonardo.more.activities.ActivityAutoRecognitionActivities;
import com.coveiot.android.leonardo.more.activities.ActivityAutoSPO2Settings;
import com.coveiot.android.leonardo.more.activities.ActivityAutoStressHRVSettings;
import com.coveiot.android.leonardo.more.activities.ActivityAutoStressSettings;
import com.coveiot.android.leonardo.more.activities.ActivityAutoTemperatureSettings;
import com.coveiot.android.leonardo.more.activities.ActivityCameraSettings;
import com.coveiot.android.leonardo.more.activities.ActivityDisclaimer;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdate;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateEastapex;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateIDO;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateJStyle;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaApollo;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateKaHaRealTek;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMatrix;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdatePS1;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaJL;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateSmaRealTek;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateTouch;
import com.coveiot.android.leonardo.more.activities.ActivityLanguageSettings;
import com.coveiot.android.leonardo.more.activities.ActivityNotificationFeedBack;
import com.coveiot.android.leonardo.more.activities.ActivityShortcuts;
import com.coveiot.android.leonardo.more.activities.ActivitySportsType;
import com.coveiot.android.leonardo.more.activities.ActivityTroubleshootnFAQ;
import com.coveiot.android.leonardo.more.activities.ActivityWorldClock;
import com.coveiot.android.leonardo.more.activities.TroubleshootingActivityNew;
import com.coveiot.android.leonardo.more.activities.WatchDiagnosticsActivity;
import com.coveiot.android.leonardo.onboarding.consent.ActivityConsent;
import com.coveiot.android.leonardo.onboarding.ftu.ActivityFirstTimeUser;
import com.coveiot.android.leonardo.onboarding.goal.activities.ActivityStepsGoal;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityPairing;
import com.coveiot.android.leonardo.onboarding.paring.activities.ActivityQROnboardingFTU;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityPhoneValidation;
import com.coveiot.android.leonardo.onboarding.phonevalidation.activities.ActivityReportIssue;
import com.coveiot.android.leonardo.onboarding.profile.activities.ActivityProfile;
import com.coveiot.android.leonardo.onboarding.splash.activities.ActivitySplash;
import com.coveiot.android.leonardo.sensai.activity.ActivityOverallStats;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAICompare;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIDetails;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIFTUNew;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIHomeNew;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIInfo;
import com.coveiot.android.leonardo.sensai.activity.ActivitySessionInsights;
import com.coveiot.android.leonardo.sensai.model.SensAICompareData;
import com.coveiot.android.leonardo.sensai.model.SensAICompareTitle;
import com.coveiot.android.leonardo.websupport.ActivityInAppWebViewer;
import com.coveiot.android.navigation.activities.ActivityNavigationFTU;
import com.coveiot.android.navigation.activities.ActivityNavigationMain;
import com.coveiot.android.permissionsandsettings.ConfigureSettingsActivity;
import com.coveiot.android.qrtray.ActivityQRTray;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings;
import com.coveiot.android.theme.LocationPermissionActivity;
import com.coveiot.android.theme.model.BoatCoinsScreenCaller;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.android.weather.weather.WeatherActivity;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AppNavigator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes5.dex */
    public static final class Companion {

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Intent, Unit> {
            public static final a INSTANCE = new a();

            public a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Intent launchActivity) {
                Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void navigateToTroubleshootNotification$default(Companion companion, Context context, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            companion.navigateToTroubleshootNotification(context, z);
        }

        public final void navigateToActivityDashboard(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(67108864);
            context.startActivity(intent);
        }

        public final void navigateToActivityQROnboardingFTU(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityQROnboardingFTU.class));
        }

        public final void navigateToActivtyAlarmSettings(@NotNull Context context, int i, @NotNull String snoozeDuration, @NotNull String snoozeRepeatCount) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(snoozeDuration, "snoozeDuration");
            Intrinsics.checkNotNullParameter(snoozeRepeatCount, "snoozeRepeatCount");
            Intent intent = new Intent(context, ActivityAlarmSettings.class);
            intent.putExtra("SNOOZE_TIME_INTERVAL_KEY", snoozeDuration);
            intent.putExtra("SNOOZE_REPEAT_COUNT_KEY", snoozeRepeatCount);
            ((Activity) context).startActivityForResult(intent, i);
        }

        public final void navigateToAddQRCodeActivity(@NotNull Context context, @NotNull String type) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            Intent intent = new Intent(context, ActivityAddQRCode.class);
            intent.putExtra(AppConstants.CARD_TYPE.getValue(), type);
            context.startActivity(intent);
        }

        public final void navigateToAddQRCodeTrayActivity(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            a aVar = a.INSTANCE;
            Intent intent = new Intent(context, ActivityQRTray.class);
            aVar.invoke((a) intent);
            context.startActivity(intent, null);
        }

        public final void navigateToAdditionalActivities(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAdditionalActivities.class));
        }

        public final void navigateToAmbientSoundInfoScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, AmbientSoundLevelInfoActivity.class));
        }

        public final void navigateToAmbientSoundLevelHistory(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAmbientSoundHistory.class));
        }

        public final void navigateToAmbientSoundLevelSettings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAmbientSoundSettings.class));
        }

        public final void navigateToAutoActivityDetectionWithOneKDisclaimer(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoRecognitionActivitiesWithOneKDisclaimer.class));
        }

        public final void navigateToAutoHr(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoHrSettings.class));
        }

        public final void navigateToAutoRecognition(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoRecognitionActivities.class));
        }

        public final void navigateToAutoRecognitionActivityWithOneK(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoRecognitionActivitiesWithOneK.class));
        }

        public final void navigateToAutoSPO2Settings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoSPO2Settings.class));
        }

        public final void navigateToAutoStressHRVSettings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoStressHRVSettings.class));
        }

        public final void navigateToAutoStressSettings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoStressSettings.class));
        }

        public final void navigateToAutoTemperature(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityAutoTemperatureSettings.class));
        }

        public final void navigateToBluetoothScanActivity(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setIsLoggedIn(true);
            context.startActivity(new Intent(context, ActivityPairing.class));
        }

        public final void navigateToBluetoothScanActivityAndClear(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setIsLoggedIn(true);
            Intent intent = new Intent(context, ActivityPairing.class);
            intent.addFlags(32768);
            context.startActivity(intent);
        }

        public final void navigateToBoatCoinsContacts(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityBoatCoinContacts.class));
        }

        public final void navigateToBoatCoinsWebViewer(@NotNull Context context, @NotNull String url, @NotNull BoatCoinsScreenCaller boatCoinsScreenCaller) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(boatCoinsScreenCaller, "boatCoinsScreenCaller");
            Intent intent = new Intent(context, ActivityBoatCoinsWebViewer.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), url);
            intent.putExtra(AppConstants.SCREEN_NAME.getValue(), boatCoinsScreenCaller.name());
            intent.setFlags(67108864);
            context.startActivity(intent);
        }

        public final void navigateToCameraSettings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityCameraSettings.class));
        }

        public final void navigateToConfigurationSettings(@NotNull Context context, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            ((Activity) context).startActivityForResult(new Intent(context, ConfigureSettingsActivity.class), i);
        }

        public final void navigateToConsentScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setIsDevicePaired(true);
            context.startActivity(new Intent(context, ActivityConsent.class));
        }

        public final void navigateToDashBoard(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setOnBoardingComplete(true);
            Intent intent = new Intent(context, ActivityDashboardNew.class);
            intent.setFlags(335577088);
            intent.putExtra("FROM_ONBOARDING", z);
            context.startActivity(intent);
        }

        public final void navigateToDisclaimer(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityDisclaimer.class));
        }

        public final void navigateToEastApexFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateEastapex.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToFemaleWellness(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityFemaleWellnessSettings.class));
        }

        public final void navigateToFemaleWellnessCalendarView(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityFemaleWellnessCalendarView.class));
        }

        public final void navigateToFemaleWellnessCalendarViewOrSettings(@NotNull Context context) {
            Intent intent;
            Intrinsics.checkNotNullParameter(context, "context");
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
                intent = new Intent(context, ActivityFemaleWellnessSettings.class);
            } else {
                intent = new Intent(context, ActivityFemaleWellnessCalendarView.class);
            }
            context.startActivity(intent);
        }

        public final void navigateToFemaleWellnessSymptoms(@NotNull Context context, @NotNull String selectedDate) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
            Intent intent = new Intent(context, ActivityFemaleWellnessSymptoms.class);
            intent.putExtra(Constants.SELECTED_DATE.getValue(), selectedDate);
            context.startActivity(intent);
        }

        public final void navigateToFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdate.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToFirstTimeUser(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityFirstTimeUser.class));
        }

        public final void navigateToHRHistory(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityHeartRateHistory.class));
        }

        public final void navigateToIDOFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateIDO.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToInfoPage(@NotNull Context context, @NotNull String screenName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(screenName, "screenName");
            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra(AppConstants.SCREEN_NAME.getValue(), screenName);
            context.startActivity(intent);
        }

        public final void navigateToJStyleFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateJStyle.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToKaHaMKIFirmwareUpdateActivity(@NotNull Activity context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateKaHaApollo.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivityForResult(intent, ActivityDashboardNew.Companion.getREQUEST_CODE_FW_UPDATE());
        }

        public final void navigateToKaHaRealTekFirmwareUpdateActivity(@NotNull Activity context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateKaHaRealTek.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivityForResult(intent, ActivityDashboardNew.Companion.getREQUEST_CODE_FW_UPDATE());
        }

        public final void navigateToLanguageSettings(@NotNull Context context, @NotNull String typeOfPage) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(typeOfPage, "typeOfPage");
            Intent intent = new Intent(context, ActivityLanguageSettings.class);
            intent.putExtra(AppConstants.PAGE_TYPE.getValue(), typeOfPage);
            context.startActivity(intent);
        }

        public final void navigateToLocationPermissionActivity(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, LocationPermissionActivity.class));
        }

        public final void navigateToLogin(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            navigateToPhoneValidationScreen(context, false, "");
            SessionManager.getInstance(context).setIsGuestUser(false);
            SessionManager.getInstance(context).setOnBoardingComplete(false);
        }

        public final void navigateToMatrixFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateMatrix.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToMoyangFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateMoyang.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToNavigationScreen(@NotNull Context context, @NotNull String type, boolean z) {
            Intent intent;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            if (z) {
                intent = new Intent(context, ActivityNavigationFTU.class);
            } else {
                intent = new Intent(context, ActivityNavigationMain.class);
            }
            intent.putExtra(AppConstants.CARD_TYPE.getValue(), type);
            context.startActivity(intent);
        }

        public final void navigateToPS1FirmwareUpdateActivity(@NotNull Activity context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdatePS1.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivityForResult(intent, ActivityDashboardNew.Companion.getREQUEST_CODE_FW_UPDATE());
        }

        public final void navigateToPairYourDevice(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            navigateToBluetoothScanActivity(context);
            SessionManager.getInstance(context).setIsPairDeviceLater(false);
            SessionManager.getInstance(context).setIsPairDeviceAfterLogin(true);
            SessionManager.getInstance(context).setOnBoardingComplete(false);
        }

        public final void navigateToPermissionCheck(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setOnBoardingComplete(true);
            Intent intent = new Intent(context, ActivityPermissionCheck.class);
            intent.setFlags(335577088);
            context.startActivity(intent);
        }

        public final void navigateToPhoneValidationScreen(@NotNull Context context, boolean z, @NotNull String phoneNo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(phoneNo, "phoneNo");
            Intent intent = new Intent(context, ActivityPhoneValidation.class);
            intent.putExtra(AppConstants.IS_MODIFY_PHONE_NO.getValue(), z);
            intent.putExtra(AppConstants.PHONE_NUMBER.getValue(), phoneNo);
            context.startActivity(intent);
        }

        public final void navigateToProfileScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityProfile.class));
        }

        public final void navigateToReportIssue(@NotNull Context context, @NotNull String userPhoneNo) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(userPhoneNo, "userPhoneNo");
            Intent intent = new Intent(context, ActivityReportIssue.class);
            intent.putExtra(AppConstants.PHONE_NUMBER.getValue(), userPhoneNo);
            context.startActivity(intent);
        }

        public final void navigateToRespiratoryRateSettings(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityRespiratoryRateSettings.class));
        }

        public final void navigateToRunHistory(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityWorkoutHistory.class));
        }

        public final void navigateToSensAICompareScreen(@NotNull Context context, @NotNull ArrayList<SensAICompareData> addToCompareList, @NotNull ArrayList<SensAICompareTitle> titleCompareList) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(addToCompareList, "addToCompareList");
            Intrinsics.checkNotNullParameter(titleCompareList, "titleCompareList");
            Intent intent = new Intent(context, ActivitySensAICompare.class);
            intent.putParcelableArrayListExtra("ADD_TO_COMPARE", addToCompareList);
            intent.putParcelableArrayListExtra("ADD_TO_COMPARE_TITLE", titleCompareList);
            context.startActivity(intent);
        }

        public final void navigateToSensAIFTUScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivitySensAIFTUNew.class));
        }

        public final void navigateToSensAIHomeScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivitySensAIHomeNew.class));
        }

        public final void navigateToSensAIInfoScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivitySensAIInfo.class));
        }

        public final void navigateToSensAISOverallStatsScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityOverallStats.class));
        }

        public final void navigateToSensAISessionInsightsDetailsScreen(@NotNull Context context, @NotNull String sessionID, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sessionID, "sessionID");
            Intent intent = new Intent(context, ActivitySensAIDetails.class);
            intent.putExtra("ACTIVITY_TYPE", i);
            intent.putExtra("SESSION_ID", sessionID);
            context.startActivity(intent);
        }

        public final void navigateToSensAISessionInsightsScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivitySessionInsights.class));
        }

        public final void navigateToShareScreen(@NotNull Context context, @NotNull ShareData data, @NotNull String shareType) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
            Intrinsics.checkNotNullParameter(shareType, "shareType");
            Intent intent = new Intent(context, ActivityShareScreen.class);
            intent.putExtra(AppConstants.SHARE_DATA.getValue(), data);
            intent.putExtra(AppConstants.SHARE_SCREEN_TYPE.getValue(), shareType);
            context.startActivity(intent);
        }

        public final void navigateToShortcuts(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityShortcuts.class));
        }

        public final void navigateToSmaJLFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateSmaJL.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToSmaRealTekFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateSmaRealTek.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToSpO2Measuring(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.v7) {
                if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    context.startActivity(new Intent(context, ActivitySp02FromRawPPG.class));
                    return;
                } else {
                    Toast.makeText(context, context.getString(R.string.band_not_connected), 0).show();
                    return;
                }
            }
            context.startActivity(new Intent(context, ActivitySPO2Measurement.class));
        }

        public final void navigateToSplashActivityAndClear(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, ActivitySplash.class);
            intent.addFlags(32768);
            context.startActivity(intent);
        }

        public final void navigateToSportsType(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivitySportsType.class));
        }

        public final void navigateToStepsGoal(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionManager.getInstance(context).setIsDevicePaired(true);
            context.startActivity(new Intent(context, ActivityStepsGoal.class));
        }

        public final void navigateToTouchElxFirmwareUpdateActivity(@NotNull Context context, @NotNull SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareBean, "firmwareBean");
            Intent intent = new Intent(context, ActivityFirmwareUpdateTouch.class);
            intent.putExtra(AppConstants.FIRMWARE_BEAN.getValue(), firmwareBean);
            context.startActivity(intent);
        }

        public final void navigateToTroubleshootConnectivity(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, TroubleshootingActivityNew.class));
        }

        public final void navigateToTroubleshootNotification(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, TroubleshootingActivityNew.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_IS_FROM_FAQ.getValue(), z);
            context.startActivity(intent);
        }

        public final void navigateToTroubleshootnFAQs(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityTroubleshootnFAQ.class));
        }

        public final void navigateToWatchDiagnostics(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, WatchDiagnosticsActivity.class);
            intent.putExtra(AppConstants.IS_RUN_DIAGNOSTIC_FRAGMENT.getValue(), z);
            context.startActivity(intent);
        }

        public final void navigateToWeatherActivity(@NotNull Context context, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, WeatherActivity.class);
            intent.putExtra(ThemeConstants.IS_WEATHER_FROM_SETTING_SCREEN.getValue(), z);
            context.startActivity(intent);
        }

        public final void navigateToWebViewer(@NotNull Context context, @NotNull String title, @NotNull String url) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, ActivityInAppWebViewer.class);
            intent.putExtra(AppConstants.INTENT_EXTRA_TITLE.getValue(), title);
            intent.putExtra(AppConstants.INTENT_EXTRA_URL.getValue(), url);
            context.startActivity(intent);
        }

        public final void navigateToWomenWellnessFTUActivity(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityFemaleWellnessFTU.class));
        }

        public final void navigateToWorkoutVideos(@NotNull Context context, @NotNull String type) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            Intent intent = new Intent(context, WorkoutVideosActivity.class);
            intent.putExtra(AppConstants.WORKOUT_TYPE.getValue(), type);
            context.startActivity(intent);
        }

        public final void navigateToWorkoutWebViewer(@NotNull Context context, @NotNull String title, @NotNull String url) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, ActivityWorkoutWebViewer.class);
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_TITLE, title);
            intent.putExtra(WorkoutConstants.INTENT_EXTRA_URL, url);
            context.startActivity(intent);
        }

        public final void navigateToWorldClock(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityWorldClock.class));
        }

        public final void navigationToNotificationFeedbackScreen(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, ActivityNotificationFeedBack.class));
        }

        public final void navigateToJStyleFirmwareUpdateActivity(@NotNull Context context, @NotNull String firmwareFile) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(firmwareFile, "firmwareFile");
            Intent intent = new Intent(context, ActivityFirmwareUpdateJStyle.class);
            intent.putExtra(AppConstants.FIRMWARE_FILE.getValue(), firmwareFile);
            context.startActivity(intent);
        }
    }
}
