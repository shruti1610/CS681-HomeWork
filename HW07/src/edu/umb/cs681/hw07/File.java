package edu.umb.cs681.hw07;

import java.time.Instant;
import java.util.concurrent.locks.ReentrantLock;

public class File {
    private boolean changed = false;
    private ReentrantLock lock = new ReentrantLock();

    public void change() {
        lock.lock();
        try {
            changed = true;
        } finally {
            lock.unlock();
        }
    }

    public void save() {
        lock.lock();
        try {
            if (!changed)
                return;
            System.out.println("File saved at "+ Instant.now());
            changed = false;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        File file = new File();
        Editor editor = new Editor(file);
        AutoSaver autoSaver = new AutoSaver(file);
        Thread t1 = new Thread(editor);
        Thread t2 = new Thread(autoSaver);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        editor.setDone();
        autoSaver.setDone();
    }
}
