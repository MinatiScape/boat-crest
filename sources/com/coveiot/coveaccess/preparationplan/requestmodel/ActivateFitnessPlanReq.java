package com.coveiot.coveaccess.preparationplan.requestmodel;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class ActivateFitnessPlanReq implements Serializable {
    private String activationDate;
    private String categoryChosen;
    private String planTemplateId;

    public ActivateFitnessPlanReq(String str) {
        this.planTemplateId = str;
    }

    public void setActivationDate(String str) {
        this.activationDate = str;
    }

    public void setCategoryChosen(String str) {
        this.categoryChosen = str;
    }
}
