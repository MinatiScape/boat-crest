package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SECardPersoScript implements Serializable {
    @Nullable
    private ArrayList<APDULine> aPDULines;
    @Nullable
    private String appletInstanceAID;
    @Nullable
    private Boolean isTokenPersoScriptPending;
    @Nullable
    private String priorityCode;

    @Nullable
    public final ArrayList<APDULine> getAPDULines() {
        return this.aPDULines;
    }

    @Nullable
    public final String getAppletInstanceAID() {
        return this.appletInstanceAID;
    }

    @Nullable
    public final String getPriorityCode() {
        return this.priorityCode;
    }

    @Nullable
    public final Boolean isTokenPersoScriptPending() {
        return this.isTokenPersoScriptPending;
    }

    public final void setAPDULines(@Nullable ArrayList<APDULine> arrayList) {
        this.aPDULines = arrayList;
    }

    public final void setAppletInstanceAID(@Nullable String str) {
        this.appletInstanceAID = str;
    }

    public final void setPriorityCode(@Nullable String str) {
        this.priorityCode = str;
    }

    public final void setTokenPersoScriptPending(@Nullable Boolean bool) {
        this.isTokenPersoScriptPending = bool;
    }
}
