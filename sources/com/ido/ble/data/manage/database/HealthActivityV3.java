package com.ido.ble.data.manage.database;

import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
public class HealthActivityV3 {
    public int act_count;
    public int act_type;
    public List<ActionTemp> action_temp;
    public int aerobic_exercise_time;
    public int aerobic_mins;
    public int anaerobicMins;
    public int anaerobic_exercise_time;
    public int avg_hr_value;
    public int avg_pace_speed;
    public int avg_speed;
    public int avg_step_frequency;
    public int avg_step_stride;
    public int burn_fat_mins;
    public int calories;
    public int completion_rate;
    public int connect_app;
    public int day;
    public int distance;
    public int durations;
    public int end_day;
    public int end_hour;
    public int end_minute;
    public int end_month;
    public int extreme_exercise_time;
    public int fast_km_speed;
    public int fast_pace_speed;
    public int fat_burning_time;
    public List<Integer> frequency_items;
    public int grade;
    public int hour;
    public int hr_completion_rate;
    public int hr_data_interval_minute;
    public int[] hr_data_vlaue;
    public int in_class_calories;
    public List<Integer> item_real_speed_temp;
    public List<Item> items;
    public List<ItemKMSpeed> items_km_speed;
    public List<Integer> items_mi_speed;
    public int km_speed;
    public int limit_mins;
    public int max_hr_value;
    public int max_speed;
    public int max_step_frequency;
    public int max_step_stride;
    public int min_hr_value;
    public int minute;
    public int month;
    public List<Integer> pace_speed_items;
    public int paddle_frequency_count;
    public List<Integer> paddle_frequency_items;
    public int paddle_number_count;
    public List<Integer> paddle_number_items;
    public int recovery_time_day;
    public int recovery_time_hour;
    public int recovery_time_min;
    public int recovery_time_mon;
    public int recovery_time_s;
    public int recovery_time_year;
    public int second;
    public int sport_start_type;
    public int step;
    public int training_effect;
    public int training_offset;
    public int tread_frequency_count;
    public List<Integer> tread_frequency_items;
    public int type;
    public int vO2max;
    public int warmUpMins;
    public int warm_up_time;
    public int year;

    /* loaded from: classes11.dex */
    public static class ActionTemp {
        public int actual_time;
        public int goal_time;
        public int heart_value;
        public int type;

        public String toString() {
            return "ActionTemp{type=" + this.type + ", actual_time=" + this.actual_time + ", goal_time=" + this.goal_time + ", heart_value=" + this.heart_value + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Item {
        public int calories;
        public int distance;
        public int steps;

        public String toString() {
            return "Item{steps=" + this.steps + ", calories=" + this.calories + ", distance=" + this.distance + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class ItemKMSpeed {
        public int second;

        public String toString() {
            return "ItemKMSpeed{second=" + this.second + '}';
        }
    }

    public String toString() {
        return "HealthActivityV3{year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", hr_data_interval_minute=" + this.hr_data_interval_minute + ", type=" + this.type + ", act_type=" + this.act_type + ", step=" + this.step + ", durations=" + this.durations + ", calories=" + this.calories + ", distance=" + this.distance + ", avg_hr_value=" + this.avg_hr_value + ", max_hr_value=" + this.max_hr_value + ", warmUpMins=" + this.warmUpMins + ", warm_up_time=" + this.warm_up_time + ", burn_fat_mins=" + this.burn_fat_mins + ", fat_burning_time=" + this.fat_burning_time + ", aerobic_mins=" + this.aerobic_mins + ", aerobic_exercise_time=" + this.aerobic_exercise_time + ", anaerobicMins=" + this.anaerobicMins + ", anaerobic_exercise_time=" + this.anaerobic_exercise_time + ", limit_mins=" + this.limit_mins + ", extreme_exercise_time=" + this.extreme_exercise_time + ", training_offset=" + this.training_offset + ", action_temp=" + this.action_temp + ", hr_data_vlaue=" + Arrays.toString(this.hr_data_vlaue) + ", items=" + this.items + ", grade=" + this.grade + ", fast_km_speed=" + this.fast_km_speed + ", items_km_speed=" + this.items_km_speed + ", frequency_items=" + this.frequency_items + ", items_mi_speed=" + this.items_mi_speed + ", km_speed=" + this.km_speed + ", avg_speed=" + this.avg_speed + ", max_speed=" + this.max_speed + ", avg_step_frequency=" + this.avg_step_frequency + ", max_step_frequency=" + this.max_step_frequency + ", avg_step_stride=" + this.avg_step_stride + ", max_step_stride=" + this.max_step_stride + ", sport_start_type=" + this.sport_start_type + ", connect_app=" + this.connect_app + ", avg_pace_speed=" + this.avg_pace_speed + ", fast_pace_speed=" + this.fast_pace_speed + ", training_effect=" + this.training_effect + ", vO2max=" + this.vO2max + ", recovery_time_year=" + this.recovery_time_year + ", recovery_time_mon=" + this.recovery_time_mon + ", recovery_time_day=" + this.recovery_time_day + ", recovery_time_hour=" + this.recovery_time_hour + ", recovery_time_min=" + this.recovery_time_min + ", recovery_time_s=" + this.recovery_time_s + ", min_hr_value=" + this.min_hr_value + ", pace_speed_items=" + this.pace_speed_items + ", paddle_number_items=" + this.paddle_number_items + ", paddle_frequency_items=" + this.paddle_frequency_items + ", tread_frequency_items=" + this.tread_frequency_items + ", item_real_speed_temp=" + this.item_real_speed_temp + ", paddle_number_count=" + this.paddle_number_count + ", paddle_frequency_count=" + this.paddle_frequency_count + ", tread_frequency_count=" + this.tread_frequency_count + ", end_month=" + this.end_month + ", end_day=" + this.end_day + ", end_hour=" + this.end_hour + ", end_minute=" + this.end_minute + ", act_count=" + this.act_count + ", completion_rate=" + this.completion_rate + ", hr_completion_rate=" + this.hr_completion_rate + ", in_class_calories=" + this.in_class_calories + '}';
    }
}
