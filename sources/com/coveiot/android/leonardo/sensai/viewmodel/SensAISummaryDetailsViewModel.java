package com.coveiot.android.leonardo.sensai.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.sensai.model.feedback.AnswerModel;
import com.coveiot.android.leonardo.sensai.model.feedback.FeedbackQuestionnarieModel;
import com.coveiot.android.leonardo.sensai.model.feedback.OptionModel;
import com.coveiot.android.leonardo.sensai.model.feedback.QuestionModel;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.activitysession.PostActivitySessionHeaderResponse;
import com.coveiot.coveaccess.activitysession.Target;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.fitness.ActivityType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.FeedbackType;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.GetFeedbackListResponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieReponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.repository.Error;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.repository.sensai.datasource.db.read.SensAIBeamDBRead;
import com.coveiot.repository.sensai.datasource.db.write.SensAIBeamDBWrite;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.c;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAISummaryDetailsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5398a;
    @NotNull
    public final String b;
    @NotNull
    public MutableLiveData<SensAIActivitySummaryDetails> c;
    @NotNull
    public MutableLiveData<SensAIActivitySummary> d;
    @NotNull
    public MutableLiveData<FeedbackQuestionnarieModel> e;
    @NotNull
    public MutableLiveData<Boolean> f;

    public SensAISummaryDetailsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5398a = context;
        this.b = "ViewModelSensAISummaryDetails";
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f5398a;
    }

    public final void getFeedbackQuestionnaireList(int i) {
        String name;
        new ArrayList();
        final FeedbackQuestionnarieModel feedbackQuestionnarieModel = new FeedbackQuestionnarieModel();
        if (i == 1) {
            name = FeedbackType.CRICKET_BATTING.name();
        } else {
            name = FeedbackType.CRICKET_BOWLING.name();
        }
        if (name == null) {
            Intrinsics.throwUninitializedPropertyAccessException("subject");
            name = null;
        }
        FeedbackApiManager.getFeedbackQuestionnaireList(name, new CoveApiListener<GetFeedbackListResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$getFeedbackQuestionnaireList$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@NotNull CoveApiErrorModel object) {
                String str;
                Intrinsics.checkNotNullParameter(object, "object");
                str = SensAISummaryDetailsViewModel.this.b;
                LogHelper.d(str, "feedbacklisterror: " + object.getMsg());
                SensAISummaryDetailsViewModel.this.getGetQuestionarieLiveData().postValue(feedbackQuestionnarieModel);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull GetFeedbackListResponse getFeedbackListResponse) {
                String str;
                Intrinsics.checkNotNullParameter(getFeedbackListResponse, "getFeedbackListResponse");
                str = SensAISummaryDetailsViewModel.this.b;
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
                SensAISummaryDetailsViewModel.this.getGetQuestionarieLiveData().postValue(feedbackQuestionnarieModel);
            }
        });
    }

    @NotNull
    public final MutableLiveData<SensAIActivitySummary> getGetActivitySummaryDataLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<SensAIActivitySummaryDetails> getGetActivitySummaryDetailsLiveData() {
        return this.c;
    }

    @NotNull
    public final MutableLiveData<FeedbackQuestionnarieModel> getGetQuestionarieLiveData() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Boolean> getPostQuestionarieLiveData() {
        return this.f;
    }

    public final void getSummaryDataItemFromDB(@NotNull String sessionID, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(sessionID, "sessionID");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        this.d.postValue(SensAIBeamDBRead.getInstance(this.f5398a).getActivitySummary(sessionID, serialNo));
    }

    public final void getSummaryDetailsDataFromDB(@NotNull String sessionID, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(sessionID, "sessionID");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        this.c.postValue(SensAIBeamDBRead.getInstance(this.f5398a).getActivitySummaryDetails(sessionID, serialNo));
    }

    public final void getSummaryDetailsFromServer(int i, @NotNull String clientRefId) {
        Intrinsics.checkNotNullParameter(clientRefId, "clientRefId");
        if (i == 1) {
            String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
            Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
            TraqConfigApi.getSessionOverallDataFromServer(0, Integer.parseInt(userDeviceID), clientRefId, ActivityType.CRICKET_BATTING, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$getSummaryDetailsFromServer$1

                @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$getSummaryDetailsFromServer$1$onSuccess$1", f = "SensAISummaryDetailsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes5.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                    public int label;
                    public final /* synthetic */ SensAISummaryDetailsViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel, PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = sensAISummaryDetailsViewModel;
                        this.$p0 = postActivitySessionHeaderResponse;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, this.$p0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(this.this$0.getContext()).getConnectedDeviceMacAddress();
                            SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                            SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                            sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                            Intrinsics.checkNotNull(connectedDeviceMacAddress);
                            sensAIActivitySummary.setMacAddress(connectedDeviceMacAddress);
                            sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                            PayUtils payUtils = PayUtils.INSTANCE;
                            String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                            Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                            Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                            Intrinsics.checkNotNull(timeStampFromDate);
                            sensAIActivitySummary.setTimestamp(timeStampFromDate);
                            sensAIActivitySummary.setActivityType(1);
                            sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                            Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                            Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                            sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                            sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                            Integer totalHits = this.$p0.requestData.getActivityData().getTotalHits();
                            Intrinsics.checkNotNullExpressionValue(totalHits, "p0.requestData.activityData.totalHits");
                            sensAIActivitySummary.setPlayed(totalHits.intValue());
                            Integer hitPercentage = this.$p0.requestData.getActivityData().getHitPercentage();
                            Intrinsics.checkNotNullExpressionValue(hitPercentage, "p0.requestData.activityData.hitPercentage");
                            sensAIActivitySummary.setHitPct(hitPercentage.intValue());
                            Integer totalSwings = this.$p0.requestData.getActivityData().getTotalSwings();
                            Intrinsics.checkNotNullExpressionValue(totalSwings, "p0.requestData.activityData.totalSwings");
                            sensAIActivitySummary.setTotalSwings(totalSwings.intValue());
                            if (this.$p0.requestData.getActivityData().getPlayingHand().equals("RIGHT")) {
                                sensAIActivitySummary.setHand(0);
                            } else {
                                sensAIActivitySummary.setHand(1);
                            }
                            if (this.$p0.requestData.getActivityData().getTargets() != null) {
                                for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                    if (target.getBaseUnit().equals("MINUTES")) {
                                        sensAIActivitySummary.setGoalType(1);
                                        Integer value = target.getValue();
                                        Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                        sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                    } else {
                                        sensAIActivitySummary.setGoalType(2);
                                        Integer value2 = target.getValue();
                                        Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                        sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                    }
                                }
                            }
                            Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                            Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                            sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                            Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                            Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                            sensAIActivitySummary.setMaxHR(maxHr.intValue());
                            Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                            Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                            sensAIActivitySummary.setAvgHR(avgHr.intValue());
                            Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                            Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                            sensAIActivitySummary.setMaxHandSpeed(c.roundToInt(maxHandSpeed.doubleValue()));
                            Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                            Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                            sensAIActivitySummary.setAvgHandSpeed(c.roundToInt(avgHandSpeed.doubleValue()));
                            sensAIActivitySummary.setSavedServer(true);
                            sensAIActivitySummary.setDataAggregateSaved(true);
                            sensAIActivitySummary.setAddToCompare(false);
                            sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                            sensAIActivitySummaryDetails.setMacAddress(connectedDeviceMacAddress);
                            sensAIActivitySummaryDetails.setActivityType(1);
                            PayUtils payUtils2 = PayUtils.INSTANCE;
                            String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                            Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                            Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                            Intrinsics.checkNotNull(timeStampFromDate2);
                            sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                            if (this.$p0.requestData.getFeedback() != null) {
                                sensAIActivitySummaryDetails.setFeedbackSaved(true);
                            }
                            if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                                sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                                sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                                sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                                sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                                ArrayList<Integer> arrayList = new ArrayList<>();
                                for (Boolean hitItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHitValues()) {
                                    Intrinsics.checkNotNullExpressionValue(hitItem, "hitItem");
                                    if (hitItem.booleanValue()) {
                                        arrayList.add(Boxing.boxInt(1));
                                    } else {
                                        arrayList.add(Boxing.boxInt(0));
                                    }
                                }
                                sensAIActivitySummaryDetails.setHitOrMiss(arrayList);
                                ArrayList<Integer> arrayList2 = new ArrayList<>();
                                for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                    Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                    arrayList2.add(Boxing.boxInt(c.roundToInt(calorieItem.floatValue())));
                                }
                                sensAIActivitySummaryDetails.setCalories(arrayList2);
                                sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                            }
                            SensAIBeamDBWrite.getInstance(this.this$0.getContext()).updateActivitySummary(sensAIActivitySummary);
                            SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                            SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this.this$0;
                            String sessionId = sensAIActivitySummaryDetails.getSessionId();
                            Intrinsics.checkNotNullExpressionValue(sessionId, "battingDetailsItem.sessionId");
                            sensAISummaryDetailsViewModel.getSummaryDataItemFromDB(sessionId, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                            SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel2 = this.this$0;
                            String sessionId2 = sensAIActivitySummaryDetails.getSessionId();
                            Intrinsics.checkNotNullExpressionValue(sessionId2, "battingDetailsItem.sessionId");
                            sensAISummaryDetailsViewModel2.getSummaryDetailsDataFromDB(sessionId2, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                    if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                        e.e(GlobalScope.INSTANCE, null, null, new a(SensAISummaryDetailsViewModel.this, postActivitySessionHeaderResponse, null), 3, null);
                    }
                }
            });
            return;
        }
        String userDeviceID2 = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID2, "getInstance().userDeviceID");
        TraqConfigApi.getSessionOverallDataFromServer(0, Integer.parseInt(userDeviceID2), clientRefId, ActivityType.CRICKET_BOWLING, new CoveApiListener<PostActivitySessionHeaderResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$getSummaryDetailsFromServer$2

            @DebugMetadata(c = "com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$getSummaryDetailsFromServer$2$onSuccess$1", f = "SensAISummaryDetailsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ PostActivitySessionHeaderResponse $p0;
                public int label;
                public final /* synthetic */ SensAISummaryDetailsViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel, PostActivitySessionHeaderResponse postActivitySessionHeaderResponse, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = sensAISummaryDetailsViewModel;
                    this.$p0 = postActivitySessionHeaderResponse;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$p0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        String connectedDeviceMacAddress = new com.coveiot.android.activitymodes.preference.PreferenceManager(this.this$0.getContext()).getConnectedDeviceMacAddress();
                        SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                        SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                        sensAIActivitySummary.setSessionId(this.$p0.requestData.getFwSessionId());
                        Intrinsics.checkNotNull(connectedDeviceMacAddress);
                        sensAIActivitySummary.setMacAddress(connectedDeviceMacAddress);
                        sensAIActivitySummary.setClientRefID(this.$p0.requestData.getClientRefId());
                        PayUtils payUtils = PayUtils.INSTANCE;
                        String sessionStartDate = this.$p0.requestData.getSessionStartDate();
                        Intrinsics.checkNotNullExpressionValue(sessionStartDate, "p0.requestData.sessionStartDate");
                        Long timeStampFromDate = payUtils.getTimeStampFromDate(sessionStartDate);
                        Intrinsics.checkNotNull(timeStampFromDate);
                        sensAIActivitySummary.setTimestamp(timeStampFromDate);
                        sensAIActivitySummary.setActivityType(2);
                        sensAIActivitySummary.setDurationSec(this.$p0.requestData.getTotalActivityDuration());
                        Integer totalSteps = this.$p0.requestData.getActivityData().getTotalSteps();
                        Intrinsics.checkNotNullExpressionValue(totalSteps, "p0.requestData.activityData.totalSteps");
                        sensAIActivitySummary.setTotalSteps(totalSteps.intValue());
                        sensAIActivitySummary.setTotalCalories(this.$p0.requestData.getActivityData().getTotalCalories().floatValue());
                        Integer totalBallsBowled = this.$p0.requestData.getActivityData().getTotalBallsBowled();
                        Intrinsics.checkNotNullExpressionValue(totalBallsBowled, "p0.requestData.activityData.totalBallsBowled");
                        sensAIActivitySummary.setTotalBallsBowled(totalBallsBowled.intValue());
                        if (this.$p0.requestData.getActivityData().getPlayingHand().equals("RIGHT")) {
                            sensAIActivitySummary.setHand(0);
                        } else {
                            sensAIActivitySummary.setHand(1);
                        }
                        if (m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_FAST.getValue(), true)) {
                            sensAIActivitySummary.setBowlingType(0);
                        } else if (!m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_MEDIUM.getValue(), true) && !m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_MEDIUM_PACE.getValue(), true)) {
                            if (m.equals(this.$p0.requestData.getActivityData().getBowlingType(), AppConstants.BOWLING_SPIN.getValue(), true)) {
                                sensAIActivitySummary.setBowlingType(2);
                            }
                        } else {
                            sensAIActivitySummary.setBowlingType(1);
                        }
                        if (this.$p0.requestData.getActivityData().getTargets() != null) {
                            for (Target target : this.$p0.requestData.getActivityData().getTargets()) {
                                if (target.getBaseUnit().equals("MINUTES")) {
                                    sensAIActivitySummary.setGoalType(1);
                                    Integer value = target.getValue();
                                    Intrinsics.checkNotNullExpressionValue(value, "targetItem.value");
                                    sensAIActivitySummary.setTargetGoalValue(value.intValue());
                                } else {
                                    sensAIActivitySummary.setGoalType(2);
                                    Integer value2 = target.getValue();
                                    Intrinsics.checkNotNullExpressionValue(value2, "targetItem.value");
                                    sensAIActivitySummary.setTargetGoalValue(value2.intValue());
                                }
                            }
                        }
                        Integer targetAchievedPct = this.$p0.requestData.getActivityData().getTargetAchievedPct();
                        Intrinsics.checkNotNullExpressionValue(targetAchievedPct, "p0.requestData.activityData.targetAchievedPct");
                        sensAIActivitySummary.setGoalCompletionPct(targetAchievedPct.intValue());
                        Integer maxHr = this.$p0.requestData.getActivityData().getMaxHr();
                        Intrinsics.checkNotNullExpressionValue(maxHr, "p0.requestData.activityData.maxHr");
                        sensAIActivitySummary.setMaxHR(maxHr.intValue());
                        Integer avgHr = this.$p0.requestData.getActivityData().getAvgHr();
                        Intrinsics.checkNotNullExpressionValue(avgHr, "p0.requestData.activityData.avgHr");
                        sensAIActivitySummary.setAvgHR(avgHr.intValue());
                        Double maxHandSpeed = this.$p0.requestData.getActivityData().getMaxHandSpeed();
                        Intrinsics.checkNotNullExpressionValue(maxHandSpeed, "p0.requestData.activityData.maxHandSpeed");
                        sensAIActivitySummary.setMaxArmSpeed(c.roundToInt(maxHandSpeed.doubleValue()));
                        Double avgHandSpeed = this.$p0.requestData.getActivityData().getAvgHandSpeed();
                        Intrinsics.checkNotNullExpressionValue(avgHandSpeed, "p0.requestData.activityData.avgHandSpeed");
                        sensAIActivitySummary.setAvgArmSpeed(c.roundToInt(avgHandSpeed.doubleValue()));
                        Double minHandSpeed = this.$p0.requestData.getActivityData().getMinHandSpeed();
                        Intrinsics.checkNotNullExpressionValue(minHandSpeed, "p0.requestData.activityData.minHandSpeed");
                        sensAIActivitySummary.setMinArmSpeed(c.roundToInt(minHandSpeed.doubleValue()));
                        sensAIActivitySummary.setSavedServer(true);
                        sensAIActivitySummary.setDataAggregateSaved(true);
                        sensAIActivitySummary.setAddToCompare(false);
                        if (this.$p0.requestData.getFeedback() != null) {
                            sensAIActivitySummaryDetails.setFeedbackSaved(true);
                        }
                        sensAIActivitySummaryDetails.setSessionId(this.$p0.requestData.getFwSessionId());
                        sensAIActivitySummaryDetails.setMacAddress(connectedDeviceMacAddress);
                        sensAIActivitySummaryDetails.setActivityType(2);
                        PayUtils payUtils2 = PayUtils.INSTANCE;
                        String sessionStartDate2 = this.$p0.requestData.getSessionStartDate();
                        Intrinsics.checkNotNullExpressionValue(sessionStartDate2, "p0.requestData.sessionStartDate");
                        Long timeStampFromDate2 = payUtils2.getTimeStampFromDate(sessionStartDate2);
                        Intrinsics.checkNotNull(timeStampFromDate2);
                        sensAIActivitySummaryDetails.setUnixTimeStamp(timeStampFromDate2);
                        if (this.$p0.requestData.getActivityData().getTraqActivityLogs() != null) {
                            sensAIActivitySummaryDetails.setDistance((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getDistanceValues());
                            sensAIActivitySummaryDetails.setHr((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHrValues());
                            sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getHandSpeedValues());
                            sensAIActivitySummaryDetails.setSteps((ArrayList) this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getStepValues());
                            ArrayList<Integer> arrayList = new ArrayList<>();
                            for (Float calorieItem : this.$p0.requestData.getActivityData().getTraqActivityLogs().get(0).getCalorieValues()) {
                                Intrinsics.checkNotNullExpressionValue(calorieItem, "calorieItem");
                                arrayList.add(Boxing.boxInt(c.roundToInt(calorieItem.floatValue())));
                            }
                            sensAIActivitySummaryDetails.setCalories(arrayList);
                            sensAIActivitySummaryDetails.setDetailsDataNum(sensAIActivitySummaryDetails.getCalories().size());
                        }
                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).updateActivitySummary(sensAIActivitySummary);
                        SensAIBeamDBWrite.getInstance(this.this$0.getContext()).insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel = this.this$0;
                        String sessionId = sensAIActivitySummaryDetails.getSessionId();
                        Intrinsics.checkNotNullExpressionValue(sessionId, "bowlingDetailsItem.sessionId");
                        sensAISummaryDetailsViewModel.getSummaryDataItemFromDB(sessionId, BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress());
                        SensAISummaryDetailsViewModel sensAISummaryDetailsViewModel2 = this.this$0;
                        String sessionId2 = sensAIActivitySummaryDetails.getSessionId();
                        Intrinsics.checkNotNullExpressionValue(sessionId2, "bowlingDetailsItem.sessionId");
                        sensAISummaryDetailsViewModel2.getSummaryDetailsDataFromDB(sessionId2, connectedDeviceMacAddress);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable PostActivitySessionHeaderResponse postActivitySessionHeaderResponse) {
                if ((postActivitySessionHeaderResponse != null ? postActivitySessionHeaderResponse.requestData : null) != null) {
                    e.e(GlobalScope.INSTANCE, null, null, new a(SensAISummaryDetailsViewModel.this, postActivitySessionHeaderResponse, null), 3, null);
                }
            }
        });
    }

    public final void saveFeedbackAnswer(@NotNull String clientRefId, @NotNull FeedbackQuestionnarieModel feedbackQuestionnaireModel) {
        Intrinsics.checkNotNullParameter(clientRefId, "clientRefId");
        Intrinsics.checkNotNullParameter(feedbackQuestionnaireModel, "feedbackQuestionnaireModel");
        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = new SaveFeedbackQuestionarieRequest();
        saveFeedbackQuestionarieRequest.setClientRefId(clientRefId);
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
        FeedbackApiManager.saveFeedbackData(saveFeedbackQuestionarieRequest, new CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$saveFeedbackAnswer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                SensAISummaryDetailsViewModel.this.getPostQuestionarieLiveData().postValue(Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFeedbackQuestionarieReponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                SensAISummaryDetailsViewModel.this.getPostQuestionarieLiveData().postValue(Boolean.TRUE);
            }
        });
    }

    public final void setGetActivitySummaryDataLiveData(@NotNull MutableLiveData<SensAIActivitySummary> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setGetActivitySummaryDetailsLiveData(@NotNull MutableLiveData<SensAIActivitySummaryDetails> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setGetQuestionarieLiveData(@NotNull MutableLiveData<FeedbackQuestionnarieModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setPostQuestionarieLiveData(@NotNull MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void syncSensAI(@NotNull final String sessionID, @NotNull final String serialNo) {
        Intrinsics.checkNotNullParameter(sessionID, "sessionID");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        if (BleApiManager.getInstance(this.f5398a).getBleApi().getDeviceSupportedFeatures().isSensAISupported()) {
            SyncManager.getInstance().syncSensAISummaryData(new SyncCompleteListner() { // from class: com.coveiot.android.leonardo.sensai.viewmodel.SensAISummaryDetailsViewModel$syncSensAI$1
                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onDataSyncComplete() {
                    SensAISummaryDetailsViewModel.this.getSummaryDataItemFromDB(sessionID, serialNo);
                    SensAISummaryDetailsViewModel.this.getSummaryDetailsDataFromDB(sessionID, serialNo);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onFailure(@Nullable String str, @Nullable Error error) {
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncCompleteListner
                public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                }
            });
        }
    }

    public final void updateFeedbackToServer(boolean z, @NotNull String sessionID, @NotNull String serialNo) {
        Intrinsics.checkNotNullParameter(sessionID, "sessionID");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        SensAIBeamDBWrite.getInstance(this.f5398a).updateFeedbackSummaryData(z, serialNo, sessionID);
    }
}
