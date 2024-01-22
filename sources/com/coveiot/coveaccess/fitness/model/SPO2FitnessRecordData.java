package com.coveiot.coveaccess.fitness.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SPO2FitnessRecordData extends FitnessRecordData {
    @SerializedName("value")
    @Expose
    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
