package com.mappls.sdk.navigation.ui.views;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public final class NavigationConstants {
    public static final long ALERT_VIEW_PROBLEM_DURATION = 10000;
    public static final long FEEDBACK_BOTTOM_SHEET_DURATION = 10000;
    public static final String FEEDBACK_SUBMITTED = "Feedback Submitted";
    public static final int MANEUVER_ZONE_RADIUS = 40;
    public static final double METERS_REMAINING_TILL_ARRIVAL = 40.0d;
    public static final double MINIMUM_BACKUP_DISTANCE_FOR_OFF_ROUTE = 50.0d;
    public static final double MINIMUM_DISTANCE_BEFORE_REROUTING = 50.0d;
    public static final int NAVIGATION_NOTIFICATION_ID = 5678;
    public static final String NAVIGATION_VIEW_DARK_THEME = "navigation_view_dark_theme";
    public static final String NAVIGATION_VIEW_LIGHT_THEME = "navigation_view_light_theme";
    public static final String NAVIGATION_VIEW_OFF_ROUTE_ENABLED_KEY = "navigation_view_off_route_enabled";
    public static final String NAVIGATION_VIEW_PREFERENCE_SET_THEME = "navigation_view_theme_preference";
    public static final String NAVIGATION_VIEW_ROUTE_KEY = "route_json";
    public static final String NAVIGATION_VIEW_ROUTE_PROFILE_KEY = "navigation_view_route_profile";
    public static final String NAVIGATION_VIEW_SIMULATE_ROUTE = "navigation_view_simulate_route";
    public static final String NAVIGATION_VIEW_SNAP_ENABLED_KEY = "navigation_view_snap_enabled";
    public static final String REPORT_PROBLEM = "Report Problem";
    public static final String STEP_MANEUVER_MODIFIER_LEFT = "left";
    public static final String STEP_MANEUVER_MODIFIER_RIGHT = "right";
    public static final String STEP_MANEUVER_MODIFIER_SHARP_LEFT = "sharp left";
    public static final String STEP_MANEUVER_MODIFIER_SHARP_RIGHT = "sharp right";
    public static final String STEP_MANEUVER_MODIFIER_SLIGHT_LEFT = "slight left";
    public static final String STEP_MANEUVER_MODIFIER_SLIGHT_RIGHT = "slight right";
    public static final String STEP_MANEUVER_MODIFIER_STRAIGHT = "straight";
    public static final String STEP_MANEUVER_MODIFIER_STRAIGHT_21 = "straight_21";
    public static final String STEP_MANEUVER_MODIFIER_UTURN = "uturn";
    public static final String STEP_MANEUVER_TYPE_ARRIVE = "arrive";
    public static final String STEP_MANEUVER_TYPE_CONTINUE = "continue";
    public static final String STEP_MANEUVER_TYPE_DEPART = "depart";
    public static final String STEP_MANEUVER_TYPE_END_OF_ROAD = "end of road";
    public static final String STEP_MANEUVER_TYPE_EXIT_ROTARY = "exit rotary";
    public static final String STEP_MANEUVER_TYPE_EXIT_ROUNDABOUT = "exit roundabout";
    public static final String STEP_MANEUVER_TYPE_FORK = "fork";
    public static final String STEP_MANEUVER_TYPE_MERGE = "merge";
    public static final String STEP_MANEUVER_TYPE_NEW_NAME = "new name";
    public static final String STEP_MANEUVER_TYPE_NOTIFICATION = "notification";
    public static final String STEP_MANEUVER_TYPE_OFF_RAMP = "off ramp";
    public static final String STEP_MANEUVER_TYPE_ON_RAMP = "on ramp";
    public static final String STEP_MANEUVER_TYPE_ROTARY = "rotary";
    public static final String STEP_MANEUVER_TYPE_ROUNDABOUT = "roundabout";
    public static final String STEP_MANEUVER_TYPE_ROUNDABOUT_TURN = "roundabout turn";
    public static final String STEP_MANEUVER_TYPE_TURN = "turn";
    public static final String TURN_LANE_INDICATION_LEFT = "left";
    public static final String TURN_LANE_INDICATION_RIGHT = "right";
    public static final String TURN_LANE_INDICATION_SLIGHT_LEFT = "slight left";
    public static final String TURN_LANE_INDICATION_SLIGHT_RIGHT = "slight right";
    public static final String TURN_LANE_INDICATION_STRAIGHT = "straight";
    public static final String TURN_LANE_INDICATION_UTURN = "uturn";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ManeuverModifier {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ManeuverType {
    }
}
