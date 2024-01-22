package com.coveiot.android.fitnesschallenges.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.PagingSource;
import com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource;
import com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FitnessChallengeRepository {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final CoroutineScope f4532a;
    @NotNull
    public final String b;
    public final int c;

    public FitnessChallengeRepository(@NotNull CoroutineScope viewModelScope, @NotNull String type, int i) {
        Intrinsics.checkNotNullParameter(viewModelScope, "viewModelScope");
        Intrinsics.checkNotNullParameter(type, "type");
        this.f4532a = viewModelScope;
        this.b = type;
        this.c = i;
    }

    @NotNull
    public final LiveData<PagingData<BuddiesChallengeRes.Item>> getFitnessChallenges(@NotNull final FitnessChallengePagingSource.FitnessChallengeListListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        return PagingLiveData.getLiveData(new Pager(new PagingConfig(10, 0, false, 0, 100, 0, 46, null), null, new Function0<PagingSource<Integer, BuddiesChallengeRes.Item>>() { // from class: com.coveiot.android.fitnesschallenges.repository.FitnessChallengeRepository$getFitnessChallenges$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PagingSource<Integer, BuddiesChallengeRes.Item> invoke() {
                int i;
                CoroutineScope viewModelScope = FitnessChallengeRepository.this.getViewModelScope();
                String type = FitnessChallengeRepository.this.getType();
                i = FitnessChallengeRepository.this.c;
                final FitnessChallengePagingSource.FitnessChallengeListListener fitnessChallengeListListener = listener;
                return new FitnessChallengePagingSource(viewModelScope, type, i, new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.repository.FitnessChallengeRepository$getFitnessChallenges$1.1
                    @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
                    public void shouldShowEmptyLayout(boolean z) {
                        FitnessChallengePagingSource.FitnessChallengeListListener.this.shouldShowEmptyLayout(z);
                    }

                    @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
                    public void shouldShowProgressBar(boolean z) {
                        FitnessChallengePagingSource.FitnessChallengeListListener.this.shouldShowProgressBar(z);
                    }
                });
            }
        }, 2, null));
    }

    @NotNull
    public final LiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> getLeaderboardRankingFitnessChallenges(@NotNull final Object challengeId, @NotNull final String participantType, @NotNull final LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener listener) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(participantType, "participantType");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return PagingLiveData.getLiveData(new Pager(new PagingConfig(10, 0, false, 0, 100, 0, 46, null), null, new Function0<PagingSource<Integer, GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>>() { // from class: com.coveiot.android.fitnesschallenges.repository.FitnessChallengeRepository$getLeaderboardRankingFitnessChallenges$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PagingSource<Integer, GetAllParticipantsFitnessChallengeRes.ParticipantsDetails> invoke() {
                CoroutineScope viewModelScope = FitnessChallengeRepository.this.getViewModelScope();
                Object obj = challengeId;
                String type = FitnessChallengeRepository.this.getType();
                String str = participantType;
                final LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener participantsListener = listener;
                return new LeaderboardRankingFitnessChallengePagingSource(viewModelScope, obj, type, str, new LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener() { // from class: com.coveiot.android.fitnesschallenges.repository.FitnessChallengeRepository$getLeaderboardRankingFitnessChallenges$1.1
                    @Override // com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener
                    public void getAllParticipantRes(@NotNull GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes) {
                        Intrinsics.checkNotNullParameter(getAllParticipantsFitnessChallengeRes, "getAllParticipantsFitnessChallengeRes");
                        LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener.this.getAllParticipantRes(getAllParticipantsFitnessChallengeRes);
                    }

                    @Override // com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener
                    public void shouldShowProgress(boolean z) {
                        LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener.this.shouldShowProgress(z);
                    }
                });
            }
        }, 2, null));
    }

    @NotNull
    public final String getType() {
        return this.b;
    }

    @NotNull
    public final CoroutineScope getViewModelScope() {
        return this.f4532a;
    }
}
