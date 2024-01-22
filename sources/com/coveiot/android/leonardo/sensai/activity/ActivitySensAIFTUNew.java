package com.coveiot.android.leonardo.sensai.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensAiFtuActivityBinding;
import com.coveiot.android.leonardo.sensai.fragment.FragmentSensAIFtu1;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIFTUNew extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public SensAiFtuActivityBinding p;

    public static final void r(ActivitySensAIFTUNew this$0, View view) {
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
        SensAiFtuActivityBinding sensAiFtuActivityBinding = this.p;
        SensAiFtuActivityBinding sensAiFtuActivityBinding2 = null;
        if (sensAiFtuActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiFtuActivityBinding = null;
        }
        TextView textView = (TextView) sensAiFtuActivityBinding.toolbar.findViewById(R.id.toolbar_title);
        SensAiFtuActivityBinding sensAiFtuActivityBinding3 = this.p;
        if (sensAiFtuActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiFtuActivityBinding3 = null;
        }
        TextView textView2 = (TextView) sensAiFtuActivityBinding3.toolbar.findViewById(R.id.toolbar_back_arrow);
        SensAiFtuActivityBinding sensAiFtuActivityBinding4 = this.p;
        if (sensAiFtuActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sensAiFtuActivityBinding2 = sensAiFtuActivityBinding4;
        }
        ((ImageView) sensAiFtuActivityBinding2.toolbar.findViewById(R.id.share_iv)).setVisibility(0);
        textView.setText(getString(R.string.sens_ai));
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIFTUNew.r(ActivitySensAIFTUNew.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SensAiFtuActivityBinding inflate = SensAiFtuActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        s();
    }

    public final void s() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentSensAIFtu1.Companion.newInstance()).commitAllowingStateLoss();
    }
}
