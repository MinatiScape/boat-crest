package com.jieli.bluetooth_connect.bean.history;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.ido.ble.event.stat.one.d;
import java.util.Objects;
@Entity
/* loaded from: classes11.dex */
public class HistoryRecord implements Parcelable {
    public static final Parcelable.Creator<HistoryRecord> CREATOR = new Parcelable.Creator<HistoryRecord>() { // from class: com.jieli.bluetooth_connect.bean.history.HistoryRecord.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HistoryRecord createFromParcel(Parcel parcel) {
            return new HistoryRecord(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HistoryRecord[] newArray(int i) {
            return new HistoryRecord[i];
        }
    };
    @NonNull
    private String address;
    @ColumnInfo(name = d.F)
    private int connectType;
    @ColumnInfo(name = "dev_type")
    private int devType;
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "left_dev_lat")
    private double leftDevLatitude;
    @ColumnInfo(name = "left_dev_lon")
    private double leftDevLongitude;
    @ColumnInfo(name = "left_dev_update_time")
    private long leftDevUpdateTime;
    @ColumnInfo(name = "mapped_address")
    private String mappedAddress;
    private String name;
    @ColumnInfo(name = d.q)
    private long onlineTime;
    private int pid;
    @ColumnInfo(name = "right_dev_lat")
    private double rightDevLatitude;
    @ColumnInfo(name = "right_dev_lon")
    private double rightDevLongitude;
    @ColumnInfo(name = "right_dev_update_time")
    private long rightDevUpdateTime;
    @ColumnInfo(name = "sdk_flag")
    private int sdkFlag;
    private int uid;
    @ColumnInfo(name = "update_address")
    private String updateAddress;
    private int vid;

    public HistoryRecord() {
        this.address = "11:22:33:44:55:66";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HistoryRecord historyRecord = (HistoryRecord) obj;
        return this.connectType == historyRecord.connectType && this.sdkFlag == historyRecord.sdkFlag && this.vid == historyRecord.vid && this.uid == historyRecord.uid && this.pid == historyRecord.pid && Objects.equals(this.address, historyRecord.address) && Objects.equals(this.mappedAddress, historyRecord.mappedAddress);
    }

    @NonNull
    public String getAddress() {
        return this.address;
    }

    public int getConnectType() {
        return this.connectType;
    }

    public int getDevType() {
        return this.devType;
    }

    public int getId() {
        return this.id;
    }

    public double getLeftDevLatitude() {
        return this.leftDevLatitude;
    }

    public double getLeftDevLongitude() {
        return this.leftDevLongitude;
    }

    public long getLeftDevUpdateTime() {
        return this.leftDevUpdateTime;
    }

    public String getMappedAddress() {
        return this.mappedAddress;
    }

    public String getName() {
        return this.name;
    }

    public long getOnlineTime() {
        return this.onlineTime;
    }

    public int getPid() {
        return this.pid;
    }

    public double getRightDevLatitude() {
        return this.rightDevLatitude;
    }

    public double getRightDevLongitude() {
        return this.rightDevLongitude;
    }

    public long getRightDevUpdateTime() {
        return this.rightDevUpdateTime;
    }

    public int getSdkFlag() {
        return this.sdkFlag;
    }

    public int getUid() {
        return this.uid;
    }

    public String getUpdateAddress() {
        return this.updateAddress;
    }

    public int getVid() {
        return this.vid;
    }

    public int hashCode() {
        return Objects.hash(this.address, this.mappedAddress, Integer.valueOf(this.connectType), Integer.valueOf(this.sdkFlag), Integer.valueOf(this.vid), Integer.valueOf(this.uid), Integer.valueOf(this.pid));
    }

    public void setAddress(@NonNull String str) {
        this.address = str;
    }

    public void setConnectType(int i) {
        this.connectType = i;
    }

    public void setDevType(int i) {
        this.devType = i;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setLeftDevLatitude(double d) {
        this.leftDevLatitude = d;
    }

    public void setLeftDevLongitude(double d) {
        this.leftDevLongitude = d;
    }

    public void setLeftDevUpdateTime(long j) {
        this.leftDevUpdateTime = j;
    }

    public void setMappedAddress(String str) {
        this.mappedAddress = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOnlineTime(long j) {
        this.onlineTime = j;
    }

    public void setPid(int i) {
        this.pid = i;
    }

    public void setRightDevLatitude(double d) {
        this.rightDevLatitude = d;
    }

    public void setRightDevLongitude(double d) {
        this.rightDevLongitude = d;
    }

    public void setRightDevUpdateTime(long j) {
        this.rightDevUpdateTime = j;
    }

    public void setSdkFlag(int i) {
        this.sdkFlag = i;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void setUpdateAddress(String str) {
        this.updateAddress = str;
    }

    public void setVid(int i) {
        this.vid = i;
    }

    @NonNull
    public String toString() {
        return "HistoryRecord{id=" + this.id + ", name='" + this.name + "', address='" + this.address + "', mappedAddress='" + this.mappedAddress + "', updateAddress='" + this.updateAddress + "', devType=" + this.devType + ", connectType=" + this.connectType + ", sdkFlag=" + this.sdkFlag + ", vid=" + this.vid + ", uid=" + this.uid + ", pid=" + this.pid + ", leftDevLatitude=" + this.leftDevLatitude + ", leftDevLongitude=" + this.leftDevLongitude + ", leftDevUpdateTime=" + this.leftDevUpdateTime + ", rightDevLatitude=" + this.rightDevLatitude + ", rightDevLongitude=" + this.rightDevLongitude + ", rightDevUpdateTime=" + this.rightDevUpdateTime + ", onlineTime=" + this.onlineTime + '}';
    }

    public void updateLeftDevGpsInfo(double d, double d2, long j) {
        this.leftDevLatitude = d;
        this.leftDevLongitude = d2;
        this.leftDevUpdateTime = j;
    }

    public void updateRightDevGpsInfo(double d, double d2, long j) {
        this.rightDevLatitude = d;
        this.rightDevLongitude = d2;
        this.rightDevUpdateTime = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.address);
        parcel.writeString(this.mappedAddress);
        parcel.writeInt(this.devType);
        parcel.writeInt(this.connectType);
        parcel.writeInt(this.sdkFlag);
        parcel.writeInt(this.vid);
        parcel.writeInt(this.uid);
        parcel.writeInt(this.pid);
        parcel.writeDouble(this.leftDevLatitude);
        parcel.writeDouble(this.leftDevLongitude);
        parcel.writeLong(this.leftDevUpdateTime);
        parcel.writeDouble(this.rightDevLatitude);
        parcel.writeDouble(this.rightDevLongitude);
        parcel.writeLong(this.rightDevUpdateTime);
        parcel.writeLong(this.onlineTime);
        parcel.writeString(this.updateAddress);
    }

    public HistoryRecord(Parcel parcel) {
        this.address = "11:22:33:44:55:66";
        this.id = parcel.readInt();
        this.name = parcel.readString();
        String readString = parcel.readString();
        Objects.requireNonNull(readString);
        this.address = readString;
        this.mappedAddress = parcel.readString();
        this.devType = parcel.readInt();
        this.connectType = parcel.readInt();
        this.sdkFlag = parcel.readInt();
        this.vid = parcel.readInt();
        this.uid = parcel.readInt();
        this.pid = parcel.readInt();
        this.leftDevLatitude = parcel.readDouble();
        this.leftDevLongitude = parcel.readDouble();
        this.leftDevUpdateTime = parcel.readLong();
        this.rightDevLatitude = parcel.readDouble();
        this.rightDevLongitude = parcel.readDouble();
        this.rightDevUpdateTime = parcel.readLong();
        this.onlineTime = parcel.readLong();
        this.updateAddress = parcel.readString();
    }
}
