package com.coveiot.android.leonardo.service;

import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.request.SetNonSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetSmartAlertApplicationContentRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.smartalert.SmartAlertHandler;
import com.coveiot.android.smartalert.model.ParsingOutput;
import com.coveiot.android.smartalert.util.SmartAlertUtils;
import com.coveiot.covepreferences.data.SmartAlertAppServerConfData;
import com.coveiot.sdk.ble.api.model.DisplayPosition;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.service.SocialNotificationListenerService$onNotificationPosted$3", f = "SocialNotificationListenerService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class SocialNotificationListenerService$onNotificationPosted$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Ref.ObjectRef<String> $fullTitleWithoutTrim;
    public final /* synthetic */ Ref.ObjectRef<String> $mTitle;
    public final /* synthetic */ String $packageName;
    public int label;
    public final /* synthetic */ SocialNotificationListenerService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SocialNotificationListenerService$onNotificationPosted$3(SocialNotificationListenerService socialNotificationListenerService, String str, Ref.ObjectRef<String> objectRef, Ref.ObjectRef<String> objectRef2, Continuation<? super SocialNotificationListenerService$onNotificationPosted$3> continuation) {
        super(2, continuation);
        this.this$0 = socialNotificationListenerService;
        this.$packageName = str;
        this.$mTitle = objectRef;
        this.$fullTitleWithoutTrim = objectRef2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new SocialNotificationListenerService$onNotificationPosted$3(this.this$0, this.$packageName, this.$mTitle, this.$fullTitleWithoutTrim, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SocialNotificationListenerService$onNotificationPosted$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String str;
        String str2;
        BleApi bleApi;
        BleApi bleApi2;
        BleApi bleApi3;
        SmartAlertAppServerConfData.DeviceData deviceData;
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SmartAlertUtils.Companion companion = SmartAlertUtils.Companion;
            SocialNotificationListenerService socialNotificationListenerService = this.this$0;
            String packageName = this.$packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
            SmartAlertAppServerConfData smartAlertServerAppConfigByPackageName = companion.getSmartAlertServerAppConfigByPackageName(socialNotificationListenerService, packageName);
            ConnectionStatus connectionStatus = null;
            if (((smartAlertServerAppConfigByPackageName == null || (deviceData = smartAlertServerAppConfigByPackageName.getDeviceData()) == null) ? null : deviceData.getAppId()) != null) {
                String packageName2 = smartAlertServerAppConfigByPackageName != null ? smartAlertServerAppConfigByPackageName.getPackageName() : null;
                boolean z = false;
                if (!(packageName2 == null || packageName2.length() == 0)) {
                    SocialNotificationListenerService socialNotificationListenerService2 = this.this$0;
                    String packageName3 = smartAlertServerAppConfigByPackageName.getPackageName();
                    Intrinsics.checkNotNull(packageName3);
                    ParsingOutput parsedMessage = new SmartAlertHandler(socialNotificationListenerService2, packageName3).getParsedMessage(this.this$0, this.$mTitle.element, this.$fullTitleWithoutTrim.element);
                    BleApiManager bleApiManager = BleApiManager.getInstance(this.this$0);
                    if ((bleApiManager != null ? bleApiManager.getBleApi() : null) != null) {
                        BleApiManager bleApiManager2 = BleApiManager.getInstance(this.this$0);
                        if (bleApiManager2 != null && (bleApi3 = bleApiManager2.getBleApi()) != null) {
                            connectionStatus = bleApi3.getConnectionStatus();
                        }
                        if (connectionStatus == ConnectionStatus.CONNECTED) {
                            if (parsedMessage == null) {
                                str2 = this.this$0.h;
                                LogHelper.d(str2, "smartAlertHandler parsingOutput is null");
                            } else {
                                if (parsedMessage.isParsed()) {
                                    List<DynamicSportsField> dynamicSportFields = parsedMessage.getDynamicSportFields();
                                    if (dynamicSportFields == null || dynamicSportFields.isEmpty()) {
                                        z = true;
                                    }
                                    if (!z) {
                                        SmartAlertAppServerConfData.DeviceData deviceData2 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                        Intrinsics.checkNotNull(deviceData2);
                                        Integer appId = deviceData2.getAppId();
                                        Intrinsics.checkNotNull(appId);
                                        int intValue = appId.intValue();
                                        int ordinal = DisplayPosition.LOCK_SCREEN.ordinal();
                                        List<DynamicSportsField> dynamicSportFields2 = parsedMessage.getDynamicSportFields();
                                        Intrinsics.checkNotNull(dynamicSportFields2);
                                        SetSmartAlertApplicationContentRequest setSmartAlertApplicationContentRequest = new SetSmartAlertApplicationContentRequest(intValue, ordinal, dynamicSportFields2);
                                        BleApiManager bleApiManager3 = BleApiManager.getInstance(this.this$0);
                                        if (bleApiManager3 != null && (bleApi2 = bleApiManager3.getBleApi()) != null) {
                                            final SocialNotificationListenerService socialNotificationListenerService3 = this.this$0;
                                            bleApi2.setUserSettings(setSmartAlertApplicationContentRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.service.SocialNotificationListenerService$onNotificationPosted$3.1
                                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                                public void onSettingsError(@NotNull BleBaseError error) {
                                                    String str3;
                                                    Intrinsics.checkNotNullParameter(error, "error");
                                                    str3 = SocialNotificationListenerService.this.h;
                                                    LogHelper.d(str3, error.getErrorMsg());
                                                }

                                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                                    String str3;
                                                    Intrinsics.checkNotNullParameter(response, "response");
                                                    str3 = SocialNotificationListenerService.this.h;
                                                    LogHelper.d(str3, response.toString());
                                                }
                                            });
                                        }
                                    }
                                }
                                SmartAlertAppServerConfData.DeviceData deviceData3 = smartAlertServerAppConfigByPackageName.getDeviceData();
                                Intrinsics.checkNotNull(deviceData3);
                                Integer appId2 = deviceData3.getAppId();
                                Intrinsics.checkNotNull(appId2);
                                SetNonSmartAlertApplicationContentRequest setNonSmartAlertApplicationContentRequest = new SetNonSmartAlertApplicationContentRequest(appId2.intValue(), DisplayPosition.LOCK_SCREEN.ordinal(), this.$mTitle.element, this.$fullTitleWithoutTrim.element);
                                BleApiManager bleApiManager4 = BleApiManager.getInstance(this.this$0);
                                if (bleApiManager4 != null && (bleApi = bleApiManager4.getBleApi()) != null) {
                                    final SocialNotificationListenerService socialNotificationListenerService4 = this.this$0;
                                    bleApi.setUserSettings(setNonSmartAlertApplicationContentRequest, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.service.SocialNotificationListenerService$onNotificationPosted$3.2
                                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                        public void onSettingsError(@NotNull BleBaseError error) {
                                            String str3;
                                            Intrinsics.checkNotNullParameter(error, "error");
                                            str3 = SocialNotificationListenerService.this.h;
                                            LogHelper.d(str3, error.getErrorMsg());
                                        }

                                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                                            String str3;
                                            Intrinsics.checkNotNullParameter(response, "response");
                                            str3 = SocialNotificationListenerService.this.h;
                                            LogHelper.d(str3, response.toString());
                                        }
                                    });
                                }
                            }
                        }
                    }
                    str = this.this$0.h;
                    LogHelper.d(str, "smart alert watch not connected");
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
