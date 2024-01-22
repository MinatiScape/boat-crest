package androidx.media;

import androidx.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class AudioAttributesImplBaseParcelizer {
    public static AudioAttributesImplBase read(VersionedParcel versionedParcel) {
        AudioAttributesImplBase audioAttributesImplBase = new AudioAttributesImplBase();
        audioAttributesImplBase.f1397a = versionedParcel.readInt(audioAttributesImplBase.f1397a, 1);
        audioAttributesImplBase.b = versionedParcel.readInt(audioAttributesImplBase.b, 2);
        audioAttributesImplBase.c = versionedParcel.readInt(audioAttributesImplBase.c, 3);
        audioAttributesImplBase.d = versionedParcel.readInt(audioAttributesImplBase.d, 4);
        return audioAttributesImplBase;
    }

    public static void write(AudioAttributesImplBase audioAttributesImplBase, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(audioAttributesImplBase.f1397a, 1);
        versionedParcel.writeInt(audioAttributesImplBase.b, 2);
        versionedParcel.writeInt(audioAttributesImplBase.c, 3);
        versionedParcel.writeInt(audioAttributesImplBase.d, 4);
    }
}
