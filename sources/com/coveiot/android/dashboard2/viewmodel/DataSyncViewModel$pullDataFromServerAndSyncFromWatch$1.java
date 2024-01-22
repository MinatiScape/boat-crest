package com.coveiot.android.dashboard2.viewmodel;

import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.model.ServerDataPullConfigModel;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.manualdata.GetManualSPO2DataRes;
import com.coveiot.coveaccess.manualdata.ManualDataApiManager;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SPO2FitnessSessionDataBean;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.repository.Error;
import com.coveiot.repository.datasync.ProgressDataBean;
import com.coveiot.repository.datasync.ServerSyncUtils;
import com.coveiot.repository.datasync.domainlogic.SyncResultListner;
import com.coveiot.repository.manualdata.datasources.db.write.ManualDataDBWrite;
import com.coveiot.repository.manualdata.datasources.server.ServerFormator;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.Calendar;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Calendar $fromDataCal;
    public final /* synthetic */ ServerDataPullConfigModel $serverDataPullConfigModel;
    public final /* synthetic */ Calendar $toDate;
    public int label;
    public final /* synthetic */ DataSyncViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1(DataSyncViewModel dataSyncViewModel, Calendar calendar, Calendar calendar2, ServerDataPullConfigModel serverDataPullConfigModel, Continuation<? super DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1> continuation) {
        super(2, continuation);
        this.this$0 = dataSyncViewModel;
        this.$fromDataCal = calendar;
        this.$toDate = calendar2;
        this.$serverDataPullConfigModel = serverDataPullConfigModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1(this.this$0, this.$fromDataCal, this.$toDate, this.$serverDataPullConfigModel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Calendar calendar = this.$fromDataCal;
            Calendar toDate = this.$toDate;
            Intrinsics.checkNotNullExpressionValue(toDate, "toDate");
            final DataSyncViewModel dataSyncViewModel = this.this$0;
            final Calendar calendar2 = this.$fromDataCal;
            final Calendar calendar3 = this.$toDate;
            final ServerDataPullConfigModel serverDataPullConfigModel = this.$serverDataPullConfigModel;
            ServerSyncUtils.Companion.getInstance(this.this$0.getContext()).fetchDataFromServerWith(calendar, toDate, new SyncResultListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1.1
                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onFailure(@Nullable Error error) {
                    LogHelper.e(DataSyncViewModel.this.getTAG(), "server data pull failed");
                    DataSyncViewModel.this.setDataPullInProgress(false);
                    DataSyncViewModel.o(DataSyncViewModel.this, false, 1, null);
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onProgressUpdate(@Nullable ProgressDataBean progressDataBean) {
                }

                @Override // com.coveiot.repository.datasync.domainlogic.SyncResultListner
                public void onSuccess() {
                    LogHelper.i(DataSyncViewModel.this.getTAG(), "server data pull succeeded");
                    Calendar calendar4 = calendar2;
                    Calendar toDate2 = calendar3;
                    Intrinsics.checkNotNullExpressionValue(toDate2, "toDate");
                    final DataSyncViewModel dataSyncViewModel2 = DataSyncViewModel.this;
                    final Calendar calendar5 = calendar2;
                    final Calendar calendar6 = calendar3;
                    final ServerDataPullConfigModel serverDataPullConfigModel2 = serverDataPullConfigModel;
                    WorkoutSessionRepository.Companion.getInstance(DataSyncViewModel.this.getContext()).getSessionsListFromServer(calendar4, toDate2, new SuccessResultListener() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1$1$onSuccess$1
                        @Override // com.coveiot.android.theme.SuccessResultListener
                        public void onError(@Nullable String str) {
                            String tag = DataSyncViewModel.this.getTAG();
                            LogHelper.e(tag, "workout data pull failed " + str);
                            DataSyncViewModel.this.setDataPullInProgress(false);
                            DataSyncViewModel.o(DataSyncViewModel.this, false, 1, null);
                        }

                        @Override // com.coveiot.android.theme.SuccessResultListener
                        public void onSuccess() {
                            LogHelper.i(DataSyncViewModel.this.getTAG(), "workout data pull succeeded");
                            String formatDate = AppUtils.formatDate(calendar5.getTime(), "yyyy-MM-dd");
                            String formatDate2 = AppUtils.formatDate(calendar6.getTime(), "yyyy-MM-dd");
                            final DataSyncViewModel dataSyncViewModel3 = DataSyncViewModel.this;
                            final ServerDataPullConfigModel serverDataPullConfigModel3 = serverDataPullConfigModel2;
                            ManualDataApiManager.getManualSPO2From(formatDate, formatDate2, new CoveApiListener<GetManualSPO2DataRes, CoveApiErrorModel>() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1$1$onSuccess$1$onSuccess$1

                                @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$pullDataFromServerAndSyncFromWatch$1$1$onSuccess$1$onSuccess$1$onSuccess$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                                /* loaded from: classes4.dex */
                                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    public final /* synthetic */ GetManualSPO2DataRes $p0;
                                    public int label;
                                    public final /* synthetic */ DataSyncViewModel this$0;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    public a(GetManualSPO2DataRes getManualSPO2DataRes, DataSyncViewModel dataSyncViewModel, Continuation<? super a> continuation) {
                                        super(2, continuation);
                                        this.$p0 = getManualSPO2DataRes;
                                        this.this$0 = dataSyncViewModel;
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
                                    String tag = DataSyncViewModel.this.getTAG();
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("manual SPO2 data pull failed ");
                                    sb.append(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                                    LogHelper.e(tag, sb.toString());
                                    DataSyncViewModel.this.setDataPullInProgress(false);
                                    DataSyncViewModel.o(DataSyncViewModel.this, false, 1, null);
                                }

                                @Override // com.coveiot.coveaccess.CoveApiListener
                                public void onSuccess(@Nullable GetManualSPO2DataRes getManualSPO2DataRes) {
                                    LogHelper.i(DataSyncViewModel.this.getTAG(), "manual SPO2 data pull succeeded");
                                    if (getManualSPO2DataRes != null) {
                                        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(getManualSPO2DataRes, DataSyncViewModel.this, null), 2, null);
                                    }
                                    ServerDataPullConfigModel serverDataPullConfigModel4 = serverDataPullConfigModel3;
                                    Intrinsics.checkNotNull(serverDataPullConfigModel4);
                                    Dashboard2PreferenceManager.Companion.getInstance(DataSyncViewModel.this.getContext()).saveServerSyncConfigModel(serverDataPullConfigModel4);
                                    DataSyncViewModel.this.setDataPullInProgress(false);
                                    DataSyncViewModel.o(DataSyncViewModel.this, false, 1, null);
                                }
                            });
                        }
                    });
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
