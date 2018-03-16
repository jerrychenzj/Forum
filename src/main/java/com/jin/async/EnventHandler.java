package com.jin.async;

import java.util.List;

/**
 * Created by JINS on 2018/3/6.
 */
public interface EnventHandler {
     void doHandler(EnventModel enventModel);
     List<EnventType> getSupportEnventTypes();
}
