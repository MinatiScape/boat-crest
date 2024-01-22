package com.coveiot.android.boat.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.theme.databinding.ListItemSleepStagesLayoutBinding;
import com.coveiot.android.theme.databinding.VitalsDetailedLayoutBinding;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.viewpagerindicator.CirclePageIndicator;
/* loaded from: classes3.dex */
public class FragmentVitalSleepBindingImpl extends FragmentVitalSleepBinding {
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
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(75);
        k = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"vitals_detailed_layout"}, new int[]{7}, new int[]{R.layout.vitals_detailed_layout});
        includedLayouts.setIncludes(2, new String[]{"sleep_insights_data"}, new int[]{8}, new int[]{R.layout.sleep_insights_data});
        includedLayouts.setIncludes(3, new String[]{"list_item_sleep_stages_layout"}, new int[]{9}, new int[]{R.layout.list_item_sleep_stages_layout});
        includedLayouts.setIncludes(4, new String[]{"list_item_sleep_stages_layout"}, new int[]{10}, new int[]{R.layout.list_item_sleep_stages_layout});
        includedLayouts.setIncludes(5, new String[]{"list_item_sleep_stages_layout"}, new int[]{11}, new int[]{R.layout.list_item_sleep_stages_layout});
        includedLayouts.setIncludes(6, new String[]{"list_item_sleep_stages_layout"}, new int[]{12}, new int[]{R.layout.list_item_sleep_stages_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.nestedScrollSleep, 13);
        sparseIntArray.put(R.id.sleepGraphBg, 14);
        sparseIntArray.put(R.id.sleepGraphChart, 15);
        sparseIntArray.put(R.id.tvNoSleepDataFound, 16);
        sparseIntArray.put(R.id.sleep_at_text, 17);
        sparseIntArray.put(R.id.awake_at_text, 18);
        sparseIntArray.put(R.id.horizontalBarGraph, 19);
        sparseIntArray.put(R.id.sleep_at_value, 20);
        sparseIntArray.put(R.id.awake_at_value, 21);
        sparseIntArray.put(R.id.sleep_bottom_layout, 22);
        sparseIntArray.put(R.id.deepSleep, 23);
        sparseIntArray.put(R.id.lightSleep, 24);
        sparseIntArray.put(R.id.rem_sleep_layout, 25);
        sparseIntArray.put(R.id.remSleep, 26);
        sparseIntArray.put(R.id.awake_sleep_layout, 27);
        sparseIntArray.put(R.id.awake, 28);
        sparseIntArray.put(R.id.tvNoDataFound, 29);
        sparseIntArray.put(R.id.tvEditVitalNameGoal, 30);
        sparseIntArray.put(R.id.sleep_score_constraint, 31);
        sparseIntArray.put(R.id.sleep_score_layout, 32);
        sparseIntArray.put(R.id.yourSlpScore, 33);
        sparseIntArray.put(R.id.slpimg, 34);
        sparseIntArray.put(R.id.sleep_score_txt, 35);
        sparseIntArray.put(R.id.tv_sleep_score_img, 36);
        sparseIntArray.put(R.id.tv_sleep_score, 37);
        sparseIntArray.put(R.id.consistency_lay, 38);
        sparseIntArray.put(R.id.totalSleeplayout, 39);
        sparseIntArray.put(R.id.total_sleep_txt, 40);
        sparseIntArray.put(R.id.total_sleep_value, 41);
        sparseIntArray.put(R.id.timeTodeepSleeplayout, 42);
        sparseIntArray.put(R.id.time_total_sleep_txt, 43);
        sparseIntArray.put(R.id.time_total_sleep_value, 44);
        sparseIntArray.put(R.id.wasolayout, 45);
        sparseIntArray.put(R.id.waso_txt, 46);
        sparseIntArray.put(R.id.waso_value, 47);
        sparseIntArray.put(R.id.consistancySleeplayout, 48);
        sparseIntArray.put(R.id.sleep_consistency, 49);
        sparseIntArray.put(R.id.sleep_consistency_value, 50);
        sparseIntArray.put(R.id.view2, 51);
        sparseIntArray.put(R.id.nightlyBreathingRate, 52);
        sparseIntArray.put(R.id.imgv_respiratory_rate_sleep, 53);
        sparseIntArray.put(R.id.tv_respiratory_rate_sleep, 54);
        sparseIntArray.put(R.id.cl_min_respiratory_rate_sleep, 55);
        sparseIntArray.put(R.id.tv_respiratory_rate_min, 56);
        sparseIntArray.put(R.id.ll_respiratory_min_sleep, 57);
        sparseIntArray.put(R.id.tv_respiratory_rate_min_val, 58);
        sparseIntArray.put(R.id.tv_respiratory_rate_min_unit, 59);
        sparseIntArray.put(R.id.cl_max_respiratory_rate_sleep, 60);
        sparseIntArray.put(R.id.tv_respiratory_rate_max, 61);
        sparseIntArray.put(R.id.ll_respiratory_max_sleep, 62);
        sparseIntArray.put(R.id.tv_respiratory_rate_max_val, 63);
        sparseIntArray.put(R.id.tv_respiratory_rate_max_unit, 64);
        sparseIntArray.put(R.id.view_pager_layout, 65);
        sparseIntArray.put(R.id.sleep_feedback_pager, 66);
        sparseIntArray.put(R.id.circlePageIndicator_feedback, 67);
        sparseIntArray.put(R.id.view3, 68);
        sparseIntArray.put(R.id.tips_sleep_title_tv, 69);
        sparseIntArray.put(R.id.sleepTipsRecycler, 70);
        sparseIntArray.put(R.id.divider, 71);
        sparseIntArray.put(R.id.aboutSleepStageConstraint, 72);
        sparseIntArray.put(R.id.tvAboutSleepStages, 73);
        sparseIntArray.put(R.id.tvsleepdescription, 74);
    }

    public FragmentVitalSleepBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 75, k, l));
    }

    public final boolean a(ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean b(ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 32;
            }
            return true;
        }
        return false;
    }

    public final boolean c(ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean d(ListItemSleepStagesLayoutBinding listItemSleepStagesLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean e(SleepInsightsDataBinding sleepInsightsDataBinding, int i) {
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
        ViewDataBinding.executeBindingsOn(this.vitalsMainData);
        ViewDataBinding.executeBindingsOn(this.sleepInsights);
        ViewDataBinding.executeBindingsOn(this.deepSleepAbout);
        ViewDataBinding.executeBindingsOn(this.lightSleepAbout);
        ViewDataBinding.executeBindingsOn(this.remSleepAbout);
        ViewDataBinding.executeBindingsOn(this.awakeSleepAbout);
    }

    public final boolean f(VitalsDetailedLayoutBinding vitalsDetailedLayoutBinding, int i) {
        if (i == 0) {
            synchronized (this) {
                this.j |= 8;
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
            return this.vitalsMainData.hasPendingBindings() || this.sleepInsights.hasPendingBindings() || this.deepSleepAbout.hasPendingBindings() || this.lightSleepAbout.hasPendingBindings() || this.remSleepAbout.hasPendingBindings() || this.awakeSleepAbout.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 64L;
        }
        this.vitalsMainData.invalidateAll();
        this.sleepInsights.invalidateAll();
        this.deepSleepAbout.invalidateAll();
        this.lightSleepAbout.invalidateAll();
        this.remSleepAbout.invalidateAll();
        this.awakeSleepAbout.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return false;
                            }
                            return b((ListItemSleepStagesLayoutBinding) obj, i2);
                        }
                        return a((ListItemSleepStagesLayoutBinding) obj, i2);
                    }
                    return f((VitalsDetailedLayoutBinding) obj, i2);
                }
                return e((SleepInsightsDataBinding) obj, i2);
            }
            return c((ListItemSleepStagesLayoutBinding) obj, i2);
        }
        return d((ListItemSleepStagesLayoutBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.vitalsMainData.setLifecycleOwner(lifecycleOwner);
        this.sleepInsights.setLifecycleOwner(lifecycleOwner);
        this.deepSleepAbout.setLifecycleOwner(lifecycleOwner);
        this.lightSleepAbout.setLifecycleOwner(lifecycleOwner);
        this.remSleepAbout.setLifecycleOwner(lifecycleOwner);
        this.awakeSleepAbout.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        return true;
    }

    public FragmentVitalSleepBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (ConstraintLayout) objArr[72], (TextView) objArr[28], (TextView) objArr[18], (TextView) objArr[21], (ListItemSleepStagesLayoutBinding) objArr[12], (ConstraintLayout) objArr[6], (LinearLayout) objArr[27], (CirclePageIndicator) objArr[67], (ConstraintLayout) objArr[60], (ConstraintLayout) objArr[55], (ConstraintLayout) objArr[48], (ConstraintLayout) objArr[38], (TextView) objArr[23], (ListItemSleepStagesLayoutBinding) objArr[9], (ConstraintLayout) objArr[3], (View) objArr[71], (HorizontalBarChart) objArr[19], (ImageView) objArr[53], (TextView) objArr[24], (ListItemSleepStagesLayoutBinding) objArr[10], (ConstraintLayout) objArr[4], (LinearLayout) objArr[62], (LinearLayout) objArr[57], (NestedScrollView) objArr[13], (ConstraintLayout) objArr[52], (TextView) objArr[26], (ListItemSleepStagesLayoutBinding) objArr[11], (ConstraintLayout) objArr[5], (LinearLayout) objArr[25], (TextView) objArr[17], (TextView) objArr[20], (LinearLayout) objArr[22], (TextView) objArr[49], (TextView) objArr[50], (ViewPager) objArr[66], (ConstraintLayout) objArr[2], (ConstraintLayout) objArr[14], (RoundedBarChart) objArr[15], (SleepInsightsDataBinding) objArr[8], (ConstraintLayout) objArr[31], (ConstraintLayout) objArr[32], (TextView) objArr[35], (RecyclerView) objArr[70], (ImageView) objArr[34], (ConstraintLayout) objArr[42], (TextView) objArr[43], (TextView) objArr[44], (TextView) objArr[69], (TextView) objArr[40], (TextView) objArr[41], (ConstraintLayout) objArr[39], (TextView) objArr[73], (TextView) objArr[30], (TextView) objArr[29], (TextView) objArr[16], (TextView) objArr[61], (TextView) objArr[64], (TextView) objArr[63], (TextView) objArr[56], (TextView) objArr[59], (TextView) objArr[58], (TextView) objArr[54], (TextView) objArr[37], (ImageView) objArr[36], (TextView) objArr[74], (View) objArr[51], (View) objArr[68], (LinearLayout) objArr[65], (VitalsDetailedLayoutBinding) objArr[7], (TextView) objArr[46], (TextView) objArr[47], (ConstraintLayout) objArr[45], (ConstraintLayout) objArr[33]);
        this.j = -1L;
        setContainedBinding(this.awakeSleepAbout);
        this.awakeSleepAboutConstraint.setTag(null);
        setContainedBinding(this.deepSleepAbout);
        this.deepSleepAboutConstraint.setTag(null);
        setContainedBinding(this.lightSleepAbout);
        this.lightSleepAboutConstraint.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) objArr[1];
        this.i = constraintLayout2;
        constraintLayout2.setTag(null);
        setContainedBinding(this.remSleepAbout);
        this.remSleepAboutConstraint.setTag(null);
        this.sleepGraphAndDetail.setTag(null);
        setContainedBinding(this.sleepInsights);
        setContainedBinding(this.vitalsMainData);
        setRootTag(view);
        invalidateAll();
    }
}
