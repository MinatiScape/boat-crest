package com.coveiot.leaderboard.views.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.ViewModelFactory;
import com.coveiot.leaderboard.databinding.ActivityAddressBinding;
import com.coveiot.leaderboard.rankshare.viewmodel.ActivityRankViewModel;
import com.coveiot.leaderboard.services.LeaderBoardApiIntentService;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.views.fragment.FragmentAddress;
import com.google.gson.JsonObject;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ActivityAddress extends BaseActivity implements SuccessResultListener {
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityAddressBinding p;
    public ActivityRankViewModel q;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage r;
    @Nullable
    public BottomSheetDialogImageTitleMessage s;

    public static final void u(ActivityAddress this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.t();
    }

    public static final void w(ActivityAddress this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIRMATION_POPUP.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_RANK_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        Intent intent = new Intent(this$0, LeaderBoardApiIntentService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            this$0.startForegroundService(intent);
        } else {
            this$0.startService(intent);
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.r;
        Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        this$0.setResult(-1);
        this$0.finish();
    }

    public static final void x(ActivityAddress this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.s;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
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
    public final BottomSheetDialogOneButtonTitleMessage getSaveConfirmationDialog() {
        return this.r;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getSaveImageConfirmationDialog() {
        return this.s;
    }

    public final void initToolbar() {
        ActivityAddressBinding activityAddressBinding = this.p;
        ActivityAddressBinding activityAddressBinding2 = null;
        if (activityAddressBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddressBinding = null;
        }
        TextView textView = (TextView) activityAddressBinding.toolbar.findViewById(R.id.toolbar_title);
        ActivityAddressBinding activityAddressBinding3 = this.p;
        if (activityAddressBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddressBinding2 = activityAddressBinding3;
        }
        textView.setText(getString(R.string.enter_your_location));
        ((TextView) activityAddressBinding2.toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddress.u(ActivityAddress.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!LeaderBoardDataUtiils.isFragmentAddress(this)) {
            v();
            ActivityAddressBinding activityAddressBinding = this.p;
            if (activityAddressBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddressBinding = null;
            }
            TextView textView = (TextView) activityAddressBinding.toolbar.findViewById(R.id.toolbar_title);
            if (textView == null) {
                return;
            }
            textView.setText(getString(R.string.enter_your_location));
            return;
        }
        super.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAddressBinding inflate = ActivityAddressBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        ActivityRankViewModel activityRankViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        ViewModel viewModel = new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityRankViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …ankViewModel::class.java)");
        ActivityRankViewModel activityRankViewModel2 = (ActivityRankViewModel) viewModel;
        this.q = activityRankViewModel2;
        if (activityRankViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityRankViewModel = activityRankViewModel2;
        }
        activityRankViewModel.setMViewModelListener(this);
        initToolbar();
        v();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onError(@Nullable String str) {
        if (isFinishing()) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // com.coveiot.android.theme.SuccessResultListener
    public void onSuccess() {
        showConfirmationDialog();
    }

    public final void postAddressToServer(@NotNull JsonObject jsonAddress) {
        Intrinsics.checkNotNullParameter(jsonAddress, "jsonAddress");
        if (isFinishing()) {
            return;
        }
        ActivityRankViewModel activityRankViewModel = this.q;
        if (activityRankViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityRankViewModel = null;
        }
        activityRankViewModel.postAddressToServer(this, jsonAddress);
    }

    public final void setSaveConfirmationDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.r = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setSaveImageConfirmationDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.s = bottomSheetDialogImageTitleMessage;
    }

    public final void showConfirmationDialog() {
        if (this.r == null && !isFinishing()) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …rmation\n                )");
            String string2 = getString(R.string.your_rank_will_be_updated);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.your_rank_will_be_updated)");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(this, string, string2);
            this.r = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string3 = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAddress.w(ActivityAddress.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.r;
        if (bottomSheetDialogOneButtonTitleMessage2 != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
            if (bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
                return;
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.r;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
            bottomSheetDialogOneButtonTitleMessage3.show();
        }
    }

    public final void showImageConfirmationDialog() {
        if (this.s == null) {
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.info_icon_new)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, "", kotlin.text.f.trimIndent("\n     " + getString(R.string.location_for_competition) + "\n     \n     " + getString(R.string.location_for_competition_desc) + "\n     "), true);
            this.s = bottomSheetDialogImageTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            String string = getString(R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.ok)");
            bottomSheetDialogImageTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.activities.h
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAddress.x(ActivityAddress.this, view);
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.s;
        if (bottomSheetDialogImageTitleMessage2 != null) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
            if (bottomSheetDialogImageTitleMessage2.isShowing()) {
                return;
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.s;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
            bottomSheetDialogImageTitleMessage3.show();
        }
    }

    public final void t() {
        if (!LeaderBoardDataUtiils.isFragmentAddress(this)) {
            v();
            ActivityAddressBinding activityAddressBinding = this.p;
            if (activityAddressBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddressBinding = null;
            }
            TextView textView = (TextView) activityAddressBinding.toolbar.findViewById(R.id.toolbar_title);
            if (textView == null) {
                return;
            }
            textView.setText(getString(R.string.enter_your_location));
            return;
        }
        onBackPressed();
    }

    public final void v() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentAddress.Companion.newInstance()).commitAllowingStateLoss();
    }
}
