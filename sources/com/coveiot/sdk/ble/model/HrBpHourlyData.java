package com.coveiot.sdk.ble.model;

import com.coveiot.sdk.ble.api.model.BpHrMinData;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class HrBpHourlyData extends HourlyData {
    public int aveargeHr;
    public int avgRrValue;
    public ArrayList<BpHrMinData> bpHrMinData = new ArrayList<>();
    public int diastolicBp;
    public int maxHr;
    public int minHr;
    public int systolicBp;

    public int getAveargeHr() {
        this.aveargeHr = 0;
        if (this.bpHrMinData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.bpHrMinData.size(); i2++) {
                this.aveargeHr += this.bpHrMinData.get(i2).mHr;
                if (this.bpHrMinData.get(i2).mHr != 0) {
                    i++;
                }
            }
            if (i > 0) {
                this.aveargeHr /= i;
            } else {
                this.aveargeHr = 0;
            }
        }
        return this.aveargeHr;
    }

    public ArrayList<BpHrMinData> getBpHrMinData() {
        return this.bpHrMinData;
    }

    public int getDiastolicBp() {
        this.diastolicBp = 0;
        if (this.bpHrMinData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.bpHrMinData.size(); i2++) {
                this.diastolicBp += this.bpHrMinData.get(i2).mDbp;
                if (this.bpHrMinData.get(i2).mDbp != 0) {
                    i++;
                }
            }
            if (i > 0) {
                this.diastolicBp /= i;
            } else {
                this.diastolicBp = 0;
            }
        }
        return this.diastolicBp;
    }

    public int getMaxHr() {
        if (this.bpHrMinData.size() > 0) {
            this.maxHr = this.bpHrMinData.get(0).mHr;
            for (int i = 0; i < this.bpHrMinData.size(); i++) {
                if (this.maxHr < this.bpHrMinData.get(i).mHr) {
                    this.maxHr = this.bpHrMinData.get(i).mHr;
                }
            }
        }
        return this.maxHr;
    }

    public int getMinHr() {
        if (this.bpHrMinData.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.bpHrMinData.size()) {
                    break;
                } else if (this.bpHrMinData.get(i).mHr > 0) {
                    this.minHr = this.bpHrMinData.get(i).mHr;
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < this.bpHrMinData.size(); i2++) {
                if (this.minHr > this.bpHrMinData.get(i2).mHr && this.bpHrMinData.get(i2).mHr != 0) {
                    this.minHr = this.bpHrMinData.get(i2).mHr;
                }
            }
        }
        return this.minHr;
    }

    public int getRrValue() {
        this.avgRrValue = 0;
        if (this.bpHrMinData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.bpHrMinData.size(); i2++) {
                this.avgRrValue += this.bpHrMinData.get(i2).mRr;
                if (this.bpHrMinData.get(i2).mRr != 0) {
                    i++;
                }
            }
            if (i > 0) {
                this.avgRrValue /= i;
            } else {
                this.avgRrValue = 0;
            }
        }
        return this.avgRrValue;
    }

    public int getSystolicBp() {
        this.systolicBp = 0;
        if (this.bpHrMinData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.bpHrMinData.size(); i2++) {
                this.systolicBp += this.bpHrMinData.get(i2).mSbp;
                if (this.bpHrMinData.get(i2).mSbp != 0) {
                    i++;
                }
            }
            if (i > 0) {
                this.systolicBp /= i;
            } else {
                this.systolicBp = 0;
            }
        }
        return this.systolicBp;
    }

    public void setBpHrMinData(ArrayList<BpHrMinData> arrayList) {
        this.bpHrMinData = arrayList;
    }
}
