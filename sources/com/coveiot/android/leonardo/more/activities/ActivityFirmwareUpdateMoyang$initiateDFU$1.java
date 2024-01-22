package com.coveiot.android.leonardo.more.activities;

import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeviceInfoRequest;
import com.coveiot.android.bleabstract.request.FirmwareUpgradeRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoData;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$initiateDFU$1;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.leonardo.utils.FileUtils;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$initiateDFU$1", f = "ActivityFirmwareUpdateMoyang.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityFirmwareUpdateMoyang$initiateDFU$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivityFirmwareUpdateMoyang this$0;

    /* renamed from: com.coveiot.android.leonardo.more.activities.ActivityFirmwareUpdateMoyang$initiateDFU$1$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements DataResultListener {
        public final /* synthetic */ ActivityFirmwareUpdateMoyang h;

        public AnonymousClass1(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang) {
            this.h = activityFirmwareUpdateMoyang;
        }

        public static final void b(ActivityFirmwareUpdateMoyang this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.D();
        }

        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
        public void onDataError(@NotNull BleBaseError error) {
            SoftwareUpdateRes.DataBean.FirmwareBean firmwareBean;
            Intrinsics.checkNotNullParameter(error, "error");
            ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.h;
            Object[] objArr = new Object[1];
            firmwareBean = activityFirmwareUpdateMoyang.u;
            if (firmwareBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firmwareBean");
                firmwareBean = null;
            }
            objArr[0] = firmwareBean.getUpdateVersion();
            String string = activityFirmwareUpdateMoyang.getString(R.string.fw_update_failure, objArr);
            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
            activityFirmwareUpdateMoyang.G(string, "");
        }

        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
        public void onDataResponse(@NotNull BleBaseResponse response) {
            Handler handler;
            Intrinsics.checkNotNullParameter(response, "response");
            if (response.getResponseData() instanceof DeviceInfoResponse) {
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.DeviceInfoResponse");
                DeviceInfoResponse deviceInfoResponse = (DeviceInfoResponse) responseData;
                if (deviceInfoResponse.getDeviceInfo() == null) {
                    handler = this.h.v;
                    final ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang = this.h;
                    handler.postDelayed(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.tc
                        @Override // java.lang.Runnable
                        public final void run() {
                            ActivityFirmwareUpdateMoyang$initiateDFU$1.AnonymousClass1.b(ActivityFirmwareUpdateMoyang.this);
                        }
                    }, 200L);
                    return;
                }
                FileUtils fileUtils = FileUtils.INSTANCE;
                File filesDir = this.h.getFilesDir();
                Intrinsics.checkNotNullExpressionValue(filesDir, "this@ActivityFirmwareUpdateMoyang.filesDir");
                File firmwareFile = fileUtils.getFirmwareFile(filesDir, AppConstants.MOYANG_FIRMWARE_FILE_NAME.getValue());
                if (firmwareFile != null) {
                    DeviceInfoData deviceInfo = deviceInfoResponse.getDeviceInfo();
                    Intrinsics.checkNotNull(deviceInfo);
                    String fwVersion = deviceInfo.getFwVersion();
                    FirmwareUpgradeRequest build = fwVersion != null ? new FirmwareUpgradeRequest.Builder(firmwareFile, fwVersion).build() : null;
                    if (build != null) {
                        BleApiManager.getInstance(this.h).getBleApi().getData(build, new ActivityFirmwareUpdateMoyang$initiateDFU$1$1$onDataResponse$1(this.h));
                    }
                }
            }
        }

        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
        public void onProgressUpdate(@NotNull ProgressData progress) {
            Intrinsics.checkNotNullParameter(progress, "progress");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityFirmwareUpdateMoyang$initiateDFU$1(ActivityFirmwareUpdateMoyang activityFirmwareUpdateMoyang, Continuation<? super ActivityFirmwareUpdateMoyang$initiateDFU$1> continuation) {
        super(2, continuation);
        this.this$0 = activityFirmwareUpdateMoyang;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityFirmwareUpdateMoyang$initiateDFU$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityFirmwareUpdateMoyang$initiateDFU$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApiManager.getInstance(this.this$0).getBleApi().getData(new DeviceInfoRequest.Builder().setIsModelNo(false).setIsFwVersion(true).setIsSerialNo(false).setIsHwVersion(false).build(), new AnonymousClass1(this.this$0));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
