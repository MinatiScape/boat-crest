package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ConfirmProvisioningResponseData implements Serializable {
    @Nullable
    private List<ApduCommand> apduCommands;
    @Nullable
    private PaymentInstrumentTokenData paymentInstrumentToken;

    @Nullable
    public final List<ApduCommand> getApduCommands() {
        return this.apduCommands;
    }

    @Nullable
    public final PaymentInstrumentTokenData getPaymentInstrumentToken() {
        return this.paymentInstrumentToken;
    }

    public final void setApduCommands(@Nullable List<ApduCommand> list) {
        this.apduCommands = list;
    }

    public final void setPaymentInstrumentToken(@Nullable PaymentInstrumentTokenData paymentInstrumentTokenData) {
        this.paymentInstrumentToken = paymentInstrumentTokenData;
    }
}
