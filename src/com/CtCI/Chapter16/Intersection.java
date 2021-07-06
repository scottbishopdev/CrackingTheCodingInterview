package com.CtCI.Chapter16;

// Problem: Given two straight line segments (represented as a start point and end point), compute the point of
// intersection, if any.

// Started: 12:05 AM, Finished: 1:33 AM

public class Intersection {
    public static void main(String[] args) {
        // Test:
        LineSegment lineA = new LineSegment(new Point(-2, 4), new Point(4, -1));
        LineSegment lineB = new LineSegment(new Point(-4, 2), new Point(2, 5));

        System.out.println("--- Line A ---");
        lineA.print();

        System.out.println("--- Line B ---");
        lineB.print();

        Point intersection = ComputeIntersection(lineA, lineB);

        System.out.println("--- Intersection ---");
        intersection.print();
    }

    private static Point ComputeIntersection(LineSegment lineA, LineSegment lineB) {
        if(lineA.minX > lineB.maxX || lineB.minX > lineA.maxX) {
            // The lines can't possibly intersect since their max and min points don't overlap in the X axis.
            throw new IllegalArgumentException("The provided line segments do not intersect, their X values do not overlap.");
        }

        if(lineA.minY > lineB.maxY || lineB.minY > lineA.maxY) {
            // The lines can't possibly intersect since their max and min points don't overlap in the Y axis.
            throw new IllegalArgumentException("The provided line segments do not intersect, their Y values do not overlap.");
        }

        if(lineA.slope == lineB.slope) {
            // Should we throw an exception? What's the intersection of two lines that are the same in terms of a point?
            throw new IllegalArgumentException("The provided line segments represent the same line.");
        }

        // Should we check to see if the provided points are equal? In that case, they could be "intersections".

        double intersectX = (lineB.yIntercept - lineA.yIntercept) / (lineA.slope - lineB.slope);
        double intersectY = lineA.getYFromX(intersectX);

        return new Point(intersectX, intersectY);
    }

    private static class LineSegment {
        public Point startPoint;
        public Point endPoint;
        public double slope;
        public double yIntercept;
        public double minX;
        public double maxX;
        public double minY;
        public double maxY;

        public LineSegment (Point start, Point end) {
            startPoint = start;
            endPoint = end;
            slope = (end.y - start.y) / (end.x - start.x);
            yIntercept = start.y - (slope * start.x);

            minX = Math.min(start.x, end.x);
            maxX = Math.max(start.x, end.x);
            minY = Math.min(start.y, end.y);
            maxY = Math.max(start.y, end.y);
        }

        public double getXFromY(double yVal) {
            if(isVertical()) return Double.POSITIVE_INFINITY;
            return (yVal - yIntercept) / slope;
        }

        public double getYFromX(double xVal) {
            return (xVal * slope) + yIntercept;
        }

        public boolean isVertical() {
            return startPoint.x == endPoint.x;
        }

        public boolean isHorizontal() {
            return startPoint.y == endPoint.y;
        }

        public void print() {
            System.out.format("    Start: (%f, %f)%n", startPoint.x, startPoint.y);
            System.out.format("    End: (%f, %f)%n", endPoint.x, endPoint.y);
            System.out.format("    Slope: %f%n", slope);
            System.out.format("    Y-Intersection: %f%n", yIntercept);
        }
    }

    private static class Point {
        public double x;
        public double y;

        public Point(double xVal, double yVal) {
            x = xVal;
            y = yVal;
        }

        public void print() {
            System.out.format("    (%f, %f)%n", x, y);
        }
    }
}
