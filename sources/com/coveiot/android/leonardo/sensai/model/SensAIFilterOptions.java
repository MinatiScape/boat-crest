package com.coveiot.android.leonardo.sensai.model;

import java.io.Serializable;
/* loaded from: classes5.dex */
public class SensAIFilterOptions implements Serializable {
    private String name;

    public SensAIFilterOptions(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
