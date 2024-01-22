package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class InstallFoundationData implements Serializable {
    @Nullable
    private List<ApduCommand> apduCommands;
    @Nullable
    private Integer nextCommandSetIndex;
    @Nullable
    private String nextSecureChannelSelectAIDCommand;
    @Nullable
    private String paymentApplicationAID;

    @Nullable
    public final List<ApduCommand> getApduCommands() {
        return this.apduCommands;
    }

    @Nullable
    public final Integer getNextCommandSetIndex() {
        return this.nextCommandSetIndex;
    }

    @Nullable
    public final String getNextSecureChannelSelectAIDCommand() {
        return this.nextSecureChannelSelectAIDCommand;
    }

    @Nullable
    public final String getPaymentApplicationAID() {
        return this.paymentApplicationAID;
    }

    public final void setApduCommands(@Nullable List<ApduCommand> list) {
        this.apduCommands = list;
    }

    public final void setNextCommandSetIndex(@Nullable Integer num) {
        this.nextCommandSetIndex = num;
    }

    public final void setNextSecureChannelSelectAIDCommand(@Nullable String str) {
        this.nextSecureChannelSelectAIDCommand = str;
    }

    public final void setPaymentApplicationAID(@Nullable String str) {
        this.paymentApplicationAID = str;
    }
}
