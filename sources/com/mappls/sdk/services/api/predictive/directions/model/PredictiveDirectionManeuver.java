package com.mappls.sdk.services.api.predictive.directions.model;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import java.util.List;
@Keep
/* loaded from: classes7.dex */
public class PredictiveDirectionManeuver {
    @SerializedName("begin_shape_index")
    @Expose
    private Integer beginShapeIndex;
    @SerializedName("begin_street_names")
    @Expose
    private List<String> beginStreetNames;
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("end_shape_index")
    @Expose
    private Integer endShapeIndex;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("length")
    @Expose
    private Double length;
    @SerializedName("roundabout_exit_count")
    @Expose
    private Integer roundaboutExitCount;
    @SerializedName("sign")
    @Expose
    private String sign;
    @SerializedName("street_names")
    @Expose
    private List<String> streetNames;
    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
    @Expose
    private Double time;
    @SerializedName(DirectionsCriteria.EXCLUDE_TOLL)
    @Expose
    private Boolean toll;
    @SerializedName("travel_mode")
    @Expose
    private String travelMode;
    @SerializedName("travel_type")
    @Expose
    private String travelType;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("verbal_multi_cue")
    @Expose
    private Boolean verbalMultiCue;
    @SerializedName("verbal_post_transition_instruction")
    @Expose
    private String verbalPostTransitionInstruction;
    @SerializedName("verbal_pre_transition_instruction")
    @Expose
    private String verbalPreTransitionInstruction;
    @SerializedName("verbal_transition_alert_instruction")
    @Expose
    private String verbalTransitionAlertInstruction;

    public Integer getBeginShapeIndex() {
        return this.beginShapeIndex;
    }

    public List<String> getBeginStreetNames() {
        return this.beginStreetNames;
    }

    public Double getCost() {
        return this.cost;
    }

    public Integer getEndShapeIndex() {
        return this.endShapeIndex;
    }

    public String getInstruction() {
        return this.instruction;
    }

    public Double getLength() {
        return this.length;
    }

    public Integer getRoundaboutExitCount() {
        return this.roundaboutExitCount;
    }

    public String getSign() {
        return this.sign;
    }

    public List<String> getStreetNames() {
        return this.streetNames;
    }

    public Double getTime() {
        return this.time;
    }

    public Boolean getToll() {
        return this.toll;
    }

    public String getTravelMode() {
        return this.travelMode;
    }

    public String getTravelType() {
        return this.travelType;
    }

    public Integer getType() {
        return this.type;
    }

    public Boolean getVerbalMultiCue() {
        return this.verbalMultiCue;
    }

    public String getVerbalPostTransitionInstruction() {
        return this.verbalPostTransitionInstruction;
    }

    public String getVerbalPreTransitionInstruction() {
        return this.verbalPreTransitionInstruction;
    }

    public String getVerbalTransitionAlertInstruction() {
        return this.verbalTransitionAlertInstruction;
    }

    public void setBeginShapeIndex(Integer num) {
        this.beginShapeIndex = num;
    }

    public void setBeginStreetNames(List<String> list) {
        this.beginStreetNames = list;
    }

    public void setCost(Double d) {
        this.cost = d;
    }

    public void setEndShapeIndex(Integer num) {
        this.endShapeIndex = num;
    }

    public void setInstruction(String str) {
        this.instruction = str;
    }

    public void setLength(Double d) {
        this.length = d;
    }

    public void setRoundaboutExitCount(Integer num) {
        this.roundaboutExitCount = num;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setStreetNames(List<String> list) {
        this.streetNames = list;
    }

    public void setTime(Double d) {
        this.time = d;
    }

    public void setToll(Boolean bool) {
        this.toll = bool;
    }

    public void setTravelMode(String str) {
        this.travelMode = str;
    }

    public void setTravelType(String str) {
        this.travelType = str;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public void setVerbalMultiCue(Boolean bool) {
        this.verbalMultiCue = bool;
    }

    public void setVerbalPostTransitionInstruction(String str) {
        this.verbalPostTransitionInstruction = str;
    }

    public void setVerbalPreTransitionInstruction(String str) {
        this.verbalPreTransitionInstruction = str;
    }

    public void setVerbalTransitionAlertInstruction(String str) {
        this.verbalTransitionAlertInstruction = str;
    }
}
