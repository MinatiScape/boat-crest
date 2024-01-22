package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum SwimmingStyleEnum {
    MAIN_STROKE("MAIN_STROKE"),
    MEDLEY("MEDLEY"),
    FREE_STYLE("FREE_STYLE"),
    BREAST_STROKE("BREAST_STROKE"),
    BACK_STROKE("BACK_STROKE"),
    BUTTERFLY("BUTTERFLY");
    
    private String swimStyle;

    SwimmingStyleEnum(String str) {
        this.swimStyle = str;
    }

    public String getSwimStyle() {
        return this.swimStyle;
    }
}
