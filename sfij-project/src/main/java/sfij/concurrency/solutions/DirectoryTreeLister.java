package sfij.concurrency.solutions;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryTreeLister {

    private final Scanner scanner = new Scanner(System.in);
    private final ExecutorService threadPool;

    public DirectoryTreeLister() {
        threadPool = Executors.newFixedThreadPool(6);
    }

    // This map will hold the result of all directory scans.
    private final Map<String, Future<List<Path>>> allResults = new HashMap<>();

    public void getAndExecuteUserInput() {
        boolean stop = false;
        do {
            System.out.println("\nChoose an option from the menu: ");
            System.out.println("  1. Search a directory");
            System.out.println("  2. List all searches that have been started");
            System.out.println("  3. Show search results");
            System.out.println("  4. Quit");
            System.out.print("\n==> ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    listTree();
                    break;
                case "2":
                    showAllListings();
                    break;
                case "3":
                    showListing();
                    break;
                case "4":
                    stop = true;
                    break;
                default:
                    System.out.println("Please type a number from 1 to 4");
            }
        } while (!stop);
    }

    // Get user input and recursively list directory
    private void listTree() {

        System.out.print("Enter a directory name: ");
        String directoryName = scanner.nextLine();

        Path path = Paths.get(directoryName);
        if (!Files.isDirectory(path)) {
            System.out.println("That isn't a directory");
        } else {
            System.out.println("Starting search of " + directoryName);

            Callable<List<Path>> callable = new Callable<List<Path>>() {
                @Override
                public List<Path> call() throws Exception {
                    return listTree(path)
                            .collect(Collectors.toList());
                }
            };
            allResults.put(directoryName, threadPool.submit(callable));
        }
    }

    private Stream<Path> listTree(Path path) {
        if (!Files.isDirectory(path)) {
            return Stream.of(path);
        } else {
            try {
                return Files.list(path)
                        .flatMap(this::listTree);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    // List all the search operations that have been submitted so far
    private void showAllListings() {
        System.out.println("Here is a list of all searches that have been started:");
        for (String directoryName : allResults.keySet()) {
            System.out.println("  " + directoryName + " " +
                    (allResults.get(directoryName).isDone() ? "completed" : "still under way"));
        }
    }

    // Show the results for a particular search.
    private void showListing() {

        System.out.print("Enter a directory name: ");
        String directoryName = scanner.nextLine();

        Future<List<Path>> future = allResults.get(directoryName);
        if (future != null) {
                if (!future.isDone()) {
                    System.out.println("That search is still happening");
                } else {
                    List<Path> files = null;
                    try {
                        files = allResults.get(directoryName).get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Entries in directory " + directoryName);
                    for (Path path : files) {
                        System.out.println("  " + (Files.isDirectory(path) ? "[D] " : "[F] ") + path);
                    }
                }
        } else {
            System.out.println("No search results available for " + directoryName);
        }
    }

    public static void main(String[] args) {
        new DirectoryTreeLister().getAndExecuteUserInput();
    }
}

