package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public class BottomSheetDialogFilter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5417a;
    @NotNull
    public final String b;
    @NotNull
    public final BottomSheetDialog c;
    @NotNull
    public final TextView d;
    @NotNull
    public final TextView e;
    @NotNull
    public final CheckBox f;
    @NotNull
    public final CheckBox g;
    @NotNull
    public final CheckBox h;

    public BottomSheetDialogFilter(@NotNull Context context, @NotNull String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        this.f5417a = context;
        this.b = title;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.c = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.filter_popup);
        View findViewById = bottomSheetDialog.findViewById(R.id.apply_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.d = (TextView) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.cancel_text);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.e = (TextView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.by_distance);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.CheckBox");
        this.f = (CheckBox) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.by_opennow);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.CheckBox");
        this.h = (CheckBox) findViewById4;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.by_rating);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.CheckBox");
        this.g = (CheckBox) findViewById5;
        View findViewById6 = bottomSheetDialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById6).setText(title);
    }

    public static final void d(BottomSheetDialogFilter this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.h.setChecked(false);
            this$0.g.setChecked(true);
            this$0.f.setChecked(false);
        }
    }

    public static final void e(BottomSheetDialogFilter this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.h.setChecked(true);
            this$0.g.setChecked(false);
            this$0.f.setChecked(false);
        }
    }

    public static final void f(BottomSheetDialogFilter this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.h.setChecked(false);
            this$0.g.setChecked(false);
            this$0.f.setChecked(true);
        }
    }

    public final void dismiss() {
        this.c.dismiss();
    }

    @NotNull
    public final String fiterType() {
        String obj = this.g.getText().toString();
        if (this.f.isChecked()) {
            obj = this.f.getText().toString();
        } else if (this.h.isChecked()) {
            obj = this.h.getText().toString();
        } else if (this.g.isChecked()) {
            obj = this.g.getText().toString();
        }
        Intrinsics.checkNotNull(obj);
        return obj;
    }

    @NotNull
    public final CheckBox getByDistance() {
        return this.f;
    }

    @NotNull
    public final CheckBox getByOpenNow() {
        return this.h;
    }

    @NotNull
    public final CheckBox getByRating() {
        return this.g;
    }

    @NotNull
    public final BottomSheetDialog getConfirmPhoneNumberDialog() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f5417a;
    }

    @NotNull
    public final TextView getNoButton() {
        return this.e;
    }

    @NotNull
    public final String getTitle() {
        return this.b;
    }

    @NotNull
    public final TextView getYesButton() {
        return this.d;
    }

    public final void onCheckChange() {
        this.g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.utils.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BottomSheetDialogFilter.d(BottomSheetDialogFilter.this, compoundButton, z);
            }
        });
        this.h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.utils.c
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BottomSheetDialogFilter.e(BottomSheetDialogFilter.this, compoundButton, z);
            }
        });
        this.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.utils.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BottomSheetDialogFilter.f(BottomSheetDialogFilter.this, compoundButton, z);
            }
        });
    }

    public final void setNegativeButton(@NotNull String nevgativeBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(nevgativeBtn, "nevgativeBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setText(nevgativeBtn);
        this.e.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setText(positiveBtn);
        this.d.setOnClickListener(listner);
    }

    public final void show() {
        this.c.show();
    }
}
