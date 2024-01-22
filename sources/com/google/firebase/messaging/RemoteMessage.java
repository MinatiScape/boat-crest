package com.google.firebase.messaging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
@SafeParcelable.Class(creator = "RemoteMessageCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class RemoteMessage extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RemoteMessage> CREATOR = new RemoteMessageCreator();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @SafeParcelable.Field(id = 2)
    public Bundle h;
    public Map<String, String> i;
    public Notification j;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Bundle f11333a;
        public final Map<String, String> b;

        public Builder(@NonNull String str) {
            Bundle bundle = new Bundle();
            this.f11333a = bundle;
            this.b = new ArrayMap();
            if (TextUtils.isEmpty(str)) {
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid to: ".concat(valueOf) : new String("Invalid to: "));
            } else {
                bundle.putString(Constants.MessagePayloadKeys.TO, str);
            }
        }

        @NonNull
        public Builder addData(@NonNull String str, @Nullable String str2) {
            this.b.put(str, str2);
            return this;
        }

        @NonNull
        public RemoteMessage build() {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, String> entry : this.b.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
            bundle.putAll(this.f11333a);
            this.f11333a.remove("from");
            return new RemoteMessage(bundle);
        }

        @NonNull
        public Builder clearData() {
            this.b.clear();
            return this;
        }

        @NonNull
        public Builder setCollapseKey(@Nullable String str) {
            this.f11333a.putString(Constants.MessagePayloadKeys.COLLAPSE_KEY, str);
            return this;
        }

        @NonNull
        public Builder setData(@NonNull Map<String, String> map) {
            this.b.clear();
            this.b.putAll(map);
            return this;
        }

        @NonNull
        public Builder setMessageId(@NonNull String str) {
            this.f11333a.putString(Constants.MessagePayloadKeys.MSGID, str);
            return this;
        }

        @NonNull
        public Builder setMessageType(@Nullable String str) {
            this.f11333a.putString(Constants.MessagePayloadKeys.MESSAGE_TYPE, str);
            return this;
        }

        @NonNull
        @ShowFirstParty
        public Builder setRawData(@NonNull byte[] bArr) {
            this.f11333a.putByteArray(Constants.MessagePayloadKeys.RAW_DATA, bArr);
            return this;
        }

        @NonNull
        public Builder setTtl(@IntRange(from = 0, to = 86400) int i) {
            this.f11333a.putString(Constants.MessagePayloadKeys.TTL, String.valueOf(i));
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface MessagePriority {
    }

    /* loaded from: classes10.dex */
    public static class Notification {

        /* renamed from: a  reason: collision with root package name */
        public final String f11334a;
        public final String b;
        public final String[] c;
        public final String d;
        public final String e;
        public final String[] f;
        public final String g;
        public final String h;
        public final String i;
        public final String j;
        public final String k;
        public final String l;
        public final String m;
        public final Uri n;
        public final String o;
        public final Integer p;
        public final Integer q;
        public final Integer r;
        public final int[] s;
        public final Long t;
        public final boolean u;
        public final boolean v;
        public final boolean w;
        public final boolean x;
        public final boolean y;
        public final long[] z;

        public Notification(NotificationParams notificationParams) {
            this.f11334a = notificationParams.getString(Constants.MessageNotificationKeys.TITLE);
            this.b = notificationParams.getLocalizationResourceForKey(Constants.MessageNotificationKeys.TITLE);
            this.c = a(notificationParams, Constants.MessageNotificationKeys.TITLE);
            this.d = notificationParams.getString(Constants.MessageNotificationKeys.BODY);
            this.e = notificationParams.getLocalizationResourceForKey(Constants.MessageNotificationKeys.BODY);
            this.f = a(notificationParams, Constants.MessageNotificationKeys.BODY);
            this.g = notificationParams.getString(Constants.MessageNotificationKeys.ICON);
            this.i = notificationParams.getSoundResourceName();
            this.j = notificationParams.getString(Constants.MessageNotificationKeys.TAG);
            this.k = notificationParams.getString(Constants.MessageNotificationKeys.COLOR);
            this.l = notificationParams.getString(Constants.MessageNotificationKeys.CLICK_ACTION);
            this.m = notificationParams.getString(Constants.MessageNotificationKeys.CHANNEL);
            this.n = notificationParams.getLink();
            this.h = notificationParams.getString(Constants.MessageNotificationKeys.IMAGE_URL);
            this.o = notificationParams.getString(Constants.MessageNotificationKeys.TICKER);
            this.p = notificationParams.getInteger(Constants.MessageNotificationKeys.NOTIFICATION_PRIORITY);
            this.q = notificationParams.getInteger(Constants.MessageNotificationKeys.VISIBILITY);
            this.r = notificationParams.getInteger(Constants.MessageNotificationKeys.NOTIFICATION_COUNT);
            this.u = notificationParams.getBoolean(Constants.MessageNotificationKeys.STICKY);
            this.v = notificationParams.getBoolean(Constants.MessageNotificationKeys.LOCAL_ONLY);
            this.w = notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_SOUND);
            this.x = notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_VIBRATE_TIMINGS);
            this.y = notificationParams.getBoolean(Constants.MessageNotificationKeys.DEFAULT_LIGHT_SETTINGS);
            this.t = notificationParams.getLong(Constants.MessageNotificationKeys.EVENT_TIME);
            this.s = notificationParams.b();
            this.z = notificationParams.getVibrateTimings();
        }

        public static String[] a(NotificationParams notificationParams, String str) {
            Object[] localizationArgsForKey = notificationParams.getLocalizationArgsForKey(str);
            if (localizationArgsForKey == null) {
                return null;
            }
            String[] strArr = new String[localizationArgsForKey.length];
            for (int i = 0; i < localizationArgsForKey.length; i++) {
                strArr[i] = String.valueOf(localizationArgsForKey[i]);
            }
            return strArr;
        }

        @Nullable
        public String getBody() {
            return this.d;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.f;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.e;
        }

        @Nullable
        public String getChannelId() {
            return this.m;
        }

        @Nullable
        public String getClickAction() {
            return this.l;
        }

        @Nullable
        public String getColor() {
            return this.k;
        }

        public boolean getDefaultLightSettings() {
            return this.y;
        }

        public boolean getDefaultSound() {
            return this.w;
        }

        public boolean getDefaultVibrateSettings() {
            return this.x;
        }

        @Nullable
        public Long getEventTime() {
            return this.t;
        }

        @Nullable
        public String getIcon() {
            return this.g;
        }

        @Nullable
        public Uri getImageUrl() {
            String str = this.h;
            if (str != null) {
                return Uri.parse(str);
            }
            return null;
        }

        @Nullable
        public int[] getLightSettings() {
            return this.s;
        }

        @Nullable
        public Uri getLink() {
            return this.n;
        }

        public boolean getLocalOnly() {
            return this.v;
        }

        @Nullable
        public Integer getNotificationCount() {
            return this.r;
        }

        @Nullable
        public Integer getNotificationPriority() {
            return this.p;
        }

        @Nullable
        public String getSound() {
            return this.i;
        }

        public boolean getSticky() {
            return this.u;
        }

        @Nullable
        public String getTag() {
            return this.j;
        }

        @Nullable
        public String getTicker() {
            return this.o;
        }

        @Nullable
        public String getTitle() {
            return this.f11334a;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.c;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.b;
        }

        @Nullable
        public long[] getVibrateTimings() {
            return this.z;
        }

        @Nullable
        public Integer getVisibility() {
            return this.q;
        }
    }

    @SafeParcelable.Constructor
    public RemoteMessage(@NonNull @SafeParcelable.Param(id = 2) Bundle bundle) {
        this.h = bundle;
    }

    public final int a(String str) {
        if (com.clevertap.android.sdk.Constants.PRIORITY_HIGH.equals(str)) {
            return 1;
        }
        return com.clevertap.android.sdk.Constants.PRIORITY_NORMAL.equals(str) ? 2 : 0;
    }

    public void b(Intent intent) {
        intent.putExtras(this.h);
    }

    @Nullable
    public String getCollapseKey() {
        return this.h.getString(Constants.MessagePayloadKeys.COLLAPSE_KEY);
    }

    @NonNull
    public Map<String, String> getData() {
        if (this.i == null) {
            this.i = Constants.MessagePayloadKeys.extractDeveloperDefinedPayload(this.h);
        }
        return this.i;
    }

    @Nullable
    public String getFrom() {
        return this.h.getString("from");
    }

    @Nullable
    public String getMessageId() {
        String string = this.h.getString(Constants.MessagePayloadKeys.MSGID);
        return string == null ? this.h.getString(Constants.MessagePayloadKeys.MSGID_SERVER) : string;
    }

    @Nullable
    public String getMessageType() {
        return this.h.getString(Constants.MessagePayloadKeys.MESSAGE_TYPE);
    }

    @Nullable
    public Notification getNotification() {
        if (this.j == null && NotificationParams.isNotification(this.h)) {
            this.j = new Notification(new NotificationParams(this.h));
        }
        return this.j;
    }

    public int getOriginalPriority() {
        String string = this.h.getString(Constants.MessagePayloadKeys.ORIGINAL_PRIORITY);
        if (string == null) {
            string = this.h.getString(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return a(string);
    }

    public int getPriority() {
        String string = this.h.getString(Constants.MessagePayloadKeys.DELIVERED_PRIORITY);
        if (string == null) {
            if ("1".equals(this.h.getString(Constants.MessagePayloadKeys.PRIORITY_REDUCED_V19))) {
                return 2;
            }
            string = this.h.getString(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return a(string);
    }

    @Nullable
    @ShowFirstParty
    public byte[] getRawData() {
        return this.h.getByteArray(Constants.MessagePayloadKeys.RAW_DATA);
    }

    @Nullable
    public String getSenderId() {
        return this.h.getString(Constants.MessagePayloadKeys.SENDER_ID);
    }

    public long getSentTime() {
        Object obj = this.h.get(Constants.MessagePayloadKeys.SENT_TIME);
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(valueOf.length() + 19);
                sb.append("Invalid sent time: ");
                sb.append(valueOf);
                Log.w(Constants.TAG, sb.toString());
                return 0L;
            }
        }
        return 0L;
    }

    @Nullable
    public String getTo() {
        return this.h.getString(Constants.MessagePayloadKeys.TO);
    }

    public int getTtl() {
        Object obj = this.h.get(Constants.MessagePayloadKeys.TTL);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(valueOf.length() + 13);
                sb.append("Invalid TTL: ");
                sb.append(valueOf);
                Log.w(Constants.TAG, sb.toString());
                return 0;
            }
        }
        return 0;
    }

    @NonNull
    @KeepForSdk
    public Intent toIntent() {
        Intent intent = new Intent();
        intent.putExtras(this.h);
        return intent;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        RemoteMessageCreator.a(this, parcel, i);
    }
}
