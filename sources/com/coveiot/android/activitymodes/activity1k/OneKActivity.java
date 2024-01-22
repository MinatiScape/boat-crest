package com.coveiot.android.activitymodes.activity1k;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivityInformation;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivityReArrange;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentCategories;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentSelectWatchActivityToReplace;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchActivities;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchActivitiesAfterReplace;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchActivitiesNew;
import com.coveiot.android.activitymodes.activity1k.fragment.FragmentWatchRestoreToDefaults;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityConfigMetaData;
import com.coveiot.android.bleabstract.models.ActivityDataType;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.METData;
import com.coveiot.android.bleabstract.request.ConfigureActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoBtnsWrapContent;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.onekactivity.PhysicalActivityItem;
import com.coveiot.coveaccess.onekactivity.PhysicalActivityManager;
import com.coveiot.coveaccess.onekactivity.SPhysicalActivityListRes;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.h;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class OneKActivity extends BaseActivity {
    public boolean p;
    @Nullable
    public OneKActivityViewModel r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @Nullable
    public Integer q = 0;

    /* loaded from: classes2.dex */
    public interface DefaultActivityResultListener {
        void onFailure(@NotNull String str);

        void onResult(@NotNull ConfigureActivityListRequest configureActivityListRequest);
    }

    public static final void A(OneKActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.img_restore)).performClick();
    }

    public static final void B(OneKActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void C(OneKActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.E();
    }

    public static final void F(Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    public static /* synthetic */ void replaceFragment$default(OneKActivity oneKActivity, Fragment fragment, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        oneKActivity.replaceFragment(fragment, z);
    }

    public static final void y(OneKActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        replaceFragment$default(this$0, FragmentActivityInformation.Companion.newInstance(), false, 2, null);
    }

    public static final void z(OneKActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            if (AppUtils.isBluetoothEnabled(this$0)) {
                if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    replaceFragment$default(this$0, FragmentWatchRestoreToDefaults.Companion.newInstance(), false, 2, null);
                    return;
                } else {
                    this$0.showBandNotConnected(false);
                    return;
                }
            }
            this$0.showBluetoothOffDialog();
            return;
        }
        this$0.showNoInternetMessage();
    }

    public final void D(final SettingsResultListener settingsResultListener) {
        showProgress();
        x(new DefaultActivityResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$restoreActivitiesToDefaults$1
            @Override // com.coveiot.android.activitymodes.activity1k.OneKActivity.DefaultActivityResultListener
            public void onFailure(@NotNull String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                OneKActivity.this.dismissProgress();
                Toast.makeText(OneKActivity.this, msg, 1).show();
            }

            @Override // com.coveiot.android.activitymodes.activity1k.OneKActivity.DefaultActivityResultListener
            public void onResult(@NotNull ConfigureActivityListRequest req) {
                Intrinsics.checkNotNullParameter(req, "req");
                BleApi bleApi = BleApiManager.getInstance(OneKActivity.this).getBleApi();
                final OneKActivity oneKActivity = OneKActivity.this;
                final SettingsResultListener settingsResultListener2 = settingsResultListener;
                bleApi.setUserSettings(req, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$restoreActivitiesToDefaults$1$onResult$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        OneKActivity.this.dismissProgress();
                        settingsResultListener2.onSettingsError(error);
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        Intrinsics.checkNotNullParameter(response, "response");
                        OneKActivity.this.dismissProgress();
                        settingsResultListener2.onSettingsResponse(response);
                    }
                });
            }
        });
    }

    public final void E() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.setContentView(R.layout.activity_leevl_dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        ((ImageView) dialog.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneKActivity.F(dialog, view);
            }
        });
        dialog.show();
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

    public final boolean getISFROMFTU() {
        return this.p;
    }

    public final boolean getMetrics(@NotNull List<String> metrics, @NotNull String metricValue) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        Intrinsics.checkNotNullParameter(metricValue, "metricValue");
        return metrics.contains(metricValue);
    }

    @Nullable
    public final Integer getProgress() {
        return this.q;
    }

    @Nullable
    public final OneKActivityViewModel getViewModel() {
        return this.r;
    }

    public final void loadFragmentCategories() {
        replaceFragment$default(this, FragmentCategories.Companion.newInstance(false), false, 2, null);
    }

    public final void loadSelectWatchActivityToReplaceFragment() {
        replaceFragment$default(this, FragmentSelectWatchActivityToReplace.Companion.newInstance(), false, 2, null);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            int i = R.id.container;
            Fragment findFragmentById = supportFragmentManager.findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById);
            if (findFragmentById instanceof FragmentWatchActivitiesAfterReplace) {
                finish();
                return;
            }
            Fragment findFragmentById2 = getSupportFragmentManager().findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById2);
            if (findFragmentById2 instanceof FragmentSelectWatchActivityToReplace) {
                CategoryAndActivityModel selectedActivity = SingletonOneKActivity.Companion.getInstance(this).getSelectedActivity();
                if (selectedActivity != null) {
                    Drawable drawable = getDrawable(R.drawable.failure_image_img);
                    Intrinsics.checkNotNull(drawable);
                    String string = getString(R.string.are_you_dont_want_save, new Object[]{selectedActivity.getTitleInMetric()});
                    Intrinsics.checkNotNullExpressionValue(string, "getString(\n             …                        )");
                    final BottomSheetDialogImageTitleMessageTwoBtnsWrapContent bottomSheetDialogImageTitleMessageTwoBtnsWrapContent = new BottomSheetDialogImageTitleMessageTwoBtnsWrapContent(this, drawable, string, "", true);
                    String string2 = getString(R.string.cancel);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.cancel)");
                    bottomSheetDialogImageTitleMessageTwoBtnsWrapContent.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$onBackPressed$1
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogImageTitleMessageTwoBtnsWrapContent.this.dismiss();
                        }
                    });
                    String string3 = getString(R.string.dont_replace);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.dont_replace)");
                    bottomSheetDialogImageTitleMessageTwoBtnsWrapContent.setNegativeButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$onBackPressed$2
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogImageTitleMessageTwoBtnsWrapContent.this.dismiss();
                            this.getSupportFragmentManager().popBackStack();
                            SingletonOneKActivity.Companion.getInstance(this).setSelectedActivity(null);
                            OneKActivity oneKActivity = this;
                            Fragment findFragmentById3 = oneKActivity.getSupportFragmentManager().findFragmentById(R.id.container);
                            Intrinsics.checkNotNull(findFragmentById3);
                            oneKActivity.setupToolbar(findFragmentById3);
                        }
                    });
                    bottomSheetDialogImageTitleMessageTwoBtnsWrapContent.show();
                    return;
                }
                getSupportFragmentManager().popBackStack();
                Fragment findFragmentById3 = getSupportFragmentManager().findFragmentById(i);
                Intrinsics.checkNotNull(findFragmentById3);
                setupToolbar(findFragmentById3);
                return;
            }
            getSupportFragmentManager().popBackStack();
            Fragment findFragmentById4 = getSupportFragmentManager().findFragmentById(i);
            Intrinsics.checkNotNull(findFragmentById4);
            setupToolbar(findFragmentById4);
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_one_k_activities);
        this.r = (OneKActivityViewModel) ViewModelProviders.of(this, (ViewModelProvider.Factory) null).get(OneKActivityViewModel.class);
        this.p = getIntent().getBooleanExtra("IS_FROM_FTU", false);
        new SeperatedProgressbar(ContextCompat.getColor(this, R.color.seek_red), ContextCompat.getColor(this, R.color.colorPrimaryDark), this);
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_info);
        Intrinsics.checkNotNull(imageView);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneKActivity.y(OneKActivity.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.img_restore)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneKActivity.z(OneKActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.reset_btn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneKActivity.A(OneKActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OneKActivity.B(OneKActivity.this, view);
            }
        });
        showProgress();
        setupToolbar(FragmentWatchActivitiesNew.Companion.newInstance());
        OneKActivityViewModel oneKActivityViewModel = this.r;
        Intrinsics.checkNotNull(oneKActivityViewModel);
        oneKActivityViewModel.saveCategoryListFromServer(new ResponseListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$onCreate$5
            @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
            public void onError() {
                OneKActivityViewModel viewModel = OneKActivity.this.getViewModel();
                Intrinsics.checkNotNull(viewModel);
                final OneKActivity oneKActivity = OneKActivity.this;
                viewModel.getCurrentActivitiesOnWatch(new ResponseListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$onCreate$5$onError$1
                    @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                    public void onError() {
                        OneKActivity.this.dismissProgress();
                    }

                    @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                    public void onResponse() {
                        OneKActivity.this.dismissProgress();
                        OneKActivity.replaceFragment$default(OneKActivity.this, FragmentWatchActivitiesNew.Companion.newInstance(), false, 2, null);
                    }
                });
            }

            @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
            public void onResponse() {
                OneKActivityViewModel viewModel = OneKActivity.this.getViewModel();
                Intrinsics.checkNotNull(viewModel);
                final OneKActivity oneKActivity = OneKActivity.this;
                viewModel.getCurrentActivitiesOnWatch(new ResponseListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$onCreate$5$onResponse$1
                    @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                    public void onError() {
                        OneKActivity.this.dismissProgress();
                    }

                    @Override // com.coveiot.android.activitymodes.activity1k.ResponseListener
                    public void onResponse() {
                        OneKActivity.this.dismissProgress();
                        OneKActivity.replaceFragment$default(OneKActivity.this, FragmentWatchActivitiesNew.Companion.newInstance(), false, 2, null);
                    }
                });
            }
        });
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OneKActivity.C(OneKActivity.this, view);
                }
            });
        }
    }

    public final void replaceFragment(@NotNull Fragment fragment, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        setupToolbar(fragment);
        if (z) {
            while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(FragmentActivityInformation.Companion.getClass().getCanonicalName()).commitAllowingStateLoss();
    }

    public final void setISFROMFTU(boolean z) {
        this.p = z;
    }

    public final void setProgress(@Nullable Integer num) {
        this.q = num;
    }

    public final void setViewModel(@Nullable OneKActivityViewModel oneKActivityViewModel) {
        this.r = oneKActivityViewModel;
    }

    public final void setupToolbar(@NotNull Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        if (fragment instanceof FragmentWatchActivities) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.activity_on_watch));
        } else if (fragment instanceof FragmentWatchActivitiesAfterReplace) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.activity_on_watch));
        } else if (fragment instanceof FragmentReplaceActivity) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.select_activity_to_replace));
        } else if (fragment instanceof FragmentCategories) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.select_category));
        } else if (fragment instanceof FragmentActivitySelectionNew) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getString(R.string.replace_activity));
        } else if (fragment instanceof FragmentActivityReArrange) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.confirm_selected_activity));
        } else if (fragment instanceof FragmentActivityInformation) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.activity_information));
        } else if (fragment instanceof FragmentWatchActivitiesNew) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.watch_activities));
        } else if (fragment instanceof FragmentSelectWatchActivityToReplace) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.select_activity));
        } else if (fragment instanceof FragmentWatchRestoreToDefaults) {
            ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.reset_to_defaults));
        }
        if (fragment instanceof FragmentCategories) {
            ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_info);
            Intrinsics.checkNotNull(imageView);
            imageView.setVisibility(8);
            ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_restore);
            Intrinsics.checkNotNull(imageView2);
            imageView2.setVisibility(8);
            ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
            if (imageView3 == null) {
                return;
            }
            imageView3.setVisibility(0);
        } else if (fragment instanceof FragmentWatchActivitiesNew) {
            ImageView imageView4 = (ImageView) _$_findCachedViewById(R.id.img_info);
            Intrinsics.checkNotNull(imageView4);
            imageView4.setVisibility(8);
            ImageView imageView5 = (ImageView) _$_findCachedViewById(R.id.img_restore);
            Intrinsics.checkNotNull(imageView5);
            imageView5.setVisibility(8);
            TextView textView = (TextView) _$_findCachedViewById(R.id.reset_btn);
            Intrinsics.checkNotNull(textView);
            textView.setVisibility(0);
            ImageView imageView6 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
            if (imageView6 == null) {
                return;
            }
            imageView6.setVisibility(8);
        } else if (fragment instanceof FragmentActivitySelectionNew) {
            ImageView imageView7 = (ImageView) _$_findCachedViewById(R.id.img_info);
            Intrinsics.checkNotNull(imageView7);
            imageView7.setVisibility(8);
            ImageView imageView8 = (ImageView) _$_findCachedViewById(R.id.img_restore);
            Intrinsics.checkNotNull(imageView8);
            imageView8.setVisibility(8);
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.reset_btn);
            Intrinsics.checkNotNull(textView2);
            textView2.setVisibility(8);
            ImageView imageView9 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
            if (imageView9 == null) {
                return;
            }
            imageView9.setVisibility(8);
        } else if (fragment instanceof FragmentWatchActivitiesAfterReplace) {
            ImageView imageView10 = (ImageView) _$_findCachedViewById(R.id.img_info);
            Intrinsics.checkNotNull(imageView10);
            imageView10.setVisibility(0);
            ImageView imageView11 = (ImageView) _$_findCachedViewById(R.id.img_restore);
            Intrinsics.checkNotNull(imageView11);
            imageView11.setVisibility(8);
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.reset_btn);
            Intrinsics.checkNotNull(textView3);
            textView3.setVisibility(8);
            ImageView imageView12 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
            if (imageView12 == null) {
                return;
            }
            imageView12.setVisibility(8);
        } else {
            ImageView imageView13 = (ImageView) _$_findCachedViewById(R.id.activity_level_info);
            if (imageView13 != null) {
                imageView13.setVisibility(8);
            }
            ImageView imageView14 = (ImageView) _$_findCachedViewById(R.id.img_info);
            Intrinsics.checkNotNull(imageView14);
            imageView14.setVisibility(8);
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.reset_btn);
            Intrinsics.checkNotNull(textView4);
            textView4.setVisibility(8);
            ImageView imageView15 = (ImageView) _$_findCachedViewById(R.id.img_restore);
            Intrinsics.checkNotNull(imageView15);
            imageView15.setVisibility(8);
        }
    }

    public final void showRestoreToDefaultDialog(@NotNull final SettingsResultListener resultListener) {
        Intrinsics.checkNotNullParameter(resultListener, "resultListener");
        String string = getString(R.string.restore_default);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.restore_default)");
        String string2 = getString(R.string.restore_default_msg);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.restore_default_msg)");
        final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
        String string3 = getString(R.string.done);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.done)");
        bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$showRestoreToDefaultDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
                if (AppUtils.isNetConnected(this)) {
                    if (AppUtils.isBluetoothEnabled(this)) {
                        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                            this.D(resultListener);
                            return;
                        } else {
                            Toast.makeText(this, R.string.band_not_connected, 0).show();
                            return;
                        }
                    }
                    Toast.makeText(this, R.string.bluetooth_off_message, 0).show();
                    return;
                }
                Toast.makeText(this, R.string.please_check_network, 0).show();
            }
        });
        String string4 = getString(R.string.cancel);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
        bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$showRestoreToDefaultDialog$2
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogTwoButtons.this.dismiss();
            }
        });
        bottomSheetDialogTwoButtons.show();
    }

    public final void updateToolbarText(@NotNull String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        ((TextView) _$_findCachedViewById(R.id.toolbar_title)).setText(title);
    }

    public final int w(String str) {
        Intrinsics.checkNotNull(str);
        switch (str.hashCode()) {
            case -2014989386:
                return !str.equals("MOTION") ? 0 : 2;
            case -1635077031:
                str.equals("INBUILT");
                return 0;
            case 79223559:
                return !str.equals("STEPS") ? 0 : 1;
            case 1320014927:
                return !str.equals("TOTAL_DURATION") ? 0 : 3;
            default:
                return 0;
        }
    }

    public final void x(final DefaultActivityResultListener defaultActivityResultListener) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        List<EntityPhysicalActivities> defaultActivities = PhysicalActivityRepository.Companion.getInstance(this).getDefaultActivities();
        if (!AppUtils.isEmpty(defaultActivities)) {
            Intrinsics.checkNotNull(defaultActivities);
            for (EntityPhysicalActivities entityPhysicalActivities : defaultActivities) {
                ArrayList arrayList3 = new ArrayList();
                if (!m.equals$default(entityPhysicalActivities.getCal_func(), "INBUILT", false, 2, null)) {
                    Intrinsics.checkNotNull(entityPhysicalActivities);
                    Double defaultMets = entityPhysicalActivities.getDefaultMets();
                    Intrinsics.checkNotNull(defaultMets);
                    arrayList3 = CollectionsKt__CollectionsKt.arrayListOf(new METData(0.0f, (float) defaultMets.doubleValue()));
                }
                ArrayList arrayList4 = arrayList3;
                int categoryId = entityPhysicalActivities.getCategoryId();
                Integer defaultCategoryIcon = entityPhysicalActivities.getDefaultCategoryIcon();
                Intrinsics.checkNotNull(defaultCategoryIcon);
                int intValue = defaultCategoryIcon.intValue();
                int fwActId = entityPhysicalActivities.getFwActId();
                Integer defaultActivityIcon = entityPhysicalActivities.getDefaultActivityIcon();
                Integer defaultCategoryIcon2 = (defaultActivityIcon != null && defaultActivityIcon.intValue() == -1) ? entityPhysicalActivities.getDefaultCategoryIcon() : entityPhysicalActivities.getDefaultActivityIcon();
                Intrinsics.checkNotNull(defaultCategoryIcon2);
                int intValue2 = defaultCategoryIcon2.intValue();
                String defaultActivityName = entityPhysicalActivities.getDefaultActivityName();
                List<String> metrics = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics);
                boolean metrics2 = getMetrics(metrics, EcgStyleReportUtil.UserInfoKey.HR);
                List<String> metrics3 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics3);
                boolean metrics4 = getMetrics(metrics3, "STEPS");
                List<String> metrics5 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics5);
                boolean metrics6 = getMetrics(metrics5, "STEP_STRIDE");
                List<String> metrics7 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics7);
                boolean metrics8 = getMetrics(metrics7, "STEP_FREQ");
                List<String> metrics9 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics9);
                boolean metrics10 = getMetrics(metrics9, "SPEED");
                List<String> metrics11 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics11);
                boolean metrics12 = getMetrics(metrics11, "PACE");
                List<String> metrics13 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics13);
                boolean metrics14 = getMetrics(metrics13, "ALTITUDE");
                List<String> metrics15 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics15);
                boolean metrics16 = getMetrics(metrics15, "GPS");
                List<String> metrics17 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics17);
                boolean metrics18 = getMetrics(metrics17, FitnessChallengeConstants.CALORIES);
                List<String> metrics19 = entityPhysicalActivities.getMetrics();
                Intrinsics.checkNotNull(metrics19);
                arrayList.add(new ActivityConfigMetaData(0, categoryId, intValue, "", fwActId, intValue2, defaultActivityName, 0, new ActivityDataType(metrics2, metrics4, metrics6, metrics8, metrics10, metrics12, metrics14, metrics16, metrics18, getMetrics(metrics19, "DISTANCE")), w(entityPhysicalActivities.getCal_func()), arrayList4.size(), arrayList4));
            }
        }
        PhysicalActivityManager.getInBuiltPhysicalActivityList(new CoveApiListener<SPhysicalActivityListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$getConfigureActivityListRequest$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                if (coveApiErrorModel != null) {
                    OneKActivity.DefaultActivityResultListener defaultActivityResultListener2 = defaultActivityResultListener;
                    String msg = coveApiErrorModel.getMsg();
                    Intrinsics.checkNotNull(msg);
                    defaultActivityResultListener2.onFailure(msg);
                    return;
                }
                OneKActivity.DefaultActivityResultListener defaultActivityResultListener3 = defaultActivityResultListener;
                String string = this.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                defaultActivityResultListener3.onFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SPhysicalActivityListRes sPhysicalActivityListRes) {
                if (sPhysicalActivityListRes == null || sPhysicalActivityListRes.getData() == null || sPhysicalActivityListRes.getData().getItems() == null) {
                    return;
                }
                List<PhysicalActivityItem> defaultActivityListFromServer = sPhysicalActivityListRes.getData().getItems();
                Intrinsics.checkNotNullExpressionValue(defaultActivityListFromServer, "defaultActivityListFromServer");
                if (defaultActivityListFromServer.size() > 1) {
                    h.sortWith(defaultActivityListFromServer, new Comparator() { // from class: com.coveiot.android.activitymodes.activity1k.OneKActivity$getConfigureActivityListRequest$1$onSuccess$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return kotlin.comparisons.f.compareValues(((PhysicalActivityItem) t).getSeqNumber(), ((PhysicalActivityItem) t2).getSeqNumber());
                        }
                    });
                }
                for (PhysicalActivityItem physicalActivityItem : defaultActivityListFromServer) {
                    Iterator<ActivityConfigMetaData> it = arrayList.iterator();
                    while (it.hasNext()) {
                        ActivityConfigMetaData next = it.next();
                        if (physicalActivityItem.getFwActId() == next.getActivityId()) {
                            Integer categoryId2 = physicalActivityItem.getCategoryId();
                            int categoryId3 = next.getCategoryId();
                            if (categoryId2 != null && categoryId2.intValue() == categoryId3) {
                                arrayList2.add(next);
                            }
                        }
                    }
                }
                defaultActivityResultListener.onResult(new ConfigureActivityListRequest(arrayList2));
            }
        });
    }
}
