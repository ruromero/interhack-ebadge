package eu.europa.ec.interhack.ebadge.dto;

public class VisitorResponse {

	private String result;
	
	private String comment;
	
	public VisitorResponse(String result) {
		this.result = result;
	}
	
	public VisitorResponse setResult(String result) {
		this.result = result;
		return this;
	}
	
	public String getResult() {
		return result;
	}
	
	public String getComment() {
		return comment;
	}
	
	public VisitorResponse setComment(String comment) {
		this.comment = comment;
		return this;
	}
}
