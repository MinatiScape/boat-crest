package com.coveiot.android.activitymodes.autodetection.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.ResponseListener;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK;
import com.coveiot.android.activitymodes.models.AutoActivityDetectionServerInputData;
import com.coveiot.android.activitymodes.models.AutoRecognitonModelOneK;
import com.coveiot.android.activitymodes.utils.DialogListener;
import com.coveiot.android.activitymodes.utils.WorkoutUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.AutoActivityDetectionModel;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetConfiguredActivityListRequest;
import com.coveiot.android.bleabstract.request.SetAutoActivityDetectionSettingsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.AutoRecognize;
import com.coveiot.coveaccess.model.server.AutoRecognizeActivity;
import com.coveiot.coveaccess.model.server.AutoRecognizeSchedule;
import com.coveiot.coveaccess.model.server.AutoRecognizeSlot;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoActivityRecognizeReq;
import com.coveiot.coveaccess.userdevicesetting.SaveAutoRecognizeSettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes2.dex */
public final class ActivityAutoRecognitionViewModelWithOneK extends AndroidViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int m = TypedValues.CycleType.TYPE_EASING;
    public static final int n = 720;
    public static final int o = 720;
    public static final int p = 960;
    public static final int q = 960;
    public static final int r = 1260;
    public static final int s = 1260;
    public static final int t = TypedValues.CycleType.TYPE_EASING;
    @NotNull
    public Context d;
    @NotNull
    public final String e;
    @NotNull
    public final String f;
    @Nullable
    public DialogListener g;
    @Nullable
    public SettingsUpdateListener h;
    @Nullable
    public ConfiguredActivities.ActivityInfo[] i;
    @NotNull
    public MutableLiveData<ArrayList<AutoRecognitonModelOneK>> j;
    @Nullable
    public List<EntityPhysicalActivities> k;
    @NotNull
    public final List<AutoActivityDetectionServerInputData> l;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getET_12PM_4PM() {
            return ActivityAutoRecognitionViewModelWithOneK.p;
        }

        public final int getET_4PM_9PM() {
            return ActivityAutoRecognitionViewModelWithOneK.r;
        }

        public final int getET_7AM_12PM() {
            return ActivityAutoRecognitionViewModelWithOneK.n;
        }

        public final int getET_9PM_7AM() {
            return ActivityAutoRecognitionViewModelWithOneK.t;
        }

        public final int getST_12PM_4PM() {
            return ActivityAutoRecognitionViewModelWithOneK.o;
        }

        public final int getST_4PM_9PM() {
            return ActivityAutoRecognitionViewModelWithOneK.q;
        }

        public final int getST_7AM_12PM() {
            return ActivityAutoRecognitionViewModelWithOneK.m;
        }

        public final int getST_9PM_7AM() {
            return ActivityAutoRecognitionViewModelWithOneK.s;
        }
    }

    /* loaded from: classes2.dex */
    public interface SettingsUpdateListener {
        void onAutoDetectionFailure(@Nullable String str);

        void onAutoDetectionSuccess();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityAutoRecognitionViewModelWithOneK(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.e = "";
        this.f = "-";
        this.d = application;
        this.j = new MutableLiveData<>();
        WorkoutUtils workoutUtils = WorkoutUtils.INSTANCE;
        this.l = CollectionsKt__CollectionsKt.mutableListOf(new AutoActivityDetectionServerInputData("17-262", workoutUtils.getAutoActivityDetectionFWOrderForAnActivity("17-262")), new AutoActivityDetectionServerInputData("12-20", workoutUtils.getAutoActivityDetectionFWOrderForAnActivity("12-20")));
    }

    public final AutoRecognitionData b(int i) {
        List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(this.d).getAutoRecognitionList();
        AutoRecognitionData autoRecognitionData = null;
        if (autoRecognitionList != null) {
            int i2 = 0;
            for (Object obj : autoRecognitionList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                AutoRecognitionData autoRecognitionData2 = (AutoRecognitionData) obj;
                if (autoRecognitionData2.getByteOrderInFW() == i) {
                    autoRecognitionData = autoRecognitionData2;
                }
                i2 = i3;
            }
        }
        return autoRecognitionData;
    }

    public final void getActivityListFromServer() {
        DialogListener dialogListener = this.g;
        if (dialogListener != null) {
            dialogListener.showSuccessDialog();
        }
    }

    public final void getCurrentActivitiesOnWatch(@NotNull final ResponseListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        if (BleApiManager.getInstance(this.d).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.d).getBleApi().getData(new GetConfiguredActivityListRequest(), new DataResultListener() { // from class: com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK$getCurrentActivitiesOnWatch$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    listner.onError();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof GetConfiguredActivityListResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetConfiguredActivityListResponse");
                    ActivityAutoRecognitionViewModelWithOneK activityAutoRecognitionViewModelWithOneK = ActivityAutoRecognitionViewModelWithOneK.this;
                    List<ConfiguredActivities.ActivityInfo> activityInfoList = ((GetConfiguredActivityListResponse) responseData).getConfiguredActivities().getActivityInfoList();
                    Intrinsics.checkNotNullExpressionValue(activityInfoList, "data.configuredActivities.activityInfoList");
                    activityAutoRecognitionViewModelWithOneK.i = (ConfiguredActivities.ActivityInfo[]) activityInfoList.toArray(new ConfiguredActivities.ActivityInfo[0]);
                    listner.onResponse();
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else {
            listner.onError();
        }
    }

    @Nullable
    public final List<EntityPhysicalActivities> getDefaultActivities() {
        return this.k;
    }

    @NotNull
    public final AutoActivityDetectionData getDefaultAutoActivityDetectionData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AutoActivityDetectionData.Period(Integer.valueOf(m), Integer.valueOf(n)));
        arrayList.add(new AutoActivityDetectionData.Period(Integer.valueOf(q), Integer.valueOf(r)));
        Boolean bool = Boolean.TRUE;
        return new AutoActivityDetectionData(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, bool, bool, bool, bool, bool, bool, bool, 5, bool, Boolean.FALSE, arrayList);
    }

    @Nullable
    public final DialogListener getDialogListener() {
        return this.g;
    }

    @NotNull
    public final String getEMPTY_HYPHEN() {
        return this.f;
    }

    @NotNull
    public final String getEMPTY_STRING() {
        return this.e;
    }

    public final void getListOfActivities() {
        this.k = PhysicalActivityRepository.Companion.getInstance(this.d).getDefaultActivities();
        ArrayList<AutoRecognitonModelOneK> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        arrayList.addAll(h());
        List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(this.d).getAutoRecognitionList();
        if (autoRecognitionList == null || autoRecognitionList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(new AutoRecognitionData(arrayList.get(i).getActivityName(), arrayList.get(i).isSelected(), arrayList.get(i).isFromOneKActivity(), arrayList.get(i).getActivityCategoryId(), arrayList.get(i).getActivityId(), arrayList.get(i).getFwActivityId(), arrayList.get(i).getActivityCode(), arrayList.get(i).getByteOrderInFW()));
            }
            UserDataManager.getInstance(this.d).saveAutoRecognitionList(arrayList2);
        } else {
            List<AutoRecognitionData> autoRecognitionList2 = UserDataManager.getInstance(this.d).getAutoRecognitionList();
            int size2 = autoRecognitionList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String activityCode = autoRecognitionList2.get(i2).getActivityCode();
                if (!(activityCode == null || activityCode.length() == 0)) {
                    int size3 = arrayList.size();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= size3) {
                            break;
                        } else if (Intrinsics.areEqual(autoRecognitionList2.get(i2).getActivityCode(), arrayList.get(i3).getActivityCode())) {
                            arrayList.get(i3).setSelected(autoRecognitionList2.get(i2).isEnabled());
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            }
        }
        this.j.postValue(arrayList);
    }

    @NotNull
    public final MutableLiveData<ArrayList<AutoRecognitonModelOneK>> getListOfActivityLiveData() {
        return this.j;
    }

    @Nullable
    public final SettingsUpdateListener getSettingUpdateListener() {
        return this.h;
    }

    public final ArrayList<AutoRecognitonModelOneK> h() {
        ArrayList<AutoRecognitonModelOneK> arrayList = new ArrayList<>();
        for (AutoActivityDetectionServerInputData autoActivityDetectionServerInputData : this.l) {
            EntityPhysicalActivities physicalActivityN = PhysicalActivityRepository.Companion.getInstance(this.d).getPhysicalActivityN(autoActivityDetectionServerInputData.getActivityCode());
            if (physicalActivityN != null) {
                String titleInMetric = physicalActivityN.getTitleInMetric();
                Intrinsics.checkNotNull(titleInMetric);
                String activityCode = physicalActivityN.getActivityCode();
                Intrinsics.checkNotNull(activityCode);
                arrayList.add(new AutoRecognitonModelOneK(titleInMetric, false, !l(activityCode), physicalActivityN.getCategoryId(), physicalActivityN.getActivityId(), physicalActivityN.getFwActId(), physicalActivityN.getActivityCode(), autoActivityDetectionServerInputData.getByteOrderInFW(), physicalActivityN.getIconUrl()));
            }
        }
        return arrayList;
    }

    public final String i(int i) {
        String valueOf;
        String valueOf2;
        int i2 = i / 60;
        if (i2 < 10) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(i2);
            valueOf = sb.toString();
        } else {
            valueOf = String.valueOf(i2);
        }
        int i3 = i % 60;
        if (i3 < 10) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(i3);
            valueOf2 = sb2.toString();
        } else {
            valueOf2 = String.valueOf(i3);
        }
        return valueOf + ':' + valueOf2 + ":00";
    }

    public final boolean isSelectedActivityPresentInWatch(int i) {
        ConfiguredActivities.ActivityInfo[] activityInfoArr = this.i;
        if (activityInfoArr != null) {
            for (ConfiguredActivities.ActivityInfo activityInfo : activityInfoArr) {
                if (activityInfo.getActivityId() == i) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final String j(AutoActivityDetectionData autoActivityDetectionData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8 = this.e;
        StringBuilder sb = new StringBuilder();
        sb.append(str8);
        Boolean sundayEnabled = autoActivityDetectionData.getSundayEnabled();
        Intrinsics.checkNotNullExpressionValue(sundayEnabled, "autoActivityDetectionData.sundayEnabled");
        if (sundayEnabled.booleanValue()) {
            str = this.d.getResources().getString(R.string.S);
        } else {
            str = this.f;
        }
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        Boolean mondayEnabled = autoActivityDetectionData.getMondayEnabled();
        Intrinsics.checkNotNullExpressionValue(mondayEnabled, "autoActivityDetectionData.mondayEnabled");
        if (mondayEnabled.booleanValue()) {
            str2 = this.d.getResources().getString(R.string.M);
        } else {
            str2 = this.f;
        }
        sb3.append(str2);
        String sb4 = sb3.toString();
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb4);
        Boolean tuesdayEnabled = autoActivityDetectionData.getTuesdayEnabled();
        Intrinsics.checkNotNullExpressionValue(tuesdayEnabled, "autoActivityDetectionData.tuesdayEnabled");
        if (tuesdayEnabled.booleanValue()) {
            str3 = this.d.getResources().getString(R.string.T);
        } else {
            str3 = this.f;
        }
        sb5.append(str3);
        String sb6 = sb5.toString();
        StringBuilder sb7 = new StringBuilder();
        sb7.append(sb6);
        Boolean wednesdayEnabled = autoActivityDetectionData.getWednesdayEnabled();
        Intrinsics.checkNotNullExpressionValue(wednesdayEnabled, "autoActivityDetectionData.wednesdayEnabled");
        if (wednesdayEnabled.booleanValue()) {
            str4 = this.d.getResources().getString(R.string.W);
        } else {
            str4 = this.f;
        }
        sb7.append(str4);
        String sb8 = sb7.toString();
        StringBuilder sb9 = new StringBuilder();
        sb9.append(sb8);
        Boolean thursdayEnabled = autoActivityDetectionData.getThursdayEnabled();
        Intrinsics.checkNotNullExpressionValue(thursdayEnabled, "autoActivityDetectionData.thursdayEnabled");
        if (thursdayEnabled.booleanValue()) {
            str5 = this.d.getResources().getString(R.string.T);
        } else {
            str5 = this.f;
        }
        sb9.append(str5);
        String sb10 = sb9.toString();
        StringBuilder sb11 = new StringBuilder();
        sb11.append(sb10);
        Boolean fridayEnabled = autoActivityDetectionData.getFridayEnabled();
        Intrinsics.checkNotNullExpressionValue(fridayEnabled, "autoActivityDetectionData.fridayEnabled");
        if (fridayEnabled.booleanValue()) {
            str6 = this.d.getResources().getString(R.string.F);
        } else {
            str6 = this.f;
        }
        sb11.append(str6);
        String sb12 = sb11.toString();
        StringBuilder sb13 = new StringBuilder();
        sb13.append(sb12);
        Boolean saturdayEnabled = autoActivityDetectionData.getSaturdayEnabled();
        Intrinsics.checkNotNullExpressionValue(saturdayEnabled, "autoActivityDetectionData.saturdayEnabled");
        if (saturdayEnabled.booleanValue()) {
            str7 = this.d.getResources().getString(R.string.S);
        } else {
            str7 = this.f;
        }
        sb13.append(str7);
        return sb13.toString();
    }

    public final boolean k(byte[] bArr) {
        for (byte b : bArr) {
            if (b == 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean l(String str) {
        List<EntityPhysicalActivities> list = this.k;
        if (!(list == null || list.isEmpty())) {
            List<EntityPhysicalActivities> list2 = this.k;
            Intrinsics.checkNotNull(list2);
            for (EntityPhysicalActivities entityPhysicalActivities : list2) {
                if (Intrinsics.areEqual(entityPhysicalActivities.getActivityCode(), str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void m(AutoActivityDetectionData autoActivityDetectionData) {
        List<AutoRecognitionData> autoRecognitionList = UserDataManager.getInstance(this.d).getAutoRecognitionList();
        if (autoRecognitionList != null) {
            int size = autoRecognitionList.size();
            for (int i = 0; i < size; i++) {
                if (autoRecognitionList.get(i).getActivityCode() != null) {
                    String activityCode = autoRecognitionList.get(i).getActivityCode();
                    if (Intrinsics.areEqual(activityCode, "17-262")) {
                        autoRecognitionList.get(i).setEnabled(autoActivityDetectionData.getActivities()[1] == 1);
                    } else if (Intrinsics.areEqual(activityCode, "12-20")) {
                        autoRecognitionList.get(i).setEnabled(autoActivityDetectionData.getActivities()[0] == 1);
                    }
                }
            }
            UserDataManager.getInstance(this.d).saveAutoRecognitionList(autoRecognitionList);
        }
    }

    public final void saveSelectedActivitiesToPref(@NotNull ArrayList<AutoRecognitonModelOneK> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        Iterator<AutoRecognitonModelOneK> it = list.iterator();
        while (it.hasNext()) {
            AutoRecognitonModelOneK next = it.next();
            arrayList.add(new AutoRecognitionData(next.getActivityName(), next.isSelected(), next.isFromOneKActivity(), next.getActivityCategoryId(), next.getActivityId(), next.getFwActivityId(), next.getActivityCode(), next.getByteOrderInFW()));
        }
        UserDataManager.getInstance(this.d).saveAutoRecognitionList(arrayList);
    }

    public final void senDataToServer(@NotNull AutoActivityDetectionData autoActivityDetectionData) {
        AutoRecognitionData b;
        Intrinsics.checkNotNullParameter(autoActivityDetectionData, "autoActivityDetectionData");
        SaveAutoActivityRecognizeReq saveAutoActivityRecognizeReq = new SaveAutoActivityRecognizeReq();
        AutoRecognize autoRecognize = new AutoRecognize();
        byte[] activities = autoActivityDetectionData.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities, "autoActivityDetectionData.activities");
        boolean z = false;
        if (k(activities)) {
            ArrayList arrayList = new ArrayList();
            byte[] activities2 = autoActivityDetectionData.getActivities();
            Intrinsics.checkNotNullExpressionValue(activities2, "autoActivityDetectionData.activities");
            int length = activities2.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                if (activities2[i] == 1 && (b = b(i2)) != null) {
                    AutoRecognizeActivity autoRecognizeActivity = new AutoRecognizeActivity();
                    String activityCode = b.getActivityCode();
                    if (Intrinsics.areEqual(activityCode, "17-262")) {
                        autoRecognizeActivity.setType("WALK");
                    } else if (Intrinsics.areEqual(activityCode, "12-20")) {
                        autoRecognizeActivity.setType(CoveApiConstants.RUN);
                    } else {
                        autoRecognizeActivity.setType(b.getTypeName());
                    }
                    autoRecognizeActivity.setCode(b.getActivityCode());
                    arrayList.add(autoRecognizeActivity);
                }
                i++;
                i2 = i3;
            }
            if (!arrayList.isEmpty()) {
                autoRecognize.setActivities(arrayList);
            }
        }
        autoRecognize.setWeekDays(j(autoActivityDetectionData));
        AutoRecognizeSchedule autoRecognizeSchedule = new AutoRecognizeSchedule();
        Boolean smartModeEnabled = autoActivityDetectionData.getSmartModeEnabled();
        Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "autoActivityDetectionData.smartModeEnabled");
        autoRecognizeSchedule.setActive(smartModeEnabled.booleanValue());
        List<AutoActivityDetectionData.Period> periods = autoActivityDetectionData.getPeriods();
        if (!((periods == null || periods.isEmpty()) ? true : true)) {
            ArrayList arrayList2 = new ArrayList();
            for (AutoActivityDetectionData.Period period : autoActivityDetectionData.getPeriods()) {
                AutoRecognizeSlot autoRecognizeSlot = new AutoRecognizeSlot();
                Integer startTime = period.getStartTime();
                Intrinsics.checkNotNullExpressionValue(startTime, "p.startTime");
                autoRecognizeSlot.setStartTime(i(startTime.intValue()));
                Integer endTime = period.getEndTime();
                Intrinsics.checkNotNullExpressionValue(endTime, "p.endTime");
                autoRecognizeSlot.setEndTime(i(endTime.intValue()));
                arrayList2.add(autoRecognizeSlot);
            }
            autoRecognizeSchedule.setSlots(arrayList2);
        }
        autoRecognize.setSchedule(autoRecognizeSchedule);
        saveAutoActivityRecognizeReq.setAutoRecognize(autoRecognize);
        CoveUserDeviceSettings.saveAutoRecognizeSettings(saveAutoActivityRecognizeReq, new CoveApiListener<SaveAutoRecognizeSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK$senDataToServer$2
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                DialogListener dialogListener = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener != null) {
                    dialogListener.onDismiss();
                }
                DialogListener dialogListener2 = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener2 != null) {
                    dialogListener2.showErrorDialog();
                }
                ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener settingUpdateListener = ActivityAutoRecognitionViewModelWithOneK.this.getSettingUpdateListener();
                if (settingUpdateListener != null) {
                    settingUpdateListener.onAutoDetectionFailure("Save  auto recognize error");
                }
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveAutoRecognizeSettingsRes object) {
                Intrinsics.checkNotNullParameter(object, "object");
                DialogListener dialogListener = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener != null) {
                    dialogListener.onDismiss();
                }
                if (object.getCode() == 200) {
                    DialogListener dialogListener2 = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                    if (dialogListener2 != null) {
                        dialogListener2.showSuccessDialog();
                    }
                    ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener settingUpdateListener = ActivityAutoRecognitionViewModelWithOneK.this.getSettingUpdateListener();
                    if (settingUpdateListener != null) {
                        settingUpdateListener.onAutoDetectionSuccess();
                    }
                    LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, " auto recognize saved successfully");
                    return;
                }
                DialogListener dialogListener3 = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener3 != null) {
                    dialogListener3.showErrorDialog();
                }
                ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener settingUpdateListener2 = ActivityAutoRecognitionViewModelWithOneK.this.getSettingUpdateListener();
                if (settingUpdateListener2 != null) {
                    settingUpdateListener2.onAutoDetectionFailure("Save  auto recognize error");
                }
                LogHelper.d(HeaderParameterNames.AUTHENTICATION_TAG, "Save  auto recognize error");
            }
        });
    }

    public final void setAutoRecognitionToWatch(@NotNull final AutoActivityDetectionData activityDetectionData) {
        Intrinsics.checkNotNullParameter(activityDetectionData, "activityDetectionData");
        SetAutoActivityDetectionSettingsRequest setAutoActivityDetectionSettingsRequest = new SetAutoActivityDetectionSettingsRequest();
        ArrayList arrayList = new ArrayList();
        Boolean smartModeEnabled = activityDetectionData.getSmartModeEnabled();
        Intrinsics.checkNotNullExpressionValue(smartModeEnabled, "activityDetectionData.smartModeEnabled");
        if (smartModeEnabled.booleanValue()) {
            List<AutoActivityDetectionData.Period> periods = activityDetectionData.getPeriods();
            if (!(periods == null || periods.isEmpty())) {
                for (AutoActivityDetectionData.Period period : activityDetectionData.getPeriods()) {
                    Integer startTime = period.getStartTime();
                    Intrinsics.checkNotNullExpressionValue(startTime, "p.startTime");
                    int intValue = startTime.intValue();
                    Integer endTime = period.getEndTime();
                    Intrinsics.checkNotNullExpressionValue(endTime, "p.endTime");
                    arrayList.add(new AutoActivityDetectionModel.Period(intValue, endTime.intValue()));
                }
            }
        }
        byte[] activities = activityDetectionData.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities, "activityDetectionData.activities");
        Boolean sundayEnabled = activityDetectionData.getSundayEnabled();
        Intrinsics.checkNotNullExpressionValue(sundayEnabled, "activityDetectionData.sundayEnabled");
        boolean booleanValue = sundayEnabled.booleanValue();
        Boolean mondayEnabled = activityDetectionData.getMondayEnabled();
        Intrinsics.checkNotNullExpressionValue(mondayEnabled, "activityDetectionData.mondayEnabled");
        boolean booleanValue2 = mondayEnabled.booleanValue();
        Boolean tuesdayEnabled = activityDetectionData.getTuesdayEnabled();
        Intrinsics.checkNotNullExpressionValue(tuesdayEnabled, "activityDetectionData.tuesdayEnabled");
        boolean booleanValue3 = tuesdayEnabled.booleanValue();
        Boolean wednesdayEnabled = activityDetectionData.getWednesdayEnabled();
        Intrinsics.checkNotNullExpressionValue(wednesdayEnabled, "activityDetectionData.wednesdayEnabled");
        boolean booleanValue4 = wednesdayEnabled.booleanValue();
        Boolean thursdayEnabled = activityDetectionData.getThursdayEnabled();
        Intrinsics.checkNotNullExpressionValue(thursdayEnabled, "activityDetectionData.thursdayEnabled");
        boolean booleanValue5 = thursdayEnabled.booleanValue();
        Boolean fridayEnabled = activityDetectionData.getFridayEnabled();
        Intrinsics.checkNotNullExpressionValue(fridayEnabled, "activityDetectionData.fridayEnabled");
        boolean booleanValue6 = fridayEnabled.booleanValue();
        Boolean saturdayEnabled = activityDetectionData.getSaturdayEnabled();
        Intrinsics.checkNotNullExpressionValue(saturdayEnabled, "activityDetectionData.saturdayEnabled");
        boolean booleanValue7 = saturdayEnabled.booleanValue();
        Integer countDownValue = activityDetectionData.getCountDownValue();
        Intrinsics.checkNotNullExpressionValue(countDownValue, "activityDetectionData.countDownValue");
        int intValue2 = countDownValue.intValue();
        Boolean vibrationEnabled = activityDetectionData.getVibrationEnabled();
        Intrinsics.checkNotNullExpressionValue(vibrationEnabled, "activityDetectionData.vibrationEnabled");
        setAutoActivityDetectionSettingsRequest.setAutoActivityDetectionModel(new AutoActivityDetectionModel(activities, booleanValue, booleanValue2, booleanValue3, booleanValue4, booleanValue5, booleanValue6, booleanValue7, intValue2, vibrationEnabled.booleanValue(), arrayList));
        BleApiManager.getInstance(this.d).getBleApi().setUserSettings(setAutoActivityDetectionSettingsRequest, new SettingsResultListener() { // from class: com.coveiot.android.activitymodes.autodetection.viewmodel.ActivityAutoRecognitionViewModelWithOneK$setAutoRecognitionToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                DialogListener dialogListener = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener != null) {
                    dialogListener.onDismiss();
                }
                DialogListener dialogListener2 = ActivityAutoRecognitionViewModelWithOneK.this.getDialogListener();
                if (dialogListener2 != null) {
                    dialogListener2.showErrorDialog();
                }
                ActivityAutoRecognitionViewModelWithOneK.SettingsUpdateListener settingUpdateListener = ActivityAutoRecognitionViewModelWithOneK.this.getSettingUpdateListener();
                if (settingUpdateListener != null) {
                    settingUpdateListener.onAutoDetectionFailure(error.getErrorMsg());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Context context;
                Intrinsics.checkNotNullParameter(response, "response");
                context = ActivityAutoRecognitionViewModelWithOneK.this.d;
                UserDataManager.getInstance(context).saveAutoActivityDetectionData(activityDetectionData);
                ActivityAutoRecognitionViewModelWithOneK.this.m(activityDetectionData);
                ActivityAutoRecognitionViewModelWithOneK.this.senDataToServer(activityDetectionData);
            }
        });
    }

    public final void setDefaultActivities(@Nullable List<EntityPhysicalActivities> list) {
        this.k = list;
    }

    public final void setDialogListener(@Nullable DialogListener dialogListener) {
        this.g = dialogListener;
    }

    public final void setListOfActivityLiveData(@NotNull MutableLiveData<ArrayList<AutoRecognitonModelOneK>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.j = mutableLiveData;
    }

    public final void setSettingUpdateListener(@Nullable SettingsUpdateListener settingsUpdateListener) {
        this.h = settingsUpdateListener;
    }
}
