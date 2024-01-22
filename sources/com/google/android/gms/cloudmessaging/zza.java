package com.google.android.gms.cloudmessaging;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.cloudmessaging.IMessengerCompat;
/* loaded from: classes6.dex */
public class zza implements Parcelable {
    public static final Parcelable.Creator<zza> CREATOR = new a();
    public Messenger h;
    public IMessengerCompat i;

    /* renamed from: com.google.android.gms.cloudmessaging.zza$zza  reason: collision with other inner class name */
    /* loaded from: classes6.dex */
    public static final class C0373zza extends ClassLoader {
        @Override // java.lang.ClassLoader
        public final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if ("com.google.android.gms.iid.MessengerCompat".equals(str)) {
                if (Log.isLoggable("CloudMessengerCompat", 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable("CloudMessengerCompat", 3))) {
                    Log.d("CloudMessengerCompat", "Using renamed FirebaseIidMessengerCompat class");
                    return zza.class;
                }
                return zza.class;
            }
            return super.loadClass(str, z);
        }
    }

    public zza(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.h = new Messenger(iBinder);
        } else {
            this.i = new IMessengerCompat.Proxy(iBinder);
        }
    }

    public final IBinder a() {
        Messenger messenger = this.h;
        return messenger != null ? messenger.getBinder() : this.i.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return a().equals(((zza) obj).a());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return a().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        Messenger messenger = this.h;
        if (messenger != null) {
            parcel.writeStrongBinder(messenger.getBinder());
        } else {
            parcel.writeStrongBinder(this.i.asBinder());
        }
    }

    public final void zza(Message message) throws RemoteException {
        Messenger messenger = this.h;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.i.send(message);
        }
    }
}
