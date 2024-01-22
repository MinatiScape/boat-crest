package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalManualSpo2Binding;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractSP02Dashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.spo2.adapters.SPO2ManualValuesAdapter;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentSpo2ViewModel;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.utils.utility.AppUtils;
import com.github.mikephil.charting.data.Entry;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalsManualSpo2Fragment extends BaseFragment implements ContractSP02Dashboard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalManualSpo2Binding m;
    public FragmentSpo2ViewModel n;
    @NotNull
    public Calendar o;
    @Nullable
    public SPO2ManualValuesAdapter p;
    public boolean q;
    public SimpleDateFormat simpleDateFormat;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalsManualSpo2Fragment newInstance() {
            return new VitalsManualSpo2Fragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsManualSpo2Fragment$handleSelectedDate$1", f = "VitalsManualSpo2Fragment.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Calendar $calendar;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Calendar calendar, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$calendar = calendar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$calendar, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                VitalsManualSpo2Fragment vitalsManualSpo2Fragment = VitalsManualSpo2Fragment.this;
                String formatDate = AppUtils.formatDate(this.$calendar.getTime(), "yyyy-MM-dd");
                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                Deferred r = vitalsManualSpo2Fragment.r(formatDate);
                this.label = 1;
                obj = r.await(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            boolean booleanValue = ((Boolean) obj).booleanValue();
            FragmentSpo2ViewModel fragmentSpo2ViewModel = VitalsManualSpo2Fragment.this.n;
            if (fragmentSpo2ViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentSpo2ViewModel = null;
            }
            fragmentSpo2ViewModel.loadHourlySP02Data(this.$calendar, booleanValue);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsManualSpo2Fragment$isLevelInterpretation$1", f = "VitalsManualSpo2Fragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public final /* synthetic */ String $date;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$date = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$date, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Boolean> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    ManualDataRepository.Companion companion = ManualDataRepository.Companion;
                    Context requireContext = VitalsManualSpo2Fragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    List<EntityManualData> spo2DataList = companion.getInstance(requireContext).getSpo2DataList(this.$date, null, null);
                    if (spo2DataList != null && (!spo2DataList.isEmpty())) {
                        VitalsManualSpo2Fragment.this.setLevelInterpretation(true);
                        for (EntityManualData entityManualData : spo2DataList) {
                            if (!entityManualData.isLevelInterpretation()) {
                                VitalsManualSpo2Fragment.this.setLevelInterpretation(false);
                            }
                        }
                    } else {
                        VitalsManualSpo2Fragment.this.setLevelInterpretation(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Boxing.boxBoolean(VitalsManualSpo2Fragment.this.isLevelInterpretation());
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalsManualSpo2Fragment() {
        AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
    }

    public static final void t(VitalsManualSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.x();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void u(VitalsManualSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.q(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
    }

    public static final void v(VitalsManualSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
            return;
        }
        this$0.q(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
    }

    public static final void w(VitalsManualSpo2Fragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void y(VitalsManualSpo2Fragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.q(newDate);
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

    @Nullable
    public final SPO2ManualValuesAdapter getAdapter() {
        return this.p;
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

    public final void initData() {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        Intrinsics.checkNotNullExpressionValue(simpleDateFormat, "getSimpleDateFormat(\"dd MMMM yyyy\")");
        setSimpleDateFormat(simpleDateFormat);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentSpo2ViewModel fragmentSpo2ViewModel = (FragmentSpo2ViewModel) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentSpo2ViewModel.class);
        this.n = fragmentSpo2ViewModel;
        if (fragmentSpo2ViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSpo2ViewModel = null;
        }
        fragmentSpo2ViewModel.setContractSP02Dashboard$app_prodRelease(this);
        FragmentSpo2ViewModel fragmentSpo2ViewModel2 = this.n;
        if (fragmentSpo2ViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentSpo2ViewModel2 = null;
        }
        fragmentSpo2ViewModel2.setMLifecycleOwner(this);
        FragmentVitalManualSpo2Binding p = p();
        ConstraintLayout constraintLayout = p.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout);
        TextView textView = p.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        TextView textView2 = p.vitalsMainData.tvVitalMax;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvVitalMax");
        inVisible(textView2);
        TextView textView3 = p.vitalsMainData.tvVitalMin;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvVitalMin");
        inVisible(textView3);
        TextView textView4 = p.vitalsMainData.tvVitalMaxValue;
        Intrinsics.checkNotNullExpressionValue(textView4, "vitalsMainData.tvVitalMaxValue");
        inVisible(textView4);
        TextView textView5 = p.vitalsMainData.tvVitalMinValue;
        Intrinsics.checkNotNullExpressionValue(textView5, "vitalsMainData.tvVitalMinValue");
        inVisible(textView5);
        ImageView imageView = p.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = p.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = p.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = p.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        ConstraintLayout constraintLayout2 = p.vitalsMainData.clTopSelector;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clTopSelector");
        gone(constraintLayout2);
        p.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_spo2);
        ConstraintLayout constraintLayout3 = p.vitalsMainData.clCenterDataHRVSpo2;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "vitalsMainData.clCenterDataHRVSpo2");
        visible(constraintLayout3);
        p.vitalsMainData.tvVitalName.setText(getString(R.string.spo2));
        p.vitalsMainData.tvVitalName.setCompoundDrawables(null, null, null, null);
        p.vitalsMainData.tvAvgHRVSpo2ValueUnit.setText("%");
        p.rvSpo2List.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView textView6 = p.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView6.setText(payUtils.getLastSyncTime(requireContext2));
        String string = getString(R.string.medical_disclaimer_info);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.medical_disclaimer_info)");
        SpannableString spannableString = new SpannableString(string);
        Context context = getContext();
        Integer valueOf = context != null ? Integer.valueOf(context.getColor(R.color.color_b3b3b3)) : null;
        Intrinsics.checkNotNull(valueOf);
        spannableString.setSpan(new ForegroundColorSpan(valueOf.intValue()), 0, 12, 18);
        p.tvdisclaimer.setText(spannableString);
        try {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            q(calendar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        s();
    }

    public final boolean isLevelInterpretation() {
        return this.q;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalManualSpo2Binding.inflate(inflater, viewGroup, false);
        View root = p().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
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
        initData();
    }

    public final FragmentVitalManualSpo2Binding p() {
        FragmentVitalManualSpo2Binding fragmentVitalManualSpo2Binding = this.m;
        Intrinsics.checkNotNull(fragmentVitalManualSpo2Binding);
        return fragmentVitalManualSpo2Binding;
    }

    public final void q(Calendar calendar) {
        if (isAdded()) {
            TextView textView = p().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                p().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                p().vitalsMainData.ibForward.setEnabled(false);
            } else {
                p().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                p().vitalsMainData.ibForward.setEnabled(true);
            }
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(calendar, null), 2, null);
            this.o = calendar;
        }
    }

    public final Deferred<Boolean> r(String str) {
        Deferred<Boolean> b2;
        b2 = kotlinx.coroutines.e.b(GlobalScope.INSTANCE, null, null, new b(str, null), 3, null);
        return b2;
    }

    public final void s() {
        FragmentVitalManualSpo2Binding p = p();
        p.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualSpo2Fragment.t(VitalsManualSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualSpo2Fragment.u(VitalsManualSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualSpo2Fragment.v(VitalsManualSpo2Fragment.this, view);
            }
        });
        p.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualSpo2Fragment.w(VitalsManualSpo2Fragment.this, view);
            }
        });
    }

    public final void setAdapter(@Nullable SPO2ManualValuesAdapter sPO2ManualValuesAdapter) {
        this.p = sPO2ManualValuesAdapter;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void setLevelInterpretation(boolean z) {
        this.q = z;
    }

    public final void setSimpleDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.simpleDateFormat = simpleDateFormat;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setSpo2_max(p().vitalsMainData.tvAvgHRVSpo2Value.getText().toString());
        shareData.setData(p().vitalsMainData.tvAvgHRVSpo2Value.getText().toString());
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.spo2);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.spo2)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSP02Dashboard
    public void updateHourlyLevelData(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar) {
        if (arrayList != null && arrayList.size() > 0) {
            LinearLayout linearLayout = p().cvSpo2;
            Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.cvSpo2");
            visible(linearLayout);
            TextView textView = p().tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
            gone(textView);
            return;
        }
        TextView textView2 = p().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
        visible(textView2);
        p().vitalsMainData.tvAvgHRVSpo2Value.setText(BleConst.GetDeviceTime);
        LinearLayout linearLayout2 = p().cvSpo2;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.cvSpo2");
        gone(linearLayout2);
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractSP02Dashboard
    public void updateHourlySPO2Data(@Nullable List<? extends EntityManualData> list, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar) {
        if (list != null) {
            if (!list.isEmpty()) {
                if (this.q) {
                    TextView textView = p().vitalsMainData.tvAvgHRVSpo2Value;
                    FragmentSpo2ViewModel fragmentSpo2ViewModel = this.n;
                    if (fragmentSpo2ViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        fragmentSpo2ViewModel = null;
                    }
                    textView.setText(fragmentSpo2ViewModel.getSpo2Level((float) list.get(0).getSpo2()));
                } else {
                    if (!(list.get(0).getSpo2() == 0.0d)) {
                        p().vitalsMainData.tvAvgHRVSpo2Value.setText(String.valueOf((int) list.get(0).getSpo2()));
                    } else {
                        p().vitalsMainData.tvAvgHRVSpo2Value.setText(BleConst.GetDeviceTime);
                    }
                }
                LinearLayout linearLayout = p().cvSpo2;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.cvSpo2");
                visible(linearLayout);
                TextView textView2 = p().tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
                gone(textView2);
                FragmentActivity activity = getActivity();
                this.p = activity != null ? new SPO2ManualValuesAdapter(activity, list) : null;
                p().rvSpo2List.setAdapter(this.p);
                return;
            }
        }
        TextView textView3 = p().tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvNoDataFound");
        visible(textView3);
        LinearLayout linearLayout2 = p().cvSpo2;
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.cvSpo2");
        gone(linearLayout2);
    }

    public final void x() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogThemeDarWindowBG, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.q0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalsManualSpo2Fragment.y(VitalsManualSpo2Fragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
    }
}
