package com.example.moo.controller;

import java.time.LocalDateTime;

import com.example.moo.common.MemberStatusType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	public String name;
	private String encodedPassword;
	private MemberStatusType state;
	private LocalDateTime createDate;
}
