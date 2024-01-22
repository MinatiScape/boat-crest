package com.coveiot.android.fitnesschallenges;

import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeGoalType;
import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ChallengeShareData implements Serializable {
    @Nullable
    private String challengeDesc;
    @Nullable
    private String challengeEndDate;
    @Nullable
    private Integer challengeGoal;
    @Nullable
    private FitnessChallengeGoalType challengeGoalType;
    @Nullable
    private String challengeId;
    @Nullable
    private Integer challengeProgress;
    @Nullable
    private String challengeStartDate;
    @Nullable
    private String challengeType;
    @Nullable
    private String challengetitle;
    @Nullable
    private String createdBy;
    private boolean isCreator;
    @Nullable
    private Integer participantsInChallenge;

    @Nullable
    public final String getChallengeDesc() {
        return this.challengeDesc;
    }

    @Nullable
    public final String getChallengeEndDate() {
        return this.challengeEndDate;
    }

    @Nullable
    public final Integer getChallengeGoal() {
        return this.challengeGoal;
    }

    @Nullable
    public final FitnessChallengeGoalType getChallengeGoalType() {
        return this.challengeGoalType;
    }

    @Nullable
    public final String getChallengeId() {
        return this.challengeId;
    }

    @Nullable
    public final Integer getChallengeProgress() {
        return this.challengeProgress;
    }

    @Nullable
    public final String getChallengeStartDate() {
        return this.challengeStartDate;
    }

    @Nullable
    public final String getChallengeType() {
        return this.challengeType;
    }

    @Nullable
    public final String getChallengetitle() {
        return this.challengetitle;
    }

    @Nullable
    public final String getCreatedBy() {
        return this.createdBy;
    }

    @Nullable
    public final Integer getParticipantsInChallenge() {
        return this.participantsInChallenge;
    }

    public final boolean isCreator() {
        return this.isCreator;
    }

    public final void setChallengeDesc(@Nullable String str) {
        this.challengeDesc = str;
    }

    public final void setChallengeEndDate(@Nullable String str) {
        this.challengeEndDate = str;
    }

    public final void setChallengeGoal(@Nullable Integer num) {
        this.challengeGoal = num;
    }

    public final void setChallengeGoalType(@Nullable FitnessChallengeGoalType fitnessChallengeGoalType) {
        this.challengeGoalType = fitnessChallengeGoalType;
    }

    public final void setChallengeId(@Nullable String str) {
        this.challengeId = str;
    }

    public final void setChallengeProgress(@Nullable Integer num) {
        this.challengeProgress = num;
    }

    public final void setChallengeStartDate(@Nullable String str) {
        this.challengeStartDate = str;
    }

    public final void setChallengeType(@Nullable String str) {
        this.challengeType = str;
    }

    public final void setChallengetitle(@Nullable String str) {
        this.challengetitle = str;
    }

    public final void setCreatedBy(@Nullable String str) {
        this.createdBy = str;
    }

    public final void setCreator(boolean z) {
        this.isCreator = z;
    }

    public final void setParticipantsInChallenge(@Nullable Integer num) {
        this.participantsInChallenge = num;
    }
}
