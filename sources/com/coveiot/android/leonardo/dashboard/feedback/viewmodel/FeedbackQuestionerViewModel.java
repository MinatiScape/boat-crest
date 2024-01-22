package com.coveiot.android.leonardo.dashboard.feedback.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.leonardo.dashboard.feedback.eventdata.StressFeedbackEventData;
import com.coveiot.android.leonardo.utils.FeedBackPageType;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.FeedbackApiManager;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieReponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.domainlogic.APIResponseListner;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.stress.datasources.db.read.StressDBRead;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FeedbackQuestionerViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4719a;

    public FeedbackQuestionerViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4719a = context;
    }

    @NotNull
    public final SaveFeedbackQuestionarieRequest createRequestObject(@NotNull String optionId, @NotNull String userInput, @NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModel, @NotNull String subject) {
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        Intrinsics.checkNotNullParameter(userInput, "userInput");
        Intrinsics.checkNotNullParameter(feedbackQuetionnarieModel, "feedbackQuetionnarieModel");
        Intrinsics.checkNotNullParameter(subject, "subject");
        ArrayList arrayList = new ArrayList();
        if (subject.equals(FeedBackPageType.Companion.getSTRESS_DAILY_DATA())) {
            DailyStress dailyStressData = StressDBRead.getInstance(this.f4719a).getDailyStressData(PayUtils.getDate(), BleApiManager.getInstance(this.f4719a).getBleApi().getMacAddress());
            if (dailyStressData != null && !AppUtils.isEmpty(dailyStressData.AnsweredQuestions_FeedBack)) {
                for (String str : dailyStressData.AnsweredQuestions_FeedBack) {
                    arrayList.add(new Gson().fromJson(str, (Class<Object>) SaveFeedbackQuestionarieRequest.QuestionsAndAnswer.class));
                }
            }
        }
        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = new SaveFeedbackQuestionarieRequest();
        SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(optionId);
        questionsAndAnswer.setAnswerIds(arrayList2);
        questionsAndAnswer.setQuestionId(feedbackQuetionnarieModel.getQuestionId());
        questionsAndAnswer.setUserInput(userInput);
        arrayList.add(questionsAndAnswer);
        saveFeedbackQuestionarieRequest.setDate(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        saveFeedbackQuestionarieRequest.setQuestionnaireId(feedbackQuetionnarieModel.getQuestionnaireId());
        saveFeedbackQuestionarieRequest.setSubject(feedbackQuetionnarieModel.getSubject());
        saveFeedbackQuestionarieRequest.setQuestionsAndAnswers(arrayList);
        return saveFeedbackQuestionarieRequest;
    }

    @NotNull
    public final Context getContext() {
        return this.f4719a;
    }

    public final void saveFeedbackToServer(@NotNull final Context context, @Nullable final SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, @NotNull final String pageType, @NotNull final APIResponseListner listner) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(listner, "listner");
        FeedbackApiManager.saveFeedbackData(saveFeedbackQuestionarieRequest, new CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.dashboard.feedback.viewmodel.FeedbackQuestionerViewModel$saveFeedbackToServer$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.dashboard.feedback.viewmodel.FeedbackQuestionerViewModel$saveFeedbackToServer$1$onSuccess$1", f = "FeedbackQuestionerViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes2.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;

                public a(Continuation<? super a> continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(continuation);
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
                        CoveEventBusManager.getInstance().getEventBus().post(new StressFeedbackEventData());
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                listner.onFailure(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveFeedbackQuestionarieReponse object) {
                Intrinsics.checkNotNullParameter(object, "object");
                if (m.equals(object.status, CoveApiConstants.RESPONSE_STATUS_VALUE_OK, true)) {
                    if (pageType.equals(FeedBackPageType.Companion.getSTRESS_DAILY_DATA())) {
                        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest2 = saveFeedbackQuestionarieRequest;
                        List<SaveFeedbackQuestionarieRequest.QuestionsAndAnswer> questionsAndAnswers = saveFeedbackQuestionarieRequest2 != null ? saveFeedbackQuestionarieRequest2.getQuestionsAndAnswers() : null;
                        ArrayList<String> arrayList = new ArrayList<>();
                        DailyStress dailyStressData = StressDBRead.getInstance(context).getDailyStressData(AppUtils.formatDate(new Date(), "yyyy-MM-dd"), BleApiManager.getInstance(context).getBleApi().getMacAddress());
                        if (!AppUtils.isEmpty(dailyStressData.AnsweredQuestions_FeedBack)) {
                            arrayList.addAll(dailyStressData.AnsweredQuestions_FeedBack);
                        }
                        if (questionsAndAnswers != null) {
                            for (SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer : questionsAndAnswers) {
                                arrayList.add(new Gson().toJson(questionsAndAnswer));
                            }
                        }
                        Calendar calendar = Calendar.getInstance();
                        Intrinsics.checkNotNullExpressionValue(calendar, "getInstance()");
                        StressRepository.Companion.getInstance(context).updateAnsweredQuestionsList(calendar, BleApiManager.getInstance(context).getBleApi().getMacAddress(), arrayList);
                        e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new a(null), 2, null);
                        return;
                    }
                    return;
                }
                listner.onFailure(object.message);
            }
        });
    }
}
