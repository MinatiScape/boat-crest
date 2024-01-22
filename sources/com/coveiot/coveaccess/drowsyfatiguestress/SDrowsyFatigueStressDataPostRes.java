package com.coveiot.coveaccess.drowsyfatiguestress;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class SDrowsyFatigueStressDataPostRes extends CoveApiResponseBaseModel {
    @SerializedName("class_prob_percent")
    @Expose
    private List<Integer> classProbPercent;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("result_type")
    @Expose
    private Integer type;

    public SDrowsyFatigueStressDataPostRes(int i) {
        super(i);
        this.classProbPercent = null;
    }

    public List<Integer> getClassProbPercent() {
        return this.classProbPercent;
    }

    public String getResult() {
        return this.result;
    }

    public Integer getType() {
        return this.type;
    }

    public void setClassProbPercent(List<Integer> list) {
        this.classProbPercent = list;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public void setType(Integer num) {
        this.type = num;
    }
}
