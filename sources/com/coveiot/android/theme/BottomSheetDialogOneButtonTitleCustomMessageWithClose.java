package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonTitleCustomMessageWithClose {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6062a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final Dialog d;
    @NotNull
    public final Button e;
    @NotNull
    public final ImageView f;

    public BottomSheetDialogOneButtonTitleCustomMessageWithClose(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6062a = context;
        this.b = title;
        this.c = message;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.d = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_one_button_title_message_with_close);
        View findViewById = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById;
        View findViewById2 = dialog.findViewById(R.id.close_icon);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById2;
        this.f = imageView;
        imageView.setVisibility(8);
        View findViewById3 = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById3).setText(title);
        View findViewById4 = dialog.findViewById(R.id.message_textView);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById4).setText(message);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BottomSheetDialogOneButtonTitleCustomMessageWithClose.b(BottomSheetDialogOneButtonTitleCustomMessageWithClose.this, view);
            }
        });
    }

    public static final void b(BottomSheetDialogOneButtonTitleCustomMessageWithClose this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final ImageView getCloseIcon() {
        return this.f;
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6062a;
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
        Context context = this.f6062a;
        if (((context instanceof Activity) && ((Activity) context).isFinishing()) || this.d.isShowing()) {
            return;
        }
        this.d.show();
    }
}
