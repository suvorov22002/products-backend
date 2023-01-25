package com.afriland.packageservices.model;

import com.afriland.packageservices.enums.SubscriptionType;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Data
public class SubscriptionDTO {

    private String name;

    private Date subscriptionDate;

    private List<ProductDTO> productDTOList;
}
