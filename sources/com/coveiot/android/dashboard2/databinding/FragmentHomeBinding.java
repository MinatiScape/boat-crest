package com.coveiot.android.dashboard2.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.coveiot.android.activitymodes.databinding.ActivityHistoryBeanLayoutBinding;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.customui.PagerContainer;
import com.coveiot.android.dashboard2.model.state.SyncingStateData;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.databinding.BestoffersContainersBinding;
import com.coveiot.android.theme.databinding.ConnectedDeviceInfoCardDashboardBinding;
import com.coveiot.android.theme.databinding.DashboardDynamicWebLayoutBinding;
import com.coveiot.android.theme.databinding.DashboardFitnessCardLayoutBinding;
import com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardBoatCoinsBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBinding;
import com.coveiot.android.theme.databinding.FitnessIndusindWellnessCrewBinding;
import com.coveiot.android.theme.databinding.FitnessVitalCardContainerDashboardBinding;
import com.coveiot.android.theme.databinding.FitnessVitalsCardWithBackgroundBinding;
import com.coveiot.android.theme.databinding.KeepGoingLayoutDashboardBinding;
import com.coveiot.android.theme.databinding.LayoutCultFitFtuCardBinding;
import com.coveiot.android.theme.databinding.LayoutMyBuddiesFtuCardBinding;
import com.coveiot.android.theme.databinding.ListItemWeekPlanLayoutBinding;
import com.coveiot.android.theme.databinding.PersonalizedWatchFaceDashboardBinding;
import com.coveiot.android.theme.databinding.ProfileCompletionCardDashboardBinding;
import com.coveiot.android.theme.databinding.RoundedCardCalendarNavLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
import com.coveiot.android.theme.databinding.RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoBinding;
import com.coveiot.android.theme.databinding.SmallHealthCardInfoWithProgressBinding;
import com.coveiot.android.theme.databinding.SmallRoundedCardIconHeaderStatusBinding;
import com.coveiot.android.theme.databinding.ToolbarGenericDashboardBinding;
import com.coveiot.android.theme.databinding.WatchFaceStudioBigCardDashboardBinding;
import com.coveiot.android.theme.databinding.WatchSettingsBigCardDashboardBinding;
import com.coveiot.android.theme.model.BindingDataModel1;
import com.coveiot.android.theme.model.SmallHealthCardInfo;
import com.coveiot.covepreferences.data.StepsDataModel;
/* loaded from: classes4.dex */
public abstract class FragmentHomeBinding extends ViewDataBinding {
    @NonNull
    public final ExclusiveCardContentHeaderInfoImageTextbuttonBinding activities700plus;
    @NonNull
    public final ActivityHistoryBeanLayoutBinding activtyHistory;
    @NonNull
    public final ExclusiveCardBoatCoinsBinding boatCoinsLayout;
    @NonNull
    public final ConstraintLayout buddiesLayout;
    @NonNull
    public final RoundedCardCalendarNavLayoutBinding calSelectionView;
    @NonNull
    public final ConstraintLayout challengeBannerSectionLayout;
    @NonNull
    public final ConstraintLayout challengeHeaderSectionTv;
    @NonNull
    public final LinearLayout challengeLinearLayoutDots;
    @NonNull
    public final ConstraintLayout clActivityHistory;
    @NonNull
    public final LinearLayout clFitnessJourneyMain;
    @NonNull
    public final ConstraintLayout clFitnessVitalsHeader;
    @NonNull
    public final ConstraintLayout clOtherFitnessData;
    @NonNull
    public final ConstraintLayout clTopFeature;
    @NonNull
    public final LayoutCultFitFtuCardBinding cultFitFtu;
    @NonNull
    public final ConstraintLayout cultFitLayout;
    @NonNull
    public final DoMoreWithYourWatchCardContainerDashboardBinding doMoreWithYourWatchCardContainer;
    @NonNull
    public final RecyclerView dynamicTabRecy;
    @NonNull
    public final DashboardDynamicWebLayoutBinding dynamicWebView;
    @NonNull
    public final DashboardDynamicWebLayoutBinding dynamicWebView1;
    @NonNull
    public final SmallHealthCardInfoBinding fitnessCalorie;
    @NonNull
    public final SmallHealthCardInfoBinding fitnessDistance;
    @NonNull
    public final RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding fitnessJourney;
    @NonNull
    public final ListItemWeekPlanLayoutBinding fitnessJourneyOngoing;
    @NonNull
    public final DashboardFitnessCardLayoutBinding fitnessOverviewCl;
    @NonNull
    public final SmallHealthCardInfoWithProgressBinding fitnessSleep;
    @NonNull
    public final FrameLayout fragmentContainerCultFit;
    @NonNull
    public final FitnessVitalCardContainerDashboardBinding healthVitalsCardContainer;
    @NonNull
    public final ImageView imageViewDot1;
    @NonNull
    public final ImageView imageViewDot2;
    @NonNull
    public final ImageView imageViewDot3;
    @NonNull
    public final FitnessIndusindWellnessCrewBinding indusIndWellnessCrew;
    @NonNull
    public final ImageView ivPoweredBy;
    @NonNull
    public final SmallRoundedCardIconHeaderStatusBinding lBleState;
    @NonNull
    public final SmallRoundedCardIconHeaderStatusBinding lBt3State;
    @NonNull
    public final LinearLayout linearLayoutDots;
    @NonNull
    public final LinearLayout llOtherFitnessData;
    @Bindable
    public Integer mBestOffers;
    @Bindable
    public BindingDataModel1 mBleConnectionState;
    @Bindable
    public BindingDataModel1 mBt3ConnectionState;
    @Bindable
    public SmallHealthCardInfo mCalorieData;
    @Bindable
    public SmallHealthCardInfo mDistanceData;
    @Bindable
    public Integer mDoMoreWithYourWatchItemCount;
    @Bindable
    public Boolean mIsFitnessPlanOngoing;
    @Bindable
    public Boolean mIsSetupYourWatchSettingsCompleted;
    @Bindable
    public Boolean mIsUserProfileCompleted;
    @Bindable
    public Integer mSelectedVitalsCount;
    @Bindable
    public Integer mSetupYourWatchItemCount;
    @Bindable
    public Integer mSetupYourWatchItemSelectedPosition;
    @Bindable
    public Boolean mShow1kFtu;
    @Bindable
    public Boolean mShowActivityHistory;
    @Bindable
    public Boolean mShowBoatCoins;
    @Bindable
    public Boolean mShowCultFitFTU;
    @Bindable
    public Boolean mShowDynamicTab;
    @Bindable
    public Boolean mShowFitnessChallenge;
    @Bindable
    public Boolean mShowMyBuddies;
    @Bindable
    public Boolean mShowMyBuddiesList;
    @Bindable
    public Boolean mShowPersonalizedWatchFace;
    @Bindable
    public Boolean mShowWatchSettingsBigLayout;
    @Bindable
    public Boolean mShowWatchfaceBigLayout;
    @Bindable
    public Boolean mShowWatchfaceStudioBigLayout;
    @Bindable
    public Boolean mShowWatchfaceStudioBigLayoutTop;
    @Bindable
    public SmallHealthCardInfo mSleepData;
    @Bindable
    public StepsDataModel mStepsDataModel;
    @Bindable
    public SyncingStateData mSyncingStateData;
    @NonNull
    public final LayoutMyBuddiesFtuCardBinding myBuddiesFtu;
    @NonNull
    public final ImageView navigationBanner;
    @NonNull
    public final NoChallengesBannerBinding noChallengeView;
    @NonNull
    public final PersonalizedWatchFaceDashboardBinding personalizedWatchfaceContainer;
    @NonNull
    public final View placeHolderView1;
    @NonNull
    public final ProfileCompletionCardDashboardBinding profileLayout;
    @NonNull
    public final KeepGoingLayoutDashboardBinding progressMotivationalMessage;
    @NonNull
    public final RecyclerView rcvBuddiesList;
    @NonNull
    public final RecyclerView rvFitnessChallenge;
    @NonNull
    public final RecyclerView rvTopFeatures;
    @NonNull
    public final ConstraintLayout section1;
    @NonNull
    public final ConstraintLayout section10;
    @NonNull
    public final ConstraintLayout section11;
    @NonNull
    public final ConstraintLayout section12;
    @NonNull
    public final ConstraintLayout section13;
    @NonNull
    public final ConstraintLayout section14;
    @NonNull
    public final ConstraintLayout section15;
    @NonNull
    public final ConstraintLayout section16;
    @NonNull
    public final ConstraintLayout section17;
    @NonNull
    public final ConstraintLayout section18;
    @NonNull
    public final ConstraintLayout section2;
    @NonNull
    public final ConstraintLayout section3;
    @NonNull
    public final ConstraintLayout section4;
    @NonNull
    public final ConstraintLayout section5;
    @NonNull
    public final ConstraintLayout section61;
    @NonNull
    public final ConstraintLayout section62;
    @NonNull
    public final ConstraintLayout section7;
    @NonNull
    public final ConstraintLayout section8;
    @NonNull
    public final ConstraintLayout section9;
    @NonNull
    public final ConstraintLayout sectionContainer;
    @NonNull
    public final ConstraintLayout sectionDynamicTab;
    @NonNull
    public final PagerContainer setYourWatchPager;
    @NonNull
    public final SwipeRefreshLayout swipeContainer;
    @NonNull
    public final ConnectedDeviceInfoCardDashboardBinding syncStatusLayout;
    @NonNull
    public final ToolbarGenericDashboardBinding toolbar;
    @NonNull
    public final FitnessVitalsCardWithBackgroundBinding trackMoreVitals;
    @NonNull
    public final TextView tvActivityHistoryHeader;
    @NonNull
    public final TextView tvBestOffers;
    @NonNull
    public final BestoffersContainersBinding tvBestOffersCardContainer;
    @NonNull
    public final TextView tvCultFitHeader;
    @NonNull
    public final TextView tvDoMoreWithYourWatch;
    @NonNull
    public final TextView tvEditVitalCards;
    @NonNull
    public final TextView tvFitnessBuddiesHeader;
    @NonNull
    public final TextView tvFitnessChallengeHeader;
    @NonNull
    public final TextView tvFitnessChallengeViewMore;
    @NonNull
    public final TextView tvFitnessJourneyHeader;
    @NonNull
    public final TextView tvFitnessJourneyViewMore;
    @NonNull
    public final TextView tvFitnessOverviewHeader;
    @NonNull
    public final TextView tvFitnessVitals;
    @NonNull
    public final TextView tvSetupYourWatchHeader;
    @NonNull
    public final TextView tvTopFeaturesTitle;
    @NonNull
    public final TextView tvboatExclusiveHeader;
    @NonNull
    public final View v;
    @NonNull
    public final RoundedCardNavLayoutBinding viewActivityHistoryHeader;
    @NonNull
    public final RoundedCardNavLayoutBinding viewFitnessChallengeDashboardHeader;
    @NonNull
    public final RoundedCardNavLayoutBinding viewFitnessDashboardHeader;
    @NonNull
    public final TextView viewMoreBuddies;
    @NonNull
    public final TextView viewMoreCultFit;
    @NonNull
    public final WatchSettingsBigCardDashboardBinding watchSettingsBig;
    @NonNull
    public final WatchFaceStudioBigCardDashboardBinding watchfaceStudioBig;
    @NonNull
    public final WatchFaceStudioBigCardDashboardBinding watchfaceStudioBigTop;

    public FragmentHomeBinding(Object obj, View view, int i, ExclusiveCardContentHeaderInfoImageTextbuttonBinding exclusiveCardContentHeaderInfoImageTextbuttonBinding, ActivityHistoryBeanLayoutBinding activityHistoryBeanLayoutBinding, ExclusiveCardBoatCoinsBinding exclusiveCardBoatCoinsBinding, ConstraintLayout constraintLayout, RoundedCardCalendarNavLayoutBinding roundedCardCalendarNavLayoutBinding, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, ConstraintLayout constraintLayout4, LinearLayout linearLayout2, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ConstraintLayout constraintLayout7, LayoutCultFitFtuCardBinding layoutCultFitFtuCardBinding, ConstraintLayout constraintLayout8, DoMoreWithYourWatchCardContainerDashboardBinding doMoreWithYourWatchCardContainerDashboardBinding, RecyclerView recyclerView, DashboardDynamicWebLayoutBinding dashboardDynamicWebLayoutBinding, DashboardDynamicWebLayoutBinding dashboardDynamicWebLayoutBinding2, SmallHealthCardInfoBinding smallHealthCardInfoBinding, SmallHealthCardInfoBinding smallHealthCardInfoBinding2, RoundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding roundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding, ListItemWeekPlanLayoutBinding listItemWeekPlanLayoutBinding, DashboardFitnessCardLayoutBinding dashboardFitnessCardLayoutBinding, SmallHealthCardInfoWithProgressBinding smallHealthCardInfoWithProgressBinding, FrameLayout frameLayout, FitnessVitalCardContainerDashboardBinding fitnessVitalCardContainerDashboardBinding, ImageView imageView, ImageView imageView2, ImageView imageView3, FitnessIndusindWellnessCrewBinding fitnessIndusindWellnessCrewBinding, ImageView imageView4, SmallRoundedCardIconHeaderStatusBinding smallRoundedCardIconHeaderStatusBinding, SmallRoundedCardIconHeaderStatusBinding smallRoundedCardIconHeaderStatusBinding2, LinearLayout linearLayout3, LinearLayout linearLayout4, LayoutMyBuddiesFtuCardBinding layoutMyBuddiesFtuCardBinding, ImageView imageView5, NoChallengesBannerBinding noChallengesBannerBinding, PersonalizedWatchFaceDashboardBinding personalizedWatchFaceDashboardBinding, View view2, ProfileCompletionCardDashboardBinding profileCompletionCardDashboardBinding, KeepGoingLayoutDashboardBinding keepGoingLayoutDashboardBinding, RecyclerView recyclerView2, RecyclerView recyclerView3, RecyclerView recyclerView4, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, ConstraintLayout constraintLayout15, ConstraintLayout constraintLayout16, ConstraintLayout constraintLayout17, ConstraintLayout constraintLayout18, ConstraintLayout constraintLayout19, ConstraintLayout constraintLayout20, ConstraintLayout constraintLayout21, ConstraintLayout constraintLayout22, ConstraintLayout constraintLayout23, ConstraintLayout constraintLayout24, ConstraintLayout constraintLayout25, ConstraintLayout constraintLayout26, ConstraintLayout constraintLayout27, ConstraintLayout constraintLayout28, ConstraintLayout constraintLayout29, PagerContainer pagerContainer, SwipeRefreshLayout swipeRefreshLayout, ConnectedDeviceInfoCardDashboardBinding connectedDeviceInfoCardDashboardBinding, ToolbarGenericDashboardBinding toolbarGenericDashboardBinding, FitnessVitalsCardWithBackgroundBinding fitnessVitalsCardWithBackgroundBinding, TextView textView, TextView textView2, BestoffersContainersBinding bestoffersContainersBinding, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, View view3, RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, RoundedCardNavLayoutBinding roundedCardNavLayoutBinding2, RoundedCardNavLayoutBinding roundedCardNavLayoutBinding3, TextView textView16, TextView textView17, WatchSettingsBigCardDashboardBinding watchSettingsBigCardDashboardBinding, WatchFaceStudioBigCardDashboardBinding watchFaceStudioBigCardDashboardBinding, WatchFaceStudioBigCardDashboardBinding watchFaceStudioBigCardDashboardBinding2) {
        super(obj, view, i);
        this.activities700plus = exclusiveCardContentHeaderInfoImageTextbuttonBinding;
        this.activtyHistory = activityHistoryBeanLayoutBinding;
        this.boatCoinsLayout = exclusiveCardBoatCoinsBinding;
        this.buddiesLayout = constraintLayout;
        this.calSelectionView = roundedCardCalendarNavLayoutBinding;
        this.challengeBannerSectionLayout = constraintLayout2;
        this.challengeHeaderSectionTv = constraintLayout3;
        this.challengeLinearLayoutDots = linearLayout;
        this.clActivityHistory = constraintLayout4;
        this.clFitnessJourneyMain = linearLayout2;
        this.clFitnessVitalsHeader = constraintLayout5;
        this.clOtherFitnessData = constraintLayout6;
        this.clTopFeature = constraintLayout7;
        this.cultFitFtu = layoutCultFitFtuCardBinding;
        this.cultFitLayout = constraintLayout8;
        this.doMoreWithYourWatchCardContainer = doMoreWithYourWatchCardContainerDashboardBinding;
        this.dynamicTabRecy = recyclerView;
        this.dynamicWebView = dashboardDynamicWebLayoutBinding;
        this.dynamicWebView1 = dashboardDynamicWebLayoutBinding2;
        this.fitnessCalorie = smallHealthCardInfoBinding;
        this.fitnessDistance = smallHealthCardInfoBinding2;
        this.fitnessJourney = roundedLeftTopBorderColorBackgroundCardHeaderInfoImageBinding;
        this.fitnessJourneyOngoing = listItemWeekPlanLayoutBinding;
        this.fitnessOverviewCl = dashboardFitnessCardLayoutBinding;
        this.fitnessSleep = smallHealthCardInfoWithProgressBinding;
        this.fragmentContainerCultFit = frameLayout;
        this.healthVitalsCardContainer = fitnessVitalCardContainerDashboardBinding;
        this.imageViewDot1 = imageView;
        this.imageViewDot2 = imageView2;
        this.imageViewDot3 = imageView3;
        this.indusIndWellnessCrew = fitnessIndusindWellnessCrewBinding;
        this.ivPoweredBy = imageView4;
        this.lBleState = smallRoundedCardIconHeaderStatusBinding;
        this.lBt3State = smallRoundedCardIconHeaderStatusBinding2;
        this.linearLayoutDots = linearLayout3;
        this.llOtherFitnessData = linearLayout4;
        this.myBuddiesFtu = layoutMyBuddiesFtuCardBinding;
        this.navigationBanner = imageView5;
        this.noChallengeView = noChallengesBannerBinding;
        this.personalizedWatchfaceContainer = personalizedWatchFaceDashboardBinding;
        this.placeHolderView1 = view2;
        this.profileLayout = profileCompletionCardDashboardBinding;
        this.progressMotivationalMessage = keepGoingLayoutDashboardBinding;
        this.rcvBuddiesList = recyclerView2;
        this.rvFitnessChallenge = recyclerView3;
        this.rvTopFeatures = recyclerView4;
        this.section1 = constraintLayout9;
        this.section10 = constraintLayout10;
        this.section11 = constraintLayout11;
        this.section12 = constraintLayout12;
        this.section13 = constraintLayout13;
        this.section14 = constraintLayout14;
        this.section15 = constraintLayout15;
        this.section16 = constraintLayout16;
        this.section17 = constraintLayout17;
        this.section18 = constraintLayout18;
        this.section2 = constraintLayout19;
        this.section3 = constraintLayout20;
        this.section4 = constraintLayout21;
        this.section5 = constraintLayout22;
        this.section61 = constraintLayout23;
        this.section62 = constraintLayout24;
        this.section7 = constraintLayout25;
        this.section8 = constraintLayout26;
        this.section9 = constraintLayout27;
        this.sectionContainer = constraintLayout28;
        this.sectionDynamicTab = constraintLayout29;
        this.setYourWatchPager = pagerContainer;
        this.swipeContainer = swipeRefreshLayout;
        this.syncStatusLayout = connectedDeviceInfoCardDashboardBinding;
        this.toolbar = toolbarGenericDashboardBinding;
        this.trackMoreVitals = fitnessVitalsCardWithBackgroundBinding;
        this.tvActivityHistoryHeader = textView;
        this.tvBestOffers = textView2;
        this.tvBestOffersCardContainer = bestoffersContainersBinding;
        this.tvCultFitHeader = textView3;
        this.tvDoMoreWithYourWatch = textView4;
        this.tvEditVitalCards = textView5;
        this.tvFitnessBuddiesHeader = textView6;
        this.tvFitnessChallengeHeader = textView7;
        this.tvFitnessChallengeViewMore = textView8;
        this.tvFitnessJourneyHeader = textView9;
        this.tvFitnessJourneyViewMore = textView10;
        this.tvFitnessOverviewHeader = textView11;
        this.tvFitnessVitals = textView12;
        this.tvSetupYourWatchHeader = textView13;
        this.tvTopFeaturesTitle = textView14;
        this.tvboatExclusiveHeader = textView15;
        this.v = view3;
        this.viewActivityHistoryHeader = roundedCardNavLayoutBinding;
        this.viewFitnessChallengeDashboardHeader = roundedCardNavLayoutBinding2;
        this.viewFitnessDashboardHeader = roundedCardNavLayoutBinding3;
        this.viewMoreBuddies = textView16;
        this.viewMoreCultFit = textView17;
        this.watchSettingsBig = watchSettingsBigCardDashboardBinding;
        this.watchfaceStudioBig = watchFaceStudioBigCardDashboardBinding;
        this.watchfaceStudioBigTop = watchFaceStudioBigCardDashboardBinding2;
    }

    public static FragmentHomeBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Integer getBestOffers() {
        return this.mBestOffers;
    }

    @Nullable
    public BindingDataModel1 getBleConnectionState() {
        return this.mBleConnectionState;
    }

    @Nullable
    public BindingDataModel1 getBt3ConnectionState() {
        return this.mBt3ConnectionState;
    }

    @Nullable
    public SmallHealthCardInfo getCalorieData() {
        return this.mCalorieData;
    }

    @Nullable
    public SmallHealthCardInfo getDistanceData() {
        return this.mDistanceData;
    }

    @Nullable
    public Integer getDoMoreWithYourWatchItemCount() {
        return this.mDoMoreWithYourWatchItemCount;
    }

    @Nullable
    public Boolean getIsFitnessPlanOngoing() {
        return this.mIsFitnessPlanOngoing;
    }

    @Nullable
    public Boolean getIsSetupYourWatchSettingsCompleted() {
        return this.mIsSetupYourWatchSettingsCompleted;
    }

    @Nullable
    public Boolean getIsUserProfileCompleted() {
        return this.mIsUserProfileCompleted;
    }

    @Nullable
    public Integer getSelectedVitalsCount() {
        return this.mSelectedVitalsCount;
    }

    @Nullable
    public Integer getSetupYourWatchItemCount() {
        return this.mSetupYourWatchItemCount;
    }

    @Nullable
    public Integer getSetupYourWatchItemSelectedPosition() {
        return this.mSetupYourWatchItemSelectedPosition;
    }

    @Nullable
    public Boolean getShow1kFtu() {
        return this.mShow1kFtu;
    }

    @Nullable
    public Boolean getShowActivityHistory() {
        return this.mShowActivityHistory;
    }

    @Nullable
    public Boolean getShowBoatCoins() {
        return this.mShowBoatCoins;
    }

    @Nullable
    public Boolean getShowCultFitFTU() {
        return this.mShowCultFitFTU;
    }

    @Nullable
    public Boolean getShowDynamicTab() {
        return this.mShowDynamicTab;
    }

    @Nullable
    public Boolean getShowFitnessChallenge() {
        return this.mShowFitnessChallenge;
    }

    @Nullable
    public Boolean getShowMyBuddies() {
        return this.mShowMyBuddies;
    }

    @Nullable
    public Boolean getShowMyBuddiesList() {
        return this.mShowMyBuddiesList;
    }

    @Nullable
    public Boolean getShowPersonalizedWatchFace() {
        return this.mShowPersonalizedWatchFace;
    }

    @Nullable
    public Boolean getShowWatchSettingsBigLayout() {
        return this.mShowWatchSettingsBigLayout;
    }

    @Nullable
    public Boolean getShowWatchfaceBigLayout() {
        return this.mShowWatchfaceBigLayout;
    }

    @Nullable
    public Boolean getShowWatchfaceStudioBigLayout() {
        return this.mShowWatchfaceStudioBigLayout;
    }

    @Nullable
    public Boolean getShowWatchfaceStudioBigLayoutTop() {
        return this.mShowWatchfaceStudioBigLayoutTop;
    }

    @Nullable
    public SmallHealthCardInfo getSleepData() {
        return this.mSleepData;
    }

    @Nullable
    public StepsDataModel getStepsDataModel() {
        return this.mStepsDataModel;
    }

    @Nullable
    public SyncingStateData getSyncingStateData() {
        return this.mSyncingStateData;
    }

    public abstract void setBestOffers(@Nullable Integer num);

    public abstract void setBleConnectionState(@Nullable BindingDataModel1 bindingDataModel1);

    public abstract void setBt3ConnectionState(@Nullable BindingDataModel1 bindingDataModel1);

    public abstract void setCalorieData(@Nullable SmallHealthCardInfo smallHealthCardInfo);

    public abstract void setDistanceData(@Nullable SmallHealthCardInfo smallHealthCardInfo);

    public abstract void setDoMoreWithYourWatchItemCount(@Nullable Integer num);

    public abstract void setIsFitnessPlanOngoing(@Nullable Boolean bool);

    public abstract void setIsSetupYourWatchSettingsCompleted(@Nullable Boolean bool);

    public abstract void setIsUserProfileCompleted(@Nullable Boolean bool);

    public abstract void setSelectedVitalsCount(@Nullable Integer num);

    public abstract void setSetupYourWatchItemCount(@Nullable Integer num);

    public abstract void setSetupYourWatchItemSelectedPosition(@Nullable Integer num);

    public abstract void setShow1kFtu(@Nullable Boolean bool);

    public abstract void setShowActivityHistory(@Nullable Boolean bool);

    public abstract void setShowBoatCoins(@Nullable Boolean bool);

    public abstract void setShowCultFitFTU(@Nullable Boolean bool);

    public abstract void setShowDynamicTab(@Nullable Boolean bool);

    public abstract void setShowFitnessChallenge(@Nullable Boolean bool);

    public abstract void setShowMyBuddies(@Nullable Boolean bool);

    public abstract void setShowMyBuddiesList(@Nullable Boolean bool);

    public abstract void setShowPersonalizedWatchFace(@Nullable Boolean bool);

    public abstract void setShowWatchSettingsBigLayout(@Nullable Boolean bool);

    public abstract void setShowWatchfaceBigLayout(@Nullable Boolean bool);

    public abstract void setShowWatchfaceStudioBigLayout(@Nullable Boolean bool);

    public abstract void setShowWatchfaceStudioBigLayoutTop(@Nullable Boolean bool);

    public abstract void setSleepData(@Nullable SmallHealthCardInfo smallHealthCardInfo);

    public abstract void setStepsDataModel(@Nullable StepsDataModel stepsDataModel);

    public abstract void setSyncingStateData(@Nullable SyncingStateData syncingStateData);

    @Deprecated
    public static FragmentHomeBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentHomeBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_home);
    }

    @NonNull
    @Deprecated
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_home, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentHomeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentHomeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_home, null, false, obj);
    }
}
