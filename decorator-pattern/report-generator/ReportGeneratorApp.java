// Factory Pattern - Report Types
interface Report {
    void generate();
}

class PDFReport implements Report {
    @Override
    public void generate() {
        System.out.println("Generating PDF Report.");
    }
}

class ExcelReport implements Report {
    @Override
    public void generate() {
        System.out.println("Generating Excel Report.");
    }
}

class HTMLReport implements Report {
    @Override
    public void generate() {
        System.out.println("Generating HTML Report.");
    }
}

class ReportFactory {
    public static Report createReport(String type) {
        switch (type.toLowerCase()) {
            case "pdf":
                return new PDFReport();
            case "excel":
                return new ExcelReport();
            case "html":
                return new HTMLReport();
            default:
                throw new IllegalArgumentException("Unknown report type: " + type);
        }
    }
}

// Decorator Pattern - Adding Additional Details or Filters
abstract class ReportDecorator implements Report {
    protected Report decoratedReport;

    public ReportDecorator(Report report) {
        this.decoratedReport = report;
    }

    @Override
    public void generate() {
        decoratedReport.generate();
    }
}

class FooterDecorator extends ReportDecorator {
    public FooterDecorator(Report report) {
        super(report);
    }

    @Override
    public void generate() {
        super.generate();
        addFooter();
    }

    private void addFooter() {
        System.out.println("Adding footer to the report.");
    }
}

class SummaryDecorator extends ReportDecorator {
    public SummaryDecorator(Report report) {
        super(report);
    }

    @Override
    public void generate() {
        super.generate();
        addSummary();
    }

    private void addSummary() {
        System.out.println("Adding summary to the report.");
    }
}

class FilterDecorator extends ReportDecorator {
    public FilterDecorator(Report report) {
        super(report);
    }

    @Override
    public void generate() {
        super.generate();
        applyFilter();
    }

    private void applyFilter() {
        System.out.println("Applying filter to the report.");
    }
}

// Client Code
public class ReportGeneratorApp {
    public static void main(String[] args) {
        // Factory Pattern - Creating Report Types
        Report pdfReport = ReportFactory.createReport("pdf");
        Report excelReport = ReportFactory.createReport("excel");

        System.out.println("Generating PDF Report with Footer and Summary:");
        Report decoratedPDFReport = new SummaryDecorator(new FooterDecorator(pdfReport));
        decoratedPDFReport.generate();

        System.out.println("\nGenerating Excel Report with Filter:");
        Report decoratedExcelReport = new FilterDecorator(excelReport);
        decoratedExcelReport.generate();
    }
}

/**
Explanation:
1. `Report` is the product interface for the Factory pattern that defines the `generate()` method for generating reports.
2. `PDFReport`, `ExcelReport`, and `HTMLReport` are concrete implementations of the `Report` interface for different report formats.
3. `ReportFactory` is the factory class that creates instances of reports based on the input type.
4. `ReportDecorator` is the abstract base class for the Decorator pattern that allows additional details or filters to be added to a report.
5. `FooterDecorator`, `SummaryDecorator`, and `FilterDecorator` are concrete decorators that add specific functionalities to a report.
6. `ReportGeneratorApp` is the client code that demonstrates using both the Factory pattern for creating different report types and the Decorator pattern for adding additional details or filters to the reports.
*/
