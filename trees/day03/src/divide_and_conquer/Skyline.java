package divide_and_conquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    public static class Point {
        public int x;
        public int y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Building {
        private int l, r, h;
        public Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        // TODO
        List<Point> points = new ArrayList<>();

        points = build_skyline(B);

        System.out.println("BUILT SKYLINE: ");
        for(Point x : points){
            System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
        }
        List<Point> points2 = condense_skyline(points);


        System.out.println("\n\nCONDENSED SKYLINE: ");
        for(Point x : points){
            System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
        }

        points2 = condense_skyline(points2);

        return points2;
    }

    private static List<Point> build_skyline(Building[] B){
        List<Point> points = new ArrayList<>();
        if(B.length == 0){
            return null;
        }
        if(B.length == 1){
            for(int i = B[0].l; i < B[0].r; i ++){
                points.add(new Point(i, B[0].h));
            }
            points.add(new Point(B[0].r, 0));
            System.out.println("Points: ");
            System.out.println(points.size());
            for(Point x : points){
                System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
            }
            return points;
        }

        List<Point> left, right;
        left = build_skyline(Arrays.copyOfRange(B,0, B.length/2));
        right = build_skyline(Arrays.copyOfRange(B, B.length/2, B.length));
        System.out.println(left.size());
        System.out.println(right.size());
        System.out.println(points.size());
        for(Point x : points){
            System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
        }
        points = merge_skyline(left, right);
        return points;
    }

    private static List<Point> merge_skyline(List<Point> one, List<Point> two){
        List<Point> merged_vals = new ArrayList<>();
        while(one.size() > 0 && two.size() > 0){
            if(one.get(0).x < two.get(0).x){
                merged_vals.add(one.remove(0));
            }
            else if(one.get(0).x > two.get(0).x){
                merged_vals.add(two.remove(0));
            }
            else{
                if(one.get(0).y < two.get(0).y){
                    merged_vals.add(one.remove(0));
                }
                else{
                    merged_vals.add(two.remove(0));
                }
            }
        }

        while(one.size() > 0){
            merged_vals.add(one.remove(0));
        }

        while(two.size() > 0){
            merged_vals.add(two.remove(0));
        }

        System.out.println("MERGED");
        for(Point x : merged_vals){
            System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
        }
        return merged_vals;
    }

    private static List<Point> condense_skyline(List<Point> hi){
        int loc = 0;
        List<Point> merged_vals = hi;
        while(loc < merged_vals.size()-1){

            //If the x-positions are the same
            if(merged_vals.get(loc).x == merged_vals.get(loc+1).x){
                //If the second one is higher
                if(merged_vals.get(loc+1).y > merged_vals.get(loc).y){
                    merged_vals.remove(loc);
                }
                //If the first one is higher
                else {
                    merged_vals.remove(loc+1);
                }
            }

            //If the height is the same as the prior, remove this one as it's unnecessary
            else if(merged_vals.get(loc).y == merged_vals.get(loc+1).y){
                merged_vals.remove(loc+1);
            }

            //Increment the location you're looking at
            else {
                loc++;
            }
        }
        return merged_vals;
    }



//    private static List<Point> merging(List<Point> one, List<Point> two){
//
//        List<Point> merged_vals = new ArrayList<>();
//        while(one.size() > 0 && two.size() > 0){
//            if(one.get(0).x < two.get(0).x){
//                merged_vals.add(one.remove(0));
//            }
//            else{
//                merged_vals.add(two.remove(0));
//            }
//        }
//
//        while(one.size() > 0){
//            merged_vals.add(one.remove(0));
//        }
//
//        while(two.size() > 0){
//            merged_vals.add(two.remove(0));
//        }
//
//        System.out.println("MERGED");
//        for(Point x : merged_vals){
//            System.out.println("X pos: " + x.x + "  Y pos: " + x.y);
//        }
//
//        int loc = 1;
//        while(loc < merged_vals.size()){
//
//            //If the x-positions are the same
//            if(merged_vals.get(loc).x == merged_vals.get(loc-1).x){
//                //If the second one is higher
//                if(merged_vals.get(loc).y > merged_vals.get(loc-1).y){
//                    merged_vals.remove(loc-1);
//                }
//                //If the first one is higher
//                else {
//                    merged_vals.remove(loc);
//                }
//            }
//
//            //If the height is the same as the prior, remove this one as it's unnecessary
//            else if(merged_vals.get(loc).y == merged_vals.get(loc-1).y){
//                merged_vals.remove(loc);
//            }
//
//            //Increment the location you're looking at
//            else {
//                loc++;
//            }
//        }
//        return merged_vals;
//    }
}
