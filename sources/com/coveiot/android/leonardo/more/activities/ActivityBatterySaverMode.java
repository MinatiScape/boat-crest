package com.coveiot.android.leonardo.more.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityBatterySaverModeNewBinding;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityBatterySaverMode extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
    public BottomSheetDialogImageTitleMessage bottomSheetDialogOneButtonTitleMessage;
    public ActivityBatterySaverModeNewBinding p;
    public boolean q;
    public boolean r;

    public static final void r(ActivityBatterySaverMode this$0, View view) {
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

    @NotNull
    public final BottomSheetDialogOneButtonOneTitle getBottomSheetDialogOneButtonOneTitle() {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this.bottomSheetDialogOneButtonOneTitle;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            return bottomSheetDialogOneButtonOneTitle;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonOneTitle");
        return null;
    }

    @NotNull
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogOneButtonTitleMessage() {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.bottomSheetDialogOneButtonTitleMessage;
        if (bottomSheetDialogImageTitleMessage != null) {
            return bottomSheetDialogImageTitleMessage;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bottomSheetDialogOneButtonTitleMessage");
        return null;
    }

    public final boolean getFlagAdvanced() {
        return this.r;
    }

    public final boolean getFlagStandard() {
        return this.q;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.battery_saver_mode));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.w3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBatterySaverMode.r(ActivityBatterySaverMode.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityBatterySaverModeNewBinding inflate = ActivityBatterySaverModeNewBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
    }

    public final void setBottomSheetDialogOneButtonOneTitle(@NotNull BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "<set-?>");
        this.bottomSheetDialogOneButtonOneTitle = bottomSheetDialogOneButtonOneTitle;
    }

    public final void setBottomSheetDialogOneButtonTitleMessage(@NotNull BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "<set-?>");
        this.bottomSheetDialogOneButtonTitleMessage = bottomSheetDialogImageTitleMessage;
    }

    public final void setFlagAdvanced(boolean z) {
        this.r = z;
    }

    public final void setFlagStandard(boolean z) {
        this.q = z;
    }
}
