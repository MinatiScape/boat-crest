package com.coveiot.android.leonardo.more.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityAddQrcodeBinding;
import com.coveiot.android.leonardo.more.fragments.SelectAppToPushQRCodeFragment;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAddQRCode extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityAddQrcodeBinding p;
    @Nullable
    public Bundle q;

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

    @Nullable
    public final Bundle getBundle() {
        return this.q;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddQrcodeBinding inflate = ActivityAddQrcodeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        SelectAppToPushQRCodeFragment.Companion companion = SelectAppToPushQRCodeFragment.Companion;
        Intent intent = getIntent();
        beginTransaction.replace(R.id.fragment_container_add_qr_code, companion.newInstance(intent != null ? intent.getStringExtra(AppConstants.CARD_TYPE.getValue()) : null)).commit();
    }

    public final void setBundle(@Nullable Bundle bundle) {
        this.q = bundle;
    }
}
