package com.google.android.gms.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.ReflectedParcelable;
/* loaded from: classes6.dex */
public class MessengerCompat implements ReflectedParcelable {
    public static final Parcelable.Creator<MessengerCompat> CREATOR = new n();
    public Messenger h;
    public zzl i;

    public MessengerCompat(IBinder iBinder) {
        zzl zzmVar;
        if (Build.VERSION.SDK_INT >= 21) {
            this.h = new Messenger(iBinder);
            return;
        }
        if (iBinder == null) {
            zzmVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.iid.IMessengerCompat");
            if (queryLocalInterface instanceof zzl) {
                zzmVar = (zzl) queryLocalInterface;
            } else {
                zzmVar = new zzm(iBinder);
            }
        }
        this.i = zzmVar;
    }

    public final IBinder a() {
        Messenger messenger = this.h;
        return messenger != null ? messenger.getBinder() : this.i.asBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return a().equals(((MessengerCompat) obj).a());
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return a().hashCode();
    }

    public final void send(Message message) throws RemoteException {
        Messenger messenger = this.h;
        if (messenger != null) {
            messenger.send(message);
        } else {
            this.i.send(message);
        }
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
}
