package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import android.icu.util.Calendar;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.DeleteScheduleRequest;
import com.coveiot.android.bleabstract.request.SetScheduleRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.more.listeners.ScheduleListener;
import com.coveiot.android.leonardo.utils.PayUtils;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.userappsetting.CalendarBean;
import com.coveiot.coveaccess.userappsetting.EventsBean;
import com.coveiot.coveaccess.userappsetting.SaveScheduleEventsReq;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.ScheduleData;
import com.coveiot.sdk.ble.api.model.ScheduleInfo;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ScheduleViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5200a;
    public int b;
    public int c;
    public DialogListener dialogListener;
    public ScheduleListener scheduleListener;

    public ScheduleViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5200a = context;
    }

    public final void callSaveApi(@NotNull List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        SaveScheduleEventsReq saveScheduleEventsReq = new SaveScheduleEventsReq();
        CalendarBean calendarBean = new CalendarBean();
        if (scheduleInfos.isEmpty()) {
            calendarBean.setEvents(null);
        } else {
            ArrayList arrayList = new ArrayList();
            for (ScheduleInfo scheduleInfo : scheduleInfos) {
                EventsBean eventsBean = new EventsBean();
                eventsBean.setDescription(scheduleInfo.getContent());
                eventsBean.setSummary(scheduleInfo.getTitle());
                eventsBean.setRemindBefore(Integer.valueOf(scheduleInfo.getAdvance()));
                Calendar calendar = Calendar.getInstance();
                calendar.set(1, scheduleInfo.getYear());
                calendar.set(2, scheduleInfo.getMonth());
                calendar.set(5, scheduleInfo.getDay());
                calendar.set(11, scheduleInfo.getHour());
                calendar.set(12, scheduleInfo.getMinute());
                calendar.set(13, 0);
                eventsBean.setStart(AppUtils.formatDate(calendar.getTime(), UtilConstants.SERVER_TIME_FORMAT));
                eventsBean.setTzOffset(PayUtils.INSTANCE.getTimeZoneOffset());
                arrayList.add(eventsBean);
            }
            calendarBean.setEvents(arrayList);
        }
        if (calendarBean.getEvents() != null && calendarBean.getEvents().size() > 0) {
            saveScheduleEventsReq.setCalendar(calendarBean);
        } else {
            saveScheduleEventsReq.setCalendar(null);
        }
        CoveUserAppSettings.saveScheduleEventsSettings(saveScheduleEventsReq, new ScheduleViewModel$callSaveApi$1(this));
    }

    @NotNull
    public final List<ScheduleInfo> convertFromScheduleDataToScheduleInfo() {
        List<ScheduleData> scheduleData = UserDataManager.getInstance(this.f5200a).getScheduleData();
        Intrinsics.checkNotNull(scheduleData, "null cannot be cast to non-null type kotlin.collections.MutableList<@[FlexibleNullability] com.coveiot.covepreferences.data.ScheduleData?>");
        List<ScheduleData> asMutableList = TypeIntrinsics.asMutableList(scheduleData);
        ArrayList arrayList = new ArrayList();
        for (ScheduleData scheduleData2 : asMutableList) {
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            scheduleInfo.setScheduleId(scheduleData2.getScheduleId());
            scheduleInfo.setTitle(scheduleData2.getTitle());
            scheduleInfo.setContent(scheduleData2.getContent());
            scheduleInfo.setAdvance(scheduleData2.getAdvance());
            scheduleInfo.setDay(scheduleData2.getDay());
            scheduleInfo.setMonth(scheduleData2.getMonth());
            scheduleInfo.setYear(scheduleData2.getYear());
            scheduleInfo.setHour(scheduleData2.getHour());
            scheduleInfo.setMinute(scheduleData2.getMinute());
            arrayList.add(scheduleInfo);
        }
        if ((!arrayList.isEmpty()) && arrayList.size() > 1) {
            kotlin.collections.h.sortWith(arrayList, new Comparator() { // from class: com.coveiot.android.leonardo.more.viewmodel.ScheduleViewModel$convertFromScheduleDataToScheduleInfo$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return kotlin.comparisons.f.compareValues(Integer.valueOf(((ScheduleInfo) t).getScheduleId()), Integer.valueOf(((ScheduleInfo) t2).getScheduleId()));
                }
            });
        }
        return arrayList;
    }

    @NotNull
    public final Context getContext() {
        return this.f5200a;
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

    public final int getScheduleCount() {
        return this.b;
    }

    public final int getScheduleDeletedCount() {
        return this.c;
    }

    @NotNull
    public final ScheduleListener getScheduleListener() {
        ScheduleListener scheduleListener = this.scheduleListener;
        if (scheduleListener != null) {
            return scheduleListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduleListener");
        return null;
    }

    public final void saveToPref(@NotNull List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        ArrayList arrayList = new ArrayList();
        for (ScheduleInfo scheduleInfo : scheduleInfos) {
            ScheduleData scheduleData = ScheduleData.getInstance();
            Intrinsics.checkNotNullExpressionValue(scheduleData, "getInstance()");
            scheduleData.setTitle(scheduleInfo.getTitle());
            scheduleData.setContent(scheduleInfo.getContent());
            scheduleData.setAdvance(scheduleInfo.getAdvance());
            scheduleData.setDay(scheduleInfo.getDay());
            scheduleData.setMonth(scheduleInfo.getMonth());
            scheduleData.setYear(scheduleInfo.getYear());
            scheduleData.setHour(scheduleInfo.getHour());
            scheduleData.setMinute(scheduleInfo.getMinute());
            scheduleData.setScheduleId(scheduleInfo.getScheduleId());
            arrayList.add(scheduleData);
        }
        UserDataManager.getInstance(this.f5200a).saveScheduleSettings(arrayList);
    }

    public final void sendDeleteToBle(@NotNull final List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        if (this.c < scheduleInfos.size()) {
            getDialogListener().onShowProgressDialog();
            BleApiManager.getInstance(this.f5200a).getBleApi().setUserSettings(new DeleteScheduleRequest(scheduleInfos.get(this.c).getScheduleId()), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ScheduleViewModel$sendDeleteToBle$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Toast.makeText(ScheduleViewModel.this.getContext(), ScheduleViewModel.this.getContext().getString(R.string.setting_couldnot_save), 0).show();
                    ScheduleViewModel.this.getDialogListener().onDismiss();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    ScheduleViewModel scheduleViewModel = ScheduleViewModel.this;
                    scheduleViewModel.setScheduleDeletedCount(scheduleViewModel.getScheduleDeletedCount() + 1);
                    if (ScheduleViewModel.this.getScheduleDeletedCount() < scheduleInfos.size()) {
                        ScheduleViewModel.this.sendDeleteToBle(scheduleInfos);
                    } else if (ScheduleViewModel.this.getScheduleDeletedCount() == scheduleInfos.size()) {
                        ScheduleViewModel scheduleViewModel2 = ScheduleViewModel.this;
                        scheduleViewModel2.callSaveApi(scheduleViewModel2.getScheduleListener().getLatestScheduleList());
                        ScheduleViewModel scheduleViewModel3 = ScheduleViewModel.this;
                        scheduleViewModel3.saveToPref(scheduleViewModel3.getScheduleListener().getLatestScheduleList());
                    }
                }
            });
        }
    }

    public final void sendToBle(@NotNull final List<ScheduleInfo> scheduleInfos) {
        Intrinsics.checkNotNullParameter(scheduleInfos, "scheduleInfos");
        if (this.b < scheduleInfos.size()) {
            ScheduleInfo scheduleInfo = scheduleInfos.get(this.b);
            SetScheduleRequest setScheduleReq = new SetScheduleRequest.Builder().setScheduleId(scheduleInfo.getScheduleId()).setTitle(scheduleInfo.getTitle()).setContent(scheduleInfo.getContent()).setAdvance(scheduleInfo.getAdvance()).setDay(scheduleInfo.getDay()).setMonth(scheduleInfo.getMonth()).setYear(scheduleInfo.getYear()).setHour(scheduleInfo.getHour()).setMinute(scheduleInfo.getMinute()).build();
            getDialogListener().onShowProgressDialog();
            BleApi bleApi = BleApiManager.getInstance(this.f5200a).getBleApi();
            Intrinsics.checkNotNullExpressionValue(setScheduleReq, "setScheduleReq");
            bleApi.setUserSettings(setScheduleReq, new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ScheduleViewModel$sendToBle$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Toast.makeText(ScheduleViewModel.this.getContext(), ScheduleViewModel.this.getContext().getString(R.string.setting_couldnot_save), 0).show();
                    ScheduleViewModel.this.getDialogListener().onDismiss();
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    ScheduleViewModel scheduleViewModel = ScheduleViewModel.this;
                    scheduleViewModel.setScheduleCount(scheduleViewModel.getScheduleCount() + 1);
                    if (ScheduleViewModel.this.getScheduleCount() < scheduleInfos.size()) {
                        ScheduleViewModel.this.sendToBle(scheduleInfos);
                    } else if (ScheduleViewModel.this.getScheduleCount() == scheduleInfos.size()) {
                        if (ScheduleViewModel.this.getScheduleListener().getDeletedScheduleList().isEmpty()) {
                            ScheduleViewModel scheduleViewModel2 = ScheduleViewModel.this;
                            scheduleViewModel2.callSaveApi(scheduleViewModel2.getScheduleListener().getLatestScheduleList());
                            ScheduleViewModel scheduleViewModel3 = ScheduleViewModel.this;
                            scheduleViewModel3.saveToPref(scheduleViewModel3.getScheduleListener().getLatestScheduleList());
                            return;
                        }
                        ScheduleViewModel scheduleViewModel4 = ScheduleViewModel.this;
                        scheduleViewModel4.sendDeleteToBle(scheduleViewModel4.getScheduleListener().getDeletedScheduleList());
                    }
                }
            });
        }
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setScheduleCount(int i) {
        this.b = i;
    }

    public final void setScheduleDeletedCount(int i) {
        this.c = i;
    }

    public final void setScheduleListener(@NotNull ScheduleListener scheduleListener) {
        Intrinsics.checkNotNullParameter(scheduleListener, "<set-?>");
        this.scheduleListener = scheduleListener;
    }
}
