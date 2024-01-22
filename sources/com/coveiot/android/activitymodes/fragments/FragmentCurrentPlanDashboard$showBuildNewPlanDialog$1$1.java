package com.coveiot.android.activitymodes.fragments;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateRes;
import com.realsil.sdk.dfu.DfuException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentCurrentPlanDashboard;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.isAdded()) {
                ViewModel viewModel = ViewModelProviders.of(this.this$0).get(ViewModelWorkoutFeedback.class);
                Intrinsics.checkNotNullExpressionValue(viewModel, "of(this@FragmentCurrentP…koutFeedback::class.java)");
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = (ViewModelWorkoutFeedback) viewModel;
                try {
                    final FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard = this.this$0;
                    viewModelWorkoutFeedback.sendWorkoutProgressToServerAfterPlanFinished(new CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1.1

                        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1$1$onError$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {278}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1$1$a */
                        /* loaded from: classes2.dex */
                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public int label;
                            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public a(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super a> continuation) {
                                super(2, continuation);
                                this.this$0 = fragmentCurrentPlanDashboard;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @NotNull
                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                return new a(this.this$0, continuation);
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
                                    Context context = this.this$0.getContext();
                                    if (context != null) {
                                        this.label = 1;
                                        if (PreparationPlanRepository.Companion.getInstance(context).deletePlan(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                } else {
                                    ResultKt.throwOnFailure(obj);
                                }
                                if (this.this$0.getParentFragment() != null && (this.this$0.getParentFragment() instanceof FragmentPreparationPlanMainDashboard)) {
                                    Fragment parentFragment = this.this$0.getParentFragment();
                                    Intrinsics.checkNotNull(parentFragment);
                                    ((FragmentPreparationPlanMainDashboard) parentFragment).showCurrentDashboardOrFTU();
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1$1$onSuccess$1", f = "FragmentCurrentPlanDashboard.kt", i = {}, l = {DfuException.ERROR_CONNECT_ERROR}, m = "invokeSuspend", n = {}, s = {})
                        /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentCurrentPlanDashboard$showBuildNewPlanDialog$1$1$1$b */
                        /* loaded from: classes2.dex */
                        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public int label;
                            public final /* synthetic */ FragmentCurrentPlanDashboard this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public b(FragmentCurrentPlanDashboard fragmentCurrentPlanDashboard, Continuation<? super b> continuation) {
                                super(2, continuation);
                                this.this$0 = fragmentCurrentPlanDashboard;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @NotNull
                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                return new b(this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            @Nullable
                            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @Nullable
                            public final Object invokeSuspend(@NotNull Object obj) {
                                Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    Context context = this.this$0.getContext();
                                    if (context != null) {
                                        this.label = 1;
                                        if (PreparationPlanRepository.Companion.getInstance(context).deletePlan(this) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                } else if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                } else {
                                    ResultKt.throwOnFailure(obj);
                                }
                                if (this.this$0.getParentFragment() != null && (this.this$0.getParentFragment() instanceof FragmentPreparationPlanMainDashboard)) {
                                    Fragment parentFragment = this.this$0.getParentFragment();
                                    Intrinsics.checkNotNull(parentFragment);
                                    ((FragmentPreparationPlanMainDashboard) parentFragment).showCurrentDashboardOrFTU();
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            if (coveApiErrorModel == null || coveApiErrorModel.getCode() != 200) {
                                return;
                            }
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(FragmentCurrentPlanDashboard.this, null), 2, null);
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@Nullable PlanProgressUpdateRes planProgressUpdateRes) {
                            kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new b(FragmentCurrentPlanDashboard.this, null), 2, null);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
