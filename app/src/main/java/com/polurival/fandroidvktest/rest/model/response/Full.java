package com.polurival.fandroidvktest.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Polurival
 * on 19.08.2017.
 */

public class Full<T> {
    @SerializedName("response")
    @Expose
    public T response;
}
