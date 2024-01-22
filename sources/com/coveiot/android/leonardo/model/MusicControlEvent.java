package com.coveiot.android.leonardo.model;
/* loaded from: classes2.dex */
public class MusicControlEvent {

    /* renamed from: a  reason: collision with root package name */
    public Control f4855a;

    /* loaded from: classes2.dex */
    public enum Control {
        PLAY,
        PAUSE,
        NEXT,
        PREVIOUS
    }

    public MusicControlEvent(Control control) {
        this.f4855a = control;
    }

    public Control getControl() {
        return this.f4855a;
    }
}
