package com.coveiot.android.theme;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class GenericMessageDialog extends Dialog {
    @NotNull
    public Context h;
    @NotNull
    public String i;
    @NotNull
    public String j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GenericMessageDialog(@NotNull Context mContext, @NotNull String mTitle, @NotNull String mContentText) {
        super(mContext, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mContentText, "mContentText");
        this.h = mContext;
        this.i = mTitle;
        this.j = mContentText;
        requestWindowFeature(1);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.create_new_confirmation_dialog);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        ((TextView) findViewById(R.id.title)).setText(this.i);
        ((TextView) findViewById(R.id.content)).setText(this.j);
    }

    @NotNull
    public final String getMContentText() {
        return this.j;
    }

    @NotNull
    public final Context getMContext() {
        return this.h;
    }

    @NotNull
    public final String getMTitle() {
        return this.i;
    }

    public final void setMContentText(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setMContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.h = context;
    }

    public final void setMTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.i = str;
    }
}
