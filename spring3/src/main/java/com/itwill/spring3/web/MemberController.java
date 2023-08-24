package com.itwill.spring3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring3.dto.member.MemberSignUpDto;
import com.itwill.spring3.service.MemberService1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService1 memberService;
	
	@GetMapping("/signup")
	public void signUp() {
		log.info("signUp() GET");
	}
	
	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto) {
		log.info("signUp(dto={}) POST", dto);
		
		Long id = memberService.registerMember(dto);
		log.info("회원가입 id = {}", id);
		
		return "redirect:/login";
	}
}
