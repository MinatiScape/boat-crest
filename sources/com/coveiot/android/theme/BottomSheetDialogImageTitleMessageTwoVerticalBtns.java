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
public class BottomSheetDialogImageTitleMessageTwoVerticalBtns {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6059a;
    @NotNull
    public final Drawable b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final Dialog e;
    @NotNull
    public final Button f;
    @NotNull
    public final TextView g;
    @NotNull
    public final ImageView h;
    @NotNull
    public final ImageView i;
    @NotNull
    public final ImageView j;
    @NotNull
    public final ImageView k;

    public BottomSheetDialogImageTitleMessageTwoVerticalBtns(@NotNull Context context, @NotNull Drawable drawable, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6059a = context;
        this.b = drawable;
        this.c = title;
        this.d = message;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.e = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_img_title_msg_two_vertical_btns);
        View findViewById = dialog.findViewById(R.id.img_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById;
        this.h = imageView;
        imageView.setImageDrawable(drawable);
        View findViewById2 = dialog.findViewById(R.id.success_image);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView2 = (ImageView) findViewById2;
        this.i = imageView2;
        imageView2.setImageDrawable(drawable);
        View findViewById3 = dialog.findViewById(R.id.ivBigIcon);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView3 = (ImageView) findViewById3;
        this.j = imageView3;
        View findViewById4 = dialog.findViewById(R.id.ivCross);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        this.k = (ImageView) findViewById4;
        imageView3.setImageDrawable(drawable);
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
        if (message.equals(context.getResources().getString(R.string.unsubscribe_success))) {
            imageView2.setVisibility(0);
            imageView.setVisibility(4);
        } else {
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
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
        View findViewById5 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById5;
        View findViewById6 = dialog.findViewById(R.id.negative_tv);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        this.g = (TextView) findViewById6;
    }

    public final void dismiss() {
        this.e.dismiss();
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f6059a;
    }

    @NotNull
    public final Drawable getDrawable() {
        return this.b;
    }

    @NotNull
    public final ImageView getIvBigIcon() {
        return this.j;
    }

    @NotNull
    public final ImageView getIvCross() {
        return this.k;
    }

    @NotNull
    public final String getMessage() {
        return this.d;
    }

    @NotNull
    public final TextView getNoButton() {
        return this.g;
    }

    @NotNull
    public final ImageView getSuccessImage() {
        return this.i;
    }

    @NotNull
    public final String getTitle() {
        return this.c;
    }

    @NotNull
    public final ImageView getTitleImageView() {
        return this.h;
    }

    @NotNull
    public final Button getYesButton() {
        return this.f;
    }

    public final void hideNegativeButton() {
        this.g.setVisibility(8);
    }

    public final boolean isShowing() {
        return this.e.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.e.setCancelable(z);
    }

    public final void setCrossButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.k.setOnClickListener(listner);
    }

    public final void setNegativeButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.g.setText(positiveBtn);
        this.g.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setText(positiveBtn);
        this.f.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6059a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.e.show();
    }

    public final void showBigIcon() {
        this.h.setVisibility(8);
        this.j.setVisibility(0);
    }

    public final void showCrossIcon() {
        this.k.setVisibility(0);
    }

    public /* synthetic */ BottomSheetDialogImageTitleMessageTwoVerticalBtns(Context context, Drawable drawable, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, drawable, (i & 4) != 0 ? "" : str, str2);
    }
}
