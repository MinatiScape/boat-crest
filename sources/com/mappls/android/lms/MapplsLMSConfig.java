package com.mappls.android.lms;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.mappls.android.util.MPLog;
import com.mappls.android.util.MapplsLMSConstants;
import com.mappls.android.util.OfflineMode;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes11.dex */
public class MapplsLMSConfig {
    public static boolean ADD_ADDITIONAL_DATA_IN_PACKET = false;
    public static boolean DEBUG = false;
    private static final String LOGTAG = "MapplsLMSAPI.Conf";
    public static final String REFERRER_PREFS_NAME = "com.mappls.android.metrics.ReferralInfo";
    public static final String VERSION = "1.0.0";
    private static MapplsLMSConfig sInstance;
    private static final Object sInstanceLock = new Object();
    private final int mBulkUploadLimit;
    private final long mDataExpiration;
    private final boolean mDisableAppOpenEvent;
    private final boolean mDisableExceptionHandler;
    private String mEventsEndpoint;
    private String mFcmEventsEndpoint;
    private int mFlushBatchSize;
    private final int mFlushInterval;
    private final boolean mFlushOnBackground;
    private String mGroupsEndpoint;
    private int mMaximumDatabaseLimit;
    private final int mMinSessionDuration;
    private final int mMinimumDatabaseLimit;
    private OfflineMode mOfflineMode;
    private String mPeopleEndpoint;
    private final boolean mRemoveLegacyResidualFiles;
    private final String mResourcePackageName;
    private SSLSocketFactory mSSLSocketFactory;
    private final int mSessionTimeoutDuration;
    private boolean mTrackAutomaticEvents = true;
    private boolean mUseIpAddressForGeolocation;
    private String serverUrl;

    public MapplsLMSConfig(Bundle bundle, Context context) {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, null, null);
            sSLSocketFactory = sSLContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            MPLog.i("MapplsAnalyticsAPI.Conf", "System has no SSL support. Built-in events editor will not be available", e);
        }
        this.mSSLSocketFactory = sSLSocketFactory;
        boolean z = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.EnableDebugLogging", false);
        DEBUG = z;
        if (z) {
            MPLog.setLevel(2);
        }
        if (bundle.containsKey("com.mappls.android.MapplsAnalyticsConfig.DebugFlushInterval")) {
            MPLog.w(LOGTAG, "We do not support com.mappls.android.MapplsAnalyticsConfig.DebugFlushInterval anymore. There will only be one flush interval. Please, update your AndroidManifest.xml.");
        }
        this.mBulkUploadLimit = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.BulkUploadLimit", 40);
        this.mFlushInterval = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.FlushInterval", 60000);
        this.mFlushBatchSize = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.FlushBatchSize", 50);
        this.mFlushOnBackground = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.FlushOnBackground", true);
        this.mMinimumDatabaseLimit = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.MinimumDatabaseLimit", 20971520);
        this.mMaximumDatabaseLimit = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.MaximumDatabaseLimit", Integer.MAX_VALUE);
        this.mResourcePackageName = bundle.getString("com.mappls.android.MapplsAnalyticsConfig.ResourcePackageName");
        this.mDisableAppOpenEvent = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.DisableAppOpenEvent", true);
        this.mDisableExceptionHandler = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.DisableExceptionHandler", false);
        this.mMinSessionDuration = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.MinimumSessionDuration", 10000);
        this.mSessionTimeoutDuration = bundle.getInt("com.mappls.android.MapplsAnalyticsConfig.SessionTimeoutDuration", Integer.MAX_VALUE);
        this.mUseIpAddressForGeolocation = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.UseIpAddressForGeolocation", false);
        this.mRemoveLegacyResidualFiles = bundle.getBoolean("com.mappls.android.MapplsAnalyticsConfig.RemoveLegacyResidualFiles", false);
        Object obj = bundle.get("com.mappls.android.MapplsAnalyticsConfig.DataExpiration");
        long j = 432000000;
        if (obj != null) {
            try {
                if (obj instanceof Integer) {
                    j = ((Integer) obj).intValue();
                } else if (!(obj instanceof Float)) {
                    throw new NumberFormatException(obj.toString() + " is not a number.");
                } else {
                    j = ((Float) obj).floatValue();
                }
            } catch (Exception e2) {
                MPLog.e(LOGTAG, "Error parsing com.mappls.android.MapplsAnalyticsConfig.DataExpiration meta-data value", e2);
            }
        }
        this.mDataExpiration = j;
        boolean containsKey = true ^ bundle.containsKey("com.mappls.android.MapplsAnalyticsConfig.UseIpAddressForGeolocation");
        String string = bundle.getString("com.mappls.android.MapplsAnalyticsConfig.EventsEndpoint");
        if (string != null) {
            setEventsEndpoint(containsKey ? string : getEndPointWithIpTrackingParam(string, getUseIpAddressForGeolocation()));
        } else {
            String str = this.serverUrl;
            if (str != null) {
                setEventsEndpointWithBaseURL(MapplsLMSConstants.URL.MAPPLS_ANALYTICS_API);
            } else {
                setEventsEndpointWithBaseURL(str);
            }
        }
        String string2 = bundle.getString("com.mappls.android.MapplsAnalyticsConfig.FcmEventsEndpoint");
        if (string2 != null) {
            setFcmEventsEndpoint(containsKey ? string2 : getEndPointWithIpTrackingParam(string2, getUseIpAddressForGeolocation()));
        } else {
            String str2 = this.serverUrl;
            if (str2 != null) {
                setFcmEventsEndpointWithBaseURL(MapplsLMSConstants.URL.MAPPLS_ANALYTICS_API);
            } else {
                setFcmEventsEndpointWithBaseURL(str2);
            }
        }
        String string3 = bundle.getString("com.mappls.android.MapplsAnalyticsConfig.PeopleEndpoint");
        if (string3 != null) {
            setPeopleEndpoint(containsKey ? string3 : getEndPointWithIpTrackingParam(string3, getUseIpAddressForGeolocation()));
        } else {
            String str3 = this.serverUrl;
            if (str3 != null) {
                setPeopleEndpointWithBaseURL(MapplsLMSConstants.URL.MAPPLS_ANALYTICS_API);
            } else {
                setPeopleEndpointWithBaseURL(str3);
            }
        }
        String string4 = bundle.getString("com.mappls.android.MapplsAnalyticsConfig.GroupsEndpoint");
        if (string4 != null) {
            setGroupsEndpoint(containsKey ? string4 : getEndPointWithIpTrackingParam(string4, getUseIpAddressForGeolocation()));
        } else {
            String str4 = this.serverUrl;
            if (str4 != null) {
                setGroupsEndpointWithBaseURL(MapplsLMSConstants.URL.MAPPLS_ANALYTICS_API);
            } else {
                setGroupsEndpointWithBaseURL(str4);
            }
        }
        MPLog.v(LOGTAG, toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1.append(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
        return r1.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003b, code lost:
        if (r8 != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r8 != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0027, code lost:
        r2 = com.jstyle.blesdk1860.constant.BleConst.GetDeviceTime;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getEndPointWithIpTrackingParam(java.lang.String r7, boolean r8) {
        /*
            r6 = this;
            boolean r0 = com.mappls.android.lms.MapplsLMSConfig.ADD_ADDITIONAL_DATA_IN_PACKET
            if (r0 == 0) goto L3e
            java.lang.String r0 = "?ip="
            boolean r1 = r7.contains(r0)
            java.lang.String r2 = "1"
            java.lang.String r3 = "0"
            if (r1 == 0) goto L30
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r4 = r7.indexOf(r0)
            r5 = 0
            java.lang.String r7 = r7.substring(r5, r4)
            r1.append(r7)
            r1.append(r0)
            if (r8 == 0) goto L27
            goto L28
        L27:
            r2 = r3
        L28:
            r1.append(r2)
            java.lang.String r7 = r1.toString()
            return r7
        L30:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r7)
            r1.append(r0)
            if (r8 == 0) goto L27
            goto L28
        L3e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.android.lms.MapplsLMSConfig.getEndPointWithIpTrackingParam(java.lang.String, boolean):java.lang.String");
    }

    public static MapplsLMSConfig getInstance(Context context) {
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                sInstance = readConfig(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    private boolean getUseIpAddressForGeolocation() {
        return this.mUseIpAddressForGeolocation;
    }

    public static MapplsLMSConfig readConfig(Context context) {
        String packageName = context.getPackageName();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(packageName, 128).metaData;
            if (bundle == null) {
                bundle = new Bundle();
            }
            return new MapplsLMSConfig(bundle, context);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Can't configure Mappls Analytics with package name " + packageName, e);
        }
    }

    private void setEventsEndpoint(String str) {
        this.mEventsEndpoint = str;
    }

    private void setEventsEndpointWithBaseURL(String str) {
        setEventsEndpoint(getEndPointWithIpTrackingParam(str + "lms/s2a2bvrrr", getUseIpAddressForGeolocation()));
    }

    private void setFcmEventsEndpointWithBaseURL(String str) {
        setFcmEventsEndpoint(getEndPointWithIpTrackingParam(str + "lms/s2a2bvrrr?flag=f1", getUseIpAddressForGeolocation()));
    }

    private void setGroupsEndpoint(String str) {
        this.mGroupsEndpoint = str;
    }

    private void setGroupsEndpointWithBaseURL(String str) {
        setGroupsEndpoint(getEndPointWithIpTrackingParam(str + "lms/s2a2bvrrr/groups/", getUseIpAddressForGeolocation()));
    }

    private void setPeopleEndpoint(String str) {
        this.mPeopleEndpoint = str;
    }

    private void setPeopleEndpointWithBaseURL(String str) {
        setPeopleEndpoint(getEndPointWithIpTrackingParam(str + "lms/s2a2bvrrr/engage/", getUseIpAddressForGeolocation()));
    }

    public int getBulkUploadLimit() {
        return this.mBulkUploadLimit;
    }

    public long getDataExpiration() {
        return this.mDataExpiration;
    }

    public boolean getDisableAppOpenEvent() {
        return this.mDisableAppOpenEvent;
    }

    public boolean getDisableExceptionHandler() {
        return this.mDisableExceptionHandler;
    }

    public String getEventsEndpoint() {
        return this.mEventsEndpoint;
    }

    public String getFcmEventsEndpoint() {
        return this.mFcmEventsEndpoint;
    }

    public int getFlushBatchSize() {
        return this.mFlushBatchSize;
    }

    public int getFlushInterval() {
        return this.mFlushInterval;
    }

    public boolean getFlushOnBackground() {
        return this.mFlushOnBackground;
    }

    public String getGroupsEndpoint() {
        return this.mGroupsEndpoint;
    }

    public int getMaximumDatabaseLimit() {
        return this.mMaximumDatabaseLimit;
    }

    public int getMinimumDatabaseLimit() {
        return this.mMinimumDatabaseLimit;
    }

    public int getMinimumSessionDuration() {
        return this.mMinSessionDuration;
    }

    public synchronized OfflineMode getOfflineMode() {
        return this.mOfflineMode;
    }

    public String getPeopleEndpoint() {
        return this.mPeopleEndpoint;
    }

    public boolean getRemoveLegacyResidualFiles() {
        return this.mRemoveLegacyResidualFiles;
    }

    public String getResourcePackageName() {
        return this.mResourcePackageName;
    }

    public synchronized SSLSocketFactory getSSLSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public int getSessionTimeoutDuration() {
        return this.mSessionTimeoutDuration;
    }

    public boolean getTrackAutomaticEvents() {
        return this.mTrackAutomaticEvents;
    }

    public void setEnableLogging(boolean z) {
        DEBUG = z;
        MPLog.setLevel(z ? 2 : Integer.MAX_VALUE);
    }

    public void setFcmEventsEndpoint(String str) {
        this.mFcmEventsEndpoint = str;
    }

    public void setFlushBatchSize(int i) {
        this.mFlushBatchSize = i;
    }

    public void setMaximumDatabaseLimit(int i) {
        this.mMaximumDatabaseLimit = i;
    }

    public synchronized void setOfflineMode(OfflineMode offlineMode) {
        this.mOfflineMode = offlineMode;
    }

    public synchronized void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.mSSLSocketFactory = sSLSocketFactory;
    }

    public void setServerURL(String str) {
        this.serverUrl = str;
        setEventsEndpointWithBaseURL(str);
        setPeopleEndpointWithBaseURL(str);
        setGroupsEndpointWithBaseURL(str);
    }

    public void setTrackAutomaticEvents(boolean z) {
        this.mTrackAutomaticEvents = z;
    }

    public void setUseIpAddressForGeolocation(boolean z) {
        this.mUseIpAddressForGeolocation = z;
        setEventsEndpoint(getEndPointWithIpTrackingParam(getEventsEndpoint(), z));
        setFcmEventsEndpoint(getEndPointWithIpTrackingParam(getFcmEventsEndpoint(), z));
        setPeopleEndpoint(getEndPointWithIpTrackingParam(getPeopleEndpoint(), z));
        setGroupsEndpoint(getEndPointWithIpTrackingParam(getGroupsEndpoint(), z));
    }

    public String toString() {
        return "Mappls Analytics (1.0.0) configured with:\n    TrackAutomaticEvents: " + getTrackAutomaticEvents() + "\n    BulkUploadLimit " + getBulkUploadLimit() + "\n    FlushInterval " + getFlushInterval() + "\n    FlushInterval " + getFlushBatchSize() + "\n    DataExpiration " + getDataExpiration() + "\n    MinimumDatabaseLimit " + getMinimumDatabaseLimit() + "\n    MaximumDatabaseLimit " + getMaximumDatabaseLimit() + "\n    DisableAppOpenEvent " + getDisableAppOpenEvent() + "\n    EnableDebugLogging " + DEBUG + "\n    EventsEndpoint " + getEventsEndpoint() + "\n    PeopleEndpoint " + getPeopleEndpoint() + "\n    MinimumSessionDuration: " + getMinimumSessionDuration() + "\n    SessionTimeoutDuration: " + getSessionTimeoutDuration() + "\n    DisableExceptionHandler: " + getDisableExceptionHandler() + "\n    FlushOnBackground: " + getFlushOnBackground();
    }
}
