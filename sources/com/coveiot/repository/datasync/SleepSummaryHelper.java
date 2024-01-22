package com.coveiot.repository.datasync;

import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class SleepSummaryHelper {
    @NotNull
    public static final SleepSummaryHelper INSTANCE = new SleepSummaryHelper();

    public final byte[] a(List list) {
        byte[] bArr = new byte[1440];
        Arrays.fill(bArr, 0, 1440, (byte) -1);
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int size2 = ((SleepDataModelForLastNight) list.get(i)).getCodevalue().size();
            for (int i2 = 0; i2 < size2; i2++) {
                int timeIndex = getTimeIndex((SleepDataModelForLastNight) list.get(i), i2);
                if (timeIndex < 1440) {
                    Integer num = ((SleepDataModelForLastNight) list.get(i)).getCodevalue().get(i2);
                    if (num != null) {
                        if (num.intValue() > -1) {
                            bArr[timeIndex] = (byte) num.intValue();
                        }
                    } else {
                        bArr[timeIndex] = -1;
                    }
                }
            }
        }
        return bArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0175 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01bf  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.repository.datasync.SleepSummaryData getSleepScoreSummary(@org.jetbrains.annotations.NotNull android.content.Context r10, @org.jetbrains.annotations.NotNull java.util.Calendar r11) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.repository.datasync.SleepSummaryHelper.getSleepScoreSummary(android.content.Context, java.util.Calendar):com.coveiot.repository.datasync.SleepSummaryData");
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01ad A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0234 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0235  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.coveiot.repository.datasync.SleepSummaryData getSleepSummary(@org.jetbrains.annotations.NotNull android.content.Context r11, @org.jetbrains.annotations.NotNull java.util.Calendar r12) {
        /*
            Method dump skipped, instructions count: 571
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.repository.datasync.SleepSummaryHelper.getSleepSummary(android.content.Context, java.util.Calendar):com.coveiot.repository.datasync.SleepSummaryData");
    }

    public final int getTimeIndex(@NotNull SleepDataModelForLastNight sleepDataModelForLastNight, int i) {
        int parseInt;
        int parseInt2;
        Intrinsics.checkNotNullParameter(sleepDataModelForLastNight, "sleepDataModelForLastNight");
        String startTime = sleepDataModelForLastNight.getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "sleepDataModelForLastNight.startTime");
        Object[] array = StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
        Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        if (strArr.length > 1) {
            if (Integer.parseInt(strArr[0]) < 12) {
                parseInt = (Integer.parseInt(strArr[0]) + 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            } else {
                parseInt = (Integer.parseInt(strArr[0]) - 12) * 60;
                parseInt2 = Integer.parseInt(strArr[1]);
            }
            return parseInt + parseInt2 + i;
        }
        return -1;
    }
}
