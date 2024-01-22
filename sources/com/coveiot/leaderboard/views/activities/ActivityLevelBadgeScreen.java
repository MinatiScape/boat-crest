package com.coveiot.leaderboard.views.activities;

import android.view.View;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.leaderboard.LeaderBoardNavigator;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.ActivityLevelBadgeScreenBinding;
import com.coveiot.leaderboard.utils.ShareData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityLevelBadgeScreen extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public MyBadgesModel.DataBean.BadgesBean data;
    public ActivityLevelBadgeScreenBinding p;

    public static final void s(ActivityLevelBadgeScreen this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void t(ActivityLevelBadgeScreen this$0, View view) {
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

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x010c  */
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(@org.jetbrains.annotations.Nullable android.os.Bundle r4) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.leaderboard.views.activities.ActivityLevelBadgeScreen.onCreate(android.os.Bundle):void");
    }

    public final void setData(@NotNull MyBadgesModel.DataBean.BadgesBean badgesBean) {
        Intrinsics.checkNotNullParameter(badgesBean, "<set-?>");
        this.data = badgesBean;
    }
}
