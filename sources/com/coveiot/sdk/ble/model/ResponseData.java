package com.coveiot.sdk.ble.model;

import com.coveiot.sdk.ble.events.ResponseType;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class ResponseData {
    private ArrayList<String> dataList;
    private int day;
    private int endTime;
    public int height;
    private String macAddress;
    private ResponseType responseType;
    public int runStrideLength;
    private int startTime;
    public int strideLength;
    public int timeInterval;
    public int weight;

    public ArrayList<String> getDataList() {
        return this.dataList;
    }

    public int getDay() {
        return this.day;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getHeight() {
        return this.height;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public ResponseType getResponseType() {
        return this.responseType;
    }

    public int getRunStrideLength() {
        return this.runStrideLength;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getStrideLength() {
        return this.strideLength;
    }

    public int getTimeInterval() {
        return this.timeInterval;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setDataList(ArrayList<String> arrayList) {
        this.dataList = arrayList;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public void setEndTime(int i) {
        this.endTime = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setRunStrideLength(int i) {
        this.runStrideLength = i;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public void setStrideLength(int i) {
        this.strideLength = i;
    }

    public void setTimeInterval(int i) {
        this.timeInterval = i;
    }

    public void setWeight(int i) {
        this.weight = i;
    }
}
