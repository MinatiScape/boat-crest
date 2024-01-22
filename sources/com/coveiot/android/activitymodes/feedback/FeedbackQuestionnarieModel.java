package com.coveiot.android.activitymodes.feedback;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FeedbackQuestionnarieModel {
    @Nullable
    private List<AnswerModel> answers;
    @Nullable
    private String createdDate;
    @Nullable
    private String questionnaireId;
    @Nullable
    private List<QuestionModel> questions;
    @Nullable
    private String subject;

    @Nullable
    public final List<AnswerModel> getAnswers() {
        return this.answers;
    }

    @Nullable
    public final String getCreatedDate() {
        return this.createdDate;
    }

    @Nullable
    public final String getQuestionnaireId() {
        return this.questionnaireId;
    }

    @Nullable
    public final List<QuestionModel> getQuestions() {
        return this.questions;
    }

    @Nullable
    public final String getSubject() {
        return this.subject;
    }

    public final void setAnswers(@Nullable List<AnswerModel> list) {
        this.answers = list;
    }

    public final void setCreatedDate(@Nullable String str) {
        this.createdDate = str;
    }

    public final void setQuestionnaireId(@Nullable String str) {
        this.questionnaireId = str;
    }

    public final void setQuestions(@Nullable List<QuestionModel> list) {
        this.questions = list;
    }

    public final void setSubject(@Nullable String str) {
        this.subject = str;
    }
}
