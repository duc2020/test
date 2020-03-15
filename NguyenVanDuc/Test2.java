import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;


public class Test2 {
    static {
        System.loadLibrary("jniortools");
    }

    public static void main(String[] args) {
        MPSolver solver = new MPSolver("duc", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

        double infinity = Double.POSITIVE_INFINITY;

//      //x and y are continous non-negative variables
        MPVariable x = solver.makeNumVar(0.0, infinity, "x");
        MPVariable y = solver.makeNumVar(0.0, infinity, "y");
        System.out.println("Number of variables: " + solver.numVariables());

        //define the constraints
        // x + 2*y <= 14
        MPConstraint c0 = solver.makeConstraint(-infinity, 14.0, "c0");
        c0.setCoefficient(x, 1);
        c0.setCoefficient(y, 2);

        //3*x - y >= 0
        MPConstraint c1 = solver.makeConstraint(0.0, infinity, "c1");
        c1.setCoefficient(x, 3);
        c1.setCoefficient(y, -1);

        // x - y <= 2
        MPConstraint c2 = solver.makeConstraint(-infinity, 2, "c2");
        c2.setCoefficient(x, 1);
        c2.setCoefficient(y, -1);

        System.out.println("number of constraints: " + solver.numConstraints());

        //define the objective function
        // Maximize 3*x + 4*y
        MPObjective objective = solver.objective();
        objective.setCoefficient(x, 3);
        objective.setCoefficient(y, 4);
        objective.setMaximization();

        //invoke the solver
        final MPSolver.ResultStatus resultStatus = solver.solve();
        //check that the problem has an optimal solution
        if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
            System.err.println("the problem does not have an optimal solution!");
            return;
        }

        //Display solution
        System.out.println("solution");
        System.out.println("x = " + x.solutionValue());
        System.out.println("y = " + y.solutionValue());
        System.out.println("Optimal objective value = " + solver.objective().value());

    }

}
