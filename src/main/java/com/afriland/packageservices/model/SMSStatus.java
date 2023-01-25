package com.afriland.packageservices.model;

import lombok.Data;

@Data
public class SMSStatus {

    public String status;
    public String smsclientid;
    public String mobileno;
    public String messageid;
    public String errordescription;
    public String errorcode;
    public String total_sms_unit;
    public String balance;

}
