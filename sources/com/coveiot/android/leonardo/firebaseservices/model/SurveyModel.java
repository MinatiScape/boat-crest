package com.coveiot.android.leonardo.firebaseservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
/* loaded from: classes2.dex */
public class SurveyModel implements Serializable {
    @SerializedName("options")
    @Expose
    private List<OptionsBean> options;
    @SerializedName("questionId")
    @Expose
    private String questionId;
    @SerializedName("text")
    @Expose
    private String text;

    public List<OptionsBean> getOptions() {
        return this.options;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public String getText() {
        return this.text;
    }

    public void setOptions(List<OptionsBean> list) {
        this.options = list;
    }

    public void setQuestionId(String str) {
        this.questionId = str;
    }

    public void setText(String str) {
        this.text = str;
    }
}
