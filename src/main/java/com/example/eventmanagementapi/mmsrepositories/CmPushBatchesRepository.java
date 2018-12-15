package com.example.eventmanagementapi.mmsrepositories;

import com.example.eventmanagementapi.mmsentities.CmPushBatch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CmPushBatchesRepository extends CrudRepository<CmPushBatch,Long>
{
    @Query(
            value = "SELECT * FROM CM_PUSH_BATCHES b WHERE b.channel_id = :channel_id AND status = 1 ORDER BY batch_id",
            countQuery = "SELECT COUNT(*) FROM CM_PUSH_BATCHES b WHERE b.channel_id = :channel_id AND status = 1",
            nativeQuery = true)
    CmPushBatch findFirstByChannelIdOrderByCreated(@Param("channel_id") int channelId);

    List<CmPushBatch> findAllByChannelIdOrderByCreated(int channelId);
}
