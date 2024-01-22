package com.coveiot.android.dashboard2.util;

import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.utils.utility.AppUtils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class HRVDataHelper {
    @NotNull
    public static final HRVDataHelper INSTANCE = new HRVDataHelper();

    @NotNull
    public final String calculationFormulaHRV(double d) {
        if (d > 0.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(Math.exp((((d - 3.4646797d) * 0.8099999999999996d) / 2.6128658999999996d) + 2.95d))}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("%.02f", Arrays.copyOf(new Object[]{Double.valueOf(1.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
        return format2;
    }

    @NotNull
    public final Pair<Double, Calendar> getHrvBy(@NotNull HourlyHRV hourlyHRV) {
        Intrinsics.checkNotNullParameter(hourlyHRV, "hourlyHRV");
        int size = 60 / hourlyHRV.getCodevalue().size();
        int size2 = hourlyHRV.getCodevalue().size();
        double d = 0.0d;
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            Double d2 = hourlyHRV.getCodevalue().get(i2);
            Intrinsics.checkNotNullExpressionValue(d2, "hourlyHRV.codevalue[i]");
            if (d2.doubleValue() > 0.0d) {
                Double d3 = hourlyHRV.getCodevalue().get(i2);
                Intrinsics.checkNotNullExpressionValue(d3, "hourlyHRV.codevalue.get(i)");
                d = d3.doubleValue();
                i = i2;
            }
        }
        Date parseDate = AppUtils.parseDate(hourlyHRV.getmDate() + ' ' + hourlyHRV.getStartTime(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate);
        calendar.add(12, size * i);
        return new Pair<>(Double.valueOf(d), calendar);
    }
}
