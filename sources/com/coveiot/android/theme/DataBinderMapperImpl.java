package com.coveiot.android.theme;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.coveiot.android.theme.databinding.ActivityStrideLengthAnimationBindingImpl;
import com.coveiot.android.theme.databinding.ArcCircularProgressBarBindingImpl;
import com.coveiot.android.theme.databinding.BestoffersContainersBindingImpl;
import com.coveiot.android.theme.databinding.CommonErrorMessageDialogBindingImpl;
import com.coveiot.android.theme.databinding.CommonMessageDialogBindingImpl;
import com.coveiot.android.theme.databinding.CommonMessageDialogWithoutDrawableBindingImpl;
import com.coveiot.android.theme.databinding.ConnectedDeviceInfoCardDashboardBindingImpl;
import com.coveiot.android.theme.databinding.DashboardDynamicWebLayoutBindingImpl;
import com.coveiot.android.theme.databinding.DashboardFitnessCardLayoutBindingImpl;
import com.coveiot.android.theme.databinding.DeviceNotPairedBindingImpl;
import com.coveiot.android.theme.databinding.DialogCalendarRangeDateBindingImpl;
import com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBindingImpl;
import com.coveiot.android.theme.databinding.ExclusiveCardBoatCoinsBindingImpl;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBindingImpl;
import com.coveiot.android.theme.databinding.FitnessIndusindWellnessCrewBindingImpl;
import com.coveiot.android.theme.databinding.FitnessVitalCardContainerDashboardBindingImpl;
import com.coveiot.android.theme.databinding.FitnessVitalsCardRectangleBindingImpl;
import com.coveiot.android.theme.databinding.FitnessVitalsCardWithBackgroundBindingImpl;
import com.coveiot.android.theme.databinding.FragmentResendOtpInfoDialogBindingImpl;
import com.coveiot.android.theme.databinding.GenericDialogTwoButtonsBindingImpl;
import com.coveiot.android.theme.databinding.GenericDialogTwoButtonsNewBindingImpl;
import com.coveiot.android.theme.databinding.GenericDialogWithTwoOptionsBindingImpl;
import com.coveiot.android.theme.databinding.InfoDetailsBindingImpl;
import com.coveiot.android.theme.databinding.ItemListFitnessVitalsLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ItemListFitnessVitalsLayoutFullBindingImpl;
import com.coveiot.android.theme.databinding.KeepGoingLayoutDashboardBindingImpl;
import com.coveiot.android.theme.databinding.LayoutCultFitFtuCardBindingImpl;
import com.coveiot.android.theme.databinding.LayoutDashboardDoMoreWithYourWatchGridItemBindingImpl;
import com.coveiot.android.theme.databinding.LayoutDashboardVitalGridItemBindingImpl;
import com.coveiot.android.theme.databinding.LayoutMyBuddiesFtuCardBindingImpl;
import com.coveiot.android.theme.databinding.ListItemDeviceFeatureMyWatchLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemImageTitleSubtitleBindingImpl;
import com.coveiot.android.theme.databinding.ListItemInsightsLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemSleepStagesLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemVitalTipsLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemVitalsLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemWorkoutVideosLayoutBindingImpl;
import com.coveiot.android.theme.databinding.ListItemYourAchievementsLayoutBindingImpl;
import com.coveiot.android.theme.databinding.PersonalizedWatchFaceDashboardBindingImpl;
import com.coveiot.android.theme.databinding.PersonalizedWatchFaceMyWatchBindingImpl;
import com.coveiot.android.theme.databinding.PickerDialogBindingImpl;
import com.coveiot.android.theme.databinding.ProfileCompletionCardDashboardBindingImpl;
import com.coveiot.android.theme.databinding.ReminderLayoutNewBindingImpl;
import com.coveiot.android.theme.databinding.RepeatLayoutNewBindingImpl;
import com.coveiot.android.theme.databinding.RoundedCardCalendarNavLayoutBindingImpl;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBindingImpl;
import com.coveiot.android.theme.databinding.RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBindingImpl;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoBindingImpl;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoWithProgressBindingImpl;
import com.coveiot.android.theme.databinding.SmallRoundedCardIconHeaderStatusBindingImpl;
import com.coveiot.android.theme.databinding.StayTunnedContentHeaderInfoImageTextbuttonBindingImpl;
import com.coveiot.android.theme.databinding.ToolbarGenericDashboardBindingImpl;
import com.coveiot.android.theme.databinding.TooltipDialogTwoButtonsBindingImpl;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBindingImpl;
import com.coveiot.android.theme.databinding.WatchFaceStudioBigCardDashboardBindingImpl;
import com.coveiot.android.theme.databinding.WatchSettingsBigCardDashboardBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes7.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYSTRIDELENGTHANIMATION = 1;
    private static final int LAYOUT_ARCCIRCULARPROGRESSBAR = 2;
    private static final int LAYOUT_BESTOFFERSCONTAINERS = 3;
    private static final int LAYOUT_COMMONERRORMESSAGEDIALOG = 4;
    private static final int LAYOUT_COMMONMESSAGEDIALOG = 5;
    private static final int LAYOUT_COMMONMESSAGEDIALOGWITHOUTDRAWABLE = 6;
    private static final int LAYOUT_CONNECTEDDEVICEINFOCARDDASHBOARD = 7;
    private static final int LAYOUT_DASHBOARDDYNAMICWEBLAYOUT = 8;
    private static final int LAYOUT_DASHBOARDFITNESSCARDLAYOUT = 9;
    private static final int LAYOUT_DEVICENOTPAIRED = 10;
    private static final int LAYOUT_DIALOGCALENDARRANGEDATE = 11;
    private static final int LAYOUT_DOMOREWITHYOURWATCHCARDCONTAINERDASHBOARD = 12;
    private static final int LAYOUT_EXCLUSIVECARDBOATCOINS = 13;
    private static final int LAYOUT_EXCLUSIVECARDCONTENTHEADERINFOIMAGETEXTBUTTON = 14;
    private static final int LAYOUT_FITNESSINDUSINDWELLNESSCREW = 15;
    private static final int LAYOUT_FITNESSVITALCARDCONTAINERDASHBOARD = 16;
    private static final int LAYOUT_FITNESSVITALSCARDRECTANGLE = 17;
    private static final int LAYOUT_FITNESSVITALSCARDWITHBACKGROUND = 18;
    private static final int LAYOUT_FRAGMENTRESENDOTPINFODIALOG = 19;
    private static final int LAYOUT_GENERICDIALOGTWOBUTTONS = 20;
    private static final int LAYOUT_GENERICDIALOGTWOBUTTONSNEW = 21;
    private static final int LAYOUT_GENERICDIALOGWITHTWOOPTIONS = 22;
    private static final int LAYOUT_INFODETAILS = 23;
    private static final int LAYOUT_ITEMLISTFITNESSVITALSLAYOUT = 24;
    private static final int LAYOUT_ITEMLISTFITNESSVITALSLAYOUTFULL = 25;
    private static final int LAYOUT_KEEPGOINGLAYOUTDASHBOARD = 26;
    private static final int LAYOUT_LAYOUTCULTFITFTUCARD = 27;
    private static final int LAYOUT_LAYOUTDASHBOARDDOMOREWITHYOURWATCHGRIDITEM = 28;
    private static final int LAYOUT_LAYOUTDASHBOARDVITALGRIDITEM = 29;
    private static final int LAYOUT_LAYOUTMYBUDDIESFTUCARD = 30;
    private static final int LAYOUT_LISTITEMDEVICEFEATUREMYWATCHLAYOUT = 31;
    private static final int LAYOUT_LISTITEMIMAGETITLESUBTITLE = 32;
    private static final int LAYOUT_LISTITEMINSIGHTSLAYOUT = 33;
    private static final int LAYOUT_LISTITEMSLEEPSTAGESLAYOUT = 34;
    private static final int LAYOUT_LISTITEMVITALSLAYOUT = 36;
    private static final int LAYOUT_LISTITEMVITALTIPSLAYOUT = 35;
    private static final int LAYOUT_LISTITEMWEEKPLANLAYOUT = 37;
    private static final int LAYOUT_LISTITEMWORKOUTVIDEOSLAYOUT = 38;
    private static final int LAYOUT_LISTITEMYOURACHIEVEMENTSLAYOUT = 39;
    private static final int LAYOUT_PERSONALIZEDWATCHFACEDASHBOARD = 40;
    private static final int LAYOUT_PERSONALIZEDWATCHFACEMYWATCH = 41;
    private static final int LAYOUT_PICKERDIALOG = 42;
    private static final int LAYOUT_PROFILECOMPLETIONCARDDASHBOARD = 43;
    private static final int LAYOUT_REMINDERLAYOUTNEW = 44;
    private static final int LAYOUT_REPEATLAYOUTNEW = 45;
    private static final int LAYOUT_ROUNDEDCARDCALENDARNAVLAYOUT = 46;
    private static final int LAYOUT_ROUNDEDCARDNAVLAYOUT = 47;
    private static final int LAYOUT_ROUNDEDLEFTTOPBORDERCOLORBACKGROUNDCARDHEADERINFOIMAGE = 48;
    private static final int LAYOUT_SMALLHEALTHCARDINFO = 49;
    private static final int LAYOUT_SMALLHEALTHCARDINFOWITHPROGRESS = 50;
    private static final int LAYOUT_SMALLROUNDEDCARDICONHEADERSTATUS = 51;
    private static final int LAYOUT_STAYTUNNEDCONTENTHEADERINFOIMAGETEXTBUTTON = 52;
    private static final int LAYOUT_TOOLBARGENERICDASHBOARD = 53;
    private static final int LAYOUT_TOOLTIPDIALOGTWOBUTTONS = 54;
    private static final int LAYOUT_VITALSDETAILEDLAYOUT = 55;
    private static final int LAYOUT_WATCHFACESTUDIOBIGCARDDASHBOARD = 56;
    private static final int LAYOUT_WATCHSETTINGSBIGCARDDASHBOARD = 57;

    /* loaded from: classes7.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final SparseArray<String> f6083a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(19);
            f6083a = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "bindingDataModel1");
            sparseArray.put(2, "firstCardType");
            sparseArray.put(3, "fitnessType");
            sparseArray.put(4, "healthInfo");
            sparseArray.put(5, "isDataAvailable");
            sparseArray.put(6, "isFirstCardDataAvailable");
            sparseArray.put(7, "progress");
            sparseArray.put(8, "showAlexaConnect");
            sparseArray.put(9, "showFitnessPlan");
            sparseArray.put(10, "showSOSSettings");
            sparseArray.put(11, "showSportScores");
            sparseArray.put(12, "showTapAndPay");
            sparseArray.put(13, "showWellnessCrew");
            sparseArray.put(14, "stepsDataModel");
            sparseArray.put(15, "stepsGoal");
            sparseArray.put(16, "tipsData");
            sparseArray.put(17, "title");
            sparseArray.put(18, "watchName");
        }
    }

    /* loaded from: classes7.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final HashMap<String, Integer> f6084a;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(57);
            f6084a = hashMap;
            hashMap.put("layout/activity_stride_length_animation_0", Integer.valueOf(R.layout.activity_stride_length_animation));
            hashMap.put("layout/arc_circular_progress_bar_0", Integer.valueOf(R.layout.arc_circular_progress_bar));
            hashMap.put("layout/bestoffers_containers_0", Integer.valueOf(R.layout.bestoffers_containers));
            hashMap.put("layout/common_error_message_dialog_0", Integer.valueOf(R.layout.common_error_message_dialog));
            hashMap.put("layout/common_message_dialog_0", Integer.valueOf(R.layout.common_message_dialog));
            hashMap.put("layout/common_message_dialog_without_drawable_0", Integer.valueOf(R.layout.common_message_dialog_without_drawable));
            hashMap.put("layout/connected_device_info_card_dashboard_0", Integer.valueOf(R.layout.connected_device_info_card_dashboard));
            hashMap.put("layout/dashboard_dynamic_web_layout_0", Integer.valueOf(R.layout.dashboard_dynamic_web_layout));
            hashMap.put("layout/dashboard_fitness_card_layout_0", Integer.valueOf(R.layout.dashboard_fitness_card_layout));
            hashMap.put("layout/device_not_paired_0", Integer.valueOf(R.layout.device_not_paired));
            hashMap.put("layout/dialog_calendar_range_date_0", Integer.valueOf(R.layout.dialog_calendar_range_date));
            hashMap.put("layout/do_more_with_your_watch_card_container_dashboard_0", Integer.valueOf(R.layout.do_more_with_your_watch_card_container_dashboard));
            hashMap.put("layout/exclusive_card_boat_coins_0", Integer.valueOf(R.layout.exclusive_card_boat_coins));
            hashMap.put("layout/exclusive_card_content_header_info_image_textbutton_0", Integer.valueOf(R.layout.exclusive_card_content_header_info_image_textbutton));
            hashMap.put("layout/fitness_indusind_wellness_crew_0", Integer.valueOf(R.layout.fitness_indusind_wellness_crew));
            hashMap.put("layout/fitness_vital_card_container_dashboard_0", Integer.valueOf(R.layout.fitness_vital_card_container_dashboard));
            hashMap.put("layout/fitness_vitals_card_rectangle_0", Integer.valueOf(R.layout.fitness_vitals_card_rectangle));
            hashMap.put("layout/fitness_vitals_card_with_background_0", Integer.valueOf(R.layout.fitness_vitals_card_with_background));
            hashMap.put("layout/fragment_resend_otp_info_dialog_0", Integer.valueOf(R.layout.fragment_resend_otp_info_dialog));
            hashMap.put("layout/generic_dialog_two_buttons_0", Integer.valueOf(R.layout.generic_dialog_two_buttons));
            hashMap.put("layout/generic_dialog_two_buttons_new_0", Integer.valueOf(R.layout.generic_dialog_two_buttons_new));
            hashMap.put("layout/generic_dialog_with_two_options_0", Integer.valueOf(R.layout.generic_dialog_with_two_options));
            hashMap.put("layout/info_details_0", Integer.valueOf(R.layout.info_details));
            hashMap.put("layout/item_list_fitness_vitals_layout_0", Integer.valueOf(R.layout.item_list_fitness_vitals_layout));
            hashMap.put("layout/item_list_fitness_vitals_layout_full_0", Integer.valueOf(R.layout.item_list_fitness_vitals_layout_full));
            hashMap.put("layout/keep_going_layout_dashboard_0", Integer.valueOf(R.layout.keep_going_layout_dashboard));
            hashMap.put("layout/layout_cult_fit_ftu_card_0", Integer.valueOf(R.layout.layout_cult_fit_ftu_card));
            hashMap.put("layout/layout_dashboard_do_more_with_your_watch_grid_item_0", Integer.valueOf(R.layout.layout_dashboard_do_more_with_your_watch_grid_item));
            hashMap.put("layout/layout_dashboard_vital_grid_item_0", Integer.valueOf(R.layout.layout_dashboard_vital_grid_item));
            hashMap.put("layout/layout_my_buddies_ftu_card_0", Integer.valueOf(R.layout.layout_my_buddies_ftu_card));
            hashMap.put("layout/list_item_device_feature_my_watch_layout_0", Integer.valueOf(R.layout.list_item_device_feature_my_watch_layout));
            hashMap.put("layout/list_item_image_title_subtitle_0", Integer.valueOf(R.layout.list_item_image_title_subtitle));
            hashMap.put("layout/list_item_insights_layout_0", Integer.valueOf(R.layout.list_item_insights_layout));
            hashMap.put("layout/list_item_sleep_stages_layout_0", Integer.valueOf(R.layout.list_item_sleep_stages_layout));
            hashMap.put("layout/list_item_vital_tips_layout_0", Integer.valueOf(R.layout.list_item_vital_tips_layout));
            hashMap.put("layout/list_item_vitals_layout_0", Integer.valueOf(R.layout.list_item_vitals_layout));
            hashMap.put("layout/list_item_week_plan_layout_0", Integer.valueOf(R.layout.list_item_week_plan_layout));
            hashMap.put("layout/list_item_workout_videos_layout_0", Integer.valueOf(R.layout.list_item_workout_videos_layout));
            hashMap.put("layout/list_item_your_achievements_layout_0", Integer.valueOf(R.layout.list_item_your_achievements_layout));
            hashMap.put("layout/personalized_watch_face_dashboard_0", Integer.valueOf(R.layout.personalized_watch_face_dashboard));
            hashMap.put("layout/personalized_watch_face_my_watch_0", Integer.valueOf(R.layout.personalized_watch_face_my_watch));
            hashMap.put("layout/picker_dialog_0", Integer.valueOf(R.layout.picker_dialog));
            hashMap.put("layout/profile_completion_card_dashboard_0", Integer.valueOf(R.layout.profile_completion_card_dashboard));
            hashMap.put("layout/reminder_layout_new_0", Integer.valueOf(R.layout.reminder_layout_new));
            hashMap.put("layout/repeat_layout_new_0", Integer.valueOf(R.layout.repeat_layout_new));
            hashMap.put("layout/rounded_card_calendar_nav_layout_0", Integer.valueOf(R.layout.rounded_card_calendar_nav_layout));
            hashMap.put("layout/rounded_card_nav_layout_0", Integer.valueOf(R.layout.rounded_card_nav_layout));
            hashMap.put("layout/rounded_left_top_border_color_background_card_header_info_image_0", Integer.valueOf(R.layout.rounded_left_top_border_color_background_card_header_info_image));
            hashMap.put("layout/small_health_card_info_0", Integer.valueOf(R.layout.small_health_card_info));
            hashMap.put("layout/small_health_card_info_with_progress_0", Integer.valueOf(R.layout.small_health_card_info_with_progress));
            hashMap.put("layout/small_rounded_card_icon_header_status_0", Integer.valueOf(R.layout.small_rounded_card_icon_header_status));
            hashMap.put("layout/stay_tunned_content_header_info_image_textbutton_0", Integer.valueOf(R.layout.stay_tunned_content_header_info_image_textbutton));
            hashMap.put("layout/toolbar_generic_dashboard_0", Integer.valueOf(R.layout.toolbar_generic_dashboard));
            hashMap.put("layout/tooltip_dialog_two_buttons_0", Integer.valueOf(R.layout.tooltip_dialog_two_buttons));
            hashMap.put("layout/vitals_detailed_layout_0", Integer.valueOf(R.layout.vitals_detailed_layout));
            hashMap.put("layout/watch_face_studio_big_card_dashboard_0", Integer.valueOf(R.layout.watch_face_studio_big_card_dashboard));
            hashMap.put("layout/watch_settings_big_card_dashboard_0", Integer.valueOf(R.layout.watch_settings_big_card_dashboard));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(57);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_stride_length_animation, 1);
        sparseIntArray.put(R.layout.arc_circular_progress_bar, 2);
        sparseIntArray.put(R.layout.bestoffers_containers, 3);
        sparseIntArray.put(R.layout.common_error_message_dialog, 4);
        sparseIntArray.put(R.layout.common_message_dialog, 5);
        sparseIntArray.put(R.layout.common_message_dialog_without_drawable, 6);
        sparseIntArray.put(R.layout.connected_device_info_card_dashboard, 7);
        sparseIntArray.put(R.layout.dashboard_dynamic_web_layout, 8);
        sparseIntArray.put(R.layout.dashboard_fitness_card_layout, 9);
        sparseIntArray.put(R.layout.device_not_paired, 10);
        sparseIntArray.put(R.layout.dialog_calendar_range_date, 11);
        sparseIntArray.put(R.layout.do_more_with_your_watch_card_container_dashboard, 12);
        sparseIntArray.put(R.layout.exclusive_card_boat_coins, 13);
        sparseIntArray.put(R.layout.exclusive_card_content_header_info_image_textbutton, 14);
        sparseIntArray.put(R.layout.fitness_indusind_wellness_crew, 15);
        sparseIntArray.put(R.layout.fitness_vital_card_container_dashboard, 16);
        sparseIntArray.put(R.layout.fitness_vitals_card_rectangle, 17);
        sparseIntArray.put(R.layout.fitness_vitals_card_with_background, 18);
        sparseIntArray.put(R.layout.fragment_resend_otp_info_dialog, 19);
        sparseIntArray.put(R.layout.generic_dialog_two_buttons, 20);
        sparseIntArray.put(R.layout.generic_dialog_two_buttons_new, 21);
        sparseIntArray.put(R.layout.generic_dialog_with_two_options, 22);
        sparseIntArray.put(R.layout.info_details, 23);
        sparseIntArray.put(R.layout.item_list_fitness_vitals_layout, 24);
        sparseIntArray.put(R.layout.item_list_fitness_vitals_layout_full, 25);
        sparseIntArray.put(R.layout.keep_going_layout_dashboard, 26);
        sparseIntArray.put(R.layout.layout_cult_fit_ftu_card, 27);
        sparseIntArray.put(R.layout.layout_dashboard_do_more_with_your_watch_grid_item, 28);
        sparseIntArray.put(R.layout.layout_dashboard_vital_grid_item, 29);
        sparseIntArray.put(R.layout.layout_my_buddies_ftu_card, 30);
        sparseIntArray.put(R.layout.list_item_device_feature_my_watch_layout, 31);
        sparseIntArray.put(R.layout.list_item_image_title_subtitle, 32);
        sparseIntArray.put(R.layout.list_item_insights_layout, 33);
        sparseIntArray.put(R.layout.list_item_sleep_stages_layout, 34);
        sparseIntArray.put(R.layout.list_item_vital_tips_layout, 35);
        sparseIntArray.put(R.layout.list_item_vitals_layout, 36);
        sparseIntArray.put(R.layout.list_item_week_plan_layout, 37);
        sparseIntArray.put(R.layout.list_item_workout_videos_layout, 38);
        sparseIntArray.put(R.layout.list_item_your_achievements_layout, 39);
        sparseIntArray.put(R.layout.personalized_watch_face_dashboard, 40);
        sparseIntArray.put(R.layout.personalized_watch_face_my_watch, 41);
        sparseIntArray.put(R.layout.picker_dialog, 42);
        sparseIntArray.put(R.layout.profile_completion_card_dashboard, 43);
        sparseIntArray.put(R.layout.reminder_layout_new, 44);
        sparseIntArray.put(R.layout.repeat_layout_new, 45);
        sparseIntArray.put(R.layout.rounded_card_calendar_nav_layout, 46);
        sparseIntArray.put(R.layout.rounded_card_nav_layout, 47);
        sparseIntArray.put(R.layout.rounded_left_top_border_color_background_card_header_info_image, 48);
        sparseIntArray.put(R.layout.small_health_card_info, 49);
        sparseIntArray.put(R.layout.small_health_card_info_with_progress, 50);
        sparseIntArray.put(R.layout.small_rounded_card_icon_header_status, 51);
        sparseIntArray.put(R.layout.stay_tunned_content_header_info_image_textbutton, 52);
        sparseIntArray.put(R.layout.toolbar_generic_dashboard, 53);
        sparseIntArray.put(R.layout.tooltip_dialog_two_buttons, 54);
        sparseIntArray.put(R.layout.vitals_detailed_layout, 55);
        sparseIntArray.put(R.layout.watch_face_studio_big_card_dashboard, 56);
        sparseIntArray.put(R.layout.watch_settings_big_card_dashboard, 57);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/activity_stride_length_animation_0".equals(obj)) {
                    return new ActivityStrideLengthAnimationBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_stride_length_animation is invalid. Received: " + obj);
            case 2:
                if ("layout/arc_circular_progress_bar_0".equals(obj)) {
                    return new ArcCircularProgressBarBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for arc_circular_progress_bar is invalid. Received: " + obj);
            case 3:
                if ("layout/bestoffers_containers_0".equals(obj)) {
                    return new BestoffersContainersBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for bestoffers_containers is invalid. Received: " + obj);
            case 4:
                if ("layout/common_error_message_dialog_0".equals(obj)) {
                    return new CommonErrorMessageDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for common_error_message_dialog is invalid. Received: " + obj);
            case 5:
                if ("layout/common_message_dialog_0".equals(obj)) {
                    return new CommonMessageDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for common_message_dialog is invalid. Received: " + obj);
            case 6:
                if ("layout/common_message_dialog_without_drawable_0".equals(obj)) {
                    return new CommonMessageDialogWithoutDrawableBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for common_message_dialog_without_drawable is invalid. Received: " + obj);
            case 7:
                if ("layout/connected_device_info_card_dashboard_0".equals(obj)) {
                    return new ConnectedDeviceInfoCardDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for connected_device_info_card_dashboard is invalid. Received: " + obj);
            case 8:
                if ("layout/dashboard_dynamic_web_layout_0".equals(obj)) {
                    return new DashboardDynamicWebLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dashboard_dynamic_web_layout is invalid. Received: " + obj);
            case 9:
                if ("layout/dashboard_fitness_card_layout_0".equals(obj)) {
                    return new DashboardFitnessCardLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dashboard_fitness_card_layout is invalid. Received: " + obj);
            case 10:
                if ("layout/device_not_paired_0".equals(obj)) {
                    return new DeviceNotPairedBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for device_not_paired is invalid. Received: " + obj);
            case 11:
                if ("layout/dialog_calendar_range_date_0".equals(obj)) {
                    return new DialogCalendarRangeDateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_calendar_range_date is invalid. Received: " + obj);
            case 12:
                if ("layout/do_more_with_your_watch_card_container_dashboard_0".equals(obj)) {
                    return new DoMoreWithYourWatchCardContainerDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for do_more_with_your_watch_card_container_dashboard is invalid. Received: " + obj);
            case 13:
                if ("layout/exclusive_card_boat_coins_0".equals(obj)) {
                    return new ExclusiveCardBoatCoinsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for exclusive_card_boat_coins is invalid. Received: " + obj);
            case 14:
                if ("layout/exclusive_card_content_header_info_image_textbutton_0".equals(obj)) {
                    return new ExclusiveCardContentHeaderInfoImageTextbuttonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for exclusive_card_content_header_info_image_textbutton is invalid. Received: " + obj);
            case 15:
                if ("layout/fitness_indusind_wellness_crew_0".equals(obj)) {
                    return new FitnessIndusindWellnessCrewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_indusind_wellness_crew is invalid. Received: " + obj);
            case 16:
                if ("layout/fitness_vital_card_container_dashboard_0".equals(obj)) {
                    return new FitnessVitalCardContainerDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_vital_card_container_dashboard is invalid. Received: " + obj);
            case 17:
                if ("layout/fitness_vitals_card_rectangle_0".equals(obj)) {
                    return new FitnessVitalsCardRectangleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_vitals_card_rectangle is invalid. Received: " + obj);
            case 18:
                if ("layout/fitness_vitals_card_with_background_0".equals(obj)) {
                    return new FitnessVitalsCardWithBackgroundBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fitness_vitals_card_with_background is invalid. Received: " + obj);
            case 19:
                if ("layout/fragment_resend_otp_info_dialog_0".equals(obj)) {
                    return new FragmentResendOtpInfoDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for fragment_resend_otp_info_dialog is invalid. Received: " + obj);
            case 20:
                if ("layout/generic_dialog_two_buttons_0".equals(obj)) {
                    return new GenericDialogTwoButtonsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for generic_dialog_two_buttons is invalid. Received: " + obj);
            case 21:
                if ("layout/generic_dialog_two_buttons_new_0".equals(obj)) {
                    return new GenericDialogTwoButtonsNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for generic_dialog_two_buttons_new is invalid. Received: " + obj);
            case 22:
                if ("layout/generic_dialog_with_two_options_0".equals(obj)) {
                    return new GenericDialogWithTwoOptionsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for generic_dialog_with_two_options is invalid. Received: " + obj);
            case 23:
                if ("layout/info_details_0".equals(obj)) {
                    return new InfoDetailsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for info_details is invalid. Received: " + obj);
            case 24:
                if ("layout/item_list_fitness_vitals_layout_0".equals(obj)) {
                    return new ItemListFitnessVitalsLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_list_fitness_vitals_layout is invalid. Received: " + obj);
            case 25:
                if ("layout/item_list_fitness_vitals_layout_full_0".equals(obj)) {
                    return new ItemListFitnessVitalsLayoutFullBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_list_fitness_vitals_layout_full is invalid. Received: " + obj);
            case 26:
                if ("layout/keep_going_layout_dashboard_0".equals(obj)) {
                    return new KeepGoingLayoutDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for keep_going_layout_dashboard is invalid. Received: " + obj);
            case 27:
                if ("layout/layout_cult_fit_ftu_card_0".equals(obj)) {
                    return new LayoutCultFitFtuCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_cult_fit_ftu_card is invalid. Received: " + obj);
            case 28:
                if ("layout/layout_dashboard_do_more_with_your_watch_grid_item_0".equals(obj)) {
                    return new LayoutDashboardDoMoreWithYourWatchGridItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_dashboard_do_more_with_your_watch_grid_item is invalid. Received: " + obj);
            case 29:
                if ("layout/layout_dashboard_vital_grid_item_0".equals(obj)) {
                    return new LayoutDashboardVitalGridItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_dashboard_vital_grid_item is invalid. Received: " + obj);
            case 30:
                if ("layout/layout_my_buddies_ftu_card_0".equals(obj)) {
                    return new LayoutMyBuddiesFtuCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for layout_my_buddies_ftu_card is invalid. Received: " + obj);
            case 31:
                if ("layout/list_item_device_feature_my_watch_layout_0".equals(obj)) {
                    return new ListItemDeviceFeatureMyWatchLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_device_feature_my_watch_layout is invalid. Received: " + obj);
            case 32:
                if ("layout/list_item_image_title_subtitle_0".equals(obj)) {
                    return new ListItemImageTitleSubtitleBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_image_title_subtitle is invalid. Received: " + obj);
            case 33:
                if ("layout/list_item_insights_layout_0".equals(obj)) {
                    return new ListItemInsightsLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_insights_layout is invalid. Received: " + obj);
            case 34:
                if ("layout/list_item_sleep_stages_layout_0".equals(obj)) {
                    return new ListItemSleepStagesLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_sleep_stages_layout is invalid. Received: " + obj);
            case 35:
                if ("layout/list_item_vital_tips_layout_0".equals(obj)) {
                    return new ListItemVitalTipsLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_vital_tips_layout is invalid. Received: " + obj);
            case 36:
                if ("layout/list_item_vitals_layout_0".equals(obj)) {
                    return new ListItemVitalsLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_vitals_layout is invalid. Received: " + obj);
            case 37:
                if ("layout/list_item_week_plan_layout_0".equals(obj)) {
                    return new ListItemWeekPlanLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_week_plan_layout is invalid. Received: " + obj);
            case 38:
                if ("layout/list_item_workout_videos_layout_0".equals(obj)) {
                    return new ListItemWorkoutVideosLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_workout_videos_layout is invalid. Received: " + obj);
            case 39:
                if ("layout/list_item_your_achievements_layout_0".equals(obj)) {
                    return new ListItemYourAchievementsLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for list_item_your_achievements_layout is invalid. Received: " + obj);
            case 40:
                if ("layout/personalized_watch_face_dashboard_0".equals(obj)) {
                    return new PersonalizedWatchFaceDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for personalized_watch_face_dashboard is invalid. Received: " + obj);
            case 41:
                if ("layout/personalized_watch_face_my_watch_0".equals(obj)) {
                    return new PersonalizedWatchFaceMyWatchBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for personalized_watch_face_my_watch is invalid. Received: " + obj);
            case 42:
                if ("layout/picker_dialog_0".equals(obj)) {
                    return new PickerDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for picker_dialog is invalid. Received: " + obj);
            case 43:
                if ("layout/profile_completion_card_dashboard_0".equals(obj)) {
                    return new ProfileCompletionCardDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for profile_completion_card_dashboard is invalid. Received: " + obj);
            case 44:
                if ("layout/reminder_layout_new_0".equals(obj)) {
                    return new ReminderLayoutNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for reminder_layout_new is invalid. Received: " + obj);
            case 45:
                if ("layout/repeat_layout_new_0".equals(obj)) {
                    return new RepeatLayoutNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for repeat_layout_new is invalid. Received: " + obj);
            case 46:
                if ("layout/rounded_card_calendar_nav_layout_0".equals(obj)) {
                    return new RoundedCardCalendarNavLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for rounded_card_calendar_nav_layout is invalid. Received: " + obj);
            case 47:
                if ("layout/rounded_card_nav_layout_0".equals(obj)) {
                    return new RoundedCardNavLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for rounded_card_nav_layout is invalid. Received: " + obj);
            case 48:
                if ("layout/rounded_left_top_border_color_background_card_header_info_image_0".equals(obj)) {
                    return new RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for rounded_left_top_border_color_background_card_header_info_image is invalid. Received: " + obj);
            case 49:
                if ("layout/small_health_card_info_0".equals(obj)) {
                    return new SmallHealthCardInfoBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for small_health_card_info is invalid. Received: " + obj);
            case 50:
                if ("layout/small_health_card_info_with_progress_0".equals(obj)) {
                    return new SmallHealthCardInfoWithProgressBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for small_health_card_info_with_progress is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/small_rounded_card_icon_header_status_0".equals(obj)) {
                    return new SmallRoundedCardIconHeaderStatusBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for small_rounded_card_icon_header_status is invalid. Received: " + obj);
            case 52:
                if ("layout/stay_tunned_content_header_info_image_textbutton_0".equals(obj)) {
                    return new StayTunnedContentHeaderInfoImageTextbuttonBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for stay_tunned_content_header_info_image_textbutton is invalid. Received: " + obj);
            case 53:
                if ("layout/toolbar_generic_dashboard_0".equals(obj)) {
                    return new ToolbarGenericDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for toolbar_generic_dashboard is invalid. Received: " + obj);
            case 54:
                if ("layout/tooltip_dialog_two_buttons_0".equals(obj)) {
                    return new TooltipDialogTwoButtonsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for tooltip_dialog_two_buttons is invalid. Received: " + obj);
            case 55:
                if ("layout/vitals_detailed_layout_0".equals(obj)) {
                    return new VitalsDetailedLayoutBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for vitals_detailed_layout is invalid. Received: " + obj);
            case 56:
                if ("layout/watch_face_studio_big_card_dashboard_0".equals(obj)) {
                    return new WatchFaceStudioBigCardDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_face_studio_big_card_dashboard is invalid. Received: " + obj);
            case 57:
                if ("layout/watch_settings_big_card_dashboard_0".equals(obj)) {
                    return new WatchSettingsBigCardDashboardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for watch_settings_big_card_dashboard is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return a.f6083a.get(i);
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
                        return null;
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
        if (str == null || (num = b.f6084a.get(str)) == null) {
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
