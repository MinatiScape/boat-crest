package com.coveiot.android.tappy.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class MasterCardPaymentCardInfo implements Serializable {
    @Nullable
    private CardAccountData cardAccountData;
    @Nullable
    private String source;

    @Nullable
    public final CardAccountData getCardAccountData() {
        return this.cardAccountData;
    }

    @Nullable
    public final String getSource() {
        return this.source;
    }

    public final void setCardAccountData(@Nullable CardAccountData cardAccountData) {
        this.cardAccountData = cardAccountData;
    }

    public final void setSource(@Nullable String str) {
        this.source = str;
    }
}
