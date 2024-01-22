package com.mappls.sdk.navigation.voice;

import android.text.TextUtils;
import com.alibaba.fastjson.parser.JSONLexer;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.textinstructions.TextInstructionHelper;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.ArrayList;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final c f13047a;
    public ArrayList b = new ArrayList();

    public b(a aVar) {
        this.f13047a = aVar;
    }

    public static String k(int i) {
        switch (i) {
            case 1:
                return "first ";
            case 2:
                return "second ";
            case 3:
                return "third ";
            case 4:
                return "fourth ";
            case 5:
                return "fifth ";
            case 6:
                return "sixth ";
            case 7:
                return "seventh ";
            case 8:
                return "eighth ";
            case 9:
                return "nineth ";
            case 10:
                return "tenth ";
            case 11:
                return "eleventh ";
            case 12:
                return "twelfth ";
            case 13:
                return "thirteenth ";
            case 14:
                return "fourteenth ";
            case 15:
                return "fifteenth ";
            case 16:
                return "sixteenth ";
            case 17:
                return "seventeenth ";
            default:
                return "";
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String k(String str) {
        char c;
        switch (str.hashCode()) {
            case -2032923755:
                if (str.equals("take_left_at_intersection")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1995243783:
                if (str.equals("and_arrive_intermediate")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1913017095:
                if (str.equals("location_recovered")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1891021735:
                if (str.equals("speed_alarm")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1858374222:
                if (str.equals("bear_left")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1796839058:
                if (str.equals("location_lost")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1769365039:
                if (str.equals("bear_right")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1763483894:
                if (str.equals("take_right_at_end")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1748384171:
                if (str.equals("border_crossing")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1743222074:
                if (str.equals("reached_intermediate")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1605024242:
                if (str.equals("and_arrive_destination")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1436079272:
                if (str.equals("right_sh")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1436079268:
                if (str.equals("right_sl")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -1387916344:
                if (str.equals("right_keep")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1380797366:
                if (str.equals("and_arrive_poi")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1363373215:
                if (str.equals("highway_exit_left_driving_road")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -1277915446:
                if (str.equals("route_new_calc")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -1022962709:
                if (str.equals("at_fork_right")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1002275663:
                if (str.equals("keep_left_at")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -940798574:
                if (str.equals("leave_ferry")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -866040319:
                if (str.equals("and_arrive_waypoint")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -860341416:
                if (str.equals("prepare_make_ut")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -787359902:
                if (str.equals("take_ferry")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -684025865:
                if (str.equals("highway_enter_left_driving_road")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -563553990:
                if (str.equals("take_right_at_intersection")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -515766372:
                if (str.equals("and_arrive_favorite")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -353951458:
                if (str.equals("attention")) {
                    c = JSONLexer.EOI;
                    break;
                }
                c = 65535;
                break;
            case 3317767:
                if (str.equals("left")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 3558941:
                if (str.equals("then")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 55443853:
                if (str.equals("left_sh")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 55443857:
                if (str.equals("left_sl")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case 108511772:
                if (str.equals("right")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 134370480:
                if (str.equals("keep_right_at")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case 280388531:
                if (str.equals("take_left_at")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case 432844764:
                if (str.equals("event_ahead")) {
                    c = Typography.quote;
                    break;
                }
                c = 65535;
                break;
            case 602516200:
                if (str.equals("make_ut_wp")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 832601744:
                if (str.equals("make_ut")) {
                    c = Typography.dollar;
                    break;
                }
                c = 65535;
                break;
            case 846643278:
                if (str.equals("reached_waypoint")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 946027997:
                if (str.equals("reached_poi")) {
                    c = Typography.amp;
                    break;
                }
                c = 65535;
                break;
            case 1035504801:
                if (str.equals("reached_destination")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case 1121192527:
                if (str.equals("take_left_at_end")) {
                    c = HexStringBuilder.COMMENT_BEGIN_CHAR;
                    break;
                }
                c = 65535;
                break;
            case 1196917225:
                if (str.equals("reached_favorite")) {
                    c = HexStringBuilder.COMMENT_END_CHAR;
                    break;
                }
                c = 65535;
                break;
            case 1242254830:
                if (str.equals("take_right_at")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 1529331777:
                if (str.equals("back_on_route")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 1662048170:
                if (str.equals("go_ahead")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 1741697213:
                if (str.equals("left_keep")) {
                    c = Soundex.SILENT_MARKER;
                    break;
                }
                c = 65535;
                break;
            case 1773581177:
                if (str.equals("off_route")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case 1906481240:
                if (str.equals("at_fork_left")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 1931801600:
                if (str.equals("continue_straight_at")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return " Keep left at next ";
            case 1:
                return " and you will arrive at your intermediate destination ";
            case 2:
                return " g p s mode recovered ";
            case 3:
                return " you are exceeding the speed limit ";
            case 4:
            case '-':
                return " keep left ";
            case 5:
                return " g p s mode lost ";
            case 6:
            case '\r':
                return " keep right ";
            case 7:
                return " At the end of the road turn right ";
            case '\b':
                return " Border crossing ";
            case '\t':
                return " you have reached your intermediate destination ";
            case '\n':
                return " and you will arrive at your destination ";
            case 11:
                return " turn sharply right ";
            case '\f':
                return " turn slightly right ";
            case 14:
                return "and pass POI ";
            case 15:
                return " Highway exit left-driving road";
            case 16:
                return " The trip is ";
            case 17:
                return " At the fork bear right";
            case 18:
                return " At the next intersection bear left ";
            case 19:
                return " Leave ferry ";
            case 20:
                return " and pass waypoint ";
            case 21:
            case '$':
                return " Make a U turn ";
            case 22:
                return " take ferry ";
            case 23:
                return " Highway enter left-driving road ";
            case 24:
                return " Keep right at next ";
            case 25:
                return " and pass favorite ";
            case 26:
                return " attention ";
            case 27:
                return " turn left ";
            case 28:
                return "then";
            case 29:
                return " turn sharply left ";
            case 30:
                return " turn slightly left ";
            case 31:
                return " turn right ";
            case ' ':
                return " At the next intersection bear right ";
            case '!':
                return " Take the 1st left ";
            case '\"':
                return "";
            case '#':
                return " When possible, please make a U turn ";
            case '%':
                return " you are passing waypoint ";
            case '&':
                return " you are passing POI ";
            case '\'':
                return " you have reached your destination ";
            case '(':
                return " At the end of the road turn left ";
            case ')':
                return " you are passing favorite ";
            case '*':
                return " Take the 1st right ";
            case '+':
                return " you are back on the route ";
            case ',':
                return " Go straight ahead ";
            case '.':
                return " you have been off the route for ";
            case '/':
                return " At the fork bear left";
            case '0':
                return " Continue straight at next";
            default:
                return null;
        }
    }

    public static String l(NavigationStep navigationStep) {
        return TextInstructionHelper.getInstance().getInstruction((LegStep) navigationStep.getExtraInfo(), true).split("\\$")[0];
    }

    public static String m(NavigationStep navigationStep) {
        String shortInstruction = TextInstructionHelper.getInstance().getShortInstruction((LegStep) navigationStep.getExtraInfo());
        return (!((LegStep) navigationStep.getExtraInfo()).maneuver().type().equalsIgnoreCase("roundabout") || shortInstruction.contains("exit")) ? shortInstruction : o.a(shortInstruction, " exit");
    }

    public static String n(String str) {
        return (TextUtils.isEmpty(str) || !str.contains(Constants.SEPARATOR_COMMA)) ? str : str.replace(Constants.SEPARATOR_COMMA, "&");
    }

    public final b a(double d, String str, NavigationStep navigationStep) {
        String k;
        if (navigationStep != null && navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            str = "this route";
        }
        if (TextUtils.isEmpty(str)) {
            k = k("go_ahead");
        } else {
            StringBuilder a2 = h.a(" Follow ");
            a2.append(n(str));
            a2.append(" for ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            k = a2.toString();
        }
        return j(k);
    }

    public final ArrayList a() {
        c cVar = this.f13047a;
        ArrayList arrayList = this.b;
        ((a) cVar).getClass();
        return arrayList;
    }

    public final void a(String str, NavigationStep navigationStep) {
        String k;
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            k = l(navigationStep);
        } else if (TextUtils.isEmpty(str)) {
            k = k("bear_left");
        } else {
            k = k("bear_left") + " at " + n(str);
        }
        j(k);
    }

    public final void a(String str, String str2, NavigationStep navigationStep) {
        String k;
        if (navigationStep == null || navigationStep.getExtraInfo() == null || !(navigationStep.getExtraInfo() instanceof LegStep)) {
            if (TextUtils.isEmpty(str2)) {
                k = k(str);
            } else {
                k = k(str) + " on " + n(str2);
            }
        } else if (((LegStep) navigationStep.getExtraInfo()).maneuver().type().equalsIgnoreCase("arrive") || navigationStep.isDestination() || navigationStep.getManeuverID() == 8) {
            return;
        } else {
            k = m(navigationStep);
        }
        j(k);
    }

    public final b b(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("and_arrive_favorite");
        } else {
            k = k("and_arrive_favorite") + str;
        }
        return j(k);
    }

    public final void b() {
        j(k("go_ahead"));
    }

    public final void b(double d, String str, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            StringBuilder a2 = h.a(" In ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            a2.append(l(navigationStep));
            j(a2.toString());
        } else if (!TextUtils.isEmpty(str)) {
            StringBuilder a3 = h.a(" In ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(k("make_ut"));
            a3.append(" On ");
            a3.append(n(str));
            j(a3.toString());
        } else {
            StringBuilder a4 = h.a(" In ");
            a4.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a4.append(k("make_ut"));
            j(a4.toString());
        }
    }

    public final void b(String str, NavigationStep navigationStep) {
        String k;
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            k = l(navigationStep);
        } else if (TextUtils.isEmpty(str)) {
            k = k("bear_right");
        } else {
            k = k("bear_right") + " at " + n(str);
        }
        j(k);
    }

    public final b c() {
        return j(k("make_ut_wp"));
    }

    public final b c(double d, String str, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            StringBuilder a2 = h.a(" after ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            a2.append(l(navigationStep));
            return j(a2.toString());
        } else if (!TextUtils.isEmpty(str)) {
            StringBuilder a3 = h.a(" after ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(k("prepare_make_ut"));
            a3.append(" On ");
            a3.append(n(str));
            return j(a3.toString());
        } else {
            StringBuilder a4 = h.a(" after ");
            a4.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a4.append(k("prepare_make_ut"));
            return j(a4.toString());
        }
    }

    public final b c(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("and_arrive_poi");
        } else {
            k = k("and_arrive_poi") + str;
        }
        return j(k);
    }

    public final void c(String str, NavigationStep navigationStep) {
        String k;
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            k = m(navigationStep);
        } else if (TextUtils.isEmpty(str)) {
            k = k("make_ut");
        } else {
            k = k("make_ut") + " on " + n(str);
        }
        j(k);
    }

    public final b d(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("reached_destination");
        } else {
            k = k("reached_destination") + str;
        }
        return j(k);
    }

    public final void d() {
        ((g) this.f13047a).a(this);
    }

    public final b e(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("reached_favorite");
        } else {
            k = k("reached_favorite") + str;
        }
        return j(k);
    }

    public final void e() {
        ((g) this.f13047a).a(this, true);
    }

    public final b f() {
        return j(k("speed_alarm"));
    }

    public final b f(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("reached_intermediate");
        } else {
            k = k("reached_intermediate") + str;
        }
        return j(k);
    }

    public final b g() {
        return j(k("then"));
    }

    public final b g(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("reached_poi");
        } else {
            k = k("reached_poi") + str;
        }
        return j(k);
    }

    public final b h(String str) {
        return j(k("attention") + ", " + str);
    }

    public final b i(String str) {
        String k;
        if (TextUtils.isEmpty(str)) {
            k = k("event_ahead");
        } else {
            k = k("event_ahead") + str;
        }
        return j(k);
    }

    public final b j(String str) {
        NavigationLogger.d(str, new Object[0]);
        this.b.add(str);
        return this;
    }

    public final b a(double d) {
        return j(k("off_route") + NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
    }

    public final b a(double d, int i, String str, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() == null || !(navigationStep.getExtraInfo() instanceof LegStep)) {
            if (!TextUtils.isEmpty(str)) {
                StringBuilder a2 = h.a("after ");
                a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
                a2.append(" enter a roundabout and take the ");
                a2.append(k(i));
                a2.append(" Exit ");
                return j(a2.toString());
            }
            StringBuilder a3 = h.a("after ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(" enter a roundabout and take the ");
            a3.append(k(i));
            a3.append(" Exit ");
            return j(a3.toString());
        }
        return j(l(navigationStep));
    }

    public final void b(double d, int i, String str, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            StringBuilder a2 = h.a(" in ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            a2.append(l(navigationStep));
            j(a2.toString());
        } else if (!TextUtils.isEmpty(str)) {
            StringBuilder a3 = h.a(" in ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(" enter a roundabout and take the ");
            a3.append(k(i));
            a3.append(" Exit ");
            j(a3.toString());
        } else {
            StringBuilder a4 = h.a(" in ");
            a4.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a4.append(" enter a roundabout and take the ");
            a4.append(k(i));
            a4.append(" Exit ");
            j(a4.toString());
        }
    }

    public final b a(String str, double d, String str2, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            StringBuilder a2 = h.a(" after ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            a2.append(l(navigationStep));
            return j(a2.toString());
        }
        NavigationLogger.d(str, new Object[0]);
        NavigationLogger.d(str2, new Object[0]);
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder a3 = h.a(" after ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(k(str));
            a3.append(" On ");
            a3.append(n(str2));
            return j(a3.toString());
        }
        StringBuilder a4 = h.a(" after ");
        a4.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
        a4.append(k(str));
        return j(a4.toString());
    }

    public final void b(String str, double d, String str2, NavigationStep navigationStep) {
        if (d == 0.0d) {
            return;
        }
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            StringBuilder a2 = h.a(" in ");
            a2.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            a2.append(l(navigationStep));
            j(a2.toString());
            return;
        }
        NavigationLogger.d(str, new Object[0]);
        NavigationLogger.d(str2, new Object[0]);
        if (!TextUtils.isEmpty(str2)) {
            StringBuilder a3 = h.a(" in ");
            a3.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
            a3.append(k(str));
            a3.append(" On ");
            a3.append(n(str2));
            j(a3.toString());
            return;
        }
        StringBuilder a4 = h.a(" in ");
        a4.append(NavigationFormatter.getFormattedDistanceNavigation((float) d, ((g) this.f13047a).f13046a));
        a4.append(k(str));
        j(a4.toString());
    }

    public final void a(int i, String str, NavigationStep navigationStep) {
        if (navigationStep.getExtraInfo() != null && (navigationStep.getExtraInfo() instanceof LegStep)) {
            j(m(navigationStep));
        } else if (!TextUtils.isEmpty(str)) {
            StringBuilder a2 = h.a(" take the ");
            a2.append(k(i));
            a2.append(" Exit ");
            j(a2.toString());
        } else {
            StringBuilder a3 = h.a(" take the ");
            a3.append(k(i));
            a3.append(" Exit ");
            j(a3.toString());
        }
    }
}
