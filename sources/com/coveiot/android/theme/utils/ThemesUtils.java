package com.coveiot.android.theme.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.constant.CacheConstants;
import com.blankj.utilcode.constant.TimeConstants;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.CommonMessageDialog;
import com.coveiot.android.theme.R;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import com.github.mikephil.charting.data.BarEntry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.net.HttpHeaders;
import java.nio.charset.Charset;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.c;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ThemesUtils {
    @NotNull
    public static final ThemesUtils INSTANCE = new ThemesUtils();

    /* loaded from: classes7.dex */
    public static final class EmojiInputDisable implements InputFilter {
        @NotNull
        public final Pattern h;

        public EmojiInputDisable() {
            Pattern compile = Pattern.compile("[\\p{So}]");
            Intrinsics.checkNotNullExpressionValue(compile, "compile(\"[\\\\p{So}]\")");
            this.h = compile;
        }

        public final boolean a(CharSequence charSequence) {
            return this.h.matcher(charSequence).find();
        }

        @Override // android.text.InputFilter
        @NotNull
        public CharSequence filter(@NotNull CharSequence source, int i, int i2, @NotNull Spanned dest, int i3, int i4) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(dest, "dest");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(dest);
            spannableStringBuilder.replace(i3, i4, source, i, i2);
            return a(spannableStringBuilder) ? "" : source;
        }
    }

    public static final void d(CommonMessageDialog commonMessageDialog) {
        Intrinsics.checkNotNullParameter(commonMessageDialog, "$commonMessageDialog");
        commonMessageDialog.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final String getDateFromTimeStamp(long j, @NotNull Context context, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return j != 0 ? String.valueOf(AppUtils.getSimpleDateFormat(pattern).format(new Date(j))) : "";
    }

    @JvmStatic
    public static final double getTemperatureInCelsius(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(((d - 32) * 5) / 9);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format((temperature - 32) * 5 / 9)");
        return Double.parseDouble(format);
    }

    @JvmStatic
    public static final double getTemperatureInFahrenheit(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format((d * 1.8d) + 32);
        Intrinsics.checkNotNullExpressionValue(format, "df2.format(temperature * 1.8 + 32)");
        return Double.parseDouble(format);
    }

    @JvmStatic
    public static final void setPoweredByLogoIcon(@NotNull Context context, @NotNull ImageView imageView, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (SessionManager.getInstance(context).getDeviceModelBean() == null || SessionManager.getInstance(context).getDeviceModelBean().getLogoType() == null || !m.equals(SessionManager.getInstance(context).getDeviceModelBean().getLogoType(), "CrestOS", true)) {
            imageView.setVisibility(8);
        } else if (z) {
            imageView.setImageDrawable(context.getDrawable(R.drawable.powered_by_crest_share));
        } else {
            imageView.setImageDrawable(context.getDrawable(R.drawable.powered_by_crest_logo));
        }
    }

    @NotNull
    public final String addSecondsToCurrentTimeAndGetTimeIn12HoursFormat(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, (int) j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(Locale.getDefault());
        dateFormatSymbols.setAmPmStrings(new String[]{"AM", "PM"});
        simpleDateFormat.setDateFormatSymbols(dateFormatSymbols);
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(currentTime.time)");
        return format;
    }

    @NotNull
    public final String addSecondsToCurrentTimeAndGetTimeIn24HoursFormat(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, (int) j);
        String format = new SimpleDateFormat("HH:mm").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "sdf.format(currentTime.time)");
        return format;
    }

    public final SpannableStringBuilder b(Spanned spanned, final TextView textView, int i, String str, final boolean z) {
        String obj = spanned.toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned);
        if (StringsKt__StringsKt.contains$default((CharSequence) obj, (CharSequence) str, false, 2, (Object) null)) {
            spannableStringBuilder.setSpan(new com.coveiot.android.theme.compundview.MySpannable() { // from class: com.coveiot.android.theme.utils.ThemesUtils$addClickablePartTextViewResizable$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(false);
                }

                @Override // com.coveiot.android.theme.compundview.MySpannable, android.text.style.ClickableSpan
                public void onClick(@NotNull View widget) {
                    Intrinsics.checkNotNullParameter(widget, "widget");
                    TextView textView2 = textView;
                    textView2.setLayoutParams(textView2.getLayoutParams());
                    TextView textView3 = textView;
                    textView3.setText(textView3.getTag().toString(), TextView.BufferType.SPANNABLE);
                    textView.invalidate();
                    if (z) {
                        ThemesUtils.INSTANCE.makeTextViewResizable(textView, -1, "Read Less", false);
                    } else {
                        ThemesUtils.INSTANCE.makeTextViewResizable(textView, 3, "... Read More", true);
                    }
                }
            }, StringsKt__StringsKt.indexOf$default((CharSequence) obj, str, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) obj, str, 0, false, 6, (Object) null) + str.length(), 0);
        }
        return spannableStringBuilder;
    }

    public final String c(String str) {
        int parseInt = Integer.parseInt(str);
        boolean z = false;
        if (11 <= parseInt && parseInt < 14) {
            z = true;
        }
        if (z) {
            return "th";
        }
        int i = parseInt % 10;
        return i == 1 ? "st" : i == 2 ? "nd" : i == 3 ? "rd" : "th";
    }

    public final double convertCmToMeters(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(i / 100.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return Double.parseDouble(format);
    }

    @RequiresApi(26)
    @NotNull
    public final String decode(@NotNull String inputText) {
        Intrinsics.checkNotNullParameter(inputText, "inputText");
        try {
            Base64.Decoder urlDecoder = Base64.getUrlDecoder();
            Charset charset = Charsets.UTF_8;
            byte[] bytes = inputText.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] decode = urlDecoder.decode(bytes);
            Intrinsics.checkNotNullExpressionValue(decode, "getUrlDecoder().decode(inputText.toByteArray())");
            return new String(decode, charset);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "";
        }
    }

    public final void disableCopyPasteOperations(@NotNull EditText editText) {
        Intrinsics.checkNotNullParameter(editText, "editText");
        editText.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.coveiot.android.theme.utils.ThemesUtils$disableCopyPasteOperations$1
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(@Nullable ActionMode actionMode, @Nullable MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(@Nullable ActionMode actionMode, @Nullable Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(@Nullable ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(@Nullable ActionMode actionMode, @Nullable Menu menu) {
                return false;
            }
        });
        editText.setLongClickable(false);
        editText.setTextIsSelectable(false);
    }

    @RequiresApi(26)
    @NotNull
    public final String encode(@NotNull String inputText) {
        Intrinsics.checkNotNullParameter(inputText, "inputText");
        try {
            Base64.Encoder urlEncoder = Base64.getUrlEncoder();
            Charset charset = Charsets.UTF_8;
            byte[] bytes = inputText.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] encode = urlEncoder.encode(bytes);
            Intrinsics.checkNotNullExpressionValue(encode, "getUrlEncoder().encode(inputText.toByteArray())");
            return new String(encode, charset);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "";
        }
    }

    @NotNull
    public final String formatDateAndMonthRange(@NotNull String startDate, @NotNull String endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date parse = simpleDateFormat.parse(startDate);
        Date parse2 = simpleDateFormat.parse(endDate);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM", locale);
        Date parse3 = simpleDateFormat.parse(startDate);
        Date parse4 = simpleDateFormat.parse(endDate);
        String format = parse3 != null ? simpleDateFormat2.format(parse3) : null;
        String format2 = parse4 != null ? simpleDateFormat2.format(parse4) : null;
        String format3 = parse != null ? new SimpleDateFormat("d", locale).format(parse) : null;
        String format4 = parse2 != null ? new SimpleDateFormat("d", locale).format(parse2) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        sb.append(format3);
        sb.append(format3 != null ? INSTANCE.c(format3) : null);
        sb.append(' ');
        sb.append(format);
        sb.append(" to ");
        sb.append(format4);
        sb.append(format4 != null ? INSTANCE.c(format4) : null);
        sb.append(' ');
        sb.append(format2);
        return sb.toString();
    }

    @NotNull
    public final String formatDateWithSuffix(@NotNull String startDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Locale locale = Locale.ENGLISH;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        Date parse = simpleDateFormat.parse(startDate);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM, yyyy", locale);
        Date parse2 = simpleDateFormat.parse(startDate);
        String format = parse2 != null ? simpleDateFormat2.format(parse2) : null;
        String format2 = parse != null ? new SimpleDateFormat("d", locale).format(parse) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(format2);
        sb.append(format2 != null ? INSTANCE.c(format2) : null);
        sb.append(' ');
        sb.append(format);
        sb.append(' ');
        return sb.toString();
    }

    @NotNull
    public final String formatDistance(double d) {
        if (d < 1000.0d) {
            StringBuilder sb = new StringBuilder();
            sb.append(c.roundToInt(d));
            sb.append('m');
            return sb.toString();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f km", Arrays.copyOf(new Object[]{Double.valueOf(d / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public final String formatSeconds(long j) {
        long j2 = 60;
        long j3 = j / j2;
        long j4 = j3 / j2;
        long j5 = 24;
        long j6 = j4 / j5;
        if (j6 > 0) {
            long j7 = j4 % j5;
            StringBuilder sb = new StringBuilder();
            sb.append(j6);
            sb.append(" day");
            sb.append(j6 > 1 ? "s" : "");
            sb.append(' ');
            sb.append(j7);
            sb.append(" hr");
            sb.append(j7 <= 1 ? "" : "s");
            return sb.toString();
        } else if (j4 > 0) {
            long j8 = j3 % j2;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(j4);
            sb2.append(" hr");
            sb2.append(j4 > 1 ? "s" : "");
            sb2.append(' ');
            sb2.append(j8);
            sb2.append(" min");
            sb2.append(j8 <= 1 ? "" : "s");
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(j3);
            sb3.append(" min");
            sb3.append(j3 <= 1 ? "" : "s");
            return sb3.toString();
        }
    }

    @Nullable
    public final String formattedDate(@Nullable String str, @Nullable String str2) {
        try {
            return AppUtils.getSimpleDateFormat(str2).format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }

    @Nullable
    public final String formattedDateBasedOnPattern(@Nullable String str, @NotNull String datePattern, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(datePattern, "datePattern");
        try {
            return AppUtils.getSimpleDateFormat(str2).format(AppUtils.getSimpleDateFormat(datePattern).parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }

    @NotNull
    public final String getCurrentDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    public final int getDateDifference(@NotNull Calendar fromCal, @NotNull Calendar toCal) {
        Intrinsics.checkNotNullParameter(fromCal, "fromCal");
        Intrinsics.checkNotNullParameter(toCal, "toCal");
        long timeInMillis = toCal.getTimeInMillis() - fromCal.getTimeInMillis();
        long j = 60;
        long j2 = (timeInMillis / 1000) % j;
        long j3 = (timeInMillis / 60000) % j;
        long j4 = (timeInMillis / ((long) TimeConstants.HOUR)) % 24;
        return (int) (timeInMillis / ((long) TimeConstants.DAY));
    }

    @NotNull
    public final String getDateFormat(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMM, yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(simpleDateFormat.parse(date));
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(calendar.time)");
        return format;
    }

    @NotNull
    public final String getDateFormatDDMMMYYYY(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        return String.valueOf(AppUtils.getSimpleDateFormat("dd MMM, yyyy").format(new Date(calendar.getTimeInMillis())));
    }

    @Nullable
    public final Date getDateFromString(@NotNull String date, @NotNull String formatType) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(formatType, "formatType");
        try {
            Date parse = new SimpleDateFormat(formatType).parse(date);
            Log.i(HttpHeaders.DATE, String.valueOf(parse.getTime()));
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003b A[LOOP:0: B:12:0x0035->B:14:0x003b, LOOP_END] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<java.util.Date> getDates(@org.jetbrains.annotations.NotNull java.lang.String r4, @org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r0 = "dateString1"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "dateString2"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r2 = "yyyy-MM-dd"
            r1.<init>(r2)
            r2 = 0
            java.util.Date r4 = r1.parse(r4)     // Catch: java.text.ParseException -> L22
            java.util.Date r2 = r1.parse(r5)     // Catch: java.text.ParseException -> L20
            goto L27
        L20:
            r5 = move-exception
            goto L24
        L22:
            r5 = move-exception
            r4 = r2
        L24:
            r5.printStackTrace()
        L27:
            java.util.Calendar r5 = java.util.Calendar.getInstance()
            r5.setTime(r4)
            java.util.Calendar r4 = java.util.Calendar.getInstance()
            r4.setTime(r2)
        L35:
            boolean r1 = r5.after(r4)
            if (r1 != 0) goto L48
            java.util.Date r1 = r5.getTime()
            r0.add(r1)
            r1 = 5
            r2 = 1
            r5.add(r1, r2)
            goto L35
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.theme.utils.ThemesUtils.getDates(java.lang.String, java.lang.String):java.util.List");
    }

    public final long getDayDifference(@NotNull Date startDate, @NotNull Date endDate) {
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        return Math.abs(-Math.round((endDate.getTime() - startDate.getTime()) / ((long) TimeConstants.DAY)));
    }

    public final double getDistanceByUnitSelected(@NotNull Context context, int i) {
        float f;
        float f2;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            f = i;
            f2 = 1609.34f;
        } else {
            f = i;
            f2 = 1000.0f;
        }
        return Double.parseDouble(getStringFormattedValueTillNDecimal(Double.valueOf(f / f2), 2));
    }

    @NotNull
    public final String getDistanceWithUnit(@NotNull Context context, int i, int i2) {
        double d;
        String string;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            d = i / 1609.34f;
            string = context.getString(R.string.mi_small);
            Intrinsics.checkNotNullExpressionValue(string, "this.getString(R.string.mi_small)");
        } else {
            d = i / 1000.0f;
            string = context.getString(R.string.km_small);
            Intrinsics.checkNotNullExpressionValue(string, "this.getString(R.string.km_small)");
        }
        return getStringFormattedValueTillNDecimalWithUnit(d, i2, string);
    }

    @NotNull
    public final String getDistanceWithoutUnit(@NotNull Context context, int i, int i2) {
        float f;
        float f2;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            f = i;
            f2 = 1609.34f;
        } else {
            f = i;
            f2 = 1000.0f;
        }
        return getFormattedDistance(Double.parseDouble(getStringFormattedValueTillNDecimalWithUnit(f / f2, i2, "")));
    }

    public final double getDivisionValueAsPerUnit(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(\n           …   ).isDistanceUnitInMile");
        return isDistanceUnitInMile.booleanValue() ? 1609.34d : 1000.0d;
    }

    @NotNull
    public final String getFormattedDistance(double d) {
        String format = new DecimalFormat("0.#").format(d);
        Intrinsics.checkNotNullExpressionValue(format, "format.format(this)");
        return format;
    }

    @NotNull
    public final String getLastMondayDate() {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        while (calendar.get(7) != 2) {
            calendar.add(7, -1);
        }
        String format = simpleDateFormat.format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(calendar.time)");
        return format;
    }

    public final int getMaximumYValue(@NotNull List<? extends BarEntry> barEntries) {
        Intrinsics.checkNotNullParameter(barEntries, "barEntries");
        int i = 0;
        if (!barEntries.isEmpty()) {
            for (BarEntry barEntry : barEntries) {
                if (i < ((int) barEntry.getY())) {
                    i = (int) barEntry.getY();
                }
            }
        }
        return i;
    }

    public final long getMinutesTimeDifference(@NotNull Calendar date1, @NotNull Calendar date2) {
        Intrinsics.checkNotNullParameter(date1, "date1");
        Intrinsics.checkNotNullParameter(date2, "date2");
        long timeInMillis = (date1.getTimeInMillis() - date2.getTimeInMillis()) / 1000;
        long j = 60;
        long j2 = timeInMillis / j;
        long j3 = (j2 / j) / 24;
        return j2;
    }

    @Nullable
    public final String getMonthName(int i) {
        return new DateFormatSymbols().getMonths()[i].toString();
    }

    public final double getPercentageOfTwoNumbers(int i, int i2) {
        double d = (i + i2) / 2;
        double d2 = i - i2;
        if (d == 0.0d) {
            return 0.0d;
        }
        return (d2 / d) * 100;
    }

    @NotNull
    public final Calendar getPreviousDate(@NotNull Calendar date, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        Object clone = date.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        calendar.add(5, i);
        return calendar;
    }

    @NotNull
    public final String getRankSuffix(int i) {
        if (i == 1) {
            return i + "st";
        } else if (i == 2) {
            return i + "nd";
        } else if (i != 3) {
            return i + "th";
        } else {
            return i + "rd";
        }
    }

    @NotNull
    public final String getStringFormattedValueTillNDecimal(@NotNull Object value, int i) {
        Intrinsics.checkNotNullParameter(value, "value");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, new String[]{"%.0f", "%.1f", "%.2f", "%.3f"}[i], Arrays.copyOf(new Object[]{value}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    @NotNull
    public final String getStringFormattedValueTillNDecimalWithUnit(double d, int i, @NotNull String unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        getFormattedDistance(d);
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, new String[]{"%.0f", "%.1f", "%.2f", "%.3f"}[i], Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        if (unit.length() == 0) {
            unit = "";
        }
        sb.append(unit);
        return sb.toString();
    }

    @Nullable
    public final Long getTimeStampFromDate(@NotNull String dateValue) {
        Intrinsics.checkNotNullParameter(dateValue, "dateValue");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT);
        Date parse = simpleDateFormat.parse(dateValue);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(parse));
        Intrinsics.checkNotNull(parse2, "null cannot be cast to non-null type java.util.Date");
        return Long.valueOf(parse2.getTime());
    }

    @NotNull
    public final String getYestWeekMonthText(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = new String[]{context.getString(R.string.yesterday_small), context.getString(R.string.last_week), context.getString(R.string.last_month)}[i];
        Intrinsics.checkNotNullExpressionValue(str, "arrayInsightType[type]");
        return str;
    }

    public final boolean isDevicePaired(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return SessionManager.getInstance(context).isDevicePaired();
    }

    public final boolean isGuestUser(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return SessionManager.getInstance(context).isGuestUser();
    }

    public final boolean isLoggedIn(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return SessionManager.getInstance(context).isLoggedIn();
    }

    public final boolean isPairDeviceLater(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return SessionManager.getInstance(context).isPairDeviceLater();
    }

    public final void makeTextViewBold(@NotNull Context context, @NotNull String text, @NotNull String mutableString, int i, int i2, @NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(mutableString, "mutableString");
        Intrinsics.checkNotNullParameter(textView, "textView");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, text, Arrays.copyOf(new Object[]{mutableString}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.bold), i, i2, 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        textView.setText(spannableStringBuilder);
    }

    public final void makeTextViewBoldForFitnessChallengesLeaderboard(@NotNull Context context, @NotNull String text, @NotNull String mutableString1, @NotNull String mutableString2, @NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(mutableString1, "mutableString1");
        Intrinsics.checkNotNullParameter(mutableString2, "mutableString2");
        Intrinsics.checkNotNullParameter(textView, "textView");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, text, Arrays.copyOf(new Object[]{mutableString1, mutableString2}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.bold), 0, mutableString1.length(), 18);
        spannableStringBuilder.append((CharSequence) spannableString);
        textView.setText(spannableStringBuilder);
    }

    public final void makeTextViewResizable(@NotNull final TextView tv, final int i, @NotNull final String expandText, final boolean z) {
        Intrinsics.checkNotNullParameter(tv, "tv");
        Intrinsics.checkNotNullParameter(expandText, "expandText");
        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.coveiot.android.theme.utils.ThemesUtils$makeTextViewResizable$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int lineEnd;
                String str;
                SpannableStringBuilder b;
                tv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int i2 = i;
                if (i2 == 0) {
                    lineEnd = tv.getLayout().getLineEnd(0);
                    str = ((Object) tv.getText().subSequence(0, (lineEnd - expandText.length()) + 1)) + ' ' + expandText;
                } else if (i2 > 0 && tv.getLineCount() >= i) {
                    lineEnd = tv.getLayout().getLineEnd(i - 1);
                    str = ((Object) tv.getText().subSequence(0, (lineEnd - expandText.length()) + 1)) + ' ' + expandText;
                } else {
                    lineEnd = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    str = ((Object) tv.getText().subSequence(0, lineEnd)) + ' ' + expandText;
                }
                int i3 = lineEnd;
                tv.setText(str);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                TextView textView = tv;
                b = ThemesUtils.INSTANCE.b(new SpannableString(tv.getText().toString()), tv, i3, expandText, z);
                textView.setText(b, TextView.BufferType.SPANNABLE);
            }
        });
    }

    @NotNull
    public final String removeEmojisAndDifferentLanguage(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new Regex("[^ A-Za-z0-9_@./#&+-=|\\\\;:‘“<>,(){}?*%$₹!]").replace(message, HexStringBuilder.DEFAULT_SEPARATOR);
    }

    public final void showCommonMessageDialog(@NotNull FragmentActivity context, @NotNull String message) {
        Window window;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        final CommonMessageDialog commonMessageDialog = new CommonMessageDialog(context, message, false, true);
        commonMessageDialog.show(context.getSupportFragmentManager(), "");
        Dialog dialog = commonMessageDialog.getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            window.setGravity(80);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.theme.utils.a
            @Override // java.lang.Runnable
            public final void run() {
                ThemesUtils.d(CommonMessageDialog.this);
            }
        }, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    @NotNull
    public final SpannableStringBuilder spannableStringBuilder(@NotNull Context context, @NotNull String string, @NotNull String highlightValue) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(highlightValue, "highlightValue");
        SpannableString spannableString = new SpannableString(string);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));
        StringsKt__StringsKt.indexOf$default((CharSequence) string, highlightValue, 0, false, 6, (Object) null);
        spannableString.setSpan(foregroundColorSpan, 0, StringsKt__StringsKt.indexOf$default((CharSequence) string, highlightValue, 0, false, 6, (Object) null) + highlightValue.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.bold), StringsKt__StringsKt.indexOf$default((CharSequence) string, highlightValue, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) string, highlightValue, 0, false, 6, (Object) null) + highlightValue.length(), 18);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) spannableString);
        return spannableStringBuilder;
    }

    @NotNull
    public final SpannableString styled(@NotNull String fullMessage, @NotNull String styledMessage, final int i, final boolean z, final boolean z2, @NotNull final Function0<Unit> onClick, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(fullMessage, "fullMessage");
        Intrinsics.checkNotNullParameter(styledMessage, "styledMessage");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Intrinsics.checkNotNullParameter(context, "context");
        SpannableString spannableString = new SpannableString(fullMessage);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.theme.utils.ThemesUtils$styled$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                onClick.invoke();
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NotNull TextPaint ds) {
                Intrinsics.checkNotNullParameter(ds, "ds");
                super.updateDrawState(ds);
                ds.setColor(i);
                ds.setUnderlineText(z);
                ds.setFakeBoldText(z2);
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) fullMessage, styledMessage, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) fullMessage, styledMessage, 0, false, 6, (Object) null) + styledMessage.length(), 33);
        return spannableString;
    }

    @Nullable
    public final String timeConversionToHHMM(@Nullable Long l) {
        if (l != null) {
            long longValue = l.longValue() / 1000;
            long j = 60;
            long j2 = longValue % j;
            long j3 = (longValue / j) % j;
            long j4 = (longValue / ((long) CacheConstants.HOUR)) % 24;
            if (j4 <= 24) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j4), Long.valueOf(j3)}, 2));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                return format;
            }
            return null;
        }
        return null;
    }
}
