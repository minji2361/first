package com.kg.myapp.vo;

public class JobVO {

	private String jobId;
	private String jobTitle;
	
	@Override
	public String toString() {
		return "JobVO [jobId=" + jobId + ", jobTitle=" + jobTitle + "]";
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
}
