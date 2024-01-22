package com.coveiot.coveaccess.ambientsound;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import java.util.List;
/* loaded from: classes8.dex */
public class GetAmbientSoundDataRes extends CoveApiResponseBaseModel {
    public List<AmbientSoundData> ambientSoundDataList;

    public GetAmbientSoundDataRes(int i) {
        super(i);
    }

    public List<AmbientSoundData> getAmbientSoundDataList() {
        return this.ambientSoundDataList;
    }

    public void setAmbientSoundDataList(List<AmbientSoundData> list) {
        this.ambientSoundDataList = list;
    }
}
