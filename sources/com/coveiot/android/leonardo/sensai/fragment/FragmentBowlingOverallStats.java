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
import com.coveiot.android.boat.databinding.FragmentBowlingOverallStatsBinding;
import com.coveiot.android.leonardo.sensai.viewmodel.OverallStatsViewModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.GetOverallStatsResponse;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsData;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed;
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
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
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
public final class FragmentBowlingOverallStats extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public OverallStatsViewModel m;
    @Nullable
    public FragmentBowlingOverallStatsBinding n;
    @Nullable
    public GetOverallStatsResponse o;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentBowlingOverallStats newInstance(int i) {
            FragmentBowlingOverallStats fragmentBowlingOverallStats = new FragmentBowlingOverallStats();
            Bundle bundle = new Bundle();
            bundle.putInt("param1", i);
            fragmentBowlingOverallStats.setArguments(bundle);
            return fragmentBowlingOverallStats;
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
            FragmentBowlingOverallStats.this.o = getOverallStatsResponse;
            FragmentBowlingOverallStats.this.q();
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
            FragmentBowlingOverallStats.this.o = getOverallStatsResponse;
            FragmentBowlingOverallStats.this.q();
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
            FragmentBowlingOverallStats.this.o = getOverallStatsResponse;
            FragmentBowlingOverallStats.this.q();
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function1<GetOverallStatsResponse, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(GetOverallStatsResponse getOverallStatsResponse) {
            invoke2(getOverallStatsResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(GetOverallStatsResponse getOverallStatsResponse) {
            FragmentBowlingOverallStats.this.o = getOverallStatsResponse;
            FragmentBowlingOverallStats.this.q();
        }
    }

    public static final void p(Function1 tmp0, Object obj) {
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

    public final FragmentBowlingOverallStatsBinding o() {
        FragmentBowlingOverallStatsBinding fragmentBowlingOverallStatsBinding = this.n;
        Intrinsics.checkNotNull(fragmentBowlingOverallStatsBinding);
        return fragmentBowlingOverallStatsBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getInt("param1");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentBowlingOverallStatsBinding.inflate(inflater, viewGroup, false);
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
        OverallStatsViewModel overallStatsViewModel = (OverallStatsViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(OverallStatsViewModel.class);
        this.m = overallStatsViewModel;
        if (overallStatsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            overallStatsViewModel = null;
        }
        MutableLiveData<GetOverallStatsResponse> sessionsListFromServer = overallStatsViewModel.getSessionsListFromServer("LAST_HALF_YEAR", "CRICKET_BOWLING");
        if (sessionsListFromServer != null) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            final a aVar = new a();
            sessionsListFromServer.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.f
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentBowlingOverallStats.p(Function1.this, obj);
                }
            });
        }
    }

    public final void q() {
        String sb;
        Resources resources;
        Resources resources2;
        GetOverallStatsResponse getOverallStatsResponse = this.o;
        if (getOverallStatsResponse != null) {
            Intrinsics.checkNotNull(getOverallStatsResponse);
            if (getOverallStatsResponse.getOverallStatsData() != null) {
                o().cardViewHandSpeedGraph.setVisibility(0);
                TextView textView = o().tvSessions;
                GetOverallStatsResponse getOverallStatsResponse2 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse2);
                textView.setText(String.valueOf(getOverallStatsResponse2.getOverallStatsData().getTotalSessions()));
                TextView textView2 = o().tvGoals;
                GetOverallStatsResponse getOverallStatsResponse3 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse3);
                textView2.setText(String.valueOf(getOverallStatsResponse3.getOverallStatsData().getTotalTargetAchieved()));
                Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                if (isDistanceUnitInMile.booleanValue()) {
                    StringBuilder sb2 = new StringBuilder();
                    Context context = getContext();
                    sb2.append((context == null || (resources2 = context.getResources()) == null) ? null : resources2.getString(R.string.mil_per_hours));
                    sb2.append(' ');
                    sb = sb2.toString();
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    Context context2 = getContext();
                    sb3.append((context2 == null || (resources = context2.getResources()) == null) ? null : resources.getString(R.string.km_per_hours));
                    sb3.append(' ');
                    sb = sb3.toString();
                }
                Boolean isDistanceUnitInMile2 = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
                Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile2, "getInstance(requireContext()).isDistanceUnitInMile");
                if (isDistanceUnitInMile2.booleanValue()) {
                    TextView textView3 = o().tvAvgArmSpeed;
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
                    TextView textView4 = o().tvMaxArmSpeed;
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
                    TextView textView5 = o().tvAvgArmSpeed;
                    StringBuilder sb6 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse6 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse6);
                    sb6.append(kotlin.math.c.roundToInt(getOverallStatsResponse6.getOverallStatsData().getAvgHandSpeed()));
                    sb6.append(' ');
                    sb6.append(sb);
                    textView5.setText(sb6.toString());
                    TextView textView6 = o().tvMaxArmSpeed;
                    StringBuilder sb7 = new StringBuilder();
                    GetOverallStatsResponse getOverallStatsResponse7 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse7);
                    sb7.append(kotlin.math.c.roundToInt(getOverallStatsResponse7.getOverallStatsData().getMaxHandSpeed()));
                    sb7.append(' ');
                    sb7.append(sb);
                    textView6.setText(sb7.toString());
                }
                TextView textView7 = o().tvTotalBalls;
                GetOverallStatsResponse getOverallStatsResponse8 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse8);
                OverallStatsData overallStatsData = getOverallStatsResponse8.getOverallStatsData();
                textView7.setText(String.valueOf(overallStatsData != null ? Integer.valueOf(overallStatsData.getTotalBallsBowled()) : null));
                GetOverallStatsResponse getOverallStatsResponse9 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse9);
                if (getOverallStatsResponse9.getOverallStatsData().getPercentile() == 0) {
                    o().tvAcheivedPerc.setVisibility(8);
                } else {
                    TextView textView8 = o().tvAcheivedPerc;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Locale locale = Locale.ENGLISH;
                    String string = getString(R.string.overall_stats_compare_users);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.overall_stats_compare_users)");
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("");
                    GetOverallStatsResponse getOverallStatsResponse10 = this.o;
                    Intrinsics.checkNotNull(getOverallStatsResponse10);
                    sb8.append(getOverallStatsResponse10.getOverallStatsData().getPercentile());
                    sb8.append(" % ");
                    String format = String.format(locale, string, Arrays.copyOf(new Object[]{sb8.toString()}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                    textView8.setText(format);
                }
                TextView textView9 = o().tvDuration;
                PayUtils payUtils2 = PayUtils.INSTANCE;
                GetOverallStatsResponse getOverallStatsResponse11 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse11);
                textView9.setText(payUtils2.formatSeconds(getOverallStatsResponse11.getOverallStatsData().getTotalDuration()));
                GetOverallStatsResponse getOverallStatsResponse12 = this.o;
                Intrinsics.checkNotNull(getOverallStatsResponse12);
                List<OverallStatsHandSpeed> handSpeedList = getOverallStatsResponse12.getOverallStatsData().getHandSpeedList();
                Intrinsics.checkNotNull(handSpeedList, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.OverallStatsHandSpeed>");
                r((ArrayList) handSpeedList);
                dismissProgress();
            }
        }
        o().tvSessions.setText("--");
        o().tvGoals.setText("--");
        o().tvTotalBalls.setText("--");
        o().tvDuration.setText("--");
        o().tvAvgArmSpeed.setText("--");
        o().tvMaxArmSpeed.setText("--");
        o().tvAcheivedPerc.setVisibility(8);
        o().cardViewHandSpeedGraph.setVisibility(8);
        dismissProgress();
    }

    public final void r(ArrayList<OverallStatsHandSpeed> arrayList) {
        ArrayList<Entry> arrayList2 = new ArrayList();
        new ArrayList();
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
            o().lineChartArmSpeed.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.fragment.FragmentBowlingOverallStats$setHandSpeedLineGraphValues$1
                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onNothingSelected() {
                }

                @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
                public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (((int) e.getY()) > 0) {
                        Context context = FragmentBowlingOverallStats.this.getContext();
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer = overallStatsViewModel.getSessionsListFromServer("LAST_HALF_YEAR", "CRICKET_BOWLING");
            if (sessionsListFromServer != null) {
                LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
                final b bVar = new b();
                sessionsListFromServer.observe(viewLifecycleOwner, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.g
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBowlingOverallStats.s(Function1.this, obj);
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer2 = overallStatsViewModel.getSessionsListFromServer("LAST_WEEK", "CRICKET_BOWLING");
            if (sessionsListFromServer2 != null) {
                LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
                final d dVar = new d();
                sessionsListFromServer2.observe(viewLifecycleOwner2, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.e
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBowlingOverallStats.u(Function1.this, obj);
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
            MutableLiveData<GetOverallStatsResponse> sessionsListFromServer3 = overallStatsViewModel.getSessionsListFromServer("LAST_MONTH", "CRICKET_BOWLING");
            if (sessionsListFromServer3 != null) {
                LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
                final c cVar = new c();
                sessionsListFromServer3.observe(viewLifecycleOwner3, new Observer() { // from class: com.coveiot.android.leonardo.sensai.fragment.d
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        FragmentBowlingOverallStats.t(Function1.this, obj);
                    }
                });
            }
        }
        dismissProgress();
    }
}
