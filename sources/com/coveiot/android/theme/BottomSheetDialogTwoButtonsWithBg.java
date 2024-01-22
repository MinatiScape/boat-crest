package com.coveiot.android.theme;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogTwoButtonsWithBg {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6079a;
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
    public final ImageView g;

    public BottomSheetDialogTwoButtonsWithBg(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6079a = context;
        this.b = title;
        this.c = message;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.d = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_two_buttons_title_msg);
        View findViewById = bottomSheetDialog.findViewById(R.id.message);
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
        View findViewById5 = bottomSheetDialog.findViewById(R.id.iv_close);
        Intrinsics.checkNotNull(findViewById5);
        this.g = (ImageView) findViewById5;
    }

    public final void dismiss() {
        if (this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    @NotNull
    public final BottomSheetDialog getConfirmDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6079a;
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

    public final void show() {
        Context context = this.f6079a;
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

    public /* synthetic */ BottomSheetDialogTwoButtonsWithBg(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i & 4) != 0 ? "" : str2);
    }
}
