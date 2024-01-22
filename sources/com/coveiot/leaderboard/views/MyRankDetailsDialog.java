package com.coveiot.leaderboard.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.coveiot.utils.utility.ShareScreen;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public class MyRankDetailsDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7221a;
    @NotNull
    public final BottomSheetDialog b;
    public Bitmap bitmap;
    @NotNull
    public final ImageView c;
    @NotNull
    public final ImageView d;
    @NotNull
    public final ImageView e;
    @NotNull
    public final Button f;
    @NotNull
    public final TextView g;
    @NotNull
    public final TextView h;
    @NotNull
    public final TextView i;
    @NotNull
    public final TextView j;
    @NotNull
    public final TextView k;
    @NotNull
    public final TextView l;
    @NotNull
    public final ConstraintLayout m;
    @NotNull
    public final ImageView n;

    /* JADX WARN: Can't wrap try/catch for region: R(18:1|(1:99)(1:5)|(3:7|(1:97)(1:11)|(14:13|(1:15)(1:96)|16|17|18|(1:94)(1:22)|23|24|(1:92)(1:28)|(6:30|(1:90)(1:34)|(3:38|(1:60)(1:42)|43)(2:61|(3:65|(1:71)(1:69)|70)(2:72|(3:76|(1:82)(1:80)|81)(3:83|(1:89)(1:87)|88)))|44|(1:48)|49)(1:91)|50|(1:56)|57|58))|98|16|17|18|(1:20)|94|23|24|(1:26)|92|(0)(0)|50|(3:52|54|56)|57|58) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0173, code lost:
        r8.h.setText("--");
     */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MyRankDetailsDialog(@org.jetbrains.annotations.NotNull android.content.Context r9, @org.jetbrains.annotations.NotNull com.coveiot.coveaccess.leaderboard.model.MyRankModel r10) {
        /*
            Method dump skipped, instructions count: 802
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.leaderboard.views.MyRankDetailsDialog.<init>(android.content.Context, com.coveiot.coveaccess.leaderboard.model.MyRankModel):void");
    }

    public static final void d(final MyRankDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.leaderboard.views.j
            @Override // java.lang.Runnable
            public final void run() {
                MyRankDetailsDialog.f(MyRankDetailsDialog.this);
            }
        }, 500L);
    }

    public static final void e(MyRankDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void f(MyRankDetailsDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.m, this$0.f7221a);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(clMain, context)");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.f7221a);
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.b;
    }

    @NotNull
    public final Button getBtnShare() {
        return this.f;
    }

    @NotNull
    public final ConstraintLayout getClMain() {
        return this.m;
    }

    @NotNull
    public final Context getContext() {
        return this.f7221a;
    }

    @NotNull
    public final ImageView getIvClose() {
        return this.c;
    }

    @NotNull
    public final ImageView getIvRank() {
        return this.d;
    }

    @NotNull
    public final ImageView getIvRankerProfile() {
        return this.e;
    }

    @NotNull
    public final ImageView getIv_powered_cove() {
        return this.n;
    }

    @NotNull
    public final TextView getMyName() {
        return this.k;
    }

    @NotNull
    public final TextView getMyRank() {
        return this.i;
    }

    @NotNull
    public final TextView getMySteps() {
        return this.l;
    }

    @NotNull
    public final TextView getTvPreviousRank() {
        return this.j;
    }

    @NotNull
    public final TextView getTvRank() {
        return this.g;
    }

    @NotNull
    public final TextView getTvRankDate() {
        return this.h;
    }

    public final boolean isShowing() {
        return this.b.isShowing();
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setCancelable(boolean z) {
        this.b.setCancelable(z);
    }

    public final void setCancelableOutside(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
    }

    public final void show() {
        Context context = this.f7221a;
        if (context instanceof Activity) {
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (this.b.isShowing()) {
            return;
        }
        this.b.show();
    }
}
