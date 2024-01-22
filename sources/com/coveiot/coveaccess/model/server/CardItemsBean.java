package com.coveiot.coveaccess.model.server;

import com.coveiot.coveaccess.mediauplaod.model.MediaListBean;
import com.coveiot.coveaccess.timeline.model.TimeLineBadgeData;
import com.coveiot.coveaccess.timeline.model.TimeLineCheckInData;
import com.coveiot.coveaccess.timeline.model.TimeLineCheerData;
import com.coveiot.coveaccess.timeline.model.TimeLineData;
import com.coveiot.coveaccess.timeline.model.TimeLineSleepData;
import com.coveiot.coveaccess.timeline.model.TimeLineStepsData;
import com.coveiot.coveaccess.timeline.model.TimelineCardType;
import com.coveiot.coveaccess.utils.CoveUtil;
import com.coveiot.coveaccess.utils.TimeUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes8.dex */
public class CardItemsBean implements Serializable {
    private int awake;
    private BadgeBean badge;
    private int calories;
    private String cardType;
    private int cardVersion;
    private int dailySleepTarget;
    private int dailyStepsTarget;
    private int deepSleep;
    private int dwellRadius;
    private int dwellThreshold;
    private FitnessMessageBean fitnessMessage;
    private String fitnessMessageId;
    private String formattedAddress;
    private int lightSleep;
    private String localityAddress;
    private LocationBean location;
    private String logId;
    private String loggedDate;
    private List<MediaListBean> mediaList;
    private double meters;
    private PlaceBean place;
    @SerializedName("predictedPlace")
    private PredictedPlaceBean predictedPlace;
    private double sleepQuality;
    private int steps;
    private int totalStayTime;

    /* loaded from: classes8.dex */
    public static class BadgeBean {
        private String badgeDescription;
        private String badgeImageUrl;
        private BadgeLevelBean badgeLevel;
        private String badgeName;
        private String userBadgeId;

        /* loaded from: classes8.dex */
        public static class BadgeLevelBean {
            private String levelDescription;
            private String levelImageUrl;
            private String levelName;
            private String nextLevelCriteria;
            private String obtainedDate;
            private int totalObtained;

            public String getLevelDescription() {
                return this.levelDescription;
            }

            public String getLevelImageUrl() {
                return this.levelImageUrl;
            }

            public String getLevelName() {
                return this.levelName;
            }

            public String getNextLevelCriteria() {
                return this.nextLevelCriteria;
            }

            public String getObtainedDate() {
                return this.obtainedDate;
            }

            public int getTotalObtained() {
                return this.totalObtained;
            }

            public void setLevelDescription(String str) {
                this.levelDescription = str;
            }

            public void setLevelImageUrl(String str) {
                this.levelImageUrl = str;
            }

            public void setLevelName(String str) {
                this.levelName = str;
            }

            public void setNextLevelCriteria(String str) {
                this.nextLevelCriteria = str;
            }

            public void setObtainedDate(String str) {
                this.obtainedDate = str;
            }

            public void setTotalObtained(int i) {
                this.totalObtained = i;
            }
        }

        public String getBadgeDescription() {
            return this.badgeDescription;
        }

        public String getBadgeImageUrl() {
            return this.badgeImageUrl;
        }

        public BadgeLevelBean getBadgeLevel() {
            return this.badgeLevel;
        }

        public String getBadgeName() {
            return this.badgeName;
        }

        public String getUserBadgeId() {
            return this.userBadgeId;
        }

        public void setBadgeDescription(String str) {
            this.badgeDescription = str;
        }

        public void setBadgeImageUrl(String str) {
            this.badgeImageUrl = str;
        }

        public void setBadgeLevel(BadgeLevelBean badgeLevelBean) {
            this.badgeLevel = badgeLevelBean;
        }

        public void setBadgeName(String str) {
            this.badgeName = str;
        }

        public void setUserBadgeId(String str) {
            this.userBadgeId = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class FitnessMessageBean {
        private String createdDate;
        private FitnessGoalBean fitnessGoal;
        private int fromUserId;
        private String fromUserName;
        private String message;
        private String toUserDpUrl;
        private int toUserId;
        private String toUserName;
        private String type;

        /* loaded from: classes8.dex */
        public static class FitnessGoalBean {
            private String activityBaseUnit;
            private String activityType;
            private String goalId;
            private String startDate;
            private String target;
            private String targetAchieved;
            private int targetedDays;

            public String getActivityBaseUnit() {
                return this.activityBaseUnit;
            }

            public String getActivityType() {
                return this.activityType;
            }

            public String getGoalId() {
                return this.goalId;
            }

            public String getStartDate() {
                return this.startDate;
            }

            public String getTarget() {
                return this.target;
            }

            public String getTargetAchieved() {
                return this.targetAchieved;
            }

            public int getTargetedDays() {
                return this.targetedDays;
            }

            public void setActivityBaseUnit(String str) {
                this.activityBaseUnit = str;
            }

            public void setActivityType(String str) {
                this.activityType = str;
            }

            public void setGoalId(String str) {
                this.goalId = str;
            }

            public void setStartDate(String str) {
                this.startDate = str;
            }

            public void setTarget(String str) {
                this.target = str;
            }

            public void setTargetAchieved(String str) {
                this.targetAchieved = str;
            }

            public void setTargetedDays(int i) {
                this.targetedDays = i;
            }
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public FitnessGoalBean getFitnessGoal() {
            return this.fitnessGoal;
        }

        public int getFromUserId() {
            return this.fromUserId;
        }

        public String getFromUserName() {
            return this.fromUserName;
        }

        public String getMessage() {
            return this.message;
        }

        public String getToUserDpUrl() {
            return this.toUserDpUrl;
        }

        public int getToUserId() {
            return this.toUserId;
        }

        public String getToUserName() {
            return this.toUserName;
        }

        public String getType() {
            return this.type;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setFitnessGoal(FitnessGoalBean fitnessGoalBean) {
            this.fitnessGoal = fitnessGoalBean;
        }

        public void setFromUserId(int i) {
            this.fromUserId = i;
        }

        public void setFromUserName(String str) {
            this.fromUserName = str;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public void setToUserDpUrl(String str) {
            this.toUserDpUrl = str;
        }

        public void setToUserId(int i) {
            this.toUserId = i;
        }

        public void setToUserName(String str) {
            this.toUserName = str;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class LocationBean {
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }
    }

    /* loaded from: classes8.dex */
    public static class PlaceBean {
        private String name;
        private String placeId;
        private String selectedDate;
        private String vicinity;

        public String getName() {
            return this.name;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public String getSelectedDate() {
            return this.selectedDate;
        }

        public String getVicinity() {
            return this.vicinity;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public void setSelectedDate(String str) {
            this.selectedDate = str;
        }

        public void setVicinity(String str) {
            this.vicinity = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class PredictedPlaceBean {
        @SerializedName("mediaList")
        private List<MediaListBean> mediaListX;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName("placeId")
        private String placeId;
        @SerializedName("vicinity")
        private String vicinity;

        public List<MediaListBean> getMediaListX() {
            return this.mediaListX;
        }

        public String getName() {
            return this.name;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public String getVicinity() {
            return this.vicinity;
        }

        public void setMediaListX(List<MediaListBean> list) {
            this.mediaListX = list;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public void setVicinity(String str) {
            this.vicinity = str;
        }
    }

    public static CardItemsBean getInstanceFrom(TimeLineData timeLineData) {
        CardItemsBean cardItemsBean = new CardItemsBean();
        if (timeLineData.getTimelineCardType() == TimelineCardType.SLEEP) {
            if (timeLineData instanceof TimeLineSleepData) {
                TimeLineSleepData timeLineSleepData = (TimeLineSleepData) timeLineData;
                cardItemsBean.setCardType("FIT_SLEEP_DAILY");
                cardItemsBean.setCardVersion(1);
                cardItemsBean.setLoggedDate(TimeUtils.covertTimeStamptoISOLocalTime(timeLineData.getTimeStamp()));
                cardItemsBean.setDeepSleep(timeLineSleepData.getDeepSleep());
                cardItemsBean.setLightSleep(timeLineSleepData.getLightSleep());
                cardItemsBean.setAwake(timeLineSleepData.getAwake());
                cardItemsBean.setDailySleepTarget(timeLineSleepData.getTarget());
                cardItemsBean.setSleepQuality(((timeLineSleepData.getDeepSleep() + timeLineSleepData.getLightSleep()) * 100) / timeLineSleepData.getTarget());
            }
        } else if (timeLineData.getTimelineCardType() == TimelineCardType.FITNESS) {
            if (timeLineData instanceof TimeLineStepsData) {
                TimeLineStepsData timeLineStepsData = (TimeLineStepsData) timeLineData;
                cardItemsBean.setCardType("FIT_WALK_COMPLETED");
                cardItemsBean.setCardVersion(1);
                cardItemsBean.setLoggedDate(TimeUtils.covertTimeStamptoISOLocalTime(timeLineData.getTimeStamp()));
                cardItemsBean.setSteps(timeLineStepsData.getStepsValue());
                cardItemsBean.setCalories(timeLineStepsData.getCalories());
                cardItemsBean.setMeters(timeLineStepsData.getDistance());
                cardItemsBean.setDailyStepsTarget(timeLineStepsData.getTarget());
            }
        } else if (timeLineData.getTimelineCardType() == TimelineCardType.CHECK_IN && (timeLineData instanceof TimeLineCheckInData)) {
            TimeLineCheckInData timeLineCheckInData = (TimeLineCheckInData) timeLineData;
            cardItemsBean.setCardType("PLACE_CHECKIN");
            cardItemsBean.setCardVersion(1);
            cardItemsBean.setLoggedDate(TimeUtils.covertTimeStamptoISOLocalTime(timeLineData.getTimeStamp()));
            LocationBean locationBean = new LocationBean();
            locationBean.setLatitude(timeLineCheckInData.getLatitude());
            locationBean.setLongitude(timeLineCheckInData.getLongitude());
            cardItemsBean.setLocation(locationBean);
            cardItemsBean.setFormattedAddress(timeLineCheckInData.getAddress());
            cardItemsBean.setLocalityAddress(timeLineCheckInData.getLocality());
            if (!CoveUtil.isEmpty(timeLineCheckInData.getPlace_id())) {
                PlaceBean placeBean = new PlaceBean();
                placeBean.setPlaceId(timeLineCheckInData.getPlace_id());
                placeBean.setName(timeLineCheckInData.getPlaceName());
                placeBean.setVicinity(timeLineCheckInData.getPlaceVicivity());
                cardItemsBean.setPlace(placeBean);
            }
            if (!CoveUtil.isEmpty(timeLineCheckInData.getMediaListBeanList())) {
                cardItemsBean.setMediaList(timeLineCheckInData.getMediaListBeanList());
            }
            cardItemsBean.setDwellRadius(timeLineCheckInData.getDwellRadius());
            cardItemsBean.setDwellThreshold(timeLineCheckInData.getDwellThreshold());
        }
        return cardItemsBean;
    }

    public TimeLineData covertToTimeLineData() {
        String covertISOLocalTimeToTimeStamp = TimeUtils.covertISOLocalTimeToTimeStamp(getLoggedDate());
        String covertISOLocalTimeToDate = TimeUtils.covertISOLocalTimeToDate(getLoggedDate());
        if (getCardType().equalsIgnoreCase("FIT_WALK_COMPLETED")) {
            TimeLineStepsData timeLineStepsData = new TimeLineStepsData(TimelineCardType.FITNESS, covertISOLocalTimeToTimeStamp, covertISOLocalTimeToDate);
            timeLineStepsData.setLogId(getLogId());
            timeLineStepsData.setStepsValue(getSteps());
            timeLineStepsData.setCalories(getCalories());
            timeLineStepsData.setDistance(getMeters());
            timeLineStepsData.setTarget(getDailyStepsTarget());
            return timeLineStepsData;
        } else if (getCardType().equalsIgnoreCase("FIT_SLEEP_DAILY")) {
            TimeLineSleepData timeLineSleepData = new TimeLineSleepData(TimelineCardType.SLEEP, covertISOLocalTimeToTimeStamp, covertISOLocalTimeToDate);
            timeLineSleepData.setLogId(getLogId());
            timeLineSleepData.setSleepValue(getLightSleep() + getDeepSleep());
            timeLineSleepData.setDeepSleep(getDeepSleep());
            timeLineSleepData.setLightSleep(getLightSleep());
            timeLineSleepData.setAwake(getAwake());
            timeLineSleepData.setTarget(getDailySleepTarget());
            return timeLineSleepData;
        } else if (getCardType().equalsIgnoreCase("PLACE_CHECKIN")) {
            TimeLineCheckInData timeLineCheckInData = new TimeLineCheckInData(TimelineCardType.CHECK_IN, covertISOLocalTimeToTimeStamp, covertISOLocalTimeToDate, getLocation().getLatitude(), getLocation().getLongitude(), getLocalityAddress(), getFormattedAddress());
            timeLineCheckInData.setLogId(getLogId());
            if (getPlace() != null) {
                timeLineCheckInData.setPlace_id(getPlace().getPlaceId());
                timeLineCheckInData.setPlaceName(getPlace().getName());
                timeLineCheckInData.setPlaceVicivity(getPlace().getVicinity());
                timeLineCheckInData.setPlaceSelectedTime(getPlace().getSelectedDate());
                timeLineCheckInData.setMediaListBeanList(getMediaList());
            }
            if (getPredictedPlace() != null) {
                TimeLineCheckInData.PredictedPlaceBean predictedPlaceBean = new TimeLineCheckInData.PredictedPlaceBean();
                predictedPlaceBean.setPlaceId(getPredictedPlace().getPlaceId());
                predictedPlaceBean.setName(getPredictedPlace().getName());
                predictedPlaceBean.setVicinity(getPredictedPlace().getVicinity());
                predictedPlaceBean.setMediaListX(getPredictedPlace().getMediaListX());
                timeLineCheckInData.setPredictedPlaceBean(predictedPlaceBean);
            }
            return timeLineCheckInData;
        } else if (getCardType().equalsIgnoreCase("FIT_BUDDY_MESSAGE")) {
            try {
                covertISOLocalTimeToTimeStamp = TimeUtils.convertFromUTCToLocalTimeStamp(getLoggedDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            TimeLineCheerData timeLineCheerData = new TimeLineCheerData(TimelineCardType.CHEER, covertISOLocalTimeToTimeStamp, covertISOLocalTimeToDate);
            timeLineCheerData.setLogId(getLogId());
            timeLineCheerData.setCheertype(getFitnessMessage().getType());
            timeLineCheerData.setBuddyMessage(getFitnessMessage().getMessage());
            timeLineCheerData.setCheerSenderName(getFitnessMessage().getFromUserName());
            timeLineCheerData.setFitnessMessageId(getFitnessMessageId());
            return timeLineCheerData;
        } else if (this.cardType.equalsIgnoreCase("LEADERBOARD_BADGE_OBTAINED")) {
            try {
                covertISOLocalTimeToTimeStamp = TimeUtils.convertFromUTCToLocalTimeStamp(getLoggedDate());
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            TimeLineBadgeData timeLineBadgeData = new TimeLineBadgeData(TimelineCardType.BADGES, covertISOLocalTimeToTimeStamp, covertISOLocalTimeToDate);
            timeLineBadgeData.setLogId(getLogId());
            StringBuilder sb = new StringBuilder();
            sb.append("You earned ");
            sb.append(getBadge().getBadgeName());
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(getBadge().getBadgeLevel().getLevelName().equalsIgnoreCase("white") ? "" : getBadge().getBadgeLevel().getLevelName());
            sb.append(" Badge!");
            timeLineBadgeData.setBadgeTitle(sb.toString());
            timeLineBadgeData.setBadgeDesc(getBadge().getBadgeDescription());
            timeLineBadgeData.setBadgeType(getBadge().getBadgeLevel().getLevelName());
            timeLineBadgeData.setBadgeUrl(getBadge().getBadgeLevel().getLevelImageUrl());
            return timeLineBadgeData;
        } else {
            return null;
        }
    }

    public int getAwake() {
        return this.awake;
    }

    public BadgeBean getBadge() {
        return this.badge;
    }

    public int getCalories() {
        return this.calories;
    }

    public String getCardType() {
        return this.cardType;
    }

    public int getCardVersion() {
        return this.cardVersion;
    }

    public int getDailySleepTarget() {
        return this.dailySleepTarget;
    }

    public int getDailyStepsTarget() {
        return this.dailyStepsTarget;
    }

    public int getDeepSleep() {
        return this.deepSleep;
    }

    public int getDwellRadius() {
        return this.dwellRadius;
    }

    public int getDwellThreshold() {
        return this.dwellThreshold;
    }

    public FitnessMessageBean getFitnessMessage() {
        return this.fitnessMessage;
    }

    public String getFitnessMessageId() {
        return this.fitnessMessageId;
    }

    public String getFormattedAddress() {
        return this.formattedAddress;
    }

    public int getLightSleep() {
        return this.lightSleep;
    }

    public String getLocalityAddress() {
        return this.localityAddress;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public String getLogId() {
        return this.logId;
    }

    public String getLoggedDate() {
        return this.loggedDate;
    }

    public List<MediaListBean> getMediaList() {
        return this.mediaList;
    }

    public int getMeters() {
        return (int) this.meters;
    }

    public PlaceBean getPlace() {
        return this.place;
    }

    public PredictedPlaceBean getPredictedPlace() {
        return this.predictedPlace;
    }

    public double getSleepQuality() {
        return this.sleepQuality;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getTotalStayTime() {
        return this.totalStayTime;
    }

    public void setAwake(int i) {
        this.awake = i;
    }

    public void setBadge(BadgeBean badgeBean) {
        this.badge = badgeBean;
    }

    public void setCalories(int i) {
        this.calories = i;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public void setCardVersion(int i) {
        this.cardVersion = i;
    }

    public void setDailySleepTarget(int i) {
        this.dailySleepTarget = i;
    }

    public void setDailyStepsTarget(int i) {
        this.dailyStepsTarget = i;
    }

    public void setDeepSleep(int i) {
        this.deepSleep = i;
    }

    public void setDwellRadius(int i) {
        this.dwellRadius = i;
    }

    public void setDwellThreshold(int i) {
        this.dwellThreshold = i;
    }

    public void setFitnessMessage(FitnessMessageBean fitnessMessageBean) {
        this.fitnessMessage = fitnessMessageBean;
    }

    public void setFitnessMessageId(String str) {
        this.fitnessMessageId = str;
    }

    public void setFormattedAddress(String str) {
        this.formattedAddress = str;
    }

    public void setLightSleep(int i) {
        this.lightSleep = i;
    }

    public void setLocalityAddress(String str) {
        this.localityAddress = str;
    }

    public void setLocation(LocationBean locationBean) {
        this.location = locationBean;
    }

    public void setLogId(String str) {
        this.logId = str;
    }

    public void setLoggedDate(String str) {
        this.loggedDate = str;
    }

    public void setMediaList(List<MediaListBean> list) {
        this.mediaList = list;
    }

    public void setMeters(int i) {
        this.meters = i;
    }

    public void setPlace(PlaceBean placeBean) {
        this.place = placeBean;
    }

    public void setPredictedPlace(PredictedPlaceBean predictedPlaceBean) {
        this.predictedPlace = predictedPlaceBean;
    }

    public void setSleepQuality(double d) {
        this.sleepQuality = d;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setTotalStayTime(int i) {
        this.totalStayTime = i;
    }
}
