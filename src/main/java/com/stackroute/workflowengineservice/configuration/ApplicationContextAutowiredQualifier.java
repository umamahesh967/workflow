package com.stackroute.workflowengineservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stackroute.workflowengineservice.service.GitVersionControlService;
import com.stackroute.workflowengineservice.service.SvnVersionControlService;
import com.stackroute.workflowengineservice.service.VersionControlService;

@Configuration
public class ApplicationContextAutowiredQualifier {

	
	@Bean
	public VersionControlService gitVersionControlSystem() {
		VersionControlService gitVersionControlService = 
				new GitVersionControlService();
		return gitVersionControlService;
	}
	
	@Bean
	public VersionControlService svnVersionControlSystem() {
		VersionControlService svnVersionControlService = 
				new SvnVersionControlService();
		return svnVersionControlService;
	}
}
