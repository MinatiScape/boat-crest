package com.coveiot.coveaccess.fitnessbuddies;
/* loaded from: classes8.dex */
public enum ReactionType {
    CHEER("CHEER"),
    APPLAUD("APPLAUD"),
    NUDGE("NUDGE");
    
    private String reaction;

    ReactionType(String str) {
        this.reaction = str;
    }

    public String getAction() {
        return this.reaction;
    }
}
