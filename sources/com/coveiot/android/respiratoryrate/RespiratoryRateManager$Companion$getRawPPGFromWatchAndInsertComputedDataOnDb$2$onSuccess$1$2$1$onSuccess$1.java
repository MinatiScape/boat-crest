package com.coveiot.android.respiratoryrate;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.android.respiratoryrate.RespiratoryRateManager;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedData;
import com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedDataApiManager;
import com.coveiot.coveaccess.fitnesscomputeddataapi.SaveFitnessComputedDataRequest;
import com.coveiot.coveaccess.fitnesscomputeddataapi.SaveFitnessComputedDataResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.respiratoryrate.RespiratoryRateApiRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1", f = "RespiratoryRateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ Map<String, ArrayList<RawPPGHistoryData>> $mapDateRawPPGHistoryDataList;
    public final /* synthetic */ RespiratoryRateApiRes $p0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1(RespiratoryRateApiRes respiratoryRateApiRes, Context context, Map<String, ArrayList<RawPPGHistoryData>> map, Continuation<? super RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1> continuation) {
        super(2, continuation);
        this.$p0 = respiratoryRateApiRes;
        this.$context = context;
        this.$mapDateRawPPGHistoryDataList = map;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1(this.$p0, this.$context, this.$mapDateRawPPGHistoryDataList, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Map d;
        final SaveFitnessComputedDataRequest i;
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            RespiratoryRateApiRes respiratoryRateApiRes = this.$p0;
            if (respiratoryRateApiRes != null) {
                final Context context = this.$context;
                Map<String, ArrayList<RawPPGHistoryData>> map = this.$mapDateRawPPGHistoryDataList;
                RespiratoryRateManager.Companion companion = RespiratoryRateManager.Companion;
                d = companion.d(map);
                i = companion.i(context, respiratoryRateApiRes, d);
                FitnessComputedDataApiManager.saveFitnesComputedData(i, new CoveApiListener<SaveFitnessComputedDataResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1$1$1

                    @DebugMetadata(c = "com.coveiot.android.respiratoryrate.RespiratoryRateManager$Companion$getRawPPGFromWatchAndInsertComputedDataOnDb$2$onSuccess$1$2$1$onSuccess$1$1$1$onSuccess$1", f = "RespiratoryRateManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    /* loaded from: classes6.dex */
                    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        public final /* synthetic */ SaveFitnessComputedDataRequest $appServerSaveFitnessComputedDataRequest;
                        public final /* synthetic */ Context $context;
                        public int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public a(SaveFitnessComputedDataRequest saveFitnessComputedDataRequest, Context context, Continuation<? super a> continuation) {
                            super(2, continuation);
                            this.$appServerSaveFitnessComputedDataRequest = saveFitnessComputedDataRequest;
                            this.$context = context;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                            return new a(this.$appServerSaveFitnessComputedDataRequest, this.$context, continuation);
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
                                SaveFitnessComputedDataRequest saveFitnessComputedDataRequest = this.$appServerSaveFitnessComputedDataRequest;
                                if (saveFitnessComputedDataRequest != null) {
                                    List<FitnessComputedData> fitnessData = saveFitnessComputedDataRequest.getFitnessDataComputed();
                                    RespiratoryRateManager.Companion companion = RespiratoryRateManager.Companion;
                                    Context context = this.$context;
                                    Intrinsics.checkNotNullExpressionValue(fitnessData, "fitnessData");
                                    companion.o(context, fitnessData);
                                    UserDataManager.getInstance(this.$context).saveLastPPGSyncTimestamp(BleApiManager.getInstance(this.$context).getBleApi().getMacAddress(), Boxing.boxLong(Calendar.getInstance().getTimeInMillis()));
                                    RespiratoryRateRepository.Companion companion2 = RespiratoryRateRepository.Companion;
                                    companion2.getInstance(this.$context).deleteAllHourlyRawPPGData(BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                                    companion2.getInstance(this.$context).deleteAllDailyRawPPGData(BleApiManager.getInstance(this.$context).getBleApi().getMacAddress());
                                }
                                return Unit.INSTANCE;
                            }
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                        LogHelper.d(RespiratoryRateManager.Companion.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@Nullable SaveFitnessComputedDataResponse saveFitnessComputedDataResponse) {
                        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(SaveFitnessComputedDataRequest.this, context, null), 2, null);
                    }
                });
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
