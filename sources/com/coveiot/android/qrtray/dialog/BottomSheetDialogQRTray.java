package com.coveiot.android.qrtray.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coveiot.android.qrtray.utils.ViewUtilsKt;
import com.coveiot.android.theme.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public class BottomSheetDialogQRTray {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5572a;
    @NotNull
    public final Dialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final Button d;
    @NotNull
    public final ImageView e;
    @NotNull
    public final TextView f;
    @NotNull
    public final TextView g;
    @NotNull
    public final TextView h;
    @NotNull
    public final TextView i;
    @NotNull
    public final TextView j;
    @NotNull
    public final ConstraintLayout k;

    public BottomSheetDialogQRTray(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5572a = context;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_qr_tray_maxed_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.btnPositive);
        Intrinsics.checkNotNullExpressionValue(findViewById, "bottomSheetDialog.findViewById(R.id.btnPositive)");
        this.c = (Button) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btnNegative);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "bottomSheetDialog.findViewById(R.id.btnNegative)");
        this.d = (Button) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.ivIcon);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "bottomSheetDialog.findViewById(R.id.ivIcon)");
        this.e = (ImageView) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "bottomSheetDialog.findViewById(R.id.tvHeader)");
        this.f = (TextView) findViewById4;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.tvInfo);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "bottomSheetDialog.findViewById(R.id.tvInfo)");
        this.g = (TextView) findViewById5;
        View findViewById6 = bottomSheetDialog.findViewById(R.id.tvQRName);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "bottomSheetDialog.findViewById(R.id.tvQRName)");
        this.h = (TextView) findViewById6;
        View findViewById7 = bottomSheetDialog.findViewById(R.id.tvQRTag);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "bottomSheetDialog.findViewById(R.id.tvQRTag)");
        this.i = (TextView) findViewById7;
        View findViewById8 = bottomSheetDialog.findViewById(R.id.tvNote);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "bottomSheetDialog.findViewById(R.id.tvNote)");
        this.j = (TextView) findViewById8;
        View findViewById9 = bottomSheetDialog.findViewById(R.id.clMiddle);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "bottomSheetDialog.findViewById(R.id.clMiddle)");
        this.k = (ConstraintLayout) findViewById9;
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f5572a;
    }

    public final void hideNote(boolean z) {
        ViewUtilsKt.visibleIf(this.j, z);
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

    public final void setNegativeButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.c.setOnClickListener(listner);
    }

    public final void setQRCodeData(boolean z, @NotNull String name, @NotNull String categoryName, @Nullable String str) {
        String string;
        String string2;
        String string3;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(categoryName, "categoryName");
        TextView textView = this.f;
        if (z) {
            string = this.f5572a.getString(R.string.maximum_qr_code_limit_exceeded_on_app);
        } else {
            string = this.f5572a.getString(R.string.you_have_maxed_the_limit_on_watch);
        }
        textView.setText(string);
        TextView textView2 = this.g;
        if (z) {
            string2 = this.f5572a.getString(R.string.please_delete_an_existing_qr_code_to_add_a_new_one);
        } else {
            string2 = this.f5572a.getString(R.string.this_qr_code_will_replace_the_oldest_one_below);
        }
        textView2.setText(string2);
        ViewUtilsKt.goneIF(this.k, z);
        ViewUtilsKt.goneIF(this.d, z);
        Button button = this.c;
        if (z) {
            string3 = this.f5572a.getString(R.string.okay);
        } else {
            string3 = this.f5572a.getString(R.string.proceed);
        }
        button.setText(string3);
        this.h.setText(name);
        this.i.setText(categoryName);
        if (str != null) {
            RequestBuilder<Drawable> m30load = Glide.with(this.f5572a).m30load(str);
            int i = R.drawable.rounded_black_grey_border_background_24dp;
            m30load.error(i).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(i).into(this.e);
        }
    }

    public final void setUnAppliedCheckUI() {
        this.g.setText(this.f5572a.getString(R.string.you_have_maxed_the_limit_on_watch));
        this.f.setText(this.f5572a.getString(R.string.un_applied_check_info));
    }

    public final void show() {
        Context context = this.f5572a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.b.show();
    }
}
