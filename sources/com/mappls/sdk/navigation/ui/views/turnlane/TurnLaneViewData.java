package com.mappls.sdk.navigation.ui.views.turnlane;
/* loaded from: classes11.dex */
public class TurnLaneViewData {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13040a;
    public String b;

    public TurnLaneViewData(String str, String str2) {
        a(str, str2);
    }

    public final void a(String str, String str2) {
        if (str.contentEquals("uturn")) {
            this.b = "draw_lane_uturn";
            this.f13040a = true;
        } else if (str.contentEquals("straight")) {
            this.b = "draw_lane_straight";
        } else if (str.contentEquals("right")) {
            this.b = "draw_lane_right";
        } else if (str.contentEquals("left")) {
            this.b = "draw_lane_right";
            this.f13040a = true;
        } else if (str.contentEquals("slight right")) {
            this.b = "draw_lane_slight_right";
        } else if (str.contentEquals("slight left")) {
            this.b = "draw_lane_slight_right";
            this.f13040a = true;
        } else if (c(str, "right")) {
            d(str2);
        } else if (c(str, "left")) {
            d(str2);
            this.f13040a = true;
        }
    }

    public String b() {
        return this.b;
    }

    public final boolean c(String str, String str2) {
        return str.contains("straight") && str.contains(str2);
    }

    public final void d(String str) {
        this.b = str.contains("right") ? "draw_lane_right_only" : str.contains("straight") ? "draw_lane_straight_only" : "draw_lane_straight_right";
    }

    public boolean e() {
        return this.f13040a;
    }
}
