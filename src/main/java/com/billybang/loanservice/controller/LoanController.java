package com.billybang.loanservice.controller;

import com.billybang.loanservice.api.ApiResult;
import com.billybang.loanservice.api.ApiUtils;
import com.billybang.loanservice.api.LoanApi;
import com.billybang.loanservice.model.dto.response.*;
import com.billybang.loanservice.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoanController implements LoanApi {

    private final LoanService loanService;

    @Override
    public ResponseEntity<ApiResult<LoanResDto>> getLoans(Long propertyId) {
        UserResponseDto userInfo = loanService.getUserInfo();
        PropertyResponseDto propertyInfo = loanService.getPropertyInfo(propertyId);
        LoanResDto loans = loanService.getLoans(propertyInfo, userInfo);
        return ResponseEntity.ok(ApiUtils.success(loans));
    }

    @Override
    public ResponseEntity<ApiResult<LoanSimpleResDto>> getLoanSimple(Long propertyId) {
        UserResponseDto userInfo = loanService.getUserInfo();
        PropertyResponseDto propertyInfo = loanService.getPropertyInfo(propertyId);
        LoanSimpleResDto loanSimpleResDto = loanService.getLoanSimple(propertyInfo, userInfo);
        return ResponseEntity.ok(ApiUtils.success(loanSimpleResDto));
    }

    @Override
    public ResponseEntity<ApiResult<LoanDetailResDto>> getLoanDetail(Long loanId) {
        UserResponseDto userInfo = loanService.getUserInfo();
        log.info("userInfo : {}", userInfo);
        LoanDetailResDto loanDetailResDto = loanService.getLoanDetail(loanId, userInfo);
        return ResponseEntity.ok(ApiUtils.success(loanDetailResDto));
    }

}
