package com.shu.backend.facade.milvus;

import com.alibaba.fastjson.JSONObject;
import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.*;
import io.milvus.param.*;
import io.milvus.param.collection.*;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.QueryParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.param.dml.UpsertParam;
import io.milvus.param.index.CreateIndexParam;
import io.milvus.param.partition.CreatePartitionParam;
import io.milvus.param.partition.DropPartitionParam;
import io.milvus.param.partition.HasPartitionParam;
import io.milvus.param.partition.ShowPartitionsParam;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MilvusManageFacade {

    public static final String CHUNK_ID = "chunk_id";
    public static final String DOC_ID = "doc_id";
    public static final String CORPUS_id = "corpus_id";
    public static final String CHUNK_VECTOR = "chunk_vector";
    public static final Integer VECTOR_DIM = 1024;

    @Resource
    private MilvusServiceClient milvusClient;

    public R<RpcStatus> createCollection(String collectionName, String description) {
        List<FieldType> fieldsSchema = Arrays.asList(
                FieldType.newBuilder()
                        .withName(CHUNK_ID)
                        .withDataType(DataType.Int64)
                        .withPrimaryKey(true)
                        .build(),
                FieldType.newBuilder()
                        .withName(DOC_ID)
                        .withDataType(DataType.Int64)
                        .build(),
                FieldType.newBuilder()
                        .withName(CORPUS_id)
                        .withDataType(DataType.Int64)
                        .build(),
                FieldType.newBuilder()
                        .withName(CHUNK_VECTOR)
                        .withDataType(DataType.FloatVector)
                        .withDimension(VECTOR_DIM)
                        .build()
        );

        CreateCollectionParam createCollectionReq = CreateCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .withDescription(description)
                .withFieldTypes(fieldsSchema)
                .build();

        R<RpcStatus> r = milvusClient.createCollection(createCollectionReq);
        return r;
    }

    public R<Boolean> hasCollection(String collectionName) {
        R<Boolean> r = milvusClient.hasCollection(HasCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<RpcStatus> dropCollection(String collectionName) {
        R<RpcStatus> r = milvusClient.dropCollection(DropCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<ShowCollectionsResponse> showCollections() {
        R<ShowCollectionsResponse> r = milvusClient.showCollections(ShowCollectionsParam.newBuilder()
                .build());
        return r;
    }

    public R<RpcStatus> loadCollection(String collectionName) {
        R<RpcStatus> r = milvusClient.loadCollection(LoadCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<RpcStatus> releaseCollection(String collectionName) {
        R<RpcStatus> r = milvusClient.releaseCollection(ReleaseCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<DescribeCollectionResponse> describeCollection(String collectionName) {
        R<DescribeCollectionResponse> r = milvusClient.describeCollection(DescribeCollectionParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<RpcStatus> createPartition(String collectionName, String partitionName) {
        R<RpcStatus> r = milvusClient.createPartition(CreatePartitionParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionName(partitionName)
                .build());
        return r;
    }

    public R<RpcStatus> dropPartition(String collectionName, String partitionName) {
        R<RpcStatus> r = milvusClient.dropPartition(DropPartitionParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionName(partitionName)
                .build());
        return r;
    }

    public R<Boolean> hasPartition(String collectionName, String partitionName) {
        R<Boolean> r = milvusClient.hasPartition(HasPartitionParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionName(partitionName)
                .build());
        return r;
    }

    public R<ShowPartitionsResponse> showPartitions(String collectionName) {
        R<ShowPartitionsResponse> r = milvusClient.showPartitions(ShowPartitionsParam.newBuilder()
                .withCollectionName(collectionName)
                .build());
        return r;
    }

    public R<RpcStatus> createIndex(String collectionName, String fieldName, String indexName,
                                    IndexType indexType, MetricType metricType) {
        R<RpcStatus> r = milvusClient.createIndex(CreateIndexParam.newBuilder()
                .withCollectionName(collectionName)
                .withFieldName(fieldName)
                .withIndexName(indexName)
                .withIndexType(indexType)
                .withMetricType(metricType)
                .build());
        return r;
    }

    public R<MutationResult> insert(List<JSONObject> rows, String collectionName, String partitionName) {
        R<MutationResult> r = milvusClient.insert(InsertParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionName(partitionName)
                .withRows(rows)
                .build());
        return r;
    }

    public R<MutationResult> upsert(List<JSONObject> rows, String collectionName, String partitionName) {
        R<MutationResult> r = milvusClient.upsert(UpsertParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionName(partitionName)
                .withRows(rows)
                .build());
        return r;
    }

    public R<QueryResults> query(String collectionName, List<String> partitions, String expr, String outField) {
        QueryParam queryParam = QueryParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionNames(partitions)
                .withExpr(expr)
                .addOutField(outField)
                .build();
        R<QueryResults> r = milvusClient.query(queryParam);
        System.out.println(r.toString());
        return r;
    }

    public R<SearchResults> search(List<Float> vector, String collectionName, List<String> partitions,
                                   MetricType metricType, Integer topK, String vectorFieldName, String outField) {
        R<SearchResults> r = milvusClient.search(SearchParam.newBuilder()
                .withCollectionName(collectionName)
                .withPartitionNames(partitions)
                .withMetricType(metricType)
                .withTopK(topK)
                .withVectors(Collections.singletonList(vector))
                .withVectorFieldName(vectorFieldName)
                .withParams("{}")
                .addOutField(outField)
                .build());
        System.out.println(r.toString());
        return r;
    }
}
