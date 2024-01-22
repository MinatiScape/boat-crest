package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogSuccessImageTitleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6073a;
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

    public BottomSheetDialogSuccessImageTitleMessage(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6073a = context;
        this.b = title;
        this.c = message;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.d = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_sucess_img_two_msg);
        View findViewById = dialog.findViewById(R.id.success_image);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        this.f = (ImageView) findViewById;
        TextView textView = (TextView) dialog.findViewById(R.id.title);
        if (textView != null) {
            textView.setText(title);
        }
        TextView textView2 = (TextView) dialog.findViewById(R.id.message_textView);
        if (textView2 != null) {
            textView2.setText(message);
        }
        View findViewById2 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById2;
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final Dialog getConfirmSuccessDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6073a;
    }

    @NotNull
    public final String getMessage() {
        return this.c;
    }

    @NotNull
    public final ImageView getSuccessImage() {
        return this.f;
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

    public final void setSuccessImageVisibility(boolean z) {
    }

    public final void show() {
        Context context = this.f6073a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.d.show();
    }

    public /* synthetic */ BottomSheetDialogSuccessImageTitleMessage(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "" : str, str2);
    }
}
