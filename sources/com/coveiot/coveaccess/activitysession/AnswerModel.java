package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AnswerModel {
    @SerializedName("questionId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6382a;
    @SerializedName("answerIds")
    @Expose
    private List<String> b;
    @SerializedName("userInput")
    @Expose
    private String c;

    public List<String> getAnswerIds() {
        return this.b;
    }

    public String getQuestionId() {
        return this.f6382a;
    }

    public String getUserInput() {
        return this.c;
    }

    public void setAnswerIds(List<String> list) {
        this.b = list;
    }

    public void setQuestionId(String str) {
        this.f6382a = str;
    }

    public void setUserInput(String str) {
        this.c = str;
    }
}
