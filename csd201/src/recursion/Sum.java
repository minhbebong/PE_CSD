/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

/**
 *
 * @author Admin
 */
public class Sum {

    static int sum(int a, int n) {
        if (n == 1) {
            return 1;
        }
        int t = sum(a, n / 2);
        if (n % 2 == 0) {
            return t * t;

        } else {
            return t * t * a;
        }
    }

    static int findMin(int[] a, int n) {
        if (n == 1) {
            return a[0];
        }
        return Integer.min(a[n - 1], findMin(a, n - 1));
    }

    static int findSum(int[] a, int n) {
        if (n == 0) {
            return 0;
        }
        return a[n - 1] + findSum(a, n - 1);
    }

    static int isPalindrome(char[] a, int begin, int n) {
        if(a.length == 0 || a.length == 1) return 1;
        if(begin >= n) return 1;
        if (a[begin] != a[n - 1]) {
            return 0;
        } else {
            return isPalindrome(a, begin + 1, n - 1);
        }
    }

    static int binarySearch(int[] a, int begin, int end, int x) {
            int temp = (begin + end) / 2;
            if(a[begin] > x || a[end] < x) return -1;
            if(a[temp] == x){
                return temp;
            } else if(a[temp] > x){
                return binarySearch(a, begin, temp, x);
            } else {
                return binarySearch(a, temp+1, end, x);
            }
    }

    static int greatestCommonDivisor(int m, int n) {
        if (n == 0) {
            return m;
        }

        return greatestCommonDivisor(n, m % n);
    }

    static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * power(x, n - 1);
    }

    static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    static double addReciprocals(int n) {
        if (n == 1) {
            return 1;
        }
        return (double) 1 / n + addReciprocals(n - 1);

    }

    static int stirlingNumber(int n, int k) {
        if (n == 0 && k == 0) {
            return 1;
        } else if (k == 0 && n != 0) {
            return 0;
        } else {
            return stirlingNumber(n - 1, k - 1) - n * stirlingNumber(n - 1, k);
        }

    }

    public static void main(String[] args) {
//        System.out.println(sum(3, 2));
        int[] a = {1, 2, 3, 4, 5, 8};
        System.out.println(binarySearch(a, 0, 5, 10));
//        System.out.println(a);
//        char []b = {'a', 'b', 'c', 'c', 'b', 'a'};       
//        System.out.println(isPalindrome(b, 0, 6));
//        System.out.println(findMin(a, 6));
//        System.out.println(findSum(a, 6));
//        System.out.println(addReciprocals(3));
//        System.out.println(greatestCommonDivisor(0, 155));
////        show(145);
//        System.out.println(ex238(6));
    }

    static String ex238(int n) {
        if (n <= 0) {
            return "";
        }
        return (ex238(n - 3) + n + ex238(n - 2) + n);
    }
}
