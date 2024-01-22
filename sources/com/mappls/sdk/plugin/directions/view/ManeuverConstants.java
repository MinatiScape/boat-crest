package com.mappls.sdk.plugin.directions.view;

import androidx.annotation.Keep;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Keep
/* loaded from: classes11.dex */
public class ManeuverConstants {
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

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ManeuverModifier {
    }

    @Keep
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ManeuverType {
    }
}
