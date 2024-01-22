package com.coveiot.android.fitnesschallenges.paging;

import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource$load$2", f = "LeaderboardRankingFitnessChallengePagingSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class LeaderboardRankingFitnessChallengePagingSource$load$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $position;
    public final /* synthetic */ CompletableDeferred<GetAllParticipantsFitnessChallengeRes> $response;
    public int label;
    public final /* synthetic */ LeaderboardRankingFitnessChallengePagingSource this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LeaderboardRankingFitnessChallengePagingSource$load$2(int i, LeaderboardRankingFitnessChallengePagingSource leaderboardRankingFitnessChallengePagingSource, CompletableDeferred<GetAllParticipantsFitnessChallengeRes> completableDeferred, Continuation<? super LeaderboardRankingFitnessChallengePagingSource$load$2> continuation) {
        super(2, continuation);
        this.$position = i;
        this.this$0 = leaderboardRankingFitnessChallengePagingSource;
        this.$response = completableDeferred;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new LeaderboardRankingFitnessChallengePagingSource$load$2(this.$position, this.this$0, this.$response, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((LeaderboardRankingFitnessChallengePagingSource$load$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$position == 0) {
                this.this$0.getListener().shouldShowProgress(true);
            }
            Object challengeId = this.this$0.getChallengeId();
            String type = this.this$0.getType();
            String participantType = this.this$0.getParticipantType();
            int i = this.$position;
            final LeaderboardRankingFitnessChallengePagingSource leaderboardRankingFitnessChallengePagingSource = this.this$0;
            final CompletableDeferred<GetAllParticipantsFitnessChallengeRes> completableDeferred = this.$response;
            CoveFitnessChallengeApi.getAllFitnessChallengeParticipants(challengeId, type, participantType, i, 10, new CoveApiListener<GetAllParticipantsFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource$load$2.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LeaderboardRankingFitnessChallengePagingSource.this.getListener().shouldShowProgress(false);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes) {
                    if (getAllParticipantsFitnessChallengeRes != null) {
                        LeaderboardRankingFitnessChallengePagingSource.this.getListener().getAllParticipantRes(getAllParticipantsFitnessChallengeRes);
                        List<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> participants = getAllParticipantsFitnessChallengeRes.getParticipants();
                        if (!(participants == null || participants.isEmpty())) {
                            completableDeferred.complete(getAllParticipantsFitnessChallengeRes);
                        }
                    }
                    LeaderboardRankingFitnessChallengePagingSource.this.getListener().shouldShowProgress(false);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
