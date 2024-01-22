package com.coveiot.android.activitymodes.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityDataSummaryDetails;
import com.coveiot.android.activitymodes.activities.ActivityHistory;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.theme.utils.ThemeConstants;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivityDashboard extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public EntityWorkoutSession m;
    @Nullable
    public View n;
    @Nullable
    public AlertDialog p;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String o = "FragmentActivityDashboard";

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentActivityDashboard newInstance() {
            return new FragmentActivityDashboard();
        }
    }

    public static final void A(FragmentActivityDashboard this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Button button = (Button) this$0._$_findCachedViewById(R.id.slide_button);
        if (button != null) {
            button.setVisibility(8);
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        ImageView imageView = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        TextView textView = (TextView) this$0._$_findCachedViewById(R.id.recent_activity_text);
        if (textView == null) {
            return;
        }
        textView.setText(this$0.getString(R.string.start_activity_info));
    }

    public static final void B(final FirebaseRemoteConfig remoteConfig, final FragmentActivityDashboard this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r6 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.activitymodes.fragments.e
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    FragmentActivityDashboard.C(FirebaseRemoteConfig.this, this$0, r6, task2);
                }
            });
        } else if (!this$0.isAdded() || this$0.getView() == null) {
        } else {
            String string = remoteConfig.getString(ThemeConstants.REMOTE_CONFIG_ONEK_LIST.getValue());
            Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…E_CONFIG_ONEK_LIST.value)");
            DeviceRemoteConfig deviceRemoteConfig = (DeviceRemoteConfig) new Gson().fromJson(string, (Class<Object>) DeviceRemoteConfig.class);
            if (deviceRemoteConfig == null || AppUtils.isEmpty(deviceRemoteConfig.getDeviceList())) {
                return;
            }
            if (this$0.isAdded() && this$0.getView() != null) {
                if (this$0.getContext() != null && BleApiManager.getInstance(this$0.getContext()) != null) {
                    WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                    Context context = this$0.getContext();
                    Intrinsics.checkNotNull(context);
                    DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context!!).deviceType");
                    List<DeviceRemoteConfig.DeviceModelsBean> deviceList = deviceRemoteConfig.getDeviceList();
                    Intrinsics.checkNotNullExpressionValue(deviceList, "deviceRemoteConfig.deviceList");
                    if (workoutUtils.shouldShow1KActivity(deviceType, deviceList)) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                        if (constraintLayout != null) {
                            constraintLayout.setVisibility(0);
                        }
                        ImageView imageView = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                        if (imageView != null) {
                            imageView.setVisibility(8);
                        }
                        ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.ensure_your_activities_synced));
                        return;
                    }
                }
                ConstraintLayout constraintLayout2 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                if (constraintLayout2 != null) {
                    constraintLayout2.setVisibility(8);
                }
                ImageView imageView2 = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.start_activity_info));
            } else if (!this$0.isAdded() || this$0.getView() == null) {
            } else {
                ConstraintLayout constraintLayout3 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                if (constraintLayout3 != null) {
                    constraintLayout3.setVisibility(8);
                }
                ImageView imageView3 = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                if (imageView3 != null) {
                    imageView3.setVisibility(0);
                }
                ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.start_activity_info));
            }
        }
    }

    public static final void C(FirebaseRemoteConfig remoteConfig, FragmentActivityDashboard this$0, Void r7, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String string = remoteConfig.getString(ThemeConstants.REMOTE_CONFIG_ONEK_LIST.getValue());
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(T…E_CONFIG_ONEK_LIST.value)");
        DeviceRemoteConfig deviceRemoteConfig = (DeviceRemoteConfig) new Gson().fromJson(string, (Class<Object>) DeviceRemoteConfig.class);
        if (deviceRemoteConfig != null && !AppUtils.isEmpty(deviceRemoteConfig.getDeviceList())) {
            if (this$0.isAdded() && this$0.getView() != null) {
                if (this$0.getContext() != null && BleApiManager.getInstance(this$0.getContext()) != null) {
                    WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
                    Context context = this$0.getContext();
                    Intrinsics.checkNotNull(context);
                    DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
                    Intrinsics.checkNotNullExpressionValue(deviceType, "getInstance(context!!).deviceType");
                    List<DeviceRemoteConfig.DeviceModelsBean> deviceList = deviceRemoteConfig.getDeviceList();
                    Intrinsics.checkNotNullExpressionValue(deviceList, "deviceRemoteConfig.deviceList");
                    if (workoutUtils.shouldShow1KActivity(deviceType, deviceList)) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                        if (constraintLayout != null) {
                            constraintLayout.setVisibility(0);
                        }
                        ImageView imageView = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                        if (imageView != null) {
                            imageView.setVisibility(8);
                        }
                        ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.ensure_your_activities_synced));
                    }
                }
                ConstraintLayout constraintLayout2 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                if (constraintLayout2 != null) {
                    constraintLayout2.setVisibility(8);
                }
                ImageView imageView2 = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.start_activity_info));
            } else if (this$0.isAdded() && this$0.getView() != null) {
                ConstraintLayout constraintLayout3 = (ConstraintLayout) this$0._$_findCachedViewById(R.id.onek_layout);
                if (constraintLayout3 != null) {
                    constraintLayout3.setVisibility(8);
                }
                ImageView imageView3 = (ImageView) this$0._$_findCachedViewById(R.id.circular_image);
                if (imageView3 != null) {
                    imageView3.setVisibility(0);
                }
                ((TextView) this$0._$_findCachedViewById(R.id.recent_activity_text)).setText(this$0.getString(R.string.start_activity_info));
            }
        }
        String str = this$0.o;
        LogHelper.d(str, "Config params updated: " + r7 + ' ' + string);
    }

    public static final void E(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public static final void G(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.m != null) {
            Intent intent = new Intent(this$0.getContext(), ActivityHistory.class);
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context);
            context.startActivity(intent);
            return;
        }
        Toast.makeText(this$0.getContext(), this$0.getString(R.string.no_recent_activities), 1).show();
    }

    public static final void H(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((Button) this$0._$_findCachedViewById(R.id.view_history)).performClick();
    }

    public static final void I(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        if (BleApiManager.getInstance(context).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            if (AppUtils.isNetConnected(context2)) {
                if (!SyncManager.getInstance().isSyncInProgress()) {
                    Context context3 = this$0.getContext();
                    Intrinsics.checkNotNull(context3);
                    if (new PreferenceManager(context3).getShouldShowActivitySyncDialog()) {
                        this$0.N();
                        return;
                    } else {
                        this$0.startActivity(new Intent(this$0.getContext(), OneKActivity.class));
                        return;
                    }
                }
                Toast.makeText(this$0.getContext(), R.string.syncing_please_wait, 0).show();
                return;
            }
            this$0.showNoInternetDialog();
            return;
        }
        this$0.Q();
    }

    public static final void J(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_BANNER_CLICK.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.ACTIVITY_TAB_ON_HOME_DASH.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        ((Button) this$0._$_findCachedViewById(R.id.slide_button)).performClick();
    }

    public static final void K(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D();
    }

    public static final void L(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.D();
    }

    public static final void M(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.S();
    }

    public static final void O(Dialog alertDialog, View view) {
        Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
        alertDialog.dismiss();
    }

    public static final void P(Dialog alertDialog, FragmentActivityDashboard this$0, CheckBox checkBox, View view) {
        Intrinsics.checkNotNullParameter(alertDialog, "$alertDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        alertDialog.dismiss();
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        new PreferenceManager(context).setShouldShowActivitySyncDialog(!checkBox.isChecked());
        this$0.startActivity(new Intent(this$0.getContext(), OneKActivity.class));
    }

    public static final void R(FragmentActivityDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog alertDialog = this$0.p;
        Intrinsics.checkNotNull(alertDialog);
        if (alertDialog.isShowing()) {
            AlertDialog alertDialog2 = this$0.p;
            Intrinsics.checkNotNull(alertDialog2);
            alertDialog2.dismiss();
        }
    }

    public static final void T(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    public final void D() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        String string = getResources().getString(R.string.information);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.information)");
        String string2 = getString(R.string.info_activity);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.info_activity)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activity, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivityDashboard.E(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void F(EntityWorkoutSession entityWorkoutSession) {
        LogHelper.d("FragmentActivityDashboard", "loadDetailScreen");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        if (new PreferenceManager(activity).isSampleDataSupported()) {
            FragmentActivity activity2 = getActivity();
            Intrinsics.checkNotNull(activity2);
            Intent intent = new Intent(activity2, ActivityDataSummaryDetails.class);
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, entityWorkoutSession.getActivity_type());
            intent.putExtra(WorkoutConstants.SESSION_ID, entityWorkoutSession.getSession_id());
            FragmentActivity activity3 = getActivity();
            Intrinsics.checkNotNull(activity3);
            activity3.startActivity(intent);
        }
    }

    public final void N() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.requestWindowFeature(1);
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.activity_sync_warning_dialog, (ViewGroup) null);
        dialog.setContentView(inflate);
        ((ImageView) inflate.findViewById(R.id.iv_close)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivityDashboard.O(dialog, view);
            }
        });
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.checkBox);
        ((TextView) inflate.findViewById(R.id.btn_stop)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivityDashboard.P(dialog, this, checkBox, view);
            }
        });
        dialog.show();
    }

    public final void Q() {
        AlertDialog alertDialog = this.p;
        if (alertDialog != null) {
            Intrinsics.checkNotNull(alertDialog);
            if (alertDialog.isShowing()) {
                AlertDialog alertDialog2 = this.p;
                Intrinsics.checkNotNull(alertDialog2);
                alertDialog2.dismiss();
            }
        }
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_phone_not_connected, (ViewGroup) null);
        this.n = inflate;
        builder.setView(inflate);
        View view = this.n;
        Intrinsics.checkNotNull(view);
        AlertDialog create = builder.create();
        this.p = create;
        Intrinsics.checkNotNull(create);
        create.show();
        ((Button) view.findViewById(R.id.try_again_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentActivityDashboard.R(FragmentActivityDashboard.this, view2);
            }
        });
    }

    public final void S() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        String string = getResources().getString(R.string.information);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.information)");
        String string2 = getString(R.string.info_activity_2);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.info_activity_2)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(activity, string, string2);
        String string3 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivityDashboard.T(BottomSheetDialogOneButtonTitleMessage.this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Nullable
    public final EntityWorkoutSession getEntityWSession() {
        return this.m;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_activity_dashboard, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0101  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r7, @org.jetbrains.annotations.Nullable android.os.Bundle r8) {
        /*
            Method dump skipped, instructions count: 443
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentActivityDashboard.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void setEntityWSession(@Nullable EntityWorkoutSession entityWorkoutSession) {
        this.m = entityWorkoutSession;
    }

    public final void z() {
        if (((Button) _$_findCachedViewById(R.id.slide_button)) == null || getContext() == null) {
            return;
        }
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        remoteConfig.fetch(10L).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.activitymodes.fragments.f
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FragmentActivityDashboard.A(FragmentActivityDashboard.this, exc);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.activitymodes.fragments.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FragmentActivityDashboard.B(FirebaseRemoteConfig.this, this, task);
            }
        });
    }
}
