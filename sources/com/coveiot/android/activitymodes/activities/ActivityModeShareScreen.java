package com.coveiot.android.activitymodes.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.fragments.FragmentBestActivityShare;
import com.coveiot.android.activitymodes.models.ActivityShareData;
import com.coveiot.android.theme.BaseActivity;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityModeShareScreen extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public Bitmap bitmap;
    @Nullable
    public ActivityShareData p;

    public static final void s(ActivityModeShareScreen this$0, View view) {
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
    public final Bitmap getBitmap() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bitmap");
        return null;
    }

    @Nullable
    public final ActivityShareData getDataActivity() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_screen);
        if (getIntent().getExtras() != null && getIntent().getSerializableExtra("share_data") != null && (getIntent().getSerializableExtra("share_data") instanceof ActivityShareData)) {
            Serializable serializableExtra = getIntent().getSerializableExtra("share_data");
            Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.activitymodes.models.ActivityShareData");
            this.p = (ActivityShareData) serializableExtra;
        }
        if (this.p == null) {
            finish();
        } else {
            r();
        }
        ((LinearLayout) _$_findCachedViewById(R.id.share_close_layout)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activities.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityModeShareScreen.s(ActivityModeShareScreen.this, view);
            }
        });
    }

    public final void r() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentBestActivityShare.Companion companion = FragmentBestActivityShare.Companion;
        ActivityShareData activityShareData = this.p;
        Intrinsics.checkNotNull(activityShareData);
        beginTransaction.replace(i, companion.newInstance(activityShareData)).commitAllowingStateLoss();
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void setDataActivity(@Nullable ActivityShareData activityShareData) {
        this.p = activityShareData;
    }
}
