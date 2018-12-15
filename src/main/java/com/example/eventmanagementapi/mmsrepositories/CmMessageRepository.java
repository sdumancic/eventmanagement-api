package com.example.eventmanagementapi.mmsrepositories;

import com.example.eventmanagementapi.mmsentities.CmMessage;
import com.example.eventmanagementapi.mmsentities.CmPushBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CmMessageRepository extends PagingAndSortingRepository<CmMessage,Long>
{
    CmMessage findFirstByBatchIdOrderByCreated(Long batchId);
    List<CmMessage> findAllByBatchIdOrderByCreated(CmPushBatch batch);

    Page<CmMessage> findAllByBatchId(CmPushBatch batch, Pageable pageable);

    @Modifying
    @Query("UPDATE CmMessage m SET m.status = :status WHERE m.batchId = :batchId")
    int updateMessageStatus(@Param("status") int status, @Param("batchId") CmPushBatch batch);


    @Query(
            value = "SELECT * FROM CM_MESSAGES m WHERE m.batch_id = :batch_id ORDER BY message_id",
            countQuery = "SELECT count(*) FROM CM_MESSAGES m WHERE m.batch_id = :batch_id",
            nativeQuery = true)
    Page<CmMessage> findAllByBatchIdNative(@Param("batch_id") Long batchId, Pageable pageable);


}
