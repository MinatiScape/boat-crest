package com.coveiot.leaderboard.views.activities;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.ActivityStepsBadgeScreenBinding;
import com.coveiot.leaderboard.utils.LeaderboardConstants;
import com.coveiot.leaderboard.utils.ShareData;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityStepsBadgeScreen extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public MyBadgesModel.DataBean.BadgesBean data;
    public ActivityStepsBadgeScreenBinding p;

    public static final void s(ActivityStepsBadgeScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void t(ActivityStepsBadgeScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ShareData shareData = new ShareData();
        shareData.setData(this$0.getData());
        if (this$0.getData().getBadgeLevels() == null || this$0.getData().getBadgeLevels().size() <= 0) {
            return;
        }
        LeaderBoardNavigator.navigateToRankShareScreen(this$0, shareData, this$0.getResources().getString(R.string.badge_level));
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final MyBadgesModel.DataBean.BadgesBean getData() {
        MyBadgesModel.DataBean.BadgesBean badgesBean = this.data;
        if (badgesBean != null) {
            return badgesBean;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityStepsBadgeScreenBinding inflate = ActivityStepsBadgeScreenBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().getExtras() != null) {
            Serializable serializableExtra = getIntent().getSerializableExtra(LeaderboardConstants.STEPS_BADGE);
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.coveaccess.leaderboard.model.MyBadgesModel.DataBean.BadgesBean");
            setData((MyBadgesModel.DataBean.BadgesBean) serializableExtra);
        }
        ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding2 = this.p;
        if (activityStepsBadgeScreenBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityStepsBadgeScreenBinding2 = null;
        }
        activityStepsBadgeScreenBinding2.description.setText(getData().getBadgeDescription());
        if (getData().getBadgeLevels() != null && getData().getBadgeLevels().size() > 0) {
            ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding3 = this.p;
            if (activityStepsBadgeScreenBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityStepsBadgeScreenBinding3 = null;
            }
            activityStepsBadgeScreenBinding3.badgeName.setText(getData().getBadgeName());
            RequestBuilder<Drawable> m30load = Glide.with((FragmentActivity) this).m30load(getData().getBadgeLevels().get(0).getLevelImageUrl());
            ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding4 = this.p;
            if (activityStepsBadgeScreenBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityStepsBadgeScreenBinding4 = null;
            }
            m30load.into(activityStepsBadgeScreenBinding4.badgeIv);
            if (getData().getBadgeLevels().get(0).getUserCriteria() == 1) {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding5 = this.p;
                if (activityStepsBadgeScreenBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding5 = null;
                }
                activityStepsBadgeScreenBinding5.earnedBadge.setText(getString(R.string.earned_once));
            } else if (getData().getBadgeLevels().get(0).getUserCriteria() == 2) {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding6 = this.p;
                if (activityStepsBadgeScreenBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding6 = null;
                }
                activityStepsBadgeScreenBinding6.earnedBadge.setText(getString(R.string.earned_twice));
            } else {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding7 = this.p;
                if (activityStepsBadgeScreenBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding7 = null;
                }
                activityStepsBadgeScreenBinding7.earnedBadge.setText(getString(R.string.earned) + ' ' + getData().getBadgeLevels().get(0).getUserCriteria() + ' ' + getString(R.string.times));
            }
            if (kotlin.text.m.equals(getData().getBadgeLevels().get(0).getLevelName(), LeaderboardConstants.GOLD, true)) {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding8 = this.p;
                if (activityStepsBadgeScreenBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding8 = null;
                }
                activityStepsBadgeScreenBinding8.unLock.setVisibility(8);
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding9 = this.p;
                if (activityStepsBadgeScreenBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding9 = null;
                }
                activityStepsBadgeScreenBinding9.earnedBadge.setTextColor(Color.parseColor("#ffffff"));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding10 = this.p;
                if (activityStepsBadgeScreenBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding10 = null;
                }
                activityStepsBadgeScreenBinding10.unLock.setTextColor(Color.parseColor("#ffffff"));
            } else if (kotlin.text.m.equals(getData().getBadgeLevels().get(0).getLevelName(), LeaderboardConstants.BRONZE, true)) {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding11 = this.p;
                if (activityStepsBadgeScreenBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding11 = null;
                }
                activityStepsBadgeScreenBinding11.unLock.setText(getString(R.string.unlock_silver) + ' ' + getData().getBadgeLevels().get(0).getNextLevelCriteria() + ' ' + getString(R.string.times));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding12 = this.p;
                if (activityStepsBadgeScreenBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding12 = null;
                }
                activityStepsBadgeScreenBinding12.earnedBadge.setTextColor(Color.parseColor("#ffffff"));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding13 = this.p;
                if (activityStepsBadgeScreenBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding13 = null;
                }
                activityStepsBadgeScreenBinding13.unLock.setTextColor(Color.parseColor("#ffffff"));
            } else if (kotlin.text.m.equals(getData().getBadgeLevels().get(0).getLevelName(), LeaderboardConstants.SILVER, true)) {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding14 = this.p;
                if (activityStepsBadgeScreenBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding14 = null;
                }
                activityStepsBadgeScreenBinding14.earnedBadge.setTextColor(Color.parseColor("#ffffff"));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding15 = this.p;
                if (activityStepsBadgeScreenBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding15 = null;
                }
                activityStepsBadgeScreenBinding15.unLock.setTextColor(Color.parseColor("#ffffff"));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding16 = this.p;
                if (activityStepsBadgeScreenBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding16 = null;
                }
                activityStepsBadgeScreenBinding16.unLock.setText(getString(R.string.unlock_gold) + ' ' + getData().getBadgeLevels().get(0).getNextLevelCriteria() + ' ' + getString(R.string.times));
            } else {
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding17 = this.p;
                if (activityStepsBadgeScreenBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding17 = null;
                }
                activityStepsBadgeScreenBinding17.rootLayout.setBackgroundColor(getResources().getColor(R.color.background_color_dark));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding18 = this.p;
                if (activityStepsBadgeScreenBinding18 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding18 = null;
                }
                TextView textView = activityStepsBadgeScreenBinding18.badgeName;
                Resources resources = getResources();
                int i = R.color.main_text_color;
                textView.setTextColor(resources.getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding19 = this.p;
                if (activityStepsBadgeScreenBinding19 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding19 = null;
                }
                activityStepsBadgeScreenBinding19.unLock.setTextColor(getResources().getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding20 = this.p;
                if (activityStepsBadgeScreenBinding20 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding20 = null;
                }
                activityStepsBadgeScreenBinding20.earnedBadge.setTextColor(getResources().getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding21 = this.p;
                if (activityStepsBadgeScreenBinding21 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding21 = null;
                }
                activityStepsBadgeScreenBinding21.description.setTextColor(getResources().getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding22 = this.p;
                if (activityStepsBadgeScreenBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding22 = null;
                }
                activityStepsBadgeScreenBinding22.earnedBadge.setTextColor(getResources().getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding23 = this.p;
                if (activityStepsBadgeScreenBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding23 = null;
                }
                activityStepsBadgeScreenBinding23.unLock.setTextColor(getResources().getColor(i));
                ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding24 = this.p;
                if (activityStepsBadgeScreenBinding24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityStepsBadgeScreenBinding24 = null;
                }
                activityStepsBadgeScreenBinding24.unLock.setText(getString(R.string.unlock_bronze) + ' ' + getData().getBadgeLevels().get(0).getNextLevelCriteria() + ' ' + getString(R.string.times));
            }
        }
        ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding25 = this.p;
        if (activityStepsBadgeScreenBinding25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityStepsBadgeScreenBinding25 = null;
        }
        activityStepsBadgeScreenBinding25.icImageShare.setVisibility(4);
        ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding26 = this.p;
        if (activityStepsBadgeScreenBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityStepsBadgeScreenBinding26 = null;
        }
        activityStepsBadgeScreenBinding26.done.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityStepsBadgeScreen.s(ActivityStepsBadgeScreen.this, view);
            }
        });
        ActivityStepsBadgeScreenBinding activityStepsBadgeScreenBinding27 = this.p;
        if (activityStepsBadgeScreenBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityStepsBadgeScreenBinding = activityStepsBadgeScreenBinding27;
        }
        activityStepsBadgeScreenBinding.icImageShare.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityStepsBadgeScreen.t(ActivityStepsBadgeScreen.this, view);
            }
        });
    }

    public final void setData(@NotNull MyBadgesModel.DataBean.BadgesBean badgesBean) {
        Intrinsics.checkNotNullParameter(badgesBean, "<set-?>");
        this.data = badgesBean;
    }
}
