package com.coveiot.android.leonardo.dashboard.health.activities;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentAmbientSoundLevelHistory;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityAmbientSoundHistory extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentAmbientSoundLevelHistory p;

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

    public final void initFragment() {
        this.p = new FragmentAmbientSoundLevelHistory();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentAmbientSoundLevelHistory fragmentAmbientSoundLevelHistory = this.p;
        if (fragmentAmbientSoundLevelHistory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAmbientSoundLevelHistory");
            fragmentAmbientSoundLevelHistory = null;
        }
        beginTransaction.replace(R.id.fragment_container, fragmentAmbientSoundLevelHistory).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fitness);
        initFragment();
    }
}
