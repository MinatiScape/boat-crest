package com.coveiot.android.activitymodes.workoutVideos.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.CachedPagingDataKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingSource;
import com.coveiot.android.activitymodes.feedback.AnswerModel;
import com.coveiot.android.activitymodes.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.activitymodes.feedback.OptionModel;
import com.coveiot.android.activitymodes.feedback.QuestionModel;
import com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutCategoriesBean;
import com.coveiot.android.activitymodes.workoutVideos.models.WorkoutVideosBean;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FeedbackType;
import com.coveiot.coveaccess.model.server.SWorkoutCategoryList;
import com.coveiot.coveaccess.model.server.SWorkoutVideosList;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieReponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.coveaccess.workoutvideos.WorkoutVideosManager;
import com.coveiot.coveaccess.workoutvideos.model.VideoType;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ViewModelWorkoutVideos extends AndroidViewModel {
    @NotNull
    public final String d;
    @NotNull
    public Context e;
    @Nullable
    public Flow<PagingData<WorkoutVideosBean>> f;
    @NotNull
    public MutableLiveData<FeedbackQuestionnarieModel> g;
    @NotNull
    public MutableLiveData<Boolean> h;
    @NotNull
    public MutableLiveData<List<WorkoutCategoriesBean>> i;
    @NotNull
    public final MutableLiveData<Boolean> j;
    @NotNull
    public final MutableLiveData<Integer> k;
    @NotNull
    public MutableLiveData<List<WorkoutVideosBean>> l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelWorkoutVideos(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = "ViewModelWorkoutVideos";
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
        this.j = new MutableLiveData<>(Boolean.FALSE);
        this.k = new MutableLiveData<>();
        this.l = new MutableLiveData<>();
        this.e = application;
    }

    public final Flow<PagingData<WorkoutVideosBean>> c(final String str) {
        LogHelper.d(this.d, "getPullWorkoutVideosResultStream");
        return new Pager(new PagingConfig(10, 0, false, 0, 0, 0, 58, null), null, new Function0<PagingSource<Integer, WorkoutVideosBean>>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getPullSensAICoachVideosResultStream$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PagingSource<Integer, WorkoutVideosBean> invoke() {
                Context context;
                context = ViewModelWorkoutVideos.this.e;
                String str2 = str;
                String videoType = VideoType.SENSE_AI_COACH.getVideoType();
                final ViewModelWorkoutVideos viewModelWorkoutVideos = ViewModelWorkoutVideos.this;
                return new WorkoutVideosPagingSource(context, str2, videoType, new WorkoutVideosPagingSource.ProgressListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getPullSensAICoachVideosResultStream$1.1
                    @Override // com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.ProgressListener
                    public void getTotalVideoCount(int i) {
                        ViewModelWorkoutVideos.this.getVideoTotalCountLiveData().postValue(Integer.valueOf(i));
                    }

                    @Override // com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.ProgressListener
                    public void shouldShowProgressBar(boolean z) {
                        ViewModelWorkoutVideos.this.getProgressLiveData().setValue(Boolean.valueOf(z));
                        ViewModelWorkoutVideos.this.getProgressLiveData().postValue(Boolean.valueOf(z));
                    }
                });
            }
        }, 2, null).getFlow();
    }

    public final Flow<PagingData<WorkoutVideosBean>> d(final String str) {
        LogHelper.d(this.d, "getPullWorkoutVideosResultStream");
        return new Pager(new PagingConfig(10, 0, false, 0, 0, 0, 58, null), null, new Function0<PagingSource<Integer, WorkoutVideosBean>>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getPullWorkoutVideosResultStream$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final PagingSource<Integer, WorkoutVideosBean> invoke() {
                Context context;
                context = ViewModelWorkoutVideos.this.e;
                String str2 = str;
                final ViewModelWorkoutVideos viewModelWorkoutVideos = ViewModelWorkoutVideos.this;
                return new WorkoutVideosPagingSource(context, str2, null, new WorkoutVideosPagingSource.ProgressListener() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getPullWorkoutVideosResultStream$1.1
                    @Override // com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.ProgressListener
                    public void getTotalVideoCount(int i) {
                        ViewModelWorkoutVideos.this.getVideoTotalCountLiveData().postValue(Integer.valueOf(i));
                    }

                    @Override // com.coveiot.android.activitymodes.workoutVideos.WorkoutVideosPagingSource.ProgressListener
                    public void shouldShowProgressBar(boolean z) {
                        ViewModelWorkoutVideos.this.getProgressLiveData().setValue(Boolean.valueOf(z));
                        ViewModelWorkoutVideos.this.getProgressLiveData().postValue(Boolean.valueOf(z));
                    }
                });
            }
        }, 2, null).getFlow();
    }

    public final void getFeedbackQuestionnaireList() {
        new ArrayList();
        final FeedbackQuestionnarieModel feedbackQuestionnarieModel = new FeedbackQuestionnarieModel();
        FeedbackApiManager.getFeedbackQuestionnaireList(FeedbackType.FITNESS_VIDEO.name(), new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getFeedbackQuestionnaireList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = ViewModelWorkoutVideos.this.d;
                LogHelper.d(str, "feedbacklisterror: " + object.getMsg());
                ViewModelWorkoutVideos.this.getGetQuestionarieLiveData().postValue(feedbackQuestionnarieModel);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = ViewModelWorkoutVideos.this.d;
                LogHelper.d(str, "FeedbacklistResponse: " + new Gson().toJson(getFeedbackListResponse));
                FeedbackQuestionnarieModel feedbackQuestionnarieModel2 = feedbackQuestionnarieModel;
                GetFeedbackListResponse.Data data = getFeedbackListResponse.getData();
                if (data != null) {
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    feedbackQuestionnarieModel2.setQuestionnaireId(data.getQuestionnaireId());
                    feedbackQuestionnarieModel2.setCreatedDate(data.getCreatedDate());
                    feedbackQuestionnarieModel2.setSubject(data.getSubject());
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
                            feedbackQuestionnarieModel2.setQuestions(arrayList);
                        }
                    }
                }
                ViewModelWorkoutVideos.this.getGetQuestionarieLiveData().postValue(feedbackQuestionnarieModel);
            }
        });
    }

    @NotNull
    public final MutableLiveData<List<WorkoutCategoriesBean>> getGetCategoriesLiveData() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<FeedbackQuestionnarieModel> getGetQuestionarieLiveData() {
        return this.g;
    }

    @NotNull
    public final MutableLiveData<List<WorkoutVideosBean>> getGetRecommenderCultFitVideosLiveData() {
        return this.l;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPostQuestionarieLiveData() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<Boolean> getProgressLiveData() {
        return this.j;
    }

    public final void getRecommendedWorkoutVideos() {
        WorkoutVideosManager.getVideoRecommendationsList(new CoveApiListener<SWorkoutVideosList, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getRecommendedWorkoutVideos$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWorkoutVideosList sWorkoutVideosList) {
                if (sWorkoutVideosList != null) {
                    ViewModelWorkoutVideos viewModelWorkoutVideos = ViewModelWorkoutVideos.this;
                    SWorkoutVideosList.DataBean data = sWorkoutVideosList.getData();
                    viewModelWorkoutVideos.getWorkoutVideosBeans(data != null ? data.getItems() : null);
                }
            }
        }, VideoType.CULT_FIT.getVideoType());
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getSensAICoachCategory() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        WorkoutVideosManager.getVideoCategoryList(new CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getSensAICoachCategory$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                this.getGetCategoriesLiveData().postValue(objectRef.element);
            }

            /* JADX WARN: Type inference failed for: r3v4, types: [java.util.List, T] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWorkoutCategoryList sWorkoutCategoryList) {
                if (sWorkoutCategoryList == null || sWorkoutCategoryList.getData() == null || sWorkoutCategoryList.getData().getItems() == null) {
                    return;
                }
                Ref.ObjectRef<List<WorkoutCategoriesBean>> objectRef2 = objectRef;
                ViewModelWorkoutVideos viewModelWorkoutVideos = this;
                SWorkoutCategoryList.DataBean data = sWorkoutCategoryList.getData();
                Intrinsics.checkNotNull(data);
                List<WorkoutCategoriesBean> workoutCategoriesBeans = viewModelWorkoutVideos.getWorkoutCategoriesBeans(data.getItems());
                Intrinsics.checkNotNull(workoutCategoriesBeans, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.activitymodes.workoutVideos.models.WorkoutCategoriesBean>");
                objectRef2.element = TypeIntrinsics.asMutableList(workoutCategoriesBeans);
                this.getGetCategoriesLiveData().postValue(objectRef.element);
            }
        }, VideoType.SENSE_AI_COACH.getVideoType());
    }

    @NotNull
    public final MutableLiveData<Integer> getVideoTotalCountLiveData() {
        return this.k;
    }

    @NotNull
    public final List<WorkoutCategoriesBean> getWorkoutCategoriesBeans(@Nullable List<? extends SWorkoutCategoryList.DataBean.ItemsBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty())) {
            arrayList.add(0, new WorkoutCategoriesBean(null, "All"));
            for (SWorkoutCategoryList.DataBean.ItemsBean itemsBean : list) {
                String categoryId = itemsBean.getCategoryId();
                String title = itemsBean.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "itemBean.title");
                arrayList.add(new WorkoutCategoriesBean(categoryId, title));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getWorkoutVideoCategoryList() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        WorkoutVideosManager.getCategoryList(new CoveApiListener<SWorkoutCategoryList, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$getWorkoutVideoCategoryList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                this.getGetCategoriesLiveData().postValue(objectRef.element);
            }

            /* JADX WARN: Type inference failed for: r3v4, types: [java.util.List, T] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SWorkoutCategoryList sWorkoutCategoryList) {
                if (sWorkoutCategoryList == null || sWorkoutCategoryList.getData() == null || sWorkoutCategoryList.getData().getItems() == null) {
                    return;
                }
                Ref.ObjectRef<List<WorkoutCategoriesBean>> objectRef2 = objectRef;
                ViewModelWorkoutVideos viewModelWorkoutVideos = this;
                SWorkoutCategoryList.DataBean data = sWorkoutCategoryList.getData();
                Intrinsics.checkNotNull(data);
                List<WorkoutCategoriesBean> workoutCategoriesBeans = viewModelWorkoutVideos.getWorkoutCategoriesBeans(data.getItems());
                Intrinsics.checkNotNull(workoutCategoriesBeans, "null cannot be cast to non-null type kotlin.collections.MutableList<com.coveiot.android.activitymodes.workoutVideos.models.WorkoutCategoriesBean>");
                objectRef2.element = TypeIntrinsics.asMutableList(workoutCategoriesBeans);
                this.getGetCategoriesLiveData().postValue(objectRef.element);
            }
        });
    }

    public final void getWorkoutVideosBeans(@Nullable List<? extends SWorkoutVideosList.DataBean.ItemsBean> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty())) {
            for (SWorkoutVideosList.DataBean.ItemsBean itemsBean : list) {
                String videoId = itemsBean.getVideoId();
                Intrinsics.checkNotNullExpressionValue(videoId, "itemBean.videoId");
                String ytVideoId = itemsBean.getYtVideoId();
                Intrinsics.checkNotNullExpressionValue(ytVideoId, "itemBean.ytVideoId");
                arrayList.add(new WorkoutVideosBean(videoId, ytVideoId, itemsBean.getTitle(), itemsBean.getDescription(), itemsBean.getDuration(), itemsBean.getVideoUrl(), itemsBean.getThumbnailUrl(), itemsBean.getCategoryIds()));
            }
        }
        this.l.postValue(arrayList);
    }

    @NotNull
    public final Flow<PagingData<WorkoutVideosBean>> pullSensAIVideos(@Nullable String str) {
        Flow<PagingData<WorkoutVideosBean>> cachedIn = CachedPagingDataKt.cachedIn(c(str), ViewModelKt.getViewModelScope(this));
        this.f = cachedIn;
        return cachedIn;
    }

    @NotNull
    public final Flow<PagingData<WorkoutVideosBean>> pullWorkVideos(@Nullable String str) {
        Flow<PagingData<WorkoutVideosBean>> cachedIn = CachedPagingDataKt.cachedIn(d(str), ViewModelKt.getViewModelScope(this));
        this.f = cachedIn;
        return cachedIn;
    }

    public final void saveFeedbackAnswer(@NotNull String videoId, @NotNull FeedbackQuestionnarieModel feedbackQuestionnaireModel) {
        Intrinsics.checkNotNullParameter(videoId, "videoId");
        Intrinsics.checkNotNullParameter(feedbackQuestionnaireModel, "feedbackQuestionnaireModel");
        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = new SaveFeedbackQuestionarieRequest();
        saveFeedbackQuestionarieRequest.setVideoId(videoId);
        saveFeedbackQuestionarieRequest.setDate(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        saveFeedbackQuestionarieRequest.setQuestionnaireId(feedbackQuestionnaireModel.getQuestionnaireId());
        saveFeedbackQuestionarieRequest.setSubject(feedbackQuestionnaireModel.getSubject());
        ArrayList arrayList = new ArrayList();
        List<AnswerModel> answers = feedbackQuestionnaireModel.getAnswers();
        if (!(answers == null || answers.isEmpty())) {
            List<AnswerModel> answers2 = feedbackQuestionnaireModel.getAnswers();
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
        FeedbackApiManager.saveFeedbackData(saveFeedbackQuestionarieRequest, new CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.workoutVideos.viewmodel.ViewModelWorkoutVideos$saveFeedbackAnswer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ViewModelWorkoutVideos.this.getPostQuestionarieLiveData().postValue(Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFeedbackQuestionarieReponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ViewModelWorkoutVideos.this.getPostQuestionarieLiveData().postValue(Boolean.TRUE);
            }
        });
    }

    public final void setGetCategoriesLiveData(@NotNull MutableLiveData<List<WorkoutCategoriesBean>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.i = mutableLiveData;
    }

    public final void setGetQuestionarieLiveData(@NotNull MutableLiveData<FeedbackQuestionnarieModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setGetRecommenderCultFitVideosLiveData(@NotNull MutableLiveData<List<WorkoutVideosBean>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.l = mutableLiveData;
    }

    public final void setPostQuestionarieLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.h = mutableLiveData;
    }
}
