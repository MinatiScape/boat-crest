package com.coveiot.android.activitymodes.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityFitnessPlan;
import com.coveiot.android.activitymodes.adapters.FitnessPlanHistoryAdapter;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.databinding.FragmentFitnessPlanHistoryBinding;
import com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory;
import com.coveiot.android.activitymodes.models.Images;
import com.coveiot.android.activitymodes.models.PlanHistory;
import com.coveiot.android.activitymodes.models.PlanTemplate;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.utils.PlanStatus;
import com.coveiot.android.activitymodes.viewmodels.ViewModelCurrentPlanDashboard;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.realsil.sdk.dfu.DfuException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
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
public final class FragmentFitnessPlanHistory extends BaseFragment implements FitnessPlanHistoryAdapter.FitnessHistoryClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public List<PlanHistory> m;
    public FragmentFitnessPlanHistoryBinding n;
    public FitnessPlanHistoryAdapter o;
    public ViewModelCurrentPlanDashboard p;
    public ViewModelWorkoutFeedback q;
    public boolean r;
    public boolean s;
    public boolean t;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentFitnessPlanHistory newInstance(@NotNull List<PlanHistory> planHistory, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(planHistory, "planHistory");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentFitnessPlanHistory fragmentFitnessPlanHistory = new FragmentFitnessPlanHistory();
            Bundle bundle = new Bundle();
            bundle.putSerializable("planHistory", (Serializable) planHistory);
            bundle.putString("param2", param2);
            fragmentFitnessPlanHistory.setArguments(bundle);
            return fragmentFitnessPlanHistory;
        }
    }

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlanStatus.values().length];
            try {
                iArr[PlanStatus.ONGOING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PlanStatus.UPCOMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$getFitnessPlanDetail$1", f = "FragmentFitnessPlanHistory.kt", i = {}, l = {246, 247}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = null;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard2 = FragmentFitnessPlanHistory.this.p;
                if (viewModelCurrentPlanDashboard2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
                    viewModelCurrentPlanDashboard2 = null;
                }
                boolean z = FragmentFitnessPlanHistory.this.t;
                this.label = 1;
                if (viewModelCurrentPlanDashboard2.getOngoingPlanData(z, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard3 = FragmentFitnessPlanHistory.this.p;
            if (viewModelCurrentPlanDashboard3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            } else {
                viewModelCurrentPlanDashboard = viewModelCurrentPlanDashboard3;
            }
            this.label = 2;
            if (viewModelCurrentPlanDashboard.getCurrentPlan(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public static final class b extends Lambda implements Function1<Intent, Unit> {
        public final /* synthetic */ PlanHistory $plan;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(PlanHistory planHistory) {
            super(1);
            this.$plan = planHistory;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
            invoke2(intent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull Intent launchActivity) {
            Images images;
            Intrinsics.checkNotNullParameter(launchActivity, "$this$launchActivity");
            launchActivity.putExtra("userPlanId", this.$plan.getUserPlanId());
            if (this.$plan.getPlanTemplate() != null) {
                PlanTemplate planTemplate = this.$plan.getPlanTemplate();
                Intrinsics.checkNotNull(planTemplate);
                launchActivity.putExtra("planTitleHistory", planTemplate.getFullTitle());
                PlanTemplate planTemplate2 = this.$plan.getPlanTemplate();
                launchActivity.putExtra("planHistoryBackground", (planTemplate2 == null || (images = planTemplate2.getImages()) == null) ? null : images.getThumbnail1());
            }
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$onResume$1", f = "FragmentFitnessPlanHistory.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = FragmentFitnessPlanHistory.this.p;
                if (viewModelCurrentPlanDashboard == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
                    viewModelCurrentPlanDashboard = null;
                }
                viewModelCurrentPlanDashboard.checkIfPlanOngoingOrFinished();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes2.dex */
    public static final class d extends Lambda implements Function1<Intent, Unit> {
        public static final d INSTANCE = new d();

        public d() {
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

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setObserver$2$1", f = "FragmentFitnessPlanHistory.kt", i = {0}, l = {177}, m = "invokeSuspend", n = {"planStartDate"}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class e extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ EntityPreparationPlan $it;
        public Object L$0;
        public int label;
        public final /* synthetic */ FragmentFitnessPlanHistory this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(EntityPreparationPlan entityPreparationPlan, FragmentFitnessPlanHistory fragmentFitnessPlanHistory, Continuation<? super e> continuation) {
            super(2, continuation);
            this.$it = entityPreparationPlan;
            this.this$0 = fragmentFitnessPlanHistory;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new e(this.$it, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((e) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            String str;
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String formatDate = AppUtils.formatDate(AppUtils.parseDate(this.$it.getStartDate(), "yyyy-MM-dd"), "dd MMM yy");
                PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                String planId = this.$it.getPlanId();
                this.L$0 = formatDate;
                this.label = 1;
                Object planEndDate = companion.getInstance(requireContext).getPlanEndDate(planId, this);
                if (planEndDate == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = formatDate;
                obj = planEndDate;
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                str = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            String str2 = (String) obj;
            if (str2 != null) {
                String formatDate2 = AppUtils.formatDate(AppUtils.parseDate(str2, "yyyy-MM-dd"), "dd MMM yy");
                FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = this.this$0.n;
                if (fragmentFitnessPlanHistoryBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentFitnessPlanHistoryBinding = null;
                }
                TextView textView = fragmentFitnessPlanHistoryBinding.fitnessJourneyOngoing.textView;
                textView.setText(str + " - " + formatDate2);
            }
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes2.dex */
    public static final class f implements Runnable {
        public final /* synthetic */ Pair<Integer, Integer> i;

        public f(Pair<Integer, Integer> pair) {
            this.i = pair;
        }

        @Override // java.lang.Runnable
        public final void run() {
            FragmentFitnessPlanHistory.this.p(this.i.getFirst().intValue(), this.i.getSecond().intValue());
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setOnGoingPlanData$1", f = "FragmentFitnessPlanHistory.kt", i = {}, l = {DfuException.ERROR_DFU_SPP_RWS_NOT_READY}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class g extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Pair<EntityPreparationDay, EntityPreparationWeek> $data;
        public final /* synthetic */ FragmentFitnessPlanHistoryBinding $this_setOnGoingPlanData;
        public int label;
        public final /* synthetic */ FragmentFitnessPlanHistory this$0;

        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setOnGoingPlanData$1$dayData$1", f = "FragmentFitnessPlanHistory.kt", i = {}, l = {283}, m = "invokeSuspend", n = {}, s = {})
        /* loaded from: classes2.dex */
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EntityPreparationDay>, Object> {
            public int label;
            public final /* synthetic */ FragmentFitnessPlanHistory this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(FragmentFitnessPlanHistory fragmentFitnessPlanHistory, Continuation<? super a> continuation) {
                super(2, continuation);
                this.this$0 = fragmentFitnessPlanHistory;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                return new a(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            @Nullable
            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super EntityPreparationDay> continuation) {
                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.this$0.p;
                    if (viewModelCurrentPlanDashboard == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
                        viewModelCurrentPlanDashboard = null;
                    }
                    boolean z = this.this$0.t;
                    this.label = 1;
                    obj = viewModelCurrentPlanDashboard.getCurrentDayPlanData(z, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Pair<EntityPreparationDay, EntityPreparationWeek> pair, FragmentFitnessPlanHistory fragmentFitnessPlanHistory, FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding, Continuation<? super g> continuation) {
            super(2, continuation);
            this.$data = pair;
            this.this$0 = fragmentFitnessPlanHistory;
            this.$this_setOnGoingPlanData = fragmentFitnessPlanHistoryBinding;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new g(this.$data, this.this$0, this.$this_setOnGoingPlanData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((g) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:69:0x01c6  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x01ca  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r9) {
            /*
                Method dump skipped, instructions count: 470
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory.g.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final void n(FragmentFitnessPlanHistory this$0, FragmentFitnessPlanHistoryBinding this_apply, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        if (i == R.id.rbAll) {
            RadioButton rbAll = this_apply.rbAll;
            Intrinsics.checkNotNullExpressionValue(rbAll, "rbAll");
            RadioButton rbOngoing = this_apply.rbOngoing;
            Intrinsics.checkNotNullExpressionValue(rbOngoing, "rbOngoing");
            RadioButton rbCompleted = this_apply.rbCompleted;
            Intrinsics.checkNotNullExpressionValue(rbCompleted, "rbCompleted");
            this$0.s(rbAll, rbOngoing, rbCompleted);
            ConstraintLayout clOngoingPlan = this_apply.clOngoingPlan;
            Intrinsics.checkNotNullExpressionValue(clOngoingPlan, "clOngoingPlan");
            this$0.visible(clOngoingPlan);
            RecyclerView rvPlanHistory = this_apply.rvPlanHistory;
            Intrinsics.checkNotNullExpressionValue(rvPlanHistory, "rvPlanHistory");
            this$0.visible(rvPlanHistory);
            if (this$0.r && this$0.s) {
                TextView tvNoDataFound = this_apply.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(tvNoDataFound, "tvNoDataFound");
                this$0.visible(tvNoDataFound);
                return;
            }
            TextView tvNoDataFound2 = this_apply.tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(tvNoDataFound2, "tvNoDataFound");
            this$0.gone(tvNoDataFound2);
        } else if (i == R.id.rbOngoing) {
            RadioButton rbOngoing2 = this_apply.rbOngoing;
            Intrinsics.checkNotNullExpressionValue(rbOngoing2, "rbOngoing");
            RadioButton rbAll2 = this_apply.rbAll;
            Intrinsics.checkNotNullExpressionValue(rbAll2, "rbAll");
            RadioButton rbCompleted2 = this_apply.rbCompleted;
            Intrinsics.checkNotNullExpressionValue(rbCompleted2, "rbCompleted");
            this$0.s(rbOngoing2, rbAll2, rbCompleted2);
            ConstraintLayout clOngoingPlan2 = this_apply.clOngoingPlan;
            Intrinsics.checkNotNullExpressionValue(clOngoingPlan2, "clOngoingPlan");
            this$0.visible(clOngoingPlan2);
            RecyclerView rvPlanHistory2 = this_apply.rvPlanHistory;
            Intrinsics.checkNotNullExpressionValue(rvPlanHistory2, "rvPlanHistory");
            this$0.gone(rvPlanHistory2);
            if (this$0.r) {
                TextView tvNoDataFound3 = this_apply.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(tvNoDataFound3, "tvNoDataFound");
                this$0.visible(tvNoDataFound3);
                return;
            }
            TextView tvNoDataFound4 = this_apply.tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(tvNoDataFound4, "tvNoDataFound");
            this$0.gone(tvNoDataFound4);
        } else if (i == R.id.rbCompleted) {
            RadioButton rbCompleted3 = this_apply.rbCompleted;
            Intrinsics.checkNotNullExpressionValue(rbCompleted3, "rbCompleted");
            RadioButton rbOngoing3 = this_apply.rbOngoing;
            Intrinsics.checkNotNullExpressionValue(rbOngoing3, "rbOngoing");
            RadioButton rbAll3 = this_apply.rbAll;
            Intrinsics.checkNotNullExpressionValue(rbAll3, "rbAll");
            this$0.s(rbCompleted3, rbOngoing3, rbAll3);
            ConstraintLayout clOngoingPlan3 = this_apply.clOngoingPlan;
            Intrinsics.checkNotNullExpressionValue(clOngoingPlan3, "clOngoingPlan");
            this$0.gone(clOngoingPlan3);
            RecyclerView rvPlanHistory3 = this_apply.rvPlanHistory;
            Intrinsics.checkNotNullExpressionValue(rvPlanHistory3, "rvPlanHistory");
            this$0.visible(rvPlanHistory3);
            FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = null;
            if (this$0.s) {
                FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding2 = this$0.n;
                if (fragmentFitnessPlanHistoryBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding2;
                }
                TextView textView = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
                this$0.visible(textView);
                return;
            }
            FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding3 = this$0.n;
            if (fragmentFitnessPlanHistoryBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding3;
            }
            TextView textView2 = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
            this$0.gone(textView2);
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentFitnessPlanHistory newInstance(@NotNull List<PlanHistory> list, @NotNull String str) {
        return Companion.newInstance(list, str);
    }

    public static final void o(FragmentFitnessPlanHistory this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        d dVar = d.INSTANCE;
        Intent intent = new Intent(requireContext, ActivityFitnessPlan.class);
        dVar.invoke((d) intent);
        requireContext.startActivity(intent, null);
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

    @Override // com.coveiot.android.activitymodes.adapters.FitnessPlanHistoryAdapter.FitnessHistoryClickListener
    public void historyClick(@NotNull PlanHistory plan) {
        Images images;
        Intrinsics.checkNotNullParameter(plan, "plan");
        SessionManager.getInstance(requireContext()).setUserPlanId(plan.getUserPlanId());
        SessionManager sessionManager = SessionManager.getInstance(requireContext());
        PlanTemplate planTemplate = plan.getPlanTemplate();
        sessionManager.setUserPlanBackground((planTemplate == null || (images = planTemplate.getImages()) == null) ? null : images.getThumbnail1());
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        b bVar = new b(plan);
        Intent intent = new Intent(requireActivity, ActivityFitnessPlan.class);
        bVar.invoke((b) intent);
        requireActivity.startActivityForResult(intent, -1, null);
    }

    public final void m() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getSerializable("planHistory") != null) {
                Serializable serializable = arguments.getSerializable("planHistory");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type kotlin.collections.List<com.coveiot.android.activitymodes.models.PlanHistory>");
                this.m = (List) serializable;
            }
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFitnessPlanHistoryBinding inflate = FragmentFitnessPlanHistoryBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.n = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModelCurrentPlanDashboard.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this)[…lanDashboard::class.java]");
        this.p = (ViewModelCurrentPlanDashboard) viewModel;
        ViewModel viewModel2 = new ViewModelProvider(this).get(ViewModelWorkoutFeedback.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProvider(this)[…koutFeedback::class.java]");
        this.q = (ViewModelWorkoutFeedback) viewModel2;
        q();
        final FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = this.n;
        List<PlanHistory> list = null;
        if (fragmentFitnessPlanHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanHistoryBinding = null;
        }
        List<PlanHistory> list2 = this.m;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planHistoryList");
            list2 = null;
        }
        boolean z = false;
        this.s = list2 == null || list2.isEmpty();
        RadioButton rbAll = fragmentFitnessPlanHistoryBinding.rbAll;
        Intrinsics.checkNotNullExpressionValue(rbAll, "rbAll");
        RadioButton rbOngoing = fragmentFitnessPlanHistoryBinding.rbOngoing;
        Intrinsics.checkNotNullExpressionValue(rbOngoing, "rbOngoing");
        RadioButton rbCompleted = fragmentFitnessPlanHistoryBinding.rbCompleted;
        Intrinsics.checkNotNullExpressionValue(rbCompleted, "rbCompleted");
        s(rbAll, rbOngoing, rbCompleted);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        FitnessPlanHistoryAdapter fitnessPlanHistoryAdapter = new FitnessPlanHistoryAdapter(requireContext, this);
        this.o = fitnessPlanHistoryAdapter;
        fragmentFitnessPlanHistoryBinding.rvPlanHistory.setAdapter(fitnessPlanHistoryAdapter);
        fragmentFitnessPlanHistoryBinding.rvPlanHistory.setLayoutManager(new LinearLayoutManager(requireActivity()));
        List<PlanHistory> list3 = this.m;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planHistoryList");
            list3 = null;
        }
        if (list3 == null || list3.isEmpty()) {
            z = true;
        }
        if (!z) {
            FitnessPlanHistoryAdapter fitnessPlanHistoryAdapter2 = this.o;
            if (fitnessPlanHistoryAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("planHistoryAdapter");
                fitnessPlanHistoryAdapter2 = null;
            }
            List<PlanHistory> list4 = this.m;
            if (list4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("planHistoryList");
            } else {
                list = list4;
            }
            fitnessPlanHistoryAdapter2.setPlanHistoryList(list);
        }
        fragmentFitnessPlanHistoryBinding.rgCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.activitymodes.fragments.k0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentFitnessPlanHistory.n(FragmentFitnessPlanHistory.this, fragmentFitnessPlanHistoryBinding, radioGroup, i);
            }
        });
        fragmentFitnessPlanHistoryBinding.fitnessJourneyOngoing.getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentFitnessPlanHistory.o(FragmentFitnessPlanHistory.this, view2);
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    public final void p(int i, int i2) {
        FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = this.n;
        FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding2 = null;
        if (fragmentFitnessPlanHistoryBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanHistoryBinding = null;
        }
        TextView textView = fragmentFitnessPlanHistoryBinding.fitnessJourneyOngoing.tvTodayGoalTotalValue;
        StringBuilder sb = new StringBuilder();
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        double convertCmToMeters = themesUtils.convertCmToMeters(i);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        sb.append(themesUtils.getStringFormattedValueTillNDecimal(Double.valueOf(convertCmToMeters / themesUtils.getDivisionValueAsPerUnit(requireContext)), 2));
        sb.append('/');
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(requireContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(requireContext()).isDistanceUnitInMile");
        sb.append(themesUtils.getDistanceWithUnit(requireContext2, i2, isDistanceUnitInMile.booleanValue() ? 1 : 0));
        textView.setText(sb.toString());
        FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding3 = this.n;
        if (fragmentFitnessPlanHistoryBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFitnessPlanHistoryBinding3 = null;
        }
        fragmentFitnessPlanHistoryBinding3.fitnessJourneyOngoing.todayGoalProgress.setMax(i2);
        FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding4 = this.n;
        if (fragmentFitnessPlanHistoryBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFitnessPlanHistoryBinding2 = fragmentFitnessPlanHistoryBinding4;
        }
        fragmentFitnessPlanHistoryBinding2.fitnessJourneyOngoing.todayGoalProgress.setProgress((int) themesUtils.convertCmToMeters(i));
    }

    public final void q() {
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard = this.p;
        ViewModelWorkoutFeedback viewModelWorkoutFeedback = null;
        if (viewModelCurrentPlanDashboard == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            viewModelCurrentPlanDashboard = null;
        }
        MutableLiveData<Pair<EntityPreparationDay, EntityPreparationWeek>> ongoingPlanLiveData = viewModelCurrentPlanDashboard.getOngoingPlanLiveData();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        ongoingPlanLiveData.observe(viewLifecycleOwner, new Observer<T>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setObserver$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                Pair pair = (Pair) t;
                if (FragmentFitnessPlanHistory.this.isAdded()) {
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistory.n;
                    if (fragmentFitnessPlanHistoryBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding = null;
                    }
                    fragmentFitnessPlanHistory.r(fragmentFitnessPlanHistoryBinding, pair);
                }
            }
        });
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard2 = this.p;
        if (viewModelCurrentPlanDashboard2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            viewModelCurrentPlanDashboard2 = null;
        }
        MutableLiveData<EntityPreparationPlan> currentPlanLiveData = viewModelCurrentPlanDashboard2.getCurrentPlanLiveData();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "viewLifecycleOwner");
        currentPlanLiveData.observe(viewLifecycleOwner2, new Observer<T>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setObserver$$inlined$observe$2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                EntityPreparationPlan entityPreparationPlan = (EntityPreparationPlan) t;
                if (!FragmentFitnessPlanHistory.this.isAdded() || entityPreparationPlan == null || entityPreparationPlan.getStartDate() == null) {
                    return;
                }
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentFitnessPlanHistory.this), Dispatchers.getMain(), null, new FragmentFitnessPlanHistory.e(entityPreparationPlan, FragmentFitnessPlanHistory.this, null), 2, null);
            }
        });
        ViewModelCurrentPlanDashboard viewModelCurrentPlanDashboard3 = this.p;
        if (viewModelCurrentPlanDashboard3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelCurrentPlanDashboard");
            viewModelCurrentPlanDashboard3 = null;
        }
        MutableLiveData<PlanStatus> planStatusLiveData = viewModelCurrentPlanDashboard3.getPlanStatusLiveData();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "viewLifecycleOwner");
        planStatusLiveData.observe(viewLifecycleOwner3, new Observer<T>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setObserver$$inlined$observe$3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                PlanStatus planStatus = (PlanStatus) t;
                FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding = null;
                if (planStatus == null) {
                    FragmentFitnessPlanHistory.this.t = false;
                    FragmentFitnessPlanHistory.this.r = true;
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding2 = fragmentFitnessPlanHistory.n;
                    if (fragmentFitnessPlanHistoryBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding2 = null;
                    }
                    ConstraintLayout constraintLayout = fragmentFitnessPlanHistoryBinding2.fitnessJourneyOngoing.clMain;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.fitnessJourneyOngoing.clMain");
                    fragmentFitnessPlanHistory.gone(constraintLayout);
                    if (FragmentFitnessPlanHistory.this.r && FragmentFitnessPlanHistory.this.s) {
                        FragmentFitnessPlanHistory fragmentFitnessPlanHistory2 = FragmentFitnessPlanHistory.this;
                        FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding3 = fragmentFitnessPlanHistory2.n;
                        if (fragmentFitnessPlanHistoryBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding3;
                        }
                        TextView textView = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
                        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvNoDataFound");
                        fragmentFitnessPlanHistory2.visible(textView);
                        return;
                    }
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory3 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding4 = fragmentFitnessPlanHistory3.n;
                    if (fragmentFitnessPlanHistoryBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding4;
                    }
                    TextView textView2 = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
                    Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvNoDataFound");
                    fragmentFitnessPlanHistory3.gone(textView2);
                    return;
                }
                int i = FragmentFitnessPlanHistory.WhenMappings.$EnumSwitchMapping$0[planStatus.ordinal()];
                if (i == 1) {
                    FragmentFitnessPlanHistory.this.t = false;
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory4 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding5 = fragmentFitnessPlanHistory4.n;
                    if (fragmentFitnessPlanHistoryBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding5 = null;
                    }
                    ConstraintLayout constraintLayout2 = fragmentFitnessPlanHistoryBinding5.fitnessJourneyOngoing.clOngoing;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.fitnessJourneyOngoing.clOngoing");
                    fragmentFitnessPlanHistory4.visible(constraintLayout2);
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory5 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding6 = fragmentFitnessPlanHistory5.n;
                    if (fragmentFitnessPlanHistoryBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding6 = null;
                    }
                    ConstraintLayout constraintLayout3 = fragmentFitnessPlanHistoryBinding6.fitnessJourneyOngoing.clUpcoming;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout3, "binding.fitnessJourneyOngoing.clUpcoming");
                    fragmentFitnessPlanHistory5.gone(constraintLayout3);
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding7 = FragmentFitnessPlanHistory.this.n;
                    if (fragmentFitnessPlanHistoryBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding7 = null;
                    }
                    fragmentFitnessPlanHistoryBinding7.fitnessJourneyOngoing.tvWeekPlanStatus.setText("ONGOING");
                    FragmentFitnessPlanHistory.this.m();
                } else if (i != 2) {
                    FragmentFitnessPlanHistory.this.t = false;
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory6 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding8 = fragmentFitnessPlanHistory6.n;
                    if (fragmentFitnessPlanHistoryBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding8 = null;
                    }
                    ConstraintLayout constraintLayout4 = fragmentFitnessPlanHistoryBinding8.fitnessJourneyOngoing.clMain;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout4, "binding.fitnessJourneyOngoing.clMain");
                    fragmentFitnessPlanHistory6.gone(constraintLayout4);
                } else {
                    FragmentFitnessPlanHistory.this.t = true;
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory7 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding9 = fragmentFitnessPlanHistory7.n;
                    if (fragmentFitnessPlanHistoryBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding9 = null;
                    }
                    ConstraintLayout constraintLayout5 = fragmentFitnessPlanHistoryBinding9.fitnessJourneyOngoing.clOngoing;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout5, "binding.fitnessJourneyOngoing.clOngoing");
                    fragmentFitnessPlanHistory7.gone(constraintLayout5);
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory8 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding10 = fragmentFitnessPlanHistory8.n;
                    if (fragmentFitnessPlanHistoryBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding10 = null;
                    }
                    ConstraintLayout constraintLayout6 = fragmentFitnessPlanHistoryBinding10.fitnessJourneyOngoing.clUpcoming;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout6, "binding.fitnessJourneyOngoing.clUpcoming");
                    fragmentFitnessPlanHistory8.visible(constraintLayout6);
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding11 = FragmentFitnessPlanHistory.this.n;
                    if (fragmentFitnessPlanHistoryBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentFitnessPlanHistoryBinding11 = null;
                    }
                    fragmentFitnessPlanHistoryBinding11.fitnessJourneyOngoing.tvWeekPlanStatus.setText("UPCOMING");
                    FragmentFitnessPlanHistory.this.m();
                }
                if (FragmentFitnessPlanHistory.this.r && FragmentFitnessPlanHistory.this.s) {
                    FragmentFitnessPlanHistory fragmentFitnessPlanHistory9 = FragmentFitnessPlanHistory.this;
                    FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding12 = fragmentFitnessPlanHistory9.n;
                    if (fragmentFitnessPlanHistoryBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding12;
                    }
                    TextView textView3 = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
                    Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvNoDataFound");
                    fragmentFitnessPlanHistory9.visible(textView3);
                    return;
                }
                FragmentFitnessPlanHistory fragmentFitnessPlanHistory10 = FragmentFitnessPlanHistory.this;
                FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding13 = fragmentFitnessPlanHistory10.n;
                if (fragmentFitnessPlanHistoryBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentFitnessPlanHistoryBinding = fragmentFitnessPlanHistoryBinding13;
                }
                TextView textView4 = fragmentFitnessPlanHistoryBinding.tvNoDataFound;
                Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvNoDataFound");
                fragmentFitnessPlanHistory10.gone(textView4);
            }
        });
        ViewModelWorkoutFeedback viewModelWorkoutFeedback2 = this.q;
        if (viewModelWorkoutFeedback2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelWorkoutFeedback");
        } else {
            viewModelWorkoutFeedback = viewModelWorkoutFeedback2;
        }
        MutableLiveData<Pair<Integer, Integer>> dailyDistanceData = viewModelWorkoutFeedback.getDailyDistanceData();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "viewLifecycleOwner");
        dailyDistanceData.observe(viewLifecycleOwner4, new Observer<T>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanHistory$setObserver$$inlined$observe$4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                FragmentFitnessPlanHistory.this.requireActivity().runOnUiThread(new FragmentFitnessPlanHistory.f((Pair) t));
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    public final void r(FragmentFitnessPlanHistoryBinding fragmentFitnessPlanHistoryBinding, Pair<EntityPreparationDay, EntityPreparationWeek> pair) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new g(pair, this, fragmentFitnessPlanHistoryBinding, null), 2, null);
    }

    public final void s(RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3) {
        radioButton.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.metropolis_bold));
        Context requireContext = requireContext();
        int i = R.font.metropolis_regular;
        radioButton2.setTypeface(ResourcesCompat.getFont(requireContext, i));
        radioButton3.setTypeface(ResourcesCompat.getFont(requireContext(), i));
    }
}
