package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.eventmodels.PlanDeleted;
import com.coveiot.android.activitymodes.eventmodels.PlanUpdated;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentPreparationPlanMainDashboard extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentPreparationPlanMainDashboard newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentPreparationPlanMainDashboard fragmentPreparationPlanMainDashboard = new FragmentPreparationPlanMainDashboard();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentPreparationPlanMainDashboard.setArguments(bundle);
            return fragmentPreparationPlanMainDashboard;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentPreparationPlanMainDashboard$showCurrentDashboardOrFTU$1", f = "FragmentPreparationPlanMainDashboard.kt", i = {}, l = {107}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentPreparationPlanMainDashboard$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static final class C0242a extends Lambda implements Function1<Intent, Unit> {
            public static final C0242a INSTANCE = new C0242a();

            public C0242a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                invoke2(intent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Intent launchActivity) {
                Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            }
        }

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

        /* JADX WARN: Removed duplicated region for block: B:18:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r5) {
            /*
                Method dump skipped, instructions count: 279
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentPreparationPlanMainDashboard.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentPreparationPlanMainDashboard newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
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

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        showProgress(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.MAIN_MODES_DASHBOARD;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        CoveEventBusManager.getInstance().getEventBus().register(this);
        return inflater.inflate(R.layout.fragment_fragment_preparation_plan_main_dashboard, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onPlanDeleted(@NotNull PlanDeleted planDeleted) {
        Intrinsics.checkNotNullParameter(planDeleted, "planDeleted");
        LogHelper.d("preplan", "PlanUpdated: called");
        showCurrentDashboardOrFTU();
    }

    @Subscribe
    public final void onPlanUpdated(@NotNull PlanUpdated planUpdated) {
        Intrinsics.checkNotNullParameter(planUpdated, "planUpdated");
        LogHelper.d("preplan", "PlanUpdated: called");
        String string = getString(R.string.loading_plan);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loading_plan)");
        showProgresswithMsg(string);
        PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        companion.getInstance(context).getCurrentPlanFromServer(new PreparationPlanRepository.PlanDetailsListner() { // from class: com.coveiot.android.activitymodes.fragments.FragmentPreparationPlanMainDashboard$onPlanUpdated$1
            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
            public void onFailure(@NotNull String mesaage) {
                Intrinsics.checkNotNullParameter(mesaage, "mesaage");
                FragmentPreparationPlanMainDashboard.this.dismissProgress();
                Context context2 = FragmentPreparationPlanMainDashboard.this.getContext();
                Intrinsics.checkNotNull(context2);
                Toast.makeText(context2, FragmentPreparationPlanMainDashboard.this.getString(R.string.some_thing_went_wrong), 0).show();
            }

            @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
            public void onPlanFetchedSuccessfully() {
                FragmentPreparationPlanMainDashboard.this.requireActivity().finish();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogHelper.d("preplan", "FragmentPreparationMain onStart: called");
        super.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        LogHelper.d("preplan", "FragmentPreparationMain onViewCreated: called");
        showCurrentDashboardOrFTU();
    }

    public final void showCurrentDashboardOrFTU() {
        dismissProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }
}
