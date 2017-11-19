package org.lfmexi.alphagalaxy.ui.workers;

import javax.swing.SwingWorker;

public interface SwingWorkerFactory<V, T> {
  SwingWorker<V, T> createSwingWorker();
}
