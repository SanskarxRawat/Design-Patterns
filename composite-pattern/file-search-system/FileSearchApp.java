import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Abstract base interface for file criteria
interface FileCriteria{
    boolean meetsCriteria(File file);
}

// Concrete implementation for name-based filtering
class NameCriteria implements FileCriteria {
    private String pattern;

    public NameCriteria(String pattern) {
        this.pattern = pattern.toLowerCase();
    }

    @Override
    public boolean meetsCriteria(File file) {
        return file.getName().toLowerCase().contains(pattern);
    }
}

// Concrete implementation for size-based filtering
class SizeCriteria implements FileCriteria {
    private Long minSize;
    private Long maxSize;

    public SizeCriteria(Long minSize, Long maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public boolean meetsCriteria(File file) {
        long fileSize = file.length();

        if (minSize != null && fileSize < minSize) {
            return false;
        }

        if (maxSize != null && fileSize > maxSize) {
            return false;
        }

        return true;
    }
}

// Composite criteria for combining multiple filters
class AndCriteria implements FileCriteria {
    private List<FileCriteria> criteriaList;

    public AndCriteria(FileCriteria... criteria) {
        this.criteriaList = Arrays.asList(criteria);
    }

    @Override
    public boolean meetsCriteria(File file) {
        return criteriaList.stream()
                .allMatch(criteria -> criteria.meetsCriteria(file));
    }
}

// Additional extensibility examples
class ModifiedTimeCriteria implements FileCriteria {
    private long olderThan;

    public ModifiedTimeCriteria(long olderThanMillis) {
        this.olderThan = olderThanMillis;
    }

    @Override
    public boolean meetsCriteria(File file) {
        long currentTime = System.currentTimeMillis();
        return (currentTime - file.lastModified()) > olderThan;
    }
}

class FileTypeCriteria implements FileCriteria {
    private List<String> allowedExtensions;

    public FileTypeCriteria(List<String> extensions) {
        this.allowedExtensions = extensions;
    }

    @Override
    public boolean meetsCriteria(File file) {
        String fileName = file.getName();
        return allowedExtensions.stream()
                .anyMatch(ext -> fileName.toLowerCase().endsWith(ext.toLowerCase()));
    }
}

// File search engine
class FileSearchEngine {
    private File baseDirectory;

    public FileSearchEngine(String baseDirectoryPath) {
        this.baseDirectory = new File(baseDirectoryPath);
    }

    public List<File> search(FileCriteria criteria) {
        List<File> matchingFiles = new ArrayList<>();
        searchRecursively(baseDirectory, criteria, matchingFiles);
        return matchingFiles;
    }

    private void searchRecursively(File directory, FileCriteria criteria, List<File> matchingFiles) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchRecursively(file, criteria, matchingFiles);
                } else if (criteria.meetsCriteria(file)) {
                    matchingFiles.add(file);
                }
            }
        }
    }
}



public class FileSearchApp {
    public static void main(String[] args) {
        // Create a search engine for a specific directory
        FileSearchEngine searchEngine = new FileSearchEngine("/path/to/directory");

        // Create individual criteria
        FileCriteria nameCriteria = new NameCriteria("report");
        FileCriteria sizeCriteria = new SizeCriteria(1024L, 1024 * 1024L);

        // Combine criteria
        FileCriteria combinedCriteria = new AndCriteria(nameCriteria, sizeCriteria);

        // Perform search
        List<File> results = searchEngine.search(combinedCriteria);

        // Print matching files
        for (File file : results) {
            System.out.println("Matching file: " + file.getAbsolutePath());
        }
    }
}
