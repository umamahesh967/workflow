package com.stackroute.workflowengineservice.repository;

import java.util.List;

import com.stackroute.workflowengineservice.model.JenkinsJob;


public interface DatabaseService {
	List<JenkinsJob> read();
}
