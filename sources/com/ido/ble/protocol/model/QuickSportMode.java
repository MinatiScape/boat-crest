package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class QuickSportMode implements Serializable {
    private static final long serialVersionUID = 1;
    public boolean HIIT;
    public boolean elliptical;
    private final int flag = 2;
    public boolean indoor_cycle;
    public boolean indoor_run;
    public boolean indoor_walk;
    public boolean open_water_swim;
    public boolean outdoor_cycle;
    public boolean outdoor_run;
    public boolean outdoor_walk;
    public boolean pool_swim;
    public boolean rower;
    public boolean sport_type0_badminton;
    public boolean sport_type0_by_bike;
    public boolean sport_type0_mountain_climbing;
    public boolean sport_type0_on_foot;
    public boolean sport_type0_other;
    public boolean sport_type0_run;
    public boolean sport_type0_swim;
    public boolean sport_type0_walk;
    public boolean sport_type1_dumbbell;
    public boolean sport_type1_ellipsoid;
    public boolean sport_type1_fitness;
    public boolean sport_type1_push_up;
    public boolean sport_type1_sit_up;
    public boolean sport_type1_spinning;
    public boolean sport_type1_treadmill;
    public boolean sport_type1_weightlifting;
    public boolean sport_type2_basketball;
    public boolean sport_type2_bodybuilding_exercise;
    public boolean sport_type2_footballl;
    public boolean sport_type2_rope_skipping;
    public boolean sport_type2_table_tennis;
    public boolean sport_type2_tennis;
    public boolean sport_type2_volleyball;
    public boolean sport_type2_yoga;
    public boolean sport_type3_baseball;
    public boolean sport_type3_dance;
    public boolean sport_type3_golf;
    public boolean sport_type3_roller_skating;
    public boolean sport_type3_skiing;

    public String toString() {
        return "QuickSportMode{flag=2, sport_type0_walk=" + this.sport_type0_walk + ", sport_type0_run=" + this.sport_type0_run + ", sport_type0_by_bike=" + this.sport_type0_by_bike + ", sport_type0_on_foot=" + this.sport_type0_on_foot + ", sport_type0_swim=" + this.sport_type0_swim + ", sport_type0_mountain_climbing=" + this.sport_type0_mountain_climbing + ", sport_type0_badminton=" + this.sport_type0_badminton + ", sport_type0_other=" + this.sport_type0_other + ", sport_type1_fitness=" + this.sport_type1_fitness + ", sport_type1_spinning=" + this.sport_type1_spinning + ", sport_type1_ellipsoid=" + this.sport_type1_ellipsoid + ", sport_type1_treadmill=" + this.sport_type1_treadmill + ", sport_type1_sit_up=" + this.sport_type1_sit_up + ", sport_type1_push_up=" + this.sport_type1_push_up + ", sport_type1_dumbbell=" + this.sport_type1_dumbbell + ", sport_type1_weightlifting=" + this.sport_type1_weightlifting + ", sport_type2_bodybuilding_exercise=" + this.sport_type2_bodybuilding_exercise + ", sport_type2_yoga=" + this.sport_type2_yoga + ", sport_type2_rope_skipping=" + this.sport_type2_rope_skipping + ", sport_type2_table_tennis=" + this.sport_type2_table_tennis + ", sport_type2_basketball=" + this.sport_type2_basketball + ", sport_type2_footballl=" + this.sport_type2_footballl + ", sport_type2_volleyball=" + this.sport_type2_volleyball + ", sport_type2_tennis=" + this.sport_type2_tennis + ", sport_type3_golf=" + this.sport_type3_golf + ", sport_type3_baseball=" + this.sport_type3_baseball + ", sport_type3_skiing=" + this.sport_type3_skiing + ", sport_type3_roller_skating=" + this.sport_type3_roller_skating + ", sport_type3_dance=" + this.sport_type3_dance + ", outdoor_run=" + this.outdoor_run + ", indoor_run=" + this.indoor_run + ", outdoor_cycle=" + this.outdoor_cycle + ", indoor_cycle=" + this.indoor_cycle + ", outdoor_walk=" + this.outdoor_walk + ", indoor_walk=" + this.indoor_walk + ", pool_swim=" + this.pool_swim + ", open_water_swim=" + this.open_water_swim + ", elliptical=" + this.elliptical + ", rower=" + this.rower + ", HIIT=" + this.HIIT + '}';
    }
}
