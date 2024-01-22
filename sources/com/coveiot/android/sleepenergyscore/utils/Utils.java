package com.coveiot.android.sleepenergyscore.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.sleepenergyscore.R;
import com.coveiot.coveaccess.sleepscore.model.SleepHistory;
import com.coveiot.coveaccess.sleepscore.model.UserInfo;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.datasync.SleepSummaryData;
import com.coveiot.repository.datasync.SleepSummaryHelper;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.sleep.datasources.server.FormatorServerToEntity;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.utility.AppUtils;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class Utils {
    @NotNull
    public static final Utils INSTANCE = new Utils();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5747a = "Utils";

    @JvmStatic
    public static final float calculateBmi(float f, float f2) {
        float f3 = f2 / 100;
        return f / (f3 * f3);
    }

    @JvmStatic
    @NotNull
    public static final String currentDayString() {
        String str;
        Date time = Calendar.getInstance().getTime();
        Intrinsics.checkNotNullExpressionValue(time, "getInstance().time");
        try {
            str = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(time);
        } catch (ParseException e) {
            e.printStackTrace();
            str = null;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    @JvmStatic
    public static final int getActivityIcon(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        if (StringsKt__StringsKt.contains$default((CharSequence) type, (CharSequence) ":", false, 2, (Object) null)) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) type, new String[]{":"}, false, 0, 6, (Object) null);
            return WorkoutUtils.INSTANCE.getPhysicalActivityImage(Integer.parseInt((String) split$default.get(0)), Integer.parseInt((String) split$default.get(1)));
        }
        String upperCase = type.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        return Intrinsics.areEqual(upperCase, ActivityMode.RUN.name()) ? R.drawable.activity_running_icon : Intrinsics.areEqual(upperCase, ActivityMode.WALK.name()) ? R.drawable.activity_walking_icon : Intrinsics.areEqual(upperCase, ActivityMode.HIKING.name()) ? R.drawable.activity_hiking_icon : Intrinsics.areEqual(upperCase, ActivityMode.CLIMBING.name()) ? R.drawable.activity_climbing_icon : Intrinsics.areEqual(upperCase, ActivityMode.TREADMILL.name()) ? R.drawable.activity_treadmill_icon : Intrinsics.areEqual(upperCase, ActivityMode.CYCLE.name()) ? R.drawable.activity_cycling_icon : Intrinsics.areEqual(upperCase, ActivityMode.YOGA.name()) ? R.drawable.activity_yoga_icon : Intrinsics.areEqual(upperCase, ActivityMode.BASKETBALL.name()) ? R.drawable.activity_basketball_icon : Intrinsics.areEqual(upperCase, ActivityMode.FOOTBALL.name()) ? R.drawable.activity_football_icon : Intrinsics.areEqual(upperCase, ActivityMode.BADMINTON.name()) ? R.drawable.activity_badminton_icon : Intrinsics.areEqual(upperCase, ActivityMode.FREE_EXERCISE.name()) ? R.drawable.activity_free_exercise_icon : Intrinsics.areEqual(upperCase, ActivityMode.TENNIS.name()) ? R.drawable.activity_tennis_icon : Intrinsics.areEqual(upperCase, ActivityMode.MEDITATION.name()) ? R.drawable.activity_meditation_icon : Intrinsics.areEqual(upperCase, ActivityMode.DANCE.name()) ? R.drawable.activity_dance_icon : Intrinsics.areEqual(upperCase, ActivityMode.WORKOUT.name()) ? R.drawable.activity_workout_icon : Intrinsics.areEqual(upperCase, ActivityMode.SKIPPING.name()) ? R.drawable.activity_skipping_icon : Intrinsics.areEqual(upperCase, ActivityMode.ROWING.name()) ? R.drawable.activity_rowing_machine : Intrinsics.areEqual(upperCase, ActivityMode.ELLIPTICAL.name()) ? R.drawable.activity_elliptical : Intrinsics.areEqual(upperCase, ActivityMode.STEPS.name()) ? R.drawable.ic_steps_icon_drain : R.drawable.activity_running_icon;
    }

    @JvmStatic
    @NotNull
    public static final String getActivityModeNames(@NotNull Context context, int i, int i2) {
        Intrinsics.checkNotNullParameter(context, "context");
        CategoryAndActivityModel activityAndCategoryList = PhysicalActivityRepository.Companion.getInstance(context).getActivityAndCategoryList(i2, i);
        if (activityAndCategoryList != null) {
            if (AppUtils.isEmpty(activityAndCategoryList.getDefaultActivityName())) {
                return String.valueOf(activityAndCategoryList.getTitleInMetric());
            }
            return String.valueOf(activityAndCategoryList.getDefaultActivityName());
        }
        String string = context.getString(com.coveiot.android.activitymodes.R.string.physical_activity);
        Intrinsics.checkNotNull(string);
        return string;
    }

    @JvmStatic
    @NotNull
    public static final String getAmPmHourValue(@NotNull String hourValue) {
        int parseInt;
        List emptyList;
        boolean z;
        Intrinsics.checkNotNullParameter(hourValue, "hourValue");
        if (StringsKt__StringsKt.contains$default((CharSequence) hourValue, (CharSequence) ":", false, 2, (Object) null)) {
            List<String> split = new Regex(":").split(hourValue, 0);
            if (!split.isEmpty()) {
                ListIterator<String> listIterator = split.listIterator(split.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() == 0) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (!z) {
                        emptyList = CollectionsKt___CollectionsKt.take(split, listIterator.nextIndex() + 1);
                        break;
                    }
                }
            }
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            parseInt = Integer.parseInt(((String[]) emptyList.toArray(new String[0]))[0]);
        } else {
            parseInt = Integer.parseInt(hourValue);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, parseInt);
        calendar.get(10);
        String valueOf = String.valueOf(calendar.get(10));
        if (valueOf.equals(BleConst.GetDeviceTime)) {
            valueOf = BleConst.CMD_Reset;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        sb.append(calendar.get(9) == 0 ? " AM" : " PM");
        return sb.toString();
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(cal.time)");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentHour(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j != 0) {
            String format = AppUtils.getSimpleDateFormat("hh:00 a").format(new Date(j));
            Intrinsics.checkNotNullExpressionValue(format, "{\n                val si…          )\n            }");
            return format;
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getCurrentRateEnergyInfo(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = true;
        if (i >= 0 && i < 6) {
            String string = context.getResources().getString(R.string.current_rate_energy_score_info1);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…_rate_energy_score_info1)");
            return string;
        }
        if (6 <= i && i < 9) {
            String string2 = context.getResources().getString(R.string.current_rate_energy_score_info2);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…_rate_energy_score_info2)");
            return string2;
        }
        if (9 <= i && i < 13) {
            String string3 = context.getResources().getString(R.string.current_rate_energy_score_info3);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…_rate_energy_score_info3)");
            return string3;
        }
        if (13 > i || i >= 101) {
            z = false;
        }
        if (z) {
            String string4 = context.getResources().getString(R.string.current_rate_energy_score_info4);
            Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…_rate_energy_score_info4)");
            return string4;
        }
        return "";
    }

    @JvmStatic
    @Nullable
    public static final String getCurrentTimezoneOffset() {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis());
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(Math.abs(offset / TimeConstants.HOUR)), Integer.valueOf(Math.abs((offset / 60000) % 60))}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        StringBuilder sb = new StringBuilder();
        sb.append(offset >= 0 ? "+" : "-");
        sb.append(format);
        return sb.toString();
    }

    @JvmStatic
    @NotNull
    public static final List<Date> getDatesBetween(@NotNull String str_date, @NotNull String end_date) {
        Intrinsics.checkNotNullParameter(str_date, "str_date");
        Intrinsics.checkNotNullParameter(end_date, "end_date");
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long time = simpleDateFormat.parse(end_date).getTime();
        for (long time2 = simpleDateFormat.parse(str_date).getTime(); time2 <= time; time2 += 86400000) {
            arrayList.add(new Date(time2));
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Intrinsics.checkNotNullExpressionValue(simpleDateFormat.format((Date) arrayList.get(i)), "formatter.format(dates[i])");
        }
        return arrayList;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd/MM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getEnergyInfo(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = true;
        if (i >= 0 && i < 25) {
            String string = context.getResources().getString(R.string.energy_score_dynamic_info1);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ergy_score_dynamic_info1)");
            return string;
        }
        if (25 <= i && i < 50) {
            String string2 = context.getResources().getString(R.string.energy_score_dynamic_info2);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ergy_score_dynamic_info2)");
            return string2;
        }
        if (50 <= i && i < 75) {
            String string3 = context.getResources().getString(R.string.energy_score_dynamic_info3);
            Intrinsics.checkNotNullExpressionValue(string3, "context.resources.getStr…ergy_score_dynamic_info3)");
            return string3;
        }
        if (75 > i || i >= 101) {
            z = false;
        }
        if (z) {
            String string4 = context.getResources().getString(R.string.energy_score_dynamic_info4);
            Intrinsics.checkNotNullExpressionValue(string4, "context.resources.getStr…ergy_score_dynamic_info4)");
            return string4;
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final ArrayList<SleepHistory> getSleepHistoryArray(@NotNull Date endDate, @NotNull Context context, @NotNull String dateFormat) {
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dateFormat, "dateFormat");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(6, -1);
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone;
        calendar2.add(6, -3);
        String formatDate = RepositoryUtils.formatDate(calendar.getTime(), dateFormat);
        String formatDate2 = RepositoryUtils.formatDate(calendar2.getTime(), dateFormat);
        String str = f5747a;
        LogsHelper.d(str, "sleepHisstarttime*** " + formatDate2);
        LogsHelper.d(str, "sleepHisendtime*** " + formatDate);
        ArrayList<SleepHistory> arrayList = new ArrayList<>();
        List<DailySleepData> dailySleepDataBetweenDates = SleepDBRead.getInstance(context).getDailySleepDataBetweenDates(formatDate2, formatDate, BleApiManager.getInstance(context).getBleApi().getMacAddress());
        if (dailySleepDataBetweenDates.size() > 0) {
            for (DailySleepData dailySleepHistoryData : dailySleepDataBetweenDates) {
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(AppUtils.parseDate(dailySleepHistoryData.getDate(), dateFormat));
                SleepSummaryHelper sleepSummaryHelper = SleepSummaryHelper.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(calendar3, "calendar");
                SleepSummaryData sleepScoreSummary = sleepSummaryHelper.getSleepScoreSummary(context, calendar3);
                FormatorServerToEntity.Companion companion = FormatorServerToEntity.Companion;
                Intrinsics.checkNotNullExpressionValue(dailySleepHistoryData, "dailySleepHistoryData");
                arrayList.add(companion.getSleepHistoryItemHelper(dailySleepHistoryData, sleepScoreSummary));
            }
        }
        return arrayList;
    }

    @JvmStatic
    public static final int getTimeDifferenceInHours(@NotNull String dateStart, @NotNull String dateStop, @NotNull String formatter) {
        Intrinsics.checkNotNullParameter(dateStart, "dateStart");
        Intrinsics.checkNotNullParameter(dateStop, "dateStop");
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter, Locale.ENGLISH);
        try {
            Date parse = simpleDateFormat.parse(dateStart);
            Date parse2 = simpleDateFormat.parse(dateStop);
            Intrinsics.checkNotNull(parse2);
            long time = parse2.getTime();
            Intrinsics.checkNotNull(parse);
            return (int) (((time - parse.getTime()) / ((long) TimeConstants.HOUR)) % 24);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @JvmStatic
    @Nullable
    public static final String getTodayYesterdayDate(@Nullable Context context, @NotNull String strType, @NotNull String strDate, int i) {
        Resources resources;
        Resources resources2;
        Intrinsics.checkNotNullParameter(strType, "strType");
        Intrinsics.checkNotNullParameter(strDate, "strDate");
        String str = "";
        if (Intrinsics.areEqual(strType, Constants.DAY.getValue())) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                str = simpleDateFormat2.format(simpleDateFormat.parse(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                str = (context == null || (resources2 = context.getResources()) == null) ? null : resources2.getString(R.string.today);
            }
            calendar.add(5, -1);
            if (m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                if (context == null || (resources = context.getResources()) == null) {
                    return null;
                }
                return resources.getString(R.string.yesterday);
            }
            return str;
        }
        return "";
    }

    public static /* synthetic */ String getTodayYesterdayDate$default(Context context, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            i = 0;
        }
        return getTodayYesterdayDate(context, str, str2, i);
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringFromTimeStamp(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        if (j != 0) {
            if (DateUtils.isToday(j)) {
                return String.valueOf(context.getResources().getString(R.string.today));
            }
            if (INSTANCE.a(j)) {
                return String.valueOf(context.getResources().getString(R.string.yesterday));
            }
            return String.valueOf(simpleDateFormat.format(new Date(j)));
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringWithOnlyHourAt(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:00 a");
            if (DateUtils.isToday(j)) {
                return context.getResources().getString(R.string.today) + ' ' + simpleDateFormat2.format(new Date(j));
            } else if (INSTANCE.a(j)) {
                return String.valueOf(context.getResources().getString(R.string.yesterday));
            } else {
                return String.valueOf(simpleDateFormat.format(new Date(j)));
            }
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final String getTodayYesterdayStringWithTimeStamp(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm a");
            if (DateUtils.isToday(j)) {
                return context.getResources().getString(R.string.today_small) + ' ' + simpleDateFormat2.format(new Date(j));
            } else if (INSTANCE.a(j)) {
                return context.getResources().getString(R.string.yesterday_small) + ' ' + simpleDateFormat2.format(new Date(j));
            } else {
                return simpleDateFormat.format(new Date(j)) + ' ' + simpleDateFormat2.format(new Date(j));
            }
        }
        return "";
    }

    @JvmStatic
    @NotNull
    public static final UserInfo getUserInfo(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        UserInfo userInfo = new UserInfo();
        Intrinsics.checkNotNull(userDetails);
        if (!AppUtils.isEmpty(userDetails.getGender())) {
            String gender = userDetails.getGender();
            Intrinsics.checkNotNullExpressionValue(gender, "profileData.gender");
            String upperCase = gender.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            userInfo.setGender(upperCase);
        } else {
            String string = context.getResources().getString(R.string.male);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.male)");
            String upperCase2 = string.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
            userInfo.setGender(upperCase2);
        }
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        userInfo.setHeight(Integer.valueOf(Integer.parseInt(height)));
        String weight = userDetails.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        userInfo.setWeight(Integer.valueOf(Integer.parseInt(weight)));
        if (!AppUtils.isEmpty(userDetails.getDob())) {
            userInfo.setAge(AppUtils.getAge(userDetails.getDob()) > 119 ? 119 : Integer.valueOf(AppUtils.getAge(userDetails.getDob())));
        } else {
            userInfo.setAge(30);
        }
        return userInfo;
    }

    @JvmStatic
    @NotNull
    public static final com.coveiot.coveaccess.energyscore.model.UserInfo getUserInfoForEnergyMeter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProfileData userDetails = SessionManager.getInstance(context).getUserDetails();
        com.coveiot.coveaccess.energyscore.model.UserInfo userInfo = new com.coveiot.coveaccess.energyscore.model.UserInfo();
        Intrinsics.checkNotNull(userDetails);
        if (!AppUtils.isEmpty(userDetails.getGender())) {
            String gender = userDetails.getGender();
            Intrinsics.checkNotNullExpressionValue(gender, "profileData.gender");
            String upperCase = gender.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            userInfo.setGender(upperCase);
        } else {
            String string = context.getResources().getString(R.string.male);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.male)");
            String upperCase2 = string.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase()");
            userInfo.setGender(upperCase2);
        }
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        userInfo.setHeight(Integer.valueOf(Integer.parseInt(height)));
        String weight = userDetails.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        userInfo.setWeight(Integer.valueOf(Integer.parseInt(weight)));
        if (!AppUtils.isEmpty(userDetails.getDob())) {
            userInfo.setAge(AppUtils.getAge(userDetails.getDob()) > 119 ? 119 : Integer.valueOf(AppUtils.getAge(userDetails.getDob())));
        } else {
            userInfo.setAge(30);
        }
        return userInfo;
    }

    @JvmStatic
    @NotNull
    public static final String previousDayString() {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        try {
            str = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            str = null;
        }
        Intrinsics.checkNotNull(str);
        return str;
    }

    public final boolean a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(6, -1);
        return calendar.get(6) == calendar2.get(6);
    }

    @NotNull
    public final String getDateFormatValue(@NotNull Calendar calendar) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%04d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(1))}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(Soundex.SILENT_MARKER);
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(2) + 1)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(Soundex.SILENT_MARKER);
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(5))}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb.append(format3);
        return sb.toString();
    }

    @NotNull
    public final String getLastSyncTime(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context).getBleApi() != null) {
            EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            if (deviceInfoBy != null) {
                deviceInfoBy.getLasySyncTime();
                return context.getString(R.string.last_sync) + ' ' + getTodayYesterdayStringWithTimeStamp(deviceInfoBy.getLasySyncTime(), context);
            }
        }
        return "";
    }

    @NotNull
    public final Calendar getNextDayCalendar(@NotNull Calendar selectedCalendar) {
        Intrinsics.checkNotNullParameter(selectedCalendar, "selectedCalendar");
        selectedCalendar.add(6, 1);
        return selectedCalendar;
    }

    @NotNull
    public final Calendar getPreviousDayCalendar(@NotNull Calendar selectedCalendar) {
        Intrinsics.checkNotNullParameter(selectedCalendar, "selectedCalendar");
        selectedCalendar.add(6, -1);
        return selectedCalendar;
    }
}
