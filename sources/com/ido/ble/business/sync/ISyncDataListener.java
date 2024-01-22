package com.ido.ble.business.sync;

import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthBodyPower;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthRespiratoryRate;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import java.util.List;
/* loaded from: classes11.dex */
public interface ISyncDataListener {
    void onGetActivityData(HealthActivity healthActivity);

    void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z);

    void onGetGpsData(HealthGps healthGps, List<HealthGpsItem> list, boolean z);

    void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3);

    void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3);

    void onGetHealthBodyPower(HealthBodyPower healthBodyPower);

    void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3);

    void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z);

    void onGetHealthNoiseData(HealthNoise healthNoise);

    void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z);

    void onGetHealthRespiratoryRate(HealthRespiratoryRate healthRespiratoryRate);

    void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3);

    void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z);

    void onGetHealthSportV3Data(HealthSportV3 healthSportV3);

    void onGetHealthSwimmingData(HealthSwimming healthSwimming);

    void onGetHealthTemperature(HealthTemperature healthTemperature);

    void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z);

    void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list);

    void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z);
}
