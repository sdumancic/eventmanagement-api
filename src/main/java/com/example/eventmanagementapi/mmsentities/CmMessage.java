package com.example.eventmanagementapi.mmsentities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "CM_MESSAGES")
public class CmMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated message ID")
    private Long messageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="BATCH_ID")
    @JsonIgnore
    private CmPushBatch batchId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="CUST_ID")
    private Customer customer;
    private int campaignId;
    private int channelId;
    @ApiModelProperty(notes = "Datetime of message creation")
    private ZonedDateTime created;
    @ApiModelProperty(notes = "1 = message prepared, 3 = message sent to broker, 4 = message error, 9 = message read")
    private int status;


    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public CmPushBatch getBatchId() {
        return batchId;
    }

    public void setBatchId(CmPushBatch batchId) {
        this.batchId = batchId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
