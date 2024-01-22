package com.coveiot.android.tappy.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum PaymentInstrumentTokenStatus {
    PI_ADDED("PIAdded"),
    TERMS_ACCEPTED("TermsAccepted"),
    INACTIVE("Inactive"),
    ACTIVE("Active"),
    SUSPENDED("Suspended"),
    DELETED("Deleted");
    
    @NotNull
    private String status;

    PaymentInstrumentTokenStatus(String str) {
        this.status = str;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.status = str;
    }
}
