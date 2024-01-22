package com.coveiot.android.leonardo.more.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.leonardo.more.models.RatingInfo;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FeedbackType;
import com.coveiot.coveaccess.model.server.SPostRatingReq;
import com.coveiot.coveaccess.model.server.SPostRatingRes;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class RateUsViewModel extends AndroidViewModel {
    @NotNull
    public MutableLiveData<ArrayList<RatingInfo>> d;

    /* loaded from: classes5.dex */
    public interface ApiResultListener {
        void showFailureToast();

        void showFeedbackDialog();

        void showFeedbackSubmitSuccess();

        void showPlayStoreReviewDialog();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RateUsViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        MutableLiveData<ArrayList<RatingInfo>> mutableLiveData = new MutableLiveData<>();
        this.d = mutableLiveData;
        mutableLiveData.setValue(new ArrayList<>());
    }

    public static /* synthetic */ void saveRatingToServer$default(RateUsViewModel rateUsViewModel, RatingInfo ratingInfo, ApiResultListener apiResultListener, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        rateUsViewModel.saveRatingToServer(ratingInfo, apiResultListener, str);
    }

    @NotNull
    public final MutableLiveData<ArrayList<RatingInfo>> getRatingInfo() {
        return this.d;
    }

    public final void h() {
        ArrayList<RatingInfo> value = this.d.getValue();
        if (value != null) {
            value.add(new RatingInfo(1, "Very bad"));
        }
        ArrayList<RatingInfo> value2 = this.d.getValue();
        if (value2 != null) {
            value2.add(new RatingInfo(2, "Bad"));
        }
        ArrayList<RatingInfo> value3 = this.d.getValue();
        if (value3 != null) {
            value3.add(new RatingInfo(3, "Average"));
        }
        ArrayList<RatingInfo> value4 = this.d.getValue();
        if (value4 != null) {
            value4.add(new RatingInfo(4, "Good"));
        }
        ArrayList<RatingInfo> value5 = this.d.getValue();
        if (value5 != null) {
            value5.add(new RatingInfo(5, "Excellent"));
        }
    }

    public final void loadRatingInfoFromServer() {
        if (AppUtils.isNetConnected(getApplication())) {
            FeedbackApiManager.getFeedbackQuestionnaireList(FeedbackType.APP_RATING.name(), new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel$loadRatingInfoFromServer$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    RateUsViewModel.this.h();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetFeedbackListResponse getFeedbackListResponse) {
                    List<GetFeedbackListResponse.Data.Questionnaire> questionnaire;
                    GetFeedbackListResponse.Data.Questionnaire questionnaire2;
                    GetFeedbackListResponse.Data.Questionnaire.Question question;
                    if (getFeedbackListResponse == null) {
                        RateUsViewModel.this.h();
                        return;
                    }
                    GetFeedbackListResponse.Data data = getFeedbackListResponse.getData();
                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = (data == null || (questionnaire = data.getQuestionnaire()) == null || (questionnaire2 = questionnaire.get(0)) == null || (question = questionnaire2.getQuestion()) == null) ? null : question.getOptions();
                    if (AppUtils.isEmpty(options)) {
                        return;
                    }
                    Intrinsics.checkNotNull(options);
                    int i = 1;
                    for (GetFeedbackListResponse.Data.Questionnaire.Question.Option option : options) {
                        ArrayList<RatingInfo> value = RateUsViewModel.this.getRatingInfo().getValue();
                        if (value != null) {
                            String text = option.getText();
                            Intrinsics.checkNotNullExpressionValue(text, "option.text");
                            value.add(new RatingInfo(i, text));
                            i++;
                        }
                    }
                    RateUsViewModel.this.getRatingInfo().postValue(RateUsViewModel.this.getRatingInfo().getValue());
                }
            });
        } else {
            h();
        }
    }

    public final void saveRatingToServer(@Nullable RatingInfo ratingInfo, @NotNull final ApiResultListener listener, @Nullable final String str) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        SPostRatingReq sPostRatingReq = new SPostRatingReq();
        sPostRatingReq.setRating(ratingInfo != null ? Integer.valueOf(ratingInfo.getRating()) : null);
        if (str != null) {
            sPostRatingReq.setComments(str);
        }
        FeedbackApiManager.postUserRatingAndFeedBack(sPostRatingReq, new CoveApiListener<SPostRatingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.RateUsViewModel$saveRatingToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.e(RateUsViewModel.class.getSimpleName(), String.valueOf(coveApiErrorModel));
                listener.showFailureToast();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SPostRatingRes sPostRatingRes) {
                if (sPostRatingRes != null) {
                    if (str != null) {
                        listener.showFeedbackSubmitSuccess();
                        return;
                    }
                    Boolean positiveRating = sPostRatingRes.getData().getPositiveRating();
                    Intrinsics.checkNotNullExpressionValue(positiveRating, "p0.data.positiveRating");
                    if (positiveRating.booleanValue()) {
                        listener.showPlayStoreReviewDialog();
                        return;
                    } else {
                        listener.showFeedbackDialog();
                        return;
                    }
                }
                LogHelper.e(RateUsViewModel.class.getSimpleName(), "Response null");
                listener.showFailureToast();
            }
        });
    }

    public final void setRatingInfo(@NotNull MutableLiveData<ArrayList<RatingInfo>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }
}
