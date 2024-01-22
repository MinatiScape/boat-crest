package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonTwoMessages {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6068a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @NotNull
    public final Dialog d;
    @NotNull
    public final Button e;

    public BottomSheetDialogOneButtonTwoMessages(@NotNull Context context, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6068a = context;
        this.b = str;
        this.c = str2;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.d = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_one_button_two_messages);
        View findViewById = dialog.findViewById(R.id.tv_msg1);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        String str3 = this.b;
        if (str3 != null) {
            textView.setText(str3);
        }
        View findViewById2 = dialog.findViewById(R.id.tv_msg2);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById2;
        String str4 = this.c;
        if (str4 != null) {
            textView2.setText(str4);
        }
        View findViewById3 = dialog.findViewById(R.id.positive_btn);
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
        return this.f6068a;
    }

    @Nullable
    public final String getMessage1() {
        return this.b;
    }

    @Nullable
    public final String getMessage2() {
        return this.c;
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

    public final void setMessage1(@Nullable String str) {
        this.b = str;
    }

    public final void setMessage2(@Nullable String str) {
        this.c = str;
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(positiveBtn);
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6068a;
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

    public /* synthetic */ BottomSheetDialogOneButtonTwoMessages(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2);
    }
}
