package com.mappls.sdk.nearby.plugin;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyActivityBinding;
import com.mappls.sdk.nearby.plugin.fragment.NearbyFragment;
import com.mappls.sdk.nearby.plugin.fragment.NearbyResultFragment;
import com.mappls.sdk.nearby.plugin.model.MapplsNearbyResponse;
import com.mappls.sdk.nearby.plugin.model.NearbyUIOption;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Keep
/* loaded from: classes10.dex */
public final class MapplsNearbyActivity extends AppCompatActivity implements a, INearbyResultCallback {
    private MapplsNearbyActivityBinding mBinding;

    private final void replaceFragment(Fragment fragment, boolean z) {
        MapplsNearbyActivityBinding mapplsNearbyActivityBinding = null;
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(fragment != null ? fragment.getClass().getSimpleName() : null);
        if (fragment == null || findFragmentByTag != null) {
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "fm.beginTransaction()");
        MapplsNearbyActivityBinding mapplsNearbyActivityBinding2 = this.mBinding;
        if (mapplsNearbyActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mBinding");
        } else {
            mapplsNearbyActivityBinding = mapplsNearbyActivityBinding2;
        }
        beginTransaction.replace(mapplsNearbyActivityBinding.mapplsNearbyFragmentContainer.getId(), fragment, fragment.getClass().getSimpleName());
        if (z) {
            beginTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        try {
            beginTransaction.commit();
        } catch (IllegalStateException unused) {
            beginTransaction.commitAllowingStateLoss();
        }
    }

    @Override // com.mappls.sdk.nearby.plugin.INearbyResultCallback
    public void onCancel() {
        getSupportFragmentManager().popBackStack("NearbyResultFragment", 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.mappls_nearby_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l…t.mappls_nearby_activity)");
        this.mBinding = (MapplsNearbyActivityBinding) contentView;
        NearbyUIOption nearbyUIOption = MapplsNearbyWidget.INSTANCE.getNearbyUIOption();
        if (bundle == null) {
            NearbyFragment newInstance = NearbyFragment.Companion.newInstance(nearbyUIOption);
            newInstance.setNearbyCallback(this);
            replaceFragment(newInstance, false);
            return;
        }
        NearbyFragment nearbyFragment = (NearbyFragment) getSupportFragmentManager().findFragmentByTag("NearbyFragment");
        if (nearbyFragment != null) {
            nearbyFragment.setNearbyCallback(this);
        }
        NearbyResultFragment nearbyResultFragment = (NearbyResultFragment) getSupportFragmentManager().findFragmentByTag("NearbyResultFragment");
        if (nearbyResultFragment != null) {
            nearbyResultFragment.setOnNearbyResultCallback(this);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MapplsNearbyWidget.INSTANCE.clear$mappls_nearby_widget_othersRelease();
    }

    @Override // com.mappls.sdk.nearby.plugin.a
    public void onSelectCategory(@NotNull MapplsNearbyResponse mapplsNearbyResponse) {
        Intrinsics.checkNotNullParameter(mapplsNearbyResponse, "mapplsNearbyResponse");
        NearbyResultFragment newInstance = NearbyResultFragment.Companion.newInstance(mapplsNearbyResponse, MapplsNearbyWidget.INSTANCE.getNearbyUIOption());
        newInstance.setOnNearbyResultCallback(this);
        replaceFragment(newInstance, true);
    }

    @Override // com.mappls.sdk.nearby.plugin.INearbyResultCallback
    public void onSelectResult(@NotNull NearbyAtlasResult nearbyAtlasResult) {
        Intrinsics.checkNotNullParameter(nearbyAtlasResult, "nearbyAtlasResult");
        String json = new Gson().toJson(nearbyAtlasResult);
        Intent intent = new Intent();
        intent.putExtra("com.mappls.sdk.nearby.plugin.NEARBY_RESULT_KEY", json);
        setResult(-1, intent);
        finish();
    }
}
