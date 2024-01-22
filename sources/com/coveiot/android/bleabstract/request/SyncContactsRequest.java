package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.ContactData;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class SyncContactsRequest extends BleBaseRequest {
    public ArrayList<ContactData> f;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public ArrayList<ContactData> f3569a;

        public Builder Builder(ArrayList<ContactData> arrayList) {
            this.f3569a = arrayList;
            return this;
        }

        public SyncContactsRequest build() {
            SyncContactsRequest syncContactsRequest = new SyncContactsRequest();
            syncContactsRequest.f = this.f3569a;
            return syncContactsRequest;
        }
    }

    public ArrayList<ContactData> getContactDataArrayList() {
        return this.f;
    }
}
