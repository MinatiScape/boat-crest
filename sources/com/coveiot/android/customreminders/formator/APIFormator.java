package com.coveiot.android.customreminders.formator;

import com.coveiot.android.customreminders.ReminderType;
import com.coveiot.android.customreminders.model.CustomReminder;
import com.coveiot.android.customreminders.model.DrinkWaterReminder;
import com.coveiot.android.customreminders.model.HandWashReminder;
import com.coveiot.android.customreminders.model.MedicineReminder;
import com.coveiot.android.customreminders.model.MeetingReminder;
import com.coveiot.android.customreminders.model.OtherReminder;
import com.coveiot.android.customreminders.model.RepeatModel;
import com.coveiot.coveaccess.userdevicesetting.SaveCustomRemindersSettingsReq;
import com.coveiot.sdk.ble.api.model.TimeInfo;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class APIFormator {
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String a(TimeInfo timeInfo) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[2];
            objArr[0] = timeInfo != null ? Integer.valueOf(timeInfo.getHour()) : null;
            objArr[1] = Integer.valueOf(timeInfo.getMinute());
            String format = String.format("%02d:%02d:00", Arrays.copyOf(objArr, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            return format;
        }

        public final String b(CustomReminder customReminder) {
            String str;
            String a2;
            String str2 = "";
            if (customReminder instanceof MedicineReminder) {
                MedicineReminder medicineReminder = (MedicineReminder) customReminder;
                String str3 = medicineReminder.isReminderOn() + ";MEDICINE;" + medicineReminder.getDescription() + ';' + AppUtils.formatDate(medicineReminder.getStartDate().getTime(), "yyyy-MM-dd") + ';' + (medicineReminder.getEndDate() == null ? "" : AppUtils.formatDate(medicineReminder.getStartDate().getTime(), "yyyy-MM-dd")) + ';';
                if (!AppUtils.isEmpty(medicineReminder.getTimeInfos())) {
                    Iterator<TimeInfo> it = medicineReminder.getTimeInfos().iterator();
                    while (it.hasNext()) {
                        TimeInfo timeInfo = it.next();
                        if (medicineReminder.getTimeInfos().lastIndexOf(timeInfo) == medicineReminder.getTimeInfos().size() - 1) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(str3);
                            Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfo");
                            sb.append(a(timeInfo));
                            str3 = sb.toString();
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str3);
                            Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfo");
                            sb2.append(a(timeInfo));
                            sb2.append(',');
                            str3 = sb2.toString();
                        }
                    }
                    str3 = str3 + ';';
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str3);
                sb3.append(medicineReminder.getAdvanceTime());
                sb3.append(";:;;");
                RepeatModel repeatModel = medicineReminder.getRepeatModel();
                Intrinsics.checkNotNullExpressionValue(repeatModel, "customReminder.repeatModel");
                sb3.append(c(repeatModel));
                str = AppUtils.convertStringToMD5(sb3.toString());
                Intrinsics.checkNotNullExpressionValue(str, "convertStringToMD5(reminderId)");
            } else {
                str = "";
            }
            if (customReminder instanceof MeetingReminder) {
                MeetingReminder meetingReminder = (MeetingReminder) customReminder;
                String str4 = meetingReminder.isReminderOn() + ";MEETING;" + meetingReminder.getDescription() + ';' + AppUtils.formatDate(meetingReminder.getStartDate().getTime(), "yyyy-MM-dd") + ';' + (meetingReminder.getEndDate() == null ? "" : AppUtils.formatDate(meetingReminder.getStartDate().getTime(), "yyyy-MM-dd")) + ';';
                if (!AppUtils.isEmpty(meetingReminder.getTimeInfos())) {
                    Iterator<TimeInfo> it2 = meetingReminder.getTimeInfos().iterator();
                    while (it2.hasNext()) {
                        TimeInfo timeInfo2 = it2.next();
                        if (meetingReminder.getTimeInfos().lastIndexOf(timeInfo2) == meetingReminder.getTimeInfos().size() - 1) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str4);
                            Intrinsics.checkNotNullExpressionValue(timeInfo2, "timeInfo");
                            sb4.append(a(timeInfo2));
                            str4 = sb4.toString();
                        } else {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(str4);
                            Intrinsics.checkNotNullExpressionValue(timeInfo2, "timeInfo");
                            sb5.append(a(timeInfo2));
                            sb5.append(',');
                            str4 = sb5.toString();
                        }
                    }
                    str4 = str4 + ';';
                }
                StringBuilder sb6 = new StringBuilder();
                sb6.append(str4);
                sb6.append(meetingReminder.getAdvanceTime());
                sb6.append(";:;;");
                RepeatModel repeatModel2 = meetingReminder.getRepeatModel();
                Intrinsics.checkNotNullExpressionValue(repeatModel2, "customReminder.repeatModel");
                sb6.append(c(repeatModel2));
                str = AppUtils.convertStringToMD5(sb6.toString());
                Intrinsics.checkNotNullExpressionValue(str, "convertStringToMD5(reminderId)");
            }
            if (customReminder instanceof OtherReminder) {
                OtherReminder otherReminder = (OtherReminder) customReminder;
                String str5 = otherReminder.isReminderOn() + ";OTHERS;" + otherReminder.getDescription() + ';' + AppUtils.formatDate(otherReminder.getStartDate().getTime(), "yyyy-MM-dd") + ';' + (otherReminder.getEndDate() == null ? "" : AppUtils.formatDate(otherReminder.getStartDate().getTime(), "yyyy-MM-dd")) + ';';
                if (!AppUtils.isEmpty(otherReminder.getTimeInfos())) {
                    Iterator<TimeInfo> it3 = otherReminder.getTimeInfos().iterator();
                    while (it3.hasNext()) {
                        TimeInfo timeInfo3 = it3.next();
                        if (otherReminder.getTimeInfos().lastIndexOf(timeInfo3) == otherReminder.getTimeInfos().size() - 1) {
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append(str5);
                            Intrinsics.checkNotNullExpressionValue(timeInfo3, "timeInfo");
                            sb7.append(a(timeInfo3));
                            str5 = sb7.toString();
                        } else {
                            StringBuilder sb8 = new StringBuilder();
                            sb8.append(str5);
                            Intrinsics.checkNotNullExpressionValue(timeInfo3, "timeInfo");
                            sb8.append(a(timeInfo3));
                            sb8.append(',');
                            str5 = sb8.toString();
                        }
                    }
                    str5 = str5 + ';';
                }
                StringBuilder sb9 = new StringBuilder();
                sb9.append(str5);
                sb9.append(otherReminder.getAdvanceTime());
                sb9.append(";:;;");
                RepeatModel repeatModel3 = otherReminder.getRepeatModel();
                Intrinsics.checkNotNullExpressionValue(repeatModel3, "customReminder.repeatModel");
                sb9.append(c(repeatModel3));
                str = AppUtils.convertStringToMD5(sb9.toString());
                Intrinsics.checkNotNullExpressionValue(str, "convertStringToMD5(reminderId)");
            }
            if (customReminder instanceof HandWashReminder) {
                HandWashReminder handWashReminder = (HandWashReminder) customReminder;
                TimeInfo startTime = handWashReminder.getStartTime();
                Intrinsics.checkNotNullExpressionValue(startTime, "customReminder.startTime");
                String a3 = a(startTime);
                String a4 = a(new TimeInfo(handWashReminder.getFrequency() / 60, handWashReminder.getFrequency() % 60));
                if (handWashReminder.getEndTime() == null) {
                    a2 = "";
                } else {
                    TimeInfo endTime = handWashReminder.getEndTime();
                    Intrinsics.checkNotNullExpressionValue(endTime, "customReminder.endTime");
                    a2 = a(endTime);
                }
                String str6 = handWashReminder.isReminderOn() + ";HAND_WASH;" + handWashReminder.getDescription() + ";;;;;" + a3 + ';' + a2 + ';';
                StringBuilder sb10 = new StringBuilder();
                sb10.append(str6);
                sb10.append(a4);
                sb10.append(';');
                RepeatModel repeatModel4 = handWashReminder.getRepeatModel();
                Intrinsics.checkNotNullExpressionValue(repeatModel4, "customReminder.repeatModel");
                sb10.append(c(repeatModel4));
                str = AppUtils.convertStringToMD5(sb10.toString());
                Intrinsics.checkNotNullExpressionValue(str, "convertStringToMD5(reminderId)");
            }
            if (customReminder instanceof DrinkWaterReminder) {
                DrinkWaterReminder drinkWaterReminder = (DrinkWaterReminder) customReminder;
                TimeInfo startTime2 = drinkWaterReminder.getStartTime();
                Intrinsics.checkNotNullExpressionValue(startTime2, "customReminder.startTime");
                String a5 = a(startTime2);
                String a6 = a(new TimeInfo(drinkWaterReminder.getFrequency() / 60, drinkWaterReminder.getFrequency() % 60));
                if (drinkWaterReminder.getEndTime() != null) {
                    TimeInfo endTime2 = drinkWaterReminder.getEndTime();
                    Intrinsics.checkNotNullExpressionValue(endTime2, "customReminder.endTime");
                    str2 = a(endTime2);
                }
                StringBuilder sb11 = new StringBuilder();
                sb11.append(drinkWaterReminder.isReminderOn() + ";DRINK;" + drinkWaterReminder.getDescription() + ";;;;;" + a5 + ';' + str2 + ';');
                sb11.append(a6);
                sb11.append(';');
                RepeatModel repeatModel5 = drinkWaterReminder.getRepeatModel();
                Intrinsics.checkNotNullExpressionValue(repeatModel5, "customReminder.repeatModel");
                sb11.append(c(repeatModel5));
                String convertStringToMD5 = AppUtils.convertStringToMD5(sb11.toString());
                Intrinsics.checkNotNullExpressionValue(convertStringToMD5, "convertStringToMD5(reminderId)");
                return convertStringToMD5;
            }
            return str;
        }

        public final String c(RepeatModel repeatModel) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            if (repeatModel.sunday) {
                str = "S";
            } else {
                str = "" + Soundex.SILENT_MARKER;
            }
            if (repeatModel.monday) {
                str2 = str + 'M';
            } else {
                str2 = str + Soundex.SILENT_MARKER;
            }
            if (repeatModel.tuesday) {
                str3 = str2 + 'T';
            } else {
                str3 = str2 + Soundex.SILENT_MARKER;
            }
            if (repeatModel.wednesday) {
                str4 = str3 + 'W';
            } else {
                str4 = str3 + Soundex.SILENT_MARKER;
            }
            if (repeatModel.thursday) {
                str5 = str4 + 'T';
            } else {
                str5 = str4 + Soundex.SILENT_MARKER;
            }
            if (repeatModel.friday) {
                str6 = str5 + 'F';
            } else {
                str6 = str5 + Soundex.SILENT_MARKER;
            }
            if (repeatModel.saturday) {
                return str6 + 'S';
            }
            return str6 + Soundex.SILENT_MARKER;
        }

        @NotNull
        public final SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem convertPreferenceToAPI(@NotNull CustomReminder customReminder) {
            Intrinsics.checkNotNullParameter(customReminder, "customReminder");
            SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem customRemindersListItem = new SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem();
            customRemindersListItem.setReminderId(b(customReminder));
            customRemindersListItem.setLabel(customReminder.getDescription());
            customRemindersListItem.setActive(customReminder.isReminderOn());
            RepeatModel repeatModel = customReminder.getRepeatModel();
            Intrinsics.checkNotNullExpressionValue(repeatModel, "customReminder.repeatModel");
            customRemindersListItem.setWeekDays(c(repeatModel));
            d(customReminder, customRemindersListItem);
            return customRemindersListItem;
        }

        public final void d(CustomReminder customReminder, SaveCustomRemindersSettingsReq.CustomReminders.CustomRemindersListItem customRemindersListItem) {
            if (customReminder instanceof MedicineReminder) {
                customRemindersListItem.setType(ReminderType.MEDICINE.name());
                MedicineReminder medicineReminder = (MedicineReminder) customReminder;
                customRemindersListItem.setStartDate(AppUtils.formatDate(medicineReminder.getStartDate().getTime(), "yyyy-MM-dd"));
                if (medicineReminder.getEndDate() != null) {
                    customRemindersListItem.setEndDate(AppUtils.formatDate(medicineReminder.getEndDate().getTime(), "yyyy-MM-dd"));
                }
                customRemindersListItem.setRemindBefore(Integer.valueOf(medicineReminder.getAdvanceTime()));
                if (!AppUtils.isEmpty(medicineReminder.getTimeInfos())) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<TimeInfo> it = medicineReminder.getTimeInfos().iterator();
                    while (it.hasNext()) {
                        TimeInfo timeInfo = it.next();
                        Intrinsics.checkNotNullExpressionValue(timeInfo, "timeInfo");
                        arrayList.add(a(timeInfo));
                    }
                    customRemindersListItem.setRemindAt(arrayList);
                }
            }
            if (customReminder instanceof MeetingReminder) {
                customRemindersListItem.setType(ReminderType.MEETING.name());
                MeetingReminder meetingReminder = (MeetingReminder) customReminder;
                customRemindersListItem.setStartDate(AppUtils.formatDate(meetingReminder.getStartDate().getTime(), "yyyy-MM-dd"));
                if (meetingReminder.getEndDate() != null) {
                    customRemindersListItem.setEndDate(AppUtils.formatDate(meetingReminder.getEndDate().getTime(), "yyyy-MM-dd"));
                }
                customRemindersListItem.setRemindBefore(Integer.valueOf(meetingReminder.getAdvanceTime()));
                if (!AppUtils.isEmpty(meetingReminder.getTimeInfos())) {
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<TimeInfo> it2 = meetingReminder.getTimeInfos().iterator();
                    while (it2.hasNext()) {
                        TimeInfo timeInfo2 = it2.next();
                        Intrinsics.checkNotNullExpressionValue(timeInfo2, "timeInfo");
                        arrayList2.add(a(timeInfo2));
                    }
                    customRemindersListItem.setRemindAt(arrayList2);
                }
            }
            if (customReminder instanceof OtherReminder) {
                customRemindersListItem.setType(ReminderType.OTHERS.name());
                OtherReminder otherReminder = (OtherReminder) customReminder;
                customRemindersListItem.setStartDate(AppUtils.formatDate(otherReminder.getStartDate().getTime(), "yyyy-MM-dd"));
                if (otherReminder.getEndDate() != null) {
                    customRemindersListItem.setEndDate(AppUtils.formatDate(otherReminder.getEndDate().getTime(), "yyyy-MM-dd"));
                }
                customRemindersListItem.setRemindBefore(Integer.valueOf(otherReminder.getAdvanceTime()));
                if (!AppUtils.isEmpty(otherReminder.getTimeInfos())) {
                    ArrayList arrayList3 = new ArrayList();
                    Iterator<TimeInfo> it3 = otherReminder.getTimeInfos().iterator();
                    while (it3.hasNext()) {
                        TimeInfo timeInfo3 = it3.next();
                        Intrinsics.checkNotNullExpressionValue(timeInfo3, "timeInfo");
                        arrayList3.add(a(timeInfo3));
                    }
                    customRemindersListItem.setRemindAt(arrayList3);
                }
            }
            if (customReminder instanceof HandWashReminder) {
                customRemindersListItem.setType(ReminderType.HAND_WASH.name());
                HandWashReminder handWashReminder = (HandWashReminder) customReminder;
                TimeInfo startTime = handWashReminder.getStartTime();
                Intrinsics.checkNotNullExpressionValue(startTime, "customReminder.startTime");
                customRemindersListItem.setStartTime(a(startTime));
                if (handWashReminder.getEndTime() != null) {
                    TimeInfo endTime = handWashReminder.getEndTime();
                    Intrinsics.checkNotNullExpressionValue(endTime, "customReminder.endTime");
                    customRemindersListItem.setEndTime(a(endTime));
                }
                customRemindersListItem.setInterval(a(new TimeInfo(handWashReminder.getFrequency() / 60, handWashReminder.getFrequency() % 60)));
            }
            if (customReminder instanceof DrinkWaterReminder) {
                customRemindersListItem.setType(ReminderType.DRINK.name());
                DrinkWaterReminder drinkWaterReminder = (DrinkWaterReminder) customReminder;
                TimeInfo startTime2 = drinkWaterReminder.getStartTime();
                Intrinsics.checkNotNullExpressionValue(startTime2, "customReminder.startTime");
                customRemindersListItem.setStartTime(a(startTime2));
                if (drinkWaterReminder.getEndTime() != null) {
                    TimeInfo endTime2 = drinkWaterReminder.getEndTime();
                    Intrinsics.checkNotNullExpressionValue(endTime2, "customReminder.endTime");
                    customRemindersListItem.setEndTime(a(endTime2));
                }
                customRemindersListItem.setInterval(a(new TimeInfo(drinkWaterReminder.getFrequency() / 60, drinkWaterReminder.getFrequency() % 60)));
            }
        }
    }
}
