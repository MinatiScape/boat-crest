package com.coveiot.android.remotecommandframework.alexa.service;

import android.content.Context;
import com.coveiot.android.remotecommandframework.alexa.utils.Constants;
import com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener;
import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.rcf.mqtt.DeviceMqttInfoManager;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.device.rcf.mqtt.model.ServerToThingCommand;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.utils.utility.LogHelper;
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
@DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$connect$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class AlexaRemoteCommandFrameworkService$connect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ OnSuccessFailureListener $successListener;
    public int label;
    public final /* synthetic */ AlexaRemoteCommandFrameworkService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaRemoteCommandFrameworkService$connect$1(AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService, OnSuccessFailureListener onSuccessFailureListener, Continuation<? super AlexaRemoteCommandFrameworkService$connect$1> continuation) {
        super(2, continuation);
        this.this$0 = alexaRemoteCommandFrameworkService;
        this.$successListener = onSuccessFailureListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AlexaRemoteCommandFrameworkService$connect$1(this.this$0, this.$successListener, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AlexaRemoteCommandFrameworkService$connect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
            final AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService = this.this$0;
            final OnSuccessFailureListener onSuccessFailureListener = this.$successListener;
            DeviceMqttInfoManager.getIotMqttInfo(userDeviceID, new CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$connect$1.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    String str;
                    str = AlexaRemoteCommandFrameworkService.this.h;
                    LogHelper.e(str, "Credentials download failed.");
                    onSuccessFailureListener.onFailure("Credentials download failed.");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetIotMqttInfoResponse getIotMqttInfoResponse) {
                    String str;
                    Integer num;
                    ServerToThingCommand serverToThingCommand;
                    if (getIotMqttInfoResponse == null || (num = getIotMqttInfoResponse.code) == null || num.intValue() != 200 || (serverToThingCommand = getIotMqttInfoResponse.s2tCmd) == null || serverToThingCommand.req == null || serverToThingCommand.res == null) {
                        str = AlexaRemoteCommandFrameworkService.this.h;
                        LogHelper.e(str, "Credentials download failed with p0 != null && p0.code == 200.");
                        onSuccessFailureListener.onFailure("Credentials download failed with p0 != null && p0.code == 200.");
                        return;
                    }
                    String str2 = getIotMqttInfoResponse.clientId;
                    Intrinsics.checkNotNullExpressionValue(str2, "p0.clientId");
                    String valueOf = String.valueOf(getIotMqttInfoResponse.port);
                    ServerToThingCommand serverToThingCommand2 = getIotMqttInfoResponse.s2tCmd;
                    String str3 = serverToThingCommand2.res.topic;
                    String str4 = serverToThingCommand2.req.topic;
                    Intrinsics.checkNotNullExpressionValue(str4, "p0.s2tCmd.req.topic");
                    Integer num2 = getIotMqttInfoResponse.s2tCmd.req.qos;
                    Intrinsics.checkNotNullExpressionValue(num2, "p0.s2tCmd.req.qos");
                    int intValue = num2.intValue();
                    String value = Constants.CONTENT_TYPE_CBOR.getValue();
                    Integer num3 = getIotMqttInfoResponse.keepAlive;
                    Intrinsics.checkNotNullExpressionValue(num3, "p0.keepAlive");
                    MQTTConnectionParams mQTTConnectionParams = new MQTTConnectionParams(str2, "ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port, valueOf, null, str3, str4, false, false, intValue, value, null, null, 0, num3.intValue(), null, null, null, 122056, null);
                    AlexaRemoteCommandFrameworkService.this.n(getIotMqttInfoResponse);
                    MQTTClient mqttClient = AlexaRemoteCommandFrameworkService.this.getMqttClient();
                    if (mqttClient != null) {
                        Context applicationContext = AlexaRemoteCommandFrameworkService.this.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        final OnSuccessFailureListener onSuccessFailureListener2 = onSuccessFailureListener;
                        mqttClient.connect(applicationContext, mQTTConnectionParams, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$connect$1$1$onSuccess$1
                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onFailure(@Nullable String str5) {
                                OnSuccessFailureListener.this.onFailure(str5);
                            }

                            @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                            public void onSuccess() {
                                OnSuccessFailureListener.this.onSuccess();
                            }
                        });
                    }
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
