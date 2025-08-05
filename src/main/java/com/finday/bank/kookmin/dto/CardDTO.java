package com.finday.bank.kookmin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 카드 정보를 담는 DTO")
public class CardDTO {

    @Schema(description = "카드명", example = "틴업 체크카드")
    private String cardName;

    @Schema(description = "카드 이미지 url")
    private String cardImgUrl;

    @Schema(description = "카드 번호", example = "1111-1111-1111-1111")
    private String cardNumber;

    @Schema(description = "카드 생성일시 (YYYY-MM-DDTHH:MM:SS)", example = "2024-01-01T12:00:00")
    private String createdAt;

    @Schema(description = "은행 이름", example = "국민은행")
    private String bankName;
}
