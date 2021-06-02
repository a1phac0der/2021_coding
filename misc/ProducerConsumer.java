package misc;

public class ProducerConsumer {

	static class Message {
		String data = null;
		boolean available = false;
	}

	public static void main(String[] args) {
		final Message msgObj = new Message();
		new Thread(new Runnable() {
			public void run() {
				String[] data = { "Hello, I've got a call from a Recruiter.", "I've sent my resume.",
						"Recruiter has scheduled the interview.", "I started preparing for the interview.",
						"I went and attended the interview.", "I cleared all the rounds",
						"I got a great offer from them.", "DONE" };

				for (String message : data) {
					/*try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
					
					synchronized (msgObj) {
						while (msgObj.available) {
							try {
								msgObj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						if (!msgObj.available) {
							System.out.println("Dumping message in shared object: " + message);
							msgObj.data = message;
							msgObj.available = true;
							msgObj.notify();
						} 
					}
				}

			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					
					synchronized (msgObj) {
						while (!msgObj.available) {
							try {
								msgObj.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (msgObj.available) {
							if(msgObj.data.equals("DONE"))
								break;
							String localMessage = msgObj.data;
							System.out.println("Taking data from message Obj: " + localMessage);
							msgObj.data = null;
							msgObj.available = false;
							msgObj.notify();
						}
					}
				}
			}
		}).start();

	}

	public static void putMessage(Message msgObj, String message) {

	}

}
