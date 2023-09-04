package com.example.APITesting;

import com.example.APITesting.repository.JpaMemberRepository;
import com.example.APITesting.repository.MemberRepository;
import com.example.APITesting.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.example.APITesting.repository.SpringDataJpaMemberRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class Config {
    private final MemberRepository memberRepository;

    @Autowired
    public Config(@Qualifier("springDataJpaMemberRepository")MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}
