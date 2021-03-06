package eulerproject.level4.problem98;

import eulerproject.tools.generators.SquareNumbers;
import eulerproject.tools.primes.Primes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution
{
    private static final String FILE = "src/main/java/eulerproject/level4/problem98/p098_words.txt";

    public static void main(String[] args) throws IOException
    {
        WordReader wordReader = new WordReader(FILE);
        Map<Character, Integer> hashMap = createHashMap();

        List<String> words = wordReader.read();
        Set<Set<String>> anagramsSets = convertWordListToAnagramSet(words, hashMap);
        System.out.println(anagramsSets);

        Set<Integer> sizes = anagramsSets.stream().map(set -> set.stream().collect(Collectors.toList()).get(0).length()).collect(Collectors.toSet());

        List<String> allSquares = getSquareNumbers(sizes);
        List<String> allFoundSquares = new ArrayList<>();

        anagramsSets.stream().forEach(set -> allFoundSquares.addAll(getAnagramSquareList(allSquares, set)));
        Integer maximum = allFoundSquares.stream().mapToInt(s -> new Integer(s)).max().getAsInt();
        System.out.println(maximum);
    }

    public static boolean isCorrectlyMappedAnagram(String string, String anagram)
    {
        return isCorrectlyMappedAnagramOneSide(string, anagram) &&
                isCorrectlyMappedAnagramOneSide(anagram, string);
    }

    public static boolean isCorrectlyMappedAnagramOneSide(String string, String anagram)
    {
        Map<Character, Set<Character>> characterListMap = new HashMap<>();

        IntStream.range(0, string.length()).forEach(i -> {
            if (characterListMap.containsKey(string.charAt(i))) {
                characterListMap.get(string.charAt(i)).add(anagram.charAt(i));
            } else {
                Set<Character> newSet = new HashSet<>();
                newSet.add(anagram.charAt(i));
                characterListMap.put(string.charAt(i), newSet);
            }
        });

        return characterListMap.values().stream().mapToInt(l -> l.size()).filter(i -> i == 1).sum() == characterListMap.size();
    }

    public static List<String> getAnagramSquareList(List<String> allSquares, Set<String> anagramSet)
    {
        int size = getSizeOfSingleElement(anagramSet);
        List<String> squares = getElementsWithLength(size, allSquares);
        return getMaxSquareNumberForAnagramsSet(anagramSet, getElementsWithLength(getSizeOfSingleElement(anagramSet), squares));
    }

    public static List<String> getElementsWithLength(int len, List<String> input)
    {
        return input.stream().filter(s -> s.length() == len).collect(Collectors.toList());
    }

    public static int getSizeOfSingleElement(Collection<String> collection)
    {
        return collection.stream().collect(Collectors.toList()).get(0).length();
    }

    public static List<String> getMaxSquareNumberForAnagramsSet(final Set<String> anagramSet, final List<String> squares)
    {
        final List<String> squaresFound = new ArrayList<>();

        for (String squareNumber : squares) {
            for (String first : anagramSet) {
                for (String second : anagramSet.stream().filter(s -> !s.equals(first)).collect(Collectors.toList())) {
                    String convertedNumber = anagramsFromReplacementMap(squareNumber, replacementMap(first, second));
                    if (isCorrectlyMappedAnagram(first, squareNumber) &&
                            isCorrectlyMappedAnagram(second, convertedNumber) &&
                            squares.contains(convertedNumber))
                        squaresFound.add(convertedNumber);
                }
            }
        }
        return squaresFound;
    }

    public static List<String> getSquareNumbers(int min, int max)
    {
        List<String> result = new ArrayList<>();
        long k = 1;
        String squareString;

        while ((squareString = new Long(SquareNumbers.getNumber(k)).toString()).length() <= max) {
            if ((squareString.length() >= min) && (squareString.length() <= max)) {
                result.add(squareString);
            }
            k++;
        }
        return result;
    }

    public static List<String> getSquareNumbers(Set<Integer> sizes)
    {
        List<String> result = new ArrayList<>();
        long k = 1;
        String squareString;
        int max = sizes.stream().mapToInt(s -> s).max().getAsInt();

        while ((squareString = new Long(SquareNumbers.getNumber(k)).toString()).length() <= max) {
            if (sizes.contains(squareString.length())) {
                result.add(squareString);
            }
            k++;
        }
        return result;
    }

    public static List<String> getStringAnagrams(List<String> strings, String inputString, final Map<Character, Integer> hashMap)
    {
        return strings.stream().filter(s -> isStringAnagram(s, inputString, hashMap)).collect(Collectors.toList());
    }

    public static boolean isStringAnagram(final String input, final String other, final Map<Character, Integer> hashMap)
    {
        if (input.length() != other.length())
            return false;

        final int[] inputHash = {1};
        input.chars().forEach(c -> inputHash[0] *= hashMap.get(new Character((char) c)));

        final int[] otherHash = {1};
        other.chars().forEach(c -> otherHash[0] *= hashMap.get(new Character((char) c)));

        return (inputHash[0] == otherHash[0]);
    }

    public static Map<Character, Integer> createHashMap()
    {
        Primes primes = new Primes(10000);
        primes.init();
        Map<Character, Integer> result = new HashMap<>();
        final int[] lastPrime = {2};

        IntStream.range('A', 'Z' + 1).forEach(c -> {
            lastPrime[0] = primes.getNextPrime(lastPrime[0]);
            result.put(new Character((char) c), primes.getNextPrime(lastPrime[0]));
        });
        return result;
    }

    public static Map<Character, Character> assignLetters(String word, String number)
    {
        Map<Character, Set<Character>> setMap = new HashMap<>();
        Map<Character, Character> resultMap = new HashMap<>();

        if (word.length() != number.length())
            return resultMap;

        IntStream.range(0, word.length()).forEach(i -> {
            setMap.put(word.charAt(i), new HashSet<>());
        });
        IntStream.range(0, number.length()).forEach(i -> {
            setMap.get(word.charAt(i)).add(number.charAt(i));
        });

        if (setMap.values().stream().mapToInt(s -> s.size()).filter(s -> s != 1).sum() != 0) {
            setMap.clear();
        }

        setMap.forEach((k, v) -> resultMap.put(k, (Character) v.toArray()[0]));

        return resultMap;
    }

    public static Set<Set<String>> convertWordListToAnagramSet(List<String> words, Map<Character, Integer> hashMap)
    {
        Set<Set<String>> allSetOfAnagrams = new HashSet<>();
        List<String> copyWords = new ArrayList<>(words);

        while (!copyWords.isEmpty()) {
            String word = copyWords.get(0);
            Set<String> anagramList = getStringAnagrams(copyWords, word, hashMap).stream().collect(Collectors.toSet());
            copyWords.removeAll(anagramList);
            allSetOfAnagrams.add(anagramList);
        }

        return allSetOfAnagrams.stream().filter(set -> set.size() > 1).collect(Collectors.toSet());
    }

    public static List<List<Integer>> replacementMap(String string1, String string2)
    {
        List<List<Integer>> charSetCount = new ArrayList<>();

        IntStream.range(0, string1.length()).forEach(i -> {
            char k = string1.charAt(i);
            charSetCount.add(getCharIndexes(k, string2));
        });

        return charSetCount;
    }

    public static List<Integer> getCharIndexes(final char k, final String input)
    {
        List<Integer> result = new ArrayList<>();

        IntStream.range(0, input.length())
                .forEach(i -> {
                    if (input.charAt(i) == k) {
                        result.add(i);
                    }
                });
        return result;
    }

    public static String anagramsFromReplacementMap(String input, List<List<Integer>> replacementMap)
    {

        char[] charArray = new char[replacementMap.size()];

        IntStream.range(0, charArray.length).forEach(i -> {
            charArray[i] = 0;
        });

        IntStream.range(0, replacementMap.size()).forEach(i -> {
            if (charArray[i] == 0)
                charArray[i] = input.charAt(replacementMap.get(i).get(0));
        });

        StringBuilder sb = new StringBuilder();

        IntStream.range(0, charArray.length).forEach(i -> {
            sb.append(charArray[i]);
        });

        return sb.toString();
    }
}
