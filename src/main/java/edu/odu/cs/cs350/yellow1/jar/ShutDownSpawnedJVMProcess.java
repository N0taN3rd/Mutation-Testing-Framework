/**
 *	Copyright (C) 2014  John Berlin
 *
 *	This program is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	This program is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *	along with this program.  If not, see http://www.gnu.org/licenses/
 */

package edu.odu.cs.cs350.yellow1.jar;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ProcessDestroyer;

/**
 * ProcessDestroyer to be used by {@link DefaultExecutor#setProcessDestroyer(ProcessDestroyer)} 
 * @author jberlin
 *
 */
public class ShutDownSpawnedJVMProcess implements ProcessDestroyer, Runnable {

	private Thread shutdownHookThread;
	private Process process;
	private final int timeoutSeconds;
	private final String processInfo;
	private boolean waitOnShutdown = false;

	public ShutDownSpawnedJVMProcess(String processInfo, int timeoutSeconds) {
		this.processInfo = processInfo;
		this.timeoutSeconds = timeoutSeconds;
	}

	public boolean getWaitOnShutdown() {
		return waitOnShutdown;
	}

	public void setWaitOnShutdown(boolean waitOnShutdown) {
		this.waitOnShutdown = waitOnShutdown;
	}

	@Override
	public void run() {
		 destroyProcess(waitOnShutdown);
	}

	public void destroyProcess(boolean waitForIt) {
		Process toDestroy = null;
		synchronized (this) {
			toDestroy = process;
			process = null;
		}

		if (toDestroy == null) {
			return;
		}

		toDestroy.destroy();

		if (waitForIt) {

			final Thread mainThread = Thread.currentThread();
			final Timer t = new Timer(true);
			final TimerTask task = new TimerTask() {
				@Override
				public void run() {
					mainThread.interrupt();
				}
			};
			t.schedule(task, timeoutSeconds * 1000L);
			try {
				toDestroy.waitFor();
				try {
					final int exit = toDestroy.exitValue();

				} catch (IllegalStateException ise) {

				}
			} catch (InterruptedException e) {

			} finally {
				t.cancel();
			}
		}
	}

	@Override
	public synchronized boolean add(Process arg0) {
		if (process != null) {
			throw new IllegalStateException("Process already set: " + process);
		}

		if (shutdownHookThread == null) {
			shutdownHookThread = new Thread(this, getClass().getSimpleName());
			Runtime.getRuntime().addShutdownHook(shutdownHookThread);
		}

		process = arg0;
		return true;
	}

	@Override
	public synchronized boolean remove(Process arg0) {
		arg0 = null;
		return true;
	}

	@Override
	public int size() {
		return 1;
	}

}
