package com.coveiot.coveaccess.model.server;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import java.util.List;
/* loaded from: classes8.dex */
public class PeriodicStressData {
    @SerializedName("avg")
    private Integer avg;
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("baseline")
    private Integer baseline;
    @SerializedName("codec")
    private CodecBean codec;
    @SerializedName("date")
    private String date;
    @SerializedName(d.O)
    private Feedback feedback;
    @SerializedName(Constants.PRIORITY_MAX)
    private Integer max;
    @SerializedName("min")
    private Integer min;
    @SerializedName("readiness")
    private Integer readiness;
    @SerializedName("timeLog")
    private TimeLogBean timeLog;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;
    @SerializedName("userDeviceId")
    private String userDeviceId;

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

    public Integer getAvg() {
        return this.avg;
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public Integer getBaseline() {
        return this.baseline;
    }

    public CodecBean getCodec() {
        return this.codec;
    }

    public String getDate() {
        return this.date;
    }

    public Feedback getFeedback() {
        return this.feedback;
    }

    public Integer getMax() {
        return this.max;
    }

    public Integer getMin() {
        return this.min;
    }

    public Integer getReadiness() {
        return this.readiness;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public String getUserDeviceId() {
        return this.userDeviceId;
    }

    public void setAvg(Integer num) {
        this.avg = num;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setBaseline(Integer num) {
        this.baseline = num;
    }

    public void setCodec(CodecBean codecBean) {
        this.codec = codecBean;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void setMax(Integer num) {
        this.max = num;
    }

    public void setMin(Integer num) {
        this.min = num;
    }

    public void setReadiness(Integer num) {
        this.readiness = num;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }

    public void setUserDeviceId(String str) {
        this.userDeviceId = str;
    }

    public String toString() {
        return "PeriodicStressData{type='" + this.type + "', date='" + this.date + "', tzOffset='" + this.tzOffset + "', baseUnit='" + this.baseUnit + "', min=" + this.min + ", avg=" + this.avg + ", max=" + this.max + ", timeLog=" + this.timeLog + ", codec=" + this.codec + ", baseline=" + this.baseline + ", readiness=" + this.readiness + '}';
    }
}
