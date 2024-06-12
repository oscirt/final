package org.example.api_gateway.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Выгода от депозита после вклада
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositOptionsResponse {

    /**
     * Сумма на счете вклада после всех выплат
     */
    private BigDecimal depositAmount;

    /**
     * Процентная ставка
     */
    private BigDecimal depositRate;
}
