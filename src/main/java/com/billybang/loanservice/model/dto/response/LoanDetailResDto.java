package com.billybang.loanservice.model.dto.response;

import com.billybang.loanservice.model.entity.loan.LoanPropertyCondition;
import com.billybang.loanservice.model.entity.loan.LoanUserCondition;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LoanDetailResDto {

    private Integer providerId;

    private String providerName;

    private String providerImgUrl;

    private String productName;

    private String productDesc;

    private String guaranteeAgencyName;

    private String loanType;

    private Integer loanLimit;

    private Integer userLoanLimit;

    private Integer ltv;

    private Integer minTerm;

    private Integer maxTerm;

    private Float minInterestRate;

    private Float maxInterestRate;

    private String interestRateType;

    private List<String> preferentialItems;

    private String originUrl;

    private Boolean isStarred;

}
