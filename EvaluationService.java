package com.revature.eval.java.core;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j = 0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// Jim-written

		if (phrase == "") {
			return "";
		}

		phrase = phrase.replace('-', ' ');
		String[] split = phrase.split(" ");
		String acronym = "";

		for (String add : split) {
			acronym += add.toUpperCase().charAt(0);
		}

		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// Jim-written

			if (this.getSideOne() == this.getSideTwo() && this.getSideTwo() == this.getSideThree()) {
				return true;
			}

			return false;
		}

		public boolean isIsosceles() {
			// Jim-written

			if (this.getSideOne() == this.getSideTwo() || this.getSideTwo() == this.getSideThree()
					|| this.getSideOne() == this.getSideThree()) {
				return true;
			}

			return false;
		}

		public boolean isScalene() {
			// Jim-written

			if (!(this.getSideOne() == this.getSideTwo() || this.getSideTwo() == this.getSideThree()
					|| this.getSideOne() == this.getSideThree())) {
				return true;
			}

			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// Jim-written

		int score = 0;
		String upperString = string.toUpperCase();

		for (int i = 0; i < upperString.length(); i++) {
			if (upperString.charAt(i) == ' ') {
				break;
			}
			if (upperString.charAt(i) < 'A' || upperString.charAt(i) > 'Z') {
				return 0;
			}

			switch (upperString.charAt(i)) {
			case 'Z':
			case 'Q':
				score += 10;
				break;
			case 'X':
			case 'J':
				score += 8;
				break;
			case 'K':
				score += 5;
				break;
			case 'F':
			case 'H':
			case 'V':
			case 'W':
			case 'Y':
				score += 4;
				break;
			case 'B':
			case 'C':
			case 'M':
			case 'P':
				score += 3;
				break;
			case 'D':
			case 'G':
				score += 2;
				break;
			default:
				score += 1;
				break;
			}
		}

		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// Jim-written

		String number = "";
		boolean firstNum = true;
		int numberDigits = 0;

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) < 48 || string.charAt(i) > 57) {
				switch (string.charAt(i)) {
				case ' ':
				case '(':
				case ')':
				case '-':
				case '.':
				case '+':
					break;
				default:
					throw new IllegalArgumentException();
				}
			}

			if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
				if (!firstNum || string.charAt(i) != '1') {
					number += string.charAt(i);
					numberDigits++;
					if (numberDigits > 11) {
						throw new IllegalArgumentException();
					}
				}

				firstNum = false;
			}
		}

		return number;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// Jim-written

		Map<String, Integer> wordCount = new HashMap<>();
		string = string.replace("\n", "");
		String[] words = string.split("[\\W]");

		for (int i = 0; i < words.length; i++) {
			if (wordCount.containsKey(words[i])) {
				wordCount.put(words[i], wordCount.get(words[i]) + 1);
			} else {
				wordCount.put(words[i], 1);
			}
		}

		return wordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> implements Comparable<T> {
		private List<T> sortedList;
		private int index;

		public int indexOf(T t) {
			// Jim-written

			int half = sortedList.size() / 2;
			index = half;

			System.out.println(index);
			System.out.println(t);

			for (; this.compareTo(t) != 0; half /= 2) {
				if (half < 1) {
					half = 1;
				}
				if (this.compareTo(t) < 0) {
					index += half;
				} else if (this.compareTo(t) > 0) {
					index -= half;
				} else if (this.compareTo(t) == 0) {
					break;
				}
			}

			return index;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

		@Override
		public int compareTo(T arg0) {
			// Jim-written

			if (arg0 instanceof Integer || arg0 instanceof Character)
				return (int) this.sortedList.get(index) - (int) arg0;
			else if (arg0 instanceof String)
				return Integer.parseInt(this.sortedList.get(index).toString()) - Integer.parseInt(arg0.toString());
			else
				return 0;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// Jim-written

		String words[] = string.split("[\\W]");
		String pigLatin = "";
		boolean wordDone = false;
		boolean notFirst = false;
		boolean oneU = false;

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				switch (words[i].charAt(j)) {
				case 'a':
				case 'e':
				case 'i':
				case 'o':
				case 'A':
				case 'E':
				case 'I':
				case 'O':
					if (notFirst) {
						pigLatin += " ";
					}

					for (int k = j; k < words[i].length(); k++) {
						pigLatin += words[i].charAt(k);
					}
					for (int k = 0; k < j; k++) {
						pigLatin += words[i].charAt(k);
					}
					pigLatin += "ay";

					wordDone = true;
					notFirst = true;
					oneU = false;
					break;
				case 'u':
				case 'U':
					oneU = true;
					break;
				default:
					if (oneU) {
						j--;
						if (notFirst) {
							pigLatin += " ";
						}

						for (int k = j; k < words[i].length(); k++) {
							pigLatin += words[i].charAt(k);
						}
						for (int k = 0; k < j; k++) {
							pigLatin += words[i].charAt(k);
						}
						pigLatin += "ay";

						wordDone = true;
						notFirst = true;
						oneU = false;
					}
					break;
				}
				if (wordDone) {
					wordDone = false;
					break;
				}
			}
		}

		return pigLatin;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// Jim-written

		int inputCopy = input;
		int armstrong = 0;
		String numLength = String.valueOf(input);

		while (inputCopy > 0) {
			armstrong += Math.pow((inputCopy % 10), numLength.length());
			inputCopy /= 10;
		}

		return input == armstrong;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// Jim-written

		BigInteger checker = new BigInteger(String.valueOf(2));
		List<Long> outList = new ArrayList<Long>();

		while (checker.longValue() <= l) {
			while (l % checker.longValue() == 0) {
				outList.add(checker.longValue());
				l /= checker.longValue();
			}
			checker = checker.nextProbablePrime();
		}

		return outList;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// Jim-written

			String out = "";
			char[] cipher = string.toCharArray();

			for (int i = 0; i < cipher.length; i++) {
				if (cipher[i] >= 'a' && cipher[i] <= 'z') {
					cipher[i] = (char) ((int) cipher[i] + key);
					if (cipher[i] > 'z') {
						cipher[i] -= 26;
					}
				} else if (cipher[i] >= 'A' && cipher[i] <= 'Z') {
					cipher[i] = (char) ((int) cipher[i] + key);
					if (cipher[i] > 'Z') {
						cipher[i] -= 26;
					}
				}
				out += cipher[i];
			}

			return out;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// Jim-written

		if (i <= 0) {
			throw new IllegalArgumentException();
		}

		int prime = 1;
		boolean isPrime = false;

		for (int j = 0; j < i; j++) {
			for (; !isPrime; ) {
				prime++;
				isPrime = true;
				for (int k = 2; k < prime && isPrime; k++) {
					if (prime % k == 0) {
						isPrime = false;
					}
				}
			}
			isPrime = false;
		}

		return prime;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// Jim-written

			String out = "";
			char[] cipher = string.toCharArray();
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			char[] reversed = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".toCharArray();
			int fifth = 0;

			for (int i = 0; i < cipher.length; i++) {
				if (alphabet.indexOf(cipher[i]) != -1) {
					out += reversed[alphabet.indexOf(cipher[i])];
					fifth++;
				} else if (cipher[i] >= '0' && cipher[i] <= '9') {
					out += cipher[i];
					fifth++;
				}
				if (fifth >= 5 && i < cipher.length) {
					out += " ";
					fifth = 0;
				}
			}

			out = out.toLowerCase();

			if (out.charAt(out.length() - 1) == ' ') {
				out = out.substring(0, out.length() - 1);
			}

			return out;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// Jim-written

			String out = "";
			char[] cipher = string.toCharArray();
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			char[] reversed = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba".toCharArray();

			for (int i = 0; i < cipher.length; i++) {
				if (alphabet.indexOf(cipher[i]) != -1) {
					out += reversed[alphabet.indexOf(cipher[i])];
				} else if (cipher[i] >= '0' && cipher[i] <= '9') {
					out += cipher[i];
				}
			}

			return out;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// Jim-written

		int sum = 0;
		int numbers = 0;

		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
				sum += (10 - numbers) * (string.charAt(i) - 48);
				numbers++;
			} else if (string.charAt(i) == 'X' && i + 1 >= string.length()) {
				sum += 10;
				numbers++;
			}
		}

		if (numbers < 10) {
			return false;
		}

		return sum % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// Jim-written

		Set<Character> set = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
				'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
		string = string.toLowerCase();

		for (int i = 0; i < string.length(); i++) {
			if (set.contains(string.charAt(i))) {
				set.remove(string.charAt(i));
			}
		}

		return set.isEmpty();
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// Jim-written

		if (given instanceof LocalDate)
			return (LocalDate.parse(given.toString()).atStartOfDay()).plusSeconds((long) Math.pow(10, 9));
		else if (given instanceof LocalDateTime)
			return ((LocalDateTime) given).plusSeconds((long) Math.pow(10, 9));

		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// Jim-written

		int sum = 0;
		Set<Integer> multiples = new HashSet<>();

		for (int k = 0; k < set.length; k++) {
			for (int j = 1; j < i; j++) {
				if (!multiples.contains(set[k] * j) && set[k] * j < i) {
					multiples.add(set[k] * j);
				}
			}
		}

		for (Iterator<Integer> iter = multiples.iterator(); iter.hasNext();) {
			sum += iter.next();
		}

		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// Jim-written
		
		string = string.replaceAll("\\s+", "");
		char[] luhn = string.toCharArray();
		boolean second = false;
		int sum = 0;
		int luhnValue = 0;
		
		for (int i = luhn.length - 1; i > -1; i--) {
			if (luhn[i] >= '0' && luhn[i] <= '9') {
				luhnValue = luhn[i] - 48;
				if (second) {
					luhnValue *= 2;
					if (luhnValue > 9) {
						luhnValue -= 9;
					}
				}
				sum += luhnValue;
				second = !second;
			} else {
				return false;
			}
		}

		return sum % 10 == 0;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// Jim-written

		string = string.replaceAll("\\?", "");
		String[] split = string.split("\\s+");
		int number = 0;
		int[] numbers = new int[2];
		int keyword = 0;
		boolean negative = false;

		for (int i = 0; i < split.length; i++) {
			if (split[i].charAt(0) == '-') {
				split[i] = split[i].replaceAll("\\-", "");
				negative = true;
			}
			if (split[i].matches("\\d+")) {
				if (negative)
					numbers[number] = -(Integer.parseInt(split[i]));
				else
					numbers[number] = Integer.parseInt(split[i]);

				negative = false;
				if (number == 0) {
					keyword = i + 1;
					number = 1;
				}
			}
		}

		switch (split[keyword]) {
		case "plus":
			return numbers[0] + numbers[1];
		case "minus":
			return numbers[0] - numbers[1];
		case "multiplied":
			return numbers[0] * numbers[1];
		case "divided":
			return numbers[0] / numbers[1];
		}

		return 0;
	}

}
