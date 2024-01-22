package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ActivityTypes;
import com.coveiot.android.bleabstract.request.AutoActivityRecognitionListRequest;
import com.coveiot.android.bleabstract.request.AutoActivityRecognitionSettingRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.models.AutoRecognitonModel;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AutoRecognize;
import com.coveiot.coveaccess.model.server.AutoRecognizeActivity;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoActivityRecognizeReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoRecognizeSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes5.dex */
public final class ActivityAutoRecognitionViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5140a;
    @NotNull
    public MutableLiveData<ArrayList<AutoRecognitonModel>> b;
    public DialogListener dialogListener;

    public ActivityAutoRecognitionViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5140a = context;
        this.b = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f5140a;
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

    public final void getListOfActivities() {
        String name;
        ArrayList<AutoRecognitonModel> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        if (UserDataManager.getInstance(this.f5140a).getAutoRecognitionList() == null) {
            arrayList.addAll(PayUtils.INSTANCE.getDefaultAutoRecogActivities());
            int size = arrayList.size();
            while (i < size) {
                ActivityTypes activityTypes = arrayList.get(i).getActivityTypes();
                String capitalize = (activityTypes == null || (name = activityTypes.name()) == null) ? null : kotlin.text.m.capitalize(name);
                Intrinsics.checkNotNull(capitalize);
                arrayList2.add(new AutoRecognitionData(capitalize, arrayList.get(i).isSelected()));
                i++;
            }
            UserDataManager.getInstance(this.f5140a).saveAutoRecognitionList(arrayList2);
        } else {
            List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(this.f5140a).getAutoRecognitionList();
            int size2 = autoRecognitionList.size();
            while (i < size2) {
                arrayList.add(new AutoRecognitonModel(PayUtils.INSTANCE.getActivityTypesIDO(autoRecognitionList.get(i).getTypeName()), autoRecognitionList.get(i).isEnabled(), null, 4, null));
                i++;
            }
        }
        this.b.postValue(arrayList);
    }

    @NotNull
    public final MutableLiveData<ArrayList<AutoRecognitonModel>> getListOfActivityLiveData() {
        return this.b;
    }

    public final void senDataToServer(@NotNull ArrayList<AutoRecognitionData> listOfActivitiesPref) {
        Intrinsics.checkNotNullParameter(listOfActivitiesPref, "listOfActivitiesPref");
        ArrayList arrayList = new ArrayList();
        Iterator<AutoRecognitionData> it = listOfActivitiesPref.iterator();
        while (it.hasNext()) {
            AutoRecognitionData next = it.next();
            if (next.isEnabled()) {
                arrayList.add(next.getTypeName());
            }
        }
        SaveAutoActivityRecognizeReq saveAutoActivityRecognizeReq = new SaveAutoActivityRecognizeReq();
        AutoRecognize autoRecognize = new AutoRecognize();
        ArrayList arrayList2 = new ArrayList();
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                AutoRecognizeActivity autoRecognizeActivity = new AutoRecognizeActivity();
                autoRecognizeActivity.setType((String) it2.next());
                arrayList2.add(autoRecognizeActivity);
            }
            autoRecognize.setActivities(arrayList2);
        }
        saveAutoActivityRecognizeReq.setAutoRecognize(autoRecognize);
        CoveUserDeviceSettings.saveAutoRecognizeSettings(saveAutoActivityRecognizeReq, new CoveApiListener<SaveAutoRecognizeSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityAutoRecognitionViewModel$senDataToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                ActivityAutoRecognitionViewModel.this.getDialogListener().onDismiss();
                ActivityAutoRecognitionViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveAutoRecognizeSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                ActivityAutoRecognitionViewModel.this.getDialogListener().onDismiss();
                if (object.getCode() == 200) {
                    ActivityAutoRecognitionViewModel.this.getDialogListener().showSuccessDialog();
                    LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, " auto recognize saved successfully");
                    return;
                }
                ActivityAutoRecognitionViewModel.this.getDialogListener().showErrorDialog();
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
            }
        });
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void setAutoRecognitionToWatch(@NotNull ArrayList<AutoRecognitonModel> activityList) {
        String name;
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        ArrayList arrayList = new ArrayList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        int size = activityList.size();
        for (int i = 0; i < size; i++) {
            AutoActivityRecognitionSettingRequest autoActivityRecognitionSettingRequest = new AutoActivityRecognitionSettingRequest();
            ActivityTypes activityTypes = activityList.get(i).getActivityTypes();
            Intrinsics.checkNotNull(activityTypes);
            autoActivityRecognitionSettingRequest.setActivityTpe(activityTypes);
            autoActivityRecognitionSettingRequest.setEnabled(activityList.get(i).isSelected());
            arrayList.add(autoActivityRecognitionSettingRequest);
            ArrayList arrayList2 = (ArrayList) objectRef.element;
            ActivityTypes activityTypes2 = activityList.get(i).getActivityTypes();
            String capitalize = (activityTypes2 == null || (name = activityTypes2.name()) == null) ? null : kotlin.text.m.capitalize(name);
            Intrinsics.checkNotNull(capitalize);
            arrayList2.add(new AutoRecognitionData(capitalize, activityList.get(i).isSelected()));
        }
        BleApiManager.getInstance(this.f5140a).getBleApi().setUserSettings(new AutoActivityRecognitionListRequest.Builder().setAutoActivityRecognitionList(arrayList).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityAutoRecognitionViewModel$setAutoRecognitionToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                ActivityAutoRecognitionViewModel.this.getDialogListener().onDismiss();
                ActivityAutoRecognitionViewModel.this.getDialogListener().showErrorDialog();
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(ActivityAutoRecognitionViewModel.this.getContext()).saveAutoRecognitionList(objectRef.element);
                ActivityAutoRecognitionViewModel.this.senDataToServer(objectRef.element);
            }
        });
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setListOfActivityLiveData(@NotNull MutableLiveData<ArrayList<AutoRecognitonModel>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }
}
