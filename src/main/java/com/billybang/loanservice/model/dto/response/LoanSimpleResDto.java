package com.billybang.loanservice.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoanSimpleResDto {

    private Long loanId;

    private String providerName;

    private String providerImgUrl;

    private String productName;

    private Integer loanLimit;

    private Integer ltv;

    private Float minInterestRate;

    private Float maxInterestRate;

}
