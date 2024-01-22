package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
import com.coveiot.android.bleabstract.response.MusicControlState;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetMusicMetaDataPlaybackVolumeRequest extends BleBaseRequest {
    @Nullable
    public String f;
    @Nullable
    public String g;
    @Nullable
    public String h;
    public int i;
    public int j;
    @Nullable
    public MusicControlState k;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3535a;
        public int b;
        @Nullable
        public String c;
        @Nullable
        public String d;
        @Nullable
        public String e;
        @Nullable
        public MusicControlState f;

        @NotNull
        public final SetMusicMetaDataPlaybackVolumeRequest build() {
            SetMusicMetaDataPlaybackVolumeRequest setMusicMetaDataPlaybackVolumeRequest = new SetMusicMetaDataPlaybackVolumeRequest();
            setMusicMetaDataPlaybackVolumeRequest.setVolume(this.f3535a);
            setMusicMetaDataPlaybackVolumeRequest.setMaxVolume(this.b);
            setMusicMetaDataPlaybackVolumeRequest.setArtist(this.c);
            setMusicMetaDataPlaybackVolumeRequest.setAlbum(this.d);
            setMusicMetaDataPlaybackVolumeRequest.setTitle(this.e);
            setMusicMetaDataPlaybackVolumeRequest.setMusicControlState(this.f);
            return setMusicMetaDataPlaybackVolumeRequest;
        }

        @Nullable
        public final String getAlbum() {
            return this.d;
        }

        @Nullable
        public final String getArtist() {
            return this.c;
        }

        public final int getMaxVolume() {
            return this.b;
        }

        @Nullable
        public final MusicControlState getMusicControlState() {
            return this.f;
        }

        @Nullable
        public final String getTitle() {
            return this.e;
        }

        public final int getVolume() {
            return this.f3535a;
        }

        /* renamed from: setAlbum  reason: collision with other method in class */
        public final void m57setAlbum(@Nullable String str) {
            this.d = str;
        }

        /* renamed from: setArtist  reason: collision with other method in class */
        public final void m58setArtist(@Nullable String str) {
            this.c = str;
        }

        /* renamed from: setMaxVolume  reason: collision with other method in class */
        public final void m59setMaxVolume(int i) {
            this.b = i;
        }

        /* renamed from: setMusicControlState  reason: collision with other method in class */
        public final void m60setMusicControlState(@Nullable MusicControlState musicControlState) {
            this.f = musicControlState;
        }

        /* renamed from: setTitle  reason: collision with other method in class */
        public final void m61setTitle(@Nullable String str) {
            this.e = str;
        }

        /* renamed from: setVolume  reason: collision with other method in class */
        public final void m62setVolume(int i) {
            this.f3535a = i;
        }

        @NotNull
        public final Builder setAlbum(@Nullable String str) {
            this.d = str;
            return this;
        }

        @NotNull
        public final Builder setArtist(@Nullable String str) {
            this.c = str;
            return this;
        }

        @NotNull
        public final Builder setMaxVolume(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setMusicControlState(@Nullable MusicControlState musicControlState) {
            this.f = musicControlState;
            return this;
        }

        @NotNull
        public final Builder setTitle(@Nullable String str) {
            this.e = str;
            return this;
        }

        @NotNull
        public final Builder setVolume(int i) {
            this.f3535a = i;
            return this;
        }
    }

    @Nullable
    public final String getAlbum() {
        return this.g;
    }

    @Nullable
    public final String getArtist() {
        return this.f;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    @Nullable
    public BleCommand getBleCommand() {
        return BleCommand.SET_MUSIC_META_PLAYBACK_VOLUME_STATUS;
    }

    public final int getMaxVolume() {
        return this.j;
    }

    @Nullable
    public final MusicControlState getMusicControlState() {
        return this.k;
    }

    @Nullable
    public final String getTitle() {
        return this.h;
    }

    public final int getVolume() {
        return this.i;
    }

    public final void setAlbum(@Nullable String str) {
        this.g = str;
    }

    public final void setArtist(@Nullable String str) {
        this.f = str;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public void setBleCommand(@Nullable BleCommand bleCommand) {
        super.setBleCommand(bleCommand);
    }

    public final void setMaxVolume(int i) {
        this.j = i;
    }

    public final void setMusicControlState(@Nullable MusicControlState musicControlState) {
        this.k = musicControlState;
    }

    public final void setTitle(@Nullable String str) {
        this.h = str;
    }

    public final void setVolume(int i) {
        this.i = i;
    }

    @NotNull
    public String toString() {
        return "SetMusicMetaDataPlaybackVolumeRequest(artist=" + this.f + ", album=" + this.g + ", title=" + this.h + ", volume=" + this.i + ", maxVolume=" + this.j + ", musicControlState=" + this.k + ", bleCommand=" + getBleCommand() + HexStringBuilder.COMMENT_END_CHAR;
    }
}
