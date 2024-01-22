package com.coveiot.android.leonardo.sensai.model;

import java.io.Serializable;
/* loaded from: classes5.dex */
public class SensAIShareList implements Serializable {
    private String name;
    private String value;

    public SensAIShareList(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
