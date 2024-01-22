package com.coveiot.android.activitymodes.feedback;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FeedbackModel {
    @Nullable
    private String questionnaireId;
    @Nullable
    private List<AnswerModel> questionsAndAnswers;

    @Nullable
    public final String getQuestionnaireId() {
        return this.questionnaireId;
    }

    @Nullable
    public final List<AnswerModel> getQuestionsAndAnswers() {
        return this.questionsAndAnswers;
    }

    public final void setQuestionnaireId(@Nullable String str) {
        this.questionnaireId = str;
    }

    public final void setQuestionsAndAnswers(@Nullable List<AnswerModel> list) {
        this.questionsAndAnswers = list;
    }
}
