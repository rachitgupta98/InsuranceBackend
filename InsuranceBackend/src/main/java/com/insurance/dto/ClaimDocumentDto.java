package com.insurance.dto;

import org.springframework.web.multipart.MultipartFile;

public class ClaimDocumentDto {
	private long claimId;
	private MultipartFile docFile;
	public long getClaimId() {
		return claimId;
	}
	public void setClaimId(long claimId) {
		this.claimId = claimId;
	}
	public MultipartFile getDocFile() {
		return docFile;
	}
	public void setDocFile(MultipartFile docFile) {
		this.docFile = docFile;
	}
	
	
}
