package com.coveiot.android.sleepenergyscore;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.energymeter.EnergyScoreFeedbackEventData;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.database.SleepScoreRepository;
import com.coveiot.android.sleepenergyscore.sleepscore.eventdata.SleepScoreFeedbackEventData;
import com.coveiot.android.sleepenergyscore.utils.Constants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieReponse;
import com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi.SaveFeedbackQuestionarieRequest;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.utils.CoveEventBusManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class FitnessComputedDataApiCall$saveFeedbackToServer$1 implements CoveApiListener<SaveFeedbackQuestionarieReponse, CoveApiErrorModel> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f5694a;
    public final /* synthetic */ SaveFeedbackQuestionarieRequest b;
    public final /* synthetic */ Context c;

    public FitnessComputedDataApiCall$saveFeedbackToServer$1(String str, SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest, Context context) {
        this.f5694a = str;
        this.b = saveFeedbackQuestionarieRequest;
        this.c = context;
    }

    public static final void c() {
        CoveEventBusManager.getInstance().getEventBus().post(new SleepScoreFeedbackEventData());
    }

    public static final void d() {
        CoveEventBusManager.getInstance().getEventBus().post(new EnergyScoreFeedbackEventData());
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
    }

    @Override // com.coveiot.coveaccess.CoveApiListener
    public void onSuccess(@NotNull SaveFeedbackQuestionarieReponse object) {
        List<SaveFeedbackQuestionarieRequest.QuestionsAndAnswer> questionsAndAnswers;
        Intrinsics.checkNotNullParameter(object, "object");
        if (this.f5694a.equals(Constants.SLEEP_COMPUTATION.getValue())) {
            SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest = this.b;
            questionsAndAnswers = saveFeedbackQuestionarieRequest != null ? saveFeedbackQuestionarieRequest.getQuestionsAndAnswers() : null;
            Intrinsics.checkNotNull(questionsAndAnswers, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sleepenergyscore.sleepscore.database.entities.QuestionAnswerSleepData>");
            SleepScoreRepository singletonHolder = SleepScoreRepository.Companion.getInstance(this.c);
            String questionnaireId = this.b.getQuestionnaireId();
            Intrinsics.checkNotNullExpressionValue(questionnaireId, "saveFeedbackQuestionarieRequest.questionnaireId");
            String macAddress = BleApiManager.getInstance(this.c).getBleApi().getMacAddress();
            Date time = Calendar.getInstance().getTime();
            FitnessComputedDataApiCall fitnessComputedDataApiCall = FitnessComputedDataApiCall.INSTANCE;
            String formatDate = RepositoryUtils.formatDate(time, fitnessComputedDataApiCall.getDateFormat());
            Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
            singletonHolder.updateFeedbackList((ArrayList) questionsAndAnswers, questionnaireId, macAddress, formatDate);
            fitnessComputedDataApiCall.getMainHandler().post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.a
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessComputedDataApiCall$saveFeedbackToServer$1.c();
                }
            });
        } else {
            SaveFeedbackQuestionarieRequest saveFeedbackQuestionarieRequest2 = this.b;
            questionsAndAnswers = saveFeedbackQuestionarieRequest2 != null ? saveFeedbackQuestionarieRequest2.getQuestionsAndAnswers() : null;
            Intrinsics.checkNotNull(questionsAndAnswers, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sleepenergyscore.energymeter.database.entities.QuestionAnswerData>");
            EnergyScoreRepository singletonHolder2 = EnergyScoreRepository.Companion.getInstance(this.c);
            String questionnaireId2 = this.b.getQuestionnaireId();
            Intrinsics.checkNotNullExpressionValue(questionnaireId2, "saveFeedbackQuestionarieRequest.questionnaireId");
            String macAddress2 = BleApiManager.getInstance(this.c).getBleApi().getMacAddress();
            Date time2 = Calendar.getInstance().getTime();
            FitnessComputedDataApiCall fitnessComputedDataApiCall2 = FitnessComputedDataApiCall.INSTANCE;
            String formatDate2 = RepositoryUtils.formatDate(time2, fitnessComputedDataApiCall2.getDateFormat());
            Intrinsics.checkNotNullExpressionValue(formatDate2, "formatDate(\n            …                        )");
            singletonHolder2.updateFeedbackList((ArrayList) questionsAndAnswers, questionnaireId2, macAddress2, formatDate2);
            fitnessComputedDataApiCall2.getMainHandler().post(new Runnable() { // from class: com.coveiot.android.sleepenergyscore.b
                @Override // java.lang.Runnable
                public final void run() {
                    FitnessComputedDataApiCall$saveFeedbackToServer$1.d();
                }
            });
        }
        System.out.println((Object) ("feedbackresponse$$$$$ " + object));
    }
}
