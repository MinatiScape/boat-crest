package com.clevertap.android.sdk.inapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import com.clevertap.android.sdk.CTStringResources;
import com.clevertap.android.sdk.R;
import com.clevertap.android.sdk.inapp.AlertDialogPromptForSettings;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class AlertDialogPromptForSettings {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void c(Function0 onAccept, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(onAccept, "$onAccept");
            onAccept.invoke();
        }

        public static final void d(Function0 onDecline, DialogInterface dialogInterface, int i) {
            Intrinsics.checkNotNullParameter(onDecline, "$onDecline");
            onDecline.invoke();
        }

        @JvmStatic
        public final void show(@NotNull Activity activity, @NotNull final Function0<Unit> onAccept, @NotNull final Function0<Unit> onDecline) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(onAccept, "onAccept");
            Intrinsics.checkNotNullParameter(onDecline, "onDecline");
            Context applicationContext = activity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
            CTStringResources cTStringResources = new CTStringResources(applicationContext, R.string.ct_permission_not_available_title, R.string.ct_permission_not_available_message, R.string.ct_permission_not_available_open_settings_option, R.string.ct_txt_cancel);
            String component1 = cTStringResources.component1();
            String component2 = cTStringResources.component2();
            (Build.VERSION.SDK_INT >= 21 ? new AlertDialog.Builder(activity, 16974394) : new AlertDialog.Builder(activity)).setTitle(component1).setCancelable(false).setMessage(component2).setPositiveButton(cTStringResources.component3(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.a
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogPromptForSettings.Companion.c(Function0.this, dialogInterface, i);
                }
            }).setNegativeButton(cTStringResources.component4(), new DialogInterface.OnClickListener() { // from class: com.clevertap.android.sdk.inapp.b
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogPromptForSettings.Companion.d(Function0.this, dialogInterface, i);
                }
            }).show();
        }
    }

    @JvmStatic
    public static final void show(@NotNull Activity activity, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function02) {
        Companion.show(activity, function0, function02);
    }
}
