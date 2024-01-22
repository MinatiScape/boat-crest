package io.shipbook.shipbooksdk.Appenders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import com.realsil.sdk.dfu.DfuException;
import io.shipbook.shipbooksdk.BroadcastNames;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.Models.BaseEvent;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.Models.BaseObj;
import io.shipbook.shipbooksdk.Models.Exception;
import io.shipbook.shipbooksdk.Models.Login;
import io.shipbook.shipbooksdk.Models.Message;
import io.shipbook.shipbooksdk.Models.SessionLogData;
import io.shipbook.shipbooksdk.Models.Severity;
import io.shipbook.shipbooksdk.Models.User;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.c;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.e;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010)\u001a\u00020\u0005\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\u0006¢\u0006\u0004\b*\u0010+J\b\u0010\u0003\u001a\u00020\u0002H\u0004J$\u0010\b\u001a\u00020\u00022\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\u0006H\u0016J\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\fJ\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0011H\u0016R\u0019\u0010\u0019\u001a\u00020\u00148\u0006@\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\r\u001a\u00020\f8\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010 \u001a\u00020\f8\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\"\u0010(\u001a\u00020!8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006,"}, d2 = {"Lio/shipbook/shipbooksdk/Appenders/SBCloudAppender;", "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "", "finalize", "", "", "Lio/shipbook/shipbooksdk/Appenders/Config;", Constants.KEY_CONFIG, "update", "Lio/shipbook/shipbooksdk/Models/BaseObj;", "obj", "saveToFile", "Ljava/io/File;", "file", "", "Lio/shipbook/shipbooksdk/Models/SessionLogData;", "loadFromFile", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "log", "push", "Landroid/content/BroadcastReceiver;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Landroid/content/BroadcastReceiver;", "getBroadcastReceiver", "()Landroid/content/BroadcastReceiver;", "broadcastReceiver", "l", "Ljava/io/File;", "getFile", "()Ljava/io/File;", "m", "getTempFile", "tempFile", "", "o", "Z", "getHasLog", "()Z", "setHasLog", "(Z)V", "hasLog", AppMeasurementSdk.ConditionalUserProperty.NAME, "<init>", "(Ljava/lang/String;Ljava/util/Map;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class SBCloudAppender extends BaseAppender {
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public volatile double g;
    public volatile int h;
    public volatile Severity i;
    public volatile int j;
    @NotNull
    public final BroadcastReceiver k;
    @NotNull
    public final File l;
    @NotNull
    public final File m;
    public Queue<BaseLog> n;
    public boolean o;
    public volatile Timer p;
    public volatile boolean q;

    @DebugMetadata(c = "io.shipbook.shipbooksdk.Appenders.SBCloudAppender$push$1", f = "SBCloudAppender.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes12.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ BaseLog $log;
        public int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(BaseLog baseLog, Continuation continuation) {
            super(2, continuation);
            this.$log = baseLog;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            a aVar = new a(this.$log, completion);
            aVar.p$ = (CoroutineScope) obj;
            return aVar;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                if (!(obj instanceof Result.Failure)) {
                    BaseLog baseLog = this.$log;
                    if (baseLog instanceof Message) {
                        SBCloudAppender.this.e((Message) baseLog);
                    } else if (baseLog instanceof BaseEvent) {
                        SBCloudAppender.this.c((BaseEvent) baseLog);
                    } else if (baseLog instanceof Exception) {
                        SBCloudAppender.this.d((Exception) baseLog);
                    }
                    return Unit.INSTANCE;
                }
                throw ((Result.Failure) obj).exception;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "io.shipbook.shipbooksdk.Appenders.SBCloudAppender", f = "SBCloudAppender.kt", i = {0, 0, 0}, l = {DfuException.ERROR_READ_REMOTE_MAC_ADDR}, m = MqttServiceConstants.SEND_ACTION, n = {"this", "sessionsData", "currentTime"}, s = {"L$0", "L$1", "L$2"})
    /* loaded from: classes12.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public Object L$2;
        public int label;
        public /* synthetic */ Object result;

        public b(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SBCloudAppender.this.g(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SBCloudAppender(@NotNull String name, @Nullable Map<String, String> map) {
        super(name, map);
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.c = SBCloudAppender.class.getSimpleName();
        this.d = ": ";
        this.e = MapplsLMSDbAdapter.KEY_TOKEN;
        this.f = "\n";
        this.g = 3.0d;
        this.h = 1048576;
        this.i = Severity.Verbose;
        this.j = 40;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: io.shipbook.shipbooksdk.Appenders.SBCloudAppender$broadcastReceiver$1

            @DebugMetadata(c = "io.shipbook.shipbooksdk.Appenders.SBCloudAppender$broadcastReceiver$1$onReceive$1", f = "SBCloudAppender.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes12.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                private CoroutineScope p$;

                public a(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    a aVar = new a(completion);
                    aVar.p$ = (CoroutineScope) obj;
                    return aVar;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        if (!(obj instanceof Result.Failure)) {
                            Login login = SessionManager.INSTANCE.getLogin();
                            User user = login != null ? login.getUser() : null;
                            if (user != null) {
                                SBCloudAppender.this.saveToFile(user);
                                SBCloudAppender.this.b();
                            }
                            return Unit.INSTANCE;
                        }
                        throw ((Result.Failure) obj).exception;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "io.shipbook.shipbooksdk.Appenders.SBCloudAppender$broadcastReceiver$1$onReceive$2", f = "SBCloudAppender.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes12.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                private CoroutineScope p$;

                public b(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    b bVar = new b(completion);
                    bVar.p$ = (CoroutineScope) obj;
                    return bVar;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        }
                    } else if (obj instanceof Result.Failure) {
                        throw ((Result.Failure) obj).exception;
                    } else {
                        SBCloudAppender sBCloudAppender = SBCloudAppender.this;
                        this.label = 1;
                        if (sBCloudAppender.g(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(@NotNull Context contxt, @NotNull Intent intent) {
                String TAG;
                String TAG2;
                Intrinsics.checkParameterIsNotNull(contxt, "contxt");
                Intrinsics.checkParameterIsNotNull(intent, "intent");
                String action = intent.getAction();
                BroadcastNames broadcastNames = BroadcastNames.INSTANCE;
                if (Intrinsics.areEqual(action, broadcastNames.getUSER_CHANGE())) {
                    InnerLog innerLog = InnerLog.INSTANCE;
                    TAG2 = SBCloudAppender.this.c;
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    InnerLog.d$default(innerLog, TAG2, "received user change", null, 4, null);
                    e.e(GlobalScope.INSTANCE, SessionManager.INSTANCE.getThreadContext(), null, new a(null), 2, null);
                } else if (Intrinsics.areEqual(action, broadcastNames.getCONNECTED())) {
                    InnerLog innerLog2 = InnerLog.INSTANCE;
                    TAG = SBCloudAppender.this.c;
                    Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                    InnerLog.d$default(innerLog2, TAG, "received connected", null, 4, null);
                    e.e(GlobalScope.INSTANCE, SessionManager.INSTANCE.getThreadContext(), null, new b(null), 2, null);
                }
            }
        };
        this.k = broadcastReceiver;
        this.n = new LinkedBlockingQueue();
        Context appContext = SessionManager.INSTANCE.getAppContext();
        this.l = new File(appContext != null ? appContext.getFilesDir() : null, "CloudQueue.log");
        this.m = new File(appContext != null ? appContext.getFilesDir() : null, "TempCloudQueue.log");
        IntentFilter intentFilter = new IntentFilter();
        BroadcastNames broadcastNames = BroadcastNames.INSTANCE;
        intentFilter.addAction(broadcastNames.getUSER_CHANGE());
        intentFilter.addAction(broadcastNames.getCONNECTED());
        if (appContext == null) {
            Intrinsics.throwNpe();
        }
        LocalBroadcastManager.getInstance(appContext).registerReceiver(broadcastReceiver, intentFilter);
    }

    public final void a() {
        if (this.l.isFile()) {
            c.appendText$default(this.m, c.readText$default(this.l, null, 1, null), null, 2, null);
        }
        this.m.renameTo(this.l);
    }

    public final void b() {
        if (this.p != null) {
            return;
        }
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = this.c;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.d$default(innerLog, TAG, "the current time " + this.g, null, 4, null);
        this.p = new Timer(true);
        Timer timer = this.p;
        if (timer != null) {
            timer.schedule(new TimerTask() { // from class: io.shipbook.shipbooksdk.Appenders.SBCloudAppender$createTimer$$inlined$timerTask$1

                @DebugMetadata(c = "io.shipbook.shipbooksdk.Appenders.SBCloudAppender$createTimer$1$1", f = "SBCloudAppender.kt", i = {}, l = {198}, m = "invokeSuspend", n = {}, s = {})
                /* loaded from: classes12.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    private CoroutineScope p$;
                    public final /* synthetic */ SBCloudAppender$createTimer$$inlined$timerTask$1 this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(Continuation continuation, SBCloudAppender$createTimer$$inlined$timerTask$1 sBCloudAppender$createTimer$$inlined$timerTask$1) {
                        super(2, continuation);
                        this.this$0 = sBCloudAppender$createTimer$$inlined$timerTask$1;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
                        Intrinsics.checkParameterIsNotNull(completion, "completion");
                        a aVar = new a(completion, this.this$0);
                        aVar.p$ = (CoroutineScope) obj;
                        return aVar;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i != 0) {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            if (obj instanceof Result.Failure) {
                                throw ((Result.Failure) obj).exception;
                            }
                        } else if (obj instanceof Result.Failure) {
                            throw ((Result.Failure) obj).exception;
                        } else {
                            SBCloudAppender.this.p = null;
                            SBCloudAppender sBCloudAppender = SBCloudAppender.this;
                            this.label = 1;
                            if (sBCloudAppender.g(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    e.e(GlobalScope.INSTANCE, SessionManager.INSTANCE.getThreadContext(), null, new a(null, this), 2, null);
                }
            }, (long) (this.g * 1000));
        }
    }

    public final void c(BaseEvent baseEvent) {
        this.n.add(baseEvent);
        if (this.n.size() > this.j) {
            this.n.remove();
        }
    }

    public final void d(Exception exception) {
        f();
        saveToFile(exception);
    }

    public final void e(Message message) {
        if (this.i.ordinal() < message.getSeverity().ordinal()) {
            this.n.add(message);
            if (this.n.size() > this.j) {
                this.n.remove();
                return;
            }
            return;
        }
        f();
        saveToFile(message);
        b();
    }

    public final void f() {
        for (BaseLog it : this.n) {
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            saveToFile(it);
        }
        this.n = new LinkedBlockingQueue();
    }

    public final void finalize() {
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = this.c;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.d$default(innerLog, TAG, "unregister broadcast receiver", null, 4, null);
        SessionManager sessionManager = SessionManager.INSTANCE;
        if (sessionManager.getAppContext() == null) {
            return;
        }
        Context appContext = sessionManager.getAppContext();
        if (appContext == null) {
            Intrinsics.throwNpe();
        }
        LocalBroadcastManager.getInstance(appContext).unregisterReceiver(this.k);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013c A[Catch: Exception -> 0x0041, TryCatch #3 {Exception -> 0x0041, blocks: (B:12:0x0036, B:59:0x0134, B:61:0x013c, B:66:0x017e, B:62:0x0142, B:64:0x0149, B:65:0x015c, B:15:0x003c, B:16:0x0040), top: B:86:0x0036 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0142 A[Catch: Exception -> 0x0041, TryCatch #3 {Exception -> 0x0041, blocks: (B:12:0x0036, B:59:0x0134, B:61:0x013c, B:66:0x017e, B:62:0x0142, B:64:0x0149, B:65:0x015c, B:15:0x003c, B:16:0x0040), top: B:86:0x0036 }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object g(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.shipbook.shipbooksdk.Appenders.SBCloudAppender.g(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final BroadcastReceiver getBroadcastReceiver() {
        return this.k;
    }

    @NotNull
    public final File getFile() {
        return this.l;
    }

    public final boolean getHasLog() {
        return this.o;
    }

    @NotNull
    public final File getTempFile() {
        return this.m;
    }

    @NotNull
    public final List<SessionLogData> loadFromFile(@NotNull File file) {
        List<BaseLog> logs;
        SessionLogData sessionLogData;
        Intrinsics.checkParameterIsNotNull(file, "file");
        ArrayList arrayList = new ArrayList();
        String name = Login.class.getName();
        String name2 = BaseLog.class.getName();
        String name3 = User.class.getName();
        int i = 1;
        SessionLogData sessionLogData2 = null;
        try {
            for (String str : c.readLines$default(file, null, 1, null)) {
                String[] strArr = new String[i];
                strArr[0] = this.d;
                List split$default = StringsKt__StringsKt.split$default((CharSequence) str, strArr, false, 2, 2, (Object) null);
                String str2 = (String) split$default.get(0);
                String str3 = (String) split$default.get(1);
                if (Intrinsics.areEqual(str2, name)) {
                    Login create = Login.Companion.create(new JSONObject(str3));
                    if (sessionLogData2 != null) {
                        arrayList.add(sessionLogData2);
                    }
                    sessionLogData = new SessionLogData(null, create, null, null, 13, null);
                } else if (Intrinsics.areEqual(str2, this.e)) {
                    if (sessionLogData2 != null) {
                        arrayList.add(sessionLogData2);
                    }
                    sessionLogData = new SessionLogData(str3, null, null, null, 14, null);
                } else {
                    if (Intrinsics.areEqual(str2, name2)) {
                        BaseLog create2 = BaseLog.Companion.create(new JSONObject(str3));
                        if (sessionLogData2 != null && (logs = sessionLogData2.getLogs()) != null) {
                            logs.add(create2);
                        }
                    } else if (Intrinsics.areEqual(str2, name3)) {
                        User create3 = User.Companion.create(new JSONObject(str3));
                        if (sessionLogData2 != null) {
                            sessionLogData2.setUser(create3);
                        }
                    } else {
                        InnerLog innerLog = InnerLog.INSTANCE;
                        String TAG = this.c;
                        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                        InnerLog.e$default(innerLog, TAG, "no classname exists", null, 4, null);
                    }
                    i = 1;
                }
                sessionLogData2 = sessionLogData;
                i = 1;
            }
            if (sessionLogData2 != null) {
                arrayList.add(sessionLogData2);
            }
        } catch (Exception e) {
            InnerLog innerLog2 = InnerLog.INSTANCE;
            String TAG2 = this.c;
            Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
            innerLog2.e(TAG2, "load from file failed", e);
        }
        return arrayList;
    }

    @Override // io.shipbook.shipbooksdk.Appenders.BaseAppender
    public void push(@NotNull BaseLog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        e.e(GlobalScope.INSTANCE, SessionManager.INSTANCE.getThreadContext(), null, new a(log, null), 2, null);
    }

    public final void saveToFile(@NotNull BaseObj obj) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        try {
            if (this.l.length() > this.h) {
                this.l.delete();
                this.o = false;
            }
            if (!this.o) {
                SessionManager sessionManager = SessionManager.INSTANCE;
                if (sessionManager.getToken() != null) {
                    c.appendText$default(this.l, this.e + this.d + sessionManager.getToken() + this.f, null, 2, null);
                } else {
                    Login login = sessionManager.getLogin();
                    if (login != null) {
                        String jSONObject = login.toJson().toString();
                        Intrinsics.checkExpressionValueIsNotNull(jSONObject, "login.toJson().toString()");
                        c.appendText$default(this.l, (Login.class.getName() + this.d) + jSONObject + this.f, null, 2, null);
                    }
                }
            }
            String str = "";
            if (obj instanceof BaseLog) {
                str = BaseLog.class.getName() + this.d;
            } else if (obj instanceof User) {
                str = User.class.getName() + this.d;
            }
            c.appendText$default(this.l, str + obj.toJson() + this.f, null, 2, null);
            this.o = true;
        } catch (Exception e) {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = this.c;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            innerLog.e(TAG, "save file got error", e);
        }
    }

    public final void setHasLog(boolean z) {
        this.o = z;
    }

    @Override // io.shipbook.shipbooksdk.Appenders.BaseAppender
    public void update(@Nullable Map<String, String> map) {
        if (map != null) {
            String str = map.get("maxTime");
            if (str != null) {
                this.g = Double.parseDouble(str);
            }
            String str2 = map.get("maxFileSize");
            if (str2 != null) {
                this.h = Integer.parseInt(str2);
            }
            String str3 = map.get("flushSeverity");
            if (str3 != null) {
                this.i = Severity.valueOf(str3);
            }
            String str4 = map.get("flushSize");
            if (str4 != null) {
                this.j = Integer.parseInt(str4);
            }
        }
    }
}
