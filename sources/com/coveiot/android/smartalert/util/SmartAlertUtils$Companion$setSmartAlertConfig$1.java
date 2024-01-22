package com.coveiot.android.smartalert.util;

import android.content.Context;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.SmartAlertAppData;
import com.coveiot.android.bleabstract.request.SetSmartAlertConfigRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.smartalert.SmartAlertPreferenceManager;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
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
@DebugMetadata(c = "com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$setSmartAlertConfig$1", f = "SmartAlertUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class SmartAlertUtils$Companion$setSmartAlertConfig$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ List<AppNotificationData> $preferenceAppNotificationDataList;
    public final /* synthetic */ List<SmartAlertAppData> $smartAlertAppDataList;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SmartAlertUtils$Companion$setSmartAlertConfig$1(Context context, List<SmartAlertAppData> list, List<? extends AppNotificationData> list2, Continuation<? super SmartAlertUtils$Companion$setSmartAlertConfig$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$smartAlertAppDataList = list;
        this.$preferenceAppNotificationDataList = list2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SmartAlertUtils$Companion$setSmartAlertConfig$1(this.$context, this.$smartAlertAppDataList, this.$preferenceAppNotificationDataList, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SmartAlertUtils$Companion$setSmartAlertConfig$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        BleApi bleApi;
        DeviceSupportedFeatures deviceSupportedFeatures;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BleApiManager bleApiManager = BleApiManager.getInstance(this.$context);
            boolean z = false;
            if (bleApiManager != null && (bleApi = bleApiManager.getBleApi()) != null && (deviceSupportedFeatures = bleApi.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isSmartAlertsSupported()) {
                z = true;
            }
            if (z) {
                SetSmartAlertConfigRequest setSmartAlertConfigRequest = new SetSmartAlertConfigRequest(!this.$smartAlertAppDataList.isEmpty(), this.$smartAlertAppDataList);
                BleApi bleApi2 = BleApiManager.getInstance(this.$context).getBleApi();
                final Context context = this.$context;
                final List<AppNotificationData> list = this.$preferenceAppNotificationDataList;
                bleApi2.setUserSettings(setSmartAlertConfigRequest, new SettingsResultListener() { // from class: com.coveiot.android.smartalert.util.SmartAlertUtils$Companion$setSmartAlertConfig$1.1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        String tag = SmartAlertUtils.Companion.getTAG();
                        LogHelper.d(tag, "SetSmartAlertConfigRequest onSettingsError " + error.getErrorMsg());
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        SmartAlertPreferenceManager.Companion.getInstance(context).saveSmartAlertAppNotificationsSettings(list);
                        LogHelper.d(SmartAlertUtils.Companion.getTAG(), "SetSmartAlertConfigRequest onSettingsResponse");
                    }
                });
            } else {
                LogHelper.d(SmartAlertUtils.Companion.getTAG(), "Smart alert not supported.");
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
