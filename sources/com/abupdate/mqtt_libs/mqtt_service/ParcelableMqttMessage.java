package com.abupdate.mqtt_libs.mqtt_service;

import android.os.Parcel;
import android.os.Parcelable;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
/* loaded from: classes.dex */
public class ParcelableMqttMessage extends MqttMessage implements Parcelable {
    public static final Parcelable.Creator<ParcelableMqttMessage> CREATOR = new a();
    public String n;

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<ParcelableMqttMessage> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ParcelableMqttMessage createFromParcel(Parcel parcel) {
            return new ParcelableMqttMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ParcelableMqttMessage[] newArray(int i) {
            return new ParcelableMqttMessage[i];
        }
    }

    public ParcelableMqttMessage(MqttMessage mqttMessage) {
        super(mqttMessage.getPayload());
        this.n = null;
        setQos(mqttMessage.getQos());
        setRetained(mqttMessage.isRetained());
        setDuplicate(mqttMessage.isDuplicate());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(getPayload());
        parcel.writeInt(getQos());
        parcel.writeBooleanArray(new boolean[]{isRetained(), isDuplicate()});
        parcel.writeString(this.n);
    }

    public ParcelableMqttMessage(Parcel parcel) {
        super(parcel.createByteArray());
        this.n = null;
        setQos(parcel.readInt());
        boolean[] createBooleanArray = parcel.createBooleanArray();
        setRetained(createBooleanArray[0]);
        setDuplicate(createBooleanArray[1]);
        this.n = parcel.readString();
    }
}
