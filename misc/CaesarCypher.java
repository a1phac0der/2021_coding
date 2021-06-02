package misc;

public class CaesarCypher {
	char[] encoder = new char[26];
	char[] decoder = new char[26];

	public CaesarCypher(int rotation) {
		for (int i = 0; i < encoder.length; i++) {
			encoder[i] = (char) ('A' + ((i + rotation) % 26));
			decoder[i] = (char) ('A' + (i - rotation + 26) % 26);
		}
	}

	public String encrypt(String original) {
		char[] originalChars = original.toCharArray();
		for (int i = 0; i < originalChars.length; i++) {
			if (Character.isUpperCase(originalChars[i])) {
				int j = originalChars[i] - 'A';
				originalChars[i] = encoder[j];
			}
		}
		return new String(originalChars);
	}

	public String decrypt(String secret) {
		char[] secretChars = secret.toCharArray();
		for (int i = 0; i < secretChars.length; i++) {
			if (Character.isUpperCase(secretChars[i])) {
				int index_decoder = secretChars[i] - 'A';
				secretChars[i] = decoder[index_decoder];
			}
		}
		return new String(secretChars);
	}

	public static void main(String[] args) {
		CaesarCypher cypher = new CaesarCypher(5);
		String message = "THIS IS SRIDHAR SENDING A SECRET MESSAGE TO THE OTHER PLANET";
		System.out.println(message);
		String encryptedMessage = cypher.encrypt(message);
		System.out.println(encryptedMessage);
		String decryptedMessage = cypher.decrypt(encryptedMessage);
		System.out.println(decryptedMessage);

	}
}
