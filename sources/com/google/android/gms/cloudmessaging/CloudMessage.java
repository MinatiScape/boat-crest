package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
@SafeParcelable.Class(creator = "CloudMessageCreator")
/* loaded from: classes6.dex */
public final class CloudMessage extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CloudMessage> CREATOR = new zzb();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @NonNull
    @SafeParcelable.Field(id = 1)
    public Intent h;
    @GuardedBy("this")
    public Map<String, String> i;

    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface MessagePriority {
    }

    @SafeParcelable.Constructor
    public CloudMessage(@NonNull @SafeParcelable.Param(id = 1) Intent intent) {
        this.h = intent;
    }

    public static int zza(@Nullable String str) {
        if (Constants.PRIORITY_HIGH.equals(str)) {
            return 1;
        }
        return Constants.PRIORITY_NORMAL.equals(str) ? 2 : 0;
    }

    @Nullable
    public final String getCollapseKey() {
        return this.h.getStringExtra(Constants.MessagePayloadKeys.COLLAPSE_KEY);
    }

    @NonNull
    public final synchronized Map<String, String> getData() {
        if (this.i == null) {
            Bundle extras = this.h.getExtras();
            ArrayMap arrayMap = new ArrayMap();
            if (extras != null) {
                for (String str : extras.keySet()) {
                    Object obj = extras.get(str);
                    if (obj instanceof String) {
                        String str2 = (String) obj;
                        if (!str.startsWith(Constants.MessagePayloadKeys.RESERVED_PREFIX) && !str.equals("from") && !str.equals(Constants.MessagePayloadKeys.MESSAGE_TYPE) && !str.equals(Constants.MessagePayloadKeys.COLLAPSE_KEY)) {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
            this.i = arrayMap;
        }
        return this.i;
    }

    @Nullable
    public final String getFrom() {
        return this.h.getStringExtra("from");
    }

    @NonNull
    public final Intent getIntent() {
        return this.h;
    }

    @Nullable
    public final String getMessageId() {
        String stringExtra = this.h.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        return stringExtra == null ? this.h.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER) : stringExtra;
    }

    @Nullable
    public final String getMessageType() {
        return this.h.getStringExtra(Constants.MessagePayloadKeys.MESSAGE_TYPE);
    }

    public final int getOriginalPriority() {
        String stringExtra = this.h.getStringExtra(Constants.MessagePayloadKeys.ORIGINAL_PRIORITY);
        if (stringExtra == null) {
            stringExtra = this.h.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return zza(stringExtra);
    }

    public final int getPriority() {
        String stringExtra = this.h.getStringExtra(Constants.MessagePayloadKeys.DELIVERED_PRIORITY);
        if (stringExtra == null) {
            if ("1".equals(this.h.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_REDUCED_V19))) {
                return 2;
            }
            stringExtra = this.h.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return zza(stringExtra);
    }

    @Nullable
    public final byte[] getRawData() {
        return this.h.getByteArrayExtra(Constants.MessagePayloadKeys.RAW_DATA);
    }

    @Nullable
    public final String getSenderId() {
        return this.h.getStringExtra(Constants.MessagePayloadKeys.SENDER_ID);
    }

    public final long getSentTime() {
        Bundle extras = this.h.getExtras();
        Object obj = extras != null ? extras.get(Constants.MessagePayloadKeys.SENT_TIME) : null;
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
                Log.w("CloudMessage", sb.toString());
                return 0L;
            }
        }
        return 0L;
    }

    @Nullable
    public final String getTo() {
        return this.h.getStringExtra(Constants.MessagePayloadKeys.TO);
    }

    public final int getTtl() {
        Bundle extras = this.h.getExtras();
        Object obj = extras != null ? extras.get(Constants.MessagePayloadKeys.TTL) : null;
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
                Log.w("CloudMessage", sb.toString());
                return 0;
            }
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
