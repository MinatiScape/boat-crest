package com.coveiot.android.remotecommandframeworksdk.corepahomqtt;

import android.content.Context;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.remotecommandframeworksdk.IObserver;
import com.coveiot.android.remotecommandframeworksdk.ISubscriptionObserver;
import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams;
import com.coveiot.android.remotecommandframeworksdk.utils.SingletonHolder;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.iot.cbor.CborMap;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.m;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\u0018\u0000 +2\u00020\u0001:\u0001+B\u0011\b\u0002\u0012\u0006\u0010(\u001a\u00020\u0005¢\u0006\u0004\b)\u0010*J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J)\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J1\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ/\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ/\u0010 \u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b \u0010\u001eJ/\u0010!\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b!\u0010\u001eJ/\u0010\"\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\"\u0010\u001eR\u001c\u0010'\u001a\u00020\u00128\u0016@\u0016X\u0096D¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&¨\u0006,"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient;", "Lcom/coveiot/android/remotecommandframeworksdk/MQTTClient;", "", "isConnected", "()Z", "Landroid/content/Context;", "context", "Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;", "connectionParams", "Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", MqttServiceConstants.CONNECT_ACTION, "(Landroid/content/Context;Lcom/coveiot/android/remotecommandframeworksdk/utils/MQTTConnectionParams;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", "clearAndroidMqttClient", "()V", MqttServiceConstants.DISCONNECT_ACTION, "(Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", "", "topic", "", MqttServiceConstants.QOS, CMSAttributeTableGenerator.CONTENT_TYPE, "successListener", MqttServiceConstants.SUBSCRIBE_ACTION, "(Ljava/lang/String;ILjava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", MqttServiceConstants.UNSUBSCRIBE_ACTION, "(Ljava/lang/String;Lcom/coveiot/android/remotecommandframeworksdk/listener/SuccessListener;)V", Constants.KEY_MESSAGE, "publish", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "msg", "onSendResponseAck", "onSendResponse", "onResetRetainFlag", "n", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "TAG", "mContext", "<init>", "(Landroid/content/Context;)V", "Companion", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PahoMQTTClient extends MQTTClient {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final String n;
    public MqttAndroidClient o;
    public MQTTConnectionParams p;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient$Companion;", "Lcom/coveiot/android/remotecommandframeworksdk/utils/SingletonHolder;", "Lcom/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient;", "Landroid/content/Context;", "<init>", "()V", "remotecommandframeworksdk_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    public static final class Companion extends SingletonHolder<PahoMQTTClient, Context> {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroid/content/Context;", "p1", "Lcom/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient;", "invoke", "(Landroid/content/Context;)Lcom/coveiot/android/remotecommandframeworksdk/corepahomqtt/PahoMQTTClient;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$Companion$1  reason: invalid class name */
        /* loaded from: classes6.dex */
        public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Context, PahoMQTTClient> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1, PahoMQTTClient.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final PahoMQTTClient invoke(@NotNull Context p1) {
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new PahoMQTTClient(p1, null);
            }
        }

        public Companion() {
            super(AnonymousClass1.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PahoMQTTClient(Context context) {
        super(context);
        this.n = "PahoMQTTClient";
    }

    public /* synthetic */ PahoMQTTClient(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void a(Context context, final MQTTConnectionParams mQTTConnectionParams, final SuccessListener successListener) {
        getMHandler().post(new Runnable() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connectL$1
            /* JADX WARN: Code restructure failed: missing block: B:17:0x0041, code lost:
                if (kotlin.text.m.equals$default(r0 != null ? r0.getClientId() : null, r2.getClientId(), false, 2, null) == false) goto L16;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r6 = this;
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r0 = r2
                    if (r0 == 0) goto Le0
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getMConnectionParams$p(r0)
                    r1 = 0
                    if (r0 == 0) goto L48
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getMConnectionParams$p(r0)
                    if (r0 == 0) goto L1a
                    java.lang.String r0 = r0.getHost()
                    goto L1b
                L1a:
                    r0 = r1
                L1b:
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    java.lang.String r2 = r2.getHost()
                    r3 = 0
                    r4 = 2
                    boolean r0 = kotlin.text.m.equals$default(r0, r2, r3, r4, r1)
                    if (r0 == 0) goto L43
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getMConnectionParams$p(r0)
                    if (r0 == 0) goto L36
                    java.lang.String r0 = r0.getClientId()
                    goto L37
                L36:
                    r0 = r1
                L37:
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    java.lang.String r2 = r2.getClientId()
                    boolean r0 = kotlin.text.m.equals$default(r0, r2, r3, r4, r1)
                    if (r0 != 0) goto L48
                L43:
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    r0.clearAndroidMqttClient()
                L48:
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$setMConnectionParams$p(r0, r2)
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$initAndroidMQTTClient(r0, r2)
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$setCallback(r0, r2)
                    org.eclipse.paho.client.mqttv3.MqttConnectOptions r0 = new org.eclipse.paho.client.mqttv3.MqttConnectOptions
                    r0.<init>()
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    boolean r2 = r2.isAutomaticReconnect()
                    r0.setAutomaticReconnect(r2)
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    boolean r2 = r2.isCleanSession()
                    r0.setCleanSession(r2)
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    int r2 = r2.getConnectionTimeout()
                    r0.setConnectionTimeout(r2)
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    int r2 = r2.getKeepAliveInterval()
                    r0.setKeepAliveInterval(r2)
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r2 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
                    java.io.InputStream r2 = r2.getCaFileInputStream()
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r3 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                    java.io.InputStream r3 = r3.getCertFileInputStream()
                    com.coveiot.android.remotecommandframeworksdk.utils.MQTTConnectionParams r4 = r2
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                    java.io.InputStream r4 = r4.getKeyFileInputStream()
                    java.lang.String r5 = ""
                    javax.net.ssl.SSLSocketFactory r2 = com.coveiot.android.remotecommandframeworksdk.utils.SslUtil.getSocketFactory(r2, r3, r4, r5)
                    r0.setSocketFactory(r2)
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r2 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this     // Catch: java.lang.Exception -> Lc7 org.eclipse.paho.client.mqttv3.MqttException -> Ld0
                    org.eclipse.paho.android.service.MqttAndroidClient r2 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getMqttClient$p(r2)     // Catch: java.lang.Exception -> Lc7 org.eclipse.paho.client.mqttv3.MqttException -> Ld0
                    if (r2 == 0) goto Leb
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connectL$1$1 r3 = new com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connectL$1$1     // Catch: java.lang.Exception -> Lc7 org.eclipse.paho.client.mqttv3.MqttException -> Ld0
                    r3.<init>()     // Catch: java.lang.Exception -> Lc7 org.eclipse.paho.client.mqttv3.MqttException -> Ld0
                    r2.connect(r0, r1, r3)     // Catch: java.lang.Exception -> Lc7 org.eclipse.paho.client.mqttv3.MqttException -> Ld0
                    goto Leb
                Lc7:
                    r0 = move-exception
                    r0.printStackTrace()
                    com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener r1 = r3
                    if (r1 == 0) goto Leb
                    goto Ld8
                Ld0:
                    r0 = move-exception
                    r0.printStackTrace()
                    com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener r1 = r3
                    if (r1 == 0) goto Leb
                Ld8:
                    java.lang.String r0 = r0.getLocalizedMessage()
                    r1.onFailure(r0)
                    goto Leb
                Le0:
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r0 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    java.lang.String r0 = r0.getTAG()
                    java.lang.String r1 = "Connection params is null"
                    com.coveiot.utils.utility.LogHelper.e(r0, r1)
                Leb:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$connectL$1.run():void");
            }
        });
    }

    public final void b(MQTTConnectionParams mQTTConnectionParams) {
        if (this.o == null) {
            Context context = getContext();
            Intrinsics.checkNotNull(mQTTConnectionParams);
            this.o = new MqttAndroidClient(context, mQTTConnectionParams.getHost(), mQTTConnectionParams.getClientId());
        }
    }

    public final void c(MQTTConnectionParams mQTTConnectionParams) {
        MqttAndroidClient mqttAndroidClient = this.o;
        if (mqttAndroidClient != null) {
            mqttAndroidClient.setCallback(new PahoMQTTClient$setCallback$1(this, mQTTConnectionParams));
        }
    }

    public final void clearAndroidMqttClient() {
        this.o = null;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void connect(@NotNull Context context, @NotNull MQTTConnectionParams connectionParams, @Nullable SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(connectionParams, "connectionParams");
        getMHandler().post(new PahoMQTTClient$connect$1(this, context, connectionParams, successListener));
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void disconnect(@Nullable SuccessListener successListener) {
        getMHandler().post(new PahoMQTTClient$disconnect$1(this, successListener));
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient
    @NotNull
    public String getTAG() {
        return this.n;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient
    public boolean isConnected() {
        MqttAndroidClient mqttAndroidClient = this.o;
        if (mqttAndroidClient != null) {
            Intrinsics.checkNotNull(mqttAndroidClient);
            if (mqttAndroidClient.isConnected()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onResetRetainFlag(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        publish(msg, topic, i, contentType);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponse(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        publish(msg, topic, i, contentType);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IResponseObserver
    public void onSendResponseAck(@NotNull String msg, @NotNull String topic, int i, @NotNull String contentType) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        publish(msg, topic, i, contentType);
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void publish(@NotNull final String message, @NotNull final String topic, final int i, @NotNull final String contentType) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        getMHandler().post(new Runnable() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$publish$1
            @Override // java.lang.Runnable
            public final void run() {
                MqttAndroidClient mqttAndroidClient;
                byte[] bytes;
                try {
                    mqttAndroidClient = PahoMQTTClient.this.o;
                    if (mqttAndroidClient != null) {
                        String str = topic;
                        if (m.equals(contentType, com.coveiot.android.remotecommandframeworksdk.utils.Constants.CONTENT_TYPE_CBOR.getValue(), true)) {
                            bytes = CborMap.createFromJSONObject(new JSONObject(message)).toCborByteArray();
                        } else {
                            String str2 = message;
                            Charset charset = Charsets.UTF_8;
                            if (str2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            }
                            bytes = str2.getBytes(charset);
                            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                        }
                        mqttAndroidClient.publish(str, bytes, i, false, null, new IMqttActionListener() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$publish$1.1
                            @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                            public void onFailure(@Nullable IMqttToken iMqttToken, @Nullable Throwable th) {
                                List<IObserver> observers;
                                LogHelper.e(PahoMQTTClient.this.getTAG(), "Publish Failed!");
                                observers = PahoMQTTClient.this.getObservers();
                                for (IObserver iObserver : observers) {
                                    if (iObserver instanceof ISubscriptionObserver) {
                                        ((ISubscriptionObserver) iObserver).onStateUpdate("Failed to Publish to Topic");
                                    }
                                }
                            }

                            @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                            public void onSuccess(@Nullable IMqttToken iMqttToken) {
                                List<IObserver> observers;
                                String tag = PahoMQTTClient.this.getTAG();
                                LogHelper.d(tag, "Publish Success->" + message);
                                observers = PahoMQTTClient.this.getObservers();
                                for (IObserver iObserver : observers) {
                                    if (iObserver instanceof ISubscriptionObserver) {
                                        ((ISubscriptionObserver) iObserver).onStateUpdate("Published to Topic");
                                    }
                                }
                            }
                        });
                    }
                } catch (MqttException | Exception e) {
                    LogHelper.e(PahoMQTTClient.this.getTAG(), "Exception publishing");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void subscribe(@NotNull final String topic, final int i, @NotNull final String contentType, @Nullable final SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        getMHandler().post(new Runnable() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$subscribe$1
            /* JADX WARN: Code restructure failed: missing block: B:5:0x001f, code lost:
                r2 = r6.this$0.o;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r6 = this;
                    java.lang.String r0 = "Exception subscribing"
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r1 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    java.lang.String r2 = r2     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    int r3 = r3     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    java.lang.String r4 = r4     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    r1.addTopic(r2, r3, r4)     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r1 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    kotlin.Pair r1 = r1.getAllSubscription()     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    java.util.List r2 = kotlin.TuplesKt.toList(r1)     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    boolean r2 = r2.isEmpty()     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    r2 = r2 ^ 1
                    if (r2 == 0) goto L4c
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r2 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    org.eclipse.paho.android.service.MqttAndroidClient r2 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.access$getMqttClient$p(r2)     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    if (r2 == 0) goto L4c
                    java.lang.Object r3 = r1.getFirst()     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    java.lang.String[] r3 = (java.lang.String[]) r3     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    java.lang.Object r1 = r1.getSecond()     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    int[] r1 = (int[]) r1     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    r4 = 0
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$subscribe$1$1 r5 = new com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$subscribe$1$1     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    r5.<init>()     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    r2.subscribe(r3, r1, r4, r5)     // Catch: java.lang.Exception -> L3d org.eclipse.paho.client.mqttv3.MqttException -> L3f
                    goto L4c
                L3d:
                    r1 = move-exception
                    goto L40
                L3f:
                    r1 = move-exception
                L40:
                    com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient r2 = com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient.this
                    java.lang.String r2 = r2.getTAG()
                    com.coveiot.utils.utility.LogHelper.e(r2, r0)
                    r1.printStackTrace()
                L4c:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$subscribe$1.run():void");
            }
        });
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.MQTTClient, com.coveiot.android.remotecommandframeworksdk.IPubSubAction
    public void unsubscribe(@NotNull final String topic, @Nullable final SuccessListener successListener) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        getMHandler().post(new Runnable() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$unsubscribe$1
            @Override // java.lang.Runnable
            public final void run() {
                MqttAndroidClient mqttAndroidClient;
                try {
                    if (PahoMQTTClient.this.getSubscriptionBy(topic) != null) {
                        mqttAndroidClient = PahoMQTTClient.this.o;
                        if (mqttAndroidClient != null) {
                            mqttAndroidClient.unsubscribe(topic, (Object) null, new IMqttActionListener() { // from class: com.coveiot.android.remotecommandframeworksdk.corepahomqtt.PahoMQTTClient$unsubscribe$1.1
                                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                                public void onFailure(@Nullable IMqttToken iMqttToken, @Nullable Throwable th) {
                                    List<IObserver> observers;
                                    SuccessListener successListener2 = successListener;
                                    if (successListener2 != null) {
                                        successListener2.onFailure(th != null ? th.getLocalizedMessage() : null);
                                    }
                                    observers = PahoMQTTClient.this.getObservers();
                                    for (IObserver iObserver : observers) {
                                        if (iObserver instanceof ISubscriptionObserver) {
                                            ((ISubscriptionObserver) iObserver).onStateUpdate("Failed to UnSubscribe to Topic");
                                        }
                                    }
                                }

                                @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
                                public void onSuccess(@Nullable IMqttToken iMqttToken) {
                                    List<IObserver> observers;
                                    PahoMQTTClient$unsubscribe$1 pahoMQTTClient$unsubscribe$1 = PahoMQTTClient$unsubscribe$1.this;
                                    PahoMQTTClient.this.removeTopic(topic);
                                    SuccessListener successListener2 = successListener;
                                    if (successListener2 != null) {
                                        successListener2.onSuccess();
                                    }
                                    observers = PahoMQTTClient.this.getObservers();
                                    for (IObserver iObserver : observers) {
                                        if (iObserver instanceof ISubscriptionObserver) {
                                            ((ISubscriptionObserver) iObserver).onStateUpdate("UnSubscribed to Topic");
                                        }
                                    }
                                }
                            });
                        }
                    } else {
                        String tag = PahoMQTTClient.this.getTAG();
                        LogHelper.d(tag, "topic " + topic + " is not subscribed. Unsubscribe failed.");
                    }
                } catch (MqttException | Exception e) {
                    LogHelper.e(PahoMQTTClient.this.getTAG(), "Exception unsubscribe");
                    e.printStackTrace();
                }
            }
        });
    }
}
