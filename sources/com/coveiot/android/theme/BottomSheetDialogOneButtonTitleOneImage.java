package com.coveiot.android.theme;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogOneButtonTitleOneImage {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6067a;
    @NotNull
    public final Drawable b;
    @NotNull
    public final String c;
    @NotNull
    public final Drawable d;
    @NotNull
    public final BottomSheetDialog e;
    @NotNull
    public final Button f;

    public BottomSheetDialogOneButtonTitleOneImage(@NotNull Context context, @NotNull Drawable backgroundImage, @NotNull String title, @NotNull Drawable image) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(image, "image");
        this.f6067a = context;
        this.b = backgroundImage;
        this.c = title;
        this.d = image;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.e = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_one_button_one_title_one_image);
        View findViewById = bottomSheetDialog.findViewById(R.id.clMain);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        ((ConstraintLayout) findViewById).setBackground(backgroundImage);
        View findViewById2 = bottomSheetDialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById2).setText(title);
        View findViewById3 = bottomSheetDialog.findViewById(R.id.ivImage);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.ImageView");
        ((ImageView) findViewById3).setImageDrawable(image);
        View findViewById4 = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById4;
    }

    public final void dismiss() {
        this.e.dismiss();
    }

    @NotNull
    public final Drawable getBackgroundImage() {
        return this.b;
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f6067a;
    }

    @NotNull
    public final Drawable getImage() {
        return this.d;
    }

    @NotNull
    public final String getTitle() {
        return this.c;
    }

    @NotNull
    public final Button getYesButton() {
        return this.f;
    }

    public final boolean isShowing() {
        return this.e.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.e.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.e.setCanceledOnTouchOutside(z);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f.setText(positiveBtn);
        this.f.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6067a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.e.isShowing()) {
            return;
        }
        this.e.show();
    }
}
