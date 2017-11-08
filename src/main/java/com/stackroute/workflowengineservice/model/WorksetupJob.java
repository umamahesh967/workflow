package com.stackroute.workflowengineservice.model;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*
 * Workflow engine will recv  the data in this object.
 * */
@JsonDeserialize
public class WorksetupJob {

	private String repo_url;
	private String projectID;
	private String timeStamp;
	private List<String> list_cmd;

	public String getUrl() {
		return repo_url;
	}
	public List<String> getList_cmd() {
		return list_cmd;
	}
	public void setList_cmd(List<String> list_cmd) {
		this.list_cmd = list_cmd;
	}
	public void setUrl(String url) {
		this.repo_url = url;
	}
	public String getPid() {
		return projectID;
	}
	public void setPid(String pid) {
		this.projectID = pid;
	}
	public String getTimespan() {
		return timeStamp;
	}
	/**
	 * @param url
	 * @param pid
	 * @param timespan
	 */
	public WorksetupJob(String url, String pid, String timespan) {
		super();
		this.repo_url = url;
		this.projectID = pid;
		this.timeStamp = timespan;
	}
	/**
	 * 
	 */
	public WorksetupJob() {
		super();
	}
	public void setTimespan(String timespan) {
		this.timeStamp = timespan;
	}

}
