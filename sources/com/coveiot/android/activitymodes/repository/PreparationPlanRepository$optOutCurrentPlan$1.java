package com.coveiot.android.activitymodes.repository;

import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationPlan;
import com.coveiot.android.activitymodes.repository.PreparationPlanRepository;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CovePreparationPlanApi;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SDeactivatePlanResponse;
import com.coveiot.coveaccess.preparationplan.requestmodel.DeactivateFitnessPlanReq;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$optOutCurrentPlan$1", f = "PreparationPlanRepository.kt", i = {}, l = {150}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class PreparationPlanRepository$optOutCurrentPlan$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PreparationPlanRepository.OptoutListner $optoutListener;
    public int label;
    public final /* synthetic */ PreparationPlanRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreparationPlanRepository$optOutCurrentPlan$1(PreparationPlanRepository preparationPlanRepository, PreparationPlanRepository.OptoutListner optoutListner, Continuation<? super PreparationPlanRepository$optOutCurrentPlan$1> continuation) {
        super(2, continuation);
        this.this$0 = preparationPlanRepository;
        this.$optoutListener = optoutListner;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new PreparationPlanRepository$optOutCurrentPlan$1(this.this$0, this.$optoutListener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((PreparationPlanRepository$optOutCurrentPlan$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended = a.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PreparationPlanRepository preparationPlanRepository = this.this$0;
            this.label = 1;
            obj = preparationPlanRepository.a(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        DeactivateFitnessPlanReq deactivateFitnessPlanReq = new DeactivateFitnessPlanReq(((EntityPreparationPlan) obj).getUserPlanId());
        final PreparationPlanRepository preparationPlanRepository2 = this.this$0;
        final PreparationPlanRepository.OptoutListner optoutListner = this.$optoutListener;
        CovePreparationPlanApi.deActivateFitnessPlan(deactivateFitnessPlanReq, new CoveApiListener<SDeactivatePlanResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.repository.PreparationPlanRepository$optOutCurrentPlan$1.1

            @DebugMetadata(c = "com.coveiot.android.activitymodes.repository.PreparationPlanRepository$optOutCurrentPlan$1$1$onSuccess$1", f = "PreparationPlanRepository.kt", i = {}, l = {154}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.coveiot.android.activitymodes.repository.PreparationPlanRepository$optOutCurrentPlan$1$1$a */
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PreparationPlanRepository.OptoutListner $optoutListener;
                public int label;
                public final /* synthetic */ PreparationPlanRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(PreparationPlanRepository preparationPlanRepository, PreparationPlanRepository.OptoutListner optoutListner, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = preparationPlanRepository;
                    this.$optoutListener = optoutListner;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$optoutListener, continuation);
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
                        PreparationPlanRepository preparationPlanRepository = this.this$0;
                        this.label = 1;
                        if (preparationPlanRepository.deletePlan(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        ResultKt.throwOnFailure(obj);
                    }
                    this.$optoutListener.onPlanOptout();
                    return Unit.INSTANCE;
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null) {
                    PreparationPlanRepository.OptoutListner optoutListner2 = optoutListner;
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNullExpressionValue(msg, "p0!!.msg");
                    optoutListner2.onFailure(msg);
                    return;
                }
                PreparationPlanRepository.OptoutListner optoutListner3 = optoutListner;
                String string = PreparationPlanRepository.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                optoutListner3.onFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SDeactivatePlanResponse sDeactivatePlanResponse) {
                e.e(GlobalScope.INSTANCE, null, null, new a(PreparationPlanRepository.this, optoutListner, null), 3, null);
            }
        });
        return Unit.INSTANCE;
    }
}
