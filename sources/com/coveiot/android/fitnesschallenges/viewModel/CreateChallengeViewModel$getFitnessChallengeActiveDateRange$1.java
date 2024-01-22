package com.coveiot.android.fitnesschallenges.viewModel;

import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1", f = "CreateChallengeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ CreateChallengeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1(CreateChallengeViewModel createChallengeViewModel, Continuation<? super CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1> continuation) {
        super(2, continuation);
        this.this$0 = createChallengeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (CoveUtils.INSTANCE.isNetConnected(this.this$0.getContext())) {
                final CreateChallengeViewModel createChallengeViewModel = this.this$0;
                CoveFitnessChallengeApi.getFitnessChallengeDates(new CoveApiListener<GetChallengeStartNEndDateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1.1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LogHelper.d("fitnessChallenge", "onError_challengeActiveDateRange:" + new Gson().toJson(coveApiErrorModel));
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull GetChallengeStartNEndDateRes challengeActiveDateRange) {
                        Intrinsics.checkNotNullParameter(challengeActiveDateRange, "challengeActiveDateRange");
                        LogHelper.d("fitnessChallenge", "challengeActiveDateRange:" + new Gson().toJson(challengeActiveDateRange));
                        FitnessChallengeSessionManager.getInstance(CreateChallengeViewModel.this.getContext()).saveFitnessChallengeActiveDateRange(challengeActiveDateRange);
                    }
                });
            } else {
                LogHelper.d("fitnessChallenge", "No Internet");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
