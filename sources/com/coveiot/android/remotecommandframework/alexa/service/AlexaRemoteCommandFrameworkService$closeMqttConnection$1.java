package com.coveiot.android.remotecommandframework.alexa.service;

import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
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
@DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$closeMqttConnection$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
public final class AlexaRemoteCommandFrameworkService$closeMqttConnection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ AlexaRemoteCommandFrameworkService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaRemoteCommandFrameworkService$closeMqttConnection$1(AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService, Continuation<? super AlexaRemoteCommandFrameworkService$closeMqttConnection$1> continuation) {
        super(2, continuation);
        this.this$0 = alexaRemoteCommandFrameworkService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AlexaRemoteCommandFrameworkService$closeMqttConnection$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AlexaRemoteCommandFrameworkService$closeMqttConnection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        MQTTClient mqttClient;
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.getMqttClient() != null) {
                MQTTClient mqttClient2 = this.this$0.getMqttClient();
                Boolean boxBoolean = mqttClient2 != null ? Boxing.boxBoolean(mqttClient2.isConnected()) : null;
                Intrinsics.checkNotNull(boxBoolean);
                if (boxBoolean.booleanValue() && (mqttClient = this.this$0.getMqttClient()) != null) {
                    mqttClient.disconnect(new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$closeMqttConnection$1.1
                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onFailure(@Nullable String str) {
                        }

                        @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                        public void onSuccess() {
                        }
                    });
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
