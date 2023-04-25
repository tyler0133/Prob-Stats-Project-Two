import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * Stats Library - Tyler Smith
 *
 */

/**
 * New additions to the StatsLibrary for Project 2 added at the top
 */

public class StatsLibrary {

    /**
     *Returns the Hyper Geometric Distribution of the integers
     */

    public double findHyperGeoDist(int N, int K, int n, int k) {
        double numerator = findCombination(K, k) * findCombination(N - K, n - k);
        double denominator = findCombination(N, n);
        return numerator / denominator;
    }

    /**
     *Returns the Poisson Distribution for the lambda and integer
     */

    public double findPoissonDist(double lambda, int b) {
        double numerator = Math.pow(lambda, b) * Math.exp(-lambda);
        double denominator = findFactorial(b);
        return numerator / denominator;
    }

    /**
     *Returns the proportion of data within "c" standard deviations using Chebyshev's Theorem
     */
    public double findChebyshev(ArrayList<Integer> data, double c) {
        double mean = findMean(data);
        double stdev = findStandardDeviation(data);
        double numElements = data.size();
        double numWithinKStdev = 0;

        for (int x : data) {
            if (Math.abs(x - mean) <= c * stdev) {
                numWithinKStdev++;
            }
        }

        double proportionWithinKStdev = numWithinKStdev / numElements;
        double proportionOutsideKStdev = 1 - proportionWithinKStdev;
        double lowerBound = 1 - (1 / (c * c));
        double upperBound = 1;

        if (c <= 0 || Double.isNaN(lowerBound)) {
            throw new IllegalArgumentException("c must be a positive number greater than zero.");
        }
        if (c > 3) {
            return proportionWithinKStdev;
        } else {
            return Math.max(lowerBound, proportionOutsideKStdev);
        }
    }





    /**
     * Returns the mean of the inputted array list
     */

    public double findMean(ArrayList<Integer> inputNumbers) {
        double sum = 0;
        for(int singleElement : inputNumbers){
            sum = sum + singleElement;
        }
        double result = sum / inputNumbers.size();
        return result;
    }


    /**
     * Returns the median of the inputted array list
     */

    public double findMedian(ArrayList<Integer> inputNumbers) {
        Collections.sort(inputNumbers);
        int j = inputNumbers.size();
        if (j % 2 == 0) {
            int middleBottom = j / 2;
            int middleTop = middleBottom - 1;
            return (inputNumbers.get(middleTop) + inputNumbers.get(middleBottom)) / 2.0;
        } else {
            int middle = j / 2;
            return inputNumbers.get(middle);
        }
    }


    /**
     * Returns the mode of a given array
     * Inspiration: https://stackoverflow.com/questions/15725370/write-a-mode-method-in-java-to-find-the-most-frequently-occurring-element-in-an
     */

    public double findMode(int[] array) {
        int mode = array[0];
        int maxCounts = 0;
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            int count = 1;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == value) count++;
                if (count > maxCounts) {
                    mode = value;
                    maxCounts = count;
                }
            }
        }
        return mode;
    }


    /**
     * Returns the standard deviation of the inputted array list
     * Inspiration: https://stackoverflow.com/questions/37930631/standard-deviation-of-an-arraylist
     */

    public double findStandardDeviation(ArrayList<Integer> inputNumbers) {
        double mean = findMean(inputNumbers);
        double temp = 0;

        for (int i = 0; i < inputNumbers.size(); i++) {
            temp = Math.pow(i - mean,  2);
        }
        return Math.sqrt(findMean(inputNumbers));
    }

    /**
     * Returns the Union of two Array Lists
     */

    public ArrayList<String> findUnion(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> union = new ArrayList<>();
        union.addAll(list1);
        for (String day : list2) {
            if (!union.contains(day)) {
                union.add(day);
            }
        }
        return union;
    }

    /**
     * Returns the intersection of two Array Lists
     */
    public ArrayList<String> findIntersection(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> intersection = new ArrayList<>();
        for (String day : list1) {
            if (list2.contains(day)) {
                intersection.add(day);
            }
        }
        return intersection;
    }

    /**
     * Returns the complement of two Array Lists
     */
    public ArrayList<String> findComplement(ArrayList<String> sample, ArrayList<String> subset) {
        ArrayList<String> complement = new ArrayList<>();
        for (String day : sample) {
            if (!subset.contains(day)) {
                complement.add(day);
            }
        }
        return complement;
    }

    /**
     * Returns the combination of the inputted numbers
     */

    public int findCombination(int n, int r) {
        // Combination formula: n! / (r! * (n-r)!)
        int num = findFactorial(n);
        int denom = findFactorial(r) * findFactorial(n - r);
        return num / denom;
    }

    /**
     * Returns the permuation of the inputted numbers
     */

    public int findPermutation(int n, int r) {
        // Permutation formula: n! / (n-r)!
        int num = findFactorial(n);
        int denom = findFactorial(n - r);
        return num / denom;
    }

    /**
     * finds the factorial (!) of the integers to use in the findCombination and findPermutation classes
     */
    public int findFactorial(int n) {
        if (n == 0) {
            return 1;
        }
        else {
            return n * findFactorial(n - 1);
        }
    }

    /**
     * Returns the binomial distribution probability of the variables defined in the tester class.
     * Inspiration was given by https://www.geeksforgeeks.org/binomial-random-variables/
     */

    public double binomialProbability(int m, double p, double q, int x) {
        return findCombination(m, x) * Math.pow(p, x) * Math.pow(1 - p,  m - x);
    }

    /**
     * Returns the geometric distribution probability of the variables defined in the tester class.
     */

    public double geometricProbability(int x, double p) {
        return Math.pow(1-p, x-1) * p;
    }

}