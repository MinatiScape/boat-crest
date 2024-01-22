package com.coveiot.android.activitymodes.feedback;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AnswerModel {
    @Nullable
    private List<String> answerIds;
    @Nullable
    private String questionId;
    @Nullable
    private String userInput;

    @Nullable
    public final List<String> getAnswerIds() {
        return this.answerIds;
    }

    @Nullable
    public final String getQuestionId() {
        return this.questionId;
    }

    @Nullable
    public final String getUserInput() {
        return this.userInput;
    }

    public final void setAnswerIds(@Nullable List<String> list) {
        this.answerIds = list;
    }

    public final void setQuestionId(@Nullable String str) {
        this.questionId = str;
    }

    public final void setUserInput(@Nullable String str) {
        this.userInput = str;
    }
}
