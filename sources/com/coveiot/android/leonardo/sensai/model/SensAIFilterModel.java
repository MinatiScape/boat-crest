package com.coveiot.android.leonardo.sensai.model;

import java.io.Serializable;
/* loaded from: classes5.dex */
public class SensAIFilterModel implements Serializable {
    private boolean isSelected;
    private String name;

    public SensAIFilterModel(String str, boolean z) {
        this.isSelected = false;
        this.name = str;
        this.isSelected = z;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
