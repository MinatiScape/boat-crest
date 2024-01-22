package com.coveiot.android.leonardo.dashboard.health.spo2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.home.adapters.RemotePagerAdapter;
import com.coveiot.android.leonardo.utils.PagerContainer;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.compundview.AnimatedDotsView;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ActivitySpO2Measuring extends BaseActivity implements View.OnClickListener {
    @Nullable
    public BottomSheetDialogTwoButtons p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final int[] q = {R.string.spo2_instruction_text1, R.string.spo2_instruction_text2, R.string.spo2_instruction_text3};
    @NotNull
    public final int[] r = {R.drawable.spo2_instruction1, R.drawable.spo2_instruction2, R.drawable.spo2_instruction1};

    public static final void u(ActivitySpO2Measuring this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
        this$0.finish();
    }

    public static final void v(ActivitySpO2Measuring this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
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

    @Nullable
    public final BottomSheetDialogTwoButtons getExitConfirmationDialog() {
        return this.p;
    }

    @NotNull
    public final int[] getMContents() {
        return this.q;
    }

    @NotNull
    public final int[] getMImages() {
        return this.r;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getId() == R.id.close) {
            t();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_spo2_measuring);
        ((TextView) _$_findCachedViewById(R.id.close)).setOnClickListener(this);
        ((AnimatedDotsView) _$_findCachedViewById(R.id.spo2DotView)).startAnimation();
        s();
    }

    public final void s() {
        View findViewById = findViewById(R.id.viewpagger_spo2);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.coveiot.android.leonardo.utils.PagerContainer");
        PagerContainer pagerContainer = (PagerContainer) findViewById;
        pagerContainer.setAdapter(new RemotePagerAdapter(getSupportFragmentManager(), this, this.q, this.r));
        pagerContainer.setAnimationEnabled(true);
        pagerContainer.setFadeEnabled(true);
        pagerContainer.setFadeFactor(0.6f);
        ((CirclePageIndicator) _$_findCachedViewById(R.id.circlePageIndicator_spo2)).setViewPager(pagerContainer);
    }

    public final void setExitConfirmationDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.p = bottomSheetDialogTwoButtons;
    }

    public final void t() {
        if (this.p == null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.measurement_in_progress);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.measurement_in_progress)");
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            this.p = bottomSheetDialogTwoButtons;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
            String string3 = getString(R.string.yes);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySpO2Measuring.u(ActivitySpO2Measuring.this, view);
                }
            });
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.p;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
            String string4 = getString(R.string.f4085no);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
            bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.activity.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivitySpO2Measuring.v(ActivitySpO2Measuring.this, view);
                }
            });
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
        if (bottomSheetDialogTwoButtons3.isShowing()) {
            return;
        }
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.p;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
        bottomSheetDialogTwoButtons4.show();
    }
}
