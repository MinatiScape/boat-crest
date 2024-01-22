package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.PlanDetailsViewModel;
import java.util.LinkedHashMap;
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
public final class FragmentPlanOverviewDetails extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public PlanDetailsViewModel h;
    public FragmentInteractionListener i;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String j = "";

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentPlanOverviewDetails newInstance() {
            FragmentPlanOverviewDetails fragmentPlanOverviewDetails = new FragmentPlanOverviewDetails();
            fragmentPlanOverviewDetails.setArguments(new Bundle());
            return fragmentPlanOverviewDetails;
        }
    }

    /* loaded from: classes2.dex */
    public interface FragmentInteractionListener {
        void isFragmentPlanOverviewVisible(boolean z);

        void onViewScheduleClicked(@NotNull String str);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentPlanOverviewDetails$onViewCreated$2", f = "FragmentPlanOverviewDetails.kt", i = {1}, l = {52, 53}, m = "invokeSuspend", n = {"prePlan"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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

        /* JADX WARN: Removed duplicated region for block: B:27:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x006f  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
            /*
                Method dump skipped, instructions count: 329
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentPlanOverviewDetails.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void b(FragmentPlanOverviewDetails this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentInteractionListener fragmentInteractionListener = this$0.i;
        if (fragmentInteractionListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listner");
            fragmentInteractionListener = null;
        }
        fragmentInteractionListener.onViewScheduleClicked(this$0.j);
    }

    @JvmStatic
    @NotNull
    public static final FragmentPlanOverviewDetails newInstance() {
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

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            this.i = (FragmentInteractionListener) context;
            return;
        }
        throw new RuntimeException("Must implement FragmentInteractionListener");
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_fragment_plan_overview_details, viewGroup, false);
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
        FragmentInteractionListener fragmentInteractionListener = this.i;
        if (fragmentInteractionListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listner");
            fragmentInteractionListener = null;
        }
        fragmentInteractionListener.isFragmentPlanOverviewVisible(true);
        _$_findCachedViewById(R.id.view_schedule).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentPlanOverviewDetails.b(FragmentPlanOverviewDetails.this, view2);
            }
        });
        ViewModel viewModel = ViewModelProviders.of(this).get(PlanDetailsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(PlanDetailsViewModel::class.java)");
        this.h = (PlanDetailsViewModel) viewModel;
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(null), 2, null);
        requireActivity().setTitle(getString(R.string.plans));
    }
}
