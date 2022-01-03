package Aste;

public class Main {
	public static void main(String[] args) throws Exception{
		new Thread(new Server()).start();
		for(int i = 13;i>0;--i)
			new Thread(new Client()).start();
	}

}
