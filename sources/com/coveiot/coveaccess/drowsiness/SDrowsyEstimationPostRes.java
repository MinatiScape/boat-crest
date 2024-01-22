package com.coveiot.coveaccess.drowsiness;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes8.dex */
public class SDrowsyEstimationPostRes extends CoveApiResponseBaseModel {
    @SerializedName("feature")
    private Integer feature;
    @SerializedName("result")
    private Integer result;
    @SerializedName("result_type")
    private Integer resultType;
    @SerializedName("server_data")
    private JsonObject serverData;

    public SDrowsyEstimationPostRes(int i) {
        super(i);
    }

    public Integer getFeature() {
        return this.feature;
    }

    public Integer getResult() {
        return this.result;
    }

    public Integer getResultType() {
        return this.resultType;
    }

    public JsonObject getServerData() {
        return this.serverData;
    }

    public void setFeature(Integer num) {
        this.feature = num;
    }

    public void setResult(Integer num) {
        this.result = num;
    }

    public void setResultType(Integer num) {
        this.resultType = num;
    }

    public void setServerData(JsonObject jsonObject) {
        this.serverData = jsonObject;
    }
}
