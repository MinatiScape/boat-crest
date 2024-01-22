package com.coveiot.android.leonardo.sensai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivitySensAiInfoBinding;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIInfo extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivitySensAiInfoBinding p;

    public static final void r(ActivitySensAIInfo this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
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

    public final void initToolbar() {
        ActivitySensAiInfoBinding activitySensAiInfoBinding = this.p;
        ActivitySensAiInfoBinding activitySensAiInfoBinding2 = null;
        if (activitySensAiInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySensAiInfoBinding = null;
        }
        TextView textView = (TextView) activitySensAiInfoBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivitySensAiInfoBinding activitySensAiInfoBinding3 = this.p;
        if (activitySensAiInfoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySensAiInfoBinding2 = activitySensAiInfoBinding3;
        }
        textView.setText(getString(R.string.information));
        ((TextView) activitySensAiInfoBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIInfo.r(ActivitySensAIInfo.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivitySensAiInfoBinding inflate = ActivitySensAiInfoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
    }
}
