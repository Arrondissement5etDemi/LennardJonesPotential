import java.util.*;

public class TestTriangLattice {
	public static void main(String[] args) {
		for (int i = 0; i <= 150; i++) {
			System.out.println(i+"  "+TriangLattice.degeneracy(i));
		}
	}
}
