package com.coveiot.android.watchfaceui.activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.Glide;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtns;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageWatchFace;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.android.theme.utils.FragmentUtilsKt;
import com.coveiot.android.theme.utils.ReviewAndRateUsConstants;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfacecore.model.WatchFaceBean;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.adapter.SectionsPagerAdapterWatchFace;
import com.coveiot.android.watchfaceui.databinding.ActivityWatchFaceCanewBinding;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.listener.OnFailureListener;
import com.coveiot.android.watchfaceui.listener.OnSuccessListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceCloudViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDefaultViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceDiyViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class ActivityWatchFace extends BaseActivity implements OnSuccessListener, OnFailureListener {
    public boolean A;
    @Nullable
    public WatchFacePreferenceManager B;
    @Nullable
    public String C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityWatchFaceCanewBinding p;
    @Nullable
    public FragmentManager q;
    @Nullable
    public SectionsPagerAdapterWatchFace r;
    public WatchFaceDefaultViewModel s;
    public WatchFaceDiyViewModel t;
    public WatchFaceCloudViewModel u;
    public ActivityWatchFaceViewModel v;
    public ProgressBar w;
    public TextView x;
    public TextView y;
    @Nullable
    public BottomSheetDialog z;

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
            for (Fragment fragment : ActivityWatchFace.this.getSupportFragmentManager().getFragments()) {
                if (fragment instanceof OnClickListener) {
                    SectionsPagerAdapterWatchFace sectionsPagerAdapterWatchFace = ActivityWatchFace.this.r;
                    Intrinsics.checkNotNull(sectionsPagerAdapterWatchFace);
                    if (Intrinsics.areEqual(fragment, sectionsPagerAdapterWatchFace.getCurrentFragment())) {
                        ((OnClickListener) fragment).onSaveClicked();
                    }
                }
            }
        }
    }

    public static final void I(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this$0.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        activityWatchFaceCanewBinding.applyToWatch.performClick();
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this$0));
        hashMap.putAll(companion.getWatchDetails(this$0));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.WF_EXIT.getValue(), hashMap);
    }

    public static final void J(BottomSheetDialogImageTitleMessageTwoBtns bottomSheetDialogImageTitleMessage, ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void N(ActivityWatchFace this$0, ProgressBean progressBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String title = progressBean.getTitle();
        TextView textView = null;
        if (title != null) {
            TextView textView2 = this$0.x;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressTitleTv");
                textView2 = null;
            }
            textView2.setText(title);
        }
        if (progressBean.getVisible()) {
            BottomSheetDialog bottomSheetDialog = this$0.z;
            if (bottomSheetDialog != null) {
                Intrinsics.checkNotNull(bottomSheetDialog);
                if (!bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = this$0.z;
                    Intrinsics.checkNotNull(bottomSheetDialog2);
                    bottomSheetDialog2.show();
                }
            }
            ProgressBar progressBar = this$0.w;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pb");
                progressBar = null;
            }
            progressBar.setProgress(progressBean.getProgress());
            TextView textView3 = this$0.y;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressValue");
            } else {
                textView = textView3;
            }
            textView.setText(progressBean.getProgress() + " %");
            return;
        }
        BottomSheetDialog bottomSheetDialog3 = this$0.z;
        if (bottomSheetDialog3 != null) {
            Intrinsics.checkNotNull(bottomSheetDialog3);
            if (bottomSheetDialog3.isShowing()) {
                BottomSheetDialog bottomSheetDialog4 = this$0.z;
                Intrinsics.checkNotNull(bottomSheetDialog4);
                bottomSheetDialog4.dismiss();
            }
        }
    }

    public static final void O(ActivityWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (it.booleanValue()) {
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this$0.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding2;
            }
            activityWatchFaceCanewBinding.applyToWatch.setEnabled(true);
            return;
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this$0.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding = activityWatchFaceCanewBinding3;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(false);
    }

    public static final void P(ActivityWatchFace this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void R(ActivityWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (it.booleanValue()) {
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this$0.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding2;
            }
            activityWatchFaceCanewBinding.applyToWatch.setEnabled(true);
            return;
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this$0.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding = activityWatchFaceCanewBinding3;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(false);
    }

    public static final void S(ActivityWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (it.booleanValue()) {
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this$0.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding2;
            }
            activityWatchFaceCanewBinding.applyToWatch.setEnabled(true);
            return;
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this$0.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding = activityWatchFaceCanewBinding3;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(false);
    }

    public static final void T(ActivityWatchFace this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            this$0.D = true;
        } else {
            this$0.D = false;
        }
    }

    public static final void U(ActivityWatchFace this$0, ProgressBean progressBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String title = progressBean.getTitle();
        TextView textView = null;
        if (title != null) {
            TextView textView2 = this$0.x;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressTitleTv");
                textView2 = null;
            }
            textView2.setText(title);
        }
        if (progressBean.getVisible()) {
            BottomSheetDialog bottomSheetDialog = this$0.z;
            if (bottomSheetDialog != null) {
                Intrinsics.checkNotNull(bottomSheetDialog);
                if (!bottomSheetDialog.isShowing()) {
                    BottomSheetDialog bottomSheetDialog2 = this$0.z;
                    Intrinsics.checkNotNull(bottomSheetDialog2);
                    bottomSheetDialog2.show();
                }
            }
            ProgressBar progressBar = this$0.w;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pb");
                progressBar = null;
            }
            progressBar.setProgress(progressBean.getProgress());
            TextView textView3 = this$0.y;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressValue");
            } else {
                textView = textView3;
            }
            textView.setText(progressBean.getProgress() + " %");
            return;
        }
        BottomSheetDialog bottomSheetDialog3 = this$0.z;
        if (bottomSheetDialog3 != null) {
            Intrinsics.checkNotNull(bottomSheetDialog3);
            if (bottomSheetDialog3.isShowing()) {
                BottomSheetDialog bottomSheetDialog4 = this$0.z;
                Intrinsics.checkNotNull(bottomSheetDialog4);
                bottomSheetDialog4.dismiss();
            }
        }
    }

    public static final void V(ActivityWatchFace this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public static final void Y(ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void a0(ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(new Intent(this$0, ActivityBackgroundWatchFace.class), 123);
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this$0.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(false);
    }

    public static final void d0(Ref.ObjectRef mFirebaseRemoteConfig, ActivityWatchFace this$0, Task task) {
        Intrinsics.checkNotNullParameter(mFirebaseRemoteConfig, "$mFirebaseRemoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Boolean bool = (Boolean) task.getResult();
            this$0.E = ((FirebaseRemoteConfig) mFirebaseRemoteConfig.element).getBoolean("shouldShowWatchFaceEditBtn");
            this$0.Z();
            return;
        }
        this$0.E = false;
    }

    public static final void e0(ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this$0.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        TextView textView = activityWatchFaceCanewBinding.tvToolTip;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTip");
        this$0.gone(textView);
    }

    public static final void f0(ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this$0.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        TextView textView = activityWatchFaceCanewBinding.tvToolTip;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvToolTip");
        this$0.gone(textView);
    }

    public static final void g0(ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            if (this$0.D) {
                return;
            }
            Toast.makeText(this$0, this$0.getString(R.string.cannot_edit_applied_watchface), 1).show();
            return;
        }
        Toast.makeText(this$0, this$0.getString(R.string.no_internet_connection), 0).show();
    }

    public static final void h0(ActivityWatchFace this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isBluetoothEnabled(this$0)) {
            Toast.makeText(this$0, R.string.bluetooth_off_message, 0).show();
        } else {
            Toast.makeText(this$0, str, 1).show();
        }
    }

    public static final void m0(BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace, ActivityWatchFace this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessageWatchFace, "$bottomSheetDialogImageTitleMessageWatchFace");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogImageTitleMessageWatchFace.dismiss();
        this$0.j0();
        if (SessionManager.getInstance(this$0).isRateUsDialogShown()) {
            return;
        }
        this$0.i0();
    }

    public final void K() {
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.v;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.getDisplayWatchFacePositionFromWatch();
    }

    public final ImageView L() {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (DeviceUtils.Companion.isRoundWatch(this)) {
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding2;
            }
            return activityWatchFaceCanewBinding.selectedRoundedWatchFace;
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding = activityWatchFaceCanewBinding3;
        }
        return activityWatchFaceCanewBinding.selectedWatchFace;
    }

    public final void M() {
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceCloudViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …oudViewModel::class.java)");
        WatchFaceCloudViewModel watchFaceCloudViewModel = (WatchFaceCloudViewModel) viewModel;
        this.u = watchFaceCloudViewModel;
        WatchFaceCloudViewModel watchFaceCloudViewModel2 = null;
        if (watchFaceCloudViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel = null;
        }
        watchFaceCloudViewModel.setViewModelOnSuccessListener(this);
        WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.u;
        if (watchFaceCloudViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel3 = null;
        }
        watchFaceCloudViewModel3.setViewModelOnFailureListener(this);
        WatchFaceCloudViewModel watchFaceCloudViewModel4 = this.u;
        if (watchFaceCloudViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel4 = null;
        }
        watchFaceCloudViewModel4.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.z
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.N(ActivityWatchFace.this, (ProgressBean) obj);
            }
        });
        WatchFaceCloudViewModel watchFaceCloudViewModel5 = this.u;
        if (watchFaceCloudViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel5 = null;
        }
        watchFaceCloudViewModel5.getSaveEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.O(ActivityWatchFace.this, (Boolean) obj);
            }
        });
        WatchFaceCloudViewModel watchFaceCloudViewModel6 = this.u;
        if (watchFaceCloudViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
        } else {
            watchFaceCloudViewModel2 = watchFaceCloudViewModel6;
        }
        watchFaceCloudViewModel2.isUploadToWatchCanceled().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.P(ActivityWatchFace.this, (Boolean) obj);
            }
        });
    }

    public final void Q() {
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
            watchFaceDefaultViewModel3 = null;
        }
        watchFaceDefaultViewModel3.setViewModelOnFailureListener(this);
        WatchFaceDefaultViewModel watchFaceDefaultViewModel4 = this.s;
        if (watchFaceDefaultViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
        } else {
            watchFaceDefaultViewModel2 = watchFaceDefaultViewModel4;
        }
        watchFaceDefaultViewModel2.getSaveEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.o
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.R(ActivityWatchFace.this, (Boolean) obj);
            }
        });
    }

    public final void W() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.DialogTheme);
        this.z = bottomSheetDialog;
        Intrinsics.checkNotNull(bottomSheetDialog);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.requestFeature(1);
        BottomSheetDialog bottomSheetDialog2 = this.z;
        Intrinsics.checkNotNull(bottomSheetDialog2);
        bottomSheetDialog2.setContentView(R.layout.dialog_uploading_watchface);
        BottomSheetDialog bottomSheetDialog3 = this.z;
        Intrinsics.checkNotNull(bottomSheetDialog3);
        bottomSheetDialog3.setCancelable(false);
        BottomSheetDialog bottomSheetDialog4 = this.z;
        Intrinsics.checkNotNull(bottomSheetDialog4);
        View findViewById = bottomSheetDialog4.findViewById(R.id.tv_progress_title);
        Intrinsics.checkNotNull(findViewById);
        this.x = (TextView) findViewById;
        BottomSheetDialog bottomSheetDialog5 = this.z;
        Intrinsics.checkNotNull(bottomSheetDialog5);
        View findViewById2 = bottomSheetDialog5.findViewById(R.id.progress_value);
        Intrinsics.checkNotNull(findViewById2);
        this.y = (TextView) findViewById2;
        BottomSheetDialog bottomSheetDialog6 = this.z;
        Intrinsics.checkNotNull(bottomSheetDialog6);
        View findViewById3 = bottomSheetDialog6.findViewById(R.id.progress_update);
        Intrinsics.checkNotNull(findViewById3);
        this.w = (ProgressBar) findViewById3;
    }

    public final void X() {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this.p;
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = null;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        TextView textView = (TextView) activityWatchFaceCanewBinding.toolbar.findViewById(R.id.toolbar_back_arrow);
        textView.setText(getString(R.string.watch_faces));
        textView.setTextAppearance(R.style.semi_bold);
        textView.setTextSize(20.0f);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWatchFace.Y(ActivityWatchFace.this, view);
            }
        });
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding3 = null;
        }
        ((TextView) activityWatchFaceCanewBinding3.toolbar.findViewById(R.id.toolbar_title)).setVisibility(8);
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding4 = this.p;
        if (activityWatchFaceCanewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding2 = activityWatchFaceCanewBinding4;
        }
        ImageView toolbarImage = (ImageView) activityWatchFaceCanewBinding2.toolbar.findViewById(R.id.ivButton);
        Intrinsics.checkNotNullExpressionValue(toolbarImage, "toolbarImage");
        gone(toolbarImage);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Z() {
        /*
            Method dump skipped, instructions count: 485
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.activities.ActivityWatchFace.Z():void");
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

    public final void b0() {
        ImageView L = L();
        WatchFaceBean lastSelectedWatchFace = WatchFacePreferenceManager.Companion.getInstance(this).getLastSelectedWatchFace();
        if ((lastSelectedWatchFace != null ? lastSelectedWatchFace.getPreviewImageUrl() : null) == null) {
            if ((lastSelectedWatchFace != null ? lastSelectedWatchFace.getPreviewImageUrlFromLocal() : null) == null) {
                return;
            }
        }
        if (L != null) {
            String previewImageUrlFromLocal = lastSelectedWatchFace.getPreviewImageUrlFromLocal();
            boolean z = false;
            if (!(previewImageUrlFromLocal == null || previewImageUrlFromLocal.length() == 0)) {
                try {
                    L.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(Uri.fromFile(new File(lastSelectedWatchFace.getPreviewImageUrlFromLocal())).toString()))));
                    k0();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            String previewImageUrl = lastSelectedWatchFace.getPreviewImageUrl();
            if (previewImageUrl == null || previewImageUrl.length() == 0) {
                z = true;
            }
            if (!z) {
                String previewImageUrl2 = lastSelectedWatchFace.getPreviewImageUrl();
                Intrinsics.checkNotNull(previewImageUrl2);
                if (StringsKt__StringsKt.contains((CharSequence) previewImageUrl2, (CharSequence) ".gif", true)) {
                    try {
                        Glide.with((FragmentActivity) this).asGif().m21load(lastSelectedWatchFace.getPreviewImageUrl()).into(L);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        Glide.with((FragmentActivity) this).m30load(lastSelectedWatchFace.getPreviewImageUrl()).into(L);
                        return;
                    }
                }
            }
            Glide.with((FragmentActivity) this).m30load(lastSelectedWatchFace.getPreviewImageUrl()).into(L);
        }
    }

    public final void c0(String str) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(CleverTapConstants.CustomEventProperties.SOURCE.getValue(), str);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(this));
        hashMap.putAll(companion.getWatchDetails(this));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.WF_PAGE_ACCESSSED.getValue(), hashMap);
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
        bottomSheetDialogImageTitleMessageTwoBtns.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWatchFace.I(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        String string4 = getString(R.string.f6117no);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.no)");
        bottomSheetDialogImageTitleMessageTwoBtns.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWatchFace.J(BottomSheetDialogImageTitleMessageTwoBtns.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageTwoBtns.show();
    }

    public final boolean getEditIsEnabled() {
        return this.E;
    }

    public final boolean getFromNotification() {
        return this.G;
    }

    @Nullable
    public final String getScreenType() {
        return this.C;
    }

    @Nullable
    public final WatchFacePreferenceManager getWatchFacePreferenceManager() {
        return this.B;
    }

    public final void i0() {
        Bundle bundle = new Bundle();
        bundle.putString("cv_prev_screen_name", ReviewAndRateUsConstants.WATCHFACE.getValue());
        DialogFragment externalModuleDialogFragment = FragmentUtilsKt.getExternalModuleDialogFragment("com.coveiot.android.leonardo.rateus.RateUsDialogFragment");
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.setArguments(bundle);
        }
        if (externalModuleDialogFragment != null) {
            externalModuleDialogFragment.show(getSupportFragmentManager(), ActivityWatchFace.class.getSimpleName());
        }
    }

    public final void initDiyWatchFace() {
        ViewModel viewModel = ViewModelProviders.of(this, new ViewModelFactory(this)).get(WatchFaceDiyViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            this,\n  …DiyViewModel::class.java)");
        WatchFaceDiyViewModel watchFaceDiyViewModel = (WatchFaceDiyViewModel) viewModel;
        this.t = watchFaceDiyViewModel;
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = null;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        watchFaceDiyViewModel.setViewModelOnSuccessListener(this);
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.t;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel3 = null;
        }
        watchFaceDiyViewModel3.setViewModelOnFailureListener(this);
        WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.t;
        if (watchFaceDiyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel4 = null;
        }
        watchFaceDiyViewModel4.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.a0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.U(ActivityWatchFace.this, (ProgressBean) obj);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.t;
        if (watchFaceDiyViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel5 = null;
        }
        watchFaceDiyViewModel5.isUploadToWatchCanceled().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.V(ActivityWatchFace.this, (Boolean) obj);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel6 = this.t;
        if (watchFaceDiyViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel6 = null;
        }
        watchFaceDiyViewModel6.getSaveEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.n
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.S(ActivityWatchFace.this, (Boolean) obj);
            }
        });
        WatchFaceDiyViewModel watchFaceDiyViewModel7 = this.t;
        if (watchFaceDiyViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
        } else {
            watchFaceDiyViewModel2 = watchFaceDiyViewModel7;
        }
        watchFaceDiyViewModel2.getEditEnableDisable().observe(this, new Observer() { // from class: com.coveiot.android.watchfaceui.activities.p
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityWatchFace.T(ActivityWatchFace.this, (Boolean) obj);
            }
        });
    }

    public final void initWatchImage() {
        SessionManager sessionManager = SessionManager.getInstance(this);
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (DeviceUtils.Companion.isRoundWatch(this)) {
            sessionManager.setRoundedImage(1);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceCanewBinding2 = null;
            }
            RoundedImageView roundedImageView = activityWatchFaceCanewBinding2.selectedRoundedWatchFace;
            Intrinsics.checkNotNullExpressionValue(roundedImageView, "binding.selectedRoundedWatchFace");
            visible(roundedImageView);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this.p;
            if (activityWatchFaceCanewBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceCanewBinding3 = null;
            }
            ImageView imageView = activityWatchFaceCanewBinding3.selectedWatchFace;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.selectedWatchFace");
            gone(imageView);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding4 = this.p;
            if (activityWatchFaceCanewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding4;
            }
            activityWatchFaceCanewBinding.watchFacePlaceholder.setImageResource(R.drawable.rounded_watchface_placeholder);
        } else {
            sessionManager.setRoundedImage(0);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding5 = this.p;
            if (activityWatchFaceCanewBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceCanewBinding5 = null;
            }
            ImageView imageView2 = activityWatchFaceCanewBinding5.selectedWatchFace;
            Intrinsics.checkNotNullExpressionValue(imageView2, "binding.selectedWatchFace");
            visible(imageView2);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding6 = this.p;
            if (activityWatchFaceCanewBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceCanewBinding6 = null;
            }
            RoundedImageView roundedImageView2 = activityWatchFaceCanewBinding6.selectedRoundedWatchFace;
            Intrinsics.checkNotNullExpressionValue(roundedImageView2, "binding.selectedRoundedWatchFace");
            gone(roundedImageView2);
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding7 = this.p;
            if (activityWatchFaceCanewBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding7;
            }
            activityWatchFaceCanewBinding.watchFacePlaceholder.setImageResource(R.drawable.watchface_placeholder);
        }
        b0();
    }

    public final boolean isButtonEnabled() {
        return this.A;
    }

    public final boolean isEnabled() {
        return this.D;
    }

    public final boolean isSettings() {
        return this.F;
    }

    public final void j0() {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this.p;
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = null;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(false);
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this.p;
        if (activityWatchFaceCanewBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding3 = null;
        }
        activityWatchFaceCanewBinding3.editBtn.setTextColor(getResources().getColor(R.color.color_666666));
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding4 = this.p;
        if (activityWatchFaceCanewBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding2 = activityWatchFaceCanewBinding4;
        }
        activityWatchFaceCanewBinding2.editBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.reminder_edit_icon_grey, 0, 0, 0);
        this.D = false;
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof OnClickListener) {
                SectionsPagerAdapterWatchFace sectionsPagerAdapterWatchFace = this.r;
                Intrinsics.checkNotNull(sectionsPagerAdapterWatchFace);
                if (Intrinsics.areEqual(fragment, sectionsPagerAdapterWatchFace.getCurrentFragment())) {
                    ((OnClickListener) fragment).onAppliedClicked();
                }
            }
        }
    }

    public final void k0() {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = null;
        if (DeviceUtils.Companion.isCADevice(this)) {
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding2 = this.p;
            if (activityWatchFaceCanewBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceCanewBinding2 = null;
            }
            RoundedImageView roundedImageView = activityWatchFaceCanewBinding2.selectedWatchFaceBg;
            Intrinsics.checkNotNullExpressionValue(roundedImageView, "binding.selectedWatchFaceBg");
            visible(roundedImageView);
            if (WatchFacePreferenceManager.Companion.getInstance(this).getSelectedWatchfaceBackgroundType() == 0) {
                ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding3 = this.p;
                if (activityWatchFaceCanewBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityWatchFaceCanewBinding = activityWatchFaceCanewBinding3;
                }
                activityWatchFaceCanewBinding.selectedWatchFaceBg.setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_whitef);
                return;
            }
            ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding4 = this.p;
            if (activityWatchFaceCanewBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceCanewBinding = activityWatchFaceCanewBinding4;
            }
            activityWatchFaceCanewBinding.selectedWatchFaceBg.setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_blackf);
            return;
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding5 = this.p;
        if (activityWatchFaceCanewBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceCanewBinding = activityWatchFaceCanewBinding5;
        }
        RoundedImageView roundedImageView2 = activityWatchFaceCanewBinding.selectedWatchFaceBg;
        Intrinsics.checkNotNullExpressionValue(roundedImageView2, "binding.selectedWatchFaceBg");
        gone(roundedImageView2);
    }

    public final void l0(TabLayout.Tab tab, int i) {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        View childAt = activityWatchFaceCanewBinding.tabs.getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type android.view.ViewGroup");
        View childAt2 = ((ViewGroup) childAt).getChildAt(tab.getPosition());
        Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type android.widget.LinearLayout");
        View childAt3 = ((LinearLayout) childAt2).getChildAt(1);
        Intrinsics.checkNotNull(childAt3, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) childAt3).setTextAppearance(i);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == 123 && i2 == -1) {
            if (isFinishing()) {
                return;
            }
            b0();
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        if (activityWatchFaceCanewBinding.applyToWatch.isEnabled()) {
            confirmationDialog();
        } else {
            finish();
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onBackgroundWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCloudWatchFaceSuccess(boolean z) {
        WatchFaceCloudViewModel watchFaceCloudViewModel = this.u;
        if (watchFaceCloudViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel = null;
        }
        WatchFaceBean userSelectedWatchFace = watchFaceCloudViewModel.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace);
        String uid = userSelectedWatchFace.getUid();
        WatchFaceCloudViewModel watchFaceCloudViewModel2 = this.u;
        if (watchFaceCloudViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel2 = null;
        }
        WatchFaceBean userSelectedWatchFace2 = watchFaceCloudViewModel2.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace2);
        String faceId = userSelectedWatchFace2.getFaceId();
        WatchFaceCloudViewModel watchFaceCloudViewModel3 = this.u;
        if (watchFaceCloudViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel3 = null;
        }
        WatchFaceBean userSelectedWatchFace3 = watchFaceCloudViewModel3.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace3);
        String faceType = userSelectedWatchFace3.getFaceType();
        WatchFaceCloudViewModel watchFaceCloudViewModel4 = this.u;
        if (watchFaceCloudViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel4 = null;
        }
        WatchFaceBean userSelectedWatchFace4 = watchFaceCloudViewModel4.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace4);
        String title = userSelectedWatchFace4.getTitle();
        WatchFaceCloudViewModel watchFaceCloudViewModel5 = this.u;
        if (watchFaceCloudViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel5 = null;
        }
        WatchFaceBean userSelectedWatchFace5 = watchFaceCloudViewModel5.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace5);
        String downloadUrl = userSelectedWatchFace5.getDownloadUrl();
        WatchFaceCloudViewModel watchFaceCloudViewModel6 = this.u;
        if (watchFaceCloudViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel6 = null;
        }
        WatchFaceBean userSelectedWatchFace6 = watchFaceCloudViewModel6.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace6);
        String fileType = userSelectedWatchFace6.getFileType();
        WatchFaceCloudViewModel watchFaceCloudViewModel7 = this.u;
        if (watchFaceCloudViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel7 = null;
        }
        WatchFaceBean userSelectedWatchFace7 = watchFaceCloudViewModel7.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace7);
        String fileMd5Hash = userSelectedWatchFace7.getFileMd5Hash();
        WatchFaceCloudViewModel watchFaceCloudViewModel8 = this.u;
        if (watchFaceCloudViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel8 = null;
        }
        WatchFaceBean userSelectedWatchFace8 = watchFaceCloudViewModel8.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace8);
        String previewImageUrl = userSelectedWatchFace8.getPreviewImageUrl();
        WatchFaceCloudViewModel watchFaceCloudViewModel9 = this.u;
        if (watchFaceCloudViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel9 = null;
        }
        WatchFaceBean userSelectedWatchFace9 = watchFaceCloudViewModel9.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace9);
        List<String> tags = userSelectedWatchFace9.getTags();
        WatchFaceCloudViewModel watchFaceCloudViewModel10 = this.u;
        if (watchFaceCloudViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel10 = null;
        }
        WatchFaceBean userSelectedWatchFace10 = watchFaceCloudViewModel10.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace10);
        Integer faceResId = userSelectedWatchFace10.getFaceResId();
        WatchFaceCloudViewModel watchFaceCloudViewModel11 = this.u;
        if (watchFaceCloudViewModel11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceCloudViewModel");
            watchFaceCloudViewModel11 = null;
        }
        WatchFaceBean userSelectedWatchFace11 = watchFaceCloudViewModel11.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace11);
        WatchFaceBean watchFaceBean = new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, faceResId, userSelectedWatchFace11.getFacePosition(), null, false, null, 29184, null);
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        companion.getInstance(this).saveSelectedCloudWatchFace(watchFaceBean);
        companion.getInstance(this).saveSelectedDiyWatchFace(null);
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.v;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.callDeviceSpecificSettingsApi(companion.getInstance(this).getSelectedCloudWatchFace());
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x01f1, code lost:
        if (r8.isBESDevice(r7) == false) goto L51;
     */
    /* JADX WARN: Type inference failed for: r2v10, types: [T, com.google.firebase.remoteconfig.FirebaseRemoteConfig, java.lang.Object] */
    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(@org.jetbrains.annotations.Nullable android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.activities.ActivityWatchFace.onCreate(android.os.Bundle):void");
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onCurrentWatchFetchPositionFetchSuccess(int i) {
        WatchFaceDefaultViewModel watchFaceDefaultViewModel = this.s;
        if (watchFaceDefaultViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchfaceDefaultViewModel");
            watchFaceDefaultViewModel = null;
        }
        MutableLiveData<Integer> watchFacePositionLiveData = watchFaceDefaultViewModel.getWatchFacePositionLiveData();
        if (watchFacePositionLiveData != null) {
            watchFacePositionLiveData.postValue(Integer.valueOf(i));
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDefaultWatchFaceSuccess(boolean z) {
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.v;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.callDeviceSpecificSettingsApi(WatchFacePreferenceManager.Companion.getInstance(this).getSelectedDefaultWatchFace());
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onDiyWatchFaceSuccess(boolean z) {
        WatchFaceDiyViewModel watchFaceDiyViewModel = this.t;
        if (watchFaceDiyViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel = null;
        }
        WatchFaceBean userSelectedWatchFace = watchFaceDiyViewModel.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace);
        String uid = userSelectedWatchFace.getUid();
        WatchFaceDiyViewModel watchFaceDiyViewModel2 = this.t;
        if (watchFaceDiyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel2 = null;
        }
        WatchFaceBean userSelectedWatchFace2 = watchFaceDiyViewModel2.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace2);
        String faceId = userSelectedWatchFace2.getFaceId();
        WatchFaceDiyViewModel watchFaceDiyViewModel3 = this.t;
        if (watchFaceDiyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel3 = null;
        }
        WatchFaceBean userSelectedWatchFace3 = watchFaceDiyViewModel3.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace3);
        String faceType = userSelectedWatchFace3.getFaceType();
        WatchFaceDiyViewModel watchFaceDiyViewModel4 = this.t;
        if (watchFaceDiyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel4 = null;
        }
        WatchFaceBean userSelectedWatchFace4 = watchFaceDiyViewModel4.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace4);
        String title = userSelectedWatchFace4.getTitle();
        WatchFaceDiyViewModel watchFaceDiyViewModel5 = this.t;
        if (watchFaceDiyViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel5 = null;
        }
        WatchFaceBean userSelectedWatchFace5 = watchFaceDiyViewModel5.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace5);
        String downloadUrl = userSelectedWatchFace5.getDownloadUrl();
        WatchFaceDiyViewModel watchFaceDiyViewModel6 = this.t;
        if (watchFaceDiyViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel6 = null;
        }
        WatchFaceBean userSelectedWatchFace6 = watchFaceDiyViewModel6.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace6);
        String fileType = userSelectedWatchFace6.getFileType();
        WatchFaceDiyViewModel watchFaceDiyViewModel7 = this.t;
        if (watchFaceDiyViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel7 = null;
        }
        WatchFaceBean userSelectedWatchFace7 = watchFaceDiyViewModel7.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace7);
        String fileMd5Hash = userSelectedWatchFace7.getFileMd5Hash();
        WatchFaceDiyViewModel watchFaceDiyViewModel8 = this.t;
        if (watchFaceDiyViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel8 = null;
        }
        WatchFaceBean userSelectedWatchFace8 = watchFaceDiyViewModel8.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace8);
        String previewImageUrl = userSelectedWatchFace8.getPreviewImageUrl();
        WatchFaceDiyViewModel watchFaceDiyViewModel9 = this.t;
        if (watchFaceDiyViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel9 = null;
        }
        WatchFaceBean userSelectedWatchFace9 = watchFaceDiyViewModel9.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace9);
        List<String> tags = userSelectedWatchFace9.getTags();
        WatchFaceDiyViewModel watchFaceDiyViewModel10 = this.t;
        if (watchFaceDiyViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel10 = null;
        }
        WatchFaceBean userSelectedWatchFace10 = watchFaceDiyViewModel10.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace10);
        Integer faceResId = userSelectedWatchFace10.getFaceResId();
        WatchFaceDiyViewModel watchFaceDiyViewModel11 = this.t;
        if (watchFaceDiyViewModel11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceDiyViewModel");
            watchFaceDiyViewModel11 = null;
        }
        WatchFaceBean userSelectedWatchFace11 = watchFaceDiyViewModel11.getUserSelectedWatchFace();
        Intrinsics.checkNotNull(userSelectedWatchFace11);
        WatchFaceBean watchFaceBean = new WatchFaceBean(uid, faceId, faceType, title, downloadUrl, fileType, fileMd5Hash, previewImageUrl, tags, null, faceResId, userSelectedWatchFace11.getFacePosition(), null, false, null, 29184, null);
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        companion.getInstance(this).saveSelectedDiyWatchFace(watchFaceBean);
        companion.getInstance(this).saveSelectedCloudWatchFace(null);
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.v;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.callDeviceSpecificSettingsApiDiy();
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnFailureListener
    public void onFailure(@Nullable final String str) {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        if (str != null) {
            if (str.length() > 0) {
                runOnUiThread(new Runnable() { // from class: com.coveiot.android.watchfaceui.activities.r
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityWatchFace.h0(ActivityWatchFace.this, str);
                    }
                });
            }
        }
        ActivityWatchFaceCanewBinding activityWatchFaceCanewBinding = this.p;
        if (activityWatchFaceCanewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceCanewBinding = null;
        }
        activityWatchFaceCanewBinding.applyToWatch.setEnabled(true);
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onLayoutWatchFaceSuccess(boolean z) {
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnSuccessListener
    public void onSuccess(boolean z) {
        if (isFinishing()) {
            return;
        }
        dismissProgress();
        showSuccessDialog();
    }

    public final void setButtonEnabled(boolean z) {
        this.A = z;
    }

    public final void setEditIsEnabled(boolean z) {
        this.E = z;
    }

    public final void setEnabled(boolean z) {
        this.D = z;
    }

    public final void setFromNotification(boolean z) {
        this.G = z;
    }

    public final void setScreenType(@Nullable String str) {
        this.C = str;
    }

    public final void setSettings(boolean z) {
        this.F = z;
    }

    public final void setWatchFacePreferenceManager(@Nullable WatchFacePreferenceManager watchFacePreferenceManager) {
        this.B = watchFacePreferenceManager;
    }

    public final void showSuccessDialog() {
        Drawable drawable = getResources().getDrawable(R.drawable.success_image_new_img);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
        String string = getString(R.string.success_applied);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_applied)");
        final BottomSheetDialogImageTitleMessageWatchFace bottomSheetDialogImageTitleMessageWatchFace = new BottomSheetDialogImageTitleMessageWatchFace(this, drawable, string, "");
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogImageTitleMessageWatchFace.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.activities.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityWatchFace.m0(BottomSheetDialogImageTitleMessageWatchFace.this, this, view);
            }
        });
        bottomSheetDialogImageTitleMessageWatchFace.setCancelable(false);
        bottomSheetDialogImageTitleMessageWatchFace.show();
    }
}
