package com.example.moo.repository.member;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.moo.common.MemberStatusType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String encodedPassword;
    
    @Column(length = 200)
    private String state;

    private LocalDateTime createDate;
}