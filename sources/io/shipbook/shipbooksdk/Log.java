package io.shipbook.shipbooksdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.goodix.ble.libcomx.logger.RingLogger;
import io.shipbook.shipbooksdk.Models.Message;
import io.shipbook.shipbooksdk.Models.Severity;
import io.shipbook.shipbooksdk.Models.StackTraceElement;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import io.shipbook.shipbooksdk.Util.ListStackTraceElementExtKt;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u001a\u001a\u00020\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u0003\u001a\u00020\u0002H\u0004J\u001c\u0010\b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\t\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\n\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J\u001c\u0010\f\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J[\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0004\b\u0014\u0010\u0015R\u0019\u0010\u001a\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001e"}, d2 = {"Lio/shipbook/shipbooksdk/Log;", "", "", "finalize", "", "msg", "", "throwable", RsaJsonWebKey.EXPONENT_MEMBER_NAME, Constants.INAPP_WINDOW, "i", "d", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "Lio/shipbook/shipbooksdk/Models/Severity;", "severity", "function", "fileName", "", "lineNumber", "className", Constants.KEY_MESSAGE, "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Severity;Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "f", "Ljava/lang/String;", "getTag", "()Ljava/lang/String;", HeaderParameterNames.AUTHENTICATION_TAG, "<init>", "(Ljava/lang/String;)V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class Log {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final String f14022a;
    public volatile Severity b;
    public volatile Severity c;
    public int d;
    public final Log$broadcastReceiver$1 e;
    @NotNull
    public final String f;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J&\u0010\b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J&\u0010\t\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J&\u0010\n\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J&\u0010\u000b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007J&\u0010\f\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0007Je\u0010\u0013\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0013\u0010\u0014Je\u0010\u0013\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00152\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0013\u0010\u0016¨\u0006\u0019"}, d2 = {"Lio/shipbook/shipbooksdk/Log$Companion;", "", "", HeaderParameterNames.AUTHENTICATION_TAG, "msg", "", "throwable", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, Constants.INAPP_WINDOW, "i", "d", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "", "severity", "function", "fileName", "lineNumber", "className", Constants.KEY_MESSAGE, "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "Lio/shipbook/shipbooksdk/Models/Severity;", "(Ljava/lang/String;Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Severity;Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void d$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = null;
            }
            companion.d(str, str2, th);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void e$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = null;
            }
            companion.e(str, str2, th);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void i$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = null;
            }
            companion.i(str, str2, th);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void message$default(Companion companion, String str, String str2, int i, Throwable th, String str3, String str4, Integer num, String str5, int i2, Object obj) {
            companion.message(str, str2, i, (i2 & 8) != 0 ? null : th, (i2 & 16) != 0 ? null : str3, (i2 & 32) != 0 ? null : str4, (i2 & 64) != 0 ? null : num, (i2 & 128) != 0 ? null : str5);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void v$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = null;
            }
            companion.v(str, str2, th);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void w$default(Companion companion, String str, String str2, Throwable th, int i, Object obj) {
            if ((i & 4) != 0) {
                th = null;
            }
            companion.w(str, str2, th);
        }

        @JvmStatic
        @JvmOverloads
        public final void d(@Nullable String str, @NotNull String str2) {
            d$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void d(@Nullable String str, @NotNull String msg, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message$default(this, str, msg, Severity.Debug, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void e(@Nullable String str, @NotNull String str2) {
            e$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void e(@Nullable String str, @NotNull String msg, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message$default(this, str, msg, Severity.Error, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void i(@Nullable String str, @NotNull String str2) {
            i$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void i(@Nullable String str, @NotNull String msg, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message$default(this, str, msg, Severity.Info, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, int i) {
            message$default(this, str, str2, i, (Throwable) null, (String) null, (String) null, (Integer) null, (String) null, (int) RingLogger.EVT_UPDATE, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th) {
            message$default(this, str, str2, i, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3) {
            message$default(this, str, str2, i, th, str3, (String) null, (Integer) null, (String) null, 224, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3, @Nullable String str4) {
            message$default(this, str, str2, i, th, str3, str4, (Integer) null, (String) null, 192, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num) {
            message$default(this, str, str2, i, th, str3, str4, num, (String) null, 128, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String msg, int i, @Nullable Throwable th, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message(str, msg, Severity.Companion.fromInt(i), th, str2, str3, num, str4);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity) {
            message$default(this, str, str2, severity, (Throwable) null, (String) null, (String) null, (Integer) null, (String) null, (int) RingLogger.EVT_UPDATE, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th) {
            message$default(this, str, str2, severity, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3) {
            message$default(this, str, str2, severity, th, str3, (String) null, (Integer) null, (String) null, 224, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3, @Nullable String str4) {
            message$default(this, str, str2, severity, th, str3, str4, (Integer) null, (String) null, 192, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num) {
            message$default(this, str, str2, severity, th, str3, str4, num, (String) null, 128, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void v(@Nullable String str, @NotNull String str2) {
            v$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void v(@Nullable String str, @NotNull String msg, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message$default(this, str, msg, Severity.Verbose, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void w(@Nullable String str, @NotNull String str2) {
            w$default(this, str, str2, null, 4, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void w(@Nullable String str, @NotNull String msg, @Nullable Throwable th) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            message$default(this, str, msg, Severity.Warning, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void message$default(Companion companion, String str, String str2, Severity severity, Throwable th, String str3, String str4, Integer num, String str5, int i, Object obj) {
            companion.message(str, str2, severity, (i & 8) != 0 ? null : th, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : num, (i & 128) != 0 ? null : str5);
        }

        @JvmStatic
        @JvmOverloads
        public final void message(@Nullable String str, @NotNull String msg, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str2, @Nullable String str3, @Nullable Integer num, @Nullable String str4) {
            Message message;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            Intrinsics.checkParameterIsNotNull(severity, "severity");
            List<StackTraceElement> list = null;
            if (str == null) {
                Message message2 = new Message(severity, msg, null, null, th, str2, str3, num, str4, null, 0, null, null, 7680, null);
                String tag = message2.getTag();
                if (tag == null) {
                    return;
                }
                int ordinal = severity.ordinal();
                LogManager logManager = LogManager.INSTANCE;
                if (ordinal > logManager.getSeverity(tag).ordinal()) {
                    return;
                }
                if (severity.ordinal() <= logManager.getCallStackSeverity(tag).ordinal()) {
                    StackTraceElement[] stackTrace = new Throwable().getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Throwable().stackTrace");
                    list = ListStackTraceElementExtKt.toInternal(stackTrace);
                }
                message2.setStackTrace(list);
                message = message2;
            } else {
                int ordinal2 = severity.ordinal();
                LogManager logManager2 = LogManager.INSTANCE;
                if (ordinal2 > logManager2.getSeverity(str).ordinal()) {
                    return;
                }
                if (severity.ordinal() <= logManager2.getCallStackSeverity(str).ordinal()) {
                    StackTraceElement[] stackTrace2 = new Throwable().getStackTrace();
                    Intrinsics.checkExpressionValueIsNotNull(stackTrace2, "Throwable().stackTrace");
                    list = ListStackTraceElementExtKt.toInternal(stackTrace2);
                }
                message = new Message(severity, msg, str, list, th, str2, str3, num, str4, null, 0, null, null, 7680, null);
            }
            LogManager.INSTANCE.push(message);
        }
    }

    /* JADX WARN: Type inference failed for: r8v2, types: [io.shipbook.shipbooksdk.Log$broadcastReceiver$1] */
    public Log(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        this.f = tag;
        String TAG = Log.class.getSimpleName();
        this.f14022a = TAG;
        LogManager logManager = LogManager.INSTANCE;
        this.b = logManager.getSeverity(tag);
        this.c = logManager.getCallStackSeverity(tag);
        this.e = new BroadcastReceiver() { // from class: io.shipbook.shipbooksdk.Log$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@Nullable Context context, @Nullable Intent intent) {
                String TAG2;
                InnerLog innerLog = InnerLog.INSTANCE;
                TAG2 = Log.this.f14022a;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                InnerLog.d$default(innerLog, TAG2, "got receiver configChange for tag " + Log.this.getTag(), null, 4, null);
                Log log = Log.this;
                LogManager logManager2 = LogManager.INSTANCE;
                log.b = logManager2.getSeverity(log.getTag());
                Log log2 = Log.this;
                log2.c = logManager2.getCallStackSeverity(log2.getTag());
            }
        };
        InnerLog innerLog = InnerLog.INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.d$default(innerLog, TAG, "register broadcast receiver", null, 4, null);
        a();
    }

    @JvmStatic
    @JvmOverloads
    public static final void d(@Nullable String str, @NotNull String str2) {
        Companion.d$default(Companion, str, str2, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void d(@Nullable String str, @NotNull String str2, @Nullable Throwable th) {
        Companion.d(str, str2, th);
    }

    @JvmOverloads
    public static /* synthetic */ void d$default(Log log, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        log.d(str, th);
    }

    @JvmStatic
    @JvmOverloads
    public static final void e(@Nullable String str, @NotNull String str2) {
        Companion.e$default(Companion, str, str2, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void e(@Nullable String str, @NotNull String str2, @Nullable Throwable th) {
        Companion.e(str, str2, th);
    }

    @JvmOverloads
    public static /* synthetic */ void e$default(Log log, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        log.e(str, th);
    }

    @JvmStatic
    @JvmOverloads
    public static final void i(@Nullable String str, @NotNull String str2) {
        Companion.i$default(Companion, str, str2, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void i(@Nullable String str, @NotNull String str2, @Nullable Throwable th) {
        Companion.i(str, str2, th);
    }

    @JvmOverloads
    public static /* synthetic */ void i$default(Log log, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        log.i(str, th);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i) {
        Companion.message$default(Companion, str, str2, i, (Throwable) null, (String) null, (String) null, (Integer) null, (String) null, (int) RingLogger.EVT_UPDATE, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th) {
        Companion.message$default(Companion, str, str2, i, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3) {
        Companion.message$default(Companion, str, str2, i, th, str3, (String) null, (Integer) null, (String) null, 224, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3, @Nullable String str4) {
        Companion.message$default(Companion, str, str2, i, th, str3, str4, (Integer) null, (String) null, 192, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num) {
        Companion.message$default(Companion, str, str2, i, th, str3, str4, num, (String) null, 128, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, int i, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num, @Nullable String str5) {
        Companion.message(str, str2, i, th, str3, str4, num, str5);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity) {
        Companion.message$default(Companion, str, str2, severity, (Throwable) null, (String) null, (String) null, (Integer) null, (String) null, (int) RingLogger.EVT_UPDATE, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th) {
        Companion.message$default(Companion, str, str2, severity, th, (String) null, (String) null, (Integer) null, (String) null, 240, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3) {
        Companion.message$default(Companion, str, str2, severity, th, str3, (String) null, (Integer) null, (String) null, 224, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3, @Nullable String str4) {
        Companion.message$default(Companion, str, str2, severity, th, str3, str4, (Integer) null, (String) null, 192, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num) {
        Companion.message$default(Companion, str, str2, severity, th, str3, str4, num, (String) null, 128, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void message(@Nullable String str, @NotNull String str2, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str3, @Nullable String str4, @Nullable Integer num, @Nullable String str5) {
        Companion.message(str, str2, severity, th, str3, str4, num, str5);
    }

    @JvmOverloads
    public static /* synthetic */ void message$default(Log log, String str, Severity severity, Throwable th, String str2, String str3, Integer num, String str4, int i, Object obj) {
        log.message(str, severity, (i & 4) != 0 ? null : th, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : str4);
    }

    @JvmStatic
    @JvmOverloads
    public static final void v(@Nullable String str, @NotNull String str2) {
        Companion.v$default(Companion, str, str2, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void v(@Nullable String str, @NotNull String str2, @Nullable Throwable th) {
        Companion.v(str, str2, th);
    }

    @JvmOverloads
    public static /* synthetic */ void v$default(Log log, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        log.v(str, th);
    }

    @JvmStatic
    @JvmOverloads
    public static final void w(@Nullable String str, @NotNull String str2) {
        Companion.w$default(Companion, str, str2, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void w(@Nullable String str, @NotNull String str2, @Nullable Throwable th) {
        Companion.w(str, str2, th);
    }

    @JvmOverloads
    public static /* synthetic */ void w$default(Log log, String str, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        log.w(str, th);
    }

    public final void a() {
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = this.f14022a;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.d$default(innerLog, TAG, "add broadcast receiver with counter " + this.d, null, 4, null);
        SessionManager sessionManager = SessionManager.INSTANCE;
        if (sessionManager.getAppContext() != null && this.d > 0) {
            Context appContext = sessionManager.getAppContext();
            if (appContext == null) {
                Intrinsics.throwNpe();
            }
            LocalBroadcastManager.getInstance(appContext).registerReceiver(this.e, new IntentFilter(BroadcastNames.INSTANCE.getCONFIG_CHANGE()));
            return;
        }
        new Timer().schedule(new TimerTask() { // from class: io.shipbook.shipbooksdk.Log$addBroadcastReceiver$$inlined$schedule$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i;
                int i2;
                String TAG2;
                Log log = Log.this;
                i = log.d;
                log.d = i + 1;
                i2 = Log.this.d;
                if (i2 < 5) {
                    Log.this.a();
                    return;
                }
                InnerLog innerLog2 = InnerLog.INSTANCE;
                TAG2 = Log.this.f14022a;
                Intrinsics.checkExpressionValueIsNotNull(TAG2, "TAG");
                InnerLog.d$default(innerLog2, TAG2, "counter bigger than 5", null, 4, null);
            }
        }, 0L);
    }

    @JvmOverloads
    public final void d(@NotNull String str) {
        d$default(this, str, null, 2, null);
    }

    @JvmOverloads
    public final void d(@NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message$default(this, msg, Severity.Debug, th, null, null, null, null, 120, null);
    }

    @JvmOverloads
    public final void e(@NotNull String str) {
        e$default(this, str, null, 2, null);
    }

    @JvmOverloads
    public final void e(@NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message$default(this, msg, Severity.Error, th, null, null, null, null, 120, null);
    }

    public final void finalize() {
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = this.f14022a;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.d$default(innerLog, TAG, "unregister broadcast receiver", null, 4, null);
        SessionManager sessionManager = SessionManager.INSTANCE;
        if (sessionManager.getAppContext() != null) {
            Context appContext = sessionManager.getAppContext();
            if (appContext == null) {
                Intrinsics.throwNpe();
            }
            LocalBroadcastManager.getInstance(appContext).unregisterReceiver(this.e);
        }
    }

    @NotNull
    public final String getTag() {
        return this.f;
    }

    @JvmOverloads
    public final void i(@NotNull String str) {
        i$default(this, str, null, 2, null);
    }

    @JvmOverloads
    public final void i(@NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message$default(this, msg, Severity.Info, th, null, null, null, null, 120, null);
    }

    @JvmOverloads
    public final void message(@NotNull String str, @NotNull Severity severity) {
        message$default(this, str, severity, null, null, null, null, null, 124, null);
    }

    @JvmOverloads
    public final void message(@NotNull String str, @NotNull Severity severity, @Nullable Throwable th) {
        message$default(this, str, severity, th, null, null, null, null, 120, null);
    }

    @JvmOverloads
    public final void message(@NotNull String str, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str2) {
        message$default(this, str, severity, th, str2, null, null, null, 112, null);
    }

    @JvmOverloads
    public final void message(@NotNull String str, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str2, @Nullable String str3) {
        message$default(this, str, severity, th, str2, str3, null, null, 96, null);
    }

    @JvmOverloads
    public final void message(@NotNull String str, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str2, @Nullable String str3, @Nullable Integer num) {
        message$default(this, str, severity, th, str2, str3, num, null, 64, null);
    }

    @JvmOverloads
    public final void message(@NotNull String msg, @NotNull Severity severity, @Nullable Throwable th, @Nullable String str, @Nullable String str2, @Nullable Integer num, @Nullable String str3) {
        List<StackTraceElement> list;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(severity, "severity");
        if (severity.ordinal() > this.b.ordinal()) {
            return;
        }
        if (severity.ordinal() <= this.c.ordinal()) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Throwable().stackTrace");
            list = ListStackTraceElementExtKt.toInternal(stackTrace);
        } else {
            list = null;
        }
        LogManager.INSTANCE.push(new Message(severity, msg, this.f, list, th, str, str2, num, str3, null, 0, null, null, 7680, null));
    }

    @JvmOverloads
    public final void v(@NotNull String str) {
        v$default(this, str, null, 2, null);
    }

    @JvmOverloads
    public final void v(@NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message$default(this, msg, Severity.Verbose, th, null, null, null, null, 120, null);
    }

    @JvmOverloads
    public final void w(@NotNull String str) {
        w$default(this, str, null, 2, null);
    }

    @JvmOverloads
    public final void w(@NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message$default(this, msg, Severity.Warning, th, null, null, null, null, 120, null);
    }
}
