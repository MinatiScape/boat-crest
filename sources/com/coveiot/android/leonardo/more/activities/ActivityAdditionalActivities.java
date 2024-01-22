package com.coveiot.android.leonardo.more.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ActivityAdditionalActivitiesBinding;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.more.adapters.AdditionalActivitiesAdapter;
import com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.watchfacecore.model.ProgressBean;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAdditionalActivities extends BaseActivity implements AdditionalActivitiesAdapter.ActivitySelectionListener, AdditionalActivitiesViewModel.AdditionalActivityPushListener {
    public ActivityAdditionalActivitiesBinding p;
    public AdditionalActivitiesAdapter q;
    @Nullable
    public ActivitiesItem s;
    public AdditionalActivitiesViewModel t;
    @Nullable
    public View u;
    public ProgressBar v;
    public TextView w;
    public TextView x;
    @Nullable
    public View y;
    @Nullable
    public Dialog z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String r = ActivityAdditionalActivities.class.getName();

    public static final void A(ActivityAdditionalActivities this$0, String error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(error, "$error");
        String str = this$0.r;
        LogHelper.d(str, "error while pushing activity error msg " + error);
        Toast.makeText(this$0, (int) R.string.some_error_occurred_try_again, 1).show();
    }

    public static final void v(Dialog mAlertDialog, ActivityAdditionalActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            this$0.finish();
        }
    }

    public static final void y(ActivityAdditionalActivities this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void z(ActivityAdditionalActivities this$0, ProgressBean progressBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String title = progressBean.getTitle();
        TextView textView = null;
        if (title != null) {
            TextView textView2 = this$0.w;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressTitleTv");
                textView2 = null;
            }
            textView2.setText(title);
        }
        if (progressBean.getVisible()) {
            Dialog dialog = this$0.z;
            if (dialog != null) {
                Intrinsics.checkNotNull(dialog);
                if (!dialog.isShowing()) {
                    Dialog dialog2 = this$0.z;
                    Intrinsics.checkNotNull(dialog2);
                    dialog2.show();
                }
            }
            ProgressBar progressBar = this$0.v;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("pb");
                progressBar = null;
            }
            progressBar.setProgress(progressBean.getProgress());
            TextView textView3 = this$0.x;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressValue");
            } else {
                textView = textView3;
            }
            textView.setText(progressBean.getProgress() + " %");
            return;
        }
        Dialog dialog3 = this$0.z;
        if (dialog3 != null) {
            Intrinsics.checkNotNull(dialog3);
            if (dialog3.isShowing()) {
                Dialog dialog4 = this$0.z;
                Intrinsics.checkNotNull(dialog4);
                dialog4.dismiss();
            }
        }
    }

    public final void B() {
        AdditionalActivitiesViewModel additionalActivitiesViewModel = this.t;
        if (additionalActivitiesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("additionalActivitiesViewModel");
            additionalActivitiesViewModel = null;
        }
        ActivitiesItem activitiesItem = this.s;
        additionalActivitiesViewModel.pushAdditionalActivityToWatch(activitiesItem != null ? activitiesItem.getBinFileUrl() : null);
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

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.additional_activities));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAdditionalActivities.y(ActivityAdditionalActivities.this, view);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.adapters.AdditionalActivitiesAdapter.ActivitySelectionListener
    public void onActivitySelected(@NotNull ActivitiesItem activitiesItem) {
        Intrinsics.checkNotNullParameter(activitiesItem, "activitiesItem");
        this.s = activitiesItem;
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding = this.p;
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding2 = null;
        if (activityAdditionalActivitiesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding = null;
        }
        activityAdditionalActivitiesBinding.btnDone.setAlpha(1.0f);
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding3 = this.p;
        if (activityAdditionalActivitiesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding3 = null;
        }
        activityAdditionalActivitiesBinding3.btnDone.setClickable(true);
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding4 = this.p;
        if (activityAdditionalActivitiesBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAdditionalActivitiesBinding2 = activityAdditionalActivitiesBinding4;
        }
        activityAdditionalActivitiesBinding2.btnDone.setEnabled(true);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityAdditionalActivitiesBinding inflate = ActivityAdditionalActivitiesBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        AdditionalActivitiesViewModel additionalActivitiesViewModel = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        initToolbar();
        AdditionalActivitiesViewModel additionalActivitiesViewModel2 = (AdditionalActivitiesViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(AdditionalActivitiesViewModel.class);
        this.t = additionalActivitiesViewModel2;
        if (additionalActivitiesViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("additionalActivitiesViewModel");
            additionalActivitiesViewModel2 = null;
        }
        additionalActivitiesViewModel2.setAdditionalActivityPushListener(this);
        this.q = new AdditionalActivitiesAdapter(w(), this);
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding = this.p;
        if (activityAdditionalActivitiesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding = null;
        }
        RecyclerView recyclerView = activityAdditionalActivitiesBinding.rvAdditionalActivities;
        AdditionalActivitiesAdapter additionalActivitiesAdapter = this.q;
        if (additionalActivitiesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityAdapter");
            additionalActivitiesAdapter = null;
        }
        recyclerView.setAdapter(additionalActivitiesAdapter);
        AdditionalActivitiesAdapter additionalActivitiesAdapter2 = this.q;
        if (additionalActivitiesAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityAdapter");
            additionalActivitiesAdapter2 = null;
        }
        additionalActivitiesAdapter2.setListner(this);
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding2 = this.p;
        if (activityAdditionalActivitiesBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding2 = null;
        }
        activityAdditionalActivitiesBinding2.rvAdditionalActivities.setLayoutManager(new GridLayoutManager(this, 3));
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding3 = this.p;
        if (activityAdditionalActivitiesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding3 = null;
        }
        activityAdditionalActivitiesBinding3.rvAdditionalActivities.setItemViewCacheSize(w().size());
        x();
        ActivityAdditionalActivitiesBinding activityAdditionalActivitiesBinding4 = this.p;
        if (activityAdditionalActivitiesBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAdditionalActivitiesBinding4 = null;
        }
        Button button = activityAdditionalActivitiesBinding4.btnDone;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnDone");
        ViewUtilsKt.setSafeOnClickListener(button, new Function1<View, Unit>() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAdditionalActivities$onCreate$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull View it) {
                ActivitiesItem activitiesItem;
                String str;
                ActivitiesItem activitiesItem2;
                Intrinsics.checkNotNullParameter(it, "it");
                activitiesItem = ActivityAdditionalActivities.this.s;
                if (activitiesItem != null) {
                    str = ActivityAdditionalActivities.this.r;
                    StringBuilder sb = new StringBuilder();
                    sb.append("activityName ");
                    activitiesItem2 = ActivityAdditionalActivities.this.s;
                    sb.append(activitiesItem2 != null ? activitiesItem2.getBinFileUrl() : null);
                    LogHelper.d(str, sb.toString());
                    if (!DeviceUtils.Companion.isRuggedDevice(ActivityAdditionalActivities.this)) {
                        ActivityAdditionalActivities.this.B();
                    } else if (BleApiManager.getInstance(ActivityAdditionalActivities.this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApi bleApi = BleApiManager.getInstance(ActivityAdditionalActivities.this).getBleApi();
                        BatteryLevelRequest batteryLevelRequest = new BatteryLevelRequest();
                        final ActivityAdditionalActivities activityAdditionalActivities = ActivityAdditionalActivities.this;
                        bleApi.getData(batteryLevelRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAdditionalActivities$onCreate$1.1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                String str2;
                                Intrinsics.checkNotNullParameter(error, "error");
                                str2 = ActivityAdditionalActivities.this.r;
                                LogHelper.i(str2, "BatteryLevelRequest -- onDataError ");
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataResponse(@NotNull BleBaseResponse response) {
                                String str2;
                                Intrinsics.checkNotNullParameter(response, "response");
                                if (response.getResponseData() instanceof BatteryLevelResponse) {
                                    Object responseData = response.getResponseData();
                                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                    Utils utils = Utils.INSTANCE;
                                    Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                    Intrinsics.checkNotNull(batteryLevel);
                                    int batteryPercentageForMatrix = utils.getBatteryPercentageForMatrix(batteryLevel.intValue());
                                    str2 = ActivityAdditionalActivities.this.r;
                                    LogHelper.i(str2, "batteryLevel -- " + batteryPercentageForMatrix);
                                    if (batteryPercentageForMatrix >= 30) {
                                        ActivityAdditionalActivities.this.B();
                                        return;
                                    }
                                    ActivityAdditionalActivities activityAdditionalActivities2 = ActivityAdditionalActivities.this;
                                    Toast.makeText(activityAdditionalActivities2, activityAdditionalActivities2.getString(R.string.make_sure_battery, new Object[]{"30 %"}), 1).show();
                                }
                            }

                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onProgressUpdate(@NotNull ProgressData progress) {
                                Intrinsics.checkNotNullParameter(progress, "progress");
                            }
                        });
                    } else {
                        ActivityAdditionalActivities activityAdditionalActivities2 = ActivityAdditionalActivities.this;
                        Toast.makeText(activityAdditionalActivities2, activityAdditionalActivities2.getString(R.string.band_not_connected), 1).show();
                    }
                }
            }
        });
        AdditionalActivitiesViewModel additionalActivitiesViewModel3 = this.t;
        if (additionalActivitiesViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("additionalActivitiesViewModel");
        } else {
            additionalActivitiesViewModel = additionalActivitiesViewModel3;
        }
        additionalActivitiesViewModel.getProgressLiveData().observe(this, new Observer() { // from class: com.coveiot.android.leonardo.more.activities.i
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ActivityAdditionalActivities.z(ActivityAdditionalActivities.this, (ProgressBean) obj);
            }
        });
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel.AdditionalActivityPushListener
    public void onFailure(@NotNull final String error) {
        Dialog dialog;
        Intrinsics.checkNotNullParameter(error, "error");
        if (isFinishing()) {
            return;
        }
        if (error.length() > 0) {
            runOnUiThread(new Runnable() { // from class: com.coveiot.android.leonardo.more.activities.j
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityAdditionalActivities.A(ActivityAdditionalActivities.this, error);
                }
            });
        }
        Dialog dialog2 = this.z;
        if (dialog2 != null) {
            Boolean valueOf = dialog2 != null ? Boolean.valueOf(dialog2.isShowing()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (!valueOf.booleanValue() || (dialog = this.z) == null) {
                return;
            }
            dialog.dismiss();
        }
    }

    @Override // com.coveiot.android.leonardo.more.viewmodel.AdditionalActivitiesViewModel.AdditionalActivityPushListener
    public void onSuccess() {
        u();
    }

    public final void u() {
        final Dialog dialog = new Dialog(this, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_activity_confirm, (ViewGroup) null);
        this.u = inflate;
        Intrinsics.checkNotNull(inflate);
        ((TextView) inflate.findViewById(R.id.txt_msg)).setVisibility(8);
        View view = this.u;
        Intrinsics.checkNotNull(view);
        ((TextView) view.findViewById(R.id.txt_letsgo)).setText(getString(R.string.activity_updated_successfully));
        View view2 = this.u;
        Intrinsics.checkNotNull(view2);
        dialog.setContentView(view2);
        View view3 = this.u;
        Intrinsics.checkNotNull(view3);
        ((TextView) view3.findViewById(R.id.tv_warning_auto_detection)).setVisibility(8);
        View view4 = this.u;
        Intrinsics.checkNotNull(view4);
        dialog.setContentView(view4);
        View view5 = this.u;
        Intrinsics.checkNotNull(view5);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        ((Button) view5.findViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.activities.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                ActivityAdditionalActivities.v(dialog, this, view6);
            }
        });
    }

    public final ArrayList<ActivitiesItem> w() {
        return new ArrayList<>(CollectionsKt___CollectionsKt.sortedWith(new PreferenceManager(this).getRuggedActivityIcons(), new Comparator() { // from class: com.coveiot.android.leonardo.more.activities.ActivityAdditionalActivities$getAdditionalActivityList$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return kotlin.comparisons.f.compareValues(((ActivitiesItem) t).getActivityName(), ((ActivitiesItem) t2).getActivityName());
            }
        }));
    }

    public final void x() {
        this.z = new Dialog(this, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        this.y = layoutInflater.inflate(R.layout.dialog_uploading_additional_activities, (ViewGroup) null);
        Dialog dialog = this.z;
        Intrinsics.checkNotNull(dialog);
        View view = this.y;
        Intrinsics.checkNotNull(view);
        dialog.setContentView(view);
        View view2 = this.y;
        Intrinsics.checkNotNull(view2);
        View findViewById = view2.findViewById(R.id.tv_progress_title);
        Intrinsics.checkNotNull(findViewById);
        this.w = (TextView) findViewById;
        View view3 = this.y;
        Intrinsics.checkNotNull(view3);
        View findViewById2 = view3.findViewById(R.id.progress_value);
        Intrinsics.checkNotNull(findViewById2);
        this.x = (TextView) findViewById2;
        View view4 = this.y;
        Intrinsics.checkNotNull(view4);
        View findViewById3 = view4.findViewById(R.id.progress_update);
        Intrinsics.checkNotNull(findViewById3);
        this.v = (ProgressBar) findViewById3;
        Dialog dialog2 = this.z;
        Intrinsics.checkNotNull(dialog2);
        dialog2.setCancelable(false);
        Dialog dialog3 = this.z;
        Intrinsics.checkNotNull(dialog3);
        dialog3.setCanceledOnTouchOutside(false);
        Dialog dialog4 = this.z;
        Intrinsics.checkNotNull(dialog4);
        dialog4.show();
    }
}
