package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogProfilePicOptions {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6072a;
    @NotNull
    public final Dialog b;
    @NotNull
    public final TextView c;
    @NotNull
    public final RadioGroup d;
    @NotNull
    public final RadioButton e;
    @NotNull
    public final RadioButton f;
    @NotNull
    public String g;
    public int h;

    public BottomSheetDialogProfilePicOptions(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6072a = context;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        this.g = "";
        this.h = -1;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.profile_pic_options_popup);
        View findViewById = bottomSheetDialog.findViewById(R.id.radioPicOptionsGroup);
        Intrinsics.checkNotNullExpressionValue(findViewById, "profilePicOptionsDialog.….id.radioPicOptionsGroup)");
        RadioGroup radioGroup = (RadioGroup) findViewById;
        this.d = radioGroup;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "profilePicOptionsDialog.…ndViewById(R.id.tvHeader)");
        this.c = (TextView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.rbUpload);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "profilePicOptionsDialog.…ndViewById(R.id.rbUpload)");
        this.e = (RadioButton) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.rbTake);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "profilePicOptionsDialog.findViewById(R.id.rbTake)");
        this.f = (RadioButton) findViewById4;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.theme.p
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                BottomSheetDialogProfilePicOptions.b(BottomSheetDialogProfilePicOptions.this, radioGroup2, i);
            }
        });
    }

    public static final void b(BottomSheetDialogProfilePicOptions this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == R.id.rbUpload) {
            this$0.h = 0;
        } else if (i == R.id.rbTake) {
            this$0.h = 1;
        }
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f6072a;
    }

    @NotNull
    public final String getGender() {
        return this.g;
    }

    @NotNull
    public final Dialog getProfilePicOptionsDialog() {
        return this.b;
    }

    @NotNull
    public final RadioGroup getRadioPicOptionsGroup() {
        return this.d;
    }

    @NotNull
    public final RadioButton getRbTake() {
        return this.f;
    }

    @NotNull
    public final RadioButton getRbUpload() {
        return this.e;
    }

    public final int getSelectedOption() {
        return this.h;
    }

    @NotNull
    public final TextView getTvHeader() {
        return this.c;
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

    public final void setGender(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.c.setOnClickListener(listner);
    }

    public final void setRadioOptionClick(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setOnClickListener(listner);
        this.f.setOnClickListener(listner);
    }

    public final void setSelectedOption(int i) {
        this.h = i;
    }

    public final void show() {
        Context context = this.f6072a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.b.show();
    }
}
