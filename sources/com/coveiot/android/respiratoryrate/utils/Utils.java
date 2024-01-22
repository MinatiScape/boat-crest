package com.coveiot.android.respiratoryrate.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.format.DateUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.sleepalgorithm.filtering.CZ0ParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.IDOParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MatrixParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.MoyangParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepData;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.android.sleepalgorithm.filtering.SmaParsedSleepDataF2;
import com.coveiot.android.sleepalgorithm.filtering.StrideParsedSleepDataV2NoAlgo;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleParsedSleepData;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepAlgoWithREM;
import com.coveiot.android.sleepalgorithm.filtering.jstyle.JStyleSleepData;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
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
import kotlin.Pair;
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
    public static final String getDayMonthFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd/MM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthFormatDate1(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd MMMM").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthYearFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd MMMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final String getDayMonthYearFormatDate1(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("dd MM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @NotNull
    public static final Pair<Integer, Long> getLatestValueOfDay(@NotNull Context context, @NotNull DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        ArrayList<Float> accuracies;
        ArrayList<Integer> values;
        ArrayList<Float> accuracies2;
        ArrayList<Integer> values2;
        RespiratoryRateRemoteConfiguration.Computation computation;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dailyRespiratoryRateEntity, "dailyRespiratoryRateEntity");
        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(context).getRespiratoryRateRemoteConfig();
        Float valueOf = (respiratoryRateRemoteConfig == null || (computation = respiratoryRateRemoteConfig.getComputation()) == null) ? null : Float.valueOf(computation.getConfidenceLevelThreshold());
        Date parse = AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(dailyRespiratoryRateEntity.getMDate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        int i = 0;
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
        ArrayList<Integer> values3 = respiratoryRateData != null ? respiratoryRateData.getValues() : null;
        boolean z = true;
        if (!(values3 == null || values3.isEmpty())) {
            RespiratoryRateData respiratoryRateData2 = dailyRespiratoryRateEntity.data;
            ArrayList<Float> accuracies3 = respiratoryRateData2 != null ? respiratoryRateData2.getAccuracies() : null;
            if (accuracies3 != null && !accuracies3.isEmpty()) {
                z = false;
            }
            if (!z) {
                RespiratoryRateData respiratoryRateData3 = dailyRespiratoryRateEntity.data;
                ArrayList<Integer> values4 = respiratoryRateData3 != null ? respiratoryRateData3.getValues() : null;
                Intrinsics.checkNotNull(values4);
                int size = values4.size();
                RespiratoryRateData respiratoryRateData4 = dailyRespiratoryRateEntity.data;
                ArrayList<Float> accuracies4 = respiratoryRateData4 != null ? respiratoryRateData4.getAccuracies() : null;
                Intrinsics.checkNotNull(accuracies4);
                if (size == accuracies4.size()) {
                    int i2 = 0;
                    for (int i3 = 13; i3 < 24; i3++) {
                        RespiratoryRateData respiratoryRateData5 = dailyRespiratoryRateEntity.data;
                        if (((respiratoryRateData5 == null || (values2 = respiratoryRateData5.getValues()) == null) ? null : values2.get(i3)) != null) {
                            RespiratoryRateData respiratoryRateData6 = dailyRespiratoryRateEntity.data;
                            ArrayList<Integer> values5 = respiratoryRateData6 != null ? respiratoryRateData6.getValues() : null;
                            Intrinsics.checkNotNull(values5);
                            Integer num = values5.get(i3);
                            Intrinsics.checkNotNull(num);
                            if (num.intValue() > 0) {
                                RespiratoryRateData respiratoryRateData7 = dailyRespiratoryRateEntity.data;
                                if (((respiratoryRateData7 == null || (accuracies2 = respiratoryRateData7.getAccuracies()) == null) ? null : accuracies2.get(i3)) != null) {
                                    RespiratoryRateData respiratoryRateData8 = dailyRespiratoryRateEntity.data;
                                    ArrayList<Float> accuracies5 = respiratoryRateData8 != null ? respiratoryRateData8.getAccuracies() : null;
                                    Intrinsics.checkNotNull(accuracies5);
                                    Float f = accuracies5.get(i3);
                                    Intrinsics.checkNotNull(f);
                                    float floatValue = f.floatValue();
                                    Intrinsics.checkNotNull(valueOf);
                                    if (floatValue >= valueOf.floatValue()) {
                                        calendar.set(11, i3);
                                        RespiratoryRateData respiratoryRateData9 = dailyRespiratoryRateEntity.data;
                                        ArrayList<Integer> values6 = respiratoryRateData9 != null ? respiratoryRateData9.getValues() : null;
                                        Intrinsics.checkNotNull(values6);
                                        Integer num2 = values6.get(i3);
                                        Intrinsics.checkNotNull(num2);
                                        i2 = num2.intValue();
                                    }
                                }
                            }
                        }
                    }
                    while (i < 13) {
                        RespiratoryRateData respiratoryRateData10 = dailyRespiratoryRateEntity.data;
                        if (((respiratoryRateData10 == null || (values = respiratoryRateData10.getValues()) == null) ? null : values.get(i)) != null) {
                            RespiratoryRateData respiratoryRateData11 = dailyRespiratoryRateEntity.data;
                            ArrayList<Integer> values7 = respiratoryRateData11 != null ? respiratoryRateData11.getValues() : null;
                            Intrinsics.checkNotNull(values7);
                            Integer num3 = values7.get(i);
                            Intrinsics.checkNotNull(num3);
                            if (num3.intValue() > 0) {
                                RespiratoryRateData respiratoryRateData12 = dailyRespiratoryRateEntity.data;
                                if (((respiratoryRateData12 == null || (accuracies = respiratoryRateData12.getAccuracies()) == null) ? null : accuracies.get(i)) != null) {
                                    RespiratoryRateData respiratoryRateData13 = dailyRespiratoryRateEntity.data;
                                    ArrayList<Float> accuracies6 = respiratoryRateData13 != null ? respiratoryRateData13.getAccuracies() : null;
                                    Intrinsics.checkNotNull(accuracies6);
                                    Float f2 = accuracies6.get(i);
                                    Intrinsics.checkNotNull(f2);
                                    float floatValue2 = f2.floatValue();
                                    Intrinsics.checkNotNull(valueOf);
                                    if (floatValue2 >= valueOf.floatValue()) {
                                        calendar.set(11, i);
                                        RespiratoryRateData respiratoryRateData14 = dailyRespiratoryRateEntity.data;
                                        ArrayList<Integer> values8 = respiratoryRateData14 != null ? respiratoryRateData14.getValues() : null;
                                        Intrinsics.checkNotNull(values8);
                                        Integer num4 = values8.get(i);
                                        Intrinsics.checkNotNull(num4);
                                        i2 = num4.intValue();
                                    }
                                }
                            }
                        }
                        i++;
                    }
                    i = i2;
                }
            }
        }
        return new Pair<>(Integer.valueOf(i), Long.valueOf(calendar.getTimeInMillis()));
    }

    @JvmStatic
    @NotNull
    public static final String getMonthYearFormatDate(@Nullable String str) {
        String format = AppUtils.getSimpleDateFormat("MMMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format…ateFormat.parse(strDate))");
        return format;
    }

    @JvmStatic
    @Nullable
    public static final String getTodayYesterdayDate(@Nullable Context context, @NotNull String strType, @NotNull String strDate, @Nullable String str, @Nullable String str2, @Nullable Integer num) {
        Resources resources;
        Resources resources2;
        Resources resources3;
        Intrinsics.checkNotNullParameter(strType, "strType");
        Intrinsics.checkNotNullParameter(strDate, "strDate");
        String str3 = null;
        String str4 = "";
        if (Intrinsics.areEqual(strType, Constants.DAY.getValue())) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                str4 = simpleDateFormat2.format(simpleDateFormat.parse(strDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                str4 = (context == null || (resources3 = context.getResources()) == null) ? null : resources3.getString(R.string.last_night);
            }
            calendar.add(5, -1);
            if (m.equals(simpleDateFormat.format(calendar.getTime()), strDate, true)) {
                if (context != null && (resources2 = context.getResources()) != null) {
                    str3 = resources2.getString(R.string.yesterday);
                }
                return str3;
            }
            return str4;
        } else if (Intrinsics.areEqual(strType, Constants.WEEK.getValue())) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setFirstDayOfWeek(2);
            Intrinsics.checkNotNull(num);
            calendar2.set(1, num.intValue());
            calendar2.set(3, Integer.parseInt(str));
            calendar2.add(5, -(calendar2.get(7) - 2));
            Date time = calendar2.getTime();
            calendar2.add(5, 6);
            Date time2 = calendar2.getTime();
            SimpleDateFormat simpleDateFormat3 = AppUtils.getSimpleDateFormat("dd MMM yyyy");
            StringBuilder sb = new StringBuilder();
            if (context != null && (resources = context.getResources()) != null) {
                str3 = resources.getString(R.string.week);
            }
            sb.append(str3);
            sb.append(Constants.EMPTY_SPACE.getValue());
            sb.append(str);
            String sb2 = sb.toString();
            return sb2 + '\n' + simpleDateFormat3.format(time) + " - " + simpleDateFormat3.format(time2);
        } else if (Intrinsics.areEqual(strType, Constants.MONTH.getValue())) {
            try {
                return AppUtils.getSimpleDateFormat("MMM yyyy").format(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(strDate));
            } catch (ParseException e2) {
                e2.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    public static /* synthetic */ String getTodayYesterdayDate$default(Context context, String str, String str2, String str3, String str4, Integer num, int i, Object obj) {
        String str5 = (i & 8) != 0 ? null : str3;
        String str6 = (i & 16) != 0 ? null : str4;
        if ((i & 32) != 0) {
            num = 0;
        }
        return getTodayYesterdayDate(context, str, str2, str5, str6, num);
    }

    @JvmStatic
    public static final boolean isCADevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false);
    }

    @JvmStatic
    public static final boolean isCYDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_primia_voice), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false);
    }

    @JvmStatic
    public static final boolean isCZDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false);
    }

    @JvmStatic
    public static final boolean isIDODevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_select), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false);
    }

    @JvmStatic
    public static final boolean isJstyleDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1790_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1810g_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963yh_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1963d_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.j1860_device), false);
    }

    @JvmStatic
    public static final boolean isKaHaDeviceWithRem(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_style), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_play), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca5_wave_beat), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_wave_cosmos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca3_bt_stormpro_call), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc3_wave_smart), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_beat_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_style_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_smart_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ulc2_wave_lync), false);
    }

    @JvmStatic
    public static final boolean isMatrixDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.matrix_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_fit), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force2), false);
    }

    @JvmStatic
    public static final boolean isMoyangDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangy20_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.moyangygpf5_device), false);
    }

    @JvmStatic
    public static final boolean isSmaDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smaf2_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false);
    }

    @JvmStatic
    public static final boolean isSmaS12Device(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.smas12_device), false);
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

    @Nullable
    public final SleepDataModel getLastNightSleepData(@NotNull Context context, @Nullable List<? extends SleepDataModelForLastNight> list) {
        SmaParsedSleepDataF2 smaParsedSleepDataF2;
        MoyangParsedSleepData moyangParsedSleepData;
        IDOParsedSleepData iDOParsedSleepData;
        MatrixParsedSleepData matrixParsedSleepData;
        StrideParsedSleepDataV2NoAlgo strideParsedSleepDataV2NoAlgo;
        JStyleParsedSleepData jStyleParsedSleepData;
        JStyleSleepAlgoWithREM jStyleSleepAlgoWithREM;
        Intrinsics.checkNotNullParameter(context, "context");
        CZ0ParsedSleepData cZ0ParsedSleepData = null;
        if (AppUtils.isEmpty(list)) {
            return null;
        }
        byte[] bArr = new byte[1440];
        Arrays.fill(bArr, 0, 1440, (byte) -1);
        Intrinsics.checkNotNull(list);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            int size2 = list.get(i).getCodevalue().size();
            for (int i2 = 0; i2 < size2; i2++) {
                int timeIndex = getTimeIndex(list.get(i), i2);
                if (timeIndex < 1440) {
                    Integer num = list.get(i).getCodevalue().get(i2);
                    if (num != null) {
                        if (!isJstyleDevice(context) && !isSmaDevice(context) && !isMoyangDevice(context) && !isCZDevice(context) && !isCADevice(context) && !isMatrixDevice(context) && !isIDODevice(context)) {
                            if (num.intValue() != 3) {
                                bArr[timeIndex] = (byte) num.intValue();
                            } else {
                                bArr[timeIndex] = -1;
                            }
                        } else {
                            bArr[timeIndex] = (byte) num.intValue();
                        }
                    } else {
                        bArr[timeIndex] = -1;
                    }
                }
            }
        }
        if (isJstyleDevice(context)) {
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isREMSupportedInSleep()) {
                try {
                    jStyleSleepAlgoWithREM = new JStyleSleepAlgoWithREM(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
                } catch (Exception e) {
                    e.printStackTrace();
                    jStyleSleepAlgoWithREM = null;
                }
                if (jStyleSleepAlgoWithREM == null || jStyleSleepAlgoWithREM.getSleepDataModel() == null) {
                    return null;
                }
                return jStyleSleepAlgoWithREM.getSleepDataModel();
            }
            try {
                jStyleParsedSleepData = new JStyleParsedSleepData(bArr, JStyleSleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e2) {
                e2.printStackTrace();
                jStyleParsedSleepData = null;
            }
            if (jStyleParsedSleepData == null || jStyleParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return jStyleParsedSleepData.getSleepDataModel();
        } else if (isSmaDevice(context)) {
            try {
                smaParsedSleepDataF2 = new SmaParsedSleepDataF2(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e3) {
                e3.printStackTrace();
                smaParsedSleepDataF2 = null;
            }
            if (smaParsedSleepDataF2 == null || smaParsedSleepDataF2.getSleepDataModel() == null) {
                return null;
            }
            return smaParsedSleepDataF2.getSleepDataModel();
        } else if (isMoyangDevice(context)) {
            try {
                moyangParsedSleepData = new MoyangParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e4) {
                e4.printStackTrace();
                moyangParsedSleepData = null;
            }
            if (moyangParsedSleepData == null || moyangParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return moyangParsedSleepData.getSleepDataModel();
        } else if (isIDODevice(context)) {
            try {
                iDOParsedSleepData = new IDOParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e5) {
                e5.printStackTrace();
                iDOParsedSleepData = null;
            }
            if (iDOParsedSleepData == null || iDOParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return iDOParsedSleepData.getSleepDataModel();
        } else if (isMatrixDevice(context)) {
            try {
                matrixParsedSleepData = new MatrixParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e6) {
                e6.printStackTrace();
                matrixParsedSleepData = null;
            }
            if (matrixParsedSleepData == null || matrixParsedSleepData.getSleepDataModel() == null) {
                return null;
            }
            return matrixParsedSleepData.getSleepDataModel();
        } else if (isKaHaDeviceWithRem(context)) {
            try {
                cZ0ParsedSleepData = new CZ0ParsedSleepData(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            Intrinsics.checkNotNull(cZ0ParsedSleepData);
            return cZ0ParsedSleepData.getSleepDataModel();
        } else {
            try {
                strideParsedSleepDataV2NoAlgo = new StrideParsedSleepDataV2NoAlgo(bArr, SleepData.SUPPORTED_ENCODING.ENCODING_NONE);
            } catch (Exception e8) {
                e8.printStackTrace();
                strideParsedSleepDataV2NoAlgo = null;
            }
            if (strideParsedSleepDataV2NoAlgo == null || strideParsedSleepDataV2NoAlgo.getSleepDataModel() == null) {
                return null;
            }
            return strideParsedSleepDataV2NoAlgo.getSleepDataModel();
        }
    }

    @NotNull
    public final String getLastSyncTime(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context).getBleApi() != null) {
            EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(context).getDeviceInfoBy(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            if (deviceInfoBy != null) {
                deviceInfoBy.getLasySyncTime();
                return context.getString(R.string.last_sync) + ' ' + INSTANCE.getTodayYesterdayStringWithTimeStamp(deviceInfoBy.getLasySyncTime(), context);
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

    public final int getTimeIndex(@NotNull SleepDataModelForLastNight sleepDataModelForLastNight, int i) {
        int parseInt;
        int parseInt2;
        Intrinsics.checkNotNullParameter(sleepDataModelForLastNight, "sleepDataModelForLastNight");
        String startTime = sleepDataModelForLastNight.getStartTime();
        Intrinsics.checkNotNullExpressionValue(startTime, "sleepDataModelForLastNight.startTime");
        String[] strArr = (String[]) StringsKt__StringsKt.split$default((CharSequence) startTime, new String[]{":"}, false, 0, 6, (Object) null).toArray(new String[0]);
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

    @NotNull
    public final String getTodayYesterdayStringFromTimeStamp(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd MMMM yyyy");
        if (j != 0) {
            if (DateUtils.isToday(j)) {
                return String.valueOf(context.getResources().getString(R.string.today));
            }
            if (a(j)) {
                return String.valueOf(context.getResources().getString(R.string.yesterday));
            }
            return String.valueOf(simpleDateFormat.format(new Date(j)));
        }
        return "";
    }

    @NotNull
    public final String getTodayYesterdayStringWithTimeStamp(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (j != 0) {
            SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm a");
            if (DateUtils.isToday(j)) {
                return context.getResources().getString(R.string.today_small) + ' ' + simpleDateFormat2.format(new Date(j));
            } else if (a(j)) {
                return context.getResources().getString(R.string.yesterday_small) + ' ' + simpleDateFormat2.format(new Date(j));
            } else {
                return simpleDateFormat.format(new Date(j)) + ' ' + simpleDateFormat2.format(new Date(j));
            }
        }
        return "";
    }
}
