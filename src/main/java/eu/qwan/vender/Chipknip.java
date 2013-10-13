package eu.qwan.vender;

public class Chipknip {
	public int credits;

	public Chipknip(int initial_value) {
		credits = initial_value;
	}

	public boolean HasValue(int p) {
		return credits >= p;
	}

	public void Reduce(int p) {
		credits -= p;
	}
}
