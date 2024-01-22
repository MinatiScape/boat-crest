package com.coveiot.sdk.ble.model;

import java.util.ArrayList;
/* loaded from: classes9.dex */
public class TemperatureHourlyData extends HourlyData {
    public double avgTemperaturePerHour;
    public double maxTemperaturePerHour;
    public double minTemperaturePerHour;
    public ArrayList<Double> temperatureMinuteData = new ArrayList<>();

    public double getAvgTemperaturePerHour() {
        this.avgTemperaturePerHour = 0.0d;
        if (this.temperatureMinuteData.size() > 0) {
            int i = 0;
            for (int i2 = 0; i2 < this.temperatureMinuteData.size(); i2++) {
                this.avgTemperaturePerHour += this.temperatureMinuteData.get(i2).doubleValue();
                if (this.temperatureMinuteData.get(i2).doubleValue() != 0.0d) {
                    i++;
                }
            }
            if (i > 0) {
                this.avgTemperaturePerHour /= i;
            } else {
                this.avgTemperaturePerHour = 0.0d;
            }
        }
        return this.avgTemperaturePerHour;
    }

    public double getMaxTemperaturePerHour() {
        if (this.temperatureMinuteData.size() > 0) {
            this.maxTemperaturePerHour = this.temperatureMinuteData.get(0).doubleValue();
            for (int i = 0; i < this.temperatureMinuteData.size(); i++) {
                if (this.maxTemperaturePerHour < this.temperatureMinuteData.get(i).doubleValue()) {
                    this.maxTemperaturePerHour = this.temperatureMinuteData.get(i).doubleValue();
                }
            }
        }
        return this.maxTemperaturePerHour;
    }

    public double getMinTemperaturePerHour() {
        if (this.temperatureMinuteData.size() > 0) {
            int i = 0;
            while (true) {
                if (i >= this.temperatureMinuteData.size()) {
                    break;
                } else if (this.temperatureMinuteData.get(i).doubleValue() > 0.0d) {
                    this.minTemperaturePerHour = this.temperatureMinuteData.get(i).doubleValue();
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < this.temperatureMinuteData.size(); i2++) {
                if (this.minTemperaturePerHour > this.temperatureMinuteData.get(i2).doubleValue() && this.temperatureMinuteData.get(i2).doubleValue() != 0.0d) {
                    this.minTemperaturePerHour = this.temperatureMinuteData.get(i2).doubleValue();
                }
            }
        }
        return this.minTemperaturePerHour;
    }

    public ArrayList<Double> getTemperatureMinuteData() {
        return this.temperatureMinuteData;
    }

    public void setAvgTemperaturePerHour(double d) {
        this.avgTemperaturePerHour = d;
    }

    public void setMaxTemperaturePerHour(double d) {
        this.maxTemperaturePerHour = d;
    }

    public void setMinTemperaturePerHour(double d) {
        this.minTemperaturePerHour = d;
    }

    public void setTemperatureMinuteData(ArrayList<Double> arrayList) {
        this.temperatureMinuteData = arrayList;
    }
}
