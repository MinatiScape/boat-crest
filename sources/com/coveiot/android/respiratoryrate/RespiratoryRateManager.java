package com.coveiot.android.respiratoryrate;

import android.content.Context;
import android.text.format.DateUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ReadRawPPGHistoryDataRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.ReadRawPPGDataResponse;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity;
import com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.respiratoryrate.utils.Utils;
import com.coveiot.android.sleepalgorithm.filtering.SleepDataModel;
import com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedData;
import com.coveiot.coveaccess.fitnesscomputeddataapi.SaveFitnessComputedDataRequest;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiReq;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiRes;
import com.coveiot.coveaccess.respiratoryrate.model.PPGRecord;
import com.coveiot.coveaccess.respiratoryrate.model.UserInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.repository.sleep.SleepRepository;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class RespiratoryRateManager {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5658a = "RespiratoryRateManager";
    public static final int b = 60000;

    /* loaded from: classes6.dex */
    public static final class Companion {

        @DebugMetadata(c = "com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion", f = "RespiratoryRateManager.kt", i = {0, 0}, l = {102}, m = "getRawPPGFromWatchAndInsertComputedDataOnDb", n = {"this", "context"}, s = {"L$0", "L$1"})
        /* loaded from: classes6.dex */
        public static final class a extends ContinuationImpl {
            public Object L$0;
            public Object L$1;
            public int label;
            public /* synthetic */ Object result;

            public a(Continuation<? super a> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.g(null, this);
            }
        }

        @DebugMetadata(c = "com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion", f = "RespiratoryRateManager.kt", i = {1}, l = {93, 96}, m = "syncData", n = {"this"}, s = {"L$0"})
        /* loaded from: classes6.dex */
        public static final class b extends ContinuationImpl {
            public Object L$0;
            public int label;
            public /* synthetic */ Object result;

            public b(Continuation<? super b> continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return Companion.this.syncData(null, this);
            }
        }

        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(Context context) {
            DailyRespiratoryRawPPGHistoryEntity oldestDailyRawPPGData;
            RespiratoryRateRepository.Companion companion = RespiratoryRateRepository.Companion;
            int dailyRawPPGDataRowCount = companion.getInstance(context).getDailyRawPPGDataRowCount(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            String tag = getTAG();
            LogHelper.d(tag, "Raw PPG for days " + dailyRawPPGDataRowCount + " available");
            if (dailyRawPPGDataRowCount < 7 || (oldestDailyRawPPGData = companion.getInstance(context).getOldestDailyRawPPGData(BleApiManager.getInstance(context).getBleApi().getMacAddress())) == null) {
                return;
            }
            companion.getInstance(context).deleteHourlyRawPPGData(oldestDailyRawPPGData.getDate(), oldestDailyRawPPGData.getMacAddress());
            companion.getInstance(context).deleteDailyRawPPGData(oldestDailyRawPPGData.getDate(), oldestDailyRawPPGData.getMacAddress());
            String tag2 = RespiratoryRateManager.Companion.getTAG();
            LogHelper.d(tag2, "Delete data for " + oldestDailyRawPPGData.getDate() + " which is oldest");
        }

        public final Map<String, ArrayList<RawPPGHistoryData>> b(Context context) {
            Context context2 = context;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List<DailyRespiratoryRawPPGHistoryEntity> allDailyRawPPGData = RespiratoryRateRepository.Companion.getInstance(context2).getAllDailyRawPPGData(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            if (allDailyRawPPGData != null) {
                for (DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity : allDailyRawPPGData) {
                    ArrayList arrayList = new ArrayList();
                    List<HourlyRespiratoryRawPPGDataEntity> hourlyRawPPGData = RespiratoryRateRepository.Companion.getInstance(context2).getHourlyRawPPGData(dailyRespiratoryRawPPGHistoryEntity.getDate(), dailyRespiratoryRawPPGHistoryEntity.getMacAddress());
                    if (hourlyRawPPGData != null) {
                        for (HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity : hourlyRawPPGData) {
                            arrayList.add(new RawPPGHistoryData(hourlyRespiratoryRawPPGDataEntity.getRecordNumber(), hourlyRespiratoryRawPPGDataEntity.getTimestamp(), hourlyRespiratoryRawPPGDataEntity.getPpgValues(), hourlyRespiratoryRawPPGDataEntity.getSamplingRate(), hourlyRespiratoryRawPPGDataEntity.getPpgType(), hourlyRespiratoryRawPPGDataEntity.getInterval(), hourlyRespiratoryRawPPGDataEntity.getDuration(), hourlyRespiratoryRawPPGDataEntity.getMovementLevel()));
                        }
                    }
                    linkedHashMap.put(dailyRespiratoryRawPPGHistoryEntity.getDate(), arrayList);
                    context2 = context;
                }
            }
            return linkedHashMap;
        }

        public final String c(String str, Map<String, ArrayList<String>> map) {
            String str2 = null;
            for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
                ArrayList<String> value = entry.getValue();
                if (!(value == null || value.isEmpty())) {
                    ArrayList<String> value2 = entry.getValue();
                    Intrinsics.checkNotNull(value2);
                    Iterator<String> it = value2.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (Intrinsics.areEqual(it.next(), str)) {
                                str2 = entry.getKey();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            return str2;
        }

        public final Map<String, ArrayList<String>> d(Map<String, ArrayList<RawPPGHistoryData>> map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (!(map == null || map.isEmpty())) {
                for (Map.Entry<String, ArrayList<RawPPGHistoryData>> entry : map.entrySet()) {
                    ArrayList<RawPPGHistoryData> value = entry.getValue();
                    if (!(value == null || value.isEmpty())) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList<RawPPGHistoryData> value2 = entry.getValue();
                        Intrinsics.checkNotNull(value2);
                        Iterator<RawPPGHistoryData> it = value2.iterator();
                        while (it.hasNext()) {
                            RawPPGHistoryData next = it.next();
                            next.getTimeStamp();
                            if (next.getTimeStamp() > 0) {
                                arrayList.add(AppUtils.formatDateUTC(new Date(next.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
                            }
                        }
                        linkedHashMap.put(entry.getKey(), arrayList);
                    }
                }
            }
            return linkedHashMap;
        }

        public final int e(List<Integer> list, List<Float> list2, Float f) {
            int i = 0;
            boolean z = true;
            int i2 = -1;
            if (!(list == null || list.isEmpty())) {
                if (list2 != null && !list2.isEmpty()) {
                    z = false;
                }
                if (!z && list.size() == list2.size() && f != null) {
                    for (Object obj : list) {
                        int i3 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Integer num = (Integer) obj;
                        if (num != null && num.intValue() > 0 && list2.get(i) != null) {
                            Float f2 = list2.get(i);
                            Intrinsics.checkNotNull(f2);
                            if (f2.floatValue() >= f.floatValue()) {
                                if (i2 <= 0) {
                                    i2 = num.intValue();
                                } else if (i2 < num.intValue()) {
                                    i2 = num.intValue();
                                }
                            }
                        }
                        i = i3;
                    }
                }
            }
            return i2;
        }

        public final int f(List<Integer> list, List<Float> list2, Float f) {
            int i = 0;
            boolean z = true;
            int i2 = -1;
            if (!(list == null || list.isEmpty())) {
                if (list2 != null && !list2.isEmpty()) {
                    z = false;
                }
                if (!z && list.size() == list2.size() && f != null) {
                    for (Object obj : list) {
                        int i3 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        Integer num = (Integer) obj;
                        if (num != null && num.intValue() > 0 && list2.get(i) != null) {
                            Float f2 = list2.get(i);
                            Intrinsics.checkNotNull(f2);
                            if (f2.floatValue() >= f.floatValue()) {
                                if (i2 <= 0) {
                                    i2 = num.intValue();
                                } else if (i2 > num.intValue()) {
                                    i2 = num.intValue();
                                }
                            }
                        }
                        i = i3;
                    }
                }
            }
            return i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0079  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object g(final android.content.Context r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
            /*
                r6 = this;
                boolean r0 = r8 instanceof com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.a
                if (r0 == 0) goto L13
                r0 = r8
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$a r0 = (com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.a) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$a r0 = new com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$a
                r0.<init>(r8)
            L18:
                java.lang.Object r8 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L39
                if (r2 != r3) goto L31
                java.lang.Object r7 = r0.L$1
                android.content.Context r7 = (android.content.Context) r7
                java.lang.Object r0 = r0.L$0
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion r0 = (com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion) r0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L4c
            L31:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r8)
                throw r7
            L39:
                kotlin.ResultKt.throwOnFailure(r8)
                r4 = 4000(0xfa0, double:1.9763E-320)
                r0.L$0 = r6
                r0.L$1 = r7
                r0.label = r3
                java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r4, r0)
                if (r8 != r1) goto L4b
                return r1
            L4b:
                r0 = r6
            L4c:
                java.lang.String r8 = r0.getTAG()
                java.lang.String r1 = "getRawPPGFromWatchAndInsertComputedDataOnDb called"
                com.coveiot.utils.utility.LogHelper.d(r8, r1)
                java.util.Calendar r8 = java.util.Calendar.getInstance()
                com.coveiot.covepreferences.UserDataManager r1 = com.coveiot.covepreferences.UserDataManager.getInstance(r7)
                com.coveiot.android.bleabstract.api.BleApiManager r2 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r7)
                com.coveiot.android.bleabstract.api.BleApi r2 = r2.getBleApi()
                java.lang.String r2 = r2.getMacAddress()
                com.coveiot.covepreferences.data.LastNightSleepData r1 = r1.getLastNightSleepData(r2)
                com.coveiot.android.respiratoryrate.utils.Constants r2 = com.coveiot.android.respiratoryrate.utils.Constants.SYNC_START_TIME
                java.lang.String r2 = r2.getValue()
                int r2 = java.lang.Integer.parseInt(r2)
                if (r1 == 0) goto L86
                r1.getEndHour()
                int r3 = r1.getEndHour()
                if (r3 >= r2) goto L86
                int r2 = r1.getEndHour()
            L86:
                r1 = 11
                int r3 = r8.get(r1)
                com.coveiot.android.respiratoryrate.utils.Constants r4 = com.coveiot.android.respiratoryrate.utils.Constants.SYNC_END_TIME
                java.lang.String r4 = r4.getValue()
                int r4 = java.lang.Integer.parseInt(r4)
                if (r3 > r4) goto Lb7
                int r8 = r8.get(r1)
                if (r8 < r2) goto Lb7
                boolean r8 = r0.q(r7)
                if (r8 != 0) goto Lad
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2 r8 = new com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2
                r8.<init>()
                r0.s(r7, r8)
                goto Lc0
            Lad:
                java.lang.String r7 = r0.getTAG()
                java.lang.String r8 = "data already synced from watch till today"
                com.coveiot.utils.utility.LogHelper.d(r7, r8)
                goto Lc0
            Lb7:
                java.lang.String r7 = r0.getTAG()
                java.lang.String r8 = "Can not sync from watch between ppg start and end time."
                com.coveiot.utils.utility.LogHelper.d(r7, r8)
            Lc0:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.g(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public final int getRAW_PPG_HISTORY_COMMAND_TIMEOUT() {
            return RespiratoryRateManager.b;
        }

        @NotNull
        public final String getTAG() {
            return RespiratoryRateManager.f5658a;
        }

        public final RespiratoryRateApiReq h(Context context, ArrayList<RawPPGHistoryData> arrayList) {
            ProfileData userDetails;
            String upperCase;
            boolean z = false;
            if ((arrayList == null || arrayList.isEmpty()) || (userDetails = SessionManager.getInstance(context).getUserDetails()) == null) {
                return null;
            }
            RespiratoryRateApiReq respiratoryRateApiReq = new RespiratoryRateApiReq();
            if (Utils.isCADevice(context)) {
                respiratoryRateApiReq.setDeviceType("CA");
            } else if (Utils.isCYDevice(context)) {
                respiratoryRateApiReq.setDeviceType("CY");
            } else if (Utils.isCZDevice(context)) {
                respiratoryRateApiReq.setDeviceType("CZ");
            }
            respiratoryRateApiReq.setUserInfo(new UserInfo());
            int parseInt = Integer.parseInt(Constants.DEFAULT_AGE.getValue());
            String dob = userDetails.getDob();
            if (dob == null || dob.length() == 0) {
                z = true;
            }
            if (!z) {
                parseInt = AppUtils.getAge(userDetails.getDob());
            }
            respiratoryRateApiReq.getUserInfo().setAge(Integer.valueOf(parseInt));
            UserInfo userInfo = respiratoryRateApiReq.getUserInfo();
            if (!AppUtils.isEmpty(userDetails.getGender())) {
                String gender = userDetails.getGender();
                Intrinsics.checkNotNullExpressionValue(gender, "profileData.gender");
                upperCase = gender.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            } else {
                String string = context.getResources().getString(R.string.male);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.male)");
                upperCase = string.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            }
            userInfo.setGender(upperCase);
            UserInfo userInfo2 = respiratoryRateApiReq.getUserInfo();
            String height = userDetails.getHeight();
            Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
            userInfo2.setHeight(Integer.valueOf(Integer.parseInt(height)));
            UserInfo userInfo3 = respiratoryRateApiReq.getUserInfo();
            String weight = userDetails.getWeight();
            Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
            userInfo3.setWeight(Integer.valueOf(Integer.parseInt(weight)));
            Iterator<RawPPGHistoryData> it = arrayList.iterator();
            while (it.hasNext()) {
                RawPPGHistoryData next = it.next();
                PPGRecord pPGRecord = new PPGRecord();
                pPGRecord.setTime(AppUtils.formatDateUTC(new Date(next.getTimeStamp()), UtilConstants.SERVER_TIME_FORMAT));
                List<Integer> ppgData = pPGRecord.getPpgData();
                ArrayList<Integer> ppgData2 = next.getPpgData();
                Intrinsics.checkNotNullExpressionValue(ppgData2, "rawPPG.ppgData");
                ppgData.addAll(ppgData2);
                pPGRecord.setPpgType(next.getPpgType());
                pPGRecord.setDurationSeconds(next.getDuration());
                respiratoryRateApiReq.getPpgRecords().add(pPGRecord);
            }
            return respiratoryRateApiReq;
        }

        public final SaveFitnessComputedDataRequest i(Context context, RespiratoryRateApiRes respiratoryRateApiRes, Map<String, ArrayList<String>> map) {
            SaveFitnessComputedDataRequest saveFitnessComputedDataRequest = new SaveFitnessComputedDataRequest();
            List<RespiratoryRateApiRes.Data> data = respiratoryRateApiRes.getData();
            if (!(data == null || data.isEmpty())) {
                saveFitnessComputedDataRequest.setFitnessDataComputed(new ArrayList());
                List<RespiratoryRateApiRes.Data> data2 = respiratoryRateApiRes.getData();
                Intrinsics.checkNotNullExpressionValue(data2, "respiratoryRateApiRes.data");
                for (Map.Entry<String, List<RespiratoryRateApiRes.Data>> entry : r(data2, map).entrySet()) {
                    FitnessComputedData fitnessComputedData = new FitnessComputedData();
                    fitnessComputedData.date = entry.getKey();
                    fitnessComputedData.tzOffset = Utils.getCurrentTimezoneOffset();
                    String algoIdentifier = respiratoryRateApiRes.getAlgoIdentifier();
                    Intrinsics.checkNotNullExpressionValue(algoIdentifier, "respiratoryRateApiRes.algoIdentifier");
                    String computedDate = respiratoryRateApiRes.getComputedDate();
                    Intrinsics.checkNotNullExpressionValue(computedDate, "respiratoryRateApiRes.computedDate");
                    fitnessComputedData.respiratoryRate = RespiratoryRateManager.Companion.j(context, entry.getKey(), entry.getValue(), algoIdentifier, computedDate);
                    saveFitnessComputedDataRequest.getFitnessDataComputed().add(fitnessComputedData);
                }
            }
            return saveFitnessComputedDataRequest;
        }

        public final FitnessComputedData.RespiratoryRate j(Context context, String str, List<RespiratoryRateApiRes.Data> list, String str2, String str3) {
            RespiratoryRateRemoteConfiguration.Computation computation;
            RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(context).getRespiratoryRateRemoteConfig();
            Float valueOf = (respiratoryRateRemoteConfig == null || (computation = respiratoryRateRemoteConfig.getComputation()) == null) ? null : Float.valueOf(computation.getConfidenceLevelThreshold());
            FitnessComputedData.RespiratoryRate respiratoryRate = new FitnessComputedData.RespiratoryRate();
            respiratoryRate.setComputedDate(str3);
            respiratoryRate.setBaseUnit(Constants.BASE_UNIT_BRPM.getValue());
            FitnessComputedData.RespiratoryRate.Source source = new FitnessComputedData.RespiratoryRate.Source();
            source.setType(Constants.AIML_ALGO.getValue());
            source.setIdentifier(str2);
            respiratoryRate.setSource(source);
            FitnessComputedData.RespiratoryRate.BaseUnits baseUnits = new FitnessComputedData.RespiratoryRate.BaseUnits();
            baseUnits.setAccuracy(Constants.PERCENTAGE.getValue());
            respiratoryRate.setBaseUnits(baseUnits);
            Pair<ArrayList<Integer>, ArrayList<Float>> n = n(context, str, list);
            respiratoryRate.setValues(n.getFirst());
            respiratoryRate.setAccuracies(n.getSecond());
            respiratoryRate.setMin(f(respiratoryRate.getValues(), respiratoryRate.getAccuracies(), valueOf));
            respiratoryRate.setMax(e(respiratoryRate.getValues(), respiratoryRate.getAccuracies(), valueOf));
            return respiratoryRate;
        }

        public final SleepDataModel k(Context context, Calendar calendar) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(calendar2, "calendar");
            return Utils.INSTANCE.getLastNightSleepData(context, SleepRepository.Companion.getInstance(context).getLastNignthSleepDataWithOutLiveData(calendar2, BleApiManager.getInstance(context).getBleApi().getMacAddress()));
        }

        public final List<Integer> l(int i, int i2, int i3, int i4) {
            ArrayList arrayList = new ArrayList();
            if (i2 > 0) {
                i++;
            }
            if (i == 24) {
                i = 0;
            }
            int i5 = i3 + 1;
            if (i5 == 24) {
                i5 = 0;
            }
            while (i != i5) {
                arrayList.add(Integer.valueOf(i));
                i++;
                if (i == 24) {
                    i = 0;
                }
            }
            return arrayList;
        }

        public final Pair<Integer, Float> m(List<Integer> list, int i, List<RespiratoryRateApiRes.Data> list2) {
            Integer num;
            Float f;
            Iterator<RespiratoryRateApiRes.Data> it = list2.iterator();
            while (true) {
                num = null;
                if (!it.hasNext()) {
                    f = null;
                    break;
                }
                RespiratoryRateApiRes.Data next = it.next();
                Date parseDateUTC = AppUtils.parseDateUTC(next.getTime(), UtilConstants.SERVER_TIME_FORMAT);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(parseDateUTC);
                int i2 = calendar.get(11);
                if (i == i2) {
                    boolean z = false;
                    Iterator<Integer> it2 = list.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (i2 == it2.next().intValue()) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        num = Integer.valueOf(next.getRespiratoryRate());
                        f = Float.valueOf(next.getConfidence());
                        break;
                    }
                }
            }
            return new Pair<>(num, f);
        }

        public final Pair<ArrayList<Integer>, ArrayList<Float>> n(Context context, String str, List<RespiratoryRateApiRes.Data> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Calendar cal = Calendar.getInstance();
            cal.setTime(AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(str));
            Intrinsics.checkNotNullExpressionValue(cal, "cal");
            SleepDataModel k = k(context, cal);
            List<Integer> arrayList3 = new ArrayList<>();
            if (k != null) {
                k.getSleepStartHour();
                k.getSleepEndHour();
                arrayList3 = l(k.getSleepStartHour(), k.getSleepStartMinute(), k.getSleepEndHour(), k.getSleepEndMinute());
            }
            for (int i = 0; i < 24; i++) {
                Pair<Integer, Float> m = m(arrayList3, i, list);
                arrayList.add(i, m.getFirst());
                arrayList2.add(i, m.getSecond());
            }
            return new Pair<>(arrayList, arrayList2);
        }

        public final void o(Context context, List<FitnessComputedData> list) {
            RespiratoryRateRemoteConfiguration.Computation computation;
            if (list == null || list.isEmpty()) {
                return;
            }
            RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfig = SessionManager.getInstance(context).getRespiratoryRateRemoteConfig();
            Float valueOf = (respiratoryRateRemoteConfig == null || (computation = respiratoryRateRemoteConfig.getComputation()) == null) ? null : Float.valueOf(computation.getConfidenceLevelThreshold());
            for (FitnessComputedData fitnessComputedData : list) {
                if (fitnessComputedData.respiratoryRate != null) {
                    DailyRespiratoryRateEntity dailyRespiratoryRateEntity = new DailyRespiratoryRateEntity();
                    String str = fitnessComputedData.date;
                    Intrinsics.checkNotNullExpressionValue(str, "fd.date");
                    dailyRespiratoryRateEntity.setMDate(str);
                    dailyRespiratoryRateEntity.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                    RespiratoryRateData respiratoryRateData = new RespiratoryRateData();
                    respiratoryRateData.setDate(fitnessComputedData.date);
                    respiratoryRateData.setValues(fitnessComputedData.respiratoryRate.getValues());
                    respiratoryRateData.setAccuracies(fitnessComputedData.respiratoryRate.getAccuracies());
                    respiratoryRateData.setComputedDate(fitnessComputedData.respiratoryRate.getComputedDate());
                    respiratoryRateData.setMax(Integer.valueOf(e(fitnessComputedData.respiratoryRate.getValues(), fitnessComputedData.respiratoryRate.getAccuracies(), valueOf)));
                    respiratoryRateData.setMin(Integer.valueOf(f(fitnessComputedData.respiratoryRate.getValues(), fitnessComputedData.respiratoryRate.getAccuracies(), valueOf)));
                    fitnessComputedData.respiratoryRate.getMax();
                    fitnessComputedData.respiratoryRate.getMin();
                    respiratoryRateData.setAvg(Integer.valueOf((fitnessComputedData.respiratoryRate.getMin() + fitnessComputedData.respiratoryRate.getMax()) / 2));
                    respiratoryRateData.setBaseUnit(fitnessComputedData.respiratoryRate.getBaseUnit());
                    RespiratoryRateData.Source source = new RespiratoryRateData.Source();
                    FitnessComputedData.RespiratoryRate.Source source2 = fitnessComputedData.respiratoryRate.getSource();
                    source.setType(source2 != null ? source2.getType() : null);
                    FitnessComputedData.RespiratoryRate.Source source3 = fitnessComputedData.respiratoryRate.getSource();
                    source.setIdentifier(source3 != null ? source3.getIdentifier() : null);
                    respiratoryRateData.setSource(source);
                    RespiratoryRateData.BaseUnits baseUnits = new RespiratoryRateData.BaseUnits();
                    FitnessComputedData.RespiratoryRate.BaseUnits baseUnits2 = fitnessComputedData.respiratoryRate.getBaseUnits();
                    baseUnits.setAccuracy(baseUnits2 != null ? baseUnits2.getAccuracy() : null);
                    respiratoryRateData.setBaseUnits(baseUnits);
                    respiratoryRateData.setTzOffset(fitnessComputedData.tzOffset);
                    dailyRespiratoryRateEntity.data = respiratoryRateData;
                    dailyRespiratoryRateEntity.isSyncedWithServer = 1;
                    RespiratoryRateRepository.Companion.getInstance(context).insert(dailyRespiratoryRateEntity);
                }
            }
        }

        public final void p(Context context, Map.Entry<String, ? extends ArrayList<RawPPGHistoryData>> entry) {
            DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity = new DailyRespiratoryRawPPGHistoryEntity();
            dailyRespiratoryRawPPGHistoryEntity.setDate(entry.getKey());
            dailyRespiratoryRawPPGHistoryEntity.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            dailyRespiratoryRawPPGHistoryEntity.setSyncedToServer(0);
            RespiratoryRateRepository.Companion.getInstance(context).insertDailyRawPPGData(dailyRespiratoryRawPPGHistoryEntity);
            ArrayList arrayList = new ArrayList();
            ArrayList<RawPPGHistoryData> value = entry.getValue();
            Intrinsics.checkNotNull(value);
            Iterator<RawPPGHistoryData> it = value.iterator();
            while (it.hasNext()) {
                RawPPGHistoryData next = it.next();
                HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity = new HourlyRespiratoryRawPPGDataEntity();
                hourlyRespiratoryRawPPGDataEntity.setDate(entry.getKey());
                hourlyRespiratoryRawPPGDataEntity.setMacAddress(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                hourlyRespiratoryRawPPGDataEntity.setTimestamp(next.getTimeStamp());
                hourlyRespiratoryRawPPGDataEntity.setPpgType(next.getPpgType());
                hourlyRespiratoryRawPPGDataEntity.setInterval(next.getInterval());
                hourlyRespiratoryRawPPGDataEntity.setDuration(next.getDuration());
                hourlyRespiratoryRawPPGDataEntity.setMovementLevel(next.getMovementLevel());
                hourlyRespiratoryRawPPGDataEntity.setRecordNumber(next.getRecordNumber());
                hourlyRespiratoryRawPPGDataEntity.setSamplingRate(next.getSamplingRate());
                hourlyRespiratoryRawPPGDataEntity.setPpgValues(next.getPpgData());
                arrayList.add(hourlyRespiratoryRawPPGDataEntity);
            }
            RespiratoryRateRepository.Companion.getInstance(context).insertAllHourlyRawPPGData(arrayList);
            String tag = getTAG();
            LogHelper.d(tag, "Raw PPG data inserted for " + entry.getKey());
        }

        public final boolean q(Context context) {
            String lastSyncedDate = RespiratoryRateRepository.Companion.getInstance(context).getLastSyncedDate(BleApiManager.getInstance(context).getBleApi().getMacAddress());
            boolean z = true;
            Date parse = !(lastSyncedDate == null || lastSyncedDate.length() == 0) ? AppUtils.getSimpleDateFormat("yyyy-MM-dd").parse(lastSyncedDate) : null;
            if (parse == null || !DateUtils.isToday(parse.getTime())) {
                z = false;
            }
            if (z) {
                Long lastPPGSyncTimestamp = UserDataManager.getInstance(context).getLastPPGSyncTimestamp(BleApiManager.getInstance(context).getBleApi().getMacAddress());
                if (lastPPGSyncTimestamp == null || lastPPGSyncTimestamp.longValue() <= 0) {
                    return false;
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(lastPPGSyncTimestamp.longValue());
                if (calendar.get(11) < Integer.parseInt(Constants.SYNC_START_TIME.getValue())) {
                    return false;
                }
            }
            return z;
        }

        public final Map<String, List<RespiratoryRateApiRes.Data>> r(List<? extends RespiratoryRateApiRes.Data> list, Map<String, ArrayList<String>> map) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (RespiratoryRateApiRes.Data data : list) {
                String time = data.getTime();
                Intrinsics.checkNotNullExpressionValue(time, "d.time");
                String c = c(time, map);
                if (c != null) {
                    if (linkedHashMap.containsKey(c)) {
                        final RespiratoryRateApiRes.Data data2 = new RespiratoryRateApiRes.Data(new RespiratoryRateApiRes());
                        data2.setRespiratoryRate(data.getRespiratoryRate());
                        data2.setTime(data.getTime());
                        data2.setConfidence(data.getConfidence());
                        List list2 = (List) linkedHashMap.get(c);
                        if (list2 != null) {
                            list2.add(data2);
                        }
                        if (list2 != null && list2.size() > 1) {
                            h.sortWith(list2, new Comparator() { // from class: com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$mapByDate$$inlined$sortBy$1
                                @Override // java.util.Comparator
                                public final int compare(T t, T t2) {
                                    RespiratoryRateApiRes.Data data3 = (RespiratoryRateApiRes.Data) t;
                                    RespiratoryRateApiRes.Data data4 = (RespiratoryRateApiRes.Data) t2;
                                    return f.compareValues(RespiratoryRateApiRes.Data.this.getTime(), RespiratoryRateApiRes.Data.this.getTime());
                                }
                            });
                        }
                        if (list2 != null) {
                            linkedHashMap.put(c, list2);
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        RespiratoryRateApiRes.Data data3 = new RespiratoryRateApiRes.Data(new RespiratoryRateApiRes());
                        data3.setRespiratoryRate(data.getRespiratoryRate());
                        data3.setTime(data.getTime());
                        data3.setConfidence(data.getConfidence());
                        arrayList.add(data3);
                        linkedHashMap.put(c, arrayList);
                    }
                }
            }
            return linkedHashMap;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [java.util.LinkedHashMap, T] */
        public final void s(final Context context, final SyncRawPPGDataResultListener syncRawPPGDataResultListener) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(6, -BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().getMaxDaysOfRawPPGDataOnBand());
            Date time = calendar.getTime();
            ReadRawPPGHistoryDataRequest readRawPPGHistoryDataRequest = new ReadRawPPGHistoryDataRequest.Builder().setStartDate(time).setEndDate(Calendar.getInstance().getTime()).setTimeout(getRAW_PPG_HISTORY_COMMAND_TIMEOUT()).build();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new LinkedHashMap();
            BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
            Intrinsics.checkNotNullExpressionValue(readRawPPGHistoryDataRequest, "readRawPPGHistoryDataRequest");
            bleApi.getData(readRawPPGHistoryDataRequest, new DataResultListener() { // from class: com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$syncRawPPGHistoryData$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    UserDataManager.getInstance(context).saveLastPPGSyncTimestamp(BleApiManager.getInstance(context).getBleApi().getMacAddress(), Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                    SyncRawPPGDataResultListener.this.onFailure(error.getErrorMsg());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Map<String, ArrayList<RawPPGHistoryData>> b2;
                    Map<String, ArrayList<RawPPGHistoryData>> map;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null) {
                        SyncRawPPGDataResultListener.this.onSuccess(null);
                    } else if (response.getResponseData() instanceof ReadRawPPGDataResponse) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.ReadRawPPGDataResponse");
                        ReadRawPPGDataResponse readRawPPGDataResponse = (ReadRawPPGDataResponse) responseData;
                        if (!AppUtils.isEmpty(readRawPPGDataResponse.getPpgHistoryDataArrayList())) {
                            if ((response.getBleBaseRequest() instanceof ReadRawPPGHistoryDataRequest) && (map = objectRef.element) != null) {
                                BleBaseRequest bleBaseRequest = response.getBleBaseRequest();
                                Intrinsics.checkNotNull(bleBaseRequest, "null cannot be cast to non-null type com.coveiot.android.bleabstract.request.ReadRawPPGHistoryDataRequest");
                                String formatDate = AppUtils.formatDate(((ReadRawPPGHistoryDataRequest) bleBaseRequest).getStartDate(), "yyyy-MM-dd");
                                Intrinsics.checkNotNullExpressionValue(formatDate, "formatDate(\n            …                        )");
                                map.put(formatDate, readRawPPGDataResponse.getPpgHistoryDataArrayList());
                            }
                        } else {
                            LogHelper.d(RespiratoryRateManager.Companion.getTAG(), "PPG data is empty");
                        }
                        if (readRawPPGDataResponse.isComplete()) {
                            UserDataManager.getInstance(context).saveLastPPGSyncTimestamp(BleApiManager.getInstance(context).getBleApi().getMacAddress(), Long.valueOf(Calendar.getInstance().getTimeInMillis()));
                            Map<String, ArrayList<RawPPGHistoryData>> map2 = objectRef.element;
                            if (!(map2 == null || map2.isEmpty())) {
                                Context context2 = context;
                                for (Map.Entry<String, ArrayList<RawPPGHistoryData>> entry : objectRef.element.entrySet()) {
                                    ArrayList<RawPPGHistoryData> value = entry.getValue();
                                    if (!(value == null || value.isEmpty())) {
                                        RespiratoryRateRepository.Companion companion = RespiratoryRateRepository.Companion;
                                        if (companion.getInstance(context2).getDailyRawPPGData(entry.getKey(), BleApiManager.getInstance(context2).getBleApi().getMacAddress()) == null) {
                                            RespiratoryRateManager.Companion.a(context2);
                                        } else {
                                            companion.getInstance(context2).deleteHourlyRawPPGData(entry.getKey(), BleApiManager.getInstance(context2).getBleApi().getMacAddress());
                                            companion.getInstance(context2).deleteDailyRawPPGData(entry.getKey(), BleApiManager.getInstance(context2).getBleApi().getMacAddress());
                                            String tag = RespiratoryRateManager.Companion.getTAG();
                                            LogHelper.d(tag, "Found data for " + entry.getKey() + " and removed.");
                                        }
                                        RespiratoryRateManager.Companion.p(context2, entry);
                                    }
                                }
                            }
                            SyncRawPPGDataResultListener syncRawPPGDataResultListener2 = SyncRawPPGDataResultListener.this;
                            b2 = RespiratoryRateManager.Companion.b(context);
                            syncRawPPGDataResultListener2.onSuccess(b2);
                        }
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object syncData(@org.jetbrains.annotations.NotNull final android.content.Context r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.b
                if (r0 == 0) goto L13
                r0 = r7
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$b r0 = (com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.b) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$b r0 = new com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$b
                r0.<init>(r7)
            L18:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                r4 = 2
                if (r2 == 0) goto L3c
                if (r2 == r3) goto L38
                if (r2 != r4) goto L30
                java.lang.Object r6 = r0.L$0
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion r6 = (com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion) r6
                kotlin.ResultKt.throwOnFailure(r7)
                goto L9b
            L30:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L38:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L8c
            L3c:
                kotlin.ResultKt.throwOnFailure(r7)
                java.lang.String r7 = r5.getTAG()
                java.lang.String r2 = "Sync Called"
                com.coveiot.utils.utility.LogHelper.d(r7, r2)
                boolean r7 = com.coveiot.utils.utility.AppUtils.isNetConnected(r6)
                if (r7 == 0) goto L8f
                com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository$Companion r7 = com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository.Companion
                java.lang.Object r7 = r7.getInstance(r6)
                com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository r7 = (com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository) r7
                com.coveiot.android.bleabstract.api.BleApiManager r2 = com.coveiot.android.bleabstract.api.BleApiManager.getInstance(r6)
                com.coveiot.android.bleabstract.api.BleApi r2 = r2.getBleApi()
                java.lang.String r2 = r2.getMacAddress()
                int r7 = r7.getRowCount(r2)
                if (r7 > 0) goto L83
                java.util.Calendar r7 = java.util.Calendar.getInstance()
                r0 = -3
                r7.add(r4, r0)
                java.util.Date r7 = r7.getTime()
                java.lang.String r0 = "yyyy-MM-dd"
                java.lang.String r7 = com.coveiot.utils.utility.AppUtils.formatDate(r7, r0)
                com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$syncData$2 r0 = new com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$syncData$2
                r0.<init>()
                com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedDataApiManager.getFitnessComputedDataFromServer(r7, r0)
                goto La4
            L83:
                r0.label = r3
                java.lang.Object r6 = r5.g(r6, r0)
                if (r6 != r1) goto L8c
                return r1
            L8c:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L8f:
                r0.L$0 = r5
                r0.label = r4
                java.lang.Object r6 = r5.g(r6, r0)
                if (r6 != r1) goto L9a
                return r1
            L9a:
                r6 = r5
            L9b:
                java.lang.String r6 = r6.getTAG()
                java.lang.String r7 = "internet not available"
                com.coveiot.utils.utility.LogHelper.d(r6, r7)
            La4:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.RespiratoryRateManager.Companion.syncData(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
