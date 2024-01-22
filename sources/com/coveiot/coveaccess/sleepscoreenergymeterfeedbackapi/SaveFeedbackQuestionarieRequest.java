package com.coveiot.coveaccess.sleepscoreenergymeterfeedbackapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SaveFeedbackQuestionarieRequest {
    @SerializedName("date")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private String f6729a;
    @SerializedName("subject")
    @Expose
    private String b;
    @SerializedName("questionnaireId")
    @Expose
    private String c;
    @SerializedName("questionsAndAnswers")
    @Expose
    private List<QuestionsAndAnswer> d = null;
    @SerializedName("clientRefId")
    @Expose
    private String e;
    @SerializedName("videoId")
    @Expose
    private String f;

    /* loaded from: classes8.dex */
    public static class QuestionsAndAnswer {
        @SerializedName("questionId")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6730a;
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
            return this.f6730a;
        }

        public String getUserInput() {
            return this.c;
        }

        public void setAnswerIds(List<String> list) {
            this.b = list;
        }

        public void setQuestionId(String str) {
            this.f6730a = str;
        }

        public void setUserInput(String str) {
            this.c = str;
        }
    }

    public String getClientRefId() {
        return this.e;
    }

    public String getDate() {
        return this.f6729a;
    }

    public String getQuestionnaireId() {
        return this.c;
    }

    public List<QuestionsAndAnswer> getQuestionsAndAnswers() {
        return this.d;
    }

    public String getSubject() {
        return this.b;
    }

    public String getVideoId() {
        return this.f;
    }

    public void setClientRefId(String str) {
        this.e = str;
    }

    public void setDate(String str) {
        this.f6729a = str;
    }

    public void setQuestionnaireId(String str) {
        this.c = str;
    }

    public void setQuestionsAndAnswers(List<QuestionsAndAnswer> list) {
        this.d = list;
    }

    public void setSubject(String str) {
        this.b = str;
    }

    public void setVideoId(String str) {
        this.f = str;
    }
}
