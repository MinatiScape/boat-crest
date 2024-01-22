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
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentOverallStatsBowlingBinding;
import com.coveiot.android.leonardo.sensai.activity.ActivitySensAIShareScreen;
import com.coveiot.android.leonardo.sensai.model.SummaryShareData;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsData;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
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
public final class FragmentOverallStatsBowling extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public OverallStatsViewModel m;
    @Nullable
    public FragmentOverallStatsBowlingBinding n;
    @Nullable
    public GetOverallStatsResponse o;
    public int p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public OverallStatsData q = new OverallStatsData();

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentOverallStatsBowling newInstance(int i) {
            FragmentOverallStatsBowling fragmentOverallStatsBowling = new FragmentOverallStatsBowling();
            Bundle bundle = new Bundle();
            bundle.putInt("param1", i);
            fragmentOverallStatsBowling.setArguments(bundle);
            return fragmentOverallStatsBowling;
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
            FragmentOverallStatsBowling.this.o = getOverallStatsResponse;
            FragmentOverallStatsBowling.this.q();
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
            FragmentOverallStatsBowling.this.o = getOverallStatsResponse;
            FragmentOverallStatsBowling.this.q();
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
            FragmentOverallStatsBowling.this.o = getOverallStatsResponse;
            FragmentOverallStatsBowling.this.q();
        }
    }

    public static final void p(FragmentOverallStatsBowling this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.v(this$0.q);
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

    public final FragmentOverallStatsBowlingBinding o() {
        FragmentOverallStatsBowlingBinding fragmentOverallStatsBowlingBinding = this.n;
        Intrinsics.checkNotNull(fragmentOverallStatsBowlingBinding);
        return fragmentOverallStatsBowlingBinding;
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
        this.n = FragmentOverallStatsBowlingBinding.inflate(inflater, viewGroup, false);
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
        o().sensAiDetails.ibShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOverallStatsBowling.p(FragmentOverallStatsBowling.this, view2);
            }
        });
    }

    public final void q() {
        String sb;
        Resources resources;
        Resources resources2;
        o().sensAiDetails.clOverallStatsDetails.setVisibility(0);
        o().sensAiDetails.ivCenterVitalBg.setImageDrawable(requireContext().getDrawable(R.drawable.sens_ai_right_hand_bowling));
        o().sensAiDetails.tvTitle.setText(requireContext().getString(R.string.bowling));
        GetOverallStatsResponse getOverallStatsResponse = this.o;
        if (getOverallStatsResponse != null) {
            Intrinsics.checkNotNull(getOverallStatsResponse);
            if (getOverallStatsResponse.getOverallStatsData() != null) {
                GetOverallStatsResponse getOverallStatsResponse2 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse2);
                OverallStatsData overallStatsData = getOverallStatsResponse2.getOverallStatsData();
                Intrinsics.checkNotNullExpressionValue(overallStatsData, "overallStatsResponse!!.overallStatsData");
                this.q = overallStatsData;
                ConstraintLayout constraintLayout = o().clHitAnalysis;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clHitAnalysis");
                visible(constraintLayout);
                LineChart lineChart = o().lineChartArmSpeed;
                Intrinsics.checkNotNullExpressionValue(lineChart, "binding.lineChartArmSpeed");
                visible(lineChart);
                TextView textView = o().tvNoDataFoundHandSpeed;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFoundHandSpeed");
                gone(textView);
                ImageButton imageButton = o().sensAiDetails.ibShare;
                Intrinsics.checkNotNullExpressionValue(imageButton, "binding.sensAiDetails.ibShare");
                visible(imageButton);
                TextView textView2 = o().sensAiDetails.tvTotalSesionsValue;
                GetOverallStatsResponse getOverallStatsResponse3 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse3);
                textView2.setText(String.valueOf(getOverallStatsResponse3.getOverallStatsData().getTotalSessions()));
                TextView textView3 = o().sensAiDetails.tvGoalAcheivedValue;
                GetOverallStatsResponse getOverallStatsResponse4 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse4);
                textView3.setText(String.valueOf(getOverallStatsResponse4.getOverallStatsData().getTotalTargetAchieved()));
                TextView textView4 = o().sensAiDetails.tvDurationValue;
                StringBuilder sb2 = new StringBuilder();
                PayUtils payUtils = PayUtils.INSTANCE;
                GetOverallStatsResponse getOverallStatsResponse5 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse5);
                sb2.append(payUtils.formatSecondsInHHMMSS(getOverallStatsResponse5.getOverallStatsData().getTotalDuration()));
                sb2.append("  ");
                sb2.append(requireContext().getString(R.string.hrs));
                textView4.setText(sb2.toString());
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
                    TextView textView5 = o().tvAvgValue;
                    StringBuilder sb5 = new StringBuilder();
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse6 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse6);
                    sb5.append(payUtils.getSpeedValue(requireContext, kotlin.math.c.roundToInt(getOverallStatsResponse6.getOverallStatsData().getAvgHandSpeed())));
                    sb5.append(' ');
                    sb5.append(sb);
                    sb5.append(' ');
                    textView5.setText(sb5.toString());
                    TextView textView6 = o().tvMaxValue;
                    StringBuilder sb6 = new StringBuilder();
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse7 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse7);
                    sb6.append(payUtils.getSpeedValue(requireContext2, kotlin.math.c.roundToInt(getOverallStatsResponse7.getOverallStatsData().getMaxHandSpeed())));
                    sb6.append(' ');
                    sb6.append(sb);
                    sb6.append(' ');
                    textView6.setText(sb6.toString());
                } else {
                    TextView textView7 = o().tvAvgValue;
                    StringBuilder sb7 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse8 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse8);
                    sb7.append(kotlin.math.c.roundToInt(getOverallStatsResponse8.getOverallStatsData().getAvgHandSpeed()));
                    sb7.append(' ');
                    sb7.append(sb);
                    textView7.setText(sb7.toString());
                    TextView textView8 = o().tvMaxValue;
                    StringBuilder sb8 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse9 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse9);
                    sb8.append(kotlin.math.c.roundToInt(getOverallStatsResponse9.getOverallStatsData().getMaxHandSpeed()));
                    sb8.append(' ');
                    sb8.append(sb);
                    textView8.setText(sb8.toString());
                }
                TextView textView9 = o().tvTotalBalls;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Locale locale = Locale.ENGLISH;
                String string = getString(R.string.total_balls_bowled_value);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.total_balls_bowled_value)");
                GetOverallStatsResponse getOverallStatsResponse10 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse10);
                String format = String.format(locale, string, Arrays.copyOf(new Object[]{Integer.valueOf(getOverallStatsResponse10.getOverallStatsData().getTotalBallsBowled())}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                textView9.setText(format);
                GetOverallStatsResponse getOverallStatsResponse11 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse11);
                if (getOverallStatsResponse11.getOverallStatsData().getPercentile() == 0) {
                    o().clHitAnalysisPerc.setVisibility(8);
                } else {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    String string2 = getString(R.string.avg_arm_perc);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.avg_arm_perc)");
                    StringBuilder sb9 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse12 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse12);
                    sb9.append(getOverallStatsResponse12.getOverallStatsData().getPercentile());
                    sb9.append(" % ");
                    String format2 = String.format(locale, string2, Arrays.copyOf(new Object[]{sb9.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    SpannableString spannableString = new SpannableString(format2);
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(requireContext().getColor(R.color.gradient_green_end_color));
                    StringBuilder sb10 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse13 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse13);
                    sb10.append(getOverallStatsResponse13.getOverallStatsData().getPercentile());
                    sb10.append(" % ");
                    int indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) format2, sb10.toString(), 0, false, 6, (Object) null);
                    StringBuilder sb11 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse14 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse14);
                    sb11.append(getOverallStatsResponse14.getOverallStatsData().getPercentile());
                    sb11.append(" % ");
                    int indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) format2, sb11.toString(), 0, false, 6, (Object) null);
                    StringBuilder sb12 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse15 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse15);
                    sb12.append(getOverallStatsResponse15.getOverallStatsData().getPercentile());
                    sb12.append(" % ");
                    spannableString.setSpan(foregroundColorSpan, indexOf$default, indexOf$default2 + sb12.toString().length(), 33);
                    spannableStringBuilder.append((CharSequence) spannableString);
                    o().tvAvgHit.setText(spannableStringBuilder);
                }
                GetOverallStatsResponse getOverallStatsResponse16 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse16);
                List<OverallStatsHandSpeed> handSpeedList = getOverallStatsResponse16.getOverallStatsData().getHandSpeedList();
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
        ImageButton imageButton2 = o().sensAiDetails.ibShare;
        Intrinsics.checkNotNullExpressionValue(imageButton2, "binding.sensAiDetails.ibShare");
        gone(imageButton2);
        ConstraintLayout constraintLayout2 = o().clHitAnalysis;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clHitAnalysis");
        gone(constraintLayout2);
        LineChart lineChart2 = o().lineChartArmSpeed;
        Intrinsics.checkNotNullExpressionValue(lineChart2, "binding.lineChartArmSpeed");
        gone(lineChart2);
        TextView textView10 = o().tvNoDataFoundHandSpeed;
        Intrinsics.checkNotNullExpressionValue(textView10, "binding.tvNoDataFoundHandSpeed");
        visible(textView10);
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
            o().lineChartArmSpeed.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentOverallStatsBowling$setHandSpeedLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        Context context = FragmentOverallStatsBowling.this.getContext();
                        Toast.makeText(context, "Arm Speed: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            LineChart lineChart19 = o().lineChartArmSpeed;
            if (lineChart19 != null) {
                lineChart19.invalidate();
            }
        }
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer = overallStatsViewModel.getSessionsListFromServer("LAST_WEEK", "CRICKET_BOWLING");
            if (sessionsListFromServer != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                final a aVar = new a();
                sessionsListFromServer.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.r
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBowling.s(Function1.this, obj);
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer2 = overallStatsViewModel.getSessionsListFromServer("LAST_HALF_YEAR", "CRICKET_BOWLING");
            if (sessionsListFromServer2 != null) {
                LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
                final c cVar = new c();
                sessionsListFromServer2.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.s
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBowling.u(Function1.this, obj);
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer3 = overallStatsViewModel.getSessionsListFromServer("LAST_MONTH", "CRICKET_BOWLING");
            if (sessionsListFromServer3 != null) {
                LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
                final b bVar = new b();
                sessionsListFromServer3.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.t
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentOverallStatsBowling.t(Function1.this, obj);
                    }
                });
            }
            TextView textView3 = o().sensAiDetails.tvSelectedTypeValue;
            textView3.setText(PayUtils.INSTANCE.getCalculatedDate("dd MMM, yyyy", -30) + " - " + AppUtils.formatDate(Calendar.getInstance().getTime(), "dd MMM, yyyy"));
        }
        q();
    }

    public final void v(OverallStatsData overallStatsData) {
        SummaryShareData summaryShareData = new SummaryShareData();
        summaryShareData.setDuration(overallStatsData.getTotalDuration());
        summaryShareData.setActivityType(2);
        summaryShareData.setShareType(SummaryShareData.OVERALL_STATS);
        summaryShareData.setTotalSessions(overallStatsData.getTotalSessions());
        summaryShareData.setTotalTargetAchieved(overallStatsData.getTotalTargetAchieved());
        summaryShareData.setDate(o().sensAiDetails.tvSelectedTypeValue.getText().toString());
        summaryShareData.setTotalBallsBowled(overallStatsData.getTotalBallsBowled());
        summaryShareData.setAvgArmSpeed(kotlin.math.c.roundToInt(overallStatsData.getAvgHandSpeed()));
        summaryShareData.setMaxArmSpeed(kotlin.math.c.roundToInt(overallStatsData.getMaxHandSpeed()));
        Intent intent = new Intent(requireContext(), ActivitySensAIShareScreen.class);
        intent.putExtra("share_data", summaryShareData);
        requireActivity().startActivity(intent);
    }
}
