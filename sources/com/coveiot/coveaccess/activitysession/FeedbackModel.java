package com.coveiot.coveaccess.activitysession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FeedbackModel {
    @SerializedName("questionnaireId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6387a;
    @SerializedName("questionsAndAnswers")
    @Expose
    private List<AnswerModel> b;

    public String getQuestionnaireId() {
        return this.f6387a;
    }

    public List<AnswerModel> getQuestionsAndAnswers() {
        return this.b;
    }

    public void setQuestionnaireId(String str) {
        this.f6387a = str;
    }

    public void setQuestionsAndAnswers(List<AnswerModel> list) {
        this.b = list;
    }
}
