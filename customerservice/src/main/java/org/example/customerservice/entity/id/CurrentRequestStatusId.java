package org.example.customerservice.entity.id;

import org.example.customerservice.entity.Request;
import org.example.customerservice.entity.RequestStatus;

import java.io.Serializable;

public class CurrentRequestStatusId implements Serializable {

    private Request request;
    private RequestStatus requestStatus;
}
