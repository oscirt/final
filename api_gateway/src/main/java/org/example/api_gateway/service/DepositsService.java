package org.example.api_gateway.service;

import org.example.api_gateway.entity.request.DepositOptionsRequest;
import org.example.api_gateway.entity.response.DepositOptionsResponse;

public interface DepositsService {

    DepositOptionsResponse calculate(DepositOptionsRequest request);
}
