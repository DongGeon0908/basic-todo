package kr.ac.hs.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 시간데이터 (생성, 수정시간)을 자동으로 등록하기 위해서 필요함
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}