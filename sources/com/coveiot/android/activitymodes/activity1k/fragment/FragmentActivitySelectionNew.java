package com.coveiot.android.activitymodes.activity1k.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.OneKActivity;
import com.coveiot.android.activitymodes.activity1k.SingletonOneKActivity;
import com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivitiesListModel;
import com.coveiot.android.activitymodes.activity1k.model.ActivityCategoriesModel;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentActivitySelectionViewModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel;
import com.coveiot.android.activitymodes.activity1k.viewmodel.OneKActivityViewModel;
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
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.repository.datasync.domainlogic.SyncManager;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentActivitySelectionNew extends BaseFragment implements ActivitiesSelectionAdapterNew.ItemClickListener, FragmentReArrangeViewModel.ActivityUpdateListener, ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public ActivitiesListModel B;
    @Nullable
    public ActivityCategoriesModel C;
    @Nullable
    public AlertDialog m;
    public CategoryAndActivityModel n;
    public CategoryAndActivityModel o;
    public FragmentReArrangeViewModel p;
    public ActivityAutoRecognitionViewModelWithOneK q;
    @Nullable
    public Snackbar r;
    public OneKActivityViewModel s;
    @Nullable
    public EntityWorkoutSession t;
    @Nullable
    public View u;
    public FragmentActivitySelectionViewModel v;
    @Nullable
    public ActivitiesSelectionAdapterNew x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<ActivitiesListModel> w = new ArrayList();
    @Nullable
    public ArrayList<ActivityCategoriesModel> y = new ArrayList<>();
    @Nullable
    public HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> z = new HashMap<>();
    @Nullable
    public List<CategoryAndActivityModel> A = new ArrayList();
    @NotNull
    public final Observer<List<ActivityCategoriesModel>> D = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.k
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentActivitySelectionNew.r(FragmentActivitySelectionNew.this, (List) obj);
        }
    };
    @NotNull
    public final Observer<List<ActivitiesListModel>> E = new Observer() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.l
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            FragmentActivitySelectionNew.q(FragmentActivitySelectionNew.this, (List) obj);
        }
    };

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentActivitySelectionNew newInstance() {
            return new FragmentActivitySelectionNew();
        }
    }

    public static final void q(FragmentActivitySelectionNew this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissProgress();
        if (list != null) {
            this$0.w.clear();
            this$0.w.addAll(list);
            if (this$0.w.size() > 0) {
                SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                List<CategoryAndActivityModel> categoryAndActivityList = companion.getInstance(requireContext).getCategoryAndActivityList();
                ArrayList arrayList = new ArrayList();
                if (categoryAndActivityList != null) {
                    for (CategoryAndActivityModel categoryAndActivityModel : categoryAndActivityList) {
                        for (ActivitiesListModel activitiesListModel : this$0.w) {
                            if (activitiesListModel.getFwActId() == categoryAndActivityModel.getFwActId()) {
                                arrayList.add(activitiesListModel);
                            }
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.existing_sports_layout)).setVisibility(0);
                    this$0.w.removeAll(arrayList);
                    Context requireContext2 = this$0.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    ArrayList<ActivityCategoriesModel> arrayList2 = this$0.y;
                    Intrinsics.checkNotNull(arrayList2);
                    ActivitiesSelectionAdapterNew activitiesSelectionAdapterNew = new ActivitiesSelectionAdapterNew(requireContext2, arrayList2, arrayList, new ActivitiesSelectionAdapterNew.ItemClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew$getActivityListObserver$1$existingActivitiesAdapter$1
                        @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
                        public void onClicked(@NotNull ActivitiesListModel physicalActivities) {
                            Intrinsics.checkNotNullParameter(physicalActivities, "physicalActivities");
                        }

                        @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
                        public void onInfoClicked(@NotNull ActivitiesListModel physicalActivities) {
                            Intrinsics.checkNotNullParameter(physicalActivities, "physicalActivities");
                        }

                        @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
                        public void onLongClicked(int i) {
                        }
                    }, -1, false);
                    int i = R.id.rcv_existing_sports_activities;
                    ((RecyclerView) this$0._$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(this$0.requireContext()));
                    ((RecyclerView) this$0._$_findCachedViewById(i)).setAdapter(activitiesSelectionAdapterNew);
                } else {
                    ((LinearLayout) this$0._$_findCachedViewById(R.id.existing_sports_layout)).setVisibility(8);
                }
                ActivitiesSelectionAdapterNew activitiesSelectionAdapterNew2 = this$0.x;
                Intrinsics.checkNotNull(activitiesSelectionAdapterNew2);
                activitiesSelectionAdapterNew2.notifyAdapter(this$0.w);
                this$0.dismissProgress();
            }
        }
    }

    public static final void r(FragmentActivitySelectionNew this$0, List list) {
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            ArrayList<ActivityCategoriesModel> arrayList = this$0.y;
            Intrinsics.checkNotNull(arrayList);
            arrayList.clear();
            Iterator it = list.iterator();
            while (true) {
                fragmentActivitySelectionViewModel = null;
                if (!it.hasNext()) {
                    break;
                }
                ActivityCategoriesModel activityCategoriesModel = (ActivityCategoriesModel) it.next();
                Integer categoryId = activityCategoriesModel.getCategoryId();
                SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
                Context requireContext = this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                ActivityCategoriesModel activityCategoriesModel2 = companion.getInstance(requireContext).getActivityCategoriesModel();
                Integer categoryId2 = activityCategoriesModel2 != null ? activityCategoriesModel2.getCategoryId() : null;
                Intrinsics.checkNotNull(categoryId2);
                int intValue = categoryId2.intValue();
                if (categoryId != null && categoryId.intValue() == intValue) {
                    ArrayList<ActivityCategoriesModel> arrayList2 = this$0.y;
                    Intrinsics.checkNotNull(arrayList2);
                    arrayList2.add(activityCategoriesModel);
                }
            }
            FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel2 = this$0.v;
            if (fragmentActivitySelectionViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                fragmentActivitySelectionViewModel2 = null;
            }
            ActivityCategoriesModel activityCategoriesModel3 = this$0.C;
            Integer categoryId3 = activityCategoriesModel3 != null ? activityCategoriesModel3.getCategoryId() : null;
            Intrinsics.checkNotNull(categoryId3);
            fragmentActivitySelectionViewModel2.getActivityListFromDb(categoryId3.intValue());
            FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel3 = this$0.v;
            if (fragmentActivitySelectionViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                fragmentActivitySelectionViewModel = fragmentActivitySelectionViewModel3;
            }
            fragmentActivitySelectionViewModel.getActivityLiveData().observe(this$0, this$0.E);
        }
    }

    public static final void u(FragmentActivitySelectionNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Snackbar snackbar = this$0.r;
        if (snackbar != null) {
            snackbar.getDuration();
        }
    }

    public static final void v(final FragmentActivitySelectionNew this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0.requireContext())) {
            if (AppUtils.isBluetoothEnabled(this$0.requireContext())) {
                ConnectionStatus connectionStatus = BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus();
                ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
                if (connectionStatus == connectionStatus2) {
                    if (!SyncManager.getInstance().isSyncInProgress()) {
                        if (this$0.B != null) {
                            AnalyticsLog analyticsLog = new AnalyticsLog();
                            analyticsLog.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_SUBCAT_NEXT.getValue());
                            analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.SELECT_1K_CATEGORY_SCREEN.getValue());
                            FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.ONEK_ACTIVITY_LIST_SCREEN;
                            analyticsLog.setCVScreenName(screenName.getValue());
                            ActivitiesListModel activitiesListModel = this$0.B;
                            Intrinsics.checkNotNull(activitiesListModel);
                            analyticsLog.setActivityCode(String.valueOf(activitiesListModel.getActivityCode()));
                            CoveAnalyticsManager.Companion companion = CoveAnalyticsManager.Companion;
                            companion.getInstance().logEvent(analyticsLog);
                            ActivityCategoriesModel activityCategoriesModel = this$0.C;
                            Intrinsics.checkNotNull(activityCategoriesModel);
                            analyticsLog.setCategoryId(String.valueOf(activityCategoriesModel.getCategoryId()));
                            SingletonOneKActivity.Companion companion2 = SingletonOneKActivity.Companion;
                            Context requireContext = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            ActivitiesListModel activitiesListModel2 = this$0.B;
                            Intrinsics.checkNotNull(activitiesListModel2);
                            companion2.getInstance(requireContext).setPhysicalActivity(activitiesListModel2);
                            Context requireContext2 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            ActivityCategoriesModel activityCategoriesModel2 = this$0.C;
                            Intrinsics.checkNotNull(activityCategoriesModel2);
                            companion2.getInstance(requireContext2).setActivityCategoriesModel(activityCategoriesModel2);
                            List<CategoryAndActivityModel> list = this$0.A;
                            Intrinsics.checkNotNull(list);
                            CategoryAndActivityModel categoryAndActivityModel = this$0.n;
                            if (categoryAndActivityModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                                categoryAndActivityModel = null;
                            }
                            list.remove(categoryAndActivityModel);
                            Context requireContext3 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            List<CategoryAndActivityModel> list2 = this$0.A;
                            Intrinsics.checkNotNull(list2);
                            companion2.getInstance(requireContext3).setCategoryAndActivityList(list2);
                            Context requireContext4 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                            this$0.C = companion2.getInstance(requireContext4).getActivityCategoriesModel();
                            Context requireContext5 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                            this$0.A = companion2.getInstance(requireContext5).getCategoryAndActivityList();
                            Context requireContext6 = this$0.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext6, "requireContext()");
                            this$0.B = companion2.getInstance(requireContext6).getPhysicalActivity();
                            FragmentActivity activity = this$0.getActivity();
                            Intrinsics.checkNotNull(activity);
                            ViewModel viewModel = ViewModelProviders.of(activity).get(FragmentReArrangeViewModel.class);
                            Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!)\n         …ngeViewModel::class.java)");
                            FragmentReArrangeViewModel fragmentReArrangeViewModel = (FragmentReArrangeViewModel) viewModel;
                            this$0.p = fragmentReArrangeViewModel;
                            if (fragmentReArrangeViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                                fragmentReArrangeViewModel = null;
                            }
                            fragmentReArrangeViewModel.setActivityUpdateListener(this$0);
                            FragmentReArrangeViewModel fragmentReArrangeViewModel2 = this$0.p;
                            if (fragmentReArrangeViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                                fragmentReArrangeViewModel2 = null;
                            }
                            this$0.o = fragmentReArrangeViewModel2.getCategoryAndActivityModel(this$0.C, this$0.B);
                            List<CategoryAndActivityModel> list3 = this$0.A;
                            Intrinsics.checkNotNull(list3);
                            CategoryAndActivityModel categoryAndActivityModel2 = this$0.o;
                            if (categoryAndActivityModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                                categoryAndActivityModel2 = null;
                            }
                            list3.add(0, categoryAndActivityModel2);
                            AnalyticsLog analyticsLog2 = new AnalyticsLog();
                            if (this$0.isAdded()) {
                                BaseFragment.showProgress$default(this$0, false, 1, null);
                            }
                            analyticsLog2.setEventName(FirebaseEventParams.EventName.ONEK_ACTIVITY_EXISITING_CONFIRM.getValue());
                            analyticsLog2.setCVPrevScreenName(screenName.getValue());
                            analyticsLog2.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN.getValue());
                            CategoryAndActivityModel categoryAndActivityModel3 = this$0.n;
                            if (categoryAndActivityModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                                categoryAndActivityModel3 = null;
                            }
                            analyticsLog2.setOldActivityCode(String.valueOf(categoryAndActivityModel3.getActivityCode()));
                            ActivitiesListModel activitiesListModel3 = this$0.B;
                            analyticsLog2.setNewActivityCode(String.valueOf(activitiesListModel3 != null ? activitiesListModel3.getActivityCode() : null));
                            companion.getInstance().logEvent(analyticsLog2);
                            if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() == connectionStatus2) {
                                BleApiManager.getInstance(this$0.getContext()).getBleApi().getData(new GetImageIdListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew$onViewCreated$2$1
                                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                    public void onDataError(@NotNull BleBaseError error) {
                                        Intrinsics.checkNotNullParameter(error, "error");
                                        FragmentActivitySelectionNew fragmentActivitySelectionNew = FragmentActivitySelectionNew.this;
                                        int i = R.id.btn_next;
                                        if (((Button) fragmentActivitySelectionNew._$_findCachedViewById(i)) != null) {
                                            ((Button) FragmentActivitySelectionNew.this._$_findCachedViewById(i)).setEnabled(true);
                                        }
                                        FragmentActivitySelectionNew.this.dismissProgress();
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                    public void onDataResponse(@NotNull BleBaseResponse response) {
                                        CategoryAndActivityModel categoryAndActivityModel4;
                                        FragmentReArrangeViewModel fragmentReArrangeViewModel3;
                                        List<CategoryAndActivityModel> list4;
                                        CategoryAndActivityModel categoryAndActivityModel5;
                                        CategoryAndActivityModel categoryAndActivityModel6;
                                        boolean s;
                                        FragmentReArrangeViewModel fragmentReArrangeViewModel4;
                                        CategoryAndActivityModel categoryAndActivityModel7;
                                        Intrinsics.checkNotNullParameter(response, "response");
                                        if (response.getResponseData() instanceof GetImageListResponse) {
                                            Object responseData = response.getResponseData();
                                            Intrinsics.checkNotNull(responseData);
                                            List<Integer> imageIdList = ((GetImageListResponse) responseData).getImageIdList();
                                            Intrinsics.checkNotNull(imageIdList);
                                            categoryAndActivityModel4 = FragmentActivitySelectionNew.this.o;
                                            FragmentReArrangeViewModel fragmentReArrangeViewModel5 = null;
                                            CategoryAndActivityModel categoryAndActivityModel8 = null;
                                            if (categoryAndActivityModel4 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                                                categoryAndActivityModel4 = null;
                                            }
                                            if (!imageIdList.contains(categoryAndActivityModel4.getDefaultActivityIcon())) {
                                                categoryAndActivityModel5 = FragmentActivitySelectionNew.this.o;
                                                if (categoryAndActivityModel5 == null) {
                                                    Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                                                    categoryAndActivityModel5 = null;
                                                }
                                                if (!imageIdList.contains(categoryAndActivityModel5.getDefaultCategoryIcon())) {
                                                    FragmentActivitySelectionNew fragmentActivitySelectionNew = FragmentActivitySelectionNew.this;
                                                    categoryAndActivityModel6 = fragmentActivitySelectionNew.o;
                                                    if (categoryAndActivityModel6 == null) {
                                                        Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                                                        categoryAndActivityModel6 = null;
                                                    }
                                                    s = fragmentActivitySelectionNew.s(categoryAndActivityModel6);
                                                    if (!s) {
                                                        fragmentReArrangeViewModel4 = FragmentActivitySelectionNew.this.p;
                                                        if (fragmentReArrangeViewModel4 == null) {
                                                            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                                                            fragmentReArrangeViewModel4 = null;
                                                        }
                                                        categoryAndActivityModel7 = FragmentActivitySelectionNew.this.o;
                                                        if (categoryAndActivityModel7 == null) {
                                                            Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                                                        } else {
                                                            categoryAndActivityModel8 = categoryAndActivityModel7;
                                                        }
                                                        fragmentReArrangeViewModel4.saveImageToInternal(categoryAndActivityModel8, 0);
                                                        return;
                                                    }
                                                }
                                            }
                                            fragmentReArrangeViewModel3 = FragmentActivitySelectionNew.this.p;
                                            if (fragmentReArrangeViewModel3 == null) {
                                                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                                            } else {
                                                fragmentReArrangeViewModel5 = fragmentReArrangeViewModel3;
                                            }
                                            list4 = FragmentActivitySelectionNew.this.A;
                                            Intrinsics.checkNotNull(list4);
                                            fragmentReArrangeViewModel5.setConfigureActivityListRequest(list4);
                                            return;
                                        }
                                        FragmentActivitySelectionNew.this.dismissProgress();
                                    }

                                    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                    public void onProgressUpdate(@NotNull ProgressData progress) {
                                        Intrinsics.checkNotNullParameter(progress, "progress");
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        Toast.makeText(this$0.requireContext(), R.string.activity_is_already_available_in_watch, 1).show();
                        return;
                    }
                    Toast.makeText(this$0.getContext(), R.string.syncing_please_wait, 0).show();
                    return;
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

    public static final void x(final FragmentActivitySelectionNew this$0, Dialog mAlertDialog, View view) {
        DeviceIconModel deviceIconModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mAlertDialog, "$mAlertDialog");
        if (!AppUtils.isNetConnected(this$0.getContext())) {
            Toast.makeText(this$0.requireContext(), R.string.please_check_network, 0).show();
        } else if (!AppUtils.isBluetoothEnabled(this$0.getContext())) {
            Toast.makeText(this$0.requireContext(), R.string.bluetooth_off_message, 0).show();
        } else if (BleApiManager.getInstance(this$0.getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0.requireContext(), R.string.band_not_connected, 0).show();
        } else {
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            if (this$0.n == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
            }
            CategoryAndActivityModel categoryAndActivityModel = this$0.n;
            Integer num = null;
            if (categoryAndActivityModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                categoryAndActivityModel = null;
            }
            if (!categoryAndActivityModel.getInbuilt() && BleApiManager.getInstance(this$0.getContext()).getBleApi() != null) {
                BaseFragment.showProgress$default(this$0, false, 1, null);
                BleApi bleApi = BleApiManager.getInstance(this$0.getContext()).getBleApi();
                CategoryAndActivityModel categoryAndActivityModel2 = this$0.n;
                if (categoryAndActivityModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                    categoryAndActivityModel2 = null;
                }
                List<DeviceIconModel> deviceIconModels = categoryAndActivityModel2.getDeviceIconModels();
                if (deviceIconModels != null && (deviceIconModel = deviceIconModels.get(0)) != null) {
                    num = deviceIconModel.getRefId();
                }
                Intrinsics.checkNotNull(num);
                bleApi.setUserSettings(new DeleteImageRequest(num.intValue()), new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew$showBluetoothDialog$2$1
                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsError(@NotNull BleBaseError error) {
                        Intrinsics.checkNotNullParameter(error, "error");
                        FragmentActivitySelectionNew.this.dismissProgress();
                        FragmentActivitySelectionNew.this.t();
                    }

                    @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                    public void onSettingsResponse(@NotNull BleBaseResponse response) {
                        CategoryAndActivityModel categoryAndActivityModel3;
                        CategoryAndActivityModel categoryAndActivityModel4;
                        DeviceIconModel deviceIconModel2;
                        Intrinsics.checkNotNullParameter(response, "response");
                        categoryAndActivityModel3 = FragmentActivitySelectionNew.this.n;
                        Integer num2 = null;
                        if (categoryAndActivityModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                            categoryAndActivityModel3 = null;
                        }
                        List<DeviceIconModel> deviceIconModels2 = categoryAndActivityModel3.getDeviceIconModels();
                        Integer valueOf = deviceIconModels2 != null ? Integer.valueOf(deviceIconModels2.size()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 1) {
                            BleApi bleApi2 = BleApiManager.getInstance(FragmentActivitySelectionNew.this.getContext()).getBleApi();
                            categoryAndActivityModel4 = FragmentActivitySelectionNew.this.n;
                            if (categoryAndActivityModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("selectedActivityAndCategory");
                                categoryAndActivityModel4 = null;
                            }
                            List<DeviceIconModel> deviceIconModels3 = categoryAndActivityModel4.getDeviceIconModels();
                            if (deviceIconModels3 != null && (deviceIconModel2 = deviceIconModels3.get(1)) != null) {
                                num2 = deviceIconModel2.getRefId();
                            }
                            Intrinsics.checkNotNull(num2);
                            DeleteImageRequest deleteImageRequest = new DeleteImageRequest(num2.intValue());
                            final FragmentActivitySelectionNew fragmentActivitySelectionNew = FragmentActivitySelectionNew.this;
                            bleApi2.setUserSettings(deleteImageRequest, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew$showBluetoothDialog$2$1$onSettingsResponse$1
                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsError(@NotNull BleBaseError error) {
                                    Intrinsics.checkNotNullParameter(error, "error");
                                    FragmentActivitySelectionNew.this.dismissProgress();
                                    FragmentActivitySelectionNew.this.t();
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                                public void onSettingsResponse(@NotNull BleBaseResponse response2) {
                                    Intrinsics.checkNotNullParameter(response2, "response");
                                    FragmentActivitySelectionNew.this.dismissProgress();
                                    FragmentActivitySelectionNew.this.t();
                                }
                            });
                            return;
                        }
                        FragmentActivitySelectionNew.this.dismissProgress();
                        FragmentActivitySelectionNew.this.t();
                    }
                });
                return;
            }
            this$0.t();
        }
    }

    public static final void y(BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, FragmentActivitySelectionNew this$0, View view) {
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
    public final EntityWorkoutSession getEntityWSession() {
        return this.t;
    }

    @Nullable
    public final HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> getExpandableListDetail() {
        return this.z;
    }

    @Nullable
    public final ArrayList<ActivityCategoriesModel> getExpandableListTitle() {
        return this.y;
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onActivityUpload() {
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.getInstance(requireContext).getIsSelectedForAutoActivityDetection()) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            AutoActivityDetectionData autoActivityDetectionData = UserDataManager.getInstance(activity).getAutoActivityDetectionData();
            if (autoActivityDetectionData != null) {
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                String autoActivityDetectionCode = companion.getInstance(requireContext2).getAutoActivityDetectionCode();
                Unit unit = null;
                ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = null;
                if (autoActivityDetectionCode != null) {
                    autoActivityDetectionData.getActivities()[WorkoutUtils.INSTANCE.getAutoActivityDetectionFWOrderForAnActivity(autoActivityDetectionCode)] = 0;
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK2 = this.q;
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

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
    public void onClicked(@NotNull ActivitiesListModel physicalActivities) {
        Intrinsics.checkNotNullParameter(physicalActivities, "physicalActivities");
        this.B = physicalActivities;
        int i = R.id.btnNext;
        ((Button) _$_findCachedViewById(i)).setEnabled(true);
        ((Button) _$_findCachedViewById(i)).performClick();
        hideKeyBoard();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_activities_seclection_new, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        Snackbar snackbar;
        super.onDestroyView();
        Snackbar snackbar2 = this.r;
        if (snackbar2 != null) {
            Boolean valueOf = snackbar2 != null ? Boolean.valueOf(snackbar2.isShown()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.booleanValue() && (snackbar = this.r) != null) {
                snackbar.dismiss();
            }
        }
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.viewmodel.FragmentReArrangeViewModel.ActivityUpdateListener
    public void onImageUpload(int i) {
        if (BleApiManager.getInstance(getContext()).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(getContext(), R.string.band_not_connected, 1).show();
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel = null;
        CategoryAndActivityModel categoryAndActivityModel = null;
        FragmentReArrangeViewModel fragmentReArrangeViewModel2 = null;
        if (i == 0) {
            CategoryAndActivityModel categoryAndActivityModel2 = this.o;
            if (categoryAndActivityModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                categoryAndActivityModel2 = null;
            }
            List<DeviceIconModel> deviceIconModels = categoryAndActivityModel2.getDeviceIconModels();
            Integer valueOf = deviceIconModels != null ? Integer.valueOf(deviceIconModels.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 1) {
                FragmentReArrangeViewModel fragmentReArrangeViewModel3 = this.p;
                if (fragmentReArrangeViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
                    fragmentReArrangeViewModel3 = null;
                }
                CategoryAndActivityModel categoryAndActivityModel3 = this.o;
                if (categoryAndActivityModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("newActivityCategory");
                } else {
                    categoryAndActivityModel = categoryAndActivityModel3;
                }
                fragmentReArrangeViewModel3.saveImageToInternal(categoryAndActivityModel, 1);
                return;
            }
            FragmentReArrangeViewModel fragmentReArrangeViewModel4 = this.p;
            if (fragmentReArrangeViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
            } else {
                fragmentReArrangeViewModel2 = fragmentReArrangeViewModel4;
            }
            List<CategoryAndActivityModel> list = this.A;
            Intrinsics.checkNotNull(list);
            fragmentReArrangeViewModel2.setConfigureActivityListRequest(list);
            return;
        }
        FragmentReArrangeViewModel fragmentReArrangeViewModel5 = this.p;
        if (fragmentReArrangeViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelRearrange");
        } else {
            fragmentReArrangeViewModel = fragmentReArrangeViewModel5;
        }
        List<CategoryAndActivityModel> list2 = this.A;
        Intrinsics.checkNotNull(list2);
        fragmentReArrangeViewModel.setConfigureActivityListRequest(list2);
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
    public void onInfoClicked(@NotNull ActivitiesListModel physicalActivities) {
        Intrinsics.checkNotNullParameter(physicalActivities, "physicalActivities");
        String descInMetric = physicalActivities.getDescInMetric();
        Intrinsics.checkNotNull(descInMetric);
        Snackbar make = Snackbar.make((ConstraintLayout) _$_findCachedViewById(R.id.container), descInMetric, -2);
        this.r = make;
        Intrinsics.checkNotNull(make);
        make.setAction("Close", new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivitySelectionNew.u(FragmentActivitySelectionNew.this, view);
            }
        });
        Snackbar snackbar = this.r;
        Intrinsics.checkNotNull(snackbar);
        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        Snackbar snackbar2 = this.r;
        Intrinsics.checkNotNull(snackbar2);
        snackbar2.getView().setBackgroundColor(getResources().getColor(R.color.dialog_bg_color));
        Snackbar snackbar3 = this.r;
        Intrinsics.checkNotNull(snackbar3);
        View findViewById = snackbar3.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        ((TextView) findViewById).setTextColor(getResources().getColor(R.color.white));
        Snackbar snackbar4 = this.r;
        Intrinsics.checkNotNull(snackbar4);
        if (snackbar4.isShown()) {
            return;
        }
        Snackbar snackbar5 = this.r;
        Intrinsics.checkNotNull(snackbar5);
        snackbar5.show();
    }

    @Override // com.coveiot.android.activitymodes.activity1k.adapter.ActivitiesSelectionAdapterNew.ItemClickListener
    public void onLongClicked(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.ONEK_ACTIVITY_LIST_SCREEN.getValue());
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
        SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.C = companion.getInstance(requireContext).getActivityCategoriesModel();
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        this.A = companion.getInstance(requireContext2).getCategoryAndActivityList();
        Context requireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
        CategoryAndActivityModel selectedActivity = companion.getInstance(requireContext3).getSelectedActivity();
        Intrinsics.checkNotNull(selectedActivity);
        this.n = selectedActivity;
        FragmentActivity activity2 = getActivity();
        Intrinsics.checkNotNull(activity2);
        ViewModel viewModel = ViewModelProviders.of(activity2).get(ActivityAutoRecognitionViewModelWithOneK.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(activity!!)\n         …odelWithOneK::class.java)");
        ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = (ActivityAutoRecognitionViewModelWithOneK) viewModel;
        this.q = activityAutoRecognitionViewModelWithOneK;
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel = null;
        if (activityAutoRecognitionViewModelWithOneK == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelAutoActivityDetection");
            activityAutoRecognitionViewModelWithOneK = null;
        }
        activityAutoRecognitionViewModelWithOneK.setSettingUpdateListener(this);
        FragmentActivity activity3 = getActivity();
        Intrinsics.checkNotNull(activity3);
        ViewModel viewModel2 = ViewModelProviders.of(activity3).get(FragmentActivitySelectionViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(activity!!).get(Fragm…ionViewModel::class.java)");
        this.v = (FragmentActivitySelectionViewModel) viewModel2;
        FragmentActivity activity4 = getActivity();
        Intrinsics.checkNotNull(activity4);
        OneKActivityViewModel viewModel3 = ((OneKActivity) activity4).getViewModel();
        Intrinsics.checkNotNull(viewModel3);
        this.s = viewModel3;
        Context requireContext4 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
        ArrayList<ActivityCategoriesModel> arrayList = this.y;
        Intrinsics.checkNotNull(arrayList);
        ActivitiesSelectionAdapterNew activitiesSelectionAdapterNew = new ActivitiesSelectionAdapterNew(requireContext4, arrayList, CollectionsKt__CollectionsKt.emptyList(), this, -1, false, 32, null);
        this.x = activitiesSelectionAdapterNew;
        Intrinsics.checkNotNull(activitiesSelectionAdapterNew);
        OneKActivityViewModel oneKActivityViewModel = this.s;
        if (oneKActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onkActivityViewModel");
            oneKActivityViewModel = null;
        }
        activitiesSelectionAdapterNew.setActivityInfoList(oneKActivityViewModel.getActivityInfoList());
        int i = R.id.erv_activity;
        ((RecyclerView) _$_findCachedViewById(i)).setLayoutManager(new LinearLayoutManager(requireContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        ActivitiesSelectionAdapterNew activitiesSelectionAdapterNew2 = this.x;
        Intrinsics.checkNotNull(activitiesSelectionAdapterNew2);
        recyclerView.setAdapter(activitiesSelectionAdapterNew2);
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel2 = this.v;
        if (fragmentActivitySelectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivitySelectionViewModel2 = null;
        }
        fragmentActivitySelectionViewModel2.getCategoryListFromDb();
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel3 = this.v;
        if (fragmentActivitySelectionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivitySelectionViewModel3 = null;
        }
        fragmentActivitySelectionViewModel3.getCategoryLiveData().observe(this, this.D);
        int i2 = R.id.edt_search;
        EditText editText = (EditText) _$_findCachedViewById(i2);
        Intrinsics.checkNotNull(editText);
        editText.addTextChangedListener(new FragmentActivitySelectionNew$onViewCreated$1(this));
        Button button = (Button) _$_findCachedViewById(R.id.btnNext);
        Intrinsics.checkNotNull(button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentActivitySelectionNew.v(FragmentActivitySelectionNew.this, view2);
            }
        });
        EditText editText2 = (EditText) _$_findCachedViewById(i2);
        Intrinsics.checkNotNull(editText2);
        editText2.addTextChangedListener(new FragmentActivitySelectionNew$onViewCreated$3(this));
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel4 = this.v;
        if (fragmentActivitySelectionViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            fragmentActivitySelectionViewModel4 = null;
        }
        fragmentActivitySelectionViewModel4.getCategoryListFromDb();
        FragmentActivitySelectionViewModel fragmentActivitySelectionViewModel5 = this.v;
        if (fragmentActivitySelectionViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            fragmentActivitySelectionViewModel = fragmentActivitySelectionViewModel5;
        }
        fragmentActivitySelectionViewModel.getActivityLiveData().observe(this, this.E);
        ((EditText) _$_findCachedViewById(i2)).setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.FragmentActivitySelectionNew$onViewCreated$4
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(@Nullable TextView textView, int i3, @Nullable KeyEvent keyEvent) {
                if (i3 == 3) {
                    FragmentActivitySelectionNew.this.hideKeyBoard();
                    return true;
                }
                return false;
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

    public final boolean s(CategoryAndActivityModel categoryAndActivityModel) {
        return false;
    }

    public final void setEntityWSession(@Nullable EntityWorkoutSession entityWorkoutSession) {
        this.t = entityWorkoutSession;
    }

    public final void setExpandableListDetail(@Nullable HashMap<ActivityCategoriesModel, List<ActivitiesListModel>> hashMap) {
        this.z = hashMap;
    }

    public final void setExpandableListTitle(@Nullable ArrayList<ActivityCategoriesModel> arrayList) {
        this.y = arrayList;
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
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentActivitySelectionNew.y(BottomSheetDialogOneButtonTitleMessage.this, this, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.setCancelable(false);
        bottomSheetDialogOneButtonTitleMessage.setCancelableOutside(false);
        if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final void t() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public final void w() {
        if (isAdded()) {
            AlertDialog alertDialog = this.m;
            if (alertDialog != null) {
                if (alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
                this.m = null;
            }
            final Dialog dialog = new Dialog(requireContext(), R.style.DialogTheme);
            LayoutInflater layoutInflater = getLayoutInflater();
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "this.layoutInflater");
            this.u = layoutInflater.inflate(R.layout.dialog_activity_confirm, (ViewGroup) null);
            SingletonOneKActivity.Companion companion = SingletonOneKActivity.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            CategoryAndActivityModel selectedActivity = companion.getInstance(requireContext).getSelectedActivity();
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ActivitiesListModel physicalActivity = companion.getInstance(requireContext2).getPhysicalActivity();
            View view = this.u;
            Intrinsics.checkNotNull(view);
            TextView textView = (TextView) view.findViewById(R.id.txt_msg);
            int i = R.string.activity_available_on_watch;
            Object[] objArr = new Object[2];
            objArr[0] = selectedActivity != null ? selectedActivity.getTitleInMetric() : null;
            objArr[1] = physicalActivity != null ? physicalActivity.getTitleInMetric() : null;
            textView.setText(getString(i, objArr));
            View view2 = this.u;
            Intrinsics.checkNotNull(view2);
            TextView textView2 = (TextView) view2.findViewById(R.id.tv_warning_auto_detection);
            Context requireContext3 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            if (companion.getInstance(requireContext3).getIsSelectedForAutoActivityDetection()) {
                textView2.setVisibility(0);
                int i2 = R.string.walking_will_not_be_auto_detected_as_it_has_been_removed_from_the_watch;
                Object[] objArr2 = new Object[1];
                objArr2[0] = selectedActivity != null ? selectedActivity.getTitleInMetric() : null;
                textView2.setText(getString(i2, objArr2));
            } else {
                textView2.setVisibility(8);
            }
            View view3 = this.u;
            Intrinsics.checkNotNull(view3);
            dialog.setContentView(view3);
            View view4 = this.u;
            Intrinsics.checkNotNull(view4);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            ((Button) view4.findViewById(R.id.done_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.activity1k.fragment.i
                @Override // android.view.View.OnClickListener
                public final void onClick(View view5) {
                    FragmentActivitySelectionNew.x(FragmentActivitySelectionNew.this, dialog, view5);
                }
            });
        }
    }
}
