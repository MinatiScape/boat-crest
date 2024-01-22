package io.shipbook.shipbooksdk;

import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import io.shipbook.shipbooksdk.Models.Severity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\"\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\"\u0010\t\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\"\u0010\n\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\"\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\"\u0010\f\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005J(\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005R\"\u0010\u0017\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001a"}, d2 = {"Lio/shipbook/shipbooksdk/InnerLog;", "", "", HeaderParameterNames.AUTHENTICATION_TAG, "msg", "", "throwable", "", RsaJsonWebKey.EXPONENT_MEMBER_NAME, Constants.INAPP_WINDOW, "i", "d", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "Lio/shipbook/shipbooksdk/Models/Severity;", "severity", Constants.KEY_MESSAGE, "", "a", "Z", "getEnabled", "()Z", "setEnabled", "(Z)V", "enabled", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class InnerLog {
    public static final InnerLog INSTANCE = new InnerLog();

    /* renamed from: a */
    public static boolean f14021a;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Severity.values().length];
            $EnumSwitchMapping$0 = iArr;
            Severity severity = Severity.Error;
            iArr[severity.ordinal()] = 1;
            Severity severity2 = Severity.Warning;
            iArr[severity2.ordinal()] = 2;
            Severity severity3 = Severity.Info;
            iArr[severity3.ordinal()] = 3;
            Severity severity4 = Severity.Debug;
            iArr[severity4.ordinal()] = 4;
            Severity severity5 = Severity.Verbose;
            iArr[severity5.ordinal()] = 5;
            Severity severity6 = Severity.Off;
            iArr[severity6.ordinal()] = 6;
            int[] iArr2 = new int[Severity.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[severity.ordinal()] = 1;
            iArr2[severity2.ordinal()] = 2;
            iArr2[severity3.ordinal()] = 3;
            iArr2[severity4.ordinal()] = 4;
            iArr2[severity5.ordinal()] = 5;
            iArr2[severity6.ordinal()] = 6;
        }
    }

    public static /* synthetic */ void d$default(InnerLog innerLog, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        innerLog.d(str, str2, th);
    }

    public static /* synthetic */ void e$default(InnerLog innerLog, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        innerLog.e(str, str2, th);
    }

    public static /* synthetic */ void i$default(InnerLog innerLog, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        innerLog.i(str, str2, th);
    }

    public static /* synthetic */ void v$default(InnerLog innerLog, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        innerLog.v(str, str2, th);
    }

    public static /* synthetic */ void w$default(InnerLog innerLog, String str, String str2, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        innerLog.w(str, str2, th);
    }

    public final void d(@NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message(tag, msg, Severity.Debug, th);
    }

    public final void e(@NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message(tag, msg, Severity.Error, th);
    }

    public final boolean getEnabled() {
        return f14021a;
    }

    public final void i(@NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message(tag, msg, Severity.Info, th);
    }

    public final void message(@NotNull String tag, @NotNull String msg, @NotNull Severity severity, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(severity, "severity");
        if (f14021a) {
            String str = "======== " + msg;
            if (th != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[severity.ordinal()];
                if (i == 1) {
                    android.util.Log.e(tag, str, th);
                    return;
                } else if (i == 2) {
                    android.util.Log.w(tag, str, th);
                    return;
                } else if (i == 3) {
                    android.util.Log.i(tag, str, th);
                    return;
                } else if (i == 4) {
                    android.util.Log.d(tag, str, th);
                    return;
                } else if (i != 5) {
                    return;
                } else {
                    android.util.Log.v(tag, str, th);
                    return;
                }
            }
            int i2 = WhenMappings.$EnumSwitchMapping$1[severity.ordinal()];
            if (i2 == 1) {
                android.util.Log.e(tag, str);
            } else if (i2 == 2) {
                android.util.Log.w(tag, str);
            } else if (i2 == 3) {
                android.util.Log.i(tag, str);
            } else if (i2 == 4) {
                android.util.Log.d(tag, str);
            } else if (i2 != 5) {
            } else {
                android.util.Log.v(tag, str);
            }
        }
    }

    public final void setEnabled(boolean z) {
        f14021a = z;
    }

    public final void v(@NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message(tag, msg, Severity.Verbose, th);
    }

    public final void w(@NotNull String tag, @NotNull String msg, @Nullable Throwable th) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        message(tag, msg, Severity.Warning, th);
    }
}
