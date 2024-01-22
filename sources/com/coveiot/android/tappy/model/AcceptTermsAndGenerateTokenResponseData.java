package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AcceptTermsAndGenerateTokenResponseData implements Serializable {
    @Nullable
    private PaymentInstrumentTokenData paymentInstrumentTokenData;
    @Nullable
    private SECardPersoScript seCardPersoScript;
    @Nullable
    private ArrayList<StepUpRequest> stepUpRequests;

    @Nullable
    public final PaymentInstrumentTokenData getPaymentInstrumentTokenData() {
        return this.paymentInstrumentTokenData;
    }

    @Nullable
    public final SECardPersoScript getSeCardPersoScript() {
        return this.seCardPersoScript;
    }

    @Nullable
    public final ArrayList<StepUpRequest> getStepUpRequests() {
        return this.stepUpRequests;
    }

    public final void setPaymentInstrumentTokenData(@Nullable PaymentInstrumentTokenData paymentInstrumentTokenData) {
        this.paymentInstrumentTokenData = paymentInstrumentTokenData;
    }

    public final void setSeCardPersoScript(@Nullable SECardPersoScript sECardPersoScript) {
        this.seCardPersoScript = sECardPersoScript;
    }

    public final void setStepUpRequests(@Nullable ArrayList<StepUpRequest> arrayList) {
        this.stepUpRequests = arrayList;
    }
}
