package com.coveiot.android.sleepenergyscore.sleepscore.database.entities;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class QuestionAnswerSleepData {
    @SerializedName("questionId")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5744a;
    @SerializedName("answerIds")
    @Nullable
    private List<String> b;
    @SerializedName("userInput")
    @Nullable
    private String c;

    @Nullable
    public final List<String> getAnswerIds() {
        return this.b;
    }

    @Nullable
    public final String getQuestionId() {
        return this.f5744a;
    }

    @Nullable
    public final String getUserInput() {
        return this.c;
    }

    public final void setAnswerIds(@Nullable List<String> list) {
        this.b = list;
    }

    public final void setQuestionId(@Nullable String str) {
        this.f5744a = str;
    }

    public final void setUserInput(@Nullable String str) {
        this.c = str;
    }
}
