package com.shu.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import com.shu.backend.facade.milvus.MilvusManageFacade;
import com.shu.backend.service.VectorDBService;
import io.milvus.grpc.ShowCollectionsResponse;
import io.milvus.grpc.ShowPartitionsResponse;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.R;
import io.milvus.param.RpcStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.shu.backend.facade.milvus.MilvusManageFacade.CHUNK_VECTOR;

@Service
public class VectorDBServiceImpl implements VectorDBService {

    @Resource
    private MilvusManageFacade milvusManageFacade;

    @Override
    public boolean createCollection(String collectionName) {
        R<RpcStatus> r = milvusManageFacade.createCollection(collectionName, "");
        if (r.getStatus() != R.Status.Success.getCode()) {
            return false;
        }
        milvusManageFacade.createIndex(collectionName, CHUNK_VECTOR, CHUNK_VECTOR, IndexType.FLAT, MetricType.L2);
        return true;
    }

    @Override
    public boolean dropCollection(String collectionName) {
        R<RpcStatus> r = milvusManageFacade.dropCollection(collectionName);
        return r.getStatus() == R.Status.Success.getCode();
    }

    @Override
    public List<String> showCollections() {
        R<ShowCollectionsResponse> r = milvusManageFacade.showCollections();
        if (r.getStatus() != R.Status.Success.getCode()) {
            return null;
        }

        List<ByteString> list = r.getData().getCollectionNamesList().asByteStringList();
        List<String> collections = list.stream().map(ByteString::toStringUtf8).collect(Collectors.toList());
        return collections;
    }

    @Override
    public boolean hasCollection(String collectionName) {
        R<Boolean> r = milvusManageFacade.hasCollection(collectionName);
        return r.getStatus() == R.Status.Success.getCode() && r.getData();
    }

    @Override
    public boolean createPartition(String collectionName, String partitionName) {
        R<RpcStatus> r = milvusManageFacade.createPartition(collectionName, partitionName);
        return r.getStatus() == R.Status.Success.getCode();
    }

    @Override
    public boolean dropPartition(String collectionName, String partitionName) {
        R<RpcStatus> r = milvusManageFacade.dropPartition(collectionName, partitionName);
        return r.getStatus() == R.Status.Success.getCode();
    }

    @Override
    public List<String> showPartitions(String collectionName) {
        R<ShowPartitionsResponse> r = milvusManageFacade.showPartitions(collectionName);
        if (r.getStatus() != R.Status.Success.getCode()) {
            return null;
        }

        List<ByteString> list = r.getData().getPartitionNamesList().asByteStringList();
        List<String> partitions = list.stream().map(ByteString::toStringUtf8).collect(Collectors.toList());
        return partitions;
    }

    @Override
    public boolean hasPartition(String collectionName, String partitionName) {
        R<Boolean> r = milvusManageFacade.hasPartition(collectionName, partitionName);
        return r.getStatus() == R.Status.Success.getCode() && r.getData();
    }

    @Override
    public void insert(String collectionName, String partitionName, List<JSONObject> rows) {

    }

    @Override
    public void update(String collectionName, String partitionName, List<JSONObject> rows) {

    }

    @Override
    public void delete(String collectionName, String partitionName, String expr) {

    }
}
