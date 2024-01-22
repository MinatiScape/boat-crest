package com.coveiot.android.fitnesschallenges.viewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.fitnessbuddies.utils.CoveUtils;
import com.coveiot.android.fitnesschallenges.R;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesschallenge.CoveFitnessChallengeApi;
import com.coveiot.coveaccess.fitnesschallenge.model.AddParticipantsReq;
import com.coveiot.coveaccess.fitnesschallenge.model.BannerImagesRes;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeReq;
import com.coveiot.coveaccess.fitnesschallenge.model.CreateFitnessChallengeRes;
import com.coveiot.coveaccess.fitnesschallenge.model.RemoveParticipantsReq;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CreateChallengeViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4537a;
    @Nullable
    public SuccessResultListener b;
    @Nullable
    public Calendar c;
    @NotNull
    public MutableLiveData<String> d;
    @NotNull
    public MutableLiveData<String> e;
    @Nullable
    public Object f;
    @NotNull
    public MutableLiveData<List<BannerImagesRes.Item>> g;
    public Calendar mStartDate;

    public CreateChallengeViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4537a = context;
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
    }

    public static /* synthetic */ void setEndDate$default(CreateChallengeViewModel createChallengeViewModel, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        createChallengeViewModel.setEndDate(calendar, z);
    }

    public static /* synthetic */ void setStartDate$default(CreateChallengeViewModel createChallengeViewModel, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        createChallengeViewModel.setStartDate(calendar, z);
    }

    public final void addParticipantFitnessChallenge(@NotNull AddParticipantsReq addParticipantsReq) {
        Intrinsics.checkNotNullParameter(addParticipantsReq, "addParticipantsReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4537a)) {
            CoveFitnessChallengeApi.addParticipantsForChallenge(addParticipantsReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$addParticipantFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(CreateChallengeViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4537a.getString(R.string.no_internet_connection));
        }
    }

    public final void createFitnessChallenge(@NotNull CreateFitnessChallengeReq createFitnessChallengeReq) {
        Intrinsics.checkNotNullParameter(createFitnessChallengeReq, "createFitnessChallengeReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4537a)) {
            CoveFitnessChallengeApi.createNewBuddiesChallenge(createFitnessChallengeReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$createFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    CreateChallengeViewModel createChallengeViewModel = CreateChallengeViewModel.this;
                    Intrinsics.checkNotNull(createFitnessChallengeRes);
                    createChallengeViewModel.setChallengeId(createFitnessChallengeRes.getChallengeId());
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                    CreateChallengeViewModel.this.getFitnessChallengeActiveDateRange();
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4537a.getString(R.string.no_internet_connection));
        }
    }

    public final void editFitnessChallenge(@NotNull String challengeId, @NotNull CreateFitnessChallengeReq createFitnessChallengeReq) {
        Intrinsics.checkNotNullParameter(challengeId, "challengeId");
        Intrinsics.checkNotNullParameter(createFitnessChallengeReq, "createFitnessChallengeReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4537a)) {
            CoveFitnessChallengeApi.editBuddyFitnessChallenge(challengeId, createFitnessChallengeReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$editFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4537a.getString(R.string.no_internet_connection));
        }
    }

    @Nullable
    public final Object getChallengeId() {
        return this.f;
    }

    @NotNull
    public final Context getContext() {
        return this.f4537a;
    }

    @NotNull
    public final MutableLiveData<List<BannerImagesRes.Item>> getCreateChallengeBannerList() {
        return this.g;
    }

    public final void getCreateChallengeBanners() {
        if (CoveUtils.INSTANCE.isNetConnected(this.f4537a)) {
            CoveFitnessChallengeApi.getBannersForChallengeCreation(new CoveApiListener<List<? extends BannerImagesRes.Item>, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$getCreateChallengeBanners$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.d("challengeCheck", "onError_getBannersForChallengeCreation:" + new Gson().toJson(coveApiErrorModel));
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(CreateChallengeViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull List<? extends BannerImagesRes.Item> bannerImagesResList) {
                    Intrinsics.checkNotNullParameter(bannerImagesResList, "bannerImagesResList");
                    LogHelper.d("challengeCheck", "getBannersForChallengeCreation:" + new Gson().toJson(bannerImagesResList));
                    CreateChallengeViewModel.this.getCreateChallengeBannerList().postValue(bannerImagesResList);
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4537a.getString(R.string.no_internet_connection));
        }
    }

    @NotNull
    public final MutableLiveData<String> getEndDateLiveData() {
        return this.e;
    }

    public final void getFitnessChallengeActiveDateRange() {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new CreateChallengeViewModel$getFitnessChallengeActiveDateRange$1(this, null), 2, null);
    }

    @Nullable
    public final Calendar getMEndDate() {
        return this.c;
    }

    @Nullable
    public final SuccessResultListener getMListener() {
        return this.b;
    }

    @NotNull
    public final Calendar getMStartDate() {
        Calendar calendar = this.mStartDate;
        if (calendar != null) {
            return calendar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mStartDate");
        return null;
    }

    @NotNull
    public final MutableLiveData<String> getStartDateLiveData() {
        return this.d;
    }

    public final void removeParticipantFitnessChallenge(@NotNull RemoveParticipantsReq removeParticipantsReq) {
        Intrinsics.checkNotNullParameter(removeParticipantsReq, "removeParticipantsReq");
        if (CoveUtils.INSTANCE.isNetConnected(this.f4537a)) {
            CoveFitnessChallengeApi.removeParticipantsFromChallenge(removeParticipantsReq, new CoveApiListener<CreateFitnessChallengeRes, CoveApiErrorModel>() { // from class: com.coveiot.android.fitnesschallenges.viewModel.CreateChallengeViewModel$removeParticipantFitnessChallenge$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(CreateChallengeViewModel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CreateFitnessChallengeRes createFitnessChallengeRes) {
                    SuccessResultListener mListener = CreateChallengeViewModel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.b;
        if (successResultListener != null) {
            successResultListener.onError(this.f4537a.getString(R.string.no_internet_connection));
        }
    }

    public final void setChallengeId(@Nullable Object obj) {
        this.f = obj;
    }

    public final void setCreateChallengeBannerList(@NotNull MutableLiveData<List<BannerImagesRes.Item>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setEndDate(@NotNull Calendar endDate, boolean z) {
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        this.c = endDate;
        this.e.setValue(AppUtils.formatDate(endDate != null ? endDate.getTime() : null, "dd/MM/yyyy"));
    }

    public final void setEndDateLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setMEndDate(@Nullable Calendar calendar) {
        this.c = calendar;
    }

    public final void setMListener(@Nullable SuccessResultListener successResultListener) {
        this.b = successResultListener;
    }

    public final void setMStartDate(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "<set-?>");
        this.mStartDate = calendar;
    }

    public final void setStartDate(@NotNull Calendar startDate, boolean z) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        setMStartDate(startDate);
        this.d.setValue(AppUtils.formatDate(getMStartDate().getTime(), "dd/MM/yyyy"));
    }

    public final void setStartDateLiveData(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }
}
