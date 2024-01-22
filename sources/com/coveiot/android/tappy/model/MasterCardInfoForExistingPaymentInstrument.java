package com.coveiot.android.tappy.model;

import com.coveiot.android.tappy.utils.Constants;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class MasterCardInfoForExistingPaymentInstrument implements Serializable {
    @Nullable
    private CardAccountDataOnlySecurityCode cardAccountData;
    @NotNull
    private String source = Constants.ACCOUNT_ADDED_MANUALLY;

    @Nullable
    public final CardAccountDataOnlySecurityCode getCardAccountData() {
        return this.cardAccountData;
    }

    @NotNull
    public final String getSource() {
        return this.source;
    }

    public final void setCardAccountData(@Nullable CardAccountDataOnlySecurityCode cardAccountDataOnlySecurityCode) {
        this.cardAccountData = cardAccountDataOnlySecurityCode;
    }

    public final void setSource(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.source = str;
    }
}
