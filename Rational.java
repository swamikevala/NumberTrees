import java.math.BigInteger;

public class Rational implements Comparable<Rational>{

	private long numerator = 0;
	private long denominator = 1;
	
	public Rational(long numerator, long denominator) {
		
		if (denominator == 0) {
			throw new ArithmeticException("Rational cannot have a zero denominator");
		}
		
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Rational(Rational r) {
		
		this.numerator = r.getNumerator();
		this.denominator = r.getDenominator();
	}

	public long getNumerator() {
		return numerator;
	}

	public void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	public void setDenominator(long denominator) {
		this.denominator = denominator;
	}
	
	public static Rational multiply(Rational r1, Rational r2) {
		long a = r1.getNumerator();
		long b = r1.getDenominator();
		long c = r2.getNumerator();
		long d = r2.getDenominator();
		return new Rational(a*c, b*d).getReduced();
	}
	
	public static Rational add(Rational r1, Rational r2) {
		long a = r1.getNumerator();
		long b = r1.getDenominator();
		long c = r2.getNumerator();
		long d = r2.getDenominator();
		return new Rational(a*d  +  b*c, b*d).getReduced();
	}
	
	public Rational getReduced() {
		BigInteger n = BigInteger.valueOf(numerator);
		BigInteger d = BigInteger.valueOf(denominator);
		BigInteger gcd = n.gcd(d);
		return new Rational(n.divide(gcd).longValue(), d.divide(gcd).longValue());
	}
	
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	@Override
	public int compareTo(Rational other) {
		long ad = numerator * other.getDenominator();
		long bc = denominator * other.getNumerator();
		if (ad < bc) {
			return -1;
		} else if (ad > bc) {
			return 1;
		} else {
			return 0;
		}
	}
}
