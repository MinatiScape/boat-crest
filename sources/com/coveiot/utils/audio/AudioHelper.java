package com.coveiot.utils.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import java.io.IOException;
/* loaded from: classes9.dex */
public class AudioHelper {

    /* renamed from: a  reason: collision with root package name */
    public MediaPlayer f7610a;
    public Context b;

    /* loaded from: classes9.dex */
    public class a implements MediaPlayer.OnCompletionListener {
        public a() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            AudioHelper.this.release();
        }
    }

    public AudioHelper(Context context) {
        this.b = context;
        this.f7610a = new MediaPlayer();
    }

    public void killAfterAudioPlayed() {
        this.f7610a.setOnCompletionListener(new a());
    }

    public void pause() {
        MediaPlayer mediaPlayer = this.f7610a;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.f7610a.pause();
    }

    public void release() {
        try {
            MediaPlayer mediaPlayer = this.f7610a;
            if (mediaPlayer != null) {
                mediaPlayer.reset();
                this.f7610a.release();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        MediaPlayer mediaPlayer = this.f7610a;
        if (mediaPlayer == null || mediaPlayer.isPlaying()) {
            return;
        }
        MediaPlayer mediaPlayer2 = this.f7610a;
        mediaPlayer2.seekTo(mediaPlayer2.getCurrentPosition());
        this.f7610a.start();
    }

    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        MediaPlayer mediaPlayer = this.f7610a;
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(onCompletionListener);
        }
    }

    public void setPath(Uri uri) {
        if (this.f7610a == null) {
            this.f7610a = new MediaPlayer();
        }
        this.f7610a.reset();
        try {
            this.f7610a.setDataSource(this.b, uri);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            this.f7610a.prepare();
        } catch (IOException | IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void start() {
        MediaPlayer mediaPlayer = this.f7610a;
        if (mediaPlayer == null || mediaPlayer.isPlaying()) {
            return;
        }
        this.f7610a.start();
    }

    public void stop() {
        try {
            MediaPlayer mediaPlayer = this.f7610a;
            if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
                return;
            }
            this.f7610a.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public AudioHelper(Context context, String str) {
        this.b = context;
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f7610a = mediaPlayer;
        try {
            mediaPlayer.setDataSource(str);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            this.f7610a.prepare();
        } catch (IOException | IllegalStateException e2) {
            e2.printStackTrace();
        }
    }
}
