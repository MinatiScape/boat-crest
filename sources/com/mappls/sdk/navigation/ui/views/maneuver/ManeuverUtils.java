package com.mappls.sdk.navigation.ui.views.maneuver;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.alibaba.fastjson.parser.JSONLexer;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.navigation.R;
import com.mappls.sdk.navigation.o;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.StepManeuver;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.codec.net.a;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes11.dex */
public class ManeuverUtils {
    public static boolean a(String str, String str2, String str3) {
        return "left".equals(str3) && ("roundabout".equals(str) || "rotary".equals(str) || "uturn".equals(str2));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int b(String str) {
        char c;
        switch (str.hashCode()) {
            case -2069353886:
                if (str.equals("end of roadsharp right")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -2058784777:
                if (str.equals("rotaryslight rightleft")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_UT;
                    break;
                }
                c = 65535;
                break;
            case -2015176631:
                if (str.equals("on rampright")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1995357345:
                if (str.equals("continuesharp right")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1928466355:
                if (str.equals("roundaboutleftleft")) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case -1924264237:
                if (str.equals("on rampsharp right")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1860475435:
                if (str.equals("continueright")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1860127047:
                if (str.equals("roundaboutstraight")) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case -1857363413:
                if (str.equals("continueuturn")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1826064786:
                if (str.equals("rotarysharp left")) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case -1801004622:
                if (str.equals("rotarystraightleft")) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case -1797885270:
                if (str.equals("off rampleft")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -1695915194:
                if (str.equals("roundaboutleft")) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case -1654896656:
                if (str.equals("rotaryslight right")) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case -1626763293:
                if (str.equals("exit roundaboutslight left")) {
                    c = HexStringBuilder.COMMENT_BEGIN_CHAR;
                    break;
                }
                c = 65535;
                break;
            case -1554705391:
                if (str.equals("notificationright")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1550211583:
                if (str.equals("forkslight right")) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case -1516205288:
                if (str.equals("end of roadright")) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case -1471737444:
                if (str.equals("turnuturnleft")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1450022300:
                if (str.equals("continuesharp left")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1409157417:
                if (str.equals("arrive")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1335343116:
                if (str.equals("depart")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1300492365:
                if (str.equals("rotaryslight left")) {
                    c = a.SEP;
                    break;
                }
                c = 65535;
                break;
            case -1094043451:
                if (str.equals("exit roundaboutsharp right")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1073872668:
                if (str.equals("mergeright")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case -1055320474:
                if (str.equals("continueslight right")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1028102467:
                if (str.equals("roundaboutright")) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case -925180623:
                if (str.equals("rotary")) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case -875899460:
                if (str.equals("forkstraight")) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case -826008882:
                if (str.equals("roundaboutsharp rightleft")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_REGULAR;
                    break;
                }
                c = 65535;
                break;
            case -771347286:
                if (str.equals("notificationslight right")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -767772523:
                if (str.equals("rotarysharp right")) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case -752934610:
                if (str.equals("continueleft")) {
                    c = Typography.quote;
                    break;
                }
                c = 65535;
                break;
            case -729533179:
                if (str.equals("new namestraight")) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case -598492041:
                if (str.equals("mergeslight right")) {
                    c = Soundex.SILENT_MARKER;
                    break;
                }
                c = 65535;
                break;
            case -532588228:
                if (str.equals("turnslight right")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -435130804:
                if (str.equals("mergeslight left")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case -372303643:
                if (str.equals("notificationstraight")) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case -366904923:
                if (str.equals("arriveright")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -327557189:
                if (str.equals("exit roundaboutright")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -326187125:
                if (str.equals("end of roadleft")) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case -236347846:
                if (str.equals("rotaryslight leftleft")) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case -144622913:
                if (str.equals("roundabout")) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case -116818760:
                if (str.equals("rotaryleft")) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case -107018305:
                if (str.equals("turnright")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -103906283:
                if (str.equals("turnuturn")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -50189438:
                if (str.equals("forkslight left")) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 3148994:
                if (str.equals("fork")) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case 57950369:
                if (str.equals("continuestraight")) {
                    c = Matrix.MATRIX_TYPE_ZERO;
                    break;
                }
                c = 65535;
                break;
            case 105792473:
                if (str.equals("off rampright")) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case 134912516:
                if (str.equals("turnleft")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case 252029881:
                if (str.equals("notificationslight left")) {
                    c = HexStringBuilder.COMMENT_END_CHAR;
                    break;
                }
                c = 65535;
                break;
            case 272060992:
                if (str.equals("roundaboutstraightleft")) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case 399415090:
                if (str.equals("mergestraight")) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case 418168958:
                if (str.equals("roundaboutslight right")) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case 472546249:
                if (str.equals("forkleft")) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 489001018:
                if (str.equals("on rampleft")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 496155779:
                if (str.equals("roundaboutsharp leftleft")) {
                    c = Matrix.MATRIX_TYPE_RANDOM_LT;
                    break;
                }
                c = 65535;
                break;
            case 528634536:
                if (str.equals("departright")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 567496037:
                if (str.equals("roundaboutslight left")) {
                    c = Typography.greater;
                    break;
                }
                c = 65535;
                break;
            case 630481008:
                if (str.equals("on rampsharp left")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 679246731:
                if (str.equals("rotaryright")) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 708772031:
                if (str.equals("rotaryleftleft")) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 738059649:
                if (str.equals("off rampslight left")) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 777111339:
                if (str.equals("rotarystraight")) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case 784749723:
                if (str.equals("notificationsharp right")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 848154107:
                if (str.equals("departleft")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 962845946:
                if (str.equals("exit rotaryleft")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case 1019471429:
                if (str.equals("roundabout turnleft")) {
                    c = Typography.dollar;
                    break;
                }
                c = 65535;
                break;
            case 1100215879:
                if (str.equals("roundaboutsharp right")) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case 1126609644:
                if (str.equals("roundaboutslight leftleft")) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case 1144926069:
                if (str.equals("rotarysharp leftleft")) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case 1148565874:
                if (str.equals("on rampslight right")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 1229563111:
                if (str.equals("turnslight left")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case 1320116292:
                if (str.equals("roundaboutrightleft")) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case 1335522482:
                if (str.equals("continueuturnleft")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1410673634:
                if (str.equals("off rampslight right")) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case 1470127634:
                if (str.equals("rotaryrightleft")) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 1512002398:
                if (str.equals("arriveleft")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1538191749:
                if (str.equals("roundaboutslight rightleft")) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case 1544504222:
                if (str.equals("roundabout turnright")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1612233586:
                if (str.equals("notificationleft")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case 1627744319:
                if (str.equals("mergeleft")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 1687699880:
                if (str.equals("notificationsharp left")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 1719233210:
                if (str.equals("turnsharp left")) {
                    c = JSONLexer.EOI;
                    break;
                }
                c = 65535;
                break;
            case 1762282953:
                if (str.equals("turnsharp right")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1766890109:
                if (str.equals("continueslight left")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case 1769692826:
                if (str.equals("forkright")) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 1790366344:
                if (str.equals("exit roundaboutleft")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case 1837983217:
                if (str.equals("on rampslight left")) {
                    c = Typography.amp;
                    break;
                }
                c = 65535;
                break;
            case 2106000924:
                if (str.equals("rotarysharp rightleft")) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case 2113518204:
                if (str.equals("roundaboutsharp left")) {
                    c = Typography.less;
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
            case 1:
                return R.drawable.ic_maneuver_turn_180;
            case 2:
            case 3:
                return R.drawable.ic_maneuver_turn_180_left_driving_side;
            case 4:
                return R.drawable.ic_maneuver_arrive_left;
            case 5:
                return R.drawable.ic_maneuver_arrive_right;
            case 6:
                return R.drawable.ic_maneuver_arrive;
            case 7:
                return R.drawable.ic_maneuver_depart_left;
            case '\b':
                return R.drawable.ic_maneuver_depart_right;
            case '\t':
                return R.drawable.ic_maneuver_depart;
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
            case 15:
                return R.drawable.ic_maneuver_turn_75;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return R.drawable.ic_maneuver_turn_45;
            case 22:
            case 23:
            case 24:
            case 25:
                return R.drawable.ic_maneuver_turn_30;
            case 26:
            case 27:
            case 28:
            case 29:
                return R.drawable.ic_maneuver_turn_75_left;
            case 30:
            case 31:
            case ' ':
            case '!':
            case '\"':
            case '#':
            case '$':
                return R.drawable.ic_maneuver_turn_45_left;
            case '%':
            case '&':
            case '\'':
            case '(':
            case ')':
                return R.drawable.ic_maneuver_turn_30_left;
            case '*':
            case '+':
                return R.drawable.ic_maneuver_merge_left;
            case ',':
            case '-':
                return R.drawable.ic_maneuver_merge_right;
            case '.':
                return R.drawable.ic_maneuver_off_ramp_left;
            case '/':
                return R.drawable.ic_maneuver_off_ramp_slight_left;
            case '0':
                return R.drawable.ic_maneuver_off_ramp_right;
            case '1':
                return R.drawable.ic_maneuver_off_ramp_slight_right;
            case '2':
                return R.drawable.ic_maneuver_fork_left;
            case '3':
                return R.drawable.ic_maneuver_fork_slight_left;
            case '4':
                return R.drawable.ic_maneuver_fork_right;
            case '5':
                return R.drawable.ic_maneuver_fork_slight_right;
            case '6':
                return R.drawable.ic_maneuver_fork_straight;
            case '7':
                return R.drawable.ic_maneuver_fork;
            case '8':
                return R.drawable.ic_maneuver_end_of_road_left;
            case '9':
                return R.drawable.ic_maneuver_end_of_road_right;
            case ':':
            case ';':
                return R.drawable.ic_maneuver_roundabout_left;
            case '<':
            case '=':
                return R.drawable.ic_maneuver_roundabout_sharp_left;
            case '>':
            case '?':
                return R.drawable.ic_maneuver_roundabout_slight_left;
            case '@':
            case 'A':
                return R.drawable.ic_maneuver_roundabout_right;
            case 'B':
            case 'C':
                return R.drawable.ic_maneuver_roundabout_sharp_right;
            case 'D':
            case 'E':
                return R.drawable.ic_maneuver_roundabout_slight_right;
            case 'F':
            case 'G':
                return R.drawable.ic_maneuver_roundabout_straight;
            case 'H':
            case 'I':
                return R.drawable.ic_maneuver_roundabout;
            case 'J':
            case 'K':
                return R.drawable.ic_maneuver_roundabout_left_left_driving_side;
            case 'L':
            case 'M':
                return R.drawable.ic_maneuver_roundabout_sharp_left_left_driving_side;
            case 'N':
            case 'O':
                return R.drawable.ic_maneuver_roundabout_slight_left_left_driving_side;
            case 'P':
            case 'Q':
                return R.drawable.ic_maneuver_roundabout_right_left_driving_side;
            case 'R':
            case 'S':
                return R.drawable.ic_maneuver_roundabout_sharp_right_left_driving_side;
            case 'T':
            case 'U':
                return R.drawable.ic_maneuver_roundabout_slight_right_left_driving_side;
            case 'V':
            case 'W':
                return R.drawable.ic_maneuver_roundabout_straight_left_driving_side;
            default:
                return R.drawable.ic_maneuver_turn_0;
        }
    }

    public static int c(Double d, boolean z) {
        return d == null ? z ? R.drawable.ic_maneuver_roundabout_straight_left_driving_side : R.drawable.ic_maneuver_roundabout_straight : d.doubleValue() <= 45.0d ? z ? R.drawable.ic_maneuver_roundabout_sharp_right_left_driving_side : R.drawable.ic_maneuver_roundabout_sharp_right : d.doubleValue() <= 90.0d ? z ? R.drawable.ic_maneuver_roundabout_right_left_driving_side : R.drawable.ic_maneuver_roundabout_right : d.doubleValue() <= 135.0d ? z ? R.drawable.ic_maneuver_roundabout_slight_right_left_driving_side : R.drawable.ic_maneuver_roundabout_slight_right : d.doubleValue() <= 180.0d ? z ? R.drawable.ic_maneuver_roundabout_straight_left_driving_side : R.drawable.ic_maneuver_roundabout_straight : d.doubleValue() <= 225.0d ? z ? R.drawable.ic_maneuver_roundabout_slight_left_left_driving_side : R.drawable.ic_maneuver_roundabout_slight_left : d.doubleValue() <= 270.0d ? z ? R.drawable.ic_maneuver_roundabout_left_left_driving_side : R.drawable.ic_maneuver_roundabout_left : d.doubleValue() <= 360.0d ? z ? R.drawable.ic_maneuver_roundabout_sharp_left_left_driving_side : R.drawable.ic_maneuver_roundabout_sharp_left : z ? R.drawable.ic_maneuver_roundabout_straight_left_driving_side : R.drawable.ic_maneuver_roundabout_straight;
    }

    public static int getManeuverResource(@NonNull LegStep legStep) {
        StepManeuver maneuver = legStep.maneuver();
        String type = maneuver.type();
        String modifier = maneuver.modifier();
        String drivingSide = legStep.drivingSide();
        if (type != null && type.equalsIgnoreCase("roundabout")) {
            return (drivingSide == null || drivingSide.equalsIgnoreCase("left")) ? c(maneuver.degree(), true) : c(maneuver.degree(), false);
        } else if (type == null || !type.equalsIgnoreCase("rotary")) {
            if (TextUtils.isEmpty(modifier)) {
                return b(type);
            }
            if (a(type, modifier, drivingSide)) {
                return b(type + modifier + drivingSide);
            }
            return b(o.a(type, modifier));
        } else {
            return R.drawable.ic_maneuver_roundabout;
        }
    }
}
