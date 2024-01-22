package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class RankDetail {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public class BestRank {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("placeType")
        @Expose
        private String placeType;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("rankDate")
        @Expose
        private String rankDate;
        @SerializedName("rankType")
        @Expose
        private String rankType;
        @SerializedName("steps")
        @Expose
        private Integer steps;
        @SerializedName("totalUsers")
        @Expose
        private Integer totalUsers;
        @SerializedName(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID)
        @Expose
        private Integer userId;
        @SerializedName("userLocation")
        @Expose
        private BestRank_UserLocation userLocation;
        @SerializedName("userName")
        @Expose
        private Object userName;

        public BestRank() {
        }

        public Integer getId() {
            return this.id;
        }

        public String getPlaceType() {
            return this.placeType;
        }

        public Integer getRank() {
            return this.rank;
        }

        public String getRankDate() {
            return this.rankDate;
        }

        public String getRankType() {
            return this.rankType;
        }

        public Integer getSteps() {
            return this.steps;
        }

        public Integer getTotalUsers() {
            return this.totalUsers;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public BestRank_UserLocation getUserLocation() {
            return this.userLocation;
        }

        public Object getUserName() {
            return this.userName;
        }

        public void setId(Integer num) {
            this.id = num;
        }

        public void setPlaceType(String str) {
            this.placeType = str;
        }

        public void setRank(Integer num) {
            this.rank = num;
        }

        public void setRankDate(String str) {
            this.rankDate = str;
        }

        public void setRankType(String str) {
            this.rankType = str;
        }

        public void setSteps(Integer num) {
            this.steps = num;
        }

        public void setTotalUsers(Integer num) {
            this.totalUsers = num;
        }

        public void setUserId(Integer num) {
            this.userId = num;
        }

        public void setUserLocation(BestRank_UserLocation bestRank_UserLocation) {
            this.userLocation = bestRank_UserLocation;
        }

        public void setUserName(Object obj) {
            this.userName = obj;
        }
    }

    /* loaded from: classes8.dex */
    public class BestRank_UserLocation {
        @SerializedName("administrativeArea")
        @Expose
        private String administrativeArea;
        @SerializedName(GeoCodingCriteria.POD_CITY)
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("locality")
        @Expose
        private String locality;
        @SerializedName("longitude")
        @Expose
        private Double longitude;

        public BestRank_UserLocation() {
        }

        public String getAdministrativeArea() {
            return this.administrativeArea;
        }

        public String getCity() {
            return this.city;
        }

        public String getCountry() {
            return this.country;
        }

        public Double getLatitude() {
            return this.latitude;
        }

        public String getLocality() {
            return this.locality;
        }

        public Double getLongitude() {
            return this.longitude;
        }

        public void setAdministrativeArea(String str) {
            this.administrativeArea = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setLatitude(Double d) {
            this.latitude = d;
        }

        public void setLocality(String str) {
            this.locality = str;
        }

        public void setLongitude(Double d) {
            this.longitude = d;
        }
    }

    /* loaded from: classes8.dex */
    public class CurrentRank {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("placeType")
        @Expose
        private String placeType;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("rankDate")
        @Expose
        private String rankDate;
        @SerializedName("rankType")
        @Expose
        private String rankType;
        @SerializedName("steps")
        @Expose
        private Integer steps;
        @SerializedName("totalUsers")
        @Expose
        private Integer totalUsers;
        @SerializedName(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID)
        @Expose
        private Integer userId;
        @SerializedName("userLocation")
        @Expose
        private CurrentRank_UserLocation userLocation;
        @SerializedName("userName")
        @Expose
        private Object userName;

        public CurrentRank() {
        }

        public Integer getId() {
            return this.id;
        }

        public String getPlaceType() {
            return this.placeType;
        }

        public Integer getRank() {
            return this.rank;
        }

        public String getRankDate() {
            return this.rankDate;
        }

        public String getRankType() {
            return this.rankType;
        }

        public Integer getSteps() {
            return this.steps;
        }

        public Integer getTotalUsers() {
            return this.totalUsers;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public CurrentRank_UserLocation getUserLocation() {
            return this.userLocation;
        }

        public Object getUserName() {
            return this.userName;
        }

        public void setId(Integer num) {
            this.id = num;
        }

        public void setPlaceType(String str) {
            this.placeType = str;
        }

        public void setRank(Integer num) {
            this.rank = num;
        }

        public void setRankDate(String str) {
            this.rankDate = str;
        }

        public void setRankType(String str) {
            this.rankType = str;
        }

        public void setSteps(Integer num) {
            this.steps = num;
        }

        public void setTotalUsers(Integer num) {
            this.totalUsers = num;
        }

        public void setUserId(Integer num) {
            this.userId = num;
        }

        public void setUserLocation(CurrentRank_UserLocation currentRank_UserLocation) {
            this.userLocation = currentRank_UserLocation;
        }

        public void setUserName(Object obj) {
            this.userName = obj;
        }
    }

    /* loaded from: classes8.dex */
    public class CurrentRank_UserLocation {
        @SerializedName("administrativeArea")
        @Expose
        private String administrativeArea;
        @SerializedName(GeoCodingCriteria.POD_CITY)
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("locality")
        @Expose
        private String locality;
        @SerializedName("longitude")
        @Expose
        private Double longitude;

        public CurrentRank_UserLocation() {
        }

        public String getAdministrativeArea() {
            return this.administrativeArea;
        }

        public String getCity() {
            return this.city;
        }

        public String getCountry() {
            return this.country;
        }

        public Double getLatitude() {
            return this.latitude;
        }

        public String getLocality() {
            return this.locality;
        }

        public Double getLongitude() {
            return this.longitude;
        }

        public void setAdministrativeArea(String str) {
            this.administrativeArea = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setLatitude(Double d) {
            this.latitude = d;
        }

        public void setLocality(String str) {
            this.locality = str;
        }

        public void setLongitude(Double d) {
            this.longitude = d;
        }
    }

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("ranks")
        @Expose
        private List<Rank_Data> ranks;

        public Data() {
        }

        public List<Rank_Data> getRanks() {
            return this.ranks;
        }

        public void setRanks(List<Rank_Data> list) {
            this.ranks = list;
        }
    }

    /* loaded from: classes8.dex */
    public class PreviousRank {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("placeType")
        @Expose
        private String placeType;
        @SerializedName("rank")
        @Expose
        private Integer rank;
        @SerializedName("rankDate")
        @Expose
        private String rankDate;
        @SerializedName("rankType")
        @Expose
        private String rankType;
        @SerializedName("steps")
        @Expose
        private Integer steps;
        @SerializedName("totalUsers")
        @Expose
        private Integer totalUsers;
        @SerializedName(com.coveiot.android.tappy.utils.Constants.END_USER_GLOBAL_ID)
        @Expose
        private Integer userId;
        @SerializedName("userLocation")
        @Expose
        private PreviousRank_UserLocation userLocation;
        @SerializedName("userName")
        @Expose
        private Object userName;

        public PreviousRank() {
        }

        public Integer getId() {
            return this.id;
        }

        public String getPlaceType() {
            return this.placeType;
        }

        public Integer getRank() {
            return this.rank;
        }

        public String getRankDate() {
            return this.rankDate;
        }

        public String getRankType() {
            return this.rankType;
        }

        public Integer getSteps() {
            return this.steps;
        }

        public Integer getTotalUsers() {
            return this.totalUsers;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public PreviousRank_UserLocation getUserLocation() {
            return this.userLocation;
        }

        public Object getUserName() {
            return this.userName;
        }

        public void setId(Integer num) {
            this.id = num;
        }

        public void setPlaceType(String str) {
            this.placeType = str;
        }

        public void setRank(Integer num) {
            this.rank = num;
        }

        public void setRankDate(String str) {
            this.rankDate = str;
        }

        public void setRankType(String str) {
            this.rankType = str;
        }

        public void setSteps(Integer num) {
            this.steps = num;
        }

        public void setTotalUsers(Integer num) {
            this.totalUsers = num;
        }

        public void setUserId(Integer num) {
            this.userId = num;
        }

        public void setUserLocation(PreviousRank_UserLocation previousRank_UserLocation) {
            this.userLocation = previousRank_UserLocation;
        }

        public void setUserName(Object obj) {
            this.userName = obj;
        }
    }

    /* loaded from: classes8.dex */
    public class PreviousRank_UserLocation {
        @SerializedName("administrativeArea")
        @Expose
        private String administrativeArea;
        @SerializedName(GeoCodingCriteria.POD_CITY)
        @Expose
        private String city;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("locality")
        @Expose
        private String locality;
        @SerializedName("longitude")
        @Expose
        private Double longitude;

        public PreviousRank_UserLocation() {
        }

        public String getAdministrativeArea() {
            return this.administrativeArea;
        }

        public String getCity() {
            return this.city;
        }

        public String getCountry() {
            return this.country;
        }

        public Double getLatitude() {
            return this.latitude;
        }

        public String getLocality() {
            return this.locality;
        }

        public Double getLongitude() {
            return this.longitude;
        }

        public void setAdministrativeArea(String str) {
            this.administrativeArea = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setLatitude(Double d) {
            this.latitude = d;
        }

        public void setLocality(String str) {
            this.locality = str;
        }

        public void setLongitude(Double d) {
            this.longitude = d;
        }
    }

    /* loaded from: classes8.dex */
    public class Rank_Data {
        @SerializedName("bestRank")
        @Expose
        private BestRank bestRank;
        @SerializedName("placeType")
        @Expose
        private String placeType;
        @SerializedName("previousRank")
        @Expose
        private PreviousRank previousRank;
        @SerializedName("rank")
        @Expose
        private CurrentRank rank;

        public Rank_Data() {
        }

        public BestRank getBestRank() {
            return this.bestRank;
        }

        public String getPlaceType() {
            return this.placeType;
        }

        public PreviousRank getPreviousRank() {
            return this.previousRank;
        }

        public CurrentRank getRank() {
            return this.rank;
        }

        public void setBestRank(BestRank bestRank) {
            this.bestRank = bestRank;
        }

        public void setPlaceType(String str) {
            this.placeType = str;
        }

        public void setPreviousRank(PreviousRank previousRank) {
            this.previousRank = previousRank;
        }

        public void setRank(CurrentRank currentRank) {
            this.rank = currentRank;
        }
    }

    public Data getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
