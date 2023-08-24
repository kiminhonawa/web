package com.itwill.spring3.dto.email;

import lombok.Data;

@Data
public class EmailConfirmDto {
	private String verificationCode;
	private String confirmMessage;
}
