package com.google.android.gms.gcm;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.RequiresPermission;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import org.jose4j.jwx.HeaderParameterNames;
/* loaded from: classes6.dex */
public class Task implements ReflectedParcelable {
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    public static final long UNINITIALIZED = -1;
    public final String h;
    public final String i;
    public final boolean j;
    public final boolean k;
    public final int l;
    public final Set<Uri> m;
    public final boolean n;
    public final zzl o;
    public final Bundle p;

    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public Bundle extras;
        public String gcmTaskService;
        public boolean isPersisted;
        public int requiredNetworkState;
        public boolean requiresCharging;
        public String tag;
        public boolean updateCurrent;
        public Set<Uri> zzaw = Collections.emptySet();
        @ShowFirstParty
        public zzl zzay = zzl.zzaq;

        public abstract Task build();

        @CallSuper
        public void checkConditions() {
            Preconditions.checkArgument(this.gcmTaskService != null, "Must provide an endpoint for this task by calling setService(ComponentName).");
            GcmNetworkManager.a(this.tag);
            zzl zzlVar = this.zzay;
            if (zzlVar != null) {
                int zzi = zzlVar.zzi();
                if (zzi != 1 && zzi != 0) {
                    StringBuilder sb = new StringBuilder(45);
                    sb.append("Must provide a valid RetryPolicy: ");
                    sb.append(zzi);
                    throw new IllegalArgumentException(sb.toString());
                }
                int zzj = zzlVar.zzj();
                int zzk = zzlVar.zzk();
                if (zzi == 0 && zzj < 0) {
                    StringBuilder sb2 = new StringBuilder(52);
                    sb2.append("InitialBackoffSeconds can't be negative: ");
                    sb2.append(zzj);
                    throw new IllegalArgumentException(sb2.toString());
                } else if (zzi == 1 && zzj < 10) {
                    throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                } else {
                    if (zzk < zzj) {
                        int zzk2 = zzlVar.zzk();
                        StringBuilder sb3 = new StringBuilder(77);
                        sb3.append("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: ");
                        sb3.append(zzk2);
                        throw new IllegalArgumentException(sb3.toString());
                    }
                }
            }
            if (this.isPersisted) {
                Task.zzg(this.extras);
            }
            if (!this.zzaw.isEmpty() && this.requiredNetworkState == 2) {
                throw new IllegalArgumentException("Required URIs may not be used with NETWORK_STATE_ANY");
            }
            for (Uri uri : this.zzaw) {
                Task.a(uri);
            }
        }

        public abstract Builder setExtras(Bundle bundle);

        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public abstract Builder setPersisted(boolean z);

        public abstract Builder setRequiredNetwork(int i);

        public abstract Builder setRequiresCharging(boolean z);

        public abstract Builder setService(Class<? extends GcmTaskService> cls);

        public abstract Builder setTag(String str);

        public abstract Builder setUpdateCurrent(boolean z);
    }

    public Task(Builder builder) {
        this.h = builder.gcmTaskService;
        this.i = builder.tag;
        this.j = builder.updateCurrent;
        this.k = builder.isPersisted;
        this.l = builder.requiredNetworkState;
        this.m = builder.zzaw;
        this.n = builder.requiresCharging;
        this.p = builder.extras;
        zzl zzlVar = builder.zzay;
        this.o = zzlVar == null ? zzl.zzaq : zzlVar;
    }

    public static void a(Uri uri) {
        if (uri != null) {
            String scheme = uri.getScheme();
            String host = uri.getHost();
            if (!TextUtils.isEmpty(host) && !"null".equals(host)) {
                try {
                    int port = uri.getPort();
                    if (!"tcp".equals(scheme)) {
                        if (!"ping".equals(scheme)) {
                            String valueOf = String.valueOf(scheme);
                            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported required URI scheme: ".concat(valueOf) : new String("Unsupported required URI scheme: "));
                        } else if (port != -1) {
                            throw new IllegalArgumentException("Ping does not support port numbers");
                        } else {
                            return;
                        }
                    } else if (port <= 0 || port > 65535) {
                        int port2 = uri.getPort();
                        StringBuilder sb = new StringBuilder(38);
                        sb.append("Invalid required URI port: ");
                        sb.append(port2);
                        throw new IllegalArgumentException(sb.toString());
                    } else {
                        return;
                    }
                } catch (NumberFormatException e) {
                    String valueOf2 = String.valueOf(e.getMessage());
                    throw new IllegalArgumentException(valueOf2.length() != 0 ? "Invalid port number: ".concat(valueOf2) : new String("Invalid port number: "));
                }
            }
            throw new IllegalArgumentException("URI hostname is required");
        }
        throw new IllegalArgumentException("Null URI");
    }

    public static void zzg(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize <= 10240) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (!((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean))) {
                        if (obj instanceof Bundle) {
                            zzg((Bundle) obj);
                        } else {
                            throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                        }
                    }
                }
                return;
            }
            StringBuilder sb = new StringBuilder(55);
            sb.append("Extras exceeding maximum size(10240 bytes): ");
            sb.append(dataSize);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getExtras() {
        return this.p;
    }

    public int getRequiredNetwork() {
        return this.l;
    }

    public boolean getRequiresCharging() {
        return this.n;
    }

    public String getServiceName() {
        return this.h;
    }

    public String getTag() {
        return this.i;
    }

    public boolean isPersisted() {
        return this.k;
    }

    public boolean isUpdateCurrent() {
        return this.j;
    }

    public void toBundle(Bundle bundle) {
        bundle.putString(HeaderParameterNames.AUTHENTICATION_TAG, this.i);
        bundle.putBoolean("update_current", this.j);
        bundle.putBoolean("persisted", this.k);
        bundle.putString(NotificationCompat.CATEGORY_SERVICE, this.h);
        bundle.putInt("requiredNetwork", this.l);
        if (!this.m.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (Uri uri : this.m) {
                arrayList.add(uri.toString());
            }
            bundle.putStringArrayList("reachabilityUris", arrayList);
        }
        bundle.putBoolean("requiresCharging", this.n);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", this.o.zzf(new Bundle()));
        bundle.putBundle(NotificationCompat.MessagingStyle.Message.KEY_EXTRAS_BUNDLE, this.p);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeInt(this.k ? 1 : 0);
    }

    @Deprecated
    public Task(Parcel parcel) {
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readInt() == 1;
        this.k = parcel.readInt() == 1;
        this.l = 2;
        this.m = Collections.emptySet();
        this.n = false;
        this.o = zzl.zzaq;
        this.p = null;
    }
}
