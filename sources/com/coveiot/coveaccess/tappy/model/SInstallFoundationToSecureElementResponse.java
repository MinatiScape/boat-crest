package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SInstallFoundationToSecureElementResponse implements Serializable {
    @SerializedName("ApduCommands")
    @Expose
    private ArrayList<SApduCommand> apduCommands;
    @SerializedName("NextCommandSetIndex")
    @Expose
    private int nextCommandSetIndex;
    @SerializedName("NextSecureChannelSelectAIDCommand")
    @Expose
    private String nextSecureChannelSelectAIDCommand;
    @SerializedName("PaymentApplicationAID")
    @Expose
    private String paymentApplicationAID;

    public ArrayList<SApduCommand> getApduCommands() {
        return this.apduCommands;
    }

    public int getNextCommandSetIndex() {
        return this.nextCommandSetIndex;
    }

    public String getNextSecureChannelSelectAIDCommand() {
        return this.nextSecureChannelSelectAIDCommand;
    }

    public String getPaymentApplicationAID() {
        return this.paymentApplicationAID;
    }

    public void setApduCommands(ArrayList<SApduCommand> arrayList) {
        this.apduCommands = arrayList;
    }

    public void setNextCommandSetIndex(int i) {
        this.nextCommandSetIndex = i;
    }

    public void setNextSecureChannelSelectAIDCommand(String str) {
        this.nextSecureChannelSelectAIDCommand = str;
    }

    public void setPaymentApplicationAID(String str) {
        this.paymentApplicationAID = str;
    }
}
