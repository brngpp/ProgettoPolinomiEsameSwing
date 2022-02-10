package polinomi_cap16;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class POL_Util {

	public static Polinomio converti(String s,Polinomio PLL) {

		
		Monomio m=new Monomio();
		
		StringTokenizer st=new StringTokenizer(s,"+,-,*,/",true);
		ArrayList<String> del=new ArrayList<String>();
		ArrayList<Monomio>lm=new ArrayList<Monomio>();
		del.add("+");del.add("*");del.add("/");
		String regex="[0-9]*";
		boolean neg=false;
		while(st.hasMoreTokens()) {
			
			String a=st.nextToken();
			if(!(del.contains(a))) {
				if(a.equals("-")) {neg=true;System.out.println(a+"entrato in -");}
				if(a.equals("x")) {
					if (neg) {
						m=new Monomio(-1,1);
						neg=false;
					}
					else {
						m=new Monomio(1,1);
						
					}
					lm.add(m);System.out.println(m+" è stato aggiunto");}
				if(a.matches(regex)) { System.out.println(a+"entrato in regex");
					int tmp=Integer.parseInt(a);
					if(neg) {
						m=new Monomio(-tmp,0);neg=false;
						}
					else {
						m=new Monomio(tmp,0);
					}
					lm.add(m);System.out.println(m+" è stato aggiunto");}
				
				if(a.length()>1 && a.indexOf("x")!=-1) {
					int C=1;int Pot=1;int M=1;
					String Coeff="";String pot="1";boolean flag=false;String mul="1";
					for(int i=0;i<a.length();i++) {
						if(a.charAt(0)=='x') {
							Coeff="1";
						}
						if(a.charAt(i)=='x') { 
							mul=Coeff;
							Coeff="0";
							if(i<a.length()-1)i++;
							else {break;}
						}
						if(a.charAt(i)=='^') {
							pot="";
							flag=true;
							if(i<a.length()-1)i++;
						}
						if(flag) {
							pot+=a.charAt(i);
						}
						else {
							Coeff+=a.charAt(i);
						}
					}
					//System.out.println("pot:"+pot+ " ,coeff:"+Coeff+" mul:"+mul);
					if(pot!=null) {Pot=Integer.parseInt(pot);}
					if(Coeff!=null) {C=Integer.parseInt(Coeff);}
					if(mul!=null) {M=Integer.parseInt(mul);}
					if(neg)M=-M;neg=false; 
					if(C!=0) {m=new Monomio(C,Pot);m=m.mul(M);lm.add(m);System.out.println(m+" è stato aggiunto");}
					else {m=new Monomio(1,Pot);m=m.mul(M);lm.add(m);System.out.println(m+" è stato aggiunto");}
				}
			}
			else {continue;}
		}
		lm.sort(null);
		
		for(Monomio mn:lm) {
		 PLL.add(mn);
		 
}
		
		return PLL;
	}
	
}

