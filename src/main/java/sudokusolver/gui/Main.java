package sudokusolver.gui;

import sudokusolver.gui.solver.Solver;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.run(args);
    }
}
