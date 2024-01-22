package com.coveiot.android.fitnesschallenges;

import android.os.Bundle;
import android.view.View;
import com.coveiot.android.fitnesschallenges.databinding.ActivityCreateChallengeBinding;
import com.coveiot.android.fitnesschallenges.fragments.FragmentCreateChallenge;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.fitnesschallenge.model.BuddiesChallengeDetail;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityCreateChallenge extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityCreateChallengeBinding p;
    public boolean q;
    @Nullable
    public BuddiesChallengeDetail r;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentCreateChallenge.Companion.newInstance(this.q, this.r)).commit();
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
        if (getIntent().hasExtra("isEditChallenge")) {
            this.q = getIntent().getBooleanExtra("isEditChallenge", false);
        }
        if (getIntent().hasExtra("buddiesChallengeDetails")) {
            this.r = (BuddiesChallengeDetail) getIntent().getSerializableExtra("buddiesChallengeDetails");
        }
        loadCreateChallenge();
    }
}
