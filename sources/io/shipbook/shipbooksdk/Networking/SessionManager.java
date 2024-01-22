package io.shipbook.shipbooksdk.Networking;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.material.color.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import io.shipbook.shipbooksdk.BroadcastNames;
import io.shipbook.shipbooksdk.Events.EventManager;
import io.shipbook.shipbooksdk.ExceptionManager;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.LogManager;
import io.shipbook.shipbooksdk.Models.ConfigResponse;
import io.shipbook.shipbooksdk.Models.Login;
import io.shipbook.shipbooksdk.Models.LoginResponse;
import io.shipbook.shipbooksdk.Models.User;
import io.shipbook.shipbooksdk.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.json.JSONObject;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bT\u0010UJ>\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\nJL\u0010\u0014\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0012J\u0006\u0010\u0015\u001a\u00020\bJ\u0013\u0010\u0017\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u001e\u001a\u00020\u00198\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010+\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R0\u00102\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u0010:\u001a\u0004\u0018\u0001038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010B\u001a\u0004\u0018\u00010;8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010E\u001a\u00020\u00168\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0015\u0010L\u001a\u0004\u0018\u00010I8F@\u0006¢\u0006\u0006\u001a\u0004\bJ\u0010KR(\u0010\f\u001a\u0004\u0018\u00010M2\b\u0010\f\u001a\u0004\u0018\u00010M8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0013\u0010S\u001a\u00020\u00168F@\u0006¢\u0006\u0006\u001a\u0004\bR\u0010F\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lio/shipbook/shipbooksdk/Networking/SessionManager;", "", "Landroid/app/Application;", "application", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "appKey", "Lkotlin/Function1;", "", "completion", "Ljava/net/URI;", "userConfig", FirebaseAnalytics.Event.LOGIN, Constants.END_USER_GLOBAL_ID, "userName", "fullName", "email", "phoneNumber", "", "additionalInfo", "registerUser", "logout", "", "refreshToken", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "a", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getThreadContext", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "threadContext", c.f10260a, "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", "d", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", MapplsLMSDbAdapter.KEY_TOKEN, RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Lkotlin/jvm/functions/Function1;", "getSessionCompletion", "()Lkotlin/jvm/functions/Function1;", "setSessionCompletion", "(Lkotlin/jvm/functions/Function1;)V", "sessionCompletion", "Lio/shipbook/shipbooksdk/Models/User;", "h", "Lio/shipbook/shipbooksdk/Models/User;", "getUser", "()Lio/shipbook/shipbooksdk/Models/User;", "setUser", "(Lio/shipbook/shipbooksdk/Models/User;)V", "user", "Ljava/io/File;", "i", "Ljava/io/File;", "getConfigFile", "()Ljava/io/File;", "setConfigFile", "(Ljava/io/File;)V", "configFile", "j", "Z", "isInLoginRequest", "()Z", "setInLoginRequest", "(Z)V", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "appContext", "Lio/shipbook/shipbooksdk/Models/Login;", "getLogin", "()Lio/shipbook/shipbooksdk/Models/Login;", "setLogin", "(Lio/shipbook/shipbooksdk/Models/Login;)V", "getConnected", "connected", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes12.dex */
public final class SessionManager {
    public static final SessionManager INSTANCE = new SessionManager();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final ExecutorCoroutineDispatcher f14043a = ThreadPoolDispatcherKt.newSingleThreadContext("shipbook");
    public static final String b = SessionManager.class.getSimpleName();
    @Nullable
    public static Application c;
    @Nullable
    public static volatile String d;
    @Nullable
    public static Function1<? super String, Unit> e;
    public static String f;
    public static Login g;
    @Nullable
    public static User h;
    @Nullable
    public static File i;
    public static volatile boolean j;

    @DebugMetadata(c = "io.shipbook.shipbooksdk.Networking.SessionManager$innerLogin$1", f = "SessionManager.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
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
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                if (obj instanceof Result.Failure) {
                    throw ((Result.Failure) obj).exception;
                }
            } else if (!(obj instanceof Result.Failure)) {
                InnerLog innerLog = InnerLog.INSTANCE;
                SessionManager sessionManager = SessionManager.INSTANCE;
                String TAG = SessionManager.access$getTAG$p(sessionManager);
                Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
                StringBuilder sb = new StringBuilder();
                sb.append("current thread: ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                sb.append(currentThread.getName());
                InnerLog.d$default(innerLog, TAG, sb.toString(), null, 4, null);
                ConnectionClient connectionClient = ConnectionClient.INSTANCE;
                Login login = sessionManager.getLogin();
                String valueOf = String.valueOf(login != null ? login.toJson() : null);
                HttpMethod httpMethod = HttpMethod.POST;
                this.label = 1;
                obj = connectionClient.request("auth/loginSdk", valueOf, httpMethod, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                throw ((Result.Failure) obj).exception;
            }
            ResponseData responseData = (ResponseData) obj;
            SessionManager sessionManager2 = SessionManager.INSTANCE;
            sessionManager2.setInLoginRequest(false);
            if (responseData.getOk()) {
                try {
                    if (responseData.getData() != null) {
                        LoginResponse create = LoginResponse.Companion.create(responseData.getData());
                        sessionManager2.setToken(create.getToken());
                        create.getSessionUrl();
                        Function1<String, Unit> sessionCompletion = sessionManager2.getSessionCompletion();
                        if (sessionCompletion != null) {
                            sessionCompletion.invoke(create.getSessionUrl());
                        }
                        LogManager.INSTANCE.config(create.getConfig());
                        File configFile = sessionManager2.getConfigFile();
                        if (configFile == null) {
                            Intrinsics.throwNpe();
                        }
                        String jSONObject = create.getConfig().toJson().toString();
                        Intrinsics.checkExpressionValueIsNotNull(jSONObject, "loginResponse.config.toJson().toString()");
                        kotlin.io.c.writeText$default(configFile, jSONObject, null, 2, null);
                        Context appContext = sessionManager2.getAppContext();
                        if (appContext == null) {
                            Intrinsics.throwNpe();
                        }
                        LocalBroadcastManager.getInstance(appContext).sendBroadcast(new Intent(BroadcastNames.INSTANCE.getCONNECTED()));
                    } else {
                        throw new Exception("No Data error");
                    }
                } catch (Throwable th) {
                    InnerLog innerLog2 = InnerLog.INSTANCE;
                    String TAG2 = SessionManager.access$getTAG$p(SessionManager.INSTANCE);
                    Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                    innerLog2.e(TAG2, "There was a problem with the data", th);
                }
            } else {
                InnerLog innerLog3 = InnerLog.INSTANCE;
                String TAG3 = SessionManager.access$getTAG$p(sessionManager2);
                Intrinsics.checkExpressionValueIsNotNull(TAG3, "TAG");
                InnerLog.e$default(innerLog3, TAG3, "The response not ok", null, 4, null);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "io.shipbook.shipbooksdk.Networking.SessionManager", f = "SessionManager.kt", i = {0, 0}, l = {159}, m = "refreshToken", n = {"this", "refreshToken"}, s = {"L$0", "L$1"})
    /* loaded from: classes12.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
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
            return SessionManager.this.refreshToken(this);
        }
    }

    public static final /* synthetic */ String access$getTAG$p(SessionManager sessionManager) {
        return b;
    }

    public final void a() {
        if (j || getLogin() == null) {
            return;
        }
        j = true;
        d = null;
        e.e(GlobalScope.INSTANCE, f14043a, null, new a(null), 2, null);
    }

    public final void b(File file) {
        d(kotlin.io.c.readText$default(file, null, 1, null));
    }

    public final void c(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            d(readText);
        } finally {
        }
    }

    public final void d(String str) {
        ConfigResponse create = ConfigResponse.Companion.create(new JSONObject(str));
        if (!create.getExceptionReportDisabled()) {
            ExceptionManager.start$default(ExceptionManager.INSTANCE, false, 1, null);
        }
        if (!create.getEventLoggingDisabled()) {
            EventManager.INSTANCE.start();
        }
        LogManager.INSTANCE.config(create);
    }

    @Nullable
    public final Context getAppContext() {
        Application application = c;
        if (application != null) {
            return application.getApplicationContext();
        }
        return null;
    }

    @Nullable
    public final Application getApplication() {
        return c;
    }

    @Nullable
    public final File getConfigFile() {
        return i;
    }

    public final boolean getConnected() {
        if (d != null) {
            return true;
        }
        a();
        return false;
    }

    @Nullable
    public final Login getLogin() {
        Login login;
        User user = h;
        if (user != null && (login = g) != null) {
            login.setUser(user);
        }
        return g;
    }

    @Nullable
    public final Function1<String, Unit> getSessionCompletion() {
        return e;
    }

    @NotNull
    public final ExecutorCoroutineDispatcher getThreadContext() {
        return f14043a;
    }

    @Nullable
    public final String getToken() {
        return d;
    }

    @Nullable
    public final User getUser() {
        return h;
    }

    public final boolean isInLoginRequest() {
        return j;
    }

    public final void login(@NotNull Application application, @NotNull String appId, @NotNull String appKey, @Nullable Function1<? super String, Unit> function1, @Nullable URI uri) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(appId, "appId");
        Intrinsics.checkParameterIsNotNull(appKey, "appKey");
        try {
            c = application;
            Context appContext = getAppContext();
            File file = new File(appContext != null ? appContext.getFilesDir() : null, "config.json");
            i = file;
            if (file.isFile()) {
                File file2 = i;
                if (file2 == null) {
                    Intrinsics.throwNpe();
                }
                if (file2.length() > 0) {
                    File file3 = i;
                    if (file3 == null) {
                        Intrinsics.throwNpe();
                    }
                    b(file3);
                    f = appKey;
                    e = function1;
                    setLogin(new Login(appId, appKey, null, null, null, null, null, null, null, 0, null, 0, null, null, null, null, false, false, null, 524284, null));
                    a();
                }
            }
            if (uri != null) {
                b(new File(uri));
            } else {
                Context appContext2 = getAppContext();
                if (appContext2 == null) {
                    Intrinsics.throwNpe();
                }
                InputStream openRawResource = appContext2.getResources().openRawResource(R.raw.config);
                Intrinsics.checkExpressionValueIsNotNull(openRawResource, "appContext!!.resources.o…RawResource(R.raw.config)");
                c(openRawResource);
            }
            f = appKey;
            e = function1;
            setLogin(new Login(appId, appKey, null, null, null, null, null, null, null, 0, null, 0, null, null, null, null, false, false, null, 524284, null));
            a();
        } catch (Throwable th) {
            InnerLog innerLog = InnerLog.INSTANCE;
            String TAG = b;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            innerLog.e(TAG, "login file failed", th);
        }
    }

    public final void logout() {
        d = null;
        h = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008b A[Catch: all -> 0x00c5, TRY_LEAVE, TryCatch #0 {all -> 0x00c5, blocks: (B:35:0x0085, B:37:0x008b, B:40:0x0092, B:42:0x0098, B:45:0x00ae), top: B:59:0x0085 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0092 A[Catch: all -> 0x00c5, TRY_ENTER, TryCatch #0 {all -> 0x00c5, blocks: (B:35:0x0085, B:37:0x008b, B:40:0x0092, B:42:0x0098, B:45:0x00ae), top: B:59:0x0085 }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshToken(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r13) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.shipbook.shipbooksdk.Networking.SessionManager.refreshToken(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void registerUser(@NotNull String userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map) {
        Intrinsics.checkParameterIsNotNull(userId, "userId");
        h = new User(userId, str, str2, str3, str4, map);
        if (getLogin() != null) {
            Context appContext = getAppContext();
            if (appContext == null) {
                Intrinsics.throwNpe();
            }
            LocalBroadcastManager.getInstance(appContext).sendBroadcast(new Intent(BroadcastNames.INSTANCE.getUSER_CHANGE()));
        }
    }

    public final void setApplication(@Nullable Application application) {
        c = application;
    }

    public final void setConfigFile(@Nullable File file) {
        i = file;
    }

    public final void setInLoginRequest(boolean z) {
        j = z;
    }

    public final void setLogin(@Nullable Login login) {
        g = login;
    }

    public final void setSessionCompletion(@Nullable Function1<? super String, Unit> function1) {
        e = function1;
    }

    public final void setToken(@Nullable String str) {
        d = str;
    }

    public final void setUser(@Nullable User user) {
        h = user;
    }
}
