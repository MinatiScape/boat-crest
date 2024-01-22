package com.coveiot.android.theme;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogImageTitleMessageWatchFace {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6060a;
    @NotNull
    public final Drawable b;
    @NotNull
    public final String c;
    @NotNull
    public final String d;
    @NotNull
    public final BottomSheetDialog e;
    @NotNull
    public final Button f;

    public BottomSheetDialogImageTitleMessageWatchFace(@NotNull Context context, @NotNull Drawable drawable, @NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.f6060a = context;
        this.b = drawable;
        this.c = title;
        this.d = message;
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogTheme);
        this.e = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.generic_dialog_img_title_msg_watchface);
        View findViewById = bottomSheetDialog.findViewById(R.id.img_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.ImageView");
        ((ImageView) findViewById).setImageDrawable(drawable);
        if (!TextUtils.isEmpty(title)) {
            int i = R.id.title;
            TextView textView = (TextView) bottomSheetDialog.findViewById(i);
            if (textView != null) {
                textView.setText(title);
            }
            TextView textView2 = (TextView) bottomSheetDialog.findViewById(i);
            if (textView2 != null) {
                textView2.setVisibility(0);
            }
        } else {
            TextView textView3 = (TextView) bottomSheetDialog.findViewById(R.id.title);
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
        }
        if (!TextUtils.isEmpty(message)) {
            int i2 = R.id.message_textView;
            TextView textView4 = (TextView) bottomSheetDialog.findViewById(i2);
            if (textView4 != null) {
                textView4.setText(message);
            }
            TextView textView5 = (TextView) bottomSheetDialog.findViewById(i2);
            if (textView5 != null) {
                textView5.setVisibility(0);
            }
        } else {
            TextView textView6 = (TextView) bottomSheetDialog.findViewById(R.id.message_textView);
            if (textView6 != null) {
                textView6.setVisibility(8);
            }
        }
        View findViewById2 = bottomSheetDialog.findViewById(R.id.positive_btn);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.Button");
        this.f = (Button) findViewById2;
    }

    public final void dismiss() {
        this.e.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getConfirmPhoneNumberDialog() {
        return this.e;
    }

    @NotNull
    public final Context getContext() {
        return this.f6060a;
    }

    @NotNull
    public final Drawable getDrawable() {
        return this.b;
    }

    @NotNull
    public final String getMessage() {
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
        Context context = this.f6060a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        this.e.show();
    }

    public /* synthetic */ BottomSheetDialogImageTitleMessageWatchFace(Context context, Drawable drawable, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, drawable, (i & 4) != 0 ? "" : str, str2);
    }
}
