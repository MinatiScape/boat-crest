package com.coveiot.android.remotecommandframework.alexa.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.BleApiUtils;
import com.coveiot.android.bleabstract.NotificationInfo;
import com.coveiot.android.remotecommandframework.AppPreferenceHandlerProvider;
import com.coveiot.android.remotecommandframework.RcfPreferenceManager;
import com.coveiot.android.remotecommandframework.alexa.model.RcfSyncComplete;
import com.coveiot.android.remotecommandframework.alexa.request.CommandManager;
import com.coveiot.android.remotecommandframework.alexa.request.model.Command;
import com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest;
import com.coveiot.android.remotecommandframework.alexa.response.model.EmptyCommandList;
import com.coveiot.android.remotecommandframework.alexa.utils.Util;
import com.coveiot.android.remotecommandframework.listener.OnSuccessFailureListener;
import com.coveiot.android.remotecommandframeworksdk.IMessageObserver;
import com.coveiot.android.remotecommandframeworksdk.IObserver;
import com.coveiot.android.remotecommandframeworksdk.IResponseObserver;
import com.coveiot.android.remotecommandframeworksdk.ISyncStatusObserver;
import com.coveiot.android.remotecommandframeworksdk.MQTTClient;
import com.coveiot.android.remotecommandframeworksdk.MQTTClientFactory;
import com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener;
import com.coveiot.android.remotecommandframeworksdk.utils.MQTTClientType;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.alexa.CoveAlexaApiManager;
import com.coveiot.coveaccess.alexa.model.EnablementData;
import com.coveiot.coveaccess.alexa.model.response.GetStatusAlexaRes;
import com.coveiot.coveaccess.device.rcf.mqtt.model.GetIotMqttInfoResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class AlexaRemoteCommandFrameworkService extends Service implements IMessageObserver, ISyncStatusObserver {
    @NotNull
    public final String h;
    public int i;
    @NotNull
    public HandlerThread j;
    @Nullable
    public Handler k;
    public IBinder l;
    @NotNull
    public final String m;
    @NotNull
    public final List<IObserver> n;
    @NotNull
    public final LinkedList<RemoteRequest> o;
    @Nullable
    public RemoteRequest p;
    public long q;

    /* loaded from: classes6.dex */
    public final class RcfServiceBinder extends Binder {
        public RcfServiceBinder() {
        }

        @NotNull
        public final AlexaRemoteCommandFrameworkService getService() {
            return AlexaRemoteCommandFrameworkService.this;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$onDestroy$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes6.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AlexaRemoteCommandFrameworkService.this.unsubscribe();
                MQTTClient mqttClient = AlexaRemoteCommandFrameworkService.this.getMqttClient();
                if (mqttClient != null) {
                    AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService = AlexaRemoteCommandFrameworkService.this;
                    mqttClient.removeObserver(alexaRemoteCommandFrameworkService);
                    alexaRemoteCommandFrameworkService.n.remove(mqttClient);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public AlexaRemoteCommandFrameworkService() {
        String simpleName = AlexaRemoteCommandFrameworkService.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "AlexaRemoteCommandFramew…ce::class.java.simpleName");
        this.h = simpleName;
        this.i = 900000;
        this.j = new HandlerThread("RCF-MQTTConnectionCheck-Thread");
        this.m = "101";
        this.n = new ArrayList();
        this.o = new LinkedList<>();
        this.l = new RcfServiceBinder();
    }

    public static final void h(AlexaRemoteCommandFrameworkService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o();
        this$0.c();
        this$0.g();
    }

    public final void b(RemoteRequest remoteRequest) {
        synchronized (this.o) {
            if (this.o.size() > 0) {
                remoteRequest.getPriority();
                int i = 0;
                int i2 = -1;
                for (Object obj : this.o) {
                    int i3 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    try {
                        if (((RemoteRequest) obj).getPriority() > remoteRequest.getPriority()) {
                            i2 = i;
                        } else {
                            this.o.size();
                        }
                    } catch (Exception unused) {
                    }
                    i = i3;
                }
                if (i2 != -1) {
                    this.o.add(i2, remoteRequest);
                } else {
                    this.o.addLast(remoteRequest);
                }
            } else {
                remoteRequest.getPriority();
                remoteRequest.setPriority(remoteRequest.getPriority());
                this.o.addFirst(remoteRequest);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void c() {
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AlexaRemoteCommandFrameworkService$checkAndConnectToRemoteCommandFramework$1(this, null), 2, null);
    }

    public final void connect(@NotNull OnSuccessFailureListener successListener) {
        Intrinsics.checkNotNullParameter(successListener, "successListener");
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AlexaRemoteCommandFrameworkService$connect$1(this, successListener, null), 2, null);
    }

    public final void d() {
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AlexaRemoteCommandFrameworkService$closeMqttConnection$1(this, null), 2, null);
    }

    public final void e() {
        Notification build;
        NotificationInfo data = BleApiUtils.getData();
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(data.getAppLauncherActivity()), 67108864);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            NotificationChannel notificationChannel = new NotificationChannel(this.m, data.getAppDesc(), 2);
            notificationChannel.enableLights(false);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            build = new Notification.Builder(this, this.m).setContentTitle(data.getAppDesc()).setColor(ContextCompat.getColor(this, data.getAppColor())).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this, RCF_NOTIFI…\n                .build()");
        } else if (i >= 21) {
            build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setColor(ContextCompat.getColor(this, data.getAppColor())).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        } else {
            build = new Notification.Builder(this).setContentTitle(data.getAppDesc()).setSmallIcon(data.getAppIcon()).setContentIntent(activity).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(this)\n          …                 .build()");
        }
        startForeground(Integer.parseInt(this.m), build);
    }

    public final RemoteRequest f() {
        synchronized (this.o) {
            LinkedList<RemoteRequest> linkedList = this.o;
            if (linkedList == null || !(!linkedList.isEmpty())) {
                return null;
            }
            return this.o.removeFirst();
        }
    }

    public final void g() {
        Handler handler = this.k;
        if (handler != null) {
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.remotecommandframework.alexa.service.a
                @Override // java.lang.Runnable
                public final void run() {
                    AlexaRemoteCommandFrameworkService.h(AlexaRemoteCommandFrameworkService.this);
                }
            }, this.i);
        }
    }

    @Nullable
    public final MQTTClient getMqttClient() {
        MQTTClientFactory mQTTClientFactory = MQTTClientFactory.INSTANCE;
        MQTTClientType mQTTClientType = MQTTClientType.ECLIPSE_PAHO_CLIENT;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        return mQTTClientFactory.getMQTTClient(mQTTClientType, applicationContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0028 A[Catch: all -> 0x004b, TryCatch #0 {, blocks: (B:4:0x0003, B:10:0x0018, B:12:0x001c, B:18:0x0028, B:19:0x002e, B:21:0x0034, B:7:0x000e), top: B:29:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean i(com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest r9) {
        /*
            r8 = this;
            java.util.LinkedList<com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest> r0 = r8.o
            monitor-enter(r0)
            long r1 = r8.q     // Catch: java.lang.Throwable -> L4b
            java.lang.Long r3 = r9.getTs()     // Catch: java.lang.Throwable -> L4b
            r4 = 0
            r5 = 1
            if (r3 != 0) goto Le
            goto L18
        Le:
            long r6 = r3.longValue()     // Catch: java.lang.Throwable -> L4b
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 != 0) goto L18
        L16:
            r4 = r5
            goto L49
        L18:
            java.util.LinkedList<com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest> r1 = r8.o     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L25
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L4b
            if (r1 == 0) goto L23
            goto L25
        L23:
            r1 = r4
            goto L26
        L25:
            r1 = r5
        L26:
            if (r1 != 0) goto L49
            java.util.LinkedList<com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest> r1 = r8.o     // Catch: java.lang.Throwable -> L4b
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L4b
        L2e:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L4b
            if (r2 == 0) goto L49
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L4b
            com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest r2 = (com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest) r2     // Catch: java.lang.Throwable -> L4b
            java.lang.Long r2 = r2.getTs()     // Catch: java.lang.Throwable -> L4b
            java.lang.Long r3 = r9.getTs()     // Catch: java.lang.Throwable -> L4b
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)     // Catch: java.lang.Throwable -> L4b
            if (r2 == 0) goto L2e
            goto L16
        L49:
            monitor-exit(r0)
            return r4
        L4b:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService.i(com.coveiot.android.remotecommandframework.alexa.request.model.RemoteRequest):boolean");
    }

    public final boolean isConnected() {
        try {
            if (getMqttClient() == null) {
                return false;
            }
            MQTTClient mqttClient = getMqttClient();
            Intrinsics.checkNotNull(mqttClient);
            return mqttClient.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void j() {
        if (this.p == null) {
            RemoteRequest f = f();
            this.p = f;
            if (f != null) {
                Intrinsics.checkNotNull(f);
                if (f.getItems() != null) {
                    RemoteRequest remoteRequest = this.p;
                    Intrinsics.checkNotNull(remoteRequest);
                    List<Command> items = remoteRequest.getItems();
                    Intrinsics.checkNotNull(items);
                    if (!items.isEmpty()) {
                        RemoteRequest remoteRequest2 = this.p;
                        Intrinsics.checkNotNull(remoteRequest2);
                        List<Command> items2 = remoteRequest2.getItems();
                        if (items2 != null) {
                            CommandManager.Companion.getInstance(this).startCommandProcessing(items2, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$processNextRequest$1$1
                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onFailure(@Nullable String str) {
                                    RemoteRequest remoteRequest3;
                                    RemoteRequest remoteRequest4;
                                    RemoteRequest remoteRequest5;
                                    remoteRequest3 = AlexaRemoteCommandFrameworkService.this.p;
                                    if (remoteRequest3 != null) {
                                        remoteRequest4 = AlexaRemoteCommandFrameworkService.this.p;
                                        Intrinsics.checkNotNull(remoteRequest4);
                                        remoteRequest4.setTs(Long.valueOf(System.currentTimeMillis()));
                                        Util.Companion companion = Util.Companion;
                                        remoteRequest5 = AlexaRemoteCommandFrameworkService.this.p;
                                        Intrinsics.checkNotNull(remoteRequest5);
                                        String jsonFromObject = companion.getJsonFromObject(remoteRequest5);
                                        if (jsonFromObject != null) {
                                            AlexaRemoteCommandFrameworkService.this.k(jsonFromObject);
                                        }
                                        AlexaRemoteCommandFrameworkService.this.p = null;
                                        AlexaRemoteCommandFrameworkService.this.m();
                                    }
                                }

                                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                                public void onSuccess() {
                                    RemoteRequest remoteRequest3;
                                    RemoteRequest remoteRequest4;
                                    remoteRequest3 = AlexaRemoteCommandFrameworkService.this.p;
                                    Intrinsics.checkNotNull(remoteRequest3);
                                    remoteRequest3.setTs(Long.valueOf(System.currentTimeMillis()));
                                    Util.Companion companion = Util.Companion;
                                    remoteRequest4 = AlexaRemoteCommandFrameworkService.this.p;
                                    Intrinsics.checkNotNull(remoteRequest4);
                                    String jsonFromObject = companion.getJsonFromObject(remoteRequest4);
                                    if (jsonFromObject != null) {
                                        AlexaRemoteCommandFrameworkService.this.k(jsonFromObject);
                                    }
                                    AlexaRemoteCommandFrameworkService.this.p = null;
                                    AlexaRemoteCommandFrameworkService.this.j();
                                }
                            });
                            return;
                        }
                        return;
                    }
                }
                String jsonFromObject = Util.Companion.getJsonFromObject(new EmptyCommandList());
                Intrinsics.checkNotNull(jsonFromObject);
                k(jsonFromObject);
                this.p = null;
                return;
            }
            LogHelper.d(this.h, "waiting for message to process!!");
        }
    }

    public final void k(String str) {
        String responseContentType;
        List<IObserver> list = this.n;
        if (list != null) {
            for (IObserver iObserver : list) {
                if (iObserver instanceof IResponseObserver) {
                    RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
                    if (companion.getInstance(this).getTopicPub() != null && (responseContentType = companion.getInstance(this).getResponseContentType()) != null) {
                        String topicPub = companion.getInstance(this).getTopicPub();
                        Intrinsics.checkNotNull(topicPub);
                        ((IResponseObserver) iObserver).onSendResponse(str, topicPub, companion.getInstance(this).getResponseQOS(), responseContentType);
                    }
                }
            }
        }
    }

    public final void l(String str) {
        String responseContentType;
        List<IObserver> list = this.n;
        if (list != null) {
            for (IObserver iObserver : list) {
                if (iObserver instanceof IResponseObserver) {
                    RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
                    if (companion.getInstance(this).getTopicPub() != null && (responseContentType = companion.getInstance(this).getResponseContentType()) != null) {
                        String topicPub = companion.getInstance(this).getTopicPub();
                        Intrinsics.checkNotNull(topicPub);
                        ((IResponseObserver) iObserver).onSendResponseAck(str, topicPub, companion.getInstance(this).getResponseQOS(), responseContentType);
                    }
                }
            }
        }
    }

    public final void m() {
        synchronized (this.o) {
            this.o.clear();
            LogHelper.e(this.h, "Request Queue Cleared!!");
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void n(GetIotMqttInfoResponse getIotMqttInfoResponse) {
        RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
        String str = getIotMqttInfoResponse.clientId;
        Intrinsics.checkNotNullExpressionValue(str, "p0.clientId");
        companion.getInstance(this).saveClientId(str);
        companion.getInstance(this).saveHost("ssl://" + getIotMqttInfoResponse.host + ':' + getIotMqttInfoResponse.port);
        Integer num = getIotMqttInfoResponse.s2tCmd.req.qos;
        Intrinsics.checkNotNullExpressionValue(num, "p0.s2tCmd.req.qos");
        companion.getInstance(this).saveRequestQOS(num.intValue());
        String str2 = getIotMqttInfoResponse.s2tCmd.req.contentType;
        Intrinsics.checkNotNullExpressionValue(str2, "p0.s2tCmd.req.contentType");
        companion.getInstance(this).saveRequestContentType(str2);
        String str3 = getIotMqttInfoResponse.s2tCmd.req.topic;
        Intrinsics.checkNotNullExpressionValue(str3, "p0.s2tCmd.req.topic");
        companion.getInstance(this).saveTopicSub(str3);
        Integer num2 = getIotMqttInfoResponse.s2tCmd.res.qos;
        Intrinsics.checkNotNullExpressionValue(num2, "p0.s2tCmd.res.qos");
        companion.getInstance(this).saveResponseQOS(num2.intValue());
        String str4 = getIotMqttInfoResponse.s2tCmd.res.contentType;
        Intrinsics.checkNotNullExpressionValue(str4, "p0.s2tCmd.res.contentType");
        companion.getInstance(this).saveResponseContentType(str4);
        String str5 = getIotMqttInfoResponse.s2tCmd.res.topic;
        Intrinsics.checkNotNullExpressionValue(str5, "p0.s2tCmd.res.topic");
        companion.getInstance(this).saveTopicPub(str5);
        Integer num3 = getIotMqttInfoResponse.keepAlive;
        Intrinsics.checkNotNullExpressionValue(num3, "p0.keepAlive");
        companion.getInstance(this).saveKeepAlive(num3.intValue());
    }

    public final void o() {
        Handler handler = this.k;
        if (handler != null) {
            Intrinsics.checkNotNull(handler);
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        IBinder iBinder = this.l;
        if (iBinder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("serviceBinder");
            return null;
        }
        return iBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
        this.j.start();
        this.k = new Handler(this.j.getLooper());
        MQTTClient mqttClient = getMqttClient();
        if (mqttClient != null) {
            mqttClient.addObserver(this);
        }
        List<IObserver> list = this.n;
        MQTTClient mqttClient2 = getMqttClient();
        Intrinsics.checkNotNull(mqttClient2);
        list.add(mqttClient2);
        CommandManager.Companion.getInstance(this).setProviderPreference(new AppPreferenceHandlerProvider());
        try {
            CoveEventBusManager.getInstance().getEventBus().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            LogHelper.d(this.h, "Service onDestroy");
            o();
            HandlerThread handlerThread = this.j;
            boolean z = false;
            if (handlerThread != null && handlerThread.isAlive()) {
                z = true;
            }
            if (z) {
                this.j.quitSafely();
            }
            e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(null), 2, null);
            stopForeground(true);
            stopSelf();
            CoveEventBusManager.getInstance().getEventBus().unregister(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0092, code lost:
        r0 = r8.getItems();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r0 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a1, code lost:
        if (r0.hasNext() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a3, code lost:
        r1 = r0.next();
        r1.setStatus(com.coveiot.android.remotecommandframework.alexa.utils.ResponseType.ACK.getStatus());
        r1.setData(null);
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005d A[Catch: Exception -> 0x00d9, TryCatch #0 {Exception -> 0x00d9, blocks: (B:12:0x003d, B:14:0x0045, B:16:0x004b, B:18:0x0051, B:24:0x005d, B:26:0x0063, B:28:0x0088, B:33:0x0092, B:34:0x009d, B:36:0x00a3, B:37:0x00b6, B:38:0x00c9, B:39:0x00d1), top: B:47:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    @Override // com.coveiot.android.remotecommandframeworksdk.IMessageObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMessageUpdate(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService.onMessageUpdate(java.lang.String, java.lang.String):void");
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.IMessageObserver
    public void onMessageUpdate(@NotNull String topic, @NotNull byte[] byteArray) {
        Intrinsics.checkNotNullParameter(topic, "topic");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            Intent intent2 = new Intent(this, AlexaRemoteCommandFrameworkService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(intent2);
            } else {
                startService(intent2);
            }
            return 3;
        }
        e();
        c();
        g();
        return 3;
    }

    @Subscribe
    public final void onSyncCompleteReceived(@NotNull RcfSyncComplete rcfSyncComplete) {
        Intrinsics.checkNotNullParameter(rcfSyncComplete, "rcfSyncComplete");
        c();
    }

    @Override // com.coveiot.android.remotecommandframeworksdk.ISyncStatusObserver
    public void onSyncCompleted() {
        CommandManager.Companion.getInstance(this).onSyncCompleted();
    }

    public final void p(final OnSuccessFailureListener onSuccessFailureListener) {
        CoveAlexaApiManager.getStatusAlexaAccountLinking(new CoveApiListener<GetStatusAlexaRes, CoveApiErrorModel>() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$updateAccountLinkStatus$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                onSuccessFailureListener.onFailure(coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetStatusAlexaRes getStatusAlexaRes) {
                if (getStatusAlexaRes != null) {
                    AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService = AlexaRemoteCommandFrameworkService.this;
                    SessionManager.getInstance(alexaRemoteCommandFrameworkService).setAlexaAccountLinkingStatus(false);
                    EnablementData data = getStatusAlexaRes.getData();
                    if (data != null) {
                        Intrinsics.checkNotNullExpressionValue(data, "data");
                        String status = data.getStatus();
                        if (status != null) {
                            Intrinsics.checkNotNullExpressionValue(status, "status");
                            if (m.equals(status, "LINKED", true) || m.equals(status, "ENABLED", true) || m.equals(status, "ENABLING", true)) {
                                SessionManager.getInstance(alexaRemoteCommandFrameworkService).setAlexaAccountLinkingStatus(true);
                            }
                        }
                    }
                }
                if (!SessionManager.getInstance(AlexaRemoteCommandFrameworkService.this).getAlexaAccountLinkingStatus()) {
                    AlexaRemoteCommandFrameworkService.this.unsubscribe();
                    onSuccessFailureListener.onFailure(null);
                    return;
                }
                onSuccessFailureListener.onSuccess();
            }
        });
    }

    public final void register(@NotNull IObserver observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.n.add(observer);
    }

    public final void resetRetainFlag() {
        String responseContentType;
        List<IObserver> list = this.n;
        if (list != null) {
            for (IObserver iObserver : list) {
                if (iObserver instanceof IResponseObserver) {
                    RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
                    if (companion.getInstance(this).getTopicPub() != null && (responseContentType = companion.getInstance(this).getResponseContentType()) != null) {
                        String jSONObject = new JSONObject().toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "JSONObject().toString()");
                        String topicPub = companion.getInstance(this).getTopicPub();
                        Intrinsics.checkNotNull(topicPub);
                        ((IResponseObserver) iObserver).onResetRetainFlag(jSONObject, topicPub, companion.getInstance(this).getResponseQOS(), responseContentType);
                    }
                }
            }
        }
    }

    public final void stopService() {
        o();
        stopForeground(true);
        stopSelf();
    }

    public final void subscribe() {
        e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AlexaRemoteCommandFrameworkService$subscribe$1(this, null), 2, null);
    }

    public final void syncCompleted() {
        MQTTClient mqttClient;
        if (!isConnected() || (mqttClient = getMqttClient()) == null) {
            return;
        }
        mqttClient.syncCompleted();
    }

    public final void unregister(@NotNull IObserver observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.n.remove(observer);
    }

    public final void unsubscribe() {
        MQTTClient mqttClient;
        if (isConnected()) {
            RcfPreferenceManager.Companion companion = RcfPreferenceManager.Companion;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            String topicSub = companion.getInstance(applicationContext).getTopicSub();
            if (topicSub == null || (mqttClient = getMqttClient()) == null) {
                return;
            }
            mqttClient.unsubscribe(topicSub, new SuccessListener() { // from class: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$unsubscribe$1$1

                @DebugMetadata(c = "com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$unsubscribe$1$1$onSuccess$1", f = "AlexaRemoteCommandFrameworkService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes6.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ AlexaRemoteCommandFrameworkService this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(AlexaRemoteCommandFrameworkService alexaRemoteCommandFrameworkService, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = alexaRemoteCommandFrameworkService;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new a(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
                        if (r3 != false) goto L38;
                     */
                    /* JADX WARN: Removed duplicated region for block: B:25:0x0042  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @org.jetbrains.annotations.Nullable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r3) {
                        /*
                            r2 = this;
                            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                            int r0 = r2.label
                            if (r0 != 0) goto L6b
                            kotlin.ResultKt.throwOnFailure(r3)
                            com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService r3 = r2.this$0
                            com.coveiot.android.remotecommandframeworksdk.MQTTClient r3 = r3.getMqttClient()
                            r0 = 0
                            if (r3 == 0) goto L18
                            kotlin.Pair r3 = r3.getAllSubscription()
                            goto L19
                        L18:
                            r3 = r0
                        L19:
                            if (r3 == 0) goto L63
                            com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService r3 = r2.this$0
                            com.coveiot.android.remotecommandframeworksdk.MQTTClient r3 = r3.getMqttClient()
                            if (r3 == 0) goto L30
                            kotlin.Pair r3 = r3.getAllSubscription()
                            if (r3 == 0) goto L30
                            java.lang.Object r3 = r3.getFirst()
                            r0 = r3
                            java.lang.String[] r0 = (java.lang.String[]) r0
                        L30:
                            r3 = 0
                            r1 = 1
                            if (r0 == 0) goto L3f
                            int r0 = r0.length
                            if (r0 != 0) goto L39
                            r0 = r1
                            goto L3a
                        L39:
                            r0 = r3
                        L3a:
                            if (r0 == 0) goto L3d
                            goto L3f
                        L3d:
                            r0 = r3
                            goto L40
                        L3f:
                            r0 = r1
                        L40:
                            if (r0 != 0) goto L63
                            com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService r0 = r2.this$0
                            com.coveiot.android.remotecommandframeworksdk.MQTTClient r0 = r0.getMqttClient()
                            if (r0 == 0) goto L61
                            kotlin.Pair r0 = r0.getAllSubscription()
                            if (r0 == 0) goto L61
                            java.lang.Object r0 = r0.getSecond()
                            int[] r0 = (int[]) r0
                            if (r0 == 0) goto L61
                            int r0 = r0.length
                            if (r0 != 0) goto L5d
                            r0 = r1
                            goto L5e
                        L5d:
                            r0 = r3
                        L5e:
                            if (r0 != r1) goto L61
                            r3 = r1
                        L61:
                            if (r3 == 0) goto L68
                        L63:
                            com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService r3 = r2.this$0
                            com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService.access$closeMqttConnection(r3)
                        L68:
                            kotlin.Unit r3 = kotlin.Unit.INSTANCE
                            return r3
                        L6b:
                            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r3.<init>(r0)
                            throw r3
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.remotecommandframework.alexa.service.AlexaRemoteCommandFrameworkService$unsubscribe$1$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onFailure(@Nullable String str) {
                }

                @Override // com.coveiot.android.remotecommandframeworksdk.listener.SuccessListener
                public void onSuccess() {
                    e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new a(AlexaRemoteCommandFrameworkService.this, null), 2, null);
                }
            });
        }
    }
}
