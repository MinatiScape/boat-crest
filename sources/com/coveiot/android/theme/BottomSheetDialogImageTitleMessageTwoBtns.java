package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogImageTitleMessageTwoBtns {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6057a;
    @NotNull
    public final Drawable b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    public final boolean e;
    @NotNull
    public final Dialog f;
    @NotNull
    public final Button g;
    @NotNull
    public final Button h;

    public BottomSheetDialogImageTitleMessageTwoBtns(@NotNull Context context, @NotNull Drawable drawable, @NotNull String title, @NotNull String message, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6057a = context;
        this.b = drawable;
        this.c = title;
        this.d = message;
        this.e = z;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.f = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(z ? R.layout.generic_dialog_img_two_msg_two_btns : R.layout.generic_dialog_img_title_msg_two_btns);
        View findViewById = dialog.findViewById(R.id.img_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ((ImageView) findViewById).setImageDrawable(drawable);
        if (!TextUtils.isEmpty(title)) {
            int i = R.id.title;
            TextView textView = (TextView) dialog.findViewById(i);
            if (textView != null) {
                textView.setText(title);
            }
            TextView textView2 = (TextView) dialog.findViewById(i);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
        } else {
            TextView textView3 = (TextView) dialog.findViewById(R.id.title);
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        }
        if (!TextUtils.isEmpty(message)) {
            int i2 = R.id.message_textView;
            TextView textView4 = (TextView) dialog.findViewById(i2);
            if (textView4 != null) {
                textView4.setText(message);
            }
            TextView textView5 = (TextView) dialog.findViewById(i2);
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
        } else {
            TextView textView6 = (TextView) dialog.findViewById(R.id.message_textView);
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
        }
        View findViewById2 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.g = (Button) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.negative_btn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.h = (Button) findViewById3;
    }

    public final void dismiss() {
        this.f.dismiss();
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.f;
    }

    @NotNull
    public final Context getContext() {
        return this.f6057a;
    }

    @NotNull
    public final Drawable getDrawable() {
        return this.b;
    }

    @NotNull
    public final String getMessage() {
        return this.d;
    }

    @NotNull
    public final Button getNoButton() {
        return this.h;
    }

    @NotNull
    public final String getTitle() {
        return this.c;
    }

    @NotNull
    public final Button getYesButton() {
        return this.g;
    }

    public final boolean isContainOnlyMessages() {
        return this.e;
    }

    public final boolean isShowing() {
        return this.f.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.f.setCancelable(z);
    }

    public final void setNegativeButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.h.setText(positiveBtn);
        this.h.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.g.setText(positiveBtn);
        this.g.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6057a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.f.show();
    }

    public /* synthetic */ BottomSheetDialogImageTitleMessageTwoBtns(Context context, Drawable drawable, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, drawable, (i & 4) != 0 ? "" : str, str2, z);
    }
}
