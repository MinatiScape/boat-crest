package com.coveiot.android.theme;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogImageTitleAndMessageWatchFace {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6055a;
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
    @NotNull
    public final Button g;

    public BottomSheetDialogImageTitleAndMessageWatchFace(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6055a = context;
        this.b = title;
        this.c = message;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogTheme);
        this.d = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_img_title_msg_watchface_new);
        if (!TextUtils.isEmpty(title)) {
            int i = R.id.title;
            TextView textView = (TextView) bottomSheetDialog.findViewById(i);
            if (textView != null) {
                textView.setText(title);
            }
            TextView textView2 = (TextView) bottomSheetDialog.findViewById(i);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
        } else {
            TextView textView3 = (TextView) bottomSheetDialog.findViewById(R.id.title);
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        }
        if (!TextUtils.isEmpty(message)) {
            int i2 = R.id.message_textView;
            TextView textView4 = (TextView) bottomSheetDialog.findViewById(i2);
            if (textView4 != null) {
                textView4.setText(message);
            }
            TextView textView5 = (TextView) bottomSheetDialog.findViewById(i2);
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
        } else {
            TextView textView6 = (TextView) bottomSheetDialog.findViewById(R.id.message_textView);
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
        }
        View findViewById = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.Button");
        this.e = (Button) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.negative_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.doneBtn);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.g = (Button) findViewById3;
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getConfirmPhoneNumberDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6055a;
    }

    @NotNull
    public final Button getDoneButton() {
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

    public final void setCancelableOutside(boolean z) {
        this.d.setCanceledOnTouchOutside(z);
    }

    public final void setDoneButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setVisibility(8);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.g.setText(positiveBtn);
        this.g.setOnClickListener(listner);
    }

    public final void setNegativeButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setText(positiveBtn);
        this.f.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(positiveBtn);
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6055a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.d.show();
    }

    public /* synthetic */ BottomSheetDialogImageTitleAndMessageWatchFace(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "" : str, str2);
    }
}
