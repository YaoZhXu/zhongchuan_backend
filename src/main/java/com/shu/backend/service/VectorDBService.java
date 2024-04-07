package com.shu.backend.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface VectorDBService {

    boolean createCollection(String collectionName);

    boolean dropCollection(String collectionName);

    List<String> showCollections();

    boolean hasCollection(String collectionName);

    boolean createPartition(String collectionName, String partitionName);

    boolean dropPartition(String collectionName, String partitionName);

    List<String> showPartitions(String collectionName);

    boolean hasPartition(String collectionName, String partitionName);

    void insert(String collectionName, String partitionName, List<JSONObject> rows);

    void update(String collectionName, String partitionName, List<JSONObject> rows);

    void delete(String collectionName, String partitionName, String expr);

//    void query();
//
//    void search();
}
