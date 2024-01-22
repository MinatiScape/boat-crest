package com.mappls.sdk.services.api.transit.model;

import androidx.annotation.Keep;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.annotations.SerializedName;
import java.util.List;
@Keep
/* loaded from: classes8.dex */
public class TransitPlan {
    @SerializedName("date")
    private Long date;
    @SerializedName(TypedValues.TransitionType.S_TO)
    private TransitVertex destination;
    @SerializedName("itineraries")
    private List<TransitItinerary> itineraries;
    @SerializedName("from")
    private TransitVertex source;

    public Long getDate() {
        return this.date;
    }

    public TransitVertex getDestination() {
        return this.destination;
    }

    public List<TransitItinerary> getItineraries() {
        return this.itineraries;
    }

    public TransitVertex getSource() {
        return this.source;
    }

    public void setDate(Long l) {
        this.date = l;
    }

    public void setDestination(TransitVertex transitVertex) {
        this.destination = transitVertex;
    }

    public void setItineraries(List<TransitItinerary> list) {
        this.itineraries = list;
    }

    public void setSource(TransitVertex transitVertex) {
        this.source = transitVertex;
    }
}
