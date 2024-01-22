package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonTitleIconMessage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6064a;
    @NotNull
    public final Dialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final ImageView d;
    @NotNull
    public final ConstraintLayout e;
    @NotNull
    public final ConstraintLayout f;
    @NotNull
    public final ConstraintLayout g;
    @NotNull
    public final Button h;
    @NotNull
    public final Button i;
    public boolean j;

    public BottomSheetDialogOneButtonTitleIconMessage(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6064a = context;
        Intrinsics.checkNotNull(context);
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.b = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.generic_dialog_one_button_title_icon_message);
        View findViewById = dialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.Button");
        this.c = (Button) findViewById;
        View findViewById2 = dialog.findViewById(R.id.btnNo);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.h = (Button) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.btnYes);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.Button");
        this.i = (Button) findViewById3;
        View findViewById4 = dialog.findViewById(R.id.ivClose);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.ImageView");
        this.d = (ImageView) findViewById4;
        View findViewById5 = dialog.findViewById(R.id.clComplete);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        this.f = (ConstraintLayout) findViewById5;
        View findViewById6 = dialog.findViewById(R.id.clClose);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        this.g = (ConstraintLayout) findViewById6;
        View findViewById7 = dialog.findViewById(R.id.clRunning);
        Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        this.e = (ConstraintLayout) findViewById7;
        b();
    }

    public static final void c(BottomSheetDialogOneButtonTitleIconMessage this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.e.getVisibility() == 0) {
            this$0.e.setVisibility(8);
            this$0.f.setVisibility(0);
            this$0.d.setVisibility(0);
        }
    }

    public final void b() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.coveiot.android.theme.n
            @Override // java.lang.Runnable
            public final void run() {
                BottomSheetDialogOneButtonTitleIconMessage.c(BottomSheetDialogOneButtonTitleIconMessage.this);
            }
        }, 4500L);
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final Dialog getConfirmPhoneNumberDialog() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f6064a;
    }

    @NotNull
    public final Button getYesButton() {
        return this.c;
    }

    public final boolean isDownload() {
        return this.j;
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

    public final void setCloseFunction(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setOnClickListener(listner);
    }

    public final void setDownload(boolean z) {
        this.j = z;
    }

    public final void setNOFunction(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.h.setOnClickListener(listner);
    }

    public final void setNoClick() {
        if (this.g.getVisibility() == 0) {
            this.g.setVisibility(8);
            this.f.setVisibility(0);
            this.d.setVisibility(0);
        }
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.c.setOnClickListener(listner);
    }

    public final void setYESFunction(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.i.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6064a;
        if (((context instanceof Activity) && ((Activity) context).isFinishing()) || this.b.isShowing()) {
            return;
        }
        this.b.show();
    }

    public final void turnOnCloseCheck() {
        if (this.f.getVisibility() == 0) {
            this.f.setVisibility(8);
            this.g.setVisibility(0);
            this.d.setVisibility(8);
        }
    }
}
