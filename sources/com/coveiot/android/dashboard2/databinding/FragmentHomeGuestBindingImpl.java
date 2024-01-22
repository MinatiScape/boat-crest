package com.coveiot.android.dashboard2.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.BR;
import com.coveiot.android.fitnesschallenges.databinding.NoChallengesBannerBinding;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.databinding.BestoffersContainersBinding;
import com.coveiot.android.theme.databinding.DeviceNotPairedBinding;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBinding;
import com.coveiot.android.theme.databinding.LayoutCultFitFtuCardBinding;
import com.coveiot.android.theme.databinding.RoundedCardNavLayoutBinding;
import com.coveiot.android.theme.databinding.ToolbarGenericDashboardBinding;
/* loaded from: classes4.dex */
public class FragmentHomeGuestBindingImpl extends FragmentHomeGuestBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final ConstraintLayout h;
    @NonNull
    public final ConstraintLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(46);
        k = includedLayouts;
        includedLayouts.setIncludes(0, new String[]{"toolbar_generic_dashboard"}, new int[]{8}, new int[]{R.layout.toolbar_generic_dashboard});
        includedLayouts.setIncludes(1, new String[]{"device_not_paired", "exclusive_card_content_header_info_image_textbutton", "bestoffers_containers"}, new int[]{9, 10, 14}, new int[]{R.layout.device_not_paired, R.layout.exclusive_card_content_header_info_image_textbutton, R.layout.bestoffers_containers});
        includedLayouts.setIncludes(2, new String[]{"layout_cult_fit_ftu_card"}, new int[]{11}, new int[]{R.layout.layout_cult_fit_ftu_card});
        includedLayouts.setIncludes(5, new String[]{"rounded_card_nav_layout"}, new int[]{13}, new int[]{R.layout.rounded_card_nav_layout});
        includedLayouts.setIncludes(6, new String[]{"no_challenges_banner"}, new int[]{12}, new int[]{com.coveiot.android.fitnesschallenges.R.layout.no_challenges_banner});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clGuestDevice, 15);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvGuestHello, 16);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvGuestUserName, 17);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.ivWatch, 18);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvBoatExclusive, 19);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvDoMore, 20);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clWellnessCrew, 21);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.card_background, 22);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.ivIconBackground, 23);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.ivIcon, 24);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvHeader, 25);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.view, 26);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvInfo, 27);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.guideline1, 28);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clMatches, 29);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvGet, 30);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvMatch, 31);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clTapPay, 32);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvTap, 33);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.clBuildFitnessPlan, 34);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvHeader1, 35);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.view1, 36);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.guideline2, 37);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.ivInfo, 38);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFeatureInfo, 39);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvCultFitHeader, 40);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.challenge_header_section_tv, 41);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessChallengeHeader, 42);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.tvFitnessChallengeViewMore, 43);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.rvFitnessChallenge, 44);
        sparseIntArray.put(com.coveiot.android.dashboard2.R.id.challengeLinearLayoutDots, 45);
    }

    public FragmentHomeGuestBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 46, k, l));
    }

    public final boolean a(ExclusiveCardContentHeaderInfoImageTextbuttonBinding exclusiveCardContentHeaderInfoImageTextbuttonBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean b(LayoutCultFitFtuCardBinding layoutCultFitFtuCardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean c(DeviceNotPairedBinding deviceNotPairedBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 64;
            }
            return true;
        }
        return false;
    }

    public final boolean d(NoChallengesBannerBinding noChallengesBannerBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean e(ToolbarGenericDashboardBinding toolbarGenericDashboardBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a4  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBindingImpl.executeBindings():void");
    }

    public final boolean f(BestoffersContainersBinding bestoffersContainersBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 8;
            }
            return true;
        }
        return false;
    }

    public final boolean g(RoundedCardNavLayoutBinding roundedCardNavLayoutBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.j |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.toolbar.hasPendingBindings() || this.deviceNotPaired.hasPendingBindings() || this.activities700plus.hasPendingBindings() || this.cultFitFtu.hasPendingBindings() || this.noChallengeView.hasPendingBindings() || this.viewFitnessChallengeDashboardHeader.hasPendingBindings() || this.tvBestOffersCardContainer.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 1024L;
        }
        this.toolbar.invalidateAll();
        this.deviceNotPaired.invalidateAll();
        this.activities700plus.invalidateAll();
        this.cultFitFtu.invalidateAll();
        this.noChallengeView.invalidateAll();
        this.viewFitnessChallengeDashboardHeader.invalidateAll();
        this.tvBestOffersCardContainer.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return d((NoChallengesBannerBinding) obj, i2);
            case 1:
                return g((RoundedCardNavLayoutBinding) obj, i2);
            case 2:
                return a((ExclusiveCardContentHeaderInfoImageTextbuttonBinding) obj, i2);
            case 3:
                return f((BestoffersContainersBinding) obj, i2);
            case 4:
                return b((LayoutCultFitFtuCardBinding) obj, i2);
            case 5:
                return e((ToolbarGenericDashboardBinding) obj, i2);
            case 6:
                return c((DeviceNotPairedBinding) obj, i2);
            default:
                return false;
        }
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBinding
    public void setBestOffers(@Nullable Integer num) {
        this.mBestOffers = num;
        synchronized (this) {
            this.j |= 256;
        }
        notifyPropertyChanged(BR.bestOffers);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.toolbar.setLifecycleOwner(lifecycleOwner);
        this.deviceNotPaired.setLifecycleOwner(lifecycleOwner);
        this.activities700plus.setLifecycleOwner(lifecycleOwner);
        this.cultFitFtu.setLifecycleOwner(lifecycleOwner);
        this.noChallengeView.setLifecycleOwner(lifecycleOwner);
        this.viewFitnessChallengeDashboardHeader.setLifecycleOwner(lifecycleOwner);
        this.tvBestOffersCardContainer.setLifecycleOwner(lifecycleOwner);
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBinding
    public void setShowCultFitFTU(@Nullable Boolean bool) {
        this.mShowCultFitFTU = bool;
        synchronized (this) {
            this.j |= 128;
        }
        notifyPropertyChanged(BR.showCultFitFTU);
        super.requestRebind();
    }

    @Override // com.coveiot.android.dashboard2.databinding.FragmentHomeGuestBinding
    public void setShowFitnessChallenge(@Nullable Boolean bool) {
        this.mShowFitnessChallenge = bool;
        synchronized (this) {
            this.j |= 512;
        }
        notifyPropertyChanged(BR.showFitnessChallenge);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.showCultFitFTU == i) {
            setShowCultFitFTU((Boolean) obj);
        } else if (BR.bestOffers == i) {
            setBestOffers((Integer) obj);
        } else if (BR.showFitnessChallenge != i) {
            return false;
        } else {
            setShowFitnessChallenge((Boolean) obj);
        }
        return true;
    }

    public FragmentHomeGuestBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) objArr[10], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[41], (LinearLayout) objArr[45], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[5], (ConstraintLayout) objArr[15], (CardView) objArr[29], (CardView) objArr[32], (ConstraintLayout) objArr[21], (LayoutCultFitFtuCardBinding) objArr[11], (ConstraintLayout) objArr[2], (DeviceNotPairedBinding) objArr[9], (FrameLayout) objArr[4], (Guideline) objArr[28], (Guideline) objArr[37], (ImageView) objArr[24], (ImageView) objArr[23], (ImageView) objArr[38], (ImageView) objArr[18], (NoChallengesBannerBinding) objArr[12], (RecyclerView) objArr[44], (ToolbarGenericDashboardBinding) objArr[8], (TextView) objArr[7], (BestoffersContainersBinding) objArr[14], (TextView) objArr[19], (TextView) objArr[40], (TextView) objArr[20], (TextView) objArr[39], (TextView) objArr[42], (TextView) objArr[43], (TextView) objArr[30], (TextView) objArr[16], (TextView) objArr[17], (TextView) objArr[25], (TextView) objArr[35], (TextView) objArr[27], (TextView) objArr[31], (TextView) objArr[33], (View) objArr[26], (View) objArr[36], (RoundedCardNavLayoutBinding) objArr[13], (TextView) objArr[3]);
        this.j = -1L;
        setContainedBinding(this.activities700plus);
        this.challengeBannerSectionLayout.setTag(null);
        this.clFitnessChallenge.setTag(null);
        setContainedBinding(this.cultFitFtu);
        this.cultFitLayout.setTag(null);
        setContainedBinding(this.deviceNotPaired);
        this.fragmentContainerCultFit.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.noChallengeView);
        setContainedBinding(this.toolbar);
        this.tvBestOffers.setTag(null);
        setContainedBinding(this.tvBestOffersCardContainer);
        setContainedBinding(this.viewFitnessChallengeDashboardHeader);
        this.viewMoreCultFit.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
