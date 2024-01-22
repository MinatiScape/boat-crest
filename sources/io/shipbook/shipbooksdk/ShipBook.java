package io.shipbook.shipbooksdk;

import android.app.Application;
import androidx.core.app.NotificationCompat;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.shipbook.shipbooksdk.Models.Message;
import io.shipbook.shipbooksdk.Models.ScreenEvent;
import io.shipbook.shipbooksdk.Networking.ConnectionClient;
import io.shipbook.shipbooksdk.Networking.SessionManager;
import java.net.URI;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lio/shipbook/shipbooksdk/ShipBook;", "", "<init>", "()V", "Companion", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ShipBook {
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b!\u0010\"JD\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\rH\u0007J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0004H\u0007JX\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u001aH\u0007J\b\u0010\u001d\u001a\u00020\bH\u0007J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0004H\u0007J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0004H\u0007¨\u0006#"}, d2 = {"Lio/shipbook/shipbooksdk/ShipBook$Companion;", "", "Landroid/app/Application;", "application", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "appKey", "Lkotlin/Function1;", "", "completion", "Ljava/net/URI;", NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, "start", "", "enable", "enableInnerLog", "url", "setConnectionUrl", HeaderParameterNames.AUTHENTICATION_TAG, "Lio/shipbook/shipbooksdk/Log;", "getLogger", Constants.END_USER_GLOBAL_ID, "userName", "fullName", "email", "phoneNumber", "", "additionalInfo", "registerUser", "logout", AppMeasurementSdk.ConditionalUserProperty.NAME, "screen", "addWrapperClass", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void registerUser$default(Companion companion, String str, String str2, String str3, String str4, String str5, Map map, int i, Object obj) {
            companion.registerUser(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) == 0 ? map : null);
        }

        @JvmStatic
        @JvmOverloads
        public static /* synthetic */ void start$default(Companion companion, Application application, String str, String str2, Function1 function1, URI uri, int i, Object obj) {
            companion.start(application, str, str2, (i & 8) != 0 ? null : function1, (i & 16) != 0 ? null : uri);
        }

        @JvmStatic
        public final void addWrapperClass(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Message.Companion.addIgnoreClass(name);
        }

        @JvmStatic
        public final void enableInnerLog(boolean z) {
            InnerLog.INSTANCE.setEnabled(z);
        }

        @JvmStatic
        @NotNull
        public final Log getLogger(@NotNull String tag) {
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            return new Log(tag);
        }

        @JvmStatic
        public final void logout() {
            SessionManager.INSTANCE.logout();
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String str) {
            registerUser$default(this, str, null, null, null, null, null, 62, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String str, @Nullable String str2) {
            registerUser$default(this, str, str2, null, null, null, null, 60, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3) {
            registerUser$default(this, str, str2, str3, null, null, null, 56, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
            registerUser$default(this, str, str2, str3, str4, null, null, 48, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
            registerUser$default(this, str, str2, str3, str4, str5, null, 32, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void registerUser(@NotNull String userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(userId, "userId");
            SessionManager.INSTANCE.registerUser(userId, str, str2, str3, str4, map);
        }

        @JvmStatic
        public final void screen(@NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            LogManager.INSTANCE.push(new ScreenEvent(name, 0, null, null, 14, null));
        }

        @JvmStatic
        public final void setConnectionUrl(@NotNull String url) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            ConnectionClient.INSTANCE.setBaseUrl(url);
        }

        @JvmStatic
        @JvmOverloads
        public final void start(@NotNull Application application, @NotNull String str, @NotNull String str2) {
            start$default(this, application, str, str2, null, null, 24, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void start(@NotNull Application application, @NotNull String str, @NotNull String str2, @Nullable Function1<? super String, Unit> function1) {
            start$default(this, application, str, str2, function1, null, 16, null);
        }

        @JvmStatic
        @JvmOverloads
        public final void start(@NotNull Application application, @NotNull String appId, @NotNull String appKey, @Nullable Function1<? super String, Unit> function1, @Nullable URI uri) {
            Intrinsics.checkParameterIsNotNull(application, "application");
            Intrinsics.checkParameterIsNotNull(appId, "appId");
            Intrinsics.checkParameterIsNotNull(appKey, "appKey");
            SessionManager.INSTANCE.login(application, appId, appKey, function1, uri);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmStatic
    public static final void addWrapperClass(@NotNull String str) {
        Companion.addWrapperClass(str);
    }

    @JvmStatic
    public static final void enableInnerLog(boolean z) {
        Companion.enableInnerLog(z);
    }

    @JvmStatic
    @NotNull
    public static final Log getLogger(@NotNull String str) {
        return Companion.getLogger(str);
    }

    @JvmStatic
    public static final void logout() {
        Companion.logout();
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str) {
        Companion.registerUser$default(Companion, str, null, null, null, null, null, 62, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str, @Nullable String str2) {
        Companion.registerUser$default(Companion, str, str2, null, null, null, null, 60, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3) {
        Companion.registerUser$default(Companion, str, str2, str3, null, null, null, 56, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        Companion.registerUser$default(Companion, str, str2, str3, str4, null, null, 48, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        Companion.registerUser$default(Companion, str, str2, str3, str4, str5, null, 32, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void registerUser(@NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Map<String, String> map) {
        Companion.registerUser(str, str2, str3, str4, str5, map);
    }

    @JvmStatic
    public static final void screen(@NotNull String str) {
        Companion.screen(str);
    }

    @JvmStatic
    public static final void setConnectionUrl(@NotNull String str) {
        Companion.setConnectionUrl(str);
    }

    @JvmStatic
    @JvmOverloads
    public static final void start(@NotNull Application application, @NotNull String str, @NotNull String str2) {
        Companion.start$default(Companion, application, str, str2, null, null, 24, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void start(@NotNull Application application, @NotNull String str, @NotNull String str2, @Nullable Function1<? super String, Unit> function1) {
        Companion.start$default(Companion, application, str, str2, function1, null, 16, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void start(@NotNull Application application, @NotNull String str, @NotNull String str2, @Nullable Function1<? super String, Unit> function1, @Nullable URI uri) {
        Companion.start(application, str, str2, function1, uri);
    }
}
