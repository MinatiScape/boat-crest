package androidx.activity.result;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class IntentSenderRequest implements Parcelable {
    @NonNull
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new a();
    @NonNull
    public final IntentSender h;
    @Nullable
    public final Intent i;
    public final int j;
    public final int k;

    /* loaded from: classes.dex */
    public class a implements Parcelable.Creator<IntentSenderRequest> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public IntentSenderRequest createFromParcel(Parcel parcel) {
            return new IntentSenderRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public IntentSenderRequest[] newArray(int i) {
            return new IntentSenderRequest[i];
        }
    }

    public IntentSenderRequest(@NonNull IntentSender intentSender, @Nullable Intent intent, int i, int i2) {
        this.h = intentSender;
        this.i = intent;
        this.j = i;
        this.k = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public Intent getFillInIntent() {
        return this.i;
    }

    public int getFlagsMask() {
        return this.j;
    }

    public int getFlagsValues() {
        return this.k;
    }

    @NonNull
    public IntentSender getIntentSender() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(this.h, i);
        parcel.writeParcelable(this.i, i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public IntentSender f358a;
        public Intent b;
        public int c;
        public int d;

        public Builder(@NonNull IntentSender intentSender) {
            this.f358a = intentSender;
        }

        @NonNull
        public IntentSenderRequest build() {
            return new IntentSenderRequest(this.f358a, this.b, this.c, this.d);
        }

        @NonNull
        public Builder setFillInIntent(@Nullable Intent intent) {
            this.b = intent;
            return this;
        }

        @NonNull
        public Builder setFlags(int i, int i2) {
            this.d = i;
            this.c = i2;
            return this;
        }

        public Builder(@NonNull PendingIntent pendingIntent) {
            this(pendingIntent.getIntentSender());
        }
    }

    public IntentSenderRequest(@NonNull Parcel parcel) {
        this.h = (IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader());
        this.i = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.j = parcel.readInt();
        this.k = parcel.readInt();
    }
}
