package com.jin.async;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JINS on 2018/3/6.
 */
public class EnventModel implements Serializable{
    private EnventType enventType;
    private int actionId;
    private int entityId;
    private int entityType;
    private int entityOwnerId;

    private Map<String,String> exts = new HashMap<>();

    public EnventType getEnventType() {
        return enventType;
    }

    public EnventModel setEnventType(EnventType enventType) {
        this.enventType = enventType;
        return this;
    }

    public int getActionId() {
        return actionId;
    }

    public EnventModel setActionId(int actionId) {
        this.actionId = actionId;
       return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EnventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EnventModel setEntityType(int entityType) {
        this.entityType = entityType;
         return this;
     }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EnventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public EnventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }
    public EnventModel setExt(String key,String value){
        this.exts.put(key,value);
        return this;
    }
    public String getExt(String key){
        return exts.get(key);
    }

    public  EnventModel(EnventType enventType){
        this.enventType = enventType;
    }
    public  EnventModel(){}
}
