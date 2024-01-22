package com.coveiot.coveaccess.fitnesscomputeddataapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class FeedBackData {
    @SerializedName("questionnaireId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6553a;
    @SerializedName("questionsAndAnswers")
    @Expose
    private List<QuestionsAndAnswer> b = null;

    /* loaded from: classes8.dex */
    public class QuestionsAndAnswer {
        @SerializedName("questionId")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6554a;
        @SerializedName("answerIds")
        @Expose
        private List<String> b = null;
        @SerializedName("userInput")
        @Expose
        private String c;

        public QuestionsAndAnswer(FeedBackData feedBackData) {
        }

        public List<String> getAnswerIds() {
            return this.b;
        }

        public String getQuestionId() {
            return this.f6554a;
        }

        public String getUserInput() {
            return this.c;
        }

        public void setAnswerIds(List<String> list) {
            this.b = list;
        }

        public void setQuestionId(String str) {
            this.f6554a = str;
        }

        public void setUserInput(String str) {
            this.c = str;
        }
    }

    public String getQuestionnaireId() {
        return this.f6553a;
    }

    public List<QuestionsAndAnswer> getQuestionsAndAnswers() {
        return this.b;
    }

    public void setQuestionnaireId(String str) {
        this.f6553a = str;
    }

    public void setQuestionsAndAnswers(List<QuestionsAndAnswer> list) {
        this.b = list;
    }
}
