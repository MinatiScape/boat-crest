package com.coveiot.android.fitnesschallenges.viewModel;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.JoinChallengeReq;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeDetailsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4544a;
    @Nullable
    public SuccessResultListener b;
    @NotNull
    public MutableLiveData<BuddiesChallengeDetail> c;

    public FitnessChallengeDetailsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4544a = context;
        this.c = new MutableLiveData<>();
    }

    public final void deleteCreatedChallenge(@NotNull String challengeId) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4544a)) {
            CoveFitnessChallengeApi.deleteBuddyFitnessChallenge(challengeId, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel$deleteCreatedChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(FitnessChallengeDetailsViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    FitnessChallengeDetailsViewModel.this.getFitnessChallengeActiveDateRange();
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4544a.getString(R.string.no_internet_connection));
        }
    }

    public final void getBuddyChallengeDetails(@NotNull String challengeId) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4544a)) {
            CoveFitnessChallengeApi.getDetailsOfBuddyChallenge(challengeId, new CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel$getBuddyChallengeDetails$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    Log.d("challengeDetails", "onError_getChallengeDetails:" + new Gson().toJson(coveApiErrorModel));
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(FitnessChallengeDetailsViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull BuddiesChallengeDetail challengeDetails) {
                    Intrinsics.checkNotNullParameter(challengeDetails, "challengeDetails");
                    Log.d("challengeDetails", "get challenge details:" + new Gson().toJson(challengeDetails));
                    FitnessChallengeDetailsViewModel.this.getChallengeDetailsData().postValue(challengeDetails);
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4544a.getString(R.string.no_internet_connection));
        }
    }

    @NotNull
    public final MutableLiveData<BuddiesChallengeDetail> getChallengeDetailsData() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f4544a;
    }

    public final void getFitnessChallengeActiveDateRange() {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new FitnessChallengeDetailsViewModel$getFitnessChallengeActiveDateRange$1(this, null), 2, null);
    }

    public final void getGlobalChallengeDetails(@NotNull String challengeId) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4544a)) {
            CoveFitnessChallengeApi.getDetailsOfGlobalChallenge(challengeId, new CoveApiListener<BuddiesChallengeDetail, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel$getGlobalChallengeDetails$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    Log.d("challengeDetails", "onError_getChallengeDetails:" + new Gson().toJson(coveApiErrorModel));
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(FitnessChallengeDetailsViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull BuddiesChallengeDetail challengeDetails) {
                    Intrinsics.checkNotNullParameter(challengeDetails, "challengeDetails");
                    LogHelper.d("challengeDetails", "get challenge details:" + new Gson().toJson(challengeDetails));
                    FitnessChallengeDetailsViewModel.this.getChallengeDetailsData().postValue(challengeDetails);
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4544a.getString(R.string.no_internet_connection));
        }
    }

    @Nullable
    public final SuccessResultListener getMListener() {
        return this.b;
    }

    public final void joinFitnessChallenge(@NotNull JoinChallengeReq joinChallengeReq) {
        Intrinsics.checkNotNullParameter(joinChallengeReq, "joinChallengeReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4544a)) {
            CoveFitnessChallengeApi.joinFitnessChallenge(joinChallengeReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel$joinFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(FitnessChallengeDetailsViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4544a.getString(R.string.no_internet_connection));
        }
    }

    public final void leaveFitnessChallenge(@NotNull JoinChallengeReq leaveJoinChallengeReq) {
        Intrinsics.checkNotNullParameter(leaveJoinChallengeReq, "leaveJoinChallengeReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4544a)) {
            CoveFitnessChallengeApi.leaveFitnessChallenge(leaveJoinChallengeReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.FitnessChallengeDetailsViewModel$leaveFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(FitnessChallengeDetailsViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    SuccessResultListener mListener = FitnessChallengeDetailsViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4544a.getString(R.string.no_internet_connection));
        }
    }

    public final void setChallengeDetailsData(@NotNull MutableLiveData<BuddiesChallengeDetail> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMListener(@Nullable SuccessResultListener successResultListener) {
        this.b = successResultListener;
    }
}
