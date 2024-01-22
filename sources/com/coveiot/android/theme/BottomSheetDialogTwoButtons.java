package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogTwoButtons {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6076a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final Dialog d;
    @NotNull
    public final Button e;
    @NotNull
    public final Button f;
    @NotNull
    public final ImageView g;

    public BottomSheetDialogTwoButtons(@NotNull Context context, @NotNull String title, @NotNull String message, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6076a = context;
        this.b = title;
        this.c = message;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.d = dialog;
        dialog.requestWindowFeature(1);
        if (!z) {
            i = R.layout.generic_dialog_two_buttons;
        } else {
            i = R.layout.generic_dialog_two_buttons_new;
        }
        dialog.setContentView(i);
        View findViewById = dialog.findViewById(R.id.message);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setText(title);
        if (Build.VERSION.SDK_INT >= 24) {
            textView.setText(Html.fromHtml(message, 63));
        } else {
            textView.setText(Html.fromHtml(message));
        }
        if (message.length() == 0) {
            textView.setVisibility(8);
        }
        View findViewById3 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById3;
        View findViewById4 = dialog.findViewById(R.id.negative_btn);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById4;
        View findViewById5 = dialog.findViewById(R.id.iv_close);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.ImageView");
        this.g = (ImageView) findViewById5;
    }

    public final void dismiss() {
        if (this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6076a;
    }

    @NotNull
    public final ImageView getCrossButton() {
        return this.g;
    }

    @NotNull
    public final String getMessage() {
        return this.c;
    }

    @NotNull
    public final Button getNoButton() {
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

    public final void setCrossButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.g.setOnClickListener(listner);
    }

    public final void setCrossButtonVisible() {
        this.g.setVisibility(0);
    }

    public final void setNegativeButton(@NotNull String nevgativeBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(nevgativeBtn, "nevgativeBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setText(nevgativeBtn);
        this.f.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(positiveBtn);
        this.e.setOnClickListener(listner);
    }

    public final void setPositiveButtonDarkColor() {
        this.e.setBackgroundResource(R.drawable.rounded_black_background_50dp);
    }

    public final void show() {
        Context context = this.f6076a;
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

    public /* synthetic */ BottomSheetDialogTwoButtons(Context context, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? false : z);
    }
}
