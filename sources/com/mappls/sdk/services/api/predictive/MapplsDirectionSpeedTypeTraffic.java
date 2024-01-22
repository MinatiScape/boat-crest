package com.mappls.sdk.services.api.predictive;

import androidx.annotation.Keep;
import com.mappls.sdk.services.api.predictive.distance.PredictiveDistanceCriteria;
@Keep
/* loaded from: classes7.dex */
public class MapplsDirectionSpeedTypeTraffic implements MapplsDirectionSpeedType {
    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType
    public String speedDateTime() {
        return null;
    }

    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType
    public String speedType() {
        return PredictiveDistanceCriteria.SPEED_TYPES_TRAFFIC;
    }
}
