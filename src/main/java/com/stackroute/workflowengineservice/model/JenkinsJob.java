package com.stackroute.workflowengineservice.model;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class JenkinsJob implements Serializable {

	private String projectId;
    private String clonedPath;
    private String repoUrl; // git or svn url
     private String timeStamp; // time stamp when to build the project
	/**
	 * @param projectId
	 * @param clonedPath
	 * @param repoUrl
	 * @param timeStamp
	 */
	public JenkinsJob(String projectId, String clonedPath, String repoUrl, String timeStamp) {
		super();
		this.projectId = projectId;
		this.clonedPath = clonedPath;
		this.repoUrl = repoUrl;
		this.timeStamp = timeStamp;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getClonedPath() {
		return clonedPath;
	}
	public void setClonedPath(String clonedPath) {
		this.clonedPath = clonedPath;
	}
	public String getRepoUrl() {
		return repoUrl;
	}
	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
     
}
