import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Container1 extends Container {

	private int nroCirculos, raio;
	private Timer r; 
	private Circulo[] circulo;
	boolean erro2avisado=false, erro1avisado = false;
	
	Container1(int nroCirculos, int raio){
		r = new Timer (10, new Ticker(this));
		this.raio=raio;
		this.nroCirculos=nroCirculos;
		circulo=new Circulo[nroCirculos];
		int tentativa = 0;
		boolean ok = false;
		while (!ok)
		for(int i=0; i<nroCirculos; i++){//cria os circulos ocupando espacos diferentes
			boolean circuloOK = false;
			int k=0;
			while(!circuloOK){
				circulo[i] = new Circulo(raio);
				circuloOK=this.testaCirculo(i);
				k++;
				if(k==500){
					ok = false;
					if (tentativa < 10) {
						tentativa++;
						break;
					}
					System.err.println("PROBLEMA: Nao couberam todas as bolas na tela. \n"
							+ "          Tente diminuir o raio e/ou a quantidade de circulos para evitar.");
					System.exit(1);
				}
			ok = true;
			}
		}
		r.start();
	}

	public void paint(Graphics g){

		super.paint(g);

		Color cores[] = {Color.GREEN, Color.ORANGE, Color.BLUE, Color.RED, Color.BLACK, Color.CYAN, Color.DARK_GRAY,
				Color.GRAY, Color.LIGHT_GRAY, Color.PINK, Color.WHITE, Color.YELLOW};//array de cores
		
		int corAtual=0;
		for(int i=0; i<nroCirculos; i++){
			g.setColor(cores[corAtual]);//pinta circulo da cor
			g.fillOval(circulo[i].getX()-raio, circulo[i].getY()-raio, raio*2, 2*raio);
			
			if(corAtual==cores.length-1){
				corAtual=0;//se for a ultima cor, volta para a primeira
			}else{
				corAtual ++;//vai para a proxima cor
			}
		}
	}

	void tick(){

		for (int i=0; i<nroCirculos; i++){//verifica condicoes e move todos os circulos
			
			for (int j=i+1; j<nroCirculos && j<=100;j++){ //colisao entre circulos
				for (int k=0; circulo[i].calculaDistancia(circulo[j])<2*raio; k++){//testa cada circulo com todos os proximos
					if(k==0){//primeira execucao
						circulo[i].colisao(circulo[j]);
					}
					circulo[i].move();//move ate um circulo sair do outro
					circulo[j].move();//=================================
				}
			}
			
			//Nao deixa  circulo sair da tela:
			for (int k=0; k<1 && circulo[i].getX()<raio; k++) {
				circulo[i].trocaSentidoX();
				circulo[i].setX(raio);
				circulo[i].move();
			}
			for (int k=0; k<1 && circulo[i].getX()>820-raio; k++) {
				circulo[i].trocaSentidoX();
				circulo[i].setX(820-raio);
				circulo[i].move();
			}
			for (int k=0; k<1 && circulo[i].getY()<raio; k++) {
				circulo[i].trocaSentidoY();
				circulo[i].setY(raio);
				circulo[i].move();
			}
			for (int k=0; k<1 && circulo[i].getY()>650-raio; k++) {
				circulo[i].trocaSentidoY();
				circulo[i].setY(650-raio);
				circulo[i].move();
			}
			

			circulo[i].move();//move o circulo atual

		}

		this.repaint();
	}
	
	public boolean testaCirculo(int i){//verifica se o circulo nao esta fora da tela ou "dentro" de outro
		if (circulo[i].getX()<this.raio || circulo[i].getX()>820-raio || circulo[i].getY()<raio || circulo[i].getY()>650-raio){//se esta fora da tela
			return false;
		}
		
		for (int j=i-1; j>=0; j--){
			if (circulo[i].calculaDistancia(circulo[j])<2*raio){//se esta "dentro" de outro circulo
				return false;
			}
		}
		
		return true;
		
	}
}
