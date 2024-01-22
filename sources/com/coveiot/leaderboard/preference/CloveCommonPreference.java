package com.coveiot.leaderboard.preference;
/* loaded from: classes9.dex */
public enum CloveCommonPreference implements SavedPreference {
    IS_FIRST_TIME_SHOWN_LEADER_BOARD("is_leader_board_show"),
    LEADER_BOARD_ALL_BADGES("all_badges"),
    LEADER_BOARD_DAILY_BADGES("daily_badges"),
    LEADER_BOARD_SPECIAL_BADGES("special_badges"),
    LEADER_BOARD_MY_BADGES("my_badges"),
    LEADER_BADGES_LEVEL("badges_level"),
    MY_RANK("my_rank"),
    IS_LOCATION_ONBOARDING_DONE("is_location_onboarding_done"),
    IS_TIMELINE_ONBOARD_DONE("is_timeline_onboard_done"),
    IS_FRAGMENT_ADDRESS("is_fragment_Address"),
    ADDRESS_JSON("address_josn");
    
    private String name;

    CloveCommonPreference(String str) {
        this.name = str;
    }

    @Override // com.coveiot.leaderboard.preference.SavedPreference
    public String getName() {
        return this.name;
    }

    @Override // com.coveiot.leaderboard.preference.SavedPreference
    public PreferenceType getPreferenceType() {
        return PreferenceType.CLOVE_COMMON_APP;
    }
}
