package com.example.eventmanagementapi.mmscontrollers;

import com.example.eventmanagementapi.mmsentities.CmMessage;
import com.example.eventmanagementapi.mmsentities.CmPushBatch;
import com.example.eventmanagementapi.mmsrepositories.CmMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/messaging/core/messages")
public class CmPushMessagesController {

    /*

        http://localhost:8080/mmsmessaging-api/v2/api-docs
        http://localhost:8080/mmsmessaging-api/swagger-ui.html

        Redoslijed:
            1) http://localhost:8080/mmsmessaging-api/messaging/core/batches?channelId=3
            2) http://localhost:8080/mmsmessaging-api/messaging/core/messages/all?batchId=1
               ili
               http://localhost:8080/mmsmessaging-api/messaging/core/messages/paged?batchId=1
            3) http://localhost:8080/mmsmessaging-api/messaging/core/batches/setBatchProcessed?batchId=1

     */


    @Autowired
    private CmMessageRepository repository;

    /*
        http://localhost:8080/eventmanagement-api/messaging/core/messages?batchId=1
    * */
    @RequestMapping(value="/single", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public CmMessage getFirstMessageForBatch(@RequestParam("batchId") Long batchId){
        CmMessage message = repository.findFirstByBatchIdOrderByCreated(batchId);
        return message;
        //return ResponseEntity.ok(e.getName() + " has started");
    }

    /*
        http://localhost:8080/eventmanagement-api/messaging/core/messages/all?batchId=1
     */
    @RequestMapping(value="/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<CmMessage> getAllMessagesForBatch(@RequestParam("batchId") Long batch_id){
        CmPushBatch batch = new CmPushBatch();
        batch.setBatchId(batch_id);
        List<CmMessage> messages = repository.findAllByBatchIdOrderByCreated(batch);
        return messages;
        //return ResponseEntity.ok(e.getName() + " has started");
    }

    /*
        http://localhost:8080/eventmanagement-api/messaging/core/messages/paged?batchId=1
    * */
    @RequestMapping(value="/paged", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page<CmMessage> getPagedMessagesForBatch(@RequestParam("batchId") Long batchId,Pageable pageable){
        CmPushBatch batch = new CmPushBatch();
        batch.setBatchId(batchId);
        Page<CmMessage> messages = repository.findAllByBatchId(batch, pageable);
        return messages;
        //return ResponseEntity.ok(e.getName() + " has started");
    }

    /*
        http://localhost:8080/eventmanagement-api/messaging/core/messages/pagedNative/1?page=0&size=3&sort=created,desc
     */
    @RequestMapping(value="/pagedNative/{batchId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page<CmMessage> getPagedMessagesNativeForBatch(@PathVariable Long batchId,Pageable pageable){
        Page<CmMessage> messages = repository.findAllByBatchIdNative(batchId, pageable);
        return messages;
        //return ResponseEntity.ok(e.getName() + " has started");
    }


}
