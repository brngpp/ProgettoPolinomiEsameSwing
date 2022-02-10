package polinomi_cap16;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;

import java.util.Arrays;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ListaConcatenata.ListaConcatenataOrdinata;




public class GUIPOL {
	public static void main(String[]args) {
		FinestraSceltaPOLL fsp=new FinestraSceltaPOLL();
		fsp.setVisible(true);
	}
}	
@SuppressWarnings("serial")

class FinestraSceltaPOLL extends JFrame implements ActionListener{
	private String titolo ="PolinomioGUI";
	private JRadioButton rb,rb1,rb2,rb3,rb4;
	public FinestraSceltaPOLL(){
		setTitle(titolo);
		Toolkit kit=Toolkit.getDefaultToolkit();
		 Dimension screenSize=kit.getScreenSize();
         int larghezzaScreen=screenSize.width;
         int altezzaScreen=screenSize.height;
         
         setSize(larghezzaScreen/2, altezzaScreen/2);
         setLocationByPlatform(true);
         
		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if( consensoUscita() ) System.exit(0);
			}
		} );

		//Pannello Nord
		JPanel N=new JPanel();
		add(N,BorderLayout.NORTH);

		//Pannello Centro
		JPanel C=new JPanel();
		JLabel etichetta=new JLabel("Scelta Tipo Implementazione del Polinomio");
		etichetta.setFont(new Font("Serif", Font.PLAIN, 24));
		C.add(etichetta);
		add(C,BorderLayout.CENTER);

		//Pannello SUD
		JPanel S=new JPanel();
		add(S,BorderLayout.SOUTH);

		rb=new JRadioButton("Pol_LL",false);
		rb.addActionListener(this);
		rb1=new JRadioButton("POL_Map",false);
		rb1.addActionListener(this);
		rb2=new JRadioButton("POL_AL",false);
		rb2.addActionListener(this);
		rb3=new JRadioButton("POL_Set",false);
		rb3.addActionListener(this);
		//rb4=new JRadioButton("POL_LCO",false);
		//rb4.addActionListener(this);
		S.add(rb);S.add(rb1);S.add(rb2);S.add(rb3);//S.add(rb4);
	}

	//METODI
	private boolean consensoUscita(){
		int option=JOptionPane.showConfirmDialog(
				null, "Continuare ?", "Ancora non hai fatto nulla",
				JOptionPane.YES_NO_OPTION);
		return option==JOptionPane.YES_OPTION;
	}//consensoUscita
	//

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==rb) {
			String impl="POL_LL";
			FinestraPOLGUI fg=new FinestraPOLGUI(impl);
			fg.Poly=new PolinomioLL();
			fg.setVisible(true);
			this.invalidate();
			this.setVisible(false);	
		}
		if(evt.getSource()==rb1) {
			String impl="POL_MAP";
			FinestraPOLGUI fg=new FinestraPOLGUI(impl);
			fg.Poly=new PolinomioMap();
			fg.setVisible(true);
			this.invalidate();
			this.setVisible(false);	
		}
		if(evt.getSource()==rb2) {
			FinestraPOLGUI fg=new FinestraPOLGUI("POL_AL");
			fg.Poly=new PolinomioAL();
			fg.setVisible(true);
			this.invalidate();
			this.setVisible(false);	
		}
		if(evt.getSource()==rb3) {
			FinestraPOLGUI fg=new FinestraPOLGUI("POL_Set");
			fg.Poly=new PolinomioSet();
			fg.setVisible(true);
			this.invalidate();
			this.setVisible(false);	

		}
		/*if(evt.getSource()==rb4) {
			FinestraPOLGUI fg=new FinestraPOLGUI("POL_LCO");
			fg.Poly=new ListaConcatenataOrdinata();
			fg.setVisible(true);
			this.invalidate();
			this.setVisible(false);
		}*/

	}





	class FinestraPOLGUI extends JFrame implements ActionListener   {
		private int i=0,r;
		private String impl,nomeFileConNome,pathName;
		private String nomeFile="Polinomilist.txt";
		private String tmp="tmp.txt";
		private String titolo="FinestraPOLGUI";
		private JButton reset,canc;
		private JTextField pol;
		private JMenuItem tipoAL,tipoSet,tipoMap,tipoLL,esci,derivata,salva,salvaconNome,apri,calcolaX,somma,differenza;
		private int l=100;
		private JCheckBox [] boxv=new JCheckBox[l];//usando una lista sarei dovuto ricorrere sempre al get(i) per legare i box al pannello;
		private JPanel N,C,O;
		private Polinomio Poly;
		
		
		public FinestraPOLGUI(String impl) {
			this.impl=impl;
			setTitle(titolo+impl);
			setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
			addWindowListener( new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					if( consensoUscita() ) System.exit(0);
				}
			} );
			for(int i=0;i<l;i++) {
				boxv[i]=new JCheckBox();
			}
			N=new JPanel();
			
			
			O=new JPanel();
			add(O,BorderLayout.WEST);
			add(N,BorderLayout.NORTH);

			C=new JPanel();
			//JLabel pc=new JLabel("Polinomi Caricati");
			N.add(canc=new JButton("canc"));
			add(N,BorderLayout.NORTH);
			
			canc.addActionListener(this);
			C.add(new JLabel("POL",JLabel.RIGHT));
			C.add(pol=new JTextField("",12));

			add(C,BorderLayout.CENTER);

			JPanel q=new JPanel();
			q.add(reset=new JButton("reset"));
			add(q,BorderLayout.SOUTH);

			reset.addActionListener(this);
			pol.addActionListener(this);


			Toolkit kit=Toolkit.getDefaultToolkit();
			 Dimension screenSize=kit.getScreenSize();
	         int larghezzaScreen=screenSize.width;
	         int altezzaScreen=screenSize.height;
	         setSize(larghezzaScreen/2, altezzaScreen/2);
	         setLocationByPlatform(true);

			JMenuBar menuBar=new JMenuBar();
			this.setJMenuBar( menuBar );
			JMenu fileMenu=new JMenu("File");menuBar.add(fileMenu);
			JMenu comandi=new JMenu("Comandi");menuBar.add(comandi);
			somma=new JMenuItem("somma");comandi.add(somma);
			somma.addActionListener(this);
			differenza=new JMenuItem("differenza");comandi.add(differenza);
			differenza.addActionListener(this);
			derivata=new JMenuItem("derivata");comandi.add(derivata);
			derivata.addActionListener(this);
			calcolaX=new JMenuItem("calcolax");comandi.add(calcolaX);
			calcolaX.addActionListener(this);
			JMenu tipoimpl=new JMenu("Nuova");fileMenu.add(tipoimpl);
			salva=new JMenuItem("Salva");fileMenu.add(salva);
			salva.addActionListener(this);
			salvaconNome=new JMenuItem("SalvaConNome");fileMenu.add(salvaconNome);
			salvaconNome.addActionListener(this);
			apri=new JMenuItem("Apri");fileMenu.add(apri);
			apri.addActionListener(this);
			tipoAL=new JMenuItem("Arraylist");tipoimpl.add(tipoAL);
			tipoAL.addActionListener(this);
			tipoSet=new JMenuItem("HashSet");tipoimpl.add(tipoSet);
			tipoSet.addActionListener(this);
			tipoMap=new JMenuItem("Maplist");tipoimpl.add(tipoMap);
			tipoMap.addActionListener(this);
			tipoLL=new JMenuItem("LinkedList");tipoimpl.add(tipoLL);
			tipoLL.addActionListener(this);
			esci=new JMenuItem("esci");fileMenu.add(esci);
			esci.addActionListener(this);


		}

		//METODI
		private boolean ControlloPol(String s) { //regex
			String SIGN="[\\-\\+]";
			String COEF="\\d+";
			String LETT="[xX](\\^([2-9]|1\\d)\\d*)?";

			String UMON="("+COEF+"|"+LETT+"|"+COEF+LETT+")"; 

			String POL="\\-?"+UMON+"("+SIGN+UMON+")*";
			if( s.length()==0 ) return false;
			if( s.matches(POL) ){
				return true;
			}


			return false;
		}
		 
private void CI() { //ControllerIndiceVettore
	if(l-i<2) {
		JCheckBox [] boxv2=Arrays.copyOf(boxv, l*2);
		System.out.println("CI");
		boxv=new JCheckBox[l*2];
		boxv=Arrays.copyOf(boxv2,l*2);
		for(int i=l;i<l*2;i++) {
			boxv[i]=new JCheckBox();
		}
		l=l*2;	
	}
}
		private boolean consensoUscita(){
			int option=JOptionPane.showConfirmDialog(
					null, "Continuare ?", "Uscendo si perderanno tutti i dati!",
					JOptionPane.YES_NO_OPTION);
			return option==JOptionPane.YES_OPTION;
		}//consensoUscita

		private JCheckBox Selected() {//Controlla il box selezionato;
			JCheckBox ret=new JCheckBox();
			for(JCheckBox b:boxv) {
				if(b.getAccessibleContext().getAccessibleName()==null)break;
					if(b.isSelected()) {
						System.out.println("Selected:"+b.getAccessibleContext().getAccessibleName() +"__"+b.isSelected());
						ret=b;
						b.setSelected(false);
						return ret;
					}
			}
			return null;
		}

		private JCheckBox[] BiSelected() {//controlla i box selezioni per somma e differenza
			JCheckBox ret[]=new JCheckBox[2];int k=0;
			for(JCheckBox b:boxv) {
				if(b.getAccessibleContext().getAccessibleName()==null)break;
					if(b.isSelected()) {
						ret[k]=b;k++;
						b.setSelected(false);
					}
			}
			return ret;
			
		}
		private boolean RiempiBox(String s){//riempie un box svuotato
			for(JCheckBox b:boxv) {
				if(b.isSelected() && b.getText().equals("vuota") || b.getText()==null) {
					b.setText(s);
					b.setSelected(false);
					return true;
				}
			}return false;
		}
		
		private void Canc() {//cancella un box
				for(JCheckBox b:boxv) {
				if(b.isSelected()) {
					b.setText("vuota");
					b.setSelected(false);
				}
			}
		}
		
		public void salva() throws IOException{
			try {
				BufferedReader bf=new BufferedReader(new FileReader(nomeFile));//prova ad aprire il file di default se esiste
				PrintWriter pw=new PrintWriter(tmp);
				String righe=bf.readLine();
				if(righe!=null) {//controllo della riga per la griglia il primo elemento del file è il numero di righe che contiene
					r=Integer.parseInt(righe);
					
				}
				else {
					r=0;
				}
				for(int j=0;j<boxv.length;j++) //contiamo i nuovi box
					if(boxv[j].isSelected()) r++;
				
				pw.println(r);
				
				
				String Str=bf.readLine();
				while(Str!=null) {//copia del vecchio sul tmp
					pw.println(Str);
					Str=bf.readLine();
				}
				
				for(int i=0;i<boxv.length;i++) {//Scrittura dei nuovi box
					if(boxv[i].isSelected()) {
						
					pw.println(boxv[i].getText());
					
					}
					boxv[i].setSelected(false);

				}
				bf.close();
				pw.close();
				
				File f=new File(tmp);
				File w=new File(nomeFile);
				w.delete();
				f.renameTo(w);
				
				
			}catch(IOException e){
				System.out.println("File non esiste");
				PrintWriter pw=new PrintWriter(nomeFile);
				
				r=0;
				
				for(int j=0;j<boxv.length;j++) 
					if(boxv[j].isSelected()) r++;
					
				pw.println(r);
				for(int i=0;i<boxv.length;i++) {
					if(boxv[i].isSelected()) {
						System.out.println("box:"+boxv[i].getText());
					pw.println(boxv[i].getText());
					
					}
					boxv[i].setSelected(false);

				}
					pw.close();
			}
			
		}
		
		public void SalvaConNome() throws IOException{
			PrintWriter pw=new PrintWriter(new FileWriter(nomeFileConNome));
			r=0;
			for(int j=0;j<boxv.length;j++) {
				if(boxv[j].isSelected()) r++;
				
			}
			pw.println(r);
			
			for(int i=0;i<boxv.length;i++) {
				if(boxv[i].isSelected()) {
					System.out.println(boxv[i].getText());
				pw.println(boxv[i].getText());
				}
				boxv[i].setSelected(false);

			}
			pw.close();
		}
		
		private void CopiaFile(String path) throws IOException { //copia i polinomi dal file
		BufferedReader filebuf=new BufferedReader(new FileReader(path));
		String righe=filebuf.readLine();
		r=Integer.parseInt(righe);
		
		String nextStr;
		nextStr=filebuf.readLine();
		O.setLayout(new GridLayout(r,1));
	
		
		
		while(nextStr!=null) {
			String temp=nextStr;
				boxv[i].setText(temp);
				O.add(boxv[i]);CI();i++;
				this.invalidate();
				this.validate();
				this.repaint();
			nextStr=filebuf.readLine();
		}
		this.invalidate();
		this.validate();
		this.repaint();
		
		filebuf.close();
		}
	
		
		private void ApriFile() throws IOException {
			JFileChooser jfc=new JFileChooser("C:\\Users\\zol\\Desktop\\eclipse\\pooZol");
			int val=jfc.showOpenDialog(null);
			if(val==JFileChooser.APPROVE_OPTION) {
				pathName=jfc.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Hai scelto "+ pathName);
				CopiaFile(pathName);
			}
			else if(val==JFileChooser.CANCEL_OPTION){
				JOptionPane.showMessageDialog(null, "non hai scelto");
			}
		}
///////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()==apri) {
				
				 try {
					ApriFile();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			 if(evt.getSource()==tipoAL) {
				 FinestraPOLGUI fg=new FinestraPOLGUI("POL_AL");
					fg.Poly=new PolinomioAL();
					fg.setVisible(true);
					this.invalidate();
					this.setVisible(false);	
			 }
			 if(evt.getSource()==tipoSet) {
				 FinestraPOLGUI fg=new FinestraPOLGUI("POL_Set");
					fg.Poly=new PolinomioSet();
					fg.setVisible(true);
					this.invalidate();
					this.setVisible(false);	
			 }
			 if(evt.getSource()==tipoMap) {
				 FinestraPOLGUI fg=new FinestraPOLGUI("POL_Map");
					fg.Poly=new PolinomioMap();
					fg.setVisible(true);
					this.invalidate();
					this.setVisible(false);	
			 }
			 if(evt.getSource()==tipoLL) {
				 FinestraPOLGUI fg=new FinestraPOLGUI("POL_LL");
					fg.Poly=new PolinomioLL();
					fg.setVisible(true);
					this.invalidate();
					this.setVisible(false);	
			 }
			if(evt.getSource()==salvaconNome) {
				String salvaNome=JOptionPane.showInputDialog("nomeFile:");
				this.nomeFileConNome=salvaNome+".txt";
				try {
					SalvaConNome();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
			}
			
			if(evt.getSource()==salva) {
				
				try {
					salva();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			
			if(evt.getSource()==somma) {
				JCheckBox retv[]=new JCheckBox[2];
				retv=BiSelected();
				if(retv.length==2) {
					Poly=Poly.crea();
					String Q=retv[0].getText();
					Poly=POL_Util.converti(Q,Poly);
					String W=retv[1].getText();
					POL_Util.converti(W,Poly);
					String E=Poly.toString();
					if(E.equals(""))E="0";
					if(RiempiBox(E));
					else{boxv[i]=new JCheckBox(E);
					N.add(boxv[i]);i++;}CI();
					this.invalidate();
					this.validate();
					this.repaint();
				}
				else {JOptionPane.showMessageDialog(null,"Scegli Due Polinomi!");
				}
			}	
			
			if(evt.getSource()==differenza) {
				JCheckBox retv[]=new JCheckBox[2];
				retv=BiSelected();
				if(retv[1]!=null) {
					Poly=Poly.crea();
					String Q=retv[1].getText();
					Poly=POL_Util.converti(Q,Poly);
					Poly=Poly.mul(new Monomio(-1,0));
					String W=retv[0].getText();
					POL_Util.converti(W,Poly);
					String E=Poly.toString();
					if(E.equals(""))E="0";
					if(RiempiBox(E));
					else{boxv[i]=new JCheckBox(E);
					N.add(boxv[i]);i++;}CI();
					this.invalidate();
					this.validate();
					this.repaint();
				}
				else {JOptionPane.showMessageDialog(null,"Scegli Due Polinomi!");
				}
			}	
			
			if(evt.getSource()==canc) {
				System.out.println("canc");
				Canc();	
			}


			if(evt.getSource()==esci) {
				try {
					salva();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
				System.exit(0);
			}
			
			if(evt.getSource()==calcolaX) {
				JCheckBox ret=new JCheckBox();
				ret=Selected();
				if(ret!=null) {
					String d=ret.getAccessibleContext().getAccessibleName();
					Poly=Poly.crea();
					Poly=POL_Util.converti(d,Poly);
					System.out.println("Poly:"+ Poly.toString());
					String tmp=JOptionPane.showInputDialog("x:");
					double x=Double.parseDouble(tmp);
					double y=Poly.valore(x);
					JOptionPane.showMessageDialog(null,"il risultato è:" + y);


				}

			}

			if(evt.getSource()==derivata)	{
				JCheckBox ret= new JCheckBox();
				
				ret=Selected();
				
				if(ret!=null) {
					String d=ret.getAccessibleContext().getAccessibleName();
					
					Poly=Poly.crea();
					Poly=POL_Util.converti(d,Poly);
					String rit=Poly.derivata().toString();
					
					if(RiempiBox(rit));
					else{boxv[i]=new JCheckBox(rit);
					N.add(boxv[i]);i++;}CI();
					this.invalidate();
					this.validate();
					this.repaint();


				}
				else {JOptionPane.showMessageDialog(null, "Pol non scelto ");}
			}

			if(evt.getSource()==pol) {
				String e=pol.getText();
				if(ControlloPol(e)==true) {
					Poly=Poly.crea();
					
					Poly=POL_Util.converti(pol.getText(),Poly);
					
					if(RiempiBox(Poly.toString()));
					else{boxv[i]=new JCheckBox(Poly.toString());
					N.add(boxv[i]);i++;}CI();
					pol.setText(String.format("", 0));
					this.invalidate();
					this.validate();
					this.repaint();
				}
				else {
					JOptionPane.showMessageDialog(null, "Pol non valido ");
				}
			}

			if(evt.getSource()==reset) {
				float e=0;
				pol.setText(String.format("", e));
				this.setVisible(false);
				String tmp=this.impl;
				FinestraPOLGUI fa=new FinestraPOLGUI(tmp);
				fa.setVisible(true);
			}
		}

	}
}
