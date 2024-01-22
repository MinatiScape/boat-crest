package com.coveiot.android.leonardo.bt3call.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BTCallControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.bt3call.listener.IBT3ConnectionChangeListener;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.sdk.ble.api.model.BTCallControlData;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$connectToBT3Watch$1", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class BT3CallViewModel$connectToBT3Watch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ boolean $isFromPairing;
    public int label;
    public final /* synthetic */ BT3CallViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BT3CallViewModel$connectToBT3Watch$1(BT3CallViewModel bT3CallViewModel, boolean z, Continuation<? super BT3CallViewModel$connectToBT3Watch$1> continuation) {
        super(2, continuation);
        this.this$0 = bT3CallViewModel;
        this.$isFromPairing = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new BT3CallViewModel$connectToBT3Watch$1(this.this$0, this.$isFromPairing, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((BT3CallViewModel$connectToBT3Watch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BTCallControlRequest bTCallControlRequest = new BTCallControlRequest(true, true, (short) 120);
            BleApi bleApi = BleApiManager.getInstance(this.this$0.getContext()).getBleApi();
            final BT3CallViewModel bT3CallViewModel = this.this$0;
            final boolean z = this.$isFromPairing;
            bleApi.getData(bTCallControlRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$connectToBT3Watch$1.1

                @DebugMetadata(c = "com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$connectToBT3Watch$1$1$onDataError$1", f = "BT3CallViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.bt3call.viewmodel.BT3CallViewModel$connectToBT3Watch$1$1$a */
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ BT3CallViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(BT3CallViewModel bT3CallViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = bT3CallViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, continuation);
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
                            String modelNumber = DeviceUtils.Companion.getModelNumber(this.this$0.getContext());
                            if (modelNumber == null) {
                                modelNumber = "BT";
                            }
                            IBT3ConnectionChangeListener bT3ConnectionChangeListener = this.this$0.getBT3ConnectionChangeListener();
                            if (bT3ConnectionChangeListener != null) {
                                bT3ConnectionChangeListener.onBT3ConnectionFailure(this.this$0.getContext().getString(R.string.bt3_connection_failed, modelNumber));
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    String tag = BT3CallViewModel.this.getTAG();
                    LogHelper.d(tag, "BT Call connect error " + error.getErrorMsg());
                    e.e(ViewModelKt.getViewModelScope(BT3CallViewModel.this), Dispatchers.getMain(), null, new a(BT3CallViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() instanceof BTCallControlData) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.model.BTCallControlData");
                        BTCallControlData bTCallControlData = (BTCallControlData) responseData;
                        LogHelper.d(BT3CallViewModel.this.getTAG(), "BT Call Enabled!  " + bTCallControlData.getMacAddress() + ", " + bTCallControlData.getName());
                        UserDataManager userDataManager = UserDataManager.getInstance(BT3CallViewModel.this.getContext());
                        String macAddress = bTCallControlData.getMacAddress();
                        String str2 = null;
                        if (macAddress != null) {
                            str = macAddress.toUpperCase();
                            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toUpperCase()");
                        } else {
                            str = null;
                        }
                        userDataManager.saveConnectedBTCallDeviceMac(str);
                        UserDataManager.getInstance(BT3CallViewModel.this.getContext()).saveConnectedBTCallDeviceName(bTCallControlData.getName());
                        BT3CallViewModel bT3CallViewModel2 = BT3CallViewModel.this;
                        String macAddress2 = bTCallControlData.getMacAddress();
                        if (macAddress2 != null) {
                            str2 = macAddress2.toUpperCase();
                            Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toUpperCase()");
                        }
                        bT3CallViewModel2.createBT3Bonding(str2, z, false);
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
