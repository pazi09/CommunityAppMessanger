package com.example.CommunityAppMessanger.utils;

import java.util.HashMap;
import java.util.Map;

public class HouseHolder {

    private static final Map<Long,Long> houseCtx=new HashMap<>();

    public static synchronized Long getHouseByUserId(Long userId){
        return houseCtx.get(userId);
    }

    public static synchronized void saveHouseForUser(Long userId,Long houseId){
        houseCtx.put(userId,houseId);
    }

    public static synchronized void deleteHouseForUser(Long userId){
        houseCtx.remove(userId);
    }



}
