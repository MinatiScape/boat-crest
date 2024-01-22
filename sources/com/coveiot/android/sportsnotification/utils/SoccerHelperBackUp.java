package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.touchgui.sdk.TGEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SoccerHelperBackUp {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes7.dex */
    public static final class Companion {

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
                    iArr[EventType.PENALTY_KICK.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EventType.YELLOW_CARD.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[EventType.RED_CARD.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[EventType.SUBSTITUTION.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Pair<String, Integer> a(SoccerData soccerData) {
            String str;
            int i = 30;
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
                    str = soccerData.getTeamAShortName() + "            " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else {
                    str = soccerData.getTeamAShortName() + "           " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() <= 2 || soccerData.getTeamBShortName().length() <= 2) {
                str = "";
                i = 0;
            } else if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                str = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
            } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                str = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
            } else {
                str = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
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
                int i = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
                if (i == 1) {
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
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, TGEventListener.SLEEP_STOP, 24, 24, 0, 0, str));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 156, 341, 24, 24, 0, 0, "Goal"));
                } else if (i == 2) {
                    String str2 = event.getTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str2 = event.getTime() + "'+" + event.getInjuryTime() + "' (" + event.getTeamName() + ") " + event.getPlayerName();
                    }
                    if (str2.length() > 22) {
                        StringBuilder sb2 = new StringBuilder();
                        String substring2 = str2.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb2.append(substring2);
                        sb2.append("..");
                        str2 = sb2.toString();
                    }
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, TGEventListener.SLEEP_STOP, 24, 24, 0, 0, str2));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 116, 341, 24, 24, 0, 0, "Goal (PEN)"));
                } else if (i == 3) {
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
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, TGEventListener.SLEEP_STOP, 24, 24, 0, 0, str3));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 112, 341, 24, 24, 0, 0, "Yellow Card"));
                } else if (i == 4) {
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
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 45, TGEventListener.SLEEP_STOP, 24, 24, 0, 0, str4));
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 127, 341, 24, 24, 0, 0, "Red Card"));
                } else if (i == 5) {
                    String str5 = event.getTime() + "' SUB (" + event.getTeamName() + HexStringBuilder.COMMENT_END_CHAR;
                    if (Integer.parseInt(event.getInjuryTime()) > 0) {
                        str5 = event.getTime() + "'+" + event.getInjuryTime() + " SUB (" + event.getTeamName() + HexStringBuilder.COMMENT_END_CHAR;
                    }
                    String str6 = str5;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 184 - ((str6.length() / 2) * 14), TGEventListener.SLEEP_STOP, 24, 24, 0, 0, str6));
                    String str7 = "IN - " + event.getPlayerIn();
                    if (str7.length() > 20) {
                        StringBuilder sb5 = new StringBuilder();
                        String substring5 = str7.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring5, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb5.append(substring5);
                        sb5.append("..");
                        str7 = sb5.toString();
                    }
                    String str8 = str7;
                    arrayList.add(new DynamicSportFieldText(6, convertHexTo565, 184 - ((str8.length() / 2) * 12), 341, 24, 24, 0, 0, str8));
                    String str9 = "OUT - " + event.getPlayerOut();
                    if (str9.length() > 20) {
                        StringBuilder sb6 = new StringBuilder();
                        String substring6 = str9.substring(0, 20);
                        Intrinsics.checkNotNullExpressionValue(substring6, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb6.append(substring6);
                        sb6.append("..");
                        str9 = sb6.toString();
                    }
                    String str10 = str9;
                    arrayList.add(new DynamicSportFieldText(7, convertHexTo565, 184 - ((str10.length() / 2) * 12), 381, 24, 24, 0, 0, str10));
                }
            }
            return arrayList;
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0200  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0262  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x02c2  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x039c  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x03bf  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x03e3  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0402  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x0443  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x04a4  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0505  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x0562  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.util.ArrayList<com.coveiot.android.bleabstract.models.DynamicSportsField> c(com.coveiot.android.sportsnotification.utils.SoccerData r19) {
            /*
                Method dump skipped, instructions count: 1475
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.utils.SoccerHelperBackUp.Companion.c(com.coveiot.android.sportsnotification.utils.SoccerData):java.util.ArrayList");
        }

        @NotNull
        public final ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData) {
            Intrinsics.checkNotNullParameter(soccerData, "soccerData");
            ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
            int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
            arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 184 - ((soccerData.getLeageName().length() / 2) * 15), 70, 24, 24, 0, 0, soccerData.getLeageName()));
            Pair<String, Integer> a2 = a(soccerData);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, a2.component2().intValue(), 192, 24, 24, 0, 0, a2.component1()));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 86, 391, 24, 24, 0, 0, "Match canceled"));
            return arrayList;
        }

        @NotNull
        public final ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData) {
            Intrinsics.checkNotNullParameter(soccerData, "soccerData");
            ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
            int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
            arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 184 - ((soccerData.getLeageName().length() / 2) * 15), 70, 24, 24, 0, 0, soccerData.getLeageName()));
            Pair<String, Integer> a2 = a(soccerData);
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, a2.component2().intValue(), 192, 24, 24, 0, 0, a2.component1()));
            StringBuilder sb = new StringBuilder();
            sb.append("Match will start at ");
            String startTime = soccerData.getStartTime();
            Intrinsics.checkNotNull(startTime);
            sb.append(AppUtils.formatDate(getStartTime(startTime).getTime(), "hh:mm a"));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 13, 391, 24, 24, 0, 0, sb.toString()));
            return arrayList;
        }

        @NotNull
        public final ArrayList<DynamicSportsField> getNoMatchSelectedRequest() {
            ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
            int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
            arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 70, 391, 24, 24, 0, 0, "No Match Selected"));
            return arrayList;
        }

        @NotNull
        public final ArrayList<DynamicSportsField> getOfflineRequest() {
            ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
            int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
            arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 143, 391, 24, 24, 0, 0, "Offline"));
            return arrayList;
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x00c8, code lost:
            if (java.lang.Integer.parseInt(r14.getInjuryTime()) < 10) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x00f6, code lost:
            if (java.lang.Integer.parseInt(r14.getInjuryTime()) < 10) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00f8, code lost:
            r4 = 80;
         */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.util.ArrayList<com.coveiot.android.bleabstract.models.DynamicSportsField> getSoccerEventRequest(@org.jetbrains.annotations.NotNull com.coveiot.android.sportsnotification.utils.SoccerData r14) {
            /*
                Method dump skipped, instructions count: 315
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.utils.SoccerHelperBackUp.Companion.getSoccerEventRequest(com.coveiot.android.sportsnotification.utils.SoccerData):java.util.ArrayList");
        }

        @NotNull
        public final ArrayList<DynamicSportsField> getSoccerSummaryRequest(@NotNull SoccerData soccerData) {
            String str;
            int i;
            String str2;
            Intrinsics.checkNotNullParameter(soccerData, "soccerData");
            int convertHexTo565 = BleUtils.convertHexTo565("#ffffff");
            ArrayList<DynamicSportsField> arrayList = new ArrayList<>();
            arrayList.add(new DynamicSportFieldImage(1, -1, 0, 0, -1, -1, 996));
            arrayList.add(new DynamicSportFieldText(1, convertHexTo565, 184 - ((soccerData.getLeageName().length() / 2) * 15), 70, 24, 24, 0, 0, soccerData.getLeageName()));
            if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str2 = soccerData.getTeamAShortName() + "         " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str2 = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else {
                    str2 = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() > 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str2 = soccerData.getTeamAShortName() + "            " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str2 = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else {
                    str2 = soccerData.getTeamAShortName() + "           " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() == 2 && soccerData.getTeamBShortName().length() == 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str2 = soccerData.getTeamAShortName() + "            " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str2 = soccerData.getTeamAShortName() + "          " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                } else {
                    str2 = soccerData.getTeamAShortName() + "           " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "           " + soccerData.getTeamBShortName();
                }
            } else if (soccerData.getTeamAShortName().length() > 2 && soccerData.getTeamBShortName().length() > 2) {
                if (soccerData.getTeamAScore().length() <= 1 && soccerData.getTeamBScore().length() <= 1) {
                    str2 = soccerData.getTeamAShortName() + "         " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else if (soccerData.getTeamAScore().length() > 1 && soccerData.getTeamBScore().length() > 1) {
                    str2 = soccerData.getTeamAShortName() + "       " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                } else {
                    str2 = soccerData.getTeamAShortName() + "        " + soccerData.getTeamAScore() + " - " + soccerData.getTeamBScore() + "          " + soccerData.getTeamBShortName();
                }
            } else {
                str = "";
                i = 0;
                arrayList.add(new DynamicSportFieldText(3, convertHexTo565, i, 192, 24, 24, 0, 0, str));
                arrayList.addAll(c(soccerData));
                return arrayList;
            }
            str = str2;
            i = 30;
            arrayList.add(new DynamicSportFieldText(3, convertHexTo565, i, 192, 24, 24, 0, 0, str));
            arrayList.addAll(c(soccerData));
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
}
