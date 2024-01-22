package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.boatcoins.BoatCoinsApiManager;
import com.coveiot.coveaccess.boatcoins.model.BoatCoinsDetailsResponse;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityProfileLandingViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5155a;
    public final String b;
    @NotNull
    public MutableLiveData<Integer> c;
    @NotNull
    public MutableLiveData<MyRankModel.DataBean.RankBean> d;
    @NotNull
    public MutableLiveData<BoatCoinsDetailsResponse> e;
    public ViewModelListener mListener;

    public ActivityProfileLandingViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5155a = context;
        this.b = ActivityProfileLandingViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
    }

    public final void deleteUserSession() {
        CoveOnboarding.deleteSession(new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel$deleteUserSession$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                LogHelper.d("deleteUserSession", "onError: " + coveApiErrorModel.getMsg());
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull CommonResponseClass commonResponseClass) {
                Intrinsics.checkNotNullParameter(commonResponseClass, "commonResponseClass");
                LogHelper.d("deleteUserSession", "onSuccess: " + commonResponseClass.getMessage());
            }
        });
    }

    public final void getBoatCoinsDetails() {
        BoatCoinsApiManager.getBoatCoinsDetails(new CoveApiListener<BoatCoinsDetailsResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel$getBoatCoinsDetails$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull BoatCoinsDetailsResponse boatCoinsDetailsResponse) {
                Intrinsics.checkNotNullParameter(boatCoinsDetailsResponse, "boatCoinsDetailsResponse");
                ActivityProfileLandingViewModel.this.getGetBoatCoinsDetails().postValue(boatCoinsDetailsResponse);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5155a;
    }

    @NotNull
    public final MutableLiveData<MyRankModel.DataBean.RankBean> getGetBestRankBean() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<BoatCoinsDetailsResponse> getGetBoatCoinsDetails() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Integer> getGetMyBadgesCount() {
        return this.c;
    }

    @NotNull
    public final ViewModelListener getMListener() {
        ViewModelListener viewModelListener = this.mListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final void getMyBadges() {
        if (AppUtils.isNetConnected(this.f5155a)) {
            CoveLeaderboardApi.getMyBadges(new CoveApiListener<MyBadgesModel, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel$getMyBadges$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    ActivityProfileLandingViewModel.this.getGetMyBadgesCount().postValue(0);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable MyBadgesModel myBadgesModel) {
                    if (myBadgesModel != null) {
                        LeaderBoardDataUtiils.saveMyBadges(ActivityProfileLandingViewModel.this.getContext(), new Gson().toJson(myBadgesModel));
                        ActivityProfileLandingViewModel.this.getGetMyBadgesCount().postValue(Integer.valueOf(myBadgesModel.getData().getBadges().size()));
                    }
                }
            });
            return;
        }
        Context context = this.f5155a;
        Toast.makeText(context, context.getResources().getString(R.string.please_check_network), 0).show();
    }

    public final void getMyRankDetails() {
        CoveLeaderboardApi.getMyRank(GeoCodingCriteria.POD_CITY, new CoveApiListener<MyRankModel, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityProfileLandingViewModel$getMyRankDetails$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable MyRankModel myRankModel) {
                if (ActivityProfileLandingViewModel.this.getContext() == null || myRankModel == null || myRankModel.getData() == null || myRankModel.getData().getRank() == null) {
                    return;
                }
                LeaderBoardDataUtiils.saveMyRank(ActivityProfileLandingViewModel.this.getContext(), new Gson().toJson(myRankModel));
                ActivityProfileLandingViewModel.this.getGetBestRankBean().postValue(myRankModel.getData().getRank());
            }
        });
    }

    public final String getTAG() {
        return this.b;
    }

    public final void setGetBestRankBean(@NotNull MutableLiveData<MyRankModel.DataBean.RankBean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setGetBoatCoinsDetails(@NotNull MutableLiveData<BoatCoinsDetailsResponse> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setGetMyBadgesCount(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }
}
