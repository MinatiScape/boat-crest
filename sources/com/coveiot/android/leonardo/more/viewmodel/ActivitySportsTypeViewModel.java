package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityTypeModel;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetActivityListRequest;
import com.coveiot.android.bleabstract.request.SetActivityListRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetActivityListResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.model.ShowHideTypeModel;
import com.coveiot.android.leonardo.more.models.ShowHideListModel;
import com.coveiot.android.leonardo.more.models.SportsType;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.ActivitySession;
import com.coveiot.coveaccess.model.server.ActivitySiteIndoorOutdoor;
import com.coveiot.coveaccess.model.server.EnabledTypeSports;
import com.coveiot.coveaccess.userdevicesetting.SaveActivitySessionReq;
import com.coveiot.coveaccess.userdevicesetting.SaveActivitySessionRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ShowHideData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivitySportsTypeViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5164a;
    @NotNull
    public MutableLiveData<ShowHideListModel> b;
    public DialogListener dialogListener;

    public ActivitySportsTypeViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5164a = context;
        this.b = new MutableLiveData<>();
    }

    public final void b(ArrayList<ShowHideTypeModel> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<ShowHideTypeModel> it = arrayList.iterator();
        while (it.hasNext()) {
            ShowHideTypeModel next = it.next();
            ShowHideData showHideData = new ShowHideData();
            showHideData.setShowOrHideText(next.getShowOrHideText());
            showHideData.setTypeName(next.getTypeName());
            arrayList2.add(showHideData);
        }
        UserDataManager.getInstance(this.f5164a).saveSportsTypeList(arrayList2);
    }

    @NotNull
    public final Context getContext() {
        return this.f5164a;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void getSupportedListFromWatch() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ArrayList();
        BleApiManager.getInstance(this.f5164a).getBleApi().getData(new GetActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySportsTypeViewModel$getSupportedListFromWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivitySportsTypeViewModel.this.getDialogListener().onDismiss();
                Toast.makeText(ActivitySportsTypeViewModel.this.getContext(), ActivitySportsTypeViewModel.this.getContext().getResources().getString(R.string.some_thing_went_wrong), 0).show();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String activityNameListTouch;
                String activityNameListTouch2;
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.getResponseData() instanceof GetActivityListResponse) {
                    ArrayList arrayList = new ArrayList();
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData);
                    List<ActivityTypeModel> configuredActivityList = ((GetActivityListResponse) responseData).getConfiguredActivityList();
                    Object responseData2 = response.getResponseData();
                    Intrinsics.checkNotNull(responseData2);
                    List<ActivityTypeModel> supportedActivityList = ((GetActivityListResponse) responseData2).getSupportedActivityList();
                    for (ActivityTypeModel activityTypeModel : configuredActivityList) {
                        arrayList.add(Integer.valueOf(activityTypeModel.getType()));
                    }
                    int size = supportedActivityList.size();
                    for (int i = 0; i < size; i++) {
                        if (arrayList.contains(Integer.valueOf(supportedActivityList.get(i).getType()))) {
                            DeviceUtils.Companion companion = DeviceUtils.Companion;
                            if (companion.isIDODevice(ActivitySportsTypeViewModel.this.getContext())) {
                                String activityNameListIDO = PayUtils.INSTANCE.getActivityNameListIDO(supportedActivityList.get(i).getType(), ActivitySportsTypeViewModel.this.getContext());
                                Intrinsics.checkNotNull(activityNameListIDO);
                                objectRef2.element.add(new ShowHideTypeModel(activityNameListIDO, Integer.valueOf((int) R.drawable.hide_icon), ActivitySportsTypeViewModel.this.getContext().getResources().getString(R.string.hide)));
                            } else if (companion.isTouchELXDevice(ActivitySportsTypeViewModel.this.getContext()) && (activityNameListTouch2 = PayUtils.INSTANCE.getActivityNameListTouch(supportedActivityList.get(i).getType(), ActivitySportsTypeViewModel.this.getContext())) != null) {
                                objectRef2.element.add(new ShowHideTypeModel(activityNameListTouch2, Integer.valueOf((int) R.drawable.hide_icon), ActivitySportsTypeViewModel.this.getContext().getResources().getString(R.string.hide)));
                            }
                        } else {
                            DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                            if (companion2.isIDODevice(ActivitySportsTypeViewModel.this.getContext())) {
                                objectRef.element.add(new ShowHideTypeModel(PayUtils.INSTANCE.getActivityNameListIDO(supportedActivityList.get(i).getType(), ActivitySportsTypeViewModel.this.getContext()), Integer.valueOf((int) R.drawable.show_icon), ActivitySportsTypeViewModel.this.getContext().getResources().getString(R.string.show)));
                            } else if (companion2.isTouchELXDevice(ActivitySportsTypeViewModel.this.getContext()) && (activityNameListTouch = PayUtils.INSTANCE.getActivityNameListTouch(supportedActivityList.get(i).getType(), ActivitySportsTypeViewModel.this.getContext())) != null) {
                                objectRef.element.add(new ShowHideTypeModel(activityNameListTouch, Integer.valueOf((int) R.drawable.show_icon), ActivitySportsTypeViewModel.this.getContext().getResources().getString(R.string.show)));
                            }
                        }
                    }
                    ActivitySportsTypeViewModel.this.b(objectRef2.element);
                    ActivitySportsTypeViewModel.this.getSupportedListLiveData().postValue(new ShowHideListModel(objectRef2.element, objectRef.element));
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    @NotNull
    public final MutableLiveData<ShowHideListModel> getSupportedListLiveData() {
        return this.b;
    }

    public final void saveSportsType(@NotNull ArrayList<ActivityTypeModel> menuList) {
        Intrinsics.checkNotNullParameter(menuList, "menuList");
        ArrayList arrayList = new ArrayList();
        SaveActivitySessionReq saveActivitySessionReq = new SaveActivitySessionReq();
        ActivitySession activitySession = new ActivitySession();
        Iterator<ActivityTypeModel> it = menuList.iterator();
        while (it.hasNext()) {
            ActivityTypeModel next = it.next();
            EnabledTypeSports enabledTypeSports = new EnabledTypeSports();
            String str = null;
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            if (companion.isIDODevice(this.f5164a)) {
                str = PayUtils.INSTANCE.getActivityNameListIDO(next.getType(), this.f5164a);
            } else if (companion.isTouchELXDevice(this.f5164a)) {
                str = PayUtils.INSTANCE.getActivityNameListTouch(next.getType(), this.f5164a);
            }
            if (str != null) {
                if (StringsKt__StringsKt.contains((CharSequence) str, (CharSequence) "Indoor", true)) {
                    enabledTypeSports.setActivitySite(ActivitySiteIndoorOutdoor.INDOOR.getActivitySite());
                } else if (StringsKt__StringsKt.contains((CharSequence) str, (CharSequence) "Outdoor", true)) {
                    enabledTypeSports.setActivitySite(ActivitySiteIndoorOutdoor.OUTDOOR.getActivitySite());
                } else if (kotlin.text.m.equals(str, SportsType.SPORT_TYPE_POOL_SWIM.getType(), true)) {
                    enabledTypeSports.setActivitySite(ActivitySiteIndoorOutdoor.INDOOR.getActivitySite());
                } else if (kotlin.text.m.equals(str, SportsType.SPORT_TYPE_WATER_SWIM.getType(), true)) {
                    enabledTypeSports.setActivitySite(ActivitySiteIndoorOutdoor.OUTDOOR.getActivitySite());
                }
                enabledTypeSports.setSessionType(PayUtils.INSTANCE.getSessionType(str));
                arrayList.add(enabledTypeSports);
            }
        }
        activitySession.setEnabledTypes(arrayList);
        saveActivitySessionReq.setActivitySession(activitySession);
        CoveUserDeviceSettings.saveActivitySessionSettings(saveActivitySessionReq, new CoveApiListener<SaveActivitySessionRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySportsTypeViewModel$saveSportsType$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ActivitySportsTypeViewModel.this.getDialogListener().onDismiss();
                ActivitySportsTypeViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveActivitySessionRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ActivitySportsTypeViewModel.this.getDialogListener().onDismiss();
                ActivitySportsTypeViewModel.this.getDialogListener().showSuccessDialog();
            }
        });
    }

    public final void sendShowListToWatch(@NotNull final ArrayList<ActivityTypeModel> menuList, @NotNull final ArrayList<ShowHideTypeModel> showSportsTypeList) {
        Intrinsics.checkNotNullParameter(menuList, "menuList");
        Intrinsics.checkNotNullParameter(showSportsTypeList, "showSportsTypeList");
        BleApiManager.getInstance(this.f5164a).getBleApi().setUserSettings(new SetActivityListRequest(menuList, showSportsTypeList.size()), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivitySportsTypeViewModel$sendShowListToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivitySportsTypeViewModel.this.getDialogListener().onDismiss();
                ActivitySportsTypeViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ActivitySportsTypeViewModel.this.b(showSportsTypeList);
                ActivitySportsTypeViewModel.this.saveSportsType(menuList);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setSupportedListLiveData(@NotNull MutableLiveData<ShowHideListModel> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
