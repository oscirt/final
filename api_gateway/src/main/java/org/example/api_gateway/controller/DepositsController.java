package org.example.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.example.api_gateway.entity.request.DepositOptionsRequest;
import org.example.api_gateway.entity.response.DepositOptionsResponse;
import org.example.api_gateway.service.DepositsService;
import org.example.starter.exception.IntentionException;
import org.example.starter.exception.UnauthorizedException;
import org.example.starter.jwt.BasicJwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
@RequestMapping("/deposits")
@RequiredArgsConstructor
public class DepositsController {

    private final BasicJwtUtils basicJwtUtils;
    private final DepositsService depositsService;

    @PostMapping("/calculate")
    public ResponseEntity<DepositOptionsResponse> calculate(
            @RequestHeader("Authorization") String jwtToken,
            @RequestBody DepositOptionsRequest request) {
        validateJwtToken(jwtToken);
        return ResponseEntity.ok(depositsService.calculate(request));
    }

    private void validateJwtToken(String jwtToken) {
        if (!basicJwtUtils.validateJwtToken(jwtToken)) {
            throw new UnauthorizedException("Валидация jwt токена прошла неудачно!");
        }
    }
}
