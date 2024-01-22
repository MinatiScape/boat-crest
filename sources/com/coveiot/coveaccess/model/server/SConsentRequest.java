package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SConsentRequest {
    @SerializedName("docOnlineConsent")
    @Expose
    private Boolean docOnlineConsent;
    @SerializedName("invalidateSession")
    @Expose
    private Boolean invalidateSession;
    @SerializedName("monitoringConsent")
    @Expose
    private Boolean monitoringConsent;
    @SerializedName("sportsConsent")
    @Expose
    private Boolean sportsConsent;
    @SerializedName("stravaConsent")
    @Expose
    private Boolean stravaConsent;

    public Boolean getDocOnlineConsent() {
        return this.docOnlineConsent;
    }

    public Boolean getInvalidateSession() {
        return this.invalidateSession;
    }

    public Boolean getMonitoringConsent() {
        return this.monitoringConsent;
    }

    public Boolean getSportsConsent() {
        return this.sportsConsent;
    }

    public Boolean getStravaConsent() {
        return this.stravaConsent;
    }

    public void setDocOnlineConsent(Boolean bool) {
        this.docOnlineConsent = bool;
    }

    public void setInvalidateSession(Boolean bool) {
        this.invalidateSession = bool;
    }

    public void setMonitoringConsent(Boolean bool) {
        this.monitoringConsent = bool;
    }

    public void setSportsConsent(Boolean bool) {
        this.sportsConsent = bool;
    }

    public void setStravaConsent(Boolean bool) {
        this.stravaConsent = bool;
    }
}
