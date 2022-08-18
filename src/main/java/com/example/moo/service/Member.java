package com.example.moo.service;

import java.time.LocalDateTime;

import com.example.moo.common.MemberStatusType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Integer id;
	private String name;
	private String encodedPassword;
	private MemberStatusType state;
	private LocalDateTime createDate;
}
