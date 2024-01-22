package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
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
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.c;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperCAULC5 implements SoccerHelper {
    @NotNull
    public static final SoccerHelperCAULC5 INSTANCE = new SoccerHelperCAULC5();

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

    public final Pair<String, Integer> a(SoccerData soccerData) {
        String str;
        int i = 20;
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "       " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "     " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "         " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "      " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "         " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() > 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "      " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "       " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "       " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "      " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "    " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "      " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "      " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "      " + soccerData.getTeamBShortName();
                }
                i = 30;
            } else {
                if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() > 2) {
                    if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                        str = soccerData.getTeamAShortName() + "      " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "      " + soccerData.getTeamBShortName();
                    } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                        str = soccerData.getTeamAShortName() + "     " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "       " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "     " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "      " + soccerData.getTeamBShortName();
                    }
                }
                str = "";
                i = 0;
            }
        } else {
            if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() == 2) {
                String str2 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str3 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str2.length() <= 5 && str3.length() <= 5) {
                    if (str2.length() == 5 && str3.length() == 5) {
                        str = soccerData.getTeamAShortName() + "   " + str2 + " - " + str3 + "       " + soccerData.getTeamBShortName();
                    } else if (str2.length() != 5 && str3.length() != 5) {
                        str = soccerData.getTeamAShortName() + "     " + str2 + " - " + str3 + "         " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "    " + str2 + " - " + str3 + "      " + soccerData.getTeamBShortName();
                    }
                } else if (str2.length() > 5 && str3.length() > 5) {
                    str = soccerData.getTeamAShortName() + ' ' + str2 + " - " + str3 + "    " + soccerData.getTeamBShortName();
                } else if (str2.length() != 4 && str3.length() != 4) {
                    str = soccerData.getTeamAShortName() + "      " + str2 + " - " + str3 + "     " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "   " + str2 + " - " + str3 + "      " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() > 2) {
                String str4 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str5 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str4.length() <= 5 && str5.length() <= 5) {
                    if (str4.length() == 5 && str5.length() == 5) {
                        str = soccerData.getTeamAShortName() + "    " + str4 + " - " + str5 + "  " + soccerData.getTeamBShortName();
                    } else if (str4.length() != 5 && str5.length() != 5) {
                        str = soccerData.getTeamAShortName() + "      " + str4 + " - " + str5 + "   " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "     " + str4 + " - " + str5 + "   " + soccerData.getTeamBShortName();
                    }
                } else if (str4.length() > 5 && str5.length() > 5) {
                    str = soccerData.getTeamAShortName() + "  " + str4 + " - " + str5 + ' ' + soccerData.getTeamBShortName();
                } else if (str4.length() != 4 && str5.length() != 4) {
                    str = soccerData.getTeamAShortName() + "    " + str4 + " - " + str5 + "  " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "    " + str4 + " - " + str5 + "  " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() == 2) {
                String str6 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str7 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str6.length() <= 5 && str7.length() <= 5) {
                    if (str6.length() == 5 && str7.length() == 5) {
                        str = soccerData.getTeamAShortName() + "     " + str6 + " - " + str7 + "     " + soccerData.getTeamBShortName();
                    } else if (str6.length() != 5 && str7.length() != 5) {
                        str = soccerData.getTeamAShortName() + "       " + str6 + " - " + str7 + "       " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "      " + str6 + " - " + str7 + "      " + soccerData.getTeamBShortName();
                    }
                } else if (str6.length() > 5 && str7.length() > 5) {
                    str = soccerData.getTeamAShortName() + "    " + str6 + " - " + str7 + "    " + soccerData.getTeamBShortName();
                } else if (str6.length() != 4 && str7.length() != 4) {
                    str = soccerData.getTeamAShortName() + "     " + str6 + " - " + str7 + "     " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "     " + str6 + " - " + str7 + "     " + soccerData.getTeamBShortName();
                }
            } else {
                if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() > 2) {
                    String str8 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                    String str9 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                    if (str8.length() <= 5 && str9.length() <= 5) {
                        if (str8.length() == 5 && str9.length() == 5) {
                            str = soccerData.getTeamAShortName() + "   " + str8 + " - " + str9 + "   " + soccerData.getTeamBShortName();
                        } else if (str8.length() != 5 && str9.length() != 5) {
                            str = soccerData.getTeamAShortName() + "    " + str8 + " - " + str9 + "     " + soccerData.getTeamBShortName();
                        } else {
                            str = soccerData.getTeamAShortName() + "   " + str8 + " - " + str9 + "    " + soccerData.getTeamBShortName();
                        }
                    } else if (str8.length() > 5 && str9.length() > 5) {
                        str = soccerData.getTeamAShortName() + ' ' + str8 + " - " + str9 + "  " + soccerData.getTeamBShortName();
                    } else if (str8.length() != 4 && str9.length() != 4) {
                        str = soccerData.getTeamAShortName() + "   " + str8 + " - " + str9 + "  " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "   " + str8 + " - " + str9 + "   " + soccerData.getTeamBShortName();
                    }
                }
                str = "";
                i = 0;
            }
            i = 10;
        }
        return new Pair<>(str, Integer.valueOf(i));
    }

    public final ArrayList<DynamicSportsField> b(Event event) {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        BleUtils.convertHexTo565("#32c5ff");
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        BleUtils.convertHexTo565("#fcff00");
        if (event != null) {
            EventType type = event.getType();
            switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    String str = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str.length() > 22) {
                        StringBuilder sb = new StringBuilder();
                        String substring = str.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(substring);
                        sb.append("..");
                        str = sb.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 35, 180, 24, 24, 0, 0, str));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 106, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Goal"));
                    break;
                case 2:
                    String str2 = HexStringBuilder.COMMENT_BEGIN_CHAR + event.getTeamName() + ") " + event.getPlayerName();
                    if (str2.length() > 22) {
                        StringBuilder sb2 = new StringBuilder();
                        String substring2 = str2.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb2.append(substring2);
                        sb2.append("..");
                        str2 = sb2.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 35, 180, 24, 24, 0, 0, str2));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 60, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Penalty Shootout"));
                    break;
                case 3:
                    String str3 = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str3 = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str3.length() > 22) {
                        StringBuilder sb3 = new StringBuilder();
                        String substring3 = str3.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb3.append(substring3);
                        sb3.append("..");
                        str3 = sb3.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 35, 180, 24, 24, 0, 0, str3));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 76, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Goal (PEN)"));
                    break;
                case 4:
                    String str4 = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str4 = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str4.length() > 22) {
                        StringBuilder sb4 = new StringBuilder();
                        String substring4 = str4.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb4.append(substring4);
                        sb4.append("..");
                        str4 = sb4.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, 180, 24, 24, 0, 0, str4));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 82, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Yellow Card"));
                    break;
                case 5:
                    String str5 = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str5 = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str5.length() > 22) {
                        StringBuilder sb5 = new StringBuilder();
                        String substring5 = str5.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb5.append(substring5);
                        sb5.append("..");
                        str5 = sb5.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 35, 180, 24, 24, 0, 0, str5));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 50, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Yellow Red Card"));
                    break;
                case 6:
                    String str6 = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str6 = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str6.length() > 22) {
                        StringBuilder sb6 = new StringBuilder();
                        String substring6 = str6.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb6.append(substring6);
                        sb6.append("..");
                        str6 = sb6.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, 180, 24, 24, 0, 0, str6));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 87, Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, "Red Card"));
                    break;
                case 7:
                    String str7 = event.getTime() + "' SUB (" + event.getTeamName() + HexStringBuilder.COMMENT_END_CHAR;
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str7 = event.getTime() + "'+" + event.getInjuryTime() + " SUB (" + event.getTeamName() + HexStringBuilder.COMMENT_END_CHAR;
                    }
                    String str8 = str7;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 184 - ((str8.length() / 2) * 14), 180, 24, 24, 0, 0, str8));
                    String str9 = "IN - " + event.getPlayerIn();
                    if (str9.length() > 20) {
                        StringBuilder sb7 = new StringBuilder();
                        String substring7 = str9.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring7, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb7.append(substring7);
                        sb7.append("..");
                        str9 = sb7.toString();
                    }
                    String str10 = str9;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 184 - ((str10.length() / 2) * 15), Command.CMD_RECEIVE_SPEECH_CANCEL, 24, 24, 0, 0, str10));
                    String str11 = "OUT - " + event.getPlayerOut();
                    if (str11.length() > 20) {
                        StringBuilder sb8 = new StringBuilder();
                        String substring8 = str11.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring8, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb8.append(substring8);
                        sb8.append("..");
                        str11 = sb8.toString();
                    }
                    String str12 = str11;
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 184 - ((str12.length() / 2) * 15), 240, 24, 24, 0, 0, str12));
                    break;
            }
        }
        return arrayList;
    }

    public final int c(SoccerData soccerData) {
        int i;
        int roundToInt = c.roundToInt(soccerData.getLeageName().length() / 2.0f);
        int i2 = 184 - (roundToInt * 15);
        if (soccerData.getLeageName().length() <= 13) {
            i = roundToInt * 16;
        } else if (soccerData.getLeageName().length() % 2 != 0) {
            return i2;
        } else {
            i = roundToInt * 14;
        }
        return 184 - i;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x056a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList<com.coveiot.android.bleabstract.models.DynamicSportsField> d(com.coveiot.android.sportsnotification.utils.SoccerData r18) {
        /*
            Method dump skipped, instructions count: 1485
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.utils.SoccerHelperCAULC5.d(com.coveiot.android.sportsnotification.utils.SoccerData):java.util.ArrayList");
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, c(soccerData), 50, 24, 24, 0, 0, soccerData.getLeageName()));
        Pair<String, Integer> a2 = a(soccerData);
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, a2.component2().intValue(), 142, 24, 24, 0, 0, a2.component1()));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 76, 240, 24, 24, 0, 0, "Match canceled"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData) {
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, c(soccerData), 50, 24, 24, 0, 0, soccerData.getLeageName()));
        Pair<String, Integer> a2 = a(soccerData);
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, a2.component2().intValue(), 142, 24, 24, 0, 0, a2.component1()));
        StringBuilder sb = new StringBuilder();
        sb.append("Match will start at ");
        String startTime = soccerData.getStartTime();
        Intrinsics.checkNotNull(startTime);
        sb.append(AppUtils.formatDate(getStartTime(startTime).getTime(), "hh:mm a"));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 13, 240, 24, 24, 0, 0, sb.toString()));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getNoMatchSelectedRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 60, 240, 24, 24, 0, 0, "No Match Selected"));
        return arrayList;
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getOfflineRequest() {
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 90, 240, 24, 24, 0, 0, "Offline"));
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0145, code lost:
        if (java.lang.Integer.parseInt(r14.getInjuryTime()) < 10) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0173, code lost:
        if (java.lang.Integer.parseInt(r14.getInjuryTime()) < 10) goto L18;
     */
    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<com.coveiot.android.bleabstract.models.DynamicSportsField> getSoccerEventRequest(@org.jetbrains.annotations.NotNull com.coveiot.android.sportsnotification.utils.SoccerData r14) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.utils.SoccerHelperCAULC5.getSoccerEventRequest(com.coveiot.android.sportsnotification.utils.SoccerData):java.util.ArrayList");
    }

    @Override // com.coveiot.android.sportsnotification.utils.SoccerHelper
    @NotNull
    public ArrayList<DynamicSportsField> getSoccerSummaryRequest(@NotNull SoccerData soccerData) {
        String str;
        String str2;
        int i;
        Intrinsics.checkNotNullParameter(soccerData, "soccerData");
        int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
        ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
        arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
        arrayList.add(new DynamicSportFieldText(1, convertHexTo565, c(soccerData), 50, 24, 24, 0, 0, soccerData.getLeageName()));
        if (Integer.parseInt(soccerData.getTeamAPenaltyScore()) == 0 && Integer.parseInt(soccerData.getTeamBPenaltyScore()) == 0) {
            if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "         " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() > 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "            " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "           " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str = soccerData.getTeamAShortName() + "            " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "           " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                }
            } else {
                if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() > 2) {
                    if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                        str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "        " + soccerData.getTeamBShortName();
                    } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                        str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "         " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "         " + soccerData.getTeamBShortName();
                    }
                }
                str2 = "";
                i = 0;
            }
            str2 = str;
            i = 30;
        } else {
            if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() == 2) {
                String str3 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str4 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str3.length() <= 5 && str4.length() <= 5) {
                    if (str3.length() == 5 && str4.length() == 5) {
                        str = soccerData.getTeamAShortName() + "   " + str3 + " - " + str4 + "    " + soccerData.getTeamBShortName();
                    } else if (str3.length() != 5 && str4.length() != 5) {
                        str = soccerData.getTeamAShortName() + "     " + str3 + " - " + str4 + "      " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "    " + str3 + " - " + str4 + "    " + soccerData.getTeamBShortName();
                    }
                } else if (str3.length() > 5 && str4.length() > 5) {
                    str = soccerData.getTeamAShortName() + ' ' + str3 + " - " + str4 + "  " + soccerData.getTeamBShortName();
                } else if (str3.length() != 4 && str4.length() != 4) {
                    str = soccerData.getTeamAShortName() + ' ' + str3 + " - " + str4 + "   " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "   " + str3 + " - " + str4 + "    " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() > 2) {
                String str5 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str6 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str5.length() <= 5 && str6.length() <= 5) {
                    if (str5.length() == 5 && str6.length() == 5) {
                        str = soccerData.getTeamAShortName() + "      " + str5 + " - " + str6 + "   " + soccerData.getTeamBShortName();
                    } else if (str5.length() != 5 && str6.length() != 5) {
                        str = soccerData.getTeamAShortName() + "        " + str5 + " - " + str6 + "    " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "       " + str5 + " - " + str6 + "    " + soccerData.getTeamBShortName();
                    }
                } else if (str5.length() > 5 && str6.length() > 5) {
                    str = soccerData.getTeamAShortName() + "    " + str5 + " - " + str6 + "  " + soccerData.getTeamBShortName();
                } else if (str5.length() != 4 && str6.length() != 4) {
                    str = soccerData.getTeamAShortName() + "    " + str5 + " - " + str6 + "  " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "      " + str5 + " - " + str6 + "   " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() == 2) {
                String str7 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                String str8 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                if (str7.length() <= 5 && str8.length() <= 5) {
                    if (str7.length() == 5 && str8.length() == 5) {
                        str = soccerData.getTeamAShortName() + "      " + str7 + " - " + str8 + "    " + soccerData.getTeamBShortName();
                    } else if (str7.length() != 5 && str8.length() != 5) {
                        str = soccerData.getTeamAShortName() + "        " + str7 + " - " + str8 + "      " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "       " + str7 + " - " + str8 + "     " + soccerData.getTeamBShortName();
                    }
                } else if (str7.length() > 5 && str8.length() > 5) {
                    str = soccerData.getTeamAShortName() + "    " + str7 + " - " + str8 + "  " + soccerData.getTeamBShortName();
                } else if (str7.length() != 4 && str8.length() != 4) {
                    str = soccerData.getTeamAShortName() + "    " + str7 + " - " + str8 + "    " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "      " + str7 + " - " + str8 + "    " + soccerData.getTeamBShortName();
                }
            } else {
                if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() > 2) {
                    String str9 = HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamAPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR + soccerData.getTeamAScore();
                    String str10 = soccerData.getTeamBScore() + HexStringBuilder.COMMENT_BEGIN_CHAR + soccerData.getTeamBPenaltyScore() + HexStringBuilder.COMMENT_END_CHAR;
                    if (str9.length() <= 5 && str10.length() <= 5) {
                        if (str9.length() == 5 && str10.length() == 5) {
                            str = soccerData.getTeamAShortName() + "   " + str9 + " - " + str10 + "  " + soccerData.getTeamBShortName();
                        } else if (str9.length() != 5 && str10.length() != 5) {
                            str = soccerData.getTeamAShortName() + "     " + str9 + " - " + str10 + "     " + soccerData.getTeamBShortName();
                        } else {
                            str = soccerData.getTeamAShortName() + "    " + str9 + " - " + str10 + "    " + soccerData.getTeamBShortName();
                        }
                    } else if (str9.length() > 5 && str10.length() > 5) {
                        str = soccerData.getTeamAShortName() + ' ' + str9 + " - " + str10 + ' ' + soccerData.getTeamBShortName();
                    } else if (str9.length() != 4 && str10.length() != 4) {
                        str = soccerData.getTeamAShortName() + ' ' + str9 + " - " + str10 + "  " + soccerData.getTeamBShortName();
                    } else {
                        str = soccerData.getTeamAShortName() + "   " + str9 + " - " + str10 + "  " + soccerData.getTeamBShortName();
                    }
                }
                str2 = "";
                i = 0;
            }
            str2 = str;
            i = 30;
        }
        arrayList.add(new DynamicSportFieldText(3, convertHexTo565, i, 122, 24, 24, 0, 0, str2));
        arrayList.addAll(d(soccerData));
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
