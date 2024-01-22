package com.mappls.sdk.navigation;

import android.content.Context;
import android.text.SpannableString;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.blankj.utilcode.constant.CacheConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.navigation.t;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes11.dex */
public class NavigationFormatter {
    public static final float FEET_IN_ONE_METER = 3.2808f;
    public static final int FORMAT_DEGREES = 0;
    public static final int FORMAT_DEGREES_SHORT = 8;
    public static final int FORMAT_MINUTES = 1;
    public static final int FORMAT_SECONDS = 2;
    public static final float METERS_IN_KILOMETER = 1000.0f;
    public static final float METERS_IN_ONE_MILE = 1609.344f;
    public static final float METERS_IN_ONE_NAUTICALMILE = 1852.0f;
    public static final int MGRS_FORMAT = 5;
    public static final float MILS_IN_DEGREE = 17.777779f;
    public static final int OLC_FORMAT = 4;
    public static final int SWISS_GRID_FORMAT = 6;
    public static final int SWISS_GRID_PLUS_FORMAT = 7;
    public static final int UTM_FORMAT = 3;
    public static final float YARDS_IN_ONE_METER = 1.0936f;

    /* renamed from: a  reason: collision with root package name */
    public static final DecimalFormat f12867a;
    public static final DecimalFormat b;
    public static boolean c;
    public static b d;

    /* loaded from: classes11.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final String f12868a;
        public final String b;
        public final boolean c;

        public a(@NonNull String str, @NonNull String str2, boolean z) {
            this.f12868a = str;
            this.b = str2;
            this.c = z;
        }

        @NonNull
        public final String a(@NonNull Context context) {
            return this.c ? context.getString(R.string.mappls_ltr_or_rtl_combine_via_space, this.f12868a, this.b) : new MessageFormat("{0}{1}").format(new Object[]{this.f12868a, this.b});
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final SimpleDateFormat f12869a;
        public final SimpleDateFormat b;

        public b(@NonNull Locale locale, @NonNull String str, @NonNull String str2) {
            this.f12869a = new SimpleDateFormat(str, locale);
            this.b = new SimpleDateFormat(str2, locale);
        }

        public final String a(@NonNull Date date, boolean z) {
            return (z ? this.b : this.f12869a).format(date);
        }
    }

    static {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        f12867a = decimalFormat;
        DecimalFormat decimalFormat2 = new DecimalFormat(IdManager.DEFAULT_VERSION_NAME);
        b = decimalFormat2;
        setTwelveHoursFormatting(false, Locale.getDefault());
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat2.setMinimumFractionDigits(1);
        decimalFormat2.setMinimumIntegerDigits(1);
        decimalFormat.setMinimumIntegerDigits(1);
    }

    @NonNull
    public static a a(float f, @NonNull String str, @NonNull NavigationApplication navigationApplication) {
        return formatValue(f, str, true, 1, navigationApplication);
    }

    @NonNull
    public static a b(float f, @NonNull String str, @NonNull NavigationApplication navigationApplication) {
        return formatValue(f, str, false, 0, navigationApplication);
    }

    public static double calculateRoundedDist(double d2, NavigationApplication navigationApplication) {
        double d3;
        if (navigationApplication == null || navigationApplication.k() == null) {
            return 0.0d;
        }
        t.s sVar = (t.s) navigationApplication.k().y0.get();
        double d4 = 1609.343994140625d;
        if (sVar == t.s.MILES_AND_FEET) {
            d3 = 3.2808001041412354d;
        } else {
            if (sVar != t.s.MILES_AND_METERS) {
                if (sVar == t.s.NAUTICAL_MILES) {
                    d4 = 1852.0d;
                } else if (sVar == t.s.MILES_AND_YARDS) {
                    d3 = 1.0936000347137451d;
                } else {
                    d4 = 1000.0d;
                }
            }
            d3 = 1.0d;
        }
        double d5 = d3;
        int i = 1;
        byte b2 = 1;
        double d6 = 1.0d;
        while (true) {
            double d7 = i;
            if (d2 * d5 <= d7) {
                break;
            }
            d6 = d7 / d5;
            byte b3 = (byte) (b2 + 1);
            i = b2 % 3 == 2 ? (i * 5) / 2 : i * 2;
            if (d5 != d3 || d4 * d3 * 0.8999999761581421d > i) {
                b2 = b3;
            } else {
                d5 = 1.0d / d4;
                i = 1;
                b2 = 1;
            }
        }
        t.s sVar2 = t.s.MILES_AND_METERS;
        if (sVar != sVar2 || d6 != 1000.0d) {
            if (sVar != sVar2 || d6 != 500.0d) {
                t.s sVar3 = t.s.MILES_AND_FEET;
                if (sVar != sVar3 || d6 != 609.6073934755952d) {
                    if (sVar != sVar3 || d6 != 304.8036967377976d) {
                        t.s sVar4 = t.s.MILES_AND_YARDS;
                        if (sVar != sVar4 || d6 != 914.4110902133929d) {
                            if (sVar != sVar4 || d6 != 457.20554510669643d) {
                                return d6;
                            }
                        }
                    }
                }
            }
            return 402.33599853515625d;
        }
        return 804.6719970703125d;
    }

    @NonNull
    public static String formatInteger(int i, @NonNull String str, @NonNull NavigationApplication navigationApplication) {
        return formatIntegerValue(i, str, navigationApplication).a(navigationApplication);
    }

    @NonNull
    public static a formatIntegerValue(int i, @NonNull String str, @NonNull NavigationApplication navigationApplication) {
        return formatValue(i, str, false, 0, navigationApplication);
    }

    @NonNull
    public static a formatValue(float f, @StringRes int i, boolean z, int i2, @NonNull NavigationApplication navigationApplication) {
        return formatValue(f, navigationApplication.getString(i), z, i2, navigationApplication);
    }

    @NonNull
    public static a formatValue(float f, @NonNull String str, boolean z, int i, @NonNull NavigationApplication navigationApplication) {
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
        return new a(messageFormat.format(new Object[]{Float.valueOf(f)}).replace('\n', ' '), str, true);
    }

    public static String getFormattedAlt(double d2, NavigationApplication navigationApplication) {
        if (navigationApplication == null || navigationApplication.k() == null) {
            return "";
        }
        if (((t.s) navigationApplication.k().y0.get()) == t.s.KILOMETERS_AND_METERS) {
            StringBuilder sb = new StringBuilder();
            sb.append((int) (d2 + 0.5d));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, R.string.mappls_m, sb);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((int) ((d2 * 3.2808001041412354d) + 0.5d));
        sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
        return n.a(navigationApplication, R.string.mappls_foot, sb2);
    }

    @NonNull
    public static String getFormattedAlt(double d2, NavigationApplication navigationApplication, t.s sVar) {
        return getFormattedAltitudeValue(d2, navigationApplication, sVar).a(navigationApplication);
    }

    @NonNull
    public static a getFormattedAltitudeValue(double d2, @NonNull NavigationApplication navigationApplication, @NonNull t.s sVar) {
        float f;
        int i;
        if (sVar == t.s.MILES_AND_FEET || sVar == t.s.MILES_AND_YARDS) {
            f = (int) ((d2 * 3.2808001041412354d) + 0.5d);
            i = R.string.mappls_foot;
        } else {
            f = (int) (d2 + 0.5d);
            i = R.string.mappls_m;
        }
        return formatValue(f, i, false, 0, navigationApplication);
    }

    public static String getFormattedDate(Context context, long j) {
        return DateUtils.formatDateTime(context, j, 524304);
    }

    public static String getFormattedDistance(double d2, NavigationApplication navigationApplication, boolean z) {
        int i;
        float f;
        String str;
        String str2 = z ? "{0,number,0.0} " : "{0,number,0.#} ";
        String str3 = z ? "{0,number,0.00} " : "{0,number,0.##} ";
        if (navigationApplication == null || navigationApplication.k() == null) {
            return "";
        }
        t.s sVar = (t.s) navigationApplication.k().y0.get();
        t.s sVar2 = t.s.KILOMETERS_AND_METERS;
        if (sVar == sVar2) {
            i = R.string.mappls_km;
            f = 1000.0f;
        } else if (sVar == t.s.NAUTICAL_MILES) {
            i = R.string.mappls_nm;
            f = 1852.0f;
        } else {
            i = R.string.mappls_mile;
            f = 1609.344f;
        }
        if (d2 >= 100.0f * f) {
            StringBuilder sb = new StringBuilder();
            sb.append((int) ((d2 / f) + 0.5d));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, i, sb);
        } else if (d2 > 9.99f * f) {
            return MessageFormat.format(n.a(navigationApplication, i, h.a(str2)), Double.valueOf(d2 / f)).replace('\n', ' ');
        } else {
            if (d2 > 0.999f * f) {
                return MessageFormat.format(n.a(navigationApplication, i, h.a(str3)), Double.valueOf(d2 / f)).replace('\n', ' ');
            }
            t.s sVar3 = t.s.MILES_AND_FEET;
            if (sVar != sVar3 || d2 <= f * 0.249f) {
                t.s sVar4 = t.s.MILES_AND_METERS;
                if (sVar == sVar4) {
                    str = HexStringBuilder.DEFAULT_SEPARATOR;
                    if (d2 > f * 0.249f) {
                        return MessageFormat.format(n.a(navigationApplication, i, h.a(str3)), Double.valueOf(d2 / f)).replace('\n', ' ');
                    }
                } else {
                    str = HexStringBuilder.DEFAULT_SEPARATOR;
                }
                t.s sVar5 = t.s.MILES_AND_YARDS;
                if (sVar != sVar5 || d2 <= 0.249f * f) {
                    if (sVar != t.s.NAUTICAL_MILES || d2 <= 0.99f * f) {
                        if (sVar == sVar2 || sVar == sVar4) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append((int) (d2 + 0.5d));
                            sb2.append(str);
                            return n.a(navigationApplication, R.string.mappls_m, sb2);
                        } else if (sVar == sVar3) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append((int) ((3.2808001041412354d * d2) + 0.5d));
                            sb3.append(str);
                            return n.a(navigationApplication, R.string.mappls_foot, sb3);
                        } else {
                            String str4 = str;
                            if (sVar == sVar5) {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append((int) ((1.0936000347137451d * d2) + 0.5d));
                                sb4.append(str4);
                                return n.a(navigationApplication, R.string.mappls_yard, sb4);
                            }
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append((int) (d2 + 0.5d));
                            sb5.append(str4);
                            return n.a(navigationApplication, R.string.mappls_m, sb5);
                        }
                    }
                    return MessageFormat.format(n.a(navigationApplication, i, h.a(str3)), Double.valueOf(d2 / f)).replace('\n', ' ');
                }
                return MessageFormat.format(n.a(navigationApplication, i, h.a(str3)), Double.valueOf(d2 / f)).replace('\n', ' ');
            }
            return MessageFormat.format(n.a(navigationApplication, i, h.a(str3)), Double.valueOf(d2 / f)).replace('\n', ' ');
        }
    }

    public static String getFormattedDistance(float f, NavigationApplication navigationApplication) {
        return getFormattedDistance(f, navigationApplication, false);
    }

    @NonNull
    public static String getFormattedDistance(float f, NavigationApplication navigationApplication, boolean z) {
        return navigationApplication == null ? "" : getFormattedDistance(f, navigationApplication, z, (t.s) navigationApplication.k().y0.get());
    }

    @NonNull
    public static String getFormattedDistance(float f, @NonNull NavigationApplication navigationApplication, boolean z, @NonNull t.s sVar) {
        return getFormattedDistanceValue(f, navigationApplication, z, sVar).a(navigationApplication);
    }

    public static String getFormattedDistanceInterval(NavigationApplication navigationApplication, double d2, boolean z) {
        return getFormattedDistance((float) calculateRoundedDist(d2, navigationApplication), navigationApplication, z);
    }

    public static String getFormattedDistanceNavigation(double d2, NavigationApplication navigationApplication) {
        return j.a(navigationApplication, d2, true, 0.65f, null).toString();
    }

    public static SpannableString getFormattedDistanceRound(float f, NavigationApplication navigationApplication, float f2) {
        return j.a(navigationApplication, f, false, f2, null);
    }

    public static SpannableString getFormattedDistanceRound(float f, NavigationApplication navigationApplication, float f2, ForegroundColorSpan foregroundColorSpan) {
        return j.a(navigationApplication, f, false, f2, foregroundColorSpan);
    }

    public static String getFormattedDistanceRound(float f, NavigationApplication navigationApplication) {
        return j.a(navigationApplication, f, false, 0.65f, null).toString();
    }

    @NonNull
    public static a getFormattedDistanceValue(float f, @NonNull NavigationApplication navigationApplication, boolean z, @NonNull t.s sVar) {
        int i;
        float f2;
        t.s sVar2 = t.s.KILOMETERS_AND_METERS;
        if (sVar == sVar2) {
            i = R.string.mappls_km;
            f2 = 1000.0f;
        } else if (sVar == t.s.NAUTICAL_MILES) {
            i = R.string.mappls_nm;
            f2 = 1852.0f;
        } else {
            i = R.string.mappls_mile;
            f2 = 1609.344f;
        }
        float f3 = f / f2;
        if (f >= 100.0f * f2) {
            return formatValue((int) (f3 + 0.5d), i, z, 0, navigationApplication);
        }
        if (f > 9.99f * f2) {
            return formatValue(f3, i, z, 1, navigationApplication);
        }
        if (f > 0.999f * f2) {
            return formatValue(f3, i, z, 2, navigationApplication);
        }
        t.s sVar3 = t.s.MILES_AND_FEET;
        if (sVar != sVar3 || f <= f2 * 0.249f) {
            t.s sVar4 = t.s.MILES_AND_METERS;
            if (sVar != sVar4 || f <= f2 * 0.249f) {
                t.s sVar5 = t.s.MILES_AND_YARDS;
                return (sVar != sVar5 || f <= 0.249f * f2) ? (sVar != t.s.NAUTICAL_MILES || f <= f2 * 0.99f) ? (sVar == sVar2 || sVar == sVar4) ? formatValue((int) (f + 0.5d), R.string.mappls_m, z, 0, navigationApplication) : sVar == sVar3 ? formatValue((int) ((f * 3.2808f) + 0.5d), R.string.mappls_foot, z, 0, navigationApplication) : sVar == sVar5 ? formatValue((int) ((f * 1.0936f) + 0.5d), R.string.mappls_foot, z, 0, navigationApplication) : formatValue((int) (f + 0.5d), R.string.mappls_m, z, 0, navigationApplication) : formatValue(f3, i, z, 2, navigationApplication) : formatValue(f3, i, z, 2, navigationApplication);
            }
            return formatValue(f3, i, z, 2, navigationApplication);
        }
        return formatValue(f3, i, z, 2, navigationApplication);
    }

    public static String getFormattedDuration(int i, NavigationApplication navigationApplication) {
        int i2 = i % 60;
        long j = (i % CacheConstants.HOUR) / 60;
        long j2 = (i % CacheConstants.DAY) / CacheConstants.HOUR;
        long j3 = i / CacheConstants.DAY;
        String str = "";
        if (j3 > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(j3);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(navigationApplication.getString(j3 > 1 ? R.string.mappls_navigation_days : R.string.mappls_navigation_day));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(j2);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(navigationApplication.getString(R.string.mappls_navigation_hour));
            if (j > 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb2.append(j);
                sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
                str = n.a(navigationApplication, R.string.mappls_navigation_minute, sb2);
            }
            sb.append(str);
            return sb.toString();
        } else if (j2 > 0) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(j2);
            sb3.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb3.append(navigationApplication.getString(R.string.mappls_navigation_hour));
            if (j > 0) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb4.append(j);
                sb4.append(HexStringBuilder.DEFAULT_SEPARATOR);
                str = n.a(navigationApplication, R.string.mappls_navigation_minute, sb4);
            }
            sb3.append(str);
            return sb3.toString();
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(j >= 1 ? Long.valueOf(j) : "<1");
            sb5.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, R.string.mappls_navigation_minute, sb5);
        }
    }

    public static String getFormattedDurationShort(int i) {
        Object valueOf;
        Object valueOf2;
        int i2 = i / CacheConstants.HOUR;
        int i3 = (i / 60) % 60;
        int i4 = i % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(i2);
        sb.append(":");
        if (i3 < 10) {
            valueOf = BleConst.GetDeviceTime + i3;
        } else {
            valueOf = Integer.valueOf(i3);
        }
        sb.append(valueOf);
        sb.append(":");
        if (i4 < 10) {
            valueOf2 = BleConst.GetDeviceTime + i4;
        } else {
            valueOf2 = Integer.valueOf(i4);
        }
        sb.append(valueOf2);
        return sb.toString();
    }

    public static String getFormattedDurationShortMinutes(int i) {
        Object valueOf;
        int i2 = i / CacheConstants.HOUR;
        int i3 = (i / 60) % 60;
        StringBuilder sb = new StringBuilder();
        sb.append(i2);
        sb.append(":");
        if (i3 < 10) {
            valueOf = BleConst.GetDeviceTime + i3;
        } else {
            valueOf = Integer.valueOf(i3);
        }
        sb.append(valueOf);
        return sb.toString();
    }

    public static String getFormattedFullTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return d.a(calendar.getTime(), c);
    }

    public static String getFormattedRoundDistanceKm(float f, int i, NavigationApplication navigationApplication) {
        int i2 = R.string.mappls_km;
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append((int) ((f / 1000.0f) + 0.5d));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, i2, sb);
        } else if (i == 1) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(b.format(f / 1000.0f));
            sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, i2, sb2);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(f12867a.format(f / 1000.0f));
            sb3.append(HexStringBuilder.DEFAULT_SEPARATOR);
            return n.a(navigationApplication, i2, sb3);
        }
    }

    public static String getFormattedSpeed(float f, NavigationApplication navigationApplication) {
        StringBuilder sb;
        int round;
        int round2;
        if (navigationApplication == null || navigationApplication.k() == null) {
            return "";
        }
        t k = navigationApplication.k();
        t.v vVar = (t.v) k.r0.get();
        d e = k.e();
        float f2 = 3.6f * f;
        String str = "-";
        if (vVar == t.v.KILOMETERS_PER_HOUR) {
            if (f2 >= 20.0f || e.h()) {
                sb = new StringBuilder();
                round2 = Math.round(f2);
                sb.append(round2);
                str = HexStringBuilder.DEFAULT_SEPARATOR;
                sb.append(str);
            } else {
                round = Math.round(f2 * 10.0f);
                sb = new StringBuilder();
                sb.append(round / 10.0f);
                str = HexStringBuilder.DEFAULT_SEPARATOR;
                sb.append(str);
            }
        } else if (vVar == t.v.MILES_PER_HOUR) {
            f2 = (f2 * 1000.0f) / 1609.344f;
            if (f2 >= 20.0f || e.h()) {
                sb = new StringBuilder();
                round2 = Math.round(f2);
                sb.append(round2);
                str = HexStringBuilder.DEFAULT_SEPARATOR;
                sb.append(str);
            } else {
                round = Math.round(f2 * 10.0f);
                sb = new StringBuilder();
                sb.append(round / 10.0f);
                str = HexStringBuilder.DEFAULT_SEPARATOR;
                sb.append(str);
            }
        } else {
            if (vVar == t.v.NAUTICALMILES_PER_HOUR) {
                f2 = (f2 * 1000.0f) / 1852.0f;
                if (f2 >= 20.0f || e.h()) {
                    sb = new StringBuilder();
                    round2 = Math.round(f2);
                    sb.append(round2);
                    str = HexStringBuilder.DEFAULT_SEPARATOR;
                    sb.append(str);
                } else {
                    round = Math.round(f2 * 10.0f);
                    sb = new StringBuilder();
                }
            } else {
                if (vVar == t.v.MINUTES_PER_KILOMETER) {
                    if (f < 0.111111111d) {
                        sb = new StringBuilder();
                    } else {
                        float f3 = 1000.0f / (f * 60.0f);
                        if (f3 >= 10.0f) {
                            sb = new StringBuilder();
                            round2 = Math.round(f3);
                            sb.append(round2);
                            str = HexStringBuilder.DEFAULT_SEPARATOR;
                        } else {
                            round = Math.round(f3 * 10.0f);
                            sb = new StringBuilder();
                        }
                    }
                } else if (vVar != t.v.MINUTES_PER_MILE) {
                    if (f >= 10.0f) {
                        sb = new StringBuilder();
                        sb.append(Math.round(f));
                    } else {
                        int round3 = Math.round(f * 10.0f);
                        sb = new StringBuilder();
                        sb.append(round3 / 10.0f);
                    }
                    sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    vVar = t.v.METERS_PER_SECOND;
                } else if (f < 0.111111111d) {
                    sb = new StringBuilder();
                } else {
                    float f4 = 1609.344f / (f * 60.0f);
                    if (f4 >= 10.0f) {
                        sb = new StringBuilder();
                        round2 = Math.round(f4);
                        sb.append(round2);
                        str = HexStringBuilder.DEFAULT_SEPARATOR;
                    } else {
                        round = Math.round(f4 * 10.0f);
                        sb = new StringBuilder();
                    }
                }
                sb.append(str);
            }
            sb.append(round / 10.0f);
            str = HexStringBuilder.DEFAULT_SEPARATOR;
            sb.append(str);
        }
        sb.append(vVar.a(navigationApplication));
        return sb.toString();
    }

    @NonNull
    public static a getFormattedSpeedValue(float f, @NonNull NavigationApplication navigationApplication) {
        t k = navigationApplication.k();
        t.v vVar = (t.v) k.r0.get();
        String a2 = vVar.a(navigationApplication);
        d e = k.e();
        float f2 = 3.6f * f;
        if (vVar == t.v.KILOMETERS_PER_HOUR) {
            return (f2 >= 20.0f || e.h()) ? b(Math.round(f2), a2, navigationApplication) : a(Math.round(f2 * 10.0f) / 10.0f, a2, navigationApplication);
        } else if (vVar == t.v.MILES_PER_HOUR) {
            float f3 = (f2 * 1000.0f) / 1609.344f;
            return (f3 >= 20.0f || e.h()) ? b(Math.round(f3), a2, navigationApplication) : a(Math.round(f3 * 10.0f) / 10.0f, a2, navigationApplication);
        } else if (vVar == t.v.NAUTICALMILES_PER_HOUR) {
            float f4 = (f2 * 1000.0f) / 1852.0f;
            return (f4 >= 20.0f || e.h()) ? b(Math.round(f4), a2, navigationApplication) : a(Math.round(f4 * 10.0f) / 10.0f, a2, navigationApplication);
        } else if (vVar == t.v.MINUTES_PER_KILOMETER) {
            if (f < 0.111111111d) {
                return new a("-", a2, false);
            }
            float f5 = 1000.0f / (f * 60.0f);
            return f5 >= 10.0f ? b(Math.round(f5), a2, navigationApplication) : new a(com.mappls.sdk.navigation.util.a.a(Math.round(f5 * 60.0f), false), a2, true);
        } else if (vVar != t.v.MINUTES_PER_MILE) {
            String a3 = t.v.METERS_PER_SECOND.a(navigationApplication);
            return f >= 10.0f ? b(Math.round(f), a3, navigationApplication) : a(Math.round(f * 10.0f) / 10.0f, a3, navigationApplication);
        } else if (f < 0.111111111d) {
            return new a("-", a2, false);
        } else {
            float f6 = 1609.344f / (f * 60.0f);
            return f6 >= 10.0f ? b(Math.round(f6), a2, navigationApplication) : a(Math.round(f6 * 10.0f) / 10.0f, a2, navigationApplication);
        }
    }

    public static boolean isSameDay(@NonNull Calendar calendar, @NonNull Calendar calendar2) {
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean isSameDay(@NonNull Date date, @NonNull Date date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static void setTwelveHoursFormatting(boolean z, @NonNull Locale locale) {
        c = z;
        d = new b(locale, "H:mm:ss", "h:mm:ss a");
        new b(locale, "HH:mm", "h:mm a");
    }
}
