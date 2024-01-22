package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonTitleCustomRedMessageWithClose {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6063a;
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
    @Nullable
    public View.OnClickListener g;

    public BottomSheetDialogOneButtonTitleCustomRedMessageWithClose(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6063a = context;
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
        this.f = (ImageView) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById3).setText(title);
        View findViewById4 = dialog.findViewById(R.id.message_textView);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById4;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("The watchface has been successfully created! You can find and view it under the %s section.", Arrays.copyOf(new Object[]{"My styles"}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        SpannableString spannableString = new SpannableString(format);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#f14950")), StringsKt__StringsKt.indexOf$default((CharSequence) format, "My styles", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "My styles", 0, false, 6, (Object) null) + 9, 33);
        spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.theme.BottomSheetDialogOneButtonTitleCustomRedMessageWithClose$clickableSpan$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener hyperLinkAction = BottomSheetDialogOneButtonTitleCustomRedMessageWithClose.this.getHyperLinkAction();
                if (hyperLinkAction != null) {
                    hyperLinkAction.onClick(view);
                }
            }
        }, StringsKt__StringsKt.indexOf$default((CharSequence) format, "My styles", 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) format, "My styles", 0, false, 6, (Object) null) + 9, 33);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
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
        return this.f6063a;
    }

    @Nullable
    public final View.OnClickListener getHyperLinkAction() {
        return this.g;
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

    public final void setHyperLinkAction(@Nullable View.OnClickListener onClickListener) {
        this.g = onClickListener;
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(positiveBtn);
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6063a;
        if (((context instanceof Activity) && ((Activity) context).isFinishing()) || this.d.isShowing()) {
            return;
        }
        this.d.show();
    }
}
