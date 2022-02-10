package polinomi_cap16;

public class Monomio implements Comparable<Monomio>{
	private final int COEFF, GRADO;
	public Monomio( final int C, final int G ) {
		if( G<0 ) throw new IllegalArgumentException();
		this.COEFF=C; this.GRADO=G;
	}
	public Monomio() {
		this.COEFF=0;
		this.GRADO=1;
	}
	public Monomio( Monomio m ) {
		this.COEFF=m.COEFF; this.GRADO=m.GRADO;
	}
	public int getCoeff() { return COEFF; }
	
	public int getGrado() { return GRADO; }
	
	public boolean equals( Object x ) {
		if( !(x instanceof Monomio) ) return false;
		if( x==this ) return true;
		Monomio m=(Monomio)x;
		return this.GRADO==m.GRADO;
	}//equals
	public int hashCode() { return GRADO; }
	
	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		if( COEFF==0 ) sb.append(0);
		else {
			if( COEFF<0 ) sb.append('-');
			if( Math.abs(COEFF)!=1 || GRADO==0 ) sb.append(Math.abs(COEFF));
			if( GRADO>0 ) sb.append('x');
			if( GRADO>1 ) { sb.append('^'); sb.append(GRADO); }
		}
		return sb.toString();
	
	}//toString
	
	public Monomio add( Monomio m ) {
		if( !this.equals(m) ) throw new RuntimeException("Monomi non simili!");
		return new Monomio( COEFF+m.COEFF, GRADO );
	}//add
	
	public Monomio mul( int s ) {
		return new Monomio( COEFF*s, GRADO );
	}//mul
	
	public Monomio mul( Monomio m ) {
		return new Monomio( COEFF*m.COEFF, GRADO+m.GRADO );
	}//mul
	
	public int compareTo( Monomio m ) {
	if( GRADO>m.GRADO ) return -1;
		if( GRADO<m.GRADO ) return 1;
		return 0;
	}//compareTo
	
	public static void main( String[] args ) {
		Monomio m1=new Monomio(1,2);
		m1=m1.mul(02);
		System.out.println("m1="+m1);
		Monomio m2=new Monomio(1,0);
		System.out.println("m2="+m2);
		Monomio m3=new Monomio(1,1);
		System.out.println("m3="+m3);
		m3=m3.mul(-2);
		System.out.println("m3="+m3);
		Monomio m4=new Monomio(-3,5);
		System.out.println("m4="+m4);
		m4=m4.add( new Monomio(4,5));
		System.out.println("m4="+m4);
		m4=m4.mul( new Monomio(-2,4) );
		System.out.println("m4="+m4);
	}//main
	
}//Monomio
