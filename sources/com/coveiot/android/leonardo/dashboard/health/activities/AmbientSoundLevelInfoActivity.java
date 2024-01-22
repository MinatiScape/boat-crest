package com.coveiot.android.leonardo.dashboard.health.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.coveiot.android.boat.R;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AmbientSoundLevelInfoActivity extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    public static final void r(AmbientSoundLevelInfoActivity this$0, View view) {
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ambient_sound_info);
        ((ImageView) _$_findCachedViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmbientSoundLevelInfoActivity.r(AmbientSoundLevelInfoActivity.this, view);
            }
        });
    }
}
