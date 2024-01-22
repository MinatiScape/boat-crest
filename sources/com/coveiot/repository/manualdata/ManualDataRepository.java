package com.coveiot.repository.manualdata;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitness.model.BPFitnessRecordData;
import com.coveiot.coveaccess.fitness.model.BpFitnessRecords;
import com.coveiot.coveaccess.fitnessrecord.FitnessRecordApiManager;
import com.coveiot.coveaccess.manualdata.GetManualMixedDataRes;
import com.coveiot.coveaccess.manualdata.GetManualSPO2DataRes;
import com.coveiot.coveaccess.manualdata.ManualDataApiManager;
import com.coveiot.coveaccess.manualdata.model.ManualMixedData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SPO2FitnessSessionDataBean;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.coveiot.covedb.manualdata.entities.Source;
import com.coveiot.repository.SingletonHolder;
import com.coveiot.repository.manualdata.datasources.db.read.ManualDataDBRead;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler;
import com.coveiot.repository.manualdata.datasources.server.ServerFormator;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ManualDataRepository {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7405a;
    public final ManualDataDBRead b;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<ManualDataRepository, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, ManualDataRepository> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, ManualDataRepository.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final ManualDataRepository invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new ManualDataRepository(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ManualDataRepository(Context context) {
        this.f7405a = context;
        this.b = ManualDataDBRead.getInstance(context);
    }

    public /* synthetic */ ManualDataRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getBpData(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> bpData = this.b.getBpData(date, str, str2);
        Intrinsics.checkNotNullExpressionValue(bpData, "manualDataDbRepo.getBpData(date, serialNo, source)");
        return bpData;
    }

    @Nullable
    public final List<EntityManualData> getBpDataWithoutLiveData(@NotNull String fromDate, @NotNull String toDate, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        return this.b.getBpDataWithoutLiveData(fromDate, toDate, str, str2);
    }

    public final void getBpRecordDataFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        FitnessRecordApiManager.getBpFitnessRecord(AppUtils.formatDate(fromDate.getTime(), "yyyy--MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy--MM-dd"), new CoveApiListener<BpFitnessRecords, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.ManualDataRepository$getBpRecordDataFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable BpFitnessRecords bpFitnessRecords) {
                if (bpFitnessRecords == null || bpFitnessRecords.getItems() == null || bpFitnessRecords.getItems() == null || bpFitnessRecords.getItems().getBpItems().size() <= 0) {
                    return;
                }
                for (BPFitnessRecordData bPFitnessRecordData : bpFitnessRecords.getItems().getBpItems()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(AppUtils.parseDate(bPFitnessRecordData.getDate(), "yyyy-MM-dd"));
                    String time = bPFitnessRecordData.getTime();
                    Intrinsics.checkNotNullExpressionValue(time, "fitnessRecord.time");
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) time, new char[]{':'}, false, 0, 6, (Object) null);
                    calendar.set(11, Integer.parseInt((String) split$default.get(0)));
                    calendar.set(12, Integer.parseInt((String) split$default.get(1)));
                    calendar.set(13, Integer.parseInt((String) split$default.get(2)));
                    EntityManualData entityManualData = new EntityManualData(calendar.getTimeInMillis(), Source.MANUAL.name());
                    Integer num = bPFitnessRecordData.getValue().get(0);
                    Intrinsics.checkNotNullExpressionValue(num, "fitnessRecord.value.get(0)");
                    entityManualData.setSystolicBp(num.intValue());
                    Integer num2 = bPFitnessRecordData.getValue().get(1);
                    Intrinsics.checkNotNullExpressionValue(num2, "fitnessRecord.value.get(1)");
                    entityManualData.setDiastolicBp(num2.intValue());
                    entityManualData.setUserDeviceId(PreferenceManager.getInstance().getUserDeviceID());
                    entityManualData.setLevelInterpretation(false);
                    ManualDataDBWrite.getInstance(ManualDataRepository.this.getContext()).insert(entityManualData);
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f7405a;
    }

    @NotNull
    public final ArrayList<EntityManualData> getEntityManualHRVAndStressDataList(@NotNull List<? extends ManualMixedData> manualHRVAndStressReadingList) {
        Intrinsics.checkNotNullParameter(manualHRVAndStressReadingList, "manualHRVAndStressReadingList");
        ArrayList<EntityManualData> arrayList = new ArrayList<>();
        for (ManualMixedData manualMixedData : manualHRVAndStressReadingList) {
            long j = 1000;
            EntityManualData entityManualData = new EntityManualData((AppUtils.parseDate(manualMixedData.getSessionStartDate(), UtilConstants.SERVER_TIME_FORMAT).getTime() / j) * j, Source.FROM_DEVICE.name());
            entityManualData.setSerialNo(BleApiManager.getInstance(this.f7405a).getBleApi().getMacAddress());
            entityManualData.setUserDeviceId(PreferenceManager.getInstance().getUserDeviceID());
            Integer hrv = manualMixedData.getHrv();
            Intrinsics.checkNotNullExpressionValue(hrv, "manualHRVAndStressReading.hrv");
            entityManualData.setHrv(hrv.intValue());
            Integer stressLevel = manualMixedData.getStressLevel();
            Intrinsics.checkNotNullExpressionValue(stressLevel, "manualHRVAndStressReading.stressLevel");
            entityManualData.setStress(stressLevel.intValue());
            Integer hr = manualMixedData.getHr();
            Intrinsics.checkNotNullExpressionValue(hr, "manualHRVAndStressReading.hr");
            entityManualData.setHr(hr.intValue());
            Integer vascAging = manualMixedData.getVascAging();
            Intrinsics.checkNotNullExpressionValue(vascAging, "manualHRVAndStressReading.vascAging");
            entityManualData.setVascularAging(vascAging.intValue());
            entityManualData.setSyncedWithServer(true);
            arrayList.add(entityManualData);
        }
        return arrayList;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getHRVData(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> hRVData = this.b.getHRVData(date, str, str2);
        Intrinsics.checkNotNullExpressionValue(hRVData, "manualDataDbRepo.getHRVD…a(date, serialNo, source)");
        return hRVData;
    }

    public final void getHRVStressDataFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        ManualDataApiManager.getManualMixedFrom(AppUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), new CoveApiListener<GetManualMixedDataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.ManualDataRepository$getHRVStressDataFromServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetManualMixedDataRes getManualMixedDataRes) {
                if (getManualMixedDataRes == null || getManualMixedDataRes.getManualMixedDataList() == null || getManualMixedDataRes.getManualMixedDataList().size() <= 0) {
                    return;
                }
                ManualDataDBWrite manualDataDBWrite = ManualDataDBWrite.getInstance(ManualDataRepository.this.getContext());
                ManualDataRepository manualDataRepository = ManualDataRepository.this;
                List<ManualMixedData> manualMixedDataList = getManualMixedDataRes.getManualMixedDataList();
                Intrinsics.checkNotNull(manualMixedDataList);
                manualDataDBWrite.insetAll(manualDataRepository.getEntityManualHRVAndStressDataList(manualMixedDataList));
            }
        });
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay(@Nullable String str, @Nullable String str2) {
        LiveData<List<EntityManualData>> highestSpo2DataForEachDay = this.b.getHighestSpo2DataForEachDay(str, str2);
        Intrinsics.checkNotNullExpressionValue(highestSpo2DataForEachDay, "manualDataDbRepo.getHigh…EachDay(serialNo, source)");
        return highestSpo2DataForEachDay;
    }

    @Nullable
    public final EntityManualData getLastMeasuredBp(@Nullable String str) {
        return ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredBp(str);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLastMeasuredBpHourlyDataForDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> lastMeasuredBpHourlyDataForDate = this.b.getLastMeasuredBpHourlyDataForDate(date);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredBpHourlyDataForDate, "manualDataDbRepo.getLast…BpHourlyDataForDate(date)");
        return lastMeasuredBpHourlyDataForDate;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredBpLiveData(@Nullable String str) {
        LiveData<EntityManualData> lastMeasuredBpLiveData = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredBpLiveData(str);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredBpLiveData, "getInstance(context).get…uredBpLiveData(serial_no)");
        return lastMeasuredBpLiveData;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredHRV(@Nullable String str, @Nullable String str2) {
        LiveData<EntityManualData> lastMeasuredHRV = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredHRV(str, str2);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredHRV, "getInstance(context).get…suredHRV(serialNo,source)");
        return lastMeasuredHRV;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2(@Nullable String str) {
        LiveData<EntityManualData> lastMeasuredSpo2 = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredSpo2(str);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2, "getInstance(context).get…stMeasuredSpo2(serial_no)");
        return lastMeasuredSpo2;
    }

    @Nullable
    public final EntityManualData getLastMeasuredSpo2ByMacAddress(@Nullable String str) {
        return ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredSpo2ByMacAddress(str);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLastMeasuredSpo2Data(@NotNull String date, @Nullable String str, @Nullable String str2, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> lastMeasuredSpo2Data = this.b.getLastMeasuredSpo2Data(date, str, str2, i);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2Data, "manualDataDbRepo.getLast…ce,isLevelInterpretation)");
        return lastMeasuredSpo2Data;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2DataFor(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<EntityManualData> lastMeasuredSpo2DataFor = this.b.getLastMeasuredSpo2DataFor(date);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2DataFor, "manualDataDbRepo.getLastMeasuredSpo2DataFor(date)");
        return lastMeasuredSpo2DataFor;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(@NotNull String date, @NotNull String serialNo, @NotNull String source) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Intrinsics.checkNotNullParameter(source, "source");
        LiveData<List<EntityManualData>> lastMeasuredSpo2HourlyDataForDate = this.b.getLastMeasuredSpo2HourlyDataForDate(date, serialNo, source);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2HourlyDataForDate, "manualDataDbRepo.getLast…e(date, serialNo, source)");
        return lastMeasuredSpo2HourlyDataForDate;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredStress(@Nullable String str, @Nullable String str2) {
        LiveData<EntityManualData> lastMeasuredStress = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredStress(str, str2);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredStress, "getInstance(context).get…edStress(serialNo,source)");
        return lastMeasuredStress;
    }

    @Nullable
    public final EntityManualData getLatestBpDataFor(@Nullable String str) {
        return this.b.getLatestBpDataFor(str);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLatestBpDataForEachDay(@Nullable String str, @Nullable String str2) {
        LiveData<List<EntityManualData>> latestBpDataForEachDay = this.b.getLatestBpDataForEachDay(str, str2);
        Intrinsics.checkNotNullExpressionValue(latestBpDataForEachDay, "manualDataDbRepo.getLate…EachDay(serialNo, source)");
        return latestBpDataForEachDay;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLatestSpo2DataForEachDay(@Nullable String str, @Nullable String str2) {
        LiveData<List<EntityManualData>> latestSpo2DataForEachDay = this.b.getLatestSpo2DataForEachDay(str, str2);
        Intrinsics.checkNotNullExpressionValue(latestSpo2DataForEachDay, "manualDataDbRepo.getLate…EachDay(serialNo, source)");
        return latestSpo2DataForEachDay;
    }

    public final void getManualBPDataFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        ManualDataApiHandler.Companion.fetchManualBPDataFromServer(this.f7405a, fromDate, toDate, new ManualDataApiHandler.Companion.ManualDataApiListner() { // from class: com.coveiot.repository.manualdata.ManualDataRepository$getManualBPDataFromServer$1

            @DebugMetadata(c = "com.coveiot.repository.manualdata.ManualDataRepository$getManualBPDataFromServer$1$onManualDataReceived$1", f = "ManualDataRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ List<EntityManualData> $manualDataList;
                public int label;
                public final /* synthetic */ ManualDataRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(ManualDataRepository manualDataRepository, List<? extends EntityManualData> list, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = manualDataRepository;
                    this.$manualDataList = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$manualDataList, continuation);
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
                        ManualDataDBWrite.getInstance(this.this$0.getContext()).insetAll(this.$manualDataList);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler.Companion.ManualDataApiListner
            public void onError(@NotNull String message) {
                Intrinsics.checkNotNullParameter(message, "message");
            }

            @Override // com.coveiot.repository.manualdata.datasources.server.ManualDataApiHandler.Companion.ManualDataApiListner
            public void onManualDataReceived(@NotNull List<? extends EntityManualData> manualDataList) {
                Intrinsics.checkNotNullParameter(manualDataList, "manualDataList");
                e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(ManualDataRepository.this, manualDataList, null), 2, null);
            }
        });
    }

    public final ManualDataDBRead getManualDataDbRepo() {
        return this.b;
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMaxSpo2HourlyDataForDate(date, str, str2);
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMinSpo2HourlyDataForDate(date, str, str2);
    }

    public final void getSpo2DataFromServer(@NotNull Calendar fromDate, @NotNull Calendar toDate) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        ManualDataApiManager.getManualSPO2From(AppUtils.formatDate(fromDate.getTime(), "yyyy-MM-dd"), AppUtils.formatDate(toDate.getTime(), "yyyy-MM-dd"), new CoveApiListener<GetManualSPO2DataRes, CoveApiErrorModel>() { // from class: com.coveiot.repository.manualdata.ManualDataRepository$getSpo2DataFromServer$1

            @DebugMetadata(c = "com.coveiot.repository.manualdata.ManualDataRepository$getSpo2DataFromServer$1$onSuccess$1", f = "ManualDataRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes9.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ GetManualSPO2DataRes $p0;
                public int label;
                public final /* synthetic */ ManualDataRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(GetManualSPO2DataRes getManualSPO2DataRes, ManualDataRepository manualDataRepository, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$p0 = getManualSPO2DataRes;
                    this.this$0 = manualDataRepository;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$p0, this.this$0, continuation);
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
                        if (this.$p0.getManualSPO2DataList() != null && this.$p0.getManualSPO2DataList() != null && this.$p0.getManualSPO2DataList().size() > 0) {
                            for (SPO2FitnessSessionDataBean spo2FitnessDataBean : this.$p0.getManualSPO2DataList()) {
                                ServerFormator.Companion companion = ServerFormator.Companion;
                                Intrinsics.checkNotNullExpressionValue(spo2FitnessDataBean, "spo2FitnessDataBean");
                                String macAddress = BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getMacAddress();
                                String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
                                Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
                                ManualDataDBWrite.getInstance(this.this$0.getContext()).insert(companion.getSPO2EntityFromServerObject(spo2FitnessDataBean, macAddress, userDeviceID));
                            }
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetManualSPO2DataRes getManualSPO2DataRes) {
                if (getManualSPO2DataRes != null) {
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(getManualSPO2DataRes, ManualDataRepository.this, null), 2, null);
                }
            }
        });
    }

    @Nullable
    public final List<EntityManualData> getSpo2DataList(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getSpo2DataList(date, str, str2);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getSpo2ata(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> spo2ata = this.b.getSpo2ata(date, str, str2);
        Intrinsics.checkNotNullExpressionValue(spo2ata, "manualDataDbRepo.getSpo2…a(date, serialNo, source)");
        return spo2ata;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getStressData(@NotNull String date, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> stressData = this.b.getStressData(date, str, str2);
        Intrinsics.checkNotNullExpressionValue(stressData, "manualDataDbRepo.getStre…a(date, serialNo, source)");
        return stressData;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getTemperatureData(@NotNull String date, @NotNull String serialNo, @NotNull String source) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(serialNo, "serialNo");
        Intrinsics.checkNotNullParameter(source, "source");
        LiveData<List<EntityManualData>> temperatureData = this.b.getTemperatureData(date, serialNo, source);
        Intrinsics.checkNotNullExpressionValue(temperatureData, "manualDataDbRepo.getTemp…a(date, serialNo, source)");
        return temperatureData;
    }

    @Nullable
    public final List<EntityManualData> getUnSyncedBpData() {
        return this.b.getUnSyncedBpData();
    }

    @Nullable
    public final List<EntityManualData> getUnSyncedData() {
        return this.b.getUnSyncedData();
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getBpData(@NotNull String fromDate, @NotNull String toDate, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(fromDate, "fromDate");
        Intrinsics.checkNotNullParameter(toDate, "toDate");
        LiveData<List<EntityManualData>> bpData = this.b.getBpData(fromDate, toDate, str, str2);
        Intrinsics.checkNotNullExpressionValue(bpData, "manualDataDbRepo.getBpDa…toDate, serialNo, source)");
        return bpData;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getHRVData(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> hRVData = this.b.getHRVData(date, str);
        Intrinsics.checkNotNullExpressionValue(hRVData, "manualDataDbRepo.getHRVData(date, serialNo)");
        return hRVData;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay() {
        LiveData<List<EntityManualData>> highestSpo2DataForEachDay = this.b.getHighestSpo2DataForEachDay();
        Intrinsics.checkNotNullExpressionValue(highestSpo2DataForEachDay, "manualDataDbRepo.getHighestSpo2DataForEachDay()");
        return highestSpo2DataForEachDay;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2(@Nullable String str, @Nullable String str2) {
        LiveData<EntityManualData> lastMeasuredSpo2 = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredSpo2(str, str2);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2, "getInstance(context).get…uredSpo2(serialNo,source)");
        return lastMeasuredSpo2;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2DataFor(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<EntityManualData> lastMeasuredSpo2DataFor = this.b.getLastMeasuredSpo2DataFor(date, str);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2DataFor, "manualDataDbRepo.getLast…po2DataFor(date,serialNo)");
        return lastMeasuredSpo2DataFor;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(@NotNull String date, @Nullable String str, @Nullable String str2, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> lastMeasuredSpo2HourlyDataForDate = this.b.getLastMeasuredSpo2HourlyDataForDate(date, str, str2, i);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2HourlyDataForDate, "manualDataDbRepo.getLast…ce,isLevelInterpretation)");
        return lastMeasuredSpo2HourlyDataForDate;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLatestSpo2DataForEachDay() {
        LiveData<List<EntityManualData>> latestSpo2DataForEachDay = this.b.getLatestSpo2DataForEachDay();
        Intrinsics.checkNotNullExpressionValue(latestSpo2DataForEachDay, "manualDataDbRepo.getLatestSpo2DataForEachDay()");
        return latestSpo2DataForEachDay;
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(@NotNull String date, @Nullable String str, @Nullable String str2, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMaxSpo2HourlyDataForDate(date, str, str2, i);
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(@NotNull String date, @Nullable String str, @Nullable String str2, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMinSpo2HourlyDataForDate(date, str, str2, i);
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getSpo2DataList(@Nullable String str, @Nullable String str2) {
        LiveData<List<EntityManualData>> spo2DataList = this.b.getSpo2DataList(str, str2);
        Intrinsics.checkNotNullExpressionValue(spo2DataList, "manualDataDbRepo.getSpo2…taList( serialNo, source)");
        return spo2DataList;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getSpo2ata(@NotNull String date, @Nullable String str, @Nullable String str2, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> spo2ata = this.b.getSpo2ata(date, str, str2, i);
        Intrinsics.checkNotNullExpressionValue(spo2ata, "manualDataDbRepo.getSpo2…ce,isLevelInterpretation)");
        return spo2ata;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getStressData(@NotNull String date, @Nullable String str) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> stressData = this.b.getStressData(date, str);
        Intrinsics.checkNotNullExpressionValue(stressData, "manualDataDbRepo.getStressData(date, serialNo)");
        return stressData;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2(@Nullable String str, @Nullable String str2, int i) {
        LiveData<EntityManualData> lastMeasuredSpo2 = ManualDataDBRead.getInstance(this.f7405a).getLastMeasuredSpo2(str, str2, i);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2, "getInstance(context).get…ce,isLevelInterpretation)");
        return lastMeasuredSpo2;
    }

    @NotNull
    public final LiveData<EntityManualData> getLastMeasuredSpo2DataFor(@NotNull String date, @Nullable String str, int i) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<EntityManualData> lastMeasuredSpo2DataFor = this.b.getLastMeasuredSpo2DataFor(date, str, i);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2DataFor, "manualDataDbRepo.getLast…No,isLevelInterpretation)");
        return lastMeasuredSpo2DataFor;
    }

    @NotNull
    public final LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        LiveData<List<EntityManualData>> lastMeasuredSpo2HourlyDataForDate = this.b.getLastMeasuredSpo2HourlyDataForDate(date);
        Intrinsics.checkNotNullExpressionValue(lastMeasuredSpo2HourlyDataForDate, "manualDataDbRepo.getLast…o2HourlyDataForDate(date)");
        return lastMeasuredSpo2HourlyDataForDate;
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMaxSpo2HourlyDataForDate(date);
    }

    @Nullable
    public final LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        return this.b.getMinSpo2HourlyDataForDate(date);
    }
}
