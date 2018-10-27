import java.util.*;

//This is for treatment of honeycomb lattices.

public class Honeycomb {


	/**computes the total number of lattice points in the standard honeycomb lattice that are of distance sqrt(k) to the origin.
 	@param k positive integer, depicting the square of distance from origin to the lattice point
	@return nonnegative integer, the number of lattice points whose distance to origin is sqrt(k)*/
	public static int degeneracy(int k) {
		if (k<0) {
			throw new ArithmeticException("The input value k of the method degeneracy has to be >= 0.");
		}
		else {
			return degen1(k) + degen2(k);
		}
	}

	 /**computes the degenracy of the second type of lattice points in the honeycomb unit cell; See notebook
         @param k positive integer, depicting the square of distance from the origin to the lattice point    
	 @return nonnegative integer, the number of second-type lattice points at distance sqrt(k) to the origin */

	private static int degen2(int k) {
		int count = 0;
		double boundMin = 1/3 - (2*Math.sqrt((double) k) / 3);
		double boundMax = 1/3 + (2*Math.sqrt((double) k) / 3);
		for (int n = (int) Math.ceil(boundMin); n <= Math.ceil(boundMax); n++) {
			double determ = Math.sqrt((double) (3*(4*k - (1 - 3*n)*(1 - 3*n))));
			int m = (int) Math.round(((3 - 3*n) + determ)/6);
			if (k==3*m*m + (3*n-3)*m + 3*n*n - 3*n + 1) {
				if (4*k - (1-3*n)*(1-3*n)==0) {
					count++;
				}
				else {
					count = count + 2;
				}
			}
		}
		return count;
	}
	

	/**computes the degenracy for the first type of lattice points in the honeycomb unit cell; See notebook
 	@param k positive integer, depicting the square of distance from the origin to the lattice point
	@return nonnegative integer, the number of first-type lattice points at distance sqrt(k) to the origin */
	private static int degen1(int k) {
		//this is just a triangular lattice scaled by sqrt(3) 
		if (k%3==0) {
			return TriangLattice.degeneracy(k/3);
		}
		else {
			return 0;
		}
	}


	/**computes the LJ potential energy on a point in the triangle lattice scaled by a factor
 	@param scale double, the scaling factor, the nearest neighbor distance in the square lattice
	@return double, the LJ potential of the (scaled) square lattice, assuming e=r0=0 in the LJ eqn. */
	public static double ljPot(double scale) {
		double result = 0;
		for (int k = 1; k <= 1000; k++) {
			double potK = LjPotential.lj(Math.sqrt(k)*scale);
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
                double scale = Math.sqrt(4*app/(3*Math.sqrt(3)));
		return ljPot(scale);
        }

}
