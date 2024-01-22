package com.coveiot.leaderboard.rankshare.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.ActivityRankShareBinding;
import com.coveiot.leaderboard.rankshare.fragments.FragmentBadgeCardShare;
import com.coveiot.leaderboard.rankshare.fragments.FragmentRankShare;
import com.coveiot.leaderboard.utils.LeaderboardConstants;
import com.coveiot.leaderboard.utils.ShareData;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityRankShare extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ShareData data;
    public ActivityRankShareBinding p;

    public static final void r(ActivityRankShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
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
    public final ShareData getData() {
        ShareData shareData = this.data;
        if (shareData != null) {
            return shareData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("data");
        return null;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        String str;
        super.onCreate(bundle);
        ActivityRankShareBinding inflate = ActivityRankShareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityRankShareBinding activityRankShareBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().getExtras() != null) {
            str = getIntent().getStringExtra(LeaderboardConstants.SHARE_SCREEN_TYPE);
            Intrinsics.checkNotNull(str);
            Serializable serializableExtra = getIntent().getSerializableExtra("share_data");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.leaderboard.utils.ShareData");
            setData((ShareData) serializableExtra);
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            if (m.equals(str, getResources().getString(R.string.rank), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentRankShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            } else if (m.equals(str, getResources().getString(R.string.badge_level), true)) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout, FragmentBadgeCardShare.Companion.newInstance(getData())).commitAllowingStateLoss();
            }
        } else {
            finish();
        }
        ActivityRankShareBinding activityRankShareBinding2 = this.p;
        if (activityRankShareBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRankShareBinding = activityRankShareBinding2;
        }
        activityRankShareBinding.shareCloseLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.rankshare.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRankShare.r(ActivityRankShare.this, view);
            }
        });
    }

    public final void setData(@NotNull ShareData shareData) {
        Intrinsics.checkNotNullParameter(shareData, "<set-?>");
        this.data = shareData;
    }
}
