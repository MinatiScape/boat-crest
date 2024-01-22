package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.media.session.c;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final long ACTION_FAST_FORWARD = 64;
    public static final long ACTION_PAUSE = 2;
    public static final long ACTION_PLAY = 4;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048;
    public static final long ACTION_PLAY_FROM_URI = 8192;
    public static final long ACTION_PLAY_PAUSE = 512;
    public static final long ACTION_PREPARE = 16384;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768;
    public static final long ACTION_PREPARE_FROM_SEARCH = 65536;
    public static final long ACTION_PREPARE_FROM_URI = 131072;
    public static final long ACTION_REWIND = 8;
    public static final long ACTION_SEEK_TO = 256;
    public static final long ACTION_SET_CAPTIONING_ENABLED = 1048576;
    public static final long ACTION_SET_RATING = 128;
    public static final long ACTION_SET_REPEAT_MODE = 262144;
    public static final long ACTION_SET_SHUFFLE_MODE = 2097152;
    @Deprecated
    public static final long ACTION_SET_SHUFFLE_MODE_ENABLED = 524288;
    public static final long ACTION_SKIP_TO_NEXT = 32;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096;
    public static final long ACTION_STOP = 1;
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new a();
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_INVALID = -1;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_INVALID = -1;
    public static final int SHUFFLE_MODE_NONE = 0;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    public final int h;
    public final long i;
    public final long j;
    public final float k;
    public final long l;
    public final int m;
    public final CharSequence n;
    public final long o;
    public List<CustomAction> p;
    public final long q;
    public final Bundle r;
    public Object s;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface Actions {
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List<CustomAction> f337a;
        public int b;
        public long c;
        public long d;
        public float e;
        public long f;
        public int g;
        public CharSequence h;
        public long i;
        public long j;
        public Bundle k;

        public Builder() {
            this.f337a = new ArrayList();
            this.j = -1L;
        }

        public Builder addCustomAction(String str, String str2, int i) {
            return addCustomAction(new CustomAction(str, str2, i, null));
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.f337a, this.j, this.k);
        }

        public Builder setActions(long j) {
            this.f = j;
            return this;
        }

        public Builder setActiveQueueItemId(long j) {
            this.j = j;
            return this;
        }

        public Builder setBufferedPosition(long j) {
            this.d = j;
            return this;
        }

        public Builder setErrorMessage(CharSequence charSequence) {
            this.h = charSequence;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.k = bundle;
            return this;
        }

        public Builder setState(int i, long j, float f) {
            return setState(i, j, f, SystemClock.elapsedRealtime());
        }

        public Builder addCustomAction(CustomAction customAction) {
            if (customAction != null) {
                this.f337a.add(customAction);
                return this;
            }
            throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
        }

        public Builder setErrorMessage(int i, CharSequence charSequence) {
            this.g = i;
            this.h = charSequence;
            return this;
        }

        public Builder setState(int i, long j, float f, long j2) {
            this.b = i;
            this.c = j;
            this.i = j2;
            this.e = f;
            return this;
        }

        public Builder(PlaybackStateCompat playbackStateCompat) {
            ArrayList arrayList = new ArrayList();
            this.f337a = arrayList;
            this.j = -1L;
            this.b = playbackStateCompat.h;
            this.c = playbackStateCompat.i;
            this.e = playbackStateCompat.k;
            this.i = playbackStateCompat.o;
            this.d = playbackStateCompat.j;
            this.f = playbackStateCompat.l;
            this.g = playbackStateCompat.m;
            this.h = playbackStateCompat.n;
            List<CustomAction> list = playbackStateCompat.p;
            if (list != null) {
                arrayList.addAll(list);
            }
            this.j = playbackStateCompat.q;
            this.k = playbackStateCompat.r;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface MediaKeyAction {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface RepeatMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ShuffleMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface State {
    }

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<PlaybackStateCompat> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PlaybackStateCompat createFromParcel(Parcel parcel) {
            return new PlaybackStateCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public PlaybackStateCompat[] newArray(int i) {
            return new PlaybackStateCompat[i];
        }
    }

    public PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List<CustomAction> list, long j5, Bundle bundle) {
        this.h = i;
        this.i = j;
        this.j = j2;
        this.k = f;
        this.l = j3;
        this.m = i2;
        this.n = charSequence;
        this.o = j4;
        this.p = new ArrayList(list);
        this.q = j5;
        this.r = bundle;
    }

    public static PlaybackStateCompat fromPlaybackState(Object obj) {
        ArrayList arrayList;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        List<Object> d = c.d(obj);
        if (d != null) {
            ArrayList arrayList2 = new ArrayList(d.size());
            for (Object obj2 : d) {
                arrayList2.add(CustomAction.fromCustomAction(obj2));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(c.i(obj), c.h(obj), c.c(obj), c.g(obj), c.a(obj), 0, c.e(obj), c.f(obj), arrayList, c.b(obj), Build.VERSION.SDK_INT >= 22 ? d.a(obj) : null);
        playbackStateCompat.s = obj;
        return playbackStateCompat;
    }

    public static int toKeyCode(long j) {
        if (j == 4) {
            return 126;
        }
        if (j == 2) {
            return 127;
        }
        if (j == 32) {
            return 87;
        }
        if (j == 16) {
            return 88;
        }
        if (j == 1) {
            return 86;
        }
        if (j == 64) {
            return 90;
        }
        if (j == 8) {
            return 89;
        }
        return j == 512 ? 85 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getActions() {
        return this.l;
    }

    public long getActiveQueueItemId() {
        return this.q;
    }

    public long getBufferedPosition() {
        return this.j;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getCurrentPosition(Long l) {
        return Math.max(0L, this.i + (this.k * ((float) (l != null ? l.longValue() : SystemClock.elapsedRealtime() - this.o))));
    }

    public List<CustomAction> getCustomActions() {
        return this.p;
    }

    public int getErrorCode() {
        return this.m;
    }

    public CharSequence getErrorMessage() {
        return this.n;
    }

    @Nullable
    public Bundle getExtras() {
        return this.r;
    }

    public long getLastPositionUpdateTime() {
        return this.o;
    }

    public float getPlaybackSpeed() {
        return this.k;
    }

    public Object getPlaybackState() {
        if (this.s == null && Build.VERSION.SDK_INT >= 21) {
            ArrayList arrayList = null;
            if (this.p != null) {
                arrayList = new ArrayList(this.p.size());
                for (CustomAction customAction : this.p) {
                    arrayList.add(customAction.getCustomAction());
                }
            }
            ArrayList arrayList2 = arrayList;
            if (Build.VERSION.SDK_INT >= 22) {
                this.s = d.b(this.h, this.i, this.j, this.k, this.l, this.n, this.o, arrayList2, this.q, this.r);
            } else {
                this.s = c.j(this.h, this.i, this.j, this.k, this.l, this.n, this.o, arrayList2, this.q);
            }
        }
        return this.s;
    }

    public long getPosition() {
        return this.i;
    }

    public int getState() {
        return this.h;
    }

    public String toString() {
        return "PlaybackState {state=" + this.h + ", position=" + this.i + ", buffered position=" + this.j + ", speed=" + this.k + ", updated=" + this.o + ", actions=" + this.l + ", error code=" + this.m + ", error message=" + this.n + ", custom actions=" + this.p + ", active item id=" + this.q + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.h);
        parcel.writeLong(this.i);
        parcel.writeFloat(this.k);
        parcel.writeLong(this.o);
        parcel.writeLong(this.j);
        parcel.writeLong(this.l);
        TextUtils.writeToParcel(this.n, parcel, i);
        parcel.writeTypedList(this.p);
        parcel.writeLong(this.q);
        parcel.writeBundle(this.r);
        parcel.writeInt(this.m);
    }

    /* loaded from: classes.dex */
    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new a();
        public final String h;
        public final CharSequence i;
        public final int j;
        public final Bundle k;
        public Object l;

        /* loaded from: classes.dex */
        public static final class Builder {

            /* renamed from: a  reason: collision with root package name */
            public final String f338a;
            public final CharSequence b;
            public final int c;
            public Bundle d;

            public Builder(String str, CharSequence charSequence, int i) {
                if (!TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(charSequence)) {
                        throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
                    }
                    if (i != 0) {
                        this.f338a = str;
                        this.b = charSequence;
                        this.c = i;
                        return;
                    }
                    throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
                }
                throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
            }

            public CustomAction build() {
                return new CustomAction(this.f338a, this.b, this.c, this.d);
            }

            public Builder setExtras(Bundle bundle) {
                this.d = bundle;
                return this;
            }
        }

        /* loaded from: classes.dex */
        public static class a implements Parcelable.Creator<CustomAction> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public CustomAction createFromParcel(Parcel parcel) {
                return new CustomAction(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public CustomAction[] newArray(int i) {
                return new CustomAction[i];
            }
        }

        public CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.h = str;
            this.i = charSequence;
            this.j = i;
            this.k = bundle;
        }

        public static CustomAction fromCustomAction(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(c.a.a(obj), c.a.d(obj), c.a.c(obj), c.a.b(obj));
            customAction.l = obj;
            return customAction;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getAction() {
            return this.h;
        }

        public Object getCustomAction() {
            Object obj = this.l;
            if (obj != null || Build.VERSION.SDK_INT < 21) {
                return obj;
            }
            Object e = c.a.e(this.h, this.i, this.j, this.k);
            this.l = e;
            return e;
        }

        public Bundle getExtras() {
            return this.k;
        }

        public int getIcon() {
            return this.j;
        }

        public CharSequence getName() {
            return this.i;
        }

        public String toString() {
            return "Action:mName='" + ((Object) this.i) + ", mIcon=" + this.j + ", mExtras=" + this.k;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.h);
            TextUtils.writeToParcel(this.i, parcel, i);
            parcel.writeInt(this.j);
            parcel.writeBundle(this.k);
        }

        public CustomAction(Parcel parcel) {
            this.h = parcel.readString();
            this.i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.j = parcel.readInt();
            this.k = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = parcel.readLong();
        this.k = parcel.readFloat();
        this.o = parcel.readLong();
        this.j = parcel.readLong();
        this.l = parcel.readLong();
        this.n = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.p = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.q = parcel.readLong();
        this.r = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        this.m = parcel.readInt();
    }
}
