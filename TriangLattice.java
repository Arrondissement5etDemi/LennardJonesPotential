import java.util.*;

//This is for treatment of triangular lattices.

public class TriangLattice {

	/**computes the eligible pairs of integer (n,m) such that n^2+nm+m^2=k, where k is an integer > 0.
 	@param k positive integer, depicting the square of distance from origin to the point (n,m)
	@return the number of eligible pairs of integers (n,m) whose distance to origin is sqrt(l)*/
	public static int degeneracy(int k) {
		if (k<0) {
			throw new ArithmeticException("The input value k of the method degeneracy has to be >= 0.");
		}
		else {
			int count = 0;
			double bound = Math.sqrt( 4* (double)k / 3);
			for (int n = (int) Math.ceil(- bound); n <= Math.ceil(bound); n++) {
				double determ = Math.sqrt((double)(4*k-3*n*n));
				int m = (int) Math.round((-(double)n + determ)/2);
				//can the pair (n,m) be a good candidate?
				
				if (k == n*n + n*m + m*m) {
					if (4*k-3*n*n==0) {
						count = count + 1;
					}
					else {
						count = count + 2;	
					}
				}
			}
			return count;
		}
	}


	/**computes the LJ potential energy on a point in the triangle lattice scaled by a factor
 	@param scale double, the scaling factor, the nearest neighbor distance in the square lattice
	@return double, the LJ potential of the (scaled) square lattice, assuming e=r0=0 in the LJ eqn. */
	public static double ljPot(double scale) {
		double result = 0;
		for (int k = 1; k <= 1000; k++) {
			double potK = LjPotential.lj(k*scale);
			double degK = (double) degeneracy(k);
			double termK = potK * degK;
			result += termK;
		}
		return result;
	}

	/**computes the LJ potential energy on a point in the triangle lattice with a given value of area per particle
        @param app double, area per particle
	@param dummy int, a dummy indicator that we are in the area-per-particle mode. Can be any int.
        @return double, the LJ potential of the specified square lattice, assuming e=r0=0 in the LJ eqn. */
        public static double ljPot(double app, int dummy) {
                double scale = Math.sqrt(2/Math.sqrt(3)*app);
		return ljPot(scale);
        }

}
