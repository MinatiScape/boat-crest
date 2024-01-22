package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ContactData;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SyncContactsRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.android.theme.utils.PhoneNumberUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.model.server.SUserDeviceSettingsReq;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.model.ContactsSettings;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class BTContactsViewmodel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5179a;
    @NotNull
    public final String b;
    @Nullable
    public SuccessResultListener c;

    public BTContactsViewmodel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5179a = context;
        this.b = "ViewModelSOSSettings";
    }

    @NotNull
    public final Context getContext() {
        return this.f5179a;
    }

    @Nullable
    public final SuccessResultListener getMListener() {
        return this.c;
    }

    public final void getUserDeviceSettings(@NotNull final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CoveUserDeviceSettings.getAllUserDeviceSettings(new CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.BTContactsViewmodel$getUserDeviceSettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = BTContactsViewmodel.this.b;
                LogHelper.i(str, "getAllUserDeviceSettings onError error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAllUserDeviceSettingRes getAllUserDeviceSettingRes) {
                String str;
                if (getAllUserDeviceSettingRes == null || getAllUserDeviceSettingRes.getDataBean() == null) {
                    return;
                }
                str = BTContactsViewmodel.this.b;
                LogHelper.i(str, "" + getAllUserDeviceSettingRes.getDataBean());
                if (getAllUserDeviceSettingRes.getDataBean().getContactsSettings() == null || getAllUserDeviceSettingRes.getDataBean().getContactsSettings().size() <= 0) {
                    return;
                }
                BTContactsViewmodel bTContactsViewmodel = BTContactsViewmodel.this;
                Context context2 = context;
                List<ContactsSettings> contactsSettings = getAllUserDeviceSettingRes.getDataBean().getContactsSettings();
                Intrinsics.checkNotNullExpressionValue(contactsSettings, "`object`.dataBean.contactsSettings");
                bTContactsViewmodel.setBTContactsListToWatch(context2, contactsSettings);
            }
        });
    }

    public final void saveBTContactsToServer(@Nullable List<? extends CoveContact> list) {
        SUserDeviceSettingsReq sUserDeviceSettingsReq = new SUserDeviceSettingsReq();
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (CoveContact coveContact : list) {
                ContactsSettings contactsSettings = new ContactsSettings();
                contactsSettings.setName(coveContact.getName());
                contactsSettings.setMobileNumber(coveContact.getPhoneNumber());
                arrayList.add(contactsSettings);
            }
        }
        sUserDeviceSettingsReq.setContactsSettings(arrayList);
        CoveUserDeviceSettings.saveContactsSettings(arrayList, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.BTContactsViewmodel$saveBTContactsToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
            }
        });
    }

    public final void setBTContactsListToWatch(@NotNull final Context context, @NotNull List<? extends ContactsSettings> contactsSettingList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contactsSettingList, "contactsSettingList");
        final ArrayList<ContactData> arrayList = new ArrayList<>();
        for (ContactsSettings contactsSettings : contactsSettingList) {
            String name = contactsSettings.getName();
            Intrinsics.checkNotNullExpressionValue(name, "contact.name");
            String mobileNumber = contactsSettings.getMobileNumber();
            Intrinsics.checkNotNullExpressionValue(mobileNumber, "contact.mobileNumber");
            arrayList.add(new ContactData(name, mobileNumber));
        }
        SyncContactsRequest syncContactsRequest = new SyncContactsRequest.Builder().Builder(arrayList).build();
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Intrinsics.checkNotNullExpressionValue(syncContactsRequest, "syncContactsRequest");
        bleApi.getData(syncContactsRequest, new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.BTContactsViewmodel$setBTContactsListToWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                ArrayList<CoveContact> contacts = AppUtils.getContacts(context);
                ArrayList<CoveContact> arrayList2 = new ArrayList<>();
                ArrayList arrayList3 = new ArrayList();
                Iterator<CoveContact> it = contacts.iterator();
                while (it.hasNext()) {
                    CoveContact next = it.next();
                    PhoneNumberUtils phoneNumberUtils = PhoneNumberUtils.INSTANCE;
                    Context context2 = context;
                    String phoneNumber = next.getPhoneNumber();
                    Intrinsics.checkNotNullExpressionValue(phoneNumber, "i.phoneNumber");
                    String verifyPhoneNumber = phoneNumberUtils.verifyPhoneNumber(context2, phoneNumber);
                    if (verifyPhoneNumber != null) {
                        arrayList3.add(new CoveContact(next.getId(), next.getName(), verifyPhoneNumber, next.getRunningContactId()));
                    }
                }
                Iterator<ContactData> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ContactData next2 = it2.next();
                    Iterator it3 = arrayList3.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            CoveContact coveContact = (CoveContact) it3.next();
                            if (Intrinsics.areEqual(next2.getMobileNumber(), coveContact.getPhoneNumber())) {
                                arrayList2.add(coveContact);
                                break;
                            }
                        }
                    }
                }
                UserDataManager.getInstance(context).saveSyncedContacts(arrayList2);
                SuccessResultListener mListener = this.getMListener();
                if (mListener != null) {
                    mListener.onSuccess();
                }
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
}
