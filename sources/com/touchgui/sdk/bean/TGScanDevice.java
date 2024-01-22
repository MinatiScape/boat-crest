package com.touchgui.sdk.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.touchgui.sdk.internal.fa;
/* loaded from: classes12.dex */
public class TGScanDevice implements Parcelable {
    public static final Parcelable.Creator<TGScanDevice> CREATOR = new fa();
    private String address;
    private String name;
    private int rssi;
    private byte[] scanRecord;

    public TGScanDevice(Parcel parcel) {
        this.name = parcel.readString();
        this.address = parcel.readString();
        this.rssi = parcel.readInt();
        this.scanRecord = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public int getRssi() {
        return this.rssi;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setScanRecord(byte[] bArr) {
        this.scanRecord = bArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeInt(this.rssi);
        parcel.writeByteArray(this.scanRecord);
    }

    public TGScanDevice(String str, String str2, int i, byte[] bArr) {
        this.name = str;
        this.address = str2;
        this.rssi = i;
        this.scanRecord = bArr;
    }
}
