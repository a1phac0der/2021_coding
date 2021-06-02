package misc;

public class DeadLock {
	static class Communicate{
		String message;

		public synchronized void sendMessage(Communicate receiver){
			System.out.println("Sending message: " + this.message);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receiver.acknowledgeMessage(this.message);
		}
		
		public synchronized void acknowledgeMessage(String message){
			System.out.println("Message received: " + message);
		}
		
		public static void main(String[] args) {
			
			final Communicate armstrong = new Communicate();
			armstrong.message = "Hello, this is Armstrong boroadcasting from Moon. Please acknowledge back.";
			
			final Communicate sridhar = new Communicate();
			sridhar.message = "Hello, this is Sridhar boroadcasting from Earth. Please acknowledge back.";
			
				new Thread(new Runnable(){
					public void run(){
						sridhar.sendMessage(armstrong);
					}
				}).start();
			
			new Thread(new Runnable(){

				public void run() {
					armstrong.sendMessage(sridhar);
				}
				
			}).start();
			
			
		}
	}
}
