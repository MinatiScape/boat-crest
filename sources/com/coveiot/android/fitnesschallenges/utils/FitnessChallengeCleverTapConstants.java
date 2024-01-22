package com.coveiot.android.fitnesschallenges.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public enum FitnessChallengeCleverTapConstants {
    FITNESS_CHALLENGE_SOURCE("Source"),
    FITNESS_CHALLENGE_CHALLENGE_ID("Challenge ID"),
    FITNESS_CHALLENGE_CHALLENGE_NAME("Challenge name"),
    FITNESS_CHALLENGE_CHALLENGE_DESCRIPTION("Challenge description"),
    FITNESS_CHALLENGE_CHALLENGE_TYPE("Challenge Type"),
    FITNESS_CHALLENGE_CHALLENGE_CREATOR("Challenge Creator"),
    FITNESS_CHALLENGE_GOAL_DISTANCE("Goal distance"),
    FITNESS_CHALLENGE_GOAL_CALORIE("Goal calorie"),
    FITNESS_CHALLENGE_CHALLENGE_START_DATE("Challenge start date"),
    FITNESS_CHALLENGE_CHALLENGE_END_DATE("Challenge end date"),
    FITNESS_CHALLENGE_CHALLENGE_PARTICIPANT_COUNT("Challenge participant count"),
    FITNESS_CHALLENGE_JOINED_LOCATION("Joined location"),
    FITNESS_CHALLENGE_DETAILS_PAGE("Details page"),
    FITNESS_CHALLENGE_LISTING_PAGE("Listing page"),
    FITNESS_CHALLENGE_HP("Hp"),
    FITNESS_CHALLENGE_FITNESS_PAGE("FitnessPage"),
    FITNESS_CHALLENGE_SYSTEM("system"),
    FITNESS_CHALLENGE_CUSTOM("custom"),
    FITNESS_CHALLENGE_USER("user"),
    FITNESS_CHALLENGE_SELF("self"),
    PARTICIPANT_ADDED_WHEN_SOURCE("Participant added when source"),
    AFTER_CREATION("After Creation"),
    PARTICIPANTS_ADDED_FROM_SOURCE("Participants added from source"),
    FITNESS_CHALLENGE_DETAIL_SREEN("Details Sreen"),
    FITNESS_CHALLENGE_VIEW_ALL_PARTICIPANT("View All Participant"),
    PERCENT_OF_CHALLENGE_COMPLETED("Percent of challenge completed"),
    NA("NA");
    
    @NotNull
    private String value;

    FitnessChallengeCleverTapConstants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
