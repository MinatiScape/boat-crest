package com.coveiot.android.fitnesschallenges.utils;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class FitnessChallengeConstants {
    @NotNull
    public static final String ALL = "ALL";
    @NotNull
    public static final String BOAT_CREST_CHALLENGES = "boat_crest_challenges";
    @NotNull
    public static final String BUDDIES_CHALLENGES = "buddies_challenges";
    @NotNull
    public static final String BUDDIES_PARTICIPANTS = "BUDDIES_PARTICIPANTS";
    @NotNull
    public static final String BUDDY = "BUDDY";
    @NotNull
    public static final String CALORIES = "CALORIES";
    @NotNull
    public static final String CHALLENGE_ADD_PARTICIPANT_TYPE = "challenge_add_participant_type";
    @NotNull
    public static final String CHALLENGE_ID = "challenge_id";
    @NotNull
    public static final String CHALLENGE_SUB_TYPE = "challenge_sub_type";
    @NotNull
    public static final String CHALLENGE_SUCCESS = "challenge_successfully_created";
    @NotNull
    public static final String CHALLENGE_TYPE = "challenge_type";
    @NotNull
    public static final String COMPLETED = "COMPLETED";
    @NotNull
    public static final String COMPLETED_CHALLENGES = "completed_challenges";
    @NotNull
    public static final String CREATED = "CREATED";
    @NotNull
    public static final String EDIT_ADD_PARTICIPANT = "edit_add_participant";
    @NotNull
    public static final String EDIT_REMOVE_PARTICIPANT = "edit_remove_participant";
    @NotNull
    public static final String ENDED = "ENDED";
    @NotNull
    public static final String GLOBAL = "GLOBAL";
    @NotNull
    public static final String GLOBAL_BUDDY = "GLOBAL_BUDDY";
    public static final int GLOBAL_BUDDY_TYPE = 0;
    @NotNull
    public static final String ISFROMACHIEVEMENTS = "ISFROMACHIEVEMENTS";
    @NotNull
    public static final String ISJOINEDFROMFROMFITNESSPAGE = "ISJOINEDFROMFROMFITNESSPAGE";
    @NotNull
    public static final String ISJOINEDFROMHP = "ISJOINEDFROMHP";
    @NotNull
    public static final String ISJOINEDFROMLISTINGPAGE = "ISJOINEDFROMLISTINGPAGE";
    @NotNull
    public static final String JOINED = "JOINED";
    @NotNull
    public static final String JOINED_CHALLENGES = "joined_challenges";
    @NotNull
    public static final String JOIN_CHALLENGES = "join_challenges";
    @NotNull
    public static final String LEFT = "LEFT";
    @NotNull
    public static final String METERS = "METERS";
    public static final int MY_ACHIEVEMENT_TYPE = 2;
    @NotNull
    public static final String MY_CHALLENGES = "my_challenges";
    public static final int MY_CHALLENGE_TYPE = 1;
    @NotNull
    public static final String MY_CREATED_CHALLENGES = "my_created_challenges";
    @NotNull
    public static final String OTHER_PARTICIPANTS = "OTHER_PARTICIPANTS";
    @NotNull
    public static final String PENDING = "PENDING";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static String f4536a = "SELECTED_CONTACTS";
    @NotNull
    public static String b = "PARTICIPANTS_LIST";
    @NotNull
    public static String c = "IS_CREATED_CHALLENGE";
    @NotNull
    public static String d = "SHARE_CHALLENGE_DATA";

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getIS_CREATED_CHALLENGE() {
            return FitnessChallengeConstants.c;
        }

        @NotNull
        public final String getPARTICIPANTS_LIST() {
            return FitnessChallengeConstants.b;
        }

        @NotNull
        public final String getSELECTED_CONTACTS() {
            return FitnessChallengeConstants.f4536a;
        }

        @NotNull
        public final String getSHARE_CHALLENGE_DATA() {
            return FitnessChallengeConstants.d;
        }

        public final void setIS_CREATED_CHALLENGE(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            FitnessChallengeConstants.c = str;
        }

        public final void setPARTICIPANTS_LIST(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            FitnessChallengeConstants.b = str;
        }

        public final void setSELECTED_CONTACTS(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            FitnessChallengeConstants.f4536a = str;
        }

        public final void setSHARE_CHALLENGE_DATA(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            FitnessChallengeConstants.d = str;
        }
    }
}
