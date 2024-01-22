package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import java.util.List;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
/* loaded from: classes8.dex */
public class SWatchFaceList {
    @SerializedName("data")
    private DataBean data;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName(FirebaseAnalytics.Param.ITEMS)
        private List<ItemsBean> items;
        @SerializedName("itemsPerPage")
        private Integer itemsPerPage;
        @SerializedName("newItems")
        private Integer newItems;
        @SerializedName("pageIndex")
        private Integer pageIndex;
        @SerializedName("totalItems")
        private Integer totalItems;

        /* loaded from: classes8.dex */
        public static class ItemsBean {
            @SerializedName("bgImageUrl")
            @Expose
            private String bgImageUrl;
            @SerializedName("downloadCount")
            private Integer downloadCount;
            @SerializedName("downloadUrl")
            private String downloadUrl;
            @SerializedName("editUrl")
            private String editUrl;
            @SerializedName("faceId")
            private String faceId;
            @SerializedName("faceType")
            private String faceType;
            @SerializedName("favourite")
            private Boolean favourite;
            @SerializedName("favouriteCount")
            private Integer favouriteCount;
            @SerializedName("fileMd5Hash")
            private String fileMd5Hash;
            @SerializedName("fileType")
            private String fileType;
            @SerializedName("newWatchface")
            private Boolean newWatchface;
            @SerializedName("previewImageUrl")
            private String previewImageUrl;
            @SerializedName("seqNumber")
            private Integer seqNumber;
            @SerializedName(Constants.KEY_TAGS)
            private List<String> tags;
            @SerializedName("template")
            @Expose
            private Template template;
            @SerializedName("title")
            private String title;
            @SerializedName("uid")
            private String uid;

            /* loaded from: classes8.dex */
            public class Template {
                @SerializedName("dithering")
                @Expose
                private Boolean dithering;
                @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
                @Expose
                private Integer height;
                @SerializedName(Constants.KEY_RADIUS)
                @Expose
                private Integer radius;
                @SerializedName("widgets")
                @Expose
                private List<Widget> widgets;
                @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
                @Expose
                private Integer width;

                /* loaded from: classes8.dex */
                public class Widget {
                    @SerializedName("color")
                    @Expose
                    private Object color;
                    @SerializedName("font")
                    @Expose
                    private String font;
                    @SerializedName("fontSize")
                    @Expose
                    private Integer fontSize;
                    @SerializedName("format")
                    @Expose
                    private String format;
                    @SerializedName("glifDimension")
                    @Expose
                    private GlifDimension glifDimension;
                    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
                    @Expose
                    private Integer height;
                    @SerializedName("iconColor")
                    @Expose
                    private Object iconColor;
                    @SerializedName("iconPosition")
                    @Expose
                    private Object iconPosition;
                    @SerializedName("Meridian")
                    @Expose
                    private Boolean meridian;
                    @SerializedName("MeridianDimension")
                    @Expose
                    private MeridianDimension meridianDimension;
                    @SerializedName("MeridianFontColor")
                    @Expose
                    private String meridianFontColor;
                    @SerializedName("MeridianFontSize")
                    @Expose
                    private Integer meridianFontSize;
                    @SerializedName("MeridianPosition")
                    @Expose
                    private Object meridianPosition;
                    @SerializedName("src")
                    @Expose
                    private Object src;
                    @SerializedName("type")
                    @Expose
                    private String type;
                    @SerializedName("widget_id")
                    @Expose
                    private String widgetId;
                    @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
                    @Expose
                    private Integer width;
                    @SerializedName("x")
                    @Expose
                    private Float x;
                    @SerializedName(EllipticCurveJsonWebKey.Y_MEMBER_NAME)
                    @Expose
                    private Float y;

                    /* loaded from: classes8.dex */
                    public class GlifDimension {
                        @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
                        @Expose
                        private Float height;
                        @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
                        @Expose
                        private Float width;

                        public GlifDimension() {
                        }

                        public Float getHeight() {
                            return this.height;
                        }

                        public Float getWidth() {
                            return this.width;
                        }

                        public void setHeight(Float f) {
                            this.height = f;
                        }

                        public void setWidth(Float f) {
                            this.width = f;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public class MeridianDimension {
                        @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
                        @Expose
                        private Integer height;
                        @SerializedName(Property.ICON_TEXT_FIT_WIDTH)
                        @Expose
                        private Integer width;

                        public MeridianDimension() {
                        }

                        public Integer getHeight() {
                            return this.height;
                        }

                        public Integer getWidth() {
                            return this.width;
                        }

                        public void setHeight(Integer num) {
                            this.height = num;
                        }

                        public void setWidth(Integer num) {
                            this.width = num;
                        }
                    }

                    public Widget() {
                    }

                    public Object getColor() {
                        return this.color;
                    }

                    public String getFont() {
                        return this.font;
                    }

                    public Integer getFontSize() {
                        return this.fontSize;
                    }

                    public String getFormat() {
                        return this.format;
                    }

                    public GlifDimension getGlifDimension() {
                        return this.glifDimension;
                    }

                    public Integer getHeight() {
                        return this.height;
                    }

                    public Object getIconColor() {
                        return this.iconColor;
                    }

                    public Object getIconPosition() {
                        return this.iconPosition;
                    }

                    public MeridianDimension getMeridianDimension() {
                        return this.meridianDimension;
                    }

                    public String getMeridianFontColor() {
                        return this.meridianFontColor;
                    }

                    public Integer getMeridianFontSize() {
                        return this.meridianFontSize;
                    }

                    public Object getMeridianPosition() {
                        return this.meridianPosition;
                    }

                    public Object getSrc() {
                        return this.src;
                    }

                    public String getType() {
                        return this.type;
                    }

                    public String getWidgetId() {
                        return this.widgetId;
                    }

                    public Integer getWidth() {
                        return this.width;
                    }

                    public Float getX() {
                        return this.x;
                    }

                    public Float getY() {
                        return this.y;
                    }

                    public Boolean isMeridian() {
                        return this.meridian;
                    }

                    public void setColor(String str) {
                        this.color = str;
                    }

                    public void setFont(String str) {
                        this.font = str;
                    }

                    public void setFontSize(Integer num) {
                        this.fontSize = num;
                    }

                    public void setFormat(String str) {
                        this.format = str;
                    }

                    public void setGlifDimension(GlifDimension glifDimension) {
                        this.glifDimension = glifDimension;
                    }

                    public void setHeight(Integer num) {
                        this.height = num;
                    }

                    public void setIconColor(Object obj) {
                        this.iconColor = obj;
                    }

                    public void setIconPosition(Object obj) {
                        this.iconPosition = obj;
                    }

                    public void setMeridian(Boolean bool) {
                        this.meridian = bool;
                    }

                    public void setMeridianDimension(MeridianDimension meridianDimension) {
                        this.meridianDimension = meridianDimension;
                    }

                    public void setMeridianFontColor(String str) {
                        this.meridianFontColor = str;
                    }

                    public void setMeridianFontSize(Integer num) {
                        this.meridianFontSize = num;
                    }

                    public void setMeridianPosition(Object obj) {
                        this.meridianPosition = obj;
                    }

                    public void setSrc(Object obj) {
                        this.src = obj;
                    }

                    public void setType(String str) {
                        this.type = str;
                    }

                    public void setWidgetId(String str) {
                        this.widgetId = str;
                    }

                    public void setWidth(Integer num) {
                        this.width = num;
                    }

                    public void setX(Float f) {
                        this.x = f;
                    }

                    public void setY(Float f) {
                        this.y = f;
                    }
                }

                public Template() {
                }

                public Integer getHeight() {
                    return this.height;
                }

                public Integer getRadius() {
                    return this.radius;
                }

                public List<Widget> getWidgets() {
                    return this.widgets;
                }

                public Integer getWidth() {
                    return this.width;
                }

                public Boolean isDithering() {
                    return this.dithering;
                }

                public void setDithering(Boolean bool) {
                    this.dithering = bool;
                }

                public void setHeight(Integer num) {
                    this.height = num;
                }

                public void setRadius(Integer num) {
                    this.radius = num;
                }

                public void setWidgets(List<Widget> list) {
                    this.widgets = list;
                }

                public void setWidth(Integer num) {
                    this.width = num;
                }
            }

            public String getBgImageUrl() {
                return this.bgImageUrl;
            }

            public Integer getDownloadCount() {
                return this.downloadCount;
            }

            public String getDownloadUrl() {
                return this.downloadUrl;
            }

            public String getEditUrl() {
                return this.editUrl;
            }

            public String getFaceId() {
                return this.faceId;
            }

            public String getFaceType() {
                return this.faceType;
            }

            public Integer getFavouriteCount() {
                return this.favouriteCount;
            }

            public String getFileMd5Hash() {
                return this.fileMd5Hash;
            }

            public String getFileType() {
                return this.fileType;
            }

            public Boolean getNewWatchface() {
                return this.newWatchface;
            }

            public String getPreviewImageUrl() {
                return this.previewImageUrl;
            }

            public Integer getSeqNumber() {
                return this.seqNumber;
            }

            public List<String> getTags() {
                return this.tags;
            }

            public Template getTemplate() {
                return this.template;
            }

            public String getTitle() {
                return this.title;
            }

            public String getUid() {
                return this.uid;
            }

            public Boolean isFavourite() {
                return this.favourite;
            }

            public void setBgImageUrl(String str) {
                this.bgImageUrl = str;
            }

            public void setDownloadCount(Integer num) {
                this.downloadCount = num;
            }

            public void setDownloadUrl(String str) {
                this.downloadUrl = str;
            }

            public void setEditUrl(String str) {
                this.editUrl = str;
            }

            public void setFaceId(String str) {
                this.faceId = str;
            }

            public void setFaceType(String str) {
                this.faceType = str;
            }

            public void setFavourite(Boolean bool) {
                this.favourite = bool;
            }

            public void setFavouriteCount(Integer num) {
                this.favouriteCount = num;
            }

            public void setFileMd5Hash(String str) {
                this.fileMd5Hash = str;
            }

            public void setFileType(String str) {
                this.fileType = str;
            }

            public void setNewWatchface(Boolean bool) {
                this.newWatchface = bool;
            }

            public void setPreviewImageUrl(String str) {
                this.previewImageUrl = str;
            }

            public void setSeqNumber(Integer num) {
                this.seqNumber = num;
            }

            public void setTags(List<String> list) {
                this.tags = list;
            }

            public void setTemplate(Template template) {
                this.template = template;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public void setUid(String str) {
                this.uid = str;
            }
        }

        public List<ItemsBean> getItems() {
            return this.items;
        }

        public Integer getItemsPerPage() {
            return this.itemsPerPage;
        }

        public Integer getNewItems() {
            return this.newItems;
        }

        public Integer getPageIndex() {
            return this.pageIndex;
        }

        public Integer getTotalItems() {
            return this.totalItems;
        }

        public void setItems(List<ItemsBean> list) {
            this.items = list;
        }

        public void setItemsPerPage(Integer num) {
            this.itemsPerPage = num;
        }

        public void setNewItems(Integer num) {
            this.newItems = num;
        }

        public void setPageIndex(Integer num) {
            this.pageIndex = num;
        }

        public void setTotalItems(Integer num) {
            this.totalItems = num;
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
