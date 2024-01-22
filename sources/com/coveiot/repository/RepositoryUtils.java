package com.coveiot.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.bp.datasources.db.read.BpDBRead;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.rr.datasources.db.read.RrDbRead;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.fitness.FitnessActivities;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public class RepositoryUtils {

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7305a;

        static {
            int[] iArr = new int[BleCommand.values().length];
            f7305a = iArr;
            try {
                iArr[BleCommand.GET_STEPS_DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7305a[BleCommand.GET_SLEEP_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7305a[BleCommand.GET_HEARTRATE_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7305a[BleCommand.GET_TEMPERATURE_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7305a[BleCommand.GET_BP_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7305a[BleCommand.GET_STRESS_DATA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7305a[BleCommand.GET_PERIODIC_SPO2.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static double calculateBMR(Context context) {
        int height = ProfileDBRead.getInstance(context).getLatestProfileData().getHeight();
        int weight = (int) ProfileDBRead.getInstance(context).getLatestProfileData().getWeight();
        int age = ProfileDBRead.getInstance(context).getLatestProfileData().getAge();
        int gender = ProfileDBRead.getInstance(context).getLatestProfileData().getGender();
        if (age == 0) {
            age = 30;
        }
        if (height == 0) {
            height = 175;
        }
        if (weight == 0) {
            weight = 75;
        }
        double d = ((weight * 10) + (height * 6.25d)) - (age * 5);
        return gender == 0 ? d + 5.0d : d - 161.0d;
    }

    public static double calculateBasicCaloriesForARMBand(int i, int i2, int i3, int i4, String str) {
        if (i3 == 0) {
            i3 = 30;
        }
        if (i == 0) {
            i = 175;
        }
        if (i2 == 0) {
            i = 75;
        }
        double d = ((i2 * 10) + (i * 6.25d)) - (i3 * 5);
        double d2 = i4 == 0 ? d + 5.0d : d - 161.0d;
        String str2 = "weight " + i2 + "height " + i + "age " + i3 + "gender " + i4;
        RepositoryModuleNames repositoryModuleNames = RepositoryModuleNames.REPOSITORY;
        LogHelper.d("RepositoryUtils", str2, repositoryModuleNames.getModuleName());
        LogHelper.d("RepositoryUtils", "BMR " + d2, repositoryModuleNames.getModuleName());
        Locale locale = Locale.ENGLISH;
        double parseDouble = Double.parseDouble(String.format(locale, "%.0f", Double.valueOf(d2)));
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parseDate(str, "yyyy-MM-dd"));
            if (isToday(calendar)) {
                LogHelper.d("RepositoryUtils", "isToday true", repositoryModuleNames.getModuleName());
                LogHelper.d("RepositoryUtils", "getCurrentSecondForTheDay " + getCurrentSecondForTheDay(), repositoryModuleNames.getModuleName());
                return Double.parseDouble(String.format(locale, "%.0f", Double.valueOf((d2 / 86400.0d) * getCurrentSecondForTheDay())));
            }
            LogHelper.d("RepositoryUtils", "isToday false", repositoryModuleNames.getModuleName());
            return parseDouble;
        } catch (ParseException e) {
            e.printStackTrace();
            return parseDouble;
        }
    }

    public static double calculateCaloriesForCZ(int i, int i2, int i3) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.0f", Double.valueOf(Math.floor(((float) ((i * i3) / (160934.0d / (i2 * 0.413d)))) * 0.9f))));
    }

    public static int calculateDistance(int i, int i2) {
        return (((int) (i2 * 0.413d)) * i) / 100;
    }

    public static double caluclateCaloreis(int i, int i2, int i3) {
        return Double.parseDouble(String.format(Locale.ENGLISH, "%.0f", Double.valueOf(Math.floor(((float) ((i * i3) / (160934.0d / (i2 * 0.413d)))) * 0.9f))));
    }

    public static boolean compareCalenderInstances(@NotNull Calendar calendar, @NotNull Calendar calendar2) {
        return findDateDifference(calendar, calendar2) > 0;
    }

    public static String convertCalendarToString(Calendar calendar) {
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static String convertStringToMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = BleConst.GetDeviceTime + hexString;
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int findDateDifference(@NotNull Calendar calendar, @NotNull Calendar calendar2) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        return (int) ((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 86400000);
    }

    public static String formatDate(Date date, String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    public static long getCurrentSecondForTheDay() {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (timeInMillis - calendar.getTimeInMillis()) / 1000;
    }

    public static String getCurrentTimezoneOffset() {
        TimeZone timeZone = TimeZone.getDefault();
        int offset = timeZone.getOffset(GregorianCalendar.getInstance(timeZone).getTimeInMillis());
        String format = String.format("%02d:%02d", Integer.valueOf(Math.abs(offset / TimeConstants.HOUR)), Integer.valueOf(Math.abs((offset / 60000) % 60)));
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(offset >= 0 ? "+" : "-");
        sb.append(format);
        return sb.toString();
    }

    public static String getDateFromTimestamp(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }

    @Nullable
    public static String getErrorDataType(BleBaseError bleBaseError) {
        if (bleBaseError != null) {
            try {
                if (bleBaseError.getBleCommands() != null) {
                    switch (a.f7305a[bleBaseError.getBleCommands().ordinal()]) {
                        case 1:
                            return "steps";
                        case 2:
                            return FitnessActivities.SLEEP;
                        case 3:
                            return "hr";
                        case 4:
                            return "body_temp";
                        case 5:
                            return "bp";
                        case 6:
                            return DeviceKey.Stress;
                        case 7:
                            return "spo2";
                        default:
                            return null;
                    }
                }
                return "DataType Null";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return "DataType Null";
    }

    public static String getPreviousDate(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(parseDate(str, "yyyy-MM-dd"));
            calendar.add(6, -1);
            return formatDate(calendar.getTime(), "yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static List<HeartRateHourData> getResponseHeartRateHourData(List<EntityHourlyHeartRateData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            heartRateHourData.avgHeartRatePerHour = (int) list.get(i).getAvgHeartRate();
            heartRateHourData.minHeartRatePerHour = list.get(i).getMinHeartRate();
            heartRateHourData.maxHeartRatePerHour = list.get(i).getMaxHeartRate();
            heartRateHourData.mMinuteWiseData = list.get(i).getCodedValues();
            heartRateHourData.setDate(list.get(i).getDate());
            heartRateHourData.setStartHour(list.get(i).getStartTime());
            heartRateHourData.setEndHour(list.get(i).getEndTime());
            heartRateHourData.setMacAddress(list.get(i).serial_no);
            arrayList.add(heartRateHourData);
        }
        return arrayList;
    }

    public static List<StepsHourData> getResponseStepsHourData(List<HourlyWalkData> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            StepsHourData stepsHourData = new StepsHourData();
            stepsHourData.setDate(list.get(i).mDate);
            stepsHourData.setMacAddress(list.get(i).mac_address);
            stepsHourData.setStepsPerHour(list.get(i).getIntervelValue());
            stepsHourData.setCaloriesPerHour(list.get(i).getCalories());
            stepsHourData.setDistancePerHour(list.get(i).getDistance());
            stepsHourData.setStartHour(list.get(i).getStartTime());
            stepsHourData.setEndHour(list.get(i).getEndTime());
            stepsHourData.setMinuteWiseData(list.get(i).getCodevalue());
            arrayList.add(stepsHourData);
        }
        return arrayList;
    }

    public static List<StressHourData> getResponseStressHourData(List<HourlyStress> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            StressHourData stressHourData = new StressHourData();
            stressHourData.setAvgStress(list.get(i).getStress_avg());
            stressHourData.setMinStress(list.get(i).getStress_low());
            stressHourData.setMaxStress(list.get(i).getStress_high());
            stressHourData.mMinuteWiseData = (ArrayList) list.get(i).getCodevalue();
            stressHourData.setDate(list.get(i).getmDate());
            stressHourData.setStartHour(list.get(i).getStartTime());
            stressHourData.setEndHour(list.get(i).getEndTime());
            stressHourData.setMacAddress(list.get(i).getMacAddress());
            arrayList.add(stressHourData);
        }
        return arrayList;
    }

    public static List<TemperatureHourData> getResponseTemperatureHourData(List<HourlyTemperature> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TemperatureHourData temperatureHourData = new TemperatureHourData();
            temperatureHourData.avgTemperaturePerHour = list.get(i).getTemperature_avg();
            temperatureHourData.minTemperaturePerHour = list.get(i).getTemperature_low();
            temperatureHourData.maxTemperaturePerHour = list.get(i).getTemperature_high();
            temperatureHourData.mMinuteWiseData = (ArrayList) list.get(i).getCodevalue();
            temperatureHourData.setDate(list.get(i).getmDate());
            temperatureHourData.setStartHour(list.get(i).getStartTime());
            temperatureHourData.setEndHour(list.get(i).getEndTime());
            temperatureHourData.setMacAddress(list.get(i).getMacAddress());
            arrayList.add(temperatureHourData);
        }
        return arrayList;
    }

    public static String getTimeFromTimestamp(long j) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(j);
        return DateFormat.format("HH:mm:ss", calendar).toString();
    }

    public static String getTimeZoneOffset() {
        String format = new SimpleDateFormat("Z").format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
        StringBuilder sb = new StringBuilder(format);
        sb.insert(format.length() - 2, ":");
        return sb.toString();
    }

    public static long getTimestampFromDate(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date.getTime();
    }

    public static String getYesterdayDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (str != null) {
            try {
                Date parse = simpleDateFormat.parse(str);
                if (parse != null) {
                    return simpleDateFormat.format(new Date(parse.getTime() - 86400000));
                }
                return null;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static boolean isDataPresentInDb(Context context, String str, String str2, ActivityType activityType) {
        return activityType == ActivityType.WALK ? WalkDBRead.getInstance(context).getRowCountForDailyData(str, str2) > 0 : activityType == ActivityType.SLEEP ? SleepDBRead.getInstance(context).getRowCountForDailyData(str, str2) > 0 : activityType == ActivityType.HEARTRATE ? HeartRateDBRead.getInstance(context).getRowCountForDailyData(str, str2) > 0 : activityType == ActivityType.BP ? BpDBRead.getInstance(context).getRowCountForDailyData(str, str2) > 0 : activityType == ActivityType.RR_HISTORY && RrDbRead.getInstance(context).getRowCountForDailyData(str, str2) > 0;
    }

    public static boolean isDateToday(String str) {
        try {
            return DateUtils.isToday(new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isEastApexDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_LEAP_CALL || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_FLEX_CONNECT || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_STRIDE_VOICE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_XTEND_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_STORM_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_COSMOS_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_LUNAR_CALL_ACE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_LUNAR_CONNECT_ACE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.EA_PRIMIA_ACE;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static boolean isIDODevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.IDO_SELECT || BleApiManager.getInstance(context).getDeviceType() == DeviceType.IDO_CONNECT;
    }

    public static boolean isJStyleDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1810G || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1790 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963D || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963YH || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle2301a || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle2208a || BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1860;
    }

    public static boolean isKaHaDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.v2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.v7 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.smartT;
    }

    public static boolean isKaHaDeviceREM(Context context) {
        if (BleApiManager.getInstance(context).getDeviceType() != DeviceType.CZ0 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA0 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CZ3 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA3 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.wavePrime && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA3_BT_CALL && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA5_WAVE_BEAT && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA5_WAVE_STYLE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA5_WAVE_PLAY && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA3_WAVE_COSMOS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA3_BT_STORM_PRO_CALL && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVE_ELITE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CA3_BT_WAVE_COSMOS_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC3_WAVE_SMART && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_BEAT_PLUS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.JC2230_01 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_STYLE_PLUS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_SMART_PLUS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CY1_PRIMIA_VOICE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CY1_LOOP_CALL_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CY1_LOOP_CONNECT_PRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.CY2_ACE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_LYNC) {
            DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
            DeviceType deviceType2 = DeviceType.ULC5_ULTIMA_CALL;
            if (deviceType != deviceType2 && BleApiManager.getInstance(context).getDeviceType() != deviceType2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVECALL2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVECALL3 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.STORMCALL2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.STORMCALL3 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVEASTRA && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVEASTRA2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVESIGMA && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVESIGMA3 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVENEOPLUS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVEACTIVE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULTIMAPRISM && BleApiManager.getInstance(context).getDeviceType() != DeviceType.JC2319B && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULTIMACHRONOS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVECONVEX && BleApiManager.getInstance(context).getDeviceType() != DeviceType.LUNARORB && BleApiManager.getInstance(context).getDeviceType() != DeviceType.JC2305B && BleApiManager.getInstance(context).getDeviceType() != DeviceType.LUNARPRIME && BleApiManager.getInstance(context).getDeviceType() != DeviceType.PRIMIACURV && BleApiManager.getInstance(context).getDeviceType() != DeviceType.XTENDPRO2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.STROMPROCALL2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.LUNARCALLPRO2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.LUNARCONNECTPRO2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVEPRIMIATALK2 && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULTIMACALLPRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULTIMACONNECTPRO && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVECOSMOSTALK && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC5_ULTIMA_CONNECT && BleApiManager.getInstance(context).getDeviceType() != DeviceType.PS1_ENIGMA_OASIS && BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULTIMA_RISE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVE_CHASE && BleApiManager.getInstance(context).getDeviceType() != DeviceType.WAVE_REGAL && BleApiManager.getInstance(context).getDeviceType() != DeviceType.BES_ENIGMA_VIRTUO) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMatrixDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.matrix || BleApiManager.getInstance(context).getDeviceType() == DeviceType.WAVEFORCE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.WAVEARMOUR || BleApiManager.getInstance(context).getDeviceType() == DeviceType.WAVEARMOUR2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.WAVEFORCE2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.LUNARFIT;
    }

    public static boolean isMoyangDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.kh17 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.crpGPF5;
    }

    public static boolean isNetConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected();
    }

    public static boolean isSmaDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaF2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaR9 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaM6 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.smaV2 || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GENESIS_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_ELEVATE_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_WAVE_GLORY_PRO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_LUNAR_SEEK || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_ULTIMA_VOGUE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_LUNAR_COMET || BleApiManager.getInstance(context).getDeviceType() == DeviceType.SMA_LUNAR_VELOCITY;
    }

    public static boolean isToday(@NotNull Calendar calendar) {
        return calendar.get(6) == Calendar.getInstance().get(6) && calendar.get(2) == Calendar.getInstance().get(2) && calendar.get(1) == Calendar.getInstance().get(1);
    }

    public static boolean isTouchELXDevice(Context context) {
        return BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_CALL_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_CONNECT_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_LUNAR_CALL || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_LUNAR_CONNECT || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_LUNAR_CALL_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_LUNAR_CONNECT_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_XTEND_CALL_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_STORM_CONNECT_PLUS || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_NEO || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_MAGMA || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_LUNAR_EMBRACE || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_SPECTRA || BleApiManager.getInstance(context).getDeviceType() == DeviceType.TOUCH_WAVE_FORTUNE;
    }

    public static Date parseDate(String str, String str2) throws ParseException {
        return new SimpleDateFormat(str2, Locale.ENGLISH).parse(str);
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() <= 0;
    }

    public static int findDateDifference(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        return BleApiUtils.findDateDifference(calendar.getTime(), calendar2.getTime());
    }
}
