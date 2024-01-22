package com.coveiot.android.sleepenergyscore.feedback;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.EnergyScoreDbData;
import com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData;
import com.coveiot.android.sleepenergyscore.energymeter.model.FeedbackQuetionnarieModel;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData;
import com.coveiot.android.sleepenergyscore.sleepscore.database.entities.SleepScoreData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.repository.RepositoryUtils;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class SleepEnergyScoreFeedbackViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5735a;

    public SleepEnergyScoreFeedbackViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5735a = context;
    }

    @NotNull
    public final SaveFeedbackQuestionarieRequest createRequestObject(@NotNull String optionId, @NotNull String userInput, @NotNull FeedbackQuetionnarieModel feedbackQuetionnarieModel) {
        ArrayList<QuestionAnswerData> arrayList;
        ArrayList<QuestionAnswerSleepData> feedbackList;
        Intrinsics.checkNotNullParameter(optionId, "optionId");
        Intrinsics.checkNotNullParameter(userInput, "userInput");
        Intrinsics.checkNotNullParameter(feedbackQuetionnarieModel, "feedbackQuetionnarieModel");
        ArrayList arrayList2 = new ArrayList();
        SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = new SaveFeedbackQuestionarieRequest();
        SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(optionId);
        questionsAndAnswer.setAnswerIds(arrayList3);
        questionsAndAnswer.setQuestionId(feedbackQuetionnarieModel.getQuestionId());
        questionsAndAnswer.setUserInput(userInput);
        arrayList2.add(questionsAndAnswer);
        int i = 0;
        if (feedbackQuetionnarieModel.getSubject().equals(Constants.SLEEP_COMPUTATION.getValue())) {
            String formatDate = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …dd\"\n                    )");
            SleepScoreData sleepScoreData = SleepScoreRepository.Companion.getInstance(this.f5735a).getSleepScoreData(formatDate, BleApiManager.getInstance(this.f5735a).getBleApi().getMacAddress());
            if (sleepScoreData != null && (feedbackList = sleepScoreData.getFeedbackList()) != null && feedbackList.size() > 0) {
                ArrayList<QuestionAnswerSleepData> feedbackList2 = sleepScoreData.getFeedbackList();
                Intrinsics.checkNotNull(feedbackList2);
                int size = feedbackList2.size();
                while (i < size) {
                    SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer2 = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
                    ArrayList<QuestionAnswerSleepData> feedbackList3 = sleepScoreData.getFeedbackList();
                    Intrinsics.checkNotNull(feedbackList3);
                    questionsAndAnswer2.setUserInput(feedbackList3.get(i).getUserInput());
                    ArrayList<QuestionAnswerSleepData> feedbackList4 = sleepScoreData.getFeedbackList();
                    Intrinsics.checkNotNull(feedbackList4);
                    questionsAndAnswer2.setQuestionId(feedbackList4.get(i).getQuestionId());
                    ArrayList<QuestionAnswerSleepData> feedbackList5 = sleepScoreData.getFeedbackList();
                    Intrinsics.checkNotNull(feedbackList5);
                    questionsAndAnswer2.setAnswerIds(feedbackList5.get(i).getAnswerIds());
                    arrayList2.add(questionsAndAnswer2);
                    i++;
                }
            }
        } else {
            String formatDate2 = RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
            Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(\n            …dd\"\n                    )");
            EnergyScoreDbData energyScoreData = EnergyScoreRepository.Companion.getInstance(this.f5735a).getEnergyScoreData(formatDate2, BleApiManager.getInstance(this.f5735a).getBleApi().getMacAddress());
            if (energyScoreData != null && (arrayList = energyScoreData.feedbackList) != null && arrayList.size() > 0) {
                ArrayList<QuestionAnswerData> arrayList4 = energyScoreData.feedbackList;
                Intrinsics.checkNotNull(arrayList4);
                int size2 = arrayList4.size();
                while (i < size2) {
                    SaveFeedbackQuestionarieRequest.QuestionsAndAnswer questionsAndAnswer3 = new SaveFeedbackQuestionarieRequest.QuestionsAndAnswer();
                    ArrayList<QuestionAnswerData> arrayList5 = energyScoreData.feedbackList;
                    Intrinsics.checkNotNull(arrayList5);
                    questionsAndAnswer3.setUserInput(arrayList5.get(i).getUserInput());
                    ArrayList<QuestionAnswerData> arrayList6 = energyScoreData.feedbackList;
                    Intrinsics.checkNotNull(arrayList6);
                    questionsAndAnswer3.setQuestionId(arrayList6.get(i).getQuestionId());
                    ArrayList<QuestionAnswerData> arrayList7 = energyScoreData.feedbackList;
                    Intrinsics.checkNotNull(arrayList7);
                    questionsAndAnswer3.setAnswerIds(arrayList7.get(i).getAnswerIds());
                    arrayList2.add(questionsAndAnswer3);
                    i++;
                }
            }
        }
        saveFeedbackQuestionarieRequest.setDate(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        saveFeedbackQuestionarieRequest.setQuestionnaireId(feedbackQuetionnarieModel.getQuestionnaireId());
        saveFeedbackQuestionarieRequest.setSubject(feedbackQuetionnarieModel.getSubject());
        saveFeedbackQuestionarieRequest.setQuestionsAndAnswers(arrayList2);
        return saveFeedbackQuestionarieRequest;
    }

    @NotNull
    public final Context getContext() {
        return this.f5735a;
    }
}
