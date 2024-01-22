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
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.coveiot.utils.utility.ShareScreen;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public class BuddiesRankDetailsDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7220a;
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

    public BuddiesRankDetailsDialog(@NotNull Context context, @NotNull TopRankModel.DataBean.TopRanksBean myRankModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(myRankModel, "myRankModel");
        this.f7220a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.my_rank_share_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.iv_close);
        Intrinsics.checkNotNull(findViewById);
        this.c = (ImageView) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.iv_ranker_profile);
        Intrinsics.checkNotNull(findViewById2);
        this.e = (ImageView) findViewById2;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.iv_rank);
        Intrinsics.checkNotNull(findViewById3);
        this.d = (ImageView) findViewById3;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.btnShare);
        Intrinsics.checkNotNull(findViewById4);
        Button button = (Button) findViewById4;
        this.f = button;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.tv_rank);
        Intrinsics.checkNotNull(findViewById5);
        this.g = (TextView) findViewById5;
        View findViewById6 = bottomSheetDialog.findViewById(R.id.tv_rank_date);
        Intrinsics.checkNotNull(findViewById6);
        TextView textView = (TextView) findViewById6;
        this.h = textView;
        View findViewById7 = bottomSheetDialog.findViewById(R.id.my_rank);
        Intrinsics.checkNotNull(findViewById7);
        this.i = (TextView) findViewById7;
        View findViewById8 = bottomSheetDialog.findViewById(R.id.tv_previous_rank);
        Intrinsics.checkNotNull(findViewById8);
        TextView textView2 = (TextView) findViewById8;
        this.j = textView2;
        View findViewById9 = bottomSheetDialog.findViewById(R.id.myName);
        Intrinsics.checkNotNull(findViewById9);
        TextView textView3 = (TextView) findViewById9;
        this.k = textView3;
        View findViewById10 = bottomSheetDialog.findViewById(R.id.mySteps);
        Intrinsics.checkNotNull(findViewById10);
        this.l = (TextView) findViewById10;
        View findViewById11 = bottomSheetDialog.findViewById(R.id.clMain);
        Intrinsics.checkNotNull(findViewById11);
        this.m = (ConstraintLayout) findViewById11;
        View findViewById12 = bottomSheetDialog.findViewById(R.id.iv_powered_cove);
        Intrinsics.checkNotNull(findViewById12);
        ImageView imageView = (ImageView) findViewById12;
        this.n = imageView;
        ThemesUtils.setPoweredByLogoIcon(context, imageView, true);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuddiesRankDetailsDialog.d(BuddiesRankDetailsDialog.this, view);
            }
        });
        if (myRankModel.getPreviousRank() != null && myRankModel.getRank() != null) {
            int intValue = myRankModel.getPreviousRank().intValue();
            Integer rank = myRankModel.getRank();
            Intrinsics.checkNotNullExpressionValue(rank, "myRankModel.rank");
            int intValue2 = intValue - rank.intValue();
            textView2.setText(String.valueOf(Math.abs(intValue2)));
            if (intValue2 > 0) {
                textView2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rank_up, 0, 0, 0);
            } else if (intValue2 == 0) {
                textView2.setVisibility(8);
            } else {
                textView2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_rank_down, 0, 0, 0);
            }
        } else {
            textView2.setVisibility(8);
        }
        GlideUtils.loadScaledImage(context, SessionManager.getInstance(context).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.leaderboard.views.BuddiesRankDetailsDialog.2
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Bitmap circleBitmap;
                Intrinsics.checkNotNullParameter(resource, "resource");
                if (BuddiesRankDetailsDialog.this.getIvRankerProfile() == null || BuddiesRankDetailsDialog.this.getIvRank() == null || (circleBitmap = AppUtils.getCircleBitmap(resource)) == null) {
                    return;
                }
                BuddiesRankDetailsDialog.this.getIvRankerProfile().setImageBitmap(circleBitmap);
                BuddiesRankDetailsDialog.this.getIvRank().setImageBitmap(circleBitmap);
            }
        });
        textView3.setText(SessionManager.getInstance(context).getUserDetails().getName());
        try {
            textView.setText(String.valueOf(LeaderboardUtils.formattedDate(myRankModel.getRankDate(), "dd MMM yyyy")));
        } catch (Exception unused) {
            this.h.setText("--");
        }
        if (myRankModel.getRank() != null) {
            Integer rank2 = myRankModel.getRank();
            if (rank2 != null && rank2.intValue() == 1) {
                TextView textView4 = this.g;
                StringBuilder sb = new StringBuilder();
                Integer rank3 = myRankModel.getRank();
                sb.append(rank3 != null ? String.valueOf(rank3) : null);
                sb.append("st ");
                sb.append(this.f7220a.getString(R.string.rank));
                textView4.setText(sb.toString());
            } else if (rank2 != null && rank2.intValue() == 2) {
                TextView textView5 = this.g;
                StringBuilder sb2 = new StringBuilder();
                Integer rank4 = myRankModel.getRank();
                sb2.append(rank4 != null ? String.valueOf(rank4) : null);
                sb2.append("nd ");
                sb2.append(this.f7220a.getString(R.string.rank));
                textView5.setText(sb2.toString());
            } else if (rank2 != null && rank2.intValue() == 3) {
                TextView textView6 = this.g;
                StringBuilder sb3 = new StringBuilder();
                Integer rank5 = myRankModel.getRank();
                sb3.append(rank5 != null ? String.valueOf(rank5) : null);
                sb3.append("rd ");
                sb3.append(this.f7220a.getString(R.string.rank));
                textView6.setText(sb3.toString());
            } else {
                TextView textView7 = this.g;
                StringBuilder sb4 = new StringBuilder();
                Integer rank6 = myRankModel.getRank();
                sb4.append(rank6 != null ? String.valueOf(rank6) : null);
                sb4.append("th ");
                sb4.append(this.f7220a.getString(R.string.rank));
                textView7.setText(sb4.toString());
            }
            TextView textView8 = this.i;
            Integer rank7 = myRankModel.getRank();
            textView8.setText(rank7 != null ? String.valueOf(rank7) : null);
        } else {
            TextView textView9 = this.g;
            textView9.setText("-- " + this.f7220a.getString(R.string.rank));
            this.i.setText("--");
        }
        TextView textView10 = this.l;
        String num = Integer.valueOf(myRankModel.getSteps()).toString();
        textView10.setText(num != null ? num : "--");
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuddiesRankDetailsDialog.e(BuddiesRankDetailsDialog.this, view);
            }
        });
    }

    public static final void d(final BuddiesRankDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.leaderboard.views.g
            @Override // java.lang.Runnable
            public final void run() {
                BuddiesRankDetailsDialog.f(BuddiesRankDetailsDialog.this);
            }
        }, 500L);
    }

    public static final void e(BuddiesRankDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void f(BuddiesRankDetailsDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.m, this$0.f7220a);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(clMain, context)");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.f7220a);
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
        return this.f7220a;
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
        Context context = this.f7220a;
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
