package com.coveiot.leaderboard.views;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.leaderboard.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public class AllBadgesDetailsDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7218a;
    @NotNull
    public final BottomSheetDialog b;
    @NotNull
    public final ImageView c;
    @NotNull
    public final ImageView d;
    @NotNull
    public final TextView e;
    @NotNull
    public final TextView f;

    public AllBadgesDetailsDialog(@NotNull Context context, @Nullable AllBadgesModel.DataBean.BadgesBean badgesBean) {
        String badgeDescription;
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7218a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.all_badge_details_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.iv_close);
        Intrinsics.checkNotNull(findViewById);
        ImageView imageView = (ImageView) findViewById;
        this.c = imageView;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.iv_badge);
        Intrinsics.checkNotNull(findViewById2);
        ImageView imageView2 = (ImageView) findViewById2;
        this.d = imageView2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.tv_badge);
        Intrinsics.checkNotNull(findViewById3);
        TextView textView = (TextView) findViewById3;
        this.e = textView;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.tv_badge_details);
        Intrinsics.checkNotNull(findViewById4);
        TextView textView2 = (TextView) findViewById4;
        this.f = textView2;
        Glide.with(context).m30load(badgesBean != null ? badgesBean.getBadgeImageUrl() : null).into(imageView2);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AllBadgesDetailsDialog.b(AllBadgesDetailsDialog.this, view);
            }
        });
        String str = "--";
        textView.setText((badgesBean == null || (r1 = badgesBean.getBadgeName()) == null) ? "--" : "--");
        if (badgesBean != null && (badgeDescription = badgesBean.getBadgeDescription()) != null) {
            str = badgeDescription;
        }
        textView2.setText(str);
    }

    public static final void b(AllBadgesDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final void dismiss() {
        this.b.dismiss();
    }

    @NotNull
    public final BottomSheetDialog getBottomSheetDialog() {
        return this.b;
    }

    @NotNull
    public final Context getContext() {
        return this.f7218a;
    }

    @NotNull
    public final ImageView getIvBadge() {
        return this.d;
    }

    @NotNull
    public final ImageView getIvClose() {
        return this.c;
    }

    @NotNull
    public final TextView getTvBadge() {
        return this.e;
    }

    @NotNull
    public final TextView getTvBadgeDetails() {
        return this.f;
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

    public final void show() {
        Context context = this.f7218a;
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
