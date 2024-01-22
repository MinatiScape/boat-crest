package com.coveiot.android.theme;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public class BottomSheetDialogTransparentFullScreen {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6074a;
    @NotNull
    public final Drawable b;
    @NotNull
    public final String c;
    @NotNull
    public final BottomSheetDialog d;

    public BottomSheetDialogTransparentFullScreen(@NotNull Context context, @NotNull Drawable drawable, @NotNull String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Intrinsics.checkNotNullParameter(title, "title");
        this.f6074a = context;
        this.b = drawable;
        this.c = title;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.SheetDialog);
        this.d = bottomSheetDialog;
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.setBackgroundDrawableResource(17170445);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_transparent_full_screen);
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
        bottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.coveiot.android.theme.q
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BottomSheetDialogTransparentFullScreen.b(BottomSheetDialogTransparentFullScreen.this, dialogInterface);
            }
        });
    }

    public static final void b(BottomSheetDialogTransparentFullScreen this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View findViewById = this$0.d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (findViewById != null) {
            final BottomSheetBehavior from = BottomSheetBehavior.from(findViewById);
            this$0.c(findViewById);
            from.setState(3);
            from.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { // from class: com.coveiot.android.theme.BottomSheetDialogTransparentFullScreen$1$1$1
                @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
                public void onSlide(@NonNull @NotNull View bottomSheet, float f) {
                    Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
                }

                @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
                public void onStateChanged(@NonNull @NotNull View bottomSheet, int i) {
                    Intrinsics.checkNotNullParameter(bottomSheet, "bottomSheet");
                    if (i == 1) {
                        from.setState(3);
                    }
                }
            });
        }
    }

    public final void c(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = -1;
        view.setLayoutParams(layoutParams);
    }

    public final void dismiss() {
        BottomSheetDialog bottomSheetDialog = this.d;
        if (bottomSheetDialog == null || !bottomSheetDialog.isShowing()) {
            return;
        }
        this.d.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getConfirmPhoneNumberDialog() {
        return this.d;
    }

    @NotNull
    public final Context getContext() {
        return this.f6074a;
    }

    @NotNull
    public final Drawable getDrawable() {
        return this.b;
    }

    @NotNull
    public final String getTitle() {
        return this.c;
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

    public final void setProgress(int i) {
        ProgressBar progressBar = (ProgressBar) this.d.findViewById(R.id.progress_update);
        if (progressBar == null) {
            return;
        }
        progressBar.setProgress(i);
    }

    public final void show() {
        BottomSheetDialog bottomSheetDialog = this.d;
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show();
        }
    }

    public final void showLoader(int i) {
        DottedCircleProgressBarCustom dottedCircleProgressBarCustom = (DottedCircleProgressBarCustom) this.d.findViewById(R.id.dotted_progressbar);
        if (dottedCircleProgressBarCustom == null) {
            return;
        }
        dottedCircleProgressBarCustom.setVisibility(i);
    }

    public final void showProgress(int i) {
        ProgressBar progressBar = (ProgressBar) this.d.findViewById(R.id.progress_update);
        if (progressBar == null) {
            return;
        }
        progressBar.setVisibility(i);
    }

    public /* synthetic */ BottomSheetDialogTransparentFullScreen(Context context, Drawable drawable, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, drawable, (i & 4) != 0 ? "" : str);
    }
}
