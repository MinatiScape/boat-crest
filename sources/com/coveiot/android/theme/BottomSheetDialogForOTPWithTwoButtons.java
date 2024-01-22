package com.coveiot.android.theme;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogForOTPWithTwoButtons {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6054a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final BottomSheetDialog d;
    @NotNull
    public final Button e;
    @NotNull
    public final Button f;

    public BottomSheetDialogForOTPWithTwoButtons(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6054a = context;
        this.b = title;
        this.c = message;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        this.d = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.otp_consent_dialog_two_buttons);
        View findViewById = bottomSheetDialog.findViewById(R.id.message_textView);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.title);
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
        View findViewById3 = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.negative_btn);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById4;
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f6054a;
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
    public final BottomSheetDialog getOtpConsentBottomSheetDialog() {
        return this.d;
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

    public final void show() {
        this.d.show();
    }

    public /* synthetic */ BottomSheetDialogForOTPWithTwoButtons(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? "" : str2);
    }
}
