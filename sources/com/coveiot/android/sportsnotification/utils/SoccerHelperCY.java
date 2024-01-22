package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldRectangle;
import com.coveiot.android.bleabstract.models.DynamicSportFieldSquare;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.task.ITask;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import com.veryfit.multi.nativeprotocol.b;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperCY implements SoccerHelper {
    @NotNull
    public static final SoccerHelperCY INSTANCE = new SoccerHelperCY();

    /* renamed from: a  reason: collision with root package name */
    public static final int f5884a = BleUtils.convertHexTo565("#ffffff");
    public static final int b = BleUtils.convertHexTo565("#95b53b");
    public static final int c = BleUtils.convertHexTo565("#fcff00");
    public static final int d = BleUtils.convertHexTo565("#b4dbff");
    public static final int e = BleUtils.convertHexTo565("#ff9400");
    public static final int f = BleUtils.convertHexTo565("#31fff3");
    public static final int g = BleUtils.convertHexTo565("#ffbe26");

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EventType.values().length];
            try {
                iArr[EventType.GOAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EventType.PENALTY_SHOOT_OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EventType.PENALTY_KICK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EventType.YELLOW_CARD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EventType.YELLOW_RED_CARD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[EventType.RED_CARD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[EventType.SUBSTITUTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void b(SoccerHelperCY soccerHelperCY, SoccerData soccerData, ArrayList arrayList, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 239;
        }
        soccerHelperCY.a(soccerData, arrayList, i);
    }

    public final void a(SoccerData soccerData, ArrayList<DynamicSportsField> arrayList, int i) {
        BleUtils.convertHexTo565("#fcff00");
        int convertHexTo565 = BleUtils.convertHexTo565("#b4dbff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ff9400");
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, soccerData.getTeamAShortName().length() > 2 ? 54 : 64, i, 24, 24, 24, 0, soccerData.getTeamAShortName()));
            String str = soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore();
            arrayList.add(new DynamicSportFieldText(1, f, (454 - FontLengthUtility.getStringWidth$default(FontLengthUtility.INSTANCE, str, 0, 2, null)) / 2, i - 4, 28, 28, 28, 0, str));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, b.s1, i, 24, 24, 24, 0, soccerData.getTeamBShortName()));
            return;
        }
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, soccerData.getTeamAShortName().length() > 2 ? 54 : 64, i, 24, 24, 24, 0, soccerData.getTeamAShortName()));
        String str2 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + ") " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + " (" + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
        arrayList.add(new DynamicSportFieldText(1, f, (454 - FontLengthUtility.INSTANCE.getStringWidth(str2, 28)) / 2, i - 4, 28, 28, 28, 0, str2));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, b.s1, i, 24, 24, 24, 0, soccerData.getTeamBShortName()));
    }

    public final ArrayList<DynamicSportsField> c(Event event) {
        int i;
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ff0000");
        int convertHexTo5653 = BleUtils.convertHexTo565("#05ff00");
        BleUtils.convertHexTo565("#95b53b");
        int convertHexTo5654 = BleUtils.convertHexTo565("#fedb41");
        int convertHexTo5655 = BleUtils.convertHexTo565("#ff2323");
        BleUtils.convertHexTo565("#fcff00");
        int convertHexTo5656 = BleUtils.convertHexTo565("#b4dbff");
        int convertHexTo5657 = BleUtils.convertHexTo565("#ff9400");
        if (event != null) {
            EventType type = event.getType();
            switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    String str = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName = event.getPlayerName();
                    if (playerName.length() > 19) {
                        StringBuilder sb = new StringBuilder();
                        i = 0;
                        String substring = playerName.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(substring);
                        sb.append("..");
                        playerName = sb.toString();
                    } else {
                        i = 0;
                    }
                    String str2 = playerName;
                    if (str.length() > 22) {
                        StringBuilder sb2 = new StringBuilder();
                        String substring2 = str.substring(i, 20);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb2.append(substring2);
                        sb2.append("..");
                        str = sb2.toString();
                    }
                    String str3 = str;
                    FontLengthUtility fontLengthUtility = FontLengthUtility.INSTANCE;
                    int stringWidth$default = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility, str3 + ' ' + str2, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default, b.k1, 0, 0, 24, 0, str3));
                    arrayList.add(new DynamicSportFieldText(6, event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657, stringWidth$default + FontLengthUtility.getStringWidth$default(fontLengthUtility, str3 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str2));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 190, 376, 24, 24, 0, 0, "GOAL"));
                    Unit unit = Unit.INSTANCE;
                    break;
                case 2:
                    String str4 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str4 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName2 = event.getPlayerName();
                    if (playerName2.length() > 19) {
                        StringBuilder sb3 = new StringBuilder();
                        String substring3 = playerName2.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb3.append(substring3);
                        sb3.append("..");
                        playerName2 = sb3.toString();
                    }
                    String str5 = playerName2;
                    if (str4.length() > 22) {
                        StringBuilder sb4 = new StringBuilder();
                        String substring4 = str4.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb4.append(substring4);
                        sb4.append("..");
                        str4 = sb4.toString();
                    }
                    String str6 = str4;
                    FontLengthUtility fontLengthUtility2 = FontLengthUtility.INSTANCE;
                    int stringWidth$default2 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility2, str6 + ' ' + str5, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default2, b.k1, 0, 0, 24, 0, str6));
                    arrayList.add(new DynamicSportFieldText(6, event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657, stringWidth$default2 + FontLengthUtility.getStringWidth$default(fontLengthUtility2, str6 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str5));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 80, 376, 24, 24, 0, 0, "Penalty Shootout"));
                    Unit unit2 = Unit.INSTANCE;
                    break;
                case 3:
                    String str7 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str7 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName3 = event.getPlayerName();
                    if (playerName3.length() > 19) {
                        StringBuilder sb5 = new StringBuilder();
                        String substring5 = playerName3.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb5.append(substring5);
                        sb5.append("..");
                        playerName3 = sb5.toString();
                    }
                    String str8 = playerName3;
                    if (str7.length() > 22) {
                        StringBuilder sb6 = new StringBuilder();
                        String substring6 = str7.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb6.append(substring6);
                        sb6.append("..");
                        str7 = sb6.toString();
                    }
                    String str9 = str7;
                    FontLengthUtility fontLengthUtility3 = FontLengthUtility.INSTANCE;
                    int stringWidth$default3 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility3, str9 + ' ' + str8, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default3, b.k1, 0, 0, 24, 0, str9));
                    arrayList.add(new DynamicSportFieldText(6, event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657, stringWidth$default3 + FontLengthUtility.getStringWidth$default(fontLengthUtility3, str9 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str8));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 153, 376, 24, 24, 0, 0, "GOAL - PEN"));
                    Unit unit3 = Unit.INSTANCE;
                    break;
                case 4:
                    String str10 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str10 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName4 = event.getPlayerName();
                    if (playerName4.length() > 19) {
                        StringBuilder sb7 = new StringBuilder();
                        String substring7 = playerName4.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring7, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb7.append(substring7);
                        sb7.append("..");
                        playerName4 = sb7.toString();
                    }
                    String str11 = playerName4;
                    if (str10.length() > 22) {
                        StringBuilder sb8 = new StringBuilder();
                        String substring8 = str10.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring8, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb8.append(substring8);
                        sb8.append("..");
                        str10 = sb8.toString();
                    }
                    String str12 = str10;
                    FontLengthUtility fontLengthUtility4 = FontLengthUtility.INSTANCE;
                    int stringWidth$default4 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility4, str12 + ' ' + str11, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default4, b.k1, 0, 0, 24, 0, str12));
                    arrayList.add(new DynamicSportFieldText(6, event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657, stringWidth$default4 + FontLengthUtility.getStringWidth$default(fontLengthUtility4, str12 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str11));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5654, Command.CMD_GET_EXTERNAL_FLASH_MSG, 376, 26, 31));
                    Unit unit4 = Unit.INSTANCE;
                    break;
                case 5:
                    String str13 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str13 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName5 = event.getPlayerName();
                    if (playerName5.length() > 19) {
                        StringBuilder sb9 = new StringBuilder();
                        String substring9 = playerName5.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring9, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb9.append(substring9);
                        sb9.append("..");
                        playerName5 = sb9.toString();
                    }
                    String str14 = playerName5;
                    if (str13.length() > 22) {
                        StringBuilder sb10 = new StringBuilder();
                        String substring10 = str13.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring10, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb10.append(substring10);
                        sb10.append("..");
                        str13 = sb10.toString();
                    }
                    String str15 = str13;
                    FontLengthUtility fontLengthUtility5 = FontLengthUtility.INSTANCE;
                    int stringWidth$default5 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility5, str15 + ' ' + str14, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default5, b.k1, 0, 0, 24, 0, str15));
                    if (!event.isHomeTeam()) {
                        convertHexTo5656 = convertHexTo5657;
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo5656, stringWidth$default5 + FontLengthUtility.getStringWidth$default(fontLengthUtility5, str15 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str14));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5654, 204, 396, 26, 31));
                    arrayList.add(new DynamicSportFieldSquare(8, convertHexTo5655, Command.CMD_GET_EXTERNAL_FLASH_MSG, 376, 26, 31));
                    Unit unit5 = Unit.INSTANCE;
                    break;
                case 6:
                    String str16 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str16 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName6 = event.getPlayerName();
                    if (playerName6.length() > 19) {
                        StringBuilder sb11 = new StringBuilder();
                        String substring11 = playerName6.substring(0, 18);
                        Intrinsics.checkNotNullExpressionValue(substring11, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb11.append(substring11);
                        sb11.append("..");
                        playerName6 = sb11.toString();
                    }
                    String str17 = playerName6;
                    if (str16.length() > 22) {
                        StringBuilder sb12 = new StringBuilder();
                        String substring12 = str16.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring12, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb12.append(substring12);
                        sb12.append("..");
                        str16 = sb12.toString();
                    }
                    String str18 = str16;
                    FontLengthUtility fontLengthUtility6 = FontLengthUtility.INSTANCE;
                    int stringWidth$default6 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility6, str18 + ' ' + str17, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default6, b.k1, 0, 0, 24, 0, str18));
                    arrayList.add(new DynamicSportFieldText(6, event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657, stringWidth$default6 + FontLengthUtility.getStringWidth$default(fontLengthUtility6, str18 + ' ', 0, 2, null), b.k1, 0, 0, 24, 0, str17));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5655, Command.CMD_GET_EXTERNAL_FLASH_MSG, 376, 26, 31));
                    Unit unit6 = Unit.INSTANCE;
                    break;
                case 7:
                    String str19 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str19 = event.getTime() + "'+" + event.getInjuryTime() + "' ";
                    }
                    String str20 = str19;
                    String playerIn = event.getPlayerIn();
                    if (playerIn.length() > 14) {
                        StringBuilder sb13 = new StringBuilder();
                        String substring13 = playerIn.substring(0, 13);
                        Intrinsics.checkNotNullExpressionValue(substring13, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb13.append(substring13);
                        sb13.append("..");
                        playerIn = sb13.toString();
                    }
                    String str21 = playerIn;
                    FontLengthUtility fontLengthUtility7 = FontLengthUtility.INSTANCE;
                    int stringWidth$default7 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility7, str20 + " In " + str21, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, stringWidth$default7, b.k1, 0, 0, 24, 0, str20));
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo5653, stringWidth$default7 + FontLengthUtility.getStringWidth$default(fontLengthUtility7, str20 + ' ', 0, 2, null), b.k1, 24, 24, 24, 0, "In"));
                    int i2 = event.isHomeTeam() ? convertHexTo5656 : convertHexTo5657;
                    arrayList.add(new DynamicSportFieldText(6, i2, stringWidth$default7 + FontLengthUtility.getStringWidth$default(fontLengthUtility7, str20 + " In ", 0, 2, null), b.k1, 24, 24, 24, 0, str21));
                    String playerOut = event.getPlayerOut();
                    if (playerOut.length() > 14) {
                        StringBuilder sb14 = new StringBuilder();
                        String substring14 = playerOut.substring(0, 13);
                        Intrinsics.checkNotNullExpressionValue(substring14, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb14.append(substring14);
                        sb14.append("..");
                        playerOut = sb14.toString();
                    }
                    int stringWidth$default8 = (454 - FontLengthUtility.getStringWidth$default(fontLengthUtility7, "Out " + playerOut, 0, 2, null)) / 2;
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo5652, stringWidth$default8, 380, 24, 24, 24, 0, "Out"));
                    arrayList.add(new DynamicSportFieldText(6, i2, stringWidth$default8 + FontLengthUtility.getStringWidth$default(fontLengthUtility7, "Out ", 0, 2, null), 380, 24, 24, 24, 0, playerOut));
                default:
                    Unit unit7 = Unit.INSTANCE;
                    break;
            }
        }
        return arrayList;
    }

    public final int d(SoccerData soccerData) {
        return (454 - FontLengthUtility.INSTANCE.getStringWidth(soccerData.getLeageName(), 28)) / 2;
    }

    public final ArrayList<DynamicSportsField> e(SoccerData soccerData) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        BleUtils.convertHexTo565("#95b53b");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ff0023");
        int convertHexTo5653 = BleUtils.convertHexTo565("#fcff00");
        BleUtils.convertHexTo565("#b4dbff");
        BleUtils.convertHexTo565("#ff9400");
        if (soccerData.getSoccerSummary() != null) {
            Intrinsics.checkNotNull(soccerData);
            SoccerSummary soccerSummary = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 81, 176, 24, 24, 24, 0, String.valueOf(soccerSummary.getShotsOnTarget()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 149, 176, 24, 24, 24, 0, "Shots on Tgt"));
            SoccerSummary soccerSummary2 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary2);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 361, 176, 24, 24, 24, 0, String.valueOf(soccerSummary2.getShotsOnTarget()[1].intValue())));
            StringBuilder sb = new StringBuilder();
            SoccerSummary soccerSummary3 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary3);
            sb.append(soccerSummary3.getPossession()[0].intValue());
            sb.append('%');
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 69, 218, 24, 24, 24, 0, sb.toString()));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, (454 - FontLengthUtility.getStringWidth$default(FontLengthUtility.INSTANCE, "Possession", 0, 2, null)) / 2, 218, 24, 24, 24, 0, "Possession"));
            StringBuilder sb2 = new StringBuilder();
            SoccerSummary soccerSummary4 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary4);
            sb2.append(soccerSummary4.getPossession()[1].intValue());
            sb2.append('%');
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 344, 218, 24, 24, 24, 0, sb2.toString()));
            SoccerSummary soccerSummary5 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary5);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 81, DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS, 24, 24, 24, 0, String.valueOf(soccerSummary5.getYellowCards()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo5653, 153, 258, 24, 24, 24, 0, "Yellow Card"));
            SoccerSummary soccerSummary6 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary6);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 361, DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS, 24, 24, 24, 0, String.valueOf(soccerSummary6.getYellowCards()[1].intValue())));
            SoccerSummary soccerSummary7 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary7);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 81, 301, 24, 24, 24, 0, String.valueOf(soccerSummary7.getRedCards()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 170, 301, 24, 24, 24, 0, "Red Card"));
            SoccerSummary soccerSummary8 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary8);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 361, 301, 24, 24, 24, 0, String.valueOf(soccerSummary8.getRedCards()[1].intValue())));
            SoccerSummary soccerSummary9 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary9);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 81, ITask.EVT_START, 24, 24, 24, 0, String.valueOf(soccerSummary9.getFouls()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, g, 194, ITask.EVT_START, 24, 24, 24, 0, "Fouls"));
            SoccerSummary soccerSummary10 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary10);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 361, ITask.EVT_START, 24, 24, 24, 0, String.valueOf(soccerSummary10.getFouls()[1].intValue())));
        }
        return arrayList;
    }

    public final int getColor2() {
        return f5884a;
    }

    public final int getColor_31fff3() {
        return f;
    }

    public final int getColor_95b53b() {
        return b;
    }

    public final int getColor_b4dbff() {
        return d;
    }

    public final int getColor_fcff00() {
        return c;
    }

    public final int getColor_ff9400() {
        return e;
    }

    public final int getColor_ffbe26() {
        return g;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        int i = b;
        arrayList.add(new DynamicSportFieldText(1, i, 136, 74, 32, 32, 32, 0, "Sports score"));
        arrayList.add(new DynamicSportFieldText(1, i, d(soccerData), 187, 28, 28, 28, 0, soccerData.getLeageName()));
        b(this, soccerData, arrayList, 0, 4, null);
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (454 - FontLengthUtility.INSTANCE.getStringWidth("Match canceled", 28)) / 2, TGEventListener.WORKOUT_START, 28, 28, 28, 0, "Match canceled"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        int i = b;
        arrayList.add(new DynamicSportFieldText(1, i, 136, 74, 32, 32, 32, 0, "Sports score"));
        arrayList.add(new DynamicSportFieldText(1, i, d(soccerData), 187, 28, 28, 28, 0, soccerData.getLeageName()));
        b(this, soccerData, arrayList, 0, 4, null);
        StringBuilder sb = new StringBuilder();
        sb.append("Match will start at ");
        String startTime = soccerData.getStartTime();
        Intrinsics.checkNotNull(startTime);
        sb.append(AppUtils.formatDate(getStartTime(startTime).getTime(), "hh:mm a"));
        String sb2 = sb.toString();
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (454 - FontLengthUtility.getStringWidth$default(FontLengthUtility.INSTANCE, sb2, 0, 2, null)) / 2, TGEventListener.WORKOUT_START, 24, 24, 24, 0, sb2));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getNoMatchSelectedRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, b, 136, 74, 32, 32, 32, 0, "Sports score"));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (454 - FontLengthUtility.INSTANCE.getStringWidth("No Match Selected", 28)) / 2, TGEventListener.WORKOUT_START, 28, 28, 28, 0, "No Match Selected"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getOfflineRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, b, 136, 74, 32, 32, 32, 0, "Sports score"));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (454 - FontLengthUtility.INSTANCE.getStringWidth("Offline", 28)) / 2, TGEventListener.WORKOUT_START, 28, 28, 28, 0, "Offline"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getSoccerEventRequest(@NotNull SoccerData soccerData) {
        String str;
        StringBuilder sb;
        String injuryTime;
        StringBuilder sb2;
        String injuryTime2;
        StringBuilder sb3;
        String injuryTime3;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#95b53b");
        BleUtils.convertHexTo565("#fcff00");
        BleUtils.convertHexTo565("#b4dbff");
        BleUtils.convertHexTo565("#ff9400");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, 136, 74, 32, 32, 32, 0, "Sports score"));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, d(soccerData), 187, 28, 28, 28, 0, soccerData.getLeageName()));
        b(this, soccerData, arrayList, 0, 4, null);
        if (soccerData.getGameState() != 3 && soccerData.getGameState() != 5) {
            if (Integer.parseInt(soccerData.getInjuryTime()) == 0) {
                sb3 = new StringBuilder();
                injuryTime3 = soccerData.getMatchTime();
            } else {
                sb3 = new StringBuilder();
                sb3.append(soccerData.getMatchTime());
                sb3.append("'+");
                injuryTime3 = soccerData.getInjuryTime();
            }
            sb3.append(injuryTime3);
            sb3.append("' ONGOING");
            str = sb3.toString();
        } else if (soccerData.getGameState() == 3) {
            if (Integer.parseInt(soccerData.getInjuryTime()) == 0) {
                sb2 = new StringBuilder();
                injuryTime2 = soccerData.getMatchTime();
            } else {
                sb2 = new StringBuilder();
                sb2.append(soccerData.getMatchTime());
                sb2.append("'+");
                injuryTime2 = soccerData.getInjuryTime();
            }
            sb2.append(injuryTime2);
            sb2.append("' HALF TIME");
            str = sb2.toString();
        } else if (soccerData.getGameState() == 5) {
            if (Integer.parseInt(soccerData.getInjuryTime()) == 0) {
                sb = new StringBuilder();
                injuryTime = soccerData.getMatchTime();
            } else {
                sb = new StringBuilder();
                sb.append(soccerData.getMatchTime());
                sb.append("'+");
                injuryTime = soccerData.getInjuryTime();
            }
            sb.append(injuryTime);
            sb.append("' EXTRA TIME");
            str = sb.toString();
        } else {
            str = "";
        }
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, (454 - FontLengthUtility.getStringWidth$default(FontLengthUtility.INSTANCE, str, 0, 2, null)) / 2, TGEventListener.WORKOUT_START, 24, 24, 24, 0, str));
        if (soccerData.getEvent() != null) {
            arrayList.addAll(c(soccerData.getEvent()));
        }
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getSoccerSummaryRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        BleUtils.convertHexTo565("#ffffff");
        BleUtils.convertHexTo565("#95b53b");
        int convertHexTo565 = BleUtils.convertHexTo565("#fcff00");
        BleUtils.convertHexTo565("#b4dbff");
        BleUtils.convertHexTo565("#ff9400");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, d(soccerData), 80, 28, 28, 28, 0, soccerData.getLeageName()));
        a(soccerData, arrayList, 126);
        arrayList.addAll(e(soccerData));
        return arrayList;
    }

    @NotNull
    public final Calendar getStartTime(@NotNull String dateStart) {
        Intrinsics.checkNotNullParameter(dateStart, "dateStart");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        calendar.setTime(simpleDateFormat.parse(dateStart));
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }
}
