package com.coveiot.coveaccess.activitysession;

import androidx.core.app.NotificationCompat;
import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class ActivitySessionDataResponse extends CoveApiRequestBaseModel {
    @SerializedName("activityCode")
    @Expose
    private String activityCode;
    @SerializedName(FitnessActivities.BADMINTON)
    @Expose
    private BadmintonActivityDetails badmintonDetails;
    @SerializedName("baseUnits")
    @Expose
    private BaseUnits baseUnits;
    @SerializedName(FitnessActivities.BASEBALL)
    @Expose
    private BaseballActivityDetails baseballActivityDetails;
    @SerializedName(FitnessActivities.BASKETBALL)
    @Expose
    private BasketBallActivityDetails basketballDetails;
    @SerializedName("biking")
    @Expose
    private BikingActivityDetails bikingDetails;
    @SerializedName(FitnessActivities.BOXING)
    @Expose
    private BoxingActivityDetails boxingActivityDetails;
    @SerializedName("clientRefId")
    @Expose
    private String clientRefId;
    @SerializedName("climbStairs")
    @Expose
    private ClimbStairsActivityDetails climbStairsActivityDetails;
    @SerializedName("cycle")
    @Expose
    private CycleActivityDetails cycleDetails;
    @SerializedName("dance")
    @Expose
    private DanceActivityDetails danceDetails;
    @SerializedName(FitnessActivities.ELLIPTICAL)
    @Expose
    private EllipticalActivityDetails ellipticalDetails;
    @SerializedName("fitnessSessionId")
    @Expose
    private String fitnessSessionId;
    @SerializedName("football")
    @Expose
    private FootBallActivityDetails footballDetails;
    @SerializedName("freeExercise")
    @Expose
    private FreeExerciseActivityDetails freeExerciseActivityDetails;
    @SerializedName(FitnessActivities.GOLF)
    @Expose
    private GolfActivityDetails golfActivityDetails;
    @SerializedName(FitnessActivities.HIKING)
    @Expose
    private HikingActivityDetails hikingDetails;
    @SerializedName(FitnessActivities.HOCKEY)
    @Expose
    private HockeyActivityDetails hockeyActivityDetails;
    @SerializedName("hulaHoop")
    @Expose
    private HulaHoopActivityDetails hulaHoopActivityDetails;
    @SerializedName(FitnessActivities.MEDITATION)
    @Expose
    private MeditationActivityDetails meditationDetails;
    @SerializedName("physicalActivity")
    @Expose
    private PhysicalActivityDetails physicalActivityDetails;
    @SerializedName(FitnessActivities.PILATES)
    @Expose
    private PilatesActivityDetails pilatesActivityDetails;
    @SerializedName("pingPong")
    @Expose
    private PingPongActivityDetails pingPongActivityDetails;
    @SerializedName(FitnessActivities.ROWING)
    @Expose
    private RowingMachineActivityDetails rowingDetails;
    @SerializedName("run")
    @Expose
    private RunWalkActivityDetails runDetails;
    @SerializedName("sessionEndDate")
    @Expose
    private String sessionEndDate;
    @SerializedName("sessionStartDate")
    @Expose
    private String sessionStartDate;
    @SerializedName("sessionType")
    @Expose
    private String sessionType;
    @SerializedName("ski")
    @Expose
    private SkiActivityDetails skiActivityDetails;
    @SerializedName("skipping")
    @Expose
    private SkippingActivityDetails skippingDetails;
    @SerializedName("swim")
    @Expose
    private SwimActivityDetails swimDetails;
    @SerializedName(FitnessActivities.TENNIS)
    @Expose
    private TennisActivityDetails tennisDetails;
    @SerializedName("totalActivityDuration")
    @Expose
    private Integer totalActivityDuration;
    @SerializedName("totalSampleCount")
    @Expose
    private Integer totalSampleCount;
    @SerializedName("userDeviceId")
    @Expose
    private Integer userDeviceId;
    @SerializedName("walk")
    @Expose
    private RunWalkActivityDetails walkDetails;
    @SerializedName(NotificationCompat.CATEGORY_WORKOUT)
    @Expose
    private WorkoutActivityDetails workoutDetails;
    @SerializedName(FitnessActivities.YOGA)
    @Expose
    private YogaActivityDetails yogaDetails;

    public String getActivityCode() {
        return this.activityCode;
    }

    public BadmintonActivityDetails getBadmintonDetails() {
        return this.badmintonDetails;
    }

    public BaseUnits getBaseUnits() {
        return this.baseUnits;
    }

    public BaseballActivityDetails getBaseballActivityDetails() {
        return this.baseballActivityDetails;
    }

    public BasketBallActivityDetails getBasketballDetails() {
        return this.basketballDetails;
    }

    public BikingActivityDetails getBikingDetails() {
        return this.bikingDetails;
    }

    public BoxingActivityDetails getBoxingActivityDetails() {
        return this.boxingActivityDetails;
    }

    public String getClientRefId() {
        return this.clientRefId;
    }

    public ClimbStairsActivityDetails getClimbStairsActivityDetails() {
        return this.climbStairsActivityDetails;
    }

    public CycleActivityDetails getCycleDetails() {
        return this.cycleDetails;
    }

    public DanceActivityDetails getDanceDetails() {
        return this.danceDetails;
    }

    public EllipticalActivityDetails getEllipticalDetails() {
        return this.ellipticalDetails;
    }

    public String getFitnessSessionId() {
        return this.fitnessSessionId;
    }

    public FootBallActivityDetails getFootballDetails() {
        return this.footballDetails;
    }

    public FreeExerciseActivityDetails getFreeExerciseActivityDetails() {
        return this.freeExerciseActivityDetails;
    }

    public GolfActivityDetails getGolfActivityDetails() {
        return this.golfActivityDetails;
    }

    public HikingActivityDetails getHikingDetails() {
        return this.hikingDetails;
    }

    public HockeyActivityDetails getHockeyActivityDetails() {
        return this.hockeyActivityDetails;
    }

    public HulaHoopActivityDetails getHulaHoopActivityDetails() {
        return this.hulaHoopActivityDetails;
    }

    public MeditationActivityDetails getMeditationDetails() {
        return this.meditationDetails;
    }

    public PhysicalActivityDetails getPhysicalActivityDetails() {
        return this.physicalActivityDetails;
    }

    public PilatesActivityDetails getPilatesActivityDetails() {
        return this.pilatesActivityDetails;
    }

    public PingPongActivityDetails getPingPongActivityDetails() {
        return this.pingPongActivityDetails;
    }

    public RowingMachineActivityDetails getRowingDetails() {
        return this.rowingDetails;
    }

    public RunWalkActivityDetails getRunDetails() {
        return this.runDetails;
    }

    public String getSessionEndDate() {
        return this.sessionEndDate;
    }

    public String getSessionStartDate() {
        return this.sessionStartDate;
    }

    public String getSessionType() {
        return this.sessionType;
    }

    public SkiActivityDetails getSkiActivityDetails() {
        return this.skiActivityDetails;
    }

    public SkippingActivityDetails getSkippingDetails() {
        return this.skippingDetails;
    }

    public SwimActivityDetails getSwimDetails() {
        return this.swimDetails;
    }

    public TennisActivityDetails getTennisDetails() {
        return this.tennisDetails;
    }

    public int getTotalActivityDuration() {
        return this.totalActivityDuration.intValue();
    }

    public Integer getTotalSampleCount() {
        return this.totalSampleCount;
    }

    public RunWalkActivityDetails getWalkDetails() {
        return this.walkDetails;
    }

    public WorkoutActivityDetails getWorkoutDetails() {
        return this.workoutDetails;
    }

    public YogaActivityDetails getYogaDetails() {
        return this.yogaDetails;
    }

    public void setActivityCode(String str) {
        this.activityCode = str;
    }

    public void setBadmintonDetails(BadmintonActivityDetails badmintonActivityDetails) {
        this.badmintonDetails = badmintonActivityDetails;
    }

    public void setBaseUnits(BaseUnits baseUnits) {
        this.baseUnits = baseUnits;
    }

    public void setBaseballActivityDetails(BaseballActivityDetails baseballActivityDetails) {
        this.baseballActivityDetails = baseballActivityDetails;
    }

    public void setBasketballDetails(BasketBallActivityDetails basketBallActivityDetails) {
        this.basketballDetails = basketBallActivityDetails;
    }

    public void setBikingDetails(BikingActivityDetails bikingActivityDetails) {
        this.bikingDetails = bikingActivityDetails;
    }

    public void setBoxingActivityDetails(BoxingActivityDetails boxingActivityDetails) {
        this.boxingActivityDetails = boxingActivityDetails;
    }

    public void setClientRefId(String str) {
        this.clientRefId = str;
    }

    public void setClimbStairsActivityDetails(ClimbStairsActivityDetails climbStairsActivityDetails) {
        this.climbStairsActivityDetails = climbStairsActivityDetails;
    }

    public void setCycleDetails(CycleActivityDetails cycleActivityDetails) {
        this.cycleDetails = cycleActivityDetails;
    }

    public void setDanceDetails(DanceActivityDetails danceActivityDetails) {
        this.danceDetails = danceActivityDetails;
    }

    public void setEllipticalDetails(EllipticalActivityDetails ellipticalActivityDetails) {
        this.ellipticalDetails = ellipticalActivityDetails;
    }

    public void setFitnessSessionId(String str) {
        this.fitnessSessionId = str;
    }

    public void setFootballDetails(FootBallActivityDetails footBallActivityDetails) {
        this.footballDetails = footBallActivityDetails;
    }

    public void setFreeExerciseActivityDetails(FreeExerciseActivityDetails freeExerciseActivityDetails) {
        this.freeExerciseActivityDetails = freeExerciseActivityDetails;
    }

    public void setGolfActivityDetails(GolfActivityDetails golfActivityDetails) {
        this.golfActivityDetails = golfActivityDetails;
    }

    public void setHikingDetails(HikingActivityDetails hikingActivityDetails) {
        this.hikingDetails = hikingActivityDetails;
    }

    public void setHockeyActivityDetails(HockeyActivityDetails hockeyActivityDetails) {
        this.hockeyActivityDetails = hockeyActivityDetails;
    }

    public void setHulaHoopActivityDetails(HulaHoopActivityDetails hulaHoopActivityDetails) {
        this.hulaHoopActivityDetails = hulaHoopActivityDetails;
    }

    public void setMeditationDetails(MeditationActivityDetails meditationActivityDetails) {
        this.meditationDetails = meditationActivityDetails;
    }

    public void setPhysicalActivityDetails(PhysicalActivityDetails physicalActivityDetails) {
        this.physicalActivityDetails = physicalActivityDetails;
    }

    public void setPilatesActivityDetails(PilatesActivityDetails pilatesActivityDetails) {
        this.pilatesActivityDetails = pilatesActivityDetails;
    }

    public void setPingPongActivityDetails(PingPongActivityDetails pingPongActivityDetails) {
        this.pingPongActivityDetails = pingPongActivityDetails;
    }

    public void setRowingDetails(RowingMachineActivityDetails rowingMachineActivityDetails) {
        this.rowingDetails = rowingMachineActivityDetails;
    }

    public void setRunDetails(RunWalkActivityDetails runWalkActivityDetails) {
        this.runDetails = runWalkActivityDetails;
    }

    public void setSessionEndDate(String str) {
        this.sessionEndDate = str;
    }

    public void setSessionStartDate(String str) {
        this.sessionStartDate = str;
    }

    public void setSessionType(String str) {
        this.sessionType = str;
    }

    public void setSkiActivityDetails(SkiActivityDetails skiActivityDetails) {
        this.skiActivityDetails = skiActivityDetails;
    }

    public void setSkippingDetails(SkippingActivityDetails skippingActivityDetails) {
        this.skippingDetails = skippingActivityDetails;
    }

    public void setSwimDetails(SwimActivityDetails swimActivityDetails) {
        this.swimDetails = swimActivityDetails;
    }

    public void setTennisDetails(TennisActivityDetails tennisActivityDetails) {
        this.tennisDetails = tennisActivityDetails;
    }

    public void setTotalActivityDuration(int i) {
        this.totalActivityDuration = Integer.valueOf(i);
    }

    public void setTotalSampleCount(Integer num) {
        this.totalSampleCount = num;
    }

    public void setWalkDetails(RunWalkActivityDetails runWalkActivityDetails) {
        this.walkDetails = runWalkActivityDetails;
    }

    public void setWorkoutDetails(WorkoutActivityDetails workoutActivityDetails) {
        this.workoutDetails = workoutActivityDetails;
    }

    public void setYogaDetails(YogaActivityDetails yogaActivityDetails) {
        this.yogaDetails = yogaActivityDetails;
    }

    public void setTotalSampleCount(int i) {
        this.totalSampleCount = Integer.valueOf(i);
    }
}
