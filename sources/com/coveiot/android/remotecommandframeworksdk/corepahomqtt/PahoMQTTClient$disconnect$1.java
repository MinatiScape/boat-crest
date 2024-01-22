package com.coveiot.android.remotecommandframeworksdk.corepahomqtt;

import com.coveiot.android.remotecommandframeworksdk.IConnectionObserver;
import com.coveiot.android.remotecommandframeworksdk.IObserver;
import com.coveiot.android.remotecommandframeworksdk.PreferenceManager;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PahoMQTTClient$disconnect$1 implements Runnable {
    public final /* synthetic */ SuccessListener $listener;
    public final /* synthetic */ PahoMQTTClient this$0;

    public PahoMQTTClient$disconnect$1(PahoMQTTClient pahoMQTTClient, SuccessListener successListener) {
        this.this$0 = pahoMQTTClient;
        this.$listener = successListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SuccessListener successListener;
        String message;
        MqttAndroidClient mqttAndroidClient;
        try {
            mqttAndroidClient = this.this$0.o;
            if (mqttAndroidClient != null) {
                Object obj = null;
                if (mqttAndroidClient.isConnected()) {
                    Pair<String[], int[]> allSubscription = this.this$0.getAllSubscription();
                    if (!TuplesKt.toList(allSubscription).isEmpty()) {
                        mqttAndroidClient.unsubscribe(allSubscription.getFirst());
                        this.this$0.clearAllTopic();
                    }
                    obj = mqttAndroidClient.disconnect(null, new IMqttActionListener() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$disconnect$1$$special$$inlined$let$lambda$1
                        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onFailure(@Nullable IMqttToken iMqttToken, @Nullable Throwable th) {
                            List<IObserver> observers;
                            SuccessListener successListener2 = PahoMQTTClient$disconnect$1.this.$listener;
                            if (successListener2 != null) {
                                successListener2.onFailure(th != null ? th.getMessage() : null);
                            }
                            observers = PahoMQTTClient$disconnect$1.this.this$0.getObservers();
                            for (IObserver iObserver : observers) {
                                if (iObserver instanceof IConnectionObserver) {
                                    ((IConnectionObserver) iObserver).onConnectionUpdate(false);
                                }
                            }
                        }

                        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                        public void onSuccess(@Nullable IMqttToken iMqttToken) {
                            MqttAndroidClient mqttAndroidClient2;
                            List<IObserver> observers;
                            PreferenceManager.Companion.getInstance(PahoMQTTClient$disconnect$1.this.this$0.getContext()).clearCertificates();
                            mqttAndroidClient2 = PahoMQTTClient$disconnect$1.this.this$0.o;
                            if (mqttAndroidClient2 != null) {
                                mqttAndroidClient2.close();
                            }
                            PahoMQTTClient$disconnect$1.this.this$0.clearAndroidMqttClient();
                            SuccessListener successListener2 = PahoMQTTClient$disconnect$1.this.$listener;
                            if (successListener2 != null) {
                                successListener2.onSuccess();
                            }
                            observers = PahoMQTTClient$disconnect$1.this.this$0.getObservers();
                            for (IObserver iObserver : observers) {
                                if (iObserver instanceof IConnectionObserver) {
                                    ((IConnectionObserver) iObserver).onConnectionUpdate(false);
                                }
                            }
                        }
                    });
                } else {
                    PreferenceManager.Companion.getInstance(this.this$0.getContext()).clearCertificates();
                    SuccessListener successListener2 = this.$listener;
                    if (successListener2 != null) {
                        successListener2.onSuccess();
                        obj = Unit.INSTANCE;
                    }
                }
                if (obj != null) {
                    return;
                }
            }
            PreferenceManager.Companion.getInstance(this.this$0.getContext()).clearCertificates();
            SuccessListener successListener3 = this.$listener;
            if (successListener3 != null) {
                successListener3.onSuccess();
            }
        } catch (MqttException e) {
            e = e;
            successListener = this.$listener;
            if (successListener != null) {
                message = e.getMessage();
                successListener.onFailure(message);
            }
            LogHelper.d(this.this$0.getTAG(), "Exception disconnect");
            e.printStackTrace();
        } catch (Exception e2) {
            e = e2;
            successListener = this.$listener;
            if (successListener != null) {
                message = e.getMessage();
                successListener.onFailure(message);
            }
            LogHelper.d(this.this$0.getTAG(), "Exception disconnect");
            e.printStackTrace();
        }
    }
}
