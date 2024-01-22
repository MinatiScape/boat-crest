package com.mappls.sdk.services.api.directions.models;

import androidx.annotation.Nullable;
import com.mappls.sdk.services.api.directions.models.VoiceInstructions;
/* loaded from: classes11.dex */
public abstract class q extends VoiceInstructions {
    private final String announcement;
    private final Double distanceAlongGeometry;
    private final String ssmlAnnouncement;

    /* loaded from: classes11.dex */
    public static class b extends VoiceInstructions.Builder {

        /* renamed from: a  reason: collision with root package name */
        public Double f13202a;
        public String b;
        public String c;

        @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions.Builder
        public VoiceInstructions.Builder announcement(String str) {
            this.b = str;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions.Builder
        public VoiceInstructions build() {
            return new AutoValue_VoiceInstructions(this.f13202a, this.b, this.c);
        }

        @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions.Builder
        public VoiceInstructions.Builder distanceAlongGeometry(Double d) {
            this.f13202a = d;
            return this;
        }

        @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions.Builder
        public VoiceInstructions.Builder ssmlAnnouncement(String str) {
            this.c = str;
            return this;
        }

        public b() {
        }

        public b(VoiceInstructions voiceInstructions) {
            this.f13202a = voiceInstructions.distanceAlongGeometry();
            this.b = voiceInstructions.announcement();
            this.c = voiceInstructions.ssmlAnnouncement();
        }
    }

    public q(@Nullable Double d, @Nullable String str, @Nullable String str2) {
        this.distanceAlongGeometry = d;
        this.announcement = str;
        this.ssmlAnnouncement = str2;
    }

    @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions
    @Nullable
    public String announcement() {
        return this.announcement;
    }

    @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions
    @Nullable
    public Double distanceAlongGeometry() {
        return this.distanceAlongGeometry;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VoiceInstructions) {
            VoiceInstructions voiceInstructions = (VoiceInstructions) obj;
            Double d = this.distanceAlongGeometry;
            if (d != null ? d.equals(voiceInstructions.distanceAlongGeometry()) : voiceInstructions.distanceAlongGeometry() == null) {
                String str = this.announcement;
                if (str != null ? str.equals(voiceInstructions.announcement()) : voiceInstructions.announcement() == null) {
                    String str2 = this.ssmlAnnouncement;
                    if (str2 == null) {
                        if (voiceInstructions.ssmlAnnouncement() == null) {
                            return true;
                        }
                    } else if (str2.equals(voiceInstructions.ssmlAnnouncement())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.distanceAlongGeometry;
        int hashCode = ((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003;
        String str = this.announcement;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.ssmlAnnouncement;
        return hashCode2 ^ (str2 != null ? str2.hashCode() : 0);
    }

    @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions
    @Nullable
    public String ssmlAnnouncement() {
        return this.ssmlAnnouncement;
    }

    @Override // com.mappls.sdk.services.api.directions.models.VoiceInstructions
    public VoiceInstructions.Builder toBuilder() {
        return new b(this);
    }

    public String toString() {
        return "VoiceInstructions{distanceAlongGeometry=" + this.distanceAlongGeometry + ", announcement=" + this.announcement + ", ssmlAnnouncement=" + this.ssmlAnnouncement + "}";
    }
}
