package com.coveiot.sdk.ble.model;

import com.coveiot.sdk.ble.events.ResponseType;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class RawData {
    public ArrayList<String> list;
    private final ResponseType responseType;

    public RawData(ArrayList<String> arrayList, ResponseType responseType) {
        this.list = arrayList;
        this.responseType = responseType;
    }

    public ArrayList<String> getList() {
        return this.list;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }

    public void setList(ArrayList<String> arrayList) {
        this.list = arrayList;
    }
}
