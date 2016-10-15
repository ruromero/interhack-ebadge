package eu.europa.ec.interhack.ebadge.dto;

public class VisitorResponse {

	private String result;
	
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
}
