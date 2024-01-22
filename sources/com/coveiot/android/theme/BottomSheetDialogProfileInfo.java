package com.coveiot.android.theme;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogProfileInfo {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6071a;
    @NotNull
    public final String b;
    @NotNull
    public final String c;
    @NotNull
    public final Dialog d;
    @NotNull
    public final TextView e;

    public BottomSheetDialogProfileInfo(@NotNull Context context, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6071a = context;
        this.b = title;
        this.c = message;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.d = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.profile_info_popup);
        View findViewById = bottomSheetDialog.findViewById(R.id.tvHeader);
        Intrinsics.checkNotNullExpressionValue(findViewById, "profileInfoDialog.findViewById(R.id.tvHeader)");
        TextView textView = (TextView) findViewById;
        this.e = textView;
        textView.setText(title);
        TextView textView2 = (TextView) bottomSheetDialog.findViewById(R.id.tvInfo);
        if (textView2 == null) {
            return;
        }
        textView2.setText(message);
    }

    public final void dismiss() {
        this.d.dismiss();
    }

    @NotNull
    public final Context getContext() {
        return this.f6071a;
    }

    @NotNull
    public final String getMessage() {
        return this.c;
    }

    @NotNull
    public final Dialog getProfileInfoDialog() {
        return this.d;
    }

    @NotNull
    public final String getTitle() {
        return this.b;
    }

    @NotNull
    public final TextView getTvHeader() {
        return this.e;
    }

    public final boolean isShowing() {
        return this.d.isShowing();
    }

    public final void setCancelable(boolean z) {
        this.d.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.d.setCanceledOnTouchOutside(z);
    }

    public final void setPositiveButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        this.e.setOnClickListener(listner);
    }

    public final void show() {
        Context context = this.f6071a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.d.show();
    }

    public /* synthetic */ BottomSheetDialogProfileInfo(Context context, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? "" : str, str2);
    }
}
