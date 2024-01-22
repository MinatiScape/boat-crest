package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleQueryMusic {
    public PlayerType e_app;

    /* loaded from: classes.dex */
    public enum PlayerType {
        default_type(0),
        apple_music(1),
        deeze(2),
        spotify(3);
        
        public int value;

        PlayerType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public PlayerType getE_app() {
        return this.e_app;
    }

    public void setE_app(PlayerType playerType) {
        this.e_app = playerType;
    }
}
