package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalSleepBinding;
import com.coveiot.android.dashboard2.util.SleepDataHelper;
import com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.dashboard.ActivityDashboardNew;
import com.coveiot.android.leonardo.dashboard.HealthNavigationElement;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.model.SleepData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepHistoryViewModel;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSleepViewModel;
import com.coveiot.android.leonardo.dashboard.vitals.model.SleepInsightsModel;
import com.coveiot.android.leonardo.more.activities.ActivitySleepGoalSettings;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateViewModel;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList;
import com.coveiot.android.sleepenergyscore.feedback.DismissLoader;
import com.coveiot.android.sleepenergyscore.feedback.PagerAdapterFeedback;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.SleepScoreEventData;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.SleepScoreFeedbackEventData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.adapter.TipsAdapter;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog;
import com.coveiot.android.theme.model.TipsModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covedb.sleep.DailySleepDataAlias;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.squareup.otto.Subscribe;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalsSleepFragment extends BaseFragment implements ContractSleepDashboard, ContractFeedBackQuestionsList, ContractRespiratoryRateDashBoard, CalendarRangeDialog.OnCalendarRangeSelector {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public Pair<? extends Calendar, ? extends Calendar> A;
    public Pair<? extends Calendar, ? extends Calendar> B;
    @NotNull
    public final int[] C;
    @NotNull
    public final int[] D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    @Nullable
    public Float K;
    @Nullable
    public Highlight L;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalSleepBinding m;
    public FragmentSleepViewModel n;
    public FragmentSleepHistoryViewModel o;
    @NotNull
    public Calendar p;
    @Nullable
    public RespiratoryRateViewModel q;
    @Nullable
    public SleepData r;
    public boolean s;
    public SimpleDateFormat simpleDateFormat;
    @Nullable
    public String t;
    public boolean u;
    public int v;
    public final int w;
    public final int x;
    public final int y;
    public CalendarRangeDialog z;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalsSleepFragment newInstance() {
            return new VitalsSleepFragment();
        }
    }

    /* loaded from: classes4.dex */
    public final class HoursFormattor implements IAxisValueFormatter {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final List<BarEntry> f4816a;
        @NotNull
        public DecimalFormat b;

        /* JADX WARN: Multi-variable type inference failed */
        public HoursFormattor(@NotNull VitalsSleepFragment vitalsSleepFragment, List<? extends BarEntry> mValues) {
            Intrinsics.checkNotNullParameter(mValues, "mValues");
            this.f4816a = mValues;
            this.b = new DecimalFormat("#.#");
        }

        @NotNull
        public final DecimalFormat getFormatDecimal() {
            return this.b;
        }

        @Override // com.github.mikephil.charting.formatter.IAxisValueFormatter
        @NotNull
        public String getFormattedValue(float f, @NotNull AxisBase axis) {
            Intrinsics.checkNotNullParameter(axis, "axis");
            this.b.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
            return this.b.format(Float.valueOf(f)) + " hr";
        }

        public final void setFormatDecimal(@NotNull DecimalFormat decimalFormat) {
            Intrinsics.checkNotNullParameter(decimalFormat, "<set-?>");
            this.b = decimalFormat;
        }
    }

    /* loaded from: classes4.dex */
    public static final class a extends Lambda implements Function1<Integer, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke2(num);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Integer num) {
            TextView textView = VitalsSleepFragment.this.D().vitalsMainData.tvAvgStepSleepValue;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = VitalsSleepFragment.this.getString(R.string.deepsleep_spannable);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.deepsleep_spannable)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(num.intValue() / 60), Integer.valueOf(num.intValue() % 60)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            textView.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        }
    }

    /* loaded from: classes4.dex */
    public static final class b extends Lambda implements Function1<SleepInsightsModel, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SleepInsightsModel sleepInsightsModel) {
            invoke2(sleepInsightsModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(SleepInsightsModel sleepInsightsModel) {
            VitalsSleepFragment.this.F = sleepInsightsModel != null ? sleepInsightsModel.getTotalSleep() : 0;
            VitalsSleepFragment.this.E = sleepInsightsModel != null ? sleepInsightsModel.getAwakeTime() : 0;
            VitalsSleepFragment.this.G = sleepInsightsModel != null ? sleepInsightsModel.getDeepSleep() : 0;
            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = VitalsSleepFragment.this.o;
            Pair pair = null;
            if (fragmentSleepHistoryViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                fragmentSleepHistoryViewModel = null;
            }
            Pair pair2 = VitalsSleepFragment.this.B;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("previousDateRange");
                pair2 = null;
            }
            Calendar calendar = (Calendar) pair2.getFirst();
            Pair pair3 = VitalsSleepFragment.this.B;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("previousDateRange");
            } else {
                pair = pair3;
            }
            fragmentSleepHistoryViewModel.queryRangeInsightsSleepDataFor(calendar, (Calendar) pair.getSecond(), false);
        }
    }

    /* loaded from: classes4.dex */
    public static final class c extends Lambda implements Function1<SleepInsightsModel, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(SleepInsightsModel sleepInsightsModel) {
            invoke2(sleepInsightsModel);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(SleepInsightsModel sleepInsightsModel) {
            VitalsSleepFragment.this.I = sleepInsightsModel != null ? sleepInsightsModel.getTotalSleep() : 0;
            VitalsSleepFragment.this.H = sleepInsightsModel != null ? sleepInsightsModel.getAwakeTime() : 0;
            VitalsSleepFragment.this.J = sleepInsightsModel != null ? sleepInsightsModel.getDeepSleep() : 0;
            VitalsSleepFragment.this.g0();
        }
    }

    /* loaded from: classes4.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {

        @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment$registerLiveData$1$1", f = "VitalsSleepFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes4.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            public final /* synthetic */ Boolean $it;
            public int label;
            public final /* synthetic */ VitalsSleepFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Boolean bool, VitalsSleepFragment vitalsSleepFragment, Continuation<? super a> continuation) {
                super(2, continuation);
                this.$it = bool;
                this.this$0 = vitalsSleepFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.$it, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    Boolean it = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (it.booleanValue()) {
                        RoundedBarChart roundedBarChart = this.this$0.D().sleepGraphChart;
                        if (roundedBarChart != null) {
                            roundedBarChart.invalidate();
                        }
                        RoundedBarChart roundedBarChart2 = this.this$0.D().sleepGraphChart;
                        if (roundedBarChart2 != null) {
                            roundedBarChart2.clear();
                        }
                        VitalsSleepFragment vitalsSleepFragment = this.this$0;
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = vitalsSleepFragment.o;
                        List<SleepDataModelForLastNight> list = null;
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel2 = null;
                        if (fragmentSleepHistoryViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                            fragmentSleepHistoryViewModel = null;
                        }
                        ArrayList<BarEntry> barEntries = fragmentSleepHistoryViewModel.getBarEntries();
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel3 = this.this$0.o;
                        if (fragmentSleepHistoryViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                            fragmentSleepHistoryViewModel3 = null;
                        }
                        ArrayList<String> barLabels = fragmentSleepHistoryViewModel3.getBarLabels();
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel4 = this.this$0.o;
                        if (fragmentSleepHistoryViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                            fragmentSleepHistoryViewModel4 = null;
                        }
                        boolean z = true;
                        vitalsSleepFragment.setBarGraphValues(barEntries, barLabels, fragmentSleepHistoryViewModel4.getBarEntries().size() > 0);
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel5 = this.this$0.o;
                        if (fragmentSleepHistoryViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                            fragmentSleepHistoryViewModel5 = null;
                        }
                        ArrayList<BarEntry> barEntries2 = fragmentSleepHistoryViewModel5.getBarEntries();
                        if (barEntries2 != null && !barEntries2.isEmpty()) {
                            z = false;
                        }
                        if (!z) {
                            VitalsSleepFragment vitalsSleepFragment2 = this.this$0;
                            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel6 = vitalsSleepFragment2.o;
                            if (fragmentSleepHistoryViewModel6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                                fragmentSleepHistoryViewModel6 = null;
                            }
                            HashMap<String, List<SleepDataModelForLastNight>> mLastQueriedSleepData = fragmentSleepHistoryViewModel6.getMLastQueriedSleepData();
                            if (mLastQueriedSleepData != null) {
                                FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel7 = this.this$0.o;
                                if (fragmentSleepHistoryViewModel7 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                                } else {
                                    fragmentSleepHistoryViewModel2 = fragmentSleepHistoryViewModel7;
                                }
                                list = mLastQueriedSleepData.get(fragmentSleepHistoryViewModel2.getLatestDateKey());
                            }
                            vitalsSleepFragment2.updateHourlyLevelData(list);
                        } else {
                            this.this$0.updateHourlyLevelData(null);
                        }
                        this.this$0.dismissProgress();
                    } else {
                        RoundedBarChart roundedBarChart3 = this.this$0.D().sleepGraphChart;
                        if (roundedBarChart3 != null) {
                            roundedBarChart3.invalidate();
                        }
                        RoundedBarChart roundedBarChart4 = this.this$0.D().sleepGraphChart;
                        if (roundedBarChart4 != null) {
                            roundedBarChart4.clear();
                        }
                        this.this$0.dismissProgress();
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean bool) {
            if (VitalsSleepFragment.this.isAdded()) {
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(VitalsSleepFragment.this), Dispatchers.getMain(), null, new a(bool, VitalsSleepFragment.this, null), 2, null);
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment$updateDailyLevelData$1", f = "VitalsSleepFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DailyRespiratoryRateEntity $dailyRespiratoryRate;
        public int label;
        public final /* synthetic */ VitalsSleepFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(DailyRespiratoryRateEntity dailyRespiratoryRateEntity, VitalsSleepFragment vitalsSleepFragment, Continuation<? super e> continuation) {
            super(2, continuation);
            this.$dailyRespiratoryRate = dailyRespiratoryRateEntity;
            this.this$0 = vitalsSleepFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(this.$dailyRespiratoryRate, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.$dailyRespiratoryRate;
                if (dailyRespiratoryRateEntity == null) {
                    TextView textView = this.this$0.D().tvRespiratoryRateMinVal;
                    AppConstants appConstants = AppConstants.TWO_DASH_STRING;
                    textView.setText(appConstants.getValue());
                    this.this$0.D().tvRespiratoryRateMaxVal.setText(appConstants.getValue());
                } else {
                    RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
                    if ((respiratoryRateData != null ? respiratoryRateData.getMin() : null) != null) {
                        RespiratoryRateData respiratoryRateData2 = this.$dailyRespiratoryRate.data;
                        Intrinsics.checkNotNull(respiratoryRateData2);
                        Integer min = respiratoryRateData2.getMin();
                        Intrinsics.checkNotNull(min);
                        if (min.intValue() > 0) {
                            RespiratoryRateData respiratoryRateData3 = this.$dailyRespiratoryRate.data;
                            if ((respiratoryRateData3 != null ? respiratoryRateData3.getMax() : null) != null) {
                                RespiratoryRateData respiratoryRateData4 = this.$dailyRespiratoryRate.data;
                                Intrinsics.checkNotNull(respiratoryRateData4);
                                Integer max = respiratoryRateData4.getMax();
                                Intrinsics.checkNotNull(max);
                                if (max.intValue() > 0) {
                                    TextView textView2 = this.this$0.D().tvRespiratoryRateMinVal;
                                    RespiratoryRateData respiratoryRateData5 = this.$dailyRespiratoryRate.data;
                                    Intrinsics.checkNotNull(respiratoryRateData5);
                                    textView2.setText(String.valueOf(respiratoryRateData5.getMin()));
                                    TextView textView3 = this.this$0.D().tvRespiratoryRateMaxVal;
                                    RespiratoryRateData respiratoryRateData6 = this.$dailyRespiratoryRate.data;
                                    Intrinsics.checkNotNull(respiratoryRateData6);
                                    textView3.setText(String.valueOf(respiratoryRateData6.getMax()));
                                }
                            }
                        }
                    }
                    TextView textView4 = this.this$0.D().tvRespiratoryRateMinVal;
                    AppConstants appConstants2 = AppConstants.TWO_DASH_STRING;
                    textView4.setText(appConstants2.getValue());
                    this.this$0.D().tvRespiratoryRateMaxVal.setText(appConstants2.getValue());
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalsSleepFragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.p = calendar;
        this.s = true;
        this.x = 1;
        this.y = 2;
        this.C = new int[]{R.string.sleep_info1, R.string.sleep_info2, R.string.sleep_info3, R.string.sleep_info4, R.string.sleep_info5, R.string.sleep_info6};
        this.D = new int[]{2131233481, 2131233482, 2131233483, 2131233484, 2131233485, 2131233486};
    }

    public static final void C(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void K(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void N(final FragmentVitalSleepBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollSleep.post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.z1
            @Override // java.lang.Runnable
            public final void run() {
                VitalsSleepFragment.O(FragmentVitalSleepBinding.this);
            }
        });
    }

    public static final void O(FragmentVitalSleepBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollSleep.fullScroll(130);
    }

    public static final void P(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppNavigator.Companion companion = AppNavigator.Companion;
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = this$0.getResources().getString(R.string.sleep);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.sleep)");
        companion.navigateToInfoPage(requireActivity, string);
    }

    public static final void Q(VitalsSleepFragment this$0, FragmentVitalSleepBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.SLEEP_HISTORY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_DAY_GRAPH.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.DAY_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        TextView textView = this_apply.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvOutOfStepsSleepValue");
        this$0.visible(textView);
        ConstraintLayout sleepGraphBg = this_apply.sleepGraphBg;
        Intrinsics.checkNotNullExpressionValue(sleepGraphBg, "sleepGraphBg");
        this$0.gone(sleepGraphBg);
        ConstraintLayout constraintLayout = this_apply.vitalsMainData.clMainVitals;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clMainVitals");
        this$0.visible(constraintLayout);
        this$0.v = this$0.w;
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this$0.H(calendar);
        this$0.m0();
        ConstraintLayout constraintLayout2 = this$0.D().vitalsMainData.clStepsSleepsTotalAvg;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.vitalsMainData.clStepsSleepsTotalAvg");
        this$0.gone(constraintLayout2);
        ConstraintLayout constraintLayout3 = this$0.D().vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.vitalsMainData.clCenterDataStepsSleep");
        this$0.visible(constraintLayout3);
        ConstraintLayout constraintLayout4 = this$0.D().vitalsMainData.clCenterDataAVGStepSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.vitalsMainData.clCenterDataAVGStepSleep");
        this$0.gone(constraintLayout4);
    }

    public static final void R(VitalsSleepFragment this$0, FragmentVitalSleepBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.SLEEP_HISTORY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_WEEK_GRAPH.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.WEEK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.v = this$0.x;
        ConstraintLayout sleepGraphBg = this_apply.sleepGraphBg;
        Intrinsics.checkNotNullExpressionValue(sleepGraphBg, "sleepGraphBg");
        this$0.visible(sleepGraphBg);
        ConstraintLayout constraintLayout = this_apply.vitalsMainData.clMainVitals;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clMainVitals");
        this$0.visible(constraintLayout);
        TextView textView = this_apply.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvOutOfStepsSleepValue");
        this$0.visible(textView);
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -6);
        Calendar endDate = Calendar.getInstance();
        this$0.A = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.G(startDate, endDate);
        this$0.m0();
        this$0.b0(true);
    }

    public static final void S(VitalsSleepFragment this$0, FragmentVitalSleepBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.SLEEP_HISTORY_SCREEN.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_MONTH_GRAPH.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.MONTH_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        TextView textView = this_apply.vitalsMainData.tvOutOfStepsSleepValue;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvOutOfStepsSleepValue");
        this$0.visible(textView);
        ConstraintLayout sleepGraphBg = this_apply.sleepGraphBg;
        Intrinsics.checkNotNullExpressionValue(sleepGraphBg, "sleepGraphBg");
        this$0.visible(sleepGraphBg);
        ConstraintLayout constraintLayout = this_apply.vitalsMainData.clMainVitals;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clMainVitals");
        this$0.visible(constraintLayout);
        this$0.v = this$0.y;
        Calendar startDate = Calendar.getInstance();
        startDate.add(5, -30);
        Calendar endDate = Calendar.getInstance();
        this$0.A = new Pair<>(startDate, endDate);
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        this$0.G(startDate, endDate);
        this$0.m0();
        this$0.b0(false);
    }

    public static final void T(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.ActivityDashboardNew");
        ((ActivityDashboardNew) activity).takeToScreen(HealthNavigationElement.RESPIRATORY_RATE, this$0.p);
    }

    public static final void U(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.v;
        if (i == this$0.w) {
            this$0.d0();
            this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
            this$0.getDatePickerDialog().show();
        } else if (i == this$0.x) {
            this$0.u = true;
            this$0.k0();
        } else if (i == this$0.y) {
            this$0.u = false;
            this$0.k0();
        }
    }

    public static final void V(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.v;
        if (i == this$0.w) {
            this$0.H(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.p));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.x) {
            if (this$0.A == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            }
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.A;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.A;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            Object clone2 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            this$0.A = new Pair<>(calendar, calendar2);
            this$0.G(calendar, calendar2);
            this$0.m0();
        } else if (i == this$0.y) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.A;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone3 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.A;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            Object clone4 = pair.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            this$0.A = new Pair<>(calendar3, calendar4);
            this$0.G(calendar3, calendar4);
            this$0.m0();
        }
    }

    public static final void W(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.v;
        if (i == this$0.w) {
            if (DateUtils.isToday(this$0.p.getTimeInMillis())) {
                return;
            }
            this$0.H(PayUtils.INSTANCE.getNextDayCalendar(this$0.p));
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this$0.x) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this$0.A;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            if (DateUtils.isToday(pair2.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair3 = this$0.A;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone = pair3.getSecond().clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this$0.A;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair4;
            }
            Object clone2 = pair.getSecond().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone2;
            calendar2.add(5, 6);
            if (calendar2.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                this$0.A = new Pair<>(calendar, calendar2);
                this$0.G(calendar, calendar2);
                this$0.m0();
                return;
            }
            int findDateDifference = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar2.getTime());
            Object clone3 = calendar.clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone3;
            calendar3.add(5, 6 - findDateDifference);
            this$0.A = new Pair<>(calendar, calendar3);
            this$0.G(calendar, calendar3);
            this$0.m0();
        } else if (i == this$0.y) {
            Pair<? extends Calendar, ? extends Calendar> pair5 = this$0.A;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            if (DateUtils.isToday(pair5.getSecond().getTimeInMillis())) {
                return;
            }
            Pair<? extends Calendar, ? extends Calendar> pair6 = this$0.A;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair6 = null;
            }
            Object clone4 = pair6.getSecond().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            Pair<? extends Calendar, ? extends Calendar> pair7 = this$0.A;
            if (pair7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair7;
            }
            Object clone5 = pair.getSecond().clone();
            Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar5 = (Calendar) clone5;
            calendar5.add(5, 30);
            if (calendar5.getTimeInMillis() <= Calendar.getInstance().getTimeInMillis()) {
                this$0.A = new Pair<>(calendar4, calendar5);
                this$0.G(calendar4, calendar5);
                this$0.m0();
                return;
            }
            int findDateDifference2 = AppUtils.findDateDifference(Calendar.getInstance().getTime(), calendar5.getTime());
            Object clone6 = calendar4.clone();
            Intrinsics.checkNotNull(clone6, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar6 = (Calendar) clone6;
            calendar6.add(5, 30 - findDateDifference2);
            this$0.A = new Pair<>(calendar4, calendar6);
            this$0.G(calendar4, calendar6);
            this$0.m0();
        }
    }

    public static final void X(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireContext().startActivity(new Intent(this$0.requireContext(), ActivitySleepGoalSettings.class));
    }

    public static final void Y(VitalsSleepFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void a0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e0(VitalsSleepFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.H(newDate);
    }

    public final void B() {
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.o;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        MutableLiveData<Integer> avgSleepData = fragmentSleepHistoryViewModel.getAvgSleepData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final a aVar = new a();
        avgSleepData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.x1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalsSleepFragment.C(Function1.this, obj);
            }
        });
    }

    public final FragmentVitalSleepBinding D() {
        FragmentVitalSleepBinding fragmentVitalSleepBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalSleepBinding);
        return fragmentVitalSleepBinding;
    }

    public final SleepInsightsModel E(Calendar calendar) {
        SleepInsightsModel sleepInsightsModel = new SleepInsightsModel(0, 0, 0);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        SleepRepository.Companion companion = SleepRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.getInstance(requireContext).getLastNignthSleepDataWithOutLiveData(calendar, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress()) != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            List<SleepDataModelForLastNight> lastNignthSleepDataWithOutLiveData = companion.getInstance(requireContext2).getLastNignthSleepDataWithOutLiveData(calendar, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
            if (lastNignthSleepDataWithOutLiveData != null) {
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                SleepDataModel sleepDataModel = SleepDataHelper.getSleepDataModel(requireContext3, lastNignthSleepDataWithOutLiveData);
                return new SleepInsightsModel(sleepDataModel != null ? sleepDataModel.getCountTotalSleep() : 0, sleepDataModel != null ? sleepDataModel.getCountOfAwakeMinutes() : 0, sleepDataModel != null ? sleepDataModel.getCountOfDeepSleepMinutes() : 0);
            }
            return new SleepInsightsModel(0, 0, 0);
        }
        return sleepInsightsModel;
    }

    public final List<TipsModel> F() {
        ArrayList arrayList = new ArrayList();
        int length = this.C.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.D[i];
            String string = getString(this.C[i]);
            Intrinsics.checkNotNullExpressionValue(string, "getString(description[i])");
            arrayList.add(new TipsModel(i2, string));
        }
        return arrayList;
    }

    public final void G(Calendar calendar, Calendar calendar2) {
        if (isAdded()) {
            if (DateUtils.isToday(calendar2.getTimeInMillis())) {
                D().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                D().vitalsMainData.ibForward.setEnabled(false);
            } else {
                D().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                D().vitalsMainData.ibForward.setEnabled(true);
            }
            TextView textView = D().vitalsMainData.tvSelectedTypeValue;
            StringBuilder sb = new StringBuilder();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar));
            sb.append(" - ");
            sb.append(themesUtils.getDateFormatDDMMMYYYY(calendar2));
            textView.setText(sb.toString());
            l0();
            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = null;
            BaseFragment.showProgress$default(this, false, 1, null);
            FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel2 = this.o;
            if (fragmentSleepHistoryViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
            } else {
                fragmentSleepHistoryViewModel = fragmentSleepHistoryViewModel2;
            }
            fragmentSleepHistoryViewModel.queryLastNightSleepDataFor(calendar, calendar2);
            f0();
        }
    }

    public final void H(Calendar calendar) {
        if (isAdded()) {
            TextView textView = D().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                D().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                D().vitalsMainData.ibForward.setEnabled(false);
            } else {
                D().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                D().vitalsMainData.ibForward.setEnabled(true);
            }
            FragmentSleepViewModel fragmentSleepViewModel = this.n;
            FragmentSleepViewModel fragmentSleepViewModel2 = null;
            if (fragmentSleepViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentSleepViewModel = null;
            }
            fragmentSleepViewModel.loadHourlyData(calendar);
            FragmentSleepViewModel fragmentSleepViewModel3 = this.n;
            if (fragmentSleepViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentSleepViewModel2 = fragmentSleepViewModel3;
            }
            fragmentSleepViewModel2.loadDailyData(calendar);
            this.p = calendar;
            if (BleApiManager.getInstance(requireActivity()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
                Calendar calendar2 = this.p;
                RespiratoryRateViewModel respiratoryRateViewModel = this.q;
                if (respiratoryRateViewModel != null) {
                    respiratoryRateViewModel.loadDailyData(calendar2);
                }
            }
            f0();
        }
    }

    public final void I() {
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.o;
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel2 = null;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        MutableLiveData<SleepInsightsModel> currentSleepInsightsData = fragmentSleepHistoryViewModel.getCurrentSleepInsightsData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final b bVar = new b();
        currentSleepInsightsData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.w1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalsSleepFragment.J(Function1.this, obj);
            }
        });
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel3 = this.o;
        if (fragmentSleepHistoryViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
        } else {
            fragmentSleepHistoryViewModel2 = fragmentSleepHistoryViewModel3;
        }
        MutableLiveData<SleepInsightsModel> previousSleepInsightsData = fragmentSleepHistoryViewModel2.getPreviousSleepInsightsData();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        final c cVar = new c();
        previousSleepInsightsData.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.y1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalsSleepFragment.K(Function1.this, obj);
            }
        });
    }

    public final void L(ArrayList<FeedbackQuetionnarieModel> arrayList) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        D().sleepFeedbackPager.setAdapter(new PagerAdapterFeedback(requireContext, childFragmentManager, arrayList));
        D().circlePageIndicatorFeedback.setViewPager(D().sleepFeedbackPager);
    }

    public final void M() {
        final FragmentVitalSleepBinding D = D();
        D.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.e2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.U(VitalsSleepFragment.this, view);
            }
        });
        D.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.f2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.V(VitalsSleepFragment.this, view);
            }
        });
        D.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.b2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.W(VitalsSleepFragment.this, view);
            }
        });
        D.tvEditVitalNameGoal.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.g2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.X(VitalsSleepFragment.this, view);
            }
        });
        D.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.d2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.Y(VitalsSleepFragment.this, view);
            }
        });
        D.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.a2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.N(FragmentVitalSleepBinding.this, view);
            }
        });
        D.sleepScoreTxt.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.c2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.P(VitalsSleepFragment.this, view);
            }
        });
        D.vitalsMainData.tvDay.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.i2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.Q(VitalsSleepFragment.this, D, view);
            }
        });
        D.vitalsMainData.tvWeek.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.R(VitalsSleepFragment.this, D, view);
            }
        });
        D.vitalsMainData.tvMonth.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.S(VitalsSleepFragment.this, D, view);
            }
        });
        D.nightlyBreathingRate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.h2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsSleepFragment.T(VitalsSleepFragment.this, view);
            }
        });
    }

    public final void Z() {
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.o;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        MutableLiveData<Boolean> sleepRangeQueryLiveData = fragmentSleepHistoryViewModel.getSleepRangeQueryLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        final d dVar = new d();
        sleepRangeQueryLiveData.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.v1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VitalsSleepFragment.a0(Function1.this, obj);
            }
        });
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

    public final void b0(boolean z) {
        ConstraintLayout constraintLayout = D().vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout);
        ConstraintLayout constraintLayout2 = D().vitalsMainData.clCenterDataAVGStepSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.vitalsMainData.clCenterDataAVGStepSleep");
        visible(constraintLayout2);
        ConstraintLayout constraintLayout3 = D().vitalsMainData.clStepsSleepsTotalAvg;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.vitalsMainData.clStepsSleepsTotalAvg");
        visible(constraintLayout3);
        D().vitalsMainData.tvAvgStepSleepTitle.setText(getString(z ? R.string.total_sleeps_taken_week : R.string.total_sleeps_taken_month));
    }

    public final void c0() {
        FragmentVitalSleepBinding D = D();
        TextView textView = D.vitalsMainData.tvAvgStepsSleepValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String string = getString(R.string.deepsleep_spannable);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.deepsleep_spannable)");
        String format = String.format(locale, string, Arrays.copyOf(new Object[]{0, 0}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView.setText(Html.fromHtml(format), TextView.BufferType.SPANNABLE);
        D.sleepTipsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TipsAdapter tipsAdapter = new TipsAdapter();
        tipsAdapter.setTipsList(F());
        D.sleepTipsRecycler.setAdapter(tipsAdapter);
        D.deepSleepAbout.tvSleepStage.setText(getString(R.string.deep_sleep_text));
        D.deepSleepAbout.tvSleepStage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.deep_sleep_moon, 0, 0, 0);
        D.deepSleepAbout.tvSleepStageInfo.setText(getString(R.string.about_deep_sleep));
        D.lightSleepAbout.tvSleepStage.setText(getString(R.string.light_sleep_text));
        D.lightSleepAbout.tvSleepStage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.light_sleep_moon, 0, 0, 0);
        D.lightSleepAbout.tvSleepStageInfo.setText(getString(R.string.about_light_sleep));
        D.awakeSleepAbout.tvSleepStage.setText(getString(R.string.awake));
        D.awakeSleepAbout.tvSleepStage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.awake_sleep_moon, 0, 0, 0);
        D.awakeSleepAbout.tvSleepStageInfo.setText(getString(R.string.about_awake_sleep));
        D.remSleepAbout.tvSleepStage.setText(getString(R.string.rem_sleep));
        D.remSleepAbout.tvSleepStage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rem_sleep_moon, 0, 0, 0);
        D.remSleepAbout.tvSleepStageInfo.setText(getString(R.string.about_rem_sleep));
        if (!this.s) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (!companion.isSmaDevice(requireActivity)) {
                ConstraintLayout remSleepAboutConstraint = D.remSleepAboutConstraint;
                Intrinsics.checkNotNullExpressionValue(remSleepAboutConstraint, "remSleepAboutConstraint");
                gone(remSleepAboutConstraint);
                LinearLayout remSleepLayout = D.remSleepLayout;
                Intrinsics.checkNotNullExpressionValue(remSleepLayout, "remSleepLayout");
                gone(remSleepLayout);
                TextView textView2 = D.deepSleep;
                String string2 = getString(R.string.deepsleep_spannable);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.deepsleep_spannable)");
                String format2 = String.format(locale, string2, Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                textView2.setText(Html.fromHtml(format2), TextView.BufferType.SPANNABLE);
                TextView textView3 = D.lightSleep;
                String string3 = getString(R.string.deepsleep_spannable);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.deepsleep_spannable)");
                String format3 = String.format(locale, string3, Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                textView3.setText(Html.fromHtml(format3), TextView.BufferType.SPANNABLE);
                TextView textView4 = D.awake;
                String string4 = getString(R.string.deepsleep_spannable);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.deepsleep_spannable)");
                String format4 = String.format(locale, string4, Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                textView4.setText(Html.fromHtml(format4), TextView.BufferType.SPANNABLE);
                return;
            }
        }
        LinearLayout remSleepLayout2 = D.remSleepLayout;
        Intrinsics.checkNotNullExpressionValue(remSleepLayout2, "remSleepLayout");
        visible(remSleepLayout2);
        ConstraintLayout remSleepAboutConstraint2 = D.remSleepAboutConstraint;
        Intrinsics.checkNotNullExpressionValue(remSleepAboutConstraint2, "remSleepAboutConstraint");
        visible(remSleepAboutConstraint2);
        TextView textView5 = D.deepSleep;
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.sleep_cons));
        sb.append(' ');
        Context context = getContext();
        sb.append(context != null ? context.getString(R.string.hrs) : null);
        textView5.setText(sb.toString());
        TextView textView6 = D.awake;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getResources().getString(R.string.sleep_cons));
        sb2.append(' ');
        Context context2 = getContext();
        sb2.append(context2 != null ? context2.getString(R.string.hrs) : null);
        textView6.setText(sb2.toString());
        TextView textView7 = D.lightSleep;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getResources().getString(R.string.sleep_cons));
        sb3.append(' ');
        Context context3 = getContext();
        sb3.append(context3 != null ? context3.getString(R.string.hrs) : null);
        textView7.setText(sb3.toString());
        TextView textView8 = D.remSleep;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getResources().getString(R.string.sleep_cons));
        sb4.append(' ');
        Context context4 = getContext();
        sb4.append(context4 != null ? context4.getString(R.string.hrs) : null);
        textView8.setText(sb4.toString());
        DeviceUtils.Companion companion2 = DeviceUtils.Companion;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        if (companion2.isSmaDevice(requireActivity2)) {
            LinearLayout awakeSleepLayout = D.awakeSleepLayout;
            Intrinsics.checkNotNullExpressionValue(awakeSleepLayout, "awakeSleepLayout");
            gone(awakeSleepLayout);
            ConstraintLayout awakeSleepAboutConstraint = D.awakeSleepAboutConstraint;
            Intrinsics.checkNotNullExpressionValue(awakeSleepAboutConstraint, "awakeSleepAboutConstraint");
            gone(awakeSleepAboutConstraint);
            ConstraintLayout constraintLayout = D().sleepInsights.clAwakeTime;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.sleepInsights.clAwakeTime");
            gone(constraintLayout);
        }
    }

    public final void d0() {
        Calendar calendar = this.p;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.s1
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalsSleepFragment.e0(VitalsSleepFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void f0() {
        int i = this.v;
        if (i == this.w) {
            Calendar calendar = this.p;
            Object clone = calendar.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(6, -1);
            SleepInsightsModel E = E(calendar);
            SleepInsightsModel E2 = E(calendar2);
            this.F = E != null ? E.getTotalSleep() : 0;
            this.E = E != null ? E.getAwakeTime() : 0;
            this.G = E != null ? E.getDeepSleep() : 0;
            this.I = E2 != null ? E2.getTotalSleep() : 0;
            this.H = E2 != null ? E2.getAwakeTime() : 0;
            this.J = E2 != null ? E2.getDeepSleep() : 0;
            g0();
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this.x) {
            Pair<? extends Calendar, ? extends Calendar> pair2 = this.A;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Object clone2 = pair2.getFirst().clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar3 = (Calendar) clone2;
            calendar3.add(5, -6);
            Pair<? extends Calendar, ? extends Calendar> pair3 = this.A;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            Object clone3 = pair3.getFirst().clone();
            Intrinsics.checkNotNull(clone3, "null cannot be cast to non-null type java.util.Calendar");
            this.B = new Pair<>(calendar3, (Calendar) clone3);
        } else if (i == this.y) {
            Pair<? extends Calendar, ? extends Calendar> pair4 = this.A;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Object clone4 = pair4.getFirst().clone();
            Intrinsics.checkNotNull(clone4, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar4 = (Calendar) clone4;
            calendar4.add(5, -30);
            Pair<? extends Calendar, ? extends Calendar> pair5 = this.A;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            Object clone5 = pair5.getFirst().clone();
            Intrinsics.checkNotNull(clone5, "null cannot be cast to non-null type java.util.Calendar");
            this.B = new Pair<>(calendar4, (Calendar) clone5);
        }
        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = this.o;
        if (fragmentSleepHistoryViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
            fragmentSleepHistoryViewModel = null;
        }
        Pair<? extends Calendar, ? extends Calendar> pair6 = this.A;
        if (pair6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            pair6 = null;
        }
        Calendar first = pair6.getFirst();
        Pair<? extends Calendar, ? extends Calendar> pair7 = this.A;
        if (pair7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
        } else {
            pair = pair7;
        }
        fragmentSleepHistoryViewModel.queryRangeInsightsSleepDataFor(first, pair.getSecond(), true);
    }

    public final void g0() {
        String str;
        int i = this.v;
        if (i == 0) {
            D().sleepInsights.tvAwakeDesc.setText(requireContext().getString(R.string.your_awake_time_compared_yesterday));
            D().sleepInsights.tvSleepDesc.setText(requireContext().getString(R.string.your_sleep_time_compared_yesterday));
            D().sleepInsights.tvDeepDesc.setText(requireContext().getString(R.string.time_you_were_in_deep_sleep));
            D().sleepInsights.tvDeepTime.setText(requireContext().getString(R.string.hours));
            D().sleepInsights.tvInsights.setText(requireContext().getString(R.string.daily_insights));
        } else if (i != 1) {
            D().sleepInsights.tvAwakeDesc.setText(requireContext().getString(R.string.your_awake_time_compared_last_month));
            D().sleepInsights.tvSleepDesc.setText(requireContext().getString(R.string.your_sleep_time_compared_last_month));
            D().sleepInsights.tvDeepDesc.setText(requireContext().getString(R.string.avg_time_you_were_in_deep_sleep_month));
            D().sleepInsights.tvDeepTime.setText(requireContext().getString(R.string.avg_hours));
            D().sleepInsights.tvInsights.setText(requireContext().getString(R.string.monthly_insights));
        } else {
            D().sleepInsights.tvAwakeDesc.setText(requireContext().getString(R.string.your_awake_time_compared_last_week));
            D().sleepInsights.tvSleepDesc.setText(requireContext().getString(R.string.your_sleep_time_compared_last_week));
            D().sleepInsights.tvDeepDesc.setText(requireContext().getString(R.string.avg_time_you_were_in_deep_sleep_week));
            D().sleepInsights.tvDeepTime.setText(requireContext().getString(R.string.avg_hours));
            D().sleepInsights.tvInsights.setText(requireContext().getString(R.string.weekly_insights));
        }
        int i2 = this.E;
        if (i2 == 0 && this.G == 0 && this.F == 0 && this.H == 0 && this.J == 0 && this.I == 0) {
            D().sleepInsights.tvAwakePerc.setText("--");
            D().sleepInsights.tvSleepPerc.setText("--");
            D().sleepInsights.tvDeepPerc.setText("--");
            TextView textView = D().sleepInsights.tvAwakeTime;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.sleepInsights.tvAwakeTime");
            String string = requireContext().getString(R.string.hours_awake);
            Intrinsics.checkNotNullExpressionValue(string, "requireContext().getString(R.string.hours_awake)");
            j0("--", textView, string);
            TextView textView2 = D().sleepInsights.tvAwakePerc;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.sleepInsights.tvAwakePerc");
            emptyDrawable(textView2);
            TextView textView3 = D().sleepInsights.tvSleepTime;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.sleepInsights.tvSleepTime");
            String string2 = requireContext().getString(R.string.hours_total_sleep);
            Intrinsics.checkNotNullExpressionValue(string2, "requireContext().getStri…string.hours_total_sleep)");
            j0("--", textView3, string2);
            TextView textView4 = D().sleepInsights.tvSleepPerc;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.sleepInsights.tvSleepPerc");
            emptyDrawable(textView4);
            return;
        }
        int abs = Math.abs(i2 - this.H);
        int abs2 = Math.abs(this.F - this.I);
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Integer insightArrowDrawable = payUtils.getInsightArrowDrawable(requireContext, this.E - this.H);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Integer insightArrowDrawable2 = payUtils.getInsightArrowDrawable(requireContext2, this.F - this.I);
        if (abs != 0) {
            TextView textView5 = D().sleepInsights.tvAwakePerc;
            StringBuilder sb = new StringBuilder();
            str = "requireContext().getStri…string.hours_total_sleep)";
            sb.append(payUtils.getHourFormat(abs));
            sb.append(':');
            sb.append(payUtils.getSecondFormat(abs % 60));
            textView5.setText(sb.toString());
        } else {
            str = "requireContext().getStri…string.hours_total_sleep)";
            D().sleepInsights.tvAwakePerc.setText("--");
        }
        if (abs2 != 0) {
            D().sleepInsights.tvSleepPerc.setText(payUtils.getHourFormat(abs2) + ':' + payUtils.getSecondFormat(abs2 % 60));
        } else {
            D().sleepInsights.tvSleepPerc.setText("--");
        }
        if (insightArrowDrawable != null) {
            TextView textView6 = D().sleepInsights.tvAwakePerc;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.sleepInsights.tvAwakePerc");
            drawableEnd(textView6, insightArrowDrawable.intValue());
        } else {
            TextView textView7 = D().sleepInsights.tvAwakePerc;
            Intrinsics.checkNotNullExpressionValue(textView7, "binding.sleepInsights.tvAwakePerc");
            emptyDrawable(textView7);
        }
        if (insightArrowDrawable2 != null) {
            TextView textView8 = D().sleepInsights.tvSleepPerc;
            Intrinsics.checkNotNullExpressionValue(textView8, "binding.sleepInsights.tvSleepPerc");
            drawableEnd(textView8, insightArrowDrawable2.intValue());
        } else {
            TextView textView9 = D().sleepInsights.tvSleepPerc;
            Intrinsics.checkNotNullExpressionValue(textView9, "binding.sleepInsights.tvSleepPerc");
            emptyDrawable(textView9);
        }
        if (this.E != 0) {
            TextView textView10 = D().sleepInsights.tvAwakeTime;
            Intrinsics.checkNotNullExpressionValue(textView10, "binding.sleepInsights.tvAwakeTime");
            String string3 = requireContext().getString(R.string.hours_awake);
            Intrinsics.checkNotNullExpressionValue(string3, "requireContext().getString(R.string.hours_awake)");
            j0(payUtils.getHourFormat(this.E) + ':' + payUtils.getSecondFormat(this.E % 60), textView10, string3);
        } else {
            TextView textView11 = D().sleepInsights.tvAwakeTime;
            Intrinsics.checkNotNullExpressionValue(textView11, "binding.sleepInsights.tvAwakeTime");
            String string4 = requireContext().getString(R.string.hours_awake);
            Intrinsics.checkNotNullExpressionValue(string4, "requireContext().getString(R.string.hours_awake)");
            j0("--", textView11, string4);
        }
        if (this.F != 0) {
            TextView textView12 = D().sleepInsights.tvSleepTime;
            Intrinsics.checkNotNullExpressionValue(textView12, "binding.sleepInsights.tvSleepTime");
            String string5 = requireContext().getString(R.string.hours_total_sleep);
            Intrinsics.checkNotNullExpressionValue(string5, str);
            j0(payUtils.getHourFormat(this.F) + ':' + payUtils.getSecondFormat(this.F % 60), textView12, string5);
        } else {
            TextView textView13 = D().sleepInsights.tvSleepTime;
            Intrinsics.checkNotNullExpressionValue(textView13, "binding.sleepInsights.tvSleepTime");
            String string6 = requireContext().getString(R.string.hours_total_sleep);
            Intrinsics.checkNotNullExpressionValue(string6, str);
            j0("--", textView13, string6);
        }
        int i3 = this.J;
        if (i3 != 0) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(Locale.ENGLISH, "%dhr %dmin", Arrays.copyOf(new Object[]{Integer.valueOf(i3 / 60), Integer.valueOf(this.J % 60)}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            SpannableString spannableString = new SpannableString(format);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format, "hr", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "hr", 0, false, 6, (Object) null) + 2, 33);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format, "min", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "min", 0, false, 6, (Object) null) + 3, 33);
            D().sleepInsights.tvDeepPerc.setText(spannableString);
            return;
        }
        D().sleepInsights.tvDeepPerc.setText("--");
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

    @Nullable
    public final SleepData getSleepData() {
        return this.r;
    }

    public final void h0() {
        if (!this.s) {
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            if (!companion.isSmaDevice(requireActivity)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String format = String.format(locale, "%dhr %dmin", Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                SpannableString spannableString = new SpannableString(format);
                spannableString.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format, "hr", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "hr", 0, false, 6, (Object) null) + 2, 33);
                spannableString.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format, "min", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "min", 0, false, 6, (Object) null) + 3, 33);
                D().deepSleep.setText(spannableString);
                String format2 = String.format(locale, "%dhr %dmin", Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                SpannableString spannableString2 = new SpannableString(format2);
                spannableString2.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format2, "hr", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format2, "hr", 0, false, 6, (Object) null) + 2, 33);
                spannableString2.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format2, "min", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format2, "min", 0, false, 6, (Object) null) + 3, 33);
                D().lightSleep.setText(spannableString2);
                String format3 = String.format(locale, "%dhr %dmin", Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
                SpannableString spannableString3 = new SpannableString(format3);
                spannableString3.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format3, "hr", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format3, "hr", 0, false, 6, (Object) null) + 2, 33);
                spannableString3.setSpan(new RelativeSizeSpan(0.7f), StringsKt__StringsKt.indexOf$default((CharSequence) format3, "min", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format3, "min", 0, false, 6, (Object) null) + 3, 33);
                D().awake.setText(spannableString3);
                TextView textView = D().vitalsMainData.tvAvgStepsSleepValue;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Locale locale2 = Locale.ENGLISH;
                String string = getString(R.string.deepsleep_spannable);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.deepsleep_spannable)");
                String format4 = String.format(locale2, string, Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
                textView.setText(Html.fromHtml(format4), TextView.BufferType.SPANNABLE);
                TextView textView2 = D().vitalsMainData.tvStepSleepTotalValue;
                String string2 = getString(R.string.deepsleep_spannable);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.deepsleep_spannable)");
                String format5 = String.format(locale2, string2, Arrays.copyOf(new Object[]{0, 0}, 2));
                Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
                textView2.setText(Html.fromHtml(format5), TextView.BufferType.SPANNABLE);
                D().totalSleepValue.setText(D().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
                TextView textView3 = D().sleepAtText;
                Intrinsics.checkNotNullExpressionValue(textView3, "binding.sleepAtText");
                gone(textView3);
                TextView textView4 = D().awakeAtText;
                Intrinsics.checkNotNullExpressionValue(textView4, "binding.awakeAtText");
                gone(textView4);
                TextView textView5 = D().sleepAtValue;
                Intrinsics.checkNotNullExpressionValue(textView5, "binding.sleepAtValue");
                gone(textView5);
                TextView textView6 = D().awakeAtValue;
                Intrinsics.checkNotNullExpressionValue(textView6, "binding.awakeAtValue");
                gone(textView6);
                LinearLayout linearLayout = D().sleepBottomLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.sleepBottomLayout");
                gone(linearLayout);
                TextView textView7 = D().tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(textView7, "binding.tvNoDataFound");
                visible(textView7);
            }
        }
        TextView textView8 = D().deepSleep;
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.sleep_cons));
        sb.append(' ');
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        sb.append(context.getString(R.string.hrs));
        textView8.setText(sb.toString());
        TextView textView9 = D().awake;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getResources().getString(R.string.sleep_cons));
        sb2.append(' ');
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        sb2.append(context2.getString(R.string.hrs));
        textView9.setText(sb2.toString());
        TextView textView10 = D().lightSleep;
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getResources().getString(R.string.sleep_cons));
        sb3.append(' ');
        Context context3 = getContext();
        Intrinsics.checkNotNull(context3);
        sb3.append(context3.getString(R.string.hrs));
        textView10.setText(sb3.toString());
        TextView textView11 = D().remSleep;
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getResources().getString(R.string.sleep_cons));
        sb4.append(' ');
        Context context4 = getContext();
        Intrinsics.checkNotNull(context4);
        sb4.append(context4.getString(R.string.hrs));
        textView11.setText(sb4.toString());
        TextView textView12 = D().vitalsMainData.tvAvgStepsSleepValue;
        StringCompanionObject stringCompanionObject22 = StringCompanionObject.INSTANCE;
        Locale locale22 = Locale.ENGLISH;
        String string3 = getString(R.string.deepsleep_spannable);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.deepsleep_spannable)");
        String format42 = String.format(locale22, string3, Arrays.copyOf(new Object[]{0, 0}, 2));
        Intrinsics.checkNotNullExpressionValue(format42, "format(locale, format, *args)");
        textView12.setText(Html.fromHtml(format42), TextView.BufferType.SPANNABLE);
        TextView textView22 = D().vitalsMainData.tvStepSleepTotalValue;
        String string22 = getString(R.string.deepsleep_spannable);
        Intrinsics.checkNotNullExpressionValue(string22, "getString(R.string.deepsleep_spannable)");
        String format52 = String.format(locale22, string22, Arrays.copyOf(new Object[]{0, 0}, 2));
        Intrinsics.checkNotNullExpressionValue(format52, "format(locale, format, *args)");
        textView22.setText(Html.fromHtml(format52), TextView.BufferType.SPANNABLE);
        D().totalSleepValue.setText(D().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
        TextView textView32 = D().sleepAtText;
        Intrinsics.checkNotNullExpressionValue(textView32, "binding.sleepAtText");
        gone(textView32);
        TextView textView42 = D().awakeAtText;
        Intrinsics.checkNotNullExpressionValue(textView42, "binding.awakeAtText");
        gone(textView42);
        TextView textView52 = D().sleepAtValue;
        Intrinsics.checkNotNullExpressionValue(textView52, "binding.sleepAtValue");
        gone(textView52);
        TextView textView62 = D().awakeAtValue;
        Intrinsics.checkNotNullExpressionValue(textView62, "binding.awakeAtValue");
        gone(textView62);
        LinearLayout linearLayout2 = D().sleepBottomLayout;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.sleepBottomLayout");
        gone(linearLayout2);
        TextView textView72 = D().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView72, "binding.tvNoDataFound");
        visible(textView72);
    }

    public final void i0() {
        Integer sleepScore;
        String valueOf;
        Integer sleepScore2;
        SleepScoreRepository.Companion companion = SleepScoreRepository.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String formatDate = RepositoryUtils.formatDate(this.p.getTime(), "yyyy-MM-dd");
        Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(selectedDate.time, \"yyyy-MM-dd\")");
        if (companion.getInstance(requireContext).getSleepScoreData(formatDate, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress()) != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            String formatDate2 = RepositoryUtils.formatDate(this.p.getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(selectedDate.time, \"yyyy-MM-dd\")");
            SleepScoreData sleepScoreData = companion.getInstance(requireContext2).getSleepScoreData(formatDate2, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
            if ((sleepScoreData != null ? sleepScoreData.getSleepScore() : null) != null && ((sleepScore2 = sleepScoreData.getSleepScore()) == null || sleepScore2.intValue() != -1)) {
                D().tvSleepScore.setText(String.valueOf(sleepScoreData.getSleepScore()));
                ImageView imageView = D().tvSleepScoreImg;
                PayUtils payUtils = PayUtils.INSTANCE;
                Integer sleepScore3 = sleepScoreData.getSleepScore();
                Intrinsics.checkNotNull(sleepScore3);
                int intValue = sleepScore3.intValue();
                Context requireContext3 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                imageView.setImageDrawable(payUtils.getSleepScoreRange(intValue, requireContext3));
            } else {
                D().tvSleepScore.setText(BleConst.GetDeviceTime);
                ImageView imageView2 = D().tvSleepScoreImg;
                PayUtils payUtils2 = PayUtils.INSTANCE;
                Context requireContext4 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                imageView2.setImageDrawable(payUtils2.getSleepScoreRange(0, requireContext4));
            }
            if ((sleepScoreData != null ? sleepScoreData.getTimeToDeepSleep() : null) != null) {
                Integer timeToDeepSleep = sleepScoreData.getTimeToDeepSleep();
                if (timeToDeepSleep != null && timeToDeepSleep.intValue() == -1) {
                    D().timeTotalSleepValue.setText(getResources().getString(R.string.not_available));
                } else {
                    TextView textView = D().timeTotalSleepValue;
                    textView.setText(sleepScoreData.getTimeToDeepSleep() + " mins");
                }
            } else {
                D().timeTotalSleepValue.setText("0 mins");
            }
            if ((sleepScoreData != null ? sleepScoreData.getWascoCount() : null) != null) {
                TextView textView2 = D().wasoValue;
                textView2.setText(sleepScoreData.getWascoCount() + " times");
            } else {
                D().wasoValue.setText("0 times");
            }
            if ((sleepScoreData != null ? sleepScoreData.getSleepConsistency() : null) != null) {
                if (kotlin.text.m.equals(sleepScoreData.getSleepConsistency(), Constants.NOT_AVAILABLE.getValue(), true)) {
                    D().sleepConsistencyValue.setText(getResources().getString(R.string.not_available));
                } else {
                    TextView textView3 = D().sleepConsistencyValue;
                    String sleepConsistency = sleepScoreData.getSleepConsistency();
                    Intrinsics.checkNotNull(sleepConsistency);
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String lowerCase = sleepConsistency.toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    if (lowerCase.length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        char charAt = lowerCase.charAt(0);
                        if (Character.isLowerCase(charAt)) {
                            Locale locale2 = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                            valueOf = kotlin.text.a.titlecase(charAt, locale2);
                        } else {
                            valueOf = String.valueOf(charAt);
                        }
                        sb.append((Object) valueOf);
                        String substring = lowerCase.substring(1);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        sb.append(substring);
                        lowerCase = sb.toString();
                    }
                    textView3.setText(lowerCase);
                }
            } else {
                D().sleepConsistencyValue.setText(getResources().getString(R.string.not_available));
            }
            Object clone = this.p.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar = (Calendar) clone;
            calendar.add(6, -1);
            Context requireContext5 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
            String formatDate3 = RepositoryUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate3, "formatDate(preDayCal.time, \"yyyy-MM-dd\")");
            SleepScoreData sleepScoreData2 = companion.getInstance(requireContext5).getSleepScoreData(formatDate3, BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
            if (sleepScoreData2 != null && ((sleepScore = sleepScoreData2.getSleepScore()) == null || sleepScore.intValue() != -1)) {
                if ((sleepScoreData != null ? sleepScoreData.getTimeToDeepSleep() : null) != null && sleepScoreData2.getTimeToDeepSleep() != null) {
                    Integer timeToDeepSleep2 = sleepScoreData.getTimeToDeepSleep();
                    Intrinsics.checkNotNull(timeToDeepSleep2);
                    int intValue2 = timeToDeepSleep2.intValue();
                    Integer timeToDeepSleep3 = sleepScoreData2.getTimeToDeepSleep();
                    Intrinsics.checkNotNull(timeToDeepSleep3);
                    if (intValue2 > timeToDeepSleep3.intValue()) {
                        D().timeTotalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131233674, 0);
                    } else {
                        D().timeTotalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131231702, 0);
                    }
                } else {
                    D().timeTotalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                if ((sleepScoreData != null ? sleepScoreData.getWascoCount() : null) != null && sleepScoreData2.getWascoCount() != null) {
                    Integer wascoCount = sleepScoreData.getWascoCount();
                    Intrinsics.checkNotNull(wascoCount);
                    int intValue3 = wascoCount.intValue();
                    Integer wascoCount2 = sleepScoreData2.getWascoCount();
                    Intrinsics.checkNotNull(wascoCount2);
                    if (intValue3 > wascoCount2.intValue()) {
                        D().wasoValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131233674, 0);
                    } else {
                        D().wasoValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131231702, 0);
                    }
                } else {
                    D().wasoValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
                if ((sleepScoreData != null ? sleepScoreData.getTotalSleep() : null) != null && sleepScoreData2.getTotalSleep() != null) {
                    String totalSleep = sleepScoreData.getTotalSleep();
                    Intrinsics.checkNotNull(totalSleep);
                    String totalSleep2 = sleepScoreData2.getTotalSleep();
                    Intrinsics.checkNotNull(totalSleep2);
                    if (totalSleep.compareTo(totalSleep2) > 0) {
                        D().totalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131233674, 0);
                        return;
                    } else {
                        D().totalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2131231702, 0);
                        return;
                    }
                }
                D().totalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                return;
            }
            D().timeTotalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            D().totalSleepValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            D().wasoValue.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            return;
        }
        D().tvSleepScore.setText(BleConst.GetDeviceTime);
        D().timeTotalSleepValue.setText("0 mins");
        TextView textView4 = D().totalSleepValue;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale3 = Locale.ENGLISH;
        String string = getResources().getString(R.string.deepsleep_spannable);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.deepsleep_spannable)");
        String format = String.format(locale3, string, Arrays.copyOf(new Object[]{0, 0}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        textView4.setText(Html.fromHtml(format).toString());
        D().wasoValue.setText("0 times");
        D().sleepConsistencyValue.setText(getResources().getString(R.string.not_available));
        ImageView imageView3 = D().tvSleepScoreImg;
        PayUtils payUtils3 = PayUtils.INSTANCE;
        Context requireContext6 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
        imageView3.setImageDrawable(payUtils3.getSleepScoreRange(0, requireContext6));
    }

    public final void initData() {
        this.v = this.w;
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.n = (FragmentSleepViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentSleepViewModel.class);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.o = (FragmentSleepHistoryViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext2)).get(FragmentSleepHistoryViewModel.class);
        this.s = BleApiManager.getInstance(requireContext()).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep();
        FragmentSleepViewModel fragmentSleepViewModel = this.n;
        FragmentSleepViewModel fragmentSleepViewModel2 = null;
        if (fragmentSleepViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSleepViewModel = null;
        }
        fragmentSleepViewModel.setContractSleepDashboard$app_prodRelease(this);
        FragmentSleepViewModel fragmentSleepViewModel3 = this.n;
        if (fragmentSleepViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSleepViewModel3 = null;
        }
        fragmentSleepViewModel3.setQuestionsList$app_prodRelease(this);
        FragmentSleepViewModel fragmentSleepViewModel4 = this.n;
        if (fragmentSleepViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentSleepViewModel2 = fragmentSleepViewModel4;
        }
        fragmentSleepViewModel2.setMLifecycleOwner(this);
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        RespiratoryRateViewModel respiratoryRateViewModel = (RespiratoryRateViewModel) ViewModelProviders.of(this, new com.coveiot.android.respiratoryrate.utils.ViewModelFactory(requireContext3)).get(RespiratoryRateViewModel.class);
        this.q = respiratoryRateViewModel;
        if (respiratoryRateViewModel != null) {
            respiratoryRateViewModel.setContractRespiratoryRateDashboard(this);
        }
        RespiratoryRateViewModel respiratoryRateViewModel2 = this.q;
        if (respiratoryRateViewModel2 != null) {
            respiratoryRateViewModel2.setMLifecycleOwner(this);
        }
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat2, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat2);
        FragmentVitalSleepBinding D = D();
        ConstraintLayout constraintLayout = D.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        visible(constraintLayout);
        TextView textView = D.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        TextView textView2 = D.vitalsMainData.tvVitalMax;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvVitalMax");
        inVisible(textView2);
        TextView textView3 = D.vitalsMainData.tvVitalMin;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvVitalMin");
        inVisible(textView3);
        TextView textView4 = D.vitalsMainData.tvVitalMaxValue;
        Intrinsics.checkNotNullExpressionValue(textView4, "vitalsMainData.tvVitalMaxValue");
        inVisible(textView4);
        TextView textView5 = D.vitalsMainData.tvVitalMinValue;
        Intrinsics.checkNotNullExpressionValue(textView5, "vitalsMainData.tvVitalMinValue");
        inVisible(textView5);
        ImageView imageView = D.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = D.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = D.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = D.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        D.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_sleep);
        ConstraintLayout constraintLayout2 = D.vitalsMainData.clCenterDataHRVSpo2;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clCenterDataHRVSpo2");
        gone(constraintLayout2);
        ImageView imageView5 = D.vitalsMainData.ivCenterVitalBg;
        Intrinsics.checkNotNullExpressionValue(imageView5, "vitalsMainData.ivCenterVitalBg");
        gone(imageView5);
        D.vitalsMainData.progressBar.setProgressDrawable(requireContext().getDrawable(R.drawable.progress_drawable_sleep_thickness_5dp));
        ProgressBar progressBar = D.vitalsMainData.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "vitalsMainData.progressBar");
        visible(progressBar);
        D.vitalsMainData.tvVitalName.setText(getString(R.string.sleep));
        TextView textView6 = D.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        textView6.setText(payUtils.getLastSyncTime(requireContext4));
        if (BleApiManager.getInstance(requireActivity()).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported()) {
            ConstraintLayout nightlyBreathingRate = D.nightlyBreathingRate;
            Intrinsics.checkNotNullExpressionValue(nightlyBreathingRate, "nightlyBreathingRate");
            visible(nightlyBreathingRate);
            TextView textView7 = D.tvRespiratoryRateMinVal;
            AppConstants appConstants = AppConstants.TWO_DASH_STRING;
            textView7.setText(appConstants.getValue());
            D.tvRespiratoryRateMaxVal.setText(appConstants.getValue());
        } else {
            ConstraintLayout nightlyBreathingRate2 = D.nightlyBreathingRate;
            Intrinsics.checkNotNullExpressionValue(nightlyBreathingRate2, "nightlyBreathingRate");
            gone(nightlyBreathingRate2);
        }
        D.vitalsMainData.tvAvgStepSleepValueUnit.setText(getString(R.string.avg_sleep_hour_per_day));
        c0();
        Z();
        M();
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        H(calendar);
        B();
        I();
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public boolean isSyncInProgress() {
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        return ((DataSyncViewModel) ViewModelProviders.of(requireActivity, new com.coveiot.android.dashboard2.ViewModelFactory(requireActivity2)).get(DataSyncViewModel.class)).checkIsSyncing();
    }

    public final void j0(String str, TextView textView, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, str2, Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(requireContext().getColor(R.color.main_text_color)), StringsKt__StringsKt.indexOf$default((CharSequence) format, str, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, str, 0, false, 6, (Object) null) + str.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        textView.setText(spannableStringBuilder);
    }

    public final void k0() {
        if (isAdded()) {
            boolean z = this.u;
            Pair<? extends Calendar, ? extends Calendar> pair = this.A;
            if (pair == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair = null;
            }
            CalendarRangeDialog calendarRangeDialog = new CalendarRangeDialog(z, pair, this);
            this.z = calendarRangeDialog;
            calendarRangeDialog.show(getChildFragmentManager(), "");
        }
    }

    public final void l0() {
        FragmentVitalSleepBinding D = D();
        if (UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData() == null || UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target == null) {
            return;
        }
        int i = this.v;
        if (i == this.w) {
            TextView textView = D.vitalsMainData.tvOutOfStepsSleepValue;
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.out_of));
            sb.append(' ');
            PayUtils payUtils = PayUtils.INSTANCE;
            Integer num = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target;
            Intrinsics.checkNotNullExpressionValue(num, "getInstance(requireConte…tnessGoalSleepData.target");
            sb.append(payUtils.getHourMinuteFormatFromMinutes(num.intValue()));
            textView.setText(sb.toString());
            return;
        }
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        if (i == this.x) {
            TextView textView2 = D.vitalsMainData.tvOutOfStepsSleepValue;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getString(R.string.out_of));
            sb2.append(' ');
            PayUtils payUtils2 = PayUtils.INSTANCE;
            int intValue = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target.intValue();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair2 = this.A;
            if (pair2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair2 = null;
            }
            Calendar first = pair2.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair3 = this.A;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair3;
            }
            sb2.append(payUtils2.getHourMinuteFormatFromMinutes(intValue * themesUtils.getDateDifference(first, pair.getSecond())));
            textView2.setText(sb2.toString());
        } else if (i == this.y) {
            TextView textView3 = D.vitalsMainData.tvOutOfStepsSleepValue;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(getString(R.string.out_of));
            sb3.append(' ');
            PayUtils payUtils3 = PayUtils.INSTANCE;
            int intValue2 = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData().target.intValue();
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair4 = this.A;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            Calendar first2 = pair4.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair5 = this.A;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair5;
            }
            sb3.append(payUtils3.getHourMinuteFormatFromMinutes(intValue2 * themesUtils2.getDateDifference(first2, pair.getSecond())));
            textView3.setText(sb3.toString());
        }
    }

    public final void m0() {
        if (isAdded()) {
            l0();
            int i = this.v;
            if (i == this.w) {
                D().vitalsMainData.tvDay.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                D().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.main_text_color));
                D().vitalsMainData.tvDay.setTextAppearance(R.style.bold);
                D().vitalsMainData.tvWeek.setBackground(null);
                D().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                D().vitalsMainData.tvMonth.setBackground(null);
                D().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
                ImageButton imageButton = D().vitalsMainData.ibPrevious;
                Intrinsics.checkNotNullExpressionValue(imageButton, "binding.vitalsMainData.ibPrevious");
                visible(imageButton);
                ImageButton imageButton2 = D().vitalsMainData.ibForward;
                Intrinsics.checkNotNullExpressionValue(imageButton2, "binding.vitalsMainData.ibForward");
                visible(imageButton2);
                ImageView imageView = D().vitalsMainData.ivCenterVitalBg;
                Intrinsics.checkNotNullExpressionValue(imageView, "binding.vitalsMainData.ivCenterVitalBg");
                gone(imageView);
                D().vitalsMainData.progressBar.setProgressDrawable(requireContext().getDrawable(R.drawable.progress_drawable_sleep_thickness_5dp));
                ProgressBar progressBar = D().vitalsMainData.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar, "binding.vitalsMainData.progressBar");
                visible(progressBar);
            } else if (i == this.x) {
                D().vitalsMainData.tvDay.setBackground(null);
                D().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                D().vitalsMainData.tvWeek.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                D().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.main_text_color));
                D().vitalsMainData.tvWeek.setTextAppearance(R.style.bold);
                D().vitalsMainData.tvMonth.setBackground(null);
                D().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvMonth.setTextAppearance(R.style.regular);
                ImageButton imageButton3 = D().vitalsMainData.ibPrevious;
                Intrinsics.checkNotNullExpressionValue(imageButton3, "binding.vitalsMainData.ibPrevious");
                visible(imageButton3);
                ImageButton imageButton4 = D().vitalsMainData.ibForward;
                Intrinsics.checkNotNullExpressionValue(imageButton4, "binding.vitalsMainData.ibForward");
                visible(imageButton4);
                ImageView imageView2 = D().vitalsMainData.ivCenterVitalBg;
                Intrinsics.checkNotNullExpressionValue(imageView2, "binding.vitalsMainData.ivCenterVitalBg");
                gone(imageView2);
                D().vitalsMainData.progressBar.setProgressDrawable(requireContext().getDrawable(R.drawable.progress_drawable_sleep_thickness_5dp));
                ProgressBar progressBar2 = D().vitalsMainData.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar2, "binding.vitalsMainData.progressBar");
                visible(progressBar2);
            } else {
                D().vitalsMainData.tvDay.setBackground(null);
                D().vitalsMainData.tvDay.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvDay.setTextAppearance(R.style.regular);
                D().vitalsMainData.tvWeek.setBackground(null);
                D().vitalsMainData.tvWeek.setTextColor(requireContext().getColor(R.color.secondary_text_color));
                D().vitalsMainData.tvWeek.setTextAppearance(R.style.regular);
                D().vitalsMainData.tvMonth.setBackground(requireContext().getDrawable(R.drawable.red_gradient_bottom_border_background));
                D().vitalsMainData.tvMonth.setTextColor(requireContext().getColor(R.color.main_text_color));
                D().vitalsMainData.tvMonth.setTextAppearance(R.style.bold);
                ImageButton imageButton5 = D().vitalsMainData.ibPrevious;
                Intrinsics.checkNotNullExpressionValue(imageButton5, "binding.vitalsMainData.ibPrevious");
                visible(imageButton5);
                ImageButton imageButton6 = D().vitalsMainData.ibForward;
                Intrinsics.checkNotNullExpressionValue(imageButton6, "binding.vitalsMainData.ibForward");
                visible(imageButton6);
                ImageView imageView3 = D().vitalsMainData.ivCenterVitalBg;
                Intrinsics.checkNotNullExpressionValue(imageView3, "binding.vitalsMainData.ivCenterVitalBg");
                gone(imageView3);
                D().vitalsMainData.progressBar.setProgressDrawable(requireContext().getDrawable(R.drawable.progress_drawable_sleep_thickness_5dp));
                ProgressBar progressBar3 = D().vitalsMainData.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar3, "binding.vitalsMainData.progressBar");
                visible(progressBar3);
            }
        }
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog.OnCalendarRangeSelector
    public void onCalendarRangeSelected(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        this.A = new Pair<>(fromDate, toDate);
        G(fromDate, toDate);
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalSleepBinding.inflate(inflater, viewGroup, false);
        View root = D().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
    }

    @Override // com.coveiot.android.sleepenergyscore.feedback.ContractFeedBackQuestionsList
    public void onReceiveQuestionList(@Nullable ArrayList<FeedbackQuetionnarieModel> arrayList) {
        if (isAdded() && isVisible()) {
            if (arrayList != null && arrayList.size() > 0) {
                LinearLayout linearLayout = D().viewPagerLayout;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.viewPagerLayout");
                visible(linearLayout);
                L(arrayList);
                return;
            }
            LinearLayout linearLayout2 = D().viewPagerLayout;
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.viewPagerLayout");
            gone(linearLayout2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        CoveEventBusManager.getInstance().getEventBus().register(this);
        l0();
    }

    @Subscribe
    public final void onSleepScoreCalcultaed(@NotNull SleepScoreEventData sleepScoreEventData) {
        Intrinsics.checkNotNullParameter(sleepScoreEventData, "sleepScoreEventData");
        i0();
    }

    @Subscribe
    public final void onSleepScoreFeedbackUiUpdate(@NotNull SleepScoreFeedbackEventData sleepScoreFeedbackEventData) {
        Intrinsics.checkNotNullParameter(sleepScoreFeedbackEventData, "sleepScoreFeedbackEventData");
        CoveEventBusManager.getInstance().getEventBus().post(new DismissLoader());
        FragmentSleepViewModel fragmentSleepViewModel = this.n;
        if (fragmentSleepViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSleepViewModel = null;
        }
        fragmentSleepViewModel.getFeedbackQuestionnarieList();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
    }

    public final void setBarGraphValues(@NotNull ArrayList<BarEntry> entries, @NotNull ArrayList<String> labels, boolean z) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        Intrinsics.checkNotNullParameter(labels, "labels");
        if (!z) {
            ConstraintLayout constraintLayout = D().sleepGraphBg;
            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.sleepGraphBg");
            visible(constraintLayout);
            RoundedBarChart roundedBarChart = D().sleepGraphChart;
            Intrinsics.checkNotNullExpressionValue(roundedBarChart, "binding.sleepGraphChart");
            gone(roundedBarChart);
            TextView textView = D().tvNoSleepDataFound;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoSleepDataFound");
            visible(textView);
            return;
        }
        ConstraintLayout constraintLayout2 = D().sleepGraphBg;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.sleepGraphBg");
        visible(constraintLayout2);
        RoundedBarChart roundedBarChart2 = D().sleepGraphChart;
        Intrinsics.checkNotNullExpressionValue(roundedBarChart2, "binding.sleepGraphChart");
        visible(roundedBarChart2);
        TextView textView2 = D().tvNoSleepDataFound;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoSleepDataFound");
        gone(textView2);
        try {
            D().sleepGraphChart.highlightValues(new Highlight[]{new Highlight(entries.size() - 1, 0, 0)});
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Object data = entries.get(entries.size() - 1).getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.SleepData");
        SleepData sleepData = (SleepData) data;
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.setTime(AppUtils.parseDate(sleepData.getDwmValue(), "yyyy-MM-dd"));
        Intrinsics.checkNotNullExpressionValue(cal, "cal");
        this.p = cal;
        BarDataSet barDataSet = new BarDataSet(entries, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(getResources().getColor(R.color.background_color_dark));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.background_color_dark));
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
        barData.setBarWidth(0.4f);
        barData.setDrawValues(false);
        D().sleepGraphChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        D().sleepGraphChart.setDrawMarkers(true);
        D().sleepGraphChart.setData(barData);
        D().sleepGraphChart.setFitBars(true);
        D().sleepGraphChart.setDrawValueAboveBar(false);
        D().sleepGraphChart.setDrawGridBackground(false);
        D().sleepGraphChart.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        D().sleepGraphChart.setDescription(description);
        barData.setValueTextColor(getResources().getColor(R.color.steps_graph_color));
        D().sleepGraphChart.getPaint(7).setColor(getResources().getColor(R.color.steps_graph_color));
        D().sleepGraphChart.setDrawBorders(false);
        D().sleepGraphChart.setAutoScaleMinMaxEnabled(false);
        YAxis axisLeft = D().sleepGraphChart.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setValueFormatter(new HoursFormattor(this, entries));
        axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
        D().sleepGraphChart.getAxisRight().setEnabled(false);
        XAxis xAxis = D().sleepGraphChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
        D().sleepGraphChart.getAxisLeft().setStartAtZero(true);
        D().sleepGraphChart.getAxisRight().setStartAtZero(true);
        D().sleepGraphChart.getAxisLeft().setTextColor(getResources().getColor(R.color.color_757575));
        D().sleepGraphChart.getXAxis().setTextColor(getResources().getColor(R.color.color_757575));
        D().sleepGraphChart.getLegend().setTextColor(getResources().getColor(R.color.color_757575));
        D().sleepGraphChart.getLegend().setEnabled(false);
        D().sleepGraphChart.setVisibleXRangeMaximum(5.0f);
        D().sleepGraphChart.setVisibleXRangeMinimum(5.0f);
        String deep_sleep = sleepData.getDeep_sleep();
        Intrinsics.checkNotNullExpressionValue(deep_sleep, "sleepData.deep_sleep");
        int parseInt = Integer.parseInt(deep_sleep);
        String light_sleep = sleepData.getLight_sleep();
        Intrinsics.checkNotNullExpressionValue(light_sleep, "sleepData.light_sleep");
        int parseInt2 = parseInt + Integer.parseInt(light_sleep);
        String awake = sleepData.getAwake();
        Intrinsics.checkNotNullExpressionValue(awake, "sleepData.awake");
        if (parseInt2 + Integer.parseInt(awake) == 0) {
            RoundedBarChart roundedBarChart3 = D().sleepGraphChart;
            YAxis.AxisDependency axisDependency = YAxis.AxisDependency.LEFT;
            roundedBarChart3.setVisibleYRangeMaximum(0.0f, axisDependency);
            D().sleepGraphChart.setVisibleYRangeMinimum(8.0f, axisDependency);
        }
        D().sleepGraphChart.moveViewToX(entries.size());
        D().sleepGraphChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment$setBarGraphValues$1
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
                LogHelper.d("VitalsSleepFragment", "onNothingSelected");
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e3, @NotNull Highlight h) {
                Float f;
                Highlight highlight;
                Highlight highlight2;
                Float f2;
                Intrinsics.checkNotNullParameter(e3, "e");
                Intrinsics.checkNotNullParameter(h, "h");
                f = VitalsSleepFragment.this.K;
                if (f != null) {
                    float yPx = h.getYPx();
                    f2 = VitalsSleepFragment.this.K;
                    Intrinsics.checkNotNull(f2);
                    if (yPx <= f2.floatValue()) {
                        Calendar cal2 = Calendar.getInstance();
                        cal2.set(11, 0);
                        cal2.set(12, 0);
                        cal2.set(13, 0);
                        Object data2 = e3.getData();
                        Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.SleepData");
                        cal2.setTime(AppUtils.parseDate(((SleepData) data2).getDwmValue(), "yyyy-MM-dd"));
                        VitalsSleepFragment vitalsSleepFragment = VitalsSleepFragment.this;
                        Intrinsics.checkNotNullExpressionValue(cal2, "cal");
                        vitalsSleepFragment.p = cal2;
                        VitalsSleepFragment vitalsSleepFragment2 = VitalsSleepFragment.this;
                        FragmentSleepHistoryViewModel fragmentSleepHistoryViewModel = vitalsSleepFragment2.o;
                        if (fragmentSleepHistoryViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("historyViewModel");
                            fragmentSleepHistoryViewModel = null;
                        }
                        HashMap<String, List<SleepDataModelForLastNight>> mLastQueriedSleepData = fragmentSleepHistoryViewModel.getMLastQueriedSleepData();
                        Object data3 = e3.getData();
                        Intrinsics.checkNotNull(data3, "null cannot be cast to non-null type com.coveiot.android.leonardo.dashboard.health.model.SleepData");
                        vitalsSleepFragment2.updateHourlyLevelData(mLastQueriedSleepData.get(((SleepData) data3).getDwmValue()));
                        VitalsSleepFragment.this.L = h;
                        return;
                    }
                }
                highlight = VitalsSleepFragment.this.L;
                if (highlight != null) {
                    RoundedBarChart roundedBarChart4 = VitalsSleepFragment.this.D().sleepGraphChart;
                    highlight2 = VitalsSleepFragment.this.L;
                    roundedBarChart4.highlightValues(new Highlight[]{highlight2});
                }
            }
        });
        D().sleepGraphChart.setOnChartGestureListener(new OnChartGestureListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment$setBarGraphValues$2
            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartDoubleTapped(@NotNull MotionEvent me) {
                Intrinsics.checkNotNullParameter(me, "me");
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartFling(@Nullable MotionEvent motionEvent, @Nullable MotionEvent motionEvent2, float f, float f2) {
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartGestureEnd(@Nullable MotionEvent motionEvent, @Nullable ChartTouchListener.ChartGesture chartGesture) {
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartGestureStart(@Nullable MotionEvent motionEvent, @Nullable ChartTouchListener.ChartGesture chartGesture) {
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartLongPressed(@Nullable MotionEvent motionEvent) {
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartScale(@Nullable MotionEvent motionEvent, float f, float f2) {
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartSingleTapped(@NotNull MotionEvent me) {
                Intrinsics.checkNotNullParameter(me, "me");
                Entry entryByTouchPoint = VitalsSleepFragment.this.D().sleepGraphChart.getEntryByTouchPoint(me.getX(), me.getY());
                VitalsSleepFragment.this.K = entryByTouchPoint != null ? Float.valueOf(me.getY()) : null;
            }

            @Override // com.github.mikephil.charting.listener.OnChartGestureListener
            public void onChartTranslate(@Nullable MotionEvent motionEvent, float f, float f2) {
            }
        });
        System.out.println((Object) ("sleephistory** " + D().sleepGraphChart.getData()));
        D().sleepGraphChart.invalidate();
        if (entries.size() > 6) {
            D().sleepGraphChart.moveViewToX(6.0f);
        }
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void setSleepData(@Nullable SleepData sleepData) {
        this.r = sleepData;
    }

    public final void share() {
        Integer num;
        Integer num2;
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        int i = this.v;
        Pair<? extends Calendar, ? extends Calendar> pair = null;
        Pair<? extends Calendar, ? extends Calendar> pair2 = null;
        if (i == this.w) {
            shareData.setGraphType(getString(R.string.share_daily));
            shareData.setDwmValue(simpleDateFormat.format(this.p.getTime()));
            StringBuilder sb = new StringBuilder();
            sb.append(getString(R.string.out_of));
            sb.append(' ');
            PayUtils payUtils = PayUtils.INSTANCE;
            FitnessGoal fitnessGoalSleepData = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            Integer num3 = fitnessGoalSleepData != null ? fitnessGoalSleepData.target : null;
            sb.append(payUtils.getHourMinuteFormatFromMinutes(num3 != null ? num3.intValue() : 480));
            shareData.setTotalSleep(sb.toString());
        } else if (i == this.x) {
            shareData.setGraphType(getString(R.string.share_weekly));
            StringBuilder sb2 = new StringBuilder();
            Pair<? extends Calendar, ? extends Calendar> pair3 = this.A;
            if (pair3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair3 = null;
            }
            sb2.append(simpleDateFormat.format(pair3.getFirst().getTime()));
            sb2.append(Soundex.SILENT_MARKER);
            Pair<? extends Calendar, ? extends Calendar> pair4 = this.A;
            if (pair4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair4 = null;
            }
            sb2.append(simpleDateFormat.format(pair4.getSecond().getTime()));
            shareData.setDwmValue(sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(getString(R.string.out_of));
            sb3.append(' ');
            PayUtils payUtils2 = PayUtils.INSTANCE;
            FitnessGoal fitnessGoalSleepData2 = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            if (fitnessGoalSleepData2 != null && (num2 = fitnessGoalSleepData2.target) != null) {
                r5 = num2.intValue();
            }
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair5 = this.A;
            if (pair5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair5 = null;
            }
            Calendar first = pair5.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair6 = this.A;
            if (pair6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair2 = pair6;
            }
            sb3.append(payUtils2.getHourMinuteFormatFromMinutes(r5 * themesUtils.getDateDifference(first, pair2.getSecond())));
            shareData.setTotalSleep(sb3.toString());
            shareData.setTotalValueTitle(getString(R.string.total_sleeps_taken_week));
        } else if (i == this.y) {
            shareData.setGraphType(getString(R.string.share_monthly));
            StringBuilder sb4 = new StringBuilder();
            Pair<? extends Calendar, ? extends Calendar> pair7 = this.A;
            if (pair7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair7 = null;
            }
            sb4.append(simpleDateFormat.format(pair7.getFirst().getTime()));
            sb4.append(Soundex.SILENT_MARKER);
            Pair<? extends Calendar, ? extends Calendar> pair8 = this.A;
            if (pair8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair8 = null;
            }
            sb4.append(simpleDateFormat.format(pair8.getSecond().getTime()));
            shareData.setDwmValue(sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(getString(R.string.out_of));
            sb5.append(' ');
            PayUtils payUtils3 = PayUtils.INSTANCE;
            FitnessGoal fitnessGoalSleepData3 = UserDataManager.getInstance(requireContext()).getFitnessGoalSleepData();
            if (fitnessGoalSleepData3 != null && (num = fitnessGoalSleepData3.target) != null) {
                r5 = num.intValue();
            }
            ThemesUtils themesUtils2 = ThemesUtils.INSTANCE;
            Pair<? extends Calendar, ? extends Calendar> pair9 = this.A;
            if (pair9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
                pair9 = null;
            }
            Calendar first2 = pair9.getFirst();
            Pair<? extends Calendar, ? extends Calendar> pair10 = this.A;
            if (pair10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedDateRange");
            } else {
                pair = pair10;
            }
            sb5.append(payUtils3.getHourMinuteFormatFromMinutes(r5 * themesUtils2.getDateDifference(first2, pair.getSecond())));
            shareData.setTotalSleep(sb5.toString());
            shareData.setTotalValueTitle(getString(R.string.total_sleeps_taken_month));
        }
        if (this.v == this.w) {
            String obj = D().tvSleepScore.getText().toString();
            if (((obj == null || obj.length() == 0) ? 1 : null) == null) {
                shareData.setSleepScore(Integer.parseInt(D().tvSleepScore.getText().toString()));
            } else {
                shareData.setSleepScore(0);
            }
            if (!BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                if (!companion.isSmaDevice(requireActivity)) {
                    String obj2 = D().deepSleep.getText().toString();
                    String string = getResources().getString(R.string.hr);
                    Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.hr)");
                    String replace = kotlin.text.m.replace(obj2, string, "", true);
                    String string2 = getResources().getString(R.string.min);
                    Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.min)");
                    String replace2 = kotlin.text.m.replace(kotlin.text.m.replace(replace, string2, "", true), "-", "", true);
                    String string3 = getResources().getString(R.string.hrs);
                    Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.hrs)");
                    String obj3 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace2, string3, "", true)).toString();
                    String obj4 = D().lightSleep.getText().toString();
                    String string4 = getResources().getString(R.string.hr);
                    Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.hr)");
                    String replace3 = kotlin.text.m.replace(obj4, string4, "", true);
                    String string5 = getResources().getString(R.string.min);
                    Intrinsics.checkNotNullExpressionValue(string5, "resources.getString(R.string.min)");
                    String replace4 = kotlin.text.m.replace(kotlin.text.m.replace(replace3, string5, "", true), "-", "", true);
                    String string6 = getResources().getString(R.string.hrs);
                    Intrinsics.checkNotNullExpressionValue(string6, "resources.getString(R.string.hrs)");
                    String obj5 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace4, string6, "", true)).toString();
                    String obj6 = D().awake.getText().toString();
                    String string7 = getResources().getString(R.string.hr);
                    Intrinsics.checkNotNullExpressionValue(string7, "resources.getString(R.string.hr)");
                    String replace5 = kotlin.text.m.replace(obj6, string7, "", true);
                    String string8 = getResources().getString(R.string.min);
                    Intrinsics.checkNotNullExpressionValue(string8, "resources.getString(R.string.min)");
                    String replace6 = kotlin.text.m.replace(kotlin.text.m.replace(replace5, string8, "", true), "-", "", true);
                    String string9 = getResources().getString(R.string.hrs);
                    Intrinsics.checkNotNullExpressionValue(string9, "resources.getString(R.string.hrs)");
                    String obj7 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace6, string9, "", true)).toString();
                    AppConstants appConstants = AppConstants.ZERO;
                    shareData.setDeepSleep(appConstants.getValue());
                    shareData.setLightSleep(appConstants.getValue());
                    shareData.setAwake(appConstants.getValue());
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) obj3, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
                    if (split$default.size() == 2) {
                        shareData.setDeepSleep(String.valueOf((Integer.parseInt((String) split$default.get(0)) * 60) + Integer.parseInt((String) split$default.get(1))));
                    }
                    List split$default2 = StringsKt__StringsKt.split$default((CharSequence) obj5, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
                    if (split$default2.size() == 2) {
                        shareData.setLightSleep(String.valueOf((Integer.parseInt((String) split$default2.get(0)) * 60) + Integer.parseInt((String) split$default2.get(1))));
                    }
                    List split$default3 = StringsKt__StringsKt.split$default((CharSequence) obj7, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
                    if (split$default3.size() == 2) {
                        shareData.setAwake(String.valueOf((Integer.parseInt((String) split$default3.get(0)) * 60) + Integer.parseInt((String) split$default3.get(1))));
                    }
                    shareData.setTarget(24);
                }
            }
            String replace7 = kotlin.text.m.replace(D().deepSleep.getText().toString(), "-", "", true);
            String string10 = getResources().getString(R.string.hrs);
            Intrinsics.checkNotNullExpressionValue(string10, "resources.getString(R.string.hrs)");
            String obj8 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace7, string10, "", true)).toString();
            String replace8 = kotlin.text.m.replace(D().lightSleep.getText().toString(), "-", "", true);
            String string11 = getResources().getString(R.string.hrs);
            Intrinsics.checkNotNullExpressionValue(string11, "resources.getString(R.string.hrs)");
            String obj9 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace8, string11, "", true)).toString();
            String replace9 = kotlin.text.m.replace(D().awake.getText().toString(), "-", "", true);
            String string12 = getResources().getString(R.string.hrs);
            Intrinsics.checkNotNullExpressionValue(string12, "resources.getString(R.string.hrs)");
            String obj10 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace9, string12, "", true)).toString();
            String replace10 = kotlin.text.m.replace(D().remSleep.getText().toString(), "-", "", true);
            String string13 = getResources().getString(R.string.hrs);
            Intrinsics.checkNotNullExpressionValue(string13, "resources.getString(R.string.hrs)");
            String obj11 = StringsKt__StringsKt.trim(kotlin.text.m.replace(replace10, string13, "", true)).toString();
            AppConstants appConstants2 = AppConstants.ZERO;
            shareData.setDeepSleep(appConstants2.getValue());
            shareData.setLightSleep(appConstants2.getValue());
            shareData.setAwake(appConstants2.getValue());
            shareData.setRemSleep(appConstants2.getValue());
            List split$default4 = StringsKt__StringsKt.split$default((CharSequence) obj8, new String[]{":"}, false, 0, 6, (Object) null);
            if (split$default4.size() == 2) {
                shareData.setDeepSleep(String.valueOf((Integer.parseInt((String) split$default4.get(0)) * 60) + Integer.parseInt((String) split$default4.get(1))));
            }
            List split$default5 = StringsKt__StringsKt.split$default((CharSequence) obj9, new String[]{":"}, false, 0, 6, (Object) null);
            if (split$default5.size() == 2) {
                shareData.setLightSleep(String.valueOf((Integer.parseInt((String) split$default5.get(0)) * 60) + Integer.parseInt((String) split$default5.get(1))));
            }
            List split$default6 = StringsKt__StringsKt.split$default((CharSequence) obj10, new String[]{":"}, false, 0, 6, (Object) null);
            if (split$default6.size() == 2) {
                shareData.setAwake(String.valueOf((Integer.parseInt((String) split$default6.get(0)) * 60) + Integer.parseInt((String) split$default6.get(1))));
            }
            List split$default7 = StringsKt__StringsKt.split$default((CharSequence) obj11, new String[]{":"}, false, 0, 6, (Object) null);
            if (split$default7.size() == 2) {
                shareData.setRemSleep(String.valueOf((Integer.parseInt((String) split$default7.get(0)) * 60) + Integer.parseInt((String) split$default7.get(1))));
            }
            shareData.setTarget(24);
        } else {
            shareData.setWeekMonthTotalSleep(D().vitalsMainData.tvAvgStepsSleepValue.getText().toString());
        }
        shareData.setAvgData(D().vitalsMainData.tvAvgStepSleepValue.getText().toString());
        shareData.setTotalSleepFormatted(D().vitalsMainData.tvStepSleepTotalValue.getText().toString());
        AppNavigator.Companion companion2 = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string14 = getResources().getString(R.string.sleep);
        Intrinsics.checkNotNullExpressionValue(string14, "resources.getString(R.string.sleep)");
        companion2.navigateToShareScreen(requireContext, shareData, string14);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    public void updateDailyLevelData(@Nullable DailySleepDataAlias dailySleepDataAlias) {
        Intrinsics.checkNotNull(dailySleepDataAlias);
        this.t = dailySleepDataAlias.getDate();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x08d4 A[ADDED_TO_REGION] */
    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSleepDashboard
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateHourlyLevelData(@org.jetbrains.annotations.Nullable java.util.List<? extends com.coveiot.covedb.sleep.model.SleepDataModelForLastNight> r36) {
        /*
            Method dump skipped, instructions count: 2892
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsSleepFragment.updateHourlyLevelData(java.util.List):void");
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateRangeLevelData(@NotNull List<RespiratoryRateListBean> dailyRespiratoryRate) {
        Intrinsics.checkNotNullParameter(dailyRespiratoryRate, "dailyRespiratoryRate");
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateDailyLevelData(@Nullable DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new e(dailyRespiratoryRateEntity, this, null), 2, null);
    }
}
