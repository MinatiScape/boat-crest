package com.mappls.sdk.navigation;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import com.mappls.sdk.navigation.t;
import com.mappls.sdk.turf.TurfConstants;
import com.mappls.sdk.turf.TurfConversion;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    public static HashMap f12913a = new HashMap();
    public static NumberFormat b;

    public static SpannableString a(NavigationApplication navigationApplication, double d, boolean z, float f, ForegroundColorSpan foregroundColorSpan) {
        if (navigationApplication == null || navigationApplication.k() == null) {
            return new SpannableString("");
        }
        boolean isEmpty = f12913a.isEmpty();
        String str = TurfConstants.UNIT_FEET;
        String str2 = TurfConstants.UNIT_MILES;
        if (isEmpty) {
            f12913a.put("kilometers", navigationApplication.getString(R.string.mappls_kilometers));
            f12913a.put(TurfConstants.UNIT_METERS, navigationApplication.getString(R.string.mappls_meters));
            f12913a.put(TurfConstants.UNIT_MILES, navigationApplication.getString(R.string.mappls_miles));
            f12913a.put(TurfConstants.UNIT_FEET, navigationApplication.getString(R.string.mappls_feet));
        }
        b = NumberFormat.getNumberInstance(Locale.getDefault());
        boolean z2 = ((t.s) navigationApplication.k().y0.get()) == t.s.KILOMETERS_AND_METERS;
        if (z2) {
            str2 = "kilometers";
        }
        if (z2) {
            str = TurfConstants.UNIT_METERS;
        }
        double convertLength = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str);
        double convertLength2 = TurfConversion.convertLength(d, TurfConstants.UNIT_METERS, str2);
        if (convertLength2 > 10.0d) {
            b.setMaximumFractionDigits(d <= 10000.0d ? 1 : 0);
            return b(b.format(convertLength2), str2, z, f, foregroundColorSpan);
        } else if (convertLength >= 999.0d) {
            b.setMaximumFractionDigits(1);
            return b(b.format(convertLength2), str2, z, f, foregroundColorSpan);
        } else if (d > 500.0d) {
            int round = (((int) Math.round(convertLength)) / 50) * 50;
            return b(String.valueOf(round >= 50 ? round : 50), str, z, f, foregroundColorSpan);
        } else if (d > 100.0d) {
            int round2 = (((int) Math.round(convertLength)) / 25) * 25;
            return b(String.valueOf(round2 >= 25 ? round2 : 25), str, z, f, foregroundColorSpan);
        } else if (d > 20.0d) {
            int round3 = (((int) Math.round(convertLength)) / 10) * 10;
            return b(String.valueOf(round3 >= 10 ? round3 : 10), str, z, f, foregroundColorSpan);
        } else {
            int round4 = (((int) Math.round(convertLength)) / 5) * 5;
            return b(String.valueOf(round4 >= 5 ? round4 : 5), str, z, f, foregroundColorSpan);
        }
    }

    public static SpannableString b(String str, String str2, boolean z, float f, ForegroundColorSpan foregroundColorSpan) {
        String str3 = (z || foregroundColorSpan == null) ? "%s %s" : "%s%s";
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object obj = str2;
        if (!z) {
            obj = f12913a.get(str2);
        }
        objArr[1] = obj;
        SpannableString spannableString = new SpannableString(String.format(str3, objArr));
        spannableString.setSpan(new StyleSpan(1), 0, str.length(), 33);
        spannableString.setSpan(new RelativeSizeSpan(f), str.length(), spannableString.length(), 33);
        if (foregroundColorSpan != null) {
            spannableString.setSpan(foregroundColorSpan, str.length(), spannableString.length(), 33);
        }
        return spannableString;
    }
}
