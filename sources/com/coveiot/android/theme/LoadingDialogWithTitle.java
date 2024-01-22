package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class LoadingDialogWithTitle extends Dialog {
    @NotNull
    public final TextView h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDialogWithTitle(@NotNull Context context) {
        super(context, R.style.DialogThemeDarWindowBG);
        Intrinsics.checkNotNullParameter(context, "context");
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loading_dialog_with_title);
        View findViewById = findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "this.findViewById(R.id.title)");
        this.h = (TextView) findViewById;
    }

    @NotNull
    public final TextView getTitleTv() {
        return this.h;
    }

    public final void setTitle(@NotNull String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.h.setText(title);
    }
}
