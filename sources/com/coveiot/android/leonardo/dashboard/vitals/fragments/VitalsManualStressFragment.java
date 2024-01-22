package com.coveiot.android.leonardo.dashboard.vitals.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
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
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.FragmentVitalManualStressBinding;
import com.coveiot.android.leonardo.dashboard.health.adapters.StressManualValuesAdapter;
import com.coveiot.android.leonardo.dashboard.health.listeners.ContractStressDashboard;
import com.coveiot.android.leonardo.dashboard.health.model.ShareData;
import com.coveiot.android.leonardo.dashboard.health.viewmodel.FragmentStressViewModelNew;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.AppNavigator;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.adapter.TipsAdapter;
import com.coveiot.android.theme.model.TipsModel;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class VitalsManualStressFragment extends BaseFragment implements ContractStressDashboard {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public DatePickerDialog datePickerDialog;
    @Nullable
    public FragmentVitalManualStressBinding m;
    public FragmentStressViewModelNew n;
    @NotNull
    public Calendar o;
    @Nullable
    public StressManualValuesAdapter p;
    @NotNull
    public final int[] q;
    @NotNull
    public final int[] r;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final VitalsManualStressFragment newInstance() {
            return new VitalsManualStressFragment();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.vitals.fragments.VitalsManualStressFragment$handleSelectedDate$1", f = "VitalsManualStressFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                FragmentStressViewModelNew fragmentStressViewModelNew = VitalsManualStressFragment.this.n;
                if (fragmentStressViewModelNew == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    fragmentStressViewModelNew = null;
                }
                fragmentStressViewModelNew.loadHourlyStressData(this.$calendar);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public VitalsManualStressFragment() {
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
        this.o = calendar;
        this.q = new int[]{2131233536, 2131233537, 2131233538, 2131233539};
        this.r = new int[]{R.string.stress_reduce_tip1, R.string.stress_reduce_tip2, R.string.stress_reduce_tip3, R.string.stress_reduce_tip4};
    }

    public static final void A(VitalsManualStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.share();
    }

    public static final void C(VitalsManualStressFragment this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar newDate = Calendar.getInstance();
        newDate.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(newDate, "newDate");
        this$0.t(newDate);
    }

    public static final void u(final FragmentVitalManualStressBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollManualStress.post(new Runnable() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.b1
            @Override // java.lang.Runnable
            public final void run() {
                VitalsManualStressFragment.v(FragmentVitalManualStressBinding.this);
            }
        });
    }

    public static final void v(FragmentVitalManualStressBinding this_apply) {
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this_apply.nestedScrollManualStress.fullScroll(130);
    }

    public static final void x(VitalsManualStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B();
        this$0.getDatePickerDialog().getDatePicker().setMaxDate(System.currentTimeMillis());
        this$0.getDatePickerDialog().show();
    }

    public static final void y(VitalsManualStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t(PayUtils.INSTANCE.getPreviousDayCalendar(this$0.o));
    }

    public static final void z(VitalsManualStressFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (DateUtils.isToday(this$0.o.getTimeInMillis())) {
            return;
        }
        this$0.t(PayUtils.INSTANCE.getNextDayCalendar(this$0.o));
    }

    public final void B() {
        Calendar calendar = this.o;
        setDatePickerDialog(new DatePickerDialog(requireActivity(), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.v0
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                VitalsManualStressFragment.C(VitalsManualStressFragment.this, datePicker, i, i2, i3);
            }
        }, calendar.get(1), calendar.get(2), calendar.get(5)));
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
    public final StressManualValuesAdapter getAdapter() {
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

    public final void initData() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FragmentStressViewModelNew fragmentStressViewModelNew = (FragmentStressViewModelNew) ViewModelProviders.of(this, new ViewModelFactory(requireContext)).get(FragmentStressViewModelNew.class);
        this.n = fragmentStressViewModelNew;
        FragmentStressViewModelNew fragmentStressViewModelNew2 = null;
        if (fragmentStressViewModelNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentStressViewModelNew = null;
        }
        fragmentStressViewModelNew.setContractStressDashboard$app_prodRelease(this);
        FragmentStressViewModelNew fragmentStressViewModelNew3 = this.n;
        if (fragmentStressViewModelNew3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentStressViewModelNew2 = fragmentStressViewModelNew3;
        }
        fragmentStressViewModelNew2.setMLifecycleOwner(this);
        final FragmentVitalManualStressBinding r = r();
        ConstraintLayout constraintLayout = r.vitalsMainData.clCenterDataStepsSleep;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "vitalsMainData.clCenterDataStepsSleep");
        gone(constraintLayout);
        TextView textView = r.vitalsMainData.tvVitalInfo;
        Intrinsics.checkNotNullExpressionValue(textView, "vitalsMainData.tvVitalInfo");
        gone(textView);
        TextView textView2 = r.vitalsMainData.tvVitalMax;
        Intrinsics.checkNotNullExpressionValue(textView2, "vitalsMainData.tvVitalMax");
        inVisible(textView2);
        TextView textView3 = r.vitalsMainData.tvVitalMin;
        Intrinsics.checkNotNullExpressionValue(textView3, "vitalsMainData.tvVitalMin");
        inVisible(textView3);
        TextView textView4 = r.vitalsMainData.tvVitalMaxValue;
        Intrinsics.checkNotNullExpressionValue(textView4, "vitalsMainData.tvVitalMaxValue");
        inVisible(textView4);
        TextView textView5 = r.vitalsMainData.tvVitalMinValue;
        Intrinsics.checkNotNullExpressionValue(textView5, "vitalsMainData.tvVitalMinValue");
        inVisible(textView5);
        ImageView imageView = r.vitalsMainData.ivMin;
        Intrinsics.checkNotNullExpressionValue(imageView, "vitalsMainData.ivMin");
        inVisible(imageView);
        ImageView imageView2 = r.vitalsMainData.ivMax;
        Intrinsics.checkNotNullExpressionValue(imageView2, "vitalsMainData.ivMax");
        inVisible(imageView2);
        ImageView imageView3 = r.vitalsMainData.ivMinBg;
        Intrinsics.checkNotNullExpressionValue(imageView3, "vitalsMainData.ivMinBg");
        inVisible(imageView3);
        ImageView imageView4 = r.vitalsMainData.ivMaxBg;
        Intrinsics.checkNotNullExpressionValue(imageView4, "vitalsMainData.ivMaxBg");
        inVisible(imageView4);
        ConstraintLayout constraintLayout2 = r.vitalsMainData.clTopSelector;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "vitalsMainData.clTopSelector");
        gone(constraintLayout2);
        r.vitalsMainData.ivCenterVital.setImageResource(R.drawable.ic_stress);
        ConstraintLayout constraintLayout3 = r.vitalsMainData.clCenterDataHRVSpo2;
        Intrinsics.checkNotNullExpressionValue(constraintLayout3, "vitalsMainData.clCenterDataHRVSpo2");
        gone(constraintLayout3);
        ConstraintLayout constraintLayout4 = r.vitalsMainData.clCenterDataEnergyAndStress;
        Intrinsics.checkNotNullExpressionValue(constraintLayout4, "vitalsMainData.clCenterDataEnergyAndStress");
        visible(constraintLayout4);
        r.vitalsMainData.tvVitalName.setText(getString(R.string.stress_level));
        r.vitalsMainData.tvAvgType.setText(getString(R.string.avg_stress_level));
        r.stressTipsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        TipsAdapter tipsAdapter = new TipsAdapter();
        tipsAdapter.setTipsList(s());
        r.stressTipsRecycler.setAdapter(tipsAdapter);
        r.vitalsMainData.tvVitalName.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualStressFragment.u(FragmentVitalManualStressBinding.this, view);
            }
        });
        r.stressRangeBar.setImageResource(R.drawable.stress_range_bar_2);
        r.rvStressList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        TextView tvstressdescription = r.tvstressdescription;
        Intrinsics.checkNotNullExpressionValue(tvstressdescription, "tvstressdescription");
        themesUtils.makeTextViewResizable(tvstressdescription, 3, "... Read More", true);
        TextView textView6 = r.vitalsMainData.tvLastSyncTime;
        PayUtils payUtils = PayUtils.INSTANCE;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        textView6.setText(payUtils.getLastSyncTime(requireContext2));
        try {
            Calendar calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
            t(calendar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        w();
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.m = FragmentVitalManualStressBinding.inflate(inflater, viewGroup, false);
        View root = r().getRoot();
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

    public final FragmentVitalManualStressBinding r() {
        FragmentVitalManualStressBinding fragmentVitalManualStressBinding = this.m;
        Intrinsics.checkNotNull(fragmentVitalManualStressBinding);
        return fragmentVitalManualStressBinding;
    }

    public final List<TipsModel> s() {
        ArrayList arrayList = new ArrayList();
        int length = this.r.length;
        for (int i = 0; i < length; i++) {
            int i2 = this.q[i];
            String string = getString(this.r[i]);
            Intrinsics.checkNotNullExpressionValue(string, "getString(description[i])");
            arrayList.add(new TipsModel(i2, string));
        }
        return arrayList;
    }

    public final void setAdapter(@Nullable StressManualValuesAdapter stressManualValuesAdapter) {
        this.p = stressManualValuesAdapter;
    }

    public final void setDatePickerDialog(@NotNull DatePickerDialog datePickerDialog) {
        Intrinsics.checkNotNullParameter(datePickerDialog, "<set-?>");
        this.datePickerDialog = datePickerDialog;
    }

    public final void share() {
        ShareData shareData = new ShareData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        shareData.setData(r().vitalsMainData.tvAvgVitalValue.getText().toString());
        shareData.setGraphType(getResources().getString(R.string.share_daily));
        shareData.setDwmValue(simpleDateFormat.format(this.o.getTime()));
        AppNavigator.Companion companion = AppNavigator.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getResources().getString(R.string.stress);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.stress)");
        companion.navigateToShareScreen(requireContext, shareData, string);
    }

    public final void t(Calendar calendar) {
        if (isAdded()) {
            TextView textView = r().vitalsMainData.tvSelectedTypeValue;
            long timeInMillis = calendar.getTimeInMillis();
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            textView.setText(PayUtils.getTodayYesterdayStringFromTimeStamp(timeInMillis, requireContext));
            if (DateUtils.isToday(calendar.getTimeInMillis())) {
                r().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_grey);
                r().vitalsMainData.ibForward.setEnabled(false);
            } else {
                r().vitalsMainData.ibForward.setImageResource(R.drawable.ic_small_right_arrow_white);
                r().vitalsMainData.ibForward.setEnabled(true);
            }
            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(calendar, null), 2, null);
            this.o = calendar;
        }
    }

    @Override // com.coveiot.android.leonardo.dashboard.health.listeners.ContractStressDashboard
    public void updateHourlyStressData(@Nullable List<? extends EntityManualData> list, @Nullable String str, @Nullable Long l, @Nullable Calendar calendar) {
        if (list != null && list.size() > 0) {
            FragmentActivity activity = getActivity();
            this.p = activity != null ? new StressManualValuesAdapter(activity, list) : null;
            FragmentVitalManualStressBinding r = r();
            LinearLayout cvStress = r.cvStress;
            Intrinsics.checkNotNullExpressionValue(cvStress, "cvStress");
            visible(cvStress);
            TextView tvNoDataFound = r.tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(tvNoDataFound, "tvNoDataFound");
            gone(tvNoDataFound);
            RecyclerView stressTipsRecycler = r.stressTipsRecycler;
            Intrinsics.checkNotNullExpressionValue(stressTipsRecycler, "stressTipsRecycler");
            visible(stressTipsRecycler);
            TextView tvTipsToReduce = r.tvTipsToReduce;
            Intrinsics.checkNotNullExpressionValue(tvTipsToReduce, "tvTipsToReduce");
            visible(tvTipsToReduce);
            r.rvStressList.setAdapter(this.p);
            if (list.get(0).getStress() != 0) {
                FragmentVitalManualStressBinding r2 = r();
                r2.vitalsMainData.tvAvgVitalValue.setText(str);
                TextView textView = r2.vitalsMainData.tvAvgValueLevel;
                Intrinsics.checkNotNull(str);
                int parseInt = Integer.parseInt(str);
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                textView.setText(PayUtils.getStressRange(parseInt, requireContext));
                return;
            }
            r().vitalsMainData.tvAvgVitalValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
            r().vitalsMainData.tvAvgValueLevel.setText("-");
            return;
        }
        FragmentVitalManualStressBinding r3 = r();
        LinearLayout cvStress2 = r3.cvStress;
        Intrinsics.checkNotNullExpressionValue(cvStress2, "cvStress");
        gone(cvStress2);
        TextView tvNoDataFound2 = r3.tvNoDataFound;
        Intrinsics.checkNotNullExpressionValue(tvNoDataFound2, "tvNoDataFound");
        visible(tvNoDataFound2);
        RecyclerView stressTipsRecycler2 = r3.stressTipsRecycler;
        Intrinsics.checkNotNullExpressionValue(stressTipsRecycler2, "stressTipsRecycler");
        gone(stressTipsRecycler2);
        TextView tvTipsToReduce2 = r3.tvTipsToReduce;
        Intrinsics.checkNotNullExpressionValue(tvTipsToReduce2, "tvTipsToReduce");
        gone(tvTipsToReduce2);
        r3.vitalsMainData.tvAvgVitalValue.setText(String.valueOf(AppConstants.TWO_DASH_STRING.getValue()));
        r3.vitalsMainData.tvAvgValueLevel.setText("-");
    }

    public final void w() {
        FragmentVitalManualStressBinding r = r();
        r.vitalsMainData.tvSelectedTypeValue.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.a1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualStressFragment.x(VitalsManualStressFragment.this, view);
            }
        });
        r.vitalsMainData.ibPrevious.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualStressFragment.y(VitalsManualStressFragment.this, view);
            }
        });
        r.vitalsMainData.ibForward.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualStressFragment.z(VitalsManualStressFragment.this, view);
            }
        });
        r.vitalsMainData.ibShareVitals.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.vitals.fragments.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VitalsManualStressFragment.A(VitalsManualStressFragment.this, view);
            }
        });
    }
}
