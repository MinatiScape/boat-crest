package com.coveiot.leaderboard.views.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class LeaderboardViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7269a;
    public final String b;
    @NotNull
    public MutableLiveData<TopRankModel> c;
    @NotNull
    public MutableLiveData<RankHistoryModel> d;
    @NotNull
    public MutableLiveData<MyRankModel> e;
    public SuccessResultListener mListener;

    public LeaderboardViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7269a = context;
        this.b = LeaderboardViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void getBuddiesRankListData(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.leaderboard.model.RankType r5, @org.jetbrains.annotations.Nullable com.coveiot.coveaccess.leaderboard.model.FilterType r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L8
            java.lang.String r1 = r5.getRankType()
            goto L9
        L8:
            r1 = r0
        L9:
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L16
            int r1 = r1.length()
            if (r1 != 0) goto L14
            goto L16
        L14:
            r1 = r2
            goto L17
        L16:
            r1 = r3
        L17:
            if (r1 == 0) goto L2f
            if (r6 == 0) goto L20
            java.lang.String r1 = r6.getFilterType()
            goto L21
        L20:
            r1 = r0
        L21:
            if (r1 == 0) goto L29
            int r1 = r1.length()
            if (r1 != 0) goto L2a
        L29:
            r2 = r3
        L2a:
            if (r2 != 0) goto L2d
            goto L2f
        L2d:
            r1 = r0
            goto L31
        L2f:
            java.lang.String r1 = "LOCATION"
        L31:
            if (r6 == 0) goto L38
            java.lang.String r6 = r6.getFilterType()
            goto L39
        L38:
            r6 = r0
        L39:
            if (r5 == 0) goto L40
            java.lang.String r5 = r5.getRankType()
            goto L41
        L40:
            r5 = r0
        L41:
            com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel$getBuddiesRankListData$1 r2 = new com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel$getBuddiesRankListData$1
            r2.<init>()
            com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi.getBuddiesTopRank(r1, r6, r5, r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel.getBuddiesRankListData(com.coveiot.coveaccess.leaderboard.model.RankType, com.coveiot.coveaccess.leaderboard.model.FilterType):void");
    }

    @NotNull
    public final Context getContext() {
        return this.f7269a;
    }

    public final void getDailyRankingHistory(@NotNull RankType rankType, @NotNull FilterType filter) {
        Intrinsics.checkNotNullParameter(rankType, "rankType");
        Intrinsics.checkNotNullParameter(filter, "filter");
        CoveLeaderboardApi.getRankHistory(rankType, filter, new CoveApiListener<RankHistoryModel, CoveApiErrorModel>() { // from class: com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel$getDailyRankingHistory$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LeaderboardViewModel.this.getMListener().onError("No Data Found");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable RankHistoryModel rankHistoryModel) {
                if (rankHistoryModel != null) {
                    if (rankHistoryModel.getData().getRanks() != null && rankHistoryModel.getData().getRanks().size() > 0) {
                        LeaderboardViewModel.this.getGetFilteredRankList().postValue(rankHistoryModel);
                    } else {
                        LeaderboardViewModel.this.getMListener().onError("No Data Found");
                    }
                }
            }
        });
    }

    @NotNull
    public final MutableLiveData<RankHistoryModel> getGetFilteredRankList() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<TopRankModel> getGetToppersRankList() {
        return this.c;
    }

    public final void getGlobalRankListData(@NotNull String rankId) {
        Intrinsics.checkNotNullParameter(rankId, "rankId");
        CoveLeaderboardApi.getTopRank(rankId, new CoveApiListener<TopRankModel, CoveApiErrorModel>() { // from class: com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel$getGlobalRankListData$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LeaderboardViewModel.this.getMListener().onError("No Data Found");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable TopRankModel topRankModel) {
                if (LeaderboardViewModel.this.getContext() != null && topRankModel != null && topRankModel.getData() != null) {
                    LeaderboardViewModel.this.getGetToppersRankList().postValue(topRankModel);
                } else {
                    LeaderboardViewModel.this.getMListener().onError("No Data Found");
                }
            }
        });
    }

    @NotNull
    public final SuccessResultListener getMListener() {
        SuccessResultListener successResultListener = this.mListener;
        if (successResultListener != null) {
            return successResultListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final void getMyRank(final int i, final boolean z) {
        CoveLeaderboardApi.getMyRank(GeoCodingCriteria.POD_CITY, new CoveApiListener<MyRankModel, CoveApiErrorModel>() { // from class: com.coveiot.leaderboard.views.viewmodel.LeaderboardViewModel$getMyRank$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LeaderboardViewModel.this.getMListener().onError("No Data Found");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable MyRankModel myRankModel) {
                MyRankModel.DataBean.RankBean rank;
                if (LeaderboardViewModel.this.getContext() == null || myRankModel == null || myRankModel.getData() == null) {
                    return;
                }
                LeaderboardViewModel.this.getMyRankDetails().postValue(myRankModel);
                MyRankModel.DataBean data = myRankModel.getData();
                if (((data == null || (rank = data.getRank()) == null) ? null : Integer.valueOf(rank.getId())) != null) {
                    if (i != 1 || z) {
                        return;
                    }
                    LeaderboardViewModel.this.getGlobalRankListData(String.valueOf(myRankModel.getData().getRank().getId()));
                    return;
                }
                LeaderboardViewModel.this.getMListener().onError("No Data Found");
            }
        });
    }

    @NotNull
    public final MutableLiveData<MyRankModel> getMyRankDetails() {
        return this.e;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void setGetFilteredRankList(@NotNull MutableLiveData<RankHistoryModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setGetToppersRankList(@NotNull MutableLiveData<TopRankModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMListener(@NotNull SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(successResultListener, "<set-?>");
        this.mListener = successResultListener;
    }

    public final void setMyRankDetails(@NotNull MutableLiveData<MyRankModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }
}
