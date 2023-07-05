package model;

import javax.swing.SwingWorker;

public class SolverTask<T extends Field> extends SwingWorker<T, Integer> {

	private T field;
	private Solver solver = new Solver();
	
	public SolverTask(T field) {
		this.field = field;
		solver.setUpdateListener((step) -> {
			step /= 10000;
			setProgress(step < 100 ? step : 99);
		});
		solver.setIsCancelled(() -> { return isCancelled(); });
	}
	
	@Override
	protected T doInBackground() throws Exception {
		return solver.solve(field);
	}
	
	@Override
	protected void done() {
		setProgress(100);
	}

}
