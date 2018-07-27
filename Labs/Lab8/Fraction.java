

public class Fraction {
	private int mNum;
	private int mDen;
	
	public Fraction() {
		mNum = 0;
		mDen = 0;
	}
	
	public Fraction(double d) { // hehe
		
	}
	
	public Fraction(int num, int den) {
		mNum = num;
		mDen = den;
		simplify();
	}
	
	public void setNumerator(int num) {
		mNum = num;
		simplify();
	}
	
	public void setDenominator(int den) {
		mDen = den;
		simplify();
	}
	
	public int getNumerator() {
		return mNum;
	}
	
	public int getDenominator() {
		return mDen;
	}
	
	public double getIrrational() {
		return (double)mNum / mDen;
	}
	
	public Fraction add(Fraction o) {
		int n = (mNum * o.mDen) + (mDen * o.mNum);
		return new Fraction(n, mDen * o.mDen);
	}
	
	public Fraction subtract(Fraction o) {
		int n = (mNum * o.mDen) - (mDen * o.mNum);
		return new Fraction(n, mDen * o.mDen);
	}
	
	public Fraction multiply(Fraction o) {
		return new Fraction(mNum * o.mNum, mDen * o.mDen);
	}
	
	public Fraction divide(Fraction o) {
		return new Fraction(mNum * o.mDen, mDen * o.mNum);
	}
	
	public int compareTo(Fraction o) {
		return (mNum * o.mDen) - (o.mNum * mDen);
	}
	
	public boolean equals(Fraction o) {
		return compareTo(o) == 0;
	}
	
	public String toString() {
		return mNum + "/" + mDen;
	}
	
	private void simplify() {
		int m = (mNum < mDen)? mNum : mDen;
		while (m > 1) {
			if (mNum % m == 0 && mDen % m == 0) {
				mNum /= m;
				mDen /= m;
			} else {
				--m;
			}
			
		}
	}
}