package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class QRTrayMenuDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6085a;
    @NotNull
    public final Dialog b;
    @NotNull
    public final TextView c;
    @NotNull
    public final TextView d;
    @NotNull
    public final TextView e;
    @NotNull
    public final View f;

    public QRTrayMenuDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6085a = context;
        Dialog dialog = new Dialog(context, R.style.DialogTheme);
        this.b = dialog;
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.qr_tray_menu_dialog);
        Window window = dialog.getWindow();
        Intrinsics.checkNotNull(window);
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
            attributes.width = -2;
            attributes.height = -2;
            attributes.gravity = 8388693;
            window.setAttributes(attributes);
        }
        View findViewById = dialog.findViewById(R.id.view1);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.view.View");
        this.f = findViewById;
        View findViewById2 = dialog.findViewById(R.id.tvDeleteQRCode);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.c = (TextView) findViewById2;
        View findViewById3 = dialog.findViewById(R.id.tvEditQrCode);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        this.d = (TextView) findViewById3;
        View findViewById4 = dialog.findViewById(R.id.tvPushQRCodeToWatch);
        Intrinsics.checkNotNull(findViewById4);
        this.e = (TextView) findViewById4;
    }

    public final void dismiss() {
        if (this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f6085a;
    }

    @NotNull
    public final Dialog getQrTrayMenuDialog() {
        return this.b;
    }

    @NotNull
    public final TextView getTvDeleteQRCode() {
        return this.c;
    }

    @NotNull
    public final TextView getTvEditQrCode() {
        return this.d;
    }

    @NotNull
    public final TextView getTvPushQRCodeToWatch() {
        return this.e;
    }

    @NotNull
    public final View getView1() {
        return this.f;
    }

    public final void hidePushQButton() {
        this.e.setVisibility(8);
        this.f.setVisibility(8);
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void setDeleteButton(@NotNull View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c.setOnClickListener(listener);
    }

    public final void setEditQrCodeButton(@NotNull View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.d.setOnClickListener(listener);
    }

    public final void setPushQRButton(@NotNull View.OnClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.e.setOnClickListener(listener);
    }

    public final void show() {
        Context context = this.f6085a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.b.isShowing()) {
            return;
        }
        this.b.show();
    }
}
