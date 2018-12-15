package com.example.eventmanagementapi.mmscontrollers;

import com.example.eventmanagementapi.mmsentities.CmPushBatch;
import com.example.eventmanagementapi.mmsrepositories.CmMessageRepository;
import com.example.eventmanagementapi.mmsrepositories.CmPushBatchesRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@RepositoryRestController
@RequestMapping("/messaging/core/batches")
@Api(value="mmsmessaging", description="Operations connected to push batches")
public class CmPushBatchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CmPushBatchesRepository repository;

    @Autowired
    private CmMessageRepository messageRepository;

    @ApiOperation(value = "Return first available batch for given channel if any", response = CmPushBatch.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved push batch"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public CmPushBatch getBatchForChannel(@RequestParam("channelId") int channelId){
        logger.info("Requested batch for channel " + channelId);
        CmPushBatch batch = repository.findFirstByChannelIdOrderByCreated(channelId);
        if (batch==null){
            logger.info("No batch available for channelId " + channelId);
            CmPushBatch nullBatch = new CmPushBatch();
            nullBatch.setChannelId(channelId);
            nullBatch.setStatus(-1);
            nullBatch.setCreated(ZonedDateTime.of(1900,1,1,1,1,1,1, ZoneId.of("Europe/Paris")));
            nullBatch.setBatchId(null);
            return nullBatch;
        }
        logger.info("Batch found, batchId=" + batch.getBatchId());
        return batch;
    }

    @ApiOperation(value = "After batch of messages is processed, batch must be flagged as processed", response = CmPushBatch.class)
    @PostMapping("/setBatchProcessed")
    @Transactional
    public ResponseEntity setBatchProcessed(@RequestParam("batchId") Long batchId){
        //http://localhost:8080/eventmanagement-api/events/start/1
        Optional<CmPushBatch> batch = repository.findById(batchId);
        CmPushBatch b;
        if (batch.isPresent()){
            b = batch.get();
            messageRepository.updateMessageStatus(3,b);
            b.setConsumed(ZonedDateTime.now());
            b.setStatus(3);
            repository.save(b);
        } else{
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok("Batch with id " + b.getBatchId() + " has been processed");
    }
}
