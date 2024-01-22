package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetMusicMetaDataRequest extends BleBaseRequest {
    public String album;
    public String artist;
    public String title;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public String album;
        public String artist;
        public String title;

        public SetMusicMetaDataRequest build() {
            SetMusicMetaDataRequest setMusicMetaDataRequest = new SetMusicMetaDataRequest();
            setMusicMetaDataRequest.artist = this.artist;
            setMusicMetaDataRequest.album = this.album;
            setMusicMetaDataRequest.title = this.title;
            return setMusicMetaDataRequest;
        }

        public Builder setAlbum(String str) {
            this.album = str;
            return this;
        }

        public Builder setArtist(String str) {
            this.artist = str;
            return this;
        }

        public Builder setTitle(String str) {
            this.title = str;
            return this;
        }
    }
}
