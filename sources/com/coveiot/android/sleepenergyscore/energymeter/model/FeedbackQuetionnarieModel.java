package com.coveiot.android.sleepenergyscore.energymeter.model;

import java.util.List;
/* loaded from: classes6.dex */
public class FeedbackQuetionnarieModel {

    /* renamed from: a  reason: collision with root package name */
    public String f5725a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public List<QuestionsOptionData> g = null;

    public String getCreatedDate() {
        return this.c;
    }

    public List<QuestionsOptionData> getOptions() {
        return this.g;
    }

    public String getQuestionId() {
        return this.e;
    }

    public String getQuestionnaireId() {
        return this.f5725a;
    }

    public String getSubject() {
        return this.b;
    }

    public String getText() {
        return this.f;
    }

    public String getType() {
        return this.d;
    }

    public void setCreatedDate(String str) {
        this.c = str;
    }

    public void setOptions(List<QuestionsOptionData> list) {
        this.g = list;
    }

    public void setQuestionId(String str) {
        this.e = str;
    }

    public void setQuestionnaireId(String str) {
        this.f5725a = str;
    }

    public void setSubject(String str) {
        this.b = str;
    }

    public void setText(String str) {
        this.f = str;
    }

    public void setType(String str) {
        this.d = str;
    }
}
