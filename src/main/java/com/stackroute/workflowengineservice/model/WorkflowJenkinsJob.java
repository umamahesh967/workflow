package com.stackroute.workflowengineservice.model;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize 
public class WorkflowJenkinsJob {

	private List<String> cmds;

	/**
	 * @param cmds
	 */
	public WorkflowJenkinsJob(List<String> list_cmd) {
		super();
		this.cmds = list_cmd;
	}

	/**
	 * 
	 */
	public WorkflowJenkinsJob() {
		super();
	}

	public List<String> getCmds() {
		return cmds;
	}

	public void setCmds(List<String> cmds) {
		this.cmds = cmds;
	}
}
