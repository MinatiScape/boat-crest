package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleMusicRespond {
    public String artist;
    public String content;
    public int duration;
    public MusicStatus e_status;
    public int elapsedtime;
    public int volume;

    /* loaded from: classes.dex */
    public enum MusicStatus {
        not_play(0),
        playing(1),
        stop_play(2);
        
        public int value;

        MusicStatus(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public String getArtist() {
        return this.artist;
    }

    public String getContent() {
        return this.content;
    }

    public int getDuration() {
        return this.duration;
    }

    public MusicStatus getE_status() {
        return this.e_status;
    }

    public int getElapsedtime() {
        return this.elapsedtime;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public void setE_status(MusicStatus musicStatus) {
        this.e_status = musicStatus;
    }

    public void setElapsedtime(int i) {
        this.elapsedtime = i;
    }

    public void setVolume(int i) {
        this.volume = i;
    }
}
