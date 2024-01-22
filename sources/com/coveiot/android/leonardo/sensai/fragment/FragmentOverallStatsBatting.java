package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBattingBinding;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIShareScreen;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.RoundedBarChart;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.compundview.CustomMarkerViewPieChart;
import com.coveiot.android.theme.compundview.CustomMarkerViewVitals;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsData;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHitPercentage;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentOverallStatsBatting extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public OverallStatsViewModel m;
    @Nullable
    public FragmentOverallStatsBattingBinding n;
    @Nullable
    public GetOverallStatsResponse o;
    public int p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public OverallStatsData q = new OverallStatsData();

    /* loaded from: classes5.dex */
    public final class BattingChartValueFormatter implements IValueFormatter {
        public BattingChartValueFormatter(FragmentOverallStatsBatting fragmentOverallStatsBatting) {
        }

        @Override // com.github.mikephil.charting.formatter.IValueFormatter
        @NotNull
        public String getFormattedValue(float f, @Nullable Entry entry, int i, @Nullable ViewPortHandler viewPortHandler) {
            if (f == 0.0f) {
                return "";
            }
            return ((int) f) + " %";
        }
    }

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentOverallStatsBatting newInstance(int i) {
            FragmentOverallStatsBatting fragmentOverallStatsBatting = new FragmentOverallStatsBatting();
            Bundle bundle = new Bundle();
            bundle.putInt("param1", i);
            fragmentOverallStatsBatting.setArguments(bundle);
            return fragmentOverallStatsBatting;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<GetOverallStatsResponse, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetOverallStatsResponse getOverallStatsResponse) {
            invoke2(getOverallStatsResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(GetOverallStatsResponse getOverallStatsResponse) {
            FragmentOverallStatsBatting.this.o = getOverallStatsResponse;
            FragmentOverallStatsBatting.this.q();
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<GetOverallStatsResponse, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetOverallStatsResponse getOverallStatsResponse) {
            invoke2(getOverallStatsResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(GetOverallStatsResponse getOverallStatsResponse) {
            FragmentOverallStatsBatting.this.o = getOverallStatsResponse;
            FragmentOverallStatsBatting.this.q();
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<GetOverallStatsResponse, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetOverallStatsResponse getOverallStatsResponse) {
            invoke2(getOverallStatsResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(GetOverallStatsResponse getOverallStatsResponse) {
            FragmentOverallStatsBatting.this.o = getOverallStatsResponse;
            FragmentOverallStatsBatting.this.q();
        }
    }

    public static final void p(FragmentOverallStatsBatting this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.w(this$0.q);
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void t(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void u(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
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

    public final FragmentOverallStatsBattingBinding o() {
        FragmentOverallStatsBattingBinding fragmentOverallStatsBattingBinding = this.n;
        Intrinsics.checkNotNull(fragmentOverallStatsBattingBinding);
        return fragmentOverallStatsBattingBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.p = arguments.getInt("param1");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentOverallStatsBattingBinding.inflate(inflater, viewGroup, false);
        return o().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.m = (OverallStatsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(OverallStatsViewModel.class);
        setPositionFromActivity(this.p);
        ConstraintLayout constraintLayout = o().sensAiDetails.clSelectedOptionValues;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.sensAiDetails.clSelectedOptionValues");
        visible(constraintLayout);
        o().sensAiDetails.ibShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStatsBatting.p(FragmentOverallStatsBatting.this, view2);
            }
        });
    }

    public final void q() {
        String sb;
        Resources resources;
        Resources resources2;
        o().sensAiDetails.clOverallStatsDetails.setVisibility(0);
        o().sensAiDetails.ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_batting));
        o().sensAiDetails.tvTitle.setText(requireContext().getString(R.string.batting));
        GetOverallStatsResponse getOverallStatsResponse = this.o;
        if (getOverallStatsResponse != null) {
            Intrinsics.checkNotNull(getOverallStatsResponse);
            if (getOverallStatsResponse.getOverallStatsData() != null) {
                ConstraintLayout constraintLayout = o().clHitAnalysis;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clHitAnalysis");
                visible(constraintLayout);
                RoundedBarChart roundedBarChart = o().lineChartHitPerc;
                Intrinsics.checkNotNullExpressionValue(roundedBarChart, "binding.lineChartHitPerc");
                visible(roundedBarChart);
                TextView textView = o().tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
                gone(textView);
                LineChart lineChart = o().lineChartArmSpeed;
                Intrinsics.checkNotNullExpressionValue(lineChart, "binding.lineChartArmSpeed");
                visible(lineChart);
                TextView textView2 = o().tvNoDataFoundHandSpeed;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFoundHandSpeed");
                gone(textView2);
                ImageButton imageButton = o().sensAiDetails.ibShare;
                Intrinsics.checkNotNullExpressionValue(imageButton, "binding.sensAiDetails.ibShare");
                visible(imageButton);
                GetOverallStatsResponse getOverallStatsResponse2 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse2);
                OverallStatsData overallStatsData = getOverallStatsResponse2.getOverallStatsData();
                Intrinsics.checkNotNullExpressionValue(overallStatsData, "overallStatsResponse!!.overallStatsData");
                this.q = overallStatsData;
                TextView textView3 = o().sensAiDetails.tvTotalSesionsValue;
                GetOverallStatsResponse getOverallStatsResponse3 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse3);
                textView3.setText(String.valueOf(getOverallStatsResponse3.getOverallStatsData().getTotalSessions()));
                TextView textView4 = o().sensAiDetails.tvGoalAcheivedValue;
                GetOverallStatsResponse getOverallStatsResponse4 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse4);
                textView4.setText(String.valueOf(getOverallStatsResponse4.getOverallStatsData().getTotalTargetAchieved()));
                TextView textView5 = o().sensAiDetails.tvDurationValue;
                StringBuilder sb2 = new StringBuilder();
                PayUtils payUtils = PayUtils.INSTANCE;
                GetOverallStatsResponse getOverallStatsResponse5 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse5);
                sb2.append(payUtils.formatSecondsInHHMMSS(getOverallStatsResponse5.getOverallStatsData().getTotalDuration()));
                sb2.append("  ");
                sb2.append(requireContext().getString(R.string.hrs));
                textView5.setText(sb2.toString());
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                String str = null;
                if (isDistanceUnitInMile.booleanValue()) {
                    StringBuilder sb3 = new StringBuilder();
                    Context context = getContext();
                    if (context != null && (resources2 = context.getResources()) != null) {
                        str = resources2.getString(R.string.mil_per_hours);
                    }
                    sb3.append(str);
                    sb3.append(' ');
                    sb = sb3.toString();
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    Context context2 = getContext();
                    if (context2 != null && (resources = context2.getResources()) != null) {
                        str = resources.getString(R.string.km_per_hours);
                    }
                    sb4.append(str);
                    sb4.append(' ');
                    sb = sb4.toString();
                }
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(requireContext()).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    TextView textView6 = o().tvAvgValue;
                    StringBuilder sb5 = new StringBuilder();
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse6 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse6);
                    sb5.append(payUtils.getSpeedValue(requireContext, kotlin.math.c.roundToInt(getOverallStatsResponse6.getOverallStatsData().getAvgHandSpeed())));
                    sb5.append(' ');
                    sb5.append(sb);
                    sb5.append(' ');
                    textView6.setText(sb5.toString());
                    TextView textView7 = o().tvMaxValue;
                    StringBuilder sb6 = new StringBuilder();
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse7 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse7);
                    sb6.append(payUtils.getSpeedValue(requireContext2, kotlin.math.c.roundToInt(getOverallStatsResponse7.getOverallStatsData().getMaxHandSpeed())));
                    sb6.append(' ');
                    sb6.append(sb);
                    sb6.append(' ');
                    textView7.setText(sb6.toString());
                } else {
                    TextView textView8 = o().tvAvgValue;
                    StringBuilder sb7 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse8 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse8);
                    sb7.append(kotlin.math.c.roundToInt(getOverallStatsResponse8.getOverallStatsData().getAvgHandSpeed()));
                    sb7.append(' ');
                    sb7.append(sb);
                    textView8.setText(sb7.toString());
                    TextView textView9 = o().tvMaxValue;
                    StringBuilder sb8 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse9 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse9);
                    sb8.append(kotlin.math.c.roundToInt(getOverallStatsResponse9.getOverallStatsData().getMaxHandSpeed()));
                    sb8.append(' ');
                    sb8.append(sb);
                    textView9.setText(sb8.toString());
                }
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.total_balls_played);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.total_balls_played)");
                GetOverallStatsResponse getOverallStatsResponse10 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse10);
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{String.valueOf(getOverallStatsResponse10.getOverallStatsData().getTotalSwings())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                SpannableString spannableString = new SpannableString(format);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(requireContext().getColor(R.color.main_text_color));
                GetOverallStatsResponse getOverallStatsResponse11 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse11);
                int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) format, String.valueOf(getOverallStatsResponse11.getOverallStatsData().getTotalSwings()), 0, false, 6, (Object) null);
                GetOverallStatsResponse getOverallStatsResponse12 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse12);
                int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) format, String.valueOf(getOverallStatsResponse12.getOverallStatsData().getTotalSwings()), 0, false, 6, (Object) null);
                GetOverallStatsResponse getOverallStatsResponse13 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse13);
                spannableString.setSpan(foregroundColorSpan, indexOf$default, indexOf$default2 + String.valueOf(getOverallStatsResponse13.getOverallStatsData().getTotalSwings()).length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString);
                o().tvTotalBalls.setText(spannableStringBuilder);
                GetOverallStatsResponse getOverallStatsResponse14 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse14);
                if (getOverallStatsResponse14.getOverallStatsData().getPercentile() == 0) {
                    o().clHitAnalysisPerc.setVisibility(8);
                } else {
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                    String string2 = getString(R.string.avg_hit_perc);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.avg_hit_perc)");
                    StringBuilder sb9 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse15 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse15);
                    sb9.append(getOverallStatsResponse15.getOverallStatsData().getPercentile());
                    sb9.append(" % ");
                    String format2 = String.format(locale, string2, Arrays.copyOf(new Object[]{sb9.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    SpannableString spannableString2 = new SpannableString(format2);
                    ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(requireContext().getColor(R.color.gradient_green_end_color));
                    StringBuilder sb10 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse16 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse16);
                    sb10.append(getOverallStatsResponse16.getOverallStatsData().getPercentile());
                    sb10.append(" % ");
                    int indexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) format2, sb10.toString(), 0, false, 6, (Object) null);
                    StringBuilder sb11 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse17 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse17);
                    sb11.append(getOverallStatsResponse17.getOverallStatsData().getPercentile());
                    sb11.append(" % ");
                    int indexOf$default4 = StringsKt__StringsKt.indexOf$default((CharSequence) format2, sb11.toString(), 0, false, 6, (Object) null);
                    StringBuilder sb12 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse18 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse18);
                    sb12.append(getOverallStatsResponse18.getOverallStatsData().getPercentile());
                    sb12.append(" % ");
                    spannableString2.setSpan(foregroundColorSpan2, indexOf$default3, indexOf$default4 + sb12.toString().length(), 33);
                    spannableStringBuilder2.append((CharSequence) spannableString2);
                    o().tvAvgHit.setText(spannableStringBuilder2);
                }
                GetOverallStatsResponse getOverallStatsResponse19 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse19);
                for (OverallStatsHitPercentage overallStatsHitPercentage : getOverallStatsResponse19.getOverallStatsData().getHitPercentageList()) {
                    overallStatsHitPercentage.getHitPercentage();
                }
                GetOverallStatsResponse getOverallStatsResponse20 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse20);
                if (getOverallStatsResponse20.getOverallStatsData().getHitPercentageList().size() >= 1) {
                    GetOverallStatsResponse getOverallStatsResponse21 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse21);
                    getOverallStatsResponse21.getOverallStatsData().getHitPercentageList().size();
                }
                int totalSwings = this.q.getTotalSwings() - this.q.getTotalHits();
                try {
                    double d = 100;
                    if (!Double.isNaN((this.q.getTotalHits() / this.q.getTotalSwings()) * d)) {
                        v(this.q.getTotalHits(), totalSwings, kotlin.math.c.roundToInt((this.q.getTotalHits() / this.q.getTotalSwings()) * d));
                    } else {
                        v(this.q.getTotalHits(), totalSwings, 0);
                    }
                } catch (Exception unused) {
                    v(this.q.getTotalHits(), totalSwings, 0);
                }
                GetOverallStatsResponse getOverallStatsResponse22 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse22);
                List<OverallStatsHitPercentage> hitPercentageList = getOverallStatsResponse22.getOverallStatsData().getHitPercentageList();
                Intrinsics.checkNotNull(hitPercentageList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHitPercentage>");
                setGraphValues((ArrayList) hitPercentageList);
                GetOverallStatsResponse getOverallStatsResponse23 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse23);
                List<OverallStatsHandSpeed> handSpeedList = getOverallStatsResponse23.getOverallStatsData().getHandSpeedList();
                Intrinsics.checkNotNull(handSpeedList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed>");
                r((ArrayList) handSpeedList);
                dismissProgress();
            }
        }
        o().sensAiDetails.tvTotalSesionsValue.setText("--");
        o().sensAiDetails.tvGoalAcheivedValue.setText("--");
        o().sensAiDetails.tvDurationValue.setText("--");
        o().tvMaxValue.setText("--");
        o().tvAvgValue.setText("--");
        View view = o().view2;
        Intrinsics.checkNotNullExpressionValue(view, "binding.view2");
        gone(view);
        ImageButton imageButton2 = o().sensAiDetails.ibShare;
        Intrinsics.checkNotNullExpressionValue(imageButton2, "binding.sensAiDetails.ibShare");
        gone(imageButton2);
        ConstraintLayout constraintLayout2 = o().clHitAnalysis;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clHitAnalysis");
        gone(constraintLayout2);
        RoundedBarChart roundedBarChart2 = o().lineChartHitPerc;
        Intrinsics.checkNotNullExpressionValue(roundedBarChart2, "binding.lineChartHitPerc");
        gone(roundedBarChart2);
        TextView textView10 = o().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView10, "binding.tvNoDataFound");
        visible(textView10);
        LineChart lineChart2 = o().lineChartArmSpeed;
        Intrinsics.checkNotNullExpressionValue(lineChart2, "binding.lineChartArmSpeed");
        gone(lineChart2);
        TextView textView11 = o().tvNoDataFoundHandSpeed;
        Intrinsics.checkNotNullExpressionValue(textView11, "binding.tvNoDataFoundHandSpeed");
        visible(textView11);
        dismissProgress();
    }

    public final void r(ArrayList<OverallStatsHandSpeed> arrayList) {
        ArrayList<Entry> arrayList2 = new ArrayList();
        Iterator<OverallStatsHandSpeed> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            OverallStatsHandSpeed next = it.next();
            Boolean isDistanceUnitInMile = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
            Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
            if (isDistanceUnitInMile.booleanValue()) {
                PayUtils payUtils = PayUtils.INSTANCE;
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                arrayList2.add(new BarEntry(i, (float) payUtils.getSpeedValue(requireContext, kotlin.math.c.roundToInt(next.getAvgHandSpeed()))));
            } else {
                arrayList2.add(new Entry(i, (float) next.getAvgHandSpeed()));
            }
        }
        if (o().lineChartArmSpeed != null) {
            LineChart lineChart = o().lineChartArmSpeed;
            if (lineChart != null) {
                lineChart.clear();
            }
            ArrayList arrayList3 = new ArrayList();
            for (Entry entry : arrayList2) {
                if (entry.getY() == -1.0f) {
                    arrayList3.add(entry);
                }
            }
            arrayList2.removeAll(arrayList3);
            LineDataSet lineDataSet = new LineDataSet(arrayList2, "");
            lineDataSet.setCircleColor(getResources().getColor(R.color.lightsleep_color));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            lineDataSet.setColor(getResources().getColor(R.color.lightsleep_color));
            lineDataSet.setHighLightColor(getResources().getColor(17170445));
            lineDataSet.setFillColor(getResources().getColor(R.color.color_33E51C23));
            LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
            lineDataSet2.setCircleColor(getResources().getColor(R.color.transparent));
            lineDataSet2.setDrawCircleHole(false);
            lineDataSet2.setDrawValues(false);
            lineDataSet2.setValueTextColor(getResources().getColor(17170445));
            lineDataSet2.setColor(getResources().getColor(17170445));
            lineDataSet2.setHighLightColor(getResources().getColor(17170445));
            LineData lineData = new LineData(lineDataSet, lineDataSet2);
            lineData.setDrawValues(false);
            lineData.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            LineChart lineChart2 = o().lineChartArmSpeed;
            YAxis axisLeft = lineChart2 != null ? lineChart2.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(1.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(100.0f);
            axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
            LineChart lineChart3 = o().lineChartArmSpeed;
            YAxis axisRight = lineChart3 != null ? lineChart3.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            LineChart lineChart4 = o().lineChartArmSpeed;
            XAxis xAxis = lineChart4 != null ? lineChart4.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis);
            xAxis.setEnabled(true);
            xAxis.setGranularity(1.0f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setAxisMinimum(1.0f);
            xAxis.setGridColor(getResources().getColor(R.color.color_757575));
            xAxis.setDrawLabels(true);
            xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
            LineChart lineChart5 = o().lineChartArmSpeed;
            Legend legend = lineChart5 != null ? lineChart5.getLegend() : null;
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            LineChart lineChart6 = o().lineChartArmSpeed;
            if (lineChart6 != null) {
                lineChart6.setData(lineData);
            }
            LineChart lineChart7 = o().lineChartArmSpeed;
            if (lineChart7 != null) {
                lineChart7.setDrawGridBackground(false);
            }
            LineChart lineChart8 = o().lineChartArmSpeed;
            Description description = lineChart8 != null ? lineChart8.getDescription() : null;
            if (description != null) {
                description.setEnabled(false);
            }
            LineChart lineChart9 = o().lineChartArmSpeed;
            if (lineChart9 != null) {
                lineChart9.setNoDataText("");
            }
            LineChart lineChart10 = o().lineChartArmSpeed;
            if (lineChart10 != null) {
                lineChart10.setDrawBorders(false);
            }
            LineChart lineChart11 = o().lineChartArmSpeed;
            if (lineChart11 != null) {
                lineChart11.setAutoScaleMinMaxEnabled(true);
            }
            LineChart lineChart12 = o().lineChartArmSpeed;
            if (lineChart12 != null) {
                lineChart12.setPinchZoom(false);
            }
            LineChart lineChart13 = o().lineChartArmSpeed;
            if (lineChart13 != null) {
                lineChart13.setScaleEnabled(false);
            }
            LineChart lineChart14 = o().lineChartArmSpeed;
            if (lineChart14 != null) {
                lineChart14.setVisibleXRangeMinimum(5.0f);
            }
            LineChart lineChart15 = o().lineChartArmSpeed;
            YAxis axisLeft2 = lineChart15 != null ? lineChart15.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            LineChart lineChart16 = o().lineChartArmSpeed;
            YAxis axisRight2 = lineChart16 != null ? lineChart16.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            LineChart lineChart17 = o().lineChartArmSpeed;
            YAxis axisLeft3 = lineChart17 != null ? lineChart17.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            LineChart lineChart18 = o().lineChartArmSpeed;
            XAxis xAxis2 = lineChart18 != null ? lineChart18.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            o().lineChartArmSpeed.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentOverallStatsBatting$setHandSpeedLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        Context context = FragmentOverallStatsBatting.this.getContext();
                        Toast.makeText(context, "Hand Speed: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            LineChart lineChart19 = o().lineChartArmSpeed;
            if (lineChart19 != null) {
                lineChart19.invalidate();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0057 A[Catch: Exception -> 0x0074, TRY_LEAVE, TryCatch #0 {Exception -> 0x0074, blocks: (B:20:0x0037, B:22:0x0045, B:24:0x004b, B:30:0x0057), top: B:36:0x0037 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setCustomLegend() {
        /*
            r14 = this;
            com.coveiot.android.boat.databinding.FragmentOverallStatsBattingBinding r0 = r14.o()
            if (r0 == 0) goto L99
            com.github.mikephil.charting.charts.PieChart r1 = r0.pieChart
            if (r1 == 0) goto L99
            androidx.recyclerview.widget.RecyclerView r2 = r0.gridView
            if (r2 == 0) goto L99
            com.github.mikephil.charting.components.Legend r1 = r1.getLegend()
            r2 = 0
            r1.setEnabled(r2)
            com.github.mikephil.charting.charts.PieChart r1 = r0.pieChart
            com.github.mikephil.charting.components.Legend r1 = r1.getLegend()
            java.lang.String[] r3 = r1.getLabels()
            r4 = 1
            if (r3 == 0) goto L2e
            int r3 = r3.length
            if (r3 != 0) goto L28
            r3 = r4
            goto L29
        L28:
            r3 = r2
        L29:
            if (r3 == 0) goto L2c
            goto L2e
        L2c:
            r3 = r2
            goto L2f
        L2e:
            r3 = r4
        L2f:
            if (r3 != 0) goto L99
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r5 = 3
            java.lang.String[] r6 = r1.getLabels()     // Catch: java.lang.Exception -> L74
            java.lang.String r7 = "legend.labels"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Exception -> L74
            int r7 = r6.length     // Catch: java.lang.Exception -> L74
            r8 = r2
            r9 = r8
        L43:
            if (r8 >= r7) goto L78
            r10 = r6[r8]     // Catch: java.lang.Exception -> L74
            int r11 = r9 + 1
            if (r10 == 0) goto L54
            int r12 = r10.length()     // Catch: java.lang.Exception -> L74
            if (r12 != 0) goto L52
            goto L54
        L52:
            r12 = r2
            goto L55
        L54:
            r12 = r4
        L55:
            if (r12 != 0) goto L70
            com.coveiot.android.sleepenergyscore.energymeter.model.DrainGainGridItem r12 = new com.coveiot.android.sleepenergyscore.energymeter.model.DrainGainGridItem     // Catch: java.lang.Exception -> L74
            r13 = 0
            r12.<init>(r13, r13, r5, r13)     // Catch: java.lang.Exception -> L74
            r12.setName(r10)     // Catch: java.lang.Exception -> L74
            int[] r10 = r1.getColors()     // Catch: java.lang.Exception -> L74
            r9 = r10[r9]     // Catch: java.lang.Exception -> L74
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch: java.lang.Exception -> L74
            r12.setColor(r9)     // Catch: java.lang.Exception -> L74
            r3.add(r12)     // Catch: java.lang.Exception -> L74
        L70:
            int r8 = r8 + 1
            r9 = r11
            goto L43
        L74:
            r1 = move-exception
            r1.printStackTrace()
        L78:
            androidx.recyclerview.widget.RecyclerView r1 = r0.gridView
            androidx.recyclerview.widget.GridLayoutManager r2 = new androidx.recyclerview.widget.GridLayoutManager
            android.content.Context r4 = r14.requireContext()
            r2.<init>(r4, r5)
            r1.setLayoutManager(r2)
            androidx.recyclerview.widget.RecyclerView r0 = r0.gridView
            com.coveiot.android.sleepenergyscore.energymeter.adapters.EnergyScoreLegendGridAdapter1 r1 = new com.coveiot.android.sleepenergyscore.energymeter.adapters.EnergyScoreLegendGridAdapter1
            androidx.fragment.app.FragmentActivity r2 = r14.requireActivity()
            java.lang.String r4 = "requireActivity()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r1.<init>(r2, r3)
            r0.setAdapter(r1)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.sensai.fragment.FragmentOverallStatsBatting.setCustomLegend():void");
    }

    public final void setGraphValues(@NotNull ArrayList<OverallStatsHitPercentage> hitPerc) {
        int maximumYValue;
        Intrinsics.checkNotNullParameter(hitPerc, "hitPerc");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<OverallStatsHitPercentage> it = hitPerc.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            OverallStatsHitPercentage next = it.next();
            arrayList.add(new BarEntry(i, next.getHitPercentage()));
            arrayList2.add(String.valueOf(next.getDate()));
        }
        BarDataSet barDataSet = new BarDataSet(arrayList, AppConstants.EMPTY_STRING.getValue());
        barDataSet.setDrawValues(false);
        barDataSet.setBarShadowColor(getResources().getColor(R.color.steps_graph_color));
        barDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        barDataSet.setColor(getResources().getColor(R.color.steps_graph_color));
        if (barDataSet.getEntryCount() > 0) {
            int entryCount = barDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i2 = 0; i2 < entryCount; i2++) {
                iArr[i2] = getResources().getColor(R.color.steps_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.steps_graph_color);
            barDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        BarData barData = new BarData(barDataSet);
        barData.setDrawValues(false);
        o().lineChartHitPerc.setData(barData);
        o().lineChartHitPerc.setFitBars(true);
        o().lineChartHitPerc.setDrawValueAboveBar(false);
        o().lineChartHitPerc.setDrawGridBackground(false);
        o().lineChartHitPerc.setScaleEnabled(false);
        Description description = new Description();
        description.setText("");
        o().lineChartHitPerc.setDescription(description);
        barData.setValueTextColor(getResources().getColor(R.color.steps_graph_color));
        o().lineChartHitPerc.getPaint(7).setColor(getResources().getColor(R.color.steps_graph_color));
        o().lineChartHitPerc.setDrawBorders(false);
        o().lineChartHitPerc.setAutoScaleMinMaxEnabled(false);
        Context context = getContext();
        String string = getString(R.string.sens_ai);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sens_ai)");
        CustomMarkerViewVitals customMarkerViewVitals = new CustomMarkerViewVitals(context, R.layout.custom_marker_view_steps_hr, string, 6, arrayList2);
        customMarkerViewVitals.setChartView(o().lineChartHitPerc);
        o().lineChartHitPerc.setMarker(customMarkerViewVitals);
        YAxis axisLeft = o().lineChartHitPerc.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setYOffset(10.0f);
        axisLeft.setXOffset(15.0f);
        if (PayUtils.INSTANCE.getMaximumYValue(arrayList) == 0) {
            axisLeft.mAxisMaximum = 1000.0f;
        } else {
            axisLeft.mAxisMaximum = (maximumYValue * 150) / 100.0f;
        }
        axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
        o().lineChartHitPerc.getAxisRight().setEnabled(false);
        XAxis xAxis = o().lineChartHitPerc.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(R.color.color_757575));
        o().lineChartHitPerc.getAxisLeft().setStartAtZero(true);
        o().lineChartHitPerc.getAxisRight().setStartAtZero(true);
        o().lineChartHitPerc.setVisibleXRangeMaximum(24.0f);
        o().lineChartHitPerc.setVisibleXRangeMinimum(7.0f);
        o().lineChartHitPerc.getAxisLeft().setTextColor(getResources().getColor(R.color.color_757575));
        o().lineChartHitPerc.getXAxis().setTextColor(getResources().getColor(R.color.color_757575));
        o().lineChartHitPerc.getLegend().setTextColor(getResources().getColor(R.color.color_757575));
        o().lineChartHitPerc.getLegend().setEnabled(false);
        o().lineChartHitPerc.invalidate();
    }

    public final void setPositionFromActivity(int i) {
        if (this.m == null && isAdded()) {
            return;
        }
        showProgress(true);
        OverallStatsViewModel overallStatsViewModel = null;
        if (i == 0) {
            OverallStatsViewModel overallStatsViewModel2 = this.m;
            if (overallStatsViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                overallStatsViewModel = overallStatsViewModel2;
            }
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer = overallStatsViewModel.getSessionsListFromServer("LAST_WEEK", "CRICKET_BATTING");
            if (sessionsListFromServer != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                final a aVar = new a();
                sessionsListFromServer.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.p
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBatting.s(Function1.this, obj);
                    }
                });
            }
            TextView textView = o().sensAiDetails.tvSelectedTypeValue;
            textView.setText(PayUtils.INSTANCE.getCalculatedDate("dd MMM, yyyy", -7) + " - " + AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMM, yyyy"));
        } else if (i != 1) {
            OverallStatsViewModel overallStatsViewModel3 = this.m;
            if (overallStatsViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                overallStatsViewModel = overallStatsViewModel3;
            }
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer2 = overallStatsViewModel.getSessionsListFromServer("LAST_HALF_YEAR", "CRICKET_BATTING");
            if (sessionsListFromServer2 != null) {
                LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
                final c cVar = new c();
                sessionsListFromServer2.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.o
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBatting.u(Function1.this, obj);
                    }
                });
            }
            TextView textView2 = o().sensAiDetails.tvSelectedTypeValue;
            textView2.setText(PayUtils.INSTANCE.getCalculatedDate("dd MMM, yyyy", -180) + " - " + AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMM, yyyy"));
        } else {
            OverallStatsViewModel overallStatsViewModel4 = this.m;
            if (overallStatsViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                overallStatsViewModel = overallStatsViewModel4;
            }
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer3 = overallStatsViewModel.getSessionsListFromServer("LAST_MONTH", "CRICKET_BATTING");
            if (sessionsListFromServer3 != null) {
                LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
                final b bVar = new b();
                sessionsListFromServer3.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.n
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBatting.t(Function1.this, obj);
                    }
                });
            }
            TextView textView3 = o().sensAiDetails.tvSelectedTypeValue;
            textView3.setText(PayUtils.INSTANCE.getCalculatedDate("dd MMM, yyyy", -30) + " - " + AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMM, yyyy"));
        }
        q();
    }

    public final void v(int i, int i2, int i3) {
        Resources resources;
        Resources resources2;
        if (o() == null || o().pieChart == null) {
            return;
        }
        o().pieChart.clear();
        PieEntry pieEntry = new PieEntry(i3, requireContext().getString(R.string.hit), String.valueOf(i));
        PieEntry pieEntry2 = new PieEntry(100 - i3, requireContext().getString(R.string.missed), String.valueOf(i2));
        ArrayList arrayList = new ArrayList();
        arrayList.add(pieEntry);
        arrayList.add(pieEntry2);
        PieDataSet pieDataSet = new PieDataSet(arrayList, "");
        if (pieDataSet.getEntryCount() > 0) {
            PieChart pieChart = o().pieChart;
            Intrinsics.checkNotNullExpressionValue(pieChart, "binding.pieChart");
            visible(pieChart);
            RecyclerView recyclerView = o().gridView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.gridView");
            visible(recyclerView);
            pieDataSet.setDrawIcons(false);
            pieDataSet.setSliceSpace(3.0f);
            pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
            pieDataSet.setSelectionShift(10.0f);
            ArrayList arrayList2 = new ArrayList();
            Context context = getContext();
            Integer num = null;
            Integer valueOf = (context == null || (resources2 = context.getResources()) == null) ? null : Integer.valueOf(resources2.getColor(R.color.color_7dcb88));
            Intrinsics.checkNotNull(valueOf);
            arrayList2.add(valueOf);
            Context context2 = getContext();
            if (context2 != null && (resources = context2.getResources()) != null) {
                num = Integer.valueOf(resources.getColor(R.color.color_fa874e));
            }
            Intrinsics.checkNotNull(num);
            arrayList2.add(num);
            pieDataSet.setColors(arrayList2);
            pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
            pieDataSet.setValueLinePart1OffsetPercentage(10.0f);
            pieDataSet.setValueLinePart1Length(0.3f);
            pieDataSet.setValueLinePart2Length(0.1f);
            pieDataSet.setValueLineColor(getResources().getColor(R.color.main_text_color));
            pieDataSet.setValueTextColor(getResources().getColor(R.color.main_text_color));
            PieData pieData = new PieData(pieDataSet);
            pieData.setDrawValues(false);
            pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("###,###,##")));
            pieData.setValueTextSize(10.0f);
            o().pieChart.setData(pieData);
            o().pieChart.setRotationEnabled(false);
            o().pieChart.setHighlightPerTapEnabled(true);
            o().pieChart.setTransparentCircleColor(0);
            o().pieChart.setHoleRadius(70.0f);
            o().pieChart.setHoleColor(0);
            o().pieChart.setTransparentCircleRadius(0.0f);
            o().pieChart.getLegend().setWordWrapEnabled(true);
            o().pieChart.setDrawEntryLabels(false);
            o().pieChart.setDrawHoleEnabled(true);
            o().pieChart.setUsePercentValues(false);
            o().pieChart.setEntryLabelTextSize(5.0f);
            o().pieChart.setEntryLabelColor(getResources().getColor(R.color.main_text_color));
            o().pieChart.setCenterTextSize(24.0f);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String string = getString(R.string.hit_percentage_value);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.hit_percentage_value)");
            String format = String.format(locale, string, Arrays.copyOf(new Object[]{i3 + " % "}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            SpannableString spannableString = new SpannableString(format);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(requireContext().getColor(R.color.white));
            int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) format, i3 + " % ", 0, false, 6, (Object) null);
            int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) format, i3 + " % ", 0, false, 6, (Object) null);
            spannableString.setSpan(foregroundColorSpan, indexOf$default, indexOf$default2 + (i3 + " % ").length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
            o().pieChart.setCenterText(spannableStringBuilder);
            o().pieChart.setCenterTextColor(requireContext().getColor(R.color.secondary_text_color));
            CustomMarkerViewPieChart customMarkerViewPieChart = new CustomMarkerViewPieChart(getContext(), R.layout.custom_marker_view_pie_chart);
            customMarkerViewPieChart.setChartView(o().pieChart);
            o().pieChart.setMarker(customMarkerViewPieChart);
            o().pieChart.getDescription().setEnabled(false);
            o().pieChart.getLegend().setEnabled(false);
            setCustomLegend();
            o().pieChart.invalidate();
            return;
        }
        PieChart pieChart2 = o().pieChart;
        Intrinsics.checkNotNullExpressionValue(pieChart2, "binding.pieChart");
        gone(pieChart2);
        RecyclerView recyclerView2 = o().gridView;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.gridView");
        gone(recyclerView2);
    }

    public final void w(OverallStatsData overallStatsData) {
        SummaryShareData summaryShareData = new SummaryShareData();
        summaryShareData.setDuration(overallStatsData.getTotalDuration());
        summaryShareData.setActivityType(1);
        summaryShareData.setShareType(SummaryShareData.OVERALL_STATS);
        summaryShareData.setTotalSessions(overallStatsData.getTotalSessions());
        summaryShareData.setTotalTargetAchieved(overallStatsData.getTotalTargetAchieved());
        summaryShareData.setDate(o().sensAiDetails.tvSelectedTypeValue.getText().toString());
        summaryShareData.setTotalShots(overallStatsData.getTotalSwings());
        summaryShareData.setAvgHandSpeed(kotlin.math.c.roundToInt(overallStatsData.getAvgHandSpeed()));
        summaryShareData.setMaxHandSpeed(kotlin.math.c.roundToInt(overallStatsData.getMaxHandSpeed()));
        summaryShareData.setHitPercentage(overallStatsData.getPercentile());
        summaryShareData.setPlayed(overallStatsData.getTotalHits());
        Intent intent = new Intent(requireContext(), ActivitySensAIShareScreen.class);
        intent.putExtra("share_data", summaryShareData);
        requireActivity().startActivity(intent);
    }
}
