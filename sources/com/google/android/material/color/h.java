package com.google.android.material.color;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
/* loaded from: classes10.dex */
public final class h {
    public static void a(@NonNull Context context, @StyleRes int i) {
        Resources.Theme b;
        context.getTheme().applyStyle(i, true);
        if (!(context instanceof Activity) || (b = b((Activity) context)) == null) {
            return;
        }
        b.applyStyle(i, true);
    }

    @Nullable
    public static Resources.Theme b(@NonNull Activity activity) {
        View peekDecorView;
        Context context;
        Window window = activity.getWindow();
        if (window == null || (peekDecorView = window.peekDecorView()) == null || (context = peekDecorView.getContext()) == null) {
            return null;
        }
        return context.getTheme();
    }
}
