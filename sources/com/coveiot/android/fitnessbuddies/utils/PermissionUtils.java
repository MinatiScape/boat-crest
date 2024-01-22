package com.coveiot.android.fitnessbuddies.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.coveiot.android.fitnessbuddies.utils.PreferenceManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class PermissionUtils {
    @NotNull
    public static final PermissionUtils INSTANCE = new PermissionUtils();

    /* loaded from: classes4.dex */
    public interface PermissionAskListener {
        void onPermissionAsk();

        void onPermissionDisabled();

        void onPermissionGranted();

        void onPermissionPreviouslyDenied();
    }

    public final boolean a(Context context, String str) {
        return shouldAskPermission() && ContextCompat.checkSelfPermission(context, str) != 0;
    }

    @SuppressLint({"NewApi"})
    public final void checkPermission(@NotNull Context context, @NotNull String permission, @NotNull PermissionAskListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permission, "permission");
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (a(context, permission)) {
            if (((Activity) context).shouldShowRequestPermissionRationale(permission)) {
                listener.onPermissionPreviouslyDenied();
                return;
            }
            PreferenceManager.Companion companion = PreferenceManager.Companion;
            if (companion.isFirstTimeAskingPermission(context, permission)) {
                companion.firstTimeAskingPermission(context, permission, false);
                listener.onPermissionAsk();
                return;
            }
            listener.onPermissionDisabled();
            return;
        }
        listener.onPermissionGranted();
    }

    public final boolean shouldAskPermission() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
