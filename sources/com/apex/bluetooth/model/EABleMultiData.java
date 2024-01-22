package com.apex.bluetooth.model;

import androidx.exifinterface.media.ExifInterface;
/* loaded from: classes.dex */
public class EABleMultiData implements Comparable<EABleMultiData> {
    public int average_altitude;
    public int average_heart_rate;
    public int average_heart_rate_max;
    public int average_heart_rate_min;
    public int average_pace;
    public int average_speed;
    public int average_step_freq;
    public int average_stride;
    public float average_temperature;
    public long begin_time_stamp;
    public int calorie;
    public int distance;
    public int duration;
    public MotionType e_type;
    public long end_time_stamp;
    public int steps;
    public int training_effect_aerobic;
    public int training_effect_anaerobic;
    public int training_effect_fatconsumption;
    public int training_effect_limit;
    public int training_effect_normal;
    public int training_effect_warmUp;

    /* loaded from: classes.dex */
    public enum MotionType {
        daily(0),
        ourdoor_walking(1),
        ourdoor_running(2),
        ourdoor_on_foot(3),
        ourdoor_on_mountaineering(4),
        ourdoor_trail_running(5),
        ourdoor_cycling(6),
        outdoor_swimming(7),
        indoor_walking(8),
        indoor_running(9),
        indoor_exercise(10),
        indoor_cycling(11),
        elliptical(12),
        yoga(13),
        rowing(14),
        indoor_swimming(15),
        od_rock(16),
        od_skate(17),
        od_roller(18),
        od_parkour(19),
        od_parachute(20),
        train_HIT(21),
        train_weight(22),
        train_plank(23),
        train_jumping(24),
        train_stair(25),
        train_core(26),
        train_flex(27),
        train_pilates(28),
        train_stretch(29),
        train_strength(30),
        train_cross(31),
        train_dumbbell(32),
        train_deadlift(33),
        train_sit(34),
        train_funcition(35),
        train_upper(36),
        train_lower(37),
        train_abs(38),
        train_back(39),
        water_sailboat(40),
        water_sup(41),
        water_polo(42),
        water_thrash(43),
        water_kayak(44),
        water_drifting(45),
        water_boating(46),
        water_fin(47),
        water_diving(48),
        water_artistic(49),
        water_snorkel(50),
        water_kitesurfing(51),
        water_atv(52),
        water_beach(53),
        dance_dance(54),
        dance_delly(55),
        dance_gymnastics(56),
        dance_aerobics(57),
        dance_hip_hop(58),
        fight_boxing(59),
        fight_wushu(60),
        fight_wrestling(61),
        fight_taichi(62),
        fight_muay_thai(63),
        fight_judo(64),
        fight_taekwondo(65),
        fight_karate(66),
        fight_free_sparring(67),
        ball_soccer(68),
        ball_basketball(69),
        ball_volleyball(70),
        ball_badminton(71),
        ball_pingpong(72),
        ball_cricket(73),
        ball_football(74),
        ball_racqurball(75),
        ball_handball(76),
        ball_squash(77),
        ball_shuttlecock(78),
        ball_raga(79),
        snow_mobile(80),
        snow_skis(81),
        snow_puck(82),
        snow_skate(83),
        snow_curling(84),
        snow_board(85),
        snow_sled(86),
        leisure_meditation(87),
        leisure_kendo(88),
        leisure_fence(89),
        leisure_bowling(90),
        leisure_billiards(91),
        leisure_archery(92),
        leisure_darts(93),
        leisure_horse(94),
        leisure_hula(95),
        leisure_kite(96),
        leisure_fishing(97),
        leisure_fribee(98),
        leisure_equestrian(99),
        leisure_racing(100),
        other_free(101),
        other_rope(102),
        other_climb(103),
        other_push(104),
        other_horizontal(105),
        other_parallel(106),
        tennis(107),
        baseball(108),
        hockey(109),
        custom_sport(110),
        mark_time(111),
        walking_machine(112),
        athletics(113),
        lumbar_abdomen_training(114),
        latin_dance(115),
        ballet(116),
        golf(117),
        folk_dance(118),
        lacrosse(119),
        softball(120),
        peak_ball(121),
        trampoline(122),
        parkour(123),
        push_up(124),
        high_jump(125),
        long_jump(126),
        daily_ex(32768),
        ourdoor_walking_ex(32769),
        ourdoor_running_ex(32770),
        ourdoor_on_foot_ex(32771),
        ourdoor_on_mountaineering_ex(32772),
        ourdoor_trail_running_ex(ExifInterface.DATA_PACK_BITS_COMPRESSED),
        ourdoor_cycling_ex(32774),
        outdoor_swimming_ex(32775),
        indoor_walking_ex(32776),
        indoor_running_ex(32777),
        indoor_exercise_ex(32778),
        indoor_cycling_ex(32779),
        elliptical_ex(32780),
        yoga_ex(32781),
        rowing_ex(32782),
        indoor_swimming_ex(32783);
        
        public int value;

        MotionType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getAverage_altitude() {
        return this.average_altitude;
    }

    public int getAverage_heart_rate() {
        return this.average_heart_rate;
    }

    public int getAverage_heart_rate_max() {
        return this.average_heart_rate_max;
    }

    public int getAverage_heart_rate_min() {
        return this.average_heart_rate_min;
    }

    public int getAverage_pace() {
        return this.average_pace;
    }

    public int getAverage_speed() {
        return this.average_speed;
    }

    public int getAverage_step_freq() {
        return this.average_step_freq;
    }

    public int getAverage_stride() {
        return this.average_stride;
    }

    public float getAverage_temperature() {
        return this.average_temperature;
    }

    public long getBegin_time_stamp() {
        return this.begin_time_stamp;
    }

    public int getCalorie() {
        return this.calorie;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDuration() {
        return this.duration;
    }

    public MotionType getE_type() {
        return this.e_type;
    }

    public long getEnd_time_stamp() {
        return this.end_time_stamp;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getTraining_effect_aerobic() {
        return this.training_effect_aerobic;
    }

    public int getTraining_effect_anaerobic() {
        return this.training_effect_anaerobic;
    }

    public int getTraining_effect_fatconsumption() {
        return this.training_effect_fatconsumption;
    }

    public int getTraining_effect_limit() {
        return this.training_effect_limit;
    }

    public int getTraining_effect_normal() {
        return this.training_effect_normal;
    }

    public int getTraining_effect_warmUp() {
        return this.training_effect_warmUp;
    }

    public void setAverage_altitude(int i) {
        this.average_altitude = i;
    }

    public void setAverage_heart_rate(int i) {
        this.average_heart_rate = i;
    }

    public void setAverage_heart_rate_max(int i) {
        this.average_heart_rate_max = i;
    }

    public void setAverage_heart_rate_min(int i) {
        this.average_heart_rate_min = i;
    }

    public void setAverage_pace(int i) {
        this.average_pace = i;
    }

    public void setAverage_speed(int i) {
        this.average_speed = i;
    }

    public void setAverage_step_freq(int i) {
        this.average_step_freq = i;
    }

    public void setAverage_stride(int i) {
        this.average_stride = i;
    }

    public void setAverage_temperature(float f) {
        this.average_temperature = f;
    }

    public void setBegin_time_stamp(long j) {
        this.begin_time_stamp = j;
    }

    public void setCalorie(int i) {
        this.calorie = i;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setE_type(MotionType motionType) {
        this.e_type = motionType;
    }

    public void setEnd_time_stamp(long j) {
        this.end_time_stamp = j;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setTraining_effect_aerobic(int i) {
        this.training_effect_aerobic = i;
    }

    public void setTraining_effect_anaerobic(int i) {
        this.training_effect_anaerobic = i;
    }

    public void setTraining_effect_fatconsumption(int i) {
        this.training_effect_fatconsumption = i;
    }

    public void setTraining_effect_limit(int i) {
        this.training_effect_limit = i;
    }

    public void setTraining_effect_normal(int i) {
        this.training_effect_normal = i;
    }

    public void setTraining_effect_warmUp(int i) {
        this.training_effect_warmUp = i;
    }

    public String toString() {
        return "EABleMultiData{e_type=" + this.e_type + ", begin_time_stamp=" + this.begin_time_stamp + ", end_time_stamp=" + this.end_time_stamp + ", steps=" + this.steps + ", calorie=" + this.calorie + ", distance=" + this.distance + ", duration=" + this.duration + ", training_effect_normal=" + this.training_effect_normal + ", training_effect_warmUp=" + this.training_effect_warmUp + ", training_effect_fatconsumption=" + this.training_effect_fatconsumption + ", training_effect_aerobic=" + this.training_effect_aerobic + ", training_effect_anaerobic=" + this.training_effect_anaerobic + ", training_effect_limit=" + this.training_effect_limit + ", average_heart_rate=" + this.average_heart_rate + ", average_temperature=" + this.average_temperature + ", average_speed=" + this.average_speed + ", average_pace=" + this.average_pace + ", average_step_freq=" + this.average_step_freq + ", average_stride=" + this.average_stride + ", average_altitude=" + this.average_altitude + ", average_heart_rate_max=" + this.average_heart_rate_max + ", average_heart_rate_min=" + this.average_heart_rate_min + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleMultiData eABleMultiData) {
        if (eABleMultiData != null) {
            if (getBegin_time_stamp() > eABleMultiData.getBegin_time_stamp()) {
                return 1;
            }
            return getBegin_time_stamp() == eABleMultiData.getBegin_time_stamp() ? 0 : -1;
        }
        return 0;
    }
}
