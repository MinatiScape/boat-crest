package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SConfirmProvisioningResponse implements Serializable {
    @SerializedName("APDUCommands")
    @Expose
    private ArrayList<SApduCommand> aPDUCommands;
    @SerializedName("PaymentInstrumentToken")
    @Expose
    private SPaymentInstrumentTokens paymentInstrumentToken;

    public SPaymentInstrumentTokens getPaymentInstrumentToken() {
        return this.paymentInstrumentToken;
    }

    public ArrayList<SApduCommand> getaPDUCommands() {
        return this.aPDUCommands;
    }

    public void setPaymentInstrumentToken(SPaymentInstrumentTokens sPaymentInstrumentTokens) {
        this.paymentInstrumentToken = sPaymentInstrumentTokens;
    }

    public void setaPDUCommands(ArrayList<SApduCommand> arrayList) {
        this.aPDUCommands = arrayList;
    }
}
