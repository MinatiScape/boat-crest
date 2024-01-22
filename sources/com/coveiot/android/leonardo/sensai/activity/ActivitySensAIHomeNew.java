package com.coveiot.android.leonardo.sensai.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.SensAiHomeActivityBinding;
import com.coveiot.android.leonardo.more.activities.ActivityEditProfile;
import com.coveiot.android.leonardo.sensai.adapter.PagerAdapterSensAIHome;
import com.coveiot.android.leonardo.sensai.fragment.FragmentOverallStats;
import com.coveiot.android.leonardo.sensai.fragment.FragmentSensAICoach;
import com.coveiot.android.leonardo.sensai.fragment.FragmentSessionInsights;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.GlideUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySensAIHomeNew extends BaseActivity {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public SensAiHomeActivityBinding p;
    @NotNull
    public PagerAdapterSensAIHome q;
    public boolean r;

    public ActivitySensAIHomeNew() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        this.q = new PagerAdapterSensAIHome(supportFragmentManager);
    }

    public static final void t(ActivitySensAIHomeNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivityEditProfile.class));
    }

    public static final void u(ActivitySensAIHomeNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void w(Ref.ObjectRef mFirebaseRemoteConfig, ActivitySensAIHomeNew this$0, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        SensAiHomeActivityBinding sensAiHomeActivityBinding = null;
        if (task.isSuccessful()) {
            ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("senseAI_coach_videos_filter");
            boolean z = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("senseAI_coach_videos_filter");
            this$0.r = z;
            if (z) {
                PagerAdapterSensAIHome pagerAdapterSensAIHome = this$0.q;
                FragmentOverallStats fragmentOverallStats = new FragmentOverallStats();
                String string = this$0.getString(R.string.overall_stats);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.overall_stats)");
                pagerAdapterSensAIHome.addFragment(fragmentOverallStats, string);
                PagerAdapterSensAIHome pagerAdapterSensAIHome2 = this$0.q;
                FragmentSessionInsights fragmentSessionInsights = new FragmentSessionInsights();
                String string2 = this$0.getString(R.string.session_details);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.session_details)");
                pagerAdapterSensAIHome2.addFragment(fragmentSessionInsights, string2);
                PagerAdapterSensAIHome pagerAdapterSensAIHome3 = this$0.q;
                FragmentSensAICoach fragmentSensAICoach = new FragmentSensAICoach();
                String string3 = this$0.getString(R.string.sens_ai_coach);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.sens_ai_coach)");
                pagerAdapterSensAIHome3.addFragment(fragmentSensAICoach, string3);
                SensAiHomeActivityBinding sensAiHomeActivityBinding2 = this$0.p;
                if (sensAiHomeActivityBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    sensAiHomeActivityBinding2 = null;
                }
                sensAiHomeActivityBinding2.viewPager.setAdapter(this$0.q);
                SensAiHomeActivityBinding sensAiHomeActivityBinding3 = this$0.p;
                if (sensAiHomeActivityBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    sensAiHomeActivityBinding3 = null;
                }
                sensAiHomeActivityBinding3.viewPager.setOffscreenPageLimit(3);
                SensAiHomeActivityBinding sensAiHomeActivityBinding4 = this$0.p;
                if (sensAiHomeActivityBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    sensAiHomeActivityBinding4 = null;
                }
                TabLayout tabLayout = sensAiHomeActivityBinding4.tabLayout;
                SensAiHomeActivityBinding sensAiHomeActivityBinding5 = this$0.p;
                if (sensAiHomeActivityBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    sensAiHomeActivityBinding = sensAiHomeActivityBinding5;
                }
                tabLayout.setupWithViewPager(sensAiHomeActivityBinding.viewPager);
                this$0.x();
                return;
            }
            PagerAdapterSensAIHome pagerAdapterSensAIHome4 = this$0.q;
            FragmentOverallStats fragmentOverallStats2 = new FragmentOverallStats();
            String string4 = this$0.getString(R.string.overall_stats);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.overall_stats)");
            pagerAdapterSensAIHome4.addFragment(fragmentOverallStats2, string4);
            PagerAdapterSensAIHome pagerAdapterSensAIHome5 = this$0.q;
            FragmentSessionInsights fragmentSessionInsights2 = new FragmentSessionInsights();
            String string5 = this$0.getString(R.string.session_details);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.session_details)");
            pagerAdapterSensAIHome5.addFragment(fragmentSessionInsights2, string5);
            SensAiHomeActivityBinding sensAiHomeActivityBinding6 = this$0.p;
            if (sensAiHomeActivityBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                sensAiHomeActivityBinding6 = null;
            }
            sensAiHomeActivityBinding6.viewPager.setAdapter(this$0.q);
            SensAiHomeActivityBinding sensAiHomeActivityBinding7 = this$0.p;
            if (sensAiHomeActivityBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                sensAiHomeActivityBinding7 = null;
            }
            sensAiHomeActivityBinding7.viewPager.setOffscreenPageLimit(2);
            SensAiHomeActivityBinding sensAiHomeActivityBinding8 = this$0.p;
            if (sensAiHomeActivityBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                sensAiHomeActivityBinding8 = null;
            }
            TabLayout tabLayout2 = sensAiHomeActivityBinding8.tabLayout;
            SensAiHomeActivityBinding sensAiHomeActivityBinding9 = this$0.p;
            if (sensAiHomeActivityBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                sensAiHomeActivityBinding = sensAiHomeActivityBinding9;
            }
            tabLayout2.setupWithViewPager(sensAiHomeActivityBinding.viewPager);
            this$0.x();
            return;
        }
        PagerAdapterSensAIHome pagerAdapterSensAIHome6 = this$0.q;
        FragmentOverallStats fragmentOverallStats3 = new FragmentOverallStats();
        String string6 = this$0.getString(R.string.overall_stats);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.overall_stats)");
        pagerAdapterSensAIHome6.addFragment(fragmentOverallStats3, string6);
        PagerAdapterSensAIHome pagerAdapterSensAIHome7 = this$0.q;
        FragmentSessionInsights fragmentSessionInsights3 = new FragmentSessionInsights();
        String string7 = this$0.getString(R.string.session_details);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.session_details)");
        pagerAdapterSensAIHome7.addFragment(fragmentSessionInsights3, string7);
        SensAiHomeActivityBinding sensAiHomeActivityBinding10 = this$0.p;
        if (sensAiHomeActivityBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding10 = null;
        }
        sensAiHomeActivityBinding10.viewPager.setAdapter(this$0.q);
        SensAiHomeActivityBinding sensAiHomeActivityBinding11 = this$0.p;
        if (sensAiHomeActivityBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding11 = null;
        }
        sensAiHomeActivityBinding11.viewPager.setOffscreenPageLimit(2);
        SensAiHomeActivityBinding sensAiHomeActivityBinding12 = this$0.p;
        if (sensAiHomeActivityBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding12 = null;
        }
        TabLayout tabLayout3 = sensAiHomeActivityBinding12.tabLayout;
        SensAiHomeActivityBinding sensAiHomeActivityBinding13 = this$0.p;
        if (sensAiHomeActivityBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sensAiHomeActivityBinding = sensAiHomeActivityBinding13;
        }
        tabLayout3.setupWithViewPager(sensAiHomeActivityBinding.viewPager);
        this$0.x();
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
    public final PagerAdapterSensAIHome getAdapter() {
        return this.q;
    }

    public final void init() {
        v();
    }

    public final void initToolbar() {
        SensAiHomeActivityBinding sensAiHomeActivityBinding = this.p;
        SensAiHomeActivityBinding sensAiHomeActivityBinding2 = null;
        if (sensAiHomeActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding = null;
        }
        TextView textView = (TextView) sensAiHomeActivityBinding.toolbar.findViewById(R.id.toolbar_title);
        SensAiHomeActivityBinding sensAiHomeActivityBinding3 = this.p;
        if (sensAiHomeActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding3 = null;
        }
        TextView textView2 = (TextView) sensAiHomeActivityBinding3.toolbar.findViewById(R.id.toolbar_back_arrow);
        SensAiHomeActivityBinding sensAiHomeActivityBinding4 = this.p;
        if (sensAiHomeActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sensAiHomeActivityBinding2 = sensAiHomeActivityBinding4;
        }
        final ImageView imageView = (ImageView) sensAiHomeActivityBinding2.toolbar.findViewById(R.id.share_iv);
        textView.setText(getString(R.string.sens_ai));
        imageView.setVisibility(0);
        GlideUtils.loadScaledImage(this, SessionManager.getInstance(this).getUserDetails().getDpUrl(), new SimpleTarget<Bitmap>() { // from class: com.coveiot.android.leonardo.sensai.activity.ActivitySensAIHomeNew$initToolbar$1
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(@NotNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                if (imageView != null) {
                    Bitmap circleBitmap = AppUtils.getCircleBitmap(resource);
                    if (circleBitmap != null) {
                        imageView.setImageBitmap(circleBitmap);
                    } else {
                        imageView.setImageDrawable(this.getDrawable(2131231665));
                    }
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIHomeNew.t(ActivitySensAIHomeNew.this, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.sensai.activity.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivitySensAIHomeNew.u(ActivitySensAIHomeNew.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SensAiHomeActivityBinding inflate = SensAiHomeActivityBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        init();
    }

    public final void setAdapter(@NotNull PagerAdapterSensAIHome pagerAdapterSensAIHome) {
        Intrinsics.checkNotNullParameter(pagerAdapterSensAIHome, "<set-?>");
        this.q = pagerAdapterSensAIHome;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    public final boolean v() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfig, "getInstance()");
        objectRef.element = firebaseRemoteConfig;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0L).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .s…s(0)\n            .build()");
        ((FirebaseRemoteConfig) objectRef.element).setConfigSettingsAsync(build);
        ((FirebaseRemoteConfig) objectRef.element).fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener() { // from class: com.coveiot.android.leonardo.sensai.activity.p
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivitySensAIHomeNew.w(Ref.ObjectRef.this, this, task);
            }
        });
        return this.r;
    }

    public final void x() {
        SensAiHomeActivityBinding sensAiHomeActivityBinding = null;
        View inflate = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.tab_title);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.tab_badge);
        Intrinsics.checkNotNull(findViewById2, "null cannot be cast to non-null type android.widget.TextView");
        textView.setText(getResources().getString(R.string.overall_stats));
        textView.setTextAppearance(R.style.selectedTabFont);
        textView.setTextColor(ContextCompat.getColor(this, R.color.main_text_color));
        ((TextView) findViewById2).setVisibility(8);
        SensAiHomeActivityBinding sensAiHomeActivityBinding2 = this.p;
        if (sensAiHomeActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding2 = null;
        }
        TabLayout.Tab tabAt = sensAiHomeActivityBinding2.tabLayout.getTabAt(0);
        if (tabAt != null) {
            tabAt.setCustomView(inflate);
        }
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        View findViewById3 = inflate2.findViewById(R.id.tab_title);
        Intrinsics.checkNotNull(findViewById3, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView2 = (TextView) findViewById3;
        View findViewById4 = inflate2.findViewById(R.id.tab_badge);
        Intrinsics.checkNotNull(findViewById4, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById4).setVisibility(8);
        textView2.setTextAppearance(R.style.unSelectedTabFontOnBoarding);
        textView2.setText(getResources().getString(R.string.session_details));
        SensAiHomeActivityBinding sensAiHomeActivityBinding3 = this.p;
        if (sensAiHomeActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding3 = null;
        }
        TabLayout.Tab tabAt2 = sensAiHomeActivityBinding3.tabLayout.getTabAt(1);
        if (tabAt2 != null) {
            tabAt2.setCustomView(inflate2);
        }
        View inflate3 = LayoutInflater.from(this).inflate(R.layout.custom_tab, (ViewGroup) null);
        View findViewById5 = inflate3.findViewById(R.id.tab_title);
        Intrinsics.checkNotNull(findViewById5, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView3 = (TextView) findViewById5;
        View findViewById6 = inflate3.findViewById(R.id.tab_badge);
        Intrinsics.checkNotNull(findViewById6, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById6).setVisibility(8);
        textView3.setTextAppearance(R.style.unSelectedTabFontOnBoarding);
        textView3.setText(getResources().getString(R.string.sens_ai_coach));
        SensAiHomeActivityBinding sensAiHomeActivityBinding4 = this.p;
        if (sensAiHomeActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            sensAiHomeActivityBinding4 = null;
        }
        TabLayout.Tab tabAt3 = sensAiHomeActivityBinding4.tabLayout.getTabAt(2);
        if (tabAt3 != null) {
            tabAt3.setCustomView(inflate3);
        }
        SensAiHomeActivityBinding sensAiHomeActivityBinding5 = this.p;
        if (sensAiHomeActivityBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            sensAiHomeActivityBinding = sensAiHomeActivityBinding5;
        }
        sensAiHomeActivityBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.coveiot.android.leonardo.sensai.activity.ActivitySensAIHomeNew$setUpTabLayout$1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById7 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
                TextView textView4 = (TextView) findViewById7;
                textView4.setTextColor(ContextCompat.getColor(ActivitySensAIHomeNew.this, R.color.main_text_color));
                textView4.setTextAppearance(R.style.selectedTabFont);
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(@NotNull TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                View customView = tab.getCustomView();
                Intrinsics.checkNotNull(customView);
                View findViewById7 = customView.findViewById(R.id.tab_title);
                Intrinsics.checkNotNull(findViewById7, "null cannot be cast to non-null type android.widget.TextView");
                ((TextView) findViewById7).setTextAppearance(R.style.unSelectedTabFontOnBoarding);
            }
        });
    }
}
