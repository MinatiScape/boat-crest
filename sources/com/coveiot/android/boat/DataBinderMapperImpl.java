package com.coveiot.android.boat;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.boat.databinding.ActivityAddQrcodeBindingImpl;
import com.coveiot.android.boat.databinding.ActivityAdditionalActivitiesBindingImpl;
import com.coveiot.android.boat.databinding.ActivityAutoSpo2SettingsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityBatterySaverModeNewBindingImpl;
import com.coveiot.android.boat.databinding.ActivityBoatCoinContactsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityBoatCoinsFtuBindingImpl;
import com.coveiot.android.boat.databinding.ActivityBoatCoinsWebViewerBindingImpl;
import com.coveiot.android.boat.databinding.ActivityCameraSettingsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityCheckPermissionsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityContactUsFeedbackBindingImpl;
import com.coveiot.android.boat.databinding.ActivityCustomise4hButtonBindingImpl;
import com.coveiot.android.boat.databinding.ActivityDrinkReminderBindingImpl;
import com.coveiot.android.boat.databinding.ActivityFirstTimeUserBindingImpl;
import com.coveiot.android.boat.databinding.ActivityOnboardingFtuBindingImpl;
import com.coveiot.android.boat.databinding.ActivityOverallStatsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityProfileGoalsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityProfileLandingPageBindingImpl;
import com.coveiot.android.boat.databinding.ActivityRemoteCameraBindingImpl;
import com.coveiot.android.boat.databinding.ActivityScheduleBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySedentaryReminderBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySensAiCompareBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySensAiDetailsBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySensAiInfoBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySensaiFilterBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySensaiShareScreenBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySessionInsightsBindingImpl;
import com.coveiot.android.boat.databinding.ActivitySessionInsightsDetailsBindingImpl;
import com.coveiot.android.boat.databinding.ActivityTroubleshootingConnectivitySuccessBindingImpl;
import com.coveiot.android.boat.databinding.ActivityTroubleshootingNewBindingImpl;
import com.coveiot.android.boat.databinding.ActivityWatchDiagnosticsBindingImpl;
import com.coveiot.android.boat.databinding.AlarmListRowNewBindingImpl;
import com.coveiot.android.boat.databinding.CalendarDayItemBindingImpl;
import com.coveiot.android.boat.databinding.ComparePopupBindingImpl;
import com.coveiot.android.boat.databinding.FitnessBlogItemBindingImpl;
import com.coveiot.android.boat.databinding.FitnessGoalsItemBindingImpl;
import com.coveiot.android.boat.databinding.FragmentBattingOverallStatsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentBoatCoinsContactsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentBoatCoinsFTUBindingImpl;
import com.coveiot.android.boat.databinding.FragmentBowlingOverallStatsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentDeviceListingFragmentBindingImpl;
import com.coveiot.android.boat.databinding.FragmentDevicePairedBindingImpl;
import com.coveiot.android.boat.databinding.FragmentDeviceSelectionFragmentBindingImpl;
import com.coveiot.android.boat.databinding.FragmentFitnessBindingImpl;
import com.coveiot.android.boat.databinding.FragmentFitnessDataShareBindingImpl;
import com.coveiot.android.boat.databinding.FragmentFitnessHomeBindingImpl;
import com.coveiot.android.boat.databinding.FragmentMyWatchBindingImpl;
import com.coveiot.android.boat.databinding.FragmentMyWatchStatusBindingImpl;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBattingBindingImpl;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBowlingBindingImpl;
import com.coveiot.android.boat.databinding.FragmentPairingFailedBindingImpl;
import com.coveiot.android.boat.databinding.FragmentQRScanDeviceBindingImpl;
import com.coveiot.android.boat.databinding.FragmentRunDiagnosticTestingBindingImpl;
import com.coveiot.android.boat.databinding.FragmentRunWatchDiagnosticsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensAiCoachBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensAiFtu1BindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensAiFtu2BindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensAiFtu3BindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensAiShareDataBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensaiFilterOptionsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSensaiShareBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSessionInsightsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSocialShareCardBindingImpl;
import com.coveiot.android.boat.databinding.FragmentSystemSettingsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentTroubleshoootingDndBindingImpl;
import com.coveiot.android.boat.databinding.FragmentTroubleshoootingFTUBindingImpl;
import com.coveiot.android.boat.databinding.FragmentTroubleshootTestingBindingImpl;
import com.coveiot.android.boat.databinding.FragmentTroubleshootingBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalHeartrateBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalHrvBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalManualSpo2BindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalManualStressBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalPeriodicSpo2BindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalPeriodicStressBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalSleepBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalStepsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalTemperatureBindingImpl;
import com.coveiot.android.boat.databinding.FragmentVitalsBindingImpl;
import com.coveiot.android.boat.databinding.FragmentWatchDiagnosticDashboardBindingImpl;
import com.coveiot.android.boat.databinding.FragmentWatchDiagnosticTestHistoryBindingImpl;
import com.coveiot.android.boat.databinding.FragmentWatchSettingsNewBindingImpl;
import com.coveiot.android.boat.databinding.GridviewlayoutSsBindingImpl;
import com.coveiot.android.boat.databinding.Item4hButtonViewBindingImpl;
import com.coveiot.android.boat.databinding.ItemPermssionsRequiredBindingImpl;
import com.coveiot.android.boat.databinding.ItemSensAiCompareHeaderBindingImpl;
import com.coveiot.android.boat.databinding.ItemSensAiCompareListBindingImpl;
import com.coveiot.android.boat.databinding.ItemSensAiFilterBindingImpl;
import com.coveiot.android.boat.databinding.ItemSensAiSortByBindingImpl;
import com.coveiot.android.boat.databinding.LayoutItemQrCodeAppListBindingImpl;
import com.coveiot.android.boat.databinding.ListItemAdditionalActivitiesBindingImpl;
import com.coveiot.android.boat.databinding.ListItemDeviceLayoutBindingImpl;
import com.coveiot.android.boat.databinding.ListItemDiagnosticHistoryBindingImpl;
import com.coveiot.android.boat.databinding.ListItemDiagnosticTestingBindingImpl;
import com.coveiot.android.boat.databinding.ListItemNewDesignTitleBindingImpl;
import com.coveiot.android.boat.databinding.ListItemOtpServiceTypeLayoutBindingImpl;
import com.coveiot.android.boat.databinding.ListItemScannedBleDeviceLayoutBindingImpl;
import com.coveiot.android.boat.databinding.ListItemTroubleshootTestingBindingImpl;
import com.coveiot.android.boat.databinding.OverallStatsItemBindingImpl;
import com.coveiot.android.boat.databinding.ProfileAchievementsItemBindingImpl;
import com.coveiot.android.boat.databinding.ProfileFitnessItemBindingImpl;
import com.coveiot.android.boat.databinding.ProfileFitnessSubheaderItemBindingImpl;
import com.coveiot.android.boat.databinding.ProfileGoalsItemBindingImpl;
import com.coveiot.android.boat.databinding.ProfileGoalsItemHorizontalBindingImpl;
import com.coveiot.android.boat.databinding.ProfileMoreItemBindingImpl;
import com.coveiot.android.boat.databinding.QrScanOnBoardingFTUBindingImpl;
import com.coveiot.android.boat.databinding.SensAiDetailsBindingImpl;
import com.coveiot.android.boat.databinding.SensAiFeedbackItemBindingImpl;
import com.coveiot.android.boat.databinding.SensAiFilterItemBindingImpl;
import com.coveiot.android.boat.databinding.SensAiFilterOptionListItemBindingImpl;
import com.coveiot.android.boat.databinding.SensAiFtuActivityBindingImpl;
import com.coveiot.android.boat.databinding.SensAiHomeActivityBindingImpl;
import com.coveiot.android.boat.databinding.SensAiListItemBindingImpl;
import com.coveiot.android.boat.databinding.SensAiProfileItemBindingImpl;
import com.coveiot.android.boat.databinding.SensAiSortByDialogBindingImpl;
import com.coveiot.android.boat.databinding.SensaiCompareItemBindingImpl;
import com.coveiot.android.boat.databinding.SensaiShareCardItemBindingImpl;
import com.coveiot.android.boat.databinding.SessionInsightsItemBindingImpl;
import com.coveiot.android.boat.databinding.SleepInsightsDataBindingImpl;
import com.coveiot.android.boat.databinding.TroubleshootActivityNotiItemBindingImpl;
import com.coveiot.android.boat.databinding.TroubleshootingftuViewpagerLayoutBindingImpl;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes3.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYADDITIONALACTIVITIES = 2;
    private static final int LAYOUT_ACTIVITYADDQRCODE = 1;
    private static final int LAYOUT_ACTIVITYAUTOSPO2SETTINGS = 3;
    private static final int LAYOUT_ACTIVITYBATTERYSAVERMODENEW = 4;
    private static final int LAYOUT_ACTIVITYBOATCOINCONTACTS = 5;
    private static final int LAYOUT_ACTIVITYBOATCOINSFTU = 6;
    private static final int LAYOUT_ACTIVITYBOATCOINSWEBVIEWER = 7;
    private static final int LAYOUT_ACTIVITYCAMERASETTINGS = 8;
    private static final int LAYOUT_ACTIVITYCHECKPERMISSIONS = 9;
    private static final int LAYOUT_ACTIVITYCONTACTUSFEEDBACK = 10;
    private static final int LAYOUT_ACTIVITYCUSTOMISE4HBUTTON = 11;
    private static final int LAYOUT_ACTIVITYDRINKREMINDER = 12;
    private static final int LAYOUT_ACTIVITYFIRSTTIMEUSER = 13;
    private static final int LAYOUT_ACTIVITYONBOARDINGFTU = 14;
    private static final int LAYOUT_ACTIVITYOVERALLSTATS = 15;
    private static final int LAYOUT_ACTIVITYPROFILEGOALS = 16;
    private static final int LAYOUT_ACTIVITYPROFILELANDINGPAGE = 17;
    private static final int LAYOUT_ACTIVITYREMOTECAMERA = 18;
    private static final int LAYOUT_ACTIVITYSCHEDULE = 19;
    private static final int LAYOUT_ACTIVITYSEDENTARYREMINDER = 20;
    private static final int LAYOUT_ACTIVITYSENSAICOMPARE = 21;
    private static final int LAYOUT_ACTIVITYSENSAIDETAILS = 22;
    private static final int LAYOUT_ACTIVITYSENSAIFILTER = 24;
    private static final int LAYOUT_ACTIVITYSENSAIINFO = 23;
    private static final int LAYOUT_ACTIVITYSENSAISHARESCREEN = 25;
    private static final int LAYOUT_ACTIVITYSESSIONINSIGHTS = 26;
    private static final int LAYOUT_ACTIVITYSESSIONINSIGHTSDETAILS = 27;
    private static final int LAYOUT_ACTIVITYTROUBLESHOOTINGCONNECTIVITYSUCCESS = 28;
    private static final int LAYOUT_ACTIVITYTROUBLESHOOTINGNEW = 29;
    private static final int LAYOUT_ACTIVITYWATCHDIAGNOSTICS = 30;
    private static final int LAYOUT_ALARMLISTROWNEW = 31;
    private static final int LAYOUT_CALENDARDAYITEM = 32;
    private static final int LAYOUT_COMPAREPOPUP = 33;
    private static final int LAYOUT_FITNESSBLOGITEM = 34;
    private static final int LAYOUT_FITNESSGOALSITEM = 35;
    private static final int LAYOUT_FRAGMENTBATTINGOVERALLSTATS = 36;
    private static final int LAYOUT_FRAGMENTBOATCOINSCONTACTS = 37;
    private static final int LAYOUT_FRAGMENTBOATCOINSFTU = 38;
    private static final int LAYOUT_FRAGMENTBOWLINGOVERALLSTATS = 39;
    private static final int LAYOUT_FRAGMENTDEVICELISTINGFRAGMENT = 40;
    private static final int LAYOUT_FRAGMENTDEVICEPAIRED = 41;
    private static final int LAYOUT_FRAGMENTDEVICESELECTIONFRAGMENT = 42;
    private static final int LAYOUT_FRAGMENTFITNESS = 43;
    private static final int LAYOUT_FRAGMENTFITNESSDATASHARE = 44;
    private static final int LAYOUT_FRAGMENTFITNESSHOME = 45;
    private static final int LAYOUT_FRAGMENTMYWATCH = 46;
    private static final int LAYOUT_FRAGMENTMYWATCHSTATUS = 47;
    private static final int LAYOUT_FRAGMENTOVERALLSTATS = 48;
    private static final int LAYOUT_FRAGMENTOVERALLSTATSBATTING = 49;
    private static final int LAYOUT_FRAGMENTOVERALLSTATSBOWLING = 50;
    private static final int LAYOUT_FRAGMENTPAIRINGFAILED = 51;
    private static final int LAYOUT_FRAGMENTQRSCANDEVICE = 52;
    private static final int LAYOUT_FRAGMENTRUNDIAGNOSTICTESTING = 53;
    private static final int LAYOUT_FRAGMENTRUNWATCHDIAGNOSTICS = 54;
    private static final int LAYOUT_FRAGMENTSENSAICOACH = 55;
    private static final int LAYOUT_FRAGMENTSENSAIFILTEROPTIONS = 60;
    private static final int LAYOUT_FRAGMENTSENSAIFTU1 = 56;
    private static final int LAYOUT_FRAGMENTSENSAIFTU2 = 57;
    private static final int LAYOUT_FRAGMENTSENSAIFTU3 = 58;
    private static final int LAYOUT_FRAGMENTSENSAISHARE = 61;
    private static final int LAYOUT_FRAGMENTSENSAISHAREDATA = 59;
    private static final int LAYOUT_FRAGMENTSESSIONINSIGHTS = 62;
    private static final int LAYOUT_FRAGMENTSOCIALSHARECARD = 63;
    private static final int LAYOUT_FRAGMENTSYSTEMSETTINGS = 64;
    private static final int LAYOUT_FRAGMENTTROUBLESHOOOTINGDND = 65;
    private static final int LAYOUT_FRAGMENTTROUBLESHOOOTINGFTU = 66;
    private static final int LAYOUT_FRAGMENTTROUBLESHOOTING = 68;
    private static final int LAYOUT_FRAGMENTTROUBLESHOOTTESTING = 67;
    private static final int LAYOUT_FRAGMENTVITALHEARTRATE = 69;
    private static final int LAYOUT_FRAGMENTVITALHRV = 70;
    private static final int LAYOUT_FRAGMENTVITALMANUALSPO2 = 71;
    private static final int LAYOUT_FRAGMENTVITALMANUALSTRESS = 72;
    private static final int LAYOUT_FRAGMENTVITALPERIODICSPO2 = 73;
    private static final int LAYOUT_FRAGMENTVITALPERIODICSTRESS = 74;
    private static final int LAYOUT_FRAGMENTVITALS = 78;
    private static final int LAYOUT_FRAGMENTVITALSLEEP = 75;
    private static final int LAYOUT_FRAGMENTVITALSTEPS = 76;
    private static final int LAYOUT_FRAGMENTVITALTEMPERATURE = 77;
    private static final int LAYOUT_FRAGMENTWATCHDIAGNOSTICDASHBOARD = 79;
    private static final int LAYOUT_FRAGMENTWATCHDIAGNOSTICTESTHISTORY = 80;
    private static final int LAYOUT_FRAGMENTWATCHSETTINGSNEW = 81;
    private static final int LAYOUT_GRIDVIEWLAYOUTSS = 82;
    private static final int LAYOUT_ITEM4HBUTTONVIEW = 83;
    private static final int LAYOUT_ITEMPERMSSIONSREQUIRED = 84;
    private static final int LAYOUT_ITEMSENSAICOMPAREHEADER = 85;
    private static final int LAYOUT_ITEMSENSAICOMPARELIST = 86;
    private static final int LAYOUT_ITEMSENSAIFILTER = 87;
    private static final int LAYOUT_ITEMSENSAISORTBY = 88;
    private static final int LAYOUT_LAYOUTITEMQRCODEAPPLIST = 89;
    private static final int LAYOUT_LISTITEMADDITIONALACTIVITIES = 90;
    private static final int LAYOUT_LISTITEMDEVICELAYOUT = 91;
    private static final int LAYOUT_LISTITEMDIAGNOSTICHISTORY = 92;
    private static final int LAYOUT_LISTITEMDIAGNOSTICTESTING = 93;
    private static final int LAYOUT_LISTITEMNEWDESIGNTITLE = 94;
    private static final int LAYOUT_LISTITEMOTPSERVICETYPELAYOUT = 95;
    private static final int LAYOUT_LISTITEMSCANNEDBLEDEVICELAYOUT = 96;
    private static final int LAYOUT_LISTITEMTROUBLESHOOTTESTING = 97;
    private static final int LAYOUT_OVERALLSTATSITEM = 98;
    private static final int LAYOUT_PROFILEACHIEVEMENTSITEM = 99;
    private static final int LAYOUT_PROFILEFITNESSITEM = 100;
    private static final int LAYOUT_PROFILEFITNESSSUBHEADERITEM = 101;
    private static final int LAYOUT_PROFILEGOALSITEM = 102;
    private static final int LAYOUT_PROFILEGOALSITEMHORIZONTAL = 103;
    private static final int LAYOUT_PROFILEMOREITEM = 104;
    private static final int LAYOUT_QRSCANONBOARDINGFTU = 105;
    private static final int LAYOUT_SENSAICOMPAREITEM = 115;
    private static final int LAYOUT_SENSAIDETAILS = 106;
    private static final int LAYOUT_SENSAIFEEDBACKITEM = 107;
    private static final int LAYOUT_SENSAIFILTERITEM = 108;
    private static final int LAYOUT_SENSAIFILTEROPTIONLISTITEM = 109;
    private static final int LAYOUT_SENSAIFTUACTIVITY = 110;
    private static final int LAYOUT_SENSAIHOMEACTIVITY = 111;
    private static final int LAYOUT_SENSAILISTITEM = 112;
    private static final int LAYOUT_SENSAIPROFILEITEM = 113;
    private static final int LAYOUT_SENSAISHARECARDITEM = 116;
    private static final int LAYOUT_SENSAISORTBYDIALOG = 114;
    private static final int LAYOUT_SESSIONINSIGHTSITEM = 117;
    private static final int LAYOUT_SLEEPINSIGHTSDATA = 118;
    private static final int LAYOUT_TROUBLESHOOTACTIVITYNOTIITEM = 119;
    private static final int LAYOUT_TROUBLESHOOTINGFTUVIEWPAGERLAYOUT = 120;

    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f4082a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(133);
            f4082a = sparseArray;
            sparseArray.put(1, "FTUItemCount");
            sparseArray.put(0, "_all");
            sparseArray.put(2, "achievementsData");
            sparseArray.put(3, "activitiesCompleted");
            sparseArray.put(4, "alarmInfo");
            sparseArray.put(5, "appData");
            sparseArray.put(6, "appNotificationData");
            sparseArray.put(7, "arrival");
            sparseArray.put(8, "bestOffers");
            sparseArray.put(9, "bindingDataModel1");
            sparseArray.put(10, "bleConnectionState");
            sparseArray.put(11, "bt3ConnectionState");
            sparseArray.put(12, "calorieData");
            sparseArray.put(13, "challengeItem");
            sparseArray.put(14, "challengeJoined");
            sparseArray.put(15, "clickListener");
            sparseArray.put(16, "cloudCategoryData");
            sparseArray.put(17, "compareData");
            sparseArray.put(18, "completedCalories");
            sparseArray.put(19, "completedDistance");
            sparseArray.put(20, "currentSelection");
            sparseArray.put(21, "data");
            sparseArray.put(22, "dayCount");
            sparseArray.put(23, "dayProgress");
            sparseArray.put(24, "dayProgressMax");
            sparseArray.put(25, "daysLeft");
            sparseArray.put(26, "destinationRouteLocation");
            sparseArray.put(27, "deviceData");
            sparseArray.put(28, "deviceInfo");
            sparseArray.put(29, "diagnosticData");
            sparseArray.put(30, "diagnosticTestHistoryData");
            sparseArray.put(31, "distance");
            sparseArray.put(32, "distanceCovered");
            sparseArray.put(33, "distanceData");
            sparseArray.put(34, "doMoreWithYourWatchItemCount");
            sparseArray.put(35, "featureData");
            sparseArray.put(36, "firstCardType");
            sparseArray.put(37, "fitnessData");
            sparseArray.put(38, "fitnessType");
            sparseArray.put(39, "ftuItemCount");
            sparseArray.put(40, "futureDayInfo");
            sparseArray.put(41, "goalCategory");
            sparseArray.put(42, "goalsData");
            sparseArray.put(43, "headerData");
            sparseArray.put(44, "healthInfo");
            sparseArray.put(45, "isDataAvailable");
            sparseArray.put(46, "isFirstCardDataAvailable");
            sparseArray.put(47, "isFitnessPlanOngoing");
            sparseArray.put(48, "isFlashSupported");
            sparseArray.put(49, "isFutureDay");
            sparseArray.put(50, "isHistoryPlan");
            sparseArray.put(51, "isInEditMode");
            sparseArray.put(52, "isMetricUnitData");
            sparseArray.put(53, "isPlanCompleted");
            sparseArray.put(54, "isPlanStartsTomorrow");
            sparseArray.put(55, "isProgressFull");
            sparseArray.put(56, "isRestDay");
            sparseArray.put(57, "isSetupYourWatchSettingsCompleted");
            sparseArray.put(58, "isShowingInProgress");
            sparseArray.put(59, "isUserProfileCompleted");
            sparseArray.put(60, "isWeatherEnableData");
            sparseArray.put(61, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            sparseArray.put(62, "moreData");
            sparseArray.put(63, "onClickGetRoute");
            sparseArray.put(64, "onClickSearchCategory");
            sparseArray.put(65, "onHandleBack");
            sparseArray.put(66, "onNextClick");
            sparseArray.put(67, "onPreviousClick");
            sparseArray.put(68, "onRouteReportClick");
            sparseArray.put(69, "onStartClick");
            sparseArray.put(70, "onclickHandleBack");
            sparseArray.put(71, "participantData");
            sparseArray.put(72, "participantItem");
            sparseArray.put(73, "planBg");
            sparseArray.put(74, "planHeader");
            sparseArray.put(75, "planHistoryData");
            sparseArray.put(76, "planTitle");
            sparseArray.put(77, "progress");
            sparseArray.put(78, "progressText");
            sparseArray.put(79, "qrCategoryData");
            sparseArray.put(80, "qrCodeData");
            sparseArray.put(81, "qrEditImage");
            sparseArray.put(82, "retryButtonClick");
            sparseArray.put(83, "routeTime");
            sparseArray.put(84, "selectedItemPosition");
            sparseArray.put(85, "selectedVitalsCount");
            sparseArray.put(86, "setupYourWatchItemCount");
            sparseArray.put(87, "setupYourWatchItemSelectedPosition");
            sparseArray.put(88, "shouldEnableClick");
            sparseArray.put(89, "shouldExpandAlarm");
            sparseArray.put(90, "show1kFtu");
            sparseArray.put(91, "showActivityHistory");
            sparseArray.put(92, "showAlexaConnect");
            sparseArray.put(93, "showBoatCoins");
            sparseArray.put(94, "showButtonProceed");
            sparseArray.put(95, "showButtonRepeat");
            sparseArray.put(96, "showCharging");
            sparseArray.put(97, "showClBottomButton");
            sparseArray.put(98, "showCultFitFTU");
            sparseArray.put(99, "showDynamicTab");
            sparseArray.put(100, "showFitnessChallenge");
            sparseArray.put(101, "showFitnessPlan");
            sparseArray.put(102, "showMyBuddies");
            sparseArray.put(103, "showMyBuddiesList");
            sparseArray.put(104, "showPersonalizedWatchFace");
            sparseArray.put(105, "showRepeatLayout");
            sparseArray.put(106, "showSOSSettings");
            sparseArray.put(107, "showSportScores");
            sparseArray.put(108, "showTapAndPay");
            sparseArray.put(109, "showTimer");
            sparseArray.put(110, "showWatchSettingsBigLayout");
            sparseArray.put(111, "showWatchfaceBigLayout");
            sparseArray.put(112, "showWatchfaceStudioBigLayout");
            sparseArray.put(113, "showWatchfaceStudioBigLayoutTop");
            sparseArray.put(114, "showWellnessCrew");
            sparseArray.put(115, "sleepData");
            sparseArray.put(116, "sourceRouteLocation");
            sparseArray.put(117, "startButtonClick");
            sparseArray.put(118, "stepsDataModel");
            sparseArray.put(119, "stepsGoal");
            sparseArray.put(120, "syncingStateData");
            sparseArray.put(121, "timerData");
            sparseArray.put(122, "tipsData");
            sparseArray.put(123, "title");
            sparseArray.put(124, "totalActivities");
            sparseArray.put(125, "totalActivitiesAndDistance");
            sparseArray.put(126, "totalParticipantsCount");
            sparseArray.put(127, "troubleshootingModel");
            sparseArray.put(128, "troubleshootingTestModel");
            sparseArray.put(129, "vitalName");
            sparseArray.put(130, "watchName");
            sparseArray.put(131, "wayPoints");
            sparseArray.put(132, "weekCount");
        }
    }

    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f4083a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(120);
            f4083a = hashMap;
            hashMap.put("layout/activity_add_qrcode_0", Integer.valueOf((int) R.layout.activity_add_qrcode));
            hashMap.put("layout/activity_additional_activities_0", Integer.valueOf((int) R.layout.activity_additional_activities));
            hashMap.put("layout/activity_auto_spo2_settings_0", Integer.valueOf((int) R.layout.activity_auto_spo2_settings));
            hashMap.put("layout/activity_battery_saver_mode_new_0", Integer.valueOf((int) R.layout.activity_battery_saver_mode_new));
            hashMap.put("layout/activity_boat_coin_contacts_0", Integer.valueOf((int) R.layout.activity_boat_coin_contacts));
            hashMap.put("layout/activity_boat_coins_ftu_0", Integer.valueOf((int) R.layout.activity_boat_coins_ftu));
            hashMap.put("layout/activity_boat_coins_web_viewer_0", Integer.valueOf((int) R.layout.activity_boat_coins_web_viewer));
            hashMap.put("layout/activity_camera_settings_0", Integer.valueOf((int) R.layout.activity_camera_settings));
            hashMap.put("layout/activity_check_permissions_0", Integer.valueOf((int) R.layout.activity_check_permissions));
            hashMap.put("layout/activity_contact_us_feedback_0", Integer.valueOf((int) R.layout.activity_contact_us_feedback));
            hashMap.put("layout/activity_customise4h_button_0", Integer.valueOf((int) R.layout.activity_customise4h_button));
            hashMap.put("layout/activity_drink_reminder_0", Integer.valueOf((int) R.layout.activity_drink_reminder));
            hashMap.put("layout/activity_first_time_user_0", Integer.valueOf((int) R.layout.activity_first_time_user));
            hashMap.put("layout/activity_onboarding_ftu_0", Integer.valueOf((int) R.layout.activity_onboarding_ftu));
            hashMap.put("layout/activity_overall_stats_0", Integer.valueOf((int) R.layout.activity_overall_stats));
            hashMap.put("layout/activity_profile_goals_0", Integer.valueOf((int) R.layout.activity_profile_goals));
            hashMap.put("layout/activity_profile_landing_page_0", Integer.valueOf((int) R.layout.activity_profile_landing_page));
            hashMap.put("layout/activity_remote_camera_0", Integer.valueOf((int) R.layout.activity_remote_camera));
            hashMap.put("layout/activity_schedule_0", Integer.valueOf((int) R.layout.activity_schedule));
            hashMap.put("layout/activity_sedentary_reminder_0", Integer.valueOf((int) R.layout.activity_sedentary_reminder));
            hashMap.put("layout/activity_sens_ai_compare_0", Integer.valueOf((int) R.layout.activity_sens_ai_compare));
            hashMap.put("layout/activity_sens_ai_details_0", Integer.valueOf((int) R.layout.activity_sens_ai_details));
            hashMap.put("layout/activity_sens_ai_info_0", Integer.valueOf((int) R.layout.activity_sens_ai_info));
            hashMap.put("layout/activity_sensai_filter_0", Integer.valueOf((int) R.layout.activity_sensai_filter));
            hashMap.put("layout/activity_sensai_share_screen_0", Integer.valueOf((int) R.layout.activity_sensai_share_screen));
            hashMap.put("layout/activity_session_insights_0", Integer.valueOf((int) R.layout.activity_session_insights));
            hashMap.put("layout/activity_session_insights_details_0", Integer.valueOf((int) R.layout.activity_session_insights_details));
            hashMap.put("layout/activity_troubleshooting_connectivity_success_0", Integer.valueOf((int) R.layout.activity_troubleshooting_connectivity_success));
            hashMap.put("layout/activity_troubleshooting_new_0", Integer.valueOf((int) R.layout.activity_troubleshooting_new));
            hashMap.put("layout/activity_watch_diagnostics_0", Integer.valueOf((int) R.layout.activity_watch_diagnostics));
            hashMap.put("layout/alarm_list_row_new_0", Integer.valueOf((int) R.layout.alarm_list_row_new));
            hashMap.put("layout/calendar_day_item_0", Integer.valueOf((int) R.layout.calendar_day_item));
            hashMap.put("layout/compare_popup_0", Integer.valueOf((int) R.layout.compare_popup));
            hashMap.put("layout/fitness_blog_item_0", Integer.valueOf((int) R.layout.fitness_blog_item));
            hashMap.put("layout/fitness_goals_item_0", Integer.valueOf((int) R.layout.fitness_goals_item));
            hashMap.put("layout/fragment_batting_overall_stats_0", Integer.valueOf((int) R.layout.fragment_batting_overall_stats));
            hashMap.put("layout/fragment_boat_coins_contacts_0", Integer.valueOf((int) R.layout.fragment_boat_coins_contacts));
            hashMap.put("layout/fragment_boat_coins_f_t_u_0", Integer.valueOf((int) R.layout.fragment_boat_coins_f_t_u));
            hashMap.put("layout/fragment_bowling_overall_stats_0", Integer.valueOf((int) R.layout.fragment_bowling_overall_stats));
            hashMap.put("layout/fragment_device_listing_fragment_0", Integer.valueOf((int) R.layout.fragment_device_listing_fragment));
            hashMap.put("layout/fragment_device_paired_0", Integer.valueOf((int) R.layout.fragment_device_paired));
            hashMap.put("layout/fragment_device_selection_fragment_0", Integer.valueOf((int) R.layout.fragment_device_selection_fragment));
            hashMap.put("layout/fragment_fitness_0", Integer.valueOf((int) R.layout.fragment_fitness));
            hashMap.put("layout/fragment_fitness_data_share_0", Integer.valueOf((int) R.layout.fragment_fitness_data_share));
            hashMap.put("layout/fragment_fitness_home_0", Integer.valueOf((int) R.layout.fragment_fitness_home));
            hashMap.put("layout/fragment_my_watch_0", Integer.valueOf((int) R.layout.fragment_my_watch));
            hashMap.put("layout/fragment_my_watch_status_0", Integer.valueOf((int) R.layout.fragment_my_watch_status));
            hashMap.put("layout/fragment_overall_stats_0", Integer.valueOf((int) R.layout.fragment_overall_stats));
            hashMap.put("layout/fragment_overall_stats_batting_0", Integer.valueOf((int) R.layout.fragment_overall_stats_batting));
            hashMap.put("layout/fragment_overall_stats_bowling_0", Integer.valueOf((int) R.layout.fragment_overall_stats_bowling));
            hashMap.put("layout/fragment_pairing_failed_0", Integer.valueOf((int) R.layout.fragment_pairing_failed));
            hashMap.put("layout/fragment_q_r_scan_device_0", Integer.valueOf((int) R.layout.fragment_q_r_scan_device));
            hashMap.put("layout/fragment_run_diagnostic_testing_0", Integer.valueOf((int) R.layout.fragment_run_diagnostic_testing));
            hashMap.put("layout/fragment_run_watch_diagnostics_0", Integer.valueOf((int) R.layout.fragment_run_watch_diagnostics));
            hashMap.put("layout/fragment_sens_ai_coach_0", Integer.valueOf((int) R.layout.fragment_sens_ai_coach));
            hashMap.put("layout/fragment_sens_ai_ftu_1_0", Integer.valueOf((int) R.layout.fragment_sens_ai_ftu_1));
            hashMap.put("layout/fragment_sens_ai_ftu_2_0", Integer.valueOf((int) R.layout.fragment_sens_ai_ftu_2));
            hashMap.put("layout/fragment_sens_ai_ftu_3_0", Integer.valueOf((int) R.layout.fragment_sens_ai_ftu_3));
            hashMap.put("layout/fragment_sens_ai_share_data_0", Integer.valueOf((int) R.layout.fragment_sens_ai_share_data));
            hashMap.put("layout/fragment_sensai_filter_options_0", Integer.valueOf((int) R.layout.fragment_sensai_filter_options));
            hashMap.put("layout/fragment_sensai_share_0", Integer.valueOf((int) R.layout.fragment_sensai_share));
            hashMap.put("layout/fragment_session_insights_0", Integer.valueOf((int) R.layout.fragment_session_insights));
            hashMap.put("layout/fragment_social_share_card_0", Integer.valueOf((int) R.layout.fragment_social_share_card));
            hashMap.put("layout/fragment_system_settings_0", Integer.valueOf((int) R.layout.fragment_system_settings));
            hashMap.put("layout/fragment_troubleshoooting_dnd_0", Integer.valueOf((int) R.layout.fragment_troubleshoooting_dnd));
            hashMap.put("layout/fragment_troubleshoooting_f_t_u_0", Integer.valueOf((int) R.layout.fragment_troubleshoooting_f_t_u));
            hashMap.put("layout/fragment_troubleshoot_testing_0", Integer.valueOf((int) R.layout.fragment_troubleshoot_testing));
            hashMap.put("layout/fragment_troubleshooting_0", Integer.valueOf((int) R.layout.fragment_troubleshooting));
            hashMap.put("layout/fragment_vital_heartrate_0", Integer.valueOf((int) R.layout.fragment_vital_heartrate));
            hashMap.put("layout/fragment_vital_hrv_0", Integer.valueOf((int) R.layout.fragment_vital_hrv));
            hashMap.put("layout/fragment_vital_manual_spo2_0", Integer.valueOf((int) R.layout.fragment_vital_manual_spo2));
            hashMap.put("layout/fragment_vital_manual_stress_0", Integer.valueOf((int) R.layout.fragment_vital_manual_stress));
            hashMap.put("layout/fragment_vital_periodic_spo2_0", Integer.valueOf((int) R.layout.fragment_vital_periodic_spo2));
            hashMap.put("layout/fragment_vital_periodic_stress_0", Integer.valueOf((int) R.layout.fragment_vital_periodic_stress));
            hashMap.put("layout/fragment_vital_sleep_0", Integer.valueOf((int) R.layout.fragment_vital_sleep));
            hashMap.put("layout/fragment_vital_steps_0", Integer.valueOf((int) R.layout.fragment_vital_steps));
            hashMap.put("layout/fragment_vital_temperature_0", Integer.valueOf((int) R.layout.fragment_vital_temperature));
            hashMap.put("layout/fragment_vitals_0", Integer.valueOf((int) R.layout.fragment_vitals));
            hashMap.put("layout/fragment_watch_diagnostic_dashboard_0", Integer.valueOf((int) R.layout.fragment_watch_diagnostic_dashboard));
            hashMap.put("layout/fragment_watch_diagnostic_test_history_0", Integer.valueOf((int) R.layout.fragment_watch_diagnostic_test_history));
            hashMap.put("layout/fragment_watch_settings_new_0", Integer.valueOf((int) R.layout.fragment_watch_settings_new));
            hashMap.put("layout/gridviewlayout_ss_0", Integer.valueOf((int) R.layout.gridviewlayout_ss));
            hashMap.put("layout/item_4h_button_view_0", Integer.valueOf((int) R.layout.item_4h_button_view));
            hashMap.put("layout/item_permssions_required_0", Integer.valueOf((int) R.layout.item_permssions_required));
            hashMap.put("layout/item_sens_ai_compare_header_0", Integer.valueOf((int) R.layout.item_sens_ai_compare_header));
            hashMap.put("layout/item_sens_ai_compare_list_0", Integer.valueOf((int) R.layout.item_sens_ai_compare_list));
            hashMap.put("layout/item_sens_ai_filter_0", Integer.valueOf((int) R.layout.item_sens_ai_filter));
            hashMap.put("layout/item_sens_ai_sort_by_0", Integer.valueOf((int) R.layout.item_sens_ai_sort_by));
            hashMap.put("layout/layout_item_qr_code_app_list_0", Integer.valueOf((int) R.layout.layout_item_qr_code_app_list));
            hashMap.put("layout/list_item_additional_activities_0", Integer.valueOf((int) R.layout.list_item_additional_activities));
            hashMap.put("layout/list_item_device_layout_0", Integer.valueOf((int) R.layout.list_item_device_layout));
            hashMap.put("layout/list_item_diagnostic_history_0", Integer.valueOf((int) R.layout.list_item_diagnostic_history));
            hashMap.put("layout/list_item_diagnostic_testing_0", Integer.valueOf((int) R.layout.list_item_diagnostic_testing));
            hashMap.put("layout/list_item_new_design_title_0", Integer.valueOf((int) R.layout.list_item_new_design_title));
            hashMap.put("layout/list_item_otp_service_type_layout_0", Integer.valueOf((int) R.layout.list_item_otp_service_type_layout));
            hashMap.put("layout/list_item_scanned_ble_device_layout_0", Integer.valueOf((int) R.layout.list_item_scanned_ble_device_layout));
            hashMap.put("layout/list_item_troubleshoot_testing_0", Integer.valueOf((int) R.layout.list_item_troubleshoot_testing));
            hashMap.put("layout/overall_stats_item_0", Integer.valueOf((int) R.layout.overall_stats_item));
            hashMap.put("layout/profile_achievements_item_0", Integer.valueOf((int) R.layout.profile_achievements_item));
            hashMap.put("layout/profile_fitness_item_0", Integer.valueOf((int) R.layout.profile_fitness_item));
            hashMap.put("layout/profile_fitness_subheader_item_0", Integer.valueOf((int) R.layout.profile_fitness_subheader_item));
            hashMap.put("layout/profile_goals_item_0", Integer.valueOf((int) R.layout.profile_goals_item));
            hashMap.put("layout/profile_goals_item_horizontal_0", Integer.valueOf((int) R.layout.profile_goals_item_horizontal));
            hashMap.put("layout/profile_more_item_0", Integer.valueOf((int) R.layout.profile_more_item));
            hashMap.put("layout/qr_scan_on_boarding_f_t_u_0", Integer.valueOf((int) R.layout.qr_scan_on_boarding_f_t_u));
            hashMap.put("layout/sens_ai_details_0", Integer.valueOf((int) R.layout.sens_ai_details));
            hashMap.put("layout/sens_ai_feedback_item_0", Integer.valueOf((int) R.layout.sens_ai_feedback_item));
            hashMap.put("layout/sens_ai_filter_item_0", Integer.valueOf((int) R.layout.sens_ai_filter_item));
            hashMap.put("layout/sens_ai_filter_option_list_item_0", Integer.valueOf((int) R.layout.sens_ai_filter_option_list_item));
            hashMap.put("layout/sens_ai_ftu_activity_0", Integer.valueOf((int) R.layout.sens_ai_ftu_activity));
            hashMap.put("layout/sens_ai_home_activity_0", Integer.valueOf((int) R.layout.sens_ai_home_activity));
            hashMap.put("layout/sens_ai_list_item_0", Integer.valueOf((int) R.layout.sens_ai_list_item));
            hashMap.put("layout/sens_ai_profile_item_0", Integer.valueOf((int) R.layout.sens_ai_profile_item));
            hashMap.put("layout/sens_ai_sort_by_dialog_0", Integer.valueOf((int) R.layout.sens_ai_sort_by_dialog));
            hashMap.put("layout/sensai_compare_item_0", Integer.valueOf((int) R.layout.sensai_compare_item));
            hashMap.put("layout/sensai_share_card_item_0", Integer.valueOf((int) R.layout.sensai_share_card_item));
            hashMap.put("layout/session_insights_item_0", Integer.valueOf((int) R.layout.session_insights_item));
            hashMap.put("layout/sleep_insights_data_0", Integer.valueOf((int) R.layout.sleep_insights_data));
            hashMap.put("layout/troubleshoot_activity_noti_item_0", Integer.valueOf((int) R.layout.troubleshoot_activity_noti_item));
            hashMap.put("layout/troubleshootingftu_viewpager_layout_0", Integer.valueOf((int) R.layout.troubleshootingftu_viewpager_layout));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(120);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_add_qrcode, 1);
        sparseIntArray.put(R.layout.activity_additional_activities, 2);
        sparseIntArray.put(R.layout.activity_auto_spo2_settings, 3);
        sparseIntArray.put(R.layout.activity_battery_saver_mode_new, 4);
        sparseIntArray.put(R.layout.activity_boat_coin_contacts, 5);
        sparseIntArray.put(R.layout.activity_boat_coins_ftu, 6);
        sparseIntArray.put(R.layout.activity_boat_coins_web_viewer, 7);
        sparseIntArray.put(R.layout.activity_camera_settings, 8);
        sparseIntArray.put(R.layout.activity_check_permissions, 9);
        sparseIntArray.put(R.layout.activity_contact_us_feedback, 10);
        sparseIntArray.put(R.layout.activity_customise4h_button, 11);
        sparseIntArray.put(R.layout.activity_drink_reminder, 12);
        sparseIntArray.put(R.layout.activity_first_time_user, 13);
        sparseIntArray.put(R.layout.activity_onboarding_ftu, 14);
        sparseIntArray.put(R.layout.activity_overall_stats, 15);
        sparseIntArray.put(R.layout.activity_profile_goals, 16);
        sparseIntArray.put(R.layout.activity_profile_landing_page, 17);
        sparseIntArray.put(R.layout.activity_remote_camera, 18);
        sparseIntArray.put(R.layout.activity_schedule, 19);
        sparseIntArray.put(R.layout.activity_sedentary_reminder, 20);
        sparseIntArray.put(R.layout.activity_sens_ai_compare, 21);
        sparseIntArray.put(R.layout.activity_sens_ai_details, 22);
        sparseIntArray.put(R.layout.activity_sens_ai_info, 23);
        sparseIntArray.put(R.layout.activity_sensai_filter, 24);
        sparseIntArray.put(R.layout.activity_sensai_share_screen, 25);
        sparseIntArray.put(R.layout.activity_session_insights, 26);
        sparseIntArray.put(R.layout.activity_session_insights_details, 27);
        sparseIntArray.put(R.layout.activity_troubleshooting_connectivity_success, 28);
        sparseIntArray.put(R.layout.activity_troubleshooting_new, 29);
        sparseIntArray.put(R.layout.activity_watch_diagnostics, 30);
        sparseIntArray.put(R.layout.alarm_list_row_new, 31);
        sparseIntArray.put(R.layout.calendar_day_item, 32);
        sparseIntArray.put(R.layout.compare_popup, 33);
        sparseIntArray.put(R.layout.fitness_blog_item, 34);
        sparseIntArray.put(R.layout.fitness_goals_item, 35);
        sparseIntArray.put(R.layout.fragment_batting_overall_stats, 36);
        sparseIntArray.put(R.layout.fragment_boat_coins_contacts, 37);
        sparseIntArray.put(R.layout.fragment_boat_coins_f_t_u, 38);
        sparseIntArray.put(R.layout.fragment_bowling_overall_stats, 39);
        sparseIntArray.put(R.layout.fragment_device_listing_fragment, 40);
        sparseIntArray.put(R.layout.fragment_device_paired, 41);
        sparseIntArray.put(R.layout.fragment_device_selection_fragment, 42);
        sparseIntArray.put(R.layout.fragment_fitness, 43);
        sparseIntArray.put(R.layout.fragment_fitness_data_share, 44);
        sparseIntArray.put(R.layout.fragment_fitness_home, 45);
        sparseIntArray.put(R.layout.fragment_my_watch, 46);
        sparseIntArray.put(R.layout.fragment_my_watch_status, 47);
        sparseIntArray.put(R.layout.fragment_overall_stats, 48);
        sparseIntArray.put(R.layout.fragment_overall_stats_batting, 49);
        sparseIntArray.put(R.layout.fragment_overall_stats_bowling, 50);
        sparseIntArray.put(R.layout.fragment_pairing_failed, 51);
        sparseIntArray.put(R.layout.fragment_q_r_scan_device, 52);
        sparseIntArray.put(R.layout.fragment_run_diagnostic_testing, 53);
        sparseIntArray.put(R.layout.fragment_run_watch_diagnostics, 54);
        sparseIntArray.put(R.layout.fragment_sens_ai_coach, 55);
        sparseIntArray.put(R.layout.fragment_sens_ai_ftu_1, 56);
        sparseIntArray.put(R.layout.fragment_sens_ai_ftu_2, 57);
        sparseIntArray.put(R.layout.fragment_sens_ai_ftu_3, 58);
        sparseIntArray.put(R.layout.fragment_sens_ai_share_data, 59);
        sparseIntArray.put(R.layout.fragment_sensai_filter_options, 60);
        sparseIntArray.put(R.layout.fragment_sensai_share, 61);
        sparseIntArray.put(R.layout.fragment_session_insights, 62);
        sparseIntArray.put(R.layout.fragment_social_share_card, 63);
        sparseIntArray.put(R.layout.fragment_system_settings, 64);
        sparseIntArray.put(R.layout.fragment_troubleshoooting_dnd, 65);
        sparseIntArray.put(R.layout.fragment_troubleshoooting_f_t_u, 66);
        sparseIntArray.put(R.layout.fragment_troubleshoot_testing, 67);
        sparseIntArray.put(R.layout.fragment_troubleshooting, 68);
        sparseIntArray.put(R.layout.fragment_vital_heartrate, 69);
        sparseIntArray.put(R.layout.fragment_vital_hrv, 70);
        sparseIntArray.put(R.layout.fragment_vital_manual_spo2, 71);
        sparseIntArray.put(R.layout.fragment_vital_manual_stress, 72);
        sparseIntArray.put(R.layout.fragment_vital_periodic_spo2, 73);
        sparseIntArray.put(R.layout.fragment_vital_periodic_stress, 74);
        sparseIntArray.put(R.layout.fragment_vital_sleep, 75);
        sparseIntArray.put(R.layout.fragment_vital_steps, 76);
        sparseIntArray.put(R.layout.fragment_vital_temperature, 77);
        sparseIntArray.put(R.layout.fragment_vitals, 78);
        sparseIntArray.put(R.layout.fragment_watch_diagnostic_dashboard, 79);
        sparseIntArray.put(R.layout.fragment_watch_diagnostic_test_history, 80);
        sparseIntArray.put(R.layout.fragment_watch_settings_new, 81);
        sparseIntArray.put(R.layout.gridviewlayout_ss, 82);
        sparseIntArray.put(R.layout.item_4h_button_view, 83);
        sparseIntArray.put(R.layout.item_permssions_required, 84);
        sparseIntArray.put(R.layout.item_sens_ai_compare_header, 85);
        sparseIntArray.put(R.layout.item_sens_ai_compare_list, 86);
        sparseIntArray.put(R.layout.item_sens_ai_filter, 87);
        sparseIntArray.put(R.layout.item_sens_ai_sort_by, 88);
        sparseIntArray.put(R.layout.layout_item_qr_code_app_list, 89);
        sparseIntArray.put(R.layout.list_item_additional_activities, 90);
        sparseIntArray.put(R.layout.list_item_device_layout, 91);
        sparseIntArray.put(R.layout.list_item_diagnostic_history, 92);
        sparseIntArray.put(R.layout.list_item_diagnostic_testing, 93);
        sparseIntArray.put(R.layout.list_item_new_design_title, 94);
        sparseIntArray.put(R.layout.list_item_otp_service_type_layout, 95);
        sparseIntArray.put(R.layout.list_item_scanned_ble_device_layout, 96);
        sparseIntArray.put(R.layout.list_item_troubleshoot_testing, 97);
        sparseIntArray.put(R.layout.overall_stats_item, 98);
        sparseIntArray.put(R.layout.profile_achievements_item, 99);
        sparseIntArray.put(R.layout.profile_fitness_item, 100);
        sparseIntArray.put(R.layout.profile_fitness_subheader_item, 101);
        sparseIntArray.put(R.layout.profile_goals_item, 102);
        sparseIntArray.put(R.layout.profile_goals_item_horizontal, 103);
        sparseIntArray.put(R.layout.profile_more_item, 104);
        sparseIntArray.put(R.layout.qr_scan_on_boarding_f_t_u, 105);
        sparseIntArray.put(R.layout.sens_ai_details, 106);
        sparseIntArray.put(R.layout.sens_ai_feedback_item, 107);
        sparseIntArray.put(R.layout.sens_ai_filter_item, 108);
        sparseIntArray.put(R.layout.sens_ai_filter_option_list_item, 109);
        sparseIntArray.put(R.layout.sens_ai_ftu_activity, 110);
        sparseIntArray.put(R.layout.sens_ai_home_activity, 111);
        sparseIntArray.put(R.layout.sens_ai_list_item, 112);
        sparseIntArray.put(R.layout.sens_ai_profile_item, 113);
        sparseIntArray.put(R.layout.sens_ai_sort_by_dialog, 114);
        sparseIntArray.put(R.layout.sensai_compare_item, 115);
        sparseIntArray.put(R.layout.sensai_share_card_item, 116);
        sparseIntArray.put(R.layout.session_insights_item, 117);
        sparseIntArray.put(R.layout.sleep_insights_data, 118);
        sparseIntArray.put(R.layout.troubleshoot_activity_noti_item, 119);
        sparseIntArray.put(R.layout.troubleshootingftu_viewpager_layout, 120);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/activity_add_qrcode_0".equals(obj)) {
                    return new ActivityAddQrcodeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_add_qrcode is invalid. Received: " + obj);
            case 2:
                if ("layout/activity_additional_activities_0".equals(obj)) {
                    return new ActivityAdditionalActivitiesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_additional_activities is invalid. Received: " + obj);
            case 3:
                if ("layout/activity_auto_spo2_settings_0".equals(obj)) {
                    return new ActivityAutoSpo2SettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_auto_spo2_settings is invalid. Received: " + obj);
            case 4:
                if ("layout/activity_battery_saver_mode_new_0".equals(obj)) {
                    return new ActivityBatterySaverModeNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_battery_saver_mode_new is invalid. Received: " + obj);
            case 5:
                if ("layout/activity_boat_coin_contacts_0".equals(obj)) {
                    return new ActivityBoatCoinContactsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_boat_coin_contacts is invalid. Received: " + obj);
            case 6:
                if ("layout/activity_boat_coins_ftu_0".equals(obj)) {
                    return new ActivityBoatCoinsFtuBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_boat_coins_ftu is invalid. Received: " + obj);
            case 7:
                if ("layout/activity_boat_coins_web_viewer_0".equals(obj)) {
                    return new ActivityBoatCoinsWebViewerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_boat_coins_web_viewer is invalid. Received: " + obj);
            case 8:
                if ("layout/activity_camera_settings_0".equals(obj)) {
                    return new ActivityCameraSettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_camera_settings is invalid. Received: " + obj);
            case 9:
                if ("layout/activity_check_permissions_0".equals(obj)) {
                    return new ActivityCheckPermissionsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_check_permissions is invalid. Received: " + obj);
            case 10:
                if ("layout/activity_contact_us_feedback_0".equals(obj)) {
                    return new ActivityContactUsFeedbackBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_contact_us_feedback is invalid. Received: " + obj);
            case 11:
                if ("layout/activity_customise4h_button_0".equals(obj)) {
                    return new ActivityCustomise4hButtonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_customise4h_button is invalid. Received: " + obj);
            case 12:
                if ("layout/activity_drink_reminder_0".equals(obj)) {
                    return new ActivityDrinkReminderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_drink_reminder is invalid. Received: " + obj);
            case 13:
                if ("layout/activity_first_time_user_0".equals(obj)) {
                    return new ActivityFirstTimeUserBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_first_time_user is invalid. Received: " + obj);
            case 14:
                if ("layout/activity_onboarding_ftu_0".equals(obj)) {
                    return new ActivityOnboardingFtuBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_onboarding_ftu is invalid. Received: " + obj);
            case 15:
                if ("layout/activity_overall_stats_0".equals(obj)) {
                    return new ActivityOverallStatsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_overall_stats is invalid. Received: " + obj);
            case 16:
                if ("layout/activity_profile_goals_0".equals(obj)) {
                    return new ActivityProfileGoalsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_profile_goals is invalid. Received: " + obj);
            case 17:
                if ("layout/activity_profile_landing_page_0".equals(obj)) {
                    return new ActivityProfileLandingPageBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_profile_landing_page is invalid. Received: " + obj);
            case 18:
                if ("layout/activity_remote_camera_0".equals(obj)) {
                    return new ActivityRemoteCameraBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_remote_camera is invalid. Received: " + obj);
            case 19:
                if ("layout/activity_schedule_0".equals(obj)) {
                    return new ActivityScheduleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_schedule is invalid. Received: " + obj);
            case 20:
                if ("layout/activity_sedentary_reminder_0".equals(obj)) {
                    return new ActivitySedentaryReminderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sedentary_reminder is invalid. Received: " + obj);
            case 21:
                if ("layout/activity_sens_ai_compare_0".equals(obj)) {
                    return new ActivitySensAiCompareBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sens_ai_compare is invalid. Received: " + obj);
            case 22:
                if ("layout/activity_sens_ai_details_0".equals(obj)) {
                    return new ActivitySensAiDetailsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sens_ai_details is invalid. Received: " + obj);
            case 23:
                if ("layout/activity_sens_ai_info_0".equals(obj)) {
                    return new ActivitySensAiInfoBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sens_ai_info is invalid. Received: " + obj);
            case 24:
                if ("layout/activity_sensai_filter_0".equals(obj)) {
                    return new ActivitySensaiFilterBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sensai_filter is invalid. Received: " + obj);
            case 25:
                if ("layout/activity_sensai_share_screen_0".equals(obj)) {
                    return new ActivitySensaiShareScreenBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_sensai_share_screen is invalid. Received: " + obj);
            case 26:
                if ("layout/activity_session_insights_0".equals(obj)) {
                    return new ActivitySessionInsightsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_session_insights is invalid. Received: " + obj);
            case 27:
                if ("layout/activity_session_insights_details_0".equals(obj)) {
                    return new ActivitySessionInsightsDetailsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_session_insights_details is invalid. Received: " + obj);
            case 28:
                if ("layout/activity_troubleshooting_connectivity_success_0".equals(obj)) {
                    return new ActivityTroubleshootingConnectivitySuccessBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_troubleshooting_connectivity_success is invalid. Received: " + obj);
            case 29:
                if ("layout/activity_troubleshooting_new_0".equals(obj)) {
                    return new ActivityTroubleshootingNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_troubleshooting_new is invalid. Received: " + obj);
            case 30:
                if ("layout/activity_watch_diagnostics_0".equals(obj)) {
                    return new ActivityWatchDiagnosticsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_watch_diagnostics is invalid. Received: " + obj);
            case 31:
                if ("layout/alarm_list_row_new_0".equals(obj)) {
                    return new AlarmListRowNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for alarm_list_row_new is invalid. Received: " + obj);
            case 32:
                if ("layout/calendar_day_item_0".equals(obj)) {
                    return new CalendarDayItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for calendar_day_item is invalid. Received: " + obj);
            case 33:
                if ("layout/compare_popup_0".equals(obj)) {
                    return new ComparePopupBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for compare_popup is invalid. Received: " + obj);
            case 34:
                if ("layout/fitness_blog_item_0".equals(obj)) {
                    return new FitnessBlogItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_blog_item is invalid. Received: " + obj);
            case 35:
                if ("layout/fitness_goals_item_0".equals(obj)) {
                    return new FitnessGoalsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_goals_item is invalid. Received: " + obj);
            case 36:
                if ("layout/fragment_batting_overall_stats_0".equals(obj)) {
                    return new FragmentBattingOverallStatsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_batting_overall_stats is invalid. Received: " + obj);
            case 37:
                if ("layout/fragment_boat_coins_contacts_0".equals(obj)) {
                    return new FragmentBoatCoinsContactsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_boat_coins_contacts is invalid. Received: " + obj);
            case 38:
                if ("layout/fragment_boat_coins_f_t_u_0".equals(obj)) {
                    return new FragmentBoatCoinsFTUBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_boat_coins_f_t_u is invalid. Received: " + obj);
            case 39:
                if ("layout/fragment_bowling_overall_stats_0".equals(obj)) {
                    return new FragmentBowlingOverallStatsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_bowling_overall_stats is invalid. Received: " + obj);
            case 40:
                if ("layout/fragment_device_listing_fragment_0".equals(obj)) {
                    return new FragmentDeviceListingFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_device_listing_fragment is invalid. Received: " + obj);
            case 41:
                if ("layout/fragment_device_paired_0".equals(obj)) {
                    return new FragmentDevicePairedBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_device_paired is invalid. Received: " + obj);
            case 42:
                if ("layout/fragment_device_selection_fragment_0".equals(obj)) {
                    return new FragmentDeviceSelectionFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_device_selection_fragment is invalid. Received: " + obj);
            case 43:
                if ("layout/fragment_fitness_0".equals(obj)) {
                    return new FragmentFitnessBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_fitness is invalid. Received: " + obj);
            case 44:
                if ("layout/fragment_fitness_data_share_0".equals(obj)) {
                    return new FragmentFitnessDataShareBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_fitness_data_share is invalid. Received: " + obj);
            case 45:
                if ("layout/fragment_fitness_home_0".equals(obj)) {
                    return new FragmentFitnessHomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_fitness_home is invalid. Received: " + obj);
            case 46:
                if ("layout/fragment_my_watch_0".equals(obj)) {
                    return new FragmentMyWatchBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_my_watch is invalid. Received: " + obj);
            case 47:
                if ("layout/fragment_my_watch_status_0".equals(obj)) {
                    return new FragmentMyWatchStatusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_my_watch_status is invalid. Received: " + obj);
            case 48:
                if ("layout/fragment_overall_stats_0".equals(obj)) {
                    return new FragmentOverallStatsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_overall_stats is invalid. Received: " + obj);
            case 49:
                if ("layout/fragment_overall_stats_batting_0".equals(obj)) {
                    return new FragmentOverallStatsBattingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_overall_stats_batting is invalid. Received: " + obj);
            case 50:
                if ("layout/fragment_overall_stats_bowling_0".equals(obj)) {
                    return new FragmentOverallStatsBowlingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_overall_stats_bowling is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/fragment_pairing_failed_0".equals(obj)) {
                    return new FragmentPairingFailedBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_pairing_failed is invalid. Received: " + obj);
            case 52:
                if ("layout/fragment_q_r_scan_device_0".equals(obj)) {
                    return new FragmentQRScanDeviceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_q_r_scan_device is invalid. Received: " + obj);
            case 53:
                if ("layout/fragment_run_diagnostic_testing_0".equals(obj)) {
                    return new FragmentRunDiagnosticTestingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_run_diagnostic_testing is invalid. Received: " + obj);
            case 54:
                if ("layout/fragment_run_watch_diagnostics_0".equals(obj)) {
                    return new FragmentRunWatchDiagnosticsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_run_watch_diagnostics is invalid. Received: " + obj);
            case 55:
                if ("layout/fragment_sens_ai_coach_0".equals(obj)) {
                    return new FragmentSensAiCoachBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sens_ai_coach is invalid. Received: " + obj);
            case 56:
                if ("layout/fragment_sens_ai_ftu_1_0".equals(obj)) {
                    return new FragmentSensAiFtu1BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sens_ai_ftu_1 is invalid. Received: " + obj);
            case 57:
                if ("layout/fragment_sens_ai_ftu_2_0".equals(obj)) {
                    return new FragmentSensAiFtu2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sens_ai_ftu_2 is invalid. Received: " + obj);
            case 58:
                if ("layout/fragment_sens_ai_ftu_3_0".equals(obj)) {
                    return new FragmentSensAiFtu3BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sens_ai_ftu_3 is invalid. Received: " + obj);
            case 59:
                if ("layout/fragment_sens_ai_share_data_0".equals(obj)) {
                    return new FragmentSensAiShareDataBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sens_ai_share_data is invalid. Received: " + obj);
            case 60:
                if ("layout/fragment_sensai_filter_options_0".equals(obj)) {
                    return new FragmentSensaiFilterOptionsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sensai_filter_options is invalid. Received: " + obj);
            case 61:
                if ("layout/fragment_sensai_share_0".equals(obj)) {
                    return new FragmentSensaiShareBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_sensai_share is invalid. Received: " + obj);
            case 62:
                if ("layout/fragment_session_insights_0".equals(obj)) {
                    return new FragmentSessionInsightsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_session_insights is invalid. Received: " + obj);
            case 63:
                if ("layout/fragment_social_share_card_0".equals(obj)) {
                    return new FragmentSocialShareCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_social_share_card is invalid. Received: " + obj);
            case 64:
                if ("layout/fragment_system_settings_0".equals(obj)) {
                    return new FragmentSystemSettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_system_settings is invalid. Received: " + obj);
            case 65:
                if ("layout/fragment_troubleshoooting_dnd_0".equals(obj)) {
                    return new FragmentTroubleshoootingDndBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_troubleshoooting_dnd is invalid. Received: " + obj);
            case 66:
                if ("layout/fragment_troubleshoooting_f_t_u_0".equals(obj)) {
                    return new FragmentTroubleshoootingFTUBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_troubleshoooting_f_t_u is invalid. Received: " + obj);
            case 67:
                if ("layout/fragment_troubleshoot_testing_0".equals(obj)) {
                    return new FragmentTroubleshootTestingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_troubleshoot_testing is invalid. Received: " + obj);
            case 68:
                if ("layout/fragment_troubleshooting_0".equals(obj)) {
                    return new FragmentTroubleshootingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_troubleshooting is invalid. Received: " + obj);
            case 69:
                if ("layout/fragment_vital_heartrate_0".equals(obj)) {
                    return new FragmentVitalHeartrateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_heartrate is invalid. Received: " + obj);
            case 70:
                if ("layout/fragment_vital_hrv_0".equals(obj)) {
                    return new FragmentVitalHrvBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_hrv is invalid. Received: " + obj);
            case 71:
                if ("layout/fragment_vital_manual_spo2_0".equals(obj)) {
                    return new FragmentVitalManualSpo2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_manual_spo2 is invalid. Received: " + obj);
            case 72:
                if ("layout/fragment_vital_manual_stress_0".equals(obj)) {
                    return new FragmentVitalManualStressBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_manual_stress is invalid. Received: " + obj);
            case 73:
                if ("layout/fragment_vital_periodic_spo2_0".equals(obj)) {
                    return new FragmentVitalPeriodicSpo2BindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_periodic_spo2 is invalid. Received: " + obj);
            case 74:
                if ("layout/fragment_vital_periodic_stress_0".equals(obj)) {
                    return new FragmentVitalPeriodicStressBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_periodic_stress is invalid. Received: " + obj);
            case 75:
                if ("layout/fragment_vital_sleep_0".equals(obj)) {
                    return new FragmentVitalSleepBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_sleep is invalid. Received: " + obj);
            case 76:
                if ("layout/fragment_vital_steps_0".equals(obj)) {
                    return new FragmentVitalStepsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_steps is invalid. Received: " + obj);
            case 77:
                if ("layout/fragment_vital_temperature_0".equals(obj)) {
                    return new FragmentVitalTemperatureBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vital_temperature is invalid. Received: " + obj);
            case 78:
                if ("layout/fragment_vitals_0".equals(obj)) {
                    return new FragmentVitalsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_vitals is invalid. Received: " + obj);
            case 79:
                if ("layout/fragment_watch_diagnostic_dashboard_0".equals(obj)) {
                    return new FragmentWatchDiagnosticDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_diagnostic_dashboard is invalid. Received: " + obj);
            case 80:
                if ("layout/fragment_watch_diagnostic_test_history_0".equals(obj)) {
                    return new FragmentWatchDiagnosticTestHistoryBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_diagnostic_test_history is invalid. Received: " + obj);
            case 81:
                if ("layout/fragment_watch_settings_new_0".equals(obj)) {
                    return new FragmentWatchSettingsNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_watch_settings_new is invalid. Received: " + obj);
            case 82:
                if ("layout/gridviewlayout_ss_0".equals(obj)) {
                    return new GridviewlayoutSsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for gridviewlayout_ss is invalid. Received: " + obj);
            case 83:
                if ("layout/item_4h_button_view_0".equals(obj)) {
                    return new Item4hButtonViewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_4h_button_view is invalid. Received: " + obj);
            case 84:
                if ("layout/item_permssions_required_0".equals(obj)) {
                    return new ItemPermssionsRequiredBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_permssions_required is invalid. Received: " + obj);
            case 85:
                if ("layout/item_sens_ai_compare_header_0".equals(obj)) {
                    return new ItemSensAiCompareHeaderBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_sens_ai_compare_header is invalid. Received: " + obj);
            case 86:
                if ("layout/item_sens_ai_compare_list_0".equals(obj)) {
                    return new ItemSensAiCompareListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_sens_ai_compare_list is invalid. Received: " + obj);
            case 87:
                if ("layout/item_sens_ai_filter_0".equals(obj)) {
                    return new ItemSensAiFilterBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_sens_ai_filter is invalid. Received: " + obj);
            case 88:
                if ("layout/item_sens_ai_sort_by_0".equals(obj)) {
                    return new ItemSensAiSortByBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_sens_ai_sort_by is invalid. Received: " + obj);
            case 89:
                if ("layout/layout_item_qr_code_app_list_0".equals(obj)) {
                    return new LayoutItemQrCodeAppListBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_item_qr_code_app_list is invalid. Received: " + obj);
            case 90:
                if ("layout/list_item_additional_activities_0".equals(obj)) {
                    return new ListItemAdditionalActivitiesBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_additional_activities is invalid. Received: " + obj);
            case 91:
                if ("layout/list_item_device_layout_0".equals(obj)) {
                    return new ListItemDeviceLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_device_layout is invalid. Received: " + obj);
            case 92:
                if ("layout/list_item_diagnostic_history_0".equals(obj)) {
                    return new ListItemDiagnosticHistoryBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_diagnostic_history is invalid. Received: " + obj);
            case 93:
                if ("layout/list_item_diagnostic_testing_0".equals(obj)) {
                    return new ListItemDiagnosticTestingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_diagnostic_testing is invalid. Received: " + obj);
            case 94:
                if ("layout/list_item_new_design_title_0".equals(obj)) {
                    return new ListItemNewDesignTitleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_new_design_title is invalid. Received: " + obj);
            case 95:
                if ("layout/list_item_otp_service_type_layout_0".equals(obj)) {
                    return new ListItemOtpServiceTypeLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_otp_service_type_layout is invalid. Received: " + obj);
            case 96:
                if ("layout/list_item_scanned_ble_device_layout_0".equals(obj)) {
                    return new ListItemScannedBleDeviceLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_scanned_ble_device_layout is invalid. Received: " + obj);
            case 97:
                if ("layout/list_item_troubleshoot_testing_0".equals(obj)) {
                    return new ListItemTroubleshootTestingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_troubleshoot_testing is invalid. Received: " + obj);
            case 98:
                if ("layout/overall_stats_item_0".equals(obj)) {
                    return new OverallStatsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for overall_stats_item is invalid. Received: " + obj);
            case 99:
                if ("layout/profile_achievements_item_0".equals(obj)) {
                    return new ProfileAchievementsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_achievements_item is invalid. Received: " + obj);
            case 100:
                if ("layout/profile_fitness_item_0".equals(obj)) {
                    return new ProfileFitnessItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_fitness_item is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding2(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 101:
                if ("layout/profile_fitness_subheader_item_0".equals(obj)) {
                    return new ProfileFitnessSubheaderItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_fitness_subheader_item is invalid. Received: " + obj);
            case 102:
                if ("layout/profile_goals_item_0".equals(obj)) {
                    return new ProfileGoalsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_goals_item is invalid. Received: " + obj);
            case 103:
                if ("layout/profile_goals_item_horizontal_0".equals(obj)) {
                    return new ProfileGoalsItemHorizontalBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_goals_item_horizontal is invalid. Received: " + obj);
            case 104:
                if ("layout/profile_more_item_0".equals(obj)) {
                    return new ProfileMoreItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_more_item is invalid. Received: " + obj);
            case 105:
                if ("layout/qr_scan_on_boarding_f_t_u_0".equals(obj)) {
                    return new QrScanOnBoardingFTUBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for qr_scan_on_boarding_f_t_u is invalid. Received: " + obj);
            case 106:
                if ("layout/sens_ai_details_0".equals(obj)) {
                    return new SensAiDetailsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_details is invalid. Received: " + obj);
            case 107:
                if ("layout/sens_ai_feedback_item_0".equals(obj)) {
                    return new SensAiFeedbackItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_feedback_item is invalid. Received: " + obj);
            case 108:
                if ("layout/sens_ai_filter_item_0".equals(obj)) {
                    return new SensAiFilterItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_filter_item is invalid. Received: " + obj);
            case 109:
                if ("layout/sens_ai_filter_option_list_item_0".equals(obj)) {
                    return new SensAiFilterOptionListItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_filter_option_list_item is invalid. Received: " + obj);
            case 110:
                if ("layout/sens_ai_ftu_activity_0".equals(obj)) {
                    return new SensAiFtuActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_ftu_activity is invalid. Received: " + obj);
            case 111:
                if ("layout/sens_ai_home_activity_0".equals(obj)) {
                    return new SensAiHomeActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_home_activity is invalid. Received: " + obj);
            case 112:
                if ("layout/sens_ai_list_item_0".equals(obj)) {
                    return new SensAiListItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_list_item is invalid. Received: " + obj);
            case 113:
                if ("layout/sens_ai_profile_item_0".equals(obj)) {
                    return new SensAiProfileItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_profile_item is invalid. Received: " + obj);
            case 114:
                if ("layout/sens_ai_sort_by_dialog_0".equals(obj)) {
                    return new SensAiSortByDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sens_ai_sort_by_dialog is invalid. Received: " + obj);
            case 115:
                if ("layout/sensai_compare_item_0".equals(obj)) {
                    return new SensaiCompareItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sensai_compare_item is invalid. Received: " + obj);
            case 116:
                if ("layout/sensai_share_card_item_0".equals(obj)) {
                    return new SensaiShareCardItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sensai_share_card_item is invalid. Received: " + obj);
            case 117:
                if ("layout/session_insights_item_0".equals(obj)) {
                    return new SessionInsightsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for session_insights_item is invalid. Received: " + obj);
            case 118:
                if ("layout/sleep_insights_data_0".equals(obj)) {
                    return new SleepInsightsDataBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sleep_insights_data is invalid. Received: " + obj);
            case 119:
                if ("layout/troubleshoot_activity_noti_item_0".equals(obj)) {
                    return new TroubleshootActivityNotiItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for troubleshoot_activity_noti_item is invalid. Received: " + obj);
            case 120:
                if ("layout/troubleshootingftu_viewpager_layout_0".equals(obj)) {
                    return new TroubleshootingftuViewpagerLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for troubleshootingftu_viewpager_layout is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(26);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.activitymodes.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.customreminders.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.dashboard2.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.femalewellness.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnessbuddies.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.fitnesschallenges.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.navigation.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.permissionsandsettings.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.qrtray.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.remotecommandframework.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.respiratoryrate.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sleepenergyscore.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.smartalert.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sos.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.sportsnotification.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.tappy.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.theme.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.watchfaceui.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.weather.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.android.weeklyreport.DataBinderMapperImpl());
        arrayList.add(new com.coveiot.leaderboard.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.category.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.direction.ui.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.navigation.ui.DataBinderMapperImpl());
        arrayList.add(new com.mappls.sdk.nearby.plugin.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f4082a.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                int i3 = (i2 - 1) / 50;
                if (i3 != 0) {
                    if (i3 != 1) {
                        if (i3 != 2) {
                            return null;
                        }
                        return internalGetViewDataBinding2(dataBindingComponent, view, i2, tag);
                    }
                    return internalGetViewDataBinding1(dataBindingComponent, view, i2, tag);
                }
                return internalGetViewDataBinding0(dataBindingComponent, view, i2, tag);
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = b.f4083a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
