package com.coveiot.android.tappy.activity;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.tappy.databinding.ActivityAddNfcStrapFtuBinding;
import com.coveiot.android.tappy.fragment.FragmentAddNfcStrapFtu;
import com.coveiot.android.tappy.fragment.FragmentSupportedBank;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AddNfcStrapFtuActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String p = "AddNfcStrapFtuActivity";
    public ActivityAddNfcStrapFtuBinding q;
    @Nullable
    public Long r;

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
    public final String getTAG() {
        return this.p;
    }

    public final void loadSupportedBankFragment() {
        FragmentSupportedBank fragmentSupportedBank;
        Long l = this.r;
        ActivityAddNfcStrapFtuBinding activityAddNfcStrapFtuBinding = null;
        if (l != null) {
            fragmentSupportedBank = FragmentSupportedBank.Companion.newInstance(l.longValue());
        } else {
            fragmentSupportedBank = null;
        }
        if (fragmentSupportedBank != null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            ActivityAddNfcStrapFtuBinding activityAddNfcStrapFtuBinding2 = this.q;
            if (activityAddNfcStrapFtuBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityAddNfcStrapFtuBinding = activityAddNfcStrapFtuBinding2;
            }
            beginTransaction.replace(activityAddNfcStrapFtuBinding.fragmentContainer.getId(), fragmentSupportedBank).commit();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @RequiresApi(26)
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddNfcStrapFtuBinding inflate = ActivityAddNfcStrapFtuBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.q = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().hasExtra(Constants.END_USER_GLOBAL_ID)) {
            this.r = Long.valueOf(getIntent().getLongExtra(Constants.END_USER_GLOBAL_ID, 0L));
        }
        if (!SessionManager.getInstance(this).isTapAndPayFtuShown()) {
            q();
        } else {
            loadSupportedBankFragment();
        }
    }

    public final void q() {
        FragmentAddNfcStrapFtu fragmentAddNfcStrapFtu = new FragmentAddNfcStrapFtu();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        ActivityAddNfcStrapFtuBinding activityAddNfcStrapFtuBinding = this.q;
        if (activityAddNfcStrapFtuBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddNfcStrapFtuBinding = null;
        }
        beginTransaction.replace(activityAddNfcStrapFtuBinding.fragmentContainer.getId(), fragmentAddNfcStrapFtu).commitAllowingStateLoss();
    }
}
