package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldRectangle;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
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
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperPS1Devices466X466 implements SoccerHelper {
    @NotNull
    public static final SoccerHelperPS1Devices466X466 INSTANCE = new SoccerHelperPS1Devices466X466();

    /* renamed from: a  reason: collision with root package name */
    public static final int f5890a = BleUtils.convertHexTo565("#ffffff");
    public static final int b = BleUtils.convertHexTo565("#03c28a");
    public static final int c = BleUtils.convertHexTo565("#fedb41");
    public static final int d = BleUtils.convertHexTo565("#ff2323");

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

    public static /* synthetic */ void b(SoccerHelperPS1Devices466X466 soccerHelperPS1Devices466X466, SoccerData soccerData, ArrayList arrayList, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = Command.CMD_SET_DEVICE_STORAGE;
        }
        soccerHelperPS1Devices466X466.a(soccerData, arrayList, i);
    }

    public final void a(SoccerData soccerData, ArrayList<DynamicSportsField> arrayList, int i) {
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            int i2 = f5890a;
            arrayList.add(new DynamicSportFieldText(1, i2, 100, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 30, 30, 24, 0, soccerData.getTeamAShortName()));
            FontLengthUtility fontLengthUtility = FontLengthUtility.INSTANCE;
            arrayList.add(new DynamicSportFieldText(1, i2, 366 - FontLengthUtility.getStringWidth$default(fontLengthUtility, soccerData.getTeamBShortName(), 0, 2, null), CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 30, 30, 24, 0, soccerData.getTeamBShortName()));
            String teamAScore = soccerData.getTeamAScore();
            int i3 = b;
            arrayList.add(new DynamicSportFieldText(1, i3, soccerData.getTeamAShortName().length() > 2 ? 114 : 105, i, 30, 30, 28, 0, teamAScore));
            arrayList.add(new DynamicSportFieldText(1, i3, (466 - (soccerData.getTeamBShortName().length() > 2 ? 85 : 90)) - FontLengthUtility.getStringWidth$default(fontLengthUtility, soccerData.getTeamBShortName(), 0, 2, null), i, 30, 30, 28, 0, soccerData.getTeamBScore()));
            return;
        }
        int i4 = f5890a;
        arrayList.add(new DynamicSportFieldText(1, i4, 100, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 30, 30, 24, 0, soccerData.getTeamAShortName()));
        FontLengthUtility fontLengthUtility2 = FontLengthUtility.INSTANCE;
        arrayList.add(new DynamicSportFieldText(1, i4, 366 - FontLengthUtility.getStringWidth$default(fontLengthUtility2, soccerData.getTeamBShortName(), 0, 2, null), CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 30, 30, 24, 0, soccerData.getTeamBShortName()));
        String str = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + ") " + soccerData.getTeamAScore() + HexStringBuilder.COMMENT_END_CHAR;
        int i5 = b;
        arrayList.add(new DynamicSportFieldText(1, i5, soccerData.getTeamAShortName().length() > 2 ? 114 : 105, i, 30, 30, 28, 0, str));
        arrayList.add(new DynamicSportFieldText(1, i5, (466 - (soccerData.getTeamBShortName().length() > 2 ? 85 : 90)) - FontLengthUtility.getStringWidth$default(fontLengthUtility2, soccerData.getTeamBShortName(), 0, 2, null), i, 30, 30, 28, 0, HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBScore() + " (" + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR));
    }

    public final void c(SoccerData soccerData, ArrayList<DynamicSportsField> arrayList, int i) {
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            arrayList.add(new DynamicSportFieldText(1, f5890a, 114, i, 30, 30, 28, 0, soccerData.getTeamAShortName() + Soundex.SILENT_MARKER + soccerData.getTeamAScore() + " VS " + soccerData.getTeamBShortName() + Soundex.SILENT_MARKER + soccerData.getTeamBScore()));
            return;
        }
        arrayList.add(new DynamicSportFieldText(1, f5890a, 83, i, 30, 30, 28, 0, soccerData.getTeamAShortName() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + ") " + soccerData.getTeamAScore() + " VS " + soccerData.getTeamBShortName() + ' ' + soccerData.getTeamBScore() + " (" + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR));
    }

    public final ArrayList<DynamicSportsField> d(Event event) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
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
                        String substring = playerName.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(substring);
                        sb.append("..");
                        playerName = sb.toString();
                    }
                    if (str.length() > 30) {
                        StringBuilder sb2 = new StringBuilder();
                        String substring2 = str.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb2.append(substring2);
                        sb2.append("..");
                        str = sb2.toString();
                    }
                    FontLengthUtility fontLengthUtility = FontLengthUtility.INSTANCE;
                    int i = f5890a;
                    arrayList.add(new DynamicSportFieldText(6, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, str + " (" + event.getTeamName() + ") " + playerName, 0, 2, null)) / 2, 302, 30, 30, 24, 0, str + " (" + event.getTeamName() + ") " + playerName));
                    arrayList.add(new DynamicSportFieldText(7, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, "GOAL", 0, 2, null)) / 2, 387, 30, 30, 24, 0, "GOAL"));
                    break;
                case 2:
                    String str2 = event.getTime() + '\'';
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str2 = event.getTime() + "'+" + event.getInjuryTime() + '\'';
                    }
                    String playerName2 = event.getPlayerName();
                    if (playerName2.length() > 12) {
                        StringBuilder sb3 = new StringBuilder();
                        String substring3 = playerName2.substring(0, 11);
                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb3.append(substring3);
                        sb3.append("..");
                        playerName2 = sb3.toString();
                    }
                    if (str2.length() > 30) {
                        StringBuilder sb4 = new StringBuilder();
                        String substring4 = str2.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb4.append(substring4);
                        sb4.append("..");
                        str2 = sb4.toString();
                    }
                    FontLengthUtility fontLengthUtility2 = FontLengthUtility.INSTANCE;
                    int i2 = f5890a;
                    arrayList.add(new DynamicSportFieldText(6, i2, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility2, str2 + " (" + event.getTeamName() + ") " + playerName2, 0, 2, null)) / 2, 302, 30, 30, 24, 0, str2 + " (" + event.getTeamName() + ") " + playerName2));
                    arrayList.add(new DynamicSportFieldText(7, i2, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility2, "Penalty Shootout", 0, 2, null)) / 2, 387, 30, 30, 24, 0, "Penalty Shootout"));
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
                    if (str3.length() > 30) {
                        StringBuilder sb6 = new StringBuilder();
                        String substring6 = str3.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb6.append(substring6);
                        sb6.append("..");
                        str3 = sb6.toString();
                    }
                    FontLengthUtility fontLengthUtility3 = FontLengthUtility.INSTANCE;
                    int i3 = f5890a;
                    arrayList.add(new DynamicSportFieldText(6, i3, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility3, str3 + " (" + event.getTeamName() + ") " + playerName3, 0, 2, null)) / 2, 302, 30, 30, 24, 0, str3 + " (" + event.getTeamName() + ") " + playerName3));
                    arrayList.add(new DynamicSportFieldText(7, i3, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility3, "GOAL - PEN", 0, 2, null)) / 2, 387, 30, 30, 24, 0, "GOAL - PEN"));
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
                    if (str4.length() > 30) {
                        StringBuilder sb8 = new StringBuilder();
                        String substring8 = str4.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring8, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb8.append(substring8);
                        sb8.append("..");
                        str4 = sb8.toString();
                    }
                    FontLengthUtility fontLengthUtility4 = FontLengthUtility.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, f5890a, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility4, str4 + " (" + event.getTeamName() + ") " + playerName4, 0, 2, null)) / 2, TGEventListener.OTA_COMPLETED, 30, 30, 24, 0, str4 + " (" + event.getTeamName() + ") " + playerName4));
                    arrayList.add(new DynamicSportFieldRectangle(7, c, 220, 391, 26, 31));
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
                    if (str5.length() > 30) {
                        StringBuilder sb10 = new StringBuilder();
                        String substring10 = str5.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring10, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb10.append(substring10);
                        sb10.append("..");
                        str5 = sb10.toString();
                    }
                    FontLengthUtility fontLengthUtility5 = FontLengthUtility.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, f5890a, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility5, str5 + " (" + event.getTeamName() + ") " + playerName5, 0, 2, null)) / 2, TGEventListener.OTA_COMPLETED, 30, 30, 24, 0, str5 + " (" + event.getTeamName() + ") " + playerName5));
                    arrayList.add(new DynamicSportFieldRectangle(7, c, 230, 409, 26, 31));
                    arrayList.add(new DynamicSportFieldRectangle(8, d, 220, 391, 26, 31));
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
                    if (str6.length() > 30) {
                        StringBuilder sb12 = new StringBuilder();
                        String substring12 = str6.substring(0, 28);
                        Intrinsics.checkNotNullExpressionValue(substring12, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb12.append(substring12);
                        sb12.append("..");
                        str6 = sb12.toString();
                    }
                    FontLengthUtility fontLengthUtility6 = FontLengthUtility.INSTANCE;
                    arrayList.add(new DynamicSportFieldText(6, f5890a, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility6, str6 + " (" + event.getTeamName() + ") " + playerName6, 0, 2, null)) / 2, TGEventListener.OTA_COMPLETED, 30, 30, 24, 0, str6 + " (" + event.getTeamName() + ") " + playerName6));
                    arrayList.add(new DynamicSportFieldRectangle(7, d, 220, 391, 26, 31));
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
                    FontLengthUtility fontLengthUtility7 = FontLengthUtility.INSTANCE;
                    int i4 = f5890a;
                    arrayList.add(new DynamicSportFieldText(6, i4, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility7, str8, 0, 2, null)) / 2, TGEventListener.SLEEP_START, 30, 30, 24, 0, str8));
                    String str9 = "In " + playerIn;
                    arrayList.add(new DynamicSportFieldText(6, i4, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility7, str9, 0, 2, null)) / 2, b.m1, 30, 30, 24, 0, str9));
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
                    arrayList.add(new DynamicSportFieldText(7, i4, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility7, str10, 0, 2, null)) / 2, 374, 30, 30, 24, 0, str10));
                    break;
            }
        }
        return arrayList;
    }

    public final int e(SoccerData soccerData) {
        String leageName = soccerData.getLeageName();
        if (soccerData.getLeageName().length() > 22) {
            StringBuilder sb = new StringBuilder();
            String substring = soccerData.getLeageName().substring(0, 21);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("..");
            leageName = sb.toString();
        }
        return (466 - FontLengthUtility.INSTANCE.getStringWidth(leageName, 28)) / 2;
    }

    public final ArrayList<DynamicSportsField> f(SoccerData soccerData) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        if (soccerData.getSoccerSummary() != null) {
            int i = f5890a;
            Intrinsics.checkNotNull(soccerData);
            SoccerSummary soccerSummary = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary);
            arrayList.add(new DynamicSportFieldText(3, i, 83, Command.CMD_GET_EXTERNAL_FLASH_MSG, 30, 30, 24, 0, String.valueOf(soccerSummary.getShotsOnTarget()[0].intValue())));
            FontLengthUtility fontLengthUtility = FontLengthUtility.INSTANCE;
            arrayList.add(new DynamicSportFieldText(3, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, "Shots on Tgt", 0, 2, null)) / 2, Command.CMD_GET_EXTERNAL_FLASH_MSG, 30, 30, 24, 0, "Shots on Tgt"));
            SoccerSummary soccerSummary2 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary2);
            SoccerSummary soccerSummary3 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary3);
            arrayList.add(new DynamicSportFieldText(3, i, 383 - FontLengthUtility.getStringWidth$default(fontLengthUtility, String.valueOf(soccerSummary2.getShotsOnTarget()[1].intValue()), 0, 2, null), Command.CMD_GET_EXTERNAL_FLASH_MSG, 30, 30, 24, 0, String.valueOf(soccerSummary3.getShotsOnTarget()[1].intValue())));
            StringBuilder sb = new StringBuilder();
            SoccerSummary soccerSummary4 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary4);
            sb.append(soccerSummary4.getPossession()[0].intValue());
            sb.append('%');
            arrayList.add(new DynamicSportFieldText(3, i, 83, DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR, 30, 30, 24, 0, sb.toString()));
            arrayList.add(new DynamicSportFieldText(3, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, "Possn", 0, 2, null)) / 2, DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR, 30, 30, 24, 0, "Possn"));
            StringBuilder sb2 = new StringBuilder();
            SoccerSummary soccerSummary5 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary5);
            sb2.append(soccerSummary5.getPossession()[1].intValue());
            sb2.append('%');
            StringBuilder sb3 = new StringBuilder();
            SoccerSummary soccerSummary6 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary6);
            sb3.append(soccerSummary6.getPossession()[1].intValue());
            sb3.append('%');
            arrayList.add(new DynamicSportFieldText(3, i, 383 - FontLengthUtility.getStringWidth$default(fontLengthUtility, sb2.toString(), 0, 2, null), DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR, 30, 30, 24, 0, sb3.toString()));
            StringBuilder sb4 = new StringBuilder();
            SoccerSummary soccerSummary7 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary7);
            sb4.append(soccerSummary7.getYellowCards()[0].intValue());
            sb4.append('/');
            SoccerSummary soccerSummary8 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary8);
            sb4.append(soccerSummary8.getRedCards()[0].intValue());
            arrayList.add(new DynamicSportFieldText(3, i, 83, 316, 30, 30, 24, 0, sb4.toString()));
            arrayList.add(new DynamicSportFieldText(3, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, "Y/R Card", 0, 2, null)) / 2, 316, 30, 30, 24, 0, "Y/R Card"));
            StringBuilder sb5 = new StringBuilder();
            SoccerSummary soccerSummary9 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary9);
            sb5.append(soccerSummary9.getYellowCards()[1].intValue());
            sb5.append('/');
            SoccerSummary soccerSummary10 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary10);
            sb5.append(soccerSummary10.getRedCards()[1].intValue());
            StringBuilder sb6 = new StringBuilder();
            SoccerSummary soccerSummary11 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary11);
            sb6.append(soccerSummary11.getYellowCards()[1].intValue());
            sb6.append('/');
            SoccerSummary soccerSummary12 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary12);
            sb6.append(soccerSummary12.getRedCards()[1].intValue());
            arrayList.add(new DynamicSportFieldText(3, i, 383 - FontLengthUtility.getStringWidth$default(fontLengthUtility, sb5.toString(), 0, 2, null), 316, 30, 30, 24, 0, sb6.toString()));
            SoccerSummary soccerSummary13 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary13);
            arrayList.add(new DynamicSportFieldText(3, i, 83, 366, 30, 30, 24, 0, String.valueOf(soccerSummary13.getFouls()[0].intValue())));
            arrayList.add(new DynamicSportFieldText(3, i, (466 - FontLengthUtility.getStringWidth$default(fontLengthUtility, "Fouls", 0, 2, null)) / 2, 366, 30, 30, 24, 0, "Fouls"));
            SoccerSummary soccerSummary14 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary14);
            SoccerSummary soccerSummary15 = soccerData.getSoccerSummary();
            Intrinsics.checkNotNull(soccerSummary15);
            arrayList.add(new DynamicSportFieldText(3, i, 383 - FontLengthUtility.getStringWidth$default(fontLengthUtility, String.valueOf(soccerSummary14.getFouls()[1].intValue()), 0, 2, null), 366, 30, 30, 24, 0, String.valueOf(soccerSummary15.getFouls()[1].intValue())));
        }
        return arrayList;
    }

    public final Calendar g(String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Intrinsics.checkNotNull(str);
        calendar.setTime(simpleDateFormat.parse(str));
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData) {
        String leageName;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60002));
        int e = e(soccerData);
        int i = f5890a;
        if (soccerData.getLeageName().length() > 22) {
            StringBuilder sb = new StringBuilder();
            String substring = soccerData.getLeageName().substring(0, 21);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("..");
            leageName = sb.toString();
        } else {
            leageName = soccerData.getLeageName();
        }
        arrayList.add(new DynamicSportFieldText(1, i, e, 94, 30, 30, 28, 0, leageName));
        b(this, soccerData, arrayList, 0, 4, null);
        arrayList.add(new DynamicSportFieldText(1, i, (466 - FontLengthUtility.INSTANCE.getStringWidth("Match cancelled", 28)) / 2, 387, 30, 30, 28, 0, "Match cancelled"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData) {
        String leageName;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60002));
        int e = e(soccerData);
        int i = f5890a;
        if (soccerData.getLeageName().length() > 22) {
            StringBuilder sb = new StringBuilder();
            String substring = soccerData.getLeageName().substring(0, 21);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("..");
            leageName = sb.toString();
        } else {
            leageName = soccerData.getLeageName();
        }
        arrayList.add(new DynamicSportFieldText(1, i, e, 94, 30, 30, 28, 0, leageName));
        b(this, soccerData, arrayList, 0, 4, null);
        String startTime = soccerData.getStartTime();
        Intrinsics.checkNotNull(startTime);
        String dateStr = AppUtils.formatDate(g(startTime).getTime(), "hh:mm a");
        FontLengthUtility fontLengthUtility = FontLengthUtility.INSTANCE;
        arrayList.add(new DynamicSportFieldText(1, i, (466 - fontLengthUtility.getStringWidth("Match will start at", 28)) / 2, b.p1, 30, 30, 28, 0, "Match will start at"));
        Intrinsics.checkNotNullExpressionValue(dateStr, "dateStr");
        arrayList.add(new DynamicSportFieldText(1, i, (466 - fontLengthUtility.getStringWidth(dateStr, 28)) / 2, 387, 30, 30, 28, 0, dateStr));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getNoMatchSelectedRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60002));
        arrayList.add(new DynamicSportFieldText(1, f5890a, (466 - FontLengthUtility.INSTANCE.getStringWidth("No Match Selected", 28)) / 2, 387, 30, 30, 28, 0, "No Match Selected"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getOfflineRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60002));
        arrayList.add(new DynamicSportFieldText(1, f5890a, (466 - FontLengthUtility.INSTANCE.getStringWidth("Offline", 28)) / 2, 387, 30, 30, 28, 0, "Offline"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getSoccerEventRequest(@NotNull SoccerData soccerData) {
        String leageName;
        String str;
        StringBuilder sb;
        String injuryTime;
        StringBuilder sb2;
        String injuryTime2;
        StringBuilder sb3;
        String injuryTime3;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60002));
        int e = e(soccerData);
        int i = f5890a;
        if (soccerData.getLeageName().length() > 22) {
            StringBuilder sb4 = new StringBuilder();
            String substring = soccerData.getLeageName().substring(0, 21);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb4.append(substring);
            sb4.append("..");
            leageName = sb4.toString();
        } else {
            leageName = soccerData.getLeageName();
        }
        arrayList.add(new DynamicSportFieldText(1, i, e, 94, 30, 30, 28, 0, leageName));
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
        arrayList.add(new DynamicSportFieldText(3, i, (466 - FontLengthUtility.getStringWidth$default(FontLengthUtility.INSTANCE, str, 0, 2, null)) / 2, 260, 30, 30, 24, 0, str));
        if (soccerData.getEvent() != null) {
            arrayList.addAll(d(soccerData.getEvent()));
        }
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getSoccerSummaryRequest(@NotNull SoccerData soccerData) {
        String leageName;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 60001));
        int e = e(soccerData);
        int i = f5890a;
        if (soccerData.getLeageName().length() > 22) {
            StringBuilder sb = new StringBuilder();
            String substring = soccerData.getLeageName().substring(0, 21);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring);
            sb.append("..");
            leageName = sb.toString();
        } else {
            leageName = soccerData.getLeageName();
        }
        arrayList.add(new DynamicSportFieldText(1, i, e, 94, 30, 30, 28, 0, leageName));
        c(soccerData, arrayList, 166);
        arrayList.addAll(f(soccerData));
        return arrayList;
    }
}
