package com.mappls.sdk.plugin.directions;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import androidx.annotation.NonNull;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfConversion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, String> f13089a = new HashMap();

    /* renamed from: com.mappls.sdk.plugin.directions.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0656a {

        /* renamed from: a  reason: collision with root package name */
        public final String f13090a;
        public final String b;
        public final boolean c;

        public C0656a(@NonNull String str, @NonNull String str2) {
            this(str, str2, true);
        }

        public C0656a(@NonNull String str, @NonNull String str2, boolean z) {
            this.f13090a = str;
            this.b = str2;
            this.c = z;
        }

        @NonNull
        public String a() {
            return this.c ? new MessageFormat("{0} {1}").format(new Object[]{this.f13090a, this.b}) : new MessageFormat("{0}{1}").format(new Object[]{this.f13090a, this.b});
        }
    }

    public static SpannableString a(double d, DistanceType distanceType, boolean z, float f, ForegroundColorSpan foregroundColorSpan) {
        boolean isEmpty = f13089a.isEmpty();
        String str = TurfConstants.UNIT_MILES;
        if (isEmpty) {
            f13089a.put("kilometers", "km");
            f13089a.put(TurfConstants.UNIT_METERS, "m");
            f13089a.put(TurfConstants.UNIT_MILES, "mi");
            f13089a.put(TurfConstants.UNIT_YARDS, "yd");
        }
        DistanceType distanceType2 = DistanceType.DISTANCE_KILOMETER;
        if (distanceType == distanceType2) {
            str = "kilometers";
        }
        String str2 = distanceType == distanceType2 ? TurfConstants.UNIT_METERS : TurfConstants.UNIT_FEET;
        double convertLength = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str2);
        double convertLength2 = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str);
        if (convertLength2 > 10.0d) {
            return convertLength2 >= 1000.0d ? b(String.format("%,d", Long.valueOf((long) convertLength2)), str, z, f, foregroundColorSpan) : b(d(convertLength2, 0), str, z, f, foregroundColorSpan);
        } else if (convertLength >= 999.0d) {
            return b(d(convertLength2, d > 9500.0d ? 0 : 1), str, z, f, foregroundColorSpan);
        } else if (d >= 975.0d) {
            return b(d(1.0d, d > 10000.0d ? 0 : 1), str, z, f, foregroundColorSpan);
        } else {
            return d > 300.0d ? b(c(convertLength, 50), str2, z, f, foregroundColorSpan) : convertLength > 100.0d ? b(c(convertLength, 10), str2, z, f, foregroundColorSpan) : b(c(convertLength, 5), str2, z, f, foregroundColorSpan);
        }
    }

    @NonNull
    public static C0656a a(double d, boolean z, DistanceType distanceType) {
        boolean isEmpty = f13089a.isEmpty();
        String str = TurfConstants.UNIT_MILES;
        if (isEmpty) {
            f13089a.put("kilometers", "km");
            f13089a.put(TurfConstants.UNIT_METERS, "m");
            f13089a.put(TurfConstants.UNIT_MILES, "mi");
            f13089a.put(TurfConstants.UNIT_YARDS, "yd");
        }
        DistanceType distanceType2 = DistanceType.DISTANCE_KILOMETER;
        if (distanceType == distanceType2) {
            str = "kilometers";
        }
        String str2 = distanceType == distanceType2 ? TurfConstants.UNIT_METERS : TurfConstants.UNIT_FEET;
        double convertLength = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str2);
        double convertLength2 = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str);
        return convertLength2 >= 100.0d ? a((int) (convertLength2 + 0.5d), str, z, 0) : convertLength2 > 9.9d ? a((int) convertLength2, str, z, 1) : convertLength2 > 0.9d ? a((int) convertLength2, str, z, 2) : a((int) (convertLength + 0.5d), str2, z, 0);
    }

    @NonNull
    public static C0656a a(float f, @NonNull String str, boolean z, int i) {
        char[] cArr;
        String str2 = BleConst.GetDeviceTime;
        if (i > 0) {
            Arrays.fill(new char[i], z ? '0' : '#');
            str2 = BleConst.GetDeviceTime + "." + String.valueOf(cArr);
        }
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setGroupingSeparator(' ');
        DecimalFormat decimalFormat = new DecimalFormat(str2);
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        if (Math.abs(f) >= 10000.0f) {
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);
        }
        MessageFormat messageFormat = new MessageFormat("{0}");
        messageFormat.setFormatByArgumentIndex(0, decimalFormat);
        return new C0656a(messageFormat.format(new Object[]{Float.valueOf(f)}).replace('\n', ' '), f13089a.get(str));
    }

    public static SpannableString b(String str, String str2, boolean z, float f, ForegroundColorSpan foregroundColorSpan) {
        String str3 = (z || foregroundColorSpan == null) ? "%s %s" : "%s%s";
        Object[] objArr = new Object[2];
        objArr[0] = str;
        if (!z) {
            str2 = f13089a.get(str2);
        }
        objArr[1] = str2;
        SpannableString spannableString = new SpannableString(String.format(str3, objArr));
        spannableString.setSpan(new StyleSpan(1), 0, str.length(), 33);
        spannableString.setSpan(new RelativeSizeSpan(f), str.length(), spannableString.length(), 33);
        if (foregroundColorSpan != null) {
            spannableString.setSpan(foregroundColorSpan, str.length(), spannableString.length(), 33);
        }
        return spannableString;
    }

    public static String c(double d, int i) {
        return String.valueOf(Math.max(((int) Math.round(d / i)) * i, i));
    }

    public static String d(double d, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
        numberInstance.setMaximumFractionDigits(i);
        return numberInstance.format(d);
    }
}
