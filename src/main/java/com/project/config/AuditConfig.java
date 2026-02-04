package com.project.config;

import java.util.Optional;

//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditConfig implements AuditorAware<Integer>{

	// agar AuditorAware ko implement nahi karna hoto direct aise bhi code kar sakte hain.
//    @Bean
//    public AuditorAware<Integer> getCurrentAuditorAware() {
//        return () -> Optional.of(5); // abhi hardcoded hai ye. 
//    }

	@Override
	public Optional<Integer> getCurrentAuditor() {
		
		return Optional.of(4);
	}
}
