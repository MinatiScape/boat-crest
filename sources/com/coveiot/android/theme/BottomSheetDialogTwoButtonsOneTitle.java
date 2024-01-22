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
public class BottomSheetDialogTwoButtonsOneTitle {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6078a;
    @NotNull
    public final String b;
    @NotNull
    public final Dialog c;
    @NotNull
    public final Button d;
    @NotNull
    public final Button e;

    public BottomSheetDialogTwoButtonsOneTitle(@NotNull Context context, @NotNull String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        this.f6078a = context;
        this.b = title;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.c = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_two_buttons_one_title);
        View findViewById = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setText(title);
        View findViewById2 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.d = (Button) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.negative_btn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById3;
    }

    public final void dismiss() {
        if (this.c.isShowing()) {
            this.c.dismiss();
        }
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f6078a;
    }

    @NotNull
    public final Button getNoButton() {
        return this.e;
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

    public final void setNegativeButton(@NotNull String nevgativeBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(nevgativeBtn, "nevgativeBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(nevgativeBtn);
        this.e.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setText(positiveBtn);
        this.d.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6078a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.c.isShowing()) {
            return;
        }
        this.c.show();
    }
}
