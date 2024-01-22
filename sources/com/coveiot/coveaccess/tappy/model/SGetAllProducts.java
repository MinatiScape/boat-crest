package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetAllProducts implements Serializable {
    private boolean includeDeleted;

    public boolean isIncludeDeleted() {
        return this.includeDeleted;
    }

    public void setIncludeDeleted(boolean z) {
        this.includeDeleted = z;
    }
}
