package com.mappls.sdk.plugin.directions;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.Keep;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
@Keep
/* loaded from: classes11.dex */
public class DirectionFormatter {
    public static String getArrivalTime(double d) {
        return getArrivalTime(d, DateTimeFormat.DATE_DEFAULT);
    }

    public static String getArrivalTime(double d, DateTimeFormat dateTimeFormat) {
        DateFormat timeInstance;
        Date date;
        Date date2 = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date2);
        calendar.add(12, (int) ((d % 3600.0d) / 60.0d));
        calendar.add(10, (int) ((d % 86400.0d) / 3600.0d));
        calendar.add(5, (int) (d / 86400.0d));
        if (dateTimeFormat == DateTimeFormat.DATE_12_HOUR) {
            timeInstance = new SimpleDateFormat("hh:mm a", Locale.US);
            timeInstance.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
            date = new Date(calendar.getTimeInMillis());
        } else if (dateTimeFormat == DateTimeFormat.DATE_24_HOUR) {
            timeInstance = new SimpleDateFormat("HH:mm", Locale.US);
            timeInstance.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
            date = new Date(calendar.getTimeInMillis());
        } else {
            timeInstance = SimpleDateFormat.getTimeInstance(3);
            timeInstance.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
            date = new Date(calendar.getTimeInMillis());
        }
        return timeInstance.format(date);
    }

    public static String getFormattedDistance(double d) {
        return a.a(d, false, DistanceType.DISTANCE_KILOMETER).a();
    }

    public static String getFormattedDistance(double d, DistanceType distanceType) {
        return a.a(d, false, distanceType).a();
    }

    public static String getFormattedDistance(double d, boolean z) {
        return a.a(d, z, DistanceType.DISTANCE_KILOMETER).a();
    }

    public static String getFormattedDistance(double d, boolean z, DistanceType distanceType) {
        return a.a(d, z, distanceType).a();
    }

    public static SpannableString getFormattedDistanceRound(double d, float f) {
        return a.a(d, DistanceType.DISTANCE_KILOMETER, false, f, null);
    }

    public static SpannableString getFormattedDistanceRound(double d, float f, ForegroundColorSpan foregroundColorSpan) {
        return a.a(d, DistanceType.DISTANCE_KILOMETER, false, f, foregroundColorSpan);
    }

    public static SpannableString getFormattedDistanceRound(double d, DistanceType distanceType, float f) {
        return a.a(d, distanceType, false, f, null);
    }

    public static SpannableString getFormattedDistanceRound(double d, DistanceType distanceType, float f, ForegroundColorSpan foregroundColorSpan) {
        return a.a(d, distanceType, false, f, foregroundColorSpan);
    }

    public static String getFormattedDistanceRound(double d) {
        return a.a(d, DistanceType.DISTANCE_KILOMETER, false, 0.65f, null).toString();
    }

    public static String getFormattedDistanceRound(double d, DistanceType distanceType) {
        return a.a(d, distanceType, false, 0.65f, null).toString();
    }

    public static String getFormattedDistanceRound(double d, DistanceType distanceType, boolean z) {
        return a.a(d, distanceType, z, 0.65f, null).toString();
    }

    public static String getFormattedDistanceRound(double d, boolean z) {
        return a.a(d, DistanceType.DISTANCE_KILOMETER, z, 0.65f, null).toString();
    }

    public static String getFormattedDuration(double d) {
        StringBuilder sb;
        long j = (long) ((d % 3600.0d) / 60.0d);
        long j2 = (long) ((d % 86400.0d) / 3600.0d);
        long j3 = (long) (d / 86400.0d);
        String str = "";
        if (j3 > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(j3);
            sb2.append(" d ");
            if (j2 > 0) {
                str = HexStringBuilder.DEFAULT_SEPARATOR + j2 + " hr";
            }
            sb2.append(str);
            return sb2.toString();
        }
        if (j2 > 0) {
            sb = new StringBuilder();
            sb.append(j2);
            sb.append(" hr");
            if (j > 0) {
                str = HexStringBuilder.DEFAULT_SEPARATOR + j + " min";
            }
            sb.append(str);
        } else {
            sb = new StringBuilder();
            sb.append(j >= 1 ? Long.valueOf(j) : "1");
            sb.append(" min");
        }
        return sb.toString();
    }

    public static String getFormattedSpeed(double d) {
        return c.a(d, SpeedUnitType.KILOMETERS_PER_HOUR);
    }

    public static String getFormattedSpeed(double d, SpeedUnitType speedUnitType) {
        return c.a(d, speedUnitType);
    }
}
