package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import java.util.List;
/* loaded from: classes8.dex */
public class StressFitnessData {
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("baseline")
    private Integer baseline;
    @SerializedName("date")
    private String date;
    @SerializedName(d.O)
    private Feedback feedback;
    @SerializedName("readiness")
    private Integer readiness;
    @SerializedName("timeLog")
    private TimeLog timeLog;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;

    /* loaded from: classes8.dex */
    public static class Feedback {
        @SerializedName("questionnaireId")
        private String questionnaireId;
        @SerializedName("questionsAndAnswers")
        private List<QuestionsAndAnswers> questionsAndAnswers;

        /* loaded from: classes8.dex */
        public static class QuestionsAndAnswers {
            @SerializedName("answerIds")
            private List<String> answerIds;
            @SerializedName("questionId")
            private String questionId;
            @SerializedName("userInput")
            private String userInput;

            public List<String> getAnswerIds() {
                return this.answerIds;
            }

            public String getQuestionId() {
                return this.questionId;
            }

            public String getUserInput() {
                return this.userInput;
            }

            public void setAnswerIds(List<String> list) {
                this.answerIds = list;
            }

            public void setQuestionId(String str) {
                this.questionId = str;
            }

            public void setUserInput(String str) {
                this.userInput = str;
            }
        }

        public String getQuestionnaireId() {
            return this.questionnaireId;
        }

        public List<QuestionsAndAnswers> getQuestionsAndAnswers() {
            return this.questionsAndAnswers;
        }

        public void setQuestionnaireId(String str) {
            this.questionnaireId = str;
        }

        public void setQuestionsAndAnswers(List<QuestionsAndAnswers> list) {
            this.questionsAndAnswers = list;
        }
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public Integer getBaseline() {
        return this.baseline;
    }

    public String getDate() {
        return this.date;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }

    public Integer getReadiness() {
        return this.readiness;
    }

    public TimeLog getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setBaseline(Integer num) {
        this.baseline = num;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setReadiness(Integer num) {
        this.readiness = num;
    }

    public void setTimeLog(TimeLog timeLog) {
        this.timeLog = timeLog;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }
}
