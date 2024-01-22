package com.coveiot.coveaccess.leaderboard.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes8.dex */
public final class MyBadgesModel implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        private List<BadgesBean> badges;

        /* loaded from: classes8.dex */
        public static class BadgesBean implements Serializable {
            private String badgeDescription;
            private int badgeId;
            private String badgeImageUrl;
            private List<BadgeLevelsBean> badgeLevels;
            private String badgeName;
            private String categoryId;
            private String createdDate;

            /* loaded from: classes8.dex */
            public static class BadgeLevelsBean implements Serializable {
                private String createdDate;
                private int levelCriteria;
                private String levelDescription;
                private int levelId;
                private String levelImageUrl;
                private String levelName;
                private int levelWeight;
                private int nextLevelCriteria;
                private String obtainedDate;
                private int userCriteria;

                public String getCreatedDate() {
                    return this.createdDate;
                }

                public int getLevelCriteria() {
                    return this.levelCriteria;
                }

                public String getLevelDescription() {
                    return this.levelDescription;
                }

                public int getLevelId() {
                    return this.levelId;
                }

                public String getLevelImageUrl() {
                    return this.levelImageUrl;
                }

                public String getLevelName() {
                    return this.levelName;
                }

                public int getLevelWeight() {
                    return this.levelWeight;
                }

                public int getNextLevelCriteria() {
                    return this.nextLevelCriteria;
                }

                public String getObtainedDate() {
                    return this.obtainedDate;
                }

                public int getUserCriteria() {
                    return this.userCriteria;
                }

                public void setCreatedDate(String str) {
                    this.createdDate = str;
                }

                public void setLevelCriteria(int i) {
                    this.levelCriteria = i;
                }

                public void setLevelDescription(String str) {
                    this.levelDescription = str;
                }

                public void setLevelId(int i) {
                    this.levelId = i;
                }

                public void setLevelImageUrl(String str) {
                    this.levelImageUrl = str;
                }

                public void setLevelName(String str) {
                    this.levelName = str;
                }

                public void setLevelWeight(int i) {
                    this.levelWeight = i;
                }

                public void setNextLevelCriteria(int i) {
                    this.nextLevelCriteria = i;
                }

                public void setObtainedDate(String str) {
                    this.obtainedDate = str;
                }

                public void setUserCriteria(int i) {
                    this.userCriteria = i;
                }
            }

            public String getBadgeDescription() {
                return this.badgeDescription;
            }

            public int getBadgeId() {
                return this.badgeId;
            }

            public String getBadgeImageUrl() {
                return this.badgeImageUrl;
            }

            public List<BadgeLevelsBean> getBadgeLevels() {
                return this.badgeLevels;
            }

            public String getBadgeName() {
                return this.badgeName;
            }

            public String getCategoryId() {
                return this.categoryId;
            }

            public String getCreatedDate() {
                return this.createdDate;
            }

            public void setBadgeDescription(String str) {
                this.badgeDescription = str;
            }

            public void setBadgeId(int i) {
                this.badgeId = i;
            }

            public void setBadgeImageUrl(String str) {
                this.badgeImageUrl = str;
            }

            public void setBadgeLevels(List<BadgeLevelsBean> list) {
                this.badgeLevels = list;
            }

            public void setBadgeName(String str) {
                this.badgeName = str;
            }

            public void setCategoryId(String str) {
                this.categoryId = str;
            }

            public void setCreatedDate(String str) {
                this.createdDate = str;
            }
        }

        public List<BadgesBean> getBadges() {
            return this.badges;
        }

        public void setBadges(List<BadgesBean> list) {
            this.badges = list;
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
