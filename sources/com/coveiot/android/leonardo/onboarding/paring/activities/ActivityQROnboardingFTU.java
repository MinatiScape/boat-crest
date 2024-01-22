package com.coveiot.android.leonardo.onboarding.paring.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.viewpager2.widget.ViewPager2;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityOnboardingFtuBinding;
import com.coveiot.android.leonardo.onboarding.paring.adapters.ViewPagerOnboardingFTUAdapter;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityQROnboardingFTU extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityOnboardingFtuBinding p;
    public ViewPagerOnboardingFTUAdapter q;
    @NotNull
    public final int[] r;
    public int s;
    public final int t;

    public ActivityQROnboardingFTU() {
        int[] iArr = {R.drawable.ftu_image1, R.drawable.ftu_image2, R.drawable.ftu_image3, R.drawable.ftu_image4, R.drawable.ftu_image5};
        this.r = iArr;
        this.t = iArr.length;
    }

    public static final void s(ActivityQROnboardingFTU this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void t(ActivityQROnboardingFTU this$0, View view) {
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

    public final int getFtuItemCount() {
        return this.t;
    }

    public final int getSelectedItemPosition() {
        return this.s;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityOnboardingFtuBinding inflate = ActivityOnboardingFtuBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding2 = this.p;
        if (activityOnboardingFtuBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding2 = null;
        }
        activityOnboardingFtuBinding2.setFTUItemCount(Integer.valueOf(this.t));
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding3 = this.p;
        if (activityOnboardingFtuBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding3 = null;
        }
        ((TextView) activityOnboardingFtuBinding3.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title1));
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding4 = this.p;
        if (activityOnboardingFtuBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding4 = null;
        }
        activityOnboardingFtuBinding4.tvFtuInfo.setText(getString(R.string.desc1));
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding5 = this.p;
        if (activityOnboardingFtuBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding5 = null;
        }
        activityOnboardingFtuBinding5.dot1.setImageResource(R.drawable.viewpager_selected_indicator);
        this.q = new ViewPagerOnboardingFTUAdapter(this, this.r);
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding6 = this.p;
        if (activityOnboardingFtuBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding6 = null;
        }
        ViewPager2 viewPager2 = activityOnboardingFtuBinding6.vpOnboardingFTU;
        ViewPagerOnboardingFTUAdapter viewPagerOnboardingFTUAdapter = this.q;
        if (viewPagerOnboardingFTUAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPagerAdapter");
            viewPagerOnboardingFTUAdapter = null;
        }
        viewPager2.setAdapter(viewPagerOnboardingFTUAdapter);
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding7 = this.p;
        if (activityOnboardingFtuBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding7 = null;
        }
        activityOnboardingFtuBinding7.vpOnboardingFTU.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.ActivityQROnboardingFTU$onCreate$1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i, float f, int i2) {
                ActivityOnboardingFtuBinding activityOnboardingFtuBinding8;
                activityOnboardingFtuBinding8 = ActivityQROnboardingFTU.this.p;
                if (activityOnboardingFtuBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityOnboardingFtuBinding8 = null;
                }
                activityOnboardingFtuBinding8.setSelectedItemPosition(Integer.valueOf(i));
                ActivityQROnboardingFTU.this.setSelectedItemPosition(i);
                ActivityQROnboardingFTU.this.u(i);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i) {
            }
        });
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding8 = this.p;
        if (activityOnboardingFtuBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityOnboardingFtuBinding8 = null;
        }
        activityOnboardingFtuBinding8.btnGetStarted.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQROnboardingFTU.s(ActivityQROnboardingFTU.this, view);
            }
        });
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding9 = this.p;
        if (activityOnboardingFtuBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityOnboardingFtuBinding = activityOnboardingFtuBinding9;
        }
        ((TextView) activityOnboardingFtuBinding.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.activities.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityQROnboardingFTU.t(ActivityQROnboardingFTU.this, view);
            }
        });
    }

    public final void setSelectedItemPosition(int i) {
        this.s = i;
    }

    public final void u(int i) {
        ActivityOnboardingFtuBinding activityOnboardingFtuBinding = null;
        if (i == 0) {
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding2 = this.p;
            if (activityOnboardingFtuBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOnboardingFtuBinding2 = null;
            }
            ((TextView) activityOnboardingFtuBinding2.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title1));
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding3 = this.p;
            if (activityOnboardingFtuBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityOnboardingFtuBinding = activityOnboardingFtuBinding3;
            }
            activityOnboardingFtuBinding.tvFtuInfo.setText(getString(R.string.desc1));
        } else if (i == 1) {
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding4 = this.p;
            if (activityOnboardingFtuBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOnboardingFtuBinding4 = null;
            }
            ((TextView) activityOnboardingFtuBinding4.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title2));
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding5 = this.p;
            if (activityOnboardingFtuBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityOnboardingFtuBinding = activityOnboardingFtuBinding5;
            }
            activityOnboardingFtuBinding.tvFtuInfo.setText(getString(R.string.desc2));
        } else if (i == 2) {
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding6 = this.p;
            if (activityOnboardingFtuBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOnboardingFtuBinding6 = null;
            }
            ((TextView) activityOnboardingFtuBinding6.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title3));
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding7 = this.p;
            if (activityOnboardingFtuBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityOnboardingFtuBinding = activityOnboardingFtuBinding7;
            }
            activityOnboardingFtuBinding.tvFtuInfo.setText(getString(R.string.desc3));
        } else if (i == 3) {
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding8 = this.p;
            if (activityOnboardingFtuBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOnboardingFtuBinding8 = null;
            }
            ((TextView) activityOnboardingFtuBinding8.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title4));
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding9 = this.p;
            if (activityOnboardingFtuBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityOnboardingFtuBinding = activityOnboardingFtuBinding9;
            }
            activityOnboardingFtuBinding.tvFtuInfo.setText(getString(R.string.desc4));
        } else if (i != 4) {
        } else {
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding10 = this.p;
            if (activityOnboardingFtuBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityOnboardingFtuBinding10 = null;
            }
            ((TextView) activityOnboardingFtuBinding10.toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.toolbar_title5));
            ActivityOnboardingFtuBinding activityOnboardingFtuBinding11 = this.p;
            if (activityOnboardingFtuBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityOnboardingFtuBinding = activityOnboardingFtuBinding11;
            }
            activityOnboardingFtuBinding.tvFtuInfo.setText(getString(R.string.desc5));
        }
    }
}
