package androidx.media;

import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.Arrays;
/* loaded from: classes.dex */
class AudioAttributesImplBase implements AudioAttributesImpl {

    /* renamed from: a  reason: collision with root package name */
    public int f1397a;
    public int b;
    public int c;
    public int d;

    public AudioAttributesImplBase() {
        this.f1397a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
    }

    public static AudioAttributesImpl c(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return new AudioAttributesImplBase(bundle.getInt("androidx.media.audio_attrs.CONTENT_TYPE", 0), bundle.getInt("androidx.media.audio_attrs.FLAGS", 0), bundle.getInt("androidx.media.audio_attrs.USAGE", 0), bundle.getInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", -1));
    }

    @Override // androidx.media.AudioAttributesImpl
    public int a() {
        return this.d;
    }

    @Override // androidx.media.AudioAttributesImpl
    public Object b() {
        return null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioAttributesImplBase) {
            AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
            return this.b == audioAttributesImplBase.getContentType() && this.c == audioAttributesImplBase.getFlags() && this.f1397a == audioAttributesImplBase.getUsage() && this.d == audioAttributesImplBase.d;
        }
        return false;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getContentType() {
        return this.b;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getFlags() {
        int i = this.c;
        int legacyStreamType = getLegacyStreamType();
        if (legacyStreamType == 6) {
            i |= 4;
        } else if (legacyStreamType == 7) {
            i |= 1;
        }
        return i & 273;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getLegacyStreamType() {
        int i = this.d;
        return i != -1 ? i : AudioAttributesCompat.c(false, this.c, this.f1397a);
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getUsage() {
        return this.f1397a;
    }

    @Override // androidx.media.AudioAttributesImpl
    public int getVolumeControlStream() {
        return AudioAttributesCompat.c(true, this.c, this.f1397a);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.c), Integer.valueOf(this.f1397a), Integer.valueOf(this.d)});
    }

    @Override // androidx.media.AudioAttributesImpl
    @NonNull
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("androidx.media.audio_attrs.USAGE", this.f1397a);
        bundle.putInt("androidx.media.audio_attrs.CONTENT_TYPE", this.b);
        bundle.putInt("androidx.media.audio_attrs.FLAGS", this.c);
        int i = this.d;
        if (i != -1) {
            bundle.putInt("androidx.media.audio_attrs.LEGACY_STREAM_TYPE", i);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
        if (this.d != -1) {
            sb.append(" stream=");
            sb.append(this.d);
            sb.append(" derived");
        }
        sb.append(" usage=");
        sb.append(AudioAttributesCompat.e(this.f1397a));
        sb.append(" content=");
        sb.append(this.b);
        sb.append(" flags=0x");
        sb.append(Integer.toHexString(this.c).toUpperCase());
        return sb.toString();
    }

    public AudioAttributesImplBase(int i, int i2, int i3, int i4) {
        this.f1397a = 0;
        this.b = 0;
        this.c = 0;
        this.d = -1;
        this.b = i;
        this.c = i2;
        this.f1397a = i3;
        this.d = i4;
    }
}
