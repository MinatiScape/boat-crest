package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogProfileGender {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6070a;
    @NotNull
    public final String b;
    @NotNull
    public final Dialog c;
    @NotNull
    public final TextView d;
    @NotNull
    public final Button e;
    @NotNull
    public final RadioGroup f;
    @NotNull
    public final RadioButton g;
    @NotNull
    public final RadioButton h;
    @NotNull
    public final RadioButton i;
    @NotNull
    public String j;

    public BottomSheetDialogProfileGender(@NotNull Context context, @NotNull String currentGender) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(currentGender, "currentGender");
        this.f6070a = context;
        this.b = currentGender;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.c = bottomSheetDialog;
        this.j = "";
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.profile_gender_popup);
        View findViewById = bottomSheetDialog.findViewById(R.id.radioGender);
        Intrinsics.checkNotNullExpressionValue(findViewById, "profileGenderDialog.findViewById(R.id.radioGender)");
        RadioGroup radioGroup = (RadioGroup) findViewById;
        this.f = radioGroup;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "profileGenderDialog.findViewById(R.id.tvHeader)");
        this.d = (TextView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.rbMale);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "profileGenderDialog.findViewById(R.id.rbMale)");
        RadioButton radioButton = (RadioButton) findViewById3;
        this.g = radioButton;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.rbFemale);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "profileGenderDialog.findViewById(R.id.rbFemale)");
        RadioButton radioButton2 = (RadioButton) findViewById4;
        this.h = radioButton2;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.rbOther);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "profileGenderDialog.findViewById(R.id.rbOther)");
        RadioButton radioButton3 = (RadioButton) findViewById5;
        this.i = radioButton3;
        View findViewById6 = bottomSheetDialog.findViewById(R.id.genderOkBtn);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "profileGenderDialog.findViewById(R.id.genderOkBtn)");
        this.e = (Button) findViewById6;
        this.j = currentGender;
        int hashCode = currentGender.hashCode();
        if (hashCode != 2358797) {
            if (hashCode != 75532016) {
                if (hashCode == 2070122316 && currentGender.equals("FEMALE")) {
                    radioButton2.setChecked(true);
                }
            } else if (currentGender.equals("OTHER")) {
                radioButton3.setChecked(true);
            }
        } else if (currentGender.equals("MALE")) {
            radioButton.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.theme.o
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                BottomSheetDialogProfileGender.b(BottomSheetDialogProfileGender.this, radioGroup2, i);
            }
        });
    }

    public static final void b(BottomSheetDialogProfileGender this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == R.id.rbMale) {
            this$0.j = "MALE";
        } else if (i == R.id.rbFemale) {
            this$0.j = "FEMALE";
        } else if (i == R.id.rbOther) {
            this$0.j = "OTHER";
        }
    }

    public final void dismiss() {
        this.c.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f6070a;
    }

    @NotNull
    public final String getCurrentGender() {
        return this.b;
    }

    @NotNull
    public final String getGender() {
        return this.j;
    }

    @NotNull
    public final Dialog getProfileGenderDialog() {
        return this.c;
    }

    @NotNull
    public final RadioGroup getRadioGenderGroup() {
        return this.f;
    }

    @NotNull
    public final RadioButton getRbFemale() {
        return this.h;
    }

    @NotNull
    public final RadioButton getRbMale() {
        return this.g;
    }

    @NotNull
    public final RadioButton getRbOther() {
        return this.i;
    }

    @NotNull
    public final Button getSetGender() {
        return this.e;
    }

    @NotNull
    public final TextView getTvHeader() {
        return this.d;
    }

    public final boolean isShowing() {
        return this.c.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.c.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.c.setCanceledOnTouchOutside(z);
    }

    public final void setGender(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.j = str;
    }

    public final void setNegativeButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6070a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.c.show();
    }
}
