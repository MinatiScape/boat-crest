package com.coveiot.android.dashboard2.util;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class Dashboard2Constants {
    @NotNull
    public static final String ACTION_SYNC_COMPLETE = "ACTION_SYNC_COMPLETE";
    public static final int AGPS_INTERVAL = 1;
    @NotNull
    public static final String ALL_OFF = "ALL_OFF";
    @NotNull
    public static final String ALL_ON = "ALL_ON";
    public static final int BUTTON_4H_POSITION = 4;
    public static final int CONNECTED = 2;
    public static final int CONNECTING = 1;
    public static final int DATA_SYNC_FAILURE_THRESHOLD = 3;
    public static final int DISCONNECTED = 0;
    public static final int DISCOVERING_SERVICE = 4;
    @NotNull
    public static final String EMPTY_HYPHEN = "-";
    @NotNull
    public static final String EMPTY_STRING = "";
    @NotNull
    public static final String FEMALE = "Female";
    @NotNull
    public static final String IS_FROM_SCHEDULER = "IS_FROM_SCHEDULER";
    @NotNull
    public static final String JSTYLE_AGPS_FILE_NAME = "Jstyle_AGPS";
    @NotNull
    public static final String LONG_PRESS = "LONG_PRESS";
    @NotNull
    public static final String MALE = "Male";
    public static final float MAX_FILTER_VALUE = 41.1f;
    @NotNull
    public static final String MEDIA_VOLUME_CHANGE_BROADCAST_INTENT = "android.media.VOLUME_CHANGED_ACTION";
    public static final float MIN_FILTER_VALUE = 34.9f;
    @NotNull
    public static final String MUSIC_APP_PLAYBACK_STATE_CHANGED = "music_app_playback_state_changed";
    @NotNull
    public static final String MUSIC_METADATA_BROADCAST_INTENT = "music_metadata_broadcast";
    @NotNull
    public static final String MUSIC_METADATA_KEY_ALBUM = "album";
    @NotNull
    public static final String MUSIC_METADATA_KEY_ARTIST = "artist";
    @NotNull
    public static final String MUSIC_METADATA_KEY_TITLE = "title";
    @NotNull
    public static final String MUSIC_PLAYBACK_STATE_CHANGE_BROADCAST_INTENT = "music_playback_state_change_broadcast";
    public static final int SCANNING = 3;
    @NotNull
    public static final String SHIPBOOK_APP_ID = "606c0ff57cb97d56542c7afd";
    @NotNull
    public static final String SHIPBOOK_APP_KEY = "20e2af52bcb0facabc15bac7ea72e51a";
    @NotNull
    public static final String SHIPBOOK_STATE = "shipbook_state";
    @NotNull
    public static final String SHORT_PRESS = "SHORT_PRESS";
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f4251a = "ACTION_LOW_BATTERY_NOTIFICATION_SHOW";
    @NotNull
    public static final String b = "ACTION_LOW_BATTERY_NOTIFICATION_DISMISS";
    @NotNull
    public static final String c = "TROUBLESHOOT_NOTIFICATION_CLICKED";

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getACTION_LOW_BATTERY_NOTIFICATION_DISMISS() {
            return Dashboard2Constants.b;
        }

        @NotNull
        public final String getACTION_LOW_BATTERY_NOTIFICATION_SHOW() {
            return Dashboard2Constants.f4251a;
        }

        @NotNull
        public final String getTROUBLESHOOT_NOTIFICATION_CLICKED() {
            return Dashboard2Constants.c;
        }
    }
}
