package com.mappls.sdk.navigation.routing;

import android.text.Html;
import android.text.Spanned;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.StepManeuver;
/* loaded from: classes11.dex */
public class NavigationStep {

    /* renamed from: a  reason: collision with root package name */
    public final com.mappls.sdk.navigation.router.c f12944a;
    public int afterLeftTime;
    public float d;
    public int distance;
    public String e;
    public int f;
    public String g;
    public String h;
    public int i;
    public NavLocation j;
    public Object k;
    public Object l;
    public boolean m;
    public int routePointOffset;
    public int routeEndPointOffset = 0;
    public String b = "";
    public String c = "";

    public NavigationStep(float f, com.mappls.sdk.navigation.router.c cVar) {
        this.d = f == 0.0f ? 1.0f : f;
        this.f12944a = cVar;
    }

    public NavigationStep(float f, String str, Float f2, int[] iArr, boolean z) {
        com.mappls.sdk.navigation.router.c a2 = str != null ? com.mappls.sdk.navigation.router.c.a(str.toUpperCase(), z) : com.mappls.sdk.navigation.router.c.h();
        if (f2 != null) {
            a2.a(f2.floatValue());
        }
        if (iArr != null && iArr.length > 0) {
            a2.a(iArr);
        }
        this.d = f == 0.0f ? 1.0f : f;
        this.f12944a = a2;
    }

    public LegStep a(String str, NavLocation navLocation, String str2, boolean z, boolean z2) {
        LegStep.Builder builder = LegStep.builder();
        builder.distance(getDistance());
        StepManeuver.Builder builder2 = StepManeuver.builder();
        builder2.modifier(b());
        if (z2) {
            builder2.type("depart");
            builder2.bearingAfter(Double.valueOf(Math.abs(this.f12944a.b())));
        } else if (z) {
            builder2.type("arrive");
            builder2.modifier(null);
        } else {
            builder2.type(getTurnTypeString());
        }
        builder2.instruction(this.c);
        builder2.rawLocation(new double[]{navLocation.getLongitude(), navLocation.getLatitude()});
        if (this.f12944a.e()) {
            builder2.exit(Integer.valueOf(this.f12944a.a()));
            builder2.degree(Double.valueOf(this.f12944a.b() > 0.0f ? this.f12944a.b() : 180.0f));
        }
        builder.maneuver(builder2.build());
        builder.duration(getExpectedTime());
        builder.weight(getExpectedTime());
        builder.geometry(str2);
        builder.mode(str);
        builder.name(this.g);
        return builder.build();
    }

    public String b() {
        Object obj = this.k;
        if (obj == null || !(obj instanceof LegStep)) {
            int c = this.f12944a.c();
            if (c != 19) {
                if (c != 20) {
                    if (c != 34) {
                        if (c != 35) {
                            if (c != 41) {
                                if (c == 68 || c == 69) {
                                    return "roundabout";
                                }
                                switch (c) {
                                    case 0:
                                        return "left";
                                    case 1:
                                        return "sharp left";
                                    case 2:
                                        return "slight left";
                                    case 3:
                                        return "right";
                                    case 4:
                                        return "sharp right";
                                    case 5:
                                        return "slight right";
                                    case 6:
                                        return "uturn";
                                    default:
                                        switch (c) {
                                            case 9:
                                            case 11:
                                            case 13:
                                            case 15:
                                                return "left";
                                            case 10:
                                            case 12:
                                            case 14:
                                            case 16:
                                                return "right";
                                            default:
                                                return "straight";
                                        }
                                }
                            }
                            return "uturn";
                        }
                        return "right";
                    }
                    return "left";
                }
                return "right";
            }
            return "left";
        }
        return ((LegStep) obj).maneuver().modifier();
    }

    public boolean c(String str) {
        char[] charArray;
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && ((c < '0' || c > '9') && c != ' '))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((NavigationStep) obj).b);
    }

    public float getAverageSpeed() {
        return this.d;
    }

    public String getDescriptionRoute(NavigationApplication navigationApplication) {
        if (!this.b.endsWith(NavigationFormatter.getFormattedDistance(this.distance, navigationApplication))) {
            this.b += HexStringBuilder.DEFAULT_SEPARATOR + NavigationFormatter.getFormattedDistance(this.distance, navigationApplication);
        }
        return this.b.trim();
    }

    public String getDescriptionRoute(NavigationApplication navigationApplication, int i) {
        float f;
        if (!this.b.endsWith(NavigationFormatter.getFormattedDistance(i, navigationApplication))) {
            this.b += HexStringBuilder.DEFAULT_SEPARATOR + NavigationFormatter.getFormattedDistance(f, navigationApplication);
        }
        return this.b.trim();
    }

    public String getDescriptionRoutePart() {
        return Html.fromHtml(this.b).toString();
    }

    public Spanned getDescriptionRoutePartHTML() {
        return Html.fromHtml(this.b);
    }

    public String getDestinationName() {
        return this.h;
    }

    public int getDistance() {
        return this.distance;
    }

    public float getExitAngle() {
        return this.f12944a.b();
    }

    public int getExitNumber() {
        return this.f12944a.a();
    }

    public int getExpectedTime() {
        return Math.round(this.distance / this.d);
    }

    public Object getExtraInfo() {
        return this.k;
    }

    public int getManeuverID() {
        return this.f;
    }

    public NavLocation getNavLocation() {
        return this.j;
    }

    public Object getNextExtraInfo() {
        return this.l;
    }

    public int getPosition() {
        return this.i;
    }

    public String getRef() {
        return this.e;
    }

    public String getShortInstruction() {
        return this.c;
    }

    public String getStreetName() {
        return this.g;
    }

    public float getTurnAngle() {
        return this.f12944a.b();
    }

    public com.mappls.sdk.navigation.router.c getTurnType() {
        return this.f12944a;
    }

    public int getTurnTypeConstants() {
        return this.f12944a.c();
    }

    public String getTurnTypeString() {
        Object obj = this.k;
        if (obj == null || !(obj instanceof LegStep)) {
            int c = this.f12944a.c();
            if (c == 19 || c == 20) {
                return "fork";
            }
            if (c == 34 || c == 35 || c == 41) {
                return "turn";
            }
            if (c == 68 || c == 69) {
                return "roundabout";
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return "turn";
                default:
                    switch (c) {
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                            return "turn";
                        case 15:
                        case 16:
                            return "fork";
                        default:
                            return "continue";
                    }
            }
        }
        return ((LegStep) obj).maneuver().type();
    }

    public int getTurnValue() {
        return this.f12944a.c();
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean isDestination() {
        return this.m;
    }

    public boolean isRoundAbout() {
        return this.f12944a.e();
    }

    public void setAverageSpeed(float f) {
        if (f == 0.0f) {
            f = 1.0f;
        }
        this.d = f;
    }

    public void setDescriptionRoute(String str) {
        this.b = str;
    }

    public void setDestination(boolean z) {
        this.m = z;
    }

    public void setDestinationName(String str) {
        this.h = str;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setExtraInfo(Object obj) {
        this.k = obj;
    }

    public void setManeuverID(int i) {
        this.f = i;
    }

    public void setNavLocation(NavLocation navLocation) {
        this.j = navLocation;
    }

    public void setNextExtraInfo(Object obj) {
        this.l = obj;
    }

    public void setPosition(int i) {
        this.i = i;
    }

    public void setRef(String str) {
        this.e = str;
    }

    public void setShortInstruction(String str) {
        this.c = str;
    }

    public void setStreetName(String str) {
        if (c(str)) {
            this.g = str;
        }
    }

    public void setTurnAngle(float f) {
        this.f12944a.a(f);
    }

    public LatLng toLatLng() {
        return new LatLng(this.j.getLatitude(), this.j.getLongitude(), this.j.getAltitude());
    }

    public Point toPoint() {
        return Point.fromLngLat(this.j.getLongitude(), this.j.getLatitude(), this.j.getAltitude());
    }

    public String toString() {
        StringBuilder a2 = com.mappls.sdk.navigation.h.a("NavigationStep{routePointOffset=");
        a2.append(this.routePointOffset);
        a2.append(", routeEndPointOffset=");
        a2.append(this.routeEndPointOffset);
        a2.append(", afterLeftTime=");
        a2.append(this.afterLeftTime);
        a2.append(", distance=");
        a2.append(this.distance);
        a2.append(", turnType=");
        a2.append(this.f12944a);
        a2.append(", descriptionRoute='");
        a2.append(this.b);
        a2.append('\'');
        a2.append(", shortInstruction='");
        a2.append(this.c);
        a2.append('\'');
        a2.append(", averageSpeed=");
        a2.append(this.d);
        a2.append(", ref='");
        a2.append(this.e);
        a2.append('\'');
        a2.append(", maneuverID=");
        a2.append(this.f);
        a2.append(", streetName='");
        a2.append(this.g);
        a2.append('\'');
        a2.append(", destinationName='");
        a2.append(this.h);
        a2.append('\'');
        a2.append(", position=");
        a2.append(this.i);
        a2.append(", navLocation=");
        a2.append(this.j);
        a2.append(", extraInfo=");
        a2.append(this.k);
        a2.append(", nextExtraInfo=");
        a2.append(this.l);
        a2.append(", isDestination=");
        a2.append(this.m);
        a2.append('}');
        return a2.toString();
    }
}
