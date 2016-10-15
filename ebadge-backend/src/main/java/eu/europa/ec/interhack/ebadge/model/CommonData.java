package eu.europa.ec.interhack.ebadge.model;

public class CommonData {

	public enum Institution {
		EC("European Commission"), EP("European Parliament"), COUNCIL("Council of the European Union");

		private String name;

		private Institution(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}
}
