package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleMusicControl {
    public MusicControl e_ops;
    public long elapsedtime;
    public int volume;

    /* loaded from: classes.dex */
    public enum MusicControl {
        play_start(0),
        play_stop(1),
        previous_song(2),
        next_song(3),
        volume_up(4),
        volume_reduction(5);
        
        public int value;

        MusicControl(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public MusicControl getE_ops() {
        return this.e_ops;
    }

    public long getElapsedtime() {
        return this.elapsedtime;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setE_ops(MusicControl musicControl) {
        this.e_ops = musicControl;
    }

    public void setElapsedtime(long j) {
        this.elapsedtime = j;
    }

    public void setVolume(int i) {
        this.volume = i;
    }
}
