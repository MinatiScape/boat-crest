package com.coveiot.android.activitymodes.feedback;

import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class QuestionModel {
    @Nullable
    private List<OptionModel> options;
    @Nullable
    private String questionId;
    @Nullable
    private String text;
    @Nullable
    private String type;

    @Nullable
    public final List<OptionModel> getOptions() {
        return this.options;
    }

    @Nullable
    public final String getQuestionId() {
        return this.questionId;
    }

    @Nullable
    public final String getText() {
        return this.text;
    }

    @Nullable
    public final String getType() {
        return this.type;
    }

    public final void setOptions(@Nullable List<OptionModel> list) {
        this.options = list;
    }

    public final void setQuestionId(@Nullable String str) {
        this.questionId = str;
    }

    public final void setText(@Nullable String str) {
        this.text = str;
    }

    public final void setType(@Nullable String str) {
        this.type = str;
    }
}
