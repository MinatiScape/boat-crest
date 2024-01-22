package com.coveiot.android.activitymodes.fragments;

import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.coveiot.android.activitymodes.eventmodels.PlanDeleted;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.utils.CoveEventBusManager;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Continuation<? super FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1> continuation) {
        super(2, continuation);
        this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PreparationPlanRepository.Companion companion = PreparationPlanRepository.Companion;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            final FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo = this.this$0;
            companion.getInstance(requireActivity).optOutCurrentPlan(new PreparationPlanRepository.OptoutListner() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1.1

                @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1$1$onFailure$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1$1$a */
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ String $message;
                    public int label;
                    public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, String str, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
                        this.$message = str;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$message, continuation);
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
                            Toast.makeText(this.this$0.requireContext(), this.$message, 1).show();
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
                public void onFailure(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentFitnessPlanWeekAndDayInfo.this), Dispatchers.getMain(), null, new a(FragmentFitnessPlanWeekAndDayInfo.this, message, null), 2, null);
                    }
                }

                @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.OptoutListner
                public void onPlanOptout() {
                    PreparationPlanRepository.Companion companion2 = PreparationPlanRepository.Companion;
                    FragmentActivity requireActivity2 = FragmentFitnessPlanWeekAndDayInfo.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                    final FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo2 = FragmentFitnessPlanWeekAndDayInfo.this;
                    companion2.getInstance(requireActivity2).getCurrentPlanFromServer(new PreparationPlanRepository.PlanDetailsListner() { // from class: com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1$1$onPlanOptout$1

                        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1$1$onPlanOptout$1$onFailure$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* loaded from: classes2.dex */
                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public int label;
                            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public a(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Continuation<? super a> continuation) {
                                super(2, continuation);
                                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
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
                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                if (this.label == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.this$0.dismissProgress();
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentFitnessPlanWeekAndDayInfo$optOutCurrentPlan$1$1$onPlanOptout$1$onPlanFetchedSuccessfully$1", f = "FragmentFitnessPlanWeekAndDayInfo.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* loaded from: classes2.dex */
                        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public int label;
                            public final /* synthetic */ FragmentFitnessPlanWeekAndDayInfo this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public b(FragmentFitnessPlanWeekAndDayInfo fragmentFitnessPlanWeekAndDayInfo, Continuation<? super b> continuation) {
                                super(2, continuation);
                                this.this$0 = fragmentFitnessPlanWeekAndDayInfo;
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
                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                if (this.label == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    CoveEventBusManager.getInstance().getEventBus().post(new PlanDeleted());
                                    this.this$0.dismissProgress();
                                    this.this$0.requireActivity().finish();
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
                        public void onFailure(@NotNull String message) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentFitnessPlanWeekAndDayInfo.this), Dispatchers.getMain(), null, new a(FragmentFitnessPlanWeekAndDayInfo.this, null), 2, null);
                            }
                        }

                        @Override // com.coveiot.android.activitymodes.repository.PreparationPlanRepository.PlanDetailsListner
                        public void onPlanFetchedSuccessfully() {
                            if (FragmentFitnessPlanWeekAndDayInfo.this.isAdded()) {
                                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(FragmentFitnessPlanWeekAndDayInfo.this), Dispatchers.getMain(), null, new b(FragmentFitnessPlanWeekAndDayInfo.this, null), 2, null);
                            }
                        }
                    });
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
