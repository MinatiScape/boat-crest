package com.coveiot.android.activitymodes.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.activitymodes.feedback.OptionModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieReponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelWorkoutHistory extends AndroidViewModel {
    @NotNull
    public final String d;
    @NotNull
    public Context e;
    @Nullable
    public LiveData<List<EntityWorkoutSession>> f;
    @Nullable
    public LiveData<List<EntityWorkoutSession>> g;
    @NotNull
    public MutableLiveData<FeedbackQuestionnarieModel> h;
    @NotNull
    public MutableLiveData<Boolean> i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelWorkoutHistory(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "ViewModelWorkoutHistory";
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.e = application;
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getAllWorkoutSessionsFromDB(@NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return WorkoutSessionRepository.Companion.getInstance(this.e).getSessionsListLiveData(serialNo);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel, T] */
    public final void getFeedbackQuestionnarieList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new FeedbackQuestionnarieModel();
        FeedbackApiManager.getFeedbackQuestionnaireList("AUTO_RECOGNIZED_ACTIVITY", new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutHistory$getFeedbackQuestionnarieList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = ViewModelWorkoutHistory.this.d;
                Log.d(str, "feedbacklisterror: " + object.getMsg());
                ViewModelWorkoutHistory.this.getGetQuestionarieLiveData().postValue(objectRef.element);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = ViewModelWorkoutHistory.this.d;
                Log.d(str, "FeedbacklistResponse: " + new Gson().toJson(getFeedbackListResponse));
                Ref.ObjectRef<FeedbackQuestionnarieModel> objectRef2 = objectRef;
                GetFeedbackListResponse.Data data = getFeedbackListResponse.getData();
                if (data != null) {
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    objectRef2.element.setQuestionnaireId(data.getQuestionnaireId());
                    objectRef2.element.setCreatedDate(data.getCreatedDate());
                    objectRef2.element.setSubject(data.getSubject());
                    List<GetFeedbackListResponse.Data.Questionnaire> questionnaire = data.getQuestionnaire();
                    if (questionnaire != null) {
                        Intrinsics.checkNotNullExpressionValue(questionnaire, "questionnaire");
                        if (!questionnaire.isEmpty()) {
                            ArrayList arrayList = new ArrayList();
                            for (GetFeedbackListResponse.Data.Questionnaire questionnaire2 : questionnaire) {
                                QuestionModel questionModel = new QuestionModel();
                                questionModel.setQuestionId(questionnaire2.getQuestionId());
                                questionModel.setType(questionnaire2.getType());
                                GetFeedbackListResponse.Data.Questionnaire.Question question = questionnaire2.getQuestion();
                                if (question != null) {
                                    Intrinsics.checkNotNullExpressionValue(question, "question");
                                    questionModel.setText(question.getText());
                                    List<GetFeedbackListResponse.Data.Questionnaire.Question.Option> options = question.getOptions();
                                    if (options != null) {
                                        Intrinsics.checkNotNullExpressionValue(options, "options");
                                        if (!options.isEmpty()) {
                                            ArrayList arrayList2 = new ArrayList();
                                            for (GetFeedbackListResponse.Data.Questionnaire.Question.Option option : options) {
                                                OptionModel optionModel = new OptionModel();
                                                optionModel.setText(option.getText());
                                                optionModel.setOptionId(option.getOptionId());
                                                optionModel.setActiveIconUrl(option.getActiveIconUrl());
                                                optionModel.setInactiveIconUrl(option.getInactiveIconUrl());
                                                optionModel.setIconUrl(option.getIconUrl());
                                                arrayList2.add(optionModel);
                                            }
                                            questionModel.setOptions(arrayList2);
                                        }
                                    }
                                }
                                arrayList.add(questionModel);
                            }
                            objectRef2.element.setQuestions(arrayList);
                        }
                    }
                }
                ViewModelWorkoutHistory.this.getGetQuestionarieLiveData().postValue(objectRef.element);
            }
        });
    }

    @NotNull
    public final MutableLiveData<FeedbackQuestionnarieModel> getGetQuestionarieLiveData() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPostQuestionarieLiveData() {
        return this.i;
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getWorkoutSessionsFromDB(@NotNull String activityType) {
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        return WorkoutSessionRepository.Companion.getInstance(this.e).getSessionsListLiveDataFilterBy(activityType);
    }

    @Nullable
    public final LiveData<List<EntityWorkoutSession>> getWorkoutSessionsRun() {
        return this.f;
    }

    @Nullable
    public final LiveData<List<EntityWorkoutSession>> getWorkoutSessionsWalk() {
        return this.g;
    }

    public final void saveFeedbackAnswer(@NotNull EntityWorkoutSession entityWorkoutSession, @NotNull FeedbackQuestionnarieModel feedbackQuestionnarieModel) {
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        Intrinsics.checkNotNullParameter(feedbackQuestionnarieModel, "feedbackQuestionnarieModel");
        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = new SaveFeedbackQuestionarieRequest();
        saveFeedbackQuestionarieRequest.setClientRefId(entityWorkoutSession.getClient_ref_id());
        saveFeedbackQuestionarieRequest.setDate(entityWorkoutSession.getSession_date());
        saveFeedbackQuestionarieRequest.setQuestionnaireId(feedbackQuestionnarieModel.getQuestionnaireId());
        saveFeedbackQuestionarieRequest.setSubject(feedbackQuestionnarieModel.getSubject());
        ArrayList arrayList = new ArrayList();
        List<AnswerModel> answers = feedbackQuestionnarieModel.getAnswers();
        if (!(answers == null || answers.isEmpty())) {
            List<AnswerModel> answers2 = feedbackQuestionnarieModel.getAnswers();
            Intrinsics.checkNotNull(answers2);
            for (AnswerModel answerModel : answers2) {
                SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
                questionsAndAnswer.setQuestionId(answerModel.getQuestionId());
                questionsAndAnswer.setUserInput(answerModel.getUserInput());
                questionsAndAnswer.setAnswerIds(answerModel.getAnswerIds());
                arrayList.add(questionsAndAnswer);
            }
        }
        saveFeedbackQuestionarieRequest.setQuestionsAndAnswers(arrayList);
        FeedbackApiManager.saveFeedbackData(saveFeedbackQuestionarieRequest, new CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.viewmodels.ViewModelWorkoutHistory$saveFeedbackAnswer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelWorkoutHistory.this.getPostQuestionarieLiveData().postValue(Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFeedbackQuestionarieReponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ViewModelWorkoutHistory.this.getPostQuestionarieLiveData().postValue(Boolean.TRUE);
            }
        });
    }

    public final void setGetQuestionarieLiveData(@NotNull MutableLiveData<FeedbackQuestionnarieModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }

    public final void setPostQuestionarieLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setWorkoutSessionsRun(@Nullable LiveData<List<EntityWorkoutSession>> liveData) {
        this.f = liveData;
    }

    public final void setWorkoutSessionsWalk(@Nullable LiveData<List<EntityWorkoutSession>> liveData) {
        this.g = liveData;
    }

    @NotNull
    public final LiveData<List<EntityWorkoutSession>> getWorkoutSessionsFromDB(@NotNull String activityType, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(activityType, "activityType");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        return WorkoutSessionRepository.Companion.getInstance(this.e).getSessionsListLiveDataFilterBy(activityType, serialNo);
    }
}
