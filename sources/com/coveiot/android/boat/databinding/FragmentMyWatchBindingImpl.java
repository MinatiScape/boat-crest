package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.databinding.ExclusiveCardContentHeaderInfoImageTextbuttonBinding;
import com.coveiot.android.theme.databinding.PersonalizedWatchFaceMyWatchBinding;
import com.coveiot.android.theme.databinding.WatchFaceStudioBigCardDashboardBinding;
/* loaded from: classes3.dex */
public class FragmentMyWatchBindingImpl extends FragmentMyWatchBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k;
    @Nullable
    public static final SparseIntArray l;
    @NonNull
    public final LinearLayout h;
    @NonNull
    public final LinearLayout i;
    public long j;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(84);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"personalized_watch_face_my_watch", "watch_face_studio_big_card_dashboard", "exclusive_card_content_header_info_image_textbutton"}, new int[]{2, 3, 4}, new int[]{R.layout.personalized_watch_face_my_watch, R.layout.watch_face_studio_big_card_dashboard, R.layout.exclusive_card_content_header_info_image_textbutton});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.topHeaderTv, 5);
        sparseIntArray.put(R.id.view, 6);
        sparseIntArray.put(R.id.watch_status_container, 7);
        sparseIntArray.put(R.id.clQuickAccessItemContainer, 8);
        sparseIntArray.put(R.id.clSOSettings, 9);
        sparseIntArray.put(R.id.sosettingsImgV, 10);
        sparseIntArray.put(R.id.sosettingsTv, 11);
        sparseIntArray.put(R.id.sosSettingsTv, 12);
        sparseIntArray.put(R.id.sosSettingsImgV, 13);
        sparseIntArray.put(R.id.clWatchSettings, 14);
        sparseIntArray.put(R.id.watchSettingsImgV, 15);
        sparseIntArray.put(R.id.watchSettingsTv, 16);
        sparseIntArray.put(R.id.watchSettingsDetailsTv, 17);
        sparseIntArray.put(R.id.watchSettingsArrowRightImgV, 18);
        sparseIntArray.put(R.id.clFindMyWatch, 19);
        sparseIntArray.put(R.id.findMyWatchImgV, 20);
        sparseIntArray.put(R.id.findMyWatchTv, 21);
        sparseIntArray.put(R.id.findMyWatchDetailsTv, 22);
        sparseIntArray.put(R.id.findMyWatchArrowRightImgV, 23);
        sparseIntArray.put(R.id.clBluetoothCalling, 24);
        sparseIntArray.put(R.id.bluetoothCallingImgV, 25);
        sparseIntArray.put(R.id.bluetoothCallingTv, 26);
        sparseIntArray.put(R.id.bluetoothCallingDetailsTv, 27);
        sparseIntArray.put(R.id.bluetoothCallingArrowRightImgV, 28);
        sparseIntArray.put(R.id.clFemaleWellnessTracker, 29);
        sparseIntArray.put(R.id.clFemaleWellnessTrackerHeader, 30);
        sparseIntArray.put(R.id.femaleWellnessTrackerTv, 31);
        sparseIntArray.put(R.id.femaleWellnessTrackerRightArrow, 32);
        sparseIntArray.put(R.id.clFemaleWellnessTrackerData, 33);
        sparseIntArray.put(R.id.femaleCalImgV, 34);
        sparseIntArray.put(R.id.femaleDetailTv, 35);
        sparseIntArray.put(R.id.femaleWellnessDataRightArrow, 36);
        sparseIntArray.put(R.id.cycleDetailTv, 37);
        sparseIntArray.put(R.id.clSensAIDetails, 38);
        sparseIntArray.put(R.id.clSensAIHeader, 39);
        sparseIntArray.put(R.id.sensAITv, 40);
        sparseIntArray.put(R.id.sensAIInfoTv, 41);
        sparseIntArray.put(R.id.sensAIArrow, 42);
        sparseIntArray.put(R.id.clData1, 43);
        sparseIntArray.put(R.id.ivImage, 44);
        sparseIntArray.put(R.id.tvTitle, 45);
        sparseIntArray.put(R.id.tvTime, 46);
        sparseIntArray.put(R.id.ivAward, 47);
        sparseIntArray.put(R.id.view_sens_ai, 48);
        sparseIntArray.put(R.id.clDetails, 49);
        sparseIntArray.put(R.id.clDuration, 50);
        sparseIntArray.put(R.id.ivDuration, 51);
        sparseIntArray.put(R.id.tvDuration, 52);
        sparseIntArray.put(R.id.tvDurationTxt, 53);
        sparseIntArray.put(R.id.viewShots, 54);
        sparseIntArray.put(R.id.clTotalShots, 55);
        sparseIntArray.put(R.id.ivShots, 56);
        sparseIntArray.put(R.id.tvTotalShots, 57);
        sparseIntArray.put(R.id.tvTotalShotsTxt, 58);
        sparseIntArray.put(R.id.viewSpeed, 59);
        sparseIntArray.put(R.id.clAvgHandSpeed, 60);
        sparseIntArray.put(R.id.ivSpeed, 61);
        sparseIntArray.put(R.id.tvAvgHandSpeed, 62);
        sparseIntArray.put(R.id.tvAvgHandSpeedTxt, 63);
        sparseIntArray.put(R.id.clSportsScoreDetail, 64);
        sparseIntArray.put(R.id.clSportsScoreHeader, 65);
        sparseIntArray.put(R.id.sportsScoreTv, 66);
        sparseIntArray.put(R.id.sportsScoreRightArrow, 67);
        sparseIntArray.put(R.id.clSportsScoreData, 68);
        sparseIntArray.put(R.id.sportsScoreContentTv, 69);
        sparseIntArray.put(R.id.sportsScoreClockImgV, 70);
        sparseIntArray.put(R.id.intervalTv, 71);
        sparseIntArray.put(R.id.sportsScoreView, 72);
        sparseIntArray.put(R.id.sportsScoreTypeImgV, 73);
        sparseIntArray.put(R.id.sportsScoreTypeTv, 74);
        sparseIntArray.put(R.id.watchFeatureHeaderTv, 75);
        sparseIntArray.put(R.id.clFeatureInfo, 76);
        sparseIntArray.put(R.id.ivInfo, 77);
        sparseIntArray.put(R.id.tvFeatureInfo, 78);
        sparseIntArray.put(R.id.rvMyWatchFeatures, 79);
        sparseIntArray.put(R.id.clViewAll, 80);
        sparseIntArray.put(R.id.viewAllTv, 81);
        sparseIntArray.put(R.id.clViewLess, 82);
        sparseIntArray.put(R.id.viewLessTv, 83);
    }

    public FragmentMyWatchBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 84, k, l));
    }

    public final boolean a(ExclusiveCardContentHeaderInfoImageTextbuttonBinding exclusiveCardContentHeaderInfoImageTextbuttonBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean b(PersonalizedWatchFaceMyWatchBinding personalizedWatchFaceMyWatchBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean c(WatchFaceStudioBigCardDashboardBinding watchFaceStudioBigCardDashboardBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 4;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        synchronized (this) {
            this.j = 0L;
        }
        ViewDataBinding.executeBindingsOn(this.personalizedWatchfaceContainer);
        ViewDataBinding.executeBindingsOn(this.watchfaceStudioBigTop);
        ViewDataBinding.executeBindingsOn(this.activities700plus);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.j != 0) {
                return true;
            }
            return this.personalizedWatchfaceContainer.hasPendingBindings() || this.watchfaceStudioBigTop.hasPendingBindings() || this.activities700plus.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 8L;
        }
        this.personalizedWatchfaceContainer.invalidateAll();
        this.watchfaceStudioBigTop.invalidateAll();
        this.activities700plus.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                return c((WatchFaceStudioBigCardDashboardBinding) obj, i2);
            }
            return a((ExclusiveCardContentHeaderInfoImageTextbuttonBinding) obj, i2);
        }
        return b((PersonalizedWatchFaceMyWatchBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.personalizedWatchfaceContainer.setLifecycleOwner(lifecycleOwner);
        this.watchfaceStudioBigTop.setLifecycleOwner(lifecycleOwner);
        this.activities700plus.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentMyWatchBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (ExclusiveCardContentHeaderInfoImageTextbuttonBinding) objArr[4], (ImageView) objArr[28], (TextView) objArr[27], (ImageView) objArr[25], (TextView) objArr[26], (ConstraintLayout) objArr[60], (ConstraintLayout) objArr[24], (ConstraintLayout) objArr[43], (LinearLayout) objArr[49], (ConstraintLayout) objArr[50], (ConstraintLayout) objArr[76], (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[33], (ConstraintLayout) objArr[30], (ConstraintLayout) objArr[19], (ConstraintLayout) objArr[8], (ConstraintLayout) objArr[9], (ConstraintLayout) objArr[38], (ConstraintLayout) objArr[39], (ConstraintLayout) objArr[68], (ConstraintLayout) objArr[64], (ConstraintLayout) objArr[65], (ConstraintLayout) objArr[55], (ConstraintLayout) objArr[80], (ConstraintLayout) objArr[82], (ConstraintLayout) objArr[14], (TextView) objArr[37], (ImageView) objArr[34], (TextView) objArr[35], (ImageView) objArr[36], (ImageView) objArr[32], (TextView) objArr[31], (ImageView) objArr[23], (TextView) objArr[22], (ImageView) objArr[20], (TextView) objArr[21], (TextView) objArr[71], (ImageView) objArr[47], (ImageView) objArr[51], (ImageView) objArr[44], (ImageView) objArr[77], (ImageView) objArr[56], (ImageView) objArr[61], (PersonalizedWatchFaceMyWatchBinding) objArr[2], (RecyclerView) objArr[79], (ImageView) objArr[42], (TextView) objArr[41], (TextView) objArr[40], (ImageView) objArr[13], (TextView) objArr[12], (ImageView) objArr[10], (TextView) objArr[11], (ImageView) objArr[70], (TextView) objArr[69], (ImageView) objArr[67], (TextView) objArr[66], (ImageView) objArr[73], (TextView) objArr[74], (View) objArr[72], (TextView) objArr[5], (TextView) objArr[62], (TextView) objArr[63], (TextView) objArr[52], (TextView) objArr[53], (TextView) objArr[78], (TextView) objArr[46], (TextView) objArr[45], (TextView) objArr[57], (TextView) objArr[58], (View) objArr[6], (TextView) objArr[81], (TextView) objArr[83], (View) objArr[48], (View) objArr[54], (View) objArr[59], (TextView) objArr[75], (ImageView) objArr[18], (TextView) objArr[17], (ImageView) objArr[15], (TextView) objArr[16], (FrameLayout) objArr[7], (WatchFaceStudioBigCardDashboardBinding) objArr[3]);
        this.j = -1L;
        setContainedBinding(this.activities700plus);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) objArr[1];
        this.i = linearLayout2;
        linearLayout2.setTag(null);
        setContainedBinding(this.personalizedWatchfaceContainer);
        setContainedBinding(this.watchfaceStudioBigTop);
        setRootTag(view);
        invalidateAll();
    }
}
