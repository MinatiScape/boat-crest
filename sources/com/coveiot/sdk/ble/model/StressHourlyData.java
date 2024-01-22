package com.coveiot.sdk.ble.model;

import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class StressHourlyData extends HourlyData {
    private double avgHRV;
    private double avgStress;
    private double maxHRV;
    private double maxStress;
    private double minHRV;
    private double minStress;
    private ArrayList<StressHRVHolder> stressHRVHolders;

    public StressHourlyData(ArrayList<StressHRVHolder> arrayList) {
        this.stressHRVHolders = new ArrayList<>();
        this.stressHRVHolders = arrayList;
        getMinStress();
        getMaxStress();
        getAvgStress();
        getMinHRV();
        getMaxHRV();
        getAvgHRV();
    }

    public double getAvgHRV() {
        this.avgHRV = 0.0d;
        if (this.stressHRVHolders.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.stressHRVHolders.size(); i2++) {
                if (this.stressHRVHolders.get(i2).getHrv() > 0.0d) {
                    this.avgHRV += this.stressHRVHolders.get(i2).getHrv();
                    i++;
                }
            }
            if (i > 0) {
                this.avgHRV /= i;
            } else {
                this.avgHRV = 0.0d;
            }
        }
        return this.avgHRV;
    }

    public double getAvgStress() {
        this.avgStress = 0.0d;
        if (this.stressHRVHolders.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.stressHRVHolders.size(); i2++) {
                if (this.stressHRVHolders.get(i2).getStress() > 0.0d) {
                    this.avgStress += this.stressHRVHolders.get(i2).getStress();
                    i++;
                }
            }
            if (i > 0) {
                this.avgStress /= i;
            } else {
                this.avgStress = 0.0d;
            }
        }
        return this.avgStress;
    }

    public double getMaxHRV() {
        if (this.stressHRVHolders.size() > 0) {
            this.maxHRV = this.stressHRVHolders.get(0).getHrv();
            for (int i = 0; i < this.stressHRVHolders.size(); i++) {
                if (this.maxHRV < this.stressHRVHolders.get(i).getHrv()) {
                    this.maxHRV = this.stressHRVHolders.get(i).getHrv();
                }
            }
        }
        return this.maxHRV;
    }

    public double getMaxStress() {
        if (this.stressHRVHolders.size() > 0) {
            this.maxStress = this.stressHRVHolders.get(0).getStress();
            for (int i = 0; i < this.stressHRVHolders.size(); i++) {
                if (this.maxStress < this.stressHRVHolders.get(i).getStress()) {
                    this.maxStress = this.stressHRVHolders.get(i).getStress();
                }
            }
        }
        return this.maxStress;
    }

    public double getMinHRV() {
        if (this.stressHRVHolders.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.stressHRVHolders.size()) {
                    break;
                } else if (this.stressHRVHolders.get(i).getHrv() > 0.0d) {
                    this.minHRV = this.stressHRVHolders.get(i).getHrv();
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < this.stressHRVHolders.size(); i2++) {
                if (this.minHRV > this.stressHRVHolders.get(i2).getHrv() && this.stressHRVHolders.get(i2).getHrv() > 0.0d) {
                    this.minHRV = this.stressHRVHolders.get(i2).getHrv();
                }
            }
        }
        return this.minHRV;
    }

    public double getMinStress() {
        if (this.stressHRVHolders.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.stressHRVHolders.size()) {
                    break;
                } else if (this.stressHRVHolders.get(i).getStress() > 0.0d) {
                    this.minStress = this.stressHRVHolders.get(i).getStress();
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < this.stressHRVHolders.size(); i2++) {
                if (this.minStress > this.stressHRVHolders.get(i2).getStress() && this.stressHRVHolders.get(i2).getStress() > 0.0d) {
                    this.minStress = this.stressHRVHolders.get(i2).getStress();
                }
            }
        }
        return this.minStress;
    }

    public ArrayList<Double> getMinuteWiseHRVData() {
        ArrayList<Double> arrayList = new ArrayList<>();
        if (!AppUtils.isEmpty(this.stressHRVHolders)) {
            Iterator<StressHRVHolder> it = this.stressHRVHolders.iterator();
            while (it.hasNext()) {
                arrayList.add(Double.valueOf(it.next().getHrv()));
            }
        }
        return arrayList;
    }

    public ArrayList<Integer> getMinuteWiseStressData() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (!AppUtils.isEmpty(this.stressHRVHolders)) {
            Iterator<StressHRVHolder> it = this.stressHRVHolders.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf((int) it.next().getStress()));
            }
        }
        return arrayList;
    }

    public ArrayList<StressHRVHolder> getStressHRVHolders() {
        return this.stressHRVHolders;
    }

    public void setStressHRVHolders(ArrayList<StressHRVHolder> arrayList) {
        this.stressHRVHolders = arrayList;
    }
}
