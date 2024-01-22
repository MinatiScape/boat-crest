package com.coveiot.coveaccess.timeline.model;

import com.coveiot.coveaccess.mediauplaod.model.MediaClassType;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadReq;
import java.io.File;
/* loaded from: classes8.dex */
public class MediaUploadWithPlaceIdReq extends MediaUploadReq {
    public String placeId;

    public MediaUploadWithPlaceIdReq(MediaClassType mediaClassType, File file, String str) {
        super(mediaClassType, file);
        this.placeId = str;
    }

    public String getPlaceId() {
        return this.placeId;
    }
}
