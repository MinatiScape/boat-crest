package com.coveiot.android.respiratoryrate.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.respiratoryrate.R;
import com.coveiot.android.respiratoryrate.databinding.ActivityRespiratoryRateShareBinding;
import com.coveiot.android.respiratoryrate.fragments.FragmentRespiratoryRateShare;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData;
import com.coveiot.android.respiratoryrate.utils.Constants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.utils.utility.ShareScreen;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ActivityRespiratoryRateShare extends BaseActivity {
    public Bitmap bitmap;
    public ActivityRespiratoryRateShareBinding p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public RespiratoryRateShareData q = new RespiratoryRateShareData();

    public static final void u(ActivityRespiratoryRateShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void v(final ActivityRespiratoryRateShare this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Handler handler = new Handler();
        this$0.showProgress();
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.respiratoryrate.activities.j
            @Override // java.lang.Runnable
            public final void run() {
                ActivityRespiratoryRateShare.w(ActivityRespiratoryRateShare.this);
            }
        }, 500L);
    }

    public static final void w(ActivityRespiratoryRateShare this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        ActivityRespiratoryRateShareBinding activityRespiratoryRateShareBinding = this$0.p;
        if (activityRespiratoryRateShareBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateShareBinding = null;
        }
        Bitmap saveScreen = ShareScreen.saveScreen(activityRespiratoryRateShareBinding.containerLayout, this$0);
        Intrinsics.checkNotNullExpressionValue(saveScreen, "saveScreen(\n            …teShare\n                )");
        this$0.setBitmap(saveScreen);
        ShareScreen.shareScreenCommom(this$0.getBitmap(), this$0);
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
        ActivityRespiratoryRateShareBinding inflate = ActivityRespiratoryRateShareBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityRespiratoryRateShareBinding activityRespiratoryRateShareBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Constants constants = Constants.SHARE_DATA;
            if (intent.getSerializableExtra(constants.getValue()) != null && (getIntent().getSerializableExtra(constants.getValue()) instanceof RespiratoryRateShareData)) {
                Serializable serializableExtra = getIntent().getSerializableExtra(constants.getValue());
                Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.coveiot.android.respiratoryrate.model.RespiratoryRateShareData");
                this.q = (RespiratoryRateShareData) serializableExtra;
            }
        }
        if (this.q == null) {
            finish();
        } else {
            t();
        }
        ActivityRespiratoryRateShareBinding activityRespiratoryRateShareBinding2 = this.p;
        if (activityRespiratoryRateShareBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRespiratoryRateShareBinding2 = null;
        }
        activityRespiratoryRateShareBinding2.shareCloseLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateShare.u(ActivityRespiratoryRateShare.this, view);
            }
        });
        ActivityRespiratoryRateShareBinding activityRespiratoryRateShareBinding3 = this.p;
        if (activityRespiratoryRateShareBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityRespiratoryRateShareBinding = activityRespiratoryRateShareBinding3;
        }
        activityRespiratoryRateShareBinding.shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.respiratoryrate.activities.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityRespiratoryRateShare.v(ActivityRespiratoryRateShare.this, view);
            }
        });
    }

    public final void setBitmap(@NotNull Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<set-?>");
        this.bitmap = bitmap;
    }

    public final void t() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        int i = R.id.container_layout;
        FragmentRespiratoryRateShare.Companion companion = FragmentRespiratoryRateShare.Companion;
        RespiratoryRateShareData respiratoryRateShareData = this.q;
        Intrinsics.checkNotNull(respiratoryRateShareData);
        beginTransaction.replace(i, companion.newInstance(respiratoryRateShareData)).commitAllowingStateLoss();
    }
}
