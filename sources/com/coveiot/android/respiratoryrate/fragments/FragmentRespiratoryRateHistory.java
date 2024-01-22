package com.coveiot.android.respiratoryrate.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateHistory;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateShare;
import com.coveiot.android.respiratoryrate.adapter.RespiratoryRateHistoryAdapter;
import com.coveiot.android.respiratoryrate.customview.CustomMarkerViewRespiratoryRate;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateHistoryBinding;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateHistory;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateBean;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.Utils;
import com.coveiot.android.respiratoryrate.utils.ViewModelFactory;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateHistoryViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentRespiratoryRateHistory extends BaseFragment implements View.OnClickListener, ContractRespiratoryRateHistory {
    @Nullable
    public FragmentRespiratoryRateHistoryBinding m;
    public ActivityRespiratoryRateHistory mActivity;
    public int n;
    public final int o;
    @Nullable
    public RespiratoryRateBean r;
    public RespiratoryRateHistoryViewModel viewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int p = 1;
    public final int q = 2;

    @DebugMetadata(c = "com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRateHistory$onLineChartDataLoaded$1", f = "FragmentRespiratoryRateHistory.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ArrayList<Entry> $entries;
        public final /* synthetic */ ArrayList<String> $labels;
        public final /* synthetic */ String $type;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, ArrayList<Entry> arrayList, ArrayList<String> arrayList2, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$type = str;
            this.$entries = arrayList;
            this.$labels = arrayList2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$type, this.$entries, this.$labels, continuation);
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
                if (FragmentRespiratoryRateHistory.this.m != null) {
                    if (Intrinsics.areEqual(this.$type, Constants.DAY.getValue())) {
                        FragmentRespiratoryRateHistory.this.k().clMinMaxView.setVisibility(0);
                        FragmentRespiratoryRateHistory.this.k().clRvView.setVisibility(8);
                    } else {
                        FragmentRespiratoryRateHistory.this.k().clMinMaxView.setVisibility(8);
                        FragmentRespiratoryRateHistory.this.k().clRvView.setVisibility(0);
                    }
                    ArrayList<Entry> arrayList = this.$entries;
                    if (arrayList == null || arrayList.isEmpty()) {
                        FragmentRespiratoryRateHistory.this.k().linechartRespiratoryRate.setVisibility(4);
                        FragmentRespiratoryRateHistory.this.k().dateTv.setVisibility(8);
                        FragmentRespiratoryRateHistory.this.k().noDataTv.setVisibility(0);
                        TextView textView = FragmentRespiratoryRateHistory.this.k().tvRespiratoryRateMin;
                        Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
                        textView.setText(constants.getValue());
                        FragmentRespiratoryRateHistory.this.k().tvRespiratoryRateMax.setText(constants.getValue());
                    } else {
                        ArrayList<Entry> arrayList2 = this.$entries;
                        Intrinsics.checkNotNull(arrayList2);
                        if (arrayList2.get(this.$entries.size() - 1).getData() instanceof RespiratoryRateBean) {
                            FragmentRespiratoryRateHistory fragmentRespiratoryRateHistory = FragmentRespiratoryRateHistory.this;
                            ArrayList<Entry> arrayList3 = this.$entries;
                            Object data = arrayList3.get(arrayList3.size() - 1).getData();
                            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.respiratoryrate.model.RespiratoryRateBean");
                            fragmentRespiratoryRateHistory.setRespiratoryRateBean((RespiratoryRateBean) data);
                            FragmentRespiratoryRateHistory fragmentRespiratoryRateHistory2 = FragmentRespiratoryRateHistory.this;
                            ArrayList<Entry> arrayList4 = this.$entries;
                            Object data2 = arrayList4.get(arrayList4.size() - 1).getData();
                            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type com.coveiot.android.respiratoryrate.model.RespiratoryRateBean");
                            fragmentRespiratoryRateHistory2.m((RespiratoryRateBean) data2);
                        }
                        FragmentRespiratoryRateHistory fragmentRespiratoryRateHistory3 = FragmentRespiratoryRateHistory.this;
                        ArrayList<Entry> arrayList5 = this.$entries;
                        ArrayList<String> arrayList6 = this.$labels;
                        Intrinsics.checkNotNull(arrayList6);
                        fragmentRespiratoryRateHistory3.l(arrayList5, arrayList6);
                        FragmentRespiratoryRateHistory.this.k().linechartRespiratoryRate.setVisibility(0);
                        FragmentRespiratoryRateHistory.this.k().noDataTv.setVisibility(8);
                        FragmentRespiratoryRateHistory.this.k().dateTv.setVisibility(0);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
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

    @NotNull
    public final ActivityRespiratoryRateHistory getMActivity() {
        ActivityRespiratoryRateHistory activityRespiratoryRateHistory = this.mActivity;
        if (activityRespiratoryRateHistory != null) {
            return activityRespiratoryRateHistory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mActivity");
        return null;
    }

    @Nullable
    public final RespiratoryRateBean getRespiratoryRateBean() {
        return this.r;
    }

    @NotNull
    public final RespiratoryRateHistoryViewModel getViewModel() {
        RespiratoryRateHistoryViewModel respiratoryRateHistoryViewModel = this.viewModel;
        if (respiratoryRateHistoryViewModel != null) {
            return respiratoryRateHistoryViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final FragmentRespiratoryRateHistoryBinding k() {
        FragmentRespiratoryRateHistoryBinding fragmentRespiratoryRateHistoryBinding = this.m;
        Intrinsics.checkNotNull(fragmentRespiratoryRateHistoryBinding);
        return fragmentRespiratoryRateHistoryBinding;
    }

    public final void l(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry next = it.next();
            if (next.getY() == 0.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        Resources resources = getResources();
        int i = R.color.respiratory_rate_graph_color;
        lineDataSet.setCircleColor(resources.getColor(i));
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(i));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i2 = 0; i2 < entryCount; i2++) {
                iArr[i2] = getResources().getColor(R.color.respiratory_rate_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.respiratory_rate_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
        }
        LineData lineData = new LineData(lineDataSet);
        k().linechartRespiratoryRate.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        k().linechartRespiratoryRate.setData(lineData);
        k().linechartRespiratoryRate.setDrawGridBackground(false);
        k().linechartRespiratoryRate.getDescription().setEnabled(false);
        Resources resources2 = getResources();
        int i3 = R.color.secondary_text_color;
        lineData.setValueTextColor(resources2.getColor(i3));
        k().linechartRespiratoryRate.getPaint(7).setColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.setDrawBorders(false);
        k().linechartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        YAxis axisLeft = k().linechartRespiratoryRate.getAxisLeft();
        axisLeft.setEnabled(true);
        axisLeft.setDrawAxisLine(true);
        axisLeft.setDrawGridLines(false);
        axisLeft.setAxisMaximum(40.0f);
        axisLeft.setAxisLineColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.getAxisRight().setEnabled(false);
        XAxis xAxis = k().linechartRespiratoryRate.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.getAxisLeft().setStartAtZero(true);
        k().linechartRespiratoryRate.getAxisRight().setStartAtZero(true);
        k().linechartRespiratoryRate.getAxisLeft().setTextColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.getXAxis().setTextColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.getLegend().setTextColor(getResources().getColor(i3));
        k().linechartRespiratoryRate.getLegend().setEnabled(false);
        k().linechartRespiratoryRate.animateY(2000);
        k().linechartRespiratoryRate.setVisibleXRangeMinimum(5.0f);
        k().linechartRespiratoryRate.setMarker(null);
        k().linechartRespiratoryRate.setPinchZoom(false);
        k().linechartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        k().linechartRespiratoryRate.setScaleEnabled(false);
        k().linechartRespiratoryRate.setDoubleTapToZoomEnabled(false);
        k().linechartRespiratoryRate.moveViewToX(arrayList.size() - 1);
        k().linechartRespiratoryRate.invalidate();
        k().linechartRespiratoryRate.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // from class: com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRateHistory$setLineGraphValues$1
            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onNothingSelected() {
            }

            @Override // com.github.mikephil.charting.listener.OnChartValueSelectedListener
            public void onValueSelected(@NotNull Entry e, @NotNull Highlight h) {
                Intrinsics.checkNotNullParameter(e, "e");
                Intrinsics.checkNotNullParameter(h, "h");
                CustomMarkerViewRespiratoryRate customMarkerViewRespiratoryRate = new CustomMarkerViewRespiratoryRate(FragmentRespiratoryRateHistory.this.getContext(), R.layout.custom_marker_view);
                customMarkerViewRespiratoryRate.setChartView(FragmentRespiratoryRateHistory.this.k().linechartRespiratoryRate);
                FragmentRespiratoryRateHistory.this.k().linechartRespiratoryRate.setMarker(customMarkerViewRespiratoryRate);
                FragmentRespiratoryRateHistory fragmentRespiratoryRateHistory = FragmentRespiratoryRateHistory.this;
                Object data = e.getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type com.coveiot.android.respiratoryrate.model.RespiratoryRateBean");
                fragmentRespiratoryRateHistory.setRespiratoryRateBean((RespiratoryRateBean) data);
                FragmentRespiratoryRateHistory fragmentRespiratoryRateHistory2 = FragmentRespiratoryRateHistory.this;
                RespiratoryRateBean respiratoryRateBean = fragmentRespiratoryRateHistory2.getRespiratoryRateBean();
                Intrinsics.checkNotNull(respiratoryRateBean);
                fragmentRespiratoryRateHistory2.m(respiratoryRateBean);
            }
        });
    }

    public final void m(RespiratoryRateBean respiratoryRateBean) {
        this.r = respiratoryRateBean;
        TextView textView = k().dateTv;
        FragmentActivity activity = getActivity();
        String type = respiratoryRateBean.getType();
        Intrinsics.checkNotNull(type);
        String startDate = respiratoryRateBean.getStartDate();
        Intrinsics.checkNotNull(startDate);
        String week = respiratoryRateBean.getWeek();
        String month = respiratoryRateBean.getMonth();
        String year = respiratoryRateBean.getYear();
        textView.setText(Utils.getTodayYesterdayDate(activity, type, startDate, week, month, year != null ? Integer.valueOf(Integer.parseInt(year)) : null));
        if (Intrinsics.areEqual(respiratoryRateBean.getType(), Constants.DAY.getValue())) {
            if (respiratoryRateBean.getMin() != null) {
                Integer min = respiratoryRateBean.getMin();
                Intrinsics.checkNotNull(min);
                if (min.intValue() > 0 && respiratoryRateBean.getMax() != null) {
                    Integer max = respiratoryRateBean.getMax();
                    Intrinsics.checkNotNull(max);
                    if (max.intValue() > 0) {
                        TextView textView2 = k().tvRespiratoryRateMax;
                        StringBuilder sb = new StringBuilder();
                        sb.append(getString(R.string.max));
                        sb.append(": ");
                        sb.append(respiratoryRateBean.getMax());
                        sb.append(' ');
                        int i = R.string.brpm;
                        sb.append(getString(i));
                        textView2.setText(sb.toString());
                        TextView textView3 = k().tvRespiratoryRateMin;
                        textView3.setText(getString(R.string.min) + ": " + respiratoryRateBean.getMin() + ' ' + getString(i));
                        return;
                    }
                }
            }
            TextView textView4 = k().tvRespiratoryRateMax;
            Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
            textView4.setText(String.valueOf(constants.getValue()));
            k().tvRespiratoryRateMin.setText(String.valueOf(constants.getValue()));
            return;
        }
        List<RespiratoryRateListBean> dailyRespiratoryRateBeanItemList = respiratoryRateBean.getDailyRespiratoryRateBeanItemList();
        if (dailyRespiratoryRateBeanItemList != null) {
            k().rvMinMax.setLayoutManager(new LinearLayoutManager(requireActivity()));
            RecyclerView recyclerView = k().rvMinMax;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            recyclerView.setAdapter(new RespiratoryRateHistoryAdapter(requireActivity, dailyRespiratoryRateBeanItemList));
        }
    }

    public final void n(TextView textView) {
        if (isAdded()) {
            TextView textView2 = k().dailyTv;
            Resources resources = getResources();
            int i = R.color.secondary_text_color;
            textView2.setTextColor(resources.getColor(i));
            k().weeklyTv.setTextColor(getResources().getColor(i));
            k().monthlyTv.setTextColor(getResources().getColor(i));
            k().weeklyTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            k().monthlyTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            k().dailyTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            textView.setTextColor(getResources().getColor(R.color.color_E51C23));
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_rectangle_tab_selector_bg);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof ActivityRespiratoryRateHistory) {
            setMActivity((ActivityRespiratoryRateHistory) context);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        int id = view.getId();
        if (id == R.id.daily_tv) {
            this.n = this.o;
            TextView textView = k().dailyTv;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.dailyTv");
            n(textView);
            getViewModel().selectDayView();
        } else if (id == R.id.weekly_tv) {
            this.n = this.p;
            TextView textView2 = k().weeklyTv;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.weeklyTv");
            n(textView2);
            getViewModel().selectWeekView();
        } else if (id == R.id.monthly_tv) {
            this.n = this.q;
            TextView textView3 = k().monthlyTv;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.monthlyTv");
            n(textView3);
            getViewModel().selectMonthView();
        } else if (id == R.id.back) {
            if (this.mActivity == null || getMActivity().isFinishing()) {
                return;
            }
            getMActivity().finish();
        } else if (id == R.id.share_iv) {
            share();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentRespiratoryRateHistoryBinding.inflate(inflater, viewGroup, false);
        return k().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.m = null;
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateHistory
    public void onLineChartDataLoaded(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, @NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(type, arrayList, arrayList2, null), 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(RespiratoryRateHistoryViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…oryViewModel::class.java)");
        setViewModel((RespiratoryRateHistoryViewModel) viewModel);
        getViewModel().setContractRespiratoryRateHistory$respiratoryrate_prodRelease(this);
        getViewModel().setMLifecycleOwner(this);
        this.n = this.o;
        TextView textView = k().dailyTv;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.dailyTv");
        n(textView);
        k().dailyTv.setOnClickListener(this);
        k().weeklyTv.setOnClickListener(this);
        k().monthlyTv.setOnClickListener(this);
        k().back.setOnClickListener(this);
        k().shareIv.setOnClickListener(this);
        getViewModel().selectDayView();
    }

    public final void setMActivity(@NotNull ActivityRespiratoryRateHistory activityRespiratoryRateHistory) {
        Intrinsics.checkNotNullParameter(activityRespiratoryRateHistory, "<set-?>");
        this.mActivity = activityRespiratoryRateHistory;
    }

    public final void setRespiratoryRateBean(@Nullable RespiratoryRateBean respiratoryRateBean) {
        this.r = respiratoryRateBean;
    }

    public final void setViewModel(@NotNull RespiratoryRateHistoryViewModel respiratoryRateHistoryViewModel) {
        Intrinsics.checkNotNullParameter(respiratoryRateHistoryViewModel, "<set-?>");
        this.viewModel = respiratoryRateHistoryViewModel;
    }

    public final void share() {
        String obj;
        if (this.r != null) {
            RespiratoryRateShareData respiratoryRateShareData = new RespiratoryRateShareData();
            RespiratoryRateBean respiratoryRateBean = this.r;
            r2 = null;
            List list = null;
            respiratoryRateShareData.setMin(respiratoryRateBean != null ? respiratoryRateBean.getMin() : null);
            RespiratoryRateBean respiratoryRateBean2 = this.r;
            respiratoryRateShareData.setMax(respiratoryRateBean2 != null ? respiratoryRateBean2.getMax() : null);
            int i = this.n;
            if (i == this.o) {
                RespiratoryRateBean respiratoryRateBean3 = this.r;
                Intrinsics.checkNotNull(respiratoryRateBean3);
                String dayMonthFormatDate1 = Utils.getDayMonthFormatDate1(respiratoryRateBean3.getStartDate());
                respiratoryRateShareData.setTitle(SessionManager.getInstance(getContext()).getUserDetails().getName() + "'s " + requireActivity().getString(R.string.nightly_breathing_rate_small) + ' ' + requireActivity().getString(R.string.fr) + ' ' + dayMonthFormatDate1);
            } else if (i == this.p) {
                CharSequence text = k().dateTv.getText();
                if (text != null && (obj = text.toString()) != null) {
                    list = StringsKt__StringsKt.split$default((CharSequence) obj, new String[]{"\n"}, false, 0, 6, (Object) null);
                }
                String obj2 = !(list == null || list.isEmpty()) ? StringsKt__StringsKt.trim((String) list.get(list.size() - 1)).toString() : "";
                respiratoryRateShareData.setTitle(SessionManager.getInstance(getContext()).getUserDetails().getName() + "'s " + requireActivity().getString(R.string.nightly_breathing_rate_small) + ' ' + requireActivity().getString(R.string.fr) + ' ' + obj2);
            } else if (i == this.q) {
                StringBuilder sb = new StringBuilder();
                sb.append(SessionManager.getInstance(getContext()).getUserDetails().getName());
                sb.append("'s ");
                sb.append(requireActivity().getString(R.string.nightly_breathing_rate_small));
                sb.append(' ');
                sb.append(requireActivity().getString(R.string.fr));
                sb.append(' ');
                CharSequence text2 = k().dateTv.getText();
                sb.append(text2 != null ? text2.toString() : null);
                respiratoryRateShareData.setTitle(sb.toString());
            }
            Intent intent = new Intent(getContext(), ActivityRespiratoryRateShare.class);
            intent.putExtra(Constants.SHARE_DATA.getValue(), respiratoryRateShareData);
            requireActivity().startActivity(intent);
        }
    }
}
