package com.coveiot.sdk.ble.api.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class RawAccelerometerHistoryData {
    private ArrayList<BleAccelerometerData> bleAccelerometerData;
    private int duration;
    private int interval;
    private List<Byte> rawData;
    private int recordNumber;
    private int samplingRate;
    private long timeStamp;

    public RawAccelerometerHistoryData(List<Byte> list) {
        this.rawData = list;
        parseData();
    }

    private void parseData() {
        this.recordNumber = this.rawData.get(0).byteValue() & 255;
        this.timeStamp = (((this.rawData.get(4).byteValue() & 255) << 24) + ((this.rawData.get(3).byteValue() & 255) << 16) + ((this.rawData.get(2).byteValue() & 255) << 8) + (this.rawData.get(1).byteValue() & 255)) * 1000;
        this.samplingRate = this.rawData.get(5).byteValue() & 255;
        this.interval = this.rawData.get(6).byteValue() & 255;
        this.duration = this.rawData.get(7).byteValue() & 255;
        int byteValue = ((this.rawData.get(11).byteValue() & 255) << 24) + ((this.rawData.get(10).byteValue() & 255) << 16) + ((this.rawData.get(9).byteValue() & 255) << 8) + (this.rawData.get(8).byteValue() & 255);
        ArrayList arrayList = (ArrayList) this.rawData.subList(12, byteValue);
        this.bleAccelerometerData = new ArrayList<>();
        for (int i = 0; i < byteValue - 6; i += 6) {
            this.bleAccelerometerData.add(new BleAccelerometerData(((Byte) arrayList.get(i)).byteValue() | (((Byte) arrayList.get(i + 1)).byteValue() << 8), ((Byte) arrayList.get(i + 2)).byteValue() | (((Byte) arrayList.get(i + 3)).byteValue() << 8), ((Byte) arrayList.get(i + 4)).byteValue() | (((Byte) arrayList.get(i + 5)).byteValue() << 8)));
        }
    }

    public ArrayList<BleAccelerometerData> getAccelerometerData() {
        return this.bleAccelerometerData;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getRecordNumber() {
        return this.recordNumber;
    }

    public int getSamplingRate() {
        return this.samplingRate;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
