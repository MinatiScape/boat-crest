package org.eclipse.paho.client.mqttv3;

import com.clevertap.android.sdk.Constants;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.HighResolutionTimer;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import org.eclipse.paho.client.mqttv3.internal.SystemHighResolutionTimer;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;
/* loaded from: classes13.dex */
public class MqttAsyncClient implements IMqttAsyncClient {
    public static final String s = MqttAsyncClient.class.getName();
    public static int t = 1000;
    public static final Object u = new Object();
    public ClientComms comms;
    public Logger h;
    public String i;
    public String j;
    public Hashtable k;
    public MqttClientPersistence l;
    public MqttCallback m;
    public MqttConnectOptions n;
    public Object o;
    public Timer p;
    public boolean q;
    public ScheduledExecutorService r;

    /* loaded from: classes13.dex */
    public class a implements IMqttActionListener {

        /* renamed from: a  reason: collision with root package name */
        public final String f15436a;

        public a(String str) {
            this.f15436a = str;
        }

        public final void a(int i) {
            MqttAsyncClient.this.h.fine(MqttAsyncClient.s, String.valueOf(this.f15436a) + ":rescheduleReconnectCycle", "505", new Object[]{MqttAsyncClient.this.i, String.valueOf(MqttAsyncClient.t)});
            synchronized (MqttAsyncClient.u) {
                if (MqttAsyncClient.this.n.isAutomaticReconnect()) {
                    if (MqttAsyncClient.this.p != null) {
                        MqttAsyncClient.this.p.schedule(new c(MqttAsyncClient.this, null), i);
                    } else {
                        MqttAsyncClient.t = i;
                        MqttAsyncClient.this.o();
                    }
                }
            }
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            MqttAsyncClient.this.h.fine(MqttAsyncClient.s, this.f15436a, "502", new Object[]{iMqttToken.getClient().getClientId()});
            if (MqttAsyncClient.t < MqttAsyncClient.this.n.getMaxReconnectDelay()) {
                MqttAsyncClient.t *= 2;
            }
            a(MqttAsyncClient.t);
        }

        @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            MqttAsyncClient.this.h.fine(MqttAsyncClient.s, this.f15436a, "501", new Object[]{iMqttToken.getClient().getClientId()});
            MqttAsyncClient.this.comms.setRestingState(false);
            MqttAsyncClient.this.p();
        }
    }

    /* loaded from: classes13.dex */
    public class b implements MqttCallbackExtended {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f15437a;

        public b(boolean z) {
            this.f15437a = z;
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
        public void connectComplete(boolean z, String str) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void connectionLost(Throwable th) {
            if (this.f15437a) {
                MqttAsyncClient.this.comms.setRestingState(true);
                MqttAsyncClient.this.q = true;
                MqttAsyncClient.this.o();
            }
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }

        @Override // org.eclipse.paho.client.mqttv3.MqttCallback
        public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        }
    }

    /* loaded from: classes13.dex */
    public class c extends TimerTask {
        public c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MqttAsyncClient.this.h.fine(MqttAsyncClient.s, "ReconnectTask.run", "506");
            MqttAsyncClient.this.m();
        }

        public /* synthetic */ c(MqttAsyncClient mqttAsyncClient, c cVar) {
            this();
        }
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public static boolean Character_isHighSurrogate(char c2) {
        return c2 >= 55296 && c2 <= 56319;
    }

    public static String generateClientId() {
        return "paho" + System.nanoTime();
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = this.h;
        String str = s;
        logger.fine(str, "ping", "117");
        MqttToken checkForActivity = this.comms.checkForActivity(iMqttActionListener);
        this.h.fine(str, "ping", "118");
        return checkForActivity;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient, java.lang.AutoCloseable
    public void close() throws MqttException {
        close(false);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    public NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        this.h.fine(s, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = n(serverURIs[i], mqttConnectOptions);
        }
        this.h.fine(s, "createNetworkModules", "108");
        return networkModuleArr;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void deleteBufferedMessage(int i) {
        this.comms.deleteBufferedMessage(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(30000L, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(30000L, 10000L);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public MqttMessage getBufferedMessage(int i) {
        return this.comms.getBufferedMessage(i);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getClientId() {
        return this.i;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    public Debug getDebug() {
        return new Debug(this.i, this.comms);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public int getInFlightMessageCount() {
        return this.comms.getActualInFlight();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public String getServerURI() {
        return this.j;
    }

    public MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.k.get(str);
        if (mqttTopic == null) {
            MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
            this.k.put(str, mqttTopic2);
            return mqttTopic2;
        }
        return mqttTopic;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    public final void m() {
        this.h.fine(s, "attemptReconnect", "500", new Object[]{this.i});
        try {
            connect(this.n, this.o, new a("attemptReconnect"));
        } catch (MqttSecurityException e) {
            this.h.fine(s, "attemptReconnect", "804", null, e);
        } catch (MqttException e2) {
            this.h.fine(s, "attemptReconnect", "804", null, e2);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.comms.messageArrivedComplete(i, i2);
    }

    public final NetworkModule n(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        this.h.fine(s, "createNetworkModule", "115", new Object[]{str});
        return NetworkModuleService.createInstance(str, mqttConnectOptions, this.i);
    }

    public final void o() {
        this.h.fine(s, "startReconnectCycle", "503", new Object[]{this.i, Long.valueOf(t)});
        Timer timer = new Timer("MQTT Reconnect: " + this.i);
        this.p = timer;
        timer.schedule(new c(this, null), (long) t);
    }

    public final void p() {
        this.h.fine(s, "stopReconnectCycle", "504", new Object[]{this.i});
        synchronized (u) {
            if (this.n.isAutomaticReconnect()) {
                Timer timer = this.p;
                if (timer != null) {
                    timer.cancel();
                    this.p = null;
                }
                t = 1000;
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    public final IMqttToken q(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.h.isLoggable(5)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("topic=");
                stringBuffer.append(strArr[i]);
                stringBuffer.append(" qos=");
                stringBuffer.append(iArr[i]);
            }
            this.h.fine(s, MqttServiceConstants.SUBSCRIBE_ACTION, "106", new Object[]{stringBuffer.toString(), obj, iMqttActionListener});
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        this.h.fine(s, MqttServiceConstants.SUBSCRIBE_ACTION, "109");
        return mqttToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void reconnect() throws MqttException {
        this.h.fine(s, "reconnect", "500", new Object[]{this.i});
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        p();
                        m();
                        return;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw ExceptionHelper.createMqttException(32100);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        return this.comms.removeMessage(iMqttDeliveryToken);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setCallback(MqttCallback mqttCallback) {
        this.m = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public void close(boolean z) throws MqttException {
        Logger logger = this.h;
        String str = s;
        logger.fine(str, Constants.KEY_HIDE_CLOSE, "113");
        this.comms.close(z);
        this.h.fine(str, Constants.KEY_HIDE_CLOSE, "114");
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return connect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(30000L, j);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this(str, str2, mqttClientPersistence, mqttPingSender, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return connect(mqttConnectOptions, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.comms.disconnectForcibly(j, j2);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ScheduledExecutorService scheduledExecutorService) throws MqttException {
        this(str, str2, mqttClientPersistence, mqttPingSender, scheduledExecutorService, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        if (!this.comms.isConnected()) {
            if (!this.comms.isConnecting()) {
                if (!this.comms.isDisconnecting()) {
                    if (!this.comms.isClosed()) {
                        if (mqttConnectOptions == null) {
                            mqttConnectOptions = new MqttConnectOptions();
                        }
                        MqttConnectOptions mqttConnectOptions2 = mqttConnectOptions;
                        this.n = mqttConnectOptions2;
                        this.o = obj;
                        boolean isAutomaticReconnect = mqttConnectOptions2.isAutomaticReconnect();
                        Logger logger = this.h;
                        String str = s;
                        Object[] objArr = new Object[8];
                        objArr[0] = Boolean.valueOf(mqttConnectOptions2.isCleanSession());
                        objArr[1] = Integer.valueOf(mqttConnectOptions2.getConnectionTimeout());
                        objArr[2] = Integer.valueOf(mqttConnectOptions2.getKeepAliveInterval());
                        objArr[3] = mqttConnectOptions2.getUserName();
                        objArr[4] = mqttConnectOptions2.getPassword() == null ? "[null]" : "[notnull]";
                        objArr[5] = mqttConnectOptions2.getWillMessage() != null ? "[notnull]" : "[null]";
                        objArr[6] = obj;
                        objArr[7] = iMqttActionListener;
                        logger.fine(str, MqttServiceConstants.CONNECT_ACTION, "103", objArr);
                        this.comms.setNetworkModules(createNetworkModules(this.j, mqttConnectOptions2));
                        this.comms.setReconnectCallback(new b(isAutomaticReconnect));
                        MqttToken mqttToken = new MqttToken(getClientId());
                        ConnectActionListener connectActionListener = new ConnectActionListener(this, this.l, this.comms, mqttConnectOptions2, mqttToken, obj, iMqttActionListener, this.q);
                        mqttToken.setActionCallback(connectActionListener);
                        mqttToken.setUserContext(this);
                        MqttCallback mqttCallback = this.m;
                        if (mqttCallback instanceof MqttCallbackExtended) {
                            connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) mqttCallback);
                        }
                        this.comms.setNetworkModuleIndex(0);
                        connectActionListener.connect();
                        return mqttToken;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw ExceptionHelper.createMqttException(32100);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = this.h;
        String str = s;
        logger.fine(str, MqttServiceConstants.DISCONNECT_ACTION, "104", new Object[]{Long.valueOf(j), obj, iMqttActionListener});
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            this.h.fine(str, MqttServiceConstants.DISCONNECT_ACTION, "108");
            return mqttToken;
        } catch (MqttException e) {
            this.h.fine(s, MqttServiceConstants.DISCONNECT_ACTION, "105", null, e);
            throw e;
        }
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.comms.disconnectForcibly(j, j2, z);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length == iArr.length) {
            for (String str : strArr) {
                MqttTopic.validate(str, true);
                this.comms.removeMessageListener(str);
            }
            return q(strArr, iArr, obj, iMqttActionListener);
        }
        throw new IllegalArgumentException();
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.h.isLoggable(5)) {
            String str = "";
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    str = String.valueOf(str) + ", ";
                }
                str = String.valueOf(str) + strArr[i];
            }
            this.h.fine(s, MqttServiceConstants.UNSUBSCRIBE_ACTION, "107", new Object[]{str, obj, iMqttActionListener});
        }
        for (String str2 : strArr) {
            MqttTopic.validate(str2, true);
        }
        for (String str3 : strArr) {
            this.comms.removeMessageListener(str3);
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        this.h.fine(s, MqttServiceConstants.UNSUBSCRIBE_ACTION, "110");
        return mqttToken;
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ScheduledExecutorService scheduledExecutorService, HighResolutionTimer highResolutionTimer) throws MqttException {
        ScheduledExecutorService scheduledExecutorService2;
        SystemHighResolutionTimer systemHighResolutionTimer;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, s);
        this.h = logger;
        this.q = false;
        logger.setResourceName(str2);
        if (str2 != null) {
            int i = 0;
            int i2 = 0;
            while (i < str2.length() - 1) {
                if (Character_isHighSurrogate(str2.charAt(i))) {
                    i++;
                }
                i2++;
                i++;
            }
            if (i2 <= 65535) {
                NetworkModuleService.validateURI(str);
                this.j = str;
                this.i = str2;
                this.l = mqttClientPersistence;
                if (mqttClientPersistence == null) {
                    this.l = new MemoryPersistence();
                }
                if (highResolutionTimer == null) {
                    scheduledExecutorService2 = scheduledExecutorService;
                    systemHighResolutionTimer = new SystemHighResolutionTimer();
                } else {
                    scheduledExecutorService2 = scheduledExecutorService;
                    systemHighResolutionTimer = highResolutionTimer;
                }
                this.r = scheduledExecutorService2;
                this.h.fine(s, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
                this.l.open(str2, str);
                this.comms = new ClientComms(this, this.l, mqttPingSender, this.r, systemHighResolutionTimer);
                this.l.close();
                this.k = new Hashtable();
                return;
            }
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        throw new IllegalArgumentException("Null clientId");
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        return publish(str, bArr, i, z, null, null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        Logger logger = this.h;
        String str2 = s;
        logger.fine(str2, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        this.h.fine(str2, "publish", "112");
        return mqttDeliveryToken;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttAsyncClient
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        if ((iMqttMessageListenerArr == null || iMqttMessageListenerArr.length == iArr.length) && iArr.length == strArr.length) {
            for (int i = 0; i < strArr.length; i++) {
                MqttTopic.validate(strArr[i], true);
                if (iMqttMessageListenerArr != null && iMqttMessageListenerArr[i] != null) {
                    this.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
                } else {
                    this.comms.removeMessageListener(strArr[i]);
                }
            }
            try {
                return q(strArr, iArr, obj, iMqttActionListener);
            } catch (Exception e) {
                for (String str : strArr) {
                    this.comms.removeMessageListener(str);
                }
                throw e;
            }
        }
        throw new IllegalArgumentException();
    }
}
