package com.coveiot.android.dashboard2.viewmodel;

import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.utils.BT3Utils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$triggerSync$1$onDataResponse$1", f = "DataSyncViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
public final class DataSyncViewModel$triggerSync$1$onDataResponse$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ BleBaseResponse $response;
    public int label;
    public final /* synthetic */ DataSyncViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSyncViewModel$triggerSync$1$onDataResponse$1(BleBaseResponse bleBaseResponse, DataSyncViewModel dataSyncViewModel, Continuation<? super DataSyncViewModel$triggerSync$1$onDataResponse$1> continuation) {
        super(2, continuation);
        this.$response = bleBaseResponse;
        this.this$0 = dataSyncViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new DataSyncViewModel$triggerSync$1$onDataResponse$1(this.$response, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((DataSyncViewModel$triggerSync$1$onDataResponse$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$response.getResponseData() instanceof BatteryLevelResponse) {
                Object responseData = this.$response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                Intrinsics.checkNotNull(batteryLevel);
                int intValue = batteryLevel.intValue();
                if (DeviceUtils.Companion.isCurrentFirmwareHasIssueWithBatteryPercentage(this.this$0.getContext()) && intValue <= 0) {
                    intValue = 100;
                }
                UserDataManager.getInstance(this.this$0.getContext()).saveBatteryLevelData(new BatteryLevelData(intValue, System.currentTimeMillis()));
                this.this$0.getBatteryLiveData().postValue(Boxing.boxInt(intValue));
                if (!this.this$0.isManualSyncAttempt() && !SessionManager.getInstance(this.this$0.getContext()).isFromOnboarding()) {
                    try {
                        BT3Utils.checkBT3ConnectionStatusAndLogEvent(this.this$0.getContext(), Boxing.boxBoolean(false));
                    } catch (Exception e) {
                        LogHelper.e(this.this$0.getTAG(), e.getMessage());
                    }
                }
                if (!BleApiManager.getInstance(this.this$0.getContext()).getBleApi().getDeviceSupportedFeatures().isSyncBandSettingsSupported()) {
                    this.this$0.n();
                } else {
                    final DataSyncViewModel dataSyncViewModel = this.this$0;
                    SettingsSyncHelper.Companion.getInstance(this.this$0.getContext()).syncBandSettings(new SettingsSyncHelper.SettingsSyncListner() { // from class: com.coveiot.android.dashboard2.viewmodel.DataSyncViewModel$triggerSync$1$onDataResponse$1.1
                        @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                        public void onProgressUpdate(int i) {
                        }

                        @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                        public void onSettingSyncError() {
                            DataSyncViewModel.this.n();
                        }

                        @Override // com.coveiot.android.dashboard2.SettingsSyncHelper.SettingsSyncListner
                        public void onSettingsSyncCompleted() {
                            DataSyncViewModel.this.n();
                        }
                    });
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
