package org.eclipse.paho.client.mqttv3.internal;

import com.clevertap.android.sdk.Constants;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class ClientComms {
    public static String BUILD_LEVEL = "L${build.level}";
    public static String VERSION = "${project.version}";

    /* renamed from: a  reason: collision with root package name */
    public final String f15442a;
    public final Logger b;
    public IMqttAsyncClient c;
    public int d;
    public NetworkModule[] e;
    public CommsReceiver f;
    public CommsSender g;
    public CommsCallback h;
    public ClientState i;
    public MqttConnectOptions j;
    public MqttClientPersistence k;
    public MqttPingSender l;
    public CommsTokenStore m;
    public boolean n;
    public byte o;
    public final Object p;
    public boolean q;
    public boolean r;
    public DisconnectedMessageBuffer s;
    public ExecutorService t;

    /* loaded from: classes13.dex */
    public class a implements Runnable {
        public ClientComms h;
        public MqttToken i;
        public MqttConnect j;
        public String k;

        public a(ClientComms clientComms, MqttToken mqttToken, MqttConnect mqttConnect, ExecutorService executorService) {
            this.h = null;
            this.h = clientComms;
            this.i = mqttToken;
            this.j = mqttConnect;
            this.k = "MQTT Con: " + ClientComms.this.getClient().getClientId();
        }

        public void a() {
            if (ClientComms.this.t != null) {
                ClientComms.this.t.execute(this);
            } else {
                new Thread(this).start();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.k);
            ClientComms.this.b.fine(ClientComms.this.f15442a, "connectBG:run", "220");
            MqttException e = null;
            try {
                for (MqttDeliveryToken mqttDeliveryToken : ClientComms.this.m.getOutstandingDelTokens()) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.this.m.saveToken(this.i, this.j);
                NetworkModule networkModule = ClientComms.this.e[ClientComms.this.d];
                networkModule.start();
                ClientComms.this.f = new CommsReceiver(this.h, ClientComms.this.i, ClientComms.this.m, networkModule.getInputStream());
                ClientComms.this.f.start("MQTT Rec: " + ClientComms.this.getClient().getClientId(), ClientComms.this.t);
                ClientComms.this.g = new CommsSender(this.h, ClientComms.this.i, ClientComms.this.m, networkModule.getOutputStream());
                ClientComms.this.g.start("MQTT Snd: " + ClientComms.this.getClient().getClientId(), ClientComms.this.t);
                ClientComms.this.h.start("MQTT Call: " + ClientComms.this.getClient().getClientId(), ClientComms.this.t);
                ClientComms.this.p(this.j, this.i);
            } catch (MqttException e2) {
                e = e2;
                ClientComms.this.b.fine(ClientComms.this.f15442a, "connectBG:run", "212", null, e);
            } catch (Exception e3) {
                ClientComms.this.b.fine(ClientComms.this.f15442a, "connectBG:run", "209", null, e3);
                e = ExceptionHelper.createMqttException(e3);
            }
            if (e != null) {
                ClientComms.this.shutdownConnection(this.i, e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Runnable {
        public MqttDisconnect h;
        public long i;
        public MqttToken j;
        public String k;

        public b(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken, ExecutorService executorService) {
            this.h = mqttDisconnect;
            this.i = j;
            this.j = mqttToken;
        }

        public void a() {
            this.k = "MQTT Disc: " + ClientComms.this.getClient().getClientId();
            if (ClientComms.this.t != null) {
                ClientComms.this.t.execute(this);
            } else {
                new Thread(this).start();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0065, code lost:
            if (r4.l.g.isRunning() != false) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x00ac, code lost:
            if (r4.l.g.isRunning() != false) goto L13;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                java.lang.String r1 = r4.k
                r0.setName(r1)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.logging.Logger r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.b(r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                java.lang.String r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.f(r1)
                java.lang.String r2 = "disconnectBG:run"
                java.lang.String r3 = "221"
                r0.fine(r1, r2, r3)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.ClientState r0 = org.eclipse.paho.client.mqttv3.internal.ClientComms.j(r0)
                long r1 = r4.i
                r0.quiesce(r1)
                r0 = 0
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect r2 = r4.h     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.MqttToken r3 = r4.j     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                r1.p(r2, r3)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                if (r1 == 0) goto L4c
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                boolean r1 = r1.isRunning()     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                if (r1 == 0) goto L4c
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.j     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
                r1.waitUntilSent()     // Catch: java.lang.Throwable -> L68 org.eclipse.paho.client.mqttv3.MqttException -> L93
            L4c:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.j
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)
                if (r1 == 0) goto Lae
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)
                boolean r1 = r1.isRunning()
                if (r1 != 0) goto Lb5
                goto Lae
            L68:
                r1 = move-exception
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.j
                org.eclipse.paho.client.mqttv3.internal.Token r2 = r2.internalTok
                r2.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r2)
                if (r2 == 0) goto L84
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r2)
                boolean r2 = r2.isRunning()
                if (r2 != 0) goto L8b
            L84:
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.j
                org.eclipse.paho.client.mqttv3.internal.Token r2 = r2.internalTok
                r2.notifyComplete()
            L8b:
                org.eclipse.paho.client.mqttv3.internal.ClientComms r2 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.MqttToken r3 = r4.j
                r2.shutdownConnection(r3, r0)
                throw r1
            L93:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.j
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.markComplete(r0, r0)
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)
                if (r1 == 0) goto Lae
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.internal.CommsSender r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.c(r1)
                boolean r1 = r1.isRunning()
                if (r1 != 0) goto Lb5
            Lae:
                org.eclipse.paho.client.mqttv3.MqttToken r1 = r4.j
                org.eclipse.paho.client.mqttv3.internal.Token r1 = r1.internalTok
                r1.notifyComplete()
            Lb5:
                org.eclipse.paho.client.mqttv3.internal.ClientComms r1 = org.eclipse.paho.client.mqttv3.internal.ClientComms.this
                org.eclipse.paho.client.mqttv3.MqttToken r2 = r4.j
                r1.shutdownConnection(r2, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientComms.b.run():void");
        }
    }

    /* loaded from: classes13.dex */
    public class c implements IDiscardedBufferMessageCallback {
        public c() {
        }

        @Override // org.eclipse.paho.client.mqttv3.internal.IDiscardedBufferMessageCallback
        public void messageDiscarded(MqttWireMessage mqttWireMessage) {
            if (ClientComms.this.s.isPersistBuffer()) {
                ClientComms.this.i.unPersistBufferedMessage(mqttWireMessage);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class d implements IDisconnectedBufferCallback {

        /* renamed from: a  reason: collision with root package name */
        public final String f15444a;

        public d(String str) {
            this.f15444a = str;
        }

        @Override // org.eclipse.paho.client.mqttv3.internal.IDisconnectedBufferCallback
        public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException {
            if (ClientComms.this.isConnected()) {
                while (ClientComms.this.i.getActualInFlight() >= ClientComms.this.i.getMaxInFlight() - 3) {
                    Thread.yield();
                }
                ClientComms.this.b.fine(ClientComms.this.f15442a, this.f15444a, "510", new Object[]{bufferedMessage.getMessage().getKey()});
                ClientComms.this.p(bufferedMessage.getMessage(), bufferedMessage.getToken());
                ClientComms.this.i.unPersistBufferedMessage(bufferedMessage.getMessage());
                return;
            }
            ClientComms.this.b.fine(ClientComms.this.f15442a, this.f15444a, "208");
            throw ExceptionHelper.createMqttException(32104);
        }
    }

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender, ExecutorService executorService, HighResolutionTimer highResolutionTimer) throws MqttException {
        String name = ClientComms.class.getName();
        this.f15442a = name;
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        this.b = logger;
        this.n = false;
        this.o = (byte) 3;
        this.p = new Object();
        this.q = false;
        this.r = false;
        this.o = (byte) 3;
        this.c = iMqttAsyncClient;
        this.k = mqttClientPersistence;
        this.l = mqttPingSender;
        mqttPingSender.init(this);
        this.t = executorService;
        this.m = new CommsTokenStore(getClient().getClientId());
        this.h = new CommsCallback(this);
        ClientState clientState = new ClientState(mqttClientPersistence, this.m, this.h, this, mqttPingSender, highResolutionTimer);
        this.i = clientState;
        this.h.setClientState(clientState);
        logger.setResourceName(getClient().getClientId());
    }

    public MqttToken checkForActivity() {
        return checkForActivity(null);
    }

    public void close(boolean z) throws MqttException {
        synchronized (this.p) {
            if (!isClosed()) {
                if (!isDisconnected() || z) {
                    this.b.fine(this.f15442a, Constants.KEY_HIDE_CLOSE, "224");
                    if (!isConnecting()) {
                        if (!isConnected()) {
                            if (isDisconnecting()) {
                                this.q = true;
                                return;
                            }
                        } else {
                            throw ExceptionHelper.createMqttException(32100);
                        }
                    } else {
                        throw new MqttException(32110);
                    }
                }
                this.o = (byte) 4;
                this.i.close();
                this.i = null;
                this.h = null;
                this.k = null;
                this.g = null;
                this.l = null;
                this.f = null;
                this.e = null;
                this.j = null;
                this.m = null;
            }
        }
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.p) {
            if (isDisconnected() && !this.q) {
                this.b.fine(this.f15442a, MqttServiceConstants.CONNECT_ACTION, "214");
                this.o = (byte) 1;
                this.j = mqttConnectOptions;
                MqttConnect mqttConnect = new MqttConnect(this.c.getClientId(), this.j.getMqttVersion(), this.j.isCleanSession(), this.j.getKeepAliveInterval(), this.j.getUserName(), this.j.getPassword(), this.j.getWillMessage(), this.j.getWillDestination());
                this.i.setKeepAliveSecs(this.j.getKeepAliveInterval());
                this.i.setCleanSession(this.j.isCleanSession());
                this.i.setMaxInflight(this.j.getMaxInflight());
                this.m.open();
                new a(this, mqttToken, mqttConnect, this.t).a();
            } else {
                this.b.fine(this.f15442a, MqttServiceConstants.CONNECT_ACTION, "207", new Object[]{Byte.valueOf(this.o)});
                if (!isClosed() && !this.q) {
                    if (!isConnecting()) {
                        if (isDisconnecting()) {
                            throw new MqttException(32102);
                        }
                        throw ExceptionHelper.createMqttException(32100);
                    }
                    throw new MqttException(32110);
                }
                throw new MqttException(32111);
            }
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.p) {
            if (returnCode == 0) {
                this.b.fine(this.f15442a, "connectComplete", "215");
                this.o = (byte) 0;
                return;
            }
            this.b.fine(this.f15442a, "connectComplete", "204", new Object[]{Integer.valueOf(returnCode)});
            throw mqttException;
        }
    }

    public void deleteBufferedMessage(int i) {
        this.s.deleteMessage(i);
    }

    public void deliveryComplete(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.i.deliveryComplete(mqttPublish);
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) throws MqttException {
        synchronized (this.p) {
            if (!isClosed()) {
                if (!isDisconnected()) {
                    if (!isDisconnecting()) {
                        if (Thread.currentThread() != this.h.getThread()) {
                            this.b.fine(this.f15442a, MqttServiceConstants.DISCONNECT_ACTION, "218");
                            this.o = (byte) 2;
                            new b(mqttDisconnect, j, mqttToken, this.t).a();
                        } else {
                            this.b.fine(this.f15442a, MqttServiceConstants.DISCONNECT_ACTION, "210");
                            throw ExceptionHelper.createMqttException(32107);
                        }
                    } else {
                        this.b.fine(this.f15442a, MqttServiceConstants.DISCONNECT_ACTION, "219");
                        throw ExceptionHelper.createMqttException(32102);
                    }
                } else {
                    this.b.fine(this.f15442a, MqttServiceConstants.DISCONNECT_ACTION, "211");
                    throw ExceptionHelper.createMqttException(32101);
                }
            } else {
                this.b.fine(this.f15442a, MqttServiceConstants.DISCONNECT_ACTION, "223");
                throw ExceptionHelper.createMqttException(32111);
            }
        }
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        disconnectForcibly(j, j2, true);
    }

    public int getActualInFlight() {
        return this.i.getActualInFlight();
    }

    public MqttMessage getBufferedMessage(int i) {
        return ((MqttPublish) this.s.getMessage(i).getMessage()).getMessage();
    }

    public int getBufferedMessageCount() {
        return this.s.getMessageCount();
    }

    public IMqttAsyncClient getClient() {
        return this.c;
    }

    public ClientState getClientState() {
        return this.i;
    }

    public MqttConnectOptions getConOptions() {
        return this.j;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", Integer.valueOf(this.o));
        properties.put("serverURI", getClient().getServerURI());
        properties.put("callback", this.h);
        properties.put("stoppingComms", Boolean.valueOf(this.n));
        return properties;
    }

    public long getKeepAlive() {
        return this.i.getKeepAlive();
    }

    public int getNetworkModuleIndex() {
        return this.d;
    }

    public NetworkModule[] getNetworkModules() {
        return this.e;
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.m.getOutstandingDelTokens();
    }

    public MqttTopic getTopic(String str) {
        return new MqttTopic(str, this);
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 4;
        }
        return z;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 0;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.p) {
            z = true;
            if (this.o != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 2;
        }
        return z;
    }

    public boolean isResting() {
        boolean z;
        synchronized (this.p) {
            z = this.r;
        }
        return z;
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.h.messageArrivedComplete(i, i2);
    }

    public final MqttToken n(MqttToken mqttToken, MqttException mqttException) {
        this.b.fine(this.f15442a, "handleOldTokens", "222");
        MqttToken mqttToken2 = null;
        if (mqttToken != null) {
            try {
                if (!mqttToken.isComplete() && this.m.getToken(mqttToken.internalTok.getKey()) == null) {
                    this.m.saveToken(mqttToken, mqttToken.internalTok.getKey());
                }
            } catch (Exception unused) {
            }
        }
        Enumeration elements = this.i.resolveOldTokens(mqttException).elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken3 = (MqttToken) elements.nextElement();
            if (!mqttToken3.internalTok.getKey().equals(MqttDisconnect.KEY) && !mqttToken3.internalTok.getKey().equals("Con")) {
                this.h.asyncOperationComplete(mqttToken3);
            }
            mqttToken2 = mqttToken3;
        }
        return mqttToken2;
    }

    public void notifyConnect() {
        if (this.s != null) {
            this.b.fine(this.f15442a, "notifyConnect", "509", null);
            this.s.setPublishCallback(new d("notifyConnect"));
            this.s.setMessageDiscardedCallBack(new c());
            ExecutorService executorService = this.t;
            if (executorService == null) {
                new Thread(this.s).start();
            } else {
                executorService.execute(this.s);
            }
        }
    }

    public final void o(Exception exc) {
        MqttException mqttException;
        this.b.fine(this.f15442a, "handleRunException", "804", null, exc);
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        shutdownConnection(null, mqttException);
    }

    public void p(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        this.b.fine(this.f15442a, "internalSend", "200", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.setClient(getClient());
            try {
                this.i.send(mqttWireMessage, mqttToken);
                return;
            } catch (MqttException e) {
                mqttToken.internalTok.setClient(null);
                if (mqttWireMessage instanceof MqttPublish) {
                    this.i.undo((MqttPublish) mqttWireMessage);
                }
                throw e;
            }
        }
        this.b.fine(this.f15442a, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        throw new MqttException(32201);
    }

    public boolean removeMessage(IMqttDeliveryToken iMqttDeliveryToken) throws MqttException {
        return this.i.removeMessage(iMqttDeliveryToken);
    }

    public void removeMessageListener(String str) {
        this.h.removeMessageListener(str);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (!isConnected() && ((isConnected() || !(mqttWireMessage instanceof MqttConnect)) && (!isDisconnecting() || !(mqttWireMessage instanceof MqttDisconnect)))) {
            if (this.s != null) {
                this.b.fine(this.f15442a, "sendNoWait", "508", new Object[]{mqttWireMessage.getKey()});
                if (this.s.isPersistBuffer()) {
                    this.i.persistBufferedMessage(mqttWireMessage);
                }
                this.s.putMessage(mqttWireMessage, mqttToken);
                return;
            }
            this.b.fine(this.f15442a, "sendNoWait", "208");
            throw ExceptionHelper.createMqttException(32104);
        }
        DisconnectedMessageBuffer disconnectedMessageBuffer = this.s;
        if (disconnectedMessageBuffer != null && disconnectedMessageBuffer.getMessageCount() != 0) {
            this.b.fine(this.f15442a, "sendNoWait", "507", new Object[]{mqttWireMessage.getKey()});
            if (this.s.isPersistBuffer()) {
                this.i.persistBufferedMessage(mqttWireMessage);
            }
            this.s.putMessage(mqttWireMessage, mqttToken);
            return;
        }
        p(mqttWireMessage, mqttToken);
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.h.setCallback(mqttCallback);
    }

    public void setDisconnectedMessageBuffer(DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.s = disconnectedMessageBuffer;
    }

    public void setManualAcks(boolean z) {
        this.h.setManualAcks(z);
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.h.setMessageListener(str, iMqttMessageListener);
    }

    public void setNetworkModuleIndex(int i) {
        this.d = i;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.e = (NetworkModule[]) networkModuleArr.clone();
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.h.setReconnectCallback(mqttCallbackExtended);
    }

    public void setRestingState(boolean z) {
        this.r = z;
    }

    public void shutdownConnection(MqttToken mqttToken, MqttException mqttException) {
        CommsCallback commsCallback;
        CommsCallback commsCallback2;
        MqttClientPersistence mqttClientPersistence;
        NetworkModule networkModule;
        synchronized (this.p) {
            if (!this.n && !this.q && !isClosed()) {
                this.n = true;
                this.b.fine(this.f15442a, "shutdownConnection", "216");
                boolean z = isConnected() || isDisconnecting();
                this.o = (byte) 2;
                if (mqttToken != null && !mqttToken.isComplete()) {
                    mqttToken.internalTok.setException(mqttException);
                }
                CommsCallback commsCallback3 = this.h;
                if (commsCallback3 != null) {
                    commsCallback3.stop();
                }
                CommsReceiver commsReceiver = this.f;
                if (commsReceiver != null) {
                    commsReceiver.stop();
                }
                try {
                    NetworkModule[] networkModuleArr = this.e;
                    if (networkModuleArr != null && (networkModule = networkModuleArr[this.d]) != null) {
                        networkModule.stop();
                    }
                } catch (Exception unused) {
                }
                this.m.quiesce(new MqttException(32102));
                MqttToken n = n(mqttToken, mqttException);
                try {
                    this.i.disconnected(mqttException);
                    if (this.i.getCleanSession()) {
                        this.h.removeMessageListeners();
                    }
                } catch (Exception unused2) {
                }
                CommsSender commsSender = this.g;
                if (commsSender != null) {
                    commsSender.stop();
                }
                MqttPingSender mqttPingSender = this.l;
                if (mqttPingSender != null) {
                    mqttPingSender.stop();
                }
                try {
                    if (this.s == null && (mqttClientPersistence = this.k) != null) {
                        mqttClientPersistence.close();
                    }
                } catch (Exception unused3) {
                }
                synchronized (this.p) {
                    this.b.fine(this.f15442a, "shutdownConnection", "217");
                    this.o = (byte) 3;
                    this.n = false;
                }
                if (n != null && (commsCallback2 = this.h) != null) {
                    commsCallback2.asyncOperationComplete(n);
                }
                if (z && (commsCallback = this.h) != null) {
                    commsCallback.connectionLost(mqttException);
                }
                synchronized (this.p) {
                    if (this.q) {
                        try {
                            close(true);
                        } catch (Exception unused4) {
                        }
                    }
                }
            }
        }
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) {
        try {
            return this.i.checkForActivity(iMqttActionListener);
        } catch (MqttException e) {
            o(e);
            return null;
        } catch (Exception e2) {
            o(e2);
            return null;
        }
    }

    public void deliveryComplete(int i) throws MqttPersistenceException {
        this.i.deliveryComplete(i);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.o = (byte) 2;
        ClientState clientState = this.i;
        if (clientState != null) {
            clientState.quiesce(j);
        }
        MqttToken mqttToken = new MqttToken(this.c.getClientId());
        if (z) {
            try {
                p(new MqttDisconnect(), mqttToken);
                mqttToken.waitForCompletion(j2);
            } catch (Exception unused) {
            } catch (Throwable th) {
                mqttToken.internalTok.markComplete(null, null);
                shutdownConnection(mqttToken, null);
                throw th;
            }
        }
        mqttToken.internalTok.markComplete(null, null);
        shutdownConnection(mqttToken, null);
    }
}
