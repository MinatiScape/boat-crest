package com.coveiot.android.leonardo.sensai.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentBattingOverallStatsBinding;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHitPercentage;
import com.coveiot.covepreferences.UserDataManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentBattingOverallStats extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public OverallStatsViewModel m;
    @Nullable
    public FragmentBattingOverallStatsBinding n;
    @Nullable
    public GetOverallStatsResponse o;
    public int p;

    /* loaded from: classes5.dex */
    public final class BattingChartValueFormatter implements IValueFormatter {
        public BattingChartValueFormatter(FragmentBattingOverallStats fragmentBattingOverallStats) {
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
        public final FragmentBattingOverallStats newInstance(int i) {
            FragmentBattingOverallStats fragmentBattingOverallStats = new FragmentBattingOverallStats();
            Bundle bundle = new Bundle();
            bundle.putInt("param1", i);
            fragmentBattingOverallStats.setArguments(bundle);
            return fragmentBattingOverallStats;
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
            FragmentBattingOverallStats.this.o = getOverallStatsResponse;
            FragmentBattingOverallStats.this.o();
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
            FragmentBattingOverallStats.this.o = getOverallStatsResponse;
            FragmentBattingOverallStats.this.o();
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
            FragmentBattingOverallStats.this.o = getOverallStatsResponse;
            FragmentBattingOverallStats.this.o();
        }
    }

    public static final void r(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void t(Function1 tmp0, Object obj) {
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

    public final FragmentBattingOverallStatsBinding n() {
        FragmentBattingOverallStatsBinding fragmentBattingOverallStatsBinding = this.n;
        Intrinsics.checkNotNull(fragmentBattingOverallStatsBinding);
        return fragmentBattingOverallStatsBinding;
    }

    public final void o() {
        String sb;
        Resources resources;
        Resources resources2;
        GetOverallStatsResponse getOverallStatsResponse = this.o;
        if (getOverallStatsResponse != null) {
            Intrinsics.checkNotNull(getOverallStatsResponse);
            if (getOverallStatsResponse.getOverallStatsData() != null) {
                n().cvTotalSwings.setVisibility(0);
                n().cardViewHandSpeedGraph.setVisibility(0);
                TextView textView = n().tvSessions;
                GetOverallStatsResponse getOverallStatsResponse2 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse2);
                textView.setText(String.valueOf(getOverallStatsResponse2.getOverallStatsData().getTotalSessions()));
                TextView textView2 = n().tvGoals;
                GetOverallStatsResponse getOverallStatsResponse3 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse3);
                textView2.setText(String.valueOf(getOverallStatsResponse3.getOverallStatsData().getTotalTargetAchieved()));
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                String str = null;
                if (isDistanceUnitInMile.booleanValue()) {
                    StringBuilder sb2 = new StringBuilder();
                    Context context = getContext();
                    if (context != null && (resources2 = context.getResources()) != null) {
                        str = resources2.getString(R.string.mil_per_hours);
                    }
                    sb2.append(str);
                    sb2.append(' ');
                    sb = sb2.toString();
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    Context context2 = getContext();
                    if (context2 != null && (resources = context2.getResources()) != null) {
                        str = resources.getString(R.string.km_per_hours);
                    }
                    sb3.append(str);
                    sb3.append(' ');
                    sb = sb3.toString();
                }
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(requireContext()).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    TextView textView3 = n().tvAvgHandSpeed;
                    StringBuilder sb4 = new StringBuilder();
                    PayUtils payUtils = PayUtils.INSTANCE;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse4 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse4);
                    sb4.append(payUtils.getSpeedValue(requireContext, kotlin.math.c.roundToInt(getOverallStatsResponse4.getOverallStatsData().getAvgHandSpeed())));
                    sb4.append(' ');
                    sb4.append(sb);
                    sb4.append(' ');
                    textView3.setText(sb4.toString());
                    TextView textView4 = n().tvMaxHandSpeed;
                    StringBuilder sb5 = new StringBuilder();
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    GetOverallStatsResponse getOverallStatsResponse5 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse5);
                    sb5.append(payUtils.getSpeedValue(requireContext2, kotlin.math.c.roundToInt(getOverallStatsResponse5.getOverallStatsData().getMaxHandSpeed())));
                    sb5.append(' ');
                    sb5.append(sb);
                    sb5.append(' ');
                    textView4.setText(sb5.toString());
                } else {
                    TextView textView5 = n().tvAvgHandSpeed;
                    StringBuilder sb6 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse6 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse6);
                    sb6.append(kotlin.math.c.roundToInt(getOverallStatsResponse6.getOverallStatsData().getAvgHandSpeed()));
                    sb6.append(' ');
                    sb6.append(sb);
                    textView5.setText(sb6.toString());
                    TextView textView6 = n().tvMaxHandSpeed;
                    StringBuilder sb7 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse7 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse7);
                    sb7.append(kotlin.math.c.roundToInt(getOverallStatsResponse7.getOverallStatsData().getMaxHandSpeed()));
                    sb7.append(' ');
                    sb7.append(sb);
                    textView6.setText(sb7.toString());
                }
                TextView textView7 = n().totalSwingsCount;
                GetOverallStatsResponse getOverallStatsResponse8 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse8);
                textView7.setText(String.valueOf(getOverallStatsResponse8.getOverallStatsData().getTotalSwings()));
                TextView textView8 = n().tvDurationValue;
                PayUtils payUtils2 = PayUtils.INSTANCE;
                GetOverallStatsResponse getOverallStatsResponse9 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse9);
                textView8.setText(payUtils2.formatSeconds(getOverallStatsResponse9.getOverallStatsData().getTotalDuration()));
                GetOverallStatsResponse getOverallStatsResponse10 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse10);
                if (getOverallStatsResponse10.getOverallStatsData().getPercentile() == 0) {
                    n().tvAcheivedPerc.setVisibility(8);
                } else {
                    TextView textView9 = n().tvAcheivedPerc;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string = getString(R.string.overall_stats_compare_users);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.overall_stats_compare_users)");
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("");
                    GetOverallStatsResponse getOverallStatsResponse11 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse11);
                    sb8.append(getOverallStatsResponse11.getOverallStatsData().getPercentile());
                    sb8.append(" % ");
                    String format = String.format(locale, string, Arrays.copyOf(new Object[]{sb8.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    textView9.setText(format);
                }
                double d = 0.0d;
                GetOverallStatsResponse getOverallStatsResponse12 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse12);
                for (OverallStatsHitPercentage overallStatsHitPercentage : getOverallStatsResponse12.getOverallStatsData().getHitPercentageList()) {
                    d += overallStatsHitPercentage.getHitPercentage();
                }
                GetOverallStatsResponse getOverallStatsResponse13 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse13);
                if (getOverallStatsResponse13.getOverallStatsData().getHitPercentageList().size() >= 1) {
                    GetOverallStatsResponse getOverallStatsResponse14 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse14);
                    d /= getOverallStatsResponse14.getOverallStatsData().getHitPercentageList().size();
                }
                n().totalHitCount.setText(String.valueOf(kotlin.math.c.roundToInt(d)));
                u(kotlin.math.c.roundToInt(d), 100 - kotlin.math.c.roundToInt(d));
                GetOverallStatsResponse getOverallStatsResponse15 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse15);
                List<OverallStatsHitPercentage> hitPercentageList = getOverallStatsResponse15.getOverallStatsData().getHitPercentageList();
                Intrinsics.checkNotNull(hitPercentageList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHitPercentage>");
                q((ArrayList) hitPercentageList);
                GetOverallStatsResponse getOverallStatsResponse16 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse16);
                List<OverallStatsHandSpeed> handSpeedList = getOverallStatsResponse16.getOverallStatsData().getHandSpeedList();
                Intrinsics.checkNotNull(handSpeedList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed>");
                p((ArrayList) handSpeedList);
                dismissProgress();
            }
        }
        n().tvSessions.setText("--");
        n().tvGoals.setText("--");
        n().tvAvgHandSpeed.setText("--");
        n().tvMaxHandSpeed.setText("--");
        n().totalSwingsCount.setText("--");
        n().totalHitCount.setText("--");
        n().tvDurationValue.setText("--");
        n().tvHitPerc.setVisibility(8);
        n().tvAcheivedPerc.setVisibility(8);
        n().lineChartHitPerc.setVisibility(8);
        n().cardViewHandSpeedGraph.setVisibility(8);
        dismissProgress();
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
        this.n = FragmentBattingOverallStatsBinding.inflate(inflater, viewGroup, false);
        return n().getRoot();
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
    }

    public final void p(ArrayList<OverallStatsHandSpeed> arrayList) {
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
        if (n().lineChartArmSpeed != null) {
            LineChart lineChart = n().lineChartArmSpeed;
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
            lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
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
            LineChart lineChart2 = n().lineChartArmSpeed;
            YAxis axisLeft = lineChart2 != null ? lineChart2.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(1.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(100.0f);
            axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
            LineChart lineChart3 = n().lineChartArmSpeed;
            YAxis axisRight = lineChart3 != null ? lineChart3.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            LineChart lineChart4 = n().lineChartArmSpeed;
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
            LineChart lineChart5 = n().lineChartArmSpeed;
            Legend legend = lineChart5 != null ? lineChart5.getLegend() : null;
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            LineChart lineChart6 = n().lineChartArmSpeed;
            if (lineChart6 != null) {
                lineChart6.setData(lineData);
            }
            LineChart lineChart7 = n().lineChartArmSpeed;
            if (lineChart7 != null) {
                lineChart7.setDrawGridBackground(false);
            }
            LineChart lineChart8 = n().lineChartArmSpeed;
            Description description = lineChart8 != null ? lineChart8.getDescription() : null;
            if (description != null) {
                description.setEnabled(false);
            }
            LineChart lineChart9 = n().lineChartArmSpeed;
            if (lineChart9 != null) {
                lineChart9.setNoDataText("");
            }
            LineChart lineChart10 = n().lineChartArmSpeed;
            if (lineChart10 != null) {
                lineChart10.setDrawBorders(false);
            }
            LineChart lineChart11 = n().lineChartArmSpeed;
            if (lineChart11 != null) {
                lineChart11.setAutoScaleMinMaxEnabled(true);
            }
            LineChart lineChart12 = n().lineChartArmSpeed;
            if (lineChart12 != null) {
                lineChart12.setPinchZoom(false);
            }
            LineChart lineChart13 = n().lineChartArmSpeed;
            if (lineChart13 != null) {
                lineChart13.setScaleEnabled(false);
            }
            LineChart lineChart14 = n().lineChartArmSpeed;
            if (lineChart14 != null) {
                lineChart14.setVisibleXRangeMinimum(5.0f);
            }
            LineChart lineChart15 = n().lineChartArmSpeed;
            YAxis axisLeft2 = lineChart15 != null ? lineChart15.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            LineChart lineChart16 = n().lineChartArmSpeed;
            YAxis axisRight2 = lineChart16 != null ? lineChart16.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            LineChart lineChart17 = n().lineChartArmSpeed;
            YAxis axisLeft3 = lineChart17 != null ? lineChart17.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            LineChart lineChart18 = n().lineChartArmSpeed;
            XAxis xAxis2 = lineChart18 != null ? lineChart18.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            n().lineChartArmSpeed.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentBattingOverallStats$setHandSpeedLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        Context context = FragmentBattingOverallStats.this.getContext();
                        Toast.makeText(context, "Hand Speed: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            LineChart lineChart19 = n().lineChartArmSpeed;
            if (lineChart19 != null) {
                lineChart19.invalidate();
            }
        }
    }

    public final void q(ArrayList<OverallStatsHitPercentage> arrayList) {
        ArrayList<Entry> arrayList2 = new ArrayList();
        Iterator<OverallStatsHitPercentage> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            arrayList2.add(new Entry(i, it.next().getHitPercentage()));
        }
        if (n().lineChartHitPerc != null) {
            LineChart lineChart = n().lineChartHitPerc;
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
            lineDataSet.setCircleColor(getResources().getColor(R.color.colorPrimary));
            lineDataSet.setCircleSize(0.5f);
            lineDataSet.setDrawValues(false);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
            lineDataSet.setColor(getResources().getColor(R.color.colorPrimary));
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
            LineChart lineChart2 = n().lineChartHitPerc;
            YAxis axisLeft = lineChart2 != null ? lineChart2.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft);
            axisLeft.setDrawAxisLine(true);
            axisLeft.setDrawGridLines(false);
            axisLeft.setAxisMinimum(1.0f);
            axisLeft.setEnabled(true);
            axisLeft.setAxisMaximum(100.0f);
            axisLeft.setAxisLineColor(getResources().getColor(R.color.color_757575));
            LineChart lineChart3 = n().lineChartHitPerc;
            YAxis axisRight = lineChart3 != null ? lineChart3.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight);
            axisRight.setEnabled(false);
            LineChart lineChart4 = n().lineChartHitPerc;
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
            LineChart lineChart5 = n().lineChartHitPerc;
            Legend legend = lineChart5 != null ? lineChart5.getLegend() : null;
            Intrinsics.checkNotNull(legend);
            legend.setEnabled(false);
            LineChart lineChart6 = n().lineChartHitPerc;
            if (lineChart6 != null) {
                lineChart6.setData(lineData);
            }
            LineChart lineChart7 = n().lineChartHitPerc;
            if (lineChart7 != null) {
                lineChart7.setDrawGridBackground(false);
            }
            LineChart lineChart8 = n().lineChartHitPerc;
            Description description = lineChart8 != null ? lineChart8.getDescription() : null;
            if (description != null) {
                description.setEnabled(false);
            }
            LineChart lineChart9 = n().lineChartHitPerc;
            if (lineChart9 != null) {
                lineChart9.setNoDataText("");
            }
            LineChart lineChart10 = n().lineChartHitPerc;
            if (lineChart10 != null) {
                lineChart10.setDrawBorders(false);
            }
            LineChart lineChart11 = n().lineChartHitPerc;
            if (lineChart11 != null) {
                lineChart11.setAutoScaleMinMaxEnabled(true);
            }
            LineChart lineChart12 = n().lineChartHitPerc;
            if (lineChart12 != null) {
                lineChart12.setPinchZoom(false);
            }
            LineChart lineChart13 = n().lineChartHitPerc;
            if (lineChart13 != null) {
                lineChart13.setVisibleXRangeMinimum(5.0f);
            }
            LineChart lineChart14 = n().lineChartHitPerc;
            if (lineChart14 != null) {
                lineChart14.setScaleEnabled(false);
            }
            LineChart lineChart15 = n().lineChartHitPerc;
            YAxis axisLeft2 = lineChart15 != null ? lineChart15.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft2);
            axisLeft2.setStartAtZero(true);
            LineChart lineChart16 = n().lineChartHitPerc;
            YAxis axisRight2 = lineChart16 != null ? lineChart16.getAxisRight() : null;
            Intrinsics.checkNotNull(axisRight2);
            axisRight2.setStartAtZero(true);
            LineChart lineChart17 = n().lineChartHitPerc;
            YAxis axisLeft3 = lineChart17 != null ? lineChart17.getAxisLeft() : null;
            Intrinsics.checkNotNull(axisLeft3);
            axisLeft3.setTextColor(getResources().getColor(R.color.secondary_text_color));
            LineChart lineChart18 = n().lineChartHitPerc;
            XAxis xAxis2 = lineChart18 != null ? lineChart18.getXAxis() : null;
            Intrinsics.checkNotNull(xAxis2);
            xAxis2.setTextColor(getResources().getColor(R.color.secondary_text_color));
            n().lineChartHitPerc.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentBattingOverallStats$setLineGrapValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        Context context = FragmentBattingOverallStats.this.getContext();
                        Toast.makeText(context, "Hit Perc: " + ((int) e.getY()), 0).show();
                    }
                }
            });
            LineChart lineChart19 = n().lineChartHitPerc;
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer = overallStatsViewModel.getSessionsListFromServer("LAST_WEEK", "CRICKET_BATTING");
            if (sessionsListFromServer != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                final a aVar = new a();
                sessionsListFromServer.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.b
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBattingOverallStats.r(Function1.this, obj);
                    }
                });
            }
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
                sessionsListFromServer2.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.c
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBattingOverallStats.t(Function1.this, obj);
                    }
                });
            }
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
                sessionsListFromServer3.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.a
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBattingOverallStats.s(Function1.this, obj);
                    }
                });
            }
        }
        dismissProgress();
    }

    public final void u(int i, int i2) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        n().pieChart.setUsePercentValues(true);
        n().pieChart.getDescription().setEnabled(false);
        n().pieChart.setNoDataText("");
        n().pieChart.setExtraOffsets(5.0f, 10.0f, 5.0f, 5.0f);
        n().pieChart.setDragDecelerationFrictionCoef(0.95f);
        n().pieChart.setDrawHoleEnabled(false);
        n().pieChart.setRotationEnabled(false);
        n().pieChart.setHighlightPerTapEnabled(false);
        n().pieChart.setDrawEntryLabels(false);
        n().pieChart.getLegend().setEnabled(false);
        PieEntry pieEntry = new PieEntry(i, "hit");
        PieEntry pieEntry2 = new PieEntry(i2, "missed");
        ArrayList arrayList = new ArrayList();
        arrayList.add(pieEntry);
        arrayList.add(pieEntry2);
        PieDataSet pieDataSet = new PieDataSet(arrayList, "Cricket");
        pieDataSet.setDrawIcons(false);
        pieDataSet.setSliceSpace(0.0f);
        pieDataSet.setIconsOffset(new MPPointF(0.0f, 40.0f));
        pieDataSet.setSelectionShift(5.0f);
        ArrayList arrayList2 = new ArrayList();
        Context context = getContext();
        Integer valueOf = (context == null || (resources3 = context.getResources()) == null) ? null : Integer.valueOf(resources3.getColor(R.color.color_3c70ff));
        Intrinsics.checkNotNull(valueOf);
        arrayList2.add(valueOf);
        Context context2 = getContext();
        Integer valueOf2 = (context2 == null || (resources2 = context2.getResources()) == null) ? null : Integer.valueOf(resources2.getColor(R.color.color_e76867));
        Intrinsics.checkNotNull(valueOf2);
        arrayList2.add(valueOf2);
        pieDataSet.setColors(arrayList2);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(new DecimalFormat("####,###,###")));
        Context context3 = getContext();
        Integer valueOf3 = (context3 == null || (resources = context3.getResources()) == null) ? null : Integer.valueOf(resources.getColor(R.color.main_text_color));
        Intrinsics.checkNotNull(valueOf3);
        pieData.setValueTextColor(valueOf3.intValue());
        pieDataSet.setDrawValues(true);
        pieDataSet.setValueFormatter(new BattingChartValueFormatter(this));
        n().pieChart.setData(pieData);
        n().pieChart.highlightValues(null);
        n().pieChart.invalidate();
    }
}
