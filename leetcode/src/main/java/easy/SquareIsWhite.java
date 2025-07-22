package easy;

public class SquareIsWhite {
    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a';
        int y = coordinates.charAt(1) - '1';
        return (x / 2) != (y / 2);
    }
    
    public static void main(String[] args) {
        SquareIsWhite solution = new SquareIsWhite();
        System.out.println(solution.squareIsWhite("a1"));
        System.out.println(solution.squareIsWhite("a2"));
        System.out.println(solution.squareIsWhite("a3"));
        System.out.println(solution.squareIsWhite("a7"));
    }
}
