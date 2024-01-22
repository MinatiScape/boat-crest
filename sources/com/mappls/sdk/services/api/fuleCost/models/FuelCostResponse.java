package com.mappls.sdk.services.api.fuleCost.models;

import androidx.annotation.Keep;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.costestimation.CostEstimationCriteria;
@Keep
/* loaded from: classes6.dex */
public class FuelCostResponse {
    @SerializedName("cng")
    @Expose
    private Double cng;
    @SerializedName(CostEstimationCriteria.VEHICLE_FUEL_TYPE_DIESEL)
    @Expose
    private Double diesel;
    @SerializedName(CostEstimationCriteria.VEHICLE_FUEL_TYPE_ELECTRIC)
    @Expose
    private Double electric;
    @SerializedName(CostEstimationCriteria.VEHICLE_FUEL_TYPE_PETROL)
    @Expose
    private Double petrol;

    public Double getCng() {
        return this.cng;
    }

    public Double getDiesel() {
        return this.diesel;
    }

    public Double getElectric() {
        return this.electric;
    }

    public Double getPetrol() {
        return this.petrol;
    }

    public void setCng(Double d) {
        this.cng = d;
    }

    public void setDiesel(Double d) {
        this.diesel = d;
    }

    public void setElectric(Double d) {
        this.electric = d;
    }

    public void setPetrol(Double d) {
        this.petrol = d;
    }
}
