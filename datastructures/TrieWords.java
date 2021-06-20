package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieWords {
    static class Node {
        Map<String, Node> children;

        public Node() {
            children = new HashMap();
        }
    }

    Node node;

    public TrieWords() {
        this.node = new Node();
    }

    public void insert(final List<String> words) {
        Node node = this.node;
        for (String word : words) {
            if (!node.children.containsKey(word)) {
                node.children.put(word, new Node());
            }
            node = node.children.get(word);
        }
    }

    public Node findNode(final List<String> words) {
        Node node = this.node;
        for (String word : words) {
            if (node.children.containsKey(word)) {
                node = node.children.get(word);
            }
            else { return null; }
        }
        return node;
    }

    public boolean deleteWord(final List<String> words) {
        if (findNode(words) == null) {
            return false;
        }
        final Node node = this.node;
        deleteWord(words, node, 0);
        return true;
    }

    private boolean deleteWord(final List<String> words, final Node node, final int index) {
        if (index >= words.size() - 1) {return true;}
        Node childNode = node.children.get(words.get(index));
        if (deleteWord(words, childNode, index + 1)) {
            node.children.remove(words.get(index));
        }
        return node.children.isEmpty();
    }

    public List<List<String>> collectAutoFill(List<String> words) {
        Node node = findNode(words);
        if (node == null) {
            return null;
        }
        List<List<String>> autoFill = new ArrayList<>();
        collectAutoFill(node, autoFill, words);
        return autoFill;
    }

    private void collectAutoFill(Node node, List<List<String>> collection, List<String> words) {
        List<String> wordsCopy = new ArrayList<>(words);
        if (node.children.isEmpty() || collection.isEmpty()) { collection.add(wordsCopy);}
        for (String word : node.children.keySet()) {
            wordsCopy.add(word);
            collectAutoFill(node.children.get(word), collection, new ArrayList<>(wordsCopy));
            wordsCopy.remove(wordsCopy.size() - 1);
        }
    }

    public static void main(String[] args) {
        TrieWords trieWords = new TrieWords();
        trieWords.insert(Arrays.asList("they decided the grey sofa".split(" ")));
        trieWords.insert(Arrays.asList("they decided to helped the poor".split(" ")));
        trieWords.insert(Arrays.asList("they decided to buy the house because".split(" ")));
        trieWords.insert(Arrays.asList("they decided on forever".split(" ")));
        trieWords.insert(Arrays.asList("they decided to dismantle their kitchen".split(" ")));
        trieWords.insert(Arrays.asList("they decided to buy the brown shoes".split(" ")));
        trieWords.insert(Arrays.asList("they decided by now".split(" ")));
        trieWords.insert(Arrays.asList("they decided to put an inn".split(" ")));
        trieWords.insert(Arrays.asList("there is no glory in this".split(" ")));
        trieWords.insert(Arrays.asList("there is an apple pie in the kitchen".split(" ")));
        trieWords.insert(Arrays.asList("there is an old man standing".split(" ")));
        trieWords.insert(Arrays.asList("there is a chair to sit down".split(" ")));
        trieWords.insert(Arrays.asList("that is not my problem".split(" ")));
        trieWords.insert(Arrays.asList("that should go into this".split(" ")));
        trieWords.insert(Arrays.asList("that is not really true".split(" ")));
        trieWords.insert(Arrays.asList("that was in kitchen last time I checked".split(" ")));
        trieWords.insert(Arrays.asList("those are good people".split(" ")));
        System.out.println(trieWords.collectAutoFill(Arrays.asList("they decided to".split(" "))));
    }
}
