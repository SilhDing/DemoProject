package cn.mldn.util;

class Point {
  double x;
  double y;
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  static int getOrientation(Point p, Point q, Point r) {
    // 0: collinear
    // 1:clockwise
    // 2: counter-clockwise
    double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    if (val == 0) return 0;
    return val > 0 ? 1 : 2;
  }

  static boolean checkBetween(Point p, Point q, Point r) {
    // check is r is between p and q
    if (r.x >= Math.min(p.x, q.x) && r.x <= Math.max(p.x, q.x) &&
      r.y >= Math.min(p.y, q.y) &&  r.y <= Math.max(p.y, q.y)) {
      return true;
    }
    return false;
  }
}


public class SegmentIntersection {

  private class Segment {

    Point p;
    Point q;

    public Segment(Point p, Point q) {
      this.p = p;
      this.q = q;
    }

    public boolean intersect(Segment s) {
      int o1 = Point.getOrientation(this.p, this.q, s.p);
      int o2 = Point.getOrientation(this.p, this.q, s.q);
      int o3 = Point.getOrientation(s.p, s.q, this.p);
      int o4 = Point.getOrientation(s.p, s.q, this.q);

      // General case
      if (o1 != o2 && o3 != o4) return true;

      // special case
      if (o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0) {
        return Point.checkBetween(this.p, this.q, s.p) ||
               Point.checkBetween(this.p, this.q, s.q) ||
               Point.checkBetween(s.p, s.q, this.p) ||
               Point.checkBetween(s.p, s.q, this.q);
      }

      return false;
    }

  }


}
