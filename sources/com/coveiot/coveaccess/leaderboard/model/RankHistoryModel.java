package com.coveiot.coveaccess.leaderboard.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class RankHistoryModel implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        private List<RanksBean> ranks;

        /* loaded from: classes8.dex */
        public static class RanksBean implements Serializable {
            private String dpUrl;
            private int id;
            private String placeType;
            private Integer previousRank;
            private int rank;
            private String rankDate;
            private String rankType;
            private int steps;
            private int totalUsers;
            private int userId;
            private UserLocationBean userLocation;
            private Object userName;

            /* loaded from: classes8.dex */
            public static class UserLocationBean implements Serializable {
                private String administrativeArea;
                private String city;
                private String country;
                private double latitude;
                private String locality;
                private double longitude;

                public String getAdministrativeArea() {
                    return this.administrativeArea;
                }

                public String getCity() {
                    return this.city;
                }

                public String getCountry() {
                    return this.country;
                }

                public double getLatitude() {
                    return this.latitude;
                }

                public String getLocality() {
                    return this.locality;
                }

                public double getLongitude() {
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

                public void setLatitude(double d) {
                    this.latitude = d;
                }

                public void setLocality(String str) {
                    this.locality = str;
                }

                public void setLongitude(double d) {
                    this.longitude = d;
                }
            }

            public String getDpUrl() {
                return this.dpUrl;
            }

            public int getId() {
                return this.id;
            }

            public String getPlaceType() {
                return this.placeType;
            }

            public Integer getPreviousRank() {
                return this.previousRank;
            }

            public int getRank() {
                return this.rank;
            }

            public String getRankDate() {
                return this.rankDate;
            }

            public String getRankType() {
                return this.rankType;
            }

            public int getSteps() {
                return this.steps;
            }

            public int getTotalUsers() {
                return this.totalUsers;
            }

            public int getUserId() {
                return this.userId;
            }

            public UserLocationBean getUserLocation() {
                return this.userLocation;
            }

            public Object getUserName() {
                return this.userName;
            }

            public void setDpUrl(String str) {
                this.dpUrl = str;
            }

            public void setId(int i) {
                this.id = i;
            }

            public void setPlaceType(String str) {
                this.placeType = str;
            }

            public void setPreviousRank(Integer num) {
                this.previousRank = num;
            }

            public void setRank(int i) {
                this.rank = i;
            }

            public void setRankDate(String str) {
                this.rankDate = str;
            }

            public void setRankType(String str) {
                this.rankType = str;
            }

            public void setSteps(int i) {
                this.steps = i;
            }

            public void setTotalUsers(int i) {
                this.totalUsers = i;
            }

            public void setUserId(int i) {
                this.userId = i;
            }

            public void setUserLocation(UserLocationBean userLocationBean) {
                this.userLocation = userLocationBean;
            }

            public void setUserName(Object obj) {
                this.userName = obj;
            }
        }

        public List<RanksBean> getRanks() {
            return this.ranks;
        }

        public void setRanks(List<RanksBean> list) {
            this.ranks = list;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
