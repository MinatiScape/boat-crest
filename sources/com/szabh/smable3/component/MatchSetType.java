package com.szabh.smable3.component;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class MatchSetType {
    public static final int CLASSIC_MATCH = 1;
    public static final int GUEST_TEAM_PLAYER_LIST = 5;
    public static final int HOME_TEAM_PLAYER_LIST = 4;
    @NotNull
    public static final MatchSetType INSTANCE = new MatchSetType();
    public static final int INTERVAL_TRAINING = 3;
    public static final int PRO_MATCH = 2;
    public static final int YOUTH_MATCH = 0;

    private MatchSetType() {
    }
}
