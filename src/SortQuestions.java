public class SortQuestions {
     /*
    Question 1
    Intersection of two sets.
    Given two arrays a[] and b[], each containing N distinct 2D points in the plane,
    design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
     */

     public class Point2d implements Comparable<Point2d>{
        private int x,y;

        public Point2d(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point2d that){
            if(that.x > this.x) return -1;
            if(that.x < this.x) return 1;
            if(that.y < this.y) return -1;
            if(that.y > this.y) return 1;
            return 0; //they are equal
        }

        public int intersectionCount(Point2d[] a, Point2d[] b){
            Shell.sort(a);
            Shell.sort(b);

            int i = 0;
            int j = 0;
            int counter = 0;

            while(i<a.length && j<b.length){
                if(a[i].compareTo(b[j])==0){
                    counter++;
                    i++;
                    j++;
                } else if (a[i].compareTo(b[j]) < 0) {
                    i++;
                } else {
                    j++;
                }
            }
            return counter;
        }
    }

    /*
    Question 2
    Permutation.
    Given two integer arrays of size N, design a subquadratic algorithm to determine whether one is a permutation of the other.
    That is, do they contain exactly the same entries but, possibly, in a different order.
     */

    public boolean isPermutation(Integer[] a, Integer[] b){
        if(a.length != b.length) return false;
        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; i++) {
            if(a[i] != b[i]) return false;
        }
        return true;
    }

    /*
    Question 3
    Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
    swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
    color(i): color of pebble in bucket i.
    The performance requirements are as follows:
    At most N calls to color().
    At most N calls to swap().
    Constant extra space.
     */


}
