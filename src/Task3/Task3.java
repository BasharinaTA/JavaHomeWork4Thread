package Task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Task3 {
    public static void main(String[] args) throws InterruptedException, IOException {
        var deque = new Deque();

       Thread thread =  new Thread(() -> {
            while (!deque.strings.isEmpty() || !deque.fileRead) {
                var str = deque.strings.pollFirst();
                if (str != null) {
                    deque.patients.add(createPatient(str));
                }
            }
        });

       thread.start();

        String file = "dump.txt";
        var patientsString = Files.lines(Paths.get(file));

        new Thread(() -> {
            patientsString.forEach((str) -> deque.strings.add(str));
            deque.fileRead = true;
        }).start();

        thread.join();

        deque.patients.forEach((System.out::print));
    }

    private static Patient createPatient(String str) {
        return new Patient(str.replaceAll(",", "")
                .replaceAll("'", "")
                .replace("(", "")
                .replace(")", ""));
    }

    public static class Deque {
        public ConcurrentLinkedDeque<String> strings = new ConcurrentLinkedDeque<>();
        public ConcurrentLinkedDeque<Patient> patients = new ConcurrentLinkedDeque<>();
        public boolean fileRead;
    }
}