package com.coveiot.android.remotecommandframework.alexa.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class AlexaAppUtils {
    @NotNull
    public static final String ALEXA_PACKAGE_NAME = "com.amazon.dee.app";
    @NotNull
    public static final AlexaAppUtils INSTANCE = new AlexaAppUtils();

    @JvmStatic
    public static final boolean isAlexaAppInstalled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            PackageManager packageManager = context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
            PackageInfo packageInfo = packageManager.getPackageInfo(ALEXA_PACKAGE_NAME, 0);
            if (Build.VERSION.SDK_INT >= 28) {
                if (packageInfo.getLongVersionCode() <= 866607211) {
                    return false;
                }
            } else if (packageInfo == null) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
