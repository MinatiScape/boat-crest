package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.PlanDetailsViewModel;
import com.coveiot.android.activitymodes.adapters.PlanWeekListAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWeeklyPlan extends Fragment implements Observer<List<? extends EntityPreparationWeek>>, PlanWeekListAdapter.WeekSelectionListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public PlanWeekListAdapter adapterPlanWeek;
    public FragmentWeekSelectionListener mFragmentWeekSelectionListener;
    public PlanDetailsViewModel planDetailsViewModel;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWeeklyPlan newInstance() {
            return new FragmentWeeklyPlan();
        }
    }

    /* loaded from: classes2.dex */
    public interface FragmentWeekSelectionListener {
        void isFragmentWeeklyPlanVisible(boolean z, @NotNull String str);

        void onWeekSelected(@NotNull EntityPreparationWeek entityPreparationWeek);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentWeeklyPlan$onViewCreated$1", f = "FragmentWeeklyPlan.kt", i = {}, l = {51, 53, 54, 55, 57}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int I$0;
        public Object L$0;
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0096  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00c9 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00eb A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00f0  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
            /*
                Method dump skipped, instructions count: 253
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentWeeklyPlan.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentWeeklyPlan newInstance() {
        return Companion.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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
    public final PlanWeekListAdapter getAdapterPlanWeek() {
        PlanWeekListAdapter planWeekListAdapter = this.adapterPlanWeek;
        if (planWeekListAdapter != null) {
            return planWeekListAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapterPlanWeek");
        return null;
    }

    @NotNull
    public final FragmentWeekSelectionListener getMFragmentWeekSelectionListener() {
        FragmentWeekSelectionListener fragmentWeekSelectionListener = this.mFragmentWeekSelectionListener;
        if (fragmentWeekSelectionListener != null) {
            return fragmentWeekSelectionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFragmentWeekSelectionListener");
        return null;
    }

    @NotNull
    public final PlanDetailsViewModel getPlanDetailsViewModel() {
        PlanDetailsViewModel planDetailsViewModel = this.planDetailsViewModel;
        if (planDetailsViewModel != null) {
            return planDetailsViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("planDetailsViewModel");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof FragmentWeekSelectionListener) {
            setMFragmentWeekSelectionListener((FragmentWeekSelectionListener) context);
            return;
        }
        throw new RuntimeException("Activity must implement FragmentWeekSelectionListener");
    }

    @Override // androidx.lifecycle.Observer
    public /* bridge */ /* synthetic */ void onChanged(List<? extends EntityPreparationWeek> list) {
        onChanged2((List<EntityPreparationWeek>) list);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_fragment_weekly_plan, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        setAdapterPlanWeek(new PlanWeekListAdapter());
        getAdapterPlanWeek().setWeekSelectionListener(this);
        int i = R.id.recyclerview_week_plan;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(getContext()));
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(getAdapterPlanWeek());
        ViewModel viewModel = ViewModelProviders.of(this).get(PlanDetailsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(PlanDetailsViewModel::class.java)");
        setPlanDetailsViewModel((PlanDetailsViewModel) viewModel);
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(null), 2, null);
    }

    @Override // com.coveiot.android.activitymodes.adapters.PlanWeekListAdapter.WeekSelectionListener
    public void onWeekSelected(@NotNull EntityPreparationWeek entityPreparationWeek) {
        Intrinsics.checkNotNullParameter(entityPreparationWeek, "entityPreparationWeek");
        getMFragmentWeekSelectionListener().onWeekSelected(entityPreparationWeek);
    }

    public final void setAdapterPlanWeek(@NotNull PlanWeekListAdapter planWeekListAdapter) {
        Intrinsics.checkNotNullParameter(planWeekListAdapter, "<set-?>");
        this.adapterPlanWeek = planWeekListAdapter;
    }

    public final void setMFragmentWeekSelectionListener(@NotNull FragmentWeekSelectionListener fragmentWeekSelectionListener) {
        Intrinsics.checkNotNullParameter(fragmentWeekSelectionListener, "<set-?>");
        this.mFragmentWeekSelectionListener = fragmentWeekSelectionListener;
    }

    public final void setPlanDetailsViewModel(@NotNull PlanDetailsViewModel planDetailsViewModel) {
        Intrinsics.checkNotNullParameter(planDetailsViewModel, "<set-?>");
        this.planDetailsViewModel = planDetailsViewModel;
    }

    /* renamed from: onChanged  reason: avoid collision after fix types in other method */
    public void onChanged2(@Nullable List<EntityPreparationWeek> list) {
        Log.d("planCheck", "onChanged1: " + new Gson().toJson(list));
        PlanWeekListAdapter adapterPlanWeek = getAdapterPlanWeek();
        Intrinsics.checkNotNull(list);
        adapterPlanWeek.setPlanWeekList(list);
        getAdapterPlanWeek().notifyDataSetChanged();
    }
}
