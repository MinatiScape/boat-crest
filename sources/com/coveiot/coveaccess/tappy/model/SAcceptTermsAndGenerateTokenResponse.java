package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SAcceptTermsAndGenerateTokenResponse implements Serializable {
    @SerializedName("StepUpRequests")
    @Expose
    private ArrayList<SStepUpRequest> StepUpRequests;
    @SerializedName("PaymentInstrumentToken")
    @Expose
    private SPaymentInstrumentTokens paymentInstrumentToken;
    @SerializedName("SECardPersoScript")
    @Expose
    private SSECardPersoScript sECardPersoScript;

    public SPaymentInstrumentTokens getPaymentInstrumentToken() {
        return this.paymentInstrumentToken;
    }

    public ArrayList<SStepUpRequest> getStepUpRequests() {
        return this.StepUpRequests;
    }

    public SSECardPersoScript getsECardPersoScript() {
        return this.sECardPersoScript;
    }

    public void setPaymentInstrumentToken(SPaymentInstrumentTokens sPaymentInstrumentTokens) {
        this.paymentInstrumentToken = sPaymentInstrumentTokens;
    }

    public void setStepUpRequests(ArrayList<SStepUpRequest> arrayList) {
        this.StepUpRequests = arrayList;
    }

    public void setsECardPersoScript(SSECardPersoScript sSECardPersoScript) {
        this.sECardPersoScript = sSECardPersoScript;
    }
}
