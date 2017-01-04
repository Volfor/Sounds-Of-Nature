package com.github.volfor.sondsofnature.events;

import android.support.annotation.RawRes;

/**
 * Created by Volfor on 04.01.2017.
 * http://github.com/Volfor
 */

public class ListenEvent {

    @RawRes
    public int soundId;

    public ListenEvent(@RawRes int soundId) {
        this.soundId = soundId;
    }
}
