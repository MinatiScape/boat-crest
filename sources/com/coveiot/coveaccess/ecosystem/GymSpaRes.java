package com.coveiot.coveaccess.ecosystem;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.List;
/* loaded from: classes8.dex */
public class GymSpaRes {
    @SerializedName(FirebaseAnalytics.Param.ITEMS)

    /* renamed from: a  reason: collision with root package name */
    private List<Item> f6488a = null;
    @SerializedName("itemsPerPage")
    private Integer b;
    @SerializedName("pageIndex")
    private Integer c;

    /* loaded from: classes8.dex */
    public static class Item {
        @SerializedName("openingHours")

        /* renamed from: a  reason: collision with root package name */
        private OpeningHours f6489a;
        @SerializedName("id")
        private String b;
        @SerializedName("placeId")
        private String c;
        @SerializedName(FirebaseAnalytics.Param.LOCATION)
        private Location d;
        @SerializedName(Constants.KEY_ICON)
        private String e;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String f;
        @SerializedName("rating")
        private float g;
        @SerializedName("types")
        private List<String> h = null;
        @SerializedName("photos")
        private List<Photo> i = null;
        @SerializedName("formattedPhoneNumber")
        private String j;
        @SerializedName("internationalPhoneNumber")
        private String k;
        @SerializedName("website")
        private String l;
        @SerializedName("vicinity")
        private String m;

        /* loaded from: classes8.dex */
        public static class Location {

            /* renamed from: a  reason: collision with root package name */
            public String f6490a;
            @SerializedName("coordinates")
            private List<Double> b = null;

            public List<Double> getCoordinates() {
                return this.b;
            }

            public String getType() {
                return this.f6490a;
            }

            public void setCoordinates(List<Double> list) {
                this.b = list;
            }

            public void setType(String str) {
                this.f6490a = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class OpeningHours {
            @SerializedName("openNow")

            /* renamed from: a  reason: collision with root package name */
            private Boolean f6491a;
            @SerializedName("periods")
            private List<Period> b = null;
            @SerializedName("weekdayText")
            private List<String> c = null;
            @SerializedName("permanentlyClosed")
            private Object d;

            /* loaded from: classes8.dex */
            public static class Period {
                @SerializedName("open")

                /* renamed from: a  reason: collision with root package name */
                private Open f6492a;
                @SerializedName(Constants.KEY_HIDE_CLOSE)
                private Close b;

                /* loaded from: classes8.dex */
                public static class Close {
                    @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)

                    /* renamed from: a  reason: collision with root package name */
                    private String f6493a;
                    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
                    private String b;

                    public String getDay() {
                        return this.f6493a;
                    }

                    public String getTime() {
                        return this.b;
                    }

                    public void setDay(String str) {
                        this.f6493a = str;
                    }

                    public void setTime(String str) {
                        this.b = str;
                    }
                }

                /* loaded from: classes8.dex */
                public static class Open {
                    @SerializedName(WeatherCriteria.UNIT_TYPE_DAY)

                    /* renamed from: a  reason: collision with root package name */
                    private String f6494a;
                    @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
                    private String b;

                    public String getDay() {
                        return this.f6494a;
                    }

                    public String getTime() {
                        return this.b;
                    }

                    public void setDay(String str) {
                        this.f6494a = str;
                    }

                    public void setTime(String str) {
                        this.b = str;
                    }
                }

                public Close getClose() {
                    return this.b;
                }

                public Open getOpen() {
                    return this.f6492a;
                }

                public void setClose(Close close) {
                    this.b = close;
                }

                public void setOpen(Open open) {
                    this.f6492a = open;
                }
            }

            public Boolean getOpenNow() {
                return this.f6491a;
            }

            public List<Period> getPeriods() {
                return this.b;
            }

            public Object getPermanentlyClosed() {
                return this.d;
            }

            public List<String> getWeekdayText() {
                return this.c;
            }

            public void setOpenNow(Boolean bool) {
                this.f6491a = bool;
            }

            public void setPeriods(List<Period> list) {
                this.b = list;
            }

            public void setPermanentlyClosed(Object obj) {
                this.d = obj;
            }

            public void setWeekdayText(List<String> list) {
                this.c = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class Photo {
            @SerializedName("photoReference")

            /* renamed from: a  reason: collision with root package name */
            private String f6495a;
            @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
            private Integer b;
            @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
            private Integer c;
            @SerializedName("htmlAttributions")
            private List<String> d = null;
            @SerializedName("downloaded")
            private Boolean e;
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
            private Object f;

            public Boolean getDownloaded() {
                return this.e;
            }

            public Integer getHeight() {
                return this.b;
            }

            public List<String> getHtmlAttributions() {
                return this.d;
            }

            public Object getName() {
                return this.f;
            }

            public String getPhotoReference() {
                return this.f6495a;
            }

            public Integer getWidth() {
                return this.c;
            }

            public void setDownloaded(Boolean bool) {
                this.e = bool;
            }

            public void setHeight(Integer num) {
                this.b = num;
            }

            public void setHtmlAttributions(List<String> list) {
                this.d = list;
            }

            public void setName(Object obj) {
                this.f = obj;
            }

            public void setPhotoReference(String str) {
                this.f6495a = str;
            }

            public void setWidth(Integer num) {
                this.c = num;
            }
        }

        public String getFormattedPhoneNumber() {
            return this.j;
        }

        public String getIcon() {
            return this.e;
        }

        public String getId() {
            return this.b;
        }

        public String getInternationalPhoneNumber() {
            return this.k;
        }

        public Location getLocation() {
            return this.d;
        }

        public String getName() {
            return this.f;
        }

        public OpeningHours getOpeningHours() {
            return this.f6489a;
        }

        public List<Photo> getPhotos() {
            return this.i;
        }

        public String getPlaceId() {
            return this.c;
        }

        public float getRating() {
            return this.g;
        }

        public List<String> getTypes() {
            return this.h;
        }

        public String getVicinity() {
            return this.m;
        }

        public String getWebsite() {
            return this.l;
        }

        public void setFormattedPhoneNumber(String str) {
            this.j = str;
        }

        public void setIcon(String str) {
            this.e = str;
        }

        public void setId(String str) {
            this.b = str;
        }

        public void setInternationalPhoneNumber(String str) {
            this.k = str;
        }

        public void setLocation(Location location) {
            this.d = location;
        }

        public void setName(String str) {
            this.f = str;
        }

        public void setOpeningHours(OpeningHours openingHours) {
            this.f6489a = openingHours;
        }

        public void setPhotos(List<Photo> list) {
            this.i = list;
        }

        public void setPlaceId(String str) {
            this.c = str;
        }

        public void setRating(float f) {
            this.g = f;
        }

        public void setTypes(List<String> list) {
            this.h = list;
        }

        public void setVicinity(String str) {
            this.m = str;
        }

        public void setWebsite(String str) {
            this.l = str;
        }
    }

    public List<Item> getItems() {
        return this.f6488a;
    }

    public Integer getItemsPerPage() {
        return this.b;
    }

    public Integer getPageIndex() {
        return this.c;
    }

    public void setItems(List<Item> list) {
        this.f6488a = list;
    }

    public void setItemsPerPage(Integer num) {
        this.b = num;
    }

    public void setPageIndex(Integer num) {
        this.c = num;
    }
}
