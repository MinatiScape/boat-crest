package com.coveiot.android.leonardo.utils;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.customreminders.viewmodel.AddCustomReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.HandWashReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.MedicineReminderViewModel;
import com.coveiot.android.customreminders.viewmodel.RemindersListViewModel;
import com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel;
import com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel;
import com.coveiot.android.leonardo.dashboard.feedback.viewmodel.FeedbackQuestionerViewModel;
import com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleDeviceScanViewModel;
import com.coveiot.android.leonardo.dashboard.health.spo2.viewmodel.FragmentSPO2BleMeasurementViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.ActivityShareScreenViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundHistoryViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentAmbientSoundViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHRVPeriodicViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentHeartRateViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentPeriodicSpo2ViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSpo2ViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressPeriodicViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressViewModelNew;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentTemperatureViewModel;
import com.coveiot.android.leonardo.dashboard.socialshare.viewmodel.DashboardSocialShareViewModel;
import com.coveiot.android.leonardo.more.ActivityBandDisplayViewModel;
import com.coveiot.android.leonardo.more.ActivityBandSettingsViewModel;
import com.coveiot.android.leonardo.more.ActivityNotificationViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityAmbientSoundSettingsViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityAutoRecognitionViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityCalorieGoalViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityDistanceGoalViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityExerciseMinutesGoalViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityShortcutViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivitySleepGoalViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivitySportsTypeViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ActivityWalkHourGoalViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AddQRCodeToWatchViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AlarmViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AutoSPO2SettingsViewModel;
import com.coveiot.android.leonardo.more.viewmodel.AutoStressSettingsViewModel;
import com.coveiot.android.leonardo.more.viewmodel.DiagnosticsTestViewModel;
import com.coveiot.android.leonardo.more.viewmodel.DoNotDisturbViewModel;
import com.coveiot.android.leonardo.more.viewmodel.DrinkWaterReminderViewModel;
import com.coveiot.android.leonardo.more.viewmodel.LiftWristToViewModel;
import com.coveiot.android.leonardo.more.viewmodel.MyAccountViewModel;
import com.coveiot.android.leonardo.more.viewmodel.MyWatchViewModel;
import com.coveiot.android.leonardo.more.viewmodel.ScheduleViewModel;
import com.coveiot.android.leonardo.more.viewmodel.SedentaryReminderViewModel;
import com.coveiot.android.leonardo.more.viewmodel.WomenWellnessViewModel;
import com.coveiot.android.leonardo.more.viewmodel.WorldClockViewModel;
import com.coveiot.android.leonardo.onboarding.goal.viewmodel.FragmentStepsGoalViewModel;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceListingViewModelCompanionDM;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentDeviceSelectionViewModel;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentQRScanDeviceViewModel;
import com.coveiot.android.leonardo.onboarding.paring.viewmodel.FragmentScanningDeviceViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityCountrySelectionViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityPhoneValidationViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.ActivityReportIssueViewModel;
import com.coveiot.android.leonardo.onboarding.phonevalidation.viewmodel.FragmentEnterPhoneNumberViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentBasicProfileInfoViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentFinalProfileViewModel;
import com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentGenderViewModel;
import com.coveiot.android.leonardo.onboarding.splash.viewmodel.ActivitySplashViewModel;
import com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel;
import com.coveiot.android.leonardo.quickreply.viewmodel.QuickReplySettingsViewModel;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDataViewModel;
import com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterHistoryViewModel;
import com.coveiot.android.sleepenergyscore.energymeter.viewmodel.FragmentEnergyMeterViewModel;
import com.coveiot.android.sleepenergyscore.feedback.SleepEnergyScoreFeedbackViewModel;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ViewModelFactory implements ViewModelProvider.Factory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5438a;

    public ViewModelFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5438a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NotNull
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(ActivityCountrySelectionViewModel.class)) {
            return new ActivityCountrySelectionViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentEnterPhoneNumberViewModel.class)) {
            return new FragmentEnterPhoneNumberViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivitySplashViewModel.class)) {
            return new ActivitySplashViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityPhoneValidationViewModel.class)) {
            return new ActivityPhoneValidationViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentBasicProfileInfoViewModel.class)) {
            return new FragmentBasicProfileInfoViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentFinalProfileViewModel.class)) {
            return new FragmentFinalProfileViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentGenderViewModel.class)) {
            return new FragmentGenderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentDeviceListingViewModelCompanionDM.class)) {
            return new FragmentDeviceListingViewModelCompanionDM(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentStepsGoalViewModel.class)) {
            return new FragmentStepsGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentSleepViewModel.class)) {
            return new FragmentSleepViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentSleepHistoryViewModel.class)) {
            return new FragmentSleepHistoryViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentDeviceSelectionViewModel.class)) {
            return new FragmentDeviceSelectionViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentStepsViewModel.class)) {
            return new FragmentStepsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityShareScreenViewModel.class)) {
            return new ActivityShareScreenViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentHeartRateViewModel.class)) {
            return new FragmentHeartRateViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivitySleepGoalViewModel.class)) {
            return new ActivitySleepGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityBandSettingsViewModel.class)) {
            return new ActivityBandSettingsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityBandDisplayViewModel.class)) {
            return new ActivityBandDisplayViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityNotificationViewModel.class)) {
            return new ActivityNotificationViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(MyAccountViewModel.class)) {
            return new MyAccountViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityPairingViewModel.class)) {
            return new ActivityPairingViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityReportIssueViewModel.class)) {
            return new ActivityReportIssueViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentTemperatureViewModel.class)) {
            return new FragmentTemperatureViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(SedentaryReminderViewModel.class)) {
            return new SedentaryReminderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(DoNotDisturbViewModel.class)) {
            return new DoNotDisturbViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AlarmViewModel.class)) {
            return new AlarmViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentSpo2ViewModel.class)) {
            return new FragmentSpo2ViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentSPO2BleDeviceScanViewModel.class)) {
            return new FragmentSPO2BleDeviceScanViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentSPO2BleMeasurementViewModel.class)) {
            return new FragmentSPO2BleMeasurementViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentScanningDeviceViewModel.class)) {
            return new FragmentScanningDeviceViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentStressViewModelNew.class)) {
            return new FragmentStressViewModelNew(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ScheduleViewModel.class)) {
            return new ScheduleViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(WomenWellnessViewModel.class)) {
            return new WomenWellnessViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentStressPeriodicViewModel.class)) {
            return new FragmentStressPeriodicViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(DrinkWaterReminderViewModel.class)) {
            return new DrinkWaterReminderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentPeriodicSpo2ViewModel.class)) {
            return new FragmentPeriodicSpo2ViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(LiftWristToViewModel.class)) {
            return new LiftWristToViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(QuickReplySettingsViewModel.class)) {
            return new QuickReplySettingsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ManageQuickReplyViewModel.class)) {
            return new ManageQuickReplyViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentEnergyMeterViewModel.class)) {
            return new FragmentEnergyMeterViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(SleepEnergyScoreFeedbackViewModel.class)) {
            return new SleepEnergyScoreFeedbackViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentEnergyMeterHistoryViewModel.class)) {
            return new FragmentEnergyMeterHistoryViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivitySportsTypeViewModel.class)) {
            return new ActivitySportsTypeViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityShortcutViewModel.class)) {
            return new ActivityShortcutViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityAutoRecognitionViewModel.class)) {
            return new ActivityAutoRecognitionViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(BT3CallViewModel.class)) {
            return new BT3CallViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AutoStressSettingsViewModel.class)) {
            return new AutoStressSettingsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentHRVPeriodicViewModel.class)) {
            return new FragmentHRVPeriodicViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FeedbackQuestionerViewModel.class)) {
            return new FeedbackQuestionerViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FeedbackQuestionerViewModel.class)) {
            return new FeedbackQuestionerViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(WorldClockViewModel.class)) {
            return new WorldClockViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentAmbientSoundViewModel.class)) {
            return new FragmentAmbientSoundViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentAmbientSoundHistoryViewModel.class)) {
            return new FragmentAmbientSoundHistoryViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityAmbientSoundSettingsViewModel.class)) {
            return new ActivityAmbientSoundSettingsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityExerciseMinutesGoalViewModel.class)) {
            return new ActivityExerciseMinutesGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityCalorieGoalViewModel.class)) {
            return new ActivityCalorieGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityWalkHourGoalViewModel.class)) {
            return new ActivityWalkHourGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(MedicineReminderViewModel.class)) {
            return new MedicineReminderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AddCustomReminderViewModel.class)) {
            return new AddCustomReminderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(HandWashReminderViewModel.class)) {
            return new HandWashReminderViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(RemindersListViewModel.class)) {
            return new RemindersListViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(DashboardSocialShareViewModel.class)) {
            return new DashboardSocialShareViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AutoSPO2SettingsViewModel.class)) {
            return new AutoSPO2SettingsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(BoatCoinsContactsViewModel.class)) {
            return new BoatCoinsContactsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(SensAISummaryDataViewModel.class)) {
            return new SensAISummaryDataViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(SensAISummaryDetailsViewModel.class)) {
            return new SensAISummaryDetailsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(OverallStatsViewModel.class)) {
            return new OverallStatsViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(DiagnosticsTestViewModel.class)) {
            return new DiagnosticsTestViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityDistanceGoalViewModel.class)) {
            return new ActivityDistanceGoalViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityProfileLandingViewModel.class)) {
            return new ActivityProfileLandingViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(MyWatchViewModel.class)) {
            return new MyWatchViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AdditionalActivitiesViewModel.class)) {
            return new AdditionalActivitiesViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(AddQRCodeToWatchViewModel.class)) {
            return new AddQRCodeToWatchViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(ActivityCustomise4hButtonViewModel.class)) {
            return new ActivityCustomise4hButtonViewModel(this.f5438a);
        }
        if (modelClass.isAssignableFrom(FragmentQRScanDeviceViewModel.class)) {
            return new FragmentQRScanDeviceViewModel(this.f5438a);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NotNull
    public final Context getContext() {
        return this.f5438a;
    }
}
