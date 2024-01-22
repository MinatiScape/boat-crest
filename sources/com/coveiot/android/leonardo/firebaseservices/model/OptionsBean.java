package com.coveiot.android.leonardo.firebaseservices.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class OptionsBean implements Serializable {
    @SerializedName("optionId")
    @Expose
    private String optionId;
    @SerializedName("value")
    @Expose
    private String value;

    public String getOptionId() {
        return this.optionId;
    }

    public String getValue() {
        return this.value;
    }

    public void setOptionId(String str) {
        this.optionId = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
