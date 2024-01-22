package com.coveiot.android.dashboard2.util;

import android.content.Context;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.utils.RrHrHelperKt;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.utils.utility.AppUtils;
import java.util.Calendar;
import java.util.Date;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class StressDataHelper {
    @NotNull
    public static final StressDataHelper INSTANCE = new StressDataHelper();

    @NotNull
    public final Pair<Integer, Calendar> getStressBy(@NotNull HourlyStress hourlyStress) {
        Intrinsics.checkNotNullParameter(hourlyStress, "hourlyStress");
        int size = 60 / hourlyStress.getCodevalue().size();
        int size2 = hourlyStress.getCodevalue().size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size2; i3++) {
            Integer num = hourlyStress.getCodevalue().get(i3);
            Intrinsics.checkNotNullExpressionValue(num, "hourlyStress.codevalue.get(i)");
            if (num.intValue() > 0) {
                Integer num2 = hourlyStress.getCodevalue().get(i3);
                Intrinsics.checkNotNullExpressionValue(num2, "hourlyStress.codevalue.get(i)");
                i2 = num2.intValue();
                i = i3;
            }
        }
        Date parseDate = AppUtils.parseDate(hourlyStress.getmDate() + ' ' + hourlyStress.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Integer.valueOf(i2), calendar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r3.equals("Mild") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0032, code lost:
        if (r3.equals("Moderate") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003b, code lost:
        if (r3.equals("Relaxed") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0043, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004a, code lost:
        if (r3.equals(com.coveiot.android.leonardo.utils.RrHrHelperKt.b) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0052, code lost:
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r3.equals("Medium") == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0061, code lost:
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0011, code lost:
        if (r3.equals("Relax") == false) goto L28;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Integer getStressIndexExcludingZero(@org.jetbrains.annotations.Nullable java.lang.String r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L62
            int r1 = r3.hashCode()
            switch(r1) {
                case -1994163307: goto L53;
                case -1955878649: goto L44;
                case -1539816689: goto L35;
                case -554213085: goto L2c;
                case 2249154: goto L1d;
                case 2398260: goto L14;
                case 78844528: goto Lb;
                default: goto La;
            }
        La:
            goto L62
        Lb:
            java.lang.String r1 = "Relax"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L3e
            goto L62
        L14:
            java.lang.String r1 = "Mild"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L4d
            goto L62
        L1d:
            java.lang.String r1 = "High"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L26
            goto L62
        L26:
            r3 = 3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L2c:
            java.lang.String r1 = "Moderate"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L5c
            goto L62
        L35:
            java.lang.String r1 = "Relaxed"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L3e
            goto L62
        L3e:
            r3 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L44:
            java.lang.String r1 = "Normal"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L4d
            goto L62
        L4d:
            r3 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L53:
            java.lang.String r1 = "Medium"
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L5c
            goto L62
        L5c:
            r3 = 2
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            return r3
        L62:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dashboard2.util.StressDataHelper.getStressIndexExcludingZero(java.lang.String):java.lang.Integer");
    }

    @NotNull
    public final String getStressRange(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        boolean z = true;
        if (companion.isSmaDevice(context)) {
            if (!(1 <= i && i < 26)) {
                if (26 <= i && i < 51) {
                    return RrHrHelperKt.b;
                }
                if (51 <= i && i < 76) {
                    return "Moderate";
                }
                if (76 > i || i >= 101) {
                    z = false;
                }
                if (z) {
                    return RrHrHelperKt.c;
                }
            }
            return "Relax";
        } else if (!companion.isCADevice(context) && !companion.isCYDevice(context) && !companion.isPS1Device(context) && !companion.isBESDevice(context)) {
            if (1 <= i && i < 30) {
                return "Relax";
            }
            if (30 <= i && i < 60) {
                return RrHrHelperKt.b;
            }
            if (60 <= i && i < 80) {
                return "Medium";
            }
            if (80 > i || i >= 100) {
                z = false;
            }
            return z ? RrHrHelperKt.c : RrHrHelperKt.f5433a;
        } else {
            if (!(1 <= i && i < 26)) {
                if (26 <= i && i < 51) {
                    return "Mild";
                }
                if (51 <= i && i < 76) {
                    return "Moderate";
                }
                if (76 > i || i >= 101) {
                    z = false;
                }
                if (z) {
                    return RrHrHelperKt.c;
                }
            }
            return "Relaxed";
        }
    }

    @Nullable
    public final String getStressRangeExcludingZero(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        boolean z = true;
        if (companion.isSmaDevice(context)) {
            if (1 <= i && i < 26) {
                return "Relaxed";
            }
            if (26 <= i && i < 51) {
                return RrHrHelperKt.b;
            }
            if (51 <= i && i < 76) {
                return "Moderate";
            }
            if (76 > i || i >= 101) {
                z = false;
            }
            if (z) {
                return RrHrHelperKt.c;
            }
            return null;
        } else if (companion.isCADevice(context) || companion.isCYDevice(context) || companion.isPS1Device(context) || companion.isBESDevice(context)) {
            if (1 <= i && i < 26) {
                return "Relaxed";
            }
            if (26 <= i && i < 51) {
                return "Mild";
            }
            if (51 <= i && i < 76) {
                return "Moderate";
            }
            if (76 > i || i >= 101) {
                z = false;
            }
            if (z) {
                return RrHrHelperKt.c;
            }
            return null;
        } else {
            if (1 <= i && i < 30) {
                return "Relax";
            }
            if (30 <= i && i < 60) {
                return RrHrHelperKt.b;
            }
            if (60 <= i && i < 80) {
                return "Medium";
            }
            if (80 > i || i >= 100) {
                z = false;
            }
            if (z) {
                return RrHrHelperKt.c;
            }
            return null;
        }
    }

    @NotNull
    public final String getStressTextColor(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        String stressRange = getStressRange(i, context);
        if (m.equals(stressRange, "Anxious", true) || m.equals(stressRange, RrHrHelperKt.c, true)) {
            return "#ae1b2e";
        }
        if (m.equals(stressRange, "Moderate", true)) {
            return "#ef7a3d";
        }
        if (m.equals(stressRange, "Mild", true) || m.equals(stressRange, RrHrHelperKt.b, true)) {
            return "#f8c236";
        }
        if (m.equals(stressRange, "Relaxed", true)) {
            return "#1fbba6";
        }
        m.equals(stressRange, "Rest", true);
        return "#1fbba6";
    }
}
