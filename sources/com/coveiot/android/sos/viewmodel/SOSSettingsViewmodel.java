package com.coveiot.android.sos.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetSOSConfigRequest;
import com.coveiot.android.bleabstract.request.GetSOSRecordRequest;
import com.coveiot.android.bleabstract.request.SetSOSConfigRequest;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetSOSConfigResponse;
import com.coveiot.android.bleabstract.response.GetSOSRecordsItem;
import com.coveiot.android.bleabstract.response.GetSOSRecordsResponse;
import com.coveiot.android.sos.R;
import com.coveiot.android.sos.utils.SOSConstants;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.PhoneNumberUtils;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.sos.CoveSOSApi;
import com.coveiot.coveaccess.sos.model.SosEventReq;
import com.coveiot.coveaccess.userdevicesetting.model.SOSSettings;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.covepreferences.sos.SOSEvents;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.coveiot.utils.utility.UtilConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SOSSettingsViewmodel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5788a;
    @NotNull
    public final String b;
    @Nullable
    public SuccessResultListener c;

    public SOSSettingsViewmodel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5788a = context;
        this.b = "ViewModelSOSSettings";
    }

    public final void a(Context context, SosEventReq.EventItem eventItem) {
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String eventDate = eventItem.getEventDate();
        Intrinsics.checkNotNullExpressionValue(eventDate, "eventItem.eventDate");
        SOSEvents sOSEvents = new SOSEvents(themesUtils.getTimeStampFromDate(eventDate), eventItem.getStatus(), eventItem.getContactItems().getName(), eventItem.getContactItems().getMobileNumber(), Boolean.TRUE);
        ArrayList<SOSEvents> arrayList = new ArrayList<>();
        ArrayList<SOSEvents> sOSEventsList = SessionManager.getInstance(context).getSOSEventsList();
        if (!(sOSEventsList == null || sOSEventsList.isEmpty())) {
            arrayList.addAll(SessionManager.getInstance(context).getSOSEventsList());
        }
        arrayList.add(sOSEvents);
        SessionManager.getInstance(context).saveSOSEventsList(arrayList);
    }

    @NotNull
    public final Context getContext() {
        return this.f5788a;
    }

    @Nullable
    public final SuccessResultListener getMListener() {
        return this.c;
    }

    public final void getSOSConfigSettings() {
        if (BleApiManager.getInstance(this.f5788a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5788a).getBleApi().getData(new GetSOSConfigRequest(), new DataResultListener() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$getSOSConfigSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() == null || !(response.getResponseData() instanceof GetSOSConfigResponse)) {
                        return;
                    }
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSOSConfigResponse");
                    GetSOSConfigResponse getSOSConfigResponse = (GetSOSConfigResponse) responseData;
                    String contactName = getSOSConfigResponse.getContactName();
                    if (contactName == null || contactName.length() == 0) {
                        return;
                    }
                    String contactNumber = getSOSConfigResponse.getContactNumber();
                    if (contactNumber == null || contactNumber.length() == 0) {
                        return;
                    }
                    SOSData sOSData = new SOSData(Boolean.valueOf(getSOSConfigResponse.isSOSEnable()), getSOSConfigResponse.getContactName(), getSOSConfigResponse.getContactNumber());
                    SessionManager.getInstance(SOSSettingsViewmodel.this.getContext()).saveSOSConfig(sOSData);
                    if (SessionManager.getInstance(SOSSettingsViewmodel.this.getContext()).getSOSContact() == null) {
                        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(SOSSettingsViewmodel.this.getContext(), new String[]{"android.permission.READ_CONTACTS"});
                        Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                        if (checkPermissionsHasGranted.length == 0) {
                            SOSSettingsViewmodel.this.saveSOSContact(sOSData);
                        }
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    SuccessResultListener mListener = SOSSettingsViewmodel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(SOSSettingsViewmodel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.c;
        if (successResultListener != null) {
            successResultListener.onError(this.f5788a.getString(R.string.device_disconnected));
        }
    }

    public final void getSOSRecords() {
        if (BleApiManager.getInstance(this.f5788a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5788a).getBleApi().getData(new GetSOSRecordRequest(), new DataResultListener() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$getSOSRecords$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.getResponseData() != null && (response.getResponseData() instanceof GetSOSRecordsResponse)) {
                        Object responseData = response.getResponseData();
                        Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetSOSRecordsResponse");
                        ArrayList arrayList = new ArrayList();
                        for (GetSOSRecordsItem getSOSRecordsItem : ((GetSOSRecordsResponse) responseData).getRecordsItem()) {
                            String contactName = getSOSRecordsItem.getContactName();
                            boolean z = false;
                            if (!(contactName == null || contactName.length() == 0)) {
                                String contactNumber = getSOSRecordsItem.getContactNumber();
                                if (!((contactNumber == null || contactNumber.length() == 0) ? true : true)) {
                                    SosEventReq.EventItem eventItem = new SosEventReq.EventItem();
                                    eventItem.setType("PHONE_CALL");
                                    SosEventReq.EventItem.ContactItem contactItem = new SosEventReq.EventItem.ContactItem();
                                    contactItem.setName(getSOSRecordsItem.getContactName());
                                    contactItem.setMobileNumber(getSOSRecordsItem.getContactNumber());
                                    eventItem.setContactItems(contactItem);
                                    int status = getSOSRecordsItem.getStatus();
                                    if (status == 1) {
                                        eventItem.setStatus(SOSConstants.ABORTED.getValue());
                                    } else if (status == 2) {
                                        eventItem.setStatus(SOSConstants.CONNECTED.getValue());
                                    } else if (status == 3) {
                                        eventItem.setStatus(SOSConstants.FAILED.getValue());
                                    } else if (status == 4) {
                                        eventItem.setStatus(SOSConstants.BLE_CONNECTION_ERROR.getValue());
                                    }
                                    Long unixTimeStamp = getSOSRecordsItem.getUnixTimeStamp();
                                    eventItem.setEventDate(unixTimeStamp != null ? ThemesUtils.getDateFromTimeStamp(unixTimeStamp.longValue(), SOSSettingsViewmodel.this.getContext(), UtilConstants.SERVER_TIME_FORMAT) : null);
                                    arrayList.add(eventItem);
                                }
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            SOSSettingsViewmodel.this.postSOSEvents(arrayList);
                        }
                    }
                    SuccessResultListener mListener = SOSSettingsViewmodel.this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                    SuccessResultListener mListener = SOSSettingsViewmodel.this.getMListener();
                    if (mListener != null) {
                        mListener.onError(SOSSettingsViewmodel.this.getContext().getString(R.string.something_went_wrong));
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.c;
        if (successResultListener != null) {
            successResultListener.onError(this.f5788a.getString(R.string.device_disconnected));
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void postSOSEvents(@NotNull List<? extends SosEventReq.EventItem> eventItems) {
        boolean z;
        Intrinsics.checkNotNullParameter(eventItems, "eventItems");
        ArrayList<SOSEvents> sOSEventsList = SessionManager.getInstance(this.f5788a).getSOSEventsList();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? arrayList = new ArrayList();
        objectRef.element = arrayList;
        ((ArrayList) arrayList).addAll(eventItems);
        boolean z2 = true;
        if (sOSEventsList != null) {
            for (SosEventReq.EventItem eventItem : eventItems) {
                Iterator<SOSEvents> it = sOSEventsList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SOSEvents next = it.next();
                    Long unixTimeStamp = next.getUnixTimeStamp();
                    if (unixTimeStamp != null) {
                        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
                        String eventDate = eventItem.getEventDate();
                        Intrinsics.checkNotNullExpressionValue(eventDate, "eventItem.eventDate");
                        if (unixTimeStamp.equals(themesUtils.getTimeStampFromDate(eventDate))) {
                            z = true;
                            if (!z && m.equals$default(next.getStatus(), eventItem.getStatus(), false, 2, null)) {
                                ((ArrayList) objectRef.element).remove(eventItem);
                                break;
                            }
                        }
                    }
                    z = false;
                    if (!z) {
                    }
                }
            }
            new SosEventReq().setEventItems((List) objectRef.element);
        } else {
            new SosEventReq().setEventItems((List) objectRef.element);
        }
        Collection collection = (Collection) objectRef.element;
        if (collection != null && !collection.isEmpty()) {
            z2 = false;
        }
        if (z2) {
            return;
        }
        SosEventReq sosEventReq = new SosEventReq();
        sosEventReq.setEventItems((List) objectRef.element);
        CoveSOSApi.postSosEvent(sosEventReq, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$postSOSEvents$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                ArrayList<SosEventReq.EventItem> arrayList2 = objectRef.element;
                if (arrayList2 != null) {
                    Iterator<SosEventReq.EventItem> it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        SosEventReq.EventItem updatedEventItem = it2.next();
                        SOSSettingsViewmodel sOSSettingsViewmodel = this;
                        Context context = sOSSettingsViewmodel.getContext();
                        Intrinsics.checkNotNullExpressionValue(updatedEventItem, "updatedEventItem");
                        sOSSettingsViewmodel.a(context, updatedEventItem);
                    }
                }
            }
        });
    }

    public final void postSOSSettings(@Nullable SOSData sOSData) {
        if (AppUtils.isNetConnected(this.f5788a)) {
            SOSSettings sOSSettings = new SOSSettings();
            if (sOSData != null) {
                Boolean isSOSEnabled = sOSData.isSOSEnabled();
                Intrinsics.checkNotNull(isSOSEnabled);
                sOSSettings.setActive(isSOSEnabled.booleanValue());
                ArrayList arrayList = new ArrayList();
                SOSSettings.ContactItem contactItem = new SOSSettings.ContactItem();
                contactItem.setMobileNumber(sOSData.getContactNumber());
                contactItem.setName(sOSData.getContactName());
                arrayList.add(contactItem);
                sOSSettings.setContactsList(arrayList);
            } else {
                sOSSettings.setActive(false);
            }
            CoveUserDeviceSettings.saveSOSSettings(sOSSettings, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$postSOSSettings$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    String str;
                    str = SOSSettingsViewmodel.this.b;
                    LogHelper.d(str, coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                    String str;
                    str = SOSSettingsViewmodel.this.b;
                    LogHelper.d(str, "SOS settings saved successfully");
                }
            });
        }
    }

    public final void saveSOSContact(@Nullable SOSData sOSData) {
        ArrayList<CoveContact> contacts = AppUtils.getContacts(this.f5788a);
        ArrayList arrayList = new ArrayList();
        Iterator<CoveContact> it = contacts.iterator();
        while (it.hasNext()) {
            CoveContact next = it.next();
            PhoneNumberUtils phoneNumberUtils = PhoneNumberUtils.INSTANCE;
            Context context = this.f5788a;
            String phoneNumber = next.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
            String verifyPhoneNumber = phoneNumberUtils.verifyPhoneNumber(context, phoneNumber);
            if (verifyPhoneNumber != null) {
                arrayList.add(new CoveContact(next.getId(), next.getName(), verifyPhoneNumber, next.getRunningContactId()));
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            CoveContact coveContact = (CoveContact) it2.next();
            if (m.equals$default(sOSData != null ? sOSData.getContactNumber() : null, coveContact.getPhoneNumber(), false, 2, null)) {
                SessionManager.getInstance(this.f5788a).saveSOSContact(coveContact);
                return;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.util.ArrayList] */
    public final void sendContactsToDevice(@NotNull final Context context, @NotNull CoveContact contactData) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contactData, "contactData");
        ArrayList<ContactData> arrayList = new ArrayList<>();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? arrayList2 = new ArrayList();
        objectRef.element = arrayList2;
        ((List) arrayList2).add(contactData);
        ArrayList<CoveContact> syncedContacts = UserDataManager.getInstance(context).getSyncedContacts();
        Intrinsics.checkNotNullExpressionValue(syncedContacts, "getInstance(context).syncedContacts");
        ((List) objectRef.element).addAll(syncedContacts);
        for (CoveContact coveContact : (List) objectRef.element) {
            String name = coveContact.getName();
            Intrinsics.checkNotNullExpressionValue(name, "contact.name");
            String phoneNumber = coveContact.getPhoneNumber();
            Intrinsics.checkNotNullExpressionValue(phoneNumber, "contact.phoneNumber");
            arrayList.add(new ContactData(name, phoneNumber));
        }
        SyncContactsRequest syncContactsRequest = new SyncContactsRequest.Builder().Builder(arrayList).build();
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Intrinsics.checkNotNullExpressionValue(syncContactsRequest, "syncContactsRequest");
        bleApi.getData(syncContactsRequest, new DataResultListener() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$sendContactsToDevice$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                SuccessResultListener mListener = SOSSettingsViewmodel.this.getMListener();
                if (mListener != null) {
                    mListener.onError(error.getErrorMsg());
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                UserDataManager.getInstance(context).saveSyncedContacts((ArrayList) objectRef.element);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void setMListener(@Nullable SuccessResultListener successResultListener) {
        this.c = successResultListener;
    }

    public final void setSOSConfigSettings(final boolean z, @NotNull final String contactName, @NotNull final String contactNumber, final boolean z2) {
        Intrinsics.checkNotNullParameter(contactName, "contactName");
        Intrinsics.checkNotNullParameter(contactNumber, "contactNumber");
        String replace$default = m.replace$default(contactNumber, HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
        if (BleApiManager.getInstance(this.f5788a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5788a).getBleApi().setUserSettings(new SetSOSConfigRequest.Builder().setSOSConfigRequests(z, 5, contactName, replace$default).build(), new SettingsResultListener() { // from class: com.coveiot.android.sos.viewmodel.SOSSettingsViewmodel$setSOSConfigSettings$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    SuccessResultListener mListener = this.getMListener();
                    if (mListener != null) {
                        mListener.onError(this.getContext().getString(R.string.something_went_wrong));
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (z) {
                        SOSData sOSData = new SOSData(Boolean.TRUE, contactName, contactNumber);
                        SessionManager.getInstance(this.getContext()).saveSOSConfig(sOSData);
                        this.postSOSSettings(sOSData);
                    } else if (z2) {
                        SessionManager.getInstance(this.getContext()).saveSOSContact(null);
                        SessionManager.getInstance(this.getContext()).saveSOSConfig(null);
                        this.postSOSSettings(null);
                    } else {
                        SOSData sOSData2 = new SOSData(Boolean.FALSE, contactName, contactNumber);
                        SessionManager.getInstance(this.getContext()).saveSOSConfig(sOSData2);
                        this.postSOSSettings(sOSData2);
                    }
                    SuccessResultListener mListener = this.getMListener();
                    if (mListener != null) {
                        mListener.onSuccess();
                    }
                }
            });
            return;
        }
        SuccessResultListener successResultListener = this.c;
        if (successResultListener != null) {
            successResultListener.onError(this.f5788a.getString(R.string.device_disconnected));
        }
    }
}
