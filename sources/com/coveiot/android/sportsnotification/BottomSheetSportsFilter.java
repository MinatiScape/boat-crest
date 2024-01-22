package com.coveiot.android.sportsnotification;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class BottomSheetSportsFilter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5795a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final ImageView c;
    @NotNull
    public final ImageView d;
    @NotNull
    public final ImageView e;
    @NotNull
    public final ImageView f;
    @NotNull
    public final RelativeLayout g;
    @NotNull
    public final RelativeLayout h;
    @NotNull
    public final RelativeLayout i;
    @NotNull
    public final Button j;
    @NotNull
    public final Button k;

    public BottomSheetSportsFilter(@NotNull Context context, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.f5795a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.filter_bottom_sheet);
        View findViewById = bottomSheetDialog.findViewById(R.id.btn_apply);
        Intrinsics.checkNotNull(findViewById);
        Button button = (Button) findViewById;
        this.k = button;
        button.setOnClickListener(listner);
        View findViewById2 = bottomSheetDialog.findViewById(R.id.btn_reset);
        Intrinsics.checkNotNull(findViewById2);
        Button button2 = (Button) findViewById2;
        this.j = button2;
        button2.setOnClickListener(listner);
        View findViewById3 = bottomSheetDialog.findViewById(R.id.rl_test);
        Intrinsics.checkNotNull(findViewById3);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById3;
        this.i = relativeLayout;
        relativeLayout.setOnClickListener(listner);
        View findViewById4 = bottomSheetDialog.findViewById(R.id.rl_odi);
        Intrinsics.checkNotNull(findViewById4);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById4;
        this.h = relativeLayout2;
        relativeLayout2.setOnClickListener(listner);
        View findViewById5 = bottomSheetDialog.findViewById(R.id.rl_t20);
        Intrinsics.checkNotNull(findViewById5);
        RelativeLayout relativeLayout3 = (RelativeLayout) findViewById5;
        this.g = relativeLayout3;
        relativeLayout3.setOnClickListener(listner);
        View findViewById6 = bottomSheetDialog.findViewById(R.id.img_odi);
        Intrinsics.checkNotNull(findViewById6);
        ImageView imageView = (ImageView) findViewById6;
        this.c = imageView;
        imageView.setOnClickListener(listner);
        View findViewById7 = bottomSheetDialog.findViewById(R.id.img_t20);
        Intrinsics.checkNotNull(findViewById7);
        ImageView imageView2 = (ImageView) findViewById7;
        this.d = imageView2;
        imageView2.setOnClickListener(listner);
        View findViewById8 = bottomSheetDialog.findViewById(R.id.img_test);
        Intrinsics.checkNotNull(findViewById8);
        ImageView imageView3 = (ImageView) findViewById8;
        this.e = imageView3;
        imageView3.setOnClickListener(listner);
        View findViewById9 = bottomSheetDialog.findViewById(R.id.img_close);
        Intrinsics.checkNotNull(findViewById9);
        ImageView imageView4 = (ImageView) findViewById9;
        this.f = imageView4;
        imageView4.setOnClickListener(listner);
    }

    public final void dismiss() {
        if (this.b.isShowing()) {
            this.b.dismiss();
        }
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.b;
    }

    @NotNull
    public final Button getBtnApply() {
        return this.k;
    }

    @NotNull
    public final Button getBtnReset() {
        return this.j;
    }

    @NotNull
    public final Context getContext() {
        return this.f5795a;
    }

    @NotNull
    public final ImageView getImgClose() {
        return this.f;
    }

    @NotNull
    public final ImageView getImgOdi() {
        return this.c;
    }

    @NotNull
    public final ImageView getImgT20() {
        return this.d;
    }

    @NotNull
    public final ImageView getImgTest() {
        return this.e;
    }

    @NotNull
    public final RelativeLayout getRl_odi() {
        return this.h;
    }

    @NotNull
    public final RelativeLayout getRl_t20() {
        return this.g;
    }

    @NotNull
    public final RelativeLayout getRl_test() {
        return this.i;
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void show() {
        this.b.show();
    }
}
