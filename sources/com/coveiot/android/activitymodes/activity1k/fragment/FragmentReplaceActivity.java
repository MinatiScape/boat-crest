package com.coveiot.android.activitymodes.activity1k.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivityAndCategoryAdapter;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel;
import com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentReplaceActivity extends BaseFragment implements ActivityAndCategoryAdapter.ItemClickListener, FragmentReArrangeViewModel.ActivityUpdateListener, ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public String A;
    public FragmentReArrangeViewModel m;
    public ActivityAutoRecognitionViewModelWithOneK n;
    @Nullable
    public EntityWorkoutSession o;
    public FragmentActivityAndCategoryViewModel p;
    public ActivityAndCategoryAdapter q;
    @Nullable
    public CategoryAndActivityModel t;
    @Nullable
    public ActivitiesListModel u;
    @Nullable
    public ActivityCategoriesModel v;
    @Nullable
    public CategoryAndActivityModel w;
    @Nullable
    public View x;
    @Nullable
    public AlertDialog y;
    public boolean z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int r = -1;
    @Nullable
    public List<CategoryAndActivityModel> s = new ArrayList();

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentReplaceActivity newInstance() {
            return new FragmentReplaceActivity();
        }
    }

    public static final void s(final FragmentReplaceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNull(context);
        if (AppUtils.isNetConnected(context)) {
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            if (AppUtils.isBluetoothEnabled(context2)) {
                Context context3 = this$0.getContext();
                Intrinsics.checkNotNull(context3);
                ConnectionStatus connectionStatus = BleApiManager.getInstance(context3).getBleApi().getConnectionStatus();
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    if (SyncManager.getInstance().isSyncInProgress()) {
                        Toast.makeText(this$0.getContext(), R.string.syncing_please_wait, 0).show();
                        return;
                    } else if (this$0.r != -1) {
                        ((Button) this$0._$_findCachedViewById(R.id.btn_next)).setEnabled(false);
                        List<CategoryAndActivityModel> list = this$0.s;
                        Intrinsics.checkNotNull(list);
                        this$0.t = list.get(this$0.r);
                        List<CategoryAndActivityModel> list2 = this$0.s;
                        Intrinsics.checkNotNull(list2);
                        list2.remove(this$0.r);
                        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
                        Context context4 = this$0.getContext();
                        Intrinsics.checkNotNull(context4);
                        List<CategoryAndActivityModel> list3 = this$0.s;
                        Intrinsics.checkNotNull(list3);
                        companion.getInstance(context4).setCategoryAndActivityList(list3);
                        Context context5 = this$0.getContext();
                        Intrinsics.checkNotNull(context5);
                        this$0.v = companion.getInstance(context5).getActivityCategoriesModel();
                        Context context6 = this$0.getContext();
                        Intrinsics.checkNotNull(context6);
                        this$0.s = companion.getInstance(context6).getCategoryAndActivityList();
                        Context context7 = this$0.getContext();
                        Intrinsics.checkNotNull(context7);
                        this$0.u = companion.getInstance(context7).getPhysicalActivity();
                        FragmentActivity activity = this$0.getActivity();
                        Intrinsics.checkNotNull(activity);
                        ViewModel viewModel = ViewModelProviders.of(activity).get(FragmentReArrangeViewModel.class);
                        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!)\n         …ngeViewModel::class.java)");
                        FragmentReArrangeViewModel fragmentReArrangeViewModel = (FragmentReArrangeViewModel) viewModel;
                        this$0.m = fragmentReArrangeViewModel;
                        if (fragmentReArrangeViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                            fragmentReArrangeViewModel = null;
                        }
                        fragmentReArrangeViewModel.setActivityUpdateListener(this$0);
                        FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this$0.m;
                        if (fragmentReArrangeViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                            fragmentReArrangeViewModel2 = null;
                        }
                        this$0.w = fragmentReArrangeViewModel2.getCategoryAndActivityModel(this$0.v, this$0.u);
                        List<CategoryAndActivityModel> list4 = this$0.s;
                        Intrinsics.checkNotNull(list4);
                        CategoryAndActivityModel categoryAndActivityModel = this$0.w;
                        Intrinsics.checkNotNull(categoryAndActivityModel);
                        list4.add(0, categoryAndActivityModel);
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_EXISITING_CONFIRM.getValue());
                        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_ACTIVITY_LIST_SCREEN.getValue());
                        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN.getValue());
                        CategoryAndActivityModel categoryAndActivityModel2 = this$0.t;
                        analyticsLog.setOldActivityCode(String.valueOf(categoryAndActivityModel2 != null ? categoryAndActivityModel2.getActivityCode() : null));
                        ActivitiesListModel activitiesListModel = this$0.u;
                        analyticsLog.setNewActivityCode(String.valueOf(activitiesListModel != null ? activitiesListModel.getActivityCode() : null));
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                            BleApiManager.getInstance(this$0.getContext()).getBleApi().getData(new GetImageIdListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity$onViewCreated$2$1
                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onDataError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    FragmentReplaceActivity fragmentReplaceActivity = FragmentReplaceActivity.this;
                                    int i = R.id.btn_next;
                                    if (((Button) fragmentReplaceActivity._$_findCachedViewById(i)) != null) {
                                        ((Button) FragmentReplaceActivity.this._$_findCachedViewById(i)).setEnabled(true);
                                    }
                                }

                                /* JADX WARN: Removed duplicated region for block: B:20:0x008f  */
                                /* JADX WARN: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                    To view partially-correct add '--show-bad-code' argument
                                */
                                public void onDataResponse(@org.jetbrains.annotations.NotNull com.coveiot.android.bleabstract.response.BleBaseResponse r5) {
                                    /*
                                        r4 = this;
                                        java.lang.String r0 = "response"
                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                                        java.lang.Object r0 = r5.getResponseData()
                                        boolean r0 = r0 instanceof com.coveiot.android.bleabstract.response.GetImageListResponse
                                        if (r0 == 0) goto L95
                                        java.lang.Object r5 = r5.getResponseData()
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                                        com.coveiot.android.bleabstract.response.GetImageListResponse r5 = (com.coveiot.android.bleabstract.response.GetImageListResponse) r5
                                        java.util.List r5 = r5.getImageIdList()
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getNewActivityCategory$p(r0)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        java.lang.Integer r0 = r0.getDefaultActivityIcon()
                                        boolean r0 = r5.contains(r0)
                                        r1 = 0
                                        java.lang.String r2 = "viewModelRearrange"
                                        r3 = 0
                                        if (r0 != 0) goto L6f
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getNewActivityCategory$p(r0)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        java.lang.Integer r0 = r0.getDefaultCategoryIcon()
                                        boolean r5 = r5.contains(r0)
                                        if (r5 != 0) goto L6f
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getNewActivityCategory$p(r5)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        boolean r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$isDefaultCategory(r5, r0)
                                        if (r5 != 0) goto L6f
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getViewModelRearrange$p(r5)
                                        if (r5 != 0) goto L62
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                                        r5 = r3
                                    L62:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getNewActivityCategory$p(r0)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        r5.saveImageToInternal(r0, r1)
                                        goto L87
                                    L6f:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getViewModelRearrange$p(r5)
                                        if (r5 != 0) goto L7b
                                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                                        r5 = r3
                                    L7b:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        java.util.List r0 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.access$getCategoryAndActivityList$p(r0)
                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                        r5.setConfigureActivityListRequest(r0)
                                    L87:
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        boolean r5 = r5.isAdded()
                                        if (r5 == 0) goto L95
                                        com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity r5 = com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity.this
                                        r0 = 1
                                        com.coveiot.android.theme.BaseFragment.showProgress$default(r5, r1, r0, r3)
                                    L95:
                                        return
                                    */
                                    throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity$onViewCreated$2$1.onDataResponse(com.coveiot.android.bleabstract.response.BleBaseResponse):void");
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onProgressUpdate(@NotNull ProgressData progress) {
                                    Intrinsics.checkNotNullParameter(progress, "progress");
                                }
                            });
                            return;
                        }
                        return;
                    } else {
                        Toast.makeText(this$0.getContext(), R.string.please_select_activity_to_replace, 1).show();
                        return;
                    }
                }
                FragmentActivity activity2 = this$0.getActivity();
                Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
                ((OneKActivity) activity2).showBandNotConnected(false);
                return;
            }
            this$0.showNoBluetoothDialog();
            return;
        }
        this$0.showNoInternetDialog();
    }

    public static final void u(Dialog mAlertDialog, View view) {
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        mAlertDialog.dismiss();
    }

    public static final void v(Dialog mAlertDialog, FragmentReplaceActivity this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        mAlertDialog.dismiss();
        this$0.r(i);
    }

    public static final void x(final FragmentReplaceActivity this$0, Dialog mAlertDialog, View view) {
        List<DeviceIconModel> deviceIconModels;
        DeviceIconModel deviceIconModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        if (!AppUtils.isNetConnected(this$0.getContext())) {
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context);
            Toast.makeText(context, R.string.please_check_network, 0).show();
        } else if (!AppUtils.isBluetoothEnabled(this$0.getContext())) {
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNull(context2);
            Toast.makeText(context2, R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Context context3 = this$0.getContext();
            Intrinsics.checkNotNull(context3);
            Toast.makeText(context3, R.string.band_not_connected, 0).show();
        } else {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            CategoryAndActivityModel categoryAndActivityModel = this$0.t;
            if (categoryAndActivityModel != null) {
                Integer num = null;
                Boolean valueOf = categoryAndActivityModel != null ? Boolean.valueOf(categoryAndActivityModel.getInbuilt()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (!valueOf.booleanValue() && BleApiManager.getInstance(this$0.getContext()).getBleApi() != null) {
                    BaseFragment.showProgress$default(this$0, false, 1, null);
                    BleApi bleApi = BleApiManager.getInstance(this$0.getContext()).getBleApi();
                    CategoryAndActivityModel categoryAndActivityModel2 = this$0.t;
                    if (categoryAndActivityModel2 != null && (deviceIconModels = categoryAndActivityModel2.getDeviceIconModels()) != null && (deviceIconModel = deviceIconModels.get(0)) != null) {
                        num = deviceIconModel.getRefId();
                    }
                    Intrinsics.checkNotNull(num);
                    bleApi.setUserSettings(new DeleteImageRequest(num.intValue()), new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity$showBluetoothDialog$2$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            FragmentReplaceActivity.this.dismissProgress();
                            FragmentReplaceActivity.this.q();
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            CategoryAndActivityModel categoryAndActivityModel3;
                            CategoryAndActivityModel categoryAndActivityModel4;
                            List<DeviceIconModel> deviceIconModels2;
                            DeviceIconModel deviceIconModel2;
                            List<DeviceIconModel> deviceIconModels3;
                            Intrinsics.checkNotNullParameter(response, "response");
                            categoryAndActivityModel3 = FragmentReplaceActivity.this.t;
                            Integer num2 = null;
                            Integer valueOf2 = (categoryAndActivityModel3 == null || (deviceIconModels3 = categoryAndActivityModel3.getDeviceIconModels()) == null) ? null : Integer.valueOf(deviceIconModels3.size());
                            Intrinsics.checkNotNull(valueOf2);
                            if (valueOf2.intValue() > 1) {
                                BleApi bleApi2 = BleApiManager.getInstance(FragmentReplaceActivity.this.getContext()).getBleApi();
                                categoryAndActivityModel4 = FragmentReplaceActivity.this.t;
                                if (categoryAndActivityModel4 != null && (deviceIconModels2 = categoryAndActivityModel4.getDeviceIconModels()) != null && (deviceIconModel2 = deviceIconModels2.get(1)) != null) {
                                    num2 = deviceIconModel2.getRefId();
                                }
                                Intrinsics.checkNotNull(num2);
                                DeleteImageRequest deleteImageRequest = new DeleteImageRequest(num2.intValue());
                                final FragmentReplaceActivity fragmentReplaceActivity = FragmentReplaceActivity.this;
                                bleApi2.setUserSettings(deleteImageRequest, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity$showBluetoothDialog$2$1$onSettingsResponse$1
                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        FragmentReplaceActivity.this.dismissProgress();
                                        FragmentReplaceActivity.this.q();
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                    public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                                        Intrinsics.checkNotNullParameter(response2, "response");
                                        FragmentReplaceActivity.this.dismissProgress();
                                        FragmentReplaceActivity.this.q();
                                    }
                                });
                                return;
                            }
                            FragmentReplaceActivity.this.dismissProgress();
                            FragmentReplaceActivity.this.q();
                        }
                    });
                    return;
                }
            }
            this$0.q();
        }
    }

    public static final void y(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, FragmentReplaceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonTitleMessage.dismiss();
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
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
    public final String getActivityCodeSelectedForAutoDetection() {
        return this.A;
    }

    @Nullable
    public final EntityWorkoutSession getEntityWSession() {
        return this.o;
    }

    public final boolean isSelectedForAutoDetection() {
        return this.z;
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onActivityUpload() {
        if (this.z) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            AutoActivityDetectionData autoActivityDetectionData = UserDataManager.getInstance(activity).getAutoActivityDetectionData();
            if (autoActivityDetectionData != null) {
                String str = this.A;
                Unit unit = null;
                ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = null;
                if (str != null) {
                    autoActivityDetectionData.getActivities()[WorkoutUtils.INSTANCE.getAutoActivityDetectionFWOrderForAnActivity(str)] = 0;
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = this.n;
                    if (activityAutoRecognitionViewModelWithOneK2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModelAutoActivityDetection");
                    } else {
                        activityAutoRecognitionViewModelWithOneK = activityAutoRecognitionViewModelWithOneK2;
                    }
                    activityAutoRecognitionViewModelWithOneK.setAutoRecognitionToWatch(autoActivityDetectionData);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    dismissProgress();
                    int i = R.id.btn_next;
                    if (((Button) _$_findCachedViewById(i)) != null) {
                        ((Button) _$_findCachedViewById(i)).setEnabled(true);
                    }
                    w();
                    return;
                }
                return;
            }
            dismissProgress();
            int i2 = R.id.btn_next;
            if (((Button) _$_findCachedViewById(i2)) != null) {
                ((Button) _$_findCachedViewById(i2)).setEnabled(true);
            }
            w();
            return;
        }
        dismissProgress();
        int i3 = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i3)) != null) {
            ((Button) _$_findCachedViewById(i3)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener
    public void onAutoDetectionFailure(@Nullable String str) {
        dismissProgress();
        int i = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i)) != null) {
            ((Button) _$_findCachedViewById(i)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener
    public void onAutoDetectionSuccess() {
        dismissProgress();
        int i = R.id.btn_next;
        if (((Button) _$_findCachedViewById(i)) != null) {
            ((Button) _$_findCachedViewById(i)).setEnabled(true);
        }
        w();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityAndCategoryAdapter.ItemClickListener
    public void onClicked(int i) {
        List<CategoryAndActivityModel> list;
        this.z = false;
        this.A = null;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(context).getAutoRecognitionList();
        if (autoRecognitionList != null && (list = this.s) != null) {
            Intrinsics.checkNotNull(list);
            if (list.get(i) != null) {
                Iterator<AutoRecognitionData> it = autoRecognitionList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AutoRecognitionData next = it.next();
                    if (next.isEnabled()) {
                        int fwActivityId = next.getFwActivityId();
                        List<CategoryAndActivityModel> list2 = this.s;
                        Intrinsics.checkNotNull(list2);
                        if (fwActivityId == list2.get(i).getFwActId()) {
                            this.z = true;
                            this.A = next.getActivityCode();
                            break;
                        }
                    }
                }
            }
        }
        if (this.z) {
            t(i);
        } else {
            r(i);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_category_activity, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onImageUpload(int i) {
        List<DeviceIconModel> deviceIconModels;
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel = null;
        if (i == 0) {
            CategoryAndActivityModel categoryAndActivityModel = this.w;
            Integer valueOf = (categoryAndActivityModel == null || (deviceIconModels = categoryAndActivityModel.getDeviceIconModels()) == null) ? null : Integer.valueOf(deviceIconModels.size());
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 1) {
                FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this.m;
                if (fragmentReArrangeViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                } else {
                    fragmentReArrangeViewModel = fragmentReArrangeViewModel2;
                }
                CategoryAndActivityModel categoryAndActivityModel2 = this.w;
                Intrinsics.checkNotNull(categoryAndActivityModel2);
                fragmentReArrangeViewModel.saveImageToInternal(categoryAndActivityModel2, 1);
                return;
            }
            FragmentReArrangeViewModel fragmentReArrangeViewModel3 = this.m;
            if (fragmentReArrangeViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
            } else {
                fragmentReArrangeViewModel = fragmentReArrangeViewModel3;
            }
            List<CategoryAndActivityModel> list = this.s;
            Intrinsics.checkNotNull(list);
            fragmentReArrangeViewModel.setConfigureActivityListRequest(list);
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel4 = this.m;
        if (fragmentReArrangeViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
        } else {
            fragmentReArrangeViewModel = fragmentReArrangeViewModel4;
        }
        List<CategoryAndActivityModel> list2 = this.s;
        Intrinsics.checkNotNull(list2);
        fragmentReArrangeViewModel.setConfigureActivityListRequest(list2);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivityAndCategoryAdapter.ItemClickListener
    public void onLongClicked(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN.getValue());
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onUpdateFailed() {
        if (getActivity() != null) {
            dismissProgress();
            int i = R.id.btn_next;
            if (((Button) _$_findCachedViewById(i)) != null) {
                ((Button) _$_findCachedViewById(i)).setEnabled(true);
            }
            Toast.makeText(getContext(), getString(R.string.activity_config_failed), 0).show();
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            activity.finish();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        ((OneKActivity) activity).setupToolbar(Companion.newInstance());
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        ViewModel viewModel = ViewModelProviders.of(activity2).get(FragmentActivityAndCategoryViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!).get(Fragm…oryViewModel::class.java)");
        this.p = (FragmentActivityAndCategoryViewModel) viewModel;
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        ViewModel viewModel2 = ViewModelProviders.of(activity3).get(ActivityAutoRecognitionViewModelWithOneK.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(activity!!).get(Activ…odelWithOneK::class.java)");
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = (ActivityAutoRecognitionViewModelWithOneK) viewModel2;
        this.n = activityAutoRecognitionViewModelWithOneK;
        FragmentActivityAndCategoryViewModel fragmentActivityAndCategoryViewModel = null;
        if (activityAutoRecognitionViewModelWithOneK == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelAutoActivityDetection");
            activityAutoRecognitionViewModelWithOneK = null;
        }
        activityAutoRecognitionViewModelWithOneK.setSettingUpdateListener(this);
        BaseFragment.showProgress$default(this, false, 1, null);
        FragmentActivityAndCategoryViewModel fragmentActivityAndCategoryViewModel2 = this.p;
        if (fragmentActivityAndCategoryViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentActivityAndCategoryViewModel = fragmentActivityAndCategoryViewModel2;
        }
        fragmentActivityAndCategoryViewModel.getConfiguredActivitiesFromWatch(new FragmentActivityAndCategoryViewModel.WatchActivitiesListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentReplaceActivity$onViewCreated$1
            @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel.WatchActivitiesListener
            public void onFailure(@NotNull String msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                if (FragmentReplaceActivity.this.isAdded()) {
                    FragmentReplaceActivity.this.dismissProgress();
                    Toast.makeText(FragmentReplaceActivity.this.getContext(), FragmentReplaceActivity.this.getString(R.string.some_thing_went_wrong), 1).show();
                }
            }

            @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivityAndCategoryViewModel.WatchActivitiesListener
            public void onWatchActivitiesResult(@Nullable List<CategoryAndActivityModel> list) {
                List list2;
                ActivityAndCategoryAdapter activityAndCategoryAdapter;
                if (!FragmentReplaceActivity.this.isAdded() || list == null) {
                    return;
                }
                FragmentReplaceActivity.this.dismissProgress();
                FragmentReplaceActivity.this.s = list;
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FragmentReplaceActivity.this.getContext());
                FragmentReplaceActivity fragmentReplaceActivity = FragmentReplaceActivity.this;
                int i = R.id.rv_activity;
                RecyclerView recyclerView = (RecyclerView) fragmentReplaceActivity._$_findCachedViewById(i);
                if (recyclerView != null) {
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
                FragmentReplaceActivity fragmentReplaceActivity2 = FragmentReplaceActivity.this;
                list2 = fragmentReplaceActivity2.s;
                Intrinsics.checkNotNull(list2);
                FragmentReplaceActivity fragmentReplaceActivity3 = FragmentReplaceActivity.this;
                Context context = fragmentReplaceActivity3.getContext();
                Intrinsics.checkNotNull(context);
                fragmentReplaceActivity2.q = new ActivityAndCategoryAdapter(list2, fragmentReplaceActivity3, context);
                RecyclerView recyclerView2 = (RecyclerView) FragmentReplaceActivity.this._$_findCachedViewById(i);
                if (recyclerView2 == null) {
                    return;
                }
                activityAndCategoryAdapter = FragmentReplaceActivity.this.q;
                if (activityAndCategoryAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
                    activityAndCategoryAdapter = null;
                }
                recyclerView2.setAdapter(activityAndCategoryAdapter);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_next)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.x
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentReplaceActivity.s(FragmentReplaceActivity.this, view2);
            }
        });
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onWatchBusyStatusReceived() {
        if (getActivity() != null && isAdded()) {
            dismissProgress();
        }
        showWatchBusyDialog();
    }

    public final boolean p(CategoryAndActivityModel categoryAndActivityModel) {
        return false;
    }

    public final void q() {
        FragmentWatchActivitiesAfterReplace newInstance = FragmentWatchActivitiesAfterReplace.Companion.newInstance();
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.activitymodes.activity1k.OneKActivity");
        OneKActivity.replaceFragment$default((OneKActivity) activity, newInstance, false, 2, null);
    }

    public final void r(int i) {
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        ActivitiesListModel physicalActivity = companion.getInstance(context).getPhysicalActivity();
        this.r = i;
        TextView textView = (TextView) _$_findCachedViewById(R.id.bottom_text);
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.selected_activity_replaced_by));
        sb.append(' ');
        ActivityAndCategoryAdapter activityAndCategoryAdapter = null;
        sb.append(physicalActivity != null ? physicalActivity.getTitleInMetric() : null);
        textView.setText(sb.toString());
        ((Button) _$_findCachedViewById(R.id.btn_next)).setEnabled(true);
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_EXISITING_SELECT.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.ONEK_ACTIVITY_LIST_SCREEN.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN.getValue());
        List<CategoryAndActivityModel> list = this.s;
        Intrinsics.checkNotNull(list);
        CategoryAndActivityModel categoryAndActivityModel = list.get(i);
        this.t = categoryAndActivityModel;
        String activityCode = categoryAndActivityModel != null ? categoryAndActivityModel.getActivityCode() : null;
        Intrinsics.checkNotNull(activityCode);
        analyticsLog.setActivityCode(activityCode.toString());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        ActivityAndCategoryAdapter activityAndCategoryAdapter2 = this.q;
        if (activityAndCategoryAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemAdapter");
        } else {
            activityAndCategoryAdapter = activityAndCategoryAdapter2;
        }
        activityAndCategoryAdapter.notifyAdapter(i);
    }

    public final void setActivityCodeSelectedForAutoDetection(@Nullable String str) {
        this.A = str;
    }

    public final void setEntityWSession(@Nullable EntityWorkoutSession entityWorkoutSession) {
        this.o = entityWorkoutSession;
    }

    public final void setSelectedForAutoDetection(boolean z) {
        this.z = z;
    }

    public final void showWatchBusyDialog() {
        if (!isAdded() || getView() == null || requireActivity() == null || requireActivity().isFinishing()) {
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.activity_config_failed);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.activity_config_failed)");
        String string2 = getString(R.string.make_sure_no_running_activity);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.make_sure_no_running_activity)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, string2);
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        String string3 = requireActivity().getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string3, "requireActivity().getStr…ndroid.theme.R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentReplaceActivity.y(BottomSheetDialogOneButtonTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void t(final int i) {
        AlertDialog alertDialog = this.y;
        if (alertDialog != null) {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            this.y = null;
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
        View inflate = layoutInflater.inflate(R.layout.dialog_auto_activity_detection_confirmation, (ViewGroup) null);
        this.x = inflate;
        Intrinsics.checkNotNull(inflate);
        dialog.setContentView(inflate);
        View view = this.x;
        Intrinsics.checkNotNull(view);
        ((Button) view.findViewById(R.id.noBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentReplaceActivity.u(dialog, view2);
            }
        });
        View view2 = this.x;
        Intrinsics.checkNotNull(view2);
        ((Button) view2.findViewById(R.id.yesBtn)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                FragmentReplaceActivity.v(dialog, this, i, view3);
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public final void w() {
        if (isAdded()) {
            AlertDialog alertDialog = this.y;
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                this.y = null;
            }
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            final Dialog dialog = new Dialog(context, R.style.DialogTheme);
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
            View inflate = layoutInflater.inflate(R.layout.dialog_activity_confirm, (ViewGroup) null);
            this.x = inflate;
            Intrinsics.checkNotNull(inflate);
            dialog.setContentView(inflate);
            View view = this.x;
            Intrinsics.checkNotNull(view);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            ((Button) view.findViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentReplaceActivity.x(FragmentReplaceActivity.this, dialog, view2);
                }
            });
        }
    }
}
