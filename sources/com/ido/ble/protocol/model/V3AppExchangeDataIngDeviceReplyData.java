package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class V3AppExchangeDataIngDeviceReplyData implements Serializable {
    private static final long serialVersionUID = 1;
    public int count_hour;
    public int count_minute;
    public int count_second;
    public int distance;
    public int duration;
    public int heart_rate;
    public int hour;
    public int km_speed;
    public int minute;
    public int real_time_calories;
    public int real_time_speed;
    public int real_time_speed_pace;
    public int second;
    public int status;
    public int steps;
    public int swim_posture;
    public int te;
    public int tean;
    public int type;
    public int version;

    public String toString() {
        return "V3AppExchangeDataIngDeviceReplyData{type=" + this.type + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", heart_rate=" + this.heart_rate + ", distance=" + this.distance + ", real_time_speed=" + this.real_time_speed + ", real_time_speed_pace=" + this.real_time_speed_pace + ", km_speed=" + this.km_speed + ", real_time_calories=" + this.real_time_calories + ", steps=" + this.steps + ", swim_posture=" + this.swim_posture + ", status=" + this.status + ", duration=" + this.duration + ", te=" + this.te + ", tean=" + this.tean + ", count_hour=" + this.count_hour + ", count_minute=" + this.count_minute + ", count_second=" + this.count_second + '}';
    }
}
