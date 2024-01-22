package com.coveiot.repository.datasync;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import androidx.exifinterface.media.ExifInterface;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.AccelerometerData;
import com.coveiot.android.bleabstract.models.CalorieHourlyData;
import com.coveiot.android.bleabstract.models.DailyCalorieData;
import com.coveiot.android.bleabstract.models.DailyDistanceData;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.DistanceHourlyData;
import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.coveiot.android.bleabstract.models.ManualHRVAndStressReading;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.ProgressType;
import com.coveiot.android.bleabstract.models.RawAccelerometerHistoryData;
import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.android.bleabstract.models.SedentaryData;
import com.coveiot.android.bleabstract.models.SensAISummaryData;
import com.coveiot.android.bleabstract.request.AmbientSoundDataRequest;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.BpDataRequest;
import com.coveiot.android.bleabstract.request.EcgDataRequest;
import com.coveiot.android.bleabstract.request.GetCalorieDataRequest;
import com.coveiot.android.bleabstract.request.GetDistanceDataRequest;
import com.coveiot.android.bleabstract.request.GetSensAISummaryDetailsRequest;
import com.coveiot.android.bleabstract.request.GetSensAISummaryRequest;
import com.coveiot.android.bleabstract.request.HeartRateDataRequest;
import com.coveiot.android.bleabstract.request.PeriodicSPO2BleRequest;
import com.coveiot.android.bleabstract.request.ReadManualBpRequest;
import com.coveiot.android.bleabstract.request.ReadManualHRVRequest;
import com.coveiot.android.bleabstract.request.ReadManualSpo2Request;
import com.coveiot.android.bleabstract.request.ReadManualStressRequest;
import com.coveiot.android.bleabstract.request.ReadRawAccelerometerDataRequest;
import com.coveiot.android.bleabstract.request.ReadRawPPGHistoryDataRequest;
import com.coveiot.android.bleabstract.request.ReadSedentaryDataRequest;
import com.coveiot.android.bleabstract.request.RrHistoryRequest;
import com.coveiot.android.bleabstract.request.SleepDataRequest;
import com.coveiot.android.bleabstract.request.StepsDataRequest;
import com.coveiot.android.bleabstract.request.StressDataRequest;
import com.coveiot.android.bleabstract.request.TemperatureDataRequest;
import com.coveiot.android.bleabstract.response.AmbientSoundDayData;
import com.coveiot.android.bleabstract.response.AmbientSoundResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.BpDayData;
import com.coveiot.android.bleabstract.response.BpResponse;
import com.coveiot.android.bleabstract.response.ECGResultResponse;
import com.coveiot.android.bleabstract.response.GetCalorieDataResponse;
import com.coveiot.android.bleabstract.response.GetDistanceDataResponse;
import com.coveiot.android.bleabstract.response.GetSensAISummaryDataResponse;
import com.coveiot.android.bleabstract.response.GetSensAISummaryDetailsResponse;
import com.coveiot.android.bleabstract.response.HRVDayData;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.ReadManualBpResponse;
import com.coveiot.android.bleabstract.response.ReadManualHRVAndStressResponse;
import com.coveiot.android.bleabstract.response.ReadManualSpo2Response;
import com.coveiot.android.bleabstract.response.ReadRawAccelerometerDataResponse;
import com.coveiot.android.bleabstract.response.ReadRawPPGDataResponse;
import com.coveiot.android.bleabstract.response.ReadSedentaryResponse;
import com.coveiot.android.bleabstract.response.RrDayData;
import com.coveiot.android.bleabstract.response.RrResponse;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.response.Spo2DayData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.TemperatureDayData;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.utils.jstyleUtils.JStyleUtils;
import com.coveiot.android.bleformator.BleFormator;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.activitysession.TraqConfigApi;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.BaseUnits;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.CricketBatting;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.CricketBowling;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.FitnessDataAggregate;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.OtherParams;
import com.coveiot.coveaccess.dailyfitnessdata.aggregatedata.PostDailyDataAggregate;
import com.coveiot.coveaccess.dailyfitnessdata.fitnessdata.SSaveFitnessDataRes;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sedentaryalerts.SaveSedentaryAlertsDataRes;
import com.coveiot.coveaccess.sedentaryalerts.SedentaryAlertsApiManager;
import com.coveiot.coveaccess.sedentaryalerts.model.SSaveSedentaryAlertsDataReq;
import com.coveiot.coveaccess.sedentaryalerts.model.SedentaryAlertsDataBean;
import com.coveiot.covedb.accelerometer.entities.EntityRawAccelerometerData;
import com.coveiot.covedb.ambientsound.EntityDailyAmbientSoundData;
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.deviceinfo.DeviceInfoRepository;
import com.coveiot.covedb.deviceinfo.EntityDeviceInfo;
import com.coveiot.covedb.ecg.EntityECGSummaryData;
import com.coveiot.covedb.heartrate.EntityDailyHeartRateData;
import com.coveiot.covedb.heartrate.EntityHourlyHeartRateData;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.ppg.entities.EntityRawPPGData;
import com.coveiot.covedb.profile.entities.EntityProfile;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummary;
import com.coveiot.covedb.sensai.entity.SensAIActivitySummaryDetails;
import com.coveiot.covedb.sleep.DailySleepData;
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.khjstyledb.heartrate.KHJstyleHRRepository;
import com.coveiot.khjstyledb.heartrate.model.SessionHR;
import com.coveiot.repository.ActivityType;
import com.coveiot.repository.Error;
import com.coveiot.repository.FileUtils;
import com.coveiot.repository.RepositoryModuleNames;
import com.coveiot.repository.RepositoryUtils;
import com.coveiot.repository.accelerometer.datasource.db.RawAccelerometerDBRepo;
import com.coveiot.repository.ambientsound.datasources.db.read.AmbientSoundDBRead;
import com.coveiot.repository.ambientsound.datasources.db.write.AmbientSoundDBWrite;
import com.coveiot.repository.bp.datasources.db.write.BpDBWrite;
import com.coveiot.repository.datasync.BleSyncUtils;
import com.coveiot.repository.datasync.domainlogic.APIResponseListner;
import com.coveiot.repository.datasync.domainlogic.SyncCompleteListner;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.ecg.db.write.ECGDBWrite;
import com.coveiot.repository.heartrate.datasources.db.read.HeartRateDBRead;
import com.coveiot.repository.heartrate.datasources.db.write.HeartRateDBWrite;
import com.coveiot.repository.hrv.datasource.db.write.HRVDBWrite;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.periodicspo2.PeriodicSpo2DBWrite;
import com.coveiot.repository.ppg.datasource.db.RawPPGDBRepo;
import com.coveiot.repository.profile.ProfileRepository;
import com.coveiot.repository.profile.read.ProfileDBRead;
import com.coveiot.repository.rr.datasources.db.write.RrDataWrite;
import com.coveiot.repository.sedentary.datasource.SedentarySettings;
import com.coveiot.repository.sedentary.datasource.db.SedentaryDBRepo;
import com.coveiot.repository.sensai.datasource.db.read.SensAIBeamDBRead;
import com.coveiot.repository.sensai.datasource.db.write.SensAIBeamDBWrite;
import com.coveiot.repository.sleep.datasources.db.read.SleepDBRead;
import com.coveiot.repository.sleep.datasources.db.write.SleepDBWrite;
import com.coveiot.repository.stress.datasources.db.read.StressDBRead;
import com.coveiot.repository.stress.datasources.db.write.StressDBWrite;
import com.coveiot.repository.temperature.datasources.db.write.TemperatureDBWrite;
import com.coveiot.repository.walk.datasources.db.read.WalkDBRead;
import com.coveiot.repository.walk.datasources.db.write.WalkDBWrite;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.FileUtil;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class BleSyncUtils {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static BleSyncUtils k;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f7321a;
    @NotNull
    public List<Integer> b;
    public String baseDir;
    public int c;
    public Context context;
    @Nullable
    public final String d;
    @NotNull
    public final Handler e;
    public int f;
    public int g;
    public int h;
    public int i;
    public String imagePath;
    @NotNull
    public ArrayList<String> j;
    public String serial_no;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void clearInstance() {
            BleSyncUtils.k = null;
        }

        @Nullable
        public final BleSyncUtils getInstance(@NotNull Context context, @NotNull String serial_no) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(serial_no, "serial_no");
            if (BleSyncUtils.k == null) {
                BleSyncUtils.k = new BleSyncUtils(context, serial_no, null);
            }
            return BleSyncUtils.k;
        }
    }

    /* loaded from: classes9.dex */
    public interface a {
        void onDataFetchFailure(@NotNull Error error);

        void onDataFetchSucess();

        void onProgressUpdate(@NotNull ProgressDataBean progressDataBean);
    }

    public BleSyncUtils(Context context, String str) {
        this.f7321a = "yyyy MM dd hh:mm:ss a";
        this.b = new ArrayList();
        this.d = "BleSyncUtils";
        this.e = new Handler(Looper.getMainLooper());
        this.j = new ArrayList<>();
        setContext(context);
        setSerial_no(str);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int i = applicationInfo.labelRes;
        if (i == 0) {
            applicationInfo.nonLocalizedLabel.toString();
        } else {
            Intrinsics.checkNotNullExpressionValue(context.getString(i), "context.getString(stringId)");
        }
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNull(filesDir);
        String absolutePath = filesDir.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir!!.absolutePath");
        setBaseDir(absolutePath);
        setImagePath(getBaseDir() + "/image/");
    }

    public /* synthetic */ BleSyncUtils(Context context, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str);
    }

    public static final void access$processECGResponse(BleSyncUtils bleSyncUtils, BleBaseResponse bleBaseResponse, LinkedHashMap linkedHashMap, Bitmap bitmap, Bitmap bitmap2, SyncResultListner syncResultListner, BleApi bleApi) {
        bleSyncUtils.getClass();
        if (bleBaseResponse.getResponseData() instanceof ECGResultResponse) {
            ECGResultResponse eCGResultResponse = (ECGResultResponse) bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(eCGResultResponse);
            List<Integer> queueEcg = eCGResultResponse.getQueueEcg();
            bleSyncUtils.b = queueEcg;
            bleSyncUtils.c = queueEcg.size();
            bleSyncUtils.a(linkedHashMap, bitmap, bitmap2, eCGResultResponse, syncResultListner, bleApi);
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashSet, T] */
    public static final void access$pushSedentaryAlertsDataToServer(final BleSyncUtils bleSyncUtils, SedentarySettings sedentarySettings) {
        bleSyncUtils.getClass();
        SedentaryDBRepo companion = SedentaryDBRepo.Companion.getInstance(bleSyncUtils.getContext());
        Intrinsics.checkNotNull(companion);
        List<EntitySedentary> unSyncedSedentaryAlertsList = companion.getUnSyncedSedentaryAlertsList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new HashSet();
        if (AppUtils.isEmpty(unSyncedSedentaryAlertsList)) {
            return;
        }
        Intrinsics.checkNotNull(unSyncedSedentaryAlertsList);
        for (EntitySedentary entitySedentary : unSyncedSedentaryAlertsList) {
            ((HashSet) objectRef.element).add(entitySedentary.getDate());
        }
        SSaveSedentaryAlertsDataReq sSaveSedentaryAlertsDataReq = new SSaveSedentaryAlertsDataReq();
        ArrayList arrayList = new ArrayList();
        Iterator it = ((HashSet) objectRef.element).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            ArrayList arrayList2 = new ArrayList();
            for (EntitySedentary entitySedentary2 : unSyncedSedentaryAlertsList) {
                if (RepositoryUtils.getDateFromTimestamp(entitySedentary2.getTimestamp()).equals(str)) {
                    arrayList2.add(RepositoryUtils.getTimeFromTimestamp(entitySedentary2.getTimestamp()));
                }
            }
            SedentaryAlertsDataBean sedentaryAlertsDataBean = new SedentaryAlertsDataBean();
            sedentaryAlertsDataBean.setType("SED_ALERTS");
            sedentaryAlertsDataBean.setDate(str);
            sedentaryAlertsDataBean.setTzOffset(RepositoryUtils.getCurrentTimezoneOffset());
            sedentaryAlertsDataBean.setValue(arrayList2.size());
            sedentaryAlertsDataBean.setBaseUnit("UNITLESS");
            SedentaryAlertsDataBean.TimeLog timeLog = new SedentaryAlertsDataBean.TimeLog();
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                SedentaryAlertsDataBean.TimeLog.Log log = new SedentaryAlertsDataBean.TimeLog.Log();
                log.setTime((String) it2.next());
                arrayList3.add(log);
            }
            timeLog.setLogs(arrayList3);
            sedentaryAlertsDataBean.setTimeLog(timeLog);
            if (sedentarySettings != null) {
                SedentaryAlertsDataBean.SedentaryAlertSettings sedentaryAlertSettings = new SedentaryAlertsDataBean.SedentaryAlertSettings();
                sedentaryAlertSettings.setStartTime(sedentarySettings.getStartTime());
                sedentaryAlertSettings.setEndTime(sedentarySettings.getEndTime());
                sedentaryAlertSettings.setActive(sedentarySettings.isActive());
                sedentaryAlertSettings.setInterval(sedentarySettings.getInterval());
                if (sedentarySettings.getSiesta() != null) {
                    SedentaryAlertsDataBean.SedentaryAlertSettingsSiesta sedentaryAlertSettingsSiesta = new SedentaryAlertsDataBean.SedentaryAlertSettingsSiesta();
                    sedentaryAlertSettingsSiesta.setActive(Boolean.valueOf(sedentarySettings.getSiesta().isActive()));
                    sedentaryAlertSettings.setSiesta(sedentaryAlertSettingsSiesta);
                }
                sedentaryAlertsDataBean.setSedentaryAlertSettings(sedentaryAlertSettings);
            }
            arrayList.add(sedentaryAlertsDataBean);
        }
        sSaveSedentaryAlertsDataReq.setFitnessData(arrayList);
        SedentaryAlertsApiManager.saveSedentaryAlertsData(sSaveSedentaryAlertsDataReq, new CoveApiListener<SaveSedentaryAlertsDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.BleSyncUtils$pushSedentaryAlertsDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SaveSedentaryAlertsDataRes saveSedentaryAlertsDataRes) {
                Iterator<String> it3 = objectRef.element.iterator();
                while (it3.hasNext()) {
                    String date = it3.next();
                    if (!RepositoryUtils.isDateToday(date)) {
                        SedentaryDBRepo companion2 = SedentaryDBRepo.Companion.getInstance(bleSyncUtils.getContext());
                        Intrinsics.checkNotNull(companion2);
                        Intrinsics.checkNotNullExpressionValue(date, "date");
                        companion2.updateSyncState(date);
                    }
                }
            }
        });
    }

    public static final void access$syncFailure(BleSyncUtils bleSyncUtils, SyncResultListner syncResultListner, Error error) {
        bleSyncUtils.f = 0;
        bleSyncUtils.g = 0;
        bleSyncUtils.h = 0;
        bleSyncUtils.i = 0;
        bleSyncUtils.j.clear();
        syncResultListner.onFailure(error);
    }

    public static final void access$updateProgress(BleSyncUtils bleSyncUtils, SyncResultListner syncResultListner, ProgressDataBean progressDataBean) {
        bleSyncUtils.getClass();
        bleSyncUtils.a(progressDataBean.getActivityType(), syncResultListner, false);
    }

    public final void a(final Date date, final Date date2, final Date date3, final Date date4, final Date date5, final Date date6, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        a(new TemperatureDataRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getTemperatureDataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.TEMPERATURE, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess Temperature Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    BleSyncUtils.this.a(date3, date4, date5, date6, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date, date2, date5, date6, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date5, date6);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date, date2, date5, date6, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date5, date6, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void b(final BleApi bleApi, final Date date, final Date date2, final Date date3, final Date date4, final SyncResultListner syncResultListner, final SyncCompleteListner syncCompleteListner) {
        if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
            a(new StressDataRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncStressData$1
                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchFailure(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchSucess() {
                    String str;
                    BleSyncUtils.this.a(ActivityType.STRESS, syncResultListner, true);
                    str = BleSyncUtils.this.d;
                    LogHelper.d(str, "onDataFetchSuccess Stress Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                    if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                        BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                        BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                        BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date3, date4);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                        BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                        BleSyncUtils.this.a(bleApi, date, date2, date3, date4, syncResultListner, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.syncDistanceData(date3, date4, syncResultListner, bleApi, syncCompleteListner);
                    } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.b(syncResultListner);
                    } else {
                        BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                    }
                    if (RepositoryUtils.isIDODevice(BleSyncUtils.this.getContext()) && BleApiManager.getInstance(BleSyncUtils.this.getContext()).getDeviceType() == DeviceType.IDO_SELECT) {
                        BleSyncUtils.this.a();
                    }
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                    Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                    BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
                }
            }, bleApi);
        } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
            a(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
            syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date3, date4);
        } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
            syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
            a(bleApi, date, date2, date3, date4, syncResultListner, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
            syncDistanceData(date3, date4, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
            syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
        } else {
            b(syncResultListner);
        }
    }

    @NotNull
    public final ArrayList<String> getActivityTypeList() {
        return this.j;
    }

    @NotNull
    public final String getBaseDir() {
        String str = this.baseDir;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("baseDir");
        return null;
    }

    @NotNull
    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    @NotNull
    public final String getDATE_FORMAT() {
        return this.f7321a;
    }

    public final void getECGDataFromDevice(@NotNull final LinkedHashMap<String, String> userInfoMap, @NotNull final Bitmap headerLogo, @NotNull final Bitmap footerLogo, @NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi) {
        Intrinsics.checkNotNullParameter(userInfoMap, "userInfoMap");
        Intrinsics.checkNotNullParameter(headerLogo, "headerLogo");
        Intrinsics.checkNotNullParameter(footerLogo, "footerLogo");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        bleApi.getData(new EcgDataRequest(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getECGDataFromDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                syncResultListner.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                BleSyncUtils.access$processECGResponse(BleSyncUtils.this, response, userInfoMap, headerLogo, footerLogo, syncResultListner, bleApi);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final ArrayList<Integer> getEmptySleepHourCodedValuesList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            arrayList.add(-1);
        }
        return arrayList;
    }

    @NotNull
    public final Handler getHandler() {
        return this.e;
    }

    @NotNull
    public final String getImagePath() {
        String str = this.imagePath;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imagePath");
        return null;
    }

    public final int getPerStagePercentValue() {
        return this.i;
    }

    public final int getProgressPercent() {
        return this.f;
    }

    @NotNull
    public final String getSerial_no() {
        String str = this.serial_no;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("serial_no");
        return null;
    }

    @NotNull
    public final List<SleepResponse> getSleepNoDataList(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        ArrayList arrayList = new ArrayList();
        SleepDayData sleepDayData = new SleepDayData();
        sleepDayData.setmDeepSleep(0);
        sleepDayData.setmLightSleep(0);
        sleepDayData.setmTotalSleep(0);
        sleepDayData.setmAwakeTime(0);
        sleepDayData.setmUnSleep(0);
        sleepDayData.setmActivityType(ActivityType.SLEEP.toString());
        sleepDayData.setmDate(date);
        sleepDayData.setmMacAddress(str);
        SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 24; i++) {
            SleepHourData sleepHourData = new SleepHourData();
            sleepHourData.setTotalSleepPerHour(0);
            sleepHourData.setDeepSleepPerHour(0);
            sleepHourData.setLightSleepPerHour(0);
            sleepHourData.setAwakePerHour(0);
            sleepHourData.setActivityType(ActivityType.SLEEP.toString());
            sleepHourData.setDate(date);
            StringBuilder sb = new StringBuilder();
            Locale locale = Locale.ENGLISH;
            String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
            sb.append(format);
            sb.append(":00:00");
            sleepHourData.setStartHour(sb.toString());
            if (i == 23) {
                sleepHourData.setEndHour("00:00:00");
            } else {
                StringBuilder sb2 = new StringBuilder();
                String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1)}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                sb2.append(format2);
                sb2.append(":00:00");
                sleepHourData.setEndHour(sb2.toString());
            }
            sleepHourData.setMacAddress(str);
            sleepHourData.setMinuteWiseData(getEmptySleepHourCodedValuesList());
            arrayList2.add(sleepHourData);
        }
        SleepResponse sleepResponse = new SleepResponse();
        sleepTimeLogBeanData.setLogBeans(arrayList2);
        sleepDayData.setTimeLogBean(sleepTimeLogBeanData);
        sleepResponse.setSleepDayData(sleepDayData);
        arrayList.add(sleepResponse);
        return arrayList;
    }

    public final int getStageProgress() {
        return this.h;
    }

    public final int getTotalDataCount() {
        return this.c;
    }

    public final int getTotalStage() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object] */
    public final void postDailyDataAggregateData() {
        SensAIBeamDBRead sensAIBeamDBRead = SensAIBeamDBRead.getInstance(getContext());
        Intrinsics.checkNotNull(sensAIBeamDBRead);
        List<SensAIActivitySummary> aggregateSummaryList = sensAIBeamDBRead.getAggregateSummaryList(getSerial_no());
        HashMap hashMap = new HashMap();
        new ArrayList();
        if (aggregateSummaryList != null) {
            for (SensAIActivitySummary sensAIActivitySummary : aggregateSummaryList) {
                SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("yyyy-MM-dd");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Long timestamp = sensAIActivitySummary.getTimestamp();
                Intrinsics.checkNotNullExpressionValue(timestamp, "item.timestamp");
                String format = simpleDateFormat.format(new Date(timestamp.longValue()));
                ?? r4 = (List) hashMap.get(format);
                if (r4 != 0) {
                    r4.add(sensAIActivitySummary);
                } else {
                    r4 = new ArrayList();
                    r4.add(sensAIActivitySummary);
                }
                List list = (List) hashMap.put(format, r4);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            pushDailyDataAggregate((List) entry.getValue(), (String) entry.getKey());
        }
    }

    public final void pushDailyDataAggregate(@NotNull final List<SensAIActivitySummary> activitySummaryList, @NotNull String date) {
        final BleSyncUtils bleSyncUtils;
        Intrinsics.checkNotNullParameter(activitySummaryList, "activitySummaryList");
        Intrinsics.checkNotNullParameter(date, "date");
        PostDailyDataAggregate postDailyDataAggregate = new PostDailyDataAggregate();
        ArrayList arrayList = new ArrayList();
        FitnessDataAggregate fitnessDataAggregate = new FitnessDataAggregate();
        fitnessDataAggregate.setUserId(PreferenceManager.getInstance().getUserId());
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
        fitnessDataAggregate.setUserDeviceId(Integer.valueOf(Integer.parseInt(userDeviceID)));
        fitnessDataAggregate.setDate(date);
        fitnessDataAggregate.setTzOffset(RepositoryUtils.getCurrentTimezoneOffset());
        Iterator<SensAIActivitySummary> it = activitySummaryList.iterator();
        long j = 0;
        float f = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        long j2 = 0;
        while (it.hasNext()) {
            SensAIActivitySummary next = it.next();
            i6 += next.getTotalSteps();
            i2 += kotlin.math.c.roundToInt(next.getTotalCalories());
            Iterator<SensAIActivitySummary> it2 = it;
            i5 += (int) next.getDurationSec();
            if (next.getActivityType() == 1) {
                i++;
                j += next.getDurationSec();
                next.getTotalCalories();
                next.getTotalSteps();
                f4 += next.getMaxHandSpeed();
                f3 += next.getAvgHandSpeed();
                i7 += next.getTotalSwings();
                i4 += next.getHitPct();
                i10 += next.getPlayed();
                if (next.getGoalCompletionPct() >= 100) {
                    i8++;
                }
            } else {
                i3++;
                j2 += next.getDurationSec();
                next.getTotalCalories();
                next.getTotalSteps();
                f2 += next.getMaxArmSpeed();
                f += next.getAvgArmSpeed();
                i9 += next.getTotalBallsBowled();
                if (next.getGoalCompletionPct() >= 100) {
                    i11++;
                }
            }
            it = it2;
        }
        fitnessDataAggregate.setTotalSteps(Integer.valueOf(i6));
        fitnessDataAggregate.setTotalCalories(Float.valueOf(i2));
        fitnessDataAggregate.setTotalActiveTime(Integer.valueOf(i5));
        CricketBatting cricketBatting = new CricketBatting();
        cricketBatting.setTotalDuration(Integer.valueOf((int) j));
        cricketBatting.setTotalTargetAchieved(Integer.valueOf(i8));
        cricketBatting.setTotalSwings(Integer.valueOf(i7));
        cricketBatting.setTotalSessions(Integer.valueOf(i));
        try {
            i4 /= i;
            bleSyncUtils = this;
        } catch (ArithmeticException e) {
            bleSyncUtils = this;
            LogHelper.d(bleSyncUtils.d, e.toString());
        }
        if (i != 0) {
            float f5 = i;
            cricketBatting.setMaxHandSpeed(Float.valueOf(f4 / f5));
            cricketBatting.setAvgHandSpeed(Float.valueOf(f3 / f5));
        } else {
            cricketBatting.setMaxHandSpeed(valueOf);
            cricketBatting.setAvgHandSpeed(valueOf);
        }
        cricketBatting.setHitPercentage(Float.valueOf(i4));
        cricketBatting.setTotalHits(Integer.valueOf(i10));
        fitnessDataAggregate.setCricketBatting(cricketBatting);
        CricketBowling cricketBowling = new CricketBowling();
        if (i3 != 0) {
            float f6 = i3;
            cricketBowling.setMaxHandSpeed(Float.valueOf(f2 / f6));
            cricketBowling.setAvgHandSpeed(Float.valueOf(f / f6));
        } else {
            cricketBowling.setMaxHandSpeed(valueOf);
            cricketBowling.setAvgHandSpeed(valueOf);
        }
        cricketBowling.setTotalDuration(Integer.valueOf((int) j2));
        cricketBowling.setTotalTargetAchieved(Integer.valueOf(i11));
        cricketBowling.setTotalBallsBowled(Integer.valueOf(i9));
        cricketBowling.setTotalSessions(Integer.valueOf(i3));
        fitnessDataAggregate.setCricketBowling(cricketBowling);
        EntityProfile latestProfileData = ProfileDBRead.getInstance(getContext()).getLatestProfileData();
        if (latestProfileData == null) {
            latestProfileData = new EntityProfile();
        }
        OtherParams otherParams = new OtherParams();
        if (latestProfileData.getAge() == 0) {
            otherParams.setUserAge(50);
        } else {
            otherParams.setUserAge(Integer.valueOf(latestProfileData.getAge()));
        }
        if (latestProfileData.getGender() == 0) {
            otherParams.setUserGender("MALE");
        } else {
            otherParams.setUserGender("FEMALE");
        }
        otherParams.setUserHeight(Integer.valueOf(latestProfileData.getHeight()));
        otherParams.setUserWeight(Double.valueOf(latestProfileData.getWeight()));
        int i12 = latestProfileData.walkStrideLength;
        if (i12 <= 0) {
            otherParams.setUserWalkingStrideLength(2);
        } else {
            otherParams.setUserWalkingStrideLength(Integer.valueOf(i12));
        }
        int i13 = latestProfileData.runStrideLength;
        if (i13 <= 0) {
            otherParams.setUserRunningStrideLength(2);
        } else {
            otherParams.setUserRunningStrideLength(Integer.valueOf(i13));
        }
        otherParams.setUserSwimmingStrokeLength(4);
        fitnessDataAggregate.setOtherParams(otherParams);
        BaseUnits baseUnits = new BaseUnits();
        baseUnits.setDistance("CENTIMETERS");
        baseUnits.setActiveTime("SECONDS");
        baseUnits.setUserWeight("KILOGRAMS");
        baseUnits.setUserHeight("CENTIMETERS");
        baseUnits.setUserAge("YEARS");
        baseUnits.setStrideLength("CENTIMETERS");
        baseUnits.setStrokeLength("CENTIMETERS");
        baseUnits.setHrZoneDuration("SECONDS");
        baseUnits.setHandSpeed("KM_PER_HOUR");
        fitnessDataAggregate.setBaseUnits(baseUnits);
        arrayList.add(fitnessDataAggregate);
        postDailyDataAggregate.setFitnessDataAggregates(arrayList);
        TraqConfigApi.postFitnessDataAggregate(postDailyDataAggregate, new CoveApiListener<SSaveFitnessDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.datasync.BleSyncUtils$pushDailyDataAggregate$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SSaveFitnessDataRes object) {
                String a2;
                Intrinsics.checkNotNullParameter(object, "object");
                List<SensAIActivitySummary> list = activitySummaryList;
                if (list != null) {
                    for (SensAIActivitySummary sensAIActivitySummary : list) {
                        BleSyncUtils bleSyncUtils2 = bleSyncUtils;
                        Long timestamp = sensAIActivitySummary.getTimestamp();
                        Intrinsics.checkNotNullExpressionValue(timestamp, "summaryData.timestamp");
                        a2 = bleSyncUtils2.a(timestamp.longValue());
                        if (!RepositoryUtils.isDateToday(a2)) {
                            SensAIBeamDBWrite sensAIBeamDBWrite = SensAIBeamDBWrite.getInstance(bleSyncUtils.getContext());
                            Intrinsics.checkNotNull(sensAIBeamDBWrite);
                            sensAIBeamDBWrite.updateAggregateSummaryData(true, bleSyncUtils.getSerial_no(), sensAIActivitySummary.getSessionId());
                        }
                    }
                }
            }
        });
    }

    public final void setActivityTypeList(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.j = arrayList;
    }

    public final void setBaseDir(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.baseDir = str;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    public final void setDATE_FORMAT(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f7321a = str;
    }

    public final void setImagePath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.imagePath = str;
    }

    public final void setPerStagePercentValue(int i) {
        this.i = i;
    }

    public final void setProgressPercent(int i) {
        this.f = i;
    }

    public final void setSerial_no(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serial_no = str;
    }

    public final void setStageProgress(int i) {
        this.h = i;
    }

    public final void setTotalDataCount(int i) {
        this.c = i;
    }

    public final void setTotalStage(int i) {
        this.g = i;
    }

    public final void syncCalorieData(@NotNull Date distanceStartDate, @NotNull Date distanceEndDate, @NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi, @NotNull final SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(distanceStartDate, "distanceStartDate");
        Intrinsics.checkNotNullParameter(distanceEndDate, "distanceEndDate");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfCalorieDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfCalorieDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateCalorieData()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateCalorieData()) <= maxDaysOfCalorieDataOnBand) {
            maxDaysOfCalorieDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateCalorieData());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfCalorieDataOnBand);
        calendar.getTime();
        Calendar.getInstance().getTime();
        a(new GetCalorieDataRequest.Builder().setStartDate(distanceStartDate).setEndDate(distanceEndDate).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncCalorieData$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.CALORIE, syncResultListner, true);
                if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess Calorie Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [boolean, int] */
    public final void syncData(@NotNull Date startDate, @NotNull Date endDate, @NotNull final Date sleepStartDate, @NotNull final Date sleepEndDate, @NotNull final Date heartRateStartDate, @NotNull final Date heartRateEndDate, @NotNull final Date bpStartDate, @NotNull final Date bpEndDate, @NotNull final Date rrStartDate, @NotNull final Date rrEndDate, @NotNull final Date temperatureStartDate, @NotNull final Date temperatureEndDate, @NotNull final Date spo2StartDate, @NotNull final Date spo2EndDate, @NotNull final Date distanceStartDate, @NotNull final Date distanceEndDate, @NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi, final boolean z, @NotNull final SyncCompleteListner syncCompleteListner) {
        BleSyncUtils bleSyncUtils;
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(sleepStartDate, "sleepStartDate");
        Intrinsics.checkNotNullParameter(sleepEndDate, "sleepEndDate");
        Intrinsics.checkNotNullParameter(heartRateStartDate, "heartRateStartDate");
        Intrinsics.checkNotNullParameter(heartRateEndDate, "heartRateEndDate");
        Intrinsics.checkNotNullParameter(bpStartDate, "bpStartDate");
        Intrinsics.checkNotNullParameter(bpEndDate, "bpEndDate");
        Intrinsics.checkNotNullParameter(rrStartDate, "rrStartDate");
        Intrinsics.checkNotNullParameter(rrEndDate, "rrEndDate");
        Intrinsics.checkNotNullParameter(temperatureStartDate, "temperatureStartDate");
        Intrinsics.checkNotNullParameter(temperatureEndDate, "temperatureEndDate");
        Intrinsics.checkNotNullParameter(spo2StartDate, "spo2StartDate");
        Intrinsics.checkNotNullParameter(spo2EndDate, "spo2EndDate");
        Intrinsics.checkNotNullParameter(distanceStartDate, "distanceStartDate");
        Intrinsics.checkNotNullParameter(distanceEndDate, "distanceEndDate");
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        StepsDataRequest.Builder startDate2 = new StepsDataRequest.Builder().setStartDate(startDate);
        EntityProfile latestProfileData = ProfileRepository.getInstance().getLatestProfileData(getContext());
        StepsDataRequest.Builder strideLength = startDate2.setStrideLength(latestProfileData != null ? latestProfileData.walkStrideLength : 68);
        EntityProfile latestProfileData2 = ProfileRepository.getInstance().getLatestProfileData(getContext());
        StepsDataRequest stepsDataRequest = strideLength.setRunStrideLength(latestProfileData2 != null ? latestProfileData2.runStrideLength : 88).setEndDate(endDate).build();
        ?? isHeartRateSupported = BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported();
        int i = isHeartRateSupported;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isSleepSupported()) {
            i = isHeartRateSupported + 1;
        }
        int i2 = i;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isStepsSupported()) {
            i2 = i + 1;
        }
        int i3 = i2;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isBpSupported()) {
            i3 = i2 + 1;
        }
        int i4 = i3;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isRrSupported()) {
            i4 = i3 + 1;
        }
        int i5 = i4;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            i5 = i4 + 1;
        }
        int i6 = i5;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isStressHistorySupported()) {
            i6 = i5 + 1;
        }
        int i7 = i6;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            i7 = i6 + 1;
        }
        int i8 = i7;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
            i8 = i7 + 1;
        }
        int i9 = i8;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
            i9 = i8 + 1;
        }
        int i10 = i9;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isSedentaryAlertHistorySupported()) {
            i10 = i9 + 1;
        }
        int i11 = i10;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isSportsModeHistorySupported()) {
            i11 = i10 + 1;
        }
        int i12 = i11;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isAgpsFileUploadSupported()) {
            i12 = i11 + 1;
        }
        int i13 = i12;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isSensAISupported()) {
            i13 = i12 + 1;
        }
        int i14 = i13;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
            i14 = i13 + 2;
        }
        this.g = i14;
        this.h = i14;
        this.i = 100 / i14;
        if (BleApiManager.getInstance(getContext()).getBleApi().getDeviceSupportedFeatures().isStepsSupported()) {
            Intrinsics.checkNotNullExpressionValue(stepsDataRequest, "stepsDataRequest");
            a(stepsDataRequest, new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncData$1
                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchFailure(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchSucess() {
                    String str;
                    String str2;
                    BleSyncUtils.this.a(ActivityType.WALK, syncResultListner, true);
                    str = BleSyncUtils.this.d;
                    LogHelper.d(str, "onDataFetchSucess Step Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                    if (bleApi.getDeviceSupportedFeatures().isSleepSupported()) {
                        if (z) {
                            BleSyncUtils.this.a(sleepStartDate, sleepEndDate, heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                            return;
                        }
                        str2 = BleSyncUtils.this.d;
                        LogHelper.d(str2, "Sleep data sync skipped by app at " + AppUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        if (bleApi.getDeviceSupportedFeatures().isHeartRateSupported()) {
                            BleSyncUtils.this.a(heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
                            BleSyncUtils.this.a(bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                            BleSyncUtils.this.a(rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                            BleSyncUtils.this.a(temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                            BleSyncUtils.this.a(spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                            BleSyncUtils.this.b(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                            BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                            BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                            BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, distanceStartDate, distanceEndDate);
                        } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                            BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                            BleSyncUtils.this.a(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                            BleSyncUtils.this.syncDistanceData(distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                        } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                            BleSyncUtils.this.b(syncResultListner);
                        } else {
                            BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                        }
                    } else if (bleApi.getDeviceSupportedFeatures().isHeartRateSupported()) {
                        BleSyncUtils.this.a(heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
                        BleSyncUtils.this.a(bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                        BleSyncUtils.this.a(rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                        BleSyncUtils.this.a(temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                        BleSyncUtils.this.a(spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                        BleSyncUtils.this.b(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                        BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                        BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                        BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, distanceStartDate, distanceEndDate);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                        BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                        BleSyncUtils.this.a(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.syncDistanceData(distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.b(syncResultListner);
                    } else {
                        BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                    }
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                    Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                    BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
                }
            }, bleApi);
            return;
        }
        LogHelper.d(this.d, "onDataFetchSucess Step Data success");
        if (bleApi.getDeviceSupportedFeatures().isSleepSupported()) {
            if (z) {
                a(sleepStartDate, sleepEndDate, heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                return;
            }
            String str = this.d;
            LogHelper.d(str, "Sleep data sync skipped by app at " + AppUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            if (bleApi.getDeviceSupportedFeatures().isHeartRateSupported()) {
                a(heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
            } else if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
                a(bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
            } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                a(rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
            } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                a(temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
            } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                a(spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
            } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                b(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
            } else {
                if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    bleSyncUtils = this;
                    bleSyncUtils.a(syncResultListner, bleApi, syncCompleteListner);
                } else {
                    bleSyncUtils = this;
                    if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                        bleSyncUtils.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                        syncManualStressData(syncResultListner, bleApi, syncCompleteListner, distanceStartDate, distanceEndDate);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                        bleSyncUtils.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                        a(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        syncDistanceData(distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        bleSyncUtils.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                    } else {
                        bleSyncUtils.b(syncResultListner);
                    }
                }
            }
        } else if (bleApi.getDeviceSupportedFeatures().isHeartRateSupported()) {
            a(heartRateStartDate, heartRateEndDate, bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
            a(bpStartDate, bpEndDate, rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
            a(rrStartDate, rrEndDate, temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
            a(temperatureStartDate, temperatureEndDate, spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
            a(spo2StartDate, spo2EndDate, distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
            b(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
            a(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
            syncManualStressData(syncResultListner, bleApi, syncCompleteListner, distanceStartDate, distanceEndDate);
        } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
            syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
            a(bleApi, temperatureStartDate, temperatureEndDate, distanceStartDate, distanceEndDate, syncResultListner, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
            syncDistanceData(distanceStartDate, distanceEndDate, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
            syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
        } else {
            b(syncResultListner);
        }
    }

    public final void syncDistanceData(@Nullable Date date, @Nullable Date date2, @NotNull final SyncResultListner syncResultListner, @NotNull final BleApi bleApi, @NotNull final SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfDistanceDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfDistanceDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateDistanceData()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateDistanceData()) <= maxDaysOfDistanceDataOnBand) {
            maxDaysOfDistanceDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateDistanceData());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfDistanceDataOnBand);
        if (date == null) {
            date = calendar.getTime();
        }
        final Date startDate = date;
        if (date2 == null) {
            date2 = Calendar.getInstance().getTime();
        }
        final Date endDate = date2;
        GetDistanceDataRequest.Builder builder = new GetDistanceDataRequest.Builder();
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        GetDistanceDataRequest.Builder startDate2 = builder.setStartDate(startDate);
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        a(startDate2.setEndDate(endDate).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncDistanceData$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.DISTANCE, syncResultListner, true);
                BleSyncUtils bleSyncUtils = BleSyncUtils.this;
                Date startDate3 = startDate;
                Intrinsics.checkNotNullExpressionValue(startDate3, "startDate");
                Date endDate2 = endDate;
                Intrinsics.checkNotNullExpressionValue(endDate2, "endDate");
                bleSyncUtils.syncCalorieData(startDate3, endDate2, syncResultListner, bleApi, syncCompleteListner);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess Distance Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void syncDynamicHeartRate(@NotNull SyncResultListner syncResultListner, @NotNull BleApi bleApi, @NotNull SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(syncResultListner, "syncResultListner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        a(syncResultListner, bleApi, syncCompleteListner);
    }

    public final void syncManualBpData(@NotNull final SyncResultListner listner, @NotNull BleApi bleApi) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        bleApi.getData(new ReadManualBpRequest(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncManualBpData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                listner.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof ReadManualBpResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadManualBpResponse");
                    ReadManualBpResponse readManualBpResponse = (ReadManualBpResponse) responseData;
                    if (!AppUtils.isEmpty(readManualBpResponse.getManualBpReadings())) {
                        String userDeviceId = PreferenceManager.getInstance().getUserDeviceID();
                        ManualDataDBWrite manualDataDBWrite = ManualDataDBWrite.getInstance(BleSyncUtils.this.getContext());
                        BleFormator.Companion companion = BleFormator.Companion;
                        ArrayList<ManualBpReading> manualBpReadings = readManualBpResponse.getManualBpReadings();
                        Intrinsics.checkNotNull(manualBpReadings);
                        String serial_no = BleSyncUtils.this.getSerial_no();
                        Intrinsics.checkNotNullExpressionValue(userDeviceId, "userDeviceId");
                        manualDataDBWrite.insetAll(companion.getEntityManualDataList(manualBpReadings, serial_no, userDeviceId));
                    } else {
                        LogHelper.d("MainActivity", "Manual BP is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    }
                    listner.onSuccess();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncManualHRVData(@NotNull final SyncResultListner listener, @NotNull final BleApi bleApi, @NotNull final SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        bleApi.getData(new ReadManualHRVRequest(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncManualHRVData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                listener.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                BleSyncUtils.this.a(ActivityType.HRV, listener, true);
                if (response.getResponseData() != null && (response.getResponseData() instanceof ReadManualHRVAndStressResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadManualHRVAndStressResponse");
                    ReadManualHRVAndStressResponse readManualHRVAndStressResponse = (ReadManualHRVAndStressResponse) responseData;
                    if (!AppUtils.isEmpty(readManualHRVAndStressResponse.getManualHRVStressReadings())) {
                        String userDeviceId = PreferenceManager.getInstance().getUserDeviceID();
                        ManualDataDBWrite manualDataDBWrite = ManualDataDBWrite.getInstance(BleSyncUtils.this.getContext());
                        BleFormator.Companion companion = BleFormator.Companion;
                        ArrayList<ManualHRVAndStressReading> manualHRVStressReadings = readManualHRVAndStressResponse.getManualHRVStressReadings();
                        Intrinsics.checkNotNull(manualHRVStressReadings);
                        String serial_no = BleSyncUtils.this.getSerial_no();
                        Intrinsics.checkNotNullExpressionValue(userDeviceId, "userDeviceId");
                        manualDataDBWrite.insetAll(companion.getEntityManualHRVAndStressDataList(manualHRVStressReadings, serial_no, userDeviceId));
                    } else {
                        LogHelper.d("BleSyncUtils", "Manual HRV is empty");
                    }
                    if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.syncDistanceData(null, null, listener, bleApi, syncCompleteListner);
                        return;
                    } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.b(listener);
                        return;
                    } else {
                        BleSyncUtils.this.syncSensAISummaryData(listener, bleApi, syncCompleteListner);
                        return;
                    }
                }
                LogHelper.d("BleSyncUtils", "Manual HRV is null");
                if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(null, null, listener, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(listener);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(listener, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncManualSpo2Data(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi, @NotNull final SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfSpo2DataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfSpo2DataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateSpo2()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSpo2()) <= maxDaysOfSpo2DataOnBand) {
            maxDaysOfSpo2DataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSpo2());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfSpo2DataOnBand);
        final Date startDateSpo2 = calendar.getTime();
        final Date endDateSpo2 = Calendar.getInstance().getTime();
        ReadManualSpo2Request.Builder builder = new ReadManualSpo2Request.Builder();
        Intrinsics.checkNotNullExpressionValue(startDateSpo2, "startDateSpo2");
        ReadManualSpo2Request.Builder startDate = builder.setStartDate(startDateSpo2);
        Intrinsics.checkNotNullExpressionValue(endDateSpo2, "endDateSpo2");
        bleApi.getData(startDate.setEndDate(endDateSpo2).build(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncManualSpo2Data$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                listner.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                BleSyncUtils bleSyncUtils = BleSyncUtils.this;
                ActivityType activityType = ActivityType.SPO2;
                bleSyncUtils.a(activityType, listner, true);
                if (response.getResponseData() == null) {
                    if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                        BleSyncUtils.this.syncManualStressData(listner, bleApi, syncCompleteListner, null, null);
                    } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                        BleSyncUtils.this.syncManualHRVData(listner, bleApi, syncCompleteListner);
                    } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.syncDistanceData(startDateSpo2, endDateSpo2, listner, bleApi, syncCompleteListner);
                    } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.b(listner);
                    } else {
                        BleSyncUtils.this.syncSensAISummaryData(listner, bleApi, syncCompleteListner);
                    }
                } else if (response.getResponseData() instanceof ReadManualSpo2Response) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadManualSpo2Response");
                    ReadManualSpo2Response readManualSpo2Response = (ReadManualSpo2Response) responseData;
                    if (AppUtils.isEmpty(readManualSpo2Response.getManualSpo2Readings())) {
                        str = BleSyncUtils.this.d;
                        LogHelper.d(str, "Manual Spo2 is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    } else {
                        String userDeviceId = PreferenceManager.getInstance().getUserDeviceID();
                        ManualDataDBWrite manualDataDBWrite = ManualDataDBWrite.getInstance(BleSyncUtils.this.getContext());
                        BleFormator.Companion companion = BleFormator.Companion;
                        ArrayList<ManualSpo2Reading> manualSpo2Readings = readManualSpo2Response.getManualSpo2Readings();
                        Intrinsics.checkNotNull(manualSpo2Readings);
                        String serial_no = BleSyncUtils.this.getSerial_no();
                        Intrinsics.checkNotNullExpressionValue(userDeviceId, "userDeviceId");
                        manualDataDBWrite.insetAll(companion.getEntityManualDataListSpo2(manualSpo2Readings, serial_no, userDeviceId));
                    }
                    if (readManualSpo2Response.isComplete()) {
                        if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                            BleSyncUtils.this.syncManualStressData(listner, bleApi, syncCompleteListner, null, null);
                        } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                            BleSyncUtils.this.syncManualHRVData(listner, bleApi, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                            BleSyncUtils bleSyncUtils2 = BleSyncUtils.this;
                            BleApi bleApi2 = bleApi;
                            Date startDateSpo22 = startDateSpo2;
                            Intrinsics.checkNotNullExpressionValue(startDateSpo22, "startDateSpo2");
                            Date endDateSpo22 = endDateSpo2;
                            Intrinsics.checkNotNullExpressionValue(endDateSpo22, "endDateSpo2");
                            Date startDateSpo23 = startDateSpo2;
                            Intrinsics.checkNotNullExpressionValue(startDateSpo23, "startDateSpo2");
                            Date endDateSpo23 = endDateSpo2;
                            Intrinsics.checkNotNullExpressionValue(endDateSpo23, "endDateSpo2");
                            bleSyncUtils2.a(bleApi2, startDateSpo22, endDateSpo22, startDateSpo23, endDateSpo23, listner, syncCompleteListner);
                        } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                            BleSyncUtils.this.syncDistanceData(startDateSpo2, endDateSpo2, listner, bleApi, syncCompleteListner);
                        } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                            BleSyncUtils.this.b(listner);
                        } else {
                            BleSyncUtils.this.syncSensAISummaryData(listner, bleApi, syncCompleteListner);
                        }
                        BleSyncUtils.this.a(activityType, bleApi);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncManualStressData(@NotNull final SyncResultListner listener, @NotNull final BleApi bleApi, @NotNull final SyncCompleteListner syncCompleteListner, @Nullable final Date date, @Nullable final Date date2) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        bleApi.getData(new ReadManualStressRequest(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncManualStressData$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                listener.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                BleSyncUtils.this.a(ActivityType.STRESS, listener, true);
                if (response.getResponseData() != null && (response.getResponseData() instanceof ReadManualHRVAndStressResponse)) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadManualHRVAndStressResponse");
                    ReadManualHRVAndStressResponse readManualHRVAndStressResponse = (ReadManualHRVAndStressResponse) responseData;
                    if (!AppUtils.isEmpty(readManualHRVAndStressResponse.getManualHRVStressReadings())) {
                        String userDeviceId = PreferenceManager.getInstance().getUserDeviceID();
                        ManualDataDBWrite manualDataDBWrite = ManualDataDBWrite.getInstance(BleSyncUtils.this.getContext());
                        BleFormator.Companion companion = BleFormator.Companion;
                        ArrayList<ManualHRVAndStressReading> manualHRVStressReadings = readManualHRVAndStressResponse.getManualHRVStressReadings();
                        Intrinsics.checkNotNull(manualHRVStressReadings);
                        String serial_no = BleSyncUtils.this.getSerial_no();
                        Intrinsics.checkNotNullExpressionValue(userDeviceId, "userDeviceId");
                        manualDataDBWrite.insetAll(companion.getEntityManualHRVAndStressDataList(manualHRVStressReadings, serial_no, userDeviceId));
                    } else {
                        LogHelper.d("BleSyncUtils", "Manual Stress is empty");
                    }
                    if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                        BleSyncUtils.this.syncManualHRVData(listener, bleApi, syncCompleteListner);
                        return;
                    } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.syncSensAISummaryData(listener, bleApi, syncCompleteListner);
                        return;
                    } else if (!bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.b(listener);
                        return;
                    } else {
                        BleSyncUtils.this.syncDistanceData(date, date2, listener, bleApi, syncCompleteListner);
                        return;
                    }
                }
                LogHelper.d("BleSyncUtils", "Manual Stress is null");
                if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(listener, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.syncSensAISummaryData(listener, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.b(listener);
                } else {
                    BleSyncUtils.this.syncDistanceData(null, null, listener, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncRawAccelerometerHistoryData(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfRawAccelerometerDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfRawAccelerometerDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateAccelerometer()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateAccelerometer()) <= maxDaysOfRawAccelerometerDataOnBand) {
            maxDaysOfRawAccelerometerDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateAccelerometer());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfRawAccelerometerDataOnBand);
        Date time = calendar.getTime();
        ReadRawAccelerometerDataRequest readRawAccelerometerDataRequest = new ReadRawAccelerometerDataRequest.Builder().setStartDate(time).setEndDate(Calendar.getInstance().getTime()).build();
        Intrinsics.checkNotNullExpressionValue(readRawAccelerometerDataRequest, "readRawAccelerometerDataRequest");
        bleApi.getData(readRawAccelerometerDataRequest, new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncRawAccelerometerHistoryData$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.BleSyncUtils$syncRawAccelerometerHistoryData$1$onDataResponse$1", f = "BleSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ BleApi $bleApi;
                public final /* synthetic */ ReadRawAccelerometerDataResponse $readRawPPGDataResponse;
                public int label;
                public final /* synthetic */ BleSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ReadRawAccelerometerDataResponse readRawAccelerometerDataResponse, BleApi bleApi, BleSyncUtils bleSyncUtils, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$readRawPPGDataResponse = readRawAccelerometerDataResponse;
                    this.$bleApi = bleApi;
                    this.this$0 = bleSyncUtils;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$readRawPPGDataResponse, this.$bleApi, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        Iterator<RawAccelerometerHistoryData> it = this.$readRawPPGDataResponse.getRawAccelerometerHistoryData().iterator();
                        while (it.hasNext()) {
                            RawAccelerometerHistoryData next = it.next();
                            EntityRawAccelerometerData entityRawAccelerometerData = new EntityRawAccelerometerData();
                            entityRawAccelerometerData.setTimestamp(next.getTimeStamp());
                            entityRawAccelerometerData.setSerial_number(this.$bleApi.getMacAddress());
                            entityRawAccelerometerData.setDuration(next.getDuration());
                            entityRawAccelerometerData.setSamplingRate(next.getSamplingRate());
                            entityRawAccelerometerData.setInterval(next.getInterval());
                            entityRawAccelerometerData.setRecordNumber(next.getRecordNumber());
                            FileUtils.Companion companion = FileUtils.Companion;
                            ArrayList<AccelerometerData> accelerometerData = next.getAccelerometerData();
                            Intrinsics.checkNotNullExpressionValue(accelerometerData, "accelerometerHistoryData.accelerometerData");
                            String absolutePath = this.this$0.getContext().getFilesDir().getAbsolutePath();
                            Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
                            String writeObjectToFile = companion.writeObjectToFile(accelerometerData, "accelerometer_data_" + next.getTimeStamp(), absolutePath);
                            if (!AppUtils.isEmpty(writeObjectToFile)) {
                                entityRawAccelerometerData.setAccelerometerDataFilePath(writeObjectToFile);
                            }
                            RawAccelerometerDBRepo companion2 = RawAccelerometerDBRepo.Companion.getInstance(this.this$0.getContext());
                            if (companion2 != null) {
                                companion2.insert(entityRawAccelerometerData);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SyncResultListner.this.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() == null) {
                    SyncResultListner.this.onSuccess();
                } else if (response.getResponseData() instanceof ReadRawAccelerometerDataResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadRawAccelerometerDataResponse");
                    ReadRawAccelerometerDataResponse readRawAccelerometerDataResponse = (ReadRawAccelerometerDataResponse) responseData;
                    if (!AppUtils.isEmpty(readRawAccelerometerDataResponse.getRawAccelerometerHistoryData())) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(readRawAccelerometerDataResponse, bleApi, this, null), 2, null);
                    } else {
                        str = this.d;
                        LogHelper.d(str, "Manual Spo2 is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    }
                    if (readRawAccelerometerDataResponse.isComplete()) {
                        SyncResultListner.this.onSuccess();
                        this.a(ActivityType.RAW_PPG, bleApi);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncRawPPGHistoryData(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfRawPPGDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfRawPPGDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateRawPPG()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateRawPPG()) <= maxDaysOfRawPPGDataOnBand) {
            maxDaysOfRawPPGDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateRawPPG());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfRawPPGDataOnBand);
        Date time = calendar.getTime();
        ReadRawPPGHistoryDataRequest readRawPPGHistoryDataRequest = new ReadRawPPGHistoryDataRequest.Builder().setStartDate(time).setEndDate(Calendar.getInstance().getTime()).build();
        Intrinsics.checkNotNullExpressionValue(readRawPPGHistoryDataRequest, "readRawPPGHistoryDataRequest");
        bleApi.getData(readRawPPGHistoryDataRequest, new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncRawPPGHistoryData$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.BleSyncUtils$syncRawPPGHistoryData$1$onDataResponse$1", f = "BleSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ ReadRawPPGDataResponse $readRawPPGDataResponse;
                public int label;
                public final /* synthetic */ BleSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ReadRawPPGDataResponse readRawPPGDataResponse, BleSyncUtils bleSyncUtils, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$readRawPPGDataResponse = readRawPPGDataResponse;
                    this.this$0 = bleSyncUtils;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$readRawPPGDataResponse, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        Iterator<RawPPGHistoryData> it = this.$readRawPPGDataResponse.getPpgHistoryDataArrayList().iterator();
                        while (it.hasNext()) {
                            RawPPGHistoryData next = it.next();
                            EntityRawPPGData entityRawPPGData = new EntityRawPPGData();
                            entityRawPPGData.setTimestamp(next.getTimeStamp());
                            entityRawPPGData.setSerial_number(this.this$0.getSerial_no());
                            entityRawPPGData.setDuration(next.getDuration());
                            entityRawPPGData.setSamplingRate(next.getSamplingRate());
                            entityRawPPGData.setInterval(next.getInterval());
                            entityRawPPGData.setMovementLevel(next.getMovementLevel());
                            entityRawPPGData.setPpgType(next.getPpgType());
                            entityRawPPGData.setRecordNumber(next.getRecordNumber());
                            FileUtils.Companion companion = FileUtils.Companion;
                            ArrayList<Integer> ppgData = next.getPpgData();
                            Intrinsics.checkNotNullExpressionValue(ppgData, "rawPPGData.ppgData");
                            String absolutePath = this.this$0.getContext().getFilesDir().getAbsolutePath();
                            Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
                            String writeObjectToFile = companion.writeObjectToFile(ppgData, "ppg_data_" + next.getTimeStamp(), absolutePath);
                            if (!AppUtils.isEmpty(writeObjectToFile)) {
                                entityRawPPGData.setPpgDataFilePath(writeObjectToFile);
                            }
                            RawPPGDBRepo companion2 = RawPPGDBRepo.Companion.getInstance(this.this$0.getContext());
                            if (companion2 != null) {
                                companion2.insert(entityRawPPGData);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SyncResultListner.this.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() == null) {
                    SyncResultListner.this.onSuccess();
                } else if (response.getResponseData() instanceof ReadRawPPGDataResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadRawPPGDataResponse");
                    ReadRawPPGDataResponse readRawPPGDataResponse = (ReadRawPPGDataResponse) responseData;
                    if (!AppUtils.isEmpty(readRawPPGDataResponse.getPpgHistoryDataArrayList())) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(readRawPPGDataResponse, this, null), 2, null);
                    } else {
                        str = this.d;
                        LogHelper.d(str, "Manual Spo2 is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    }
                    if (readRawPPGDataResponse.isComplete()) {
                        SyncResultListner.this.onSuccess();
                        this.a(ActivityType.RAW_PPG, bleApi);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncSedentaryData(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi, @Nullable final SedentarySettings sedentarySettings) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfSedentaryDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfSedentaryDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateSedentary()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSedentary()) <= maxDaysOfSedentaryDataOnBand) {
            maxDaysOfSedentaryDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSedentary());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfSedentaryDataOnBand);
        Date startDate = calendar.getTime();
        Date endDate = Calendar.getInstance().getTime();
        ReadSedentaryDataRequest.Builder builder = new ReadSedentaryDataRequest.Builder();
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        ReadSedentaryDataRequest.Builder startDate2 = builder.setStartDate(startDate);
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        bleApi.getData(startDate2.setEndDate(endDate).build(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncSedentaryData$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.BleSyncUtils$syncSedentaryData$1$onDataResponse$1", f = "BleSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ ReadSedentaryResponse $readSedentaryResponse;
                public int label;
                public final /* synthetic */ BleSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ReadSedentaryResponse readSedentaryResponse, BleSyncUtils bleSyncUtils, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$readSedentaryResponse = readSedentaryResponse;
                    this.this$0 = bleSyncUtils;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$readSedentaryResponse, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        ArrayList<SedentaryData> sedentaryData = this.$readSedentaryResponse.getSedentaryData();
                        Intrinsics.checkNotNull(sedentaryData);
                        Iterator<SedentaryData> it = sedentaryData.iterator();
                        while (it.hasNext()) {
                            SedentaryData next = it.next();
                            EntitySedentary entitySedentary = new EntitySedentary();
                            entitySedentary.setRecordNumber(next.getRecordNumber());
                            entitySedentary.setSerial_number(this.this$0.getSerial_no());
                            entitySedentary.setTimestamp(next.getTimeStamp());
                            entitySedentary.setDate(RepositoryUtils.getDateFromTimestamp(next.getTimeStamp()));
                            SedentaryDBRepo companion = SedentaryDBRepo.Companion.getInstance(this.this$0.getContext());
                            Intrinsics.checkNotNull(companion);
                            companion.insert(entitySedentary);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$pushSedentaryAlertsDataToServer(this, sedentarySettings);
                SyncResultListner.this.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() == null) {
                    SyncResultListner.this.onSuccess();
                } else if (response.getResponseData() instanceof ReadSedentaryResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadSedentaryResponse");
                    ReadSedentaryResponse readSedentaryResponse = (ReadSedentaryResponse) responseData;
                    if (!AppUtils.isEmpty(readSedentaryResponse.getSedentaryData())) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(readSedentaryResponse, this, null), 2, null);
                    } else {
                        str = this.d;
                        LogHelper.d(str, "Sedentary data is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    }
                    if (readSedentaryResponse.isComplete()) {
                        BleSyncUtils.access$pushSedentaryAlertsDataToServer(this, sedentarySettings);
                        SyncResultListner.this.onSuccess();
                        this.a(ActivityType.SEDENTARY, bleApi);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncSensAISummaryData(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi, @NotNull SyncCompleteListner syncCompleteListner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(syncCompleteListner, "syncCompleteListner");
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(bleApi.getMacAddress());
        int maxDaysOfSensAISummaryDataOnBand = bleApi.getDeviceSupportedFeatures().getMaxDaysOfSensAISummaryDataOnBand();
        if (deviceInfoBy != null && !AppUtils.isEmpty(deviceInfoBy.getLastSyncDateSensAISummary()) && RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSensAISummary()) <= maxDaysOfSensAISummaryDataOnBand) {
            maxDaysOfSensAISummaryDataOnBand = RepositoryUtils.findDateDifference(deviceInfoBy.getLastSyncDateSensAISummary());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -maxDaysOfSensAISummaryDataOnBand);
        Date startDate = calendar.getTime();
        Date endDate = Calendar.getInstance().getTime();
        GetSensAISummaryRequest.Builder builder = new GetSensAISummaryRequest.Builder();
        Intrinsics.checkNotNullExpressionValue(startDate, "startDate");
        GetSensAISummaryRequest.Builder startDate2 = builder.setStartDate(startDate);
        Intrinsics.checkNotNullExpressionValue(endDate, "endDate");
        bleApi.getData(startDate2.setEndDate(endDate).build(), new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncSensAISummaryData$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.BleSyncUtils$syncSensAISummaryData$1$onDataResponse$1", f = "BleSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ BleApi $bleApi;
                public final /* synthetic */ Ref.ObjectRef<Integer> $count;
                public final /* synthetic */ SyncResultListner $listner;
                public final /* synthetic */ GetSensAISummaryDataResponse $sensAISummaryResponse;
                public int label;
                public final /* synthetic */ BleSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(GetSensAISummaryDataResponse getSensAISummaryDataResponse, BleSyncUtils bleSyncUtils, Ref.ObjectRef<Integer> objectRef, SyncResultListner syncResultListner, BleApi bleApi, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$sensAISummaryResponse = getSensAISummaryDataResponse;
                    this.this$0 = bleSyncUtils;
                    this.$count = objectRef;
                    this.$listner = syncResultListner;
                    this.$bleApi = bleApi;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$sensAISummaryResponse, this.this$0, this.$count, this.$listner, this.$bleApi, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        ArrayList arrayList = new ArrayList();
                        ArrayList<SensAISummaryData> sensAISummaryData = this.$sensAISummaryResponse.getSensAISummaryData();
                        Iterable<IndexedValue> withIndex = sensAISummaryData != null ? CollectionsKt___CollectionsKt.withIndex(sensAISummaryData) : null;
                        Intrinsics.checkNotNull(withIndex);
                        for (IndexedValue indexedValue : withIndex) {
                            int component1 = indexedValue.component1();
                            SensAISummaryData sensAISummaryData2 = (SensAISummaryData) indexedValue.component2();
                            SensAIActivitySummary sensAIActivitySummary = new SensAIActivitySummary();
                            sensAIActivitySummary.setMacAddress(this.this$0.getSerial_no());
                            sensAIActivitySummary.setTimestamp(Boxing.boxLong(sensAISummaryData2.getTimeStamp()));
                            sensAIActivitySummary.setActivityType(sensAISummaryData2.getActivityType());
                            sensAIActivitySummary.setDurationSec(sensAISummaryData2.getDuration());
                            sensAIActivitySummary.setTotalSteps(sensAISummaryData2.getTotalSteps());
                            sensAIActivitySummary.setTotalCalories(sensAISummaryData2.getTotalCalories());
                            sensAIActivitySummary.setHand(sensAISummaryData2.getHand());
                            sensAIActivitySummary.setGoalType(sensAISummaryData2.getGoalType());
                            sensAIActivitySummary.setTargetGoalValue(Math.abs(sensAISummaryData2.getTargetGoalValue()));
                            sensAIActivitySummary.setGoalCompletionPct(sensAISummaryData2.getGoalCompletionPct());
                            sensAIActivitySummary.setMaxHR(sensAISummaryData2.getMaxHr());
                            sensAIActivitySummary.setAvgHR(sensAISummaryData2.getAvgHr());
                            if (sensAISummaryData2.getActivityType() == 1) {
                                sensAIActivitySummary.setSessionId(sensAISummaryData2.getSessionId() + "-1");
                                sensAIActivitySummary.setTotalSwings(sensAISummaryData2.getTotalSwings());
                                sensAIActivitySummary.setPlayed(sensAISummaryData2.getPlayed());
                                sensAIActivitySummary.setHitPct(sensAISummaryData2.getHitPct());
                                sensAIActivitySummary.setMaxHandSpeed(sensAISummaryData2.getMaxHandSpeed());
                                sensAIActivitySummary.setAvgHandSpeed(sensAISummaryData2.getAvgHandSpeed());
                            } else {
                                sensAIActivitySummary.setSessionId(sensAISummaryData2.getSessionId() + "-2");
                                sensAIActivitySummary.setBowlingType(sensAISummaryData2.getBowlingType());
                                sensAIActivitySummary.setTotalBallsBowled(sensAISummaryData2.getTotalBallsBowled());
                                sensAIActivitySummary.setMaxArmSpeed(sensAISummaryData2.getMaxArmSpeed());
                                sensAIActivitySummary.setAvgArmSpeed(sensAISummaryData2.getAvgArmSpeed());
                                sensAIActivitySummary.setMinArmSpeed(sensAISummaryData2.getMinArmSpeed());
                            }
                            arrayList.add(sensAIActivitySummary);
                            SensAIBeamDBWrite sensAIBeamDBWrite = SensAIBeamDBWrite.getInstance(this.this$0.getContext());
                            Intrinsics.checkNotNull(sensAIBeamDBWrite);
                            sensAIBeamDBWrite.insertActivitySummaryList(arrayList);
                            Integer num = this.$count.element;
                            int i = component1 + 1;
                            if (num != null && num.intValue() == i) {
                                this.this$0.syncSensAISummaryDetailsData(this.$listner, this.$bleApi, String.valueOf(sensAISummaryData2.getSessionId()), true);
                            } else {
                                this.this$0.syncSensAISummaryDetailsData(this.$listner, this.$bleApi, String.valueOf(sensAISummaryData2.getSessionId()), false);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                listner.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                String str2;
                String str3;
                Intrinsics.checkNotNullParameter(response, "response");
                BleSyncUtils bleSyncUtils = BleSyncUtils.this;
                ActivityType activityType = ActivityType.SENS_AI;
                bleSyncUtils.a(activityType, listner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "Sens AI summary data in bleSync  " + response);
                if (response.getResponseData() == null) {
                    listner.onSuccess();
                } else if (response.getResponseData() instanceof GetSensAISummaryDataResponse) {
                    str2 = BleSyncUtils.this.d;
                    LogHelper.d(str2, String.valueOf(response.getResponseData()));
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSensAISummaryDataResponse");
                    GetSensAISummaryDataResponse getSensAISummaryDataResponse = (GetSensAISummaryDataResponse) responseData;
                    if (AppUtils.isEmpty(getSensAISummaryDataResponse.getSensAISummaryData())) {
                        str3 = BleSyncUtils.this.d;
                        LogHelper.d(str3, "SensAI data is empty", RepositoryModuleNames.REPOSITORY.getModuleName());
                    } else {
                        Ref.ObjectRef objectRef = new Ref.ObjectRef();
                        ArrayList<SensAISummaryData> sensAISummaryData = getSensAISummaryDataResponse.getSensAISummaryData();
                        objectRef.element = sensAISummaryData != null ? Integer.valueOf(sensAISummaryData.size()) : 0;
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(getSensAISummaryDataResponse, BleSyncUtils.this, objectRef, listner, bleApi, null), 2, null);
                    }
                    if (getSensAISummaryDataResponse.isComplete()) {
                        BleSyncUtils.this.b(listner);
                        BleSyncUtils.this.postDailyDataAggregateData();
                        BleSyncUtils.this.a(listner, bleApi);
                        BleSyncUtils.this.a(activityType, bleApi);
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void syncSensAISummaryDetailsData(@NotNull final SyncResultListner listner, @NotNull final BleApi bleApi, @NotNull String sessionID, final boolean z) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        Intrinsics.checkNotNullParameter(bleApi, "bleApi");
        Intrinsics.checkNotNullParameter(sessionID, "sessionID");
        GetSensAISummaryDetailsRequest build = new GetSensAISummaryDetailsRequest.Builder().setSessionID(sessionID).build();
        new GetSensAISummaryDetailsRequest().setSessionID(sessionID);
        bleApi.getData(build, new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncSensAISummaryDetailsData$1

            @DebugMetadata(c = "com.coveiot.repository.datasync.BleSyncUtils$syncSensAISummaryDetailsData$1$onDataResponse$1", f = "BleSyncUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ BleApi $bleApi;
                public final /* synthetic */ SyncResultListner $listner;
                public final /* synthetic */ GetSensAISummaryDetailsResponse $sensAISummaryDetailsResponse;
                public int label;
                public final /* synthetic */ BleSyncUtils this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(BleSyncUtils bleSyncUtils, GetSensAISummaryDetailsResponse getSensAISummaryDetailsResponse, SyncResultListner syncResultListner, BleApi bleApi, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = bleSyncUtils;
                    this.$sensAISummaryDetailsResponse = getSensAISummaryDetailsResponse;
                    this.$listner = syncResultListner;
                    this.$bleApi = bleApi;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$sensAISummaryDetailsResponse, this.$listner, this.$bleApi, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        SensAIActivitySummaryDetails sensAIActivitySummaryDetails = new SensAIActivitySummaryDetails();
                        sensAIActivitySummaryDetails.setMacAddress(this.this$0.getSerial_no());
                        sensAIActivitySummaryDetails.setActivityType(this.$sensAISummaryDetailsResponse.getActivityType());
                        sensAIActivitySummaryDetails.setDetailsDataNum(this.$sensAISummaryDetailsResponse.getDetailsDataNum());
                        sensAIActivitySummaryDetails.setUnixTimeStamp(Boxing.boxLong(this.$sensAISummaryDetailsResponse.getTimeStamp()));
                        sensAIActivitySummaryDetails.setHr((ArrayList) this.$sensAISummaryDetailsResponse.getHrList());
                        sensAIActivitySummaryDetails.setSteps((ArrayList) this.$sensAISummaryDetailsResponse.getStepsList());
                        sensAIActivitySummaryDetails.setDistance((ArrayList) this.$sensAISummaryDetailsResponse.getDistanceList());
                        sensAIActivitySummaryDetails.setCalories((ArrayList) this.$sensAISummaryDetailsResponse.getCaloriesList());
                        if (this.$sensAISummaryDetailsResponse.getActivityType() == 1) {
                            sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$sensAISummaryDetailsResponse.getHandSpeedList());
                            sensAIActivitySummaryDetails.setSessionId(this.$sensAISummaryDetailsResponse.getSessionId() + "-1");
                            sensAIActivitySummaryDetails.setHitOrMiss((ArrayList) this.$sensAISummaryDetailsResponse.getHitOrMissList());
                        } else {
                            sensAIActivitySummaryDetails.setHandSpeed((ArrayList) this.$sensAISummaryDetailsResponse.getArmSpeedList());
                            sensAIActivitySummaryDetails.setSessionId(this.$sensAISummaryDetailsResponse.getSessionId() + "-2");
                        }
                        SensAIBeamDBWrite sensAIBeamDBWrite = SensAIBeamDBWrite.getInstance(this.this$0.getContext());
                        Intrinsics.checkNotNull(sensAIBeamDBWrite);
                        sensAIBeamDBWrite.insertActivitySummaryDetails(sensAIActivitySummaryDetails);
                        this.this$0.a(this.$listner, this.$bleApi);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.a(SyncResultListner.this, bleApi);
                SyncResultListner.this.onFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() == null) {
                    SyncResultListner.this.onSuccess();
                } else if (response.getResponseData() instanceof GetSensAISummaryDetailsResponse) {
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSensAISummaryDetailsResponse");
                    GetSensAISummaryDetailsResponse getSensAISummaryDetailsResponse = (GetSensAISummaryDetailsResponse) responseData;
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(this, getSensAISummaryDetailsResponse, SyncResultListner.this, bleApi, null), 2, null);
                    if (getSensAISummaryDetailsResponse.isComplete()) {
                        this.a(SyncResultListner.this, bleApi);
                    }
                    if (getSensAISummaryDetailsResponse.isComplete() && z) {
                        this.a(SyncResultListner.this, bleApi);
                        SyncResultListner.this.onSuccess();
                    }
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void a(final Date date, final Date date2, final Date date3, final Date date4, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        PeriodicSPO2BleRequest spo2DataRequest = new PeriodicSPO2BleRequest.Builder().setStartDate(date).setEndDate(date2).build();
        Intrinsics.checkNotNullExpressionValue(spo2DataRequest, "spo2DataRequest");
        a(spo2DataRequest, new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getPeriodicSpo2DataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.SPO2, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess spo2 Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date, date2, date3, date4, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date3, date4);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date, date2, date3, date4, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date3, date4, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void a() {
        Iterator<DailySleepData> it;
        String macAddress = BleApiManager.getInstance(getContext()).getBleApi().getMacAddress();
        List<DailySleepData> totalUnSyncedSleepDatFomHrStress = SleepDBRead.getInstance(getContext()).getTotalUnSyncedSleepDatFomHrStress(macAddress);
        if (totalUnSyncedSleepDatFomHrStress == null || totalUnSyncedSleepDatFomHrStress.size() <= 0) {
            return;
        }
        for (Iterator<DailySleepData> it2 = totalUnSyncedSleepDatFomHrStress.iterator(); it2.hasNext(); it2 = it) {
            DailySleepData next = it2.next();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(AppUtils.parseDate(next.getDate(), "yyyy-MM-dd"));
            Object clone = calendar.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(6, -1);
            String formatDate = RepositoryUtils.formatDate(calendar2.getTime(), "yyyy-MM-dd");
            SleepSummaryHelper sleepSummaryHelper = SleepSummaryHelper.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
            SleepSummaryData sleepSummary = sleepSummaryHelper.getSleepSummary(context, calendar);
            DailySleepData dailySleepData = new DailySleepData();
            dailySleepData.setMinStress(0);
            dailySleepData.setMaxStress(0);
            dailySleepData.setMinHr(0);
            dailySleepData.setMaxHr(0);
            dailySleepData.setMinAmbientSound(0);
            dailySleepData.setMaxAmbientSound(0);
            dailySleepData.setDate(next.getDate());
            dailySleepData.setMacAddress(macAddress);
            if (sleepSummary != null) {
                StressDBRead stressDBRead = StressDBRead.getInstance(getContext());
                String date = next.getDate();
                String str = sleepSummary.f7383a;
                Intrinsics.checkNotNullExpressionValue(str, "sleepSummary.startTime");
                String formatDate2 = AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
                String str2 = sleepSummary.b;
                Intrinsics.checkNotNullExpressionValue(str2, "sleepSummary.endTime");
                it = it2;
                MinMaxData stressData = stressDBRead.getHourlyMinMaxStressWithDate(formatDate, date, macAddress, formatDate2, AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str2, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00"));
                if (stressData != null) {
                    Intrinsics.checkNotNullExpressionValue(stressData, "stressData");
                    if (stressData.getMaxValue() > 0 && stressData.getMinValue() > 0) {
                        dailySleepData.setMinStress(stressData.getMinValue());
                        dailySleepData.setMaxStress(stressData.getMaxValue());
                    }
                }
                HeartRateDBRead heartRateDBRead = HeartRateDBRead.getInstance(getContext());
                String date2 = next.getDate();
                String str3 = sleepSummary.f7383a;
                Intrinsics.checkNotNullExpressionValue(str3, "sleepSummary.startTime");
                String formatDate3 = AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str3, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
                String str4 = sleepSummary.b;
                Intrinsics.checkNotNullExpressionValue(str4, "sleepSummary.endTime");
                MinMaxData hrData = heartRateDBRead.getHourlyMinMaxHeartRateDate(formatDate, date2, macAddress, formatDate3, AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str4, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00"));
                if (hrData != null) {
                    Intrinsics.checkNotNullExpressionValue(hrData, "hrData");
                    if (hrData.getMinValue() > 0 && hrData.getMaxValue() > 0) {
                        dailySleepData.setMinHr(hrData.getMinValue());
                        dailySleepData.setMaxHr(hrData.getMaxValue());
                    }
                }
                String str5 = this.d;
                LogHelper.d(str5, " daily sleepp => minHR " + hrData.getMinValue() + " Maxhr  " + hrData.getMaxValue() + "  minstress " + stressData.getMinValue() + " maxStress " + stressData.getMaxValue());
                if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.IDO_CONNECT) {
                    AmbientSoundDBRead ambientSoundDBRead = AmbientSoundDBRead.getInstance(getContext());
                    String date3 = next.getDate();
                    String str6 = sleepSummary.f7383a;
                    Intrinsics.checkNotNullExpressionValue(str6, "sleepSummary.startTime");
                    String formatDate4 = AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str6, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00");
                    String str7 = sleepSummary.b;
                    Intrinsics.checkNotNullExpressionValue(str7, "sleepSummary.endTime");
                    MinMaxData ambientSoundData = ambientSoundDBRead.getHourlyMinMaxAmbientSoundDate(formatDate, date3, macAddress, formatDate4, AppUtils.formatDate(AppUtils.parseDate(m.replace$default(str7, ExifInterface.GPS_DIRECTION_TRUE, HexStringBuilder.DEFAULT_SEPARATOR, false, 4, (Object) null), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:00:00"));
                    if (ambientSoundData != null) {
                        Intrinsics.checkNotNullExpressionValue(ambientSoundData, "ambientSoundData");
                        if (ambientSoundData.getMinValue() > 0 && ambientSoundData.getMaxValue() > 0) {
                            dailySleepData.setMinAmbientSound(ambientSoundData.getMinValue());
                            dailySleepData.setMaxAmbientSound(ambientSoundData.getMaxValue());
                        }
                    }
                }
            } else {
                it = it2;
            }
            if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.IDO_SELECT) {
                SleepDBRead.getInstance(getContext()).updateMinMaxHrStressData(dailySleepData);
            } else if (BleApiManager.getInstance(getContext()).getDeviceType() == DeviceType.IDO_CONNECT) {
                SleepDBRead.getInstance(getContext()).updateMinMaxHrStressAmbientSoundInDailySleepData(dailySleepData);
            }
        }
    }

    public final void b(SyncResultListner syncResultListner) {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j.clear();
        syncResultListner.onSuccess();
    }

    public final String b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String time = new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(time, "time");
        return time;
    }

    public final void a(Date date, Date date2, final Date date3, final Date date4, final Date date5, final Date date6, final Date date7, final Date date8, final Date date9, final Date date10, final Date date11, final Date date12, final Date date13, final Date date14, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        SleepDataRequest sleepDataRequest = new SleepDataRequest.Builder().setStartDate(date).setEndDate(date2).build();
        Intrinsics.checkNotNullExpressionValue(sleepDataRequest, "sleepDataRequest");
        a(sleepDataRequest, new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getSleepDataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.SLEEP, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess Sleep Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isHeartRateSupported()) {
                    BleSyncUtils.this.a(date3, date4, date5, date6, date7, date8, date9, date10, date11, date12, date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
                    BleSyncUtils.this.a(date5, date6, date7, date8, date9, date10, date11, date12, date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                    BleSyncUtils.this.a(date7, date8, date9, date10, date11, date12, date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    BleSyncUtils.this.a(date9, date10, date11, date12, date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    BleSyncUtils.this.a(date11, date12, date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date9, date10, date13, date14, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date13, date14);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date9, date10, date13, date14, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date13, date14, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void a(Date date, Date date2, final Date date3, final Date date4, final Date date5, final Date date6, final Date date7, final Date date8, final Date date9, final Date date10, final Date date11, final Date date12, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        a(new HeartRateDataRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getHeartRateDataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.HEARTRATE, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess Heart Rate Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isBpSupported()) {
                    BleSyncUtils.this.a(date3, date4, date5, date6, date7, date8, date9, date10, date11, date12, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                    BleSyncUtils.this.a(date5, date6, date7, date8, date9, date10, date11, date12, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    BleSyncUtils.this.a(date7, date8, date9, date10, date11, date12, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    BleSyncUtils.this.a(date9, date10, date11, date12, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date7, date8, date11, date12, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date11, date12);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date7, date8, date11, date12, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date11, date12, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void a(Date date, Date date2, final Date date3, final Date date4, final Date date5, final Date date6, final Date date7, final Date date8, final Date date9, final Date date10, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        a(new BpDataRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getBPDataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.BP, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess BP Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isRrSupported()) {
                    BleSyncUtils.this.a(date3, date4, date5, date6, date7, date8, date9, date10, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    BleSyncUtils.this.a(date5, date6, date7, date8, date9, date10, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    BleSyncUtils.this.a(date7, date8, date9, date10, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date5, date6, date9, date10, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date9, date10);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date5, date6, date9, date10, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date9, date10, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void a(Date date, Date date2, final Date date3, final Date date4, final Date date5, final Date date6, final Date date7, final Date date8, final SyncResultListner syncResultListner, final BleApi bleApi, final SyncCompleteListner syncCompleteListner) {
        a(new RrHistoryRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getRRDataFromBle$1
            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchFailure(@NotNull Error error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onDataFetchSucess() {
                String str;
                BleSyncUtils.this.a(ActivityType.RR_HISTORY, syncResultListner, true);
                str = BleSyncUtils.this.d;
                LogHelper.d(str, "onDataFetchSucess RR Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                if (bleApi.getDeviceSupportedFeatures().isTemparatureHistorySupported()) {
                    BleSyncUtils.this.a(date3, date4, date5, date6, date7, date8, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isPeriodicSpO2Supported()) {
                    BleSyncUtils.this.a(date5, date6, date7, date8, syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isStressHistorySupported()) {
                    BleSyncUtils.this.b(bleApi, date3, date4, date7, date8, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDynamicHRMergeSupported()) {
                    BleSyncUtils.this.a(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
                    BleSyncUtils.this.syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
                    BleSyncUtils.this.syncManualStressData(syncResultListner, bleApi, syncCompleteListner, date7, date8);
                } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
                    BleSyncUtils.this.syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
                    BleSyncUtils.this.a(bleApi, date3, date4, date7, date8, syncResultListner, syncCompleteListner);
                } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                    BleSyncUtils.this.syncDistanceData(date7, date8, syncResultListner, bleApi, syncCompleteListner);
                } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                    BleSyncUtils.this.b(syncResultListner);
                } else {
                    BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                }
            }

            @Override // com.coveiot.repository.datasync.BleSyncUtils.a
            public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
            }
        }, bleApi);
    }

    public final void a(BleBaseRequest bleBaseRequest, final a aVar, final BleApi bleApi) {
        RepositoryModuleNames repositoryModuleNames = RepositoryModuleNames.REPOSITORY;
        LogHelper.d(this.d, "getDataFromDevice " + bleBaseRequest, repositoryModuleNames.getModuleName());
        bleApi.getData(bleBaseRequest, new DataResultListener() { // from class: com.coveiot.repository.datasync.BleSyncUtils$getDataFromDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BleSyncUtils.a.this.onDataFetchFailure(new Error(error.getErrorMsg(), error.getErrorCode(), RepositoryUtils.getErrorDataType(error)));
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                this.a(response, BleSyncUtils.a.this, bleApi);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                if (progress.getBleBaseRequest() instanceof StepsDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.WALK, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof SleepDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.SLEEP, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof HeartRateDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.HEARTRATE, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof BpDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.BP, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof TemperatureDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.TEMPERATURE, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof StressDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.STRESS, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof AmbientSoundDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.AMBIENT_SOUND, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof GetDistanceDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.DISTANCE, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof GetCalorieDataRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.CALORIE, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof ReadManualSpo2Request) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.SPO2, 0, 0, 0));
                } else if (progress.getBleBaseRequest() instanceof ReadManualStressRequest) {
                    BleSyncUtils.a.this.onProgressUpdate(new ProgressDataBean(progress.getProgressType(), progress.getProgress(), ActivityType.STRESS, 0, 0, 0));
                }
            }
        });
        LogHelper.d(this.d, "getDataFromDevice 2" + bleBaseRequest, repositoryModuleNames.getModuleName());
    }

    public final void a(BleBaseResponse bleBaseResponse, final a aVar, final BleApi bleApi) {
        RepositoryModuleNames repositoryModuleNames = RepositoryModuleNames.REPOSITORY;
        LogHelper.d(this.d, "processBleResponse is Called for " + bleBaseResponse, repositoryModuleNames.getModuleName());
        if (bleBaseResponse.getResponseData() instanceof StepsResponse) {
            Object responseData = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.StepsResponse");
            final StepsResponse stepsResponse = (StepsResponse) responseData;
            if (stepsResponse.getStepsDayData() != null) {
                String str = this.d;
                StringBuilder sb = new StringBuilder();
                sb.append("StepsResponse Date ");
                StepsDayData stepsDayData = stepsResponse.getStepsDayData();
                Intrinsics.checkNotNull(stepsDayData);
                sb.append(stepsDayData.getmDate());
                LogHelper.d(str, sb.toString(), repositoryModuleNames.getModuleName());
                StepsDayData stepsDayData2 = stepsResponse.getStepsDayData();
                Intrinsics.checkNotNull(stepsDayData2);
                stepsDayData2.mMacAddress = getSerial_no();
                BleFormator.Companion companion = BleFormator.Companion;
                StepsDayData stepsDayData3 = stepsResponse.getStepsDayData();
                Intrinsics.checkNotNull(stepsDayData3);
                List<HourlyWalkData> hourlySleepData = companion.getHourlySleepData(stepsDayData3);
                if (!hourlySleepData.isEmpty()) {
                    WalkDBWrite.getInstance(getContext()).insertHourlyStepsData(hourlySleepData);
                }
                StepsDayData stepsDayData4 = stepsResponse.getStepsDayData();
                Intrinsics.checkNotNull(stepsDayData4);
                final DailyWalkData dailyWalkData = companion.getDailyWalkData(stepsDayData4);
                if (ProfileDBRead.getInstance(getContext()).getLatestProfileData() != null) {
                    dailyWalkData.setStepsTarget(ProfileDBRead.getInstance(getContext()).getLatestProfileData().stepsTarget);
                }
                dailyWalkData.setMacAddress(getSerial_no());
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.repository.datasync.BleSyncUtils$processBleResponse$1
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2;
                        if (!StepsResponse.this.isDailyWalkDataCalculateFromBand()) {
                            StepsDayData stepsDayData5 = StepsResponse.this.getStepsDayData();
                            Intrinsics.checkNotNull(stepsDayData5);
                            if (stepsDayData5.timeLogBean != null) {
                                dailyWalkData.setValue(WalkDBRead.getInstance(this.getContext()).getTotalSteps(dailyWalkData.mDate, this.getSerial_no()));
                            }
                        }
                        if (StepsResponse.this.isCaloriesDistanceCalculatedFromBand()) {
                            if (StepsResponse.this.isBandSupportBasicCalories()) {
                                if (ProfileDBRead.getInstance(this.getContext()).getLatestProfileData() != null) {
                                    int i = ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().age;
                                    int i2 = ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().gender;
                                    StepsDayData stepsDayData6 = StepsResponse.this.getStepsDayData();
                                    Intrinsics.checkNotNull(stepsDayData6);
                                    double calculateBasicCaloriesForARMBand = RepositoryUtils.calculateBasicCaloriesForARMBand(ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().height, (int) ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().weight, i, i2, stepsDayData6.mDate);
                                    str2 = this.d;
                                    LogHelper.d(str2, "basicCalories " + calculateBasicCaloriesForARMBand, RepositoryModuleNames.REPOSITORY.getModuleName());
                                    DailyWalkData dailyWalkData2 = dailyWalkData;
                                    StepsDayData stepsDayData7 = StepsResponse.this.getStepsDayData();
                                    Intrinsics.checkNotNull(stepsDayData7);
                                    dailyWalkData2.setCalories(stepsDayData7.mCalories + calculateBasicCaloriesForARMBand);
                                }
                            } else {
                                DailyWalkData dailyWalkData3 = dailyWalkData;
                                StepsDayData stepsDayData8 = StepsResponse.this.getStepsDayData();
                                Intrinsics.checkNotNull(stepsDayData8);
                                dailyWalkData3.setCalories(stepsDayData8.mCalories);
                            }
                            StepsDayData stepsDayData9 = StepsResponse.this.getStepsDayData();
                            Intrinsics.checkNotNull(stepsDayData9);
                            double d = stepsDayData9.mDistance;
                            if (StepsResponse.this.isDistanceIsInMetresFromBand()) {
                                DailyWalkData dailyWalkData4 = dailyWalkData;
                                StepsDayData stepsDayData10 = StepsResponse.this.getStepsDayData();
                                Intrinsics.checkNotNull(stepsDayData10);
                                dailyWalkData4.setMeters((int) stepsDayData10.mDistance);
                            } else {
                                dailyWalkData.setMeters((int) (d * 1000));
                            }
                        } else if (ProfileDBRead.getInstance(this.getContext()).getLatestProfileData() != null) {
                            if (BleApiManager.getInstance(this.getContext()) != null && (RepositoryUtils.isKaHaDevice(this.getContext()) || RepositoryUtils.isKaHaDeviceREM(this.getContext()))) {
                                DailyWalkData dailyWalkData5 = dailyWalkData;
                                dailyWalkData5.setCalories(RepositoryUtils.calculateCaloriesForCZ(dailyWalkData5.getValue(), ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().height, (int) ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().weight));
                            } else {
                                DailyWalkData dailyWalkData6 = dailyWalkData;
                                dailyWalkData6.setCalories(RepositoryUtils.caluclateCaloreis(dailyWalkData6.getValue(), ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().height, (int) ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().weight));
                            }
                            DailyWalkData dailyWalkData7 = dailyWalkData;
                            dailyWalkData7.setMeters(RepositoryUtils.calculateDistance(dailyWalkData7.getValue(), ProfileDBRead.getInstance(this.getContext()).getLatestProfileData().height));
                        }
                        WalkDBWrite.getInstance(this.getContext()).insertDailyData(dailyWalkData);
                    }
                }, 1000L);
                if (stepsResponse.isComplete()) {
                    LogHelper.d(this.d, "StepsResponse isComplete = true", repositoryModuleNames.getModuleName());
                    aVar.onDataFetchSucess();
                    a(ActivityType.WALK, bleApi);
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
            return;
        }
        int i = 0;
        String str2 = null;
        String str3 = null;
        if (bleBaseResponse.getResponseData() instanceof GetDistanceDataResponse) {
            Object responseData2 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData2, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetDistanceDataResponse");
            GetDistanceDataResponse getDistanceDataResponse = (GetDistanceDataResponse) responseData2;
            if (getDistanceDataResponse.getDistanceDataData() != null) {
                DailyDistanceData distanceDataData = getDistanceDataResponse.getDistanceDataData();
                Intrinsics.checkNotNull(distanceDataData);
                for (DistanceHourlyData distanceHourlyData : distanceDataData.getDistanceHourlyDataList()) {
                    i += distanceHourlyData.getDistanceValue();
                    str2 = distanceHourlyData.getDate();
                    WalkDBWrite.getInstance(getContext()).updateHourlyDistanceData((ArrayList) distanceHourlyData.getCodeValues(), distanceHourlyData.getDistanceValue(), distanceHourlyData.getDate(), distanceHourlyData.getStartHour(), distanceHourlyData.getEndHour(), getSerial_no());
                }
                WalkDBWrite.getInstance(getContext()).updateDailyDistance(i, str2, getSerial_no());
                if (getDistanceDataResponse.isComplete()) {
                    LogHelper.d(this.d, "Distance isComplete = true", RepositoryModuleNames.REPOSITORY.getModuleName());
                    aVar.onDataFetchSucess();
                    a(ActivityType.DISTANCE, bleApi);
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof GetCalorieDataResponse) {
            Object responseData3 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData3, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetCalorieDataResponse");
            GetCalorieDataResponse getCalorieDataResponse = (GetCalorieDataResponse) responseData3;
            if (getCalorieDataResponse.getCalorieData() != null) {
                float f = 0.0f;
                DailyCalorieData calorieData = getCalorieDataResponse.getCalorieData();
                Intrinsics.checkNotNull(calorieData);
                for (CalorieHourlyData calorieHourlyData : calorieData.getHourlyDataList()) {
                    Float calorieValue = calorieHourlyData.getCalorieValue();
                    Intrinsics.checkNotNullExpressionValue(calorieValue, "hourItem.calorieValue");
                    f += calorieValue.floatValue();
                    str3 = calorieHourlyData.getDate();
                    WalkDBWrite walkDBWrite = WalkDBWrite.getInstance(getContext());
                    List<Float> codeValues = calorieHourlyData.getCodeValues();
                    Intrinsics.checkNotNull(codeValues, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Float>");
                    walkDBWrite.updateHourlyCalorieData((ArrayList) codeValues, (int) Math.floor(calorieHourlyData.getCalorieValue().floatValue()), calorieHourlyData.getDate(), calorieHourlyData.getStartHour(), calorieHourlyData.getEndHour(), getSerial_no());
                }
                WalkDBWrite.getInstance(getContext()).updateDailyCalorie((int) Math.floor(f), str3, getSerial_no());
                if (getCalorieDataResponse.isComplete()) {
                    LogHelper.d(this.d, "Calorie isComplete = true", RepositoryModuleNames.REPOSITORY.getModuleName());
                    aVar.onDataFetchSucess();
                    a(ActivityType.CALORIE, bleApi);
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof SleepResponse) {
            Object responseData4 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData4, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.SleepResponse");
            final SleepResponse sleepResponse = (SleepResponse) responseData4;
            if (sleepResponse.getSleepDayData() != null) {
                String str4 = this.d;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("sleepResponse Date ");
                SleepDayData sleepDayData = sleepResponse.getSleepDayData();
                Intrinsics.checkNotNull(sleepDayData);
                sb2.append(sleepDayData.getmDate());
                LogHelper.d(str4, sb2.toString(), repositoryModuleNames.getModuleName());
                SleepDayData sleepDayData2 = sleepResponse.getSleepDayData();
                Intrinsics.checkNotNull(sleepDayData2);
                String yesterdayDate = RepositoryUtils.getYesterdayDate(sleepDayData2.getmDate());
                if (yesterdayDate != null && !RepositoryUtils.isMoyangDevice(getContext())) {
                    Context context = getContext();
                    SleepDayData sleepDayData3 = sleepResponse.getSleepDayData();
                    Intrinsics.checkNotNull(sleepDayData3);
                    if (!RepositoryUtils.isDataPresentInDb(context, yesterdayDate, sleepDayData3.mMacAddress, ActivityType.SLEEP)) {
                        SleepDayData sleepDayData4 = sleepResponse.getSleepDayData();
                        Intrinsics.checkNotNull(sleepDayData4);
                        List<SleepResponse> sleepNoDataList = getSleepNoDataList(yesterdayDate, sleepDayData4.mMacAddress);
                        if (sleepNoDataList != null && sleepNoDataList.size() > 0) {
                            SleepResponse sleepResponse2 = sleepNoDataList.get(0);
                            Intrinsics.checkNotNull(sleepResponse2);
                            a(sleepResponse2, aVar, bleApi);
                        }
                    }
                }
                new Handler().postDelayed(new Runnable() { // from class: com.coveiot.repository.datasync.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        BleSyncUtils.a(BleSyncUtils.this, sleepResponse, aVar, bleApi);
                    }
                }, 500L);
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof HeartRateResponse) {
            Object responseData5 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData5, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.HeartRateResponse");
            HeartRateResponse heartRateResponse = (HeartRateResponse) responseData5;
            if (heartRateResponse.getHeartRateData() != null) {
                String str5 = this.d;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("heartRateResponse Date ");
                HeartRateDayData heartRateData = heartRateResponse.getHeartRateData();
                Intrinsics.checkNotNull(heartRateData);
                sb3.append(heartRateData.getmDate());
                LogHelper.d(str5, sb3.toString(), repositoryModuleNames.getModuleName());
                BleFormator.Companion companion2 = BleFormator.Companion;
                HeartRateDayData heartRateData2 = heartRateResponse.getHeartRateData();
                Intrinsics.checkNotNull(heartRateData2);
                EntityDailyHeartRateData dailyHeartRateData = companion2.getDailyHeartRateData(heartRateData2);
                dailyHeartRateData.setSerialNo(getSerial_no());
                HeartRateDBWrite.getInstance(getContext()).insertDailyHeartRate(dailyHeartRateData);
                HeartRateDBWrite heartRateDBWrite = HeartRateDBWrite.getInstance(getContext());
                HeartRateDayData heartRateData3 = heartRateResponse.getHeartRateData();
                Intrinsics.checkNotNull(heartRateData3);
                heartRateDBWrite.inserHeartRateDataList(companion2.getHourlyHeartRateData(heartRateData3));
                if (heartRateResponse.isComplete()) {
                    LogHelper.d(this.d, "heartRateResponse isComplete = true", repositoryModuleNames.getModuleName());
                    a(ActivityType.HEARTRATE, bleApi);
                    aVar.onDataFetchSucess();
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof BpResponse) {
            Object responseData6 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData6, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BpResponse");
            BpResponse bpResponse = (BpResponse) responseData6;
            BleFormator.Companion companion3 = BleFormator.Companion;
            BpDayData bpDayData = bpResponse.getBpDayData();
            Intrinsics.checkNotNull(bpDayData);
            EntityDailyBp dailyBpData = companion3.getDailyBpData(bpDayData);
            dailyBpData.setSerialNo(getSerial_no());
            BpDBWrite.getInstance(getContext()).insert(dailyBpData);
            BpDBWrite bpDBWrite = BpDBWrite.getInstance(getContext());
            BpDayData bpDayData2 = bpResponse.getBpDayData();
            Intrinsics.checkNotNull(bpDayData2);
            bpDBWrite.insert(companion3.getHourlyBPData(bpDayData2));
            if (bpResponse.isComplete()) {
                LogHelper.d(this.d, "bpResponse isComplete = true", repositoryModuleNames.getModuleName());
                a(ActivityType.BP, bleApi);
                aVar.onDataFetchSucess();
            }
        } else if (bleBaseResponse.getResponseData() instanceof RrResponse) {
            Object responseData7 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData7, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.RrResponse");
            RrResponse rrResponse = (RrResponse) responseData7;
            BleFormator.Companion companion4 = BleFormator.Companion;
            RrDayData rrResponse2 = rrResponse.getRrResponse();
            Intrinsics.checkNotNull(rrResponse2);
            EntityDailyRrData dailyRrData = companion4.getDailyRrData(rrResponse2);
            dailyRrData.serial_no = getSerial_no();
            RrDataWrite.getInstance(getContext()).insertDailyRr(dailyRrData);
            RrDataWrite rrDataWrite = RrDataWrite.getInstance(getContext());
            RrDayData rrResponse3 = rrResponse.getRrResponse();
            Intrinsics.checkNotNull(rrResponse3);
            rrDataWrite.insertHourlyRrDataList(companion4.getHourlyRrData(rrResponse3));
            if (rrResponse.isComplete()) {
                LogHelper.d(this.d, "rrResponse isComplete = true", repositoryModuleNames.getModuleName());
                a(ActivityType.RR_HISTORY, bleApi);
                aVar.onDataFetchSucess();
            }
        } else if (bleBaseResponse.getResponseData() instanceof TemperatureResponse) {
            LogHelper.d(this.d, "processBleResponse is Called for TemperatureResponse", repositoryModuleNames.getModuleName());
            Object responseData8 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData8, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.TemperatureResponse");
            TemperatureResponse temperatureResponse = (TemperatureResponse) responseData8;
            if (temperatureResponse.getTemperatureData() != null) {
                String str6 = this.d;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("temperatureResponse Date ");
                TemperatureDayData temperatureData = temperatureResponse.getTemperatureData();
                Intrinsics.checkNotNull(temperatureData);
                sb4.append(temperatureData.getmDate());
                LogHelper.d(str6, sb4.toString(), repositoryModuleNames.getModuleName());
                BleFormator.Companion companion5 = BleFormator.Companion;
                TemperatureDayData temperatureData2 = temperatureResponse.getTemperatureData();
                Intrinsics.checkNotNull(temperatureData2);
                DailyTemperature dailyTemperatureData = companion5.getDailyTemperatureData(temperatureData2);
                dailyTemperatureData.mac_address = getSerial_no();
                TemperatureDBWrite.getInstance(getContext()).insertTemperature(dailyTemperatureData);
                TemperatureDBWrite temperatureDBWrite = TemperatureDBWrite.getInstance(getContext());
                TemperatureDayData temperatureData3 = temperatureResponse.getTemperatureData();
                Intrinsics.checkNotNull(temperatureData3);
                temperatureDBWrite.insertTemperatureHourlyList(companion5.getHourlyTemperatureData(temperatureData3));
                LogHelper.d(this.d, "processBleResponse DB insertion completed", repositoryModuleNames.getModuleName());
                if (temperatureResponse.isComplete()) {
                    LogHelper.d(this.d, "temperatureResponse isComplete = true", repositoryModuleNames.getModuleName());
                    a(ActivityType.TEMPERATURE, bleApi);
                    LogHelper.d(this.d, "processBleResponse temperatureResponse.isComplete", repositoryModuleNames.getModuleName());
                    aVar.onDataFetchSucess();
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof PeriodicSpo2Response) {
            LogHelper.d(this.d, "processBleResponse is Called for spo2res", repositoryModuleNames.getModuleName());
            Object responseData9 = bleBaseResponse.getResponseData();
            Intrinsics.checkNotNull(responseData9, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.PeriodicSpo2Response");
            PeriodicSpo2Response periodicSpo2Response = (PeriodicSpo2Response) responseData9;
            if (periodicSpo2Response.getSpo2DayData() != null) {
                String str7 = this.d;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("spo2Response Date ");
                Spo2DayData spo2DayData = periodicSpo2Response.getSpo2DayData();
                Intrinsics.checkNotNull(spo2DayData);
                sb5.append(spo2DayData.getmDate());
                LogHelper.d(str7, sb5.toString(), repositoryModuleNames.getModuleName());
                BleFormator.Companion companion6 = BleFormator.Companion;
                Spo2DayData spo2DayData2 = periodicSpo2Response.getSpo2DayData();
                Intrinsics.checkNotNull(spo2DayData2);
                DailyPeriodicSpo2 dailySpo2Data = companion6.getDailySpo2Data(spo2DayData2);
                dailySpo2Data.mac_address = getSerial_no();
                PeriodicSpo2DBWrite.getInstance(getContext()).insertPeriodicSpo2(dailySpo2Data);
                PeriodicSpo2DBWrite periodicSpo2DBWrite = PeriodicSpo2DBWrite.getInstance(getContext());
                Spo2DayData spo2DayData3 = periodicSpo2Response.getSpo2DayData();
                Intrinsics.checkNotNull(spo2DayData3);
                periodicSpo2DBWrite.insertSpo2HourlyList(companion6.getHourlySpo2Data(spo2DayData3));
                LogHelper.d(this.d, "processBleResponse DB insertion completed", repositoryModuleNames.getModuleName());
                if (periodicSpo2Response.isComplete()) {
                    LogHelper.d(this.d, "spo2 isComplete = true", repositoryModuleNames.getModuleName());
                    a(ActivityType.SPO2, bleApi);
                    LogHelper.d(this.d, "processBleResponse spo2.isComplete", repositoryModuleNames.getModuleName());
                    aVar.onDataFetchSucess();
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof StressResponse) {
            LogHelper.d(this.d, "processBleResponse is Called for StressResponse", repositoryModuleNames.getModuleName());
            StressResponse stressResponse = (StressResponse) bleBaseResponse.getResponseData();
            if (stressResponse != null) {
                if (stressResponse.getStressDayData() != null) {
                    String str8 = this.d;
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("Stress Date ");
                    StressDayData stressDayData = stressResponse.getStressDayData();
                    Intrinsics.checkNotNull(stressDayData);
                    sb6.append(stressDayData.getmDate());
                    LogHelper.d(str8, sb6.toString(), repositoryModuleNames.getModuleName());
                    BleFormator.Companion companion7 = BleFormator.Companion;
                    StressDayData stressDayData2 = stressResponse.getStressDayData();
                    Intrinsics.checkNotNull(stressDayData2);
                    DailyStress dailyStressData = companion7.getDailyStressData(stressDayData2);
                    StressDBRead stressDBRead = StressDBRead.getInstance(getContext());
                    StressDayData stressDayData3 = stressResponse.getStressDayData();
                    Intrinsics.checkNotNull(stressDayData3);
                    DailyStress dailyStressData2 = stressDBRead.getDailyStressData(stressDayData3.mDate, getSerial_no());
                    if (dailyStressData2 != null) {
                        dailyStressData.AnsweredQuestions_FeedBack = dailyStressData2.AnsweredQuestions_FeedBack;
                    }
                    dailyStressData.mac_address = getSerial_no();
                    StressDBWrite.getInstance(getContext()).insertStress(dailyStressData);
                    StressDBWrite stressDBWrite = StressDBWrite.getInstance(getContext());
                    StressDayData stressDayData4 = stressResponse.getStressDayData();
                    Intrinsics.checkNotNull(stressDayData4);
                    stressDBWrite.insertStressHourlyList(companion7.getHourlyStressData(stressDayData4));
                    LogHelper.d(this.d, "processBleResponse DB insertion completed", repositoryModuleNames.getModuleName());
                }
                if (stressResponse.getHrvDayData() != null) {
                    String str9 = this.d;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("HRV Date ");
                    HRVDayData hrvDayData = stressResponse.getHrvDayData();
                    Intrinsics.checkNotNull(hrvDayData);
                    sb7.append(hrvDayData.getmDate());
                    LogHelper.d(str9, sb7.toString(), repositoryModuleNames.getModuleName());
                    BleFormator.Companion companion8 = BleFormator.Companion;
                    HRVDayData hrvDayData2 = stressResponse.getHrvDayData();
                    Intrinsics.checkNotNull(hrvDayData2);
                    DailyHRV dailyHRVData = companion8.getDailyHRVData(hrvDayData2);
                    dailyHRVData.mac_address = getSerial_no();
                    HRVDBWrite.getInstance(getContext()).insert(dailyHRVData);
                    HRVDBWrite hRVDBWrite = HRVDBWrite.getInstance(getContext());
                    HRVDayData hrvDayData3 = stressResponse.getHrvDayData();
                    Intrinsics.checkNotNull(hrvDayData3);
                    hRVDBWrite.insertHourlyList(companion8.getHourlyHRVData(hrvDayData3));
                    LogHelper.d(this.d, "processBleResponse DB insertion completed", repositoryModuleNames.getModuleName());
                }
                if (stressResponse.isComplete()) {
                    LogHelper.d(this.d, "stressResponse isComplete = true", repositoryModuleNames.getModuleName());
                    a(ActivityType.STRESS, bleApi);
                    LogHelper.d(this.d, "stressResponse temperatureResponse.isComplete", repositoryModuleNames.getModuleName());
                    aVar.onDataFetchSucess();
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else if (bleBaseResponse.getResponseData() instanceof AmbientSoundResponse) {
            LogHelper.d(this.d, "processBleResponse is Called for AmbientSoundResponse", repositoryModuleNames.getModuleName());
            AmbientSoundResponse ambientSoundResponse = (AmbientSoundResponse) bleBaseResponse.getResponseData();
            if ((ambientSoundResponse != null ? ambientSoundResponse.getAmbientSoundDayData() : null) != null) {
                BleFormator.Companion companion9 = BleFormator.Companion;
                AmbientSoundDayData ambientSoundDayData = ambientSoundResponse.getAmbientSoundDayData();
                Intrinsics.checkNotNull(ambientSoundDayData);
                EntityDailyAmbientSoundData dailyAmbientSoundData = companion9.getDailyAmbientSoundData(ambientSoundDayData);
                dailyAmbientSoundData.setSerialNo(getSerial_no());
                AmbientSoundDBWrite.getInstance(getContext()).insertDailyAmbientSound(dailyAmbientSoundData);
                AmbientSoundDBWrite ambientSoundDBWrite = AmbientSoundDBWrite.getInstance(getContext());
                AmbientSoundDayData ambientSoundDayData2 = ambientSoundResponse.getAmbientSoundDayData();
                Intrinsics.checkNotNull(ambientSoundDayData2);
                ambientSoundDBWrite.insertHourlyAmbientSoundDataList(companion9.getHourlyAmbientSoundData(ambientSoundDayData2));
                LogHelper.d(this.d, "processBleResponse DB insertion completed", repositoryModuleNames.getModuleName());
                if (ambientSoundResponse.isComplete()) {
                    LogHelper.d(this.d, "Ambient Sound Response isComplete", repositoryModuleNames.getModuleName());
                    a(ActivityType.AMBIENT_SOUND, bleApi);
                    aVar.onDataFetchSucess();
                    return;
                }
                return;
            }
            aVar.onDataFetchSucess();
        } else {
            aVar.onDataFetchSucess();
        }
    }

    public static final void a(BleSyncUtils this$0, SleepResponse sleepResponse, a listener, BleApi bleApi) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sleepResponse, "$sleepResponse");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(bleApi, "$bleApi");
        this$0.a(sleepResponse, listener, bleApi);
    }

    public final void a(SyncResultListner syncResultListner, BleApi bleApi, SyncCompleteListner syncCompleteListner) {
        Integer avgValueFromList;
        Integer avgValueFromList2;
        int i = -1;
        for (SessionHR sessionHR : KHJstyleHRRepository.Companion.getInstance(getContext()).getSessionHeartrateDataList(getSerial_no())) {
            List split$default = StringsKt__StringsKt.split$default((CharSequence) b(sessionHR.getHrTimeStamp()), new String[]{":"}, false, 0, 6, (Object) null);
            int i2 = 0;
            int parseInt = Integer.parseInt((String) split$default.get(0));
            int parseInt2 = Integer.parseInt((String) split$default.get(0)) + 1;
            if (parseInt != i) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(sessionHR.getHrTimeStamp());
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeInMillis(sessionHR.getHrTimeStamp());
                calendar2.set(12, calendar2.getActualMaximum(12));
                List<SessionHR> hRDataListBetweenTime = KHJstyleHRRepository.Companion.getInstance(getContext()).getHRDataListBetweenTime(calendar.getTimeInMillis(), calendar2.getTimeInMillis(), getSerial_no());
                Intrinsics.checkNotNull(hRDataListBetweenTime, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.khjstyledb.heartrate.model.SessionHR>");
                ArrayList arrayList = (ArrayList) hRDataListBetweenTime;
                String str = parseInt + ":00:00";
                String str2 = parseInt2 + ":00:00";
                EntityHourlyHeartRateData hourlyHeartRateDate = HeartRateDBRead.getInstance(getContext()).getHourlyHeartRateDate(a(sessionHR.getHrTimeStamp()), getSerial_no(), str, str2);
                if (hourlyHeartRateDate == null) {
                    EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                    ArrayList<Integer> arrayList2 = new ArrayList<>(Collections.nCopies(60, 0));
                    int size = arrayList.size();
                    while (i2 < size) {
                        arrayList = arrayList;
                        arrayList2.set(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) b(((SessionHR) arrayList.get(i2)).getHrTimeStamp()), new String[]{":"}, false, 0, 6, (Object) null).get(1)), Integer.valueOf(((SessionHR) arrayList.get(i2)).getHrValue()));
                        i2++;
                    }
                    entityHourlyHeartRateData.setDate(a(sessionHR.getHrTimeStamp()));
                    entityHourlyHeartRateData.setEndTime(str2);
                    entityHourlyHeartRateData.setStartTime(str);
                    entityHourlyHeartRateData.serial_no = getSerial_no();
                    entityHourlyHeartRateData.setCodedValues(arrayList2);
                    Integer minValueFromList = JStyleUtils.getMinValueFromList(entityHourlyHeartRateData.getCodedValues());
                    Intrinsics.checkNotNull(minValueFromList);
                    entityHourlyHeartRateData.setMinHeartRate(minValueFromList.intValue());
                    Integer maxValueFromList = JStyleUtils.getMaxValueFromList(entityHourlyHeartRateData.getCodedValues());
                    Intrinsics.checkNotNull(maxValueFromList);
                    entityHourlyHeartRateData.setMaxHeartRate(maxValueFromList.intValue());
                    Intrinsics.checkNotNull(JStyleUtils.getAvgValueFromList(entityHourlyHeartRateData.getCodedValues()));
                    entityHourlyHeartRateData.setAvgHeartRate(avgValueFromList2.intValue());
                    int insertHeartRate = (int) HeartRateDBWrite.getInstance(getContext()).insertHeartRate(entityHourlyHeartRateData);
                    if (insertHeartRate != -1) {
                        a(a(sessionHR.getHrTimeStamp()), getSerial_no());
                    }
                    LogHelper.d("BleSynUtils", "inserthrdata " + insertHeartRate);
                } else {
                    int size2 = arrayList.size();
                    while (i2 < size2) {
                        hourlyHeartRateDate.getCodedValues().set(Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) b(((SessionHR) arrayList.get(i2)).getHrTimeStamp()), new String[]{":"}, false, 0, 6, (Object) null).get(1)), Integer.valueOf(((SessionHR) arrayList.get(i2)).getHrValue()));
                        i2++;
                    }
                    Integer minValueFromList2 = JStyleUtils.getMinValueFromList(hourlyHeartRateDate.getCodedValues());
                    Intrinsics.checkNotNull(minValueFromList2);
                    hourlyHeartRateDate.setMinHeartRate(minValueFromList2.intValue());
                    Integer maxValueFromList2 = JStyleUtils.getMaxValueFromList(hourlyHeartRateDate.getCodedValues());
                    Intrinsics.checkNotNull(maxValueFromList2);
                    hourlyHeartRateDate.setMaxHeartRate(maxValueFromList2.intValue());
                    Intrinsics.checkNotNull(JStyleUtils.getAvgValueFromList(hourlyHeartRateDate.getCodedValues()));
                    hourlyHeartRateDate.setAvgHeartRate(avgValueFromList.intValue());
                    int updateHrData = HeartRateDBWrite.getInstance(getContext()).updateHrData(hourlyHeartRateDate);
                    if (updateHrData != -1) {
                        a(a(sessionHR.getHrTimeStamp()), getSerial_no());
                    }
                    LogHelper.d("BleSynUtils", "updatehrdata " + updateHrData);
                }
                i = parseInt;
            }
        }
        if (bleApi.getDeviceSupportedFeatures().isManualSpo2SupportedOnBand()) {
            syncManualSpo2Data(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isManualStressMeasurementSupported()) {
            syncManualStressData(syncResultListner, bleApi, syncCompleteListner, null, null);
        } else if (bleApi.getDeviceSupportedFeatures().isManualHRVMeasurementSupported()) {
            syncManualHRVData(syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
            syncDistanceData(null, null, syncResultListner, bleApi, syncCompleteListner);
        } else if (bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
            syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
        } else {
            b(syncResultListner);
        }
    }

    public final void a(String str, String str2) {
        List<EntityHourlyHeartRateData> hourlyHeartRateData = HeartRateDBRead.getInstance(getContext()).getHourlyHeartRateData(str, str2);
        LogHelper.d("BleSynUtils", "hrdatadate*** " + hourlyHeartRateData.size() + " date ** " + str);
        EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int size = hourlyHeartRateData.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Integer.valueOf(hourlyHeartRateData.get(i).getMaxHeartRate()));
            arrayList2.add(Integer.valueOf(hourlyHeartRateData.get(i).getMinHeartRate()));
            arrayList3.add(Integer.valueOf((int) hourlyHeartRateData.get(i).getAvgHeartRate()));
        }
        entityDailyHeartRateData.setDate(str);
        entityDailyHeartRateData.setSerialNo(str2);
        Integer maxValueFromList = JStyleUtils.getMaxValueFromList(arrayList);
        Intrinsics.checkNotNull(maxValueFromList);
        entityDailyHeartRateData.setMaxHeartRate(maxValueFromList.intValue());
        Integer minValueFromList = JStyleUtils.getMinValueFromList(arrayList2);
        Intrinsics.checkNotNull(minValueFromList);
        entityDailyHeartRateData.setMinHeartRate(minValueFromList.intValue());
        Integer avgValueFromList = JStyleUtils.getAvgValueFromList(arrayList3);
        Intrinsics.checkNotNull(avgValueFromList);
        entityDailyHeartRateData.setAvgHeartRate(avgValueFromList.intValue());
        HeartRateDBWrite.getInstance(getContext()).insertDailyHeartRate(entityDailyHeartRateData);
    }

    public final String a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String time = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(time, "time");
        return time;
    }

    public final void a(SleepResponse sleepResponse, a aVar, BleApi bleApi) {
        BleFormator.Companion companion = BleFormator.Companion;
        SleepDayData sleepDayData = sleepResponse.getSleepDayData();
        Intrinsics.checkNotNull(sleepDayData);
        DailySleepData dailySleepData = companion.getDailySleepData(sleepDayData);
        if (ProfileDBRead.getInstance(getContext()).getLatestProfileData() != null) {
            dailySleepData.setSleepTarget(ProfileDBRead.getInstance(getContext()).getLatestProfileData().sleepTarget);
        }
        dailySleepData.setMacAddress(getSerial_no());
        SleepDBWrite.getInstance(getContext()).insertDailySleepData(dailySleepData);
        SleepDBWrite sleepDBWrite = SleepDBWrite.getInstance(getContext());
        SleepDayData sleepDayData2 = sleepResponse.getSleepDayData();
        Intrinsics.checkNotNull(sleepDayData2);
        sleepDBWrite.insertHourlySleepData(companion.getHourlySleepData(sleepDayData2));
        if (sleepResponse.isComplete()) {
            aVar.onDataFetchSucess();
            a(ActivityType.SLEEP, bleApi);
        }
    }

    public final void a(ActivityType activityType, BleApi bleApi) {
        String macAddress = bleApi.getMacAddress();
        EntityDeviceInfo deviceInfoBy = DeviceInfoRepository.getInstance(getContext()).getDeviceInfoBy(macAddress);
        if (deviceInfoBy == null) {
            deviceInfoBy = new EntityDeviceInfo();
            deviceInfoBy.setMacAddress(macAddress);
        }
        deviceInfoBy.setActive(true);
        if (activityType == ActivityType.WALK) {
            deviceInfoBy.setLastSyncDateWalk(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.DISTANCE) {
            deviceInfoBy.setLastSyncDateDistanceData(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.CALORIE) {
            deviceInfoBy.setLastSyncDateCalorieData(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.SLEEP) {
            deviceInfoBy.setLastSyncDateSleep(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.HEARTRATE) {
            deviceInfoBy.setLastSyncDateHr(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.BP) {
            deviceInfoBy.setLastSyncDateBp(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.RR_HISTORY) {
            deviceInfoBy.setLastSyncDateRr(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.TEMPERATURE) {
            deviceInfoBy.setLastSyncDateTemperature(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.SPO2) {
            deviceInfoBy.setLastSyncDateSpo2(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.RAW_PPG) {
            deviceInfoBy.setLastSyncDateRawPPG(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.SEDENTARY) {
            deviceInfoBy.setLastSyncDateSedentary(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.AMBIENT_SOUND) {
            deviceInfoBy.setLastSyncDateAmbientSound(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        if (activityType == ActivityType.SENS_AI) {
            deviceInfoBy.setLastSyncDateSensAISummary(RepositoryUtils.formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd"));
        }
        deviceInfoBy.setLasySyncTime(System.currentTimeMillis());
        DeviceInfoRepository.getInstance(getContext()).insertDeviceInfo(deviceInfoBy);
    }

    public final void a(LinkedHashMap<String, String> linkedHashMap, Bitmap bitmap, Bitmap bitmap2, final ECGResultResponse eCGResultResponse, final SyncResultListner syncResultListner, final BleApi bleApi) {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        final String str = uuid + ".png";
        try {
            Context context = getContext();
            String imagePath = getImagePath();
            List<Integer> list = this.b;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.f7321a, Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            String format = simpleDateFormat.format(Calendar.getInstance().getTime());
            Intrinsics.checkNotNull(format);
            final boolean generateImageReport = EcgStyleReportUtil.generateImageReport(context, imagePath, str, list, format, FileUtil.Format.PNG, linkedHashMap, bitmap, bitmap2);
            this.e.postDelayed(new Runnable() { // from class: com.coveiot.repository.datasync.c
                @Override // java.lang.Runnable
                public final void run() {
                    BleSyncUtils.a(generateImageReport, this, str, eCGResultResponse, bleApi, syncResultListner);
                }
            }, 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void a(boolean z, final BleSyncUtils this$0, String fileName, final ECGResultResponse eCGResultResponse, final BleApi bleApi, final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(fileName, "$fileName");
        Intrinsics.checkNotNullParameter(bleApi, "$bleApi");
        Intrinsics.checkNotNullParameter(syncResultListner, "$syncResultListner");
        if (z) {
            final File file = new File(this$0.getImagePath() + fileName);
            ServerSyncUtils.Companion.getInstance(this$0.getContext()).uploadECGFileToServer(file, new APIResponseListner() { // from class: com.coveiot.repository.datasync.BleSyncUtils$createFile$1$1
                @Override // com.coveiot.repository.datasync.domainlogic.APIResponseListner
                public void onFailure(@Nullable String str) {
                }

                @Override // com.coveiot.repository.datasync.domainlogic.APIResponseListner
                public void onSuccess(@NotNull Object result) {
                    Intrinsics.checkNotNullParameter(result, "result");
                    MediaUploadRes mediaUploadRes = (MediaUploadRes) result;
                    BleFormator.Companion companion = BleFormator.Companion;
                    ECGResultResponse eCGResultResponse2 = ECGResultResponse.this;
                    Intrinsics.checkNotNull(eCGResultResponse2);
                    EntityECGSummaryData eCGResultData = companion.getECGResultData(eCGResultResponse2);
                    eCGResultData.serial_no = bleApi.getMacAddress();
                    eCGResultData.session_id = UUID.randomUUID().toString();
                    eCGResultData.mediaID = mediaUploadRes.getMediaListBean().getMediaId();
                    eCGResultData.mediaURL = mediaUploadRes.getMediaListBean().getFileUrl();
                    ECGDBWrite.getInstance(this$0.getContext()).insertECGResultData(eCGResultData);
                    file.delete();
                    if (file.exists()) {
                        file.getCanonicalFile().delete();
                        if (file.exists()) {
                            this$0.getContext().deleteFile(file.getName());
                        }
                    }
                    this$0.a(syncResultListner);
                }
            });
        }
    }

    public final void a(final SyncResultListner syncResultListner) {
        this.e.postDelayed(new Runnable() { // from class: com.coveiot.repository.datasync.b
            @Override // java.lang.Runnable
            public final void run() {
                BleSyncUtils.a(BleSyncUtils.this, syncResultListner);
            }
        }, 3000L);
    }

    public static final void a(BleSyncUtils this$0, final SyncResultListner syncResultListner) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(syncResultListner, "$syncResultListner");
        ServerSyncUtils.Companion.getInstance(this$0.getContext()).saveECGSummaryToServer(this$0.c, new SyncResultListner() { // from class: com.coveiot.repository.datasync.BleSyncUtils$fetchFromDBAndPostToServer$1$1
            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onFailure(@NotNull Error message) {
                Intrinsics.checkNotNullParameter(message, "message");
                SyncResultListner.this.onFailure(message);
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
            }

            @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
            public void onSuccess() {
                SyncResultListner.this.onSuccess();
            }
        });
    }

    public final void a(ActivityType activityType, SyncResultListner syncResultListner, boolean z) {
        if (z) {
            if ((this.j.isEmpty() || !this.j.contains(activityType.name())) && this.i > 0) {
                this.j.add(activityType.name());
                int i = this.f;
                int i2 = this.i;
                int i3 = i + i2;
                this.f = i3;
                int i4 = this.h - 1;
                this.h = i4;
                int i5 = this.g;
                syncResultListner.onProgressUpdate(new ProgressDataBean(ProgressType.INDETERMINATE, i3, activityType, i5, i5 - i4, i2));
            }
        }
    }

    public final String a(String str, String str2, String str3, int i) {
        String str4 = PreferenceManager.getInstance().getUserDeviceID().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(PreferenceManager.getInstance().getUserId().intValue());
        sb.append(';');
        sb.append(str4);
        sb.append(';');
        String lowerCase = str3.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        sb.append(lowerCase);
        sb.append(';');
        sb.append(str);
        sb.append(';');
        sb.append(str2);
        sb.append(';');
        sb.append(i);
        return AppUtils.convertStringToMD5(sb.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x06e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.coveiot.repository.datasync.domainlogic.SyncResultListner r36, com.coveiot.android.bleabstract.api.BleApi r37) {
        /*
            Method dump skipped, instructions count: 1897
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.repository.datasync.BleSyncUtils.a(com.coveiot.repository.datasync.domainlogic.SyncResultListner, com.coveiot.android.bleabstract.api.BleApi):void");
    }

    public final void a(final BleApi bleApi, Date date, Date date2, final Date date3, final Date date4, final SyncResultListner syncResultListner, final SyncCompleteListner syncCompleteListner) {
        if (bleApi.getDeviceSupportedFeatures().isAmbientSoundLevelSupported()) {
            a(new AmbientSoundDataRequest.Builder().setStartDate(date).setEndDate(date2).build(), new a() { // from class: com.coveiot.repository.datasync.BleSyncUtils$syncAmbientSoundLevelData$1
                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchFailure(@NotNull Error error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    BleSyncUtils.access$syncFailure(BleSyncUtils.this, syncResultListner, error);
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onDataFetchSucess() {
                    String str;
                    BleSyncUtils.this.a(ActivityType.AMBIENT_SOUND, syncResultListner, true);
                    str = BleSyncUtils.this.d;
                    LogHelper.d(str, "onDataFetchSuccess Ambient sound Data success", RepositoryModuleNames.REPOSITORY.getModuleName());
                    if (RepositoryUtils.isIDODevice(BleSyncUtils.this.getContext())) {
                        BleSyncUtils.this.a();
                    }
                    if (bleApi.getDeviceSupportedFeatures().isDistanceAndCalorieDataFromBandSupported()) {
                        BleSyncUtils.this.syncDistanceData(date3, date4, syncResultListner, bleApi, syncCompleteListner);
                    } else if (!bleApi.getDeviceSupportedFeatures().isSensAISupported()) {
                        BleSyncUtils.this.b(syncResultListner);
                    } else {
                        BleSyncUtils.this.syncSensAISummaryData(syncResultListner, bleApi, syncCompleteListner);
                    }
                }

                @Override // com.coveiot.repository.datasync.BleSyncUtils.a
                public void onProgressUpdate(@NotNull ProgressDataBean progressDataBean) {
                    Intrinsics.checkNotNullParameter(progressDataBean, "progressDataBean");
                    BleSyncUtils.access$updateProgress(BleSyncUtils.this, syncResultListner, progressDataBean);
                }
            }, bleApi);
        } else {
            b(syncResultListner);
        }
    }
}
