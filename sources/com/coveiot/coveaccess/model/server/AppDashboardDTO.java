package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class AppDashboardDTO {
    @SerializedName("activeVitalCards")
    private List<String> activeVitalCards;

    public List<String> getActiveVitalCards() {
        return this.activeVitalCards;
    }

    public void setActiveVitalCards(List<String> list) {
        this.activeVitalCards = list;
    }

    public String toString() {
        return "AppDashboardDTO{activeVitalCards=" + this.activeVitalCards + '}';
    }
}
