package io.shipbook.shipbooksdk;

import android.content.Context;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.clevertap.android.sdk.Constants;
import com.google.android.material.color.c;
import io.shipbook.shipbooksdk.Appenders.AppenderFactory;
import io.shipbook.shipbooksdk.Appenders.BaseAppender;
import io.shipbook.shipbooksdk.Models.BaseLog;
import io.shipbook.shipbooksdk.Models.ConfigResponse;
import io.shipbook.shipbooksdk.Models.Message;
import io.shipbook.shipbooksdk.Models.Severity;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jose4j.jwx.HeaderParameterNames;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001#B\t\b\u0002¢\u0006\u0004\b!\u0010\"J\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\r\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fR2\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f0\u000ej\u0002`\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R(\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006$"}, d2 = {"Lio/shipbook/shipbooksdk/LogManager;", "", "", "clear", "Lio/shipbook/shipbooksdk/Models/BaseLog;", "log", "push", "", HeaderParameterNames.AUTHENTICATION_TAG, "Lio/shipbook/shipbooksdk/Models/Severity;", "getSeverity", "getCallStackSeverity", "Lio/shipbook/shipbooksdk/Models/ConfigResponse;", Constants.KEY_CONFIG, "", "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "Lio/shipbook/shipbooksdk/AppenderMap;", "a", "Ljava/util/Map;", "getAppenders", "()Ljava/util/Map;", "setAppenders", "(Ljava/util/Map;)V", "appenders", "", "Lio/shipbook/shipbooksdk/LogManager$Logger;", "b", "Ljava/util/List;", "getLoggers", "()Ljava/util/List;", "setLoggers", "(Ljava/util/List;)V", "loggers", "<init>", "()V", "Logger", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class LogManager {
    public static final LogManager INSTANCE = new LogManager();
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static volatile Map<String, BaseAppender> f14024a = new LinkedHashMap();
    @NotNull
    public static volatile List<Logger> b = new ArrayList();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\b\u0012\u0006\u0010\u0016\u001a\u00020\u0011¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\r\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0010\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\u000f\u0010\fR\u0019\u0010\u0016\u001a\u00020\u00118\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lio/shipbook/shipbooksdk/LogManager$Logger;", "", "", "a", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", Constants.KEY_KEY, "Lio/shipbook/shipbooksdk/Models/Severity;", "b", "Lio/shipbook/shipbooksdk/Models/Severity;", "getSeverity", "()Lio/shipbook/shipbooksdk/Models/Severity;", "severity", c.f10260a, "getCallStackSeverity", "callStackSeverity", "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "d", "Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "getAppender", "()Lio/shipbook/shipbooksdk/Appenders/BaseAppender;", "appender", "<init>", "(Ljava/lang/String;Lio/shipbook/shipbooksdk/Models/Severity;Lio/shipbook/shipbooksdk/Models/Severity;Lio/shipbook/shipbooksdk/Appenders/BaseAppender;)V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Logger {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final String f14025a;
        @NotNull
        public final Severity b;
        @NotNull
        public final Severity c;
        @NotNull
        public final BaseAppender d;

        public Logger(@NotNull String key, @NotNull Severity severity, @NotNull Severity callStackSeverity, @NotNull BaseAppender appender) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(severity, "severity");
            Intrinsics.checkParameterIsNotNull(callStackSeverity, "callStackSeverity");
            Intrinsics.checkParameterIsNotNull(appender, "appender");
            this.f14025a = key;
            this.b = severity;
            this.c = callStackSeverity;
            this.d = appender;
        }

        @NotNull
        public final BaseAppender getAppender() {
            return this.d;
        }

        @NotNull
        public final Severity getCallStackSeverity() {
            return this.c;
        }

        @NotNull
        public final String getKey() {
            return this.f14025a;
        }

        @NotNull
        public final Severity getSeverity() {
            return this.b;
        }
    }

    public final void clear() {
        f14024a = new LinkedHashMap();
        b = new ArrayList();
    }

    public final void config(@NotNull ConfigResponse config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ConfigResponse.AppenderResponse appenderResponse : config.getAppenders()) {
            BaseAppender baseAppender = f14024a.get(appenderResponse.getName());
            if (baseAppender != null) {
                baseAppender.update(appenderResponse.getConfig());
                linkedHashMap.put(appenderResponse.getName(), baseAppender);
            } else {
                BaseAppender create = AppenderFactory.INSTANCE.create(appenderResponse.getType(), appenderResponse.getName(), appenderResponse.getConfig());
                if (create != null) {
                    linkedHashMap.put(appenderResponse.getName(), create);
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (ConfigResponse.LoggerResponse loggerResponse : config.getLoggers()) {
            BaseAppender baseAppender2 = (BaseAppender) linkedHashMap.get(loggerResponse.getAppenderRef());
            if (baseAppender2 != null) {
                arrayList.add(new Logger(loggerResponse.getName(), loggerResponse.getSeverity(), loggerResponse.getCallStackSeverity(), baseAppender2));
            }
        }
        f14024a = linkedHashMap;
        b = arrayList;
        Context appContext = SessionManager.INSTANCE.getAppContext();
        if (appContext == null) {
            Intrinsics.throwNpe();
        }
        LocalBroadcastManager.getInstance(appContext).sendBroadcast(new Intent(BroadcastNames.INSTANCE.getCONFIG_CHANGE()));
    }

    @NotNull
    public final Map<String, BaseAppender> getAppenders() {
        return f14024a;
    }

    @NotNull
    public final Severity getCallStackSeverity(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        List<Logger> list = b;
        Severity severity = Severity.Off;
        for (Logger logger : list) {
            if (m.startsWith$default(tag, logger.getKey(), false, 2, null) && logger.getCallStackSeverity().ordinal() > severity.ordinal()) {
                severity = logger.getCallStackSeverity();
            }
        }
        return severity;
    }

    @NotNull
    public final List<Logger> getLoggers() {
        return b;
    }

    @NotNull
    public final Severity getSeverity(@NotNull String tag) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        List<Logger> list = b;
        Severity severity = Severity.Off;
        for (Logger logger : list) {
            if (m.startsWith$default(tag, logger.getKey(), false, 2, null) && logger.getSeverity().ordinal() > severity.ordinal()) {
                severity = logger.getSeverity();
            }
        }
        return severity;
    }

    public final void push(@NotNull BaseLog log) {
        Intrinsics.checkParameterIsNotNull(log, "log");
        if (log instanceof Message) {
            List<Logger> list = b;
            Map<String, BaseAppender> map = f14024a;
            HashSet<String> hashSet = new HashSet();
            for (Logger logger : list) {
                Message message = (Message) log;
                String tag = message.getTag();
                if (tag == null) {
                    Intrinsics.throwNpe();
                }
                if (m.startsWith$default(tag, logger.getKey(), false, 2, null) && message.getSeverity().ordinal() <= logger.getSeverity().ordinal()) {
                    hashSet.add(logger.getAppender().getName());
                }
            }
            for (String str : hashSet) {
                BaseAppender baseAppender = map.get(str);
                if (baseAppender != null) {
                    baseAppender.push(log);
                }
            }
            return;
        }
        for (Map.Entry<String, BaseAppender> entry : f14024a.entrySet()) {
            entry.getValue().push(log);
        }
    }

    public final void setAppenders(@NotNull Map<String, BaseAppender> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        f14024a = map;
    }

    public final void setLoggers(@NotNull List<Logger> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        b = list;
    }
}
