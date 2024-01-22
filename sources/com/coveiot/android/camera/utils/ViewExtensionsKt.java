package com.coveiot.android.camera.utils;

import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.ImageButton;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ViewExtensionsKt {
    public static final long ANIMATION_FAST_MILLIS = 50;
    public static final long ANIMATION_SLOW_MILLIS = 100;
    public static final int FLAGS_FULLSCREEN = 4871;

    public static final void c(View view, DisplayCutout displayCutout) {
        view.setPadding(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom());
    }

    public static final WindowInsets d(View this_padWithDisplayCutout, View view, WindowInsets insets) {
        Intrinsics.checkNotNullParameter(this_padWithDisplayCutout, "$this_padWithDisplayCutout");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(insets, "insets");
        DisplayCutout displayCutout = insets.getDisplayCutout();
        if (displayCutout != null) {
            c(this_padWithDisplayCutout, displayCutout);
        }
        return insets;
    }

    public static final void e(ImageButton this_simulateClick) {
        Intrinsics.checkNotNullParameter(this_simulateClick, "$this_simulateClick");
        this_simulateClick.invalidate();
        this_simulateClick.setPressed(false);
    }

    @RequiresApi(28)
    public static final void padWithDisplayCutout(@NotNull final View view) {
        DisplayCutout displayCutout;
        Intrinsics.checkNotNullParameter(view, "<this>");
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets != null && (displayCutout = rootWindowInsets.getDisplayCutout()) != null) {
            c(view, displayCutout);
        }
        view.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.coveiot.android.camera.utils.a
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                WindowInsets d;
                d = ViewExtensionsKt.d(view, view2, windowInsets);
                return d;
            }
        });
    }

    public static final void showImmersive(@NotNull AlertDialog alertDialog) {
        Intrinsics.checkNotNullParameter(alertDialog, "<this>");
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setFlags(8, 8);
        }
        Window window2 = alertDialog.getWindow();
        View decorView = window2 != null ? window2.getDecorView() : null;
        if (decorView != null) {
            decorView.setSystemUiVisibility(FLAGS_FULLSCREEN);
        }
        alertDialog.show();
        Window window3 = alertDialog.getWindow();
        if (window3 != null) {
            window3.clearFlags(8);
        }
    }

    public static final void simulateClick(@NotNull final ImageButton imageButton, long j) {
        Intrinsics.checkNotNullParameter(imageButton, "<this>");
        imageButton.performClick();
        imageButton.setPressed(true);
        imageButton.invalidate();
        imageButton.postDelayed(new Runnable() { // from class: com.coveiot.android.camera.utils.b
            @Override // java.lang.Runnable
            public final void run() {
                ViewExtensionsKt.e(imageButton);
            }
        }, j);
    }

    public static /* synthetic */ void simulateClick$default(ImageButton imageButton, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 50;
        }
        simulateClick(imageButton, j);
    }
}
