package com.polurival.fandroidvktest.model;

import com.vk.sdk.api.model.Identifiable;

/**
 * Created by Polurival
 * on 27.08.2017.
 */

public interface Owner extends Identifiable {

    String getFullName();

    String getPhoto();
}
