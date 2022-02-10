package polinomi_cap16;

import java.util.Iterator;

public interface Polinomio extends Iterable<Monomio>{
	
	default int size() {
		int c=0;
		for( Iterator<Monomio> it=iterator(); it.hasNext(); it.next(),c++ );
		return c;
	}//size
	
	void add( Monomio m );
	
	Polinomio crea(); //metodo factory
	
		
	default Polinomio add( Polinomio p ) {
		Polinomio somma=crea();
		for( Monomio m: this ) somma.add(m);
		for( Monomio m: p ) somma.add(m);
		
		return somma;
		
	}//add
	
	default Polinomio mul( Monomio m ) {
		Polinomio prodotto=crea();
		for( Monomio m1: this )
			prodotto.add( m.mul(m1) );
		return prodotto;
	}//mul
	
	default Polinomio mul( Polinomio p ) {
		Polinomio prodotto=crea();
		for( Monomio m: this )
			prodotto=prodotto.add( p.mul(m) );
		return prodotto;
	}//mul
	
	default Polinomio derivata() {
		Polinomio d=crea();
		for( Monomio m: this ) {
			System.out.println(m);
			if(m.getGrado()==0)d.add(new Monomio(0,1));
			else if( m.getGrado()>0 )
				d.add( new Monomio( m.getCoeff()*m.getGrado(), m.getGrado()-1 ) );
		}return d;
	}//derivata
	
	default double valore( double x ) {
		
		double ret=0.0D;
		for( Monomio m: this ) {
			double v=0.0D;
			v=Math.pow(x,m.getGrado());
			v=v*m.getCoeff();
			ret+=v;
			
		}
			
		return ret;
	}//valore
}//Polinomio





