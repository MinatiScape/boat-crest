package com.coveiot.coveaccess.model.server;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.polidea.rxandroidble2.ClientComponent;
import java.util.List;
/* loaded from: classes8.dex */
public class SRemoteConfigResponse {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("alexa")
        private AlexaBean alexaBean;
        @SerializedName("apiFrequency")
        private ApiFrequencyBean apiFrequency;
        @SerializedName("coins")
        private CoinsBean coins;
        @SerializedName("dataPull")
        private DataPull dataPull;
        @SerializedName("docOnline")
        private DocOnline docOnline;
        private FitnessPlanBean fitnessPlan;
        @SerializedName("guardian")
        private GuardianBean guardian;
        @SerializedName("healthStatus")
        private HealthStatusBean healthStatus;
        @SerializedName("legal")
        private LegalBean legalBean;
        @SerializedName("nearbyTracking")
        private NearbyTrackingBean nearbyTracking;
        @SerializedName("openWeatherMap")
        private OpenWeatherMap openWeatherMap;
        @SerializedName("openWeatherMapAPiKey")
        private String openWeatherMapAPiKey;
        private PayBean pay;
        @SerializedName("refs")
        private RefsBean refsBean;
        @SerializedName("respiratoryRate")
        private RespiratoryRateBean respiratoryRate;
        @SerializedName("sensAi")
        private SensAIBean sensAI;
        private SessionBean session;
        @SerializedName("spo2Aiml")
        private Spo2AimlBean spo2Aiml;
        @SerializedName(DeviceKey.Stress)
        private Stress stress;
        @SerializedName("support")
        private SupportBean supportBean;
        private TimelineBean timeline;
        @SerializedName("titanWeatherApi")
        private TitanWeather titanWeatherApi;
        @SerializedName("trigger")
        private TriggerBean trigger;
        private List<UiBean> ui;
        @SerializedName("watchface")
        private WatchFaceBean watchface;

        /* loaded from: classes8.dex */
        public static class AlexaBean {
            @SerializedName("locales")
            private List<Locale> locales;
            @SerializedName("lwaFallbackUrl")
            private String lwaFallbackUrl;
            @SerializedName("nativeAppUrl")
            private String nativeAppUrl;

            /* loaded from: classes8.dex */
            public static class Locale {
                @SerializedName("examplePhrases")
                private List<String> examplePhrases;
                @SerializedName("helpUrl")
                private String helpUrl;
                @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
                private String label;
                @SerializedName("locale")
                private String locale;
                @SerializedName("primary")
                private boolean primary;

                public List<String> getExamplePhrases() {
                    return this.examplePhrases;
                }

                public String getHelpUrl() {
                    return this.helpUrl;
                }

                public String getLabel() {
                    return this.label;
                }

                public String getLocale() {
                    return this.locale;
                }

                public boolean isPrimary() {
                    return this.primary;
                }

                public void setExamplePhrases(List<String> list) {
                    this.examplePhrases = list;
                }

                public void setHelpUrl(String str) {
                    this.helpUrl = str;
                }

                public void setLabel(String str) {
                    this.label = str;
                }

                public void setLocale(String str) {
                    this.locale = str;
                }

                public void setPrimary(boolean z) {
                    this.primary = z;
                }
            }

            public List<Locale> getLocales() {
                return this.locales;
            }

            public String getLwaFallbackUrl() {
                return this.lwaFallbackUrl;
            }

            public String getNativeAppUrl() {
                return this.nativeAppUrl;
            }

            public void setLocales(List<Locale> list) {
                this.locales = list;
            }

            public void setLwaFallbackUrl(String str) {
                this.lwaFallbackUrl = str;
            }

            public void setNativeAppUrl(String str) {
                this.nativeAppUrl = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class ApiFrequencyBean {
            @SerializedName("post_fitness_data")
            private int postFitnessData;
            @SerializedName("post_fitness_live")
            private int postFitnessLive;
            @SerializedName("post_iot_user_heartbeat")
            private int postIotUserHeartbeat;
            @SerializedName("post_iot_user_nearby")
            private int postIotUserNearby;

            public int getPostFitnessData() {
                return this.postFitnessData;
            }

            public int getPostFitnessLive() {
                return this.postFitnessLive;
            }

            public int getPostIotUserHeartbeat() {
                return this.postIotUserHeartbeat;
            }

            public int getPostIotUserNearby() {
                return this.postIotUserNearby;
            }

            public void setPostFitnessData(int i) {
                this.postFitnessData = i;
            }

            public void setPostFitnessLive(int i) {
                this.postFitnessLive = i;
            }

            public void setPostIotUserHeartbeat(int i) {
                this.postIotUserHeartbeat = i;
            }

            public void setPostIotUserNearby(int i) {
                this.postIotUserNearby = i;
            }
        }

        /* loaded from: classes8.dex */
        public static class CoinsBean {
            @SerializedName("cardImage")
            private String cardImage;
            @SerializedName("webViewUrl")
            private WebViewUrl webViewUrl;

            /* loaded from: classes8.dex */
            public static class WebViewUrl {
                @SerializedName("home")
                private String home;

                public String getHome() {
                    return this.home;
                }

                public void setHome(String str) {
                    this.home = str;
                }
            }

            public String getCardImage() {
                return this.cardImage;
            }

            public WebViewUrl getWebViewUrl() {
                return this.webViewUrl;
            }

            public void setCardImage(String str) {
                this.cardImage = str;
            }

            public void setWebViewUrl(WebViewUrl webViewUrl) {
                this.webViewUrl = webViewUrl;
            }
        }

        /* loaded from: classes8.dex */
        public static class DataPull {
            @SerializedName("fromDate")
            private String fromDate;
            @SerializedName("versionTag")
            private String versionTag;

            public String getFromDate() {
                return this.fromDate;
            }

            public String getVersionTag() {
                return this.versionTag;
            }

            public void setFromDate(String str) {
                this.fromDate = str;
            }

            public void setVersionTag(String str) {
                this.versionTag = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class DocOnline {
            @SerializedName("authToken")
            private String authToken;
            @SerializedName(com.coveiot.android.tappy.utils.Constants.ENCRYPTION_KEY)
            private String encryptionKey;
            @SerializedName("loginUrl")
            private String url;

            public String getAuthToken() {
                return this.authToken;
            }

            public String getEncryptionKey() {
                return this.encryptionKey;
            }

            public String getUrl() {
                return this.url;
            }

            public void setAuthToken(String str) {
                this.authToken = str;
            }

            public void setEncryptionKey(String str) {
                this.encryptionKey = str;
            }

            public void setUrl(String str) {
                this.url = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class FitnessPlanBean {
            private WebViewUrlBean webViewUrl;

            /* loaded from: classes8.dex */
            public static class WebViewUrlBean {
                private String browsePlan;
                private String onboarding;
                private String userPlanHistory;

                public String getBrowsePlan() {
                    return this.browsePlan;
                }

                public String getOnboarding() {
                    return this.onboarding;
                }

                public String getUserPlanHistory() {
                    return this.userPlanHistory;
                }

                public void setBrowsePlan(String str) {
                    this.browsePlan = str;
                }

                public void setOnboarding(String str) {
                    this.onboarding = str;
                }

                public void setUserPlanHistory(String str) {
                    this.userPlanHistory = str;
                }
            }

            public WebViewUrlBean getWebViewUrl() {
                return this.webViewUrl;
            }

            public void setWebViewUrl(WebViewUrlBean webViewUrlBean) {
                this.webViewUrl = webViewUrlBean;
            }
        }

        /* loaded from: classes8.dex */
        public static class GuardianBean {
            @SerializedName("enable")
            private boolean enable;

            public boolean isEnable() {
                return this.enable;
            }

            public void setEnable(boolean z) {
                this.enable = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class HealthStatusBean {
            @SerializedName(DeviceKey.TempData)
            private TemperatureBeanX temperature;

            /* loaded from: classes8.dex */
            public static class TemperatureBeanX {
                @SerializedName("flaggedDate")
                private String flaggedDate;
                @SerializedName("isFlagged")
                private boolean isFlagged;
                @SerializedName("locationBreachConfig")
                private LocationBreachConfigBean locationBreachConfig;
                @SerializedName("pauseTriggerForHours")
                private int pauseTriggerForHours;

                /* loaded from: classes8.dex */
                public static class LocationBreachConfigBean {
                    @SerializedName("distanceThresholdGte")
                    private int distanceThresholdGte;
                    @SerializedName("maxBucketSize")
                    private int maxBucketSize;
                    @SerializedName("maxTimeSpan")
                    private int maxTimeSpan;

                    public int getDistanceThresholdGte() {
                        return this.distanceThresholdGte;
                    }

                    public int getMaxBucketSize() {
                        return this.maxBucketSize;
                    }

                    public int getMaxTimeSpan() {
                        return this.maxTimeSpan;
                    }

                    public void setDistanceThresholdGte(int i) {
                        this.distanceThresholdGte = i;
                    }

                    public void setMaxBucketSize(int i) {
                        this.maxBucketSize = i;
                    }

                    public void setMaxTimeSpan(int i) {
                        this.maxTimeSpan = i;
                    }
                }

                public String getFlaggedDate() {
                    return this.flaggedDate;
                }

                public LocationBreachConfigBean getLocationBreachConfig() {
                    return this.locationBreachConfig;
                }

                public int getPauseTriggerForHours() {
                    return this.pauseTriggerForHours;
                }

                public boolean isIsFlagged() {
                    return this.isFlagged;
                }

                public void setFlaggedDate(String str) {
                    this.flaggedDate = str;
                }

                public void setIsFlagged(boolean z) {
                    this.isFlagged = z;
                }

                public void setLocationBreachConfig(LocationBreachConfigBean locationBreachConfigBean) {
                    this.locationBreachConfig = locationBreachConfigBean;
                }

                public void setPauseTriggerForHours(int i) {
                    this.pauseTriggerForHours = i;
                }
            }

            public TemperatureBeanX getTemperature() {
                return this.temperature;
            }

            public void setTemperature(TemperatureBeanX temperatureBeanX) {
                this.temperature = temperatureBeanX;
            }
        }

        /* loaded from: classes8.dex */
        public static class LegalBean {
            @SerializedName("docs")
            private List<Doc> docs;

            /* loaded from: classes8.dex */
            public static class Doc {
                @SerializedName("dvcText")
                @Expose
                private String dvcText;
                @SerializedName("htmlUrl")
                @Expose
                private String htmlUrl;
                @SerializedName("type")
                @Expose
                private String type;
                @SerializedName("version")
                @Expose
                private String version;

                public String getDvcText() {
                    return this.dvcText;
                }

                public String getHtmlUrl() {
                    return this.htmlUrl;
                }

                public String getType() {
                    return this.type;
                }

                public String getVersion() {
                    return this.version;
                }

                public void setDvcText(String str) {
                    this.dvcText = str;
                }

                public void setHtmlUrl(String str) {
                    this.htmlUrl = str;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public void setVersion(String str) {
                    this.version = str;
                }
            }

            public List<Doc> getDoc() {
                return this.docs;
            }

            public void setDoc(List<Doc> list) {
                this.docs = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class OpenWeatherMap {
            @SerializedName("apiKey")
            private String apiKey;

            public String getApiKey() {
                return this.apiKey;
            }

            public void setApiKey(String str) {
                this.apiKey = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class PayBean {
            private boolean enableUpi;

            public boolean isEnableUpi() {
                return this.enableUpi;
            }

            public void setEnableUpi(boolean z) {
                this.enableUpi = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class SessionBean {
            private boolean isExist;
            private boolean requireRefresh;

            public boolean isIsExist() {
                return this.isExist;
            }

            public boolean isRequireRefresh() {
                return this.requireRefresh;
            }

            public void setIsExist(boolean z) {
                this.isExist = z;
            }

            public void setRequireRefresh(boolean z) {
                this.requireRefresh = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class Spo2AimlBean {
            @SerializedName("remeasure")
            private RemeasureBean remeasure;

            /* loaded from: classes8.dex */
            public static class RemeasureBean {
                @SerializedName("maxRetry")
                private Integer maxRetry;
                @SerializedName("remindIn")
                private Integer remindIn;
                @SerializedName("retryTimeout")
                private Integer retryTimeout;

                public Integer getMaxRetry() {
                    return this.maxRetry;
                }

                public Integer getRemindIn() {
                    return this.remindIn;
                }

                public Integer getRetryTimeout() {
                    return this.retryTimeout;
                }

                public void setMaxRetry(Integer num) {
                    this.maxRetry = num;
                }

                public void setRemindIn(Integer num) {
                    this.remindIn = num;
                }

                public void setRetryTimeout(Integer num) {
                    this.retryTimeout = num;
                }
            }

            public RemeasureBean getRemeasure() {
                return this.remeasure;
            }

            public void setRemeasure(RemeasureBean remeasureBean) {
                this.remeasure = remeasureBean;
            }
        }

        /* loaded from: classes8.dex */
        public static class Stress {
            @SerializedName("alert")
            private Alert alert;
            @SerializedName("baselineTime")
            private String baselineTime;
            @SerializedName("readinessTime")
            private String readinessTime;

            /* loaded from: classes8.dex */
            public static class Alert {
                @SerializedName("maxAllowed")
                private Integer maxAllowed;
                @SerializedName("threshold")
                private Integer threshold;

                public Integer getMaxAllowed() {
                    return this.maxAllowed;
                }

                public Integer getThreshold() {
                    return this.threshold;
                }

                public void setMaxAllowed(Integer num) {
                    this.maxAllowed = num;
                }

                public void setThreshold(Integer num) {
                    this.threshold = num;
                }
            }

            public Alert getAlert() {
                return this.alert;
            }

            public String getBaselineTime() {
                return this.baselineTime;
            }

            public String getReadinessTime() {
                return this.readinessTime;
            }

            public void setAlert(Alert alert) {
                this.alert = alert;
            }

            public void setBaselineTime(String str) {
                this.baselineTime = str;
            }

            public void setReadinessTime(String str) {
                this.readinessTime = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class TimelineBean {
            private int dwellRadius;
            private int dwellThreshold;
            private int walkStepsThreshold;

            public int getDwellRadius() {
                return this.dwellRadius;
            }

            public int getDwellThreshold() {
                return this.dwellThreshold;
            }

            public int getWalkStepsThreshold() {
                return this.walkStepsThreshold;
            }

            public void setDwellRadius(int i) {
                this.dwellRadius = i;
            }

            public void setDwellThreshold(int i) {
                this.dwellThreshold = i;
            }

            public void setWalkStepsThreshold(int i) {
                this.walkStepsThreshold = i;
            }
        }

        /* loaded from: classes8.dex */
        public static class TriggerBean {
            @SerializedName(DeviceKey.TempData)
            private TemperatureBean temperature;

            /* loaded from: classes8.dex */
            public static class TemperatureBean {
                @SerializedName("baseUnit")
                private String baseUnit;
                @SerializedName("breachWindowCount")
                private int breachWindowCount;
                @SerializedName("breachWindowTimeSpan")
                private int breachWindowTimeSpan;
                @SerializedName("historyLookbackPeriod")
                private int historyLookbackPeriod;
                @SerializedName("thresholdGte")
                private double thresholdGte;
                @SerializedName("webViewUrl")
                private String webViewUrl;

                public String getBaseUnit() {
                    return this.baseUnit;
                }

                public int getBreachWindowCount() {
                    return this.breachWindowCount;
                }

                public int getBreachWindowTimeSpan() {
                    return this.breachWindowTimeSpan;
                }

                public int getHistoryLookbackPeriod() {
                    return this.historyLookbackPeriod;
                }

                public double getThresholdGte() {
                    return this.thresholdGte;
                }

                public String getWebViewUrl() {
                    return this.webViewUrl;
                }

                public void setBaseUnit(String str) {
                    this.baseUnit = str;
                }

                public void setBreachWindowCount(int i) {
                    this.breachWindowCount = i;
                }

                public void setBreachWindowTimeSpan(int i) {
                    this.breachWindowTimeSpan = i;
                }

                public void setHistoryLookbackPeriod(int i) {
                    this.historyLookbackPeriod = i;
                }

                public void setThresholdGte(double d) {
                    this.thresholdGte = d;
                }

                public void setWebViewUrl(String str) {
                    this.webViewUrl = str;
                }
            }

            public TemperatureBean getTemperature() {
                return this.temperature;
            }

            public void setTemperature(TemperatureBean temperatureBean) {
                this.temperature = temperatureBean;
            }
        }

        /* loaded from: classes8.dex */
        public static class UiBean {
            private List<ElementsBean> elements;
            private String placeholder;
            private String screenName;

            /* loaded from: classes8.dex */
            public static class ElementsBean {
                private ContentBean content;
                private String elementName;
                private List<EventsBean> events;
                private int index;
                private StyleBeanXXX style;

                /* loaded from: classes8.dex */
                public static class ContentBean {
                    private DescriptionBean description;
                    private ImageBean image;
                    private SubtitleBean subtitle;
                    private TitleBean title;

                    /* loaded from: classes8.dex */
                    public static class DescriptionBean {
                        private StyleBeanXX style;
                        private String text;

                        /* loaded from: classes8.dex */
                        public static class StyleBeanXX {
                            private String color;

                            public String getColor() {
                                return this.color;
                            }

                            public void setColor(String str) {
                                this.color = str;
                            }
                        }

                        public StyleBeanXX getStyle() {
                            return this.style;
                        }

                        public String getText() {
                            return this.text;
                        }

                        public void setStyle(StyleBeanXX styleBeanXX) {
                            this.style = styleBeanXX;
                        }

                        public void setText(String str) {
                            this.text = str;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public static class ImageBean {
                        private String src;

                        public String getSrc() {
                            return this.src;
                        }

                        public void setSrc(String str) {
                            this.src = str;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public static class SubtitleBean {
                        private StyleBeanX style;
                        private String text;

                        /* loaded from: classes8.dex */
                        public static class StyleBeanX {
                            private String color;

                            public String getColor() {
                                return this.color;
                            }

                            public void setColor(String str) {
                                this.color = str;
                            }
                        }

                        public StyleBeanX getStyle() {
                            return this.style;
                        }

                        public String getText() {
                            return this.text;
                        }

                        public void setStyle(StyleBeanX styleBeanX) {
                            this.style = styleBeanX;
                        }

                        public void setText(String str) {
                            this.text = str;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public static class TitleBean {
                        private StyleBean style;
                        private String text;

                        /* loaded from: classes8.dex */
                        public static class StyleBean {
                            private String color;

                            public String getColor() {
                                return this.color;
                            }

                            public void setColor(String str) {
                                this.color = str;
                            }
                        }

                        public StyleBean getStyle() {
                            return this.style;
                        }

                        public String getText() {
                            return this.text;
                        }

                        public void setStyle(StyleBean styleBean) {
                            this.style = styleBean;
                        }

                        public void setText(String str) {
                            this.text = str;
                        }
                    }

                    public DescriptionBean getDescription() {
                        return this.description;
                    }

                    public ImageBean getImage() {
                        return this.image;
                    }

                    public SubtitleBean getSubtitle() {
                        return this.subtitle;
                    }

                    public TitleBean getTitle() {
                        return this.title;
                    }

                    public void setDescription(DescriptionBean descriptionBean) {
                        this.description = descriptionBean;
                    }

                    public void setImage(ImageBean imageBean) {
                        this.image = imageBean;
                    }

                    public void setSubtitle(SubtitleBean subtitleBean) {
                        this.subtitle = subtitleBean;
                    }

                    public void setTitle(TitleBean titleBean) {
                        this.title = titleBean;
                    }
                }

                /* loaded from: classes8.dex */
                public static class EventsBean {
                    private String action;
                    private String name;
                    private String target;
                    private String url;
                    private WindowBean window;

                    /* loaded from: classes8.dex */
                    public static class WindowBean {
                        private CoveJsInterfaceBean coveJsInterface;
                        private NavBarBean navBar;
                        private String windowId;

                        /* loaded from: classes8.dex */
                        public static class CoveJsInterfaceBean {
                            private boolean enable;

                            public boolean isEnable() {
                                return this.enable;
                            }

                            public void setEnable(boolean z) {
                                this.enable = z;
                            }
                        }

                        /* loaded from: classes8.dex */
                        public static class NavBarBean {
                            private CloseButtonBean closeButton;
                            private boolean display;
                            private TitleBeanX title;

                            /* loaded from: classes8.dex */
                            public static class CloseButtonBean {
                                private boolean enable;

                                public boolean isEnable() {
                                    return this.enable;
                                }

                                public void setEnable(boolean z) {
                                    this.enable = z;
                                }
                            }

                            /* loaded from: classes8.dex */
                            public static class TitleBeanX {
                                private StyleBeanXXXX style;
                                private String text;

                                /* loaded from: classes8.dex */
                                public static class StyleBeanXXXX {
                                    private String color;

                                    public String getColor() {
                                        return this.color;
                                    }

                                    public void setColor(String str) {
                                        this.color = str;
                                    }
                                }

                                public StyleBeanXXXX getStyle() {
                                    return this.style;
                                }

                                public String getText() {
                                    return this.text;
                                }

                                public void setStyle(StyleBeanXXXX styleBeanXXXX) {
                                    this.style = styleBeanXXXX;
                                }

                                public void setText(String str) {
                                    this.text = str;
                                }
                            }

                            public CloseButtonBean getCloseButton() {
                                return this.closeButton;
                            }

                            public TitleBeanX getTitle() {
                                return this.title;
                            }

                            public boolean isDisplay() {
                                return this.display;
                            }

                            public void setCloseButton(CloseButtonBean closeButtonBean) {
                                this.closeButton = closeButtonBean;
                            }

                            public void setDisplay(boolean z) {
                                this.display = z;
                            }

                            public void setTitle(TitleBeanX titleBeanX) {
                                this.title = titleBeanX;
                            }
                        }

                        public CoveJsInterfaceBean getCoveJsInterface() {
                            return this.coveJsInterface;
                        }

                        public NavBarBean getNavBar() {
                            return this.navBar;
                        }

                        public String getWindowId() {
                            return this.windowId;
                        }

                        public void setCoveJsInterface(CoveJsInterfaceBean coveJsInterfaceBean) {
                            this.coveJsInterface = coveJsInterfaceBean;
                        }

                        public void setNavBar(NavBarBean navBarBean) {
                            this.navBar = navBarBean;
                        }

                        public void setWindowId(String str) {
                            this.windowId = str;
                        }
                    }

                    public String getAction() {
                        return this.action;
                    }

                    public String getName() {
                        return this.name;
                    }

                    public String getTarget() {
                        return this.target;
                    }

                    public String getUrl() {
                        return this.url;
                    }

                    public WindowBean getWindow() {
                        return this.window;
                    }

                    public void setAction(String str) {
                        this.action = str;
                    }

                    public void setName(String str) {
                        this.name = str;
                    }

                    public void setTarget(String str) {
                        this.target = str;
                    }

                    public void setUrl(String str) {
                        this.url = str;
                    }

                    public void setWindow(WindowBean windowBean) {
                        this.window = windowBean;
                    }
                }

                /* loaded from: classes8.dex */
                public static class StyleBeanXXX {
                    private String backgroundColor;
                    private String backgroundImage;

                    public String getBackgroundColor() {
                        return this.backgroundColor;
                    }

                    public String getBackgroundImage() {
                        return this.backgroundImage;
                    }

                    public void setBackgroundColor(String str) {
                        this.backgroundColor = str;
                    }

                    public void setBackgroundImage(String str) {
                        this.backgroundImage = str;
                    }
                }

                public ContentBean getContent() {
                    return this.content;
                }

                public String getElementName() {
                    return this.elementName;
                }

                public List<EventsBean> getEvents() {
                    return this.events;
                }

                public int getIndex() {
                    return this.index;
                }

                public StyleBeanXXX getStyle() {
                    return this.style;
                }

                public void setContent(ContentBean contentBean) {
                    this.content = contentBean;
                }

                public void setElementName(String str) {
                    this.elementName = str;
                }

                public void setEvents(List<EventsBean> list) {
                    this.events = list;
                }

                public void setIndex(int i) {
                    this.index = i;
                }

                public void setStyle(StyleBeanXXX styleBeanXXX) {
                    this.style = styleBeanXXX;
                }
            }

            public List<ElementsBean> getElements() {
                return this.elements;
            }

            public String getPlaceholder() {
                return this.placeholder;
            }

            public String getScreenName() {
                return this.screenName;
            }

            public void setElements(List<ElementsBean> list) {
                this.elements = list;
            }

            public void setPlaceholder(String str) {
                this.placeholder = str;
            }

            public void setScreenName(String str) {
                this.screenName = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class WatchFaceBean {
            @SerializedName("diyMaxAllowed")
            private Integer diyMaxAllowed;
            @SerializedName("diyToolCardImage")
            private String diyToolCardImage;
            @SerializedName("diyToolUrl")
            private String diyToolUrl;

            public Integer getDiyMaxAllowed() {
                return this.diyMaxAllowed;
            }

            public String getDiyToolCardImage() {
                return this.diyToolCardImage;
            }

            public String getDiyToolUrl() {
                return this.diyToolUrl;
            }

            public void setDiyMaxAllowed(Integer num) {
                this.diyMaxAllowed = num;
            }

            public void setDiyToolCardImage(String str) {
                this.diyToolCardImage = str;
            }

            public void setDiyToolUrl(String str) {
                this.diyToolUrl = str;
            }
        }

        public AlexaBean getAlexaBean() {
            return this.alexaBean;
        }

        public ApiFrequencyBean getApiFrequency() {
            return this.apiFrequency;
        }

        public CoinsBean getCoins() {
            return this.coins;
        }

        public DataPull getDataPull() {
            return this.dataPull;
        }

        public DocOnline getDocOnline() {
            return this.docOnline;
        }

        public FitnessPlanBean getFitnessPlan() {
            return this.fitnessPlan;
        }

        public GuardianBean getGuardian() {
            return this.guardian;
        }

        public HealthStatusBean getHealthStatus() {
            return this.healthStatus;
        }

        public LegalBean getLegalBean() {
            return this.legalBean;
        }

        public NearbyTrackingBean getNearbyTracking() {
            return this.nearbyTracking;
        }

        public OpenWeatherMap getOpenWeatherMap() {
            return this.openWeatherMap;
        }

        public String getOpenWeatherMapAPiKey() {
            return this.openWeatherMapAPiKey;
        }

        public PayBean getPay() {
            return this.pay;
        }

        public RefsBean getRefsBean() {
            return this.refsBean;
        }

        public RespiratoryRateBean getRespiratoryRate() {
            return this.respiratoryRate;
        }

        public SensAIBean getSensAI() {
            return this.sensAI;
        }

        public SessionBean getSession() {
            return this.session;
        }

        public Spo2AimlBean getSpo2Aiml() {
            return this.spo2Aiml;
        }

        public Stress getStress() {
            return this.stress;
        }

        public SupportBean getSupportBean() {
            return this.supportBean;
        }

        public TimelineBean getTimeline() {
            return this.timeline;
        }

        public TitanWeather getTitanWeatherApi() {
            return this.titanWeatherApi;
        }

        public TriggerBean getTrigger() {
            return this.trigger;
        }

        public List<UiBean> getUi() {
            return this.ui;
        }

        public WatchFaceBean getWatchface() {
            return this.watchface;
        }

        public void setAlexaBean(AlexaBean alexaBean) {
            this.alexaBean = alexaBean;
        }

        public void setApiFrequency(ApiFrequencyBean apiFrequencyBean) {
            this.apiFrequency = apiFrequencyBean;
        }

        public void setCoins(CoinsBean coinsBean) {
            this.coins = coinsBean;
        }

        public void setDataPull(DataPull dataPull) {
            this.dataPull = dataPull;
        }

        public void setDocOnline(DocOnline docOnline) {
            this.docOnline = docOnline;
        }

        public void setFitnessPlan(FitnessPlanBean fitnessPlanBean) {
            this.fitnessPlan = fitnessPlanBean;
        }

        public void setGuardian(GuardianBean guardianBean) {
            this.guardian = guardianBean;
        }

        public void setHealthStatus(HealthStatusBean healthStatusBean) {
            this.healthStatus = healthStatusBean;
        }

        public void setLegalBean(LegalBean legalBean) {
            this.legalBean = legalBean;
        }

        public void setNearbyTracking(NearbyTrackingBean nearbyTrackingBean) {
            this.nearbyTracking = nearbyTrackingBean;
        }

        public void setOpenWeatherMap(OpenWeatherMap openWeatherMap) {
            this.openWeatherMap = openWeatherMap;
        }

        public void setOpenWeatherMapAPiKey(String str) {
            this.openWeatherMapAPiKey = str;
        }

        public void setPay(PayBean payBean) {
            this.pay = payBean;
        }

        public DataBean setRefsBean(RefsBean refsBean) {
            this.refsBean = refsBean;
            return this;
        }

        public void setRespiratoryRate(RespiratoryRateBean respiratoryRateBean) {
            this.respiratoryRate = respiratoryRateBean;
        }

        public void setSensAI(SensAIBean sensAIBean) {
            this.sensAI = sensAIBean;
        }

        public void setSession(SessionBean sessionBean) {
            this.session = sessionBean;
        }

        public void setSpo2Aiml(Spo2AimlBean spo2AimlBean) {
            this.spo2Aiml = spo2AimlBean;
        }

        public void setStress(Stress stress) {
            this.stress = stress;
        }

        public DataBean setSupportBean(SupportBean supportBean) {
            this.supportBean = supportBean;
            return this;
        }

        public void setTimeline(TimelineBean timelineBean) {
            this.timeline = timelineBean;
        }

        public void setTitanWeatherApi(TitanWeather titanWeather) {
            this.titanWeatherApi = titanWeather;
        }

        public void setTrigger(TriggerBean triggerBean) {
            this.trigger = triggerBean;
        }

        public void setUi(List<UiBean> list) {
            this.ui = list;
        }

        public void setWatchface(WatchFaceBean watchFaceBean) {
            this.watchface = watchFaceBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class RefsBean {
        @SerializedName("eastapexActivities")
        private String eastapexActivities;
        @SerializedName("idoActivities")
        private String idoActivities;
        @SerializedName("mfrIntents")
        private String mfrIntents;
        @SerializedName("ruggedActivities")
        private String ruggedActivities;
        @SerializedName("smaActivities")
        private String smaActivities;
        @SerializedName("timeZones")
        private String timeZones;
        @SerializedName("touchelxActivities")
        private String touchelxActivities;

        public String getEastapexActivities() {
            return this.eastapexActivities;
        }

        public String getIdoActivities() {
            return this.idoActivities;
        }

        public String getMfrIntents() {
            return this.mfrIntents;
        }

        public String getRuggedActivities() {
            return this.ruggedActivities;
        }

        public String getSmaActivities() {
            return this.smaActivities;
        }

        public String getTimeZones() {
            return this.timeZones;
        }

        public String getTouchelxActivities() {
            return this.touchelxActivities;
        }

        public void setEastapexActivities(String str) {
            this.eastapexActivities = str;
        }

        public void setIdoActivities(String str) {
            this.idoActivities = str;
        }

        public void setMfrIntents(String str) {
            this.mfrIntents = str;
        }

        public void setRuggedActivities(String str) {
            this.ruggedActivities = str;
        }

        public void setSmaActivities(String str) {
            this.smaActivities = str;
        }

        public void setTimeZones(String str) {
            this.timeZones = str;
        }

        public void setTouchelxActivities(String str) {
            this.touchelxActivities = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class RespiratoryRateBean {
        @SerializedName(ClientComponent.NamedSchedulers.COMPUTATION)
        private Computation computation;
        @SerializedName("duration")
        private int duration;
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("interval")
        private String interval;
        @SerializedName("startTime")
        private String startTime;

        /* loaded from: classes8.dex */
        public class Computation {
            @SerializedName("confidenceLevelThreshold")
            private float confidenceLevelThreshold;

            public Computation() {
            }

            public float getConfidenceLevelThreshold() {
                return this.confidenceLevelThreshold;
            }

            public void setConfidenceLevelThreshold(float f) {
                this.confidenceLevelThreshold = f;
            }
        }

        public Computation getComputation() {
            return this.computation;
        }

        public int getDuration() {
            return this.duration;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public String getInterval() {
            return this.interval;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setComputation(Computation computation) {
            this.computation = computation;
        }

        public void setDuration(int i) {
            this.duration = i;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setInterval(String str) {
            this.interval = str;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SensAIBean {
        @SerializedName("cardImage")
        private String cardImage;
        @SerializedName("coach")
        private SensAICoach coach;

        /* loaded from: classes8.dex */
        public static class SensAICoach {
            @SerializedName("bannerImage")
            private String bannerImage;
            @SerializedName("cardImage")
            private String cardImage;

            public String getBannerImage() {
                return this.bannerImage;
            }

            public String getCardImage() {
                return this.cardImage;
            }

            public void setBannerImage(String str) {
                this.bannerImage = str;
            }

            public void setCardImage(String str) {
                this.cardImage = str;
            }
        }

        public String getCardImage() {
            return this.cardImage;
        }

        public SensAICoach getCoach() {
            return this.coach;
        }

        public void setCardImage(String str) {
            this.cardImage = str;
        }

        public void setCoach(SensAICoach sensAICoach) {
            this.coach = sensAICoach;
        }
    }

    /* loaded from: classes8.dex */
    public static class SupportBean {
        @SerializedName("faqUrl")
        private String faqUrl;
        @SerializedName("troubleshootUrl")
        private String troubleshootUrl;
        @SerializedName("videoHelpUrl")
        private String videoHelpUrl;

        public String getFaqUrl() {
            return this.faqUrl;
        }

        public String getTroubleshootUrl() {
            return this.troubleshootUrl;
        }

        public String getVideoHelpUrl() {
            return this.videoHelpUrl;
        }

        public void setFaqUrl(String str) {
            this.faqUrl = str;
        }

        public void setTroubleshootUrl(String str) {
            this.troubleshootUrl = str;
        }

        public void setVideoHelpUrl(String str) {
            this.videoHelpUrl = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TitanWeather {
        @SerializedName(com.clevertap.android.sdk.Constants.KEY_KEY)
        private String key;
        @SerializedName("pw")
        private String password;
        @SerializedName("url")
        private String url;
        @SerializedName("un")
        private String userName;

        public String getKey() {
            return this.key;
        }

        public String getPassword() {
            return this.password;
        }

        public String getUrl() {
            return this.url;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setKey(String str) {
            this.key = str;
        }

        public void setPassword(String str) {
            this.password = str;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public void setUserName(String str) {
            this.userName = str;
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
