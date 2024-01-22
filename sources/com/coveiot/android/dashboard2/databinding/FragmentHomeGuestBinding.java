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
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.databinding.BestoffersContainersBinding;
import com.coveiot.android.theme.databinding.DeviceNotPairedBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBinding;
import com.coveiot.android.theme.databinding.LayoutCultFitFtuCardBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
import com.coveiot.android.theme.databinding.ToolbarGenericDashboardBinding;
/* loaded from: classes4.dex */
public abstract class FragmentHomeGuestBinding extends ViewDataBinding {
    @NonNull
    public final ExclusiveCardContentHeaderInfoImageTextbuttonBinding activities700plus;
    @NonNull
    public final ConstraintLayout cardBackground;
    @NonNull
    public final ConstraintLayout challengeBannerSectionLayout;
    @NonNull
    public final ConstraintLayout challengeHeaderSectionTv;
    @NonNull
    public final LinearLayout challengeLinearLayoutDots;
    @NonNull
    public final ConstraintLayout clBuildFitnessPlan;
    @NonNull
    public final ConstraintLayout clFitnessChallenge;
    @NonNull
    public final ConstraintLayout clGuestDevice;
    @NonNull
    public final CardView clMatches;
    @NonNull
    public final CardView clTapPay;
    @NonNull
    public final ConstraintLayout clWellnessCrew;
    @NonNull
    public final LayoutCultFitFtuCardBinding cultFitFtu;
    @NonNull
    public final ConstraintLayout cultFitLayout;
    @NonNull
    public final DeviceNotPairedBinding deviceNotPaired;
    @NonNull
    public final FrameLayout fragmentContainerCultFit;
    @NonNull
    public final Guideline guideline1;
    @NonNull
    public final Guideline guideline2;
    @NonNull
    public final ImageView ivIcon;
    @NonNull
    public final ImageView ivIconBackground;
    @NonNull
    public final ImageView ivInfo;
    @NonNull
    public final ImageView ivWatch;
    @Bindable
    public Integer mBestOffers;
    @Bindable
    public Boolean mShowCultFitFTU;
    @Bindable
    public Boolean mShowFitnessChallenge;
    @NonNull
    public final NoChallengesBannerBinding noChallengeView;
    @NonNull
    public final RecyclerView rvFitnessChallenge;
    @NonNull
    public final ToolbarGenericDashboardBinding toolbar;
    @NonNull
    public final TextView tvBestOffers;
    @NonNull
    public final BestoffersContainersBinding tvBestOffersCardContainer;
    @NonNull
    public final TextView tvBoatExclusive;
    @NonNull
    public final TextView tvCultFitHeader;
    @NonNull
    public final TextView tvDoMore;
    @NonNull
    public final TextView tvFeatureInfo;
    @NonNull
    public final TextView tvFitnessChallengeHeader;
    @NonNull
    public final TextView tvFitnessChallengeViewMore;
    @NonNull
    public final TextView tvGet;
    @NonNull
    public final TextView tvGuestHello;
    @NonNull
    public final TextView tvGuestUserName;
    @NonNull
    public final TextView tvHeader;
    @NonNull
    public final TextView tvHeader1;
    @NonNull
    public final TextView tvInfo;
    @NonNull
    public final TextView tvMatch;
    @NonNull
    public final TextView tvTap;
    @NonNull
    public final View view;
    @NonNull
    public final View view1;
    @NonNull
    public final RoundedCardNavLayoutBinding viewFitnessChallengeDashboardHeader;
    @NonNull
    public final TextView viewMoreCultFit;

    public FragmentHomeGuestBinding(Object obj, View view, int i, ExclusiveCardContentHeaderInfoImageTextbuttonBinding exclusiveCardContentHeaderInfoImageTextbuttonBinding, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, LinearLayout linearLayout, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, CardView cardView, CardView cardView2, ConstraintLayout constraintLayout7, LayoutCultFitFtuCardBinding layoutCultFitFtuCardBinding, ConstraintLayout constraintLayout8, DeviceNotPairedBinding deviceNotPairedBinding, FrameLayout frameLayout, Guideline guideline, Guideline guideline2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, NoChallengesBannerBinding noChallengesBannerBinding, RecyclerView recyclerView, ToolbarGenericDashboardBinding toolbarGenericDashboardBinding, TextView textView, BestoffersContainersBinding bestoffersContainersBinding, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, View view2, View view3, RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, TextView textView16) {
        super(obj, view, i);
        this.activities700plus = exclusiveCardContentHeaderInfoImageTextbuttonBinding;
        this.cardBackground = constraintLayout;
        this.challengeBannerSectionLayout = constraintLayout2;
        this.challengeHeaderSectionTv = constraintLayout3;
        this.challengeLinearLayoutDots = linearLayout;
        this.clBuildFitnessPlan = constraintLayout4;
        this.clFitnessChallenge = constraintLayout5;
        this.clGuestDevice = constraintLayout6;
        this.clMatches = cardView;
        this.clTapPay = cardView2;
        this.clWellnessCrew = constraintLayout7;
        this.cultFitFtu = layoutCultFitFtuCardBinding;
        this.cultFitLayout = constraintLayout8;
        this.deviceNotPaired = deviceNotPairedBinding;
        this.fragmentContainerCultFit = frameLayout;
        this.guideline1 = guideline;
        this.guideline2 = guideline2;
        this.ivIcon = imageView;
        this.ivIconBackground = imageView2;
        this.ivInfo = imageView3;
        this.ivWatch = imageView4;
        this.noChallengeView = noChallengesBannerBinding;
        this.rvFitnessChallenge = recyclerView;
        this.toolbar = toolbarGenericDashboardBinding;
        this.tvBestOffers = textView;
        this.tvBestOffersCardContainer = bestoffersContainersBinding;
        this.tvBoatExclusive = textView2;
        this.tvCultFitHeader = textView3;
        this.tvDoMore = textView4;
        this.tvFeatureInfo = textView5;
        this.tvFitnessChallengeHeader = textView6;
        this.tvFitnessChallengeViewMore = textView7;
        this.tvGet = textView8;
        this.tvGuestHello = textView9;
        this.tvGuestUserName = textView10;
        this.tvHeader = textView11;
        this.tvHeader1 = textView12;
        this.tvInfo = textView13;
        this.tvMatch = textView14;
        this.tvTap = textView15;
        this.view = view2;
        this.view1 = view3;
        this.viewFitnessChallengeDashboardHeader = roundedCardNavLayoutBinding;
        this.viewMoreCultFit = textView16;
    }

    public static FragmentHomeGuestBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static FragmentHomeGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public Integer getBestOffers() {
        return this.mBestOffers;
    }

    @Nullable
    public Boolean getShowCultFitFTU() {
        return this.mShowCultFitFTU;
    }

    @Nullable
    public Boolean getShowFitnessChallenge() {
        return this.mShowFitnessChallenge;
    }

    public abstract void setBestOffers(@Nullable Integer num);

    public abstract void setShowCultFitFTU(@Nullable Boolean bool);

    public abstract void setShowFitnessChallenge(@Nullable Boolean bool);

    @Deprecated
    public static FragmentHomeGuestBinding bind(@NonNull View view, @Nullable Object obj) {
        return (FragmentHomeGuestBinding) ViewDataBinding.bind(obj, view, R.layout.fragment_home_guest);
    }

    @NonNull
    @Deprecated
    public static FragmentHomeGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (FragmentHomeGuestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_home_guest, viewGroup, z, obj);
    }

    @NonNull
    public static FragmentHomeGuestBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static FragmentHomeGuestBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (FragmentHomeGuestBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_home_guest, null, false, obj);
    }
}
