package com.coveiot.android.tappy.model;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum TokenProvisioningProcessStatus {
    NOT_PROVISIONED("NotProvisioned"),
    PROVISIONING("Provisioning"),
    PROVISION_PERSO_SCRIPT_EXECUTED("Provisioned_PersoScriptExecuted"),
    PROVISIONED("Provisioned");
    
    @NotNull
    private String status;

    TokenProvisioningProcessStatus(String str) {
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
