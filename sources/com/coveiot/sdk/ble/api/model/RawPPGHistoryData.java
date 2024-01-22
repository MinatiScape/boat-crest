package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class RawPPGHistoryData {
    private String TAG = RawPPGHistoryData.class.getSimpleName();
    private int duration;
    private int interval;
    private int movementLevel;
    private ArrayList<Integer> ppgData;
    private int ppgType;
    private List<Byte> rawData;
    private int recordNumber;
    private int samplingRate;
    private long timeStamp;

    public RawPPGHistoryData(List<Byte> list) {
        this.rawData = list;
        parseData();
    }

    private void parseData() {
        this.recordNumber = this.rawData.get(0).byteValue() & 255;
        this.timeStamp = (((this.rawData.get(4).byteValue() & 255) << 24) + ((this.rawData.get(3).byteValue() & 255) << 16) + ((this.rawData.get(2).byteValue() & 255) << 8) + (this.rawData.get(1).byteValue() & 255)) * 1000;
        this.ppgType = this.rawData.get(5).byteValue() & 255;
        this.samplingRate = this.rawData.get(6).byteValue() & 255;
        this.interval = this.rawData.get(7).byteValue() & 255;
        this.duration = this.rawData.get(8).byteValue() & 255;
        int byteValue = ((this.rawData.get(12).byteValue() & 255) << 24) + ((this.rawData.get(11).byteValue() & 255) << 16) + ((this.rawData.get(10).byteValue() & 255) << 8) + (this.rawData.get(9).byteValue() & 255);
        int byteValue2 = this.rawData.get(13).byteValue() & 255;
        this.movementLevel = byteValue2;
        List<Integer> eachBit = BleUtils.getEachBit(byteValue2);
        LogHelper.d(this.TAG, "IsSigned->" + eachBit.get(3));
        if (byteValue % 2 != 0) {
            LogHelper.d(this.TAG, "Raw sample length must be multiple of 2");
        }
        if (this.rawData.size() < byteValue) {
            LogHelper.d(this.TAG, "Raw sample length not matching length in header");
        }
        List<Byte> list = this.rawData;
        List<Byte> subList = list.subList(14, list.size());
        this.ppgData = new ArrayList<>();
        for (int i = 0; i < subList.size(); i += 2) {
            this.ppgData.add(Integer.valueOf(eachBit.get(3).intValue() == 1 ? ByteBuffer.wrap(new byte[]{subList.get(i + 1).byteValue(), subList.get(i).byteValue()}).getShort() : (subList.get(i).byteValue() & 255) + ((subList.get(i + 1).byteValue() & 255) << 8)));
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getMovementLevel() {
        return this.movementLevel;
    }

    public ArrayList<Integer> getPpgData() {
        return this.ppgData;
    }

    public int getPpgType() {
        return this.ppgType;
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
