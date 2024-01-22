package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalStepsBinding;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.leonardo.dashboard.adapter.VitalStepsInsightsAdapter;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.model.WalkData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStepsViewModel;
import com.coveiot.android.leonardo.dashboard.model.InsightStepsHourModel;
import com.coveiot.android.leonardo.dashboard.model.InsightsStepsDataModel;
import com.coveiot.android.leonardo.dashboard.model.InsightsStepsDetailsModel;
import com.coveiot.android.leonardo.dashboard.model.InsightsStepsInitialModel;
import com.coveiot.android.leonardo.dashboard.model.StepsWeeklyMonthlyData;
import com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalStepsFragment;
import com.coveiot.android.leonardo.more.activities.ActivityStepsGoalSettings;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.android.theme.utils.GridSpacingItemDecoration;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.callbacks.IMyBadgeItemClick;
import com.coveiot.leaderboard.views.BadgesDetailsDialog;
import com.coveiot.leaderboard.views.adapters.HomeMyDailyBadgesAdapter;
import com.coveiot.repository.sedentary.datasource.db.SedentaryDBRepo;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalStepsFragment extends BaseFragment implements ContractFitnessDashBoard, CalendarRangeDialog.OnCalendarRangeSelector, IMyBadgeItemClick {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public boolean A;
    @Nullable
    public BadgesDetailsDialog B;
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalStepsBinding m;
    public FragmentStepsViewModel n;
    @Nullable
    public WalkData o;
    @NotNull
    public Calendar q;
    public Pair<? extends Calendar, ? extends Calendar> r;
    public VitalStepsInsightsAdapter s;
    public SimpleDateFormat simpleDateFormat;
    public CalendarRangeDialog t;
    @Nullable
    public HomeMyDailyBadgesAdapter u;
    @NotNull
    public List<MyBadgesModel.DataBean.BadgesBean> v;
    public int w;
    public final int x;
    public final int y;
    public final int z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public boolean p = true;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalStepsFragment newInstance() {
            return new VitalStepsFragment();
        }
    }

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<MyBadgesModel, Unit> {
        public a() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int invoke$lambda$0(MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean, MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2) {
            return Intrinsics.compare(badgeLevelsBean.getLevelWeight(), badgeLevelsBean2.getLevelWeight());
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(MyBadgesModel myBadgesModel) {
            invoke2(myBadgesModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable MyBadgesModel myBadgesModel) {
            MyBadgesModel.DataBean data;
            if (((myBadgesModel == null || (data = myBadgesModel.getData()) == null) ? null : data.getBadges()) != null && myBadgesModel.getData().getBadges().size() > 0) {
                VitalStepsFragment vitalStepsFragment = VitalStepsFragment.this;
                ConstraintLayout constraintLayout = vitalStepsFragment.F().clAchievements;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clAchievements");
                vitalStepsFragment.visible(constraintLayout);
                List list = VitalStepsFragment.this.v;
                List<MyBadgesModel.DataBean.BadgesBean> badges = myBadgesModel.getData().getBadges();
                Intrinsics.checkNotNullExpressionValue(badges, "it.data.badges");
                list.addAll(badges);
                for (MyBadgesModel.DataBean.BadgesBean badgesBean : VitalStepsFragment.this.v) {
                    List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels = badgesBean.getBadgeLevels();
                    Intrinsics.checkNotNull(badgeLevels);
                    Collections.sort(badgeLevels, new Comparator() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.i0
                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            int invoke$lambda$0;
                            invoke$lambda$0 = VitalStepsFragment.a.invoke$lambda$0((MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj, (MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean) obj2);
                            return invoke$lambda$0;
                        }
                    });
                }
                GridLayoutManager gridLayoutManager = new GridLayoutManager(VitalStepsFragment.this.requireContext(), 3);
                VitalStepsFragment.this.F().rvAchievements.addItemDecoration(new GridSpacingItemDecoration(3, 30, false));
                VitalStepsFragment.this.F().rvAchievements.setLayoutManager(gridLayoutManager);
                VitalStepsFragment vitalStepsFragment2 = VitalStepsFragment.this;
                vitalStepsFragment2.u = new HomeMyDailyBadgesAdapter(vitalStepsFragment2.requireContext(), VitalStepsFragment.this, Boolean.TRUE);
                HomeMyDailyBadgesAdapter homeMyDailyBadgesAdapter = VitalStepsFragment.this.u;
                Intrinsics.checkNotNull(homeMyDailyBadgesAdapter);
                homeMyDailyBadgesAdapter.setData(VitalStepsFragment.this.v);
                VitalStepsFragment.this.F().rvAchievements.setAdapter(VitalStepsFragment.this.u);
                return;
            }
            VitalStepsFragment vitalStepsFragment3 = VitalStepsFragment.this;
            ConstraintLayout constraintLayout2 = vitalStepsFragment3.F().clAchievements;
            Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clAchievements");
            vitalStepsFragment3.gone(constraintLayout2);
        }
    }

    /* loaded from: classes4.dex */
    public static final class b extends Lambda implements Function1<StepsWeeklyMonthlyData, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(StepsWeeklyMonthlyData stepsWeeklyMonthlyData) {
            invoke2(stepsWeeklyMonthlyData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(StepsWeeklyMonthlyData stepsWeeklyMonthlyData) {
            if (VitalStepsFragment.this.w != VitalStepsFragment.this.x) {
                VitalStepsFragment.this.F().vitalsMainData.tvAvgStepSleepValue.setText(String.valueOf(stepsWeeklyMonthlyData.getAvgSteps()));
                TextView textView = VitalStepsFragment.this.F().vitalsMainData.tvVitalMinValue;
                PayUtils payUtils = PayUtils.INSTANCE;
                Context requireContext = VitalStepsFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                textView.setText(payUtils.getAvgDistanceValueDouble(requireContext, VitalStepsFragment.this.p ? stepsWeeklyMonthlyData.getAvgDistanceMiles() : stepsWeeklyMonthlyData.getAvgDistanceKm()));
                VitalStepsFragment.this.F().vitalsMainData.tvVitalMaxValue.setText(ThemesUtils.INSTANCE.getStringFormattedValueTillNDecimal(Double.valueOf(stepsWeeklyMonthlyData.getAvgCalorie()), 2));
            }
        }
    }

    /* loaded from: classes4.dex */
    public static final class c extends Lambda implements Function1<DailyWalkData, Unit> {
        public final /* synthetic */ InsightsStepsInitialModel $insightsInitialModel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(InsightsStepsInitialModel insightsStepsInitialModel) {
            super(1);
            this.$insightsInitialModel = insightsStepsInitialModel;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DailyWalkData dailyWalkData) {
            invoke2(dailyWalkData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DailyWalkData dailyWalkData) {
            if (dailyWalkData != null) {
                this.$insightsInitialModel.setDistanceCurrent(dailyWalkData.getMeters());
                this.$insightsInitialModel.setCaloriesCurrent((int) dailyWalkData.getCalories());
                this.$insightsInitialModel.setStepsCurrent(dailyWalkData.getValue());
                return;
            }
            this.$insightsInitialModel.setDistanceCurrent(0.0d);
            this.$insightsInitialModel.setCaloriesCurrent(0);
            this.$insightsInitialModel.setStepsCurrent(0);
        }
    }

    /* loaded from: classes4.dex */
    public static final class d extends Lambda implements Function1<DailyWalkData, Unit> {
        public final /* synthetic */ InsightsStepsInitialModel $insightsInitialModel;
        public final /* synthetic */ VitalStepsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(InsightsStepsInitialModel insightsStepsInitialModel, VitalStepsFragment vitalStepsFragment) {
            super(1);
            this.$insightsInitialModel = insightsStepsInitialModel;
            this.this$0 = vitalStepsFragment;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(DailyWalkData dailyWalkData) {
            invoke2(dailyWalkData);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable DailyWalkData dailyWalkData) {
            if (dailyWalkData != null) {
                this.$insightsInitialModel.setDistancePrevious(dailyWalkData.getMeters());
                this.$insightsInitialModel.setCaloriesPrevious((int) dailyWalkData.getCalories());
                this.$insightsInitialModel.setStepsPrevious(dailyWalkData.getValue());
            } else {
                this.$insightsInitialModel.setDistancePrevious(0.0d);
                this.$insightsInitialModel.setCaloriesPrevious(0);
                this.$insightsInitialModel.setStepsPrevious(0);
            }
            this.this$0.E(this.$insightsInitialModel);
        }
    }

    /* loaded from: classes4.dex */
    public static final class e extends Lambda implements Function1<InsightsStepsInitialModel, Unit> {
        public final /* synthetic */ InsightsStepsInitialModel $insightsInitialModel;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(InsightsStepsInitialModel insightsStepsInitialModel) {
            super(1);
            this.$insightsInitialModel = insightsStepsInitialModel;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InsightsStepsInitialModel insightsStepsInitialModel) {
            invoke2(insightsStepsInitialModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(InsightsStepsInitialModel insightsStepsInitialModel) {
            if (VitalStepsFragment.this.w != VitalStepsFragment.this.x) {
                this.$insightsInitialModel.setStepsCurrent(insightsStepsInitialModel.getStepsCurrent());
                this.$insightsInitialModel.setCaloriesCurrent(insightsStepsInitialModel.getCaloriesCurrent());
                this.$insightsInitialModel.setDistanceCurrent(insightsStepsInitialModel.getDistanceCurrent());
                this.$insightsInitialModel.setTotalNoOfDays(insightsStepsInitialModel.getTotalNoOfDays());
                this.$insightsInitialModel.setStepsPrevious(insightsStepsInitialModel.getStepsPrevious());
                this.$insightsInitialModel.setCaloriesPrevious(insightsStepsInitialModel.getCaloriesPrevious());
                this.$insightsInitialModel.setDistancePrevious(insightsStepsInitialModel.getDistancePrevious());
            }
        }
    }

    /* loaded from: classes4.dex */
    public static final class f extends Lambda implements Function1<InsightStepsHourModel, Unit> {
        public final /* synthetic */ InsightsStepsInitialModel $insightsInitialModel;
        public final /* synthetic */ VitalStepsFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(InsightsStepsInitialModel insightsStepsInitialModel, VitalStepsFragment vitalStepsFragment) {
            super(1);
            this.$insightsInitialModel = insightsStepsInitialModel;
            this.this$0 = vitalStepsFragment;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(InsightStepsHourModel insightStepsHourModel) {
            invoke2(insightStepsHourModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(InsightStepsHourModel insightStepsHourModel) {
            if (insightStepsHourModel.isGoalAchieved()) {
                this.$insightsInitialModel.setTotalTimeCurrent(insightStepsHourModel.getTimeTakenInMinutes());
            } else {
                this.$insightsInitialModel.setTotalTimeCurrent(0);
            }
            this.this$0.E(this.$insightsInitialModel);
        }
    }

    public VitalStepsFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.q = calendar;
        this.v = new ArrayList();
        this.y = 1;
        this.z = 2;
    }

    public static final void B(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void D(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LeaderBoardNavigator.navigateToAchievementsHomeScreen(this$0.requireActivity(), 0);
    }

    public static final void M(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.U(this$0.G());
        int i = this$0.w;
        if (i == this$0.x) {
            if (!DateUtils.isToday(this$0.q.getTimeInMillis())) {
                this$0.I(PayUtils.INSTANCE.getNextDayCalendar(this$0.q));
            }
        } else {
            FragmentStepsViewModel fragmentStepsViewModel = null;
            if (i == this$0.y) {
                Pair<? extends Calendar, ? extends Calendar> pair = this$0.r;
                if (pair == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair = null;
                }
                if (!DateUtils.isToday(pair.getSecond().getTimeInMillis())) {
                    Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.r;
                    if (pair2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                        pair2 = null;
                    }
                    Object clone = pair2.getSecond().clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                    Calendar calendar = (Calendar) clone;
                    Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.r;
                    if (pair3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                        pair3 = null;
                    }
                    Object clone2 = pair3.getSecond().clone();
                    Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                    Calendar calendar2 = (Calendar) clone2;
                    calendar2.add(5, 6);
                    if (calendar2.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                        this$0.r = new Pair<>(calendar, calendar2);
                        this$0.H(calendar, calendar2);
                        this$0.h0();
                        FragmentStepsViewModel fragmentStepsViewModel2 = this$0.n;
                        if (fragmentStepsViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            fragmentStepsViewModel = fragmentStepsViewModel2;
                        }
                        fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar, calendar2);
                    } else {
                        int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
                        Object clone3 = calendar.clone();
                        Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
                        Calendar calendar3 = (Calendar) clone3;
                        calendar3.add(5, 6 - findDateDifference);
                        this$0.r = new Pair<>(calendar, calendar3);
                        this$0.H(calendar, calendar3);
                        FragmentStepsViewModel fragmentStepsViewModel3 = this$0.n;
                        if (fragmentStepsViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            fragmentStepsViewModel = fragmentStepsViewModel3;
                        }
                        fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar, calendar3);
                        this$0.h0();
                    }
                }
            } else if (i == this$0.z) {
                Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.r;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                if (!DateUtils.isToday(pair4.getSecond().getTimeInMillis())) {
                    Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.r;
                    if (pair5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                        pair5 = null;
                    }
                    Object clone4 = pair5.getSecond().clone();
                    Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
                    Calendar calendar4 = (Calendar) clone4;
                    Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.r;
                    if (pair6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                        pair6 = null;
                    }
                    Object clone5 = pair6.getSecond().clone();
                    Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
                    Calendar calendar5 = (Calendar) clone5;
                    calendar5.add(5, 30);
                    if (calendar5.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                        this$0.r = new Pair<>(calendar4, calendar5);
                        this$0.H(calendar4, calendar5);
                        this$0.h0();
                        FragmentStepsViewModel fragmentStepsViewModel4 = this$0.n;
                        if (fragmentStepsViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            fragmentStepsViewModel = fragmentStepsViewModel4;
                        }
                        fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar4, calendar5);
                    } else {
                        int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
                        Object clone6 = calendar4.clone();
                        Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
                        Calendar calendar6 = (Calendar) clone6;
                        calendar6.add(5, 30 - findDateDifference2);
                        this$0.r = new Pair<>(calendar4, calendar6);
                        this$0.H(calendar4, calendar6);
                        this$0.h0();
                        FragmentStepsViewModel fragmentStepsViewModel5 = this$0.n;
                        if (fragmentStepsViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        } else {
                            fragmentStepsViewModel = fragmentStepsViewModel5;
                        }
                        fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar4, calendar6);
                    }
                }
            }
        }
        if (this$0.w != this$0.x) {
            if (this$0.p) {
                this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.avg_mi));
            } else {
                this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.avg_kms));
            }
        }
    }

    public static final void N(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireContext().startActivity(new Intent(this$0.requireContext(), ActivityStepsGoalSettings.class));
    }

    public static final void O(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void P(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.U(this$0.G());
        this$0.w = this$0.x;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.I(calendar);
        this$0.h0();
        ConstraintLayout constraintLayout = this$0.F().vitalsMainData.clStepsSleepsTotalAvg;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.vitalsMainData.clStepsSleepsTotalAvg");
        this$0.gone(constraintLayout);
        if (this$0.p) {
            this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.mi_dot));
        } else {
            this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.kms_nw));
        }
        this$0.F().vitalsMainData.tvVitalMax.setText(this$0.getString(R.string.cal));
        ConstraintLayout constraintLayout2 = this$0.F().vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.vitalsMainData.clCenterDataStepsSleep");
        this$0.visible(constraintLayout2);
        ConstraintLayout constraintLayout3 = this$0.F().vitalsMainData.clCenterDataAVGStepSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.vitalsMainData.clCenterDataAVGStepSleep");
        this$0.gone(constraintLayout3);
    }

    public static final void Q(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentStepsViewModel fragmentStepsViewModel = this$0.n;
        FragmentStepsViewModel fragmentStepsViewModel2 = null;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        fragmentStepsViewModel.setIsWeekSelected(true);
        this$0.U(this$0.G());
        this$0.w = this$0.y;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.r = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.H(startDate, endDate);
        this$0.h0();
        this$0.V(true);
        FragmentStepsViewModel fragmentStepsViewModel3 = this$0.n;
        if (fragmentStepsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStepsViewModel2 = fragmentStepsViewModel3;
        }
        fragmentStepsViewModel2.getWeekAndMonthWiseHourlyData(startDate, endDate);
    }

    public static final void R(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentStepsViewModel fragmentStepsViewModel = this$0.n;
        FragmentStepsViewModel fragmentStepsViewModel2 = null;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        fragmentStepsViewModel.setIsWeekSelected(false);
        this$0.U(this$0.G());
        this$0.w = this$0.z;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.r = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.H(startDate, endDate);
        this$0.h0();
        this$0.V(false);
        FragmentStepsViewModel fragmentStepsViewModel3 = this$0.n;
        if (fragmentStepsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStepsViewModel2 = fragmentStepsViewModel3;
        }
        fragmentStepsViewModel2.getWeekAndMonthWiseHourlyData(startDate, endDate);
    }

    public static final void S(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.w;
        if (i == this$0.x) {
            this$0.X();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.y) {
            this$0.A = true;
            this$0.b0();
        } else {
            this$0.A = false;
            this$0.b0();
        }
    }

    public static final void T(VitalStepsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.U(this$0.G());
        int i = this$0.w;
        if (i == this$0.x) {
            this$0.I(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.q));
        } else {
            FragmentStepsViewModel fragmentStepsViewModel = null;
            if (i == this$0.y) {
                if (this$0.r == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                }
                Pair<? extends Calendar, ? extends Calendar> pair = this$0.r;
                if (pair == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair = null;
                }
                Object clone = pair.getFirst().clone();
                Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar = (Calendar) clone;
                calendar.add(5, -6);
                Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.r;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                Object clone2 = pair2.getFirst().clone();
                Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar2 = (Calendar) clone2;
                this$0.r = new Pair<>(calendar, calendar2);
                FragmentStepsViewModel fragmentStepsViewModel2 = this$0.n;
                if (fragmentStepsViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentStepsViewModel = fragmentStepsViewModel2;
                }
                fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar, calendar2);
                this$0.H(calendar, calendar2);
                this$0.h0();
            } else if (i == this$0.z) {
                Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.r;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair3 = null;
                }
                Object clone3 = pair3.getFirst().clone();
                Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar3 = (Calendar) clone3;
                calendar3.add(5, -30);
                Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.r;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                Object clone4 = pair4.getFirst().clone();
                Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar4 = (Calendar) clone4;
                this$0.r = new Pair<>(calendar3, calendar4);
                FragmentStepsViewModel fragmentStepsViewModel3 = this$0.n;
                if (fragmentStepsViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    fragmentStepsViewModel = fragmentStepsViewModel3;
                }
                fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(calendar3, calendar4);
                this$0.H(calendar3, calendar4);
                this$0.h0();
            }
        }
        if (this$0.w != this$0.x) {
            if (this$0.p) {
                this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.avg_mi));
            } else {
                this$0.F().vitalsMainData.tvVitalMin.setText(this$0.getString(R.string.avg_kms));
            }
        }
    }

    public static final void Y(VitalStepsFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.I(newDate);
    }

    public static final void d0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void f0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void g0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void A() {
        FragmentStepsViewModel fragmentStepsViewModel = this.n;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        MutableLiveData<MyBadgesModel> myBadges = fragmentStepsViewModel.getMyBadges();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        myBadges.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.t
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.B(Function1.this, obj);
            }
        });
    }

    public final void C() {
        FragmentStepsViewModel fragmentStepsViewModel = this.n;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        MutableLiveData<StepsWeeklyMonthlyData> avgStepsData = fragmentStepsViewModel.getAvgStepsData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        avgStepsData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.w
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.D(Function1.this, obj);
            }
        });
    }

    public final void E(InsightsStepsInitialModel insightsStepsInitialModel) {
        int totalTimeCurrent;
        String sb;
        InsightsStepsDetailsModel insightsStepsDetailsModel = new InsightsStepsDetailsModel(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        insightsStepsDetailsModel.setStepsWalked(String.valueOf(insightsStepsInitialModel.getStepsPrevious()));
        insightsStepsDetailsModel.setStepsPercentage(Double.valueOf(insightsStepsInitialModel.getStepsCurrent() - insightsStepsInitialModel.getStepsPrevious()));
        insightsStepsDetailsModel.setStepsDecreased(Boolean.valueOf(insightsStepsInitialModel.getStepsCurrent() <= insightsStepsInitialModel.getStepsPrevious()));
        if (this.w != this.x) {
            insightsStepsDetailsModel.setDistanceWalked(String.valueOf(insightsStepsInitialModel.getDistancePrevious()));
            insightsStepsDetailsModel.setDistancePercentage(Double.valueOf(Double.parseDouble(ThemesUtils.INSTANCE.getStringFormattedValueTillNDecimal(Double.valueOf(insightsStepsInitialModel.getDistanceCurrent() - insightsStepsInitialModel.getDistancePrevious()), 1))));
        } else {
            PayUtils payUtils = PayUtils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            insightsStepsDetailsModel.setDistanceWalked(payUtils.getDistanceValueDouble(requireContext, insightsStepsInitialModel.getDistancePrevious()));
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            insightsStepsDetailsModel.setDistancePercentage(Double.valueOf(payUtils.getDistanceDifference(requireContext2, insightsStepsInitialModel.getDistanceCurrent(), insightsStepsInitialModel.getDistancePrevious())));
        }
        insightsStepsDetailsModel.setDistanceDecreased(Boolean.valueOf(insightsStepsInitialModel.getDistanceCurrent() <= insightsStepsInitialModel.getDistancePrevious()));
        insightsStepsDetailsModel.setCaloriesBurnt(String.valueOf(insightsStepsInitialModel.getCaloriesPrevious()));
        insightsStepsDetailsModel.setCaloriesPercentage(Double.valueOf(insightsStepsInitialModel.getCaloriesCurrent() - insightsStepsInitialModel.getCaloriesPrevious()));
        insightsStepsDetailsModel.setCaloriesDecreased(Boolean.valueOf(insightsStepsInitialModel.getCaloriesCurrent() <= insightsStepsInitialModel.getCaloriesPrevious()));
        if (insightsStepsInitialModel.getTotalNoOfDays() > 0) {
            totalTimeCurrent = insightsStepsInitialModel.getTotalTimeCurrent() / insightsStepsInitialModel.getTotalNoOfDays();
        } else {
            totalTimeCurrent = insightsStepsInitialModel.getTotalTimeCurrent();
        }
        ArrayList<InsightsStepsDataModel> arrayList = new ArrayList<>();
        int i = this.w;
        Double distancePercentage = insightsStepsDetailsModel.getDistancePercentage();
        Intrinsics.checkNotNull(distancePercentage);
        double doubleValue = distancePercentage.doubleValue();
        Boolean isDistanceDecreased = insightsStepsDetailsModel.isDistanceDecreased();
        Intrinsics.checkNotNull(isDistanceDecreased);
        boolean booleanValue = isDistanceDecreased.booleanValue();
        String string = getString(R.string.distance);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.distance)");
        arrayList.add(new InsightsStepsDataModel(true, i, doubleValue, booleanValue, string, String.valueOf(insightsStepsDetailsModel.getDistanceWalked()), "", ""));
        int i2 = this.w;
        Double caloriesPercentage = insightsStepsDetailsModel.getCaloriesPercentage();
        Intrinsics.checkNotNull(caloriesPercentage);
        double doubleValue2 = caloriesPercentage.doubleValue();
        Boolean isCaloriesDecreased = insightsStepsDetailsModel.isCaloriesDecreased();
        Intrinsics.checkNotNull(isCaloriesDecreased);
        boolean booleanValue2 = isCaloriesDecreased.booleanValue();
        String valueOf = String.valueOf(insightsStepsDetailsModel.getCaloriesBurnt());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getString(R.string.calories_burnt));
        sb2.append(' ');
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        int i3 = this.w;
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        sb2.append(themesUtils.getYestWeekMonthText(i3, requireContext3));
        String sb3 = sb2.toString();
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getString(R.string.your_calories_burnt_compared_to));
        sb4.append(' ');
        int i4 = this.w;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        sb4.append(themesUtils.getYestWeekMonthText(i4, requireContext4));
        arrayList.add(new InsightsStepsDataModel(true, i2, doubleValue2, booleanValue2, "", valueOf, sb3, sb4.toString()));
        int i5 = this.w;
        Double stepsPercentage = insightsStepsDetailsModel.getStepsPercentage();
        Intrinsics.checkNotNull(stepsPercentage);
        double doubleValue3 = stepsPercentage.doubleValue();
        Boolean isStepsDecreased = insightsStepsDetailsModel.isStepsDecreased();
        Intrinsics.checkNotNull(isStepsDecreased);
        boolean booleanValue3 = isStepsDecreased.booleanValue();
        String valueOf2 = String.valueOf(insightsStepsDetailsModel.getStepsWalked());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(getString(R.string.steps_walked));
        sb5.append(' ');
        int i6 = this.w;
        Context requireContext5 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
        sb5.append(themesUtils.getYestWeekMonthText(i6, requireContext5));
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(getString(R.string.your_steps_compared_to));
        sb7.append(' ');
        int i7 = this.w;
        Context requireContext6 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
        sb7.append(themesUtils.getYestWeekMonthText(i7, requireContext6));
        arrayList.add(new InsightsStepsDataModel(true, i5, doubleValue3, booleanValue3, "", valueOf2, sb6, sb7.toString()));
        int i8 = this.w;
        double d2 = totalTimeCurrent;
        String string2 = getString(i8 == this.x ? R.string.hour : R.string.avg_hour);
        Intrinsics.checkNotNullExpressionValue(string2, "if(mSelectedButton == DA…String(R.string.avg_hour)");
        if (this.w == this.x) {
            sb = getString(R.string.time_you_spent_to_reach_daily_steps_goal);
        } else {
            StringBuilder sb8 = new StringBuilder();
            sb8.append(getString(R.string.time_you_spent_to_reach_daily_steps_goal));
            sb8.append(' ');
            int i9 = this.w;
            Context requireContext7 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext7, "requireContext()");
            sb8.append(themesUtils.getYestWeekMonthText(i9, requireContext7));
            sb = sb8.toString();
        }
        Intrinsics.checkNotNullExpressionValue(sb, "if(mSelectedButton == DA…tton, requireContext())}\"");
        arrayList.add(new InsightsStepsDataModel(true, i8, d2, true, string2, "", "", sb));
        U(arrayList);
        Z();
    }

    public final FragmentVitalStepsBinding F() {
        FragmentVitalStepsBinding fragmentVitalStepsBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalStepsBinding);
        return fragmentVitalStepsBinding;
    }

    public final ArrayList<InsightsStepsDataModel> G() {
        ArrayList<InsightsStepsDataModel> arrayList = new ArrayList<>();
        int i = this.w;
        String string = getString(R.string.distance);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.distance)");
        arrayList.add(new InsightsStepsDataModel(true, i, 0.0d, false, string, BleConst.GetDeviceTime, "", ""));
        int i2 = this.w;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.calories_burnt));
        sb.append(' ');
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        int i3 = this.w;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(themesUtils.getYestWeekMonthText(i3, requireContext));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getString(R.string.your_calories_burnt_compared_to));
        sb3.append(' ');
        int i4 = this.w;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        sb3.append(themesUtils.getYestWeekMonthText(i4, requireContext2));
        arrayList.add(new InsightsStepsDataModel(true, i2, 0.0d, false, "", BleConst.GetDeviceTime, sb2, sb3.toString()));
        int i5 = this.w;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getString(R.string.steps_walked));
        sb4.append(' ');
        int i6 = this.w;
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        sb4.append(themesUtils.getYestWeekMonthText(i6, requireContext3));
        String sb5 = sb4.toString();
        StringBuilder sb6 = new StringBuilder();
        sb6.append(getString(R.string.your_steps_compared_to));
        sb6.append(' ');
        int i7 = this.w;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        sb6.append(themesUtils.getYestWeekMonthText(i7, requireContext4));
        arrayList.add(new InsightsStepsDataModel(true, i5, 0.0d, false, "", BleConst.GetDeviceTime, sb5, sb6.toString()));
        int i8 = this.w;
        String string2 = getString(R.string.hour);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.hour)");
        String string3 = getString(R.string.time_you_spent_to_reach_daily_steps_goal);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.time_…o_reach_daily_steps_goal)");
        arrayList.add(new InsightsStepsDataModel(true, i8, 0.0d, false, string2, "", "", string3));
        return arrayList;
    }

    public final void H(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                F().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                F().vitalsMainData.ibForward.setEnabled(false);
            } else {
                F().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                F().vitalsMainData.ibForward.setEnabled(true);
            }
            TextView textView = F().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            a0();
            FragmentStepsViewModel fragmentStepsViewModel = this.n;
            if (fragmentStepsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStepsViewModel = null;
            }
            fragmentStepsViewModel.selectWeeklyView(calendar, calendar2);
        }
    }

    public final void I(Calendar calendar) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        if (isAdded()) {
            TextView textView = F().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                F().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                F().vitalsMainData.ibForward.setEnabled(false);
            } else {
                F().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                F().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentStepsViewModel fragmentStepsViewModel = this.n;
            Boolean bool = null;
            if (fragmentStepsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStepsViewModel = null;
            }
            fragmentStepsViewModel.loadHourlyData(calendar);
            FragmentStepsViewModel fragmentStepsViewModel2 = this.n;
            if (fragmentStepsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStepsViewModel2 = null;
            }
            fragmentStepsViewModel2.loadDailData(calendar);
            this.q = calendar;
            BleApi bleApi = BleApiManager.getInstance(requireContext()).getBleApi();
            if (bleApi != null && (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) != null) {
                bool = Boolean.valueOf(deviceSupportedFeatures.isSedentaryAlertHistorySupported());
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                K();
            }
        }
    }

    public final void K() {
        if (isAdded()) {
            SedentaryDBRepo companion = SedentaryDBRepo.Companion.getInstance(requireContext());
            Integer readCountOfSedentaryAlertsForDate = companion != null ? companion.readCountOfSedentaryAlertsForDate(this.q) : null;
            if (readCountOfSedentaryAlertsForDate != null && readCountOfSedentaryAlertsForDate.intValue() > 0) {
                F().tvSedentaryTimesValue.setText(readCountOfSedentaryAlertsForDate.toString());
            } else {
                F().tvSedentaryTimesValue.setText("--");
            }
        }
    }

    public final void L() {
        FragmentVitalStepsBinding F = F();
        F.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.S(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.T(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.M(VitalStepsFragment.this, view);
            }
        });
        F.tvEditVitalNameGoal.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.N(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.O(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.P(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.Q(VitalStepsFragment.this, view);
            }
        });
        F.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.R(VitalStepsFragment.this, view);
            }
        });
    }

    public final void U(ArrayList<InsightsStepsDataModel> arrayList) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.s = new VitalStepsInsightsAdapter(requireContext);
        RecyclerView recyclerView = F().rvInsights;
        VitalStepsInsightsAdapter vitalStepsInsightsAdapter = this.s;
        VitalStepsInsightsAdapter vitalStepsInsightsAdapter2 = null;
        if (vitalStepsInsightsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vitalInsightsAdapter");
            vitalStepsInsightsAdapter = null;
        }
        recyclerView.setAdapter(vitalStepsInsightsAdapter);
        F().rvInsights.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        VitalStepsInsightsAdapter vitalStepsInsightsAdapter3 = this.s;
        if (vitalStepsInsightsAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vitalInsightsAdapter");
        } else {
            vitalStepsInsightsAdapter2 = vitalStepsInsightsAdapter3;
        }
        vitalStepsInsightsAdapter2.setInsightList(arrayList);
    }

    public final void V(boolean z) {
        ConstraintLayout constraintLayout = F().vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout);
        if (this.p) {
            F().vitalsMainData.tvVitalMin.setText(getString(R.string.avg_mi));
        } else {
            F().vitalsMainData.tvVitalMin.setText(getString(R.string.avg_kms));
        }
        F().vitalsMainData.tvVitalMax.setText(getString(R.string.avg_cal));
        ConstraintLayout constraintLayout2 = F().vitalsMainData.clCenterDataAVGStepSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.vitalsMainData.clCenterDataAVGStepSleep");
        visible(constraintLayout2);
        ConstraintLayout constraintLayout3 = F().vitalsMainData.clStepsSleepsTotalAvg;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.vitalsMainData.clStepsSleepsTotalAvg");
        visible(constraintLayout3);
        F().vitalsMainData.tvAvgStepSleepTitle.setText(getString(z ? R.string.total_steps_taken_week : R.string.total_steps_taken_month));
    }

    public final void W(List<? extends DailyWalkData> list) {
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i = 0;
        for (DailyWalkData dailyWalkData : list) {
            i += dailyWalkData.getValue();
            d3 += dailyWalkData.getCalories();
            d2 += dailyWalkData.getMeters();
        }
        F().vitalsMainData.tvStepSleepTotalValue.setText(String.valueOf(i));
        F().vitalsMainData.tvAvgStepSleepValue.setText(String.valueOf(i));
        F().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(i));
        TextView textView = F().vitalsMainData.tvVitalMinValue;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        textView.setText(payUtils.getDistanceValueDouble(requireContext, d2));
        F().vitalsMainData.tvVitalMaxValue.setText(payUtils.getCaloriesValue(d3));
        if (this.p) {
            F().vitalsMainData.tvVitalMin.setText(getString(R.string.mi_dot));
        } else {
            F().vitalsMainData.tvVitalMin.setText(getString(R.string.kms_nw));
        }
        if (this.w != this.x) {
            if (this.p) {
                F().vitalsMainData.tvVitalMin.setText(getString(R.string.avg_mi));
            } else {
                F().vitalsMainData.tvVitalMin.setText(getString(R.string.avg_kms));
            }
        }
        if (UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData() == null || UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target == null) {
            return;
        }
        int intValue = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target.intValue();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Pair<? extends Calendar, ? extends Calendar> pair = this.r;
        Pair<? extends Calendar, ? extends Calendar> pair2 = null;
        if (pair == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            pair = null;
        }
        Calendar first = pair.getFirst();
        Pair<? extends Calendar, ? extends Calendar> pair3 = this.r;
        if (pair3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
        } else {
            pair2 = pair3;
        }
        int dateDifference = intValue * themesUtils.getDateDifference(first, pair2.getSecond());
        if (dateDifference > 0) {
            F().vitalsMainData.progressBar.setProgress((i * 100) / dateDifference);
        } else {
            F().vitalsMainData.progressBar.setProgress(0);
        }
    }

    public final void X() {
        Calendar calendar = this.q;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.s
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalStepsFragment.Y(VitalStepsFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void Z() {
        F().tvInsightsHeader.setText(new String[]{getString(R.string.daily_insights), getString(R.string.weekly_insights), getString(R.string.monthly_insights)}[this.w]);
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void a0() {
        FragmentVitalStepsBinding F = F();
        if (UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData() == null || UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target == null) {
            return;
        }
        int i = this.w;
        if (i == this.x) {
            F.vitalsMainData.tvOutOfStepsSleepValue.setText(getString(R.string.out_of) + ' ' + UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target);
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this.y) {
            TextView textView = F.vitalsMainData.tvOutOfStepsSleepValue;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.out_of));
            sb.append(' ');
            int intValue = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target.intValue();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair2 = this.r;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Calendar first = pair2.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair3 = this.r;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            sb.append(intValue * themesUtils.getDateDifference(first, pair.getSecond()));
            textView.setText(sb.toString());
        } else if (i == this.z) {
            TextView textView2 = F.vitalsMainData.tvOutOfStepsSleepValue;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.out_of));
            sb2.append(' ');
            int intValue2 = UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target.intValue();
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this.r;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Calendar first2 = pair4.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair5 = this.r;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            sb2.append(intValue2 * themesUtils2.getDateDifference(first2, pair.getSecond()));
            textView2.setText(sb2.toString());
        }
    }

    public final void b0() {
        if (isAdded()) {
            boolean z = this.A;
            Pair<? extends Calendar, ? extends Calendar> pair = this.r;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.t = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void c0() {
        InsightsStepsInitialModel insightsStepsInitialModel = new InsightsStepsInitialModel(0.0d, 0.0d, 0, 0, 0, 0, 0, 0);
        FragmentStepsViewModel fragmentStepsViewModel = this.n;
        FragmentStepsViewModel fragmentStepsViewModel2 = null;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        MutableLiveData<DailyWalkData> currentSingleDayStepData = fragmentStepsViewModel.getCurrentSingleDayStepData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final c cVar = new c(insightsStepsInitialModel);
        currentSingleDayStepData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.y
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.d0(Function1.this, obj);
            }
        });
        FragmentStepsViewModel fragmentStepsViewModel3 = this.n;
        if (fragmentStepsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel3 = null;
        }
        MutableLiveData<DailyWalkData> previousSingleDayStepData = fragmentStepsViewModel3.getPreviousSingleDayStepData();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final d dVar = new d(insightsStepsInitialModel, this);
        previousSingleDayStepData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.v
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.e0(Function1.this, obj);
            }
        });
        FragmentStepsViewModel fragmentStepsViewModel4 = this.n;
        if (fragmentStepsViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel4 = null;
        }
        MutableLiveData<InsightsStepsInitialModel> currentPreviousWeekOrMonthStepsData = fragmentStepsViewModel4.getCurrentPreviousWeekOrMonthStepsData();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        final e eVar = new e(insightsStepsInitialModel);
        currentPreviousWeekOrMonthStepsData.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.u
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.f0(Function1.this, obj);
            }
        });
        FragmentStepsViewModel fragmentStepsViewModel5 = this.n;
        if (fragmentStepsViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStepsViewModel2 = fragmentStepsViewModel5;
        }
        MutableLiveData<InsightStepsHourModel> timeRequiredForStepGoalAchieved = fragmentStepsViewModel2.getTimeRequiredForStepGoalAchieved();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        final f fVar = new f(insightsStepsInitialModel, this);
        timeRequiredForStepGoalAchieved.observe(viewLifecycleOwner4, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.x
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalStepsFragment.g0(Function1.this, obj);
            }
        });
    }

    @NotNull
    public final DatePickerDialog getDatePickerDialog() {
        DatePickerDialog datePickerDialog = this.datePickerDialog;
        if (datePickerDialog != null) {
            return datePickerDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("datePickerDialog");
        return null;
    }

    @NotNull
    public final SimpleDateFormat getSimpleDateFormat() {
        SimpleDateFormat simpleDateFormat = this.simpleDateFormat;
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        Intrinsics.throwUninitializedPropertyAccessException("simpleDateFormat");
        return null;
    }

    public final void h0() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        if (isAdded()) {
            a0();
            int i = this.w;
            Boolean bool = null;
            if (i == this.x) {
                F().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                F().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                F().vitalsMainData.tvWeek.setBackground(null);
                F().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                F().vitalsMainData.tvMonth.setBackground(null);
                F().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                F().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                F().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                F().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
                BleApi bleApi = BleApiManager.getInstance(requireContext()).getBleApi();
                Boolean valueOf = (bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures2.isSedentaryAlertHistorySupported());
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.booleanValue()) {
                    ConstraintLayout constraintLayout = F().clSedentaryTimes;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSedentaryTimes");
                    visible(constraintLayout);
                } else {
                    ConstraintLayout constraintLayout2 = F().clSedentaryTimes;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clSedentaryTimes");
                    gone(constraintLayout2);
                }
                BleApi bleApi2 = BleApiManager.getInstance(requireContext()).getBleApi();
                if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
                    bool = Boolean.valueOf(deviceSupportedFeatures.isActiveTimeSupported());
                }
                Intrinsics.checkNotNull(bool);
                if (bool.booleanValue()) {
                    ConstraintLayout constraintLayout3 = F().clActiveTimes;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.clActiveTimes");
                    visible(constraintLayout3);
                    return;
                }
                ConstraintLayout constraintLayout4 = F().clActiveTimes;
                Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.clActiveTimes");
                gone(constraintLayout4);
            } else if (i == this.y) {
                F().vitalsMainData.tvDay.setBackground(null);
                F().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                F().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                F().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                F().vitalsMainData.tvMonth.setBackground(null);
                F().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                ConstraintLayout constraintLayout5 = F().clSedentaryTimes;
                Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.clSedentaryTimes");
                gone(constraintLayout5);
                ConstraintLayout constraintLayout6 = F().clActiveTimes;
                Intrinsics.checkNotNullExpressionValue(constraintLayout6, "binding.clActiveTimes");
                gone(constraintLayout6);
                F().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                F().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                F().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
            } else {
                F().vitalsMainData.tvDay.setBackground(null);
                F().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                F().vitalsMainData.tvWeek.setBackground(null);
                F().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                F().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                F().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                ConstraintLayout constraintLayout7 = F().clSedentaryTimes;
                Intrinsics.checkNotNullExpressionValue(constraintLayout7, "binding.clSedentaryTimes");
                gone(constraintLayout7);
                ConstraintLayout constraintLayout8 = F().clActiveTimes;
                Intrinsics.checkNotNullExpressionValue(constraintLayout8, "binding.clActiveTimes");
                gone(constraintLayout8);
                F().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                F().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                F().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
            }
        }
    }

    public final void initData() {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        this.p = isDistanceUnitInMile.booleanValue() && BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isDistanceUnitSettingsSupported();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentStepsViewModel fragmentStepsViewModel = (FragmentStepsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentStepsViewModel.class);
        this.n = fragmentStepsViewModel;
        Boolean bool = null;
        if (fragmentStepsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel = null;
        }
        fragmentStepsViewModel.setContractFitnessDashBoard(this);
        FragmentStepsViewModel fragmentStepsViewModel2 = this.n;
        if (fragmentStepsViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel2 = null;
        }
        fragmentStepsViewModel2.setMLifecycleOwner(this);
        FragmentStepsViewModel fragmentStepsViewModel3 = this.n;
        if (fragmentStepsViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStepsViewModel3 = null;
        }
        fragmentStepsViewModel3.m105getMyBadges();
        F().tvAchievementsViewMore.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalStepsFragment.J(VitalStepsFragment.this, view);
            }
        });
        this.w = this.x;
        FragmentVitalStepsBinding F = F();
        F.vitalsMainData.tvVitalInfo.setVisibility(8);
        ConstraintLayout constraintLayout = F.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        visible(constraintLayout);
        F.vitalsMainData.tvVitalMax.setText(getString(R.string.cal));
        F.vitalsMainData.tvVitalName.setText(getString(R.string.steps));
        F.vitalsMainData.tvVitalName.setCompoundDrawables(null, null, null, null);
        if (this.p) {
            F.vitalsMainData.tvVitalMin.setText(getString(R.string.mi_dot));
        } else {
            F.vitalsMainData.tvVitalMin.setText(getString(R.string.kms_nw));
        }
        F.vitalsMainData.ivMin.setImageResource(R.drawable.ic_disatnce);
        F.vitalsMainData.ivMax.setImageResource(R.drawable.ic_calories);
        F.vitalsMainData.ivCenterVital.setImageResource(2131232553);
        TextView textView = F.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView.setText(payUtils.getLastSyncTime(requireContext2));
        F().vitalsMainData.progressBar.setVisibility(0);
        ImageView imageView = F().vitalsMainData.ivCenterVitalBg;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.vitalsMainData.ivCenterVitalBg");
        gone(imageView);
        F().vitalsMainData.progressBar.setBackground(requireContext().getDrawable(R.drawable.circular_grey_background));
        F().vitalsMainData.progressBar.setProgressDrawable(requireContext().getDrawable(R.drawable.progress_drawable_steps_thickness_5dp));
        BleApi bleApi = BleApiManager.getInstance(requireContext()).getBleApi();
        Boolean valueOf = (bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null) ? null : Boolean.valueOf(deviceSupportedFeatures2.isSedentaryAlertHistorySupported());
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.booleanValue()) {
            F().clSedentaryTimes.setVisibility(0);
            K();
        } else {
            F().clSedentaryTimes.setVisibility(8);
        }
        BleApi bleApi2 = BleApiManager.getInstance(requireContext()).getBleApi();
        if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null) {
            bool = Boolean.valueOf(deviceSupportedFeatures.isActiveTimeSupported());
        }
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            F().clActiveTimes.setVisibility(0);
        } else {
            F().clActiveTimes.setVisibility(8);
        }
        L();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        I(calendar);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard, com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new com.coveiot.android.dashboard2.ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog.OnCalendarRangeSelector
    public void onCalendarRangeSelected(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        this.r = new Pair<>(fromDate, toDate);
        H(fromDate, toDate);
        if (this.w != this.x) {
            FragmentStepsViewModel fragmentStepsViewModel = this.n;
            if (fragmentStepsViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentStepsViewModel = null;
            }
            fragmentStepsViewModel.getWeekAndMonthWiseHourlyData(fromDate, toDate);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalStepsBinding.inflate(inflater, viewGroup, false);
        View root = F().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void onDataLoaded(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, @NotNull List<? extends DailyWalkData> stepsList) {
        Intrinsics.checkNotNullParameter(stepsList, "stepsList");
        if (!AppUtils.isEmpty(arrayList)) {
            W(stepsList);
            Intrinsics.checkNotNull(arrayList);
            Intrinsics.checkNotNull(arrayList2);
            setGraphValues(arrayList, arrayList2, true);
            F().stepsGraph.setVisibility(0);
            F().tvNoDataFound.setVisibility(8);
            return;
        }
        F().vitalsMainData.tvStepSleepTotalValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvAvgStepsSleepValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvVitalMinValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvVitalMaxValue.setText(BleConst.GetDeviceTime);
        F().stepsGraph.setVisibility(8);
        F().tvNoDataFound.setVisibility(0);
        F().stepsGraph.clear();
        F().vitalsMainData.progressBar.setProgress(0);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.leaderboard.callbacks.IMyBadgeItemClick
    public void onMyBadgeItemClick(@Nullable MyBadgesModel.DataBean.BadgesBean badgesBean) {
        BadgesDetailsDialog badgesDetailsDialog;
        BadgesDetailsDialog badgesDetailsDialog2 = this.B;
        if (badgesDetailsDialog2 != null) {
            boolean z = true;
            if (badgesDetailsDialog2 == null || !badgesDetailsDialog2.isShowing()) {
                z = false;
            }
            if (z && (badgesDetailsDialog = this.B) != null) {
                badgesDetailsDialog.dismiss();
            }
            this.B = null;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        BadgesDetailsDialog badgesDetailsDialog3 = new BadgesDetailsDialog(requireActivity, badgesBean);
        this.B = badgesDetailsDialog3;
        badgesDetailsDialog3.setCancelableOutside(false);
        BadgesDetailsDialog badgesDetailsDialog4 = this.B;
        if (badgesDetailsDialog4 != null) {
            badgesDetailsDialog4.show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        a0();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        C();
        c0();
        A();
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setGraphValues(@NotNull ArrayList<BarEntry> entries, @NotNull final ArrayList<String> labels, boolean z) {
        int maximumYValue;
        Intrinsics.checkNotNullParameter(entries, "entries");
        Intrinsics.checkNotNullParameter(labels, "labels");
        BarDataSet barDataSet = new BarDataSet(entries, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(getResources().getColor(R.color.steps_graph_color));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);
        F().stepsGraph.setData(barData);
        F().stepsGraph.setFitBars(true);
        F().stepsGraph.setDrawValueAboveBar(false);
        F().stepsGraph.setDrawGridBackground(false);
        F().stepsGraph.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        F().stepsGraph.setDescription(description);
        barData.setValueTextColor(getResources().getColor(R.color.steps_graph_color));
        F().stepsGraph.getPaint(7).setColor(getResources().getColor(R.color.steps_graph_color));
        F().stepsGraph.setDrawBorders(false);
        F().stepsGraph.setAutoScaleMinMaxEnabled(false);
        Context context = getContext();
        String string = getString(R.string.steps);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.steps)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, this.w, labels);
        customMarkerViewVitals.setChartView(F().stepsGraph);
        F().stepsGraph.setMarker(customMarkerViewVitals);
        YAxis axisLeft = F().stepsGraph.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setYOffset(10.0f);
        axisLeft.setXOffset(15.0f);
        if (PayUtils.INSTANCE.getMaximumYValue(entries) == 0) {
            axisLeft.mAxisMaximum = 1000.0f;
        } else {
            axisLeft.mAxisMaximum = (maximumYValue * 150) / 100.0f;
        }
        axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
        F().stepsGraph.getAxisRight().setEnabled(false);
        XAxis xAxis = F().stepsGraph.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalStepsFragment$setGraphValues$1
            @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
            @NotNull
            public String getFormattedValue(float f2, @Nullable AxisBase axisBase) {
                int roundToInt;
                if (f2 >= 0.0f && (roundToInt = kotlin.math.c.roundToInt(f2)) >= 0 && roundToInt < labels.size() && roundToInt == ((int) f2)) {
                    String str = labels.get(roundToInt);
                    Intrinsics.checkNotNullExpressionValue(str, "labels[index]");
                    return str;
                }
                return "";
            }
        });
        xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
        F().stepsGraph.getAxisLeft().setStartAtZero(true);
        F().stepsGraph.getAxisRight().setStartAtZero(true);
        F().stepsGraph.setVisibleXRangeMaximum(24.0f);
        F().stepsGraph.setVisibleXRangeMinimum(7.0f);
        F().stepsGraph.getAxisLeft().setTextColor(getResources().getColor(R.color.color_757575));
        F().stepsGraph.getXAxis().setTextColor(getResources().getColor(R.color.color_757575));
        F().stepsGraph.getLegend().setTextColor(getResources().getColor(R.color.color_757575));
        F().stepsGraph.getLegend().setEnabled(false);
        F().stepsGraph.invalidate();
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setCal(F().vitalsMainData.tvVitalMaxValue.getText().toString());
        if (this.w != this.x) {
            shareData.setCal_unit(getResources().getString(R.string.avg_cal));
            shareData.setData(F().vitalsMainData.tvStepSleepTotalValue.getText().toString());
        } else {
            shareData.setCal_unit(getResources().getString(R.string.cal));
            shareData.setData(F().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
        }
        if (this.p) {
            shareData.setDistance(F().vitalsMainData.tvVitalMinValue.getText().toString());
            if (this.w != this.x) {
                shareData.setDistance_unit(getResources().getString(R.string.avg_mi));
            } else {
                shareData.setDistance_unit(getResources().getString(R.string.mi_dot));
            }
        } else {
            shareData.setDistance(F().vitalsMainData.tvVitalMinValue.getText().toString());
            if (this.w != this.x) {
                shareData.setDistance_unit(getResources().getString(R.string.avg_kms));
            } else {
                shareData.setDistance_unit(getString(R.string.kms_nw));
            }
        }
        int i = this.w;
        if (i == this.x) {
            shareData.setGraphType(getString(R.string.share_daily));
            shareData.setDwmValue(simpleDateFormat.format(this.q.getTime()));
        } else {
            Pair<? extends Calendar, ? extends Calendar> pair = null;
            if (i == this.y) {
                shareData.setGraphType(getString(R.string.share_weekly));
                StringBuilder sb = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair2 = this.r;
                if (pair2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair2 = null;
                }
                sb.append(simpleDateFormat.format(pair2.getFirst().getTime()));
                sb.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair3 = this.r;
                if (pair3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair3;
                }
                sb.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb.toString());
                shareData.setTotalValueTitle(getString(R.string.total_steps_taken_week));
            } else if (i == this.z) {
                shareData.setGraphType(getString(R.string.share_monthly));
                StringBuilder sb2 = new StringBuilder();
                Pair<? extends Calendar, ? extends Calendar> pair4 = this.r;
                if (pair4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                    pair4 = null;
                }
                sb2.append(simpleDateFormat.format(pair4.getFirst().getTime()));
                sb2.append(Soundex.SILENT_MARKER);
                Pair<? extends Calendar, ? extends Calendar> pair5 = this.r;
                if (pair5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                } else {
                    pair = pair5;
                }
                sb2.append(simpleDateFormat.format(pair.getSecond().getTime()));
                shareData.setDwmValue(sb2.toString());
                shareData.setTotalValueTitle(getString(R.string.total_steps_taken_month));
            }
        }
        shareData.setAvgData(F().vitalsMainData.tvAvgStepSleepValue.getText().toString());
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.fitness);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.fitness)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void updateDailyLevelData(@Nullable DailyWalkData dailyWalkData) {
        Integer num;
        if (dailyWalkData != null) {
            F().stepsGraph.setVisibility(0);
            F().tvNoDataFound.setVisibility(8);
            if (this.w == this.x && UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData() != null && UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target != null) {
                F().vitalsMainData.progressBar.setProgress((int) ((dailyWalkData.getValue() / (UserDataManager.getInstance(requireContext()).getFitnessGoalStepsData().target != null ? num.intValue() : 10000)) * 100));
            }
            F().vitalsMainData.tvAvgStepsSleepValue.setText(String.valueOf(dailyWalkData.getValue()));
            F().vitalsMainData.tvAvgStepSleepValue.setText(String.valueOf(dailyWalkData.getValue()));
            TextView textView = F().vitalsMainData.tvVitalMaxValue;
            PayUtils payUtils = PayUtils.INSTANCE;
            textView.setText(payUtils.getCaloriesValue(dailyWalkData.getCalories()));
            TextView textView2 = F().vitalsMainData.tvVitalMinValue;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView2.setText(payUtils.getDistanceValueDouble(requireContext, dailyWalkData.getMeters()));
            if (dailyWalkData.getActiveTime() != null) {
                F().tvActiveTimesValue.setText(String.valueOf(dailyWalkData.getActiveTime()));
            } else {
                F().tvActiveTimesValue.setText("--");
            }
            int value = dailyWalkData.getValue();
            String caloriesValue = AppUtils.getCaloriesValue(dailyWalkData.getCalories());
            Intrinsics.checkNotNullExpressionValue(caloriesValue, "getCaloriesValue(dailyWalkData.calories)");
            String valueOf = String.valueOf(dailyWalkData.getMeters());
            String str = dailyWalkData.getmDate();
            Intrinsics.checkNotNullExpressionValue(str, "dailyWalkData.getmDate()");
            this.o = new WalkData(value, caloriesValue, valueOf, str).setType("Day");
            F().vitalsMainData.tvSelectedTypeValue.setVisibility(0);
            return;
        }
        F().vitalsMainData.tvStepSleepTotalValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvAvgStepsSleepValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvVitalMinValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvVitalMaxValue.setText(BleConst.GetDeviceTime);
        F().vitalsMainData.tvAvgStepsSleepValue.setText(BleConst.GetDeviceTime);
        F().stepsGraph.setVisibility(8);
        F().tvNoDataFound.setVisibility(0);
        F().vitalsMainData.progressBar.setProgress(0);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractFitnessDashBoard
    public void updateHourlyLevelData(@Nullable ArrayList<BarEntry> arrayList, @Nullable ArrayList<String> arrayList2, boolean z) {
        if (!AppUtils.isEmpty(arrayList) && !AppUtils.isEmpty(arrayList2)) {
            Intrinsics.checkNotNull(arrayList);
            Intrinsics.checkNotNull(arrayList2);
            setGraphValues(arrayList, arrayList2, z);
            F().stepsGraph.setVisibility(0);
            F().tvNoDataFound.setVisibility(8);
            return;
        }
        F().stepsGraph.setVisibility(8);
        F().tvNoDataFound.setVisibility(0);
        F().stepsGraph.clear();
    }
}
