package com.example.eventmanagementapi.entities.projections;

import com.example.eventmanagementapi.entities.Venue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types={Venue.class}, name="virtual")
public interface StreetAddressProjection {

    @Value("#{target.streetAddress} #{target.streetAddress2}")
    String getCompleteStreetAddress();

}
