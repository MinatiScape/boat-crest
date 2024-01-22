package com.coveiot.android.fitnesschallenges;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.fitnesschallenges.databinding.ActivityCreateChallengeBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentFCLeaderboard;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityViewLeaderboard extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityCreateChallengeBinding p;
    @Nullable
    public BuddiesChallengeDetail q;
    public boolean r;

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

    public final void loadCreateChallenge() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.fragment_container;
        FragmentFCLeaderboard.Companion companion = FragmentFCLeaderboard.Companion;
        BuddiesChallengeDetail buddiesChallengeDetail = this.q;
        Intrinsics.checkNotNull(buddiesChallengeDetail);
        beginTransaction.replace(i, companion.newInstance(buddiesChallengeDetail, this.r)).commit();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityCreateChallengeBinding inflate = ActivityCreateChallengeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().getExtras() != null && getIntent().hasExtra("buddiesChallengeDetails")) {
            Serializable serializableExtra = getIntent().getSerializableExtra("buddiesChallengeDetails");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail");
            this.q = (BuddiesChallengeDetail) serializableExtra;
        }
        if (getIntent().hasExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS)) {
            this.r = getIntent().getBooleanExtra(FitnessChallengeConstants.ISFROMACHIEVEMENTS, false);
        }
        if (this.q != null) {
            loadCreateChallenge();
        } else {
            finish();
        }
    }
}
