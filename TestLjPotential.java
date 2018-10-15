import java.util.*;

public class TestLjPotential {
	public static void main(String[] args) {
		System.out.println(LjPotential.ljClassic(2.0,4.0,1.0));
		System.out.println(LjPotential.lj(0.5));
		for (double app = 0.7; app <= 3.0; app += 0.05) {
			double u = Honeycomb.ljPot(app,1);
			System.out.println(app + "  " + u);
		}
	}
}
