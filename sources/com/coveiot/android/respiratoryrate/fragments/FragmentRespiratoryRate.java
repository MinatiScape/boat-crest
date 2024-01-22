package com.coveiot.android.respiratoryrate.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateHistory;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateInfo;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateSettings;
import com.coveiot.android.respiratoryrate.activities.ActivityRespiratoryRateShare;
import com.coveiot.android.respiratoryrate.customview.CustomMarkerViewRespiratoryRate;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.databinding.FragmentRespiratoryRateBinding;
import com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.Utils;
import com.coveiot.android.respiratoryrate.utils.ViewModelFactory;
import com.coveiot.android.respiratoryrate.viewmodel.RespiratoryRateViewModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FragmentRespiratoryRate extends BaseFragment implements View.OnClickListener, ContractRespiratoryRateDashBoard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public DatePickerDialog datePickerDialog;
    public View[] dateSelectionView;
    @Nullable
    public FragmentRespiratoryRateBinding m;
    @Nullable
    public Calendar n;
    public boolean o;
    public boolean p;
    @Nullable
    public Calendar q;
    public RespiratoryRateViewModel r;
    public SimpleDateFormat simpleDateFormat;
    @Nullable
    public DailyRespiratoryRateEntity t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int s = 1;

    /* loaded from: classes6.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ FragmentRespiratoryRate newInstance$default(Companion companion, Calendar calendar, int i, Object obj) {
            if ((i & 1) != 0) {
                calendar = null;
            }
            return companion.newInstance(calendar);
        }

        @NotNull
        public final FragmentRespiratoryRate newInstance(@Nullable Calendar calendar) {
            FragmentRespiratoryRate fragmentRespiratoryRate = new FragmentRespiratoryRate();
            if (calendar != null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calender", calendar);
                fragmentRespiratoryRate.setArguments(bundle);
            }
            return fragmentRespiratoryRate;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRate$updateDailyLevelData$1", f = "FragmentRespiratoryRate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ DailyRespiratoryRateEntity $dailyRespiratoryRateEntity;
        private /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(DailyRespiratoryRateEntity dailyRespiratoryRateEntity, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$dailyRespiratoryRateEntity = dailyRespiratoryRateEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            a aVar = new a(this.$dailyRespiratoryRateEntity, continuation);
            aVar.L$0 = obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Unit unit;
            int i;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                if (FragmentRespiratoryRate.this.m != null) {
                    FragmentRespiratoryRate.this.t = this.$dailyRespiratoryRateEntity;
                    TextView textView = FragmentRespiratoryRate.this.m().tvRespiratoryRateMin;
                    Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
                    textView.setText(String.valueOf(constants.getValue()));
                    FragmentRespiratoryRate.this.m().tvRespiratoryRateMax.setText(String.valueOf(constants.getValue()));
                    FragmentRespiratoryRate.this.m().tvDay.setVisibility(8);
                    DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.$dailyRespiratoryRateEntity;
                    if (dailyRespiratoryRateEntity != null) {
                        FragmentRespiratoryRate fragmentRespiratoryRate = FragmentRespiratoryRate.this;
                        RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
                        if (respiratoryRateData != null) {
                            if (respiratoryRateData.getMin() != null) {
                                Integer min = respiratoryRateData.getMin();
                                Intrinsics.checkNotNull(min);
                                if (min.intValue() > 0 && respiratoryRateData.getMax() != null) {
                                    Integer max = respiratoryRateData.getMax();
                                    Intrinsics.checkNotNull(max);
                                    if (max.intValue() > 0) {
                                        TextView textView2 = fragmentRespiratoryRate.m().tvRespiratoryRateMin;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(fragmentRespiratoryRate.requireContext().getString(R.string.min));
                                        sb.append(": ");
                                        sb.append(respiratoryRateData.getMin());
                                        sb.append(' ');
                                        sb.append(fragmentRespiratoryRate.requireContext().getString(R.string.brpm));
                                        textView2.setText(sb.toString());
                                        fragmentRespiratoryRate.m().tvRespiratoryRateMax.setText(fragmentRespiratoryRate.requireContext().getString(R.string.max) + ": " + respiratoryRateData.getMax() + ' ' + fragmentRespiratoryRate.requireContext().getString(i));
                                    }
                                }
                            }
                            fragmentRespiratoryRate.m().tvRespiratoryRateMin.setText(String.valueOf(constants.getValue()));
                            fragmentRespiratoryRate.m().tvRespiratoryRateMax.setText(String.valueOf(constants.getValue()));
                        }
                        TextView textView3 = fragmentRespiratoryRate.m().tvDay;
                        FragmentActivity activity = fragmentRespiratoryRate.getActivity();
                        textView3.setText(Utils.getTodayYesterdayDate$default(activity != null ? activity.getBaseContext() : null, Constants.DAY.getValue(), dailyRespiratoryRateEntity.getMDate(), null, null, null, 56, null));
                        fragmentRespiratoryRate.m().tvDay.setVisibility(0);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit == null) {
                        FragmentRespiratoryRate fragmentRespiratoryRate2 = FragmentRespiratoryRate.this;
                        if (fragmentRespiratoryRate2.n != null) {
                            TextView textView4 = fragmentRespiratoryRate2.m().tvDay;
                            FragmentActivity activity2 = fragmentRespiratoryRate2.getActivity();
                            Context baseContext = activity2 != null ? activity2.getBaseContext() : null;
                            String value = Constants.DAY.getValue();
                            Calendar calendar = fragmentRespiratoryRate2.n;
                            Intrinsics.checkNotNull(calendar);
                            String formatDate = AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd");
                            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(selectedDate!!.time, \"yyyy-MM-dd\")");
                            textView4.setText(Utils.getTodayYesterdayDate$default(baseContext, value, formatDate, null, null, null, 56, null));
                            fragmentRespiratoryRate2.m().tvDay.setVisibility(0);
                        }
                    }
                    FragmentRespiratoryRate.this.u();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void o(FragmentRespiratoryRate this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.requireActivity(), ActivityRespiratoryRateInfo.class));
    }

    public static final void q(FragmentRespiratoryRate this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.s(newDate);
        TextView textView = this$0.m().tvDate1;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDate1");
        this$0.t(textView);
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
    public final DatePickerDialog getDatePickerDialog() {
        DatePickerDialog datePickerDialog = this.datePickerDialog;
        if (datePickerDialog != null) {
            return datePickerDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("datePickerDialog");
        return null;
    }

    @NotNull
    public final View[] getDateSelectionView() {
        View[] viewArr = this.dateSelectionView;
        if (viewArr != null) {
            return viewArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dateSelectionView");
        return null;
    }

    public final int getPosition1() {
        return this.s;
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

    public final FragmentRespiratoryRateBinding m() {
        FragmentRespiratoryRateBinding fragmentRespiratoryRateBinding = this.m;
        Intrinsics.checkNotNull(fragmentRespiratoryRateBinding);
        return fragmentRespiratoryRateBinding;
    }

    public final void n() {
        String string = getString(R.string.no_data_available_ensure_that_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_da…ble_ensure_that_settings)");
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(requireContext().getColor(R.color.colorAccent)), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length() - 1, 34);
        spannableString.setSpan(new UnderlineSpan(), StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length() - 1, 34);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRate$loadSettingsNavigationTextView$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                FragmentRespiratoryRate.this.startActivity(new Intent(FragmentRespiratoryRate.this.requireActivity(), ActivityRespiratoryRateSettings.class));
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) string, "Settings", 0, false, 6, (Object) null), string.length() - 1, 34);
        m().noSettingsEnabledTv.setText(spannableString, TextView.BufferType.SPANNABLE);
        m().noSettingsEnabledTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void onCandleChartDataLoaded(@Nullable ArrayList<CandleEntry> arrayList, @Nullable ArrayList<String> arrayList2) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        if (view != null) {
            if (view.getId() == R.id.calendar_image) {
                p();
                getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
                getDatePickerDialog().show();
            } else if (view.getId() == R.id.history_image) {
                startActivity(new Intent(getContext(), ActivityRespiratoryRateHistory.class));
            } else if (view.getId() == R.id.share_image) {
                share();
            } else if (view instanceof TextView) {
                t((TextView) view);
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            if ((arguments != null ? arguments.getSerializable("calender") : null) != null) {
                Bundle arguments2 = getArguments();
                Serializable serializable = arguments2 != null ? arguments2.getSerializable("calender") : null;
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.Calendar");
                this.q = (Calendar) serializable;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentRespiratoryRateBinding.inflate(inflater, viewGroup, false);
        return m().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.m = null;
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Long lastPPGSyncTimestamp = UserDataManager.getInstance(getContext()).getLastPPGSyncTimestamp(BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        Intrinsics.checkNotNullExpressionValue(lastPPGSyncTimestamp, "getInstance(context).get…acAddress()\n            )");
        this.p = DateUtils.isToday(lastPPGSyncTimestamp.longValue());
        if (UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity()) != this.o) {
            this.o = UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity());
            u();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.o = UserDataManager.getInstance(getContext()).isRespiratoryRateFeatureEnabled(requireActivity());
        Long lastPPGSyncTimestamp = UserDataManager.getInstance(getContext()).getLastPPGSyncTimestamp(BleApiManager.getInstance(getContext()).getBleApi().getMacAddress());
        Intrinsics.checkNotNullExpressionValue(lastPPGSyncTimestamp, "getInstance(context)\n   …).bleApi.getMacAddress())");
        this.p = DateUtils.isToday(lastPPGSyncTimestamp.longValue());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(RespiratoryRateViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this, ViewModelFactor…ateViewModel::class.java)");
        RespiratoryRateViewModel respiratoryRateViewModel = (RespiratoryRateViewModel) viewModel;
        this.r = respiratoryRateViewModel;
        RespiratoryRateViewModel respiratoryRateViewModel2 = null;
        if (respiratoryRateViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            respiratoryRateViewModel = null;
        }
        respiratoryRateViewModel.setContractRespiratoryRateDashboard(this);
        RespiratoryRateViewModel respiratoryRateViewModel3 = this.r;
        if (respiratoryRateViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            respiratoryRateViewModel3 = null;
        }
        respiratoryRateViewModel3.setMLifecycleOwner(this);
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        TextView textView = m().tvDate1;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDate1");
        TextView textView2 = m().tvDate2;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvDate2");
        TextView textView3 = m().tvDate3;
        Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvDate3");
        TextView textView4 = m().tvDate4;
        Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvDate4");
        TextView textView5 = m().tvDate5;
        Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvDate5");
        setDateSelectionView(new View[]{textView, textView2, textView3, textView4, textView5});
        int length = getDateSelectionView().length;
        for (int i = 0; i < length; i++) {
            getDateSelectionView()[i].setOnClickListener(this);
        }
        TextView textView6 = m().tvRespiratoryRateMin;
        Constants constants = Constants.EMPTY_RESPIRATORY_RATE;
        textView6.setText(String.valueOf(constants.getValue()));
        m().tvRespiratoryRateMax.setText(String.valueOf(constants.getValue()));
        setDefaultDateUi(this.q);
        m().calendarImage.setOnClickListener(this);
        m().tvDate1.setOnClickListener(this);
        m().tvDate2.setOnClickListener(this);
        m().tvDate3.setOnClickListener(this);
        m().tvDate4.setOnClickListener(this);
        m().tvDate5.setOnClickListener(this);
        m().shareImage.setOnClickListener(this);
        m().historyImage.setOnClickListener(this);
        m().tvRespiratoryRate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentRespiratoryRate.o(FragmentRespiratoryRate.this, view2);
            }
        });
        n();
        if (this.q != null) {
            RespiratoryRateViewModel respiratoryRateViewModel4 = this.r;
            if (respiratoryRateViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                respiratoryRateViewModel2 = respiratoryRateViewModel4;
            }
            Calendar calendar = this.q;
            Intrinsics.checkNotNull(calendar);
            respiratoryRateViewModel2.loadDailyData(calendar);
            return;
        }
        RespiratoryRateViewModel respiratoryRateViewModel5 = this.r;
        if (respiratoryRateViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            respiratoryRateViewModel2 = respiratoryRateViewModel5;
        }
        Calendar calendar2 = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar2, "getInstance()");
        respiratoryRateViewModel2.loadDailyData(calendar2);
    }

    public final void p() {
        Calendar calendar = Calendar.getInstance();
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.respiratoryrate.fragments.a
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                FragmentRespiratoryRate.q(FragmentRespiratoryRate.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }

    public final void r(ArrayList<Entry> arrayList, ArrayList<String> arrayList2) {
        m().linechartRespiratoryRate.clear();
        ArrayList arrayList3 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        Iterator<Entry> it = arrayList.iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.getY() <= 0.0f) {
                arrayList3.add(next);
            }
        }
        arrayList.removeAll(arrayList3);
        LineDataSet lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.secondary_text_color));
        lineDataSet.setColor(getResources().getColor(R.color.respiratory_rate_graph_color));
        lineDataSet.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet.getEntryCount() > 0) {
            int entryCount = lineDataSet.getEntryCount();
            int[] iArr = new int[entryCount];
            for (int i = 0; i < entryCount; i++) {
                iArr[i] = getResources().getColor(R.color.respiratory_rate_graph_color);
            }
            iArr[entryCount - 1] = getResources().getColor(R.color.respiratory_rate_graph_color);
            lineDataSet.setColors(Arrays.copyOf(iArr, entryCount));
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setCircleColors(Arrays.copyOf(iArr, entryCount));
        }
        LineDataSet lineDataSet2 = new LineDataSet(arrayList3, "");
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setDrawCircles(false);
        Resources resources = getResources();
        int i2 = R.color.transparent;
        lineDataSet2.setValueTextColor(resources.getColor(i2));
        lineDataSet2.setColor(getResources().getColor(i2));
        lineDataSet2.setCircleColor(getResources().getColor(i2));
        lineDataSet2.setHighLightColor(getResources().getColor(17170445));
        if (lineDataSet2.getEntryCount() > 0) {
            int entryCount2 = lineDataSet2.getEntryCount();
            int[] iArr2 = new int[entryCount2];
            for (int i3 = 0; i3 < entryCount2; i3++) {
                iArr2[i3] = getResources().getColor(17170445);
            }
            iArr2[entryCount2 - 1] = getResources().getColor(17170445);
            lineDataSet2.setColors(Arrays.copyOf(iArr2, entryCount2));
            lineDataSet2.setCircleColors(Arrays.copyOf(iArr2, entryCount2));
        }
        lineDataSet2.setCircleRadius(0.0f);
        m().linechartRespiratoryRate.getAxisLeft().setEnabled(true);
        m().linechartRespiratoryRate.getAxisLeft().setDrawAxisLine(true);
        m().linechartRespiratoryRate.getAxisLeft().setDrawGridLines(false);
        m().linechartRespiratoryRate.getAxisLeft().setAxisMaximum(40.0f);
        m().linechartRespiratoryRate.getAxisLeft().setAxisLineWidth(1.0f);
        YAxis axisLeft = m().linechartRespiratoryRate.getAxisLeft();
        Resources resources2 = getResources();
        int i4 = R.color.secondary_text_color;
        axisLeft.setAxisLineColor(resources2.getColor(i4));
        LineData lineData = new LineData(lineDataSet, lineDataSet2);
        m().linechartRespiratoryRate.getXAxis().setValueFormatter(new IndexAxisValueFormatter(arrayList2));
        lineData.setDrawValues(false);
        m().linechartRespiratoryRate.setData(lineData);
        m().linechartRespiratoryRate.setDrawGridBackground(false);
        m().linechartRespiratoryRate.getDescription().setEnabled(false);
        lineData.setValueTextColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.getPaint(7).setColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.setDrawBorders(false);
        m().linechartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        m().linechartRespiratoryRate.setPinchZoom(false);
        m().linechartRespiratoryRate.getAxisRight().setEnabled(false);
        XAxis xAxis = m().linechartRespiratoryRate.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineWidth(1.0f);
        xAxis.setAxisLineColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.getAxisLeft().setStartAtZero(true);
        m().linechartRespiratoryRate.getAxisRight().setStartAtZero(true);
        m().linechartRespiratoryRate.getAxisLeft().setTextColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.getXAxis().setTextColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.getLegend().setTextColor(getResources().getColor(i4));
        m().linechartRespiratoryRate.getLegend().setEnabled(false);
        m().linechartRespiratoryRate.animateY(2000);
        m().linechartRespiratoryRate.setVisibleXRangeMinimum(5.0f);
        CustomMarkerViewRespiratoryRate customMarkerViewRespiratoryRate = new CustomMarkerViewRespiratoryRate(getContext(), R.layout.custom_marker_view);
        customMarkerViewRespiratoryRate.setChartView(m().linechartRespiratoryRate);
        m().linechartRespiratoryRate.setMarker(customMarkerViewRespiratoryRate);
        m().linechartRespiratoryRate.setPinchZoom(false);
        m().linechartRespiratoryRate.setAutoScaleMinMaxEnabled(false);
        m().linechartRespiratoryRate.setScaleEnabled(false);
        m().linechartRespiratoryRate.setDoubleTapToZoomEnabled(false);
        m().linechartRespiratoryRate.invalidate();
    }

    public final void s(Calendar calendar) {
        if (isAdded()) {
            TextView textView = m().tvDate1;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            textView.setText(format);
            m().tvDate1.setTag(calendar.clone());
            calendar.add(5, -this.s);
            TextView textView2 = m().tvDate2;
            String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
            textView2.setText(format2);
            m().tvDate2.setTag(calendar.clone());
            calendar.add(5, -this.s);
            TextView textView3 = m().tvDate3;
            String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
            textView3.setText(format3);
            m().tvDate3.setTag(calendar.clone());
            calendar.add(5, -this.s);
            TextView textView4 = m().tvDate4;
            String format4 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format4, "format(locale, format, *args)");
            textView4.setText(format4);
            m().tvDate4.setTag(calendar.clone());
            calendar.add(5, -this.s);
            TextView textView5 = m().tvDate5;
            String format5 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
            Intrinsics.checkNotNullExpressionValue(format5, "format(locale, format, *args)");
            textView5.setText(format5);
            m().tvDate5.setTag(calendar.clone());
        }
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setDateSelectionView(@NotNull View[] viewArr) {
        Intrinsics.checkNotNullParameter(viewArr, "<set-?>");
        this.dateSelectionView = viewArr;
    }

    public final void setDefaultDateUi(@Nullable Calendar calendar) {
        Calendar newDate = Calendar.getInstance();
        if (calendar != null) {
            Object clone = calendar.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            newDate = (Calendar) clone;
        }
        newDate.set(newDate.get(1), newDate.get(2), newDate.get(5));
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        s(newDate);
        TextView textView = m().tvDate1;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDate1");
        t(textView);
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        RespiratoryRateData respiratoryRateData;
        RespiratoryRateData respiratoryRateData2;
        DailyRespiratoryRateEntity dailyRespiratoryRateEntity = this.t;
        Integer num = null;
        if ((dailyRespiratoryRateEntity != null ? dailyRespiratoryRateEntity.data : null) != null) {
            RespiratoryRateData respiratoryRateData3 = dailyRespiratoryRateEntity != null ? dailyRespiratoryRateEntity.data : null;
            Intrinsics.checkNotNull(respiratoryRateData3);
            if (respiratoryRateData3.getMin() != null) {
                DailyRespiratoryRateEntity dailyRespiratoryRateEntity2 = this.t;
                RespiratoryRateData respiratoryRateData4 = dailyRespiratoryRateEntity2 != null ? dailyRespiratoryRateEntity2.data : null;
                Intrinsics.checkNotNull(respiratoryRateData4);
                Integer min = respiratoryRateData4.getMin();
                Intrinsics.checkNotNull(min);
                if (min.intValue() > 0) {
                    DailyRespiratoryRateEntity dailyRespiratoryRateEntity3 = this.t;
                    RespiratoryRateData respiratoryRateData5 = dailyRespiratoryRateEntity3 != null ? dailyRespiratoryRateEntity3.data : null;
                    Intrinsics.checkNotNull(respiratoryRateData5);
                    if (respiratoryRateData5.getMax() != null) {
                        DailyRespiratoryRateEntity dailyRespiratoryRateEntity4 = this.t;
                        RespiratoryRateData respiratoryRateData6 = dailyRespiratoryRateEntity4 != null ? dailyRespiratoryRateEntity4.data : null;
                        Intrinsics.checkNotNull(respiratoryRateData6);
                        Integer max = respiratoryRateData6.getMax();
                        Intrinsics.checkNotNull(max);
                        if (max.intValue() > 0) {
                            RespiratoryRateShareData respiratoryRateShareData = new RespiratoryRateShareData();
                            DailyRespiratoryRateEntity dailyRespiratoryRateEntity5 = this.t;
                            respiratoryRateShareData.setMin((dailyRespiratoryRateEntity5 == null || (respiratoryRateData2 = dailyRespiratoryRateEntity5.data) == null) ? null : respiratoryRateData2.getMin());
                            DailyRespiratoryRateEntity dailyRespiratoryRateEntity6 = this.t;
                            if (dailyRespiratoryRateEntity6 != null && (respiratoryRateData = dailyRespiratoryRateEntity6.data) != null) {
                                num = respiratoryRateData.getMax();
                            }
                            respiratoryRateShareData.setMax(num);
                            DailyRespiratoryRateEntity dailyRespiratoryRateEntity7 = this.t;
                            Intrinsics.checkNotNull(dailyRespiratoryRateEntity7);
                            String dayMonthFormatDate1 = Utils.getDayMonthFormatDate1(dailyRespiratoryRateEntity7.getMDate());
                            respiratoryRateShareData.setTitle(SessionManager.getInstance(getContext()).getUserDetails().getName() + "'s " + requireActivity().getString(R.string.nightly_breathing_rate_small) + ' ' + requireActivity().getString(R.string.fr) + ' ' + dayMonthFormatDate1);
                            Intent intent = new Intent(getContext(), ActivityRespiratoryRateShare.class);
                            intent.putExtra(Constants.SHARE_DATA.getValue(), respiratoryRateShareData);
                            requireActivity().startActivity(intent);
                        }
                    }
                }
            }
        }
    }

    public final void t(TextView textView) {
        if (isAdded()) {
            TextView textView2 = m().tvDate1;
            Resources resources = getResources();
            int i = R.color.secondary_text_color;
            textView2.setTextColor(resources.getColor(i));
            m().tvDate2.setTextColor(getResources().getColor(i));
            m().tvDate3.setTextColor(getResources().getColor(i));
            m().tvDate4.setTextColor(getResources().getColor(i));
            m().tvDate5.setTextColor(getResources().getColor(i));
            TextView textView3 = m().tvDate1;
            int i2 = R.drawable.un_selected_date_health_tab;
            textView3.setBackgroundResource(i2);
            m().tvDate2.setBackgroundResource(i2);
            m().tvDate3.setBackgroundResource(i2);
            m().tvDate4.setBackgroundResource(i2);
            m().tvDate5.setBackgroundResource(i2);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackgroundResource(R.drawable.circle_with_primary_color_solid_temp_steps);
            RespiratoryRateViewModel respiratoryRateViewModel = null;
            if (textView.getTag() == null) {
                RespiratoryRateViewModel respiratoryRateViewModel2 = this.r;
                if (respiratoryRateViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    respiratoryRateViewModel = respiratoryRateViewModel2;
                }
                Calendar calendar = Calendar.getInstance();
                Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                respiratoryRateViewModel.loadDailyData(calendar);
                this.n = Calendar.getInstance();
                return;
            }
            RespiratoryRateViewModel respiratoryRateViewModel3 = this.r;
            if (respiratoryRateViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                respiratoryRateViewModel = respiratoryRateViewModel3;
            }
            Object tag = textView.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type java.util.Calendar");
            respiratoryRateViewModel.loadDailyData((Calendar) tag);
            Object tag2 = textView.getTag();
            Intrinsics.checkNotNull(tag2, "null cannot be cast to non-null type java.util.Calendar");
            this.n = (Calendar) tag2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u() {
        /*
            Method dump skipped, instructions count: 670
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRate.u():void");
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateDailyLevelData(@Nullable DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), Dispatchers.getMain(), null, new a(dailyRespiratoryRateEntity, null), 2, null);
    }

    @Override // com.coveiot.android.respiratoryrate.listener.ContractRespiratoryRateDashBoard
    public void updateRangeLevelData(@NotNull List<RespiratoryRateListBean> dailyRespiratoryRate) {
        Intrinsics.checkNotNullParameter(dailyRespiratoryRate, "dailyRespiratoryRate");
    }
}
