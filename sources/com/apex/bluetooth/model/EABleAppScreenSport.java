package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.EABleSportStatus;
/* loaded from: classes.dex */
public class EABleAppScreenSport {
    public EABleAppSportType eaBleAppSportType;
    public EABleSportStatus eaBleSportStatus;
    public int interval;

    /* loaded from: classes.dex */
    public enum EABleAppSportType {
        ourdoor_null(0),
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
        long_jump(126);
        
        public int value;

        EABleAppSportType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public EABleAppSportType getEaBleAppSportType() {
        return this.eaBleAppSportType;
    }

    public EABleSportStatus getEaBleSportStatus() {
        return this.eaBleSportStatus;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setEaBleAppSportType(EABleAppSportType eABleAppSportType) {
        this.eaBleAppSportType = eABleAppSportType;
    }

    public void setEaBleSportStatus(EABleSportStatus eABleSportStatus) {
        this.eaBleSportStatus = eABleSportStatus;
    }

    public void setInterval(int i) {
        this.interval = i;
    }
}
