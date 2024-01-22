package com.coveiot.android.leonardo.dashboard.health.activities;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.fragments.FragmentHeartRateHistory;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivityHeartRateHistory extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentHeartRateHistory p;

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
        this.p = new FragmentHeartRateHistory();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        FragmentHeartRateHistory fragmentHeartRateHistory = this.p;
        if (fragmentHeartRateHistory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragmentHeartRateHistory");
            fragmentHeartRateHistory = null;
        }
        beginTransaction.replace(R.id.fragment_container, fragmentHeartRateHistory).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_fitness);
        initFragment();
    }
}
