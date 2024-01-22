package com.coveiot.android.theme;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.coveiot.covepreferences.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public class BottomSheetDialogWellnessCrew {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6080a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final Button c;
    @NotNull
    public final TextView d;
    @NotNull
    public final TextView e;
    @NotNull
    public TextView f;
    @Nullable
    public View.OnClickListener g;
    @Nullable
    public View.OnClickListener h;

    public BottomSheetDialogWellnessCrew(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6080a = context;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogTheme);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_wellness_crew);
        View findViewById = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.Button");
        this.c = (Button) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.negative_tv);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        this.d = (TextView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.message_textView);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        this.f = (TextView) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.title);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById4;
        this.e = textView;
        textView.setText(context.getString(SessionManager.getInstance(context).isShowIndusInd() ? R.string.acceptance_msg_health_buddies_data_sharing_indus_ind : R.string.you_are_consenting));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(context.getString(R.string.by_continuing));
        StringBuilder sb = new StringBuilder();
        sb.append(' ');
        int i = R.string.t_and_c;
        sb.append(context.getString(i));
        spannableStringBuilder.append((CharSequence) sb.toString());
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.coveiot.android.theme.BottomSheetDialogWellnessCrew$clickableSpanTC$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener hyperLinkAction = BottomSheetDialogWellnessCrew.this.getHyperLinkAction();
                if (hyperLinkAction != null) {
                    hyperLinkAction.onClick(view);
                }
            }
        }, spannableStringBuilder.length() - context.getString(i).length(), spannableStringBuilder.length(), 0);
        spannableStringBuilder.append((CharSequence) (' ' + context.getString(R.string.and)));
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#a7a7a7")), 36, spannableStringBuilder.length(), 0);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(' ');
        int i2 = R.string.privacy_policy;
        sb2.append(context.getString(i2));
        spannableStringBuilder.append((CharSequence) sb2.toString());
        spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.coveiot.android.theme.BottomSheetDialogWellnessCrew$clickableSpanPP$1
            @Override // android.text.style.ClickableSpan
            public void onClick(@NotNull View view) {
                Intrinsics.checkNotNullParameter(view, "view");
                View.OnClickListener hyperLinkActionPolicy = BottomSheetDialogWellnessCrew.this.getHyperLinkActionPolicy();
                if (hyperLinkActionPolicy != null) {
                    hyperLinkActionPolicy.onClick(view);
                }
            }
        }, spannableStringBuilder.length() - context.getString(i2).length(), spannableStringBuilder.length(), 0);
        this.f.setMovementMethod(LinkMovementMethod.getInstance());
        this.f.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getConfirmPhoneNumberDialog() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f6080a;
    }

    @Nullable
    public final View.OnClickListener getHyperLinkAction() {
        return this.g;
    }

    @Nullable
    public final View.OnClickListener getHyperLinkActionPolicy() {
        return this.h;
    }

    @NotNull
    public final TextView getNoButton() {
        return this.d;
    }

    @NotNull
    public final TextView getTermsAndConditions() {
        return this.f;
    }

    @NotNull
    public final TextView getTitle() {
        return this.e;
    }

    @NotNull
    public final Button getYesButton() {
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

    public final void setHyperLinkAction(@Nullable View.OnClickListener onClickListener) {
        this.g = onClickListener;
    }

    public final void setHyperLinkActionPolicy(@Nullable View.OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    public final void setNegativeButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.d.setText(positiveBtn);
        this.d.setOnClickListener(listner);
    }

    public final void setPositiveButton(@NotNull String positiveBtn, @NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(positiveBtn, "positiveBtn");
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.c.setText(positiveBtn);
        this.c.setOnClickListener(listner);
    }

    public final void setTermsAndConditions(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.f = textView;
    }

    public final void show() {
        Context context = this.f6080a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.b.show();
    }
}
