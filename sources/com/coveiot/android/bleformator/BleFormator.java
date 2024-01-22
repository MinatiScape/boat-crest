package com.coveiot.android.bleformator;

import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.coveiot.android.bleabstract.models.ManualHRVAndStressReading;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.response.AmbientSoundDayData;
import com.coveiot.android.bleabstract.response.AmbientSoundHourData;
import com.coveiot.android.bleabstract.response.AmbientSoundTimeLogBeanData;
import com.coveiot.android.bleabstract.response.BpDayData;
import com.coveiot.android.bleabstract.response.BpHourData;
import com.coveiot.android.bleabstract.response.BpIntervalData;
import com.coveiot.android.bleabstract.response.ECGResultResponse;
import com.coveiot.android.bleabstract.response.HRVDayData;
import com.coveiot.android.bleabstract.response.HRVHourData;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.response.RrDayData;
import com.coveiot.android.bleabstract.response.RrHourData;
import com.coveiot.android.bleabstract.response.RrTimeLogBeanData;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.Spo2DayData;
import com.coveiot.android.bleabstract.response.Spo2HourData;
import com.coveiot.android.bleabstract.response.Spo2TimeLogBeanData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.android.bleabstract.response.TemperatureDayData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.android.bleabstract.response.TemperatureTimeLogBeanData;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.ambientsound.EntityHourlyAmbientSoundData;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.bp.model.BpCodedValueData;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.sleep.HourlySleepData;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/coveiot/android/bleformator/BleFormator;", "", "<init>", "()V", "Companion", "bleformator_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes2.dex */
public final class BleFormator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000ú\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bR\u0010SJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tJ\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010J\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00140\rj\b\u0012\u0004\u0012\u00020\u0014`\u000f2\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0016J\u001e\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u001a0\rj\b\u0012\u0004\u0012\u00020\u001a`\u000f2\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001cJ\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020 0\rj\b\u0012\u0004\u0012\u00020 `\u000f2\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"J\u000e\u0010)\u001a\u00020(2\u0006\u0010'\u001a\u00020&J\u001e\u0010,\u001a\u0012\u0012\u0004\u0012\u00020+0\rj\b\u0012\u0004\u0012\u00020+`\u000f2\u0006\u0010*\u001a\u00020&J\u000e\u00100\u001a\u00020/2\u0006\u0010.\u001a\u00020-J\u001e\u00102\u001a\u0012\u0012\u0004\u0012\u0002010\rj\b\u0012\u0004\u0012\u000201`\u000f2\u0006\u0010.\u001a\u00020-J\u000e\u00106\u001a\u0002052\u0006\u00104\u001a\u000203J\u001e\u00108\u001a\u0012\u0012\u0004\u0012\u0002070\rj\b\u0012\u0004\u0012\u000207`\u000f2\u0006\u00104\u001a\u000203J>\u0010?\u001a\u0012\u0012\u0004\u0012\u00020>0\rj\b\u0012\u0004\u0012\u00020>`\u000f2\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u0002090\rj\b\u0012\u0004\u0012\u000209`\u000f2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;J>\u0010B\u001a\u0012\u0012\u0004\u0012\u00020>0\rj\b\u0012\u0004\u0012\u00020>`\u000f2\u0016\u0010A\u001a\u0012\u0012\u0004\u0012\u00020@0\rj\b\u0012\u0004\u0012\u00020@`\u000f2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;J>\u0010E\u001a\u0012\u0012\u0004\u0012\u00020>0\rj\b\u0012\u0004\u0012\u00020>`\u000f2\u0016\u0010D\u001a\u0012\u0012\u0004\u0012\u00020C0\rj\b\u0012\u0004\u0012\u00020C`\u000f2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020;J\u000e\u0010I\u001a\u00020H2\u0006\u0010G\u001a\u00020FJ\u001e\u0010K\u001a\u0012\u0012\u0004\u0012\u00020J0\rj\b\u0012\u0004\u0012\u00020J`\u000f2\u0006\u0010G\u001a\u00020FJ\u000e\u0010O\u001a\u00020N2\u0006\u0010M\u001a\u00020LJ\u001e\u0010Q\u001a\u0012\u0012\u0004\u0012\u00020P0\rj\b\u0012\u0004\u0012\u00020P`\u000f2\u0006\u0010M\u001a\u00020L¨\u0006T"}, d2 = {"Lcom/coveiot/android/bleformator/BleFormator$Companion;", "", "Lcom/coveiot/android/bleabstract/response/StepsDayData;", "stepsDayData", "Lcom/coveiot/covedb/walk/entities/DailyWalkData;", "getDailyWalkData", "", "Lcom/coveiot/covedb/walk/entities/HourlyWalkData;", "getHourlySleepData", "Lcom/coveiot/android/bleabstract/response/SleepDayData;", "sleepDayData", "Lcom/coveiot/covedb/sleep/DailySleepData;", "getDailySleepData", "Ljava/util/ArrayList;", "Lcom/coveiot/covedb/sleep/HourlySleepData;", "Lkotlin/collections/ArrayList;", "Lcom/coveiot/android/bleabstract/response/HeartRateDayData;", "heartRateDayData", "Lcom/coveiot/covedb/heartrate/EntityDailyHeartRateData;", "getDailyHeartRateData", "Lcom/coveiot/covedb/heartrate/EntityHourlyHeartRateData;", "getHourlyHeartRateData", "Lcom/coveiot/android/bleabstract/response/BpDayData;", "bpDayData", "Lcom/coveiot/covedb/bp/entities/EntityDailyBp;", "getDailyBpData", "Lcom/coveiot/covedb/bp/entities/EntityHourlyBp;", "getHourlyBPData", "Lcom/coveiot/android/bleabstract/response/RrDayData;", "rrDayData", "Lcom/coveiot/covedb/rr/entity/EntityDailyRrData;", "getDailyRrData", "Lcom/coveiot/covedb/rr/entity/EntityHourlyRrData;", "getHourlyRrData", "Lcom/coveiot/android/bleabstract/response/ECGResultResponse;", "ecgResultResponse", "Lcom/coveiot/covedb/ecg/EntityECGSummaryData;", "getECGResultData", "Lcom/coveiot/android/bleabstract/response/TemperatureDayData;", "temperatureDayData", "Lcom/coveiot/covedb/temperature/entity/DailyTemperature;", "getDailyTemperatureData", "tempratureDayData", "Lcom/coveiot/covedb/temperature/entity/HourlyTemperature;", "getHourlyTemperatureData", "Lcom/coveiot/android/bleabstract/response/Spo2DayData;", "spo2DayData", "Lcom/coveiot/covedb/spo2/entity/DailyPeriodicSpo2;", "getDailySpo2Data", "Lcom/coveiot/covedb/spo2/entity/EntityHourlySpo2;", "getHourlySpo2Data", "Lcom/coveiot/android/bleabstract/response/StressDayData;", "stressDayData", "Lcom/coveiot/covedb/stress/entity/DailyStress;", "getDailyStressData", "Lcom/coveiot/covedb/stress/entity/HourlyStress;", "getHourlyStressData", "Lcom/coveiot/android/bleabstract/models/ManualBpReading;", "manualBpReadingList", "", "serialNo", "userDeviceId", "Lcom/coveiot/covedb/manualdata/entities/EntityManualData;", "getEntityManualDataList", "Lcom/coveiot/android/bleabstract/models/ManualSpo2Reading;", "manualSpo2ReadingList", "getEntityManualDataListSpo2", "Lcom/coveiot/android/bleabstract/models/ManualHRVAndStressReading;", "manualHRVAndStressReadingList", "getEntityManualHRVAndStressDataList", "Lcom/coveiot/android/bleabstract/response/HRVDayData;", "hrvDayData", "Lcom/coveiot/covedb/hrv/entity/DailyHRV;", "getDailyHRVData", "Lcom/coveiot/covedb/hrv/entity/HourlyHRV;", "getHourlyHRVData", "Lcom/coveiot/android/bleabstract/response/AmbientSoundDayData;", "ambientSoundDayData", "Lcom/coveiot/covedb/ambientsound/EntityDailyAmbientSoundData;", "getDailyAmbientSoundData", "Lcom/coveiot/covedb/ambientsound/EntityHourlyAmbientSoundData;", "getHourlyAmbientSoundData", "<init>", "()V", "bleformator_release"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EntityDailyAmbientSoundData getDailyAmbientSoundData(@NotNull AmbientSoundDayData ambientSoundDayData) {
            Intrinsics.checkNotNullParameter(ambientSoundDayData, "ambientSoundDayData");
            EntityDailyAmbientSoundData entityDailyAmbientSoundData = new EntityDailyAmbientSoundData();
            entityDailyAmbientSoundData.setDate(ambientSoundDayData.mDate);
            entityDailyAmbientSoundData.setSerialNo(ambientSoundDayData.mMacAddress);
            entityDailyAmbientSoundData.setMaxAmbientSound(ambientSoundDayData.getMaxAmbientSound());
            entityDailyAmbientSoundData.setMinAmbientSound(ambientSoundDayData.getMinAmbientSound());
            entityDailyAmbientSoundData.setAvgAmbientSound(ambientSoundDayData.getAvgAmbientSound());
            entityDailyAmbientSoundData.setTotalTime(ambientSoundDayData.getTotalAmbientSoundTime());
            return entityDailyAmbientSoundData;
        }

        @NotNull
        public final EntityDailyBp getDailyBpData(@NotNull BpDayData bpDayData) {
            Intrinsics.checkNotNullParameter(bpDayData, "bpDayData");
            EntityDailyBp entityDailyBp = new EntityDailyBp();
            entityDailyBp.date = bpDayData.mDate;
            entityDailyBp.setSerialNo(bpDayData.getmMacAddress());
            entityDailyBp.diastolic_bp = bpDayData.getDiastolicBp();
            entityDailyBp.setSystolicBp(bpDayData.getSystolicBp());
            return entityDailyBp;
        }

        @NotNull
        public final DailyHRV getDailyHRVData(@NotNull HRVDayData hrvDayData) {
            Intrinsics.checkNotNullParameter(hrvDayData, "hrvDayData");
            DailyHRV dailyHRV = new DailyHRV();
            dailyHRV.mDate = hrvDayData.mDate;
            dailyHRV.mac_address = hrvDayData.mMacAddress;
            dailyHRV.hrv_high = hrvDayData.getMaxHrv();
            dailyHRV.hrv_low = hrvDayData.getMinHrv();
            dailyHRV.hrv_avg = hrvDayData.getAvgHrv();
            dailyHRV.baselinne = hrvDayData.getBaselineHrv();
            dailyHRV.readiness = hrvDayData.getReadinessHrv();
            return dailyHRV;
        }

        @NotNull
        public final EntityDailyHeartRateData getDailyHeartRateData(@NotNull HeartRateDayData heartRateDayData) {
            Intrinsics.checkNotNullParameter(heartRateDayData, "heartRateDayData");
            EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
            entityDailyHeartRateData.setDate(heartRateDayData.mDate);
            entityDailyHeartRateData.setSerialNo(heartRateDayData.mMacAddress);
            entityDailyHeartRateData.setMaxHeartRate(heartRateDayData.getMaxHeartRate());
            entityDailyHeartRateData.setMinHeartRate(heartRateDayData.getMinHeartRate());
            entityDailyHeartRateData.setAvgHeartRate(heartRateDayData.getAvgHeartRate());
            return entityDailyHeartRateData;
        }

        @NotNull
        public final EntityDailyRrData getDailyRrData(@NotNull RrDayData rrDayData) {
            Intrinsics.checkNotNullParameter(rrDayData, "rrDayData");
            EntityDailyRrData entityDailyRrData = new EntityDailyRrData();
            entityDailyRrData.setDate(rrDayData.mDate);
            entityDailyRrData.serial_no = rrDayData.mMacAddress;
            entityDailyRrData.setAvgRrvalue(rrDayData.getRrInterval());
            return entityDailyRrData;
        }

        @NotNull
        public final DailySleepData getDailySleepData(@NotNull SleepDayData sleepDayData) {
            Intrinsics.checkNotNullParameter(sleepDayData, "sleepDayData");
            DailySleepData dailySleepData = new DailySleepData();
            dailySleepData.setDate(sleepDayData.mDate);
            dailySleepData.setMacAddress(sleepDayData.getmMacAddress());
            dailySleepData.setValue(sleepDayData.mTotalSleep);
            dailySleepData.setDeepSleep(sleepDayData.mDeepSleep);
            dailySleepData.setLightSleep(sleepDayData.mLightSleep);
            dailySleepData.setAwakeTime(sleepDayData.mAwakeTime);
            dailySleepData.setSleepScore(sleepDayData.mSleepScore);
            dailySleepData.setBreathQuality(sleepDayData.mBreathQuality);
            return dailySleepData;
        }

        @NotNull
        public final DailyPeriodicSpo2 getDailySpo2Data(@NotNull Spo2DayData spo2DayData) {
            Intrinsics.checkNotNullParameter(spo2DayData, "spo2DayData");
            DailyPeriodicSpo2 dailyPeriodicSpo2 = new DailyPeriodicSpo2();
            dailyPeriodicSpo2.mDate = spo2DayData.mDate;
            dailyPeriodicSpo2.mac_address = spo2DayData.mMacAddress;
            dailyPeriodicSpo2.spo2_high = spo2DayData.getMaxSPo2();
            dailyPeriodicSpo2.spo2_low = spo2DayData.getMinSpo2();
            dailyPeriodicSpo2.spo2_avg = spo2DayData.getAvgSpo2();
            return dailyPeriodicSpo2;
        }

        @NotNull
        public final DailyStress getDailyStressData(@NotNull StressDayData stressDayData) {
            Intrinsics.checkNotNullParameter(stressDayData, "stressDayData");
            DailyStress dailyStress = new DailyStress();
            dailyStress.mDate = stressDayData.mDate;
            dailyStress.mac_address = stressDayData.mMacAddress;
            dailyStress.stress_high = stressDayData.getMaxStress();
            dailyStress.stress_low = stressDayData.getMinStress();
            dailyStress.stress_avg = stressDayData.getAvgStress();
            dailyStress.baselinne = stressDayData.getBaselineStress();
            dailyStress.readiness = stressDayData.getReadinessStress();
            return dailyStress;
        }

        @NotNull
        public final DailyTemperature getDailyTemperatureData(@NotNull TemperatureDayData temperatureDayData) {
            Intrinsics.checkNotNullParameter(temperatureDayData, "temperatureDayData");
            DailyTemperature dailyTemperature = new DailyTemperature();
            dailyTemperature.mDate = temperatureDayData.mDate;
            dailyTemperature.mac_address = temperatureDayData.mMacAddress;
            dailyTemperature.temperature_high = temperatureDayData.getMaxTemperature();
            dailyTemperature.temperature_low = temperatureDayData.getMinTemperature();
            dailyTemperature.temperature_avg = temperatureDayData.getAvgTemperature();
            return dailyTemperature;
        }

        @NotNull
        public final DailyWalkData getDailyWalkData(@NotNull StepsDayData stepsDayData) {
            Intrinsics.checkNotNullParameter(stepsDayData, "stepsDayData");
            DailyWalkData dailyWalkData = new DailyWalkData();
            dailyWalkData.mDate = stepsDayData.mDate;
            dailyWalkData.setValue(stepsDayData.mSteps);
            dailyWalkData.setCalories(stepsDayData.getmCalories());
            dailyWalkData.setMeters((int) stepsDayData.getmDistance());
            dailyWalkData.setMacAddress(stepsDayData.getmMacAddress());
            dailyWalkData.setActiveTime(Integer.valueOf(stepsDayData.active_time));
            return dailyWalkData;
        }

        @NotNull
        public final EntityECGSummaryData getECGResultData(@NotNull ECGResultResponse ecgResultResponse) {
            Intrinsics.checkNotNullParameter(ecgResultResponse, "ecgResultResponse");
            EntityECGSummaryData entityECGSummaryData = new EntityECGSummaryData();
            entityECGSummaryData.startDateTime = ecgResultResponse.getStartDateTime();
            entityECGSummaryData.endDateTime = ecgResultResponse.getEndDateTime();
            String mMacAddress = ecgResultResponse.getMMacAddress();
            Intrinsics.checkNotNull(mMacAddress);
            entityECGSummaryData.serial_no = mMacAddress;
            entityECGSummaryData.heart_rate = ecgResultResponse.getHeartRateValue();
            entityECGSummaryData.hrv_value = ecgResultResponse.getHrvValue();
            entityECGSummaryData.stress = ecgResultResponse.getStressValue();
            entityECGSummaryData.high_bp = ecgResultResponse.getHighBpValue();
            entityECGSummaryData.low_bp = ecgResultResponse.getLowBpValue();
            entityECGSummaryData.rr_interval = ecgResultResponse.getBreathValue();
            return entityECGSummaryData;
        }

        @NotNull
        public final ArrayList<EntityManualData> getEntityManualDataList(@NotNull ArrayList<ManualBpReading> manualBpReadingList, @NotNull String serialNo, @NotNull String userDeviceId) {
            Intrinsics.checkNotNullParameter(manualBpReadingList, "manualBpReadingList");
            Intrinsics.checkNotNullParameter(serialNo, "serialNo");
            Intrinsics.checkNotNullParameter(userDeviceId, "userDeviceId");
            ArrayList<EntityManualData> arrayList = new ArrayList<>();
            Iterator<ManualBpReading> it = manualBpReadingList.iterator();
            while (it.hasNext()) {
                ManualBpReading next = it.next();
                long j = 1000;
                EntityManualData entityManualData = new EntityManualData((next.getTimeStamp() / j) * j, Source.FROM_DEVICE.name());
                entityManualData.setSerialNo(serialNo);
                entityManualData.setSystolicBp(next.getSystolicbp());
                entityManualData.setUserDeviceId(userDeviceId);
                entityManualData.setDiastolicBp(next.getDiastolicbp());
                entityManualData.setHr(next.getHr());
                arrayList.add(entityManualData);
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityManualData> getEntityManualDataListSpo2(@NotNull ArrayList<ManualSpo2Reading> manualSpo2ReadingList, @NotNull String serialNo, @NotNull String userDeviceId) {
            Intrinsics.checkNotNullParameter(manualSpo2ReadingList, "manualSpo2ReadingList");
            Intrinsics.checkNotNullParameter(serialNo, "serialNo");
            Intrinsics.checkNotNullParameter(userDeviceId, "userDeviceId");
            ArrayList<EntityManualData> arrayList = new ArrayList<>();
            Iterator<ManualSpo2Reading> it = manualSpo2ReadingList.iterator();
            while (it.hasNext()) {
                ManualSpo2Reading next = it.next();
                long j = 1000;
                EntityManualData entityManualData = new EntityManualData((next.getTimeStamp() / j) * j, Source.FROM_DEVICE.name());
                entityManualData.setSerialNo(serialNo);
                entityManualData.setSpo2(next.getSpo2());
                entityManualData.setUserDeviceId(userDeviceId);
                entityManualData.setLevelInterpretation(next.getLevelInterpretation());
                arrayList.add(entityManualData);
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityManualData> getEntityManualHRVAndStressDataList(@NotNull ArrayList<ManualHRVAndStressReading> manualHRVAndStressReadingList, @NotNull String serialNo, @NotNull String userDeviceId) {
            Intrinsics.checkNotNullParameter(manualHRVAndStressReadingList, "manualHRVAndStressReadingList");
            Intrinsics.checkNotNullParameter(serialNo, "serialNo");
            Intrinsics.checkNotNullParameter(userDeviceId, "userDeviceId");
            ArrayList<EntityManualData> arrayList = new ArrayList<>();
            Iterator<ManualHRVAndStressReading> it = manualHRVAndStressReadingList.iterator();
            while (it.hasNext()) {
                ManualHRVAndStressReading next = it.next();
                long j = 1000;
                EntityManualData entityManualData = new EntityManualData((next.getTimeStamp() / j) * j, Source.FROM_DEVICE.name());
                entityManualData.setSerialNo(serialNo);
                entityManualData.setUserDeviceId(userDeviceId);
                entityManualData.setHrv(next.getHrv());
                entityManualData.setStress(next.getStress());
                entityManualData.setHr(next.getHr());
                entityManualData.setVascularAging(next.getVascularAging());
                arrayList.add(entityManualData);
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityHourlyAmbientSoundData> getHourlyAmbientSoundData(@NotNull AmbientSoundDayData ambientSoundDayData) {
            Intrinsics.checkNotNullParameter(ambientSoundDayData, "ambientSoundDayData");
            ArrayList<EntityHourlyAmbientSoundData> arrayList = new ArrayList<>();
            if (ambientSoundDayData.getTimeLogBean() != null) {
                AmbientSoundTimeLogBeanData timeLogBean = ambientSoundDayData.getTimeLogBean();
                Intrinsics.checkNotNull(timeLogBean);
                if (timeLogBean.getLogBeans() != null) {
                    AmbientSoundTimeLogBeanData timeLogBean2 = ambientSoundDayData.getTimeLogBean();
                    Intrinsics.checkNotNull(timeLogBean2);
                    List<AmbientSoundHourData> logBeans = timeLogBean2.getLogBeans();
                    Intrinsics.checkNotNull(logBeans);
                    if (!logBeans.isEmpty()) {
                        AmbientSoundTimeLogBeanData timeLogBean3 = ambientSoundDayData.getTimeLogBean();
                        Intrinsics.checkNotNull(timeLogBean3);
                        List<AmbientSoundHourData> logBeans2 = timeLogBean3.getLogBeans();
                        Intrinsics.checkNotNull(logBeans2);
                        for (AmbientSoundHourData ambientSoundHourData : logBeans2) {
                            EntityHourlyAmbientSoundData entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                            entityHourlyAmbientSoundData.setDate(ambientSoundHourData.getDate());
                            entityHourlyAmbientSoundData.setSerialNo(ambientSoundDayData.mMacAddress);
                            entityHourlyAmbientSoundData.setStartTime(ambientSoundHourData.getStartHour());
                            entityHourlyAmbientSoundData.setEndTime(ambientSoundHourData.getEndHour());
                            entityHourlyAmbientSoundData.setMaxAmbientSound(ambientSoundHourData.getMaxAmbientSound());
                            entityHourlyAmbientSoundData.setMinAmbientSound(ambientSoundHourData.getMinAmbientSound());
                            entityHourlyAmbientSoundData.setAvgAmbientSound(ambientSoundHourData.getAvgAmbientSound());
                            entityHourlyAmbientSoundData.setCodedValues(ambientSoundHourData.getmMinuteWiseData());
                            arrayList.add(entityHourlyAmbientSoundData);
                        }
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityHourlyBp> getHourlyBPData(@NotNull BpDayData bpDayData) {
            Intrinsics.checkNotNullParameter(bpDayData, "bpDayData");
            ArrayList<EntityHourlyBp> arrayList = new ArrayList<>();
            if (bpDayData.getTimeLogBean() != null && bpDayData.getTimeLogBean().getLogBeans() != null) {
                List<BpHourData> logBeans = bpDayData.getTimeLogBean().getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "bpDayData.getTimeLogBean().logBeans");
                if (!logBeans.isEmpty()) {
                    for (BpHourData bpHourData : bpDayData.getTimeLogBean().getLogBeans()) {
                        EntityHourlyBp entityHourlyBp = new EntityHourlyBp();
                        entityHourlyBp.date = bpHourData.getDate();
                        entityHourlyBp.setStartHour(bpHourData.getStartHour());
                        entityHourlyBp.setEndHour(bpHourData.getEndHour());
                        entityHourlyBp.setSerialNo(bpHourData.getmMacAddress());
                        entityHourlyBp.setSystolicBp(bpHourData.mSystolicBpPerHour);
                        entityHourlyBp.setDiastolicBp(bpHourData.mdiastolicBpPerHour);
                        ArrayList<BpCodedValueData> arrayList2 = new ArrayList<>();
                        Iterator<BpIntervalData> it = bpHourData.getmCodedList().iterator();
                        while (it.hasNext()) {
                            BpIntervalData next = it.next();
                            BpCodedValueData bpCodedValueData = new BpCodedValueData();
                            bpCodedValueData.setSystolicBp(next.getSystolicBp());
                            bpCodedValueData.setDiastolicBp(next.getDiastolicBp());
                            arrayList2.add(bpCodedValueData);
                        }
                        entityHourlyBp.codevalues = arrayList2;
                        arrayList.add(entityHourlyBp);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<HourlyHRV> getHourlyHRVData(@NotNull HRVDayData hrvDayData) {
            Intrinsics.checkNotNullParameter(hrvDayData, "hrvDayData");
            ArrayList<HourlyHRV> arrayList = new ArrayList<>();
            if (hrvDayData.getHrvTimeLogBeanData() != null && hrvDayData.getHrvTimeLogBeanData().getLogBeans() != null) {
                List<HRVHourData> logBeans = hrvDayData.getHrvTimeLogBeanData().getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "hrvDayData.hrvTimeLogBeanData.logBeans");
                if (!logBeans.isEmpty()) {
                    for (HRVHourData hRVHourData : hrvDayData.getHrvTimeLogBeanData().getLogBeans()) {
                        HourlyHRV hourlyHRV = new HourlyHRV();
                        hourlyHRV.mDate = hRVHourData.getDate();
                        hourlyHRV.setMacAddress(hrvDayData.mMacAddress);
                        hourlyHRV.setStartTime(hRVHourData.getStartHour());
                        hourlyHRV.setEndTime(hRVHourData.getEndHour());
                        hourlyHRV.hrv_low = hRVHourData.getMinHRV();
                        hourlyHRV.hrv_high = hRVHourData.getMaxHRV();
                        hourlyHRV.hrv_avg = hRVHourData.getAvgHRV();
                        hourlyHRV.setCodevalue(hRVHourData.getmMinuteWiseData());
                        arrayList.add(hourlyHRV);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityHourlyHeartRateData> getHourlyHeartRateData(@NotNull HeartRateDayData heartRateDayData) {
            Intrinsics.checkNotNullParameter(heartRateDayData, "heartRateDayData");
            ArrayList<EntityHourlyHeartRateData> arrayList = new ArrayList<>();
            HeartRateTimeLogBeanData heartRateTimeLogBeanData = heartRateDayData.timeLogBean;
            if (heartRateTimeLogBeanData != null && heartRateTimeLogBeanData.getLogBeans() != null) {
                List<HeartRateHourData> logBeans = heartRateDayData.timeLogBean.getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "heartRateDayData.timeLogBean.logBeans");
                if (!logBeans.isEmpty()) {
                    for (HeartRateHourData heartRateHourData : heartRateDayData.timeLogBean.getLogBeans()) {
                        EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                        entityHourlyHeartRateData.setDate(heartRateHourData.getDate());
                        entityHourlyHeartRateData.setSerialNo(heartRateDayData.mMacAddress);
                        entityHourlyHeartRateData.setStartTime(heartRateHourData.getStartHour());
                        entityHourlyHeartRateData.setEndTime(heartRateHourData.getEndHour());
                        entityHourlyHeartRateData.setMinHeartRate(heartRateHourData.minHeartRatePerHour);
                        entityHourlyHeartRateData.setMaxHeartRate(heartRateHourData.maxHeartRatePerHour);
                        entityHourlyHeartRateData.setAvgHeartRate(heartRateHourData.avgHeartRatePerHour);
                        entityHourlyHeartRateData.setCodedValues(heartRateHourData.getmMinuteWiseData());
                        arrayList.add(entityHourlyHeartRateData);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityHourlyRrData> getHourlyRrData(@NotNull RrDayData rrDayData) {
            Intrinsics.checkNotNullParameter(rrDayData, "rrDayData");
            ArrayList<EntityHourlyRrData> arrayList = new ArrayList<>();
            RrTimeLogBeanData rrTimeLogBeanData = rrDayData.timeLogBean;
            if (rrTimeLogBeanData != null && rrTimeLogBeanData.getLogs() != null) {
                List<RrHourData> logs = rrDayData.timeLogBean.getLogs();
                Intrinsics.checkNotNullExpressionValue(logs, "rrDayData.timeLogBean.logs");
                if (!logs.isEmpty()) {
                    for (RrHourData rrHourData : rrDayData.timeLogBean.getLogs()) {
                        EntityHourlyRrData entityHourlyRrData = new EntityHourlyRrData();
                        entityHourlyRrData.setDate(rrHourData.getDate());
                        entityHourlyRrData.serial_no = rrDayData.mMacAddress;
                        entityHourlyRrData.setStart_time(rrHourData.getStartHour());
                        entityHourlyRrData.setEnd_time(rrHourData.getEndHour());
                        entityHourlyRrData.avgRrValue = (int) rrHourData.mRrIntervalPerHour;
                        entityHourlyRrData.setCodedValues(rrHourData.getmMinuteWiseData());
                        arrayList.add(entityHourlyRrData);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final List<HourlyWalkData> getHourlySleepData(@NotNull StepsDayData stepsDayData) {
            Intrinsics.checkNotNullParameter(stepsDayData, "stepsDayData");
            ArrayList arrayList = new ArrayList();
            if (stepsDayData.getTimeLogBean() != null) {
                List<StepsHourData> logBeans = stepsDayData.getTimeLogBean().getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "stepsDayData.getTimeLogBean().logBeans");
                if (!logBeans.isEmpty()) {
                    for (StepsHourData stepsHourData : stepsDayData.getTimeLogBean().getLogBeans()) {
                        HourlyWalkData hourlyWalkData = new HourlyWalkData();
                        hourlyWalkData.mDate = stepsDayData.mDate;
                        hourlyWalkData.setStartTime(stepsHourData.getStartHour());
                        hourlyWalkData.setEndTime(stepsHourData.getEndHour());
                        hourlyWalkData.setMacAddress(stepsDayData.getmMacAddress());
                        hourlyWalkData.setIntervelValue(stepsHourData.mStepsPerHour);
                        hourlyWalkData.setCalories((int) stepsHourData.getCaloriesPerHour());
                        hourlyWalkData.setDistance((int) stepsHourData.getmistancePerHour());
                        hourlyWalkData.setCodevalue(stepsHourData.getmMinuteWiseData());
                        hourlyWalkData.setActiveTime(Integer.valueOf(stepsHourData.activeTime));
                        arrayList.add(hourlyWalkData);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<EntityHourlySpo2> getHourlySpo2Data(@NotNull Spo2DayData spo2DayData) {
            Intrinsics.checkNotNullParameter(spo2DayData, "spo2DayData");
            ArrayList<EntityHourlySpo2> arrayList = new ArrayList<>();
            Spo2TimeLogBeanData spo2TimeLogBeanData = spo2DayData.timeLogBean;
            if (spo2TimeLogBeanData != null && spo2TimeLogBeanData.getLogBeans() != null) {
                List<Spo2HourData> logBeans = spo2DayData.timeLogBean.getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "spo2DayData.timeLogBean.logBeans");
                if (!logBeans.isEmpty()) {
                    for (Spo2HourData spo2HourData : spo2DayData.timeLogBean.getLogBeans()) {
                        EntityHourlySpo2 entityHourlySpo2 = new EntityHourlySpo2();
                        entityHourlySpo2.mDate = spo2HourData.getDate();
                        entityHourlySpo2.mac_address = spo2DayData.mMacAddress;
                        entityHourlySpo2.setStartTime(spo2HourData.getStartHour());
                        entityHourlySpo2.setEndTime(spo2HourData.getEndHour());
                        entityHourlySpo2.spo2_low = spo2HourData.minSpo2PerHour;
                        entityHourlySpo2.spo2_high = spo2HourData.maxSpo2PerHour;
                        entityHourlySpo2.spo2_avg = spo2HourData.avgSpo2PerHour;
                        entityHourlySpo2.codevalue = spo2HourData.getmMinuteWiseData();
                        arrayList.add(entityHourlySpo2);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<HourlyStress> getHourlyStressData(@NotNull StressDayData stressDayData) {
            Intrinsics.checkNotNullParameter(stressDayData, "stressDayData");
            ArrayList<HourlyStress> arrayList = new ArrayList<>();
            StressTimeLogBeanData stressTimeLogBeanData = stressDayData.timeLogBean;
            if (stressTimeLogBeanData != null && stressTimeLogBeanData.getLogBeans() != null) {
                List<StressHourData> logBeans = stressDayData.timeLogBean.getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "stressDayData.timeLogBean.logBeans");
                if (!logBeans.isEmpty()) {
                    for (StressHourData stressHourData : stressDayData.timeLogBean.getLogBeans()) {
                        HourlyStress hourlyStress = new HourlyStress();
                        hourlyStress.mDate = stressHourData.getDate();
                        hourlyStress.setMacAddress(stressDayData.mMacAddress);
                        hourlyStress.setStartTime(stressHourData.getStartHour());
                        hourlyStress.setEndTime(stressHourData.getEndHour());
                        hourlyStress.stress_low = stressHourData.getMinStress();
                        hourlyStress.stress_high = stressHourData.getMaxStress();
                        hourlyStress.stress_avg = stressHourData.getAvgStress();
                        hourlyStress.setCodevalue(stressHourData.getmMinuteWiseData());
                        arrayList.add(hourlyStress);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<HourlyTemperature> getHourlyTemperatureData(@NotNull TemperatureDayData tempratureDayData) {
            Intrinsics.checkNotNullParameter(tempratureDayData, "tempratureDayData");
            ArrayList<HourlyTemperature> arrayList = new ArrayList<>();
            TemperatureTimeLogBeanData temperatureTimeLogBeanData = tempratureDayData.timeLogBean;
            if (temperatureTimeLogBeanData != null && temperatureTimeLogBeanData.getLogBeans() != null) {
                List<TemperatureHourData> logBeans = tempratureDayData.timeLogBean.getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "tempratureDayData.timeLogBean.logBeans");
                if (!logBeans.isEmpty()) {
                    for (TemperatureHourData temperatureHourData : tempratureDayData.timeLogBean.getLogBeans()) {
                        HourlyTemperature hourlyTemperature = new HourlyTemperature();
                        hourlyTemperature.mDate = temperatureHourData.getDate();
                        hourlyTemperature.setMacAddress(tempratureDayData.mMacAddress);
                        hourlyTemperature.setStartTime(temperatureHourData.getStartHour());
                        hourlyTemperature.setEndTime(temperatureHourData.getEndHour());
                        hourlyTemperature.temperature_low = temperatureHourData.minTemperaturePerHour;
                        hourlyTemperature.temperature_high = temperatureHourData.maxTemperaturePerHour;
                        hourlyTemperature.temperature_avg = temperatureHourData.avgTemperaturePerHour;
                        hourlyTemperature.setCodevalue(temperatureHourData.getmMinuteWiseData());
                        arrayList.add(hourlyTemperature);
                    }
                }
            }
            return arrayList;
        }

        @NotNull
        public final ArrayList<HourlySleepData> getHourlySleepData(@NotNull SleepDayData sleepDayData) {
            Intrinsics.checkNotNullParameter(sleepDayData, "sleepDayData");
            ArrayList<HourlySleepData> arrayList = new ArrayList<>();
            if (sleepDayData.getTimeLogBean() != null) {
                List<SleepHourData> logBeans = sleepDayData.getTimeLogBean().getLogBeans();
                Intrinsics.checkNotNullExpressionValue(logBeans, "sleepDayData.getTimeLogBean().logBeans");
                if (!logBeans.isEmpty()) {
                    for (SleepHourData sleepHourData : sleepDayData.getTimeLogBean().getLogBeans()) {
                        HourlySleepData hourlySleepData = new HourlySleepData();
                        hourlySleepData.setDate(sleepHourData.getDate());
                        hourlySleepData.setMacAddress(sleepDayData.getmMacAddress());
                        hourlySleepData.setStartTime(sleepHourData.getStartHour());
                        hourlySleepData.setEndTime(sleepHourData.getEndHour());
                        hourlySleepData.setIntervalValue(sleepHourData.getTotalSleepPerHour());
                        hourlySleepData.setLigthSleep(sleepHourData.getLightSleepPerHour());
                        hourlySleepData.setAwake(sleepHourData.getAwakePerHour());
                        hourlySleepData.setDeepSleep(sleepHourData.getDeepSleepPerHour());
                        hourlySleepData.setCodevalue(sleepHourData.mMinuteWiseData);
                        arrayList.add(hourlySleepData);
                    }
                }
            }
            return arrayList;
        }
    }
}
