package com.coveiot.android.fitnesschallenges.viewModel;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource;
import com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource;
import com.coveiot.android.fitnesschallenges.repository.FitnessChallengeRepository;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
/* loaded from: classes2.dex */
public final class FitnessChallengeListViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4551a;
    @NotNull
    public MutableLiveData<PagingData<BuddiesChallengeRes.Item>> b;
    @NotNull
    public MutableLiveData<PagingData<BuddiesChallengeRes.Item>> c;
    @NotNull
    public MutableLiveData<PagingData<BuddiesChallengeRes.Item>> d;
    @NotNull
    public MutableLiveData<PagingData<BuddiesChallengeRes.Item>> e;
    @NotNull
    public MutableLiveData<PagingData<BuddiesChallengeRes.Item>> f;
    @NotNull
    public MutableLiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> g;
    @NotNull
    public MutableLiveData<GetAllParticipantsFitnessChallengeRes> h;
    @NotNull
    public MutableLiveData<BuddiesChallengeRes> i;
    @NotNull
    public MutableLiveData<Boolean> j;
    @NotNull
    public MutableLiveData<Boolean> k;
    @NotNull
    public MutableLiveData<Boolean> l;

    /* loaded from: classes2.dex */
    public interface FileDownload {
        void downloadFailure(@NotNull String str);

        void downloadedSuccessfully(@NotNull Response<ResponseBody> response);
    }

    public FitnessChallengeListViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4551a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        Boolean bool = Boolean.TRUE;
        this.j = new MutableLiveData<>(bool);
        this.k = new MutableLiveData<>(bool);
        this.l = new MutableLiveData<>(bool);
    }

    public final void downloadLeaderboardReport(@NotNull Object challengeId, @NotNull final FileDownload listener) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(listener, "listener");
        CoveFitnessChallengeApi.downloadFCLeaderboardReport(challengeId, new CoveApiListener<Response<ResponseBody>, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$downloadLeaderboardReport$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                FitnessChallengeListViewModel.FileDownload fileDownload = FitnessChallengeListViewModel.FileDownload.this;
                String string = this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                fileDownload.downloadFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull Response<ResponseBody> p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                FitnessChallengeListViewModel.FileDownload.this.downloadedSuccessfully(p0);
            }
        });
    }

    @NotNull
    public final MutableLiveData<PagingData<BuddiesChallengeRes.Item>> getBuddiesFitnessChallengeList() {
        return this.c;
    }

    public final void getBuddyFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), FitnessChallengeConstants.BUDDY, 0).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getBuddyFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.c = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final Context getContext() {
        return this.f4551a;
    }

    public final void getDashboardFirstTwoChallenges() {
        if (CoveUtils.INSTANCE.isNetConnected(this.f4551a)) {
            CoveFitnessChallengeApi.getDashboardChallenges(new CoveApiListener<List<? extends BuddiesChallengeRes.Item>, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getDashboardFirstTwoChallenges$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.TRUE);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull List<? extends BuddiesChallengeRes.Item> p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    if (!p0.isEmpty()) {
                        BuddiesChallengeRes buddiesChallengeRes = new BuddiesChallengeRes();
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(p0);
                        buddiesChallengeRes.setItems(arrayList);
                        FitnessChallengeListViewModel.this.getGetBuddiesChallengeListLiveData().postValue(buddiesChallengeRes);
                        return;
                    }
                    FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.TRUE);
                }
            });
        } else {
            this.j.postValue(Boolean.TRUE);
        }
    }

    @NotNull
    public final MutableLiveData<PagingData<BuddiesChallengeRes.Item>> getFitnessChallengeList() {
        return this.b;
    }

    public final void getFitnessChallengesLeaderboardRankingPagingData(@NotNull Object challengeId, @NotNull String challengeType, @NotNull String participantType) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        Intrinsics.checkNotNullParameter(participantType, "participantType");
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), challengeType, 0).getLeaderboardRankingFitnessChallenges(challengeId, participantType, new LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getFitnessChallengesLeaderboardRankingPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener
            public void getAllParticipantRes(@NotNull GetAllParticipantsFitnessChallengeRes getAllParticipantsFitnessChallengeRes) {
                Intrinsics.checkNotNullParameter(getAllParticipantsFitnessChallengeRes, "getAllParticipantsFitnessChallengeRes");
                FitnessChallengeListViewModel.this.getUserDetailMutableLiveData().postValue(getAllParticipantsFitnessChallengeRes);
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.LeaderboardRankingFitnessChallengePagingSource.ParticipantsListener
            public void shouldShowProgress(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>>");
        this.g = (MutableLiveData) cachedIn;
    }

    public final void getFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), "", 2).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.b = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final MutableLiveData<BuddiesChallengeRes> getGetBuddiesChallengeListLiveData() {
        return this.i;
    }

    public final void getGlobalFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), FitnessChallengeConstants.GLOBAL, 0).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getGlobalFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.b = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final MutableLiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> getLeaderboardRankingFitnessChallengeList() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<PagingData<BuddiesChallengeRes.Item>> getMyCompletedFitnessChallengeList() {
        return this.f;
    }

    public final void getMyCompletedFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), FitnessChallengeConstants.COMPLETED, 1).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getMyCompletedFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.f = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final MutableLiveData<PagingData<BuddiesChallengeRes.Item>> getMyCreatedFitnessChallengeList() {
        return this.d;
    }

    public final void getMyCreatedFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), "CREATED", 1).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getMyCreatedFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.d = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final MutableLiveData<PagingData<BuddiesChallengeRes.Item>> getMyJoinedFitnessChallengeList() {
        return this.e;
    }

    public final void getMyJoinedFitnessChallengesPagingData() {
        LiveData cachedIn = PagingLiveData.cachedIn(new FitnessChallengeRepository(ViewModelKt.getViewModelScope(this), FitnessChallengeConstants.JOINED, 1).getFitnessChallenges(new FitnessChallengePagingSource.FitnessChallengeListListener() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeListViewModel$getMyJoinedFitnessChallengesPagingData$1
            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowEmptyLayout(boolean z) {
                FitnessChallengeListViewModel.this.getShowEmptyLayout().postValue(Boolean.valueOf(z));
            }

            @Override // com.coveiot.android.fitnesschallenges.paging.FitnessChallengePagingSource.FitnessChallengeListListener
            public void shouldShowProgressBar(boolean z) {
                FitnessChallengeListViewModel.this.getShouldShowProgress().postValue(Boolean.valueOf(z));
            }
        }), ViewModelKt.getViewModelScope(this));
        Intrinsics.checkNotNull(cachedIn, "null cannot be cast to non-null type androidx.lifecycle.MutableLiveData<androidx.paging.PagingData<com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeRes.Item>>");
        this.e = (MutableLiveData) cachedIn;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShouldShowProgress() {
        return this.l;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShowEmptyLayout() {
        return this.j;
    }

    @NotNull
    public final MutableLiveData<Boolean> getShowEmptyLayoutParticipants() {
        return this.k;
    }

    @NotNull
    public final MutableLiveData<GetAllParticipantsFitnessChallengeRes> getUserDetailMutableLiveData() {
        return this.h;
    }

    public final void setBuddiesFitnessChallengeList(@NotNull MutableLiveData<PagingData<BuddiesChallengeRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setFitnessChallengeList(@NotNull MutableLiveData<PagingData<BuddiesChallengeRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setGetBuddiesChallengeListLiveData(@NotNull MutableLiveData<BuddiesChallengeRes> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setLeaderboardRankingFitnessChallengeList(@NotNull MutableLiveData<PagingData<GetAllParticipantsFitnessChallengeRes.ParticipantsDetails>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setMyCompletedFitnessChallengeList(@NotNull MutableLiveData<PagingData<BuddiesChallengeRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setMyCreatedFitnessChallengeList(@NotNull MutableLiveData<PagingData<BuddiesChallengeRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setMyJoinedFitnessChallengeList(@NotNull MutableLiveData<PagingData<BuddiesChallengeRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setShouldShowProgress(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.l = mutableLiveData;
    }

    public final void setShowEmptyLayout(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.j = mutableLiveData;
    }

    public final void setShowEmptyLayoutParticipants(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.k = mutableLiveData;
    }

    public final void setUserDetailMutableLiveData(@NotNull MutableLiveData<GetAllParticipantsFitnessChallengeRes> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }
}
