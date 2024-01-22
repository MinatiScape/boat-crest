package com.coveiot.android.respiratoryrate.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateInfoBinding;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivityRespiratoryRateInfo extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public Bitmap bitmap;
    public ActivityRespiratoryRateInfoBinding p;

    public static final void r(ActivityRespiratoryRateInfo this$0, View view) {
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

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityRespiratoryRateInfoBinding inflate = ActivityRespiratoryRateInfoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityRespiratoryRateInfoBinding activityRespiratoryRateInfoBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityRespiratoryRateInfoBinding activityRespiratoryRateInfoBinding2 = this.p;
        if (activityRespiratoryRateInfoBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRespiratoryRateInfoBinding = activityRespiratoryRateInfoBinding2;
        }
        activityRespiratoryRateInfoBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateInfo.r(ActivityRespiratoryRateInfo.this, view);
            }
        });
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }
}
