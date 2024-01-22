package com.coveiot.coveaccess.activitysession;

import androidx.core.app.NotificationCompat;
import com.coveiot.coveaccess.model.CoveApiRequestBaseModel;
import com.coveiot.coveaccess.model.server.GenericActivitySessionData;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import java.util.List;
/* loaded from: classes8.dex */
public class PostActivitySessionDataRequest extends CoveApiRequestBaseModel {
    @SerializedName("activityCode")
    @Expose
    private String activityCode;
    @SerializedName("activityData")
    @Expose
    private GenericActivitySessionData activityData;
    @SerializedName("activityOrder")
    @Expose
    private List<String> activityOrder;
    @SerializedName("activityTransitionTimes")
    @Expose
    private List<Integer> activityTransitionTimes;
    @SerializedName("appConnectivityCode")
    @Expose
    private String appConnectivityCode;
    @SerializedName("autoRecognized")
    @Expose
    private boolean autoRecognized;
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
    @SerializedName("climbing")
    private ClimbingActivityDetails climbingActivityDetails;
    @SerializedName("cycle")
    @Expose
    private CycleActivityDetails cycleDetails;
    @SerializedName("dance")
    @Expose
    private DanceActivityDetails danceDetails;
    @SerializedName("deviceSpecificParams")
    @Expose
    private DeviceSpecificParams deviceSpecificParams;
    @SerializedName(FitnessActivities.ELLIPTICAL)
    @Expose
    private EllipticalActivityDetails ellipticalDetails;
    @SerializedName(d.O)
    private FeedbackModel feedback;
    @SerializedName("fitnessSessionId")
    @Expose
    private String fitnessSessionId;
    @SerializedName("football")
    @Expose
    private FootBallActivityDetails footballDetails;
    @SerializedName("freeExercise")
    @Expose
    private FreeExerciseActivityDetails freeExerciseActivityDetails;
    @SerializedName("fwSessionId")
    @Expose
    private String fwSessionId;
    @SerializedName("geoData")
    @Expose
    private GeoData geoData;
    @SerializedName(FitnessActivities.GOLF)
    @Expose
    private GolfActivityDetails golfActivityDetails;
    @SerializedName(FitnessActivities.HIKING)
    @Expose
    private HikingActivityDetails hikingDetails;
    @SerializedName(FitnessActivities.HOCKEY)
    @Expose
    private HockeyActivityDetails hockeyActivityDetails;
    @SerializedName("hrZoneRanges")
    @Expose
    private List<Integer> hrZoneRanges;
    @SerializedName("hulaHoop")
    @Expose
    private HulaHoopActivityDetails hulaHoopActivityDetails;
    @SerializedName(FitnessActivities.MEDITATION)
    @Expose
    private MeditationActivityDetails meditationDetails;
    @SerializedName("moodAfterSession")
    private String moodAfterSession;
    @SerializedName("otherParams")
    @Expose
    private OtherParams otherParams;
    @SerializedName("physicalActivity")
    @Expose
    private PhysicalActivityDetails physicalActivityDetails;
    @SerializedName(FitnessActivities.PILATES)
    @Expose
    private PilatesActivityDetails pilatesActivityDetails;
    @SerializedName("pingPong")
    @Expose
    private PingPongActivityDetails pingPongActivityDetails;
    @SerializedName("placeType")
    private String placeType;
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
    private SkippingActivityDetails skippingActivityDetails;
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("swim")
    @Expose
    private SwimActivityDetails swimDetails;
    @SerializedName(FitnessActivities.TENNIS)
    @Expose
    private TennisActivityDetails tennisDetails;
    @SerializedName("totalActiveTime")
    @Expose
    private Integer totalActiveTime = null;
    @SerializedName("totalActivityDuration")
    @Expose
    private int totalActivityDuration;
    @SerializedName("totalSampleCount")
    @Expose
    private int totalSampleCount;
    @SerializedName(FitnessActivities.TREADMILL)
    private TreadmillActivityDetails treadmillActivityDetails;
    @SerializedName("userDeviceId")
    @Expose
    private String userDeviceId;
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

    public GenericActivitySessionData getActivityData() {
        return this.activityData;
    }

    public List<String> getActivityOrder() {
        return this.activityOrder;
    }

    public List<Integer> getActivityTransitionTimes() {
        return this.activityTransitionTimes;
    }

    public String getAppConnectivityCode() {
        return this.appConnectivityCode;
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

    public ClimbingActivityDetails getClimbingActivityDetails() {
        return this.climbingActivityDetails;
    }

    public CycleActivityDetails getCycleDetails() {
        return this.cycleDetails;
    }

    public DanceActivityDetails getDanceDetails() {
        return this.danceDetails;
    }

    public DeviceSpecificParams getDeviceSpecificParams() {
        return this.deviceSpecificParams;
    }

    public EllipticalActivityDetails getEllipticalDetails() {
        return this.ellipticalDetails;
    }

    public FeedbackModel getFeedback() {
        return this.feedback;
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

    public String getFwSessionId() {
        return this.fwSessionId;
    }

    public GeoData getGeoData() {
        return this.geoData;
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

    public List<Integer> getHrZoneRanges() {
        return this.hrZoneRanges;
    }

    public HulaHoopActivityDetails getHulaHoopActivityDetails() {
        return this.hulaHoopActivityDetails;
    }

    public MeditationActivityDetails getMeditationDetails() {
        return this.meditationDetails;
    }

    public String getMoodAfterSession() {
        return this.moodAfterSession;
    }

    public OtherParams getOtherParams() {
        return this.otherParams;
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

    public String getPlaceType() {
        return this.placeType;
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

    public String getSessionPlace() {
        return this.placeType;
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

    public SkippingActivityDetails getSkippingActivityDetails() {
        return this.skippingActivityDetails;
    }

    public Source getSource() {
        return this.source;
    }

    public SwimActivityDetails getSwimDetails() {
        return this.swimDetails;
    }

    public TennisActivityDetails getTennisDetails() {
        return this.tennisDetails;
    }

    public Integer getTotalActiveTime() {
        return this.totalActiveTime;
    }

    public int getTotalActivityDuration() {
        return this.totalActivityDuration;
    }

    public Integer getTotalSampleCount() {
        return Integer.valueOf(this.totalSampleCount);
    }

    public TreadmillActivityDetails getTreadmillActivityDetails() {
        return this.treadmillActivityDetails;
    }

    public String getUserDeviceId() {
        return this.userDeviceId;
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

    public boolean isAutoRecognized() {
        return this.autoRecognized;
    }

    public void setActivityCode(String str) {
        this.activityCode = str;
    }

    public void setActivityData(GenericActivitySessionData genericActivitySessionData) {
        this.activityData = genericActivitySessionData;
    }

    public void setActivityOrder(List<String> list) {
        this.activityOrder = list;
    }

    public void setActivityTransitionTimes(List<Integer> list) {
        this.activityTransitionTimes = list;
    }

    public void setAppConnectivityCode(String str) {
        this.appConnectivityCode = str;
    }

    public void setAutoRecognized(boolean z) {
        this.autoRecognized = z;
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

    public void setClimbingActivityDetails(ClimbingActivityDetails climbingActivityDetails) {
        this.climbingActivityDetails = climbingActivityDetails;
    }

    public void setCycleDetails(CycleActivityDetails cycleActivityDetails) {
        this.cycleDetails = cycleActivityDetails;
    }

    public void setDanceDetails(DanceActivityDetails danceActivityDetails) {
        this.danceDetails = danceActivityDetails;
    }

    public void setDeviceSpecificParams(DeviceSpecificParams deviceSpecificParams) {
        this.deviceSpecificParams = deviceSpecificParams;
    }

    public void setEllipticalDetails(EllipticalActivityDetails ellipticalActivityDetails) {
        this.ellipticalDetails = ellipticalActivityDetails;
    }

    public void setFeedback(FeedbackModel feedbackModel) {
        this.feedback = feedbackModel;
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

    public void setFwSessionId(String str) {
        this.fwSessionId = str;
    }

    public void setGeoData(GeoData geoData) {
        this.geoData = geoData;
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

    public void setHrZoneRanges(List<Integer> list) {
        this.hrZoneRanges = list;
    }

    public void setHulaHoopActivityDetails(HulaHoopActivityDetails hulaHoopActivityDetails) {
        this.hulaHoopActivityDetails = hulaHoopActivityDetails;
    }

    public void setMeditationDetails(MeditationActivityDetails meditationActivityDetails) {
        this.meditationDetails = meditationActivityDetails;
    }

    public void setMoodAfterSession(String str) {
        this.moodAfterSession = str;
    }

    public void setOtherParams(OtherParams otherParams) {
        this.otherParams = otherParams;
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

    public void setPlaceType(String str) {
        this.placeType = str;
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

    public void setSessionPlace(String str) {
        this.placeType = str;
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

    public void setSkippingActivityDetails(SkippingActivityDetails skippingActivityDetails) {
        this.skippingActivityDetails = skippingActivityDetails;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public void setSwimDetails(SwimActivityDetails swimActivityDetails) {
        this.swimDetails = swimActivityDetails;
    }

    public void setTennisDetails(TennisActivityDetails tennisActivityDetails) {
        this.tennisDetails = tennisActivityDetails;
    }

    public void setTotalActiveTime(Integer num) {
        this.totalActiveTime = num;
    }

    public void setTotalActivityDuration(int i) {
        this.totalActivityDuration = i;
    }

    public void setTotalSampleCount(Integer num) {
        this.totalSampleCount = num.intValue();
    }

    public void setTreadmillActivityDetails(TreadmillActivityDetails treadmillActivityDetails) {
        this.treadmillActivityDetails = treadmillActivityDetails;
    }

    public void setUserDeviceId(String str) {
        this.userDeviceId = str;
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
        this.totalSampleCount = i;
    }
}
