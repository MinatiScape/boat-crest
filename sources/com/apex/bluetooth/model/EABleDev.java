package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDev {
    public DevOps e_ops;
    public DevOpsStatus e_ops_status;

    /* loaded from: classes.dex */
    public enum DevOps {
        restore_factory(0),
        reset(1),
        power_off(2),
        disconnect_ble(3),
        entering_flight_mode(4),
        light_up_the_screen(5),
        turn_off_the_screen(6),
        stop_search_phone(7),
        enter_factory_test_mode(8),
        exit_factory_test_mode(9),
        start_search_watch(10),
        stop_search_watch(11),
        stop_camera(12),
        ble_sec_enc_start(13);
        
        public int value;

        DevOps(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum DevOpsStatus {
        execute(0),
        success(1),
        fail(2);
        
        public int value;

        DevOpsStatus(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public DevOps getE_ops() {
        return this.e_ops;
    }

    public DevOpsStatus getE_ops_status() {
        return this.e_ops_status;
    }

    public void setE_ops(DevOps devOps) {
        this.e_ops = devOps;
    }

    public void setE_ops_status(DevOpsStatus devOpsStatus) {
        this.e_ops_status = devOpsStatus;
    }
}
