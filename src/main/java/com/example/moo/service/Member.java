package com.example.moo.service;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String name;
	private String encodedPassword;
	private LocalDateTime createDate;
}
