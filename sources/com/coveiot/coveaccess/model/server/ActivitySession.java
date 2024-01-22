package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
/* loaded from: classes8.dex */
public class ActivitySession {
    @SerializedName("enabledTypes")
    private List<EnabledTypeSports> enabledTypes = null;

    public List<EnabledTypeSports> getEnabledTypes() {
        return this.enabledTypes;
    }

    public void setEnabledTypes(List<EnabledTypeSports> list) {
        this.enabledTypes = list;
    }
}
