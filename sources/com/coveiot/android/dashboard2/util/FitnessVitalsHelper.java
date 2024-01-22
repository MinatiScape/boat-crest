package com.coveiot.android.dashboard2.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.R;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.idoSdk.IDOConstants;
import com.coveiot.android.respiratoryrate.database.RespiratoryRateRepository;
import com.coveiot.android.sleepenergyscore.energymeter.database.EnergyScoreRepository;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.repository.ambientsound.AmbientSoundRepository;
import com.coveiot.repository.bp.BPRepository;
import com.coveiot.repository.heartrate.HeartRateRepository;
import com.coveiot.repository.hrv.datasource.HRVRepository;
import com.coveiot.repository.manualdata.ManualDataRepository;
import com.coveiot.repository.periodicspo2.PeriodicSpo2Repository;
import com.coveiot.repository.stress.StressRepository;
import com.coveiot.repository.temperature.TemperatureRepository;
import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessVitalsHelper {
    @NotNull
    public static final FitnessVitalsHelper INSTANCE = new FitnessVitalsHelper();

    @NotNull
    public final SelectedFitnessVitalsDataModel getDefaultSelectedFitnessVitals(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = 1;
        SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel = new SelectedFitnessVitalsDataModel(null, 1, null);
        if (isHeartRateSupported(context)) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel(IDOConstants.DATA_TYPE_HEART_RATE, false, false, null, context.getString(R.string.heart_rate), 0, 0, getFitnessVitalInfoText(context, IDOConstants.DATA_TYPE_HEART_RATE), 110, null));
            i = 2;
        }
        if (isSPO2Supported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("SPO2", false, false, null, context.getString(R.string.spo2), 0, 0, getFitnessVitalInfoText(context, "SPO2"), 110, null));
            i++;
        }
        if (isStressSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("STRESS", false, false, null, context.getString(R.string.stress), 0, 0, getFitnessVitalInfoText(context, "STRESS"), 110, null));
            i++;
        }
        if (isHRVSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("HRV", false, false, null, context.getString(R.string.hrv), 0, 0, getFitnessVitalInfoText(context, "HRV"), 110, null));
            i++;
        }
        if (isRespiratoryRateSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("NBR", false, false, null, context.getString(R.string.nbr), 0, 0, getFitnessVitalInfoText(context, "NBR"), 110, null));
            i++;
        }
        if (isEnergyMeterSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("ENERGY_METER", false, false, null, context.getString(R.string.energy_meter), 0, 0, getFitnessVitalInfoText(context, "ENERGY_METER"), 110, null));
            i++;
        }
        if (isTemperatureSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel("TEMPERATURE", false, false, null, context.getString(R.string.temperature), 0, 0, getFitnessVitalInfoText(context, "TEMPERATURE"), 110, null));
            i++;
        }
        if (isAmbientSoundSupported(context) && i <= 3) {
            List<FitnessVitalsDataModel> fitnessDataModels = selectedFitnessVitalsDataModel.getFitnessDataModels();
            int i2 = R.string.ambient_sound;
            fitnessDataModels.add(new FitnessVitalsDataModel("AMBIENT_SOUND", false, false, null, context.getString(i2), 0, 0, context.getString(i2), 110, null));
            i++;
        }
        if (isBpSupported(context) && i <= 3) {
            selectedFitnessVitalsDataModel.getFitnessDataModels().add(new FitnessVitalsDataModel(EcgStyleReportUtil.UserInfoKey.BP, false, false, null, context.getString(R.string.bp), 0, 0, getFitnessVitalInfoText(context, EcgStyleReportUtil.UserInfoKey.BP), 110, null));
        }
        return selectedFitnessVitalsDataModel;
    }

    @Nullable
    public final Drawable getFitnessVitalBigDrawable(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getDrawable(R.drawable.ic_ambient_sound_level_big);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getDrawable(R.drawable.ic_heart_rate_big);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getDrawable(R.drawable.ic_stress_big);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getDrawable(R.drawable.ic_temperature_big);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getDrawable(R.drawable.ic_energy_big);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getDrawable(R.drawable.ic_blood_pressure_big);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getDrawable(R.drawable.ic_hrv_big);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getDrawable(R.drawable.ic_nightly_breathing_rate_big);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getDrawable(R.drawable.ic_spo2_big);
                }
                return null;
            case 78984887:
                if (fitnessVitalsType.equals("SLEEP")) {
                    return context.getDrawable(R.drawable.ic_sleep_big);
                }
                return null;
            case 79223559:
                if (fitnessVitalsType.equals("STEPS")) {
                    return context.getDrawable(R.drawable.ic_step_big);
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    public final Drawable getFitnessVitalDrawable(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getDrawable(R.drawable.ic_ambient_sound_dashboard);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getDrawable(R.drawable.ic_heart_rate);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getDrawable(R.drawable.ic_stress);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getDrawable(R.drawable.ic_temperature);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getDrawable(R.drawable.ic_energy_level_dashboard);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getDrawable(R.drawable.ic_bp);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getDrawable(R.drawable.ic_hrv_dashboard);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getDrawable(R.drawable.ic_nightly_breathing_rate);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getDrawable(R.drawable.ic_spo2_dashboard);
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    public final String getFitnessVitalInfoText(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getString(R.string.ambient_sound);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getString(R.string.monitor_heart_rate_start);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getString(R.string.keep_cool_monitor_your_stress);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getString(R.string.cool_down_measure_your_body_temp);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getString(R.string.boost_up_check_your_current_energy);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getString(R.string.relax_yourself_monitor_your_blood_pressure);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getString(R.string.train_smarter_track_your_hrv);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getString(R.string.monitor_your_breathing_rate_during_sleep);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getString(R.string.breath_easy_track_your_spo2);
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    public final String getFitnessVitalInfoTextForEditScreen(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getString(R.string.ambient_sound);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getString(R.string.edit_heart_rate_dashboard);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getString(R.string.edit_stress_level_dashboard);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getString(R.string.edit_temperature_dashboard);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getString(R.string.edit_energy_meter_dashboard);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getString(R.string.bp);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getString(R.string.edit_hrv_dashboard);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getString(R.string.edit_nbr_dashboard);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getString(R.string.edit_spo2_dashboard);
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    public final String getFitnessVitalTitle(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getString(R.string.ambient_sound);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getString(R.string.heart_rate);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getString(R.string.stress);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getString(R.string.temperature);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getString(R.string.energy_meter);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getString(R.string.bp);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getString(R.string.hrv);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getString(R.string.nbr);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getString(R.string.spo2);
                }
                return null;
            default:
                return null;
        }
    }

    @Nullable
    public final String getFitnessVitalTitleForEditScreen(@NotNull Context context, @NotNull String fitnessVitalsType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fitnessVitalsType, "fitnessVitalsType");
        switch (fitnessVitalsType.hashCode()) {
            case -1962477464:
                if (fitnessVitalsType.equals("AMBIENT_SOUND")) {
                    return context.getString(R.string.ambient_sound);
                }
                return null;
            case -1849873063:
                if (fitnessVitalsType.equals(IDOConstants.DATA_TYPE_HEART_RATE)) {
                    return context.getString(R.string.heart_rate);
                }
                return null;
            case -1838660172:
                if (fitnessVitalsType.equals("STRESS")) {
                    return context.getString(R.string.stress);
                }
                return null;
            case -1820305068:
                if (fitnessVitalsType.equals("TEMPERATURE")) {
                    return context.getString(R.string.temperature);
                }
                return null;
            case -1606469902:
                if (fitnessVitalsType.equals("ENERGY_METER")) {
                    return context.getString(R.string.energy_meter);
                }
                return null;
            case 2126:
                if (fitnessVitalsType.equals(EcgStyleReportUtil.UserInfoKey.BP)) {
                    return context.getString(R.string.bp);
                }
                return null;
            case 71820:
                if (fitnessVitalsType.equals("HRV")) {
                    return context.getString(R.string.hrv);
                }
                return null;
            case 77086:
                if (fitnessVitalsType.equals("NBR")) {
                    return context.getString(R.string.nightly_breathing_rate_nbr);
                }
                return null;
            case 2552032:
                if (fitnessVitalsType.equals("SPO2")) {
                    return context.getString(R.string.spo2);
                }
                return null;
            default:
                return null;
        }
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getMoreFitnessVitals(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList arrayList = new ArrayList();
        ArrayList<FitnessVitalsDataModel> arrayList2 = new ArrayList();
        if (isHeartRateSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel(IDOConstants.DATA_TYPE_HEART_RATE, false, false, null, context.getString(R.string.heart_rate), 0, 0, context.getString(R.string.edit_heart_rate_dashboard), 110, null));
        }
        if (isSPO2Supported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("SPO2", false, false, null, context.getString(R.string.spo2), 0, 0, context.getString(R.string.edit_spo2_dashboard), 110, null));
        }
        if (isStressSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("STRESS", false, false, null, context.getString(R.string.stress), 0, 0, context.getString(R.string.edit_stress_level_dashboard), 110, null));
        }
        if (isHRVSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("HRV", false, false, null, context.getString(R.string.hrv), 0, 0, context.getString(R.string.edit_hrv_dashboard), 110, null));
        }
        if (isRespiratoryRateSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("NBR", false, false, null, context.getString(R.string.nbr), 0, 0, context.getString(R.string.edit_nbr_dashboard), 110, null));
        }
        if (isEnergyMeterSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("ENERGY_METER", false, false, null, context.getString(R.string.energy_meter), 0, 0, context.getString(R.string.edit_energy_meter_dashboard), 110, null));
        }
        if (isTemperatureSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("TEMPERATURE", false, false, null, context.getString(R.string.temperature), 0, 0, context.getString(R.string.edit_temperature_dashboard), 110, null));
        }
        if (isAmbientSoundSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel("AMBIENT_SOUND", false, false, null, context.getString(R.string.ambient_sound), 0, 0, context.getString(R.string.train_smarter_track_your_hrv), 110, null));
        }
        if (isBpSupported(context)) {
            arrayList2.add(new FitnessVitalsDataModel(EcgStyleReportUtil.UserInfoKey.BP, false, false, null, context.getString(R.string.bp), 0, 0, context.getString(R.string.train_smarter_track_your_hrv), 110, null));
        }
        if (!arrayList2.isEmpty()) {
            SelectedFitnessVitalsDataModel selectedFitnessVitals = Dashboard2PreferenceManager.Companion.getInstance(context).getSelectedFitnessVitals();
            List<FitnessVitalsDataModel> fitnessDataModels = selectedFitnessVitals != null ? selectedFitnessVitals.getFitnessDataModels() : null;
            for (FitnessVitalsDataModel fitnessVitalsDataModel : arrayList2) {
                String fitnessVitalType = fitnessVitalsDataModel.getFitnessVitalType();
                boolean z = false;
                if (!(fitnessVitalType == null || fitnessVitalType.length() == 0)) {
                    if (!(fitnessDataModels == null || fitnessDataModels.isEmpty())) {
                        for (FitnessVitalsDataModel fitnessVitalsDataModel2 : fitnessDataModels) {
                            String fitnessVitalType2 = fitnessVitalsDataModel2.getFitnessVitalType();
                            if (!(fitnessVitalType2 == null || fitnessVitalType2.length() == 0) && Intrinsics.areEqual(fitnessVitalsDataModel.getFitnessVitalType(), fitnessVitalsDataModel2.getFitnessVitalType())) {
                                break;
                            }
                        }
                    }
                    z = true;
                    if (z) {
                        arrayList.add(fitnessVitalsDataModel);
                    }
                }
            }
        }
        return arrayList;
    }

    public final boolean isAmbientSoundDataAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isAmbientSoundSupported(context) && AmbientSoundRepository.Companion.getInstance(context).getLastReadAmbientSound(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
    }

    public final boolean isAmbientSoundSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isAmbientSoundLevelSupported();
    }

    public final boolean isBpDataAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isBpSupported(context) && BPRepository.Companion.getInstance(context).getLatestRecordHourly(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
    }

    public final boolean isBpSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isBpSupported() || BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isManualBpSupported();
    }

    public final boolean isEnergyMeterDataAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isEnergyMeterSupported(context) && EnergyScoreRepository.Companion.getInstance(context).getLastSyncedDate(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
    }

    public final boolean isEnergyMeterSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return UserDataManager.getInstance(context).isEnableSleepEnergyScoreFeature(context);
    }

    public final boolean isHRVDataAvailable(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        boolean z = false;
        boolean z2 = (bleApi != null && (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures2.isHRVHistorySupported()) && HRVRepository.Companion.getInstance(context).getLatestRecordHourly(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
        if (!z2) {
            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualHRVMeasurementSupported()) {
                z = true;
            }
            if (z && ManualDataRepository.Companion.getInstance(context).getLastMeasuredHRV(BleApiManager.getInstance(context).getBleApi().getMacAddress(), null) != null) {
                return true;
            }
        }
        return z2;
    }

    public final boolean isHRVSupported(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isHRVHistorySupported()) ? false : true) {
            return true;
        }
        BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
        return bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualHRVMeasurementSupported();
    }

    public final boolean isHeartRateDataAvailable(@NotNull Context context) {
        String macAddress;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        return ((bleApi == null || (macAddress = bleApi.getMacAddress()) == null) ? null : HeartRateRepository.Companion.getInstance(context).getLatestRecordHourly(macAddress)) != null;
    }

    public final boolean isHeartRateSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isHeartRateSupported();
    }

    public final boolean isRespiratoryRateDataAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isRespiratoryRateSupported(context) && RespiratoryRateRepository.Companion.getInstance(context).getLastSyncedDate(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
    }

    public final boolean isRespiratoryRateSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isRespiratoryRateByPPGSupported();
    }

    public final boolean isSPO2DataAvailable(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        BleApi bleApi;
        String macAddress;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
        if (((bleApi2 == null || (deviceSupportedFeatures2 = bleApi2.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isPeriodicSpO2Supported()) ? false : true) && (bleApi = BleApiManager.getInstance(context).getBleApi()) != null && (macAddress = bleApi.getMacAddress()) != null) {
            PeriodicSpo2Repository.Companion.getInstance(context).getLatestRecordHourly(macAddress);
        }
        BleApi bleApi3 = BleApiManager.getInstance(context).getBleApi();
        return (bleApi3 != null && (deviceSupportedFeatures = bleApi3.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualSpo2SupportedOnBand()) && ManualDataRepository.Companion.getInstance(context).getLastMeasuredSpo2(BleApiManager.getInstance(context).getBleApi().getMacAddress()).getValue() != null;
    }

    public final boolean isSPO2Supported(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isPeriodicSpO2Supported()) ? false : true) {
            return true;
        }
        BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
        return bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualSpo2SupportedOnBand();
    }

    public final boolean isStressDataAvailable(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        boolean z = false;
        boolean z2 = (bleApi != null && (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures2.isStressHistorySupported()) && StressRepository.Companion.getInstance(context).getLatestRecordHourly(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
        if (!z2) {
            BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
            if (bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualStressMeasurementSupported()) {
                z = true;
            }
            if (z && ManualDataRepository.Companion.getInstance(context).getLastMeasuredStress(BleApiManager.getInstance(context).getBleApi().getMacAddress(), null) != null) {
                return true;
            }
        }
        return z2;
    }

    public final boolean isStressSupported(@NotNull Context context) {
        DeviceSupportedFeatures deviceSupportedFeatures;
        DeviceSupportedFeatures deviceSupportedFeatures2;
        Intrinsics.checkNotNullParameter(context, "context");
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        if ((bleApi == null || (deviceSupportedFeatures2 = bleApi.getDeviceSupportedFeatures()) == null || !deviceSupportedFeatures2.isStressHistorySupported()) ? false : true) {
            return true;
        }
        BleApi bleApi2 = BleApiManager.getInstance(context).getBleApi();
        return bleApi2 != null && (deviceSupportedFeatures = bleApi2.getDeviceSupportedFeatures()) != null && deviceSupportedFeatures.isManualStressMeasurementSupported();
    }

    public final boolean isTemperatureDataAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return TemperatureRepository.Companion.getInstance(context).getLatestRecordHourly(BleApiManager.getInstance(context).getBleApi().getMacAddress()) != null;
    }

    public final boolean isTemperatureSupported(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isTemparatureHistorySupported();
    }
}
