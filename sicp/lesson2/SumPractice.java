class SumPractice {
    public static void main(String[] args) {
        int x = 3, y = 4;
        //递归
        int recursion = sumByRecursion(x, y);
        //迭代(线性递归/尾递归)
        int iteration = sumByIteration(x, y);
    }

    private static int sumByRecursion(int x, int y) {
        if (x == 0) {
            return y;
        }
        return 1 + sumByRecursion(x - 1, y);
    }

    private static int sumByIteration(int x, int y) {
        if (x == 0) {
            return y;
        }
        return sumByIteration(x - 1, y + 1);
    }
}