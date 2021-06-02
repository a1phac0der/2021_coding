package misc;

public class MessageLoop implements Runnable {
	private String[] messages = { "Hello, My name is Sridhar.", "I work at eBay",
			"I am looking out for a better change", "I wanna get through all the interview rounds well" };

	public static void threadMessage(String message) {
		System.out.println(String.format("%s: %s%n", Thread.currentThread().getName(), message));
	}

	public void run() {
		try {
			for (int i = 0; i < messages.length; i++) {
				Thread.sleep(1000);
				threadMessage(messages[i]);
			}
		} catch (InterruptedException e) {
			threadMessage("I wasn't done.");
		}

	}

	public static void main(String[] args) throws InterruptedException {
		long patience = 2 * 1000;
		threadMessage("Starting message loop thread");
		Thread t = new Thread(new MessageLoop());
		long start = System.currentTimeMillis();
		t.start();

		threadMessage("Waiting for message loop thread to finish");

		while (t.isAlive()) {
			threadMessage("Still waiting..");
			t.join(1000);
			if ((System.currentTimeMillis() - start) > patience && t.isAlive()) {
				threadMessage("Tired of waiting!");
				t.interrupt();
				t.join();
			}
		}
		threadMessage("Finally!");
	}

}
