package com.clevertap.android.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashSet;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public interface Constants {
    public static final int APP_INBOX_CTA1_INDEX = 0;
    public static final int APP_INBOX_CTA2_INDEX = 1;
    public static final int APP_INBOX_CTA3_INDEX = 2;
    public static final int APP_INBOX_ITEM_CONTENT_PAGE_INDEX = 0;
    public static final int APP_INBOX_ITEM_INDEX = -1;
    public static final String APP_LAUNCHED_EVENT = "App Launched";
    public static final String AUDIO_THUMBNAIL = "ct_audio";
    public static final String AUTH = "auth";
    public static final String BLACK = "#000000";
    public static final String BLUE = "#0000FF";
    public static final String CACHED_GUIDS_KEY = "cachedGUIDsKey";
    public static final String CACHED_VARIABLES_KEY = "variablesKey";
    public static final int CHANNEL_ID_MISSING_IN_PAYLOAD = 8;
    public static final int CHANNEL_ID_NOT_REGISTERED = 9;
    public static final String CHARGED_EVENT = "Charged";
    public static final String CLEVERTAP_IDENTIFIER = "CLEVERTAP_IDENTIFIER";
    public static final String CLEVERTAP_LOG_TAG = "CleverTap";
    public static final String CLEVERTAP_OPTOUT = "ct_optout";
    public static final String CLEVERTAP_STORAGE_TAG = "WizRocket";
    public static final String CLOSE_SYSTEM_DIALOGS = "close_system_dialogs";
    public static final String COMMAND_ADD = "$add";
    public static final String COMMAND_DECREMENT = "$decr";
    public static final String COMMAND_DELETE = "$delete";
    public static final String COMMAND_INCREMENT = "$incr";
    public static final String COMMAND_REMOVE = "$remove";
    public static final String COMMAND_SET = "$set";
    public static final String COPY_TYPE = "copy";
    public static final String CRYPTION_IV = "__CL3>3Rt#P__1V_";
    public static final String CRYPTION_SALT = "W1ZRCl3>";
    public static final String CUSTOM_CLEVERTAP_ID_PREFIX = "__h";
    public static final int DATA_EVENT = 5;
    public static final String DEEP_LINK_KEY = "wzrk_dl";
    public static final long DEFAULT_PUSH_TTL = 345600000;
    public static final int DEFINE_VARS_EVENT = 8;
    public static final String DEVICE_ID_TAG = "deviceId";
    public static final String DISCARDED_EVENT_JSON_KEY = "d_e";
    public static final int DISCARDED_EVENT_NAME = 17;
    public static final String DISPLAY_UNIT_JSON_RESPONSE_KEY = "adUnit_notifs";
    public static final String DISPLAY_UNIT_PREVIEW_PUSH_PAYLOAD_KEY = "wzrk_adunit";
    public static final String DND_START = "22:00";
    public static final String DND_STOP = "06:00";
    public static final String D_SRC = "d_src";
    public static final String D_SRC_PI_R = "PI_R";
    public static final String D_SRC_PI_WM = "PI_WM";
    public static final int EMPTY_NOTIFICATION_ID = -1000;
    public static final String EMPTY_STRING = "";
    public static final int ENCRYPTION_FLAG_ALL_SUCCESS = 3;
    public static final int ENCRYPTION_FLAG_CGK_SUCCESS = 1;
    public static final int ENCRYPTION_FLAG_DB_SUCCESS = 2;
    public static final int ENCRYPTION_FLAG_FAIL = 0;
    public static final String ERROR_KEY = "wzrk_error";
    public static final String ERROR_PROFILE_PREFIX = "__i";
    public static final int EVENT_NAME_NULL = 14;
    public static final String EXTRAS_FROM = "extras_from";
    public static final String FALLBACK_ID_TAG = "fallbackId";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_ID = "fcm_fallback_notification_channel";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_NAME = "Misc";
    public static final String FEATURE_DISPLAY_UNIT = "DisplayUnit : ";
    public static final String FEATURE_FLAG_JSON_RESPONSE_KEY = "ff_notifs";
    public static final String FEATURE_FLAG_UNIT = "Feature Flag : ";
    public static final int FETCH_EVENT = 7;
    public static final int FETCH_TYPE_FF = 1;
    public static final int FETCH_TYPE_PC = 0;
    public static final int FETCH_TYPE_VARIABLES = 4;
    public static final String FLUSH_PUSH_IMPRESSIONS_ONE_TIME_WORKER_NAME = "CTFlushPushImpressionsOneTime";
    public static final String GEOFENCES_JSON_RESPONSE_KEY = "geofences";
    public static final String GREEN = "#00FF00";
    public static final String GUID_PREFIX_GOOGLE_AD_ID = "__g";
    public static final String HEADER_DOMAIN_NAME = "X-WZRK-RD";
    public static final String HEADER_MUTE = "X-WZRK-MUTE";
    public static final String ICON_BASE_URL = "http://static.wizrocket.com/android/ico/";
    public static final String IMAGE_PLACEHOLDER = "ct_image";
    public static final int INAPP_CLOSE_IV_WIDTH = 40;
    public static final String INAPP_DATA_TAG = "d";
    public static final String INAPP_HTML_TAG = "html";
    public static final String INAPP_ID_IN_PAYLOAD = "ti";
    public static final String INAPP_JSON_RESPONSE_KEY = "inapp_notifs";
    public static final String INAPP_JS_ENABLED = "isJsEnabled";
    public static final String INAPP_KEY = "inApp";
    public static final String INAPP_MAX_DISPLAY_COUNT = "mdc";
    public static final String INAPP_MAX_PER_SESSION = "imc";
    public static final String INAPP_NOTIF_DARKEN_SCREEN = "dk";
    public static final String INAPP_NOTIF_SHOW_CLOSE = "sc";
    public static final String INAPP_POSITION = "pos";
    public static final char INAPP_POSITION_BOTTOM = 'b';
    public static final char INAPP_POSITION_CENTER = 'c';
    public static final char INAPP_POSITION_LEFT = 'l';
    public static final char INAPP_POSITION_RIGHT = 'r';
    public static final char INAPP_POSITION_TOP = 't';
    public static final String INAPP_PREVIEW_PUSH_PAYLOAD_KEY = "wzrk_inapp";
    public static final String INAPP_WINDOW = "w";
    public static final String INAPP_X_DP = "xdp";
    public static final String INAPP_X_PERCENT = "xp";
    public static final String INAPP_Y_DP = "ydp";
    public static final String INAPP_Y_PERCENT = "yp";
    public static final String INBOX_JSON_RESPONSE_KEY = "inbox_notifs";
    public static final String INBOX_PREVIEW_PUSH_PAYLOAD_KEY = "wzrk_inbox";
    public static final int INVALID_COUNTRY_CODE = 4;
    public static final int INVALID_CT_CUSTOM_ID = 21;
    public static final int INVALID_INCREMENT_DECREMENT_VALUE = 25;
    public static final int INVALID_MULTI_VALUE = 1;
    public static final int INVALID_MULTI_VALUE_KEY = 23;
    public static final int INVALID_PHONE = 5;
    public static final int INVALID_PROFILE_PROP_ARRAY_COUNT = 13;
    public static final String KEY_ACCOUNT_ID = "accountId";
    public static final String KEY_ACCOUNT_REGION = "accountRegion";
    public static final String KEY_ACCOUNT_TOKEN = "accountToken";
    public static final String KEY_ACTION = "action";
    public static final String KEY_ACTIONS = "actions";
    public static final String KEY_ALLOWED_PUSH_TYPES = "allowedPushTypes";
    public static final String KEY_ANALYTICS_ONLY = "analyticsOnly";
    public static final String KEY_ANDROID = "android";
    public static final String KEY_BACKGROUND_SYNC = "backgroundSync";
    public static final String KEY_BETA = "beta";
    public static final String KEY_BG = "bg";
    public static final String KEY_BORDER = "border";
    public static final String KEY_BUTTONS = "buttons";
    public static final String KEY_C2A = "wzrk_c2a";
    public static final String KEY_COLOR = "color";
    public static final String KEY_CONFIG = "config";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_CONTENT_TYPE = "content_type";
    public static final String KEY_COUNTS_PER_INAPP = "counts_per_inapp";
    public static final String KEY_COUNTS_SHOWN_TODAY = "istc_inapp";
    public static final String KEY_CREATED_POST_APP_LAUNCH = "createdPostAppLaunch";
    public static final String KEY_CT_TYPE = "ct_type";
    public static final String KEY_CUSTOM_HTML = "custom-html";
    public static final String KEY_CUSTOM_KV = "custom_kv";
    public static final String KEY_DATE = "date";
    public static final String KEY_DEBUG_LEVEL = "debugLevel";
    public static final String KEY_DEFAULT_INSTANCE = "isDefaultInstance";
    public static final String KEY_DISABLE_APP_LAUNCHED = "disableAppLaunchedEvent";
    public static final String KEY_DOMAIN_NAME = "comms_dmn";
    public static final String KEY_EFC = "efc";
    public static final int KEY_EMPTY = 6;
    public static final String KEY_ENABLE_CUSTOM_CT_ID = "getEnableCustomCleverTapId";
    public static final String KEY_ENCRYPTION_EMAIL = "Email";
    public static final String KEY_ENCRYPTION_FLAG_STATUS = "encryptionFlagStatus";
    public static final String KEY_ENCRYPTION_IDENTITY = "Identity";
    public static final String KEY_ENCRYPTION_LEVEL = "encryptionLevel";
    public static final String KEY_ENCRYPTION_NAME = "Name";
    public static final String KEY_ENCRYPTION_PHONE = "Phone";
    public static final String KEY_FALLBACK_NOTIFICATION_SETTINGS = "fbSettings";
    public static final String KEY_FCM_SENDER_ID = "fcmSenderId";
    public static final String KEY_FIRST_TS = "comms_first_ts";
    public static final String KEY_HAS_LINKS = "hasLinks";
    public static final String KEY_HAS_URL = "hasUrl";
    public static final String KEY_HIDE_CLOSE = "close";
    public static final String KEY_I = "comms_i";
    public static final String KEY_ICON = "icon";
    public static final String KEY_ID = "id";
    public static final String KEY_IDENTITY_TYPES = "identityTypes";
    public static final String KEY_IS_READ = "isRead";
    public static final String KEY_IS_TABLET = "tablet";
    public static final String KEY_J = "comms_j";
    public static final String KEY_KEY = "key";
    public static final String KEY_KV = "kv";
    public static final String KEY_LANDSCAPE = "hasLandscape";
    public static final String KEY_LAST_TS = "comms_last_ts";
    public static final String KEY_LINKS = "links";
    public static final String KEY_MAX_PER_DAY = "istmcd_inapp";
    public static final String KEY_MEDIA = "media";
    public static final String KEY_MEDIA_LANDSCAPE = "mediaLandscape";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_MSG = "msg";
    public static final String KEY_MUTED = "comms_mtd";
    public static final String KEY_ORIENTATION = "orientation";
    public static final String KEY_PACKAGE_NAME = "packageName";
    public static final String KEY_PERSONALIZATION = "personalization";
    public static final String KEY_PORTRAIT = "hasPortrait";
    public static final String KEY_POSTER_URL = "poster";
    public static final String KEY_RADIUS = "radius";
    public static final String KEY_REQUEST_FOR_NOTIFICATION_PERMISSION = "rfp";
    public static final String KEY_SSL_PINNING = "sslPinning";
    public static final String KEY_TAGS = "tags";
    public static final String KEY_TDC = "tdc";
    public static final String KEY_TEXT = "text";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TLC = "tlc";
    public static final String KEY_TYPE = "type";
    public static final String KEY_URL = "url";
    public static final String KEY_USE_GOOGLE_AD_ID = "useGoogleAdId";
    public static final String KEY_VALUE = "value";
    public static final String KEY_WZRK_PARAMS = "wzrkParams";
    public static final String KEY_WZRK_TTL = "wzrk_ttl";
    public static final String LABEL_ACCOUNT_ID = "CLEVERTAP_ACCOUNT_ID";
    public static final String LABEL_BACKGROUND_SYNC = "CLEVERTAP_BACKGROUND_SYNC";
    public static final String LABEL_BETA = "CLEVERTAP_BETA";
    public static final String LABEL_CUSTOM_ID = "CLEVERTAP_USE_CUSTOM_ID";
    public static final String LABEL_DEFAULT_CHANNEL_ID = "CLEVERTAP_DEFAULT_CHANNEL_ID";
    public static final String LABEL_DISABLE_APP_LAUNCH = "CLEVERTAP_DISABLE_APP_LAUNCHED";
    public static final String LABEL_ENCRYPTION_LEVEL = "CLEVERTAP_ENCRYPTION_LEVEL";
    public static final String LABEL_FCM_SENDER_ID = "FCM_SENDER_ID";
    public static final String LABEL_INAPP_EXCLUDE = "CLEVERTAP_INAPP_EXCLUDE";
    public static final String LABEL_INTENT_SERVICE = "CLEVERTAP_INTENT_SERVICE";
    public static final String LABEL_NOTIFICATION_ICON = "CLEVERTAP_NOTIFICATION_ICON";
    public static final String LABEL_PACKAGE_NAME = "CLEVERTAP_APP_PACKAGE";
    public static final String LABEL_REGION = "CLEVERTAP_REGION";
    public static final String LABEL_SSL_PINNING = "CLEVERTAP_SSL_PINNING";
    public static final String LABEL_TOKEN = "CLEVERTAP_TOKEN";
    public static final String LABEL_USE_GOOGLE_AD_ID = "CLEVERTAP_USE_GOOGLE_AD_ID";
    public static final String LABEL_XIAOMI_APP_ID = "CLEVERTAP_XIAOMI_APP_ID";
    public static final String LABEL_XIAOMI_APP_KEY = "CLEVERTAP_XIAOMI_APP_KEY";
    public static final String LAST_SESSION_EPOCH = "sexe";
    public static final String LIGHT_BLUE = "#818ce5";
    public static final int LOCATION_PING_INTERVAL_IN_SECONDS = 10;
    public static final String LOG_TAG_GEOFENCES = "Geofences : ";
    public static final String LOG_TAG_PRODUCT_CONFIG = "Product Config : ";
    public static final String LOG_TAG_SIGNED_CALL = "SignedCall : ";
    public static final int MAX_DELAY_FREQUENCY = 600000;
    public static final int MAX_KEY_LENGTH = 120;
    public static final int MAX_MULTI_VALUE_ARRAY_LENGTH = 100;
    public static final int MAX_MULTI_VALUE_LENGTH = 512;
    public static final int MAX_VALUE_LENGTH = 512;
    public static final String MULTI_USER_PREFIX = "mt_";
    public static final int MULTI_VALUE_CHARS_LIMIT_EXCEEDED = 12;
    public static final String NAMESPACE_IJ = "IJ";
    public static final String NETWORK_INFO = "NetworkInfo";
    public static final String NOTIFICATION_ID_TAG = "wzrk_id";
    public static final int NOTIFICATION_ID_TAG_INTERVAL = 5000;
    public static final int NOTIFICATION_PERMISSION_REQUEST_CODE = 102;
    public static final String NOTIFICATION_RENDER_FALLBACK = "wzrk_fallback";
    public static final String NOTIFICATION_TAG = "wzrk_pn";
    public static final int NOTIFICATION_VIEWED_DISABLED = 10;
    public static final int NOTIFICATION_VIEWED_ID_TAG_INTERVAL = 2000;
    public static final String NOTIF_ICON = "ico";
    public static final String NOTIF_MSG = "nm";
    public static final String NOTIF_PRIORITY = "pr";
    public static final String NOTIF_TITLE = "nt";
    public static final int NV_EVENT = 6;
    public static final int OBJECT_VALUE_NOT_PRIMITIVE = 15;
    public static final int OBJECT_VALUE_NOT_PRIMITIVE_PROFILE = 3;
    public static final String OMR_INVOKE_TIME_IN_MILLIS = "omr_invoke_time_in_millis";
    public static final long ONE_DAY_IN_MILLIS = 86400000;
    public static final long ONE_MIN_IN_MILLIS = 60000;
    public static final int PAGE_EVENT = 1;
    public static final String PF_JOB_ID = "pfjobid";
    public static final int PING_EVENT = 2;
    public static final String PING_FREQUENCY = "pf";
    public static final int PING_FREQUENCY_VALUE = 240;
    public static final int PN_IMAGE_CONNECTION_TIMEOUT_IN_MILLIS = 1000;
    public static final long PN_IMAGE_DOWNLOAD_TIMEOUT_IN_MILLIS = 5000;
    public static final int PN_IMAGE_READ_TIMEOUT_IN_MILLIS = 5000;
    public static final long PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS = 2000;
    public static final String PRIMARY_DOMAIN = "clevertap-prod.com";
    public static final String PRIORITY_HIGH = "high";
    public static final String PRIORITY_MAX = "max";
    public static final String PRIORITY_NORMAL = "normal";
    public static final String PRIORITY_UNKNOWN = "fcm_unknown";
    public static final int PROFILE_EVENT = 3;
    public static final int PROP_VALUE_NOT_PRIMITIVE = 7;
    public static final String PT_INPUT_KEY = "pt_input_reply";
    public static final String PT_NOTIF_ID = "notificationId";
    public static final int PUSH_DELAY_MS = 1000;
    public static final int PUSH_KEY_EMPTY = 2;
    public static final int RAISED_EVENT = 4;
    public static final String REGION_EUROPE = "eu1";
    public static final String REGION_INDIA = "in1";
    public static final String REMOTE_CONFIG_FLAG_JSON_RESPONSE_KEY = "pc_notifs";
    public static final String REQUEST_VARIABLES_JSON_RESPONSE_KEY = "vars";
    public static final int RESTRICTED_EVENT_NAME = 16;
    public static final int RESTRICTED_MULTI_VALUE_KEY = 24;
    public static final String SC_CAMPAIGN_OPT_OUT_EVENT_NAME = "SCCampaignOptOut";
    public static final String SC_END_EVENT_NAME = "SCEnd";
    public static final String SC_INCOMING_EVENT_NAME = "SCIncoming";
    public static final String SC_OUTGOING_EVENT_NAME = "SCOutgoing";
    public static final String SEPARATOR_COMMA = ",";
    public static final String SESSION_ID_LAST = "lastSessionId";
    public static final int SESSION_LENGTH_MINS = 20;
    public static final String SPIKY_HEADER_DOMAIN_NAME = "X-WZRK-SPIKY-RD";
    public static final String SPIKY_KEY_DOMAIN_NAME = "comms_dmn_spiky";
    public static final String SP_KEY_PROFILE_IDENTITIES = "SP_KEY_PROFILE_IDENTITIES";
    public static final String TAG_FEATURE_IN_APPS = "TAG_FEATURE_IN_APPS";
    public static final String TEST_IDENTIFIER = "0_0";
    @NonNull
    public static final String TYPE_EMAIL = "Email";
    @NonNull
    public static final String TYPE_IDENTITY = "Identity";
    @NonNull
    public static final String TYPE_PHONE = "Phone";
    public static final int UNABLE_TO_SET_CT_CUSTOM_ID = 20;
    public static final int USE_CUSTOM_ID_FALLBACK = 18;
    public static final int USE_CUSTOM_ID_MISSING_IN_MANIFEST = 19;
    public static final int VALUE_CHARS_LIMIT_EXCEEDED = 11;
    public static final String VIDEO_THUMBNAIL = "ct_video_1";
    public static final String WHITE = "#FFFFFF";
    public static final String WZRK_ACCT_ID_KEY = "wzrk_acct_id";
    public static final String WZRK_ACTIONS = "wzrk_acts";
    public static final String WZRK_BADGE_COUNT = "wzrk_bc";
    public static final String WZRK_BADGE_ICON = "wzrk_bi";
    public static final String WZRK_BIG_PICTURE = "wzrk_bp";
    public static final String WZRK_BPDS = "wzrk_bpds";
    public static final String WZRK_CHANNEL_ID = "wzrk_cid";
    public static final String WZRK_COLLAPSE = "wzrk_ck";
    public static final String WZRK_COLOR = "wzrk_clr";
    public static final String WZRK_FETCH = "wzrk_fetch";
    public static final String WZRK_FROM = "CTPushNotificationReceiver";
    public static final String WZRK_FROM_KEY = "wzrk_from";
    public static final String WZRK_MSG_SUMMARY = "wzrk_nms";
    public static final String WZRK_PN_PRT = "wzrk_pn_prt";
    public static final String WZRK_PREFIX = "wzrk_";
    public static final String WZRK_PUSH_ID = "wzrk_pid";
    public static final String WZRK_PUSH_SILENT = "wzrk_pn_s";
    public static final String WZRK_RNV = "wzrk_rnv";
    public static final String WZRK_SOUND = "wzrk_sound";
    public static final String WZRK_SUBTITLE = "wzrk_st";
    public static final String WZRK_TIME_TO_LIVE = "wzrk_ttl";
    public static final String WZRK_TSR_FB = "wzrk_tsr_fb";
    public static final String variablePayloadType = "varsPayload";
    public static final String NOTIFICATION_CLICKED_EVENT_NAME = "Notification Clicked";
    public static final String NOTIFICATION_VIEWED_EVENT_NAME = "Notification Viewed";
    public static final String GEOFENCE_ENTERED_EVENT_NAME = "Geocluster Entered";
    public static final String GEOFENCE_EXITED_EVENT_NAME = "Geocluster Exited";
    public static final String[] SYSTEM_EVENTS = {NOTIFICATION_CLICKED_EVENT_NAME, NOTIFICATION_VIEWED_EVENT_NAME, GEOFENCE_ENTERED_EVENT_NAME, GEOFENCE_EXITED_EVENT_NAME};
    public static final HashSet<String> LEGACY_IDENTITY_KEYS = new HashSet<>(Arrays.asList("Identity", "Email"));
    public static final HashSet<String> ALL_IDENTITY_KEYS = new HashSet<>(Arrays.asList("Identity", "Email", "Phone"));
    public static final String KEY_ENCRYPTION_CGK = "cgk";
    public static final String KEY_ENCRYPTION_MIGRATION = "encryptionmigration";
    public static final HashSet<String> MEDIUM_CRYPT_KEYS = new HashSet<>(Arrays.asList(KEY_ENCRYPTION_CGK, KEY_ENCRYPTION_MIGRATION, "Email", "Phone", "Identity", "Name"));
    public static final HashSet<String> NONE_CRYPT_KEYS = new HashSet<>(Arrays.asList(KEY_ENCRYPTION_MIGRATION));
    public static final HashSet<String> piiDBKeys = new HashSet<>(Arrays.asList("Name", "Email", "Identity", "Phone"));
    public static final String[] NULL_STRING_ARRAY = new String[0];

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface IdentityType {
    }
}
