package com.coveiot.android.dashboard2.viewmodel;

import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.preference.FitnessChallengeSessionManager;
import com.coveiot.android.fitnesschallenges.utils.ExtensionsKt;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.FitnessChallengeStatsReq;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel$postFitnessChallengeStats$1", f = "FitnessChallengeSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class FitnessChallengeSyncViewModel$postFitnessChallengeStats$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<FitnessChallengeStatsReq.ChallengeStat> $challengeStatsList;
    public int label;
    public final /* synthetic */ FitnessChallengeSyncViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FitnessChallengeSyncViewModel$postFitnessChallengeStats$1(FitnessChallengeSyncViewModel fitnessChallengeSyncViewModel, List<? extends FitnessChallengeStatsReq.ChallengeStat> list, Continuation<? super FitnessChallengeSyncViewModel$postFitnessChallengeStats$1> continuation) {
        super(2, continuation);
        this.this$0 = fitnessChallengeSyncViewModel;
        this.$challengeStatsList = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new FitnessChallengeSyncViewModel$postFitnessChallengeStats$1(this.this$0, this.$challengeStatsList, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((FitnessChallengeSyncViewModel$postFitnessChallengeStats$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (CoveUtils.INSTANCE.isNetConnected(this.this$0.getContext())) {
                FitnessChallengeStatsReq fitnessChallengeStatsReq = new FitnessChallengeStatsReq();
                fitnessChallengeStatsReq.setChallengeStatsList(this.$challengeStatsList);
                final FitnessChallengeSyncViewModel fitnessChallengeSyncViewModel = this.this$0;
                CoveFitnessChallengeApi.postFitnessChallengeDetails(fitnessChallengeStatsReq, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.FitnessChallengeSyncViewModel$postFitnessChallengeStats$1.1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LogHelper.d("fitnessChallenge", "onError_challengeStats:" + new Gson().toJson(coveApiErrorModel));
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull CommonResponseClass commonResponseClass) {
                        Intrinsics.checkNotNullParameter(commonResponseClass, "commonResponseClass");
                        LogHelper.d("fitnessChallenge", "challengeStats:" + new Gson().toJson(commonResponseClass));
                        FitnessChallengeSessionManager fitnessChallengeSessionManager = FitnessChallengeSessionManager.getInstance(FitnessChallengeSyncViewModel.this.getContext());
                        Calendar calendar = Calendar.getInstance();
                        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                        fitnessChallengeSessionManager.saveFitnessChallengeLastSyncDate(ExtensionsKt.toFormattedDateStr(calendar, "yyyy-MM-dd"));
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
