package com.coveiot.android.theme;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.theme.databinding.ActivityStrideLengthAnimationBinding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class StrideLengthAnimationActivity extends BaseActivity {
    public boolean p;
    public ActivityStrideLengthAnimationBinding t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String q = "forWalkStride";
    @NotNull
    public final String r = "strideLength";
    public final int s = 173;

    public static final void u(StrideLengthAnimationActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void v(StrideLengthAnimationActivity this$0, ActivityStrideLengthAnimationBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.y(this_apply);
    }

    public static final void w(StrideLengthAnimationActivity this$0, ActivityStrideLengthAnimationBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.y(this_apply);
    }

    public static final void x(StrideLengthAnimationActivity this$0, ActivityStrideLengthAnimationBinding this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        Intent intent = new Intent();
        intent.putExtra(this$0.q, this$0.p);
        String str = this$0.r;
        Editable text = this_apply.etDistance.getText();
        Intrinsics.checkNotNullExpressionValue(text, "etDistance.text");
        int parseInt = Integer.parseInt(StringsKt__StringsKt.trim(text).toString());
        Editable text2 = this_apply.etSteps.getText();
        Intrinsics.checkNotNullExpressionValue(text2, "etSteps.text");
        intent.putExtra(str, String.valueOf(parseInt / Integer.parseInt(StringsKt__StringsKt.trim(text2).toString())));
        this$0.setResult(this$0.s, intent);
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(this.s, new Intent());
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityStrideLengthAnimationBinding inflate = ActivityStrideLengthAnimationBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.t = inflate;
        final ActivityStrideLengthAnimationBinding activityStrideLengthAnimationBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        Bundle extras = getIntent().getExtras();
        Boolean valueOf = extras != null ? Boolean.valueOf(extras.getBoolean(this.q, false)) : null;
        Intrinsics.checkNotNull(valueOf);
        this.p = valueOf.booleanValue();
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.d1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StrideLengthAnimationActivity.u(StrideLengthAnimationActivity.this, view);
            }
        });
        ActivityStrideLengthAnimationBinding activityStrideLengthAnimationBinding2 = this.t;
        if (activityStrideLengthAnimationBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityStrideLengthAnimationBinding = activityStrideLengthAnimationBinding2;
        }
        if (this.p) {
            activityStrideLengthAnimationBinding.btnCalculate.setText(getString(R.string.calculate_walk_stride_length));
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.walk_stride_length));
        } else {
            activityStrideLengthAnimationBinding.btnCalculate.setText(getString(R.string.calculate_run_stride_length));
            ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.run_stride_length));
        }
        activityStrideLengthAnimationBinding.ivVideo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.f1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StrideLengthAnimationActivity.v(StrideLengthAnimationActivity.this, activityStrideLengthAnimationBinding, view);
            }
        });
        activityStrideLengthAnimationBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.g1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StrideLengthAnimationActivity.w(StrideLengthAnimationActivity.this, activityStrideLengthAnimationBinding, view);
            }
        });
        activityStrideLengthAnimationBinding.btnCalculate.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.e1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StrideLengthAnimationActivity.x(StrideLengthAnimationActivity.this, activityStrideLengthAnimationBinding, view);
            }
        });
    }

    public final void y(ActivityStrideLengthAnimationBinding activityStrideLengthAnimationBinding) {
        if (activityStrideLengthAnimationBinding.nestedScrollView.getVisibility() == 0) {
            activityStrideLengthAnimationBinding.nestedScrollView.setVisibility(8);
            activityStrideLengthAnimationBinding.clLottie.setVisibility(0);
            activityStrideLengthAnimationBinding.animationView.playAnimation();
            return;
        }
        activityStrideLengthAnimationBinding.nestedScrollView.setVisibility(0);
        activityStrideLengthAnimationBinding.clLottie.setVisibility(8);
    }
}
