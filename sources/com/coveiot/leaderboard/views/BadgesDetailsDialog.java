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
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.utils.utility.ShareScreen;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public class BadgesDetailsDialog {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7219a;
    @NotNull
    public final BottomSheetDialog b;
    public Bitmap bitmap;
    @NotNull
    public final ImageView c;
    @NotNull
    public final ImageView d;
    @NotNull
    public final Button e;
    @NotNull
    public final TextView f;
    @NotNull
    public final TextView g;
    @NotNull
    public final TextView h;
    @NotNull
    public final TextView i;
    @NotNull
    public final TextView j;
    @NotNull
    public final ConstraintLayout k;
    @NotNull
    public final ImageView l;

    public BadgesDetailsDialog(@NotNull Context context, @Nullable MyBadgesModel.DataBean.BadgesBean badgesBean) {
        String badgeName;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean2;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean3;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean4;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean5;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean6;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean7;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean8;
        MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean badgeLevelsBean9;
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7219a = context;
        Intrinsics.checkNotNull(context);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context, R.style.DialogThemeBottomSheet);
        this.b = bottomSheetDialog;
        bottomSheetDialog.requestWindowFeature(1);
        bottomSheetDialog.setContentView(R.layout.badge_details_dialog);
        View findViewById = bottomSheetDialog.findViewById(R.id.iv_close);
        Intrinsics.checkNotNull(findViewById);
        this.c = (ImageView) findViewById;
        View findViewById2 = bottomSheetDialog.findViewById(R.id.iv_badge);
        Intrinsics.checkNotNull(findViewById2);
        ImageView imageView = (ImageView) findViewById2;
        this.d = imageView;
        View findViewById3 = bottomSheetDialog.findViewById(R.id.btnShare);
        Intrinsics.checkNotNull(findViewById3);
        Button button = (Button) findViewById3;
        this.e = button;
        View findViewById4 = bottomSheetDialog.findViewById(R.id.tv_badge);
        Intrinsics.checkNotNull(findViewById4);
        this.f = (TextView) findViewById4;
        View findViewById5 = bottomSheetDialog.findViewById(R.id.tv_badge_date);
        Intrinsics.checkNotNull(findViewById5);
        this.g = (TextView) findViewById5;
        View findViewById6 = bottomSheetDialog.findViewById(R.id.tv_badge_details);
        Intrinsics.checkNotNull(findViewById6);
        this.h = (TextView) findViewById6;
        View findViewById7 = bottomSheetDialog.findViewById(R.id.tv_badge_info);
        Intrinsics.checkNotNull(findViewById7);
        this.i = (TextView) findViewById7;
        View findViewById8 = bottomSheetDialog.findViewById(R.id.tv_badge_info2);
        Intrinsics.checkNotNull(findViewById8);
        this.j = (TextView) findViewById8;
        View findViewById9 = bottomSheetDialog.findViewById(R.id.clMain);
        Intrinsics.checkNotNull(findViewById9);
        this.k = (ConstraintLayout) findViewById9;
        View findViewById10 = bottomSheetDialog.findViewById(R.id.iv_powered_cove);
        Intrinsics.checkNotNull(findViewById10);
        ImageView imageView2 = (ImageView) findViewById10;
        this.l = imageView2;
        ThemesUtils.setPoweredByLogoIcon(context, imageView2, true);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BadgesDetailsDialog.d(BadgesDetailsDialog.this, view);
            }
        });
        String str = null;
        if (badgesBean != null && badgesBean.getBadgeLevels() != null && badgesBean.getBadgeLevels().size() > 0) {
            RequestManager with = Glide.with(context);
            List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels = badgesBean.getBadgeLevels();
            with.m30load((badgeLevels == null || (badgeLevelsBean9 = badgeLevels.get(0)) == null) ? null : badgeLevelsBean9.getLevelImageUrl()).into(imageView);
        }
        String str2 = "--";
        if (badgesBean != null && badgesBean.getBadgeLevels() != null && badgesBean.getBadgeLevels().size() > 1) {
            Iterator<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> it = badgesBean.getBadgeLevels().iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int i2 = i + 1;
                if (it.next().getObtainedDate() == null) {
                    try {
                        TextView textView = this.g;
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.f7219a.getString(R.string.date));
                        sb.append(": ");
                        List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels2 = badgesBean.getBadgeLevels();
                        String substring = String.valueOf((badgeLevels2 == null || (badgeLevelsBean8 = badgeLevels2.get(i + (-1))) == null) ? str : badgeLevelsBean8.getObtainedDate()).substring(0, 10);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb.append(LeaderboardUtils.formattedDate(substring, "dd MMM yyyy"));
                        textView.setText(sb.toString());
                        if (badgesBean.getCategoryId().equals("SPECIAL")) {
                            TextView textView2 = this.h;
                            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                            Locale locale = Locale.ENGLISH;
                            String string = this.f7219a.getString(R.string.special_badge_earned);
                            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.special_badge_earned)");
                            Object[] objArr = new Object[1];
                            List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels3 = badgesBean.getBadgeLevels();
                            String levelName = (badgeLevels3 == null || (badgeLevelsBean7 = badgeLevels3.get(i + (-1))) == null) ? str : badgeLevelsBean7.getLevelName();
                            objArr[0] = levelName == null ? "--" : levelName;
                            String format = String.format(locale, string, Arrays.copyOf(objArr, 1));
                            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                            textView2.setText(format);
                        } else if (m.equals$default(badgesBean.getCategoryId(), "DAILY", false, 2, str)) {
                            List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels4 = badgesBean.getBadgeLevels();
                            if ((badgeLevels4 == null || (badgeLevelsBean6 = badgeLevels4.get(i + (-1))) == null || badgeLevelsBean6.getUserCriteria() != 1) ? false : true) {
                                TextView textView3 = this.h;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(this.f7219a.getString(R.string.earned));
                                sb2.append(' ');
                                List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels5 = badgesBean.getBadgeLevels();
                                sb2.append((badgeLevels5 == null || (badgeLevelsBean5 = badgeLevels5.get(i + (-1))) == null) ? str : Integer.valueOf(badgeLevelsBean5.getUserCriteria()));
                                sb2.append(' ');
                                sb2.append(this.f7219a.getString(R.string.time_));
                                textView3.setText(sb2.toString());
                            } else {
                                TextView textView4 = this.h;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(this.f7219a.getString(R.string.earned));
                                sb3.append(' ');
                                List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels6 = badgesBean.getBadgeLevels();
                                sb3.append((badgeLevels6 == null || (badgeLevelsBean4 = badgeLevels6.get(i + (-1))) == null) ? str : Integer.valueOf(badgeLevelsBean4.getUserCriteria()));
                                sb3.append(' ');
                                sb3.append(this.f7219a.getString(R.string.times));
                                textView4.setText(sb3.toString());
                            }
                        }
                    } catch (Exception unused) {
                        this.g.setText("--");
                        this.h.setText("--");
                    }
                    TextView textView5 = this.i;
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    Locale locale2 = Locale.ENGLISH;
                    String string2 = this.f7219a.getString(R.string.level_up_and_unlock_by);
                    Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.level_up_and_unlock_by)");
                    Object[] objArr2 = new Object[1];
                    List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels7 = badgesBean.getBadgeLevels();
                    if (badgeLevels7 != null && (badgeLevelsBean3 = badgeLevels7.get(i)) != null) {
                        str = badgeLevelsBean3.getLevelName();
                    }
                    objArr2[0] = str == null ? "--" : str;
                    String format2 = String.format(locale2, string2, Arrays.copyOf(objArr2, 1));
                    Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
                    textView5.setText(format2);
                    TextView textView6 = this.j;
                    List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels8 = badgesBean.getBadgeLevels();
                    textView6.setText((badgeLevels8 == null || (badgeLevelsBean2 = badgeLevels8.get(i)) == null || (r3 = badgeLevelsBean2.getLevelDescription()) == null) ? "--" : "--");
                    this.i.setTextColor(this.f7219a.getColor(R.color.color_ccd9fc));
                    this.j.setTextColor(this.f7219a.getColor(R.color.main_text_color));
                } else {
                    try {
                        TextView textView7 = this.g;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(this.f7219a.getString(R.string.date));
                        sb4.append(": ");
                        List<MyBadgesModel.DataBean.BadgesBean.BadgeLevelsBean> badgeLevels9 = badgesBean.getBadgeLevels();
                        String substring2 = String.valueOf((badgeLevels9 == null || (badgeLevelsBean = badgeLevels9.get(i)) == null) ? null : badgeLevelsBean.getObtainedDate()).substring(0, 10);
                        Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                        sb4.append(LeaderboardUtils.formattedDate(substring2, "dd MMM yyyy"));
                        textView7.setText(sb4.toString());
                    } catch (Exception unused2) {
                        this.g.setText("--");
                    }
                    TextView textView8 = this.i;
                    Context context2 = this.f7219a;
                    int i3 = R.color.color_03c28a;
                    textView8.setTextColor(context2.getColor(i3));
                    this.j.setTextColor(this.f7219a.getColor(i3));
                    this.i.setText(this.f7219a.getString(R.string.ultimate_level_acheived));
                    this.j.setText(this.f7219a.getString(R.string.incredible_achievement));
                    i = i2;
                    str = null;
                }
            }
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BadgesDetailsDialog.e(BadgesDetailsDialog.this, view);
            }
        });
        TextView textView9 = this.f;
        if (badgesBean != null && (badgeName = badgesBean.getBadgeName()) != null) {
            str2 = badgeName;
        }
        textView9.setText(str2);
    }

    public static final void d(final BadgesDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e.setVisibility(8);
        this$0.c.setVisibility(8);
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.leaderboard.views.d
            @Override // java.lang.Runnable
            public final void run() {
                BadgesDetailsDialog.f(BadgesDetailsDialog.this);
            }
        }, 500L);
    }

    public static final void e(BadgesDetailsDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void f(BadgesDetailsDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bitmap saveScreen = ShareScreen.saveScreen(this$0.k, this$0.f7219a);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(clMain, context)");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0.f7219a);
        this$0.e.setVisibility(0);
        this$0.c.setVisibility(0);
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
        return this.e;
    }

    @NotNull
    public final ConstraintLayout getClMain() {
        return this.k;
    }

    @NotNull
    public final Context getContext() {
        return this.f7219a;
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
    public final ImageView getIv_powered_cove() {
        return this.l;
    }

    @NotNull
    public final TextView getTvBadge() {
        return this.f;
    }

    @NotNull
    public final TextView getTvBadgeDate() {
        return this.g;
    }

    @NotNull
    public final TextView getTvBadgeDetails() {
        return this.h;
    }

    @NotNull
    public final TextView getTvBadgeInfo() {
        return this.i;
    }

    @NotNull
    public final TextView getTvBadgeInfo2() {
        return this.j;
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
        Context context = this.f7219a;
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
