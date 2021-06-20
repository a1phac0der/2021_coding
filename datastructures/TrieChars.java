package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieChars {
    static class Node {
        Map<Character, Node> children;

        public Node() {
            children = new HashMap();
        }
    }

    Node node;

    public TrieChars() {
        this.node = new Node();
    }

    public void insert(final String word) {
        Node node = this.node;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new Node());
            }
            node = node.children.get(c);
        }
    }

    public Node findNode(final String word) {
        Node node = this.node;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            }
            else { return null; }
        }
        return node;
    }

    public boolean deleteWord(final String word) {
        if (findNode(word) == null) {
            return false;
        }
        final Node node = this.node;
        deleteWord(word, node, 0);
        return true;
    }

    private boolean deleteWord(final String word, final Node node, final int index) {
        if (index >= word.length() - 1) {return true;}
        Node childNode = node.children.get(word.charAt(index));
        if (deleteWord(word, childNode, index + 1)) {
            node.children.remove(word.charAt(index));
        }
        return node.children.isEmpty();
    }

    public List<String> collectAutoFill(String word) {
        Node node = findNode(word);
        if (node == null) {
            return null;
        }
        List<String> autoFill = new ArrayList<>();
        collectAutoFill(node, autoFill, word);
        return autoFill;
    }

    private void collectAutoFill(Node node, List<String> collection, String word) {
        if (node.children.isEmpty() || collection.isEmpty()) { collection.add(word);}
        for (Character c : node.children.keySet()) {
            word += c;
            collectAutoFill(node.children.get(c), collection, word);
            word = word.substring(0, word.length() - 1);
        }
    }

    public static void main(String[] args) {
        TrieChars trieChars = new TrieChars();
        trieChars.insert("that");
        trieChars.insert("they");
        trieChars.insert("there");
        trieChars.insert("those");
        trieChars.insert("decided");
        trieChars.insert("demanded");
        trieChars.insert("deposited");
        trieChars.insert("derived");

        TrieWords trieWords = new TrieWords();
        trieWords.insert(Arrays.asList("they decided the grey sofa".split(" ")));
        trieWords.insert(Arrays.asList("they decided to helped the poor".split(" ")));
        trieWords.insert(Arrays.asList("they decided to buy the house because".split(" ")));
        trieWords.insert(Arrays.asList("they decided on forever".split(" ")));
        trieWords.insert(Arrays.asList("they decided to dismantle their kitchen".split(" ")));
        trieWords.insert(Arrays.asList("they decided to buy the brown shoes".split(" ")));
        trieWords.insert(Arrays.asList("they decorated to dismantle their kitchen".split(" ")));
        trieWords.insert(Arrays.asList("they demanded to buy the brown shoes".split(" ")));
        trieWords.insert(Arrays.asList("they deposited by now".split(" ")));
        trieWords.insert(Arrays.asList("they derived to put an inn".split(" ")));
        trieWords.insert(Arrays.asList("there is no glory in this".split(" ")));
        trieWords.insert(Arrays.asList("there is an apple pie in the kitchen".split(" ")));
        trieWords.insert(Arrays.asList("there is an old man standing".split(" ")));
        trieWords.insert(Arrays.asList("there is a chair to sit down".split(" ")));
        trieWords.insert(Arrays.asList("that is not my problem".split(" ")));
        trieWords.insert(Arrays.asList("that should go into this".split(" ")));
        trieWords.insert(Arrays.asList("that is not really true".split(" ")));
        trieWords.insert(Arrays.asList("that was in kitchen last time I checked".split(" ")));
        trieWords.insert(Arrays.asList("those are good people".split(" ")));

        String sentence = "they de";
        String[] words = sentence.split(" ");
        String lastWord = words[words.length-1];
        List<String> possibleWords = trieChars.collectAutoFill(lastWord);
        List<List<String>> inputSentences = new ArrayList<>();
        for (String word: possibleWords){
            List<String> newSentence = new ArrayList<>();
            if(words.length > 1){
                newSentence.addAll(Arrays.asList(Arrays.copyOf(words, words.length-1)));
            }
            newSentence.add(word);
            inputSentences.add(newSentence);
        }
        List<List<String>> possibleMatches = new ArrayList<>();
        for (List<String> input: inputSentences){
            List<List<String>> possibleSentences = trieWords.collectAutoFill(input);
            if (possibleSentences != null){
                possibleMatches.addAll(possibleSentences);
            }
        }
        System.out.println(possibleMatches);
    }

}
