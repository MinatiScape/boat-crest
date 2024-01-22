package com.coveiot.android.navigation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.databinding.ActivityNavigationFtuBinding;
import com.coveiot.android.navigation.fragments.FragmentDisclaimer;
import com.coveiot.android.navigation.fragments.FragmentNavigationFtu;
import com.coveiot.android.navigation.interfaces.NavigationFTUScreenChangeListener;
import com.coveiot.android.navigation.models.ScreenType;
import com.coveiot.android.navigation.viewModels.ActivityNavigationFTUViewModel;
import com.coveiot.android.theme.BaseActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationFTU extends BaseActivity implements NavigationFTUScreenChangeListener {
    public ActivityNavigationFtuBinding binding;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public TextView titleTextView;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityNavigationFTU.class.getSimpleName();

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenType.values().length];
            try {
                iArr[ScreenType.FTU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ScreenType.DISCLAIMER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ScreenType.NAVIGATION_MAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void u(ActivityNavigationFTU this$0, View view) {
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
    public final ActivityNavigationFtuBinding getBinding() {
        ActivityNavigationFtuBinding activityNavigationFtuBinding = this.binding;
        if (activityNavigationFtuBinding != null) {
            return activityNavigationFtuBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final int getModeOnBand() {
        return this.r;
    }

    public final int getPlaceIdOnBand() {
        return this.q;
    }

    public final String getTAG() {
        return this.p;
    }

    @NotNull
    public final TextView getTitleTextView() {
        TextView textView = this.titleTextView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.t) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            int i = R.id.fragment_container_navigation_ftu;
            if (supportFragmentManager.findFragmentById(i) instanceof FragmentDisclaimer) {
                getSupportFragmentManager().beginTransaction().replace(i, new FragmentNavigationFtu()).commit();
                return;
            } else {
                super.onBackPressed();
                return;
            }
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityNavigationFtuBinding inflate = ActivityNavigationFtuBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        ActivityNavigationFTUViewModel activityNavigationFTUViewModel = (ActivityNavigationFTUViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationFTUViewModel.class);
        if (getIntent() != null) {
            this.s = getIntent().getBooleanExtra("isFromBand", false);
            this.q = getIntent().getIntExtra("placeIdOnBand", 0);
            this.r = getIntent().getIntExtra("modeOnBand", 0);
            this.t = getIntent().getBooleanExtra("isToView", false);
        }
        t();
        if (!this.t) {
            s();
            return;
        }
        getTitleTextView().setText(getString(R.string.disclaimer));
        r();
    }

    @Override // com.coveiot.android.navigation.interfaces.NavigationFTUScreenChangeListener
    public void onFragmentChanged(@NotNull ScreenType screenType) {
        Intent intent;
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        int i = WhenMappings.$EnumSwitchMapping$0[screenType.ordinal()];
        if (i == 1) {
            getTitleTextView().setText(getString(R.string.navigation));
        } else if (i == 2) {
            getTitleTextView().setText(getString(R.string.disclaimer));
            r();
        } else if (i != 3) {
        } else {
            if (this.t) {
                finish();
                return;
            }
            if (this.s) {
                intent = new Intent(this, ActivityDirections.class);
                intent.putExtra("isFromBand", true);
                intent.putExtra("placeIdOnBand", this.q);
                intent.putExtra("modeOnBand", this.r);
            } else {
                intent = new Intent(this, ActivityNavigationMain.class);
            }
            startActivity(intent);
            finish();
        }
    }

    public final void r() {
        getSupportFragmentManager().beginTransaction().replace(getBinding().fragmentContainerNavigationFtu.getId(), FragmentDisclaimer.Companion.newInstance(this.t)).commitAllowingStateLoss();
    }

    public final void s() {
        getSupportFragmentManager().beginTransaction().replace(getBinding().fragmentContainerNavigationFtu.getId(), new FragmentNavigationFtu()).commitAllowingStateLoss();
    }

    public final void setBinding(@NotNull ActivityNavigationFtuBinding activityNavigationFtuBinding) {
        Intrinsics.checkNotNullParameter(activityNavigationFtuBinding, "<set-?>");
        this.binding = activityNavigationFtuBinding;
    }

    public final void setModeOnBand(int i) {
        this.r = i;
    }

    public final void setPlaceIdOnBand(int i) {
        this.q = i;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    public final void setTitleTextView(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleTextView = textView;
    }

    public final void t() {
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        setTitleTextView((TextView) findViewById);
        getTitleTextView().setText(getResources().getString(R.string.navigation));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationFTU.u(ActivityNavigationFTU.this, view);
            }
        });
    }
}
