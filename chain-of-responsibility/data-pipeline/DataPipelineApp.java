// Chain of Responsibility Pattern - Data Filter
abstract class DataFilter {
    protected DataFilter nextFilter;

    public void setNextFilter(DataFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    public void process(String data) {
        data = applyFilter(data);
        if (nextFilter != null) {
            nextFilter.process(data);
        } else {
            System.out.println("Final Processed Data: " + data);
        }
    }

    protected abstract String applyFilter(String data);
}

// Concrete Filter Classes
class RemovePunctuationFilter extends DataFilter {
    @Override
    protected String applyFilter(String data) {
        return data.replaceAll("[\\p{Punct}]", "");
    }
}

class LowercaseFilter extends DataFilter {
    @Override
    protected String applyFilter(String data) {
        return data.toLowerCase();
    }
}

class TrimWhitespaceFilter extends DataFilter {
    @Override
    protected String applyFilter(String data) {
        return data.trim();
    }
}

// Decorator Pattern - Data Enrichment
interface DataEnricher {
    String enrich(String data);
}

// Base Decorator Class
class BaseDataEnricher implements DataEnricher {
    @Override
    public String enrich(String data) {
        return data;
    }
}

// Concrete Decorators
class AddTimestampEnricher extends BaseDataEnricher {
    private DataEnricher enricher;

    public AddTimestampEnricher(DataEnricher enricher) {
        this.enricher = enricher;
    }

    @Override
    public String enrich(String data) {
        String enrichedData = enricher.enrich(data);
        return enrichedData + " | Timestamp: " + System.currentTimeMillis();
    }
}

class AddSourceInfoEnricher extends BaseDataEnricher {
    private DataEnricher enricher;

    public AddSourceInfoEnricher(DataEnricher enricher) {
        this.enricher = enricher;
    }

    @Override
    public String enrich(String data) {
        String enrichedData = enricher.enrich(data);
        return enrichedData + " | Source: SensorX";
    }
}

// Client Code
public class DataPipelineApp {
    public static void main(String[] args) {
        // Chain of Responsibility - Filtering Data
        DataFilter removePunctuation = new RemovePunctuationFilter();
        DataFilter lowercase = new LowercaseFilter();
        DataFilter trimWhitespace = new TrimWhitespaceFilter();

        removePunctuation.setNextFilter(lowercase);
        lowercase.setNextFilter(trimWhitespace);

        String rawData = "  Hello, World! This is a test.  ";
        System.out.println("Original Data: " + rawData);
        removePunctuation.process(rawData);

        // Decorator - Enriching Data
        DataEnricher baseEnricher = new BaseDataEnricher();
        DataEnricher timestampEnricher = new AddTimestampEnricher(baseEnricher);
        DataEnricher fullEnricher = new AddSourceInfoEnricher(timestampEnricher);

        String enrichedData = fullEnricher.enrich("Processed Data: Hello World");
        System.out.println("Enriched Data: " + enrichedData);
    }
}

/**
Explanation:
1. `DataFilter` is the abstract base class for the Chain of Responsibility, with a method `process()` to apply filters in sequence.
2. `RemovePunctuationFilter`, `LowercaseFilter`, and `TrimWhitespaceFilter` are concrete filters that modify the data.
3. `DataEnricher` is the interface for the Decorator pattern, and `BaseDataEnricher` is the base class.
4. `AddTimestampEnricher` and `AddSourceInfoEnricher` are concrete decorators that add extra information to the data.
5. `DataPipelineApp` is the client that demonstrates filtering the data using Chain of Responsibility and enriching it using Decorator.
*/
