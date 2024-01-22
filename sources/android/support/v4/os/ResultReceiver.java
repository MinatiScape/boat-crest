package android.support.v4.os;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.os.IResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
@SuppressLint({"BanParcelableUsage"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new a();
    public final boolean h;
    public final Handler i;
    public IResultReceiver j;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<ResultReceiver> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ResultReceiver createFromParcel(Parcel parcel) {
            return new ResultReceiver(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ResultReceiver[] newArray(int i) {
            return new ResultReceiver[i];
        }
    }

    /* loaded from: classes.dex */
    public class b extends IResultReceiver.Stub {
        public b() {
        }

        @Override // android.support.v4.os.IResultReceiver
        public void send(int i, Bundle bundle) {
            ResultReceiver resultReceiver = ResultReceiver.this;
            Handler handler = resultReceiver.i;
            if (handler != null) {
                handler.post(new c(i, bundle));
            } else {
                resultReceiver.onReceiveResult(i, bundle);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final int h;
        public final Bundle i;

        public c(int i, Bundle bundle) {
            this.h = i;
            this.i = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResultReceiver.this.onReceiveResult(this.h, this.i);
        }
    }

    public ResultReceiver(Handler handler) {
        this.h = true;
        this.i = handler;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void onReceiveResult(int i, Bundle bundle) {
    }

    public void send(int i, Bundle bundle) {
        if (this.h) {
            Handler handler = this.i;
            if (handler != null) {
                handler.post(new c(i, bundle));
                return;
            } else {
                onReceiveResult(i, bundle);
                return;
            }
        }
        IResultReceiver iResultReceiver = this.j;
        if (iResultReceiver != null) {
            try {
                iResultReceiver.send(i, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        synchronized (this) {
            if (this.j == null) {
                this.j = new b();
            }
            parcel.writeStrongBinder(this.j.asBinder());
        }
    }

    public ResultReceiver(Parcel parcel) {
        this.h = false;
        this.i = null;
        this.j = IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
    }
}
