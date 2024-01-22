package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldRectangle;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jieli.jl_rcsp.constant.Command;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperOPPDevices240x282 implements SoccerHelper {
    @NotNull
    public static final SoccerHelperOPPDevices240x282 INSTANCE = new SoccerHelperOPPDevices240x282();

    /* renamed from: a  reason: collision with root package name */
    public static final int f5885a = BleUtils.convertHexTo565("#ffffff");
    public static final int b = BleUtils.convertHexTo565("#95b53b");
    public static final int c = BleUtils.convertHexTo565("#fcff00");
    public static final int d = BleUtils.convertHexTo565("#b4dbff");
    public static final int e = BleUtils.convertHexTo565("#ff9400");
    public static final int f = BleUtils.convertHexTo565("#31fff3");
    public static final int g = BleUtils.convertHexTo565("#ffbe26");
    public static final int h = 33;

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

    public static /* synthetic */ void b(SoccerHelperOPPDevices240x282 soccerHelperOPPDevices240x282, SoccerData soccerData, ArrayList arrayList, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 119;
        }
        soccerHelperOPPDevices240x282.a(soccerData, arrayList, i);
    }

    public final void a(SoccerData soccerData, ArrayList<DynamicSportsField> arrayList, int i) {
        BleUtils.convertHexTo565("#fcff00");
        int convertHexTo565 = BleUtils.convertHexTo565("#b4dbff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ff9400");
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            soccerData.getTeamAShortName().length();
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 10, i, 22, 22, 22, 0, soccerData.getTeamAShortName()));
            String str = soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore();
            int i2 = f;
            FontLengthUtilityOPP2 fontLengthUtilityOPP2 = FontLengthUtilityOPP2.INSTANCE;
            arrayList.add(new DynamicSportFieldText(1, i2, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, str, 0, 2, null)) / 2, i, 22, 22, 22, 0, str));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, soccerData.getTeamBShortName(), 0, 2, null), i, 22, 22, 22, 0, soccerData.getTeamBShortName()));
            return;
        }
        soccerData.getTeamAShortName().length();
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 10, i, 22, 22, 22, 0, soccerData.getTeamAShortName()));
        String str2 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + ") " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + " (" + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
        int i3 = f;
        FontLengthUtilityOPP2 fontLengthUtilityOPP22 = FontLengthUtilityOPP2.INSTANCE;
        arrayList.add(new DynamicSportFieldText(1, i3, (240 - fontLengthUtilityOPP22.getStringWidth(str2, 22)) / 2, i, 22, 22, 22, 0, str2));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP22, soccerData.getTeamBShortName(), 0, 2, null), i, 22, 22, 22, 0, soccerData.getTeamBShortName()));
    }

    public final ArrayList<DynamicSportsField> c(Event event) {
        int i;
        int i2;
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        int convertHexTo5652 = BleUtils.convertHexTo565("#ff0000");
        int convertHexTo5653 = BleUtils.convertHexTo565("#05ff00");
        BleUtils.convertHexTo565("#95b53b");
        int convertHexTo5654 = BleUtils.convertHexTo565("#fedb41");
        int convertHexTo5655 = BleUtils.convertHexTo565("#ff2323");
        BleUtils.convertHexTo565("#fcff00");
        BleUtils.convertHexTo565("#b4dbff");
        BleUtils.convertHexTo565("#ff9400");
        if (event != null) {
            EventType type = event.getType();
            switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    String str = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName = event.getPlayerName();
                    if (playerName.length() > 12) {
                        StringBuilder sb = new StringBuilder();
                        i = 0;
                        String substring = playerName.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(substring);
                        sb.append("..");
                        playerName = sb.toString();
                    } else {
                        i = 0;
                    }
                    if (str.length() > 22) {
                        StringBuilder sb2 = new StringBuilder();
                        String substring2 = str.substring(i, 20);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb2.append(substring2);
                        sb2.append("..");
                        str = sb2.toString();
                    }
                    FontLengthUtilityOPP2 fontLengthUtilityOPP2 = FontLengthUtilityOPP2.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, str + " (" + event.getTeamName() + ") " + playerName, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str + " (" + event.getTeamName() + ") " + playerName));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, "GOAL", 0, 2, null)) / 2, 229, 22, 22, 0, 0, "GOAL"));
                    Unit unit = Unit.INSTANCE;
                    break;
                case 2:
                    String str2 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str2 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName2 = event.getPlayerName();
                    if (playerName2.length() > 12) {
                        StringBuilder sb3 = new StringBuilder();
                        i2 = 0;
                        String substring3 = playerName2.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb3.append(substring3);
                        sb3.append("..");
                        playerName2 = sb3.toString();
                    } else {
                        i2 = 0;
                    }
                    if (str2.length() > 22) {
                        StringBuilder sb4 = new StringBuilder();
                        String substring4 = str2.substring(i2, 20);
                        Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb4.append(substring4);
                        sb4.append("..");
                        str2 = sb4.toString();
                    }
                    FontLengthUtilityOPP2 fontLengthUtilityOPP22 = FontLengthUtilityOPP2.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP22, str2 + " (" + event.getTeamName() + ") " + playerName2, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str2 + " (" + event.getTeamName() + ") " + playerName2));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP22, "Penalty Shootout", 0, 2, null)) / 2, 229, 22, 22, 0, 0, "Penalty Shootout"));
                    Unit unit2 = Unit.INSTANCE;
                    break;
                case 3:
                    String str3 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str3 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName3 = event.getPlayerName();
                    if (playerName3.length() > 12) {
                        StringBuilder sb5 = new StringBuilder();
                        String substring5 = playerName3.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb5.append(substring5);
                        sb5.append("..");
                        playerName3 = sb5.toString();
                    }
                    if (str3.length() > 22) {
                        StringBuilder sb6 = new StringBuilder();
                        String substring6 = str3.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb6.append(substring6);
                        sb6.append("..");
                        str3 = sb6.toString();
                    }
                    FontLengthUtilityOPP2 fontLengthUtilityOPP23 = FontLengthUtilityOPP2.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP23, str3 + " (" + event.getTeamName() + ") " + playerName3, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str3 + " (" + event.getTeamName() + ") " + playerName3));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP23, "GOAL - PEN", 0, 2, null)) / 2, 229, 22, 22, 0, 0, "GOAL - PEN"));
                    Unit unit3 = Unit.INSTANCE;
                    break;
                case 4:
                    String str4 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str4 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName4 = event.getPlayerName();
                    if (playerName4.length() > 12) {
                        StringBuilder sb7 = new StringBuilder();
                        String substring7 = playerName4.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring7, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb7.append(substring7);
                        sb7.append("..");
                        playerName4 = sb7.toString();
                    }
                    if (str4.length() > 22) {
                        StringBuilder sb8 = new StringBuilder();
                        String substring8 = str4.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring8, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb8.append(substring8);
                        sb8.append("..");
                        str4 = sb8.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str4 + " (" + event.getTeamName() + ") " + playerName4, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str4 + " (" + event.getTeamName() + ") " + playerName4));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5654, 107, 236, 26, 31));
                    Unit unit4 = Unit.INSTANCE;
                    break;
                case 5:
                    String str5 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str5 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName5 = event.getPlayerName();
                    if (playerName5.length() > 12) {
                        StringBuilder sb9 = new StringBuilder();
                        String substring9 = playerName5.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring9, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb9.append(substring9);
                        sb9.append("..");
                        playerName5 = sb9.toString();
                    }
                    if (str5.length() > 22) {
                        StringBuilder sb10 = new StringBuilder();
                        String substring10 = str5.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring10, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb10.append(substring10);
                        sb10.append("..");
                        str5 = sb10.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str5 + " (" + event.getTeamName() + ") " + playerName5, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str5 + " (" + event.getTeamName() + ") " + playerName5));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5654, 120, 238, 26, 31));
                    arrayList.add(new DynamicSportFieldRectangle(8, convertHexTo5655, 110, 228, 26, 31));
                    Unit unit5 = Unit.INSTANCE;
                    break;
                case 6:
                    String str6 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str6 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName6 = event.getPlayerName();
                    if (playerName6.length() > 12) {
                        StringBuilder sb11 = new StringBuilder();
                        String substring11 = playerName6.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring11, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb11.append(substring11);
                        sb11.append("..");
                        playerName6 = sb11.toString();
                    }
                    if (str6.length() > 22) {
                        StringBuilder sb12 = new StringBuilder();
                        String substring12 = str6.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring12, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb12.append(substring12);
                        sb12.append("..");
                        str6 = sb12.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str6 + " (" + event.getTeamName() + ") " + playerName6, 0, 2, null)) / 2, 198, 0, 0, 22, 0, str6 + " (" + event.getTeamName() + ") " + playerName6));
                    arrayList.add(new DynamicSportFieldRectangle(7, convertHexTo5655, 107, 236, 26, 31));
                    Unit unit6 = Unit.INSTANCE;
                    break;
                case 7:
                    String str7 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str7 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerIn = event.getPlayerIn();
                    if (playerIn.length() > 14) {
                        StringBuilder sb13 = new StringBuilder();
                        String substring13 = playerIn.substring(0, 13);
                        Intrinsics.checkNotNullExpressionValue(substring13, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb13.append(substring13);
                        sb13.append("..");
                        playerIn = sb13.toString();
                    }
                    String str8 = str7 + " SUB (" + event.getTeamName() + HexStringBuilder.COMMENT_END_CHAR;
                    FontLengthUtilityOPP2 fontLengthUtilityOPP24 = FontLengthUtilityOPP2.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP24, str8, 0, 2, null)) / 2, 192, 0, 0, 22, 0, str8));
                    String str9 = "In " + playerIn;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo5653, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP24, str9, 0, 2, null)) / 2, 223, 22, 22, 22, 0, str9));
                    String playerOut = event.getPlayerOut();
                    if (playerOut.length() > 14) {
                        StringBuilder sb14 = new StringBuilder();
                        String substring14 = playerOut.substring(0, 13);
                        Intrinsics.checkNotNullExpressionValue(substring14, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb14.append(substring14);
                        sb14.append("..");
                        playerOut = sb14.toString();
                    }
                    String str10 = "Out " + playerOut;
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo5652, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP24, str10, 0, 2, null)) / 2, 254, 22, 22, 22, 0, str10));
                default:
                    Unit unit7 = Unit.INSTANCE;
                    break;
            }
        }
        return arrayList;
    }

    public final int d(SoccerData soccerData) {
        return (240 - FontLengthUtilityOPP2.INSTANCE.getStringWidth(soccerData.getLeageName(), 22)) / 2;
    }

    public final ArrayList<DynamicSportsField> e(SoccerData soccerData) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        BleUtils.convertHexTo565("#95b53b");
        BleUtils.convertHexTo565("#ff0023");
        int convertHexTo5652 = BleUtils.convertHexTo565("#fcff00");
        BleUtils.convertHexTo565("#b4dbff");
        BleUtils.convertHexTo565("#ff9400");
        if (soccerData.getSoccerSummary() != null) {
            Intrinsics.checkNotNull(soccerData);
            SoccerSummary soccerSummary = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 10, 152, 22, 22, 22, 0, String.valueOf(soccerSummary.getShotsOnTarget()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 61, 152, 22, 22, 22, 0, "Shots on Tgt"));
            FontLengthUtilityOPP2 fontLengthUtilityOPP2 = FontLengthUtilityOPP2.INSTANCE;
            SoccerSummary soccerSummary2 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary2);
            int stringWidth$default = 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, String.valueOf(soccerSummary2.getShotsOnTarget()[1].intValue()), 0, 2, null);
            SoccerSummary soccerSummary3 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary3);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, stringWidth$default, 152, 22, 22, 22, 0, String.valueOf(soccerSummary3.getShotsOnTarget()[1].intValue())));
            StringBuilder sb = new StringBuilder();
            SoccerSummary soccerSummary4 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary4);
            sb.append(soccerSummary4.getPossession()[0].intValue());
            sb.append('%');
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 10, 182, 22, 22, 22, 0, sb.toString()));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, "Possn", 0, 2, null)) / 2, 182, 22, 22, 22, 0, "Possn"));
            StringBuilder sb2 = new StringBuilder();
            SoccerSummary soccerSummary5 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary5);
            sb2.append(soccerSummary5.getPossession()[1].intValue());
            sb2.append('%');
            int stringWidth$default2 = 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, sb2.toString(), 0, 2, null);
            StringBuilder sb3 = new StringBuilder();
            SoccerSummary soccerSummary6 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary6);
            sb3.append(soccerSummary6.getPossession()[1].intValue());
            sb3.append('%');
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, stringWidth$default2, 182, 22, 22, 22, 0, sb3.toString()));
            StringBuilder sb4 = new StringBuilder();
            SoccerSummary soccerSummary7 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary7);
            sb4.append(soccerSummary7.getYellowCards()[0].intValue());
            sb4.append('/');
            SoccerSummary soccerSummary8 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary8);
            sb4.append(soccerSummary8.getRedCards()[0].intValue());
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 10, Command.CMD_GET_EXTERNAL_FLASH_MSG, 22, 22, 22, 0, sb4.toString()));
            arrayList.add(new DynamicSportFieldText(3, convertHexTo5652, 80, Command.CMD_GET_EXTERNAL_FLASH_MSG, 22, 22, 22, 0, "Y/R Card"));
            StringBuilder sb5 = new StringBuilder();
            SoccerSummary soccerSummary9 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary9);
            sb5.append(soccerSummary9.getYellowCards()[1].intValue());
            sb5.append('/');
            SoccerSummary soccerSummary10 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary10);
            sb5.append(soccerSummary10.getRedCards()[1].intValue());
            int stringWidth$default3 = 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, sb5.toString(), 0, 2, null);
            StringBuilder sb6 = new StringBuilder();
            SoccerSummary soccerSummary11 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary11);
            sb6.append(soccerSummary11.getYellowCards()[1].intValue());
            sb6.append('/');
            SoccerSummary soccerSummary12 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary12);
            sb6.append(soccerSummary12.getRedCards()[1].intValue());
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, stringWidth$default3, Command.CMD_GET_EXTERNAL_FLASH_MSG, 22, 22, 22, 0, sb6.toString()));
            SoccerSummary soccerSummary13 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary13);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, 10, 245, 22, 22, 22, 0, String.valueOf(soccerSummary13.getFouls()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, g, 94, 245, 22, 22, 22, 0, "Fouls"));
            SoccerSummary soccerSummary14 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary14);
            int stringWidth$default4 = 230 - FontLengthUtilityOPP2.getStringWidth$default(fontLengthUtilityOPP2, String.valueOf(soccerSummary14.getFouls()[1].intValue()), 0, 2, null);
            SoccerSummary soccerSummary15 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary15);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, stringWidth$default4, 245, 22, 22, 22, 0, String.valueOf(soccerSummary15.getFouls()[1].intValue())));
        }
        return arrayList;
    }

    public final int getColor2() {
        return f5885a;
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

    public final int getLeagueY() {
        return h;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        String leageName = soccerData.getLeageName();
        if (leageName.length() > 20) {
            String substring = leageName.substring(0, 19);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            soccerData.setLeageName(substring);
        }
        arrayList.add(new DynamicSportFieldText(1, b, d(soccerData), h, 22, 22, 22, 0, soccerData.getLeageName()));
        b(this, soccerData, arrayList, 0, 4, null);
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (240 - FontLengthUtilityOPP2.INSTANCE.getStringWidth("Match canceled", 22)) / 2, 220, 22, 22, 22, 0, "Match canceled"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        String leageName = soccerData.getLeageName();
        if (leageName.length() > 20) {
            String substring = leageName.substring(0, 19);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            soccerData.setLeageName(substring);
        }
        arrayList.add(new DynamicSportFieldText(1, b, d(soccerData), h, 22, 22, 22, 0, soccerData.getLeageName()));
        b(this, soccerData, arrayList, 0, 4, null);
        String startTime = soccerData.getStartTime();
        Intrinsics.checkNotNull(startTime);
        String dateStr = AppUtils.formatDate(getStartTime(startTime).getTime(), "hh:mm a");
        FontLengthUtilityOPP2 fontLengthUtilityOPP2 = FontLengthUtilityOPP2.INSTANCE;
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (240 - fontLengthUtilityOPP2.getStringWidth("Match will start at", 22)) / 2, 220, 22, 22, 22, 0, "Match will start at"));
        Intrinsics.checkNotNullExpressionValue(dateStr, "dateStr");
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (240 - fontLengthUtilityOPP2.getStringWidth(dateStr, 22)) / 2, 254, 22, 22, 22, 0, dateStr));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getNoMatchSelectedRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (240 - FontLengthUtilityOPP2.INSTANCE.getStringWidth("No Match Selected", 22)) / 2, 220, 22, 22, 22, 0, "No Match Selected"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getOfflineRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, (240 - FontLengthUtilityOPP2.INSTANCE.getStringWidth("Offline", 22)) / 2, 220, 22, 22, 22, 0, "Offline"));
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
        String leageName = soccerData.getLeageName();
        if (leageName.length() > 20) {
            String substring = leageName.substring(0, 19);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            soccerData.setLeageName(substring);
        }
        arrayList.add(new DynamicSportFieldText(1, convertHexTo5652, d(soccerData), h, 22, 22, 22, 0, soccerData.getLeageName()));
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
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, (240 - FontLengthUtilityOPP2.getStringWidth$default(FontLengthUtilityOPP2.INSTANCE, str, 0, 2, null)) / 2, 165, 22, 22, 22, 0, str));
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
        String leageName = soccerData.getLeageName();
        if (leageName.length() > 20) {
            leageName = leageName.substring(0, 19);
            Intrinsics.checkNotNullExpressionValue(leageName, "this as java.lang.String…ing(startIndex, endIndex)");
            soccerData.setLeageName(leageName);
        }
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, d(soccerData), h, 22, 22, 22, 0, leageName));
        a(soccerData, arrayList, 119);
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
