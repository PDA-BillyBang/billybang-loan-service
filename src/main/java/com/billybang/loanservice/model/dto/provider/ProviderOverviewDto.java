package com.billybang.loanservice.model.dto.provider;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProviderOverviewDto {

    private String providerName;

    private Integer financialTier;

    private String imgUrl;

    private String representativeName;

    private String establishedAt;

    private String providerSize;

    private String providerType;

    private String salesAmount;

    private String businessProfit;

    private String netProfit;

    private String creditLevel;

    private String employeeCount;

    private String industryDetail;

}
