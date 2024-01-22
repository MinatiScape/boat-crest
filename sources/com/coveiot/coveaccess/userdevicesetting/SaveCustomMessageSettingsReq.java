package com.coveiot.coveaccess.userdevicesetting;
/* loaded from: classes8.dex */
public class SaveCustomMessageSettingsReq {

    /* renamed from: a  reason: collision with root package name */
    public NudgeMessages f6877a;

    /* loaded from: classes8.dex */
    public static class NudgeMessages {

        /* renamed from: a  reason: collision with root package name */
        public Nudges f6878a;
        public Vibration b;

        /* loaded from: classes8.dex */
        public static class Nudges {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6879a;

            public boolean isActive() {
                return this.f6879a;
            }

            public void setActive(boolean z) {
                this.f6879a = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class Vibration {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6880a;

            public boolean isActive() {
                return this.f6880a;
            }

            public void setActive(boolean z) {
                this.f6880a = z;
            }
        }

        public Nudges getNudges() {
            return this.f6878a;
        }

        public Vibration getVibration() {
            return this.b;
        }

        public void setNudges(Nudges nudges) {
            this.f6878a = nudges;
        }

        public void setVibration(Vibration vibration) {
            this.b = vibration;
        }
    }

    public NudgeMessages getNudgeMessages() {
        return this.f6877a;
    }

    public void setNudgeMessages(NudgeMessages nudgeMessages) {
        this.f6877a = nudgeMessages;
    }
}
