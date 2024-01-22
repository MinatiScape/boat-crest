package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonOneTitle {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6061a;
    @NotNull
    public final String b;
    @NotNull
    public final Dialog c;
    @NotNull
    public final Button d;

    public BottomSheetDialogOneButtonOneTitle(@NotNull Context context, @NotNull String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        this.f6061a = context;
        this.b = title;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.c = dialog;
        dialog.requestWindowFeature(1);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.generic_dialog_one_button_one_title);
        View findViewById = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setText(title);
        View findViewById2 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.d = (Button) findViewById2;
    }

    public final void dismiss() {
        this.c.dismiss();
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f6061a;
    }

    @NotNull
    public final String getTitle() {
        return this.b;
    }

    @NotNull
    public final Button getYesButton() {
        return this.d;
    }

    public final boolean isShowing() {
        return this.c.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.c.setCancelable(z);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setText(positiveBtn);
        this.d.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6061a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.c.show();
    }
}
