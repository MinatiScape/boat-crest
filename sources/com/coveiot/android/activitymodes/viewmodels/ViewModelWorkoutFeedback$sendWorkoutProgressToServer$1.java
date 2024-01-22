package com.coveiot.android.activitymodes.viewmodels;

import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateReq;
import com.coveiot.coveaccess.preparationplan.requestmodel.PlanProgressUpdateRes;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1", f = "ViewModelWorkoutFeedback.kt", i = {0}, l = {69}, m = "invokeSuspend", n = {"planProgressUpdateReq"}, s = {"L$0"})
/* loaded from: classes2.dex */
public final class ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ ViewModelWorkoutFeedback this$0;

    @DebugMetadata(c = "com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1$currentPlanAndProgress$1", f = "ViewModelWorkoutFeedback.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes2.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>>, Object> {
        public int label;
        public final /* synthetic */ ViewModelWorkoutFeedback this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(ViewModelWorkoutFeedback viewModelWorkoutFeedback, Continuation<? super a> continuation) {
            super(2, continuation);
            this.this$0 = viewModelWorkoutFeedback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends EntityPreparationPlan, ? extends Float>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super Pair<EntityPreparationPlan, Float>>) continuation);
        }

        @Nullable
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final Object invoke2(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Pair<EntityPreparationPlan, Float>> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
                this.label = 1;
                obj = viewModelWorkoutFeedback.getCurrentPlanAndTotalProgress(this);
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
    public ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1(ViewModelWorkoutFeedback viewModelWorkoutFeedback, Continuation<? super ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1> continuation) {
        super(2, continuation);
        this.this$0 = viewModelWorkoutFeedback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        PlanProgressUpdateReq planProgressUpdateReq;
        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlanProgressUpdateReq planProgressUpdateReq2 = new PlanProgressUpdateReq();
            CoroutineDispatcher io2 = Dispatchers.getIO();
            a aVar = new a(this.this$0, null);
            this.L$0 = planProgressUpdateReq2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(io2, aVar, this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            planProgressUpdateReq = planProgressUpdateReq2;
            obj = withContext;
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            planProgressUpdateReq = (PlanProgressUpdateReq) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Pair pair = (Pair) obj;
        planProgressUpdateReq.setProgressStatus("FINISHED");
        planProgressUpdateReq.setUserPlanId(((EntityPreparationPlan) pair.getFirst()).getUserPlanId());
        final int floatValue = (int) ((Number) pair.getSecond()).floatValue();
        planProgressUpdateReq.setPercentage(floatValue);
        if (floatValue >= 50 && floatValue - new PreferenceManager(this.this$0.d).getLastWorkoutProgressUpdated() >= 10) {
            final ViewModelWorkoutFeedback viewModelWorkoutFeedback = this.this$0;
            CovePreparationPlanApi.updatePlanProgress(planProgressUpdateReq, new CoveApiListener<PlanProgressUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutFeedback$sendWorkoutProgressToServer$1.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel object) {
                    Intrinsics.checkNotNullParameter(object, "object");
                    if (object.getCode() == 200) {
                        new PreferenceManager(ViewModelWorkoutFeedback.this.d).setLastWorkoutProgressUpdated(floatValue);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable PlanProgressUpdateRes planProgressUpdateRes) {
                    new PreferenceManager(ViewModelWorkoutFeedback.this.d).setLastWorkoutProgressUpdated(floatValue);
                }
            });
        }
        return Unit.INSTANCE;
    }
}
