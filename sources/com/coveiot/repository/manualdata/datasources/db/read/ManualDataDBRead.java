package com.coveiot.repository.manualdata.datasources.db.read;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.repository.manualdata.datasources.db.ManualDbRepo;
import java.util.List;
/* loaded from: classes9.dex */
public class ManualDataDBRead extends ManualDbRepo {

    /* renamed from: a  reason: collision with root package name */
    public static ManualDataDBRead f7410a;

    public ManualDataDBRead(Context context) {
        super(context);
    }

    public static ManualDataDBRead getInstance(Context context) {
        if (f7410a == null) {
            f7410a = new ManualDataDBRead(context);
        }
        return f7410a;
    }

    public LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3) {
        return this.manualDataDao.getBpData(str, str2, str3);
    }

    public List<EntityManualData> getBpDataWithoutLiveData(String str, String str2, String str3, String str4) {
        return this.manualDataDao.getBpDataWithoutLiveData(str, str2, str3, str4);
    }

    public LiveData<List<EntityManualData>> getHRVData(String str, String str2, String str3) {
        return this.manualDataDao.getHrvData(str, str2, str3);
    }

    public LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay() {
        return this.manualDataDao.getHighestSpo2DataForEachDay();
    }

    public EntityManualData getLastMeasuredBp(String str) {
        return this.manualDataDao.getLastMeasuredBp(str);
    }

    public LiveData<List<EntityManualData>> getLastMeasuredBpHourlyDataForDate(String str) {
        return this.manualDataDao.getLastMeasuredBpHourlyDataForDate(str);
    }

    public LiveData<EntityManualData> getLastMeasuredBpLiveData(String str) {
        return this.manualDataDao.getLastMeasuredBpLiveData(str);
    }

    public LiveData<EntityManualData> getLastMeasuredHRV(String str, String str2) {
        return this.manualDataDao.getLastMeasuredHRV(str, str2);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2(String str) {
        return this.manualDataDao.getLastMeasuredSpo2(str);
    }

    public EntityManualData getLastMeasuredSpo2ByMacAddress(String str) {
        return this.manualDataDao.getLastMeasuredSpo2ByMacAddress(str);
    }

    public LiveData<List<EntityManualData>> getLastMeasuredSpo2Data(String str, String str2, String str3, int i) {
        return this.manualDataDao.getLastMeasuredSpo2Data(str, str2, str3, i);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2) {
        return this.manualDataDao.getLastMeasuredSpo2DataFor(str, str2);
    }

    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3) {
        return this.manualDataDao.getLastMeasuredSpo2HourlyDataForDate(str, str2, str3);
    }

    public LiveData<EntityManualData> getLastMeasuredStress(String str, String str2) {
        return this.manualDataDao.getLastMeasuredStress(str, str2);
    }

    public EntityManualData getLatestBpDataFor(String str) {
        return this.manualDataDao.getLastBpDataFor(str);
    }

    public LiveData<List<EntityManualData>> getLatestBpDataForEachDay(String str, String str2) {
        return this.manualDataDao.getLastBpDataForEachDay(str, str2);
    }

    public LiveData<List<EntityManualData>> getLatestSpo2DataForEachDay() {
        return this.manualDataDao.getLastSpo2DataForEachDay();
    }

    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3) {
        return this.manualDataDao.getMaxSpo2HourlyDataForDate(str, str2, str3);
    }

    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3) {
        return this.manualDataDao.getMinSpo2HourlyDataForDate(str, str2, str3);
    }

    public List<EntityManualData> getSpo2DataList(String str, String str2, String str3) {
        return this.manualDataDao.getSpo2DataList(str, str2, str3);
    }

    public LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3) {
        return this.manualDataDao.getSpo2ata(str, str2, str3);
    }

    public LiveData<List<EntityManualData>> getStressData(String str, String str2, String str3) {
        return this.manualDataDao.getStressData(str, str2, str3);
    }

    public LiveData<List<EntityManualData>> getTemperatureData(String str, String str2, String str3) {
        return this.manualDataDao.getTemperatureData(str, str2, str3);
    }

    public List<EntityManualData> getUnSyncedBpData() {
        return this.manualDataDao.getUnSyncedBpData();
    }

    public List<EntityManualData> getUnSyncedData() {
        return this.manualDataDao.getUnSyncedData();
    }

    public LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3, String str4) {
        return this.manualDataDao.getBpData(str, str2, str3, str4);
    }

    public LiveData<List<EntityManualData>> getHRVData(String str, String str2) {
        return this.manualDataDao.getHrvData(str, str2);
    }

    public LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay(String str, String str2) {
        return this.manualDataDao.getHighestSpo2DataForEachDay(str, str2);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2) {
        return this.manualDataDao.getLastMeasuredSpo2(str, str2);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2, int i) {
        return this.manualDataDao.getLastMeasuredSpo2DataFor(str, str2, i);
    }

    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3, int i) {
        return this.manualDataDao.getLastMeasuredSpo2HourlyDataForDate(str, str2, str3, i);
    }

    public LiveData<List<EntityManualData>> getLatestSpo2DataForEachDay(String str, String str2) {
        return this.manualDataDao.getLastSpo2DataForEachDay(str, str2);
    }

    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3, int i) {
        return this.manualDataDao.getMaxSpo2HourlyDataForDate(str, str2, str3, i);
    }

    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3, int i) {
        return this.manualDataDao.getMinSpo2HourlyDataForDate(str, str2, str3, i);
    }

    public LiveData<List<EntityManualData>> getSpo2DataList(String str, String str2) {
        return this.manualDataDao.getSpo2DataList(str, str2);
    }

    public LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3, int i) {
        return this.manualDataDao.getSpo2ata(str, str2, str3, i);
    }

    public LiveData<List<EntityManualData>> getStressData(String str, String str2) {
        return this.manualDataDao.getStressData(str, str2);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2, int i) {
        return this.manualDataDao.getLastMeasuredSpo2(str, str2, i);
    }

    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str) {
        return this.manualDataDao.getLastMeasuredSpo2DataFor(str);
    }

    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str) {
        return this.manualDataDao.getLastMeasuredSpo2HourlyDataForDate(str);
    }

    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str) {
        return this.manualDataDao.getMaxSpo2HourlyDataForDate(str);
    }

    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str) {
        return this.manualDataDao.getMinSpo2HourlyDataForDate(str);
    }
}
