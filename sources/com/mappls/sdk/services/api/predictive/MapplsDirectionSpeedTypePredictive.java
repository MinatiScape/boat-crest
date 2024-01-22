package com.mappls.sdk.services.api.predictive;

import androidx.annotation.Keep;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.services.api.predictive.distance.PredictiveDistanceCriteria;
@Keep
/* loaded from: classes7.dex */
public class MapplsDirectionSpeedTypePredictive implements MapplsDirectionSpeedType {
    private MapplsDirectionDateTime mapplsDirectionDateTime;

    public MapplsDirectionSpeedTypePredictive(MapplsDirectionDateTime mapplsDirectionDateTime) {
        this.mapplsDirectionDateTime = mapplsDirectionDateTime;
    }

    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType
    public String speedDateTime() {
        return this.mapplsDirectionDateTime.type() + Constants.SEPARATOR_COMMA + this.mapplsDirectionDateTime.dateTime();
    }

    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionSpeedType
    public String speedType() {
        return PredictiveDistanceCriteria.SPEED_TYPES_PREDICTIVE;
    }
}
