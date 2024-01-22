package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogDashboardFirmwareUpdated {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6053a;
    public final boolean b;
    @NotNull
    public final Dialog c;
    @NotNull
    public final TextView d;
    @NotNull
    public final Button e;
    @NotNull
    public final Button f;

    public BottomSheetDialogDashboardFirmwareUpdated(@NotNull Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6053a = context;
        this.b = z;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.c = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.dashboard_firmware_update_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById, "firmwareUpdateDialog.findViewById(R.id.tvHeader)");
        TextView textView = (TextView) findViewById;
        this.d = textView;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btnUpdate);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "firmwareUpdateDialog.findViewById(R.id.btnUpdate)");
        this.e = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.btnLater);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "firmwareUpdateDialog.findViewById(R.id.btnLater)");
        Button button = (Button) findViewById3;
        this.f = button;
        if (z) {
            textView.setText(context.getString(R.string.mandatory_firmware_update));
            button.setVisibility(8);
            return;
        }
        textView.setText(context.getString(R.string.firmware_update_available));
        button.setVisibility(0);
    }

    public final void dismiss() {
        this.c.dismiss();
    }

    @NotNull
    public final Button getBtnLater() {
        return this.f;
    }

    @NotNull
    public final Button getBtnUpdate() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f6053a;
    }

    @NotNull
    public final Dialog getFirmwareUpdateDialog() {
        return this.c;
    }

    @NotNull
    public final TextView getTvHeader() {
        return this.d;
    }

    public final boolean isShowing() {
        return this.c.isShowing();
    }

    public final boolean isUpdateMandatory() {
        return this.b;
    }

    public final void setCancelable(boolean z) {
        this.c.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.c.setCanceledOnTouchOutside(z);
    }

    public final void setNegativeButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6053a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.c.show();
    }
}
