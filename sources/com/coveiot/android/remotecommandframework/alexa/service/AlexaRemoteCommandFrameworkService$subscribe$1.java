package com.coveiot.android.remotecommandframework.alexa.service;

import com.coveiot.android.remotecommandframework.RcfPreferenceManager;
import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.device.rcf.mqtt.DeviceMqttInfoManager;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.device.rcf.mqtt.model.ServerToThingCommand;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$subscribe$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class AlexaRemoteCommandFrameworkService$subscribe$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ AlexaRemoteCommandFrameworkService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaRemoteCommandFrameworkService$subscribe$1(AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService, Continuation<? super AlexaRemoteCommandFrameworkService$subscribe$1> continuation) {
        super(2, continuation);
        this.this$0 = alexaRemoteCommandFrameworkService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AlexaRemoteCommandFrameworkService$subscribe$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AlexaRemoteCommandFrameworkService$subscribe$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String str;
        MQTTClient mqttClient;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!SessionManager.getInstance(this.this$0).getAlexaAccountLinkingStatus() || !this.this$0.isConnected()) {
                str = this.this$0.h;
                LogHelper.e(str, "Alexa account linking not done.");
            } else {
                RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
                String topicSub = companion.getInstance(this.this$0).getTopicSub();
                if (topicSub == null || topicSub.length() == 0) {
                    String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
                    final AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService = this.this$0;
                    DeviceMqttInfoManager.getIotMqttInfo(userDeviceID, new CoveApiListener<GetIotMqttInfoResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$subscribe$1.1
                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                            String str2;
                            str2 = AlexaRemoteCommandFrameworkService.this.h;
                            LogHelper.e(str2, "Credentials download failed.");
                        }

                        @Override // com.coveiot.coveaccess.CoveApiListener
                        public void onSuccess(@Nullable GetIotMqttInfoResponse getIotMqttInfoResponse) {
                            String str2;
                            Integer num;
                            ServerToThingCommand serverToThingCommand;
                            MQTTClient mqttClient2;
                            if (getIotMqttInfoResponse == null || (num = getIotMqttInfoResponse.code) == null || num.intValue() != 200 || (serverToThingCommand = getIotMqttInfoResponse.s2tCmd) == null || serverToThingCommand.req == null || serverToThingCommand.res == null) {
                                str2 = AlexaRemoteCommandFrameworkService.this.h;
                                LogHelper.e(str2, "Credentials download failed with p0 != null && p0.code == 200.");
                                return;
                            }
                            AlexaRemoteCommandFrameworkService.this.n(getIotMqttInfoResponse);
                            RcfPreferenceManager.Companion companion2 = RcfPreferenceManager.Companion;
                            String topicSub2 = companion2.getInstance(AlexaRemoteCommandFrameworkService.this).getTopicSub();
                            if (topicSub2 != null) {
                                AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService2 = AlexaRemoteCommandFrameworkService.this;
                                String requestContentType = companion2.getInstance(alexaRemoteCommandFrameworkService2).getRequestContentType();
                                if (requestContentType == null || (mqttClient2 = alexaRemoteCommandFrameworkService2.getMqttClient()) == null) {
                                    return;
                                }
                                mqttClient2.subscribe(topicSub2, companion2.getInstance(alexaRemoteCommandFrameworkService2).getRequestQOS(), requestContentType, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$subscribe$1$1$onSuccess$1$1$1
                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onFailure(@Nullable String str3) {
                                    }

                                    @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                    public void onSuccess() {
                                    }
                                });
                            }
                        }
                    });
                } else {
                    String topicSub2 = companion.getInstance(this.this$0).getTopicSub();
                    if (topicSub2 != null) {
                        AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService2 = this.this$0;
                        String requestContentType = companion.getInstance(alexaRemoteCommandFrameworkService2).getRequestContentType();
                        if (requestContentType != null && (mqttClient = alexaRemoteCommandFrameworkService2.getMqttClient()) != null) {
                            mqttClient.subscribe(topicSub2, companion.getInstance(alexaRemoteCommandFrameworkService2).getRequestQOS(), requestContentType, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$subscribe$1$2$1$1
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str2) {
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                }
                            });
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
