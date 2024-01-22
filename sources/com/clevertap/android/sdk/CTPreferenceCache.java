package com.clevertap.android.sdk;

import android.content.Context;
import com.clevertap.android.sdk.CTPreferenceCache;
import com.clevertap.android.sdk.inapp.InAppController;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.concurrent.Callable;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CTPreferenceCache {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static volatile CTPreferenceCache f2567a;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    public static boolean firstTimeRequest = true;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final Void d(Context context) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Companion companion = CTPreferenceCache.Companion;
            CTPreferenceCache.firstTimeRequest = StorageHelper.getBoolean(context, InAppController.IS_FIRST_TIME_PERMISSION_REQUEST, true);
            return null;
        }

        public static final Void e(Context context) {
            Intrinsics.checkNotNullParameter(context, "$context");
            StorageHelper.putBooleanImmediate(context, InAppController.IS_FIRST_TIME_PERMISSION_REQUEST, CTPreferenceCache.firstTimeRequest);
            return null;
        }

        public final CTPreferenceCache c(final Context context, CleverTapInstanceConfig cleverTapInstanceConfig) {
            CTExecutorFactory.executors(cleverTapInstanceConfig).ioTask().execute("buildCache", new Callable() { // from class: com.clevertap.android.sdk.f
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void d;
                    d = CTPreferenceCache.Companion.d(context);
                    return d;
                }
            });
            return new CTPreferenceCache();
        }

        @JvmStatic
        @NotNull
        public final CTPreferenceCache getInstance(@NotNull Context context, @NotNull CleverTapInstanceConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            CTPreferenceCache cTPreferenceCache = CTPreferenceCache.f2567a;
            if (cTPreferenceCache == null) {
                synchronized (this) {
                    cTPreferenceCache = CTPreferenceCache.f2567a;
                    if (cTPreferenceCache == null) {
                        CTPreferenceCache c = CTPreferenceCache.Companion.c(context, config);
                        CTPreferenceCache.f2567a = c;
                        cTPreferenceCache = c;
                    }
                }
            }
            return cTPreferenceCache;
        }

        @JvmStatic
        public final void updateCacheToDisk(@NotNull final Context context, @NotNull CleverTapInstanceConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            CTExecutorFactory.executors(config).ioTask().execute("updateCacheToDisk", new Callable() { // from class: com.clevertap.android.sdk.g
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void e;
                    e = CTPreferenceCache.Companion.e(context);
                    return e;
                }
            });
        }
    }

    @JvmStatic
    @NotNull
    public static final CTPreferenceCache getInstance(@NotNull Context context, @NotNull CleverTapInstanceConfig cleverTapInstanceConfig) {
        return Companion.getInstance(context, cleverTapInstanceConfig);
    }

    @JvmStatic
    public static final void updateCacheToDisk(@NotNull Context context, @NotNull CleverTapInstanceConfig cleverTapInstanceConfig) {
        Companion.updateCacheToDisk(context, cleverTapInstanceConfig);
    }

    public final boolean isFirstTimeRequest() {
        return firstTimeRequest;
    }

    public final void setFirstTimeRequest(boolean z) {
        firstTimeRequest = z;
    }
}
