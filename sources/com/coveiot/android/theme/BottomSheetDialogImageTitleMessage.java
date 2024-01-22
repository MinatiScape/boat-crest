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
public class BottomSheetDialogImageTitleMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6056a;
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
    public final ImageView h;
    @NotNull
    public final ImageView i;
    @NotNull
    public final ImageView j;

    public BottomSheetDialogImageTitleMessage(@NotNull Context context, @NotNull Drawable drawable, @NotNull String title, @NotNull String message, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6056a = context;
        this.b = drawable;
        this.c = title;
        this.d = message;
        this.e = z;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.f = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(z ? R.layout.generic_dialog_img_two_msg : R.layout.generic_dialog_img_title_msg);
        View findViewById = dialog.findViewById(R.id.img_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) findViewById;
        this.h = imageView;
        imageView.setImageDrawable(drawable);
        View findViewById2 = dialog.findViewById(R.id.ivBigIcon);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView2 = (ImageView) findViewById2;
        this.j = imageView2;
        View findViewById3 = dialog.findViewById(R.id.ivCross);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
        this.i = (ImageView) findViewById3;
        imageView2.setImageDrawable(drawable);
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
        View findViewById4 = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
        this.g = (Button) findViewById4;
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
        return this.f6056a;
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
        return this.i;
    }

    @NotNull
    public final String getMessage() {
        return this.d;
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

    public final void setCancelableOutside(boolean z) {
        this.f.setCanceledOnTouchOutside(z);
    }

    public final void setCrossButton(@NotNull View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.i.setOnClickListener(listener);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.g.setText(positiveBtn);
        this.g.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6056a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.f.show();
    }

    public final void showBigIcon() {
        this.h.setVisibility(8);
        this.j.setVisibility(0);
    }

    public final void showCrossIcon() {
        this.i.setVisibility(0);
    }

    public /* synthetic */ BottomSheetDialogImageTitleMessage(Context context, Drawable drawable, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, drawable, (i & 4) != 0 ? "" : str, str2, z);
    }
}
