package eu.europa.ec.interhack.ebadge.model;

public class CommonData {

	public enum Institution {
		EC("European Commission", "ec"), EP("European Parliament", "ep"), COUNCIL("Council of the European Union", "council");

		private String name;
		private String logo;

		private Institution(String name, String logo) {
			this.name = name;
			this.logo = logo;
		}

		public String getName() {
			return name;
		}

		public String getLogo() {
			return logo;
		}

	}
}
