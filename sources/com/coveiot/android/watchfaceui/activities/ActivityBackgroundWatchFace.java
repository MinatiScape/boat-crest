package com.coveiot.android.watchfaceui.activities;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageWatchFace;
import com.coveiot.android.theme.utils.FragmentUtilsKt;
import com.coveiot.android.theme.utils.ReviewAndRateUsConstants;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.databinding.ActivityBackgroundWatchFaceBinding;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundCA;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundCZ2;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundEastApex;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundIDO;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMatrix;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMoyang;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundSMA;
import com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.utils.ViewUtilsKt;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityBackgroundWatchFace extends BaseActivity implements OnSuccessListener, OnFailureListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityBackgroundWatchFaceBinding p;
    public ActivityWatchFaceViewModel q;
    public WatchFaceLayoutViewModel r;
    public WatchFaceDefaultViewModel s;
    public WatchFaceBackgroundViewModel t;
    public ProgressBar u;
    public TextView v;
    public TextView w;
    @Nullable
    public BottomSheetDialog x;
    public boolean y;

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<View, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull View it) {
            Intrinsics.checkNotNullParameter(it, "it");
            ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = ActivityBackgroundWatchFace.this.p;
            if (activityBackgroundWatchFaceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundWatchFaceBinding = null;
            }
            activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
            for (Fragment fragment : ActivityBackgroundWatchFace.this.getSupportFragmentManager().getFragments()) {
                if (fragment instanceof OnClickListener) {
                    ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = ActivityBackgroundWatchFace.this.p;
                    if (activityBackgroundWatchFaceBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityBackgroundWatchFaceBinding2 = null;
                    }
                    activityBackgroundWatchFaceBinding2.btnSave.setEnabled(false);
                    ((OnClickListener) fragment).onSaveClicked();
                }
            }
        }
    }

    public static final void A(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityBackgroundWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.setResult(this$0.y ? -1 : 0);
        this$0.finish();
    }

    public static final void D(ActivityBackgroundWatchFace this$0, ProgressBean progressBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String title = progressBean.getTitle();
        TextView textView = null;
        if (title != null) {
            TextView textView2 = this$0.v;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressTitleTv");
                textView2 = null;
            }
            textView2.setText(title);
        }
        if (progressBean.getVisible()) {
            BottomSheetDialog bottomSheetDialog = this$0.x;
            if (bottomSheetDialog != null) {
                Intrinsics.checkNotNull(bottomSheetDialog);
                if (!bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = this$0.x;
                    Intrinsics.checkNotNull(bottomSheetDialog2);
                    bottomSheetDialog2.show();
                }
            }
            ProgressBar progressBar = this$0.u;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pb");
                progressBar = null;
            }
            progressBar.setProgress(progressBean.getProgress());
            TextView textView3 = this$0.w;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressValue");
            } else {
                textView = textView3;
            }
            textView.setText(progressBean.getProgress() + " %");
            return;
        }
        BottomSheetDialog bottomSheetDialog3 = this$0.x;
        if (bottomSheetDialog3 != null) {
            Intrinsics.checkNotNull(bottomSheetDialog3);
            if (bottomSheetDialog3.isShowing()) {
                BottomSheetDialog bottomSheetDialog4 = this$0.x;
                Intrinsics.checkNotNull(bottomSheetDialog4);
                bottomSheetDialog4.dismiss();
            }
        }
    }

    public static final void E(ActivityBackgroundWatchFace this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(this$0.y ? -1 : 0);
        this$0.finish();
    }

    public static final void F(ActivityBackgroundWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (it.booleanValue()) {
            ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this$0.p;
            if (activityBackgroundWatchFaceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding2;
            }
            activityBackgroundWatchFaceBinding.btnSave.setEnabled(true);
            return;
        }
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding3 = this$0.p;
        if (activityBackgroundWatchFaceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding3;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
    }

    public static final void J(ActivityBackgroundWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void L(ActivityBackgroundWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (it.booleanValue()) {
            ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this$0.p;
            if (activityBackgroundWatchFaceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding2;
            }
            activityBackgroundWatchFaceBinding.btnSave.setEnabled(true);
            return;
        }
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding3 = this$0.p;
        if (activityBackgroundWatchFaceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding3;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
    }

    public static final void M(ActivityBackgroundWatchFace this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0, str, 1).show();
    }

    public static final void P(BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace, ActivityBackgroundWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessageWatchFace, "$bottomSheetDialogImageTitleMessageWatchFace");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessageWatchFace.dismiss();
        this$0.O();
        if (SessionManager.getInstance(this$0).isRateUsDialogShown()) {
            return;
        }
        this$0.N();
    }

    public static final void z(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityBackgroundWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this$0.p;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        activityBackgroundWatchFaceBinding.btnSave.performClick();
    }

    public final void B() {
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.q;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.getDisplayWatchFacePositionFromWatch();
    }

    public final void C() {
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …undViewModel::class.java)");
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = (WatchFaceBackgroundViewModel) viewModel;
        this.t = watchFaceBackgroundViewModel;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = null;
        if (watchFaceBackgroundViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel = null;
        }
        watchFaceBackgroundViewModel.setViewModelOnSuccessListener(this);
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.t;
        if (watchFaceBackgroundViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel3 = null;
        }
        watchFaceBackgroundViewModel3.setViewModelOnFailureListener(this);
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.t;
        if (watchFaceBackgroundViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel4 = null;
        }
        watchFaceBackgroundViewModel4.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBackgroundWatchFace.D(ActivityBackgroundWatchFace.this, (ProgressBean) obj);
            }
        });
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.t;
        if (watchFaceBackgroundViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel5 = null;
        }
        watchFaceBackgroundViewModel5.isUploadToWatchCanceled().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.f
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBackgroundWatchFace.E(ActivityBackgroundWatchFace.this, (Boolean) obj);
            }
        });
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this.t;
        if (watchFaceBackgroundViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel2 = watchFaceBackgroundViewModel6;
        }
        watchFaceBackgroundViewModel2.getSaveEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.h
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBackgroundWatchFace.F(ActivityBackgroundWatchFace.this, (Boolean) obj);
            }
        });
    }

    public final void G() {
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceDefaultViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …ultViewModel::class.java)");
        WatchFaceDefaultViewModel watchFaceDefaultViewModel = (WatchFaceDefaultViewModel) viewModel;
        this.s = watchFaceDefaultViewModel;
        WatchFaceDefaultViewModel watchFaceDefaultViewModel2 = null;
        if (watchFaceDefaultViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            watchFaceDefaultViewModel = null;
        }
        watchFaceDefaultViewModel.setViewModelOnSuccessListener(this);
        WatchFaceDefaultViewModel watchFaceDefaultViewModel3 = this.s;
        if (watchFaceDefaultViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
        } else {
            watchFaceDefaultViewModel2 = watchFaceDefaultViewModel3;
        }
        watchFaceDefaultViewModel2.setViewModelOnFailureListener(this);
    }

    public final void H() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogTheme);
        this.x = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.x;
        Intrinsics.checkNotNull(bottomSheetDialog2);
        bottomSheetDialog2.setContentView(R.layout.dialog_uploading_watchface);
        BottomSheetDialog bottomSheetDialog3 = this.x;
        Intrinsics.checkNotNull(bottomSheetDialog3);
        bottomSheetDialog3.setCancelable(false);
        BottomSheetDialog bottomSheetDialog4 = this.x;
        Intrinsics.checkNotNull(bottomSheetDialog4);
        View findViewById = bottomSheetDialog4.findViewById(R.id.tv_progress_title);
        Intrinsics.checkNotNull(findViewById);
        this.v = (TextView) findViewById;
        BottomSheetDialog bottomSheetDialog5 = this.x;
        Intrinsics.checkNotNull(bottomSheetDialog5);
        View findViewById2 = bottomSheetDialog5.findViewById(R.id.progress_value);
        Intrinsics.checkNotNull(findViewById2);
        this.w = (TextView) findViewById2;
        BottomSheetDialog bottomSheetDialog6 = this.x;
        Intrinsics.checkNotNull(bottomSheetDialog6);
        View findViewById3 = bottomSheetDialog6.findViewById(R.id.progress_update);
        Intrinsics.checkNotNull(findViewById3);
        this.u = (ProgressBar) findViewById3;
    }

    public final void I() {
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = null;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        TextView textView = (TextView) activityBackgroundWatchFaceBinding.toolbar.findViewById(R.id.toolbar_back_arrow);
        textView.setText(getString(R.string.customise_your_watch_face));
        textView.setTextAppearance(R.style.semi_bold);
        textView.setTextSize(20.0f);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBackgroundWatchFace.J(ActivityBackgroundWatchFace.this, view);
            }
        });
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding3 = this.p;
        if (activityBackgroundWatchFaceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding2 = activityBackgroundWatchFaceBinding3;
        }
        ((TextView) activityBackgroundWatchFaceBinding2.toolbar.findViewById(R.id.toolbar_title)).setVisibility(8);
    }

    public final void K() {
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceLayoutViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …outViewModel::class.java)");
        WatchFaceLayoutViewModel watchFaceLayoutViewModel = (WatchFaceLayoutViewModel) viewModel;
        this.r = watchFaceLayoutViewModel;
        WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = null;
        if (watchFaceLayoutViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            watchFaceLayoutViewModel = null;
        }
        watchFaceLayoutViewModel.setViewModelOnSuccessListener(this);
        WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this.r;
        if (watchFaceLayoutViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            watchFaceLayoutViewModel3 = null;
        }
        watchFaceLayoutViewModel3.setViewModelOnFailureListener(this);
        WatchFaceLayoutViewModel watchFaceLayoutViewModel4 = this.r;
        if (watchFaceLayoutViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
        } else {
            watchFaceLayoutViewModel2 = watchFaceLayoutViewModel4;
        }
        watchFaceLayoutViewModel2.getSaveEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityBackgroundWatchFace.L(ActivityBackgroundWatchFace.this, (Boolean) obj);
            }
        });
    }

    public final void N() {
        Bundle bundle = new Bundle();
        bundle.putString("cv_prev_screen_name", ReviewAndRateUsConstants.CREATE_CUSTOM_WATCHFACE.getValue());
        DialogFragment externalModuleDialogFragment = FragmentUtilsKt.getExternalModuleDialogFragment("com.coveiot.android.leonardo.rateus.RateUsDialogFragment");
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.setArguments(bundle);
        }
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.show(getSupportFragmentManager(), ActivityBackgroundWatchFace.class.getSimpleName());
        }
    }

    public final void O() {
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
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

    public final void confirmationDialog() {
        Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
        String string = getString(R.string.confirmation);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
        String string2 = getString(R.string.not_applied_changes);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.not_applied_changes)");
        final BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessageTwoBtns = new BottomSheetDialogImageTitleMessageTwoBtns(this, drawable, string, string2, false);
        String string3 = getString(R.string.yes);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.yes)");
        bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBackgroundWatchFace.z(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        String string4 = getString(R.string.f6117no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogImageTitleMessageTwoBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBackgroundWatchFace.A(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageTwoBtns.show();
    }

    public final void initFragment() {
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (companion.isCADevice(this)) {
            FragmentWatchFaceBackgroundCA fragmentWatchFaceBackgroundCA = new FragmentWatchFaceBackgroundCA();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
            if (activityBackgroundWatchFaceBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityBackgroundWatchFaceBinding = null;
            }
            beginTransaction.replace(activityBackgroundWatchFaceBinding.watchfaceBackground.getId(), fragmentWatchFaceBackgroundCA).commit();
        } else if (!companion.isCYDevice(this) && !companion.isPS1Device(this) && !companion.isCZDevice(this) && !companion.isBESDevice(this)) {
            if (companion.isIDODevice(this)) {
                FragmentWatchFaceBackgroundIDO fragmentWatchFaceBackgroundIDO = new FragmentWatchFaceBackgroundIDO();
                FragmentManager supportFragmentManager2 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager2, "supportFragmentManager");
                supportFragmentManager2.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundIDO).commit();
            } else if (companion.isMatrixDevice(this)) {
                FragmentWatchFaceBackgroundMatrix fragmentWatchFaceBackgroundMatrix = new FragmentWatchFaceBackgroundMatrix();
                FragmentManager supportFragmentManager3 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager3, "supportFragmentManager");
                supportFragmentManager3.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundMatrix).commit();
            } else if (companion.isMoyangDevice(this)) {
                FragmentWatchFaceBackgroundMoyang fragmentWatchFaceBackgroundMoyang = new FragmentWatchFaceBackgroundMoyang();
                FragmentManager supportFragmentManager4 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager4, "supportFragmentManager");
                supportFragmentManager4.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundMoyang).commit();
            } else if (companion.isSmaDevice(this)) {
                FragmentWatchFaceBackgroundSMA fragmentWatchFaceBackgroundSMA = new FragmentWatchFaceBackgroundSMA();
                FragmentManager supportFragmentManager5 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager5, "supportFragmentManager");
                supportFragmentManager5.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundSMA).commit();
            } else if (companion.isTouchELXDevice(this)) {
                FragmentWatchFaceBackgroundTouchElx fragmentWatchFaceBackgroundTouchElx = new FragmentWatchFaceBackgroundTouchElx();
                FragmentManager supportFragmentManager6 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager6, "supportFragmentManager");
                supportFragmentManager6.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundTouchElx).commit();
            } else if (companion.isEastApexDevice(this)) {
                FragmentWatchFaceBackgroundEastApex fragmentWatchFaceBackgroundEastApex = new FragmentWatchFaceBackgroundEastApex();
                FragmentManager supportFragmentManager7 = getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager7, "supportFragmentManager");
                supportFragmentManager7.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundEastApex).commit();
            }
        } else {
            FragmentWatchFaceBackgroundCZ2 fragmentWatchFaceBackgroundCZ2 = new FragmentWatchFaceBackgroundCZ2();
            FragmentManager supportFragmentManager8 = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager8, "supportFragmentManager");
            supportFragmentManager8.beginTransaction().replace(R.id.watchfaceBackground, fragmentWatchFaceBackgroundCZ2).commit();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        if (activityBackgroundWatchFaceBinding.btnSave.isEnabled()) {
            confirmationDialog();
            return;
        }
        setResult(this.y ? -1 : 0);
        finish();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onBackgroundWatchFaceSuccess(boolean z) {
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (DeviceUtils.Companion.isMoyangDevice(this)) {
            WatchFaceDefaultViewModel watchFaceDefaultViewModel = this.s;
            if (watchFaceDefaultViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                watchFaceDefaultViewModel = null;
            }
            watchFaceDefaultViewModel.setDefaultWatchFacePosition(4);
            WatchFaceDefaultViewModel watchFaceDefaultViewModel2 = this.s;
            if (watchFaceDefaultViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                watchFaceDefaultViewModel2 = null;
            }
            watchFaceDefaultViewModel2.changeDefaultWatchFace();
            ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this.p;
            if (activityBackgroundWatchFaceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding2;
            }
            activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
            return;
        }
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        companion.getInstance(this).saveSelectedDiyWatchFace(null);
        companion.getInstance(this).saveSelectedCloudWatchFace(null);
        showSuccessDialog();
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding3 = this.p;
        if (activityBackgroundWatchFaceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding3;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCloudWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityBackgroundWatchFaceBinding inflate = ActivityBackgroundWatchFaceBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this.p;
        if (activityBackgroundWatchFaceBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding2 = null;
        }
        activityBackgroundWatchFaceBinding2.btnSave.setEnabled(false);
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …aceViewModel::class.java)");
        ActivityWatchFaceViewModel activityWatchFaceViewModel = (ActivityWatchFaceViewModel) viewModel;
        this.q = activityWatchFaceViewModel;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setViewModelOnSuccessListener(this);
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.q;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel2 = null;
        }
        activityWatchFaceViewModel2.setViewModelOnFailureListener(this);
        getSupportFragmentManager();
        I();
        initFragment();
        C();
        H();
        B();
        K();
        G();
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding3 = this.p;
        if (activityBackgroundWatchFaceBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding3;
        }
        Button button = activityBackgroundWatchFaceBinding.btnSave;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnSave");
        ViewUtilsKt.setSafeOnClickListener(button, new a());
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCurrentWatchFetchPositionFetchSuccess(int i) {
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(false);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDefaultWatchFaceSuccess(boolean z) {
        showSuccessDialog();
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = this.p;
        if (activityBackgroundWatchFaceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityBackgroundWatchFaceBinding = null;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(true);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDiyWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnFailureListener
    public void onFailure(@Nullable final String str) {
        BottomSheetDialog bottomSheetDialog;
        if (isFinishing()) {
            return;
        }
        if (str != null) {
            if (str.length() > 0) {
                runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.activities.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityBackgroundWatchFace.M(ActivityBackgroundWatchFace.this, str);
                    }
                });
            }
        }
        BottomSheetDialog bottomSheetDialog2 = this.x;
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (bottomSheetDialog2 != null) {
            Boolean valueOf = bottomSheetDialog2 != null ? Boolean.valueOf(bottomSheetDialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (bottomSheetDialog = this.x) != null) {
                bottomSheetDialog.dismiss();
            }
        }
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this.p;
        if (activityBackgroundWatchFaceBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding2;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(true);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onLayoutWatchFaceSuccess(boolean z) {
        Unit unit;
        Unit unit2;
        WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.r;
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding = null;
        if (watchFaceLayoutViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            watchFaceLayoutViewModel = null;
        }
        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel.getWatchFaceLayoutInfoFromWatch();
        if (watchFaceLayoutInfoFromWatch != null) {
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.t;
            if (watchFaceBackgroundViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                watchFaceBackgroundViewModel = null;
            }
            Uri selectedBackgroundImageUri = watchFaceBackgroundViewModel.getSelectedBackgroundImageUri();
            if (selectedBackgroundImageUri != null) {
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.t;
                if (watchFaceBackgroundViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel2 = null;
                }
                watchFaceBackgroundViewModel2.sendWatchFaceBackgroundToWatch(watchFaceLayoutInfoFromWatch, selectedBackgroundImageUri);
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            if (unit2 == null) {
                WatchFaceDefaultViewModel watchFaceDefaultViewModel = this.s;
                if (watchFaceDefaultViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                    watchFaceDefaultViewModel = null;
                }
                watchFaceDefaultViewModel.setDefaultWatchFacePosition(4);
                WatchFaceDefaultViewModel watchFaceDefaultViewModel2 = this.s;
                if (watchFaceDefaultViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                    watchFaceDefaultViewModel2 = null;
                }
                watchFaceDefaultViewModel2.changeDefaultWatchFace();
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            WatchFaceDefaultViewModel watchFaceDefaultViewModel3 = this.s;
            if (watchFaceDefaultViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                watchFaceDefaultViewModel3 = null;
            }
            watchFaceDefaultViewModel3.setDefaultWatchFacePosition(4);
            WatchFaceDefaultViewModel watchFaceDefaultViewModel4 = this.s;
            if (watchFaceDefaultViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
                watchFaceDefaultViewModel4 = null;
            }
            watchFaceDefaultViewModel4.changeDefaultWatchFace();
        }
        ActivityBackgroundWatchFaceBinding activityBackgroundWatchFaceBinding2 = this.p;
        if (activityBackgroundWatchFaceBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityBackgroundWatchFaceBinding = activityBackgroundWatchFaceBinding2;
        }
        activityBackgroundWatchFaceBinding.btnSave.setEnabled(true);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onSuccess(boolean z) {
        showSuccessDialog();
    }

    public final void showSuccessDialog() {
        this.y = true;
        Drawable drawable = getResources().getDrawable(R.drawable.success_image_new_img);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
        String string = getString(R.string.success_applied);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_applied)");
        final BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace = new BottomSheetDialogImageTitleMessageWatchFace(this, drawable, string, "");
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogImageTitleMessageWatchFace.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBackgroundWatchFace.P(BottomSheetDialogImageTitleMessageWatchFace.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageWatchFace.setCancelable(false);
        bottomSheetDialogImageTitleMessageWatchFace.show();
    }
}
