package com.coveiot.android.leonardo.utils;

import androidx.core.view.PointerIconCompat;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.utils.utility.AppUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes5.dex */
public class ProviderDummyData {
    public static String macAddress = "fc:39:43:37:e3:52";

    public static DailyWalkData getDailyWalkData() {
        DailyWalkData dailyWalkData = new DailyWalkData();
        dailyWalkData.setmDate(AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dailyWalkData.setMacAddress(macAddress);
        dailyWalkData.setValue(9240);
        dailyWalkData.setCalories(489.0d);
        dailyWalkData.setMeters(3200);
        return dailyWalkData;
    }

    public static List<HourlySleepData> getHourlySleepData() {
        ArrayList arrayList = new ArrayList();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 24; i++) {
            HourlySleepData hourlySleepData = new HourlySleepData();
            hourlySleepData.setMacAddress(macAddress);
            hourlySleepData.setDate(simpleDateFormat.format(new Date()));
            hourlySleepData.setStartTime(simpleDateFormat2.format(calendar.getTime()));
            calendar.add(10, 1);
            hourlySleepData.setEndTime(simpleDateFormat2.format(calendar.getTime()));
            hourlySleepData.setIntervalValue(17);
            int i2 = i % 2;
            if (i2 == 0) {
                hourlySleepData.setDeepSleep(7);
                hourlySleepData.setLigthSleep(10);
            } else {
                hourlySleepData.setDeepSleep(10);
                hourlySleepData.setLigthSleep(7);
            }
            hourlySleepData.setAwake(43);
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < 60; i3++) {
                if (i >= 17) {
                    arrayList2.add(0);
                } else if (i2 == 0) {
                    arrayList2.add(2);
                } else {
                    arrayList2.add(1);
                }
            }
            hourlySleepData.setCodevalue(arrayList2);
            arrayList.add(hourlySleepData);
        }
        return arrayList;
    }

    public static ArrayList<SleepDataModelForLastNight> getHourlySleepData1() {
        ArrayList<SleepDataModelForLastNight> arrayList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 12; i++) {
            SleepDataModelForLastNight sleepDataModelForLastNight = new SleepDataModelForLastNight();
            sleepDataModelForLastNight.setDate(simpleDateFormat.format(new Date()));
            sleepDataModelForLastNight.setStartTime(simpleDateFormat2.format(calendar.getTime()));
            calendar.add(10, 1);
            sleepDataModelForLastNight.setEndTime(simpleDateFormat2.format(calendar.getTime()));
            sleepDataModelForLastNight.setIntervalValue(17);
            int i2 = i % 2;
            if (i2 == 0) {
                sleepDataModelForLastNight.setDeepSleep(7);
                sleepDataModelForLastNight.setLigthSleep(10);
            } else {
                sleepDataModelForLastNight.setDeepSleep(10);
                sleepDataModelForLastNight.setLigthSleep(7);
            }
            sleepDataModelForLastNight.setAwake(43);
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < 60; i3++) {
                if (i >= 17) {
                    arrayList2.add(0);
                } else if (i2 == 0) {
                    arrayList2.add(2);
                } else {
                    arrayList2.add(1);
                }
            }
            sleepDataModelForLastNight.setCodevalue(arrayList2);
            arrayList.add(sleepDataModelForLastNight);
        }
        return arrayList;
    }

    public static List<HourlyWalkData> getHourlyWalkData() {
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 24; i++) {
            HourlyWalkData hourlyWalkData = new HourlyWalkData();
            hourlyWalkData.setMacAddress(macAddress);
            hourlyWalkData.setmDate(simpleDateFormat.format(new Date()));
            hourlyWalkData.setStartTime(simpleDateFormat2.format(calendar.getTime()));
            calendar.add(10, 1);
            hourlyWalkData.setEndTime(simpleDateFormat2.format(calendar.getTime()));
            hourlyWalkData.setIntervelValue(385);
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            for (int i2 = 75; i2 < 80; i2++) {
                arrayList2.add(Integer.valueOf(i2));
            }
            hourlyWalkData.setCodevalue(arrayList2);
            arrayList.add(hourlyWalkData);
        }
        return arrayList;
    }

    public static DailySleepData getSleepData() {
        DailySleepData dailySleepData = new DailySleepData();
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
        dailySleepData.setMacAddress(macAddress);
        dailySleepData.setDate(simpleDateFormat.format(new Date()));
        dailySleepData.setDeepSleep(168);
        dailySleepData.setLightSleep(240);
        dailySleepData.setAwakeTime(PointerIconCompat.TYPE_GRAB);
        dailySleepData.setValue(dailySleepData.getDeepSleep() + dailySleepData.getLightSleep());
        return dailySleepData;
    }
}
