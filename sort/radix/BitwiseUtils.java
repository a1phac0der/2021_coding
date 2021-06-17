package sort.radix;

public class BitwiseUtils {
    private int bitsInWord;
    private int bitsInRadix;

    public BitwiseUtils(int bitsInWord, int bitsInRadix){
        this.bitsInWord = bitsInWord;
        this.bitsInRadix = bitsInRadix;
    }

    public int getDigit(int byteBits, int position){
        return (byteBits >> (bitsInWord - bitsInRadix*position)) & ((1 << bitsInRadix)-1);
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseUtils(7, 2).getDigit(97, 1));
        System.out.println(Integer.parseInt("1100001",2));
    }
}
