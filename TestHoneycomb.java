import java.util.*;

public class TestHoneycomb {
	public static void main(String[] args) {
		System.out.println(Honeycomb.degeneracy(1));
		for (int i = 0; i <= 150; i++) {
			System.out.println(i+"  "+Honeycomb.degeneracy(i));
		}
	}
}
