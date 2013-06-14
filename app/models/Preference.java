package models;

	public enum Preference {
		Aucune("Aucune"), 
		Dietetique("Diététique"),
		Bio("Bio"), 
		Francais("Francais"),
		Sport("Sport"),
		Exotique("Exotique");

		private String name = "";

		//Constructeur
		Preference(String name){
			this.name = name;
		}

		public String toString(){
			return name;
		}

}
