package com.jieli.jl_rcsp.model.device.health;
/* loaded from: classes11.dex */
public class HealthSettingInfo {
    private AutomaticPressureDetection automaticPressureDetection;
    private BloodOxygenMeasurementAlert bloodOxygenMeasurementAlert;
    private DisconnectReminder disconnectReminder;
    private EmergencyContact emergencyContact;
    private ExerciseHeartRateReminder exerciseHeartRateReminder;
    private FallDetection fallDetection;
    private HeartRateMeasure heartRateMeasure;
    private LiftWristDetection liftWristDetection;
    private SedentaryReminder sedentaryReminder;
    private SleepDetection sleepDetection;
    private UserInfo userInfo;
    private int funcFlag = 0;
    private SensorInfo sensorInfo = new SensorInfo(0);

    public HealthSettingInfo() {
        byte[] bArr = new byte[0];
        this.sedentaryReminder = new SedentaryReminder(bArr);
        this.heartRateMeasure = new HeartRateMeasure(bArr);
        this.exerciseHeartRateReminder = new ExerciseHeartRateReminder(bArr);
        this.automaticPressureDetection = new AutomaticPressureDetection(bArr);
        this.sleepDetection = new SleepDetection(bArr);
        this.fallDetection = new FallDetection(bArr);
        this.liftWristDetection = new LiftWristDetection(bArr);
        this.userInfo = new UserInfo(bArr);
        this.disconnectReminder = new DisconnectReminder(bArr);
        this.bloodOxygenMeasurementAlert = new BloodOxygenMeasurementAlert(bArr);
        this.emergencyContact = new EmergencyContact(bArr);
    }

    public AutomaticPressureDetection getAutomaticPressureDetection() {
        return this.automaticPressureDetection;
    }

    public BloodOxygenMeasurementAlert getBloodOxygenMeasurementAlert() {
        return this.bloodOxygenMeasurementAlert;
    }

    public DisconnectReminder getDisconnectReminder() {
        return this.disconnectReminder;
    }

    public EmergencyContact getEmergencyContact() {
        return this.emergencyContact;
    }

    public ExerciseHeartRateReminder getExerciseHeartRateReminder() {
        return this.exerciseHeartRateReminder;
    }

    public FallDetection getFallDetection() {
        return this.fallDetection;
    }

    public int getFuncFlag() {
        return this.funcFlag;
    }

    public HeartRateMeasure getHeartRateMeasure() {
        return this.heartRateMeasure;
    }

    public LiftWristDetection getLiftWristDetection() {
        return this.liftWristDetection;
    }

    public SedentaryReminder getSedentaryReminder() {
        return this.sedentaryReminder;
    }

    public SensorInfo getSensorInfo() {
        return this.sensorInfo;
    }

    public SleepDetection getSleepDetection() {
        return this.sleepDetection;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setAutomaticPressureDetection(AutomaticPressureDetection automaticPressureDetection) {
        this.automaticPressureDetection = automaticPressureDetection;
    }

    public void setBloodOxygenMeasurementAlert(BloodOxygenMeasurementAlert bloodOxygenMeasurementAlert) {
        this.bloodOxygenMeasurementAlert = bloodOxygenMeasurementAlert;
    }

    public void setDisconnectReminder(DisconnectReminder disconnectReminder) {
        this.disconnectReminder = disconnectReminder;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public void setExerciseHeartRateReminder(ExerciseHeartRateReminder exerciseHeartRateReminder) {
        this.exerciseHeartRateReminder = exerciseHeartRateReminder;
    }

    public void setFallDetection(FallDetection fallDetection) {
        this.fallDetection = fallDetection;
    }

    public void setFuncFlag(int i) {
        this.funcFlag = i;
    }

    public void setHeartRateMeasure(HeartRateMeasure heartRateMeasure) {
        this.heartRateMeasure = heartRateMeasure;
    }

    public void setLiftWristDetection(LiftWristDetection liftWristDetection) {
        this.liftWristDetection = liftWristDetection;
    }

    public void setSedentaryReminder(SedentaryReminder sedentaryReminder) {
        this.sedentaryReminder = sedentaryReminder;
    }

    public void setSensorInfo(SensorInfo sensorInfo) {
        this.sensorInfo = sensorInfo;
    }

    public void setSleepDetection(SleepDetection sleepDetection) {
        this.sleepDetection = sleepDetection;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String toString() {
        return "HealthSettingInfo{funcFlag=" + this.funcFlag + ", sensorInfo=" + this.sensorInfo + ", sedentaryReminder=" + this.sedentaryReminder + ", heartRateMeasure=" + this.heartRateMeasure + ", exerciseHeartRateReminder=" + this.exerciseHeartRateReminder + ", automaticPressureDetection=" + this.automaticPressureDetection + ", sleepDetection=" + this.sleepDetection + ", fallDetection=" + this.fallDetection + ", liftWristDetection=" + this.liftWristDetection + ", userInfo=" + this.userInfo + ", disconnectReminder=" + this.disconnectReminder + ", bloodOxygenMeasurementAlert=" + this.bloodOxygenMeasurementAlert + ", emergencyContact=" + this.emergencyContact + '}';
    }
}
