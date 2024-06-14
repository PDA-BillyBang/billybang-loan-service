package com.billybang.loanservice.api;

import com.billybang.loanservice.model.dto.request.SaveStarredLoanReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Loan Star API", description = "대출 상품 즐겨찾기 API")
@RequestMapping("/loans/stars")
public interface StarApi {

    @Operation(summary = "대출 상품 즐겨찾기 추가", description = "대출 상품을 즐겨찾기에 추가합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("")
    ResponseEntity saveStarredLoan(@RequestBody SaveStarredLoanReqDto saveStarredLoanReqDto);

    @Operation(summary = "대출 상품 즐겨찾기 조회", description = "즐겨찾기한 대출 상품들을 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("")
    ResponseEntity getStarredLoans();

    @Operation(summary = "대출 상품 즐겨찾기 간단히 조회", description = "즐겨찾기한 대출 상품들을 간단히 조회한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @GetMapping("/simple")
    ResponseEntity getStarredLoansSimple(@RequestParam("count") Integer count);

    @Operation(summary = "대출 상품 즐겨찾기 삭제", description = "즐겨찾기한 대출 상품을 삭제한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @DeleteMapping("/{starredLoanId}")
    ResponseEntity deleteStarredLoan(@PathVariable("starredLoanId") Long starredLoanId);

}