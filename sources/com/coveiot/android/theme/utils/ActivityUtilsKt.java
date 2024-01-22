package com.coveiot.android.theme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class ActivityUtilsKt {
    public static final /* synthetic */ <T> void launchActivity(Context context, Bundle bundle, Function1<? super Intent, Unit> init) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(context, Object.class);
        init.invoke(intent);
        context.startActivity(intent, bundle);
    }

    public static /* synthetic */ void launchActivity$default(Context context, Bundle bundle, Function1 init, int i, Object obj) {
        if ((i & 1) != 0) {
            bundle = null;
        }
        if ((i & 2) != 0) {
            init = new Function1<Intent, Unit>() { // from class: com.coveiot.android.theme.utils.ActivityUtilsKt$launchActivity$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                    invoke2(intent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Intent intent) {
                    Intrinsics.checkNotNullParameter(intent, "$this$null");
                }
            };
        }
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(context, Object.class);
        init.invoke(intent);
        context.startActivity(intent, bundle);
    }

    public static final void navigateToExternalActivity(@NotNull String activtyPath, @NotNull Context context, @NotNull String previousScreenName) {
        Intrinsics.checkNotNullParameter(activtyPath, "activtyPath");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(previousScreenName, "previousScreenName");
        try {
            Intent intent = new Intent(context, Class.forName(activtyPath));
            intent.putExtra("cv_prev_screen_name", previousScreenName);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void navigateToExternalActivity$default(String str, Context context, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        navigateToExternalActivity(str, context, str2);
    }

    public static final /* synthetic */ <T> Intent newIntent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return new Intent(context, Object.class);
    }

    public static final /* synthetic */ <T> void launchActivity(Activity activity, int i, Bundle bundle, Function1<? super Intent, Unit> init) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(activity, Object.class);
        init.invoke(intent);
        activity.startActivityForResult(intent, i, bundle);
    }

    public static /* synthetic */ void launchActivity$default(Activity activity, int i, Bundle bundle, Function1 init, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        if ((i2 & 2) != 0) {
            bundle = null;
        }
        if ((i2 & 4) != 0) {
            init = new Function1<Intent, Unit>() { // from class: com.coveiot.android.theme.utils.ActivityUtilsKt$launchActivity$2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Intent intent) {
                    invoke2(intent);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull Intent intent) {
                    Intrinsics.checkNotNullParameter(intent, "$this$null");
                }
            };
        }
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Intrinsics.checkNotNullParameter(init, "init");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(activity, Object.class);
        init.invoke(intent);
        activity.startActivityForResult(intent, i, bundle);
    }
}
