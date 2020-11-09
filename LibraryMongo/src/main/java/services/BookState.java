package services;


public enum BookState {

	A("Availabe"),
	R("Reserved"),
	L("Lost"),
	H ("Hired");
	

	private char bookState;
	private String descriptionState;

	BookState() {
	}

	BookState(char bookState) {
		this.bookState = bookState;

	}

	BookState(String descriptionState) {
		this.descriptionState = descriptionState;

	}

		
}