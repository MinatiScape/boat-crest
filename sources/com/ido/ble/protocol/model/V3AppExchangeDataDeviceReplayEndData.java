package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes11.dex */
public class V3AppExchangeDataDeviceReplayEndData implements Serializable {
    private static final long serialVersionUID = 1;
    public int action_data_count;
    public int aerobic_exercise;
    public int aerobic_exercise_time;
    public int aerobic_mins;
    public int anaerobic_exercise;
    public int anaerobic_exercise_time;
    public int avg_hr_value;
    public int avg_speed;
    public int avg_step_frequency;
    public int avg_step_stride;
    public int burn_fat_mins;
    public int calories;
    public int completion_rate;
    public int day;
    public int distance;
    public int durations;
    public int extreme_exercise;
    public int extreme_exercise_time;
    public int fast_km_speed;
    public int fat_burning;
    public int fat_burning_time;
    public int grade;
    public int hour;
    public int hr_completion_rate;
    public int hr_data_count;
    public int hr_data_interval_minute;
    public int in_class_calories;
    public List<Integer> item_real_speed;
    public List<ActionData> items;
    public List<Integer> items_mi_speed;
    public int km_speed;
    public int km_speed_count;
    public List<Integer> km_speed_s;
    public int limit_mins;
    public int max_hr_value;
    public int max_speed;
    public int max_step_frequency;
    public int max_step_stride;
    public int minute;
    public int month;
    public List<Integer> pace_speed_items;
    public int recover_time;
    public int second;
    public int step;
    public List<Integer> steps_frequency;
    public int steps_frequency_count;
    public int training_effect;
    public int training_offset;
    public int type;
    public int vo2max;
    public int warm_up;
    public int warm_up_time;
    public int year;

    /* loaded from: classes11.dex */
    public static class ActionData {
        public int goal_time;
        public int heart_con_value;
        public int time;
        public int type;

        public String toString() {
            return "ActionData{type=" + this.type + ", heart_con_value=" + this.heart_con_value + ", time=" + this.time + ", goal_time=" + this.goal_time + '}';
        }
    }

    public String toString() {
        return "V3AppExchangeDataDeviceReplayEndData{type=" + this.type + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", hr_data_interval_minute=" + this.hr_data_interval_minute + ", step=" + this.step + ", durations=" + this.durations + ", calories=" + this.calories + ", distance=" + this.distance + ", burn_fat_mins=" + this.burn_fat_mins + ", aerobic_mins=" + this.aerobic_mins + ", limit_mins=" + this.limit_mins + ", warm_up=" + this.warm_up + ", fat_burning=" + this.fat_burning + ", aerobic_exercise=" + this.aerobic_exercise + ", anaerobic_exercise=" + this.anaerobic_exercise + ", extreme_exercise=" + this.extreme_exercise + ", warm_up_time=" + this.warm_up_time + ", fat_burning_time=" + this.fat_burning_time + ", aerobic_exercise_time=" + this.aerobic_exercise_time + ", anaerobic_exercise_time=" + this.anaerobic_exercise_time + ", extreme_exercise_time=" + this.extreme_exercise_time + ", avg_speed=" + this.avg_speed + ", max_speed=" + this.max_speed + ", avg_step_stride=" + this.avg_step_stride + ", max_step_stride=" + this.max_step_stride + ", km_speed=" + this.km_speed + ", fast_km_speed=" + this.fast_km_speed + ", avg_step_frequency=" + this.avg_step_frequency + ", max_step_frequency=" + this.max_step_frequency + ", avg_hr_value=" + this.avg_hr_value + ", max_hr_value=" + this.max_hr_value + ", km_speed_count=" + this.km_speed_count + ", steps_frequency_count=" + this.steps_frequency_count + ", hr_data_count=" + this.hr_data_count + ", km_speed_s=" + this.km_speed_s + ", steps_frequency=" + this.steps_frequency + ", pace_speed_items=" + this.pace_speed_items + ", items_mi_speed=" + this.items_mi_speed + ", item_real_speed=" + this.item_real_speed + ", recover_time=" + this.recover_time + ", vo2max=" + this.vo2max + ", training_effect=" + this.training_effect + ", action_data_count=" + this.action_data_count + ", in_class_calories=" + this.in_class_calories + ", items=" + this.items + ", completion_rate=" + this.completion_rate + ", hr_completion_rate=" + this.hr_completion_rate + ", grade=" + this.grade + ", training_offset=" + this.training_offset + '}';
    }
}
