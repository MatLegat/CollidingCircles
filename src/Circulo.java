import java.util.Random;


public class Circulo {

	protected double x,y, raio, speedX, speedY;
	protected double m=1;//massa

	public Circulo(double raio){
		Random rand = new Random();
		this.x = rand.nextInt(818)+1;
		this.y = rand.nextInt(648)+1;
		speedX = 0;
		while (speedX == 0)
			this.speedX = rand.nextInt(20)-10;
		speedY = 0;
		while (speedY == 0)
			this.speedY = rand.nextInt(20)-10;
		this.raio=raio;//define o circulo com posicao, velocidade e direcao/sentido aleatoriamente 
	}

	public double normaX(Circulo another){//modulo diferenca no eixo X
		if(this.x>another.x){
			return this.x - another.x;
		}else{
			return another.x-this.x;
		}
	}

	public double normaY(Circulo another){//modulo diferenca no eixo Y
		if(this.y>another.y){
			return this.y - another.y;
		}else{
			return another.y-this.y;
		}
	}

	public double calculaDistancia (Circulo another){
		double nX=this.normaX(another);
		double nY=this.normaY(another);

		return Math.sqrt((nX*nX)+(nY*nY));//pitagoras
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}


	public void move(){
		this.x+=speedX;
		this.y+=speedY;//muda a posicao do circulo de acordo com a velocidade
	}

	void trocaSentidoX(){
		speedX=-speedX;
	}
	void trocaSentidoY(){
		speedY=-speedY;
	}

	void colisao(Circulo another){
			//									--------a--------	  ---------b--------
			double collisionAngle = Math.atan2((another.y - this.y), (another.x - this.x));//obtem o angulo da colisao usando arc tg (a/b)
			double speed1 = this.distanciaVeloc();
			double speed2 = another.distanciaVeloc();

			double direction1 = Math.atan2(this.speedY, this.speedX); //obtem angulo da direcao usando arc tg
			double direction2 = Math.atan2(another.speedY, another.speedX); //mesma coisa

			double new_speedX1 = speed1 * Math.cos(direction1 - collisionAngle); //calcula nova velocidade levando angulo da colisao em consideracao
			double new_speedY1 = speed1 * Math.sin(direction1 - collisionAngle); //mesma coisa
			double new_speedX2 = speed2 * Math.cos(direction2 - collisionAngle); //mesma coisa
			double new_speedY2 = speed2 * Math.sin(direction2 - collisionAngle); //mesma coisa

			double final_speedX1 = ((this.m - another.m) * new_speedX1 + (another.m + another.m) * new_speedX2) / (this.m + another.m);//calcula velocidade considerando a massa das esferas
			double final_speedX2 = ((this.m + this.m) * new_speedX1 + (another.m - this.m) * new_speedX2) / (this.m + another.m);//mesma coisa
			double final_speedY1 = new_speedY1;
			double final_speedY2 = new_speedY2;

			double cosAngle = Math.cos(collisionAngle); //cos (angulo colisao)
			double sinAngle = Math.sin(collisionAngle); //seno ....
			
			this.speedX = (cosAngle * final_speedX1 - sinAngle * final_speedY1);//termina calculo considerando velocidade dos dois circulos
			this.speedY = (sinAngle * final_speedX1 + cosAngle * final_speedY1); //mesma coisa
			another.speedX = (cosAngle * final_speedX2 - sinAngle * final_speedY2); //mesma coisa
			another.speedY = (sinAngle * final_speedX2 + cosAngle * final_speedY2); //mesma coisa
	}

	public double distanciaVeloc(){//"tamanho" do vetor velocidade
		double sX=speedX;
		double sY=speedY;

		return Math.sqrt((sX*sX)+(sY*sY));//pitagoras
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
