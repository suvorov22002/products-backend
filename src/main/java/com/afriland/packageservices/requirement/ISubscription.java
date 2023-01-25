package com.afriland.packageservices.requirement;

import com.afriland.packageservices.model.SubscriptionDTO;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.UUID;

public interface ISubscription {

    public ResponseEntity<Object> subscribe(String clientCode, UUID
            packId);

    public ResponseEntity<SubscriptionDTO> validate();

    public ResponseEntity<SubscriptionDTO> suspend();

    public ResponseEntity<SubscriptionDTO> terminate();

    public ResponseEntity<SubscriptionDTO> activate();
}
