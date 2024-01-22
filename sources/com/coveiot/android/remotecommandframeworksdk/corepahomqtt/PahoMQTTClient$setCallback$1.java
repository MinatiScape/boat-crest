package com.coveiot.android.remotecommandframeworksdk.corepahomqtt;

import android.os.Handler;
import com.coveiot.android.remotecommandframeworksdk.GeneralPreferenceManager;
import com.coveiot.android.remotecommandframeworksdk.IConnectionObserver;
import com.coveiot.android.remotecommandframeworksdk.IObserver;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"com/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient$setCallback$1", "Lorg/eclipse/paho/client/mqttv3/MqttCallbackExtended;", "", "b", "", "s", "", "connectComplete", "(ZLjava/lang/String;)V", "", "throwable", "connectionLost", "(Ljava/lang/Throwable;)V", "topic", "Lorg/eclipse/paho/client/mqttv3/MqttMessage;", "mqttMessage", MqttServiceConstants.MESSAGE_ARRIVED_ACTION, "(Ljava/lang/String;Lorg/eclipse/paho/client/mqttv3/MqttMessage;)V", "Lorg/eclipse/paho/client/mqttv3/IMqttDeliveryToken;", "iMqttDeliveryToken", "deliveryComplete", "(Lorg/eclipse/paho/client/mqttv3/IMqttDeliveryToken;)V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PahoMQTTClient$setCallback$1 implements MqttCallbackExtended {
    public final /* synthetic */ MQTTConnectionParams $connectionParams;
    public final /* synthetic */ PahoMQTTClient this$0;

    public PahoMQTTClient$setCallback$1(PahoMQTTClient pahoMQTTClient, MQTTConnectionParams mQTTConnectionParams) {
        this.this$0 = pahoMQTTClient;
        this.$connectionParams = mQTTConnectionParams;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
    public void connectComplete(boolean z, @NotNull String s) {
        List<IObserver> observers;
        Intrinsics.checkNotNullParameter(s, "s");
        LogHelper.i(this.this$0.getTAG(), s);
        PahoMQTTClient pahoMQTTClient = this.this$0;
        MQTTConnectionParams mQTTConnectionParams = this.$connectionParams;
        Intrinsics.checkNotNull(mQTTConnectionParams);
        pahoMQTTClient.subscribe(mQTTConnectionParams.getTopicToSubscribe(), this.$connectionParams.getReqQos(), this.$connectionParams.getReqContentType(), null);
        observers = this.this$0.getObservers();
        for (IObserver iObserver : observers) {
            if (iObserver instanceof IConnectionObserver) {
                ((IConnectionObserver) iObserver).onConnectionUpdate(true);
            }
        }
        GeneralPreferenceManager.Companion.getInstance(this.this$0.getContext()).saveLastConnectionTimestamp(System.currentTimeMillis());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void connectionLost(@Nullable Throwable th) {
        List<IObserver> observers;
        String tag = this.this$0.getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("connectionLost->");
        sb.append(th != null ? th.getLocalizedMessage() : null);
        LogHelper.d(tag, sb.toString());
        observers = this.this$0.getObservers();
        for (IObserver iObserver : observers) {
            if (iObserver instanceof IConnectionObserver) {
                ((IConnectionObserver) iObserver).onConnectionUpdate(false);
            }
        }
        GeneralPreferenceManager.Companion.getInstance(this.this$0.getContext()).saveLastConnectionLostTimestamp(System.currentTimeMillis());
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void deliveryComplete(@NotNull IMqttDeliveryToken iMqttDeliveryToken) {
        Intrinsics.checkNotNullParameter(iMqttDeliveryToken, "iMqttDeliveryToken");
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void messageArrived(@NotNull final String topic, @NotNull final MqttMessage mqttMessage) {
        Handler mHandler;
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(mqttMessage, "mqttMessage");
        mHandler = this.this$0.getMHandler();
        mHandler.post(new Runnable() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1$messageArrived$1
            /* JADX WARN: Removed duplicated region for block: B:24:0x00b3  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r4 = this;
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1 r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1.this
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = r0.this$0
                    java.lang.String r1 = r2
                    com.coveiot.android.remotecommandframeworksdk.MQTTClient$Topic r0 = r0.getSubscriptionBy(r1)
                    java.lang.String r1 = "mqttMessage.payload"
                    if (r0 == 0) goto L6a
                    java.lang.String r2 = r0.getContentType()
                    r3 = 1
                    if (r2 == 0) goto L1e
                    int r2 = r2.length()
                    if (r2 != 0) goto L1c
                    goto L1e
                L1c:
                    r2 = 0
                    goto L1f
                L1e:
                    r2 = r3
                L1f:
                    if (r2 != 0) goto L6a
                    java.lang.String r0 = r0.getContentType()
                    com.coveiot.android.remotecommandframeworksdk.utils.Constants r2 = com.coveiot.android.remotecommandframeworksdk.utils.Constants.CONTENT_TYPE_CBOR
                    java.lang.String r2 = r2.getValue()
                    boolean r0 = kotlin.text.m.equals(r0, r2, r3)
                    if (r0 == 0) goto L6a
                    org.eclipse.paho.client.mqttv3.MqttMessage r0 = r3     // Catch: java.lang.OutOfMemoryError -> L40 com.google.iot.cbor.CborParseException -> L56
                    byte[] r0 = r0.getPayload()     // Catch: java.lang.OutOfMemoryError -> L40 com.google.iot.cbor.CborParseException -> L56
                    com.google.iot.cbor.CborMap r0 = com.google.iot.cbor.CborMap.createFromCborByteArray(r0)     // Catch: java.lang.OutOfMemoryError -> L40 com.google.iot.cbor.CborParseException -> L56
                    java.lang.String r0 = r0.toJsonString()     // Catch: java.lang.OutOfMemoryError -> L40 com.google.iot.cbor.CborParseException -> L56
                    goto L64
                L40:
                    r0 = move-exception
                    r0.printStackTrace()
                    org.eclipse.paho.client.mqttv3.MqttMessage r0 = r3
                    byte[] r0 = r0.getPayload()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.lang.String r1 = new java.lang.String
                    java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8
                    r1.<init>(r0, r2)
                    r0 = r1
                    goto L64
                L56:
                    org.eclipse.paho.client.mqttv3.MqttMessage r0 = r3
                    byte[] r0 = r0.getPayload()
                    com.google.iot.cbor.CborObject r0 = com.google.iot.cbor.CborObject.createFromCborByteArray(r0)
                    java.lang.String r0 = r0.toJsonString()
                L64:
                    java.lang.String r1 = "try {\n                  …                        }"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    goto L7b
                L6a:
                    org.eclipse.paho.client.mqttv3.MqttMessage r0 = r3
                    byte[] r0 = r0.getPayload()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                    java.lang.String r1 = new java.lang.String
                    java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8
                    r1.<init>(r0, r2)
                    r0 = r1
                L7b:
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1 r1 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1.this
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r1 = r1.this$0
                    java.lang.String r1 = r1.getTAG()
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "messageArrived-> "
                    r2.append(r3)
                    java.lang.String r3 = r2
                    r2.append(r3)
                    r3 = 32
                    r2.append(r3)
                    r2.append(r0)
                    java.lang.String r2 = r2.toString()
                    com.coveiot.utils.utility.LogHelper.d(r1, r2)
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1 r1 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1.this
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r1 = r1.this$0
                    java.util.List r1 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getObservers$p(r1)
                    java.util.Iterator r1 = r1.iterator()
                Lad:
                    boolean r2 = r1.hasNext()
                    if (r2 == 0) goto Lc5
                    java.lang.Object r2 = r1.next()
                    com.coveiot.android.remotecommandframeworksdk.IObserver r2 = (com.coveiot.android.remotecommandframeworksdk.IObserver) r2
                    boolean r3 = r2 instanceof com.coveiot.android.remotecommandframeworksdk.IMessageObserver
                    if (r3 == 0) goto Lad
                    com.coveiot.android.remotecommandframeworksdk.IMessageObserver r2 = (com.coveiot.android.remotecommandframeworksdk.IMessageObserver) r2
                    java.lang.String r3 = r2
                    r2.onMessageUpdate(r3, r0)
                    goto Lad
                Lc5:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$setCallback$1$messageArrived$1.run():void");
            }
        });
    }
}
