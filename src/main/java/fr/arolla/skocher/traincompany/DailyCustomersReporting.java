package fr.arolla.skocher.traincompany;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import fr.arolla.skocher.traincompany.domain.Customer;
import fr.arolla.skocher.traincompany.domain.Tap;

public class DailyCustomersReporting {

    private final String inputJsonTapsFilePath;
    private final String outputJsonSummaryFilePath;

    public static void main(String[] args) {
        String inputJsonTapsFilePath = args[0];
        String outputJsonSummaryFilePath = args[1];

        DailyCustomersReporting instance = new DailyCustomersReporting(inputJsonTapsFilePath, outputJsonSummaryFilePath);

        instance.exportJsonSummary();
    }

    public DailyCustomersReporting(String inputJsonTapsFilePath, String outputJsonSummaryFilePath) {
        this.inputJsonTapsFilePath = inputJsonTapsFilePath;
        this.outputJsonSummaryFilePath = outputJsonSummaryFilePath;
    }

    private void exportJsonSummary() {
        //Prepare data
        File inputJsonFile = new File(inputJsonTapsFilePath);
        List<Tap> taps = new TapsJsonParser(inputJsonFile).parse();
        List<Customer> customers = new DailyCustomersExtractor(taps).getCustomers();

        //Generate output
        OutputStream os;
        try {
            os = new FileOutputStream(outputJsonSummaryFilePath);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Error when creating FileOutputStream foir path " + outputJsonSummaryFilePath, e);
        }
        new CustomersSummariesJsonExporter(customers).fillJsonOutputStream(os);
    }

}
