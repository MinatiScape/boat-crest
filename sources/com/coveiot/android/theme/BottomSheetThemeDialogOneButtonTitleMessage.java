package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetThemeDialogOneButtonTitleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6082a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final Dialog d;
    @NotNull
    public final Button e;

    public BottomSheetThemeDialogOneButtonTitleMessage(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6082a = context;
        this.b = title;
        this.c = message;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.d = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_one_button_title_message_full_screen);
        View findViewById = bottomSheetDialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setText(title);
        View findViewById2 = bottomSheetDialog.findViewById(R.id.message_textView);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setText(message);
        View findViewById3 = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById3;
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6082a;
    }

    @NotNull
    public final String getMessage() {
        return this.c;
    }

    @NotNull
    public final String getTitle() {
        return this.b;
    }

    @NotNull
    public final Button getYesButton() {
        return this.e;
    }

    public final boolean isShowing() {
        return this.d.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.d.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.d.setCanceledOnTouchOutside(z);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(positiveBtn);
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6082a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.d.isShowing()) {
            return;
        }
        this.d.show();
    }
}
