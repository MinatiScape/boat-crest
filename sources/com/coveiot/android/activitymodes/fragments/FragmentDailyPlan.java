package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.PlanDetailsViewModel;
import com.coveiot.android.activitymodes.adapters.PlanDayListAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.covepreferences.UserDataManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
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
public final class FragmentDailyPlan extends Fragment implements Observer<List<? extends EntityPreparationDay>>, PlanDayListAdapter.DaySelectionListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentDayelectionListener fragmentDaySelectionListener;
    public int j;
    public PlanDetailsViewModel planDetailsViewModel;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int h = 1;
    @NotNull
    public final PlanDayListAdapter i = new PlanDayListAdapter();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentDailyPlan newInstance(int i, @NotNull String planId) {
            Intrinsics.checkNotNullParameter(planId, "planId");
            FragmentDailyPlan fragmentDailyPlan = new FragmentDailyPlan();
            Bundle bundle = new Bundle();
            bundle.putInt("param1", i);
            bundle.putString("param2", planId);
            fragmentDailyPlan.setArguments(bundle);
            return fragmentDailyPlan;
        }
    }

    /* loaded from: classes2.dex */
    public interface FragmentDayelectionListener {
        void onDaySelected(@NotNull EntityPreparationDay entityPreparationDay);
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDailyPlan", f = "FragmentDailyPlan.kt", i = {0, 0}, l = {147}, m = "getDistanceOfSessionInMeters", n = {"distanceInMeters", "sessionTotalDistance"}, s = {"L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FragmentDailyPlan.this.b(null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$onChanged$1", f = "FragmentDailyPlan.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"distanceList"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ List<EntityPreparationDay> $t;
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(List<EntityPreparationDay> list, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$t = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$t, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0072 -> B:15:0x0078). Please submit an issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                if (r1 == 0) goto L27
                if (r1 != r2) goto L1f
                java.lang.Object r1 = r6.L$2
                java.util.ArrayList r1 = (java.util.ArrayList) r1
                java.lang.Object r3 = r6.L$1
                java.util.Iterator r3 = (java.util.Iterator) r3
                java.lang.Object r4 = r6.L$0
                java.util.ArrayList r4 = (java.util.ArrayList) r4
                kotlin.ResultKt.throwOnFailure(r7)
                r5 = r3
                r3 = r1
                r1 = r0
                r0 = r6
                goto L78
            L1f:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L27:
                kotlin.ResultKt.throwOnFailure(r7)
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r7 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                com.coveiot.android.activitymodes.adapters.PlanDayListAdapter r7 = r7.getPlanDayListAdapter()
                java.util.List<com.coveiot.android.activitymodes.database.entities.EntityPreparationDay> r1 = r6.$t
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                r7.setPlanDayList(r1)
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r7 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                com.coveiot.android.activitymodes.adapters.PlanDayListAdapter r7 = r7.getPlanDayListAdapter()
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r1 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                int r1 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.access$getWeekNumber$p(r1)
                r7.setWeekNumber(r1)
                java.util.ArrayList r7 = new java.util.ArrayList
                r7.<init>()
                java.util.List<com.coveiot.android.activitymodes.database.entities.EntityPreparationDay> r1 = r6.$t
                java.util.Iterator r1 = r1.iterator()
                r3 = r1
                r1 = r7
                r7 = r6
            L55:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L80
                java.lang.Object r4 = r3.next()
                com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r4 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r4
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r5 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                r7.L$0 = r1
                r7.L$1 = r3
                r7.L$2 = r1
                r7.label = r2
                java.lang.Object r4 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.access$getDistanceOfSessionInMeters(r5, r4, r7)
                if (r4 != r0) goto L72
                return r0
            L72:
                r5 = r3
                r3 = r1
                r1 = r0
                r0 = r7
                r7 = r4
                r4 = r3
            L78:
                r3.add(r7)
                r7 = r0
                r0 = r1
                r1 = r4
                r3 = r5
                goto L55
            L80:
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r0 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                int r2 = com.coveiot.android.activitymodes.R.id.week_progress
                android.view.View r0 = r0._$_findCachedViewById(r2)
                android.widget.ProgressBar r0 = (android.widget.ProgressBar) r0
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r2 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                int r2 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.access$calculateWeeklyProgress(r2, r1)
                r0.setProgress(r2)
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r0 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                com.coveiot.android.activitymodes.adapters.PlanDayListAdapter r0 = r0.getPlanDayListAdapter()
                r0.setDistanceList(r1)
                com.coveiot.android.activitymodes.fragments.FragmentDailyPlan r7 = com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.this
                com.coveiot.android.activitymodes.adapters.PlanDayListAdapter r7 = r7.getPlanDayListAdapter()
                r7.notifyDataSetChanged()
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$onViewCreated$1", f = "FragmentDailyPlan.kt", i = {}, l = {74}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Context context = FragmentDailyPlan.this.getContext();
                Intrinsics.checkNotNull(context);
                int i2 = FragmentDailyPlan.this.h;
                this.label = 1;
                obj = companion.getInstance(context).getWeekLevelInfo(i2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            EntityPreparationWeek entityPreparationWeek = (EntityPreparationWeek) obj;
            FragmentDailyPlan.this.setWeeklyTarget(entityPreparationWeek.getWeeklyTarget());
            FragmentActivity activity = FragmentDailyPlan.this.getActivity();
            Intrinsics.checkNotNull(activity);
            if (!UserDataManager.getInstance(activity).isDistanceUnitInMile().booleanValue()) {
                ((TextView) FragmentDailyPlan.this._$_findCachedViewById(R.id.week_target)).setText(WorkoutUtils.INSTANCE.convertMetersToKmFloat(FragmentDailyPlan.this.getWeeklyTarget()) + ' ' + FragmentDailyPlan.this.getString(R.string.km));
            } else {
                StringBuilder sb = new StringBuilder();
                WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                sb.append(workoutUtils.convertKMToMiles(workoutUtils.convertMetersToKmFloat(FragmentDailyPlan.this.getWeeklyTarget())));
                sb.append(' ');
                sb.append(FragmentDailyPlan.this.getString(R.string.mil));
                ((TextView) FragmentDailyPlan.this._$_findCachedViewById(R.id.week_target)).setText(sb.toString());
            }
            entityPreparationWeek.getName();
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$onViewCreated$2", f = "FragmentDailyPlan.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PlanDetailsViewModel planDetailsViewModel = FragmentDailyPlan.this.getPlanDetailsViewModel();
                int i2 = FragmentDailyPlan.this.h;
                this.label = 1;
                obj = planDetailsViewModel.getDayLevelInfo(i2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            FragmentDailyPlan fragmentDailyPlan = FragmentDailyPlan.this;
            ((LiveData) obj).observe(fragmentDailyPlan, fragmentDailyPlan);
            return Unit.INSTANCE;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentDailyPlan newInstance(int i, @NotNull String str) {
        return Companion.newInstance(i, str);
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

    public final int a(ArrayList<Float> arrayList) {
        return (int) ((CollectionsKt___CollectionsKt.sumOfFloat(arrayList) / this.j) * 100);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b A[LOOP:0: B:21:0x0075->B:23:0x007b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r7, kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.a
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$a r0 = (com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$a r0 = new com.coveiot.android.activitymodes.fragments.FragmentDailyPlan$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r7 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$FloatRef r0 = (kotlin.jvm.internal.Ref.FloatRef) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L6f
        L31:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$FloatRef r8 = new kotlin.jvm.internal.Ref$FloatRef
            r8.<init>()
            android.content.Context r2 = r6.getContext()
            if (r2 == 0) goto L96
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r4 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
            android.content.Context r5 = r6.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            java.lang.Object r4 = r4.getInstance(r5)
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r4 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r4
            java.lang.String r7 = r7.getDate()
            r0.L$0 = r8
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r7 = r4.getSessionsOfParticularDay(r7, r0)
            if (r7 != r1) goto L6c
            return r1
        L6c:
            r0 = r8
            r8 = r7
            r7 = r2
        L6f:
            java.util.List r8 = (java.util.List) r8
            java.util.Iterator r8 = r8.iterator()
        L75:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L8b
            java.lang.Object r1 = r8.next()
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r1 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r1
            int r2 = r7.element
            int r1 = r1.getTotal_distance()
            int r2 = r2 + r1
            r7.element = r2
            goto L75
        L8b:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r8 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            int r7 = r7.element
            float r7 = r8.convertCmToMeters(r7)
            r0.element = r7
            r8 = r0
        L96:
            float r7 = r8.element
            java.lang.Float r7 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentDailyPlan.b(com.coveiot.android.activitymodes.database.entities.EntityPreparationDay, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final FragmentDayelectionListener getFragmentDaySelectionListener() {
        FragmentDayelectionListener fragmentDayelectionListener = this.fragmentDaySelectionListener;
        if (fragmentDayelectionListener != null) {
            return fragmentDayelectionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fragmentDaySelectionListener");
        return null;
    }

    @NotNull
    public final PlanDayListAdapter getPlanDayListAdapter() {
        return this.i;
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

    public final int getWeeklyTarget() {
        return this.j;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (context instanceof FragmentDayelectionListener) {
            setFragmentDaySelectionListener((FragmentDayelectionListener) context);
        }
    }

    @Override // androidx.lifecycle.Observer
    public /* bridge */ /* synthetic */ void onChanged(List<? extends EntityPreparationDay> list) {
        onChanged2((List<EntityPreparationDay>) list);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.h = arguments.getInt("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_fragment_daily_plan, viewGroup, false);
    }

    @Override // com.coveiot.android.activitymodes.adapters.PlanDayListAdapter.DaySelectionListener
    public void onDaySelected(@NotNull EntityPreparationDay entityPreparationDay) {
        Intrinsics.checkNotNullParameter(entityPreparationDay, "entityPreparationDay");
        getFragmentDaySelectionListener().onDaySelected(entityPreparationDay);
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
        ViewModel viewModel = ViewModelProviders.of(this).get(PlanDetailsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(this).get(PlanDetailsViewModel::class.java)");
        setPlanDetailsViewModel((PlanDetailsViewModel) viewModel);
        int i = R.id.recyclerview_day_plan;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(getContext()));
        this.i.setDaySelectionListener(this);
        GlobalScope globalScope = GlobalScope.INSTANCE;
        kotlinx.coroutines.e.e(globalScope, Dispatchers.getMain(), null, new c(null), 2, null);
        ((RecyclerView) _$_findCachedViewById(i)).setAdapter(this.i);
        kotlinx.coroutines.e.e(globalScope, Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void setFragmentDaySelectionListener(@NotNull FragmentDayelectionListener fragmentDayelectionListener) {
        Intrinsics.checkNotNullParameter(fragmentDayelectionListener, "<set-?>");
        this.fragmentDaySelectionListener = fragmentDayelectionListener;
    }

    public final void setPlanDetailsViewModel(@NotNull PlanDetailsViewModel planDetailsViewModel) {
        Intrinsics.checkNotNullParameter(planDetailsViewModel, "<set-?>");
        this.planDetailsViewModel = planDetailsViewModel;
    }

    public final void setWeeklyTarget(int i) {
        this.j = i;
    }

    /* renamed from: onChanged  reason: avoid collision after fix types in other method */
    public void onChanged2(@Nullable List<EntityPreparationDay> list) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new b(list, null), 2, null);
    }
}
