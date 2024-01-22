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
public class BottomSheetDialogOneTitleMessageTwoButtons {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6069a;
    @NotNull
    public final Dialog b;
    @NotNull
    public final TextView c;
    @NotNull
    public final TextView d;
    @NotNull
    public final Button e;
    @NotNull
    public final Button f;

    public BottomSheetDialogOneTitleMessageTwoButtons(@NotNull Context context, @NotNull String header, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6069a = context;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_1_title_message_2_buttons_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById, "bottomSheetDialog.findViewById(R.id.tvHeader)");
        TextView textView = (TextView) findViewById;
        this.c = textView;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.tvInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "bottomSheetDialog.findViewById(R.id.tvInfo)");
        TextView textView2 = (TextView) findViewById2;
        this.d = textView2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.btnPositive);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "bottomSheetDialog.findViewById(R.id.btnPositive)");
        this.e = (Button) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.btnNegative);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "bottomSheetDialog.findViewById(R.id.btnNegative)");
        this.f = (Button) findViewById4;
        textView.setText(header);
        textView2.setText(message);
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f6069a;
    }

    public final void hidPositiveButton(boolean z) {
        if (z) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
        }
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
    }

    public final void setNegativeButton(@NotNull String text, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setText(text);
        this.f.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String text, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(text);
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6069a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.b.show();
    }
}
