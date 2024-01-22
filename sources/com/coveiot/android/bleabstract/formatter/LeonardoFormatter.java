package com.coveiot.android.bleabstract.formatter;

import com.coveiot.android.bleabstract.models.AccelerometerData;
import com.coveiot.android.bleabstract.models.ActivityConfigMetaData;
import com.coveiot.android.bleabstract.models.ActivityDataType;
import com.coveiot.android.bleabstract.models.ActivityHeartRateSample;
import com.coveiot.android.bleabstract.models.ActivityStepsSample;
import com.coveiot.android.bleabstract.models.ConfiguredActivities;
import com.coveiot.android.bleabstract.models.CustomReminderAbstract;
import com.coveiot.android.bleabstract.models.DailyCalorieData;
import com.coveiot.android.bleabstract.models.DailyDistanceData;
import com.coveiot.android.bleabstract.models.DrinkingReminderAbstract;
import com.coveiot.android.bleabstract.models.DynamicSportFieldAnimation;
import com.coveiot.android.bleabstract.models.DynamicSportFieldBBText;
import com.coveiot.android.bleabstract.models.DynamicSportFieldButton;
import com.coveiot.android.bleabstract.models.DynamicSportFieldButtonImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldCircular;
import com.coveiot.android.bleabstract.models.DynamicSportFieldImage;
import com.coveiot.android.bleabstract.models.DynamicSportFieldNumber;
import com.coveiot.android.bleabstract.models.DynamicSportFieldProgressBar;
import com.coveiot.android.bleabstract.models.DynamicSportFieldRectangle;
import com.coveiot.android.bleabstract.models.DynamicSportFieldSquare;
import com.coveiot.android.bleabstract.models.DynamicSportFieldText;
import com.coveiot.android.bleabstract.models.DynamicSportFieldTextV2;
import com.coveiot.android.bleabstract.models.DynamicSportsField;
import com.coveiot.android.bleabstract.models.HandWashReminderAbstract;
import com.coveiot.android.bleabstract.models.METData;
import com.coveiot.android.bleabstract.models.ManualBpReading;
import com.coveiot.android.bleabstract.models.ManualSpo2Reading;
import com.coveiot.android.bleabstract.models.MedicineReminderAbstract;
import com.coveiot.android.bleabstract.models.MeetingReminderAbstract;
import com.coveiot.android.bleabstract.models.NavigationItem;
import com.coveiot.android.bleabstract.models.OtherReminderAbstract;
import com.coveiot.android.bleabstract.models.RawAccelerometerHistoryData;
import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import com.coveiot.android.bleabstract.models.SedentaryData;
import com.coveiot.android.bleabstract.models.SensAISummaryData;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeature;
import com.coveiot.android.bleabstract.models.WatchDiagnosticFeatureType;
import com.coveiot.android.bleabstract.request.SetCustomRemindersRequest;
import com.coveiot.android.bleabstract.response.ActivityModeSummaryResponse;
import com.coveiot.android.bleabstract.response.BpDayData;
import com.coveiot.android.bleabstract.response.BpHourData;
import com.coveiot.android.bleabstract.response.BpIntervalData;
import com.coveiot.android.bleabstract.response.BpResponse;
import com.coveiot.android.bleabstract.response.BpTimeLogBeanData;
import com.coveiot.android.bleabstract.response.GetSOSRecordsItem;
import com.coveiot.android.bleabstract.response.HRVDayData;
import com.coveiot.android.bleabstract.response.HRVHourData;
import com.coveiot.android.bleabstract.response.HRVTimeLogBeanData;
import com.coveiot.android.bleabstract.response.HeartRateDayData;
import com.coveiot.android.bleabstract.response.HeartRateHourData;
import com.coveiot.android.bleabstract.response.HeartRateResponse;
import com.coveiot.android.bleabstract.response.HeartRateTimeLogBeanData;
import com.coveiot.android.bleabstract.response.PeriodicSpo2Response;
import com.coveiot.android.bleabstract.response.RrDayData;
import com.coveiot.android.bleabstract.response.RrHourData;
import com.coveiot.android.bleabstract.response.RrResponse;
import com.coveiot.android.bleabstract.response.RrTimeLogBeanData;
import com.coveiot.android.bleabstract.response.SleepDayData;
import com.coveiot.android.bleabstract.response.SleepHourData;
import com.coveiot.android.bleabstract.response.SleepResponse;
import com.coveiot.android.bleabstract.response.SleepTimeLogBeanData;
import com.coveiot.android.bleabstract.response.Spo2DayData;
import com.coveiot.android.bleabstract.response.Spo2HourData;
import com.coveiot.android.bleabstract.response.Spo2TimeLogBeanData;
import com.coveiot.android.bleabstract.response.StepsDayData;
import com.coveiot.android.bleabstract.response.StepsHourData;
import com.coveiot.android.bleabstract.response.StepsResponse;
import com.coveiot.android.bleabstract.response.StepsTimeLogBeanData;
import com.coveiot.android.bleabstract.response.StressDayData;
import com.coveiot.android.bleabstract.response.StressHourData;
import com.coveiot.android.bleabstract.response.StressResponse;
import com.coveiot.android.bleabstract.response.StressTimeLogBeanData;
import com.coveiot.android.bleabstract.response.TemperatureDayData;
import com.coveiot.android.bleabstract.response.TemperatureHourData;
import com.coveiot.android.bleabstract.response.TemperatureResponse;
import com.coveiot.android.bleabstract.response.TemperatureTimeLogBeanData;
import com.coveiot.sdk.ble.api.model.BleAccelerometerData;
import com.coveiot.sdk.ble.api.model.BleActivityConfigMetaData;
import com.coveiot.sdk.ble.api.model.BleActivityDataHolder;
import com.coveiot.sdk.ble.api.model.BleActivityDetailData;
import com.coveiot.sdk.ble.api.model.BleActivitySummaryData;
import com.coveiot.sdk.ble.api.model.BleConfiguredActivities;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldAnimation;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldBBText;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldButton;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldButtonImage;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldCircular;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldImage;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldNumber;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldProgressBar;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldRectangle;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldSquare;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldText;
import com.coveiot.sdk.ble.api.model.BleDynamicSportFieldTextV2;
import com.coveiot.sdk.ble.api.model.BleDynamicSportsField;
import com.coveiot.sdk.ble.api.model.BleNavigationItem;
import com.coveiot.sdk.ble.api.model.BleSedentaryData;
import com.coveiot.sdk.ble.api.model.BleSensAISummaryData;
import com.coveiot.sdk.ble.api.model.BleWatchDiagnosticFeature;
import com.coveiot.sdk.ble.api.model.BleWatchDiagnosticFeatureType;
import com.coveiot.sdk.ble.api.model.BpHrMinData;
import com.coveiot.sdk.ble.api.model.CalorieHourlyData;
import com.coveiot.sdk.ble.api.model.CalorieSample;
import com.coveiot.sdk.ble.api.model.DistanceHourlyData;
import com.coveiot.sdk.ble.api.model.DrinkingReminder;
import com.coveiot.sdk.ble.api.model.HandWashReminder;
import com.coveiot.sdk.ble.api.model.HeartSample;
import com.coveiot.sdk.ble.api.model.HrBpData;
import com.coveiot.sdk.ble.api.model.ManualBpData;
import com.coveiot.sdk.ble.api.model.ManualSpo2Data;
import com.coveiot.sdk.ble.api.model.MedicineReminder;
import com.coveiot.sdk.ble.api.model.MeetingReminder;
import com.coveiot.sdk.ble.api.model.OtherReminder;
import com.coveiot.sdk.ble.api.model.PaceSample;
import com.coveiot.sdk.ble.api.model.RrData;
import com.coveiot.sdk.ble.api.model.SOSRecordItem;
import com.coveiot.sdk.ble.api.model.SleepData;
import com.coveiot.sdk.ble.api.model.SpeedSample;
import com.coveiot.sdk.ble.api.model.Spo2Data;
import com.coveiot.sdk.ble.api.model.StepSample;
import com.coveiot.sdk.ble.api.model.StepsData;
import com.coveiot.sdk.ble.api.model.StressData;
import com.coveiot.sdk.ble.api.model.TemperatureData;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.sdk.ble.api.request.SetCustomReminderReq;
import com.coveiot.sdk.ble.model.ActivityType;
import com.coveiot.sdk.ble.model.BleActivityDataType;
import com.coveiot.sdk.ble.model.BleMETData;
import com.coveiot.sdk.ble.model.HrBpHourlyData;
import com.coveiot.sdk.ble.model.RrHourlyData;
import com.coveiot.sdk.ble.model.SleepHourlyData;
import com.coveiot.sdk.ble.model.Spo2HourlyData;
import com.coveiot.sdk.ble.model.StepsHourlyData;
import com.coveiot.sdk.ble.model.StressHourlyData;
import com.coveiot.sdk.ble.model.TemperatureHourlyData;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public class LeonardoFormatter {
    public static List<BleActivityConfigMetaData> convertActivityConfigMetaDataToBleActivityConfigMetaData(List<ActivityConfigMetaData> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ActivityConfigMetaData> it = list.iterator();
        while (it.hasNext()) {
            ActivityConfigMetaData next = it.next();
            int activityNum = next.getActivityNum();
            int categoryId = next.getCategoryId();
            int categoryImageId = next.getCategoryImageId();
            String categoryUnicode = next.getCategoryUnicode();
            int activityId = next.getActivityId();
            int activityImageId = next.getActivityImageId();
            String activityUniCode = next.getActivityUniCode();
            int orderId = next.getOrderId();
            ActivityDataType dataType = next.getDataType();
            BleActivityDataType bleActivityDataType = new BleActivityDataType(dataType.isHRS(), dataType.isSTEP(), dataType.isSTEP_STRIDE(), dataType.isSTEP_FREQ(), dataType.isSPEED(), dataType.isPACE(), dataType.isALTITUDE(), dataType.isGPS(), dataType.isCALORIE(), dataType.isDISTANCE());
            int met_type = next.getMET_TYPE();
            int met_num = next.getMET_NUM();
            List<METData> bleMetDataList = next.getBleMetDataList();
            ArrayList arrayList2 = new ArrayList();
            if (!AppUtils.isEmpty(bleMetDataList)) {
                Iterator<METData> it2 = bleMetDataList.iterator();
                while (it2.hasNext()) {
                    METData next2 = it2.next();
                    arrayList2.add(new BleMETData(next2.getPace_percentage(), next2.getMET_DATA()));
                    it = it;
                    it2 = it2;
                    arrayList = arrayList;
                }
            }
            arrayList = arrayList;
            arrayList.add(new BleActivityConfigMetaData(activityNum, categoryId, categoryImageId, categoryUnicode, activityId, activityImageId, activityUniCode, orderId, bleActivityDataType, met_type, met_num, arrayList2));
            it = it;
        }
        return arrayList;
    }

    public static List<ActivityModeSummaryResponse> getActivitySummaryResponse(String str, HashMap<Long, BleActivityDataHolder> hashMap) {
        int i;
        int i2;
        long j;
        long j2;
        float f;
        if (hashMap.keySet().size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (Long l : hashMap.keySet()) {
                BleActivityDataHolder bleActivityDataHolder = hashMap.get(l);
                ActivityModeSummaryResponse activityModeSummaryResponse = new ActivityModeSummaryResponse();
                BleActivitySummaryData bleActivitySummaryData = bleActivityDataHolder.getBleActivitySummaryData();
                activityModeSummaryResponse.setActivityDuration(bleActivitySummaryData.getDuration());
                activityModeSummaryResponse.setActivityMode("PHYSICAL_ACTIVITY");
                activityModeSummaryResponse.setDate(BleUtils.getDate(bleActivitySummaryData.getTimeStamp(), "yyyy-MM-dd"));
                activityModeSummaryResponse.setStartDateTime(Long.valueOf(bleActivitySummaryData.getTimeStamp()));
                activityModeSummaryResponse.setEndDateTime(Long.valueOf(bleActivitySummaryData.getTimeStamp() + (bleActivitySummaryData.getDuration() * 1000)));
                activityModeSummaryResponse.setSessionID(UUID.randomUUID().toString());
                activityModeSummaryResponse.setActivityId(Integer.valueOf(bleActivitySummaryData.getActivityId()));
                activityModeSummaryResponse.setCategoryId(Integer.valueOf(bleActivitySummaryData.getCategoryId()));
                activityModeSummaryResponse.setMacAddress(str);
                activityModeSummaryResponse.setTotalSteps(bleActivitySummaryData.getTotalSteps());
                activityModeSummaryResponse.setTotalCalories(bleActivitySummaryData.getTotalCalories() / 1000.0d);
                activityModeSummaryResponse.setTotalDistance(bleActivitySummaryData.getTotalDistance() / 1000.0d);
                activityModeSummaryResponse.setMaxHeartRate(bleActivitySummaryData.getMaxHr());
                activityModeSummaryResponse.setMinHeartRate(bleActivitySummaryData.getMinHr());
                activityModeSummaryResponse.setPaceInSeconds(bleActivitySummaryData.getAveragePace());
                BleActivityDetailData bleActivityDetailData = bleActivityDataHolder.getBleActivityDetailData();
                if (bleActivityDetailData != null) {
                    activityModeSummaryResponse.setLowSamplingRate(bleActivityDetailData.getLeastSampleFrequency());
                    List<HeartSample> heartSampleList = bleActivityDetailData.getHeartSampleList();
                    if (AppUtils.isEmpty(heartSampleList)) {
                        i = 0;
                    } else {
                        int i3 = 0;
                        i = 0;
                        for (HeartSample heartSample : heartSampleList) {
                            if (heartSample.getValue() > 0) {
                                i3++;
                            }
                            i += heartSample.getValue();
                        }
                        if (i3 > 0) {
                            i /= i3;
                        }
                    }
                    activityModeSummaryResponse.setHeartRate(i);
                    ArrayList arrayList2 = new ArrayList();
                    long timeStamp = bleActivityDetailData.getTimeStamp() + (bleActivityDetailData.getDuration() * 1000);
                    int leastSampleFrequency = bleActivityDetailData.getLeastSampleFrequency() * 1000;
                    long timeStamp2 = bleActivityDetailData.getTimeStamp();
                    while (timeStamp2 < timeStamp) {
                        ActivityStepsSample activityStepsSample = new ActivityStepsSample();
                        activityStepsSample.setStepsTimeStamp(timeStamp2);
                        List<StepSample> stepSampleList = bleActivityDetailData.getStepSampleList();
                        if (!AppUtils.isEmpty(stepSampleList)) {
                            for (StepSample stepSample : stepSampleList) {
                                if (timeStamp2 == stepSample.getTimeStamp()) {
                                    i2 = stepSample.getValue();
                                    break;
                                }
                            }
                        }
                        i2 = 0;
                        activityStepsSample.setStepsValue(i2);
                        List<CalorieSample> calorieSampleList = bleActivityDetailData.getCalorieSampleList();
                        long j3 = 0;
                        if (!AppUtils.isEmpty(calorieSampleList)) {
                            for (CalorieSample calorieSample : calorieSampleList) {
                                if (timeStamp2 == calorieSample.getTimeStamp()) {
                                    j = timeStamp;
                                    j2 = calorieSample.getValue();
                                    break;
                                }
                            }
                        }
                        j = timeStamp;
                        j2 = 0;
                        activityStepsSample.setCalories(j2);
                        List<PaceSample> paceSampleList = bleActivityDetailData.getPaceSampleList();
                        if (!AppUtils.isEmpty(paceSampleList)) {
                            for (PaceSample paceSample : paceSampleList) {
                                if (timeStamp2 == paceSample.getTimeStamp()) {
                                    f = paceSample.getValue();
                                    break;
                                }
                            }
                        }
                        f = 0.0f;
                        activityStepsSample.setPaceInSecond(f);
                        List<SpeedSample> speedSampleList = bleActivityDetailData.getSpeedSampleList();
                        if (!AppUtils.isEmpty(speedSampleList)) {
                            Iterator<SpeedSample> it = speedSampleList.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    SpeedSample next = it.next();
                                    if (timeStamp2 == next.getTimeStamp()) {
                                        j3 = next.getValue();
                                        break;
                                    }
                                }
                            }
                        }
                        activityStepsSample.setSpeed((float) j3);
                        arrayList2.add(activityStepsSample);
                        timeStamp2 += leastSampleFrequency;
                        timeStamp = j;
                    }
                    activityModeSummaryResponse.setStepsSampleList(arrayList2);
                    List<HeartSample> heartSampleList2 = bleActivityDetailData.getHeartSampleList();
                    ArrayList arrayList3 = new ArrayList();
                    if (!AppUtils.isEmpty(heartSampleList2)) {
                        arrayList3 = new ArrayList();
                        for (HeartSample heartSample2 : heartSampleList2) {
                            ActivityHeartRateSample activityHeartRateSample = new ActivityHeartRateSample();
                            activityHeartRateSample.setHrValue(heartSample2.getValue());
                            activityHeartRateSample.setHrTimeStamp(heartSample2.getTimeStamp());
                            arrayList3.add(activityHeartRateSample);
                        }
                    }
                    activityModeSummaryResponse.setHeartRateSampleList(arrayList3);
                }
                activityModeSummaryResponse.setFromHAR(bleActivitySummaryData.getIsFromHAR());
                arrayList.add(activityModeSummaryResponse);
            }
            if (arrayList.size() > 0) {
                ActivityModeSummaryResponse activityModeSummaryResponse2 = (ActivityModeSummaryResponse) arrayList.get(arrayList.size() - 1);
                activityModeSummaryResponse2.setComplete(true);
                arrayList.set(arrayList.size() - 1, activityModeSummaryResponse2);
                return arrayList;
            }
            return arrayList;
        }
        return null;
    }

    public static List<BleDynamicSportsField> getBleDynamicFieldData(List<DynamicSportsField> list) {
        Iterator<DynamicSportsField> it;
        if (AppUtils.isEmpty(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<DynamicSportsField> it2 = list.iterator();
        while (it2.hasNext()) {
            DynamicSportsField next = it2.next();
            if (next instanceof DynamicSportFieldText) {
                DynamicSportFieldText dynamicSportFieldText = (DynamicSportFieldText) next;
                arrayList.add(new BleDynamicSportFieldText(dynamicSportFieldText.getColor_rgb_565(), dynamicSportFieldText.getxPosition(), dynamicSportFieldText.getyPosition(), dynamicSportFieldText.getLength(), dynamicSportFieldText.getWidth(), dynamicSportFieldText.getFont(), dynamicSportFieldText.getTypeFace(), dynamicSportFieldText.getText()));
            } else if (next instanceof DynamicSportFieldNumber) {
                DynamicSportFieldNumber dynamicSportFieldNumber = (DynamicSportFieldNumber) next;
                arrayList.add(new BleDynamicSportFieldNumber(dynamicSportFieldNumber.getColor_rgb_565(), dynamicSportFieldNumber.getxPosition(), dynamicSportFieldNumber.getyPosition(), dynamicSportFieldNumber.getLength(), dynamicSportFieldNumber.getWidth(), dynamicSportFieldNumber.getFont(), dynamicSportFieldNumber.getTypeFace(), dynamicSportFieldNumber.getNumber()));
            } else if (next instanceof DynamicSportFieldSquare) {
                DynamicSportFieldSquare dynamicSportFieldSquare = (DynamicSportFieldSquare) next;
                arrayList.add(new BleDynamicSportFieldSquare(dynamicSportFieldSquare.getColor_rgb_565(), dynamicSportFieldSquare.getxPosition(), dynamicSportFieldSquare.getyPosition(), dynamicSportFieldSquare.getLength(), dynamicSportFieldSquare.getWidth()));
            } else if (next instanceof DynamicSportFieldRectangle) {
                DynamicSportFieldRectangle dynamicSportFieldRectangle = (DynamicSportFieldRectangle) next;
                arrayList.add(new BleDynamicSportFieldRectangle(dynamicSportFieldRectangle.getColor_rgb_565(), dynamicSportFieldRectangle.getxPosition(), dynamicSportFieldRectangle.getyPosition(), dynamicSportFieldRectangle.getLength(), dynamicSportFieldRectangle.getWidth()));
            } else if (next instanceof DynamicSportFieldCircular) {
                DynamicSportFieldCircular dynamicSportFieldCircular = (DynamicSportFieldCircular) next;
                arrayList.add(new BleDynamicSportFieldCircular(dynamicSportFieldCircular.getColor_rgb_565(), dynamicSportFieldCircular.getxPosition(), dynamicSportFieldCircular.getyPosition(), dynamicSportFieldCircular.getLength(), dynamicSportFieldCircular.getWidth()));
            } else if (next instanceof DynamicSportFieldImage) {
                DynamicSportFieldImage dynamicSportFieldImage = (DynamicSportFieldImage) next;
                arrayList.add(new BleDynamicSportFieldImage(dynamicSportFieldImage.getColor_rgb_565(), dynamicSportFieldImage.getxPosition(), dynamicSportFieldImage.getyPosition(), dynamicSportFieldImage.getImageId()));
            } else {
                if (next instanceof DynamicSportFieldBBText) {
                    DynamicSportFieldBBText dynamicSportFieldBBText = (DynamicSportFieldBBText) next;
                    it = it2;
                    arrayList.add(new BleDynamicSportFieldBBText(dynamicSportFieldBBText.getColor_rgb_565(), dynamicSportFieldBBText.getxPosition(), dynamicSportFieldBBText.getyPosition(), dynamicSportFieldBBText.getLength(), dynamicSportFieldBBText.getWidth(), dynamicSportFieldBBText.getFont(), dynamicSportFieldBBText.getTypeFace(), dynamicSportFieldBBText.getText(), dynamicSportFieldBBText.getFontSize(), dynamicSportFieldBBText.getBbHeight(), dynamicSportFieldBBText.getBbWidth(), dynamicSportFieldBBText.getAlignment()));
                } else {
                    it = it2;
                    if (next instanceof DynamicSportFieldAnimation) {
                        DynamicSportFieldAnimation dynamicSportFieldAnimation = (DynamicSportFieldAnimation) next;
                        arrayList.add(new BleDynamicSportFieldAnimation(dynamicSportFieldAnimation.getColor_rgb_565(), dynamicSportFieldAnimation.getxPosition(), dynamicSportFieldAnimation.getyPosition(), dynamicSportFieldAnimation.getLength(), dynamicSportFieldAnimation.getWidth(), dynamicSportFieldAnimation.getFormat(), dynamicSportFieldAnimation.getFrameTime(), dynamicSportFieldAnimation.getImgNum(), dynamicSportFieldAnimation.getImgIdList()));
                    } else if (next instanceof DynamicSportFieldButton) {
                        DynamicSportFieldButton dynamicSportFieldButton = (DynamicSportFieldButton) next;
                        arrayList.add(new BleDynamicSportFieldButton(dynamicSportFieldButton.getButtonId(), dynamicSportFieldButton.getColor_rgb_565(), dynamicSportFieldButton.getxPosition(), dynamicSportFieldButton.getyPosition(), dynamicSportFieldButton.getSideBySide(), dynamicSportFieldButton.getLength(), dynamicSportFieldButton.getWidth(), dynamicSportFieldButton.getButtonAction(), dynamicSportFieldButton.getButtonRgb565Color(), dynamicSportFieldButton.getTextFontSize(), dynamicSportFieldButton.getButtonText()));
                    } else if (next instanceof DynamicSportFieldButtonImage) {
                        DynamicSportFieldButtonImage dynamicSportFieldButtonImage = (DynamicSportFieldButtonImage) next;
                        arrayList.add(new BleDynamicSportFieldButtonImage(dynamicSportFieldButtonImage.getButtonId(), dynamicSportFieldButtonImage.getColor_rgb_565(), dynamicSportFieldButtonImage.getxPosition(), dynamicSportFieldButtonImage.getyPosition(), dynamicSportFieldButtonImage.getSideBySide(), dynamicSportFieldButtonImage.getLength(), dynamicSportFieldButtonImage.getWidth(), dynamicSportFieldButtonImage.getButtonAction(), dynamicSportFieldButtonImage.getButtonImageId(), dynamicSportFieldButtonImage.getTextFontSize(), dynamicSportFieldButtonImage.getButtonText()));
                    } else if (next instanceof DynamicSportFieldProgressBar) {
                        DynamicSportFieldProgressBar dynamicSportFieldProgressBar = (DynamicSportFieldProgressBar) next;
                        arrayList.add(new BleDynamicSportFieldProgressBar(dynamicSportFieldProgressBar.getColor_rgb_565(), dynamicSportFieldProgressBar.getxPosition(), dynamicSportFieldProgressBar.getyPosition(), dynamicSportFieldProgressBar.getBarBackgroundImageId(), dynamicSportFieldProgressBar.getBarSlideImageId(), dynamicSportFieldProgressBar.getBarEndImageId(), dynamicSportFieldProgressBar.getBarPercentage()));
                    } else if (next instanceof DynamicSportFieldTextV2) {
                        DynamicSportFieldTextV2 dynamicSportFieldTextV2 = (DynamicSportFieldTextV2) next;
                        arrayList.add(new BleDynamicSportFieldTextV2(dynamicSportFieldTextV2.getColor_rgb_565(), dynamicSportFieldTextV2.getxPosition(), dynamicSportFieldTextV2.getyPosition(), dynamicSportFieldTextV2.getLength(), dynamicSportFieldTextV2.getWidth(), dynamicSportFieldTextV2.getFont(), dynamicSportFieldTextV2.getTypeFace(), dynamicSportFieldTextV2.getText(), dynamicSportFieldTextV2.getFormat()));
                    }
                }
                it2 = it;
            }
        }
        return arrayList;
    }

    public static List<BleNavigationItem> getBleNavigationItem(List<NavigationItem> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                BleNavigationItem bleNavigationItem = new BleNavigationItem();
                bleNavigationItem.setImageId(list.get(i).getImageId());
                bleNavigationItem.setDistance(list.get(i).getDistance());
                if (list.get(i).getDynamicSportField() != null) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(list.get(i).getDynamicSportField());
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(getBleDynamicFieldData(arrayList2));
                    if (!arrayList3.isEmpty() && arrayList3.get(0) != null && (arrayList3.get(0) instanceof BleDynamicSportFieldTextV2)) {
                        bleNavigationItem.setBleDynamicSportFieldText((BleDynamicSportFieldTextV2) arrayList3.get(0));
                    }
                }
                arrayList.add(bleNavigationItem);
            }
        }
        return arrayList;
    }

    public static BleWatchDiagnosticFeature getBleWatchDiagnosticFeature(WatchDiagnosticFeature watchDiagnosticFeature) {
        BleWatchDiagnosticFeatureType bleWatchDiagnosticFeatureType;
        if (watchDiagnosticFeature == null || watchDiagnosticFeature.getmFeatureType() == null || (bleWatchDiagnosticFeatureType = getBleWatchDiagnosticFeatureType(watchDiagnosticFeature.getmFeatureType())) == null) {
            return null;
        }
        BleWatchDiagnosticFeature bleWatchDiagnosticFeature = new BleWatchDiagnosticFeature(bleWatchDiagnosticFeatureType);
        bleWatchDiagnosticFeature.setColor(watchDiagnosticFeature.getColor());
        bleWatchDiagnosticFeature.setButtonPosition(watchDiagnosticFeature.getButtonPosition());
        bleWatchDiagnosticFeature.setTimeout(watchDiagnosticFeature.getTimeout());
        bleWatchDiagnosticFeature.setSensorType(watchDiagnosticFeature.getSensorType());
        bleWatchDiagnosticFeature.setVibrationCount(watchDiagnosticFeature.getVibrationCount());
        return bleWatchDiagnosticFeature;
    }

    public static BleWatchDiagnosticFeatureType getBleWatchDiagnosticFeatureType(WatchDiagnosticFeatureType watchDiagnosticFeatureType) {
        if (watchDiagnosticFeatureType != null) {
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.RESERVED) {
                return BleWatchDiagnosticFeatureType.RESERVED;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.CHARGING_TEST) {
                return BleWatchDiagnosticFeatureType.CHARGING_TEST;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.DISPLAY_TEST) {
                return BleWatchDiagnosticFeatureType.DISPLAY_TEST;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.VIBRATION_TEST) {
                return BleWatchDiagnosticFeatureType.VIBRATION_TEST;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.BUTTON_TEST) {
                return BleWatchDiagnosticFeatureType.BUTTON_TEST;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.TOUCHSCREEN_TEST) {
                return BleWatchDiagnosticFeatureType.TOUCHSCREEN_TEST;
            }
            if (watchDiagnosticFeatureType == WatchDiagnosticFeatureType.SENSOR_TEST) {
                return BleWatchDiagnosticFeatureType.SENSOR_TEST;
            }
        }
        return null;
    }

    public static BpResponse getBpResponse(HrBpData hrBpData) {
        int i;
        BpResponse bpResponse = new BpResponse();
        BpDayData bpDayData = new BpDayData();
        bpDayData.setId(hrBpData.getDailyData().getId());
        bpDayData.setmDate(hrBpData.getDailyData().getmDate());
        bpDayData.setSystolicBp(hrBpData.getDailyData().getSystolicBp());
        bpDayData.setDiastolicBp(hrBpData.getDailyData().getDiastolicBp());
        bpDayData.setmMacAddress(hrBpData.getDailyData().getmMacAddress());
        bpDayData.setmActivityType(hrBpData.getDailyData().getmActivityType());
        BpTimeLogBeanData bpTimeLogBeanData = new BpTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (HrBpHourlyData hrBpHourlyData : hrBpData.getDailyData().timeLogBean.getLogBeans()) {
            BpHourData bpHourData = new BpHourData();
            ArrayList<BpIntervalData> arrayList2 = new ArrayList<>();
            Iterator<BpHrMinData> it = hrBpHourlyData.getBpHrMinData().iterator();
            while (it.hasNext()) {
                BpHrMinData next = it.next();
                BpIntervalData bpIntervalData = new BpIntervalData();
                bpIntervalData.setSystolicBp(next.getmSbp());
                bpIntervalData.setDiastolicBp(next.getmDbp());
                arrayList2.add(bpIntervalData);
            }
            bpHourData.setmCodedList(arrayList2);
            bpHourData.setStartHour(hrBpHourlyData.getStartHour());
            bpHourData.setEndHour(hrBpHourlyData.getEndHour());
            bpHourData.setMacAddress(hrBpHourlyData.getmMacAddress());
            bpHourData.setMdiastolicBpPerHour(hrBpHourlyData.getDiastolicBp());
            bpHourData.setmSystolicBpPerHour(hrBpHourlyData.getSystolicBp());
            i2 += bpHourData.getMdiastolicBpPerHour();
            i4 += bpHourData.getmSystolicBpPerHour();
            if (bpHourData.getMdiastolicBpPerHour() != 0) {
                i3++;
            }
            if (bpHourData.getmSystolicBpPerHour() != 0) {
                i5++;
            }
            bpHourData.setDate(hrBpHourlyData.getDate());
            bpHourData.setmCodedList(arrayList2);
            arrayList.add(bpHourData);
        }
        bpTimeLogBeanData.setLogBeans(arrayList);
        bpDayData.setTimeLogBean(bpTimeLogBeanData);
        if (arrayList.size() > 0) {
            i2 = i3 != 0 ? i2 / i3 : 0;
            i = i5 != 0 ? i4 / i5 : 0;
        } else {
            i = i4;
        }
        bpDayData.setDiastolicBp(i2);
        bpDayData.setSystolicBp(i);
        bpResponse.setBpDayData(bpDayData);
        return bpResponse;
    }

    public static DailyCalorieData getCalorieResponse(com.coveiot.sdk.ble.api.model.DailyCalorieData dailyCalorieData) {
        DailyCalorieData dailyCalorieData2 = new DailyCalorieData();
        ArrayList arrayList = new ArrayList();
        dailyCalorieData2.setInterval(dailyCalorieData.getInterval());
        dailyCalorieData2.setDate(dailyCalorieData.getDate());
        for (CalorieHourlyData calorieHourlyData : dailyCalorieData.getHourlyDataList()) {
            arrayList.add(new com.coveiot.android.bleabstract.models.CalorieHourlyData(calorieHourlyData.getDate(), calorieHourlyData.getStartHour(), calorieHourlyData.getEndHour(), calorieHourlyData.getCodeValues(), calorieHourlyData.getCalorieValue()));
        }
        dailyCalorieData2.setHourlyDataList(arrayList);
        return dailyCalorieData2;
    }

    public static ConfiguredActivities getConfiguredActivities(BleConfiguredActivities bleConfiguredActivities) {
        ArrayList arrayList = new ArrayList();
        for (BleConfiguredActivities.ActivityInfo activityInfo : bleConfiguredActivities.getActivityInfoList()) {
            arrayList.add(new ConfiguredActivities.ActivityInfo(activityInfo.getActivityNum(), activityInfo.getActivityId(), activityInfo.getCategoryId(), activityInfo.getOrderId()));
        }
        return new ConfiguredActivities(arrayList);
    }

    public static SetCustomReminderReq getCustomReminderReq(@NotNull SetCustomRemindersRequest setCustomRemindersRequest) {
        ArrayList arrayList = new ArrayList();
        Iterator<CustomReminderAbstract> it = setCustomRemindersRequest.getCustomReminderAbstractArrayList().iterator();
        while (it.hasNext()) {
            CustomReminderAbstract next = it.next();
            if (next instanceof MedicineReminderAbstract) {
                MedicineReminderAbstract medicineReminderAbstract = (MedicineReminderAbstract) next;
                Date endDate = medicineReminderAbstract.getEndDate();
                if (endDate == null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(medicineReminderAbstract.getStartDate());
                    calendar.add(1, 80);
                    endDate = calendar.getTime();
                }
                Date startDate = medicineReminderAbstract.getStartDate();
                if (startDate != null) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(startDate);
                    calendar2.set(11, 0);
                    calendar2.set(12, 0);
                    calendar2.set(13, 0);
                    calendar2.set(14, 0);
                    startDate = calendar2.getTime();
                }
                Date date = startDate;
                if (endDate != null) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(endDate);
                    calendar3.set(11, 0);
                    calendar3.set(12, 0);
                    calendar3.set(13, 0);
                    calendar3.set(14, 0);
                    endDate = calendar3.getTime();
                }
                Date date2 = endDate;
                MedicineReminderAbstract medicineReminderAbstract2 = (MedicineReminderAbstract) next;
                arrayList.add(new MedicineReminder(next.isReminderOn(), next.getImageId(), next.isSunday(), next.isMonday(), next.isTuesday(), next.isWednesday(), next.isThursday(), next.isFriday(), next.isSaturday(), next.getVibrationSeqID(), next.getDescription(), date, date2, medicineReminderAbstract2.getAdvanceTime(), medicineReminderAbstract2.getTimeInfos()));
            }
            if (next instanceof MeetingReminderAbstract) {
                MeetingReminderAbstract meetingReminderAbstract = (MeetingReminderAbstract) next;
                Date endDate2 = meetingReminderAbstract.getEndDate();
                if (endDate2 == null) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTime(meetingReminderAbstract.getStartDate());
                    calendar4.add(1, 80);
                    endDate2 = calendar4.getTime();
                }
                Date startDate2 = meetingReminderAbstract.getStartDate();
                if (startDate2 != null) {
                    Calendar calendar5 = Calendar.getInstance();
                    calendar5.setTime(startDate2);
                    calendar5.set(11, 0);
                    calendar5.set(12, 0);
                    calendar5.set(13, 0);
                    calendar5.set(14, 0);
                    startDate2 = calendar5.getTime();
                }
                Date date3 = startDate2;
                if (endDate2 != null) {
                    Calendar calendar6 = Calendar.getInstance();
                    calendar6.setTime(endDate2);
                    calendar6.set(11, 0);
                    calendar6.set(12, 0);
                    calendar6.set(13, 0);
                    calendar6.set(14, 0);
                    endDate2 = calendar6.getTime();
                }
                Date date4 = endDate2;
                MeetingReminderAbstract meetingReminderAbstract2 = (MeetingReminderAbstract) next;
                arrayList.add(new MeetingReminder(next.isReminderOn(), next.getImageId(), next.isSunday(), next.isMonday(), next.isTuesday(), next.isWednesday(), next.isThursday(), next.isFriday(), next.isSaturday(), next.getVibrationSeqID(), next.getDescription(), date3, date4, meetingReminderAbstract2.getAdvanceTime(), meetingReminderAbstract2.getTimeInfos()));
            }
            if (next instanceof OtherReminderAbstract) {
                OtherReminderAbstract otherReminderAbstract = (OtherReminderAbstract) next;
                Date endDate3 = otherReminderAbstract.getEndDate();
                if (endDate3 == null) {
                    Calendar calendar7 = Calendar.getInstance();
                    calendar7.setTime(otherReminderAbstract.getStartDate());
                    calendar7.add(1, 80);
                    endDate3 = calendar7.getTime();
                }
                Date startDate3 = otherReminderAbstract.getStartDate();
                if (startDate3 != null) {
                    Calendar calendar8 = Calendar.getInstance();
                    calendar8.setTime(startDate3);
                    calendar8.set(11, 0);
                    calendar8.set(12, 0);
                    calendar8.set(13, 0);
                    calendar8.set(14, 0);
                    startDate3 = calendar8.getTime();
                }
                Date date5 = startDate3;
                if (endDate3 != null) {
                    Calendar calendar9 = Calendar.getInstance();
                    calendar9.setTime(endDate3);
                    calendar9.set(11, 0);
                    calendar9.set(12, 0);
                    calendar9.set(13, 0);
                    calendar9.set(14, 0);
                    endDate3 = calendar9.getTime();
                }
                OtherReminderAbstract otherReminderAbstract2 = (OtherReminderAbstract) next;
                arrayList.add(new OtherReminder(next.isReminderOn(), next.getImageId(), next.isSunday(), next.isMonday(), next.isTuesday(), next.isWednesday(), next.isThursday(), next.isFriday(), next.isSaturday(), next.getVibrationSeqID(), next.getDescription(), date5, endDate3, otherReminderAbstract2.getAdvanceTime(), otherReminderAbstract2.getTimeInfos()));
            }
            if (next instanceof HandWashReminderAbstract) {
                TimeInfo endTime = ((HandWashReminderAbstract) next).getEndTime();
                if (endTime == null) {
                    endTime = new TimeInfo(23, 59);
                }
                HandWashReminderAbstract handWashReminderAbstract = (HandWashReminderAbstract) next;
                arrayList.add(new HandWashReminder(next.isReminderOn(), next.getImageId(), next.isSunday(), next.isMonday(), next.isTuesday(), next.isWednesday(), next.isThursday(), next.isFriday(), next.isSaturday(), next.getVibrationSeqID(), next.getDescription(), handWashReminderAbstract.getStartTime(), endTime, handWashReminderAbstract.getFrequency()));
            }
            if (next instanceof DrinkingReminderAbstract) {
                TimeInfo endTime2 = ((DrinkingReminderAbstract) next).getEndTime();
                if (endTime2 == null) {
                    endTime2 = new TimeInfo(23, 59);
                }
                boolean isReminderOn = next.isReminderOn();
                int imageId = next.getImageId();
                boolean isSunday = next.isSunday();
                boolean isMonday = next.isMonday();
                boolean isTuesday = next.isTuesday();
                boolean isWednesday = next.isWednesday();
                boolean isThursday = next.isThursday();
                boolean isFriday = next.isFriday();
                boolean isSaturday = next.isSaturday();
                int vibrationSeqID = next.getVibrationSeqID();
                String description = next.getDescription();
                DrinkingReminderAbstract drinkingReminderAbstract = (DrinkingReminderAbstract) next;
                arrayList.add(new DrinkingReminder(isReminderOn, imageId, isSunday, isMonday, isTuesday, isWednesday, isThursday, isFriday, isSaturday, vibrationSeqID, description, drinkingReminderAbstract.getStartTime(), endTime2, drinkingReminderAbstract.getFrequency()));
            }
        }
        return new SetCustomReminderReq(null, (short) 1, arrayList);
    }

    public static DailyDistanceData getDistanceResponse(com.coveiot.sdk.ble.api.model.DailyDistanceData dailyDistanceData) {
        DailyDistanceData dailyDistanceData2 = new DailyDistanceData();
        ArrayList arrayList = new ArrayList();
        dailyDistanceData2.setInterval(dailyDistanceData.getInterval());
        dailyDistanceData2.setDate(dailyDistanceData.getDate());
        for (DistanceHourlyData distanceHourlyData : dailyDistanceData.getDistanceHourlyDataList()) {
            arrayList.add(new com.coveiot.android.bleabstract.models.DistanceHourlyData(distanceHourlyData.getDate(), distanceHourlyData.getStartHour(), distanceHourlyData.getEndHour(), distanceHourlyData.getCodeValues(), distanceHourlyData.getDistanceValue()));
        }
        dailyDistanceData2.setDistanceHourlyDataList(arrayList);
        return dailyDistanceData2;
    }

    public static HeartRateResponse getHeartRateResponse(HrBpData hrBpData) {
        HeartRateResponse heartRateResponse = new HeartRateResponse();
        HeartRateDayData heartRateDayData = new HeartRateDayData();
        heartRateDayData.setId(hrBpData.getDailyData().getId());
        heartRateDayData.setmDate(hrBpData.getDailyData().getmDate());
        heartRateDayData.setmMacAddress(hrBpData.getDailyData().getmMacAddress());
        heartRateDayData.setmActivityType(hrBpData.getDailyData().getmActivityType());
        HeartRateTimeLogBeanData heartRateTimeLogBeanData = new HeartRateTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (HrBpHourlyData hrBpHourlyData : hrBpData.getDailyData().timeLogBean.getLogBeans()) {
            HeartRateHourData heartRateHourData = new HeartRateHourData();
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            Iterator<BpHrMinData> it = hrBpHourlyData.getBpHrMinData().iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(it.next().getmHr()));
            }
            heartRateHourData.setMinuteWiseData(arrayList2);
            heartRateHourData.setMaxHeartRatePerHour(hrBpHourlyData.getMaxHr());
            heartRateHourData.setMinHeartRatePerHour(hrBpHourlyData.getMinHr());
            heartRateHourData.setAvgHeartRatePerHour(hrBpHourlyData.getAveargeHr());
            heartRateHourData.setStartHour(hrBpHourlyData.getStartHour());
            heartRateHourData.setEndHour(hrBpHourlyData.getEndHour());
            heartRateHourData.setMacAddress(hrBpHourlyData.getmMacAddress());
            heartRateHourData.setDate(hrBpHourlyData.getDate());
            if (i3 == 0) {
                i3 = heartRateHourData.getMinHeartRatePerHour();
            } else if (i3 > heartRateHourData.getMinHeartRatePerHour() && heartRateHourData.getMinHeartRatePerHour() != 0) {
                i3 = heartRateHourData.getMinHeartRatePerHour();
            }
            if (i2 == 0) {
                i2 = heartRateHourData.getMaxHeartRatePerHour();
            } else if (i2 < heartRateHourData.getMaxHeartRatePerHour()) {
                i2 = heartRateHourData.getMaxHeartRatePerHour();
            }
            i4 += hrBpHourlyData.getAveargeHr();
            if (hrBpHourlyData.getAveargeHr() != 0) {
                i++;
            }
            arrayList.add(heartRateHourData);
        }
        heartRateDayData.setMaxHeartRate(i2);
        heartRateDayData.setMinHeartRate(i3);
        if (i > 0) {
            heartRateDayData.setAvgHeartRate(i4 / i);
        } else {
            heartRateDayData.setAvgHeartRate(0);
        }
        heartRateTimeLogBeanData.setLogBeans(arrayList);
        heartRateDayData.setTimeLogBean(heartRateTimeLogBeanData);
        heartRateResponse.setHeartRateData(heartRateDayData);
        return heartRateResponse;
    }

    public static ArrayList<ManualBpReading> getManualBpReadingList(ArrayList<ManualBpData> arrayList) {
        if (arrayList != null) {
            ArrayList<ManualBpReading> arrayList2 = new ArrayList<>();
            Iterator<ManualBpData> it = arrayList.iterator();
            while (it.hasNext()) {
                ManualBpData next = it.next();
                arrayList2.add(next != null ? new ManualBpReading(next.getTimeStamp(), next.getSystolicbp(), next.getDiastolicbp(), next.getHr()) : null);
            }
            return arrayList2;
        }
        return null;
    }

    public static ArrayList<ManualSpo2Reading> getManualSpo2Response(ArrayList<ManualSpo2Data> arrayList) {
        ArrayList<ManualSpo2Reading> arrayList2 = new ArrayList<>();
        Iterator<ManualSpo2Data> it = arrayList.iterator();
        while (it.hasNext()) {
            ManualSpo2Data next = it.next();
            ManualSpo2Reading manualSpo2Reading = new ManualSpo2Reading();
            manualSpo2Reading.setTimeStamp(next.getTimeStamp());
            manualSpo2Reading.setSpo2(next.getSpo2Value());
            manualSpo2Reading.setLevelInterpretation(false);
            arrayList2.add(manualSpo2Reading);
        }
        return arrayList2;
    }

    public static PeriodicSpo2Response getPeriodicSpo2Response(Spo2Data spo2Data) {
        PeriodicSpo2Response periodicSpo2Response = new PeriodicSpo2Response();
        Spo2DayData spo2DayData = new Spo2DayData();
        spo2DayData.setId(spo2Data.getSpo2DailyData().getId());
        spo2DayData.setmDate(spo2Data.getSpo2DailyData().getmDate());
        spo2DayData.setmMacAddress(spo2Data.getSpo2DailyData().getmMacAddress());
        spo2DayData.setmActivityType(spo2Data.getSpo2DailyData().getmActivityType());
        Spo2TimeLogBeanData spo2TimeLogBeanData = new Spo2TimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        double d = 0.0d;
        int i2 = 0;
        int i3 = 0;
        for (Spo2HourlyData spo2HourlyData : spo2Data.getSpo2DailyData().timeLogBean.getLogBeans()) {
            Spo2HourData spo2HourData = new Spo2HourData();
            new ArrayList();
            spo2HourData.setMinuteWiseData(spo2HourlyData.spo2MinuteData);
            spo2HourData.setMaxSpo2PerHour(spo2HourlyData.getMaxSpo2PerHour());
            spo2HourData.setMinSpo2PerHour(spo2HourlyData.getMinSpo2PerHour());
            spo2HourData.setAvgSpo2PerHour(spo2HourlyData.getAvgSpo2PerHour());
            spo2HourData.setStartHour(spo2HourlyData.getStartHour());
            spo2HourData.setEndHour(spo2HourlyData.getEndHour());
            spo2HourData.setMacAddress(spo2HourlyData.getmMacAddress());
            spo2HourData.setDate(spo2HourlyData.getDate());
            if (i3 == 0) {
                i3 = spo2HourData.getMinSpo2PerHour();
            } else if (i3 > spo2HourData.getMinSpo2PerHour() && spo2HourData.getMinSpo2PerHour() != 0) {
                i3 = spo2HourData.getMinSpo2PerHour();
            }
            if (i2 == 0) {
                i2 = spo2HourData.getMaxSpo2PerHour();
            } else if (i2 < spo2HourData.getMaxSpo2PerHour()) {
                i2 = spo2HourData.getMaxSpo2PerHour();
            }
            d += spo2HourlyData.getAvgSpo2PerHour();
            if (spo2HourlyData.getAvgSpo2PerHour() != 0.0d) {
                i++;
            }
            arrayList.add(spo2HourData);
        }
        spo2DayData.setMaxSPo2(i2);
        spo2DayData.setMinSpo2(i3);
        if (i > 0) {
            spo2DayData.setAvgSpo2(d / i);
        } else {
            spo2DayData.setAvgSpo2(0.0d);
        }
        spo2TimeLogBeanData.setLogBeans(arrayList);
        spo2DayData.setTimeLogBean(spo2TimeLogBeanData);
        periodicSpo2Response.setSpo2DayData(spo2DayData);
        return periodicSpo2Response;
    }

    public static ArrayList<RawAccelerometerHistoryData> getRawAccelerometerHistoryData(ArrayList<com.coveiot.sdk.ble.api.model.RawAccelerometerHistoryData> arrayList) {
        ArrayList<RawAccelerometerHistoryData> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            Iterator<com.coveiot.sdk.ble.api.model.RawAccelerometerHistoryData> it = arrayList.iterator();
            while (it.hasNext()) {
                com.coveiot.sdk.ble.api.model.RawAccelerometerHistoryData next = it.next();
                int recordNumber = next.getRecordNumber();
                long timeStamp = next.getTimeStamp();
                ArrayList<BleAccelerometerData> accelerometerData = next.getAccelerometerData();
                ArrayList arrayList3 = new ArrayList();
                Iterator<BleAccelerometerData> it2 = accelerometerData.iterator();
                while (it2.hasNext()) {
                    BleAccelerometerData next2 = it2.next();
                    arrayList3.add(new AccelerometerData(next2.getX(), next2.getY(), next2.getZ()));
                }
                arrayList2.add(new RawAccelerometerHistoryData(recordNumber, timeStamp, arrayList3, next.getSamplingRate(), next.getInterval(), next.getDuration()));
            }
        }
        return arrayList2;
    }

    public static ArrayList<RawPPGHistoryData> getRawPPGHistoryData(ArrayList<com.coveiot.sdk.ble.api.model.RawPPGHistoryData> arrayList) {
        ArrayList<RawPPGHistoryData> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            Iterator<com.coveiot.sdk.ble.api.model.RawPPGHistoryData> it = arrayList.iterator();
            while (it.hasNext()) {
                com.coveiot.sdk.ble.api.model.RawPPGHistoryData next = it.next();
                arrayList2.add(new RawPPGHistoryData(next.getRecordNumber(), next.getTimeStamp(), next.getPpgData(), next.getSamplingRate(), next.getPpgType(), next.getInterval(), next.getDuration(), next.getMovementLevel()));
            }
        }
        return arrayList2;
    }

    public static RrResponse getRrResponse(HrBpData hrBpData) {
        RrResponse rrResponse = new RrResponse();
        RrDayData rrDayData = new RrDayData();
        rrDayData.setId(hrBpData.getDailyData().getId());
        rrDayData.setmDate(hrBpData.getDailyData().getmDate());
        rrDayData.setmMacAddress(hrBpData.getDailyData().getmMacAddress());
        rrDayData.setmActivityType(hrBpData.getDailyData().getmActivityType());
        rrDayData.setRrInterval(hrBpData.getDailyData().getRrValue());
        RrTimeLogBeanData rrTimeLogBeanData = new RrTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        for (HrBpHourlyData hrBpHourlyData : hrBpData.getDailyData().timeLogBean.getLogBeans()) {
            RrHourData rrHourData = new RrHourData();
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            Iterator<BpHrMinData> it = hrBpHourlyData.getBpHrMinData().iterator();
            while (it.hasNext()) {
                arrayList2.add(Integer.valueOf(it.next().getmRr()));
            }
            rrHourData.setMinuteWiseData(arrayList2);
            rrHourData.setStartHour(hrBpHourlyData.getStartHour());
            rrHourData.setEndHour(hrBpHourlyData.getEndHour());
            rrHourData.setMacAddress(hrBpHourlyData.getmMacAddress());
            rrHourData.setDate(hrBpHourlyData.getDate());
            rrHourData.setmRrIntervalPerHour(hrBpHourlyData.getRrValue());
            arrayList.add(rrHourData);
        }
        rrTimeLogBeanData.setLogs(arrayList);
        rrDayData.setTimeLogBean(rrTimeLogBeanData);
        rrResponse.setRrResponse(rrDayData);
        return rrResponse;
    }

    public static ArrayList<GetSOSRecordsItem> getSOSRecordsItemList(ArrayList<SOSRecordItem> arrayList) {
        ArrayList<GetSOSRecordsItem> arrayList2 = new ArrayList<>();
        if (!AppUtils.isEmpty(arrayList)) {
            Iterator<SOSRecordItem> it = arrayList.iterator();
            while (it.hasNext()) {
                SOSRecordItem next = it.next();
                arrayList2.add(new GetSOSRecordsItem(next.getStatus(), next.getUnixTimeStamp(), next.getContactNameLength(), next.getContactName(), next.getContactNumberLength(), next.getContactNumber()));
            }
        }
        return arrayList2;
    }

    public static ArrayList<SedentaryData> getSedentaryResponse(ArrayList<BleSedentaryData> arrayList) {
        ArrayList<SedentaryData> arrayList2 = new ArrayList<>();
        if (!AppUtils.isEmpty(arrayList)) {
            Iterator<BleSedentaryData> it = arrayList.iterator();
            while (it.hasNext()) {
                BleSedentaryData next = it.next();
                arrayList2.add(new SedentaryData(next.getRecordNumber(), next.getTimeStamp()));
            }
        }
        return arrayList2;
    }

    public static ArrayList<SensAISummaryData> getSensAISummaryResponse(ArrayList<BleSensAISummaryData> arrayList) {
        ArrayList<SensAISummaryData> arrayList2 = new ArrayList<>();
        if (!AppUtils.isEmpty(arrayList)) {
            for (Iterator<BleSensAISummaryData> it = arrayList.iterator(); it.hasNext(); it = it) {
                BleSensAISummaryData next = it.next();
                arrayList2.add(new SensAISummaryData(next.getTimeStamp(), next.getActivityNum(), next.getSessionId(), next.getActivityType(), next.getDuration(), next.getTotalSteps(), next.getTotalCalories(), next.getHand(), next.getGoalType(), next.getTargetGoalValue(), next.getGoalCompletionPct(), next.getTotalSwings(), next.getPlayed(), next.getHitPct(), next.getMaxHandSpeed(), next.getAvgHandSpeed(), next.getAvgHr(), next.getMaxHr(), next.getBowlingType(), next.getTotalBallsBowled(), next.getMaxArmSpeed(), next.getMinArmSpeed(), next.getAvgArmSpeed()));
            }
        }
        return arrayList2;
    }

    public static SleepResponse getSleepResponse(SleepData sleepData) {
        SleepResponse sleepResponse = new SleepResponse();
        SleepDayData sleepDayData = new SleepDayData();
        sleepDayData.setId(sleepData.getDailyData().getId());
        sleepDayData.setmDate(sleepData.getDailyData().getmDate());
        sleepDayData.setmMacAddress(sleepData.getDailyData().getmMacAddress());
        sleepDayData.setmActivityType(sleepData.getDailyData().getmActivityType());
        sleepDayData.setmAwakeTime(sleepData.getDailyData().getmAwakeTime());
        sleepDayData.setmLightSleep(sleepData.getDailyData().getmLightSleep());
        sleepDayData.setmDeepSleep(sleepData.getDailyData().getmDeepSleep());
        sleepDayData.setmTotalSleep(sleepData.getDailyData().getmDeepSleep() + sleepData.getDailyData().getmLightSleep());
        SleepTimeLogBeanData sleepTimeLogBeanData = new SleepTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        for (SleepHourlyData sleepHourlyData : sleepData.getDailyData().timeLogBean.getLogBeans()) {
            SleepHourData sleepHourData = new SleepHourData();
            sleepHourData.setAwakePerHour(sleepHourlyData.getAwakePerHour());
            sleepHourData.setLightSleepPerHour(sleepHourlyData.getLightSleepPerHour());
            sleepHourData.setDeepSleepPerHour(sleepHourlyData.getDeepSleepPerHour());
            sleepHourData.setActivityType(sleepHourlyData.getmActivityType());
            sleepHourData.setDate(sleepHourlyData.getDate());
            sleepHourData.setStartHour(sleepHourlyData.getStartHour());
            sleepHourData.setEndHour(sleepHourlyData.getEndHour());
            sleepHourData.setMacAddress(sleepHourlyData.getmMacAddress());
            sleepHourData.setMinuteWiseData(sleepHourlyData.getmMinuteWiseData());
            arrayList.add(sleepHourData);
        }
        sleepTimeLogBeanData.setLogBeans(arrayList);
        sleepDayData.setTimeLogBean(sleepTimeLogBeanData);
        sleepResponse.setSleepDayData(sleepDayData);
        return sleepResponse;
    }

    public static StepsResponse getStepsResponse(StepsData stepsData) {
        StepsResponse stepsResponse = new StepsResponse();
        StepsDayData stepsDayData = new StepsDayData();
        stepsDayData.setId(stepsData.getDailyData().getId());
        stepsDayData.setmDate(stepsData.getDailyData().getmDate());
        stepsDayData.setmMacAddress(stepsData.getDailyData().getmMacAddress());
        stepsDayData.setmActivityType(stepsData.getDailyData().getmActivityType());
        stepsDayData.setmCalories(stepsData.getDailyData().getmCalories());
        stepsDayData.setmDistance(stepsData.getDailyData().getmDistance());
        stepsDayData.setmSteps(stepsData.getDailyData().getmSteps());
        StepsTimeLogBeanData stepsTimeLogBeanData = new StepsTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        for (StepsHourlyData stepsHourlyData : stepsData.getDailyData().timeLogBean.getLogBeans()) {
            StepsHourData stepsHourData = new StepsHourData();
            stepsHourData.setStepsPerHour(stepsHourlyData.getStepsPerHour());
            stepsHourData.setCaloriesPerHour(stepsHourlyData.getCaloriesPerHour());
            stepsHourData.setDistancePerHour(stepsHourlyData.getmistancePerHour());
            stepsHourData.setActivityType(stepsHourlyData.getmActivityType());
            stepsHourData.setDate(stepsHourlyData.getDate());
            stepsHourData.setStartHour(stepsHourlyData.getStartHour());
            stepsHourData.setEndHour(stepsHourlyData.getEndHour());
            stepsHourData.setMacAddress(stepsHourlyData.getmMacAddress());
            stepsHourData.setMinuteWiseData(stepsHourlyData.getmMinuteWiseData());
            arrayList.add(stepsHourData);
        }
        stepsTimeLogBeanData.setLogBeans(arrayList);
        stepsDayData.setTimeLogBean(stepsTimeLogBeanData);
        stepsResponse.setStepsDayData(stepsDayData);
        return stepsResponse;
    }

    public static StressResponse getStressResponse(StressData stressData) {
        StressResponse stressResponse = new StressResponse();
        if (stressData != null) {
            StressDayData stressDayData = new StressDayData();
            stressDayData.setmDate(stressData.getDailyData().getmDate());
            stressDayData.setmMacAddress(stressData.getDailyData().getmMacAddress());
            stressDayData.setId(stressData.getDailyData().getId());
            stressDayData.setmActivityType(ActivityType.STRESS.toString());
            stressDayData.setAvgStress(stressData.getDailyData().getAvgStress());
            stressDayData.setMaxStress((int) stressData.getDailyData().getMaxStress());
            stressDayData.setMinStress((int) stressData.getDailyData().getMinStress());
            stressDayData.setAvgStress(stressData.getDailyData().getAvgStress());
            stressDayData.setBaselineStress(stressData.getDailyData().getBaselineStress());
            stressDayData.setReadinessStress(stressData.getDailyData().getReadinessStress());
            StressTimeLogBeanData stressTimeLogBeanData = new StressTimeLogBeanData();
            ArrayList arrayList = new ArrayList();
            if (stressData.getDailyData().timeLogBean != null && !AppUtils.isEmpty(stressData.getDailyData().timeLogBean.getLogBeans())) {
                for (StressHourlyData stressHourlyData : stressData.getDailyData().timeLogBean.getLogBeans()) {
                    StressHourData stressHourData = new StressHourData();
                    stressHourData.setDate(stressHourlyData.getDate());
                    stressHourData.setMacAddress(stressHourlyData.getmMacAddress());
                    stressHourData.setActivityType(ActivityType.STRESS.toString());
                    stressHourData.setStartHour(stressHourlyData.getStartHour());
                    stressHourData.setEndHour(stressHourlyData.getEndHour());
                    stressHourData.setMinStress((int) stressHourlyData.getMinStress());
                    stressHourData.setMaxStress((int) stressHourlyData.getMaxStress());
                    stressHourData.setAvgStress(stressHourlyData.getMaxStress());
                    stressHourData.setMinuteWiseData(stressHourlyData.getMinuteWiseStressData());
                    arrayList.add(stressHourData);
                }
                stressTimeLogBeanData.setLogBeans(arrayList);
            }
            stressDayData.setTimeLogBean(stressTimeLogBeanData);
            stressResponse.setStressDayData(stressDayData);
            HRVDayData hRVDayData = new HRVDayData();
            hRVDayData.setmDate(stressData.getDailyData().getmDate());
            hRVDayData.setmMacAddress(stressData.getDailyData().getmMacAddress());
            hRVDayData.setmActivityType(ActivityType.STRESS.toString());
            hRVDayData.setAvgHrv(stressData.getDailyData().getAvgHrv());
            hRVDayData.setMaxHrv(stressData.getDailyData().getMaxHrv());
            hRVDayData.setMinHrv(stressData.getDailyData().getMinHrv());
            hRVDayData.setAvgHrv(stressData.getDailyData().getAvgHrv());
            hRVDayData.setBaselineHrv(stressData.getDailyData().getBaselineHrv());
            hRVDayData.setReadinessHrv(stressData.getDailyData().getReadinessHrv());
            HRVTimeLogBeanData hRVTimeLogBeanData = new HRVTimeLogBeanData();
            ArrayList arrayList2 = new ArrayList();
            if (stressData.getDailyData().timeLogBean != null && !AppUtils.isEmpty(stressData.getDailyData().timeLogBean.getLogBeans())) {
                for (StressHourlyData stressHourlyData2 : stressData.getDailyData().timeLogBean.getLogBeans()) {
                    HRVHourData hRVHourData = new HRVHourData();
                    hRVHourData.setDate(stressHourlyData2.getDate());
                    hRVHourData.setMacAddress(stressHourlyData2.getmMacAddress());
                    hRVHourData.setActivityType(ActivityType.STRESS.toString());
                    hRVHourData.setStartHour(stressHourlyData2.getStartHour());
                    hRVHourData.setEndHour(stressHourlyData2.getEndHour());
                    hRVHourData.setMinHRV(stressHourlyData2.getMinHRV());
                    hRVHourData.setMaxHRV(stressHourlyData2.getMaxHRV());
                    hRVHourData.setAvgHRV(stressHourlyData2.getAvgHRV());
                    hRVHourData.setMinuteWiseData(stressHourlyData2.getMinuteWiseHRVData());
                    arrayList2.add(hRVHourData);
                }
                hRVTimeLogBeanData.setLogBeans(arrayList2);
            }
            hRVDayData.setHrvTimeLogBeanData(hRVTimeLogBeanData);
            stressResponse.setHrvDayData(hRVDayData);
        }
        return stressResponse;
    }

    public static TemperatureResponse getTemperatureResponse(TemperatureData temperatureData) {
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        TemperatureDayData temperatureDayData = new TemperatureDayData();
        temperatureDayData.setId(temperatureData.getDailyData().getId());
        temperatureDayData.setmDate(temperatureData.getDailyData().getmDate());
        temperatureDayData.setmMacAddress(temperatureData.getDailyData().getmMacAddress());
        temperatureDayData.setmActivityType(temperatureData.getDailyData().getmActivityType());
        TemperatureTimeLogBeanData temperatureTimeLogBeanData = new TemperatureTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (TemperatureHourlyData temperatureHourlyData : temperatureData.getDailyData().timeLogBean.getLogBeans()) {
            TemperatureHourData temperatureHourData = new TemperatureHourData();
            new ArrayList();
            temperatureHourData.setMinuteWiseData(temperatureHourlyData.temperatureMinuteData);
            temperatureHourData.setMaxTemperaturePerHour(temperatureHourlyData.getMaxTemperaturePerHour());
            temperatureHourData.setMinTemperaturePerHour(temperatureHourlyData.getMinTemperaturePerHour());
            temperatureHourData.setAvgTemperaturePerHour(temperatureHourlyData.getAvgTemperaturePerHour());
            temperatureHourData.setStartHour(temperatureHourlyData.getStartHour());
            temperatureHourData.setEndHour(temperatureHourlyData.getEndHour());
            temperatureHourData.setMacAddress(temperatureHourlyData.getmMacAddress());
            temperatureHourData.setDate(temperatureHourlyData.getDate());
            if (d2 == 0.0d) {
                d2 = temperatureHourData.getMinTemperaturePerHour();
            } else if (d2 > temperatureHourData.getMinTemperaturePerHour() && temperatureHourData.getMinTemperaturePerHour() != 0.0d) {
                d2 = temperatureHourData.getMinTemperaturePerHour();
            }
            if (d == 0.0d) {
                d = temperatureHourData.getMaxTemperaturePerHour();
            } else if (d < temperatureHourData.getMaxTemperaturePerHour()) {
                d = temperatureHourData.getMaxTemperaturePerHour();
            }
            d3 += temperatureHourlyData.getAvgTemperaturePerHour();
            if (temperatureHourlyData.getAvgTemperaturePerHour() != 0.0d) {
                i++;
            }
            arrayList.add(temperatureHourData);
        }
        temperatureDayData.setMaxTemperature(d);
        temperatureDayData.setMinTemperature(d2);
        if (i > 0) {
            temperatureDayData.setAvgTemperature(d3 / i);
        } else {
            temperatureDayData.setAvgTemperature(0.0d);
        }
        temperatureTimeLogBeanData.setLogBeans(arrayList);
        temperatureDayData.setTimeLogBean(temperatureTimeLogBeanData);
        temperatureResponse.setTemperatureData(temperatureDayData);
        return temperatureResponse;
    }

    public static RrResponse getRrResponse(RrData rrData) {
        RrResponse rrResponse = new RrResponse();
        RrDayData rrDayData = new RrDayData();
        rrDayData.setId(rrData.getDailyData().getId());
        rrDayData.setmDate(rrData.getDailyData().getmDate());
        rrDayData.setmMacAddress(rrData.getDailyData().getmMacAddress());
        rrDayData.setmActivityType(rrData.getDailyData().getmActivityType());
        RrTimeLogBeanData rrTimeLogBeanData = new RrTimeLogBeanData();
        ArrayList arrayList = new ArrayList();
        for (RrHourlyData rrHourlyData : rrData.getDailyData().timeLogBean.getLogBeans()) {
            RrHourData rrHourData = new RrHourData();
            rrHourData.setMinuteWiseData(rrHourlyData.getmMinuteWiseData());
            rrHourData.setStartHour(rrHourlyData.getStartHour());
            rrHourData.setEndHour(rrHourlyData.getEndHour());
            rrHourData.setMacAddress(rrHourlyData.getmMacAddress());
            rrHourData.setDate(rrHourlyData.getDate());
            arrayList.add(rrHourData);
        }
        rrTimeLogBeanData.setLogs(arrayList);
        rrDayData.setTimeLogBean(rrTimeLogBeanData);
        rrResponse.setRrResponse(rrDayData);
        return rrResponse;
    }
}
