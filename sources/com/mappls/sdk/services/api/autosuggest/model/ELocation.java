package com.mappls.sdk.services.api.autosuggest.model;

import androidx.annotation.Keep;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
@Keep
/* loaded from: classes11.dex */
public class ELocation {
    @SerializedName("addressTokens")
    @Expose
    public AddressTokens addressTokens;
    @SerializedName("alternateName")
    public String alternateName;
    @SerializedName("distance")
    @Expose
    public Double distance;
    @SerializedName("entryLatitude")
    public Double entryLatitude;
    @SerializedName("entryLongitude")
    public Double entryLongitude;
    @SerializedName(alternate = {EllipticCurveJsonWebKey.Y_MEMBER_NAME}, value = "latitude")
    public Double latitude;
    @SerializedName(alternate = {"x"}, value = "longitude")
    public Double longitude;
    @SerializedName(alternate = {"eLoc", "elc"}, value = "mapplsPin")
    public String mapplsPin;
    @SerializedName("orderIndex")
    @Expose
    public long orderIndex;
    @SerializedName(RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME)
    @Expose
    public long p;
    @SerializedName(alternate = {"addr"}, value = "placeAddress")
    public String placeAddress;
    @SerializedName("placeName")
    public String placeName;
    @SerializedName("resultType")
    @Expose
    public String resultType;
    @SerializedName("richInfo")
    @Expose
    public Map richInfo;
    @SerializedName(FirebaseAnalytics.Param.SCORE)
    @Expose
    public Double score;
    @SerializedName("suggester")
    @Expose
    public String suggester;
    @SerializedName("typeX")
    @Expose
    public long typeX;
    @SerializedName(alternate = {"addedByUserName", "userName"}, value = "user")
    public String user;
    @SerializedName("type")
    public String type = "unknown";
    @SerializedName("keywords")
    @Expose
    public List<String> keywords = null;

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String toString() {
        return "ELocation{poiId='" + this.mapplsPin + "', placeAddress='" + this.placeAddress + "', latitude=" + this.latitude + ", longitude=" + this.longitude + ", type='" + this.type + "', typeX=" + this.typeX + ", placeName='" + this.placeName + "', entryLatitude=" + this.entryLatitude + ", entryLongitude=" + this.entryLongitude + ", user='" + this.user + "', alternateName='" + this.alternateName + "', keywords=" + this.keywords + ", addressTokens=" + this.addressTokens + ", p=" + this.p + ", orderIndex=" + this.orderIndex + ", distance=" + this.distance + ", richInfo=" + this.richInfo + ", score=" + this.score + ", resultType=" + this.resultType + ", suggester=" + this.suggester + '}';
    }
}
