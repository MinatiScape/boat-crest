package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class SGymSpaResponse {
    @SerializedName("data")
    @Expose
    private GymSpaResponseModel data;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    /* loaded from: classes8.dex */
    public static class GymSpaResponseModel {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        private List<Item> items = null;
        @SerializedName("itemsPerPage")
        private Integer itemsPerPage;
        @SerializedName("pageIndex")
        private Integer pageIndex;

        /* loaded from: classes8.dex */
        public static class Item {
            @SerializedName("formattedPhoneNumber")
            private String formattedPhoneNumber;
            @SerializedName(Constants.KEY_ICON)
            private String icon;
            @SerializedName("id")
            private String id;
            @SerializedName("internationalPhoneNumber")
            private String internationalPhoneNumber;
            @SerializedName(FirebaseAnalytics.Param.LOCATION)
            private Location location;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private String name;
            @SerializedName("openingHours")
            private OpeningHours openingHours;
            @SerializedName("placeId")
            private String placeId;
            @SerializedName("rating")
            private float rating;
            @SerializedName("vicinity")
            private String vicinity;
            @SerializedName("website")
            private String website;
            @SerializedName("types")
            private List<String> types = null;
            @SerializedName("photos")
            private List<Photo> photos = null;

            /* loaded from: classes8.dex */
            public static class Location {
                @SerializedName("coordinates")
                private List<Double> coordinates = null;
                private String type;

                public List<Double> getCoordinates() {
                    return this.coordinates;
                }

                public String getType() {
                    return this.type;
                }

                public void setCoordinates(List<Double> list) {
                    this.coordinates = list;
                }

                public void setType(String str) {
                    this.type = str;
                }
            }

            /* loaded from: classes8.dex */
            public static class OpeningHours {
                @SerializedName("openNow")
                private Boolean openNow;
                @SerializedName("permanentlyClosed")
                private Object permanentlyClosed;
                @SerializedName("periods")
                private List<Period> periods = null;
                @SerializedName("weekdayText")
                private List<String> weekdayText = null;

                /* loaded from: classes8.dex */
                public static class Period {
                    @SerializedName(Constants.KEY_HIDE_CLOSE)
                    private Close close;
                    @SerializedName("open")
                    private Open open;

                    /* loaded from: classes8.dex */
                    public static class Close {
                        @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)
                        private String day;
                        @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
                        private String time;

                        public String getDay() {
                            return this.day;
                        }

                        public String getTime() {
                            return this.time;
                        }

                        public void setDay(String str) {
                            this.day = str;
                        }

                        public void setTime(String str) {
                            this.time = str;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public static class Open {
                        @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)
                        private String day;
                        @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
                        private String time;

                        public String getDay() {
                            return this.day;
                        }

                        public String getTime() {
                            return this.time;
                        }

                        public void setDay(String str) {
                            this.day = str;
                        }

                        public void setTime(String str) {
                            this.time = str;
                        }
                    }

                    public Close getClose() {
                        return this.close;
                    }

                    public Open getOpen() {
                        return this.open;
                    }

                    public void setClose(Close close) {
                        this.close = close;
                    }

                    public void setOpen(Open open) {
                        this.open = open;
                    }
                }

                public Boolean getOpenNow() {
                    return this.openNow;
                }

                public List<Period> getPeriods() {
                    return this.periods;
                }

                public Object getPermanentlyClosed() {
                    return this.permanentlyClosed;
                }

                public List<String> getWeekdayText() {
                    return this.weekdayText;
                }

                public void setOpenNow(Boolean bool) {
                    this.openNow = bool;
                }

                public void setPeriods(List<Period> list) {
                    this.periods = list;
                }

                public void setPermanentlyClosed(Object obj) {
                    this.permanentlyClosed = obj;
                }

                public void setWeekdayText(List<String> list) {
                    this.weekdayText = list;
                }
            }

            /* loaded from: classes8.dex */
            public static class Photo {
                @SerializedName("downloaded")
                private Boolean downloaded;
                @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
                private Integer height;
                @SerializedName("htmlAttributions")
                private List<String> htmlAttributions = null;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                private Object name;
                @SerializedName("photoReference")
                private String photoReference;
                @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
                private Integer width;

                public Boolean getDownloaded() {
                    return this.downloaded;
                }

                public Integer getHeight() {
                    return this.height;
                }

                public List<String> getHtmlAttributions() {
                    return this.htmlAttributions;
                }

                public Object getName() {
                    return this.name;
                }

                public String getPhotoReference() {
                    return this.photoReference;
                }

                public Integer getWidth() {
                    return this.width;
                }

                public void setDownloaded(Boolean bool) {
                    this.downloaded = bool;
                }

                public void setHeight(Integer num) {
                    this.height = num;
                }

                public void setHtmlAttributions(List<String> list) {
                    this.htmlAttributions = list;
                }

                public void setName(Object obj) {
                    this.name = obj;
                }

                public void setPhotoReference(String str) {
                    this.photoReference = str;
                }

                public void setWidth(Integer num) {
                    this.width = num;
                }
            }

            public String getFormattedPhoneNumber() {
                return this.formattedPhoneNumber;
            }

            public String getIcon() {
                return this.icon;
            }

            public String getId() {
                return this.id;
            }

            public String getInternationalPhoneNumber() {
                return this.internationalPhoneNumber;
            }

            public Location getLocation() {
                return this.location;
            }

            public String getName() {
                return this.name;
            }

            public OpeningHours getOpeningHours() {
                return this.openingHours;
            }

            public List<Photo> getPhotos() {
                return this.photos;
            }

            public String getPlaceId() {
                return this.placeId;
            }

            public float getRating() {
                return this.rating;
            }

            public List<String> getTypes() {
                return this.types;
            }

            public String getVicinity() {
                return this.vicinity;
            }

            public String getWebsite() {
                return this.website;
            }

            public void setFormattedPhoneNumber(String str) {
                this.formattedPhoneNumber = str;
            }

            public void setIcon(String str) {
                this.icon = str;
            }

            public void setId(String str) {
                this.id = str;
            }

            public void setInternationalPhoneNumber(String str) {
                this.internationalPhoneNumber = str;
            }

            public void setLocation(Location location) {
                this.location = location;
            }

            public void setName(String str) {
                this.name = str;
            }

            public void setOpeningHours(OpeningHours openingHours) {
                this.openingHours = openingHours;
            }

            public void setPhotos(List<Photo> list) {
                this.photos = list;
            }

            public void setPlaceId(String str) {
                this.placeId = str;
            }

            public void setRating(float f) {
                this.rating = f;
            }

            public void setTypes(List<String> list) {
                this.types = list;
            }

            public void setVicinity(String str) {
                this.vicinity = str;
            }

            public void setWebsite(String str) {
                this.website = str;
            }
        }

        public List<Item> getItems() {
            return this.items;
        }

        public Integer getItemsPerPage() {
            return this.itemsPerPage;
        }

        public Integer getPageIndex() {
            return this.pageIndex;
        }

        public void setItems(List<Item> list) {
            this.items = list;
        }

        public void setItemsPerPage(Integer num) {
            this.itemsPerPage = num;
        }

        public void setPageIndex(Integer num) {
            this.pageIndex = num;
        }
    }

    public GymSpaResponseModel getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(GymSpaResponseModel gymSpaResponseModel) {
        this.data = gymSpaResponseModel;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
