package main_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainClass {
	
	public static void main(String[] arg0){
		Integer numberList = null;
		do{
			String numberSelection = JOptionPane.showInputDialog("Generatore di cartelle\nInserisci il numero di liste che vuoi generare.\nOgni lista conterrà 6 cartelle con tutti i numeri da 1 a 90");
			if(numberSelection == null){
				System.exit(0);
			}
			try{
				numberList = new Integer(numberSelection);
			}catch (NumberFormatException e){
				numberList = new Integer(-1);
			}
		}while(numberList.intValue() <=0);
		
		final JFrame frame = new JFrame();
		
		frame.setTitle("Tombola Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 400, 400);
	
		JButton button = new JButton("Creazione in corso.....");
		button.setBounds(0, 0, 20, 20);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		button.setEnabled(false);
		frame.add(button);
		frame.setVisible(true);
		
		
		PageGenerator page = new PageGenerator(numberList.intValue());
		page.append(numberList.intValue());
		//page.printTable();

		PdfCreator pdfCreator = new PdfCreator();
		pdfCreator.createPDF("lista_tabelle.pdf",page.getPages());
		String text = "Creazione Completata. Clicca per chiudere";
		button.setText(text);
		
		button.setEnabled(true);

		frame.repaint();

	}

}
